// Decompiled with: CFR 0.151
// Class Version: 1
package face;

import base.DCanvas;
import base.GameControl;
import base.Macro;
import base.Param;
import common.Common;
import common.DrawBase;
import common.GridTable;
import common.IDefines;
import common.IResConfig;
import common.LayoutStyle;
import common.ScrollText;
import game.CMidlet;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import means.ImageManager;
import means.QSprite;
import means.Rms;
import means.StringManager;
import means.Wap;
import model.ORPlayer;
import model.ORole;
import model.ResPoolFactory;
import network.NetParse;
import network.NetSend;

public class LandUI
        implements UIbase {

    private boolean blnIsThis = false;
    private int landUiCounter;
    public final byte BYTE_BUTTON_NONE = (byte) -1;
    public final byte BYTE_BUTTON_OK;
    public final byte BYTE_BUTTON_BACK;
    public final byte BYTE_BUTTON_FOUND = (byte) 2;
    public final byte BYTE_BUTTON_DEL = (byte) 3;
    public final byte BYTE_BUTTON_EXIT = (byte) 4;
    public final byte BYTE_BUTTON_JIXU = (byte) 5;
    public final byte BYTE_BUTTON_LOAD = (byte) 6;
    public final byte BYTE_BUTTON_OPEN = (byte) 7;
    public final byte BYTE_BUTTON_CLOSE = (byte) 8;
    public static final byte LAND_LOGO = 0;
    public static final byte LAND_SPLASH = 1;
    public static final byte LAND_MAIN_MENU = 2;
    public static final byte LAND_SERVER = 3;
    public static final byte LAND_HAND_LAND = 4;
    public static final byte LAND_POINTS_SAREA = 5;
    public static final byte LAND_HELP = 6;
    public static final byte LAND_EXIT = 7;
    public static final byte LAND_MORE_GAME = 8;
    public static final byte LAND_REGISTER = 9;
    public static final byte LAND_SELECT_ROLE = 10;
    public static final byte LAND_FOUND_ROLE = 11;
    public static final byte LAND_ALTER_PASSWORD = 12;
    public static final byte LAND_ABOUT = 13;
    public static final byte LAND_FLASH = 14;
    public static final byte LAND_CHOOSE_EDITION = 15;
    public final byte TWO_KEY_NONE = (byte) -1;
    public final byte TWO_KEY_BACK = (byte) 3;
    private final String STRING_WORD_ISDEL;
    private final String STRING_WORD_DEL;
    private final String STRING_WORD_ENROL;
    private final String STRING_WORD_LAND;
    private final String STRING_WORD_SERVER_LAND;
    public final String STRING_WORD_FOUND_ROLE;
    public final String STRING_WORD_NONE_ROLE_NAME;
    private final String STRING_WORD_IN_GAME;
    public static final String STRING_NEW_ROLE_DEFAULT = "Lấy dữ liệu ký tự";
    private final String STRING_WORD_STAR;
    private final String STRING_WORD_SERVER_CLOSE;
    private final String LAND_UI_HELP_STR;
    private final String LAND_UI_ABOUT_STR;
    private final String STRING_CUE_LONGSHAN;
    private final String STRING_CUE_HEMUDU;
    private String OCCUPATION;
    private String OCCUPATIONTwo;
    private String STRING_CUE_LONGKUANG;
    private String STRING_CUE_YONGAN;
    private String STRING_CUE_LONGSHI;
    private String STRING_CUE_TIANYING;
    private final byte LAND_ACCOUNTS = (byte) 11;
    private final byte LAND_PASSWORD = (byte) 11;
    private final byte SCOPE = (byte) 6;
    public boolean blnIsIn;
    public byte landState = (byte) -1;
    private byte bytMaxH;
    public short shtLandMove;
    public short shtLandRoll;
    private String[] strLandString;
    private Image imgNumber;
    private Image imgButton;
    private boolean blnIsLogic;
    public byte[] bytDialogData;
    public String strLandAccounts = "";
    public boolean blnIsSaveLandInfo;
    public boolean AutoLogin;
    public String strLandPassword = "";
    private String strOldLandPassword = "";
    private byte[] bytButtonType;
    public Hashtable hPlayerList;
    public TwoDialogUI twodialog;
    private byte bytAcrossType;
    private int[] intsRockPlace;
    private short shtRoleWordY;
    public byte bytReelMove;
    private byte bytCountryIndex;
    private byte bytSexIndex;
    private byte bytOccupation;
    public String strName = "";
    private short shtRoleFoundWordH;
    private int intTimer;
    public byte bytWordMaxH;
    public short shtMenuMoveLength;
    public static LandUI land = null;
    private Image logoImg;
    private Image channelLogo;
    private long LogoTime;
    private long LogoTimeStart;
    private String[] Prompt;
    private QSprite anykey;
    private String anykey_resource = "pressanykey";
    private QSprite[] background;
    private QSprite[] arrows;
    private QSprite bird;
    private QSprite[] font;
    private QSprite logo;
    private int Menuindex;
    private int SpeedG = 15;
    private int Move_RightMan_X;
    private int Move_LeftMan_X;
    private byte menuItemIndex_row;
    private byte menuItemIndex_list;
    private final byte MENU_ITEM_RAPIDLAND;
    private final byte MENU_ITEM_HANDLAND;
    private final byte MENU_ITEM_REGISTER = (byte) 2;
    private final byte MENU_ITEM_POINTS_SAREA = (byte) 3;
    private final byte MENU_ITEM_ABOUT = (byte) 4;
    private final byte MENU_ITEM_HELP = (byte) 5;
    private final byte MENU_ITEM_MORE_GAME = (byte) 6;
    private final byte MENU_ITEM_EXIT = (byte) 7;
    private final byte MENU_ITEM_NUM = (byte) 3;
    private final byte[][] MENU_ITEM_INDEX_STATE;
    private int[] default_font_left;
    private int[] default_font_right;
    private int[] flash_array;
    private int[] left_array;
    private int[] right_array;
    private byte img_fontw;
    private byte img_fonth;
    private int begin_fonty;
    private int begin_fontLeftx;
    private int begin_fontRightx;
    private QSprite account;
    private String account_resource;
    private int account_frame_width;
    private int account_frame_height;
    private int font_Y;
    private int sign_X;
    private short up_frame_X;
    private short up_frame_W;
    private short up_frame_H;
    private short down_frame_Y;
    private short down_frame_H;
    private GridTable choose_frame_tab;
    private short[] font_array;
    private short choose_frame_index_X;
    private short choose_frame_index_Y;
    private short choose_frame_index_W;
    private short choose_frame_index_H;
    private short choose_space;
    private short[] choose_index;
    private int input_text_X;
    private int input_account_text_Y;
    private int input_password_text_Y;
    private int cursor_X;
    private short _tempaccountx;
    private short _tempaccounty;
    private short _temppasswordx;
    private short _temppasswordy;
    private short _tempw;
    private short _tempH;
    private Image sign;
    Image imgLand1;
    Image imgLand2;
    private short[] createrole_rowpercent;
    private QSprite char_script;
    private short[] char_index;
    private String font_resource;
    private short[] font_index;
    private int role_framex;
    private short role_framey;
    private short role_framew;
    private short role_frameh;
    private boolean Ifkey;
    private int _textY;
    private int _textH;
    private int _tectW;
    private QSprite[] triangle;
    private String triangle_resource;
    private short triangle_widht;
    private short triangle_height;
    private short gridtable_width;
    private short gridtable_height;
    private short[] gridtable_array;
    private boolean SavsChoose;
    public static ORPlayer orplayerCreat = null;
    private GridTable gridtable;
    private ScrollText scrolltext;
    private short[] ServiseLiset_Percent;
    private short ServiseLisetLength;
    private int ServiseStateFontColour;
    private short listHeight;
    public boolean chooseServise;
    public boolean chooseRole;
    public boolean chooseMainMenu;
    private Peffect could;
    private Peffect star;
    private boolean peffect;
    boolean stop;
    public String twodialogStr;
    boolean blnFisrt;
    boolean blnSecond;
    private int intKeyCode;
    private byte bytChooseIndex;
    private boolean blnIsWord;
    private int intTalkingTime;
    private String strTempInputString;
    private String strInputString;
    private final int TEXT_COLOR;
    private boolean blnRockFlash;
    private short shtRockFlashY;
    private short shtCloudFlashY;
    private byte bytRockFlashType;
    GridTable gridTable_row8_col1_Table;
    GridTable gridTable_row2_col1_Table;
    GridTable gridTable_row3_col1_Table;
    GridTable gridTable_row2;
    GridTable role_p_value_Table;
    GridTable roleListTable;
    GridTable gridTable;

    public static LandUI getInstance() {
        return land;
    }

    public LandUI() {
        this.BYTE_BUTTON_OK = 0;
        this.BYTE_BUTTON_BACK = 1;
        this.STRING_WORD_ISDEL = "Xóa";
        this.STRING_WORD_DEL = "Loại bỏ";
        this.STRING_WORD_ENROL = "Đăng ký";
        this.STRING_WORD_LAND = "Đang vào game";
        this.STRING_WORD_SERVER_LAND = "Đăng nhập vào máy chủ";
        this.STRING_WORD_FOUND_ROLE = "Tạo nhân vật";
        this.STRING_WORD_NONE_ROLE_NAME = "Vui lòng nhập tên";
        this.STRING_WORD_IN_GAME = "Vào trò chơi";
        this.STRING_WORD_STAR = "Nhấn phím * để xóa";
        this.STRING_WORD_SERVER_CLOSE = "Máy chủ này hiện đang bị đóng";
        this.LAND_UI_HELP_STR = "Mô tả Hoạt động \n ------ \n 【Còn lại Phần mềm】 【Nút Trung】 Xác nhận, tấn công, nhặt vật phẩm, khóa NPC hoặc các chức năng tương tác khác; 【Phải Phần mềm】 menu, hủy và trả lại; Vai trò và hướng của con trỏ; \n [1-8] Phím tắt phím chức năng tương ứng; \n [9] chìa khóa để chuyển đổi các phím tắt thanh \n [0 phím] chat chức năng; \n [# phím] để chuyển đổi tất cả các mục tiêu; Mục tiêu bạn bè";
        this.LAND_UI_ABOUT_STR = "Nhà phát triển: Luyện studio";
        this.STRING_CUE_LONGSHAN = "Con cháu của Rồng là con người sống trong lục địa Shenlong, họ là một cộng đồng có cả nhân cách tốt và chiến thắng. Được mô tả như là hậu duệ của gia tộc Rồng, trong thời cổ đại và các vị thần trên thiên đường cộng tác để đánh bại các gia tộc Mozu, và sau đó bởi đức hạnh của bộ lạc trí đại diện cho đất liền của Dragon từ thế hệ này sang thế hệ nhân, đã trở thành bậc thầy của đại lục.";
        this.STRING_CUE_HEMUDU = "Trong thời cổ đại của hậu duệ của Protoss và Terran Mozu, họ đã được sinh ra từ truyền cảm với loài người và Mozu là kẻ thù, khái niệm này đã được truyền từ thế hệ này sang thế hệ khác, vì vậy mà Mozu hiện tại cực kỳ cực đoan và Thế giới Rồng Trạng thái hận thù. Với sức sống tuyệt vời của họ, họ sống sót qua những điều kiện khắc nghiệt của đất liền khổng lồ mà không quên trả thù";
        this.MENU_ITEM_RAPIDLAND = 0;
        this.MENU_ITEM_HANDLAND = 1;
        this.MENU_ITEM_INDEX_STATE = new byte[][]{{0, 1}, {2, 3}, {4, 5}, {6, 7}};
        this.img_fontw = (byte) 14;
        this.img_fonth = (byte) 20;
        this.account_resource = "accounts";
        this.font_resource = "char_script";
        this.triangle_resource = "littlearrow";
        this.triangle_widht = (short) 9;
        this.triangle_height = (short) 15;
        this.gridtable = null;
        this.blnFisrt = true;
        this.blnSecond = false;
        this.intTalkingTime = 1000;
        this.strTempInputString = "";
        this.strInputString = "";
        this.TEXT_COLOR = 8142636;
        land = this;
    }

    public void clean() {
        land = null;
    }

    public void isThis(boolean bl) {
        this.blnIsThis = bl;
    }

    public void init() {
        this.blnIsIn = false;
        this.bytButtonType = new byte[3];
        this.imgButton = ImageManager.CreateImage("softkey", "loading");
        this.setButton((byte) -1, (byte) -1, (byte) -1);
        Rms.loadID();
        Rms.loadUseCode();
        Rms.loadServiseID();
    }

    public void setState(byte state) {
        this.chooseRole = false;
        this.chooseServise = false;
        this.chooseMainMenu = false;
        this.landUiCounter = 0;
        if (!this.SavsChoose) {
            this.shtLandMove = 0;
        }
        this.shtLandRoll = 0;
        DCanvas.getInstance().buttonLeftFlash = 0;
        DCanvas.getInstance().buttonRightFlash = 0;
        this.twodialog = null;
        this.twodialogStr = null;
        this.setButton((byte) -1, (byte) -1, (byte) -1);
        StringManager.resetWordMoveTimer();
        switch (state) {
            case 0: {
                this.initLogo();
                break;
            }
            case 15: {
                this.initEdition();
                break;
            }
            case 1: {
                this.initSplash();
                this.landState = state;
                this.initClientConnect();
                break;
            }
            case 14: {
                this.initFlash();
                break;
            }
            case 2: {
                this.blnIsIn = false;
                if (this.peffect) {
                    this.setPeffect();
                }
                this.peffect = true;
                this.initMainMenu();
                break;
            }
            case 3: {
                this.blnIsIn = true;
                this.initServerList();
                NetSend.getInstance().sendClientStep((byte) 4);
                break;
            }
            case 4: {
                this.blnIsIn = false;
                this.initLand();
                break;
            }
            case 5: {
                this.blnIsIn = false;
                this.initAccountSafe();
                break;
            }
            case 6: {
                this.initHelp(state);
                this.blnIsLogic = true;
                break;
            }
            case 13: {
                this.initHelp(state);
                this.blnIsLogic = true;
                break;
            }
            case 7: {
                this.setTwoDialog("Đồng ý rời trò chơi?", (byte) 0, (byte) 1);
                break;
            }
            case 8: {
                this.setTwoDialog("Rời khỏi trò chơi và nhập trang web chính thức của trò chơi", (byte) 0, (byte) 1);
                break;
            }
            case 9: {
                this.initRegister();
                break;
            }
            case 10: {
                this.blnIsIn = true;
                Rms.loadPlace();
                this.initSelectRole();
                NetSend.getInstance().sendClientStep((byte) 5);
                this.cleanAllResource();
                break;
            }
            case 11: {
                this.initFoundRole();
                break;
            }
        }
        this.landState = state;
    }

    public void initClientConnect() {
        NetSend.getInstance().sendUserCenterURL();
        Rms.getUserNum();
        if (Rms.strRmsUserNum == null) {
            long l = System.currentTimeMillis();
            Rms.saveUseNum("" + l);
            Param.CLIENT_FIRST_START_NUM = String.valueOf(l);
            Param.BLN_CLIENT_FIRST_START = true;
        } else {
            Param.BLN_CLIENT_FIRST_START = false;
            Param.CLIENT_FIRST_START_NUM = null;
        }
        NetSend.getInstance().sendClientStep((byte) 1);
    }

    public void setLandRole() {
        if (this.bytDialogData == null) {
            this.bytDialogData = new byte[4];
        }
        this.blnIsLogic = false;
    }

    private void drawLogo() {
        DrawBase.setColor(0xFFFFFF);
        DrawBase.setClip(0, 0, Macro.SCREEN_WIDTH, Macro.SCREEN_HEIGHT);
        DrawBase.fillRect(0, 0, Macro.SCREEN_WIDTH, Macro.SCREEN_HEIGHT, 0xFFFFFF);
        if (this.LogoTime < 3L && this.channelLogo != null) {
            DrawBase.drawImage(this.channelLogo, IDefines.LAND_UI_LOGO_IMG_POS_X, IDefines.LAND_UI_LOGO_IMG_POS_Y, 3);
        } else {
            DrawBase.drawImage(this.logoImg, IDefines.LAND_UI_LOGO_IMG_POS_X, IDefines.LAND_UI_LOGO_IMG_POS_Y, 3);
        }
    }

    private void initLogo() {
        if (this.channelLogo == null) {
            this.channelLogo = ImageManager.CreateImage(Macro.PARTNER_LOGO_NAME, Macro.CHANNEL_NAME);
        }
        if (this.logoImg == null) {
            this.logoImg = ImageManager.CreateImage("17game", "logo");
        }
        DrawBase.Transparency = ImageManager.loadSpriteById(0, ImageManager.EncodespriteId("Transparency", "Transparency"), "Transparency", "Transparency", "ui");
        this.LogoTimeStart = System.currentTimeMillis();
    }

    private void cleanLogo() {
        this.logoImg = null;
        this.channelLogo = null;
        this.LogoTime = 0L;
    }

    private void updateLogo() {
        this.LogoTime = (System.currentTimeMillis() - this.LogoTimeStart) / 1000L;
        int n = 6;
        if (this.channelLogo == null) {
            n = 3;
        }
        if (this.LogoTime >= (long) n) {
            this.setState((byte) 1);
        }
    }

    private void updateEdition() {
        int n = DCanvas.getInstance().getKeyRelease();
        if (n == 262144 || n == 65536) {
            Rms.bytLevalFunctionFlag = 1;
            GameControl.getInstance().checkLevelFunction();
            this.setState((byte) 1);
        } else if (n == 131072) {
            Rms.bytLevalFunctionFlag = 0;
            GameControl.getInstance().checkLevelFunction();
            this.setState((byte) 1);
        }
    }

    private void initEdition() {
        this.cleanLogo();
        GameControl.getInstance().initStart();
        this.Prompt = StringManager.wenZi("Hãy chọn hiệu ứng theo hiệu suất điện thoại di động Mẹo: Sau khi nhập trò chơi trong \"Menu chính - Cài đặt - Hệ thống\" để sửa đổi màn hình hiển thị lại", Macro.SCREEN_WIDTH - (Macro.FONTW << 1));
    }

    private void drawEdition() {
        DrawBase.fillRect(0, 0, Macro.SCREEN_WIDTH, Macro.SCREEN_HEIGHT, 0);
        StringManager.drawWordMove(DCanvas.gameG, this.Prompt, Macro.FONTW, (Macro.SCREEN_HEIGHT >> 1) - (Macro.FONTHEIGHT << 1), (Macro.SCREEN_HEIGHT >> 1) - (Macro.FONTHEIGHT << 1), 0xFFFFFF, 20);
        DrawBase.drawString("Đẹp", 2, Macro.SCREEN_HEIGHT, 0xFFFFFF, 36);
        DrawBase.drawString("Thường", Macro.SCREEN_WIDTH - 2, Macro.SCREEN_HEIGHT, 0xFFFFFF, 40);
    }

    private void initSplash() {
        this.cleanLogo();
        this.setPeffect();
        this.loadBack();
        Rms.getUserNum();
        if (Rms.strRmsUserNum == null) {
            long l = System.currentTimeMillis();
            Rms.saveUseNum("" + l);
            Param.CLIENT_FIRST_START_NUM = String.valueOf(l);
            Param.BLN_CLIENT_FIRST_START = true;
        } else {
            Param.BLN_CLIENT_FIRST_START = false;
            Param.CLIENT_FIRST_START_NUM = null;
        }
        NetSend.getInstance().sendClientStep((byte) 1);
    }

    private void cleanSplash() {
        this.anykey = null;
    }

    private void setPeffect() {
        Param.getInstance().CAMERAX = 0;
        Param.getInstance().CAMERAY = 0;
        Param.getInstance().shtMapMaxWidth = (short) (Macro.SCREEN_WIDTH + 50);
        Param.getInstance().shtMapMaxHeight = Macro.SCREEN_HEIGHT;
        this.could = new Peffect();
        this.star = new Peffect();
        this.could.setPeffect((byte) 3, (byte) Common.getRandom(0, 2));
        this.star.setPeffect((byte) 6, (byte) Common.getRandom(0, 2));
    }

    private void loadBack() {
        if (this.anykey == null) {
            this.anykey = ImageManager.loadSpriteById(1, ImageManager.EncodespriteId(this.anykey_resource, this.anykey_resource), this.anykey_resource, this.anykey_resource, "loading");
            this.anykey.setAnimation(0);
        }
        if (this.background == null) {
            this.background = new QSprite[2];
            int n = 0;
            while (n < this.background.length) {
                this.background[n] = ImageManager.loadSpriteById(1, ImageManager.EncodespriteId(GameControl.STRING_IMAGE_BACK(), String.valueOf(n)), GameControl.STRING_IMAGE_BACK(), GameControl.STRING_IMAGE_BACK(), "loading");
                ++n;
            }
            this.background[0].setAnimation(1);
            this.background[1].setAnimation(2);
        }
        if (this.logo == null) {
            this.logo = ImageManager.loadSpriteById(1, ImageManager.EncodespriteId("logo", "logo"), "logo", "logo", "loading");
            this.logo.setLoopOffset(1);
            this.logo.setAnimation(0);
        }
        if (this.bird == null) {
            this.bird = ImageManager.loadSpriteById(1, ImageManager.EncodespriteId("title_he", "title_he"), "title_he", "title_he", "loading");
            this.bird.setAnimation(0);
        }
    }

    private void drawSplash() {
        int n = 4574703;
        DrawBase.fillRect(0, 0, Macro.SCREEN_WIDTH, Macro.SCREEN_HEIGHT, n);
        this.background[0].drawAnimationFrame(DCanvas.gameG, 0, 0, Macro.SCREEN_WIDTH >> 1, Macro.SCREEN_HEIGHT >> 1);
        this.background[0].drawAnimation(DCanvas.gameG, Macro.SCREEN_WIDTH >> 1, Macro.SCREEN_HEIGHT >> 1);
        this.background[1].drawAnimation(DCanvas.gameG, Macro.SCREEN_WIDTH >> 1, Macro.SCREEN_HEIGHT >> 1);
        this.bird.drawAnimation(DCanvas.gameG, Macro.SCREEN_WIDTH, Macro.SCREEN_HEIGHT);
        this.logo.drawAnimation(DCanvas.gameG, Macro.SCREEN_WIDTH >> 1, Macro.SCREEN_HEIGHT / 6);
        if (this.landState == 1) {
            this.anykey.drawAnimation(DCanvas.gameG, 0, Macro.SCREEN_HEIGHT);
        }
        if (this.star != null) {
            this.star.print(DCanvas.gameG);
        }
        if (this.could != null) {
            this.could.print(DCanvas.gameG);
        }
    }

    private void updateSplash() {
        if (DCanvas.getInstance().isKeyRelease()) {
            this.setState((byte) 14);
            this.cleanSplash();
        }
        if (this.anykey != null) {
            this.anykey.update();
        }
        if (this.background != null) {
            this.background[0].update();
            this.background[1].update();
        }
        if (this.bird != null) {
            this.bird.update();
        }
        if (this.logo != null) {
            this.logo.update();
            if (this.logo.isPlayDone()) {
                this.stop = true;
            }
            if (this.stop) {
                this.logo.setAnimation(1);
                this.stop = false;
            }
        }
    }

    private void initFlash() {
        GameControl.getInstance().initStart();
        this.initMainMenu();
        this.Move_LeftMan_X = 0;
        this.Move_RightMan_X = Macro.SCREEN_WIDTH;
        this.begin_fontLeftx = (Macro.SCREEN_WIDTH >> 1) - this.img_fontw * 4;
        this.begin_fontRightx = (Macro.SCREEN_WIDTH >> 1) + this.img_fontw * 4;
    }

    private void cleanFlash() {
        this.Move_LeftMan_X = 0;
        this.Move_RightMan_X = 0;
    }

    private void drawFlash() {
        this.drawSplash();
        int n = 0;
        while (n < this.default_font_left.length) {
            this.font[0].drawAnimationFrame(DCanvas.gameG, this.default_font_left[n], 0, this.Move_LeftMan_X, this.begin_fonty + n * this.img_fonth);
            ++n;
        }
        n = 0;
        while (n < this.default_font_right.length) {
            this.font[0].drawAnimationFrame(DCanvas.gameG, this.default_font_right[n], 0, this.Move_RightMan_X, this.begin_fonty + n * this.img_fonth);
            ++n;
        }
    }

    private void updateFlash() {
        this.updateMainFlash();
        ++this.landUiCounter;
        this.landUiCounter += this.SpeedG;
        this.Move_LeftMan_X += this.landUiCounter;
        this.Move_RightMan_X -= this.landUiCounter;
        if (this.Move_LeftMan_X >= this.begin_fontLeftx && this.Move_RightMan_X <= this.begin_fontRightx) {
            this.Move_LeftMan_X = this.begin_fontLeftx;
            this.Move_RightMan_X = this.begin_fontRightx;
            this.setState((byte) 2);
            this.cleanFlash();
        }
    }

    private void initMainMenu() {
        int n;
        this.loadBack();
        this.default_font_left = new int[]{3, 9, 12, 18};
        int[] nArray = new int[4];
        nArray[1] = 6;
        nArray[2] = 15;
        nArray[3] = 21;
        this.default_font_right = nArray;
        this.flash_array = new int[]{4, 1, 10, 7, 13, 16, 19, 22};
        this.left_array = new int[]{0, 1, 2, 3};
        this.right_array = new int[]{1, 2, 3, 4};
        if (this.font == null) {
            this.font = new QSprite[8];
            n = 0;
            while (n < this.font.length) {
                this.font[n] = ImageManager.loadSpriteById(1, ImageManager.EncodespriteId("title_front", String.valueOf(n)), "title_front", "title_front", "loading");
                ++n;
            }
            n = 0;
            while (n < this.font.length) {
                this.font[n].setAnimation(this.flash_array[n]);
                ++n;
            }
        }
        if (this.arrows == null) {
            this.arrows = new QSprite[2];
            n = 0;
            while (n < this.arrows.length) {
                this.arrows[n] = ImageManager.loadSpriteById(1, ImageManager.EncodespriteId("title_effect", String.valueOf(n)), "title_effect", "title_effect", "loading");
                ++n;
            }
            this.arrows[0].setAnimation(0);
            this.arrows[1].setAnimation(1);
        }
        this.begin_fonty = Macro.SCREEN_HEIGHT * 9 / 19;
        this.begin_fontLeftx = (Macro.SCREEN_WIDTH >> 1) - this.img_fontw * 4;
        this.begin_fontRightx = (Macro.SCREEN_WIDTH >> 1) + this.img_fontw * 4;
    }

    private void drawMainMenu() {
        this.drawSplash();
        int n = 0;
        while (n < this.default_font_left.length) {
            if (this.MENU_ITEM_INDEX_STATE[this.menuItemIndex_row][this.menuItemIndex_list] - n != this.left_array[n]) {
                this.font[0].drawAnimationFrame(DCanvas.gameG, this.default_font_left[n], 0, this.begin_fontLeftx, this.begin_fonty + n * this.img_fonth);
            }
            ++n;
        }
        n = 0;
        while (n < this.default_font_right.length) {
            if (this.MENU_ITEM_INDEX_STATE[this.menuItemIndex_row][this.menuItemIndex_list] - n != this.right_array[n]) {
                this.font[0].drawAnimationFrame(DCanvas.gameG, this.default_font_right[n], 0, this.begin_fontRightx, this.begin_fonty + n * this.img_fonth);
            }
            ++n;
        }
        n = this.menuItemIndex_row * this.img_fonth;
        int n2 = this.menuItemIndex_list * (this.img_fontw * 8);
        if (this.Menuindex == 0) {
            this.font[0].drawAnimationFrame(DCanvas.gameG, this.flash_array[2 * this.menuItemIndex_row + this.menuItemIndex_list], 0, this.begin_fontLeftx + n2, this.begin_fonty + n);
        } else {
            this.font[this.Menuindex].drawAnimation(DCanvas.gameG, this.begin_fontLeftx + n2, this.begin_fonty + n);
        }
        this.arrows[0].drawAnimation(DCanvas.gameG, this.begin_fontLeftx - this.img_fontw * 3 - 4 + n2, this.begin_fonty + n);
        this.arrows[1].drawAnimation(DCanvas.gameG, this.begin_fontLeftx + n2, this.begin_fonty + 2 + n);
        DrawBase.drawString("Phiên bản:" + Macro.VERSION, Macro.SCREEN_WIDTH >> 1, Macro.SCREEN_HEIGHT, 0xFFFFFF, 33);
    }

    private void updateMainFlash() {
        if (this.background != null) {
            this.background[0].update();
            this.background[1].update();
        }
        if (this.bird != null) {
            this.bird.update();
        }
        if (this.arrows != null) {
            this.arrows[0].update();
            this.arrows[1].update();
        }
        if (this.font != null) {
            int n = 0;
            while (n < this.font.length) {
                this.font[n].update();
                ++n;
            }
        }
        if (this.logo != null) {
            this.logo.update();
            if (this.logo.isPlayDone()) {
                this.stop = true;
            }
            if (this.stop) {
                this.logo.setAnimation(1);
                this.stop = false;
            }
        }
    }

    private void updateMainMenu() {
        this.updateMainFlash();
        switch (DCanvas.getInstance().getKeyRelease()) {
            case 65536:
            case 262144: {
                this.blnIsLogic = false;
                switch (this.MENU_ITEM_INDEX_STATE[this.menuItemIndex_row][this.menuItemIndex_list]) {
                    case 0: {
                        setState((byte) 4);
//                        this.LoadAccoundPassword();
//                        if (this.strLandAccounts != null && this.strLandPassword != null && !this.strLandAccounts.equals("") && !this.strLandPassword.equals("")) {
//                            this.setTwoDialog("Đang vào game", (byte) -1, (byte) -1);
//                            DCanvas.getInstance().setNetLoad(true);
//                            NetSend.getInstance().sendQuickRegister();
//                            break;
//                        } else {
//                            setState((byte) 4);
//                        }
                        break;
                    }
                    case 1: {
                        this.setTwoDialog("Tính năng bảo trì.", (byte) -1, (byte) 1);
                        break;
                    }
                    case 2: {
                        //this.setState((byte) 5);
                        this.setTwoDialog("Tính năng bảo trì.", (byte) -1, (byte) 1);
                        break;
                    }
                    case 3: {
                        //this.setState((byte) 9);
                        this.setTwoDialog("Tính năng bảo trì.", (byte) -1, (byte) 1);
                        break;
                    }
                    case 4: {
                        this.setState((byte) 6);
                        break;
                    }
                    case 5: {
                        this.setState((byte) 13);
                        break;
                    }
                    case 6: {
                        this.setState((byte) 8);
                        break;
                    }
                    case 7: {
                        this.setState((byte) 7);
                        break;
                    }
                }
                break;
            }
            case 131072: {
                if (this.twodialog == null) {
                    break;
                }
                this.twodialog = null;
                this.setButton((byte) 0, (byte) -1, (byte) -1);
            }
        }
        if (DCanvas.getInstance().isKeyDown(4096)) {
            this.menuItemIndex_row = (byte) (this.menuItemIndex_row - 1);
            if (this.menuItemIndex_row < 0) {
                this.menuItemIndex_row = (byte) 3;
            }
            this.Menuindex = 2 * this.menuItemIndex_row + this.menuItemIndex_list;
        } else if (DCanvas.getInstance().isKeyDown(8192)) {
            this.menuItemIndex_row = (byte) (this.menuItemIndex_row + 1);
            if (this.menuItemIndex_row > 3) {
                this.menuItemIndex_row = 0;
            }
            this.Menuindex = 2 * this.menuItemIndex_row + this.menuItemIndex_list;
        } else if (DCanvas.getInstance().isKeyDown(16384)) {
            this.menuItemIndex_list = (byte) (this.menuItemIndex_list - 1);
            if (this.menuItemIndex_list < 0) {
                this.menuItemIndex_list = 1;
            }
            this.Menuindex = 2 * this.menuItemIndex_row + this.menuItemIndex_list;
        } else if (DCanvas.getInstance().isKeyDown(32768)) {
            this.menuItemIndex_list = (byte) (this.menuItemIndex_list + 1);
            if (this.menuItemIndex_list > 1) {
                this.menuItemIndex_list = 0;
            }
            this.Menuindex = 2 * this.menuItemIndex_row + this.menuItemIndex_list;
        }
        if (DCanvas.getInstance().IsKeyRelease(4096) || DCanvas.getInstance().IsKeyRelease(8192) || DCanvas.getInstance().IsKeyRelease(16384) || DCanvas.getInstance().IsKeyRelease(32768)) {
            this.Menuindex = 0;
        }
    }

    private void initHelp(byte by) {
        StringBuffer stringBuffer = new StringBuffer();
        if (by == 13) {
            stringBuffer.append("Nhà phát triển: Luyện studio");
        } else if (by == 6) {
            stringBuffer.append("Mô tả Hoạt động \n ------ \n 【Còn lại Phần mềm】 【Nút Trung】 Xác nhận, tấn công, nhặt vật phẩm, khóa NPC hoặc các chức năng tương tác khác; 【Phải Phần mềm】 menu, hủy và trả lại; Vai trò và hướng của con trỏ; \n [1-8] Phím tắt phím chức năng tương ứng; \n [9] chìa khóa để chuyển đổi các phím tắt thanh \n [0 phím] chat chức năng; \n [# phím] để chuyển đổi tất cả các mục tiêu; Mục tiêu bạn bè");
        }
        stringBuffer.append("\n");
        stringBuffer.append("\n");
        stringBuffer.append("Thông tin dịch vụ");
        stringBuffer.append("\n");
        stringBuffer.append("------");
        stringBuffer.append("\n");
        stringBuffer.append("Số phiên bản:");
        stringBuffer.append(Macro.VERSION);
        stringBuffer.append("\n");
        stringBuffer.append("");
        int n = 12;
        int n2 = 44;
        int n3 = Macro.SCREEN_HEIGHT - 38 - 31;
        this.scrolltext = new ScrollText(stringBuffer.toString(), n, n2, Macro.SCREEN_WIDTH - (n << 1), n3, 8142636, (byte) 0);
        this.setButton((byte) -1, (byte) -1, (byte) 1);
    }

    private void drawText(Graphics graphics, String[] stringArray, String string) {
        DCanvas.getInstance().drawTileTextBG(graphics, string);
        this.scrolltext.drawBread(graphics);
    }

    private void updateHelp() {
        switch (DCanvas.getInstance().getKeyRelease()) {
            case 131072: {
                this.setState((byte) 2);
                break;
            }
            case 4096: {
                this.scrolltext.keyUP();
                break;
            }
            case 8192: {
                this.scrolltext.keyDown();
            }
        }
    }

    private void updateExit() {
        if (DCanvas.getInstance().IsKeyRelease(65536) || DCanvas.getInstance().IsKeyRelease(262144)) {
            this.setButton((byte) -1, (byte) -1, (byte) -1);
            GameControl.getInstance().CreateState((byte) 7);
            DialogUI.getInstance().setDialog((byte) 15, "Vào trang web chính thức?", (byte) 2);
        } else if (DCanvas.getInstance().IsKeyRelease(131072)) {
            this.setState((byte) 2);
        }
    }

    private void updateMoreGame() {
        if (DCanvas.getInstance().IsKeyRelease(65536) || DCanvas.getInstance().IsKeyRelease(262144)) {
            Wap.wapView(CMidlet.getInstance(), Macro.OUTWAP);
            CMidlet.getInstance().exitGame();
        } else if (DCanvas.getInstance().IsKeyRelease(131072)) {
            this.setState((byte) 2);
        }
    }

    public void paint(Graphics graphics) {
        graphics.setColor(0);
        graphics.fillRect(0, 0, Macro.SCREEN_WIDTH, Macro.SCREEN_HEIGHT);
        switch (this.landState) {
            case 0: {
                this.drawLogo();
                break;
            }
            case 15: {
                this.drawEdition();
                break;
            }
            case 1: {
                this.drawSplash();
                break;
            }
            case 14: {
                this.drawFlash();
                break;
            }
            case 2:
            case 7:
            case 8: {
                this.drawMainMenu();
                break;
            }
            case 3: {
                this.drawServerList(graphics);
                break;
            }
            case 4: {
                this.drawAccountsLand(graphics);
                break;
            }
            case 5: {
                this.drawAccountSafe(graphics);
                break;
            }
            case 6: {
                this.drawText(graphics, this.strLandString, "Trợ giúp ");
                break;
            }
            case 13: {
                this.drawText(graphics, this.strLandString, "Quay lại trò chơi ");
                break;
            }
            case 9: {
                this.drawAccountsLand(graphics);
                break;
            }
            case 10: {
                this.drawSelectRole(graphics);
                break;
            }
            case 11: {
                this.drawFoundRole(graphics);
                break;
            }
        }
        if (this.twodialog != null) {
            this.twodialog.paint(graphics);
        }
        this.drawButton(this.bytButtonType);
    }

    private void drawTitleBox(Graphics graphics, String string) {
        LayoutStyle.getInstance().drawEarBox(graphics, string, 9263661, IDefines.GLOBAL_UI_MAIN_TITLEBOX_X, 0, IDefines.GLOBAL_UI_MAIN_TITLEBOX_WIDTH, 30);
    }

    public void setTwoDialog(String string, byte by, byte by2) {
        if (this.twodialog == null) {
            this.twodialog = new TwoDialogUI();
        }
        this.twodialogStr = string;
        this.twodialog.setTwoRect(string, -1, (short) 0, this.landState, (byte) 1, -1);
        this.blnIsLogic = true;
        this.setButton(by, (byte) -1, by2);
    }

    public void logic(int n) {
        if (!this.blnIsThis) {
            return;
        }
        if (this.chooseMainMenu) {
            this.initClientConnect();
            this.setState((byte) 2);
        } else if (this.chooseServise) {
            this.setState((byte) 3);
        } else if (this.chooseRole) {
            this.setState((byte) 10);
        }
        this.updateTouch();
        switch (this.landState) {
            case 0: {
                this.updateLogo();
                break;
            }
            case 15: {
                this.updateEdition();
                break;
            }
            case 1: {
                this.updateSplash();
                if (this.star != null) {
                    this.star.logic(n);
                }
                if (this.could == null) {
                    break;
                }
                this.could.logic(n);
                break;
            }
            case 14: {
                this.updateFlash();
                if (this.star != null) {
                    this.star.logic(n);
                }
                if (this.could == null) {
                    break;
                }
                this.could.logic(n);
                break;
            }
            case 2: {
                if (!DCanvas.getInstance().blnIsShowNetLoad) {
                    this.updateMainMenu();
                }
                if (this.star != null) {
                    this.star.logic(n);
                }
                if (this.could == null) {
                    break;
                }
                this.could.logic(n);
                break;
            }
            case 3: {
                if (DCanvas.getInstance().blnIsShowNetLoad) {
                    break;
                }
                this.updateServerList();
                break;
            }
            case 4: {
                if (DCanvas.getInstance().blnIsShowNetLoad) {
                    break;
                }
                this.updateLand();
                break;
            }
            case 5: {
                if (DCanvas.getInstance().blnIsShowNetLoad) {
                    break;
                }
                this.updateAccountSafe();
                break;
            }
            case 6:
            case 13: {
                this.updateHelp();
                break;
            }
            case 7: {
                this.updateExit();
                break;
            }
            case 8: {
                this.updateMoreGame();
                break;
            }
            case 9: {
                if (DCanvas.getInstance().blnIsShowNetLoad) {
                    break;
                }
                this.updateRegister();
                break;
            }
            case 10: {
                if (this.hPlayerList != null) {
                    Enumeration enumeration = this.hPlayerList.elements();
                    while (enumeration.hasMoreElements()) {
                        ORole oRole = (ORole) enumeration.nextElement();
                        if (oRole == null) {
                            continue;
                        }
                        oRole.update();
                        oRole.roleTaskAction(n);
                    }
                }
                if (this.blnRockFlash) {
                    this.flashRock();
                }
                if (DCanvas.getInstance().blnIsShowNetLoad) {
                    break;
                }
                this.updateSelectRole();
                break;
            }
            case 11: {
                this.logicReel(n);
                if (!DCanvas.getInstance().blnIsShowNetLoad) {
                    this.updateFoundRole();
                }
                if (this.triangle == null) {
                    break;
                }
                this.triangle[0].update();
                this.triangle[1].update();
                break;
            }
        }
    }

    private void updateTouch() {
        int n;
        int n2;
        if (DCanvas.getInstance().blnIsTouch && DCanvas.getInstance().blnPointerPressed) {
            if (DCanvas.getInstance().PointerDwonSoftKey_Left()) {
                DCanvas.getInstance().keyReleased(DCanvas.getInstance().GAME_KEY_SOFT_LEFT);
            } else if (DCanvas.getInstance().PointerDwonSoftKey_Right()) {
                DCanvas.getInstance().keyReleased(DCanvas.getInstance().GAME_KEY_SOFT_RIGHT);
            } else {
                switch (this.landState) {
                    case 1: {
                        if (!DCanvas.getInstance().IsPointerDown(0, 0, Macro.SCREEN_WIDTH, Macro.SCREEN_HEIGHT)) {
                            break;
                        }
                        DCanvas.getInstance().keyReleased(DCanvas.getInstance().GAME_KEY_LEFT);
                        break;
                    }
                    case 2: {
                        if (DCanvas.getInstance().IsPointerDown(this.begin_fontLeftx - this.img_fontw * 2, this.begin_fonty - (this.img_fonth >> 1), this.img_fontw << 2, this.img_fonth)) {
                            if (this.MENU_ITEM_INDEX_STATE[this.menuItemIndex_row][this.menuItemIndex_list] == 0) {
                                DCanvas.getInstance().keyReleased(DCanvas.getInstance().GAME_KEY_SOFT_LEFT);
                            } else {
                                this.menuItemIndex_row = 0;
                                this.menuItemIndex_list = 0;
                            }
                            this.Menuindex = 2 * this.menuItemIndex_row + this.menuItemIndex_list;
                        } else if (DCanvas.getInstance().IsPointerDown(this.begin_fontLeftx - this.img_fontw * 2, this.begin_fonty - (this.img_fonth >> 1) + this.img_fonth, this.img_fontw << 2, this.img_fonth)) {
                            if (this.MENU_ITEM_INDEX_STATE[this.menuItemIndex_row][this.menuItemIndex_list] == 2) {
                                DCanvas.getInstance().keyReleased(DCanvas.getInstance().GAME_KEY_SOFT_LEFT);
                            } else {
                                this.menuItemIndex_row = 1;
                                this.menuItemIndex_list = 0;
                            }
                            this.Menuindex = 2 * this.menuItemIndex_row + this.menuItemIndex_list;
                        } else if (DCanvas.getInstance().IsPointerDown(this.begin_fontLeftx - this.img_fontw * 2, this.begin_fonty - (this.img_fonth >> 1) + 2 * this.img_fonth, this.img_fontw << 2, this.img_fonth)) {
                            if (this.MENU_ITEM_INDEX_STATE[this.menuItemIndex_row][this.menuItemIndex_list] == 4) {
                                DCanvas.getInstance().keyReleased(DCanvas.getInstance().GAME_KEY_SOFT_LEFT);
                            } else {
                                this.menuItemIndex_row = (byte) 2;
                                this.menuItemIndex_list = 0;
                            }
                            this.Menuindex = 2 * this.menuItemIndex_row + this.menuItemIndex_list;
                        } else if (DCanvas.getInstance().IsPointerDown(this.begin_fontLeftx - this.img_fontw * 2, this.begin_fonty - (this.img_fonth >> 1) + 3 * this.img_fonth, this.img_fontw << 2, this.img_fonth)) {
                            if (this.MENU_ITEM_INDEX_STATE[this.menuItemIndex_row][this.menuItemIndex_list] == 6) {
                                DCanvas.getInstance().keyReleased(DCanvas.getInstance().GAME_KEY_SOFT_LEFT);
                            } else {
                                this.menuItemIndex_row = (byte) 3;
                                this.menuItemIndex_list = 0;
                            }
                            this.Menuindex = 2 * this.menuItemIndex_row + this.menuItemIndex_list;
                        } else if (DCanvas.getInstance().IsPointerDown(this.begin_fontRightx - (this.img_fontw << 1), this.begin_fonty - (this.img_fonth >> 1), this.img_fontw << 2, this.img_fonth)) {
                            if (this.MENU_ITEM_INDEX_STATE[this.menuItemIndex_row][this.menuItemIndex_list] == 1) {
                                DCanvas.getInstance().keyReleased(DCanvas.getInstance().GAME_KEY_SOFT_LEFT);
                            } else {
                                this.menuItemIndex_row = 0;
                                this.menuItemIndex_list = 1;
                            }
                            this.Menuindex = 2 * this.menuItemIndex_row + this.menuItemIndex_list;
                        } else if (DCanvas.getInstance().IsPointerDown(this.begin_fontRightx - (this.img_fontw << 1), this.begin_fonty - (this.img_fonth >> 1) + this.img_fonth, this.img_fontw << 2, this.img_fonth)) {
                            if (this.MENU_ITEM_INDEX_STATE[this.menuItemIndex_row][this.menuItemIndex_list] == 3) {
                                DCanvas.getInstance().keyReleased(DCanvas.getInstance().GAME_KEY_SOFT_LEFT);
                            } else {
                                this.menuItemIndex_row = 1;
                                this.menuItemIndex_list = 1;
                            }
                            this.Menuindex = 2 * this.menuItemIndex_row + this.menuItemIndex_list;
                        } else if (DCanvas.getInstance().IsPointerDown(this.begin_fontRightx - (this.img_fontw << 1), this.begin_fonty - (this.img_fonth >> 1) + 2 * this.img_fonth, this.img_fontw << 2, this.img_fonth)) {
                            if (this.MENU_ITEM_INDEX_STATE[this.menuItemIndex_row][this.menuItemIndex_list] == 5) {
                                DCanvas.getInstance().keyReleased(DCanvas.getInstance().GAME_KEY_SOFT_LEFT);
                            } else {
                                this.menuItemIndex_row = (byte) 2;
                                this.menuItemIndex_list = 1;
                            }
                            this.Menuindex = 2 * this.menuItemIndex_row + this.menuItemIndex_list;
                        } else if (DCanvas.getInstance().IsPointerDown(this.begin_fontRightx - (this.img_fontw << 1), this.begin_fonty - (this.img_fonth >> 1) + 3 * this.img_fonth, this.img_fontw << 2, this.img_fonth)) {
                            if (this.MENU_ITEM_INDEX_STATE[this.menuItemIndex_row][this.menuItemIndex_list] == 7) {
                                DCanvas.getInstance().keyReleased(DCanvas.getInstance().GAME_KEY_SOFT_LEFT);
                            } else {
                                this.menuItemIndex_row = (byte) 3;
                                this.menuItemIndex_list = 1;
                            }
                            this.Menuindex = 2 * this.menuItemIndex_row + this.menuItemIndex_list;
                        }
                        if (!DCanvas.getInstance().IsPointerRelease(0, 0, Macro.SCREEN_WIDTH, Macro.SCREEN_HEIGHT)) {
                            break;
                        }
                        this.Menuindex = 0;
                        break;
                    }
                    case 3: {
                        n2 = 0;
                        while (n2 < this.ServiseLisetLength) {
                            if (DCanvas.getInstance().IsPointerDown(this.gridtable.getCell(n2 + 1, 1).getCellX(), this.gridtable.getCell(n2 + 1, 1).getCellY(), this.gridtable.getCell(n2 + 1, 1).getCellW(), this.gridtable.getCell(n2 + 1, 1).getCellH())) {
                                if (this.shtLandMove == n2) {
                                    DCanvas.getInstance().keyReleased(DCanvas.getInstance().GAME_KEY_SOFT_LEFT);
                                } else {
                                    this.shtLandMove = (short) n2;
                                }
                            }
                            ++n2;
                        }
                        break;
                    }
                    case 4:
                    case 9: {
                        this.addTempString();
                        if (DCanvas.getInstance().IsPointerDown(this._tempaccountx, this._tempaccounty, this._tempw, this._tempH)) {
                            if (this.shtLandRoll == 0) {
                                DCanvas.getInstance().keyReleased(DCanvas.getInstance().GAME_KEY_SOFT_LEFT);
                            } else {
                                this.shtLandRoll = 0;
                            }
                        } else if (DCanvas.getInstance().IsPointerDown(this._temppasswordx, this._temppasswordy, this._tempw, this._tempH)) {
                            if (this.shtLandRoll == 1) {
                                DCanvas.getInstance().keyReleased(DCanvas.getInstance().GAME_KEY_SOFT_LEFT);
                            } else {
                                this.shtLandRoll = 1;
                            }
                        } else if (DCanvas.getInstance().IsPointerDown(this.sign_X, this.font_Y, Macro.FONTW, Macro.FONTHEIGHT)) {
                            this.shtLandRoll = (short) 2;
                            DCanvas.getInstance().keyReleased(DCanvas.getInstance().GAME_KEY_SOFT_LEFT);
                        }
                        if (this.landState != 4) {
                            break;
                        }
                        n2 = 1;
                        while (n2 <= 4) {
                            if (DCanvas.getInstance().IsPointerDown(this.choose_frame_tab.getCell(n2, 1).getCellX(), this.choose_frame_tab.getCell(n2, 1).getCellY(), this.choose_frame_tab.getCell(n2, 1).getCellW(), this.choose_frame_tab.getCell(n2, 1).getCellH())) {
                                n = n2 + 2;
                                if (n == this.shtLandRoll) {
                                    DCanvas.getInstance().keyReleased(DCanvas.getInstance().GAME_KEY_SOFT_LEFT);
                                } else {
                                    this.shtLandRoll = (short) n;
                                }
                            }
                            ++n2;
                        }
                        break;
                    }
                    case 5: {
                        n2 = 1;
                        while (n2 <= 3) {
                            if (DCanvas.getInstance().IsPointerDown(this.choose_frame_tab.getCell(n2, 1).getCellX(), this.choose_frame_tab.getCell(n2, 1).getCellY(), this.choose_frame_tab.getCell(n2, 1).getCellW(), this.choose_frame_tab.getCell(n2, 1).getCellH())) {
                                n = n2 - 1;
                                if (n == this.shtLandRoll) {
                                    DCanvas.getInstance().keyReleased(DCanvas.getInstance().GAME_KEY_SOFT_LEFT);
                                } else {
                                    this.shtLandRoll = (short) n;
                                }
                            }
                            ++n2;
                        }
                        break;
                    }
                    case 6:
                    case 13: {
                        this.scrolltext.Point();
                        break;
                    }
                    case 10: {
                        n2 = 2;
                        while (n2 < 5) {
                            if (DCanvas.getInstance().IsPointerDown(this.gridTable_row2_col1_Table.getCell(1, n2).getCellX(), this.gridTable_row2_col1_Table.getCell(1, n2).getCellY(), this.gridTable_row2_col1_Table.getCell(1, n2).getCellW(), this.gridTable_row2_col1_Table.getCell(1, n2).getCellH())) {
                                n = (byte) (n2 - 2);
                                this.setAcrossInfo((short) n);
                            }
                            ++n2;
                        }
                        if (!DCanvas.getInstance().IsPointerDown((Macro.SCREEN_WIDTH - 55) / 2, Macro.SCREEN_HEIGHT - 24, 55, 24) || this.bytButtonType[1] == -1 || !this.hPlayerList.containsKey(new Integer(this.shtLandMove))) {
                            break;
                        }
                        DCanvas.getInstance().keyReleased(DCanvas.getInstance().GAME_KEY_C);
                        break;
                    }
                    case 11: {
                        if (DCanvas.getInstance().IsPointerDown(this.gridtable.getCell(2, 1).getCellX(), this.gridtable.getCell(2, 1).getCellY(), this.gridtable_width, this.gridtable_height)) {
                            this.bytReelMove = 0;
                            DCanvas.getInstance().keyReleased(DCanvas.getInstance().GAME_KEY_OK);
                        }
                        n2 = 0;
                        while (n2 < 3) {
                            if (DCanvas.getInstance().IsPointerDown(this.gridtable.getCell(this.gridtable_array[n2], 1).getCellX(), this.gridtable.getCell(this.gridtable_array[n2], 1).getCellY(), this.gridtable_width >> 2, this.gridtable_height)) {
                                this.bytReelMove = (byte) (n2 + 1);
                                DCanvas.getInstance().keyReleased(DCanvas.getInstance().GAME_KEY_LEFT);
                            }
                            ++n2;
                        }
                        n2 = 0;
                        while (n2 < 3) {
                            if (DCanvas.getInstance().IsPointerDown(this.gridtable.getCell(this.gridtable_array[n2], 1).getCellX() + this.gridtable_width - (this.gridtable_width >> 2), this.gridtable.getCell(this.gridtable_array[n2], 1).getCellY(), this.gridtable_width >> 2, this.gridtable_height)) {
                                this.bytReelMove = (byte) (n2 + 1);
                                DCanvas.getInstance().keyReleased(DCanvas.getInstance().GAME_KEY_RIGHT);
                            }
                            ++n2;
                        }
                        n2 = 0;
                        while (n2 < 3) {
                            if (DCanvas.getInstance().IsPointerDown(this.gridtable.getCell(this.gridtable_array[n2], 1).getCellX() + (this.gridtable_width >> 2), this.gridtable.getCell(this.gridtable_array[n2], 1).getCellY(), this.gridtable_width >> 1, this.gridtable_height)) {
                                this.bytReelMove = (byte) (n2 + 1);
                            }
                            ++n2;
                        }
                        this.updataInfo();
                    }
                }
            }
        }
        if (DCanvas.getInstance().blnSpoolr) {
            n2 = ScrollText.arrowhead_width;
            n = ScrollText.arrowhead_height;
            short s = DCanvas.getInstance().shtSpoolrX;
            short s2 = DCanvas.getInstance().shtSpoolrSY;
            int n3 = DCanvas.getInstance().shtSpoolrEY - DCanvas.getInstance().shtSpoolrSY;
            int n4 = n2 * 3;
            if (DCanvas.getInstance().IsPointerDragged(s - n2, s2 - n, n4, (n3 >> 1) + n)) {
                DCanvas.getInstance().keyReleased(DCanvas.getInstance().GAME_KEY_UP);
            } else if (DCanvas.getInstance().IsPointerDragged(s - n2, s2 + (n3 >> 1) - n, n4, (n3 >> 1) + n)) {
                DCanvas.getInstance().keyReleased(DCanvas.getInstance().GAME_KEY_DOWN);
            } else if (DCanvas.getInstance().IsPointerDown(s - n2, s2 - n, n4, (n3 >> 1) + n)) {
                DCanvas.getInstance().keyReleased(DCanvas.getInstance().GAME_KEY_UP);
            } else if (DCanvas.getInstance().IsPointerDown(s - n2, s2 + (n3 >> 1) - n, n4, (n3 >> 1) + n)) {
                DCanvas.getInstance().keyReleased(DCanvas.getInstance().GAME_KEY_DOWN);
            }
        }
        DCanvas.getInstance().blnPointerPressed = false;
    }

    private void set(int n, byte by) {
        this.bytMaxH = by;
        if (this.strInputString.length() >= by) {
            this.initInput();
            return;
        }
        String[] stringArray = new String[]{""};
        switch (n) {
            case 0: {
                if (this.strTempInputString.length() > 0) {
                    this.add(this.strTempInputString, by);
                }
                this.add("0", by);
                return;
            }
            case 1: {
                if (this.strTempInputString.length() > 0) {
                    this.add(this.strTempInputString, by);
                }
                this.add("1", by);
                return;
            }
            case 2: {
                stringArray = new String[]{"2", "a", "b", "c"};
                break;
            }
            case 3: {
                stringArray = new String[]{"3", "d", "e", "f"};
                break;
            }
            case 4: {
                stringArray = new String[]{"4", "g", "h", "i"};
                break;
            }
            case 5: {
                stringArray = new String[]{"5", "j", "k", "l"};
                break;
            }
            case 6: {
                stringArray = new String[]{"6", "m", "n", "o"};
                break;
            }
            case 7: {
                stringArray = new String[]{"7", "p", "q", "r", "s"};
                break;
            }
            case 8: {
                stringArray = new String[]{"8", "t", "u", "v"};
                break;
            }
            case 9: {
                stringArray = new String[]{"9", "w", "x", "y", "z"};
            }
        }
        if (this.intTimer > 0) {
            if (this.intKeyCode != n) {
                this.bytChooseIndex = 0;
                this.add(this.strTempInputString, by);
            } else {
                this.bytChooseIndex = (byte) (this.bytChooseIndex + 1);
                if (this.bytChooseIndex > stringArray.length - 1) {
                    this.bytChooseIndex = 0;
                }
            }
        } else {
            this.bytChooseIndex = 0;
        }
        if (this.strInputString.length() < by) {
            this.intKeyCode = n;
            this.strTempInputString = stringArray[this.bytChooseIndex];
            this.blnIsWord = true;
            this.intTimer = this.intTalkingTime;
        } else {
            this.initInput();
        }
    }

    private void add(String string, byte by) {
        if (this.strInputString.length() < by) {
            this.strInputString = String.valueOf(this.strInputString) + string;
        }
        this.initInput();
    }

    private void addTempString() {
        if (this.strTempInputString.length() > 0) {
            if (this.shtLandRoll == 0) {
                this.add(this.strTempInputString, (byte) 11);
                this.strLandAccounts = this.strInputString;
            } else if (this.shtLandRoll == 1) {
                this.add(this.strTempInputString, (byte) 11);
                this.strLandPassword = this.strInputString;
            } else {
                this.add(this.strTempInputString, (byte) 11);
                this.strOldLandPassword = this.strInputString;
            }
        }
    }

    private void initInput() {
        this.intTimer = 0;
        this.intKeyCode = 0;
        this.blnIsWord = false;
        this.strTempInputString = "";
    }

    private void addInputNumber(int n) {
        if (this.shtLandRoll == 0) {
            this.strInputString = this.strLandAccounts;
            this.set(n, (byte) 11);
            this.strLandAccounts = this.strInputString;
        } else if (this.shtLandRoll == 1) {
            this.strInputString = this.strLandPassword;
            this.set(n, (byte) 11);
            this.strLandPassword = this.strInputString;
        } else {
            this.strInputString = this.strOldLandPassword;
            this.set(n, (byte) 11);
            this.strOldLandPassword = this.strInputString;
        }
    }

    private void delInputNumber() {
        if (this.shtLandRoll == 0 && this.strLandAccounts.length() > 0) {
            this.strLandAccounts = this.strLandAccounts.substring(0, this.strLandAccounts.length() - 1);
        } else if (this.shtLandRoll == 1 && this.strLandPassword.length() > 0) {
            this.strLandPassword = this.strLandPassword.substring(0, this.strLandPassword.length() - 1);
        } else if (this.shtLandRoll == 2 && this.strOldLandPassword.length() > 0) {
            this.strOldLandPassword = this.strOldLandPassword.substring(0, this.strOldLandPassword.length() - 1);
        }
    }

    public void newRoleList(ORPlayer oRPlayer, int n, String string, byte by, short s, byte by2, byte by3, byte by4) {
        oRPlayer.intUserId = n;
        oRPlayer.bytIsMale = by2;
        oRPlayer.strNickName = string;
        oRPlayer.bytOccupation = by;
        oRPlayer.shtLevel = s;
        oRPlayer.bytCountry = by4;
    }

    public void initRoleList() {
        this.setLandRole();
        this.hPlayerList = new Hashtable(3);
    }

    private void drawGameBack(Graphics graphics) {
        LayoutStyle.getInstance().drawFullBackGround(13866046);
    }

    private void initRegister() {
        this.strLandAccounts = "";
        this.strLandPassword = "";
        this.initPublic();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Vui lòng nhập tài khoản và mật khẩu của bạn \n tài khoản và mật khẩu chỉ có thể là 6-11 chữ cái và số");
        this.strLandString = StringManager.wenZi(stringBuffer.toString(), this.up_frame_W);
        this.setButton((byte) 0, (byte) -1, (byte) 1);
    }

    private void initLand() {
        Rms.loadID();
        if (Rms.blnIsSava) {
            if (Rms.strRmsName != null) {
                this.strLandAccounts = Rms.strRmsName;
                this.strLandPassword = Rms.strRmsPassword;
                this.blnIsSaveLandInfo = Rms.blnIsSaveLandInfo;
                this.AutoLogin = Rms.blnIsSava;
            } else {
                this.strLandAccounts = "";
                this.strLandPassword = "";
                this.blnIsSaveLandInfo = false;
                this.AutoLogin = true;
            }
        } else {
            if (Rms.strRmsName != null) {
                this.strLandAccounts = Rms.strRmsName;
                this.strLandPassword = "";
            } else {
                this.strLandAccounts = "";
                this.strLandPassword = "";
            }
            this.blnIsSaveLandInfo = false;
            this.AutoLogin = true;
        }
        this.initPublic();
        this.LoadAccoundPassword();
        this.shtLandRoll = (short) 3;
        this.setButton((byte) 0, (byte) -1, (byte) 1);
    }

    public void LoadAccoundPassword() {
        Rms.loadID();
        if (Rms.strRmsName != null) {
            this.strLandAccounts = Rms.strRmsName;
            this.strLandPassword = Rms.strRmsPassword;
        }
    }

    private void initPublic() {
        if (this.imgNumber == null) {
            this.imgNumber = ImageManager.CreateImage("accountNum", "loading");
        }
        if (this.sign == null) {
            this.sign = ImageManager.CreateImage("gou", "loading");
        }
        if (this.account == null) {
            this.account = ImageManager.loadSpriteById(1, ImageManager.EncodespriteId(this.account_resource, this.account_resource), this.account_resource, this.account_resource, "loading");
        }
        short s = 12;
        short s2 = 44;
        short s3 = (short) (Macro.SCREEN_WIDTH - (s << 1));
        short s4 = 58;
        this.gridtable = new GridTable(s, s2, s3, s4, (short) 2, (short) 2, IDefines.ACCOUNT_GRIDTABLE_ROW_PERCENT, IDefines.ACCOUNT_GRIDTABLE_LIST_PERCENT);
        this.account_frame_width = 56;
        this.account_frame_height = 22;
        this.font_Y = this.gridtable.getCell(1, 1).getCellY() + s4 + 4;
        this.sign_X = this.gridtable.getCell(1, 1).getCenterX() - (this.account_frame_width >> 1) + (this.account_frame_width >> 1);
        this.up_frame_X = (short) 6;
        this.up_frame_W = (short) (Macro.SCREEN_WIDTH - (this.up_frame_X << 1));
        this.up_frame_H = (short) (this.font_Y + Macro.FONTHEIGHT + 4 - 38);
        this.down_frame_Y = (short) (38 + this.up_frame_H + 4);
        this.down_frame_H = (short) (Macro.SCREEN_HEIGHT - this.down_frame_Y - 31);
        short s5 = s;
        short s6 = (short) (this.down_frame_Y + 4);
        short s7 = (short) (Macro.SCREEN_WIDTH - (s5 << 1));
        short s8 = (short) (this.down_frame_H - 8);
        if (s8 < 20) {
            s8 = 20;
        }
        this.choose_frame_tab = new GridTable(s5, s6, s7, s8, (short) 4, (short) 1, IDefines.ACCOUNT_CHOOSE_ROW_PERCENT, new short[]{100});
        this.font_array = new short[]{2, 3, 4, 5};
        this.choose_frame_index_X = s5;
        this.choose_frame_index_Y = (short) (this.choose_frame_tab.getCell(1, 1).getCenterY() - 10);
        this.choose_frame_index_W = s7;
        this.choose_frame_index_H = (short) 20;
        this.choose_space = (short) (this.choose_frame_tab.getCell(2, 1).getCenterY() - this.choose_frame_tab.getCell(1, 1).getCenterY());
        short[] sArray = new short[7];
        sArray[4] = 1;
        sArray[5] = 2;
        sArray[6] = 3;
        this.choose_index = sArray;
        this.input_text_X = this.gridtable.getCell(1, 2).getCellX() + 2;
        this.input_account_text_Y = this.gridtable.getCell(1, 2).getCenterY() - 7 + 1;
        this.input_password_text_Y = this.gridtable.getCell(2, 2).getCenterY() - 7 + 1;
        this._tempaccountx = this.gridtable.getCell(1, 2).getCellX();
        this._tempaccounty = this.gridtable.getCell(1, 2).getCellY();
        this._temppasswordx = this.gridtable.getCell(2, 2).getCellX();
        this._temppasswordy = this.gridtable.getCell(2, 2).getCellY();
        this._tempw = this.gridtable.getCell(1, 2).getCellW();
        this._tempH = this.gridtable.getCell(1, 2).getCellH();
    }

    public void cleanAccount() {
        this.sign = null;
    }

    private void drawAccountsLand(Graphics graphics) {
        int n;
        String string;
        String string2;
        String string3;
        this.drawGameBack(graphics);
        this.drawTitleBox(graphics, this.landState == 4 ? "Đăng nhập" : "Đăng ký");
        DrawBase.drawBox(this.up_frame_X, 38, this.up_frame_W, this.up_frame_H, new int[]{9263661, 14995858}, true);
        int n2 = 0;
        while (n2 < 2) {
            DrawBase.drawBox(this.gridtable.getCell(n2 + 1, 1).getCenterX() - (this.account_frame_width >> 1), this.gridtable.getCell(n2 + 1, 1).getCenterY() - (this.account_frame_height >> 1), this.account_frame_width, this.account_frame_height, n2 == 0 ? IDefines.ACCOUNT_ACCOUNT_COLOUR : IDefines.ACCOUNT_PASSWORD_COLOUR, true);
            DrawBase.drawBox(this.gridtable.getCell(n2 + 1, 2).getCellX(), this.gridtable.getCell(n2 + 1, 2).getCellY() + 4, this.gridtable.getCell(n2 + 1, 2).getCellW(), this.gridtable.getCell(n2 + 1, 2).getCellH() - 8, IDefines.ACCOUNT_FRAME_COLOUR, true);
            this.account.drawAnimationFrame(graphics, n2, 0, this.gridtable.getCell(n2 + 1, 1).getCenterX(), this.gridtable.getCell(n2 + 1, 1).getCenterY());
            ++n2;
        }
        if (Param.getInstance().Forma_ccount == null || Param.getInstance().Forma_password == null) {
            string3 = LandUI.getInstance().strLandAccounts;
            string2 = LandUI.getInstance().strLandPassword;
        } else {
            string3 = Param.getInstance().Forma_ccount;
            string2 = Param.getInstance().Forma_password;
        }
        String string4 = this.landState == 4 ? string3 : Param.getInstance().accound;
        String string5 = string = this.landState == 4 ? string2 : Param.getInstance().password;
        if (string4 != null && !string4.equals("")) {
            this.drawInputNum(graphics, string4, this.input_text_X, this.input_account_text_Y);
        }
        if (string != null && !string.equals("")) {
            this.drawInputNum(graphics, string, this.input_text_X, this.input_password_text_Y);
        }
        DrawBase.drawBox(this.up_frame_X, this.down_frame_Y, this.up_frame_W, this.down_frame_H, new int[]{9263661, 14995858, 14995858, 15768897, 16314576}, true);
        if (this.landState == 4) {
            DrawBase.drawString("Lưu mật khẩu", Macro.SCREEN_WIDTH >> 1, this.font_Y, 9263661, 17);
            int[] nArray = new int[2];
            nArray[0] = this.shtLandRoll == 2 ? 0xF21111 : 0xFFFFFF;
            DrawBase.drawBox(this.sign_X, this.font_Y, 15, 15, nArray, true);
            if (this.AutoLogin && this.sign != null) {
                DrawBase.drawImage(this.sign, this.sign_X + 2, this.font_Y + 2, 20);
            }
            if (this.shtLandRoll > 2) {
                LayoutStyle.getInstance().drawSelectBox(this.choose_frame_index_X, this.choose_frame_index_Y + this.choose_space * this.choose_index[this.shtLandRoll], this.choose_frame_index_W, this.choose_frame_index_H);
            }
            n = 1;
            while (n <= 4) {
                this.account.drawAnimationFrame(graphics, this.font_array[n - 1], 0, this.choose_frame_tab.getCell(n, 1).getCenterX(), this.choose_frame_tab.getCell(n, 1).getCenterY());
                ++n;
            }
        } else if (this.landState == 9) {
            StringManager.drawWordMove(graphics, this.strLandString, this.up_frame_X + 4, this.down_frame_Y + 4, this.down_frame_H - (Macro.FONTHEIGHT >> 1), 9263661, 20);
        }
        if (this.shtLandRoll == 0 || this.shtLandRoll == 1) {
            n = 0;
            while (n < 2) {
                if (this.shtLandRoll == n) {
                    DrawBase.drawBox(this.gridtable.getCell(n + 1, 2).getCellX(), this.gridtable.getCell(n + 1, 2).getCellY() + 4, this.gridtable.getCell(n + 1, 2).getCellW(), this.gridtable.getCell(n + 1, 2).getCellH() - 8, new int[]{0x3C3CF3}, false);
                }
                ++n;
            }
        }
    }

    private void updateRegister() {
        switch (DCanvas.getInstance().getKeyRelease()) {
            case 65536:
            case 262144: {
                if (DCanvas.getInstance().getCanvasState()) {
                    DCanvas.getInstance().setCanvasState(false);
                    FormDes.getInstance().setLandState("Đăng ký", (byte) 1);
                    break;
                }
                DCanvas.getInstance().setCanvasState(true);
                break;
            }
            case 131072: {
                if (DCanvas.getInstance().getCanvasState()) {
                    if (this.twodialog == null) {
                        this.setState((byte) 2);
                        break;
                    }
                    this.twodialog = null;
                    this.setButton((byte) 0, (byte) -1, (byte) 1);
                    break;
                }
                DCanvas.getInstance().setCanvasState(true);
                break;
            }
            case 4096: {
                this.addTempString();
                this.shtLandRoll = (short) (this.shtLandRoll - 1);
                if (this.shtLandRoll < 0) {
                    this.shtLandRoll = 1;
                }
                this.initInput();
                break;
            }
            case 8192: {
                this.addTempString();
                this.shtLandRoll = (short) (this.shtLandRoll + 1);
                if (this.shtLandRoll > 1) {
                    this.shtLandRoll = 0;
                }
                this.initInput();
                break;
            }
            case 2048:
            case 524288: {
                this.delInputNumber();
                this.initInput();
            }
        }
        this.keyOther();
    }

    private void updateLand() {
        switch (DCanvas.getInstance().getKeyRelease()) {
            case 65536:
            case 262144: {
                if (DCanvas.getInstance().getCanvasState()) {
                    this.addTempString();
                    switch (this.shtLandRoll) {
                        case 0:
                        case 1: {
                            DCanvas.getInstance().setCanvasState(false);
                            FormDes.getInstance().setLandState("Đang vào game", (byte) 0);
                            break;
                        }
                        case 3: {
                            if (this.strLandAccounts.length() >= 6 && this.strLandAccounts.length() <= 11 && this.strLandPassword.length() >= 6 && this.strLandPassword.length() <= 11) {
                                this.movesesDebarkation();
                                break;
                            }
                            this.setTwoDialog("Độ dài của tài khoản hoặc mật khẩu phải từ 6-11", (byte) -1, (byte) 1);
                            break;
                        }
                        case 2: {
                            if (this.strLandPassword.length() >= 6 && this.strLandPassword.length() <= 11) {
                                this.blnIsSaveLandInfo = true;
                                this.AutoLogin = !this.AutoLogin;
                                break;
                            }
                            this.setTwoDialog("Mật khẩu ít hơn 6-11 bit", (byte) -1, (byte) 1);
                            break;
                        }
                        case 4: {
                            DCanvas.getInstance().setCanvasState(false);
                            FormDes.getInstance().setLandState("Thay đổi mật khẩu", (byte) 4);
                            break;
                        }
                        case 5: {
                            this.setState((byte) 9);
                            break;
                        }
                        case 6: {
                            GameControl.getInstance().CreateState((byte) 7);
                            DialogUI.getInstance().setDialog((byte) 104, "Bạn đã sửa chữa các trò chơi và thoát khỏi trò chơi?", (byte) 2);
                        }
                    }
                    break;
                }
                DCanvas.getInstance().setCanvasState(true);
                break;
            }
            case 131072: {
                if (DCanvas.getInstance().getCanvasState()) {
                    if (this.twodialog == null) {
                        this.setState((byte) 2);
                        break;
                    }
                    this.twodialog = null;
                    this.setButton((byte) 0, (byte) -1, (byte) 1);
                    break;
                }
                DCanvas.getInstance().setCanvasState(true);
                break;
            }
            case 4096: {
                this.addTempString();
                if (this.shtLandRoll >= 0) {
                    this.shtLandRoll = (short) (this.shtLandRoll - 1);
                    if (this.shtLandRoll < 0) {
                        this.shtLandRoll = (short) 6;
                    }
                }
                this.initInput();
                break;
            }
            case 8192: {
                this.addTempString();
                if (this.shtLandRoll < 7) {
                    this.shtLandRoll = (short) (this.shtLandRoll + 1);
                    if (this.shtLandRoll > 6) {
                        this.shtLandRoll = 0;
                    }
                }
                this.initInput();
                break;
            }
            case 16384: {
                if (this.shtLandRoll <= 3) {
                    break;
                }
                this.shtLandRoll = (short) (this.shtLandRoll - 1);
                break;
            }
            case 32768: {
                if (this.shtLandRoll <= 2) {
                    break;
                }
                this.shtLandRoll = (short) (this.shtLandRoll + 1);
                if (this.shtLandRoll <= 8) {
                    break;
                }
                this.shtLandRoll = (short) 8;
                break;
            }
            case 2048:
            case 524288: {
                this.delInputNumber();
                this.initInput();
            }
        }
    }

    private void initAccountSafe() {
        this.initPublic();
        this.font_array = new short[]{6, 7, 3};
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Khi tài khoản của bạn bị ràng buộc với điện thoại, bạn có thể sử dụng tính năng khôi phục mật khẩu để truy xuất tài khoản và mật khẩu của mình.");
        this.strLandString = StringManager.wenZi(stringBuffer.toString(), this.up_frame_W - Macro.FONTW);
        this.shtLandRoll = 0;
        this.setButton((byte) 0, (byte) -1, (byte) 1);
    }

    private void drawAccountSafe(Graphics graphics) {
        this.drawGameBack(graphics);
        this.drawTitleBox(graphics, "An toàn tài khoản");
        DrawBase.drawBox(this.up_frame_X, 38, this.up_frame_W, this.up_frame_H, new int[]{9263661, 14995858}, true);
        StringManager.drawWordMove(graphics, this.strLandString, this.up_frame_X + 4, 38 + Macro.FONTHEIGHT, this.up_frame_H - (Macro.FONTHEIGHT >> 1), 9263661, 20);
        DrawBase.drawBox(this.up_frame_X, this.down_frame_Y, this.up_frame_W, this.down_frame_H, new int[]{9263661, 14995858, 14995858, 15768897, 16314576}, true);
        LayoutStyle.getInstance().drawSelectBox(this.choose_frame_index_X, this.choose_frame_index_Y + this.choose_space * this.shtLandRoll, this.choose_frame_index_W, this.choose_frame_index_H);
        int n = 1;
        while (n <= 3) {
            this.account.drawAnimationFrame(graphics, this.font_array[n - 1], 0, this.choose_frame_tab.getCell(n, 1).getCenterX(), this.choose_frame_tab.getCell(n, 1).getCenterY());
            ++n;
        }
    }

    private void updateAccountSafe() {
        block0:
        switch (DCanvas.getInstance().getKeyRelease()) {
            case 65536:
            case 262144: {
                if (DCanvas.getInstance().getCanvasState()) {
                    switch (this.shtLandRoll) {
                        case 0: {
                            DCanvas.getInstance().setCanvasState(false);
                            FormDes.getInstance().setLandState("Điện thoại ràng buộc", (byte) 2);
                            break block0;
                        }
                        case 1: {
                            DCanvas.getInstance().setCanvasState(false);
                            FormDes.getInstance().setLandState("Truy xuất mật khẩu", (byte) 3);
                            break block0;
                        }
                        case 2: {
                            DCanvas.getInstance().setCanvasState(false);
                            FormDes.getInstance().setLandState("Thay đổi mật khẩu", (byte) 4);
                            break block0;
                        }
                    }
                    break;
                }
                DCanvas.getInstance().setCanvasState(true);
                break;
            }
            case 131072: {
                if (DCanvas.getInstance().getCanvasState()) {
                    this.setState((byte) 2);
                    break;
                }
                DCanvas.getInstance().setCanvasState(true);
                break;
            }
            case 4096: {
                this.shtLandRoll = (short) (this.shtLandRoll - 1);
                if (this.shtLandRoll >= 0) {
                    break;
                }
                this.shtLandRoll = (short) 2;
                break;
            }
            case 8192: {
                this.shtLandRoll = (short) (this.shtLandRoll + 1);
                if (this.shtLandRoll <= 2) {
                    break;
                }
                this.shtLandRoll = 0;
            }
        }
    }

    private void initServerList() {
        Macro.ServerStateFont = IDefines.SERVER_UI_SERVER_SATATE;
        this.ServiseLisetLength = (short) Macro.strsServerList.length;
        short s = 28;
        short s2 = 50;
        short s3 = (short) (Macro.SCREEN_WIDTH - (s << 1));
        this.listHeight = (short) (this.ServiseLisetLength * (Macro.FONTHEIGHT + 6));
        this.ServiseLiset_Percent = new short[this.ServiseLisetLength];
        short s4 = 0;
        while (s4 < this.ServiseLisetLength) {
            this.ServiseLiset_Percent[s4] = (short) (100 / this.ServiseLisetLength);
            s4 = (short) (s4 + 1);
        }
        this.gridtable = new GridTable(s, s2, s3, this.listHeight, this.ServiseLisetLength, (short) 1, this.ServiseLiset_Percent, new short[]{100});
        this.shtMenuMoveLength = this.ServiseLisetLength;
        this.shtLandRoll = 0;
        this.bytMaxH = (byte) ((Macro.SCREEN_HEIGHT - 38 - 12 - 31) / (Macro.FONTHEIGHT + 6));
        this.setButton((byte) 0, (byte) -1, (byte) 1);
    }

    private void drawServerList(Graphics graphics) {
        DCanvas.getInstance().drawTileTextBG(graphics, "Chọn máy chủ");
        int n = 0;
        int n2 = 0;
        DrawBase.setClip(12, 44, Macro.SCREEN_WIDTH - 24, Macro.SCREEN_HEIGHT - 38 - 31 - 12);
        int n3 = this.bytMaxH;
        short s = this.shtLandRoll;
        if (n3 > this.shtMenuMoveLength) {
            n3 = this.shtMenuMoveLength;
            s = 0;
        }
        int n4 = 0;
        while (n4 < n3) {
            DrawBase.drawBox(this.gridtable.getCell(n4 + 1, 1).getCellX(), this.gridtable.getCell(n4 + 1, 1).getCellY(), this.gridtable.getCell(n4 + 1, 1).getCellW(), this.gridtable.getCell(n4 + 1, 1).getCellH() - 2, new int[]{9263661, n4 == this.shtLandMove ? 16753705 : 16642234}, true);
            if (Macro.blnServerList[n4 + s]) {
                n = 0x4C6644;
                n2 = 4642587;
            } else {
                n = 0x7E7878;
                n2 = 0xC7C7C7;
            }
            StringManager.drawShadowWord(graphics, Macro.strsServerList[n4 + s], this.gridtable.getCell(n4 + 1, 1).getCellX(), this.gridtable.getCell(n4 + 1, 1).getCellY(), n2, n, 20);
            if (Macro.ServerState[n4 + s] == 1 && Macro.blnServerList[n4 + s]) {
                this.ServiseStateFontColour = IDefines.SERVER_UI_SERVER_SATATE_FONT_COLOUR[0];
                DrawBase.drawString(Macro.ServerStateFont[0], this.gridtable.getCell(n4 + 1, 1).getCellX() + this.gridtable.getCell(n4 + 1, 1).getCellW(), this.gridtable.getCell(n4 + 1, 1).getCellY(), this.ServiseStateFontColour, 24);
            } else if (Macro.ServerState[n4 + s] == 2 && Macro.blnServerList[n4 + s]) {
                this.ServiseStateFontColour = IDefines.SERVER_UI_SERVER_SATATE_FONT_COLOUR[1];
                DrawBase.drawString(Macro.ServerStateFont[1], this.gridtable.getCell(n4 + 1, 1).getCellX() + this.gridtable.getCell(n4 + 1, 1).getCellW(), this.gridtable.getCell(n4 + 1, 1).getCellY(), this.ServiseStateFontColour, 24);
            } else if (Macro.ServerState[n4 + s] == 3 && Macro.blnServerList[n4 + s]) {
                this.ServiseStateFontColour = IDefines.SERVER_UI_SERVER_SATATE_FONT_COLOUR[2];
                DrawBase.drawString(Macro.ServerStateFont[2], this.gridtable.getCell(n4 + 1, 1).getCellX() + this.gridtable.getCell(n4 + 1, 1).getCellW(), this.gridtable.getCell(n4 + 1, 1).getCellY(), this.ServiseStateFontColour, 24);
            }
            ++n4;
        }
        n4 = 12 + Macro.SCREEN_WIDTH - 24 - 8;
        int n5 = 44;
        int n6 = Macro.SCREEN_HEIGHT - 38 - 31 - 12;
        DCanvas.getInstance().setOptionSpoolr(graphics, n4, n5, n5 + n6, 1, this.shtMenuMoveLength - this.bytMaxH + 1, this.shtLandRoll, false);
        DrawBase.setClip(0, 0, Macro.SCREEN_WIDTH, Macro.SCREEN_HEIGHT);
    }

    private void updateServerList() {
        switch (DCanvas.getInstance().getKeyRelease()) {
            case 65536:
            case 262144: {
                if (Macro.blnServerList[this.shtLandMove + this.shtLandRoll]) {
                    DCanvas.getInstance().setNetLoad(true);
                    this.setTwoDialog("Đăng nhập vào máy chủ", (byte) -1, (byte) -1);
                    if (Macro.bytsServerPalce == null) {
                        Macro.bytsServerPalce = new byte[2];
                    }
                    Macro.bytsServerPalce[0] = (byte) this.shtLandMove;
                    Macro.bytsServerPalce[1] = (byte) this.shtLandRoll;
                    Macro.RELOAD_Server_ID = Macro.shtsServerList[this.shtLandMove + this.shtLandRoll];
                    Rms.saveServiseID(Macro.RELOAD_Server_ID);
                    NetSend.getInstance().sendSelectServer(Macro.shtsServerList[this.shtLandMove + this.shtLandRoll]);
                    break;
                }
                this.setTwoDialog("Máy chủ này hiện đang bị đóng", (byte) -1, (byte) 1);
                this.setButton((byte) 0, (byte) -1, (byte) 1);
                break;
            }
            case 131072: {
                if (this.twodialog == null) {
                    this.setState((byte) 2);
                    GameControl.getInstance().stopNet();
                    break;
                }
                this.twodialog = null;
                break;
            }
            case 4096: {
                if (this.shtLandMove > 0) {
                    this.shtLandMove = (short) (this.shtLandMove - 1);
                    break;
                }
                if (this.shtLandRoll <= 0) {
                    break;
                }
                this.shtLandRoll = (short) (this.shtLandRoll - 1);
                break;
            }
            case 8192: {
                if (this.shtLandMove < (Macro.blnServerList.length > this.bytMaxH ? this.bytMaxH : Macro.blnServerList.length) - 1) {
                    this.shtLandMove = (short) (this.shtLandMove + 1);
                    break;
                }
                if (this.shtLandRoll >= this.ServiseLisetLength - this.bytMaxH) {
                    break;
                }
                this.shtLandRoll = (short) (this.shtLandRoll + 1);
            }
        }
    }

    private void drawInput(Graphics graphics, String string, int n, int n2, int n3, int n4) {
        this.drawInputNum(graphics, string, n + 2, n2 + 4);
    }

    private void drawInputNum(Graphics graphics, String string, int n, int n2) {
        StringBuffer stringBuffer = new StringBuffer(string);
        int n3 = -1;
        int n4 = 0;
        while (n4 < stringBuffer.length()) {
            n3 = stringBuffer.charAt(n4) - 48;
            if (n3 >= 49) {
                n3 -= 39;
            }
            DrawBase.drawRegion(this.imgNumber, n, n2, n3 * 8, 0, 8, 15, 0, 20);
            n += 9;
            ++n4;
        }
    }

    public void updataInfo() {
        if (orplayerCreat != null) {
            LandUI.orplayerCreat.bytCountry = (byte) (this.bytCountryIndex + 1);
            LandUI.orplayerCreat.bytIsMale = (byte) (this.bytSexIndex + 1);
            LandUI.orplayerCreat.bytOccupation = (byte) (this.bytOccupation + 1);
            DCanvas.getInstance().setCreateRole(orplayerCreat);
            DCanvas.getInstance().setFoundationRole(orplayerCreat);
            orplayerCreat.setRoleAnimation(true);
        }
        String string = this.OCCUPATION = this.bytCountryIndex == 0 ? ORole.getOccName((byte) (this.bytOccupation + 1)) : ORole.getOccName((byte) (this.bytOccupation + 1));
        if (this.bytOccupation == 1) {
            this.OCCUPATIONTwo = this.bytCountryIndex == 0 ? ORole.getOccName(this.bytOccupation) : ORole.getOccName((byte) (this.bytOccupation + 1));
        }
        this.STRING_CUE_LONGKUANG = "LỰC SĨ - được mệnh danh là anh hùng bất bại. Sức mạnh tiềm tàng cùng lòng quả cảm của mình, Lực Sĩ luôn khiến đối phương phải cúi đầu nể phục. Và vô cùng ranh mãnh tận dụng cơ hội và nhanh chóng phát hiện điểm yếu của đối phương để tìm kiếm chiến thắng cho mình. Không phô trương sức mạnh, rất thầm lặng nhưng dấu ấn mà Lực Sĩ để lại mỗi trận chiến luôn khiến đối thủ phải dè chừng." + this.OCCUPATION + "Cũng giống như Ares của Ares, thu hoạch cuộc sống của kẻ thù nói chung." + "Họ là những ứng cử viên mạnh mẽ nhất cho vị anh hùng bao giờ, và luôn là những nhân vật nổi bật nhất trên chiến trường.";
        this.STRING_CUE_YONGAN = "SÁI HẦU - đại diện cho những chiến binh thầm lặng nhưng vô cùng ngoan cường." + this.OCCUPATIONTwo + " Hay còn được mệnh danh là những nhà trinh thám đại tài, cực kỳ nhanh nhạy khi sử dụng mưu lược trong các trận chiến. " + "khi sử dụng mưu lược trong các trận chiến. " + "à vô cùng ranh mãnh tận dụng cơ hội và nhanh chóng phát " + "hiện điểm yếu của đối phương để tìm kiếm chiến thắng cho mình. " + "Không phô trương sức mạnh " + "rất thầm lặng nhưng dấu ấn" + "mà Sái Hầu để lại mỗi trận " + this.OCCUPATION + "chiến luôn khiến đối thủ phải dè chừng. " + "Họ đang âm thầm tham gia vào nhiệm vụ rất nguy hiểm và không phô trương ở góc của chiến trường," + "Sau khi chiến thắng, họ không bao giờ tự lập chính mình, luôn là người hùng sau những cảnh quay.";
        this.STRING_CUE_LONGSHI = "PHÙ THỦY - " + this.OCCUPATION + "。" + "vượt qua mọi áp đặt cho sinh mệnh con người, " + "những Phù Thủy của Long Tinh đã ngộ ra được sức mạnh của ma pháp. " + "Dựa trên sự hiểu biết của mình, " + this.OCCUPATION + "Pháp Sư đã biến quyền năng của ma pháp để tăng cường sức mạnh và thay đổi sinh mệnh. " + this.OCCUPATION + "Thậm chí, các Phù Thủy còn có khả năng cải tử hoàn đồng đến kinh ngạc, Chính vì vậy, khi một Phù Thủy lâm trận, các đối thủ hãy dè chừng bởi chỉ trong thoáng chốc, họ đã biến điều không thể thành có thể.";
        this.STRING_CUE_TIANYING = "PHÁP SƯ - với vốn kiến thức uyên thâm về những tinh linh tự nhiên, Pháp Sư đã học cách chế ngự quyền năng để tăng cường pháp lực cũng như năng lực tấn công mạnh mẽ nhất. " + this.OCCUPATION + "Trong mỗi trận chiến, sự nhạy bén cũng như khả năng quan sát tốt của mình, Pháp Sư luôn là cơn ác mộng cho bất cứ đối thủ nào. ";
        if (this.bytReelMove == 0) {
            this.strLandString = StringManager.wenZi("Nhấn nút 0 hoặc giữa để chỉnh sửa biệt hiệu", this._tectW - 6);
        } else if (this.bytReelMove == 1) {
            this.strLandString = this.bytCountryIndex == 0 ? StringManager.wenZi("Con cháu của Rồng là con người sống trong lục địa Shenlong, họ là một cộng đồng có cả nhân cách tốt và chiến thắng. Được mô tả như là hậu duệ của gia tộc Rồng, trong thời cổ đại và các vị thần trên thiên đường cộng tác để đánh bại các gia tộc Mozu, và sau đó bởi đức hạnh của bộ lạc trí đại diện cho đất liền của Dragon từ thế hệ này sang thế hệ nhân, đã trở thành bậc thầy của đại lục.", this._tectW - 3) : StringManager.wenZi("Trong thời cổ đại của hậu duệ của Protoss và Terran Mozu, họ đã được sinh ra từ truyền cảm với loài người và Mozu là kẻ thù, khái niệm này đã được truyền từ thế hệ này sang thế hệ khác, vì vậy mà Mozu hiện tại cực kỳ cực đoan và Thế giới Rồng Trạng thái hận thù. Với sức sống tuyệt vời của họ, họ sống sót qua những điều kiện khắc nghiệt của đất liền khổng lồ mà không quên trả thù", this._tectW - 3);
        } else if (this.bytReelMove == 2) {
            this.strLandString = StringManager.wenZi("Vui lòng chọn giới tính của nhân vật đã tạo", this._tectW);
        } else if (this.bytReelMove == 3) {
            if (this.bytOccupation == 0) {
                this.strLandString = StringManager.wenZi(this.STRING_CUE_LONGKUANG, this._tectW);
            } else if (this.bytOccupation == 1) {
                this.strLandString = StringManager.wenZi(this.STRING_CUE_YONGAN, this._tectW);
            } else if (this.bytOccupation == 2) {
                this.strLandString = StringManager.wenZi(this.STRING_CUE_LONGSHI, this._tectW);
            } else if (this.bytOccupation == 3) {
                this.strLandString = StringManager.wenZi(this.STRING_CUE_TIANYING, this._tectW);
            }
        }
        if (this.strLandString != null) {
            StringManager.resetWordMoveTimer();
        }
    }

    private void initFoundRole() {
        int n = 0;
        String string = "";
        short s = 0;
        byte by = (byte) Common.getRandom(1, 2);
        byte by2 = 0;
        byte by3 = (byte) Common.getRandom(1, 4);
        byte by4 = (byte) Common.getRandom(1, 2);
        orplayerCreat = new ORPlayer();
        this.newRoleList(orplayerCreat, n, string, by3, s, by, by2, by4);
        DCanvas.getInstance().setCreateRole(orplayerCreat);
        DCanvas.getInstance().setFoundationRole(orplayerCreat);
        this.strLandString = null;
        this.bytReelMove = 0;
        this.bytDialogData[1] = 0;
        this.bytDialogData[2] = 0;
        this.bytDialogData[3] = 0;
        this.bytCountryIndex = (byte) (LandUI.orplayerCreat.bytCountry - 1);
        this.bytSexIndex = (byte) (LandUI.orplayerCreat.bytIsMale - 1);
        this.bytOccupation = (byte) (LandUI.orplayerCreat.bytOccupation - 1);
        this.setButton((byte) 2, (byte) -1, (byte) 1);
        orplayerCreat.pushTask((byte) 0, (byte) 2, (byte) 1);
        orplayerCreat.roleTaskAction(0);
        this.Ifkey = true;
        this.char_script = ImageManager.loadSpriteById(1, ImageManager.EncodespriteId(this.font_resource, this.font_resource), this.font_resource, this.font_resource, "loading");
        this.triangle = new QSprite[2];
        short s2 = 0;
        while (s2 < this.triangle.length) {
            this.triangle[s2] = ImageManager.loadSpriteById(1, ImageManager.EncodespriteId(this.triangle_resource, String.valueOf(s2)), this.triangle_resource, this.triangle_resource, "loading");
            ++s2;
        }
        this.triangle[0].setAnimation(1);
        this.triangle[1].setAnimation(3);
        short[] sArray = new short[8];
        sArray[3] = 1;
        sArray[5] = 2;
        sArray[7] = 3;
        this.char_index = sArray;
        this.font_index = new short[]{2, 4, 6, 8};
        this.gridtable_array = new short[]{4, 6, 8};
        this.bytReelMove = 0;
        this.createrole_rowpercent = new short[8];
        s2 = 0;
        while (s2 < 8) {
            this.createrole_rowpercent[s2] = 12;
            ++s2;
        }
        s2 = (short) ((Macro.SCREEN_WIDTH >> 1) - Macro.FONTW);
        short s3 = 44;
        short s4 = (short) (Macro.SCREEN_WIDTH - ((Macro.SCREEN_WIDTH >> 1) - Macro.FONTW) - 12 - 2);
        short s5 = (short) (Macro.FONTHEIGHT * 8);
        this.gridtable = new GridTable(s2, s3, s4, s5, (short) 8, (short) 1, this.createrole_rowpercent, new short[]{100});
        this.gridtable_width = this.gridtable.getCell(1, 1).getCellW();
        this.gridtable_height = this.gridtable.getCell(1, 1).getCellH();
        this.role_framex = 16;
        this.role_framey = this.gridtable.getCell(1, 1).getCenterY();
        this.role_framew = (short) (Macro.SCREEN_WIDTH - s4 - 24 - this.role_framex);
        this.role_frameh = (short) (this.gridtable.getCell(8, 1).getCenterY() - this.role_framey);
        this._textH = Macro.SCREEN_HEIGHT - 31 - 8 - (this.gridtable.getCell(8, 1).getCellY() + this.gridtable.getCell(8, 1).getCellH() + 8);
        this._tectW = this.gridtable.getCell(8, 1).getCellX() + this.gridtable.getCell(8, 1).getCellW() - 12 - 4;
        this._textY = this.gridtable.getCell(8, 1).getCellY() + this.gridtable.getCell(8, 1).getCellH() + 8;
        this.bytCountryIndex = (byte) Common.getRandom(0, 2);
        this.bytSexIndex = (byte) Common.getRandom(0, 2);
        this.bytOccupation = (byte) Common.getRandom(0, 4);
        this.updataInfo();
    }

    private void cleanCreadRoleDate() {
        this.char_script = null;
        this.triangle = null;
        this.char_index = null;
        this.font_index = null;
        this.gridtable_array = null;
        this.createrole_rowpercent = null;
        this.strLandString = null;
        this.role_framex = 0;
        this.role_framey = 0;
        this.role_frameh = 0;
        this.role_framew = 0;
        this._textH = 0;
        this._tectW = 0;
        this._textY = 0;
        LandUI.getInstance().strName = "";
    }

    private void drawFoundRole(Graphics graphics) {
        DCanvas.getInstance().drawTileTextBG(graphics, "Tạo nhân vật mới");
        DrawBase.setClip(0, 0, Macro.SCREEN_WIDTH, Macro.SCREEN_HEIGHT - 38);
        DrawBase.drawBox(this.gridtable.getCell(1, 1).getCellX(), this.gridtable.getCell(1, 1).getCellY(), this.gridtable.getCell(1, 1).getCellW(), this.gridtable.getCell(1, 1).getCellH() * 8 + 4, new int[]{14799766, 16772796}, true);
        int n = 1;
        while (n <= 8) {
            if (n % 2 == 0) {
                DrawBase.drawBox(this.gridtable.getCell(n, 1).getCellX() + 2, this.gridtable.getCell(n, 1).getCellY(), this.gridtable.getCell(n, 1).getCellW() - 4, this.gridtable.getCell(n, 1).getCellH(), new int[]{15768897, 14995858}, true);
                if (n != 2) {
                    this.triangle[0].drawAnimationFrame(graphics, 0, 0, this.gridtable.getCell(n, 1).getCellX() + 2 + this.triangle_widht, this.gridtable.getCell(n, 1).getCellY() + (this.triangle_height >> 1));
                    this.triangle[0].drawAnimationFrame(graphics, 2, 0, this.gridtable.getCell(n, 1).getCellX() + this.gridtable.getCell(n, 1).getCellW() - this.triangle_widht - 2, this.gridtable.getCell(n, 1).getCellY() + (this.triangle_height >> 1));
                }
            } else if (n % 2 == 1) {
                this.char_script.drawAnimationFrame(graphics, this.char_index[n], 0, this.gridtable.getCell(n, 1).getCellX() + 2, this.gridtable.getCell(n, 1).getCellY() + 1);
            }
            if (n == 2) {
                DrawBase.DrawString(this.strName, this.gridtable.getCell(n, 1).getCellX(), this.gridtable.getCell(n, 1).getCellY(), this.gridtable.getCell(n, 1).getCenterX(), this.gridtable.getCell(n, 1).getCenterY(), this.gridtable.getCell(n, 1).getCellW(), this.gridtable.getCell(n, 1).getCellH(), (short) 0, 8142636);
            } else if (n == 4) {
                DrawBase.drawString(String.valueOf(this.bytCountryIndex + 1) + "/2", this.gridtable.getCell(n - 1, 1).getCellX() + this.gridtable.getCell(n - 1, 1).getCellW(), this.gridtable.getCell(n - 1, 1).getCellY(), 8142636, 24);
                DrawBase.DrawString(this.bytCountryIndex == 0 ? "Long Tộc" : "Ma Tộc", this.gridtable.getCell(n, 1).getCellX(), this.gridtable.getCell(n, 1).getCellY(), this.gridtable.getCell(n, 1).getCenterX(), this.gridtable.getCell(n, 1).getCenterY(), this.gridtable.getCell(n, 1).getCellW(), this.gridtable.getCell(n, 1).getCellH(), (short) 0, 8142636);
            } else if (n == 6) {
                DrawBase.drawString(String.valueOf(this.bytSexIndex + 1) + "/2", this.gridtable.getCell(n - 1, 1).getCellX() + this.gridtable.getCell(n - 1, 1).getCellW(), this.gridtable.getCell(n - 1, 1).getCellY(), 8142636, 24);
                DrawBase.DrawString(this.bytSexIndex == 0 ? "Nam" : "Nữ", this.gridtable.getCell(n, 1).getCellX(), this.gridtable.getCell(n, 1).getCellY(), this.gridtable.getCell(n, 1).getCenterX(), this.gridtable.getCell(n, 1).getCenterY(), this.gridtable.getCell(n, 1).getCellW(), this.gridtable.getCell(n, 1).getCellH(), (short) 0, 8142636);
            } else if (n == 8) {
                DrawBase.drawString(String.valueOf(this.bytOccupation + 1) + "/4", this.gridtable.getCell(n - 1, 1).getCellX() + this.gridtable.getCell(n - 1, 1).getCellW(), this.gridtable.getCell(n - 1, 1).getCellY(), 8142636, 24);
                DrawBase.DrawString(this.bytCountryIndex == 0 ? ORole.getOccName((byte) (this.bytOccupation + 1)) : ORole.getOccName((byte) (this.bytOccupation + 1)), this.gridtable.getCell(n, 1).getCellX(), this.gridtable.getCell(n, 1).getCellY(), this.gridtable.getCell(n, 1).getCenterX(), this.gridtable.getCell(n, 1).getCenterY(), this.gridtable.getCell(n, 1).getCellW(), this.gridtable.getCell(n, 1).getCellH(), (short) 0, 8142636);
            }
            ++n;
        }
        n = 0;
        while (n < 4) {
            if (this.bytReelMove == n) {
                DrawBase.drawBox(this.gridtable.getCell(this.font_index[this.bytReelMove], 1).getCellX() + 2, this.gridtable.getCell(this.font_index[this.bytReelMove], 1).getCellY(), this.gridtable.getCell(this.font_index[this.bytReelMove], 1).getCellW() - 4, this.gridtable.getCell(this.font_index[this.bytReelMove], 1).getCellH(), new int[]{0x3C3CF3}, false);
                if (n != 0) {
                    this.triangle[0].drawAnimation(graphics, this.gridtable.getCell(this.font_index[this.bytReelMove], 1).getCellX() + 2 + this.triangle_widht, this.gridtable.getCell(this.font_index[this.bytReelMove], 1).getCellY() + (this.triangle_height >> 1));
                    this.triangle[1].drawAnimation(graphics, this.gridtable.getCell(this.font_index[this.bytReelMove], 1).getCellX() + this.gridtable.getCell(this.font_index[this.bytReelMove], 1).getCellW() - this.triangle_widht - 2, this.gridtable.getCell(this.font_index[this.bytReelMove], 1).getCellY() + (this.triangle_height >> 1));
                }
            }
            ++n;
        }
        DrawBase.drawBox(this.role_framex, this.role_framey, this.role_framew, this.role_frameh, new int[]{11301955, 14995858}, true);
        if (orplayerCreat != null) {
            orplayerCreat.draw(graphics, this.role_framex + (this.role_framew >> 1), this.role_framey + ((this.role_frameh >> 1) + (this.role_frameh >> 2)));
        }
        DrawBase.drawBox(this.role_framex - 1, this._textY, this._tectW, this._textH, new int[]{15768897, 16642234}, true);
        StringManager.drawWordMove(graphics, this.strLandString, this.role_framex + 2, this._textY, this._textH - 8, 9263661, 20);
        DrawBase.setClip(0, 0, Macro.SCREEN_WIDTH, Macro.SCREEN_HEIGHT);
    }

    private void updateFoundRole() {
        if (orplayerCreat != null) {
            orplayerCreat.update();
        }
        if (this.Ifkey) {
            switch (DCanvas.getInstance().getKeyRelease()) {
                case 4096: {
                    if (this.bytReelMove > 0) {
                        this.bytReelMove = (byte) (this.bytReelMove - 1);
                    }
                    this.updataInfo();
                    break;
                }
                case 8192: {
                    if (this.bytReelMove < 3) {
                        this.bytReelMove = (byte) (this.bytReelMove + 1);
                    }
                    this.updataInfo();
                    break;
                }
                case 16384: {
                    if (this.bytReelMove == 1) {
                        this.bytCountryIndex = (byte) (this.bytCountryIndex - 1);
                        if (this.bytCountryIndex < 0) {
                            this.bytCountryIndex = 1;
                        }
                    } else if (this.bytReelMove == 2) {
                        this.bytSexIndex = (byte) (this.bytSexIndex - 1);
                        if (this.bytSexIndex < 0) {
                            this.bytSexIndex = 1;
                        }
                    } else if (this.bytReelMove == 3) {
                        this.bytOccupation = (byte) (this.bytOccupation - 1);
                        if (this.bytOccupation < 0) {
                            this.bytOccupation = (byte) 3;
                        }
                    }
                    this.updataInfo();
                    break;
                }
                case 32768: {
                    if (this.bytReelMove == 1) {
                        this.bytCountryIndex = (byte) (this.bytCountryIndex + 1);
                        if (this.bytCountryIndex > 1) {
                            this.bytCountryIndex = 0;
                        }
                    } else if (this.bytReelMove == 2) {
                        this.bytSexIndex = (byte) (this.bytSexIndex + 1);
                        if (this.bytSexIndex > 1) {
                            this.bytSexIndex = 0;
                        }
                    } else if (this.bytReelMove == 3) {
                        this.bytOccupation = (byte) (this.bytOccupation + 1);
                        if (this.bytOccupation > 3) {
                            this.bytOccupation = 0;
                        }
                    }
                    this.updataInfo();
                    break;
                }
                case 1: {
                    DCanvas.getInstance().setCanvasState(false);
                    FormDes.getInstance().showForm((byte) 19);
                }
            }
        }
        switch (DCanvas.getInstance().getKeyRelease()) {
            case 262144: {
                if (this.bytReelMove != 0) {
                    break;
                }
                DCanvas.getInstance().setCanvasState(false);
                FormDes.getInstance().showForm((byte) 19);
                break;
            }
            case 65536: {
                if (this.twodialog == null) {
                    if (DCanvas.getInstance().getCanvasState()) {
                        if (this.bytReelMove == 0) {
                            if (this.strName.equals("")) {
                                DCanvas.getInstance().setCanvasState(false);
                                FormDes.getInstance().showForm((byte) 19);
                                break;
                            }
                            NetSend.getInstance().sendClientStep((byte) 6);
                            NetSend.getInstance().sendNewRole(this.strName, this.bytCountryIndex, this.bytOccupation, this.bytSexIndex, LandUI.getInstance().bytDialogData[0]);
                            DCanvas.getInstance().setNetLoad(true);
                            LandUI.getInstance().getClass();
                            this.setTwoDialog("Tạo nhân vật", (byte) -1, (byte) -1);
                            break;
                        }
                        if (this.bytReelMove != 1 && this.bytReelMove != 2 && this.bytReelMove != 3) {
                            break;
                        }
                        if (this.strName.equals("")) {
                            this.Ifkey = false;
                            LandUI.getInstance().getClass();
                            this.setTwoDialog("Vui lòng nhập tên", (byte) 0, (byte) 1);
                            break;
                        }
                        NetSend.getInstance().sendClientStep((byte) 6);
                        NetSend.getInstance().sendNewRole(this.strName, this.bytCountryIndex, this.bytOccupation, this.bytSexIndex, LandUI.getInstance().bytDialogData[0]);
                        DCanvas.getInstance().setNetLoad(true);
                        LandUI.getInstance().getClass();
                        this.setTwoDialog("Tạo nhân vật", (byte) -1, (byte) -1);
                        break;
                    }
                    DCanvas.getInstance().setCanvasState(true);
                    break;
                }
                if (DCanvas.getInstance().getCanvasState()) {
                    this.Ifkey = true;
                    this.twodialog = null;
                    DCanvas.getInstance().setNetLoad(false);
                    DCanvas.getInstance().setCanvasState(false);
                    FormDes.getInstance().showForm((byte) 19);
                    this.switchStateAndMemoryControl((byte) 11);
                    this.setButton((byte) 2, (byte) -1, (byte) 1);
                    break;
                }
                DCanvas.getInstance().setCanvasState(true);
                break;
            }
            case 131072: {
                if (DCanvas.getInstance().getCanvasState()) {
                    if (this.twodialog == null) {
                        this.setState((byte) 10);
                        break;
                    }
                    this.Ifkey = true;
                    this.twodialog = null;
                    this.setButton((byte) 2, (byte) -1, (byte) 1);
                    break;
                }
                DCanvas.getInstance().setCanvasState(true);
            }
        }
    }

    private void logicReel(int n) {
        if (this.strLandString != null && this.strLandString.length * Macro.FONTHEIGHT > this.shtRoleFoundWordH) {
            if (this.intTimer > 0) {
                this.intTimer -= n;
            } else {
                this.shtRoleWordY = (short) (this.shtRoleWordY - 1);
                if (-this.shtRoleWordY >= this.shtRoleFoundWordH + (this.strLandString.length * Macro.FONTHEIGHT - this.shtRoleFoundWordH)) {
                    this.shtRoleWordY = this.shtRoleFoundWordH;
                }
            }
        }
    }

    public void addFream() {
        this.bytAcrossType = 0;
        this.blnRockFlash = false;
        this.shtRockFlashY = 0;
        this.shtCloudFlashY = 0;
        this.bytRockFlashType = 0;
        this.blnIsLogic = false;
        if ((ORPlayer) this.hPlayerList.get(new Integer(Rms.bytPlace)) != null) {
            this.shtLandMove = Rms.bytPlace;
        } else {
            byte[] byArray = null;
            if (Rms.bytPlace == 0) {
                byArray = new byte[]{1, 2};
            } else if (Rms.bytPlace == 1) {
                byte[] byArray2 = new byte[2];
                byArray2[1] = 2;
                byArray = byArray2;
            } else {
                byte[] byArray3 = new byte[2];
                byArray3[1] = 1;
                byArray = byArray3;
            }
            int n = 0;
            int n2 = 0;
            while (n2 < byArray.length) {
                if (this.hPlayerList.containsKey(new Integer(byArray[n2]))) {
                    this.shtLandMove = byArray[n2];
                    n = -1;
                    break;
                }
                n2 = (byte) (n2 + 1);
            }
            if (n == 0) {
                this.shtLandMove = 0;
            }
        }
    }

    private void clearFream() {
        this.hPlayerList = null;
    }

    private void flashRock() {
        if (this.bytRockFlashType == 5) {
            this.bytRockFlashType = 0;
            this.shtRockFlashY = 0;
            this.shtCloudFlashY = 0;
            this.blnRockFlash = false;
        } else if (this.bytRockFlashType == 0) {
            this.shtRockFlashY = (short) (this.shtRockFlashY + 1);
            this.shtCloudFlashY = (short) (this.shtCloudFlashY + 1);
            if (this.shtRockFlashY == 1) {
                this.shtCloudFlashY = (short) (this.shtCloudFlashY + 1);
            }
            if (this.shtRockFlashY == 3) {
                this.bytRockFlashType = 1;
            }
        } else if (this.bytRockFlashType == 1) {
            this.bytRockFlashType = (byte) 2;
            this.shtRockFlashY = (short) (this.shtRockFlashY - 4);
            this.shtCloudFlashY = (short) (this.shtCloudFlashY - 3);
        } else if (this.bytRockFlashType == 2) {
            this.bytRockFlashType = (byte) 3;
            this.shtRockFlashY = (short) (this.shtRockFlashY - 1);
            this.shtCloudFlashY = (short) (this.shtCloudFlashY - 1);
        } else if (this.bytRockFlashType == 3) {
            this.bytRockFlashType = (byte) 4;
            this.shtCloudFlashY = (short) (this.shtCloudFlashY - 1);
        } else if (this.bytRockFlashType == 4) {
            this.bytRockFlashType = (byte) 5;
            this.shtRockFlashY = (short) (this.shtRockFlashY + 1);
            this.shtCloudFlashY = (short) (this.shtCloudFlashY + 1);
        }
    }

    private void initSelectRole() {
        this.cleanCreadRoleDate();
        if (this.imgLand1 == null) {
            this.imgLand1 = ImageManager.CreateImage("role_land1", "loading");
        }
        if (this.imgLand2 == null) {
            this.imgLand2 = ImageManager.CreateImage("role_land2", "loading");
        }
    }

    private void drawSelectRole(Graphics graphics) {
        short s;
        short s2;
        this.drawGameBack(graphics);
        this.drawTitleBox(graphics, "Chọn một nhân vật");
        short s3 = 1;
        short s4 = 3;
        this.gridTable = new GridTable((short) 0, (short) 0, Macro.SCREEN_WIDTH, Macro.SCREEN_HEIGHT, s4, s3, new short[]{14, 76, 10}, new short[]{100});
        int n = 8;
        short s5 = (short) (this.gridTable.getCell(2, 1).getCellX() + n);
        short s6 = this.gridTable.getCell(2, 1).getCellY();
        short s7 = (short) (this.gridTable.getCell(2, 1).getCellW() - 2 * n);
        short s8 = this.gridTable.getCell(2, 1).getCellH();
        DrawBase.drawBox(s5, s6, s7, s8, new int[]{14995858, 14995858, 14995858, 16314576}, true);
        s3 = 5;
        s4 = 2;
        this.gridTable_row2_col1_Table = new GridTable(s5, s6, s7, s8, s4, s3, new short[]{50, 50}, new short[]{2, 32, 32, 32, 2});
        short s9 = (short) (this.gridTable_row2_col1_Table.getCell(1, 2).getCellW() * 90 / 100);
        short s10 = (short) (this.gridTable_row2_col1_Table.getCell(1, 2).getCellH() * 70 / 100);
        short s11 = (short) (this.gridTable_row2_col1_Table.getCell(1, 2).getCellX() + (this.gridTable_row2_col1_Table.getCell(1, 2).getCellW() - s9) / 2);
        short s12 = (short) (this.gridTable_row2_col1_Table.getCell(1, 2).getCellY() + (this.gridTable_row2_col1_Table.getCell(1, 2).getCellH() - s10) / 2);
        DrawBase.drawBox(s11, s12, s9, s10, new int[]{this.shtLandMove == 0 ? 16215570 : 11301955, this.shtLandMove == 0 ? 16033125 : 14995858}, true);
        s9 = (short) (this.gridTable_row2_col1_Table.getCell(1, 3).getCellW() * 90 / 100);
        s10 = (short) (this.gridTable_row2_col1_Table.getCell(1, 3).getCellH() * 70 / 100);
        s11 = (short) (this.gridTable_row2_col1_Table.getCell(1, 3).getCellX() + (this.gridTable_row2_col1_Table.getCell(1, 3).getCellW() - s9) / 2);
        s12 = (short) (this.gridTable_row2_col1_Table.getCell(1, 3).getCellY() + (this.gridTable_row2_col1_Table.getCell(1, 3).getCellH() - s10) / 2);
        DrawBase.drawBox(s11, s12, s9, s10, new int[]{this.shtLandMove == 1 ? 16215570 : 11301955, this.shtLandMove == 1 ? 16033125 : 14995858}, true);
        s9 = (short) (this.gridTable_row2_col1_Table.getCell(1, 4).getCellW() * 90 / 100);
        s10 = (short) (this.gridTable_row2_col1_Table.getCell(1, 4).getCellH() * 70 / 100);
        s11 = (short) (this.gridTable_row2_col1_Table.getCell(1, 4).getCellX() + (this.gridTable_row2_col1_Table.getCell(1, 4).getCellW() - s9) / 2);
        s12 = (short) (this.gridTable_row2_col1_Table.getCell(1, 4).getCellY() + (this.gridTable_row2_col1_Table.getCell(1, 4).getCellH() - s10) / 2);
        DrawBase.drawBox(s11, s12, s9, s10, new int[]{this.shtLandMove == 2 ? 16215570 : 11301955, this.shtLandMove == 2 ? 16033125 : 14995858}, true);
        short s13 = 0;
        while (s13 < 3) {
            ORPlayer oRPlayer = (ORPlayer) this.hPlayerList.get(new Integer(s13));
            if (oRPlayer == null) {
                s2 = this.gridTable_row2_col1_Table.getCell(1, s13 + 2).getCenterX();
                s = (short) (this.gridTable_row2_col1_Table.getCell(1, s13 + 2).getCenterY() + 20);
                if (this.shtLandMove == s13) {
                    DrawBase.drawImage(this.imgLand1, s2 - (this.imgLand1.getWidth() >> 1), s - (s10 >> 1) + (this.imgLand1.getHeight() >> 1), 0);
                } else {
                    DrawBase.drawImage(this.imgLand2, s2 - (this.imgLand2.getWidth() >> 1), s - (s10 >> 1) + (this.imgLand2.getHeight() >> 1), 0);
                }
            }
            s13 = (byte) (s13 + 1);
        }
        s13 = (short) (this.gridTable_row2_col1_Table.getCell(2, 1).getCellX() + n);
        short s14 = this.gridTable_row2_col1_Table.getCell(2, 1).getCellY();
        s2 = (short) (this.gridTable_row2_col1_Table.getCell(2, 1).getCellW() + this.gridTable_row2_col1_Table.getCell(2, 2).getCellW() + this.gridTable_row2_col1_Table.getCell(2, 3).getCellW() + this.gridTable_row2_col1_Table.getCell(2, 4).getCellW() + this.gridTable_row2_col1_Table.getCell(2, 5).getCellW() - 2 * n);
        s = (short) (this.gridTable_row2_col1_Table.getCell(2, 1).getCellH() - 2 * n);
        DrawBase.drawBox(s13, s14, s2, s, new int[]{12026667, 16442771}, true);
        int n2 = 0;
        while (n2 < 3) {
            ORPlayer orp = (ORPlayer) this.hPlayerList.get(new Integer(n2));
            if (orp != null) {
                orp.shtJudgeX = this.gridTable_row2_col1_Table.getCell(1, n2 + 2).getCenterX();
                orp.shtJudgeY = (short) (this.gridTable_row2_col1_Table.getCell(1, n2 + 2).getCenterY() + 20);
                orp.draw(graphics, orp.shtJudgeX, orp.shtJudgeY);
            }
            n2 = (byte) (n2 + 1);
        }
        ORPlayer oRPlayer = (ORPlayer) this.hPlayerList.get(new Integer(this.shtLandMove));
        if (oRPlayer != null) {
            graphics.setColor(8142636);
            String name = oRPlayer.strNickName;
            short s15 = oRPlayer.shtLevel;
            String string = ORole.getOccName(oRPlayer.bytOccupation);
            String string2 = oRPlayer.bytCountry == 1 ? "Long Tộc" : "Ma Tộc";
            String[] stringArray = new String[]{"Tên: ", "Cấp: ", "Phái: ", "Tộc: "};
            String[] stringArray2 = new String[]{name, "" + s15, string, string2};
            short s16 = 4;
            short s17 = 2;
            this.gridTable_row3_col1_Table = new GridTable(s13, s14, s2, s, s16, s17, new short[]{25, 25, 25, 25}, new short[]{30, 70});
            int n3 = 0;
            while (n3 < stringArray.length) {
                DrawBase.DrawString(stringArray[n3], this.gridTable_row3_col1_Table.getCell(1 + n3, 1).getCellX(), this.gridTable_row3_col1_Table.getCell(1 + n3, 1).getCellY(), this.gridTable_row3_col1_Table.getCell(1 + n3, 1).getCenterX(), this.gridTable_row3_col1_Table.getCell(1 + n3, 1).getCenterY(), this.gridTable_row3_col1_Table.getCell(1 + n3, 1).getCellW(), this.gridTable_row3_col1_Table.getCell(1 + n3, 1).getCellH(), (short) 0, 8142636);
                DrawBase.drawBox(this.gridTable_row3_col1_Table.getCell(1 + n3, 2).getCellX(), this.gridTable_row3_col1_Table.getCell(1 + n3, 2).getCellY() + 2, this.gridTable_row3_col1_Table.getCell(1, 2).getCellW(), this.gridTable_row3_col1_Table.getCell(1, 2).getCellH() - 4, new int[]{14995858, 16642234}, true);
                DrawBase.DrawString(stringArray2[n3], this.gridTable_row3_col1_Table.getCell(1 + n3, 2).getCellX(), this.gridTable_row3_col1_Table.getCell(1 + n3, 2).getCellY(), this.gridTable_row3_col1_Table.getCell(1 + n3, 2).getCenterX(), this.gridTable_row3_col1_Table.getCell(1 + n3, 2).getCenterY(), this.gridTable_row3_col1_Table.getCell(1 + n3, 2).getCellW(), this.gridTable_row3_col1_Table.getCell(1 + n3, 2).getCellH(), (short) 0, 8142636);
                ++n3;
            }
            if (!DCanvas.getInstance().blnIsTouch) {
                DrawBase.drawString("Nhấn * để xóa", Macro.SCREEN_WIDTH / 2, this.gridTable_row3_col1_Table.getCell(4, 1).getCellH() + this.gridTable_row3_col1_Table.getCell(4, 1).getCellY(), 0xFF0000, 17);
            }
        }
        this.setRole(this.shtLandMove);
        DCanvas.getInstance().clearScreen();
    }

    private void updateSelectRole() {
        switch (DCanvas.getInstance().getKeyRelease()) {
            case 65536:
            case 262144: {
                if (this.twodialog != null) {
                    break;
                }
                if (this.hPlayerList != null && this.hPlayerList.containsKey(new Integer(this.shtLandMove))) {
                    DCanvas.getInstance().setNetLoad(true);
                    this.setTwoDialog("Vào trò chơi", (byte) -1, (byte) -1);
                    NetParse.getInstance().setInitLoadingPre();
                    NetSend.getInstance().sendClientStep((byte) 7);
                    NetSend.getInstance().sendSelectRole(((ORPlayer) this.hPlayerList.get((Object) new Integer((int) this.shtLandMove))).intUserId);
                    NetParse.getInstance().setInitLoading();
                    this.SavsChoose = false;
                    if (this.shtLandMove == Rms.bytPlace) {
                        break;
                    }
                    Rms.savePlace((byte) this.shtLandMove);
                    break;
                }
                this.bytDialogData[0] = (byte) this.shtLandMove;
                this.SavsChoose = true;
                LandUI.getInstance().setState((byte) 11);
                break;
            }
            case 131072: {
                if (this.twodialog == null) {
                    this.SavsChoose = false;
                    this.setState((byte) 3);
                    this.clearFream();
                    break;
                }
                this.twodialog = null;
                break;
            }
            case 16384: {
                this.shtLandMove = (short) (this.shtLandMove - 1);
                if (this.shtLandMove < 0) {
                    this.shtLandMove = (short) 2;
                }
                this.setAcrossInfo(this.shtLandMove);
                break;
            }
            case 32768: {
                this.shtLandMove = (short) (this.shtLandMove + 1);
                if (this.shtLandMove > 2) {
                    this.shtLandMove = 0;
                }
                this.setAcrossInfo(this.shtLandMove);
                break;
            }
            case 2048:
            case 524288: {
                if (!this.hPlayerList.containsKey(new Integer(this.shtLandMove))) {
                    break;
                }
                GameControl.getInstance().CreateState((byte) 7);
                DialogUI.getInstance().setDialog((byte) 100, "Xóa" + ((ORPlayer) this.hPlayerList.get((Object) new Integer((int) this.shtLandMove))).strNickName + " ？", (byte) 2);
            }
        }
    }

    public void DeleteRole() {
        DCanvas.getInstance().setNetLoad(true);
        this.setTwoDialog("Loại bỏ", (byte) -1, (byte) -1);
        this.bytDialogData[0] = (byte) this.shtLandMove;
        NetSend.getInstance().sendDelRole(((ORPlayer) this.hPlayerList.get((Object) new Integer((int) this.shtLandMove))).intUserId);
    }

    private void initRell() {
        if (this.intsRockPlace == null) {
            this.intsRockPlace = new int[]{(Macro.SCREEN_WIDTH - 43) / 2 + 7, 50, (Macro.SCREEN_WIDTH - 43) / 2 - 60 + 7, 65, (Macro.SCREEN_WIDTH - 43) / 2 + 60 + 7, 65};
        }
    }

    public void setAcrossInfo(short s) {
        this.setRole(s);
        if (this.hPlayerList != null && this.hPlayerList.containsKey(new Integer(s))) {
            if (this.bytAcrossType == 2) {
                this.bytAcrossType = (byte) 3;
                this.initRell();
            }
            this.blnRockFlash = true;
            this.shtRockFlashY = 0;
            this.shtCloudFlashY = 0;
            this.bytRockFlashType = 0;
        } else {
            this.bytAcrossType = (byte) 2;
        }
    }

    public void setRole(short s) {
        this.shtLandMove = s;
        if (this.hPlayerList != null && !this.hPlayerList.isEmpty() && this.hPlayerList.containsKey(new Integer(s))) {
            this.setButton((byte) 0, (byte) 3, (byte) 1);
        } else {
            this.setButton((byte) 2, (byte) -1, (byte) 1);
        }
    }

    public void setButton(byte by, byte by2, byte by3) {
        this.bytButtonType[0] = by;
        this.bytButtonType[1] = by2;
        this.bytButtonType[2] = by3;
    }

    private void drawButton(byte[] byArray) {
        DrawBase.setClip(0, 0, Macro.SCREEN_WIDTH, Macro.SCREEN_HEIGHT);
        if (byArray[0] != -1) {
            DrawBase.drawRegion(this.imgButton, 0, Macro.SCREEN_HEIGHT - 24, 0, byArray[0] * 24, 54, 24, 0, 20);
        }
        if (byArray[1] != -1) {
            if (DCanvas.getInstance().blnIsTouch) {
                DrawBase.drawRegion(this.imgButton, (Macro.SCREEN_WIDTH - 55) / 2, Macro.SCREEN_HEIGHT - 24, 0, byArray[1] * 24, 54, 24, 0, 20);
            } else {
                this.drawCue();
            }
        }
        if (byArray[2] != -1) {
            DrawBase.drawRegion(this.imgButton, Macro.SCREEN_WIDTH - 55, Macro.SCREEN_HEIGHT - 24, 0, byArray[2] * 24, 54, 24, 0, 20);
        }
    }

    private void drawCue() {
        if (this.blnIsLogic) {
            DCanvas.getInstance().clearScreen();
        }
        DrawBase.setColor(0xFF0000);
        DrawBase.drawString("Nhấn phím * để xóa", Macro.SCREEN_WIDTH / 2, Macro.SCREEN_HEIGHT - 3, 0xFF0000, 33);
    }

    private void keyOther() {
        if (this.twodialog != null) {
            return;
        }
        byte by = DCanvas.getInstance().getKeyShortcut();
        if (by != -1 && by <= 9) {
            this.addInputNumber(by);
            DCanvas.getInstance().clearKey();
        }
    }

    private void movesesDebarkation() {
        DCanvas.getInstance().setNetLoad(true);
        this.setTwoDialog("Đang vào game", (byte) -1, (byte) -1);
        NetSend.getInstance().sendClientStep((byte) 3);
        NetSend.getInstance().sendLand(this.strLandAccounts, this.strLandPassword, (byte) 1);
        NetSend.getInstance().sendRequestRes(IResConfig.RES_CONFIG.length);
    }

    public void movesesRegistration() {
        DCanvas.getInstance().setNetLoad(true);
        this.setTwoDialog("Đăng ký", (byte) -1, (byte) -1);
        NetSend.getInstance().sendClientStep((byte) 2);
        NetSend.getInstance().sendRegister(this.strLandAccounts, this.strLandPassword, "null");
    }

    private void rapidDebarkation() {
        if (Rms.strRmsName != null) {
            DCanvas.getInstance().setNetLoad(true);
            this.setTwoDialog("Đang vào game", (byte) -1, (byte) -1);
            NetSend.getInstance().sendClientStep((byte) 3);
            NetSend.getInstance().sendLand(Rms.strRmsName, Rms.strRmsPassword, (byte) 1);
            NetSend.getInstance().sendRequestRes(IResConfig.RES_CONFIG.length);
        } else {
            this.setTwoDialog("Đang vào game", (byte) -1, (byte) 1);
        }
    }

    private void switchStateAndMemoryControl(byte by) {
        ResPoolFactory.getInstance().ClearPool(1);
        ResPoolFactory.getInstance().ClearPool(4);
        ResPoolFactory.getInstance().ClearPool(5);
        ResPoolFactory.getInstance().ClearPool(6);
    }

    private void cleanAllResource() {
        this.anykey = null;
        this.logoImg = null;
        this.background = null;
        this.arrows = null;
        this.bird = null;
        this.font = null;
        this.default_font_left = null;
        this.default_font_right = null;
        this.flash_array = null;
        this.logo = null;
        this.star = null;
        this.could = null;
        this.left_array = null;
        this.right_array = null;
        Param.getInstance().Forma_ccount = null;
        Param.getInstance().Forma_password = null;
    }
}
