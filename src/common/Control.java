// 
// Decompiled by Procyon v0.5.36
// 
package common;

import java.util.Vector;
import javax.microedition.lcdui.Font;

public class Control {

    public static Vector splitStr(final Font f, final String str, final int length) {
        if (str == null) {
            return null;
        }
        final Vector result = new Vector();
        final char[] tempChar = str.toCharArray();
        int lengthPX = 0;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < tempChar.length; ++i) {
            lengthPX += f.charWidth(tempChar[i]);
            if (lengthPX > length || tempChar[i] == '\n') {
                result.addElement(sb.toString());
                sb = new StringBuffer();
                lengthPX = f.charWidth(tempChar[i]);
                if (tempChar[i] != '\n') {
                    sb.append(tempChar[i]);
                }
            } else {
                sb.append(tempChar[i]);
            }
        }
        if (sb.length() > 0) {
            result.addElement(sb.toString());
        }
        return result;
    }
}
