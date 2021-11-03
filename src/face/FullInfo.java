// Decompiled with: CFR 0.151
// Class Version: 1
package face;

import base.DCanvas;
import base.GameControl;
import base.Macro;
import base.Param;
import common.PackageBox;
import common.ScrollText;
import face.DialogUI;
import face.GameUI;
import face.MenuUI;
import javax.microedition.lcdui.Graphics;
import means.AnalysisWordContent;
import means.ChatContent;
import means.StringManager;
import model.MailObject;
import model.MenuObject;
import model.PackageObject;
import network.NetSend;

public class FullInfo {

    private String[] strsContent;
    private int intTitleColor;
    private String strTitleName;
    private byte bytContentLenght;
    private byte bytContentMove;
    public byte bytState;
    private short shtIcon;
    private short shtOnum;
    private String strGoodName;
    private String strTitle;
    private String[] strContent;
    private String strInfo;
    private String strDate;
    private AnalysisWordContent WordContent;
    private byte[] bytButtonType;
    private static FullInfo full = null;

    public static FullInfo getInstance() {
        return full;
    }

    public FullInfo(String string, int n, byte by) {
        DCanvas.getInstance().blnSpoolr = false;
        full = this;
        this.bytState = by;
        this.bytContentMove = 0;
        this.strTitleName = string;
        this.intTitleColor = n;
        this.bytButtonType = new byte[3];
        if (this.bytState == -36) {
            this.setButton(1, 100, 2);
        } else {
            this.setButton(1, 0, 2);
        }
    }

    public void clear() {
        full = null;
        this.bytButtonType = null;
        PackageBox.upDownScroll = 0;
        Param.getInstance().DifferencePro = false;
        Param.getInstance().RunOnce = false;
        this.WordContent = null;
    }

    public void setFullRectMenu(String string) {
        Param.getInstance().blnColorInfo = false;
        this.strsContent = StringManager.wenZi(StringManager.displaceNpcTalk(string), StringManager.getNewLineW() - ScrollText.arrowhead_width);
        this.bytContentLenght = (byte) this.strsContent.length;
    }

    public void setColourContent(String string) {
        Param.getInstance().blnColorInfo = true;
        if (this.WordContent == null) {
            this.WordContent = new AnalysisWordContent();
        }
        String string2 = StringManager.displaceNpcTalk(string);
        this.WordContent.analysisChatContent(string2, 0, StringManager.getNewLineW() - ScrollText.arrowhead_width);
        this.strContent = this.WordContent.wenZi();
        this.bytContentLenght = (byte) this.strContent.length;
    }

    public void setMailFullRectMenu(String string, String string2, String string3, String string4, short s, short s2, String string5) {
        Param.getInstance().blnColorInfo = false;
        this.strsContent = StringManager.wenZi(string2, StringManager.getNewLineW() - ScrollText.arrowhead_width);
        this.strInfo = string2;
        this.strDate = string5;
        this.bytContentLenght = (byte) this.strsContent.length;
        this.strGoodName = string;
        this.shtIcon = s;
        this.shtOnum = s2;
        this.strTitle = string3;
        this.strContent = StringManager.wenZi(string4, StringManager.getNewLineW() - ScrollText.arrowhead_width);
    }

    public void setFullRectMenu_Vector() {
        Param.getInstance().blnColorInfo = true;
        int n = 0;
        while (n < GameUI.getInstance().vsColorInfo.length) {
            int n2 = 0;
            while (n2 < GameUI.getInstance().vsColorInfo[n].size()) {
                byte by = ((ChatContent) GameUI.getInstance().vsColorInfo[n].elementAt((int) n2)).type;
                this.bytContentLenght = (byte) (this.bytContentLenght + 1);
                n2 = (byte) (n2 + 1);
            }
            n = (byte) (n + 1);
        }
    }

    public void setButton(int n, int n2, int n3) {
        this.bytButtonType[0] = (byte) n;
        this.bytButtonType[1] = (byte) n2;
        this.bytButtonType[2] = (byte) n3;
    }

    public void paint(Graphics graphics) {
        if (!Param.getInstance().blnColorInfo) {
            this.drawFullRectMenu(graphics);
        } else if (this.bytState == -53) {
            this.drawColourFullRect(graphics);
        } else {
            this.drawFullRectMenu_Color(graphics);
        }
        DCanvas.getInstance().drawSoftkey(graphics, this.bytButtonType[0], this.bytButtonType[1], this.bytButtonType[2], true);
    }

    public void drawColourFullRect(Graphics graphics) {
        DCanvas.getInstance().drawTitleFullsee(graphics, this.strTitleName, this.intTitleColor, this.bytContentLenght, this.bytContentMove, Macro.bytMaxFullRow);
        int n = 38 + Macro.FONTHEIGHT / 2;
        if (this.WordContent != null) {
            this.WordContent.drawContent(graphics, 12, n, this.bytContentMove, Macro.bytMaxFullRow);
        }
    }

    public void drawFullRectMenu(Graphics graphics) {
        DCanvas.getInstance().drawTitleFullsee(graphics, this.strTitleName, this.intTitleColor, this.bytContentLenght, this.bytContentMove, Macro.bytMaxFullRow);
        int n = 38 + Macro.FONTHEIGHT / 2;
        DCanvas.getInstance().drawContent(graphics, this.strsContent, 12, n, this.bytContentMove, Macro.bytMaxFullRow, (byte) 20);
    }

    public void drawFullRectMenu_Color(Graphics graphics) {
        DCanvas.getInstance().drawTitleFullsee(graphics, this.strTitleName, this.intTitleColor, this.bytContentLenght, this.bytContentMove, Macro.bytMaxFullRow);
        graphics.setColor(0);
        graphics.setClip(0, Macro.FONTHEIGHT + 25, Macro.SCREEN_WIDTH - 12, Macro.bytMaxFullRow * Macro.FONTHEIGHT + 12);
        if (this.bytContentLenght > Macro.bytMaxFullRow) {
            byte by = 0;
            while (by < Macro.bytMaxFullRow) {
                if (by + this.bytContentMove < GameUI.getInstance().vsColorInfo.length) {
                    int n = 14;
                    int n2 = Macro.FONTHEIGHT + 25 + by * Macro.FONTHEIGHT;
                    int n3 = 0;
                    while (n3 < GameUI.getInstance().vsColorInfo[by + this.bytContentMove].size()) {
                        int n4 = ((ChatContent) GameUI.getInstance().vsColorInfo[by + this.bytContentMove].elementAt((int) n3)).fontColor;
                        String string = ((ChatContent) GameUI.getInstance().vsColorInfo[by + this.bytContentMove].elementAt((int) n3)).character;
                        byte by2 = ((ChatContent) GameUI.getInstance().vsColorInfo[by + this.bytContentMove].elementAt((int) n3)).type;
                        graphics.setColor(n4);
                        graphics.drawString(string, n, n2, 20);
                        if (by2 == 2) {
                            if (Param.getInstance().RunOnce) {
                                MenuUI.getInstance().setInlayPro(n2, false);
                            }
                            if (!Param.getInstance().RunOnce) {
                                Param.getInstance().DifferencePro = true;
                                MenuUI.getInstance().drawInlayPrp(graphics);
                            }
                        }
                        if (by2 == 1) {
                            n2 += Macro.FONTHEIGHT;
                            n = 12;
                        } else {
                            n += Macro.font.stringWidth(string);
                        }
                        n3 = (byte) (n3 + 1);
                    }
                }
                by = (byte) (by + 1);
            }
        } else {
            byte by = 0;
            while (by < this.bytContentLenght) {
                if (by < GameUI.getInstance().vsColorInfo.length) {
                    int n = 24;
                    int n5 = Macro.FONTHEIGHT + 27 + by * Macro.FONTHEIGHT;
                    int n6 = 0;
                    while (n6 < GameUI.getInstance().vsColorInfo[by].size()) {
                        int n7 = ((ChatContent) GameUI.getInstance().vsColorInfo[by].elementAt((int) n6)).fontColor;
                        byte by3 = ((ChatContent) GameUI.getInstance().vsColorInfo[by + this.bytContentMove].elementAt((int) n6)).type;
                        String string = ((ChatContent) GameUI.getInstance().vsColorInfo[by].elementAt((int) n6)).character;
                        if (by3 == 2 && MenuUI.getInstance().getState() != -127) {
                            if (Param.getInstance().RunOnce) {
                                if (MenuUI.getInstance().bytMoveType == 1) {
                                    MenuUI.getInstance().setEquipPackage(Param.getInstance().hPackage, Param.getInstance().personalPackage.getCellBoxIndex());
                                } else if (MenuUI.getInstance().rView != null) {
                                    MenuUI.getInstance().setEquipPackage(Param.getInstance().hPackageEquip, MenuUI.getInstance().rView.getViewCurrentIndex());
                                }
                                MenuUI.getInstance().setInlayPro(n5, false);
                            }
                            if (!Param.getInstance().RunOnce) {
                                Param.getInstance().DifferencePro = true;
                                MenuUI.getInstance().drawInlayPrp(graphics);
                            }
                        }
                        graphics.setColor(n7);
                        graphics.drawString(string, n, n5, 20);
                        n += Macro.font.stringWidth(string);
                        n6 = (byte) (n6 + 1);
                    }
                }
                by = (byte) (by + 1);
            }
        }
        DCanvas.getInstance().clearScreen();
    }

    public void drawMailContent(Graphics graphics, String[] stringArray, int n, int n2, byte by, byte by2, byte by3) {
        if (stringArray == null) {
            return;
        }
        graphics.setColor(0);
        if (stringArray.length > by2) {
            byte by4 = 0;
            while (by4 < by2) {
                if (by4 + by >= stringArray.length - 3) {
                    graphics.drawString(stringArray[by4 + by], Macro.SCREEN_WIDTH - 12, n2 + by4 * Macro.FONTHEIGHT, 24);
                } else {
                    graphics.drawString(stringArray[by4 + by], n, n2 + by4 * Macro.FONTHEIGHT, by3);
                }
                by4 = (byte) (by4 + 1);
            }
        } else {
            int n3 = 0;
            while (n3 < stringArray.length) {
                if (n3 >= stringArray.length - 3) {
                    graphics.drawString(stringArray[n3], Macro.SCREEN_WIDTH - 12, n2 + n3 * Macro.FONTHEIGHT, 24);
                } else {
                    graphics.drawString(stringArray[n3], n, n2 + n3 * Macro.FONTHEIGHT, by3);
                }
                n3 = (byte) (n3 + 1);
            }
        }
    }

    public void logic(int n) {
        if (DCanvas.getInstance().IsKeyRelease(131072)) {
            this.keyRightSoft();
            DCanvas.getInstance().buttonRightFlash = 0;
        } else if (DCanvas.getInstance().isKeyDown(131072)) {
            DCanvas.getInstance().buttonRightFlash = 1;
        } else if (DCanvas.getInstance().IsKeyRelease(65536) || DCanvas.getInstance().IsKeyRelease(262144)) {
            this.keyLeftSoft();
            DCanvas.getInstance().buttonLeftFlash = 0;
        } else if (DCanvas.getInstance().isKeyDown(65536) || DCanvas.getInstance().IsKeyRelease(262144)) {
            DCanvas.getInstance().buttonLeftFlash = 1;
        } else if (DCanvas.getInstance().isKeyDown(4096)) {
            this.keyFullRectUp();
        } else if (DCanvas.getInstance().isKeyDown(8192)) {
            this.keyFullRectDown();
        }
        if (DCanvas.getInstance().blnIsTouch && DCanvas.getInstance().blnPointerPressed) {
            if (DCanvas.getInstance().PointerDwonSoftKey_Left()) {
                this.keyLeftSoft();
            } else if (DCanvas.getInstance().PointerDwonSoftKey_Right()) {
                this.keyRightSoft();
            } else if (DCanvas.getInstance().blnSpoolr) {
                byte by = ScrollText.arrowhead_width;
                byte by2 = ScrollText.arrowhead_height;
                short s = DCanvas.getInstance().shtSpoolrX;
                short s2 = DCanvas.getInstance().shtSpoolrSY;
                int n2 = DCanvas.getInstance().shtSpoolrEY - DCanvas.getInstance().shtSpoolrSY;
                int n3 = by * 3;
                if (DCanvas.getInstance().IsPointerDragged(s - by, s2 - by2, n3, (n2 >> 1) + by2)) {
                    this.keyFullRectUp();
                } else if (DCanvas.getInstance().IsPointerDragged(s - by, s2 + (n2 >> 1) - by2, n3, (n2 >> 1) + by2)) {
                    this.keyFullRectDown();
                } else if (DCanvas.getInstance().IsPointerDown(s - by, s2 - by2, n3, (n2 >> 1) + by2)) {
                    this.keyFullRectUp();
                } else if (DCanvas.getInstance().IsPointerDown(s - by, s2 + (n2 >> 1) - by2, n3, (n2 >> 1) + by2)) {
                    this.keyFullRectDown();
                }
            }
        }
    }

    public void keyLeftSoft() {
        switch (this.bytState) {
            case -47: {
                if (this.shtIcon != 0) {
                    GameUI.getInstance().setTwoMenu(this.bytState, MenuUI.getInstance().getNextMenuStr(((MenuObject) Param.getInstance().vMenuMemory.elementAt((int) (Param.getInstance().vMenuMemory.size() - 1))).strOption, "345"), (byte) 5);
                    break;
                }
                GameUI.getInstance().setTwoMenu(this.bytState, MenuUI.getInstance().getNextMenuStr(((MenuObject) Param.getInstance().vMenuMemory.elementAt((int) (Param.getInstance().vMenuMemory.size() - 1))).strOption, "45"), (byte) 5);
                break;
            }
            case 61: {
                MenuUI.getInstance().keyLeftSoftEvent();
                break;
            }
            case -90:
            case -53: {
                break;
            }
            default: {
                this.clear();
            }
        }
    }

    public void keyRightSoft() {
        if (Macro.bytGameType == 1) {
            if (GameUI.getInstance().vsColorInfo != null) {
                GameUI.getInstance().vsColorInfo = null;
            }
            Param.getInstance().blnColorInfo = false;
            switch (this.bytState) {
                case -36: {
                    boolean bl = ((MailObject) Param.getInstance().vCommonList.elementAt((int) MenuUI.getInstance().getOneMove())).blnIsRead;
                    if (bl) {
                        break;
                    }
                    int n = ((MailObject) Param.getInstance().vCommonList.elementAt((int) MenuUI.getInstance().getOneMove())).intId;
                    NetSend.getInstance().sendMailRenovate((byte) 0, n);
                    ((MailObject) Param.getInstance().vCommonList.elementAt((int) MenuUI.getInstance().getOneMove())).blnIsRead = true;
                    MenuUI.getInstance().setMailList(MenuUI.getInstance().getOneMove());
                    break;
                }
                case -47: {
                    NetSend.getInstance().sendNpcOneOption(Param.getInstance().oSelectNpc.intUserId, (byte) 1, 800000);
                    break;
                }
                case -91:
                case -90:
                case -8: {
                    MenuUI.getInstance().setBackSystem();
                    break;
                }
                case -53: {
                    MenuUI.getInstance().setBackSystem();
                    GameControl.getInstance().delUIbase(4);
                    GameControl.getInstance().CreateState((byte) 7);
                    DialogUI.getInstance().setDialog((byte) -53, "活动", Param.getInstance().Notice, (byte) 3);
                    break;
                }
                case 8: {
                    this.clear();
                    MenuUI.getInstance().setBackSystem();
                    GameControl.getInstance().delUIbase(3);
                    PackageObject packageObject = null;
                    int n = 0;
                    while (n < Param.getInstance().vEquipDialog.size()) {
                        packageObject = new PackageObject();
                        packageObject = (PackageObject) Param.getInstance().vEquipDialog.elementAt(n);
                        GameUI.getInstance().addObjectImg(packageObject.shtIcon, "prop");
                        ++n;
                    }
                    GameControl.getInstance().CreateState((byte) 7);
                    GameUI.getInstance().setDialog((byte) 8);
                    break;
                }
                case 61: {
                    if (!MenuUI.getInstance().isCheckSkillinfo) {
                        break;
                    }
                    MenuUI.getInstance().isCheckSkillinfo = false;
                    break;
                }
            }
        }
        this.clear();
    }

    public void keyFullRectUp() {
        DCanvas.getInstance().bytSpoolrFlash = 1;
        if (Macro.bytMaxFullRow > this.bytContentLenght) {
            return;
        }
        PackageBox.upDownScroll = (byte) (PackageBox.upDownScroll - 1);
        this.bytContentMove = (byte) (this.bytContentMove - 1);
        if (this.bytContentMove <= 0) {
            this.bytContentMove = 0;
            PackageBox.upDownScroll = 0;
        }
    }

    public void keyFullRectDown() {
        DCanvas.getInstance().bytSpoolrFlash = (byte) 2;
        if (Macro.bytMaxFullRow > this.bytContentLenght) {
            return;
        }
        if (this.bytContentMove < this.bytContentLenght - Macro.bytMaxFullRow) {
            this.bytContentMove = (byte) (this.bytContentMove + 1);
            PackageBox.upDownScroll = (byte) (PackageBox.upDownScroll + 1);
        }
    }
}
