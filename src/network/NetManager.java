// 
// Decompiled by Procyon v0.5.36
// 
package network;

import javax.microedition.io.Connection;
import javax.microedition.io.InputConnection;
import javax.wireless.messaging.Message;
import javax.wireless.messaging.TextMessage;
import javax.wireless.messaging.MessageConnection;
import base.Param;
import means.DebugFrame;
import javax.microedition.io.Connector;
import java.io.IOException;
import face.LandUI;
import face.DialogUI;
import base.GameControl;
import base.Macro;
import base.DCanvas;
import java.io.DataInputStream;
import javax.microedition.io.HttpConnection;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import java.util.Vector;

public class NetManager {

    public boolean blnIsSend;
    public static NetManager NM;
    public NetConnector MC;
    public static Vector threadPool;
    public static ByteArrayOutputStream baos;
    public static DataOutputStream output;
    public static final short THREADTIME = 70;
    public long sendStartTime;
    public long endStartTime;
    public boolean blnTimeOut;
    public boolean blnTryReConn;
    public boolean blnExitGame;
    public boolean blnSendHeart;
    public short shtHeartTimeMax;
    public short shtHeartTime;
    public byte bytErrNum;
    public boolean blnOnceErr;
    byte bytNetConnectType;
    byte[] bytSendData;
    boolean blnSendErr;
    public boolean blnPause;
    private static HttpConnection hcon;
    private static DataInputStream dis;
    public static String message;
    public static String strURL;
    private static int sms_send_message_send;

    static {
        NetManager.threadPool = new Vector(1, 1);
        NetManager.hcon = null;
        NetManager.dis = null;
        NetManager.message = null;
        NetManager.strURL = "";
        NetManager.sms_send_message_send = 0;
    }

    private NetManager() {
        this.sendStartTime = 0L;
        this.endStartTime = 0L;
        this.blnTimeOut = false;
        this.blnTryReConn = false;
        this.shtHeartTimeMax = 6000;
        NetManager.baos = new ByteArrayOutputStream();
        NetManager.output = new DataOutputStream(NetManager.baos);
    }

    public static NetManager getInstance() {
        if (NetManager.NM == null) {
            NetManager.NM = new NetManager();
        }
        return NetManager.NM;
    }

    public void clean() {
        this.stopNetManager(this.MC);
        NetManager.baos = null;
        NetManager.output = null;
        NetManager.NM = null;
    }

    public void setConnectionType(final byte _type, final String _url) {
        NetHandler.reNetHandler();
        if (this.MC != null) {
            this.MC.setRunning(false);
            this.MC = null;
        }
        this.MC = NetFactory.getConntion(_type, _url);
        this.bytNetConnectType = _type;
        this.blnIsSend = false;
        if (!this.MC.isRunning()) {
            this.MC.setRunning(true);
            this.MC.start();
        }
    }

    public void saveSendData() {
        try {
            this.bytSendData = NetHandler.send(this.bytNetConnectType);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delSendData() {
        this.bytSendData = null;
        this.blnSendErr = false;
    }

    public void stopNetManager(final NetConnector con) {
        con.setRunning(false);
        this.blnIsSend = false;
        this.blnSendHeart = false;
    }

    public void setErr(final NetConnector con) {
        this.stopNetManager(con);
        DCanvas.getInstance().setNetLoad(false);
        if (Macro.bytGameType == 1) {
            GameControl.getInstance().CreateState((byte) 7);
            DialogUI.getInstance().setDialog((byte) 7, "Đã ngắt kết nối khỏi máy chủ (1003)", (byte) 2);
        } else if (LandUI.getInstance() != null && !LandUI.getInstance().blnIsIn) {
            Macro.FirstConntor = 1;
            NetSend.getInstance().blnStartNet = false;
            if (LandUI.getInstance() != null && (LandUI.getInstance().landState == 4 || LandUI.getInstance().landState == 9)) {
                GameControl.getInstance().CreateState((byte) 7);
                DialogUI.getInstance().setDialog((byte) 7, "không thể kết nối đến máy chủ(1004)", (byte) 2);
            }
        } else {
            GameControl.getInstance().CreateState((byte) 7);
            DialogUI.getInstance().setDialog((byte) 7, "Mất kết nối đến máy chủ(1005)", (byte) 2);
        }
    }

    public static DataOutputStream getDOS(final short _Id) {
        try {
            if (Macro.netSendFollowDOS == _Id) {
                Macro.TIME_START_CUT = System.currentTimeMillis();
                Macro.BLN_HTTP_TIME = true;
                new StringBuffer("S:").append(_Id).append("=").append("SENDING...").toString();
            }
            NetManager.baos.reset();
            NetManager.output.writeShort(0);
            NetManager.output.writeShort(_Id);
            return NetManager.output;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addPacket() {
        if (this.blnExitGame || this.blnPause) {
            return;
        }
        try {
            NetManager.baos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        NetHandler.add(NetManager.baos.toByteArray());
        NetManager.baos.reset();
        this.blnIsSend = true;
        this.shtHeartTime = 0;
    }

    public void addPacket1() {
        try {
            NetManager.baos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        NetHandler.add(NetManager.baos.toByteArray());
        NetManager.baos.reset();
        this.blnIsSend = true;
        this.shtHeartTime = 0;
    }

    public void pauseNet() {
        this.blnPause = true;
        try {
            NetManager.baos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        NetManager.baos.reset();
    }

    public void resumeNet() {
        this.blnPause = false;
        try {
            NetManager.baos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        NetManager.baos.reset();
        this.blnIsSend = false;
        this.shtHeartTimeMax = 1000;
    }

    public void sendHeart() {
        getDOS((short) 5120);
        try {
            NetManager.baos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        NetHandler.add(NetManager.baos.toByteArray());
        NetManager.baos.reset();
        this.blnIsSend = true;
        this.shtHeartTime = 0;
    }

    public static void sendHttpGet(final String url) {
        NetManager.strURL = url;
        new Thread() {
            public void run() {
                try {
                    String host = null;
                    if (NetManager.strURL != null) {
                        String _URLName = null;
                        if (NetManager.strURL.startsWith("http://")) {
                            host = NetManager.strURL.substring("http://".length(), url.length());
                        } else {
                            host = NetManager.strURL;
                        }
                        final int pos = host.indexOf("/");
                        if (pos != -1) {
                            _URLName = host.substring(pos, host.length());
                            host = host.substring(0, pos);
                        } else {
                            _URLName = "/";
                        }
                        //NetManager.strURL = "http://10.0.0.172:80" + _URLName;
                            }
                    NetManager.hcon = (HttpConnection) Connector.open(NetManager.strURL);
                    NetManager.hcon.setRequestProperty("X-Online-Host", host);
                    NetManager.dis = new DataInputStream(((InputConnection) NetManager.hcon).openInputStream());
                    final byte[] b = new byte[1024];
                    int len = 0;
                    final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    final DataOutputStream doss = new DataOutputStream(baos);
                    while ((len = NetManager.dis.read(b)) != -1) {
                        doss.write(b, 0, len);
                    }
                    final byte[] datas = baos.toByteArray();
                    NetManager.message = new String(datas, "UTF-8");
                    DebugFrame.getInstance().logIn("message:" + NetManager.message);
                } catch (Exception e) {
                    e.printStackTrace();
                    try {
                        if (NetManager.hcon != null) {
                            ((Connection) NetManager.hcon).close();
                        }
                        if (NetManager.dis != null) {
                            NetManager.dis.close();
                        }
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    return;
                } finally {
                    try {
                        if (NetManager.hcon != null) {
                            ((Connection) NetManager.hcon).close();
                        }
                        if (NetManager.dis != null) {
                            NetManager.dis.close();
                        }
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                try {
                    if (NetManager.hcon != null) {
                        ((Connection) NetManager.hcon).close();
                    }
                    if (NetManager.dis != null) {
                        NetManager.dis.close();
                    }
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }.start();
    }

    public static void sendLoginChinaMobileGet(final String strURL, final boolean blnDown) {
        new Thread() {

            public void run() {
                HttpConnection hconM = null;
                DataInputStream disM = null;
                String urlStr = strURL;
                try {
                    String host = null;
                    if (strURL != null) {
                        String _URLName = null;
                        if (strURL.startsWith("http://")) {
                            host = strURL.substring("http://".length(), strURL.length());
                        } else {
                            host = strURL;
                        }
                        final int pos = host.indexOf("/");
                        if (pos != -1) {
                            _URLName = host.substring(pos, host.length());
                            host = host.substring(0, pos);
                        } else {
                            _URLName = "/";
                        }
                        //urlStr = "http://10.0.0.172:80" + _URLName;
                    }
                    hconM = (HttpConnection) Connector.open(strURL);
                    hconM.setRequestProperty("X-Online-Host", host);
                    disM = new DataInputStream(((InputConnection) hconM).openInputStream());
                    final byte[] b = new byte[1024];
                    int len = 0;
                    final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    final DataOutputStream doss = new DataOutputStream(baos);
                    while ((len = disM.read(b)) != -1) {
                        doss.write(b, 0, len);
                    }
                    final byte[] datas = baos.toByteArray();
                    final String content = new String(datas, "UTF-8");
                    String userID = NetManager.subStringBetween(content, "userId=", "\r");
                    if (!GameControl.getInstance().isPhone) {
                        userID = "";
                    }
                    if (userID != null && !userID.equals("null") && userID.length() > 0) {
                        Param.chinaMobileUserID = userID;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                } finally {
                    try {
                        if (hconM != null) {
                            ((Connection) hconM).close();
                        }
                        if (disM != null) {
                            disM.close();
                        }
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    if (Param.chinaMobileUserID != null && !Param.chinaMobileUserID.equals("") && !Param.chinaMobileUserID.equals("null") && Param.chinaMobileUserID.length() > 0 && blnDown) {
                        NetSend.getInstance().sendUserIDToDownURL(Param.chinaMobileUserID);
                    }
                }
                try {
                    if (hconM != null) {
                        ((Connection) hconM).close();
                    }
                    if (disM != null) {
                        disM.close();
                    }
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                if (Param.chinaMobileUserID != null && !Param.chinaMobileUserID.equals("") && !Param.chinaMobileUserID.equals("null") && Param.chinaMobileUserID.length() > 0 && blnDown) {
                    NetSend.getInstance().sendUserIDToDownURL(Param.chinaMobileUserID);
                }
            }
        }.start();
    }

    public void sendMessage(final String phoneNumber, final String messageTxt1, final String messageTxt2, final int sendCount) {
        NetManager.sms_send_message_send = 0;
        new Thread(new Runnable() {
            public void run() {
                try {
                    do {
                        MessageConnection connection = null;
                        try {
                            final String address = "sms://" + phoneNumber;
                            final String mess = String.valueOf(messageTxt1) + NetManager.sms_send_message_send + messageTxt2;
                            connection = (MessageConnection) Connector.open(address, 2, true);
                            final TextMessage textMessage = (TextMessage) connection.newMessage("text");
                            ((Message) textMessage).setAddress(address);
                            textMessage.setPayloadText(mess);
                            connection.send((Message) textMessage);
                            if (!connection.toString().equals(String.valueOf(connection.getClass().getName()) + '@' + Integer.toHexString(connection.hashCode())) || !textMessage.toString().equals(String.valueOf(textMessage.getClass().getName()) + '@' + Integer.toHexString(textMessage.hashCode()))) {
                                if (connection != null) {
                                    try {
                                        ((Connection) connection).close();
                                    } catch (IOException e) {
                                        DebugFrame.getInstance().logIn("\u5173\u95ed\u5f02\u5e38\uff01");
                                    }
                                    connection = null;
                                }
                                DebugFrame.getInstance().logIn("\u53d1\u9001\u77ed\u4fe1\u5931\u8d25\uff01");
                                return;
                            }
                            DebugFrame.getInstance().logIn("\u53d1\u9001\u77ed\u6210\u529f");
                            if (connection != null) {
                                try {
                                    ((Connection) connection).close();
                                } catch (IOException e) {
                                    DebugFrame.getInstance().logIn("\u5173\u95ed\u5f02\u5e38\uff01");
                                }
                                connection = null;
                            }
                        } catch (Exception e2) {
                            DebugFrame.getInstance().logIn("\u53d1\u9001\u77ed\u4fe1\u5f02\u5e38\uff01");
                            if (connection != null) {
                                try {
                                    ((Connection) connection).close();
                                } catch (IOException ex) {
                                }
                                connection = null;
                            }
                        }
                        NetManager.sms_send_message_send++;
                    } while (NetManager.sms_send_message_send <= sendCount);
                } catch (Exception es) {
                    DebugFrame.getInstance().logIn("\u53d1\u9001\u77ed\u4fe1\u5f02\u5e38\uff01");
                    es.printStackTrace();
                    return;
                } finally {
                    NetManager.sms_send_message_send = 0;
                }
                NetManager.sms_send_message_send = 0;
            }
        }).start();
    }

    public static String subStringBetween(final String str, final String open, final String close) {
        if (str == null || open == null || close == null) {
            return null;
        }
        final int start = str.indexOf(open);
        if (start == -1) {
            return null;
        }
        final int end = str.indexOf(close, start + open.length());
        if (end != -1) {
            return str.substring(start + open.length(), end);
        }
        return str.substring(start + open.length());
    }

    public void autoReConnect() {
        this.MC.reload();
        if (this.blnTryReConn) {
            this.stopNetManager(this.MC);
            if (Param.bln_socket_recont) {
                Param.bln_socket_recont = false;
                this.blnTimeOut = false;
                getInstance().setConnectionType((byte) 1, String.valueOf(Param.str_http_url) + Param.str_http_context);
                NetSend.getInstance().sendGetMap();
            } else if (Param.bln_http_recont) {
                Param.bln_http_recont = false;
                this.blnTimeOut = false;
                getInstance().setConnectionType((byte) 2, Param.bln_socket_url);
                NetSend.getInstance().sendGetMap();
            } else {
                this.setErr(this.MC);
            }
        }
        this.blnTryReConn = false;
    }

    public void clearThreadPool() {
        final int size = NetManager.threadPool.size();
        if (size >= 2) {
            NetManager.threadPool.setElementAt(new Boolean(false), size - 2);
        }
    }

}
