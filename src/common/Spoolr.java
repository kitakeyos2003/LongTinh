// 
// Decompiled by Procyon v0.5.36
// 
package common;

import base.DCanvas;
import javax.microedition.lcdui.Graphics;

public class Spoolr {

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

    public Spoolr() {
        this.arrowhead_width = 15;
        this.arrowhead_height = 10;
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
}
