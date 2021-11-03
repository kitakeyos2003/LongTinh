// 
// Decompiled by Procyon v0.5.36
// 
package base;

import javax.microedition.lcdui.Displayable;
import means.DebugFrame;
import common.IResConfig;
import model.ORPlayer;
import java.io.IOException;
import java.io.DataInputStream;
import model.IRoleDefine;
import common.LayoutStyle;
import common.Common;
import means.StringManager;
import common.ScrollText;
import common.IDefines;
import common.Pram;
import means.ImageManager;
import face.MenuUI;
import common.DrawBase;
import javax.microedition.lcdui.Font;
import common.GridTable;
import means.QSprite;
import java.util.Vector;
import java.util.Hashtable;
import javax.microedition.lcdui.Graphics;
import face.UIbase;
import javax.microedition.lcdui.Canvas;

public class DCanvas extends Canvas implements Runnable {

    private static DCanvas dcanvas;
    public long startTime;
    public long costTime;
    public long timePerFrame;
    public long timePerFrameLast;
    public static boolean isCanvas;
    public static long frameCount;
    public static long frameCountMove;
    private final long SLEEPTIME;
    public UIbase UGameMain;
    public UIbase UMenu;
    public UIbase UDialog;
    public UIbase ULoading;
    public UIbase USystemMenu;
    public UIbase UHandle;
    public UIbase UCue;
    public boolean blnIsRun;
    public static Graphics gameG;
    private int keyDownState;
    private int keyReleaseState;
    private int keyHoldState;
    public int curKeyReleaseState;
    private int curKeyHoldState;
    private int curKeyDownState;
    private boolean blnDisableKey;
    private boolean curKeyDelete;
    private Hashtable htTimeKeyHoldStart;
    public int GAME_KEY_UP;
    public int GAME_KEY_LEFT;
    public int GAME_KEY_DOWN;
    public int GAME_KEY_RIGHT;
    public int GAME_KEY_OK;
    public int GAME_KEY_SOFT_LEFT;
    public int GAME_KEY_SOFT_RIGHT;
    public int GAME_KEY_C;
    private final int GAME_KEY_POUND;
    private final int GAME_KEY_STAR;
    private final int GAME_KEY_NUM0;
    private final int GAME_KEY_NUM1;
    private final int GAME_KEY_NUM2;
    private final int GAME_KEY_NUM3;
    private final int GAME_KEY_NUM4;
    private final int GAME_KEY_NUM5;
    private final int GAME_KEY_NUM6;
    private final int GAME_KEY_NUM7;
    private final int GAME_KEY_NUM8;
    private final int GAME_KEY_NUM9;
    private final int KEY_MAP_LENGTH;
    public int[] KEY_MAP;
    public static int[] KEY_MAP_E71;
    public static int[] KEY_MAP_E62;
    public byte buttonLeftFlash;
    public byte buttonRightFlash;
    private boolean blnIsFullScreen;
    public Vector vInformation;
    private long intTimeS;
    public int[] intMoveKey;
    public byte[] bytDrawKeyIndex;
    private int PressedX;
    private int PressedY;
    private int HoldX;
    private int HoldY;
    private int ReleasedX;
    private int ReleasedY;
    public int CurPressedX;
    public int CurPressedY;
    public int CurHoldX;
    public int CurHoldY;
    private int CurReleasedX;
    private int CurReleasedY;
    public boolean blnPointerPressed;
    public boolean blnPointerReleased;
    public boolean blnIsTouch;
    public boolean blsTouchEvent;
    long currTime;
    private short shtFillRectX;
    private byte bytFillRectH;
    private short shtFillRectStep;
    public byte bytFillRectType;
    private short shtSystemChatMove;
    private String[] strsSystemChat;
    public boolean blnIsShowNetLoad;
    public short shtShowLoadX;
    public short shtShowLoadY;
    private QSprite spTortoise;
    private int Tortoiseframe;
    private int TortoiseIndex;
    private int spTortoise_height;
    int _bottonX_L;
    int _bottonY;
    int _bottonX_R;
    public boolean blnSpoolr;
    public short shtSpoolrX;
    public short shtSpoolrSY;
    public short shtSpoolrEY;
    public short shtSpoolrTH;
    public short shtSpoolrBH;
    public short shtSpoolrBY;
    public byte bytSpoolrFlash;
    public byte arrowhead_width;
    public byte arrowhead_height;
    private short MaxShowWidth;
    private int[] fontwidtharray;
    private int frameY;
    private int fontcolour;
    private int pointer_LRKeyW;
    private int pointer_LRKeyH;
    public GridTable gridTable;

    static {
        DCanvas.isCanvas = true;
        DCanvas.KEY_MAP_E71 = new int[]{109, 114, 116, 121, 102, 103, 104, 118, 98, 110, 117, 106};
        DCanvas.KEY_MAP_E62 = new int[]{109, 114, 116, 122, 102, 103, 104, 118, 98, 110, 117, 106};
    }

    public DCanvas() {
        this.SLEEPTIME = 100L;
        this.htTimeKeyHoldStart = new Hashtable(1);
        this.GAME_KEY_UP = -1;
        this.GAME_KEY_LEFT = -3;
        this.GAME_KEY_DOWN = -2;
        this.GAME_KEY_RIGHT = -4;
        this.GAME_KEY_OK = -5;
        this.GAME_KEY_SOFT_LEFT = -6;
        this.GAME_KEY_SOFT_RIGHT = -7;
        this.GAME_KEY_C = -8;
        this.GAME_KEY_POUND = 35;
        this.GAME_KEY_STAR = 42;
        this.GAME_KEY_NUM0 = 48;
        this.GAME_KEY_NUM1 = 49;
        this.GAME_KEY_NUM2 = 50;
        this.GAME_KEY_NUM3 = 51;
        this.GAME_KEY_NUM4 = 52;
        this.GAME_KEY_NUM5 = 53;
        this.GAME_KEY_NUM6 = 54;
        this.GAME_KEY_NUM7 = 55;
        this.GAME_KEY_NUM8 = 56;
        this.GAME_KEY_NUM9 = 57;
        this.KEY_MAP_LENGTH = 12;
        this.KEY_MAP = new int[]{48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 42, 35};
        this.PressedX = -1;
        this.PressedY = -1;
        this.HoldX = -1;
        this.HoldY = -1;
        this.ReleasedX = -1;
        this.ReleasedY = -1;
        this.CurPressedX = -1;
        this.CurPressedY = -1;
        this.CurHoldX = -1;
        this.CurHoldY = -1;
        this.CurReleasedX = -1;
        this.CurReleasedY = -1;
        this.currTime = 0L;
        this.spTortoise = null;
        this.Tortoiseframe = -1;
        this.TortoiseIndex = 0;
        this.spTortoise_height = 28;
        this.arrowhead_width = 15;
        this.arrowhead_height = 10;
        this.frameY = 0;
        this.pointer_LRKeyW = 50;
        this.pointer_LRKeyH = 22;
        this.gridTable = null;
        this.setFullScreenMode(true);
        final short _tempW = (short) ((Displayable) this).getWidth();
        final short _tempH = (short) ((Displayable) this).getHeight();
        if (Macro.SCREEN_WIDTH != _tempW || Macro.SCREEN_HEIGHT != _tempH) {
            Macro.SCREEN_WIDTH = _tempW;
            Macro.SCREEN_HEIGHT = _tempH;
            if (Macro.PHONE_INFO != null && Macro.PHONE_INFO.length() > 0) {
                final String plm = Macro.PHONE_INFO.toLowerCase();
                if (plm.indexOf("nokian70") >= 0 || plm.indexOf("nokian72") >= 0 || plm.indexOf("nokia7610") >= 0) {
                    Macro.SCREEN_WIDTH = 176;
                    Macro.SCREEN_HEIGHT = 208;
                }
            }
            final int MAX_SCREEN_WIDTH = Macro.SCREEN_WIDTH;
            final int MAX_SCREEN_HEIGHT = Macro.SCREEN_HEIGHT;
            Macro.bytMaxHintWidth = (byte) (Macro.SCREEN_WIDTH / 16 - 2);
            if (MAX_SCREEN_WIDTH > 210) {
                Macro.bytHintWidth = 10;
            } else {
                Macro.bytHintWidth = 8;
            }
            final int frameH = Macro.SCREEN_HEIGHT - 38 - 31 - 12;
            Macro.bytMaxFullRow = (byte) (frameH / Macro.FONTHEIGHT);
            Macro.bytMaxChatNote = (byte) ((Macro.bytMaxFullRow < 10) ? 10 : Macro.bytMaxFullRow);
            Macro.shtRectHeight = (short) ((Macro.SCREEN_HEIGHT - (Macro.FONTHEIGHT * 2 + 34)) / 2);
            Macro.SCREEN_GAME_HEIGHT = Macro.SCREEN_HEIGHT;
        }
        DCanvas.dcanvas = this;
        if (this.hasPointerEvents()) {
            this.blnIsTouch = true;
        } else {
            this.blnIsTouch = false;
        }
        this.intTimeS = (int) System.currentTimeMillis();
    }

    public static DCanvas getInstance() {
        return DCanvas.dcanvas;
    }

    protected void pointerPressed(final int x, final int y) {
        this.blnPointerPressed = true;
        this.blnPointerReleased = false;
        this.PressedX = x;
        this.PressedY = y;
        this.HoldX = x;
        this.HoldY = y;
    }

    protected void pointerReleased(final int x, final int y) {
        this.blnPointerPressed = false;
        this.blnPointerReleased = true;
        this.ReleasedX = x;
        this.ReleasedY = y;
        this.HoldX = -1;
        this.HoldY = -1;
    }

    protected void pointerDragged(final int x, final int y) {
        this.blnPointerPressed = true;
        this.blnPointerReleased = false;
        this.ReleasedX = x;
        this.ReleasedY = y;
        this.HoldX = -1;
        this.HoldY = -1;
    }

    private void getPointer() {
        this.CurPressedX = this.PressedX;
        this.CurPressedY = this.PressedY;
        this.CurHoldX = this.HoldX;
        this.CurHoldY = this.HoldY;
        this.CurReleasedX = this.ReleasedX;
        this.CurReleasedY = this.ReleasedY;
        this.PressedX = -1;
        this.PressedY = -1;
        this.ReleasedX = -1;
        this.ReleasedY = -1;
        this.blsTouchEvent = true;
    }

    public void clearPointerKey() {
        this.CurPressedX = -1;
        this.CurPressedY = -1;
        this.CurHoldX = -1;
        this.CurHoldY = -1;
        this.CurReleasedX = -1;
        this.CurReleasedY = -1;
        this.blsTouchEvent = false;
    }

    public boolean isPointerDownInSide(final int x, final int y, final int w, final int h) {
        return this.blsTouchEvent && this.CurPressedX >= x && this.CurPressedX <= x + w && this.CurPressedY >= y && this.CurPressedY <= y + h;
    }

    public boolean IsPointerDown(final int x, final int y, final int w, final int h) {
        if (this.blsTouchEvent && this.CurPressedX >= x && this.CurPressedX <= x + w && this.CurPressedY >= y && this.CurPressedY <= y + h) {
            this.blsTouchEvent = false;
            return true;
        }
        return false;
    }

    public boolean IsPointerHold(final int x, final int y, final int w, final int h) {
        if (this.blsTouchEvent && this.CurHoldX >= x && this.CurHoldX <= x + w && this.CurHoldY >= y && this.CurHoldY <= y + h) {
            this.blsTouchEvent = false;
            return true;
        }
        return false;
    }

    public boolean IsPointerRelease(final int x, final int y, final int w, final int h) {
        if (this.blsTouchEvent && this.CurReleasedX >= x && this.CurReleasedX <= x + w && this.CurReleasedY >= y && this.CurReleasedY <= y + h) {
            this.blsTouchEvent = false;
            return true;
        }
        return false;
    }

    public boolean IsPointerDragged(final int x, final int y, final int w, final int h) {
        if (this.blsTouchEvent && this.CurReleasedX >= x && this.CurReleasedX <= x + w && this.CurReleasedY >= y && this.CurReleasedY <= y + h) {
            this.blsTouchEvent = false;
            return true;
        }
        return false;
    }

    public byte[] getPointerMap(final boolean _isDown) {
        int _x = -1;
        int _y = -1;
        if (_isDown) {
            _x = this.CurPressedX;
            _y = this.CurPressedY;
        } else {
            _x = this.CurReleasedX;
            _y = this.CurReleasedY;
        }
        if (_x == -1) {
            return null;
        }
        final byte[] _xy = {(byte) ((_x + Param.getInstance().CAMERAX) / 16), (byte) ((_y + Param.getInstance().CAMERAY) / 16)};
        return _xy;
    }

    public void keyPressed(final int keyCode) {
        if (!this.blnDisableKey) {
            final int Code = this.GetKeyValue(keyCode);
            this.keyHoldState = 0;
            this.htTimeKeyHoldStart.clear();
            this.keyDownState |= Code;
            this.keyHoldState |= Code;
            this.htTimeKeyHoldStart.put(new Integer(Code), new Long(System.currentTimeMillis()));
        }
    }

    public void keyReleased(final int keyCode) {
        if (!this.blnDisableKey) {
            if (this.curKeyDelete) {
                this.curKeyDelete = false;
                return;
            }
            final int Code = this.GetKeyValue(keyCode);
            this.keyReleaseState |= Code;
            this.keyHoldState &= ~Code;
            this.htTimeKeyHoldStart.remove(new Integer(Code));
        }
    }

    public void disableKey(final boolean disable) {
        this.blnDisableKey = disable;
    }

    private void getKey() {
        this.curKeyDownState = this.keyDownState;
        this.curKeyHoldState = this.keyHoldState;
        this.curKeyReleaseState = this.keyReleaseState;
        this.keyDownState = 0;
        this.keyReleaseState = 0;
    }

    public void deleteKey() {
        this.curKeyDelete = true;
    }

    public void clearKey() {
        this.keyDownState = 0;
        this.curKeyDownState = 0;
        this.keyReleaseState = 0;
        this.curKeyReleaseState = 0;
        this.keyHoldState = 0;
        this.curKeyHoldState = 0;
    }

    private int mapKeyIndex(final int key) {
        for (int i = 0; i < this.KEY_MAP.length; ++i) {
            if (this.KEY_MAP[i] == key || key == DCanvas.KEY_MAP_E71[i] || key == DCanvas.KEY_MAP_E62[i]) {
                return this.KEY_MAP[i];
            }
        }
        return key;
    }

    private int GetKeyValue(int keyCode) {
        keyCode = this.mapKeyIndex(keyCode);
        switch (keyCode) {
            case 48: {
                return 1;
            }
            case 49: {
                return 2;
            }
            case 50: {
                return 4;
            }
            case 51: {
                return 8;
            }
            case 52: {
                return 16;
            }
            case 53: {
                return 32;
            }
            case 54: {
                return 64;
            }
            case 55: {
                return 128;
            }
            case 56: {
                return 256;
            }
            case 57: {
                return 512;
            }
            case 35: {
                return 1024;
            }
            case 42: {
                return 2048;
            }
            default: {
                if (keyCode == this.GAME_KEY_SOFT_LEFT) {
                    return 65536;
                }
                if (keyCode == this.GAME_KEY_SOFT_RIGHT) {
                    return 131072;
                }
                if (keyCode == this.GAME_KEY_OK) {
                    return 262144;
                }
                if (keyCode == this.GAME_KEY_C) {
                    return 524288;
                }
                if (keyCode == this.GAME_KEY_UP) {
                    return 4096;
                }
                if (keyCode == this.GAME_KEY_DOWN) {
                    return 8192;
                }
                if (keyCode == this.GAME_KEY_LEFT) {
                    return 16384;
                }
                if (keyCode == this.GAME_KEY_RIGHT) {
                    return 32768;
                }
                return 0;
            }
        }
    }

    public boolean isKeyDown(final int keyCode) {
        return (this.curKeyDownState & keyCode) != 0x0;
    }

    public boolean IsKeyRelease(final int keyCode) {
        return (this.curKeyReleaseState & keyCode) != 0x0;
    }

    public boolean IsKeyHold(final int keyCode) {
        return (this.curKeyHoldState & keyCode) != 0x0;
    }

    public boolean IsKeyHold(final int keyCode, final long durTime) {
        boolean isHoldDur = false;
        if (this.IsKeyHold(keyCode)) {
            final long curTime = System.currentTimeMillis();
            if (this.htTimeKeyHoldStart.containsKey(new Integer(keyCode))) {
                long startTime = ((Long) this.htTimeKeyHoldStart.get(new Integer(keyCode))).longValue();
                if (curTime - startTime >= durTime) {
                    isHoldDur = true;
                }
            }
        }
        return isHoldDur;
    }

    public int getKeyDown() {
        return this.curKeyDownState;
    }

    public int getKeyRelease() {
        return this.curKeyReleaseState;
    }

    public boolean getCanvasState() {
        return DCanvas.isCanvas;
    }

    public void setCanvasState(final boolean state) {
        if (DCanvas.isCanvas != state) {
            this.clearKey();
        }
        DCanvas.isCanvas = state;
    }

    protected void paint(final Graphics g) {
        DCanvas.gameG = g;
        Macro.SCREEN_WIDTH = (short) ((Displayable) this).getWidth();
        Macro.SCREEN_HEIGHT = (short) ((Displayable) this).getHeight();
        try {
            DCanvas.gameG.setFont(Macro.font);
            DCanvas.gameG.setColor(0);
            DCanvas.gameG.fillRect(0, 0, (int) Macro.SCREEN_WIDTH, (int) Macro.SCREEN_HEIGHT);
            for (int i = 0; i < GameControl.getInstance().bytGamePaint.length; ++i) {
                switch (GameControl.getInstance().bytGamePaint[i]) {
                    case 1:
                    case 2: {
                        if (!this.blnIsFullScreen && this.UGameMain != null) {
                            this.UGameMain.paint(DCanvas.gameG);
                            break;
                        }
                        break;
                    }
                    case 3:
                    case 9: {
                        if (this.UMenu != null) {
                            this.UMenu.paint(DCanvas.gameG);
                            break;
                        }
                        break;
                    }
                    case 7: {
                        if (this.UDialog != null) {
                            this.UDialog.paint(DCanvas.gameG);
                            break;
                        }
                        break;
                    }
                    case 6: {
                        if (this.ULoading != null) {
                            this.ULoading.paint(DCanvas.gameG);
                            break;
                        }
                        break;
                    }
                    case 4: {
                        if (this.USystemMenu != null) {
                            this.USystemMenu.paint(DCanvas.gameG);
                            break;
                        }
                        break;
                    }
                    case 5:
                    case 8:
                    case 10: {
                        if (this.UHandle != null) {
                            this.UHandle.paint(DCanvas.gameG);
                            break;
                        }
                        break;
                    }
                    case 11: {
                        if (this.UCue != null) {
                            this.UCue.paint(DCanvas.gameG);
                            break;
                        }
                        break;
                    }
                }
            }
            if (this.vInformation != null) {
                this.drawWarnFrame();
            }
            if (this.bytFillRectType != 0) {
                this.drawSystemChat(DCanvas.gameG);
            }
            if (this.blnIsShowNetLoad) {
                this.showNetLoad(DCanvas.gameG);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void paintinfo(final Graphics g) {
        g.setColor(16711680);
        if (Macro.BLN_NET_DATA_COUNT) {
            g.setColor(255);
            g.fillRect(0, 0, 120, 50);
            g.setColor(16711680);
            g.drawString("\u6d41\u91cf\uff1a\u4e0a\u884c/\u4e0b\u884c(byte): ", 0, 0, 20);
            g.drawString(String.valueOf(Macro.sendDataCount) + "/" + Macro.recvDataCount, 0, 15, 20);
            g.drawString("\u8fd0\u884c\u65f6\u95f4: " + (System.currentTimeMillis() - Macro.proStartTime) / 1000L + "(s)", 0, 30, 20);
        }
    }

    private void paintDOS(final Graphics g) {
        final int oldColor = g.getColor();
        if (Macro.netUseInfoVector != null) {
            g.setColor(255);
            g.fillRect(0, 0, (int) Macro.SCREEN_WIDTH, 5 + Macro.FONTHEIGHT + Macro.netUseInfoVector.size() * Macro.FONTHEIGHT);
            g.setColor(16711680);
            g.drawString("DOS-RESPONSE-TIME\uff1a", 5, 5, 20);
            final int size = Macro.netUseInfoVector.size();
            int y = 0;
            for (int i = 0; i < size; ++i) {
                y = 5 + Macro.FONTHEIGHT + i * Macro.FONTHEIGHT;
                g.drawString(String.valueOf(i) + ":" + Macro.netUseInfoVector.elementAt(i).toString(), 5, y, 20);
            }
        }
        g.setColor(oldColor);
    }

    public void drawWord(final Graphics g, final String string, final int _x, final int _y, final byte _modi) {
        if (string == null) {
            return;
        }
        final Font oldFont = g.getFont();
        g.setFont(Macro.font);
        g.setColor(8142636);
        g.drawString(string, _x, _y, (int) _modi);
        g.setFont(oldFont);
    }

    public void drawContent(final Graphics g, final String[] string, final int _x, final int _y, final short _move, final byte _maxh, final byte _modi) {
        if (string == null) {
            return;
        }
        final Font oldFont = g.getFont();
        g.setFont(Macro.font);
        g.setColor(8142636);
        if (string.length > _maxh) {
            for (byte i = 0; i < _maxh; ++i) {
                g.drawString(string[i + _move], _x, _y + i * Macro.FONTHEIGHT, (int) _modi);
            }
        } else {
            for (byte i = 0; i < string.length; ++i) {
                g.drawString(string[i], _x, _y + i * Macro.FONTHEIGHT, (int) _modi);
            }
        }
        g.setFont(oldFont);
    }

    private void logicRectIn() {
        this.shtFillRectX -= this.shtFillRectStep;
        if (this.shtFillRectX <= 0) {
            this.shtFillRectX = 0;
            this.bytFillRectH += 4;
            if (this.bytFillRectH >= Macro.FONTHEIGHT) {
                this.bytFillRectH = Macro.FONTHEIGHT;
                this.bytFillRectType = 2;
            }
        }
    }

    private void logicRectGo() {
        this.bytFillRectH -= 4;
        if (this.bytFillRectH <= 0) {
            this.bytFillRectH = 1;
            this.shtFillRectX -= (short) (this.shtFillRectStep * 2);
            if (this.shtFillRectX <= -Macro.SCREEN_WIDTH) {
                this.bytFillRectType = 0;
                this.shtFillRectX = (short) (-Macro.SCREEN_WIDTH);
                this.strsSystemChat = null;
            }
        }
    }

    private void drawSystemChat(final Graphics g) {
        final short _y = (short) (Param.getInstance().shtMapMaxWidth / 16);
        if (this.bytFillRectType == 1) {
            this.logicRectIn();
        } else if (this.bytFillRectType == 3) {
            this.logicRectGo();
        }
        DrawBase.drawRGB(g, (byte) 3, this.shtFillRectX, _y + (Macro.FONTHEIGHT - this.bytFillRectH) / 2, Macro.SCREEN_WIDTH, this.bytFillRectH);
        final Font oldFont = g.getFont();
        g.setFont(Macro.font);
        if (this.bytFillRectType == 2) {
            g.setColor(16711680);
            g.drawString(this.strsSystemChat[0], Macro.SCREEN_WIDTH + this.shtSystemChatMove, (int) _y, 20);
        }
        g.setFont(oldFont);
    }

    public void setSystemChat(final String _string) {
        if (this.strsSystemChat == null) {
            (this.strsSystemChat = new String[1])[0] = _string;
        } else {
            final String[] _temp = this.strsSystemChat;
            System.arraycopy(_temp, 0, this.strsSystemChat = new String[_temp.length + 1], 0, _temp.length);
            this.strsSystemChat[_temp.length] = _string;
        }
        this.shtFillRectStep = (short) (Macro.SCREEN_WIDTH / 5);
        if (this.bytFillRectType == 0 || this.bytFillRectType == 3) {
            this.bytFillRectType = 1;
            this.bytFillRectH = 1;
            this.shtFillRectX = Macro.SCREEN_WIDTH;
        }
    }

    public void addSystemChatNote(final String _string) {
        if (Param.getInstance().vSystemNote == null) {
            (Param.getInstance().vSystemNote = new Vector(1, 1)).addElement(_string);
        } else {
            Param.getInstance().vSystemNote.addElement(_string);
            if (Param.getInstance().vSystemNote.size() > Macro.bytMaxChatNote) {
                Param.getInstance().vSystemNote.removeElementAt(0);
            }
        }
        if (this.UMenu != null && MenuUI.getInstance().getState() == 42) {
            MenuUI.getInstance().blnSetChatNote = false;
            MenuUI.getInstance().setChatNote();
            MenuUI.getInstance().setChatIndex((byte) 5);
        }
    }

    private void delSystemChat() {
        if (this.strsSystemChat.length <= 1) {
            this.bytFillRectType = 3;
        } else {
            final String[] _temp = this.strsSystemChat;
            System.arraycopy(_temp, 1, this.strsSystemChat = new String[_temp.length - 1], 0, this.strsSystemChat.length);
        }
    }

    public void setNetLoad(final boolean _bln) {
        if (_bln) {
            this.spTortoise = ImageManager.loadSpriteById(0, ImageManager.EncodespriteId("tortoise", "tortoise"), "tortoise", "tortoise", "ingame");
            if (Macro.bytGameType == 1) {
                this.shtShowLoadX = (short) (Macro.SCREEN_WIDTH / 2);
                this.shtShowLoadY = (short) (DrawBase.GetBottomBoxY - (this.spTortoise_height >> 1));
            } else {
                this.shtShowLoadX = (short) (Macro.SCREEN_WIDTH / 2);
                this.shtShowLoadY = (short) (DrawBase.GetBottomBoxY - (this.spTortoise_height >> 1));
            }
        } else if (this.spTortoise != null) {
            this.spTortoise = null;
        }
        this.blnIsShowNetLoad = _bln;
    }

    public void showNetLoad(final Graphics g) {
        this.drawSpriteFram(g, this.spTortoise, this.Tortoiseframe, this.TortoiseIndex, this.shtShowLoadX, this.shtShowLoadY);
    }

    public void drawSpriteFram(final Graphics g, final QSprite spritename, final int frame, final int frameindex, final int drawx, final int drawy) {
        final int _drawx = drawx;
        final int _drawy = drawy;
        this.updateSprite(spritename, frame, frameindex);
        if (spritename != null) {
            spritename.drawFrame(g, _drawx, _drawy);
        }
    }

    private void updateSprite(final QSprite spritename, int frame, final int spritechange) {
        frame = spritechange;
        if (spritename != null && frame != -1) {
            if (spritename.getAnimation() != frame) {
                spritename.setAnimation(frame);
            }
            spritename.update();
        } else if (spritename == null) {
            return;
        }
    }

    public void run() {
        try {
            while (GameControl.blnIsRun) {
                this.startTime = System.currentTimeMillis();
                final int _time = (int) (this.startTime - this.intTimeS);
                if (_time < 100L) {
                    continue;
                }
                ++DCanvas.frameCount;
                ++DCanvas.frameCountMove;
                if (DCanvas.frameCount < 0L) {
                    DCanvas.frameCount = 0L;
                }
                if (DCanvas.frameCountMove < 0L) {
                    DCanvas.frameCountMove = 0L;
                }
                this.getKey();
                if (this.blnIsTouch) {
                    this.getPointer();
                }
                if (this.bytFillRectType == 2) {
                    if (this.shtSystemChatMove >= -(Macro.font.stringWidth(this.strsSystemChat[0]) + Macro.SCREEN_WIDTH)) {
                        this.shtSystemChatMove -= 4;
                    } else {
                        this.shtSystemChatMove = 0;
                        this.delSystemChat();
                    }
                }
                try {
                    boolean _type1 = true;
                    boolean _type2 = true;
                    for (int i = GameControl.getInstance().bytGamePaint.length - 1; i >= 0 && i < GameControl.getInstance().bytGamePaint.length; --i) {
                        switch (GameControl.getInstance().bytGamePaint[i]) {
                            case 1:
                            case 2: {
                                if (_type2 && this.UGameMain != null) {
                                    this.UGameMain.logic(_time);
                                    break;
                                }
                                break;
                            }
                            case 3:
                            case 9: {
                                if (_type1 && this.UMenu != null) {
                                    this.UMenu.logic(_time);
                                    break;
                                }
                                break;
                            }
                            case 7: {
                                if (_type1 && this.UDialog != null) {
                                    this.UDialog.logic(_time);
                                    break;
                                }
                                break;
                            }
                            case 6: {
                                if (this.ULoading != null) {
                                    this.ULoading.logic(_time);
                                    _type2 = false;
                                    break;
                                }
                                break;
                            }
                            case 4: {
                                if (this.USystemMenu != null) {
                                    this.USystemMenu.logic(_time);
                                    break;
                                }
                                break;
                            }
                            case 5:
                            case 8:
                            case 10: {
                                if (this.UHandle != null) {
                                    this.UHandle.logic(_time);
                                    _type1 = false;
                                    break;
                                }
                                break;
                            }
                            case 11: {
                                if (this.UCue != null) {
                                    this.UCue.logic(_time);
                                    break;
                                }
                                break;
                            }
                        }
                    }
                    this.repaint();
                    this.serviceRepaints();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (this.vInformation != null) {
                    for (int j = 0; j < Math.min(this.vInformation.size(), 3); ++j) {
                        cInformation cInformation;
                        cInformation cf = cInformation = (cInformation) this.vInformation.elementAt(j);
                        --cInformation.bytTime;
                        if (cf.bytTime < 0 || ((getInstance().IsPointerRelease(0, 0, Macro.SCREEN_WIDTH, Macro.SCREEN_HEIGHT) || getInstance().isKeyDown()) && cf.bytTime < 10)) {
                            this.vInformation.removeElementAt(j);
                            break;
                        }
                    }
                    if (this.vInformation.isEmpty()) {
                        this.vInformation = null;
                    }
                }
                final long tmpTime = System.currentTimeMillis();
                this.intTimeS = this.startTime;
                this.costTime = tmpTime - this.startTime;
                this.timePerFrame = (int) (tmpTime - this.timePerFrameLast);
                this.timePerFrameLast = tmpTime;
                if (this.costTime >= 100L) {
                    continue;
                }
                Thread.sleep(100L - this.costTime);
            }
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
    }

    public void setFullScreen(final boolean _is) {
        this.blnIsFullScreen = _is;
    }

    public byte getTwoShortcut(final byte[] _byts, final boolean _bln) {
        if (_bln) {
            for (byte i = 0; i < 4; ++i) {
                if (this.getIntShortcutKey(_byts[i]) == this.curKeyDownState) {
                    return -1;
                }
            }
        }
        return this.getKey(this.curKeyDownState);
    }

    public byte getShortcut(final boolean _bln) {
        if (_bln) {
            for (byte i = 0; i < 4; ++i) {
                if (this.intMoveKey[i] == this.curKeyDownState) {
                    return -1;
                }
            }
        }
        return this.getKey(this.curKeyDownState);
    }

    public byte getKey(final int _key) {
        byte key = -1;
        switch (_key) {
            case 2: {
                key = 0;
                break;
            }
            case 4: {
                key = 1;
                break;
            }
            case 8: {
                key = 2;
                break;
            }
            case 16: {
                key = 3;
                break;
            }
            case 32: {
                key = 4;
                break;
            }
            case 64: {
                key = 5;
                break;
            }
            case 128: {
                key = 6;
                break;
            }
            case 256: {
                key = 7;
                break;
            }
            case 512: {
                key = 8;
                break;
            }
            case 4096: {
                key = 9;
                break;
            }
            case 16384: {
                key = 10;
                break;
            }
            case 32768: {
                key = 11;
                break;
            }
            case 8192: {
                key = 12;
                break;
            }
        }
        return key;
    }

    public byte getFullKey() {
        byte key = -1;
        switch (this.curKeyDownState) {
            case 2: {
                key = 0;
                break;
            }
            case 4: {
                key = 1;
                break;
            }
            case 8: {
                key = 2;
                break;
            }
            case 16: {
                key = 3;
                break;
            }
            case 32: {
                key = 4;
                break;
            }
            case 64: {
                key = 5;
                break;
            }
            case 128: {
                key = 6;
                break;
            }
            case 256: {
                key = 7;
                break;
            }
            case 512: {
                key = 8;
                break;
            }
            case 1: {
                key = 9;
                break;
            }
            case 2048: {
                key = 10;
                break;
            }
            case 1024: {
                key = 11;
                break;
            }
        }
        return key;
    }

    public int getIntShortcutKey(final byte _key) {
        int key = -1;
        switch (_key) {
            case 0: {
                key = 2;
                break;
            }
            case 1: {
                key = 4;
                break;
            }
            case 2: {
                key = 8;
                break;
            }
            case 3: {
                key = 16;
                break;
            }
            case 4: {
                key = 32;
                break;
            }
            case 5: {
                key = 64;
                break;
            }
            case 6: {
                key = 128;
                break;
            }
            case 7: {
                key = 256;
                break;
            }
            case 8: {
                key = 512;
                break;
            }
            case 9: {
                key = 4096;
                break;
            }
            case 10: {
                key = 16384;
                break;
            }
            case 11: {
                key = 32768;
                break;
            }
            case 12: {
                key = 8192;
                break;
            }
        }
        return key;
    }

    public byte checkKeyPress() {
        if (this.IsKeyHold(4096)) {
            return 9;
        }
        if (this.IsKeyHold(16384)) {
            return 10;
        }
        if (this.IsKeyHold(32768)) {
            return 11;
        }
        if (this.IsKeyHold(8192)) {
            return 12;
        }
        if (this.IsKeyHold(2)) {
            return 0;
        }
        if (this.IsKeyHold(4)) {
            return 1;
        }
        if (this.IsKeyHold(8)) {
            return 2;
        }
        if (this.IsKeyHold(16)) {
            return 3;
        }
        if (this.IsKeyHold(32)) {
            return 4;
        }
        if (this.IsKeyHold(64)) {
            return 5;
        }
        if (this.IsKeyHold(128)) {
            return 6;
        }
        if (this.IsKeyHold(256)) {
            return 7;
        }
        if (this.IsKeyHold(512)) {
            return 8;
        }
        return -1;
    }

    public byte checkKeyDown() {
        if (this.isKeyDown(4096)) {
            return 9;
        }
        if (this.isKeyDown(16384)) {
            return 10;
        }
        if (this.isKeyDown(32768)) {
            return 11;
        }
        if (this.isKeyDown(8192)) {
            return 12;
        }
        if (this.isKeyDown(2)) {
            return 0;
        }
        if (this.isKeyDown(4)) {
            return 1;
        }
        if (this.isKeyDown(8)) {
            return 2;
        }
        if (this.isKeyDown(16)) {
            return 3;
        }
        if (this.isKeyDown(32)) {
            return 4;
        }
        if (this.isKeyDown(64)) {
            return 5;
        }
        if (this.isKeyDown(128)) {
            return 6;
        }
        if (this.isKeyDown(256)) {
            return 7;
        }
        if (this.isKeyDown(512)) {
            return 8;
        }
        return -1;
    }

    public byte getKeyShortcut() {
        byte key = -1;
        switch (this.curKeyDownState) {
            case 1: {
                key = 0;
                break;
            }
            case 2: {
                key = 1;
                break;
            }
            case 4: {
                key = 2;
                break;
            }
            case 8: {
                key = 3;
                break;
            }
            case 16: {
                key = 4;
                break;
            }
            case 32: {
                key = 5;
                break;
            }
            case 64: {
                key = 6;
                break;
            }
            case 128: {
                key = 7;
                break;
            }
            case 256: {
                key = 8;
                break;
            }
            case 512: {
                key = 9;
                break;
            }
        }
        return key;
    }

    public boolean isKeyDown() {
        return this.curKeyDownState != 0;
    }

    public boolean isKeyRelease() {
        return this.curKeyReleaseState != 0;
    }

    public void drawSoftkey(final Graphics g, final byte _wordtype, final byte _wordL, final byte _wordR, final boolean _type) {
        if (!_type) {
            return;
        }
        short _bottonW_L = 0;
        short _bottonW_R = 0;
        final short _bottonH = (short) (Macro.FONTHEIGHT + 6);
        final short _WordY = (short) (Macro.SCREEN_HEIGHT - _bottonH - 7);
        if (_wordL != 100) {
            _bottonW_L = (short) Macro.font.stringWidth(String.valueOf(Pram.STR_RIGHTLEFT_KEY_WORDS[_wordL]) + 10);
            final short _WordX_L = 10;
            DrawBase.drawBox(_WordX_L, _WordY, _bottonW_L, _bottonH, (this.buttonLeftFlash == 0) ? new int[]{16314576, 16753705, 16753705} : new int[]{10176054, 16739958}, true);
            DrawBase.drawString(Pram.STR_RIGHTLEFT_KEY_WORDS[_wordL], _WordX_L + 8, _WordY + (_bottonH - Macro.FONTHEIGHT) / 2, 8399402, 20);
        }
        if (_wordR != 100) {
            _bottonW_R = (short) Macro.font.stringWidth(String.valueOf(Pram.STR_RIGHTLEFT_KEY_WORDS[_wordR]) + 10);
            final short _WordX_R = (short) (Macro.SCREEN_WIDTH - 10 - _bottonW_R);
            DrawBase.drawBox(_WordX_R, _WordY, _bottonW_R, _bottonH, (this.buttonRightFlash == 0) ? new int[]{16314576, 16753705, 16753705} : new int[]{10176054, 16739958}, true);
            DrawBase.drawString(Pram.STR_RIGHTLEFT_KEY_WORDS[_wordR], _WordX_R + 8, _WordY + (_bottonH - Macro.FONTHEIGHT) / 2, 8399402, 20);
        }
        if (_wordL != 100 || _wordR != 100) {
            final int _bottonW = Math.max(_bottonW_L, _bottonW_R);
            this.setPointerLRkey(_bottonW + 12, _bottonH + 10);
        }
    }

    public void drawSoftkey2(final Graphics g, final byte _wordtype, final byte _wordL, final byte _wordR, final boolean _type, final int x, final int y, final int w) {
        if (!_type) {
            return;
        }
        this._bottonY = y - 2;
        this.setPointerLRkey(w / 2, Macro.FONTHEIGHT + 4);
        final short _WordY = (short) (this._bottonY + 2);
        if (_wordL != 100) {
            this._bottonX_L = x + 3;
            final int _WordX_L = (short) (this._bottonX_L + 4);
            DrawBase.drawBox(this._bottonX_L, this._bottonY, Macro.font.stringWidth(Pram.STR_RIGHTLEFT_KEY_WORDS[_wordL]) + 9, Macro.FONTHEIGHT + 2, IDefines.MENU_SOFTBUTTON_BOX_COLOR, true);
            DrawBase.drawString(Pram.STR_RIGHTLEFT_KEY_WORDS[_wordL], _WordX_L + 1, _WordY - 1, 8399402, 20);
        }
        if (_wordR != 100) {
            this._bottonX_R = x + w - Macro.font.stringWidth(Pram.STR_RIGHTLEFT_KEY_WORDS[_wordR]) - 12;
            final short _WordX_R = (short) (this._bottonX_R + 4);
            DrawBase.drawBox(this._bottonX_R, this._bottonY, Macro.font.stringWidth(Pram.STR_RIGHTLEFT_KEY_WORDS[_wordR]) + 9, Macro.FONTHEIGHT + 2, IDefines.MENU_SOFTBUTTON_BOX_COLOR, true);
            DrawBase.drawString(Pram.STR_RIGHTLEFT_KEY_WORDS[_wordR], _WordX_R + 1, _WordY - 1, 8399402, 20);
        }
    }

    public void drawCueRect(final Graphics g, final int _w, final int _h) {
        final short _tempx0 = (short) ((Macro.SCREEN_WIDTH - _w) / 2);
        final short _tempy = (short) ((Macro.SCREEN_HEIGHT - _h) / 2);
        DrawBase.drawBoxInMenu(_tempx0, _tempy, _w, _h, new int[]{8537383, 16776433, 16765114, 12424807}, true, (byte) 1);
    }

    public void drawCueRect(final Graphics g, final int _y, final int _w, final int _h) {
        final short _tempx0 = (short) ((Macro.SCREEN_WIDTH - _w) / 2);
        final short _tempy = (short) _y;
        DrawBase.drawBoxInMenu(_tempx0, _tempy, _w, _h, new int[]{8537383, 16776433, 16765114, 12424807}, true, (byte) 1);
    }

    public void setFullSeeSpoolr(final Graphics g, final int _maxh, final int _length, final int _move) {
        if (_maxh >= _length) {
            this.blnSpoolr = false;
            return;
        }
        this.blnSpoolr = true;
        final short _startY = (short) (Macro.FONTHEIGHT + 5 + 13);
        final short _endY = (short) (Macro.SCREEN_HEIGHT - 17 - 3 - 13);
        this.shtSpoolrX = (short) (Macro.SCREEN_WIDTH - 12);
        this.shtSpoolrSY = _startY;
        this.shtSpoolrEY = _endY;
        this.shtSpoolrTH = (short) (_endY - _startY - 20);
        this.shtSpoolrBH = (short) (this.shtSpoolrTH * _maxh / _length);
        this.shtSpoolrBY = (short) (_move * (this.shtSpoolrTH - this.shtSpoolrBH) / (_length - _maxh));
        this.drawScrollFrame(this.shtSpoolrX, _startY, _endY, (short) (this.shtSpoolrBH - 1), this.shtSpoolrBY);
    }

    public void setOptionSpoolr(final Graphics g, final int _x, final int _startY, final int _endY, final int _maxh, final int _length, final int _move, final boolean _type) {
        if (_maxh >= _length) {
            this.blnSpoolr = false;
            return;
        }
        this.blnSpoolr = true;
        this.shtSpoolrX = (short) _x;
        this.shtSpoolrSY = (short) _startY;
        this.shtSpoolrEY = (short) _endY;
        this.shtSpoolrTH = (short) (_endY - _startY - 20);
        final short _blockH = (short) (this.shtSpoolrTH * _maxh / _length);
        final short _tempH = (short) (this.shtSpoolrTH - _blockH);
        final short _Y = (short) ((_length - 1) * _tempH / _length);
        this.shtSpoolrBH = (short) (this.shtSpoolrTH - _Y - 1);
        this.shtSpoolrBY = (short) (_move * _tempH / _length);
        this.drawScrollFrame((short) _x, (short) _startY, (short) _endY, this.shtSpoolrBH, this.shtSpoolrBY);
    }

    private void drawScrollFrame(final short _startDrawX, final short _startDrawY, final short _endDrawY, final short _blockH, final short _blockY) {
        final byte arrowhead_width = ScrollText.arrowhead_width;
        final byte arrowhead_height = ScrollText.arrowhead_height;
        final int height = _endDrawY - _startDrawY;
        final int StartX = _startDrawX;
        final int StartY = _startDrawY - 2;
        ScrollText.arrowhead.drawAnimationFrame(DCanvas.gameG, 0, 0, StartX, StartY + (arrowhead_height >> 1) + arrowhead_height);
        ScrollText.arrowhead.drawAnimationFrame(DCanvas.gameG, 0, 1, StartX, StartY + height - arrowhead_height);
        DrawBase.drawBox(StartX - (arrowhead_width - 4 >> 1) - 1, StartY + (arrowhead_height >> 1) + arrowhead_height, arrowhead_width - 2, height - (arrowhead_height >> 1) - (arrowhead_height << 1), new int[]{14716968, 12877088, 13876596}, true);
        DrawBase.drawBox(StartX - (arrowhead_width - 4 >> 1), StartY + 6 + _blockY + arrowhead_height, arrowhead_width - 4, _blockH - (arrowhead_height << 1) + arrowhead_width, new int[]{12279846, 13876596, 16641976}, true);
    }

    public void addInformation(final String _str, final int _y, final int _colour) {
        this.frameY = _y;
        this.fontcolour = _colour;
        this.addInformation(_str);
    }

    public void addInformation(final String _str, final int _colour) {
        this.fontcolour = _colour;
        this.addInformation(_str);
    }

    public void addInformation(final String _str) {
        cInformation cf = new cInformation();
        cf.fontcolour = 16642234;
        cf.strInfo = new String[_str.length()];
        this.MaxShowWidth = (short) (Macro.SCREEN_WIDTH - 24 - (Macro.FONTW << 1));
        cf.strInfo = StringManager.wenZi(_str, this.MaxShowWidth);
        this.fontwidtharray = new int[_str.length()];
        for (int i = 0; i < cf.strInfo.length; ++i) {
            this.fontwidtharray[i] = Macro.font.stringWidth(cf.strInfo[i]);
        }
        if (Common.Max(this.fontwidtharray) <= this.MaxShowWidth) {
            cf.shtDrawx = (short) (Macro.SCREEN_WIDTH - Common.Max(this.fontwidtharray) >> 1);
            cf.frameW = Common.Max(this.fontwidtharray);
        } else {
            cf.shtDrawx = (short) (Macro.SCREEN_WIDTH - this.MaxShowWidth >> 1);
            cf.frameW = (byte) (Macro.SCREEN_WIDTH - this.MaxShowWidth >> 1);
        }
        cf.shtDrawy = ((this.frameY == 0) ? ((short) (Macro.SCREEN_HEIGHT / 3)) : this.frameY);
        cf.frameH = (short) (cf.strInfo.length * Macro.FONTHEIGHT);
        cf.bytTime = 30;
        if (this.vInformation == null) {
            this.vInformation = new Vector(1, 1);
        }
        this.vInformation.addElement(cf);
    }

    private void drawWarnFrame() {
        synchronized (this.vInformation) {
            int posYOff = 0;
            for (int i = 0; i < Math.min(this.vInformation.size(), 3); ++i) {
                cInformation cf = (cInformation) this.vInformation.elementAt(i);
                DrawBase.drawBoxInMenu(cf.shtDrawx - (Macro.FONTW >> 1), cf.shtDrawy - 4 + posYOff, cf.frameW + Macro.FONTW, cf.frameH + 8, new int[]{6494722, 16777215, 16763066, 11370322}, true, (byte) 1);
                for (byte j = 0; j < cf.strInfo.length; ++j) {
                    DrawBase.drawString(cf.strInfo[j], cf.shtDrawx, cf.shtDrawy + j * Macro.FONTHEIGHT + posYOff, cf.fontcolour, 20);
                }
                posYOff += cf.frameH + 8 + 2;
            }
        }
        // monitorexit(this.vInformation)
    }

    public void setPointerLRkey(final int i, final int j) {
        this.pointer_LRKeyW = i;
        this.pointer_LRKeyH = j;
    }

    public int getLRKeyW() {
        return this.pointer_LRKeyW;
    }

    public boolean PointerDwonSoftKey_Left() {
        return this.IsPointerDown(0, Macro.SCREEN_HEIGHT - this.pointer_LRKeyH, this.pointer_LRKeyW, this.pointer_LRKeyH);
    }

    public boolean PointerDwonSoftKey_Right() {
        return this.IsPointerDown(Macro.SCREEN_WIDTH - this.pointer_LRKeyW, Macro.SCREEN_HEIGHT - this.pointer_LRKeyH, this.pointer_LRKeyW, this.pointer_LRKeyH);
    }

    public boolean IsPointerDownOutSideFrame(final int x, final int y, final int w, final int h) {
        if (this.blsTouchEvent && ((this.CurPressedX > 0 && this.CurPressedX < x) || (this.CurPressedX > x + w && this.CurPressedX < Macro.SCREEN_WIDTH) || (this.CurPressedY < y && this.CurPressedY > 0) || (this.CurPressedY < Macro.SCREEN_HEIGHT && this.CurPressedY > y + h))) {
            this.blsTouchEvent = false;
            return true;
        }
        return false;
    }

    public boolean PointerDwonSoftKey_Left1() {
        return this.IsPointerDown(this._bottonX_L, this._bottonY, this.pointer_LRKeyW, this.pointer_LRKeyH);
    }

    public boolean PointerDwonSoftKey_Right1() {
        return this.IsPointerDown(Macro.SCREEN_WIDTH - this.pointer_LRKeyW - this._bottonX_L, this._bottonY, this.pointer_LRKeyW, this.pointer_LRKeyH);
    }

    public void clearScreen() {
        DrawBase.setClip(0, 0, Macro.SCREEN_WIDTH, Macro.SCREEN_HEIGHT);
    }

    public void updateMoveKey() {
        this.intMoveKey = new int[4];
        final byte[] _bytDrawKeyIndex = new byte[9];
        final int[] _index = {2, 4, 8, 16, 32, 64, 128, 256, 512, 4096, 16384, 32768, 8192};
        for (byte i = 0; i < 13; ++i) {
            boolean b = false;
            if (Param.getInstance().intShortcutKeys[i][4] == 1) {
                if (Param.getInstance().intShortcutKeys[i][0] == 1) {
                    this.intMoveKey[0] = _index[i];
                    b = true;
                } else if (Param.getInstance().intShortcutKeys[i][0] == 2) {
                    this.intMoveKey[1] = _index[i];
                    b = true;
                } else if (Param.getInstance().intShortcutKeys[i][0] == 3) {
                    this.intMoveKey[2] = _index[i];
                    b = true;
                } else if (Param.getInstance().intShortcutKeys[i][0] == 4) {
                    this.intMoveKey[3] = _index[i];
                    b = true;
                }
            }
            if (i < 9) {
                if (!b) {
                    _bytDrawKeyIndex[i] = i;
                } else {
                    _bytDrawKeyIndex[i] = -1;
                }
            }
        }
        byte k = 0;
        for (byte j = 0; j < 9; ++j) {
            if (_bytDrawKeyIndex[j] == -1) {
                while (k < 4) {
                    if (Param.getInstance().intShortcutKeys[k + 9][0] != 1 && Param.getInstance().intShortcutKeys[k + 9][0] != 4 && Param.getInstance().intShortcutKeys[k + 9][0] != 2 && Param.getInstance().intShortcutKeys[k + 9][0] != 3) {
                        _bytDrawKeyIndex[j] = (byte) (k + 9);
                        ++k;
                        break;
                    }
                    ++k;
                }
            }
        }
        this.bytDrawKeyIndex = _bytDrawKeyIndex;
    }

    public void drawmenuBack(final Graphics g, final int _strlength, final int _recth, final int _move, final int _maxh) {
        LayoutStyle.getInstance().drawFullBackGround(13866046);
        this.setOptionSpoolr(g, Macro.SCREEN_WIDTH - 12, _recth + 16, Macro.SCREEN_HEIGHT - 35, _maxh, _strlength, _move, false);
    }

    public void drawFullSee(final Graphics g, final String _title, final int _color, final int _length, final int _move, final int _max) {
        this.drawmenuBack(g, 0, Macro.FONTHEIGHT, _move, _max);
        g.setColor(_color);
        g.drawString(_title, Macro.SCREEN_WIDTH / 2, 10, 17);
        this.setFullSeeSpoolr(g, _max, _length, _move);
    }

    public void drawTitleFullsee(final Graphics g, final String _title, final int _color, final int _length, final int _move, final int _max) {
        this.drawTileTextBG(g, _title);
        this.setOptionSpoolr(g, Macro.SCREEN_WIDTH - 12, Macro.FONTHEIGHT + 16, Macro.SCREEN_HEIGHT - 35, _max, 0, _move, false);
        g.setColor(_color);
        this.setFullSeeSpoolr(g, _max, _length, _move);
    }

    public void drawTileTextBG(final Graphics g, final String _title) {
        LayoutStyle.getInstance().drawFullBackGround(13866046);
        final int framePosX = 0;
        final int framePosY = 38;
        final int frameW = Macro.SCREEN_WIDTH;
        final int frameH = Macro.SCREEN_HEIGHT - framePosY - 31;
        LayoutStyle.getInstance().drawEarBox(g, _title, 9263661, IDefines.GLOBAL_UI_MAIN_TITLEBOX_X, 0, IDefines.GLOBAL_UI_MAIN_TITLEBOX_WIDTH, 30);
        LayoutStyle.getInstance().drawBeforeBackGround(framePosX, framePosY, frameW, frameH, new int[]{8142636, 14995858, 16314576});
    }

    public void showNotify() {
    }

    public void hideNotify() {
    }

    public boolean isRoleDefaultPartExist() {
        return Param.intRoleDefaultParts != null && Param.intRoleDefaultParts[0] != null && Param.intRoleDefaultParts[0][15] != null;
    }

    public void initRoleDefaultPart() {
        if (Param.intRoleDefaultParts == null) {
            Param.intRoleDefaultParts = new int[2][2][IRoleDefine.ROLE_DEFUALT_PARTS.length][2];
        }
        for (int cIndex = 0; cIndex < 2; ++cIndex) {
            for (int mIndex = 0; mIndex < 2; ++mIndex) {
                for (int pIndex = 0; pIndex < IRoleDefine.ROLE_DEFUALT_PARTS.length; ++pIndex) {
                    Param.intRoleDefaultParts[cIndex][mIndex][pIndex][0] = -1;
                    Param.intRoleDefaultParts[cIndex][mIndex][pIndex][1] = -1;
                }
            }
        }
    }

    public void readRoleDefaultPart(final DataInputStream dis) throws IOException {
        final byte countryCount = dis.readByte();
        for (int cIndex = 0; cIndex < 4; ++cIndex) {
            final byte male = (byte) (dis.readByte() - 1);
            final byte country = (byte) (dis.readByte() - 1);
            for (int pIndex = 0; pIndex < IRoleDefine.ROLE_DEFUALT_PARTS.length; ++pIndex) {
                Param.intRoleDefaultParts[country][male][pIndex][0] = dis.readShort();
                Param.intRoleDefaultParts[country][male][pIndex][1] = dis.readShort();
            }
        }
    }

    public void setCreateRole(final ORPlayer player) {
        if (player != null) {
            for (int partIndex = 0; partIndex < IRoleDefine.ROLE_DEFUALT_PARTS.length; ++partIndex) {
                final int partId = IRoleDefine.ROLE_DEFUALT_PARTS[partIndex];
                player.addRoleRes((short) partId, (short) Param.intRoleDefaultParts[player.bytCountry - 1][player.bytIsMale - 1][partIndex][0], (short) Param.intRoleDefaultParts[player.bytCountry - 1][player.bytIsMale - 1][partIndex][1]);
            }
        }
    }

    public void initRoleFoundationPart() {
        final int roleOcc = 4;
        if (Param.intRoleFoundationtParts == null) {
            Param.intRoleFoundationtParts = new int[roleOcc][IRoleDefine.ROLE_FOUNDATION_PARTS.length][2];
        }
        for (int cIndex = 0; cIndex < roleOcc; ++cIndex) {
            for (int pIndex = 0; pIndex < IRoleDefine.ROLE_FOUNDATION_PARTS.length; ++pIndex) {
                Param.intRoleFoundationtParts[cIndex][pIndex][0] = -1;
                Param.intRoleFoundationtParts[cIndex][pIndex][1] = -1;
            }
        }
    }

    public void readRoleFoundationPart(final DataInputStream dis) throws IOException {
        for (int roleOcc = dis.readByte(), cIndex = 0; cIndex < roleOcc; ++cIndex) {
            final int roleOccType = dis.readByte();
            for (int pIndex = 0; pIndex < IRoleDefine.ROLE_FOUNDATION_PARTS.length; ++pIndex) {
                Param.intRoleFoundationtParts[roleOccType - 1][pIndex][0] = dis.readShort();
                Param.intRoleFoundationtParts[roleOccType - 1][pIndex][1] = dis.readShort();
                if (IRoleDefine.ROLE_FOUNDATION_PARTS[pIndex] == -1 || IRoleDefine.ROLE_FOUNDATION_PARTS[pIndex] == 2) {
                    dis.readByte();
                }
            }
        }
    }

    public void setFoundationRole(final ORPlayer player) {
        if (player != null) {
            for (int partIndex = 0; partIndex < IRoleDefine.ROLE_FOUNDATION_PARTS.length; ++partIndex) {
                final int partId = IRoleDefine.ROLE_FOUNDATION_PARTS[partIndex];
                player.addRoleRes((short) partId, (short) Param.intRoleFoundationtParts[player.bytOccupation - 1][partIndex][0], (short) Param.intRoleFoundationtParts[player.bytOccupation - 1][partIndex][1]);
            }
        }
    }

    public String[] getResConfigAt(final short elementIndex) {
        if (elementIndex >= 0 && elementIndex < IResConfig.RES_CONFIG.length) {
            return IResConfig.RES_CONFIG[elementIndex];
        }
        DebugFrame.getInstance().logIn("Error!! \u83b7\u53d6\u8d44\u6e90\u5217\u8868\u53c2\u6570\u9519\u8bef");
        return null;
    }

    private class cInformation {

        int[] intColor;
        int[] intBColor;
        String[] strInfo;
        byte bytTime;
        short shtDrawx;
        int shtDrawy;
        int frameH;
        int frameW;
        int fontcolour;
    }
}
