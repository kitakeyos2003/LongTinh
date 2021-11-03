// Decompiled with: CFR 0.151
// Class Version: 3
package means;

import javax.microedition.io.ConnectionNotFoundException;
import javax.microedition.midlet.MIDlet;

public class Wap {
    public static void wapView(MIDlet mIDlet, String string) {
        try {
            mIDlet.platformRequest(string);
        }
        catch (ConnectionNotFoundException connectionNotFoundException) {
            connectionNotFoundException.printStackTrace();
        }
    }
}
