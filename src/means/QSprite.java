// Decompiled with: CFR 0.151
// Class Version: 3
package means;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import means.DebugFrame;
import means.ImageManager;
import means.QSpriteData;
import means.QSpritePng;
import model.ResPoolFactory;

public class QSprite {
    public static final byte ORIENTATION_NONE = 0;
    public static final byte ORIENTATION_FLIP_V = 1;
    public static final byte ORIENTATION_FLIP_H = 2;
    public static final byte ORIENTATION_FLIP_BOTH_H_V = 3;
    public String spriteId = "";
    public String spriteAnuId = "";
    public String spritePngId = "";
    public String spritePathId = "";
    public int spritePoolType = -1;
    private int m_currAnim = -1;
    private int m_currFrame;
    private int m_frameCount;
    private int m_loopOffset = -1;
    private int m_delayCount;
    private int m_framePoolPointer;
    private static final byte ELLIPSE_FLAG_NONE = 1;
    private static final byte ELLIPSE_FLAG_FILLED = 3;
    private static final byte LINE_FLAG = 5;
    private static final byte RECTANGLE_FLAG_NONE = 7;
    private static final byte RECTANGLE_FLAG_FILLED = 9;
    private static final byte ROUNDEDRECTANGLE_FLAG_NONE = 11;
    private static final byte ROUNDEDRECTANGLE_FLAG_FILLED = 13;
    public static int QS_VERSION_COMMON = 1;
    public static int QS_VERSION_SPLIT_PNG;
    public static final byte ROLE_PARTS_ANIMATION_LOOP_NONE = 0;
    public static final byte ROLE_PARTS_ANIMATION_LOOP_ONCE = 1;
    public static final byte ROLE_PARTS_ANIMATION_LOOP_FOREVER = -1;
    public static final long ROLE_PARTS_ANIMATION_DELAY_ZERO = 0L;
    public static int QS_FLAG;
    private QSpriteData m_data;
    private Image m_img;
    private int m_sprite_XOff;
    private int m_sprite_YOff;
    private byte m_spriteFlip;
    private boolean m_isPlaying = false;
    private boolean m_isStarted = false;
    private boolean m_isEnd = false;

    public QSprite(String string, String string2, String string3, String string4, int n) {
        this.spriteId = string;
        this.spriteAnuId = string2;
        this.spritePngId = string3;
        this.spritePathId = string4;
        this.spritePoolType = n;
        this.m_data = this.getData();
        this.m_img = this.getPng();
    }

    public void freeImageData() {
        if (this.m_data != null) {
            this.m_data.m_rawImageData = null;
            this.m_data.m_imageAlphaData = null;
            this.m_data.m_imageSize = null;
            this.m_data.m_palAlphaInfo = null;
            this.m_data.m_palData = null;
        }
    }

    public void freeData() {
        System.gc();
    }

    public QSpriteData getData() {
        return QSprite.getData(this.spritePoolType, this.spriteAnuId);
    }

    public static QSpriteData getData(int n, String string) {
        QSpriteData qSpriteData = (QSpriteData)ResPoolFactory.getInstance().getRes(n, string);
        if (qSpriteData == null && ImageManager.getInstance().isResNeedReLoad(string)) {
            boolean bl = ImageManager.loadSpriteDataById(n, string);
            qSpriteData = (QSpriteData)ResPoolFactory.getInstance().getRes(n, string);
        }
        return qSpriteData;
    }

    public Image getPng() {
        return QSprite.getPng(this.spritePoolType, this.spritePngId);
    }

    public static Image getPng(int n, String string) {
        Image image = null;
        QSpritePng qSpritePng = (QSpritePng)ResPoolFactory.getInstance().getRes(n, string);
        if (qSpritePng == null && ImageManager.getInstance().isResNeedReLoad(string)) {
            boolean bl = ImageManager.loadSpritePngById(n, string);
            qSpritePng = (QSpritePng)ResPoolFactory.getInstance().getRes(n, string);
        }
        if (qSpritePng != null) {
            image = qSpritePng.getImage();
        }
        return image;
    }

    public boolean setQSpriteData(String string, String string2, String string3, int n) {
        boolean bl = false;
        QSpriteData qSpriteData = QSprite.getData(n, string);
        Image image = QSprite.getPng(n, string2);
        if (qSpriteData != null && image != null) {
            this.spriteAnuId = string;
            this.spritePngId = string2;
            this.spritePathId = string3;
            this.spritePoolType = n;
            bl = true;
        }
        return bl;
    }

    public void setAnimation(int n) {
        this.m_currAnim = n;
        this.m_currFrame = 0;
        this.m_delayCount = 0;
        this.notifyStartOfAnimation();
    }

    public Image getImage(int n, int n2) {
        if (this.m_data != null) {
            return this.m_data.m_moduleImages[n][n2];
        }
        return null;
    }

    public int getAnimation() {
        return this.m_currAnim;
    }

    public int getFrameCount() {
        int n;
        int n2 = 0;
        if (this.m_currAnim >= 0 && this.m_data != null && (n = this.m_currAnim << 1) + 1 < this.m_data.m_animationTable.length) {
            n2 = this.m_data.m_animationTable[n + 1] - this.m_data.m_animationTable[n] + 1;
        }
        return n2;
    }

    public int getCurrentFrame() {
        return this.m_currFrame;
    }

    public void setFrame(int n) {
        if (this.m_data != null) {
            this.m_currFrame = n;
            this.m_delayCount = 0;
            int n2 = this.m_data.m_animationTable[this.m_currAnim << 1] + n;
            this.m_framePoolPointer = this.m_data.m_frameTable[n2 << 2];
        }
    }

    public void setLoopOffset(int n) {
        this.m_loopOffset = n;
    }

    public int getLoopOffset() {
        return this.m_loopOffset;
    }

    public int getAnimationCount() {
        if (this.m_data != null) {
            return this.m_data.m_animationTable.length >>> 1;
        }
        return 0;
    }

    public int getNumberOfCollisionRect() {
        if (this.m_data != null) {
            return this.m_data.getNumberOfCollisionRect(this.m_framePoolPointer);
        }
        return -1;
    }

    public void update() {
        this.m_data = this.getData();
        this.m_img = this.getPng();
        if (this.m_data == null || this.m_img == null || this.m_data.m_animationTable == null) {
            return;
        }
        if (!this.m_isStarted) {
            return;
        }
        try {
            int n = this.m_currAnim << 1;
            if (n < 0 || n >= this.m_data.m_animationTable.length) {
                return;
            }
            int n2 = this.m_data.m_animationTable[n] + this.m_currFrame;
            if (this.m_delayCount < this.m_data.m_frameTable[(n2 << 2) + 1]) {
                ++this.m_delayCount;
                return;
            }
            int n3 = this.m_currAnim << 1;
            if (n3 + 1 < this.m_data.m_animationTable.length) {
                this.m_frameCount = this.m_data.m_animationTable[n3 + 1] - this.m_data.m_animationTable[n3] + 1;
            }
            if (this.m_currFrame >= this.m_frameCount - 1) {
                if (this.m_loopOffset == 0) {
                    this.notifyEndOfAnimation();
                    return;
                }
                if (this.m_loopOffset == -1) {
                    this.m_currFrame = -1;
                } else if (this.m_loopOffset >= 1) {
                    --this.m_loopOffset;
                    this.m_currFrame = -1;
                }
            }
            this.setFrame(this.m_currFrame + 1);
            n2 = this.m_data.m_animationTable[this.m_currAnim << 1] + this.m_currFrame;
            short s = this.m_data.m_frameTable[(n2 << 2) + 2];
            short s2 = this.m_data.m_frameTable[(n2 << 2) + 3];
            ++this.m_delayCount;
        }
        catch (Exception exception) {
            exception.printStackTrace();
            System.out.println();
        }
    }

    public void drawAnimation(Graphics graphics, int n, int n2) {
        this.drawAnimationFrame(graphics, this.m_currAnim, this.m_currFrame, n, n2);
    }

    public void drawAnimationFrame(Graphics graphics, int n, int n2, int n3, int n4) {
        if (this.m_data != null && this.m_data.m_animationTable != null) {
            int n5 = n << 1;
            if (n5 >= this.m_data.m_animationTable.length - 1 || n5 < 0) {
                return;
            }
            int n6 = this.m_data.m_animationTable[n5] + n2;
            short s = this.m_data.m_frameTable[n6 << 2];
            short s2 = this.m_data.m_framePoolTableIndex[(s << 1) + 1];
            for (int i = this.m_data.m_framePoolTableIndex[s << 1]; i < s2; i += 4) {
                this.drawModule(graphics, i, n3, n4);
            }
        }
    }

    public void drawModule(Graphics graphics, int n, int n2, int n3) {
        if (this.m_data != null) {
            int n4 = graphics.getClipX();
            int n5 = graphics.getClipY();
            int n6 = graphics.getClipWidth();
            int n7 = graphics.getClipHeight();
            short s = this.m_data.m_framePoolTable[n++];
            short s2 = this.m_data.m_framePoolTable[n++];
            short s3 = this.m_data.m_framePoolTable[n++];
            byte by = (byte)this.m_data.m_framePoolTable[n++];
            this.drawClip(graphics, s2 + n2, s3 + n3, s, by);
            graphics.setClip(n4, n5, n6, n7);
        }
    }

    public void drawImage(Graphics graphics, int n, int n2, int n3, int n4) {
        if (this.m_data != null) {
            graphics.drawImage(this.m_data.m_moduleImages[n][0], n2, n3, n4);
        }
    }

    public void drawFrame(Graphics graphics, int n, int n2) {
        if (this.m_data != null) {
            int n3 = this.m_data.m_framePoolTableIndex[this.m_framePoolPointer << 1];
            short s = this.m_data.m_framePoolTableIndex[(this.m_framePoolPointer << 1) + 1];
            int n4 = graphics.getClipX();
            int n5 = graphics.getClipY();
            int n6 = graphics.getClipWidth();
            int n7 = graphics.getClipHeight();
            while (n3 < s) {
                short s2 = this.m_data.m_framePoolTable[n3++];
                short s3 = this.m_data.m_framePoolTable[n3++];
                short s4 = this.m_data.m_framePoolTable[n3++];
                byte by = (byte)this.m_data.m_framePoolTable[n3++];
                this.drawClip(graphics, s3 + n, s4 + n2, s2, by);
                graphics.setClip(n4, n5, n6, n7);
            }
        }
    }

    protected void drawClip(Graphics graphics, int n, int n2, int n3, byte by) {
        if (this.m_data != null) {
            if ((by & 1) == 0) {
                byte by2 = (byte)(by & 7);
                if ((by2 = (byte)(by2 >> 1)) == 2) {
                    by2 = 1;
                } else if (by2 == 1) {
                    by2 = 2;
                }
                this.drawImageClip(graphics, n, n2, n3, by2);
            } else if (by == 1 || by == 3) {
                int n4 = n3 * 5;
                this.drawEllipseClip(graphics, n, n2, this.m_data.m_ellipseClipPool[n4], this.m_data.m_ellipseClipPool[n4 + 1], this.m_data.m_ellipseClipPool[n4 + 2], this.m_data.m_ellipseClipPool[n4 + 3], this.m_data.m_ellipseClipPool[n4 + 4], by == 3);
            } else if (by == 5) {
                int n5 = n3 * 3;
                this.drawLineClip(graphics, n, n2, this.m_data.m_lineClipPool[n5], this.m_data.m_lineClipPool[n5 + 1], this.m_data.m_lineClipPool[n5 + 2]);
            } else if (by == 7 || by == 9) {
                int n6 = n3 * 3;
                this.drawRectangleClip(graphics, n, n2, this.m_data.m_rectangleClipPool[n6], this.m_data.m_rectangleClipPool[n6 + 1], this.m_data.m_rectangleClipPool[n6 + 2], by == 9);
            } else if (by == 11 || by == 13) {
                int n7 = n3 * 5;
                this.drawRoundedRectangleClip(graphics, n, n2, this.m_data.m_roundedRectangleClipPool[n7], this.m_data.m_roundedRectangleClipPool[n7 + 1], this.m_data.m_roundedRectangleClipPool[n7 + 2], this.m_data.m_roundedRectangleClipPool[n7 + 3], this.m_data.m_roundedRectangleClipPool[n7 + 4], by == 13);
            }
        }
    }

    protected void drawImageClip(Graphics graphics, int n, int n2, int n3, byte by) {
        if (this.m_data != null && this.m_img != null) {
            int n4 = n3 * 4;
            short s = this.m_data.m_imageClipPool[n4++];
            short s2 = this.m_data.m_imageClipPool[n4++];
            short s3 = this.m_data.m_imageClipPool[n4++];
            short s4 = this.m_data.m_imageClipPool[n4++];
            byte by2 = this.getSpriteFlip();
            by = by == by2 ? (byte)0 : (by == 0 || by2 == 0 ? (byte)(by + by2) : (byte)(by ^ by2));
            int n5 = n + this.getSpriteDrawX();
            int n6 = n2 + this.getSpriteDrawY();
            graphics.clipRect(n5, n6, s3, s4);
            if (by == 0) {
                graphics.drawImage(this.m_img, n5 - s, n6 - s2, 0);
            } else if (s + s3 <= this.m_img.getWidth() && s2 + s4 <= this.m_img.getHeight()) {
                graphics.drawRegion(this.m_img, s, s2, s3, s4, by, n5, n6, 0);
            }
        }
    }

    protected void drawEllipseClip(Graphics graphics, int n, int n2, int n3, int n4, int n5, int n6, int n7, boolean bl) {
        byte by = this.getSpriteFlip();
        if (by == 1) {
            n = -n - n3;
        } else if (by == 2) {
            n2 = -n2 - n4;
        }
        int n8 = n + this.getSpriteDrawX();
        int n9 = n2 + this.getSpriteDrawY();
        graphics.setColor(n7);
        if (bl) {
            graphics.fillArc(n8, n9, n3, n4, n5, n6);
        } else {
            graphics.drawArc(n8, n9, n3, n4, n5, n6);
        }
    }

    protected void drawLineClip(Graphics graphics, int n, int n2, int n3, int n4, int n5) {
        byte by = this.getSpriteFlip();
        if (by == 1) {
            n = -n;
            n3 = -n3;
        } else if (by == 2) {
            n2 = -n2;
            n4 = -n4;
        }
        int n6 = n + this.getSpriteDrawX();
        int n7 = n3 + this.getSpriteDrawX();
        int n8 = n2 + this.getSpriteDrawY();
        int n9 = n4 + this.getSpriteDrawY();
        graphics.setColor(n5);
        graphics.drawLine(n6, n8, n7, n9);
    }

    protected void drawRectangleClip(Graphics graphics, int n, int n2, int n3, int n4, int n5, boolean bl) {
        graphics.setColor(n5);
        byte by = this.getSpriteFlip();
        if (by == 1) {
            n = -n - n3;
        } else if (by == 2) {
            n2 = -n2 - n4;
        }
        int n6 = n + this.getSpriteDrawX();
        int n7 = n2 + this.getSpriteDrawY();
        if (bl) {
            graphics.fillRect(n6, n7, n3, n4);
        } else {
            graphics.drawRect(n6, n7, n3, n4);
        }
    }

    protected void drawRoundedRectangleClip(Graphics graphics, int n, int n2, int n3, int n4, int n5, int n6, int n7, boolean bl) {
        byte by = this.getSpriteFlip();
        if (by == 1) {
            n = -n - n3;
        } else if (by == 2) {
            n2 = -n2 - n4;
        }
        int n8 = n + this.getSpriteDrawX();
        int n9 = n2 + this.getSpriteDrawY();
        graphics.setColor(n7);
        if (bl) {
            graphics.fillRoundRect(n8, n9, n3, n4, n5, n6);
        } else {
            graphics.drawRoundRect(n8, n9, n3, n4, n5, n6);
        }
    }

    public int[] getCollisionRect(int n) {
        if (this.m_data != null) {
            return this.m_data.getCollisionRect(n, this.m_framePoolPointer);
        }
        return null;
    }

    public void setSpriteId(String string) {
        this.spriteId = string;
    }

    public void setSpriteXOff(int n) {
        this.m_sprite_XOff = n;
    }

    public void setSpriteYOff(int n) {
        this.m_sprite_YOff = n;
    }

    public void setSpriteOff(int n, int n2) {
        this.setSpriteXOff(n);
        this.setSpriteYOff(n2);
    }

    public void notifyStartOfAnimation() {
        this.m_isStarted = true;
        this.m_isPlaying = true;
        this.m_isEnd = false;
        if (this.m_loopOffset >= 1) {
            --this.m_loopOffset;
        } else if (this.m_loopOffset < -1) {
            DebugFrame.getInstance().logIn("Error! QSprite loop 数量小于1 或不等于-1");
        }
    }

    public void notifyEndOfAnimation() {
        this.m_isEnd = true;
        this.m_isPlaying = false;
        this.m_isStarted = false;
    }

    public boolean isPlaying() {
        return this.m_isStarted && this.m_isPlaying && !this.m_isEnd;
    }

    public boolean isPlayDone() {
        return !this.m_isStarted && !this.m_isPlaying && this.m_isEnd;
    }

    private void setSpriteFlip(byte by) {
        this.m_spriteFlip = by;
    }

    public byte getSpriteFlip() {
        return this.m_spriteFlip;
    }

    public int getSpriteDrawX() {
        return this.m_sprite_XOff;
    }

    public int getSpriteDrawY() {
        return this.m_sprite_YOff;
    }

    public void updateSpritePositionBy(int n, int n2) {
        this.m_sprite_XOff += n;
        this.m_sprite_YOff += n2;
    }

    static {
        QS_FLAG = QS_VERSION_SPLIT_PNG = 2;
    }
}
