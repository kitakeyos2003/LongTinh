// 
// Decompiled by Procyon v0.5.36
// 
package common;

import javax.microedition.lcdui.Graphics;
import face.GameUI;
import base.Param;
import base.Macro;
import model.PackageObject;
import base.DCanvas;
import means.ImageManager;
import model.SkillObject;
import face.MenuUI;
import java.util.Vector;
import java.util.Hashtable;

public class PackageBox {

    public int packageX;
    public int packageY;
    public int packageW;
    public int packageH;
    private int boxNum;
    private int boxColumnsNum;
    private int boxColumnsNumW;
    private int boxShowLineNum;
    private int boxLineNum;
    private byte upDownScrollIndex;
    private byte boxShowLineIndex;
    private byte boxColumnsIndex;
    private boolean packageFocal;
    private Hashtable GOODS;
    private Spoolr spoolr;
    int oldIndex;
    boolean isDouble;
    boolean isFrist;
    private Vector infoContent;
    String[] listStr;
    public static byte upDownScroll;

    public PackageBox(final int _x, final int _y, final int _w, final int _h, final int _boxNum, final boolean _packageFocal) {
        this.boxColumnsNum = 8;
        this.isDouble = false;
        this.isFrist = false;
        this.infoContent = new Vector(1, 1);
        this.packageX = _x;
        this.packageY = _y;
        this.packageW = _w;
        this.packageH = _h;
        this.reset(_boxNum);
        final int tempW = 24 * this.boxColumnsNum - 2;
        this.packageX += this.packageW - tempW >> 1;
        this.setFocal(_packageFocal);
        this.spoolr = new Spoolr();
    }

    public void reset(final int _boxNum) {
        if (this.boxNum == _boxNum) {
            return;
        }
        if ((this.boxNum = _boxNum) < this.boxColumnsNum) {
            this.boxColumnsNum = _boxNum;
        }
        this.boxColumnsNumW = 24 * this.boxColumnsNum - 2;
        if (this.boxColumnsNumW > this.packageW) {
            this.boxColumnsNumW -= this.packageW;
            this.boxColumnsNum -= this.boxColumnsNumW / 24;
            if (this.boxColumnsNumW % 24 != 0) {
                --this.boxColumnsNum;
            }
        }
        this.boxLineNum = this.boxNum / this.boxColumnsNum;
        if (this.boxNum % this.boxColumnsNum != 0) {
            ++this.boxLineNum;
        }
        this.boxShowLineNum = this.packageH / 22;
    }

    public void draw(final Hashtable _goods) {
        this.GOODS = _goods;
        for (int i = this.upDownScrollIndex; i < this.boxShowLineNum + this.upDownScrollIndex; ++i) {
            for (int j = 0; j < this.boxColumnsNum; ++j) {
                final int tempboxIndex = this.getCellBoxIndex(i, j);
                if (tempboxIndex >= this.boxNum) {
                    break;
                }
                final int boxW = 22;
                final int boxH = 22;
                final int boxX = this.packageX + j * (boxW + 2);
                final int boxY = this.packageY + i * (boxH + 2) - this.upDownScrollIndex * (boxH + 2);
                DrawBase.drawBox(boxX, boxY, boxW, boxH, IDefines.GLOBAL_UI_PACKAGE_BOX_BORDER, true);
                if (_goods != null) {
                    if (_goods.containsKey(new Integer(tempboxIndex))) {
                        final int[] boxLineColumns = this.getCellLineColumnsIndex(tempboxIndex);
                        final int imgW = 16;
                        final int imgH = 16;
                        final int imgX = this.packageX + boxLineColumns[1] * (boxW + 2) + (boxW - imgW >> 1);
                        final int imgY = this.packageY + boxLineColumns[0] * (boxH + 2) + (boxH - imgH >> 1) - this.upDownScrollIndex * (boxH + 2);
                        if (MenuUI.getInstance().getState() == -81 && MenuUI.TabState == 0) {
                            SkillObject skill = (SkillObject) _goods.get(new Integer(tempboxIndex));
                            ImageManager.getInstance().drawPropAndNum(DCanvas.gameG, skill.shtIcon, (short) 0, imgX, imgY);
                        } else {
                            final PackageObject po = (PackageObject) _goods.get(new Integer(tempboxIndex));
                            final int bgColor = (po.bytQuality == 1) ? 16777215 : Macro.INT_PROP_COLOR[po.bytQuality];
                            DrawBase.drawBox(boxX, boxY, boxW, boxH, new int[]{11370322, bgColor, bgColor}, true);
                            ImageManager.getInstance().drawPropAndNum(DCanvas.gameG, po.shtIcon, po.shtPOnum, imgX, imgY);
                        }
                    } else {
                        DrawBase.drawBox(boxX, boxY, boxW, boxH, IDefines.GLOBAL_UI_PACKAGE_BOX_BORDER, true);
                    }
                } else {
                    DrawBase.drawBox(boxX, boxY, boxW, boxH, IDefines.GLOBAL_UI_PACKAGE_BOX_BORDER, true);
                }
            }
        }
        final int boxW2 = 22;
        final int boxH2 = 22;
        final int boxX2 = this.packageX + this.boxColumnsIndex * (boxW2 + 2);
        final int boxY2 = this.packageY + this.boxShowLineIndex * (boxH2 + 2);
        if (this.getFocal()) {
            LayoutStyle.getInstance().drawChooseFrame(DCanvas.gameG, boxX2, boxY2, boxW2, boxH2);
            if (_goods != null && _goods.containsKey(new Integer(this.getCellBoxIndex()))) {
                if (this.listStr != null) {
                    Param.popupDialogInstance.setShow(true);
                } else {
                    Param.popupDialogInstance.setShow(false);
                }
            } else {
                Param.popupDialogInstance.setShow(false);
            }
        }
        this.spoolr.setOptionSpoolr(DCanvas.gameG, this.getShowX() + this.getColumnsNum() * (boxW2 + 2) + (ScrollText.arrowhead_width >> 1), this.packageY - 4, this.packageY + this.boxShowLineNum * 22 + 6, this.boxShowLineNum, this.boxLineNum, this.boxShowLineIndex + this.upDownScrollIndex, false);
    }

    public void getKeyUp() {
        if (this.getFocal()) {
            if (this.boxShowLineIndex > 0) {
                --this.boxShowLineIndex;
            } else if (this.upDownScrollIndex != 0) {
                --this.upDownScrollIndex;
            }
            this.setPopupDialog();
        }
    }

    public void getKeyDown() {
        if (this.getFocal()) {
            if (this.boxShowLineIndex < this.boxShowLineNum - 1) {
                if (this.boxShowLineIndex < this.boxLineNum - 1) {
                    ++this.boxShowLineIndex;
                }
            } else if (this.getLineIndex() < this.boxLineNum - 1) {
                ++this.upDownScrollIndex;
            }
            this.setPopupDialog();
        }
    }

    public void getKeyLeft() {
        if (this.getFocal() && this.boxColumnsIndex > 0) {
            --this.boxColumnsIndex;
            this.setPopupDialog();
        }
    }

    public void getKeyRight() {
        if (this.getCellBoxIndex() < this.boxNum - 1) {
            if (this.boxColumnsIndex < this.boxColumnsNum - 1) {
                ++this.boxColumnsIndex;
            }
            this.setPopupDialog();
        }
    }

    public boolean isDoublePoint() {
        return this.isDouble;
    }

    public void getTouch() {
        DCanvas.getInstance().blsTouchEvent = true;
        if (this.getFocal()) {
            this.oldIndex = this.getCellBoxIndex();
            this.isFrist = true;
        } else {
            this.isFrist = false;
            this.isDouble = false;
        }
        if (DCanvas.getInstance().IsPointerDown(this.packageX, this.packageY, this.getShowW(), this.getShowH())) {
            final byte li = (byte) ((DCanvas.getInstance().CurPressedY - this.packageY) / 24);
            final byte ci = (byte) ((DCanvas.getInstance().CurPressedX - this.packageX) / 24);
            if (li < this.boxShowLineNum && ci < this.getColumnsNum() && li < this.boxLineNum) {
                this.setFocal(true);
                this.boxShowLineIndex = li;
                this.boxColumnsIndex = ci;
                if (this.isFrist && this.oldIndex == this.getCellBoxIndex()) {
                    this.isDouble = true;
                } else {
                    this.isDouble = false;
                }
                this.setPopupDialog();
                return;
            }
        } else {
            this.isFrist = false;
            this.isDouble = false;
        }
        if (this.spoolr.blnSpoolr) {
            DCanvas.getInstance().blsTouchEvent = true;
            final byte arrowhead_width = ScrollText.arrowhead_width;
            final byte arrowhead_height = ScrollText.arrowhead_height;
            final short Topx = this.spoolr.shtSpoolrX;
            final short Topy = this.spoolr.shtSpoolrSY;
            final int breadHeight = this.spoolr.shtSpoolrEY - this.spoolr.shtSpoolrSY;
            final int areaW = arrowhead_width * 3;
            if (DCanvas.getInstance().IsPointerDragged(Topx - arrowhead_width, Topy - arrowhead_height, areaW, (breadHeight >> 1) + arrowhead_height)) {
                this.getKeyUp();
            } else if (DCanvas.getInstance().IsPointerDragged(Topx - arrowhead_width, Topy + (breadHeight >> 1) - arrowhead_height, areaW, (breadHeight >> 1) + arrowhead_height)) {
                this.getKeyDown();
            } else if (DCanvas.getInstance().IsPointerDown(Topx - arrowhead_width, Topy - arrowhead_height, areaW, (breadHeight >> 1) + arrowhead_height)) {
                this.getKeyUp();
            } else if (DCanvas.getInstance().IsPointerDown(Topx - arrowhead_width, Topy + (breadHeight >> 1) - arrowhead_height, areaW, (breadHeight >> 1) + arrowhead_height)) {
                this.getKeyDown();
            }
        }
    }

    public byte getLineIndex() {
        return (byte) (this.boxShowLineIndex + this.upDownScrollIndex);
    }

    public byte getColumnsIndex() {
        return this.boxColumnsIndex;
    }

    public byte getCellBoxIndex() {
        return (byte) this.getCellBoxIndex(this.getLineIndex(), this.boxColumnsIndex);
    }

    private int getCellBoxIndex(final int line, final int columns) {
        return line * this.boxColumnsNum + columns;
    }

    private int[] getCellLineColumnsIndex(final int boxIndex) {
        final int[] lineColumns = {boxIndex / this.boxColumnsNum, boxIndex % this.boxColumnsNum};
        return lineColumns;
    }

    public byte getLineNum() {
        return (byte) this.boxLineNum;
    }

    public byte getColumnsNum() {
        return (byte) this.boxColumnsNum;
    }

    public int getShowX() {
        return this.packageX;
    }

    public int getShowY() {
        return this.packageY;
    }

    public int getShowW() {
        if (this.boxColumnsNumW < this.packageW) {
            return this.packageW;
        }
        return this.boxColumnsNumW;
    }

    public int getShowH() {
        return this.boxShowLineNum * 24 - 2;
    }

    public void setFocal(final boolean focal) {
        if (!(this.packageFocal = focal)) {
            this.boxColumnsIndex = 0;
            this.boxShowLineIndex = 0;
            this.upDownScrollIndex = 0;
        }
        if (focal) {
            this.setPopupDialog();
        } else {
            Param.popupDialogInstance.setShow(false);
            if (Param.changeRoleValue != null) {
                Param.changeRoleValue[0] = 0;
                Param.changeRoleValue[1] = 0;
                Param.changeRoleValue[2] = 0;
                Param.changeRoleValue[3] = 0;
                Param.changeRoleValue[4] = 0;
                Param.changeRoleValue[5] = 0;
            }
        }
    }

    public void setBoxIndex(final byte index, final boolean focal) {
        this.packageFocal = focal;
        this.boxColumnsIndex = (byte) (index % this.boxColumnsNum);
        this.boxShowLineIndex = (byte) (index / this.boxColumnsNum);
        if (focal) {
            this.setPopupDialog();
        }
    }

    public boolean getFocal() {
        return this.packageFocal;
    }

    public boolean getCellBoxIsBedata(final Hashtable _goods) {
        boolean is = false;
        final int boxId = this.getCellBoxIndex(this.getLineIndex(), this.getColumnsIndex());
        is = (_goods != null && _goods.containsKey(new Integer(boxId)));
        return is;
    }

    public void setPopupDialog() {
        this.setPopupDialog(this.GOODS);
    }

    public void setPopupDialog(final Hashtable GOODS) {
        Param.popupDialogInstance.setShow(false);
        if (MenuUI.getInstance().getState() == -81 && MenuUI.TabState == 0) {
            if (GOODS != null && GOODS.containsKey(new Integer(this.getCellBoxIndex()))) {
                SkillObject skill = (SkillObject) GOODS.get(new Integer(this.getCellBoxIndex()));
                this.infoContent.removeAllElements();
                this.infoContent.addElement(skill.strName);
                final int boxW = 22;
                final int boxH = 22;
                final int boxX = this.packageX + this.boxColumnsIndex * (boxW + 2);
                final int boxY = this.packageY + this.boxShowLineIndex * (boxH + 2);
                if (this.infoContent != null && this.infoContent.size() != 0) {
                    this.listStr = new String[this.infoContent.size()];
                    for (int i = 0; i < this.listStr.length; ++i) {
                        this.listStr[i] = this.infoContent.elementAt(i).toString();
                    }
                    Param.popupDialogInstance.setPopupDialog(this.listStr, (short) (boxX + boxW), (short) (boxY + boxH));
                    Param.popupDialogInstance.setShow(true);
                }
            }
        } else if (GOODS != null && GOODS.containsKey(new Integer(this.getCellBoxIndex()))) {
            final PackageObject po = (PackageObject) GOODS.get(new Integer(this.getCellBoxIndex()));
            int minAttkChange = 0;
            final int maxAttkChange = 0;
            int hpChange = 0;
            int defenesChange = 0;
            int PROP_STRENGTH = 0;
            int PROP_AGILITY = 0;
            int PROP_STAMINA = 0;
            int PROP_INTE = 0;
            int PROP_SPIRIT = 0;
            int PROP_LUCKY = 0;
            if (Param.getInstance().hPackageEquip != null && (MenuUI.getInstance().getState() == 39 || MenuUI.getInstance().getState() == 38 || MenuUI.getInstance().getState() == 37 || MenuUI.getInstance().getState() == 36 || MenuUI.getInstance().getState() == 10 || MenuUI.getInstance().getState() == -127 || MenuUI.getInstance().getState() == -81)) {
                PackageObject poEquip = (PackageObject) Param.getInstance().hPackageEquip.get(new Integer(po.byteEquipFixed));
                if (Param.getInstance().hPackageEquip.containsKey(new Integer(po.byteEquipFixed))) {
                    minAttkChange = GameUI.getInstance().getAttributeValue((byte) 25, po.bytsAttribute, po.shtsAttributeInfo) - GameUI.getInstance().getAttributeValue((byte) 25, poEquip.bytsAttribute, poEquip.shtsAttributeInfo);
                    minAttkChange = GameUI.getInstance().getAttributeValue((byte) 26, po.bytsAttribute, po.shtsAttributeInfo) - GameUI.getInstance().getAttributeValue((byte) 26, poEquip.bytsAttribute, poEquip.shtsAttributeInfo);
                    hpChange = GameUI.getInstance().getAttributeValue((byte) 13, po.bytsAttribute, po.shtsAttributeInfo) - GameUI.getInstance().getAttributeValue((byte) 13, poEquip.bytsAttribute, poEquip.shtsAttributeInfo);
                    defenesChange = GameUI.getInstance().getAttributeValue((byte) 19, po.bytsAttribute, po.shtsAttributeInfo) - GameUI.getInstance().getAttributeValue((byte) 19, poEquip.bytsAttribute, poEquip.shtsAttributeInfo);
                    PROP_STRENGTH = GameUI.getInstance().getAttributeValue((byte) 9, po.bytsAttribute, po.shtsAttributeInfo) - GameUI.getInstance().getAttributeValue((byte) 9, poEquip.bytsAttribute, poEquip.shtsAttributeInfo);
                    PROP_AGILITY = GameUI.getInstance().getAttributeValue((byte) 11, po.bytsAttribute, po.shtsAttributeInfo) - GameUI.getInstance().getAttributeValue((byte) 11, poEquip.bytsAttribute, poEquip.shtsAttributeInfo);
                    PROP_STAMINA = GameUI.getInstance().getAttributeValue((byte) 7, po.bytsAttribute, po.shtsAttributeInfo) - GameUI.getInstance().getAttributeValue((byte) 7, poEquip.bytsAttribute, poEquip.shtsAttributeInfo);
                    PROP_INTE = GameUI.getInstance().getAttributeValue((byte) 8, po.bytsAttribute, po.shtsAttributeInfo) - GameUI.getInstance().getAttributeValue((byte) 8, poEquip.bytsAttribute, poEquip.shtsAttributeInfo);
                    PROP_SPIRIT = GameUI.getInstance().getAttributeValue((byte) 10, po.bytsAttribute, po.shtsAttributeInfo) - GameUI.getInstance().getAttributeValue((byte) 10, poEquip.bytsAttribute, poEquip.shtsAttributeInfo);
                    PROP_LUCKY = GameUI.getInstance().getAttributeValue((byte) 12, po.bytsAttribute, po.shtsAttributeInfo) - GameUI.getInstance().getAttributeValue((byte) 12, poEquip.bytsAttribute, poEquip.shtsAttributeInfo);
                } else {
                    minAttkChange = GameUI.getInstance().getAttributeValue((byte) 25, po.bytsAttribute, po.shtsAttributeInfo);
                    minAttkChange = GameUI.getInstance().getAttributeValue((byte) 26, po.bytsAttribute, po.shtsAttributeInfo);
                    hpChange = GameUI.getInstance().getAttributeValue((byte) 13, po.bytsAttribute, po.shtsAttributeInfo);
                    defenesChange = GameUI.getInstance().getAttributeValue((byte) 19, po.bytsAttribute, po.shtsAttributeInfo);
                    PROP_STRENGTH = GameUI.getInstance().getAttributeValue((byte) 9, po.bytsAttribute, po.shtsAttributeInfo);
                    PROP_AGILITY = GameUI.getInstance().getAttributeValue((byte) 11, po.bytsAttribute, po.shtsAttributeInfo);
                    PROP_STAMINA = GameUI.getInstance().getAttributeValue((byte) 7, po.bytsAttribute, po.shtsAttributeInfo);
                    PROP_INTE = GameUI.getInstance().getAttributeValue((byte) 8, po.bytsAttribute, po.shtsAttributeInfo);
                    PROP_SPIRIT = GameUI.getInstance().getAttributeValue((byte) 10, po.bytsAttribute, po.shtsAttributeInfo);
                    PROP_LUCKY = GameUI.getInstance().getAttributeValue((byte) 12, po.bytsAttribute, po.shtsAttributeInfo);
                }
                Param.changeRoleValue[0] = PROP_STRENGTH;
                Param.changeRoleValue[1] = PROP_AGILITY;
                Param.changeRoleValue[2] = PROP_STAMINA;
                Param.changeRoleValue[3] = PROP_INTE;
                Param.changeRoleValue[4] = PROP_SPIRIT;
                Param.changeRoleValue[5] = PROP_LUCKY;
            }
            this.infoContent.removeAllElements();
            this.infoContent.addElement(po.strName);
            if (po.bytQuality >= 0) {
                Param.popupDialogInstance.setTitleColor(true, Macro.INT_PROP_COLOR[po.bytQuality]);
            } else {
                Param.popupDialogInstance.setTitleColor(false, 0);
            }
            if (po.intPaiMaiPrice > 0) {
                this.infoContent.addElement("Giá bán: " + po.intPaiMaiPrice + " xu.");
            } else if (po.intPrice > 0) {
                this.infoContent.addElement("Giá bán: " + po.intPrice + " vàng.");
            }
            if (minAttkChange != 0) {
                this.infoContent.addElement("Tấn công: " + ((minAttkChange > 0) ? ("+" + minAttkChange) : new StringBuffer().append(minAttkChange).toString()));
            }
            if (maxAttkChange != 0) {
                this.infoContent.addElement("Tấn công: " + ((maxAttkChange > 0) ? ("+" + maxAttkChange) : new StringBuffer().append(maxAttkChange).toString()));
            }
            if (hpChange != 0) {
                this.infoContent.addElement("HP: " + ((hpChange > 0) ? ("+" + hpChange) : new StringBuffer().append(hpChange).toString()));
            }
            if (defenesChange != 0) {
                this.infoContent.addElement("Phòng thủ: " + ((defenesChange > 0) ? ("+" + defenesChange) : new StringBuffer().append(defenesChange).toString()));
            }
            this.infoContent.addElement("Cấp độ yêu cầu: " + po.shtLevel);
            final int boxW2 = 22;
            final int boxH2 = 22;
            final int boxX2 = this.packageX + this.boxColumnsIndex * (boxW2 + 2);
            final int boxY2 = this.packageY + this.boxShowLineIndex * (boxH2 + 2);
            if (this.infoContent != null && this.infoContent.size() != 0) {
                this.listStr = new String[this.infoContent.size()];
                for (int j = 0; j < this.listStr.length; ++j) {
                    this.listStr[j] = this.infoContent.elementAt(j).toString();
                }
                Param.popupDialogInstance.setPopupDialog(this.listStr, (short) (boxX2 + boxW2), (short) (boxY2 + boxH2));
                Param.popupDialogInstance.setShow(true);
            }
        } else {
            Param.changeRoleValue[0] = 0;
            Param.changeRoleValue[1] = 0;
            Param.changeRoleValue[2] = 0;
            Param.changeRoleValue[3] = 0;
            Param.changeRoleValue[4] = 0;
            Param.changeRoleValue[5] = 0;
            Param.popupDialogInstance.setShow(false);
        }
    }

    public void drawInlay(final Graphics g) {
        this.GOODS = Param.getInstance().HSInlay;
        final int boxW = 22;
        final int boxH = 22;
        int boxX = 0;
        int boxY = 0;
        final int row = 2;
        final int list = 6;
        if (this.GOODS.containsKey(new Integer(Param.getInstance().InlayIndex))) {
            PackageObject po = (PackageObject) this.GOODS.get(new Integer(Param.getInstance().InlayIndex));
            byte[] data;
            if (po.holeDate != null) {
                data = po.holeDate;
            } else {
                data = new byte[12];
            }
            for (int i = 0; i < row; ++i) {
                for (int j = 0; j < list; ++j) {
                    boxX = this.packageX + j * (boxW + 2);
                    boxY = this.packageY + i * (boxH + 2) - PackageBox.upDownScroll * Macro.FONTHEIGHT;
                    if (data[list * i + j] == 2 || data[list * i + j] == 3 || data[list * i + j] == 4) {
                        MenuUI.getInstance().inlay[0].drawAnimationFrame(g, 1, 0, boxX, boxY);
                    }
                    if (data[list * i + j] == 4) {
                        MenuUI.getInstance().inlay[1].drawAnimation(g, boxX, boxY);
                    } else {
                        MenuUI.getInstance().inlay[0].drawAnimationFrame(g, data[list * i + j], 0, boxX, boxY);
                    }
                }
            }
        }
        final int fboxX = this.packageX + this.boxColumnsIndex * (boxW + 2) - (boxW >> 1);
        final int fboxY = this.packageY + this.boxShowLineIndex * (boxH + 2) - (boxH >> 1);
        final int holeR = MenuUI.getInstance().RoundHole;
        final byte strengthenState = MenuUI.StrengthenState;
        MenuUI.getInstance().getClass();
        if (strengthenState == 1 && Param.getInstance().RightDate) {
            MenuUI.getInstance().inlay[2].drawAnimation(g, fboxX + (holeR >> 1), fboxY + (holeR >> 1));
        }
        if (this.getFocal()) {
            LayoutStyle.getInstance().drawChooseFrame(g, fboxX, fboxY, boxW, boxH);
            if (this.listStr != null) {
                Param.popupDialogInstance.setShow(true);
            } else {
                Param.popupDialogInstance.setShow(false);
            }
        }
    }

    public void setPopupShow() {
        Param.popupDialogInstance.setShow(false);
        this.GOODS = Param.getInstance().HSInlay;
        if (this.GOODS.containsKey(new Integer(Param.getInstance().InlayIndex))) {
            PackageObject po = (PackageObject) this.GOODS.get(new Integer(Param.getInstance().InlayIndex));
            final byte[] data = po.holeDate;
            this.infoContent.removeAllElements();
            if (data[this.getCellBoxIndex()] == 0) {
                this.infoContent.addElement(IDefines.HOLE_STATE[0]);
            } else if (data[this.getCellBoxIndex()] == 1) {
                this.infoContent.addElement(IDefines.HOLE_STATE[1]);
            } else if (data[this.getCellBoxIndex()] == 2) {
                this.infoContent.addElement(IDefines.HOLE_STATE[2]);
            } else if (data[this.getCellBoxIndex()] == 3) {
                this.infoContent.addElement(IDefines.HOLE_STATE[3]);
            } else if (data[this.getCellBoxIndex()] == 4) {
                this.infoContent.addElement(IDefines.HOLE_STATE[4]);
            }
            final int boxW = 22;
            final int boxH = 22;
            final int boxX = this.packageX + this.boxColumnsIndex * (boxW + 2) - (boxW >> 1);
            final int boxY = this.packageY + this.boxShowLineIndex * (boxH + 2) - (boxH >> 1);
            if (this.infoContent != null && this.infoContent.size() != 0) {
                this.listStr = new String[this.infoContent.size()];
                for (int j = 0; j < this.listStr.length; ++j) {
                    this.listStr[j] = this.infoContent.elementAt(j).toString();
                }
                Param.popupDialogInstance.setPopupDialog(this.listStr, (short) (boxX + boxW), (short) (boxY + boxH));
                Param.popupDialogInstance.setShow(true);
            }
        }
    }

    public void getTouchHole() {
        DCanvas.getInstance().blsTouchEvent = true;
        final int holeR = MenuUI.getInstance().RoundHole;
        if (DCanvas.getInstance().IsPointerDown(this.packageX - (holeR >> 1), this.packageY - (holeR >> 1), this.packageW - 8, this.packageH - 3)) {
            final byte li = (byte) ((DCanvas.getInstance().CurPressedY - (this.packageY - (holeR >> 1))) / 26);
            final byte ci = (byte) ((DCanvas.getInstance().CurPressedX - (this.packageX - (holeR >> 1))) / 25);
            if (li < this.boxShowLineNum && ci < this.getColumnsNum() && li < this.boxLineNum) {
                this.setFocal(true);
                this.boxShowLineIndex = li;
                this.boxColumnsIndex = ci;
                return;
            }
        }
        if (this.getFocal()) {
            DCanvas.getInstance().blsTouchEvent = true;
            if (DCanvas.getInstance().IsPointerDown(this.getShowX() + this.getColumnsNum() * 24 + 2, this.packageY - 2, 7, 11)) {
                this.getKeyUp();
            } else if (DCanvas.getInstance().IsPointerDown(this.getShowX() + this.getColumnsNum() * 24 + 2, this.packageY - 2 + this.packageH - 11, 7, 11)) {
                this.getKeyDown();
            }
        }
    }
}
