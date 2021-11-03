// 
// Decompiled by Procyon v0.5.36
// 
package network;

import javax.microedition.io.InputConnection;
import javax.microedition.io.OutputConnection;
import face.DialogUI;
import base.GameControl;
import face.LandUI;
import base.DCanvas;
import base.Macro;
import javax.microedition.io.Connector;
import javax.microedition.io.SocketConnection;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.OutputStream;

public class SocketConnector implements NetConnector {

    public boolean blnIsRun;
    private static SocketConnector SC;
    private String url;
    private volatile OutputStream os;
    private volatile DataInputStream dis;
    private byte bytSendNum;
    private static byte bytSendTimes;
    private Sender sender;
    private Reciever reciever;

    static {
        SocketConnector.bytSendTimes = 0;
    }

    public static SocketConnector getInstance() {
        if (SocketConnector.SC == null) {
            SocketConnector.SC = new SocketConnector();
        }
        return SocketConnector.SC;
    }

    public void setParameter(final String _url) {
        if (_url != null) {
            if (_url.startsWith("socket://")) {
                this.url = _url.substring("socket://".length(), _url.length());
            } else {
                this.url = _url;
            }
            this.url = "socket://" + this.url;
        }
    }

    public void send(final byte[] _sendData) throws Exception {
        this.os.write(_sendData);
        this.os.flush();
    }

    public void close() {
        try {
            if (this.os != null && this.dis != null) {
                this.os.close();
                this.dis.close();
                this.os = null;
                this.dis = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reload() {
        this.close();
    }

    public void start() {
        try {
            NetManager.getInstance().sendStartTime = System.currentTimeMillis();
            NetManager.getInstance().endStartTime = 0L;
            NetManager.getInstance().blnTryReConn = true;
            final SocketConnection Socket_C = (SocketConnection) Connector.open(this.url);
            NetManager.getInstance().endStartTime = System.currentTimeMillis();
            this.os = ((OutputConnection) Socket_C).openOutputStream();
            this.dis = ((InputConnection) Socket_C).openDataInputStream();
        } catch (Exception ex) {
        }
        this.sender = new Sender();
        this.reciever = new Reciever();
        this.bytSendNum = 0;
        this.sender.start();
        this.reciever.start();
    }

    public byte[] receive() {
        int length = 0;
        byte[] data = null;
        if (this.dis == null) {
            return null;
        }
        try {
            length = (this.dis.readShort() & 0xFFFF);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            data = new byte[length];
            this.dis.readFully(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public boolean isRunning() {
        return this.blnIsRun;
    }

    public void setRunning(final boolean bRunning) {
        this.blnIsRun = bRunning;
    }

    static /* synthetic */ void access$1(final SocketConnector socketConnector, final byte bytSendNum) {
        socketConnector.bytSendNum = bytSendNum;
    }

    class Sender extends Thread {

        public synchronized void run() {
            try {
                while (SocketConnector.this.blnIsRun) {
                    try {
                        final NetManager instance = NetManager.getInstance();
                        final short shtHeartTime = instance.shtHeartTime;
                        NetManager.getInstance();
                        instance.shtHeartTime = (short) (shtHeartTime + 70);
                        if (NetManager.getInstance().blnIsSend || NetManager.getInstance().blnSendErr) {
                            NetManager.getInstance().blnIsSend = false;
                            NetManager.getInstance().blnSendErr = false;
                            if (SocketConnector.this.bytSendNum == 0) {
                                NetManager.getInstance().saveSendData();
                            }
                            SocketConnector.this.send(NetManager.getInstance().bytSendData);
                            if (Macro.BLN_NET_DATA_COUNT) {
                                Macro.sendDataCount += NetManager.getInstance().bytSendData.length;
                            }
                            NetManager.getInstance().delSendData();
                            SocketConnector.access$1(SocketConnector.this, (byte) 0);
                        } else if (NetManager.getInstance().shtHeartTime >= NetManager.getInstance().shtHeartTimeMax && NetManager.getInstance().blnSendHeart) {
                            if (!NetManager.getInstance().blnPause) {
                                NetSend.getInstance().sendHeart();
                            } else {
                                NetManager.getInstance().sendHeart();
                            }
                            NetManager.getInstance().shtHeartTime = 0;
                        }
                        NetManager.getInstance();
                        Thread.sleep(70L);
                    } catch (Exception e) {
                        NetManager.getInstance().MC.reload();
                        NetManager.getInstance().blnIsSend = false;
                        if (NetManager.getInstance().blnOnceErr) {
                            if (NetManager.getInstance().blnTimeOut) {
                                NetManager.getInstance().autoReConnect();
                            } else {
                                NetManager.getInstance().setErr(SocketConnector.getInstance());
                            }
                        } else {
                            final SocketConnector this$0 = SocketConnector.this;
                            SocketConnector.access$1(this$0, (byte) (this$0.bytSendNum + 1));
                            if (SocketConnector.this.bytSendNum < 2) {
                                NetManager.getInstance().blnSendErr = true;
                            } else {
                                NetManager.getInstance().setErr(SocketConnector.getInstance());
                            }
                        }
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                SocketConnector.this.blnIsRun = false;
            }
        }
    }

    class Reciever extends Thread {

        public void run() {
            try {
                while (SocketConnector.this.blnIsRun) {
                    try {
                        if (SocketConnector.this.dis.available() > 0) {
                            final byte[] bytes = SocketConnector.this.receive();
                            if (Macro.BLN_NET_DATA_COUNT) {
                                Macro.recvDataCount += bytes.length;
                            }
                            if (bytes != null) {
                                NetParse.getInstance().addNetPacket(bytes);
                            } else if (NetManager.getInstance().blnOnceErr) {
                                NetManager.getInstance().setErr(SocketConnector.getInstance());
                            } else {
                                final NetManager instance = NetManager.getInstance();
                                ++instance.bytErrNum;
                                if (Macro.bytGameType != 1) {
                                    NetManager.getInstance().blnIsSend = false;
                                    NetManager.getInstance().blnSendHeart = false;
                                    DCanvas.getInstance().setNetLoad(false);
                                    if (!LandUI.getInstance().blnIsIn) {
                                        GameControl.getInstance().CreateState((byte) 7);
                                        DialogUI.getInstance().setDialog((byte) 7, "\u5df2\u4ece\u670d\u52a1\u5668\u65ad\u5f00(1001)", (byte) 2);
                                    } else {
                                        GameControl.getInstance().CreateState((byte) 7);
                                        DialogUI.getInstance().setDialog((byte) 7, "\u5df2\u4ece\u670d\u52a1\u5668\u65ad\u5f00(1002)", (byte) 2);
                                    }
                                } else if (NetManager.getInstance().bytErrNum > 10) {
                                    NetManager.getInstance().setErr(SocketConnector.getInstance());
                                }
                            }
                        }
                        NetManager.getInstance();
                        Thread.sleep(70L);
                    } catch (Exception e) {
                        NetManager.getInstance().MC.reload();
                        NetManager.getInstance().blnIsSend = false;
                        if (NetManager.getInstance().blnOnceErr) {
                            if (NetManager.getInstance().blnTimeOut) {
                                NetManager.getInstance().autoReConnect();
                            } else {
                                NetManager.getInstance().setErr(SocketConnector.getInstance());
                            }
                        } else {
                            final SocketConnector this$0 = SocketConnector.this;
                            SocketConnector.access$1(this$0, (byte) (this$0.bytSendNum + 1));
                            if (SocketConnector.this.bytSendNum < 2) {
                                NetManager.getInstance().blnSendErr = true;
                            } else {
                                NetManager.getInstance().setErr(SocketConnector.getInstance());
                            }
                        }
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                SocketConnector.this.blnIsRun = false;
            }
        }
    }
}
