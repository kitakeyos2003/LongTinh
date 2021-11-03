// Decompiled with: Procyon 0.5.36
// Class Version: 3
package means;

import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import base.DCanvas;
import base.Macro;
import javax.microedition.lcdui.Font;

public class DebugFrame
{
    private static final boolean s_bln_open = false;
    private static DebugFrame s_instance;
    public static boolean s_blnFocused;
    private static boolean s_blnShowSeriaNum;
    private static boolean s_blnMsgLoopMode;
    private static boolean s_blnStopLog;
    private static short s_clipHeight;
    private static short s_clipWidth;
    private static short s_clipStartIndex;
    private static final int K_LOG_NUM_MAX = 500;
    private MSG[] s_logTable;
    private static short s_msgTableSize;
    private static short s_curTableIndex;
    private static boolean s_blnWrapped;
    private static int s_msgTotalNum;
    public static final Font font;
    public static final byte FONT_HEIGHT;
    public static final byte FONT_WIDTH;
    private static final int FONT_COLOR_DEFAULT = 16777215;
    private static final int FONT_COLOR_EXCEPTION = 16711680;
    public static final int FONT_COLOR_MSG_SEND = 16711680;
    public static final int FONT_COLOR_MSG_RECV = 65280;
    public static final int FONT_COLOR_MSG_NET_STATUS = 16777215;
    public static final int FONT_COLOR_MSG_SEPARATE = 65280;
    public static final int BG_STOP_LOG = 0;
    public static final int FONT_STOP_LOG = 16777215;
    public static final String TXT_STOP_LOG = "已停止写进Log,按 0 键切换";
    public static final String KEY_OPEN_CLOSE_LOG = "#**#";
    public static final String KEY_OPEN_CLOSE_LOG_RESET = "#*#*";
    public static String key_open_close_acc;
    public static final byte KEY_EMU_STAR = 0;
    public static final byte KEY_EMU_POUND = 1;
    public static final byte KEY_EMU_RESET = 2;
    public static final short KEY_EMU_AREA_SIZE = 50;
    public static final int[][] KEY_EMU_STAR_POUND;
    public static String DEBUG_LOG_EXCEPTION;
    
    private DebugFrame() {
        this.s_logTable = new MSG[500];
        DebugFrame.s_msgTableSize = 0;
        DebugFrame.s_curTableIndex = 0;
        DebugFrame.s_clipHeight = Macro.SCREEN_HEIGHT;
        DebugFrame.s_clipWidth = Macro.SCREEN_WIDTH;
        DebugFrame.s_clipStartIndex = 0;
        this.logIn("******Debug Log Start*****");
    }
    
    public static synchronized DebugFrame getInstance() {
        if (DebugFrame.s_instance == null) {
            DebugFrame.s_instance = new DebugFrame();
        }
        return DebugFrame.s_instance;
    }
    
    private boolean isEmpty() {
        return !DebugFrame.s_blnWrapped && DebugFrame.s_curTableIndex == 0;
    }
    
    public void logic() {
        int keyDown = DCanvas.getInstance().getKeyDown();
        if (DCanvas.getInstance().IsPointerDown(DebugFrame.KEY_EMU_STAR_POUND[0][0], DebugFrame.KEY_EMU_STAR_POUND[0][1], DebugFrame.KEY_EMU_STAR_POUND[0][2], DebugFrame.KEY_EMU_STAR_POUND[0][3])) {
            keyDown = 2048;
        }
        else if (DCanvas.getInstance().IsPointerDown(DebugFrame.KEY_EMU_STAR_POUND[1][0], DebugFrame.KEY_EMU_STAR_POUND[1][1], DebugFrame.KEY_EMU_STAR_POUND[1][2], DebugFrame.KEY_EMU_STAR_POUND[1][3])) {
            keyDown = 1024;
        }
        else if (DCanvas.getInstance().IsPointerDown(DebugFrame.KEY_EMU_STAR_POUND[2][0], DebugFrame.KEY_EMU_STAR_POUND[2][1], DebugFrame.KEY_EMU_STAR_POUND[2][2], DebugFrame.KEY_EMU_STAR_POUND[2][3])) {
            keyDown = 524288;
        }
        if (keyDown == 2048) {
            DebugFrame.key_open_close_acc = String.valueOf(DebugFrame.key_open_close_acc) + "*";
        }
        else if (keyDown == 1024) {
            DebugFrame.key_open_close_acc = String.valueOf(DebugFrame.key_open_close_acc) + "#";
        }
        else if (keyDown == 524288) {
            DebugFrame.key_open_close_acc = "";
        }
        if (DebugFrame.key_open_close_acc.equals("#**#")) {
            DebugFrame.s_blnFocused = !DebugFrame.s_blnFocused;
            DebugFrame.key_open_close_acc = "";
        }
        if (DebugFrame.s_blnFocused) {
            if (this.isEmpty()) {
                return;
            }
            if (DCanvas.getInstance().isKeyDown(256) || DCanvas.getInstance().isKeyDown(8192)) {
                ++DebugFrame.s_clipStartIndex;
                if (DebugFrame.s_clipStartIndex >= DebugFrame.s_msgTableSize) {
                    DebugFrame.s_clipStartIndex = 0;
                }
            }
            else if (DCanvas.getInstance().isKeyDown(4) || DCanvas.getInstance().isKeyDown(4096)) {
                --DebugFrame.s_clipStartIndex;
                if (DebugFrame.s_clipStartIndex < 0) {
                    DebugFrame.s_clipStartIndex = (short)(DebugFrame.s_msgTableSize - 1);
                }
            }
            else if (DCanvas.getInstance().isKeyDown(64) || DCanvas.getInstance().isKeyDown(32768)) {
                DebugFrame.s_clipStartIndex = (short)(DebugFrame.s_curTableIndex - 1);
            }
            else if (DCanvas.getInstance().isKeyDown(16) || DCanvas.getInstance().isKeyDown(16384)) {
                DebugFrame.s_clipStartIndex = 0;
            }
            else if (DCanvas.getInstance().isKeyDown(32)) {
                this.clearAllMsg();
            }
            else if (DCanvas.getInstance().isKeyDown(1)) {
                DebugFrame.s_blnStopLog = !DebugFrame.s_blnStopLog;
            }
        }
    }
    
    private void clearAllMsg() {
        for (int i = 1; i < 500; ++i) {
            if (this.s_logTable[i] != null) {
                this.s_logTable[i] = null;
            }
        }
        DebugFrame.s_clipStartIndex = 0;
        DebugFrame.s_msgTableSize = 1;
        DebugFrame.s_msgTotalNum = 1;
        DebugFrame.s_curTableIndex = 1;
        DebugFrame.s_blnWrapped = false;
    }
    
    public void logIn(final String s) {
        this.logIn(s, 16777215);
    }
    
    public void logInException(final String debug_LOG_EXCEPTION) {
        this.logIn(DebugFrame.DEBUG_LOG_EXCEPTION = debug_LOG_EXCEPTION, 16711680);
    }
    
    public void logIn(final String s, final int n) {
    }
    
    public void logOut(final Graphics graphics) {
        if (!DebugFrame.s_blnFocused) {
            return;
        }
        this.logOutBG(graphics);
        int n = 0;
        byte b = 0;
        final byte font_HEIGHT = DebugFrame.FONT_HEIGHT;
        String[] wrapString;
        for (short s_clipStartIndex = DebugFrame.s_clipStartIndex; s_clipStartIndex < DebugFrame.s_msgTableSize && n < DebugFrame.s_clipHeight; n += wrapString.length * (font_HEIGHT + 3), ++s_clipStartIndex) {
            wrapString = wrapString(this.s_logTable[s_clipStartIndex].getMsgInfo(), DebugFrame.s_clipWidth);
            final int msgColor = this.s_logTable[s_clipStartIndex].getMsgColor();
            if (msgColor > 0 && msgColor <= 16777215) {
                graphics.setColor(msgColor);
            }
            else {
                graphics.setColor(16777215);
            }
            int n2 = b * (font_HEIGHT + 3);
            for (int i = 0; i < wrapString.length; ++i) {
                graphics.drawString(wrapString[i], 0, n2, 20);
                n2 += font_HEIGHT + 3;
            }
            b += (byte)wrapString.length;
        }
        if (DebugFrame.s_blnStopLog) {
            graphics.setClip(0, Macro.SCREEN_HEIGHT - 20, Macro.SCREEN_WIDTH, 20);
            graphics.setColor(0);
            graphics.fillRect(0, Macro.SCREEN_HEIGHT - 20, Macro.SCREEN_WIDTH, 20);
            graphics.setColor(16777215);
            graphics.drawString("已停止写进Log,按 0 键切换", 0, Macro.SCREEN_HEIGHT - 20, 0);
        }
    }
    
    public void logOutBG(final Graphics graphics) {
        if (!DebugFrame.s_blnFocused) {
            return;
        }
        graphics.setClip(0, 0, DebugFrame.s_clipWidth, DebugFrame.s_clipHeight);
        graphics.setColor(255);
        graphics.fillRect(0, 0, DebugFrame.s_clipWidth, DebugFrame.s_clipHeight);
    }
    
    public static String[] wrapString(final String s, final int n) {
        if (s == null) {
            return null;
        }
        if ((short)s.indexOf("\n") == -1 && DebugFrame.font.stringWidth(s) <= n) {
            return new String[] { s };
        }
        String s2 = s;
        final Vector vector = new Vector();
        for (int i = 1; i <= s2.length(); ++i) {
            final short n2 = (short)s2.indexOf("\n");
            if (n2 != -1 && DebugFrame.font.stringWidth(s2.substring(0, n2)) <= n) {
                vector.addElement(s2.substring(0, n2));
                s2 = s2.substring(n2 + 1);
                i = 0;
            }
            else if (DebugFrame.font.stringWidth(s2.substring(0, i)) > n) {
                vector.addElement(s2.substring(0, i - 1));
                s2 = s2.substring(i - 1);
                i = 0;
            }
        }
        if (s2.length() > 0) {
            vector.addElement(s2);
        }
        final String[] array = new String[vector.size()];
        for (int j = 0; j < vector.size(); ++j) {
            array[j] = (String) vector.elementAt(j);
        }
        return array;
    }
    
    static {
        DebugFrame.s_instance = null;
        DebugFrame.s_blnFocused = false;
        DebugFrame.s_blnShowSeriaNum = true;
        DebugFrame.s_blnMsgLoopMode = true;
        DebugFrame.s_blnStopLog = false;
        DebugFrame.s_msgTableSize = 0;
        DebugFrame.s_blnWrapped = false;
        DebugFrame.s_msgTotalNum = 0;
        font = Font.getFont(0, 0, 8);
        FONT_HEIGHT = (byte)DebugFrame.font.getHeight();
        FONT_WIDTH = (byte)DebugFrame.font.stringWidth("聊");
        DebugFrame.key_open_close_acc = "";
        KEY_EMU_STAR_POUND = new int[][] { { 0, 0, 50, 50 }, { Macro.SCREEN_WIDTH - 50, 0, 50, 50 }, { (Macro.SCREEN_WIDTH - 50) / 2, 0, 50, 50 } };
        DebugFrame.DEBUG_LOG_EXCEPTION = "";
    }
    
    class MSG
    {
        private String msgInfo;
        private int color;
        
        public MSG(final String msgInfo, final int color) {
            this.msgInfo = msgInfo;
            this.color = color;
        }
        
        public void setMsgInfo(final String msgInfo) {
            this.msgInfo = msgInfo;
        }
        
        public void setMsgColor(final int color) {
            this.color = color;
        }
        
        public String getMsgInfo() {
            return this.msgInfo;
        }
        
        public int getMsgColor() {
            return this.color;
        }
    }
}
