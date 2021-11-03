// 
// Decompiled by Procyon v0.5.36
// 
package network;

public interface NetConnector {

    void start();

    void setParameter(final String p0);

    void send(final byte[] p0) throws Exception;

    byte[] receive() throws Exception;

    void close();

    void reload();

    boolean isRunning();

    void setRunning(final boolean p0);
}
