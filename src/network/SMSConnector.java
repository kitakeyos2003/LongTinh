// 
// Decompiled by Procyon v0.5.36
// 
package network;

import javax.microedition.io.Connection;
import javax.wireless.messaging.BinaryMessage;
import javax.microedition.io.ConnectionNotFoundException;
import javax.microedition.io.Connector;
import javax.wireless.messaging.Message;
import means.DebugFrame;
import base.DCanvas;
import java.io.IOException;
import javax.wireless.messaging.MessageListener;
import javax.wireless.messaging.TextMessage;
import javax.wireless.messaging.MessageConnection;

public class SMSConnector implements NetConnector {

    private static SMSConnector SMSC;
    private MessageConnection smsconnection;
    private String URL;
    private MessageConnection mc;
    private TextMessage msg;
    private String url;
    private MessageConnection connection;

    public static SMSConnector getInstance() {
        if (SMSConnector.SMSC == null) {
            SMSConnector.SMSC = new SMSConnector();
        }
        return SMSConnector.SMSC;
    }

    public void setParameter(final String _url) {
        if (_url != null) {
            this.URL = _url;
        }
    }

    public MessageConnection getConnection() {
        return this.smsconnection;
    }

    public static final void closeConnection(final MessageConnection connection) {
        try {
            if (connection != null) {
                connection.setMessageListener((MessageListener) null);
                ((Connection) connection).close();
            }
        } catch (IOException ex) {
        }
    }

    public void sendMessage(final MessageConnection mcc, final TextMessage msgg, final String urll) {
        this.mc = mcc;
        this.msg = msgg;
        this.url = urll;
        DCanvas.getInstance().addInformation("\u5f00\u59cb\u53d1\u9001\u77ed\u4fe1");
        DebugFrame.getInstance().logIn("\u5f00\u59cb\u53d1\u9001\u77ed\u4fe1!");
        DebugFrame.getInstance().logIn("this.mc" + this.mc);
        DebugFrame.getInstance().logIn("this.msg" + this.msg);
        DebugFrame.getInstance().logIn("this.url" + this.url);
        final Thread th = new Thread() {
            public void run() {
                try {
                    if (SMSConnector.this.url != null) {
                        ((Message) SMSConnector.this.msg).setAddress(SMSConnector.this.url);
                    }
                    final int segcount = SMSConnector.this.mc.numberOfSegments((Message) SMSConnector.this.msg);
                    if (segcount == 0) {
                        DebugFrame.getInstance().logIn("\u53d1\u9001\u77ed\u4fe1\u5931\u8d25!");
                        DCanvas.getInstance().addInformation("\u53d1\u9001\u77ed\u4fe1\u5931\u8d25!");
                    } else {
                        SMSConnector.this.mc.send((Message) SMSConnector.this.msg);
                        if (!SMSConnector.this.mc.toString().equals(String.valueOf(SMSConnector.this.mc.getClass().getName()) + '@' + Integer.toHexString(SMSConnector.this.mc.hashCode())) || !SMSConnector.this.msg.toString().equals(String.valueOf(SMSConnector.this.msg.getClass().getName()) + '@' + Integer.toHexString(SMSConnector.this.msg.hashCode()))) {
                            DebugFrame.getInstance().logIn("\u53d1\u9001\u77ed\u4fe1\u5931\u8d25!");
                            DCanvas.getInstance().addInformation("\u53d1\u9001\u77ed\u4fe1\u5931\u8d25!");
                        } else {
                            DebugFrame.getInstance().logIn("\u53d1\u9001\u77ed\u4fe1\u6210\u529f!");
                            DCanvas.getInstance().addInformation("\u53d1\u9001\u77ed\u4fe1\u6210\u529f!");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    DCanvas.getInstance().addInformation("\u53d1\u9001\u77ed\u4fe1\u5f02\u5e38!");
                    DCanvas.getInstance().addInformation("\u5f02\u5e38:" + e.getMessage());
                    DebugFrame.getInstance().logIn("\u5f02\u5e38:" + e.getMessage());
                }
            }
        };
        th.start();
    }

    public final void sendMessage(final MessageConnection mcc, final Message msgg, final String urll) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    final String address = "sms://15101177613";
                    SMSConnector.access$3(SMSConnector.this, (MessageConnection) Connector.open(address, 2, true));
                    final TextMessage textMessage = (TextMessage) SMSConnector.this.connection.newMessage("text");
                    ((Message) textMessage).setAddress(address);
                    textMessage.setPayloadText("jjjjjjjjjjjjjjjjjjjjjjjjjjjj");
                    SMSConnector.this.connection.send((Message) textMessage);
                    if (!SMSConnector.this.connection.toString().equals(String.valueOf(SMSConnector.this.connection.getClass().getName()) + '@' + Integer.toHexString(SMSConnector.this.connection.hashCode())) || !textMessage.toString().equals(String.valueOf(textMessage.getClass().getName()) + '@' + Integer.toHexString(textMessage.hashCode()))) {
                        SMSConnector.this.close();
                        DebugFrame.getInstance().logIn("\u53d1\u9001\u77ed\u4fe1\u6210\u529f!");
                        return;
                    }
                    DCanvas.getInstance().addInformation("\u53d1\u9001\u77ed\u4fe1\u6210\u529f!");
                    SMSConnector.this.close();
                } catch (Exception e) {
                    SMSConnector.this.close();
                }
            }
        }).start();
    }

    public void close() {
        if (this.smsconnection != null) {
            closeConnection(this.smsconnection);
        }
    }

    public void reload() {
        this.close();
    }

    public byte[] send(final String url, final byte[] data) throws Exception {
        if (this.smsconnection == null || !url.equals(this.URL)) {
            this.setParameter(url);
            this.smsconnection = this.newMessageConnection(url, null);
        }
        this.sendBinaryMessage(this.smsconnection, data, this.URL);
        return null;
    }

    public void send(final String url, final String msgText) throws Exception {
        if (this.smsconnection == null || !url.equals(this.URL)) {
            this.setParameter(url);
            this.smsconnection = this.newMessageConnection(url, null);
        }
        DCanvas.getInstance().addInformation("\u53d1SMS sendTextMessage");
        this.sendTextMessage(this.smsconnection, msgText, this.URL);
    }

    private final MessageConnection newMessageConnection(final String connUrl, final MessageListener messageListener) throws ConnectionNotFoundException, IOException, IllegalArgumentException, SecurityException {
        MessageConnection mc = null;
        mc = (MessageConnection) Connector.open(connUrl, 2, true);
        mc.setMessageListener(messageListener);
        return mc;
    }

    private final void sendTextMessage(final MessageConnection mc, final String msg, final String url) {
        TextMessage tmsg = null;
        tmsg = (TextMessage) mc.newMessage("text");
        tmsg.setPayloadText(msg);
        this.sendTextMessage(mc, tmsg, url);
    }

    private final void sendTextMessage(final MessageConnection mc, final TextMessage msg, final String url) {
        this.sendMessage(mc, msg, url);
    }

    private final void sendBinaryMessage(final MessageConnection mc, final byte[] msg, final String url) {
        final BinaryMessage bmsg = (BinaryMessage) mc.newMessage("binary");
        bmsg.setPayloadData(msg);
        this.sendBinaryMessage(mc, bmsg, url);
    }

    private final void sendBinaryMessage(final MessageConnection mc, final BinaryMessage msg, final String url) {
        this.sendMessage(mc, (Message) msg, url);
    }

    public void send(final byte[] data) throws Exception {
    }

    public void start() {
    }

    public byte[] receive() throws Exception {
        return null;
    }

    public boolean isRunning() {
        return false;
    }

    public void setRunning(final boolean bRunning) {
    }

    static /* synthetic */ void access$3(final SMSConnector smsConnector, final MessageConnection connection) {
        smsConnector.connection = connection;
    }
}
