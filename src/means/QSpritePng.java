// Decompiled with: CFR 0.151
// Class Version: 3
package means;

import javax.microedition.lcdui.Image;
import means.ImageManager;
import means.QSpriteRes;

public class QSpritePng
extends QSpriteRes {
    private Image m_png = null;

    public QSpritePng() {
        this.res_type = 1;
    }

    public static QSpritePng load(int n, String string) throws Exception {
        QSpritePng qSpritePng = new QSpritePng();
        qSpritePng.spriteId = string;
        qSpritePng.spritePoolType = n;
        byte[] byArray = ImageManager.getResDataByteArray(string);
        try {
            if (byArray != null) {
                qSpritePng.m_png = Image.createImage(byArray, 0, byArray.length);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        if (qSpritePng.m_png == null) {
            return null;
        }
        return qSpritePng;
    }

    public Image getImage() {
        return this.m_png;
    }
}
