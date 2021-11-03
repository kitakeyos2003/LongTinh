// 
// Decompiled by Procyon v0.5.36
// 
package model;

public interface IRoleDefine {

    public static final int ROLE_PARTS_NULL = -1;
    public static final int ROLE_PARTS_NULL_2 = 0;
    public static final byte ROLE_PARTS_SYNC_NONE = 0;
    public static final byte ROLE_PARTS_SYNC_FORCE = 1;
    public static final int ROLE_PARTS_RES_PNG = 0;
    public static final int ROLE_PARTS_RES_ANU = 1;
    public static final int ROLE_PARTS_RES_COUNT = 2;
    public static final int ROLE_COLNE_ID = -9999;
    public static final int ROLE_PARTS_DEFAULT_INDEX = 0;
    public static final int ROLE_PARTS_DEFAULT_NUM = 1;
    public static final int ROLE_PARTS_UP_LAYER_SKILL_NUM_MAX = 5;
    public static final int ROLE_PARTS_DOWN_LAYER_SKILL_NUM_MAX = 5;
    public static final byte ROLE_PARTS_UP_LAYER = 1;
    public static final byte ROLE_PARTS_DOWN_LAYER = 2;
    public static final byte ROLE_PARTS_LAYER_COUNT = 3;
    public static final int ROLE_SKILL_TARGET_TYPE_NULL = -1;
    public static final int ROLE_SKILL_TARGET_TYPE_SELF = 0;
    public static final int ROLE_SKILL_TARGET_TYPE_ENEMY = 1;
    public static final int ROLE_SKILL_TARGET_TYPE_FRIEND = 2;
    public static final int ROLE_SKILL_TARGET_TYPE_FRIEND_DEAD = 3;
    public static final int ROLE_SKILL_TARGET_NOT_NEED = 0;
    public static final int ROLE_SKILL_TARGET_NEED = 1;
    public static final int ROLE_SKILL_CONTINUS_INSTANT = 0;
    public static final int ROLE_SKILL_DIRECTION_SINGLE = 0;
    public static final int ROLE_SKILL_DIRECTION_MULTI = 1;
    public static final int ROLE_SKILL_FRAME_MAX_NONE = 0;
    public static final int ROLE_SKILL_FRAME_MAX_FOREVER = -1;
    public static final byte ROLE_SKILL_END_TYPE = 0;
    public static final byte ROLE_SKILL_END_ID = 1;
    public static final byte ROLE_SKILL_END_PIC = 2;
    public static final byte ROLE_SKILL_END_ANU = 3;
    public static final byte ROLE_SKILL_END_LAYER = 4;
    public static final byte ROLE_SKILL_END_DISPLAY_OFF = 5;
    public static final byte ROLE_SKILL_END_COUNT = 6;
    public static final int ROLE_SKILL_NULL = -1;
    public static final int ROLE_SELECT_NULL = -1;
    public static final byte ROLE_SKILL_DEFAULT_MUSIC = 1;
    public static final byte ROLE_SKILL_DEFAULT_LAYER = 0;
    public static final byte ROLE_SKILL_DEFAULT_LOOP = 1;
    public static final byte ROLE_SKILL_DEFAULT_DELAY = 0;
    public static final byte ROLE_SKILL_TYPE_NONE = -1;
    public static final byte ROLE_SKILL_TYPE_ACTIVE_ATTACK = 0;
    public static final byte ROLE_SKILL_TYPE_PASSIVE_ATTACK = 1;
    public static final byte ROLE_SKILL_TYPE_ALL = 2;
    public static final byte ROLE_SKILL_TYPE_ATTACK_EFFECT = 0;
    public static final byte ROLE_SKILL_TYPE_ATTACKED_EFFECT = 1;
    public static final byte ROLE_SKILL_TYPE_BUF = 1;
    public static final byte ROLE_SKILL_TYPE_DEBUF = 2;
    public static final int ROLE_SKILL_IMMOBILITY_TIME = 1000;
    public static final int ROLE_SKILL_LAYER_UP = 1;
    public static final int ROLE_SKILL_LAYER_DOWN = 2;
    public static final int ROLE_SKILL_DISPLAY_POS_NONE = 0;
    public static final int ROLE_SKILL_DISPLAY_POS_BOTTOM = 1;
    public static final int ROLE_SKILL_DISPLAY_POS_CENTER = 2;
    public static final int ROLE_SKILL_DISPLAY_POS_TOP = 3;
    public static final int ROLE_SKILL_DISPLAY_POS_COUNT = 4;
    public static final int ROLE_FLAG_ATTACK_PK = 1;
    public static final int ROLE_FLAG_ATTACK_NON_PK = 2;
    public static final int ROLE_FLAG_NON_ATTACK_PK = 3;
    public static final int ROLE_FLAG_NON_ATTACK_NON_PK = 4;
    public static final int ROLE_FLAG_ATTACK_PK_COUNT = 5;
    public static final int ROLE_FLAG_NON_ATTACK = 0;
    public static final int ROLE_FLAG_ATTACK = 1;
    public static final int ROLE_FLAG_NON_SEE = 0;
    public static final int ROLE_FLAG_SEE = 1;
    public static final int ROLE_INSTALL_NOW = 1;
    public static final int ROLE_CHANGE_PARTS_ON = 0;
    public static final int ROLE_CHANGE_PARTS_OFF = 1;
    public static final int ROLE_CHANGE_MSG_PARTS_HEADWEAR = 0;
    public static final int ROLE_CHANGE_MSG_PARTS_EQUIP = 1;
    public static final int ROLE_CHANGE_MSG_PARTS_WEAPON = 4;
    public static final short ROLE_PORTRAIT_BASE_PNG_ID = 1301;
    public static final int ROLE_PORTRAIT_NUM = 8;
    public static final byte ROLE_STATE_CARTON_NONE = 0;
    public static final byte ROLE_STATE_CARTON_ENTER_JUMP_IN = 1;
    public static final byte ROLE_STATE_CARTON_LEAVE_JUMP_IN = 2;
    public static final byte ROLE_STATE_CARTON_LEVEL_UP = 3;
    public static final String ROLE_LEVEL_UP_RES = "levelup";
    public static final int ROLE_LEVEL_UP_ANIMATION_ID = 0;
    public static final String ROLE_ENTER_JUMP_RES = "transporteffect01";
    public static final int ROLE_ENTER_JUMP_ANIMATION_ID = 0;
    public static final int MONSTER_PARAM_PNG_ID = 0;
    public static final int MONSTER_PARAM_ANU_ID = 1;
    public static final int MONSTER_PARAM_NUM = 2;
    public static final int MONSTER_PARAM_SIZE = 3;
    public static final int MONSTER_PARAM_HEIGHT = 4;
    public static final int MONSTER_PARAM_SHADOW_SIZE = 5;
    public static final int MONSTER_PARAM_COUNT = 6;
    public static final int MONSTER_PORTRAIT_ANIM_ID = 0;
    public static final int MONSTER_PORTRAIT_OTHER_ANIM_WIDTH = 25;
    public static final int MONSTER_PORTRAIT_OTHER_ANIM_HEIGHT = 25;
    public static final int MONSTER_PORTRAIT_LEFT_X_OFF = 19;
    public static final int MONSTER_PORTRAIT_LEFT_Y_OFF = 15;
    public static final int MONSTER_PORTRAIT_RIGHT_X_OFF = 3;
    public static final int MONSTER_PORTRAIT_RIGHT_Y_OFF = 3;
    public static final int MONSTER_PORTRAIT_HP_MP_X_OFF = 3;
    public static final int MONSTER_PORTRAIT_HP_Y_OFF = 7;
    public static final int MONSTER_PORTRAIT_MP_Y_OFF = 14;
    public static final int MONSTER_PORTRAIT_LV_Y_OFF = 17;
    public static final int ROLE_PORTAIT_RIGHT_X_OFF = 20;
    public static final String MONSTER_DEAD_RES = "deadman";
    public static final int MONSTER_DEAD_ANIMATION_ID = 0;
    public static final int MONSTER_DEAD_FLASH_FRAME_MAX = 5;
    public static final int MONSTER_CHAT_SELF_TIME = 50;
    public static final byte[] MONSTER_SPEED = {8, 6, 4, 3, 2};
    public static final byte NPC_DEFAULT_ANIMATION_PORTRAIT_ID = 0;
    public static final byte NPC_DEFAULT_ANIMATION_ID = 1;
    public static final byte ROLE_STATE_CONCEL_NONE = 0;
    public static final byte ROLE_STATE_CONCEL_EXCEPT_ME = 1;
    public static final byte ROLE_STATE_CONCEL = 2;
    public static final int TASK_PARAM_ID = 0;
    public static final int TASK_PARAM_PNG_ID = 1;
    public static final int TASK_PARAM_ANU_ID = 2;
    public static final int TASK_PARAM_PIC_SIZE = 3;
    public static final int TASK_PARAM_HEIGHT = 4;
    public static final int TASK_PARAM_SHADOW_SIZE = 5;
    public static final int TASK_PARAM_COUNT = 6;
    public static final int TASK_BOX_STATE_NONE = -1;
    public static final int TASK_BOX_STATE_PORTRAIT = 0;
    public static final int TASK_BOX_STATE_NORMAL = 1;
    public static final int TASK_BOX_SHINE_ANIMATION = 0;
    public static final int TEAM_STATE_OCCU_NONE = 0;
    public static final int TEAM_STATE_OCCU_TEAMER = 1;
    public static final int TEAM_STATE_OCCU_SUB_LEADER = 2;
    public static final int TEAM_STATE_OCCU_LEADER = 3;
    public static final String MONSTER_BOX_PNG_ID = "bag";
    public static final String MONSTER_BOX_ANU_ID = "bag";
    public static final int BOX_STATE_NONE = -1;
    public static final int BOX_STATE_PORTRAIT = 0;
    public static final int BOX_STATE_NORMAL = 1;
    public static final int BOX_STATE_SHINE = 2;
    public static final int BOX_STATE_DISAPPEAR = 3;
    public static final int ROLE_COUNRTY_HUMANG = 1;
    public static final int ROLE_COUNRTY_GHOST = 2;
    public static final int ROLE_COUNTRY_COUNT = 2;
    public static final int ROLE_COUNTRY_ALL = 2;
    public static final int ROLE_OCCU_PHYSICS = 1;
    public static final int ROLE_OCCU_RANGER = 2;
    public static final int ROLE_OCCU_MAGIC = 3;
    public static final int ROLE_OCCU_PRIEST = 4;
    public static final int ROLE_OCCU_COUNT = 4;
    public static final int ROLE_GENDER_MALE = 1;
    public static final int ROLE_GENDER_FEMALE = 2;
    public static final int ROLE_GENDER_COUNT = 2;
    public static final int ROLE_GENDER_ALL = 2;
    public static final int ROLE_WEAPON_TYPE_MAX = 5;
    public static final int ROLE_WEAPON_DAGGER = 601;
    public static final int ROLE_WEAPON_HAMMER = 602;
    public static final int ROLE_WEAPON_STICK = 603;
    public static final int ROLE_WEAPON_BOW = 604;
    public static final int ROLE_WEAPON_SWORD = 605;
    public static final int ROLE_WEAPON_COUNT = 606;
    public static final int ROLE_WEAPON_ALL = 606;
    public static final byte dIRECTION_UP = 0;
    public static final byte dIRECTION_DOWN = 1;
    public static final byte dIRECTION_LEFT = 2;
    public static final byte dIRECTION_RIGHT = 3;
    public static final byte dIRECTION_COUNT = 4;
    public static final int ROLE_ANIM_IMAGE_WIDTH = 29;
    public static final int ROLE_ANIM_IMAGE_HEIGHT = 48;
    public static final byte ROLE_ANIM_IMAGE_PIC_SIZE = 1;
    public static final int ROLE_PORTRAIT_IMAGE_X = 20;
    public static final int ROLE_PORTRAIT_IMAGE_Y = 20;
    public static final int ROLE_PORTRAIT_IMAGE_SIZE_WIDTH = 20;
    public static final int ROLE_PORTRAIT_IMAGE_SIZE_HEIGHT = 20;
    public static final int ROLE_PORTRAIT_IMAGE_ROLE_WIDTH = 40;
    public static final int ROLE_PORTRAIT_IMAGE_ROLE_HEIGHT = 40;
    public static final int ROLE_CHECK_RES_DISTANT_TIME = 500;
    public static final byte[] ROLE_SPEED = {8, 6, 3, 2, 1};
    public static final int EQUIP_ANIM_STEP = 5;
    public static final byte ROLE_HEAD = 0;
    public static final byte ROLE_HAIR = 1;
    public static final byte ROLE_HEADWEAR = -1;
    public static final byte ROLE_EQUIP = 2;
    public static final byte ROLE_LEG = 3;
    public static final byte ROLE_WEAPON = 4;
    public static final byte ROLE_PET = 5;
    public static final byte ROLE_EFFECT_EQUIP = 6;
    public static final byte ROLE_EFFECT_EQUIP_ROUND = 7;
    public static final byte ROLE_EFFECT_WEAPON_ATTACK = 8;
    public static final byte ROLE_EFFECT_WEAPON_ROUND = 9;
    public static final byte ROLE_EFFECT_SKILL_UP_LAYER = 10;
    public static final byte ROLE_EFFECT_SKILL_DOWN_LAYER = 11;
    public static final byte ROLE_SOUL_DEAD = 12;
    public static final byte ROLE_DEMON_TAIL = 13;
    public static final byte ROLE_SHADE = 14;
    public static final byte ROLE_PARTS_COUNT = 15;
    public static final byte ROLE_ACTION_STAND = 0;
    public static final byte ROLE_ACTION_WALK = 1;
    public static final byte ROLE_ACTION_ATTACK_BJ = 2;
    public static final byte ROLE_ACTION_ATTACK_KAN = 3;
    public static final byte ROLE_ACTION_ATTACK_KAN_2 = 4;
    public static final byte ROLE_ACTION_STAND_CHONG = 5;
    public static final byte ROLE_ACTION_WALK_CHONG = 6;
    public static final byte ROLE_ACTION_ATTACK_BISHOUCI = 7;
    public static final byte ROLE_ACTION_ATTACK_GONGJIAN = 8;
    public static final byte ROLE_ACTION_ATTACK_SIFA = 9;
    public static final byte ROLE_ACTION_ATTACK_HOUJIAO = 10;
    public static final byte ROLE_ACTION_DEAD = 11;
    public static final byte ROLE_ACTION_COUNT = 12;
    public static final byte[][] ROLE_LAYER_TABLE = {{14, 11, 3, 4, 2, 9, 0, 1, -1, 13, 7, 6, 10}, {14, 11, 13, 3, 2, 0, 1, -1, 4, 9, 7, 6, 10}, {14, 11, 13, 3, 2, 0, 1, -1, 4, 9, 7, 6, 10}, {14, 11, 4, 9, 13, 3, 2, 0, 1, -1, 7, 6, 10}, {14, 11, 4, 9, 13, 3, 2, 0, 1, -1, 7, 6, 10}, {14, 11, 3, 2, 0, 1, -1, 13, 4, 9, 7, 7, 6, 10}, {14, 11, 4, 9, 13, 3, 2, 0, 1, -1, 7, 6, 10}, {14, 11, 4, 9, 13, 3, 2, 0, 1, -1, 7, 6, 10}, {14, 11, 4, 9, 0, 1, -1, 13, 3, 2, 7, 6, 10}, {14, 11, 4, 9, 0, 1, -1, 13, 3, 2, 7, 6, 10}, {14, 11, 3, 2, 0, 1, -1, 13, 4, 6, 10}, {14, 11, 4, 13, 3, 2, 0, 1, -1, 6, 10}, {14, 11, 4, 13, 3, 2, 0, 1, -1, 6, 10}, {14, 11, 4, 13, 3, 2, 0, 1, -1, 6, 10}, {14, 11, 4, 13, 3, 2, 0, 1, -1, 6, 10}, {14, 11, 8, 4, 3, 2, 0, 1, -1, 13, 6, 10}, {14, 11, 13, 3, 2, 0, 1, -1, 4, 8, 6, 10}, {14, 11, 13, 3, 2, 0, 1, -1, 4, 8, 6, 10}, {14, 11, 0, 1, -1, 3, 13, 2, 4, 8, 6, 10}, {14, 11, 0, 1, -1, 3, 13, 2, 4, 8, 6, 10}, {14, 11, 8, 4, 3, 2, 0, 1, -1, 13, 6, 10}, {14, 11, 13, 3, 2, 0, 1, -1, 4, 8, 6, 10}, {14, 11, 13, 3, 2, 0, 1, -1, 4, 8, 6, 10}, {14, 11, 0, 1, -1, 3, 13, 2, 4, 8, 6, 10}, {14, 11, 0, 1, -1, 3, 13, 2, 4, 8, 6, 10}, {14, 11, 5, 3, 2, 0, 1, -1, 13, 4, 9, 7, 10}, {14, 11, 4, 9, 3, 2, 0, 1, -1, 7, 5, 10}, {14, 11, 4, 9, 3, 2, 0, 1, -1, 7, 5, 10}, {14, 11, 4, 9, 5, 13, 3, 2, 0, 1, -1, 7, 10}, {14, 11, 4, 9, 5, 13, 3, 2, 0, 1, -1, 7, 10}, {14, 11, 5, 3, 2, 0, 1, -1, 13, 4, 9, 7, 10}, {14, 11, 4, 9, 3, 2, 0, 1, -1, 7, 5, 10}, {14, 11, 4, 9, 3, 2, 0, 1, -1, 7, 5, 10}, {14, 11, 4, 9, 5, 13, 3, 2, 0, 1, -1, 7, 10}, {14, 11, 4, 9, 5, 13, 3, 2, 0, 1, -1, 7, 10}, {14, 11, 8, 4, 3, 2, 0, 1, -1, 13, 10}, {14, 11, 3, 13, 2, 0, 1, -1, 4, 8, 10}, {14, 11, 3, 13, 2, 0, 1, -1, 4, 8, 10}, {14, 11, 8, 4, 0, 1, -1, 3, 13, 2, 10}, {14, 11, 8, 4, 0, 1, -1, 3, 13, 2, 10}, {14, 11, 8, 4, 3, 13, 2, 0, 1, -1, 10}, {14, 11, 3, 13, 2, 0, 1, -1, 4, 8, 10}, {14, 11, 3, 13, 2, 0, 1, -1, 4, 8, 10}, {14, 11, 0, 1, -1, 3, 13, 2, 4, 8, 10}, {14, 11, 0, 1, -1, 3, 13, 2, 4, 8, 10}, {14, 11, 4, 3, 13, 2, 0, 1, -1, 10}, {14, 11, 13, 3, 2, 0, 1, -1, 4, 10}, {14, 11, 13, 3, 2, 0, 1, -1, 4, 10}, {14, 11, 4, 0, 1, -1, 3, 13, 2, 10}, {14, 11, 4, 0, 1, -1, 3, 13, 2, 10}, {14, 11, 3, 2, 0, 1, -1, 13, 10}, {14, 11, 13, 3, 0, 1, -1, 2, 10}, {14, 11, 13, 3, 0, 1, -1, 2, 10}, {14, 11, 0, 1, -1, 3, 13, 2, 10}, {14, 11, 0, 1, -1, 3, 13, 2, 10}, {12}, {12}, {12}, {12}, {12}};
    public static final byte MONSTER_dIRECTION_LEFT = 0;
    public static final byte MONSTER_dIRECTION_RIGHT = 1;
    public static final byte MONSTER_dIRECTION_COUNT = 2;
    public static final byte MONSTER_ACTION_STAND = 0;
    public static final byte MONSTER_ACTION_WALK = 1;
    public static final byte MONSTER_ACTION_ATTACK = 2;
    public static final byte MONSTER_ACTION_COUNT = 3;
    public static final int dIRECTION_ALL = 4;
    public static final byte MONSTER_STATE_SEE_NONE = 0;
    public static final byte MONSTER_STATE_SEE = 1;
    public static final byte MONSTER_STATE_CARTON_LEAVE_FIGHTING = 1;
    public static final byte MONSTER_STATE_CARTON_CATCH_SOUL = 2;
    public static final byte MONSTER_STATE_CARTON_METAMORPHOSIS = 3;
    public static final byte MONSTER_STATE_CARTON_INVISIBILITY = 4;
    public static final byte MONSTER_STATE_CARTON_DISPLAY = 5;
    public static final byte PET_TYPE_AGGCHILD = 0;
    public static final byte PET_TYPE_RIDING = 1;
    public static final byte PET_TYPE_FIGHT = 2;
    public static final byte PET_ACTION_STAND = 0;
    public static final byte PET_ACTION_WALK = 1;
    public static final byte PET_ACTION_ATTACK = 2;
    public static final byte PET_ACTION_COUNT = 3;
    public static final byte[] ROLE_DEFUALT_PARTS = {0, 1, 3, 13, 12};
    public static final byte[] ROLE_FOUNDATION_PARTS = {-1, 2, 7, 4, 5, 6, 8, 9};
    public static final String[] ROLE_OCCU_NAME = {"\u529b\u58eb", "\u65a5\u5019", "\u6cd5\u5e08", "\u5deb\u533b", "\u91d1\u521a\u529b\u58eb", "\u64ce\u5929\u529b\u58eb", "\u7f57\u5239\u529b\u58eb", "\u4fee\u7f57\u529b\u58eb", "\u783a\u5251\u65a5\u5019", "\u795e\u7bad\u65a5\u5019", "\u8840\u5203\u65a5\u5019", "\u9b3c\u7fbf\u65a5\u5019", "\u6d74\u706b\u6cd5\u5e08", "\u5929\u673a\u6cd5\u5e08", "\u708e\u9b54\u6cd5\u5e08", "\u7384\u51a5\u6cd5\u5e08", "\u5999\u624b\u5deb\u533b", "\u7075\u6cc9\u5deb\u533b", "\u8840\u796d\u5deb\u5e08", "\u9634\u9633\u5deb\u533b"};
    public static final int ROLE_OCCU_BASE_NUM = 4;
    public static final int ROLE_OCCU_EXTEND_NUM = 8;
}
