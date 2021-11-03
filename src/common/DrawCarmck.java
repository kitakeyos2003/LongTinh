// 
// Decompiled by Procyon v0.5.36
// 
package common;

import javax.microedition.lcdui.Graphics;
import model.Map;
import base.Macro;
import javax.microedition.lcdui.Image;

public class DrawCarmck {

    public static final int TILE_WIDTH = 16;
    public static final int TILE_HEIGHT = 16;
    private static int m_bufWidth;
    private static int m_bufHeight;
    private static int m_prevX0;
    private static int m_prevY0;
    private static int m_prevX1;
    private static int m_prevY1;
    public static byte[] m_mapBuf;
    public static final int TILE_MASK = -1;
    public static Image m_tileKit1;
    private static int m_tilePerLine1;
    public static boolean m_updateBg;
    public static int PF_LV_WIDTH;
    public static int PF_LV_HEIGHT;
    private static int PF_WIDTH;
    private static int PF_HEIGHT;
    private static int PF_LEFT;
    private static int PF_TOP;

    static {
        DrawCarmck.m_updateBg = true;
        DrawCarmck.PF_LV_WIDTH = Macro.SCREEN_WIDTH;
        DrawCarmck.PF_LV_HEIGHT = Macro.SCREEN_GAME_HEIGHT;
        DrawCarmck.PF_WIDTH = DrawCarmck.PF_LV_WIDTH;
        DrawCarmck.PF_HEIGHT = DrawCarmck.PF_LV_HEIGHT;
        DrawCarmck.PF_LEFT = 0;
        DrawCarmck.PF_TOP = 0;
    }

    public static void createBg() {
        DrawCarmck.PF_LV_WIDTH = Macro.SCREEN_WIDTH;
        DrawCarmck.PF_LV_HEIGHT = Macro.SCREEN_GAME_HEIGHT;
        DrawCarmck.PF_WIDTH = DrawCarmck.PF_LV_WIDTH;
        DrawCarmck.PF_HEIGHT = DrawCarmck.PF_LV_HEIGHT;
        DrawCarmck.m_bufWidth = DrawCarmck.PF_LV_WIDTH - DrawCarmck.PF_LV_WIDTH % 16 + 32;
        DrawCarmck.m_bufHeight = DrawCarmck.PF_LV_HEIGHT - DrawCarmck.PF_LV_HEIGHT % 16 + 32;
        Map.getInstance().imgMap = Image.createImage(DrawCarmck.m_bufWidth, DrawCarmck.m_bufHeight);
        Map.getInstance().gimgMap = Map.getInstance().imgMap.getGraphics();
        DrawCarmck.m_tileKit1 = Map.getInstance().imgMaterial;
        DrawCarmck.m_mapBuf = Map.getInstance().bytsMap_Ground_Kmack;
    }

    public static int getTile(final int x, final int y) {
        final int id = Map.getInstance().bytColumn * y + x;
        return id;
    }

    public static void mappedDraw(final int tileX0, final int tileY0, final int tileX1, final int tileY1) {
        int posY = tileY0 * 16 % DrawCarmck.m_bufHeight;
        for (int j = tileY0; j <= tileY1; ++j) {
            int posX = tileX0 * 16 % DrawCarmck.m_bufWidth;
            for (int i = tileX0; i <= tileX1; ++i) {
                final int id = getTile(i, j);
                Map.getInstance().gimgMap.setClip(posX, posY, 16, 16);
                if (id >= 0 && id < DrawCarmck.m_mapBuf.length) {
                    int drawX = DrawCarmck.m_mapBuf[id];
                    if (drawX != -1) {
                        DrawCarmck.m_tilePerLine1 = DrawCarmck.m_tileKit1.getWidth() / 16;
                        int flag = DrawCarmck.m_tilePerLine1;
                        final int drawY = drawX / flag * 16;
                        drawX = drawX % flag * 16;
                        flag = 0;
                        if (Map.getInstance().bytsMap_Ground_Transform != null) {
                            flag = Map.getInstance().bytsMap_Ground_Transform[id];
                        }
                        Map.getInstance().gimgMap.drawRegion(DrawCarmck.m_tileKit1, drawX, drawY, 16, 16, flag, posX, posY, 0);
                    }
                }
                posX += 16;
                if (posX >= DrawCarmck.m_bufWidth) {
                    posX -= DrawCarmck.m_bufWidth;
                }
            }
            posY += 16;
            if (posY >= DrawCarmck.m_bufHeight) {
                posY -= DrawCarmck.m_bufHeight;
            }
        }
    }

    private static void copyFromBackImage(final Graphics g, final int modX, final int modY, final int w, final int h, final int screenX, final int screenY) {
        g.setClip(screenX, screenY, w, h);
        g.drawImage(Map.getInstance().imgMap, screenX - modX, screenY - modY, 0);
    }

    public static void mapFastDraw(final Graphics g, final int camX, final int camY) {
        final int alignedX0 = camX / 16;
        final int alignedX2 = alignedX0 + Macro.SCREEN_WIDTH / 16 + 1;
        final int alignedY0 = camY / 16;
        final int alignedY2 = alignedY0 + Macro.SCREEN_GAME_HEIGHT / 16 + 1;
        if (DrawCarmck.m_updateBg) {
            DrawCarmck.m_updateBg = false;
            mappedDraw(alignedX0, alignedY0, alignedX2, alignedY2);
            DrawCarmck.m_prevX0 = alignedX0;
            DrawCarmck.m_prevY0 = alignedY0;
            DrawCarmck.m_prevX1 = alignedX2;
            DrawCarmck.m_prevY1 = alignedY2;
        }
        if (DrawCarmck.m_prevX0 != alignedX0) {
            int start;
            int end;
            if (DrawCarmck.m_prevX0 < alignedX0) {
                start = DrawCarmck.m_prevX1 + 1;
                end = alignedX2;
            } else {
                start = alignedX0;
                end = DrawCarmck.m_prevX0 - 1;
            }
            mappedDraw(start, alignedY0, end, alignedY2);
            DrawCarmck.m_prevX0 = alignedX0;
            DrawCarmck.m_prevX1 = alignedX2;
        }
        if (DrawCarmck.m_prevY0 != alignedY0 || DrawCarmck.m_prevY1 != alignedY2) {
            int start;
            int end;
            if (DrawCarmck.m_prevY0 < alignedY0) {
                start = DrawCarmck.m_prevY1 + 1;
                end = alignedY2;
            } else {
                start = alignedY0;
                end = DrawCarmck.m_prevY0 - 1;
            }
            mappedDraw(alignedX0, start, alignedX2, end);
            DrawCarmck.m_prevY0 = alignedY0;
            DrawCarmck.m_prevY1 = alignedY2;
        }
        final int modX0 = camX % DrawCarmck.m_bufWidth;
        final int modY0 = camY % DrawCarmck.m_bufHeight;
        final int modX2 = (camX + Macro.SCREEN_WIDTH) % DrawCarmck.m_bufWidth;
        final int modY2 = (camY + Macro.SCREEN_GAME_HEIGHT) % DrawCarmck.m_bufHeight;
        if (modX2 > modX0) {
            if (modY2 > modY0) {
                copyFromBackImage(g, modX0, modY0, Macro.SCREEN_WIDTH, Macro.SCREEN_GAME_HEIGHT, 0, 0);
            } else {
                copyFromBackImage(g, modX0, modY0, Macro.SCREEN_WIDTH, Macro.SCREEN_GAME_HEIGHT - modY2, 0, 0);
                copyFromBackImage(g, modX0, 0, Macro.SCREEN_WIDTH, modY2, 0, DrawCarmck.PF_TOP + Macro.SCREEN_GAME_HEIGHT - modY2);
            }
        } else if (modY2 > modY0) {
            copyFromBackImage(g, modX0, modY0, Macro.SCREEN_WIDTH - modX2, Macro.SCREEN_GAME_HEIGHT, 0, 0);
            copyFromBackImage(g, 0, modY0, modX2, Macro.SCREEN_GAME_HEIGHT, DrawCarmck.PF_LEFT + Macro.SCREEN_WIDTH - modX2, 0);
        } else {
            copyFromBackImage(g, modX0, modY0, Macro.SCREEN_WIDTH - modX2, Macro.SCREEN_GAME_HEIGHT - modY2, 0, 0);
            copyFromBackImage(g, modX0, 0, Macro.SCREEN_WIDTH - modX2, modY2, 0, DrawCarmck.PF_TOP + Macro.SCREEN_GAME_HEIGHT - modY2);
            copyFromBackImage(g, 0, modY0, modX2, Macro.SCREEN_GAME_HEIGHT - modY2, DrawCarmck.PF_LEFT + Macro.SCREEN_WIDTH - modX2, 0);
            copyFromBackImage(g, 0, 0, modX2, modY2, DrawCarmck.PF_LEFT + Macro.SCREEN_WIDTH - modX2, DrawCarmck.PF_TOP + Macro.SCREEN_GAME_HEIGHT - modY2);
        }
        g.setClip(0, 0, (int) Macro.SCREEN_WIDTH, (int) Macro.SCREEN_HEIGHT);
        Map.getInstance().blnDrawCartoon = true;
    }
}
