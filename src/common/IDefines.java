// Decompiled with: Procyon 0.5.36
// Class Version: 1
package common;

import base.Macro;

public interface IDefines
{
    public static final byte CLT_START = 1;
    public static final byte CLT_REGISTER = 2;
    public static final byte CLT_LAND = 3;
    public static final byte CLT_SERVER = 4;
    public static final byte CLT_ROLE = 5;
    public static final byte CLT_CREATEROLE = 6;
    public static final byte CLT_GAMEIN = 7;
    public static final int DRAW_BASE_LINE_FEED_STRING_ROW_SPACE = DrawBase.getFont().getHeight() + 2;
    public static final short GLOBAL_UI_PACKAGE_BOX_COLUMNS_NUM = 8;
    public static final short GLOBAL_UI_PACKAGE_BOX_W = 22;
    public static final short GLOBAL_UI_PACKAGE_BOX_H = 22;
    public static final short GLOBAL_UI_PACKAGE_BOX_SHOW_IMG_W = 16;
    public static final short GLOBAL_UI_PACKAGE_BOX_SHOW_IMG_H = 16;
    public static final short GLOBAL_UI_PACKAGE_BOX_LINE_SPACING = 2;
    public static final short GLOBAL_UI_PACKAGE_BOX_COLUMN_SPACING = 2;
    public static final int[] GLOBAL_UI_PACKAGE_BOX_BORDER = { 6494722, 16777215, 16763066, 16753705 };
    public static final int GLOBAL_UI_PACKAGE_BOX_START_HEIGHT = 40;
    public static final byte GLOBAL_UI_SCREEN_X = 0;
    public static final byte GLOBAL_UI_SCREEN_Y = 1;
    public static final int TEAM_UI_TOP_BAR_HEIGHT = 38;
    public static final int TEAM_UI_SOFTKEY_HEIGHT = 30;
    public static final int GLOBAL_UI_MAIN_TAB_POS_X = 4;
    public static final int GLOBAL_UI_MAIN_TAB_POS_Y = 38;
    public static final int GLOBAL_UI_MAIN_TAB_WIDTH = Macro.SCREEN_WIDTH - 8;
    public static final int GLOBAL_UI_MAIN_TAB_HEIGHT = 32;
    public static final int GLOBAL_UI_MAIN_BACKCOLOUR_YELLOW = 13866046;
    public static final int GLOBAL_UI_MAIN_BACKCOLOUR_ASHY = 11301955;
    public static final int GLOBAL_UI_MAIN_FONT_COLOUR_BROWN = 9263661;
    public static final int GLOBAL_UI_MAIN_TITLEBOX_HEIGHT = 30;
    public static final int GLOBAL_UI_MAIN_TITLEBOX_WIDTH = Macro.SCREEN_WIDTH - 24;
    public static final int GLOBAL_UI_MAIN_TITLEBOX_X = (Macro.SCREEN_WIDTH >> 1) - (IDefines.GLOBAL_UI_MAIN_TITLEBOX_WIDTH >> 1);
    public static final int GLOBAL_UI_MAIN_TITLEBOX_Y = 0;
    public static final int GLOBAL_UI_MAIN_OUT_COLOUR = 8142636;
    public static final int GLOBAL_UI_MAIN_GRAY = 14995858;
    public static final int GLOBAL_UI_MAIN_WHITE = 16314576;
    public static final int GLOBAL_UI_MAIN_SOFT_HEIGHT = 31;
    public static final int GLOBAL_RGB = 101;
    public static final byte GLOBAL_TRANSLUCENCE_NUM_ZERO = 0;
    public static final byte GLOBAL_TRANSLUCENCE_NUM_ONE = 1;
    public static final byte GLOBAL_TRANSLUCENCE_NUM_TWO = 2;
    public static final byte GLOBAL_TRANSLUCENCE_NUM_THREE = 3;
    public static final byte GLOBAL_WARN_INFORMATION_MAX = 3;
    public static final byte GLOBAL_WARN_INFORMATION_DELAY = 30;
    public static final int GLOBAL_UI_MAIN_NUM_COLOUR = 8142636;
    public static final short GLOBAL_UI_LAND_KEY_WIDHT = 42;
    public static final short GLOBAL_UI_LAND_KEY_HEIGHT = 28;
    public static final short GLOBAL_UI_GAMAE_KEY_WIDHT = 30;
    public static final short GLOBAL_UI_GAMAE_KEY_HEIGHT = 23;
    public static final String GAME_UI_KEY_PNG_ANU = "numbutton";
    public static final byte GLOBAL_UI_FRAME_X = 0;
    public static final byte GLOBAL_UI_FRAME_Y = 1;
    public static final byte GLOBAL_UI_FRAME_WIDTH = 2;
    public static final byte GLOBAL_UI_FRAME_HEIGHT = 3;
    public static final byte GLOBAL_UI_FRAME_VALUE_COUNT = 4;
    public static final short TEACH_UI_SELECT_SKILL_DEFAULT_MP = 33;
    public static final short TEACH_UI_SELECT_SKILL_DEFAULT_POWER = 2;
    public static final int[] MENU_SOFTBUTTON_BOX_COLOR = { 16314576, 16753705, 16762219 };
    public static final int[] MENU_SOFTBUTTON_EXPAND_COLOR = { 6494722, 16753705, 16753705 };
    public static final int STYLE_TAB_EACH_TAG_BORDERCOLOUR = 15768897;
    public static final int STYLE_TAB_EACH_TAG_BACKGROUNDCOLOUR = 16774566;
    public static final int STYLE_TAB_EACH_TAG_CHOOSEFONTCOLOUR = 16777215;
    public static final int STYLE_TAB_EACH_TAG_CHOOSEFONTDEFTONCOLOUR = 9263661;
    public static final int SYSTEM_FONT_COLOUR = 0;
    public static final int SYSTEM_FONT_ONLINE_COLOUR = 12697792;
    public static final int SYSTEM_FONT_BUTTON_COLOUR = 8399402;
    public static final int STYLE_TAB_FRAME_MAGIN_X_OFF = 0;
    public static final int STYLE_TAB_FRAME_MAGIN_Y_OFF = 0;
    public static final int STYLE_BOX_SELECT_OUT_COLOR = 12026667;
    public static final int STYLE_BOX_SELECT_FILL_COLOR = 16442771;
    public static final int STYLE_FG_FRAME_TITLE_HEIGHT = 18;
    public static final int STYLE_FG_FRAME_TITLE_COLOR = 15195324;
    public static final int LAND_UI_LOGO_IMG_POS_X = Macro.SCREEN_WIDTH >> 1;
    public static final int LAND_UI_LOGO_IMG_POS_Y = Macro.SCREEN_HEIGHT >> 1;
    public static final int LAND_UI_MUSIC_STR_POS_X = Macro.SCREEN_WIDTH >> 1;
    public static final int LAND_UI_MUSIC_STR_POS_Y = Macro.SCREEN_HEIGHT - 25 >> 1;
    public static final int LAND_UI_SPLASH_IMG_POS_X = Macro.SCREEN_WIDTH / 2;
    public static final int LAND_UI_SPLASH_IMG_POS_Y = Macro.SCREEN_HEIGHT / 8 * 7;
    public static final int LAND_UI_MAIN_MENU_ITEMBG_X = Macro.SCREEN_WIDTH >> 1;
    public static final int LAND_UI_MAIN_MENU_ITEMBG_Y = Macro.SCREEN_HEIGHT - 20 >> 1;
    public static final int LAND_UI_MAIN_MENU_NAME_Y = IDefines.LAND_UI_MAIN_MENU_ITEMBG_Y - 5;
    public static final int LAND_UI_MAIN_MENU_ITEMX1_OFFSET = 3;
    public static final int LAND_UI_MAIN_MENU_ITEMX0_OFFSET = 5;
    public static final int LAND_UI_MAIN_MENU_ITEMY0_OFFSET = 10;
    public static final int LAND_UI_MAIN_MENU_ITEMY1_OFFSET = 4;
    public static final short LAND_UI_MAIN_MENU_GRIDTABLE = 3;
    public static final short[] LAND_UI_MAIN_MENU_GRIDTABLE_PERCENT = { 33, 33, 33 };
    public static final int LAND_UI_HELP_STR_X = 24;
    public static final int LAND_UI_HELP_STR_Y = Macro.FONTHEIGHT + 20;
    public static final int LAND_UI_HELP_STR_FONT_COLOR = 4537392;
    public static final int LAND_UI_ROLE_NUM = 3;
    public static final int SERVER_UI_OFF = 16;
    public static final String[] SERVER_UI_SERVER_SATATE = { "Smooth", "Nóng", "Đầy" };
    public static final int[] SERVER_UI_SERVER_SATATE_FONT_COLOUR = { 9621584, 16760832, 16727100 };
    public static final int SERVER_UI_BACK_COLOUR = 16642234;
    public static final int SERVER_UI_FONT_COLOUR = 16777215;
    public static final int SERVER_UI_FONT_CHOUSE = 8142636;
    public static final int SERVER_UI_CLOUSE = 13749708;
    public static final short CREATE_ROLE_CHOOSE = 8;
    public static final int CREATE_ROLE_FONT_COLOUR = 8142636;
    public static final String CREATE_ROLE_DESCRIBE = "Nhấn nút 0 hoặc giữa để chỉnh sửa biệt hiệu";
    public static final String CREATE_SEX_DESCRIBE = "Vui lòng chọn giới tính của vai trò đã tạo";
    public static final int CREATE_POINT = 3;
    public static final String REGISTER_DESCRIBE = "Vui lòng nhập số tài khoản và mật khẩu của bạn \n tài khoản và mật khẩu chỉ có thể gồm 6-11 chữ cái và số";
    public static final String ACCOUNT_SAFE = "Khi tài khoản của bạn bị ràng buộc vào điện thoại, bạn có thể sử dụng chức năng khôi phục mật khẩu để truy xuất tài khoản và mật khẩu của bạn.";
    public static final String WARM = "Mật khẩu tài khoản nhỏ hơn 6-11 chữ số";
    public static final String WARM_PASSWORD = "Mật khẩu nhỏ hơn 6-11";
    public static final int ACCOUNT_PIC_WIDTH = 36;
    public static final int ACCOUNT_PIC_HEIGHT = 18;
    public static final short ACCOUNT_GRIDTABLE_ROW_LIST = 2;
    public static final short ACCOUNT_CHOOSE_ROW = 4;
    public static final short ACCOUNT_CURSOR = 15;
    public static final short[] ACCOUNT_GRIDTABLE_LIST_PERCENT = { 33, 66 };
    public static final short[] ACCOUNT_GRIDTABLE_ROW_PERCENT = { 50, 50 };
    public static final short[] ACCOUNT_CHOOSE_ROW_PERCENT = { 25, 25, 25, 25 };
    public static final int[] ACCOUNT_ACCOUNT_COLOUR = { 16777215, 13972798, 16739958 };
    public static final int[] ACCOUNT_PASSWORD_COLOUR = { 16777215, 16762219, 5818877 };
    public static final int[] ACCOUNT_FRAME_COLOUR = { 16777215, 15768897, 16314576 };
    public static final String ACCOUNT_SAVE_PASSWORD = "Lưu mật khẩu người dùng";
    public static final short NUMB_PIC_HEIGHT = 15;
    public static final String STR_LAND_GAME = "Đăng nhập trò chơi";
    public static final String STR_ACCOUNT_GAME = "Trò chơi đã đăng ký";
    public static final String STR_BINDING_NUMBER = "Điện thoại ràng buộc";
    public static final String STR_RETRIEVE_PASSWORD = "Khôi phục mật khẩu";
    public static final String STR_MODIFICATION_PASSWORD = "Thay đổi mật khẩu";
    public static final byte LAND_GAME = 0;
    public static final byte ACCOUNT_GAME = 1;
    public static final byte BINDING_NUMBER = 2;
    public static final byte RETRIEVE_PASSWORD = 3;
    public static final byte MODIFICATION_PASSWORD = 4;
    public static final byte GAME_UI_FIND_X_EXTEND = 1;
    public static final byte GAME_UI_FIND_Y_EXTEND = 1;
    public static final byte CAME_UI_BUTTON_BIG_W = 40;
    public static final byte CAME_UI_BUTTON_BIG_H = 26;
    public static final byte CAME_UI_BUTTON_SIMALL_W = 34;
    public static final byte CAME_UI_BUTTON_SIMALL_H = 18;
    public static final byte CAME_UI_ME_ATTACK_TIP_OK = 0;
    public static final byte CAME_UI_ME_ATTACK_TIP_DISTANCE_FAR = 1;
    public static final byte CAME_UI_ME_ATTACK_TIP_IMPEDE_TARGET = 2;
    public static final byte CAME_UI_ME_ATTACK_TIP_IS_RIDING = 3;
    public static final byte CAME_UI_ME_ATTACK_TIP_NO_WEAPON_ATTACK = 4;
    public static final byte CAME_UI_ME_ATTACK_TIP_SELECT_TARGET_ERROR = 5;
    public static final String GAME_UI_ROLE_BLOOD = "role";
    public static final String GAME_UI_TEAM_BLOOD = "team";
    public static final String GAME_UI_ROLE_SEL_FRMAE = "monsterhead";
    public static final String GAME_UI_NEWMAIL = "newmail";
    public static final String LOADING_UI_IMG = "loading1";
    public static final String LOADING_UI_EGG = "loading";
    public static final int LOADING_ANIMATION_ID = 0;
    public static final short LOADING_UI_EGG_W = 10;
    public static final short LOADING_UI_EGG_H = 20;
    public static final short LOADING_UI_RECT_X = 20;
    public static final int GAME_UI_PORTRAIT_BG = 0;
    public static final int GAME_UI_PORTRAIT_BLOOD = 1;
    public static final int GAME_UI_PORTRAIT_MAGIC = 2;
    public static final int GAME_UI_PORTRAIT_DEFAULT_FRAME = 0;
    public static final int GAME_UI_PORTRAIT_DEFAULT_FRAME_SPECIAL_MONSTER = 1;
    public static final int GAME_UI_PORTRAIT_BLOOD_MAGIC_IMAGE_WIDTH = 12;
    public static final int GAME_UI_PORTRAIT_BLOOD_MAGIC_IMAGE_HEIGHT = 25;
    public static final int GAME_UI_PORTRAIT_BLOOD_X = 2;
    public static final int GAME_UI_PORTRAIT_BLOOD_Y = 3;
    public static final int GAME_UI_PORTRAIT_MAGIC_X = 28;
    public static final int GAME_UI_PORTRAIT_MAGIC_Y = 3;
    public static final int GAME_UI_PORTRAIT_TEAM_BLOOD_MAGIC_IMAGE_WIDTH = 11;
    public static final int GAME_UI_PORTRAIT_TEAM_BLOOD_MAGIC_IMAGE_HEIGHT = 24;
    public static final int GAME_UI_PORTRAIT_TEAM_HEAGHT = 34;
    public static final int GAME_UI_PORTRAIT_TEAM_BLOOD_X = 2;
    public static final int GAME_UI_PORTRAIT_TEAM_BLOOD_Y = 3;
    public static final int GAME_UI_PORTRAIT_TEAM_MAGIC_X = 24;
    public static final int GAME_UI_PORTRAIT_TEAM_MAGIC_Y = 3;
    public static final int GAME_UI_PORTRAIT_TEAM_Y_OFFSET = 2;
    public static final int GAME_UI_RES_HOT_KEY_SPR_NUM = 3;
    public static final String GAME_UI_RES_HOT_KEY_PNG_ANU = "skillbar";
    public static final int GAME_UI_HOTKEY_SWITCH_WIDTH = 26;
    public static final int GAME_UI_HOTKEY_MENU_WIDTH = 30;
    public static final int GAME_UI_HOTKEY_BOX = 0;
    public static final int GAME_UI_HOTKEY_MENU_ONE = 1;
    public static final int GAME_UI_HOTKEY_MENU_TWO = 2;
    public static final int GAME_UI_HOTKEY_DEFAULT_FRAME = 0;
    public static final int MENU_KEY_WIDTH = 32;
    public static final int GAME_UI_HOTKEY_BOX_WIDTH = 22;
    public static final int GAME_UI_HOTKEY_BOX_HEIGHT = 23;
    public static final int GAME_UI_HOTKEY_BOX_CONTENT_HEIGHT = 16;
    public static final int MENU_KYE_YES = 0;
    public static final int MENU_KEY_NO = 1;
    public static final int MENU_KEY_MENU = 2;
    public static final int MENU_KEY_CHANGE = 3;
    public static final int MENU_KEY_CHAT = 4;
    public static final int MENU_KEY_HIDE = 5;
    public static final int GAME_UI_HOTKEY_BOX_CONTENT_HEIGHT_OFF = 3;
    public static final int GAME_UI_HOTKEY_EXEP_HEIGHT = 6;
    public static final int GAME_UI_HOTKEY_BOX_X_OFF = 21;
    public static final int GAME_UI_HOTKEY_NUM = 8;
    public static final int GAME_UI_HOTKEY_EXEP_X_OFF = 0;
    public static final int GAME_UI_HOTKEY_EXEP_DISTANCE = 3;
    public static final int GAME_UI_HOTKEY_EXEP_BAR_CONTENT_X_OFF = 2;
    public static final int GAME_UI_HOTKEY_EXEP_COLOR = 16770198;
    public static final int GAME_UI_HOTKEY_EXEP_COLOR_BG_BAR = 3547651;
    public static final int GAME_UI_HOTKEY_EXEP_LINE_TOP = 12156208;
    public static final int GAME_UI_HOTKEY_EXEP_LINE_BOTTOM = 16500270;
    public static final int GAME_UI_HOTKEY_EXEP_NUM = 10;
    public static final int GAME_UI_HOTKEY_PAGING_BUTTON_X = 120;
    public static final int GAME_UI_HOTKEY_PAGING_BUTTON_WIDTH = 22;
    public static final int GAME_UI_HOTKEY_PAGING_BUTTON_HEIGHT = 27;
    public static final int GAME_UI_HOTKEY_MENU_BUTTON_WIDTH = 26;
    public static final int GAME_UI_HOTKEY_MENU_BUTTON_HEIGHT = 27;
    public static final int GAME_UI_HOTKEY_MENU_BUTTON_X = Macro.SCREEN_WIDTH - 26;
    public static final String GAME_UI_MAP_NORMAL_JUMP_RES = "transport2";
    public static final String GAME_UI_MAP_BAK_JUMP_RES = "transport1";
    public static final String GAME_UI_JUMP_EFFECT_RES = "transporteffect01";
    public static final int GAME_UI_OUT_JUMP_ID = 0;
    public static final int GAME_UI_ROLE_STATE_X = 50;
    public static final int GAME_UI_ROLE_STATE_Y = 6;
    public static final int GAME_UI_BUFF_SIZE = 9;
    public static final int GAME_UI_BUFF_SIZE_INSIDE = 8;
    public static final int GAME_UI_BUFF_INDEX_BASE = 10;
    public static final byte GAME_UI_LEVEL_NORMAL = 0;
    public static final byte GAME_UI_LEVEL_LUXURY = 1;
    public static final short GAME_UI_LEVEL_EQUIP_ME_LEVEL = 9999;
    public static final short GAME_UI_LEVEL_EQUIP_NONE = 0;
    public static final byte GAME_UI_LEVEL_EQUIP_PLAYER_ID = 0;
    public static final byte GAME_UI_LEVEL_EQUIP_PLAYER_EQUIP_ID = 0;
    public static final byte GAME_UI_LEVEL_EQUIP_PLAYER_PNG_ID = 0;
    public static final byte GAME_UI_LEVEL_EQUIP_PLAYER_ANU_ID = 0;
    public static final short GAME_UI_LFS_POOL_SIZE = 6;
    public static final short GAME_UI_LFS_SKILL_POSITION_NONE = -1;
    public static final short GAME_UI_LFS_SKILL_POSITION_ANYWHERE = -2;
    public static final int GAME_UI_LFS_ACTIVE_ME = 50001;
    public static final int GAME_UI_LFS_ACTIVE_OTHER_TARGE_ME = 5001;
    public static final int GAME_UI_LFS_ACTIVE_OTHER = 1;
    public static final int GAME_UI_LFS_PASSIVE_ME = 20001;
    public static final int GAME_UI_LFS_PASSIVE_OTHER_FROM_ME = 1001;
    public static final int GAME_UI_LFS_PASSIVE_OTHER = 1;
    public static final int GAME_UI_LFS_BIND_PARAM_CENTER = 2001;
    public static final byte GAME_UI_LFS_BINDED_NON = 0;
    public static final byte GAME_UI_LFS_BINDED_BINDED = 1;
    public static final byte GAME_UI_LFS_BINDED_BINDED_DONE = 2;
    public static final byte GAME_UI_LFS_ROLE_ID = 0;
    public static final byte GAME_UI_LFS_ROLE_TYPE = 1;
    public static final byte GAME_UI_LFS_ROLE_BIND_PARAM = 2;
    public static final byte GAME_UI_LFS_ROLE_COUNT = 3;
    public static final byte ROLE_SMALL_MAP_TASK_SIGN_CAN_ACCEPT = 0;
    public static final byte ROLE_SMALL_MAP_TASK_SIGN_CAN_ACCEPT_SELECTED = 1;
    public static final byte ROLE_SMALL_MAP_TASK_SIGN_COMMIT_WITH_CONDITION = 2;
    public static final byte ROLE_SMALL_MAP_TASK_SIGN_COMMIT_WITH_CONDITION_SELECTED = 3;
    public static final byte ROLE_SMALL_MAP_TASK_SIGN_COMMIT_WITHOUT_CONDITION = 4;
    public static final byte ROLE_SMALL_MAP_TASK_SIGN_COMMIT_WITHOUT_CONDITION_SELECTED = 5;
    public static final byte ROLE_SMALL_MAP_TEAM_PLAYER = 6;
    public static final byte ROLE_SMALL_MAP_TEAM_PLAYER_SELECTED = 7;
    public static final byte ROLE_SMALL_MAP_NPC = 8;
    public static final byte ROLE_SMALL_MAP_NPC_SELECTED = 9;
    public static final byte ROLE_SMALL_MAP_JUMP = 10;
    public static final byte ROLE_SMALL_MAP_JUMP_SELECTED = 11;
    public static final byte ROLE_SMALL_MAP_MIRROR = 12;
    public static final short GAME_UI_SMALL_MAP_WIDTH = 128;
    public static final short GAME_UI_SMALL_MAP_HEIGHT = 128;
    public static final short GAME_UI_SMALL_MAP_OUTSIDE_FRAME_X_OFF = 9;
    public static final short GAME_UI_SMALL_MAP_OUTSIDE_FRAME_Y_OFF = 9;
    public static final byte GAME_UI_SMALL_MAP_NONE = 0;
    public static final byte GAME_UI_SMALL_MAP_AREA = 1;
    public static final byte GAME_UI_SMALL_MAP_WORLD = 2;
    public static final byte GAME_UI_SMALL_MAP_COUNT = 3;
    public static final byte GAME_UI_SMALL_MAP_FLASH_RATE_KEEP = 10;
    public static final byte GAME_UI_SMALL_MAP_FLASH_RATE = 8;
    public static final byte GAME_UI_SMALL_MAP_ICON_SIZE = 8;
    public static final int WARM_TIP_FONT_COLUOR = 16642234;
    public static final byte GAME_UI_MAP_AVAILABLE_AREA = 25;
    public static final byte GAME_UI_MAP_MOVE_STEP_SIZE = 10;
    public static final String GAME_UI_AREA_MAP_SEE = "Xem";
    public static final String GAME_UI_AREA_MAP_ENTER = "Nhập";
    public static final String GAME_UI_AREA_MAP_NPC = "Tìm NPC";
    public static final String GAME_UI_AREA_MAP_WORLD_MAP = "Bản đồ thế giới";
    public static final String GAME_UI_AREA_MAP_BACK = "Trở lại";
    public static final String[] GAME_UI_AREA_MAP_MENU = { "Xem", "Nhập", "Tìm NPC", "Bản đồ thế giới", "Trở lại" };
    public static final String GAME_UI_AREA_MAP_SEE_TITLE = "Bản đồ";
    public static final String GAME_UI_AREA_MAP_NPC_LIST_TITLE = "Danh sách NPC";
    public static final byte GAME_UI_AREA_MAP_ENTER_AREA_MAP = 0;
    public static final byte GAME_UI_AREA_MAP_INFO = 1;
    public static final byte GAME_UI_AREA_MAP_TRANSFER = 2;
    public static final byte GAME_UI_AREA_MAP_NPC_LIST = 3;
    public static final byte GAME_UI_AREA_MAP_ENTER_WORLD_MAP = 4;
    public static final byte GAME_UI_AREA_MAP_WORLD_MAP_AREA_MAP = 5;
    public static final byte GAME_UI_AREA_MAP_TRANSFER_DOUBLE_CHECK = 6;
    public static final byte GAME_UI_AREA_MAP_WORLD_TRANSFER_NPC = 7;
    public static final byte GAME_UI_AREA_MAP_WORLD_TRANSFER_NPC_DOUBLE_CHECK = 8;
    public static final byte GAME_UI_WORLD_MAP_NONE = 0;
    public static final byte GAME_UI_WORLD_MAP_DRAGON = 1;
    public static final byte GAME_UI_WORLD_MAP_EVAIL = 2;
    public static final byte GAME_UI_WORLD_MAP_ANGEL = 3;
    public static final byte GAME_UI_WORLD_MAP = 4;
    public static final byte GAME_UI_MAP_TOUCH_AREA_SIZE = 25;
    public static final byte GAME_UI_WORLD_MAP_ARROW_AREA_SIZE = 50;
    public static final int[][] GAME_UI_WORLD_MAP_ARROW_AREA_POS = { { 0, Macro.SCREEN_HEIGHT - 50 >> 1, 50, 50, 3, 31 }, { Macro.SCREEN_WIDTH - 50, Macro.SCREEN_HEIGHT - 50 >> 1, 50, 50, 4, 32 }, { Macro.SCREEN_WIDTH - 50 >> 1, 0, 50, 50, 1, 29 }, { Macro.SCREEN_WIDTH - 50 >> 1, Macro.SCREEN_HEIGHT - 50, 50, 50, 2, 30 } };
    public static final long GAME_UI_PLAYER_DEAD_TIMEOUT = 120000L;
    public static final String[] MENU_UI_PET_TAB_TITLE = { "Túi" };
    public static final String[] MENU_UI_PET_ATTRI_TITLE = { "Biệt hiệu", "Mức sử dụng", "Tốc độ di chuyển" };
    public static final String[] MENU_UI_PET_ATTRI_TITLE_VAL = { "--", "--", "--" };
    public static final byte MENU_UI_PET_PACKAGE = 0;
    public static final byte MENU_UI_PET_PROP = 1;
    public static final byte MENU_UI_PET_SKILL = 2;
    public static final byte MENU_UI_PET_TAB_COUNT = 3;
    public static final byte MENU_UI_SKILL_FIGHT_TAB_VOCATION_BEFORE = 0;
    public static final byte MENU_UI_SKILL_FIGHT_TAB_VOCATION_AFTER = 1;
    public static final byte MENU_UI_SKILL_FIGHT_TAB_BOOK = 2;
    public static final byte MENU_UI_SKILL_FIGHT_TAB_COUNT = 3;
    public static final int MENU_UI_SKILL_FIGHT_TAB_FRAME_HEIGHT = Macro.SCREEN_HEIGHT - 38 - 30 - 32 - 13;
    public static final String[] SKILL_FIGHT_TAB_TITLE = { "Tiểu học", "Nâng cao", "Sách thiên thượng" };
    public static final String SKILL_FIGHT_BOOK_INLAY_TITLE = "Sách Khảm";
    public static final int SKILL_FIGHT_BOOK_NONE_ID = 0;
    public static final String SKILL_FIGHT_BOOK_NONE = "Khảm";
    public static final int SKILL_FIGHT_BOOK_NONE_BOX_X = 12;
    public static final int SKILL_FIGHT_BOOK_NONE_BOX_WIDTH = Macro.SCREEN_WIDTH - 24;
    public static final int SKILL_FIGHT_BOOK_NONE_BOX_HEIGHT = 25;
    public static final int SKILL_FIGHT_BOOK_NONE_BOX_TXT_Y_OFF = 5;
    public static final int SKILL_FIGHT_BOOK_NONE_BOX_DISTANT_Y = 10;
    public static final byte SKILL_FIGHT_BOOK_DEFAULT_NUM = 3;
    public static final int[] KILL_FIGHT_BOOK_NONE_BORDER = { 6494722, 16777215, 16763066, 16777215 };
    public static final int SKILL_FIGHT_BOOK__BOX_SELECT_OUT_COLOR = 12026667;
    public static final int SKILL_FIGHT_BOOK__BOX_SELECT_FILL_COLOR = 16442771;
    public static final int SKILL_FIGHT_SKILL_ATTRI_ICON_X = 0;
    public static final int SKILL_FIGHT_SKILL_ATTRI_NAME_X = 20;
    public static final int SKILL_FIGHT_SKILL_ATTRI_TYPE_X = IDefines.SKILL_FIGHT_BOOK_NONE_BOX_WIDTH * 3 / 5;
    public static final int SKILL_FIGHT_SKILL_ATTRI_KEY_X = IDefines.SKILL_FIGHT_BOOK_NONE_BOX_WIDTH - 19;
    public static final int SKILL_FIGHT_SKILL_ATTRI_LEVEL_X = IDefines.SKILL_FIGHT_BOOK_NONE_BOX_WIDTH * 5 / 7;
    public static final String[] SKILL_FIGHT_SKILL_PASSIVITY = { "Chúa", "Hãy là" };
    public static final int SKILL_FIGHT_BOOK_ATTRI_ICON_X = 0;
    public static final int SKILL_FIGHT_BOOK_ATTRI_NAME_X = IDefines.SKILL_FIGHT_BOOK_NONE_BOX_WIDTH * 2 / 5;
    public static final int SKILL_FIGHT_BOOK_ATTRI_QUALITY_X = IDefines.SKILL_FIGHT_BOOK_NONE_BOX_WIDTH * 3 / 5;
    public static final int SKILL_FIGHT_BOOK_ATTRI_ATTITION_X = IDefines.SKILL_FIGHT_BOOK_NONE_BOX_WIDTH;
    public static final byte SKILL_FIGHT_BOOK_SUCCESS = 0;
    public static final byte SKILL_FIGHT_BOOK_UN_SUCCESS = 1;
    public static final String SKILL_FIGHT_BOOK_SEE_TITLE = "Mô tả của cuốn sách";
    public static final String[] SKILL_FIGHT_SKILL_ATTRI = { "Tên", "Lớp", "Cấp độ", "Khóa" };
    public static final String[] SKILL_FIGHT_BOOK_ATTRI = { "Tên", "Chất lượng", "Điểm cộng" };
    public static final short SKILL_FIGHT_ATTRI_HEIGHT = 25;
    public static final short SKILL_FIGHT_BOTTOM_ALIGN_HEIGHT = 13;
    public static final int TEAM_UI_MAIN_MENU_BACK_COLOUR = 15195580;
    public static final String[] TEAM_UI_TITLE_NAME = { "Đội 1", "Đội 2" };
    public static final int TEAM_OFF = 18;
    public static final int TEAM_UI_OFF = 8;
    public static final byte TEAM_HUMAN_MAN = 0;
    public static final byte TEAM_HUMAN_WOMAN = 1;
    public static final byte TEAM_DEMO_MAN = 2;
    public static final byte TEAM_DEMO_WOMAN = 3;
    public static final int FRIEND_UI_OFF = 2;
    public static final int FRIEND_UI_OFF_DOWN = 8;
    public static final String FRIEND_TITLE_NAME = "Bạn bè";
    public static final String FRIEND_UI_NAME = "Biệt hiệu";
    public static final String FRIEND_UI_LEVEL = "Lớp";
    public static final String FRIEND_UI_OCCU = "Sự nghiệp";
    public static final String FRIEND_UI_FRIENDLIST = "Danh sách bạn bè";
    public static final String FRIEND_UI_SCREEN = "Danh sách đen";
    public static final String FRIEND_UI_ENEMY = "Đã muốn";
    public static final String[] FRIEND_UI_TITLE = { "Bạn bè", "Tên màu đen", "Kẻ thù" };
    public static final String[] CHAT_TITLE_NAME = { "Toàn diện", "Thế giới", "Gia đình", "Quận", "Trợ giúp", "Đội", "Tôi", "Sở", "Trận chiến" };
    public static final String CHAT_TTTLE = "Trò chuyện";
    public static final byte CHAT_UI_OFF = 8;
    public static final int CHAT_FONT_COLOUR = 16711680;
    public static final int MASTER_UI_OFF = 8;
    public static final String MASTER_UI_TITLE_NAME = "Mối quan hệ cố vấn";
    public static final String[] MASTER_UI_RELATION = { "Thầy của tôi", "Người học nghề của tôi" };
    public static final String[] MASTER_UI_RELATION_T = { "Thầy của tôi", "Cửa của tôi" };
    public static final String PARTNER_UI_TITLE_NAME = "Mối quan hệ hôn nhân";
    public static final String PARTNER_UI_TITLE_NAMELOVE = "Thông tin tình yêu";
    public static final int PARTNER_UI_OFF = 8;
    public static final String[] PARTNER_UI_BASE = { "Người yêu", "Cấp độ:", "Sự nghiệp:", "Chủng tộc:", "Băng đảng:" };
    public static final String[] PARTNER_UI_StringLove = { "Giá trị tình yêu:", "Xếp hạng tình yêu:", "Ngày tình yêu:", "Mức độ tình yêu:" };
    public static final String PARTNER_LOVER_EXPLAIN = "Hôn nhân Mẹo: Xin chào, sau khi giá trị tình yêu đạt đến 3000, bạn có thể mang một chiếc nhẫn cưới và đội người yêu của bạn để tìm ông già để đăng ký và kết hôn.";
    public static final int TASK_UI_OFF_DOWN = 8;
    public static final String TASK_UI_TITLE = "Nhiệm vụ";
    public static final String[] TASK_UI_TITLE_NAME = { "Đã kết nối", "Tìm kiếm" };
    public static final String TASK_UI_TASKNAME = "Tên công việc";
    public static final String TASK_UI_TASKPLACE = "Vị trí nhiệm vụ";
    public static final String PROPERTY_UI_TITLE = "Thuộc tính ký tự";
    public static final String[] PROPERTY_UI_TITLE_NAME = { "Nền tảng", "Chiến đấu", "Danh dự", "Tiểu bang" };
    public static final int PROPERTY_UI_ROLE_FRAME_WIDTH = 80;
    public static final int PROPERTY_UI_ROLE_FRAME_HEIGHT = 110;
    public static final int PROPERTY_UI_UI_OFF = 8;
    public static final String[] PRORERTY_UI_BASICDATE = { "Biệt hiệu:", "Sự nghiệp:", "Cấp độ:", "Giới tính:", "Chủng tộc:" };
    public static final String PRORERTY_STATE_NAME = "Tên";
    public static final String PRORERTY_STATE_TIME = "Thời lượng";
    public static final int PRORERTY_BASE = 0;
    public static final int PRORERTY_FRIGHTING = 1;
    public static final int PRORERTY_HONOUR = 2;
    public static final int PRORERTY_STATE = 3;
    public static final String FACTION_UI_TITLE_NAME = "Băng đảng";
    public static final String FACTION_UI_NAME = "Vai trò";
    public static final String FACTION_UI_POST = "Chức vụ";
    public static final String FACTION_UI_LEVEL = "Lớp";
    public static final String FACTION_FULLINFO_TITLE = "Thông tin về băng đảng";
    public static final String FACTION_CREAT = "Băng đảng mới";
    public static final int[] FACTION_NAME_COLOUR = { 4642587, 5006916 };
    public static final String SHORTCUT_UI_TITLE_NAME = "Cài đặt phím tắt";
    public static final String SHORTCUT_UI_PRPMPT = "按下“9键”切换快捷栏";
    public static final String SHORTCUT_UI_DESCRIB = "Nhấn phím số tương ứng để cài phím tắt";
    public static final String SHORTCUT_UI_POINT = "Nhấp vào phím tắt cài đặt khu vực phím tắt tương ứng";
    public static final String[] SHORTCUT_UI_TAB = { "Kỹ năng", "Thuốc", "Chức năng", "Thú cưng", "Kho báu" };
    public static final int SHORTCUT_BOX_NUMBER = 24;
    public static final byte SHORTCUT_SKILL = 0;
    public static final byte SHORTCUT_POTION = 1;
    public static final byte SHORTCUT_FUNCTION = 2;
    public static final byte SHORTCUT_PET = 3;
    public static final byte SHORTCUT_TREASURE = 4;
    public static final String PERSONAL_EMALL_TITLE_NAME = "Mail";
    public static final String[] PERSONAL_EMALL_TITLE_STRING = { "Tiêu đề", "Ngày tháng" };
    public static final String RANK_LIST_TITLE = "Bảng dẫn đầu";
    public static final short MENU_UI_PERSONAL_SHOP_BORDER_W = 13;
    public static final short MENU_UI_PERSONAL_SHOP_PACKAGE_BOX_X_OFFSET = 8;
    public static final short MENU_UI_PERSONAL_SHOP_PACKAGE_BOX_Y_OFFSET = 8;
    public static final short MENU_UI_PERSONAL_SHOP_Y_OFFSET = 10;
    public static final short MENU_UI_PERSONAL_SHOP_X_OFFSET = 3;
    public static final int[] MENU_UI_PERSONAL_SHOP_PERSONAL_PACKAGE_BOX_BORDER = { 15768897, 16642234 };
    public static final short MENU_UI_PERSONAL_SHOP_PERSONAL_PACKAGE_BOX_SHOW_H = 72;
    public static final short MENU_UI_PERSONAL_SHOP_SELL_PACKAGE_BOX_SHOW_H = 48;
    public static final int[] MENU_UI_PERSONAL_SHOP_SELL_PACKAGE_BOX_BORDER = { 11301955, 14995858 };
    public static final String[] MENU_UI_PERSONAL_SHOP_TAB = { "Thiết bị", "Thuốc", "Nguyên liệu" };
    public static final byte DIALOG_UI_DEAL_BACK_TABLE_TOP_OFFSET = 46;
    public static final byte DIALOG_UI_DEAL_BACK_TABLE_BOTTOM_OFFSET = 47;
    public static final byte DIALOG_UI_DEAL_BACK_TABLE_LEFT_RIGHT_OFFSET = 14;
    public static final byte DIALOG_UI_DEAL_TABLE_OFFSET = 2;
    public static final int[] DIALOG_UI_DEAL_AREA_BACK_BORDER = { 14995858, 16642234 };
    public static final int[] DIALOG_UI_DEAL_BOX_BACK_BORDER = { 12026667, 16442771 };
    public static final int[] DIALOG_UI_DEAL_PERSONAL_PACKAGE_BOX_BACK_BORDER = { 14995858, 16642234 };
    public static final int TWO_DIALOG_PACKAGE_LINE_NUM = 2;
    public static final int TWO_DIALOG_PACKAGE_FRAME_MAGIN_Y = 10;
    public static final int TWO_DIALOG_PACKAGE_FRAME_MAGIN_X = 8;
    public static final byte TWO_DIALOG_PACKAGE_BOX_NUM = 16;
    public static final byte TWO_DIALOG_PACKAGE_BOX_BOTTOM_OFF_Y = 12;
    public static final int ROLE_SKILL_PARAM_ID = 0;
    public static final int ROLE_SKILL_PARAM_MUSIC_ID = 1;
    public static final int ROLE_SKILL_PARAM_LOOP = 2;
    public static final int ROLE_SKILL_PARAM_DELAY = 3;
    public static final int ROLE_SKILL_PARAM_COUNT = 4;
    public static final short ROLE_SKILL_ID_NULL = -1;
    public static final short ROLE_SKILL_ID_NULL_2 = 0;
    public static final int ROLE_SKILL_LOOP_CIRCUL = -1;
    public static final int ROLE_SKILL_DELAY_FOREVER = -1;
    public static final String ROLE_STALL_RES = "sellshop";
    public static final int ROLE_STALL_RES_ANIMATION_ID = 0;
    public static final int ROLE_STALL_RES_X_OFF = 0;
    public static final int ROLE_STALL_RES_Y_OFF = 0;
    public static final byte ROLE_NPC_TASK_SIGN_NONE = 0;
    public static final byte ROLE_NPC_TASK_SIGN_CAN_ACCEPT = 1;
    public static final byte ROLE_NPC_TASK_SIGN_COMMIT_WITH_CONDITION = 2;
    public static final byte ROLE_NPC_TASK_SIGN_COMMIT_WITHOUT_CONDITION = 3;
    public static final byte ROLE_NPC_TASK_SIGN_COUNT = 4;
    public static final byte RADARICON_NPC_TASK_SIGN_NONE = 0;
    public static final byte RADARICON_NPC_TASK_SIGN_CAN_ACCEPT = 1;
    public static final byte RADARICON_NPC_TASK_SIGN_COMMIT_WITH_CONDITION = 2;
    public static final byte RADARICON_NPC_TASK_SIGN_COMMIT_WITHOUT_CONDITION = 3;
    public static final byte RADARICON_NPC_TASK_SIGN_COUNT = 4;
    public static final byte RADARICON_ARROWS_UP = 0;
    public static final byte RADARICON_ARROWS_DOWN = 1;
    public static final byte RADARICON_ARROWS_LEFT = 2;
    public static final byte RADARICON_ARROWS_RIGHT = 3;
    public static final byte RADARICON_ONESELF = 4;
    public static final byte RADARICON_COUNT = 5;
    public static final String NPC_TASK_RESOURCE = "npctask";
    public static final byte NPC_TASK_YELLOW_BANG = 0;
    public static final byte NPC_TASK_YELLOW_QUESTION = 1;
    public static final byte NPC_TASK_GRAY_BANG = 2;
    public static final byte NPC_TASK_GRAY_QUESTION = 3;
    public static final byte NPC_TASK_BLUE_BANG = 4;
    public static final byte NPC_TASK_BLUE_QUESTION = 5;
    public static final byte NPC_TASK_COLOR_COUNT = 6;
    public static final byte ROLE_NPC_ANIMATION_TYPE_STATIC = 1;
    public static final byte ROLE_NPC_ANIMATION_TYPE_SINGLE = 2;
    public static final byte ROLE_NPC_ANIMATION_TYPE_MULTI_ACTION = 3;
    public static final byte ROLE_STATE_EQUIP_STAMINA = 0;
    public static final byte ROLE_STATE_CARTON_EMAIL = 1;
    public static final byte ROLE_STATE_CARTON_EMAIL_PACKAGE = 2;
    public static final byte ROLE_STATE_CARTON_ROLE_UNSHOW_ROLE = 3;
    public static final byte ROLE_STATE_CARTON_ROLE_GM = 4;
    public static final byte ROLE_STATE_CARTON_FIGHTING = 5;
    public static final short[] ROLE_STATE_ICON = { 19, 18, 18, 17, 16, 10 };
    public static final byte ROLE_STATE_ICON_TIMEER_MAX = 20;
    public static final byte ROLE_STATE_ICON_X_DISTANCE = 10;
    public static final byte ROLE_STATE_ICON_SIZE = 10;
    public static final String TASK_SHINE_PIC = "questitem";
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
    public static final byte[][] BOX_FIXED = { { 1, 1 }, { 2, 1 }, { 3, 1 }, { 4, 1 }, { 1, 5 }, { 2, 5 }, { 3, 5 }, { 4, 5 }, { 1, 2 }, { 1, 4 } };
    public static final short VIEW_ANGLE_WIDTH = 18;
    public static final short VIEW_ANGLE_HEIGHT = 18;
    public static final int[] VIEW_ANGLE_BORDER_COLOR = { 11370322, 6494722, -2911170, 16753705 };
    public static final short VIEW_ANGLE_MAPPING = 1;
    public static final short VIEW_ICON_MAPPING = 2;
    public static final short ROLE_VIEW_CLIP_WIDTH = 115;
    public static final short ROLE_VIEW_CLIP_HEIGHT = 105;
    public static final int ROLE_VIEW_PADDING = 5;
    public static final int ROLE_VIEW_BODY_COLOR = 14995858;
    public static final int ROLE_VIEW_BORDER_COLOR = 9263661;
    public static final int ROLE_VIEW_BOX_WIDTH = 20;
    public static final int ROLE_VIEW_BOX_HEIGHT = 20;
    public static final int ROLE_VIEW_BOX_BACK_COLOR = 20;
    public static final int[] ROLE_VIEW_BOX_BORDER_COLOR = { 11370322, 6494722, -2911170 };
    public static final short ROLE_ABILITY_CELL_NUM_FIXED = 25;
    public static final short ROLE_ABILITY_CELL_HEIGHT = 18;
    public static final short ROLE_ABILITY_CELL_WIDTH = 56;
    public static final short ROLE_ABILITY_CHAGE_WIDTH = 40;
    public static final short ROLE_ABILITY_WIDTH = 96;
    public static final int[] ROLE_ABILITY_CELL_COLOR = { 16642234, 14995858 };
    public static final int POPUP_BORDER_COLOR = 9263661;
    public static final int POPUP_BACK_COLOR = 16314576;
    public static final int POPUP_FORE_COLOR = 8142636;
    public static final int POPUP_TITLE_FORE_COLOR = 97530;
    public static final int POPUP_ARROWS_SPACE = 10;
    public static final short POPUP_ARC_SIZE = 17;
    public static final short POPUP_MAPPING_SIZE = 6;
    public static final short POPUP_TRIANGLE_BOTTOM_SIZE = 20;
    public static final short POPUP_TRIANGLE_INCHING_SIZE = 1;
    public static final short SET_STRING_SWITCH_X = 20;
    public static final short SET_STRING_SWITCH_Y = 50;
    public static final short SET_BOX_SWITCH_X = (short)(Macro.SCREEN_WIDTH / 2);
    public static final short SET_BOX_SWITCH_W = 100;
    public static final short SET_BOX_SWITCH_H = (short)(Macro.FONTHEIGHT + 5);
    public static final String INLAY_TITLE = "Đá quý";
    public static final String INLAY_TITLE_NAME = "Túi";
    public static final String INLAY_SLOGIN = "Vui lòng chọn lỗ bạn muốn đặt.";
    public static final String INLAY_REMOVE = "Vui lòng chọn lỗ bạn muốn tháo";
    public static final byte INLAY_NULL_HOLE = 0;
    public static final byte INLAY_HIT_HOLE = 1;
    public static final byte INLAY_SPLINTERING_HOLE = 2;
    public static final byte INLAY_FULL_HOLE = 3;
    public static final byte INLAY_SPARK_HOLE = 4;
    public static final byte INLAY_HOLE = 5;
    public static final byte INLAY_SHOW_ROW = 6;
    public static final String[] HOLE_STATE = { "Unpunched", "Không dát", "Phân mảnh", "Hoàn thành", "Flash" };
    public static final String LOAD_RESOURCE_WORD = "Vui lòng chờ trong khi tải tài nguyên ...";
    public static final byte NPC_AREA = 4;
    public static final byte TASK_AREA = 4;
    public static final byte BOX_AREA = 4;
    public static final byte TACITLY_APPROVE_AREA = 4;
    public static final byte DEAL_AREA = 6;
    public static final byte FRIGHT_AREA = 6;
    public static final int CHOOSE_ROLE_RECT = 16711680;
    public static final byte FRAME_OFF_Y = 4;
    public static final String POINTS_UNIT = "Điểm số";
    public static final int POINTS_COLOR = 16711680;
    public static final int MALL_NOTES_COLOR = 16711680;
    public static final int MALL_INPUT_BOX_HEIGHT = 24;
    public static final int MALL_INPUT_CARD_LENTH = 24;
    public static final byte UI_STRING_TIP = 0;
    public static final byte UI_TOOLTIP_TIP = 1;
    public static final byte UI_TOOLTIP_AND_EVENT_TIP = 2;
    public static final byte UI_COMPLEX_TIP = 3;
    public static final byte UI_TOOLTIP_CONFIM_CANCEL_TIP = 4;
    public static final byte RM_DOWMLOAD_LIST_SIZE = 50;
}