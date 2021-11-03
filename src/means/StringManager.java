// Decompiled with: CFR 0.151
// Class Version: 3
package means;

import base.DCanvas;
import base.Macro;
import common.DrawBase;
import common.LayoutStyle;
import face.GameUI;
import java.util.Vector;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import means.ChatContent;
import means.ImageManager;
import means.QSprite;
import model.ORPMe;
import model.ORole;

public class StringManager {
    static String strBase;
    static short[] shtLine;
    private static String[] str;
    public static int totalPage;
    public static int cutPage;
    public static int onePageRow;
    public static QSprite[] sprArraw;
    public static boolean blnPageSeleted;
    private static int getWrodWidth;

    public static short getNewLineW() {
        return (short)(Macro.SCREEN_WIDTH - 24);
    }

    public static String[] wenZi(String string, int n) {
        int n2;
        if (string == null) {
            return null;
        }
        strBase = string;
        int n3 = strBase.length();
        int n4 = 0;
        byte by = (byte)(n / Macro.FONTW + 1);
        shtLine = new short[n3 / by + 1];
        short s = (short)(n4 + by);
        int n5 = 0;
        while (true) {
            if (s >= n3 || s < 0) {
                int n6;
                n2 = n3;
                if (Macro.font.stringWidth(strBase.substring(n4, n3)) > n) {
                    for (n6 = n3; n6 >= n4; n6 = (short)(n6 - 1)) {
                        if (Macro.font.stringWidth(strBase.substring(n4, n6)) > n) continue;
                        n2 = n6;
                        break;
                    }
                }
                if ((n6 = StringManager.addPoint((short)n2)) == n3) break;
                n4 = n6;
                continue;
            }
            if (Macro.font.stringWidth(strBase.substring(n4, s)) <= n) {
                if (n5 == 2) {
                    n5 = 0;
                    n4 = StringManager.addPoint(s);
                    if ((s = (short)(n4 + by)) < n3) continue;
                    s = (short)(n3 - 1);
                    continue;
                }
                if (n5 == 0) {
                    n5 = 1;
                }
                s = (short)(s + 1);
                continue;
            }
            if (n5 == 1) {
                s = (short)(s - 1);
                n5 = 0;
                n4 = StringManager.addPoint(s);
                if ((s = (short)(n4 + by)) < n3) continue;
                s = (short)(n3 - 1);
                continue;
            }
            if (n5 == 0) {
                n5 = 2;
            }
            s = (short)(s - 1);
        }
        for (n2 = 0; n2 < shtLine.length; ++n2) {
            if (shtLine[n2] != 0) continue;
            short[] sArray = shtLine;
            shtLine = new short[n2];
            System.arraycopy(sArray, 0, shtLine, 0, n2);
            break;
        }
        String[] stringArray = new String[shtLine.length];
        int n7 = 0;
        for (int i = 0; i < shtLine.length; ++i) {
            stringArray[i] = strBase.substring(n7, shtLine[i]);
            if (stringArray[i].endsWith("\n")) {
                stringArray[i] = stringArray[i].substring(0, stringArray[i].length() - 1);
            }
            n7 = shtLine[i];
        }
        strBase = null;
        shtLine = null;
        return stringArray;
    }

    private static short addPoint(short s) {
        short s2;
        int n;
        for (n = shtLine.length - 1; n >= 0 && shtLine[n] == 0; --n) {
        }
        short s3 = 0;
        if (n != -1) {
            s3 = shtLine[n];
        }
        if ((s2 = (short)strBase.substring(s3, s).indexOf("\n")) != -1) {
            if (s2 == 0) {
                if (n != -1) {
                    if (shtLine[n] != 0) {
                        int n2;
                        if (strBase.substring(shtLine[n] - 1, shtLine[n]).equals("\n")) {
                            StringManager.pushLinePoint((short)(s3 + s2 + 1));
                            return (short)(s3 + s2 + 1);
                        }
                        short[] sArray = shtLine;
                        int n3 = n2 = n;
                        sArray[n3] = (short)(sArray[n3] + 1);
                    }
                } else {
                    short[] sArray = shtLine;
                    boolean bl = false;
                    sArray[0] = (short)(sArray[0] + 1);
                }
            } else {
                StringManager.pushLinePoint((short)(s3 + s2 + 1));
            }
            return (short)(s3 + s2 + 1);
        }
        StringManager.pushLinePoint(s);
        return s;
    }

    private static void pushLinePoint(short s) {
        for (int i = 0; i < shtLine.length; ++i) {
            if (shtLine[i] != 0) continue;
            StringManager.shtLine[i] = s;
            return;
        }
        short[] sArray = shtLine;
        shtLine = new short[sArray.length + 1];
        System.arraycopy(sArray, 0, shtLine, 0, sArray.length);
        StringManager.shtLine[StringManager.shtLine.length - 1] = s;
    }

    public static void drawWordMove(Graphics graphics, String string, int n, int n2, int n3, int n4, int n5, int n6) {
        if (string == null) {
            return;
        }
        str = StringManager.wenZi(string, n3);
        StringManager.drawWordMove(graphics, str, n, n2, n4, n5, n6);
    }

    public static void drawWordMove(Graphics graphics, String[] stringArray, int n, int n2, int n3, int n4, int n5) {
        if (stringArray == null) {
            return;
        }
        int n6 = n;
        int n7 = n2;
        if (stringArray.length * Macro.FONTHEIGHT > n3) {
            n7 = n7 + n3 - (int)(DCanvas.frameCountMove % (long)(stringArray.length * Macro.FONTHEIGHT + n3));
        }
        DrawBase.setClip(n, n2, Macro.SCREEN_WIDTH, n3 + 4);
        for (int n8 = 0; n8 < stringArray.length; n8 = (int)((byte)(n8 + 1))) {
            DrawBase.drawString(stringArray[n8], n6, n7 + n8 * Macro.FONTHEIGHT, n4, n5);
        }
        DrawBase.setClip(0, 0, Macro.SCREEN_WIDTH, Macro.SCREEN_HEIGHT);
    }

    public static void draWordMove(Graphics graphics, String[] stringArray, int n, int n2, int n3, int n4, int n5) {
        if (stringArray == null) {
            return;
        }
        int n6 = n;
        int n7 = n2;
        if (stringArray.length * Macro.FONTHEIGHT > n3 && DCanvas.frameCountMove % (long)(stringArray.length * Macro.FONTHEIGHT) >= (long)(stringArray.length * Macro.FONTHEIGHT >> 4) && (n7 -= (int)(DCanvas.frameCountMove % (long)(stringArray.length * Macro.FONTHEIGHT))) < -(stringArray.length * Macro.FONTHEIGHT)) {
            n7 = n3;
        }
        DrawBase.setClip(n, n2, Macro.SCREEN_WIDTH, n3 + 4);
        for (int n8 = 0; n8 < stringArray.length; n8 = (int)((byte)(n8 + 1))) {
            DrawBase.drawString(stringArray[n8], n6, n7 + n8 * Macro.FONTHEIGHT, n4, n5);
        }
        DrawBase.setClip(0, 0, Macro.SCREEN_WIDTH, Macro.SCREEN_HEIGHT);
    }

    public static void drawColorWordMove(Graphics graphics, Vector vector, int n, int n2, int n3, byte by, int n4) {
        if (vector == null) {
            return;
        }
        int n5 = n;
        int n6 = n2;
        if (by * Macro.FONTHEIGHT > n3) {
            n6 = n6 + n3 - (int)(DCanvas.frameCountMove % (long)(by * Macro.FONTHEIGHT + n3));
        }
        DrawBase.setClip(n, n2, Macro.SCREEN_WIDTH, n3 + 4);
        int n7 = 0;
        for (int n8 = 0; n8 < vector.size(); n8 = (int)((byte)(n8 + 1))) {
            ChatContent chatContent = (ChatContent)vector.elementAt(n8);
            graphics.setColor(chatContent.fontColor);
            if (n8 != 0 && chatContent.mY == 1) {
                n7 += Macro.FONTHEIGHT;
            }
            DrawBase.drawString(chatContent.character, n5 + chatContent.mX, n6 + n7, chatContent.fontColor, n4);
        }
        DrawBase.setClip(0, 0, Macro.SCREEN_WIDTH, Macro.SCREEN_HEIGHT);
    }

    public static void drawColorWordPage(Graphics graphics, Vector vector, int n, int n2, int n3, byte by, int n4) {
        int n5;
        if (vector == null) {
            return;
        }
        int n6 = n;
        int n7 = n2;
        int n8 = n3;
        byte by2 = Macro.FONTHEIGHT;
        if (by * Macro.FONTHEIGHT > n8) {
            n7 -= cutPage * onePageRow * Macro.FONTHEIGHT;
            onePageRow = (n8 -= by2) / Macro.FONTHEIGHT;
            totalPage = by % onePageRow == 0 ? by / onePageRow - 1 : by / onePageRow;
            if (sprArraw == null) {
                sprArraw = new QSprite[2];
                for (n5 = 0; n5 < sprArraw.length; ++n5) {
                    StringManager.sprArraw[n5] = ImageManager.loadSpriteById(1, ImageManager.EncodespriteId("tab", String.valueOf(n5)), "tab", "tab", "ui");
                }
            }
            if (blnPageSeleted) {
                LayoutStyle.getInstance().drawChooseFrame(DCanvas.gameG, (Macro.SCREEN_WIDTH >> 1) - 15, n2 + n3 - 10, Macro.FONTHEIGHT + 12, Macro.FONTHEIGHT - 5);
            }
            GameUI.getInstance().drawMoneyNum(graphics, cutPage + 1, (Macro.SCREEN_WIDTH >> 1) - 2, n2 + n3 - 8);
            DCanvas.getInstance().drawSpriteFram(graphics, sprArraw[0], 0, 4, (Macro.SCREEN_WIDTH >> 1) - 20, n2 + n3 - 4);
            DCanvas.getInstance().drawSpriteFram(graphics, sprArraw[1], -1, 6, (Macro.SCREEN_WIDTH >> 1) + 20, n2 + n3 - 4);
        }
        DrawBase.setClip(n, n2, Macro.SCREEN_WIDTH, n8);
        n5 = 0;
        for (int n9 = 0; n9 < vector.size(); n9 = (int)((byte)(n9 + 1))) {
            ChatContent chatContent = (ChatContent)vector.elementAt(n9);
            graphics.setColor(chatContent.fontColor);
            if (n9 != 0 && chatContent.mY == 1) {
                n5 += Macro.FONTHEIGHT;
            }
            DrawBase.drawString(chatContent.character, n6 + chatContent.mX, n7 + n5, chatContent.fontColor, n4);
        }
        DrawBase.setClip(0, 0, Macro.SCREEN_WIDTH, Macro.SCREEN_HEIGHT);
    }

    public static void resetWordMoveTimer() {
        DCanvas.frameCountMove = 0L;
    }

    public static void drawWordRightToLeft(String string, int n, int n2, int n3, int n4, int n5, boolean bl) {
        if (string == null) {
            return;
        }
        getWrodWidth = Macro.font.stringWidth(string);
        int n6 = n;
        int n7 = n2;
        if (getWrodWidth > n3 && bl) {
            n6 = n + n3 - (int)(DCanvas.frameCountMove % (long)(getWrodWidth + n3));
        }
        DrawBase.setClip(n, n2, n3, Macro.FONTHEIGHT);
        if (n5 == 0) {
            DrawBase.drawString(string, n6 + (n3 >> 1), n7, n4, 17);
        } else if (n5 == 1) {
            DrawBase.drawString(string, n6, n7, n4, 20);
        } else if (n5 == 2) {
            DrawBase.setClip(0, 0, Macro.SCREEN_WIDTH, Macro.SCREEN_HEIGHT);
            DrawBase.setClip(n - n3, n2, n3, Macro.FONTHEIGHT);
            DrawBase.drawString(string, n6, n7, n4, 24);
        }
        DrawBase.setClip(0, 0, Macro.SCREEN_WIDTH, Macro.SCREEN_HEIGHT);
    }

    public static void drawShadowWord(Graphics graphics, String string, int n, int n2, int n3, int n4, int n5) {
        Font font = graphics.getFont();
        int n6 = graphics.getGrayScale();
        graphics.setFont(Macro.font);
        graphics.setColor(n4);
        graphics.drawString(string, n, n2, n5);
        graphics.setColor(n4);
        graphics.drawString(string, n + 1, n2, n5);
        graphics.setColor(n3);
        graphics.drawString(string, n - 1, n2 - 1, n5);
        graphics.setFont(font);
        graphics.setGrayScale(n6);
    }

    public static void drawMarginateString(Graphics graphics, int n, String string, int n2, int n3, int n4) {
        int n5 = graphics.getColor();
        graphics.setColor(n);
        graphics.drawString(string, n2, n3 - 1, n4);
        graphics.drawString(string, n2, n3 + 1, n4);
        graphics.drawString(string, n2 - 1, n3, n4);
        graphics.drawString(string, n2 - 1, n3 - 1, n4);
        graphics.drawString(string, n2 - 1, n3 + 1, n4);
        graphics.drawString(string, n2 + 1, n3, n4);
        graphics.drawString(string, n2 + 1, n3 - 1, n4);
        graphics.drawString(string, n2 + 1, n3 + 1, n4);
        graphics.setColor(n5);
        graphics.drawString(string, n2, n3, n4);
    }

    public static String displaceNpcTalk(String string) {
        int n;
        String string2 = string;
        if (string2 == null) {
            return "";
        }
        String string3 = "";
        Vector vector = new Vector();
        for (n = 1; n <= string2.length(); ++n) {
            short s = (short)string2.indexOf("#");
            if (s == -1) continue;
            String string4 = string2.substring(0, s);
            vector.addElement(string4);
            if (s + 3 > string2.length()) break;
            String string5 = string2.substring(s, s + 3);
            if (string5.equals("#NC")) {
                string5 = ORPMe.ME.strNickName;
            } else if (string5.equals("#ZY")) {
                string5 = ORole.getOccName(ORPMe.ME.bytOccupation);
            } else if (string5.equals("#ZZ")) {
                string5 = ORPMe.ME.bytCountry == 1 ? "龙之传人" : "恶魔之子";
            } else if (string5.equals("#HH")) {
                string5 = "\n";
            }
            vector.addElement(string5);
            string2 = string2.substring(s + 3);
            n = 0;
        }
        if (string2.length() > 0) {
            vector.addElement(string2);
        }
        if (vector.size() > 0) {
            for (n = 0; n < vector.size(); n = (int)((byte)(n + 1))) {
                string3 = String.valueOf(string3) + vector.elementAt(n);
            }
        }
        return string3;
    }

    public static void drawPropName(Graphics graphics, String[] stringArray, int n, int n2, int n3) {
        try {
            String string = "";
            int n4 = 0;
            for (int n5 = 0; n5 < stringArray.length; n5 = (int)((byte)(n5 + 1))) {
                String string2 = stringArray[n5];
                int n6 = n;
                int n7 = n2 + n5 * Macro.FONTHEIGHT;
                for (int n8 = 0; n8 < string2.length(); n8 = (int)((byte)(n8 + 1))) {
                    int n9 = string2.indexOf("$");
                    if (n9 != -1) {
                        if (n4 == 0) {
                            n4 = (byte)(n4 + 1);
                            string = string2.substring(0, n9);
                            graphics.setColor(0);
                            graphics.drawString(string, n6, n7, 20);
                            string2 = string2.substring(n9 + 1);
                            n6 += Macro.font.stringWidth(string);
                            if (!string2.equals("")) {
                                n5 = (byte)(n5 - 1);
                            }
                            n8 = (byte)(n8 - 1);
                            continue;
                        }
                        graphics.setColor(n3);
                        string = string2.substring(0, n9);
                        graphics.drawString(string, n6, n7, 20);
                        n6 += Macro.font.stringWidth(string);
                        graphics.setColor(0);
                        string = string2.substring(n9 + 1);
                        graphics.drawString(string, n6, n7, 20);
                        n6 += Macro.font.stringWidth(string);
                        n5 = (byte)(n5 + 1);
                        n8 = (byte)(n8 + 100);
                        continue;
                    }
                    graphics.setColor(0);
                    string = string2;
                    graphics.drawString(string, n6, n7, 20);
                    n6 += Macro.font.stringWidth(string);
                    n8 = (byte)(n8 + 100);
                }
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static int getStringWidth(Graphics graphics, String string) {
        if (string == null) {
            return 0;
        }
        int n = 0;
        int n2 = 0;
        for (int i = 0; i < string.length(); ++i) {
            n = graphics.getFont().charWidth(string.charAt(i));
            n2 += n;
        }
        return n2;
    }

    public static Vector spit(String string, String string2) {
        Vector vector = new Vector();
        String string3 = string;
        String string4 = "";
        while (string3.indexOf(string2) != -1) {
            string4 = string3.substring(0, string3.indexOf(string2));
            string3 = string3.substring(string3.indexOf(string2) + string2.length());
            vector.addElement(string4);
            string4 = "";
        }
        vector.addElement(string3);
        return vector;
    }

    static {
        totalPage = 1;
        cutPage = 0;
        sprArraw = null;
        blnPageSeleted = true;
    }
}
