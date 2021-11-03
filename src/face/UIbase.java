// 
// Decompiled by Procyon v0.5.36
// 
package face;

import javax.microedition.lcdui.Graphics;

public interface UIbase {

    void init();

    void isThis(final boolean p0);

    void paint(final Graphics p0);

    void logic(final int p0);

    void clean();
}
