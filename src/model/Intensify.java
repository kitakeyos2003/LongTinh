// 
// Decompiled by Procyon v0.5.36
// 
package model;

import means.ImageManager;
import javax.microedition.lcdui.Image;
import common.DrawBase;
import base.Param;
import javax.microedition.lcdui.Graphics;

public class Intensify {

    public byte bytIntensifyId;
    public byte byteIntensifyIndex;
    public byte bytFrameMax;
    public byte bytNowIndex;
    public byte bytNowFrame;

    public void setIntensifyId(final byte _Id) {
        if (this.bytIntensifyId != _Id) {
            if (this.bytIntensifyId != 0) {
                this.delImg(this.bytIntensifyId);
            }
            this.addImg(this.getID(_Id));
        }
        this.bytIntensifyId = _Id;
    }

    private byte getID(final byte _Id) {
        switch (_Id) {
            case 2:
            case 3:
            case 4: {
                return 1;
            }
            case 5:
            case 6:
            case 7: {
                return 2;
            }
            case 8:
            case 9: {
                return 3;
            }
            case 10:
            case 11: {
                return 4;
            }
            case 12: {
                return 5;
            }
            default: {
                return -1;
            }
        }
    }

    public void draw(final Graphics g, final byte _frame, final byte _index, final byte _WeaponId, final int _drawx, final int _drawy) {
        if (_frame < 4) {
            ++this.bytNowFrame;
            if (this.bytNowFrame >= this.bytFrameMax) {
                this.bytNowFrame = 0;
            }
            if (this.bytNowIndex != _index) {
                this.bytNowIndex = _index;
            }
            try {
                synchronized (Param.getInstance().imgIntensify) {
                    if (Param.getInstance().imgIntensify[this.byteIntensifyIndex] != null) {
                        DrawBase.drawRegion(Param.getInstance().imgIntensify[this.byteIntensifyIndex], _drawx + Param.getInstance().shtIntensifyXY[this.byteIntensifyIndex][_frame * 2 + this.bytNowIndex][0] + Param.getInstance().bytWeaponXY[_frame][_WeaponId * 2] + Param.getInstance().shtIntensifyXY[this.byteIntensifyIndex][8][0] / 2, _drawy + Param.getInstance().shtIntensifyXY[this.byteIntensifyIndex][_frame * 2 + this.bytNowIndex][1] - 29 + Param.getInstance().bytWeaponXY[_frame][_WeaponId * 2 + 1] + Param.getInstance().shtIntensifyXY[this.byteIntensifyIndex][8][1] / 2, this.bytNowFrame / 2 * Param.getInstance().shtIntensifyXY[this.byteIntensifyIndex][8][0], 0, Param.getInstance().shtIntensifyXY[this.byteIntensifyIndex][8][0], Param.getInstance().shtIntensifyXY[this.byteIntensifyIndex][8][1], 0, 3);
                    }
                }
                // monitorexit(Param.getInstance().imgIntensify)
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void addImg(final byte _Id) {
        this.bytNowFrame = 0;
        if (Param.getInstance().imgIntensify == null) {
            Param.getInstance().imgIntensify = new Image[5];
            Param.getInstance().bytsIntensifyId = new byte[5][2];
            Param.getInstance().shtIntensifyXY = new short[5][9][2];
        }
        synchronized (Param.getInstance().imgIntensify) {
            byte _tempI = -1;
            while (true) {
                for (byte i = 0; i < 5; ++i) {
                    if (Param.getInstance().bytsIntensifyId[i][0] == _Id) {
                        this.byteIntensifyIndex = i;
                        if (Param.getInstance().imgIntensify[this.byteIntensifyIndex] == null) {
                            Param.getInstance().imgIntensify[this.byteIntensifyIndex] = ImageManager.CreateImage("i" + _Id, "role");
                            if (Param.getInstance().bytWeaponXY == null) {
                                Param.getInstance().bytWeaponXY = new byte[][]{{-2, 2, -2, 2, -3, 2, 1, -4, -2, -1, -1, -1}, {10, 5, 10, 6, 12, 1, 3, -3, 15, 3, 4, -1}, {5, 5, -3, 2, -1, 3, -5, -6, 1, 5, -4, -6}, {-11, 4, -11, 3, -16, 1, -10, -7, -12, 1, -11, -2}};
                            }
                            switch (_Id) {
                                case 1: {
                                    Param.getInstance().shtIntensifyXY[this.byteIntensifyIndex] = new short[][]{{7, 9}, {7, 10}, {7, 11}, {7, 12}, {-1, 11}, {-1, 12}, {3, 10}, {3, 11}, {13, 12}};
                                    break;
                                }
                                case 2: {
                                    Param.getInstance().shtIntensifyXY[this.byteIntensifyIndex] = new short[][]{{5, 8}, {5, 9}, {7, 9}, {7, 10}, {-3, 10}, {-3, 11}, {3, 7}, {3, 8}, {15, 15}};
                                    break;
                                }
                                case 3: {
                                    Param.getInstance().shtIntensifyXY[this.byteIntensifyIndex] = new short[][]{{3, 2}, {3, 3}, {2, 3}, {2, 4}, {-4, 4}, {-4, 5}, {1, 1}, {1, 2}, {22, 25}};
                                    break;
                                }
                                case 4: {
                                    Param.getInstance().shtIntensifyXY[this.byteIntensifyIndex] = new short[][]{{1, 3}, {1, 4}, {0, 5}, {0, 6}, {-6, 8}, {-6, 9}, {0, 3}, {0, 4}, {24, 25}};
                                    break;
                                }
                                case 5: {
                                    Param.getInstance().shtIntensifyXY[this.byteIntensifyIndex] = new short[][]{{3, 2}, {3, 3}, {1, 5}, {1, 6}, {-6, 7}, {-6, 8}, {2, 3}, {2, 4}, {21, 21}};
                                    break;
                                }
                            }
                        }
                        switch (_Id) {
                            case 1: {
                                this.bytFrameMax = 12;
                                break;
                            }
                            case 2: {
                                this.bytFrameMax = 16;
                                break;
                            }
                            case 3: {
                                this.bytFrameMax = 8;
                                break;
                            }
                            case 4: {
                                this.bytFrameMax = 10;
                                break;
                            }
                            case 5: {
                                this.bytFrameMax = 10;
                                break;
                            }
                        }
                        final byte[] array = Param.getInstance().bytsIntensifyId[this.byteIntensifyIndex];
                        final int n = 1;
                        ++array[n];
                        // monitorexit(Param.getInstance().imgIntensify)
                        return;
                    }
                    if (Param.getInstance().bytsIntensifyId[i][0] == 0 && _tempI == -1) {
                        _tempI = i;
                    }
                }
                this.byteIntensifyIndex = _tempI;
                Param.getInstance().bytsIntensifyId[this.byteIntensifyIndex][0] = _Id;
                continue;
            }
        }
    }

    public void delImg(byte _Id) {
        synchronized (Param.getInstance().imgIntensify) {
            _Id = this.getID(_Id);
            byte _tempI = -1;
            for (byte i = 0; i < 5; ++i) {
                if (Param.getInstance().bytsIntensifyId[i][0] == _Id) {
                    _tempI = i;
                    break;
                }
            }
            if (_tempI != -1) {
                final byte[] array = Param.getInstance().bytsIntensifyId[_tempI];
                final int n = 1;
                --array[n];
                if (Param.getInstance().bytsIntensifyId[_tempI][1] <= 0) {
                    Param.getInstance().bytsIntensifyId[_tempI][0] = 0;
                    Param.getInstance().bytsIntensifyId[_tempI][1] = 0;
                    Param.getInstance().imgIntensify[this.byteIntensifyIndex] = null;
                    Param.getInstance().shtIntensifyXY[this.byteIntensifyIndex] = null;
                }
            }
            for (byte i = 0; i < 5; ++i) {
                if (Param.getInstance().imgIntensify[i] != null) {
                    // monitorexit(Param.getInstance().imgIntensify)
                    return;
                }
            }
            Param.getInstance().imgIntensify = null;
            Param.getInstance().bytsIntensifyId = null;
            Param.getInstance().shtIntensifyXY = null;
            Param.getInstance().bytWeaponXY = null;
        }
        // monitorexit(Param.getInstance().imgIntensify)
    }
}
