// 
// Decompiled by Procyon v0.5.36
// 
package common;

import javax.microedition.lcdui.Graphics;

public class GridCell extends GridLayout {

    public short cell_x;
    public short cell_y;
    public short cell_w;
    public short cell_h;
    public short cell_center_x;
    public short cell_center_y;

    public GridCell(final short x, final short y, final short w, final short h) {
        this.cell_x = x;
        this.cell_y = y;
        this.cell_w = w;
        this.cell_h = h;
        this.cell_center_x = (short) (this.cell_x + (this.cell_w >> 1));
        this.cell_center_y = (short) (this.cell_y + (this.cell_h >> 1));
    }

    public short getCellX() {
        return this.cell_x;
    }

    public short getCellY() {
        return this.cell_y;
    }

    public short getCellW() {
        return this.cell_w;
    }

    public short getCellH() {
        return this.cell_h;
    }

    public short getCenterX() {
        return this.cell_center_x;
    }

    public short getCenterY() {
        return this.cell_center_y;
    }

    public short[] getCellFrame() {
        return new short[]{this.cell_x, this.cell_y, this.cell_w, this.cell_h};
    }

    public void drawString(final Graphics g, final String string, final int align) {
        g.setColor(Pram.FONT_FOREGROUND_COLOR);
        int strX = this.cell_x;
        int strY = this.cell_y;
        switch (align) {
            case 0: {
                strX = this.cell_center_x;
                strY = this.cell_y;
            }
            case 2: {
                strX = this.cell_x + (this.cell_w >> 1);
                strY = this.cell_y + this.cell_y - Pram.FONT_HEIGHT;
                break;
            }
            case 3: {
                strX = this.cell_x;
                strY = this.cell_y;
                break;
            }
            case 4: {
                strX = this.cell_x;
                strY = this.cell_center_y;
                break;
            }
        }
        g.drawString(string, strX + 3, strY, 0);
    }
}
