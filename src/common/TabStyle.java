// 
// Decompiled by Procyon v0.5.36
// 
package common;

import base.Param;
import base.DCanvas;
import javax.microedition.lcdui.Graphics;
import means.DebugFrame;
import means.ImageManager;
import base.Macro;
import means.QSprite;

public class TabStyle {

    public GridTable tabLayoutGlobl;
    public int characterX;
    public int characterY;
    public int characterCenterX;
    public int characterCenterY;
    public int characterWidth;
    public int characterHeight;
    public int GettabBottomY;
    public QSprite[] sprPaging;
    private short tabTopX;
    private short tabTopY;
    private short tabTopWidth;
    private short tabTopHeight;
    private String[] strtabname;
    private byte tabCurrentIndex;
    private boolean tabCurrentIndexChanged;
    private short tabrow;
    private short tablist;
    private int tabBorderColour;
    private int tabBackgroundColour;
    private int tabchoosefontColour;
    private int tabchoosefontdefoutColour;
    private int[] fontwidtharray;
    private int[] cellwidtharray;
    private int getStringArrayLength;
    private int fontoff;
    private int tabspace;
    private int spaceWidth;
    private int TriangleWidth;
    private int TriangleTopHeight;
    private int ChooseFrameChange;
    private String tabname;
    private short default_row_count;
    private int tabindex;
    private int replaceindex;
    private boolean ShowTriangle;
    private int index;
    private short[] screenPercentList;
    public boolean TabFocuse;
    public short[] tabBindingFrameRect;
    public short[] tabRect;

    public TabStyle() {
        this.sprPaging = null;
        this.tabCurrentIndexChanged = false;
        this.tabBorderColour = 15768897;
        this.tabBackgroundColour = 16774566;
        this.tabchoosefontColour = 16777215;
        this.tabchoosefontdefoutColour = 9263661;
        this.fontoff = 8;
        this.tabspace = 6;
        this.spaceWidth = 5;
        this.TriangleWidth = 9;
        this.TriangleTopHeight = 7;
        this.ChooseFrameChange = 3;
        this.tabname = "tab";
        this.TabFocuse = false;
        this.tabBindingFrameRect = new short[4];
        this.tabRect = new short[4];
    }

    public void initTabStyle(final short x, final short y, final short width, final short height, final String[] tabString, final byte currentindex) {
        this.initTabStyle(x, y, width, height, tabString, currentindex, 0, 0);
    }

    public void initTabStyle(final short x, final short y, final short width, final short height, final String[] tabString, final byte currentindex, final int frameW, final int frameH) {
        this.TabFocuse = false;
        this.getStringArrayLength = tabString.length;
        this.tabTopX = x;
        this.tabTopY = y;
        this.tabTopWidth = width;
        this.tabTopHeight = height;
        this.strtabname = tabString;
        this.fontwidtharray = new int[this.getStringArrayLength];
        for (int i = 0; i < this.getStringArrayLength; ++i) {
            this.fontwidtharray[i] = Macro.font.stringWidth(this.strtabname[i]);
        }
        final int tempMaxWidth = Common.Max(this.fontwidtharray) + this.fontoff + this.tabspace;
        this.default_row_count = (short) ((this.tabTopWidth - (this.TriangleWidth << 1)) / tempMaxWidth);
        final int tempRealWidth = (short) ((this.tabTopWidth - (this.TriangleWidth << 1)) / this.default_row_count);
        final int tempLeftVal = (short) ((this.tabTopWidth - (this.TriangleWidth << 1)) % this.default_row_count);
        if (tempLeftVal != 0) {
            this.TriangleWidth += tempLeftVal >> 1;
        }
        this.cellwidtharray = new int[this.default_row_count];
        this.screenPercentList = new short[this.default_row_count];
        for (int j = 0; j < this.default_row_count; ++j) {
            this.cellwidtharray[j] = tempRealWidth;
        }
        this.tabCurrentIndex = currentindex;
        for (int j = 0; j < this.default_row_count; ++j) {
            this.screenPercentList[j] = (short) (this.cellwidtharray[j] * 100 / (this.tabTopWidth - (this.TriangleWidth << 1)));
        }
        this.tabrow = 1;
        this.tablist = this.default_row_count;
        this.tabLayoutGlobl = new GridTable((short) (this.tabTopX + this.TriangleWidth), this.tabTopY, (short) (this.tabTopWidth - (this.TriangleWidth << 1)), this.tabTopHeight, this.tabrow, this.tablist, new short[]{100}, this.screenPercentList);
        this.characterX = this.tabLayoutGlobl.getCell(1, 2).getCellX() - this.tabLayoutGlobl.getCell(1, 1).getCellX();
        this.characterY = this.tabLayoutGlobl.getCell(1, 1).getCellY();
        this.characterCenterX = this.tabLayoutGlobl.getCell(1, 2).getCenterX() - this.tabLayoutGlobl.getCell(1, 1).getCenterX();
        this.characterCenterY = this.tabLayoutGlobl.getCell(1, 1).getCenterY();
        this.characterWidth = this.tabLayoutGlobl.getCell(1, 1).getCellW();
        this.characterHeight = this.tabLayoutGlobl.getCell(1, 1).getCellH();
        this.GettabBottomY = this.characterY + this.characterHeight;
        if (this.sprPaging == null) {
            this.sprPaging = new QSprite[7];
            for (int j = 0; j < this.sprPaging.length; ++j) {
                this.sprPaging[j] = ImageManager.loadSpriteById(1, ImageManager.EncodespriteId(this.tabname, new StringBuffer(String.valueOf(j)).toString()), this.tabname, this.tabname, "ui");
            }
            if (this.sprPaging == null) {
                DebugFrame.getInstance().logIn("Error!! \u9875\u7b7e\u8f7d\u5165\u8d44\u6e90\u9519\u8bef\uff0c\u53ef\u80fd\u662f\u9875\u7b7e\u56fe\u7247\u4e0d\u5339\u914d\uff01");
            }
        }
        this.tabBindingFrameRect[0] = (short) (this.tabTopX + 0);
        this.tabBindingFrameRect[1] = (short) (this.GettabBottomY + 0);
        this.tabBindingFrameRect[2] = (short) (this.tabTopWidth - 0);
        this.tabBindingFrameRect[3] = (short) (frameH - 0);
        this.tabRect[0] = (short) (this.tabTopX + 0);
        this.tabRect[1] = (short) (y + 0);
        this.tabRect[2] = (short) (this.tabTopWidth - 0);
        this.tabRect[3] = (short) (height - 0);
    }

    public void resetTabStyle(final byte currentindex) {
        this.tabCurrentIndex = currentindex;
    }

    public void drawTabStyle(final Graphics g) {
        DrawBase.drawBox(this.tabTopX, this.tabTopY, this.tabTopWidth, this.tabTopHeight, new int[]{this.tabBorderColour, this.tabBackgroundColour}, true);
        this.replaceindex = this.tabCurrentIndex - this.tabindex;
        for (int i = 0; i < Math.min(this.default_row_count, this.getStringArrayLength); ++i) {
            if (this.replaceindex == i) {
                if (this.sprPaging != null) {
                    if (this.sprPaging[0] != null) {
                        this.sprPaging[0].drawAnimationFrame(g, 0, 0, this.tabLayoutGlobl.getCell(this.tabrow, 1).getCenterX() - (this.cellwidtharray[i] - this.spaceWidth >> 1) + this.characterX * this.replaceindex, this.tabTopY + 1);
                    }
                    this.sprPaging[1].drawAnimationFrame(g, 1, 0, this.tabLayoutGlobl.getCell(this.tabrow, 1).getCenterX() - (this.cellwidtharray[i] - this.spaceWidth >> 1) + (this.cellwidtharray[i] - this.spaceWidth) + this.characterX * this.replaceindex, this.tabTopY + 1);
                    DrawBase.setClip(this.tabLayoutGlobl.getCell(this.tabrow, 1).getCenterX() - (this.cellwidtharray[i] - this.spaceWidth >> 1) + this.characterX * this.replaceindex, this.tabTopY - this.TriangleTopHeight, this.cellwidtharray[i] - this.spaceWidth, this.TriangleTopHeight);
                    this.sprPaging[2].drawAnimationFrame(g, 2, 0, this.tabLayoutGlobl.getCell(this.tabrow, 1).getCenterX() - (this.cellwidtharray[i] - this.spaceWidth >> 1) + this.characterX * this.replaceindex, this.tabTopY + 1);
                }
                DCanvas.getInstance().clearScreen();
            }
            DrawBase.drawBox(this.tabLayoutGlobl.getCell(this.tabrow, i + 1).getCenterX() - (this.cellwidtharray[i] - this.spaceWidth >> 1), this.tabLayoutGlobl.getCell(this.tabrow, i + 1).getCenterY() - (Macro.FONTHEIGHT + this.fontoff >> 1) + 1 - ((i == this.replaceindex) ? this.ChooseFrameChange : 0), this.cellwidtharray[i] - this.spaceWidth, Macro.FONTHEIGHT + this.fontoff, new int[]{13537098, 16761705}, true);
            DrawBase.drawBox(this.tabLayoutGlobl.getCell(this.tabrow, i + 1).getCenterX() - (this.cellwidtharray[i] - this.spaceWidth >> 1), this.tabLayoutGlobl.getCell(this.tabrow, i + 1).getCenterY() - (Macro.FONTHEIGHT + this.fontoff >> 1) - ((i == this.replaceindex) ? this.ChooseFrameChange : 0), this.cellwidtharray[i] - this.spaceWidth, Macro.FONTHEIGHT + this.fontoff, new int[]{(i == this.replaceindex) ? 14104126 : 3510965, (i == this.replaceindex) ? 16543607 : 5360636}, true);
            if (this.replaceindex == i && this.TabFocuse) {
                LayoutStyle.getInstance().drawChooseFrame(g, this.tabLayoutGlobl.getCell(this.tabrow, 1).getCenterX() - (this.cellwidtharray[i] - this.spaceWidth >> 1) + this.characterX * this.replaceindex, this.tabLayoutGlobl.getCell(this.tabrow, 1).getCenterY() - (Macro.FONTHEIGHT + this.fontoff >> 1) - this.ChooseFrameChange, this.cellwidtharray[i] - this.spaceWidth, Macro.FONTHEIGHT + this.fontoff);
            }
        }
        for (int i = 0; i < this.getStringArrayLength; ++i) {
            DrawBase.setClip(this.tabTopX + this.TriangleWidth, this.tabTopY, this.characterWidth * this.default_row_count, this.tabTopHeight);
            DrawBase.DrawString(this.strtabname[i], this.tabLayoutGlobl.getCell(this.tabrow, 1).getCellX() + this.characterX * i - this.characterX * this.index + 1, this.tabLayoutGlobl.getCell(this.tabrow, 1).getCellY() - ((i == this.tabCurrentIndex) ? this.ChooseFrameChange : 0), this.tabLayoutGlobl.getCell(this.tabrow, 1).getCenterX() + this.characterCenterX * i - this.characterX * this.index + 1, this.tabLayoutGlobl.getCell(this.tabrow, 1).getCenterY() - ((i == this.tabCurrentIndex) ? this.ChooseFrameChange : 0), this.tabLayoutGlobl.getCell(this.tabrow, 1).getCellW(), this.tabLayoutGlobl.getCell(this.tabrow, 1).getCellH(), (short) 0, (i == this.tabCurrentIndex) ? this.tabchoosefontColour : this.tabchoosefontdefoutColour);
            DCanvas.getInstance().clearScreen();
        }
        this.ShowTriangle = (this.getStringArrayLength > this.default_row_count);
        if (this.ShowTriangle) {
            if (this.tabCurrentIndex > 0) {
                DCanvas.getInstance().drawSpriteFram(g, this.sprPaging[4], -1, 4, this.tabLayoutGlobl.getCell(this.tabrow, 1).getCenterX() - (this.cellwidtharray[0] - this.spaceWidth >> 1) - this.TriangleTopHeight, this.tabLayoutGlobl.getCell(this.tabrow, 1).getCenterY());
            }
            if (this.tabCurrentIndex < this.getStringArrayLength - 1) {
                DCanvas.getInstance().drawSpriteFram(g, this.sprPaging[6], -1, 6, this.tabLayoutGlobl.getCell(this.tabrow, 1).getCellX() + (this.tabTopWidth - (this.TriangleWidth << 1)), this.tabLayoutGlobl.getCell(this.tabrow, 1).getCenterY());
            }
        }
    }

    public void cleanTabStyle() {
        this.tabLayoutGlobl = null;
        this.sprPaging = null;
        this.tabindex = 0;
        this.tabCurrentIndex = 0;
        this.index = 0;
        this.strtabname = null;
        this.fontwidtharray = null;
        this.cellwidtharray = null;
        this.screenPercentList = null;
        this.characterX = 0;
        this.characterY = 0;
        this.characterCenterX = 0;
        this.characterCenterY = 0;
        this.characterWidth = 0;
        this.characterHeight = 0;
        this.GettabBottomY = 0;
    }

    public void getTabCurrentIndexByPointer() {
        DCanvas.getInstance().blsTouchEvent = true;
        for (byte i = 0; i < Math.min(this.default_row_count, this.getStringArrayLength); ++i) {
            if (DCanvas.getInstance().IsPointerDown(this.tabLayoutGlobl.getCell(1, i + 1).getCellX(), this.tabLayoutGlobl.getCell(1, i + 1).getCellY(), this.tabLayoutGlobl.getCell(1, i + 1).getCellW(), this.tabLayoutGlobl.getCell(1, i + 1).getCellH())) {
                this.TabFocuse = true;
                if (Param.getInstance().personalPackage != null) {
                    Param.getInstance().personalPackage.setFocal(false);
                }
                this.tabCurrentIndex = (byte) (this.tabindex + i);
                this.tabCurrentIndexChanged = true;
                break;
            }
        }
        if (this.ShowTriangle) {
            if (DCanvas.getInstance().IsPointerDown(this.tabTopX - 6, this.tabTopY, this.tabTopX + this.TriangleWidth + 6, this.tabTopHeight)) {
                this.TabFocuse = true;
                this.getTabCurrentKeyLeft();
            } else if (DCanvas.getInstance().IsPointerDown(this.tabLayoutGlobl.getCell(this.tabrow, 1).getCellX() + (this.tabTopWidth - this.TriangleWidth * 2), this.tabTopY, this.TriangleWidth * 2, this.tabTopHeight)) {
                this.TabFocuse = true;
                this.getTabCurrentKeyRight();
            }
        }
    }

    public void getTabCurrentKeyLeft() {
        if (this.TabFocuse) {
            --this.tabCurrentIndex;
            if (this.tabCurrentIndex < 0) {
                this.tabCurrentIndex = 0;
            }
            if (this.tabCurrentIndex < this.index) {
                --this.index;
                if (this.index < 0) {
                    this.index = 0;
                }
            }
            if (this.tabCurrentIndex < this.tabindex) {
                --this.tabindex;
                if (this.tabindex < 0) {
                    this.tabindex = 0;
                }
            }
        }
    }

    public void getTabCurrentKeyRight() {
        if (this.TabFocuse) {
            ++this.tabCurrentIndex;
            if (this.tabCurrentIndex > this.strtabname.length - 1) {
                this.tabCurrentIndex = (byte) (this.strtabname.length - 1);
            }
            if (this.default_row_count <= this.tabCurrentIndex + 1) {
                ++this.index;
                if (this.index > this.tabCurrentIndex - this.default_row_count + 1) {
                    this.index = this.tabCurrentIndex - this.default_row_count + 1;
                }
            }
            if (this.tabCurrentIndex - this.tabindex + 1 > this.default_row_count) {
                ++this.tabindex;
            }
        }
    }

    public byte getTabCurrentIndex() {
        return this.tabCurrentIndex;
    }

    public boolean getTabCurrentIndexChanged() {
        return this.tabCurrentIndexChanged;
    }

    public void resetTabCurentIndexChanged() {
        this.tabCurrentIndexChanged = false;
    }

    public short[] getTabFrameRect() {
        return this.tabBindingFrameRect;
    }

    public short[] getTabRect() {
        return this.tabRect;
    }
}
