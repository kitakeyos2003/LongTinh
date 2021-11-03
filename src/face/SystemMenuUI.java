// Decompiled with: CFR 0.151
// Class Version: 1
package face;

import base.DCanvas;
import base.GameControl;
import base.Macro;
import base.Param;
import common.DrawBase;
import common.LayoutStyle;
import common.Pram;
import java.util.Hashtable;
import javax.microedition.lcdui.Graphics;
import means.DebugFrame;
import model.ORPMe;
import network.NetParse;
import network.NetSend;

public class SystemMenuUI
        implements UIbase {

    private short shtMainMenu_x;
    private short shtMainMenu_y;
    private short shtMainMenu_w;
    private short shtMainMenu_h;
    private short shtMainMenu_offx;
    private short shtMainMenu_offy;
    private short shtMainMenuItem_x;
    private short shtMainMenuItem_y;
    private short shtMainMenuItem_w;
    private short shtMainMenuItem_h;
    private short shtMainMenuitemHspace;
    private short shtMainMenuitemVspace;
    private byte bytHItemCol = (byte) 3;
    private byte bytVItemRow;
    private short shtMainTitle_x;
    private short shtMainTitle_y;
    private short shtMainTitle_w;
    private short shtMainTitle_h;
    private String strTitle;
    public boolean blnIsThis = false;
    public byte bytState;
    public String[] strOption;
    public byte bytOptionMove;
    private byte bytOptionRoll;
    private byte bytOldMove;
    public byte bytWordMaxH;
    public short[] shtOptionPlace;
    private short shtSystemWidth;
    private short shtSystemHeight;
    private byte[] bytButtonType;
    public static SystemMenuUI systemmenu = null;

    public static SystemMenuUI getInstance() {
        return systemmenu;
    }

    public SystemMenuUI() {
        systemmenu = this;
    }

    public void init() {
        this.bytButtonType = new byte[3];
        this.shtOptionPlace = new short[4];
        this.shtSystemWidth = Macro.SCREEN_WIDTH;
        this.shtSystemHeight = Macro.SCREEN_HEIGHT;
        this.bytWordMaxH = (byte) ((this.shtSystemHeight - 55) / (Macro.FONTHEIGHT + 2) - 2);
    }

    public void isThis(boolean bl) {
        this.blnIsThis = bl;
    }

    public void clean() {
        systemmenu = null;
        GameUI.getInstance().setButton();
    }

    public void setState(byte by) {
        DCanvas.getInstance().blnSpoolr = false;
        GameUI.getInstance().blnChatFace = false;
        this.strOption = null;
        DCanvas.getInstance().buttonLeftFlash = 0;
        DCanvas.getInstance().buttonRightFlash = 0;
        switch (by) {
            case -25: {
                GameUI.getInstance().setNextMenu((byte) -25, "Kiểm tra mạng");
                break;
            }
            case -9: {
                this.setOption(Pram.STRING_MENU_EXIT);
                this.setTitle("Rời khỏi");
                break;
            }
            case 0: {
                this.setTitle("Menu chính");
                this.setOption(Pram.STRING_MENU_LIST);
                break;
            }
            case -30: {
                this.setTitle("Nhanh");
                this.setOption(Pram.STRING_MENU_LEFT_LIST);
                break;
            }
            case -1: {
                this.setOption(Pram.STRING_MENU_GAM);
                this.setTitle("Xã hội");
                break;
            }
            case -16: {
                this.setTitle("Mall");
                this.bytState = by;
                this.setOption(Pram.STRING_SHOP_OPTION);
                break;
            }
            case -17: {
                this.setTitle("Các dịch vụ khác");
                this.bytState = by;
                this.setOption(Pram.STRING_SHOP_OTHER);
                break;
            }
            case -18: {
                this.setTitle("Điểm nạp tiền");
                this.bytState = by;
                this.setOption(Pram.STRING_RECHARGE_MENU);
                break;
            }
            case -19: {
                this.setTitle("Loại nạp tiền");
                this.bytState = by;
                int n = Param.getInstance().MALL_CHANNEL_VLIST.size();
                Pram.STRING_RECHARGE_TYPE = new String[n];
                int n2 = 0;
                while (n2 < n) {
                    Pram.STRING_RECHARGE_TYPE[n2] = ((String[]) Param.getInstance().MALL_CHANNEL_VLIST.elementAt(n2))[3];
                    ++n2;
                }
                this.setOption(Pram.STRING_RECHARGE_TYPE);
                break;
            }
            case -13: {
                this.setTitle("Kỹ năng");
                this.setOption(Pram.STRING_MENU_SKILL);
                break;
            }
            case 60: {
                this.setOption(Pram.STRING_SETTING_KEY);
                this.setTitle("Cài đặt");
                break;
            }
            case -4: {
                this.setTitle("Trò chuyện");
                this.setOption(Pram.STRING_MENU_TALK);
                break;
            }
            case -14: {
                GameControl.getInstance().delUIbase(4);
                Param.getInstance().vOptionPlace = null;
                GameUI.getInstance().openSmallMap();
                break;
            }
            case -5: {
                MenuUI.getInstance().cleanRankList();
                int n = 0;
                if (GameUI.getInstance().bytMakeSkill != 0) {
                    n = (byte) (n + 1);
                }
                int n3 = Pram.STRING_MENU_ROLE.length;
                String[] stringArray = new String[n3 + n];
                int n4 = 0;
                while (n4 < n3) {
                    stringArray[n4] = Pram.STRING_MENU_ROLE[n4];
                    n4 = (byte) (n4 + 1);
                }
                n4 = 0;
                if (GameUI.getInstance().bytMakeSkill != 0) {
                    stringArray[n4 + n3] = Pram.STRING_MENU_MAKE[GameUI.getInstance().bytMakeSkill - 1];
                }
                this.setOption(stringArray);
                this.setTitle("Nhân vật");
                break;
            }
            case -6: {
                Param.getInstance().packageBoxMaxNum = Param.getInstance().bytAccouterBag;
                GameUI.getInstance().setNextMenu((byte) 10, Pram.STRING_MENU_LIST[this.bytOptionMove]);
                DCanvas.getInstance().setNetLoad(true);
                NetSend.getInstance().sendFrameEquip((byte) 1, (byte) 0, 0);
                break;
            }
            case -8: {
                this.setOption(Pram.STRING_MENU_HELP);
                this.setTitle("Trợ giúp");
                break;
            }
            case -12: {
                this.setOption(Pram.STRING_SERVE_LIST);
                this.setTitle("Dịch vụ");
                break;
            }
            case -15: {
                if (Param.getInstance().Main_List == null) {
                    break;
                }
                Param.getInstance().Main_Name = new String[Param.getInstance().Main_List.size()];
                int n = 0;
                while (n < Param.getInstance().Main_Name.length) {
                    Param.getInstance().Main_Name[n] = ((String[]) Param.getInstance().Main_List.elementAt(n))[1];
                    ++n;
                }
                this.setOption(Param.getInstance().Main_Name);
                this.setTitle("Bảng dẫn đầu");
            }
        }
        this.bytOptionRoll = 0;
        this.bytOptionMove = 0;
        this.bytState = by;
    }

    private void setTitle(String string) {
        this.strTitle = string;
    }

    private void setOption(String[] stringArray) {
        if (stringArray == null) {
            return;
        }
        this.strOption = stringArray;
        this.bytHItemCol = (byte) (-16 == this.bytState || this.bytState == -17 || this.bytState == -18 || this.bytState == -19 ? 1 : 3);
        this.bytVItemRow = (byte) (this.strOption.length / this.bytHItemCol + (this.strOption.length % this.bytHItemCol == 0 ? 0 : 1));
        this.shtMainMenuItem_w = (short) (Macro.font.stringWidth("5 " + stringArray[0]) + 5);
        this.shtMainMenuItem_h = (short) (Macro.FONTHEIGHT + 6);
        this.shtMainMenuitemHspace = (short) 7;
        this.shtMainMenuitemVspace = (short) 4;
        this.shtMainMenuItem_x = (short) ((Macro.SCREEN_WIDTH - (this.shtMainMenuItem_w + this.shtMainMenuitemHspace) * this.bytHItemCol) / 2 + this.shtMainMenuitemHspace / 2);
        this.shtMainMenuItem_y = (short) ((Macro.SCREEN_HEIGHT - (this.shtMainMenuItem_h + this.shtMainMenuitemVspace) * this.bytVItemRow) / 2);
        this.shtMainMenu_offx = (short) 14;
        this.shtMainMenu_offy = (short) 30;
        this.shtMainMenu_x = (short) (this.shtMainMenuItem_x - this.shtMainMenu_offx - (-16 == this.bytState || this.bytState == -17 || this.bytState == -18 || this.bytState == -19 ? (byte) (this.shtMainMenuItem_w >> 1) : (byte) 0));
        if (this.shtMainMenu_x <= 0) {
            this.shtMainMenu_x = 0;
        }
        this.shtMainMenu_y = (short) (this.shtMainMenuItem_y - this.shtMainMenu_offy);
        this.shtMainMenu_w = (short) (this.shtSystemWidth - 2 * this.shtMainMenu_x);
        if (this.shtMainMenu_w > Macro.SCREEN_WIDTH - 10) {
            this.shtMainMenu_w = Macro.SCREEN_WIDTH;
        }
        this.shtMainMenu_h = (short) (this.shtSystemHeight - 2 * this.shtMainMenu_y);
        this.shtMainTitle_x = (short) (this.shtMainMenu_x + 50);
        this.shtMainTitle_y = (short) (this.shtMainMenu_y - 17);
        this.shtMainTitle_w = (short) (Macro.SCREEN_WIDTH - 2 * this.shtMainTitle_x);
        this.shtMainTitle_h = (short) 27;
        this.setButton(0, 0, 2);
    }

    private void setButton(int n, int n2, int n3) {
        this.bytButtonType[0] = (byte) n;
        this.bytButtonType[1] = (byte) n2;
        this.bytButtonType[2] = (byte) n3;
    }

    private void setMenuNotEnter(int n) {
        if (n < this.strOption.length) {
            Param.is_test_color = this.strOption[n].equals("Thú cưng") || this.strOption[n].equals("Thú cưng") || this.strOption[n].equals("Tìm kiếm") || this.strOption[n].equals("Cuộc sống") || this.strOption[n].equals("Dịch vụ hộp thư") || this.strOption[n].equals("Mở rộng chiến binh") ? 0xC0C0C0 : 16762219;
        }
    }

    public void paint(Graphics graphics) {
        if (this.strOption == null) {
            return;
        }
        DrawBase.drawBoxInMenu(this.shtMainMenu_x, this.shtMainMenu_y, this.shtMainMenu_w, this.shtMainMenu_h, new int[]{6179098, 6179098, 14020057, 6179098, 12818766, 12818766, 12818766, 12818766}, true, (byte) 3);
        LayoutStyle.getInstance().drawEarBox(graphics, "", 9263661, this.shtMainTitle_x, this.shtMainTitle_y, this.shtMainTitle_w, this.shtMainTitle_h);
        DrawBase.DrawString(this.strTitle, this.shtMainTitle_x, this.shtMainTitle_y, this.shtMainTitle_x + this.shtMainTitle_w / 2, this.shtMainTitle_y + this.shtMainTitle_h / 2 + 4, this.shtMainTitle_w, this.shtMainTitle_h, (short) 0, 0x622112);
        int n = 0;
        while (n < 2) {
            DrawBase.drawBox(n == 0 ? this.shtMainMenu_x + 4 : this.shtMainMenu_x + this.shtMainMenu_w - this.shtMainMenuItem_w - 4, this.shtMainMenu_y + this.shtMainMenu_h - this.shtMainMenuItem_h - 4, this.shtMainMenuItem_w, this.shtMainMenuItem_h, new int[]{16314576, 16753705, 16753705}, true);
            DrawBase.drawString(n == 0 ? "Chọn" : "Trở về", n == 0 ? this.shtMainMenu_x + 4 + this.shtMainMenuItem_w / 2 : this.shtMainMenu_x + this.shtMainMenu_w - (Macro.font.stringWidth("Trở lại") + (this.shtMainMenuItem_w - Macro.font.stringWidth("Trở về")) / 2 + 3), this.shtMainMenu_y + this.shtMainMenu_h - this.shtMainMenuItem_h - 4 + (this.shtMainMenuItem_h - Macro.FONTHEIGHT) / 2, 8399402, 17);
            ++n;
        }
        n = 0;
        while (n < this.bytVItemRow) {
            int n2 = 0;
            while (n2 < this.bytHItemCol) {
                this.setMenuNotEnter(n * this.bytHItemCol + n2);
                if (n * this.bytHItemCol + n2 <= this.strOption.length - 1) {
                    int[] nArray;
                    if (this.bytOptionMove == n * this.bytHItemCol + n2) {
                        int[] nArray2 = new int[2];
                        nArray2[0] = 10176054;
                        nArray = nArray2;
                        nArray2[1] = 16739958;
                    } else {
                        int[] nArray3 = new int[3];
                        nArray3[0] = 10176054;
                        nArray3[1] = 11370066;
                        nArray = nArray3;
                        nArray3[2] = Param.is_test_color;
                    }
                    DrawBase.drawBox(this.shtMainMenuItem_x + n2 * (this.shtMainMenuItem_w + this.shtMainMenuitemHspace), this.shtMainMenuItem_y + n * (this.shtMainMenuItem_h + this.shtMainMenuitemVspace), this.shtMainMenuItem_w, this.shtMainMenuItem_h, nArray, true);
                    String string = /*String.valueOf(Pram.strMenuNum[n * this.bytHItemCol + n2]) + */ this.strOption[n * this.bytHItemCol + n2];
                    DrawBase.DrawString(string, this.shtMainMenuItem_x + n2 * (this.shtMainMenuItem_w + this.shtMainMenuitemHspace), this.shtMainMenuItem_y + n * (this.shtMainMenuItem_h + this.shtMainMenuitemVspace), this.shtMainMenuItem_x + n2 * (this.shtMainMenuItem_w + this.shtMainMenuitemHspace) + this.shtMainMenuItem_w / 2, this.shtMainMenuItem_y + n * (this.shtMainMenuItem_h + this.shtMainMenuitemVspace) + this.shtMainMenuItem_h / 2, this.shtMainMenuItem_w, this.shtMainMenuItem_h, (short) 0, this.bytOptionMove == n * this.bytHItemCol + n2 ? 0xFFFFFE : 8399402);
                }
                ++n2;
            }
            ++n;
        }
    }

    public void logic(int n) {
        if (!this.blnIsThis) {
            return;
        }
        if (DCanvas.getInstance().IsKeyRelease(65536) || DCanvas.getInstance().IsKeyRelease(262144)) {
            DCanvas.getInstance().buttonLeftFlash = 0;
            this.keyAnswer(this.bytOptionMove, this.bytOptionRoll, (byte) 0);
        } else if (DCanvas.getInstance().isKeyDown(65536) || DCanvas.getInstance().IsKeyRelease(262144)) {
            DCanvas.getInstance().buttonLeftFlash = 1;
        } else if (DCanvas.getInstance().IsKeyRelease(131072)) {
            DCanvas.getInstance().buttonRightFlash = 0;
            if (this.bytState == 0) {
                GameControl.getInstance().delUIbase(4);
                Param.getInstance().vOptionPlace = null;
            } else {
                this.keyMenuFee();
            }
        } else if (DCanvas.getInstance().isKeyDown(131072)) {
            DCanvas.getInstance().buttonRightFlash = 1;
        } else if (DCanvas.getInstance().isKeyDown(8192)) {
            this.keyDown();
        } else if (DCanvas.getInstance().isKeyDown(4096)) {
            this.keyUp();
        } else if (DCanvas.getInstance().isKeyDown(16384)) {
            this.keyLeft();
        } else if (DCanvas.getInstance().isKeyDown(32768)) {
            this.keyRight();
        } else {
            this.setCutKeys();
        }
        if (DCanvas.getInstance().blnIsTouch && DCanvas.getInstance().blnPointerPressed) {
            if (DCanvas.getInstance().PointerDwonSoftKey_Left()) {
                DCanvas.getInstance().buttonLeftFlash = 0;
                this.keyAnswer(this.bytOptionMove, this.bytOptionRoll, (byte) 0);
            } else if (DCanvas.getInstance().PointerDwonSoftKey_Right() || DCanvas.getInstance().IsPointerDownOutSideFrame(this.shtMainMenu_x, this.shtMainMenu_y, this.shtMainMenu_w, this.shtMainMenu_h)) {
                DCanvas.getInstance().buttonRightFlash = 0;
                if (this.bytState == 0) {
                    GameControl.getInstance().delUIbase(4);
                    Param.getInstance().vOptionPlace = null;
                } else {
                    this.keyMenuFee();
                }
            } else {
                this.setPointer();
            }
        }
    }

    private void setCutKeys() {
        byte by = DCanvas.getInstance().getFullKey();
        switch (this.bytState) {
            case 0: {
                by = this.setCutkey_menuList(by);
                break;
            }
            case -1: {
                by = this.setCutkey_menuList(by);
                break;
            }
            case -3: {
                break;
            }
            case -4: {
                break;
            }
            case -5: {
                by = this.setCutkey_menuList(by);
                break;
            }
            case -6: {
                break;
            }
            case -8: {
                by = this.setCutkey_menuList(by);
                break;
            }
            case 60: {
                break;
            }
            case -9: {
                break;
            }
            case -12: {
                by = this.setCutkey_menuList(by);
            }
        }
        if (by >= 0 && by < this.strOption.length) {
            this.bytOptionMove = by;
            this.bytOptionRoll = 0;
            this.keyAnswer(by, (byte) 0, (byte) 1);
        }
    }

    private byte setCutkey_menuList(byte by) {
        switch (by) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8: {
                break;
            }
            case 9: {
                by = (byte) 10;
                break;
            }
            case 10: {
                by = (byte) 9;
                break;
            }
            case 11: {
                by = (byte) 11;
            }
        }
        return by;
    }

    private void keyRight() {
        if (this.bytOptionMove < this.strOption.length - 1) {
            this.bytOptionMove = (byte) (this.bytOptionMove + 1);
        }
    }

    private void keyLeft() {
        if (this.bytOptionMove > 0) {
            this.bytOptionMove = (byte) (this.bytOptionMove - 1);
        }
    }

    private void keyUp() {
        if (this.bytOptionMove / this.bytHItemCol > 0 && this.bytOptionMove > 0) {
            this.bytOptionMove = (byte) (this.bytOptionMove - this.bytHItemCol);
        }
    }

    private void keyDown() {
        if (this.bytOptionMove / this.bytHItemCol < this.bytVItemRow - 1 && this.bytOptionMove < this.strOption.length - 1) {
            this.bytOptionMove = (byte) (this.bytOptionMove + this.bytHItemCol);
        }
    }

    public void setBackOldState() {
        if (Param.getInstance().vOptionPlace == null || Param.getInstance().vOptionPlace.isEmpty()) {
            GameControl.getInstance().delUIbase(4);
            Param.getInstance().vOptionPlace = null;
        } else {
            byte by = (byte) (Param.getInstance().vOptionPlace.size() - 1);
            byte[] byArray = (byte[]) Param.getInstance().vOptionPlace.elementAt(by);
            this.bytState = 0;
            this.setState(byArray[0]);
            if (byArray[3] == 1) {
                if (byArray[1] > this.bytWordMaxH - 1) {
                    this.bytOptionMove = (byte) (this.bytWordMaxH - 1);
                    this.bytOptionRoll = (byte) (byArray[1] - this.bytWordMaxH + 1);
                } else {
                    this.bytOptionMove = byArray[1];
                    this.bytOptionRoll = 0;
                }
            } else {
                this.bytOptionMove = byArray[1];
                this.bytOptionRoll = byArray[2];
            }
            Param.getInstance().vOptionPlace.removeElementAt(by);
            if (Param.getInstance().vOptionPlace.isEmpty()) {
                Param.getInstance().vOptionPlace = null;
            }
        }
    }

    private void keyAnswer(byte by, byte by2, byte by3) {
        GameUI.getInstance().setOldState(this.bytState, by, by2, by3);
        by = (byte) (by + by2);
        switch (this.bytState) {
            case 0: {
                this.keyMenuList(by);
                break;
            }
            case -30: {
                this.keyMenuLeftList(by);
                break;
            }
            case -16: {
                this.keyMenuShopOption(by);
                break;
            }
            case -17: {
                this.keyMenuShopOtherOption(by);
                break;
            }
            case -18: {
                this.keyMenuShopRechargeOption(by);
                break;
            }
            case -19: {
                this.keyMenuShopChannelOption(by);
                break;
            }
            case -13: {
                this.KeyMenuKill(by);
                break;
            }
            case -1: {
                this.keyMeunGam(by);
                break;
            }
            case -3: {
                this.keyMenuFee();
                break;
            }
            case -4: {
                this.keyMenuTalk(by);
                break;
            }
            case -12: {
                this.keyMenuServe(by);
                break;
            }
            case -5: {
                this.keyMenuRole(by);
                break;
            }
            case -6: {
                this.keyMenuProp(by);
                break;
            }
            case -8: {
                break;
            }
            case 60: {
                this.keyMenuSetting(by);
                break;
            }
            case -9: {
                this.keyMenuBeoff(by);
                break;
            }
            case -15: {
                this.keyRankLise(by);
            }
        }
    }

    private void keyMenuBeoff(byte by) {
        if (this.strOption[by].indexOf(Pram.STRING_MENU_EXIT[1]) != -1) {
            if (GameUI.getInstance().blnFight) {
                DCanvas.getInstance().addInformation("Không thể thoát khỏi trò chơi trong trạng thái chiến đấu");
            } else {
                GameUI.getInstance().setDialog((byte) 14);
            }
        } else if (this.strOption[by].indexOf(Pram.STRING_MENU_EXIT[0]) != -1) {
            if (GameUI.getInstance().blnFight) {
                DCanvas.getInstance().addInformation("Không thể thoát khỏi trò chơi trong trạng thái chiến đấu");
            } else {
                Param.getInstance().intGMQuizTimer = 0;
                GameControl.getInstance().CreateState((byte) 7);
                DialogUI.getInstance().setDialog((byte) 10, "Vui lòng chờ trong khi tải tài nguyên ...", (byte) 2);
                DCanvas.getInstance().setNetLoad(true);
                NetParse.getInstance().setInitLoadingPre();
                NetParse.getInstance().setInitLoading();
                NetSend.getInstance().sendReload();
            }
        }
        GameControl.getInstance().delUIbase(4);
    }

    private void keyMenuSetting(byte by) {
        if (this.strOption[by].indexOf(Pram.STRING_SETTING_KEY[0]) != -1) {
            GameUI.getInstance().setNextMenu((byte) -81, this.strOption[by]);
        } else if (this.strOption[by].indexOf(Pram.STRING_SETTING_KEY[1]) != -1) {
            GameUI.getInstance().setNextMenu((byte) 60, this.strOption[by]);
        }
    }

    private void keyMenuProp(byte by) {
        Param.getInstance().bytPropBagType = 0;
        switch (by) {
            case 0: {
                Param.getInstance().packageBoxMaxNum = Param.getInstance().bytAccouterBag;
                GameUI.getInstance().setNextMenu((byte) 10, this.strOption[by]);
                DCanvas.getInstance().setNetLoad(true);
                NetSend.getInstance().sendFrameEquip((byte) 1, (byte) 0, 0);
                break;
            }
            case 1: {
                Param.getInstance().packageBoxMaxNum = Param.getInstance().bytStuffBag;
                GameUI.getInstance().setNextMenu((byte) 37, this.strOption[by]);
                DCanvas.getInstance().setNetLoad(true);
                NetSend.getInstance().sendFrameStuff((byte) 1, (byte) 0, 0);
                break;
            }
            case 2: {
                Param.getInstance().packageBoxMaxNum = Param.getInstance().bytLeechdomBag;
                GameUI.getInstance().setNextMenu((byte) 36, this.strOption[by]);
                DCanvas.getInstance().setNetLoad(true);
                NetSend.getInstance().sendFrameLeechdom((byte) 1, (byte) 0, 0);
                break;
            }
            case 3: {
                Param.getInstance().packageBoxMaxNum = Param.getInstance().bytTaskBag;
                GameUI.getInstance().setNextMenu((byte) 38, this.strOption[by]);
                DCanvas.getInstance().setNetLoad(true);
                NetSend.getInstance().sendFrameTaskprop((byte) 1, (byte) 0, 0);
                break;
            }
            case 4: {
                Param.getInstance().packageBoxMaxNum = Param.getInstance().bytEspecialBag;
                GameUI.getInstance().setNextMenu((byte) 39, this.strOption[by]);
                DCanvas.getInstance().setNetLoad(true);
                NetSend.getInstance().sendFramePeculiar((byte) 1, (byte) 0, 0, -1, (byte) 0);
                break;
            }
            case 5: {
                Param.getInstance().packageBoxMaxNum = Param.getInstance().bytPetBag;
                GameUI.getInstance().setNextMenu((byte) 33, this.strOption[by]);
                DCanvas.getInstance().setNetLoad(true);
                NetSend.getInstance().sendPetHandle((byte) 1, 0);
            }
        }
    }

    private void keyMenuRole(byte by) {
        if (this.strOption[by].indexOf(Pram.STRING_MENU_ROLE[by]) != -1) {
            switch (by) {
                case 0: {
                    GameUI.getInstance().setNextMenu((byte) -7, this.strOption[by]);
                    break;
                }
                case 1: {
                    Param.getInstance().packageBoxMaxNum = Param.getInstance().bytAccouterBag;
                    GameUI.getInstance().setNextMenu((byte) 10, Pram.STRING_MENU_ROLE[this.bytOptionMove]);
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendFrameEquip((byte) 1, (byte) 0, 0);
                    break;
                }
                case 2: {
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendShopHandle((byte) 1);
                    break;
                }
                case 3: {
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendRankList((byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0);
                }
            }
        }
    }

    private void keyMenuFee() {
        this.setBackOldState();
    }

    private void keyMenuServe(byte by) {
        switch (by) {
            case 0: {
                GameControl.getInstance().delUIbase(4);
                GameControl.getInstance().CreateState((byte) 7);
                DialogUI.getInstance().setDialog((byte) 10, "Vui lòng chờ trong khi tải tài nguyên ...", (byte) 2);
                DCanvas.getInstance().setNetLoad(true);
                NetParse.getInstance().setInitLoadingPre();
                NetParse.getInstance().setInitLoading();
                NetSend.getInstance().sendBreakAwayMap();
                break;
            }
            case 1: {
                this.keyMenuFee();
                int n = (int) (System.currentTimeMillis() - (long) Param.getInstance().intGMQuizTimer);
                if (Param.getInstance().intGMQuizTimer == 0 || n >= 1800000) {
                    FormDes.getInstance().showForm((byte) -70);
                    break;
                }
                int n2 = (1800000 - n) / 60000;
                DCanvas.getInstance().addInformation(String.valueOf(n2) + "Câu hỏi một lần nữa sau một phút");
                break;
            }
            case 2: {
                GameUI.getInstance().setNextMenu((byte) -84, this.strOption[by]);
            }
        }
    }

    private void keyMenuTalk(byte by) {
        switch (by) {
            case 0: {
                GameControl.getInstance().delUIbase(4);
                FormDes.getInstance().showForm((byte) 40);
                break;
            }
            case 1: {
                GameUI.getInstance().setNextMenu((byte) 42, this.strOption[by]);
                MenuUI.getInstance().blnSetChatNote = true;
                MenuUI.getInstance().setChatNote();
                GameUI.getInstance().blnChatFace = true;
                break;
            }
            case 2: {
                GameUI.getInstance().setNextMenu((byte) -49, this.strOption[by]);
            }
        }
    }

    private void keyMeunGam(byte by) {
        if (this.strOption[by].indexOf(Pram.STRING_MENU_GAM[by]) != -1) {
            switch (by) {
                case 0: {
                    this.setState((byte) -4);
                    break;
                }
                case 1: {
                    Param.getInstance().shtNoncePage = 1;
                    GameUI.getInstance().setNextMenu((byte) -31, this.strOption[by]);
                    break;
                }
                case 2: {
                    GameUI.getInstance().setNextMenu((byte) 35, this.strOption[by]);
                    break;
                }
                case 3: {
                    Param.getInstance().shtNoncePage = 1;
                    GameUI.getInstance().setNextMenu((byte) -48, this.strOption[by]);
                    break;
                }
                case 4: {
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendMailList();
                    GameUI.getInstance().setNextMenu((byte) -36, this.strOption[by]);
                    break;
                }
                case 5: {
                    if (ORPMe.ME.strConsortia == null || ORPMe.ME.strConsortia.equals("")) {
                        this.keyMenuFee();
                        DCanvas.getInstance().addInformation("Bạn không tham gia bất kỳ nhóm nào");
                        break;
                    }
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendConsortia((byte) 1, (byte) 1, 1);
                    break;
                }
                case 6: {
                    Param.getInstance().shtNoncePage = 1;
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendAskPartner((byte) 1);
                    break;
                }
                case 7: {
                    DCanvas.getInstance().addInformation("Chức năng chưa mở");
                }
            }
        }
    }

    private void keyMenuShopOption(byte by) {
        if (this.strOption[by].indexOf(Pram.STRING_SHOP_OPTION[by]) != -1) {
            switch (by) {
                case 0: {
                    DebugFrame.getInstance().logIn("Mở trung tâm mua sắm tại 8026:" + System.currentTimeMillis());
                    if (Param.getInstance().hMallPackage != null) {
                        GameUI.getInstance().setNextMenu((byte) -11, Pram.STRING_SHOP_OPTION[0]);
                        MenuUI.getInstance().setInfoContent((Hashtable) Param.getInstance().hMallPackage.get(new Integer(0)), Param.getInstance().personalPackage.getCellBoxIndex());
                        MenuUI.getInstance().setButton(1, 100, 2);
                        break;
                    }
                    DCanvas.getInstance().addInformation("Không có hàng hóa để bán");
                    break;
                }
                case 1: {
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendRechargeList();
                    break;
                }
                case 2: {
                    this.setState((byte) -17);
                    break;
                }
                case 3: {
                    this.keyMenuFee();
                }
            }
        }
    }

    private void keyMenuShopOtherOption(byte by) {
        if (this.strOption[by].indexOf(Pram.STRING_SHOP_OTHER[by]) != -1) {
            switch (by) {
                case 0: {
                    GameUI.getInstance().setNextMenu((byte) -15, this.strOption[by]);
                    break;
                }
                case 1: {
                    DCanvas.getInstance().addInformation("Chức năng chưa mở");
                    break;
                }
                case 2: {
                    DCanvas.getInstance().addInformation("Chức năng chưa mở");
                    break;
                }
                case 3: {
                    this.keyMenuFee();
                }
            }
        }
    }

    private void keyMenuShopRechargeOption(byte by) {
        if (this.strOption[by].indexOf(Pram.STRING_RECHARGE_MENU[by]) != -1) {
            switch (by) {
                case 0: {
                    Param.getInstance().MALL_FOR_NAME = null;
                    this.setState((byte) -19);
                    break;
                }
                case 1: {
                    FormDes.getInstance().showForm((byte) -23);
                    break;
                }
                case 2: {
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendMallSee((byte) 1);
                    break;
                }
                case 3: {
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendMallSee((byte) 2);
                }
            }
        }
    }

    private void keyMenuShopChannelOption(byte by) {
        if (this.strOption[by].indexOf(Pram.STRING_RECHARGE_TYPE[by]) != -1) {
            GameUI.getInstance().setNextMenu((byte) -16, "" + by);
        }
    }

    private void keyRankLise(byte by) {
        if (this.strOption[by].indexOf(Param.getInstance().Main_Name[by]) != -1) {
            GameUI.getInstance().setNextMenu((byte) -51, "" + by);
        }
    }

    private void KeyMenuKill(byte by) {
        if (this.strOption[by].indexOf(Pram.STRING_MENU_SKILL[by]) != -1) {
            switch (by) {
                case 0: {
                    GameUI.getInstance().setNextMenu((byte) 61, "Kỹ năng chiến đấu");
                    break;
                }
                case 1: {
                    DCanvas.getInstance().addInformation("Chức năng chưa mở");
                }
            }
        }
    }

    private void keyMenuList(byte by) {
        switch (by) {
            case 0: {
                this.setState((byte) -5);
                break;
            }
            case 1: {
                DCanvas.getInstance().addInformation("Chức năng chưa mở");
                break;
            }
            case 2: {
                this.setState((byte) -6);
                break;
            }
            case 3: {
                this.setState((byte) -1);
                break;
            }
            case 4: {
                this.setState((byte) -16);
                break;
            }
            case 5: {
                this.setState((byte) -14);
                break;
            }
            case 6: {
                GameUI.getInstance().setNextMenu((byte) 63, this.strOption[by]);
                break;
            }
            case 7: {
                this.setState((byte) -13);
                break;
            }
            case 8: {
                this.setState((byte) -12);
                break;
            }
            case 9: {
                this.setState((byte) 60);
                break;
            }
            case 10: {
                this.setState((byte) -9);
                break;
            }
            case 11: {
                Param.getInstance().Notic_state = true;
                DCanvas.getInstance().setNetLoad(true);
                NetSend.getInstance().sendNotice((byte) 0);
                break;
            }
        }
    }

    private void keyMenuLeftList(byte by) {
        switch (by) {
            case 0: {
                DCanvas.getInstance().setNetLoad(true);
                NetSend.getInstance().sendRechargeList();
                break;
            }
            case 1: {
                GameUI.getInstance().setNextMenu((byte) -15, this.strOption[by]);
                break;
            }
            case 2: {
                GameUI.getInstance().setNextMenu((byte) 61, this.strOption[by]);
                break;
            }
            case 3: {
                GameUI.getInstance().setNextMenu((byte) -7, this.strOption[by]);
                break;
            }
            case 4: {
                GameControl.getInstance().delUIbase(4);
                FormDes.getInstance().showForm((byte) 40);
                break;
            }
            case 5: {
                Param.getInstance().shtNoncePage = 1;
                GameUI.getInstance().setNextMenu((byte) -31, this.strOption[by]);
                break;
            }
            case 6: {
                GameUI.getInstance().setNextMenu((byte) 35, this.strOption[by]);
                break;
            }
            case 7: {
                if (ORPMe.ME.strConsortia == null || ORPMe.ME.strConsortia.equals("")) {
                    this.keyMenuFee();
                    DCanvas.getInstance().addInformation("Bạn không tham gia bất kỳ băng nhóm nào");
                    break;
                }
                DCanvas.getInstance().setNetLoad(true);
                NetSend.getInstance().sendConsortia((byte) 1, (byte) 1, 1);
                break;
            }
            case 8: {
                this.keyMenuFee();
                int n = (int) (System.currentTimeMillis() - (long) Param.getInstance().intGMQuizTimer);
                if (Param.getInstance().intGMQuizTimer == 0 || n >= 1800000) {
                    FormDes.getInstance().showForm((byte) -70);
                    break;
                }
                int n2 = (1800000 - n) / 60000;
                DCanvas.getInstance().addInformation(String.valueOf(n2) + "Câu hỏi một lần nữa sau một phút");
                break;
            }
            case 9: {
                GameUI.getInstance().setNextMenu((byte) 60, this.strOption[by]);
                break;
            }
            case 10: {
                GameControl.getInstance().delUIbase(4);
                GameControl.getInstance().CreateState((byte) 7);
                DialogUI.getInstance().setDialog((byte) 10, "Vui lòng chờ trong khi tải tài nguyên ...", (byte) 2);
                DCanvas.getInstance().setNetLoad(true);
                NetParse.getInstance().setInitLoadingPre();
                NetParse.getInstance().setInitLoading();
                NetSend.getInstance().sendBreakAwayMap();
            }
        }
    }

    private void setPointer() {
        if (this.strOption == null) {
            return;
        }
        short s = this.shtMainMenuItem_x;
        short s2 = this.shtMainMenuItem_y;
        int n = this.bytHItemCol * (this.shtMainMenuItem_w + this.shtMainMenuitemHspace);
        int n2 = this.shtMainMenu_y + this.shtMainMenu_h - this.shtMainMenuItem_h - 14;
        int n3 = this.shtMainMenu_x + 4;
        int n4 = this.shtMainMenu_y + this.shtMainMenu_h - this.shtMainMenuItem_h - 4 - 5;
        short s3 = this.shtMainMenuItem_w;
        short s4 = this.shtMainMenuItem_h;
        int n5 = this.shtMainMenu_x + this.shtMainMenu_w - this.shtMainMenuItem_w - 4;
        if (DCanvas.getInstance().CurPressedY < n4) {
            if (DCanvas.getInstance().IsPointerDown(s, s2, n, n2)) {
                byte by = (byte) ((DCanvas.getInstance().CurPressedY - s2) / (this.shtMainMenuItem_h + this.shtMainMenuitemVspace));
                byte by2 = (byte) ((DCanvas.getInstance().CurPressedX - s) / (this.shtMainMenuItem_w + this.shtMainMenuitemHspace));
                byte by3 = (byte) (by * this.bytHItemCol + by2);
                if (by3 > this.strOption.length - 1) {
                    return;
                }
                if (this.bytOptionMove == by3) {
                    this.keyAnswer(by3, this.bytOptionRoll, (byte) 0);
                } else {
                    this.bytOptionMove = by3;
                }
            }
        } else {
            if (DCanvas.getInstance().IsPointerDown(n3, n4, s3, s4)) {
                DCanvas.getInstance().buttonLeftFlash = 1;
                DCanvas.getInstance().buttonLeftFlash = 0;
                this.keyAnswer(this.bytOptionMove, this.bytOptionRoll, (byte) 0);
            }
            if (DCanvas.getInstance().IsPointerDown(n5, n4, s3, s4)) {
                DCanvas.getInstance().buttonRightFlash = 0;
                if (this.bytState == 0) {
                    GameControl.getInstance().delUIbase(4);
                    Param.getInstance().vOptionPlace = null;
                } else {
                    this.keyMenuFee();
                }
            }
        }
        if (DCanvas.getInstance().IsPointerDown(GameUI.getInstance().LeftDownX - 16, GameUI.getInstance().LeftDownY - 16, 32, 32) && this.bytState == 0) {
            GameControl.getInstance().delUIbase(4);
            Param.getInstance().vOptionPlace = null;
        }
    }
}
