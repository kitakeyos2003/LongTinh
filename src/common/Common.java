// 
// Decompiled by Procyon v0.5.36
// 
package common;

import base.Param;
import means.DebugFrame;
import java.util.Random;

public class Common {

    private static Common s_common_instance;

    private Common() {
    }

    public static Common getInstance() {
        if (Common.s_common_instance == null) {
            Common.s_common_instance = new Common();
        }
        return Common.s_common_instance;
    }

    public static int readIntFromByte(final byte[] array, int from) {
        final int int24 = (array[from++] & 0xFF) << 24;
        final int int25 = (array[from++] & 0xFF) << 16;
        final int int26 = (array[from++] & 0xFF) << 8;
        final int int27 = array[from++] & 0xFF;
        return int24 + int25 + int26 + int27;
    }

    public static byte[] getByteArrayFromInt(final int val) {
        final byte[] bytArray = {(byte) (val << 24 & 0xFF), (byte) (val << 16 & 0xFF), (byte) (val << 8 & 0xFF), (byte) (val << 0 & 0xFF)};
        return bytArray;
    }

    public short readShortFromByte(final byte[] array, int from) {
        final int int8 = (array[from++] & 0xFF) << 8;
        final int int9 = array[from++] & 0xFF;
        return (short) (int8 + int9);
    }

    public void delByteArrayNumBytes(byte[] array, final int from, final int num) {
        final int length = array.length;
        final byte[] newarray = new byte[length - num];
        System.arraycopy(array, 0, newarray, 0, from);
        System.arraycopy(array, from + num + 1, newarray, from + 1, length - num - from - 1);
        array = newarray;
    }

    public static long pow(final int val, final int mi) {
        long tmpRes = 1L;
        for (int i = 0; i < mi; ++i) {
            tmpRes *= val;
        }
        return tmpRes;
    }

    public int strSub(final String str) {
        final String resStr = str.substring(1, str.length());
        return Integer.parseInt(resStr);
    }

    public static int getRandom(final int from, final int to) {
        final Random random = new Random(System.currentTimeMillis());
        final int k = random.nextInt();
        final int j = Math.abs(k % (to - from)) + from;
        return j;
    }

    public static int Max(final int[] array) {
        if (array == null || array.length == 0) {
            DebugFrame.getInstance().logIn("Error! \u53d6\u5927\u6570\u7ec4\u9519\u8bef\uff01");
            return -1;
        }
        int maxVal = array[0];
        for (int i = 0; i < array.length; ++i) {
            maxVal = Math.max(array[i], maxVal);
        }
        return maxVal;
    }

    public static boolean judgeIsInterrupt(final int _jx, final int _w, final int _jy, final int _h) {
        final int[] rect = {Param.getInstance().CAMERAX, Param.getInstance().CAMERAY, Param.getInstance().shtMapMaxWidth, Param.getInstance().shtMapMaxHeight};
        return judgeIsInterrupt(rect, _jx, _w, _jy, _h, 0);
    }

    public static boolean judgeIsInterrupt(final int[] rect, final int _jx, final int _w, final int _jy, final int _h, final int _extra) {
        final int rectX = rect[0];
        final int rectY = rect[1];
        final int rectW = rect[2];
        final int rectH = rect[3];
        return _jx >= rectX - _w / 2 - _extra && _jx <= rectX + rectW + _w / 2 + _extra && _jy >= rectY - _h / 2 - _extra && _jy <= rectY + rectH + _h / 2 + _extra;
    }

    public static String strFormat(final int data, final int bitNumber) {
        String str;
        for (str = String.valueOf(data); str.length() < bitNumber; str = "0" + str) {
        }
        return str;
    }

    public static boolean isUnicodeExist(final String txt) {
        if (txt != null) {
            final char[] charArray = txt.toCharArray();
            for (int i = 0; i < charArray.length; ++i) {
                final int charVal = charArray[i];
                if ((charVal < 48 || charVal > 57) && (charVal < 65 || charVal > 90) && (charVal < 97 || charVal > 122)) {
                    return true;
                }
            }
        }
        return false;
    }
}
