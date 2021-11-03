// Decompiled with: Procyon 0.5.36
// Class Version: 1
package network;

import face.DialogUI;
import base.GameControl;
import face.LandUI;
import base.DCanvas;
import javax.microedition.io.Connector;
import base.Macro;
import java.io.DataInputStream;
import javax.microedition.io.HttpConnection;

public class HttpConntor implements NetConnector, Runnable
{
    public boolean blnIsRun;
    private static HttpConntor HC;
    private String host;
    private String URLName;
    private HttpConnection Http_C;
    private DataInputStream dis;
    private byte bytSendNum;
    private boolean BLNAGENT;
    long RE_TIME;
    long RQ_TIME;
    
    private HttpConntor() {
        this.BLNAGENT = false;
        this.RE_TIME = System.currentTimeMillis();
    }
    
    public static HttpConntor getInstance() {
        if (HttpConntor.HC == null) {
            HttpConntor.HC = new HttpConntor();
            if (HttpConntor.HC.BLNAGENT && Macro.FirstConntor == 0) {
                Macro.FirstConntor = 1;
            }
        }
        return HttpConntor.HC;
    }
    
    public void setParameter(final String host) {
        if (host != null) {
            if (host.startsWith("http://")) {
                this.host = host.substring("http://".length(), host.length());
            }
            else {
                this.host = host;
            }
            final int index = this.host.indexOf("/");
            String substring;
            if (index != -1) {
                substring = this.host.substring(index, this.host.length());
                this.host = this.host.substring(0, index);
            }
            else {
                substring = "/";
            }
            if (this.BLNAGENT) {
                this.URLName = "http://10.0.0.172:80" + substring;
            }
            else {
                this.URLName = "http://" + this.host + substring;
                this.host = null;
            }
        }
    }
    
    private void sendFirst(final byte[] array) throws Exception {
        if (this.BLNAGENT) {
            (this.Http_C = (HttpConnection)Connector.open(this.URLName, 1, true)).setRequestProperty("X-Online-Host", this.host);
        }
        else {
            this.Http_C = (HttpConnection)Connector.open(this.URLName, 1, true);
        }
        this.Http_C.setRequestProperty("datastart", new String(array));
        this.Http_C.getResponseCode();
        this.close();
    }
    
    private void createHttpConnect(final byte[] array) {
        try {
            if (Macro.FirstConntor == 1) {
                Macro.FirstConntor = 2;
                this.sendFirst(array);
            }
            this.RE_TIME = System.currentTimeMillis();
            this.RQ_TIME = 0L;
            if (Macro.BLN_HTTP_TIME) {
                Macro.netUseInfoVector.addElement("DOS" + Macro.netSendFollowDOS);
                this.RE_TIME = System.currentTimeMillis();
                Macro.netUseInfoVector.addElement("HTTP 请求：" + this.RE_TIME);
            }
            if (this.BLNAGENT) {
                (this.Http_C = (HttpConnection)Connector.open(this.URLName, 1, true)).setRequestProperty("X-Online-Host", this.host);
            }
            else {
                this.Http_C = (HttpConnection)Connector.open(this.URLName, 1, true);
            }
            this.Http_C.setRequestProperty("datastart", new String(array));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void send(final byte[] array) throws Exception {
        try {
            final int responseCode = this.Http_C.getResponseCode();
            if (responseCode != 200) {
                throw new Exception("responseCode " + responseCode);
            }
            this.dis = this.Http_C.openDataInputStream();
            if (Macro.BLN_HTTP_TIME) {
                this.RQ_TIME = System.currentTimeMillis();
                Macro.netUseInfoVector.addElement("HTTP 返回：" + this.RQ_TIME);
                Macro.TIME_HTTP_CUT = this.RQ_TIME - this.RE_TIME;
                Macro.netUseInfoVector.addElement("HTTP 消耗时间差：" + Macro.TIME_HTTP_CUT);
            }
            final byte[] receive = this.receive();
            NetManager.getInstance().endStartTime = System.currentTimeMillis();
            if (Macro.BLN_NET_DATA_COUNT) {
                Macro.sendDataCount += NetManager.getInstance().bytSendData.length;
                Macro.recvDataCount += receive.length;
            }
            NetManager.getInstance().delSendData();
            this.bytSendNum = 0;
            if (receive != null) {
                NetParse.getInstance().addNetPacket(receive);
            }
            else if (NetManager.getInstance().blnOnceErr) {
                NetManager.getInstance().setErr(getInstance());
            }
            else {
                final NetManager instance = NetManager.getInstance();
                ++instance.bytErrNum;
                if (Macro.bytGameType != 1) {
                    NetManager.getInstance().blnIsSend = false;
                    NetManager.getInstance().blnSendHeart = false;
                    DCanvas.getInstance().setNetLoad(false);
                    if (!LandUI.getInstance().blnIsIn) {
                        GameControl.getInstance().CreateState((byte)7);
                        DialogUI.getInstance().setDialog((byte)7, "已从服务器断开(2001)", (byte)2);
                    }
                    else {
                        GameControl.getInstance().CreateState((byte)7);
                        DialogUI.getInstance().setDialog((byte)7, "已从服务器断开(2002)", (byte)2);
                    }
                }
                else if (NetManager.getInstance().bytErrNum > 10) {
                    NetManager.getInstance().setErr(getInstance());
                }
            }
        }
        catch (Exception ex) {
            throw new Exception("eee: " + ex.getMessage());
        }
        finally {
            this.close();
        }
        this.close();
    }
    
    public void close() {
        try {
            if (this.dis != null) {
                this.dis.close();
                this.dis = null;
            }
            if (this.Http_C != null) {
                this.Http_C.close();
                this.Http_C = null;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void reload() {
    }
    
    public void start() {
        this.bytSendNum = 0;
        new Thread(this).start();
    }
    
    public void run() {
        while (this.blnIsRun) {
            try {
                final NetManager instance = NetManager.getInstance();
                final short shtHeartTime = instance.shtHeartTime;
                NetManager.getInstance();
                instance.shtHeartTime = (short)(shtHeartTime + 70);
                if (NetManager.getInstance().blnIsSend || NetManager.getInstance().blnSendErr) {
                    NetManager.getInstance().blnIsSend = false;
                    NetManager.getInstance().blnSendErr = false;
                    if (this.bytSendNum == 0) {
                        NetManager.getInstance().saveSendData();
                    }
                    this.createHttpConnect(NetManager.getInstance().bytSendData);
                    NetManager.getInstance().sendStartTime = System.currentTimeMillis();
                    NetManager.getInstance().endStartTime = 0L;
                    NetManager.getInstance().blnTryReConn = true;
                    this.send(NetManager.getInstance().bytSendData);
                    NetManager.getInstance().endStartTime = System.currentTimeMillis();
                }
                else if (NetManager.getInstance().shtHeartTime >= NetManager.getInstance().shtHeartTimeMax && NetManager.getInstance().blnSendHeart) {
                    if (!NetManager.getInstance().blnPause) {
                        NetSend.getInstance().sendHeart();
                    }
                    else {
                        NetManager.getInstance().sendHeart();
                    }
                    NetManager.getInstance().shtHeartTime = 0;
                }
                NetManager.getInstance();
                Thread.sleep(70L);
            }
            catch (Exception ex) {
                if (NetManager.getInstance().blnOnceErr) {
                    if (NetManager.getInstance().blnTimeOut) {
                        NetManager.getInstance().autoReConnect();
                    }
                    else {
                        NetManager.getInstance().MC.reload();
                        NetManager.getInstance().blnIsSend = false;
                        NetManager.getInstance().setErr(getInstance());
                    }
                }
                else {
                    ++this.bytSendNum;
                    if (this.bytSendNum < 10) {
                        NetManager.getInstance().blnSendErr = true;
                    }
                    else {
                        NetManager.getInstance().blnIsSend = false;
                        NetManager.getInstance().setErr(getInstance());
                    }
                }
                ex.printStackTrace();
            }
        }
    }
    
    public byte[] receive() throws Exception {
        byte[] array = null;
        if (this.blnIsRun) {
            try {
                array = new byte[this.dis.readShort() & 0xFFFF];
                this.dis.readFully(array);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return array;
    }
    
    public boolean isRunning() {
        return this.blnIsRun;
    }
    
    public void setRunning(final boolean blnIsRun) {
        this.blnIsRun = blnIsRun;
    }
}
