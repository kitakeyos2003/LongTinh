// Decompiled with: Procyon 0.5.36
// Class Version: 1
package network;

import java.io.IOException;
import face.GameUI;
import base.Param;
import model.MenuObject;
import means.DebugFrame;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.Hashtable;

public class FakeServer
{
    private static FakeServer fakeServerInstance;
    static Hashtable htNpcData;
    
    static {
        FakeServer.htNpcData = new Hashtable(1);
    }
    
    private FakeServer() {
    }
    
    public static FakeServer getInstance() {
        if (FakeServer.fakeServerInstance == null) {
            FakeServer.fakeServerInstance = new FakeServer();
        }
        return FakeServer.fakeServerInstance;
    }
    
    public void npcMenu(final DataInputStream dataInputStream) throws IOException {
        try {
            final byte[] array = new byte[dataInputStream.available()];
            dataInputStream.read(array);
            final DataInputStream dataInputStream2 = new DataInputStream(new ByteArrayInputStream(array));
            final int int1 = dataInputStream2.readInt();
            final int int2 = dataInputStream2.readInt();
            final byte byte1 = dataInputStream2.readByte();
            final byte byte2 = dataInputStream2.readByte();
            DebugFrame.getInstance().logIn("模拟与NPC:" + int1 + "发生交互,弹出框类型:" + byte2 + "  层次：" + byte1 + "  编号：" + int2);
            this.pushNpcData(int1, int2, byte1, byte2, array);
            if (byte2 == 1) {
                final byte byte3 = dataInputStream2.readByte();
                if (byte3 != 0) {
                    final String[] array2 = new String[byte3];
                    final int[] array3 = new int[byte3];
                    final short[] array4 = new short[byte3];
                    for (byte b = 0; b < byte3; ++b) {
                        array3[b] = dataInputStream2.readInt();
                        array4[b] = dataInputStream2.readShort();
                        array2[b] = dataInputStream2.readUTF();
                        final byte byte4 = dataInputStream2.readByte();
                        if (byte4 > 0) {
                            for (byte b2 = 0; b2 < byte4; ++b2) {
                                NetParse.getInstance().readMenuListData(dataInputStream2, dataInputStream2.readByte(), null, true);
                            }
                        }
                        if (getInstance().isOperationTask(array3[b])) {
                            NetSend.getInstance().sendNpcOneOption(int1, byte1, array3[b], false);
                        }
                    }
                }
                if (Param.getInstance().oSelectNpc != null && GameUI.getInstance().checkNpcMenuUi(int1, int2)) {
                    NetSend.getInstance().sendNpcOptionFake(int1);
                }
            }
            else if (byte2 == 12 && Param.getInstance().oSelectNpc != null && GameUI.getInstance().checkNpcMenuUi(int1, int2)) {
                NetSend.getInstance().sendNpcOneOptionFake(int1, byte1, int2);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void pushNpcData(final int n, final int n2, final byte b, final byte b2, final byte[] array) {
        final Integer n3 = new Integer(n);
        final String npcDataEncode = this.getNpcDataEncode(n, n2, b);
        Hashtable hashtable;
        if (FakeServer.htNpcData.containsKey(n3)) {
            hashtable = (Hashtable) FakeServer.htNpcData.get(n3);
        }
        else {
            hashtable = new Hashtable(1);
            FakeServer.htNpcData.put(n3, hashtable);
        }
        hashtable.put(npcDataEncode, array);
    }
    
    private String getNpcDataEncode(final int n, final int n2, final byte b) {
        return new String(String.valueOf(n) + "/" + b + "/" + n2);
    }
    
    public DataInputStream getNpcMenuStream(final int n, final int n2, final byte b) {
        final Integer n3 = new Integer(n);
        final String npcDataEncode = this.getNpcDataEncode(n, n2, b);
        DataInputStream dataInputStream = null;
        if (FakeServer.htNpcData.containsKey(n3)) {
            final Hashtable hashtable = (Hashtable) FakeServer.htNpcData.get(n3);
            final byte[] array = null;
            if (hashtable.containsKey(npcDataEncode)) {
                dataInputStream = new DataInputStream(new ByteArrayInputStream((byte[]) hashtable.get(npcDataEncode)));
            }
            else {
                DebugFrame.getInstance().logIn("Error!!! 该npc任务哈希表中不存在该组数据!");
            }
        }
        else {
            DebugFrame.getInstance().logIn("Error!!! htNpcData中不存在该NPC数据!　NPC ID = " + n);
        }
        return dataInputStream;
    }
    
    public void removeNpcMenuData(final int n) {
        final Integer n2 = new Integer(n);
        DebugFrame.getInstance().logIn("与NPC交互完成，移除NPC数据!　NPC ID = " + n);
        if (FakeServer.htNpcData.containsKey(n2)) {
            FakeServer.htNpcData.remove(n2);
        }
    }
    
    public boolean isOperationTask(final int n) {
        return n / 100000 == 1;
    }
    
    public void clean() {
        FakeServer.htNpcData.clear();
    }
}
