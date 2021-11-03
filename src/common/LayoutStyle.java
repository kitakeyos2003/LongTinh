// 
// Decompiled by Procyon v0.5.36
// 
package common;

import means.StringManager;
import base.Macro;
import means.ImageManager;
import javax.microedition.lcdui.Graphics;
import means.QSprite;
import javax.microedition.lcdui.Image;

public class LayoutStyle {

    public static LayoutStyle layoutStyle;
    private Image imgEar;
    private int earwidth;
    public int earheight;
    private int earoff;
    private int beforeoff;
    private int SelectBoxOutColour;
    private int SelectBoxFillColour;
    private QSprite sprFrame;
    private byte bytChooseFreamIndex;
    public String[] TitleFont;
    public String[] ExplainFont;
    private int[] FontTitleWidth;
    private int[] FontExplainWidth;
    private int GetTitleArrayLength;
    private int GetExplainArrayLength;
    private short[] screenPercentRow;
    private short[] screenPercentList;
    private int MaxHeight;
    private int TitleMaxWidth;
    private int ExplainMaxWidth;
    private GridTable FontFrameGlobl;
    private int fontx;
    private int fonty;
    public int FontFrameBottom;
    public int FontFramWidth;
    public int FontFramHeight;
    private int fontcolour;
    private int showFrameMinWidth;
    private int explainFramWidth;
    private short shtMainMenuTitle_offx;
    private short shtMainMenuOutsideBox_offx;
    private short shtMainMenuOutsideBox_offy;
    private short shtMainMenuOutsideBox_margin;
    private short shtMainMenuInsideBox_offx;
    private short shtMainMenuInsideBox_offy;
    private short shtMainTitle_h;
    private int[] outSideColor;
    private int[] insideColor;
    private int[] fontwidtharray;
    private int frameW;
    private int frameH;
    private String[] str;
    private short MaxShowWidth;

    static {
        LayoutStyle.layoutStyle = null;
    }

    public LayoutStyle() {
        this.earoff = 2;
        this.beforeoff = 2;
        this.SelectBoxOutColour = 12026667;
        this.SelectBoxFillColour = 16442771;
        this.sprFrame = null;
        this.fontcolour = 6896918;
        this.shtMainMenuTitle_offx = 14;
        this.shtMainMenuOutsideBox_offx = 9;
        this.shtMainMenuOutsideBox_offy = 15;
        this.shtMainMenuOutsideBox_margin = 1;
        this.shtMainMenuInsideBox_offx = 3;
        this.shtMainMenuInsideBox_offy = 3;
        this.shtMainTitle_h = 34;
        this.outSideColor = new int[]{6179098, 6179098, 14020057, 6179098, 12818766};
        this.insideColor = new int[]{14147274, 14147274};
        LayoutStyle.layoutStyle = this;
    }

    public static LayoutStyle getInstance() {
        if (LayoutStyle.layoutStyle == null) {
            new LayoutStyle();
        }
        return LayoutStyle.layoutStyle;
    }

    public void drawEarBox(final Graphics g, final String titlename, final int titlenameColour, final int x, final int y, final int w, final int h) {
        if (this.imgEar == null) {
            this.imgEar = ImageManager.CreateImage("ear", "ui");
        }
        this.earwidth = this.imgEar.getWidth();
        this.earheight = this.imgEar.getHeight();
        DrawBase.drawBox(x, y + this.earoff, w, this.earheight, new int[]{6690072, 6690072, 10887943, 16049801, 13538406, 15445647, 16641976}, true);
        DrawBase.drawRegion(this.imgEar, x - this.earwidth + 7, y, 0, 0, this.earwidth, this.earheight, 0, 0);
        DrawBase.drawRegion(this.imgEar, x + w - 7, y, 0, 0, this.earwidth, this.earheight, this.earoff, 0);
        DrawBase.drawString(titlename, x + (w >> 1), (y + this.earoff + this.earheight) / (this.earoff << 1), titlenameColour, 17);
    }

    public void cleanimgEar() {
        this.imgEar = null;
    }

    public void drawBeforeBackGround(final int x, final int y, final int w, final int h, final int[] colour) {
        this.drawBeforeBackGround(x, y, w, h, colour, false);
    }

    public void drawBeforeBackGround(final int x, final int y, final int w, final int h, final int[] colour, final boolean withTitleBg) {
        DrawBase.drawBox(x + 6, y, w - 12, h, new int[]{colour[0]}, false);
        DrawBase.drawBox(x + 6 + this.beforeoff, y + this.beforeoff, w - 12 - (this.beforeoff << 1), h - (this.beforeoff << 1), new int[]{colour[1]}, true);
        DrawBase.drawBox(x + 12, y + 6, w - 24, h - 12, new int[]{colour[2]}, true);
        if (withTitleBg) {
            DrawBase.drawBox(x + 12, y + 6, w - 24, 18, new int[]{15195324}, true);
        }
    }

    public void drawFullBackGround(final int backcolor) {
        DrawBase.drawBox(0, 0, Macro.SCREEN_WIDTH, Macro.SCREEN_HEIGHT, new int[]{7684391, 7684391, 16315622, 6635032, backcolor}, true);
    }

    public void drawSelectBox(final int x, final int y, final int w, final int h, final int[] selBoxColor, final boolean isfill) {
        DrawBase.drawBox(x, y, w, h, selBoxColor, isfill);
    }

    public void drawSelectBox(final int x, final int y, final int w, final int h) {
        this.drawSelectBox(x, y, w, h, new int[]{15768897, this.SelectBoxOutColour, this.SelectBoxFillColour}, true);
    }

    public void drawChooseFrame(final Graphics g, final int LeftTopx, final int LeftTopy, final int BoxWidth, final int BoxHeight) {
        if (this.sprFrame == null) {
            this.sprFrame = ImageManager.loadSpriteById(1, "ChooseFrame", "frame", "frame", "ui");
        }
        ++this.bytChooseFreamIndex;
        if (this.bytChooseFreamIndex > 4) {
            this.bytChooseFreamIndex = 0;
        }
        byte _bytindex = 0;
        if (this.bytChooseFreamIndex > 2) {
            _bytindex = 1;
        }
        this.sprFrame.drawAnimationFrame(g, 0, 0, LeftTopx - _bytindex - 1, LeftTopy - _bytindex - 1);
        this.sprFrame.drawAnimationFrame(g, 1, 0, LeftTopx - _bytindex - 1, LeftTopy + BoxHeight + _bytindex + 1);
        this.sprFrame.drawAnimationFrame(g, 2, 0, LeftTopx + BoxWidth + _bytindex + 1, LeftTopy - _bytindex - 1);
        this.sprFrame.drawAnimationFrame(g, 3, 0, LeftTopx + BoxWidth + _bytindex + 1, LeftTopy + BoxHeight + _bytindex + 1);
    }

    public void setFont(final String[] titlefont, final String[] explain, final int x, final int y) {
        this.GetTitleArrayLength = titlefont.length;
        this.GetExplainArrayLength = explain.length;
        this.TitleFont = titlefont;
        this.ExplainFont = explain;
        this.fontx = x;
        this.fonty = y;
        this.FontTitleWidth = new int[this.GetTitleArrayLength];
        this.FontExplainWidth = new int[this.GetExplainArrayLength];
        for (int i = 0; i < this.GetTitleArrayLength; ++i) {
            this.FontTitleWidth[i] = Macro.font.stringWidth(titlefont[i]);
        }
        for (int i = 0; i < this.GetExplainArrayLength; ++i) {
            this.FontExplainWidth[i] = Macro.font.stringWidth(explain[i]);
        }
        this.TitleMaxWidth = Common.Max(this.FontTitleWidth);
        this.ExplainMaxWidth = Common.Max(this.FontExplainWidth) + Macro.FONTW;
        this.screenPercentRow = new short[this.GetTitleArrayLength];
        this.screenPercentList = new short[2];
        this.MaxHeight = Macro.FONTHEIGHT + 2;
        for (int i = 0; i < this.GetTitleArrayLength; ++i) {
            this.screenPercentRow[i] = (short) (this.MaxHeight * 100 / (this.MaxHeight * this.GetTitleArrayLength));
        }
        this.showFrameMinWidth = Macro.SCREEN_WIDTH - (this.fontx + this.TitleMaxWidth);
        this.explainFramWidth = Math.min(this.showFrameMinWidth - 12, this.ExplainMaxWidth - 4);
        this.screenPercentList[0] = (short) (this.TitleMaxWidth * 100 / (this.TitleMaxWidth + this.explainFramWidth));
        this.screenPercentList[1] = (short) (this.explainFramWidth * 100 / (this.TitleMaxWidth + this.explainFramWidth));
        this.FontFrameGlobl = new GridTable((short) (this.fontx + 2), (short) this.fonty, (short) (this.TitleMaxWidth + this.explainFramWidth), (short) (this.MaxHeight * this.GetTitleArrayLength), (short) this.GetTitleArrayLength, (short) 2, this.screenPercentRow, this.screenPercentList);
        this.FontFrameBottom = this.fonty + this.MaxHeight * this.GetTitleArrayLength;
        this.FontFramWidth = this.TitleMaxWidth + this.explainFramWidth;
        this.FontFramHeight = this.MaxHeight * this.GetTitleArrayLength + 2;
    }

    public void drawFontFrame(final Graphics g) {
        DrawBase.drawBox(this.fontx, this.fonty - 1, this.TitleMaxWidth + this.explainFramWidth, this.MaxHeight * this.GetTitleArrayLength + 2, new int[]{12026667, 16442771}, true);
        for (int i = 1; i < this.GetTitleArrayLength + 1; ++i) {
            DrawBase.DrawString(this.TitleFont[i - 1], this.FontFrameGlobl.getCell(i, 1).getCellX(), this.FontFrameGlobl.getCell(i, 1).getCellY(), this.FontFrameGlobl.getCell(i, 1).getCenterX(), this.FontFrameGlobl.getCell(i, 1).getCenterY(), this.FontFrameGlobl.getCell(i, 1).getCellW(), this.FontFrameGlobl.getCell(i, 1).getCellH(), (short) 0, this.fontcolour);
            DrawBase.drawBox(this.FontFrameGlobl.getCell(i, 2).getCellX(), this.FontFrameGlobl.getCell(i, 2).getCellY() + 1, this.FontFrameGlobl.getCell(i, 2).getCellW() - 2, this.FontFrameGlobl.getCell(i, 2).getCellH() - 2, new int[]{14995858, 16642234}, true);
        }
        for (int i = 1; i < this.GetExplainArrayLength + 1; ++i) {
            StringManager.drawWordRightToLeft(this.ExplainFont[i - 1], this.FontFrameGlobl.getCell(i, 2).getCellX(), this.FontFrameGlobl.getCell(i, 2).getCellY() + 1, this.FontFrameGlobl.getCell(i, 2).getCellW() - 3, this.fontcolour, 0, true);
        }
    }

    public void drawMultiBox(final Graphics g, final int boxX, final int boxY, final int boxW, final int boxH) {
        this.drawMultiBox(g, boxX, boxY, boxW, boxH, this.shtMainMenuOutsideBox_offx, this.shtMainMenuOutsideBox_offy, this.outSideColor, this.insideColor);
    }

    public void drawMultiBox(final Graphics g, final int boxX, final int boxY, final int boxW, final int boxH, final int xOff, final int yOff, final int[] outColor, final int[] inColor) {
        final short shtMainMenu_x = (short) boxX;
        final short shtMainMenu_y = (short) boxY;
        final short shtMainMenu_w = (short) boxW;
        final short shtMainMenu_h = (short) boxH;
        DrawBase.fillRect(shtMainMenu_x - xOff - this.shtMainMenuOutsideBox_margin, shtMainMenu_y - yOff - this.shtMainMenuOutsideBox_margin, shtMainMenu_w + (xOff + this.shtMainMenuOutsideBox_margin) * 2, shtMainMenu_h + (yOff + this.shtMainMenuOutsideBox_margin) * 2, 7945772);
        DrawBase.drawBox(shtMainMenu_x - xOff, shtMainMenu_y - yOff, shtMainMenu_w + xOff * 2, shtMainMenu_h + yOff * 2, outColor, true);
        DrawBase.drawBox(shtMainMenu_x - this.shtMainMenuInsideBox_offx, shtMainMenu_y - this.shtMainMenuInsideBox_offy, shtMainMenu_w + this.shtMainMenuInsideBox_offx * 2, shtMainMenu_h + this.shtMainMenuInsideBox_offy * 2, inColor, true);
    }

    public void drawMultiBoxWithTitle(final Graphics g, final int boxX, final int boxY, final int boxW, final int boxH, final String title) {
        this.drawMultiBoxWithTitle(g, boxX, boxY, boxW, boxH, this.shtMainMenuOutsideBox_offx, this.shtMainMenuOutsideBox_offy, this.outSideColor, this.insideColor, title);
    }

    public void drawMultiBoxWithTitle(final Graphics g, final int boxX, final int boxY, final int boxW, final int boxH, final int xOff, final int yOff, final String title) {
        this.drawMultiBoxWithTitle(g, boxX, boxY, boxW, boxH, xOff, yOff, this.outSideColor, this.insideColor, title);
    }

    public void drawMultiBoxWithTitle(final Graphics g, final int boxX, final int boxY, final int boxW, final int boxH, final int xOff, final int yOff, final int[] outColor, final int[] inColor, final String title) {
        this.drawMultiBox(g, boxX, boxY, boxW, boxH, xOff, yOff, outColor, inColor);
        final short shtMainTitle_x = (short) (boxX - this.shtMainMenuTitle_offx);
        final short shtMainTitle_y = (short) (boxY - this.shtMainTitle_h - this.shtMainMenuOutsideBox_offy / 2);
        final short shtMainTitle_w = (short) (Macro.SCREEN_WIDTH - 2 * shtMainTitle_x);
        getInstance().drawEarBox(g, "", 9263661, shtMainTitle_x, shtMainTitle_y, shtMainTitle_w, this.shtMainTitle_h);
        DrawBase.DrawString(title, shtMainTitle_x, shtMainTitle_y, shtMainTitle_w, this.shtMainTitle_h, (short) 3, 0);
    }

    public void drawChat(final Graphics g, final int _drawx, final int _drawy, final String _chat, final byte picID) {
        this.drawChat(g, _drawx, _drawy, this.str = StringManager.wenZi(_chat, Macro.FONTW * _chat.length() + Macro.FONTW), picID);
    }

    public void drawChat(final Graphics g, final int _drawx, final int _drawy, final String[] _chat, final byte picID) {
        this.fontwidtharray = new int[_chat.length];
        for (int i = 0; i < _chat.length; ++i) {
            this.fontwidtharray[i] = Macro.font.stringWidth(_chat[i]);
        }
        this.MaxShowWidth = (short) (Macro.SCREEN_WIDTH - 24 - (Macro.FONTW << 1));
        if (Common.Max(this.fontwidtharray) <= this.MaxShowWidth) {
            this.frameW = Common.Max(this.fontwidtharray);
        } else {
            this.frameW = (byte) (Macro.SCREEN_WIDTH - this.MaxShowWidth >> 1);
        }
        this.frameH = (short) (_chat.length * Macro.FONTHEIGHT);
        DrawBase.drawBoxInMenu(_drawx - (Macro.FONTW >> 1), _drawy - 4, this.frameW + Macro.FONTW, this.frameH + 8, new int[]{6693903, 15182891, 15182891, 15124897}, true, picID);
        for (byte j = 0; j < _chat.length; ++j) {
            DrawBase.drawString(_chat[j], _drawx, _drawy + j * Macro.FONTHEIGHT - 1, 9263661, 20);
        }
    }
}
