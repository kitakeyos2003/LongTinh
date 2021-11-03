// 
// Decompiled by Procyon v0.5.36
// 
package common;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Font;
import base.Macro;
import javax.microedition.lcdui.Image;
import base.DCanvas;
import means.QSprite;

public class DrawBase {

    public static QSprite Transparency;
    private static int element;
    public static int GetBottomBoxY;

    static {
        DrawBase.element = 16;
    }

    public static void drawString(final String strCont, final int x, final int y, final int color, final int anchor) {
        if (strCont == null) {
            return;
        }
        if (strCont.equals("")) {
            return;
        }
        final int oldColor = getColor();
        setColor(color);
        DCanvas.gameG.drawString(strCont, x, y, anchor);
        setColor(oldColor);
    }

    public static void drawShadowString(final String strCont, final int x, final int y, final int frontColor, final int bgColor, final int anchor) {
        if (bgColor >= 0) {
            drawString(strCont, x + 1, y + 1, bgColor, anchor);
        }
        drawString(strCont, x, y, frontColor, anchor);
    }

    public static void drawLineFeedString(final String[] strCont, final int x, final int y, final int frontColor, final int bgColor, final int anchor) {
        for (int i = 0; i < strCont.length; ++i) {
            if (bgColor >= 0) {
                drawShadowString(strCont[i], x, y + i * IDefines.DRAW_BASE_LINE_FEED_STRING_ROW_SPACE, frontColor, bgColor, anchor);
            } else {
                drawString(strCont[i], x, y + i * IDefines.DRAW_BASE_LINE_FEED_STRING_ROW_SPACE, frontColor, anchor);
            }
        }
    }

    public static void drawArc(final int x, final int y, final int width, final int height, final int startAngle, final int arcAngle) {
        DCanvas.gameG.drawArc(x, y, width, height, startAngle, arcAngle);
    }

    public static void drawImage(final Image img, final int x, final int y, final int anchor) {
        if (img != null) {
            DCanvas.gameG.drawImage(img, x, y, anchor);
        }
    }

    public static void DrawString(final String strCont, final int x, final int y, final int centerX, final int centerY, final int w, final int h, final short Distribution, final int color) {
        final Font oldFont = getFont();
        setFont(Macro.font);
        switch (Distribution) {
            case 1: {
                drawString(strCont, centerX - (getFont().stringWidth(strCont) >> 1), y, color, 20);
                break;
            }
            case 2: {
                drawString(strCont, centerX - (getFont().stringWidth(strCont) >> 1), y + h - getFont().getHeight(), color, 20);
                break;
            }
            case 3: {
                drawString(strCont, x + 3, centerY - (getFont().getHeight() >> 1), color, 20);
                break;
            }
            case 0: {
                drawString(strCont, centerX - (getFont().stringWidth(strCont) >> 1), centerY - (getFont().getHeight() >> 1), color, 20);
                break;
            }
            case 4: {
                drawString(strCont, x + w - getFont().stringWidth(strCont), centerY - (getFont().getHeight() >> 1), color, 20);
                break;
            }
            case -1: {
                drawString(strCont, x, y, color, 20);
                break;
            }
            default: {
                drawString(strCont, x, y, color, 20);
                break;
            }
        }
        setFont(oldFont);
    }

    public static void DrawString(final String strCont, final int framex, final int framey, final int framew, final int frameh, final short Distribution, final int color) {
        int posX = 0;
        int posY = 0;
        if ((Distribution & 0x10) != 0x0) {
            posY = framey;
        } else if ((Distribution & 0x20) != 0x0) {
            posY = framey + frameh - getFont().getHeight();
        } else if ((Distribution & 0x2) != 0x0) {
            posY = framey + (frameh >> 1) - (getFont().getHeight() >> 1);
        }
        if ((Distribution & 0x4) != 0x0) {
            posX = framex;
        } else if ((Distribution & 0x8) != 0x0) {
            posX = framex + framew - getFont().stringWidth(strCont);
        } else if ((Distribution & 0x1) != 0x0) {
            posX = framex + (framew >> 1) - (getFont().stringWidth(strCont) >> 1);
        }
        drawString(strCont, posX, posY, color, 20);
    }

    public static void DrawImage(final Image img, final int framex, final int framey, final int framew, final int frameh, final short Distribution) {
        int posX = 0;
        int posY = 0;
        if ((Distribution & 0x10) != 0x0) {
            posY = framey;
        } else if ((Distribution & 0x20) != 0x0) {
            posY = framey + frameh - img.getHeight();
        } else if ((Distribution & 0x2) != 0x0) {
            posY = framey + (frameh >> 1) - (img.getHeight() >> 1);
        }
        if ((Distribution & 0x4) != 0x0) {
            posX = framex;
        } else if ((Distribution & 0x8) != 0x0) {
            posX = framex + framew - img.getWidth();
        } else if ((Distribution & 0x1) != 0x0) {
            posX = framex + (framew >> 1) - (img.getWidth() >> 1);
        }
        drawImage(img, posX, posY, 20);
    }

    public static void drawLine(final int x1, final int y1, final int x2, final int y2, final int color) {
        final int oldColor = getColor();
        setColor(color);
        DCanvas.gameG.drawLine(x1, y1, x2, y2);
        setColor(oldColor);
    }

    public static void drawRect(final int x, final int y, final int width, final int height, final int color) {
        final int oldColor = getColor();
        setColor(color);
        DCanvas.gameG.drawRect(x, y, width, height);
        setColor(oldColor);
    }

    public static void drawRoundRect(final int x, final int y, final int width, final int height, final int startAngle, final int arcAngle, final int color) {
        final int oldColor = getColor();
        setColor(color);
        DCanvas.gameG.drawRoundRect(x, y, width, height, startAngle, arcAngle);
        setColor(oldColor);
    }

    public static void drawRegion(final Image img, final int x, final int y, final int imgX, final int imgY, final int imgWidth, final int imgHeight, final int transform, final int anchor) {
        if (img == null || imgX + imgWidth > img.getWidth() || imgY + imgHeight > img.getHeight()) {
            return;
        }
        DCanvas.gameG.drawRegion(img, imgX, imgY, imgWidth, imgHeight, transform, x, y, anchor);
    }

    public static void fillRect(final int x, final int y, final int width, final int height, final int color) {
        final int oldColor = getColor();
        setColor(color);
        DCanvas.gameG.fillRect(x, y, width, height);
        setColor(oldColor);
    }

    public static void fillRoundRect(final int x, final int y, final int width, final int height, final int arcWidth, final int arcHeight, final int color) {
        final int oldColor = getColor();
        setColor(color);
        DCanvas.gameG.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
        setColor(oldColor);
    }

    public static void fillTriangle(final int x1, final int y1, final int x2, final int y2, final int x3, final int y3, final int color) {
        final int oldColor = getColor();
        setColor(color);
        DCanvas.gameG.fillTriangle(x1, y1, x2, y2, x3, y3);
        setColor(oldColor);
    }

    public static void setColor(final int RGB) {
        DCanvas.gameG.setColor(RGB);
    }

    public static void setColor(final int red, final int green, final int blue) {
        DCanvas.gameG.setColor(red, green, blue);
    }

    public static int getColor() {
        return DCanvas.gameG.getColor();
    }

    public static void setClip(final int x, final int y, final int width, final int height) {
        final int oldColor = DCanvas.gameG.getColor();
        DCanvas.gameG.setClip(x, y, width, height);
    }

    public static void setFont(final Font font) {
        if (font == null) {
            return;
        }
        DCanvas.gameG.setFont(font);
    }

    public static Font getFont() {
        return DCanvas.gameG.getFont();
    }

    public static void drawRGB(final Graphics g, final byte picID, final int framex, final int framey, final int framewidth, final int frameheight) {
        int posX = framex;
        int posY = framey;
        final int rowNum = framewidth / DrawBase.element + 1;
        final int colNum = frameheight / DrawBase.element + 1;
        setClip(framex, framey, framewidth, frameheight);
        for (int row = 0; row < colNum; ++row) {
            posX = framex;
            for (int col = 0; col < rowNum; ++col) {
                if (DrawBase.Transparency != null) {
                    DrawBase.Transparency.drawAnimationFrame(g, 0, picID, posX, posY);
                }
                posX += DrawBase.element;
            }
            posY += DrawBase.element;
        }
        setClip(0, 0, Macro.SCREEN_WIDTH, Macro.SCREEN_HEIGHT);
    }

    public static int[] getRGB(final int _w, final int _h, final int _color) {
        int[] rgb = null;
        rgb = new int[_w * _h];
        for (int i = 0; i < _w * _h; ++i) {
            rgb[i] = _color;
        }
        return rgb;
    }

    public static int[] getElementRGB(final int _w, final int _h, final int _color, final int alpha) {
        int[] rgb = null;
        rgb = new int[_w * _h];
        int backColor = (alpha & 0x7F) << 24 | (_color & 0xFFFFFF);
        backColor &= -1;
        for (int i = 0; i < _w * _h; ++i) {
            rgb[i] = backColor;
        }
        return rgb;
    }

    public static void drawBox(final int x, final int y, final int w, final int h, final int[] border, final boolean isfill) {
        for (int i = border.length - 1; i >= 0; --i) {
            drawRoundRect(x + i, y + i, w - i * 2 - 1, h - i * 2 - 1, 2, 2, border[i]);
            drawLine(x + i + 1, y + i + 1, x + i + 1, y + i + 1, border[i]);
            drawLine(x - i + w - 2, y + i + 1, x - i + w - 2, y + i + 1, border[i]);
            drawLine(x + i + 1, y - i + h - 2, x + i + 1, y - i + h - 2, border[i]);
            drawLine(x - i + w - 2, y - i + h - 2, x - i + w - 2, y - i + h - 2, border[i]);
        }
        if (isfill) {
            fillRect(x + border.length, y + border.length, w - border.length * 2, h - border.length * 2, border[border.length - 1]);
        }
    }

    public static void drawBoxInMenu(final int x, final int y, final int w, final int h, final int[] border, final boolean isfill, final byte picID) {
        DrawBase.GetBottomBoxY = y + h;
        for (int i = border.length - 1; i >= 0; --i) {
            drawRoundRect(x + i, y + i, w - i * 2 - 1, h - i * 2 - 1, 2, 2, border[i]);
            drawLine(x + i + 1, y + i + 1, x + i + 1, y + i + 1, border[i]);
            drawLine(x - i + w - 2, y + i + 1, x - i + w - 2, y + i + 1, border[i]);
            drawLine(x + i + 1, y - i + h - 2, x + i + 1, y - i + h - 2, border[i]);
            drawLine(x - i + w - 2, y - i + h - 2, x - i + w - 2, y - i + h - 2, border[i]);
        }
        if (isfill) {
            drawRGB(DCanvas.gameG, picID, x + border.length, y + border.length, w - border.length * 2, h - border.length * 2);
        }
    }
}
