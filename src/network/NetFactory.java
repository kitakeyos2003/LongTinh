// Decompiled with: Procyon 0.5.36
// Class Version: 1
package network;

public class NetFactory
{
    public static final byte HTTP_CONNTION = 1;
    public static final byte SOCKET_CONNTION = 2;
    public static final byte SMS_CONNTION = 3;
    
    public static NetConnector getConntion(final byte b, final String parameter) {
        NetConnector netConnector = null;
        switch (b) {
            case 1: {
                netConnector = HttpConntor.getInstance();
                netConnector.setParameter(parameter);
                break;
            }
            case 2: {
                netConnector = SocketConnector.getInstance();
                netConnector.setParameter(parameter);
                break;
            }
            case 3: {
                netConnector = SMSConnector.getInstance();
                netConnector.setParameter(parameter);
                break;
            }
        }
        return netConnector;
    }
}
