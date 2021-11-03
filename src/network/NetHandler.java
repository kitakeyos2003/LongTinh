// Decompiled with: Procyon 0.5.36
// Class Version: 1
package network;

import java.io.IOException;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import java.util.Vector;

public class NetHandler
{
    public static final byte HTTP = 1;
    public static final byte SOCKET = 2;
    private static final String TAIL = "dataend";
    private static final String ENCODE = "ISO-8859-1";
    private static final byte[] ascii;
    public static int MAX_PAK_NUM;
    public static int sessionId;
    public static int gameId;
    private static byte[] heartbeat;
    private static Vector mesgQueue;
    private static ByteArrayOutputStream baos;
    private static DataOutputStream dos;
    
    static {
        ascii = new byte[] { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70 };
        NetHandler.MAX_PAK_NUM = 3;
        NetHandler.sessionId = 1999;
        NetHandler.mesgQueue = new Vector(NetHandler.MAX_PAK_NUM, 1);
        NetHandler.baos = new ByteArrayOutputStream();
        NetHandler.dos = new DataOutputStream(NetHandler.baos);
    }
    
    public static void reNetHandler() {
        NetHandler.mesgQueue = new Vector(NetHandler.MAX_PAK_NUM, 1);
        NetHandler.baos = new ByteArrayOutputStream();
        NetHandler.dos = new DataOutputStream(NetHandler.baos);
    }
    
    public static void add(final byte[] array) {
        synchronized (NetHandler.mesgQueue) {
            short2Bytes((short)(array.length - 2), array, 0);
            NetHandler.mesgQueue.addElement(array);
        }
        // monitorexit(NetHandler.mesgQueue)
    }
    
    public static byte[] send(final byte b) throws Exception {
        if (b == 2) {
            return pack();
        }
        if (b == 1) {
            final byte[] pack = pack();
            final byte[] array = new byte[pack.length * 2];
            for (int i = 0; i < pack.length; ++i) {
                encode(array, pack, i);
            }
            return (String.valueOf(new String(array)) + "dataend").getBytes("ISO-8859-1");
        }
        throw new NullPointerException("wrong nettype value!");
    }
    
    private static byte[] pack() throws IOException {
        try {
            byte[] byteArray = null;
            synchronized (NetHandler.mesgQueue) {
                if (NetHandler.mesgQueue.size() == 0) {
                    if (NetHandler.heartbeat == null) {
                        NetHandler.dos.writeShort(10);
                        NetHandler.dos.writeByte(NetHandler.gameId);
                        NetHandler.dos.writeInt(NetHandler.sessionId);
                        NetHandler.dos.writeByte(1);
                        NetHandler.dos.writeShort(2);
                        NetHandler.dos.writeShort(2049);
                        NetHandler.baos.flush();
                        NetHandler.heartbeat = NetHandler.baos.toByteArray();
                    }
                    // monitorexit(NetHandler.mesgQueue)
                    return NetHandler.heartbeat;
                }
                NetHandler.dos.writeShort(0);
                NetHandler.dos.writeByte(NetHandler.gameId);
                NetHandler.dos.writeInt(NetHandler.sessionId);
                final int min = Math.min(NetHandler.MAX_PAK_NUM, NetHandler.mesgQueue.size());
                NetHandler.dos.writeByte(min);
                for (int i = 0; i < min; ++i) {
                    byteArray = (byte[]) NetHandler.mesgQueue.firstElement();
                    NetHandler.dos.write(byteArray);
                    NetHandler.mesgQueue.removeElementAt(0);
                }
                NetHandler.baos.flush();
                byteArray = NetHandler.baos.toByteArray();
                short2Bytes((short)(byteArray.length - 2), byteArray, 0);
            }
            // monitorexit(NetHandler.mesgQueue)
            return byteArray;
        }
        finally {
            NetHandler.baos.reset();
        }
    }
    
    private static void short2Bytes(final short n, final byte[] array, final int n2) {
        if (array == null) {
            throw new NullPointerException("output array is null");
        }
        if (n2 + 2 > array.length) {
            throw new IndexOutOfBoundsException("offset or size of output array is not correct");
        }
        array[n2] = (byte)((n & 0xFF00) >> 8);
        array[n2 + 1] = (byte)(n & 0xFF);
    }
    
    private static void encode(final byte[] array, final byte[] array2, final int n) {
        int n2 = array2[n];
        if (n2 < 0) {
            n2 += 256;
        }
        final int n3 = n2 / 16;
        final int n4 = n2 % 16;
        array[n * 2] = NetHandler.ascii[n3];
        array[n * 2 + 1] = NetHandler.ascii[n4];
    }
}
