// Decompiled with: CFR 0.151
// Class Version: 3
package means;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.lcdui.Image;
import means.ImageManager;
import means.QSpriteRes;

public class QSpriteData
extends QSpriteRes {
    private static final int BUILD_ALL_ANIMATION = -1;
    private static final int BUILD_ALL_PALETTE = -1;
    private static final byte COLLISIONRECT_FLAG = 15;
    public short[] m_animationTable;
    public short[] m_frameTable;
    public short[] m_framePoolTable;
    public short[] m_framePoolTableIndex;
    public short[] m_imageClipPool;
    public short[] m_imageIndexTable;
    public int[] m_ellipseClipPool;
    public int[] m_lineClipPool;
    public int[] m_rectangleClipPool;
    public int[] m_roundedRectangleClipPool;
    public short[] m_collisionClipPool;
    public static final int NOALPHA = 0;
    public static final int ALPHA = 1;
    public static final int IMAGE_PACK = 0;
    public static final int MODULE_PACK = 1;
    public static final int HAS_ANIM = 1;
    public byte[][][] m_rawImageData;
    public byte[][][] m_imageAlphaData;
    public short[][][] m_imageSize;
    public byte[][][] m_palAlphaInfo;
    public int[][][] m_palData;
    private int[] rect = new int[4];
    private int m_hasAnim;
    public Image[][] m_moduleImages;

    public QSpriteData() {
        this.res_type = (byte)2;
    }

    public static QSpriteData load(int n, String string) throws Exception {
        QSpriteData qSpriteData = new QSpriteData();
        try {
            qSpriteData.spriteId = string;
            qSpriteData.spritePoolType = n;
            String string2 = string;
            qSpriteData.m_hasAnim = 1;
            byte[] byArray = ImageManager.getResDataByteArray(string2);
            if (byArray == null) {
                return null;
            }
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byArray);
            DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
            if (qSpriteData.m_hasAnim == 1) {
                qSpriteData.LoadAnimation(dataInputStream, qSpriteData);
            }
            dataInputStream = null;
        }
        catch (Exception exception) {
            return null;
        }
        return qSpriteData;
    }

    private void LoadAnimation(DataInputStream dataInputStream, QSpriteData qSpriteData) {
        try {
            int n;
            int n2;
            int n3;
            int n4;
            int n5;
            int n6;
            int n7;
            int n8;
            int n9;
            int n10;
            dataInputStream.readShort();
            dataInputStream.readUTF();
            int n11 = dataInputStream.readByte();
            qSpriteData.m_animationTable = new short[n11 << 1];
            for (n10 = 0; n10 < n11; ++n10) {
                qSpriteData.m_animationTable[2 * n10] = dataInputStream.readShort();
                qSpriteData.m_animationTable[2 * n10 + 1] = dataInputStream.readShort();
            }
            n10 = dataInputStream.readShort();
            qSpriteData.m_frameTable = new short[n10 * 4];
            for (n9 = 0; n9 < n10; ++n9) {
                qSpriteData.m_frameTable[4 * n9] = dataInputStream.readShort();
                qSpriteData.m_frameTable[4 * n9 + 1] = dataInputStream.readByte();
                qSpriteData.m_frameTable[4 * n9 + 2] = dataInputStream.readShort();
                qSpriteData.m_frameTable[4 * n9 + 3] = dataInputStream.readShort();
            }
            n9 = dataInputStream.readShort();
            qSpriteData.m_framePoolTable = new short[n9];
            int n12 = dataInputStream.readShort();
            int n13 = 0;
            qSpriteData.m_framePoolTableIndex = new short[n12 << 1];
            for (n8 = 0; n8 < n12; ++n8) {
                qSpriteData.m_framePoolTableIndex[2 * n8] = (short) n13;
                n7 = dataInputStream.readShort();
                for (n6 = 0; n6 < n7; ++n6) {
                    short[] sArray = qSpriteData.m_framePoolTable;
                    n5 = n13;
                    n13 = (short)(n5 + 1);
                    sArray[n5] = dataInputStream.readShort();
                    short[] sArray2 = qSpriteData.m_framePoolTable;
                    int n14 = n13;
                    n13 = (short)(n14 + 1);
                    sArray2[n14] = dataInputStream.readShort();
                    short[] sArray3 = qSpriteData.m_framePoolTable;
                    int n15 = n13;
                    n13 = (short)(n15 + 1);
                    sArray3[n15] = dataInputStream.readShort();
                    short[] sArray4 = qSpriteData.m_framePoolTable;
                    int n16 = n13;
                    n13 = (short)(n16 + 1);
                    sArray4[n16] = dataInputStream.readByte();
                }
                qSpriteData.m_framePoolTableIndex[2 * n8 + 1] = (short)(n13 - 1);
            }
            n8 = dataInputStream.readShort();
            n7 = dataInputStream.readByte();
            qSpriteData.m_imageClipPool = new short[n8 << 2];
            n13 = 0;
            qSpriteData.m_imageIndexTable = new short[n7];
            n6 = 0;
            for (n4 = 0; n4 < n7; ++n4) {
                qSpriteData.m_imageIndexTable[n4] = (short) n6;
                n5 = dataInputStream.readShort();
                for (int i = 0; i < n5; ++i) {
                    short[] sArray = qSpriteData.m_imageClipPool;
                    int n17 = n13;
                    n13 = (short)(n17 + 1);
                    sArray[n17] = dataInputStream.readShort();
                    short[] sArray5 = qSpriteData.m_imageClipPool;
                    int n18 = n13;
                    n13 = (short)(n18 + 1);
                    sArray5[n18] = dataInputStream.readShort();
                    short[] sArray6 = qSpriteData.m_imageClipPool;
                    int n19 = n13;
                    n13 = (short)(n19 + 1);
                    sArray6[n19] = dataInputStream.readShort();
                    short[] sArray7 = qSpriteData.m_imageClipPool;
                    int n20 = n13;
                    n13 = (short)(n20 + 1);
                    sArray7[n20] = dataInputStream.readShort();
                }
                n6 = (short)(n6 + (short)n5);
            }
            n4 = dataInputStream.readShort();
            qSpriteData.m_ellipseClipPool = new int[n4 * 5];
            for (n5 = 0; n5 < n4; ++n5) {
                qSpriteData.m_ellipseClipPool[5 * n5] = dataInputStream.readShort();
                qSpriteData.m_ellipseClipPool[5 * n5 + 1] = dataInputStream.readShort();
                qSpriteData.m_ellipseClipPool[5 * n5 + 2] = dataInputStream.readShort();
                qSpriteData.m_ellipseClipPool[5 * n5 + 3] = dataInputStream.readShort();
                qSpriteData.m_ellipseClipPool[5 * n5 + 4] = dataInputStream.readInt();
            }
            n5 = dataInputStream.readShort();
            qSpriteData.m_lineClipPool = new int[n5 * 3];
            for (n3 = 0; n3 < n5; ++n3) {
                qSpriteData.m_lineClipPool[3 * n3] = dataInputStream.readShort();
                qSpriteData.m_lineClipPool[3 * n3 + 1] = dataInputStream.readShort();
                qSpriteData.m_lineClipPool[3 * n3 + 2] = dataInputStream.readInt();
            }
            n3 = dataInputStream.readShort();
            qSpriteData.m_rectangleClipPool = new int[n3 * 3];
            for (n2 = 0; n2 < n3; ++n2) {
                qSpriteData.m_rectangleClipPool[3 * n2] = dataInputStream.readShort();
                qSpriteData.m_rectangleClipPool[3 * n2 + 1] = dataInputStream.readShort();
                qSpriteData.m_rectangleClipPool[3 * n2 + 2] = dataInputStream.readInt();
            }
            n2 = dataInputStream.readShort();
            qSpriteData.m_roundedRectangleClipPool = new int[n2 * 5];
            for (n = 0; n < n2; ++n) {
                qSpriteData.m_roundedRectangleClipPool[5 * n] = dataInputStream.readShort();
                qSpriteData.m_roundedRectangleClipPool[5 * n + 1] = dataInputStream.readShort();
                qSpriteData.m_roundedRectangleClipPool[5 * n + 2] = dataInputStream.readShort();
                qSpriteData.m_roundedRectangleClipPool[5 * n + 3] = dataInputStream.readShort();
                qSpriteData.m_roundedRectangleClipPool[5 * n + 4] = dataInputStream.readInt();
            }
            n = dataInputStream.readShort();
            qSpriteData.m_collisionClipPool = new short[n << 1];
            for (int i = 0; i < n; ++i) {
                qSpriteData.m_collisionClipPool[2 * i] = dataInputStream.readShort();
                qSpriteData.m_collisionClipPool[2 * i + 1] = dataInputStream.readShort();
            }
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }

    public int[] getCollisionRect(int n, int n2) {
        int n3 = -1;
        int n4 = this.m_framePoolTableIndex[n2 << 1];
        short s = this.m_framePoolTableIndex[(n2 << 1) + 1];
        while (n4 < s) {
            int n5 = this.m_framePoolTable[n4++];
            int n6 = this.m_framePoolTable[n4++];
            int n7 = this.m_framePoolTable[n4++];
            if ((byte)this.m_framePoolTable[n4++] != 15 || ++n3 != n) continue;
            this.rect[2] = this.m_collisionClipPool[n5 <<= 1];
            this.rect[3] = this.m_collisionClipPool[n5 + 1];
            boolean bl = false;
            this.rect[0] = n6;
            this.rect[1] = n7;
            return this.rect;
        }
        return null;
    }

    public int getNumberOfCollisionRect(int n) {
        int n2 = 0;
        int n3 = this.m_framePoolTableIndex[n << 1];
        short s = this.m_framePoolTableIndex[(n << 1) + 1];
        while (n3 < s) {
            n3 += 3;
            if ((byte)this.m_framePoolTable[n3++] != 15) continue;
            ++n2;
        }
        return n2;
    }

    public boolean hasAnimation() {
        return this.m_hasAnim == 1;
    }
}
