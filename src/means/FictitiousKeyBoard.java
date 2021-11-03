// Decompiled with: CFR 0.151
// Class Version: 3
package means;

import base.Macro;
import common.GridTable;
import javax.microedition.lcdui.Graphics;
import means.ImageManager;
import means.QSprite;

public class FictitiousKeyBoard {
    public static FictitiousKeyBoard fkb = null;
    public int SetKeyNumber = 12;
    public int bytKeyFlash;
    public short shtKeyX;
    public short shtKeyY;
    public short shtKeyW;
    public short shtKeyH;
    public GridTable KeyBoardTab;
    private String rescource;
    private short TabRow = (short)3;
    private short TabList = (short)4;
    private short[] RowPercent;
    private short[] ListPercent;
    private QSprite KeyNumber;
    private short[] shortKeyDateArray;
    private short[] shortKeyFlashArray;
    public short[][] Key_PositionArray = new short[][]{{1, 1}, {1, 2}, {1, 3}, {1, 4}, {2, 1}, {2, 2}, {2, 3}, {2, 4}, {3, 1}, {3, 2}, {3, 3}, {3, 4}};

    public static FictitiousKeyBoard getInstance() {
        if (fkb == null) {
            new FictitiousKeyBoard();
        }
        return fkb;
    }

    public FictitiousKeyBoard() {
        fkb = this;
    }

    public void clear() {
        this.clearKeyBoard();
        fkb = null;
    }

    public void initKeyboard(short s) {
        this.shtKeyW = (short)30;
        this.shtKeyH = (short)23;
        this.rescource = "numbutton";
        this.KeyNumber = ImageManager.loadSpriteById(1, ImageManager.EncodespriteId(this.rescource, this.rescource), this.rescource, this.rescource, "ui");
        this.shtKeyY = (short)(s + 2);
        this.shtKeyX = (short)((Macro.SCREEN_WIDTH - 4 * this.shtKeyW - 8) / 2);
        short s2 = (short)(Macro.SCREEN_HEIGHT - s - 24);
        if (s2 < 75) {
            this.shtKeyY = (short)(s - 18 - 75 - 3);
        }
        this.shortKeyDateArray = new short[]{2, 4, 6, 20, 8, 10, 12, 0, 14, 16, 18, 22};
        this.shortKeyFlashArray = new short[]{3, 5, 7, 21, 9, 11, 13, 1, 15, 17, 19, 23};
        this.RowPercent = new short[]{33, 33, 33};
        this.ListPercent = new short[]{25, 25, 25, 25};
        this.KeyBoardTab = Macro.bytGameType == 1 ? new GridTable(this.shtKeyX, this.shtKeyY, (short)((this.shtKeyW + 2) * this.TabList), (short)((this.shtKeyH + 2) * this.TabRow), this.TabRow, this.TabList, this.RowPercent, this.ListPercent) : new GridTable(this.shtKeyX, this.shtKeyY, (short)((this.shtKeyW + 2) * this.TabList), (short)((this.shtKeyH + 4) * this.TabRow), this.TabRow, this.TabList, this.RowPercent, this.ListPercent);
        this.bytKeyFlash = -1;
    }

    public void drawKeyBoard(Graphics graphics) {
        int n = 0;
        for (int i = 0; i < 12; ++i) {
            this.KeyNumber.drawAnimationFrame(graphics, 24, 0, this.KeyBoardTab.getCell(this.Key_PositionArray[i][0], this.Key_PositionArray[i][1]).getCenterX(), this.KeyBoardTab.getCell(this.Key_PositionArray[i][0], this.Key_PositionArray[i][1]).getCenterY());
            this.KeyNumber.drawAnimationFrame(graphics, this.shortKeyDateArray[++n - 1], 0, this.KeyBoardTab.getCell(this.Key_PositionArray[i][0], this.Key_PositionArray[i][1]).getCenterX(), this.KeyBoardTab.getCell(this.Key_PositionArray[i][0], this.Key_PositionArray[i][1]).getCenterY());
        }
        if (this.bytKeyFlash > -1) {
            this.KeyNumber.drawAnimationFrame(graphics, this.shortKeyFlashArray[this.bytKeyFlash], 0, this.KeyBoardTab.getCell(this.Key_PositionArray[this.bytKeyFlash][0], this.Key_PositionArray[this.bytKeyFlash][1]).getCenterX(), this.KeyBoardTab.getCell(this.Key_PositionArray[this.bytKeyFlash][0], this.Key_PositionArray[this.bytKeyFlash][1]).getCenterY());
        }
        this.bytKeyFlash = -1;
    }

    private void clearKeyBoard() {
        this.KeyBoardTab = null;
        this.KeyNumber = null;
        this.Key_PositionArray = null;
        this.shortKeyDateArray = null;
        this.shortKeyFlashArray = null;
        this.RowPercent = null;
        this.ListPercent = null;
    }
}
