// Decompiled with: CFR 0.151
// Class Version: 1
package face;

import base.DCanvas;
import base.GameControl;
import base.Macro;
import base.Param;
import common.DrawBase;
import common.GridCell;
import common.GridTable;
import common.IDefines;
import common.LayoutStyle;
import common.PackageBox;
import common.PopupDialog;
import common.Pram;
import common.RoleViewStyle;
import common.ScrollText;
import common.TabStyle;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import means.AnalysisWordContent;
import means.ChatContent;
import means.DebugFrame;
import means.ImageManager;
import means.QSprite;
import means.Rms;
import means.StringManager;
import model.CollectionObject;
import model.MailObject;
import model.MakeObject;
import model.Map;
import model.MenuObject;
import model.ONpc;
import model.ORPMe;
import model.ORPlayer;
import model.ORole;
import model.PackageObject;
import model.Pet;
import model.SkillObject;
import model.TaskObject;
import model.TeamObject;
import network.FakeServer;
import network.NetManager;
import network.NetParse;
import network.NetSend;

public class MenuUI
        implements UIbase {

    public boolean blnIsThis = false;
    public final String STRING_OPTION_PK;
    public final String STRING_OPTION_BACKNAME;
    public final String STRING_OPTION_EQUIP;
    public boolean isDrawSkillinfo;
    public boolean isCheckSkillinfo;
    public boolean iscutkey;
    public final String STRING_OPTION_SYSTEM_ANNAL;
    public final String STRING_OPTION_NEED_STUFF;
    private final String STRING_OPTION_LV;
    private final String STRING_SAY_OPTIONAL;
    private final String STRING_SAY_YOU;
    private final String STRING_SAY_ALSO;
    private final String STRING_SAY_ATTAIN;
    public final String STRING_SAY_WEAR;
    private final String STRING_TITLE_TASK;
    private final String STRING_CUE_NO_PEOPLE;
    private final String STRING_OPTION_STATUS;
    public final String STRING_SAY_NAME;
    public final String STRING_SAY_LV;
    private final String STRING_SAY_CLAN;
    private final String STRING_SAY_SEX;
    public final String STRING_SAY_JOB;
    public final String STRING_SAY_UNION;
    private final String STRING_SAY_UNIONOCC;
    private final String STRING_SAY_EXPERIENCE;
    private final String STRING_SAY_GOLD;
    private final String STRING_SAY_SPOUSE;
    private final String STRING_SAY_MASTER;
    private final String STRING_SAY_APPRENTENCE;
    private final String STRING_SAY_AP;
    private final String STRING_SAY_AGILITY;
    private final String STRING_SAY_BRAINS;
    private final String STRING_SAY_STAMINA;
    private final String STRING_SAY_HEART;
    private final String STRING_SAY_LUCK;
    private final String STRING_SAY_HP;
    private final String STRING_SAY_MP;
    private final String STRING_SAY_HARM;
    private final String STRING_SAY_CRIT;
    private final String STRING_SAY_BUMB;
    private final String STRING_SAY_HIT;
    private final String STRING_SAY_HITRATE;
    private final String STRING_SAY_PHYSICS_MAX;
    private final String STRING_SAY_RECOVERY;
    private final String STRING_SAY_MAGICATTRACK;
    private final String STRING_SAY_JINK;
    private final String STRING_SAY_TRANSIENT;
    private final String STRING_SAY_ShadowRecovery;
    private final String STRING_SAY_HolinessRecovery;
    private final String STRING_SAY_FireRecovery;
    private final String STRING_SAY_WaterRecovery;
    private final String STRING_SAY_EarthRecovery;
    private final String STRING_SAY_SKILLENEMY;
    private final String STRING_SAY_SKILLNUM;
    private final String STRING_SAY_PKWIN;
    private final String STRING_SAY_PKNUM;
    public final String STRING_SAY_BASIC;
    public final String STRING_SEE_ROLE_BASIC;
    public final String STRING_SAY_ATTACK;
    public final String STRING_OPTION_RECOVERY;
    private final String STRING_CUE_MONEY;
    private final String STRING_CUE_EXP;
    private final String STRING_CUE_AWARD;
    public final String STRING_CUE_INPUT_MONEY;
    public final String STRING_OPTION_SEE;
    public final String STRING_PUT_GOODS;
    public final String STRING_DEL_GOODS;
    public final String STRING_SEE_GOODS;
    public final String STRING_SEE_PROPERTY;
    public final String STRING_BACK;
    public final String STRING_OPTION_RELESE;
    public final String STRING_OPTION_USE;
    public final String STRING_OPTION_SKILL_FRESH;
    public final String STRING_OPTION_SKILL_UP;
    public final String STRING_OPTION_KEY;
    public final String STRING_OPTION_SORT;
    public final String STRING_OPTION_MOVE;
    public final String STRING_OPTION_DICE;
    public final String STRING_OPTION_PUP;
    public final String STRING_OPTION_PDOWN;
    public final String STRING_OPTION_WRITE;
    public final String STRING_OPTION_SEND_EMALL;
    private final String STRING_MAIL_RECV_TYPE;
    private final String STRING_MALL_RECV_TITLE;
    public final String STRING_MALL_RECV_SEE_TITLE;
    public final String STRING_MALL_RECV_SEE_CONTENT;
    private final String STRING_MAIL_RECV_ADDRESSER;
    public final String STRING_OPTION_EPURATE;
    public final String STRING_OPTION_MAKE;
    public final String STRING_OPTION_ABNEGATE;
    public final String STRING_OPTION_SINGLE;
    public final String STRING_OPTION_TROOP_INVITE;
    public final String STRING_OPTION_BLACKLIST;
    public final String STRING_OPTION_WANTED;
    public final String STRING_OPTION_LOVER;
    public final String STRING_OPTION_LOVEREXPLAIN;
    public final String STRING_OPTION_GIVEUPHAND;
    public final String STRING_OPTINE_GIVEDIVORCE;
    public final String STRING_OPTION_TRANSMIT;
    public final String STRING_OPTION_FRIEND;
    public final String STRING_OPTION_GONGHUI;
    public final String STRING_OPTION_INVITE;
    public final String STRING_OPTION_MANAGE;
    public final String STRING_OPTION_INFROMATION;
    public final String STRING_OPINT_EXIT;
    public final String STRING_OPINT_PROMOTE;
    public final String STRING_OPINT_REDUCE;
    public final String STRING_OPINT_REJECT;
    public final String STRING_OPINT_UPGRADE;
    public final String STRING_OPTION_GIVEUP;
    public final String STRING_OPTION_LIVE;
    public final String STRING_OPTION_DEL;
    public final String STRING_OPTION_ADD;
    public final String SRTING_OPTION_ADD_GOODS;
    public final String STRING_OPTION_SEE_DETAILS;
    public final String STRING_OPTION_ADD_FRIENDS;
    public final String STRING_OPTION_ADD_ENEMY;
    public final String STRING_OPTION_ADD_SHIELD;
    public final String STRING_OPTION_ADD_HEIMING;
    public final String STRING_OPTIOM_ADD_CHOUREN;
    public final String STRING_OPTION_SEVER_TROOP;
    public final String STRING_OPTION_APPOINT_AIDE;
    public final String STRING_OPTION_CANCEL_AIDE;
    public final String STRING_OPTION_TROOP_PLACE;
    public final String STRING_OPTION_CESSION_CAPTAIN;
    public final String STRING_OPTION_DEL_MEMBER;
    public final String STRING_OPTION_TALK;
    public final String STRING_OPTION_SENDSMS;
    public final String STRING_OPTION_CLEARSMS;
    public final String STRING_OPTION_REPLY;
    public final String STRING_OPTION_GOODS_SEE;
    public final String STRING_OPTION_CHANNEL;
    public final String STRING_OPTION_ACCEPT_TASK;
    public final String STRING_OPTION_BUY;
    public final String STRING_OPTION_STILETTO;
    public final String STRING_OPTION_INLAY;
    public final String STRING_OPTION_EXTRACT;
    public final String STRING_OPTION_REPAIR;
    public final String STRING_OPTION_COMPARE;
    public final String STRING_OPTION_ARM;
    public final String STRING_OPTION_UNLOAD;
    public final String STRING_OPTION_INLAY_BOOK;
    public final String STRING_OPTION_SEAL;
    public final String STRING_OPTION_SEE_KIND;
    public final String STRING_OPTION_SELL_OUTFIT;
    public final String STRING_OPTION_BUY_ONE;
    public final String STRING_OPTION_BUY_MORE;
    public final String STRING_OPTION_SELL_ONE;
    public final String STRING_OPTION_SELL_MORE;
    public final String STRING_OPTION_STORAGE_OUT;
    public final String STRING_OPTION_STORAGE_INPUT;
    public final String STRING_OPTION_PET_HIDE;
    public final String STRING_OPTION_ANSWER;
    public final String STRING_OPTION_GET;
    public final String STRING_OPTION_PAGEUP;
    public final String STRING_OPTION_PAGEDOWN;
    public final String STRING_OPTION_SAVE;
    public final String STRING_OPTION_UP_BAG;
    public final String STRING_OPTION_WEIGHTY;
    public final String STRING_OPTION_SPAR;
    public final String STRING_OPTION_CLOTH;
    public final String STRING_OPTION_BISHOU;
    public final String STRING_OPTION_CHUIZI;
    public final String STRING_OPTION_FAZHANG;
    public final String STRING_OPTION_GONGJIAN;
    public final String STRING_OPTION_JIAN;
    private final String STRING_OPTION_SIGN;
    public final String STRING_OPTION_TILTED;
    public final String STRING_OPTION_TO;
    public final String STRING_CUE_NODEAL;
    public final byte BYTE_MAIL_OPTION_GET;
    public final byte BYTE_MAIL_OPTION_PAGEUP;
    public final byte BYTE_MAIL_OPTION_PAGEDOWN = (byte) 2;
    public final byte BYTE_MAIL_OPTION_DEL = (byte) 3;
    public final byte BYTE_MAIL_OPTION_SEE = (byte) 4;
    public final String STR_OPTION_MIAL_SEND;
    public short shtOneMenuMove;
    public short shtOneMenuRoll;
    public byte bytWordMaxH;
    private boolean blnRoleInfo = false;
    public byte bytPackMaxH;
    private byte bytChatShowMax;
    public byte bytTwoMenuState;
    public byte bytTaskTempMove;
    public byte bytTaskTempRoll;
    public byte bytMenuState;
    private short MENU_HEIGHT;
    private int intTimer;
    private short shtWordMoveY;
    public byte bytWordMoveStep;
    private byte bytSetWordMove;
    private byte bytSetWordY;
    private byte bytSetBoxY;
    public int scroll_OFF;
    private byte[] bytButtonType;
    public String strOneTitlestr = "";
    public String[] strTabTitlestr;
    private String strSeeTitlestr = "";
    public String strRectPropName = "";
    public String strinfo = "";
    public String strPetLevel = "";
    public String strPetStatge = "";
    public String strPetKind = "";
    public String strPetShow = "";
    public String strPetFeed = "";
    private String strRectPropKey = "";
    public int intColor;
    public String[] strOneMenuOption;
    public String strBakInfo;
    int mailPackageBoxX;
    int mailPackageBoxY;
    int mailPackageBoxW;
    private byte bytMailMove;
    public static final byte MOVE_MAIL_SUBTAB = 0;
    public static final byte MOVE_MAIL_PACKAGE = 1;
    public boolean blnNpcMailSendGood = false;
    public String strMailInput;
    public byte bytMailSubtabIndex;
    public final byte MAIL_SUBTAB_EQUIP;
    public final byte MAIL_SUBTAB_DRUG;
    public final byte MAIL_SUBTAB_MATERIAL = (byte) 2;
    public final byte MAIL_SUBTAB_MONEY = (byte) 3;
    public final byte MAIL_SUBTAB_SPECIAL = (byte) 4;
    public static String[] strMailType = null;
    public String[] strOneDescribeChar;
    public String[] strMailDescribeChar;
    public short shtMenuMoveLength;
    public byte bytFullMenuMoveLength;
    private byte[] setData;
    private byte bytSetChannel;
    private byte bytSetWeather;
    private byte bytSetShowRole;
    private byte bytSetShow;
    private byte bytShowName;
    private byte bytAutoWalk;
    private byte bytAutoSelect;
    public byte bytMoveType;
    public static final byte bytEquipType = 0;
    public static final byte bytPackBoxType = 1;
    public static final byte bytTabType = 2;
    public static final byte bytExpandPack = 3;
    private String getText;
    public byte bytRoleMove;
    public byte bytTagMove;
    public byte bytPetMove;
    public byte bytPropMove;
    public byte bytTagIndex;
    public byte bytTagPlace;
    public boolean blnIsPetList;
    public byte myDealBoxMove;
    public boolean blnIsTemp;
    public int intTempOptionId;
    public byte bytTempStep;
    public int intTempInputMax;
    public int intTempInputMin;
    private short shtNDialogY;
    public TwoDialogUI twodialog;
    public boolean blnTab = false;
    public boolean blnCursor = true;
    public boolean blnTabPet = false;
    public byte bytDelState;
    public int intShopMoney;
    public boolean blnSetChatNote;
    public boolean blnIsLoadOver;
    public Vector vTempVessel;
    public TabStyle tabStyleInstance = new TabStyle();
    private GridTable gridTable = null;
    public ScrollText scrolltext;
    private AnalysisWordContent WordContent;
    public static MenuUI menu = null;
    private byte[] bytsHelpNumber;
    private Image imgHelpIcon;
    private short shtIOMoveY;
    private String[] strLineFeed;
    private short personalShopSellPackageBoxX;
    private short personalShopSellPackageBoxY;
    private short personalShopSellPackageBoxW;
    private short personalShopSellPackageBoxH;
    private short personalShopPersonalPackageTabY;
    private short personalShopPersonalPackageTabW;
    private short personalShopPersonalPackageTabH;
    short personalPackageY;
    private final String PERSONAL_SHOP_OPERATE_PROMPT;
    public byte personalShopState;
    public final byte PERSONAL_SHOP_SELL;
    public final byte PERSONAL_SHOP_TAB;
    public final byte PERSONAL_SHOP_PERSONAL_PACKAGE = (byte) 2;
    public int personalShopPersonalPackageTabIndex;
    public PackageBox personalShopSellPackage;
    public final String SHOP_MENU_OPEN_SHOP;
    public final String MENU_CLOSE_SHOP;
    public final String SHOP_MENU_UPDATE_SHOP_NAME;
    public final String SHOP_MENU_LOOK_PROPERTY;
    public final String SHOP_MENU_GOODS_SHELVE;
    public final String SHOP_MENU_REPEAL_GOODS;
    public final String SHOP_MENU_UPDATE_PRICE;
    public final String SHOP_MENU_BUY;
    private short shtTitleW;
    private short shtTitleH;
    private short shtTitleX;
    private byte bytTitleType;
    private String[] strsTitle;
    private int[] IntTitleRgbColor;
    public byte bytTitleMove;
    public int color_ChatNote = 14514209;
    public static String[] STR_PACK_LIST = new String[]{"Trang bị", "Thuốc", "Chất liệu", "Nhiệm vụ", "Kho báu"};
    private final int PACK_INDEX_TYPE_EQUIP;
    private final int PACK_INDEX_TYPE_POTION;
    private final int PACK_INDEX_TYPE_MATERIAL;
    private final int PACK_INDEX_TYPE_TASK;
    private final int PACK_INDEX_TYPE_TREASURE;
    private GridTable GT_PACK_TABLE;
    private short GT_PACK_ROW = (short) 6;
    private short GT_PACK_COL = 1;
    private short[] GT_PACK_ROW_PERCENT;
    private short[] GT_PACK_COL_PERCENT;
    public RoleViewStyle rView;
    public static boolean bln_show_prope = false;
    short shtPetRectX;
    short ShtPetRectY;
    private int selectBgColor;
    private int tag_color;
    private int BORDER_PADDING;
    public QSprite[] inlay;
    private int sloginY;
    public int RoundHole;
    private int _offx;
    public PackageBox inlayHole;
    private int _framX;
    private int _framY;
    private int _framW;
    private int _framH;
    private int _holex;
    private int _holey;
    private int _holew;
    private int _holeh;
    private boolean ChooseJewel;
    public final byte InlayJewel;
    public final byte RemoveJewel;
    public static byte StrengthenState;
    public int frameBottomY;
    public static byte byteSkill;
    private int strboxwidth;
    public byte bytChatTeam;
    public byte bytChatTeam1NumIndex;
    public byte bytChatTeam2NumIndex;
    public int bytChatRelation;
    public int intRelation;
    public static byte bytChatMasterListIndexOne;
    public static byte bytChatMasterListIndexTwo;
    private String PartnerTile;
    private int _frameH;
    private GridTable fromTable;
    private boolean blnOpenMake;
    private int intMakeTime;
    private int intMakeTimeMax;
    private int rankingShowW;
    private int nameShowW;
    private int occShowW;
    private int valueShow;
    public byte menuID;
    public byte secondID;
    public byte MainmenuID;
    public byte mainIndex;
    public boolean Difference_Level;
    private boolean blnShow;
    short sht_Triangle_L_x;
    short sht_Triangle_R_x;
    short sht_Triangle_y;
    short sht_box_space;
    private QSprite[] triangle;
    private short triangle_widht;
    private short triangle_height;
    private short shtTaskMeedvsoY;
    private short shtTaskMeedvnsoY;
    private short shtTaskMeedvooY;
    short _downYoffset;
    int describeH;
    int boxX;
    int boxUpY;
    int boxDownY;
    int boxW;
    int boxH;
    int awardY;
    int selectY;
    int packageH;
    int socrollBoxY;
    int comperH;
    String strSelect;
    String strLook;
    String strTouchLook;
    int mustBoxY;
    int selectBoxY;
    private boolean CanPress;
    Hashtable _temp0;
    Hashtable _temp1;
    private short shtRoleRectX;
    private short shtRoleRectY;
    private short shtPropRectX;
    private short shtPropRectY;
    private short shtPropRectW;
    private short shtRectUpH;
    private short shtRectDownH;
    private short shtPropRect2X;
    private short shtPropRect2Y;
    private String[] PRORERTY_UI_EXPLAIN;
    private int Font_Show_Width;
    private short role_framex;
    private short role_framey;
    private short role_framew;
    private short role_frameh;
    private byte getstrsBuffLeng;
    private byte getstrsDeBuffLeng;
    private int pointY;
    private short shtPropUpRX;
    private int tag_equit_x;
    private int tag_spe_x;
    private int tag_data_x;
    private int tag_pet_x;
    private int tag_potion_x;
    private int tag_equit_y;
    private int tag_rect_w;
    private int tag_rect_h;
    GridCell tagEquitCell;
    private byte MoveUp;
    private byte MoveDown;
    private boolean blnGrille;
    private short shtGrilleX;
    private short shtGrilleY;
    private byte bytGrilleRow;
    private boolean blnWordRollType;
    private short shtWordRollX;
    private short shtWordRollStrW;
    private short shtWordRollShowW;
    public byte bytAddShopNumber;
    public String strShopName;
    private int hotkeyX;
    private int hotkeyY;
    public String[] strsFunction;
    public byte[] bytsFunction;
    public Hashtable HashSkillList;
    private Hashtable HashFunction;
    private byte[] FunctionPicID;
    private int boxNum;
    private int GetContainer;
    public static byte TabState;
    public PackageBox Shortcut;
    public int KeyNum;
    private byte bytFriendSendType;
    private byte bytMentorSendType;
    private boolean blnBusinessMore;
    public int intMaxNum;

    static {
        bytChatMasterListIndexOne = 0;
        bytChatMasterListIndexTwo = 0;
    }

    public static MenuUI getInstance() {
        if (menu == null) {
            new MenuUI();
        }
        return menu;
    }

    public MenuUI() {
        short[] sArray;
        this.STRING_OPTION_PK = "Tìm hiểu PK";
        this.STRING_OPTION_BACKNAME = "Thêm tên màu đen";
        this.STRING_OPTION_EQUIP = "Thiết bị sàng lọc";
        this.STRING_OPTION_SYSTEM_ANNAL = "Thông báo hệ thống";
        this.STRING_OPTION_NEED_STUFF = "Cần vật liệu";
        this.STRING_OPTION_LV = "Lớp";
        this.STRING_SAY_OPTIONAL = "Chọn mục thưởng";
        this.STRING_SAY_YOU = "Bạn";
        this.STRING_SAY_ALSO = "Vẫn";
        this.STRING_SAY_ATTAIN = "Sẽ nhận được";
        this.STRING_SAY_WEAR = "Độ bền:";
        this.STRING_TITLE_TASK = "Nhiệm vụ";
        this.STRING_CUE_NO_PEOPLE = "Không có người bạn nào lân cận";
        this.STRING_OPTION_STATUS = "Trạng thái ký tự";
        this.STRING_SAY_NAME = "Biệt hiệu:";
        this.STRING_SAY_LV = "Cấp độ:";
        this.STRING_SAY_CLAN = "Chủng tộc:";
        this.STRING_SAY_SEX = "Giới tính:";
        this.STRING_SAY_JOB = "Sự nghiệp:";
        this.STRING_SAY_UNION = "Băng đảng:";
        this.STRING_SAY_UNIONOCC = "Vị trí băng đảng:";
        this.STRING_SAY_EXPERIENCE = "Kinh nghiệm:";
        this.STRING_SAY_GOLD = "Tiền:";
        this.STRING_SAY_SPOUSE = "Vợ / chồng:";
        this.STRING_SAY_MASTER = "Sư Phụ:";
        this.STRING_SAY_APPRENTENCE = "Người học nghề:";
        this.STRING_SAY_AP = "Lực: ";
        this.STRING_SAY_AGILITY = "Nhanh nhẹn: ";
        this.STRING_SAY_BRAINS = "Trí lực: ";
        this.STRING_SAY_STAMINA = "Thể lực: ";
        this.STRING_SAY_HEART = "Tinh thần: ";
        this.STRING_SAY_LUCK = "May mắn: ";
        this.STRING_SAY_HP = "Máu:";
        this.STRING_SAY_MP = "Mana:";
        this.STRING_SAY_HARM = "Tấn công vật lý:";
        this.STRING_SAY_CRIT = "Bạo kích vật lý:";
        this.STRING_SAY_BUMB = "Bạo kích phép:";
        this.STRING_SAY_HIT = "Tỉ lệ chính xác phép:";
        this.STRING_SAY_HITRATE = "Tỉ lệ chính xác vật lý:";
        this.STRING_SAY_PHYSICS_MAX = "Tốc độ tấn công vật lý:";
        this.STRING_SAY_RECOVERY = "Phòng ngự:";
        this.STRING_SAY_MAGICATTRACK = "Tấn công phép:";
        this.STRING_SAY_JINK = "Vật lý né tránh:";
        this.STRING_SAY_TRANSIENT = "Không";
        this.STRING_SAY_ShadowRecovery = "Kháng Ám Ảnh: ";
        this.STRING_SAY_HolinessRecovery = "Kháng Thần Thánh: ";
        this.STRING_SAY_FireRecovery = "Kháng Hỏa: ";
        this.STRING_SAY_WaterRecovery = "Kháng Băng: ";
        this.STRING_SAY_EarthRecovery = "Kháng Lôi: ";
        this.STRING_SAY_SKILLENEMY = "Bị giết: ";
        this.STRING_SAY_SKILLNUM = "Số người giết: ";
        this.STRING_SAY_PKWIN = "Học hỏi từ chiến thắng: ";
        this.STRING_SAY_PKNUM = "Số lượng tham vấn: ";
        this.STRING_SAY_BASIC = "Thuộc tính cơ bản";
        this.STRING_SEE_ROLE_BASIC = "Dữ liệu ký tự";
        this.STRING_SAY_ATTACK = "Thuộc tính tấn công";
        this.STRING_OPTION_RECOVERY = "Thuộc tính phòng thủ";
        this.STRING_CUE_MONEY = "Tiền";
        this.STRING_CUE_EXP = "Kinh nghiệm";
        this.STRING_CUE_AWARD = "Phần thưởng:";
        this.STRING_CUE_INPUT_MONEY = "Nhập số lượng giao dịch";
        this.STRING_OPTION_SEE = "Kiểm tra";
        this.STRING_PUT_GOODS = "Đặt vào mặt hàng";
        this.STRING_DEL_GOODS = "Xóa mục";
        this.STRING_SEE_GOODS = "Chế độ xem mục";
        this.STRING_SEE_PROPERTY = "Thuộc tính mục";
        this.STRING_BACK = "Trở lại";
        this.STRING_OPTION_RELESE = "Phát hành";
        this.STRING_OPTION_USE = "Sử dụng";
        this.STRING_OPTION_SKILL_FRESH = "Điểm rửa";
        this.STRING_OPTION_SKILL_UP = "Nâng cấp kỹ năng";
        this.STRING_OPTION_KEY = "Đặt phím tắt";
        this.STRING_OPTION_SORT = "Điều hòa";
        this.STRING_OPTION_MOVE = "Điện thoại di động";
        this.STRING_OPTION_DICE = "Vứt vật phẩm";
        this.STRING_OPTION_PUP = "Trang trước";
        this.STRING_OPTION_PDOWN = "Trang tiếp theo";
        this.STRING_OPTION_WRITE = "Viết tin nhắn";
        this.STRING_OPTION_SEND_EMALL = "Gửi thư";
        this.STRING_MAIL_RECV_TYPE = "Belonging";
        this.STRING_MALL_RECV_TITLE = "Môn học";
        this.STRING_MALL_RECV_SEE_TITLE = "Chủ đề:";
        this.STRING_MALL_RECV_SEE_CONTENT = "Nội dung:";
        this.STRING_MAIL_RECV_ADDRESSER = "Người gửi";
        this.STRING_OPTION_EPURATE = "Tinh chỉnh";
        this.STRING_OPTION_MAKE = "Sản xuất";
        this.STRING_OPTION_ABNEGATE = "Từ bỏ";
        this.STRING_OPTION_SINGLE = "Trò chuyện riêng tư";
        this.STRING_OPTION_TROOP_INVITE = "Nhóm lời mời";
        this.STRING_OPTION_BLACKLIST = "Danh sách đen";
        this.STRING_OPTION_WANTED = "Vượt qua";
        this.STRING_OPTION_LOVER = "Đặt người yêu";
        this.STRING_OPTION_LOVEREXPLAIN = "Tuyên bố kết hôn";
        this.STRING_OPTION_GIVEUPHAND = "Chia tay";
        this.STRING_OPTINE_GIVEDIVORCE = "Buộc ly hôn";
        this.STRING_OPTION_TRANSMIT = "Chuyển khoản";
        this.STRING_OPTION_FRIEND = "Thêm làm bạn bè";
        this.STRING_OPTION_GONGHUI = "Thêm thành viên";
        this.STRING_OPTION_INVITE = "Mời để được giúp đỡ";
        this.STRING_OPTION_MANAGE = "Quản lý băng đảng";
        this.STRING_OPTION_INFROMATION = "Thông tin về băng đảng";
        this.STRING_OPINT_EXIT = "Thoát băng đảng";
        this.STRING_OPINT_PROMOTE = "Vị trí quảng cáo";
        this.STRING_OPINT_REDUCE = "Vị trí thấp hơn";
        this.STRING_OPINT_REJECT = "Đá ra băng đảng";
        this.STRING_OPINT_UPGRADE = "Nâng cấp băng đảng";
        this.STRING_OPTION_GIVEUP = "Từ bỏ";
        this.STRING_OPTION_LIVE = "Disengage";
        this.STRING_OPTION_DEL = "Xóa";
        this.STRING_OPTION_ADD = "Thêm";
        this.SRTING_OPTION_ADD_GOODS = "Thêm mục";
        this.STRING_OPTION_SEE_DETAILS = "Xem chi tiết";
        this.STRING_OPTION_ADD_FRIENDS = "Thêm một người bạn";
        this.STRING_OPTION_ADD_ENEMY = "Thêm kẻ thù";
        this.STRING_OPTION_ADD_SHIELD = "Thêm tên màu đen";
        this.STRING_OPTION_ADD_HEIMING = "Thêm tên màu đen";
        this.STRING_OPTIOM_ADD_CHOUREN = "Thêm làm kẻ thù";
        this.STRING_OPTION_SEVER_TROOP = "Rời khỏi nhóm";
        this.STRING_OPTION_APPOINT_AIDE = "Trợ lý cuộc hẹn";
        this.STRING_OPTION_CANCEL_AIDE = "Hủy trợ lý";
        this.STRING_OPTION_TROOP_PLACE = "Vị trí trình phát";
        this.STRING_OPTION_CESSION_CAPTAIN = "Đội trưởng chuyển nhượng";
        this.STRING_OPTION_DEL_MEMBER = "Xóa trình phát";
        this.STRING_OPTION_TALK = "Trò chuyện";
        this.STRING_OPTION_SENDSMS = "Gửi tin nhắn";
        this.STRING_OPTION_CLEARSMS = "Xóa thông tin";
        this.STRING_OPTION_REPLY = "Trả lời";
        this.STRING_OPTION_GOODS_SEE = "Xem các mục";
        this.STRING_OPTION_CHANNEL = "Bài phát biểu của kênh";
        this.STRING_OPTION_ACCEPT_TASK = "Chấp nhận tác vụ";
        this.STRING_OPTION_BUY = "Mua một đĩa đơn";
        this.STRING_OPTION_STILETTO = "Đục lỗ";
        this.STRING_OPTION_INLAY = "Khảm đá";
        this.STRING_OPTION_EXTRACT = "Tháo đá";
        this.STRING_OPTION_REPAIR = "Sửa chữa thiết bị";
        this.STRING_OPTION_COMPARE = "So sánh thiết bị";
        this.STRING_OPTION_ARM = "Trang bị";
        this.STRING_OPTION_UNLOAD = "Unload";
        this.STRING_OPTION_INLAY_BOOK = "Sách Mosaic";
        this.STRING_OPTION_SEAL = "Bỏ chặn";
        this.STRING_OPTION_SEE_KIND = "Xem thuộc tính";
        this.STRING_OPTION_SELL_OUTFIT = "Bán quần áo trắng";
        this.STRING_OPTION_BUY_ONE = "Mua hàng";
        this.STRING_OPTION_BUY_MORE = "Mua nhiều";
        this.STRING_OPTION_SELL_ONE = "Bán hàng";
        this.STRING_OPTION_SELL_MORE = "Bán nhiều";
        this.STRING_OPTION_STORAGE_OUT = "Đưa ra";
        this.STRING_OPTION_STORAGE_INPUT = "Tiền gửi";
        this.STRING_OPTION_PET_HIDE = "Bỏ vật nuôi";
        this.STRING_OPTION_ANSWER = "Trả lời";
        this.STRING_OPTION_GET = "Biên nhận";
        this.STRING_OPTION_PAGEUP = "Trang trước";
        this.STRING_OPTION_PAGEDOWN = "Trang tiếp theo";
        this.STRING_OPTION_SAVE = "Lưu";
        this.STRING_OPTION_UP_BAG = "Nâng cấp ba lô";
        this.STRING_OPTION_WEIGHTY = "Áo giáp nặng";
        this.STRING_OPTION_SPAR = "Áo giáp nhẹ";
        this.STRING_OPTION_CLOTH = "Vải";
        this.STRING_OPTION_BISHOU = "Dagger";
        this.STRING_OPTION_CHUIZI = "Hammer";
        this.STRING_OPTION_FAZHANG = "Nhân viên";
        this.STRING_OPTION_GONGJIAN = "Cung và mũi tên";
        this.STRING_OPTION_JIAN = "Thanh kiếm";
        this.STRING_OPTION_SIGN = "、";
        this.STRING_OPTION_TILTED = "/";
        this.STRING_OPTION_TO = "-";
        this.STRING_CUE_NODEAL = "Không thể giao dịch mặt hàng này";
        this.BYTE_MAIL_OPTION_GET = 0;
        this.BYTE_MAIL_OPTION_PAGEUP = 1;
        this.STR_OPTION_MIAL_SEND = "Gửi thư";
        this.MAIL_SUBTAB_EQUIP = 0;
        this.MAIL_SUBTAB_DRUG = 1;
        this.PERSONAL_SHOP_OPERATE_PROMPT = "Nhấn phím ## để chuyển thẻ trang";
        this.PERSONAL_SHOP_SELL = 0;
        this.PERSONAL_SHOP_TAB = 1;
        this.SHOP_MENU_OPEN_SHOP = "Mở cửa hàng";
        this.MENU_CLOSE_SHOP = "Đóng cửa hàng";
        this.SHOP_MENU_UPDATE_SHOP_NAME = "Thay đổi tên cửa hàng";
        this.SHOP_MENU_LOOK_PROPERTY = "Xem thuộc tính";
        this.SHOP_MENU_GOODS_SHELVE = "Kệ hàng";
        this.SHOP_MENU_REPEAL_GOODS = "Thu hồi hàng hóa";
        this.SHOP_MENU_UPDATE_PRICE = "Thay đổi giá";
        this.SHOP_MENU_BUY = "Mua hàng";
        this.PACK_INDEX_TYPE_EQUIP = 0;
        this.PACK_INDEX_TYPE_POTION = 1;
        this.PACK_INDEX_TYPE_MATERIAL = 2;
        this.PACK_INDEX_TYPE_TASK = 3;
        this.PACK_INDEX_TYPE_TREASURE = 4;
        if (Macro.SCREEN_HEIGHT <= 250) {
            short[] sArray2 = new short[6];
            sArray2[0] = 10;
            sArray2[1] = 50;
            sArray2[2] = 15;
            sArray2[3] = 15;
            sArray = sArray2;
            sArray2[5] = 10;
        } else {
            short[] sArray3 = new short[6];
            sArray3[0] = 10;
            sArray3[1] = 40;
            sArray3[2] = 10;
            sArray3[3] = 25;
            sArray3[4] = 5;
            sArray = sArray3;
            sArray3[5] = 10;
        }
        this.GT_PACK_ROW_PERCENT = sArray;
        this.GT_PACK_COL_PERCENT = new short[]{1};
        this.shtPetRectX = (short) ((Macro.SCREEN_WIDTH - 76) / 5);
        this.ShtPetRectY = (short) (Macro.SCREEN_HEIGHT / 2 - 18);
        this.selectBgColor = 16739958;
        this.tag_color = 5818877;
        this.BORDER_PADDING = 4;
        this.RoundHole = 22;
        this._offx = 3;
        this.InlayJewel = 1;
        this.RemoveJewel = (byte) 2;
        this.strboxwidth = Macro.FONTHEIGHT + 6;
        this.bytChatTeam1NumIndex = 0;
        this.bytChatTeam2NumIndex = 0;
        this.Difference_Level = true;
        this.blnShow = false;
        this.triangle_widht = (short) 9;
        this.triangle_height = (short) 15;
        this._downYoffset = 0;
        this.strSelect = "Tùy chọn:";
        this.strLook = "Nhấn 0 để xem các thuộc tính";
        this.strTouchLook = "Nhấp đúp vào mục để xem các thuộc tính";
        this.CanPress = false;
        this.MoveUp = (byte) 15;
        this.MoveDown = (byte) 14;
        this.strShopName = "";
        this.bytFriendSendType = 0;
        this.bytMentorSendType = 0;
        this.blnBusinessMore = false;
        this.intMaxNum = 1;
        menu = this;
    }

    public byte getState() {
        return this.bytMenuState;
    }

    public void isThis(boolean bl) {
        this.blnIsThis = bl;
    }

    public void init() {
        this.intColor = 0;
        DCanvas.getInstance().setFullScreen(true);
        this.MENU_HEIGHT = (short) (Macro.SCREEN_HEIGHT - 18);
        this.bytButtonType = new byte[3];
    }

    public void clean() {
        menu = null;
        this.imgHelpIcon = null;
        this.bytButtonType = null;
        this.strOneDescribeChar = null;
        this.fromTable = null;
        this.PRORERTY_UI_EXPLAIN = null;
        if (SeeDetail.getInstance() != null) {
            SeeDetail.getInstance().clear();
        }
        Param.getInstance().hPackage = null;
        Param.getInstance().hOtherPackageEquip = null;
        Param.getInstance().personalPackage = null;
        GameUI.getInstance().intSendMenuId = -1;
        Param.getInstance().vMenuMemory = null;
        Param.getInstance().vCommonList = null;
        Param.getInstance().imgTaskThru = null;
        Param.getInstance().IntRgbColor = null;
        Param.getInstance().vInfoContent = null;
        Param.getInstance().hShopPackage = null;
        Param.getInstance().imgEmallSystem = null;
        if (this.bytMenuState != 9) {
            Param.getInstance().hImgObject = null;
        }
        if (this.WordContent != null) {
            this.WordContent = null;
        }
        Param.getInstance().strTempTitlestr = null;
        DCanvas.getInstance().setNetLoad(false);
        this.tabStyleInstance.cleanTabStyle();
        PopupDialog.infoContent = null;
        if (Param.getInstance().playerColne != null) {
            Param.getInstance().playerColne = null;
        }
        if (Param.getInstance().partner != null) {
            Param.getInstance().partner = null;
        }
    }

    public void setButton(int n, int n2, int n3) {
        this.bytButtonType[0] = (byte) n;
        this.bytButtonType[1] = (byte) n2;
        this.bytButtonType[2] = (byte) n3;
    }

    public void setBackSystem() {
        if (this.bytMenuState == 42 || this.bytMenuState == -22 || GameUI.blnCloseMenuAfterChargeMenu) {
            GameUI.blnCloseMenuAfterChargeMenu = false;
            GameControl.getInstance().delUIbase(9);
            GameControl.getInstance().delUIbase(3);
        } else {
            GameControl.getInstance().delUIbase(this.bytDelState);
        }
        if (Param.getInstance().vOptionPlace != null && !Param.getInstance().vOptionPlace.isEmpty()) {
            GameControl.getInstance().CreateState((byte) 4);
            SystemMenuUI.getInstance().setBackOldState();
        }
        if (DCanvas.getInstance().UDialog == null) {
            Param.getInstance().bytNpcDataType = 0;
            DCanvas.getInstance().buttonRightFlash = 0;
        }
    }

    public void setState(byte by, String string) {
        this.strOneTitlestr = string;
        this.bytWordMaxH = Macro.bytMaxFullRow;
        this.blnGrille = false;
        DCanvas.getInstance().blnSpoolr = false;
        this.bytTwoMenuState = 0;
        this.shtIOMoveY = 0;
        DCanvas.getInstance().buttonLeftFlash = 0;
        DCanvas.getInstance().buttonRightFlash = 0;
        StringManager.resetWordMoveTimer();
        this.setButton(1, 100, 2);
        switch (by) {
            case -26: {
                String string2 = StringManager.displaceNpcTalk(Param.getInstance().MALL_STR_RECORD);
                this.strOneDescribeChar = StringManager.wenZi(string2, StringManager.getNewLineW() - 12);
                this.setGrrdTableFrom();
                this.scrolltext = new ScrollText(string2, this.fromTable.getCell(2, 1).getCellX() + 12, 42, this.fromTable.getCell(2, 1).getCellW() - 24, Macro.SCREEN_HEIGHT - 36 - 38, 6896918, (byte) 0);
                this.setButton(1, 100, 2);
                break;
            }
            case -25: {
                Macro.hNetList = new Hashtable();
                String string3 = "Nhấn OK để bắt đầu kiểm tra kết nối mạng\n" + (Rms.blnSelectHttp ? "\nHTTP\n" : "\nSOCKET\n");
                this.strOneDescribeChar = StringManager.wenZi(string3, StringManager.getNewLineW() - 12);
                this.setGrrdTableFrom();
                this.scrolltext = new ScrollText(string3, this.fromTable.getCell(2, 1).getCellX() + 12, 42, this.fromTable.getCell(2, 1).getCellW() - 24, Macro.SCREEN_HEIGHT - 36 - 38, 255, (byte) 0);
                this.setButton(1, 0, 2);
                break;
            }
            case -5: {
                this.setEquipImage(Param.getInstance().hPackageEquip);
                this.bytMoveType = 0;
                this.bytRoleMove = 0;
                this.setRoleSee();
                this.setInfoContent(Param.getInstance().hPackageEquip, this.bytRoleMove);
                break;
            }
            case -7: {
                this.initProperty();
                break;
            }
            case -6: {
                Param.getInstance().playerColne = ((ORPlayer) Param.getInstance().oSelectRole).colnePlayerBaseData();
                Param.getInstance().playerColne.pushTask((byte) 0, Param.getInstance().playerColne.bytDirection, (byte) 1);
                this.setRoleAttribute(4);
                this.rView = new RoleViewStyle((short) (Macro.SCREEN_WIDTH - 115 >> 1), (short) 40, true, true, true, true);
                break;
            }
            case 10:
            case 33: {
                if (by == 10) {
                    this.strTabTitlestr = STR_PACK_LIST;
                } else if (by == 33) {
                    this.strTabTitlestr = IDefines.MENU_UI_PET_TAB_TITLE;
                    this.blnTabPet = true;
                }
                this.initPack();
                this.setEquipImage(this.blnTabPet ? Param.getInstance().hPackage : Param.getInstance().hPackageEquip);
                this.bytMoveType = Param.getInstance().EquipIndex != -1 ? (byte) 1 : (byte) 2;
                this.bytRoleMove = 0;
                this.bytPropMove = 0;
                this.setRoleEquip();
                this.blnTab = true;
                Param.getInstance().playerColne = this.blnTabPet ? ORPMe.ME.colnePlayerBaseData() : ORPMe.ME.colnePlayerBaseData();
                Param.getInstance().playerColne.pushTask((byte) 0, Param.getInstance().playerColne.bytDirection);
                Param.getInstance().playerColne.setRoleAnimation(true);
                break;
            }
            case 11: {
                this.initInlay();
                break;
            }
            case -37: {
                break;
            }
            case 60: {
                this.initSet();
                break;
            }
            case -81: {
                this.initKey();
                this.initShortcut();
                break;
            }
            case -82: {
                this.initMoveKey();
                break;
            }
            case 42: {
                this.clearMove();
                this.bytTitleMove = Param.getInstance().bytChatNoteType;
                this.tabStyleInstance.initTabStyle((short) 4, (short) 38, (short) IDefines.GLOBAL_UI_MAIN_TAB_WIDTH, (short) 32, IDefines.CHAT_TITLE_NAME, this.bytTitleMove);
                this.tabStyleInstance.TabFocuse = true;
                this.setGrrdTableFrom();
                this.setButton(1, 4, 2);
                this.bytWordMaxH = (byte) ((Macro.SCREEN_HEIGHT - this.tabStyleInstance.GettabBottomY - 31 - Macro.FONTHEIGHT) / Macro.FONTHEIGHT);
                this.shtWordMoveY = 0;
                this.setTitlePlace((byte) 8);
                Param.getInstance().IntRgbColor = DrawBase.getRGB(StringManager.getNewLineW(), Macro.FONTHEIGHT, 1144385017);
                break;
            }
            case -49: {
                this.initMacroChat();
                break;
            }
            case 61: {
                this.bytTitleMove = 0;
                this.iniSkillOccBefore();
                this.iniSkillOccLater();
                this.iniSkillBook();
                this.strsTitle = IDefines.SKILL_FIGHT_TAB_TITLE;
                this.setTitlePlace((byte) this.strsTitle.length);
                this.initTransVocationSkillList(this.bytTitleMove);
                this.tabStyleInstance.initTabStyle((short) 4, (short) 38, (short) IDefines.GLOBAL_UI_MAIN_TAB_WIDTH, (short) 32, this.strsTitle, this.bytTitleMove, Macro.SCREEN_WIDTH, (short) IDefines.MENU_UI_SKILL_FIGHT_TAB_FRAME_HEIGHT);
                this.setGrrdTableFrom();
                this.bytWordMaxH = (byte) ((IDefines.MENU_UI_SKILL_FIGHT_TAB_FRAME_HEIGHT - 25) / Macro.FONTHEIGHT);
                break;
            }
            case 64: {
                this.clearTaskInfo();
                this.bytTitleMove = 0;
                this.setLifeSkillType();
                this.strsTitle = new String[]{"Kỹ năng 1", "Kỹ năng 2"};
                this.tabStyleInstance.initTabStyle((short) 4, (short) 38, (short) IDefines.GLOBAL_UI_MAIN_TAB_WIDTH, (short) 32, this.strsTitle, byteSkill);
                this.tabStyleInstance.TabFocuse = true;
                this.setGrrdTableFrom();
                this.bytWordMaxH = (byte) ((Macro.SCREEN_HEIGHT - this.tabStyleInstance.GettabBottomY - 31 - Macro.FONTHEIGHT) / Macro.FONTHEIGHT);
                break;
            }
            case 65: {
                DCanvas.getInstance().setNetLoad(true);
                NetSend.getInstance().sendFrameEquip((byte) 1, (byte) 0, 0);
                Param.getInstance().packageBoxMaxNum = (byte) 16;
                byte by2 = (byte) (this.gridTable.getCell((int) 2, (int) 1).cell_y + Macro.FONTHEIGHT + this.strboxwidth);
                this.setPropRect(by2, this.shtRectDownH);
                this.setEquipImage(Param.getInstance().hPackageEquip);
                break;
            }
            case -113: {
                this.bytWordMaxH = (byte) (Macro.bytMaxFullRow - 1);
                break;
            }
            case 63: {
                this.clearTaskInfo();
                if (Param.getInstance().imgTaskThru == null) {
                    Param param = Param.getInstance();
                    ImageManager.getInstance();
                    param.imgTaskThru = ImageManager.CreateImage("done", "ui");
                }
                this.strsTitle = IDefines.TASK_UI_TITLE_NAME;
                this.setTitlePlace((byte) this.strsTitle.length);
                this.setTaskType();
                this.tabStyleInstance.initTabStyle((short) 4, (short) 38, (short) IDefines.GLOBAL_UI_MAIN_TAB_WIDTH, (short) 32, this.strsTitle, this.bytTitleMove);
                this.tabStyleInstance.TabFocuse = true;
                this.setGrrdTableFrom();
                this.bytWordMaxH = (byte) ((Macro.SCREEN_HEIGHT - this.tabStyleInstance.GettabBottomY - 31 - Macro.FONTHEIGHT) / Macro.FONTHEIGHT);
                this.shtWordMoveY = 0;
                break;
            }
            case 34: {
                this.initFaction(Param.getInstance().vCommonList);
                break;
            }
            case -31: {
                this.clearMove();
                this.blnIsLoadOver = false;
                this.bytChatRelation = 0;
                this.bytTitleMove = 0;
                this.strsTitle = IDefines.FRIEND_UI_TITLE;
                this.tabStyleInstance.initTabStyle((short) 4, (short) 38, (short) IDefines.GLOBAL_UI_MAIN_TAB_WIDTH, (short) 32, this.strsTitle, (byte) this.bytChatRelation);
                this.tabStyleInstance.TabFocuse = true;
                this.setGrrdTableFrom();
                this.bytWordMaxH = (byte) ((Macro.SCREEN_HEIGHT - this.tabStyleInstance.GettabBottomY - 31 - Macro.FONTHEIGHT) / Macro.FONTHEIGHT);
                this.bytFriendSendType = 1;
                DCanvas.getInstance().setNetLoad(true);
                NetSend.getInstance().sendHailFellow((byte) 1, (byte) 1, "");
                Param.getInstance().bytNpcDataType = (byte) 7;
                this.shtWordMoveY = 0;
                this.setButton(1, 4, 2);
                break;
            }
            case -50: {
                this.LoadPartnerDate();
                break;
            }
            case -48: {
                this.clearMove();
                this.blnIsLoadOver = false;
                this.bytTitleMove = 0;
                DCanvas.getInstance().setNetLoad(true);
                NetSend.getInstance().sendShiTu_Option((byte) 1, -1);
                this.strsTitle = ORPMe.ME.bytApprentice == 0 ? IDefines.MASTER_UI_RELATION : (ORPMe.ME.bytApprentice == 1 ? IDefines.MASTER_UI_RELATION_T : IDefines.MASTER_UI_RELATION);
                this.tabStyleInstance.initTabStyle((short) 4, (short) 38, (short) IDefines.GLOBAL_UI_MAIN_TAB_WIDTH, (short) 32, this.strsTitle, (byte) this.intRelation);
                this.tabStyleInstance.TabFocuse = true;
                this.setGrrdTableFrom();
                this.bytWordMaxH = (byte) ((Macro.SCREEN_HEIGHT - this.tabStyleInstance.GettabBottomY - 31 - Macro.FONTHEIGHT) / Macro.FONTHEIGHT);
                this.bytMentorSendType = 1;
                break;
            }
            case -33:
            case -32: {
                this.clearMove();
                this.blnIsLoadOver = false;
                this.strsTitle = new String[]{"Danh sách", "Danh sách đen", "Kẻ thù"};
                if (by == -32) {
                    this.bytChatRelation = 1;
                    this.tabStyleInstance.initTabStyle((short) 4, (short) 38, (short) IDefines.GLOBAL_UI_MAIN_TAB_WIDTH, (short) 32, this.strsTitle, (byte) this.bytChatRelation);
                    DCanvas.getInstance().setNetLoad(true);
                    this.setGrrdTableFrom();
                    this.bytWordMaxH = (byte) ((Macro.SCREEN_HEIGHT - this.tabStyleInstance.GettabBottomY - 31 - Macro.FONTHEIGHT) / Macro.FONTHEIGHT);
                    NetSend.getInstance().sendHailFellow((byte) 1, (byte) 2, "");
                }
                if (by == -33) {
                    this.bytChatRelation = 2;
                    this.tabStyleInstance.initTabStyle((short) 4, (short) 38, (short) IDefines.GLOBAL_UI_MAIN_TAB_WIDTH, (short) 32, this.strsTitle, (byte) this.bytChatRelation);
                    this.setGrrdTableFrom();
                    this.bytWordMaxH = (byte) ((Macro.SCREEN_HEIGHT - this.tabStyleInstance.GettabBottomY - 31 - Macro.FONTHEIGHT) / Macro.FONTHEIGHT);
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendHailFellow((byte) 1, (byte) 3, "");
                }
                Param.getInstance().bytNpcDataType = (byte) 7;
                this.tabStyleInstance.TabFocuse = true;
                this.setButton(1, 4, 2);
                break;
            }
            case 35: {
                this.strsTitle = IDefines.TEAM_UI_TITLE_NAME;
                this.setRoleList(by, Param.getInstance().vTeam);
                this.tabStyleInstance.initTabStyle((short) 4, (short) 38, (short) IDefines.GLOBAL_UI_MAIN_TAB_WIDTH, (short) 32, this.strsTitle, this.bytChatTeam);
                this.tabStyleInstance.TabFocuse = true;
                this.setGrrdTableFrom();
                this.bytWordMaxH = (byte) ((Macro.SCREEN_HEIGHT - this.tabStyleInstance.GettabBottomY - 31 - Macro.FONTHEIGHT) / Macro.FONTHEIGHT);
                break;
            }
            case -34: {
                Param.getInstance().vInfoContent = null;
                this.setFBCD();
                break;
            }
            case -38: {
                Param.getInstance().IntRgbColor = DrawBase.getRGB(StringManager.getNewLineW(), Macro.FONTHEIGHT, 1144385017);
                this.setAboutRole();
                if (Param.getInstance().vCommonList == null) {
                    break;
                }
                this.setButton(1, 4, 2);
                break;
            }
            case -10: {
                break;
            }
            case -17: {
                break;
            }
            case -15: {
                String string4 = "Bạn có thể mở rộng dung lượng của từng gói riêng biệt và phí cho nhiều lần mở rộng sẽ tăng dần";
                this.strOneDescribeChar = StringManager.wenZi(string4, StringManager.getNewLineW() - 12);
                this.setGrrdTableFrom();
                Param.getInstance().MALL_SHOW_LIST = Param.MALL_EXTEND_LIST;
                this.shtMenuMoveLength = (short) Param.getInstance().MALL_SHOW_LIST.size();
                this.setButton(1, 0, 2);
                break;
            }
            case -16: {
                this.strOneTitlestr = "Nạp điểm";
                String string5 = ((String[]) Param.getInstance().MALL_CHANNEL_VLIST.elementAt(Integer.parseInt(string)))[5];
                this.strOneDescribeChar = StringManager.wenZi(string5, StringManager.getNewLineW() - 12);
                this.setGrrdTableFrom();
                Param.getInstance().MALL_SHOW_LIST = new Vector(2, 2);
                String string6 = ((String[]) Param.getInstance().MALL_CHANNEL_VLIST.elementAt(Integer.parseInt(string)))[2];
                int n = 0;
                while (n < Param.getInstance().MALL_CHANNEL_SUB_VLIST.size()) {
                    String string7 = ((String[]) Param.getInstance().MALL_CHANNEL_SUB_VLIST.elementAt(n))[2];
                    if (string6.equals(string7)) {
                        Param.getInstance().MALL_SHOW_LIST.addElement(Param.getInstance().MALL_CHANNEL_SUB_VLIST.elementAt(n));
                    }
                    ++n;
                }
                this.shtMenuMoveLength = (short) Param.getInstance().MALL_SHOW_LIST.size();
                this.setButton(1, 0, 2);
                break;
            }
            case -51: {
                this.initRankList(string);
                break;
            }
            case -18: {
                String string8 = ((String[]) Param.getInstance().MALL_SHOW_LIST.elementAt(MenuUI.getInstance().getOneMove()))[5];
                this.strOneDescribeChar = StringManager.wenZi(string8, StringManager.getNewLineW() - 12);
                this.setGrrdTableFrom();
                this.setButton(1, 0, 2);
                break;
            }
            case -19: {
                FormDes.getInstance().showForm((byte) -19);
                break;
            }
            case -20: {
                String string9 = ((String[]) Param.getInstance().MALL_SHOW_LIST.elementAt(MenuUI.getInstance().getOneMove()))[4];
                String string10 = "Vui lòng xác nhận thông tin nạp tiền của bạn \n giá trị giao diện：" + string9 + "\nsố seri：" + Param.getInstance().MALL_CARD_NUMBER + "\nsố thẻ：" + Param.getInstance().MALL_PASSWORD_NUMBER + "\nMẹo: Nếu số lượng thẻ nạp bạn chọn không khớp với mệnh giá thẻ nạp bạn đang giữ thì giao dịch sẽ không thành công và thẻ nạp có thể mất giá trị";
                this.strOneDescribeChar = StringManager.wenZi(string10, StringManager.getNewLineW() - 12);
                this.setGrrdTableFrom();
                this.setButton(1, 0, 2);
                break;
            }
            case -24: {
                String string11 = "Lưu ý: Bạn chỉ có thể nạp tiền vào tài khoản trên cùng một máy chủ, được xác định là" + Param.getInstance().MALL_FOR_NAME + " Nạp tiền?";
                this.strOneDescribeChar = StringManager.wenZi(string11, StringManager.getNewLineW() - 12);
                this.setGrrdTableFrom();
                this.setButton(1, 0, 2);
                break;
            }
            case -22: {
                String string12 = "Do sự cố đường truyền mạng nên quá trình xác minh thông tin nạp tiền của bạn sẽ bị chậm trong vài phút, bạn có thể quay lại game để chơi trước, sau khi nạp tiền chúng tôi sẽ thông báo kịp thời qua e-mail, các bạn chú ý kiểm tra nhé Email. Cảm ơn các bạn đã thông cảm và ủng hộ, chúc các bạn chơi game vui vẻ.";
                this.strOneDescribeChar = StringManager.wenZi(string12, StringManager.getNewLineW() - 12);
                this.setGrrdTableFrom();
                this.setButton(1, 0, 2);
                break;
            }
            case -11: {
                this.strsTitle = Param.MALL_TAB_LIST;
                this.tabStyleInstance.initTabStyle((short) 4, (short) 38, (short) IDefines.GLOBAL_UI_MAIN_TAB_WIDTH, (short) 32, this.strsTitle, (byte) 0);
                this.tabStyleInstance.TabFocuse = true;
                this.setGrrdTableFrom();
                int n = this.fromTable.getCell(2, 1).getCellW() - 12 - 16;
                int n2 = Macro.SCREEN_HEIGHT - 31 - (this.tabStyleInstance.GettabBottomY + 2) - 2;
                int n3 = (n2 >> 1) - (Macro.FONTHEIGHT >> 1);
                Param.getInstance().personalPackage = new PackageBox(14, this.tabStyleInstance.GettabBottomY + 8, n, n3, Param.MALL_BOX_NUM[0], false);
                this.setShangChengRes(0);
                break;
            }
            case 9: {
                this.setTradeEquip();
                this.bytMoveType = 1;
                this.shtOneMenuMove = 0;
                this.blnCursor = false;
                this.myDealBoxMove = (byte) -1;
                this.setButton(1, 4, 2);
                break;
            }
            case -43: {
                if (Param.getInstance().npcShopBarPackage == null) {
                    Param.getInstance().npcShopBarPackage = new PackageBox(4, 50, IDefines.GLOBAL_UI_MAIN_TAB_WIDTH, (Macro.SCREEN_HEIGHT >> 1) - Param.getInstance().npcShopBarTabStyle.GettabBottomY - 24, 16, true);
                    Param.getInstance().npcShopBarPackage.setBoxIndex(Param.getInstance().IndexNpcShopBarTabStyle, true);
                    Param.popupDialogInstance.setShow(true);
                    Param.getInstance().npcShopBarPackage.setPopupDialog();
                }
                if (Param.getInstance().npcShopOursTabArray != null && Param.getInstance().npcShopOursTabArray[0] != null) {
                    this.tabStyleInstance.initTabStyle((short) 12, (short) (Macro.SCREEN_HEIGHT >> 1), (short) (Macro.SCREEN_WIDTH - 24), (short) 32, Param.getInstance().npcShopOursTabArray, (byte) 0);
                    this.tabStyleInstance.TabFocuse = false;
                    this.tabStyleInstance.resetTabStyle((byte) Param.getInstance().npcBarSelectIndex);
                    this.setNpcStorageButton((byte) 10);
                    if (Param.getInstance().personalPackage == null) {
                        Param.getInstance().personalPackage = new PackageBox(4, this.tabStyleInstance.GettabBottomY, IDefines.GLOBAL_UI_MAIN_TAB_WIDTH, Macro.SCREEN_HEIGHT - 38 - this.tabStyleInstance.GettabBottomY - 6, 16, !Param.getInstance().npcShopBarPackage.getFocal());
                    }
                }
                this.setNpcStorageSelect();
                Param.popupDialogInstance.setShow(false);
                break;
            }
            case -39: {
                if (Param.getInstance().npcShopBarPackage == null) {
                    Param.getInstance().npcShopBarTabStyle.initTabStyle((short) 12, (short) 38, (short) (Macro.SCREEN_WIDTH - 24), (short) 32, Param.getInstance().npcShopBarTabArray, (byte) 0);
                    Param.getInstance().npcShopBarTabStyle.TabFocuse = false;
                    Param.getInstance().npcShopBarPackage = new PackageBox(4, Param.getInstance().npcShopBarTabStyle.GettabBottomY, IDefines.GLOBAL_UI_MAIN_TAB_WIDTH, (Macro.SCREEN_HEIGHT >> 1) - Param.getInstance().npcShopBarTabStyle.GettabBottomY, 16, true);
                    Param.getInstance().npcShopBarPackage.setBoxIndex(Param.getInstance().IndexNpcShopBarTabStyle, true);
                    Param.popupDialogInstance.setShow(true);
                    Param.getInstance().npcShopBarPackage.setPopupDialog();
                }
                this.tabStyleInstance.initTabStyle((short) 12, (short) (Macro.SCREEN_HEIGHT >> 1), (short) (Macro.SCREEN_WIDTH - 24), (short) 32, Param.getInstance().npcShopOursTabArray, (byte) 0);
                this.tabStyleInstance.TabFocuse = false;
                this.tabStyleInstance.resetTabStyle((byte) Param.getInstance().npcBarSelectIndex);
                if (Param.getInstance().personalPackage == null) {
                    Param.getInstance().personalPackage = new PackageBox(4, (Macro.SCREEN_HEIGHT >> 1) + 32, IDefines.GLOBAL_UI_MAIN_TAB_WIDTH, Macro.SCREEN_HEIGHT - 38 - this.tabStyleInstance.GettabBottomY, 16, !Param.getInstance().npcShopBarPackage.getFocal());
                } else {
                    Param.getInstance().personalPackage.setFocal(!Param.getInstance().npcShopBarPackage.getFocal());
                    if (Param.getInstance().personalPackage.getFocal()) {
                        this.setNpcBusinessButton((byte) 3);
                    } else if (Param.getInstance().npcShopBarPackage.getFocal()) {
                        this.setNpcBusinessButton((byte) 2);
                    }
                }
                Param.popupDialogInstance.setShow(false);
                break;
            }
            case 21:
            case 22:
            case 36:
            case 37:
            case 38:
            case 39: {
                this.initPack();
                break;
            }
            case -72:
            case -35: {
                this.initPersonalShop();
                if (by != -72) {
                    break;
                }
                this.personalShopSellPackage.setFocal(true);
                Param.getInstance().personalPackage.setFocal(false);
                this.personalShopState = 0;
                break;
            }
            case 58: {
                this.setPropRect((short) (Macro.FONTHEIGHT * 2 + 9 + 2 + Macro.shtRectHeight), Macro.shtRectHeight);
                this.bytMoveType = 1;
                this.bytPetMove = 0;
                this.bytPropMove = 0;
                break;
            }
            case -1: {
                break;
            }
            case -44: {
                this.setWordMove();
                if (Param.getInstance().vCommonList != null && !Param.getInstance().vCommonList.isEmpty()) {
                    this.shtMenuMoveLength = (short) Param.getInstance().vCommonList.size();
                    this.setButton(1, 4, 2);
                }
                Param.getInstance().bytNpcDataType = (byte) 3;
                break;
            }
            case -36: {
                if (Param.getInstance().imgEmallSystem == null) {
                    Param param = Param.getInstance();
                    ImageManager.getInstance();
                    param.imgEmallSystem = ImageManager.CreateImage("sys_icon", "ui");
                }
                this.setMailList((short) 0);
                int n = Macro.SCREEN_HEIGHT - 38 - 31 - 12 - Macro.FONTHEIGHT + 4;
                this.bytWordMaxH = (byte) (n / Macro.FONTHEIGHT);
                break;
            }
            case 31: {
                this.mailPackageBoxX = (Macro.SCREEN_WIDTH - Macro.PROPRECT_WIDTH * 24) / 2;
                this.mailPackageBoxY = 79;
                this.mailPackageBoxW = 192;
                this.strsTitle = this.strOneMenuOption;
                if (this.bytMailSubtabIndex == 0) {
                    this.tabStyleInstance.initTabStyle((short) 8, (short) 38, (short) (IDefines.GLOBAL_UI_MAIN_TAB_WIDTH - 8), (short) 32, this.strsTitle, this.bytMailSubtabIndex);
                }
                this.tabStyleInstance.TabFocuse = true;
                this.bytMailMove = 0;
                Param.getInstance().personalPackage = new PackageBox(this.mailPackageBoxX, this.mailPackageBoxY, this.mailPackageBoxW, 22 * (Param.getInstance().packageBoxMaxNum / 8 + 1), Param.getInstance().packageBoxMaxNum, false);
                break;
            }
            case -47: {
                this.strsTitle = new String[]{"Hộp thư đến"};
                strMailType = new String[]{"友", "会", "系", "陌"};
                this.tabStyleInstance.initTabStyle((short) 8, (short) 38, (short) (IDefines.GLOBAL_UI_MAIN_TAB_WIDTH - 8), (short) 32, this.strsTitle, this.bytMailSubtabIndex);
                break;
            }
            case -90: {
                new FullInfo("Bản đồ", 0, by);
                FullInfo.getInstance().setFullRectMenu(Macro.strAreaMapInfo);
                FullInfo.getInstance().setButton(1, 100, 2);
                break;
            }
            case -91: {
                if (Param.getInstance().htAreaMapNpcList == null) {
                    Param.getInstance().htAreaMapNpcList = new Vector(1);
                    Param.getInstance().htAreaMapNpcIDList = new Vector(1);
                }
                this.initOptionList(Param.getInstance().htAreaMapNpcList, 0, 38);
                if (Param.getInstance().htAreaMapNpcList.size() <= 0) {
                    break;
                }
                this.setButton(1, 26, 2);
            }
        }
        this.bytMenuState = by;
    }

    public void setShangChengRes(int n) {
        Hashtable hashtable;
        int n2;
        if (Param.getInstance().hMallPackage != null && (n2 = Param.getInstance().hMallPackage.size()) > 0 && (hashtable = (Hashtable) Param.getInstance().hMallPackage.get(new Integer(n))) != null) {
            Enumeration enumeration = hashtable.elements();
            while (enumeration.hasMoreElements()) {
                PackageObject packageObject = (PackageObject) enumeration.nextElement();
                String string = packageObject.strName;
                short s = packageObject.shtIcon;
                GameUI.getInstance().addObjectImg(s, "prop");
            }
        }
    }

    private void setSkill() {
        if (this.bytTitleMove == 0 || this.bytTitleMove == 1) {
            this.initTransVocationSkillList(this.bytTitleMove);
        } else if (this.bytTitleMove == 2) {
            this.initSkillBookList();
        }
    }

    private void initTransVocationSkillList(byte by) {
        this.clearMove();
        this.shtMenuMoveLength = 0;
        if ((by == 0 ? Param.getInstance().vSkillOccupationBefore != null : Param.getInstance().vSkillOccupationLater != null) && (by == 0 ? !Param.getInstance().vSkillOccupationBefore.isEmpty() : !Param.getInstance().vSkillOccupationLater.isEmpty())) {
            this.shtMenuMoveLength = by == 0 ? (short) Param.getInstance().vSkillOccupationBefore.size() : (short) Param.getInstance().vSkillOccupationLater.size();
            int n = 0;
            while (n < (by == 0 ? Param.getInstance().vSkillOccupationBefore.size() : Param.getInstance().vSkillOccupationLater.size())) {
                short s = ((SkillObject) (by == 0 ? Param.getInstance().vSkillOccupationBefore.elementAt((int) n) : Param.getInstance().vSkillOccupationLater.elementAt((int) n))).shtIcon;
                GameUI.getInstance().addObjectImg(s, "prop");
                n = (short) (n + 1);
            }
            Param.getInstance().IntRgbColor = DrawBase.getRGB(StringManager.getNewLineW(), Macro.FONTHEIGHT, 1144385017);
            this.setButton(1, 4, 2);
        } else {
            this.shtMenuMoveLength = 0;
            this.setButton(1, 100, 2);
        }
    }

    public void initSkillBookList() {
        this.clearMove();
        this.shtMenuMoveLength = 0;
        this.shtMenuMoveLength = (short) Param.getInstance().vSkillBook.size();
        int n = 0;
        while (n < this.shtMenuMoveLength) {
            PackageObject packageObject = (PackageObject) Param.getInstance().vSkillBook.elementAt(n);
            if (packageObject.intId != 0) {
                short s = packageObject.shtIcon;
                GameUI.getInstance().addObjectImg(s, "prop");
            }
            ++n;
        }
        if (this.shtMenuMoveLength != 3) {
            DebugFrame.getInstance().logIn("Error!  天书默认数量只能为3!");
        }
        this.setButton(1, 4, 2);
    }

    public void setGmAppraise() {
        Param.getInstance().intGMQuizTimer = 0;
        GameUI.getInstance().delRoleStateIcon((short) 4);
        if (Param.getInstance().IntRgbColor == null) {
            Param.getInstance().IntRgbColor = DrawBase.getRGB(StringManager.getNewLineW(), Macro.FONTHEIGHT, 1144385017);
        }
        this.strOneDescribeChar = StringManager.wenZi("Vui lòng đánh giá dịch vụ này：", StringManager.getNewLineW());
        this.shtMenuMoveLength = (short) this.strOneDescribeChar.length;
        this.setState((byte) -83, "Xếp hạng dịch vụ");
        this.strOneMenuOption = new String[]{"Rất hài lòng", "Tốt", "Bình thường", "Thất vọng"};
        this.shtMenuMoveLength = (short) this.strOneMenuOption.length;
        this.setNpcDialog();
        this.clearMove();
        this.setButton(1, 0, 2);
    }

    public void setWordMove() {
        this.shtWordMoveY = 0;
        this.shtIOMoveY = 0;
        this.intTimer = 3000;
        this.bytWordMoveStep = (byte) 2;
    }

    public void clearMove() {
        this.shtOneMenuMove = 0;
        this.shtOneMenuRoll = 0;
        this.bytPropMove = 0;
        this.setWordMove();
    }

    public void drawWordMove_Y(Graphics graphics, String[] stringArray, short s, short s2) {
        if (stringArray == null) {
            return;
        }
        if (this.intTimer <= 0 && stringArray.length * Macro.FONTHEIGHT > s2) {
            this.shtWordMoveY = (short) (this.shtWordMoveY - this.bytWordMoveStep);
            if (this.shtWordMoveY < -(stringArray.length * Macro.FONTHEIGHT)) {
                this.shtWordMoveY = s2;
            }
        }
        graphics.setClip(0, s, Macro.SCREEN_WIDTH, s2 + 2);
        GameUI.getInstance().drawWordMove(graphics, stringArray, 15, s + this.shtWordMoveY, (byte) 20);
        graphics.setClip(0, 0, Macro.SCREEN_WIDTH, this.MENU_HEIGHT + 18);
    }

    public void drawWordMove_Y2(Graphics graphics, String[] stringArray, short s, short s2, short s3) {
        if (stringArray == null) {
            return;
        }
        if (this.intTimer <= 0 && stringArray.length * Macro.FONTHEIGHT > s3) {
            this.shtWordMoveY = (short) (this.shtWordMoveY - this.bytWordMoveStep);
            if (this.shtWordMoveY < -(stringArray.length * Macro.FONTHEIGHT)) {
                this.shtWordMoveY = s3;
            }
        }
        graphics.setClip(0, s2, Macro.SCREEN_WIDTH, s3 + 2);
        GameUI.getInstance().drawWordMove(graphics, stringArray, s, s2 + this.shtWordMoveY, (byte) 20);
        graphics.setClip(0, 0, Macro.SCREEN_WIDTH, this.MENU_HEIGHT + 18);
    }

    public void drawWordMove_Y_Color(Graphics graphics, short s, short s2) {
        if (GameUI.getInstance().vsColorInfo != null) {
            if (this.intTimer <= 0 && GameUI.getInstance().vsColorInfo.length * Macro.FONTHEIGHT > s2) {
                this.shtWordMoveY = (short) (this.shtWordMoveY - this.bytWordMoveStep);
                if (this.shtWordMoveY < -(GameUI.getInstance().vsColorInfo.length * Macro.FONTHEIGHT)) {
                    this.shtWordMoveY = s2;
                }
            }
            graphics.setClip(0, s, Macro.SCREEN_WIDTH, s2 + 2);
            int n = 0;
            while (n < GameUI.getInstance().vsColorInfo.length) {
                int n2 = 24;
                int n3 = s + n * Macro.FONTHEIGHT + this.shtWordMoveY;
                int n4 = 0;
                while (n4 < GameUI.getInstance().vsColorInfo[n].size()) {
                    int n5 = ((ChatContent) GameUI.getInstance().vsColorInfo[n].elementAt((int) n4)).fontColor;
                    String string = ((ChatContent) GameUI.getInstance().vsColorInfo[n].elementAt((int) n4)).character;
                    graphics.setColor(n5);
                    graphics.drawString(string, n2, n3, 20);
                    n2 += Macro.font.stringWidth(string);
                    n4 = (short) (n4 + 1);
                }
                n = (short) (n + 1);
            }
            graphics.setClip(0, 0, Macro.SCREEN_WIDTH, this.MENU_HEIGHT + 18);
        }
    }

    private void drawMackStuffMove(Graphics graphics, MakeObject makeObject, short s, short s2) {
        int n = 12;
        if (makeObject.strMaterialName.length * Macro.FONTHEIGHT > s2 && this.intTimer <= 0) {
            this.shtIOMoveY = (short) (this.shtIOMoveY - this.bytWordMoveStep);
            if (this.shtIOMoveY < -(makeObject.strMaterialName.length * Macro.FONTHEIGHT)) {
                this.shtIOMoveY = s2;
            }
        }
        graphics.setClip(0, s, Macro.SCREEN_WIDTH, s2);
        int n2 = 0;
        while (n2 < makeObject.strMaterialName.length) {
            Image image = GameUI.getInstance().getObjectImg(makeObject.shtMaterialNameIcon[n2]);
            if (image != null) {
                graphics.drawImage(image, n, s + this.shtIOMoveY + n2 * Macro.FONTHEIGHT, 20);
                graphics.setColor(makeObject.intMaterialColor[n2]);
                graphics.drawString(makeObject.strMaterialName[n2], n + 18, s + this.shtIOMoveY + n2 * Macro.FONTHEIGHT, 20);
                graphics.drawString(String.valueOf(makeObject.shtNowNum[n2]) + "/" + makeObject.shtMaterialNum[n2], Macro.SCREEN_WIDTH - 12, s + this.shtIOMoveY + n2 * Macro.FONTHEIGHT, 24);
            }
            n2 = (byte) (n2 + 1);
        }
        DCanvas.getInstance().clearScreen();
    }

    private void initPersonalShop() {
        GridTable gridTable = new GridTable((short) 0, (short) 0, Macro.SCREEN_WIDTH, Macro.SCREEN_HEIGHT, (short) 4, (short) 1, new short[]{15, 22, 37, 64}, new short[]{100});
        this.personalShopSellPackageBoxX = (short) (gridTable.getCell(2, 1).getCellX() + 13 + 3);
        this.personalShopSellPackageBoxY = gridTable.getCell(2, 1).getCellY();
        this.personalShopSellPackageBoxW = (short) (gridTable.getCell(2, 1).getCellW() - 26 - 6);
        this.personalShopSellPackageBoxH = gridTable.getCell(2, 1).getCellH();
        this.personalShopPersonalPackageTabY = (short) (gridTable.getCell(3, 1).getCellY() + 10);
        this.personalShopPersonalPackageTabW = (short) (gridTable.getCell(3, 1).getCellW() - 26 - 6);
        this.personalShopPersonalPackageTabH = (short) 32;
        this.tabStyleInstance.initTabStyle(this.personalShopSellPackageBoxX, this.personalShopPersonalPackageTabY, this.personalShopPersonalPackageTabW, this.personalShopPersonalPackageTabH, IDefines.MENU_UI_PERSONAL_SHOP_TAB, (byte) 0);
        this.personalPackageY = (short) (this.personalShopPersonalPackageTabY + this.personalShopPersonalPackageTabH);
        int n = this.personalShopSellPackageBoxX + 8;
        int n2 = this.personalShopSellPackageBoxY + 2;
        int n3 = this.personalShopSellPackageBoxW - 16;
        short s = this.personalShopSellPackageBoxH;
        this.personalShopSellPackage = new PackageBox(n, n2, n3, s, Param.getInstance().bytShopBag, false);
        short s2 = this.personalPackageY;
        Param.getInstance().personalPackage = new PackageBox(n, s2, n3, Macro.SCREEN_HEIGHT - 32 - this.tabStyleInstance.GettabBottomY, Param.getInstance().bytTaskBag, true);
        this.strLineFeed = StringManager.wenZi("Nhấn phím # để chuyển đổi thẻ trang", this.personalShopPersonalPackageTabW);
        this.personalShopState = (byte) 2;
    }

    private void cleanPersonalShop() {
        Param.getInstance().hShopPackage = null;
        Param.getInstance().hImgObject = null;
        this.personalShopSellPackage = null;
        Param.getInstance().personalPackage = null;
    }

    private void drawPersonalShop() {
        DCanvas.getInstance().drawTileTextBG(DCanvas.gameG, this.strOneTitlestr);
        DrawBase.drawBox(this.personalShopSellPackageBoxX, this.personalShopSellPackageBoxY, this.personalShopSellPackageBoxW, this.personalShopSellPackageBoxH, IDefines.MENU_UI_PERSONAL_SHOP_SELL_PACKAGE_BOX_BORDER, true);
        this.personalShopSellPackage.draw(Param.getInstance().hShopPackage);
        this.tabStyleInstance.drawTabStyle(DCanvas.gameG);
        DrawBase.drawBox(this.personalShopSellPackageBoxX, this.personalPackageY, this.personalShopPersonalPackageTabW, Macro.SCREEN_HEIGHT - this.personalPackageY - 40, IDefines.MENU_UI_PERSONAL_SHOP_PERSONAL_PACKAGE_BOX_BORDER, true);
        Param.getInstance().personalPackage.draw(Param.getInstance().hPackage);
        this.drawMoney(DCanvas.gameG, ORPMe.Gold, -1, this.MENU_HEIGHT, true);
    }

    public void setPersonalShopPackageMessage() {
        if (this.personalShopPersonalPackageTabIndex != this.tabStyleInstance.getTabCurrentIndex()) {
            this.personalShopPersonalPackageTabIndex = this.tabStyleInstance.getTabCurrentIndex();
            switch (this.personalShopPersonalPackageTabIndex) {
                case 0: {
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendShop_OpenBag((byte) 1);
                    break;
                }
                case 1: {
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendShop_OpenBag((byte) 2);
                    break;
                }
                case 2: {
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendShop_OpenBag((byte) 3);
                    break;
                }
                case 3: {
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendShop_OpenBag((byte) 5);
                }
            }
        }
    }

    private void updatePersonalShop(byte by) {
        String[] stringArray;
        block39:
        {
            stringArray = null;
            block0:
            switch (this.personalShopState) {
                case 0: {
                    switch (by) {
                        case 5: {
                            if (ORPMe.ME.blnStall) {
                                stringArray = new String[]{"Đóng cửa hàng"};
                                break;
                            }
                            if (Param.getInstance().hShopPackage != null && Param.getInstance().hShopPackage.containsKey(new Integer(this.personalShopSellPackage.getCellBoxIndex()))) {
                                stringArray = new String[]{"Xem các thuộc tính", "Thay đổi giá", "Rút hàng", "Mở cửa hàng", "Đóng cửa hàng", "Thay đổi tên cửa hàng"};
                                break;
                            }
                            stringArray = new String[]{"Mở cửa hàng", "Đóng cửa hàng", "Thay đổi tên cửa hàng"};
                            break;
                        }
                        case 1: {
                            if (this.personalShopSellPackage.getLineIndex() <= 0) {
                                break;
                            }
                            this.personalShopSellPackage.getKeyUp();
                            this.setInfoContent(Param.getInstance().hShopPackage, this.personalShopSellPackage.getCellBoxIndex());
                            break;
                        }
                        case 2: {
                            if (this.personalShopSellPackage.getLineIndex() < this.personalShopSellPackage.getLineNum() - 1) {
                                this.personalShopSellPackage.getKeyDown();
                                this.setInfoContent(Param.getInstance().hShopPackage, this.personalShopSellPackage.getCellBoxIndex());
                                break;
                            }
                            this.personalShopSellPackage.setFocal(false);
                            this.tabStyleInstance.TabFocuse = true;
                            this.personalShopState = 1;
                            break;
                        }
                        case 3: {
                            this.personalShopSellPackage.getKeyLeft();
                            this.setInfoContent(Param.getInstance().hShopPackage, this.personalShopSellPackage.getCellBoxIndex());
                            break;
                        }
                        case 4: {
                            this.personalShopSellPackage.getKeyRight();
                            this.setInfoContent(Param.getInstance().hShopPackage, this.personalShopSellPackage.getCellBoxIndex());
                        }
                    }
                    break;
                }
                case 1: {
                    switch (by) {
                        case 5: {
                            if (ORPMe.ME.blnStall) {
                                stringArray = new String[]{"Đóng cửa hàng"};
                                break;
                            }
                            stringArray = new String[]{"Mở cửa hàng", "Đóng cửa hàng", "Thay đổi tên cửa hàng"};
                            break;
                        }
                        case 1: {
                            this.tabStyleInstance.TabFocuse = false;
                            this.personalShopSellPackage.setFocal(true);
                            this.personalShopState = 0;
                            this.setInfoContent(Param.getInstance().hShopPackage, this.personalShopSellPackage.getCellBoxIndex());
                            break;
                        }
                        case 2: {
                            this.tabStyleInstance.TabFocuse = false;
                            Param.getInstance().personalPackage.setFocal(true);
                            this.personalShopState = (byte) 2;
                            this.setInfoContent(Param.getInstance().hPackage, Param.getInstance().personalPackage.getCellBoxIndex());
                            break;
                        }
                        case 3: {
                            this.tabStyleInstance.getTabCurrentKeyLeft();
                            this.setInfoContent(null, -1);
                            break;
                        }
                        case 4: {
                            this.tabStyleInstance.getTabCurrentKeyRight();
                            this.setInfoContent(null, -1);
                        }
                    }
                    this.setPersonalShopPackageMessage();
                    break;
                }
                case 2: {
                    switch (by) {
                        case 5: {
                            if (ORPMe.ME.blnStall) {
                                stringArray = new String[]{"Đóng cửa hàng"};
                                break block0;
                            }
                            if (Param.getInstance().hPackage != null && Param.getInstance().hPackage.containsKey(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()))) {
                                stringArray = new String[]{"Xem thuộc tính", "Hàng trên kệ", "Mở cửa hàng", "Đóng cửa hàng", "Thay đổi tên cửa hàng"};
                                break block0;
                            }
                            stringArray = new String[]{"Mở cửa hàng", "Đóng cửa hàng", "Thay đổi tên cửa hàng"};
                            break block0;
                        }
                        case 1: {
                            if (Param.getInstance().personalPackage.getLineIndex() > 0) {
                                Param.getInstance().personalPackage.getKeyUp();
                                this.setInfoContent(Param.getInstance().hPackage, Param.getInstance().personalPackage.getCellBoxIndex());
                                break block0;
                            }
                            Param.getInstance().personalPackage.setFocal(false);
                            this.tabStyleInstance.TabFocuse = true;
                            this.personalShopState = 1;
                            break block0;
                        }
                        case 2: {
                            if (Param.getInstance().personalPackage.getLineIndex() < Param.getInstance().personalPackage.getLineNum() - 1) {
                                Param.getInstance().personalPackage.getKeyDown();
                                this.setInfoContent(Param.getInstance().hPackage, Param.getInstance().personalPackage.getCellBoxIndex());
                                break block0;
                            }
                            break block39;
                        }
                        case 3: {
                            Param.getInstance().personalPackage.getKeyLeft();
                            this.setInfoContent(Param.getInstance().hPackage, Param.getInstance().personalPackage.getCellBoxIndex());
                            break block0;
                        }
                        case 4: {
                            Param.getInstance().personalPackage.getKeyRight();
                            this.setInfoContent(Param.getInstance().hPackage, Param.getInstance().personalPackage.getCellBoxIndex());
                        }
                    }
                }
            }
        }
        if (stringArray != null && !stringArray.equals("")) {
            GameUI.getInstance().setTwoMenu(this.bytMenuState, stringArray, (byte) 5);
        }
        if (by == 6) {
            if (GameUI.getInstance().vsColorInfo != null) {
                GameUI.getInstance().vsColorInfo = null;
                Param.getInstance().blnColorInfo = false;
            }
            Param.getInstance().bytNpcDataType = (byte) -1;
            if (this.bytTwoMenuState != 0) {
                this.bytTwoMenuState = 0;
                this.setButton(1, 4, 2);
            } else {
                this.setBackSystem();
                this.cleanPersonalShop();
            }
        }
    }

    private void updatePersonalShopTouch() {
        if (DCanvas.getInstance().IsPointerDown(this.personalShopSellPackage.getShowX(), this.personalShopSellPackage.getShowY(), this.personalShopSellPackage.getShowW(), this.personalShopSellPackage.getShowH())) {
            if (this.personalShopState != 0) {
                this.tabStyleInstance.TabFocuse = false;
                Param.getInstance().personalPackage.setFocal(false);
                this.personalShopSellPackage.setFocal(true);
                this.personalShopState = 0;
            }
        } else if (DCanvas.getInstance().IsPointerDown(this.personalShopSellPackageBoxX, this.personalShopPersonalPackageTabY, this.personalShopPersonalPackageTabW, this.personalShopPersonalPackageTabH)) {
            if (this.personalShopState != 1) {
                this.tabStyleInstance.TabFocuse = true;
                this.personalShopSellPackage.setFocal(false);
                Param.getInstance().personalPackage.setFocal(false);
                this.personalShopState = 1;
            }
        } else if (DCanvas.getInstance().IsPointerDown(Param.getInstance().personalPackage.getShowX(), Param.getInstance().personalPackage.getShowY(), Param.getInstance().personalPackage.getShowW(), Param.getInstance().personalPackage.getShowH()) && this.personalShopState != 2) {
            this.tabStyleInstance.TabFocuse = false;
            this.personalShopSellPackage.setFocal(false);
            Param.getInstance().personalPackage.setFocal(true);
            this.personalShopState = (byte) 2;
        }
        this.personalShopSellPackage.getTouch();
        this.tabStyleInstance.getTabCurrentIndexByPointer();
        this.setPersonalShopPackageMessage();
        Param.getInstance().personalPackage.getTouch();
    }

    public void paint(Graphics graphics) {
        if (FullInfo.getInstance() != null) {
            FullInfo.getInstance().paint(graphics);
        } else if (this.bytTwoMenuState != 2) {
            switch (this.bytMenuState) {
                case -26: {
                    this.drawMassage(graphics);
                    break;
                }
                case -25: {
                    if (Macro.hNetList != null && Macro.hNetList.size() != 0) {
                        String string = "";
                        String string2 = "";
                        int n = 0;
                        while (n < Macro.hNetList.size()) {
                            string2 = (String) Macro.hNetList.get(new Integer(n));
                            string = String.valueOf(string) + (String) Macro.hNetList.get(new Integer(n));
                            ++n;
                        }
                        this.strOneDescribeChar = StringManager.wenZi(string, StringManager.getNewLineW() - 12);
                        this.scrolltext.setText(this.strOneDescribeChar);
                    }
                    this.drawMassage(graphics);
                    break;
                }
                case 42: {
                    this.drawChatNote(graphics);
                    break;
                }
                case -49: {
                    this.drawMacroChat(graphics);
                    break;
                }
                case 60: {
                    this.drawSetting(graphics);
                    break;
                }
                case -84: {
                    this.drawServeInfo(graphics);
                    break;
                }
                case -81: {
                    this.drawShortcut(graphics);
                    break;
                }
                case -82: {
                    this.drawMoveKey(graphics);
                    break;
                }
                case 61: {
                    this.drawSkillList(graphics);
                    break;
                }
                case 64: {
                    this.drawLifeSkill(graphics);
                    break;
                }
                case 65: {
                    this.drawLifeSkillPurification(graphics, "提纯");
                    break;
                }
                case 63: {
                    this.drawTaskList(graphics);
                    break;
                }
                case 35: {
                    this.drawTroopImageOption(graphics);
                    break;
                }
                case -48: {
                    this.drawMasterList(graphics);
                    break;
                }
                case -50: {
                    this.drawPartnerList(graphics);
                    break;
                }
                case -31: {
                    this.drawFriendOption(graphics);
                    break;
                }
                case -33:
                case -32: {
                    this.drawFriendOption(graphics);
                    break;
                }
                case -40: {
                    this.drawTitlTabBackGround(graphics, "Tìm kiếm người chơi");
                    break;
                }
                case -46:
                case -44:
                case -38: {
                    this.drawImageOption(Param.getInstance().vCommonList, graphics);
                    break;
                }
                case 34: {
                    this.drawFaction(graphics);
                    break;
                }
                case -47: {
                    this.drawMailRecv(Param.getInstance().vCommonList, graphics);
                    break;
                }
                case -36: {
                    this.drawEmailList(graphics, Param.getInstance().vCommonList);
                    break;
                }
                case -113: {
                    this.drawFullTiTle(graphics, this.strOneTitlestr, this.shtMenuMoveLength, this.getOneMove(), this.bytWordMaxH);
                    if (this.strsTitle != null) {
                        this.drawMenuTitle(graphics);
                        this.drawMenuTitleName(graphics);
                    }
                    this.drawOption(graphics, this.bytMenuState, this.vTempVessel, (short) 12, (short) (Macro.FONTHEIGHT * 2 + 16), this.shtOneMenuMove, this.shtOneMenuRoll, this.bytWordMaxH);
                    break;
                }
                case -34: {
                    this.drawImageOption(Param.getInstance().vInfoContent, graphics);
                    break;
                }
                case -72:
                case -35: {
                    this.drawPersonalShop();
                    break;
                }
                case -43: {
                    this.drawNpcStorage(graphics);
                    break;
                }
                case -39: {
                    this.drawNpcShop(graphics);
                    if (!this.blnTabPet || this.blnTab) {
                        break;
                    }
                    this.drawPetGoodList(graphics);
                    break;
                }
                case -70:
                case 10:
                case 21:
                case 22:
                case 33:
                case 36:
                case 37:
                case 38:
                case 39: {
                    this.drawPack(graphics);
                    if (!this.blnTabPet || this.blnTab) {
                        break;
                    }
                    this.drawPetGoodList(graphics);
                    break;
                }
                case 11: {
                    this.drawInlay(graphics);
                    break;
                }
                case -11: {
                    this.drawShopPropList(graphics);
                    break;
                }
                case -15: {
                    this.drawMallPackList(graphics, (byte) -15);
                    break;
                }
                case -16: {
                    this.drawMallPackList(graphics, (byte) -16);
                    break;
                }
                case -24:
                case -22:
                case -20:
                case -18: {
                    this.drawMallAlert(graphics);
                    break;
                }
                case -19: {
                    break;
                }
                case -37: {
                    break;
                }
                case -1: {
                    this.drawPetProp(graphics);
                    break;
                }
                case 58: {
                    this.drawPetGoodList(graphics);
                    if (this.bytMoveType == 0) {
                        this.drawPetRect(graphics, this.bytMoveType, this.bytPetMove, (short) 0, (short) 0);
                        break;
                    }
                    this.drawPetRect(graphics, this.bytMoveType, this.bytPropMove, this.shtOneMenuMove, this.shtOneMenuRoll);
                    break;
                }
                case 1: {
                    this.drawNpcOneDialog(graphics);
                    break;
                }
                case 31: {
                    this.drawMailSendGood(graphics);
                    break;
                }
                case -83:
                case -80: {
                    this.drawHelpOption(graphics);
                    break;
                }
                case -10: {
                    break;
                }
                case -127: {
                    this.drawTaskMessage(graphics);
                    break;
                }
                case -7: {
                    this.drawRoleInfo(graphics);
                    break;
                }
                case -5: {
                    break;
                }
                case -6: {
                    short s = (short) (Macro.FONTHEIGHT * 2 + 9 + this.shtRectUpH + 13);
                    this.drawORoleInfo(graphics);
                    if (Param.getInstance().oSelectRole != null || Param.getInstance().vOptionPlace != null) {
                        break;
                    }
                    GameControl.getInstance().delUIbase(3);
                    return;
                }
                case -91: {
                    this.drawNpcList(graphics);
                    break;
                }
                case -51: {
                    this.drawRankList(graphics);
                }
            }
            if (DCanvas.getInstance().UHandle == null) {
                if (DCanvas.getInstance().UDialog == null || DCanvas.getInstance().UDialog != null && this.bytDelState == 9 && this.bytButtonType != null) {
                    DCanvas.getInstance().drawSoftkey(graphics, this.bytButtonType[0], this.bytButtonType[1], this.bytButtonType[2], this.blnIsThis);
                }
                if (Param.popupDialogInstance.getIsShow() && (this.bytMenuState == 1 || this.bytMenuState == 10 || this.bytMenuState == 39 || this.bytMenuState == -70 || this.bytMenuState == 36 || this.bytMenuState == 38 || this.bytMenuState == 37 || this.bytMenuState == -72 || this.bytMenuState == -35 || this.bytMenuState == 37 || this.bytMenuState == 11 || this.bytMenuState == -11 || this.bytMenuState == -127 || this.bytMenuState == -81 || this.bytMenuState == -39 || this.bytMenuState == -43 || this.bytMenuState == -6)) {
                    Param.popupDialogInstance.drawPopupDialog(graphics);
                }
            }
            if (this.twodialog != null) {
                this.twodialog.paint(graphics);
            }
        }
    }

    private void drawSkillList(Graphics graphics) {
        this.drawTitlTabBackGround(graphics, this.strOneTitlestr);
        this.scroll_OFF = DCanvas.getInstance().blnSpoolr ? ScrollText.arrowhead_width : (byte) 0;
        DrawBase.drawBox(8, this.tabStyleInstance.GettabBottomY + 1, Macro.SCREEN_WIDTH - 12 - 4 - this.scroll_OFF, Macro.FONTHEIGHT + 4, new int[]{15195580}, true);
        if (this.bytTitleMove == 0) {
            int n = 12;
            short s = this.tabStyleInstance.getTabFrameRect()[1];
            short s2 = (short) (s + 5);
            DrawBase.DrawString(IDefines.SKILL_FIGHT_SKILL_ATTRI[0], (short) (n + 20 - this.scroll_OFF), s2, IDefines.SKILL_FIGHT_BOOK_NONE_BOX_WIDTH - 20, 20, (short) 6, 0);
            DrawBase.DrawString(IDefines.SKILL_FIGHT_SKILL_ATTRI[1], (short) (n + IDefines.SKILL_FIGHT_SKILL_ATTRI_TYPE_X - this.scroll_OFF), s2, IDefines.SKILL_FIGHT_BOOK_NONE_BOX_WIDTH - IDefines.SKILL_FIGHT_SKILL_ATTRI_TYPE_X, 20, (short) 6, 0);
            DrawBase.DrawString(IDefines.SKILL_FIGHT_SKILL_ATTRI[2], (short) (n + IDefines.SKILL_FIGHT_SKILL_ATTRI_LEVEL_X - this.scroll_OFF), s2, IDefines.SKILL_FIGHT_BOOK_NONE_BOX_WIDTH - IDefines.SKILL_FIGHT_SKILL_ATTRI_LEVEL_X, 20, (short) 6, 0);
            DrawBase.DrawString(IDefines.SKILL_FIGHT_SKILL_ATTRI[3], (short) (n + IDefines.SKILL_FIGHT_SKILL_ATTRI_KEY_X - this.scroll_OFF), s2, IDefines.SKILL_FIGHT_BOOK_NONE_BOX_WIDTH - IDefines.SKILL_FIGHT_SKILL_ATTRI_KEY_X, 20, (short) 17, 0);
            s = (short) (s + 25);
            this.drawOption(graphics, this.bytMenuState, Param.getInstance().vSkillOccupationBefore, (short) 12, s, this.shtOneMenuMove, this.shtOneMenuRoll, this.bytWordMaxH);
        } else if (this.bytTitleMove == 1) {
            int n = 12;
            short s = this.tabStyleInstance.getTabFrameRect()[1];
            short s3 = (short) (s + 5);
            DrawBase.DrawString(IDefines.SKILL_FIGHT_SKILL_ATTRI[0], (short) (n + 20 - this.scroll_OFF), s3, IDefines.SKILL_FIGHT_BOOK_NONE_BOX_WIDTH - 20, 20, (short) 6, 0);
            DrawBase.DrawString(IDefines.SKILL_FIGHT_SKILL_ATTRI[1], (short) (n + IDefines.SKILL_FIGHT_SKILL_ATTRI_TYPE_X - this.scroll_OFF), s3, IDefines.SKILL_FIGHT_BOOK_NONE_BOX_WIDTH - IDefines.SKILL_FIGHT_SKILL_ATTRI_TYPE_X, 20, (short) 6, 0);
            DrawBase.DrawString(IDefines.SKILL_FIGHT_SKILL_ATTRI[2], (short) (n + IDefines.SKILL_FIGHT_SKILL_ATTRI_LEVEL_X - this.scroll_OFF), s3, IDefines.SKILL_FIGHT_BOOK_NONE_BOX_WIDTH - IDefines.SKILL_FIGHT_SKILL_ATTRI_LEVEL_X, 20, (short) 6, 0);
            DrawBase.DrawString(IDefines.SKILL_FIGHT_SKILL_ATTRI[3], (short) (n + IDefines.SKILL_FIGHT_SKILL_ATTRI_KEY_X - this.scroll_OFF), s3, IDefines.SKILL_FIGHT_BOOK_NONE_BOX_WIDTH - IDefines.SKILL_FIGHT_SKILL_ATTRI_KEY_X, 20, (short) 10, 0);
            s = (short) (s + 25);
            this.drawOption(graphics, this.bytMenuState, Param.getInstance().vSkillOccupationLater, (short) 12, s, this.shtOneMenuMove, this.shtOneMenuRoll, this.bytWordMaxH);
        } else if (this.bytTitleMove == 2) {
            short s = 12;
            short s4 = this.tabStyleInstance.getTabFrameRect()[1];
            short s5 = (short) (s4 + 5);
            DrawBase.DrawString(IDefines.SKILL_FIGHT_BOOK_ATTRI[0], (short) (s + 0), s5, IDefines.SKILL_FIGHT_BOOK_NONE_BOX_WIDTH - 0, 20, (short) 6, 0);
            DrawBase.DrawString(IDefines.SKILL_FIGHT_BOOK_ATTRI[1], (short) (s + IDefines.SKILL_FIGHT_BOOK_ATTRI_QUALITY_X), s5, IDefines.SKILL_FIGHT_BOOK_NONE_BOX_WIDTH - IDefines.SKILL_FIGHT_BOOK_ATTRI_QUALITY_X, 20, (short) 6, 0);
            DrawBase.DrawString(IDefines.SKILL_FIGHT_BOOK_ATTRI[2], s, s5, IDefines.SKILL_FIGHT_BOOK_NONE_BOX_WIDTH, 20, (short) 10, 0);
            s4 = (short) (s4 + 25);
            int n = 25;
            this.drawOption(graphics, this.bytMenuState, Param.getInstance().vSkillBook, s, s4, this.shtOneMenuMove, this.shtOneMenuRoll, (byte) 3, n);
        }
        if (this.isDrawSkillinfo) {
            this.drawSkillInfo(graphics);
        }
        graphics.setColor(0);
        graphics.drawString("Điểm kỹ năng:" + ORPMe.ME.skillPoints, Macro.SCREEN_WIDTH / 2, Macro.SCREEN_HEIGHT - Macro.FONTHEIGHT - 4, 17);
    }

    private void drawSkillInfo(Graphics graphics) {
        DCanvas.getInstance().drawTileTextBG(graphics, "Học kỹ năng");
        int n = Macro.SCREEN_HEIGHT - 38 - 31 - 12;
        int n2 = Macro.SCREEN_WIDTH - 24;
        int n3 = 18;
        int n4 = 46;
        int n5 = n2 - 12;
        int n6 = n - 8 >> 1;
        DrawBase.drawBox(n3, n4, n5, n6, new int[]{14995858, 16642234}, true);
        String[] stringArray = StringManager.wenZi(this.bytTitleMove == 0 ? StringManager.displaceNpcTalk(((SkillObject) Param.getInstance().vSkillOccupationBefore.elementAt((int) MenuUI.getInstance().getOneMove())).strInfo) : StringManager.displaceNpcTalk(((SkillObject) Param.getInstance().vSkillOccupationLater.elementAt((int) MenuUI.getInstance().getOneMove())).strInfo), n5);
        DrawBase.drawString("Cấp hiện tại", Macro.SCREEN_WIDTH >> 1, n4, 9263661, 17);
        StringManager.draWordMove(graphics, stringArray, n3, n4 + Macro.FONTHEIGHT, n6 - (Macro.FONTHEIGHT >> 1) + 2 - Macro.FONTHEIGHT, 9263661, 20);
        DrawBase.drawBox(n3, n4 + n6 + 4, n5, n6, new int[]{14995858, 16642234}, true);
        stringArray = StringManager.wenZi(this.bytTitleMove == 0 ? StringManager.displaceNpcTalk(((SkillObject) Param.getInstance().vSkillOccupationBefore.elementAt((int) MenuUI.getInstance().getOneMove())).strNextInfo) : StringManager.displaceNpcTalk(((SkillObject) Param.getInstance().vSkillOccupationLater.elementAt((int) MenuUI.getInstance().getOneMove())).strNextInfo), n5);
        DrawBase.drawString("Cấp độ tiếp theo", Macro.SCREEN_WIDTH >> 1, n4 + n6 + 4, 9263661, 17);
        StringManager.draWordMove(graphics, stringArray, n3, n4 + n6 + 4 + Macro.FONTHEIGHT, n6 - (Macro.FONTHEIGHT >> 1) + 2 - Macro.FONTHEIGHT, 9263661, 20);
    }

    public void iniSkillOccList() {
        this.iniSkillOccBefore();
        this.iniSkillOccLater();
    }

    public void setBookPackage(byte by, byte by2) {
        PackageObject packageObject = null;
        if (Param.getInstance().hPackage != null && Param.getInstance().hPackage.containsKey(new Integer(by)) && (packageObject = (PackageObject) Param.getInstance().hPackage.get(new Integer(by))) != null) {
            NetSend.getInstance().sendRequestInlayBook(packageObject.intId, by2);
        }
    }

    private void iniSkillOccBefore() {
        int n = Param.getInstance().vSkillList == null ? 0 : Param.getInstance().vSkillList.size() + (Param.getInstance().vSkillPassivityList == null ? 0 : Param.getInstance().vSkillPassivityList.size());
        Param.getInstance().vSkillOccupationBefore = new Vector();
        int n2 = 0;
        while (n2 < n) {
            if (n2 < Param.getInstance().vSkillList.size()) {
                if (((SkillObject) Param.getInstance().vSkillList.elementAt((int) n2)).bytRank == 1) {
                    Param.getInstance().vSkillOccupationBefore.addElement(Param.getInstance().vSkillList.elementAt(n2));
                }
            } else if (Param.getInstance().vSkillPassivityList != null && ((SkillObject) Param.getInstance().vSkillPassivityList.elementAt((int) (n2 - Param.getInstance().vSkillList.size()))).bytRank == 1) {
                Param.getInstance().vSkillOccupationBefore.addElement(Param.getInstance().vSkillPassivityList.elementAt(n2 - Param.getInstance().vSkillList.size()));
            }
            ++n2;
        }
    }

    private void iniSkillOccLater() {
        int n = Param.getInstance().vSkillList == null ? 0 : Param.getInstance().vSkillList.size() + (Param.getInstance().vSkillPassivityList == null ? 0 : Param.getInstance().vSkillPassivityList.size());
        Param.getInstance().vSkillOccupationLater = new Vector();
        int n2 = 0;
        while (n2 < n) {
            if (n2 < Param.getInstance().vSkillList.size()) {
                if (((SkillObject) Param.getInstance().vSkillList.elementAt((int) n2)).bytRank == 2) {
                    Param.getInstance().vSkillOccupationLater.addElement(Param.getInstance().vSkillList.elementAt(n2));
                }
            } else if (Param.getInstance().vSkillPassivityList != null && ((SkillObject) Param.getInstance().vSkillPassivityList.elementAt((int) (n2 - Param.getInstance().vSkillList.size()))).bytRank == 2) {
                Param.getInstance().vSkillOccupationLater.addElement(Param.getInstance().vSkillPassivityList.elementAt(n2 - Param.getInstance().vSkillList.size()));
            }
            ++n2;
        }
    }

    public static int getSkillTypeNum(Vector vector, boolean bl) {
        int n = 0;
        if (vector != null) {
            int n2 = 0;
            while (n2 < vector.size()) {
                SkillObject skillObject = (SkillObject) vector.elementAt(n2);
                if (bl == skillObject.blnPassivity) {
                    ++n;
                }
                ++n2;
            }
        }
        return n;
    }

    public static int searchSkillIndexById(Vector vector, int n) {
        int n2 = -1;
        if (vector != null) {
            int n3 = 0;
            while (n3 < vector.size()) {
                SkillObject skillObject = (SkillObject) vector.elementAt(n3);
                if (skillObject.intId == n) {
                    n2 = n3;
                    break;
                }
                ++n3;
            }
        }
        return n2;
    }

    public void iniSkillBook() {
        if (Param.getInstance().vSkillBook == null) {
            Param.getInstance().vSkillBook = new Vector(3);
            int n = 0;
            while (n < 3) {
                Param.getInstance().vSkillBook.insertElementAt(new PackageObject(), n);
                ++n;
            }
        }
    }

    public void setChatNote() {
        this.shtMenuMoveLength = 0;
        byte by = this.tabStyleInstance.getTabCurrentIndex();
        if (this.bytTitleMove != by) {
            this.bytTitleMove = by;
        }
        Param.getInstance().getClass();
        if (Param.getInstance().bytChatNoteType == 0) {
            if (Param.getInstance().vChatNote != null) {
                this.shtMenuMoveLength = (short) Param.getInstance().vChatNote.size();
            }
            this.setButton(1, 4, 2);
        } else {
            Param.getInstance().getClass();
            if (Param.getInstance().bytChatNoteType == 6) {
                if (Param.getInstance().vChatSingleNote != null) {
                    this.shtMenuMoveLength = (short) Param.getInstance().vChatSingleNote.size();
                }
                this.setButton(1, 4, 2);
            } else {
                Param.getInstance().getClass();
                if (Param.getInstance().bytChatNoteType == 2) {
                    if (Param.getInstance().vChatRaceNote != null) {
                        this.shtMenuMoveLength = (short) Param.getInstance().vChatRaceNote.size();
                    }
                    this.setButton(1, 4, 2);
                } else {
                    Param.getInstance().getClass();
                    if (Param.getInstance().bytChatNoteType == 5) {
                        if (Param.getInstance().vChatTeamNote != null) {
                            this.shtMenuMoveLength = (short) Param.getInstance().vChatTeamNote.size();
                        }
                        this.setButton(1, 4, 2);
                    } else {
                        Param.getInstance().getClass();
                        if (Param.getInstance().bytChatNoteType == 4) {
                            if (Param.getInstance().vChatConsortianNote != null) {
                                this.shtMenuMoveLength = (short) Param.getInstance().vChatConsortianNote.size();
                            }
                            this.setButton(1, 4, 2);
                        } else {
                            Param.getInstance().getClass();
                            if (Param.getInstance().bytChatNoteType == 1) {
                                if (Param.getInstance().vChatWordNote != null) {
                                    this.shtMenuMoveLength = (short) Param.getInstance().vChatWordNote.size();
                                }
                                this.setButton(1, 4, 2);
                            } else {
                                Param.getInstance().getClass();
                                if (Param.getInstance().bytChatNoteType == 3) {
                                    if (Param.getInstance().vChatMapNote != null) {
                                        this.shtMenuMoveLength = (short) Param.getInstance().vChatMapNote.size();
                                    }
                                    this.setButton(1, 4, 2);
                                } else {
                                    Param.getInstance().getClass();
                                    if (Param.getInstance().bytChatNoteType == 8) {
                                        if (Param.getInstance().vChatFightNote != null) {
                                            this.shtMenuMoveLength = (short) Param.getInstance().vChatFightNote.size();
                                        }
                                        this.setButton(1, 4, 2);
                                    } else {
                                        Param.getInstance().getClass();
                                        if (Param.getInstance().bytChatNoteType == 7) {
                                            if (Param.getInstance().vSystemNote != null) {
                                                this.shtMenuMoveLength = (short) Param.getInstance().vSystemNote.size();
                                            }
                                            this.setButton(1, 4, 2);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (this.blnSetChatNote) {
            this.shtOneMenuMove = (short) -1;
            this.blnSetChatNote = false;
        }
    }

    private void setTitlePlace(byte by) {
        this.bytTitleType = by;
        this.shtTitleW = (short) (Macro.SCREEN_WIDTH - 36);
        this.shtTitleX = (short) ((Macro.SCREEN_WIDTH - this.shtTitleW) / 2);
        this.shtTitleH = this.strsTitle == null ? (short) 16 : (short) Macro.FONTHEIGHT;
        this.IntTitleRgbColor = DrawBase.getRGB(Macro.SCREEN_WIDTH, this.shtTitleH, 895490824);
    }

    private void cleanTitle() {
        this.IntTitleRgbColor = null;
        this.strsTitle = null;
        this.bytTitleType = 0;
    }

    private void drawMenuTitle(Graphics graphics) {
        int n = this.shtTitleX + this.bytTitleMove * (this.shtTitleW / this.bytTitleType);
        if (this.IntTitleRgbColor != null) {
            graphics.drawRGB(this.IntTitleRgbColor, 0, n - 2, 2, Macro.FONTHEIGHT + 5, n - 2, this.shtTitleH, true);
            graphics.drawRGB(this.IntTitleRgbColor, 0, Macro.SCREEN_WIDTH - n - this.shtTitleW / this.bytTitleType - 2, n + this.shtTitleW / this.bytTitleType, Macro.FONTHEIGHT + 5, Macro.SCREEN_WIDTH - n - this.shtTitleW / this.bytTitleType - 2, this.shtTitleH, true);
        }
    }

    private boolean leftMenuTitle() {
        if (this.bytTitleMove > 0) {
            this.bytTitleMove = (byte) (this.bytTitleMove - 1);
            return true;
        }
        return false;
    }

    private boolean rightMenuTitle() {
        if (this.bytTitleMove < this.bytTitleType) {
            this.bytTitleMove = (byte) (this.bytTitleMove + 1);
            return true;
        }
        return false;
    }

    public static void DrawAlphaRect(int n, int n2, int n3, int n4, int n5, Graphics graphics) {
        int[] nArray = new int[n4 * n3];
        int n6 = 0;
        while (n6 < nArray.length) {
            nArray[n6] = n5;
            ++n6;
        }
        graphics.drawRGB(nArray, 0, n3, n, n2, n3, n4, true);
        graphics.setColor(n5);
        graphics.drawRoundRect(n - 1, n2 - 1, n3 + 1, n4 + 1, 10, 10);
        graphics.drawRoundRect(n - 2, n2 - 2, n3 + 3, n4 + 3, 10, 10);
    }

    private void drawMenuTitleName(Graphics graphics) {
        graphics.setColor(0xFF0000);
        int n = 0;
        while (n < this.strsTitle.length) {
            graphics.drawString(this.strsTitle[n], this.shtTitleX + this.shtTitleW / this.bytTitleType * (n * 2 + 1) / 2, Macro.FONTHEIGHT + 5, 17);
            n = (byte) (n + 1);
        }
    }

    private void drawChatNote(Graphics graphics) {
        this.drawTitlTabBackGround(graphics, "Trò chuyện");
        Param.getInstance().bytChatNoteType = this.tabStyleInstance.getTabCurrentIndex();
        this.bytTitleMove = this.tabStyleInstance.getTabCurrentIndex();
        Param.getInstance().getClass();
        if (Param.getInstance().bytChatNoteType == 0) {
            if (Param.getInstance().vChatNote != null) {
                this.drawChat(graphics, Param.getInstance().vChatNote, this.tabStyleInstance.GettabBottomY + 8);
            }
        } else {
            Param.getInstance().getClass();
            if (Param.getInstance().bytChatNoteType == 2) {
                if (Param.getInstance().vChatRaceNote != null) {
                    this.drawChat(graphics, Param.getInstance().vChatRaceNote, this.tabStyleInstance.GettabBottomY + 8);
                }
            } else {
                Param.getInstance().getClass();
                if (Param.getInstance().bytChatNoteType == 6) {
                    if (Param.getInstance().vChatSingleNote != null) {
                        this.drawChat(graphics, Param.getInstance().vChatSingleNote, this.tabStyleInstance.GettabBottomY + 8);
                    }
                } else {
                    Param.getInstance().getClass();
                    if (Param.getInstance().bytChatNoteType == 5) {
                        if (Param.getInstance().vChatTeamNote != null) {
                            this.drawChat(graphics, Param.getInstance().vChatTeamNote, this.tabStyleInstance.GettabBottomY + 8);
                        }
                    } else {
                        Param.getInstance().getClass();
                        if (Param.getInstance().bytChatNoteType == 4) {
                            if (Param.getInstance().vChatConsortianNote != null) {
                                this.drawChat(graphics, Param.getInstance().vChatConsortianNote, this.tabStyleInstance.GettabBottomY + 8);
                            }
                        } else {
                            Param.getInstance().getClass();
                            if (Param.getInstance().bytChatNoteType == 1) {
                                if (Param.getInstance().vChatWordNote != null) {
                                    this.drawChat(graphics, Param.getInstance().vChatWordNote, this.tabStyleInstance.GettabBottomY + 8);
                                }
                            } else {
                                Param.getInstance().getClass();
                                if (Param.getInstance().bytChatNoteType == 3) {
                                    if (Param.getInstance().vChatMapNote != null) {
                                        this.drawChat(graphics, Param.getInstance().vChatMapNote, this.tabStyleInstance.GettabBottomY + 8);
                                    }
                                } else {
                                    Param.getInstance().getClass();
                                    if (Param.getInstance().bytChatNoteType == 8) {
                                        if (Param.getInstance().vChatFightNote != null) {
                                            this.drawChat(graphics, Param.getInstance().vChatFightNote, this.tabStyleInstance.GettabBottomY + 8);
                                        }
                                    } else {
                                        Param.getInstance().getClass();
                                        if (Param.getInstance().bytChatNoteType == 7 && Param.getInstance().vSystemNote != null) {
                                            this.drawChat(graphics, Param.getInstance().vSystemNote, this.tabStyleInstance.GettabBottomY + 8);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void setPetSet() {
        if (this.shtOneMenuMove == Param.getInstance().bytPetSaleType) {
            this.setButton(1, 100, 2);
        } else {
            this.setButton(1, 0, 2);
        }
        this.strOneDescribeChar = StringManager.wenZi(this.strOneMenuOption[this.getOneMove() * 2 + 1], StringManager.getNewLineW());
        this.setWordMove();
    }

    private void clearPetSet() {
        this.strOneMenuOption = null;
        this.strOneDescribeChar = null;
        Param.getInstance().IntRgbColor = null;
    }

    public void drawTaskList(Graphics graphics) {
        this.drawTitlTabBackGround(graphics, "Nhiệm vụ");
        this.bytTitleMove = this.tabStyleInstance.getTabCurrentIndex();
        int n = this.tabStyleInstance.GettabBottomY + Macro.FONTHEIGHT + 4;
        int n2 = Macro.FONTHEIGHT + 2;
        this.scroll_OFF = DCanvas.getInstance().blnSpoolr ? ScrollText.arrowhead_width : (byte) 0;
        DrawBase.drawBox(10, this.tabStyleInstance.GettabBottomY + 2, this.fromTable.getCell(2, 1).getCellW() - 12 - 8 - this.scroll_OFF, n2, new int[]{15195580}, true);
        DrawBase.drawString("Tên nhiệm vụ", 12, this.tabStyleInstance.GettabBottomY + 2, 0, 20);
        if (this.bytTitleMove == 1) {
            DrawBase.drawString("Vị trí", Macro.SCREEN_WIDTH - 12 - this.scroll_OFF, this.tabStyleInstance.GettabBottomY + 2, 0, 24);
        }
        this.drawOption(graphics, this.bytMenuState, this.bytTitleMove == 0 ? Param.getInstance().vTaskList : this.vTempVessel, (short) 12, (short) n, this.shtOneMenuMove, this.shtOneMenuRoll, this.bytWordMaxH);
        DrawBase.drawString("Nhiệm vụ tuần hoàn" + Param.getInstance().Recv_Task + "/" + Param.getInstance().Recv_Total_Task, Macro.SCREEN_WIDTH >> 1, Macro.SCREEN_HEIGHT - Macro.FONTHEIGHT - 4, 0, 17);
    }

    private void drawPack(Graphics graphics) {
        LayoutStyle.getInstance().drawFullBackGround(13866046);
        byte by = this.tabStyleInstance.getTabCurrentIndex();
        LayoutStyle.getInstance().drawEarBox(graphics, "Túi", 9263661, IDefines.GLOBAL_UI_MAIN_TITLEBOX_X, 0, IDefines.GLOBAL_UI_MAIN_TITLEBOX_WIDTH, 30);
        LayoutStyle.getInstance().drawBeforeBackGround(this.GT_PACK_TABLE.getCell(2, 1).getCellX(), this.GT_PACK_TABLE.getCell(2, 1).getCellY(), Macro.SCREEN_WIDTH, this.GT_PACK_TABLE.getCell(2, 1).getCellH() + this.GT_PACK_TABLE.getCell(3, 1).getCellH() + this.GT_PACK_TABLE.getCell(4, 1).getCellH() + this.GT_PACK_TABLE.getCell(5, 1).getCellH(), new int[]{8142636, 14995858, 16314576});
        this.tabStyleInstance.drawTabStyle(graphics);
        if (this.blnTabPet) {
            if (ORPMe.ME.blnSelectedRide) {
                IDefines.MENU_UI_PET_ATTRI_TITLE_VAL[2] = String.valueOf(ORPMe.ME.bytSpeed);
            }
            LayoutStyle.getInstance().setFont(IDefines.MENU_UI_PET_ATTRI_TITLE, IDefines.MENU_UI_PET_ATTRI_TITLE_VAL, (Macro.SCREEN_WIDTH >> 1) + 12, (short) (this.GT_PACK_TABLE.getCell(2, 1).getCellY() + 7 + 8));
            LayoutStyle.getInstance().drawFontFrame(graphics);
        } else {
            this.rView.drawAbilityRect(graphics);
        }
        this.rView.drawViewStyle(graphics, Param.getInstance().playerColne);
        Param.getInstance().personalPackage.reset(Param.getInstance().packageBoxMaxNum);
        Param.getInstance().personalPackage.draw(this.bytMenuState == -35 || this.bytMenuState == -72 ? Param.getInstance().hShopPackage : Param.getInstance().hPackage);
        this.drawMoney(graphics, ORPMe.Gold, -1, this.MENU_HEIGHT, true);
    }

    public void drawExpandButton(Graphics graphics, String string, int n, int n2) {
        int n3 = Macro.font.stringWidth(string) + 4;
        int n4 = Macro.FONTHEIGHT + 4;
        DrawBase.drawBox(n, n2, n3, n4, IDefines.MENU_SOFTBUTTON_EXPAND_COLOR, true);
        DrawBase.drawString(string, n + 2, n2 + 2, 8399402, 20);
    }

    public void initPack() {
        this.GT_PACK_TABLE = new GridTable((short) 0, (short) 0, Macro.SCREEN_WIDTH, Macro.SCREEN_HEIGHT, this.GT_PACK_ROW, this.GT_PACK_COL, this.GT_PACK_ROW_PERCENT, this.GT_PACK_COL_PERCENT);
        this.tabStyleInstance.initTabStyle((short) 8, this.GT_PACK_TABLE.getCell(3, 1).getCellY(), (short) (IDefines.GLOBAL_UI_MAIN_TAB_WIDTH - 8), (short) 32, this.strTabTitlestr, this.bytTagMove);
        this.rView = new RoleViewStyle((short) ((Macro.SCREEN_WIDTH >> 1) - 115 + 12), (short) (this.GT_PACK_TABLE.getCell(2, 1).getCellY() + 12), !this.blnTabPet, true, true, false);
        this.rView.setAbilityFixed((Macro.SCREEN_WIDTH >> 1) + 12, (short) (this.GT_PACK_TABLE.getCell(2, 1).getCellY() + 8), 1);
        Param.getInstance().personalPackage = new PackageBox(4, this.GT_PACK_TABLE.getCell(4, 1).getCellY() + 6, IDefines.GLOBAL_UI_MAIN_TAB_WIDTH, (short) (Macro.SCREEN_HEIGHT - 38 - this.tabStyleInstance.GettabBottomY - 12), Param.getInstance().packageBoxMaxNum, false);
        if (Param.getInstance().EquipIndex != -1) {
            Param.getInstance().personalPackage.setBoxIndex(Param.getInstance().EquipIndex, true);
        }
        if (this.ChooseJewel) {
            DCanvas.getInstance().setNetLoad(true);
            NetSend.getInstance().sendFramePeculiar((byte) 1, (byte) 0, 0, -1, (byte) 0);
            this.tabStyleInstance.TabFocuse = false;
            Param.getInstance().personalPackage.setFocal(true);
            this.setPointOut("Vui lòng chọn đá quý bạn muốn gắn");
        } else {
            this.tabStyleInstance.TabFocuse = Param.getInstance().EquipIndex == -1;
        }
    }

    public void drawNpcStorage(Graphics graphics) {
        DCanvas.getInstance().drawTitleFullsee(graphics, this.strOneTitlestr, 9263661, this.strOneMenuOption == null ? 0 : this.strOneMenuOption.length, this.getOneMove(), this.bytWordMaxH);
        Param.getInstance().npcShopBarPackage.reset(Param.getInstance().hNpcStorageBoxMaxNum);
        Param.getInstance().npcShopBarPackage.draw(Param.getInstance().hNpcStorage);
        if (this.tabStyleInstance != null && Param.getInstance().personalPackage != null) {
            this.tabStyleInstance.drawTabStyle(graphics);
            byte by = Param.getInstance().npcShopOursNumPack[this.tabStyleInstance.getTabCurrentIndex()];
            Param.getInstance().personalPackage.reset(by);
            String string = Param.getInstance().npcShopOursTabArray[this.tabStyleInstance.getTabCurrentIndex()];
            Hashtable hashtable = (Hashtable) Param.getInstance().hNpcOursPackTable.get(string);
            if (hashtable != null) {
                Param.getInstance().personalPackage.draw(hashtable);
            }
        }
        if (Param.getInstance().npcShopBarPackage.getFocal()) {
            Param.getInstance().npcShopBarPackage.setPopupDialog();
        } else if (Param.getInstance().personalPackage.getFocal()) {
            Param.getInstance().personalPackage.setPopupDialog();
        }
        this.drawMoney(graphics, ORPMe.Gold, -1, this.MENU_HEIGHT + 1, true);
    }

    public void drawNpcShop(Graphics graphics) {
        DCanvas.getInstance().drawTitleFullsee(graphics, this.strOneTitlestr, 9263661, this.strOneMenuOption == null ? 0 : this.strOneMenuOption.length, this.getOneMove(), this.bytWordMaxH);
        Param.getInstance().npcShopBarTabStyle.drawTabStyle(graphics);
        Hashtable hashtable = (Hashtable) Param.getInstance().hNpcBarPackTable.get(new Integer(Param.getInstance().npcShopBarTabStyle.getTabCurrentIndex()));
        int n = hashtable.size();
        n = n < 16 ? 16 : n / 8 * 8 + (n % 8 == 0 ? 0 : 8);
        Param.getInstance().npcShopBarPackage.reset(n);
        if (hashtable != null) {
            Param.getInstance().npcShopBarPackage.draw(hashtable);
        }
        this.tabStyleInstance.drawTabStyle(graphics);
        byte by = Param.getInstance().npcShopOursNumPack[this.tabStyleInstance.getTabCurrentIndex()];
        Param.getInstance().personalPackage.reset(by);
        String string = Param.getInstance().npcShopOursTabArray[this.tabStyleInstance.getTabCurrentIndex()];
        Hashtable hashtable2 = (Hashtable) Param.getInstance().hNpcOursPackTable.get(string);
        if (hashtable2 != null) {
            Param.getInstance().personalPackage.draw(hashtable2);
        }
        if (Param.getInstance().npcShopBarPackage.getFocal()) {
            Param.getInstance().npcShopBarPackage.setPopupDialog();
        } else if (Param.getInstance().personalPackage.getFocal()) {
            Param.getInstance().personalPackage.setPopupDialog();
        }
        this.drawMoney(graphics, ORPMe.Gold, -1, this.MENU_HEIGHT + 1, true);
    }

    public void drawPropList(Graphics graphics) {
        DCanvas.getInstance().drawTitleFullsee(graphics, this.strOneTitlestr, 9263661, this.strOneMenuOption == null ? 0 : this.strOneMenuOption.length, this.getOneMove(), this.bytWordMaxH);
        if (Param.getInstance().oSelectNpc != null) {
            Param.getInstance().oSelectNpc.draw(graphics, Macro.npcOneX, Macro.npcOneY);
        }
        PopupDialog.drawPopupRect(graphics, this.strOneDescribeChar, 0, Macro.npcRectX, Macro.npcRectY, Macro.npcRectW, Macro.npcRectH, true);
        if (this.blnTab) {
            if (this.bytMoveType == 0) {
                this.drawRoleRect(graphics, this.strOneTitlestr, this.strRectPropName, this.intColor, this.bytMoveType, this.bytRoleMove, (short) 0, (short) 0);
            } else {
                this.drawRoleRect(graphics, this.strOneTitlestr, this.strRectPropName, this.intColor, this.bytMoveType, this.bytPropMove, this.shtOneMenuMove, this.shtOneMenuRoll);
            }
            this.drawTagChooseRect(graphics, this.bytTagMove);
            this.drawPackTag(graphics);
        }
        this.drawPropRect(graphics, this.bytMenuState == -35 || this.bytMenuState == -72 ? Param.getInstance().hShopPackage : Param.getInstance().hPackage, (short) (Macro.SCREEN_HEIGHT >> 1), Macro.shtRectHeight, this.bytPropMove, this.shtOneMenuMove, this.shtOneMenuRoll, (byte) 1);
        if (bln_show_prope) {
            PopupDialog.drawPopupRect(graphics, new String[]{""}, 0, 12, 64, Macro.SCREEN_WIDTH - 24 - 2, Macro.SCREEN_HEIGHT - 77 - 40, true);
            if (Param.getInstance().blnColorInfo) {
                this.drawWordMove_Y_Color(graphics, (short) (64 + Macro.FONTHEIGHT), (short) (Macro.SCREEN_HEIGHT - 77 - 40 - 2 * Macro.FONTHEIGHT));
            } else if (this.strOneDescribeChar != null) {
                this.drawWordMove_Y(graphics, this.strOneDescribeChar, (short) 64, (short) (Macro.SCREEN_HEIGHT - 77 - 40));
            }
        }
        this.drawMoney(graphics, ORPMe.Gold, -1, this.MENU_HEIGHT + 1, true);
    }

    public void drawShopPropList(Graphics graphics) {
        int n = 6896918;
        this.drawTitlTabBackGround(graphics, "Cửa hàng điểm");
        int n2 = Macro.SCREEN_HEIGHT - 31 - (this.tabStyleInstance.GettabBottomY + 2) - 2;
        int n3 = this.tabStyleInstance.GettabBottomY + 2 + (n2 >> 1) + 2 + (Macro.FONTHEIGHT >> 1);
        DrawBase.drawBox(14, this.tabStyleInstance.GettabBottomY + 2 + 4, this.fromTable.getCell(2, 1).getCellW() - 12 - 16, (n2 >> 1) - 4 - (Macro.FONTHEIGHT >> 1), new int[]{12026667, 14995858, 16642234}, true);
        Param.getInstance().personalPackage.draw((Hashtable) Param.getInstance().hMallPackage.get(new Integer(this.tabStyleInstance.getTabCurrentIndex())));
        DrawBase.drawBox(14, n3, this.fromTable.getCell(2, 1).getCellW() - 12 - 16, (n2 >> 1) - 6 - (Macro.FONTHEIGHT >> 1), new int[]{12026667, 14995858, 16642234}, true);
        StringManager.drawWordRightToLeft(Param.strNotice, 14, this.tabStyleInstance.GettabBottomY + 2 + (n2 >> 1) + 2 - (Macro.FONTHEIGHT >> 1), this.fromTable.getCell(2, 1).getCellW() - 12 - 16, 0xFF0000, 0, true);
        StringManager.drawWordMove(graphics, this.strOneDescribeChar, 22, n3 + 4, (n2 >> 1) - 6 - Macro.FONTHEIGHT, n, 20);
        this.drawPoint(graphics, Macro.FEE_POINT, -1, this.MENU_HEIGHT, false);
    }

    public void drawMallPackList(Graphics graphics, byte by) {
        int n = 6896918;
        DCanvas.getInstance().drawTileTextBG(graphics, this.strOneTitlestr);
        int n2 = Macro.SCREEN_HEIGHT >> 2;
        DrawBase.drawBox(10, 40, this.fromTable.getCell(2, 1).getCellW() - 12 - 8, n2, new int[]{12026667, 14995858, 16642234}, true);
        StringManager.drawWordMove(graphics, this.strOneDescribeChar, 22, 42, n2 - 4, n, 20);
        int n3 = 38 + n2 + 2;
        int n4 = Macro.SCREEN_HEIGHT - n3 - 38;
        this.bytWordMaxH = (byte) (n4 / Macro.FONTHEIGHT);
        DrawBase.drawBox(10, n3, this.fromTable.getCell(2, 1).getCellW() - 12 - 8, n4, new int[]{12026667, 14995858, 16642234}, true);
        this.drawOption(graphics, by, Param.getInstance().MALL_SHOW_LIST, (short) 10, (short) (38 + n2 + 2 + 8), this.shtOneMenuMove, this.shtOneMenuRoll, this.bytWordMaxH);
        this.drawPoint(graphics, Macro.FEE_POINT, -1, this.MENU_HEIGHT, false);
    }

    public void drawMallAlert(Graphics graphics) {
        int n = 6896918;
        DCanvas.getInstance().drawTileTextBG(graphics, this.strOneTitlestr);
        int n2 = Macro.SCREEN_HEIGHT >> 2;
        int n3 = 28;
        StringManager.drawWordMove(graphics, this.strOneDescribeChar, 18, 42, Macro.SCREEN_HEIGHT - n3 - 38, n, 20);
        this.drawPoint(graphics, Macro.FEE_POINT, -1, this.MENU_HEIGHT, false);
    }

    public void drawMassage(Graphics graphics) {
        int n = 65280;
        DCanvas.getInstance().drawTileTextBG(graphics, this.strOneTitlestr);
        int n2 = Macro.SCREEN_HEIGHT >> 2;
        int n3 = 28;
        if (this.strOneDescribeChar != null) {
            this.scrolltext.setText(this.strOneDescribeChar);
            this.scrolltext.drawBread(graphics);
        }
        this.drawPoint(graphics, Macro.FEE_POINT, -1, this.MENU_HEIGHT, false);
    }

    public void setPetList() {
        if (ORPMe.ME.hPackagePet.containsKey(new Integer(this.getPropRectMove())) || ORPMe.ME.hPackagePet != null && ORPMe.ME.hPackagePet.containsKey(new Integer(this.getPropRectMove()))) {
            short s = 0;
            int n = 0;
            if (!this.blnTab) {
                s = ((PackageObject) Param.getInstance().hPackage.get((Object) new Integer((int) this.getPropRectMove()))).shtLevel;
                n = ((PackageObject) Param.getInstance().hPackage.get((Object) new Integer((int) this.getPropRectMove()))).petKey;
                this.strRectPropName = ((PackageObject) Param.getInstance().hPackage.get((Object) new Integer((int) this.getPropRectMove()))).strName;
            }
            if (Pet.getInstance() == null) {
                new Pet();
            }
            this.intColor = 0;
            if (n == ORPMe.ME.intPetId) {
                this.strRectPropName = String.valueOf(this.strRectPropName);
            }
            this.setButton(1, 4, 2);
        } else {
            if (Pet.getInstance() != null) {
                Pet.getInstance().clear();
            }
            this.intColor = 0;
            this.strRectPropName = "";
            this.setButton(1, 100, 2);
        }
    }

    public void updatePetList() {
        if (ORPMe.ME.hPackagePet != null) {
            Enumeration enumeration = ORPMe.ME.hPackagePet.elements();
            while (enumeration.hasMoreElements()) {
                PackageObject packageObject = (PackageObject) enumeration.nextElement();
                if (packageObject.shtType == 0 || packageObject.shtType == 1 || packageObject.shtType != 2) {
                    continue;
                }
                ORPMe.ME.setPet(packageObject.petKey, packageObject.shtPngId, packageObject.shtAnuId, packageObject.shtType);
            }
        }
    }

    public void setPetPack() {
        if ((PackageObject) Param.getInstance().hPackage.get(new Integer(this.getPropRectMove())) != null || (PackageObject) Param.getInstance().hPetPackEquip.get(new Integer(this.getPropRectMove())) != null) {
            this.setButton(1, 4, 2);
        } else {
            this.setButton(1, 100, 2);
        }
    }

    public void drawPetProp(Graphics graphics) {
        graphics.setColor(13808780);
        graphics.fillRect(0, 0, Macro.SCREEN_WIDTH, Macro.SCREEN_HEIGHT);
    }

    public void drawPetGoodList(Graphics graphics) {
        short s = (short) (Macro.SCREEN_HEIGHT - Macro.shtRectHeight - Macro.FONTHEIGHT * 2 - 2 - 12);
        short s2 = (short) (Macro.SCREEN_HEIGHT - 3 - s - 14);
        graphics.setColor(13866046);
        graphics.fillRect(0, 0, Macro.SCREEN_WIDTH, Macro.SCREEN_HEIGHT);
        this.drawPropRect(graphics, Param.getInstance().hPackage, s2, s, this.bytPropMove, this.shtOneMenuMove, this.shtOneMenuRoll, this.bytMoveType);
        this.drawMoney(graphics, ORPMe.Gold, -1, this.MENU_HEIGHT + 6, true);
    }

    public void setNpcDialog() {
        Param.popupDialogInstance.setShow(false);
        short s = (short) (Macro.SCREEN_HEIGHT / 2);
        byte by = (byte) ((this.strOneDescribeChar != null ? this.strOneDescribeChar.length : 0) + this.strOneMenuOption.length + 1);
        if (by > Macro.bytMaxFullRow) {
            byte by2 = (byte) ((s - Macro.FONTHEIGHT - Macro.NPC_HEIGHT) / Macro.FONTHEIGHT);
            this.shtNDialogY = this.strOneDescribeChar.length > by2 ? (short) (s + Macro.FONTHEIGHT) : (short) (Macro.FONTHEIGHT + Macro.NPC_HEIGHT + (this.strOneDescribeChar.length + 1) * Macro.FONTHEIGHT);
            this.bytPackMaxH = (byte) ((Macro.SCREEN_HEIGHT - this.shtNDialogY - 40 - 8) / Macro.FONTHEIGHT);
        } else {
            this.shtNDialogY = this.strOneDescribeChar == null ? (short) (Macro.FONTHEIGHT + Macro.NPC_HEIGHT) : (short) (Macro.SCREEN_HEIGHT >> 1);
            this.bytPackMaxH = (byte) this.strOneMenuOption.length;
        }
    }

    private void drawMailSendGood(Graphics graphics) {
        this.drawFullTiTle(graphics, this.strOneTitlestr, this.strOneMenuOption == null ? 0 : this.strOneMenuOption.length, this.getOneMove(), this.bytWordMaxH);
        graphics.setColor(0);
        this.tabStyleInstance.drawTabStyle(graphics);
        Param.getInstance().personalPackage.draw(Param.getInstance().hPackage);
        short s = (short) (this.mailPackageBoxY + Param.getInstance().personalPackage.getLineNum() * 22 + 8);
        short s2 = (short) (Macro.SCREEN_HEIGHT - s - 35);
        LayoutStyle.getInstance().drawBeforeBackGround(4, s, IDefines.GLOBAL_UI_MAIN_TAB_WIDTH, s2, new int[]{8142636, 14995858, 16314576});
        if (this.bytMailMove == 1) {
            if (Param.getInstance().blnColorInfo) {
                short s3 = (short) ((this.MENU_HEIGHT + 18 - (Macro.FONTHEIGHT + 3 + 20 + Macro.FONTHEIGHT + 6 + 2)) / 2);
                short s4 = (short) (this.MENU_HEIGHT - 3 - s3 + 13);
                this.drawWordMove_Y_Color(graphics, (short) (s + Macro.FONTHEIGHT * 3 / 2), (short) (s2 - 2 * Macro.FONTHEIGHT));
            } else if (this.strOneDescribeChar != null) {
                short s5 = (short) ((this.MENU_HEIGHT + 18 - (Macro.FONTHEIGHT + 3 + 20 + Macro.FONTHEIGHT + 6 + 2)) / 2);
                short s6 = (short) (this.MENU_HEIGHT - 3 - s5 + 13);
                this.drawWordMove_Y(graphics, this.strOneDescribeChar, (short) (s + Macro.FONTHEIGHT * 3 / 2), (short) (s2 - 2 * Macro.FONTHEIGHT));
            }
            graphics.drawString(this.strRectPropName, Macro.SCREEN_WIDTH / 2 - this.strRectPropName.length() * Macro.FONTHEIGHT / 2, s + 5, 20);
        }
        if (this.bytMailSubtabIndex == 3) {
            this.drawMoney(graphics, ORPMe.Gold, 19, s + 10, true);
        }
    }

    public void sendMailGood(byte by) {
        Param.getInstance().hImgObject = null;
        Param.getInstance().hPackage = null;
        if (this.bytMailSubtabIndex == 3) {
            Param.getInstance().personalPackage.reset(0);
        }
        this.setNpcOption();
        Param.getInstance().bytPopRectStep = 0;
        if (this.bytMailSubtabIndex != 3) {
            NetSend.getInstance().sendNpcMoveOption(by, (short) 0, 0);
        }
    }

    public void drawNpcOneDialog(Graphics graphics) {
        byte by;
        DCanvas.getInstance().drawTitleFullsee(graphics, this.strOneTitlestr, 9263661, this.strOneMenuOption == null ? 0 : this.strOneMenuOption.length, this.getOneMove(), this.bytWordMaxH);
        if (this.strBakInfo != null && !this.strBakInfo.equals("")) {
            int n = Macro.font.stringWidth(this.strBakInfo);
            int n2 = n + 10;
            by = Macro.FONTHEIGHT;
            int n3 = Macro.SCREEN_WIDTH - n2 >> 1;
            int n4 = Macro.SCREEN_HEIGHT - by - 10;
            DrawBase.DrawString(this.strBakInfo, n3, n4, n2, by, (short) 3, 0xFFFFFF);
        }
        if (this.strOneMenuOption != null && Param.getInstance().blnIsOk) {
            DrawBase.drawBox(12, this.shtNDialogY, StringManager.getNewLineW(), Macro.SCREEN_HEIGHT - this.shtNDialogY - 37, new int[]{12026667, 16442771, 16642234}, true);
            LayoutStyle.getInstance().drawSelectBox(12, this.shtNDialogY + this.shtOneMenuMove * Macro.FONTHEIGHT, StringManager.getNewLineW(), Macro.FONTHEIGHT);
            short[] sArray = null;
            String[] stringArray = null;
            if (Param.getInstance().oSelectNpc != null && Param.getInstance().vMenuMemory != null) {
                sArray = ((MenuObject) Param.getInstance().vMenuMemory.elementAt((int) (Param.getInstance().vMenuMemory.size() - 1))).shtIcon;
                stringArray = ((MenuObject) Param.getInstance().vMenuMemory.elementAt((int) (Param.getInstance().vMenuMemory.size() - 1))).strOptionMoney;
            }
            if (this.strOneMenuOption.length > this.bytPackMaxH) {
                by = 0;
                while (by < this.bytPackMaxH) {
                    this.drawWordRoll(graphics, by, stringArray == null ? null : stringArray[by + this.shtOneMenuRoll], sArray == null ? (short) 0 : sArray[by + this.shtOneMenuRoll], this.strOneMenuOption[by + this.shtOneMenuRoll], Param.getInstance().blnNpcOptionWork ? 0 : Macro.INT_PROP_COLOR[0], (short) (Macro.SCREEN_WIDTH >> 2), (short) (this.shtNDialogY + by * Macro.FONTHEIGHT), this.getOneMove() == by);
                    by = (byte) (by + 1);
                }
                DCanvas.getInstance().setOptionSpoolr(graphics, Macro.SCREEN_WIDTH - 12, this.shtNDialogY, Macro.SCREEN_HEIGHT - 35, this.bytPackMaxH, this.strOneMenuOption.length, this.getOneMove(), false);
            } else {
                by = 0;
                while (by < this.strOneMenuOption.length) {
                    this.drawWordRoll(graphics, by, stringArray == null ? null : stringArray[by], sArray == null ? (short) 0 : sArray[by], this.strOneMenuOption[by + this.shtOneMenuRoll], Param.getInstance().blnNpcOptionWork ? 0 : Macro.INT_PROP_COLOR[0], (short) (Macro.SCREEN_WIDTH >> 2), (short) (this.shtNDialogY + by * Macro.FONTHEIGHT), this.getOneMove() == by);
                    by = (byte) (by + 1);
                }
            }
        }
        if (Param.getInstance().oSelectNpc != null) {
            Param.getInstance().oSelectNpc.draw(graphics, Macro.npcOneX, Macro.npcOneY);
        }
        PopupDialog.drawPopupRect(graphics, this.strOneDescribeChar, 0, Macro.npcRectX, Macro.npcRectY, Macro.npcRectW, Macro.npcRectH, true);
    }

    private void drawHelpOption(Graphics graphics) {
        this.drawFullTiTle(graphics, this.strOneTitlestr, this.strOneMenuOption.length, this.getOneMove(), this.bytWordMaxH);
        graphics.setColor(0);
        this.drawWordMove_Y(graphics, this.strOneDescribeChar, (short) (Macro.FONTHEIGHT + 16), (short) (this.shtNDialogY - Macro.FONTHEIGHT * 2 - 16));
        LayoutStyle.getInstance().drawSelectBox(12, this.shtNDialogY + this.shtOneMenuMove * Macro.FONTHEIGHT, StringManager.getNewLineW(), Macro.FONTHEIGHT);
        short s = 13;
        short s2 = 0;
        if (this.strOneMenuOption.length > this.bytPackMaxH) {
            byte by = 0;
            while (by < this.bytPackMaxH) {
                s2 = (short) (this.shtNDialogY + by * Macro.FONTHEIGHT);
                if (this.imgHelpIcon != null) {
                    GameUI.getInstance().drawOptionImage(graphics, this.imgHelpIcon, s, s2, (byte) 20);
                    graphics.drawString(this.strOneMenuOption[by + this.shtOneMenuRoll], s + 16, s2, 20);
                }
                by = (byte) (by + 1);
            }
            DCanvas.getInstance().setOptionSpoolr(graphics, Macro.SCREEN_WIDTH - 12, this.shtNDialogY, Macro.SCREEN_HEIGHT - 35, this.bytPackMaxH, this.strOneMenuOption.length, this.getOneMove(), false);
        } else {
            int n = 0;
            while (n < this.strOneMenuOption.length) {
                s2 = (short) (this.shtNDialogY + n * Macro.FONTHEIGHT);
                if (this.imgHelpIcon != null) {
                    GameUI.getInstance().drawOptionImage(graphics, this.imgHelpIcon, s, s2, (byte) 20);
                }
                graphics.drawString(this.strOneMenuOption[n], s + 16, s2, 20);
                n = (byte) (n + 1);
            }
        }
    }

    private void initInlay() {
        this.clearTwoRect();
        this.sloginY = 50;
        this._framY = this.sloginY + Macro.FONTHEIGHT + 2;
        this.setInlayPro(this._framY, true);
        if (!this.ChooseJewel) {
            this.setEquipPackage(Param.getInstance().hPackage, Param.getInstance().personalPackage.getCellBoxIndex());
        }
        this.inlayHole.setBoxIndex(Param.getInstance().HoleIndex, true);
        this.inlay[1].setAnimation(4);
        this.inlay[2].setAnimation(5);
        this.inlayHole.setPopupShow();
        this.strOneTitlestr = StrengthenState == 1 ? "Vui lòng chọn vị trí lỗ bạn muốn khảm" : "Vui lòng chọn vị trí lỗ bạn muốn tháo";
        this.setButton(1, 0, 2);
    }

    private void drawInlay(Graphics graphics) {
        DCanvas.getInstance().drawTileTextBG(graphics, "Túi");
        DrawBase.drawString(this.strOneTitlestr, Macro.SCREEN_WIDTH >> 1, this.sloginY, 9263661, 17);
        this.drawInlayPrp(graphics);
        if (Param.getInstance().describeText != null && !Param.getInstance().describeText.equals("")) {
            int n = Macro.SCREEN_HEIGHT - 31 - 12 - (this._framY + this._framH + Macro.FONTHEIGHT);
            Param.getInstance().describeText = StringManager.displaceNpcTalk(Param.getInstance().describeText);
            StringManager.drawWordMove(graphics, Param.getInstance().describeText, this._framX + Macro.FONTW, this._framY + this._framH + Macro.FONTHEIGHT, this._framW - (Macro.FONTW << 1), n, 9263661, 20);
        }
    }

    public void setInlayPro(int n, boolean bl) {
        if (this.inlay == null) {
            this.inlay = new QSprite[3];
            int n2 = 0;
            while (n2 < this.inlay.length) {
                this.inlay[n2] = ImageManager.loadSpriteById(1, ImageManager.EncodespriteId("inlayui", String.valueOf(n2) + "inlayui"), "inlayui", "inlayui", "ingame");
                ++n2;
            }
        }
        this.inlay[1].setAnimation(4);
        this._framX = 16;
        this._framY = n;
        this._framW = Macro.SCREEN_WIDTH - 24 - 8;
        this._framH = this.RoundHole * 3;
        this._holex = (this._framW - (this.RoundHole + this._offx) * 6 >> 1) + this._framX + (this.RoundHole >> 1);
        this._holey = this._framY + (this.RoundHole << 1) / 5 + (this.RoundHole >> 1);
        this._holew = (this.RoundHole + this._offx) * 6;
        this._holeh = (this.RoundHole + this._offx + 1) * 2;
        this.inlayHole = new PackageBox(this._holex, this._holey, this._holew, this._holeh, 12, bl);
        this.frameBottomY = this._framH;
        Param.getInstance().RunOnce = false;
    }

    public void drawInlayPrp(Graphics graphics) {
        DrawBase.drawBox(this._framX, this._framY - PackageBox.upDownScroll * Macro.FONTHEIGHT, this._framW, this._framH, new int[]{12026667, 14995858, 16642234}, true);
        this.inlayHole.drawInlay(graphics);
        int n = Param.getInstance().InlayIndex;
        String string = ((PackageObject) Param.getInstance().HSInlay.get((Object) new Integer((int) n))).DescribeProperty;
        if (this.bytMenuState == 11) {
            DrawBase.drawString(string, Macro.SCREEN_WIDTH >> 1, this._framY + this.frameBottomY, 9263661, 17);
        }
        if (Param.getInstance().DifferencePro) {
            DrawBase.drawString(string, 24, this._framY + this.frameBottomY - PackageBox.upDownScroll * Macro.FONTHEIGHT - 1, 1110603, 20);
        }
    }

    private boolean ErgodicArray(int n) {
        int n2 = Param.getInstance().InlayIndex;
        if (Param.getInstance().HSInlay.containsKey(new Integer(n2))) {
            PackageObject packageObject = (PackageObject) Param.getInstance().HSInlay.get(new Integer(n2));
            byte[] byArray = packageObject.holeDate;
            if (StrengthenState == 1) {
                return byArray[n] == 1;
            }
            if (StrengthenState == 2) {
                return byArray[n] == 2 || byArray[n] == 3 || byArray[n] == 4;
            }
        }
        return false;
    }

    public void setEquipPackage(Hashtable hashtable, int n) {
        Param.getInstance().HSInlay = hashtable;
        Param.getInstance().InlayIndex = n;
    }

    private void drawLifeSkill(Graphics graphics) {
        this.drawTitlTabBackGround(graphics, "Kỹ năng");
        byteSkill = this.tabStyleInstance.getTabCurrentIndex();
        int n = this.tabStyleInstance.GettabBottomY + 5;
        this.drawOption(graphics, this.bytMenuState, this.vTempVessel, (short) 12, (byte) n, this.shtOneMenuMove, this.shtOneMenuRoll, this.bytWordMaxH);
    }

    private void drawLifeSkillPurification(Graphics graphics, String string) {
        DCanvas.getInstance().drawTileTextBG(graphics, string);
        this.drawPropRect(graphics, Param.getInstance().hPackage, (short) (Macro.FONTHEIGHT + 3), Macro.shtRectHeight, this.bytPropMove, this.shtOneMenuMove, this.shtOneMenuRoll, (byte) 1);
        DrawBase.drawBox(this.gridTable.getCell((int) 2, (int) 1).cell_x + 12 + 2, this.gridTable.getCell((int) 2, (int) 1).cell_y + Macro.FONTHEIGHT, this.gridTable.getCell((int) 2, (int) 1).cell_w - 24 - 4, this.strboxwidth, new int[]{16049801, 15768897, 14995858}, true);
        DrawBase.drawString(this.strRectPropName, Macro.SCREEN_WIDTH / 2, this.gridTable.getCell((int) 2, (int) 1).cell_y + Macro.FONTHEIGHT + 2, 9263661, 17);
        this.setButton(1, 0, 2);
    }

    private void drawTroopImageOption(Graphics graphics) {
        this.drawTitlTabBackGround(graphics, "Đội");
        this.bytChatTeam = this.tabStyleInstance.getTabCurrentIndex();
        Param.getInstance();
        if (Param.bytChatSubTeamNumCount[this.bytChatTeam] != 0) {
            this.drawOption(graphics, this.bytMenuState, Param.getInstance().vTeam, (short) 12, (short) (this.tabStyleInstance.GettabBottomY + 8), this.shtOneMenuMove, this.shtOneMenuRoll, this.bytWordMaxH);
        }
    }

    private void drawFriendOption(Graphics graphics) {
        block7:
        {
            short s;
            this.drawTitlTabBackGround(graphics, "Bạn bè");
            this.bytChatRelation = this.tabStyleInstance.getTabCurrentIndex();
            int n = this.tabStyleInstance.GettabBottomY + Macro.FONTHEIGHT + 4;
            short s2 = (short) ((Param.getInstance().shtNoncePage - 1) * 20);
            short s3 = this.bytWordMaxH > 20 ? (short) 20 : (short) this.bytWordMaxH;
            int n2 = 0;
            int n3 = Macro.FONTHEIGHT + 2;
            this.scroll_OFF = DCanvas.getInstance().blnSpoolr ? ScrollText.arrowhead_width : (byte) 0;
            DrawBase.drawBox(10, this.tabStyleInstance.GettabBottomY + 2, this.fromTable.getCell(2, 1).getCellW() - 12 - 8 - this.scroll_OFF, n3, new int[]{15195580}, true);
            DrawBase.drawString("Tên", 12, this.tabStyleInstance.GettabBottomY + 2, 0, 20);
            DrawBase.drawString("Lớp", Macro.SCREEN_WIDTH >> 1, this.tabStyleInstance.GettabBottomY + 2, 0, 17);
            DrawBase.drawString("Nghề nghiệp", Macro.SCREEN_WIDTH - 12 - this.scroll_OFF, this.tabStyleInstance.GettabBottomY + 2, 0, 24);
            if (this.blnIsLoadOver && Param.getInstance().vCommonList != null && !Param.getInstance().vCommonList.isEmpty() && Param.getInstance().IntRgbColor != null) {
                LayoutStyle.getInstance().drawSelectBox(10, n + this.shtOneMenuMove * Macro.FONTHEIGHT, StringManager.getNewLineW() + 6 - this.scroll_OFF, Macro.FONTHEIGHT);
            }
            if (this.bytMenuState == -31 && this.blnIsLoadOver && this.shtMenuMoveLength != 0) {
                s = s2;
                while (s < s2 + this.shtMenuMoveLength) {
                    this.drawGameRelation(graphics, (TeamObject) Param.getInstance().vCommonList.elementAt(s), (short) (n + n2 * Macro.FONTHEIGHT), s, this.shtOneMenuMove);
                    ++n2;
                    s = (short) (s + 1);
                }
            }
            if (this.bytMenuState != -32 && this.bytMenuState != -33 || !this.blnIsLoadOver || Param.getInstance().vCommonList == null || Param.getInstance().vCommonList.isEmpty()) {
                break block7;
            }
            if (this.shtMenuMoveLength > s3) {
                s = s2;
                while (s < s2 + s3) {
                    this.drawGameRelation(graphics, (TeamObject) Param.getInstance().vCommonList.elementAt(s + this.shtOneMenuRoll), (short) (Macro.FONTHEIGHT + 4 + n2 * Macro.FONTHEIGHT), s, this.shtOneMenuMove);
                    ++n2;
                    s = (short) (s + 1);
                }
            } else {
                s = s2;
                while (s < s2 + this.shtMenuMoveLength) {
                    this.drawGameRelation(graphics, (TeamObject) Param.getInstance().vCommonList.elementAt(s), (short) (n + n2 * Macro.FONTHEIGHT), s, this.shtOneMenuMove);
                    ++n2;
                    s = (short) (s + 1);
                }
            }
        }
    }

    private void drawMasterList(Graphics graphics) {
        this.drawTitlTabBackGround(graphics, "Sư đồ");
        this.intRelation = this.tabStyleInstance.getTabCurrentIndex();
        int n = this.tabStyleInstance.GettabBottomY + 8;
        if (this.blnIsLoadOver && Param.bytChatSubMasterNumCount[this.intRelation] != 0) {
            this.drawOption(graphics, this.bytMenuState, this.vTempVessel, (short) 12, (short) n, this.shtOneMenuMove, this.shtOneMenuRoll, this.bytWordMaxH);
        }
    }

    private void drawEmailList(Graphics graphics, Vector vector) {
        this.drawTileTextBG(graphics, "Thư");
        int n = 38 + Macro.FONTHEIGHT + 4;
        this.scroll_OFF = DCanvas.getInstance().blnSpoolr ? ScrollText.arrowhead_width : (byte) 0;
        DrawBase.drawBox(8, 39, Macro.SCREEN_WIDTH - 12 - 4 - this.scroll_OFF, Macro.FONTHEIGHT + 4, new int[]{15195580}, true);
        DrawBase.drawString(IDefines.PERSONAL_EMALL_TITLE_STRING[0], 12, 41, 0, 20);
        DrawBase.drawString(IDefines.PERSONAL_EMALL_TITLE_STRING[1], 6 + (Macro.SCREEN_WIDTH - 12) - 6 - this.scroll_OFF, 41, 0, 24);
        this.drawOption(graphics, this.bytMenuState, vector, (short) 12, (short) n, this.shtOneMenuMove, this.shtOneMenuRoll, this.bytWordMaxH);
        int n2 = 6 + (Macro.SCREEN_WIDTH - 12) - (ScrollText.arrowhead_width >> 1);
        int n3 = 38;
        int n4 = Macro.SCREEN_HEIGHT - 38 - 31;
    }

    public void LoadPartnerDate() {
        this.setGrrdTableFrom();
        if (Param.getInstance().PARTNER_relation > 0) {
            String[] stringArray = new String[]{Param.getInstance().partner.strNickName, "" + Param.getInstance().partner.shtLevel, ORole.getOccName(Param.getInstance().partner.bytOccupation), Param.getInstance().partner.bytCountry == 1 ? "龙之传人" : "恶魔之子", Param.getInstance().partner.strConsortia};
            this.role_framex = (short) 16;
            this.role_framey = (short) 46;
            this.role_framew = (short) ((Macro.SCREEN_WIDTH >> 1) - Macro.FONTW - this.role_framex - 4);
            this.role_frameh = (short) (105 > LayoutStyle.getInstance().FontFramHeight ? 113 : LayoutStyle.getInstance().FontFramHeight + 8);
            LayoutStyle.getInstance().setFont(IDefines.PARTNER_UI_BASE, stringArray, this.role_framex + this.role_framew + 2, this.role_framey + 2);
            this._frameH = Macro.SCREEN_HEIGHT - 38 - 31 - 12 - this.role_frameh - 4;
            int n = this.role_framey + this.role_frameh + 4;
            if (this.setRolePartner()) {
                this.scrolltext = new ScrollText(this.getText, this.fromTable.getCell(2, 1).getCellX() + 12 + 4, n, this.fromTable.getCell(2, 1).getCellW() - 24, this._frameH, 8142636, (byte) 0);
            }
        }
        this.setButton(1, 4, 2);
    }

    private boolean setRolePartner() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(IDefines.PARTNER_UI_StringLove[0]);
        stringBuffer.append(Param.getInstance().partner.PARTNER_loves_value);
        stringBuffer.append("\n");
        stringBuffer.append(IDefines.PARTNER_UI_StringLove[1]);
        stringBuffer.append(Param.getInstance().partner.PARTNER_loves_ranking);
        stringBuffer.append("\n");
        stringBuffer.append(IDefines.PARTNER_UI_StringLove[2]);
        stringBuffer.append(Param.getInstance().partner.PARTNER_loves_days);
        stringBuffer.append("\n");
        stringBuffer.append(IDefines.PARTNER_UI_StringLove[3]);
        stringBuffer.append(Param.getInstance().partner.PARTNER_loves_Lv);
        stringBuffer.append("\n");
        this.getText = stringBuffer.toString();
        return true;
    }

    private void drawPartnerList(Graphics graphics) {
        this.PartnerTile = Param.getInstance().PARTNER_relation == 1 ? "Thư tình" : "Quan hệ hôn nhân";
        DCanvas.getInstance().drawTileTextBG(graphics, this.PartnerTile);
        if (Param.getInstance().PARTNER_relation > 0) {
            DrawBase.drawBox(this.role_framex, this.role_framey, this.role_framew, this.role_frameh, new int[]{11301955, 14995858}, true);
            Param.getInstance().partner.draw(graphics, this.role_framex + (this.role_framew >> 1), this.role_framey + ((this.role_frameh >> 1) + (this.role_frameh >> 2)));
            LayoutStyle.getInstance().drawFontFrame(graphics);
            int n = this.fromTable.getCell(2, 1).getCellX() + 12;
            int n2 = this.fromTable.getCell(2, 1).getCellW() - 24;
            int n3 = this.role_framey + this.role_frameh + 2;
            DrawBase.drawBox(n, n3, n2, this._frameH, new int[]{12026667, 14995858, 14995858, 16642234}, true);
            if (this.setRolePartner()) {
                this.scrolltext.drawBread(graphics);
            }
        }
    }

    public void drawTitlTabBackGround(Graphics graphics, String string) {
        LayoutStyle.getInstance().drawFullBackGround(13866046);
        LayoutStyle.getInstance().drawEarBox(graphics, string, 9263661, IDefines.GLOBAL_UI_MAIN_TITLEBOX_X, 0, IDefines.GLOBAL_UI_MAIN_TITLEBOX_WIDTH, 30);
        this.tabStyleInstance.drawTabStyle(graphics);
        LayoutStyle.getInstance().drawBeforeBackGround(this.fromTable.getCell(2, 1).getCellX(), this.tabStyleInstance.GettabBottomY, this.fromTable.getCell(2, 1).getCellW(), Macro.SCREEN_HEIGHT - this.tabStyleInstance.GettabBottomY - 31, new int[]{8142636, 14995858, 16314576});
        int n = this.fromTable.getCell(2, 1).getCellX() + this.fromTable.getCell(2, 1).getCellW() - ScrollText.arrowhead_width - 2;
        int n2 = this.tabStyleInstance.GettabBottomY;
        int n3 = Macro.SCREEN_HEIGHT - this.tabStyleInstance.GettabBottomY - 31;
        DCanvas.getInstance().setOptionSpoolr(graphics, n, n2, n2 + n3, this.bytWordMaxH, this.shtMenuMoveLength, this.getOneMove(), false);
    }

    public void drawTileTextBG(Graphics graphics, String string) {
        LayoutStyle.getInstance().drawFullBackGround(13866046);
        int n = 0;
        int n2 = 38;
        short s = Macro.SCREEN_WIDTH;
        int n3 = Macro.SCREEN_HEIGHT - n2 - 31;
        LayoutStyle.getInstance().drawEarBox(graphics, string, 9263661, IDefines.GLOBAL_UI_MAIN_TITLEBOX_X, 0, IDefines.GLOBAL_UI_MAIN_TITLEBOX_WIDTH, 30);
        LayoutStyle.getInstance().drawBeforeBackGround(n, n2, s, n3, new int[]{8142636, 14995858, 16314576});
        int n4 = 6 + (Macro.SCREEN_WIDTH - 12) - (ScrollText.arrowhead_width >> 1);
        int n5 = 38;
        int n6 = Macro.SCREEN_HEIGHT - 38 - 31;
        DCanvas.getInstance().setOptionSpoolr(graphics, n4, n5, n5 + n6, this.bytWordMaxH, this.shtMenuMoveLength, this.getOneMove(), false);
    }

    private void setGrrdTableFrom() {
        short s = 1;
        short s2 = 4;
        this.fromTable = new GridTable((short) 0, (short) 0, Macro.SCREEN_WIDTH, Macro.SCREEN_HEIGHT, s2, s, new short[]{10, 12, 66, 12}, new short[]{100});
    }

    public void openMake() {
        this.blnOpenMake = true;
        this.intMakeTimeMax = this.intMakeTime = 3000;
    }

    private void logicMake(int n) {
    }

    private void FriendTitleChoose() {
        byte by = this.tabStyleInstance.getTabCurrentIndex();
        if (this.bytChatRelation != by) {
            this.bytChatRelation = by;
            switch (this.bytChatRelation) {
                case 0: {
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendHailFellow((byte) 1, (byte) 1, "");
                    this.setState((byte) -31, "Danh sách bạn bè");
                    break;
                }
                case 1: {
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendHailFellow((byte) 1, (byte) 2, "");
                    this.setState((byte) -32, "Danh sách đen");
                    break;
                }
                case 2: {
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendHailFellow((byte) 1, (byte) 3, "");
                    this.setState((byte) -33, "Kẻ thù");
                }
            }
        }
    }

    public void drawGameRelation(Graphics graphics, TeamObject teamObject, short s, short s2, short s3) {
        int n = (Macro.SCREEN_WIDTH >> 1) - 12;
        this.scroll_OFF = DCanvas.getInstance().blnSpoolr ? ScrollText.arrowhead_width : (byte) 0;
        StringManager.drawWordRightToLeft(teamObject.strName, 12, s, n, teamObject.blnIsOnLine ? 0 : 0xC1C0C0, 1, s3 == s2);
        if (this.bytMenuState == -48) {
            DrawBase.drawString(teamObject.blnIsOnLine ? "" + teamObject.shtLevel : "-- ", Macro.SCREEN_WIDTH >> 1, s, teamObject.blnIsOnLine ? 0 : 0xC1C0C0, 17);
            String string = "";
            if (teamObject.blnIsOnLine) {
                string = ORole.getOccName(teamObject.bytOcc);
            }
            DrawBase.drawString(teamObject.blnIsOnLine ? string : "--", Macro.SCREEN_WIDTH - 12 - this.scroll_OFF, s, teamObject.blnIsOnLine ? 0 : 0xC1C0C0, 24);
        } else {
            DrawBase.drawString(teamObject.blnIsOnLine ? "" + teamObject.shtLevel : "-- ", Macro.SCREEN_WIDTH >> 1, s, teamObject.blnIsOnLine ? 0 : 0xC1C0C0, 17);
            String string = "";
            if (teamObject.blnIsOnLine) {
                string = ORole.getOccName(teamObject.bytOcc);
            }
            DrawBase.drawString(teamObject.blnIsOnLine ? string : "--", Macro.SCREEN_WIDTH - 12 - this.scroll_OFF, s, teamObject.blnIsOnLine ? 0 : 0xC1C0C0, 24);
        }
    }

    public void drawMailRecv(Vector vector, Graphics graphics) {
        this.drawFullTiTle(graphics, this.strOneTitlestr, vector != null ? vector.size() : 0, this.getOneMove(), this.bytWordMaxH);
        this.tabStyleInstance.drawTabStyle(graphics);
        if (vector == null) {
            return;
        }
        if (vector.isEmpty()) {
            return;
        }
        int n = 20;
        short s = this.shtOneMenuRoll;
        int n2 = 12;
        short s2 = (short) (70 + Macro.FONTHEIGHT);
        int n3 = 12;
        short s3 = (short) (70 + Macro.FONTHEIGHT);
        short s4 = this.shtOneMenuMove;
        graphics.setColor(0);
        graphics.drawString("Thuộc về", n2 + 4, s2 - Macro.FONTHEIGHT, 20);
        graphics.drawString("Chủ đề", Macro.SCREEN_WIDTH / 2, s2 - Macro.FONTHEIGHT, 17);
        graphics.drawString("Người gửi", Macro.SCREEN_WIDTH - 12 - 3 * Macro.FONTHEIGHT, s2 - Macro.FONTHEIGHT, 20);
        LayoutStyle.getInstance().drawSelectBox(n3, s3 + s4 * Macro.FONTHEIGHT, StringManager.getNewLineW(), Macro.FONTHEIGHT);
        n3 = (short) (n3 + 2);
        int n4 = this.bytWordMaxH;
        byte by = Macro.FONTHEIGHT;
        if (n4 > vector.size()) {
            n4 = vector.size();
            s = 0;
            by = 0;
        }
        int n5 = 0;
        while (n5 < n4) {
            PackageObject packageObject = (PackageObject) vector.elementAt(n5 + s);
            short s5 = (short) (Macro.font.stringWidth(packageObject.strInfo) + 12);
            graphics.setColor(packageObject.intColor);
            graphics.drawString(strMailType[packageObject.bytType], n2 + 4, (short) (s2 + n5 * Macro.FONTHEIGHT), 20);
            graphics.drawString(packageObject.strTitle, Macro.SCREEN_WIDTH / 2, (short) (s2 + n5 * Macro.FONTHEIGHT), 17);
            graphics.drawString(packageObject.strInfo, Macro.SCREEN_WIDTH - 12 - s5, (short) (s2 + n5 * Macro.FONTHEIGHT), 20);
            n5 = (short) (n5 + 1);
        }
        this.drawMoney(graphics, ORPMe.Gold, -1, this.MENU_HEIGHT + 4, true);
    }

    public void drawImageOption(Vector vector, Graphics graphics) {
        this.drawFullTiTle(graphics, this.strOneTitlestr, vector != null ? vector.size() : 0, this.getOneMove(), this.bytWordMaxH);
        this.drawOption(graphics, this.bytMenuState, vector, (short) 12, (short) (Macro.FONTHEIGHT + 16), this.shtOneMenuMove, this.shtOneMenuRoll, this.bytWordMaxH);
        switch (Param.getInstance().bytNpcDataType) {
            case 1: {
                if (vector != null && ((SkillObject) vector.elementAt((int) this.getOneMove())).intCDTime != 0) {
                    this.drawFingerRect(graphics, "" + ((SkillObject) vector.elementAt((int) this.getOneMove())).intCDTime, "", true);
                }
                this.drawMoney(graphics, ORPMe.Gold, -1, this.MENU_HEIGHT + 6, true);
                break;
            }
            case 2: {
                StringBuffer stringBuffer = new StringBuffer();
                if (ORPMe.ME.shtCNowNum == -1) {
                    stringBuffer.append("-");
                } else {
                    stringBuffer.append(ORPMe.ME.shtCNowNum);
                }
                stringBuffer.append("/");
                stringBuffer.append(ORPMe.ME.shtCAllNum);
                short s = (short) (Macro.SCREEN_WIDTH / 2 + Macro.font.stringWidth(ORPMe.ME.strConsortia) / 2 + Macro.font.stringWidth("200/200") + 5);
                StringManager.drawShadowWord(graphics, stringBuffer.toString(), s, 2, Macro.INT_TITLE_COLOR[1], Macro.INT_TITLE_COLOR[0], 24);
                this.drawPage(graphics, Param.getInstance().shtNoncePage, Param.getInstance().shtAllPage);
                break;
            }
            case 3: {
                if (this.bytTwoMenuState == 3) {
                    GameUI.getInstance().drawRectTypeA(graphics, " ", "Đánh dấu", 0, "");
                    graphics.setColor(this.intColor);
                    graphics.drawString(this.strSeeTitlestr, Macro.SCREEN_WIDTH / 2, 2, 17);
                    short s = ((MakeObject) vector.elementAt((int) this.getOneMove())).shtCanNum;
                    if (s > 0) {
                        graphics.drawString("" + s, (Macro.SCREEN_WIDTH + Macro.font.stringWidth(this.strSeeTitlestr)) / 2 + 5, 2, 17);
                    }
                    short s2 = (short) (Macro.FONTHEIGHT + 3 + 13);
                    short s3 = (short) (Macro.shtRectHeight - 26);
                    this.drawWordMove_Y(graphics, this.strOneDescribeChar, s2, s3);
                    short s4 = (short) (Macro.FONTHEIGHT + 3 + Macro.shtRectHeight + 3 + Macro.FONTHEIGHT + 2 + 3 + 13);
                    this.drawMackStuffMove(graphics, (MakeObject) vector.elementAt(this.getOneMove()), s4, (short) (this.MENU_HEIGHT - 3 - 13 - s4));
                }
                this.drawMoney(graphics, ORPMe.Gold, -1, this.MENU_HEIGHT + 6, true);
                break;
            }
            case 4: {
                if (vector != null) {
                    int n = ((PackageObject) vector.elementAt((int) this.getOneMove())).intPaiMaiPrice;
                    short s = ((PackageObject) vector.elementAt((int) this.getOneMove())).shtLevel;
                    this.drawFingerRect(graphics, "" + n, "" + s, true);
                }
                this.drawMoney(graphics, ORPMe.Gold, -1, this.MENU_HEIGHT + 6, true);
                break;
            }
            case 5:
            case 6: {
                if (Param.getInstance().vCommonList != null && !this.strRectPropName.equals("")) {
                    this.drawFingerRect(graphics, this.strRectPropName, "", false);
                }
                this.drawMoney(graphics, ORPMe.Gold, -1, this.MENU_HEIGHT + 6, true);
                break;
            }
            case 7: {
                this.drawPage(graphics, Param.getInstance().shtNoncePage, Param.getInstance().shtAllPage);
                break;
            }
            case 8: {
                if (vector != null) {
                    String string = ((PackageObject) vector.elementAt((int) this.getOneMove())).strPropName;
                    this.drawFingerRect(graphics, string, "", false);
                }
                this.drawMoney(graphics, ORPMe.Gold, -1, this.MENU_HEIGHT + 6, true);
                break;
            }
            case 9: {
                if (vector == null) {
                    break;
                }
                this.setFingerNewLineRect(graphics, (String[]) vector.elementAt(this.getOneMove()));
            }
        }
    }

    private void initFaction(Vector vector) {
        this.clearMove();
        if (vector != null && !vector.isEmpty()) {
            this.shtMenuMoveLength = (short) vector.size();
            int n = Macro.SCREEN_HEIGHT - 38 - 31 - 12 - Macro.FONTHEIGHT + 4;
            this.bytWordMaxH = (byte) (n / Macro.FONTHEIGHT);
            this.setChoiceMove(this.shtMenuMoveLength, this.getOneMove(), this.bytWordMaxH);
            this.setButton(1, 4, 2);
        } else {
            this.shtMenuMoveLength = 0;
            this.setButton(1, 4, 2);
        }
    }

    private void drawFaction(Graphics graphics) {
        this.drawTileTextBG(graphics, "Nhóm");
        int n = 38 + Macro.FONTHEIGHT + 4;
        this.scroll_OFF = DCanvas.getInstance().blnSpoolr ? ScrollText.arrowhead_width : (byte) 0;
        DrawBase.drawBox(8, 39, Macro.SCREEN_WIDTH - 12 - 4 - this.scroll_OFF, Macro.FONTHEIGHT + 4, new int[]{15195580}, true);
        DrawBase.drawString("Vai diễn", 12, 41, 0, 20);
        DrawBase.drawString("Lớp", Macro.SCREEN_WIDTH >> 1, 41, 0, 17);
        DrawBase.drawString("Chức vụ", 6 + (Macro.SCREEN_WIDTH - 12) - 6 - this.scroll_OFF, 41, 0, 24);
        this.drawOption(graphics, this.bytMenuState, Param.getInstance().vCommonList, (short) 12, (short) (n + 1), this.shtOneMenuMove, this.shtOneMenuRoll, this.bytWordMaxH);
    }

    private void drawFactionMemberList(Graphics graphics, TeamObject teamObject, short s, short s2, short s3, short s4) {
        this.scroll_OFF = DCanvas.getInstance().blnSpoolr ? ScrollText.arrowhead_width : (byte) 0;
        int n = 0;
        int n2 = 6 + (Macro.SCREEN_WIDTH - 12) - 6 - this.scroll_OFF;
        int n3 = (Macro.SCREEN_WIDTH >> 1) - 12 - Macro.FONTW;
        String string = MenuUI.getInstance().getLeaguerRemark(teamObject);
        n = teamObject.bytTroopRank == 3 ? (teamObject.blnIsOnLine ? 15550986 : Macro.INT_PROP_COLOR[0]) : (teamObject.bytTroopRank == 2 ? (teamObject.blnIsOnLine ? 15571968 : Macro.INT_PROP_COLOR[0]) : (teamObject.blnIsOnLine ? 0 : Macro.INT_PROP_COLOR[0]));
        StringManager.drawWordRightToLeft(teamObject.strName, s, s2, n3, n, 1, s4 == s3);
        DrawBase.drawString(teamObject.blnIsOnLine ? "" + teamObject.shtLevel : "-- ", Macro.SCREEN_WIDTH >> 1, s2, n, 17);
        DrawBase.drawString(string, n2, s2, n, 24);
    }

    private void initRankList(String string) {
        byte by;
        byte by2;
        this.clearMove();
        this.strOneTitlestr = String.valueOf(((String[]) Param.getInstance().Main_List.elementAt(Integer.parseInt(string)))[1]) + "排行榜";
        this.MainmenuID = by2 = Byte.parseByte(((String[]) Param.getInstance().Main_List.elementAt(Integer.parseInt(string)))[0]);
        this.mainIndex = Byte.parseByte(string);
        Vector vector = (Vector) Param.getInstance().MainOccupation_List.get(new Integer(by2));
        byte by3 = Byte.parseByte(((String[]) vector.elementAt(Integer.parseInt(string)))[2]);
        this.menuID = by = Byte.parseByte(((String[]) vector.elementAt(Integer.parseInt(string)))[0]);
        DCanvas.getInstance().setNetLoad(true);
        NetSend.getInstance().sendRankList((byte) 1, by3, by, (byte) 0, (byte) 0);
    }

    public void RecvRankValue() {
        if (Param.getInstance().Tab_Name != null && Param.getInstance().Member_List != null) {
            this.shtMenuMoveLength = (short) Param.getInstance().Member_List.size();
            int n = Macro.SCREEN_HEIGHT - 38 - 31 - 12 - Macro.FONTHEIGHT + 4;
            this.bytWordMaxH = (byte) (n / Macro.FONTHEIGHT);
            Vector vector = (Vector) Param.getInstance().MainOccupation_List.get(new Integer(this.MainmenuID));
            byte by = Byte.parseByte(((String[]) vector.elementAt(this.mainIndex))[3]);
            if (by == 1) {
                this.setButton(1, 4, 2);
            } else {
                this.setButton(1, 100, 2);
            }
        } else {
            this.shtMenuMoveLength = 0;
            this.setButton(1, 100, 2);
        }
    }

    private void drawRankList(Graphics graphics) {
        this.drawTileTextBG(graphics, this.strOneTitlestr);
        this.scroll_OFF = DCanvas.getInstance().blnSpoolr ? ScrollText.arrowhead_width : (byte) 0;
        int n = Macro.SCREEN_WIDTH - 12 - 4 - this.scroll_OFF - 4;
        this.rankingShowW = n / 8;
        this.nameShowW = n * 3 / 8;
        this.occShowW = n / 4;
        this.valueShow = n / 4;
        int n2 = 38 + Macro.FONTHEIGHT + 4;
        if (Param.getInstance().Tab_Name != null) {
            DrawBase.drawBox(8, 39, Macro.SCREEN_WIDTH - 12 - 4 - this.scroll_OFF, Macro.FONTHEIGHT + 4, new int[]{15195580}, true);
            DrawBase.drawString(Param.getInstance().Tab_Name[0], 12 + (this.rankingShowW >> 1), 41, 0, 17);
            DrawBase.drawString(Param.getInstance().Tab_Name[1], 12 + this.rankingShowW + 1, 41, 0, 20);
            DrawBase.drawString(Param.getInstance().Tab_Name[2], 12 + this.rankingShowW + this.nameShowW, 41, 0, 20);
            DrawBase.drawString(Param.getInstance().Tab_Name[3], 12 + this.rankingShowW + this.nameShowW + this.occShowW + this.valueShow, 41, 0, 24);
        }
        if (Param.getInstance().Member_List != null) {
            this.drawOption(graphics, this.bytMenuState, Param.getInstance().Member_List, (short) 12, (short) (n2 + 1), this.shtOneMenuMove, this.shtOneMenuRoll, this.bytWordMaxH);
        }
    }

    private void drawRankMemberList(Graphics graphics, TeamObject teamObject, short s, short s2, short s3, short s4) {
        this.scroll_OFF = DCanvas.getInstance().blnSpoolr ? ScrollText.arrowhead_width : (byte) 0;
        int n = 0;
        n = teamObject.blnIsOnLine ? 0 : Macro.INT_PROP_COLOR[0];
        byte by = teamObject.ranking;
        String string = teamObject.strName;
        String string2 = teamObject.occupation;
        int n2 = teamObject.rankValue;
        StringManager.drawWordRightToLeft("" + by, s - 2, s2, this.rankingShowW - 1, n, 0, s4 == s3);
        StringManager.drawWordRightToLeft(string, s + this.rankingShowW - 1, s2, this.nameShowW - 4, n, 1, s4 == s3);
        StringManager.drawWordRightToLeft(string2, s + this.rankingShowW + this.nameShowW - 2, s2, this.occShowW - 2, n, 1, s4 == s3);
        StringManager.drawWordRightToLeft("" + n2, s + this.rankingShowW + this.nameShowW + this.occShowW - 2 + this.valueShow, s2, this.valueShow - 1, n, 2, s4 == s3);
    }

    public void cleanRankList() {
        Param.getInstance().Main_Name = null;
        Param.getInstance().Tab_Name = null;
        Param.getInstance().Main_List = null;
        Param.getInstance().Second_List = null;
        Param.getInstance().Submenu_List = null;
        Param.getInstance().Member_List = null;
        Param.getInstance().Occupation_List = null;
        Param.getInstance().MainOccupation_List = null;
    }

    private void drawNpcList(Graphics graphics) {
        this.drawTileTextBG(graphics, "NPC列表");
        this.drawOption(graphics, this.bytMenuState, Param.getInstance().htAreaMapNpcList, (short) 12, (short) (Macro.FONTHEIGHT / 2 + 38), this.shtOneMenuMove, this.shtOneMenuRoll, this.bytWordMaxH);
    }

    public void setTitle(String[] stringArray) {
        this.bytTitleMove = 0;
        this.strsTitle = stringArray;
        this.setTitlePlace((byte) this.strsTitle.length);
    }

    public void setMakeList(byte by) {
        if (Param.getInstance().vCommonList != null && !Param.getInstance().vCommonList.isEmpty()) {
            if (by == -113) {
                this.getMakeSkillContent();
            } else {
                this.getMakeContent();
            }
        } else {
            this.shtMenuMoveLength = 0;
            this.setButton(1, 100, 2);
        }
    }

    private void clearMakeContent() {
        this.vTempVessel = null;
        this.shtMenuMoveLength = 0;
        Param.getInstance().hImgObject = null;
        this.setButton(1, 100, 2);
        this.clearMove();
    }

    private void getMakeContent() {
        int n = 0;
        while (n < Param.getInstance().vCommonList.size()) {
            if (Param.getInstance().IntRgbColor == null) {
                Param.getInstance().IntRgbColor = DrawBase.getRGB(StringManager.getNewLineW(), Macro.FONTHEIGHT, 1144385017);
            }
            MakeObject makeObject = (MakeObject) Param.getInstance().vCommonList.elementAt(n);
            if (this.bytTitleMove == makeObject.bytType) {
                if (this.vTempVessel == null) {
                    this.vTempVessel = new Vector(1, 1);
                }
                GameUI.getInstance().addObjectImg(makeObject.shtIcon, "prop");
                this.vTempVessel.addElement(makeObject);
            }
            n = (byte) (n + 1);
        }
        if (this.vTempVessel != null) {
            this.shtMenuMoveLength = (short) this.vTempVessel.size();
            this.setButton(1, 4, 2);
        }
    }

    private void getMakeSkillContent() {
        if (Param.getInstance().vCommonList != null) {
            if (Param.getInstance().IntRgbColor == null) {
                Param.getInstance().IntRgbColor = DrawBase.getRGB(StringManager.getNewLineW(), Macro.FONTHEIGHT, 1144385017);
            }
            int n = 0;
            while (n < Param.getInstance().vCommonList.size()) {
                SkillObject skillObject = (SkillObject) Param.getInstance().vCommonList.elementAt(n);
                if (this.bytTitleMove == skillObject.bytType) {
                    if (this.vTempVessel == null) {
                        this.vTempVessel = new Vector(1, 1);
                    }
                    GameUI.getInstance().addObjectImg(skillObject.shtIcon, "prop");
                    this.vTempVessel.addElement(skillObject);
                }
                n = (byte) (n + 1);
            }
            if (this.vTempVessel != null) {
                this.shtMenuMoveLength = (short) this.vTempVessel.size();
                this.setButton(1, 4, 2);
            }
        }
    }

    public void setExchangeSee(short s) {
        Param.getInstance().bytNpcDataType = (byte) 3;
        this.bytTwoMenuState = (byte) 3;
        MakeObject makeObject = (MakeObject) Param.getInstance().vCommonList.elementAt(s);
        this.intColor = makeObject.intColor;
        this.strSeeTitlestr = makeObject.strName;
        this.strOneDescribeChar = StringManager.wenZi(makeObject.strInfo, StringManager.getNewLineW());
        this.setWordMove();
        this.setButton(1, 100, 2);
    }

    public void setMakeSee(short s) {
        Param.getInstance().bytNpcDataType = (byte) 3;
        this.bytTwoMenuState = (byte) 3;
        MakeObject makeObject = (MakeObject) this.vTempVessel.elementAt(s);
        this.intColor = makeObject.intColor;
        this.strSeeTitlestr = makeObject.strName;
        this.strOneDescribeChar = StringManager.wenZi(makeObject.strInfo, StringManager.getNewLineW());
        this.setWordMove();
        this.setButton(1, 100, 2);
    }

    public void setMailList(short s) {
        if (Param.getInstance().vCommonList != null && !Param.getInstance().vCommonList.isEmpty()) {
            this.shtMenuMoveLength = (short) Param.getInstance().vCommonList.size();
            this.setChoiceMove(this.shtMenuMoveLength, s, this.bytWordMaxH);
            this.strRectPropName = ((MailObject) Param.getInstance().vCommonList.elementAt((int) this.getOneMove())).strName;
            FormDes.getInstance().bytTypeMail = 1;
        } else {
            this.strRectPropName = "";
            this.shtMenuMoveLength = 0;
        }
        this.setButton(1, 4, 2);
    }

    public void setMailContent(short s) {
        if (Param.getInstance().vCommonList != null && !Param.getInstance().vCommonList.isEmpty()) {
            MailObject mailObject = (MailObject) Param.getInstance().vCommonList.elementAt(s);
            this.strSeeTitlestr = mailObject.strTitle;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Thời gian：" + mailObject.strTime);
            stringBuffer.append("\n");
            stringBuffer.append("Tiêu đề：" + this.strSeeTitlestr);
            stringBuffer.append("\n");
            stringBuffer.append("Tên：" + mailObject.strName);
            stringBuffer.append("\n");
            stringBuffer.append("Nội dung：" + mailObject.strContent);
            new FullInfo("Thư", 0, this.bytMenuState);
            FullInfo.getInstance().setFullRectMenu(stringBuffer.toString());
        } else {
            this.setButton(1, 100, 2);
        }
    }

    public void drawMailContent(Graphics graphics, String[] stringArray, int n, int n2, byte by, byte s, byte by2) {
        if (stringArray == null) {
            return;
        }
        graphics.setColor(0);
        if (stringArray.length > s) {
            short s2 = 0;
            while (s2 < s) {
                if (s2 + by >= stringArray.length - 3) {
                    graphics.drawString(stringArray[s2 + by], Macro.SCREEN_WIDTH - 12, n2 + s2 * Macro.FONTHEIGHT, 24);
                } else {
                    graphics.drawString(stringArray[s2 + by], n, n2 + s2 * Macro.FONTHEIGHT, by2);
                }
                s2 = (short) (s2 + 1);
            }
        } else {
            int n3 = 0;
            while (n3 < stringArray.length) {
                if (n3 >= stringArray.length - 3) {
                    graphics.drawString(stringArray[n3], Macro.SCREEN_WIDTH - 12, n2 + n3 * Macro.FONTHEIGHT, 24);
                } else {
                    graphics.drawString(stringArray[n3], n, n2 + n3 * Macro.FONTHEIGHT, by2);
                }
                n3 = (short) (n3 + 1);
            }
        }
    }

    public void setChoiceMove(int n, short s, short s2) {
        if (s > n - 1) {
            if (this.shtOneMenuRoll > 0) {
                this.shtOneMenuRoll = (short) (this.shtOneMenuRoll - 1);
            } else if (this.shtOneMenuMove > 0) {
                this.shtOneMenuMove = (short) (this.shtOneMenuMove - 1);
            }
        }
        if (n > s2 && this.shtOneMenuRoll + s2 > n) {
            this.shtOneMenuRoll = (short) (this.shtOneMenuRoll - 1);
            this.shtOneMenuMove = (short) (this.shtOneMenuMove + 1);
        }
    }

    public void setParcelPostButton(short s) {
        if (Param.getInstance().vCommonList != null && !Param.getInstance().vCommonList.isEmpty()) {
            this.shtMenuMoveLength = (short) Param.getInstance().vCommonList.size();
            if (Param.getInstance().IntRgbColor == null) {
                Param.getInstance().IntRgbColor = DrawBase.getRGB(StringManager.getNewLineW(), Macro.FONTHEIGHT, 1144385017);
            }
            this.setChoiceMove(this.shtMenuMoveLength, this.getOneMove(), this.bytWordMaxH);
            PackageObject packageObject = (PackageObject) Param.getInstance().vCommonList.elementAt(this.getOneMove());
            if (packageObject != null) {
                this.strRectPropName = packageObject.strInfo;
            }
            this.setButton(1, 4, 2);
        } else {
            this.strRectPropName = "";
            this.shtMenuMoveLength = 0;
            this.setButton(1, 100, 2);
        }
    }

    private void drawFingerRect(Graphics graphics, String string, String string2, boolean bl) {
        short s = (short) (Macro.FONTHEIGHT + 16 + this.shtOneMenuMove * Macro.FONTHEIGHT);
        if (this.shtOneMenuMove == 0) {
            this.blnShow = false;
        } else if (this.shtOneMenuMove == this.bytWordMaxH - 1) {
            this.blnShow = true;
        }
        this.drawFingerInfo(graphics, string, string2, s, bl, this.blnShow);
    }

    private void setFingerNewLineRect(Graphics graphics, String[] stringArray) {
        String[] stringArray2 = StringManager.wenZi(stringArray[1], StringManager.getNewLineW() - Macro.font.stringWidth(stringArray[0]));
        short s = (short) (Macro.font.stringWidth(stringArray2[0]) + 12);
        short s2 = (short) (Macro.SCREEN_WIDTH - 12 - s);
        short s3 = (short) (Macro.FONTHEIGHT * stringArray2.length + 4);
        short s4 = (short) (Macro.FONTHEIGHT + 16 + this.shtOneMenuMove * Macro.FONTHEIGHT);
        short s5 = 0;
        if (this.MENU_HEIGHT - s4 >= s3) {
            s5 = (short) (s4 + Macro.FONTHEIGHT);
            this.blnShow = false;
        } else {
            this.blnShow = true;
            s5 = (short) (s4 - Macro.FONTHEIGHT - s3);
        }
        GameUI.getInstance().drawChatRect(graphics, s2, s5, s, s3, 10, this.blnShow);
        graphics.setColor(0xFFFFFF);
        int n = 0;
        while (n < stringArray2.length) {
            graphics.drawString(stringArray2[n], s2 + 6, s5 + n * Macro.FONTHEIGHT + 2, 20);
            n = (short) (n + 1);
        }
    }

    private void drawServeInfo(Graphics graphics) {
        this.drawFullTiTle(graphics, this.strOneTitlestr, this.shtMenuMoveLength, this.getOneMove(), this.bytWordMaxH);
        LayoutStyle.getInstance().drawBeforeBackGround(4, 38, IDefines.GLOBAL_UI_MAIN_TAB_WIDTH, Macro.SCREEN_HEIGHT - 38 - 30, new int[]{8142636, 14995858, 16314576});
        graphics.setColor(0);
        graphics.drawString(Pram.strMenuServeInfo[0], 19, 43, 20);
        graphics.drawString(String.valueOf(Pram.strMenuServeInfo[1]) + Macro.VERSION, 19, 43 + Macro.FONTHEIGHT, 20);
        graphics.drawString(Pram.strMenuServeInfo[2], 19, 43 + Macro.FONTHEIGHT * 2, 20);
        graphics.drawString(Pram.strMenuServeInfo[3], 19, 43 + Macro.FONTHEIGHT * 3, 20);
    }

    private void drawSetting(Graphics graphics) {
        DCanvas.getInstance().drawmenuBack(graphics, this.shtMenuMoveLength, 17, this.getOneMove(), this.bytWordMaxH);
        LayoutStyle.getInstance().drawEarBox(graphics, "Cài đặt", 9263661, IDefines.GLOBAL_UI_MAIN_TITLEBOX_X, 0, IDefines.GLOBAL_UI_MAIN_TITLEBOX_WIDTH, 30);
        LayoutStyle.getInstance().drawBeforeBackGround(2, 36, IDefines.GLOBAL_UI_MAIN_TAB_WIDTH, Macro.SCREEN_HEIGHT - 38 - 30 - 2, new int[]{8142636, 14995858, 16314576});
        DCanvas.getInstance().setOptionSpoolr(graphics, Macro.SCREEN_WIDTH - 12, 33, Macro.SCREEN_HEIGHT - 30, 5, Pram.STR_SET_SWITCH.length, this.bytSetWordMove, false);
        int n = 30;
        int n2 = 50;
        short s = (short) (n + Macro.font.stringWidth("Chặn người chơi") + 20);
        short s2 = (short) (Macro.SCREEN_WIDTH - s - 40);
        short s3 = (short) (Macro.FONTHEIGHT + 5);
        this.sht_box_space = (short) ((Macro.SCREEN_HEIGHT - n2 - 30 - 6 * s3) / 6);
        this.sht_Triangle_L_x = (short) (s - this.triangle_widht - 2);
        this.sht_Triangle_R_x = (short) (s + s2 + this.triangle_widht + 2);
        this.sht_Triangle_y = (short) (n2 + 2);
        graphics.setClip(n, n2, Macro.SCREEN_WIDTH, Macro.SCREEN_HEIGHT - 40 - 50);
        int n3 = 0;
        while (n3 < Pram.STR_SET_SWITCH.length) {
            DrawBase.drawBox(s, n2 + (s3 + this.sht_box_space) * n3, s2, s3, new int[]{0xFFFFFF, 14995858}, true);
            DrawBase.drawRoundRect(s, n2 + (s3 + this.sht_box_space) * this.bytSetBoxY, s2 - 1, s3 - 1, 2, 2, 0xFF0000);
            if (this.bytSetBoxY == n3) {
                this.triangle[0].drawAnimation(graphics, this.sht_Triangle_L_x + this.triangle_widht, n2 + (s3 + this.sht_box_space) * n3 + 2 + (this.triangle_height >> 1));
                this.triangle[1].drawAnimation(graphics, this.sht_Triangle_R_x - this.triangle_widht, n2 + (s3 + this.sht_box_space) * n3 + 2 + (this.triangle_height >> 1));
            } else {
                this.triangle[0].drawAnimationFrame(graphics, 0, 0, this.sht_Triangle_L_x + this.triangle_widht, n2 + (s3 + this.sht_box_space) * n3 + 2 + (this.triangle_height >> 1));
                this.triangle[0].drawAnimationFrame(graphics, 2, 0, this.sht_Triangle_R_x - this.triangle_widht, n2 + (s3 + this.sht_box_space) * n3 + 2 + (this.triangle_height >> 1));
            }
            DrawBase.drawShadowString(Pram.STR_SET_SWITCH[n3], n, n2 + (s3 + this.sht_box_space) * (n3 - this.bytSetWordY), 16766720, 0, 20);
            ++n3;
        }
        DrawBase.DrawString(Pram.STR_SHOW_EFFECT[this.bytSetShow], s, n2 + (s3 + this.sht_box_space) * (0 - this.bytSetWordY), s2, s3, (short) 3, 0);
        DrawBase.DrawString(Pram.STR_OPTION_OPENANDCLOSE[this.bytSetChannel], s, n2 + (s3 + this.sht_box_space) * (1 - this.bytSetWordY), s2, s3, (short) 3, 0);
        DrawBase.DrawString(Pram.STR_SHOWNAME_OPTION[this.bytShowName], s, n2 + (s3 + this.sht_box_space) * (2 - this.bytSetWordY), s2, s3, (short) 3, 0);
        DrawBase.DrawString(Pram.STR_OPTION_OPENANDCLOSE[this.bytSetWeather], s, n2 + (s3 + this.sht_box_space) * (3 - this.bytSetWordY), s2, s3, (short) 3, 0);
        DrawBase.DrawString(Pram.STR_OPTION_OPENANDCLOSE[this.bytSetShowRole], s, n2 + (s3 + this.sht_box_space) * (4 - this.bytSetWordY), s2, s3, (short) 3, 0);
        DrawBase.DrawString(Pram.STR_OPTION_OPENANDCLOSE[this.bytAutoWalk], s, n2 + (s3 + this.sht_box_space) * (5 - this.bytSetWordY), s2, s3, (short) 3, 0);
        DrawBase.DrawString(Pram.STR_OPTION_OPENANDCLOSE[this.bytAutoSelect], s, n2 + (s3 + this.sht_box_space) * (6 - this.bytSetWordY), s2, s3, (short) 3, 0);
        graphics.setClip(0, 0, Macro.SCREEN_WIDTH, Macro.SCREEN_HEIGHT);
    }

    public void setStudySkill() {
        if (Param.getInstance().vCommonList != null && !Param.getInstance().vCommonList.isEmpty()) {
            this.shtMenuMoveLength = (short) Param.getInstance().vCommonList.size();
            this.setButton(1, 4, 2);
            Param.getInstance().IntRgbColor = DrawBase.getRGB(StringManager.getNewLineW(), Macro.FONTHEIGHT, 1144385017);
            this.setChoiceMove(this.shtMenuMoveLength, this.getOneMove(), this.bytWordMaxH);
        } else {
            this.shtMenuMoveLength = 0;
            this.setButton(1, 100, 2);
        }
        Param.getInstance().bytNpcDataType = 1;
        this.bytWordMaxH = Macro.bytMaxFullRow;
    }

    private void pointTaskMeed() {
        if (DCanvas.getInstance().IsPointerDown(this.boxX, this.boxUpY, this.boxW >> 1, this.boxH)) {
            if (Param.getInstance().awardSelectPackage != null) {
                Param.getInstance().awardSelectPackage.setFocal(false);
            }
            if (Param.getInstance().awardMustPackage != null) {
                Param.getInstance().awardMustPackage.setFocal(false);
            }
            this.keyLeftEvent();
            StringManager.blnPageSeleted = true;
        } else if (DCanvas.getInstance().IsPointerDown(this.boxX + (this.boxW >> 1), this.boxUpY, this.boxW >> 1, this.boxH)) {
            if (Param.getInstance().awardSelectPackage != null) {
                Param.getInstance().awardSelectPackage.setFocal(false);
            }
            if (Param.getInstance().awardMustPackage != null) {
                Param.getInstance().awardMustPackage.setFocal(false);
            }
            this.keyRightEvent();
            StringManager.blnPageSeleted = true;
        }
        if (Param.getInstance().awardMustPackage != null) {
            if (Param.getInstance().awardMustPackage.getFocal()) {
                Param.getInstance().awardMustPackage.getTouch();
                if (Param.getInstance().awardMustPackage.isDoublePoint()) {
                    this.keyNum0Event();
                }
            } else {
                Param.getInstance().awardMustPackage.getTouch();
                if (Param.getInstance().awardMustPackage.getFocal()) {
                    if (Param.getInstance().awardSelectPackage != null) {
                        Param.getInstance().awardSelectPackage.setFocal(false);
                    }
                    StringManager.blnPageSeleted = false;
                    Param.popupDialogInstance.setShow(true);
                }
            }
        }
        if (Param.getInstance().awardSelectPackage != null) {
            byte by = Param.getInstance().awardSelectPackage.getCellBoxIndex();
            if (Param.getInstance().awardSelectPackage.getFocal()) {
                Param.getInstance().awardSelectPackage.getTouch();
                if (Param.getInstance().awardSelectPackage.isDoublePoint()) {
                    this.keyNum0Event();
                }
            } else {
                Param.getInstance().awardSelectPackage.getTouch();
                if (Param.getInstance().awardSelectPackage.getFocal()) {
                    if (Param.getInstance().awardMustPackage != null) {
                        Param.getInstance().awardMustPackage.setFocal(false);
                    }
                    StringManager.blnPageSeleted = false;
                }
            }
        }
    }

    public void drawTask(Graphics graphics) {
        short s = (short) (Macro.FONTHEIGHT + 27);
        short s2 = 0;
        s2 = this.shtOneMenuRoll == 0 ? s : (short) (s + 55);
        short s3 = (short) (20 + Macro.FONTHEIGHT * 2);
        short s4 = (short) (this.MENU_HEIGHT - 2 - (Macro.FONTHEIGHT + 3) - (s3 + 3) - (Macro.FONTHEIGHT + 5) - 30);
        short s5 = (short) (s + 65);
        DCanvas.getInstance().drawTileTextBG(DCanvas.gameG, this.strOneTitlestr);
        DrawBase.drawBox(14, s, Macro.SCREEN_WIDTH - 24 - 4, s3, IDefines.MENU_UI_PERSONAL_SHOP_PERSONAL_PACKAGE_BOX_BORDER, true);
        DrawBase.drawBox(14, s5 + this._downYoffset, Macro.SCREEN_WIDTH - 24 - 4, s4 - this._downYoffset, IDefines.MENU_UI_PERSONAL_SHOP_PERSONAL_PACKAGE_BOX_BORDER, true);
        boolean bl = false;
        if (Param.getInstance().vCommonList != null) {
            short s6;
            short s7;
            byte by;
            TaskObject taskObject = (TaskObject) Param.getInstance().vCommonList.elementAt(0);
            if (taskObject.vSelectObject != null) {
                bl = true;
                graphics.setColor(0);
                graphics.drawString("Chọn vật phẩm thưởng", 20, s + 2, 20);
                this.shtTaskMeedvsoY = (short) (s + Macro.FONTHEIGHT);
                byte by2 = (byte) taskObject.vSelectObject.size();
                by = 0;
                while (by < by2) {
                    short s8;
                    GameUI.getInstance().backSmallRect(graphics, 20 + by * 24, s + 5 + Macro.FONTHEIGHT, 22, 22);
                    s7 = ((PackageObject) taskObject.vSelectObject.get((Object) new Integer((int) by))).shtIcon;
                    Image image = GameUI.getInstance().getObjectImg(s7);
                    if (image != null) {
                        DrawBase.drawImage(image, 20 + by * 24 + 3, s + 5 + Macro.FONTHEIGHT + 3, 20);
                    }
                    if ((s8 = ((PackageObject) taskObject.vSelectObject.get((Object) new Integer((int) by))).shtPOnum) > 1) {
                        GameUI.getInstance().drawWhiteNum(graphics, s8, 20 + by * 24, s + 5 + Macro.FONTHEIGHT + 12);
                    }
                    by = (byte) (by + 1);
                }
                s = (short) (s + 55);
                this._downYoffset = 0;
            }
            if (taskObject.vNSelectObject != null) {
                graphics.setColor(0);
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("bạn");
                if (bl) {
                    stringBuffer.append(" trở lại ");
                }
                stringBuffer.append("Sẽ lấy");
                DrawBase.drawBox(14, s, Macro.SCREEN_WIDTH - 24 - 4, s3, IDefines.MENU_UI_PERSONAL_SHOP_PERSONAL_PACKAGE_BOX_BORDER, true);
                graphics.drawString(stringBuffer.toString(), 20, s, 20);
                this.shtTaskMeedvnsoY = (short) (s + Macro.FONTHEIGHT);
                by = (byte) taskObject.vNSelectObject.size();
                s7 = 0;
                while (s7 < by) {
                    GameUI.getInstance().backSmallRect(graphics, 20 + s7 * 24, s + 5 + Macro.FONTHEIGHT, 22, 22);
                    short s9 = ((PackageObject) taskObject.vNSelectObject.get((Object) new Integer((int) s7))).shtIcon;
                    Image image = GameUI.getInstance().getObjectImg(s9);
                    if (image != null) {
                        DrawBase.drawImage(image, 20 + s7 * 24 + 3, s + 5 + Macro.FONTHEIGHT + 3, 20);
                    }
                    if ((s6 = ((PackageObject) taskObject.vNSelectObject.get((Object) new Integer((int) s7))).shtPOnum) > 1) {
                        GameUI.getInstance().drawWhiteNum(graphics, s6, 20 + s7 * 24, s + 5 + Macro.FONTHEIGHT + 12);
                    }
                    s7 = (byte) (s7 + 1);
                }
                this._downYoffset = (short) 60;
            }
            if (taskObject.vOtherObject != null) {
                graphics.setColor(0);
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("你");
                if (bl) {
                    stringBuffer.append("还");
                }
                stringBuffer.append("将获得");
                DrawBase.drawBox(20, s, Macro.SCREEN_WIDTH - 24 - 4, s3, IDefines.MENU_UI_PERSONAL_SHOP_PERSONAL_PACKAGE_BOX_BORDER, true);
                graphics.drawString(stringBuffer.toString(), 12, s + 8, 20);
                this.shtTaskMeedvooY = (short) (s + Macro.FONTHEIGHT);
                by = (byte) taskObject.vOtherObject.size();
                s7 = 0;
                while (s7 < by) {
                    GameUI.getInstance().backSmallRect(graphics, 20 + s7 * 24, s + 5 + Macro.FONTHEIGHT, 22, 22);
                    short s10 = ((PackageObject) taskObject.vOtherObject.get((Object) new Integer((int) s7))).shtIcon;
                    Image image = GameUI.getInstance().getObjectImg(s10);
                    if (image != null) {
                        DrawBase.drawImage(image, 20 + s7 * 24 + 3, s + 5 + Macro.FONTHEIGHT + 3, 20);
                    }
                    if ((s6 = ((PackageObject) taskObject.vOtherObject.get((Object) new Integer((int) s7))).shtPOnum) > 1) {
                        GameUI.getInstance().drawWhiteNum(graphics, s6, 20 + s7 * 24, s + 5 + Macro.FONTHEIGHT + 12);
                    }
                    s7 = (byte) (s7 + 1);
                }
            }
            if (this.shtOneMenuMove >= 0) {
                LayoutStyle.getInstance().drawChooseFrame(DCanvas.gameG, 20 + this.shtOneMenuMove * 24, s2 + 5 + Macro.FONTHEIGHT, 22, 22);
            }
            this.drawWordMove_Y(graphics, this.strOneDescribeChar, (short) (s5 + 5 + this._downYoffset), (short) (s4 - 10 - this._downYoffset));
        }
    }

    public void drawTaskMessage(Graphics graphics) {
        int n = 0xFF0000;
        DCanvas.getInstance().drawTileTextBG(graphics, this.strOneTitlestr);
        DrawBase.drawBox(this.boxX, this.boxUpY, this.boxW, this.boxH, new int[]{12026667, 14995858, 16642234}, true);
        DrawBase.drawBox(this.boxX, this.boxDownY, this.boxW, this.boxH - 4, new int[]{12026667, 14995858, 16642234}, true);
        int n2 = 14;
        int n3 = this.boxUpY;
        graphics.setClip(0, Macro.FONTHEIGHT + 25, Macro.SCREEN_WIDTH, this.bytWordMaxH * Macro.FONTHEIGHT);
        if (this.WordContent != null) {
            this.WordContent.drawWord(graphics, n2, n3 + 2, this.boxH - 8);
        }
        DrawBase.setClip(this.boxX, this.boxDownY, this.boxW, this.boxH - 8);
        int n4 = this.socrollBoxY;
        n3 = this.socrollBoxY;
        if (Param.getInstance().vInfoContent != null && !Param.getInstance().vInfoContent.isEmpty()) {
            this.shtIOMoveY = (short) n3;
            String string = "目标:";
            int n5 = 0;
            while (n5 < Param.getInstance().vInfoContent.size()) {
                if (n3 > Macro.FONTHEIGHT + 16 && n3 < Macro.SCREEN_HEIGHT - 17 - 16 && this.shtOneMenuMove == n5) {
                    LayoutStyle.getInstance().drawSelectBox(12, n3, StringManager.getNewLineW(), Macro.FONTHEIGHT);
                }
                if (n5 == 0) {
                    graphics.setColor(0xFF0000);
                    graphics.drawString(string, n2, n3, 20);
                }
                ChatContent chatContent = (ChatContent) Param.getInstance().vInfoContent.elementAt(n5);
                graphics.setColor(chatContent.fontColor);
                graphics.drawString(chatContent.character, n2 + Macro.font.stringWidth(string), n3, 20);
                n3 += Macro.FONTHEIGHT;
                n5 = (byte) (n5 + 1);
            }
        }
        graphics.setColor(n);
        if (this.strOneMenuOption != null) {
            int n6 = 0;
            while (n6 < this.strOneMenuOption.length) {
                graphics.drawString(this.strOneMenuOption[n6], 14, n3, 20);
                n3 += Macro.FONTHEIGHT + 2;
                n6 = (short) (n6 + 1);
            }
        }
        if (Param.getInstance().awardMustPackage != null && this._temp1 != null) {
            this.mustBoxY = n3;
            if (Param.getInstance().awardMustPackage.packageY != n3) {
                Param.getInstance().awardMustPackage.packageY = n3;
                if (Param.getInstance().awardMustPackage.getFocal()) {
                    Param.getInstance().awardMustPackage.setPopupDialog();
                }
            }
            Param.getInstance().awardMustPackage.draw(this._temp1);
            n3 += this.packageH;
        }
        if (Param.getInstance().awardSelectPackage != null && this._temp0 != null) {
            DrawBase.drawString(this.strSelect, n2, n3, n, 20);
            this.selectBoxY = n3 += Macro.FONTHEIGHT;
            if (Param.getInstance().awardSelectPackage.packageY != n3) {
                Param.getInstance().awardSelectPackage.packageY = n3;
                if (Param.getInstance().awardSelectPackage.getFocal()) {
                    Param.getInstance().awardSelectPackage.setPopupDialog();
                }
            }
            Param.getInstance().awardSelectPackage.draw(this._temp0);
            n3 += this.packageH;
        }
        this.comperH = n3 - this.socrollBoxY;
        if (this.comperH != 0 && this.packageH != 0) {
            DCanvas.getInstance().setOptionSpoolr(graphics, Macro.SCREEN_WIDTH - 18, this.boxDownY, this.boxDownY + this.boxH - 8, 1, this.comperH / this.packageH + 1 - this.boxH / this.packageH + 1, (this.boxDownY - this.socrollBoxY) / this.packageH, false);
        }
        DrawBase.setClip(0, 0, Macro.SCREEN_WIDTH, Macro.SCREEN_HEIGHT);
        if (Param.getInstance().awardMustPackage != null && Param.getInstance().awardMustPackage.getFocal() || Param.getInstance().awardSelectPackage != null && Param.getInstance().awardSelectPackage.getFocal()) {
            if (DCanvas.getInstance().blnIsTouch) {
                DrawBase.drawString(this.strTouchLook, (Macro.SCREEN_WIDTH >> 1) - (Macro.font.stringWidth(this.strLook) >> 1), Macro.SCREEN_HEIGHT - 30, 0xFFFFFF, 20);
            } else {
                DrawBase.drawString(this.strLook, (Macro.SCREEN_WIDTH >> 1) - (Macro.font.stringWidth(this.strLook) >> 1), Macro.SCREEN_HEIGHT - 30, 0xFFFFFF, 20);
            }
        }
        DCanvas.getInstance().clearScreen();
    }

    public void setTwoRect(String string, int n, short s, byte by, int n2) {
        this.twodialog = new TwoDialogUI();
        this.twodialog.setTwoRect(string, n, s, this.bytMenuState, by, n2);
        this.setButton(1, 0, 2);
    }

    public void setDialogOption(String string) {
        this.twodialog = new TwoDialogUI();
        this.twodialog.initDialogOptions(string);
        this.setButton(1, 0, 2);
    }

    public void setTwoKeyRect(String string, short s, int n) {
        this.twodialog = new TwoDialogUI();
        this.twodialog.initSettingKey(string, s, n, this.bytMenuState);
        this.setButton(1, 0, 2);
    }

    public void setPackageDataRect(String string, short s, int n) {
        this.twodialog = new TwoDialogUI();
        this.twodialog.initPackageSettingKey(string, s, n, this.bytMenuState);
        this.setButton(1, 0, 2);
    }

    public void setTwoNumber(String string, int n, int n2, byte by) {
        this.twodialog = new TwoDialogUI();
        this.twodialog.setTwoNumber(string, n, this.bytMenuState, n2, by);
        this.setButton(1, 0, 2);
    }

    private void setPointOut(String string) {
        this.CanPress = true;
        this.twodialog = new TwoDialogUI();
        this.twodialog.setTwoRect(string, -1, (short) 0, this.bytMenuState, (byte) 1, -1);
    }

    public void clearTwoRect() {
        this.twodialog = null;
        this.CanPress = false;
        Param.getInstance().bytPopRectStep = 0;
        block0:
        switch (this.bytMenuState) {
            case 39: {
                this.setPetList();
                break;
            }
            case 58: {
                this.setPetPack();
                break;
            }
            case 10: {
                this.setEquip();
                break;
            }
            case -43: {
                break;
            }
            case -39: {
                Hashtable hashtable = null;
                Object var2_2 = null;
                byte by = 0;
                switch (Macro.NPC_STEP_SELECT) {
                    case 2: {
                        byte by2 = Param.getInstance().npcShopBarTabStyle.getTabCurrentIndex();
                        hashtable = (Hashtable) Param.getInstance().hNpcBarPackTable.get(new Integer(by2));
                        by = Param.getInstance().npcShopBarPackage.getCellBoxIndex();
                        this.setInfoContent(hashtable, by);
                        break block0;
                    }
                    case 3: {
                        String string = Param.getInstance().npcShopOursTabArray[MenuUI.getInstance().tabStyleInstance.getTabCurrentIndex()];
                        hashtable = (Hashtable) Param.getInstance().hNpcOursPackTable.get(string);
                        by = Param.getInstance().personalPackage.getCellBoxIndex();
                        this.setInfoContent(hashtable, by);
                        break block0;
                    }
                }
                break;
            }
            case -70:
            case 36:
            case 37:
            case 38: {
                this.setInfoContent(Param.getInstance().hPackage, Param.getInstance().personalPackage.getCellBoxIndex());
                break;
            }
            case -11: {
                this.setInfoContent(Param.getInstance().hPackage, Param.getInstance().personalPackage.getCellBoxIndex());
                break;
            }
            case -72:
            case -35: {
                this.setInfoContent(Param.getInstance().hShopPackage, this.personalShopSellPackage.getCellBoxIndex());
                break;
            }
            case 35:
            case 42: {
                this.setButton(1, 4, 2);
                break;
            }
            case 61: {
                if (this.bytTitleMove != 0) {
                    break;
                }
                this.setButton(1, 4, 2);
                break;
            }
            case 64: {
                break;
            }
            case 63: {
                if (this.bytTitleMove != 0) {
                    break;
                }
                if (Param.getInstance().vTaskList != null) {
                    this.setButton(1, 4, 2);
                    break;
                }
                this.setButton(1, 100, 2);
                break;
            }
            case -48: {
                if (this.vTempVessel != null) {
                    this.setButton(1, 4, 2);
                    break;
                }
                this.setButton(1, 100, 2);
                break;
            }
            case -31: {
                this.setButton(1, 4, 2);
                break;
            }
            case 31: {
                this.setButton(1, 100, 100);
                break;
            }
            default: {
                if (Param.getInstance().vCommonList != null) {
                    this.setButton(1, 4, 2);
                    break;
                }
                if (Param.getInstance().vMenuMemory != null) {
                    this.setButton(1, 0, 2);
                    break;
                }
                this.setButton(1, 100, 2);
            }
        }
    }

    public void setNpcTalk(String string, String string2) {
        this.setState((byte) 1, string);
        if (Param.getInstance().oSelectNpc != null) {
            Macro.NPC_HEIGHT = Param.getInstance().oSelectNpc.shtImgHeight;
            Macro.NPC_WEIGHT = Param.getInstance().oSelectNpc.shtImgWidth;
            Macro.npcOneX = (short) (24 + (Macro.NPC_WEIGHT >> 1));
            Macro.npcOneY = (short) (40 + Macro.NPC_HEIGHT + 20);
        }
        Param.getInstance().blnNpcOptionWork = true;
        Param.getInstance().IntRgbColor = DrawBase.getRGB(StringManager.getNewLineW(), Macro.FONTHEIGHT, 1144385017);
        Macro.npcRectX = (short) (Macro.npcOneX + 12 + (Macro.NPC_WEIGHT >> 1));
        Macro.npcRectY = 52;
        Macro.npcRectW = Macro.SCREEN_WIDTH - Macro.npcRectX - 12 - 6;
        Macro.npcRectH = Macro.FONTHEIGHT * 4;
        this.strOneDescribeChar = string2 == null ? null : StringManager.wenZi(StringManager.displaceNpcTalk(string2), Macro.npcRectW - 6);
        this.setButton(1, 100, 2);
        this.clearMove();
        Param.getInstance().npcShopBarTabArray = null;
        Param.getInstance().npcShopBarPackage = null;
        Param.getInstance().IndexNpcShopBarTabStyle = 0;
        Param.getInstance().npcShopBarPackage = null;
    }

    public void setNpcOption() {
        Param.getInstance().bytNpcDataType = 0;
        byte by = (byte) Param.getInstance().vMenuMemory.size();
        MenuObject menuObject = (MenuObject) Param.getInstance().vMenuMemory.elementAt(by - 1);
        if (by - 1 != 0) {
            this.strOneDescribeChar = StringManager.wenZi(StringManager.displaceNpcTalk(menuObject.strInfo), Macro.npcRectW - 6 - 2);
            Param.popupDialogInstance.setPopupDialog(StringManager.displaceNpcTalk(menuObject.strInfo), (Macro.SCREEN_WIDTH >> 2) - 12, 40, (Macro.SCREEN_WIDTH >> 2) - 12, Macro.NPC_HEIGHT);
        }
        if (menuObject.shtIcon != null && menuObject.shtIcon[0] != 0) {
            int n = 0;
            while (n < menuObject.shtIcon.length) {
                GameUI.getInstance().addObjectImg(menuObject.shtIcon[n], "prop");
                n = (byte) (n + 1);
            }
        }
        this.shtOneMenuMove = menuObject.bytMove;
        this.shtOneMenuRoll = menuObject.bytRoll;
        if (menuObject.strOption != null) {
            this.setNpcOption(menuObject.strOption, menuObject.strBakInfo, (byte) 0);
        } else {
            this.shtMenuMoveLength = 0;
            this.strOneMenuOption = null;
            this.setButton(1, 100, 2);
        }
        this.setWordMove();
    }

    public void setNpcOption(String[] stringArray, String string, byte by) {
        this.initWordRoll(stringArray[by], (short) (Macro.SCREEN_WIDTH - 38));
        this.strOneMenuOption = stringArray;
        this.strBakInfo = string;
        this.shtMenuMoveLength = (short) stringArray.length;
        this.setNpcDialog();
        this.setChoiceMove(stringArray.length, this.getOneMove(), this.bytPackMaxH);
        this.setButton(1, 0, 2);
    }

    public void setChatIndex(byte by) {
        byte by2 = 0;
        switch (by) {
            case 2: {
                Param.getInstance().getClass();
                by2 = 3;
                break;
            }
            case 1: {
                Param.getInstance().getClass();
                by2 = 1;
                break;
            }
            case 10: {
                Param.getInstance().getClass();
                by2 = 2;
                break;
            }
            case 0: {
                Param.getInstance().getClass();
                by2 = 6;
                break;
            }
            case 4: {
                Param.getInstance().getClass();
                by2 = 4;
                break;
            }
            case 3: {
                Param.getInstance().getClass();
                by2 = 5;
                break;
            }
            case 20: {
                Param.getInstance().getClass();
                by2 = 8;
                break;
            }
            case 5: {
                Param.getInstance().getClass();
                by2 = 7;
                break;
            }
            case 12: {
                Param.getInstance().getClass();
                by2 = 7;
                break;
            }
            default: {
                by2 = 0;
            }
        }
        byte by3 = 0;
        Param.getInstance().getClass();
        if (Param.getInstance().bytChatNoteType == 0) {
            if (Param.getInstance().vChatNote != null) {
                by3 = (byte) Param.getInstance().vChatNote.size();
            }
        } else {
            Param.getInstance().getClass();
            if (Param.getInstance().bytChatNoteType == 6) {
                if (Param.getInstance().vChatSingleNote != null) {
                    by3 = (byte) Param.getInstance().vChatSingleNote.size();
                }
            } else {
                Param.getInstance().getClass();
                if (Param.getInstance().bytChatNoteType == 2) {
                    if (Param.getInstance().vChatRaceNote != null) {
                        by3 = (byte) Param.getInstance().vChatRaceNote.size();
                    }
                } else {
                    Param.getInstance().getClass();
                    if (Param.getInstance().bytChatNoteType == 5) {
                        if (Param.getInstance().vChatTeamNote != null) {
                            by3 = (byte) Param.getInstance().vChatTeamNote.size();
                        }
                    } else {
                        Param.getInstance().getClass();
                        if (Param.getInstance().bytChatNoteType == 4) {
                            if (Param.getInstance().vChatConsortianNote != null) {
                                by3 = (byte) Param.getInstance().vChatConsortianNote.size();
                            }
                        } else {
                            Param.getInstance().getClass();
                            if (Param.getInstance().bytChatNoteType == 1) {
                                if (Param.getInstance().vChatNote != null) {
                                    by3 = (byte) Param.getInstance().vChatNote.size();
                                }
                            } else {
                                Param.getInstance().getClass();
                                if (Param.getInstance().bytChatNoteType == 3) {
                                    if (Param.getInstance().vChatMapNote != null) {
                                        by3 = (byte) Param.getInstance().vChatMapNote.size();
                                    }
                                } else {
                                    Param.getInstance().getClass();
                                    if (Param.getInstance().bytChatNoteType == 8) {
                                        if (Param.getInstance().vChatFightNote != null) {
                                            by3 = (byte) Param.getInstance().vChatFightNote.size();
                                        }
                                    } else {
                                        Param.getInstance().getClass();
                                        if (Param.getInstance().bytChatNoteType == 7 && Param.getInstance().vSystemNote != null) {
                                            by3 = (byte) Param.getInstance().vSystemNote.size();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (by2 == Param.getInstance().bytChatNoteType) {
            if (by3 >= 20) {
                if (this.shtOneMenuMove > 0) {
                    this.shtOneMenuMove = (short) (this.shtOneMenuMove - 1);
                } else if (this.shtOneMenuRoll > 0) {
                    this.shtOneMenuRoll = (short) (this.shtOneMenuRoll - 1);
                } else {
                    this.shtWordMoveY = 0;
                }
            } else if (this.shtOneMenuMove < 0) {
                this.shtOneMenuMove = 0;
            }
        } else {
            return;
        }
    }

    /*
     * Unable to fully structure code
     */
    private void drawChat(final Graphics graphics, final Vector vector, final int n) {
        short n2 = 0;
        final int n3 = 6 + this.tabStyleInstance.GettabBottomY;
        final int n4 = Macro.SCREEN_WIDTH - 24;
        final int n5 = Macro.SCREEN_HEIGHT - n3 - 31;
        this.bytChatShowMax = (byte) vector.size();
        byte b;
        if (this.bytChatShowMax > this.bytWordMaxH) {
            b = this.bytWordMaxH;
        } else {
            b = this.bytChatShowMax;
        }
        this.scroll_OFF = (DCanvas.getInstance().blnSpoolr ? ScrollText.arrowhead_width : 0);
        if (this.shtOneMenuMove != -1) {
            LayoutStyle.getInstance().drawSelectBox(12, n + this.shtOneMenuMove * Macro.FONTHEIGHT, StringManager.getNewLineW() - this.scroll_OFF, Macro.FONTHEIGHT);
        }
        DrawBase.setClip(12, n3, n4 - this.scroll_OFF, n5);
        final byte bytChatNoteType = Param.getInstance().bytChatNoteType;
        Param.getInstance().getClass();
        Label_0562:
        {
            if (bytChatNoteType != 7) {
                final byte bytChatNoteType2 = Param.getInstance().bytChatNoteType;
                Param.getInstance().getClass();
                if (bytChatNoteType2 != 8) {
                    for (byte b2 = 0; b2 < b; ++b2) {
                        final Vector vector2 = (Vector) vector.elementAt(b2 + this.shtOneMenuRoll);
                        int n6 = 12;
                        final int n7 = n + b2 * Macro.FONTHEIGHT;
                        for (int i = 0; i < vector2.size(); i = (byte) (i + 1)) {
                            final ChatContent chatContent = (ChatContent) vector2.elementAt(i);
                            if (chatContent.type == 2) {
                                if (this.shtOneMenuMove == b2) {
                                    n2 += 14;
                                    Param.getInstance().sprface.drawAnimationFrame(graphics, chatContent.imageID, 0, n6 + this.shtWordMoveY + 7, n7 + 7);
                                } else {
                                    Param.getInstance().sprface.drawAnimationFrame(graphics, chatContent.imageID, 0, n6 + 7, n7 + 7);
                                }
                                n6 += 14;
                            } else if (chatContent.type == 1) {
                                graphics.setColor(chatContent.fontColor);
                                if (this.shtOneMenuMove == b2) {
                                    DrawBase.drawString(chatContent.character, n6 + this.shtWordMoveY, n7, chatContent.fontColor, 20);
                                    n2 += (short) Macro.font.stringWidth(chatContent.character);
                                } else {
                                    DrawBase.drawString(chatContent.character, n6, n7, chatContent.fontColor, 20);
                                }
                                n6 += Macro.font.stringWidth(chatContent.character);
                            }
                        }
                    }
                    break Label_0562;
                }
            }
            for (byte b3 = 0; b3 < b; ++b3) {
                final String s = (String) vector.elementAt(b3 + this.shtOneMenuRoll);
                int n8 = 12;
                if (this.shtOneMenuMove == b3) {
                    n8 = this.shtWordMoveY + 12;
                    n2 = (short) Macro.font.stringWidth(s);
                }
                DrawBase.drawString(s, n8, n + b3 * Macro.FONTHEIGHT, 16711680, 20);
            }
        }
        if (n2 > StringManager.getNewLineW()) {
            this.shtWordMoveY -= GameUI.getInstance().bytMsgSpeed;
            if (-this.shtWordMoveY > n2) {
                this.shtWordMoveY = (short) (Macro.SCREEN_WIDTH - 12);
            }
        }
        DCanvas.getInstance().clearScreen();
    }

    public short getOneMove() {
        return (short) (this.shtOneMenuMove + this.shtOneMenuRoll);
    }

    public byte getTeamPlayerMove(byte by) {
        int n = 0;
        byte by2 = 0;
        boolean bl = false;
        Enumeration enumeration = Param.getInstance().vTeam.elements();
        while (enumeration.hasMoreElements()) {
            TeamObject teamObject = (TeamObject) enumeration.nextElement();
            if (teamObject.bytTeamId == by + 1) {
                n = (byte) (n + 1);
            }
            if (n == this.getOneMove() + 1) {
                bl = true;
                break;
            }
            by2 = (byte) (by2 + 1);
        }
        if (bl) {
            return by2;
        }
        return -1;
    }

    public byte getMasterPlayerMove(byte by) {
        int n = 0;
        byte by2 = 0;
        boolean bl = false;
        Enumeration enumeration = this.vTempVessel.elements();
        while (enumeration.hasMoreElements()) {
            TeamObject teamObject = (TeamObject) enumeration.nextElement();
            if (teamObject.bytRelation == by + 1) {
                n = (byte) (n + 1);
            }
            if (n == this.getOneMove() + 1) {
                bl = true;
                break;
            }
            by2 = (byte) (by2 + 1);
        }
        if (bl) {
            return by2;
        }
        return -1;
    }

    public byte getPropRectMove() {
        return (byte) (this.bytPropMove + this.getOneMove() * Macro.PROPRECT_WIDTH);
    }

    public void logic(int n) {
        if (!this.blnIsThis) {
            return;
        }
        if (this.intTimer > 0) {
            this.intTimer -= n;
        }
        GameUI.getInstance().logicMoney();
        if (FullInfo.getInstance() != null) {
            FullInfo.getInstance().logic(n);
        } else if (!this.CanPress && this.twodialog != null) {
            this.twodialog.logicTwoRect();
        } else if (SeeDetail.getInstance() != null) {
            SeeDetail.getInstance().logic(n);
        } else {
            if (Pet.getInstance() != null) {
                Pet.getInstance().logicPetFlash();
            }
            if (Param.getInstance().oSelectNpc != null) {
                if (!Param.getInstance().blnNpcOptionWork) {
                    if (Param.getInstance().intNpcOptionCounter > 0) {
                        Param.getInstance().intNpcOptionCounter -= n;
                    } else {
                        Param.getInstance().blnNpcOptionWork = true;
                        this.setButton(1, 0, 2);
                    }
                } else if (this.blnWordRollType && Param.getInstance().intNpcOptionCounter <= 0) {
                    this.logicWordRoll();
                }
            }
            if (this.blnOpenMake) {
                this.logicMake(n);
                return;
            }
            if (DCanvas.getInstance().IsKeyRelease(65536) || DCanvas.getInstance().IsKeyRelease(262144)) {
                DCanvas.getInstance().buttonLeftFlash = 0;
                this.keyLeftSoftEvent();
            } else if (DCanvas.getInstance().isKeyDown(65536) || DCanvas.getInstance().IsKeyRelease(262144)) {
                DCanvas.getInstance().buttonLeftFlash = 1;
            } else if (DCanvas.getInstance().IsKeyRelease(131072)) {
                DCanvas.getInstance().buttonRightFlash = 0;
                this.keyRightSoftEvent();
            } else if (DCanvas.getInstance().isKeyDown(131072)) {
                DCanvas.getInstance().buttonRightFlash = 1;
            } else if (DCanvas.getInstance().isKeyDown(4096)) {
                if (this.bytTwoMenuState == 0) {
                    this.keyUpEvent();
                } else if (this.bytMenuState == -127 && this.bytTwoMenuState == 3) {
                    this.setTaskHortation((byte) 1);
                }
            } else if (DCanvas.getInstance().isKeyDown(8192)) {
                if (this.bytTwoMenuState == 0) {
                    this.keyDwonEvent();
                } else if (this.bytMenuState == -127 && this.bytTwoMenuState == 3) {
                    this.setTaskHortation((byte) 2);
                }
            } else if (DCanvas.getInstance().isKeyDown(16384)) {
                if (this.bytTwoMenuState == 0) {
                    this.keyLeftEvent();
                } else if (this.bytMenuState == -127 && this.bytTwoMenuState == 3) {
                    this.setTaskHortation((byte) 3);
                }
            } else if (DCanvas.getInstance().isKeyDown(32768)) {
                if (this.bytTwoMenuState == 0) {
                    this.keyRightEvent();
                } else if (this.bytMenuState == -127 && this.bytTwoMenuState == 3) {
                    this.setTaskHortation((byte) 4);
                }
            } else if (DCanvas.getInstance().IsKeyHold(4)) {
                if (this.bytTwoMenuState == 0) {
                    this.keyNum2Event();
                }
            } else if (DCanvas.getInstance().IsKeyHold(256)) {
                if (this.bytTwoMenuState == 0) {
                    this.keyNum8Event();
                }
            } else if (DCanvas.getInstance().isKeyDown(32)) {
                if (this.bytTwoMenuState == 0) {
                    this.keyNum5Event();
                }
            } else if (DCanvas.getInstance().isKeyDown(2)) {
                if (this.bytTwoMenuState == 0) {
                    this.keyNum1Event();
                }
            } else if (DCanvas.getInstance().isKeyDown(8)) {
                if (this.bytTwoMenuState == 0) {
                    this.keyNum3Event();
                }
            } else if (DCanvas.getInstance().isKeyDown(1)) {
                this.keyNum0Event();
            } else {
                this.keyNoneEvent();
            }
            if (DCanvas.getInstance().blnIsTouch && DCanvas.getInstance().blnPointerPressed) {
                if (DCanvas.getInstance().PointerDwonSoftKey_Left()) {
                    if (this.bytButtonType[1] != 100) {
                        if (FullInfo.getInstance() != null) {
                            FullInfo.getInstance().keyLeftSoft();
                        } else {
                            this.keyLeftSoftEvent();
                        }
                    }
                } else if (DCanvas.getInstance().PointerDwonSoftKey_Right()) {
                    if (this.bytButtonType[2] != 100) {
                        if (FullInfo.getInstance() != null) {
                            FullInfo.getInstance().keyRightSoft();
                        } else {
                            this.keyRightSoftEvent();
                        }
                    }
                } else if (DCanvas.getInstance().isPointerDownInSide(0, 0, Macro.SCREEN_WIDTH, Macro.SCREEN_HEIGHT)) {
                    this.pointerOption();
                }
            }
            if (Param.getInstance().playerColne != null) {
                Param.getInstance().playerColne.checkAction(0);
                Param.getInstance().playerColne.update();
            }
            this.update(n);
        }
    }

    private void update(int n) {
        switch (this.bytMenuState) {
            case 10:
            case 11: {
                if (this.inlay == null) {
                    break;
                }
                this.inlay[1].update();
                this.inlay[2].update();
                break;
            }
            case -50: {
                if (Param.getInstance().PARTNER_relation <= 0 || Param.getInstance().partner == null) {
                    break;
                }
                Param.getInstance().partner.checkAction(0);
                Param.getInstance().partner.update();
                break;
            }
            case -81: {
                this.SetShortcutKey();
                break;
            }
            case 60: {
                if (this.triangle == null) {
                    break;
                }
                this.triangle[0].update();
                this.triangle[1].update();
                break;
            }
        }
    }

    private void keyNum0Event() {
        switch (this.bytMenuState) {
            case -127: {
                int n = 0;
                String string = "";
                String string2 = "";
                byte by = 0;
                byte by2 = 0;
                if (Param.getInstance().awardMustPackage != null && Param.getInstance().awardMustPackage.getFocal()) {
                    by2 = Param.getInstance().awardMustPackage.getCellBoxIndex();
                }
                if (Param.getInstance().awardSelectPackage != null && Param.getInstance().awardSelectPackage.getFocal()) {
                    by = Param.getInstance().awardSelectPackage.getCellBoxIndex();
                }
                byte by3 = ((TaskObject) Param.getInstance().vCommonList.elementAt((int) 0)).bytType;
                switch (by3) {
                    case 0:
                    case 1: {
                        if (this._temp0 != null && Param.getInstance().awardSelectPackage != null && Param.getInstance().awardSelectPackage.getFocal()) {
                            string = ((PackageObject) this._temp0.get((Object) new Integer((int) by))).strInfo;
                            n = ((PackageObject) this._temp0.get((Object) new Integer((int) by))).intColor;
                            string2 = ((PackageObject) this._temp0.get((Object) new Integer((int) by))).strName;
                            break;
                        }
                        if (this._temp1 == null || Param.getInstance().awardMustPackage == null || !Param.getInstance().awardMustPackage.getFocal()) {
                            break;
                        }
                        string = ((PackageObject) this._temp1.get((Object) new Integer((int) by2))).strInfo;
                        n = ((PackageObject) this._temp1.get((Object) new Integer((int) by2))).intColor;
                        string2 = ((PackageObject) this._temp1.get((Object) new Integer((int) by2))).strName;
                        break;
                    }
                    case 2: {
                        if (this._temp0 != null && Param.getInstance().awardSelectPackage != null && Param.getInstance().awardSelectPackage.getFocal()) {
                            string = ((PackageObject) this._temp0.get((Object) new Integer((int) by))).strInfo;
                            n = ((PackageObject) this._temp0.get((Object) new Integer((int) by))).intColor;
                            string2 = ((PackageObject) this._temp0.get((Object) new Integer((int) by))).strName;
                            break;
                        }
                        if (this._temp1 == null || Param.getInstance().awardMustPackage == null || !Param.getInstance().awardMustPackage.getFocal()) {
                            break;
                        }
                        string = ((PackageObject) this._temp1.get((Object) new Integer((int) by))).strInfo;
                        n = ((PackageObject) this._temp1.get((Object) new Integer((int) by))).intColor;
                        string2 = ((PackageObject) this._temp1.get((Object) new Integer((int) by))).strName;
                    }
                }
                if (Param.getInstance().awardMustPackage != null && Param.getInstance().awardMustPackage.getFocal()) {
                    if (string.equals("")) {
                        GameUI.getInstance().getEquioAttribute(this._temp1, Param.getInstance().awardMustPackage.getCellBoxIndex());
                        new FullInfo(string2, n, (byte) -127);
                        FullInfo.getInstance().setFullRectMenu_Vector();
                    } else {
                        new FullInfo(string2, n, (byte) -127);
                        FullInfo.getInstance().setFullRectMenu(string);
                    }
                }
                if (Param.getInstance().awardSelectPackage == null || !Param.getInstance().awardSelectPackage.getFocal()) {
                    break;
                }
                if (string.equals("")) {
                    GameUI.getInstance().getEquioAttribute(this._temp0, Param.getInstance().awardSelectPackage.getCellBoxIndex());
                    new FullInfo(string2, n, (byte) -127);
                    FullInfo.getInstance().setFullRectMenu_Vector();
                    break;
                }
                new FullInfo(string2, n, (byte) -127);
                FullInfo.getInstance().setFullRectMenu(string);
            }
        }
    }

    private void setUpDown(byte by) {
        short s;
        if (this.bytTwoMenuState == 2) {
            return;
        }
        if (this.bytMenuState == 60) {
            s = (short) (Param.getInstance().shtSettingData.length - 1);
        } else {
            s = this.bytMenuState == 35 ? Param.bytChatSubTeamNumCount[this.bytChatTeam] : (this.bytMenuState == -48 ? Param.bytChatSubMasterNumCount[this.intRelation] : this.shtMenuMoveLength);
            if (s == 0) {
                return;
            }
        }
        if (by == 2) {
            if (this.shtOneMenuMove < (this.bytWordMaxH < s ? this.bytWordMaxH - 1 : s - 1)) {
                this.shtOneMenuMove = (short) (this.shtOneMenuMove + 1);
            } else {
                this.shtOneMenuMove = (short) (this.bytWordMaxH - 1);
                this.shtOneMenuRoll = (short) (this.shtOneMenuRoll + 1);
                if (this.shtOneMenuRoll >= s - this.bytWordMaxH + 1) {
                    this.shtOneMenuRoll = 0;
                    this.shtOneMenuMove = 0;
                }
            }
        } else if (this.shtOneMenuMove <= 0) {
            this.shtOneMenuMove = 0;
            this.shtOneMenuRoll = (short) (this.shtOneMenuRoll - 1);
            if (this.shtOneMenuRoll < 0) {
                this.shtOneMenuRoll = (short) (this.bytWordMaxH < s ? s - this.bytWordMaxH : 0);
                this.shtOneMenuMove = (short) (this.bytMenuState == 42 ? -1 : (short) (this.bytWordMaxH < s ? this.bytWordMaxH - 1 : s - 1));
            }
        } else {
            this.shtOneMenuMove = (short) (this.shtOneMenuMove - 1);
        }
        this.shtWordMoveY = 0;
    }

    public void setBackOff() {
        Param.popupDialogInstance.setShow(false);
        Param.getInstance().vMenuMemory.removeElementAt(Param.getInstance().vMenuMemory.size() - 1);
        byte by = (byte) Param.getInstance().vMenuMemory.size();
        byte by2 = ((MenuObject) Param.getInstance().vMenuMemory.elementAt((int) (by - 1))).bytType;
        switch (by2) {
            case 11: {
                Param.getInstance().hImgObject = null;
                Param.getInstance().hPackage = null;
                this.setState((byte) 1, Param.getInstance().oSelectNpc.getNpcName());
                this.setNpcOption();
                break;
            }
            case 10: {
                Param.getInstance().hImgObject = null;
                Param.getInstance().hPackage = null;
                this.setNpcTalk(Param.getInstance().oSelectNpc.getNpcName(), Param.getInstance().oSelectNpc.strTALK);
                this.setNpcOption();
                break;
            }
            case 31: {
                Param.getInstance().hImgObject = null;
                Param.getInstance().hPackage = null;
                this.setNpcOption();
                break;
            }
            case 13: {
                Param.getInstance().hImgObject = null;
                if (Param.getInstance().hPackage != null) {
                    Enumeration enumeration = Param.getInstance().hPackage.elements();
                    while (enumeration.hasMoreElements()) {
                        GameUI.getInstance().addObjectImg(((PackageObject) enumeration.nextElement()).shtIcon, "prop");
                    }
                }
                this.clearMove();
                this.setState((byte) -39, Param.getInstance().strTempTitlestr);
                break;
            }
        }
    }

    public void setEquip() {
        if (this.bytMoveType == 0) {
            this.setInfoContent(Param.getInstance().hPackageEquip, this.bytRoleMove);
        }
        if (this.bytMoveType == 1) {
            this.setInfoContent(Param.getInstance().hPackage, this.getPropRectMove());
        }
    }

    public void setEquipImage(Hashtable hashtable) {
        this.setWordMove();
        if (hashtable != null) {
            Enumeration enumeration = hashtable.elements();
            while (enumeration.hasMoreElements()) {
                PackageObject packageObject = (PackageObject) enumeration.nextElement();
                DebugFrame.getInstance().logIn("->>_po.shtIcon=" + packageObject.shtIcon);
                GameUI.getInstance().addObjectImg(packageObject.shtIcon, "prop");
            }
        }
    }

    private void setRoleSee(int n) {
        switch (n) {
            case 2: {
                if (this.bytRoleMove == 8) {
                    return;
                }
                this.bytRoleMove = (byte) (this.bytRoleMove + 4);
                if (this.bytRoleMove <= 7) {
                    break;
                }
                this.bytRoleMove = (byte) (this.bytRoleMove - 8);
                break;
            }
            case 1: {
                if (this.bytRoleMove == 8) {
                    return;
                }
                this.bytRoleMove = (byte) (this.bytRoleMove - 4);
                if (this.bytRoleMove >= 0) {
                    break;
                }
                this.bytRoleMove = (byte) (this.bytRoleMove + 8);
                break;
            }
            case 3: {
                if (this.bytRoleMove != 8 && this.bytTwoMenuState == 0) {
                    this.bytRoleMove = (byte) (this.bytRoleMove - 1);
                    if (this.bytMenuState == -5) {
                        if (this.bytRoleMove >= 0) {
                            break;
                        }
                        this.bytRoleMove = (byte) 7;
                        break;
                    }
                    if (this.bytRoleMove >= 0 && this.bytRoleMove != 3) {
                        break;
                    }
                    this.bytRoleMove = (byte) 8;
                    if (this.bytMenuState == -7) {
                        this.setRoleAttribute(0);
                    } else {
                        this.setRoleAttribute(4);
                    }
                    return;
                }
                return;
            }
            case 4: {
                if (this.bytTwoMenuState != 0) {
                    return;
                }
                if (this.bytRoleMove == 8) {
                    this.bytRoleMove = 0;
                    break;
                }
                this.bytRoleMove = (byte) (this.bytRoleMove + 1);
                if (this.bytRoleMove <= 7) {
                    break;
                }
                this.bytRoleMove = 0;
            }
        }
        if (this.bytMenuState == -7 || this.bytMenuState == -5) {
            this.setInfoContent(Param.getInstance().hPackageEquip, this.bytRoleMove);
        } else {
            this.setInfoContent(Param.getInstance().hPackage, this.bytRoleMove);
        }
    }

    public void setInfoContent(Hashtable hashtable, int n) {
        block40:
        {
            block37:
            {
                block38:
                {
                    PackageObject packageObject;
                    block41:
                    {
                        block39:
                        {
                            Param.getInstance().blnColorInfo = false;
                            if (GameUI.getInstance().vsColorInfo != null) {
                                GameUI.getInstance().vsColorInfo = null;
                            }
                            this.intShopMoney = 0;
                            this.strOneDescribeChar = null;
                            this.strRectPropName = "";
                            this.strRectPropKey = "";
                            if (hashtable == null) {
                                break block37;
                            }
                            if (!hashtable.containsKey(new Integer(n))) {
                                break block38;
                            }
                            packageObject = (PackageObject) hashtable.get(new Integer(n));
                            this.intColor = packageObject.shtStaminaMax == 0 ? packageObject.intColor : (packageObject.shtStamina == 0 ? Macro.INT_PROP_COLOR[0] : packageObject.intColor);
                            StringBuffer stringBuffer = new StringBuffer();
                            stringBuffer.append(packageObject.strName);
                            if (packageObject.bytAggrandizementLv > 0) {
                                stringBuffer.append(" +");
                                stringBuffer.append(packageObject.bytAggrandizementLv);
                            }
                            this.strRectPropName = stringBuffer.toString();
                            this.strRectPropKey = GameUI.getInstance().getStringShortcutKey(packageObject.bytKey, true);
                            if (this.bytMenuState != 10) {
                                String string = packageObject.strInfo;
                                if (string.equals("")) {
                                    this.strOneDescribeChar = null;
                                    GameUI.getInstance().getEquioAttribute(hashtable, n);
                                } else {
                                    this.strOneDescribeChar = StringManager.wenZi(StringManager.displaceNpcTalk(string), Macro.SCREEN_WIDTH - 2 * LayoutStyle.getInstance().earheight - 12);
                                }
                            }
                            if (this.bytMenuState != -35) {
                                break block39;
                            }
                            this.strRectPropKey = "";
                            this.intShopMoney = packageObject.intPaiMaiPrice;
                            this.setButton(1, 4, 2);
                            break block40;
                        }
                        if (this.bytMenuState != -72) {
                            break block41;
                        }
                        this.strRectPropKey = "";
                        this.intShopMoney = packageObject.intPaiMaiPrice;
                        switch (this.personalShopState) {
                            case 0: {
                                if (Param.getInstance().hShopPackage != null) {
                                    if (Param.getInstance().hShopPackage.containsKey(new Integer(this.personalShopSellPackage.getCellBoxIndex()))) {
                                        this.setButton(1, 4, 2);
                                        break;
                                    }
                                    this.setButton(1, 100, 2);
                                    break;
                                }
                                break block40;
                            }
                            case 2: {
                                if (Param.getInstance().hPackage == null) {
                                    break block40;
                                }
                                if (Param.getInstance().hPackage.containsKey(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()))) {
                                    this.setButton(1, 4, 2);
                                    break;
                                }
                                this.setButton(1, 100, 2);
                            }
                            default: {
                                break;
                            }
                        }
                        break block40;
                    }
                    if (this.bytMenuState == 9) {
                        this.setButton(1, 4, 2);
                    } else if (this.bytMenuState == -7) {
                        this.setButton(1, 19, 2);
                    } else {
                        if (this.bytMenuState == -70) {
                            this.strRectPropKey = "";
                        } else if (this.bytMenuState == -11) {
                            this.strRectPropKey = "";
                            this.intShopMoney = packageObject.intPaiMaiPrice;
                            if (packageObject.bytBagPlace != 0) {
                                this.setButton(1, 4, 2);
                            } else {
                                this.setButton(1, 22, 2);
                            }
                            this.setWordMove();
                            return;
                        }
                        if (this.tabStyleInstance.TabFocuse) {
                            this.setButton(1, 100, 2);
                        } else if (this.ChooseJewel && this.bytTagMove == 4) {
                            this.setButton(1, 0, 2);
                        } else {
                            this.setButton(1, 4, 2);
                        }
                    }
                    break block40;
                }
                if (this.bytMenuState == -35) {
                    if (ORPMe.ME.blnStall) {
                        this.setButton(1, 1, 2);
                    } else {
                        this.setButton(1, 4, 2);
                    }
                } else if (Param.getInstance().bytPropBagType == 0 && (this.bytMenuState == 10 && this.bytMoveType == 1 || this.bytMenuState == 33 || this.bytMenuState == 36 || this.bytMenuState == 37 || this.bytMenuState == 39) && !this.tabStyleInstance.TabFocuse) {
                    this.setButton(1, 4, 2);
                } else {
                    this.setButton(1, 100, 2);
                }
                break block40;
            }
            if (Param.getInstance().bytPropBagType == 0 && (this.bytMenuState == 10 && this.bytMoveType == 1 || this.bytMenuState == 36 || this.bytMenuState == 37 || this.bytMenuState == 39) && !this.tabStyleInstance.TabFocuse || this.bytMenuState == -35) {
                this.setButton(1, 4, 2);
            } else {
                this.setButton(1, 100, 2);
            }
        }
        if (this.bytMailSubtabIndex == 3) {
            this.setButton(1, 4, 2);
        }
        this.setWordMove();
    }

    public void setTaskType() {
        this.strOneTitlestr = "任务";
        this.clearMove();
        this.bytTitleMove = this.tabStyleInstance.getTabCurrentIndex();
        if (this.bytTitleMove == 0) {
            if (Param.getInstance().vTaskList != null && !Param.getInstance().vTaskList.isEmpty()) {
                this.shtMenuMoveLength = (short) Param.getInstance().vTaskList.size();
                this.setChoiceMove(this.shtMenuMoveLength, this.getOneMove(), this.bytWordMaxH);
                this.setButton(1, 4, 2);
            } else {
                this.shtMenuMoveLength = 0;
                this.setButton(1, 100, 2);
            }
        } else if (this.vTempVessel == null) {
            this.shtMenuMoveLength = 0;
            this.setButton(1, 23, 2);
        } else {
            this.shtMenuMoveLength = (short) this.vTempVessel.size();
            this.setButton(1, 20, 2);
        }
        Param.getInstance().IntRgbColor = DrawBase.getRGB(StringManager.getNewLineW(), Macro.FONTHEIGHT, 1144385017);
    }

    public void setLifeSkillType() {
        this.clearMove();
        if (this.vTempVessel == null) {
            this.shtMenuMoveLength = 0;
            this.setButton(1, 4, 2);
        } else {
            this.shtMenuMoveLength = (short) this.vTempVessel.size();
            this.setButton(1, 4, 2);
        }
        Param.getInstance().IntRgbColor = DrawBase.getRGB(StringManager.getNewLineW(), Macro.FONTHEIGHT, 1144385017);
    }

    public void setTaskHortation(byte by) {
        Hashtable hashtable = ((TaskObject) Param.getInstance().vCommonList.elementAt((int) 0)).vSelectObject;
        Hashtable hashtable2 = ((TaskObject) Param.getInstance().vCommonList.elementAt((int) 0)).vNSelectObject;
        Hashtable hashtable3 = ((TaskObject) Param.getInstance().vCommonList.elementAt((int) 0)).vOtherObject;
        byte by2 = ((TaskObject) Param.getInstance().vCommonList.elementAt((int) 0)).bytType;
        if (by2 == 2 && hashtable == null) {
            return;
        }
        switch (by) {
            case 1: {
                if (by2 == 2 || hashtable2 == null && hashtable3 == null || hashtable == null) {
                    break;
                }
                this.shtOneMenuRoll = 0;
                if (this.shtOneMenuMove > hashtable.size() - 1) {
                    this.shtOneMenuMove = (short) (hashtable.size() - 1);
                }
                this.setWordMove();
                break;
            }
            case 2: {
                if (by2 == 2 || hashtable2 == null && hashtable3 == null || hashtable == null) {
                    break;
                }
                if (this.shtOneMenuRoll == 0) {
                    this.shtOneMenuRoll = 1;
                    if (hashtable2 != null && this.shtOneMenuMove > hashtable2.size() - 1) {
                        this.shtOneMenuMove = (short) (hashtable2.size() - 1);
                    } else if (hashtable3 != null && this.shtOneMenuMove > hashtable3.size() - 1) {
                        this.shtOneMenuMove = (short) (hashtable3.size() - 1);
                    }
                }
                this.setWordMove();
                break;
            }
            case 3: {
                if (this.shtOneMenuMove == -1) {
                    this.shtOneMenuMove = 0;
                } else if (this.shtOneMenuMove > 0) {
                    this.shtOneMenuMove = (short) (this.shtOneMenuMove - 1);
                }
                this.setWordMove();
                break;
            }
            case 4: {
                if (this.shtOneMenuRoll == 0 && hashtable != null) {
                    if (this.shtOneMenuMove == -1) {
                        this.shtOneMenuMove = 0;
                    } else if (this.shtOneMenuMove < hashtable.size() - 1) {
                        this.shtOneMenuMove = (short) (this.shtOneMenuMove + 1);
                    }
                } else if (hashtable2 != null && this.shtOneMenuMove < hashtable2.size() - 1) {
                    this.shtOneMenuMove = (short) (this.shtOneMenuMove + 1);
                } else if (hashtable3 != null && this.shtOneMenuMove < hashtable3.size() - 1) {
                    this.shtOneMenuMove = (short) (this.shtOneMenuMove + 1);
                }
                this.setWordMove();
            }
        }
        this.setTaskPrizeInfo();
    }

    public void setTaskPrizeInfo() {
        this.setTaskInfo((short) 0);
        byte by = ((TaskObject) Param.getInstance().vCommonList.elementAt((int) 0)).bytType;
        this.strSeeTitlestr = ((TaskObject) Param.getInstance().vCommonList.elementAt((int) 0)).strName;
        int n = 0;
        String string = "";
        String string2 = "";
        byte by2 = 0;
        byte by3 = 0;
        if (Param.getInstance().awardMustPackage != null && Param.getInstance().awardMustPackage.getFocal()) {
            by2 = Param.getInstance().awardMustPackage.getCellBoxIndex();
        }
        if (Param.getInstance().awardSelectPackage != null && Param.getInstance().awardSelectPackage.getFocal()) {
            by3 = Param.getInstance().awardSelectPackage.getCellBoxIndex();
        }
        switch (by) {
            case 0:
            case 1: {
                if (this.shtOneMenuRoll == 0) {
                    if (this._temp0 != null) {
                        string = ((PackageObject) this._temp0.get((Object) new Integer((int) by2))).strInfo;
                        n = ((PackageObject) this._temp0.get((Object) new Integer((int) by2))).intColor;
                        string2 = ((PackageObject) this._temp0.get((Object) new Integer((int) by2))).strName;
                        break;
                    }
                    if (this._temp1 == null) {
                        break;
                    }
                    string = ((PackageObject) this._temp1.get((Object) new Integer((int) by3))).strInfo;
                    n = ((PackageObject) this._temp1.get((Object) new Integer((int) by3))).intColor;
                    string2 = ((PackageObject) this._temp1.get((Object) new Integer((int) by3))).strName;
                    break;
                }
                if (this._temp1 == null) {
                    break;
                }
                string = ((PackageObject) this._temp1.get((Object) new Integer((int) by3))).strInfo;
                n = ((PackageObject) this._temp1.get((Object) new Integer((int) by3))).intColor;
                string2 = ((PackageObject) this._temp1.get((Object) new Integer((int) by3))).strName;
                break;
            }
            case 2: {
                if (this._temp0 == null) {
                    break;
                }
                string = ((PackageObject) this._temp0.get((Object) new Integer((int) by2))).strInfo;
                n = ((PackageObject) this._temp0.get((Object) new Integer((int) by2))).intColor;
                string2 = ((PackageObject) this._temp0.get((Object) new Integer((int) by2))).strName;
            }
        }
        this.intColor = n;
        this.strRectPropName = string2;
        this.strinfo = string;
    }

    public void setFBCD() {
        if (Param.getInstance().vInfoContent != null) {
            this.clearMove();
            this.shtMenuMoveLength = (short) Param.getInstance().vInfoContent.size();
            Param.getInstance().IntRgbColor = DrawBase.getRGB(StringManager.getNewLineW(), Macro.FONTHEIGHT, 1144385017);
            Param.getInstance().bytNpcDataType = (byte) 9;
        }
    }

    public void setRoleList(byte by, Vector vector) {
        this.clearMove();
        if (vector != null && !vector.isEmpty()) {
            this.shtMenuMoveLength = (short) vector.size();
            this.setChoiceMove(this.shtMenuMoveLength, this.getOneMove(), this.bytWordMaxH);
            this.setButton(1, 4, 2);
        } else {
            this.shtMenuMoveLength = 0;
            this.setButton(1, 4, 2);
        }
    }

    public void logicPropKey(byte by) {
        switch (by) {
            case 1: {
                if (this.shtOneMenuMove > 0) {
                    this.shtOneMenuMove = (short) (this.shtOneMenuMove - 1);
                    break;
                }
                if (this.shtOneMenuRoll == 0) {
                    break;
                }
                this.shtOneMenuRoll = (short) (this.shtOneMenuRoll - 1);
                break;
            }
            case 2: {
                this.shtOneMenuMove = (short) (this.shtOneMenuMove + 1);
                if (this.getPropRectMove() > Param.getInstance().packageBoxMaxNum - 1) {
                    this.shtOneMenuMove = (short) (this.shtOneMenuMove - 1);
                    break;
                }
                if (this.shtOneMenuMove <= this.bytPackMaxH - 1) {
                    break;
                }
                this.shtOneMenuMove = (short) (this.shtOneMenuMove - 1);
                this.shtOneMenuRoll = (short) (this.shtOneMenuRoll + 1);
                break;
            }
            case 3: {
                this.bytPropMove = (byte) (this.bytPropMove - 1);
                if (this.bytPropMove >= 0) {
                    break;
                }
                this.bytPropMove = (byte) (Macro.PROPRECT_WIDTH - 1);
                if (this.getPropRectMove() <= Param.getInstance().packageBoxMaxNum - 1) {
                    break;
                }
                this.bytPropMove = (byte) (Param.getInstance().packageBoxMaxNum % Macro.PROPRECT_WIDTH - 1);
                break;
            }
            case 4: {
                this.bytPropMove = (byte) (this.bytPropMove + 1);
                if (this.bytPropMove <= Macro.PROPRECT_WIDTH - 1 && this.getPropRectMove() <= Param.getInstance().packageBoxMaxNum - 1) {
                    break;
                }
                this.bytPropMove = 0;
            }
        }
        if (this.bytMenuState == -35 || this.bytMenuState == -72) {
            this.setInfoContent(Param.getInstance().hShopPackage, this.getPropRectMove());
        } else if (this.bytMenuState == 33) {
            this.setPetList();
        } else if (this.bytMenuState == 58) {
            this.setPetPack();
        } else {
            this.setInfoContent(Param.getInstance().hPackage, this.getPropRectMove());
        }
    }

    public void setAboutRole() {
        Enumeration enumeration = Param.getInstance().htRolePlayer.elements();
        Param.getInstance().vCommonList = null;
        while (enumeration.hasMoreElements()) {
            ORPlayer oRPlayer = (ORPlayer) enumeration.nextElement();
            if (oRPlayer.bytCountry != ORPMe.ME.bytCountry || oRPlayer.intUserId == ORPMe.ME.intUserId) {
                continue;
            }
            if (Param.getInstance().vCommonList == null) {
                Param.getInstance().vCommonList = new Vector(1, 1);
            }
            Param.getInstance().vCommonList.addElement(this.getTeam(oRPlayer));
        }
        this.clearMove();
        if (Param.getInstance().vCommonList != null) {
            this.shtMenuMoveLength = (short) Param.getInstance().vCommonList.size();
        } else {
            this.shtMenuMoveLength = 0;
            DCanvas.getInstance().addInformation("附近无友方玩家");
            this.setButton(1, 100, 2);
        }
        this.setChoiceMove(this.shtMenuMoveLength, this.getOneMove(), this.bytWordMaxH);
    }

    private TeamObject getTeam(ORPlayer oRPlayer) {
        TeamObject teamObject = new TeamObject();
        teamObject.strName = oRPlayer.strNickName;
        teamObject.intId = oRPlayer.intUserId;
        teamObject.setOcc(oRPlayer.bytOccupation);
        teamObject.shtLevel = oRPlayer.shtLevel;
        teamObject.blnIsMale = oRPlayer.bytIsMale == 1;
        teamObject.blnIsOnLine = true;
        return teamObject;
    }

    public void addAboutRole(ORPlayer oRPlayer) {
        if (oRPlayer.bytCountry != ORPMe.ME.bytCountry) {
            return;
        }
        if (Param.getInstance().vCommonList == null) {
            Param.getInstance().vCommonList = new Vector(1, 1);
        }
        this.clearTwoRect();
        Param.getInstance().vCommonList.addElement(this.getTeam(oRPlayer));
        this.shtMenuMoveLength = (short) Param.getInstance().vCommonList.size();
    }

    public void delAboutRole(ORPlayer oRPlayer) {
        if (oRPlayer.bytCountry != ORPMe.ME.bytCountry) {
            return;
        }
        int n = 0;
        while (n < Param.getInstance().vCommonList.size()) {
            TeamObject teamObject = (TeamObject) Param.getInstance().vCommonList.elementAt(n);
            if (teamObject.intId == oRPlayer.intUserId) {
                this.clearTwoRect();
                Param.getInstance().vCommonList.removeElementAt(n);
                if (Param.getInstance().vCommonList.isEmpty()) {
                    this.shtMenuMoveLength = 0;
                    Param.getInstance().vCommonList = null;
                    this.setButton(1, 100, 2);
                } else {
                    this.shtMenuMoveLength = (short) Param.getInstance().vCommonList.size();
                    if (n <= this.getOneMove()) {
                        if (this.shtOneMenuMove > 0) {
                            this.shtOneMenuMove = (short) (this.shtOneMenuMove - 1);
                        } else if (this.shtOneMenuRoll > 0) {
                            this.shtOneMenuRoll = (short) (this.shtOneMenuRoll - 1);
                        }
                    }
                }
                this.setChoiceMove(this.shtMenuMoveLength, this.getOneMove(), this.bytWordMaxH);
                break;
            }
            n = (short) (n + 1);
        }
    }

    public void setRoleAttribute(int n) {
        Param.getInstance().blnColorInfo = false;
        this.intColor = 0;
        StringBuffer stringBuffer = new StringBuffer();
        switch (n) {
            case 0: {
                if (this.blnRoleInfo) {
                    stringBuffer.append("昵称：");
                    stringBuffer.append(ORPMe.ME.strNickName);
                    stringBuffer.append("\n");
                    stringBuffer.append("职业：");
                    stringBuffer.append(ORole.getOccName(ORPMe.ME.bytOccupation));
                    stringBuffer.append("\n");
                    stringBuffer.append("等级：");
                    stringBuffer.append(ORPMe.ME.shtLevel);
                    stringBuffer.append("\n");
                    stringBuffer.append("性别：");
                    stringBuffer.append(ORPMe.ME.bytIsMale == 1 ? "男" : "女");
                    stringBuffer.append("\n");
                    stringBuffer.append("种族：");
                    stringBuffer.append(ORPMe.ME.bytCountry == 1 ? "龙之传人" : "恶魔之子");
                    stringBuffer.append("\n");
                }
                stringBuffer.append("生命：");
                stringBuffer.append(ORPMe.ME.intHP);
                stringBuffer.append("/");
                stringBuffer.append(ORPMe.ME.intHPMax);
                stringBuffer.append("\n");
                stringBuffer.append("魔法：");
                stringBuffer.append(ORPMe.ME.intMP);
                stringBuffer.append("/");
                stringBuffer.append(ORPMe.ME.intMPMax);
                stringBuffer.append("\n");
                stringBuffer.append("经验：");
                stringBuffer.append(ORPMe.ME.ShowEXP);
                stringBuffer.append("/");
                stringBuffer.append(ORPMe.ME.ShowNextEXP);
                stringBuffer.append("\n");
                stringBuffer.append("金钱：");
                stringBuffer.append(ORPMe.Gold);
                stringBuffer.append("\n");
                this.RoleProperty(stringBuffer, ORPMe.ME);
                this.getText = stringBuffer.toString();
                this.strOneDescribeChar = StringManager.wenZi(stringBuffer.toString(), this.Font_Show_Width);
                this.PRORERTY_UI_EXPLAIN = new String[]{ORPMe.ME.strNickName, ORole.getOccName(ORPMe.ME.bytOccupation), "" + ORPMe.ME.shtLevel, ORPMe.ME.bytIsMale == 1 ? "男" : "女", ORPMe.ME.bytCountry == 1 ? "龙之传人" : "恶魔之子"};
                break;
            }
            case 1: {
                stringBuffer.append("力：");
                stringBuffer.append(ORPMe.ME.Strength);
                stringBuffer.append("\n");
                stringBuffer.append("敏：");
                stringBuffer.append(ORPMe.ME.Agility);
                stringBuffer.append("\n");
                stringBuffer.append("耐：");
                stringBuffer.append(ORPMe.ME.Stamina);
                stringBuffer.append("\n");
                stringBuffer.append("智：");
                stringBuffer.append(ORPMe.ME.Intellect);
                stringBuffer.append("\n");
                stringBuffer.append("神：");
                stringBuffer.append(ORPMe.ME.Energy);
                stringBuffer.append("\n");
                stringBuffer.append("运：");
                stringBuffer.append(ORPMe.ME.Luck);
                stringBuffer.append("\n");
                stringBuffer.append("物理攻击：");
                stringBuffer.append(ORPMe.ME.AttackMin);
                stringBuffer.append("-");
                stringBuffer.append(ORPMe.ME.AttackMax);
                stringBuffer.append("\n");
                stringBuffer.append("物理攻速：");
                stringBuffer.append(ORPMe.ME.strAttrick);
                stringBuffer.append("\n");
                stringBuffer.append("物理暴击：");
                stringBuffer.append(ORPMe.ME.ArmLuckOdds);
                stringBuffer.append("\n");
                stringBuffer.append("物理命中：");
                stringBuffer.append(ORPMe.ME.ArmTargetOdds);
                stringBuffer.append("\n");
                stringBuffer.append("物理闪避：");
                stringBuffer.append(ORPMe.ME.JookOdds);
                stringBuffer.append("\n");
                stringBuffer.append("物理防御：");
                stringBuffer.append(ORPMe.ME.Recovery);
                stringBuffer.append("\n");
                stringBuffer.append("法术攻击：");
                stringBuffer.append(ORPMe.ME.MagicAttrack);
                stringBuffer.append("\n");
                stringBuffer.append("法术暴击：");
                stringBuffer.append(ORPMe.ME.MagicLuckOdds);
                stringBuffer.append("\n");
                stringBuffer.append("法术命中：");
                stringBuffer.append(ORPMe.ME.MagicTargetOdds);
                stringBuffer.append("\n");
                stringBuffer.append("神圣抗性：");
                stringBuffer.append(ORPMe.ME.HolinessRecovery);
                stringBuffer.append("\n");
                stringBuffer.append("暗影抗性：");
                stringBuffer.append(ORPMe.ME.ShadowRecovery);
                stringBuffer.append("\n");
                stringBuffer.append("自然抗性：");
                stringBuffer.append(ORPMe.ME.EarthRecovery);
                stringBuffer.append("\n");
                stringBuffer.append("冰霜抗性：");
                stringBuffer.append(ORPMe.ME.WaterRecovery);
                stringBuffer.append("\n");
                stringBuffer.append("火焰抗性：");
                stringBuffer.append(ORPMe.ME.FireRecovery);
                stringBuffer.append("\n");
                this.getText = stringBuffer.toString();
                this.strOneDescribeChar = StringManager.wenZi(stringBuffer.toString(), this.Font_Show_Width);
                break;
            }
            case 3: {
                stringBuffer.append("被杀次数：");
                stringBuffer.append(ORPMe.ME.SKILLENEMY);
                stringBuffer.append("\n");
                stringBuffer.append("杀敌数量：");
                stringBuffer.append(ORPMe.ME.SKILLNUM);
                stringBuffer.append("\n");
                stringBuffer.append("切磋胜利：");
                stringBuffer.append(ORPMe.ME.PKWIN);
                stringBuffer.append("\n");
                stringBuffer.append("切磋次数：");
                stringBuffer.append(ORPMe.ME.PKNUM);
                stringBuffer.append("\n");
                this.getText = stringBuffer.toString();
                this.strOneDescribeChar = StringManager.wenZi(stringBuffer.toString(), this.Font_Show_Width);
                break;
            }
            case 4: {
                if (Param.getInstance().oSelectRole == null) {
                    return;
                }
                this.strOneTitlestr = Param.getInstance().oSelectRole.strNickName;
                this.strRectPropName = "人物状况";
                stringBuffer.append("昵称：");
                stringBuffer.append(Param.getInstance().oSelectRole.strNickName);
                stringBuffer.append("\n");
                stringBuffer.append("职业：");
                stringBuffer.append(ORole.getOccName(Param.getInstance().oSelectRole.bytOccupation));
                stringBuffer.append("\n");
                stringBuffer.append("等级：");
                stringBuffer.append(Param.getInstance().oSelectRole.shtLevel);
                stringBuffer.append("\n");
                stringBuffer.append("性别：");
                stringBuffer.append(Param.getInstance().oSelectRole.bytIsMale == 1 ? "男" : "女");
                stringBuffer.append("\n");
                stringBuffer.append("种族：");
                stringBuffer.append(Param.getInstance().oSelectRole.bytCountry == 1 ? "龙之传人" : "恶魔之子");
                stringBuffer.append("\n");
                this.RoleProperty(stringBuffer, Param.getInstance().oSelectRole);
                this.strOneDescribeChar = StringManager.wenZi(stringBuffer.toString(), Macro.SCREEN_WIDTH);
                this.setButton(1, 4, 2);
            }
        }
    }

    private void RoleProperty(StringBuffer stringBuffer, ORole oRole) {
        stringBuffer.append("帮派：");
        if (oRole.strConsortia != null && !oRole.strConsortia.equals("")) {
            stringBuffer.append(oRole.strConsortia);
        } else {
            stringBuffer.append("暂无");
        }
        stringBuffer.append("\n");
        stringBuffer.append("帮派职务：");
        if (oRole.societyName != null && !oRole.societyName.equals("")) {
            stringBuffer.append(oRole.societyName);
        } else {
            stringBuffer.append("暂无");
        }
        stringBuffer.append("\n");
        stringBuffer.append("配偶：");
        if (oRole.spouse != null && !oRole.spouse.equals("")) {
            stringBuffer.append(oRole.spouse);
        } else {
            stringBuffer.append("暂无");
        }
        stringBuffer.append("\n");
        stringBuffer.append("师傅：");
        if (oRole.MasterName != null && !oRole.MasterName.equals("")) {
            stringBuffer.append(oRole.MasterName);
        } else {
            stringBuffer.append("暂无");
        }
        stringBuffer.append("\n");
        stringBuffer.append("徒弟：");
        if (oRole.appreticeName != null && !oRole.appreticeName.equals("")) {
            stringBuffer.append(oRole.appreticeName);
        } else {
            stringBuffer.append("暂无");
        }
        stringBuffer.append("\n");
    }

    public void setTaskInfo(short s) {
        try {
            short s2;
            String string;
            Object object;
            Enumeration enumeration;
            this.setGrrdTableFrom();
            StringManager.blnPageSeleted = true;
            this.describeH = Macro.SCREEN_HEIGHT - 38 - 30 >> 1;
            this.boxX = 10;
            this.boxUpY = 40;
            this.boxDownY = 38 + this.describeH + 2;
            this.boxW = this.fromTable.getCell(2, 1).getCellW() - 12 - 8;
            this.boxH = this.describeH - 2;
            this.packageH = 24;
            this.awardY = this.boxDownY + this.boxH - 44 - Macro.FONTHEIGHT - 8;
            this.selectY = this.awardY + this.packageH + Macro.FONTHEIGHT;
            this.socrollBoxY = this.boxDownY;
            int n = this.boxX + 4;
            if (Macro.SCREEN_HEIGHT <= 240) {
                this.awardY = this.awardY + Macro.FONTHEIGHT - 8;
                this.selectY = this.awardY + this.packageH;
                n += Macro.font.stringWidth(this.strSelect);
            }
            this._temp0 = ((TaskObject) Param.getInstance().vCommonList.elementAt((int) s)).vSelectObject;
            this._temp1 = ((TaskObject) Param.getInstance().vCommonList.elementAt((int) s)).vNSelectObject;
            if (this._temp1 != null && this._temp1.size() != 0) {
                Param.getInstance().awardMustPackage = new PackageBox(n, this.awardY, this._temp1.size() * this.packageH, this.packageH, this._temp1.size(), false);
                Param.getInstance().awardMustPackage.reset(this._temp1.size());
                Param.popupDialogInstance.setShow(true);
                if (this._temp1 != null) {
                    enumeration = this._temp1.elements();
                    while (enumeration.hasMoreElements()) {
                        object = (PackageObject) enumeration.nextElement();
                        string = ((PackageObject) object).strName;
                        s2 = ((PackageObject) object).shtIcon;
                        GameUI.getInstance().addObjectImg(s2, "prop");
                    }
                }
            }
            if (this._temp0 != null && this._temp0.size() != 0) {
                if (Macro.SCREEN_HEIGHT <= 240) {
                    this.awardY += Macro.FONTHEIGHT;
                }
                Param.getInstance().awardSelectPackage = new PackageBox(n, this.selectY, this._temp0.size() * this.packageH, this.packageH, this._temp0.size(), false);
                Param.getInstance().awardSelectPackage.reset(this._temp0.size());
                Param.popupDialogInstance.setShow(true);
                if (this._temp0 != null) {
                    enumeration = this._temp0.elements();
                    while (enumeration.hasMoreElements()) {
                        object = (PackageObject) enumeration.nextElement();
                        string = ((PackageObject) object).strName;
                        s2 = ((PackageObject) object).shtIcon;
                        GameUI.getInstance().addObjectImg(s2, "prop");
                    }
                }
            }
            TaskObject task = (TaskObject) Param.getInstance().vCommonList.elementAt(s);
            Param.getInstance().vInfoContent = new Vector(1, 1);
            this.intColor = this.getTaskColor(task.shtLevel);
            this.strSeeTitlestr = task.strName;
            object = StringManager.displaceNpcTalk(task.strInfo);
            if (this.WordContent == null) {
                this.WordContent = new AnalysisWordContent();
            }
            this.WordContent.analysisChatContent("Cấp：" + task.shtLevel + "\n" + (String) object, 0, StringManager.getNewLineW());
            this.bytFullMenuMoveLength = (byte) this.WordContent.shtLenght;
            if (task.strCondition != null) {
                int n2 = 0;
                while (n2 < task.strCondition.length) {
                    String[] stringArray = StringManager.wenZi(task.strCondition[n2], StringManager.getNewLineW());
                    int n3 = 0;
                    while (n3 < stringArray.length) {
                        ChatContent chatContent = new ChatContent();
                        chatContent.character = String.valueOf(stringArray[n3]) + (task.bytConditionType[n2] == 1 ? "[kết thúc]" : "");
                        chatContent.fontColor = task.bytConditionType[n2] == 1 ? Macro.INT_PROP_COLOR[0] : 0;
                        Param.getInstance().vInfoContent.addElement(chatContent);
                        n3 = (byte) (n3 + 1);
                    }
                    n2 = (byte) (n2 + 1);
                }
                this.bytFullMenuMoveLength = (byte) (this.bytFullMenuMoveLength + (byte) (task.strCondition.length + 1));
            }
            if (task.intMoney != 0 || task.intEXP != 0) {
                this.strOneMenuOption = new String[1];
                this.strOneMenuOption[0] = "Phần thưởng:";
                if (task.intMoney != 0) {
                    this.strOneMenuOption[0] = String.valueOf(this.strOneMenuOption[0]) + "Tiền " + task.intMoney + " ";
                }
                if (task.intEXP != 0) {
                    int n4 = 0;
                    n4 = ORPMe.ME.shtLevel < task.shtLevel ? task.intEXP : ((n4 = (int) ((float) task.intEXP * (1.0f - 0.08f * (float) (ORPMe.ME.shtLevel - task.shtLevel)))) < 10 ? 10 : n4);
                    this.strOneMenuOption[0] = String.valueOf(this.strOneMenuOption[0]) + "Kinh nghiệm " + n4;
                }
            }
            if (task.bytType == 1) {
                this.setButton(1, 12, 2);
            } else if (task.bytType == 2) {
                this.setButton(1, 13, 2);
            } else if (task.bytConditionType != null && task.bytConditionType[0] != 1) {
                if (task.bytIsCarry[0] != 1) {
                    this.setButton(1, 20, 2);
                } else {
                    this.setButton(1, 20, 2);
                }
            } else {
                this.setButton(1, 100, 2);
            }
            this.clearMove();
            this.bytTwoMenuState = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearTaskInfo() {
        this.strOneMenuOption = null;
        this.strOneDescribeChar = null;
        Param.getInstance().vInfoContent = null;
        Param.getInstance().vCommonList = null;
        this._temp0 = null;
        this._temp1 = null;
        Param.getInstance().awardMustPackage = null;
        Param.getInstance().awardSelectPackage = null;
        Param.popupDialogInstance.setShow(false);
        if (this.WordContent != null) {
            this.WordContent = null;
        }
    }

    public void addTaskImage(short s, boolean bl) {
        short s2;
        byte by;
        byte by2;
        this.shtOneMenuMove = 0;
        TaskObject taskObject = (TaskObject) Param.getInstance().vCommonList.elementAt(s);
        Hashtable hashtable = taskObject.vSelectObject;
        Hashtable hashtable2 = taskObject.vNSelectObject;
        Hashtable hashtable3 = taskObject.vOtherObject;
        if (hashtable != null) {
            by2 = (byte) hashtable.size();
            by = 0;
            while (by < by2) {
                s2 = ((PackageObject) hashtable.get((Object) new Integer((int) by))).shtIcon;
                GameUI.getInstance().addObjectImg(s2, "prop");
                by = (byte) (by + 1);
            }
        }
        if (hashtable2 != null) {
            by2 = (byte) hashtable2.size();
            by = 0;
            while (by < by2) {
                s2 = ((PackageObject) hashtable2.get((Object) new Integer((int) by))).shtIcon;
                GameUI.getInstance().addObjectImg(s2, "prop");
                by = (byte) (by + 1);
            }
        }
        if (hashtable3 != null) {
            by2 = (byte) hashtable3.size();
            by = 0;
            while (by < by2) {
                s2 = ((PackageObject) hashtable3.get((Object) new Integer((int) by))).shtIcon;
                GameUI.getInstance().addObjectImg(s2, "prop");
                by = (byte) (by + 1);
            }
        }
    }

    public void sendTaskHandle(final byte b, final byte b2) {
        if (Param.getInstance().vMenuMemory == null) {
            return;
        }
        final MenuObject menuObject = (MenuObject) Param.getInstance().vMenuMemory.elementAt(Param.getInstance().vMenuMemory.size() - 1);
        if (b == 0) {
            NetSend.getInstance().sendNpcOneOption(Param.getInstance().oSelectNpc.intUserId, menuObject.bytStep, menuObject.intOptionId);
        } else {
            final TaskObject taskObject = (TaskObject) Param.getInstance().vCommonList.elementAt(0);
            if (taskObject.vSelectObject == null) {
                NetSend.getInstance().sendNpcTaskOption(Param.getInstance().oSelectNpc.intUserId, menuObject.bytStep, menuObject.intOptionId, 0);
            } else {
                NetSend.getInstance().sendNpcTaskOption(Param.getInstance().oSelectNpc.intUserId, menuObject.bytStep, menuObject.intOptionId, ((PackageObject) taskObject.vSelectObject.get(new Integer(b2))).intId);
            }
        }
        final MenuObject menuObject2 = (MenuObject) Param.getInstance().vMenuMemory.elementAt(0);
        final int[] intsOptionId = menuObject2.intsOptionId;
        final String[] strOption = menuObject2.strOption;
        final short[] shtIcon = menuObject2.shtIcon;
        final MenuObject[][] vNextMenu = menuObject2.vNextMenu;
        int[] intsOptionId2 = null;
        String[] array = null;
        short[] shtIcon2 = null;
        MenuObject[][] vNextMenu2 = null;
        this.shtMenuMoveLength = 0;
        if (intsOptionId != null && intsOptionId.length > 1) {
            this.shtMenuMoveLength = (short) (intsOptionId.length - 1);
            intsOptionId2 = new int[intsOptionId.length - 1];
            array = new String[intsOptionId.length - 1];
            shtIcon2 = new short[intsOptionId.length - 1];
            vNextMenu2 = new MenuObject[intsOptionId.length - 1][];
            int n = 0;
            for (int i = 0; i < intsOptionId.length; i = (byte) (i + 1)) {
                if (intsOptionId[i] != menuObject.intOptionId) {
                    intsOptionId2[n] = intsOptionId[i];
                    array[n] = strOption[i];
                    shtIcon2[n] = shtIcon[i];
                    vNextMenu2[n] = vNextMenu[i];
                    n = (byte) (n + 1);
                }
            }
        }
        menuObject2.intsOptionId = intsOptionId2;
        menuObject2.strOption = array;
        menuObject2.shtIcon = shtIcon2;
        menuObject2.vNextMenu = vNextMenu2;
        this.strOneMenuOption = array;
        this.setButton(1, 100, 100);
        this.setBackOff();
        if (Macro.BLN_ACCERATE_NPC_TASK) {
            FakeServer.getInstance().removeNpcMenuData(Param.getInstance().oSelectNpc.intUserId);
        }
    }

    public void getPeople(Vector vector, boolean bl) {
        Param.getInstance().shtNoncePage = 1;
        Param.getInstance().shtAllPage = 1;
        this.shtMenuMoveLength = 0;
        if (bl) {
            Param.getInstance().vCommonList = vector;
            if (Param.getInstance().vCommonList != null) {
                this.shtMenuMoveLength = (short) Param.getInstance().vCommonList.size();
                if (this.bytMenuState == -31 || this.bytMenuState == -48) {
                    this.addFriendImage();
                } else {
                    this.addSocialIntercourse();
                }
            }
        } else {
            this.vTempVessel = vector;
            if (this.vTempVessel != null) {
                this.shtMenuMoveLength = (short) this.vTempVessel.size();
                this.addFriendImage();
            }
        }
        if (this.bytMenuState == -48) {
            if (this.vTempVessel != null) {
                this.setButton(1, 4, 2);
            } else {
                this.setButton(1, 100, 2);
            }
        } else {
            this.setButton(1, 4, 2);
        }
        this.blnIsLoadOver = true;
    }

    private void pointerOption() {
        int n;
        int n2;
        short s;
        switch (this.bytMenuState) {
            case -26:
            case -25: {
                if (MenuUI.getInstance().scrolltext == null) {
                    break;
                }
                MenuUI.getInstance().scrolltext.Point();
                break;
            }
            case -72:
            case -35: {
                this.updatePersonalShopTouch();
                break;
            }
            case 11: {
                this.inlayHole.getTouchHole();
                this.inlayHole.setPopupShow();
                break;
            }
            case -48: {
                this.tabStyleInstance.getTabCurrentIndexByPointer();
                s = (short) ((Param.bytChatSubMasterNumCount[this.intRelation] > this.bytWordMaxH ? this.bytWordMaxH : Param.bytChatSubMasterNumCount[this.intRelation]) * Macro.FONTHEIGHT);
                if (!DCanvas.getInstance().IsPointerDown(12, this.tabStyleInstance.GettabBottomY + 8, StringManager.getNewLineW(), s - 2)) {
                    break;
                }
                short s2 = (short) ((DCanvas.getInstance().CurPressedY - this.tabStyleInstance.GettabBottomY - 8) / Macro.FONTHEIGHT);
                if (this.shtOneMenuMove == s2) {
                    this.keyLeftSoftEvent();
                    break;
                }
                this.shtOneMenuMove = s2;
                break;
            }
            case -33:
            case -32:
            case -31: {
                this.tabStyleInstance.getTabCurrentIndexByPointer();
                if (this.tabStyleInstance.getTabCurrentIndexChanged()) {
                    this.FriendTitleChoose();
                    this.tabStyleInstance.resetTabCurentIndexChanged();
                }
                int n3 = this.tabStyleInstance.GettabBottomY + Macro.FONTHEIGHT + 1;
                short s3 = (short) ((this.shtMenuMoveLength > this.bytWordMaxH ? this.bytWordMaxH : this.shtMenuMoveLength) * Macro.FONTHEIGHT);
                if (!DCanvas.getInstance().IsPointerDown(12, n3, StringManager.getNewLineW(), s3 - 2)) {
                    break;
                }
                short s4 = (short) ((DCanvas.getInstance().CurPressedY - n3) / Macro.FONTHEIGHT);
                if (this.shtOneMenuMove == s4) {
                    this.keyLeftSoftEvent();
                    break;
                }
                this.shtOneMenuMove = s4;
                break;
            }
            case 31: {
                this.tabStyleInstance.getTabCurrentIndexByPointer();
                if (this.bytMailSubtabIndex != this.tabStyleInstance.getTabCurrentIndex()) {
                    if (this.bytMailSubtabIndex != 3) {
                        Param.getInstance().vMenuMemory.removeElementAt(Param.getInstance().vMenuMemory.size() - 1);
                    }
                    this.bytMailSubtabIndex = this.tabStyleInstance.getTabCurrentIndex();
                    this.sendMailGood(this.tabStyleInstance.getTabCurrentIndex());
                }
                Param.getInstance().personalPackage.getTouch();
                if (DCanvas.getInstance().IsPointerDown(4, 38, IDefines.GLOBAL_UI_MAIN_TAB_WIDTH, 32)) {
                    this.tabStyleInstance.TabFocuse = true;
                    Param.getInstance().personalPackage.setFocal(false);
                    this.bytMailMove = 0;
                }
                if (this.bytMailSubtabIndex == 3) {
                    this.tabStyleInstance.TabFocuse = true;
                    Param.getInstance().personalPackage.setFocal(false);
                    this.bytMailMove = 0;
                    break;
                }
                if (DCanvas.getInstance().IsPointerDown(this.mailPackageBoxX, this.mailPackageBoxY, this.mailPackageBoxW, 48)) {
                    this.tabStyleInstance.TabFocuse = false;
                    Param.getInstance().personalPackage.setFocal(true);
                    this.bytMailMove = 1;
                }
                this.setInfoContent(Param.getInstance().hPackage, Param.getInstance().personalPackage.getCellBoxIndex());
                break;
            }
            case 64: {
                this.tabStyleInstance.getTabCurrentIndexByPointer();
                int n4 = this.tabStyleInstance.GettabBottomY + 5;
                short s5 = (short) ((this.shtMenuMoveLength > this.bytWordMaxH ? this.bytWordMaxH : this.shtMenuMoveLength) * Macro.FONTHEIGHT);
                if (!DCanvas.getInstance().IsPointerDown(12, n4, StringManager.getNewLineW(), s5 - 2)) {
                    break;
                }
                short s6 = (short) ((DCanvas.getInstance().CurPressedY - n4) / Macro.FONTHEIGHT);
                if (this.shtOneMenuMove == s6) {
                    this.keyLeftSoftEvent();
                    break;
                }
                this.shtOneMenuMove = s6;
                break;
            }
            case 63: {
                this.tabStyleInstance.getTabCurrentIndexByPointer();
                if (this.tabStyleInstance.getTabCurrentIndexChanged()) {
                    this.setTaskType();
                    this.tabStyleInstance.resetTabCurentIndexChanged();
                }
                int n5 = this.tabStyleInstance.GettabBottomY + Macro.FONTHEIGHT + 4;
                short s7 = (short) ((this.shtMenuMoveLength > this.bytWordMaxH ? this.bytWordMaxH : this.shtMenuMoveLength) * Macro.FONTHEIGHT);
                if (!DCanvas.getInstance().IsPointerDown(12, n5, StringManager.getNewLineW(), s7 - 2)) {
                    break;
                }
                short s8 = (short) ((DCanvas.getInstance().CurPressedY - n5) / Macro.FONTHEIGHT);
                if (this.shtOneMenuMove == s8) {
                    this.keyLeftSoftEvent();
                    break;
                }
                this.shtOneMenuMove = s8;
                break;
            }
            case -43: {
                short[] sArray = this.tabStyleInstance.getTabRect();
                if (DCanvas.getInstance().IsPointerDown(sArray[0], sArray[1], sArray[2], sArray[3])) {
                    this.tabStyleInstance.getTabCurrentIndexByPointer();
                    if (!this.tabStyleInstance.TabFocuse) {
                        break;
                    }
                    this.tabStyleInstance.TabFocuse = true;
                    Param.getInstance().personalPackage.setFocal(false);
                    Param.getInstance().npcShopBarPackage.setFocal(false);
                    this.setNpcStorageSelect();
                    this.setNpcStorageButton((byte) -1);
                    break;
                }
                if (DCanvas.getInstance().IsPointerDown(Param.getInstance().npcShopBarPackage.packageX, Param.getInstance().npcShopBarPackage.packageY, Param.getInstance().npcShopBarPackage.packageW, Param.getInstance().npcShopBarPackage.packageH)) {
                    Param.getInstance().npcShopBarPackage.getTouch();
                    if (!Param.getInstance().npcShopBarPackage.getFocal()) {
                        break;
                    }
                    Param.getInstance().npcShopBarPackage.setFocal(true);
                    Param.getInstance().personalPackage.setFocal(false);
                    this.tabStyleInstance.TabFocuse = false;
                    this.setNpcStorageSelect();
                    this.setNpcStorageButton((byte) 10);
                    if (!Param.getInstance().npcShopBarPackage.isDoublePoint()) {
                        break;
                    }
                    this.keyLeftSoftEvent();
                    break;
                }
                if (!DCanvas.getInstance().IsPointerDown(Param.getInstance().personalPackage.packageX, Param.getInstance().personalPackage.packageY, Param.getInstance().personalPackage.packageW, Param.getInstance().personalPackage.packageH)) {
                    break;
                }
                Param.getInstance().personalPackage.getTouch();
                if (!Param.getInstance().personalPackage.getFocal()) {
                    break;
                }
                Param.getInstance().personalPackage.setFocal(true);
                Param.getInstance().npcShopBarPackage.setFocal(false);
                this.tabStyleInstance.TabFocuse = false;
                this.setNpcStorageSelect();
                this.setNpcStorageButton(Macro.NPC_STEP_SELECT);
                if (!Param.getInstance().personalPackage.isDoublePoint()) {
                    break;
                }
                this.keyLeftSoftEvent();
                break;
            }
            case -39: {
                short[] sArray = Param.getInstance().npcShopBarTabStyle.getTabRect();
                short[] sArray2 = this.tabStyleInstance.getTabRect();
                if (DCanvas.getInstance().IsPointerDown(sArray[0], sArray[1], sArray[2], sArray[3])) {
                    Param.getInstance().npcShopBarTabStyle.getTabCurrentIndexByPointer();
                    if (!Param.getInstance().npcShopBarTabStyle.TabFocuse) {
                        break;
                    }
                    Param.getInstance().npcShopBarTabStyle.TabFocuse = true;
                    Param.getInstance().personalPackage.setFocal(false);
                    Param.getInstance().npcShopBarPackage.setFocal(false);
                    this.tabStyleInstance.TabFocuse = false;
                    this.setNpcBusinessButton((byte) -1);
                    break;
                }
                if (DCanvas.getInstance().IsPointerDown(sArray2[0], sArray2[1], sArray2[2], sArray2[3])) {
                    this.tabStyleInstance.getTabCurrentIndexByPointer();
                    if (!this.tabStyleInstance.TabFocuse) {
                        break;
                    }
                    this.tabStyleInstance.TabFocuse = true;
                    Param.getInstance().personalPackage.setFocal(false);
                    Param.getInstance().npcShopBarPackage.setFocal(false);
                    Param.getInstance().npcShopBarTabStyle.TabFocuse = false;
                    this.setNpcBusinessButton((byte) -1);
                    break;
                }
                if (DCanvas.getInstance().IsPointerDown(Param.getInstance().npcShopBarPackage.packageX, Param.getInstance().npcShopBarPackage.packageY, Param.getInstance().npcShopBarPackage.packageW, Param.getInstance().npcShopBarPackage.packageH)) {
                    Param.getInstance().npcShopBarPackage.getTouch();
                    if (!Param.getInstance().npcShopBarPackage.getFocal()) {
                        break;
                    }
                    Param.getInstance().npcShopBarPackage.setFocal(true);
                    Param.getInstance().personalPackage.setFocal(false);
                    Param.getInstance().npcShopBarTabStyle.TabFocuse = false;
                    this.tabStyleInstance.TabFocuse = false;
                    this.setNpcBusinessButton((byte) 2);
                    if (!Param.getInstance().npcShopBarPackage.isDoublePoint()) {
                        break;
                    }
                    this.keyLeftSoftEvent();
                    break;
                }
                if (!DCanvas.getInstance().IsPointerDown(Param.getInstance().personalPackage.packageX, Param.getInstance().personalPackage.packageY, Param.getInstance().personalPackage.packageW, Param.getInstance().personalPackage.packageH)) {
                    break;
                }
                Param.getInstance().personalPackage.getTouch();
                if (!Param.getInstance().personalPackage.getFocal()) {
                    break;
                }
                Param.getInstance().personalPackage.setFocal(true);
                Param.getInstance().npcShopBarPackage.setFocal(false);
                Param.getInstance().npcShopBarTabStyle.TabFocuse = false;
                this.tabStyleInstance.TabFocuse = false;
                this.setNpcBusinessButton((byte) 3);
                if (!Param.getInstance().personalPackage.isDoublePoint()) {
                    break;
                }
                this.keyLeftSoftEvent();
                break;
            }
            case -6: {
                byte by = this.rView.getViewCurrentIndex();
                this.rView.getViewCurrentIndexByPointer();
                if (by != this.rView.getViewCurrentIndex()) {
                    break;
                }
                this.keyLeftSoftEvent();
                break;
            }
            case 61: {
                if (this.isDrawSkillinfo) {
                    break;
                }
                this.tabStyleInstance.TabFocuse = true;
                this.tabStyleInstance.getTabCurrentIndexByPointer();
                if (this.bytTitleMove == this.tabStyleInstance.getTabCurrentIndex()) {
                    break;
                }
                this.bytTitleMove = this.tabStyleInstance.getTabCurrentIndex();
                this.setSkill();
            }
        }
        if (this.bytTwoMenuState != 2 && FullInfo.getInstance() == null) {
            if (this.bytTitleType != 0 && DCanvas.getInstance().IsPointerDown(this.shtTitleX, Macro.FONTHEIGHT + 2 + 3, this.shtTitleW, this.shtTitleH)) {
                this.bytTitleMove = (byte) ((DCanvas.getInstance().CurPressedX - this.shtTitleX) / (this.shtTitleW / this.bytTitleType));
                switch (this.bytMenuState) {
                    case -113: {
                        this.clearMakeContent();
                        this.getMakeSkillContent();
                    }
                }
            } else {
                switch (this.bytMenuState) {
                    case 42: {
                        this.tabStyleInstance.getTabCurrentIndexByPointer();
                        if (this.tabStyleInstance.getTabCurrentIndexChanged()) {
                            Param.getInstance().bytChatNoteType = this.tabStyleInstance.getTabCurrentIndex();
                            this.bytTitleMove = this.tabStyleInstance.getTabCurrentIndex();
                            this.keyLeftSoftEvent();
                            this.shtWordMoveY = 0;
                            this.clearMove();
                            this.blnSetChatNote = true;
                            this.setChatNote();
                            this.tabStyleInstance.resetTabCurentIndexChanged();
                        }
                        s = (short) ((this.shtMenuMoveLength > this.bytWordMaxH ? this.bytWordMaxH : this.shtMenuMoveLength) * Macro.FONTHEIGHT);
                        if (!DCanvas.getInstance().IsPointerDown(12, this.tabStyleInstance.GettabBottomY + 8, StringManager.getNewLineW(), s - 2)) {
                            break;
                        }
                        short s9 = (short) ((DCanvas.getInstance().CurPressedY - this.tabStyleInstance.GettabBottomY - 8) / Macro.FONTHEIGHT);
                        if (this.shtOneMenuMove == s9) {
                            this.keyLeftSoftEvent();
                            break;
                        }
                        this.shtOneMenuMove = s9;
                        this.shtWordMoveY = 0;
                        break;
                    }
                    case -82: {
                        s = (short) ((this.shtMenuMoveLength > this.bytWordMaxH ? this.bytWordMaxH : this.shtMenuMoveLength) * Macro.FONTHEIGHT);
                        if (!DCanvas.getInstance().IsPointerDown(12, 43, StringManager.getNewLineW(), s - 2)) {
                            break;
                        }
                        short s10 = (short) ((DCanvas.getInstance().CurPressedY - 38 - 5) / Macro.FONTHEIGHT);
                        if (this.shtOneMenuMove == s10) {
                            this.keyLeftSoftEvent();
                            break;
                        }
                        this.shtOneMenuMove = s10;
                        break;
                    }
                    case -19: {
                        break;
                    }
                    case -16:
                    case -15: {
                        s = (short) (38 + (Macro.SCREEN_HEIGHT >> 2) + 2 + 8);
                        int n6 = Macro.FONTHEIGHT;
                        if (this.bytTitleMove == 2) {
                            n6 = 25;
                        }
                        short s11 = (short) ((this.shtMenuMoveLength > this.bytWordMaxH ? this.bytWordMaxH : this.shtMenuMoveLength) * n6);
                        if (!DCanvas.getInstance().IsPointerDown(12, s, StringManager.getNewLineW(), s11 - 2)) {
                            break;
                        }
                        short s12 = (short) ((DCanvas.getInstance().CurPressedY - s) / n6);
                        if (this.shtOneMenuMove == s12) {
                            this.keyLeftSoftEvent();
                            break;
                        }
                        this.shtOneMenuMove = s12;
                        break;
                    }
                    case 61: {
                        if (this.isDrawSkillinfo) {
                            break;
                        }
                        int n7 = this.tabStyleInstance.getTabFrameRect()[1] + 25;
                        n2 = Macro.FONTHEIGHT;
                        if (this.bytTitleMove == 2) {
                            n2 = 25;
                        }
                        n = (short) ((this.shtMenuMoveLength > this.bytWordMaxH ? this.bytWordMaxH : this.shtMenuMoveLength) * n2);
                        if (!DCanvas.getInstance().IsPointerDown(12, n7, StringManager.getNewLineW(), n - 2)) {
                            break;
                        }
                        short s13 = (short) ((DCanvas.getInstance().CurPressedY - n7) / n2);
                        if (this.shtOneMenuMove == s13) {
                            this.keyLeftSoftEvent();
                            break;
                        }
                        this.shtOneMenuMove = s13;
                        break;
                    }
                    case -40: {
                        this.tabStyleInstance.getTabCurrentIndexByPointer();
                        this.tabStyleInstance.getTabCurrentIndex();
                        break;
                    }
                    case -36: {
                        int n8 = 38 + Macro.FONTHEIGHT + 4;
                        n2 = (short) ((this.shtMenuMoveLength > this.bytWordMaxH ? this.bytWordMaxH : this.shtMenuMoveLength) * Macro.FONTHEIGHT);
                        if (!DCanvas.getInstance().IsPointerDown(12, n8, StringManager.getNewLineW(), n2 - 2)) {
                            break;
                        }
                        n = (short) ((DCanvas.getInstance().CurPressedY - n8) / Macro.FONTHEIGHT);
                        if (this.shtOneMenuMove == n) {
                            this.keyLeftSoftEvent();
                            break;
                        }
                        this.shtOneMenuMove = (short) n;
                        this.setMailList(this.getOneMove());
                        break;
                    }
                    case 35: {
                        this.tabStyleInstance.getTabCurrentIndexByPointer();
                        short s14 = (short) ((Param.bytChatSubTeamNumCount[this.bytChatTeam] > this.bytWordMaxH ? this.bytWordMaxH : Param.bytChatSubTeamNumCount[this.bytChatTeam]) * Macro.FONTHEIGHT);
                        if (!DCanvas.getInstance().IsPointerDown(12, this.tabStyleInstance.GettabBottomY + 8, StringManager.getNewLineW(), s14 - 2)) {
                            break;
                        }
                        n2 = (short) ((DCanvas.getInstance().CurPressedY - this.tabStyleInstance.GettabBottomY - 8) / Macro.FONTHEIGHT);
                        if (this.shtOneMenuMove == n2) {
                            this.keyLeftSoftEvent();
                            break;
                        }
                        this.shtOneMenuMove = (short) n2;
                        break;
                    }
                    case -51:
                    case 34: {
                        int n9 = 38 + Macro.FONTHEIGHT + 5;
                        n2 = (short) ((this.shtMenuMoveLength > this.bytWordMaxH ? this.bytWordMaxH : this.shtMenuMoveLength) * Macro.FONTHEIGHT);
                        if (!DCanvas.getInstance().IsPointerDown(12, n9, StringManager.getNewLineW(), n2 - 2)) {
                            break;
                        }
                        n = (short) ((DCanvas.getInstance().CurPressedY - n9) / Macro.FONTHEIGHT);
                        if (this.shtOneMenuMove == n) {
                            this.keyLeftSoftEvent();
                            break;
                        }
                        this.shtOneMenuMove = (short) n;
                        break;
                    }
                    case -91: {
                        int n10 = Macro.FONTHEIGHT / 2 + 38;
                        n2 = (short) ((this.shtMenuMoveLength > this.bytWordMaxH ? this.bytWordMaxH : this.shtMenuMoveLength) * Macro.FONTHEIGHT);
                        if (!DCanvas.getInstance().IsPointerDown(12, n10, StringManager.getNewLineW(), n2 - 2)) {
                            break;
                        }
                        n = (short) ((DCanvas.getInstance().CurPressedY - n10) / Macro.FONTHEIGHT);
                        if (this.shtOneMenuMove == n) {
                            this.keyLeftSoftEvent();
                            break;
                        }
                        this.shtOneMenuMove = (short) n;
                        break;
                    }
                    case -113:
                    case -49:
                    case -46:
                    case -44:
                    case -38:
                    case -37: {
                        int n11 = 0;
                        n11 = this.bytTitleType != 0 ? Macro.FONTHEIGHT * 2 + 16 : Macro.FONTHEIGHT + 16;
                        n2 = (short) ((this.shtMenuMoveLength > this.bytWordMaxH ? this.bytWordMaxH : this.shtMenuMoveLength) * Macro.FONTHEIGHT);
                        if (!DCanvas.getInstance().IsPointerDown(12, n11, StringManager.getNewLineW(), n2 - 2)) {
                            break;
                        }
                        n = (short) ((DCanvas.getInstance().CurPressedY - n11) / Macro.FONTHEIGHT);
                        if (this.shtOneMenuMove == n) {
                            this.keyLeftSoftEvent();
                            break;
                        }
                        this.shtOneMenuMove = (short) n;
                        break;
                    }
                    case 58: {
                        break;
                    }
                    case -47: {
                        short s15 = (short) ((this.shtMenuMoveLength > this.bytWordMaxH ? this.bytWordMaxH : this.shtMenuMoveLength) * Macro.FONTHEIGHT + Macro.FONTHEIGHT);
                        if (!DCanvas.getInstance().IsPointerDown(12, 70 + Macro.FONTHEIGHT, StringManager.getNewLineW(), s15 - 2)) {
                            break;
                        }
                        n2 = (short) ((DCanvas.getInstance().CurPressedY - 38 - 32 - Macro.FONTHEIGHT) / Macro.FONTHEIGHT);
                        if (this.shtOneMenuMove == n2) {
                            this.keyLeftSoftEvent();
                            break;
                        }
                        this.shtOneMenuMove = (short) n2;
                        this.setParcelPostButton(this.getOneMove());
                        break;
                    }
                    case -80:
                    case -10:
                    case 1: {
                        if (this.strOneMenuOption == null || !DCanvas.getInstance().IsPointerDown(12, this.shtNDialogY, StringManager.getNewLineW(), this.strOneMenuOption.length * Macro.FONTHEIGHT - 2)) {
                            break;
                        }
                        short s16 = (short) ((DCanvas.getInstance().CurPressedY - this.shtNDialogY) / Macro.FONTHEIGHT);
                        if (this.shtOneMenuMove == s16) {
                            this.keyLeftSoftEvent();
                            break;
                        }
                        this.shtOneMenuMove = s16;
                        this.initWordRoll(this.strOneMenuOption[this.getOneMove()], (short) (Macro.SCREEN_WIDTH - 38));
                        break;
                    }
                    case -81: {
                        this.tabStyleInstance.getTabCurrentIndexByPointer();
                        if (this.tabStyleInstance.getTabCurrentIndexChanged()) {
                            this.ChangeTabState();
                            this.Shortcut.setFocal(false);
                            this.tabStyleInstance.resetTabCurentIndexChanged();
                        } else {
                            this.tabStyleInstance.TabFocuse = false;
                            this.Shortcut.getTouch();
                        }
                        int n12 = DCanvas.getInstance().CurPressedX;
                        if (DCanvas.getInstance().IsPointerDown(this.hotkeyX - 11 + 176, this.hotkeyY, 26, 27)) {
                            GameUI.getInstance().doChangeKeyType();
                            break;
                        }
                        if (!DCanvas.getInstance().IsPointerDown(this.hotkeyX - 10, this.hotkeyY, 8 * (GameUI.getInstance().bytHotKeyBoxWidth + GameUI.getInstance().bytHotKeyBoxDistance) + 22, Macro.bytb3height)) {
                            break;
                        }
                        if (DCanvas.getInstance().CurPressedX > this.hotkeyX - 11 + 176 + 26) {
                            n12 -= 26;
                        }
                        n2 = (n12 - this.hotkeyX + 11) / 22;
                        this.sendDate(n2);
                        break;
                    }
                    case -11: {
                        n2 = this.tabStyleInstance.getTabCurrentIndex();
                        this.tabStyleInstance.getTabCurrentIndexByPointer();
                        Param.getInstance().personalPackage.getTouch();
                        this.tabStyleInstance.TabFocuse = !Param.getInstance().personalPackage.getFocal();
                        n = this.tabStyleInstance.getTabCurrentIndex();
                        if (Param.MALL_BOX_NUM[n] > 0) {
                            Param.getInstance().personalPackage.reset(Param.MALL_BOX_NUM[n]);
                        }
                        if (n2 != n) {
                            this.setShangChengRes(n);
                        }
                        this.setSelectedButton((Hashtable) Param.getInstance().hMallPackage.get(new Integer(this.tabStyleInstance.getTabCurrentIndex())), Param.getInstance().personalPackage.getCellBoxIndex());
                        MenuUI.getInstance().setInfoContent((Hashtable) Param.getInstance().hMallPackage.get(new Integer(this.tabStyleInstance.getTabCurrentIndex())), Param.getInstance().personalPackage.getCellBoxIndex());
                        break;
                    }
                    case -7: {
                        this.tabStyleInstance.getTabCurrentIndexByPointer();
                        this.bytTitleMove = this.tabStyleInstance.getTabCurrentIndex();
                        if (this.tabStyleInstance.getTabCurrentIndexChanged()) {
                            this.refreshRoleInfo();
                            this.tabStyleInstance.resetTabCurentIndexChanged();
                        }
                        if (this.bytTitleMove == 3) {
                            short s17 = (short) ((this.shtMenuMoveLength > this.bytWordMaxH ? this.bytWordMaxH : this.shtMenuMoveLength) * Macro.FONTHEIGHT);
                            if (!DCanvas.getInstance().IsPointerDown(12, this.pointY, StringManager.getNewLineW(), s17 - 2)) {
                                break;
                            }
                            short s18 = (short) ((DCanvas.getInstance().CurPressedY - this.pointY) / Macro.FONTHEIGHT);
                            if (this.shtOneMenuMove == s18) {
                                this.keyLeftSoftEvent();
                                break;
                            }
                            this.shtOneMenuMove = s18;
                            break;
                        }
                        this.scrolltext.Point();
                        break;
                    }
                    case -50: {
                        this.scrolltext.Point();
                        break;
                    }
                    case -5: {
                        if (DCanvas.getInstance().IsPointerDown(this.shtRoleRectX, this.shtRoleRectY, 48, 55)) {
                            if (this.bytRoleMove == 8) {
                                this.keyLeftSoftEvent();
                                break;
                            }
                            this.bytRoleMove = (byte) 8;
                            if (this.bytMenuState == -6) {
                                this.setRoleAttribute(4);
                                break;
                            }
                            this.setRoleAttribute(0);
                            break;
                        }
                        if (!DCanvas.getInstance().IsPointerDown(this.shtPropRectX, this.shtPropRectY + 3, 90, 45)) {
                            break;
                        }
                        byte by = (byte) ((DCanvas.getInstance().CurPressedY - this.shtPropRectY - 3) / 23);
                        byte by2 = (byte) ((DCanvas.getInstance().CurPressedX - this.shtPropRectX) / 22);
                        if (this.bytRoleMove == by * 4 + by2) {
                            this.keyLeftSoftEvent();
                            break;
                        }
                        this.bytRoleMove = (byte) (by * 4 + by2);
                        if (this.bytMenuState == -6) {
                            this.setInfoContent(Param.getInstance().hPackage, this.bytRoleMove);
                            break;
                        }
                        this.setInfoContent(Param.getInstance().hPackageEquip, this.bytRoleMove);
                        break;
                    }
                    case -6: {
                        break;
                    }
                    case -127: {
                        if (Param.getInstance().vCommonList == null) {
                            break;
                        }
                        this.pointTaskMeed();
                        break;
                    }
                    case 10:
                    case 21:
                    case 22:
                    case 33:
                    case 36:
                    case 37:
                    case 38:
                    case 39: {
                        this.getMenuFoucs();
                        break;
                    }
                    case 60: {
                        int n13 = 36;
                        short s19 = (short) (this.sht_box_space + Macro.FONTHEIGHT + 5);
                        int n14 = 0;
                        while (n14 < Pram.STR_SET_SWITCH.length) {
                            if (DCanvas.getInstance().CurPressedY - 36 > s19 * n14 && DCanvas.getInstance().CurPressedY - 36 < s19 * (n14 + 1)) {
                                if (this.bytSetWordMove != (byte) (n14 + this.bytSetWordY)) {
                                    this.bytSetWordMove = (byte) n14;
                                    if (this.bytSetWordY > 0) {
                                        this.bytSetWordMove = (byte) (this.bytSetWordMove + this.bytSetWordY);
                                    }
                                    this.bytSetBoxY = (byte) n14;
                                    if (this.bytSetBoxY > 5) {
                                        this.bytSetBoxY = (byte) 5;
                                    }
                                } else if (this.bytSetWordMove == (byte) (n14 + this.bytSetWordY)) {
                                    this.keyRightEvent();
                                }
                            }
                            ++n14;
                        }
                        if (DCanvas.getInstance().IsPointerDown(this.sht_Triangle_L_x + (this.triangle_widht << 1), this.sht_Triangle_y, this.triangle_widht, Macro.SCREEN_HEIGHT - 30 - 50)) {
                            this.keyLeftEvent();
                        }
                        if (!DCanvas.getInstance().IsPointerDown(this.sht_Triangle_R_x - this.triangle_widht, this.sht_Triangle_y, this.triangle_widht << 2, Macro.SCREEN_HEIGHT - 30 - 50)) {
                            break;
                        }
                        this.keyRightEvent();
                    }
                }
            }
        }
        if (DCanvas.getInstance().blnSpoolr) {
            DCanvas.getInstance().blsTouchEvent = true;
            s = ScrollText.arrowhead_width;
            byte by = ScrollText.arrowhead_height;
            short s20 = DCanvas.getInstance().shtSpoolrX;
            short s21 = DCanvas.getInstance().shtSpoolrSY;
            n2 = DCanvas.getInstance().shtSpoolrEY - DCanvas.getInstance().shtSpoolrSY;
            n = s * 3;
            if (DCanvas.getInstance().IsPointerDragged(s20 - s, s21 - by, n, (n2 >> 1) + by)) {
                this.keyUpEvent();
            } else if (DCanvas.getInstance().IsPointerDragged(s20 - s, s21 + (n2 >> 1) - by, n, (n2 >> 1) + by)) {
                this.keyDwonEvent();
            } else if (DCanvas.getInstance().IsPointerDown(s20 - s, s21 - by, n, (n2 >> 1) + by)) {
                this.keyUpEvent();
            } else if (DCanvas.getInstance().IsPointerDown(s20 - s, s21 + (n2 >> 1) - by, n, (n2 >> 1) + by)) {
                this.keyDwonEvent();
            }
        }
    }

    private void getMenuFoucs() {
        byte by = this.tabStyleInstance.getTabCurrentIndex();
        if (!this.ChooseJewel) {
            this.rView.getViewCurrentIndexByPointer();
            this.tabStyleInstance.getTabCurrentIndexByPointer();
        }
        Param.getInstance().personalPackage.getTouch();
        if (Param.getInstance().personalPackage.getFocal()) {
            MenuUI.getInstance().setFocus(1);
        }
        if (this.tabStyleInstance.TabFocuse && !this.ChooseJewel && by != this.tabStyleInstance.getTabCurrentIndex()) {
            MenuUI.getInstance().setFocus(2);
            this.bytTagMove = this.tabStyleInstance.getTabCurrentIndex();
            this.tagUseEvent();
        }
        if (this.rView.getViewFocal()) {
            this.bytTagMove = 0;
            MenuUI.getInstance().setFocus(0);
            this.rView.getViewCurrentIndexByPointer();
            this.rView.setViewFocal(true);
        }
    }

    public void keyLeftSoftEvent() {
        if (this.bytButtonType[1] == 100) {
            return;
        }
        String[] stringArray = null;
        switch (this.bytMenuState) {
            case -25: {
                boolean bl = Macro.blnStart = !Macro.blnStart;
                if (Macro.blnStart) {
                    Macro.hNetList.clear();
                    Macro.netKey = -1;
                    Macro.tatalDefyTime = 0L;
                    Macro.upTime = 0L;
                    Macro.downTime = 0L;
                    Macro.missSum = 0;
                    DCanvas.getInstance().setNetLoad(true);
                    new Thread() {

                        public void run() {
                            long l = System.currentTimeMillis();
                            while (Macro.blnStart && Macro.netKey < Macro.numSend) {
                                long l2 = System.currentTimeMillis();
                                int n = (int) (l2 - l);
                                if (n < Macro.stepTime) {
                                    continue;
                                }
                                l = System.currentTimeMillis();
                                NetSend.getInstance().sendHttpSocket();
                            }
                        }
                    }.start();
                    break;
                }
                DCanvas.getInstance().setNetLoad(false);
                String string = "连接方式:" + (Rms.blnSelectHttp ? "HTTP连接\n" : "\nSOCKET 连接\n") + "发送次数:" + Macro.netKey + "\n" + "平均延迟:" + Macro.tatalDefyTime / (long) (Macro.netKey + 1 - (Macro.numSend - Macro.missSum)) + "ms\n" + "丢包次数:" + (Macro.netKey - Macro.missSum + 1) + "次";
                Macro.hNetList.put(new Integer(Macro.netKey), string);
                break;
            }
            case -83: {
                NetSend.getInstance().snedGmAppraise((byte) this.getOneMove());
                GameControl.getInstance().delUIbase(9);
                break;
            }
            case -81: {
                this.setBackSystem();
                this.clearShortcut();
                break;
            }
            case -82: {
                GameUI.getInstance().setMoveShortcutKeys(this.getMoveKey(this.getOneMove()));
                this.clearMoveKey();
                this.setBackSystem();
                break;
            }
            case -91: {
                int n;
                Integer n2 = (Integer) Param.getInstance().htAreaMapNpcIDList.elementAt(this.getOneMove());
                Map.getInstance().intMapTransferNpcId = n = n2.intValue();
                if (n2 == null) {
                    break;
                }
                if (Map.getInstance().shtMapId == GameUI.getInstance().shtMoveMapID) {
                    ONpc oNpc = (ONpc) Param.getInstance().htRoleNPC.get(n2);
                    if (oNpc != null) {
                        DebugFrame.getInstance().logIn("您就在本地图!");
                        if (!GameUI.getInstance().setAStar(oNpc.bytTileX, oNpc.bytTileY)) {
                            break;
                        }
                        GameUI.getInstance().cleanSmallMap();
                        break;
                    }
                    DebugFrame.getInstance().logIn("本地图中不存在该NPC!");
                    break;
                }
                DCanvas.getInstance().setNetLoad(true);
                NetSend.getInstance().sendAreaMapInfo((byte) 7, GameUI.getInstance().shtMoveMapID, Map.getInstance().bytCurAreaMapClanType, n);
                break;
            }
            case -49: {
                String string = "";
                if (this.strsFunction[this.getOneMove() * 2].indexOf("未使用") == -1) {
                    string = this.strsFunction[this.getOneMove() * 2].substring(2);
                }
                FormDes.getInstance().setMacroChat(string, this.strsFunction[this.getOneMove() * 2 + 1]);
                break;
            }
            case 9: {
                if (this.bytMoveType == 2) {
                    stringArray = new String[]{"放上物品", "物品查看"};
                    GameUI.getInstance().setTwoMenu(this.bytMenuState, stringArray, (byte) 10);
                    break;
                }
                if (this.bytMoveType != 0) {
                    break;
                }
                stringArray = new String[]{"物品查看"};
                GameUI.getInstance().setTwoMenu(this.bytMenuState, stringArray, (byte) 10);
                break;
            }
            case -43: {
                stringArray = null;
                switch (Macro.NPC_STEP_SELECT) {
                    case 10: {
                        stringArray = new String[]{"取　　出", "查看属性"};
                        break;
                    }
                    case 3:
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                    case 25: {
                        stringArray = new String[]{"存　　入", "查看属性"};
                        break;
                    }
                }
                if (stringArray == null) {
                    break;
                }
                GameUI.getInstance().setTwoMenu(this.bytMenuState, stringArray, (byte) 5);
                break;
            }
            case -39: {
                stringArray = null;
                switch (Macro.NPC_STEP_SELECT) {
                    case 2: {
                        Param.getInstance().IndexNpcShopBarTabStyle = Param.getInstance().npcShopBarPackage != null ? Param.getInstance().npcShopBarPackage.getCellBoxIndex() : (byte) 0;
                        if (this.blnBusinessMore) {
                            stringArray = new String[]{"购　　买", "购买多个", "查看属性"};
                            break;
                        }
                        stringArray = new String[]{"购　　买", "查看属性"};
                        break;
                    }
                    case 3: {
                        stringArray = new String[]{"出　　售", "查看属性"};
                        break;
                    }
                }
                if (stringArray == null) {
                    break;
                }
                GameUI.getInstance().setTwoMenu(this.bytMenuState, stringArray, (byte) 5);
                break;
            }
            case 21:
            case 22:
            case 33: {
                int n = 0;
                if (!this.blnTab) {
                    n = this.blnTabPet ? ((PackageObject) ORPMe.ME.hPackagePet.get((Object) new Integer((int) this.getPropRectMove()))).petKey : ((PackageObject) Param.getInstance().hPackage.get((Object) new Integer((int) this.getPropRectMove()))).petKey;
                }
                stringArray = ORPMe.ME.intPetId == n ? new String[]{"收回宠物"} : new String[]{"召唤宠物"};
                if (this.bytMoveType == 0 && (PackageObject) ORPMe.ME.hPackagePet.get(new Integer(this.bytRoleMove - 8)) != null) {
                    stringArray = new String[]{"宠物收起", "宠物战斗"};
                }
                if (this.bytMoveType == 2) {
                    break;
                }
                GameUI.getInstance().setTwoMenu(this.bytMenuState, stringArray, (byte) 5);
                break;
            }
            case 58: {
                if ((PackageObject) Param.getInstance().hPackage.get(new Integer(this.getPropRectMove())) != null && this.bytMoveType == 1) {
                    stringArray = new String[]{"使        用", "装备丢弃", "返        回"};
                } else if ((PackageObject) Param.getInstance().hPetPackEquip.get(new Integer(this.getPropRectMove())) != null && this.bytMoveType == 0) {
                    stringArray = new String[]{"卸        下", "装备比较", "返        回"};
                }
                GameUI.getInstance().setTwoMenu(this.bytMenuState, stringArray, (byte) 5);
                break;
            }
            case -37: {
                if (this.shtOneMenuMove == Param.getInstance().bytPetSaleType) {
                    break;
                }
                Param.getInstance().bytPetSaleType = (byte) this.shtOneMenuMove;
                NetSend.getInstance().sendPetHandle_Set();
                this.setButton(1, 100, 2);
                break;
            }
            case -72: {
                switch (this.personalShopState) {
                    case 0: {
                        if (Param.getInstance().hShopPackage == null || !Param.getInstance().hShopPackage.containsKey(new Integer(this.personalShopSellPackage.getCellBoxIndex()))) {
                            break;
                        }
                        GameUI.getInstance().setTwoMenu(this.bytMenuState, new String[]{"购买物品", "查看属性"}, (byte) 5);
                        break;
                    }
                    case 2: {
                        if (Param.getInstance().hPackage == null || !Param.getInstance().hPackage.containsKey(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()))) {
                            break;
                        }
                        GameUI.getInstance().setTwoMenu(this.bytMenuState, new String[]{"查看属性"}, (byte) 5);
                    }
                }
                break;
            }
            case -35: {
                this.updatePersonalShop((byte) 5);
                break;
            }
            case -11: {
                if (Param.getInstance().personalPackage == null || !Param.getInstance().personalPackage.getFocal()) {
                    break;
                }
                if (this.bytButtonType[1] == 22) {
                    Hashtable hashtable = (Hashtable) Param.getInstance().hMallPackage.get(new Integer(this.tabStyleInstance.getTabCurrentIndex()));
                    Param.getInstance().intMallNum = 1;
                    int n = ((PackageObject) hashtable.get((Object) new Integer((int) Param.getInstance().personalPackage.getCellBoxIndex()))).intPaiMaiPrice;
                    if (Macro.FEE_POINT >= n) {
                        this.mallShop();
                        break;
                    }
                    DCanvas.getInstance().addInformation("点数不足");
                    break;
                }
                stringArray = new String[]{"购买单个", "购买多个"};
                GameUI.getInstance().setTwoMenu(this.bytMenuState, stringArray, (byte) 5);
                break;
            }
            case -10: {
                DCanvas.getInstance().setNetLoad(true);
                NetSend.getInstance().sendShop_List(this.bytsHelpNumber[this.getOneMove()]);
                Param.getInstance().bytShopType = this.bytsHelpNumber[this.getOneMove()];
                this.bytTaskTempMove = (byte) this.shtOneMenuMove;
                this.bytTaskTempRoll = (byte) this.shtOneMenuRoll;
                this.setState((byte) -11, this.strOneMenuOption[this.getOneMove()]);
                break;
            }
            case 36: {
                if (Param.getInstance().bytPropBagType == 0) {
                    int n = 0;
                    if (Param.getInstance().packageBoxMaxNum <= 40) {
                        n = 1;
                    }
                    if (Param.getInstance().hPackage != null && !Param.getInstance().hPackage.isEmpty()) {
                        if ((PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex())) != null) {
                            stringArray = new String[5 + n];
                            stringArray[0] = "查看属性";
                            stringArray[1] = "使　　用";
                            stringArray[2] = "设快捷键";
                            stringArray[3] = "移　　动";
                            stringArray[4] = "丢　　弃";
                            stringArray[5] = "返　　回";
                        } else {
                            stringArray = new String[1 + n];
                            stringArray[0] = "整　　理";
                        }
                    } else if (n == 1) {
                        stringArray = new String[1];
                    }
                } else {
                    stringArray = new String[]{"添加物品", "查看详情"};
                }
                if (this.bytMoveType == 2) {
                    break;
                }
                GameUI.getInstance().setTwoMenu(this.bytMenuState, stringArray, (byte) 5);
                break;
            }
            case 37:
            case 38: {
                if (Param.getInstance().bytPropBagType == 0) {
                    int n = 0;
                    if (Param.getInstance().packageBoxMaxNum <= 40) {
                        n = 1;
                    }
                    if (Param.getInstance().hPackage != null && !Param.getInstance().hPackage.isEmpty()) {
                        if ((PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex())) != null) {
                            stringArray = new String[4 + n];
                            stringArray[0] = "查看属性";
                            stringArray[1] = "使　　用";
                            stringArray[2] = "移　　动";
                            stringArray[3] = "丢　　弃";
                            stringArray[4] = "返　　回";
                        } else {
                            stringArray = new String[1 + n];
                            stringArray[0] = "整　　理";
                        }
                    } else if (n == 1) {
                        stringArray = new String[1];
                    }
                } else {
                    stringArray = new String[]{"添加物品", "查看详情"};
                }
                if (this.bytMoveType == 2) {
                    break;
                }
                GameUI.getInstance().setTwoMenu(this.bytMenuState, stringArray, (byte) 5);
                break;
            }
            case 39: {
                if (this.ChooseJewel) {
                    Param.getInstance();
                    if (Param.ASKING_ONCE) {
                        byte by = (byte) Param.getInstance().InlayIndex;
                        int n = ((PackageObject) Param.getInstance().hPackage.get((Object) new Integer((int) Param.getInstance().personalPackage.getCellBoxIndex()))).intId;
                        byte by2 = MenuUI.getInstance().inlayHole.getCellBoxIndex();
                        DCanvas.getInstance().setNetLoad(true);
                        NetSend.getInstance().sendStrengthen(by, (byte) 3, n, by2);
                        break;
                    }
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendTimeAaking();
                    return;
                }
                if (Param.getInstance().bytPropBagType == 0) {
                    int n = 0;
                    if (Param.getInstance().packageBoxMaxNum <= 40) {
                        n = 1;
                    }
                    if (Param.getInstance().hPackage != null && !Param.getInstance().hPackage.isEmpty()) {
                        PackageObject packageObject = (PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()));
                        if (packageObject != null) {
                            if (packageObject.blnUse) {
                                stringArray = new String[5 + n];
                                stringArray[0] = "查看属性";
                                stringArray[1] = "使　　用";
                                stringArray[2] = "设快捷键";
                                stringArray[3] = "移　　动";
                                stringArray[4] = "丢　　弃";
                                stringArray[5] = "返　　回";
                            } else {
                                stringArray = new String[4 + n];
                                stringArray[0] = "查看属性";
                                stringArray[1] = "设快捷键";
                                stringArray[2] = "移　　动";
                                stringArray[3] = "丢　　弃";
                                stringArray[4] = "返　　回";
                            }
                        } else {
                            stringArray = new String[1 + n];
                            stringArray[0] = "整　　理";
                        }
                    } else if (n == 1) {
                        stringArray = new String[1];
                    }
                } else {
                    stringArray = new String[]{"添加物品", "查看详情"};
                }
                if (this.bytMoveType == 2) {
                    break;
                }
                GameUI.getInstance().setTwoMenu(this.bytMenuState, stringArray, (byte) 5);
                break;
            }
            case -36: {
                if (Param.getInstance().vCommonList != null) {
                    MailObject mailObject = (MailObject) Param.getInstance().vCommonList.elementAt(this.getOneMove());
                    stringArray = mailObject.bytType == 0 ? new String[]{"查　　看", "写  邮   件", "删　　除"} : new String[]{"查　　看", "写  邮   件", "回　　复", "删　　除"};
                } else {
                    stringArray = new String[]{"写  邮   件"};
                }
                GameUI.getInstance().setTwoMenu(this.bytMenuState, stringArray, (byte) 5);
                break;
            }
            case 31: {
                byte by = (byte) Param.getInstance().vMenuMemory.size();
                if (Param.getInstance().hPackage != null && (PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex())) != null) {
                    if (this.strOneTitlestr.indexOf("存") != -1 || this.strOneTitlestr.indexOf("") != -1 || this.strOneTitlestr.indexOf("售") != -1 || ((PackageObject) Param.getInstance().hPackage.get((Object) new Integer((int) Param.getInstance().personalPackage.getCellBoxIndex()))).bytIsBind == 0) {
                        stringArray = ((MenuObject) Param.getInstance().vMenuMemory.elementAt((int) (by - 1))).strOption;
                        GameUI.getInstance().setTwoMenu(this.bytMenuState, stringArray, (byte) 5);
                    } else {
                        DCanvas.getInstance().addInformation("该物品无法交易");
                    }
                }
                if (this.bytMailSubtabIndex != 3) {
                    break;
                }
                stringArray = new String[]{"邮       寄"};
                GameUI.getInstance().setTwoMenu(this.bytMenuState, stringArray, (byte) 5);
                break;
            }
            case 11: {
                if (StrengthenState == 1) {
                    if (this.ErgodicArray(this.inlayHole.getCellBoxIndex())) {
                        this.bytTagMove = (byte) 4;
                        this.ChooseJewel = true;
                        Param.getInstance().HoleIndex = this.inlayHole.getCellBoxIndex();
                        this.setState((byte) 39, "特殊物品");
                        break;
                    }
                    DCanvas.getInstance().addInformation("仅能对已开启且为空的孔位进行镶嵌，请重新选择");
                    break;
                }
                if (StrengthenState != 2) {
                    break;
                }
                if (this.ErgodicArray(this.inlayHole.getCellBoxIndex())) {
                    byte by = (byte) Param.getInstance().InlayIndex;
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendStrengthen(by, (byte) 2, 0, this.inlayHole.getCellBoxIndex());
                    break;
                }
                DCanvas.getInstance().addInformation("仅能对已镶嵌宝石的孔位进行剥离！");
                break;
            }
            case -44: {
                if (this.bytButtonType[1] != 4) {
                    break;
                }
                GameUI.getInstance().setTwoMenu(this.bytMenuState, ((MenuObject) Param.getInstance().vMenuMemory.elementAt((int) (Param.getInstance().vMenuMemory.size() - 1))).strOption, (byte) 5);
                break;
            }
            case -16: {
                NetManager.message = null;
                String string = ((String[]) Param.getInstance().MALL_SHOW_LIST.elementAt(MenuUI.getInstance().getOneMove()))[4];
                String string2 = "0";
                int n = 0;
                if (string != null && !string.equals("")) {
                    n = Integer.parseInt(string);
                }
                if (Param.chinaMobileUserID != null) {
                    string2 = Param.chinaMobileUserID;
                }
                DCanvas.getInstance().setNetLoad(true);
                if (Param.getInstance().MALL_FOR_NAME != null && !Param.getInstance().MALL_FOR_NAME.equals("")) {
                    NetSend.getInstance().sendMallOtherSerial(n, Param.getInstance().MALL_FOR_NAME, Macro.VERSION, string2, 1);
                } else {
                    NetSend.getInstance().sendMallSerial(n, Macro.VERSION, string2, 1);
                }
                Param.getInstance().MALL_FOR_NAME = null;
                break;
            }
            case -18: {
                FormDes.getInstance().showForm((byte) -19);
                break;
            }
            case -20: {
                this.setState((byte) -22, "商城充值");
                String string = ((String[]) Param.getInstance().MALL_SHOW_LIST.elementAt(MenuUI.getInstance().getOneMove()))[0];
                DCanvas.getInstance().setNetLoad(true);
                NetSend.getInstance().sendMallRecharge(Byte.parseByte(string), Param.getInstance().MALL_SHANGCHENG_SERIAL[0], Param.getInstance().MALL_SHANGCHENG_SERIAL[2], Integer.parseInt(Param.getInstance().MALL_SHANGCHENG_SERIAL[1]), Integer.parseInt(Param.getInstance().MALL_SHANGCHENG_SERIAL[2]), Param.getInstance().MALL_CARD_NUMBER, Param.getInstance().MALL_PASSWORD_NUMBER);
                break;
            }
            case -24: {
                GameControl.getInstance().delUIbase(3);
                GameControl.getInstance().CreateState((byte) 4);
                SystemMenuUI.getInstance().setState((byte) -19);
                break;
            }
            case -22: {
                GameControl.getInstance().delUIbase(9);
                GameControl.getInstance().delUIbase(3);
                GameControl.getInstance().delUIbase(4);
                NetParse.getInstance().clearTwoRect();
                break;
            }
            case -15: {
                Param.getInstance();
                String[] stringArray2 = (String[]) Param.MALL_EXTEND_LIST.elementAt(MenuUI.getInstance().getOneMove());
                byte by = Byte.parseByte(stringArray2[1]);
                DCanvas.getInstance().setNetLoad(true);
                NetSend.getInstance().sendUpdatePacket((byte) 0, by);
                break;
            }
            case 61: {
                PackageObject packageObject;
                if (this.bytTitleMove == 0 || this.bytTitleMove == 1) {
                    Vector vector = Param.getInstance().vSkillOccupationBefore;
                    if (this.bytTitleMove == 1) {
                        vector = Param.getInstance().vSkillOccupationLater;
                    }
                    if (this.isDrawSkillinfo) {
                        if (ORPMe.ME.skillPoints < ((SkillObject) vector.elementAt((int) MenuUI.getInstance().getOneMove())).bytNeedPoints) {
                            DCanvas.getInstance().addInformation("所需技能点不足");
                        }
                        if (ORPMe.Gold < ((SkillObject) vector.elementAt((int) MenuUI.getInstance().getOneMove())).intNeedMoney) {
                            DCanvas.getInstance().addInformation("所需金币不足");
                            break;
                        }
                        GameControl.getInstance().CreateState((byte) 7);
                        DialogUI.getInstance().setDialog((byte) 19, "升级此技能需要消耗" + ((SkillObject) vector.elementAt((int) MenuUI.getInstance().getOneMove())).bytNeedPoints + "点技能点," + "消耗" + ((SkillObject) vector.elementAt((int) MenuUI.getInstance().getOneMove())).intNeedMoney + "金币," + "您确认要升级吗?", (byte) 2);
                        break;
                    }
                    if (this.isCheckSkillinfo) {
                        if (vector == null || vector.isEmpty()) {
                            break;
                        }
                        if (((SkillObject) vector.elementAt((int) this.getOneMove())).blnPassivity || ((SkillObject) vector.elementAt((int) this.getOneMove())).bytLv <= 0) {
                            GameUI.getInstance().setTwoMenu(this.bytMenuState, new String[]{"技能升级", "洗　　点"}, (byte) 5);
                            break;
                        }
                        GameUI.getInstance().setTwoMenu(this.bytMenuState, new String[]{"技能升级", "设快捷键", "洗　　点"}, (byte) 5);
                        break;
                    }
                    if (vector == null || vector.isEmpty()) {
                        break;
                    }
                    if (((SkillObject) vector.elementAt((int) this.getOneMove())).blnPassivity || ((SkillObject) vector.elementAt((int) this.getOneMove())).bytLv <= 0) {
                        GameUI.getInstance().setTwoMenu(this.bytMenuState, new String[]{"查　　看", "技能升级", "洗　　点"}, (byte) 5);
                        break;
                    }
                    GameUI.getInstance().setTwoMenu(this.bytMenuState, new String[]{"查　　看", "技能升级", "设快捷键", "洗　　点"}, (byte) 5);
                    break;
                }
                if (this.bytTitleMove != 2 || this.isDrawSkillinfo || Param.getInstance().vSkillBook == null || Param.getInstance().vSkillBook.isEmpty() || (packageObject = (PackageObject) Param.getInstance().vSkillBook.elementAt(this.getOneMove())) == null) {
                    break;
                }
                if (packageObject.intId == 0) {
                    GameUI.getInstance().setTwoMenu(this.bytMenuState, new String[]{"镶嵌天书"}, (byte) 5);
                    break;
                }
                GameUI.getInstance().setTwoMenu(this.bytMenuState, new String[]{"镶嵌天书", "查　　看"}, (byte) 5);
                break;
            }
            case 63: {
                if (this.bytButtonType[1] == 23) {
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendSearchTaskList();
                    break;
                }
                if (this.bytButtonType[1] == 20) {
                    if (GameUI.getInstance().blnFight) {
                        DCanvas.getInstance().addInformation("战斗中无法传送");
                        break;
                    }
                    int n = ((TaskObject) this.vTempVessel.elementAt((int) this.getOneMove())).intId;
                    DCanvas.getInstance().setNetLoad(true);
                    NetParse.getInstance().setInitLoadingPre();
                    NetSend.getInstance().sendTaskCarry_oknpc((byte) 3, n);
                    NetParse.getInstance().setInitLoading();
                    break;
                }
                stringArray = ((TaskObject) Param.getInstance().vTaskList.elementAt((int) this.getOneMove())).bytIsProp == 1 ? new String[]{"查　　看", "放　　弃"} : new String[]{"查　　看", "放　　弃"};
                if (((TaskObject) Param.getInstance().vTaskList.elementAt((int) this.getOneMove())).bnlOverType) {
                    String[] stringArray3 = stringArray;
                    stringArray = new String[stringArray3.length + 1];
                    System.arraycopy(stringArray3, 0, stringArray, 0, stringArray3.length);
                    stringArray[stringArray3.length] = "传　　送";
                }
                GameUI.getInstance().setTwoMenu(this.bytMenuState, stringArray, (byte) 5);
                break;
            }
            case 64: {
                if (byteSkill == 0) {
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendSearchTaskList();
                }
                if (byteSkill != 1) {
                    break;
                }
                byte by = this.getPageType();
                Vector vector = new Vector();
                vector.addElement("使　　用");
                vector.addElement("物品属性");
                vector.addElement("返　　回");
                if (by == 2) {
                    vector.addElement("下　　页");
                } else if (by == 3) {
                    vector.addElement("上　　页");
                } else if (by == 4) {
                    vector.addElement("上　　页");
                    vector.addElement("下　　页");
                }
                stringArray = new String[vector.size()];
                int n = 0;
                while (n < vector.size()) {
                    stringArray[n] = (String) vector.elementAt(n);
                    n = (short) (n + 1);
                }
                GameUI.getInstance().setTwoMenu(this.bytMenuState, stringArray, (byte) 5);
                break;
            }
            case 65: {
                this.setState((byte) 64, "生活技能");
                break;
            }
            case 34: {
                if (Param.getInstance().vCommonList == null) {
                    break;
                }
                TeamObject teamObject = (TeamObject) Param.getInstance().vCommonList.elementAt(this.getOneMove());
                boolean bl = teamObject.strName.equals(ORPMe.ME.strNickName);
                if (ORPMe.ME.bytOfficialRank == 3 || ORPMe.ME.bytOfficialRank == 2 || ORPMe.ME.bytOfficialRank == 1) {
                    Vector vector = new Vector();
                    if (bl) {
                        vector.addElement("添加成员");
                        vector.addElement("帮派管理");
                        vector.addElement("帮派信息");
                        if (ORPMe.ME.bytOfficialRank == 2 || ORPMe.ME.bytOfficialRank == 1) {
                            vector.addElement("退出帮派");
                        }
                        stringArray = new String[vector.size()];
                        int n = 0;
                        while (n < vector.size()) {
                            stringArray[n] = (String) vector.elementAt(n);
                            n = (short) (n + 1);
                        }
                    } else {
                        vector.addElement("查　　看");
                        vector.addElement("添加成员");
                        if (teamObject.blnIsOnLine) {
                            vector.addElement("私　　聊");
                            vector.addElement("邀请组队");
                        }
                        vector.addElement("加为好友");
                        vector.addElement("加为黑名");
                        vector.addElement("帮派管理");
                        vector.addElement("帮派信息");
                        if (ORPMe.ME.bytOfficialRank != 3) {
                            vector.addElement("退出帮派");
                        }
                        stringArray = new String[vector.size()];
                        int n = 0;
                        while (n < vector.size()) {
                            stringArray[n] = (String) vector.elementAt(n);
                            n = (short) (n + 1);
                        }
                    }
                }
                GameUI.getInstance().setTwoMenu(this.bytMenuState, stringArray, (byte) 5);
                break;
            }
            case -51: {
                if (Param.getInstance().Second_List == null) {
                    break;
                }
                stringArray = new String[Param.getInstance().Second_List.size()];
                int n = 0;
                while (n < stringArray.length) {
                    stringArray[n] = ((String[]) Param.getInstance().Second_List.elementAt(n))[1];
                    ++n;
                }
                GameUI.getInstance().setTwoMenu(this.bytMenuState, stringArray, (byte) 5);
                break;
            }
            case -50: {
                if (Param.getInstance().PARTNER_relation == 0) {
                    Vector vector = new Vector();
                    vector.addElement("设置恋人");
                    vector.addElement("结婚说明");
                    vector.addElement("返　　回");
                    stringArray = new String[vector.size()];
                    int n = 0;
                    while (n < vector.size()) {
                        stringArray[n] = (String) vector.elementAt(n);
                        n = (short) (n + 1);
                    }
                } else if (Param.getInstance().partner.PARTNER_relation == 1) {
                    Vector vector = new Vector();
                    vector.addElement("私　　聊");
                    vector.addElement("分　　手");
                    vector.addElement("邀请组队");
                    vector.addElement("返　　回");
                    stringArray = new String[vector.size()];
                    int n = 0;
                    while (n < vector.size()) {
                        stringArray[n] = (String) vector.elementAt(n);
                        n = (short) (n + 1);
                    }
                } else if (Param.getInstance().partner.PARTNER_relation == 2) {
                    Vector vector = new Vector();
                    vector.addElement("私　　聊");
                    vector.addElement("传　　送");
                    vector.addElement("邀请组队");
                    vector.addElement("强制离婚");
                    stringArray = new String[vector.size()];
                    int n = 0;
                    while (n < vector.size()) {
                        stringArray[n] = (String) vector.elementAt(n);
                        n = (short) (n + 1);
                    }
                }
                GameUI.getInstance().setTwoMenu(this.bytMenuState, stringArray, (byte) 5);
                break;
            }
            case -48: {
                if (this.vTempVessel != null) {
                    TeamObject teamObject;
                    short s;
                    byte by = this.getPageType();
                    Vector vector = new Vector();
                    if (this.intRelation == 0) {
                        if (Param.bytChatSubMasterNumCount[this.intRelation] == 0) {
                            return;
                        }
                        s = this.getMasterPlayerMove((byte) this.intRelation);
                        if (this.intRelation >= 0) {
                            teamObject = (TeamObject) this.vTempVessel.elementAt(s);
                            vector.addElement("查　　看");
                            if (teamObject.blnIsOnLine) {
                                vector.addElement("私　　聊");
                                vector.addElement("邀请组队");
                            }
                            vector.addElement("脱        离");
                            vector.addElement("返　　回");
                        }
                    } else if (this.intRelation == 1 && (s = this.getMasterPlayerMove((byte) this.intRelation)) >= 0) {
                        teamObject = (TeamObject) this.vTempVessel.elementAt(s);
                        if (ORPMe.ME.bytApprentice == 0) {
                            vector.addElement("查　　看");
                            if (teamObject.blnIsOnLine) {
                                vector.addElement("私　　聊");
                                vector.addElement("邀请组队");
                            }
                            vector.addElement("遗        弃");
                            vector.addElement("返　　回");
                        } else if (ORPMe.ME.bytApprentice == 1) {
                            vector.addElement("查　　看");
                            if (teamObject.blnIsOnLine) {
                                vector.addElement("私　　聊");
                                vector.addElement("邀请组队");
                            }
                            vector.addElement("返　　回");
                        }
                    }
                    if (by == 2) {
                        vector.addElement("下　　页");
                    } else if (by == 3) {
                        vector.addElement("上　　页");
                    } else if (by == 4) {
                        vector.addElement("上　　页");
                        vector.addElement("下　　页");
                    }
                    stringArray = new String[vector.size()];
                    s = 0;
                    while (s < vector.size()) {
                        stringArray[s] = (String) vector.elementAt(s);
                        s = (short) (s + 1);
                    }
                }
                GameUI.getInstance().setTwoMenu(this.bytMenuState, stringArray, (byte) 5);
                break;
            }
            case -31: {
                if (Param.getInstance().vCommonList != null) {
                    byte by = this.getPageType();
                    TeamObject teamObject = (TeamObject) Param.getInstance().vCommonList.elementAt(this.getOneMove());
                    Vector vector = new Vector();
                    vector.addElement("查　　看");
                    vector.addElement("添加好友");
                    if (teamObject.blnIsOnLine) {
                        vector.addElement("私　　聊");
                        vector.addElement("邀请组队");
                    }
                    vector.addElement("发送邮件");
                    vector.addElement("删　　除");
                    if (by == 2) {
                        vector.addElement("下　　页");
                    } else if (by == 3) {
                        vector.addElement("上　　页");
                    } else if (by == 4) {
                        vector.addElement("上　　页");
                        vector.addElement("下　　页");
                    }
                    stringArray = new String[vector.size()];
                    int n = 0;
                    while (n < vector.size()) {
                        stringArray[n] = (String) vector.elementAt(n);
                        n = (short) (n + 1);
                    }
                } else {
                    stringArray = new String[]{"添加好友"};
                }
                GameUI.getInstance().setTwoMenu(this.bytMenuState, stringArray, (byte) 5);
                break;
            }
            case -33:
            case -32: {
                if (Param.getInstance().vCommonList != null) {
                    byte by = this.getPageType();
                    TeamObject teamObject = (TeamObject) Param.getInstance().vCommonList.elementAt(this.getOneMove());
                    Vector vector = new Vector();
                    if (this.bytMenuState == -32) {
                        vector.addElement("添加黑名");
                    } else {
                        vector.addElement("添加仇人");
                    }
                    vector.addElement("删　　除");
                    if (by == 2) {
                        vector.addElement("下　　页");
                    } else if (by == 3) {
                        vector.addElement("上　　页");
                    } else if (by == 4) {
                        vector.addElement("上　　页");
                        vector.addElement("下　　页");
                    }
                    stringArray = new String[vector.size()];
                    int n = 0;
                    while (n < vector.size()) {
                        stringArray[n] = (String) vector.elementAt(n);
                        n = (short) (n + 1);
                    }
                } else {
                    stringArray = this.bytMenuState == -32 ? new String[]{"添加黑名"} : new String[]{"添加仇人"};
                }
                GameUI.getInstance().setTwoMenu(this.bytMenuState, stringArray, (byte) 5);
                break;
            }
            case 35: {
                byte by;
                if (ORPMe.ME.bytTeamRank == 0) {
                    stringArray = new String[]{"邀请组队"};
                } else if (ORPMe.ME.bytTeamRank == 1) {
                    byte by3 = this.getTeamPlayerMove(this.bytChatTeam);
                    if (by3 >= 0) {
                        TeamObject teamObject = (TeamObject) Param.getInstance().vTeam.elementAt(by3);
                        stringArray = teamObject.strName.equals(ORPMe.ME.strNickName) || !teamObject.blnIsOnLine ? new String[]{"离开队伍"} : new String[]{"查　　看", "离开队伍"};
                    }
                } else if (ORPMe.ME.bytTeamRank == 2) {
                    byte by4 = this.getTeamPlayerMove(this.bytChatTeam);
                    if (by4 >= 0) {
                        TeamObject teamObject = (TeamObject) Param.getInstance().vTeam.elementAt(by4);
                        stringArray = teamObject.strName.equals(ORPMe.ME.strNickName) ? new String[]{"邀请组队", "离开队伍"} : (teamObject.bytTroopRank == 3 || teamObject.bytTroopRank == 2 ? (!teamObject.blnIsOnLine ? new String[]{"队员位置", "离开队伍"} : new String[]{"查　　看", "加为好友"}) : (!teamObject.blnIsOnLine ? new String[]{"队员位置", "删除队员", "离开队伍"} : new String[]{"查　　看", "私　　聊", "加为好友", "删除队员"}));
                    }
                } else if (ORPMe.ME.bytTeamRank == 3 && (by = this.getTeamPlayerMove(this.bytChatTeam)) >= 0) {
                    TeamObject teamObject = (TeamObject) Param.getInstance().vTeam.elementAt(by);
                    stringArray = teamObject.strName.equals(ORPMe.ME.strNickName) ? new String[]{"邀请组队", "队员位置", "离开队伍"} : (teamObject.bytTroopRank == 2 ? (!teamObject.blnIsOnLine ? new String[]{"取消助手", "队员位置", "删除队员"} : new String[]{"查　　看", "私　　聊", "加为好友", "队员位置", "取消助手", "转让队长", "删除队员"}) : (!teamObject.blnIsOnLine ? new String[]{"任命助手", "队员位置", "删除队员", "离开队伍"} : new String[]{"查　　看", "私　　聊", "加为好友", "队员位置", "任命助手", "转让队长", "删除队员"}));
                }
                GameUI.getInstance().setTwoMenu(this.bytMenuState, stringArray, (byte) 5);
                break;
            }
            case -38: {
                if (Param.getInstance().vCommonList == null) {
                    return;
                }
                GameUI gameUI = GameUI.getInstance();
                String[] stringArray4 = new String[4];
                stringArray4[0] = "查　　看";
                GameUI.getInstance().getClass();
                stringArray4[1] = "加为好友";
                stringArray4[2] = "邀请组队";
                stringArray4[3] = "添加成员";
                gameUI.setTwoMenu(this.bytMenuState, stringArray4, (byte) 5);
                break;
            }
            case 60: {
                byte by = (byte) (this.setData[0] - 1);
                byte by5 = (byte) (this.setData[1] - 1);
                byte by6 = (byte) (this.setData[2] - 1);
                byte by7 = (byte) (this.setData[3] - 1);
                byte by8 = (byte) (this.setData[4] - 1);
                byte by9 = (byte) (this.setData[5] - 1);
                byte by10 = (byte) (this.setData[6] - 1);
                Rms.saveSet(by, by5, by6, by7, by8, by9, by10);
                this.setBackSystem();
                Param.getInstance().IntRgbColor = null;
                Param.getInstance().shtSettingData = null;
                break;
            }
            case 42: {
                if (this.shtOneMenuMove == -1) {
                    Param.getInstance().getClass();
                    if (Param.getInstance().bytChatNoteType == 8) {
                        if (Param.getInstance().vChatFightNote != null) {
                            stringArray = new String[]{"清除信息"};
                        }
                    } else {
                        Param.getInstance().getClass();
                        if (Param.getInstance().bytChatNoteType == 7) {
                            if (Param.getInstance().vSystemNote != null) {
                                stringArray = new String[]{"清除信息"};
                            }
                        } else {
                            Param.getInstance().getClass();
                            if (Param.getInstance().bytChatNoteType == 0) {
                                if (Param.getInstance().vChatNote != null) {
                                    stringArray = new String[]{"清除信息"};
                                }
                            } else {
                                Param.getInstance().getClass();
                                if (Param.getInstance().bytChatNoteType == 4) {
                                    if (ORPMe.ME.strConsortia.length() > 0) {
                                        stringArray = new String[]{"频道发言"};
                                    }
                                } else {
                                    Param.getInstance().getClass();
                                    if (Param.getInstance().bytChatNoteType == 5) {
                                        if (ORPMe.ME.bytTeamRank > 0) {
                                            stringArray = new String[]{"频道发言"};
                                        }
                                    } else {
                                        stringArray = new String[]{"频道发言"};
                                    }
                                }
                            }
                        }
                    }
                    if (stringArray == null) {
                        break;
                    }
                    GameUI.getInstance().setTwoMenu(this.bytMenuState, stringArray, (byte) 5);
                    break;
                }
                Param.getInstance().getClass();
                if (Param.getInstance().bytChatNoteType == 0) {
                    String[] stringArray5;
                    if (Param.getInstance().vChatNote == null || Param.getInstance().vChatNote != null && ((ChatContent) ((Vector) Param.getInstance().vChatNote.elementAt((int) this.getOneMove())).elementAt((int) 0)).name.equals(ORPMe.ME.strNickName)) {
                        stringArray = new String[]{"频道发言", "清除信息"};
                    } else if (((ChatContent) ((Vector) Param.getInstance().vChatNote.elementAt((int) this.getOneMove())).elementAt((int) 0)).channel == 11) {
                        stringArray = new String[]{"回          复", "聊　　天"};
                    } else {
                        String[] stringArray6 = new String[9];
                        stringArray6[0] = "私　　聊";
                        stringArray6[1] = "频道发言";
                        stringArray6[2] = "查　　看";
                        GameUI.getInstance().getClass();
                        stringArray6[3] = "加为好友";
                        stringArray6[4] = "邀请组队";
                        stringArray6[5] = "发送邮件";
                        stringArray6[6] = "加为黑名";
                        stringArray6[7] = "加为仇人";
                        stringArray6[8] = "清除信息";
                        stringArray = stringArray6;
                        if (ORPMe.ME.bytOfficialRank >= 2) {
                            stringArray5 = stringArray;
                            stringArray = new String[stringArray5.length + 1];
                            System.arraycopy(stringArray5, 0, stringArray, 0, stringArray5.length);
                            stringArray[stringArray5.length] = "添加成员";
                        }
                    }
                    if (Param.getInstance().vChatNote != null && ((ChatContent) ((Vector) Param.getInstance().vChatNote.elementAt((int) this.getOneMove())).elementAt((int) 0)).strsContent != null) {
                        stringArray5 = stringArray;
                        stringArray = new String[stringArray5.length + 1];
                        System.arraycopy(stringArray5, 0, stringArray, 0, stringArray5.length);
                        stringArray[stringArray5.length] = "查看物品";
                    }
                } else {
                    Param.getInstance().getClass();
                    if (Param.getInstance().bytChatNoteType == 2) {
                        String[] stringArray7;
                        if (Param.getInstance().vChatRaceNote == null || Param.getInstance().vChatRaceNote != null && ((ChatContent) ((Vector) Param.getInstance().vChatRaceNote.elementAt((int) this.getOneMove())).elementAt((int) 0)).name.equals(ORPMe.ME.strNickName)) {
                            stringArray = new String[]{"频道发言", "清除信息"};
                        } else if (((ChatContent) ((Vector) Param.getInstance().vChatRaceNote.elementAt((int) this.getOneMove())).elementAt((int) 0)).channel == 11) {
                            stringArray = new String[]{"回          复", "聊　　天"};
                        } else {
                            String[] stringArray8 = new String[9];
                            stringArray8[0] = "私　　聊";
                            stringArray8[1] = "频道发言";
                            stringArray8[2] = "查　　看";
                            GameUI.getInstance().getClass();
                            stringArray8[3] = "加为好友";
                            stringArray8[4] = "邀请组队";
                            stringArray8[5] = "发送邮件";
                            stringArray8[6] = "加为黑名";
                            stringArray8[7] = "加为仇人";
                            stringArray8[8] = "清除信息";
                            stringArray = stringArray8;
                            if (ORPMe.ME.bytOfficialRank >= 2) {
                                stringArray7 = stringArray;
                                stringArray = new String[stringArray7.length + 1];
                                System.arraycopy(stringArray7, 0, stringArray, 0, stringArray7.length);
                                stringArray[stringArray7.length] = "添加成员";
                            }
                        }
                        if (Param.getInstance().vChatRaceNote != null && ((ChatContent) ((Vector) Param.getInstance().vChatRaceNote.elementAt((int) this.getOneMove())).elementAt((int) 0)).strsContent != null) {
                            stringArray7 = stringArray;
                            stringArray = new String[stringArray7.length + 1];
                            System.arraycopy(stringArray7, 0, stringArray, 0, stringArray7.length);
                            stringArray[stringArray7.length] = "查看物品";
                        }
                    } else {
                        Param.getInstance().getClass();
                        if (Param.getInstance().bytChatNoteType == 6) {
                            String[] stringArray9;
                            if (Param.getInstance().vChatSingleNote == null || Param.getInstance().vChatSingleNote != null && ((ChatContent) ((Vector) Param.getInstance().vChatSingleNote.elementAt((int) this.getOneMove())).elementAt((int) 0)).name.equals(ORPMe.ME.strNickName)) {
                                stringArray = new String[]{"频道发言"};
                            } else if (((ChatContent) ((Vector) Param.getInstance().vChatSingleNote.elementAt((int) this.getOneMove())).elementAt((int) 0)).channel == 11) {
                                stringArray = new String[]{"回          复", "聊　　天"};
                            } else {
                                String[] stringArray10 = new String[6];
                                stringArray10[0] = "查　　看";
                                stringArray10[1] = "频道发言";
                                stringArray10[2] = "私　　聊";
                                GameUI.getInstance().getClass();
                                stringArray10[3] = "加为好友";
                                stringArray10[4] = "邀请组队";
                                stringArray10[5] = "加为黑名";
                                stringArray = stringArray10;
                                if (ORPMe.ME.bytOfficialRank >= 2) {
                                    stringArray9 = stringArray;
                                    stringArray = new String[stringArray9.length + 1];
                                    System.arraycopy(stringArray9, 0, stringArray, 0, stringArray9.length);
                                    stringArray[stringArray9.length] = "添加成员";
                                }
                            }
                            if (Param.getInstance().vChatSingleNote != null && ((ChatContent) ((Vector) Param.getInstance().vChatSingleNote.elementAt((int) this.getOneMove())).elementAt((int) 0)).strsContent != null) {
                                stringArray9 = stringArray;
                                stringArray = new String[stringArray9.length + 1];
                                System.arraycopy(stringArray9, 0, stringArray, 0, stringArray9.length);
                                stringArray[stringArray9.length] = "查看物品";
                            }
                        } else {
                            Param.getInstance().getClass();
                            if (Param.getInstance().bytChatNoteType == 4) {
                                String[] stringArray11;
                                if (Param.getInstance().vChatConsortianNote == null || Param.getInstance().vChatConsortianNote != null && ((ChatContent) ((Vector) Param.getInstance().vChatConsortianNote.elementAt((int) this.getOneMove())).elementAt((int) 0)).name.equals(ORPMe.ME.strNickName)) {
                                    stringArray = new String[]{"清除信息"};
                                } else {
                                    String[] stringArray12 = new String[8];
                                    stringArray12[0] = "私　　聊";
                                    stringArray12[1] = "查　　看";
                                    GameUI.getInstance().getClass();
                                    stringArray12[2] = "加为好友";
                                    stringArray12[3] = "邀请组队";
                                    stringArray12[4] = "发送邮件";
                                    stringArray12[5] = "加为黑名";
                                    stringArray12[6] = "加为仇人";
                                    stringArray12[7] = "清除信息";
                                    stringArray = stringArray12;
                                    if (ORPMe.ME.bytOfficialRank > 0) {
                                        stringArray11 = stringArray;
                                        stringArray = new String[stringArray11.length + 1];
                                        System.arraycopy(stringArray11, 0, stringArray, 0, stringArray11.length);
                                        stringArray[stringArray11.length] = "频道发言";
                                    }
                                }
                                if (Param.getInstance().vChatConsortianNote != null && ((ChatContent) ((Vector) Param.getInstance().vChatConsortianNote.elementAt((int) this.getOneMove())).elementAt((int) 0)).strsContent != null) {
                                    stringArray11 = stringArray;
                                    stringArray = new String[stringArray11.length + 1];
                                    System.arraycopy(stringArray11, 0, stringArray, 0, stringArray11.length);
                                    stringArray[stringArray11.length] = "查看物品";
                                }
                            } else {
                                Param.getInstance().getClass();
                                if (Param.getInstance().bytChatNoteType == 5) {
                                    String[] stringArray13;
                                    if (Param.getInstance().vChatTeamNote == null || Param.getInstance().vChatTeamNote != null && ((ChatContent) ((Vector) Param.getInstance().vChatTeamNote.elementAt((int) this.getOneMove())).elementAt((int) 0)).name.equals(ORPMe.ME.strNickName)) {
                                        stringArray = new String[]{"清除信息"};
                                    } else {
                                        String[] stringArray14 = new String[7];
                                        stringArray14[0] = "私　　聊";
                                        stringArray14[1] = "查　　看";
                                        GameUI.getInstance().getClass();
                                        stringArray14[2] = "加为好友";
                                        stringArray14[3] = "发送邮件";
                                        stringArray14[4] = "加为黑名";
                                        stringArray14[5] = "加为仇人";
                                        stringArray14[6] = "清除信息";
                                        stringArray = stringArray14;
                                        if (ORPMe.ME.bytTeamRank > 0) {
                                            stringArray13 = stringArray;
                                            stringArray = new String[stringArray13.length + 1];
                                            System.arraycopy(stringArray13, 0, stringArray, 0, stringArray13.length);
                                            stringArray[stringArray13.length] = "频道发言";
                                        }
                                    }
                                    if (Param.getInstance().vChatTeamNote != null && ((ChatContent) ((Vector) Param.getInstance().vChatTeamNote.elementAt((int) this.getOneMove())).elementAt((int) 0)).strsContent != null) {
                                        stringArray13 = stringArray;
                                        stringArray = new String[stringArray13.length + 1];
                                        System.arraycopy(stringArray13, 0, stringArray, 0, stringArray13.length);
                                        stringArray[stringArray13.length] = "查看物品";
                                    }
                                } else {
                                    Param.getInstance().getClass();
                                    if (Param.getInstance().bytChatNoteType == 1) {
                                        String[] stringArray15;
                                        if (Param.getInstance().vChatWordNote == null || Param.getInstance().vChatWordNote != null && ((ChatContent) ((Vector) Param.getInstance().vChatWordNote.elementAt((int) this.getOneMove())).elementAt((int) 0)).name.equals(ORPMe.ME.strNickName)) {
                                            stringArray = new String[]{"频道发言", "清除信息"};
                                        } else if (((ChatContent) ((Vector) Param.getInstance().vChatWordNote.elementAt((int) this.getOneMove())).elementAt((int) 0)).channel == 11) {
                                            stringArray = new String[]{"回          复", "聊　　天"};
                                        } else {
                                            String[] stringArray16 = new String[9];
                                            stringArray16[0] = "私　　聊";
                                            stringArray16[1] = "频道发言";
                                            stringArray16[2] = "查　　看";
                                            GameUI.getInstance().getClass();
                                            stringArray16[3] = "加为好友";
                                            stringArray16[4] = "邀请组队";
                                            stringArray16[5] = "发送邮件";
                                            stringArray16[6] = "加为黑名";
                                            stringArray16[7] = "加为仇人";
                                            stringArray16[8] = "清除信息";
                                            stringArray = stringArray16;
                                            if (ORPMe.ME.bytOfficialRank >= 2) {
                                                stringArray15 = stringArray;
                                                stringArray = new String[stringArray15.length + 1];
                                                System.arraycopy(stringArray15, 0, stringArray, 0, stringArray15.length);
                                                stringArray[stringArray15.length] = "添加成员";
                                            }
                                        }
                                        if (Param.getInstance().vChatWordNote != null && ((ChatContent) ((Vector) Param.getInstance().vChatWordNote.elementAt((int) this.getOneMove())).elementAt((int) 0)).strsContent != null) {
                                            stringArray15 = stringArray;
                                            stringArray = new String[stringArray15.length + 1];
                                            System.arraycopy(stringArray15, 0, stringArray, 0, stringArray15.length);
                                            stringArray[stringArray15.length] = "查看物品";
                                        }
                                    } else {
                                        Param.getInstance().getClass();
                                        if (Param.getInstance().bytChatNoteType == 3) {
                                            String[] stringArray17;
                                            if (Param.getInstance().vChatMapNote == null || Param.getInstance().vChatMapNote != null && ((ChatContent) ((Vector) Param.getInstance().vChatMapNote.elementAt((int) this.getOneMove())).elementAt((int) 0)).name.equals(ORPMe.ME.strNickName)) {
                                                stringArray = new String[]{"频道发言", "清除信息"};
                                            } else if (((ChatContent) ((Vector) Param.getInstance().vChatMapNote.elementAt((int) this.getOneMove())).elementAt((int) 0)).channel == 11) {
                                                stringArray = new String[]{"回          复", "聊　　天"};
                                            } else {
                                                String[] stringArray18 = new String[9];
                                                stringArray18[0] = "私　　聊";
                                                stringArray18[1] = "频道发言";
                                                stringArray18[2] = "查　　看";
                                                GameUI.getInstance().getClass();
                                                stringArray18[3] = "加为好友";
                                                stringArray18[4] = "邀请组队";
                                                stringArray18[5] = "发送邮件";
                                                stringArray18[6] = "加为黑名";
                                                stringArray18[7] = "加为仇人";
                                                stringArray18[8] = "清除信息";
                                                stringArray = stringArray18;
                                                if (ORPMe.ME.bytOfficialRank >= 2) {
                                                    stringArray17 = stringArray;
                                                    stringArray = new String[stringArray17.length + 1];
                                                    System.arraycopy(stringArray17, 0, stringArray, 0, stringArray17.length);
                                                    stringArray[stringArray17.length] = "添加成员";
                                                }
                                            }
                                            if (Param.getInstance().vChatMapNote != null && ((ChatContent) ((Vector) Param.getInstance().vChatMapNote.elementAt((int) this.getOneMove())).elementAt((int) 0)).strsContent != null) {
                                                stringArray17 = stringArray;
                                                stringArray = new String[stringArray17.length + 1];
                                                System.arraycopy(stringArray17, 0, stringArray, 0, stringArray17.length);
                                                stringArray[stringArray17.length] = "查看物品";
                                            }
                                        } else {
                                            Param.getInstance().getClass();
                                            if (Param.getInstance().bytChatNoteType == 8) {
                                                stringArray = new String[]{"清除信息"};
                                            } else {
                                                Param.getInstance().getClass();
                                                if (Param.getInstance().bytChatNoteType == 7) {
                                                    stringArray = new String[]{"清除信息"};
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                GameUI.getInstance().setTwoMenu(this.bytMenuState, stringArray, (byte) 5);
                break;
            }
            case 1: {
                if (!Param.getInstance().blnNpcOptionWork) {
                    return;
                }
                if (Param.getInstance().oSelectNpc != null) {
                    int n;
                    Param.getInstance().bytPopRectStep = 0;
                    byte by = (byte) Param.getInstance().vMenuMemory.size();
                    MenuObject menuObject = (MenuObject) Param.getInstance().vMenuMemory.elementAt(by - 1);
                    int n3 = n = by == 1 ? menuObject.intsOptionId[MenuUI.getInstance().getOneMove()] : menuObject.intOptionId;
                    if (n == 800001) {
                        FormDes.getInstance().bytTypeMail = (byte) 2;
                        FormDes.getInstance().showForm((byte) -42);
                        break;
                    }
                    NetSend.getInstance().sendNpcMoveOption((byte) this.shtOneMenuMove, (byte) this.shtOneMenuRoll, 0);
                    break;
                }
                switch (Param.getInstance().bSelectTask.bytTaskType) {
                    case 0: {
                        GameControl.getInstance().delUIbase(3);
                        NetSend.getInstance().sendUnlockEvent(Param.getInstance().bSelectTask.intUserId);
                        break;
                    }
                    case 1: {
                        GameControl.getInstance().delUIbase(3);
                        NetSend.getInstance().sendUnlockEvent2(Param.getInstance().bSelectTask.intUserId);
                    }
                }
                break;
            }
            case -80: {
                break;
            }
            case -127: {
                StringManager.cutPage = 0;
                if (this.bytTwoMenuState == 0) {
                    if (this.bytButtonType[1] == 12) {
                        this.sendTaskHandle((byte) 0, (byte) 0);
                        if (this.WordContent != null) {
                            this.WordContent = null;
                        }
                        GameControl.getInstance().delUIbase(3);
                        this._temp0 = null;
                        this._temp1 = null;
                        Param.getInstance().awardSelectPackage = null;
                        Param.getInstance().awardMustPackage = null;
                        break;
                    }
                    if (this.bytButtonType[1] == 13) {
                        byte by = 0;
                        if (Param.getInstance().awardSelectPackage != null) {
                            if (Param.getInstance().awardSelectPackage.getFocal() || this._temp0 == null) {
                                by = Param.getInstance().awardSelectPackage.getCellBoxIndex();
                                this.sendTaskHandle((byte) 1, by);
                                if (this.WordContent != null) {
                                    this.WordContent = null;
                                }
                                GameControl.getInstance().delUIbase(3);
                                this._temp0 = null;
                                this._temp1 = null;
                                Param.getInstance().awardSelectPackage = null;
                                Param.getInstance().awardMustPackage = null;
                                break;
                            }
                            DCanvas.getInstance().addInformation("请选择奖励物品");
                            break;
                        }
                        this.sendTaskHandle((byte) 1, by);
                        if (this.WordContent != null) {
                            this.WordContent = null;
                        }
                        GameControl.getInstance().delUIbase(3);
                        this._temp0 = null;
                        this._temp1 = null;
                        Param.getInstance().awardSelectPackage = null;
                        Param.getInstance().awardMustPackage = null;
                        break;
                    }
                    if (this.bytButtonType[1] == 0) {
                        if (this.WordContent != null) {
                            this.WordContent = null;
                        }
                        this.bytTwoMenuState = (byte) 3;
                        this.shtOneMenuMove = 0;
                        this.shtOneMenuRoll = 0;
                        this.addTaskImage((short) 0, false);
                        this.setTaskPrizeInfo();
                        break;
                    }
                    if (this.bytButtonType[1] == 20) {
                        if (GameUI.getInstance().blnFight) {
                            DCanvas.getInstance().addInformation("战斗中无法传送");
                            break;
                        }
                        Param.getInstance().blnTaskType = false;
                        int n = ((TaskObject) Param.getInstance().vCommonList.elementAt((int) 0)).intId;
                        int n4 = ((TaskObject) Param.getInstance().vCommonList.elementAt((int) 0)).intConditionId[this.shtOneMenuMove];
                        DCanvas.getInstance().setNetLoad(true);
                        NetParse.getInstance().setInitLoadingPre();
                        NetSend.getInstance().sendTaskCarry_target(n, n4);
                        NetParse.getInstance().setInitLoading();
                        break;
                    }
                    if (this.bytButtonType[1] != 4) {
                        break;
                    }
                    GameUI.getInstance().setTwoMenu(this.bytMenuState, new String[]{"接受任务"}, (byte) 5);
                    break;
                }
                if (this.bytTwoMenuState != 3 || this.bytButtonType[1] != 13) {
                    break;
                }
                this.sendTaskHandle((byte) 1, (byte) this.shtOneMenuMove);
                GameControl.getInstance().delUIbase(3);
                this._temp0 = null;
                this._temp1 = null;
                Param.getInstance().awardSelectPackage = null;
                Param.getInstance().awardMustPackage = null;
                break;
            }
            case -113:
            case -46: {
                GameUI.getInstance().setTwoMenu(this.bytMenuState, ((MenuObject) Param.getInstance().vMenuMemory.elementAt((int) (Param.getInstance().vMenuMemory.size() - 1))).strOption, (byte) 5);
                break;
            }
            case -47: {
                GameUI.getInstance().setTwoMenu(this.bytMenuState, this.getNextMenuStr(((MenuObject) Param.getInstance().vMenuMemory.elementAt((int) (Param.getInstance().vMenuMemory.size() - 1))).strOption, "01245"), (byte) 5);
                break;
            }
            case -5: {
                String[] stringArray19 = null;
                stringArray19 = Param.getInstance().blnCrystal ? new String[]{"查　　看"} : new String[]{"强　　化", "查　　看"};
                GameUI.getInstance().setTwoMenu(this.bytMenuState, stringArray19, (byte) 5);
                break;
            }
            case -7: {
                if (this.bytTitleMove != 3) {
                    break;
                }
                new FullInfo("人物属性", 9263661, this.bytMenuState);
                if (ORPMe.ME.strsBuff != null && !ORPMe.ME.strsBuff.equals("") && ORPMe.ME.intsBuff != null && ORPMe.ME.strsDeBuff != null && !ORPMe.ME.strsDeBuff.equals("") && ORPMe.ME.intsDeBuff != null) {
                    if (this.getOneMove() <= this.getstrsBuffLeng) {
                        FullInfo.getInstance().setFullRectMenu(ORPMe.ME.strsBuff[this.getOneMove()][1]);
                        break;
                    }
                    int n = this.getOneMove() - this.getstrsBuffLeng;
                    if (n > this.getstrsDeBuffLeng) {
                        break;
                    }
                    FullInfo.getInstance().setFullRectMenu(ORPMe.ME.strsDeBuff[this.getOneMove()][1]);
                    break;
                }
                if (ORPMe.ME.strsBuff != null && !ORPMe.ME.strsBuff.equals("") && ORPMe.ME.intsBuff != null) {
                    if (this.getOneMove() > this.getstrsBuffLeng) {
                        break;
                    }
                    FullInfo.getInstance().setFullRectMenu(ORPMe.ME.strsBuff[this.getOneMove()][1]);
                    break;
                }
                if (ORPMe.ME.strsDeBuff == null || ORPMe.ME.strsDeBuff.equals("") || ORPMe.ME.intsDeBuff == null || this.getOneMove() > this.getstrsDeBuffLeng) {
                    break;
                }
                FullInfo.getInstance().setFullRectMenu(ORPMe.ME.strsDeBuff[this.getOneMove()][1]);
                break;
            }
            case -6: {
                if (Param.getInstance().hOtherPackageEquip == null || !Param.getInstance().hOtherPackageEquip.containsKey(new Integer(this.rView.getViewCurrentIndex()))) {
                    break;
                }
                stringArray = new String[]{"查看属性"};
                GameUI.getInstance().setTwoMenu(this.bytMenuState, stringArray, (byte) 5);
                break;
            }
            case 10: {
                if (this.bytButtonType[1] != 4) {
                    break;
                }
                if (Param.getInstance().bytPropBagType == 0) {
                    if (this.bytMoveType == 1) {
                        stringArray = new String[]{"查看属性", "装　　备", "打　　孔", "镶　　嵌", "剥　　离", "移　　动", "丢　　弃", "返　　回"};
                    } else if (this.bytMoveType == 0) {
                        stringArray = new String[]{"查看属性", "卸　　下", "返　　回"};
                    }
                } else {
                    stringArray = new String[]{"添加物品", "查看详情"};
                }
                if (this.bytMoveType == 2) {
                    break;
                }
                GameUI.getInstance().setTwoMenu(this.bytMenuState, stringArray, (byte) 5);
            }
        }
    }

    public String[] getNextMenuStr(String[] stringArray, String string) {
        String[] stringArray2 = new String[string.length()];
        int[] nArray = new int[string.length()];
        int n = 0;
        while (n < string.length()) {
            nArray[n] = Integer.parseInt(string.substring(n, n + 1));
            stringArray2[n] = stringArray[nArray[n]];
            ++n;
        }
        return stringArray2;
    }

    /*
     * Unable to fully structure code
     */
    public void keyRightSoftEvent() {
        switch (this.bytMenuState) {
            case -26: {
                Param.getInstance().MALL_STR_RECORD = null;
                this.clearMoveKey();
                this.setBackSystem();
                break;
            }
            case -25: {
                Macro.blnStart = false;
                Macro.hNetList.clear();
                Macro.netKey = -1;
                Macro.tatalDefyTime = 0L;
                Macro.upTime = 0L;
                Macro.downTime = 0L;
                Macro.missSum = 0;
                DCanvas.getInstance().setNetLoad(false);
                this.clearMoveKey();
                this.setBackSystem();
                break;
            }
            case -91: {
                if (Param.getInstance().htAreaMapNpcList != null) {
                    Param.getInstance().htAreaMapNpcList.removeAllElements();
                }
                if (Param.getInstance().htAreaMapNpcIDList != null) {
                    Param.getInstance().htAreaMapNpcIDList.removeAllElements();
                }
            }
            case -82: {
                this.clearMoveKey();
                this.setBackSystem();
                break;
            }
            case -81: {
                this.setBackSystem();
                this.clearShortcut();
                break;
            }
            case -84: {
                this.setBackSystem();
                break;
            }
            case -49: {
                int n = 0;
                final String[] array = new String[10];
                for (int i = 0; i < this.strsFunction.length / 2; i = (byte) (i + 1)) {
                    if (this.strsFunction[i * 2].indexOf("未使用") == -1) {
                        array[n * 2] = this.strsFunction[i * 2];
                        array[n * 2 + 1] = this.strsFunction[i * 2 + 1];
                        n = (byte) (n + 1);
                    }
                }
                if (n != 0) {
                    Rms.strsMacroChat = new String[n * 2];
                    for (int j = 0; j < n; j = (byte) (j + 1)) {
                        Rms.strsMacroChat[j * 2] = array[j * 2].substring(2);
                        Rms.strsMacroChat[j * 2 + 1] = array[j * 2 + 1];
                    }
                } else {
                    Rms.strsMacroChat = null;
                }
                Rms.saveMacroChat();
                this.clearMacroChat();
                this.setBackSystem();
                break;
            }
            case 9: {
                GameControl.getInstance().delUIbase(9);
                break;
            }
            case -6: {
                if (GameUI.getInstance().vsColorInfo != null) {
                    GameUI.getInstance().vsColorInfo = null;
                    Param.getInstance().blnColorInfo = false;
                }
                this.setBackSystem();
                break;
            }
            case -5: {
                if (GameUI.getInstance().vsColorInfo != null) {
                    GameUI.getInstance().vsColorInfo = null;
                    Param.getInstance().blnColorInfo = false;
                }
                if (this.bytTwoMenuState != 0) {
                    this.bytTwoMenuState = 0;
                    this.setButton(1, 4, 2);
                    break;
                }
                if (Param.getInstance().vOptionPlace != null && !Param.getInstance().vOptionPlace.isEmpty()) {
                    Macro.PROPRECT_WIDTH = (byte) 8;
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendFrameEquip((byte) 1, (byte) 0, 0);
                    this.setRoleEquip();
                    Param.getInstance().packageBoxMaxNum = Param.getInstance().bytAccouterBag;
                    this.setState((byte) 10, "特殊道具");
                    break;
                }
                GameControl.getInstance().delUIbase(3);
                Param.getInstance().IntRgbColor = null;
                Param.getInstance().hImgObject = null;
                break;
            }
            case 21:
            case 22:
            case 33: {
                if (Pet.getInstance() != null) {
                    Pet.getInstance().clear();
                }
                this.blnTabPet = false;
                this.blnIsPetList = false;
                break;
            }
            case 39: {
                if (Param.getInstance().bytPropBagType == 0) {
                    if (!this.ChooseJewel) {
                        this.setBackSystem();
                        break;
                    }
                    this.setState((byte) 11, "镶嵌宝石");
                    this.ChooseJewel = false;
                    break;
                }
                GameControl.getInstance().delUIbase(this.bytDelState);
                FormDes.getInstance().showFormDes();
                Param.getInstance().bytPropBagType = 0;
                break;
            }
            case -1: {
                Macro.PET_PROP_CHANGE = 0;
                this.setState((byte) 33, "");
                this.setPetList();
                break;
            }
            case 58: {
                Macro.PET_PROP_CHANGE = 0;
                this.setState((byte) 33, "");
                this.setPetList();
                NetSend.getInstance().sendPetHandle((byte) 1, ORPlayer.itSelectPetId);
                break;
            }
            case -37: {
                this.setState((byte) 33, "宠　　物");
                this.clearPetSet();
                this.shtOneMenuMove = this.bytTaskTempMove;
                this.shtOneMenuRoll = this.bytTaskTempRoll;
                this.setPetList();
                break;
            }
            case -72:
            case -35: {
                this.updatePersonalShop((byte) 6);
                break;
            }
            case -24:
            case -22:
            case -20:
            case -19:
            case -18:
            case -16:
            case -15:
            case -11: {
                Param.getInstance().hPackage = null;
                Param.getInstance().hImgObject = null;
                Param.getInstance().personalPackage = null;
                this.intShopMoney = 0;
                this.shtOneMenuMove = this.bytTaskTempMove;
                this.shtOneMenuRoll = this.bytTaskTempRoll;
                Label_1262:
                {
                    if (this.bytMenuState == -11) {
                        GameUI.getInstance();
                        if (GameUI.blnNextChargeMenu) {
                            GameUI.getInstance().rebackToMenu();
                            break Label_1262;
                        }
                    }
                    this.setBackSystem();
                }
                if (this.bytMenuState == -16) {
                    Param.getInstance().MALL_FOR_NAME = "";
                    break;
                }
                break;
            }
            case 10:
            case 36:
            case 37:
            case 38: {
                if (this.bytMenuState == 10) {
                    Param.getInstance().HoleIndex = 0;
                    Param.getInstance().EquipIndex = (byte) -1;
                }
                if (GameUI.getInstance().vsColorInfo != null) {
                    GameUI.getInstance().vsColorInfo = null;
                    Param.getInstance().blnColorInfo = false;
                }
                if (Param.getInstance().bytPropBagType == 0) {
                    Param.getInstance().hImgObject = null;
                    this.setBackSystem();
                } else {
                    GameControl.getInstance().delUIbase(this.bytDelState);
                    FormDes.getInstance().showFormDes();
                    Param.getInstance().bytPropBagType = 0;
                }
                this.blnTab = false;
                break;
            }
            case -10: {
                this.bytsHelpNumber = null;
            }
            case -51:
            case -50:
            case -48:
            case -40:
            case -38:
            case -33:
            case -32:
            case -31:
            case -7:
            case 34:
            case 61:
            case 64: {
                if (!this.isDrawSkillinfo) {
                    if (GameUI.getInstance().vsColorInfo != null) {
                        GameUI.getInstance().vsColorInfo = null;
                        Param.getInstance().blnColorInfo = false;
                    }
                    Param.getInstance().bytNpcDataType = (byte) -1;
                    if (this.bytTwoMenuState != 0) {
                        this.bytTwoMenuState = 0;
                        this.setButton(1, 4, 2);
                    } else {
                        this.setBackSystem();
                    }
                }
                if (!this.isDrawSkillinfo) {
                    break;
                }
                this.isDrawSkillinfo = false;
                this.setButton(1, 4, 2);
                break;
            }
            case 65: {
                this.setState((byte) 64, "生活技能");
                break;
            }
            case 11: {
                this.ChooseJewel = false;
                Param.getInstance().HoleIndex = 0;
                this.bytTagMove = 0;
                Param.getInstance().RightDate = false;
                DCanvas.getInstance().setNetLoad(true);
                NetSend.getInstance().sendFrameEquip((byte) 1, (byte) 0, 0);
                this.setState((byte) 10, MenuUI.STR_PACK_LIST[0]);
                break;
            }
            case 35:
            case 42: {
                if (GameUI.getInstance().vsColorInfo != null) {
                    GameUI.getInstance().vsColorInfo = null;
                    Param.getInstance().blnColorInfo = false;
                }
                Param.getInstance().bytNpcDataType = (byte) -1;
                if (this.bytTwoMenuState != 0) {
                    this.bytTwoMenuState = 0;
                    this.setButton(1, 4, 2);
                } else {
                    this.setBackSystem();
                }
            }
            case -36: {
                if (this.bytTwoMenuState != 0) {
                    this.bytTwoMenuState = 0;
                    this.setButton(1, 4, 2);
                    break;
                }
                Label_1678:
                {
                    if (Param.getInstance().vCommonList != null) {
                        for (int k = 0; k < Param.getInstance().vCommonList.size(); k = (short) (k + 1)) {
                            if (!((MailObject) Param.getInstance().vCommonList.elementAt(k)).blnIsRead) {
                                break Label_1678;
                            }
                        }
                        GameUI.getInstance().delRoleStateIcon((short) 1);
                    }
                }
                this.setBackSystem();
                break;
            }
            case 31: {
                FormDes.getInstance().showForm((byte) -42);
                break;
            }
            case -34: {
                this.setBackSystem();
                Param.getInstance().vInfoContent = null;
                break;
            }
            case 63: {
                this.setBackSystem();
                this.cleanTitle();
                if (Param.getInstance().imgTaskThru == null) {
                    break;
                }
                Param.getInstance().imgTaskThru = null;
                break;
            }
            case -44: {
                if (this.bytTwoMenuState == 3) {
                    this.bytTwoMenuState = 0;
                    ((MakeObject) Param.getInstance().vCommonList.elementAt((int) this.getOneMove())).intMaterialColor = null;
                    ((MakeObject) Param.getInstance().vCommonList.elementAt((int) this.getOneMove())).shtMaterialNum = null;
                    ((MakeObject) Param.getInstance().vCommonList.elementAt((int) this.getOneMove())).shtNowNum = null;
                    ((MakeObject) Param.getInstance().vCommonList.elementAt((int) this.getOneMove())).strMaterialName = null;
                    ((MakeObject) Param.getInstance().vCommonList.elementAt((int) this.getOneMove())).shtMaterialNameIcon = null;
                    this.setButton(1, 4, 2);
                    break;
                }
                this.setBackSystem();
                break;
            }
            case 60: {
                this.setBackSystem();
                this.triangle = null;
                Param.getInstance().shtSettingData = null;
                Param.getInstance().IntRgbColor = null;
                break;
            }
            case 43: {
                FormDes.getInstance().showFormDes();
                DCanvas.getInstance().buttonRightFlash = 0;
                break;
            }
            case 1: {
                Param.getInstance().blnNpcOptionWork = true;
                if (Param.getInstance().vMenuMemory == null || (Param.getInstance().vMenuMemory != null && Param.getInstance().vMenuMemory.size() == 1)) {
                    Param.getInstance().vMenuMemory = null;
                    GameControl.getInstance().delUIbase(3);
                    return;
                }
                final MenuObject menuObject = (MenuObject) Param.getInstance().vMenuMemory.elementAt((byte) Param.getInstance().vMenuMemory.size() - 1);
                final byte bytType = menuObject.bytType;
                final int intUserId = Param.getInstance().oSelectNpc.intUserId;
                if (bytType == 14 && intUserId > 0) {
                    final byte b = 3;
                    final int intOptionId = menuObject.intOptionId;
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendNpcOneOption(intUserId, b, intOptionId);
                    break;
                }
                this.setBackOff();
                break;
            }
            case -83:
            case -80: {
                this.setBackSystem();
                break;
            }
            case -127: {
                if (Param.getInstance().blnTaskType) {
                    this.setState((byte) 63, "任务");
                    this.shtOneMenuMove = this.bytTaskTempMove;
                    this.shtOneMenuRoll = this.bytTaskTempRoll;
                    Param.getInstance().vCommonList = null;
                    Param.getInstance().blnTaskType = false;
                    this.clearTaskInfo();
                    break;
                }
                if (this.bytTwoMenuState == 3) {
                    this.setTaskInfo((short) 0);
                    this.bytTwoMenuState = 0;
                    break;
                }
                this.clearTaskInfo();
                this.setBackOff();
                StringManager.cutPage = 0;
                Param.popupDialogInstance.setShow(false);
                break;
            }
            case -113:
            case -47:
            case -46: {
                this.setBackOff();
                break;
            }
            case -43: {
                Param.popupDialogInstance.setShow(false);
                Param.getInstance().IndexNpcShopBarTabStyle = 0;
                Param.getInstance().npcBarSelectIndex = 0;
                DCanvas.getInstance().setNetLoad(false);
                this.setBackOff();
                this.tabStyleInstance.cleanTabStyle();
                Param.getInstance().hNpcStorage = null;
                Param.getInstance().hNpcOursPackTable = null;
                Param.getInstance().npcShopOursTabArray = null;
                Param.getInstance().npcShopOursNumPack = null;
                Param.getInstance().npcShopBarPackage = null;
                Param.getInstance().personalPackage = null;
                break;
            }
            case -39: {
                Param.popupDialogInstance.setShow(false);
                Param.getInstance().IndexNpcShopBarTabStyle = 0;
                Param.getInstance().npcBarSelectIndex = 0;
                DCanvas.getInstance().setNetLoad(false);
                this.tabStyleInstance.cleanTabStyle();
                Param.getInstance().npcShopBarTabStyle.cleanTabStyle();
                Param.getInstance().hNpcStorage = null;
                Param.getInstance().personalPackage = null;
                Param.getInstance().npcShopBarPackage = null;
                this.setBackOff();
                this.setBackOff();
            }
        }
    }

    private void keyUpEvent() {
        DCanvas.getInstance().bytSpoolrFlash = 1;
        switch (this.bytMenuState) {
            case -26:
            case -25: {
                this.scrolltext.keyUP();
                break;
            }
            case -72:
            case -35: {
                this.updatePersonalShop((byte) 1);
                break;
            }
            case -81: {
                if (this.tabStyleInstance.TabFocuse || !this.Shortcut.getFocal()) {
                    break;
                }
                if (this.Shortcut.getLineIndex() == 0) {
                    this.Shortcut.setFocal(false);
                    this.tabStyleInstance.TabFocuse = true;
                    break;
                }
                this.Shortcut.getKeyUp();
                break;
            }
            case -11: {
                if (this.tabStyleInstance.TabFocuse || !Param.getInstance().personalPackage.getFocal()) {
                    break;
                }
                if (Param.getInstance().personalPackage.getLineIndex() == 0) {
                    Param.getInstance().personalPackage.setFocal(false);
                    this.tabStyleInstance.TabFocuse = true;
                    this.setSelectedButton(null, 0);
                    break;
                }
                Param.getInstance().personalPackage.getKeyUp();
                this.setSelectedButton((Hashtable) Param.getInstance().hMallPackage.get(new Integer(this.tabStyleInstance.getTabCurrentIndex())), Param.getInstance().personalPackage.getCellBoxIndex());
                MenuUI.getInstance().setInfoContent((Hashtable) Param.getInstance().hMallPackage.get(new Integer(this.tabStyleInstance.getTabCurrentIndex())), Param.getInstance().personalPackage.getCellBoxIndex());
                break;
            }
            case -70:
            case 40:
            case 41:
            case 44: {
                this.logicPropKey((byte) 1);
                break;
            }
            case -43: {
                if (Param.getInstance().npcShopBarPackage.getFocal()) {
                    if (Param.getInstance().npcShopBarPackage.getLineIndex() == 0) {
                        this.setNpcBusinessButton((byte) -1);
                        break;
                    }
                    Param.getInstance().npcShopBarPackage.getKeyUp();
                    this.setNpcStorageButton((byte) 10);
                    break;
                }
                if (this.tabStyleInstance.TabFocuse) {
                    this.tabStyleInstance.TabFocuse = false;
                    Param.getInstance().npcShopBarPackage.setFocal(true);
                    Param.getInstance().npcShopBarPackage.setBoxIndex((byte) 0, true);
                    this.setNpcStorageButton((byte) 10);
                    Param.popupDialogInstance.setShow(true);
                    break;
                }
                if (!Param.getInstance().personalPackage.getFocal()) {
                    break;
                }
                if (Param.getInstance().personalPackage.getLineIndex() == 0) {
                    Param.getInstance().personalPackage.setFocal(false);
                    this.tabStyleInstance.TabFocuse = true;
                    this.setNpcStorageSelect();
                    this.setNpcStorageButton((byte) -1);
                    break;
                }
                Param.getInstance().personalPackage.getKeyUp();
                this.setNpcStorageButton(Macro.NPC_STEP_SELECT);
                break;
            }
            case -39: {
                if (Param.getInstance().npcShopBarTabStyle.TabFocuse) {
                    break;
                }
                if (Param.getInstance().npcShopBarPackage.getFocal()) {
                    if (Param.getInstance().npcShopBarPackage.getLineIndex() == 0) {
                        Param.getInstance().npcShopBarPackage.setFocal(false);
                        Param.getInstance().npcShopBarTabStyle.TabFocuse = true;
                        this.setNpcBusinessButton((byte) -1);
                        break;
                    }
                    Param.getInstance().npcShopBarPackage.getKeyUp();
                    this.setNpcBusinessButton((byte) 2);
                    break;
                }
                if (this.tabStyleInstance.TabFocuse) {
                    this.tabStyleInstance.TabFocuse = false;
                    Param.getInstance().npcShopBarPackage.setFocal(true);
                    Param.getInstance().npcShopBarPackage.setBoxIndex((byte) 0, true);
                    this.setNpcBusinessButton((byte) 2);
                    Param.popupDialogInstance.setShow(true);
                    break;
                }
                if (!Param.getInstance().personalPackage.getFocal()) {
                    break;
                }
                if (Param.getInstance().personalPackage.getLineIndex() == 0) {
                    Param.getInstance().personalPackage.setFocal(false);
                    this.tabStyleInstance.TabFocuse = true;
                    this.setNpcBusinessButton((byte) -1);
                    break;
                }
                Param.getInstance().personalPackage.getKeyUp();
                this.setNpcBusinessButton((byte) 3);
                break;
            }
            case 9: {
                if (this.shtOneMenuMove <= 0) {
                    if (this.bytMoveType > 0) {
                        this.bytMoveType = (byte) (this.bytMoveType - 1);
                    }
                    this.shtOneMenuMove = 0;
                }
                if (this.shtOneMenuRoll == 0 && this.shtOneMenuMove == 0) {
                    this.bytTagMove = 0;
                    this.blnCursor = false;
                    this.shtOneMenuMove = 0;
                }
                if (this.bytMoveType == 2) {
                    this.logicPropKey((byte) 1);
                    break;
                }
                if (this.bytMoveType == 1) {
                    this.myDealBoxMove = (byte) -1;
                    this.bytTagMove_Deal();
                    break;
                }
                this.myDealBoxMove = 0;
                break;
            }
            case 21:
            case 22:
            case 33:
            case 36:
            case 37:
            case 38:
            case 39: {
                if (this.tabStyleInstance.TabFocuse && !this.ChooseJewel) {
                    this.tabStyleInstance.TabFocuse = false;
                    this.bytMoveType = 0;
                    this.rView.setViewFocal(true);
                    if (this.blnTabPet) {
                        this.setSelectedButton(ORPMe.ME.hPackagePet, this.rView.getViewCurrentIndex());
                        break;
                    }
                    this.setState((byte) 10, STR_PACK_LIST[0]);
                    this.setSelectedButton(Param.getInstance().hPackageEquip, this.rView.getViewCurrentIndex());
                    break;
                }
                if (Param.getInstance().personalPackage.getFocal()) {
                    if (Param.getInstance().personalPackage.getLineIndex() == 0 && !this.ChooseJewel) {
                        Param.getInstance().personalPackage.setFocal(false);
                        this.tabStyleInstance.TabFocuse = true;
                        this.bytMoveType = (byte) 2;
                        this.setSelectedButton(null, 0);
                        break;
                    }
                    Param.getInstance().personalPackage.getKeyUp();
                    this.setSelectedButton(this.bytMenuState == -35 || this.bytMenuState == -72 ? Param.getInstance().hShopPackage : Param.getInstance().hPackage, Param.getInstance().personalPackage.getCellBoxIndex());
                    break;
                }
                if (!this.rView.getViewFocal()) {
                    break;
                }
                this.rView.getViewCurrentKeyUp();
                this.setSelectedButton(Param.getInstance().hPackageEquip, this.rView.getViewCurrentIndex());
                break;
            }
            case 11: {
                if (this.inlayHole.getLineIndex() <= 0) {
                    break;
                }
                this.inlayHole.getKeyUp();
                this.inlayHole.setPopupShow();
                break;
            }
            case 58: {
                if (this.bytMoveType == 1) {
                    if (this.shtOneMenuRoll == 0 && this.shtOneMenuMove == 0) {
                        this.bytPetMove = 0;
                        this.bytMoveType = (byte) 2;
                        this.setInfoContent(Param.getInstance().hPetPackEquip, this.bytPetMove);
                        break;
                    }
                    this.logicPropKey((byte) 1);
                    break;
                }
                if (this.bytPetMove > 0 && this.bytPetMove < 4) {
                    this.bytPetMove = (byte) (this.bytPetMove - 1);
                }
                if (this.bytPetMove == 4) {
                    return;
                }
                this.setInfoContent(Param.getInstance().hPetPackEquip, this.bytPetMove);
                break;
            }
            case -5: {
                this.setRoleSee(1);
                break;
            }
            case -6: {
                this.rView.getViewCurrentKeyUp();
                break;
            }
            case 10: {
                if (this.bytTwoMenuState == 0) {
                    if (this.tabStyleInstance.TabFocuse) {
                        this.tabStyleInstance.TabFocuse = false;
                        this.bytMoveType = 0;
                        this.rView.setViewFocal(true);
                        this.setSelectedButton(Param.getInstance().hPackageEquip, this.rView.getViewCurrentIndex());
                        break;
                    }
                    if (Param.getInstance().personalPackage.getFocal()) {
                        if (Param.getInstance().personalPackage.getLineIndex() == 0) {
                            Param.getInstance().personalPackage.setFocal(false);
                            this.tabStyleInstance.TabFocuse = true;
                            this.bytMoveType = (byte) 2;
                            this.setSelectedButton(null, 0);
                            break;
                        }
                        Param.getInstance().personalPackage.getKeyUp();
                        this.setSelectedButton(this.bytMenuState == -35 || this.bytMenuState == -72 ? Param.getInstance().hShopPackage : Param.getInstance().hPackage, Param.getInstance().personalPackage.getCellBoxIndex());
                        break;
                    }
                    if (!this.rView.getViewFocal()) {
                        break;
                    }
                    this.rView.getViewCurrentKeyUp();
                    this.setSelectedButton(Param.getInstance().hPackageEquip, this.rView.getViewCurrentIndex());
                    break;
                }
                if (this.bytTwoMenuState != 1) {
                    break;
                }
                this.setUpDown((byte) 1);
                break;
            }
            case 65: {
                this.logicPropKey((byte) 1);
                this.setInfoContent(Param.getInstance().hPackage, this.getPropRectMove());
                break;
            }
            case -82: {
                this.setUpDown((byte) 1);
                this.setWordMove();
                break;
            }
            case 61: {
                if (this.isDrawSkillinfo) {
                    break;
                }
                this.setUpDown((byte) 1);
                break;
            }
            case -49:
            case 42: {
                this.shtWordMoveY = 0;
            }
            case -113:
            case -91:
            case -51:
            case -48:
            case -46:
            case -38:
            case -36:
            case -34:
            case -33:
            case -32:
            case -16:
            case -15:
            case 34:
            case 35:
            case 63:
            case 64: {
                this.setUpDown((byte) 1);
                break;
            }
            case -19: {
                if (Param.getInstance().mallCardSelect - 1 < 0) {
                    break;
                }
                --Param.getInstance().mallCardSelect;
                break;
            }
            case -50:
            case -7: {
                if (this.bytTitleMove == 3) {
                    this.setUpDown((byte) 1);
                    break;
                }
                this.scrolltext.keyUP();
                break;
            }
            case -37: {
                this.setUpDown((byte) 1);
                this.setPetSet();
                break;
            }
            case -47: {
                this.setUpDown((byte) 1);
                this.setParcelPostButton(this.getOneMove());
                break;
            }
            case -83:
            case -80:
            case -10: {
                if (this.shtOneMenuMove > 0) {
                    this.shtOneMenuMove = (short) (this.shtOneMenuMove - 1);
                    break;
                }
                if (this.shtOneMenuRoll <= 0) {
                    break;
                }
                this.shtOneMenuRoll = (short) (this.shtOneMenuRoll - 1);
                break;
            }
            case -44: {
                this.setUpDown((byte) 1);
                break;
            }
            case 60: {
                if (this.bytSetWordMove > Pram.STR_SET_SWITCH.length - 1 || this.bytSetWordMove <= 0) {
                    break;
                }
                this.bytSetWordMove = (byte) (this.bytSetWordMove - 1);
                if (this.bytSetBoxY <= 0) {
                    this.bytSetWordY = (byte) (this.bytSetWordY - 1);
                    if (this.bytSetWordY <= 0) {
                        this.bytSetWordY = 0;
                    }
                }
                if (this.bytSetWordMove >= Pram.STR_SET_SWITCH.length - 1 || this.bytSetBoxY <= 0) {
                    break;
                }
                this.bytSetBoxY = (byte) (this.bytSetBoxY - 1);
                break;
            }
            case 1: {
                if (!Param.getInstance().blnNpcOptionWork || this.strOneMenuOption == null) {
                    return;
                }
                if (this.shtOneMenuMove > 0) {
                    this.shtOneMenuMove = (short) (this.shtOneMenuMove - 1);
                } else if (this.shtOneMenuRoll > 0) {
                    this.shtOneMenuRoll = (short) (this.shtOneMenuRoll - 1);
                }
                this.initWordRoll(this.strOneMenuOption[this.getOneMove()], (short) (Macro.SCREEN_WIDTH - 38));
                break;
            }
            case -127: {
                if (this.comperH >= this.boxH) {
                    this.socrollBoxY += this.packageH;
                    if (this.socrollBoxY < this.boxDownY) {
                        break;
                    }
                    this.socrollBoxY = this.boxDownY;
                    if (Param.getInstance().awardSelectPackage != null && Param.getInstance().awardSelectPackage.getFocal()) {
                        if (Param.getInstance().awardMustPackage == null) {
                            break;
                        }
                        Param.getInstance().awardSelectPackage.setFocal(false);
                        Param.getInstance().awardMustPackage.setFocal(true);
                        break;
                    }
                    if (Param.getInstance().awardMustPackage == null || !Param.getInstance().awardMustPackage.getFocal()) {
                        break;
                    }
                    Param.getInstance().awardMustPackage.setFocal(false);
                    StringManager.blnPageSeleted = true;
                    break;
                }
                if (Param.getInstance().awardSelectPackage != null && Param.getInstance().awardSelectPackage.getFocal()) {
                    if (Param.getInstance().awardMustPackage != null) {
                        Param.getInstance().awardSelectPackage.setFocal(false);
                        Param.getInstance().awardMustPackage.setFocal(true);
                        break;
                    }
                    Param.getInstance().awardSelectPackage.setFocal(false);
                    Param.popupDialogInstance.setShow(false);
                    StringManager.blnPageSeleted = true;
                    break;
                }
                if (Param.getInstance().awardMustPackage == null) {
                    break;
                }
                if (Param.getInstance().awardMustPackage.getFocal()) {
                    Param.getInstance().awardMustPackage.setFocal(false);
                    StringManager.blnPageSeleted = true;
                    break;
                }
                Param.getInstance().awardMustPackage.setFocal(true);
                StringManager.blnPageSeleted = false;
                break;
            }
            case 31: {
                if (this.bytMailSubtabIndex == 3) {
                    break;
                }
                if (this.bytMailMove == 1) {
                    if (Param.getInstance().personalPackage.getLineIndex() > 0) {
                        Param.getInstance().personalPackage.getKeyUp();
                    } else {
                        Param.getInstance().personalPackage.setFocal(false);
                        this.tabStyleInstance.TabFocuse = true;
                        this.bytMailMove = 0;
                    }
                }
                this.setInfoContent(Param.getInstance().hPackage, Param.getInstance().personalPackage.getCellBoxIndex());
                if (!this.tabStyleInstance.TabFocuse) {
                    break;
                }
                this.setButton(1, 100, 2);
            }
        }
    }

    private void keyDwonEvent() {
        DCanvas.getInstance().bytSpoolrFlash = (byte) 2;
        switch (this.bytMenuState) {
            case -26:
            case -25: {
                this.scrolltext.keyDown();
                break;
            }
            case -72:
            case -35: {
                this.updatePersonalShop((byte) 2);
                break;
            }
            case -19: {
                if (Param.getInstance().mallCardSelect + 1 > 1) {
                    break;
                }
                ++Param.getInstance().mallCardSelect;
                break;
            }
            case -81: {
                if (this.tabStyleInstance.TabFocuse) {
                    this.Shortcut.setFocal(true);
                    this.tabStyleInstance.TabFocuse = false;
                    break;
                }
                if (!this.Shortcut.getFocal() || this.Shortcut.getLineIndex() == this.Shortcut.getLineNum() - 1) {
                    break;
                }
                this.Shortcut.getKeyDown();
                break;
            }
            case -11: {
                if (this.tabStyleInstance.TabFocuse) {
                    Param.getInstance().personalPackage.setFocal(true);
                    this.tabStyleInstance.TabFocuse = false;
                } else if (Param.getInstance().personalPackage.getFocal() && Param.getInstance().personalPackage.getLineIndex() != Param.getInstance().personalPackage.getLineNum() - 1) {
                    Param.getInstance().personalPackage.getKeyDown();
                }
                this.setSelectedButton((Hashtable) Param.getInstance().hMallPackage.get(new Integer(this.tabStyleInstance.getTabCurrentIndex())), Param.getInstance().personalPackage.getCellBoxIndex());
                MenuUI.getInstance().setInfoContent((Hashtable) Param.getInstance().hMallPackage.get(new Integer(this.tabStyleInstance.getTabCurrentIndex())), Param.getInstance().personalPackage.getCellBoxIndex());
                break;
            }
            case -70: {
                this.logicPropKey((byte) 2);
                break;
            }
            case -43: {
                if (Param.getInstance().npcShopBarPackage.getFocal()) {
                    if (Param.getInstance().npcShopBarPackage.getLineIndex() == Param.getInstance().npcShopBarPackage.getLineNum() - 1) {
                        Param.getInstance().npcShopBarPackage.setFocal(false);
                        this.tabStyleInstance.TabFocuse = true;
                        this.setNpcBusinessButton((byte) -1);
                    } else {
                        Param.getInstance().npcShopBarPackage.getKeyDown();
                    }
                } else if (this.tabStyleInstance.TabFocuse) {
                    this.tabStyleInstance.TabFocuse = false;
                    Param.getInstance().personalPackage.setFocal(true);
                    Param.getInstance().personalPackage.setBoxIndex((byte) 0, true);
                } else if (Param.getInstance().personalPackage.getFocal()) {
                    Param.getInstance().personalPackage.getKeyDown();
                }
                this.setNpcStorageSelect();
                this.setNpcStorageButton(Macro.NPC_STEP_SELECT);
                break;
            }
            case -39: {
                if (Param.getInstance().npcShopBarTabStyle.TabFocuse) {
                    Param.getInstance().npcShopBarTabStyle.TabFocuse = false;
                    Param.getInstance().npcShopBarPackage.setFocal(true);
                    Param.getInstance().npcShopBarPackage.setBoxIndex((byte) 0, true);
                    this.setNpcBusinessButton((byte) 2);
                    break;
                }
                if (Param.getInstance().npcShopBarPackage.getFocal()) {
                    if (Param.getInstance().npcShopBarPackage.getLineIndex() == Param.getInstance().npcShopBarPackage.getLineNum() - 1) {
                        Param.getInstance().npcShopBarPackage.setFocal(false);
                        this.tabStyleInstance.TabFocuse = true;
                        this.setNpcBusinessButton((byte) -1);
                        break;
                    }
                    Param.getInstance().npcShopBarPackage.getKeyDown();
                    this.setNpcBusinessButton((byte) 2);
                    break;
                }
                if (this.tabStyleInstance.TabFocuse) {
                    this.tabStyleInstance.TabFocuse = false;
                    Param.getInstance().personalPackage.setFocal(true);
                    Param.getInstance().personalPackage.setBoxIndex((byte) 0, true);
                    this.setNpcBusinessButton((byte) 3);
                    break;
                }
                if (!Param.getInstance().personalPackage.getFocal()) {
                    break;
                }
                Param.getInstance().personalPackage.getKeyDown();
                this.setNpcBusinessButton((byte) 3);
                break;
            }
            case 9: {
                if (this.bytMoveType != 2) {
                    this.bytMoveType = (byte) (this.bytMoveType + 1);
                    this.shtOneMenuMove = (short) -1;
                }
                if (this.bytMoveType == 2) {
                    this.blnCursor = true;
                    this.logicPropKey((byte) 2);
                    break;
                }
                if (this.bytMoveType == 1) {
                    this.myDealBoxMove = (byte) -1;
                    break;
                }
                this.myDealBoxMove = 0;
                this.blnCursor = false;
                break;
            }
            case 21:
            case 22:
            case 33:
            case 36:
            case 37:
            case 38:
            case 39: {
                if (this.tabStyleInstance.TabFocuse) {
                    Param.getInstance().personalPackage.setFocal(true);
                    this.bytMoveType = 1;
                    this.tabStyleInstance.TabFocuse = false;
                    this.setSelectedButton(this.bytMenuState == -35 || this.bytMenuState == -72 ? Param.getInstance().hShopPackage : Param.getInstance().hPackage, Param.getInstance().personalPackage.getCellBoxIndex());
                    break;
                }
                if (Param.getInstance().personalPackage.getFocal()) {
                    if (Param.getInstance().personalPackage.getLineIndex() == Param.getInstance().personalPackage.getLineNum() - 1) {
                        break;
                    }
                    Param.getInstance().personalPackage.getKeyDown();
                    this.setSelectedButton(this.bytMenuState == -35 || this.bytMenuState == -72 ? Param.getInstance().hShopPackage : Param.getInstance().hPackage, Param.getInstance().personalPackage.getCellBoxIndex());
                    break;
                }
                if (!this.rView.getViewFocal()) {
                    break;
                }
                byte by = this.rView.getViewCurrentIndex();
                this.rView.getViewCurrentKeyDown();
                if (by == this.rView.getViewCurrentIndex()) {
                    this.tabStyleInstance.TabFocuse = true;
                    this.rView.setViewFocal(false);
                    this.bytMoveType = (byte) 2;
                    this.setSelectedButton(null, 0);
                    break;
                }
                this.setSelectedButton(Param.getInstance().hPackageEquip, this.rView.getViewCurrentIndex());
                break;
            }
            case 58: {
                if (this.bytMoveType == 1) {
                    this.logicPropKey((byte) 2);
                    break;
                }
                this.bytPropMove = 0;
                this.shtOneMenuMove = 0;
                this.shtOneMenuRoll = 0;
                this.bytMoveType = 1;
                this.setInfoContent(Param.getInstance().hPackage, this.getPropRectMove());
                break;
            }
            case 11: {
                if (this.inlayHole.getLineIndex() >= this.inlayHole.getLineNum() - 1) {
                    break;
                }
                this.inlayHole.getKeyDown();
                this.inlayHole.setPopupShow();
                break;
            }
            case -5: {
                this.setRoleSee(2);
                break;
            }
            case -6: {
                this.rView.getViewCurrentKeyDown();
                break;
            }
            case 10: {
                if (this.tabStyleInstance.TabFocuse) {
                    Param.getInstance().personalPackage.setFocal(true);
                    this.bytMoveType = 1;
                    this.tabStyleInstance.TabFocuse = false;
                    this.setSelectedButton(this.bytMenuState == -35 || this.bytMenuState == -72 ? Param.getInstance().hShopPackage : Param.getInstance().hPackage, Param.getInstance().personalPackage.getCellBoxIndex());
                    this.ChangeBodyState(this.tabStyleInstance.getTabCurrentIndex());
                    break;
                }
                if (Param.getInstance().personalPackage.getFocal()) {
                    if (Param.getInstance().personalPackage.getLineIndex() == Param.getInstance().personalPackage.getLineNum() - 1) {
                        break;
                    }
                    Param.getInstance().personalPackage.getKeyDown();
                    this.setSelectedButton(this.bytMenuState == -35 || this.bytMenuState == -72 ? Param.getInstance().hShopPackage : Param.getInstance().hPackage, Param.getInstance().personalPackage.getCellBoxIndex());
                    break;
                }
                if (!this.rView.getViewFocal()) {
                    break;
                }
                byte by = this.rView.getViewCurrentIndex();
                this.rView.getViewCurrentKeyDown();
                if (by == this.rView.getViewCurrentIndex()) {
                    this.tabStyleInstance.TabFocuse = true;
                    this.rView.setViewFocal(false);
                    this.bytMoveType = (byte) 2;
                    this.setSelectedButton(null, 0);
                    break;
                }
                this.setSelectedButton(Param.getInstance().hPackageEquip, this.rView.getViewCurrentIndex());
                break;
            }
            case 65: {
                this.logicPropKey((byte) 2);
                this.setInfoContent(Param.getInstance().hPackage, this.getPropRectMove());
                break;
            }
            case -82: {
                this.setUpDown((byte) 2);
                this.setWordMove();
                break;
            }
            case 42: {
                this.shtWordMoveY = 0;
            }
            case -113:
            case -91:
            case -51:
            case -49:
            case -46:
            case -38:
            case -36:
            case -34:
            case -33:
            case -32:
            case -31:
            case -16:
            case -15:
            case 34:
            case 35:
            case 63:
            case 64: {
                this.setUpDown((byte) 2);
                break;
            }
            case -50:
            case -7: {
                if (this.bytTitleMove == 3) {
                    this.setUpDown((byte) 2);
                    break;
                }
                this.scrolltext.keyDown();
                break;
            }
            case -48: {
                if (this.bytChatRelation != 0) {
                    break;
                }
                this.setUpDown((byte) 2);
                break;
            }
            case 61: {
                if (this.isDrawSkillinfo) {
                    break;
                }
                this.setUpDown((byte) 2);
                break;
            }
            case -37: {
                this.setUpDown((byte) 2);
                this.setPetSet();
                break;
            }
            case -47: {
                this.setUpDown((byte) 2);
                this.setParcelPostButton(this.getOneMove());
                break;
            }
            case -83:
            case -80:
            case -10: {
                if (this.bytPackMaxH < this.strOneMenuOption.length) {
                    if (this.shtOneMenuMove < this.bytPackMaxH - 1) {
                        this.shtOneMenuMove = (short) (this.shtOneMenuMove + 1);
                        break;
                    }
                    if (this.shtOneMenuMove + this.shtOneMenuRoll >= this.strOneMenuOption.length - 1) {
                        break;
                    }
                    this.shtOneMenuRoll = (short) (this.shtOneMenuRoll + 1);
                    break;
                }
                if (this.shtOneMenuMove >= this.strOneMenuOption.length - 1) {
                    break;
                }
                this.shtOneMenuMove = (short) (this.shtOneMenuMove + 1);
                break;
            }
            case -44: {
                this.setUpDown((byte) 2);
                break;
            }
            case 60: {
                if (this.bytSetWordMove < Pram.STR_SET_SWITCH.length - 1) {
                    this.bytSetWordMove = (byte) (this.bytSetWordMove + 1);
                    this.bytSetBoxY = (byte) (this.bytSetBoxY + 1);
                    if (this.bytSetBoxY >= 5) {
                        this.bytSetBoxY = (byte) 5;
                    }
                    if (this.bytSetWordMove > 5 && this.bytSetBoxY == 5) {
                        this.bytSetWordY = (byte) (this.bytSetWordMove - 6 + 1);
                    }
                }
                if (this.bytSetWordMove < Pram.STR_SET_SWITCH.length - 1) {
                    break;
                }
                this.bytSetWordMove = (byte) (Pram.STR_SET_SWITCH.length - 1);
                break;
            }
            case 1: {
                if (!Param.getInstance().blnNpcOptionWork || this.strOneMenuOption == null) {
                    return;
                }
                if (this.bytPackMaxH < this.strOneMenuOption.length) {
                    if (this.shtOneMenuMove < this.bytPackMaxH - 1) {
                        this.shtOneMenuMove = (short) (this.shtOneMenuMove + 1);
                    } else if (this.shtOneMenuMove + this.shtOneMenuRoll < this.strOneMenuOption.length - 1) {
                        this.shtOneMenuRoll = (short) (this.shtOneMenuRoll + 1);
                    }
                } else if (this.shtOneMenuMove < this.strOneMenuOption.length - 1) {
                    this.shtOneMenuMove = (short) (this.shtOneMenuMove + 1);
                }
                this.initWordRoll(this.strOneMenuOption[this.getOneMove()], (short) (Macro.SCREEN_WIDTH - 38));
                break;
            }
            case -127: {
                boolean bl = true;
                if (StringManager.blnPageSeleted) {
                    bl = false;
                    StringManager.blnPageSeleted = false;
                    if (Param.getInstance().awardMustPackage != null) {
                        Param.getInstance().awardMustPackage.setFocal(true);
                    } else if (Param.getInstance().awardSelectPackage != null) {
                        Param.getInstance().awardSelectPackage.setFocal(true);
                    } else {
                        StringManager.blnPageSeleted = true;
                    }
                } else if (Param.getInstance().awardMustPackage != null) {
                    if (Param.getInstance().awardMustPackage.getFocal()) {
                        Param.getInstance().awardMustPackage.setFocal(false);
                        if (Param.getInstance().awardSelectPackage != null) {
                            Param.getInstance().awardSelectPackage.setFocal(true);
                        }
                    }
                } else if (Param.getInstance().awardSelectPackage != null) {
                    Param.getInstance().awardSelectPackage.setFocal(true);
                }
                if (this.comperH < this.boxH || this.socrollBoxY - this.packageH + this.comperH <= this.boxDownY + this.boxH - this.packageH || !bl) {
                    break;
                }
                this.socrollBoxY -= this.packageH;
                break;
            }
            case 31: {
                if (this.bytMailSubtabIndex == 3) {
                    break;
                }
                if (this.bytMailMove == 0) {
                    this.tabStyleInstance.TabFocuse = false;
                    Param.getInstance().personalPackage.setFocal(true);
                    this.bytMailMove = 1;
                } else {
                    Param.getInstance().personalPackage.getKeyDown();
                }
                this.setInfoContent(Param.getInstance().hPackage, Param.getInstance().personalPackage.getCellBoxIndex());
            }
        }
    }

    private void keyLeftEvent() {
        switch (this.bytMenuState) {
            case -72:
            case -35: {
                this.updatePersonalShop((byte) 3);
                break;
            }
            case -81: {
                if (this.tabStyleInstance.TabFocuse) {
                    this.tabStyleInstance.getTabCurrentKeyLeft();
                    this.ChangeTabState();
                    break;
                }
                if (!this.Shortcut.getFocal()) {
                    break;
                }
                this.Shortcut.getKeyLeft();
                break;
            }
            case -127: {
                if (StringManager.blnPageSeleted) {
                    if (StringManager.cutPage - 1 < 0) {
                        break;
                    }
                    --StringManager.cutPage;
                    break;
                }
                if (Param.getInstance().awardMustPackage != null && Param.getInstance().awardMustPackage.getFocal()) {
                    Param.getInstance().awardMustPackage.getKeyLeft();
                    break;
                }
                if (Param.getInstance().awardSelectPackage == null || !Param.getInstance().awardSelectPackage.getFocal()) {
                    break;
                }
                Param.getInstance().awardSelectPackage.getKeyLeft();
                break;
            }
            case -11: {
                if (this.tabStyleInstance.TabFocuse) {
                    this.tabStyleInstance.getTabCurrentKeyLeft();
                    byte by = this.tabStyleInstance.getTabCurrentIndex();
                    if (Param.MALL_BOX_NUM[by] > 0) {
                        Param.getInstance().personalPackage.reset(Param.MALL_BOX_NUM[by]);
                    }
                    this.setShangChengRes(by);
                    this.setSelectedButton(null, 0);
                    break;
                }
                if (!Param.getInstance().personalPackage.getFocal()) {
                    break;
                }
                Param.getInstance().personalPackage.getKeyLeft();
                this.setSelectedButton((Hashtable) Param.getInstance().hMallPackage.get(new Integer(this.tabStyleInstance.getTabCurrentIndex())), Param.getInstance().personalPackage.getCellBoxIndex());
                MenuUI.getInstance().setInfoContent((Hashtable) Param.getInstance().hMallPackage.get(new Integer(this.tabStyleInstance.getTabCurrentIndex())), Param.getInstance().personalPackage.getCellBoxIndex());
                break;
            }
            case -43: {
                if (this.tabStyleInstance.TabFocuse) {
                    this.tabStyleInstance.getTabCurrentKeyLeft();
                    this.setNpcStorageSelect();
                }
                if (Param.getInstance().npcShopBarPackage.getFocal()) {
                    Param.getInstance().npcShopBarPackage.getKeyLeft();
                }
                if (Param.getInstance().personalPackage.getFocal()) {
                    Param.getInstance().personalPackage.getKeyLeft();
                }
                this.setNpcStorageSelect();
                this.setNpcStorageButton(Macro.NPC_STEP_SELECT);
                break;
            }
            case -39: {
                if (Param.getInstance().npcShopBarTabStyle.TabFocuse) {
                    Param.getInstance().npcShopBarTabStyle.getTabCurrentKeyLeft();
                }
                if (this.tabStyleInstance.TabFocuse) {
                    this.tabStyleInstance.getTabCurrentKeyLeft();
                }
                if (Param.getInstance().npcShopBarPackage.getFocal()) {
                    Param.getInstance().npcShopBarPackage.getKeyLeft();
                    this.setNpcBusinessButton((byte) 2);
                }
                if (!Param.getInstance().personalPackage.getFocal()) {
                    break;
                }
                Param.getInstance().personalPackage.getKeyLeft();
                this.setNpcBusinessButton((byte) 3);
                break;
            }
            case 39: {
                if (this.tabStyleInstance.TabFocuse && !this.ChooseJewel) {
                    this.tabStyleInstance.getTabCurrentKeyLeft();
                    byte by = this.tabStyleInstance.getTabCurrentIndex();
                    this.bytTagMove = by;
                    this.tagUseEvent();
                    this.setSelectedButton(null, 0);
                    break;
                }
                if (Param.getInstance().personalPackage.getFocal()) {
                    Param.getInstance().personalPackage.getKeyLeft();
                    this.setSelectedButton(this.bytMenuState == -35 || this.bytMenuState == -72 ? Param.getInstance().hShopPackage : Param.getInstance().hPackage, Param.getInstance().personalPackage.getCellBoxIndex());
                    break;
                }
                if (!this.rView.getViewFocal()) {
                    break;
                }
                this.rView.getViewCurrentKeyLeft();
                this.setSelectedButton(Param.getInstance().hPackageEquip, this.rView.getViewCurrentIndex());
                break;
            }
            case 9: {
                if (this.bytMoveType == 1) {
                    this.bytTagMove = (byte) (this.bytTagMove - 1);
                    this.blnCursor = false;
                    if (this.bytTagMove < 0) {
                        this.bytTagMove = (byte) 4;
                    }
                    this.bytTagMove_Deal();
                    break;
                }
                if (this.bytMoveType == 0) {
                    if (this.myDealBoxMove <= 0) {
                        break;
                    }
                    this.myDealBoxMove = (byte) (this.myDealBoxMove - 1);
                    break;
                }
                this.logicPropKey((byte) 3);
                break;
            }
            case 21:
            case 22:
            case 36:
            case 37:
            case 38: {
                if (this.tabStyleInstance.TabFocuse) {
                    this.tabStyleInstance.getTabCurrentKeyLeft();
                    byte by = this.tabStyleInstance.getTabCurrentIndex();
                    this.bytTagMove = by;
                    this.tagUseEvent();
                    this.setSelectedButton(null, 0);
                    break;
                }
                if (Param.getInstance().personalPackage.getFocal()) {
                    Param.getInstance().personalPackage.getKeyLeft();
                    this.setSelectedButton(this.bytMenuState == -35 || this.bytMenuState == -72 ? Param.getInstance().hShopPackage : Param.getInstance().hPackage, Param.getInstance().personalPackage.getCellBoxIndex());
                    break;
                }
                if (!this.rView.getViewFocal()) {
                    break;
                }
                this.rView.getViewCurrentKeyLeft();
                this.setSelectedButton(Param.getInstance().hPackageEquip, this.rView.getViewCurrentIndex());
                break;
            }
            case 58: {
                if (this.bytTwoMenuState != 0) {
                    return;
                }
                if (this.bytMoveType == 1) {
                    this.logicPropKey((byte) 3);
                    break;
                }
                if (this.bytPetMove >= 0) {
                    this.bytPetMove = (byte) (this.bytPetMove - 1);
                    if (this.bytPetMove < 0) {
                        this.bytPetMove = 0;
                    }
                }
                this.setInfoContent(Param.getInstance().hPetPackEquip, this.bytPetMove);
                break;
            }
            case -33:
            case -32:
            case -31: {
                this.tabStyleInstance.getTabCurrentKeyLeft();
                this.FriendTitleChoose();
                break;
            }
            case 11: {
                this.inlayHole.getKeyLeft();
                this.inlayHole.setPopupShow();
                break;
            }
            case 31: {
                this.tabStyleInstance.getTabCurrentKeyLeft();
                if (this.bytMailMove == 0) {
                    if (this.bytMailSubtabIndex > 0) {
                        this.bytMailSubtabIndex = (byte) (this.bytMailSubtabIndex - 1);
                        if (this.bytMailSubtabIndex != 2) {
                            Param.getInstance().vMenuMemory.removeElementAt(Param.getInstance().vMenuMemory.size() - 1);
                        }
                        this.sendMailGood(this.bytMailSubtabIndex);
                    }
                } else {
                    Param.getInstance().personalPackage.getKeyLeft();
                }
                this.setInfoContent(Param.getInstance().hPackage, Param.getInstance().personalPackage.getCellBoxIndex());
                break;
            }
            case 64: {
                this.tabStyleInstance.getTabCurrentKeyLeft();
                break;
            }
            case -113: {
                if (!this.leftMenuTitle()) {
                    break;
                }
                this.clearMakeContent();
                this.getMakeSkillContent();
                break;
            }
            case 35: {
                this.clearMove();
                this.tabStyleInstance.getTabCurrentKeyLeft();
                break;
            }
            case -48: {
                this.clearMove();
                this.tabStyleInstance.getTabCurrentKeyLeft();
                break;
            }
            case 61: {
                if (this.isDrawSkillinfo) {
                    break;
                }
                this.tabStyleInstance.TabFocuse = true;
                this.tabStyleInstance.getTabCurrentKeyLeft();
                if (this.bytTitleMove == this.tabStyleInstance.getTabCurrentIndex()) {
                    break;
                }
                this.bytTitleMove = this.tabStyleInstance.getTabCurrentIndex();
                this.setSkill();
                break;
            }
            case 63: {
                this.tabStyleInstance.getTabCurrentKeyLeft();
                this.setTaskType();
                break;
            }
            case -7: {
                this.tabStyleInstance.getTabCurrentKeyLeft();
                this.bytTitleMove = this.tabStyleInstance.getTabCurrentIndex();
                this.setWordMove();
                MenuUI.getInstance().refreshRoleInfo();
                break;
            }
            case -5: {
                this.setRoleSee(3);
                break;
            }
            case -6: {
                this.rView.getViewCurrentKeyLeft();
                break;
            }
            case 10:
            case 33: {
                if (this.bytTwoMenuState != 0) {
                    return;
                }
                if (this.tabStyleInstance.TabFocuse) {
                    byte by = this.tabStyleInstance.getTabCurrentIndex();
                    this.tabStyleInstance.getTabCurrentKeyLeft();
                    byte by2 = this.tabStyleInstance.getTabCurrentIndex();
                    if (by2 == by) {
                        break;
                    }
                    this.bytTagMove = by2;
                    this.tagUseEvent();
                    this.setSelectedButton(null, 0);
                    break;
                }
                if (Param.getInstance().personalPackage.getFocal()) {
                    Param.getInstance().personalPackage.getKeyLeft();
                    this.setSelectedButton(this.bytMenuState == -35 || this.bytMenuState == -72 ? Param.getInstance().hShopPackage : Param.getInstance().hPackage, Param.getInstance().personalPackage.getCellBoxIndex());
                    break;
                }
                if (!this.rView.getViewFocal()) {
                    break;
                }
                this.rView.getViewCurrentKeyLeft();
                this.setSelectedButton(Param.getInstance().hPackageEquip, this.rView.getViewCurrentIndex());
                break;
            }
            case 65: {
                this.logicPropKey((byte) 3);
                break;
            }
            case 42: {
                this.tabStyleInstance.getTabCurrentKeyLeft();
                Param.getInstance().bytChatNoteType = this.bytTitleMove = this.tabStyleInstance.getTabCurrentIndex();
                this.shtWordMoveY = 0;
                this.clearMove();
                this.blnSetChatNote = true;
                this.setChatNote();
                break;
            }
            case 60: {
                this.setButton(1, 0, 2);
                this.keyMenuSettingLeft();
            }
        }
    }

    public void bytTagMove_Deal() {
        switch (this.bytTagMove) {
            case 0: {
                Param.getInstance().bytDealGoods = 0;
                NetSend.getInstance().sendDealBegPack(Param.getInstance().intDealID, Param.getInstance().bytDealGoods);
                break;
            }
            case 1: {
                Param.getInstance().bytDealGoods = (byte) 3;
                NetSend.getInstance().sendDealBegPack(Param.getInstance().intDealID, Param.getInstance().bytDealGoods);
                break;
            }
            case 2: {
                Param.getInstance().bytDealGoods = (byte) 2;
                NetSend.getInstance().sendDealBegPack(Param.getInstance().intDealID, Param.getInstance().bytDealGoods);
                break;
            }
            case 3: {
                MenuUI.getInstance().setPetList();
                NetSend.getInstance().sendPetHandle((byte) 10, 0);
                break;
            }
            case 4: {
                Param.getInstance().bytDealGoods = 1;
                NetSend.getInstance().sendDealBegPack(Param.getInstance().intDealID, Param.getInstance().bytDealGoods);
            }
        }
    }

    private void keyRightEvent() {
        switch (this.bytMenuState) {
            case -72:
            case -35: {
                this.updatePersonalShop((byte) 4);
                break;
            }
            case -81: {
                if (this.tabStyleInstance.TabFocuse) {
                    this.tabStyleInstance.getTabCurrentKeyRight();
                    this.ChangeTabState();
                    break;
                }
                if (!this.Shortcut.getFocal()) {
                    break;
                }
                this.Shortcut.getKeyRight();
                break;
            }
            case -127: {
                if (StringManager.blnPageSeleted) {
                    if (StringManager.cutPage + 1 > StringManager.totalPage) {
                        break;
                    }
                    ++StringManager.cutPage;
                    break;
                }
                if (Param.getInstance().awardMustPackage != null && Param.getInstance().awardMustPackage.getFocal()) {
                    Param.getInstance().awardMustPackage.getKeyRight();
                    break;
                }
                if (Param.getInstance().awardSelectPackage == null || !Param.getInstance().awardSelectPackage.getFocal()) {
                    break;
                }
                Param.getInstance().awardSelectPackage.getKeyRight();
                break;
            }
            case -11: {
                if (this.tabStyleInstance.TabFocuse) {
                    byte by = this.tabStyleInstance.getTabCurrentIndex();
                    this.tabStyleInstance.getTabCurrentKeyRight();
                    this.setSelectedButton(null, 0);
                    byte by2 = this.tabStyleInstance.getTabCurrentIndex();
                    if (Param.MALL_BOX_NUM[by2] > 0) {
                        Param.getInstance().personalPackage.reset(Param.MALL_BOX_NUM[by2]);
                    }
                    this.setShangChengRes(by2);
                    break;
                }
                if (!Param.getInstance().personalPackage.getFocal()) {
                    break;
                }
                Param.getInstance().personalPackage.getKeyRight();
                this.setSelectedButton((Hashtable) Param.getInstance().hMallPackage.get(new Integer(this.tabStyleInstance.getTabCurrentIndex())), Param.getInstance().personalPackage.getCellBoxIndex());
                MenuUI.getInstance().setInfoContent((Hashtable) Param.getInstance().hMallPackage.get(new Integer(this.tabStyleInstance.getTabCurrentIndex())), Param.getInstance().personalPackage.getCellBoxIndex());
                break;
            }
            case -70: {
                this.logicPropKey((byte) 4);
                break;
            }
            case -43: {
                if (this.tabStyleInstance.TabFocuse) {
                    this.tabStyleInstance.getTabCurrentKeyRight();
                }
                if (Param.getInstance().npcShopBarPackage.getFocal()) {
                    Param.getInstance().npcShopBarPackage.getKeyRight();
                }
                if (Param.getInstance().personalPackage.getFocal()) {
                    Param.getInstance().personalPackage.getKeyRight();
                }
                this.setNpcStorageSelect();
                this.setNpcStorageButton(Macro.NPC_STEP_SELECT);
                break;
            }
            case -39: {
                if (Param.getInstance().npcShopBarTabStyle.TabFocuse) {
                    Param.getInstance().npcShopBarTabStyle.getTabCurrentKeyRight();
                }
                if (this.tabStyleInstance.TabFocuse) {
                    this.tabStyleInstance.getTabCurrentKeyRight();
                }
                if (Param.getInstance().npcShopBarPackage.getFocal()) {
                    Param.getInstance().npcShopBarPackage.getKeyRight();
                    this.setNpcBusinessButton((byte) 2);
                }
                if (!Param.getInstance().personalPackage.getFocal()) {
                    break;
                }
                Param.getInstance().personalPackage.getKeyRight();
                this.setNpcBusinessButton((byte) 3);
                break;
            }
            case 39: {
                if (this.tabStyleInstance.TabFocuse && !this.ChooseJewel) {
                    this.tabStyleInstance.getTabCurrentKeyRight();
                    byte by = this.tabStyleInstance.getTabCurrentIndex();
                    this.bytTagMove = by;
                    this.tagUseEvent();
                    this.setSelectedButton(null, 0);
                    break;
                }
                if (Param.getInstance().personalPackage.getFocal()) {
                    Param.getInstance().personalPackage.getKeyRight();
                    this.setSelectedButton(this.bytMenuState == -35 || this.bytMenuState == -72 ? Param.getInstance().hShopPackage : Param.getInstance().hPackage, Param.getInstance().personalPackage.getCellBoxIndex());
                    break;
                }
                if (!this.rView.getViewFocal()) {
                    break;
                }
                this.rView.getViewCurrentKeyRight();
                this.setSelectedButton(Param.getInstance().hPackageEquip, this.rView.getViewCurrentIndex());
                break;
            }
            case 9: {
                if (this.bytMoveType == 1) {
                    this.bytTagMove = (byte) (this.bytTagMove + 1);
                    this.blnCursor = false;
                    if (this.bytTagMove > 4) {
                        this.bytTagMove = (byte) 4;
                    }
                    this.bytTagMove_Deal();
                    break;
                }
                if (this.bytMoveType == 0) {
                    DialogUI.getInstance();
                    if (this.myDealBoxMove >= 6 - 1) {
                        break;
                    }
                    this.myDealBoxMove = (byte) (this.myDealBoxMove + 1);
                    break;
                }
                if (this.bytMoveType != 2) {
                    break;
                }
                this.logicPropKey((byte) 4);
                break;
            }
            case 21:
            case 22:
            case 36:
            case 37:
            case 38: {
                if (this.tabStyleInstance.TabFocuse) {
                    byte by = this.tabStyleInstance.getTabCurrentIndex();
                    this.tabStyleInstance.getTabCurrentKeyRight();
                    byte by3 = this.tabStyleInstance.getTabCurrentIndex();
                    if (by == by3) {
                        break;
                    }
                    this.bytTagMove = by3;
                    this.tagUseEvent();
                    this.setSelectedButton(null, 0);
                    break;
                }
                if (Param.getInstance().personalPackage.getFocal()) {
                    Param.getInstance().personalPackage.getKeyRight();
                    this.setSelectedButton(this.bytMenuState == -35 || this.bytMenuState == -72 ? Param.getInstance().hShopPackage : Param.getInstance().hPackage, Param.getInstance().personalPackage.getCellBoxIndex());
                    break;
                }
                if (!this.rView.getViewFocal()) {
                    break;
                }
                this.rView.getViewCurrentKeyRight();
                this.setSelectedButton(Param.getInstance().hPackageEquip, this.rView.getViewCurrentIndex());
                break;
            }
            case 35: {
                this.clearMove();
                this.tabStyleInstance.getTabCurrentKeyRight();
                break;
            }
            case -48: {
                this.clearMove();
                this.tabStyleInstance.getTabCurrentKeyRight();
                break;
            }
            case 11: {
                this.inlayHole.getKeyRight();
                this.inlayHole.setPopupShow();
                break;
            }
            case 58: {
                if (this.bytTagPlace == 1) {
                    this.bytTagIndex = 0;
                }
                if (this.bytTwoMenuState != 0) {
                    return;
                }
                if (this.bytMoveType == 1) {
                    this.logicPropKey((byte) 4);
                    break;
                }
                if (this.bytMoveType != 0) {
                    break;
                }
                if (this.bytPetMove <= 3) {
                    this.bytPetMove = (byte) (this.bytPetMove + 1);
                    if (this.bytPetMove > 3) {
                        this.bytPetMove = (byte) 3;
                    }
                }
                this.setInfoContent(Param.getInstance().hPetPackEquip, this.bytPetMove);
                break;
            }
            case -33:
            case -32:
            case -31: {
                this.tabStyleInstance.getTabCurrentKeyRight();
                this.FriendTitleChoose();
                break;
            }
            case 31: {
                if (this.tabStyleInstance.TabFocuse) {
                    byte by = this.tabStyleInstance.getTabCurrentIndex();
                    this.tabStyleInstance.getTabCurrentKeyRight();
                    byte by4 = this.tabStyleInstance.getTabCurrentIndex();
                    if (by4 != by && by4 != 3) {
                        if (Param.getInstance().vMenuMemory != null && Param.getInstance().vMenuMemory.size() > by) {
                            Param.getInstance().vMenuMemory.removeElementAt(by);
                        }
                        this.sendMailGood(by4);
                    }
                } else {
                    Param.getInstance().personalPackage.getKeyRight();
                }
                this.setInfoContent(Param.getInstance().hPackage, Param.getInstance().personalPackage.getCellBoxIndex());
                if (this.bytMailMove == 0) {
                    if (this.bytMailSubtabIndex < 4) {
                        this.bytMailSubtabIndex = (byte) (this.bytMailSubtabIndex + 1);
                        if (this.bytMailSubtabIndex != 4) {
                            Param.getInstance().vMenuMemory.removeElementAt(Param.getInstance().vMenuMemory.size() - 1);
                        }
                        this.sendMailGood(this.bytMailSubtabIndex);
                    }
                } else {
                    Param.getInstance().personalPackage.getKeyRight();
                }
                this.setInfoContent(Param.getInstance().hPackage, Param.getInstance().personalPackage.getCellBoxIndex());
                break;
            }
            case 64: {
                this.tabStyleInstance.getTabCurrentKeyRight();
                break;
            }
            case -113: {
                if (!this.rightMenuTitle()) {
                    break;
                }
                this.clearMakeContent();
                this.getMakeSkillContent();
                break;
            }
            case 61: {
                if (this.isDrawSkillinfo) {
                    break;
                }
                this.tabStyleInstance.TabFocuse = true;
                this.tabStyleInstance.getTabCurrentKeyRight();
                if (this.bytTitleMove == this.tabStyleInstance.getTabCurrentIndex()) {
                    break;
                }
                this.bytTitleMove = this.tabStyleInstance.getTabCurrentIndex();
                this.setSkill();
                break;
            }
            case 63: {
                this.tabStyleInstance.getTabCurrentKeyRight();
                this.setTaskType();
                break;
            }
            case -7: {
                this.tabStyleInstance.getTabCurrentKeyRight();
                this.bytTitleMove = this.tabStyleInstance.getTabCurrentIndex();
                this.setWordMove();
                MenuUI.getInstance().refreshRoleInfo();
                break;
            }
            case -5: {
                this.setRoleSee(4);
                break;
            }
            case -6: {
                this.rView.getViewCurrentKeyRight();
                break;
            }
            case 10:
            case 33: {
                if (this.bytTwoMenuState != 0) {
                    return;
                }
                if (this.tabStyleInstance.TabFocuse) {
                    this.tabStyleInstance.getTabCurrentKeyRight();
                    byte by = this.tabStyleInstance.getTabCurrentIndex();
                    if (this.bytTagMove == by) {
                        break;
                    }
                    this.bytTagMove = by;
                    this.tagUseEvent();
                    this.setSelectedButton(null, 0);
                    break;
                }
                if (Param.getInstance().personalPackage.getFocal()) {
                    Param.getInstance().personalPackage.getKeyRight();
                    this.setSelectedButton(this.bytMenuState == -35 || this.bytMenuState == -72 ? Param.getInstance().hShopPackage : Param.getInstance().hPackage, Param.getInstance().personalPackage.getCellBoxIndex());
                    break;
                }
                if (!this.rView.getViewFocal()) {
                    break;
                }
                byte by = this.rView.getViewCurrentIndex();
                this.rView.getViewCurrentKeyRight();
                this.setSelectedButton(Param.getInstance().hPackageEquip, this.rView.getViewCurrentIndex());
                this.rView.getViewCurrentIndex();
                break;
            }
            case 65: {
                this.logicPropKey((byte) 4);
                break;
            }
            case 42: {
                this.tabStyleInstance.getTabCurrentKeyRight();
                Param.getInstance().bytChatNoteType = this.bytTitleMove = this.tabStyleInstance.getTabCurrentIndex();
                this.shtWordMoveY = 0;
                this.clearMove();
                this.blnSetChatNote = true;
                this.setChatNote();
                break;
            }
            case 60: {
                this.setButton(1, 0, 2);
                this.keyMenuSettingRight();
            }
        }
    }

    private void keyNum2Event() {
        switch (this.bytMenuState) {
            case -44: {
                if (Param.getInstance().vCommonList == null) {
                    return;
                }
                if (this.bytTwoMenuState != 0 && this.strOneDescribeChar == null && this.intTimer > 0) {
                    return;
                }
                if (this.shtWordMoveY < 0) {
                    this.bytWordMoveStep = (byte) -4;
                    break;
                }
                if (this.shtWordMoveY >= 2) {
                    this.bytWordMoveStep = (byte) 2;
                    break;
                }
                this.bytWordMoveStep = 0;
                break;
            }
        }
    }

    private void keyNum8Event() {
        switch (this.bytMenuState) {
            case -44: {
                if (Param.getInstance().vCommonList == null) {
                    return;
                }
                if (this.bytTwoMenuState != 0 && this.strOneDescribeChar == null) {
                    return;
                }
                this.bytWordMoveStep = (byte) 4;
                this.intTimer = 0;
                break;
            }
        }
    }

    private void keyNum5Event() {
        switch (this.bytMenuState) {
            case -44: {
                break;
            }
        }
    }

    public void keyNum1Event() {
        switch (this.bytMenuState) {
            case 10:
            case 36:
            case 37:
            case 38:
            case 39: {
                if (Param.getInstance().playerColne == null) {
                    break;
                }
                switch (Param.getInstance().playerColne.bytDirection) {
                    case 1: {
                        RoleViewStyle.ROLE_DIRECTION = 4;
                        break;
                    }
                    case 2: {
                        RoleViewStyle.ROLE_DIRECTION = 3;
                        break;
                    }
                    case 3: {
                        RoleViewStyle.ROLE_DIRECTION = 1;
                        break;
                    }
                    case 4: {
                        RoleViewStyle.ROLE_DIRECTION = 2;
                        break;
                    }
                }
                Param.getInstance().playerColne.pushTask((byte) 0, (byte) RoleViewStyle.ROLE_DIRECTION, (byte) 1);
            }
        }
    }

    public void keyNum3Event() {
        switch (this.bytMenuState) {
            case 10:
            case 36:
            case 37:
            case 38:
            case 39: {
                if (Param.getInstance().playerColne == null || Param.getInstance().playerColne == null) {
                    break;
                }
                switch (Param.getInstance().playerColne.bytDirection) {
                    case 1: {
                        RoleViewStyle.ROLE_DIRECTION = 3;
                        break;
                    }
                    case 2: {
                        RoleViewStyle.ROLE_DIRECTION = 4;
                        break;
                    }
                    case 3: {
                        RoleViewStyle.ROLE_DIRECTION = 2;
                        break;
                    }
                    case 4: {
                        RoleViewStyle.ROLE_DIRECTION = 1;
                        break;
                    }
                }
                Param.getInstance().playerColne.pushTask((byte) 0, (byte) RoleViewStyle.ROLE_DIRECTION, (byte) 1);
                break;
            }
        }
    }

    private void keyNoneEvent() {
        switch (this.bytMenuState) {
            default:
        }
    }

    private void keyMenuSettingLeft() {
        switch (this.bytSetWordMove) {
            case 1: {
                if (this.bytSetChannel > 0) {
                    this.bytSetChannel = (byte) (this.bytSetChannel - 1);
                    break;
                }
                this.bytSetChannel = (byte) (Pram.STR_OPTION_OPENANDCLOSE.length - 1);
                break;
            }
            case 3: {
                if (this.bytSetWeather > 0) {
                    this.bytSetWeather = (byte) (this.bytSetWeather - 1);
                    break;
                }
                this.bytSetWeather = (byte) (Pram.STR_OPTION_OPENANDCLOSE.length - 1);
                break;
            }
            case 4: {
                if (this.bytSetShowRole > 0) {
                    this.bytSetShowRole = (byte) (this.bytSetShowRole - 1);
                    break;
                }
                this.bytSetShowRole = (byte) (Pram.STR_OPTION_OPENANDCLOSE.length - 1);
                break;
            }
            case 0: {
                if (this.bytSetShow > 0) {
                    this.bytSetShow = (byte) (this.bytSetShow - 1);
                    break;
                }
                this.bytSetShow = (byte) (Pram.STR_SHOW_EFFECT.length - 1);
                break;
            }
            case 2: {
                if (this.bytShowName > 0) {
                    this.bytShowName = (byte) (this.bytShowName - 1);
                    break;
                }
                this.bytShowName = (byte) (Pram.STR_SHOWNAME_OPTION.length - 1);
                break;
            }
            case 5: {
                if (this.bytAutoWalk > 0) {
                    this.bytAutoWalk = (byte) (this.bytAutoWalk - 1);
                    break;
                }
                this.bytAutoWalk = (byte) (Pram.STR_OPTION_OPENANDCLOSE.length - 1);
                break;
            }
            case 6: {
                this.bytAutoSelect = this.bytAutoSelect > 0 ? (byte) (this.bytAutoSelect - 1) : (byte) (Pram.STR_OPTION_OPENANDCLOSE.length - 1);
            }
        }
        this.keymenuSettingSave();
    }

    private void keyMenuSettingRight() {
        switch (this.bytSetWordMove) {
            case 1: {
                this.bytSetChannel = (byte) (this.bytSetChannel + 1);
                if (this.bytSetChannel <= Pram.STR_OPTION_OPENANDCLOSE.length - 1) {
                    break;
                }
                this.bytSetChannel = 0;
                break;
            }
            case 3: {
                this.bytSetWeather = (byte) (this.bytSetWeather + 1);
                if (this.bytSetWeather <= Pram.STR_OPTION_OPENANDCLOSE.length - 1) {
                    break;
                }
                this.bytSetWeather = 0;
                break;
            }
            case 4: {
                this.bytSetShowRole = (byte) (this.bytSetShowRole + 1);
                if (this.bytSetShowRole <= Pram.STR_OPTION_OPENANDCLOSE.length - 1) {
                    break;
                }
                this.bytSetShowRole = 0;
                break;
            }
            case 0: {
                this.bytSetShow = (byte) (this.bytSetShow + 1);
                if (this.bytSetShow <= Pram.STR_SHOW_EFFECT.length - 1) {
                    break;
                }
                this.bytSetShow = 0;
                break;
            }
            case 2: {
                this.bytShowName = (byte) (this.bytShowName + 1);
                if (this.bytShowName <= Pram.STR_SHOWNAME_OPTION.length - 1) {
                    break;
                }
                this.bytShowName = 0;
                break;
            }
            case 5: {
                this.bytAutoWalk = (byte) (this.bytAutoWalk + 1);
                if (this.bytAutoWalk <= Pram.STR_OPTION_OPENANDCLOSE.length - 1) {
                    break;
                }
                this.bytAutoWalk = 0;
                break;
            }
            case 6: {
                this.bytAutoSelect = (byte) (this.bytAutoSelect + 1);
                if (this.bytAutoSelect <= Pram.STR_OPTION_OPENANDCLOSE.length - 1) {
                    break;
                }
                this.bytAutoSelect = 0;
            }
        }
        this.keymenuSettingSave();
    }

    private void keymenuSettingSave() {
        this.setData[0] = (byte) (this.bytSetChannel + 1);
        this.setData[1] = (byte) (this.bytSetWeather + 1);
        this.setData[2] = (byte) (this.bytSetShowRole + 1);
        this.setData[3] = (byte) (this.bytSetShow + 1);
        this.setData[4] = (byte) (this.bytShowName + 1);
        this.setData[5] = (byte) (this.bytAutoWalk + 1);
        this.setData[6] = (byte) (this.bytAutoSelect + 1);
    }

    private byte getPageType() {
        int n = 0;
        n = Param.getInstance().shtAllPage <= 1 ? 1 : (Param.getInstance().shtNoncePage <= 1 ? 2 : (Param.getInstance().shtNoncePage >= Param.getInstance().shtAllPage ? 3 : 4));
        return (byte) n;
    }

    private void setRoleSee() {
        this.shtRectUpH = (short) 60;
        this.shtPropRectW = (short) 23;
        int n = 0;
        if (Macro.SCREEN_WIDTH > 210) {
            this.shtRectUpH = (short) 80;
            n = 10;
        }
        this.shtRectDownH = (short) (Macro.SCREEN_HEIGHT - (Macro.FONTHEIGHT + 3 + this.shtRectUpH + Macro.FONTHEIGHT + 28));
        this.shtRoleRectX = (short) ((Macro.SCREEN_WIDTH - (50 + n + 4 * this.shtPropRectW)) / 2);
        this.shtRoleRectY = (short) (Macro.FONTHEIGHT + 3 + (this.shtRectUpH - 54) / 2);
        this.shtPropRectX = (short) (this.shtRoleRectX + 50 + n);
        this.shtPropRectY = this.shtRoleRectY;
    }

    private void initProperty() {
        this.bytMoveType = 0;
        this.bytTitleMove = 0;
        this.strsTitle = IDefines.PROPERTY_UI_TITLE_NAME;
        this.setTitlePlace((byte) this.strsTitle.length);
        this.tabStyleInstance.initTabStyle((short) 4, (short) 38, (short) IDefines.GLOBAL_UI_MAIN_TAB_WIDTH, (short) 32, this.strsTitle, this.bytTitleMove);
        this.tabStyleInstance.TabFocuse = true;
        this.setGrrdTableFrom();
        this.Font_Show_Width = this.fromTable.getCell(2, 1).getCellW() - 24;
        if (this.bytTitleMove == 0) {
            this.initBase();
        }
        Param.getInstance().playerColne = ORPMe.ME.colnePlayerBaseData();
        Param.getInstance().playerColne.pushTask((byte) 0, Param.getInstance().playerColne.bytDirection, (byte) 1);
        this.setButton(1, 100, 2);
    }

    private void initBase() {
        this.clearMove();
        if (Macro.SCREEN_HEIGHT < 290) {
            this.blnRoleInfo = true;
        }
        this.setRoleAttribute(0);
        int n = Macro.SCREEN_HEIGHT - this.tabStyleInstance.GettabBottomY - 31 - (this.tabStyleInstance.GettabBottomY + Macro.FONTW + 110 + 2 - this.tabStyleInstance.GettabBottomY) - 6 - 2;
        this.bytWordMaxH = this.blnRoleInfo ? (byte) ((Macro.SCREEN_HEIGHT - this.tabStyleInstance.GettabBottomY - 31 - Macro.FONTHEIGHT) / Macro.FONTHEIGHT) : (byte) ((n - 4) / Macro.FONTHEIGHT);
        this.shtMenuMoveLength = (short) this.strOneDescribeChar.length;
        this.role_framex = (short) 16;
        this.role_framey = (short) (this.tabStyleInstance.GettabBottomY + 8);
        this.role_framew = (short) ((Macro.SCREEN_WIDTH >> 1) - Macro.FONTW - this.role_framex - 4);
        this.role_frameh = (short) (105 > LayoutStyle.getInstance().FontFramHeight ? 113 : LayoutStyle.getInstance().FontFramHeight + 8);
        LayoutStyle.getInstance().setFont(IDefines.PRORERTY_UI_BASICDATE, this.PRORERTY_UI_EXPLAIN, this.role_framex + this.role_framew + 2, this.role_framey + 2);
        int n2 = Macro.SCREEN_HEIGHT - this.tabStyleInstance.GettabBottomY - 31 - (this.tabStyleInstance.GettabBottomY + Macro.FONTW + 110 + 2 - this.tabStyleInstance.GettabBottomY) - 6 - 2;
        this.scrolltext = this.blnRoleInfo ? new ScrollText(this.getText, this.role_framex + this.role_framew, this.role_framey, Macro.SCREEN_WIDTH - this.role_framex - this.role_framew - 10, this.bytWordMaxH * Macro.FONTHEIGHT, 8142636, (byte) 0) : new ScrollText(this.getText, this.fromTable.getCell(2, 1).getCellX() + 12 + 2, this.tabStyleInstance.GettabBottomY + Macro.FONTW + 110 + 6, this.fromTable.getCell(2, 1).getCellW() - 24, n2, 8142636, (byte) 0);
    }

    private void initFrightingDate() {
        this.clearMove();
        this.setRoleAttribute(1);
        this.bytWordMaxH = (byte) ((Macro.SCREEN_HEIGHT - this.tabStyleInstance.GettabBottomY - 31) / Macro.FONTHEIGHT);
        this.shtMenuMoveLength = (short) this.strOneDescribeChar.length;
        this.scrolltext = new ScrollText(this.getText, this.fromTable.getCell(2, 1).getCellX() + 12, this.tabStyleInstance.GettabBottomY + 6 - 2, this.fromTable.getCell(2, 1).getCellW() - 24, Macro.SCREEN_HEIGHT - this.tabStyleInstance.GettabBottomY - 31, 8142636, (byte) 0);
        this.setButton(1, 100, 2);
    }

    public void initState() {
        this.clearMove();
        this.shtMenuMoveLength = 0;
        short s = 0;
        if (ORPMe.ME.strsBuff != null && !ORPMe.ME.strsBuff.equals("") && ORPMe.ME.intsBuff != null) {
            this.getstrsBuffLeng = (byte) ORPMe.ME.strsBuff.length;
            s = (short) (s + (short) ORPMe.ME.strsBuff.length);
        }
        if (ORPMe.ME.strsDeBuff != null && !ORPMe.ME.strsDeBuff.equals("") && ORPMe.ME.intsDeBuff != null) {
            this.getstrsDeBuffLeng = (byte) ORPMe.ME.strsDeBuff.length;
            s = (short) (s + (short) ORPMe.ME.strsDeBuff.length);
        }
        this.shtMenuMoveLength = s;
        this.pointY = this.tabStyleInstance.GettabBottomY + Macro.FONTHEIGHT + 4;
        this.bytWordMaxH = (byte) ((Macro.SCREEN_HEIGHT - this.pointY - 31) / Macro.FONTHEIGHT);
        if (this.shtMenuMoveLength > 0) {
            this.setButton(1, 7, 2);
        }
    }

    private void initHonour() {
        this.setRoleAttribute(3);
        this.scrolltext = new ScrollText(this.getText, this.fromTable.getCell(2, 1).getCellX() + 12, this.tabStyleInstance.GettabBottomY + 6 - 2, this.fromTable.getCell(2, 1).getCellW() - 24, Macro.SCREEN_HEIGHT - this.tabStyleInstance.GettabBottomY - 31, 8142636, (byte) 0);
    }

    private void drawRoleInfo(Graphics graphics) {
        this.drawTitlTabBackGround(graphics, "Nhân vật");
        this.bytTitleMove = this.tabStyleInstance.getTabCurrentIndex();
        int n = this.fromTable.getCell(2, 1).getCellX() + 12;
        int n2 = this.fromTable.getCell(2, 1).getCellW() - 24;
        this.shtOneMenuRoll = 0;
        if (this.bytTitleMove == 0) {
            LayoutStyle.getInstance().drawBeforeBackGround(this.fromTable.getCell(2, 1).getCellX(), this.tabStyleInstance.GettabBottomY, this.fromTable.getCell(2, 1).getCellW(), Macro.SCREEN_HEIGHT - this.tabStyleInstance.GettabBottomY - 31, new int[]{8142636, 14995858, 16314576});
            DrawBase.drawBox(this.role_framex, this.role_framey, this.role_framew, this.role_frameh, new int[]{11301955, 14995858}, true);
            if (ORPMe.ME.blnPlayerResOK) {
                Param.getInstance().playerColne.draw(graphics, this.role_framex + (this.role_framew >> 1), this.role_framey + ((this.role_frameh >> 1) + (this.role_frameh >> 2)));
            }
            if (this.blnRoleInfo) {
                int n3 = 0;
                while (n3 < this.bytWordMaxH) {
                    DrawBase.drawBox(this.role_framex + this.role_framew, this.role_framey + n3 * Macro.FONTHEIGHT, Macro.SCREEN_WIDTH - this.role_framex - this.role_framew - 10, Macro.FONTHEIGHT, new int[]{n3 % 2 == 0 ? 16314576 : 15195580}, true);
                    ++n3;
                }
                this.scrolltext.drawBread(graphics);
            } else {
                LayoutStyle.getInstance().drawFontFrame(graphics);
                int n4 = this.role_framey + this.role_frameh;
                int n5 = Macro.SCREEN_HEIGHT - this.tabStyleInstance.GettabBottomY - 31 - (this.tabStyleInstance.GettabBottomY + Macro.FONTW + 110 + 2 - this.tabStyleInstance.GettabBottomY) - 6 - 2;
                DrawBase.drawBox(n, n4, n2, n5, new int[]{12026667, 14995858, 14995858, 16642234}, true);
                this.scrolltext.drawBread(graphics);
            }
        } else if (this.bytTitleMove == 1) {
            int n6 = this.tabStyleInstance.GettabBottomY + 6 - 2;
            int n7 = this.fromTable.getCell(2, 1).getCellW() - 24;
            LayoutStyle.getInstance().drawBeforeBackGround(this.fromTable.getCell(2, 1).getCellX(), this.tabStyleInstance.GettabBottomY, this.fromTable.getCell(2, 1).getCellW(), Macro.SCREEN_HEIGHT - this.tabStyleInstance.GettabBottomY - 31, new int[]{8142636, 14995858, 16314576});
            DrawBase.setClip(n, n6, n7, Macro.SCREEN_HEIGHT - this.tabStyleInstance.GettabBottomY - 31 - 2);
            int n8 = 0;
            while (n8 < this.bytWordMaxH) {
                DrawBase.drawBox(n, n6 + n8 * Macro.FONTHEIGHT, n7, Macro.FONTHEIGHT, new int[]{n8 % 2 == 0 ? 16314576 : 15195580}, true);
                ++n8;
            }
            DrawBase.setClip(0, 0, Macro.SCREEN_WIDTH, Macro.SCREEN_HEIGHT);
            this.scrolltext.drawBread(graphics);
        } else if (this.bytTitleMove == 2) {
            this.scrolltext.drawBread(graphics);
        } else if (this.bytTitleMove == 3) {
            int n9;
            int n10 = 12;
            int n11 = this.tabStyleInstance.GettabBottomY + Macro.FONTHEIGHT + 4;
            int n12 = 6 + (Macro.SCREEN_WIDTH - 12) - 6 - (Macro.FONTW << 2);
            if (ORPMe.ME.strsBuff != null && !ORPMe.ME.strsBuff.equals("") && ORPMe.ME.intsBuff != null || ORPMe.ME.strsDeBuff != null && !ORPMe.ME.strsDeBuff.equals("") && ORPMe.ME.intsDeBuff != null) {
                DrawBase.drawBox(6, this.tabStyleInstance.GettabBottomY + 1, Macro.SCREEN_WIDTH - 12, Macro.FONTHEIGHT + 4, new int[]{15195580}, true);
                DrawBase.drawString("Tên", 12, this.tabStyleInstance.GettabBottomY + 3, 0, 20);
                DrawBase.drawString("Khoảng thời gian", 6 + (Macro.SCREEN_WIDTH - 12) - 6, this.tabStyleInstance.GettabBottomY + 3, 0, 24);
                LayoutStyle.getInstance().drawSelectBox(10, n11 + this.shtOneMenuMove * Macro.FONTHEIGHT, StringManager.getNewLineW() + 6, Macro.FONTHEIGHT);
            }
            if (ORPMe.ME.strsBuff != null && !ORPMe.ME.strsBuff.equals("") && ORPMe.ME.intsBuff != null) {
                n9 = 0;
                while (n9 < ORPMe.ME.strsBuff.length) {
                    StringManager.drawWordRightToLeft(ORPMe.ME.strsBuff[n9][0], n10, n11, Macro.SCREEN_WIDTH - 12 >> 1, 9263661, 1, this.shtOneMenuMove == n9);
                    StringManager.drawWordRightToLeft(ORPMe.ME.intsBuff[n9][1] / 1000 + "Thứ hai", n12, n11, Macro.FONTW << 2, 9263661, 1, this.shtOneMenuMove == n9);
                    n11 += Macro.FONTHEIGHT;
                    ++n9;
                }
            }
            if (ORPMe.ME.strsDeBuff != null && !ORPMe.ME.strsDeBuff.equals("") && ORPMe.ME.intsDeBuff != null) {
                n9 = 0;
                while (n9 < ORPMe.ME.strsBuff.length) {
                    StringManager.drawWordRightToLeft(ORPMe.ME.strsDeBuff[n9][0], n10, n11, Macro.SCREEN_WIDTH - 12 >> 1, 9263661, 1, this.shtOneMenuMove == n9);
                    StringManager.drawWordRightToLeft(ORPMe.ME.intsDeBuff[n9][1] / 1000 + "Thứ hai", n12, n11, Macro.FONTW << 2, 9263661, 1, this.shtOneMenuMove == n9);
                    n11 += Macro.FONTHEIGHT;
                    ++n9;
                }
            }
        }
    }

    public void initOptionList(Vector vector, int n, int n2) {
        this.clearMove();
        this.shtMenuMoveLength = 0;
        if (vector != null) {
            this.shtMenuMoveLength = (short) vector.size();
        }
        this.pointY = n2 + Macro.FONTHEIGHT + 4;
        this.bytWordMaxH = (byte) ((Macro.SCREEN_HEIGHT - this.pointY - 31) / Macro.FONTHEIGHT);
    }

    public void refreshRoleInfo() {
        if (this.bytTitleMove == 0) {
            this.initBase();
        } else if (this.bytTitleMove == 1) {
            this.initFrightingDate();
        } else if (this.bytTitleMove == 2) {
            this.initHonour();
        } else if (this.bytTitleMove == 3) {
            this.initState();
        }
    }

    private void setRoleEquip() {
        this.shtRectUpH = (short) 60;
        this.shtPropRectW = (short) 23;
        this.shtRectUpH = (short) 90;
        this.shtRectDownH = (short) (Macro.SCREEN_HEIGHT - (Macro.FONTHEIGHT * 2 + this.shtRectUpH + 31));
        this.shtRoleRectX = (short) ((Macro.SCREEN_WIDTH - 46) / 2);
        this.shtRoleRectY = (short) (Macro.FONTHEIGHT + 3 + (this.shtRectUpH - 54) / 2);
        this.shtPropRectX = (short) ((Macro.SCREEN_WIDTH - 36) / 2 - 50);
        this.shtPropUpRX = (short) ((Macro.SCREEN_WIDTH + 36) / 2 + 30);
        this.shtPropRectY = (short) (Macro.FONTHEIGHT + 3 + (this.shtRectUpH - 84) / 2);
        this.shtPropRect2X = (short) ((Macro.SCREEN_WIDTH - 57 - 2) / 2);
        this.shtPropRect2Y = (short) (this.shtRoleRectY + 54);
        this.setPropRect((short) (Macro.FONTHEIGHT * 2 + 3 + this.shtRectUpH + 36), this.shtRectDownH);
    }

    private void setTradeEquip() {
        this.shtRectUpH = (short) 60;
        this.shtPropRectW = (short) 23;
        this.shtRectUpH = (short) 90;
        this.shtRectDownH = (short) (Macro.SCREEN_HEIGHT - (Macro.FONTHEIGHT * 2 + this.shtRectUpH + 31));
        this.shtRoleRectX = (short) ((Macro.SCREEN_WIDTH - 46) / 2);
        this.shtRoleRectY = (short) (Macro.FONTHEIGHT + 3 + (this.shtRectUpH - 54) / 2);
        this.shtPropRectX = (short) ((Macro.SCREEN_WIDTH - 36) / 2 - 50);
        this.shtPropUpRX = (short) ((Macro.SCREEN_WIDTH + 36) / 2 + 30);
        this.shtPropRectY = (short) (Macro.FONTHEIGHT + 3 + (this.shtRectUpH - 84) / 2);
        this.shtPropRect2X = (short) ((Macro.SCREEN_WIDTH - 57 - 2) / 2);
        this.shtPropRect2Y = (short) (this.shtRoleRectY + 54);
        this.setPropRect((short) (Macro.FONTHEIGHT * 2 + 3 + this.shtRectUpH + 36 + 15), this.shtRectDownH);
    }

    private void setRoleEquip1() {
        this.shtRectUpH = (short) 60;
        this.shtPropRectW = (short) 23;
        this.shtRectUpH = (short) 90;
        this.shtRectDownH = (short) (Macro.SCREEN_HEIGHT - (Macro.FONTHEIGHT * 2 + this.shtRectUpH + 31));
        this.shtRoleRectX = (short) ((Macro.SCREEN_WIDTH - 46) / 2);
        this.shtRoleRectY = (short) (Macro.FONTHEIGHT + 3 + (this.shtRectUpH - 54) / 2);
        this.shtPropRectX = (short) ((Macro.SCREEN_WIDTH - 36) / 2 - 50);
        this.shtPropUpRX = (short) ((Macro.SCREEN_WIDTH + 36) / 2 + 30);
        this.shtPropRectY = (short) (Macro.FONTHEIGHT + 3 + (this.shtRectUpH - 84) / 2);
        this.shtPropRect2X = (short) ((Macro.SCREEN_WIDTH - 57 - 2) / 2);
        this.shtPropRect2Y = (short) (this.shtRoleRectY + 54);
        this.setPropRect((short) (Macro.FONTHEIGHT * 2 + 3 + this.shtRectUpH + 36), this.shtRectDownH);
    }

    public void drawPackTag(Graphics graphics) {
        graphics.translate(0, this.MoveUp);
        short s = 1;
        short s2 = 4;
        GridTable gridTable = new GridTable((short) 0, (short) 0, Macro.SCREEN_WIDTH, Macro.SCREEN_HEIGHT, s2, s, new short[]{35, 7, 13, 35}, new short[]{100});
        short s3 = 5;
        short s4 = 1;
        GridCell gridCell = gridTable.getCell(3, 1);
        GridTable gridTable2 = new GridTable(gridCell.getCellX(), gridCell.getCellY(), gridCell.getCellW(), gridCell.getCellH(), s4, s3, new short[]{100}, new short[]{23, 18, 18, 18, 23});
        this.tagEquitCell = gridTable2.getCell(1, 1);
        this.tag_equit_x = this.tagEquitCell.getCellX() + this.BORDER_PADDING;
        this.tag_equit_y = this.tagEquitCell.getCellY() + this.BORDER_PADDING * 2;
        this.tag_rect_w = this.tagEquitCell.getCellW() - this.BORDER_PADDING * 4;
        this.tag_rect_h = this.tagEquitCell.getCellH() - this.BORDER_PADDING * 4;
        DrawBase.fillRoundRect(this.tag_equit_x + 10, this.tag_equit_y, this.tag_rect_w, this.tag_rect_h, 6, 6, this.bytTagMove == 0 ? this.selectBgColor : this.tag_color);
        DrawBase.drawRoundRect(this.tag_equit_x + 10, this.tag_equit_y, this.tag_rect_w, this.tag_rect_h, 6, 6, 0xFF8000);
        DrawBase.DrawString("装备", this.tagEquitCell.getCellX(), this.tagEquitCell.getCellY(), this.tagEquitCell.getCenterX() + 8, this.tagEquitCell.getCenterY(), this.tagEquitCell.getCellW(), this.tagEquitCell.getCellH(), (short) 0, 0);
        GridCell gridCell2 = gridTable2.getCell(1, 2);
        this.tag_spe_x = gridCell2.getCellX() + this.BORDER_PADDING;
        DrawBase.fillRoundRect(this.tag_spe_x, this.tag_equit_y, this.tag_rect_w, this.tag_rect_h, 6, 6, this.bytTagMove == 1 ? this.selectBgColor : this.tag_color);
        DrawBase.drawRoundRect(this.tag_spe_x, this.tag_equit_y, this.tag_rect_w, this.tag_rect_h, 6, 6, 0xFF8000);
        DrawBase.DrawString("任务", gridCell2.getCellX(), gridCell2.getCellY(), gridCell2.getCenterX() + 4, gridCell2.getCenterY(), gridCell2.getCellW(), gridCell2.getCellH(), (short) 0, 0);
        GridCell gridCell3 = gridTable2.getCell(1, 3);
        this.tag_data_x = gridCell3.getCellX() + this.BORDER_PADDING;
        DrawBase.fillRoundRect(this.tag_data_x, this.tag_equit_y, this.tag_rect_w, this.tag_rect_h, 6, 6, this.bytTagMove == 2 ? this.selectBgColor : this.tag_color);
        DrawBase.drawRoundRect(this.tag_data_x, this.tag_equit_y, this.tag_rect_w, this.tag_rect_h, 6, 6, 0xFF8000);
        DrawBase.DrawString("材料", gridCell3.getCellX(), gridCell3.getCellY(), gridCell3.getCenterX() + 4, gridCell3.getCenterY(), gridCell3.getCellW(), gridCell3.getCellH(), (short) 0, 0);
        GridCell gridCell4 = gridTable2.getCell(1, 4);
        this.tag_pet_x = gridCell4.getCellX() + this.BORDER_PADDING;
        DrawBase.fillRoundRect(this.tag_pet_x, this.tag_equit_y, this.tag_rect_w, this.tag_rect_h, 6, 6, this.bytTagMove == 3 ? this.selectBgColor : this.tag_color);
        DrawBase.drawRoundRect(this.tag_pet_x, this.tag_equit_y, this.tag_rect_w, this.tag_rect_h, 6, 6, 0xFF8000);
        DrawBase.DrawString("宠物", gridCell4.getCellX(), gridCell4.getCellY(), gridCell4.getCenterX() + 4, gridCell4.getCenterY(), gridCell4.getCellW(), gridCell4.getCellH(), (short) 0, 0);
        GridCell gridCell5 = gridTable2.getCell(1, 5);
        this.tag_potion_x = gridCell5.getCellX() + this.BORDER_PADDING;
        DrawBase.fillRoundRect(this.tag_potion_x, this.tag_equit_y, this.tag_rect_w, this.tag_rect_h, 6, 6, this.bytTagMove == 4 ? this.selectBgColor : this.tag_color);
        DrawBase.drawRoundRect(this.tag_potion_x, this.tag_equit_y, this.tag_rect_w, this.tag_rect_h, 6, 6, 0xFF8000);
        DrawBase.DrawString("消耗", gridCell5.getCellX(), gridCell5.getCellY(), gridCell5.getCenterX() - 3, gridCell5.getCenterY(), gridCell5.getCellW(), gridCell5.getCellH(), (short) 0, 0);
        graphics.translate(0, -this.MoveUp);
    }

    private void tagUseEvent() {
        switch (this.bytTagMove) {
            case 0: {
                DCanvas.getInstance().setNetLoad(true);
                NetSend.getInstance().sendFrameEquip((byte) 1, (byte) 0, 0);
                this.setState((byte) 10, STR_PACK_LIST[0]);
                break;
            }
            case 1: {
                Param.getInstance().EquipIndex = (byte) -1;
                DCanvas.getInstance().setNetLoad(true);
                NetSend.getInstance().sendFrameLeechdom((byte) 1, (byte) 0, 0);
                this.setState((byte) 36, STR_PACK_LIST[4]);
                break;
            }
            case 2: {
                Param.getInstance().EquipIndex = (byte) -1;
                DCanvas.getInstance().setNetLoad(true);
                NetSend.getInstance().sendFrameStuff((byte) 1, (byte) 0, 0);
                this.setState((byte) 37, STR_PACK_LIST[3]);
                break;
            }
            case 3: {
                Param.getInstance().EquipIndex = (byte) -1;
                DCanvas.getInstance().setNetLoad(true);
                NetSend.getInstance().sendFrameTaskprop((byte) 1, (byte) 0, 0);
                this.setState((byte) 38, STR_PACK_LIST[1]);
                break;
            }
            case 4: {
                Param.getInstance().EquipIndex = (byte) -1;
                DCanvas.getInstance().setNetLoad(true);
                NetSend.getInstance().sendFramePeculiar((byte) 1, (byte) 0, 0, -1, (byte) 0);
                this.setState((byte) 39, "特殊物品");
            }
        }
    }

    private void ChangeBodyState(byte by) {
        switch (by) {
            case 1: {
                this.setState((byte) 36, STR_PACK_LIST[4]);
                break;
            }
            case 2: {
                this.setState((byte) 37, STR_PACK_LIST[3]);
                break;
            }
            case 3: {
                this.setState((byte) 38, STR_PACK_LIST[1]);
                break;
            }
            case 4: {
                this.setState((byte) 39, "特殊物品");
            }
        }
    }

    private void tagPetEvent() {
        switch (this.bytTagIndex) {
            case 0: {
                DCanvas.getInstance().setNetLoad(true);
                Param.getInstance().packageBoxMaxNum = Param.getInstance().bytPetBag;
                MenuUI.getInstance().setPetList();
                NetSend.getInstance().sendPetHandle((byte) 10, 0);
                this.setState((byte) 33, "宠　　物");
                break;
            }
            case 1: {
                this.setState((byte) 21, "宠　　物");
                break;
            }
            case 2: {
                this.setState((byte) 22, "宠　　物");
                break;
            }
            case 3: {
                DCanvas.getInstance().setNetLoad(true);
                Param.getInstance().packageBoxMaxNum = Param.getInstance().bytPetBag;
                this.blnTabPet = true;
                NetSend.getInstance().sendPetHandle((byte) 11, 0);
                this.setState((byte) 39, "宠　　物");
                break;
            }
            case 4: {
                DCanvas.getInstance().setNetLoad(true);
                NetSend.getInstance().sendPetPack((byte) 1, 0, (byte) 0, 0);
                Macro.PET_PROP_CHANGE = (short) 2;
                this.setState((byte) 58, "宠　　物");
            }
        }
    }

    public void drawRoleRect(Graphics graphics, String string, String string2, int n, byte by, byte by2, short s, short s2) {
        short s3 = 0;
        short s4 = 0;
        short s5 = 0;
        int n2 = 0;
        short s6 = 0;
        short s7 = 0;
        LayoutStyle.getInstance().drawFullBackGround(13866046);
        LayoutStyle.getInstance().drawEarBox(graphics, string, 9263661, IDefines.GLOBAL_UI_MAIN_TITLEBOX_X, 0, IDefines.GLOBAL_UI_MAIN_TITLEBOX_WIDTH, 30);
        graphics.translate(0, this.MoveUp);
        short s8 = 0;
        while (s8 < 15) {
            Image image;
            PackageObject packageObject;
            switch (s8) {
                case 0:
                case 1:
                case 2:
                case 3: {
                    s5 = (short) (s3 + this.shtPropRectX);
                    n2 = (short) (s4 + this.shtPropRectY + s8 * 21);
                    break;
                }
                case 4:
                case 5:
                case 6:
                case 7: {
                    s5 = (short) (s3 + this.shtPropUpRX);
                    n2 = (short) (s4 + this.shtPropRectY + (s8 - 4) * 21);
                    break;
                }
                case 8:
                case 9:
                case 10: {
                    s5 = (short) (this.shtPropRect2X + (s8 - 8) * 21);
                    n2 = this.shtPropRect2Y;
                }
            }
            if (s8 < 8) {
                DrawBase.drawBox(s5 + 1, n2 + 2, 18, 18, new int[]{13866046, 13866046, 13866046}, true);
            }
            this.drawSmallBackRect(graphics, s5, n2);
            if (s8 < 8) {
                if (Param.getInstance().hPackageEquip != null && Param.getInstance().hPackageEquip.containsKey(new Integer(s8))) {
                    packageObject = (PackageObject) Param.getInstance().hPackageEquip.get(new Integer(s8));
                    image = GameUI.getInstance().getObjectImg(packageObject.shtIcon);
                    if (image != null) {
                        DrawBase.drawImage(image, s5 + 2, n2 + 2, 20);
                    }
                }
            } else if (s8 >= 8 && ORPMe.ME.hPackagePet != null && ORPMe.ME.hPackagePet.containsKey(new Integer(s8 - 8))) {
                packageObject = (PackageObject) ORPMe.ME.hPackagePet.get(new Integer(s8 - 8));
                image = GameUI.getInstance().getObjectImg(packageObject.shtIcon);
                if (image != null) {
                    DrawBase.drawImage(image, s5 + 2, n2 + 2, 20);
                }
            }
            s8 = (byte) (s8 + 1);
        }
        switch (by2) {
            case 0:
            case 1:
            case 2:
            case 3: {
                s6 = (short) (s3 + this.shtPropRectX);
                s7 = (short) (s4 + this.shtPropRectY + by2 * 21);
                break;
            }
            case 4:
            case 5:
            case 6:
            case 7: {
                s6 = (short) (s3 + this.shtPropUpRX);
                s7 = (short) (s4 + this.shtPropRectY + (by2 - 4) * 21);
                break;
            }
            case 8:
            case 9:
            case 10: {
                s6 = (short) (this.shtPropRect2X + (by2 - 8) * 21);
                s7 = this.shtPropRect2Y;
            }
        }
        if (by2 < 11 && by == 0) {
            LayoutStyle.getInstance().drawChooseFrame(graphics, s6 + 1, s7 + 1, 22, 22);
        }
        DrawBase.drawBox(this.shtRoleRectX - 20, this.shtRoleRectY - 15, 85, 70, new int[]{13866046, 13866046, 14995858}, true);
        if (ORPMe.ME.blnPlayerResOK) {
            ORPMe.ME.draw(graphics, Macro.SCREEN_WIDTH / 2, s4 + this.shtRoleRectY + 27 + 20);
        }
        s8 = (short) (Macro.FONTHEIGHT * 2 + 11 + this.shtRectUpH);
        if (this.bytTagMove == 0) {
            this.drawPropRect(graphics, Param.getInstance().hPackage, s8, this.shtRectDownH, by2, s, s2, by);
        }
        graphics.setColor(n);
        graphics.drawString(string2, Macro.SCREEN_WIDTH / 2, s4 + Macro.FONTHEIGHT + this.shtRectUpH + 8, 17);
        graphics.translate(0, -this.MoveUp);
    }

    public void setSelectedButton(Hashtable hashtable, int n) {
        if (hashtable == null) {
            this.setButton(1, 100, 2);
        } else if (hashtable.get(new Integer(n)) != null) {
            if (this.ChooseJewel && this.bytTagMove == 4) {
                this.setButton(1, 0, 2);
            } else {
                this.setButton(1, 4, 2);
            }
        } else {
            this.setButton(1, 100, 2);
        }
    }

    public void drawORoleInfo(Graphics graphics) {
        LayoutStyle.getInstance().drawFullBackGround(13866046);
        DCanvas.getInstance().drawTileTextBG(graphics, Param.getInstance().playerColne.strNickName);
        this.rView.drawViewStyle(graphics, Param.getInstance().playerColne);
        int n = 12;
        int n2 = 157;
        int n3 = Macro.SCREEN_WIDTH - 2 * n;
        int n4 = Macro.SCREEN_HEIGHT - 31 - n2;
        DrawBase.drawBox(n, n2, n3, n4 - 12, new int[]{12026667, 14995858, 14995858, 16642234}, true);
        this.drawWordMove_Y2(graphics, this.strOneDescribeChar, (short) (n + 12), (short) (n2 + 6), (short) (n4 - 24));
    }

    public void drawTagChooseRect(Graphics graphics, short s) {
        int n = this.tag_equit_x + 10;
        int n2 = this.tag_equit_y;
        switch (s) {
            case 0: {
                n = this.tag_equit_x + 10;
                n2 = this.tag_equit_y;
                break;
            }
            case 1: {
                n = this.tag_spe_x;
                n2 = this.tag_equit_y;
                break;
            }
            case 2: {
                n = this.tag_data_x;
                n2 = this.tag_equit_y;
                break;
            }
            case 3: {
                n = this.tag_pet_x;
                n2 = this.tag_equit_y;
                break;
            }
            case 4: {
                n = this.tag_potion_x;
                n2 = this.tag_equit_y;
            }
        }
        DrawBase.fillRoundRect(n, n2 + 15, this.tag_rect_w, this.tag_rect_h, 6, 6, this.selectBgColor);
        DrawBase.drawRoundRect(n, n2 + 15, this.tag_rect_w, this.tag_rect_h, 6, 6, this.tag_color);
    }

    private void drawRoleRect(Graphics graphics, int n, int n2) {
        graphics.setColor(14995858);
        graphics.fillRect(n + 1, n2 + 1, 43, 51);
        graphics.setColor(9986604);
        graphics.drawLine(n + 2, n2, n + 43, n2);
        graphics.drawLine(n + 2, n2 + 53, n + 43, n2 + 53);
        graphics.drawLine(n, n2 + 2, n, n2 + 51);
        graphics.drawLine(n + 45, n2 + 2, n + 45, n2 + 51);
        graphics.setColor(11301955);
        graphics.drawRect(n + 1, n2 + 1, 43, 51);
    }

    private void drawSmallBackRect(Graphics graphics, int n, int n2) {
        graphics.setColor(9986604);
        graphics.drawLine(n + 1, n2, n + 18, n2);
        graphics.drawLine(n + 1, n2 + 19, n + 18, n2 + 19);
        graphics.drawLine(n, n2 + 1, n, n2 + 18);
        graphics.drawLine(n + 19, n2 + 1, n + 19, n2 + 18);
        graphics.setColor(6298376);
        graphics.drawRect(n + 1, n2 + 1, 17, 17);
    }

    public void drawPetRect(Graphics graphics, byte by, byte by2, short s, short s2) {
        int n = 0;
        boolean bl = false;
        int n2 = 0;
        while (n2 < 4) {
            this.drawPetPackRect(graphics, this.shtPetRectX * (n2 + 1) + 19 * n2 + n2 * 9, this.ShtPetRectY + 2 + this.MoveDown);
            if (Param.getInstance().hPetPackEquip != null && Param.getInstance().hPetPackEquip.containsKey(new Integer(n2))) {
                PackageObject packageObject = (PackageObject) Param.getInstance().hPetPackEquip.get(new Integer(n2));
                Image image = GameUI.getInstance().getObjectImg(packageObject.shtIcon);
                DrawBase.drawImage(image, this.shtPetRectX * (n2 + 1) + 19 * n2 + n2 * 9 + 2, this.ShtPetRectY + 4 + this.MoveDown, 20);
            }
            n2 = (byte) (n2 + 1);
        }
        if (by2 != 4 && by == 0) {
            LayoutStyle.getInstance().drawChooseFrame(graphics, n + this.shtPetRectX * (by2 + 1) + 22 * by2 + by2 * 9 + 1, this.ShtPetRectY + 1 + this.MoveDown, 22, 22);
        }
    }

    private void drawPetPackRect(Graphics graphics, int n, int n2) {
        graphics.setColor(9986604);
        graphics.drawLine(n + 1, n2, n + 18, n2);
        graphics.drawLine(n + 1, n2 + 19, n + 18, n2 + 19);
        graphics.drawLine(n, n2 + 1, n, n2 + 18);
        graphics.drawLine(n + 19, n2 + 1, n + 19, n2 + 18);
        graphics.setColor(6298376);
        graphics.drawRect(n + 1, n2 + 1, 17, 17);
    }

    public void setGourdList(Vector vector) {
        if (vector != null && !vector.isEmpty()) {
            int n = 0;
            while (n < vector.size()) {
                GameUI.getInstance().addObjectImg(((CollectionObject) vector.elementAt((int) n)).shtIcon, "prop");
                n = (short) (n + 1);
            }
            Param.getInstance().IntRgbColor = DrawBase.getRGB(StringManager.getNewLineW(), Macro.FONTHEIGHT, 1144385017);
            this.setButton(1, 4, 2);
            this.shtMenuMoveLength = (short) vector.size();
        }
        Param.getInstance().vCommonList = vector;
    }

    public void setMakeList(Vector vector) {
        if (vector != null && !vector.isEmpty()) {
            int n = 0;
            while (n < vector.size()) {
                GameUI.getInstance().addObjectImg(((MakeObject) vector.elementAt((int) n)).shtIcon, "prop");
                n = (short) (n + 1);
            }
            Param.getInstance().IntRgbColor = DrawBase.getRGB(StringManager.getNewLineW(), Macro.FONTHEIGHT, 1144385017);
            this.shtMenuMoveLength = (short) vector.size();
            this.setButton(1, 4, 2);
        }
        Param.getInstance().vCommonList = vector;
    }

    private String getOccEquipName(byte by) {
        StringBuffer stringBuffer = new StringBuffer();
        if (by == 1) {
            stringBuffer.append("布甲");
        } else if (by == 2) {
            stringBuffer.append("布甲");
            stringBuffer.append("、");
            stringBuffer.append("轻甲");
        } else {
            stringBuffer.append("重甲");
            stringBuffer.append("、");
            stringBuffer.append("布甲");
            stringBuffer.append("、");
            stringBuffer.append("轻甲");
        }
        return stringBuffer.toString();
    }

    public boolean ifVectorNull() {
        if (Param.getInstance().vCommonList.isEmpty()) {
            this.shtMenuMoveLength = 0;
            Param.getInstance().vCommonList = null;
            this.setButton(1, 100, 2);
            return false;
        }
        return true;
    }

    private void drawFingerInfo(Graphics graphics, String string, String string2, int n, boolean bl, boolean bl2) {
        short s = 0;
        short s2 = 0;
        boolean bl3 = string2.equals("");
        if (bl) {
            s = (short) ((Macro.FONTHEIGHT > 8 ? Macro.FONTHEIGHT : 8) + 8);
            s2 = (short) ((bl3 ? 0 : Macro.font.stringWidth("等级" + string2) + 8) + string.length() * 7 + 18 + 12);
        } else {
            s = Macro.FONTHEIGHT;
            s2 = (short) (Macro.font.stringWidth(string) + 12);
        }
        if (s2 < 40) {
            s2 = (short) 40;
        }
        short s3 = (short) (n + Macro.FONTHEIGHT);
        if (bl2) {
            s3 = (short) (n - s - 2);
        }
        short s4 = (short) (Macro.SCREEN_WIDTH - 12 - s2);
        GameUI.getInstance().drawChatRect(graphics, s4, s3, s2, s, 40, bl2);
        graphics.setColor(0xFFFFFF);
        if (bl) {
            if (!bl3) {
                graphics.drawString("等级", s4 + 6, s3 + 4, 20);
                GameUI.getInstance().drawMoneyNum(graphics, Integer.parseInt(string2), s4 + 6 + Macro.font.stringWidth("等级") + 3, s3 + (s - 8) / 2);
            }
            this.drawMoney(graphics, Integer.parseInt(string), s4 + 6 + (bl3 ? 0 : Macro.font.stringWidth("等级" + string2) + 6 + 5), s3 + (s - 8) / 2, false);
        } else {
            s4 = (short) (s4 + (s2 - Macro.font.stringWidth(string)) / 2);
            graphics.drawString(string, s4, s3 + 1, 20);
        }
    }

    private void drawPage(Graphics graphics, short s, short s2) {
        short s3 = (short) (Macro.SCREEN_WIDTH / 2);
        GameUI.getInstance().drawMoneyNum(graphics, s, s3 - Integer.toString(s).length() * 7 - 3, Macro.SCREEN_HEIGHT - 12);
        DrawBase.drawRegion(Param.getInstance().imgHMPNum, s3 - 4, Macro.SCREEN_HEIGHT - 12, 55, 0, 5, 8, 0, 20);
        GameUI.getInstance().drawMoneyNum(graphics, s2, s3 + 3, Macro.SCREEN_HEIGHT - 12);
    }

    public void drawMoney(Graphics graphics, int n, int n2, int n3, boolean bl) {
        graphics.setClip(0, 0, Macro.SCREEN_WIDTH, Macro.SCREEN_HEIGHT);
        if (n2 == -1) {
            n2 = (Macro.SCREEN_WIDTH - (Integer.toString(n).length() * 7 + 17)) / 2;
        }
        GameUI.getInstance().drawBullion(graphics, n2, n3);
        if (bl) {
            GameUI.getInstance().drawMoneyNum(graphics, n, n2 + 20, n3 - 2);
        } else {
            GameUI.getInstance().drawWhiteMoneyNum(graphics, n, n2 + 20, n3 - 2);
        }
        DCanvas.getInstance().clearScreen();
    }

    private void drawPoint(Graphics graphics, int n, int n2, int n3, boolean bl) {
        graphics.setClip(0, 0, Macro.SCREEN_WIDTH, Macro.SCREEN_HEIGHT);
        if (n2 == -1) {
            n2 = (Macro.SCREEN_WIDTH - (Integer.toString(n).length() * 7 + 12)) / 2;
        }
        DrawBase.drawShadowString("点数", n2 - 25, n3 - (Macro.FONTHEIGHT >> 1), 0xFF0000, 0xFFF45F, 20);
        if (bl) {
            GameUI.getInstance().drawMoneyNum(graphics, n, n2 + 13, n3 - 1);
        } else {
            GameUI.getInstance().drawWhiteMoneyNum(graphics, n, n2 + 13, n3 - 1);
        }
        DCanvas.getInstance().clearScreen();
    }

    private void setPropRect(short s, short s2) {
        this.shtGrilleX = (short) ((Macro.SCREEN_WIDTH - Macro.PROPRECT_WIDTH * 24) / 2);
        this.shtGrilleY = s;
        Macro.shtRectHeight = s2;
    }

    private void drawPropRect(Graphics graphics, Hashtable hashtable, short s, short s2, byte by, short s3, short s4, byte by2) {
        this.blnGrille = true;
        this.bytPackMaxH = (byte) (s2 / 24);
        this.bytGrilleRow = (byte) (Param.getInstance().packageBoxMaxNum / Macro.PROPRECT_WIDTH);
        byte by3 = (byte) (s4 * Macro.PROPRECT_WIDTH);
        byte by4 = 0;
        if (this.bytGrilleRow > this.bytPackMaxH) {
            by4 = this.bytPackMaxH;
            this.bytGrilleRow = (byte) (this.bytGrilleRow + 1);
        } else {
            by4 = (byte) ((Param.getInstance().packageBoxMaxNum % Macro.PROPRECT_WIDTH == 0 ? (byte) 0 : 1) + this.bytGrilleRow);
            if (by4 > this.bytPackMaxH) {
                by4 = this.bytPackMaxH;
                this.bytGrilleRow = (byte) (this.bytGrilleRow + 1);
            }
        }
        byte by5 = 0;
        while (by5 < by4) {
            byte by6 = 0;
            while (by6 < Macro.PROPRECT_WIDTH) {
                byte by7;
                if ((by5 + this.shtOneMenuRoll) * Macro.PROPRECT_WIDTH + by6 >= Param.getInstance().packageBoxMaxNum) {
                    break;
                }
                int n = 22;
                int n2 = 22;
                int n3 = this.shtGrilleX + by6 * (n + 2);
                int n4 = this.shtGrilleY + by5 * (n2 + 2);
                DrawBase.drawBox(n3, n4, n, n2, IDefines.GLOBAL_UI_PACKAGE_BOX_BORDER, true);
                if (hashtable != null && hashtable.containsKey(new Integer(by7 = (byte) (by5 * Macro.PROPRECT_WIDTH + by6 + by3)))) {
                    int n5 = 16;
                    int n6 = 16;
                    int n7 = n3 + (n - n5 >> 1);
                    int n8 = n4 + (n2 - n6 >> 1);
                    PackageObject packageObject = (PackageObject) hashtable.get(new Integer(by7));
                    ImageManager.getInstance().drawPropAndNum(graphics, packageObject.shtIcon, packageObject.shtPOnum, n7, n8);
                }
                by6 = (byte) (by6 + 1);
            }
            by5 = (byte) (by5 + 1);
        }
        if (Param.getInstance().packageBoxMaxNum != 0 && by2 == 1 && this.blnCursor) {
            LayoutStyle.getInstance().drawChooseFrame(graphics, this.shtGrilleX + by * 24, this.shtGrilleY + s3 * 24, 24, 24);
        }
        if (this.intShopMoney != 0) {
            this.drawShopMoney(graphics, by, s3);
        }
        DCanvas.getInstance().setOptionSpoolr(graphics, Macro.SCREEN_WIDTH - 13 - 3, s, s + s2, this.bytPackMaxH, this.bytGrilleRow, s3 + s4, false);
    }

    public void drawShopMoney(Graphics graphics, int n, int n2) {
        int n3 = Integer.toString(this.intShopMoney).length() * 7 + 17 + 8;
        int n4 = this.shtGrilleX + (n + 1) * 19;
        int n5 = n4 - 8;
        int n6 = this.shtGrilleY + (n2 + 1) * 19 - 2;
        if (n4 + n3 > Macro.SCREEN_WIDTH - 12) {
            n5 = n4 - 19 - 12 >= n3 ? n4 - 19 - n3 + 8 : Macro.SCREEN_WIDTH - 12 - n3;
        }
        if (n2 == this.bytPackMaxH - 1) {
            n6 = this.shtGrilleY + (n2 - 1) * 19 - 1;
        }
        GameUI.getInstance().drawChatRect(graphics, n5 - 1, n6 + 1, n3, 18, 0, false);
    }

    public void drawOption(Graphics graphics, byte by, Vector vector, short s, short s2, short s3, short s4, byte by2) {
        this.drawOption(graphics, by, vector, s, s2, s3, s4, by2, Macro.FONTHEIGHT);
    }

    public void drawOption(final Graphics graphics, final byte b, final Vector vector, final short n, final short n2, final short n3, final short n4, final byte b2, final int n5) {
        if (vector == null) {
            return;
        }
        if (vector.isEmpty()) {
            return;
        }
        short n6 = n4;
        this.scroll_OFF = (DCanvas.getInstance().blnSpoolr ? ScrollText.arrowhead_width : 0);
        LayoutStyle.getInstance().drawSelectBox(n, n2 + n3 * n5, StringManager.getNewLineW() - this.scroll_OFF + 4, n5);
        final short n7 = (short) (n + 2);
        int size = b2;
        byte b3 = Macro.FONTHEIGHT;
        if (size > vector.size()) {
            size = vector.size();
            n6 = 0;
            b3 = 0;
        }
        for (short n8 = 0; n8 < size; ++n8) {
            switch (b) {
                case 63: {
                    if (this.bytTitleMove == 0) {
                        this.drawTaskImageOption(graphics, (TaskObject) vector.elementAt(n8 + n6), n7, (short) (n2 + n8 * Macro.FONTHEIGHT), (byte) 20);
                        break;
                    }
                    this.drawMayTaskImageOption(graphics, (TaskObject) vector.elementAt(n8 + n6), n7, (short) (n2 + n8 * Macro.FONTHEIGHT), (byte) 20);
                    break;
                }
                case -51: {
                    if (b3 == 0) {
                        b3 = Macro.FONTHEIGHT;
                    }
                    this.drawRankMemberList(graphics, (TeamObject) vector.elementAt(n8 + n6), n7, (short) (n2 + n8 * b3), n8, n3);
                    break;
                }
                case 64: {
                    this.drawMayTaskImageOption(graphics, (TaskObject) vector.elementAt(n8 + n6), n7, (short) (n2 + n8 * Macro.FONTHEIGHT), (byte) 20);
                    break;
                }
                case 61: {
                    if (this.bytTitleMove == 0 || this.bytTitleMove == 1) {
                        this.drawSkillImageOption(graphics, (SkillObject) vector.elementAt(n8 + n6), n7, (short) (n2 + n8 * Macro.FONTHEIGHT), (byte) 20, true, n8, n3);
                        break;
                    }
                    if (this.bytTitleMove == 2) {
                        this.drawSkillBookImageOption(graphics, (PackageObject) vector.elementAt(n8 + n6), n7, (short) (n2 + n8 * n5), (byte) 20, true);
                        break;
                    }
                    break;
                }
                case -113:
                case -46: {
                    this.drawSkillImageOption(graphics, (SkillObject) vector.elementAt(n8 + n6), n7, (short) (n2 + n8 * Macro.FONTHEIGHT), (byte) 20, false, n8, n3);
                    break;
                }
                case -44:
                case -36: {
                    this.drawMailImageOption(graphics, (MailObject) vector.elementAt(n8 + n6), n7, (short) (n2 + n8 * Macro.FONTHEIGHT), (byte) 20, n8, n3);
                    break;
                }
                case -38:
                case 35: {
                    if (b3 == 0) {
                        b3 = Macro.FONTHEIGHT;
                    }
                    final TeamObject teamObject = (TeamObject) vector.elementAt(n8 + n6);
                    if (this.bytChatTeam == 0 && teamObject.bytTeamId == 1) {
                        GameUI.getInstance().drawTeamImageOption(graphics, teamObject, n7, (short) (n2 + this.bytChatTeam1NumIndex * b3), (byte) 20);
                        ++this.bytChatTeam1NumIndex;
                    }
                    if (this.bytChatTeam == 1 && teamObject.bytTeamId == 2) {
                        GameUI.getInstance().drawTeamImageOption(graphics, teamObject, n7, (short) (n2 + this.bytChatTeam2NumIndex * b3), (byte) 20);
                        ++this.bytChatTeam2NumIndex;
                        break;
                    }
                    break;
                }
                case 34: {
                    if (b3 == 0) {
                        b3 = Macro.FONTHEIGHT;
                    }
                    this.drawFactionMemberList(graphics, (TeamObject) vector.elementAt(n8 + n6), n7, (short) (n2 + n8 * b3), n8, n3);
                    break;
                }
                case -48: {
                    if (b3 == 0) {
                        b3 = Macro.FONTHEIGHT;
                    }
                    final TeamObject teamObject2 = (TeamObject) vector.elementAt(n8 + n6);
                    if (this.intRelation == 0 && teamObject2.bytRelation == 1) {
                        this.drawGameRelation(graphics, teamObject2, (short) (n2 + MenuUI.bytChatMasterListIndexOne * Macro.FONTHEIGHT), n8, n3);
                        ++MenuUI.bytChatMasterListIndexOne;
                    }
                    if (this.intRelation == 1 && teamObject2.bytRelation == 2) {
                        this.drawGameRelation(graphics, teamObject2, (short) (n2 + MenuUI.bytChatMasterListIndexTwo * Macro.FONTHEIGHT), n8, n3);
                        ++MenuUI.bytChatMasterListIndexTwo;
                        break;
                    }
                    break;
                }
                case -16: {
                    graphics.setColor(0);
                    StringManager.drawWordRightToLeft(((String[]) (Object) vector.elementAt(n8 + n6))[3], n7, n2 + n8 * Macro.FONTHEIGHT, Macro.SCREEN_WIDTH - 24, 0, 1, true);
                    break;
                }
                case -15: {
                    graphics.setColor(0);
                    StringManager.drawWordRightToLeft(((String[]) (Object) vector.elementAt(n8 + n6))[0], n7, n2 + n8 * Macro.FONTHEIGHT, Macro.SCREEN_WIDTH - 24, 0, 1, true);
                    break;
                }
                case -91: {
                    graphics.setColor(0);
                    graphics.drawString((String) vector.elementAt(n8 + n6), n7, n2 + n8 * Macro.FONTHEIGHT, 20);
                    break;
                }
                case -34: {
                    graphics.setColor(0);
                    graphics.drawString(((String[]) (Object) vector.elementAt(n8 + n6))[0], n7, n2 + n8 * Macro.FONTHEIGHT, 20);
                    break;
                }
                default: {
                    this.drawPackageImageOption(graphics, (PackageObject) vector.elementAt(n8 + n6), n7, (short) (n2 + n8 * Macro.FONTHEIGHT), (byte) 20);
                    break;
                }
            }
        }
        this.bytChatTeam1NumIndex = 0;
        this.bytChatTeam2NumIndex = 0;
        MenuUI.bytChatMasterListIndexOne = 0;
        MenuUI.bytChatMasterListIndexTwo = 0;
    }

    private void drawPackageImageOption(Graphics graphics, PackageObject packageObject, short s, short s2, byte by) {
        Image image = GameUI.getInstance().getObjectImg(packageObject.shtIcon);
        if (image != null) {
            GameUI.getInstance().drawOptionImage(graphics, image, s, s2, by);
        }
        if (packageObject.shtPOnum > 1) {
            GameUI.getInstance().drawWhiteNum(graphics, packageObject.shtPOnum, s, s2 + 11);
        }
        graphics.setColor(packageObject.intColor);
        graphics.drawString(packageObject.strName, s + 20, s2, by);
    }

    private void drawCollectionOption(Graphics graphics, CollectionObject collectionObject, short s, short s2, byte by) {
        Image image = GameUI.getInstance().getObjectImg(collectionObject.shtIcon);
        if (image != null) {
            GameUI.getInstance().drawOptionImage(graphics, image, s, s2, by);
            graphics.setColor(Macro.INT_PROP_COLOR[1]);
            graphics.drawString(collectionObject.strName, s + 20, s2, by);
            graphics.drawString("x" + collectionObject.bytNum, Macro.SCREEN_WIDTH - 12, s2, 24);
        }
    }

    private void drawMakeImageOption(Graphics graphics, MakeObject makeObject, short s, short s2, byte by) {
        Image image = GameUI.getInstance().getObjectImg(makeObject.shtIcon);
        if (image != null) {
            GameUI.getInstance().drawOptionImage(graphics, image, s, s2, by);
        }
        graphics.setColor(makeObject.intColor);
        graphics.drawString(makeObject.strName, s + 20, s2, by);
        if (this.bytMenuState == -44 && makeObject.shtNum > 0) {
            graphics.setColor(0);
            graphics.drawString("" + makeObject.shtNum, Macro.SCREEN_WIDTH - 12 - 2, s2, 24);
        }
    }

    private void drawTaskImageOption(Graphics graphics, TaskObject taskObject, short s, short s2, byte by) {
        this.scroll_OFF = DCanvas.getInstance().blnSpoolr ? ScrollText.arrowhead_width : (byte) 0;
        graphics.setColor(this.getTaskColor(taskObject.shtLevel));
        graphics.drawString(taskObject.strName, s, s2, 20);
        if (taskObject.bnlOverType && Param.getInstance().imgTaskThru != null) {
            GameUI.getInstance().drawOptionImage(graphics, Param.getInstance().imgTaskThru, (short) (Macro.SCREEN_WIDTH - 12 - this.scroll_OFF), s2, (byte) 24);
        }
    }

    private void drawMayTaskImageOption(Graphics graphics, TaskObject taskObject, short s, short s2, byte by) {
        this.scroll_OFF = DCanvas.getInstance().blnSpoolr ? ScrollText.arrowhead_width : (byte) 0;
        graphics.setColor(623372);
        graphics.drawString(taskObject.strName, s, s2, 20);
        graphics.setColor(0);
        graphics.drawString(taskObject.strInfo, Macro.SCREEN_WIDTH - 12 - this.scroll_OFF, s2, 24);
    }

    private void drawSkillBookImageOption(Graphics graphics, PackageObject packageObject, short s, short s2, byte by, boolean bl) {
        DrawBase.drawBox(s, s2, IDefines.SKILL_FIGHT_BOOK_NONE_BOX_WIDTH, 25, IDefines.KILL_FIGHT_BOOK_NONE_BORDER, false);
        if (packageObject.intId == 0) {
            DrawBase.setFont(Macro.font);
            DrawBase.DrawString("可镶嵌", s, s2, IDefines.SKILL_FIGHT_BOOK_NONE_BOX_WIDTH, 25, (short) 3, 0);
        } else {
            Image image = GameUI.getInstance().getObjectImg(packageObject.shtIcon);
            if (image != null) {
                GameUI.getInstance().drawOptionImage(graphics, image, (short) (s + 0), s2, by);
            }
            DrawBase.DrawString(packageObject.strName, (short) (s + IDefines.SKILL_FIGHT_BOOK_ATTRI_NAME_X), s2, IDefines.SKILL_FIGHT_BOOK_NONE_BOX_WIDTH - IDefines.SKILL_FIGHT_BOOK_ATTRI_NAME_X, 25, (short) 6, 0);
            DrawBase.DrawString("品质:" + packageObject.bytQuality, (short) (s + IDefines.SKILL_FIGHT_BOOK_ATTRI_QUALITY_X), s2, IDefines.SKILL_FIGHT_BOOK_NONE_BOX_WIDTH - IDefines.SKILL_FIGHT_BOOK_ATTRI_QUALITY_X, 25, (short) 6, 0);
            DrawBase.DrawString("+" + packageObject.shtAddition, s, s2, IDefines.SKILL_FIGHT_BOOK_NONE_BOX_WIDTH, 25, (short) 10, 0);
        }
    }

    private void drawSkillImageOption(Graphics graphics, SkillObject skillObject, short s, short s2, byte by, boolean bl, short s3, short s4) {
        this.scroll_OFF = DCanvas.getInstance().blnSpoolr ? ScrollText.arrowhead_width : (byte) 0;
        Image image = GameUI.getInstance().getObjectImg(skillObject.shtIcon);
        if (image != null) {
            DrawBase.drawImage(image, s + 0, s2, by);
        }
        graphics.setColor(0);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(skillObject.strName);
        int n = (Macro.SCREEN_WIDTH >> 1) - 12 - Macro.FONTW - 20;
        StringManager.drawWordRightToLeft(stringBuffer.toString(), (short) (s + 20), s2, n, 0, 1, s4 == s3);
        DrawBase.DrawString(IDefines.SKILL_FIGHT_SKILL_PASSIVITY[skillObject.blnPassivity ? 1 : 0], (short) (s + IDefines.SKILL_FIGHT_SKILL_ATTRI_TYPE_X - this.scroll_OFF), s2, IDefines.SKILL_FIGHT_BOOK_NONE_BOX_WIDTH - IDefines.SKILL_FIGHT_SKILL_ATTRI_TYPE_X, 25, (short) 20, 0);
        DrawBase.DrawString(String.valueOf(skillObject.bytLv), (short) (s + IDefines.SKILL_FIGHT_SKILL_ATTRI_LEVEL_X - this.scroll_OFF), s2, IDefines.SKILL_FIGHT_BOOK_NONE_BOX_WIDTH - IDefines.SKILL_FIGHT_SKILL_ATTRI_LEVEL_X, 25, (short) 20, 0);
        String string = "";
        if (bl) {
            if (skillObject.bytKey == -1) {
                if (skillObject.bytLv <= 0) {
                    string = "chưa";
                }
            } else {
                string = GameUI.getInstance().getStringShortcutKey(skillObject.bytKey, true);
            }
        }
        DrawBase.DrawString(string, (short) (s + IDefines.SKILL_FIGHT_SKILL_ATTRI_KEY_X - this.scroll_OFF), s2, IDefines.SKILL_FIGHT_BOOK_NONE_BOX_WIDTH - IDefines.SKILL_FIGHT_SKILL_ATTRI_KEY_X, 25, (short) 17, 0);
        graphics.setColor(5190917);
        graphics.drawRect(s, s2, 15, 15);
    }

    private void drawMailImageOption(Graphics graphics, MailObject mailObject, short n, short s, byte by, short s2, short s3) {
        int n2 = n;
        int n3 = 16;
        this.scroll_OFF = DCanvas.getInstance().blnSpoolr ? ScrollText.arrowhead_width : (byte) 0;
        int n4 = 0;
        n4 = mailObject.bytType == 0 ? (mailObject.blnIsRead ? 10841710 : 0xFF0000) : (mailObject.blnIsRead ? Macro.INT_PROP_COLOR[0] : 0);
        int n5 = (Macro.SCREEN_WIDTH >> 1) - 12;
        if (mailObject.bytType == 0 && Param.getInstance().imgEmallSystem != null) {
            GameUI.getInstance().drawOptionImage(graphics, Param.getInstance().imgEmallSystem, (short) (n2 + n3), s, (byte) 24);
        }
        StringManager.drawWordRightToLeft("chức vụ：" + mailObject.strTitle, mailObject.bytType == 0 ? n2 + n3 : n2, s, n5, n4, 1, s3 == s2);
        DrawBase.drawString(mailObject.strTime, 6 + (Macro.SCREEN_WIDTH - 12) - 6 - this.scroll_OFF, s, n4, 24);
    }

    public int getTaskColor(short s) {
        short s2 = (short) (s - ORPMe.ME.shtLevel);
        if (s2 < -5) {
            return Macro.INT_PROP_COLOR[0];
        }
        if (s2 > 3) {
            return 0xFF0000;
        }
        return 623372;
    }

    private void initWordRoll(String string, short s) {
        this.shtWordRollStrW = (short) Macro.font.stringWidth(string);
        this.shtWordRollShowW = s;
        this.blnWordRollType = this.shtWordRollStrW > this.shtWordRollShowW;
        this.shtWordRollX = 0;
    }

    private void drawWordRoll(Graphics graphics, short s, String string, short s2, String string2, int n, short s3, short s4, boolean bl) {
        Image image;
        s3 = (short) (s3 + 1);
        if (s2 > 0 && (image = GameUI.getInstance().getObjectImg(s2)) != null) {
            GameUI.getInstance().drawOptionImage(graphics, image, (short) (s3 - image.getWidth()), s4, (byte) 20);
            s3 = (short) (s3 + 10);
        }
        graphics.setClip(s3, 0, this.shtWordRollShowW - 6, Macro.SCREEN_HEIGHT);
        graphics.setColor(n);
        graphics.drawString(string2, s3 + (s == this.getOneMove() ? this.shtWordRollX : (short) 0), s4, 20);
        DCanvas.getInstance().clearScreen();
        if (bl && string != null) {
            graphics.drawString(string, Macro.SCREEN_WIDTH - Macro.font.stringWidth(string) - 15, s4, 20);
        }
    }

    private void logicWordRoll() {
        short s = (short) (this.shtWordRollStrW - this.shtWordRollShowW);
        this.shtWordRollX = (short) (this.shtWordRollX - 2);
        if (this.shtWordRollX <= -(s + this.shtWordRollShowW)) {
            this.shtWordRollX = this.shtWordRollShowW;
        }
    }

    public void drawFullTiTle(Graphics graphics, String string, int n, int n2, int n3) {
        DCanvas.getInstance().drawmenuBack(graphics, n, Macro.FONTHEIGHT, n2, n3);
        int n4 = 70;
        boolean bl = false;
        if (n4 < Macro.font.stringWidth(string)) {
            n4 = Macro.font.stringWidth(string);
        }
        LayoutStyle.getInstance().drawEarBox(graphics, string, 9263661, IDefines.GLOBAL_UI_MAIN_TITLEBOX_X, 0, IDefines.GLOBAL_UI_MAIN_TITLEBOX_WIDTH, 30);
    }

    public boolean getShopFull() {
        if (Param.getInstance().hShopPackage == null) {
            return false;
        }
        byte by = 0;
        Enumeration enumeration = Param.getInstance().hShopPackage.elements();
        while (enumeration.hasMoreElements()) {
            enumeration.nextElement();
            by = (byte) (by + 1);
        }
        return by >= Param.getInstance().bytShopBag;
    }

    public void addShopProp(PackageObject packageObject, byte by) {
        byte by2;
        if (Param.getInstance().bytsShopBagType == null) {
            Param.getInstance().bytsShopBagType = new byte[1];
            Param.getInstance().bytsShopBagType[0] = by;
        } else {
            by2 = -1;
            int n = 0;
            while (n < Param.getInstance().bytsShopBagType.length) {
                if (Param.getInstance().bytsShopBagType[n] == by) {
                    by2 = 1;
                    break;
                }
                n = (byte) (n + 1);
            }
            if (by2 == -1) {
                byte[] byArray = Param.getInstance().bytsShopBagType;
                Param.getInstance().bytsShopBagType = new byte[byArray.length + 1];
                System.arraycopy(byArray, 0, Param.getInstance().bytsShopBagType, 0, byArray.length);
                Param.getInstance().bytsShopBagType[byArray.length] = by;
            }
        }
        if (Param.getInstance().hShopPackage == null) {
            Param.getInstance().hShopPackage = new Hashtable();
            this.bytAddShopNumber = 0;
        }
        this.bytAddShopNumber = (byte) (this.bytAddShopNumber + 1);
        by2 = 0;
        while (by2 < Param.getInstance().bytShopBag) {
            if (!Param.getInstance().hShopPackage.containsKey(new Integer(by2))) {
                packageObject.intPOindex = by2;
                Param.getInstance().hShopPackage.put(new Integer(by2), packageObject);
                break;
            }
            by2 = (byte) (by2 + 1);
        }
    }

    public void buyShop() {
        String string = ((PackageObject) Param.getInstance().hShopPackage.get((Object) new Integer((int) MenuUI.getInstance().personalShopSellPackage.getCellBoxIndex()))).strName;
        int n = ((PackageObject) Param.getInstance().hShopPackage.get((Object) new Integer((int) MenuUI.getInstance().personalShopSellPackage.getCellBoxIndex()))).intColor;
        int n2 = ((PackageObject) Param.getInstance().hShopPackage.get((Object) new Integer((int) MenuUI.getInstance().personalShopSellPackage.getCellBoxIndex()))).intPaiMaiPrice;
        short s = ((PackageObject) Param.getInstance().hShopPackage.get((Object) new Integer((int) MenuUI.getInstance().personalShopSellPackage.getCellBoxIndex()))).shtPOnum;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Bạn thực sự muốn tiêu ");
        stringBuffer.append(n2);
        stringBuffer.append(" vàng để mua");
        if (s > 0) {
            stringBuffer.append(s + " vật phẩm");
        }
        stringBuffer.append(string);
        stringBuffer.append(" không?");
        GameControl.getInstance().CreateState((byte) 7);
        DialogUI.getInstance().setDialog((byte) 121, stringBuffer.toString(), (byte) 2);
    }

    public void mallShop() {
        PackageObject packageObject = (PackageObject) ((Hashtable) Param.getInstance().hMallPackage.get(new Integer(this.tabStyleInstance.getTabCurrentIndex()))).get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()));
        String string = packageObject.strName;
        int n = packageObject.intColor;
        int n2 = packageObject.intPaiMaiPrice;
        short s = packageObject.shtPOnum;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Bạn thực sự muốn tiêu ");
        stringBuffer.append(n2 * Param.getInstance().intMallNum);
        stringBuffer.append(" xu để mua");
        if (s > 0) {
            stringBuffer.append(Param.getInstance().intMallNum + " vật phẩm");
        }
        stringBuffer.append(string);
        stringBuffer.append(" không？");
        GameControl.getInstance().CreateState((byte) 7);
        DialogUI.getInstance().setDialog((byte) 109, stringBuffer.toString(), (byte) 2);
    }

    public void shopNPC() {
        Hashtable hashtable = null;
        PackageObject packageObject = null;
        byte by = 0;
        switch (Macro.NPC_STEP_SELECT) {
            case 2: {
                byte by2 = Param.getInstance().npcShopBarTabStyle.getTabCurrentIndex();
                hashtable = (Hashtable) Param.getInstance().hNpcBarPackTable.get(new Integer(by2));
                by = Param.getInstance().npcShopBarPackage.getCellBoxIndex();
                if (hashtable == null || !hashtable.containsKey(new Integer(by))) {
                    break;
                }
                packageObject = (PackageObject) hashtable.get(new Integer(by));
                String string = packageObject.strName;
                int n = packageObject.intColor;
                int n2 = packageObject.intPrice;
                short s = packageObject.shtPOnum;
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Bạn thực sự muốn tiêu ");
                stringBuffer.append(n2 * Param.getInstance().intMallNum);
                stringBuffer.append(" vàng để mua");
                stringBuffer.append(Param.getInstance().intMallNum);
                stringBuffer.append(" vật phẩm");
                stringBuffer.append(string);
                stringBuffer.append(" không？");
                GameControl.getInstance().CreateState((byte) 7);
                DialogUI.getInstance().setDialog((byte) 114, stringBuffer.toString(), (byte) 2);
                break;
            }
            case 3: {
                String string = Param.getInstance().npcShopOursTabArray[MenuUI.getInstance().tabStyleInstance.getTabCurrentIndex()];
                hashtable = (Hashtable) Param.getInstance().hNpcOursPackTable.get(string);
                by = Param.getInstance().personalPackage.getCellBoxIndex();
                boolean bl = false;
                if (hashtable == null || !hashtable.containsKey(new Integer(by))) {
                    break;
                }
                packageObject = (PackageObject) hashtable.get(new Integer(by));
                String string2 = packageObject.strName;
                int n = packageObject.intColor;
                int n3 = packageObject.intPrice;
                short s = packageObject.shtPOnum;
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Bạn thực sự muốn bán");
                if (s > 0) {
                    stringBuffer.append(s);
                    stringBuffer.append(" vật phẩm");
                }
                stringBuffer.append(n3);
                stringBuffer.append(" vàng");
                stringBuffer.append(string2);
                stringBuffer.append(" không？");
                GameControl.getInstance().CreateState((byte) 7);
                DialogUI.getInstance().setDialog((byte) 114, stringBuffer.toString(), (byte) 2);
                break;
            }
        }
    }

    private void initSet() {
        if (this.triangle == null) {
            this.triangle = new QSprite[2];
            int n = 0;
            while (n < this.triangle.length) {
                this.triangle[n] = ImageManager.loadSpriteById(1, ImageManager.EncodespriteId("littlearrow", String.valueOf(n)), "littlearrow", "littlearrow", "loading");
                ++n;
            }
            this.triangle[0].setAnimation(1);
            this.triangle[1].setAnimation(3);
        }
        this.setData = new byte[7];
        this.setData[0] = (byte) (Rms.blnChatWChannel ? 1 : 2);
        this.setData[1] = (byte) (Rms.blnWeather ? 1 : 2);
        this.setData[2] = (byte) (Rms.blnShowRole ? 2 : 1);
        this.setData[3] = (byte) (Rms.bytLevalFunctionFlag + 1);
        this.setData[4] = (byte) (Rms.bytShowName + 1);
        this.setData[5] = (byte) (Rms.blnAutoWalk ? 1 : 2);
        this.setData[6] = (byte) (Rms.blnAutoSelect ? 1 : 2);
        this.bytSetChannel = (byte) (this.setData[0] - 1);
        this.bytSetWeather = (byte) (this.setData[1] - 1);
        this.bytSetShowRole = (byte) (this.setData[2] - 1);
        this.bytSetShow = (byte) (this.setData[3] - 1);
        this.bytShowName = (byte) (this.setData[4] - 1);
        this.bytAutoWalk = (byte) (this.setData[5] - 1);
        this.bytAutoSelect = (byte) (this.setData[6] - 1);
        DebugFrame.getInstance().logIn("blnBusiness:" + Rms.blnBusiness);
    }

    private void initShortcut() {
        this.hotkeyX = GameUI.getInstance().shtDownHotKeyXOff;
        this.clearMove();
        int n = Macro.FONTHEIGHT + 2;
        short s = (short) (44 + (23 + Macro.FONTHEIGHT + 4) + n);
        short s2 = (short) (Macro.SCREEN_WIDTH - 12);
        this.tabStyleInstance.initTabStyle((short) 6, s, s2, (short) 32, IDefines.SHORTCUT_UI_TAB, TabState);
        this.tabStyleInstance.TabFocuse = true;
        if (TabState == 0) {
            if (Param.getInstance().vSkillList != null) {
                this.HashSkillList = new Hashtable();
                int n2 = 0;
                int n3 = 0;
                while (n2 < Param.getInstance().vSkillList.size()) {
                    SkillObject skillObject = (SkillObject) Param.getInstance().vSkillList.elementAt(n2);
                    if (skillObject.bytLv > 0) {
                        this.HashSkillList.put(new Integer(n3), skillObject);
                        ++n3;
                        GameUI.getInstance().addObjectImg(skillObject.shtIcon, "prop");
                    }
                    ++n2;
                }
                this.GetContainer = Param.getInstance().vSkillList.size();
            } else {
                this.GetContainer = 24;
            }
        } else if (TabState == 3) {
            if (ORPMe.ME.hPackagePet != null) {
                this.GetContainer = ORPMe.ME.hPackagePet.size();
            }
        } else if (TabState == 1 || TabState == 4) {
            this.GetContainer = Param.getInstance().packageBoxMaxNum;
        } else if (this.bytsFunction != null) {
            this.HashFunction = new Hashtable();
            int n4 = 0;
            while (n4 < this.bytsFunction.length) {
                PackageObject packageObject = new PackageObject();
                packageObject.intPOindex = n4;
                packageObject.strName = this.strsFunction[n4];
                packageObject.shtIcon = this.FunctionPicID[n4];
                GameUI.getInstance().addObjectImg(packageObject.shtIcon, "sys");
                this.HashFunction.put(new Integer(packageObject.intPOindex), packageObject);
                ++n4;
            }
            this.GetContainer = this.bytsFunction.length;
        }
        this.boxNum = this.GetContainer <= 24 ? 24 : this.GetContainer + (8 - this.GetContainer % 8);
        this._holex = 12;
        this._holey = s + 32 + 2;
        this._holew = Macro.SCREEN_WIDTH - 24;
        this._holeh = Macro.SCREEN_HEIGHT - this._holey - 31 - Macro.FONTHEIGHT;
        this.Shortcut = new PackageBox(this._holex, this._holey, this._holew, this._holeh, this.boxNum, false);
        this.setButton(1, 0, 2);
    }

    public void initKey() {
        this.bytsFunction = Pram.SystemKeyID;
        this.FunctionPicID = new byte[this.bytsFunction.length];
        this.strsFunction = new String[this.bytsFunction.length];
        int n = 0;
        while (n < this.bytsFunction.length) {
            this.strsFunction[n] = Pram.SystemKeyName[n];
            this.FunctionPicID[n] = Pram.KEYFUNCTION_PNG_ID[n];
            n = (byte) (n + 1);
        }
    }

    private void clearShortcut() {
        Param.getInstance().hImgObject = null;
        TabState = 0;
        this.HashSkillList = null;
        this.HashFunction = null;
        this.Shortcut = null;
        this.strsFunction = null;
        Param.getInstance().IntRgbColor = null;
        Param.getInstance().hImgObject = null;
    }

    private void drawShortcut(Graphics graphics) {
        DCanvas.getInstance().drawTileTextBG(graphics, "Cài đặt phím tắt");
        int n = 12;
        int n2 = Macro.SCREEN_WIDTH - 24;
        int n3 = 44;
        int n4 = 23 + Macro.FONTHEIGHT + 4;
        this.hotkeyY = n3 + 2;
        DrawBase.drawBox(n, n3, n2, n4, new int[]{12026667, 14995858, 14995858, 16642234}, true);
        String string = DCanvas.getInstance().blnIsTouch ? "" : "Nhấn \"phím 9\" để chuyển thanh phím tắt";
        DrawBase.drawString(string, Macro.SCREEN_WIDTH >> 1, this.hotkeyY + 23 + 1, 9263661, 17);
        String string2 = DCanvas.getInstance().blnIsTouch ? "Nhấp vào khu vực phím tắt tương ứng để đặt phím tắt" : "Nhấn phím số tương ứng để đặt phím tắt";
        StringManager.drawWordRightToLeft(string2, this._holex, Macro.SCREEN_HEIGHT - 31 - Macro.FONTHEIGHT - 4, Macro.SCREEN_WIDTH - 24, 9263661, 0, true);
        GameUI.getInstance().drawChangeKey(graphics, this.hotkeyX, this.hotkeyY + 23);
        GameUI.getInstance().drawShortcutKeys(graphics, this.hotkeyX, this.hotkeyY + 23, GameUI.getInstance().bytShortcutKeysType);
        this.tabStyleInstance.drawTabStyle(graphics);
        if (TabState == 0) {
            this.Shortcut.draw(this.HashSkillList);
        } else if (TabState == 3) {
            this.Shortcut.draw(ORPMe.ME.hPackagePet);
        } else if (TabState == 1 || TabState == 4) {
            this.Shortcut.draw(Param.getInstance().hPackage);
        } else if (TabState == 2) {
            this.Shortcut.draw(this.HashFunction);
        }
    }

    private void ChangeTabState() {
        byte by = this.tabStyleInstance.getTabCurrentIndex();
        if (TabState != by) {
            TabState = by;
            switch (TabState) {
                case 0: {
                    break;
                }
                case 1: {
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendFrameLeechdom((byte) 1, (byte) 0, 0);
                    break;
                }
                case 2: {
                    break;
                }
                case 3: {
                    break;
                }
                case 4: {
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendFramePeculiar((byte) 1, (byte) 0, 0, -1, (byte) 0);
                }
            }
            this.initShortcut();
        }
    }

    private void SetShortcutKey() {
        if (DCanvas.getInstance().isKeyDown(2)) {
            this.KeyNum = 0;
            this.sendDate(this.KeyNum);
        } else if (DCanvas.getInstance().isKeyDown(4)) {
            this.KeyNum = 1;
            this.sendDate(this.KeyNum);
        } else if (DCanvas.getInstance().isKeyDown(8)) {
            this.KeyNum = 2;
            this.sendDate(this.KeyNum);
        } else if (DCanvas.getInstance().isKeyDown(16)) {
            this.KeyNum = 3;
            this.sendDate(this.KeyNum);
        } else if (DCanvas.getInstance().isKeyDown(32)) {
            this.KeyNum = 4;
            this.sendDate(this.KeyNum);
        } else if (DCanvas.getInstance().isKeyDown(64)) {
            this.KeyNum = 5;
            this.sendDate(this.KeyNum);
        } else if (DCanvas.getInstance().isKeyDown(128)) {
            this.KeyNum = 6;
            this.sendDate(this.KeyNum);
        } else if (DCanvas.getInstance().isKeyDown(256)) {
            this.KeyNum = 7;
            this.sendDate(this.KeyNum);
        } else if (DCanvas.getInstance().isKeyDown(512)) {
            GameUI.getInstance().doChangeKeyType();
        }
    }

    private void sendDate(int n) {
        int n2 = n;
        if (Param.popupDialogInstance.getIsShow()) {
            byte by = (byte) (DCanvas.getInstance().bytDrawKeyIndex[n2] + GameUI.getInstance().bytShortcutKeysType * 13);
            switch (TabState) {
                case 0: {
                    byte by2 = this.Shortcut.getCellBoxIndex();
                    SkillObject skillObject = (SkillObject) this.HashSkillList.get(new Integer(by2));
                    if (skillObject != null) {
                        int n3 = MenuUI.searchSkillIndexById(Param.getInstance().vSkillList, skillObject.intId);
                        if (n3 == -1) {
                            break;
                        }
                        GameUI.getInstance().setSkillKey(n3, by);
                        break;
                    }
                    DebugFrame.getInstance().logIn("Error!! 获取快捷键技能列表序号错误!");
                    break;
                }
                case 1:
                case 4: {
                    GameUI.getInstance().setGoodsKey(new Integer(this.Shortcut.getCellBoxIndex()), by);
                    break;
                }
                case 2: {
                    byte by3 = this.bytsFunction[this.Shortcut.getCellBoxIndex()];
                    GameUI.getInstance().setSystemKey(by, by3);
                    this.initKey();
                    break;
                }
            }
        }
    }

    private void initMoveKey() {
        this.strsFunction = new String[]{"Lựa chọn một", "Phương án 2"};
        this.shtMenuMoveLength = (short) this.strsFunction.length;
        this.bytWordMaxH = (byte) ((Macro.shtRectHeight - 20) / Macro.FONTHEIGHT);
        byte by = this.getIsPitchOn(DCanvas.getInstance().intMoveKey);
        this.strsFunction[by] = String.valueOf(this.strsFunction[by]) + "(Cho phép)";
        if (by > this.bytWordMaxH) {
            this.shtOneMenuMove = (short) (this.bytWordMaxH - 1);
            this.shtOneMenuRoll = (short) (by - this.bytWordMaxH - 1);
        } else {
            this.shtOneMenuMove = by;
            this.shtOneMenuRoll = 0;
        }
        this.setButton(1, 10, 2);
        Param.getInstance().IntRgbColor = DrawBase.getRGB(StringManager.getNewLineW(), Macro.FONTHEIGHT, 1144385017);
    }

    private void drawMoveKey(Graphics graphics) {
        int n;
        short s;
        this.drawFullTiTle(graphics, this.strOneTitlestr, this.shtMenuMoveLength, this.getOneMove(), this.bytWordMaxH);
        LayoutStyle.getInstance().drawBeforeBackGround(4, 38, IDefines.GLOBAL_UI_MAIN_TAB_WIDTH, Macro.SCREEN_HEIGHT - 38 - 30, new int[]{8142636, 14995858, 16314576});
        DrawBase.drawBox(14, 43, StringManager.getNewLineW() - 4, Macro.FONTHEIGHT * 2 + 10, new int[]{14995858, 16642234}, true);
        DrawBase.drawBox(14, 43 + Macro.FONTHEIGHT * 2 + 20, StringManager.getNewLineW() - 4, Macro.SCREEN_HEIGHT - 38 - 5 - Macro.FONTHEIGHT * 2 - 20 - 34, new int[]{14995858, 16642234}, true);
        LayoutStyle.getInstance().drawSelectBox(12, 43 + this.shtOneMenuMove * Macro.FONTHEIGHT, StringManager.getNewLineW(), Macro.FONTHEIGHT);
        short s2 = (short) ((Macro.SCREEN_WIDTH - Macro.font.stringWidth("Di chuyển lên: phím lên")) / 2);
        short s3 = (short) (Macro.SCREEN_HEIGHT / 2);
        short s4 = (short) (Macro.SCREEN_WIDTH - 2 * s2);
        short s5 = (short) (Macro.FONTHEIGHT * 4);
        graphics.setColor(0);
        if (this.shtMenuMoveLength > this.bytWordMaxH) {
            s = 0;
            while (s < this.bytWordMaxH) {
                n = 43 + s * Macro.FONTHEIGHT;
                graphics.drawString(this.strsFunction[s + this.shtOneMenuRoll], 17, n, 20);
                s = (byte) (s + 1);
            }
        } else {
            s = 0;
            while (s < this.shtMenuMoveLength) {
                n = 43 + s * Macro.FONTHEIGHT;
                graphics.drawString(this.strsFunction[s], 17, n, 20);
                s = (byte) (s + 1);
            }
        }
        s = (short) ((this.MENU_HEIGHT + 18 - (Macro.FONTHEIGHT + 3 + 20 + Macro.FONTHEIGHT + 6 + 2)) / 2);
        n = (short) (this.MENU_HEIGHT - 3 - s + 13);
        GameUI.getInstance().drawWordMove(graphics, this.getMoveKeyInfo(this.getOneMove()), s2, s3, (byte) 20);
        DCanvas.getInstance().setOptionSpoolr(graphics, Macro.SCREEN_WIDTH - 12, Macro.FONTHEIGHT + 16, Macro.shtRectHeight, this.bytWordMaxH, this.shtMenuMoveLength, this.getOneMove(), false);
    }

    private String[] getMoveKeyInfo(short s) {
        return new String[0];
    }

    private byte[] getMoveKey(short s) {
        return new byte[0];
    }

    private byte getIsPitchOn(int[] nArray) {
        switch (nArray[1]) {
            case 16384: {
                return 0;
            }
            case 16: {
                return 1;
            }
            case 4096: {
                return 2;
            }
            case 4: {
                return 3;
            }
            case 8192: {
                return 4;
            }
            case 256: {
                return 5;
            }
        }
        return -1;
    }

    private void clearMoveKey() {
        this.strsFunction = null;
        Param.getInstance().IntRgbColor = null;
        Param.getInstance().hImgObject = null;
    }

    public void initMacroChat() {
        int n;
        this.shtMenuMoveLength = (short) 5;
        int n2 = 0;
        this.strsFunction = new String[10];
        if (Rms.strsMacroChat != null) {
            n = 0;
            while (n < Rms.strsMacroChat.length / 2) {
                this.strsFunction[n * 2] = String.valueOf(n2 + 1) + "." + Rms.strsMacroChat[n * 2];
                this.strsFunction[n * 2 + 1] = Rms.strsMacroChat[n * 2 + 1];
                n2 = (byte) (n2 + 1);
                n = (byte) (n + 1);
            }
        }
        n = 4;
        while (n > n2 - 1) {
            this.strsFunction[n * 2] = String.valueOf(n + 1) + ".Câu thường được sử dụng (không sử dụng)";
            this.strsFunction[n * 2 + 1] = "";
            n = (byte) (n - 1);
        }
        this.setButton(1, 9, 2);
        Param.getInstance().IntRgbColor = DrawBase.getRGB(StringManager.getNewLineW(), Macro.FONTHEIGHT, 1144385017);
    }

    public void setMacroChat(String string, String string2) {
        this.strsFunction[this.getOneMove() * 2] = String.valueOf(this.getOneMove() + 1) + "." + string;
        this.strsFunction[this.getOneMove() * 2 + 1] = string2;
    }

    private void drawMacroChat(Graphics graphics) {
        this.drawFullTiTle(graphics, this.strOneTitlestr, this.shtMenuMoveLength, this.getOneMove(), this.bytWordMaxH);
        LayoutStyle.getInstance().drawSelectBox(12, Macro.FONTHEIGHT + 16 + this.shtOneMenuMove * Macro.FONTHEIGHT, StringManager.getNewLineW(), Macro.FONTHEIGHT);
        graphics.setColor(0);
        if (this.shtMenuMoveLength > this.bytWordMaxH) {
            byte by = 0;
            while (by < this.bytWordMaxH) {
                int n = Macro.FONTHEIGHT + 16 + by * Macro.FONTHEIGHT;
                graphics.drawString(this.strsFunction[(by + this.shtOneMenuRoll) * 2], 12, n, 20);
                by = (byte) (by + 1);
            }
        } else {
            short s = 0;
            while (s < this.shtMenuMoveLength) {
                int n = Macro.FONTHEIGHT + 16 + s * Macro.FONTHEIGHT;
                graphics.drawString(this.strsFunction[s * 2], 12, n, 20);
                s = (short) (s + 1);
            }
        }
    }

    private void clearMacroChat() {
        this.strsFunction = null;
        Param.getInstance().IntRgbColor = null;
    }

    public String getLeaguerRemark(TeamObject teamObject) {
        String string = "";
        if (this.bytMenuState == 34) {
            switch (teamObject.bytTroopRank) {
                case 1: {
                    string = "Giúp đỡ";
                    break;
                }
                case 2: {
                    string = "Phó thủ lĩnh";
                    break;
                }
                case 3: {
                    string = "Người giúp đỡ";
                }
            }
        } else if (this.bytMenuState == 35 && teamObject.bytTeamId != 0) {
            string = String.valueOf(teamObject.bytTeamId) + "đội";
        }
        return string;
    }

    private String getRoleRelation(byte by) {
        switch (by) {
            case 2: {
                return "Học nghề";
            }
            case 1: {
                return "Bậc thầy";
            }
        }
        return "";
    }

    private void logicFriendList() {
        Param.getInstance().shtNoncePage = 1;
        this.clearMove();
        if (Param.getInstance().vCommonList == null) {
            this.shtMenuMoveLength = 0;
            if (this.bytFriendSendType == 0) {
                this.bytFriendSendType = 1;
                DCanvas.getInstance().setNetLoad(true);
                NetSend.getInstance().sendHailFellow((byte) 1, (byte) 1, "");
            }
        }
        this.setButton(1, 4, 2);
    }

    private void logicMentoringList() {
        Param.getInstance().shtNoncePage = 1;
        this.clearMove();
        if (this.vTempVessel == null) {
            this.setButton(1, 100, 2);
            this.shtMenuMoveLength = 0;
            if (this.bytMentorSendType == 0) {
                this.bytMentorSendType = 1;
                DCanvas.getInstance().setNetLoad(true);
                NetSend.getInstance().sendShiTu_Option((byte) 1, -1);
            }
        } else {
            this.setButton(1, 4, 2);
            this.addFriendImage();
        }
    }

    public void addFriendImage() {
        if (this.bytMenuState == -31) {
            this.addSocialIntercourse();
        } else {
            byte by;
            Param.getInstance().shtAllPage = (short) ((this.vTempVessel.size() - 1) / 20 + 1);
            if (Param.getInstance().IntRgbColor == null) {
                Param.getInstance().IntRgbColor = DrawBase.getRGB(StringManager.getNewLineW(), Macro.FONTHEIGHT, 1144385017);
            }
            this.shtMenuMoveLength = Param.getInstance().shtNoncePage >= Param.getInstance().shtAllPage ? (short) ((by = (byte) (this.vTempVessel.size() - (Param.getInstance().shtNoncePage - 1) * 20)) > 20 ? 20 : (int) by) : (short) 20;
        }
    }

    public void addSocialIntercourse() {
        byte by;
        Param.getInstance().shtAllPage = (short) ((Param.getInstance().vCommonList.size() - 1) / 20 + 1);
        if (Param.getInstance().IntRgbColor == null) {
            Param.getInstance().IntRgbColor = DrawBase.getRGB(StringManager.getNewLineW(), Macro.FONTHEIGHT, 1144385017);
        }
        this.shtMenuMoveLength = Param.getInstance().shtNoncePage >= Param.getInstance().shtAllPage ? (short) ((by = (byte) (Param.getInstance().vCommonList.size() - (Param.getInstance().shtNoncePage - 1) * 20)) > 20 ? 20 : (int) by) : (short) 20;
    }

    public void setFocus(int n) {
        switch (n) {
            case 0: {
                this.rView.setViewFocal(true);
                Param.getInstance().personalPackage.setFocal(false);
                this.tabStyleInstance.TabFocuse = false;
                this.bytMoveType = 0;
                this.bytMenuState = (byte) 10;
                break;
            }
            case 1: {
                this.rView.setViewFocal(false);
                Param.getInstance().personalPackage.setFocal(true);
                this.tabStyleInstance.TabFocuse = false;
                this.bytMoveType = 1;
                this.setSelectedButton(this.bytMenuState == -35 || this.bytMenuState == -72 ? Param.getInstance().hShopPackage : Param.getInstance().hPackage, Param.getInstance().personalPackage.getCellBoxIndex());
                break;
            }
            case 2: {
                this.rView.setViewFocal(false);
                Param.getInstance().personalPackage.setFocal(false);
                this.tabStyleInstance.TabFocuse = true;
                this.bytMoveType = (byte) 2;
                break;
            }
            case 3: {
                this.rView.setViewFocal(false);
                Param.getInstance().personalPackage.setFocal(false);
                this.tabStyleInstance.TabFocuse = false;
                this.bytMoveType = (byte) 3;
            }
        }
    }

    private void setNpcBusinessButton(byte by) {
        switch (by) {
            case 2: {
                if (Param.getInstance().npcShopBarPackage == null || !Param.getInstance().npcShopBarPackage.getFocal()) {
                    break;
                }
                byte by2 = Param.getInstance().npcShopBarTabStyle.getTabCurrentIndex();
                Hashtable hashtable = (Hashtable) Param.getInstance().hNpcBarPackTable.get(new Integer(by2));
                byte by3 = Param.getInstance().npcShopBarPackage.getCellBoxIndex();
                if (hashtable != null && hashtable.containsKey(new Integer(by3))) {
                    PackageObject packageObject = (PackageObject) hashtable.get(new Integer(by3));
                    if (packageObject.bytBagPlace == 0 || packageObject.bytBagPlace == -1) {
                        this.blnBusinessMore = false;
                        this.intMaxNum = 1;
                    } else {
                        this.blnBusinessMore = true;
                        this.intMaxNum = packageObject.bytBagPlace;
                    }
                    this.setButton(1, 4, 2);
                    break;
                }
                this.setButton(1, 100, 2);
                break;
            }
            case 3: {
                if (Param.getInstance().personalPackage == null || !Param.getInstance().personalPackage.getFocal()) {
                    break;
                }
                byte by4 = this.tabStyleInstance.getTabCurrentIndex();
                String string = Param.getInstance().npcShopOursTabArray[by4];
                Hashtable hashtable = (Hashtable) Param.getInstance().hNpcOursPackTable.get(string);
                byte by5 = Param.getInstance().personalPackage.getCellBoxIndex();
                if (hashtable != null && hashtable.containsKey(new Integer(by5))) {
                    this.setButton(1, 4, 2);
                    break;
                }
                this.setButton(1, 100, 2);
                break;
            }
            default: {
                this.setButton(1, 100, 2);
                this.blnBusinessMore = false;
            }
        }
        Macro.NPC_STEP_SELECT = by;
    }

    private void setNpcStorageButton(byte by) {
        switch (by) {
            case 10: {
                byte by2 = Param.getInstance().npcShopBarPackage.getCellBoxIndex();
                if (Param.getInstance().hNpcStorage != null && Param.getInstance().hNpcStorage.containsKey(new Integer(by2))) {
                    PackageObject packageObject = (PackageObject) Param.getInstance().hNpcStorage.get(new Integer(by2));
                    this.intMaxNum = packageObject.shtPOnum == 0 || packageObject.shtPOnum == -1 ? 1 : (int) packageObject.shtPOnum;
                    this.setButton(1, 4, 2);
                    break;
                }
                this.setButton(1, 100, 2);
                break;
            }
            case 21:
            case 22:
            case 23:
            case 24:
            case 25: {
                byte by3 = this.tabStyleInstance.getTabCurrentIndex();
                String string = Param.getInstance().npcShopOursTabArray[by3];
                Hashtable hashtable = (Hashtable) Param.getInstance().hNpcOursPackTable.get(string);
                byte by4 = Param.getInstance().personalPackage.getCellBoxIndex();
                if (hashtable != null && hashtable.containsKey(new Integer(by4))) {
                    PackageObject packageObject = (PackageObject) hashtable.get(new Integer(by4));
                    this.intMaxNum = packageObject.shtPOnum == 0 || packageObject.shtPOnum == -1 ? 1 : (int) packageObject.shtPOnum;
                    this.setButton(1, 4, 2);
                    break;
                }
                this.setButton(1, 100, 2);
                break;
            }
            default: {
                this.setButton(1, 100, 2);
            }
        }
    }

    public void setNpcStorageSelect() {
        if (Param.getInstance().personalPackage != null && Param.getInstance().personalPackage.getFocal()) {
            byte by = this.tabStyleInstance.getTabCurrentIndex();
            switch (by) {
                case 0: {
                    Macro.NPC_STEP_SELECT = (byte) 21;
                    break;
                }
                case 1: {
                    Macro.NPC_STEP_SELECT = (byte) 22;
                    break;
                }
                case 2: {
                    Macro.NPC_STEP_SELECT = (byte) 23;
                    break;
                }
                case 3: {
                    Macro.NPC_STEP_SELECT = (byte) 24;
                    break;
                }
                case 4: {
                    Macro.NPC_STEP_SELECT = (byte) 25;
                }
            }
        } else if (Param.getInstance().npcShopBarPackage.getFocal()) {
            Macro.NPC_STEP_SELECT = (byte) 10;
        }
    }
}
