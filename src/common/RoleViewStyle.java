// 
// Decompiled by Procyon v0.5.36
// 
package common;

import face.MenuUI;
import javax.microedition.lcdui.Image;
import base.Macro;
import model.PackageObject;
import base.DCanvas;
import base.Param;
import face.GameUI;
import model.ORole;
import javax.microedition.lcdui.Graphics;
import model.ORPMe;
import means.ImageManager;
import java.util.Hashtable;
import means.QSprite;

public class RoleViewStyle {

    private boolean viewFocuse;
    private short viewTopX;
    private short viewTopY;
    private byte viewCurrentIndex;
    private boolean blnShowEquip;
    private boolean blnShowPet;
    private boolean blnShowBorder;
    private boolean blnDrawPopup;
    private GridTable vGTable;
    private static final short VIEW_ROW = 4;
    private static final short VIEW_COL = 5;
    private static final short[] VIEW_ROW_PERCENT;
    private static final short[] VIEW_COL_PERCENT;
    private static final short ROLE_VIEW_X_OFF = 2;
    public static int ROLE_DIRECTION;
    private QSprite sprAbility;
    private String abilityName;
    private static final byte ANGLE_ONE = 6;
    private static final byte ANGLE_THREE = 7;
    public static int[] roleAbility;
    private static int abilityX;
    private static int abilityY;
    private static int abilityLayoutCol;
    private Hashtable drawPackTable;
    public static final byte ROLE_VIEW_INDEX_NONE = -1;
    public static final byte ROLE_VIEW_INDEX_LEFT_ONE = 0;
    public static final byte ROLE_VIEW_INDEX_LEFT_TWO = 1;
    public static final byte ROLE_VIEW_INDEX_LEFT_THREE = 2;
    public static final byte ROLE_VIEW_INDEX_LEFT_FOUR = 3;
    public static final byte ROLE_VIEW_INDEX_RIGHT_ONE = 4;
    public static final byte ROLE_VIEW_INDEX_RIGHT_TWO = 5;
    public static final byte ROLE_VIEW_INDEX_RIGHT_THREE = 6;
    public static final byte ROLE_VIEW_INDEX_RIGHT_FOUR = 7;
    public static final byte ROLE_VIEW_INDEX_TOP_LEFT = 8;
    public static final byte ROLE_VIEW_INDEX_TOP_RIGHT = 9;
    public static final byte ROLE_VIEW_INDEX_COUNT = 10;

    static {
        VIEW_ROW_PERCENT = new short[]{22, 22, 22, 34};
        VIEW_COL_PERCENT = new short[]{20, 20, 20, 20, 20};
        RoleViewStyle.ROLE_DIRECTION = 2;
        RoleViewStyle.abilityX = 0;
        RoleViewStyle.abilityY = 0;
        RoleViewStyle.abilityLayoutCol = 1;
    }

    public RoleViewStyle(final short x, final short y, final boolean _blnShowEquip, final boolean _blnShowPet, final boolean _blnShowBorder_, final boolean _viewFocuse) {
        this.viewFocuse = false;
        this.sprAbility = null;
        this.abilityName = "bagfont";
        this.initViewStyle(x, y, _blnShowEquip, _blnShowPet, _blnShowBorder_, _viewFocuse);
        this.setPopupDialog();
    }

    public void initViewStyle(final short x, final short y, final boolean _blnShowEquip, final boolean _blnShowPet, final boolean _blnShowBorder_, final boolean _viewFocuse) {
        this.blnShowEquip = _blnShowEquip;
        this.blnShowPet = _blnShowPet;
        this.blnShowBorder = _blnShowBorder_;
        this.viewTopX = x;
        this.viewTopY = y;
        this.viewFocuse = _viewFocuse;
        this.vGTable = new GridTable(x, y, (short) 115, (short) 105, (short) 4, (short) 5, RoleViewStyle.VIEW_ROW_PERCENT, RoleViewStyle.VIEW_COL_PERCENT);
        if (this.sprAbility == null) {
            this.sprAbility = ImageManager.loadSpriteById(1, this.abilityName, this.abilityName, this.abilityName, "ui");
        }
        RoleViewStyle.roleAbility = new int[]{ORPMe.ME.Strength, ORPMe.ME.Agility, ORPMe.ME.Stamina, ORPMe.ME.Intellect, ORPMe.ME.Energy, ORPMe.ME.Luck};
        if (this.blnShowEquip) {
            this.viewCurrentIndex = 0;
        } else if (this.blnShowPet) {
            this.viewCurrentIndex = 8;
        } else {
            this.viewCurrentIndex = -1;
        }
    }

    public void setAbilityFixed(final int _x, final int _y, final int _layoutCol) {
        RoleViewStyle.abilityX = _x;
        RoleViewStyle.abilityY = _y;
        RoleViewStyle.abilityLayoutCol = _layoutCol;
    }

    public void drawViewStyle(final Graphics g, final ORole _ObjectRole) {
        if (GameUI.getInstance().checkMeMenuUi(10) || GameUI.getInstance().checkMeMenuUi(36) || GameUI.getInstance().checkMeMenuUi(37) || GameUI.getInstance().checkMeMenuUi(38) || GameUI.getInstance().checkMeMenuUi(-70) || GameUI.getInstance().checkMeMenuUi(39) || GameUI.getInstance().checkMeMenuUi(33) || GameUI.getInstance().checkMeMenuUi(21) || GameUI.getInstance().checkMeMenuUi(22)) {
            this.drawPackTable = Param.getInstance().hPackageEquip;
        } else if (GameUI.getInstance().checkMeMenuUi(-6)) {
            this.drawPackTable = Param.getInstance().hOtherPackageEquip;
        } else if (Param.getInstance().oSelectRole != null) {
            this.drawPackTable = ((Param.getInstance().bytSelectType == 3) ? Param.getInstance().hPackageEquip : Param.getInstance().hOtherPackageEquip);
        }
        this.drawSmallBackRect(g, this.viewTopX, this.viewTopY);
        if (_ObjectRole != null) {
            _ObjectRole.draw(g, this.vGTable.getCell(2, 3).cell_center_x, this.vGTable.getCell(4, 2).cell_y + (this.vGTable.getCell(4, 2).cell_h >> 1));
        }
        if (this.viewFocuse) {
            LayoutStyle.getInstance().drawChooseFrame(DCanvas.gameG, this.vGTable.getCell(IDefines.BOX_FIXED[this.viewCurrentIndex][0], IDefines.BOX_FIXED[this.viewCurrentIndex][1]).cell_x, this.vGTable.getCell(IDefines.BOX_FIXED[this.viewCurrentIndex][0], IDefines.BOX_FIXED[this.viewCurrentIndex][1]).cell_y, 20, 20);
            if (((Param.getInstance().oSelectRole == null) ? Param.getInstance().hPackageEquip : Param.getInstance().hOtherPackageEquip) != null && ((Param.getInstance().oSelectRole == null) ? Param.getInstance().hPackageEquip : Param.getInstance().hOtherPackageEquip).containsKey(new Integer(this.viewCurrentIndex)) && this.blnDrawPopup) {
                Param.popupDialogInstance.setShow(true);
            } else {
                Param.popupDialogInstance.setShow(false);
            }
        }
    }

    private void drawSmallBackRect(final Graphics g, final int _x, final int _y) {
        DrawBase.drawBox(this.vGTable.getCell(2, 2).cell_x, this.vGTable.getCell(2, 2).cell_y, this.vGTable.getCell(2, 2).cell_w + this.vGTable.getCell(2, 3).cell_w + this.vGTable.getCell(2, 4).cell_w - 2, this.vGTable.getCell(2, 2).cell_h + this.vGTable.getCell(3, 2).cell_h + this.vGTable.getCell(4, 2).cell_h, new int[]{-2911170, -1781358, -2911170}, true);
        this.sprAbility.drawAnimationFrame(g, 6, 0, this.vGTable.getCell(4, 2).cell_x + 1, this.vGTable.getCell(4, 2).cell_y + this.vGTable.getCell(4, 2).cell_h - 1);
        this.sprAbility.drawAnimationFrame(g, 7, 0, this.vGTable.getCell(4, 4).cell_x + this.vGTable.getCell(4, 4).cell_w - 1 - 2, this.vGTable.getCell(4, 2).cell_y + this.vGTable.getCell(4, 2).cell_h - 1);
        if (this.drawPackTable != null) {
            for (byte i = 0; i < 10; ++i) {
                if (this.blnShowEquip && i <= 7) {
                    boolean blnDraw = this.drawPackTable.containsKey(new Integer(i));
                    if (blnDraw) {
                        PackageObject po = (PackageObject) this.drawPackTable.get(new Integer(i));
                        int bgColor = (po.bytQuality == 1) ? 16777215 : Macro.INT_PROP_COLOR[po.bytQuality];
                        DrawBase.drawBox(this.vGTable.getCell(IDefines.BOX_FIXED[i][0], IDefines.BOX_FIXED[i][1]).cell_x, this.vGTable.getCell(IDefines.BOX_FIXED[i][0], IDefines.BOX_FIXED[i][1]).cell_y, 20, 20, new int[]{11370322, bgColor, bgColor}, true);
                        Image _img = (Image) Param.getInstance().hImgObject.get(new Integer(po.shtIcon));
                        if (_img != null) {
                            DrawBase.drawImage(_img, this.vGTable.getCell(IDefines.BOX_FIXED[i][0], IDefines.BOX_FIXED[i][1]).cell_x + 2, this.vGTable.getCell(IDefines.BOX_FIXED[i][0], IDefines.BOX_FIXED[i][1]).cell_y + 2, 20);
                        }
                    } else {
                        DrawBase.drawBox(this.vGTable.getCell(IDefines.BOX_FIXED[i][0], IDefines.BOX_FIXED[i][1]).cell_x, this.vGTable.getCell(IDefines.BOX_FIXED[i][0], IDefines.BOX_FIXED[i][1]).cell_y, 20, 20, IDefines.ROLE_VIEW_BOX_BORDER_COLOR, true);
                    }
                } else if (this.blnShowPet && (i == 9 || i == 8)) {
                    DrawBase.drawBox(this.vGTable.getCell(IDefines.BOX_FIXED[i][0], IDefines.BOX_FIXED[i][1]).cell_x, this.vGTable.getCell(IDefines.BOX_FIXED[i][0], IDefines.BOX_FIXED[i][1]).cell_y, 20, 20, IDefines.ROLE_VIEW_BOX_BORDER_COLOR, true);
                    if (ORPMe.ME.hPackagePet != null && ORPMe.ME.hPackagePet.containsKey(new Integer(i))) {
                        PackageObject po2 = (PackageObject) ORPMe.ME.hPackagePet.get(new Integer(i));
                        Image _img2 = GameUI.getInstance().getObjectImg(po2.shtIcon);
                        if (_img2 != null) {
                            DrawBase.drawImage(_img2, this.vGTable.getCell(IDefines.BOX_FIXED[i][0], IDefines.BOX_FIXED[i][1]).cell_x + 2, this.vGTable.getCell(IDefines.BOX_FIXED[i][0], IDefines.BOX_FIXED[i][1]).cell_y + 2, 20);
                        }
                    }
                }
            }
        }
    }

    public void drawAbilityRect(final Graphics g) {
        int row = 0;
        int col = 0;
        final int size = this.sprAbility.getAnimationCount() - 2;
        row = size / RoleViewStyle.abilityLayoutCol;
        col = RoleViewStyle.abilityLayoutCol;
        int index = 0;
        int boxX = 0;
        int boxY = 0;
        int sprX = 0;
        int sprY = 0;
        int numX = 0;
        int numY = 0;
        for (int i = 0; i < col; ++i) {
            for (int j = 0; j < row; ++j) {
                boxX = RoleViewStyle.abilityX - 2 + i * 96;
                boxY = RoleViewStyle.abilityY + j * 18;
                sprX = RoleViewStyle.abilityX + i * 96;
                sprY = RoleViewStyle.abilityY + j * 18 + 9;
                numX = RoleViewStyle.abilityX + 25 + i * 96 + 28;
                numY = RoleViewStyle.abilityY + j * 18 + 4;
                index = (col - 1) * i + j;
                DrawBase.drawBox(boxX, boxY, 71, 18, IDefines.ROLE_ABILITY_CELL_COLOR, true);
                this.sprAbility.drawAnimationFrame(g, index, 0, sprX, sprY);
                GameUI.getInstance().drawMoneyNum(g, RoleViewStyle.roleAbility[index], numX, numY + 1);
                if (Param.changeRoleValue[index] != 0) {
                    GameUI.getInstance().drawEquipNum(g, Param.changeRoleValue[index], boxX + 56 + 18, boxY + 6);
                }
            }
        }
    }

    public void setViewFocal(final boolean _viewFocuse) {
        this.viewFocuse = _viewFocuse;
        if (_viewFocuse) {
            this.setPopupDialog();
        }
    }

    public boolean getViewFocal() {
        return this.viewFocuse;
    }

    public void cleanTabStyle() {
        this.sprAbility = null;
        Param.changeRoleValue = null;
        this.vGTable = null;
        RoleViewStyle.roleAbility = null;
        this.drawPackTable = null;
    }

    public void getViewCurrentIndexByPointer() {
        for (int i = 0; i < 10; ++i) {
            if (DCanvas.getInstance().IsPointerDown(this.vGTable.getCell(IDefines.BOX_FIXED[i][0], IDefines.BOX_FIXED[i][1]).cell_x, this.vGTable.getCell(IDefines.BOX_FIXED[i][0], IDefines.BOX_FIXED[i][1]).cell_y, 20, 20)) {
                if (MenuUI.getInstance().bytMenuState != -6) {
                    MenuUI.getInstance().setFocus(0);
                }
                this.viewCurrentIndex = (byte) i;
                MenuUI.getInstance().setSelectedButton((Param.getInstance().oSelectRole == null) ? Param.getInstance().hPackageEquip : Param.getInstance().hOtherPackageEquip, this.viewCurrentIndex);
                this.setPopupDialog();
            }
        }
        if (DCanvas.getInstance().IsPointerDown(this.vGTable.getCell(4, 2).cell_x, this.vGTable.getCell(4, 2).cell_y + this.vGTable.getCell(4, 2).cell_h - 20, 20, 20)) {
            MenuUI.getInstance().keyNum1Event();
        } else if (DCanvas.getInstance().IsPointerDown(this.vGTable.getCell(4, 4).cell_x + this.vGTable.getCell(4, 4).cell_w - 20, this.vGTable.getCell(4, 2).cell_y + this.vGTable.getCell(4, 2).cell_h - 20, 20, 20)) {
            MenuUI.getInstance().keyNum3Event();
        }
    }

    public void getViewCurrentKeyUp() {
        if (this.viewFocuse) {
            switch (this.viewCurrentIndex) {
                case 1:
                case 2:
                case 3:
                case 5:
                case 6:
                case 7: {
                    --this.viewCurrentIndex;
                    break;
                }
            }
            this.setPopupDialog();
        }
    }

    public void getViewCurrentKeyDown() {
        if (this.viewFocuse) {
            switch (this.viewCurrentIndex) {
                case 0:
                case 1:
                case 2:
                case 4:
                case 5:
                case 6: {
                    ++this.viewCurrentIndex;
                }
                case 8: {
                    if (this.blnShowEquip) {
                        this.viewCurrentIndex = 0;
                        break;
                    }
                    break;
                }
                case 9: {
                    if (this.blnShowEquip) {
                        this.viewCurrentIndex = 4;
                        break;
                    }
                    break;
                }
            }
            this.setPopupDialog();
        }
    }

    public void getViewCurrentKeyLeft() {
        if (this.viewFocuse) {
            switch (this.viewCurrentIndex) {
                case 4: {
                    this.viewCurrentIndex = 9;
                    break;
                }
                case 5:
                case 6:
                case 7: {
                    this.viewCurrentIndex -= 4;
                    break;
                }
                case 8: {
                    if (this.blnShowEquip) {
                        this.viewCurrentIndex = 0;
                        break;
                    }
                    break;
                }
                case 9: {
                    this.viewCurrentIndex = 8;
                    break;
                }
            }
            this.setPopupDialog();
        }
    }

    public void getViewCurrentKeyRight() {
        if (this.viewFocuse) {
            switch (this.viewCurrentIndex) {
                case 0: {
                    this.viewCurrentIndex = 8;
                    break;
                }
                case 1:
                case 2:
                case 3: {
                    this.viewCurrentIndex += 4;
                    break;
                }
                case 8: {
                    this.viewCurrentIndex = 9;
                    break;
                }
                case 9: {
                    if (this.blnShowEquip) {
                        this.viewCurrentIndex = 4;
                        break;
                    }
                    break;
                }
            }
            this.setPopupDialog();
        }
    }

    private void setPopupDialog() {
        if (this.drawPackTable != null && this.drawPackTable.containsKey(new Integer(this.viewCurrentIndex))) {
            PackageObject po = (PackageObject) this.drawPackTable.get(new Integer(this.viewCurrentIndex));
            if (po.bytQuality >= 0) {
                Param.popupDialogInstance.setTitleColor(true, Macro.INT_PROP_COLOR[po.bytQuality]);
            } else {
                Param.popupDialogInstance.setTitleColor(false, 0);
            }
            Param.popupDialogInstance.setPopupDialog(new String[]{po.strName}, (short) (this.vGTable.getCell(IDefines.BOX_FIXED[this.viewCurrentIndex][0], IDefines.BOX_FIXED[this.viewCurrentIndex][1]).cell_x + 20), (short) (this.vGTable.getCell(IDefines.BOX_FIXED[this.viewCurrentIndex][0], IDefines.BOX_FIXED[this.viewCurrentIndex][1]).cell_y + 20));
            this.blnDrawPopup = true;
        } else {
            this.blnDrawPopup = false;
        }
    }

    public byte getViewCurrentIndex() {
        return this.viewCurrentIndex;
    }
}
