// 
// Decompiled by Procyon v0.5.36
// 
package face;

import javax.microedition.lcdui.Image;
import common.IResConfig;
import model.Map;
import model.TeamObject;
import means.Rms;
import means.Wap;
import network.NetManager;
import network.NetParse;
import game.CMidlet;
import means.StringManager;
import java.util.Vector;
import base.GameControl;
import means.ChatContent;
import java.util.Hashtable;
import network.NetSend;
import common.LayoutStyle;
import model.ORPMe;
import means.ImageManager;
import common.DrawBase;
import common.IDefines;
import model.PackageObject;
import base.Macro;
import base.DCanvas;
import javax.microedition.lcdui.Graphics;
import base.Param;
import means.QSprite;
import common.PackageBox;
import common.GridTable;
import model.MailObject;
import model.MenuObject;
import model.SkillObject;

public class DialogUI implements UIbase {

    public static DialogUI du;
    public static final byte dealBoxNum = 6;
    private final String STRING_TITLE_DEAL;
    private final String STRING_TITLE_ALLOT;
    public final String STRING_CUE_DEAL;
    public final String STRING_OPTION_SEE;
    public final String STRING_INPUT_GOLE;
    public final String STRING_ADD_GOODS;
    public final String STRING_CONF_DEAL;
    public final String STRING_OPTION_DETAIL;
    public final String[] STRING_OPTION_ALLOT;
    private boolean blnIsThis;
    public byte bytDealUiType;
    private String strDialog;
    public String[] strContent;
    private String[] strEquipContent;
    public byte bytDialogState;
    public byte bytDialogSubState;
    public byte bytRectState;
    public byte bytDialogMove;
    public byte bytDialogRoll;
    private byte bytDialogMaxH;
    private byte bytFreamIndex;
    private byte[] bytButtonType;
    private String Titlestr;
    private int intTitleColor;
    private int intMyPropColor;
    private int intDialogTime;
    public byte bytDialogType;
    public TwoDialogUI twodialog;
    private GridTable dealBackTable;
    private GridTable dealTable;
    public PackageBox dealMyBox;
    public PackageBox dealPeerBox;
    public byte dealState;
    public final byte DEAL_MY_BOX;
    public final byte DEAL_PEER_BOX;
    public final byte DEAL_INPUT_MONEY;
    private final byte DEAL_PERSONAL_TAB;
    public final byte DEAL_PERSONAL_PACKAGE_BOX;
    public final String DEAL_LOOK_PROPERTY;
    public final String DEAL_ADD_GOODS;
    public final String DEAL_REPEAL_GOODS;
    public final String DEAL_INPUT_MONEY_STR;
    public final String DEAL_LOCK;
    public final String DEAL_OK;
    QSprite dealQs;
    public byte dealPackageTabIndex;
    public boolean dealIsMyLock;
    public boolean dealIsPeerLock;
    int y_Offset;
    private byte bytWordMoveType;
    private byte bytWordMoveStep;
    private short shtWordMoveUpY;
    private short shtWordMoveDownY;
    private short shtWordMoveArea;
    private short shtEquipRectW;
    private short shtEquipRectH;

    public DialogUI() {
        this.STRING_TITLE_DEAL = "Giao dịch";
        this.STRING_TITLE_ALLOT = "Phân bổ mục";
        this.STRING_CUE_DEAL = "Nhập số tiền giao dịch";
        this.STRING_OPTION_SEE = "Chi tiết";
        this.STRING_INPUT_GOLE = "Nhập tiền vàng";
        this.STRING_ADD_GOODS = "Thêm mục";
        this.STRING_CONF_DEAL = "Xác nhận giao dịch";
        this.STRING_OPTION_DETAIL = "Chi tiết nâng cao";
        this.STRING_OPTION_ALLOT = new String[]{"Lắc", "Từ bỏ"};
        this.blnIsThis = false;
        this.strDialog = "";
        this.Titlestr = "";
        this.DEAL_MY_BOX = 0;
        this.DEAL_PEER_BOX = 1;
        this.DEAL_INPUT_MONEY = 2;
        this.DEAL_PERSONAL_TAB = 3;
        this.DEAL_PERSONAL_PACKAGE_BOX = 4;
        this.DEAL_LOOK_PROPERTY = "Xem thuộc tính";
        this.DEAL_ADD_GOODS = "Thêm mục";
        this.DEAL_REPEAL_GOODS = "Thu hồi hàng hóa";
        this.DEAL_INPUT_MONEY_STR = "Nhập tiền vàng";
        this.DEAL_LOCK = "Giao dịch bị khóa";
        this.DEAL_OK = "Xác định giao dịch";
        this.dealIsMyLock = false;
        this.dealIsPeerLock = false;
        this.y_Offset = 0;
        DialogUI.du = this;
    }

    public static DialogUI getInstance() {
        return DialogUI.du;
    }

    public void init() {
        this.intMyPropColor = 0;
        this.bytButtonType = new byte[3];
    }

    public void clean() {
        DialogUI.du = null;
        Param.lngCountDownStartTime = -1L;
        Param.lngCountDownMaxTime = -1L;
        Param.lngCountDownLeftTime = -1L;
        Param.strCountDownTxt = "";
    }

    public void isThis(final boolean _type) {
        this.blnIsThis = _type;
    }

    public void setButton(final int _type, final int _leftword, final int _rightword) {
        this.bytButtonType[0] = (byte) _type;
        this.bytButtonType[1] = (byte) _leftword;
        this.bytButtonType[2] = (byte) _rightword;
    }

    public void paint(final Graphics g) {
        if (FullInfo.getInstance() != null) {
            FullInfo.getInstance().paint(g);
        } else {
            switch (this.bytRectState) {
                case 2: {
                    this.twodialog.paint(g);
                    DCanvas.getInstance().drawSoftkey2(g, this.bytButtonType[0], this.bytButtonType[1], this.bytButtonType[2], this.blnIsThis, (Macro.SCREEN_WIDTH - this.twodialog.bytRectWidth) / 2 - (Macro.FONTW >> 1) - 2, (Macro.SCREEN_HEIGHT - this.twodialog.shtRectHeight) / 2 + this.twodialog.shtRectHeight - Macro.FONTHEIGHT - 2, this.twodialog.bytRectWidth + Macro.FONTW + 4);
                    break;
                }
                case 3: {
                    this.twodialog.paint(g);
                    DCanvas.getInstance().drawSoftkey2(g, this.bytButtonType[0], this.bytButtonType[1], this.bytButtonType[2], this.blnIsThis, this.twodialog.BackGroundX, this.twodialog.BackGroundY + this.twodialog.BackGroundH - Macro.FONTHEIGHT - 2, this.twodialog.BackGroundW);
                    break;
                }
                case -3: {
                    DCanvas.getInstance().drawCueRect(g, this.shtEquipRectW, this.shtEquipRectH);
                    final short _tempx = (short) ((Macro.SCREEN_WIDTH - this.shtEquipRectW) / 2 + 16);
                    final short _tempy = (short) ((Macro.SCREEN_HEIGHT - this.shtEquipRectH) / 2 + 8);
                    if (Param.getInstance().intsDealRgbColor != null) {
                        g.drawRGB(Param.getInstance().intsDealRgbColor, 0, this.shtEquipRectW - 28, _tempx - 4, _tempy + this.bytDialogMove * (Macro.FONTHEIGHT + 12), this.shtEquipRectW - 28, Macro.FONTHEIGHT + 8, true);
                    }
                    if (Param.getInstance().vEquipDialog.size() > this.bytDialogMaxH) {
                        for (byte i = 0; i < this.bytDialogMaxH; ++i) {
                            this.drawAllotImageOption(g, ((PackageObject) Param.getInstance().vEquipDialog.elementAt(i + this.bytDialogRoll)), _tempx, (short) (_tempy + i * (Macro.FONTHEIGHT + 12)), (byte) 20);
                        }
                    } else {
                        for (byte i = 0; i < Param.getInstance().vEquipDialog.size(); ++i) {
                            this.drawAllotImageOption(g, ((PackageObject) Param.getInstance().vEquipDialog.elementAt(i)), _tempx, (short) (_tempy + i * (Macro.FONTHEIGHT + 12)), (byte) 20);
                        }
                    }
                    final short _tempSpoorx = (short) ((Macro.SCREEN_WIDTH + this.shtEquipRectW) / 2 - 14);
                    final short _ey = (short) ((Macro.SCREEN_HEIGHT + this.shtEquipRectH) / 2 - 8);
                    DCanvas.getInstance().setOptionSpoolr(g, _tempSpoorx, _tempy, _ey, this.bytDialogMaxH, Param.getInstance().vEquipDialog.size(), (byte) (this.bytDialogMove + this.bytDialogRoll), true);
                    if (this.bytButtonType[1] == 100) {
                        GameUI.getInstance().drawRectTypeA(g, "", this.strDialog, this.intMyPropColor, "");
                        g.setColor(this.intTitleColor);
                        g.drawString(this.Titlestr, Macro.SCREEN_WIDTH / 2, 2, 17);
                        this.drawWordMove_Y(g, (short) (Macro.shtRectHeight - 26));
                    }
                    DCanvas.getInstance().drawSoftkey(g, this.bytButtonType[0], this.bytButtonType[1], this.bytButtonType[2], this.blnIsThis);
                    break;
                }
                case 9: {
                    this.drawDeal();
                    if (this.twodialog != null) {
                        this.twodialog.paint(g);
                    }
                    DCanvas.getInstance().drawSoftkey(g, this.bytButtonType[0], this.bytButtonType[1], this.bytButtonType[2], this.blnIsThis);
                    Param.popupDialogInstance.drawPopupDialog(g);
                    break;
                }
            }
        }
    }

    private void initDeal() {
        final short backTableW = (short) (Macro.SCREEN_WIDTH - 28);
        final short backTableH = (short) (Macro.SCREEN_HEIGHT - 46 - 47);
        this.dealBackTable = new GridTable((short) 14, (short) 46, backTableW, backTableH, (short) 3, (short) 1, new short[]{50, 17, 31}, new short[]{100});
        final short dealTableX = (short) (this.dealBackTable.getCell(1, 1).getCellX() + 2);
        final short dealTableY = (short) (this.dealBackTable.getCell(1, 1).getCellY() + 2);
        final short dealTableW = (short) (this.dealBackTable.getCell(1, 1).getCellW() - 4);
        final short dealTableH = (short) (this.dealBackTable.getCell(1, 1).getCellH() - 4);
        this.y_Offset = 0;
        this.dealTable = new GridTable(dealTableX, dealTableY, dealTableW, dealTableH, (short) 3, (short) 3, new short[]{25, 48, 15}, new short[]{38, 24, 38});
        final int dealBoxW = 70;
        DrawBase.drawBox(this.dealTable.getCell(2, 1).getCellX(), this.dealTable.getCell(2, 1).getCellY(), this.dealTable.getCell(2, 1).getCellW(), this.dealTable.getCell(2, 1).getCellH(), IDefines.DIALOG_UI_DEAL_BOX_BACK_BORDER, true);
        final int myDealBoxX = this.dealTable.getCell(2, 1).getCellX() + (this.dealTable.getCell(2, 1).getCellW() - dealBoxW >> 1);
        final int myDealBoxY = this.dealTable.getCell(2, 1).getCellY() + (this.dealTable.getCell(2, 1).getCellH() - 46 >> 1);
        this.dealMyBox = new PackageBox(myDealBoxX, myDealBoxY + (this.y_Offset >> 1) + 2, dealBoxW, this.dealTable.getCell(2, 1).getCellH(), 6, false);
        final int peerDealBoxX = this.dealTable.getCell(2, 3).getCellX() + (this.dealTable.getCell(2, 3).getCellW() - dealBoxW >> 1);
        final int peerDealBoxY = this.dealTable.getCell(2, 3).getCellY() + (this.dealTable.getCell(2, 3).getCellH() - 46 >> 1);
        this.dealPeerBox = new PackageBox(peerDealBoxX, peerDealBoxY + (this.y_Offset >> 1) + 2, dealBoxW, this.dealTable.getCell(2, 3).getCellH(), 6, false);
        MenuUI.getInstance().tabStyleInstance.initTabStyle(this.dealBackTable.getCell(2, 1).getCellX(), (short) (this.dealBackTable.getCell(2, 1).getCellY() + 5), this.dealBackTable.getCell(2, 1).getCellW(), (short) 30, new String[]{"\u88c5\u5907", "\u836f\u6c34", "\u6750\u6599", "\u5b9d\u7269"}, (byte) 0);
        final int personalPackageX = this.dealBackTable.getCell(3, 1).getCellX();
        final int personalPackageY = this.dealBackTable.getCell(3, 1).getCellY() + 4;
        Param.getInstance().personalPackage = new PackageBox(personalPackageX, personalPackageY + (this.y_Offset >> 1) + 2, this.dealBackTable.getCell(3, 1).getCellW(), this.dealBackTable.getCell(3, 1).getCellH(), Param.getInstance().packageBoxMaxNum, true);
        this.dealQs = ImageManager.loadSpriteById(1, "deal", "deal", "deal", "ui");
        this.setButton(1, 4, 2);
        this.dealState = 4;
    }

    private void cleanDeal() {
        this.dealQs = null;
        this.dealPeerBox = null;
        this.dealMyBox = null;
        this.dealTable = null;
        this.dealBackTable = null;
        Param.getInstance().vDealDuiProp = null;
        Param.getInstance().vDealMyProp = null;
    }

    private void drawDeal() {
        DCanvas.getInstance().drawTileTextBG(DCanvas.gameG, "\u4ea4\u3000\u3000\u6613");
        DrawBase.drawBox(this.dealBackTable.getCell(1, 1).getCellX(), this.dealBackTable.getCell(1, 1).getCellY(), this.dealBackTable.getCell(1, 1).getCellW(), this.dealBackTable.getCell(1, 1).getCellH(), IDefines.DIALOG_UI_DEAL_AREA_BACK_BORDER, true);
        DrawBase.drawString(ORPMe.ME.strNickName, this.dealTable.getCell(1, 1).getCellX(), this.dealTable.getCell(1, 1).getCellY(), 8142636, 0);
        DrawBase.drawBox(this.dealTable.getCell(2, 1).getCellX(), this.dealTable.getCell(2, 1).getCellY(), this.dealTable.getCell(2, 1).getCellW(), this.dealTable.getCell(2, 1).getCellH(), IDefines.DIALOG_UI_DEAL_BOX_BACK_BORDER, true);
        this.dealMyBox.draw(Param.getInstance().vDealMyProp);
        GameUI.getInstance().drawInputRect(DCanvas.gameG, Param.getInstance().intDealMyGold, this.dealTable.getCell(3, 1).getCellX(), this.dealTable.getCell(3, 1).getCellY() + 7, this.dealTable.getCell(3, 1).getCellW(), this.dealTable.getCell(3, 1).getCellH(), true);
        if (this.dealState == 2) {
            LayoutStyle.getInstance().drawChooseFrame(DCanvas.gameG, this.dealTable.getCell(3, 1).getCellX(), this.dealTable.getCell(3, 1).getCellY() + 7, this.dealTable.getCell(3, 1).getCellW(), this.dealTable.getCell(3, 1).getCellH());
        }
        if (this.dealIsMyLock) {
            DrawBase.drawRGB(DCanvas.gameG, (byte) 0, this.dealTable.getCell(2, 1).getCellX(), this.dealBackTable.getCell(1, 1).getCellY(), this.dealTable.getCell(2, 1).getCellW(), this.dealBackTable.getCell(1, 1).getCellH());
            DrawBase.drawRect(this.dealTable.getCell(2, 1).getCellX(), this.dealBackTable.getCell(1, 1).getCellY(), this.dealTable.getCell(2, 1).getCellW(), this.dealBackTable.getCell(1, 1).getCellH(), 8050176);
        }
        DrawBase.drawString(Param.getInstance().strDealName, this.dealTable.getCell(1, 3).getCellX(), this.dealTable.getCell(1, 3).getCellY(), 8142636, 0);
        DrawBase.drawBox(this.dealTable.getCell(2, 3).getCellX(), this.dealTable.getCell(2, 3).getCellY(), this.dealTable.getCell(2, 3).getCellW(), this.dealTable.getCell(2, 3).getCellH(), IDefines.DIALOG_UI_DEAL_BOX_BACK_BORDER, true);
        this.dealPeerBox.draw(Param.getInstance().vDealDuiProp);
        GameUI.getInstance().drawInputRect(DCanvas.gameG, Param.getInstance().intDealGold, this.dealTable.getCell(3, 3).getCellX(), this.dealTable.getCell(3, 3).getCellY() + 7, this.dealTable.getCell(3, 3).getCellW(), this.dealTable.getCell(3, 3).getCellH(), true);
        if (this.dealIsPeerLock) {
            DrawBase.drawRGB(DCanvas.gameG, (byte) 0, this.dealTable.getCell(2, 3).getCellX(), this.dealBackTable.getCell(1, 1).getCellY(), this.dealTable.getCell(2, 3).getCellW(), this.dealBackTable.getCell(1, 1).getCellH());
            DrawBase.drawRect(this.dealTable.getCell(2, 3).getCellX(), this.dealBackTable.getCell(1, 1).getCellY(), this.dealTable.getCell(2, 3).getCellW(), this.dealBackTable.getCell(1, 1).getCellH(), 8050176);
        }
        this.dealQs.drawAnimationFrame(DCanvas.gameG, 0, 0, this.dealTable.getCell(2, 2).getCenterX(), this.dealTable.getCell(2, 2).getCenterY());
        MenuUI.getInstance().tabStyleInstance.drawTabStyle(DCanvas.gameG);
        DrawBase.drawBox(this.dealBackTable.getCell(3, 1).getCellX(), this.dealBackTable.getCell(3, 1).getCellY() + this.y_Offset, this.dealBackTable.getCell(3, 1).getCellW(), this.dealBackTable.getCell(3, 1).getCellH(), IDefines.DIALOG_UI_DEAL_AREA_BACK_BORDER, true);
        Param.getInstance().personalPackage.draw(Param.getInstance().hPackage);
        MenuUI.getInstance().drawMoney(DCanvas.gameG, ORPMe.Gold, -1, Macro.SCREEN_HEIGHT - 18, true);
    }

    private void updateDeal(final byte event) {
        String[] optionStr = null;
        Label_1109:
        {
            switch (this.dealState) {
                case 0: {
                    switch (event) {
                        case 5: {
                            if (this.dealIsMyLock) {
                                optionStr = new String[]{"\u786e\u5b9a\u4ea4\u6613"};
                                break;
                            }
                            if (this.dealIsPeerLock) {
                                optionStr = new String[]{"\u9501\u5b9a\u4ea4\u6613", "\u786e\u5b9a\u4ea4\u6613"};
                                break;
                            }
                            if (this.dealMyBox.getCellBoxIsBedata(Param.getInstance().vDealMyProp)) {
                                optionStr = new String[]{"\u67e5\u770b\u5c5e\u6027", "\u64a4\u9500\u7269\u54c1", "\u9501\u5b9a\u4ea4\u6613", "\u786e\u5b9a\u4ea4\u6613"};
                                break;
                            }
                            optionStr = new String[]{"\u9501\u5b9a\u4ea4\u6613", "\u786e\u5b9a\u4ea4\u6613"};
                            break;
                        }
                        case 1: {
                            this.dealMyBox.getKeyUp();
                            break;
                        }
                        case 2: {
                            if (this.dealMyBox.getLineIndex() < this.dealMyBox.getLineNum() - 1) {
                                this.dealMyBox.getKeyDown();
                                break;
                            }
                            this.dealMyBox.setFocal(false);
                            MenuUI.getInstance().tabStyleInstance.TabFocuse = false;
                            this.dealState = 2;
                            break;
                        }
                        case 3: {
                            this.dealMyBox.getKeyLeft();
                            break;
                        }
                        case 4: {
                            if (this.dealMyBox.getColumnsIndex() < this.dealMyBox.getColumnsNum() - 1) {
                                this.dealMyBox.getKeyRight();
                                break;
                            }
                            this.dealMyBox.setFocal(false);
                            this.dealPeerBox.setFocal(true);
                            this.dealState = 1;
                            break;
                        }
                    }
                    break;
                }
                case 1: {
                    switch (event) {
                        case 5: {
                            if (this.dealIsMyLock) {
                                optionStr = new String[]{"\u786e\u5b9a\u4ea4\u6613"};
                                break;
                            }
                            if (this.dealIsPeerLock) {
                                optionStr = new String[]{"\u9501\u5b9a\u4ea4\u6613", "\u786e\u5b9a\u4ea4\u6613"};
                                break;
                            }
                            if (this.dealPeerBox.getCellBoxIsBedata(Param.getInstance().vDealDuiProp)) {
                                optionStr = new String[]{"\u67e5\u770b\u5c5e\u6027", "\u9501\u5b9a\u4ea4\u6613", "\u786e\u5b9a\u4ea4\u6613"};
                                break;
                            }
                            optionStr = new String[]{"\u9501\u5b9a\u4ea4\u6613", "\u786e\u5b9a\u4ea4\u6613"};
                            break;
                        }
                        case 1: {
                            this.dealPeerBox.getKeyUp();
                            break;
                        }
                        case 2: {
                            if (this.dealPeerBox.getLineIndex() < this.dealPeerBox.getLineNum() - 1) {
                                this.dealPeerBox.getKeyDown();
                                break;
                            }
                            this.dealPeerBox.setFocal(false);
                            MenuUI.getInstance().tabStyleInstance.TabFocuse = false;
                            this.dealState = 2;
                            break;
                        }
                        case 3: {
                            if (this.dealPeerBox.getColumnsIndex() > 0) {
                                this.dealPeerBox.getKeyLeft();
                                break;
                            }
                            this.dealPeerBox.setFocal(false);
                            this.dealMyBox.setFocal(true);
                            this.dealState = 0;
                            break;
                        }
                        case 4: {
                            this.dealPeerBox.getKeyRight();
                            break;
                        }
                    }
                    break;
                }
                case 2: {
                    switch (event) {
                        case 5: {
                            if (this.dealIsMyLock) {
                                optionStr = new String[]{"\u786e\u5b9a\u4ea4\u6613"};
                                break;
                            }
                            if (this.dealIsPeerLock) {
                                optionStr = new String[]{"\u9501\u5b9a\u4ea4\u6613", "\u786e\u5b9a\u4ea4\u6613"};
                                break;
                            }
                            optionStr = new String[]{"\u8f93\u5165\u91d1\u5e01", "\u9501\u5b9a\u4ea4\u6613", "\u786e\u5b9a\u4ea4\u6613"};
                            break;
                        }
                        case 1: {
                            this.dealMyBox.setFocal(true);
                            this.dealState = 0;
                            break;
                        }
                        case 2: {
                            MenuUI.getInstance().tabStyleInstance.TabFocuse = true;
                            this.dealState = 3;
                            break;
                        }
                    }
                    break;
                }
                case 3: {
                    switch (event) {
                        case 5: {
                            if (this.dealIsMyLock) {
                                optionStr = new String[]{"\u786e\u5b9a\u4ea4\u6613"};
                                break;
                            }
                            optionStr = new String[]{"\u9501\u5b9a\u4ea4\u6613", "\u786e\u5b9a\u4ea4\u6613"};
                            break;
                        }
                        case 1: {
                            MenuUI.getInstance().tabStyleInstance.TabFocuse = false;
                            this.dealState = 2;
                            break;
                        }
                        case 2: {
                            MenuUI.getInstance().tabStyleInstance.TabFocuse = false;
                            Param.getInstance().personalPackage.setFocal(true);
                            this.dealState = 4;
                            break;
                        }
                        case 3: {
                            MenuUI.getInstance().tabStyleInstance.getTabCurrentKeyLeft();
                            break;
                        }
                        case 4: {
                            MenuUI.getInstance().tabStyleInstance.getTabCurrentKeyRight();
                            break;
                        }
                    }
                    this.setDealPackageMessage();
                    break;
                }
                case 4: {
                    switch (event) {
                        case 5: {
                            if (this.dealIsMyLock) {
                                optionStr = new String[]{"\u786e\u5b9a\u4ea4\u6613"};
                                break Label_1109;
                            }
                            if (this.dealIsPeerLock) {
                                optionStr = new String[]{"\u9501\u5b9a\u4ea4\u6613", "\u786e\u5b9a\u4ea4\u6613"};
                                break Label_1109;
                            }
                            if (Param.getInstance().personalPackage.getCellBoxIsBedata(Param.getInstance().hPackage)) {
                                optionStr = new String[]{"\u67e5\u770b\u5c5e\u6027", "\u6dfb\u52a0\u7269\u54c1", "\u9501\u5b9a\u4ea4\u6613", "\u786e\u5b9a\u4ea4\u6613"};
                                break Label_1109;
                            }
                            optionStr = new String[]{"\u9501\u5b9a\u4ea4\u6613", "\u786e\u5b9a\u4ea4\u6613"};
                            break Label_1109;
                        }
                        case 1: {
                            if (Param.getInstance().personalPackage.getLineIndex() > 0) {
                                Param.getInstance().personalPackage.getKeyUp();
                                break Label_1109;
                            }
                            Param.getInstance().personalPackage.setFocal(false);
                            MenuUI.getInstance().tabStyleInstance.TabFocuse = true;
                            this.dealState = 3;
                            break Label_1109;
                        }
                        case 2: {
                            if (Param.getInstance().personalPackage.getLineIndex() < Param.getInstance().personalPackage.getLineNum() - 1) {
                                Param.getInstance().personalPackage.getKeyDown();
                                break Label_1109;
                            }
                            break Label_1109;
                        }
                        case 3: {
                            Param.getInstance().personalPackage.getKeyLeft();
                            break Label_1109;
                        }
                        case 4: {
                            Param.getInstance().personalPackage.getKeyRight();
                            break Label_1109;
                        }
                    }
                    break;
                }
            }
        }
        if (optionStr != null && !optionStr.equals("")) {
            GameUI.getInstance().setTwoMenu((byte) 9, optionStr, (byte) 8);
        }
        if (event == 6) {
            this.cleanDeal();
        }
    }

    private void updateDealTouch() {
        if (DCanvas.getInstance().IsPointerDown(this.dealMyBox.getShowX(), this.dealMyBox.getShowY(), this.dealMyBox.getShowW(), this.dealMyBox.getShowH()) && this.dealState != 0) {
            this.dealState = 0;
            this.dealMyBox.setFocal(true);
            this.dealPeerBox.setFocal(false);
            MenuUI.getInstance().tabStyleInstance.TabFocuse = false;
            Param.getInstance().personalPackage.setFocal(false);
        }
        if (DCanvas.getInstance().IsPointerDown(this.dealPeerBox.getShowX(), this.dealPeerBox.getShowY(), this.dealPeerBox.getShowW(), this.dealPeerBox.getShowH())) {
            if (this.dealState != 1) {
                this.dealState = 1;
                this.dealMyBox.setFocal(false);
                this.dealPeerBox.setFocal(true);
                MenuUI.getInstance().tabStyleInstance.TabFocuse = false;
                Param.getInstance().personalPackage.setFocal(false);
            }
        } else if (DCanvas.getInstance().IsPointerDown(this.dealTable.getCell(3, 1).getCellX(), this.dealTable.getCell(3, 1).getCellY(), this.dealTable.getCell(3, 1).getCellW(), this.dealTable.getCell(3, 1).getCellH())) {
            if (this.dealState != 2) {
                this.dealState = 2;
                this.dealMyBox.setFocal(false);
                this.dealPeerBox.setFocal(false);
                MenuUI.getInstance().tabStyleInstance.TabFocuse = false;
                Param.getInstance().personalPackage.setFocal(false);
            } else {
                this.leftEvent();
            }
        } else if (DCanvas.getInstance().IsPointerDown(this.dealBackTable.getCell(2, 1).getCellX(), this.dealBackTable.getCell(2, 1).getCellY(), this.dealBackTable.getCell(2, 1).getCellW(), this.dealBackTable.getCell(2, 1).getCellH())) {
            if (this.dealState != 3) {
                this.dealState = 3;
                this.dealMyBox.setFocal(false);
                this.dealPeerBox.setFocal(false);
                MenuUI.getInstance().tabStyleInstance.TabFocuse = true;
                Param.getInstance().personalPackage.setFocal(false);
            }
            MenuUI.getInstance().tabStyleInstance.getTabCurrentIndexByPointer();
            this.setDealPackageMessage();
        } else if (DCanvas.getInstance().IsPointerDown(Param.getInstance().personalPackage.getShowX(), Param.getInstance().personalPackage.getShowY(), Param.getInstance().personalPackage.getShowW(), Param.getInstance().personalPackage.getShowH()) && this.dealState != 4) {
            this.dealState = 4;
            this.dealMyBox.setFocal(false);
            this.dealPeerBox.setFocal(false);
            MenuUI.getInstance().tabStyleInstance.TabFocuse = false;
            Param.getInstance().personalPackage.setFocal(true);
        }
        this.dealPeerBox.getTouch();
        this.dealMyBox.getTouch();
        if (this.dealState == 4) {
            Param.getInstance().personalPackage.getTouch();
        }
    }

    public void setDealPackageMessage() {
        if (this.dealPackageTabIndex != MenuUI.getInstance().tabStyleInstance.getTabCurrentIndex()) {
            switch (this.dealPackageTabIndex = MenuUI.getInstance().tabStyleInstance.getTabCurrentIndex()) {
                case 0: {
                    DCanvas.getInstance().setNetLoad(true);
                    Param.getInstance().bytDealGoods = 0;
                    NetSend.getInstance().sendDealBegPack(Param.getInstance().intDealID, Param.getInstance().bytDealGoods);
                    break;
                }
                case 1: {
                    DCanvas.getInstance().setNetLoad(true);
                    Param.getInstance().bytDealGoods = 1;
                    NetSend.getInstance().sendDealBegPack(Param.getInstance().intDealID, Param.getInstance().bytDealGoods);
                    break;
                }
                case 2: {
                    DCanvas.getInstance().setNetLoad(true);
                    Param.getInstance().bytDealGoods = 2;
                    NetSend.getInstance().sendDealBegPack(Param.getInstance().intDealID, Param.getInstance().bytDealGoods);
                    break;
                }
                case 3: {
                    DCanvas.getInstance().setNetLoad(true);
                    Param.getInstance().bytDealGoods = 3;
                    NetSend.getInstance().sendDealBegPack(Param.getInstance().intDealID, Param.getInstance().bytDealGoods);
                    break;
                }
            }
        }
    }

    public void addDealProp(final Hashtable _hashtable, final PackageObject _po, final short _number) {
        final PackageObject _dealpo = new PackageObject();
        _dealpo.strName = _po.strName;
        _dealpo.shtIcon = _po.shtIcon;
        GameUI.getInstance().addObjectImg(_dealpo.shtIcon, "prop");
        _dealpo.shtPOnum = _number;
        _dealpo.intColor = _po.intColor;
        _dealpo.strInfo = _po.strInfo;
        _dealpo.byteEquipType = _po.byteEquipType;
        _dealpo.shtLevel = _po.shtLevel;
        _dealpo.bytQuality = _po.bytQuality;
        _dealpo.shtStaminaMax = _po.shtStaminaMax;
        _dealpo.bytOperation = _po.bytOperation;
        _dealpo.bytIsBind = _po.bytIsBind;
        _dealpo.intPrice = _po.intPrice;
        _dealpo.shtStamina = _po.shtStamina;
        _dealpo.bytSeal = _po.bytSeal;
        _dealpo.byteEquipPart = _po.byteEquipPart;
        _dealpo.bytsAttribute = _po.bytsAttribute;
        _dealpo.shtsAttributeInfo = _po.shtsAttributeInfo;
        if (_po.byteEquipType >= 0) {
            _dealpo.bytsAggrandizement = _po.bytsAggrandizement;
        }
        _dealpo.intId = _po.intId;
        for (byte i = 0; i < 6; ++i) {
            if (!_hashtable.containsKey(new Integer(i))) {
                _dealpo.intPOindex = i;
                _hashtable.put(new Integer(i), _dealpo);
                break;
            }
        }
    }

    public void clearTwoDialog() {
        this.twodialog = null;
        this.setButton(0, 4, 2);
    }

    private void setWordMove() {
        this.bytWordMoveType = 1;
        this.intDialogTime = 3000;
        this.shtWordMoveUpY = 0;
        this.shtWordMoveDownY = 0;
        this.bytWordMoveStep = 2;
        this.shtWordMoveArea = (short) (Macro.shtRectHeight - 26);
    }

    private void drawWordMove_Y(final Graphics g, final short _h) {
        if (this.strContent == null) {
            return;
        }
        final short _tempy0 = (short) (Macro.FONTHEIGHT + 16);
        g.setClip(0, (int) _tempy0, (int) Macro.SCREEN_WIDTH, (int) _h);
        GameUI.getInstance().drawWordMove(g, this.strContent, 12, _tempy0 + this.shtWordMoveUpY, (byte) 20);
        DCanvas.getInstance().clearScreen();
        if (!this.strDialog.equals("") && Param.getInstance().blnColorInfo) {
            final short _tempy2 = (short) ((Macro.FONTHEIGHT + 3) * 2 + Macro.shtRectHeight + 3 + 13);
            g.setClip(0, (int) _tempy2, (int) Macro.SCREEN_WIDTH, _h + 3);
            this.drawWordMove_Y_Color(g, _tempy2, _h);
            DCanvas.getInstance().clearScreen();
        }
    }

    public void drawWordMove_Y_Color(final Graphics g, final short _y, final short _h) {
        g.setClip(0, (int) _y, (int) Macro.SCREEN_WIDTH, _h + 2);
        for (byte i = 0; i < GameUI.getInstance().vsColorInfo.length; ++i) {
            int _x = 12;
            final int _yy = _y + i * Macro.FONTHEIGHT + this.shtWordMoveDownY;
            for (byte j = 0; j < GameUI.getInstance().vsColorInfo[i].size(); ++j) {
                final int _color = ((ChatContent) GameUI.getInstance().vsColorInfo[i].elementAt(j)).fontColor;
                final String _name = ((ChatContent) GameUI.getInstance().vsColorInfo[i].elementAt(j)).character;
                g.setColor(_color);
                g.drawString(_name, _x, _yy, 20);
                _x += Macro.font.stringWidth(_name);
            }
        }
        DCanvas.getInstance().clearScreen();
    }

    private void logicWordMove(final int _time) {
        if (this.bytWordMoveType == 1) {
            if (this.intDialogTime > 0) {
                this.intDialogTime -= _time;
            } else {
                this.bytWordMoveType = 2;
            }
        } else if (this.bytWordMoveType == 2) {
            if (this.strContent != null && this.strContent.length * Macro.FONTHEIGHT > this.shtWordMoveArea) {
                this.shtWordMoveUpY -= this.bytWordMoveStep;
                if (this.shtWordMoveUpY < -(this.strContent.length * Macro.FONTHEIGHT)) {
                    this.shtWordMoveUpY = this.shtWordMoveArea;
                }
            }
            if (this.intDialogTime <= 0 && GameUI.getInstance().vsColorInfo != null && GameUI.getInstance().vsColorInfo.length * Macro.FONTHEIGHT > this.shtWordMoveArea) {
                this.shtWordMoveDownY -= this.bytWordMoveStep;
                if (this.shtWordMoveDownY < -(GameUI.getInstance().vsColorInfo.length * Macro.FONTHEIGHT)) {
                    this.shtWordMoveDownY = this.shtWordMoveArea;
                }
            }
        }
    }

    public byte getMove() {
        return (byte) (this.bytDialogMove + this.bytDialogRoll);
    }

    public void logic(final int _time) {
        if (!this.blnIsThis) {
            return;
        }
        if (FullInfo.getInstance() != null) {
            FullInfo.getInstance().logic(_time);
        } else if (SeeDetail.getInstance() != null) {
            SeeDetail.getInstance().logic(_time);
            if (SeeDetail.getInstance() == null) {
                this.setButton(1, 7, 2);
            }
        } else {
            if (Param.lngCountDownMaxTime > 0L && (this.bytDialogState == 6 || this.bytDialogState == 102)) {
                if (Param.lngCountDownLeftTime > 0L) {
                    Param.lngCountDownLeftTime = System.currentTimeMillis() - Param.lngCountDownStartTime;
                    Param.lngCountDownLeftTime = Math.max(0L, (Param.lngCountDownMaxTime - Param.lngCountDownLeftTime) / 1000L);
                    final String timeout = String.valueOf(Param.lngCountDownLeftTime) + "(\u79d2)";
                    this.setTwoRect(String.valueOf(Param.strCountDownTxt) + timeout);
                }
                if (Param.lngCountDownLeftTime == 0L) {
                    if (this.bytDialogState == 6) {
                        this.leftEvent();
                    } else if (this.bytDialogState == 102) {
                        this.RightEvent();
                    }
                    Param.lngCountDownStartTime = -1L;
                    Param.lngCountDownLeftTime = -1L;
                    Param.lngCountDownMaxTime = -1L;
                }
            }
            switch (this.bytRectState) {
                case 2: {
                    if (this.bytDialogState == 13 || this.bytDialogState == 12 || this.bytDialogState == 11 || this.bytDialogState == 18) {
                        if (this.intDialogTime > 0) {
                            this.intDialogTime -= _time;
                        } else {
                            if (this.bytDialogState == 13) {
                                NetSend.getInstance().sendPlayerPk(Param.getInstance().intPkId, (byte) 3);
                            } else if (this.bytDialogState == 12) {
                                NetSend.getInstance().sendConsortiaInvite((byte) 0);
                            } else if (this.bytDialogState == 11) {
                                NetSend.getInstance().sendTeamInvite((byte) 0);
                            } else if (this.bytDialogState == 18) {
                                if (Param.getInstance().blnIsBaiShi) {
                                    NetSend.getInstance().sendShiTu((byte) 6, Param.getInstance().intShiTuID);
                                } else {
                                    NetSend.getInstance().sendShiTu((byte) 5, Param.getInstance().intShiTuID);
                                }
                            }
                            GameControl.getInstance().delUIbase(7);
                        }
                    }
                    if (DCanvas.getInstance().IsKeyRelease(131072)) {
                        this.RightEvent();
                        DCanvas.getInstance().buttonRightFlash = 0;
                    } else if (DCanvas.getInstance().isKeyDown(131072)) {
                        DCanvas.getInstance().buttonRightFlash = 1;
                    } else if (DCanvas.getInstance().IsKeyRelease(65536) || DCanvas.getInstance().IsKeyRelease(262144)) {
                        this.leftEvent();
                        DCanvas.getInstance().buttonLeftFlash = 0;
                    } else if (DCanvas.getInstance().isKeyDown(65536) || DCanvas.getInstance().IsKeyRelease(262144)) {
                        DCanvas.getInstance().buttonLeftFlash = 1;
                    } else {
                        this.doMeKey();
                    }
                    if (this.bytDialogState == 3) {
                        if (DCanvas.getInstance().isKeyDown(4096)) {
                            this.setBuffInfo(GameUI.getInstance().moveBuffIndex((byte) 1));
                            break;
                        }
                        if (DCanvas.getInstance().isKeyDown(16384)) {
                            this.setBuffInfo(GameUI.getInstance().moveBuffIndex((byte) 3));
                            break;
                        }
                        if (DCanvas.getInstance().isKeyDown(32768)) {
                            this.setBuffInfo(GameUI.getInstance().moveBuffIndex((byte) 4));
                            break;
                        }
                        if (DCanvas.getInstance().isKeyDown(8192)) {
                            this.setBuffInfo(GameUI.getInstance().moveBuffIndex((byte) 2));
                            break;
                        }
                        break;
                    } else {
                        if (this.bytDialogState != 10) {
                            break;
                        }
                        ++this.bytFreamIndex;
                        if (this.bytFreamIndex >= 8) {
                            this.bytFreamIndex = 0;
                            break;
                        }
                        break;
                    }
                }
                case 3: {
                    if (DCanvas.getInstance().IsKeyRelease(131072)) {
                        this.RightEvent();
                        DCanvas.getInstance().buttonRightFlash = 0;
                        break;
                    }
                    if (DCanvas.getInstance().isKeyDown(131072)) {
                        DCanvas.getInstance().buttonRightFlash = 1;
                        break;
                    }
                    if (DCanvas.getInstance().IsKeyRelease(65536) || DCanvas.getInstance().IsKeyRelease(262144)) {
                        this.leftEvent();
                        DCanvas.getInstance().buttonLeftFlash = 0;
                        break;
                    }
                    if (DCanvas.getInstance().isKeyDown(65536) || DCanvas.getInstance().IsKeyRelease(262144)) {
                        DCanvas.getInstance().buttonLeftFlash = 1;
                        break;
                    }
                    if (DCanvas.getInstance().isKeyDown(8192)) {
                        if (this.twodialog != null) {
                            this.twodialog.setUpDown((byte) 2);
                            break;
                        }
                        break;
                    } else {
                        if (DCanvas.getInstance().isKeyDown(4096) && this.twodialog != null) {
                            this.twodialog.setUpDown((byte) 1);
                            break;
                        }
                        break;
                    }
                }
                case -3: {
                    short _size = 0;
                    if (Param.getInstance().vEquipDialog != null) {
                        _size = (short) Param.getInstance().vEquipDialog.size();
                        for (short i = 0; i < _size; ++i) {
                            PackageObject _po = (PackageObject) Param.getInstance().vEquipDialog.elementAt(i);
                            if (_po.intPrice <= 0) {
                                this.delEquipDialog(i);
                                break;
                            }
                            final PackageObject packageObject = _po;
                            packageObject.intPrice -= _time;
                        }
                    }
                    if (this.bytButtonType[2] == 2) {
                        this.logicWordMove(_time);
                    }
                    if (DCanvas.getInstance().IsKeyRelease(65536) || DCanvas.getInstance().IsKeyRelease(262144)) {
                        this.leftEvent();
                        DCanvas.getInstance().buttonLeftFlash = 0;
                        break;
                    }
                    if (DCanvas.getInstance().isKeyDown(65536) || DCanvas.getInstance().isKeyDown(262144)) {
                        DCanvas.getInstance().buttonLeftFlash = 1;
                        break;
                    }
                    if (DCanvas.getInstance().IsKeyRelease(131072)) {
                        this.RightEvent();
                        DCanvas.getInstance().buttonRightFlash = 0;
                        break;
                    }
                    if (DCanvas.getInstance().isKeyDown(131072)) {
                        DCanvas.getInstance().buttonRightFlash = 1;
                        break;
                    }
                    if (DCanvas.getInstance().isKeyDown(4096)) {
                        if (this.bytDialogMove <= 0) {
                            this.bytDialogMove = 0;
                            --this.bytDialogRoll;
                            if (this.bytDialogRoll < 0) {
                                this.bytDialogRoll = (byte) ((this.bytDialogMaxH < _size) ? (_size - this.bytDialogMaxH) : 0);
                                this.bytDialogMove = (byte) ((this.bytDialogMaxH < _size) ? (this.bytDialogMaxH - 1) : (_size - 1));
                            }
                        } else {
                            --this.bytDialogMove;
                        }
                        DCanvas.getInstance().bytSpoolrFlash = 1;
                        break;
                    }
                    if (DCanvas.getInstance().isKeyDown(8192)) {
                        if (this.bytDialogMove < ((this.bytDialogMaxH < _size) ? (this.bytDialogMaxH - 1) : (_size - 1))) {
                            ++this.bytDialogMove;
                        } else {
                            this.bytDialogMove = (byte) (this.bytDialogMaxH - 1);
                            ++this.bytDialogRoll;
                            if (this.bytDialogRoll >= _size - this.bytDialogMaxH + 1) {
                                final byte b = 0;
                                this.bytDialogRoll = b;
                                this.bytDialogMove = b;
                            }
                        }
                        DCanvas.getInstance().bytSpoolrFlash = 2;
                        break;
                    }
                    if (DCanvas.getInstance().IsKeyHold(4)) {
                        if (this.bytButtonType[1] != 100 && this.bytDealUiType != 0) {
                            return;
                        }
                        this.intDialogTime = 0;
                        byte _w0 = -1;
                        byte _w2 = -1;
                        if (this.strContent != null) {
                            _w0 = (byte) this.strContent.length;
                        }
                        if (this.strEquipContent != null) {
                            _w2 = (byte) this.strEquipContent.length;
                        }
                        final short _y = (_w0 > _w2) ? this.shtWordMoveUpY : this.shtWordMoveDownY;
                        if (_y < 0) {
                            this.bytWordMoveStep = -4;
                            break;
                        }
                        if (_y >= 2) {
                            this.bytWordMoveStep = 2;
                            break;
                        }
                        this.bytWordMoveStep = 0;
                        break;
                    } else {
                        if (!DCanvas.getInstance().IsKeyHold(256)) {
                            this.doMeKey();
                            this.bytWordMoveStep = 2;
                            break;
                        }
                        if (this.bytButtonType[1] != 100 && this.bytDealUiType != 0) {
                            return;
                        }
                        this.intDialogTime = 0;
                        this.bytWordMoveStep = 4;
                        this.bytFreamIndex = 20;
                        break;
                    }
                }
                case 9: {
                    if (this.twodialog != null) {
                        this.twodialog.logicTwoRect();
                        break;
                    }
                    if (DCanvas.getInstance().IsKeyRelease(65536) || DCanvas.getInstance().IsKeyRelease(262144)) {
                        this.updateDeal((byte) 5);
                        DCanvas.getInstance().buttonLeftFlash = 0;
                        break;
                    }
                    if (DCanvas.getInstance().isKeyDown(65536) || DCanvas.getInstance().isKeyDown(262144)) {
                        DCanvas.getInstance().buttonLeftFlash = 1;
                        break;
                    }
                    if (DCanvas.getInstance().IsKeyRelease(131072)) {
                        this.RightEvent();
                        DCanvas.getInstance().buttonRightFlash = 0;
                        break;
                    }
                    if (DCanvas.getInstance().isKeyDown(131072)) {
                        DCanvas.getInstance().buttonRightFlash = 1;
                        break;
                    }
                    if (DCanvas.getInstance().isKeyDown(8192)) {
                        this.updateDeal((byte) 2);
                        break;
                    }
                    if (DCanvas.getInstance().isKeyDown(4096)) {
                        this.updateDeal((byte) 1);
                        break;
                    }
                    if (DCanvas.getInstance().isKeyDown(16384)) {
                        this.updateDeal((byte) 3);
                        break;
                    }
                    if (DCanvas.getInstance().isKeyDown(32768)) {
                        this.updateDeal((byte) 4);
                        break;
                    }
                    DCanvas.getInstance().isKeyDown(32);
                    break;
                }
            }
        }
        if (DCanvas.getInstance().blnIsTouch && DCanvas.getInstance().blnPointerPressed) {
            if (this.bytRectState == 3) {
                if (DCanvas.getInstance().PointerDwonSoftKey_Left1()) {
                    this.leftEvent();
                } else if (DCanvas.getInstance().PointerDwonSoftKey_Right1()) {
                    this.RightEvent();
                }
                switch (this.bytDialogState) {
                    case -53: {
                        if (this.twodialog != null) {
                            this.twodialog.pointerOption();
                            break;
                        }
                        break;
                    }
                }
            }
            if (this.bytRectState == 2) {
                if (this.bytDialogState != 10) {
                    if (DCanvas.getInstance().PointerDwonSoftKey_Left1()) {
                        this.leftEvent();
                    } else if (DCanvas.getInstance().PointerDwonSoftKey_Right1()) {
                        this.RightEvent();
                    }
                }
            } else if (DCanvas.getInstance().PointerDwonSoftKey_Left()) {
                this.leftEvent();
            } else if (DCanvas.getInstance().PointerDwonSoftKey_Right()) {
                this.RightEvent();
            } else {
                this.pointerLogic();
            }
        }
    }

    private void keyNum5() {
    }

    public void setDialogBuff(final int _id) {
        this.setBuffInfo(_id);
        this.setButton(0, 100, 2);
        this.bytRectState = 2;
        this.bytDialogState = 3;
    }

    public void delDialogBuff(final int _id) {
        if (this.bytRectState == 2 && this.bytDialogState == 3 && ((GameUI.getInstance().bytBuffIndex < 20 && _id == ORPMe.ME.intUserId) || (GameUI.getInstance().bytBuffIndex >= 20 && (Param.getInstance().oSelectRole == null || (Param.getInstance().oSelectRole != null && Param.getInstance().oSelectRole.intUserId == _id))))) {
            this.RightEvent();
        }
    }

    public void checkDialogBuffInfo(final int _id, final String _tit, final int l) {
        if (this.bytRectState == 2 && this.bytDialogState == 3) {
            if (_id == ORPMe.ME.intUserId && GameUI.getInstance().bytBuffIndex < 20) {
                if (this.Titlestr.indexOf(_tit) != -1) {
                    this.RightEvent();
                } else if (l / 10 == GameUI.getInstance().bytBuffIndex / 10 && GameUI.getInstance().bytBuffIndex > l) {
                    final GameUI instance = GameUI.getInstance();
                    --instance.bytBuffIndex;
                }
            } else if (GameUI.getInstance().bytBuffIndex >= 20 && Param.getInstance().oSelectRole != null && Param.getInstance().oSelectRole.intUserId == _id) {
                if (this.Titlestr.indexOf(_tit) != -1) {
                    this.RightEvent();
                } else if (l / 10 == (GameUI.getInstance().bytBuffIndex - 20) / 10 && GameUI.getInstance().bytBuffIndex - 20 > l) {
                    final GameUI instance2 = GameUI.getInstance();
                    --instance2.bytBuffIndex;
                }
            }
        }
    }

    private void setBuffInfo(final int _type) {
        if (_type == -1) {
            return;
        }
        String _title = "";
        final byte _num = (byte) (_type % 10);
        if (_type / 10 == 0) {
            _title = ORPMe.ME.strsBuff[_num][0];
            if (ORPMe.ME.intsBuff[_num][5] > 1) {
                _title = String.valueOf(_title) + "(" + ORPMe.ME.intsBuff[_num][5] + ")";
            }
            this.strDialog = ORPMe.ME.strsBuff[_num][1];
        } else if (_type / 10 == 1) {
            _title = ORPMe.ME.strsDeBuff[_num][0];
            if (ORPMe.ME.intsDeBuff[_num][5] > 1) {
                _title = String.valueOf(_title) + "(" + ORPMe.ME.intsDeBuff[_num][5] + ")";
            }
            this.strDialog = ORPMe.ME.strsDeBuff[_num][1];
        } else if (_type / 10 == 2) {
            _title = Param.getInstance().oSelectRole.strsBuff[_num][0];
            if (Param.getInstance().oSelectRole.intsBuff[_num][5] > 1) {
                _title = String.valueOf(_title) + "(" + Param.getInstance().oSelectRole.intsBuff[_num][5] + ")";
            }
            this.strDialog = Param.getInstance().oSelectRole.strsBuff[_num][1];
        } else if (_type / 10 == 3) {
            _title = Param.getInstance().oSelectRole.strsDeBuff[_num][0];
            if (Param.getInstance().oSelectRole.intsDeBuff[_num][5] > 1) {
                _title = String.valueOf(_title) + "(" + Param.getInstance().oSelectRole.intsDeBuff[_num][5] + ")";
            }
            this.strDialog = Param.getInstance().oSelectRole.strsDeBuff[_num][1];
        }
        this.Titlestr = _title;
        this.bytDialogMaxH = 10;
        this.setTwoRect(String.valueOf(this.Titlestr) + "\n" + this.strDialog);
    }

    private void initCountDown(final byte _type) {
        if (_type != 6 && _type != 102) {
            Param.lngCountDownStartTime = -1L;
            Param.lngCountDownMaxTime = -1L;
            Param.lngCountDownLeftTime = -1L;
            Param.strCountDownTxt = "";
        }
    }

    public void setDialog(final byte _type, final String _titleName, final Vector _tempV, final byte _rectSate) {
        this.twodialog = null;
        this.bytRectState = _rectSate;
        switch (this.bytDialogState = _type) {
            case -53: {
                this.setChooseRect(_type, _tempV, _titleName);
                this.setButton(1, 7, 2);
                break;
            }
        }
    }

    public void setDialog(final byte _type, final String _str, final byte _rectState) {
        this.setDialog(_type, (byte) (-1), _str, _rectState);
    }

    public void setDialog(final byte _type, final byte _subtype, final String _str, final byte _rectState) {
        this.initCountDown(_type);
        this.twodialog = null;
        switch (_type) {
            case -47: {
                this.setTwoRect(_str);
                this.setButton(0, 0, 2);
                break;
            }
            case 11:
            case 12:
            case 13:
            case 18: {
                this.intDialogTime = 15000;
            }
            case 1: {
                this.setTwoRect(_str);
                this.setButton(0, 0, 2);
                break;
            }
            case 3:
            case 5: {
                this.setTwoRect(_str);
                this.setButton(0, 100, 2);
                break;
            }
            case -9:
            case -8: {
                this.setTwoRect(_str);
                this.setButton(0, 0, 1);
                break;
            }
            case 4: {
                this.setTwoRect(_str);
                this.setButton(0, 100, 2);
                break;
            }
            case 8: {
                this.setTwoRect(_str);
                this.setButton(0, 4, 2);
                break;
            }
            case 6: {
                if (GameUI.getInstance().strReliveName == null) {
                    Param.lngCountDownStartTime = Param.lngDeadCountDownStartTime;
                    Param.lngCountDownMaxTime = 120000L;
                    Param.lngCountDownLeftTime = Param.lngCountDownMaxTime;
                    GameUI.getInstance().getClass();
                    Param.strCountDownTxt = "\u4f60\u5df2\u6b7b\u4ea1\uff0c\u662f\u5426\u5728\u6700\u8fd1\u7684\u590d\u6d3b\u70b9\u91cd\u751f\uff1f";
                }
                this.setTwoRect(_str);
                if (GameUI.getInstance().strReliveName == null) {
                    this.setButton(0, 11, 25);
                    break;
                }
                this.setButton(0, 11, 12);
                break;
            }
            case 9: {
                this.strDialog = _str;
                this.bytDialogMove = 0;
                this.bytDialogRoll = 1;
                Param.getInstance().intDealMyGold = -1;
                this.initDeal();
                break;
            }
            case 10: {
                this.setTwoRect(_str);
                this.setButton(0, 100, 100);
                break;
            }
            case 7: {
                this.setTwoRect(_str);
                this.setButton(0, 0, 100);
                break;
            }
            case -91:
            case -50:
            case -48:
            case -36:
            case -33:
            case -32:
            case -31:
            case -10:
            case 14:
            case 16:
            case 17:
            case 19:
            case 26:
            case 34:
            case 35:
            case 66:
            case 101:
            case 102:
            case 103:
            case 104: {
                this.setTwoRect(Param.strCountDownTxt = _str);
                this.setButton(0, 0, 2);
                break;
            }
            case 15: {
                this.setTwoRect(_str);
                this.setButton(0, 0, 21);
                break;
            }
            case -20: {
                this.setTwoRect(_str);
                this.setButton(0, 30, 29);
                break;
            }
            case 22: {
                this.setTwoRect(_str);
                this.setButton(0, 27, 31);
                break;
            }
            case -21: {
                this.setTwoRect(_str);
                this.setButton(0, 0, 2);
                break;
            }
            case 120: {
                this.setTwoRect(_str);
                this.setButton(0, 0, 2);
                break;
            }
            case 110: {
                this.setTwoRect(_str);
                this.setButton(0, 0, 100);
                break;
            }
            case 109:
            case 112:
            case 113:
            case 114:
            case 121: {
                this.setTwoRect(_str);
                this.setButton(0, 0, 2);
                break;
            }
            case 61: {
                this.setTwoRect(_str);
                this.setButton(0, 0, 2);
                break;
            }
            case 122:
            case 123:
            case 124:
            case 125:
            case 126:
            case Byte.MAX_VALUE: {
                this.setTwoRect(_str);
                this.setButton(0, 0, 2);
                break;
            }
            case 100: {
                this.setTwoRect(_str);
                this.setButton(0, 0, 2);
                break;
            }
        }
        this.bytDialogState = _type;
        this.bytDialogSubState = _subtype;
        this.bytRectState = _rectState;
    }

    private void RightEvent() {
        if (this.bytButtonType[2] == 100) {
            return;
        }
        boolean isCloseDialog = true;
        switch (this.bytDialogState) {
            case -8: {
                NetSend.getInstance().sendMarry(Param.getInstance().userID, Param.getInstance().askID, Param.getInstance().answer, (byte) 0);
                break;
            }
            case -9: {
                DCanvas.getInstance().setNetLoad(true);
                NetSend.getInstance().sendIfBuyDivorce((byte) 2, (byte) 0);
                break;
            }
            case -10: {
                DCanvas.getInstance().setNetLoad(true);
                NetSend.getInstance().sendPartnerAnswer(Param.getInstance().PARTNER_COPYID, Param.getInstance().PARTNER_WEDDINGID, (byte) 0);
            }
            case 1: {
            }
            case 3: {
                GameUI.getInstance().bytBuffIndex = -1;
                GameUI.getInstance().bytDrawType = 0;
                break;
            }
            case 4: {
                GameUI.getInstance().cleanSmallMap();
                GameUI.getInstance().bytDrawType = 0;
                break;
            }
            case 6: {
                this.setDialog((byte) 10, "\u52a0\u8f7d\u8d44\u6e90\u8bf7\u7a0d\u5019...", (byte) 2);
                DCanvas.getInstance().setNetLoad(true);
                if (GameUI.getInstance().strReliveName == null) {
                    NetSend.getInstance().sendItemRelive();
                    break;
                }
                NetSend.getInstance().sendSkillRelive(GameUI.getInstance().bytReliveX, GameUI.getInstance().bytReliveY, GameUI.getInstance().intReliveHp, GameUI.getInstance().intReliveMp);
                break;
            }
            case 8: {
                if (this.bytButtonType[2] == 7) {
                    this.setWordMove();
                    this.Titlestr = ((PackageObject) Param.getInstance().vEquipDialog.elementAt((int) this.getMove())).strName;
                    this.intTitleColor = ((PackageObject) Param.getInstance().vEquipDialog.elementAt((int) this.getMove())).intColor;
                    this.strContent = StringManager.wenZi(((PackageObject) Param.getInstance().vEquipDialog.elementAt((int) this.getMove())).strInfo, StringManager.getNewLineW());
                    PackageObject _po0 = null;
                    if (((PackageObject) Param.getInstance().vEquipDialog.elementAt((int) this.getMove())).byteEquipType > -1) {
                        _po0 = (PackageObject) Param.getInstance().vEquipDialog.elementAt(this.getMove());
                    }
                    if (_po0 != null) {
                        final String _infor = _po0.strInfo;
                        if (_infor.equals("")) {
                            Param.getInstance().GoodsSee = true;
                            GameUI.getInstance().getEquioAttribute(_po0);
                            Param.getInstance().GoodsSee = false;
                            GameControl.getInstance().CreateState((byte) 3);
                            new FullInfo(_po0.strName, _po0.intColor, (byte) 8);
                            FullInfo.getInstance().setFullRectMenu_Vector();
                            FullInfo.getInstance().setButton(1, 100, 2);
                        } else {
                            GameControl.getInstance().CreateState((byte) 3);
                            new FullInfo(_po0.strName, _po0.intColor, this.bytDialogState);
                            FullInfo.getInstance().setFullRectMenu(_infor);
                            FullInfo.getInstance().setButton(1, 100, 2);
                        }
                    } else {
                        this.intMyPropColor = 0;
                        this.strDialog = "";
                        this.strEquipContent = null;
                    }
                    this.setButton(1, 100, 2);
                    break;
                }
                if (this.bytButtonType[2] == 2) {
                    this.setButton(0, 4, 7);
                    break;
                }
                break;
            }
            case 13: {
                NetSend.getInstance().sendPlayerPk(Param.getInstance().intPkId, (byte) 3);
                break;
            }
            case 11: {
                NetSend.getInstance().sendTeamInvite((byte) 0);
                break;
            }
            case 12: {
                NetSend.getInstance().sendConsortiaInvite((byte) 0);
                break;
            }
            case 9: {
                if (this.twodialog != null) {
                    this.twodialog = null;
                    this.setButton(1, 4, 2);
                    break;
                }
                Param.getInstance().intsDealRgbColor = null;
                Param.getInstance().hImgObject = null;
                NetSend.getInstance().sendDeal((byte) 6, Param.getInstance().intDealID);
                DCanvas.getInstance().addInformation("\u4ea4\u6613\u53d6\u6d88");
                GameControl.getInstance().delUIbase(7);
                break;
            }
            case 15: {
                CMidlet.getInstance().exitGame();
                break;
            }
            case 18: {
                if (Param.getInstance().blnIsBaiShi) {
                    NetSend.getInstance().sendShiTu((byte) 6, Param.getInstance().intShiTuID);
                    break;
                }
                NetSend.getInstance().sendShiTu((byte) 5, Param.getInstance().intShiTuID);
                break;
            }
            case -20: {
                NetSend.getInstance().sendCopyModelState(Macro.MAP_NUMBER, Macro.MAP_X, Macro.MAP_Y, (byte) 2);
                break;
            }
            case -21: {
                DCanvas.getInstance().setNetLoad(true);
                NetSend.getInstance().sendDealAsk(Param.getInstance().roleID, Param.getInstance().playID, (byte) 0);
                break;
            }
            case 102: {
                switch (this.bytDialogSubState) {
                    case 0: {
                        GameControl.getInstance().delUIbase(7);
                        GameUI.getInstance().setDialog((byte) 6);
                        isCloseDialog = false;
                        break;
                    }
                    case 2: {
                        NetSend.getInstance().sendCancelAcceptFeeMessage();
                        break;
                    }
                    case 3:
                    case 4: {
                        NetSend.getInstance().sendCancelAcceptFeeMessage_Double_Check();
                        break;
                    }
                    case 5: {
                        GameControl.getInstance().delUIbase(9);
                        GameControl.getInstance().delUIbase(3);
                        NetParse.getInstance().clearTwoRect();
                        GameControl.getInstance().CreateState((byte) 4);
                        SystemMenuUI.getInstance().setState((byte) (-18));
                        GameControl.getInstance().delUIbase(7);
                        Param.getInstance().MALL_FOR_NAME = null;
                        break;
                    }
                    case 8: {
                        NetSend.getInstance().sendHokAnswer((byte) 0);
                        break;
                    }
                }
                break;
            }
            case 114: {
                MenuUI.getInstance().setButton(1, 4, 2);
                Param.getInstance().intMallNum = 1;
                break;
            }
            case 22: {
                Param.getInstance().EnterGame = true;
                DCanvas.getInstance().setCanvasState(false);
                FormDes.getInstance().setLandState("\u624b\u673a\u7ed1\u5b9a", (byte) 2);
                break;
            }
            case -53: {
                if (Param.getInstance().Notic_state) {
                    ORPMe.ME.doRightKey();
                }
                if (!Param.getInstance().Notic_state) {
                    NetSend.getInstance().sendOffLine();
                }
                Param.getInstance().Notic_state = false;
                Param.getInstance().Notice = null;
                break;
            }
            case 26: {
                NetSend.getInstance().sendExitGame();
                NetManager.getInstance().blnExitGame = true;
                NetParse.getInstance().stopParse();
                DCanvas.getInstance().clearKey();
                CMidlet.getInstance().exitGame();
                break;
            }
        }
        if (isCloseDialog) {
            GameControl.getInstance().delUIbase(7);
        }
    }

    public void leftEvent() {
        if (this.bytButtonType[1] == 100) {
            return;
        }
        Label_4322:
        {
            switch (this.bytDialogState) {
                case -47: {
                    final NetSend instance = NetSend.getInstance();
                    MenuUI.getInstance().getClass();
                    instance.sendNpcMoveOption((short) 3, (short) 0, 0);
                    Param.getInstance().vCommonList.removeElementAt(MenuUI.getInstance().getOneMove());
                    MenuUI.getInstance().shtOneMenuMove = 0;
                    NetSend.getInstance().sendNpcOneOption(Param.getInstance().oSelectNpc.intUserId, (byte) 1, 800000);
                    GameControl.getInstance().delUIbase(7);
                    break;
                }
                case 121: {
                    if (Param.getInstance().hShopPackage != null && Param.getInstance().hShopPackage.containsKey(new Integer(MenuUI.getInstance().personalShopSellPackage.getCellBoxIndex()))) {
                        DCanvas.getInstance().setNetLoad(true);
                        int n = ((PackageObject) Param.getInstance().hShopPackage.get((Object) new Integer((int) MenuUI.getInstance().personalShopSellPackage.getCellBoxIndex()))).intId;
                        NetSend.getInstance().sendShopBuy(MenuUI.getInstance().personalShopSellPackage.getCellBoxIndex(), n);
                        MenuUI.getInstance().personalShopPersonalPackageTabIndex = -1;
                        MenuUI.getInstance().setPersonalShopPackageMessage();
                    } else {
                        DCanvas.getInstance().addInformation("此商品已受空");
                    }
                    GameControl.getInstance().delUIbase(7);
                    break;
                }
                case 112: {
                    Param.getInstance();
                    String[] stringArray = (String[]) Param.MALL_EXTEND_LIST.elementAt(MenuUI.getInstance().getOneMove());
                    byte by = Byte.parseByte(stringArray[1]);
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendUpdatePacket((byte) 1, by);
                    GameControl.getInstance().delUIbase(7);
                    break;
                }
                case 113: {
                    MenuUI.getInstance().setBackSystem();
                    SystemMenuUI.getInstance().setState((byte) (-19));
                    GameControl.getInstance().delUIbase(7);
                    break;
                }
                case 109: {
                    int n = 0;
                    int n2 = 0;
                    if (Param.getInstance().hMallPackage != null && Param.getInstance().hMallPackage.containsKey(new Integer(MenuUI.getInstance().tabStyleInstance.getTabCurrentIndex()))) {
                        Hashtable hashtable = (Hashtable) Param.getInstance().hMallPackage.get(new Integer(MenuUI.getInstance().tabStyleInstance.getTabCurrentIndex()));
                        n = ((PackageObject) hashtable.get((Object) new Integer((int) Param.getInstance().personalPackage.getCellBoxIndex()))).intId;
                        n2 = ((PackageObject) hashtable.get((Object) new Integer((int) Param.getInstance().personalPackage.getCellBoxIndex()))).bytBagPlace;
                        DCanvas.getInstance().setNetLoad(true);
                        NetSend.getInstance().sendShop_Buy(n, (byte) Param.getInstance().intMallNum);
                    } else {
                        DCanvas.getInstance().addInformation("此商品已受空");
                    }
                    Param.getInstance().intMallNum = 1;
                    GameControl.getInstance().delUIbase(7);
                    if (n2 > 0) {
                        MenuUI.getInstance().setButton(1, 4, 2);
                        break;
                    }
                    MenuUI.getInstance().setButton(1, 22, 2);
                    break;
                }
                case 114: {
                    NetSend.getInstance().sendNpcMoveOption((short) 0, (short) 0, (byte) Param.getInstance().intMallNum);
                    GameControl.getInstance().delUIbase(7);
                    MenuUI.getInstance().setButton(1, 4, 2);
                    Param.getInstance().intMallNum = 1;
                    break;
                }
                case 110:
                case 120: {
                    Wap.wapView(CMidlet.getInstance(), Macro.strVersionURL);
                    CMidlet.getInstance().exitGame();
                    break;
                }
                case -8: {
                    NetSend.getInstance().sendMarry(Param.getInstance().userID, Param.getInstance().askID, Param.getInstance().answer, (byte) 1);
                    GameControl.getInstance().delUIbase(7);
                    break;
                }
                case -9: {
                    NetSend.getInstance().sendIfBuyDivorce((byte) 2, (byte) 1);
                    GameControl.getInstance().delUIbase(7);
                    break;
                }
                case -10: {
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendPartnerAnswer(Param.getInstance().PARTNER_COPYID, Param.getInstance().PARTNER_WEDDINGID, (byte) 1);
                    GameControl.getInstance().delUIbase(7);
                    break;
                }
                case 1: {
                    this.setDialog((byte) 10, "\u52a0\u8f7d\u8d44\u6e90\u8bf7\u7a0d\u5019...", (byte) 2);
                    DCanvas.getInstance().setNetLoad(true);
                    ORPMe.ME.sendMoveTask();
                    ORPMe.ME.pushTaskFirst((byte) 3, (byte) 1);
                    break;
                }
                case 3: {
                    GameUI.getInstance().bytBuffIndex = -1;
                    GameControl.getInstance().delUIbase(7);
                    break;
                }
                case 7: {
                    GameControl.getInstance().setReLoadMainMenu();
                    break;
                }
                case 14: {
                    this.setDialog((byte) 15, "\u662f\u5426\u8fdb\u5165\u5b98\u7f51\uff1f", (byte) 2);
                    break;
                }
                case 26: {
                    NetSend.getInstance().sendExitGame();
                    NetManager.getInstance().blnExitGame = true;
                    NetParse.getInstance().stopParse();
                    DCanvas.getInstance().clearKey();
                    CMidlet.getInstance().exitGame();
                    break;
                }
                case 8: {
                    if (this.bytButtonType[1] == 4) {
                        GameUI.getInstance().setTwoMenu(this.bytRectState, this.STRING_OPTION_ALLOT, (byte) 8);
                        break;
                    }
                    break;
                }
                case 13: {
                    NetSend.getInstance().sendPlayerPk(Param.getInstance().intPkId, (byte) 2);
                    GameControl.getInstance().delUIbase(7);
                    break;
                }
                case 11: {
                    NetSend.getInstance().sendTeamInvite((byte) 1);
                    GameControl.getInstance().delUIbase(7);
                    break;
                }
                case 12: {
                    NetSend.getInstance().sendConsortiaInvite((byte) 1);
                    GameControl.getInstance().delUIbase(7);
                    break;
                }
                case 9: {
                    this.updateDeal((byte) 5);
                    break;
                }
                case 6: {
                    this.setDialog((byte) 10, "\u52a0\u8f7d\u8d44\u6e90\u8bf7\u7a0d\u5019...", (byte) 2);
                    DCanvas.getInstance().setNetLoad(true);
                    GameUI.getInstance().strReliveName = null;
                    NetParse.getInstance().setInitLoadingPre();
                    NetSend.getInstance().sendCityRelive();
                    NetParse.getInstance().setInitLoading();
                    break;
                }
                case 15: {
                    Rms.saveSet((byte) (-1), (byte) (-1), (byte) (-1), (byte) (-1), (byte) (-1), (byte) (-1), (byte) (-1));
                    NetSend.getInstance().sendExitGame();
                    NetManager.getInstance().blnExitGame = true;
                    NetParse.getInstance().stopParse();
                    DCanvas.getInstance().clearKey();
                    Wap.wapView(CMidlet.getInstance(), Macro.ENTER_WAP);
                    CMidlet.getInstance().exitGame();
                    break;
                }
                case 16: {
                    NetSend.getInstance().sendCoordinate();
                    GameControl.getInstance().delUIbase(7);
                    break;
                }
                case 17: {
                    ORPMe.ME.delStall();
                    NetSend.getInstance().sendShopHandle((byte) 6);
                    GameControl.getInstance().delUIbase(7);
                    GameUI.getInstance().setButton();
                    break;
                }
                case 19: {
                    DCanvas.getInstance().setNetLoad(true);
                    MenuUI.getInstance().isDrawSkillinfo = false;
                    if (MenuUI.getInstance().bytTitleMove == 0) {
                        NetSend.getInstance().sendSkillUp(1, ((SkillObject) Param.getInstance().vSkillOccupationBefore.elementAt((int) MenuUI.getInstance().getOneMove())).intId);
                    } else {
                        NetSend.getInstance().sendSkillUp(1, ((SkillObject) Param.getInstance().vSkillOccupationLater.elementAt((int) MenuUI.getInstance().getOneMove())).intId);
                    }
                    GameControl.getInstance().delUIbase(7);
                    break;
                }
                case 18: {
                    if (Param.getInstance().blnIsBaiShi) {
                        NetSend.getInstance().sendShiTu((byte) 4, Param.getInstance().intShiTuID);
                    } else {
                        NetSend.getInstance().sendShiTu((byte) 3, Param.getInstance().intShiTuID);
                    }
                    GameControl.getInstance().delUIbase(7);
                    break;
                }
                case -20: {
                    NetSend.getInstance().sendCopyModelState(Macro.MAP_NUMBER, Macro.MAP_X, Macro.MAP_Y, (byte) 1);
                    GameControl.getInstance().delUIbase(7);
                    break;
                }
                case -21: {
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendDealAsk(Param.getInstance().roleID, Param.getInstance().playID, (byte) 1);
                    GameControl.getInstance().delUIbase(7);
                    break;
                }
                case -33:
                case -32:
                case -31: {
                    byte by = (byte) ((Param.getInstance().shtNoncePage - 1) * 20 + MenuUI.getInstance().getOneMove());
                    String string = ((TeamObject) Param.getInstance().vCommonList.elementAt((int) by)).strName;
                    byte by2 = 0;
                    by2 = this.bytDialogState == -31 ? (byte) 1 : (this.bytDialogState == -32 ? (byte) 2 : 3);
                    NetSend.getInstance().sendHailFellow((byte) 3, by2, ((TeamObject) Param.getInstance().vCommonList.elementAt((int) by)).strName);
                    Param.getInstance().vCommonList.removeElementAt(by);
                    if (MenuUI.getInstance().ifVectorNull()) {
                        short s = (short) (Param.getInstance().vCommonList.size() - (Param.getInstance().shtNoncePage - 1) * 20);
                        if (s > 20) {
                            MenuUI.getInstance().shtMenuMoveLength = (short) 20;
                        } else if (s > 0) {
                            MenuUI.getInstance().shtMenuMoveLength = s;
                            MenuUI.getInstance().setChoiceMove(s, MenuUI.getInstance().getOneMove(), MenuUI.getInstance().bytWordMaxH);
                        } else {
                            MenuUI.getInstance().clearMove();
                            if (Param.getInstance().shtAllPage > 1) {
                                Param.getInstance().shtAllPage = (short) (Param.getInstance().shtAllPage - 1);
                            }
                            if (Param.getInstance().shtNoncePage > 1) {
                                Param.getInstance().shtNoncePage = (short) (Param.getInstance().shtNoncePage - 1);
                            }
                            MenuUI.getInstance().shtMenuMoveLength = (short) 20;
                        }
                    }
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("你已把" + string + "删除");
                    MenuUI.getInstance().setButton(1, 4, 2);
                    GameControl.getInstance().delUIbase(7);
                    DCanvas.getInstance().addInformation(stringBuffer.toString());
                    break;
                }
                case 35: {
                    TeamObject teamObject = (TeamObject) Param.getInstance().vTeam.elementAt(MenuUI.getInstance().getTeamPlayerMove(MenuUI.getInstance().bytChatTeam));
                    byte by = 0;
                    by = MenuUI.getInstance().bytChatTeam == 0 ? (byte) 2 : 1;
                    if (teamObject.bytTeamId == by) {
                        DCanvas.getInstance().addInformation("已在该小队");
                        break;
                    }
                    if (by == 2 && Param.bytChatSubTeamNumCount[1] >= 5 || by == 1 && Param.bytChatSubTeamNumCount[0] >= 5) {
                        DCanvas.getInstance().addInformation("该小队已满");
                        break;
                    }
                    DCanvas.getInstance().setNetLoad(true);
                    int n = ((TeamObject) Param.getInstance().vTeam.elementAt((int) MenuUI.getInstance().getTeamPlayerMove((byte) MenuUI.getInstance().bytChatTeam))).intId;
                    NetSend.getInstance().sendTeam_Transfer(n, by);
                    String string = ((TeamObject) Param.getInstance().vTeam.elementAt((int) MenuUI.getInstance().getTeamPlayerMove((byte) MenuUI.getInstance().bytChatTeam))).strName;
                    GameControl.getInstance().delUIbase(7);
                    DCanvas.getInstance().addInformation("你已成功改变" + string + "的位置");
                    break;
                }
                case -48: {
                    TeamObject teamObject = (TeamObject) MenuUI.getInstance().vTempVessel.elementAt(MenuUI.getInstance().getMasterPlayerMove((byte) MenuUI.getInstance().intRelation));
                    if (teamObject.bytRelation == 1) {
                        NetSend.getInstance().sendShiTu_Option((byte) 2, -1);
                    } else if (teamObject.bytRelation == 2) {
                        NetSend.getInstance().sendShiTu_Option((byte) 3, teamObject.intId);
                    }
                    GameControl.getInstance().delUIbase(7);
                    break;
                }
                case 61: {
                    GameControl.getInstance().delUIbase(7);
                    if (MenuUI.getInstance().bytTitleMove == 0) {
                        NetSend.getInstance().sendSkillUp(0, ((SkillObject) Param.getInstance().vSkillOccupationBefore.elementAt((int) MenuUI.getInstance().getOneMove())).intId);
                        break;
                    }
                    if (MenuUI.getInstance().bytTitleMove != 1) {
                        break;
                    }
                    NetSend.getInstance().sendSkillUp(0, ((SkillObject) Param.getInstance().vSkillOccupationLater.elementAt((int) MenuUI.getInstance().getOneMove())).intId);
                    break;
                }
                case 66: {
                    GameControl.getInstance().delUIbase(7);
                    DCanvas.getInstance().setNetLoad(true);
                    byte by = (byte) Param.getInstance().vMenuMemory.size();
                    int n = ((MenuObject) Param.getInstance().vMenuMemory.elementAt((int) 0)).intsOptionId[MenuUI.getInstance().getOneMove()];
                    byte by3 = ((MenuObject) Param.getInstance().vMenuMemory.elementAt((int) (by - 1))).bytStep;
                    NetSend.getInstance().sendNpcIsOption(Param.getInstance().oSelectNpc.intUserId, by3, n, (byte) 1);
                    break;
                }
                case -50: {
                    NetSend.getInstance().sendDddPartner((byte) 2, Param.getInstance().partner.strNickName);
                    GameControl.getInstance().delUIbase(7);
                    break;
                }
                case 122: {
                    if (MenuUI.getInstance().bytMoveType == 1) {
                        int n = ((PackageObject) Param.getInstance().hPackage.get((Object) new Integer((int) Param.getInstance().personalPackage.getCellBoxIndex()))).intId;
                        Param.getInstance().hPackage.remove(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()));
                        NetSend.getInstance().sendFrameEquip((byte) 6, Param.getInstance().personalPackage.getCellBoxIndex(), n);
                    } else {
                        int n = ((PackageObject) Param.getInstance().hPackageEquip.get((Object) new Integer((int) MenuUI.getInstance().bytRoleMove))).intId;
                        Param.getInstance().hPackageEquip.remove(new Integer(MenuUI.getInstance().bytRoleMove));
                        NetSend.getInstance().sendFrameEquip((byte) 3, MenuUI.getInstance().bytRoleMove, n);
                    }
                    MenuUI.getInstance().setSelectedButton(MenuUI.getInstance().bytMenuState == -35 || MenuUI.getInstance().bytMenuState == -72 ? Param.getInstance().hShopPackage : Param.getInstance().hPackage, Param.getInstance().personalPackage.getCellBoxIndex());
                    GameControl.getInstance().delUIbase(7);
                    break;
                }
                case 123: {
                    int n = ((PackageObject) Param.getInstance().hPackage.get((Object) new Integer((int) Param.getInstance().personalPackage.getCellBoxIndex()))).intId;
                    Param.getInstance().hPackage.remove(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()));
                    NetSend.getInstance().sendFrameLeechdom((byte) 4, Param.getInstance().personalPackage.getCellBoxIndex(), n);
                    MenuUI.getInstance().setInfoContent(Param.getInstance().hPackage, Param.getInstance().personalPackage.getCellBoxIndex());
                    GameControl.getInstance().delUIbase(7);
                    break;
                }
                case 124: {
                    int n = ((PackageObject) Param.getInstance().hPackage.get((Object) new Integer((int) Param.getInstance().personalPackage.getCellBoxIndex()))).intId;
                    Param.getInstance().hPackage.remove(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()));
                    NetSend.getInstance().sendFrameStuff((byte) 2, Param.getInstance().personalPackage.getCellBoxIndex(), n);
                    MenuUI.getInstance().setInfoContent(Param.getInstance().hPackage, Param.getInstance().personalPackage.getCellBoxIndex());
                    GameControl.getInstance().delUIbase(7);
                    break;
                }
                case 125: {
                    int n = ((PackageObject) Param.getInstance().hPackage.get((Object) new Integer((int) Param.getInstance().personalPackage.getCellBoxIndex()))).intId;
                    Param.getInstance().hPackage.remove(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()));
                    NetSend.getInstance().sendFrameTaskprop((byte) 4, Param.getInstance().personalPackage.getCellBoxIndex(), n);
                    MenuUI.getInstance().setInfoContent(Param.getInstance().hPackage, Param.getInstance().personalPackage.getCellBoxIndex());
                    GameControl.getInstance().delUIbase(7);
                    break;
                }
                case 126: {
                    int n = ((PackageObject) Param.getInstance().hPackage.get((Object) new Integer((int) Param.getInstance().personalPackage.getCellBoxIndex()))).intId;
                    Param.getInstance().hPackage.remove(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()));
                    NetSend.getInstance().sendFramePeculiar((byte) 4, Param.getInstance().personalPackage.getCellBoxIndex(), n, -1, (byte) 0);
                    MenuUI.getInstance().setInfoContent(Param.getInstance().hPackage, Param.getInstance().personalPackage.getCellBoxIndex());
                    GameControl.getInstance().delUIbase(7);
                    break;
                }
                case 127: {
                    int n = ((PackageObject) Param.getInstance().hPackage.get((Object) new Integer((int) Param.getInstance().personalPackage.getCellBoxIndex()))).intId;
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendFrameEquip((byte) 4, Param.getInstance().personalPackage.getCellBoxIndex(), n);
                    GameControl.getInstance().delUIbase(7);
                    break;
                }
                case 100: {
                    LandUI.getInstance().DeleteRole();
                    GameControl.getInstance().delUIbase(7);
                    break;
                }
                case 101: {
                    byte by = (byte) Param.getInstance().InlayIndex;
                    int n = ((PackageObject) Param.getInstance().hPackage.get((Object) new Integer((int) Param.getInstance().personalPackage.getCellBoxIndex()))).intId;
                    byte by4 = MenuUI.getInstance().inlayHole.getCellBoxIndex();
                    Param.getInstance().RightDate = true;
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendStrengthen(by, (byte) 1, n, by4);
                    GameControl.getInstance().delUIbase(7);
                    MenuUI.getInstance().setState((byte) 11, "镶嵌宝石");
                    break;
                }
                case 102: {
                    switch (this.bytDialogSubState) {
                        case 0: {
                            GameControl.getInstance().delUIbase(7);
                            GameUI.getInstance().switchToShopMenu((byte) 7, (byte) 6, (byte) 0, (byte) 0, (byte) 0);
                            break Label_4322;
                        }
                        case 1: {
                            if (GameUI.getInstance().bytDrawType == 1) {
                                GameControl.getInstance().delUIbase(7);
                                GameUI.getInstance().switchToShopMenu((byte) 2, (byte) 0, (byte) 0, (byte) 0, (byte) 0);
                                break Label_4322;
                            }
                            GameControl.getInstance().delUIbase(7);
                            GameUI.getInstance().switchToShopMenu((byte) 4, (byte) 0, (byte) 0, (byte) 0, (byte) 0);
                            break Label_4322;
                        }
                        case 2: {
                            NetSend.getInstance().sendMobileLogin();
                            NetSend.getInstance().sendAcceptFeeMessage();
                            GameControl.getInstance().delUIbase(7);
                            break Label_4322;
                        }
                        case 3: {
                            Param.Fee_Dialog_Accept = true;
                            if (Param.Fee_Dialog_Accept && Param.Fee_Type_Received) {
                                NetSend.getInstance().sendAcceptFeeMessage_Double_Check();
                                Param.Fee_Dialog_Accept = false;
                                Param.Fee_Type_Received = false;
                            }
                            GameControl.getInstance().delUIbase(7);
                            break Label_4322;
                        }
                        case 4: {
                            if (Param.Task_Fee_Type == 1) {
                                final int sendCount = Param.Fee_SMS_Count;
                                NetManager.getInstance().sendMessage(Param.Task_Fee_Phone_Num, Param.Task_Fee_Msg_TXT_1, Param.Task_Fee_Msg_TXT_2, sendCount);
                                NetSend.getInstance().sendAcceptFeeMessage_Double_Check();
                                GameControl.getInstance().delUIbase(7);
                                break Label_4322;
                            }
                            if (Param.Task_Fee_Type == 2) {
                                NetSend.getInstance().sendAcceptFeeMessage_Double_Check();
                                GameControl.getInstance().delUIbase(7);
                                break Label_4322;
                            }
                            break Label_4322;
                        }
                        case 5: {
                            GameControl.getInstance().delUIbase(9);
                            GameControl.getInstance().delUIbase(3);
                            NetParse.getInstance().clearTwoRect();
                            GameControl.getInstance().CreateState((byte) 4);
                            SystemMenuUI.getInstance().setState((byte) (-18));
                            GameControl.getInstance().delUIbase(7);
                            Param.getInstance().MALL_FOR_NAME = null;
                            break Label_4322;
                        }
                        case 6: {
                            DCanvas.getInstance().setNetLoad(true);
                            NetParse.getInstance().setInitLoadingPre();
                            NetSend.getInstance().sendAreaMapInfo((byte) 8, GameUI.getInstance().shtMoveMapID, Map.getInstance().bytCurAreaMapClanType, Map.getInstance().intMapTransferNpcId);
                            NetParse.getInstance().setInitLoading();
                            break Label_4322;
                        }
                        case 7: {
                            DCanvas.getInstance().setNetLoad(true);
                            NetParse.getInstance().setInitLoadingPre();
                            NetSend.getInstance().sendAreaMapInfo((byte) 6, GameUI.getInstance().shtMoveMapID, Map.getInstance().bytCurAreaMapClanType, 0);
                            NetParse.getInstance().setInitLoading();
                            break Label_4322;
                        }
                        case 8: {
                            NetSend.getInstance().sendHokAnswer((byte) 1);
                            GameControl.getInstance().delUIbase(7);
                            break Label_4322;
                        }
                        default: {
                            GameControl.getInstance().delUIbase(7);
                            break Label_4322;
                        }
                    }
                }
                case 103: {
                    MenuUI.getInstance().setEquipPackage(Param.getInstance().hPackage, Param.getInstance().personalPackage.getCellBoxIndex());
                    final byte weap_ID = Param.getInstance().personalPackage.getCellBoxIndex();
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendStrengthen(weap_ID, (byte) 0, 0, (byte) 0);
                    GameControl.getInstance().delUIbase(7);
                    break;
                }
                case 34: {
                    TeamObject teamObject = (TeamObject) Param.getInstance().vCommonList.elementAt(MenuUI.getInstance().getOneMove());
                    switch (Param.getInstance().bytConsortiaType) {
                        case 0: {
                            DCanvas.getInstance().setNetLoad(true);
                            NetSend.getInstance().sendConsortia((byte) 5, (byte) 2, 1);
                            break;
                        }
                        case 1: {
                            DCanvas.getInstance().setNetLoad(true);
                            NetSend.getInstance().sendConsortia((byte) 4, (byte) 2, teamObject.intId);
                            break;
                        }
                        case 2: {
                            DCanvas.getInstance().setNetLoad(true);
                            NetSend.getInstance().sendConsortia((byte) 4, (byte) 1, teamObject.intId);
                            break;
                        }
                        case 3: {
                            DCanvas.getInstance().setNetLoad(true);
                            NetSend.getInstance().sendConsortia((byte) 3, (byte) 2, teamObject.intId);
                            break;
                        }
                        case 4: {
                            DCanvas.getInstance().setNetLoad(true);
                            NetSend.getInstance().sendConsortia((byte) 7, (byte) 0, 0);
                        }
                    }
                    GameControl.getInstance().delUIbase(7);
                    break;
                }
                case -36: {
                    int n = ((MailObject) Param.getInstance().vCommonList.elementAt((int) MenuUI.getInstance().getOneMove())).intId;
                    NetSend.getInstance().sendMailRenovate((byte) 2, n);
                    Param.getInstance().vCommonList.removeElementAt(MenuUI.getInstance().getOneMove());
                    if (Param.getInstance().vCommonList.isEmpty()) {
                        Param.getInstance().vCommonList = null;
                    }
                    MenuUI.getInstance().setMailList(MenuUI.getInstance().getOneMove());
                    GameControl.getInstance().delUIbase(7);
                    break;
                }
                case 22: {
                    if (DCanvas.getInstance().getCanvasState()) {
                        DCanvas.getInstance().setNetLoad(true);
                        if (Rms.strRmsName != null) {
                            DCanvas.getInstance().setNetLoad(true);
                            NetSend.getInstance().sendLand(Rms.strRmsName, Rms.strRmsPassword, (byte) 2);
                            NetSend.getInstance().sendRequestRes(IResConfig.RES_CONFIG.length);
                        }
                        GameControl.getInstance().delUIbase(7);
                        break;
                    }
                    DCanvas.getInstance().setCanvasState(true);
                    break;
                }
                case -53: {
                    if (Param.getInstance().Notice != null & this.twodialog != null) {
                        int n = this.twodialog.shtOneMenuMove + this.twodialog.shtOneMenuRoll;
                        Param.getInstance().shtOneMenuMove = this.twodialog.shtOneMenuMove;
                        Param.getInstance().shtOneMenuRoll = this.twodialog.shtOneMenuRoll;
                        ChatContent chatContent = (ChatContent) Param.getInstance().Notice.elementAt(n);
                        int n3 = chatContent.imageID;
                        GameControl.getInstance().CreateState((byte) 3);
                        new FullInfo("活动说明", 0, this.bytDialogState);
                        DCanvas.getInstance().setNetLoad(true);
                        NetSend.getInstance().sendNoticeContent(n3);
                    }
                    GameControl.getInstance().delUIbase(7);
                    break;
                }
                case 104: {
                    ImageManager.cleanAllRMS();
                    CMidlet.getInstance().exitGame();
                    break;
                }
            }
        }
    }

    public void setEquipDialog() {
        this.shtEquipRectW = 0;
        this.shtEquipRectH = 0;
        short s = (short) Param.getInstance().vEquipDialog.size();
        short s2 = (short) (Macro.FONTW * 5 + 20 + 32);
        short s3 = 0;
        while (s3 < s) {
            String string = ((PackageObject) Param.getInstance().vEquipDialog.elementAt((int) s3)).strName;
            if (Macro.font.stringWidth(string) + 20 + 32 > this.shtEquipRectW) {
                this.shtEquipRectW = (short) (Macro.font.stringWidth(string) + 20 + 32);
            }
            s3 = (byte) (s3 + 1);
        }
        if (s2 > this.shtEquipRectW) {
            this.shtEquipRectW = s2;
        }
        this.bytDialogMaxH = (byte) (Macro.SCREEN_HEIGHT / (Macro.FONTHEIGHT + 2 + 4 + 6) - 4);
        this.shtEquipRectH = this.bytDialogMaxH > s ? (short) (s * (Macro.FONTHEIGHT + 2 + 4 + 6) + 16) : (short) (this.bytDialogMaxH * (Macro.FONTHEIGHT + 2 + 4 + 6) + 16);
        this.strContent = StringManager.wenZi("物品分配", Macro.bytHintWidth * 16 - Macro.FONTW * 2);
        Macro.shtRectHeight = (short) ((Macro.SCREEN_HEIGHT - (Macro.FONTHEIGHT * 2 + 34)) / 2);
        Param.getInstance().intsDealRgbColor = DrawBase.getRGB(this.shtEquipRectW - 28, Macro.FONTHEIGHT + 8, 1144385017);
        this.bytRectState = (byte) -3;
        this.bytDialogState = (byte) 8;
        this.setButton(0, 4, 7);
    }

    public boolean delEquipDialog(final short _index) {
        Param.getInstance().vEquipDialog.removeElementAt(_index);
        if (Param.getInstance().vEquipDialog.isEmpty()) {
            GameControl.getInstance().delUIbase(7);
            Param.getInstance().vEquipDialog = null;
            Param.getInstance().intsDealRgbColor = null;
            if (ORPMe.ME.bytState == 5) {
                GameUI.getInstance().setDialog((byte) 6);
            }
            return true;
        }
        final short _size = (short) Param.getInstance().vEquipDialog.size();
        if (this.bytDialogMove + this.bytDialogRoll > _size - 1) {
            if (this.bytDialogRoll > 0) {
                --this.bytDialogRoll;
            } else if (this.bytDialogMove > 0) {
                --this.bytDialogMove;
            }
        }
        this.setEquipDialog();
        return false;
    }

    private void pointerLogic() {
        if (DCanvas.getInstance().PointerDwonSoftKey_Left()) {
            this.leftEvent();
        } else if (DCanvas.getInstance().PointerDwonSoftKey_Right()) {
            this.RightEvent();
        } else if (this.twodialog == null) {
            switch (this.bytDialogState) {
                case 9: {
                    if (this.twodialog == null) {
                        this.updateDealTouch();
                        break;
                    }
                    break;
                }
                case 8: {
                    final short _x = (short) ((Macro.SCREEN_WIDTH - this.shtEquipRectW) / 2 + 8);
                    final short _y = (short) ((Macro.SCREEN_HEIGHT - this.shtEquipRectH) / 2 + 8);
                    if (DCanvas.getInstance().IsPointerDown(_x, _y, this.shtEquipRectW, this.shtEquipRectH - 16)) {
                        final byte _index = (byte) ((DCanvas.getInstance().CurPressedY - _y) / (Macro.FONTHEIGHT + 12));
                        this.bytDialogMove = _index;
                        break;
                    }
                    break;
                }
                default: {
                    this.doMePoint();
                    break;
                }
            }
        } else {
            if (this.bytDialogState == 3) {
                final int _type = GameUI.getInstance().pointerCheckBuff();
                if (_type != -1) {
                    this.setBuffInfo(_type);
                }
            }
            this.doMePoint();
        }
    }

    private void setTwoRect(final String _str) {
        if (_str == null) {
            return;
        }
        (this.twodialog = new TwoDialogUI()).setTwoRect(_str, -1, this.bytDialogMove, this.bytRectState, (byte) 1, -1);
    }

    private void setChooseRect(final byte _type, final Vector _tempV, final String _titlename) {
        (this.twodialog = new TwoDialogUI()).setChooseRect(_type, _tempV, _titlename, this.bytRectState);
    }

    public void setTwoNumber(final String _str, int _max, final int _key, final byte _move) {
        this.twodialog = new TwoDialogUI();
        if (_max >= 1000000000) {
            _max = 999999999;
        }
        this.twodialog.setTwoNumber(_str, _max, this.bytRectState, _key, _move);
        this.setButton(1, 0, 2);
    }

    private void doMeKey() {
        if (FullInfo.getInstance() != null) {
            return;
        }
        if (this.bytDialogState == 6 || this.bytRectState == -3) {
            if (DCanvas.getInstance().isKeyDown(1)) {
                GameUI.getInstance().doChangeKeyType();
            } else {
                final byte _key = DCanvas.getInstance().checkKeyDown();
                if (_key == -1) {
                    return;
                }
                final byte _num = (byte) (GameUI.getInstance().bytShortcutKeysType * 13 + _key);
                if (Param.getInstance().intShortcutKeys[_num][4] == 1 && (Param.getInstance().intShortcutKeys[_num][0] == 5 || Param.getInstance().intShortcutKeys[_num][0] == 9)) {
                    GameUI.getInstance().doKey(_key);
                }
            }
        }
    }

    private void doMePoint() {
        if (DCanvas.getInstance().IsPointerDown(Macro.SCREEN_WIDTH - GameUI.getInstance().shtHotKeyXOff - 22, Macro.SCREEN_HEIGHT - GameUI.getInstance().bytbHotKeyExepHeight + GameUI.getInstance().bytHotKeyBoxContentHeightYOff, GameUI.getInstance().bytHotKeyBoxWidth, 22)) {
            GameUI.getInstance().doChangeKeyType();
        } else if (DCanvas.getInstance().IsPointerDown(GameUI.getInstance().shtHotKeyXOff, Macro.SCREEN_HEIGHT - GameUI.getInstance().bytbHotKeyExepHeight + GameUI.getInstance().bytHotKeyBoxContentHeightYOff, 9 * (GameUI.getInstance().bytHotKeyBoxWidth + GameUI.getInstance().bytHotKeyBoxDistance), 22)) {
            final byte _x = (byte) ((DCanvas.getInstance().CurPressedX - GameUI.getInstance().shtHotKeyXOff) / (GameUI.getInstance().bytHotKeyBoxWidth + GameUI.getInstance().bytHotKeyBoxDistance));
            final byte _key = DCanvas.getInstance().bytDrawKeyIndex[_x];
            final byte _num = (byte) (GameUI.getInstance().bytShortcutKeysType * 13 + _key);
            if (Param.getInstance().intShortcutKeys[_num][4] == 1 && (Param.getInstance().intShortcutKeys[_num][0] == 5 || Param.getInstance().intShortcutKeys[_num][0] == 9)) {
                GameUI.getInstance().doKey(DCanvas.getInstance().bytDrawKeyIndex[_x]);
            }
        }
    }

    private void drawAllotImageOption(final Graphics g, final PackageObject _po, final short _x, final short _y, final byte _modi) {
        final Image img = GameUI.getInstance().getObjectImg(_po.shtIcon);
        if (img != null) {
            GameUI.getInstance().drawOptionImage(g, img, _x, _y, _modi);
            final int _aa = _po.intPrice * (Macro.font.stringWidth(_po.strName) + 20) / _po.intPOindex;
            GameUI.getInstance().drawMagicBar(g, _x, _y + Macro.FONTHEIGHT + 2, _aa, Macro.font.stringWidth(_po.strName) + 20);
            g.setColor(_po.intColor);
            g.drawString(_po.strName, _x + 20, (int) _y, (int) _modi);
        }
    }
}
