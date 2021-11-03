// 
// Decompiled by Procyon v0.5.36
// 
package base;

import face.FullInfo;
import model.ORPMe;
import network.NetParse;
import network.NetManager;
import game.CMidlet;
import network.FakeServer;
import java.util.Hashtable;
import common.DrawBase;
import model.Map;
import common.ScrollText;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Displayable;
import means.ImageManager;
import network.NetSend;
import means.Rms;
import face.CueUI;
import face.LoadingUI;
import face.LandUI;
import face.DialogUI;
import face.SystemMenuUI;
import face.HandleUI;
import face.MenuUI;
import model.ResPoolLevelFunction;
import model.ResPoolFactory;
import face.GameUI;
import javax.microedition.lcdui.Image;
import means.QSpriteData;

public class GameControl {

    public QSpriteData m_data;
    public static Image spriteImg;
    public static String pathImg;
    private static GameControl con;
    public byte[] bytGamePaint;
    public static boolean blnIsRun;
    public boolean isPhone;

    static {
        GameControl.spriteImg = null;
        GameControl.pathImg = "";
    }

    private GameControl() {
        this.m_data = new QSpriteData();
        this.bytGamePaint = new byte[1];
        GameControl.con = this;
    }

    public static GameControl getInstance() {
        if (GameControl.con == null) {
            GameControl.con = new GameControl();
        }
        return GameControl.con;
    }

    public void CreateState(final byte _state) {
        try {
            DCanvas.getInstance().clearKey();
            DCanvas.getInstance().clearPointerKey();
            switch (_state) {
                case 2: {
                    (DCanvas.getInstance().UGameMain = new GameUI()).init();
                    ResPoolFactory.getInstance().ClearPool(3);
                    ResPoolFactory.getInstance().ClearPool(4);
                    ResPoolFactory.getInstance().ClearPool(7);
                    ResPoolFactory.getInstance().ClearPool(5);
                    ResPoolFactory.getInstance().ClearPool(6);
                    ResPoolLevelFunction.getInstance().cleanAllLevelFunctionData();
                    this.setGameState(_state);
                    this.setFocus();
                    break;
                }
                case 3:
                case 9: {
                    (DCanvas.getInstance().UMenu = new MenuUI()).init();
                    this.setGameState(MenuUI.getInstance().bytDelState = _state);
                    this.setFocus();
                    break;
                }
                case 5:
                case 8:
                case 10: {
                    (DCanvas.getInstance().UHandle = new HandleUI()).init();
                    this.setGameState(_state);
                    this.setFocus();
                    break;
                }
                case 4: {
                    (DCanvas.getInstance().USystemMenu = new SystemMenuUI()).init();
                    this.setGameState(_state);
                    this.setFocus();
                    break;
                }
                case 7: {
                    (DCanvas.getInstance().UDialog = new DialogUI()).init();
                    this.setGameState(_state);
                    this.setFocus();
                    break;
                }
                case 1: {
                    (DCanvas.getInstance().UGameMain = new LandUI()).init();
                    this.setGameState(_state);
                    this.setFocus();
                    break;
                }
                case 6: {
                    (DCanvas.getInstance().ULoading = new LoadingUI()).init();
                    this.setGameState(_state);
                    this.setFocus();
                    break;
                }
                case 11: {
                    (DCanvas.getInstance().UCue = new CueUI()).init();
                    this.setGameState(_state);
                    this.setFocus();
                    break;
                }
            }
            if (this.getCurStateOperation(_state) == 4) {
                this.getCurrentOperation();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private byte getCurrentOperation() {
        byte opretiorn = 0;
        final boolean isFind = false;
        if (getInstance().bytGamePaint != null) {
            for (int i = getInstance().bytGamePaint.length - 1; i >= 0 && !isFind; --i) {
                if (i >= getInstance().bytGamePaint.length) {
                    break;
                }
                final byte curState = getInstance().bytGamePaint[i];
                final byte tmpOpera = this.getCurStateOperation(curState);
                if (opretiorn < tmpOpera) {
                    opretiorn = tmpOpera;
                }
            }
        }
        return opretiorn;
    }

    public byte getCurStateOperation(final byte curState) {
        byte opretiorn = 0;
        if (curState >= 1 && curState <= 11) {
            for (int opera = Macro.OPERATION_TYPE.length - 1; opera >= 0; --opera) {
                for (int state = Macro.OPERATION_TYPE[opera].length - 1; state >= 0; --state) {
                    if (curState == Macro.OPERATION_TYPE[opera][state]) {
                        opretiorn = (byte) (opera + 1);
                        break;
                    }
                }
            }
        }
        return opretiorn;
    }

    public void setGameState(final byte _state) {
        try {
            if (this.bytGamePaint[0] == 0) {
                this.bytGamePaint[0] = _state;
                return;
            }
            for (int i = 0; i < this.bytGamePaint.length; ++i) {
                if (this.bytGamePaint[i] == _state) {
                    return;
                }
            }
            final byte[] _temp = this.bytGamePaint;
            System.arraycopy(_temp, 0, this.bytGamePaint = new byte[_temp.length + 1], 0, _temp.length);
            for (int j = 0; j < this.bytGamePaint.length; ++j) {
                if (_state < this.bytGamePaint[j]) {
                    for (int l = this.bytGamePaint.length - 1; l > j; --l) {
                        this.bytGamePaint[l] = this.bytGamePaint[l - 1];
                    }
                    this.bytGamePaint[j] = _state;
                    return;
                }
            }
            this.bytGamePaint[this.bytGamePaint.length - 1] = _state;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setFocus() {
        try {
            if (DCanvas.getInstance().UDialog != null) {
                DCanvas.getInstance().UDialog.isThis(false);
            }
            if (DCanvas.getInstance().UHandle != null) {
                DCanvas.getInstance().UHandle.isThis(false);
            }
            if (DCanvas.getInstance().UMenu != null) {
                DCanvas.getInstance().UMenu.isThis(false);
            }
            if (DCanvas.getInstance().USystemMenu != null) {
                DCanvas.getInstance().USystemMenu.isThis(false);
            }
            if (DCanvas.getInstance().ULoading != null) {
                DCanvas.getInstance().ULoading.isThis(false);
            }
            if (DCanvas.getInstance().UGameMain != null) {
                DCanvas.getInstance().UGameMain.isThis(false);
            }
            if (DCanvas.getInstance().UCue != null) {
                DCanvas.getInstance().UCue.isThis(false);
            }
            switch (this.bytGamePaint[this.bytGamePaint.length - 1]) {
                case 1:
                case 2: {
                    DCanvas.getInstance().UGameMain.isThis(true);
                    break;
                }
                case 3:
                case 9: {
                    DCanvas.getInstance().UMenu.isThis(true);
                    break;
                }
                case 7: {
                    DCanvas.getInstance().UDialog.isThis(true);
                    break;
                }
                case 6: {
                    DCanvas.getInstance().ULoading.isThis(true);
                    break;
                }
                case 4: {
                    DCanvas.getInstance().USystemMenu.isThis(true);
                    break;
                }
                case 5:
                case 8:
                case 10: {
                    DCanvas.getInstance().UHandle.isThis(true);
                    break;
                }
                case 11: {
                    DCanvas.getInstance().UCue.isThis(true);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startGame() {
        Rms.loadSet();
        new Param();
        new DCanvas();
        new NetSend();
        ImageManager.getInstance();
        getInstance().checkLevelFunction();
        GameControl.blnIsRun = true;
        Macro.bytGameType = 0;
        Macro.bytGameTypeReal = 0;
        new Thread(DCanvas.getInstance()).start();
        this.CreateState((byte) 1);
        LandUI.getInstance().setState((byte) 0);
        this.setScreen((Displayable) DCanvas.getInstance());
    }

    public void initStart() {
        final int[] key = new getSoftKeyCode(DCanvas.getInstance()).getKeylCode();
        DCanvas.getInstance().GAME_KEY_SOFT_LEFT = key[0];
        DCanvas.getInstance().GAME_KEY_SOFT_RIGHT = key[1];
        DCanvas.getInstance().GAME_KEY_OK = key[2];
        DCanvas.getInstance().GAME_KEY_UP = key[3];
        DCanvas.getInstance().GAME_KEY_DOWN = key[4];
        DCanvas.getInstance().GAME_KEY_LEFT = key[5];
        DCanvas.getInstance().GAME_KEY_RIGHT = key[6];
        ScrollText.arrowhead = ImageManager.loadSpriteById(0, ImageManager.EncodespriteId("up", "up"), "up", "up", "ui");
    }

    public void inGame() {
        Macro.bytGameTypeReal = 1;
        Param.getInstance().IMGCLOTHINGMAX = Macro.IMGCLOTHINGMAX[Rms.bytEffect];
        new Map();
        Param.getInstance().imgClothingIndex = new Image[Param.getInstance().IMGCLOTHINGMAX];
        Param.getInstance().shtsClothingId = new short[Param.getInstance().IMGCLOTHINGMAX][2];
        Param.getInstance().imgHeadIndex = new Image[4];
        Param.getInstance().shtsHeadId = new short[4][2];
        Param.getInstance().imgWeaponIndex = new Image[6];
        Param.getInstance().shtsWeaponId = new short[6][2];
        Param.getInstance().sprface = ImageManager.loadSpriteById(0, ImageManager.EncodespriteId("expression", "expression"), "expression", "expression", "ui");
        Param.getInstance().sprRolePortrait = ImageManager.loadSpriteById(3, ImageManager.EncodespriteId("portrait", "portrait"), "portrait", "portrait", "ui");
        Param.getInstance().sprBlueBNum = ImageManager.loadSpriteById(3, ImageManager.EncodespriteId("bnum", "bnum"), "bnum", "bnum", "ingame");
        Param.getInstance().sprRedNum = ImageManager.loadSpriteById(3, ImageManager.EncodespriteId("rnum", "rnum"), "rnum", "rnum", "ingame");
        Param.getInstance().sprGreenNum = ImageManager.loadSpriteById(3, ImageManager.EncodespriteId("gnum", "gnum"), "gnum", "gnum", "ingame");
        Param.getInstance().sprYellowNum = ImageManager.loadSpriteById(3, ImageManager.EncodespriteId("ynum", "ynum"), "ynum", "ynum", "ingame");
        Param.getInstance().sprMiss = ImageManager.loadSpriteById(3, ImageManager.EncodespriteId("miss", "miss"), "miss", "miss", "ingame");
        Param.getInstance().imgWhiteNum = ImageManager.CreateImage("num7", "ingame");
        Param.getInstance().moneyQs = ImageManager.loadSpriteById(1, "money", "money", "money", "ui");
        Param.getInstance().imgHMPNum = ImageManager.CreateImage("num10", "ui");
        Param.getInstance().imgMoneyNum = ImageManager.CreateImage("num11", "ui");
        Param.getInstance().imgPlusNum = ImageManager.CreateImage("equip_compare2", "ui");
        Param.getInstance().imgMinusNum = ImageManager.CreateImage("equip_compare1", "ui");
        Param.getInstance().intsSkillColor = DrawBase.getRGB(16, 16, -1721473948);
        Param.getInstance().intsNumberBackColor = DrawBase.getRGB(4, 6, 1711276032);
        Param.getInstance().intsChatColor = DrawBase.getRGB(16, Macro.FONTHEIGHT, -1308622848);
        Param.getInstance().hPackageEquip = new Hashtable(8);
        Param.getInstance().blueSelect = ImageManager.loadSpriteById(3, ImageManager.EncodespriteId("select", "bselect"), "select", "bselect", "ingame");
        Param.getInstance().redSelect = ImageManager.loadSpriteById(3, ImageManager.EncodespriteId("select", "rselect"), "select", "rselect", "ingame");
        Param.getInstance().sprShadow = ImageManager.loadSpriteById(3, ImageManager.EncodespriteId("shadow", "shadow"), "shadow", "shadow", "ingame");
        Param.getInstance().defaultImg = ImageManager.CreateImage(new StringBuffer(String.valueOf(Param.getInstance().defaultImgId)).toString(), "prop");
        Param.getInstance().hPetPackEquip = new Hashtable(4);
        Param.getInstance().intShortcutKeys = new int[26][6];
        Param.getInstance().imgShortcutKeys = new Image[26];
        Param.getInstance().shtShortcutKeysId = new short[26];
        Param.getInstance().strShortcutKeys = new String[26];
        Param.getInstance().intShortcutKeys[9][4] = 1;
        Param.getInstance().intShortcutKeys[10][4] = 1;
        Param.getInstance().intShortcutKeys[11][4] = 1;
        Param.getInstance().intShortcutKeys[12][4] = 1;
        Param.getInstance().intShortcutKeys[9][0] = 1;
        Param.getInstance().intShortcutKeys[10][0] = 2;
        Param.getInstance().intShortcutKeys[11][0] = 3;
        Param.getInstance().intShortcutKeys[12][0] = 4;
        DCanvas.getInstance().updateMoveKey();
        Rms.strsMacroChat = Rms.loadMacroChat();
        this.initGame();
    }

    public short initCreateNpc(final short _num) {
        if (Param.getInstance().htRoleNPC == null) {
            Param.getInstance().htRoleNPC = new Hashtable();
            if (Macro.BLN_ACCERATE_NPC_TASK) {
                FakeServer.getInstance().clean();
            }
        }
        short _l = 0;
        if (Map.getInstance().bytsNpcXY == null) {
            Map.getInstance().bytsNpcXY = new byte[_num][2];
        } else {
            final byte[][] _tempXY = Map.getInstance().bytsNpcXY;
            _l = (short) Map.getInstance().bytsNpcXY.length;
            System.arraycopy(_tempXY, 0, Map.getInstance().bytsNpcXY = new byte[_tempXY.length + _num][2], 0, _tempXY.length);
        }
        return _l;
    }

    public void initGame() {
        for (int i = 0; i < Param.getInstance().IMGCLOTHINGMAX; ++i) {
            Param.getInstance().imgClothingIndex[i] = null;
            Param.getInstance().shtsClothingId[i][0] = -1;
            Param.getInstance().shtsClothingId[i][1] = 0;
        }
        for (int i = 0; i < 4; ++i) {
            Param.getInstance().imgHeadIndex[i] = null;
            Param.getInstance().shtsHeadId[i][0] = -1;
            Param.getInstance().shtsHeadId[i][1] = 0;
        }
        for (int i = 0; i < 6; ++i) {
            Param.getInstance().imgWeaponIndex[i] = null;
            Param.getInstance().shtsWeaponId[i][0] = -1;
            Param.getInstance().shtsWeaponId[i][1] = 0;
        }
        Param.getInstance().shtsNewNPCId = null;
        Param.getInstance().shtsNewMonsterId = null;
        DCanvas.getInstance().vInformation = null;
        Param.getInstance().htRoleMonster = null;
        Param.getInstance().htRoleNPC = null;
        Param.getInstance().htRolePet = null;
        Param.getInstance().htAnimal = null;
        Param.getInstance().htDecorative = null;
        Param.getInstance().htTask = null;
        Param.getInstance().imgPetIndex = null;
        Param.getInstance().shtsPetId = null;
        Param.getInstance().htNpcConvoy = null;
        Param.getInstance().bytShieldNum = 0;
        Param.getInstance().imgShield = null;
        Param.getInstance().shtShieldXY = null;
        Param.getInstance().imginvincibility = null;
        Param.getInstance().shtinvincibilityXY = null;
        Param.getInstance().bytinvincibilityNum = 0;
        Param.getInstance().htBox = null;
        Param.getInstance().imgIntensify = null;
        Param.getInstance().bytsIntensifyId = null;
        Param.getInstance().shtIntensifyXY = null;
        Param.getInstance().bytWeaponXY = null;
        Param.getInstance().shtsNewTaskId = null;
        Param.getInstance().imgDead = null;
        Param.getInstance().bytDeadImgNum = 0;
        Param.getInstance().imgTaskFrame = null;
        Param.getInstance().imgTaskSign = null;
        System.gc();
    }

    public void setScreen(final Displayable _dis) {
        CMidlet.getInstance();
        CMidlet.DisInstance.setCurrent(_dis);
    }

    public void delUIbase(final int _state) {
        try {
            DCanvas.getInstance().clearKey();
            DCanvas.getInstance().clearPointerKey();
            synchronized (this.bytGamePaint) {
                if (this.bytGamePaint.length == 1) {
                    if (this.bytGamePaint[0] != _state) {
                        // monitorexit(this.bytGamePaint)
                        return;
                    }
                    this.bytGamePaint[0] = 0;
                } else {
                    int i = -1;
                    for (int l = 0; l < this.bytGamePaint.length; ++l) {
                        if (this.bytGamePaint[l] == _state) {
                            i = l;
                            break;
                        }
                    }
                    if (i == -1) {
                        // monitorexit(this.bytGamePaint)
                        return;
                    }
                    final byte[] _temp = this.bytGamePaint;
                    this.bytGamePaint = new byte[_temp.length - 1];
                    if (i != 0) {
                        System.arraycopy(_temp, 0, this.bytGamePaint, 0, i);
                    }
                    if (i != _temp.length - 1) {
                        System.arraycopy(_temp, i + 1, this.bytGamePaint, i, _temp.length - i - 1);
                    }
                }
                DCanvas.getInstance().buttonLeftFlash = 0;
                DCanvas.getInstance().buttonRightFlash = 0;
                switch (_state) {
                    case 7: {
                        if (DCanvas.getInstance().UDialog != null) {
                            DCanvas.getInstance().UDialog.clean();
                            DCanvas.getInstance().UDialog = null;
                            break;
                        }
                        break;
                    }
                    case 3:
                    case 9: {
                        if (DCanvas.getInstance().UMenu != null) {
                            DCanvas.getInstance().UMenu.clean();
                            DCanvas.getInstance().UMenu = null;
                            DCanvas.getInstance().setFullScreen(false);
                            break;
                        }
                        break;
                    }
                    case 5:
                    case 8:
                    case 10: {
                        if (DCanvas.getInstance().UHandle != null) {
                            DCanvas.getInstance().UHandle.clean();
                            DCanvas.getInstance().UHandle = null;
                            break;
                        }
                        break;
                    }
                    case 4: {
                        if (DCanvas.getInstance().USystemMenu != null) {
                            DCanvas.getInstance().USystemMenu.clean();
                            DCanvas.getInstance().USystemMenu = null;
                            break;
                        }
                        break;
                    }
                    case 2: {
                        if (DCanvas.getInstance().UGameMain != null) {
                            DCanvas.getInstance().UGameMain.clean();
                            DCanvas.getInstance().UGameMain = null;
                        }
                        ResPoolFactory.getInstance().ClearPool(3);
                        ResPoolFactory.getInstance().ClearPool(4);
                        ResPoolFactory.getInstance().ClearPool(7);
                        ResPoolFactory.getInstance().ClearPool(5);
                        ResPoolFactory.getInstance().ClearPool(6);
                        ResPoolLevelFunction.getInstance().cleanAllLevelFunctionData();
                        break;
                    }
                    case 6: {
                        if (DCanvas.getInstance().ULoading != null) {
                            DCanvas.getInstance().ULoading.clean();
                            DCanvas.getInstance().ULoading = null;
                            DCanvas.getInstance().setFullScreen(false);
                        }
                        ResPoolFactory.getInstance().ClearPool(2);
                        break;
                    }
                    case 1: {
                        if (DCanvas.getInstance().UGameMain != null) {
                            DCanvas.getInstance().UGameMain.clean();
                            DCanvas.getInstance().UGameMain = null;
                        }
                        ResPoolFactory.getInstance().ClearPool(1);
                        ResPoolFactory.getInstance().ClearPool(4);
                        ResPoolFactory.getInstance().ClearPool(5);
                        ResPoolFactory.getInstance().ClearPool(6);
                        ResPoolLevelFunction.getInstance().cleanAllLevelFunctionData();
                        break;
                    }
                    case 11: {
                        if (DCanvas.getInstance().UCue != null) {
                            DCanvas.getInstance().UCue.clean();
                            DCanvas.getInstance().UCue = null;
                            break;
                        }
                        break;
                    }
                }
                if (this.getCurStateOperation((byte) _state) == 4 && this.getCurrentOperation() != 4) {
                    ResPoolFactory.getInstance().ClearPool(1);
                }
            }
            // monitorexit(this.bytGamePaint)
            this.setFocus();
            System.gc();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setReLoadRole() {
        DCanvas.getInstance().bytFillRectType = 0;
        DCanvas.getInstance().vInformation = null;
        this.delUIbase(2);
        Macro.bytGameType = 2;
        Macro.bytGameTypeReal = 2;
        NetManager.getInstance().MC.setRunning(false);
        NetManager.getInstance().setConnectionType((byte) 1, Macro.URL_LAND);
        NetManager.getInstance().shtHeartTimeMax = 6000;
        LoadingUI.getInstance().setSpeed(50);
        NetParse.getInstance().reload();
        new Param();
        LoadingUI.getInstance().setSpeed(70);
        Map.getInstance().delMap();
        LoadingUI.getInstance().setSpeed(80);
        ORPMe.ME = null;
        System.gc();
        if (DCanvas.getInstance().UGameMain == null) {
            getInstance().CreateState((byte) 1);
        }
        LoadingUI.getInstance().setSpeed(90);
    }

    public void setReLoadMainMenu() {
        getInstance().CreateState((byte) 7);
        DialogUI.getInstance().setDialog((byte) 10, "\u52a0\u8f7d\u8d44\u6e90\u8bf7\u7a0d\u5019...", (byte) 2);
        DCanvas.getInstance().setNetLoad(true);
        NetParse.getInstance().setInitLoadingPre();
        NetParse.getInstance().setInitLoading();
        getInstance().delUIbase(7);
        DCanvas.getInstance().bytFillRectType = 0;
        DCanvas.getInstance().vInformation = null;
        this.delUIbase(2);
        Macro.bytGameType = 2;
        Macro.bytGameTypeReal = 2;
        NetManager.getInstance().setConnectionType((byte) 1, Macro.URL_LAND);
        NetManager.getInstance().shtHeartTimeMax = 6000;
        LoadingUI.getInstance().setSpeed(50);
        NetParse.getInstance().reload();
        new Param();
        LoadingUI.getInstance().setSpeed(70);
        if (Map.getInstance() != null) {
            Map.getInstance().delMap();
        }
        LoadingUI.getInstance().setSpeed(80);
        if (ORPMe.ME != null) {
            ORPMe.ME = null;
        }
        System.gc();
        if (DCanvas.getInstance().UGameMain == null) {
            getInstance().stopNet();
            getInstance().CreateState((byte) 1);
            LandUI.getInstance().chooseMainMenu = true;
        }
        LoadingUI.getInstance().setSpeed(100);
    }

    public void stopNet() {
        if (NetSend.getInstance() != null) {
            NetSend.getInstance().clean();
            if (NetManager.getInstance() != null) {
                NetManager.getInstance().clean();
                NetParse.getInstance().clean();
            }
            new NetSend();
        }
    }

    public void checkPlatform() {
        if (Runtime.getRuntime().totalMemory() == 8000000L) {
            this.isPhone = false;
            return;
        }
        if (Macro.PHONE_INFO.toLowerCase().indexOf("javasdk") != -1) {
            this.isPhone = false;
            return;
        }
        if (Macro.PHONE_INFO.toLowerCase().indexOf("sunmicrosystems_wtk") != -1) {
            this.isPhone = false;
            return;
        }
        try {
            Class.forName("emulator.Emulator");
            this.isPhone = false;
        } catch (ClassNotFoundException ex) {
            this.isPhone = true;
        }
    }

    public void checkLevelFunction() {
        if (Macro.BLN_RES_POOL_ADVANCED) {
            final int extendPoolSize = (Macro.SCREEN_WIDTH + Macro.SCREEN_HEIGHT) / 100;
            for (int index = 0; index <= 4; ++index) {
                final int[] pool_SIZE = ResPoolFactory.POOL_SIZE;
                final int n = index;
                pool_SIZE[n] += extendPoolSize;
            }
        }
    }

    public void createFullInfoMenu(final String title, final String content, final byte bytState, final byte eventType, final byte[] buttonType) {
        if (DCanvas.getInstance().UMenu == null) {
            getInstance().CreateState((byte) 3);
        }
        new FullInfo(title, 0, bytState);
        FullInfo.getInstance().setFullRectMenu(content);
        FullInfo.getInstance().setButton(buttonType[0], buttonType[1], buttonType[2]);
    }

    public static String STRING_IMAGE_SYS() {
        return "sys";
    }

    public static String STRING_IMAGE_UI() {
        return "ui";
    }

    public static String STRING_SPRITE_OTHER() {
        return "ingame";
    }

    public static String STRING_IMAGE_PROP() {
        return "prop";
    }

    public static String STRING_IMAGE_BACK() {
        return "title";
    }
}
