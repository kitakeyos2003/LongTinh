// Decompiled with: Procyon 0.5.36
// Class Version: 1
package face;

import common.Pram;
import java.util.Calendar;
import java.util.Date;
import common.LayoutStyle;
import common.ScrollText;
import model.PackageObject;
import java.util.Random;
import model.MenuObject;
import means.FightValue;
import means.AStar;
import network.NetSend;
import network.FakeServer;
import means.DebugFrame;
import model.SkillObject;
import common.Common;
import model.TeamObject;
import means.StringManager;
import model.ORole;
import means.ChatContent;
import common.DrawBase;
import javax.microedition.lcdui.Graphics;
import base.GameControl;
import model.ORPMe;
import model.ORMonster;
import java.util.Enumeration;
import model.Box;
import model.Task;
import model.ODecorative;
import model.AnimalObject;
import model.PetObject;
import model.Map;
import model.ONpc;
import model.ORPlayer;
import means.ImageManager;
import base.DCanvas;
import means.ChatContentFactory;
import means.Rms;
import base.Macro;
import base.Param;
import common.PopupDialog;
import javax.microedition.lcdui.Image;
import java.util.Hashtable;
import means.QSprite;
import java.util.Vector;
import common.IDefines;

public class GameUI implements UIbase, IDefines {

    private final String STRING_CUE_DUEL_REMAIN;
    private final String STRING_CUE_SEC;
    private final String STRING_CUE_SWITCHMAP;
    public final String STRING_CUE_DOUBT;
    private final String STRING_CUE_SMALL_MAP;
    public final String STRING_CUE_DEAD;
    public final String STRING_CUE_REVIVAL;
    public final String STRING_TITLE_CHAT;
    public final String STRING_OPTION_AREA;
    public final String STRING_OPTION_WORLD;
    public final String STRING_OPTION_ASTAR;
    public final String STRING_OPTION_SEE;
    public final String STRING_OPTION_PERSONAL;
    public final String STRING_OPTION_DEAL;
    public final String STRING_OPTION_DUEL;
    public final String STRING_OPTION_FRIEND;
    public final String STRING_OPTION_TROOP_INVITE;
    private final String STRING_CUE_FIXEDLY;
    private final String STRING_CUE_EXP;
    private final String STRING_CUE_MONEY;
    private final String STRING_TITLE_ARM;
    private final String STRING_TITLE_CESSION;
    private final String STRING_CUE_DUEL_FITOUT;
    private final String STRING_CUE_DUEL_OVER;
    private final String STRING_CUE_DUEL_START;
    public final String STRING_JIA;
    public final String STRING_CUE_BLANK;
    public int intSendMenuId;
    public byte bytDrawType;
    public byte bytBuffIndex;
    public byte[] bytsButtonType;
    public byte bytKeySelNum;
    public String strKeySelInfo;
    public short shtShowKeyInfoTime;
    public String strSkilful;
    public byte bytLevel;
    public int intNowSkilful;
    public int intMaxSkilful;
    public byte bytMakeSkill;
    public boolean blnFight;
    public String strReliveName;
    public byte bytReliveX;
    public byte bytReliveY;
    public int intReliveHp;
    public int intReliveMp;
    public boolean blnPK;
    public int intPKRoleId;
    public int intPKTime;
    public int intPKTempTime;
    public int TimeCycle;
    private static GameUI gu;
    private boolean blnIsThis;
    private byte bytSelectTime;
    private short shtMagiclength;
    private Vector vecFightInfo;
    public boolean blnChannelType;
    private int intChatShowTime;
    private boolean isShowMsg;
    public byte bytMsgShowType;
    public byte bytMsgNumber;
    public byte bytMsgSpeed;
    private short shtMeInfoTime;
    public byte bytMapState;
    private QSprite sprSmallMapIcon;
    private String strMapName;
    private short[][] shtsMapPlaceXY;
    public byte[][] bytsMapPlaceTileXY;
    private short[][] shtMapArrayPlaceTileXY;
    private short[] shtsMapflag;
    private String[] strsMapPlaceName;
    public byte bytMoveMapPointXY;
    public byte bytMoveMapPointXYCurrent;
    public short[] shtMoveMapFlagXY;
    public short shtMoveMapID;
    private byte bytDrawMapflagframe;
    private int[][] intsGetExpGold;
    public boolean blnChatFace;
    public byte bytHaveNpcChat;
    private int intNpcChatTime;
    private int intNpcChatId;
    public boolean blnHaveMonsterChat;
    private int intMonsterChatTime;
    private int intMonsterChatId;
    private short[] shtStateIcon;
    public Hashtable htImgBuffIcon;
    private byte bytDrawMoveX;
    private byte bytDrawMoveY;
    private byte bytDrawMoveTime;
    private static long lngMovePointCount;
    private byte bytMaxChat;
    private Image imgProtraitLV;
    public byte[] bytMeLevel;
    public byte[] bytMeSelLevel;
    public byte bytShortcutKeysType;
    public static PopupDialog popupDialogInstance;
    private int intSelectTime;
    private boolean blnUpdateAutoSelect;
    private boolean blnUpdateManuSelectAll;
    private boolean blnUpdateManuSelectFriends;
    private boolean blnUpdateSelect;
    public static boolean blnNextChargeMenu;
    public static boolean blnCloseMenuAfterChargeMenu;
    public static byte[] bytNextChargeOldMenu;
    public static final int MINI_MAP_BG_COLOR = 7175012;
    public static final int MINI_MAP_NPC_COLOR = 16701448;
    public static final int MINI_MAP_MONSTER_COLOR = 15807279;
    public static final int MINI_MAP_PLAYER_COLOR = 65280;
    public static final int MIMI_MAP_TEAM_PLAYER = 1631485;
    public static final int MINI_MAP_ME_COLOR = 9556779;
    public static final int MINI_MAP_ME_BORDER_COLOR = 7273289;
    public static final int MINI_UNPASSABLE_GRID_COLOR = 13678730;
    public static final int MINI_MAP_JUMP_POINT_COLOR_ON = 15982228;
    public static final int MINI_MAP_JUMP_POINT_COLOR_OFF = 15584346;
    public static final int MINI_MAP_BORDER_COLOR = 5845015;
    public static final int MINI_MAP_ICON_SIZE = 2;
    public static final int MINI_MAP_JUMPPOINT_WIDTH = 2;
    public static final int MINI_MAP_JUMPPOINT_HEIGHT = 2;
    private int mapTileWidth;
    private int mapTileHeight;
    private static final int FLASH_INTERVAL = 8;
    private int flashCount;
    public byte bytbHotKeyExepHeight;
    public short shtHotKeyXOff;
    public byte bytHotKeyBoxContentHeightYOff;
    public byte bytHotKeyBoxDistance;
    public byte bytHotKeyBoxWidth;
    public byte bytHotKeyBoxHeight;
    public byte bytHotKeyIconDistance;
    public short shtDownHotKeyXOff;
    public short shtDownHotKeyW;
    QSprite RoleBM;
    QSprite newMail;
    QSprite TeamBM;
    QSprite sprPortraitRoleSelected;
    QSprite DarwMove;
    public boolean blnNewMail;
    private int mouseframe;
    private int mouseindex;
    QSprite[] radaricon;
    public QSprite[] sprHotKey;
    public QSprite Menukey;
    private String MenuRescure;
    private int LeftUpX;
    private int LeftUpY;
    public int LeftDownX;
    public int LeftDownY;
    private int LeftCentreX;
    private int RightCentreX;
    private int RightUpX;
    private int RightUpY;
    private int RightDownX;
    private int RightDownY;
    private int HideShurtCut;
    private boolean HideOFF;
    public QSprite Number;
    private String NumberResource;
    public byte hotKeyMenuSwitch;
    private byte teamheadwidth;
    private byte teamheadheight;
    private byte headheight;
    int _drawMeOffX;
    int newMailAnimation;
    private float k;
    private Vector vInformation;
    private int fightValueOffsetX;
    private int fightValueOffsetY;
    private boolean showFightInformation;
    private byte DIR_LEFT;
    private byte DIR_RIGHT;
    private byte bytMoneyIndex;
    private int[] smallRectColor;
    public final int BACKSMALLRECT_W;
    public final int BACKSMALLRECT_H;
    public Vector[] vsColorInfo;
    private byte bytHour;
    private byte bytMin;
    private byte bytSec;
    private short shtOneSec;
    long lonFeeOnlineTime;
    long lonFeeMonsterTime;
    long lonFeeNowTime;
    public String strFeeOfflineTime;
    private static Vector targetRole;
    private static Vector targetManuRole;
    public static int lastSelectTargetIndex;
    public static final byte TARGET_PRIORITY_NONE = -1;
    public static final byte TARGET_PRIORITY_OTHER = 0;
    public static final byte TARGET_PRIORITY_FRIENDS = 1;
    public static final byte TARGET_PRIORITY_NPC = 2;
    public static final byte TARGET_PRIORITY_ENEMY = 3;

    static {
        GameUI.blnNextChargeMenu = false;
        GameUI.blnCloseMenuAfterChargeMenu = false;
        GameUI.targetRole = new Vector(1);
        GameUI.targetManuRole = new Vector(1);
        GameUI.lastSelectTargetIndex = -1;
    }

    public GameUI() {
        this.STRING_CUE_DUEL_REMAIN = "Phó bản thời gian còn lại: ";
        this.STRING_CUE_SEC = "Giây";
        this.STRING_CUE_SWITCHMAP = "Đến bản đồ: ";
        this.STRING_CUE_DOUBT = "？";
        this.STRING_CUE_SMALL_MAP = "Đang tải bản đồ ...";
        this.STRING_CUE_DEAD = "Bạn đã chết và tái sanh vào lúc hồi sinh gần đây?";
        this.STRING_CUE_REVIVAL = "Bạn có muốn được phục sinh không?";
        this.STRING_TITLE_CHAT = "Lịch sử trò chuyện";
        this.STRING_OPTION_AREA = "Bản đồ khu vực";
        this.STRING_OPTION_WORLD = "Bản đồ thế giới";
        this.STRING_OPTION_ASTAR = "Pathfinder";
        this.STRING_OPTION_SEE = "Xem";
        this.STRING_OPTION_PERSONAL = "Trò chuyện cá nhân";
        this.STRING_OPTION_DEAL = "Giao dịch";
        this.STRING_OPTION_DUEL = "Phó bản";
        this.STRING_OPTION_FRIEND = "Thêm vào làm bạn";
        this.STRING_OPTION_TROOP_INVITE = "Lời mời nhóm";
        this.STRING_CUE_FIXEDLY = "Không thể di chuyển";
        this.STRING_CUE_EXP = "Kinh nghiệm";
        this.STRING_CUE_MONEY = "Sò";
        this.STRING_TITLE_ARM = "Phổ Quang Vũ";
        this.STRING_TITLE_CESSION = "Truyền băng đảng";
        this.STRING_CUE_DUEL_FITOUT = "Giây sau khi bắt đầu cuộc đấu";
        this.STRING_CUE_DUEL_OVER = "Phó bản kết thúc";
        this.STRING_CUE_DUEL_START = "Phó bản bắt đầu";
        this.STRING_JIA = "+";
        this.STRING_CUE_BLANK = " ";
        this.bytBuffIndex = -1;
        this.bytKeySelNum = 0;
        this.strKeySelInfo = null;
        this.shtShowKeyInfoTime = 0;
        this.blnIsThis = false;
        this.isShowMsg = false;
        this.bytMsgShowType = 0;
        this.bytMsgNumber = 3;
        this.bytMsgSpeed = 5;
        this.bytMaxChat = 0;
        this.blnUpdateAutoSelect = false;
        this.blnUpdateManuSelectAll = false;
        this.blnUpdateManuSelectFriends = false;
        this.blnUpdateSelect = false;
        this.mapTileWidth = 0;
        this.mapTileHeight = 0;
        this.flashCount = 0;
        this.DarwMove = null;
        this.blnNewMail = false;
        this.mouseframe = -1;
        this.MenuRescure = "touch";
        this.NumberResource = "playerlevelnum";
        this.hotKeyMenuSwitch = 0;
        this.teamheadwidth = 38;
        this.teamheadheight = 33;
        this.headheight = 34;
        this._drawMeOffX = 0;
        this.newMailAnimation = -1;
        this.fightValueOffsetX = 0;
        this.fightValueOffsetY = -20;
        this.DIR_LEFT = 1;
        this.DIR_RIGHT = 2;
        this.smallRectColor = new int[]{6494722, 16777215, 16763066, 16753705};
        this.BACKSMALLRECT_W = 18;
        this.BACKSMALLRECT_H = 18;
        this.bytHour = 0;
        this.bytMin = 0;
        this.bytSec = 0;
        this.shtOneSec = 0;
        GameUI.gu = this;
    }

    public static GameUI getInstance() {
        return GameUI.gu;
    }

    public void setButton(final int n, final int n2, final int n3) {
        this.bytsButtonType[0] = (byte) n;
        this.bytsButtonType[1] = (byte) n2;
        this.bytsButtonType[2] = (byte) n3;
    }

    public void init() {
        Param.getInstance().bytOldChatChannel = 2;
        this.shtMagiclength = (short) (Macro.SCREEN_WIDTH / 3);
        if (Rms.bytChatRow != 3) {
            this.bytMsgNumber = (byte) (3 + Rms.bytChatRow * 2);
        } else {
            this.bytMsgNumber = 10;
        }
        new ChatContentFactory();
        this.bytsButtonType = new byte[3];
        DCanvas.getInstance().buttonLeftFlash = 0;
        DCanvas.getInstance().buttonRightFlash = 0;
        this.imgProtraitLV = ImageManager.CreateImage("num9", "ingame");
        this.bytDrawMoveX = -1;
        this.bytDrawMoveY = -1;
        if (DCanvas.getInstance().blnIsTouch) {
            this.DarwMove = ImageManager.loadSpriteById(3, ImageManager.EncodespriteId("mousepoint", "mousepoint"), "mousepoint", "mousepoint", "ingame");
        }
        this.initTime();
        this.bytDrawType = 0;
        this.intPKRoleId = -1;
        this.initSkin();
        this.vInformation = new Vector();
    }

    public void showChat() {
        this.intChatShowTime = 10000;
        this.isShowMsg = true;
    }

    public void addObject(final ORPlayer orPlayer) {
        if (Param.getInstance().htRolePlayer.containsKey(new Integer(orPlayer.intUserId)) && Param.getInstance().htRolePlayer.get(new Integer(orPlayer.intUserId)) != null) {
            this.delVObject(orPlayer);
        }
        Param.getInstance().htRolePlayer.put(new Integer(orPlayer.intUserId), orPlayer);
        if (DCanvas.getInstance().UMenu != null && MenuUI.getInstance().getState() == -38) {
            MenuUI.getInstance().addAboutRole(orPlayer);
        }
    }

    public void addObject(final ONpc oNpc) {
        Param.getInstance().htRoleNPC.put(new Integer(oNpc.intUserId), oNpc);
        if (!oNpc.blnIsTalk && oNpc.blnIsConvoy && !oNpc.blnIsActivation) {
            oNpc.NpcActivation();
            Map instance = Map.getInstance();
            ++instance.bytNpcConvoyNum;
            this.delTempVObject(oNpc);
        }
    }

    public void addObject(final PetObject petObject) {
        if (Param.getInstance().htRolePet == null) {
            Param.getInstance().htRolePet = new Hashtable();
        }
        Param.getInstance().htRolePet.put(new Integer(petObject.intUserId), petObject);
    }

    public void addVObject(final AnimalObject animalObject) {
        if (Param.getInstance().htAnimal == null) {
            Param.getInstance().htAnimal = new Hashtable();
        }
        Param.getInstance().htAnimal.put(new Integer(animalObject.intUserId), animalObject);
    }

    public void addObject(final ODecorative oDecorative) {
        if (Param.getInstance().htDecorative == null) {
            Param.getInstance().htDecorative = new Hashtable();
        }
        Param.getInstance().htDecorative.put(new Integer(oDecorative.intUserId), oDecorative);
    }

    public void addObject(final Task task) {
        if (Param.getInstance().htTask == null) {
            Param.getInstance().htTask = new Hashtable();
            Map.getInstance().bytsTaskXY = new byte[Map.getInstance().bytRow][Map.getInstance().bytColumn];
            Param.getInstance();
            Param.sprTaskShineNum = 0;
        }
        Param.getInstance().htTask.put(new Integer(task.intUserId), task);
        Map.getInstance().bytsTaskXY[task.bytTileY][task.bytTileX] = (byte) (task.blnPass ? 1 : 2);
    }

    public void addVObject(final Box box) {
        if (Param.getInstance().htBox == null) {
            Map.getInstance().bytsBoxXY = new byte[Map.getInstance().bytRow][Map.getInstance().bytColumn];
            Param.getInstance().htBox = new Hashtable();
        }
        if (Param.getInstance().htBox != null && !Param.getInstance().htBox.isEmpty()) {
            Enumeration elements = Param.getInstance().htBox.elements();
            while (elements.hasMoreElements()) {
                Box box2 = (Box) elements.nextElement();
                if (box.bytBoxType == box2.bytBoxType && box.intUserId == box2.intUserId) {
                    if (box.blnShine) {
                        byte[] array = Map.getInstance().bytsBoxXY[box.bytTileY];
                        byte bytTileX = box.bytTileX;
                        --array[bytTileX];
                    }
                    Param.getInstance().htBox.remove(new Integer(box2.intUserId));
                    break;
                }
            }
        }
        if ((box.intMonsterId == 0 || (Param.getInstance().htRoleMonster != null && !Param.getInstance().htRoleMonster.containsKey(new Integer(box.intMonsterId)))) && box.blnShine) {
            byte[] array2 = Map.getInstance().bytsBoxXY[box.bytTileY];
            byte bytTileX2 = box.bytTileX;
            ++array2[bytTileX2];
        }
        Param.getInstance().htBox.put(new Integer(box.intUserId), box);
    }

    public void addObject(final ORMonster orMonster) {
        if (orMonster == null) {
            return;
        }
        Param.getInstance().htRoleMonster.put(new Integer(orMonster.intUserId), orMonster);
    }

    public void delVObject(final int n, final int n2, final byte b) {
        if (Param.getInstance().htBox == null || Param.getInstance().htBox == null) {
            return;
        }
        if (Param.getInstance().htBox != null && !Param.getInstance().htBox.isEmpty()) {
            Enumeration elements = Param.getInstance().htBox.elements();
            while (elements.hasMoreElements()) {
                Box box = (Box) elements.nextElement();
                if (box.bytTileY == n2 && b == box.bytBoxType && n == box.intUserId) {
                    if (Param.getInstance().bytSelectType == 4 && Param.getInstance().intSelectId == n) {
                        ORPMe.ME.delSelsectRole();
                    }
                    if (box.blnShine) {
                        byte[] array = Map.getInstance().bytsBoxXY[box.bytTileY];
                        byte bytTileX = box.bytTileX;
                        --array[bytTileX];
                    }
                    box.setBoxAlive(false);
                    break;
                }
            }
        }
    }

    public void delVObject(final ORPlayer orPlayer) {
        if (orPlayer == null) {
            return;
        }
        if (Param.getInstance().oSelectRole != null && Param.getInstance().oSelectRole == orPlayer && DCanvas.getInstance().UMenu != null) {
            switch (MenuUI.getInstance().bytMenuState) {
                case -72:
                case -6: {
                    GameControl.getInstance().delUIbase(3);
                    break;
                }
            }
        }
        Param.getInstance().htRolePlayer.remove(new Integer(orPlayer.intUserId));
        if (Param.getInstance().bytSelectType == 2 && Param.getInstance().intSelectId == orPlayer.intUserId) {
            ORPMe.ME.delSelsectRole();
        }
        orPlayer.delRole();
        if (DCanvas.getInstance().UMenu != null && MenuUI.getInstance().getState() == -38) {
            MenuUI.getInstance().delAboutRole(orPlayer);
        }
    }

    public void delObject(final ORMonster orMonster) {
        if (orMonster == null) {
            return;
        }
        orMonster.delRole();
        if (Param.getInstance().bytSelectType == 3 && Param.getInstance().intSelectId == orMonster.intUserId) {
            ORPMe.ME.delSelsectRole();
        }
        Param.getInstance().htRoleMonster.remove(new Integer(orMonster.intUserId));
    }

    public void delObject(final ONpc oNpc) {
        if (oNpc == null) {
            return;
        }
        if (Param.getInstance().bytSelectType == 1 && Param.getInstance().intSelectId == oNpc.intUserId) {
            ORPMe.ME.delSelsectRole();
        }
        if (oNpc.blnIsActivation && Map.getInstance().bytNpcConvoyNum > 0) {
            Map instance = Map.getInstance();
            --instance.bytNpcConvoyNum;
            if (Param.getInstance().htNpcConvoy.containsKey(new Integer(oNpc.intUserId))) {
                Param.getInstance().htNpcConvoy.remove(new Integer(oNpc.intUserId));
                if (Param.getInstance().htNpcConvoy.isEmpty()) {
                    Param.getInstance().htNpcConvoy = null;
                }
            }
        }
        Param.getInstance().htRoleNPC.remove(new Integer(oNpc.intUserId));
        if (Map.getInstance().bytsNpcXY != null && !oNpc.blnIsActivation) {
            if (Map.getInstance().bytsNpcXY.length == 1) {
                Map.getInstance().bytsNpcXY = null;
            } else {
                int n = -1;
                for (int i = 0; i < Map.getInstance().bytsNpcXY.length; ++i) {
                    if (Map.getInstance().bytsNpcXY[i][0] == oNpc.bytTileY && Map.getInstance().bytsNpcXY[i][1] == oNpc.bytTileX) {
                        n = i;
                        break;
                    }
                }
                if (n != -1) {
                    byte[][] bytsNpcXY = Map.getInstance().bytsNpcXY;
                    Map.getInstance().bytsNpcXY = new byte[bytsNpcXY.length - 1][2];
                    if (n != 0) {
                        System.arraycopy(bytsNpcXY, 0, Map.getInstance().bytsNpcXY, 0, n);
                    }
                    if (n != bytsNpcXY.length - 1) {
                        System.arraycopy(bytsNpcXY, n + 1, Map.getInstance().bytsNpcXY, n, bytsNpcXY.length - n - 1);
                    }
                }
            }
        }
    }

    public void delTaskObject(final int n, final byte b) {
        if (Param.getInstance().htTask == null) {
            return;
        }
        if (Param.getInstance().htTask != null && !Param.getInstance().htTask.isEmpty()) {
            Enumeration elements = Param.getInstance().htTask.elements();
            while (elements.hasMoreElements()) {
                Task task = (Task) elements.nextElement();
                if (task.bytTileY == b && n == task.intUserId) {
                    if (Param.getInstance().bytSelectType == 5 && Param.getInstance().intSelectId == n) {
                        if (MenuUI.getInstance().getState() == 1) {
                            GameControl.getInstance().delUIbase(MenuUI.getInstance().bytDelState);
                        }
                        ORPMe.ME.delSelsectRole();
                    }
                    Map.getInstance().bytsTaskXY[task.bytTileY][task.bytTileX] = 0;
                    task.delTask();
                    Param.getInstance().htTask.remove(new Integer(task.intUserId));
                    break;
                }
            }
        }
        if (Param.getInstance().htTask.isEmpty()) {
            Param.getInstance().htTask = null;
        }
    }

    public void delTempVObject(final ONpc oNpc) {
        if (Param.getInstance().bytSelectType == 1 && Param.getInstance().intSelectId == oNpc.intUserId) {
            ORPMe.ME.delSelsectRole();
        }
        if (Map.getInstance().bytsNpcXY.length == 1) {
            Map.getInstance().bytsNpcXY = null;
        } else {
            int n = -1;
            for (int i = 0; i < Map.getInstance().bytsNpcXY.length; ++i) {
                if (Map.getInstance().bytsNpcXY[i][0] == oNpc.bytTileY && Map.getInstance().bytsNpcXY[i][1] == oNpc.bytTileX) {
                    n = i;
                    break;
                }
            }
            if (n != -1) {
                byte[][] bytsNpcXY = Map.getInstance().bytsNpcXY;
                Map.getInstance().bytsNpcXY = new byte[bytsNpcXY.length - 1][2];
                if (n != 0) {
                    System.arraycopy(bytsNpcXY, 0, Map.getInstance().bytsNpcXY, 0, n);
                }
                if (n != bytsNpcXY.length - 1) {
                    System.arraycopy(bytsNpcXY, n + 1, Map.getInstance().bytsNpcXY, n, bytsNpcXY.length - n - 1);
                }
            }
        }
    }

    public void delVObject(final PetObject petObject) {
        if (Param.getInstance().htRolePet != null && Param.getInstance().htRolePet.contains(petObject)) {
            Param.getInstance().htRolePet.remove(new Integer(petObject.intUserId));
        }
        if (Param.getInstance().htRolePet.size() == 0) {
            Param.getInstance().htRolePet = null;
        }
    }

    public void delVObject(final AnimalObject animalObject) {
        if (Param.getInstance().htAnimal != null && Param.getInstance().htAnimal.contains(animalObject)) {
            Param.getInstance().htAnimal.remove(new Integer(animalObject.intUserId));
        }
        if (Param.getInstance().htAnimal.size() == 0) {
            Param.getInstance().htAnimal = null;
        }
    }

    public void delVObject(final ODecorative oDecorative) {
        if (Param.getInstance().htDecorative != null && Param.getInstance().htDecorative.contains(new Integer(oDecorative.intUserId))) {
            Param.getInstance().htDecorative.remove(new Integer(oDecorative.intUserId));
        }
        if (Param.getInstance().htDecorative.size() == 0) {
            Param.getInstance().htDecorative = null;
        }
    }

    public void setChat(final String s, final int n, final byte chatIndex, final String s2, final int n2) {
        ChatContentFactory.getInstance().put(s, n, chatIndex, s2, n2);
        if (DCanvas.getInstance().UMenu != null && MenuUI.getInstance().getState() == 42) {
            MenuUI.getInstance().setChatIndex(chatIndex);
        }
    }

    private void drawFightInfo(final Graphics graphics) {
        byte b = (byte) this.vecFightInfo.size();
        this.bytMaxChat = ((this.bytMaxChat <= this.bytMsgNumber) ? this.bytMaxChat : this.bytMsgNumber);
        graphics.setClip(0, Macro.SCREEN_GAME_HEIGHT - this.bytMaxChat * Macro.FONTHEIGHT, Macro.SCREEN_WIDTH, this.bytMaxChat * Macro.FONTHEIGHT + Macro.FONTHEIGHT);
        if (!this.blnChannelType) {
            for (int i = 0; i < this.bytMaxChat + 1; i = (byte) (i + 1)) {
                DrawBase.drawRGB(graphics, (byte) 3, 0, Macro.SCREEN_GAME_HEIGHT - (i + 1) * Macro.FONTHEIGHT, Macro.SCREEN_WIDTH, Macro.FONTHEIGHT);
            }
        }
        this.bytMaxChat = 0;
        graphics.setColor(0);
        for (byte b2 = (byte) (b - 1); b2 >= 0; --b2) {
            ++this.bytMaxChat;
            ChatContent chatContent = (ChatContent) this.vecFightInfo.elementAt(b2);
            graphics.setColor(chatContent.fontColor);
            graphics.drawString(chatContent.character, 0, Macro.SCREEN_GAME_HEIGHT - this.bytMaxChat * Macro.FONTHEIGHT, 0);
            if (this.bytMaxChat > this.bytMsgNumber) {
                break;
            }
        }
        DCanvas.getInstance().clearScreen();
        if (this.bytMaxChat > this.bytMsgNumber) {
            return;
        }
    }

    public void paint(final Graphics graphics) {
        switch (this.bytDrawType) {
            case 0: {
                this.drawMain(graphics);
                break;
            }
            case 1: {
                this.drawMain(graphics);
                this.drawSmallMap(graphics);
                if (this.bytDrawType == 1) {
                    this.printSmallMapPath(graphics);
                    break;
                }
                break;
            }
        }
        Param.getInstance().getClass();
    }

    private void drawMain(final Graphics graphics) {
        Map.getInstance().draw(graphics);
        if (Param.getInstance().intSelectId > 0) {
            if (Param.getInstance().bytSelectType == 2) {
                if (Param.getInstance().oSelectRole != null) {
                    this.drawSelect(graphics, Param.getInstance().oSelectRole.shtJudgeX, Param.getInstance().oSelectRole.shtJudgeY, Param.getInstance().oSelectRole.bytPicSize, Param.getInstance().oSelectRole.blnIsAttack);
                }
            } else if (Param.getInstance().bytSelectType == 3) {
                if (Param.getInstance().oSelectRole != null) {
                    this.drawSelect(graphics, Param.getInstance().oSelectRole.shtJudgeX, Param.getInstance().oSelectRole.shtJudgeY, Param.getInstance().oSelectRole.bytPicSize, Param.getInstance().oSelectRole.blnIsAttack);
                }
            } else if (Param.getInstance().bytSelectType == 1) {
                if (Param.getInstance().oSelectNpc != null) {
                    this.drawSelect(graphics, Param.getInstance().oSelectNpc.shtJudgeX, Param.getInstance().oSelectNpc.shtJudgeY, Param.getInstance().oSelectNpc.bytPicSize, false);
                }
            } else if (Param.getInstance().bytSelectType == 5) {
                if (Param.getInstance().bSelectTask != null) {
                    this.drawSelect(graphics, Param.getInstance().bSelectTask.shtJudgeX, Param.getInstance().bSelectTask.shtJudgeY, Param.getInstance().bSelectTask.bytPicSize, false);
                }
            } else if (Param.getInstance().bytSelectType == 4 && Param.getInstance().bSelectBox != null) {
                this.drawSelect(graphics, Param.getInstance().bSelectBox.shtJudgeX, Param.getInstance().bSelectBox.shtJudgeY, (byte) 1, false);
            }
        }
        if (this.bytDrawMoveY != -1) {
            DCanvas.getInstance().drawSpriteFram(graphics, this.DarwMove, this.mouseframe, this.mouseindex, this.bytDrawMoveX * 16 - Param.getInstance().CAMERAX + 8, (this.bytDrawMoveY + 1) * 16 - Param.getInstance().CAMERAY);
        }
        byte bytRow = (byte) (Map.getInstance().bytDrawRowE + Map.getInstance().bytMaxDrawRow);
        if (bytRow > Map.getInstance().bytRow) {
            bytRow = Map.getInstance().bytRow;
        }
        for (byte bytDrawRowS = Map.getInstance().bytDrawRowS; bytDrawRowS < bytRow; ++bytDrawRowS) {
            try {
                if (Param.getInstance().htRolePlayer != null && !Param.getInstance().htRolePlayer.isEmpty() && Param.getInstance().htRolePlayer != null) {
                    Enumeration elements = Param.getInstance().htRolePlayer.elements();
                    while (elements.hasMoreElements()) {
                        ORPlayer orPlayer = (ORPlayer) elements.nextElement();
                        if (Map.getInstance().judgeRoleIsDraw(orPlayer) && (Rms.blnShowRole || (!Rms.blnShowRole && ORPMe.ME.intUserId == orPlayer.intUserId)) && orPlayer.bytTileY == bytDrawRowS && orPlayer.blnPlayerResOK && orPlayer.bytConceal < 2 && Map.getInstance().judgeIsDraw(orPlayer.shtJudgeX, orPlayer.shtImgWidth, orPlayer.shtJudgeY, orPlayer.shtImgHeight) && orPlayer.blnStall && orPlayer.sprStallInstance != null) {
                            orPlayer.sprStallInstance.drawAnimationFrame(graphics, 0, 0, orPlayer.shtJudgeX - Param.getInstance().CAMERAX + 0, orPlayer.shtJudgeY - Param.getInstance().CAMERAY + 0);
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            try {
                if (Param.getInstance().htBox != null && !Param.getInstance().htBox.isEmpty()) {
                    Enumeration elements2 = Param.getInstance().htBox.elements();
                    while (elements2.hasMoreElements()) {
                        Box box = (Box) elements2.nextElement();
                        if (box.bytTileY == bytDrawRowS && box.blnIsDraw && Map.getInstance().judgeRoleIsDraw(box)) {
                            box.draw(graphics);
                        }
                    }
                }
            } catch (Exception ex2) {
                ex2.printStackTrace();
            }
            try {
                if (Param.getInstance().htRolePet != null && !Param.getInstance().htRolePet.isEmpty()) {
                    Enumeration elements3 = Param.getInstance().htRolePet.elements();
                    while (elements3.hasMoreElements()) {
                        PetObject petObject = (PetObject) elements3.nextElement();
                        if (petObject.bytTileY == bytDrawRowS && Map.getInstance().judgeRoleIsDraw(petObject)) {
                            petObject.draw(graphics);
                        }
                    }
                }
            } catch (Exception ex3) {
                ex3.printStackTrace();
            }
            try {
                if (Param.getInstance().htAnimal != null && !Param.getInstance().htAnimal.isEmpty()) {
                    Enumeration elements4 = Param.getInstance().htAnimal.elements();
                    while (elements4.hasMoreElements()) {
                        AnimalObject animalObject = (AnimalObject) elements4.nextElement();
                        if (animalObject.bytTileY == bytDrawRowS && Map.getInstance().judgeRoleIsDraw(animalObject)) {
                            animalObject.draw(graphics);
                        }
                    }
                }
            } catch (Exception ex4) {
                ex4.printStackTrace();
            }
            try {
                if (Param.getInstance().htTask != null && !Param.getInstance().htTask.isEmpty()) {
                    Enumeration elements5 = Param.getInstance().htTask.elements();
                    while (elements5.hasMoreElements()) {
                        Task task = (Task) elements5.nextElement();
                        if (task.bytTileY == bytDrawRowS && task.blnIsDraw && Map.getInstance().judgeRoleIsDraw(task)) {
                            task.draw(graphics);
                        }
                    }
                }
            } catch (Exception ex5) {
                ex5.printStackTrace();
            }
            try {
                if (Param.getInstance().htRoleNPC != null && !Param.getInstance().htRoleNPC.isEmpty()) {
                    Enumeration elements6 = Param.getInstance().htRoleNPC.elements();
                    while (elements6.hasMoreElements()) {
                        ONpc oNpc = (ONpc) elements6.nextElement();
                        if (oNpc.bytTileY == bytDrawRowS && Map.getInstance().judgeIsDraw(oNpc.shtJudgeX, (oNpc.bytIsTask == 0) ? oNpc.shtImgWidth : (oNpc.shtImgWidth + 34), oNpc.shtJudgeY, oNpc.shtImgHeight)) {
                            oNpc.drawName(graphics, Rms.bytShowName);
                            oNpc.draw(graphics);
                        }
                    }
                }
            } catch (Exception ex6) {
                ex6.printStackTrace();
            }
            this.JudgeChooseGoal();
            try {
                if (Param.getInstance().htRoleMonster != null && !Param.getInstance().htRoleMonster.isEmpty()) {
                    Enumeration elements7 = Param.getInstance().htRoleMonster.elements();
                    while (elements7.hasMoreElements()) {
                        ORMonster orMonster = (ORMonster) elements7.nextElement();
                        if (orMonster.bytTileY == bytDrawRowS && orMonster != null && orMonster.bytConceal < 2 && Map.getInstance().judgeRoleIsDraw(orMonster)) {
                            orMonster.drawName(graphics, Rms.bytShowName);
                            orMonster.draw(graphics);
                            if (orMonster.intsDamageData != null) {
                                orMonster.drawDamageData(graphics);
                            }
                            if (orMonster.intsCureData != null) {
                                orMonster.drawCureData(graphics);
                            }
                            if (orMonster.intsMpDate == null) {
                                continue;
                            }
                            orMonster.drawaddValue(graphics);
                        }
                    }
                }
            } catch (Exception ex7) {
                ex7.printStackTrace();
            }
            Map.getInstance().drawMapFloat(graphics, bytDrawRowS);
            try {
                if (Param.getInstance().htDecorative != null && !Param.getInstance().htDecorative.isEmpty()) {
                    Enumeration elements8 = Param.getInstance().htDecorative.elements();
                    while (elements8.hasMoreElements()) {
                        ODecorative oDecorative = (ODecorative) elements8.nextElement();
                        if (oDecorative.bytTileY == bytDrawRowS && Map.getInstance().judgeRoleIsDraw(oDecorative)) {
                            oDecorative.draw(graphics);
                        }
                    }
                }
                if (Map.getInstance().blnHaveOutJump) {
                    Map.getInstance().drawOutJump(graphics, bytDrawRowS);
                }
                try {
                    if (Param.getInstance().htRolePlayer != null && !Param.getInstance().htRolePlayer.isEmpty() && Param.getInstance().htRolePlayer != null) {
                        Enumeration elements9 = Param.getInstance().htRolePlayer.elements();
                        while (elements9.hasMoreElements()) {
                            ORPlayer orPlayer2 = (ORPlayer) elements9.nextElement();
                            if ((Rms.blnShowRole || (!Rms.blnShowRole && ORPMe.ME.intUserId == orPlayer2.intUserId)) && orPlayer2.bytTileY == bytDrawRowS && orPlayer2.blnPlayerResOK && orPlayer2.bytConceal < 2 && Map.getInstance().judgeRoleIsDraw(orPlayer2)) {
                                orPlayer2.drawName(graphics, Rms.bytShowName);
                                orPlayer2.draw(graphics);
                                if (orPlayer2.intsDamageData != null) {
                                    orPlayer2.drawDamageData(graphics);
                                }
                                if (orPlayer2.intsCureData != null) {
                                    orPlayer2.drawCureData(graphics);
                                }
                                if (orPlayer2.intsMpDate == null) {
                                    continue;
                                }
                                orPlayer2.drawaddValue(graphics);
                            }
                        }
                    }
                } catch (Exception ex8) {
                    ex8.printStackTrace();
                }
            } catch (Exception ex9) {
                ex9.printStackTrace();
            }
        }
        if (Macro.BLN_TARGET_SELECT_DEBUG) {
            int[] meView = this.getMeView(7, 7, true);
            graphics.setColor(255);
            graphics.drawRect(meView[0] * 16 - Param.getInstance().CAMERAX, meView[1] * 16 - Param.getInstance().CAMERAY, meView[2] * 16, meView[3] * 16);
            int[] meView2 = this.getMeView(14, 14, false);
            graphics.setColor(65280);
            graphics.drawRect(meView2[0] * 16 - Param.getInstance().CAMERAX, meView2[1] * 16 - Param.getInstance().CAMERAY + 5, meView2[2] * 16 - 10, meView2[3] * 16 - 10);
        }
        if (Param.getInstance().peffect != null) {
            Param.getInstance().peffect.print(graphics);
        }
        if (DCanvas.getInstance().blnIsTouch && (((Param.getInstance().intSelectId > 0 || ORPMe.ME.blnStall) && this.blnIsThis) || (this.bytDrawType == 1 && this.blnIsThis))) {
            this.Menukey.drawAnimationFrame(graphics, 0, 0, this.LeftUpX, this.LeftUpY);
            this.Menukey.drawAnimationFrame(graphics, 1, 0, this.RightUpX, this.RightUpY);
        }
        this.drawSkin(graphics);
        this.drawPortrait(graphics);
        if (Param.getInstance().vTeam != null) {
            this.drawTeam(graphics);
        }
        if (ORPMe.ME.bytState == 4 && ORPMe.ME.blnKeepMagic) {
            this.drawMagic(graphics);
        }
        if (this.intsGetExpGold != null) {
            this.drawExpGold(graphics);
        }
        if (this.showFightInformation) {
            this.drawFrightInformation(graphics);
        }
        this.drawchatFrame(graphics);
        this.drawMiniMap(graphics, Macro.SCREEN_WIDTH - this.mapTileWidth - 2, 2);
    }

    private void drawMiniMap(final Graphics graphics, final int n, final int n2) {
        ++this.flashCount;
        this.drawMiniBg(graphics, n, n2);
        this.drawJumpPoint(graphics, n, n2);
        this.drawMiniNpc(graphics, n, n2);
        this.drawMiniMonster(graphics, n, n2);
        this.drawMiniPlayer(graphics, n, n2);
        this.drawGiftsCountDown(graphics, n, n2);
    }

    public void UpdateMiniMap() {
        if (this.radaricon != null) {
            for (int i = 0; i < 5; ++i) {
                this.radaricon[i].update();
            }
        }
    }

    private void drawJumpPoint(final Graphics graphics, final int n, final int n2) {
        if (this.flashCount % 8 > 4) {
            graphics.setColor(15982228);
        } else {
            graphics.setColor(15584346);
        }
        if (Map.getInstance().bytInJumpPlace != null) {
            for (int i = 0; i < Map.getInstance().bytInJumpPlace.length; i = (byte) (i + 1)) {
                graphics.fillRect(n + Map.getInstance().bytInJumpPlace[i][0], n2 + Map.getInstance().bytInJumpPlace[i][1], 2, 2);
            }
        }
        if (Map.getInstance().htShowJumpPoint != null) {
            Enumeration keys = Map.getInstance().htShowJumpPoint.keys();
            while (keys.hasMoreElements()) {
                short n3 = ((short[]) Map.getInstance().htShowJumpPoint.get(keys.nextElement()))[2];
                short n4 = Map.getInstance().shtOutJumpPlace[n3][0];
                short n5 = Map.getInstance().shtOutJumpPlace[n3][1];
                byte b = Map.getInstance().bytOutJumpDirection[n3];
                if (b == 0) {
                    this.radaricon[0].drawAnimation(graphics, n + n4, n2 + n5);
                } else if (b == 1) {
                    this.radaricon[1].drawAnimation(graphics, n + n4, n2 + n5);
                } else if (b == 2) {
                    this.radaricon[2].drawAnimation(graphics, n + n4, n2 + n5);
                } else {
                    if (b != 3) {
                        continue;
                    }
                    this.radaricon[3].drawAnimation(graphics, n + n4 - 2, n2 + n5);
                }
            }
        }
    }

    private void drawMiniBg(final Graphics graphics, final int n, final int n2) {
        this.mapTileWidth = Param.getInstance().shtMapMaxWidth / 16;
        this.mapTileHeight = Param.getInstance().shtMapMaxHeight / 16;
        DrawBase.drawBox(n - 2, n2 - 2, this.mapTileWidth + 2 + 2, this.mapTileHeight + 2 + 2, new int[]{5845015, 15463901, 14976785}, false);
        DrawBase.drawRGB(graphics, (byte) 1, n, n2, this.mapTileWidth, this.mapTileHeight);
        StringManager.drawShadowWord(graphics, Map.getInstance().strName, Macro.SCREEN_WIDTH - 2, this.mapTileHeight + 2 + 2, 16777214, 6693397, 24);
        for (int i = 0; i < Map.getInstance().shtsMapEvent.length; ++i) {
            for (int j = 0; j < Map.getInstance().shtsMapEvent[0].length; ++j) {
                if (Map.getInstance().shtsMapEvent[i][j] < 0) {
                    DrawBase.fillRect(n + j + 1, n2 + i, 1, 1, 7175012);
                }
            }
        }
    }

    private void drawMiniNpc(final Graphics graphics, final int n, final int n2) {
        int color = graphics.getColor();
        graphics.setColor(16701448);
        if (Param.getInstance().htRoleNPC != null && !Param.getInstance().htRoleNPC.isEmpty()) {
            Enumeration elements = (Enumeration) Param.getInstance().htRoleNPC.elements();
            while (elements.hasMoreElements()) {
                ONpc oNpc = (ONpc) elements.nextElement();
                graphics.fillRect(n + oNpc.bytTileX, n2 + oNpc.bytTileY, 2, 2);
                if (oNpc.blnIsTalk && oNpc.bytIsTask > 0 && oNpc.bytIsTask < 4) {
                    this.radaricon[4].drawAnimationFrame(graphics, oNpc.intTaskSignAnimation, 0, n + oNpc.bytTileX, n2 + oNpc.bytTileY);
                }
            }
        }
        graphics.setColor(color);
    }

    private void drawMiniMonster(final Graphics graphics, final int n, final int n2) {
        graphics.setColor(15807279);
        if (Param.getInstance().htRoleMonster != null && !Param.getInstance().htRoleMonster.isEmpty()) {
            Enumeration elements = Param.getInstance().htRoleMonster.elements();
            while (elements.hasMoreElements()) {
                ORMonster orMonster = (ORMonster) elements.nextElement();
                if (orMonster != null) {
                    graphics.fillRect(n + orMonster.bytTileX, n2 + orMonster.bytTileY, 2, 2);
                }
            }
        }
    }

    private void drawMiniPlayer(final Graphics graphics, final int n, final int n2) {
        if (Param.getInstance().htRolePlayer != null && Param.getInstance().htRolePlayer != null && !Param.getInstance().htRolePlayer.isEmpty() && Param.getInstance().htRolePlayer != null) {
            Enumeration elements = Param.getInstance().htRolePlayer.elements();
            while (elements.hasMoreElements()) {
                ORPlayer orPlayer = (ORPlayer) elements.nextElement();
                if (orPlayer == null) {
                    continue;
                }
                if (orPlayer.intUserId != ORPMe.ME.intUserId && Rms.blnShowRole) {
                    boolean b = false;
                    if (Param.getInstance().vTeam != null) {
                        Enumeration elements2 = Param.getInstance().vTeam.elements();
                        while (elements2.hasMoreElements()) {
                            if (((TeamObject) elements2.nextElement()).strName.equals(orPlayer.strNickName)) {
                                b = true;
                                break;
                            }
                        }
                    }
                    if (b) {
                        graphics.setColor(1631485);
                        graphics.fillRect(n + orPlayer.bytTileX, n2 + orPlayer.bytTileY, 2, 2);
                    } else {
                        graphics.setColor(65280);
                        graphics.fillRect(n + orPlayer.bytTileX, n2 + orPlayer.bytTileY, 2, 2);
                    }
                } else {
                    graphics.setColor(9556779);
                    graphics.fillRect(n + ORPMe.ME.bytTileX, n2 + ORPMe.ME.bytTileY, 2, 2);
                    StringManager.drawShadowWord(graphics, "(" + ORPMe.ME.bytTileX + "," + ORPMe.ME.bytTileY + ")", Macro.SCREEN_WIDTH - 2, this.mapTileHeight + 2 + 2 + Macro.FONTHEIGHT, 16777214, 6693397, 24);
                    this.radaricon[4].drawAnimation(graphics, n + ORPMe.ME.bytTileX, n2 + ORPMe.ME.bytTileY);
                }
            }
        }
    }

    private void drawGiftsCountDown(final Graphics graphics, final int n, final int n2) {
        if (Param.Gifts_Time_Cout_Down / 1000L > 0L) {
            int n3 = (int) Param.Gifts_Time_Cout_Down / 1000;
            short n4 = (short) (n3 / 60);
            byte b = (byte) (n3 % 60);
            if (n4 >= 0 && b >= 0 && Param.image_Gifts != null) {
                String string = String.valueOf(Common.strFormat(n4, 2)) + ":" + Common.strFormat(b, 2);
                int stringWidth = Macro.font.stringWidth(string);
                int n5 = Macro.SCREEN_WIDTH - 2;
                int n6 = this.mapTileHeight + 2 + 2 + 2 * Macro.FONTHEIGHT;
                graphics.drawImage(Param.image_Gifts, Macro.SCREEN_WIDTH - 2 - stringWidth - 2, n6, 24);
                StringManager.drawShadowWord(graphics, string, Macro.SCREEN_WIDTH - 2, n6, 15326509, 7290894, 24);
            }
        }
    }

    private void initSkin() {
        ImageManager.getInstance();
        this.sprHotKey = new QSprite[2];
        this.Menukey = ImageManager.loadSpriteById(3, ImageManager.EncodespriteId(this.MenuRescure, this.MenuRescure), this.MenuRescure, this.MenuRescure, GameControl.STRING_SPRITE_OTHER());
        this.Number = ImageManager.loadSpriteById(3, ImageManager.EncodespriteId(this.NumberResource, this.NumberResource), this.NumberResource, this.NumberResource, "ingame");
        for (int i = 0; i < this.sprHotKey.length; ++i) {
            this.sprHotKey[i] = ImageManager.loadSpriteById(3, ImageManager.EncodespriteId("skillbar", String.valueOf(i) + "skillbar"), "skillbar", "skillbar", GameControl.STRING_IMAGE_UI());
        }
        this.sprHotKey[0].setAnimation(1);
        this.sprHotKey[1].setAnimation(2);
        this.RoleBM = ImageManager.loadSpriteById(3, ImageManager.EncodespriteId("role", "role"), "role", "role", "ui");
        this.TeamBM = ImageManager.loadSpriteById(3, ImageManager.EncodespriteId("team", "team"), "team", "team", "ui");
        this.newMail = ImageManager.loadSpriteById(3, ImageManager.EncodespriteId("newmail", "newmail"), "newmail", "newmail", "ui");
        this.sprPortraitRoleSelected = ImageManager.loadSpriteById(3, ImageManager.EncodespriteId("monsterhead", "monsterhead"), "monsterhead", "monsterhead", "ui");
        if (this.radaricon == null) {
            this.radaricon = new QSprite[5];
            for (int j = 0; j < this.radaricon.length; ++j) {
                this.radaricon[j] = ImageManager.loadSpriteById(3, ImageManager.EncodespriteId("radaricon", new StringBuffer(String.valueOf(j)).toString()), "radaricon", "radaricon", "ingame");
            }
            for (int k = 0; k < 5; ++k) {
                this.radaricon[k].setAnimation(6 + k);
            }
        }
        this.bytHotKeyBoxWidth = 22;
        this.bytHotKeyBoxHeight = 23;
        this.bytHotKeyIconDistance = 1;
        this.bytbHotKeyExepHeight = Macro.expRow_h;
        this.bytHotKeyBoxDistance = 0;
        this.shtHotKeyXOff = 21;
        this.shtDownHotKeyW = 202;
        this.shtDownHotKeyXOff = (short) ((Macro.SCREEN_WIDTH - ((22 + this.bytHotKeyBoxDistance) * 9 - this.bytHotKeyBoxDistance) >> 1) + 11);
        this.LeftUpX = 26;
        this.LeftDownX = this.LeftUpX + 10;
        this.LeftDownY = Macro.SCREEN_HEIGHT - Macro.expRow_h - 23 - 16 - 5;
        this.LeftUpY = this.LeftDownY - 32 - 5;
        this.RightUpX = Macro.SCREEN_WIDTH - 10 - 16;
        this.RightUpY = this.LeftUpY;
        this.RightDownX = this.RightUpX - 10;
        this.RightDownY = this.LeftDownY;
        this.LeftCentreX = this.LeftDownX + 32 + 5;
        this.RightCentreX = this.RightDownX - 32 - 5;
        this.bytHotKeyBoxContentHeightYOff = -17;
    }

    private void updateSkin() {
        if (this.sprHotKey != null) {
            this.sprHotKey[0].update();
            this.sprHotKey[1].update();
        }
    }

    private void drawSkin(final Graphics graphics) {
        int n = Macro.SCREEN_HEIGHT - Macro.expRow_h;
        this.drawChangeKey(graphics, this.shtDownHotKeyXOff, n + 23 + this.HideShurtCut - Macro.bytb3height);
        this.drawShortcutKeys(graphics, this.shtDownHotKeyXOff, n + this.HideShurtCut, this.bytShortcutKeysType);
        int n2 = n - Macro.bytb3height;
        DrawBase.drawBox(0, Macro.SCREEN_HEIGHT - Macro.expRow_h, Macro.SCREEN_WIDTH - 0, Macro.expRow_h, new int[]{7292680, 7292680}, true);
        int n3 = 10;
        int n4 = (byte) (n3 * ORPMe.ME.EXP / ORPMe.ME.NextEXP);
        if (n4 >= n3) {
            n4 = n3;
        }
        byte b = (byte) ((Macro.SCREEN_WIDTH - 3 * (n3 - 1) - 0) / n3);
        short n5 = (short) (Macro.expRow_h - 2);
        short n6 = (short) ((Macro.SCREEN_WIDTH - ((b + 3) * n3 - 3)) / 2 + 0);
        graphics.setColor(3547651);
        short n7 = n6;
        for (int i = 0; i < n3; ++i) {
            graphics.fillRect(n7, Macro.SCREEN_HEIGHT - n5 - 1, b, n5);
            n7 += (short) (3 + b);
        }
        int n8 = n4 * 3 + (Macro.SCREEN_WIDTH - n3 * 3) * ORPMe.ME.EXP / ORPMe.ME.NextEXP;
        if (ORPMe.ME.EXP >= ORPMe.ME.NextEXP) {
            n8 = 0;
        }
        if (n8 > 0) {
            graphics.setColor(16770198);
            graphics.fillRect(n6, Macro.SCREEN_HEIGHT - Macro.expRow_h + 1, n8, n5);
            graphics.setColor(12156208);
            graphics.drawLine(n6, Macro.SCREEN_HEIGHT - Macro.expRow_h + 1, n8 - n6, Macro.SCREEN_HEIGHT - Macro.expRow_h + 1);
            graphics.setColor(16500270);
            graphics.drawLine(n6, Macro.SCREEN_HEIGHT - Macro.expRow_h + 2, n8 - n6, Macro.SCREEN_HEIGHT - Macro.expRow_h + 2);
        }
        graphics.setColor(7292680);
        short n9 = n6;
        for (int j = 0; j < n3; ++j) {
            graphics.fillRect(n9 + b, Macro.SCREEN_HEIGHT - n5 - 1, 3, n5);
            n9 += (short) (3 + b);
        }
        if (DCanvas.getInstance().blnIsTouch) {
            this.Menukey.drawAnimationFrame(graphics, 2, 0, this.LeftDownX, this.LeftDownY);
            this.Menukey.drawAnimationFrame(graphics, 5, 0, this.LeftCentreX, this.LeftDownY);
            this.Menukey.drawAnimationFrame(graphics, 3, 0, this.RightCentreX, this.RightDownY);
            this.Menukey.drawAnimationFrame(graphics, 4, 0, this.RightDownX, this.RightDownY);
        }
    }

    public void drawChangeKey(final Graphics graphics, final int n, final int n2) {
        this.drawChangeKey(graphics, n, n2, (byte) (-1));
    }

    public void drawChangeKey(final Graphics graphics, final int n, final int n2, byte bytShortcutKeysType) {
        if (bytShortcutKeysType < 0) {
            bytShortcutKeysType = this.bytShortcutKeysType;
        }
        if (bytShortcutKeysType == 0) {
            this.sprHotKey[0].drawAnimation(graphics, n + (22 + this.bytHotKeyBoxDistance) * 8, n2);
        } else {
            this.sprHotKey[1].drawAnimation(graphics, this.shtDownHotKeyXOff + (22 + this.bytHotKeyBoxDistance) * 8, n2);
        }
    }

    private void drawRoleHp(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        if (n >= 0) {
            int n5 = 12;
            int n6 = 25;
            DrawBase.setClip(2 + n3, 3 + (n6 - n * n6 / n2), n5, n6);
            this.RoleBM.drawAnimationFrame(graphics, 1, 0, n3, n4);
            graphics.setClip(0, 0, Macro.SCREEN_WIDTH, Macro.SCREEN_HEIGHT);
        }
    }

    private void drawRoleMp(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        if (n > 0) {
            int n5 = 12;
            int n6 = 25;
            DrawBase.setClip(28 + n3, 3 + (n6 - n * n6 / n2), n5, n6);
            this.RoleBM.drawAnimationFrame(graphics, 2, 0, n3, n4);
            graphics.setClip(0, 0, Macro.SCREEN_WIDTH, Macro.SCREEN_HEIGHT);
        }
    }

    private void drawTeamHp(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        if (n >= 0) {
            int n5 = 11;
            int n6 = 24;
            DrawBase.setClip(n3, n4 + (n6 - n * n6 / n2), n5, n6);
            this.TeamBM.drawAnimationFrame(graphics, 1, 0, n3, n4);
            graphics.setClip(0, 0, Macro.SCREEN_WIDTH, Macro.SCREEN_HEIGHT);
        }
    }

    private void drawTeamMp(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        if (n > 0) {
            int n5 = 11;
            int n6 = 24;
            DrawBase.setClip(24, n4 + (n6 - n * n6 / n2), n5, n6);
            this.TeamBM.drawAnimationFrame(graphics, 2, 0, n3, n4);
            graphics.setClip(0, 0, Macro.SCREEN_WIDTH, Macro.SCREEN_HEIGHT);
        }
    }

    public void drawShortcutKeys(final Graphics graphics, int n, int n2, final byte b) {
        int n3 = n;
        for (int i = 0; i < 8; i = (byte) (i + 1)) {
            this.sprHotKey[0].drawAnimationFrame(graphics, 0, 0, n3, n2);
            n3 += this.bytHotKeyBoxWidth + this.bytHotKeyBoxDistance;
        }
        n -= this.bytHotKeyBoxWidth / 2 - 3;
        n2 -= 23;
        int n4 = n;
        for (int j = 0; j < 8; j = (byte) (j + 1)) {
            int n5 = DCanvas.getInstance().bytDrawKeyIndex[j] + b * 13;
            try {
                if (Param.getInstance().intShortcutKeys[n5][4] == 2) {
                    int n6 = n + j * (this.bytHotKeyBoxWidth + this.bytHotKeyBoxDistance) + this.bytHotKeyIconDistance;
                    if (Param.getInstance().imgShortcutKeys[n5] == null && Param.getInstance().shtShortcutKeysId[n5] > 0) {
                        Param.getInstance().imgShortcutKeys[n5] = ImageManager.CreateImage(new StringBuffer(String.valueOf(Param.getInstance().shtShortcutKeysId[n5])).toString(), GameControl.STRING_IMAGE_PROP());
                    }
                    DrawBase.drawImage(Param.getInstance().imgShortcutKeys[n5], n6 - 1, n2 + 3, 20);
                    SkillObject skillObject = (SkillObject) Param.getInstance().vSkillList.elementAt(Param.getInstance().intShortcutKeys[n5][3]);
                    if (skillObject.intCDTime > 0 && skillObject.intCDTime >= ORPMe.ME.intSkillCDTime) {
                        int n7 = skillObject.intCDTime * 16 / skillObject.intCDTimeMax;
                        graphics.drawRGB(Param.getInstance().intsSkillColor, 0, 16, n6 - 1, n2 + 16 - n7 + 3, 16, n7, true);
                    } else if (ORPMe.ME.intSkillCDTime > 0) {
                        int n8 = ORPMe.ME.intSkillCDTime * 16 / 1500;
                        graphics.drawRGB(Param.getInstance().intsSkillColor, 0, 16, n6 - 1, n2 + 16 - n8 + 3, 16, n8, true);
                    }
                } else if (Param.getInstance().intShortcutKeys[n5][4] == 3) {
                    int n9 = n + j * (this.bytHotKeyBoxWidth + this.bytHotKeyBoxDistance) + this.bytHotKeyIconDistance;
                    if (Param.getInstance().imgShortcutKeys[n5] == null && Param.getInstance().shtShortcutKeysId[n5] > 0) {
                        Param.getInstance().imgShortcutKeys[n5] = ImageManager.CreateImage(new StringBuffer(String.valueOf(Param.getInstance().shtShortcutKeysId[n5])).toString(), GameControl.STRING_IMAGE_PROP());
                    }
                    DrawBase.drawImage(Param.getInstance().imgShortcutKeys[n5], n9 - 1, n2 + 3, 20);
                    if (Param.getInstance().intShortcutKeys[n5][1] > 0 && Param.getInstance().intShortcutKeys[n5][1] >= ORPMe.ME.intGoodsCDTime) {
                        int n10 = Param.getInstance().intShortcutKeys[n5][1] * 16 / Param.getInstance().intShortcutKeys[n5][2];
                        graphics.drawRGB(Param.getInstance().intsSkillColor, 0, 16, n9 - 1, n2 + 16 - n10 + 3, 16, n10, true);
                    } else if (ORPMe.ME.intGoodsCDTime > 0) {
                        int n11 = ORPMe.ME.intGoodsCDTime * 16 / 1500;
                        graphics.drawRGB(Param.getInstance().intsSkillColor, 0, 16, n9 - 1, n2 + 16 - n11 + 3, 16, n11, true);
                    }
                    if (Param.getInstance().intShortcutKeys[n5][5] == 0) {
                        DrawBase.drawRGB(DCanvas.gameG, (byte) 2, n9 - 1, n2 + 3 + 1, 16, 16);
                    }
                } else if (Param.getInstance().intShortcutKeys[n5][4] == 1 && Param.getInstance().intShortcutKeys[n5][0] != 0) {
                    int n12 = n + j * (this.bytHotKeyBoxWidth + this.bytHotKeyBoxDistance) + this.bytHotKeyIconDistance;
                    if (Param.getInstance().imgShortcutKeys[n5] == null && Param.getInstance().shtShortcutKeysId[n5] > 0) {
                        Param.getInstance().imgShortcutKeys[n5] = ImageManager.CreateImage(new StringBuffer(String.valueOf(Param.getInstance().shtShortcutKeysId[n5])).toString(), GameControl.STRING_IMAGE_SYS());
                    }
                    DrawBase.drawImage(Param.getInstance().imgShortcutKeys[n5], n12 - 1, n2 + 3, 20);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        n = n4;
        for (int k = 0; k < 9; k = (byte) (k + 1)) {
            this.drawWhiteKey(graphics, DCanvas.getInstance().bytDrawKeyIndex[k], n + k * (this.bytHotKeyBoxWidth + this.bytHotKeyBoxDistance) + this.bytHotKeyIconDistance - 1, n2 + 2);
        }
    }

    public void drawHP(final Graphics graphics, int n, int n2, final int n3, final int n4, final int color, final int color2) {
        n -= 12;
        n2 -= 5;
        graphics.setColor(14540253);
        graphics.fillRect(n, n2, 25, 2);
        graphics.setColor(7816243);
        graphics.drawRect(n - 1, n2 - 1, 26, 3);
        if (n3 > 0) {
            int n5 = n3 * 24 / n4;
            graphics.setColor(color);
            graphics.drawLine(n, n2, n + n5, n2);
            graphics.setColor(color2);
            graphics.drawLine(n, n2 + 1, n + n5, n2 + 1);
        }
    }

    public void drawSmallMap(final Graphics graphics) {
        if (((this.bytMapState == 1 && Map.getInstance().imgAreaMap != null) || (this.bytMapState == 2 && Map.getInstance().imgWorldMap != null)) && this.sprSmallMapIcon != null) {
            if (this.bytMapState == 1) {
                Map.getInstance().imgAreaMap.drawAnimationFrame(graphics, 0, 0, -Param.getInstance().SMALL_MAP_CAMERAX, -Param.getInstance().SMALL_MAP_CAMERAY);
            } else if (this.bytMapState == 2) {
                Map.getInstance().imgWorldMap.drawAnimationFrame(graphics, 0, 0, -Param.getInstance().SMALL_MAP_CAMERAX, -Param.getInstance().SMALL_MAP_CAMERAY);
            }
            if (this.shtsMapPlaceXY != null && (this.bytMapState == 1 || this.bytMapState == 2)) {
                int n = this.shtMoveMapFlagXY[0] - Param.getInstance().SMALL_MAP_CAMERAX;
                int n2 = this.shtMoveMapFlagXY[1] - Param.getInstance().SMALL_MAP_CAMERAY;
                if (this.sprSmallMapIcon != null && this.sprSmallMapIcon.getAnimation() != -1) {
                    this.sprSmallMapIcon.drawAnimation(graphics, n, n2);
                }
                if (this.bytMoveMapPointXY != -1 && GameUI.popupDialogInstance != null) {
                    GameUI.popupDialogInstance.drawPopupDialog(graphics);
                }
                if (this.bytMoveMapPointXYCurrent >= 0) {
                    int n3 = this.shtsMapPlaceXY[this.bytMoveMapPointXYCurrent][0] - Param.getInstance().SMALL_MAP_CAMERAX;
                    int n4 = this.shtsMapPlaceXY[this.bytMoveMapPointXYCurrent][1] - Param.getInstance().SMALL_MAP_CAMERAY;
                    if (Param.getInstance().sprRolePortrait != null && ORPMe.ME.bytDrawPortraitId >= 0) {
                        Param.getInstance().sprRolePortrait.drawAnimationFrame(graphics, ORPMe.ME.bytDrawPortraitId, 0, n3, n4);
                    }
                }
            }
            DCanvas.getInstance().drawSoftkey(graphics, this.bytsButtonType[0], this.bytsButtonType[1], this.bytsButtonType[2], this.blnIsThis);
        }
    }

    private void drawTeam(final Graphics graphics) {
        short n = 34;
        if (Param.getInstance().bytsMyTeam != null) {
            for (int i = 0; i < Param.getInstance().bytsMyTeam.length; i = (byte) (i + 1)) {
                TeamObject teamObject = (TeamObject) Param.getInstance().vTeam.elementAt(Param.getInstance().bytsMyTeam[i]);
                this.drawTeamImageOption(graphics, teamObject, (short) 1, n, (byte) 20);
                if (teamObject.bytType != 1 && teamObject.bytTroopRank != -1) {
                    this.drawTeamRank(graphics, teamObject.bytTroopRank, 1, n);
                }
                n += 34;
            }
        }
    }

    private void drawRoleState(final Graphics graphics, int n, final int n2) {
        if (this.shtStateIcon != null) {
            for (int i = 0; i < this.shtStateIcon.length; ++i) {
                Image stateIcon = this.getStateIcon(this.shtStateIcon[i]);
                if (stateIcon != null) {
                    DrawBase.drawImage(stateIcon, n, n2, 20);
                }
                n += 10;
            }
        }
    }

    private void drawProtraitLhp(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        if (n != 0) {
            int n5 = n * 39 / n2;
            graphics.setColor(14943744);
            graphics.drawLine(n3, n4, n3 + n5, n4);
            graphics.setColor(16759730);
            graphics.drawLine(n3, n4 + 1, n3 + n5, n4 + 1);
            graphics.setColor(16717070);
            graphics.drawLine(n3, n4 + 3, n3 + n5, n4 + 3);
            graphics.setColor(11929088);
            graphics.drawLine(n3, n4 + 2, n3 + n5, n4 + 2);
            graphics.drawLine(n3 - 1, n4 + 1, n3 - 1, n4 + 2);
            if (n == n2) {
                graphics.drawLine(n3 + n5 + 1, n4 + 1, n3 + n5 + 1, n4 + 2);
            }
        }
    }

    private void drawProtraitLmp(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        if (n != 0) {
            int n5 = n * 39 / n2;
            graphics.setColor(3612671);
            graphics.drawLine(n3, n4, n3 + n5, n4);
            graphics.setColor(11717117);
            graphics.drawLine(n3, n4 + 1, n3 + n5, n4 + 1);
            graphics.setColor(2777853);
            graphics.drawLine(n3, n4 + 3, n3 + n5, n4 + 3);
            graphics.setColor(3612671);
            graphics.drawLine(n3 - 1, n4 + 1, n3 - 1, n4 + 2);
            graphics.drawLine(n3, n4 + 2, n3 + n5, n4 + 2);
            if (n == n2) {
                graphics.drawLine(n3 + n5 + 1, n4 + 1, n3 + n5 + 1, n4 + 2);
            }
        }
    }

    private void drawProtraitRhp(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        if (n != 0) {
            int n5 = 39;
            int n6 = n * n5 / n2;
            graphics.setColor(6693141);
            graphics.fillRect(n3 - n5 - 1, n4 - 1, n5 + 2, 6);
            graphics.setColor(16732931);
            graphics.drawLine(n3, n4, n3 - n6, n4);
            graphics.setColor(16431000);
            graphics.drawLine(n3, n4 + 1, n3 - n6, n4 + 1);
            graphics.setColor(16732931);
            graphics.drawLine(n3, n4 + 2, n3 - n6, n4 + 2);
            graphics.setColor(16732931);
            graphics.drawLine(n3, n4 + 3, n3 - n6, n4 + 3);
        }
    }

    private void drawProtraitRmp(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        if (n != 0) {
            int n5 = 39;
            int n6 = n * n5 / n2;
            graphics.setColor(6693141);
            graphics.fillRect(n3 - n5 - 1, n4 - 1, n5 + 2, 6);
            graphics.setColor(1876985);
            graphics.drawLine(n3, n4, n3 - n6, n4);
            graphics.setColor(10410495);
            graphics.drawLine(n3, n4 + 1, n3 - n6, n4 + 1);
            graphics.setColor(1876985);
            graphics.drawLine(n3, n4 + 2, n3 - n6, n4 + 2);
            graphics.setColor(1876985);
            graphics.drawLine(n3, n4 + 3, n3 - n6, n4 + 3);
        }
    }

    private void drawProtraitRpower(final Graphics graphics, final int n, final int n2, final int n3) {
        int n4 = n * 30 / 100 - 1;
        graphics.setColor(14702615);
        graphics.drawLine(n2 + 1, n3 + 1, n2 + 1, n3 + 2);
        if (n != 0) {
            graphics.drawLine(n2, n3 + 2, n2 - n4, n3 + 2);
            graphics.setColor(14702615);
            graphics.drawLine(n2, n3, n2 - n4, n3);
            graphics.setColor(16775016);
            graphics.drawLine(n2, n3 + 1, n2 - n4, n3 + 1);
            graphics.setColor(16736258);
            graphics.drawLine(n2, n3 + 3, n2 - n4, n3 + 3);
        }
        graphics.setColor(424284);
        graphics.drawLine(n2 - 30, n3 + 1, n2 - 30, n3 + 2);
        if (n != 100) {
            graphics.drawLine(n2 - n4 - 1, n3 + 2, n2 - 29, n3 + 2);
            graphics.setColor(424284);
            graphics.drawLine(n2 - n4 - 1, n3, n2 - 29, n3);
            graphics.setColor(4325002);
            graphics.drawLine(n2 - n4 - 1, n3 + 1, n2 - 29, n3 + 1);
            graphics.setColor(428112);
            graphics.drawLine(n2 - n4 - 1, n3 + 3, n2 - 29, n3 + 3);
        }
    }

    private void drawPortrait(final Graphics graphics) {
        try {
            int n;
            if (Rms.bytPortrait == 0 || Rms.bytPortrait == -1) {
                n = 39;
                this._drawMeOffX = 20;
            } else {
                n = 19;
                this._drawMeOffX = 0;
            }
            int n2 = 15;
            int n3 = n;
            int n4 = n2;
            this.RoleBM.drawAnimationFrame(graphics, 0, 0, this._drawMeOffX, 0);
            this.drawRoleHp(graphics, ORPMe.ME.intHP, ORPMe.ME.intHPMax, this._drawMeOffX, 0);
            this.drawRoleMp(graphics, ORPMe.ME.intMP, ORPMe.ME.intMPMax, this._drawMeOffX, 0);
            Param.getInstance().sprRolePortrait.drawAnimationFrame(graphics, ORPMe.ME.bytDrawPortraitId, 0, n3 + 2, n4);
            this.drawMeLv(graphics, this.shtHotKeyXOff + 2 + this._drawMeOffX, this.headheight - 8);
            this.drawRoleState(graphics, 50 + this._drawMeOffX, 6);
            ORPMe.ME.drawEffect(graphics, (short) (Param.GAME_UI_ME_DRAWBUFFX + this._drawMeOffX), Param.GAME_UI_ME_DRAWBUFFY);
            if (Param.getInstance().intSelectId >= 0) {
                int n5 = Param.getInstance().shtMapMaxWidth / 16 + 4;
                int n6 = 3;
                int n7 = Macro.SCREEN_WIDTH - 3 - n5;
                int n8 = n7 - 12;
                int n9 = n6 + 12;
                int n10 = n7 - 25 - 3;
                int n11 = n6 + 7;
                int n12 = n6 + 14;
                int n13 = n6 + 17;
                if (Param.getInstance().oSelectRole != null && Param.getInstance().oSelectRole.bytType == 3 && ((ORMonster) Param.getInstance().oSelectRole).blnIsBoss) {
                    this.sprPortraitRoleSelected.drawAnimationFrame(graphics, 0, 1, n7, n6);
                } else {
                    this.sprPortraitRoleSelected.drawAnimationFrame(graphics, 0, 0, n7, n6);
                }
                if (Param.getInstance().oSelectRole != null) {
                    if (Param.getInstance().bytSelectType == 3) {
                        Param.getInstance().oSelectRole.sprInstance.drawAnimationFrame(graphics, 0, 0, n8, n9);
                        this.drawProtraitRmp(graphics, Param.getInstance().oSelectRole.intMP, Param.getInstance().oSelectRole.intMPMax, n10, n12);
                    } else {
                        if (Param.getInstance().oSelectRole.bytState != 5) {
                            Param.getInstance().sprRolePortrait.drawAnimationFrame(graphics, Param.getInstance().oSelectRole.bytDrawPortraitId, 0, n8, n9);
                        } else {
                            DrawBase.drawRegion(Param.getInstance().imgDead, n8, n9, Param.getInstance().oSelectRole.bytFrameIndex * 12, 0, 12, 23, 0, 3);
                        }
                        if (Param.getInstance().oSelectRole.blnUseMp) {
                            this.drawProtraitRmp(graphics, Param.getInstance().oSelectRole.intMP, Param.getInstance().oSelectRole.intMPMax, n10, n12);
                        } else {
                            this.drawProtraitRpower(graphics, Param.getInstance().oSelectRole.intMP, n10, n12);
                        }
                    }
                    this.drawProtraitRhp(graphics, Param.getInstance().oSelectRole.intHP, Param.getInstance().oSelectRole.intHPMax, n10, n11);
                    this.drawSelLv(graphics, n10, n13);
                    Param.GAME_UI_ROLE_DRAWBUFFX = (short) n7;
                    Param.GAME_UI_ROLE_DRAWBUFFY = (short) (n6 + 25 + 2);
                    Param.getInstance().oSelectRole.drawEffect(graphics, Param.GAME_UI_ROLE_DRAWBUFFX, Param.GAME_UI_ROLE_DRAWBUFFY);
                } else if (Param.getInstance().oSelectNpc != null && Param.getInstance().oSelectNpc.blnNpc) {
                    Param.getInstance().oSelectNpc.sprInstance.drawAnimationFrame(graphics, 0, 0, n8, n9);
                    this.drawProtraitRmp(graphics, 1, 1, n10, n12);
                    this.drawProtraitRhp(graphics, 1, 1, n10, n11);
                } else if (Param.getInstance().bSelectBox != null) {
                    this.drawProtraitRmp(graphics, 1, 1, n10, n12);
                    this.drawProtraitRhp(graphics, 1, 1, n10, n11);
                    Param.getInstance().bSelectBox.sprInstance.drawAnimationFrame(graphics, 0, 0, n8, n9);
                } else if (Param.getInstance().bSelectTask != null) {
                    Param.getInstance().bSelectTask.sprInstance.drawAnimationFrame(graphics, 0, 0, n8, n9);
                }
            }
            if (this.bytBuffIndex >= 0) {
                graphics.setColor(3067381);
                if (this.bytBuffIndex / 10 < 2) {
                    graphics.drawRect(Param.GAME_UI_ME_DRAWBUFFX - 1 + this.bytBuffIndex % 10 * 9 + this._drawMeOffX, Param.GAME_UI_ME_DRAWBUFFY - 1 + this.bytBuffIndex / 10 * 9, 9, 9);
                } else {
                    graphics.drawRect(Param.GAME_UI_ROLE_DRAWBUFFX - this.bytBuffIndex % 10 * 9 - 9 + this._drawMeOffX, Param.GAME_UI_ROLE_DRAWBUFFY - 1 + (this.bytBuffIndex - 20) / 10 * 9, 9, 9);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void updatePortrait() {
    }

    private void drawMagic(final Graphics graphics) {
        if (ORPMe.ME.shtMagicTimeMax <= 0) {
            DebugFrame.getInstance().logIn("Lỗi! Thời gian kỹ năng điện năng dữ liệu lỗi!");
            return;
        }
        int n = (Macro.SCREEN_WIDTH - this.shtMagiclength) / 2;
        int n2 = Macro.SCREEN_HEIGHT * 9 / 14;
        this.drawMagicBar(graphics, n, n2, this.shtMagiclength - ORPMe.ME.shtMagicTime * this.shtMagiclength / ORPMe.ME.shtMagicTimeMax, this.shtMagiclength);
        this.drawWhiteNum(graphics, ORPMe.ME.shtMagicTime / 1000, ORPMe.ME.shtMagicTime % 1000 / 100, n, n2 + 9, 4);
    }

    public void drawMagicBar(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        graphics.setColor(16777215);
        graphics.fillRect(n - 1, n2 - 1, n4 + 2, 9);
        graphics.setColor(12952426);
        graphics.fillRect(n, n2, n4, 6);
        graphics.setColor(13072133);
        graphics.drawLine(n, n2, n + n4 - 1, n2);
        graphics.setColor(8865578);
        graphics.drawLine(n, n2 + 1, n + n4 - 1, n2 + 1);
        graphics.setColor(16753699);
        graphics.drawLine(n, n2 + 6, n + n4 - 1, n2 + 6);
        graphics.setColor(556194);
        graphics.drawLine(n, n2 + 1, n + n3 - 1, n2 + 1);
        graphics.setColor(5830653);
        graphics.fillRect(n, n2 + 2, n3, 3);
        graphics.setColor(1819385);
        graphics.drawLine(n, n2 + 5, n + n3 - 1, n2 + 5);
    }

    private void drawSelect(final Graphics graphics, int n, int n2, final byte b, final boolean b2) {
        n -= Param.getInstance().CAMERAX;
        n2 -= Param.getInstance().CAMERAY;
        byte selectSize = this.getSelectSize(b);
        if (selectSize > -1) {
            if (b2) {
                if (Param.getInstance().redSelect.getAnimation() != selectSize) {
                    Param.getInstance().redSelect.setAnimation(selectSize);
                }
                Param.getInstance().redSelect.drawAnimation(graphics, n, n2);
            } else {
                if (Param.getInstance().blueSelect.getAnimation() != selectSize) {
                    Param.getInstance().blueSelect.setAnimation(selectSize);
                }
                Param.getInstance().blueSelect.drawAnimation(graphics, n, n2);
            }
        }
    }

    private void JudgeChooseGoal() {
        if (ORPMe.ME.pointFrame && DCanvas.getInstance().blnPointerPressed) {
            int n = 0;
            if (Param.getInstance().bytSelectType == 1) {
                n = 0;
            } else if (Param.getInstance().bytSelectType == 3) {
                n = 4;
            }
            if (Param.getInstance().bytSelectType == 1) {
                if (Param.getInstance().oSelectNpc != null) {
                    this.drawSelectRect(Param.getInstance().oSelectNpc, Param.getInstance().oSelectNpc.shtJudgeX, Param.getInstance().oSelectNpc.shtJudgeY, Param.getInstance().oSelectNpc.shtImgWidth, Param.getInstance().oSelectNpc.shtImgHeight, n);
                }
            } else if (Param.getInstance().bytSelectType == 3) {
                if (Param.getInstance().oSelectRole != null) {
                    this.drawSelectRect(Param.getInstance().oSelectRole, Param.getInstance().oSelectRole.shtJudgeX, Param.getInstance().oSelectRole.shtJudgeY, Param.getInstance().oSelectRole.shtImgWidth, Param.getInstance().oSelectRole.shtImgHeight, n);
                }
            } else if (Param.getInstance().bytSelectType == 2) {
                if (Param.getInstance().oSelectRole != null) {
                    this.drawSelectRect(Param.getInstance().oSelectRole, Param.getInstance().oSelectRole.shtJudgeX, Param.getInstance().oSelectRole.shtJudgeY, Param.getInstance().oSelectRole.shtImgWidth, Param.getInstance().oSelectRole.shtImgHeight, n);
                }
            } else if (Param.getInstance().bytSelectType == 5) {
                if (Param.getInstance().bSelectTask != null) {
                    this.drawSelectRect(Param.getInstance().bSelectTask, Param.getInstance().bSelectTask.shtJudgeX, Param.getInstance().bSelectTask.shtJudgeY, Param.getInstance().bSelectTask.shtImgWidth, Param.getInstance().bSelectTask.shtImgHeight, n);
                }
            } else if (Param.getInstance().bytSelectType == 4 && Param.getInstance().bSelectBox != null) {
                this.drawSelectRect(Param.getInstance().bSelectBox, Param.getInstance().bSelectBox.shtJudgeX, Param.getInstance().bSelectBox.shtJudgeY, Param.getInstance().bSelectBox.shtImgWidth, Param.getInstance().bSelectBox.shtImgHeight, n);
            }
        }
    }

    private void drawSelectRect(final ORole oRole, int n, int n2, final int n3, final int n4, final int n5) {
        n -= Param.getInstance().CAMERAX;
        n2 -= Param.getInstance().CAMERAY;
        DrawBase.drawRect(n - (oRole.shtImgWidth >> 1), n2 - oRole.shtImgHeight + n5, oRole.shtImgWidth, oRole.shtImgHeight + n5, 16711680);
    }

    public byte getSelectSize(final byte b) {
        byte b2;
        if (b <= 0) {
            b2 = -1;
        } else if (b <= 2) {
            b2 = 0;
        } else if (b <= 4) {
            b2 = 1;
        } else if (b <= 6) {
            b2 = 2;
        } else {
            b2 = 3;
        }
        return b2;
    }

    public void setTwoMenu(final byte b, final String[] array, final byte b2) {
        GameControl.getInstance().CreateState(b2);
        HandleUI.getInstance().setHandleOption(b2, b, array);
    }

    public void logic(final int n) {
        try {
            Map.getInstance().logic(n);
            if (this.blnIsThis && !Param.getInstance().blnTeach) {
                this.checkMeKey();
                if (DCanvas.getInstance().blnIsTouch && DCanvas.getInstance().blnPointerPressed) {
                    this.pointerLogic();
                }
            }
            if (this.bytDrawType == 1) {
                this.updateSmallMap();
            }
            if (Param.getInstance().redSelect.getAnimation() != -1) {
                Param.getInstance().redSelect.update();
            }
            if (Param.getInstance().blueSelect.getAnimation() != -1) {
                Param.getInstance().blueSelect.update();
            }
            if (!DCanvas.getInstance().blnPointerPressed) {
                ORPMe.ME.pointFrame = false;
            }
            this.intSelectTime += n;
            if (this.intSelectTime > 1000) {
                this.intSelectTime = 0;
                this.blnUpdateSelect = true;
            } else {
                this.blnUpdateSelect = false;
            }
            this.clearAllTarget();
            try {
                if (Param.getInstance().htTask != null && !Param.getInstance().htTask.isEmpty()) {
                    Enumeration elements = (Enumeration) Param.getInstance().htTask.elements();
                    while (elements.hasMoreElements()) {
                        Task task = (Task) elements.nextElement();
                        if (task.blnIsDraw) {
                            task.update();
                            task.checkAction(n);
                            if (!task.blnShine) {
                                continue;
                            }
                            this.checkTargetRange(task);
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            try {
                if (Param.getInstance().htBox != null && !Param.getInstance().htBox.isEmpty()) {
                    Enumeration elements2 = Param.getInstance().htBox.elements();
                    while (elements2.hasMoreElements()) {
                        Box box = (Box) elements2.nextElement();
                        if (box.blnIsDraw) {
                            box.update();
                            box.checkAction(n);
                            this.checkTargetRange(box);
                        }
                    }
                }
            } catch (Exception ex2) {
                ex2.printStackTrace();
            }
            if (Param.getInstance().peffect != null) {
                Param.getInstance().peffect.logic(n);
            }
            if (Param.getInstance().htRolePlayer != null) {
                Enumeration elements3 = (Enumeration) Param.getInstance().htRolePlayer.elements();
                while (elements3.hasMoreElements()) {
                    ORPlayer orPlayer = (ORPlayer) elements3.nextElement();
                    if (orPlayer != null) {
                        orPlayer.checkAction(n);
                        orPlayer.update();
                        this.checkTargetRange(orPlayer);
                    }
                }
            }
            if (Param.getInstance().htRoleMonster != null) {
                Enumeration elements4 = (Enumeration) Param.getInstance().htRoleMonster.elements();
                while (elements4.hasMoreElements()) {
                    ORMonster orMonster = (ORMonster) elements4.nextElement();
                    if (orMonster != null) {
                        orMonster.update();
                        orMonster.checkAction(n);
                        this.checkTargetRange(orMonster);
                    }
                }
            }
            if (Param.getInstance().htRolePet != null && !Param.getInstance().htRolePet.isEmpty()) {
                Enumeration elements5 = (Enumeration) Param.getInstance().htRolePet.elements();
                while (elements5.hasMoreElements()) {
                    PetObject petObject = (PetObject) elements5.nextElement();
                    if (petObject != null) {
                        petObject.update();
                        petObject.checkAction(n);
                    }
                }
            }
            if (Param.getInstance().htAnimal != null && !Param.getInstance().htAnimal.isEmpty()) {
                Enumeration elements6 = (Enumeration) Param.getInstance().htAnimal.elements();
                while (elements6.hasMoreElements()) {
                    AnimalObject animalObject = (AnimalObject) elements6.nextElement();
                    if (animalObject != null) {
                        animalObject.update();
                        animalObject.checkAction(n);
                    }
                }
            }
            if (Param.getInstance().htDecorative != null && !Param.getInstance().htDecorative.isEmpty()) {
                Enumeration elements7 = (Enumeration) Param.getInstance().htDecorative.elements();
                while (elements7.hasMoreElements()) {
                    ODecorative oDecorative = (ODecorative) elements7.nextElement();
                    if (oDecorative != null) {
                        oDecorative.update();
                        oDecorative.checkAction(n);
                    }
                }
            }
            if (Param.getInstance().htRoleNPC != null && !Param.getInstance().htRoleNPC.isEmpty()) {
                Enumeration elements8 = (Enumeration) Param.getInstance().htRoleNPC.elements();
                while (elements8.hasMoreElements()) {
                    ONpc oNpc = (ONpc) elements8.nextElement();
                    if (oNpc != null) {
                        oNpc.update();
                        oNpc.checkAction(n);
                        this.checkTargetRange(oNpc);
                    }
                }
            }
            if (this.bytHaveNpcChat > 0 && Param.getInstance().htRoleNPC != null) {
                if (this.intNpcChatId != 0) {
                    ONpc npc = this.getNpc(this.intNpcChatId);
                    if (npc != null && npc.chatLogic() == 0) {
                        this.intNpcChatId = 0;
                    }
                } else if (!this.blnFight) {
                    this.intNpcChatTime += n;
                    if (this.intNpcChatTime >= 15000) {
                        this.setRandomNpcChat();
                        this.intNpcChatTime = 0;
                    }
                }
            }
            if (this.blnHaveMonsterChat && Param.getInstance().htRoleMonster != null) {
                if (this.intMonsterChatId != 0) {
                    ORMonster orMonster2 = (ORMonster) Param.getInstance().htRoleMonster.get(new Integer(this.intMonsterChatId));
                    if (orMonster2 != null && orMonster2.chatLogic() == 0) {
                        this.intMonsterChatId = 0;
                    }
                } else if (!this.blnFight) {
                    this.intMonsterChatTime += n;
                    if (this.intMonsterChatTime >= 5000) {
                        this.setRandomMonsterChat();
                        this.intMonsterChatTime = 0;
                    }
                }
            }
            if (this.blnUpdateManuSelectAll) {
                this.blnUpdateManuSelectAll = false;
                this.printTargetINfo(GameUI.targetManuRole, false);
                GameUI.lastSelectTargetIndex = this.setSelectTarget(GameUI.targetManuRole, GameUI.lastSelectTargetIndex + 1, -1, -1, true);
                DebugFrame.getInstance().logIn("Sel: " + GameUI.lastSelectTargetIndex);
            } else if (this.blnUpdateManuSelectFriends) {
                this.blnUpdateManuSelectFriends = false;
                this.printTargetINfo(GameUI.targetManuRole, false);
                GameUI.lastSelectTargetIndex = this.setSelectTarget(GameUI.targetManuRole, GameUI.lastSelectTargetIndex + 1, 1, 1, true);
                DebugFrame.getInstance().logIn("Sel: " + GameUI.lastSelectTargetIndex);
            } else if (this.blnUpdateAutoSelect) {
                this.printTargetINfo(GameUI.targetRole, true);
                this.setSelectTarget(GameUI.targetRole, 0, -1, -1, false);
            }
            if (this.blnUpdateSelect) {
                ORPMe.ME.checkSelectFail();
            }
            this.updatePortrait();
            this.timeLogic(n);
            if (this.shtMeInfoTime > 0) {
                this.shtMeInfoTime -= (short) n;
                if (this.shtMeInfoTime <= 0) {
                    this.shtMeInfoTime = 0;
                }
            } else if (this.intChatShowTime > 0) {
                this.intChatShowTime -= n;
                if (this.intChatShowTime <= 5000) {
                    this.blnChannelType = false;
                }
            } else {
                this.isShowMsg = false;
            }
            this.updateSkin();
            Label_1347:
            {
                if (this.intsGetExpGold != null) {
                    int n2;
                    for (n2 = 0; n2 < this.intsGetExpGold.length && this.intsGetExpGold[n2][2] != 0; ++n2) {
                        if (this.intsGetExpGold[n2][2] > 0) {
                            int[] array = this.intsGetExpGold[n2];
                            int n3 = 2;
                            array[n3] += 6;
                            if (this.intsGetExpGold[n2][2] > 5 * Macro.FONTHEIGHT) {
                                if (n2 == this.intsGetExpGold.length - 1) {
                                    this.intsGetExpGold = null;
                                    break Label_1347;
                                }
                                this.intsGetExpGold[n2][2] = -1;
                            }
                        }
                    }
                    if (n2 > 0 && n2 < this.intsGetExpGold.length && (this.intsGetExpGold[n2 - 1][2] > Macro.FONTHEIGHT || this.intsGetExpGold[n2 - 1][2] == -1)) {
                        this.intsGetExpGold[n2][2] = 1;
                    }
                }
            }
            this.updateFringhInformation();
            if (this.intPKTime > 0) {
                this.intPKTempTime -= 100;
                if (this.blnPK) {
                    this.intPKTime -= 100;
                    if (this.intPKTime % this.TimeCycle == 0) {
                        DCanvas.getInstance().addInformation("Phó bản thời gian còn lại: " + this.intPKTime / 1000 + "Giây");
                    }
                } else if (this.intPKTempTime <= 0) {
                    this.blnPK = true;
                    this.intPKTempTime = this.intPKTime;
                    DCanvas.getInstance().addInformation("Phó bản bắt đầu");
                    DCanvas.getInstance().addInformation("Phó bản thời gian còn lại: " + (this.intPKTime / 1000 + 1) + "Giây");
                    if (Param.getInstance().htRolePlayer != null) {
                        ORPlayer orPlayer2 = (ORPlayer) Param.getInstance().htRolePlayer.get(new Integer(this.intPKRoleId));
                        if (orPlayer2 != null) {
                            orPlayer2.blnIsAttack = true;
                            if (this.intPKRoleId == Param.getInstance().intSelectId) {
                                ORPMe.ME.setSelectRole(Param.getInstance().oSelectRole);
                            }
                        }
                    }
                } else if (this.intPKTempTime > 0 && this.intPKTempTime % 1000 == 0) {
                    DCanvas.getInstance().addInformation(String.valueOf(this.intPKTempTime / 1000) + "Giây sau khi bắt đầu cuộc đấu");
                }
            }
            if (this.shtShowKeyInfoTime > 0) {
                this.shtShowKeyInfoTime -= (short) n;
                if (this.shtShowKeyInfoTime <= 0) {
                    this.strKeySelInfo = null;
                }
            }
            if (DCanvas.getInstance().blnIsTouch) {
                if (this.bytDrawMoveTime < 4) {
                    ++this.bytDrawMoveTime;
                } else if (this.bytDrawMoveY != -1) {
                    this.bytDrawMoveY = -1;
                }
            }
            this.HideShortcut();
            if (Param.Gifts_Time_Cout_Down / 1000L > 0L) {
                Param.Gifts_Time_Cout_Down -= n;
                if (Param.image_Gifts == null && Param.Gifts_Time_Cout_Down / 1000L > 0L) {
                    Param.image_Gifts = ImageManager.CreateImage(new StringBuffer(String.valueOf(Param.Gifts_Icon_Cout_Down)).toString(), "prop");
                }
                if (Param.Gifts_Time_Cout_Down / 1000L <= 0L) {
                    Param.image_Gifts = null;
                }
            }
            if (this.radaricon != null) {
                this.UpdateMiniMap();
            }
        } catch (Exception ex3) {
            ex3.printStackTrace();
        }
    }

    public void clean() {
        if (this.htImgBuffIcon != null) {
            this.htImgBuffIcon.clear();
        }
        GameUI.gu = null;
        if (Macro.BLN_ACCERATE_NPC_TASK) {
            FakeServer.getInstance().clean();
        }
    }

    public void isThis(final boolean blnIsThis) {
        this.blnIsThis = blnIsThis;
    }

    public void setLoading() {
        this.endPK(false);
        this.intNpcChatId = 0;
        this.intMonsterChatId = 0;
        this.bytHaveNpcChat = 0;
        this.blnHaveMonsterChat = false;
        if (DCanvas.getInstance().ULoading == null) {
            GameControl.getInstance().delUIbase(7);
            if (DCanvas.getInstance().UMenu != null) {
                GameControl.getInstance().delUIbase(MenuUI.getInstance().bytDelState);
            }
            GameControl.getInstance().delUIbase(4);
            GameControl.getInstance().CreateState((byte) 6);
        }
        this.cleanSmallMap();
        Map.getInstance().initMap();
        GameControl.getInstance().initGame();
        if (ORPMe.ME.bytState == 5) {
            ImageManager.getInstance().addDeadImg();
        }
        short shtAnuId = 0;
        short n = 0;
        int intUserId = 0;
        short shtType = 0;
        if (ORPMe.ME.playerFollowPet != null) {
            intUserId = ORPMe.ME.playerFollowPet.intUserId;
            short shtPicId = ORPMe.ME.playerFollowPet.shtPicId;
            shtAnuId = ORPMe.ME.playerFollowPet.shtAnuId;
            shtType = ORPMe.ME.playerFollowPet.shtType;
        }
        ORPMe.ME.resetMe();
        if (shtAnuId != 0) {
            ORPMe.ME.setPet(intUserId, shtAnuId, n, shtType);
        }
        if (ORPMe.ME.intsBuff == null && ORPMe.ME.intsDeBuff == null) {
            Param.getInstance().intsBuffColor = null;
        }
        if (Param.getInstance().bytsMyTeam != null) {
            for (int i = 0; i < Param.getInstance().bytsMyTeam.length; i = (byte) (i + 1)) {
                ((TeamObject) Param.getInstance().vTeam.elementAt(Param.getInstance().bytsMyTeam[i])).intNowBlood = -1;
                ((TeamObject) Param.getInstance().vTeam.elementAt(Param.getInstance().bytsMyTeam[i])).intNowMagic = -1;
            }
        }
    }

    public void setDialog(final byte b) {
        this.bytDrawType = 0;
        switch (b) {
            case 1: {
                if (this.checkDialogPRI(b, (byte) 1)) {
                    DialogUI.getInstance().setDialog(b, "Đến bản đồ: " + Map.getInstance().strOutJumpName[ORPMe.ME.shtEvent] + "？", (byte) 2);
                    break;
                }
                break;
            }
            case 4: {
                if (this.checkDialogPRI(b, (byte) 1)) {
                    this.bytDrawType = 1;
                    DialogUI.getInstance().setDialog(b, "Đang tải bản đồ ...", (byte) 2);
                    break;
                }
                break;
            }
            case 6: {
                if (this.checkDialogPRI(b, (byte) 4)) {
                    if (DCanvas.getInstance().UMenu != null) {
                        GameControl.getInstance().delUIbase(MenuUI.getInstance().bytDelState);
                    }
                    GameControl.getInstance().delUIbase(5);
                    GameControl.getInstance().delUIbase(8);
                    Param.strCountDownTxt = "Bạn đã chết và tái sanh vào lúc hồi sinh gần đây?";
                    DialogUI.getInstance().setDialog(b, "Bạn đã chết và tái sanh vào lúc hồi sinh gần đây?", (byte) 2);
                    break;
                }
                break;
            }
            case 3: {
                Label_0335:
                {
                    if (Param.getInstance().oSelectRole != null) {
                        if (Param.getInstance().oSelectRole.intsBuff != null) {
                            this.bytBuffIndex = 20;
                            break Label_0335;
                        }
                        if (Param.getInstance().oSelectRole.intsDeBuff != null) {
                            this.bytBuffIndex = 30;
                            break Label_0335;
                        }
                    }
                    if (ORPMe.ME.intsBuff != null) {
                        this.bytBuffIndex = 0;
                    } else {
                        if (ORPMe.ME.intsDeBuff == null) {
                            break;
                        }
                        this.bytBuffIndex = 10;
                    }
                }
                if (this.checkDialogPRI(b, (byte) 1)) {
                    DialogUI.getInstance().setDialogBuff(this.bytBuffIndex);
                    break;
                }
                break;
            }
            case 8: {
                if (this.checkDialogPRI(b, (byte) 5)) {
                    DialogUI.getInstance().setEquipDialog();
                    break;
                }
                break;
            }
            case 9: {
                if (this.checkDialogPRI(b, (byte) 3)) {
                    DialogUI.getInstance().setDialog((byte) 9, " ", (byte) 9);
                    Param.getInstance().intDealGold = -1;
                    Param.getInstance().intDealMyGold = -1;
                    Param.getInstance().bytDealType = 0;
                    Param.getInstance().vDealDuiProp = null;
                    Param.getInstance().vDealMyProp = null;
                    break;
                }
                break;
            }
            case 14: {
                if (this.checkDialogPRI(b, (byte) 3)) {
                    DialogUI.getInstance().setDialog(b, "Có phải bỏ cuộc chơi không?", (byte) 2);
                    break;
                }
                break;
            }
            case 16: {
                if (this.checkDialogPRI(b, (byte) 2)) {
                    String string;
                    if (Param.getInstance().bytCrystalType == 1) {
                        string = "Hãy chắc chắn để đánh dấu linh hồn của bạn tại" + Map.getInstance().strName + "Là nó?";
                    } else {
                        string = "Xác định bản đồ được gửi đến dấu linh hồn của bạn?";
                    }
                    DialogUI.getInstance().setDialog(b, string, (byte) 2);
                    break;
                }
                break;
            }
            case 17: {
                if (this.checkDialogPRI(b, (byte) 2)) {
                    DialogUI.getInstance().setDialog(b, "Bạn có chắc chắn muốn hủy cửa hàng cá nhân của mình?", (byte) 2);
                    break;
                }
                break;
            }
            case 18: {
                if (this.checkDialogPRI(b, (byte) 2)) {
                    StringBuffer sb = new StringBuffer();
                    sb.append(Param.getInstance().strShiTuName);
                    if (Param.getInstance().blnIsBaiShi) {
                        sb.append("Hy vọng là học việc của bạn");
                    } else {
                        sb.append("Bạn muốn chấp nhận bạn là một môn đệ");
                    }
                    sb.append(", Sẵn sàng?");
                    DialogUI.getInstance().setDialog(b, sb.toString(), (byte) 2);
                    break;
                }
                break;
            }
            case -21: {
                if (this.checkDialogPRI(b, (byte) 3)) {
                    DialogUI.getInstance().setDialog(b, Param.getInstance().askdialog, (byte) 2);
                    break;
                }
                break;
            }
        }
    }

    public void setHintDialog(final byte b, final String s) {
        this.bytDrawType = 0;
        if (this.checkDialogPRI(b, (byte) 1)) {
            DialogUI.getInstance().setDialog(b, s, (byte) 2);
        }
    }

    public boolean checkDialogPRI(final byte b, final byte bytDialogType) {
        if (b == 9 || b == 8 || b == 6) {
            if (DCanvas.getInstance().UMenu != null) {
                GameControl.getInstance().delUIbase(MenuUI.getInstance().bytDelState);
            }
            GameControl.getInstance().delUIbase(5);
            GameControl.getInstance().delUIbase(8);
        }
        if (DCanvas.getInstance().UDialog == null) {
            GameControl.getInstance().CreateState((byte) 7);
            DialogUI.getInstance().bytDialogType = bytDialogType;
            return true;
        }
        byte bytDialogType2 = DialogUI.getInstance().bytDialogType;
        byte bytDialogState = DialogUI.getInstance().bytDialogState;
        if (DialogUI.getInstance().bytDialogState == 10 && b != 9) {
            if (bytDialogType == 2 || bytDialogType == 3 || bytDialogType == 5) {
                this.returnDialog(b, false);
            }
            return false;
        }
        if (bytDialogType > bytDialogType2) {
            if (bytDialogType2 == 2 || bytDialogType2 == 3) {
                this.returnDialog(bytDialogState, true);
            }
            DialogUI.getInstance().bytDialogType = bytDialogType;
            return true;
        }
        if (bytDialogType == 2 || bytDialogType == 3) {
            this.returnDialog(b, false);
        } else if (bytDialogType == bytDialogType2) {
            DialogUI.getInstance().bytDialogType = bytDialogType;
            return true;
        }
        return false;
    }

    private void returnDialog(final byte b, final boolean b2) {
        switch (b) {
            case 11: {
                NetSend.getInstance().sendTeamInvite((byte) 0);
                break;
            }
            case 12: {
                NetSend.getInstance().sendConsortiaInvite((byte) 0);
                break;
            }
            case 13: {
                NetSend.getInstance().sendPlayerPk(Param.getInstance().intPkId, (byte) 4);
                break;
            }
            case 9: {
                if (b2) {
                    NetSend.getInstance().sendDeal((byte) 6, Param.getInstance().intDealID);
                    break;
                }
                NetSend.getInstance().sendDeal((byte) 8, Param.getInstance().intDealID);
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
        }
    }

    public void drawShade(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        graphics.setStrokeStyle(0);
        graphics.setColor(Macro.SHADE_COLOR);
        graphics.fillArc(n, n2, n3, n4, 0, 360);
    }

    public void drawWhiteNum(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5) {
        StringBuffer sb = new StringBuffer(Integer.toString(n));
        int n6 = n3;
        if (n5 == 1) {
            n6 -= sb.length() * 4;
        } else if (n5 == 8) {
            n6 -= sb.length() * 7;
        }
        for (int i = 0; i < sb.length(); ++i) {
            int n7 = sb.charAt(i) - '0';
            graphics.drawRGB(Param.getInstance().intsNumberBackColor, 0, 4, n6, n4 - 1, 4, 6, true);
            DrawBase.drawRegion(Param.getInstance().imgWhiteNum, n6, n4, n7 * 3, 0, 3, 5, 0, n5 | 0x10);
            n6 += 4;
        }
        if (n2 >= 0) {
            graphics.drawRGB(Param.getInstance().intsNumberBackColor, 0, 4, n6, n4 - 1, 1, 6, true);
            DrawBase.drawRegion(Param.getInstance().imgWhiteNum, n6, n4, 30, 0, 1, 5, 0, n5 | 0x10);
            ++n6;
            StringBuffer sb2 = new StringBuffer(Integer.toString(n2));
            for (int j = 0; j < sb2.length(); ++j) {
                int n8 = sb2.charAt(j) - '0';
                graphics.drawRGB(Param.getInstance().intsNumberBackColor, 0, 4, n6, n4 - 1, 4, 6, true);
                DrawBase.drawRegion(Param.getInstance().imgWhiteNum, n6, n4, n8 * 3, 0, 3, 5, 0, n5 | 0x10);
                n6 += 4;
            }
        }
    }

    public void drawNum(final Graphics graphics, final int n, final int n2, final int n3, final byte b, final QSprite qSprite) {
        StringBuffer sb = new StringBuffer(Integer.toString(n));
        int n4 = n2 - sb.length() * 7;
        if (3 == b || 4 == b) {
            qSprite.drawAnimationFrame(graphics, 10, 0, n4 - 14, n3);
        }
        for (int i = 0; i < sb.length(); ++i) {
            qSprite.drawAnimationFrame(graphics, sb.charAt(i) - '0', 0, n4, n3);
            if (2 == b) {
                n4 += 16;
            } else if (1 == b) {
                n4 += 19;
            } else if (3 == b || 4 == b) {
                n4 += 12;
            }
        }
    }

    public void drawMiss(final Graphics graphics, final int n, final int n2, final int n3, final QSprite qSprite) {
        qSprite.drawAnimationFrame(graphics, n, 0, n2, n3);
    }

    public void drawWhiteNum(final Graphics graphics, final int n, final int n2, final int n3) {
        StringBuffer sb = new StringBuffer(Integer.toString(n));
        int n4 = n2;
        for (int i = 0; i < sb.length(); ++i) {
            int n5 = sb.charAt(i) - '0';
            graphics.drawRGB(Param.getInstance().intsNumberBackColor, 0, 4, n4, n3 - 1, 4, 6, true);
            DrawBase.drawRegion(Param.getInstance().imgWhiteNum, n4, n3, n5 * 3, 0, 3, 5, 0, 20);
            n4 += 4;
        }
    }

    public void drawWhiteKey(final Graphics graphics, final int n, final int n2, final int n3) {
        this.Number.drawAnimationFrame(graphics, n + 1, 0, n2, n3);
    }

    public byte[] getBytes(final int n) {
        StringBuffer sb = new StringBuffer(Integer.toString(n));
        byte[] array = new byte[sb.length()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = (byte) (sb.charAt(i) - '0');
        }
        return array;
    }

    public void drawMeLv(final Graphics graphics, final int n, final int n2) {
        int n3 = n - this.bytMeLevel.length * 4 - this.bytMeLevel.length / 2;
        if (this.bytMeLevel.length == 1) {
            this.Number.drawAnimationFrame(graphics, this.bytMeLevel[0], 0, n - 2, n2 + 9);
        } else if (this.bytMeLevel.length == 2) {
            this.Number.drawAnimationFrame(graphics, this.bytMeLevel[0], 0, n - 4, n2 + 9);
            this.Number.drawAnimationFrame(graphics, this.bytMeLevel[1], 0, n + 1, n2 + 9);
        }
    }

    public void drawSelLv(final Graphics graphics, final int n, final int n2) {
        if (this.bytMeSelLevel == null) {
            return;
        }
        int n3 = n;
        for (int i = this.bytMeSelLevel.length - 1; i >= 0; --i) {
            DrawBase.drawRegion(this.imgProtraitLV, n3, n2, this.bytMeSelLevel[i] * 7, 0, 7, 9, 0, 20);
            n3 -= 6;
        }
    }

    public void drawInfoNum(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final byte b) {
        StringBuffer sb = new StringBuffer(Integer.toString(n));
        int n5 = n3;
        for (int i = 0; i < sb.length(); ++i) {
            DrawBase.drawRegion(Param.getInstance().imgHMPNum, n5, n4, (sb.charAt(i) - '0') * 5, 0, 5, 8, 0, 20);
            n5 += 7;
        }
        DrawBase.drawRegion(Param.getInstance().imgHMPNum, n5, n4, b, 0, 5, 8, 0, 20);
        n5 += 7;
        StringBuffer sb2 = new StringBuffer(Integer.toString(n2));
        for (int j = 0; j < sb2.length(); ++j) {
            DrawBase.drawRegion(Param.getInstance().imgHMPNum, n5, n4, (sb2.charAt(j) - '0') * 5, 0, 5, 8, 0, 20);
            n5 += 7;
        }
    }

    public int moveBuffIndex(final byte b) {
        switch (b) {
            case 4: {
                if (this.bytBuffIndex < 10) {
                    if (ORPMe.ME.intsBuff != null && this.bytBuffIndex < ORPMe.ME.intsBuff.length - 1) {
                        ++this.bytBuffIndex;
                        break;
                    }
                    if (Param.getInstance().oSelectRole == null) {
                        break;
                    }
                    if (Param.getInstance().oSelectRole.intsBuff != null) {
                        this.bytBuffIndex = (byte) (Param.getInstance().oSelectRole.intsBuff.length - 1 + 20);
                        break;
                    }
                    if (Param.getInstance().oSelectRole.intsDeBuff != null) {
                        this.bytBuffIndex = (byte) (Param.getInstance().oSelectRole.intsDeBuff.length - 1 + 30);
                        break;
                    }
                    break;
                } else if (this.bytBuffIndex < 20) {
                    if (ORPMe.ME.intsDeBuff != null && this.bytBuffIndex - 10 < ORPMe.ME.intsDeBuff.length - 1) {
                        ++this.bytBuffIndex;
                        break;
                    }
                    if (Param.getInstance().oSelectRole == null) {
                        break;
                    }
                    if (Param.getInstance().oSelectRole.intsDeBuff != null) {
                        this.bytBuffIndex = (byte) (Param.getInstance().oSelectRole.intsDeBuff.length - 1 + 30);
                        break;
                    }
                    if (Param.getInstance().oSelectRole.intsBuff != null) {
                        this.bytBuffIndex = (byte) (Param.getInstance().oSelectRole.intsBuff.length - 1 + 20);
                        break;
                    }
                    break;
                } else if (this.bytBuffIndex < 30) {
                    if (Param.getInstance().oSelectRole != null && Param.getInstance().oSelectRole.intsBuff != null && this.bytBuffIndex % 10 > 0) {
                        --this.bytBuffIndex;
                        break;
                    }
                } else {
                    if (this.bytBuffIndex < 40 && Param.getInstance().oSelectRole != null && Param.getInstance().oSelectRole.intsDeBuff != null && this.bytBuffIndex % 10 > 0) {
                        --this.bytBuffIndex;
                        break;
                    }
                }
                break;
            }
            case 3: {
                if (this.bytBuffIndex < 10) {
                    if (ORPMe.ME.intsBuff != null && this.bytBuffIndex > 0) {
                        --this.bytBuffIndex;
                        break;
                    }
                } else if (this.bytBuffIndex < 20) {
                    if (ORPMe.ME.intsDeBuff != null && this.bytBuffIndex % 10 > 0) {
                        --this.bytBuffIndex;
                        break;
                    }
                } else if (this.bytBuffIndex < 30) {
                    if (Param.getInstance().oSelectRole != null && Param.getInstance().oSelectRole.intsBuff != null && this.bytBuffIndex - 20 < Param.getInstance().oSelectRole.intsBuff.length - 1) {
                        ++this.bytBuffIndex;
                        break;
                    }
                    if (ORPMe.ME.intsBuff != null) {
                        this.bytBuffIndex = (byte) (ORPMe.ME.intsBuff.length - 1);
                        break;
                    }
                    if (ORPMe.ME.intsDeBuff != null) {
                        this.bytBuffIndex = (byte) (ORPMe.ME.intsDeBuff.length - 1 + 10);
                        break;
                    }
                } else {
                    if (this.bytBuffIndex >= 40) {
                        break;
                    }
                    if (Param.getInstance().oSelectRole != null && Param.getInstance().oSelectRole.intsDeBuff != null && this.bytBuffIndex - 30 < Param.getInstance().oSelectRole.intsDeBuff.length - 1) {
                        ++this.bytBuffIndex;
                        break;
                    }
                    if (ORPMe.ME.intsDeBuff != null) {
                        this.bytBuffIndex = (byte) (ORPMe.ME.intsDeBuff.length - 1 + 10);
                        break;
                    }
                    if (ORPMe.ME.intsBuff != null) {
                        this.bytBuffIndex = (byte) (ORPMe.ME.intsBuff.length - 1);
                        break;
                    }
                }
                break;
            }
            case 1: {
                if (this.bytBuffIndex >= 10 && this.bytBuffIndex < 20) {
                    if (ORPMe.ME.intsBuff != null) {
                        if (this.bytBuffIndex - 10 > ORPMe.ME.intsBuff.length - 1) {
                            this.bytBuffIndex = (byte) (ORPMe.ME.intsBuff.length - 1);
                            break;
                        }
                        this.bytBuffIndex -= 10;
                    } else {
                        if (Param.getInstance().oSelectRole != null && Param.getInstance().oSelectRole.intsBuff != null) {
                            this.bytBuffIndex = (byte) (Param.getInstance().oSelectRole.intsBuff.length - 1 + 20);
                            break;
                        }
                    }
                } else {
                    if (this.bytBuffIndex < 30) {
                        break;
                    }
                    if (Param.getInstance().oSelectRole != null && Param.getInstance().oSelectRole.intsBuff != null) {
                        if (this.bytBuffIndex - 30 > Param.getInstance().oSelectRole.intsBuff.length - 1) {
                            this.bytBuffIndex = (byte) (Param.getInstance().oSelectRole.intsBuff.length - 1 + 20);
                            break;
                        }
                        this.bytBuffIndex -= 10;
                    } else {
                        if (ORPMe.ME.intsBuff != null) {
                            this.bytBuffIndex = (byte) (ORPMe.ME.intsBuff.length - 1);
                            break;
                        }
                    }
                }
                break;
            }
            case 2: {
                if (this.bytBuffIndex < 10) {
                    if (ORPMe.ME.intsDeBuff != null) {
                        if (this.bytBuffIndex > ORPMe.ME.intsDeBuff.length - 1) {
                            this.bytBuffIndex = (byte) (ORPMe.ME.intsDeBuff.length - 1 + 10);
                            break;
                        }
                        this.bytBuffIndex += 10;
                        break;
                    } else {
                        if (Param.getInstance().oSelectRole != null && Param.getInstance().oSelectRole.intsDeBuff != null) {
                            this.bytBuffIndex = (byte) (Param.getInstance().oSelectRole.intsDeBuff.length - 1 + 30);
                            break;
                        }
                        break;
                    }
                } else {
                    if (this.bytBuffIndex < 20 || this.bytBuffIndex >= 30) {
                        break;
                    }
                    if (Param.getInstance().oSelectRole != null && Param.getInstance().oSelectRole.intsDeBuff != null) {
                        if (this.bytBuffIndex - 20 > Param.getInstance().oSelectRole.intsDeBuff.length - 1) {
                            this.bytBuffIndex = (byte) (Param.getInstance().oSelectRole.intsDeBuff.length - 1 + 30);
                            break;
                        }
                        this.bytBuffIndex += 10;
                    } else {
                        if (ORPMe.ME.intsDeBuff != null) {
                            this.bytBuffIndex = (byte) (ORPMe.ME.intsDeBuff.length - 1 + 10);
                            break;
                        }
                    }
                }
                break;
            }
        }
        return this.bytBuffIndex;
    }

    private boolean checkBuffIndex(final byte b) {
        if (b < 10) {
            if (ORPMe.ME.intsBuff != null && b < ORPMe.ME.intsBuff.length) {
                return true;
            }
        } else if (b < 20) {
            if (ORPMe.ME.intsDeBuff != null && b - 10 < ORPMe.ME.intsDeBuff.length) {
                return true;
            }
        } else if (b < 30) {
            if (Param.getInstance().oSelectRole != null && Param.getInstance().oSelectRole.intsBuff != null && b - 20 < Param.getInstance().oSelectRole.intsBuff.length) {
                return true;
            }
        } else if (b < 40 && Param.getInstance().oSelectRole != null && Param.getInstance().oSelectRole.intsDeBuff != null && b - 30 < Param.getInstance().oSelectRole.intsDeBuff.length) {
            return true;
        }
        return false;
    }

    public byte getDirection(final byte b, final byte b2, final byte b3, final byte b4, final byte b5, final byte b6) {
        int n = 0;
        int n2 = 0;
        byte transPicSize = ORole.transPicSize(b3);
        byte transPicSize2 = ORole.transPicSize(b6);
        for (byte b7 = 0, b8 = (byte) (-transPicSize / 2); b7 < transPicSize; ++b7, ++b8) {
            for (byte b9 = (byte) (-transPicSize2 / 2); b7 < transPicSize2; ++b7, ++b9) {
                if (b + b8 < b4 + b9) {
                    n2 += b4 + b9 - (b + b8);
                } else if (b + b8 > b4 + b7) {
                    n2 += b4 + b9 - (b + b8);
                }
            }
            if (b2 < b5) {
                n = transPicSize2 * (b2 - b5);
            } else if (b2 > b5) {
                n = transPicSize2 * (b2 - b5);
            }
        }
        if (Math.abs(n) > Math.abs(n2)) {
            if (n > 0) {
                return 1;
            }
            return 2;
        } else {
            if (n2 >= 0) {
                return 4;
            }
            return 3;
        }
    }

    public boolean checkMeMoveKey() {
        if (ORPMe.ME.blnTaskOK && !ORPMe.ME.blnStall) {
            if (Rms.blnAutoWalk) {
                if (ORPMe.ME.bytState == 1) {
                    for (int i = 0; i < DCanvas.getInstance().intMoveKey.length; ++i) {
                        if (DCanvas.getInstance().IsKeyHold(DCanvas.getInstance().intMoveKey[i], ORPMe.ME.timeKeepDirMoveMax)) {
                            ORPMe.ME.blnKeepDirMove = true;
                            ORPMe.ME.intKeepMoveKeyCode = DCanvas.getInstance().intMoveKey[i];
                            break;
                        }
                    }
                } else {
                    ORPMe.ME.blnKeepDirMove = false;
                    ORPMe.ME.intKeepMoveKeyCode = -1;
                }
            }
            if (Rms.blnAutoWalk && ORPMe.ME.blnKeepDirMove) {
                for (int j = 0; j < DCanvas.getInstance().intMoveKey.length; ++j) {
                    if ((DCanvas.getInstance().intMoveKey[j] & this.getReverseKey(ORPMe.ME.intKeepMoveKeyCode)) != 0x0) {
                        if (DCanvas.getInstance().isKeyDown(DCanvas.getInstance().intMoveKey[j]) || DCanvas.getInstance().IsKeyHold(DCanvas.getInstance().intMoveKey[j]) || DCanvas.getInstance().IsKeyRelease(DCanvas.getInstance().intMoveKey[j])) {
                            new StringBuffer("Remove Keep:").append(ORPMe.ME.intKeepMoveKeyCode).toString();
                            ORPMe.ME.blnKeepDirMove = false;
                            ORPMe.ME.intKeepMoveKeyCode = -1;
                            return true;
                        }
                    } else if (DCanvas.getInstance().IsKeyHold(DCanvas.getInstance().intMoveKey[j])) {
                        if ((DCanvas.getInstance().intMoveKey[j] & this.getReverseKey(ORPMe.ME.intKeepMoveKeyCode)) != 0x0) {
                            new StringBuffer("Remove Keep:").append(ORPMe.ME.intKeepMoveKeyCode).toString();
                            ORPMe.ME.blnKeepDirMove = false;
                            ORPMe.ME.intKeepMoveKeyCode = -1;
                            return true;
                        }
                        ORPMe.ME.intKeepMoveKeyCode = DCanvas.getInstance().intMoveKey[j];
                        break;
                    }
                }
            }
            if (DCanvas.getInstance().IsKeyHold(DCanvas.getInstance().intMoveKey[0]) || (ORPMe.ME.blnKeepDirMove && ORPMe.ME.intKeepMoveKeyCode == DCanvas.getInstance().intMoveKey[0])) {
                this.meMove((byte) 1);
                return true;
            }
            if (DCanvas.getInstance().IsKeyHold(DCanvas.getInstance().intMoveKey[1]) || (ORPMe.ME.blnKeepDirMove && ORPMe.ME.intKeepMoveKeyCode == DCanvas.getInstance().intMoveKey[1])) {
                this.meMove((byte) 3);
                return true;
            }
            if (DCanvas.getInstance().IsKeyHold(DCanvas.getInstance().intMoveKey[2]) || (ORPMe.ME.blnKeepDirMove && ORPMe.ME.intKeepMoveKeyCode == DCanvas.getInstance().intMoveKey[2])) {
                this.meMove((byte) 4);
                return true;
            }
            if (DCanvas.getInstance().IsKeyHold(DCanvas.getInstance().intMoveKey[3]) || (ORPMe.ME.blnKeepDirMove && ORPMe.ME.intKeepMoveKeyCode == DCanvas.getInstance().intMoveKey[3])) {
                this.meMove((byte) 2);
                return true;
            }
        }
        return false;
    }

    private int getReverseKey(final int n) {
        int n2 = -1;
        if (DCanvas.getInstance().intMoveKey != null) {
            if (n == DCanvas.getInstance().intMoveKey[0]) {
                n2 = DCanvas.getInstance().intMoveKey[3];
            } else if (n == DCanvas.getInstance().intMoveKey[1]) {
                n2 = DCanvas.getInstance().intMoveKey[2];
            } else if (n == DCanvas.getInstance().intMoveKey[2]) {
                n2 = DCanvas.getInstance().intMoveKey[1];
            } else if (n == DCanvas.getInstance().intMoveKey[3]) {
                n2 = DCanvas.getInstance().intMoveKey[0];
            }
        }
        return n2;
    }

    public void doMoveKey(final int n) {
        if (n == -1) {
            return;
        }
        if (!ORPMe.ME.blnStall) {
            byte b = (byte) (this.bytShortcutKeysType * 13 + n);
            if (Param.getInstance().intShortcutKeys[b][4] == 1) {
                switch (Param.getInstance().intShortcutKeys[b][0]) {
                    case 1: {
                        this.meMove((byte) 1);
                        break;
                    }
                    case 4: {
                        this.meMove((byte) 2);
                        break;
                    }
                    case 2: {
                        this.meMove((byte) 3);
                        break;
                    }
                    case 3: {
                        this.meMove((byte) 4);
                        break;
                    }
                }
            }
        }
    }

    public void doKey(final int n) {
        if (n == -1) {
            return;
        }
        byte b = (byte) (this.bytShortcutKeysType * 13 + n);
        if (Param.getInstance().intShortcutKeys[b][4] == 2) {
            ORPMe.ME.doUseKey(true, b);
        } else if (Param.getInstance().intShortcutKeys[b][4] == 1) {
            switch (Param.getInstance().intShortcutKeys[b][0]) {
                case 5: {
                    FormDes.getInstance().showForm((byte) 40);
                    this.blnChatFace = false;
                    break;
                }
                case 6: {
                    GameControl.getInstance().CreateState((byte) 3);
                    this.blnChatFace = true;
                    MenuUI.getInstance().setState((byte) 42, "Lịch sử trò chuyện");
                    MenuUI.getInstance().blnSetChatNote = true;
                    MenuUI.getInstance().setChatNote();
                    break;
                }
                case 8: {
                    this.openSmallMap();
                    break;
                }
                case 14: {
                    this.setNextMenu((byte) 63, "Nhiệm vụ");
                    break;
                }
                case 15: {
                    Param.getInstance().packageBoxMaxNum = Param.getInstance().bytAccouterBag;
                    this.setNextMenu((byte) 10, "Trang bị");
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendFrameEquip((byte) 1, (byte) 0, 0);
                    break;
                }
                case 16: {
                    this.setNextMenu((byte) (-7), "Thông tin về nhân vật");
                    break;
                }
                case 17: {
                    this.setNextMenu((byte) 35, "Đội");
                    break;
                }
                case 18: {
                    Param.getInstance().shtNoncePage = 1;
                    this.setNextMenu((byte) (-31), "Danh sách thân nhân");
                    break;
                }
                case 19: {
                    if (ORPMe.ME.strConsortia == null || ORPMe.ME.strConsortia.equals("")) {
                        DCanvas.getInstance().addInformation("Bạn đã không tham gia bất kỳ bang hội nào");
                        break;
                    }
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendConsortia((byte) 1, (byte) 1, 1);
                    break;
                }
                case 22: {
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendMailList();
                    this.setNextMenu((byte) (-36), "Hộp thư");
                    break;
                }
                case 24: {
                    Rms.blnShowRole = !Rms.blnShowRole;
                    if (Rms.blnShowRole) {
                        this.delRoleStateIcon((short) 3);
                        break;
                    }
                    if (Param.getInstance().oSelectRole != null) {
                        ORPMe.ME.delSelsectRole();
                    }
                    this.pushRoleStateIcon((short) 3);
                    break;
                }
                case 25: {
                    this.blnUpdateManuSelectAll = true;
                    break;
                }
                case 26: {
                    this.blnUpdateManuSelectFriends = true;
                    break;
                }
                case 9: {
                    Rms.bytShowName = (byte) ((++Rms.bytShowName >= 5) ? 0 : Rms.bytShowName);
                    break;
                }
            }
        } else {
            if (Param.getInstance().intShortcutKeys[b][4] != 3) {
                return;
            }
            ORPMe.ME.doUseKey(false, b);
        }
        if (Rms.blnShowKeyInfo) {
            this.shtShowKeyInfoTime = 2000;
            this.strKeySelInfo = Param.getInstance().strShortcutKeys[b];
            for (byte bytKeySelNum = 0; bytKeySelNum < DCanvas.getInstance().bytDrawKeyIndex.length; ++bytKeySelNum) {
                if (DCanvas.getInstance().bytDrawKeyIndex[bytKeySelNum] == b % 13) {
                    this.bytKeySelNum = bytKeySelNum;
                    break;
                }
            }
        }
    }

    public void openSmallMap() {
        this.bytDrawType = 1;
        this.bytMapState = 1;
        if (this.isMapExist((byte) 1, Map.getInstance().bytMapClanType)) {
            this.initMapinfo();
        } else {
            DCanvas.getInstance().setNetLoad(true);
            NetSend.getInstance().sendAreaMapInfo((byte) 0, Map.getInstance().shtMapId, Map.getInstance().bytMapClanType, 0);
            getInstance().setDialog((byte) 4);
        }
    }

    public byte checkDirectionKeyHold() {
        if (DCanvas.getInstance().IsKeyHold(DCanvas.getInstance().intMoveKey[0])) {
            return 1;
        }
        if (DCanvas.getInstance().IsKeyHold(DCanvas.getInstance().intMoveKey[1])) {
            return 3;
        }
        if (DCanvas.getInstance().IsKeyHold(DCanvas.getInstance().intMoveKey[2])) {
            return 4;
        }
        if (DCanvas.getInstance().IsKeyHold(DCanvas.getInstance().intMoveKey[3])) {
            return 2;
        }
        return -1;
    }

    public void checkMeKey() {
        this.blnUpdateAutoSelect = false;
        this.blnUpdateManuSelectAll = false;
        this.blnUpdateManuSelectFriends = false;
        if (Rms.blnAutoSelect && ORPMe.ME.bytState == 1 && !getInstance().blnFight && !Macro.blnDistanceFarAStar) {
            this.blnUpdateAutoSelect = true;
        }
        switch (this.bytDrawType) {
            case 0: {
                if (this.checkMeMoveKey()) {
                    return;
                }
                if (DCanvas.getInstance().IsKeyRelease(131072)) {
                    DCanvas.getInstance().buttonRightFlash = 0;
                    ORPMe.ME.doRightKey();
                    break;
                }
                if (DCanvas.getInstance().isKeyDown(131072)) {
                    DCanvas.getInstance().buttonRightFlash = 1;
                    break;
                }
                if (DCanvas.getInstance().IsKeyRelease(65536) || DCanvas.getInstance().IsKeyRelease(262144)) {
                    if (getInstance().checkMeSystemMenuUi(-30)) {
                        ORPMe.ME.doLeftKey();
                        break;
                    }
                    if (DCanvas.getInstance().IsKeyRelease(65536)) {
                        if (getInstance().bytDrawType == 0 && !ORPMe.ME.blnStall) {
                            Param.getInstance().vOptionPlace = new Vector(1, 1);
                            getInstance().hotKeyMenuSwitch = 1;
                            GameControl.getInstance().CreateState((byte) 4);
                            SystemMenuUI.getInstance().setState((byte) (-30));
                            break;
                        }
                        ORPMe.ME.doLeftKey();
                        break;
                    } else {
                        if (!DCanvas.getInstance().IsKeyRelease(262144)) {
                            break;
                        }
                        if (Param.getInstance().bytSelectType == 0) {
                            this.blnUpdateManuSelectAll = true;
                            break;
                        }
                        ORPMe.ME.doLeftKey();
                        break;
                    }
                } else {
                    if (DCanvas.getInstance().isKeyDown(2048) || (DCanvas.getInstance().IsKeyRelease(262144) && Param.getInstance().intSelectId <= 0)) {
                        this.blnUpdateManuSelectAll = true;
                        break;
                    }
                    if (DCanvas.getInstance().isKeyDown(512)) {
                        this.doChangeKeyType();
                        break;
                    }
                    if (DCanvas.getInstance().isKeyDown(1024)) {
                        this.blnUpdateManuSelectFriends = true;
                        break;
                    }
                    if (DCanvas.getInstance().isKeyDown(1)) {
                        GameControl.getInstance().CreateState((byte) 3);
                        this.blnChatFace = true;
                        MenuUI.getInstance().setState((byte) 42, "Lịch sử trò chuyện");
                        MenuUI.getInstance().blnSetChatNote = true;
                        MenuUI.getInstance().setChatNote();
                        break;
                    }
                    this.doKey(DCanvas.getInstance().checkKeyDown());
                    this.doMoveKey(DCanvas.getInstance().checkKeyPress());
                }
                break;
            }
            case 1: {
                if (DCanvas.getInstance().IsKeyRelease(65536) || DCanvas.getInstance().IsKeyRelease(262144)) {
                    this.mapCheckKey(65536);
                    DCanvas.getInstance().buttonLeftFlash = 0;
                    break;
                }
                if (DCanvas.getInstance().isKeyDown(65536) || DCanvas.getInstance().IsKeyRelease(262144)) {
                    DCanvas.getInstance().buttonLeftFlash = 1;
                    break;
                }
                if (DCanvas.getInstance().IsKeyRelease(131072)) {
                    this.mapCheckKey(131072);
                    DCanvas.getInstance().buttonRightFlash = 0;
                    break;
                }
                if (DCanvas.getInstance().isKeyDown(131072)) {
                    DCanvas.getInstance().buttonRightFlash = 1;
                    break;
                }
                byte checkDirectionKeyHold = this.checkDirectionKeyHold();
                if (checkDirectionKeyHold != -1) {
                    this.mapCheckKey(checkDirectionKeyHold);
                    break;
                }
                break;
            }
        }
    }

    public boolean setAStar(final byte bytAstarMoveEndX, final byte bytAstarMoveEndY) {
        boolean b = false;
        if ((ORPMe.ME.blnTaskOK || ORPMe.ME.bytState == 1) && !ORPMe.ME.blnStall) {
            if (ORPMe.ME.blnCanMove) {
                ORPMe.ME.delTask((byte) 1);
                byte[] path = new AStar(ORPMe.ME.bytTileX, ORPMe.ME.bytTileY, bytAstarMoveEndX, bytAstarMoveEndY, (byte) 0, (byte) 0).getPath();
                if (path != null) {
                    ORPMe.ME.blnAstarMove = true;
                    ORPMe.ME.bytAstarMoveEndX = bytAstarMoveEndX;
                    ORPMe.ME.bytAstarMoveEndY = bytAstarMoveEndY;
                    for (int i = 0; i < path.length; ++i) {
                        ORPMe.ME.pushTask((byte) 1, path[i]);
                    }
                    b = true;
                }
            } else {
                getInstance().addFrightInformation("Không thể tìm đường");
            }
        } else {
            getInstance().addFrightInformation("Không thể tìm đường");
        }
        return b;
    }

    public void doChangeKeyType() {
        ++this.bytShortcutKeysType;
        if (this.bytShortcutKeysType > 1) {
            this.bytShortcutKeysType = 0;
        }
        if (Rms.blnShowKeyInfo) {
            this.shtShowKeyInfoTime = 0;
            this.strKeySelInfo = null;
        }
    }

    private void mapCheckKey(final int n) {
        if (n == 65536) {
            if (this.bytMapState == 1 && this.bytMoveMapPointXY != -1) {
                GameControl.getInstance().CreateState((byte) 8);
                this.setTwoMenu((byte) (-4), IDefines.GAME_UI_AREA_MAP_MENU, (byte) 8);
            } else if (this.bytMapState == 2 && this.bytMoveMapPointXY != -1) {
                this.bytDrawType = 1;
                this.bytMapState = 1;
                if (this.isMapExist((byte) 1, (byte) this.shtMoveMapID)) {
                    this.initMapinfo();
                } else {
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendAreaMapInfo((byte) 5, Map.getInstance().shtMapId, (byte) (this.bytMoveMapPointXY + 1), 0);
                    getInstance().setDialog((byte) 4);
                }
            }
        } else if (n == 131072) {
            if (this.bytMapState == 1) {
                this.cleanSmallMap();
            } else if (this.bytMapState == 2) {
                this.bytMapState = 1;
                this.initMapinfo();
            }
        } else if (this.bytMapState == 1 || this.bytMapState == 2) {
            int n2 = 0;
            int n3 = 0;
            if (this.bytMapState == 1) {
                n2 = Map.getInstance().shtSmallMapMaxWidth;
                n3 = Map.getInstance().shtSmallMapMaxHeight;
            } else if (this.bytMapState == 2) {
                n2 = Map.getInstance().shtWorldMapMaxWidth;
                n3 = Map.getInstance().shtWorldMapMaxHeight;
            }
            Map.getInstance().moveSmallMap(n, this.shtsMapPlaceXY, this.shtMoveMapFlagXY, n2, n3, 10);
            byte bytMoveMapPointXY = (byte) Map.getInstance().getSmallMapCurPlace(this.shtsMapPlaceXY, this.shtMoveMapFlagXY, (byte) 25);
            if (bytMoveMapPointXY != -1) {
                if (bytMoveMapPointXY != this.bytMoveMapPointXY) {
                    this.bytMoveMapPointXY = bytMoveMapPointXY;
                    this.shtMoveMapID = this.shtsMapflag[this.bytMoveMapPointXY];
                    int n4 = this.shtsMapPlaceXY[this.bytMoveMapPointXY][0] - Param.getInstance().SMALL_MAP_CAMERAX;
                    int n5 = this.shtsMapPlaceXY[this.bytMoveMapPointXY][1] - Param.getInstance().SMALL_MAP_CAMERAY;
                    if (GameUI.popupDialogInstance == null) {
                        (GameUI.popupDialogInstance = new PopupDialog()).setShow(true);
                    }
                    GameUI.popupDialogInstance.setPopupDialog(new String[]{this.strsMapPlaceName[this.bytMoveMapPointXY]}, (short) n4, (short) n5);
                }
                this.setButton(0, 4, 2);
            } else {
                if (GameUI.popupDialogInstance != null) {
                    GameUI.popupDialogInstance.setShow(false);
                    GameUI.popupDialogInstance = null;
                }
                this.bytMoveMapPointXY = -1;
                this.shtMoveMapID = -1;
                this.setButton(0, 100, 2);
            }
        }
    }

    public void initMapinfo() {
        if (this.sprSmallMapIcon == null) {
            this.sprSmallMapIcon = ImageManager.loadSpriteById(3, "flag", "flag", "flag", "ingame");
        }
        if (Map.getInstance().imgWorldMapArrow == null) {
            Map.getInstance().imgWorldMapArrow = new Image[4];
            for (int i = 0; i < Map.getInstance().imgWorldMapArrow.length; ++i) {
                Map.getInstance().imgWorldMapArrow[i] = ImageManager.CreateImage(new StringBuffer(String.valueOf(IDefines.GAME_UI_WORLD_MAP_ARROW_AREA_POS[i][5])).toString(), "sys");
            }
        }
        this.bytsMapPlaceTileXY = null;
        if (this.bytMapState == 1) {
            Map.getInstance().imgAreaMap = ImageManager.loadSpriteById(3, "AreaMap/" + Map.getInstance().imgAreaMapAnuId, new StringBuffer(String.valueOf(Map.getInstance().imgAreaMapAnuId)).toString(), new StringBuffer(String.valueOf(Map.getInstance().imgAreaMapPngId)).toString(), "ingame");
            this.shtsMapPlaceXY = Map.getInstance().shtsAreaMapPlaceXY;
            this.shtsMapflag = Map.getInstance().shtsAreaMapflag;
            this.strsMapPlaceName = Map.getInstance().strsAreaMapPlaceName;
            this.strMapName = Map.getInstance().strAreaMapName;
            this.bytMoveMapPointXY = -1;
            for (int j = 0; j < this.shtsMapflag.length; ++j) {
                if (this.shtsMapflag[j] == Map.getInstance().shtMapId) {
                    this.bytMoveMapPointXY = (byte) j;
                    this.bytMoveMapPointXYCurrent = this.bytMoveMapPointXY;
                    this.shtMoveMapID = this.shtsMapflag[j];
                    this.shtMoveMapFlagXY = new short[]{this.shtsMapPlaceXY[this.bytMoveMapPointXY][0], this.shtsMapPlaceXY[this.bytMoveMapPointXY][1]};
                    break;
                }
            }
            if (this.bytMoveMapPointXY == -1) {
                this.bytMoveMapPointXY = 0;
                this.bytMoveMapPointXYCurrent = -1;
                this.shtMoveMapID = this.shtsMapflag[this.bytMoveMapPointXY];
                this.shtMoveMapFlagXY = new short[]{this.shtsMapPlaceXY[this.bytMoveMapPointXY][0], this.shtsMapPlaceXY[this.bytMoveMapPointXY][1]};
            }
            Map.getInstance().setCameraTileXY_SmallMap(this.shtsMapPlaceXY[this.bytMoveMapPointXY][0], this.shtsMapPlaceXY[this.bytMoveMapPointXY][1], Map.getInstance().shtSmallMapMaxWidth, Map.getInstance().shtSmallMapMaxHeight);
            (GameUI.popupDialogInstance = new PopupDialog()).setPopupDialog(new String[]{this.strsMapPlaceName[this.bytMoveMapPointXY]}, (short) (this.shtsMapPlaceXY[this.bytMoveMapPointXY][0] - Param.getInstance().SMALL_MAP_CAMERAX), (short) (this.shtsMapPlaceXY[this.bytMoveMapPointXY][1] - Param.getInstance().SMALL_MAP_CAMERAY));
            GameUI.popupDialogInstance.setShow(true);
            if (this.sprSmallMapIcon != null) {
                this.sprSmallMapIcon.setAnimation(0);
            }
            this.setButton(0, 4, 2);
        } else if (this.bytMapState == 2) {
            Map.getInstance().imgWorldMap = ImageManager.loadSpriteById(3, "WorldMap/" + Map.getInstance().imgWorldMapAnuId, new StringBuffer(String.valueOf(Map.getInstance().imgWorldMapAnuId)).toString(), new StringBuffer(String.valueOf(Map.getInstance().imgWorldMapPngId)).toString(), "ingame");
            this.shtsMapPlaceXY = Map.getInstance().shtsWorldMapPlaceXY;
            this.shtsMapflag = Map.getInstance().shtsWorldMapflag;
            this.strsMapPlaceName = Map.getInstance().strsWorldMapPlaceName;
            this.strMapName = Map.getInstance().strWorldMapName;
            this.bytMoveMapPointXY = -1;
            for (int k = 0; k < this.shtsMapflag.length; ++k) {
                if (this.shtsMapflag[k] == Map.getInstance().bytMapClanType) {
                    this.bytMoveMapPointXY = (byte) k;
                    this.bytMoveMapPointXYCurrent = this.bytMoveMapPointXY;
                    this.shtMoveMapID = this.shtsMapflag[k];
                    this.shtMoveMapFlagXY = new short[]{this.shtsMapPlaceXY[this.bytMoveMapPointXY][0], this.shtsMapPlaceXY[this.bytMoveMapPointXY][1]};
                    break;
                }
            }
            if (this.bytMoveMapPointXY == -1) {
                this.bytMoveMapPointXY = 0;
                this.bytMoveMapPointXYCurrent = -1;
                this.shtMoveMapID = this.shtsMapflag[this.bytMoveMapPointXY];
                this.shtMoveMapFlagXY = new short[]{this.shtsMapPlaceXY[this.bytMoveMapPointXY][0], this.shtsMapPlaceXY[this.bytMoveMapPointXY][1]};
            }
            Map.getInstance().setCameraTileXY_SmallMap(this.shtsMapPlaceXY[this.bytMoveMapPointXY][0], this.shtsMapPlaceXY[this.bytMoveMapPointXY][1], Map.getInstance().shtWorldMapMaxWidth, Map.getInstance().shtWorldMapMaxHeight);
            (GameUI.popupDialogInstance = new PopupDialog()).setPopupDialog(new String[]{this.strsMapPlaceName[this.bytMoveMapPointXY]}, (short) (this.shtsMapPlaceXY[this.bytMoveMapPointXY][0] - Param.getInstance().SMALL_MAP_CAMERAX), (short) (this.shtsMapPlaceXY[this.bytMoveMapPointXY][1] - Param.getInstance().SMALL_MAP_CAMERAY));
            GameUI.popupDialogInstance.setShow(true);
            if (this.sprSmallMapIcon != null) {
                this.sprSmallMapIcon.setAnimation(0);
            }
            this.setButton(0, 27, 2);
        }
    }

    private void updateSmallMap() {
        if (this.sprSmallMapIcon != null) {
            this.sprSmallMapIcon.update();
        }
    }

    private void printSmallMapPath(final Graphics graphics) {
        if (this.bytMapState == 2 || this.bytMapState == 1) {
            for (int i = 0; i < IDefines.GAME_UI_WORLD_MAP_ARROW_AREA_POS.length; ++i) {
                if (Map.getInstance().imgWorldMapArrow != null && Map.getInstance().imgWorldMapArrow.length == IDefines.GAME_UI_WORLD_MAP_ARROW_AREA_POS.length && Map.getInstance().imgWorldMapArrow[i] != null) {
                    DrawBase.DrawImage(Map.getInstance().imgWorldMapArrow[i], IDefines.GAME_UI_WORLD_MAP_ARROW_AREA_POS[i][0], IDefines.GAME_UI_WORLD_MAP_ARROW_AREA_POS[i][1], IDefines.GAME_UI_WORLD_MAP_ARROW_AREA_POS[i][2], IDefines.GAME_UI_WORLD_MAP_ARROW_AREA_POS[i][3], (short) 3);
                }
            }
        }
    }

    private void meMove(final byte b) {
        if (ORPMe.ME.blnCanMove) {
            if (ORPMe.ME.vTask.size() < 1) {
                ORPMe.ME.pushTask((byte) 1, b);
            } else if (ORPMe.ME.blnAstarMove) {
                ORPMe.ME.blnAstarMove = false;
                ORPMe.ME.delTask((byte) 1);
                ORPMe.ME.pushTask((byte) 1, b);
            }
        } else {
            getInstance().addFrightInformation("Không thể di chuyển");
        }
    }

    public void cleanSmallMap() {
        if (DCanvas.getInstance().UMenu != null) {
            GameControl.getInstance().delUIbase(3);
        }
        if (Map.getInstance().imgAreaMap != null) {
            Map.getInstance().imgAreaMap = null;
        }
        if (Map.getInstance().imgWorldMap != null) {
            Map.getInstance().imgWorldMap = null;
        }
        if (Map.getInstance().imgWorldMapArrow != null) {
            for (int i = 0; i < Map.getInstance().imgWorldMapArrow.length; ++i) {
                Map.getInstance().imgWorldMapArrow[i] = null;
            }
            Map.getInstance().imgWorldMapArrow = null;
        }
        if (GameUI.popupDialogInstance != null) {
            GameUI.popupDialogInstance.setShow(false);
            GameUI.popupDialogInstance = null;
        }
        this.bytDrawType = 0;
        this.bytMapState = 0;
        this.shtsMapPlaceXY = null;
        this.strsMapPlaceName = null;
        this.shtsMapflag = null;
        this.bytsMapPlaceTileXY = null;
        this.setButton();
    }

    public boolean isMapExist(final byte b, final byte b2) {
        boolean b3 = false;
        if (b == 1) {
            if (b2 == Map.getInstance().bytMapClanType && Map.getInstance().blnAreaMapExist && Map.getInstance().bytCurAreaMapClanType != 0 && b2 == Map.getInstance().bytCurAreaMapClanType) {
                b3 = true;
            }
        } else if (b == 2 && Map.getInstance().blnWorldMapExist) {
            b3 = true;
        }
        return b3;
    }

    public void addObjectImg(final short n, final String s) {
        if (Param.getInstance().hImgObject == null) {
            Param.getInstance().hImgObject = new Hashtable();
        }
        if (!Param.getInstance().hImgObject.containsKey(new Integer(n))) {
            Image createImage = ImageManager.CreateImage(new StringBuffer(String.valueOf(n)).toString(), s);
            if (createImage != null) {
                Param.getInstance().hImgObject.put(new Integer(n), createImage);
            }
        }
    }

    public Image getObjectImg(final int n) {
        Image defaultImg = null;
        if (Param.getInstance().hImgObject != null && Param.getInstance().hImgObject.containsKey(new Integer(n))) {
            defaultImg = (Image) Param.getInstance().hImgObject.get(new Integer(n));
        }
        if (defaultImg == null) {
            defaultImg = Param.getInstance().defaultImg;
        }
        return defaultImg;
    }

    public void touchMeMove() {
        if ((ORPMe.ME.blnTaskOK || ORPMe.ME.bytState == 1) && !ORPMe.ME.blnStall) {
            byte[] array = DCanvas.getInstance().getPointerMap(true);
            if (array != null) {
                int find_Role = 0;
                int n = (int) Common.pow(7, 2);
                if (this.checkPosInView(array[0], array[1], false)) {
                    find_Role = (ORPMe.ME.find_Role(array[0], array[1], true, false, (byte) 0) ? 1 : 0);
                    if (ORPMe.ME.bytSelectPos != null && find_Role != 0) {
                        array = ORPMe.ME.bytSelectPos;
                        find_Role = 0;
                        Macro.blnDistanceFar = true;
                    }
                }
                if (find_Role == 0 && ORPMe.ME.blnCanMove) {
                    if (array != null) {
                        if (this.setAStar(array[0], array[1])) {
                            if (Rms.blnAutoWalk && ORPMe.ME.blnKeepDirMove) {
                                ORPMe.ME.blnKeepDirMove = false;
                                ORPMe.ME.intKeepMoveKeyCode = -1;
                            }
                            this.bytDrawMoveTime = 0;
                            this.bytDrawMoveX = array[0];
                            this.bytDrawMoveY = array[1];
                            ++GameUI.lngMovePointCount;
                            this.MoseSpriteLog();
                            if (Macro.blnDistanceFar) {
                                Macro.blnDistanceFarAStar = true;
                            }
                        }
                        ORPMe.ME.bytSelectPos = null;
                    }
                    Macro.blnDistanceFar = false;
                }
            }
        }
    }

    private void MoseSpriteLog() {
        int curPressedX = DCanvas.getInstance().CurPressedX;
        int curPressedY = DCanvas.getInstance().CurPressedY;
        int n = ORPMe.ME.shtJudgeX - Param.getInstance().CAMERAX;
        int n2 = ORPMe.ME.shtJudgeY - Param.getInstance().CAMERAY - 16 + 15;
        if (curPressedX == n) {
            this.k = 999999.0f;
        } else {
            this.k = (curPressedY - n2) / (float) (curPressedX - n);
        }
        if (this.k >= -1.0f && this.k <= 1.0f) {
            if (curPressedX < n) {
                if (GameUI.lngMovePointCount % 2L == 0L) {
                    this.mouseindex = 5;
                } else {
                    this.mouseindex = 4;
                }
            } else if (curPressedX >= n) {
                if (GameUI.lngMovePointCount % 2L == 0L) {
                    this.mouseindex = 7;
                } else {
                    this.mouseindex = 6;
                }
            }
        } else if (curPressedY > n2) {
            if (GameUI.lngMovePointCount % 2L == 0L) {
                this.mouseindex = 3;
            } else {
                this.mouseindex = 2;
            }
        } else if (curPressedY <= n2) {
            if (GameUI.lngMovePointCount % 2L == 0L) {
                this.mouseindex = 1;
            } else {
                this.mouseindex = 0;
            }
        }
    }

    public void pointerLogic() {
        switch (this.bytDrawType) {
            case 0: {
                if ((Param.getInstance().intSelectId > 0 || ORPMe.ME.blnStall) && DCanvas.getInstance().IsPointerDown(this.RightUpX - 16, this.RightUpY - 16, 32, 32)) {
                    if (getInstance().bytsButtonType[2] == 2) {
                        ORPMe.ME.doRightKey();
                        break;
                    }
                } else {
                    if ((Param.getInstance().intSelectId > 0 || ORPMe.ME.blnStall) && DCanvas.getInstance().IsPointerDown(this.LeftUpX - 16, this.LeftUpY - 16, 32, 32)) {
                        ORPMe.ME.doLeftKey();
                        break;
                    }
                    if (DCanvas.getInstance().IsPointerDown(this.LeftDownX - 16, this.LeftDownY - 16, 32, 32)) {
                        DCanvas.getInstance().buttonRightFlash = 1;
                        DCanvas.getInstance().buttonRightFlash = 0;
                        ORPMe.ME.doRightKey();
                        break;
                    }
                    if (DCanvas.getInstance().IsPointerDown(this.RightCentreX - 16, this.RightDownY - 16, 32, 32)) {
                        this.blnUpdateManuSelectAll = true;
                        break;
                    }
                    if (DCanvas.getInstance().IsPointerDown(this.RightDownX - 16, this.RightDownY - 16, 32, 32)) {
                        GameControl.getInstance().CreateState((byte) 3);
                        this.blnChatFace = true;
                        MenuUI.getInstance().setState((byte) 42, "Lịch sử trò chuyện");
                        MenuUI.getInstance().blnSetChatNote = true;
                        MenuUI.getInstance().setChatNote();
                        break;
                    }
                    if (DCanvas.getInstance().IsPointerDown(this.LeftCentreX - 16, this.LeftDownY - 16, 32, 32)) {
                        this.HideOFF = !this.HideOFF;
                        break;
                    }
                    if (!this.HideOFF && DCanvas.getInstance().IsPointerDown(this.shtDownHotKeyXOff - 11 + 176, Macro.SCREEN_HEIGHT - Macro.expRow_h - 27, 26, 27)) {
                        this.doChangeKeyType();
                        break;
                    }
                    if (!this.HideOFF && DCanvas.getInstance().IsPointerDown(this.shtDownHotKeyXOff - 10, Macro.SCREEN_HEIGHT - Macro.expRow_h - 23, 8 * (this.bytHotKeyBoxWidth + this.bytHotKeyBoxDistance) + 22, Macro.bytb3height)) {
                        int curPressedX = DCanvas.getInstance().CurPressedX;
                        if (DCanvas.getInstance().CurPressedX > this.shtDownHotKeyXOff - 11 + 176 + 26) {
                            curPressedX -= 26;
                        }
                        byte b = (byte) ((curPressedX - this.shtDownHotKeyXOff + 11) / 22);
                        this.doKey(DCanvas.getInstance().bytDrawKeyIndex[b]);
                        this.doMoveKey(DCanvas.getInstance().bytDrawKeyIndex[b]);
                        break;
                    }
                    if (DCanvas.getInstance().CurPressedY > Macro.SCREEN_GAME_HEIGHT) {
                        break;
                    }
                    byte pointerCheckBuff = this.pointerCheckBuff();
                    if (pointerCheckBuff == -1) {
                        this.touchMeMove();
                        break;
                    }
                    if (this.checkDialogPRI((byte) 3, (byte) 1)) {
                        DialogUI.getInstance().setDialogBuff(pointerCheckBuff);
                        break;
                    }
                }
                break;
            }
            case 1: {
                if (DCanvas.getInstance().PointerDwonSoftKey_Left()) {
                    this.mapCheckKey(65536);
                    break;
                }
                if (DCanvas.getInstance().PointerDwonSoftKey_Right()) {
                    this.mapCheckKey(131072);
                    break;
                }
                if (DCanvas.getInstance().IsPointerDown(this.shtHotKeyXOff, Macro.SCREEN_HEIGHT - this.bytbHotKeyExepHeight + this.bytHotKeyBoxContentHeightYOff, 9 * (this.bytHotKeyBoxWidth + this.bytHotKeyBoxDistance), 22)) {
                    byte b2 = (byte) ((DCanvas.getInstance().CurPressedX - this.shtHotKeyXOff) / (this.bytHotKeyBoxWidth + this.bytHotKeyBoxDistance));
                    if (Param.getInstance().intShortcutKeys[DCanvas.getInstance().bytDrawKeyIndex[b2] + 13 * this.bytShortcutKeysType][4] == 1 && Param.getInstance().intShortcutKeys[DCanvas.getInstance().bytDrawKeyIndex[b2] + 13 * this.bytShortcutKeysType][0] == 8) {
                        this.mapCheckKey(65536);
                        break;
                    }
                } else {
                    if (this.shtsMapPlaceXY != null && DCanvas.getInstance().IsPointerDown(0, 0, Macro.SCREEN_WIDTH, Macro.SCREEN_HEIGHT)) {
                        this.shtMoveMapFlagXY[0] = (short) (DCanvas.getInstance().CurPressedX + Param.getInstance().SMALL_MAP_CAMERAX);
                        this.shtMoveMapFlagXY[1] = (short) (DCanvas.getInstance().CurPressedY + Param.getInstance().SMALL_MAP_CAMERAY);
                        byte bytMoveMapPointXY = (byte) Map.getInstance().getSmallMapCurPlace(this.shtsMapPlaceXY, this.shtMoveMapFlagXY, (byte) 10);
                        int n = 0;
                        int n2 = 0;
                        if (this.bytMapState == 1) {
                            n = Map.getInstance().shtSmallMapMaxWidth;
                            n2 = Map.getInstance().shtSmallMapMaxHeight;
                        } else if (this.bytMapState == 2) {
                            n = Map.getInstance().shtWorldMapMaxWidth;
                            n2 = Map.getInstance().shtWorldMapMaxHeight;
                        }
                        Map.getInstance().setCameraTileXY_SmallMap(this.shtMoveMapFlagXY[0], this.shtMoveMapFlagXY[1], n, n2);
                        if (bytMoveMapPointXY != -1) {
                            if (bytMoveMapPointXY != this.bytMoveMapPointXY) {
                                this.bytMoveMapPointXY = bytMoveMapPointXY;
                                this.shtMoveMapID = this.shtsMapflag[this.bytMoveMapPointXY];
                                int n3 = this.shtsMapPlaceXY[this.bytMoveMapPointXY][0] - Param.getInstance().SMALL_MAP_CAMERAX;
                                int n4 = this.shtsMapPlaceXY[this.bytMoveMapPointXY][1] - Param.getInstance().SMALL_MAP_CAMERAY;
                                if (GameUI.popupDialogInstance == null) {
                                    (GameUI.popupDialogInstance = new PopupDialog()).setShow(true);
                                }
                                GameUI.popupDialogInstance.setPopupDialog(new String[]{this.strsMapPlaceName[this.bytMoveMapPointXY]}, (short) n3, (short) n4);
                            } else {
                                this.mapCheckKey(65536);
                            }
                            this.setButton(0, 4, 2);
                        } else {
                            GameUI.popupDialogInstance = null;
                            this.bytMoveMapPointXY = -1;
                            this.shtMoveMapID = -1;
                            this.setButton(0, 100, 2);
                        }
                    }
                    if (this.bytMapState == 2 || this.bytMapState == 1) {
                        for (int i = 0; i < IDefines.GAME_UI_WORLD_MAP_ARROW_AREA_POS.length; ++i) {
                            if (DCanvas.getInstance().IsPointerDown(IDefines.GAME_UI_WORLD_MAP_ARROW_AREA_POS[i][0], IDefines.GAME_UI_WORLD_MAP_ARROW_AREA_POS[i][1], IDefines.GAME_UI_WORLD_MAP_ARROW_AREA_POS[i][2], IDefines.GAME_UI_WORLD_MAP_ARROW_AREA_POS[i][3])) {
                                this.mapCheckKey(IDefines.GAME_UI_WORLD_MAP_ARROW_AREA_POS[i][4]);
                            }
                        }
                        break;
                    }
                }
                break;
            }
        }
    }

    private void HideShortcut() {
        if (this.HideOFF) {
            this.HideShurtCut += 8;
            if (this.HideShurtCut >= this.bytHotKeyBoxWidth + Macro.expRow_h + 5) {
                this.HideShurtCut = this.bytHotKeyBoxWidth + Macro.expRow_h + 5;
            }
        } else {
            this.HideShurtCut -= 8;
            if (this.HideShurtCut <= 0) {
                this.HideShurtCut = 0;
            }
        }
    }

    private void drawchatFrame(final Graphics graphics) {
        if (this.isShowMsg && this.bytMsgShowType < 2) {
            ChatContentFactory.getInstance().drawChat(graphics);
            this.bytMaxChat = ChatContentFactory.getInstance().bytChatLineNum;
        } else if (this.isShowMsg && this.bytMsgShowType == 2) {
            if (this.vecFightInfo != null) {
                this.drawFightInfo(graphics);
            }
        } else {
            this.bytMaxChat = 0;
        }
    }

    public void addGetExpGold(final int n, final int n2) {
        if (this.intsGetExpGold == null) {
            this.intsGetExpGold = new int[1][3];
            this.intsGetExpGold[0][2] = 1;
        } else {
            int[][] intsGetExpGold = this.intsGetExpGold;
            System.arraycopy(intsGetExpGold, 0, this.intsGetExpGold = new int[intsGetExpGold.length + 1][3], 0, intsGetExpGold.length);
        }
        this.intsGetExpGold[this.intsGetExpGold.length - 1][0] = n;
        this.intsGetExpGold[this.intsGetExpGold.length - 1][1] = n2;
    }

    private void drawExpGold(final Graphics graphics) {
        int n = ORPMe.ME.shtJudgeX - Param.getInstance().CAMERAX + 30;
        int n2 = ORPMe.ME.shtJudgeY - Param.getInstance().CAMERAY;
        int n3 = 20;
        if (ORPMe.ME.bytTileX > Map.getInstance().bytColumn * 3 / 4) {
            n = ORPMe.ME.shtJudgeX - Param.getInstance().CAMERAX - 10;
            n3 = 24;
        }
        for (int i = 0; i < this.intsGetExpGold.length; ++i) {
            if (this.intsGetExpGold[i][2] > 0) {
                if (this.intsGetExpGold[i][1] == 0) {
                    StringManager.drawShadowWord(graphics, "Kinh nghiệm" + this.intsGetExpGold[i][0], n, n2 - this.intsGetExpGold[i][2], 16773120, 15257595, n3);
                } else {
                    StringManager.drawShadowWord(graphics, "Sò" + this.intsGetExpGold[i][0], n, n2 - this.intsGetExpGold[i][2], 16747008, 15592917, n3);
                }
            }
        }
    }

    public void addFrightInformation(final String s) {
        if (this.vInformation.isEmpty()) {
            this.vInformation.addElement(new FightValue((byte) 5, (byte) 13, s, this.fightValueOffsetX, this.fightValueOffsetY));
            this.showFightInformation = true;
        }
    }

    private void drawFrightInformation(final Graphics graphics) {
        int n = 0;
        int n2 = 0;
        int n3 = ORPMe.ME.shtJudgeY - Param.getInstance().CAMERAY + 12;
        byte transdirection = this.transdirection(ORPMe.ME.bytDirection);
        if (transdirection == this.DIR_RIGHT) {
            n = ORPMe.ME.shtJudgeX - Param.getInstance().CAMERAX + 20;
            n2 = 20;
        } else if (transdirection == this.DIR_LEFT) {
            n = ORPMe.ME.shtJudgeX - Param.getInstance().CAMERAX - 20;
            n2 = 24;
        }
        for (int i = 0; i < this.vInformation.size(); ++i) {
            FightValue fightValue = (FightValue) this.vInformation.elementAt(i);
            fightValue.paint(graphics, n - fightValue.startX, n3, n2);
        }
    }

    private byte transdirection(final byte b) {
        byte b2 = this.DIR_RIGHT;
        if (b == 3) {
            b2 = this.DIR_RIGHT;
        } else if (b == 4) {
            b2 = this.DIR_LEFT;
        }
        return b2;
    }

    private void updateFringhInformation() {
        if (this.vInformation != null) {
            int i = 0;
            while (i < this.vInformation.size()) {
                if (((FightValue) this.vInformation.elementAt(i)).update()) {
                    ++i;
                } else {
                    this.vInformation.removeElementAt(i);
                    this.showFightInformation = false;
                }
            }
        }
    }

    public void pushFightInfo(final String character, final byte b) {
        int n = 0;
        switch (b) {
            case 1: {
                n = 16711680;
                break;
            }
            case 2: {
                n = 5642007;
                break;
            }
            case 3: {
                n = 91905;
                break;
            }
            case 4: {
                n = 255;
                break;
            }
        }
        if (this.bytMsgShowType == 2) {
            this.showChat();
        }
        String[] wenZi = StringManager.wenZi(character, Macro.SCREEN_WIDTH);
        if (this.vecFightInfo == null) {
            this.vecFightInfo = new Vector(1, 1);
        }
        for (int i = 0; i < wenZi.length; i = (byte) (i + 1)) {
            ChatContent chatContent = new ChatContent();
            chatContent.fontColor = n;
            chatContent.character = wenZi[i];
            this.vecFightInfo.addElement(chatContent);
        }
        for (byte b2 = (byte) (this.vecFightInfo.size() - 10), b3 = 0; b3 < b2; ++b3) {
            this.vecFightInfo.removeElementAt(b3);
        }
        if (Param.getInstance().vChatFightNote == null) {
            Param.getInstance().vChatFightNote = new Vector(1, 1);
        }
        ChatContent chatContent2 = new ChatContent();
        chatContent2.fontColor = n;
        chatContent2.character = character;
        Param.getInstance().vChatFightNote.addElement(chatContent2);
        if (Param.getInstance().vChatFightNote.size() > Macro.bytMaxChatNote) {
            Param.getInstance().vChatFightNote.removeElementAt(0);
        }
        if (DCanvas.getInstance().UMenu != null) {
            DCanvas.getInstance().setNetLoad(false);
            if (MenuUI.getInstance().getState() == 42) {
                MenuUI.getInstance().blnSetChatNote = false;
                MenuUI.getInstance().setChatNote();
                MenuUI.getInstance().setChatIndex((byte) 20);
            }
        }
    }

    public void pushNpcMenu(final MenuObject menuObject) {
        try {
            Param.getInstance().blnIsOk = false;
            if (menuObject.intOptionId != 200000 && menuObject.intOptionId != 700000) {
                this.intSendMenuId = 0;
            }
            if (Param.getInstance().vMenuMemory == null) {
                Param.getInstance().vMenuMemory = new Vector(1, 1);
            } else {
                byte bytType = ((MenuObject) Param.getInstance().vMenuMemory.elementAt(Param.getInstance().vMenuMemory.size() - 1)).bytType;
                if (bytType == 12 || bytType == 13 || bytType == 16 || bytType == 17 || bytType == 15 || bytType == -12 || bytType == 14 || bytType == 22) {
                    Param.getInstance().vMenuMemory.removeElementAt(Param.getInstance().vMenuMemory.size() - 1);
                }
            }
            Param.getInstance().vMenuMemory.addElement(menuObject);
            MenuUI.getInstance().clearMove();
            switch (menuObject.bytType) {
                case 11:
                case 14:
                case 22: {
                    if (MenuUI.getInstance().blnNpcMailSendGood) {
                        MenuUI.getInstance().setNpcOption();
                        NetSend instance = NetSend.getInstance();
                        MenuUI.getInstance().getClass();
                        instance.sendNpcMoveOption((short) 0, (short) 0, 0);
                        MenuUI.getInstance().setState((byte) 31, "Tiền thư");
                    } else {
                        MenuUI.getInstance().setState((byte) 1, Param.getInstance().strTempTitlestr);
                        MenuUI.getInstance().setNpcOption();
                    }
                    MenuUI.getInstance().clearMove();
                    break;
                }
                case 10: {
                    MenuUI.getInstance().setNpcOption();
                    break;
                }
                case 16: {
                    MenuUI.getInstance().setState((byte) (-47), MenuUI.getInstance().strOneTitlestr);
                    MenuUI.getInstance().setParcelPostButton(MenuUI.getInstance().getOneMove());
                    break;
                }
                case -12: {
                    MenuUI.getInstance().setState((byte) (-113), MenuUI.getInstance().strOneTitlestr);
                    MenuUI.getInstance().setMakeList((byte) (-113));
                    break;
                }
                case 12: {
                    MenuUI.getInstance().setState((byte) (-46), MenuUI.getInstance().strOneTitlestr);
                    MenuUI.getInstance().setStudySkill();
                    break;
                }
                case 13: {
                    if (MenuUI.getInstance().blnNpcMailSendGood) {
                        MenuUI.getInstance().setState((byte) 31, "Tiền thư");
                        break;
                    }
                    if (menuObject.bytStep == 10 || menuObject.bytStep == 21 || menuObject.bytStep == 22 || menuObject.bytStep == 23 || menuObject.bytStep == 24 || menuObject.bytStep == 25) {
                        MenuUI.getInstance().setState((byte) (-43), Param.getInstance().strTempTitlestr);
                        break;
                    }
                    MenuUI.getInstance().setState((byte) (-39), Param.getInstance().strTempTitlestr);
                    break;
                }
                case 15: {
                    MenuUI.getInstance().setState((byte) (-127), MenuUI.getInstance().strOneTitlestr);
                    MenuUI.getInstance().setTaskInfo((short) 0);
                    MenuUI.getInstance().bytTwoMenuState = 0;
                    break;
                }
                case 0: {
                    break;
                }
                case 17: {
                    MenuUI.getInstance().setState((byte) (-44), Param.getInstance().strTempTitlestr);
                    Param.getInstance().bytNpcDataType = 3;
                    break;
                }
                case 23: {
                    Param.getInstance().strNpcInputTitle = menuObject.strInfo;
                    Param.getInstance().bytArrayNpcInputMax = menuObject.intMaxArray;
                    Param.getInstance().strArrayNpcInputName = menuObject.strInfoArray;
                    FormDes.getInstance().showForm((byte) 21);
                    break;
                }
                default: {
                    NetSend.getInstance().sendNpcMoveOption((short) 0, (short) 0, 0);
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Param.getInstance().blnIsOk = true;
    }

    public ONpc getNpc(final int n) {
        synchronized (Param.getInstance().htRoleNPC) {
            // monitorexit(Param.getInstance().htRoleNPC)
            return (ONpc) Param.getInstance().htRoleNPC.get(new Integer(n));
        }
    }

    public void setRandomNpcChat() {
        Random random = new Random();
        if (Math.abs(random.nextInt()) % 100 < this.bytHaveNpcChat * 10) {
            byte b = (byte) (Math.abs(random.nextInt()) % this.bytHaveNpcChat);
            byte b2 = 0;
            if (Param.getInstance().htRoleNPC != null && !Param.getInstance().htRoleNPC.isEmpty()) {
                Enumeration elements = (Enumeration) Param.getInstance().htRoleNPC.elements();
                while (elements.hasMoreElements()) {
                    ONpc oNpc = (ONpc) elements.nextElement();
                    if (oNpc.strChat != null && oNpc.bytChatTime == 0) {
                        if (b2 >= b) {
                            oNpc.bytChatTime = 1;
                            this.intNpcChatId = oNpc.intUserId;
                            return;
                        }
                        ++b2;
                    }
                }
            }
        }
    }

    public void setRandomMonsterChat() {
        int n = Math.abs(new Random().nextInt()) % Param.getInstance().htRoleMonster.size();
        int n2 = 0;
        Enumeration elements = (Enumeration) Param.getInstance().htRoleMonster.elements();
        while (elements.hasMoreElements()) {
            if (n == n2) {
                ORMonster orMonster = (ORMonster) elements.nextElement();
                if (orMonster != null && orMonster.strChat != null && orMonster.bytChatTime == 0) {
                    orMonster.bytChatTime = 1;
                    this.intMonsterChatId = orMonster.intUserId;
                }
                return;
            }
            elements.nextElement();
            ++n2;
        }
    }

    public void delSkillList(final int[] array) {
        int i = 0;
        Label_0130:
        while (i < array.length) {
            while (true) {
                for (int j = 0; j < Param.getInstance().vSkillList.size(); ++j) {
                    if (array[i] == ((SkillObject) Param.getInstance().vSkillList.elementAt(j)).intId) {
                        Param.getInstance().vSkillList.removeElementAt(j);
                        ++i;
                        continue Label_0130;
                    }
                }
                for (int k = 0; k < Param.getInstance().vSkillPassivityList.size(); ++k) {
                    if (array[i] == ((SkillObject) Param.getInstance().vSkillPassivityList.elementAt(k)).intId) {
                        Param.getInstance().vSkillPassivityList.removeElementAt(k);
                        break;
                    }
                }
                continue;
            }
        }
        this.updateSkillShortcutKeys();
    }

    public boolean checkNpcMenuUi(final int n, final int n2) {
        return Param.getInstance().oSelectNpc != null && Param.getInstance().intSelectId == n && this.intSendMenuId == n2;
    }

    public boolean checkMeMenuUi(final int n) {
        return MenuUI.menu != null && MenuUI.getInstance().getState() == n;
    }

    public boolean checkMeSystemMenuUi(final int n) {
        return DCanvas.getInstance().USystemMenu != null && SystemMenuUI.getInstance().bytState == n;
    }

    public void setSkillKey(final int n, final byte bytKey) {
        SkillObject skillObject = (SkillObject) Param.getInstance().vSkillList.elementAt(n);
        this.setSkillShortcutKeys(bytKey, skillObject.intId, skillObject.shtIcon, skillObject.strName, n, skillObject.bytKey);
        skillObject.bytKey = bytKey;
        NetSend.getInstance().sendShortcutKeys((byte) 2, skillObject.intId, bytKey);
    }

    public void setGoodsKey(final Integer n, final byte bytKey) {
        PackageObject packageObject = (PackageObject) Param.getInstance().hPackage.get(n);
        if (packageObject.bytKey == bytKey) {
            return;
        }
        this.setGoodsShortcutKeys(bytKey, packageObject.intId, 0, 0, 0, packageObject.shtIcon, packageObject.strName, packageObject.bytKey);
        packageObject.bytKey = bytKey;
        NetSend.getInstance().sendShortcutKeys((byte) 3, packageObject.intId, bytKey);
    }

    public void setSystemKey(final byte b, final byte b2) {
        this.setSystemShortcutKeys(b, b2);
        NetSend.getInstance().sendShortcutKeys((byte) 1, b2, b);
    }

    public void addStateIcon(final short n) {
        if (this.htImgBuffIcon == null) {
            this.htImgBuffIcon = new Hashtable(1);
        }
        if (!getInstance().htImgBuffIcon.containsKey(new Short(n))) {
            Image createImage = ImageManager.CreateImage(new StringBuffer(String.valueOf(n)).toString(), "states");
            if (createImage != null) {
                getInstance().htImgBuffIcon.put(new Short(n), createImage);
            }
        }
    }

    public void removeStateIcon(final short n) {
        if (this.htImgBuffIcon == null) {
            this.htImgBuffIcon = new Hashtable(1);
        }
        if (this.htImgBuffIcon != null && getInstance().htImgBuffIcon.containsKey(new Short(n))) {
            ImageManager.CreateImage(new StringBuffer(String.valueOf(n)).toString(), "states");
            getInstance().htImgBuffIcon.remove(new Short(n));
        }
    }

    public Image getStateIcon(final short n) {
        Image image = null;
        if (getInstance().htImgBuffIcon.containsKey(new Short(n))) {
            image = (Image) getInstance().htImgBuffIcon.get(new Short(n));
        }
        return image;
    }

    public void pushRoleStateIcon(final short n) {
        short n2 = IDefines.ROLE_STATE_ICON[n];
        if (this.shtStateIcon == null) {
            this.shtStateIcon = new short[]{n2};
            this.addStateIcon(n2);
        } else {
            for (int i = 0; i < this.shtStateIcon.length; ++i) {
                if (this.shtStateIcon[i] == n2) {
                    return;
                }
            }
            if (n == 5) {
                short[] shtStateIcon = this.shtStateIcon;
                System.arraycopy(shtStateIcon, 0, this.shtStateIcon = new short[shtStateIcon.length + 1], 1, shtStateIcon.length);
                this.shtStateIcon[0] = n2;
            } else {
                short[] shtStateIcon2 = this.shtStateIcon;
                System.arraycopy(shtStateIcon2, 0, this.shtStateIcon = new short[shtStateIcon2.length + 1], 0, shtStateIcon2.length);
                this.shtStateIcon[this.shtStateIcon.length - 1] = n2;
            }
            this.addStateIcon(n2);
        }
    }

    public void delRoleStateIcon(final short n) {
        short n2 = IDefines.ROLE_STATE_ICON[n];
        if (this.shtStateIcon == null) {
            return;
        }
        if (this.shtStateIcon.length != 1 || this.shtStateIcon[0] != n2) {
            for (int i = 0; i < this.shtStateIcon.length; ++i) {
                if (this.shtStateIcon[i] == n2) {
                    short[] shtStateIcon = this.shtStateIcon;
                    this.shtStateIcon = new short[this.shtStateIcon.length - 1];
                    if (i != 0) {
                        System.arraycopy(shtStateIcon, 0, this.shtStateIcon, 0, i);
                    }
                    if (i != shtStateIcon.length - 1) {
                        System.arraycopy(shtStateIcon, i + 1, this.shtStateIcon, i, shtStateIcon.length - i - 1);
                    }
                    this.removeStateIcon(n2);
                    return;
                }
            }
            return;
        }
        this.shtStateIcon = null;
    }

    public void GoodsCD(final int n) {
        int i = 0;
        while (i < 26) {
            if (Param.getInstance().intShortcutKeys[i][4] == 3 && Param.getInstance().intShortcutKeys[i][0] == n) {
                Param.getInstance().intShortcutKeys[i][1] = Param.getInstance().intShortcutKeys[i][2];
                if (Param.getInstance().intShortcutKeys[i][3] != 0) {
                    for (int j = 0; j < 26; j = (byte) (j + 1)) {
                        if (Param.getInstance().intShortcutKeys[j][4] == 3 && Param.getInstance().intShortcutKeys[j][3] == Param.getInstance().intShortcutKeys[i][3] && Param.getInstance().intShortcutKeys[j][0] == n) {
                            Param.getInstance().intShortcutKeys[j][1] = Param.getInstance().intShortcutKeys[j][2];
                        }
                    }
                    break;
                }
                break;
            } else {
                i = (byte) (i + 1);
            }
        }
        ORPMe.ME.intGoodsCDTime = 1500;
    }

    public void DelGoodsCD(final int n) {
        int i = 0;
        while (i < 26) {
            if (Param.getInstance().intShortcutKeys[i][4] == 3 && Param.getInstance().intShortcutKeys[i][0] == n) {
                Param.getInstance().intShortcutKeys[i][1] = 0;
                if (Param.getInstance().intShortcutKeys[i][3] != 0) {
                    for (int j = 0; j < 26; j = (byte) (j + 1)) {
                        if (Param.getInstance().intShortcutKeys[j][4] == 3 && Param.getInstance().intShortcutKeys[j][3] == Param.getInstance().intShortcutKeys[i][3]) {
                            Param.getInstance().intShortcutKeys[j][1] = 0;
                        }
                    }
                    break;
                }
                break;
            } else {
                i = (byte) (i + 1);
            }
        }
    }

    public void SkillCD(final int n) {
        short shtCDType = 0;
        for (int i = 0; i < Param.getInstance().vSkillList.size(); ++i) {
            SkillObject skillObject = (SkillObject) Param.getInstance().vSkillList.elementAt(i);
            if (skillObject.intId == n) {
                shtCDType = skillObject.shtCDType;
                skillObject.intCDTime = skillObject.intCDTimeMax;
                break;
            }
        }
        if (shtCDType != 0) {
            for (int j = 0; j < Param.getInstance().vSkillList.size(); ++j) {
                SkillObject skillObject2 = (SkillObject) Param.getInstance().vSkillList.elementAt(j);
                if (skillObject2.shtCDType == shtCDType) {
                    skillObject2.intCDTime = skillObject2.intCDTimeMax;
                }
            }
        }
    }

    public void DelSkillCD(final int n) {
        short shtCDType = 0;
        for (int i = 0; i < Param.getInstance().vSkillList.size(); ++i) {
            SkillObject skillObject = (SkillObject) Param.getInstance().vSkillList.elementAt(i);
            if (skillObject.intId == n) {
                shtCDType = skillObject.shtCDType;
                skillObject.intCDTime = 0;
                break;
            }
        }
        if (shtCDType != 0) {
            for (int j = 0; j < Param.getInstance().vSkillList.size(); ++j) {
                SkillObject skillObject2 = (SkillObject) Param.getInstance().vSkillList.elementAt(j);
                if (skillObject2.shtCDType == shtCDType) {
                    skillObject2.intCDTime = 0;
                }
            }
        }
    }

    public void pushTeam(final TeamObject teamObject) {
        if (Param.getInstance().vTeam == null) {
            return;
        }
        int n = 0;
        for (int n2 = 0; n2 < Param.getInstance().vTeam.size() && ((TeamObject) Param.getInstance().vTeam.elementAt(n2)).bytTeamId <= teamObject.bytTeamId; n2 = (byte) (n2 + 1)) {
            n = (byte) (n + 1);
        }
        Param.getInstance().vTeam.insertElementAt(teamObject, n);
    }

    public void setTeamNameColor(final String s, final boolean blnTeam) {
        Enumeration elements = Param.getInstance().htRolePlayer.elements();
        while (elements.hasMoreElements()) {
            ORPlayer orPlayer = (ORPlayer) elements.nextElement();
            if (s.equals(orPlayer.strNickName)) {
                orPlayer.blnTeam = blnTeam;
                break;
            }
        }
    }

    public void addTeamer() {
        int n = 0;
        byte[] array = new byte[4];
        if (Param.getInstance().vTeam != null) {
            for (byte b = 0; b < Param.getInstance().vTeam.size(); ++b) {
                String strName = ((TeamObject) Param.getInstance().vTeam.elementAt(b)).strName;
                byte bytTeamId = ((TeamObject) Param.getInstance().vTeam.elementAt(b)).bytTeamId;
                if (!strName.equals(ORPMe.ME.strNickName) && bytTeamId == ORPMe.ME.bytMeTeamID) {
                    array[n] = b;
                    n = (byte) (n + 1);
                }
            }
        }
        if (n != -1) {
            Param.getInstance().bytsMyTeam = new byte[n];
            for (int i = 0; i < n; i = (byte) (i + 1)) {
                Param.getInstance().bytsMyTeam[i] = array[i];
            }
            for (int j = 0; j < Param.getInstance().bytsMyTeam.length; j = (byte) (j + 1)) {
                TeamObject teamObject = (TeamObject) Param.getInstance().vTeam.elementAt(Param.getInstance().bytsMyTeam[j]);
                Enumeration elements = Param.getInstance().htRolePlayer.elements();
                while (elements.hasMoreElements()) {
                    ORPlayer orPlayer = (ORPlayer) elements.nextElement();
                    if (orPlayer.strNickName.equals(teamObject.strName)) {
                        if (orPlayer.intHP == 0) {
                            teamObject.blnIsDeath = true;
                        } else {
                            teamObject.blnIsDeath = false;
                        }
                        teamObject.intNowBlood = orPlayer.intHP;
                        teamObject.intMaxBlood = orPlayer.intHPMax;
                        teamObject.intNowMagic = orPlayer.intMP;
                        teamObject.intMaxMagic = orPlayer.intMPMax;
                    }
                }
            }
        } else {
            Param.getInstance().bytsMyTeam = null;
        }
    }

    public void updateTeamHP(final ORPlayer orPlayer, final int intNowBlood, final int intMaxBlood) {
        if (orPlayer != null && Param.getInstance().bytsMyTeam != null) {
            for (int i = 0; i < Param.getInstance().bytsMyTeam.length; i = (byte) (i + 1)) {
                TeamObject teamObject = (TeamObject) Param.getInstance().vTeam.elementAt(Param.getInstance().bytsMyTeam[i]);
                if (teamObject.strName.equals(orPlayer.strNickName) && teamObject.bytTeamId == ORPMe.ME.bytMeTeamID) {
                    if (intNowBlood == 0) {
                        teamObject.blnIsDeath = true;
                    } else {
                        teamObject.blnIsDeath = false;
                    }
                    teamObject.intNowBlood = intNowBlood;
                    if (intMaxBlood != -1) {
                        teamObject.intMaxBlood = intMaxBlood;
                    }
                    int intMaxBlood2 = teamObject.intMaxBlood;
                    if (intMaxBlood2 != -1 && teamObject.intNowBlood > intMaxBlood2) {
                        teamObject.intNowBlood = intMaxBlood2;
                    }
                    orPlayer.blnTeam = true;
                    break;
                }
            }
        }
    }

    public void updateTeamMP(final ORPlayer orPlayer, final int intNowMagic, final int intMaxMagic) {
        if (orPlayer != null && Param.getInstance().bytsMyTeam != null) {
            for (int i = 0; i < Param.getInstance().bytsMyTeam.length; i = (byte) (i + 1)) {
                TeamObject teamObject = (TeamObject) Param.getInstance().vTeam.elementAt(Param.getInstance().bytsMyTeam[i]);
                if (teamObject.strName.equals(orPlayer.strNickName) && teamObject.bytTeamId == ORPMe.ME.bytMeTeamID) {
                    teamObject.intNowMagic = intNowMagic;
                    if (intMaxMagic != -1) {
                        teamObject.intMaxMagic = intMaxMagic;
                    }
                    int intMaxMagic2 = teamObject.intMaxMagic;
                    if (intMaxMagic2 != -1 && teamObject.intNowMagic > intMaxMagic2) {
                        teamObject.intNowMagic = intMaxMagic2;
                    }
                    orPlayer.blnTeam = true;
                    break;
                }
            }
        }
    }

    public void updateOtherHP(final int n, final int intNowBlood, final int intMaxBlood) {
        int i = 0;
        while (i < Param.getInstance().bytsMyTeam.length) {
            TeamObject teamObject = (TeamObject) Param.getInstance().vTeam.elementAt(Param.getInstance().bytsMyTeam[i]);
            if (teamObject.intId == n) {
                if (intNowBlood == 0) {
                    teamObject.blnIsDeath = true;
                } else {
                    teamObject.blnIsDeath = false;
                }
                teamObject.intNowBlood = intNowBlood;
                if (intMaxBlood != -1) {
                    teamObject.intMaxBlood = intMaxBlood;
                }
                int intMaxBlood2 = teamObject.intMaxBlood;
                if (intMaxBlood2 != -1 && teamObject.intNowBlood > intMaxBlood2) {
                    teamObject.intNowBlood = intMaxBlood2;
                    break;
                }
                break;
            } else {
                i = (byte) (i + 1);
            }
        }
    }

    public void updateOtherMP(final int n, final int intNowMagic, final int intMaxMagic) {
        int i = 0;
        while (i < Param.getInstance().bytsMyTeam.length) {
            TeamObject teamObject = (TeamObject) Param.getInstance().vTeam.elementAt(Param.getInstance().bytsMyTeam[i]);
            if (teamObject.intId == n) {
                teamObject.intNowMagic = intNowMagic;
                if (intMaxMagic != -1) {
                    teamObject.intMaxMagic = intMaxMagic;
                }
                int intMaxMagic2 = teamObject.intMaxMagic;
                if (intMaxMagic2 != -1 && teamObject.intNowMagic > intMaxMagic2) {
                    teamObject.intNowMagic = intMaxMagic2;
                    break;
                }
                break;
            } else {
                i = (byte) (i + 1);
            }
        }
    }

    public void checkEquipStamina() {
        Enumeration elements = Param.getInstance().hPackageEquip.elements();
        while (elements.hasMoreElements()) {
            PackageObject packageObject = (PackageObject) elements.nextElement();
            if (packageObject.shtStamina * 100 / packageObject.shtStaminaMax <= 20) {
                this.pushRoleStateIcon((short) 0);
                return;
            }
        }
        this.delRoleStateIcon((short) 0);
    }

    public void checkBox(final int n, final byte b) {
        if (Param.getInstance().htBox != null && !Param.getInstance().htBox.isEmpty()) {
            Enumeration elements = Param.getInstance().htBox.elements();
            while (elements.hasMoreElements()) {
                Box box = (Box) elements.nextElement();
                if (box.bytTileY == b && n == box.intMonsterId) {
                    if (!box.blnIsDraw) {
                        box.blnIsDraw = true;
                        if (box.blnShine) {
                            byte[] array = Map.getInstance().bytsBoxXY[box.bytTileY];
                            byte bytTileX = box.bytTileX;
                            ++array[bytTileX];
                        }
                    }
                    return;
                }
            }
            Enumeration elements2 = Param.getInstance().htBox.elements();
            while (elements2.hasMoreElements()) {
                Box box2 = (Box) elements2.nextElement();
                if (!box2.blnIsDraw) {
                    box2.blnIsDraw = true;
                    if (!box2.blnShine) {
                        continue;
                    }
                    byte[] array2 = Map.getInstance().bytsBoxXY[box2.bytTileY];
                    byte bytTileX2 = box2.bytTileX;
                    ++array2[bytTileX2];
                }
            }
        }
    }

    public void setButton() {
        if (this.bytDrawType == 0) {
            if (Param.getInstance().intSelectId < 0) {
                if (!ORPMe.ME.blnStall) {
                    this.setButton(this.bytsButtonType[0], 100, 3);
                } else {
                    this.setButton(this.bytsButtonType[0], 1, 3);
                }
            } else if (Param.getInstance().bytSelectType == 1) {
                if (Param.getInstance().oSelectNpc.blnNpc) {
                    if (Param.getInstance().oSelectNpc.blnCountry && Param.getInstance().oSelectNpc.blnIsTalk) {
                        if (!Param.getInstance().oSelectNpc.blnWood) {
                            this.setButton(0, 17, 2);
                        } else {
                            this.setButton(0, 7, 2);
                        }
                    } else {
                        this.setButton(0, 100, 2);
                    }
                } else {
                    this.setButton(0, 7, 2);
                }
            } else if (Param.getInstance().bytSelectType == 4) {
                if (Param.getInstance().bSelectBox.blnShine) {
                    this.setButton(0, 14, 2);
                } else {
                    this.setButton(0, 100, 2);
                }
            } else if (Param.getInstance().bytSelectType == 5) {
                if (Param.getInstance().bSelectTask.blnShine) {
                    if (Param.getInstance().bSelectTask.bytTaskType == 0) {
                        this.setButton(0, 4, 2);
                    } else {
                        this.setButton(0, 14, 2);
                    }
                }
            } else if (!Param.getInstance().oSelectRole.blnIsAttack) {
                if (Param.getInstance().bytSelectType == 3) {
                    this.setButton(0, 100, 2);
                } else if (Param.getInstance().oSelectRole.bytCountry == ORPMe.ME.bytCountry) {
                    this.setButton(0, 4, 2);
                } else {
                    this.setButton(0, 100, 2);
                }
            } else if (ORPMe.ME.shtImmobilityTime > 0 && ORPMe.ME.blnAttackFlag) {
                this.setButton(0, 100, 2);
            } else if (Param.getInstance().oSelectRole.bytState == 5) {
                this.setButton(0, 100, 2);
            } else {
                this.setButton(0, 5, 2);
            }
        }
    }

    public void setRoleEffect(final ORole oRole, final int n, final short n2) {
    }

    public void setRoleEffect_Conceal(final ORPlayer orPlayer) {
        orPlayer.bytConceal = 2;
        if (orPlayer.intUserId == ORPMe.ME.intUserId) {
            if (Param.getInstance().imgConceal == null) {
                Param.getInstance().imgConceal = ImageManager.CreateImage("conceal", "ingame");
            }
            orPlayer.bytConceal = 1;
            orPlayer.bytConcealDrawRole = 1;
            orPlayer.bytConcealIndex = 0;
            orPlayer.bytConcealAdd = 1;
        } else if (Param.getInstance().intSelectId == orPlayer.intUserId) {
            ORPMe.ME.delSelsectRole();
        }
    }

    public void delRoleEffect(final ORole oRole, final int n) {
    }

    public void delRoleEffect_Conceal(final ORPlayer orPlayer) {
        if (orPlayer.intUserId == ORPMe.ME.intUserId) {
            orPlayer.bytConcealDrawRole = 2;
            orPlayer.bytConcealIndex = 3;
            orPlayer.bytConcealAdd = -1;
        } else {
            orPlayer.bytConceal = 0;
        }
    }

    public byte pointerCheckBuff() {
        byte b = -1;
        if ((ORPMe.ME.intsBuff != null && DCanvas.getInstance().IsPointerDown(Param.GAME_UI_ME_DRAWBUFFX + this._drawMeOffX, Param.GAME_UI_ME_DRAWBUFFY, 72, 9)) || (ORPMe.ME.intsDeBuff != null && DCanvas.getInstance().IsPointerDown(Param.GAME_UI_ME_DRAWBUFFX + this._drawMeOffX, Param.GAME_UI_ME_DRAWBUFFY + 9, 72, 9))) {
            b = (byte) ((DCanvas.getInstance().CurPressedX - Param.GAME_UI_ME_DRAWBUFFX - this._drawMeOffX) / 9 + (DCanvas.getInstance().CurPressedY - Param.GAME_UI_ME_DRAWBUFFY) / 9 * 10);
            if (this.checkBuffIndex(b)) {
                this.bytBuffIndex = b;
            } else {
                b = -1;
            }
        }
        if (Param.getInstance().oSelectRole != null && ((Param.getInstance().oSelectRole.intsBuff != null && DCanvas.getInstance().IsPointerDown(Param.GAME_UI_ROLE_DRAWBUFFX - 72, Param.GAME_UI_ROLE_DRAWBUFFY, 72, 9)) || (Param.getInstance().oSelectRole.intsDeBuff != null && DCanvas.getInstance().IsPointerDown(Param.GAME_UI_ROLE_DRAWBUFFX - 72, Param.GAME_UI_ROLE_DRAWBUFFY + 9, 72, 9)))) {
            b = (byte) ((Param.GAME_UI_ROLE_DRAWBUFFX - DCanvas.getInstance().CurPressedX) / 9 + (DCanvas.getInstance().CurPressedY - Param.GAME_UI_ROLE_DRAWBUFFY) / 9 * 10 + 20);
            if (this.checkBuffIndex(b)) {
                this.bytBuffIndex = b;
            } else {
                b = -1;
            }
        }
        return b;
    }

    public void keyDownChatSwitch() {
        if (!this.blnChannelType) {
            this.blnChannelType = true;
        } else {
            ++this.bytMsgShowType;
            if (this.bytMsgShowType > 3) {
                this.bytMsgShowType = 0;
            }
        }
        this.showChat();
    }

    public void drawChatRect(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final boolean b) {
        for (int i = 0; i < n4; i += Macro.FONTHEIGHT) {
            int fontheight = Macro.FONTHEIGHT;
            if (i + Macro.FONTHEIGHT > n4) {
                fontheight = n4 - i;
            }
            int j;
            for (j = 0; j < n3 - 2 - 16; j += 16) {
                graphics.drawRGB(Param.getInstance().intsChatColor, 0, 16, n + j + 1, n2 + i, 16, fontheight, true);
            }
            if (j < n3 - 2) {
                graphics.drawRGB(Param.getInstance().intsChatColor, 0, 16, n + j + 1, n2 + i, n3 - 2 - j, fontheight, true);
            }
        }
        graphics.setColor(12298871);
        graphics.drawLine(n + 1, n2 + 1, n - 1 + n3, n2 + 1);
        graphics.drawLine(n + 1, n2 + n4 - 1, n - 1 + n3, n2 + n4 - 1);
        graphics.drawLine(n + 1, n2 + 1, n + 1, n2 + n4 - 1);
        graphics.drawLine(n - 1 + n3, n2 + 1, n - 1 + n3, n2 + n4 - 1);
        graphics.setColor(7816243);
        graphics.drawLine(n + 1, n2, n + n3 - 2, n2);
        graphics.drawLine(n + 1, n2 + n4, n + n3 - 2, n2 + n4);
        graphics.drawLine(n, n2 + 1, n, n2 + n4 - 2);
        graphics.drawLine(n + n3, n2 + 1, n + n3, n2 + n4 - 2);
    }

    public void StartPK(final int intPKRoleId, final byte b, final byte b2, final short n) {
        if (Param.getInstance().htRolePlayer != null && Param.getInstance().htRolePlayer.get(new Integer(intPKRoleId)) != null) {
            this.intPKRoleId = intPKRoleId;
            this.blnPK = false;
            this.intPKTime = n * 1000;
            this.intPKTempTime = b2 * 1000;
            this.TimeCycle = b * 1000;
            DCanvas.getInstance().addInformation(String.valueOf(this.intPKTempTime / 1000) + "Giây sau khi bắt đầu cuộc đấu");
        }
    }

    public void endPK(final boolean b) {
        this.blnPK = false;
        this.intPKTime = 0;
        if (b) {
            DCanvas.getInstance().addInformation("Phó bản kết thúc");
        }
        if (Param.getInstance().htRolePlayer != null) {
            ORPlayer orPlayer = (ORPlayer) Param.getInstance().htRolePlayer.get(new Integer(this.intPKRoleId));
            if (orPlayer != null) {
                orPlayer.blnIsAttack = false;
                if (this.intPKRoleId == Param.getInstance().intSelectId) {
                    ORPMe.ME.setSelectRole(Param.getInstance().oSelectRole);
                }
            }
        }
        this.intPKRoleId = -1;
    }

    public void logicMoney() {
        ++this.bytMoneyIndex;
        if (this.bytMoneyIndex > 12) {
            this.bytMoneyIndex = 0;
        }
    }

    public void drawBullion(final Graphics graphics, final int n, final int n2) {
        Param.getInstance().moneyQs.drawAnimationFrame(graphics, 0, 0, n, n2);
    }

    public void drawMoneyNum(final Graphics graphics, final int n, final int n2, final int n3) {
        StringBuffer sb = new StringBuffer(Integer.toString(n));
        for (int i = 0; i < sb.length(); ++i) {
            int n4 = sb.charAt(i) - '0';
            try {
                DrawBase.drawRegion(Param.getInstance().imgMoneyNum, n2 + i * 7, n3, n4 * 5, 0, 5, 8, 0, 20);
            } catch (Exception ex) {
            }
        }
    }

    public void drawEquipNum(final Graphics graphics, int n, final int n2, final int n3) {
        boolean b = true;
        if (n < 0) {
            n = -n;
            b = false;
        }
        StringBuffer sb = new StringBuffer(Integer.toString(n));
        if (b) {
            if (Param.getInstance().imgPlusNum != null) {
                DrawBase.drawRegion(Param.getInstance().imgPlusNum, n2, n3, 60, 0, 5, 8, 0, 20);
            }
        } else if (Param.getInstance().imgMinusNum != null) {
            DrawBase.drawRegion(Param.getInstance().imgMinusNum, n2, n3, 55, 0, 5, 8, 0, 20);
        }
        for (int i = 0; i < sb.length(); ++i) {
            int n4 = sb.charAt(i) - '0';
            try {
                if (b) {
                    if (Param.getInstance().imgPlusNum != null) {
                        DrawBase.drawRegion(Param.getInstance().imgPlusNum, n2 + (i + 1) * 7, n3, n4 * 5, 0, 5, 8, 0, 20);
                    }
                } else if (Param.getInstance().imgMinusNum != null) {
                    DrawBase.drawRegion(Param.getInstance().imgMinusNum, n2 + (i + 1) * 7, n3, n4 * 5, 0, 5, 8, 0, 20);
                }
            } catch (Exception ex) {
            }
        }
    }

    public void drawWhiteMoneyNum(final Graphics graphics, final int n, final int n2, final int n3) {
        StringBuffer sb = new StringBuffer(Integer.toString(n));
        for (int i = 0; i < sb.length(); ++i) {
            DrawBase.drawRegion(Param.getInstance().imgHMPNum, n2 + i * 7, n3, (sb.charAt(i) - '0') * 5, 0, 5, 8, 0, 20);
        }
    }

    public void drawOptionImage(final Graphics graphics, final Image image, final short n, final short n2, final byte b) {
        if (image != null) {
            DrawBase.drawImage(image, n, (short) (n2 + (Macro.FONTHEIGHT - image.getHeight()) / 2), b);
        }
    }

    public void drawInputRect(final Graphics graphics, final int n, final int n2, final int n3, int n4, final int n5, final boolean b) {
        int n6 = 19;
        short n7;
        if (b) {
            n7 = (short) (n2 + n6);
            n4 -= n6;
        } else {
            n7 = (short) n2;
        }
        graphics.setColor(11440201);
        graphics.fillRect(n7, n3, n4, n5);
        graphics.setColor(8740692);
        graphics.drawLine(n7, n3, n7 + n4 - 1, n3);
        graphics.drawLine(n7, n3, n7, n3 + n5 - 1);
        graphics.setColor(6298376);
        graphics.drawLine(n7 + 1, n3 + 1, n7 + 1 + n4 - 2, n3 + 1);
        graphics.drawLine(n7 + 1, n3 + 2, n7 + 1, n3 + n5 - 2);
        graphics.setColor(11967068);
        graphics.drawLine(n7 + n4 - 2, n3 + 1, n7 + n4 - 2, n3 + 1 + n5 - 2);
        graphics.drawLine(n7 + 1, n3 + n5 - 2, n7 + 1 + n4 - 2, n3 + n5 - 2);
        graphics.setColor(16777164);
        graphics.drawLine(n7, n3 + n5 - 1, n7 + n4 - 1, n3 + n5 - 1);
        graphics.drawLine(n7 + n4 - 1, n3, n7 + n4 - 1, n3 + n5 - 1);
        if (n >= 0) {
            this.drawMoneyNum(graphics, n, (short) (n7 + 4), n3 + 4);
        }
        if (b) {
            this.drawBullion(graphics, n7 - n6, n3 + 7);
        }
    }

    public void backSmallRect(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        DrawBase.drawBox(n, n2, n3, n4, this.smallRectColor, true);
    }

    public void drawTeamImageOption(final Graphics graphics, final TeamObject teamObject, final short n, final short n2, final byte b) {
        if (teamObject.blnIsOnLine) {
            int n3 = teamObject.blnIsMale ? 1268117 : 13898001;
        } else {
            int n4 = Macro.INT_PROP_COLOR[0];
        }
        if (DCanvas.getInstance().UMenu != null) {
            MenuUI.getInstance().scroll_OFF = (DCanvas.getInstance().blnSpoolr ? ScrollText.arrowhead_width : 0);
            short n5 = (short) (Macro.SCREEN_WIDTH - Macro.font.stringWidth(MenuUI.getInstance().getLeaguerRemark(teamObject)) - 18 - 3);
            int color;
            if (teamObject.bytTroopRank == 3) {
                color = (teamObject.blnIsOnLine ? 12912648 : Macro.INT_PROP_COLOR[0]);
            } else if (teamObject.bytTroopRank == 2) {
                color = (teamObject.blnIsOnLine ? 15621517 : Macro.INT_PROP_COLOR[0]);
            } else {
                color = (teamObject.blnIsOnLine ? 0 : Macro.INT_PROP_COLOR[0]);
            }
            graphics.setColor(color);
            graphics.drawString(teamObject.strName, n, n2, 20);
            if (teamObject.bytTroopRank == 1) {
                graphics.drawString("Thành viên trong nhóm", n5 - MenuUI.getInstance().scroll_OFF, n2, 20);
            } else if (teamObject.bytTroopRank == 2) {
                graphics.drawString("Trợ lý", n5 - MenuUI.getInstance().scroll_OFF, n2, 20);
            } else if (teamObject.bytTroopRank == 3) {
                graphics.drawString("Thuyền trưởng", n5 - MenuUI.getInstance().scroll_OFF, n2, 20);
            }
        } else {
            this.drawTeamName(graphics, teamObject.strName, (short) (n + this.teamheadwidth), (short) (n2 + this.teamheadheight / 2 + 4), teamObject.intNowBlood, teamObject.intMaxBlood, teamObject.blnIsDeath);
            this.TeamBM.drawAnimationFrame(graphics, 0, 0, 0, n2);
            this.drawTeamHp(graphics, teamObject.intNowBlood, teamObject.intMaxBlood, 0, n2);
            this.drawTeamMp(graphics, teamObject.intNowMagic, teamObject.intMaxMagic, 0, n2);
            Param.getInstance().sprRolePortrait.drawAnimationFrame(graphics, teamObject.bytHead, 0, 19, n2 + (this.teamheadheight >> 1) - 4);
            if (teamObject.blnIsOnLine) {
                this.drawWhiteNum(graphics, teamObject.shtLevel, n + 18 - Integer.toString(teamObject.shtLevel).length() * 4 + 4, n2 + this.teamheadheight - 9);
            }
        }
    }

    private void drawTeamName(final Graphics graphics, final String s, final short n, final short n2, final int n3, final int n4, final boolean b) {
        if (!b) {
        }
        int n5 = 16777215;
        if (b) {
            n5 = 16711680;
        }
        StringManager.drawShadowWord(graphics, s, n, n2 - 16, n5, 0, 20);
    }

    public void drawTeamRank(final Graphics graphics, final byte b, final int n, final int n2) {
        if (b != 3) {
            if (b == 2) {
            }
        }
    }

    public void drawWordMove(final Graphics graphics, final String[] array, final int n, final int n2, final byte b) {
        if (array == null) {
            return;
        }
        graphics.setColor(0);
        for (byte b2 = 0; b2 < array.length; ++b2) {
            graphics.drawString(array[b2], n, n2 + b2 * Macro.FONTHEIGHT, b);
        }
    }

    public void drawRectTypeA(final Graphics graphics, final String s, final String s2, final int color, final String s3) {
        short n = (short) (Macro.SCREEN_HEIGHT - (Macro.FONTHEIGHT * 2 + Macro.shtRectHeight + 31));
        LayoutStyle.getInstance().drawFullBackGround(13866046);
        if (s != null && !s.equals("")) {
            StringManager.drawShadowWord(graphics, s, Macro.SCREEN_WIDTH / 2, 7, Macro.INT_TITLE_COLOR[1], Macro.INT_TITLE_COLOR[0], 17);
        }
        graphics.setColor(color);
        graphics.drawString(s2, Macro.SCREEN_WIDTH / 2, Macro.FONTHEIGHT + Macro.shtRectHeight + 8, 17);
        if (s3 != null && !s3.equals("")) {
            short n2 = (short) ((Macro.SCREEN_WIDTH + Macro.font.stringWidth(s2) + Macro.FONTW) / 2);
            graphics.setColor(0);
            graphics.drawString(s3, n2, Macro.FONTHEIGHT + Macro.shtRectHeight + 8, 20);
        }
    }

    public void getEquioAttribute(final Hashtable hashtable, final int n) {
        this.vsColorInfo = null;
        Param.getInstance().blnColorInfo = true;
        if (MenuUI.getInstance() != null) {
            MenuUI.getInstance().setEquipPackage(hashtable, n);
        }
        this.getWeaponAttribute((PackageObject) hashtable.get(new Integer(n)));
    }

    public void getEquioAttribute(final PackageObject packageObject) {
        this.vsColorInfo = null;
        Param.getInstance().blnColorInfo = true;
        this.getWeaponAttribute(packageObject);
    }

    private void addColorInfo(final Vector vector) {
        this.vsColorInfo = this.setNoteVector(this.vsColorInfo, vector);
    }

    public Vector[] setNoteVector(Vector[] array, final Vector vector) {
        if (array == null) {
            array = new Vector[]{vector};
        } else {
            Vector[] array2 = array;
            array = new Vector[array2.length + 1];
            System.arraycopy(array2, 0, array, 0, array2.length);
            array[array2.length] = vector;
        }
        return array;
    }

    private void getWeaponAttribute(final PackageObject packageObject) {
        if (DCanvas.getInstance().UMenu != null && Param.getInstance().oSelectNpc != null && packageObject.intPaiMaiPrice != -1) {
            Vector vector = new Vector(1, 1);
            ChatContent chatContent = new ChatContent();
            chatContent.fontColor = 0;
            chatContent.character = "Giá bán:" + packageObject.intPaiMaiPrice + " xu.";
            chatContent.type = 1;
            vector.addElement(chatContent);
            this.addColorInfo(vector);
        }
        if (packageObject.bytSeal == 1) {
            if (Macro.font.stringWidth("Đã được niêm phong, bạn có thể sử dụng phước lành của thiết bị để nâng") > StringManager.getNewLineW()) {
                Vector vector2 = new Vector(1, 1);
                ChatContent chatContent2 = new ChatContent();
                chatContent2.fontColor = 16711680;
                chatContent2.character = "Niêm phong";
                chatContent2.type = 1;
                vector2.addElement(chatContent2);
                this.addColorInfo(vector2);
                Vector vector3 = new Vector(1, 1);
                ChatContent chatContent3 = new ChatContent();
                chatContent3.fontColor = 16711680;
                chatContent3.character = "Có thể sử dụng phước lành của thiết bị để nâng";
                chatContent3.type = 1;
                vector3.addElement(chatContent3);
                this.addColorInfo(vector3);
            } else {
                Vector vector4 = new Vector(1, 1);
                ChatContent chatContent4 = new ChatContent();
                chatContent4.fontColor = 16711680;
                chatContent4.character = "Đã được niêm phong, bạn có thể sử dụng phước lành của thiết bị để nâng";
                chatContent4.type = 1;
                vector4.addElement(chatContent4);
                this.addColorInfo(vector4);
            }
        }
        Vector vector5 = new Vector(1, 1);
        ChatContent chatContent5 = new ChatContent();
        chatContent5.character = String.valueOf(packageObject.strName) + "(" + Macro.STRING_PROP_NAME[packageObject.bytQuality] + ")";
        chatContent5.fontColor = packageObject.intColor;
        chatContent5.type = 1;
        chatContent5.type = 1;
        vector5.addElement(chatContent5);
        this.addColorInfo(vector5);
        if (packageObject.byteEquipFixed != -1) {
            Vector vector6 = new Vector(1, 1);
            ChatContent chatContent6 = new ChatContent();
            chatContent6.fontColor = 0;
            chatContent6.character = Macro.STRING_WEAPON_PART[packageObject.byteEquipFixed];
            chatContent6.type = 1;
            vector6.addElement(chatContent6);
            this.addColorInfo(vector6);
        }
        Vector vector7 = new Vector(1, 1);
        ChatContent chatContent7 = new ChatContent();
        chatContent7.fontColor = 0;
        if (packageObject.byteEquipPart > 0 && packageObject.byteEquipPart <= 3) {
            chatContent7.character = Macro.STRING_PROP_ARMOR[packageObject.byteEquipPart - 1];
            chatContent7.type = 0;
            vector7.addElement(chatContent7);
            this.addColorInfo(vector7);
        }
        Vector vector8 = new Vector(1, 1);
        ChatContent chatContent8 = new ChatContent();
        if (packageObject.byteEquipType >= 10) {
            chatContent8.character = Macro.STRING_WEAPON[packageObject.byteEquipType % 10];
            chatContent8.type = 0;
            vector8.addElement(chatContent8);
            this.addColorInfo(vector8);
        }
        if (packageObject.bytOperation != 1) {
            Vector vector9 = new Vector(1, 1);
            ChatContent chatContent9 = new ChatContent();
            chatContent9.fontColor = 0;
            if (packageObject.bytOperation == 2) {
                String character = "Khóa khi trang bị";
                if (packageObject.bytIsBind == 1) {
                    character = String.valueOf(character) + "(Khóa)";
                }
                if (packageObject.bytIsBind != 1) {
                    character = String.valueOf(character) + "(Không khóa)";
                }
                chatContent9.character = character;
            } else if (packageObject.bytOperation == 3) {
                String character2 = "Khóa giao dịch ";
                if (packageObject.bytIsBind == 1) {
                    character2 = String.valueOf(character2) + "(Khóa)";
                }
                if (packageObject.bytIsBind != 1) {
                    character2 = String.valueOf(character2) + "(Không khóa)";
                }
                chatContent9.character = character2;
            }
            chatContent9.type = 1;
            vector9.addElement(chatContent9);
            this.addColorInfo(vector9);
        }
        Vector vector10 = new Vector(1, 1);
        ChatContent chatContent10 = new ChatContent();
        if (packageObject.byteEquipType >= 10 && ORole.WeaponToCareer(packageObject.byteEquipType) != null) {
            if (ORole.canEquipWeapon(ORPMe.ME.bytOccupation, packageObject.byteEquipType)) {
                chatContent10.fontColor = 0;
            } else {
                chatContent10.fontColor = 16711680;
            }
            chatContent10.character = "Phái: " + ORole.WeaponToCareer(packageObject.byteEquipType);
            chatContent10.type = 0;
            vector10.addElement(chatContent10);
            this.addColorInfo(vector10);
        }
        if (packageObject.byteEquipPart > 0 && ORole.WeaponToCareer(packageObject.byteEquipPart) != null) {
            if (ORole.canEquipEquipment(ORPMe.ME.bytOccupation, packageObject.byteEquipPart)) {
                chatContent10.fontColor = 0;
            } else {
                chatContent10.fontColor = 16711680;
            }
            chatContent10.character = "Phái: " + ORole.WeaponToCareer(packageObject.byteEquipPart);
            chatContent10.type = 0;
            vector10.addElement(chatContent10);
            this.addColorInfo(vector10);
        }
        if (!Param.getInstance().GoodsSee) {
            String[] array = new String[MenuUI.getInstance().RoundHole * 3 / Macro.FONTHEIGHT + ((MenuUI.getInstance().RoundHole * 3 % Macro.FONTHEIGHT != 0) ? 1 : 0) + 1];
            for (int i = 0; i < array.length; ++i) {
                array[i] = " ";
            }
            for (int j = 0; j < array.length; j = (byte) (j + 1)) {
                Vector vector11 = new Vector(1, 1);
                ChatContent chatContent11 = new ChatContent();
                chatContent11.fontColor = 0;
                chatContent11.character = array[j];
                chatContent11.type = 2;
                vector11.addElement(chatContent11);
                this.addColorInfo(vector11);
            }
            Param.getInstance().RunOnce = true;
        }
        byte b = 0;
        if (packageObject.bytsAggrandizement != null) {
            b = (byte) ((packageObject.bytsAggrandizement.length <= 4) ? 1 : 2);
            Vector baseAttribute = this.getBaseAttribute(packageObject.bytsAggrandizement, packageObject.bytsAttribute, packageObject.shtsAttributeInfo);
            if (baseAttribute != null && !baseAttribute.isEmpty()) {
                for (int k = 0; k < baseAttribute.size(); k = (byte) (k + 1)) {
                    Vector vector12 = new Vector(1, 1);
                    ChatContent chatContent12 = new ChatContent();
                    chatContent12.fontColor = 0;
                    chatContent12.character = (String) baseAttribute.elementAt(k);
                    chatContent12.type = 1;
                    vector12.addElement(chatContent12);
                    this.addColorInfo(vector12);
                }
            }
        }
        byte attributeIndex = this.getAttributeIndex((byte) 25, packageObject.bytsAttribute);
        byte attributeIndex2 = this.getAttributeIndex((byte) 26, packageObject.bytsAttribute);
        if (attributeIndex != -1) {
            Vector vector13 = new Vector(1, 1);
            StringBuffer sb = new StringBuffer();
            sb.append("S.Thương vật lý: ");
            sb.append(packageObject.shtsAttributeInfo[attributeIndex]);
            sb.append("-");
            sb.append(packageObject.shtsAttributeInfo[attributeIndex2]);
            String[] wenZi = StringManager.wenZi(sb.toString(), StringManager.getNewLineW());
            for (int l = 0; l < wenZi.length; l = (byte) (l + 1)) {
                ChatContent chatContent13 = new ChatContent();
                chatContent13.fontColor = 0;
                chatContent13.character = wenZi[l];
                chatContent13.type = 1;
                vector13.addElement(chatContent13);
            }
            this.addColorInfo(vector13);
        }
        byte attributeIndex3 = this.getAttributeIndex((byte) 42, packageObject.bytsAttribute);
        byte attributeIndex4 = this.getAttributeIndex((byte) 41, packageObject.bytsAttribute);
        if (attributeIndex3 != -1) {
            Vector vector14 = new Vector(1, 1);
            StringBuffer sb2 = new StringBuffer();
            sb2.append("S.Thương phép: ");
            sb2.append(packageObject.shtsAttributeInfo[attributeIndex3]);
            sb2.append("-");
            sb2.append(packageObject.shtsAttributeInfo[attributeIndex4]);
            String[] wenZi2 = StringManager.wenZi(sb2.toString(), StringManager.getNewLineW());
            for (int n = 0; n < wenZi2.length; n = (byte) (n + 1)) {
                ChatContent chatContent14 = new ChatContent();
                chatContent14.fontColor = 0;
                chatContent14.character = wenZi2[n];
                chatContent14.type = 1;
                vector14.addElement(chatContent14);
            }
            this.addColorInfo(vector14);
        }
        byte attributeIndex5 = this.getAttributeIndex((byte) 19, packageObject.bytsAttribute);
        if (attributeIndex5 != -1) {
            Vector vector15 = new Vector(1, 1);
            StringBuffer sb3 = new StringBuffer();
            sb3.append("Thủ: ");
            sb3.append(packageObject.shtsAttributeInfo[attributeIndex5]);
            if (b != 0 && packageObject.bytsAggrandizement[(b == 1) ? 1 : 3] >= 1) {
                int n2 = (packageObject.bytsAggrandizement[(b == 1) ? 1 : 3] * packageObject.shtsAttributeInfo[attributeIndex5] * 10 + 50) / 100;
                sb3.append("(");
                sb3.append(n2);
                sb3.append(")");
            }
            String[] wenZi3 = StringManager.wenZi(sb3.toString(), StringManager.getNewLineW());
            for (int n3 = 0; n3 < wenZi3.length; n3 = (byte) (n3 + 1)) {
                ChatContent chatContent15 = new ChatContent();
                chatContent15.fontColor = 0;
                chatContent15.character = wenZi3[n3];
                chatContent15.type = 1;
                vector15.addElement(chatContent15);
            }
            this.addColorInfo(vector15);
        }
        byte attributeIndex6 = this.getAttributeIndex((byte) 13, packageObject.bytsAttribute);
        if (attributeIndex6 != -1) {
            Vector vector16 = new Vector(1, 1);
            StringBuffer sb4 = new StringBuffer();
            sb4.append("Máu + ");
            sb4.append(packageObject.shtsAttributeInfo[attributeIndex6]);
            ChatContent chatContent16 = new ChatContent();
            chatContent16.fontColor = 969327;
            chatContent16.character = sb4.toString();
            chatContent16.type = 1;
            vector16.addElement(chatContent16);
            this.addColorInfo(vector16);
        }
        byte attributeIndex7 = this.getAttributeIndex((byte) 14, packageObject.bytsAttribute);
        if (attributeIndex7 != -1) {
            Vector vector17 = new Vector(1, 1);
            StringBuffer sb5 = new StringBuffer();
            sb5.append("Phép + ");
            sb5.append(packageObject.shtsAttributeInfo[attributeIndex7]);
            ChatContent chatContent17 = new ChatContent();
            chatContent17.fontColor = 969327;
            chatContent17.character = sb5.toString();
            chatContent17.type = 1;
            vector17.addElement(chatContent17);
            this.addColorInfo(vector17);
        }
        byte attributeIndex8 = this.getAttributeIndex((byte) 39, packageObject.bytsAttribute);
        if (attributeIndex8 != -1) {
            Vector vector18 = new Vector(1, 1);
            StringBuffer sb6 = new StringBuffer();
            sb6.append("Khoảng cách tấn công: ");
            sb6.append(packageObject.shtsAttributeInfo[attributeIndex8]);
            ChatContent chatContent18 = new ChatContent();
            chatContent18.fontColor = 0;
            chatContent18.character = sb6.toString();
            chatContent18.type = 1;
            vector18.addElement(chatContent18);
            this.addColorInfo(vector18);
        }
        byte attributeIndex9 = this.getAttributeIndex((byte) 40, packageObject.bytsAttribute);
        if (attributeIndex9 != -1) {
            Vector vector19 = new Vector(1, 1);
            StringBuffer sb7 = new StringBuffer();
            sb7.append("Tốc độ tấn công");
            sb7.append(packageObject.shtsAttributeInfo[attributeIndex9] / 1000);
            sb7.append(".");
            sb7.append(packageObject.shtsAttributeInfo[attributeIndex9] % 1000 / 100);
            sb7.append("Giây");
            ChatContent chatContent19 = new ChatContent();
            chatContent19.fontColor = 0;
            chatContent19.character = sb7.toString();
            chatContent19.type = 1;
            vector19.addElement(chatContent19);
            this.addColorInfo(vector19);
        }
        Vector vector20 = new Vector(1, 1);
        StringBuffer sb8 = new StringBuffer();
        if (packageObject.shtStaminaMax != 0) {
            Vector vector21 = new Vector(1, 1);
            sb8.append("Độ bền: ");
            sb8.append(packageObject.shtStamina);
            sb8.append("/");
            sb8.append(packageObject.shtStaminaMax);
            ChatContent chatContent20 = new ChatContent();
            chatContent20.fontColor = 0;
            chatContent20.character = sb8.toString();
            chatContent20.type = 1;
            vector21.addElement(chatContent20);
            this.addColorInfo(vector21);
        }
        Vector vector22 = new Vector(1, 1);
        ChatContent chatContent21 = new ChatContent();
        StringBuffer sb9 = new StringBuffer();
        sb9.append("Yêu cầu: cấp ");
        sb9.append(packageObject.shtLevel);
        chatContent21.fontColor = ((packageObject.shtLevel > ORPMe.ME.shtLevel) ? 16711680 : 0);
        chatContent21.character = sb9.toString();
        chatContent21.type = 0;
        vector22.addElement(chatContent21);
        this.addColorInfo(vector22);
        byte attributeIndex10 = this.getAttributeIndex((byte) 9, packageObject.bytsAttribute);
        if (attributeIndex10 != -1) {
            Vector vector23 = new Vector(1, 1);
            StringBuffer sb10 = new StringBuffer();
            sb10.append("Lực  +  ");
            sb10.append(packageObject.shtsAttributeInfo[attributeIndex10]);
            ChatContent chatContent22 = new ChatContent();
            chatContent22.fontColor = 969327;
            chatContent22.character = sb10.toString();
            chatContent22.type = 1;
            vector23.addElement(chatContent22);
            this.addColorInfo(vector23);
        }
        byte attributeIndex11 = this.getAttributeIndex((byte) 11, packageObject.bytsAttribute);
        if (attributeIndex11 != -1) {
            Vector vector24 = new Vector(1, 1);
            StringBuffer sb11 = new StringBuffer();
            sb11.append("Nhanh  +  ");
            sb11.append(packageObject.shtsAttributeInfo[attributeIndex11]);
            ChatContent chatContent23 = new ChatContent();
            chatContent23.fontColor = 969327;
            chatContent23.character = sb11.toString();
            chatContent23.type = 1;
            vector24.addElement(chatContent23);
            this.addColorInfo(vector24);
        }
        byte attributeIndex12 = this.getAttributeIndex((byte) 7, packageObject.bytsAttribute);
        if (attributeIndex12 != -1) {
            Vector vector25 = new Vector(1, 1);
            StringBuffer sb12 = new StringBuffer();
            sb12.append("Thể Lực  +  ");
            sb12.append(packageObject.shtsAttributeInfo[attributeIndex12]);
            ChatContent chatContent24 = new ChatContent();
            chatContent24.fontColor = 969327;
            chatContent24.character = sb12.toString();
            chatContent24.type = 1;
            vector25.addElement(chatContent24);
            this.addColorInfo(vector25);
        }
        byte attributeIndex13 = this.getAttributeIndex((byte) 8, packageObject.bytsAttribute);
        if (attributeIndex13 != -1) {
            Vector vector26 = new Vector(1, 1);
            StringBuffer sb13 = new StringBuffer();
            sb13.append("Trí  +   ");
            sb13.append(packageObject.shtsAttributeInfo[attributeIndex13]);
            ChatContent chatContent25 = new ChatContent();
            chatContent25.fontColor = 969327;
            chatContent25.character = sb13.toString();
            chatContent25.type = 1;
            vector26.addElement(chatContent25);
            this.addColorInfo(vector26);
        }
        byte attributeIndex14 = this.getAttributeIndex((byte) 10, packageObject.bytsAttribute);
        if (attributeIndex14 != -1) {
            Vector vector27 = new Vector(1, 1);
            StringBuffer sb14 = new StringBuffer();
            sb14.append("Thần  +  ");
            sb14.append(packageObject.shtsAttributeInfo[attributeIndex14]);
            ChatContent chatContent26 = new ChatContent();
            chatContent26.fontColor = 969327;
            chatContent26.character = sb14.toString();
            chatContent26.type = 1;
            vector27.addElement(chatContent26);
            this.addColorInfo(vector27);
        }
        byte attributeIndex15 = this.getAttributeIndex((byte) 12, packageObject.bytsAttribute);
        if (attributeIndex15 != -1) {
            Vector vector28 = new Vector(1, 1);
            StringBuffer sb15 = new StringBuffer();
            sb15.append("May mắn  +  ");
            sb15.append(packageObject.shtsAttributeInfo[attributeIndex15]);
            ChatContent chatContent27 = new ChatContent();
            chatContent27.fontColor = 969327;
            chatContent27.character = sb15.toString();
            chatContent27.type = 1;
            vector28.addElement(chatContent27);
            this.addColorInfo(vector28);
        }
        byte attributeIndex16 = this.getAttributeIndex((byte) 15, packageObject.bytsAttribute);
        if (attributeIndex16 != -1) {
            Vector vector29 = new Vector(1, 1);
            StringBuffer sb16 = new StringBuffer();
            sb16.append("S.Thương vật lý chí mạng  +  ");
            sb16.append(packageObject.shtsAttributeInfo[attributeIndex16]);
            sb16.append("%");
            ChatContent chatContent28 = new ChatContent();
            chatContent28.fontColor = 969327;
            chatContent28.character = sb16.toString();
            chatContent28.type = 1;
            vector29.addElement(chatContent28);
            this.addColorInfo(vector29);
        }
        byte attributeIndex17 = this.getAttributeIndex((byte) 16, packageObject.bytsAttribute);
        if (attributeIndex17 != -1) {
            Vector vector30 = new Vector(1, 1);
            StringBuffer sb17 = new StringBuffer();
            sb17.append("S.Thương phép chí mạng  +  ");
            sb17.append(packageObject.shtsAttributeInfo[attributeIndex17]);
            sb17.append("%");
            ChatContent chatContent29 = new ChatContent();
            chatContent29.fontColor = 969327;
            chatContent29.character = sb17.toString();
            chatContent29.type = 1;
            vector30.addElement(chatContent29);
            this.addColorInfo(vector30);
        }
        byte attributeIndex18 = this.getAttributeIndex((byte) 18, packageObject.bytsAttribute);
        if (attributeIndex18 != -1) {
            Vector vector31 = new Vector(1, 1);
            StringBuffer sb18 = new StringBuffer();
            sb18.append("Né  +  ");
            sb18.append(packageObject.shtsAttributeInfo[attributeIndex18]);
            sb18.append("%");
            ChatContent chatContent30 = new ChatContent();
            chatContent30.fontColor = 969327;
            chatContent30.character = sb18.toString();
            chatContent30.type = 1;
            vector31.addElement(chatContent30);
            this.addColorInfo(vector31);
        }
        byte attributeIndex19 = this.getAttributeIndex((byte) 27, packageObject.bytsAttribute);
        byte attributeIndex20 = this.getAttributeIndex((byte) 32, packageObject.bytsAttribute);
        if (attributeIndex19 != -1) {
            Vector vector32 = new Vector(1, 1);
            StringBuffer sb19 = new StringBuffer();
            sb19.append("Kháng phép:");
            sb19.append(packageObject.shtsAttributeInfo[attributeIndex19]);
            sb19.append("-");
            sb19.append(packageObject.shtsAttributeInfo[attributeIndex20]);
            if (b != 0 && packageObject.bytsAggrandizement[(b == 1) ? 2 : 4] >= 1) {
                int n4 = (packageObject.bytsAggrandizement[(b == 1) ? 2 : 4] * ((packageObject.shtsAttributeInfo[attributeIndex19] + packageObject.shtsAttributeInfo[attributeIndex20]) / 2) * 10 + 50) / 100;
                sb19.append("(");
                sb19.append(n4);
                sb19.append(")");
            }
            String[] wenZi4 = StringManager.wenZi(sb19.toString(), StringManager.getNewLineW());
            for (int n5 = 0; n5 < wenZi4.length; n5 = (byte) (n5 + 1)) {
                ChatContent chatContent31 = new ChatContent();
                chatContent31.fontColor = 0;
                chatContent31.character = wenZi4[n5];
                chatContent31.type = 1;
                vector32.addElement(chatContent31);
            }
            this.addColorInfo(vector32);
        }
        byte attributeIndex21 = this.getAttributeIndex((byte) 28, packageObject.bytsAttribute);
        byte attributeIndex22 = this.getAttributeIndex((byte) 33, packageObject.bytsAttribute);
        if (attributeIndex21 != -1) {
            Vector vector33 = new Vector(1, 1);
            StringBuffer sb20 = new StringBuffer();
            sb20.append("Kháng phép:");
            sb20.append(packageObject.shtsAttributeInfo[attributeIndex21]);
            sb20.append("-");
            sb20.append(packageObject.shtsAttributeInfo[attributeIndex22]);
            if (b != 0 && packageObject.bytsAggrandizement[(b == 1) ? 2 : 4] >= 1) {
                int n6 = (packageObject.bytsAggrandizement[(b == 1) ? 2 : 4] * ((packageObject.shtsAttributeInfo[attributeIndex21] + packageObject.shtsAttributeInfo[attributeIndex22]) / 2) * 10 + 50) / 100;
                sb20.append("(");
                sb20.append(n6);
                sb20.append(")");
            }
            String[] wenZi5 = StringManager.wenZi(sb20.toString(), StringManager.getNewLineW());
            for (int n7 = 0; n7 < wenZi5.length; n7 = (byte) (n7 + 1)) {
                ChatContent chatContent32 = new ChatContent();
                chatContent32.fontColor = 0;
                chatContent32.character = wenZi5[n7];
                chatContent32.type = 1;
                vector33.addElement(chatContent32);
            }
            this.addColorInfo(vector33);
        }
        byte attributeIndex23 = this.getAttributeIndex((byte) 29, packageObject.bytsAttribute);
        byte attributeIndex24 = this.getAttributeIndex((byte) 34, packageObject.bytsAttribute);
        if (attributeIndex23 != -1) {
            Vector vector34 = new Vector(1, 1);
            StringBuffer sb21 = new StringBuffer();
            sb21.append("Kháng phép thuật:");
            sb21.append(packageObject.shtsAttributeInfo[attributeIndex23]);
            sb21.append("-");
            sb21.append(packageObject.shtsAttributeInfo[attributeIndex24]);
            if (b != 0 && packageObject.bytsAggrandizement[(b == 1) ? 2 : 4] >= 1) {
                int n8 = (packageObject.bytsAggrandizement[(b == 1) ? 2 : 4] * ((packageObject.shtsAttributeInfo[attributeIndex23] + packageObject.shtsAttributeInfo[attributeIndex24]) / 2) * 10 + 50) / 100;
                sb21.append("(");
                sb21.append(n8);
                sb21.append(")");
            }
            String[] wenZi6 = StringManager.wenZi(sb21.toString(), StringManager.getNewLineW());
            for (int n9 = 0; n9 < wenZi6.length; n9 = (byte) (n9 + 1)) {
                ChatContent chatContent33 = new ChatContent();
                chatContent33.fontColor = 0;
                chatContent33.character = wenZi6[n9];
                chatContent33.type = 1;
                vector34.addElement(chatContent33);
            }
            this.addColorInfo(vector34);
        }
        byte attributeIndex25 = this.getAttributeIndex((byte) 30, packageObject.bytsAttribute);
        byte attributeIndex26 = this.getAttributeIndex((byte) 35, packageObject.bytsAttribute);
        if (attributeIndex25 != -1) {
            Vector vector35 = new Vector(1, 1);
            StringBuffer sb22 = new StringBuffer();
            sb22.append("Kháng phép:");
            sb22.append(packageObject.shtsAttributeInfo[attributeIndex25]);
            sb22.append("-");
            sb22.append(packageObject.shtsAttributeInfo[attributeIndex26]);
            if (b != 0 && packageObject.bytsAggrandizement[(b == 1) ? 2 : 4] >= 1) {
                int n10 = (packageObject.bytsAggrandizement[(b == 1) ? 2 : 4] * ((packageObject.shtsAttributeInfo[attributeIndex25] + packageObject.shtsAttributeInfo[attributeIndex26]) / 2) * 10 + 50) / 100;
                sb22.append("(");
                sb22.append(n10);
                sb22.append(")");
            }
            String[] wenZi7 = StringManager.wenZi(sb22.toString(), StringManager.getNewLineW());
            for (int n11 = 0; n11 < wenZi7.length; n11 = (byte) (n11 + 1)) {
                ChatContent chatContent34 = new ChatContent();
                chatContent34.fontColor = 0;
                chatContent34.character = wenZi7[n11];
                chatContent34.type = 1;
                vector35.addElement(chatContent34);
            }
            this.addColorInfo(vector35);
        }
        byte attributeIndex27 = this.getAttributeIndex((byte) 31, packageObject.bytsAttribute);
        byte attributeIndex28 = this.getAttributeIndex((byte) 36, packageObject.bytsAttribute);
        if (attributeIndex27 != -1) {
            Vector vector36 = new Vector(1, 1);
            StringBuffer sb23 = new StringBuffer();
            sb23.append("Kháng phép:");
            sb23.append(packageObject.shtsAttributeInfo[attributeIndex27]);
            sb23.append("-");
            sb23.append(packageObject.shtsAttributeInfo[attributeIndex28]);
            if (b != 0 && packageObject.bytsAggrandizement[(b == 1) ? 2 : 4] >= 1) {
                int n12 = (packageObject.bytsAggrandizement[(b == 1) ? 2 : 4] * ((packageObject.shtsAttributeInfo[attributeIndex27] + packageObject.shtsAttributeInfo[attributeIndex27]) / 2) * 10 + 50) / 100;
                sb23.append("(");
                sb23.append(n12);
                sb23.append(")");
            }
            String[] wenZi8 = StringManager.wenZi(sb23.toString(), StringManager.getNewLineW());
            for (int n13 = 0; n13 < wenZi8.length; n13 = (byte) (n13 + 1)) {
                ChatContent chatContent35 = new ChatContent();
                chatContent35.fontColor = 0;
                chatContent35.character = wenZi8[n13];
                chatContent35.type = 1;
                vector36.addElement(chatContent35);
            }
            this.addColorInfo(vector36);
        }
        Vector soilFsts = this.getSoilFsts(packageObject.bytsAggrandizement, packageObject.bytsAttribute, packageObject.shtsAttributeInfo);
        if (soilFsts != null && !soilFsts.isEmpty()) {
            for (int n14 = 0; n14 < soilFsts.size(); n14 = (byte) (n14 + 1)) {
                Vector vector37 = new Vector(1, 1);
                ChatContent chatContent36 = new ChatContent();
                chatContent36.fontColor = 0;
                chatContent36.character = (String) soilFsts.elementAt(n14);
                chatContent36.type = 1;
                vector37.addElement(chatContent36);
                this.addColorInfo(vector37);
            }
        }
        byte attributeIndex29 = this.getAttributeIndex((byte) 81, packageObject.bytsAttribute);
        if (attributeIndex29 != -1) {
            Vector vector38 = new Vector(1, 1);
            StringBuffer sb24 = new StringBuffer();
            sb24.append("Đặt:");
            sb24.append(packageObject.shtsAttributeInfo[attributeIndex29]);
            ChatContent chatContent37 = new ChatContent();
            chatContent37.fontColor = 969327;
            chatContent37.character = sb24.toString();
            chatContent37.type = 1;
            vector38.addElement(chatContent37);
            this.addColorInfo(vector38);
        }
        byte attributeIndex30 = this.getAttributeIndex((byte) 82, packageObject.bytsAttribute);
        if (attributeIndex30 != -1) {
            Vector vector39 = new Vector(1, 1);
            StringBuffer sb25 = new StringBuffer();
            sb25.append("Mô tả: ");
            sb25.append(packageObject.shtsAttributeInfo[attributeIndex30]);
            ChatContent chatContent38 = new ChatContent();
            chatContent38.fontColor = 969327;
            chatContent38.character = sb25.toString();
            chatContent38.type = 1;
            vector39.addElement(chatContent38);
            this.addColorInfo(vector39);
        }
        if (packageObject.intPaiMaiPrice == -1) {
            Vector vector40 = new Vector(1, 1);
            StringBuffer sb26 = new StringBuffer();
            sb26.append("Giá bán:");
            sb26.append(packageObject.intPrice);
            sb26.append("Xu");
            ChatContent chatContent39 = new ChatContent();
            chatContent39.fontColor = 0;
            chatContent39.character = sb26.toString();
            chatContent39.type = 1;
            vector40.addElement(chatContent39);
            this.addColorInfo(vector40);
        }
        if (packageObject.strExplain != null && !packageObject.strExplain.equals("")) {
            Vector vector41 = new Vector(1, 1);
            ChatContent chatContent40 = new ChatContent();
            chatContent40.fontColor = 0;
            chatContent40.character = "";
            chatContent40.type = 1;
            vector41.addElement(chatContent40);
            this.addColorInfo(vector41);
            Vector vector42 = new Vector(1, 1);
            String[] wenZi9 = StringManager.wenZi(packageObject.strExplain, StringManager.getNewLineW());
            for (int n15 = 0; n15 < wenZi9.length; n15 = (byte) (n15 + 1)) {
                ChatContent chatContent41 = new ChatContent();
                chatContent41.fontColor = 4217288;
                chatContent41.character = wenZi9[n15];
                chatContent41.type = 1;
                vector42.addElement(chatContent41);
            }
            this.addColorInfo(vector42);
        }
    }

    private Vector getBaseAttribute(final byte[] array, final byte[] array2, final short[] array3) {
        Vector vector = new Vector(1, 1);
        byte b = 0;
        if (array != null) {
            b = (byte) ((array.length > 4) ? 2 : ((array.length == 4) ? 1 : 0));
        }
        byte b2 = (byte) ((b == 2) ? 2 : 0);
        byte b3 = (byte) ((b == 2) ? 5 : ((b == 1) ? 3 : 1));
        byte attributeIndex = this.getAttributeIndex((byte) 7, array2);
        if (attributeIndex != -1) {
            StringBuffer sb = new StringBuffer();
            sb.append("+");
            sb.append(array3[attributeIndex]);
            if (b != 0 && array[b2] >= 1) {
                int n = (array[b2] * array3[attributeIndex] * 10 + 50) / 100;
                sb.append("(");
                sb.append(n);
                sb.append(")");
            }
            sb.append("Độ bền");
            vector.addElement(sb.toString());
        }
        byte attributeIndex2 = this.getAttributeIndex((byte) 8, array2);
        if (attributeIndex2 != -1) {
            StringBuffer sb2 = new StringBuffer();
            sb2.append("+");
            sb2.append(array3[attributeIndex2]);
            if (b != 0 && array[b2] >= 1) {
                int n2 = (array[b2] * array3[attributeIndex2] * 10 + 50) / 100;
                sb2.append("(");
                sb2.append(n2);
                sb2.append(")");
            }
            sb2.append("Trí");
            vector.addElement(sb2.toString());
        }
        byte attributeIndex3 = this.getAttributeIndex((byte) 9, array2);
        if (attributeIndex3 != -1) {
            StringBuffer sb3 = new StringBuffer();
            sb3.append("+");
            sb3.append(array3[attributeIndex3]);
            if (b != 0 && array[b2] >= 1) {
                int n3 = (array[b2] * array3[attributeIndex3] * 10 + 50) / 100;
                sb3.append("(");
                sb3.append(n3);
                sb3.append(")");
            }
            sb3.append("Điện");
            vector.addElement(sb3.toString());
        }
        byte attributeIndex4 = this.getAttributeIndex((byte) 10, array2);
        if (attributeIndex4 != -1) {
            StringBuffer sb4 = new StringBuffer();
            sb4.append("+");
            sb4.append(array3[attributeIndex4]);
            if (b != 0 && array[b2] >= 1) {
                int n4 = (array[b2] * array3[attributeIndex4] * 10 + 50) / 100;
                sb4.append("(");
                sb4.append(n4);
                sb4.append(")");
            }
            sb4.append("Tinh thần");
            vector.addElement(sb4.toString());
        }
        byte attributeIndex5 = this.getAttributeIndex((byte) 11, array2);
        if (attributeIndex5 != -1) {
            StringBuffer sb5 = new StringBuffer();
            sb5.append("+");
            sb5.append(array3[attributeIndex5]);
            if (b != 0 && array[b2] >= 1) {
                int n5 = (array[b2] * array3[attributeIndex5] * 10 + 50) / 100;
                sb5.append("(");
                sb5.append(n5);
                sb5.append(")");
            }
            sb5.append("Nhanh");
            vector.addElement(sb5.toString());
        }
        byte attributeIndex6 = this.getAttributeIndex((byte) 12, array2);
        if (attributeIndex6 != -1) {
            StringBuffer sb6 = new StringBuffer();
            sb6.append("+");
            sb6.append(array3[attributeIndex6]);
            if (b != 0 && array[b2] >= 1) {
                int n6 = (array[b2] * array3[attributeIndex6] * 10 + 50) / 100;
                sb6.append("(");
                sb6.append(n6);
                sb6.append(")");
            }
            sb6.append("May mắn");
            vector.addElement(sb6.toString());
        }
        byte attributeIndex7 = this.getAttributeIndex((byte) 13, array2);
        if (attributeIndex7 != -1) {
            StringBuffer sb7 = new StringBuffer();
            sb7.append("+");
            sb7.append(array3[attributeIndex7]);
            if (b != 0 && array[b2] >= 1) {
                int n7 = (array[b2] * array3[attributeIndex7] * 10 + 50) / 100;
                sb7.append("(");
                sb7.append(n7);
                sb7.append(")");
            }
            sb7.append("HP");
            vector.addElement(sb7.toString());
        }
        byte attributeIndex8 = this.getAttributeIndex((byte) 14, array2);
        if (attributeIndex8 != -1) {
            StringBuffer sb8 = new StringBuffer();
            sb8.append("+");
            sb8.append(array3[attributeIndex8]);
            if (b != 0 && array[b2] >= 1) {
                int n8 = (array[b2] * array3[attributeIndex8] * 10 + 50) / 100;
                sb8.append("(");
                sb8.append(n8);
                sb8.append(")");
            }
            sb8.append("MP");
            vector.addElement(sb8.toString());
        }
        byte attributeIndex9 = this.getAttributeIndex((byte) 15, array2);
        if (attributeIndex9 != -1) {
            StringBuffer sb9 = new StringBuffer();
            sb9.append("+");
            sb9.append(array3[attributeIndex9]);
            if (b != 0 && array[b3] >= 1) {
                int n9 = (array[b3] * array3[attributeIndex9] * 10 + 50) / 100;
                sb9.append("(");
                sb9.append(n9);
                sb9.append(")");
            }
            sb9.append("Bạo kích vật lý");
            vector.addElement(sb9.toString());
        }
        byte attributeIndex10 = this.getAttributeIndex((byte) 16, array2);
        if (attributeIndex10 != -1) {
            StringBuffer sb10 = new StringBuffer();
            sb10.append("+");
            sb10.append(array3[attributeIndex10]);
            if (b != 0 && array[b3] >= 1) {
                int n10 = (array[b3] * array3[attributeIndex10] * 10 + 50) / 100;
                sb10.append("(");
                sb10.append(n10);
                sb10.append(")");
            }
            sb10.append("Bạo kích ma pháp");
            vector.addElement(sb10.toString());
        }
        byte attributeIndex11 = this.getAttributeIndex((byte) 17, array2);
        if (attributeIndex11 != -1) {
            StringBuffer sb11 = new StringBuffer();
            sb11.append("+");
            sb11.append(array3[attributeIndex11]);
            if (b != 0 && array[b3] >= 1) {
                int n11 = (array[b3] * array3[attributeIndex11] * 10 + 50) / 100;
                sb11.append("(");
                sb11.append(n11);
                sb11.append(")");
            }
            sb11.append("Tỉ lệ chính xác");
            vector.addElement(sb11.toString());
        }
        byte attributeIndex12 = this.getAttributeIndex((byte) 18, array2);
        if (attributeIndex12 != -1) {
            StringBuffer sb12 = new StringBuffer();
            sb12.append("+");
            sb12.append(array3[attributeIndex12]);
            if (b != 0 && array[b3] >= 1) {
                int n12 = (array[b3] * array3[attributeIndex12] * 10 + 50) / 100;
                sb12.append("(");
                sb12.append(n12);
                sb12.append(")");
            }
            sb12.append("Tỉ lệ né tránh");
            vector.addElement(sb12.toString());
        }
        return vector;
    }

    private Vector getSoilFsts(final byte[] array, final byte[] array2, final short[] array3) {
        byte b = 0;
        if (array != null) {
            b = (byte) ((array.length > 4) ? 2 : ((array.length == 4) ? 1 : 0));
        }
        byte b2 = (byte) ((b == 2) ? 3 : ((b == 1) ? 1 : 2));
        Vector vector = new Vector(1, 1);
        byte attributeIndex = this.getAttributeIndex((byte) 20, array2);
        if (attributeIndex != -1) {
            StringBuffer sb = new StringBuffer();
            sb.append("+");
            sb.append(array3[attributeIndex]);
            if (b != 0 && array[b2] >= 1) {
                int n = (array[b2] * array3[attributeIndex] * 10 + 50) / 100;
                sb.append("(");
                sb.append(n);
                sb.append(")");
            }
            sb.append("Kháng ám ảnh");
            vector.addElement(sb.toString());
        }
        byte attributeIndex2 = this.getAttributeIndex((byte) 21, array2);
        if (attributeIndex2 != -1) {
            StringBuffer sb2 = new StringBuffer();
            sb2.append("+");
            sb2.append(array3[attributeIndex2]);
            if (b != 0 && array[b2] >= 1) {
                int n2 = (array[b2] * array3[attributeIndex2] * 10 + 50) / 100;
                sb2.append("(");
                sb2.append(n2);
                sb2.append(")");
            }
            sb2.append("Kháng thần thánh");
            vector.addElement(sb2.toString());
        }
        byte attributeIndex3 = this.getAttributeIndex((byte) 22, array2);
        if (attributeIndex3 != -1) {
            StringBuffer sb3 = new StringBuffer();
            sb3.append("+");
            sb3.append(array3[attributeIndex3]);
            if (b != 0 && array[b2] >= 1) {
                int n3 = (array[b2] * array3[attributeIndex3] * 10 + 50) / 100;
                sb3.append("(");
                sb3.append(n3);
                sb3.append(")");
            }
            sb3.append("Kháng hỏa");
            vector.addElement(sb3.toString());
        }
        byte attributeIndex4 = this.getAttributeIndex((byte) 23, array2);
        if (attributeIndex4 != -1) {
            StringBuffer sb4 = new StringBuffer();
            sb4.append("+");
            sb4.append(array3[attributeIndex4]);
            if (b != 0 && array[b2] >= 1) {
                int n4 = (array[b2] * array3[attributeIndex4] * 10 + 50) / 100;
                sb4.append("(");
                sb4.append(n4);
                sb4.append(")");
            }
            sb4.append("Kháng thủy");
            vector.addElement(sb4.toString());
        }
        byte attributeIndex5 = this.getAttributeIndex((byte) 24, array2);
        if (attributeIndex5 != -1) {
            StringBuffer sb5 = new StringBuffer();
            sb5.append("+");
            sb5.append(array3[attributeIndex5]);
            if (b != 0 && array[b2] >= 1) {
                int n5 = (array[b2] * array3[attributeIndex5] * 10 + 50) / 100;
                sb5.append("(");
                sb5.append(n5);
                sb5.append(")");
            }
            sb5.append("Kháng lôi");
            vector.addElement(sb5.toString());
        }
        return vector;
    }

    public byte getAttributeIndex(final byte b, final byte[] array) {
        byte b2 = -1;
        if (array != null) {
            for (byte b3 = 0; b3 < array.length; ++b3) {
                if (array[b3] == b) {
                    b2 = b3;
                    break;
                }
            }
        }
        return b2;
    }

    public short getAttributeValue(final byte b, final byte[] array, final short[] array2) {
        byte attributeIndex = getInstance().getAttributeIndex(b, array);
        short n;
        if (attributeIndex == -1) {
            n = 0;
        } else {
            n = array2[attributeIndex];
        }
        return n;
    }

    private String getEquipPlace(final byte b) {
        switch (b) {
            case 0: {
                return "Đầu";
            }
            case 1: {
                return "Ngực";
            }
            case 2: {
                return "Tay";
            }
            case 3: {
                return "Chân";
            }
            case 4: {
                return "Vũ khí";
            }
            case 5: {
                return "Cổ";
            }
            case 6: {
                return "Nhẫn";
            }
            case 7: {
                return "Vòng";
            }
            default: {
                return "Trống";
            }
        }
    }

    private void initTime() {
        Date time = new Date();
        Calendar instance = Calendar.getInstance();
        instance.setTime(time);
        this.bytHour = (byte) instance.get(11);
        this.bytMin = (byte) instance.get(12);
        this.bytSec = (byte) instance.get(13);
        this.timeStrBuff();
    }

    private void timeStrBuff() {
        StringBuffer sb = new StringBuffer();
        sb.append((this.bytHour < 10) ? ("0" + this.bytHour) : new StringBuffer().append(this.bytHour).toString());
        sb.append(" : ");
        sb.append((this.bytMin < 10) ? ("0" + this.bytMin) : new StringBuffer().append(this.bytMin).toString());
        sb.append(" : ");
        sb.append((this.bytSec < 10) ? ("0" + this.bytSec) : new StringBuffer().append(this.bytSec).toString());
    }

    private void timeLogic(final int n) {
        this.shtOneSec += (short) n;
        if (this.shtOneSec >= 1000) {
            this.shtOneSec -= 1000;
            ++this.bytSec;
            if (this.bytSec >= 60) {
                this.bytSec -= 60;
                ++this.bytMin;
                if (this.bytMin >= 60) {
                    this.bytMin -= 60;
                    ++this.bytHour;
                    if (this.bytHour >= 24) {
                        this.bytHour -= 24;
                    }
                }
            }
            if (Rms.bytShowMap == 2) {
                this.timeStrBuff();
            }
        }
    }

    private void setSuitAttribute(final Hashtable hashtable, final PackageObject packageObject, final boolean b) {
        if (packageObject.strSuitName == null) {
            return;
        }
        Vector vector = new Vector(1, 1);
        ChatContent chatContent = new ChatContent();
        chatContent.fontColor = 0;
        chatContent.character = "";
        chatContent.type = 1;
        vector.addElement(chatContent);
        this.addColorInfo(vector);
        Vector vector2 = new Vector(1, 1);
        ChatContent chatContent2 = new ChatContent();
        chatContent2.fontColor = packageObject.intColor;
        chatContent2.character = packageObject.strSuitName;
        chatContent2.type = 1;
        vector2.addElement(chatContent2);
        this.addColorInfo(vector2);
        int n = 0;
        Vector vector3 = new Vector(1, 1);
        if (b) {
            for (byte b2 = 0; b2 < 4; ++b2) {
                if (hashtable.containsKey(new Integer(b2))) {
                    String strSuitName = ((PackageObject) hashtable.get(new Integer(b2))).strSuitName;
                    if (strSuitName != null && strSuitName.equals(packageObject.strSuitName)) {
                        ChatContent chatContent3 = new ChatContent();
                        chatContent3.fontColor = 0;
                        chatContent3.character = this.getEquipPlace(b2);
                        chatContent3.type = (byte) ((b2 == 3) ? 1 : 0);
                        vector3.addElement(chatContent3);
                        n = (byte) (n + 1);
                    } else {
                        ChatContent chatContent4 = new ChatContent();
                        chatContent4.fontColor = 6579300;
                        chatContent4.character = this.getEquipPlace(b2);
                        chatContent4.type = (byte) ((b2 == 3) ? 1 : 0);
                        vector3.addElement(chatContent4);
                    }
                } else {
                    ChatContent chatContent5 = new ChatContent();
                    chatContent5.fontColor = 6579300;
                    chatContent5.character = this.getEquipPlace(b2);
                    chatContent5.type = (byte) ((b2 == 3) ? 1 : 0);
                    vector3.addElement(chatContent5);
                }
                if (b2 != 3) {
                    ChatContent chatContent6 = new ChatContent();
                    chatContent6.fontColor = 0;
                    chatContent6.character = "/";
                    chatContent6.type = 0;
                    vector3.addElement(chatContent6);
                } else {
                    this.addColorInfo(vector3);
                }
            }
        } else {
            for (byte b3 = 0; b3 < 4; ++b3) {
                ChatContent chatContent7 = new ChatContent();
                chatContent7.fontColor = ((packageObject.byteEquipType == b3) ? 0 : 6579300);
                chatContent7.character = this.getEquipPlace(b3);
                chatContent7.type = (byte) ((b3 == 3) ? 1 : 0);
                vector3.addElement(chatContent7);
                if (b3 != 3) {
                    ChatContent chatContent8 = new ChatContent();
                    chatContent8.fontColor = 0;
                    chatContent8.character = "/";
                    chatContent8.type = 0;
                    vector3.addElement(chatContent8);
                } else {
                    this.addColorInfo(vector3);
                }
            }
        }
        String[] wenZi = StringManager.wenZi(packageObject.strSuitInfo, StringManager.getNewLineW());
        for (int i = 0; i < wenZi.length; i = (byte) (i + 1)) {
            Vector vector4 = new Vector(1, 1);
            ChatContent chatContent9 = new ChatContent();
            chatContent9.fontColor = ((n == 4) ? 4217288 : 6579300);
            chatContent9.character = wenZi[i];
            chatContent9.type = 1;
            vector4.addElement(chatContent9);
            this.addColorInfo(vector4);
        }
    }

    public void setOnlineTime(final long n, final long lonFeeOnlineTime, final long lonFeeMonsterTime) {
        this.lonFeeOnlineTime = lonFeeOnlineTime;
        this.lonFeeNowTime = System.currentTimeMillis();
        this.lonFeeMonsterTime = lonFeeMonsterTime;
        int n2 = (int) (n / 60000L);
        int n3 = n2 / 60;
        int n4 = n2 % 60;
        StringBuffer sb = new StringBuffer();
        if (n3 != 0) {
            sb.append(n3);
            sb.append("Giờ");
        }
        sb.append(n4);
        sb.append("Điểm");
        this.strFeeOfflineTime = sb.toString();
    }

    public String getFeeTime(final long n) {
        if (n <= 0L) {
            return null;
        }
        int n2 = (int) ((n - (System.currentTimeMillis() - this.lonFeeNowTime)) / 60000L);
        int n3 = n2 / 60;
        int n4 = n2 % 60;
        StringBuffer sb = new StringBuffer();
        if (n3 != 0) {
            sb.append(n3);
            sb.append("Giờ");
        }
        sb.append(n4);
        sb.append("Điểm");
        return sb.toString();
    }

    public void initShortcutKeys(final int[][] array, final short[] array2, final String[] array3) {
        Param.getInstance().intShortcutKeys = new int[26][6];
        Param.getInstance().imgShortcutKeys = new Image[26];
        Param.getInstance().strShortcutKeys = new String[26];
        Param.getInstance().shtShortcutKeysId = new short[26];
        boolean b = false;
        for (int i = 0; i < 26; i = (byte) (i + 1)) {
            if (array[i][4] == 2) {
                if (Param.getInstance().vSkillList != null) {
                    b = true;
                } else {
                    array[i][4] = 0;
                }
            } else if (array[i][4] == 3) {
                Param.getInstance().shtShortcutKeysId[i] = array2[i];
                Param.getInstance().imgShortcutKeys[i] = ImageManager.CreateImage(new StringBuffer(String.valueOf(array2[i])).toString(), GameControl.STRING_IMAGE_PROP());
                Param.getInstance().strShortcutKeys[i] = array3[i];
            }
            for (int j = 0; j < array[i].length; j = (byte) (j + 1)) {
                Param.getInstance().intShortcutKeys[i][j] = array[i][j];
            }
        }
        if (b) {
            this.updateSkillShortcutKeys();
        }
        this.updateSystemShortcutKeys();
    }

    public void updateSkillShortcutKeys() {
        if (Param.getInstance().vSkillList != null) {
            for (int i = 0; i < Param.getInstance().vSkillList.size(); ++i) {
                ((SkillObject) Param.getInstance().vSkillList.elementAt(i)).bytKey = -1;
            }
        }
        Label_0232:
        for (byte b = 0; b < 26; ++b) {
            if (Param.getInstance().intShortcutKeys[b][4] == 2) {
                for (int j = 0; j < Param.getInstance().vSkillList.size(); ++j) {
                    SkillObject skillObject = (SkillObject) Param.getInstance().vSkillList.elementAt(j);
                    if (skillObject.intId == Param.getInstance().intShortcutKeys[b][0]) {
                        Param.getInstance().intShortcutKeys[b][3] = j;
                        Param.getInstance().intShortcutKeys[b][4] = 2;
                        Param.getInstance().intShortcutKeys[b][1] = 0;
                        Param.getInstance().intShortcutKeys[b][2] = 0;
                        Param.getInstance().imgShortcutKeys[b] = ImageManager.CreateImage(new StringBuffer(String.valueOf(skillObject.shtIcon)).toString(), GameControl.STRING_IMAGE_PROP());
                        Param.getInstance().shtShortcutKeysId[b] = skillObject.shtIcon;
                        Param.getInstance().strShortcutKeys[b] = skillObject.strName;
                        skillObject.bytKey = b;
                        continue Label_0232;
                    }
                }
                this.setCleanShortcutKeys(b);
            }
        }
    }

    public void updateSystemShortcutKeys() {
        for (int i = 0; i < 26; i = (byte) (i + 1)) {
            if (Param.getInstance().intShortcutKeys[i][4] == 1) {
                Param.getInstance().shtShortcutKeysId[i] = this.PicID((byte) Param.getInstance().intShortcutKeys[i][0]);
                Param.getInstance().imgShortcutKeys[i] = ImageManager.CreateImage(new StringBuffer(String.valueOf(this.PicID((byte) Param.getInstance().intShortcutKeys[i][0]))).toString(), GameControl.STRING_IMAGE_SYS());
                Param.getInstance().strShortcutKeys[i] = this.getSystemKeyName((byte) Param.getInstance().intShortcutKeys[i][0]);
            }
        }
    }

    public void setCleanShortcutKeys(final byte b) {
        if (Param.getInstance().intShortcutKeys[b][4] == 2) {
            int n = Param.getInstance().intShortcutKeys[b][3];
            if (n < Param.getInstance().vSkillList.size() && Param.getInstance().intShortcutKeys[b][0] == ((SkillObject) Param.getInstance().vSkillList.elementAt(n)).intId) {
                ((SkillObject) Param.getInstance().vSkillList.elementAt(n)).bytKey = -1;
            }
        } else if (Param.getInstance().intShortcutKeys[b][4] == 3 && (this.checkMeMenuUi(36) || this.checkMeMenuUi(38) || this.checkMeMenuUi(39))) {
            Enumeration elements = Param.getInstance().hPackage.elements();
            while (elements.hasMoreElements()) {
                PackageObject packageObject = (PackageObject) elements.nextElement();
                if (packageObject.bytKey == b) {
                    packageObject.bytKey = -1;
                }
            }
        }
        Param.getInstance().intShortcutKeys[b][4] = 0;
        Param.getInstance().intShortcutKeys[b][0] = 0;
        Param.getInstance().intShortcutKeys[b][1] = 0;
        Param.getInstance().intShortcutKeys[b][2] = 0;
        Param.getInstance().intShortcutKeys[b][3] = 0;
        Param.getInstance().imgShortcutKeys[b] = null;
        Param.getInstance().shtShortcutKeysId[b] = 0;
        Param.getInstance().strShortcutKeys[b] = null;
    }

    public void setGoodsShortcutKeys(final byte cleanShortcutKeys, final int n, final int n2, final int n3, final int n4, final short n5, final String s, final byte cleanShortcutKeys2) {
        if (cleanShortcutKeys2 != -1) {
            this.setCleanShortcutKeys(cleanShortcutKeys2);
        }
        if (Param.getInstance().intShortcutKeys[cleanShortcutKeys][4] == 2 || (Param.getInstance().intShortcutKeys[cleanShortcutKeys][4] == 3 && (this.checkMeMenuUi(36) || this.checkMeMenuUi(38) || this.checkMeMenuUi(39)))) {
            this.setCleanShortcutKeys(cleanShortcutKeys);
        }
        Param.getInstance().intShortcutKeys[cleanShortcutKeys][0] = n;
        Param.getInstance().intShortcutKeys[cleanShortcutKeys][1] = n2;
        Param.getInstance().intShortcutKeys[cleanShortcutKeys][2] = n3;
        Param.getInstance().intShortcutKeys[cleanShortcutKeys][3] = n4;
        if (n5 != -1) {
            Param.getInstance().shtShortcutKeysId[cleanShortcutKeys] = n5;
            Param.getInstance().imgShortcutKeys[cleanShortcutKeys] = ImageManager.CreateImage(new StringBuffer(String.valueOf(n5)).toString(), GameControl.STRING_IMAGE_PROP());
        }
        Param.getInstance().strShortcutKeys[cleanShortcutKeys] = s;
        Param.getInstance().intShortcutKeys[cleanShortcutKeys][4] = 3;
    }

    public void updateGoodsShortcutKeys(final byte b, final int n, final int n2, final int n3) {
        if (Param.getInstance().intShortcutKeys[b][4] == 3) {
            Param.getInstance().intShortcutKeys[b][1] = n;
            Param.getInstance().intShortcutKeys[b][2] = n2;
            Param.getInstance().intShortcutKeys[b][3] = n3;
        }
    }

    public void setSkillShortcutKeys(final byte cleanShortcutKeys, final int n, final short n2, final String s, final int n3, final byte cleanShortcutKeys2) {
        if (cleanShortcutKeys2 != -1) {
            this.setCleanShortcutKeys(cleanShortcutKeys2);
        }
        this.setCleanShortcutKeys(cleanShortcutKeys);
        Param.getInstance().intShortcutKeys[cleanShortcutKeys][0] = n;
        Param.getInstance().intShortcutKeys[cleanShortcutKeys][1] = 0;
        Param.getInstance().intShortcutKeys[cleanShortcutKeys][2] = 0;
        if (n3 != -1) {
            Param.getInstance().intShortcutKeys[cleanShortcutKeys][3] = n3;
        }
        Param.getInstance().shtShortcutKeysId[cleanShortcutKeys] = n2;
        Param.getInstance().imgShortcutKeys[cleanShortcutKeys] = ImageManager.CreateImage(new StringBuffer(String.valueOf(n2)).toString(), GameControl.STRING_IMAGE_PROP());
        Param.getInstance().strShortcutKeys[cleanShortcutKeys] = s;
        Param.getInstance().intShortcutKeys[cleanShortcutKeys][4] = 2;
    }

    public void setSystemShortcutKeys(final byte cleanShortcutKeys, final byte b) {
        for (byte cleanShortcutKeys2 = 0; cleanShortcutKeys2 < 26; ++cleanShortcutKeys2) {
            if (Param.getInstance().intShortcutKeys[cleanShortcutKeys2][4] == 1 && Param.getInstance().intShortcutKeys[cleanShortcutKeys2][0] == b) {
                this.setCleanShortcutKeys(cleanShortcutKeys2);
                break;
            }
        }
        this.setCleanShortcutKeys(cleanShortcutKeys);
        Param.getInstance().intShortcutKeys[cleanShortcutKeys][0] = b;
        Param.getInstance().intShortcutKeys[cleanShortcutKeys][1] = 0;
        Param.getInstance().intShortcutKeys[cleanShortcutKeys][2] = 0;
        Param.getInstance().intShortcutKeys[cleanShortcutKeys][3] = 0;
        Param.getInstance().shtShortcutKeysId[cleanShortcutKeys] = (byte) Param.getInstance().intShortcutKeys[cleanShortcutKeys][0];
        Param.getInstance().imgShortcutKeys[cleanShortcutKeys] = ImageManager.CreateImage(new StringBuffer(String.valueOf(this.PicID((byte) Param.getInstance().intShortcutKeys[cleanShortcutKeys][0]))).toString(), GameControl.STRING_IMAGE_SYS());
        Param.getInstance().strShortcutKeys[cleanShortcutKeys] = this.getSystemKeyName(b);
        Param.getInstance().intShortcutKeys[cleanShortcutKeys][4] = 1;
    }

    public void setMoveShortcutKeys(final byte[] array) {
        for (int i = 0; i < 4; i = (byte) (i + 1)) {
            if (DCanvas.getInstance().intMoveKey[i] != DCanvas.getInstance().getIntShortcutKey(array[i])) {
                for (int j = 0; j < 4; j = (byte) (j + 1)) {
                    for (byte cleanShortcutKeys = 0; cleanShortcutKeys < 13; ++cleanShortcutKeys) {
                        if (Param.getInstance().intShortcutKeys[cleanShortcutKeys][4] == 1 && Param.getInstance().intShortcutKeys[cleanShortcutKeys][0] == 1 + j) {
                            this.setCleanShortcutKeys(cleanShortcutKeys);
                            break;
                        }
                    }
                    this.setCleanShortcutKeys(array[j]);
                    this.setCleanShortcutKeys((byte) (array[j] + 13));
                    this.setSystemShortcutKeys(array[j], (byte) (1 + j));
                    DCanvas.getInstance().intMoveKey[j] = DCanvas.getInstance().getIntShortcutKey(array[j]);
                }
                DCanvas.getInstance().updateMoveKey();
                NetSend.getInstance().sendMoveShortcutKeys(array);
                return;
            }
        }
    }

    public String getStringShortcutKey(final byte b, final boolean b2) {
        String s = "";
        byte b3 = (byte) (b % 13);
        String s2 = null;
        switch (b3) {
            case -1: {
                return "";
            }
            case 9: {
                s2 = "Ngày" + s;
                break;
            }
            case 10: {
                s2 = "Trái" + s;
                break;
            }
            case 11: {
                s2 = "Phải" + s;
                break;
            }
            case 12: {
                s2 = "Tiếp theo" + s;
                break;
            }
            default: {
                s2 = String.valueOf(b3 + 1) + s;
                break;
            }
        }
        return s2;
    }

    public String getTeamRemark(final TeamObject teamObject) {
        String string = "";
        if (teamObject.bytTeamId != 0) {
            string = String.valueOf(teamObject.bytTeamId) + "Đội";
        }
        return string;
    }

    public void setNextMenu(final byte b, final String s) {
        GameControl.getInstance().delUIbase(4);
        GameControl.getInstance().CreateState((byte) 3);
        MenuUI.getInstance().clearMove();
        MenuUI.getInstance().setState(b, s);
    }

    public String getSystemKeyName(final byte b) {
        MenuUI.getInstance().strsFunction = Pram.SystemKeyName;
        for (int i = 0; i < Pram.SystemKeyID.length; ++i) {
            if (Pram.SystemKeyID[i] == b) {
                return Pram.SystemKeyName[i];
            }
        }
        return MenuUI.getInstance().strsFunction[b];
    }

    private byte PicID(final byte b) {
        for (int i = 0; i < Pram.SystemKeyID.length; ++i) {
            if (Pram.SystemKeyID[i] == b) {
                return Pram.KEYFUNCTION_PNG_ID[i];
            }
        }
        return b;
    }

    public void setOldState(final byte b, final byte b2, final byte b3, final byte b4) {
        byte b5 = (byte) (b2 + b3);
        byte[] array = {b, b2, b3, b4};
        if (Param.getInstance().vOptionPlace == null) {
            Param.getInstance().vOptionPlace = new Vector(1, 1);
        }
        Param.getInstance().vOptionPlace.addElement(array);
    }

    public void switchToShopMenu(final byte b, final byte b2, final byte b3, final byte b4, final byte b5) {
        if (Param.getInstance().hMallPackage != null) {
            getInstance().setNextMenu((byte) (-11), Pram.STRING_SHOP_OPTION[0]);
            MenuUI.getInstance().setInfoContent((Hashtable) Param.getInstance().hMallPackage.get(new Integer(0)), Param.getInstance().personalPackage.getCellBoxIndex());
            MenuUI.getInstance().setButton(1, 100, 2);
        } else {
            DCanvas.getInstance().addInformation("Không bán hàng");
        }
        (GameUI.bytNextChargeOldMenu = new byte[5])[0] = b;
        GameUI.bytNextChargeOldMenu[1] = b2;
        GameUI.bytNextChargeOldMenu[2] = b3;
        GameUI.bytNextChargeOldMenu[3] = b4;
        GameUI.bytNextChargeOldMenu[4] = b5;
        GameUI.blnNextChargeMenu = true;
    }

    public void rebackToMenu() {
        if (GameUI.bytNextChargeOldMenu != null) {
            byte b = GameUI.bytNextChargeOldMenu[0];
            byte dialog = GameUI.bytNextChargeOldMenu[1];
            byte b2 = GameUI.bytNextChargeOldMenu[2];
            byte b3 = GameUI.bytNextChargeOldMenu[3];
            byte b4 = GameUI.bytNextChargeOldMenu[4];
            if (b == 4) {
                GameControl.getInstance().delUIbase(3);
                GameControl.getInstance().CreateState((byte) 4);
                SystemMenuUI.getInstance().setState((byte) 0);
                Param.getInstance().vOptionPlace = null;
            } else if (b == 7) {
                GameControl.getInstance().delUIbase(3);
                getInstance().setDialog(dialog);
            } else if (b == 2) {
                GameControl.getInstance().delUIbase(3);
            }
        }
        GameUI.bytNextChargeOldMenu = null;
        GameUI.blnNextChargeMenu = false;
    }

    private void clearAllTarget() {
        if (this.blnUpdateAutoSelect) {
            this.clearAllTarget(GameUI.targetRole);
        }
        if (this.blnUpdateManuSelectAll || this.blnUpdateManuSelectFriends) {
            this.clearAllTarget(GameUI.targetManuRole);
        }
    }

    private void clearAllTarget(final Vector vector) {
        if (vector != null) {
            vector.removeAllElements();
        }
    }

    private void checkTargetRange(final ORole oRole) {
        if (this.blnUpdateAutoSelect) {
            this.checkTargetRange(GameUI.targetRole, oRole, true);
        }
        if (this.blnUpdateManuSelectAll || this.blnUpdateManuSelectFriends) {
            this.checkTargetRange(GameUI.targetManuRole, oRole, false);
        }
    }

    private void checkTargetRange(final Vector vector, final ORole oRole, final boolean b) {
        byte bytType = oRole.bytType;
        if (oRole.intUserId != ORPMe.ME.intUserId && (bytType == 1 || bytType == 3 || bytType == 2 || bytType == 4 || bytType == 5)) {
            targetObj targetObj = new targetObj();
            targetObj.distance = this.getRoleDistance(oRole);
            targetObj.targetID = oRole.intUserId;
            targetObj.targetType = oRole.bytType;
            targetObj.targetName = oRole.strNickName;
            targetObj.targetTileX = oRole.bytTileX;
            targetObj.targetTileY = oRole.bytTileY;
            targetObj.isInsideView = false;
            targetObj.blnSameCountry = oRole.blnCountry;
            targetObj.blnAttack = oRole.blnIsAttack;
            targetObj.priority = this.getTargetPriority(targetObj);
            oRole.getClass();
            oRole.getClass();
            if (this.checkRoleInView(targetObj, b)) {
                int size = vector.size();
                if (size == 0) {
                    vector.addElement(targetObj);
                } else {
                    for (int i = 0; i < size; ++i) {
                        targetObj targetObj2 = (targetObj) vector.elementAt(i);
                        if (targetObj.priority > targetObj2.priority || (targetObj.priority == targetObj2.priority && targetObj.distance < targetObj2.distance)) {
                            vector.insertElementAt(targetObj, i);
                            break;
                        }
                        if (i == size - 1) {
                            vector.addElement(targetObj);
                            break;
                        }
                    }
                }
            }
        }
    }

    public int setSelectTarget(final Vector vector, int n, final int n2, final int n3, final boolean b) {
        int size = vector.size();
        int n4;
        if (size == 0) {
            if (Param.getInstance().intSelectId >= 0) {
                ORPMe.ME.delSelsectRole();
            }
            n4 = -1;
        } else {
            int n5 = -1;
            int n6 = 49;
            if (n >= size) {
                n = 0;
            }
            if (b) {
                boolean b2 = false;
                for (int i = 0; i < size; ++i) {
                    targetObj targetObj = (targetObj) vector.elementAt(i);
                    if ((i >= n && targetObj.priority >= n2 && n3 == -1) || (n3 > -1 && targetObj.priority <= n3)) {
                        b2 = true;
                    }
                }
                if (!b2) {
                    n = 0;
                }
            }
            for (int j = 0; j < size; ++j) {
                targetObj targetObj2 = (targetObj) vector.elementAt(j);
                if (j >= n) {
                    if (targetObj2.priority >= n2) {
                        byte priority = targetObj2.priority;
                        int distance = targetObj2.distance;
                        if (b) {
                            if ((priority >= n2 && n3 == -1) || (n3 > -1 && priority <= n3)) {
                                n5 = j;
                                break;
                            }
                        } else if (distance < n6) {
                            n5 = j;
                            n6 = distance;
                        }
                    }
                }
            }
            n4 = n5;
            if (n4 != -1) {
                targetObj targetObj3 = (targetObj) vector.elementAt(n5);
                if (targetObj3 != null) {
                    byte targetType = targetObj3.targetType;
                    int targetID = targetObj3.targetID;
                    if (targetType == 2 && Param.getInstance().htRolePlayer != null) {
                        ORPMe.ME.setSelectRole((ORole) Param.getInstance().htRolePlayer.get(new Integer(targetID)));
                    } else if (targetType == 3 && Param.getInstance().htRoleMonster != null) {
                        ORPMe.ME.setSelectRole((ORole) Param.getInstance().htRoleMonster.get(new Integer(targetID)));
                    } else if (targetType == 1 && Param.getInstance().htRoleNPC != null) {
                        ORPMe.ME.setSelectRole((ONpc) Param.getInstance().htRoleNPC.get(new Integer(targetID)));
                    } else if (targetType == 4 && Param.getInstance().htBox != null) {
                        ORPMe.ME.setSelectBox((Box) Param.getInstance().htBox.get(new Integer(targetID)));
                    } else if (targetType == 5 && Param.getInstance().htTask != null) {
                        ORPMe.ME.setSelectTask((Task) Param.getInstance().htTask.get(new Integer(targetID)));
                    }
                }
            }
        }
        return n4;
    }

    private int getRoleDistance(final ORole oRole) {
        return (int) (Common.pow(oRole.bytTileX - ORPMe.ME.bytTileX, 2) + Common.pow(oRole.bytTileY - ORPMe.ME.bytTileY, 2));
    }

    private byte getTargetPriority(final targetObj targetObj) {
        byte b = -1;
        byte targetType = targetObj.targetType;
        if (targetType == 3) {
            b = 3;
        }
        if (targetType == 2) {
            if (targetObj.blnSameCountry) {
                b = 1;
            } else {
                b = 3;
            }
        }
        if (targetType == 1) {
            b = 2;
        }
        if (targetType == 4 || targetType == 5) {
            b = 0;
        }
        return b;
    }

    private boolean checkRoleInView(final targetObj targetObj, final boolean b) {
        return this.checkPosInView(targetObj.targetTileX, targetObj.targetTileY, b);
    }

    public boolean checkPosInView(final byte b, final byte b2, final boolean b3) {
        int n = 7;
        int n2 = 7;
        if (!b3) {
            n = 14;
            n2 = 14;
        }
        return Common.judgeIsInterrupt(this.getMeView(n, n2, b3), b, 1, b2, 1, 0);
    }

    private int[] getMeView(final int n, final int n2, final boolean b) {
        int[] array = new int[4];
        byte bytTileX = ORPMe.ME.bytTileX;
        byte bytTileY = ORPMe.ME.bytTileY;
        if (ORPMe.ME.bytDirection == 1) {
            if (b) {
                array[0] = bytTileX - n / 2;
                array[1] = bytTileY - n2;
            } else {
                array[0] = bytTileX - n / 2;
                array[1] = bytTileY - n2 / 2;
            }
            array[2] = array[0] + n;
            array[3] = array[1] + n2;
        } else if (ORPMe.ME.bytDirection == 2) {
            if (b) {
                array[0] = bytTileX - n / 2;
                array[1] = bytTileY;
            } else {
                array[0] = bytTileX - n / 2;
                array[1] = bytTileY - n2 / 2;
            }
            array[2] = array[0] + n;
            array[3] = array[1] + n2;
        } else if (ORPMe.ME.bytDirection == 3) {
            if (b) {
                array[0] = bytTileX - n2;
                array[1] = bytTileY - n / 2;
            } else {
                array[0] = bytTileX - n2 / 2;
                array[1] = bytTileY - n / 2;
            }
            array[2] = array[0] + n2;
            array[3] = array[1] + n;
        } else if (ORPMe.ME.bytDirection == 4) {
            if (b) {
                array[0] = bytTileX;
                array[1] = bytTileY - n2 / 2;
            } else {
                array[0] = bytTileX - n / 2;
                array[1] = bytTileY - n2 / 2;
            }
            array[2] = array[0] + n2;
            array[3] = array[1] + n;
        }
        array[0] = Math.max(array[0], Param.getInstance().CAMERAX / 16);
        array[1] = Math.max(array[1], Param.getInstance().CAMERAY / 16);
        array[2] = Math.min(array[2], (Param.getInstance().CAMERAX + Map.getInstance().shtMapImgWidth) / 16);
        array[3] = Math.min(array[3], (Param.getInstance().CAMERAY + Map.getInstance().shtMapImgHeight) / 16);
        if (!b) {
            array[0] = Param.getInstance().CAMERAX / 16;
            array[1] = Param.getInstance().CAMERAY / 16;
            array[2] = (Param.getInstance().CAMERAX + Map.getInstance().shtMapImgWidth) / 16;
            array[3] = (Param.getInstance().CAMERAY + Map.getInstance().shtMapImgHeight) / 16;
        }
        array[2] -= array[0];
        array[3] -= array[1];
        return array;
    }

    public void printTargetINfo(final Vector vector, final boolean b) {
        if (Macro.BLN_TARGET_SELECT_DEBUG) {
            DebugFrame.getInstance().logIn("..............TargetInfo....................");
            DebugFrame.getInstance().logIn("Content:" + (b ? "View" : "Manu"));
            for (int size = vector.size(), i = 0; i < size; ++i) {
                targetObj targetObj = (targetObj) vector.elementAt(i);
                DebugFrame.getInstance().logIn("Index" + i + "/" + targetObj.targetName + "/" + targetObj.targetID);
                DebugFrame.getInstance().logIn("Distance: " + targetObj.distance);
                DebugFrame.getInstance().logIn("Priority: " + targetObj.priority);
            }
        }
    }

    class targetObj {

        public int targetID;
        public byte targetType;
        public String targetName;
        public boolean blnSameCountry;
        public boolean blnAttack;
        public byte targetTileX;
        public byte targetTileY;
        public int distance;
        public boolean isInsideView;
        public byte priority;
    }
}
