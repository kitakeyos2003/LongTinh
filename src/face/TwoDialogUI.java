// Decompiled with: Procyon 0.5.36
// Class Version: 1
package face;

import means.ChatContent;
import javax.microedition.lcdui.Image;
import java.util.Enumeration;
import model.TaskObject;
import base.GameControl;
import model.ORPMe;
import model.SkillObject;
import network.NetSend;
import means.FictitiousKeyBoard;
import common.ScrollText;
import base.DCanvas;
import common.LayoutStyle;
import javax.microedition.lcdui.Graphics;
import model.PackageObject;
import model.MenuObject;
import common.Common;
import common.DrawBase;
import means.StringManager;
import base.Param;
import base.Macro;
import java.util.Hashtable;
import common.PackageBox;
import means.AnalysisWordContent;
import java.util.Vector;

public class TwoDialogUI {

    public String[] strContent;
    private String TitleName;
    private int intColor;
    private int intTempKey;
    private int intInputMax;
    private short bytMove;
    private short bytRoll;
    private byte bytType;
    private byte bytState;
    private short shtIcon;
    public int bytRectWidth;
    public short shtRectHeight;
    private short shtKeyHeight;
    private short shtRectWordW;
    private short shtRectWordX;
    private short shtRectWordY;
    private byte bytWrodFlash;
    public static int[] two_dialog_Rgb;
    private int MaxShowWidth;
    private int[] fontwidtharray;
    private short shtMenuMoveLength;
    private byte bytWordMaxH;
    public short shtOneMenuMove;
    public short shtOneMenuRoll;
    private Vector member;
    public byte bytMenuState;
    public int scroll_OFF;
    private int shtTitle_x;
    private int shtTitle_y;
    private int shtTitle_w;
    private int shtTitle_h;
    public int BackGroundX;
    public int BackGroundY;
    public int BackGroundW;
    public int BackGroundH;
    private AnalysisWordContent WordContent;
    public PackageBox packageInstance;
    public Hashtable packageData;
    private int bytKeyBoardIndex;
    private short shtKeyBoardX;
    private short shtKeyBoardY;
    private short shtKeyBoardH;
    private short shtKeyBoardW;
    private byte bytCutKeysIndex;
    private byte bytNewLineHeight;
    private short shtKeyTitleMoveX;
    private short shtKeyTitleMoveW;
    private int ShortcutKeysBox_count;
    private int _textcolour;

    public TwoDialogUI() {
        this.ShortcutKeysBox_count = 8;
    }

    public void initDialogOptions(String s) {
        this.bytRectWidth = 0;
        this.shtRectWordW = 0;
        this.strContent = new String[Integer.parseInt(s.substring(0, 1)) * 3];
        s = s.substring(1);
        for (int i = 0; i < s.length(); i = (byte) (i + 1)) {
            int index = s.indexOf("#S");
            this.strContent[i * 3 + 0] = s.substring(index + 2, index + 3);
            s = s.substring(index + 3);
            int index2 = s.indexOf("F");
            int index3 = s.indexOf("[");
            this.strContent[i * 3 + 1] = s.substring(index2 + 1, index3);
            int index4 = s.indexOf("]");
            this.strContent[i * 3 + 2] = s.substring(index3 + 1, index4);
            s = s.substring(index4 + 1);
            int stringWidth = Macro.font.stringWidth(this.strContent[i * 3 + 2]);
            if (this.bytRectWidth * 16 < stringWidth) {
                this.bytRectWidth = (byte) (stringWidth / 16 + 2);
            }
            if (this.shtRectWordW < stringWidth) {
                this.shtRectWordW = (short) stringWidth;
            }
            if (s.length() < 1) {
                break;
            }
        }
        this.shtRectHeight = (short) (this.strContent.length / 3 * Macro.FONTHEIGHT + 16);
        this.shtRectWordX = (short) ((Macro.SCREEN_WIDTH - this.shtRectWordW) / 2);
        this.shtRectWordY = (short) ((Macro.SCREEN_HEIGHT - this.shtRectHeight) / 2 + 8);
        this.bytType = 100;
    }

    private void keyUpDialogOptions() {
        if (this.bytMove > 0) {
            --this.bytMove;
        }
    }

    private void keyDownDialogOptions() {
        if (this.bytMove < this.strContent.length / 3 - 1) {
            ++this.bytMove;
        }
    }

    private String setStringName(String s) {
        short n = (short) s.indexOf("#FNAME");
        if (n != -1) {
            StringBuffer sb = new StringBuffer();
            sb.append(s.substring(0, n));
            sb.append(Param.getInstance().strInputName);
            sb.append(s.substring(n + 6));
            return sb.toString();
        }
        return s;
    }

    public void initSettingKey(String s, short shtIcon, int intColor, byte bytState) {
        short n = 0;
        this.bytRoll = n;
        this.bytMove = n;
        this.bytCutKeysIndex = 0;
        this.shtIcon = shtIcon;
        this.intColor = intColor;
        (this.strContent = new String[1])[0] = s;
        this.initSetKey();
        this.bytState = bytState;
        this.bytType = 2;
    }

    public void setPackageSettingData(Hashtable packageData) {
        this.packageData = packageData;
        if (this.packageInstance != null) {
            this.packageInstance.setPopupDialog(this.packageData);
        }
    }

    public void initPackageSettingKey(String s, short shtIcon, int intColor, byte bytState) {
        short n = 0;
        this.bytRoll = n;
        this.bytMove = n;
        this.bytCutKeysIndex = 0;
        this.shtIcon = shtIcon;
        this.intColor = intColor;
        (this.strContent = new String[1])[0] = s;
        this.initPackageSetKey();
        this.bytState = bytState;
        this.bytType = 19;
        this.packageInstance = new PackageBox(this.shtKeyBoardX, this.shtKeyBoardY, this.shtKeyBoardW, this.shtKeyBoardH, 16, true);
    }

    public void setTwoNumber(String s, int intInputMax, byte bytState, int n, short bytMove) {
        this.shtKeyHeight = 78;
        this.bytState = bytState;
        this.intTempKey = 0;
        this.bytMove = bytMove;
        this.shtRectWordW = 0;
        if (Macro.font.stringWidth(s) > (Macro.bytHintWidth - 2) * 16 && Macro.font.stringWidth(s) < (Macro.bytMaxHintWidth - 2) * 16) {
            this.bytRectWidth = (byte) ((Macro.font.stringWidth(s) + Macro.FONTW * 2) / 16 + 1);
        } else {
            this.bytRectWidth = Macro.bytHintWidth;
        }
        this.strContent = StringManager.wenZi(s, Macro.bytHintWidth * 16 - Macro.FONTW);
        for (int i = 0; i < this.strContent.length; i = (short) (i + 1)) {
            if (Macro.font.stringWidth(this.strContent[i]) > this.shtRectWordW) {
                this.shtRectWordW = (short) Macro.font.stringWidth(this.strContent[i]);
            }
        }
        this.shtRectHeight = (short) (this.strContent.length * Macro.FONTHEIGHT + 30);
        this.intInputMax = intInputMax;
        this.initDummyKeyBoard(this.bytType = 4);
        this.shtRectWordY = (short) ((Macro.SCREEN_HEIGHT - this.shtRectHeight) / 2 + 16);
        this.shtRectWordX = (short) ((Macro.SCREEN_WIDTH - this.bytRectWidth * 16 >> 1) + (this.bytRectWidth * 16 - this.shtRectWordW >> 1));
    }

    public short setTwoRect(String setStringName, int intColor, short bytMove, byte bytState, byte bytType, int intTempKey) {
        this.intTempKey = intTempKey;
        this.intColor = intColor;
        this.bytMove = bytMove;
        this.bytState = bytState;
        setStringName = this.setStringName(setStringName);
        this.shtRectWordW = 0;
        TwoDialogUI.two_dialog_Rgb = new int[4];
        TwoDialogUI.two_dialog_Rgb = DrawBase.getElementRGB(2, 2, 11370322, 127);
        if (this.WordContent == null) {
            this.WordContent = new AnalysisWordContent();
        }
        int maxShowWidth = Macro.SCREEN_WIDTH >> 1;
        String displaceNpcTalk = StringManager.displaceNpcTalk(setStringName);
        this.WordContent.analysisChatContent(displaceNpcTalk, intColor, maxShowWidth);
        this.strContent = this.WordContent.wenZi();
        if (this.strContent.length * Macro.FONTHEIGHT + 38 <= Macro.SCREEN_HEIGHT >> 1) {
            this.MaxShowWidth = maxShowWidth;
        } else {
            this.MaxShowWidth = (short) (Macro.SCREEN_WIDTH - 24 - (Macro.FONTW << 1));
        }
        this.fontwidtharray = new int[setStringName.length()];
        this.WordContent.analysisChatContent(displaceNpcTalk, intColor, this.MaxShowWidth);
        this.strContent = this.WordContent.wenZi();
        for (int i = 0; i < this.strContent.length; ++i) {
            this.fontwidtharray[i] = Macro.font.stringWidth(this.strContent[i]);
        }
        if (Common.Max(this.fontwidtharray) <= this.MaxShowWidth) {
            this.bytRectWidth = this.MaxShowWidth;
        } else {
            this.bytRectWidth = this.MaxShowWidth;
        }
        for (int j = 0; j < this.strContent.length; j = (byte) (j + 1)) {
            if (Macro.font.stringWidth(this.strContent[j]) > this.shtRectWordW) {
                this.shtRectWordW = (short) Macro.font.stringWidth(this.strContent[j]);
            }
        }
        switch (bytType) {
            case 1: {
                this.shtRectHeight = (short) (this.strContent.length * Macro.FONTHEIGHT + 38);
                break;
            }
            case 3: {
                this.clearTwoRect();
                FormDes.getInstance().showForm((byte) 20);
                break;
            }
            case 5: {
                this.clearTwoRect();
                FormDes.getInstance().setAddFriendName("");
                break;
            }
            case 4: {
                this.shtKeyHeight = 78;
                MenuObject menuObject = ((MenuObject) Param.getInstance().vMenuMemory.elementAt(Param.getInstance().vMenuMemory.size() - 1)).vNextMenu[this.bytMove][Param.getInstance().bytPopRectStep];
                if (Param.getInstance().hPackage == null) {
                    this.setTwoNumber(setStringName, menuObject.intMax, this.bytState, this.intTempKey, bytMove);
                } else {
                    PackageObject packageObject = (PackageObject) Param.getInstance().hPackage.get(new Integer(MenuUI.getInstance().getPropRectMove()));
                    if (menuObject.intMax == -1) {
                        this.intInputMax = ((packageObject.shtPOnum > 0) ? packageObject.shtPOnum : packageObject.bytPOnumMax);
                    } else {
                        this.intInputMax = menuObject.intMax;
                    }
                }
                this.shtRectHeight = (short) (this.strContent.length * Macro.FONTHEIGHT + 30);
                this.initDummyKeyBoard(bytType);
                break;
            }
            case 2: {
                short n = 0;
                this.bytRoll = n;
                this.bytMove = n;
                this.initSetKey();
                break;
            }
            default: {
                this.shtRectHeight = (short) (this.strContent.length * Macro.FONTHEIGHT + 16);
                break;
            }
        }
        this.shtRectWordY = (short) ((Macro.SCREEN_HEIGHT - this.shtRectHeight) / 2 + 16);
        this.shtRectWordX = (short) ((Macro.SCREEN_WIDTH - (this.bytRectWidth + Macro.FONTW + 4) >> 1) + (this.bytRectWidth + Macro.FONTW + 4 - this.shtRectWordW >> 1));
        this.bytType = bytType;
        return (short) (this.shtRectWordX + this.shtRectWordW + 3);
    }

    public void setChooseRect(byte bytMenuState, Vector member, String titleName, byte bytType) {
        this.shtOneMenuMove = Param.getInstance().shtOneMenuMove;
        this.shtOneMenuRoll = Param.getInstance().shtOneMenuRoll;
        this.bytType = bytType;
        this.bytMenuState = bytMenuState;
        this.TitleName = titleName;
        this.shtTitle_w = Macro.font.stringWidth(titleName) + (Macro.FONTW << 2);
        this.shtTitle_h = 32;
        this.shtTitle_x = Macro.SCREEN_WIDTH - this.shtTitle_w >> 1;
        this.BackGroundW = Macro.SCREEN_WIDTH * 9 / 10;
        this.BackGroundH = Macro.SCREEN_HEIGHT * 7 / 10;
        this.BackGroundX = Macro.SCREEN_WIDTH - this.BackGroundW >> 1;
        this.BackGroundY = Macro.SCREEN_HEIGHT / 11;
        this.shtTitle_y = this.BackGroundY - 20;
        this.member = member;
        if (member != null) {
            this.shtMenuMoveLength = (short) member.size();
            this.bytWordMaxH = (byte) ((this.BackGroundH - (Macro.FONTHEIGHT << 1)) / Macro.FONTHEIGHT);
        }
    }

    public void paint(Graphics graphics) {
        short n = (short) (this.shtRectWordY - 5);
        try {
            switch (this.bytType) {
                case 1: {
                    DrawBase.drawBoxInMenu(Macro.SCREEN_WIDTH - (this.bytRectWidth + Macro.FONTW + 4) >> 1, (Macro.SCREEN_HEIGHT - this.shtRectHeight) / 2, this.bytRectWidth + Macro.FONTW + 4, this.shtRectHeight, new int[]{6494722, 16777215, 16763066, 11370322}, true, (byte) 1);
                    if (this.intColor != -1) {
                        StringManager.drawPropName(graphics, this.strContent, this.shtRectWordX, n, this.intColor);
                        break;
                    }
                    if (this.WordContent != null) {
                        this.WordContent.drawString(graphics, this.shtRectWordX, n);
                        break;
                    }
                    break;
                }
                case 3: {
                    DrawBase.drawBoxInMenu(this.BackGroundX, this.BackGroundY, this.BackGroundW, this.BackGroundH, new int[]{6179098, 6179098, 14020057, 6179098, 12818766, 12818766, 12818766, 12818766}, true, (byte) 3);
                    LayoutStyle.getInstance().drawEarBox(graphics, "", 9263661, this.shtTitle_x, this.shtTitle_y, this.shtTitle_w, this.shtTitle_h);
                    DrawBase.DrawString(this.TitleName, this.shtTitle_x, this.shtTitle_y, this.shtTitle_x + (this.shtTitle_w >> 1), this.shtTitle_y + (this.shtTitle_h >> 1), this.shtTitle_w, this.shtTitle_h, (short) 0, 9263661);
                    this.drawOption(graphics, this.bytMenuState, this.member, (short) (this.BackGroundX + 8), (short) (this.BackGroundY + 12), this.shtOneMenuMove, this.shtOneMenuRoll, this.bytWordMaxH, Macro.FONTHEIGHT);
                    DCanvas.getInstance().setOptionSpoolr(graphics, this.BackGroundX + this.BackGroundW - (ScrollText.arrowhead_width >> 1), this.BackGroundY, this.BackGroundY + this.BackGroundH - Macro.FONTHEIGHT - 2, this.bytWordMaxH, this.shtMenuMoveLength, this.shtOneMenuMove + this.shtOneMenuRoll, false);
                    break;
                }
                case 2: {
                    this.drawSetKey(graphics);
                    break;
                }
                case 19: {
                    this.drawPackageSetKey(graphics);
                    break;
                }
                case 4: {
                    DCanvas.getInstance().drawCueRect(graphics, this.bytRectWidth * 16, this.shtRectHeight);
                    graphics.setColor(16314576);
                    for (byte b = 0; b < this.strContent.length; ++b) {
                        graphics.drawString(this.strContent[b], this.shtRectWordX, n + 1 + b * Macro.FONTHEIGHT, 20);
                    }
                    this.drawInputNumber(graphics, this.intTempKey, (short) (n + this.strContent.length * Macro.FONTHEIGHT), 77);
                    FictitiousKeyBoard.getInstance().drawKeyBoard(graphics);
                    break;
                }
                case 100: {
                    short n2 = (short) ((Macro.SCREEN_WIDTH - this.bytRectWidth * 16) / 2);
                    DrawBase.drawBox(n2, (short) ((Macro.SCREEN_HEIGHT - this.shtRectHeight) / 2), this.bytRectWidth * 16, this.shtRectHeight, new int[]{8399402, 8399402, 16314576}, true);
                    LayoutStyle.getInstance().drawSelectBox(n2 + 4, this.shtRectWordY + this.bytMove * Macro.FONTHEIGHT, this.bytRectWidth * 16 - 8, Macro.FONTHEIGHT);
                    for (byte b2 = 0; b2 < this.strContent.length / 3; ++b2) {
                        graphics.setColor(Macro.INT_PROP_COLOR[Integer.parseInt(this.strContent[b2 * 3])]);
                        graphics.drawString(this.strContent[b2 * 3 + 2], this.shtRectWordX, this.shtRectWordY + b2 * Macro.FONTHEIGHT, 20);
                    }
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void drawBlackWord(Graphics graphics, short n, short n2) {
        graphics.setColor(16642234);
        int n3 = 0;
        for (byte b = 0; b < this.strContent.length; ++b) {
            n3 = (byte) this.strContent[b].indexOf("...");
            if (n3 != -1) {
                graphics.drawString(this.strContent[b].substring(0, n3 + this.bytWrodFlash / 3), n, n2 + b * Macro.FONTHEIGHT, 20);
            } else {
                graphics.drawString(this.strContent[b], n, n2 + b * Macro.FONTHEIGHT, 20);
            }
        }
        if (n3 != -1) {
            ++this.bytWrodFlash;
            if (this.bytWrodFlash > 9) {
                this.bytWrodFlash = 0;
            }
        }
    }

    private void keyLeftSoft_Option() {
        DCanvas.getInstance().buttonLeftFlash = 0;
        int int1 = Integer.parseInt(this.strContent[this.bytMove * 3 + 1]);
        DCanvas.getInstance().setNetLoad(true);
        NetSend.getInstance().sendSeeChatProp(int1);
    }

    private void keyLeftSoft_YN() {
        DCanvas.getInstance().buttonLeftFlash = 0;
        if (Param.getInstance().strInputName != null) {
            byte b = (byte) Param.getInstance().vMenuMemory.size();
            int n = ((MenuObject) Param.getInstance().vMenuMemory.elementAt(0)).intsOptionId[MenuUI.getInstance().getOneMove()];
            byte bytStep = ((MenuObject) Param.getInstance().vMenuMemory.elementAt(b - 1)).bytStep;
            DCanvas.getInstance().setNetLoad(true);
            NetSend.getInstance().sendNpcPaiM(Param.getInstance().oSelectNpc.intUserId, bytStep, n, (byte) MenuUI.getInstance().getOneMove(), Param.getInstance().strInputName);
        } else if (MenuUI.getInstance().blnIsTemp) {
            DCanvas.getInstance().setNetLoad(true);
            NetSend.getInstance().sendNpcIsOption(Param.getInstance().oSelectNpc.intUserId, MenuUI.getInstance().bytTempStep, MenuUI.getInstance().intTempOptionId, (byte) 1);
        } else {
            switch (this.bytState) {
                case 61: {
                    DCanvas.getInstance().setNetLoad(true);
                    MenuUI.getInstance().isDrawSkillinfo = false;
                    if (MenuUI.getInstance().bytTitleMove == 0) {
                        NetSend.getInstance().sendSkillUp(1, ((SkillObject) Param.getInstance().vSkillOccupationBefore.elementAt(MenuUI.getInstance().getOneMove())).intId);
                        break;
                    }
                    NetSend.getInstance().sendSkillUp(1, ((SkillObject) Param.getInstance().vSkillOccupationLater.elementAt(MenuUI.getInstance().getOneMove())).intId);
                    break;
                }
                case -35: {
                    ORPMe.ME.delStall();
                    GameControl.getInstance().delUIbase(3);
                    NetSend.getInstance().sendShopHandle((byte) 6);
                    GameUI.getInstance().setButton();
                    break;
                }
                case 1: {
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendNpcIsOption(Param.getInstance().oSelectNpc.intUserId, ((MenuObject) Param.getInstance().vMenuMemory.elementAt((byte) Param.getInstance().vMenuMemory.size() - 1)).bytStep, ((MenuObject) Param.getInstance().vMenuMemory.elementAt(0)).intsOptionId[MenuUI.getInstance().getOneMove()], (byte) 1);
                    break;
                }
                case 10: {
                    if (MenuUI.getInstance().bytMoveType != 1) {
                        int intId = ((PackageObject) Param.getInstance().hPackageEquip.get(new Integer(MenuUI.getInstance().bytRoleMove))).intId;
                        Param.getInstance().hPackageEquip.remove(new Integer(MenuUI.getInstance().bytRoleMove));
                        NetSend.getInstance().sendFrameEquip((byte) 3, MenuUI.getInstance().bytRoleMove, intId);
                        break;
                    }
                    if (this.intTempKey == -1) {
                        int intId2 = ((PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()))).intId;
                        Param.getInstance().hPackage.remove(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()));
                        NetSend.getInstance().sendFrameEquip((byte) 6, Param.getInstance().personalPackage.getCellBoxIndex(), intId2);
                        break;
                    }
                    if (this.intTempKey == 1) {
                        DCanvas.getInstance().setNetLoad(true);
                        NetSend.getInstance().sendUpdatePacket((byte) 1, (byte) 56);
                        break;
                    }
                    if (this.intTempKey == 2) {
                        int intId3 = ((PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()))).intId;
                        DCanvas.getInstance().setNetLoad(true);
                        NetSend.getInstance().sendFrameEquip((byte) 4, Param.getInstance().personalPackage.getCellBoxIndex(), intId3);
                        break;
                    }
                    break;
                }
                case 58: {
                    if (MenuUI.getInstance().bytMoveType != 1) {
                        int intId4 = ((PackageObject) Param.getInstance().hPetPackEquip.get(new Integer(MenuUI.getInstance().bytPetMove))).intId;
                        Param.getInstance().hPetPackEquip.remove(new Integer(MenuUI.getInstance().bytPetMove));
                        NetSend.getInstance().sendFrameEquip((byte) 3, MenuUI.getInstance().bytPetMove, intId4);
                        break;
                    }
                    if (this.intTempKey == -1) {
                        int intId5 = ((PackageObject) Param.getInstance().hPackage.get(new Integer(MenuUI.getInstance().getPropRectMove()))).intId;
                        Param.getInstance().hPackage.remove(new Integer(MenuUI.getInstance().getPropRectMove()));
                        NetSend.getInstance().sendFrameEquip((byte) 6, MenuUI.getInstance().getPropRectMove(), intId5);
                        break;
                    }
                    if (this.intTempKey == 1) {
                        DCanvas.getInstance().setNetLoad(true);
                        NetSend.getInstance().sendUpdatePacket((byte) 1, (byte) 57);
                        break;
                    }
                    if (this.intTempKey == 2) {
                        int intId6 = ((PackageObject) Param.getInstance().hPackage.get(new Integer(MenuUI.getInstance().getPropRectMove()))).intId;
                        DCanvas.getInstance().setNetLoad(true);
                        NetSend.getInstance().sendFrameEquip((byte) 4, MenuUI.getInstance().getPropRectMove(), intId6);
                        break;
                    }
                    break;
                }
                case 36: {
                    if (this.intTempKey == -1) {
                        int intId7 = ((PackageObject) Param.getInstance().hPackage.get(new Integer(MenuUI.getInstance().getPropRectMove()))).intId;
                        Param.getInstance().hPackage.remove(new Integer(MenuUI.getInstance().getPropRectMove()));
                        NetSend.getInstance().sendFrameLeechdom((byte) 4, MenuUI.getInstance().getPropRectMove(), intId7);
                        MenuUI.getInstance().setInfoContent(Param.getInstance().hPackage, MenuUI.getInstance().getPropRectMove());
                        break;
                    }
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendUpdatePacket((byte) 1, this.bytState);
                    break;
                }
                case 37: {
                    if (this.intTempKey == -1) {
                        int intId8 = ((PackageObject) Param.getInstance().hPackage.get(new Integer(MenuUI.getInstance().getPropRectMove()))).intId;
                        Param.getInstance().hPackage.remove(new Integer(MenuUI.getInstance().getPropRectMove()));
                        NetSend.getInstance().sendFrameStuff((byte) 2, MenuUI.getInstance().getPropRectMove(), intId8);
                        MenuUI.getInstance().setInfoContent(Param.getInstance().hPackage, MenuUI.getInstance().getPropRectMove());
                        break;
                    }
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendUpdatePacket((byte) 1, this.bytState);
                    break;
                }
                case 38: {
                    int intId9 = ((PackageObject) Param.getInstance().hPackage.get(new Integer(MenuUI.getInstance().getPropRectMove()))).intId;
                    Param.getInstance().hPackage.remove(new Integer(MenuUI.getInstance().getPropRectMove()));
                    NetSend.getInstance().sendFrameTaskprop((byte) 4, MenuUI.getInstance().getPropRectMove(), intId9);
                    MenuUI.getInstance().setInfoContent(Param.getInstance().hPackage, MenuUI.getInstance().getPropRectMove());
                    break;
                }
                case 39: {
                    if (this.intTempKey == -1) {
                        int intId10 = ((PackageObject) Param.getInstance().hPackage.get(new Integer(MenuUI.getInstance().getPropRectMove()))).intId;
                        Param.getInstance().hPackage.remove(new Integer(MenuUI.getInstance().getPropRectMove()));
                        NetSend.getInstance().sendFramePeculiar((byte) 4, MenuUI.getInstance().getPropRectMove(), intId10, -1, (byte) 0);
                        MenuUI.getInstance().setInfoContent(Param.getInstance().hPackage, MenuUI.getInstance().getPropRectMove());
                        break;
                    }
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendUpdatePacket((byte) 1, this.bytState);
                    break;
                }
                case 33: {
                    int petKey = ((PackageObject) Param.getInstance().hPackage.get(new Integer(MenuUI.getInstance().getPropRectMove()))).petKey;
                    if (ORPMe.ME.intPetId == petKey) {
                        ORPMe.ME.delPet();
                        ORPMe.ME.intPetId = 0;
                    }
                    Param.getInstance().hPackage.remove(new Integer(MenuUI.getInstance().getPropRectMove()));
                    if (Param.getInstance().hPackage.isEmpty()) {
                        Param.getInstance().hPackage = null;
                        Param.getInstance().hImgObject = null;
                    } else {
                        Hashtable hPackage = new Hashtable();
                        int n2 = 0;
                        Enumeration elements = (Enumeration) Param.getInstance().hPackage.elements();
                        while (elements.hasMoreElements()) {
                            hPackage.put(new Integer(n2), elements.nextElement());
                            n2 = (byte) (n2 + 1);
                        }
                        Param.getInstance().hPackage = hPackage;
                    }
                    NetSend.getInstance().sendPetHandle((byte) 4, petKey);
                    break;
                }
                case 63: {
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendTaskList((byte) 3, ((PackageObject) Param.getInstance().vTaskList.elementAt(MenuUI.getInstance().getOneMove())).intId);
                    if (DCanvas.getInstance().UMenu != null && MenuUI.getInstance().vTempVessel != null) {
                        MenuUI.getInstance().vTempVessel = null;
                        break;
                    }
                    break;
                }
            }
        }
        this.clearTwoRect();
    }

    private void keyRightSoft_YN() {
        DCanvas.getInstance().buttonRightFlash = 0;
        switch (this.bytState) {
            case 10: {
                if (MenuUI.getInstance().bytMoveType == 1) {
                    MenuUI.getInstance().setInfoContent(Param.getInstance().hPackage, Param.getInstance().personalPackage.getCellBoxIndex());
                    break;
                }
                MenuUI.getInstance().setInfoContent(Param.getInstance().hPackageEquip, MenuUI.getInstance().bytRoleMove);
                break;
            }
            case 36:
            case 37:
            case 38:
            case 39: {
                MenuUI.getInstance().setInfoContent(Param.getInstance().hPackage, Param.getInstance().personalPackage.getCellBoxIndex());
                break;
            }
        }
        this.clearTwoRect();
    }

    private void keyLeftSoft_KEY() {
        DCanvas.getInstance().buttonLeftFlash = 0;
        byte b = (byte) (DCanvas.getInstance().bytDrawKeyIndex[this.bytMove] + this.bytRoll * 13);
        switch (this.bytState) {
            case -81: {
                GameUI.getInstance().setSystemKey(b, MenuUI.getInstance().bytsFunction[MenuUI.getInstance().getOneMove()]);
                MenuUI.getInstance().initKey();
                break;
            }
            case 61: {
                if (MenuUI.getInstance().bytTitleMove == 0 || MenuUI.getInstance().bytTitleMove == 1) {
                    short oneMove = MenuUI.getInstance().getOneMove();
                    if (DCanvas.getInstance().UMenu != null && MenuUI.getInstance().bytMenuState == 61 && MenuUI.getInstance().bytTitleMove == 1) {
                        GameUI.getInstance().setSkillKey(MenuUI.searchSkillIndexById(Param.getInstance().vSkillList, ((SkillObject) Param.getInstance().vSkillOccupationLater.elementAt(oneMove)).intId), b);
                        break;
                    }
                    if (DCanvas.getInstance().UMenu != null && MenuUI.getInstance().bytMenuState == 61 && MenuUI.getInstance().bytTitleMove == 0) {
                        GameUI.getInstance().setSkillKey(MenuUI.searchSkillIndexById(Param.getInstance().vSkillList, ((SkillObject) Param.getInstance().vSkillOccupationBefore.elementAt(oneMove)).intId), b);
                        break;
                    }
                } else {
                    if (MenuUI.getInstance().bytTitleMove == 2) {
                        MenuUI.getInstance().setBookPackage(this.packageInstance.getCellBoxIndex(), (byte) MenuUI.getInstance().getOneMove());
                        break;
                    }
                }
                break;
            }
            case 36:
            case 38:
            case 39: {
                GameUI.getInstance().setGoodsKey(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()), b);
                MenuUI.getInstance().setInfoContent(Param.getInstance().hPackage, Param.getInstance().personalPackage.getCellBoxIndex());
                break;
            }
        }
        this.clearTwoRect();
    }

    public void logicTwoRect() {
        switch (this.bytType) {
            case 1: {
                if (DCanvas.getInstance().IsKeyRelease(65536) || DCanvas.getInstance().IsKeyRelease(262144)) {
                    this.keyLeftSoft_YN();
                    break;
                }
                if (DCanvas.getInstance().isKeyDown(65536) || DCanvas.getInstance().isKeyDown(262144)) {
                    DCanvas.getInstance().buttonLeftFlash = 1;
                    break;
                }
                if (DCanvas.getInstance().IsKeyRelease(131072)) {
                    this.keyRightSoft_YN();
                    break;
                }
                if (DCanvas.getInstance().isKeyDown(131072)) {
                    DCanvas.getInstance().buttonRightFlash = 1;
                    break;
                }
                break;
            }
            case 19: {
                if (DCanvas.getInstance().IsKeyRelease(65536) || DCanvas.getInstance().IsKeyRelease(262144)) {
                    this.keyLeftSoft_KEY();
                    break;
                }
                if (DCanvas.getInstance().isKeyDown(65536) || DCanvas.getInstance().isKeyDown(262144)) {
                    DCanvas.getInstance().buttonLeftFlash = 1;
                    break;
                }
                if (DCanvas.getInstance().IsKeyRelease(131072)) {
                    DCanvas.getInstance().buttonRightFlash = 0;
                    this.clearTwoRect();
                    break;
                }
                if (DCanvas.getInstance().isKeyDown(131072)) {
                    DCanvas.getInstance().buttonRightFlash = 1;
                    break;
                }
                if (DCanvas.getInstance().isKeyDown(16384)) {
                    this.packageInstance.getKeyLeft();
                    break;
                }
                if (DCanvas.getInstance().isKeyDown(32768)) {
                    this.packageInstance.getKeyRight();
                    break;
                }
                if (DCanvas.getInstance().isKeyDown(4096)) {
                    if (this.packageInstance.getLineIndex() > 0) {
                        this.packageInstance.getKeyUp();
                        break;
                    }
                } else {
                    if (DCanvas.getInstance().isKeyDown(8192) && this.packageInstance.getLineIndex() < this.packageInstance.getLineNum() - 1) {
                        this.packageInstance.getKeyDown();
                        break;
                    }
                }
                break;
            }
            case 2: {
                if (DCanvas.getInstance().IsKeyRelease(65536) || DCanvas.getInstance().IsKeyRelease(262144)) {
                    this.keyLeftSoft_KEY();
                    break;
                }
                if (DCanvas.getInstance().isKeyDown(65536) || DCanvas.getInstance().isKeyDown(262144)) {
                    DCanvas.getInstance().buttonLeftFlash = 1;
                    break;
                }
                if (DCanvas.getInstance().IsKeyRelease(131072)) {
                    DCanvas.getInstance().buttonRightFlash = 0;
                    this.clearTwoRect();
                    break;
                }
                if (DCanvas.getInstance().isKeyDown(131072)) {
                    DCanvas.getInstance().buttonRightFlash = 1;
                    break;
                }
                if (DCanvas.getInstance().isKeyDown(16384)) {
                    this.keyLeft_YN();
                    break;
                }
                if (DCanvas.getInstance().isKeyDown(32768)) {
                    this.keyRight_YN();
                    break;
                }
                if (DCanvas.getInstance().isKeyDown(4096) || DCanvas.getInstance().isKeyDown(8192) || !DCanvas.getInstance().isKeyDown(512)) {
                    break;
                }
                if (this.bytCutKeysIndex == 0) {
                    this.bytCutKeysIndex = 1;
                    this.bytRoll = 1;
                    break;
                }
                if (this.bytCutKeysIndex == 1) {
                    this.bytCutKeysIndex = 0;
                    this.bytRoll = 0;
                    break;
                }
                break;
            }
            case 4: {
                if (DCanvas.getInstance().IsKeyRelease(65536) || DCanvas.getInstance().IsKeyRelease(262144)) {
                    this.keyLeftSoftNumber();
                    break;
                }
                if (DCanvas.getInstance().isKeyDown(65536) || DCanvas.getInstance().isKeyDown(262144)) {
                    DCanvas.getInstance().buttonLeftFlash = 1;
                    break;
                }
                if (DCanvas.getInstance().IsKeyRelease(131072)) {
                    DCanvas.getInstance().buttonRightFlash = 0;
                    this.clearTwoRect();
                    FictitiousKeyBoard.getInstance().clear();
                    if (MenuUI.getInstance().blnNpcMailSendGood) {
                        MenuUI.getInstance().setInfoContent(Param.getInstance().hPackage, Param.getInstance().personalPackage.getCellBoxIndex());
                        break;
                    }
                    break;
                } else {
                    if (DCanvas.getInstance().isKeyDown(131072)) {
                        DCanvas.getInstance().buttonRightFlash = 1;
                        break;
                    }
                    if (DCanvas.getInstance().IsKeyRelease(16384)) {
                        this.setKeyLeft();
                        break;
                    }
                    if (DCanvas.getInstance().IsKeyRelease(32768)) {
                        this.setKeyRight();
                        break;
                    }
                    this.setKeyInputNumber(DCanvas.getInstance().getKeyShortcut());
                    DCanvas.getInstance().clearKey();
                }
                break;
            }
            case 100: {
                if (DCanvas.getInstance().IsKeyRelease(65536) || DCanvas.getInstance().IsKeyRelease(262144)) {
                    this.keyLeftSoft_Option();
                    break;
                }
                if (DCanvas.getInstance().isKeyDown(65536) || DCanvas.getInstance().isKeyDown(262144)) {
                    DCanvas.getInstance().buttonLeftFlash = 1;
                    break;
                }
                if (DCanvas.getInstance().IsKeyRelease(131072)) {
                    DCanvas.getInstance().buttonRightFlash = 0;
                    this.clearTwoRect();
                    break;
                }
                if (DCanvas.getInstance().isKeyDown(131072)) {
                    DCanvas.getInstance().buttonRightFlash = 1;
                    break;
                }
                if (DCanvas.getInstance().IsKeyRelease(4096)) {
                    this.keyUpDialogOptions();
                    break;
                }
                if (DCanvas.getInstance().IsKeyRelease(8192)) {
                    this.keyDownDialogOptions();
                    break;
                }
                break;
            }
        }
        if (DCanvas.getInstance().blnIsTouch && DCanvas.getInstance().blnPointerPressed) {
            if (DCanvas.getInstance().PointerDwonSoftKey_Left()) {
                switch (this.bytType) {
                    case 1: {
                        this.keyLeftSoft_YN();
                        break;
                    }
                    case 2: {
                        this.keyLeftSoft_KEY();
                        break;
                    }
                    case 4: {
                        this.keyLeftSoftNumber();
                        break;
                    }
                }
                if (this.bytState == 61 && MenuUI.getInstance().bytTitleMove == 2) {
                    this.keyLeftSoft_KEY();
                }
            } else if (DCanvas.getInstance().PointerDwonSoftKey_Right()) {
                switch (this.bytType) {
                    case 1: {
                        this.keyRightSoft_YN();
                        break;
                    }
                    case 2: {
                        DCanvas.getInstance().buttonRightFlash = 0;
                        this.clearTwoRect();
                        break;
                    }
                    case 4: {
                        MenuUI.getInstance().setButton(1, 4, 2);
                        Param.getInstance().intMallNum = 1;
                        DCanvas.getInstance().buttonRightFlash = 0;
                        this.clearTwoRect();
                        if (MenuUI.getInstance().blnNpcMailSendGood) {
                            MenuUI.getInstance().setInfoContent(Param.getInstance().hPackage, Param.getInstance().personalPackage.getCellBoxIndex());
                            break;
                        }
                        break;
                    }
                }
                if (this.bytState == 61 && MenuUI.getInstance().bytTitleMove == 2) {
                    this.keyRightSoft_YN();
                }
            } else if (this.bytType == 2 || this.bytType == 4 || this.bytType == -19) {
                this.pointDummyKeyBoard();
                if (this.bytType == -19) {
                    if (Param.getInstance().mallCardSelect == 0) {
                        Param.getInstance().MALL_CARD_NUMBER = new StringBuffer().append(this.intTempKey).toString();
                    } else {
                        Param.getInstance().MALL_PASSWORD_NUMBER = new StringBuffer().append(this.intTempKey).toString();
                    }
                }
            } else if (this.bytType == 100) {
                this.pointOption();
            } else if (this.bytState == 61 && MenuUI.getInstance().bytTitleMove == 2) {
                this.packageInstance.getTouch();
            }
        }
    }

    private void setKeyLeft() {
        String string = Integer.toString(this.intTempKey);
        if (string.length() >= 2) {
            this.intTempKey = Integer.parseInt(string.substring(0, string.length() - 1));
        } else {
            this.intTempKey = 0;
        }
        this.bytKeyBoardIndex = 9;
    }

    private void setKeyRight() {
        this.intTempKey = this.intInputMax;
    }

    private void setKeyInputNumber(int n) {
        if (n != -1) {
            String string = String.valueOf(Integer.toString(this.intTempKey)) + n;
            int intTempKey;
            try {
                intTempKey = Integer.parseInt(string);
            } catch (Exception ex) {
                intTempKey = this.intInputMax;
            }
            if (intTempKey > this.intInputMax) {
                this.intTempKey = this.intInputMax;
            } else {
                this.intTempKey = intTempKey;
            }
            this.bytKeyBoardIndex = (byte) ((n - 1 < 0) ? 10 : (n - 1));
        }
    }

    private void keyLeftSoftNumber() {
        this.bytKeyBoardIndex = 11;
        DCanvas.getInstance().buttonLeftFlash = 0;
        if (this.intTempKey > 0) {
            if (Param.getInstance().oSelectNpc != null) {
                if (this.bytState == -43) {
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendNpcMoveOption((byte) this.bytMove, (short) 0, this.intTempKey);
                    GameControl.getInstance().delUIbase(7);
                    MenuUI.getInstance().setButton(1, 4, 2);
                    this.clearTwoRect();
                } else {
                    Param.getInstance().intMallNum = this.intTempKey;
                    FictitiousKeyBoard.getInstance().clear();
                    GameControl.getInstance().delUIbase(7);
                    this.clearTwoRect();
                    MenuUI.getInstance().shopNPC();
                }
            } else {
                switch (this.bytState) {
                    case -43:
                    case -39: {
                        NetSend.getInstance().sendNpcMoveOption(this.bytMove, (short) 0, this.intTempKey);
                        GameControl.getInstance().delUIbase(7);
                        MenuUI.getInstance().setButton(1, 4, 2);
                        this.clearTwoRect();
                        break;
                    }
                    case -11: {
                        Param.getInstance().intMallNum = this.intTempKey;
                        GameControl.getInstance().delUIbase(7);
                        MenuUI.getInstance().setButton(1, 4, 2);
                        this.clearTwoRect();
                        if (Param.getInstance().hMallPackage == null || !Param.getInstance().hMallPackage.containsKey(new Integer(MenuUI.getInstance().tabStyleInstance.getTabCurrentIndex()))) {
                            DCanvas.getInstance().addInformation("Mục này đã được chấp nhận");
                            break;
                        }
                        if (Macro.FEE_POINT >= ((PackageObject) ((Hashtable) Param.getInstance().hMallPackage.get(new Integer(MenuUI.getInstance().tabStyleInstance.getTabCurrentIndex()))).get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()))).intPrice * Param.getInstance().intMallNum) {
                            MenuUI.getInstance().mallShop();
                            break;
                        }
                        DCanvas.getInstance().addInformation("Thiếu điểm");
                        break;
                    }
                    case -35: {
                        if (Param.getInstance().bytPopRectStep == 0) {
                            Param.getInstance().intNpcNum = this.intTempKey;
                            Param.getInstance().bytPopRectStep = 1;
                            this.setTwoNumber("Vui lòng nhập tổng giá của mặt hàng cần bán:", 999999999, this.bytState, -1, this.bytMove);
                        } else if (Param.getInstance().bytPopRectStep != 3) {
                            PackageObject packageObject = (PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()));
                            PackageObject packageObject2 = new PackageObject();
                            packageObject2.intId = packageObject.intId;
                            packageObject2.shtIcon = packageObject.shtIcon;
                            packageObject2.intPrice = packageObject.intPrice;
                            packageObject2.shtLevel = packageObject.shtLevel;
                            packageObject2.bytSeal = packageObject.bytSeal;
                            packageObject2.strPropName = packageObject.strPropName;
                            packageObject2.strName = packageObject.strName;
                            packageObject2.strInfo = packageObject.strInfo;
                            packageObject2.strExplain = packageObject.strExplain;
                            packageObject2.bytPOnumMax = packageObject.bytPOnumMax;
                            packageObject2.intColor = packageObject.intColor;
                            packageObject2.bytQuality = packageObject.bytQuality;
                            packageObject2.bytOperation = packageObject.bytOperation;
                            packageObject2.bytIsBind = packageObject.bytIsBind;
                            packageObject2.bytKey = packageObject.bytKey;
                            packageObject2.shtStamina = packageObject.shtStamina;
                            packageObject2.shtStaminaMax = packageObject.shtStaminaMax;
                            packageObject2.bytAggrandizementLv = packageObject.bytAggrandizementLv;
                            packageObject2.bytsAggrandizement = packageObject.bytsAggrandizement;
                            packageObject2.strPveNumber = packageObject.strPveNumber;
                            packageObject2.strPvpNumber = packageObject.strPvpNumber;
                            packageObject2.byteEquipType = packageObject.byteEquipType;
                            packageObject2.byteEquipPart = packageObject.byteEquipPart;
                            packageObject2.strSuitName = packageObject.strSuitName;
                            packageObject2.strSuitInfo = packageObject.strSuitInfo;
                            packageObject2.bytsAttribute = packageObject.bytsAttribute;
                            packageObject2.shtsAttributeInfo = packageObject.shtsAttributeInfo;
                            packageObject2.blnUse = true;
                            packageObject2.bytBagPlace = (byte) packageObject.intPOindex;
                            if (Param.getInstance().bytPopRectStep == 1) {
                                short shtPOnum = packageObject.shtPOnum;
                                packageObject2.shtPOnum = (short) Param.getInstance().intNpcNum;
                                packageObject2.intPaiMaiPrice = this.intTempKey;
                                MenuUI.getInstance().addShopProp(packageObject2, packageObject2.bytKey);
                                if (shtPOnum == Param.getInstance().intNpcNum) {
                                    Param.getInstance().hPackage.remove(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()));
                                } else {
                                    packageObject.shtPOnum = (short) (shtPOnum - Param.getInstance().intNpcNum);
                                }
                                Param.getInstance().bytPopRectStep = 0;
                            } else {
                                packageObject2.shtPOnum = packageObject.shtPOnum;
                                packageObject2.intPaiMaiPrice = this.intTempKey;
                                MenuUI.getInstance().addShopProp(packageObject2, packageObject2.bytKey);
                                Param.getInstance().hPackage.remove(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()));
                            }
                            this.clearTwoRect();
                        }
                        if (Param.getInstance().bytPopRectStep == 3) {
                            PackageObject packageObject3 = (PackageObject) Param.getInstance().hShopPackage.get(new Integer(MenuUI.getInstance().personalShopSellPackage.getCellBoxIndex()));
                            if (packageObject3.blnUse) {
                                packageObject3.intPaiMaiPrice = this.intTempKey;
                            } else {
                                DCanvas.getInstance().setNetLoad(true);
                                NetSend.getInstance().sendShop_AmendMoney(MenuUI.getInstance().personalShopSellPackage.getCellBoxIndex(), packageObject3.intId, this.intTempKey);
                            }
                            this.clearTwoRect();
                            break;
                        }
                        break;
                    }
                    case 9: {
                        byte dealState = DialogUI.getInstance().dealState;
                        DialogUI.getInstance().getClass();
                        if (dealState == 2) {
                            if (this.intTempKey <= ORPMe.Gold) {
                                NetSend.getInstance().sendDealGold(this.intTempKey);
                                Param.getInstance().intDealMyGold = this.intTempKey;
                                this.clearTwoRect();
                                break;
                            }
                            DCanvas.getInstance().addInformation("Thiếu tiền");
                            break;
                        } else {
                            byte dealState2 = DialogUI.getInstance().dealState;
                            DialogUI.getInstance().getClass();
                            if (dealState2 != 4) {
                                break;
                            }
                            PackageObject packageObject4 = (PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()));
                            if (Param.getInstance().vDealMyProp == null) {
                                Param.getInstance().vDealMyProp = new Hashtable();
                            }
                            if (Param.getInstance().vDealMyProp.size() < 6) {
                                NetSend.getInstance().sendDealGoods(Param.getInstance().bytDealGoods, packageObject4.intId, (short) packageObject4.intPOindex, (short) this.intTempKey);
                                DialogUI.getInstance().addDealProp(Param.getInstance().vDealMyProp, packageObject4, (short) this.intTempKey);
                                DialogUI.getInstance().dealPackageTabIndex = -1;
                                DialogUI.getInstance().setDealPackageMessage();
                                this.clearTwoRect();
                                break;
                            }
                            DCanvas.getInstance().addInformation("Không còn mục nào để thêm");
                        }
                        break;
                    }
                }
            }
        }
    }

    private void clearTwoRect() {
        if (DCanvas.getInstance().UMenu != null) {
            MenuUI.getInstance().clearTwoRect();
        } else if (DCanvas.getInstance().UDialog != null) {
            DialogUI.getInstance().clearTwoDialog();
        }
        this.strContent = null;
    }

    private void initDummyKeyBoard(byte b) {
        this.shtKeyBoardX = (short) ((Macro.SCREEN_WIDTH - 65) / 2);
        this.bytKeyBoardIndex = 0;
        FictitiousKeyBoard.getInstance().initKeyboard((short) ((Macro.SCREEN_HEIGHT - this.shtRectHeight) / 2 + this.shtRectHeight - 4));
    }

    private void pointDummyKeyBoard() {
        if (this.bytType == 4 || this.bytType == -19) {
            for (int i = 0; i < FictitiousKeyBoard.getInstance().SetKeyNumber; ++i) {
                if (DCanvas.getInstance().IsPointerDown(FictitiousKeyBoard.getInstance().KeyBoardTab.getCell(FictitiousKeyBoard.getInstance().Key_PositionArray[i][0], FictitiousKeyBoard.getInstance().Key_PositionArray[i][1]).getCellX(), FictitiousKeyBoard.getInstance().KeyBoardTab.getCell(FictitiousKeyBoard.getInstance().Key_PositionArray[i][0], FictitiousKeyBoard.getInstance().Key_PositionArray[i][1]).getCellY(), FictitiousKeyBoard.getInstance().KeyBoardTab.getCell(FictitiousKeyBoard.getInstance().Key_PositionArray[i][0], FictitiousKeyBoard.getInstance().Key_PositionArray[i][1]).getCellW(), FictitiousKeyBoard.getInstance().KeyBoardTab.getCell(FictitiousKeyBoard.getInstance().Key_PositionArray[i][0], FictitiousKeyBoard.getInstance().Key_PositionArray[i][1]).getCellH())) {
                    this.bytKeyBoardIndex = i;
                    FictitiousKeyBoard.getInstance().bytKeyFlash = i;
                    if (this.bytKeyBoardIndex < 3) {
                        this.setKeyInputNumber(this.bytKeyBoardIndex + 1);
                    }
                    if (this.bytKeyBoardIndex == 3) {
                        this.setKeyLeft();
                    } else if (this.bytKeyBoardIndex == 4 || this.bytKeyBoardIndex == 5 || this.bytKeyBoardIndex == 6) {
                        this.setKeyInputNumber(this.bytKeyBoardIndex);
                    } else if (this.bytKeyBoardIndex == 7) {
                        this.setKeyInputNumber(0);
                    } else if (this.bytKeyBoardIndex >= 8 && this.bytKeyBoardIndex <= 10) {
                        this.setKeyInputNumber(this.bytKeyBoardIndex - 1);
                    } else if (this.bytKeyBoardIndex == 11) {
                        this.keyLeftSoftNumber();
                        break;
                    }
                }
            }
        } else if (this.bytType == 2) {
            int n = Macro.SCREEN_WIDTH - this.shtKeyBoardX * 2 - (GameUI.getInstance().bytHotKeyBoxWidth + GameUI.getInstance().bytHotKeyBoxDistance);
            int n2 = GameUI.getInstance().bytHotKeyBoxWidth + GameUI.getInstance().bytHotKeyBoxDistance;
            if (DCanvas.getInstance().IsPointerDown(this.shtKeyBoardX, this.shtKeyBoardY, n - 1, 22)) {
                byte bytMove = (byte) ((DCanvas.getInstance().CurPressedX - this.shtKeyBoardX) / n2);
                if (this.bytMove != bytMove) {
                    this.bytMove = bytMove;
                } else {
                    this.keyLeftSoft_KEY();
                }
            }
            if (DCanvas.getInstance().IsPointerDown(this.shtKeyBoardX + this.ShortcutKeysBox_count * n2, this.shtKeyBoardY, n - 1, 22)) {
                if (this.bytCutKeysIndex == 0) {
                    this.bytCutKeysIndex = 1;
                    this.bytRoll = 1;
                } else if (this.bytCutKeysIndex == 1) {
                    this.bytCutKeysIndex = 0;
                    this.bytRoll = 0;
                }
            }
        }
    }

    private void pointOption() {
        if (DCanvas.getInstance().IsPointerDown(this.shtRectWordX, this.shtRectWordY, this.shtRectWordW, this.strContent.length / 3 * Macro.FONTHEIGHT - 1)) {
            byte bytMove = (byte) ((DCanvas.getInstance().CurPressedY - this.shtRectWordY) / Macro.FONTHEIGHT);
            if (this.bytMove == bytMove) {
                this.keyLeftSoft_Option();
            } else {
                this.bytMove = bytMove;
            }
        }
    }

    private void drawInputNumber(Graphics graphics, int n, int n2, int n3) {
        short n4 = (short) ((Macro.SCREEN_WIDTH - n3) / 2 - 1);
        DrawBase.drawBox(n4, n2, n3, Macro.FONTHEIGHT + 2, new int[]{16777215, 15768897, 16314576}, true);
        if (n > 0) {
            this.drawMoneyNum(graphics, n, n4 + n3 - 4, n2 + 4);
        }
    }

    private void initSetKey() {
        this.shtKeyBoardX = (short) ((Macro.SCREEN_WIDTH - 9 * (GameUI.getInstance().bytHotKeyBoxWidth + GameUI.getInstance().bytHotKeyBoxDistance)) / 2);
        this.bytNewLineHeight = (byte) ((Macro.FONTHEIGHT > 18) ? Macro.FONTHEIGHT : 18);
        this.shtKeyBoardH = (short) (GameUI.getInstance().bytHotKeyBoxHeight + this.bytNewLineHeight);
        this.shtKeyBoardY = (short) (Macro.SCREEN_HEIGHT - this.shtKeyBoardH - 30 - 12);
        int n = Macro.font.stringWidth("Đặt phím tắt []" + this.strContent[0]) + 18;
        if (n > Macro.SCREEN_WIDTH) {
            this.shtKeyTitleMoveW = (short) n;
            this.shtKeyTitleMoveX = Macro.SCREEN_WIDTH;
            this.bytKeyBoardIndex = 1;
        } else {
            this.bytKeyBoardIndex = 0;
        }
    }

    private void initPackageSetKey() {
        this.shtKeyBoardH = (short) (2 * (GameUI.getInstance().bytHotKeyBoxHeight + 2));
        this.shtKeyBoardW = (short) (8 * (GameUI.getInstance().bytHotKeyBoxWidth + 2));
        this.shtKeyBoardX = (short) ((Macro.SCREEN_WIDTH - this.shtKeyBoardW) / 2);
        this.shtKeyBoardY = (short) (Macro.SCREEN_HEIGHT - this.shtKeyBoardH - 10 - 30 - 12);
        this.bytNewLineHeight = Macro.FONTHEIGHT;
        int n = Macro.font.stringWidth(this.strContent[0]) + 18;
        if (n > Macro.SCREEN_WIDTH) {
            this.shtKeyTitleMoveW = (short) n;
            this.shtKeyTitleMoveX = Macro.SCREEN_WIDTH;
            this.bytKeyBoardIndex = 1;
        } else {
            this.bytKeyBoardIndex = 0;
        }
    }

    private void drawSetKey(Graphics graphics) {
        int n = this.shtKeyBoardY - this.bytNewLineHeight;
        graphics.setClip(0, n, Macro.SCREEN_WIDTH, this.shtKeyBoardH + this.bytNewLineHeight);
        int n2 = 8;
        DrawBase.drawBox(n2, n, Macro.SCREEN_WIDTH - 2 * n2, this.shtKeyBoardH + this.bytNewLineHeight, new int[]{15768897, 16641976}, true);
        this.drawKey(graphics, this.shtKeyBoardX, this.shtKeyBoardY, this.bytCutKeysIndex);
        int n3 = this.shtKeyTitleMoveX + 2 + n2;
        graphics.setColor(0);
        graphics.drawString("Cài đặt [", n3, n + 4, 20);
        int n4 = n3 + Macro.font.stringWidth("Cài đặt [");
        Image objectImg = GameUI.getInstance().getObjectImg(this.shtIcon);
        if (objectImg != null) {
            GameUI.getInstance().drawOptionImage(graphics, objectImg, (short) n4, (short) (n + 4), (byte) 20);
        }
        n4 += 18;
        graphics.setColor(this.intColor);
        if (this.strContent == null) {
            return;
        }
        graphics.drawString(this.strContent[0], n4, n + 4, 20);
        int n5 = n4 + Macro.font.stringWidth(this.strContent[0]);
        graphics.setColor(0);
        graphics.drawString("] phím tắt", n5, n + 4, 20);
        DCanvas.getInstance().clearScreen();
        if (this.bytKeyBoardIndex == 1) {
            this.moveKeyTitle();
        }
    }

    private void drawPackageSetKey(Graphics graphics) {
        int n = 8;
        int n2 = Macro.SCREEN_WIDTH - 2 * n;
        int n3 = this.shtKeyBoardH + this.bytNewLineHeight + 20;
        int n4 = Macro.SCREEN_HEIGHT - n3 - 30 - 12;
        graphics.setClip(n, n4, n2, n3);
        DrawBase.drawBox(n, n4, n2, n3, new int[]{15768897, 16641976}, true);
        if (this.packageInstance != null) {
            this.packageInstance.draw(this.packageData);
        }
        graphics.setColor(0);
        graphics.setColor(this.intColor);
        if (this.strContent != null) {
            DrawBase.DrawString(this.strContent[0], n, n4 + 10, n2, this.bytNewLineHeight, (short) 3, 0);
        }
        DCanvas.getInstance().clearScreen();
        Param.popupDialogInstance.drawPopupDialog(graphics);
    }

    private void moveKeyTitle() {
        this.shtKeyTitleMoveX -= 4;
        if (this.shtKeyTitleMoveX < -this.shtKeyTitleMoveW) {
            this.shtKeyTitleMoveX = Macro.SCREEN_WIDTH;
        }
    }

    private void drawKey(Graphics graphics, int n, int n2, int n3) {
        int n4 = n + GameUI.getInstance().bytHotKeyBoxWidth / 2;
        int n5 = GameUI.getInstance().bytHotKeyBoxWidth + GameUI.getInstance().bytHotKeyBoxDistance;
        for (int i = 0; i < 8; i = (byte) (i + 1)) {
            GameUI.getInstance().sprHotKey[0].drawAnimationFrame(graphics, 0, 0, n4, n2 + GameUI.getInstance().bytHotKeyBoxHeight);
            n4 += n5;
        }
        LayoutStyle.getInstance().drawChooseFrame(DCanvas.gameG, n + this.bytMove * n5 + GameUI.getInstance().bytHotKeyIconDistance, n2, 22, 23);
        int n6 = 0;
        for (short n7 = 0; n7 < this.ShortcutKeysBox_count; n7 = (byte) (n7 + 1)) {
            int n8 = DCanvas.getInstance().bytDrawKeyIndex[n7] + n3 * 13;
            int n9 = n + n7 * n5 + GameUI.getInstance().bytHotKeyIconDistance + 2;
            n6 = n2 + 3;
            if (Param.getInstance().intShortcutKeys[n8][4] == 2) {
                DrawBase.drawImage(Param.getInstance().imgShortcutKeys[n8], n9, n6, 20);
            } else if (Param.getInstance().intShortcutKeys[n8][4] == 3) {
                DrawBase.drawImage(Param.getInstance().imgShortcutKeys[n8], n9, n6, 20);
            } else if (Param.getInstance().intShortcutKeys[n8][4] == 1 && Param.getInstance().intShortcutKeys[n8][0] != 0) {
                DrawBase.drawImage(Param.getInstance().imgShortcutKeys[n8], n9, n6, 20);
            }
        }
        GameUI.getInstance().drawChangeKey(graphics, n + GameUI.getInstance().bytHotKeyBoxWidth / 2, n2 + GameUI.getInstance().bytHotKeyBoxHeight);
        for (short n10 = 0; n10 < this.ShortcutKeysBox_count + 1; n10 = (byte) (n10 + 1)) {
            GameUI.getInstance().drawWhiteKey(graphics, DCanvas.getInstance().bytDrawKeyIndex[n10], n + n10 * n5 + GameUI.getInstance().bytHotKeyIconDistance + 3, n6 + 3);
        }
    }

    private void keyLeft_YN() {
        --this.bytMove;
        if (this.bytMove < 0) {
            this.bytMove = (short) (this.ShortcutKeysBox_count - 1);
        }
    }

    private void keyRight_YN() {
        ++this.bytMove;
        if (this.bytMove > this.ShortcutKeysBox_count - 1) {
            this.bytMove = 0;
        }
    }

    private void drawMoneyNum(Graphics graphics, int n, int n2, int n3) {
        StringBuffer sb = new StringBuffer(Integer.toString(n));
        int n4 = n2 - sb.length() * 7;
        for (int i = 0; i < sb.length(); ++i) {
            DrawBase.drawRegion(Param.getInstance().imgMoneyNum, n4, n3, (sb.charAt(i) - '0') * 5, 0, 5, 8, 0, 20);
            n4 += 7;
        }
    }

    public byte getBytType() {
        return this.bytType;
    }

    public void drawOption(Graphics graphics, byte b, Vector vector, short n, short n2, short n3, short n4, byte b2, int n5) {
        if (vector == null) {
            return;
        }
        if (vector.isEmpty()) {
            return;
        }
        short n6 = n4;
        this.scroll_OFF = (DCanvas.getInstance().blnSpoolr ? ScrollText.arrowhead_width : 0);
        LayoutStyle.getInstance().drawSelectBox(n, n2 + n3 * n5, this.BackGroundW - 14 - this.scroll_OFF, n5);
        short n7 = (short) (n + 2);
        int size = b2;
        short n8 = Macro.FONTHEIGHT;
        if (size > vector.size()) {
            size = vector.size();
            n6 = 0;
            n8 = 0;
        }
        for (short n9 = 0; n9 < size; ++n9) {
            if (n8 == 0) {
                n8 = Macro.FONTHEIGHT;
            }
            switch (b) {
                case -53: {
                    this.drawActivityList(graphics, (ChatContent) vector.elementAt(n9 + n6), n7, (short) (n2 + n9 * n8), n9, n3);
                    break;
                }
            }
        }
    }

    public void drawActivityList(Graphics graphics, ChatContent chatContent, short n, short n2, short n3, short n4) {
        this.scroll_OFF = (DCanvas.getInstance().blnSpoolr ? ScrollText.arrowhead_width : 0);
        StringManager.drawWordRightToLeft(chatContent.strsContent, n, n2, this.BackGroundW - 14 - this.scroll_OFF, chatContent.fontColor, 1, n4 == n3);
    }

    public void setUpDown(byte b) {
        short shtMenuMoveLength = this.shtMenuMoveLength;
        if (shtMenuMoveLength == 0) {
            return;
        }
        if (b == 2) {
            if (this.shtOneMenuMove < ((this.bytWordMaxH < shtMenuMoveLength) ? (this.bytWordMaxH - 1) : (shtMenuMoveLength - 1))) {
                ++this.shtOneMenuMove;
            } else {
                this.shtOneMenuMove = (short) (this.bytWordMaxH - 1);
                ++this.shtOneMenuRoll;
                if (this.shtOneMenuRoll >= shtMenuMoveLength - this.bytWordMaxH + 1) {
                    this.shtOneMenuRoll = 0;
                    this.shtOneMenuMove = 0;
                }
            }
        } else if (this.shtOneMenuMove <= 0) {
            this.shtOneMenuMove = 0;
            --this.shtOneMenuRoll;
            if (this.shtOneMenuRoll < 0) {
                this.shtOneMenuRoll = (short) ((this.bytWordMaxH < shtMenuMoveLength) ? (shtMenuMoveLength - this.bytWordMaxH) : 0);
                this.shtOneMenuMove = (short) ((this.bytMenuState == 42) ? -1 : ((short) ((this.bytWordMaxH < shtMenuMoveLength) ? (this.bytWordMaxH - 1) : (shtMenuMoveLength - 1))));
            }
        } else {
            --this.shtOneMenuMove;
        }
    }

    public void pointerOption() {
        switch (this.bytMenuState) {
            case -53: {
                int n = this.BackGroundY + 12;
                if (!DCanvas.getInstance().IsPointerDown(this.BackGroundX + 8, n, this.BackGroundW - 14, (short) (((this.shtMenuMoveLength > this.bytWordMaxH) ? this.bytWordMaxH : this.shtMenuMoveLength) * Macro.FONTHEIGHT) - 2)) {
                    break;
                }
                short shtOneMenuMove = (short) ((DCanvas.getInstance().CurPressedY - n) / Macro.FONTHEIGHT);
                if (this.shtOneMenuMove == shtOneMenuMove) {
                    if (Param.getInstance().Notice != null) {
                        int n2 = this.shtOneMenuMove + this.shtOneMenuRoll;
                        Param.getInstance().shtOneMenuMove = this.shtOneMenuMove;
                        Param.getInstance().shtOneMenuRoll = this.shtOneMenuRoll;
                        int imageID = ((ChatContent) Param.getInstance().Notice.elementAt(n2)).imageID;
                        GameControl.getInstance().CreateState((byte) 3);
                        new FullInfo("Mô tả hoạt động", 0, this.bytMenuState);
                        DCanvas.getInstance().setNetLoad(true);
                        NetSend.getInstance().sendNoticeContent(imageID);
                    }
                    GameControl.getInstance().delUIbase(7);
                    break;
                }
                this.shtOneMenuMove = shtOneMenuMove;
                break;
            }
        }
        if (DCanvas.getInstance().blnSpoolr) {
            byte arrowhead_width = ScrollText.arrowhead_width;
            byte arrowhead_height = ScrollText.arrowhead_height;
            short shtSpoolrX = DCanvas.getInstance().shtSpoolrX;
            short shtSpoolrSY = DCanvas.getInstance().shtSpoolrSY;
            int n3 = DCanvas.getInstance().shtSpoolrEY - DCanvas.getInstance().shtSpoolrSY;
            int n4 = arrowhead_width * 3;
            if (DCanvas.getInstance().IsPointerDragged(shtSpoolrX - arrowhead_width, shtSpoolrSY - arrowhead_height, n4, (n3 >> 1) + arrowhead_height)) {
                this.setUpDown((byte) 1);
            } else if (DCanvas.getInstance().IsPointerDragged(shtSpoolrX - arrowhead_width, shtSpoolrSY + (n3 >> 1) - arrowhead_height, n4, (n3 >> 1) + arrowhead_height)) {
                this.setUpDown((byte) 2);
            } else if (DCanvas.getInstance().IsPointerDown(shtSpoolrX - arrowhead_width, shtSpoolrSY - arrowhead_height, n4, (n3 >> 1) + arrowhead_height)) {
                this.setUpDown((byte) 1);
            } else if (DCanvas.getInstance().IsPointerDown(shtSpoolrX - arrowhead_width, shtSpoolrSY + (n3 >> 1) - arrowhead_height, n4, (n3 >> 1) + arrowhead_height)) {
                this.setUpDown((byte) 2);
            }
        }
    }
}
