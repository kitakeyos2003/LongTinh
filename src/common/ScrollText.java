// 
// Decompiled by Procyon v0.5.36
// 
package common;

import base.DCanvas;
import javax.microedition.lcdui.Graphics;
import means.StringManager;
import base.Macro;
import means.QSprite;

public class ScrollText {

    private byte lineIndex;
    private byte lineCount;
    private String[] textArr;
    private int startX;
    private int startY;
    private byte anchor;
    private int breadWidth;
    private int breadHeight;
    public static QSprite arrowhead;
    public static byte arrowhead_width;
    public static byte arrowhead_height;
    private boolean TextScroll;
    private int fontcolour;

    static {
        ScrollText.arrowhead_width = 15;
        ScrollText.arrowhead_height = 10;
    }

    public ScrollText(final String text, final int x, final int y, final int w, final int h, final int colour, final byte hor) {
        this.startX = x;
        this.startY = y;
        this.breadWidth = w;
        this.breadHeight = h;
        this.anchor = hor;
        this.fontcolour = colour;
        if (this.anchor == 1) {
            this.textArr = StringManager.wenZi(text, w - (Macro.FONTW << 2));
        } else {
            this.textArr = StringManager.wenZi(text, w - (Macro.FONTW << 1));
        }
        this.TextScroll = false;
    }

    public void setText(final String[] text) {
        this.textArr = text;
        this.TextScroll = false;
    }

    public void drawBread(final Graphics g) {
        final int lineDistance = Macro.FONTHEIGHT;
        this.lineCount = (byte) (this.breadHeight / lineDistance - 1);
        int top = this.lineIndex;
        if (this.textArr == null) {
            return;
        }
        if (top + this.lineCount > this.textArr.length) {
            top = this.textArr.length - this.lineCount;
        }
        if (top < 0) {
            top = 0;
        }
        for (int i = 0; i <= this.lineCount && top + i < this.textArr.length; ++i) {
            if (this.anchor == 1) {
                DrawBase.drawString(this.textArr[top + i], this.startX + (this.breadWidth >> 1), this.startY + i * lineDistance, this.fontcolour, 17);
            } else {
                DrawBase.drawString(this.textArr[top + i], this.startX, this.startY + i * lineDistance, this.fontcolour, 20);
            }
        }
        if (this.textArr.length > this.lineCount) {
            this.TextScroll = true;
            this.drawBar(g, this.breadWidth + this.startX - 2, this.startY, ScrollText.arrowhead_width - 4, this.breadHeight - lineDistance, top, this.textArr.length - top - this.lineCount, this.lineCount);
        }
    }

    private void drawBar(final Graphics g, final int x, final int y, final int width, final int height, final int up, final int down, final int current) {
        final int selectsize = height * current / (up + down + current);
        ScrollText.arrowhead.drawAnimationFrame(g, 0, 0, x, y + (ScrollText.arrowhead_height >> 1));
        ScrollText.arrowhead.drawAnimationFrame(g, 0, 1, x, y + height);
        DrawBase.drawBox(x - (width >> 1), y + (ScrollText.arrowhead_height >> 1), ScrollText.arrowhead_width - 2, height - (ScrollText.arrowhead_height >> 1), new int[]{14716968, 12877088, 13876596}, true);
        final int bh = (height - (ScrollText.arrowhead_height >> 1) - selectsize) * up / (up + down);
        DrawBase.drawBox(x - (width >> 1), y + bh + (ScrollText.arrowhead_height >> 1), ScrollText.arrowhead_width - 4, selectsize, new int[]{12279846, 13876596, 16641976}, true);
    }

    public void keyUP() {
        if (this.lineIndex > 0) {
            --this.lineIndex;
        }
    }

    public void keyDown() {
        if (this.lineIndex < this.textArr.length - this.lineCount) {
            ++this.lineIndex;
        }
    }

    public void Point() {
        if (this.TextScroll) {
            final int Topx = this.breadWidth + this.startX - ScrollText.arrowhead_width;
            final int Topy = this.startY;
            final int areaW = ScrollText.arrowhead_width * 3;
            if (DCanvas.getInstance().IsPointerDown(Topx - ScrollText.arrowhead_width, Topy - ScrollText.arrowhead_height, areaW, (this.breadHeight >> 1) + ScrollText.arrowhead_height)) {
                this.keyUP();
            } else if (DCanvas.getInstance().IsPointerDown(Topx - ScrollText.arrowhead_width, Topy + (this.breadHeight >> 1) - ScrollText.arrowhead_height, areaW, (this.breadHeight >> 1) + ScrollText.arrowhead_height)) {
                this.keyDown();
            }
        }
    }
}
