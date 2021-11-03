// Decompiled with: Procyon 0.5.36
// Class Version: 1
package common;

import means.StringManager;
import base.Macro;
import javax.microedition.lcdui.Graphics;

public class PopupDialog
{
    private GridTable popupTable;
    private short POPUP_WIDTH;
    private short POPUP_HEIGHT;
    private short popupCol;
    private short popupRow;
    public static String[] infoContent;
    private static boolean blnShowColor;
    private int intTitleColor;
    private boolean blnShow;
    private short[] popupColPercent;
    private short[] popupRowPercent;
    public static short popupPositionX;
    public static short popupPositionY;
    
    public PopupDialog() {
        this.POPUP_WIDTH = 60;
        this.POPUP_HEIGHT = 60;
        this.popupCol = 3;
        this.popupRow = 3;
        this.intTitleColor = 97530;
        this.popupColPercent = new short[] { (short)(1000 / this.POPUP_WIDTH), (short)((this.POPUP_WIDTH - 20) * 100 / this.POPUP_WIDTH), (short)(1000 / this.POPUP_WIDTH) };
        this.popupRowPercent = new short[] { (short)(1000 / this.POPUP_HEIGHT), (short)((this.POPUP_HEIGHT - 20) * 100 / this.POPUP_HEIGHT), (short)(1000 / this.POPUP_HEIGHT) };
    }
    
    public void setShow(final boolean blnShow) {
        if (!(this.blnShow = blnShow)) {
            PopupDialog.infoContent = null;
        }
    }
    
    public boolean getIsShow() {
        return this.blnShow;
    }
    
    public void setTitleColor(final boolean b, final int intTitleColor) {
        if (b) {
            this.intTitleColor = intTitleColor;
        }
        else {
            this.intTitleColor = 0;
        }
    }
    
    public void drawPopupDialog(final Graphics graphics) {
        if (PopupDialog.infoContent != null && PopupDialog.infoContent.length != 0 && this.blnShow) {
            DrawBase.drawRoundRect(this.popupTable.getCell(2, 2).getCellX(), this.popupTable.getCell(2, 2).getCellY(), this.POPUP_WIDTH, this.POPUP_HEIGHT, 17, 17, 9263661);
            final short popupPositionX = PopupDialog.popupPositionX;
            final short popupPositionY = PopupDialog.popupPositionY;
            final int n = this.popupTable.getCell(2, 2).getCenterX() - (this.POPUP_WIDTH >> 2);
            final int n2 = this.popupTable.getCell(2, 2).getCenterY() + 1;
            final int n3 = this.popupTable.getCell(2, 2).getCellX() + (this.POPUP_WIDTH >> 1);
            final int n4 = this.popupTable.getCell(2, 2).getCenterY() + 1;
            DrawBase.fillTriangle(popupPositionX, popupPositionY, n, n2, n3, n4, 16314576);
            DrawBase.drawLine(popupPositionX, popupPositionY, n3, n4 - 1, 9263661);
            DrawBase.drawLine(popupPositionX, popupPositionY, n, n2 - 1, 9263661);
            DrawBase.fillRoundRect(this.popupTable.getCell(2, 2).getCellX() + 1, this.popupTable.getCell(2, 2).getCellY() + 1, this.POPUP_WIDTH - 1, this.POPUP_HEIGHT - 1, 17, 17, 16314576);
            if (PopupDialog.infoContent != null) {
                for (byte b = 0; b < PopupDialog.infoContent.length; ++b) {
                    int intTitleColor;
                    if (b == 0 && PopupDialog.blnShowColor) {
                        intTitleColor = this.intTitleColor;
                    }
                    else {
                        intTitleColor = 8142636;
                    }
                    if (PopupDialog.infoContent[b] != null) {
                        DrawBase.drawString(PopupDialog.infoContent[b], this.popupTable.getCell(2, 2).getCellX() + 6, this.popupTable.getCell(2, 2).getCellY() + 6 + Macro.FONTHEIGHT * b, intTitleColor, 20);
                    }
                }
            }
        }
    }
    
    public static void drawPopupRect(final Graphics graphics, String[] wenZi, final int n, final int n2, final int n3, final int n4, final int n5, final boolean b) {
        DrawBase.drawRoundRect(n2, n3, n4, n5, 17, 17, 9263661);
        if (b) {
            final int n6 = n2 - 10;
            final int n7 = n3 + (n5 >> 2);
            final int n8 = n7 - 5;
            final int n9 = n7 + 5;
            DrawBase.fillTriangle(n6, n7, n2 + 1, n8, n2 + 1, n9, 16314576);
            DrawBase.drawLine(n6, n7, n2, n9 - 1, 9263661);
            DrawBase.drawLine(n6, n7, n2, n8 - 1, 9263661);
        }
        DrawBase.fillRoundRect(n2 + 1, n3 + 1, n4 - 1, n5 - 1, 17, 17, 16314576);
        if (wenZi == null) {
            wenZi = StringManager.wenZi("Thời tiết hôm nay rất tốt, tất cả đều xuất hiện!", Macro.npcRectW - 12);
        }
        StringManager.drawWordMove(graphics, wenZi, n2 + 6, n3 + 3, n5 - 6, n, 20);
    }
    
    public void setPopupDialog(final String[] infoContent, final short popupPositionX, final short popupPositionY) {
        if (infoContent != null) {
            PopupDialog.blnShowColor = true;
            int n = 0;
            PopupDialog.popupPositionX = popupPositionX;
            PopupDialog.popupPositionY = popupPositionY;
            if (infoContent != null) {
                int n2 = 0;
                for (int i = 0; i < infoContent.length; ++i) {
                    if (infoContent[i] != null) {
                        if (i == 0) {
                            n2 = Macro.font.stringWidth(infoContent[i]);
                        }
                        if (n2 < Macro.font.stringWidth(infoContent[i])) {
                            n2 = Macro.font.stringWidth(infoContent[i]);
                        }
                    }
                }
                PopupDialog.infoContent = infoContent;
                this.POPUP_WIDTH = (short)(n2 + 12);
                this.POPUP_HEIGHT = (short)(12 + Macro.FONTHEIGHT * infoContent.length);
            }
            int n3;
            if (PopupDialog.popupPositionX + this.POPUP_WIDTH + 17 > Macro.SCREEN_WIDTH) {
                n3 = -(this.POPUP_WIDTH + 17);
                PopupDialog.popupPositionX -= 22;
            }
            else {
                n3 = -4;
            }
            if (PopupDialog.popupPositionY + this.POPUP_HEIGHT + 17 > Macro.SCREEN_HEIGHT) {
                n = -(this.POPUP_HEIGHT + 8) - 17;
            }
            else {
                final int n4 = -(this.POPUP_WIDTH >> 2);
                if (PopupDialog.popupPositionX + this.POPUP_WIDTH + 17 > Macro.SCREEN_WIDTH) {
                    n3 = -this.POPUP_WIDTH;
                }
                else {
                    n3 = -(this.POPUP_WIDTH >> 2);
                }
            }
            if (PopupDialog.popupPositionX + n3 <= 6) {
                n3 = -PopupDialog.popupPositionX;
            }
            this.popupTable = new GridTable((short)(PopupDialog.popupPositionX + n3), (short)(PopupDialog.popupPositionY + n), this.POPUP_WIDTH, this.POPUP_HEIGHT, this.popupRow, this.popupCol, this.popupRowPercent, this.popupColPercent);
        }
    }
    
    public void setPopupDialog(final String s, final int n, final int n2, final int n3, final int n4) {
        PopupDialog.blnShowColor = false;
        PopupDialog.popupPositionX = (short)n;
        PopupDialog.popupPositionY = (short)n2;
        this.POPUP_WIDTH = (short)n3;
        this.POPUP_HEIGHT = (short)n4;
        if (s != null) {
            PopupDialog.infoContent = StringManager.wenZi(s, this.POPUP_WIDTH - 6);
            this.POPUP_WIDTH = (short)n3;
            final short n5 = (short)(12 + Macro.FONTHEIGHT * PopupDialog.infoContent.length);
            this.POPUP_HEIGHT = (short)((n4 > n5) ? n4 : n5);
        }
        this.popupTable = new GridTable(PopupDialog.popupPositionX, PopupDialog.popupPositionY, this.POPUP_WIDTH, this.POPUP_HEIGHT, this.popupRow, this.popupCol, this.popupRowPercent, this.popupColPercent);
        PopupDialog.popupPositionY += this.POPUP_HEIGHT;
    }
}
