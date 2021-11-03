// 
// Decompiled by Procyon v0.5.36
// 
package face;

import means.ImageManager;
import common.Common;
import base.Param;
import javax.microedition.lcdui.Graphics;
import base.Macro;
import java.util.Random;
import means.QSprite;

public class Peffect {

    private short shtPeffectW;
    private short shtPeffectH;
    private byte bytPeffectType;
    QSprite weaterSprite;
    private boolean blnIsOver;
    private int intTimer;
    private Random gameRan;
    private byte[] bytBladderPlace;
    private byte bytWindSnow;
    private byte bytWindDearing;
    private byte bytWindIntensity;
    private byte bytPeffectNumber;
    private int[][] intsPeffect;
    private byte bytCounter;
    private byte bytPersistTime;
    private Peffect me;

    public Peffect() {
        this.weaterSprite = null;
        this.gameRan = null;
        this.bytBladderPlace = null;
        this.bytPersistTime = 20;
        this.me = this;
        this.shtPeffectW = Macro.SCREEN_WIDTH;
        this.shtPeffectH = Macro.SCREEN_HEIGHT;
    }

    public void clear() {
        this.me = null;
    }

    public void setIsOver() {
        this.intTimer = 7000;
        this.blnIsOver = false;
    }

    public void setPeffect(final byte _type, final byte _direction) {
        this.blnIsOver = true;
        this.gameRan = new Random();
        this.bytWindDearing = (byte) ((_direction == 1) ? 1 : -1);
        final boolean _tempbln = Macro.SCREEN_WIDTH > 230;
        switch (_type) {
            case 1: {
                this.bytPeffectNumber = (byte) (_tempbln ? 90 : 50);
                this.initRain();
                this.bytWindIntensity = (byte) (((_direction == 1) ? 1 : -1) * 6);
                break;
            }
            case 2: {
                this.bytPeffectNumber = (byte) (_tempbln ? 80 : 40);
                this.initSnow();
                this.bytWindIntensity = (byte) (((_direction == 1) ? 1 : -1) * 4);
                break;
            }
            case 3: {
                this.bytPeffectNumber = 8;
                this.initCloud();
                this.bytWindIntensity = (byte) (((_direction == 1) ? 1 : -1) * 2);
                break;
            }
            case 4: {
                this.bytPeffectNumber = (byte) Math.abs(this.gameRan.nextInt() % (Macro.SCREEN_WIDTH / 30) + 2);
                this.initDandelion();
                break;
            }
            case 5: {
                this.bytPeffectNumber = (byte) (_tempbln ? 30 : 15);
                this.initFlower();
                this.bytWindIntensity = (byte) (((_direction == 1) ? 1 : -1) * 5);
                break;
            }
            case 6: {
                this.bytPeffectNumber = (byte) Math.abs(this.gameRan.nextInt() % (Macro.SCREEN_WIDTH / 30) + 2);
                this.initBladder();
                break;
            }
        }
        this.bytPeffectType = _type;
    }

    public void logic(final int _time) {
        if (!this.blnIsOver && this.intTimer > 0) {
            this.intTimer -= _time;
        }
        short _index = 0;
        switch (this.bytPeffectType) {
            case 1: {
                _index = this.logicRain();
                break;
            }
            case 3: {
                _index = this.logicCloud();
                break;
            }
            case 4: {
                _index = this.logicBladder();
                break;
            }
            case 2:
            case 5: {
                _index = this.logicSnowAndFlower();
                break;
            }
            case 6: {
                _index = this.logicBladder();
                if (this.weaterSprite != null) {
                    this.weaterSprite.update();
                    break;
                }
                break;
            }
        }
        if (_index >= this.intsPeffect.length) {
            this.clear();
        }
    }

    public void print(final Graphics g) {
        switch (this.bytPeffectType) {
            case 1: {
                for (short i = 0; i < this.intsPeffect.length; ++i) {
                    g.setColor(this.intsPeffect[i][5]);
                    if (this.intsPeffect[i][0] == 0) {
                        g.fillRect(this.intsPeffect[i][6] - Param.getInstance().CAMERAX, this.intsPeffect[i][7] - Param.getInstance().CAMERAY, 1, 1);
                    } else if (this.intsPeffect[i][0] != -1) {
                        g.drawLine(this.intsPeffect[i][6] - Param.getInstance().CAMERAX, this.intsPeffect[i][7] - Param.getInstance().CAMERAY, this.intsPeffect[i][6] - Param.getInstance().CAMERAX + this.bytWindDearing * this.intsPeffect[i][1], this.intsPeffect[i][7] - Param.getInstance().CAMERAY + this.intsPeffect[i][2]);
                    }
                }
                break;
            }
            case 3: {
                for (short i = 0; i < this.intsPeffect.length; ++i) {
                    if (Common.judgeIsInterrupt(this.intsPeffect[i][1], 50, this.intsPeffect[i][2] + 30, 30) && this.weaterSprite != null && this.intsPeffect[i][0] != -1) {
                        this.weaterSprite.drawAnimationFrame(g, this.intsPeffect[i][0], 0, this.intsPeffect[i][1] - Param.getInstance().CAMERAX, this.intsPeffect[i][2] - Param.getInstance().CAMERAY);
                    }
                }
                break;
            }
            case 4: {
                for (short i = 0; i < this.intsPeffect.length; ++i) {
                    if (this.intsPeffect[i][3] >= 0 && this.intsPeffect[i][0] != -1) {
                        this.weaterSprite.drawAnimationFrame(g, this.intsPeffect[i][0], 0, this.intsPeffect[i][1] + this.bytBladderPlace[this.intsPeffect[i][3] / 2 * 6] - Param.getInstance().CAMERAX, this.intsPeffect[i][2] + this.bytBladderPlace[this.intsPeffect[i][3] / 2 * 6 + 1] - Param.getInstance().CAMERAY);
                    }
                }
                break;
            }
            case 2:
            case 5: {
                for (short i = 0; i < this.intsPeffect.length; ++i) {
                    if (this.bytPeffectType == 2) {
                        g.setColor(11599870);
                        if (this.intsPeffect[i][0] != 0) {
                            g.drawLine(this.intsPeffect[i][1] - Param.getInstance().CAMERAX, this.intsPeffect[i][2] - Param.getInstance().CAMERAY, this.intsPeffect[i][1] - Param.getInstance().CAMERAX, this.intsPeffect[i][2] - Param.getInstance().CAMERAY);
                        } else {
                            g.drawRect(this.intsPeffect[i][1] - Param.getInstance().CAMERAX, this.intsPeffect[i][2] - Param.getInstance().CAMERAY, 1, 1);
                        }
                    }
                    if (this.intsPeffect[i][0] == 3 && this.intsPeffect[i][4] == 1) {
                        if (this.weaterSprite != null) {
                            this.weaterSprite.drawAnimationFrame(g, this.intsPeffect[i][0], 0, this.bytBladderPlace[this.intsPeffect[i][5] * 3 + 0] - Param.getInstance().CAMERAX, this.intsPeffect[i][2] + this.bytBladderPlace[this.intsPeffect[i][5] * 3 + 1] - Param.getInstance().CAMERAY);
                        }
                    } else if (this.weaterSprite != null) {
                        this.weaterSprite.drawAnimationFrame(g, this.intsPeffect[i][0], 0, this.intsPeffect[i][1] - Param.getInstance().CAMERAX, this.intsPeffect[i][2] - Param.getInstance().CAMERAY);
                    }
                }
                break;
            }
            case 6: {
                for (short i = 0; i < this.intsPeffect.length; ++i) {
                    if (this.intsPeffect[i][3] >= 0 && this.intsPeffect[i][0] != -1) {
                        this.weaterSprite.drawAnimation(g, this.intsPeffect[i][1] + this.bytBladderPlace[this.intsPeffect[i][3] / 2 * 6] - Param.getInstance().CAMERAX, this.intsPeffect[i][2] + this.bytBladderPlace[this.intsPeffect[i][3] / 2 * 6 + 1] - Param.getInstance().CAMERAY);
                    }
                }
                for (short i = 0; i < this.intsPeffect.length; ++i) {
                    if (this.intsPeffect[i][3] >= 0 && this.intsPeffect[i][0] != -1) {
                        this.weaterSprite.drawAnimationFrame(g, 0, 0, this.intsPeffect[i][1] + this.bytBladderPlace[this.intsPeffect[i][3] / 2 * 6] - Param.getInstance().CAMERAX, this.intsPeffect[i][2] + this.bytBladderPlace[this.intsPeffect[i][3] / 2 * 6 + 1] - Param.getInstance().CAMERAY);
                    }
                }
                break;
            }
        }
    }

    public void initRain() {
        byte _index = 0;
        this.intsPeffect = new int[this.bytPeffectNumber][8];
        for (int i = 0; i < this.bytPeffectNumber; ++i) {
            switch (this.intsPeffect[i][0] = Math.abs(this.gameRan.nextInt() % 12)) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4: {
                    this.intsPeffect[i][1] = 1;
                    this.intsPeffect[i][2] = 2;
                    this.intsPeffect[i][3] = 4;
                    this.intsPeffect[i][4] = 5;
                    this.intsPeffect[i][5] = 14013909;
                    break;
                }
                case 5:
                case 6:
                case 7: {
                    this.intsPeffect[i][1] = 1;
                    this.intsPeffect[i][2] = 3;
                    this.intsPeffect[i][3] = 2;
                    this.intsPeffect[i][4] = 5;
                    this.intsPeffect[i][5] = 14540253;
                    break;
                }
                case 8:
                case 9: {
                    this.intsPeffect[i][1] = 2;
                    this.intsPeffect[i][2] = 4;
                    this.intsPeffect[i][3] = 3;
                    this.intsPeffect[i][4] = 6;
                    this.intsPeffect[i][5] = 15132390;
                    break;
                }
                case 10: {
                    this.intsPeffect[i][1] = 3;
                    this.intsPeffect[i][2] = 5;
                    this.intsPeffect[i][3] = 5;
                    this.intsPeffect[i][4] = 10;
                    this.intsPeffect[i][5] = 15790320;
                    break;
                }
                default: {
                    this.intsPeffect[i][1] = 5;
                    this.intsPeffect[i][2] = 9;
                    this.intsPeffect[i][3] = 9;
                    this.intsPeffect[i][4] = 16;
                    this.intsPeffect[i][5] = 16777215;
                    ++_index;
                    if (_index > 5) {
                        --i;
                        break;
                    }
                    break;
                }
            }
            this.intsPeffect[i][6] = i * 8;
            this.intsPeffect[i][7] = this.gameRan.nextInt() % 100 * this.shtPeffectH / 100 + Param.getInstance().CAMERAY;
        }
    }

    public void initSnow() {
        this.intsPeffect = new int[this.bytPeffectNumber][4];
        if (this.weaterSprite == null) {
            this.weaterSprite = ImageManager.loadSpriteById(3, ImageManager.EncodespriteId("wathers", "wathers"), "wathers", "wathers", "ingame");
        }
        for (int i = 0; i < this.bytPeffectNumber; ++i) {
            this.intsPeffect[i][0] = Math.abs(this.gameRan.nextInt() % 3);
            this.intsPeffect[i][1] = this.gameRan.nextInt() % 100 * this.shtPeffectW / 100 + Param.getInstance().CAMERAX;
            this.intsPeffect[i][2] = this.gameRan.nextInt() % 100 * this.shtPeffectH / 100 + Param.getInstance().CAMERAY;
            this.intsPeffect[i][3] = ((this.intsPeffect[i][0] == 1) ? 1 : 2);
            this.weaterSprite.setAnimation(this.intsPeffect[i][0]);
        }
    }

    public void initFlower() {
        if (this.weaterSprite == null) {
            this.weaterSprite = ImageManager.loadSpriteById(3, ImageManager.EncodespriteId("weatherf", "weatherf"), "weatherf", "weatherf", "ingame");
        }
        this.bytBladderPlace = new byte[]{0, 0, 0, 5, 5, 1, 6, 13, 2, -2, 20, 3, -9, 25, 0, -4, 30, 1, -5, 38, 2, -11, 45, 3, -18, 50, 0, -13, 55, 1, -12, 63, 2, -20, 70, 3};
        this.intsPeffect = new int[this.bytPeffectNumber][6];
        for (int i = 0; i < this.bytPeffectNumber; ++i) {
            this.intsPeffect[i][0] = Math.abs(this.gameRan.nextInt() % 4);
            this.intsPeffect[i][1] = this.gameRan.nextInt() % 100 * this.shtPeffectW / 100 + Param.getInstance().CAMERAX;
            this.intsPeffect[i][2] = this.gameRan.nextInt() % 100 * this.shtPeffectH / 100 + Param.getInstance().CAMERAY;
            this.intsPeffect[i][3] = ((this.intsPeffect[i][0] == 1) ? 1 : 2);
            if (this.intsPeffect[i][0] == 3) {
                this.intsPeffect[i][4] = Math.abs(this.gameRan.nextInt() % 3);
                this.intsPeffect[i][5] = 0;
            }
            this.weaterSprite.setAnimation(this.intsPeffect[i][0]);
        }
    }

    public void initCloud() {
        if (this.weaterSprite == null) {
            this.weaterSprite = ImageManager.loadSpriteById(3, ImageManager.EncodespriteId("yun", "yun"), "yun", "yun", "ingame");
        }
        this.intsPeffect = new int[this.bytPeffectNumber][4];
        for (int i = 0; i < this.bytPeffectNumber; ++i) {
            this.intsPeffect[i][0] = Math.abs(this.gameRan.nextInt() % 6);
            this.intsPeffect[i][1] = Math.abs(this.gameRan.nextInt() % 100) * ((Param.getInstance().shtMapMaxWidth + 50) / 100) + Param.getInstance().CAMERAX;
            this.intsPeffect[i][2] = Math.abs(this.gameRan.nextInt() % 100) * ((Param.getInstance().shtMapMaxHeight + 30) / 100) + Param.getInstance().CAMERAY;
            final int[] array = this.intsPeffect[i];
            final int n = 3;
            int n3;
            if (this.intsPeffect[i][0] == 2) {
                final int[] array2 = this.intsPeffect[i];
                final int n2 = 0;
                array2[n2] = (n3 = array2[n2]) + 1;
            } else {
                n3 = this.intsPeffect[i][0];
            }
            array[n] = n3;
            this.weaterSprite.setAnimation(this.intsPeffect[i][0]);
        }
    }

    public void initDandelion() {
        if (this.weaterSprite == null) {
            this.weaterSprite = ImageManager.loadSpriteById(3, ImageManager.EncodespriteId("weatherp", "weatherp"), "weatherp", "weatherp", "ingame");
        }
        this.bytBladderPlace = new byte[]{0, 0, 0, 0, 6, 6, 1, -5, 0, 0, 6, 6, 1, -12, 0, 6, 9, 9, -1, -17, 0, 6, 9, 9, -3, -25, 9, 0, 11, 11, -5, -30, 9, 0, 11, 11, -3, -44, 20, 1, 14, 14, -6, -55, 20, 1, 14, 14};
        this.intsPeffect = new int[this.bytPeffectNumber][4];
        for (int i = 0; i < this.bytPeffectNumber; ++i) {
            this.intsPeffect[i][0] = Math.abs(this.gameRan.nextInt() % 3);
            this.intsPeffect[i][1] = Math.abs(this.gameRan.nextInt() % 100) * ((Param.getInstance().shtMapMaxWidth + 19) / 100) + Param.getInstance().CAMERAX;
            this.intsPeffect[i][2] = Math.abs(this.gameRan.nextInt() % 100) * ((Param.getInstance().shtMapMaxHeight + 19) / 100) + Param.getInstance().CAMERAY;
            this.intsPeffect[i][3] = -Math.abs(this.gameRan.nextInt() % 5);
            this.weaterSprite.setAnimation(this.intsPeffect[i][0]);
        }
    }

    public void initBladder() {
        if (this.weaterSprite == null) {
            (this.weaterSprite = ImageManager.loadSpriteById(3, ImageManager.EncodespriteId("star", "star"), "star", "star", "loading")).setAnimation(1);
        }
        this.bytBladderPlace = new byte[]{0, 0, 0, 0, 6, 6, 1, -5, 0, 0, 6, 6, 1, -12, 0, 6, 9, 9, -1, -17, 0, 6, 9, 9, -3, -25, 9, 0, 11, 11, -5, -30, 9, 0, 11, 11, -3, -44, 20, 1, 14, 14, -6, -55, 20, 1, 14, 14};
        this.intsPeffect = new int[this.bytPeffectNumber][4];
        for (int i = 0; i < this.bytPeffectNumber; ++i) {
            this.intsPeffect[i][0] = Math.abs(this.gameRan.nextInt() % 2);
            this.intsPeffect[i][1] = Math.abs(this.gameRan.nextInt() % 100) * ((Param.getInstance().shtMapMaxWidth + 19) / 100) + Param.getInstance().CAMERAX;
            this.intsPeffect[i][2] = Math.abs(this.gameRan.nextInt() % 100) * ((Param.getInstance().shtMapMaxHeight + 19) / 100) + Param.getInstance().CAMERAY;
            this.intsPeffect[i][3] = -Math.abs(this.gameRan.nextInt() % 5);
        }
    }

    private short logicRain() {
        short _index = 0;
        for (short i = 0; i < this.intsPeffect.length; ++i) {
            final int[] array = this.intsPeffect[i];
            final int n = 6;
            array[n] += this.bytWindDearing * this.intsPeffect[i][3];
            final int[] array2 = this.intsPeffect[i];
            final int n2 = 7;
            array2[n2] += this.intsPeffect[i][4];
            if (this.blnIsOver) {
                this.intsPeffect[i][7] = (short) ((this.intsPeffect[i][7] - Param.getInstance().CAMERAY + this.shtPeffectH) % this.shtPeffectH + Param.getInstance().CAMERAY);
                this.intsPeffect[i][6] = (short) ((this.intsPeffect[i][6] - Param.getInstance().CAMERAX + this.shtPeffectW) % this.shtPeffectW + Param.getInstance().CAMERAX);
            } else if (this.intTimer <= 0) {
                this.intsPeffect[i][0] = -1;
                ++_index;
            }
        }
        return _index;
    }

    private short logicSnowAndFlower() {
        if (this.bytCounter > 1) {
            --this.bytCounter;
        } else {
            this.bytCounter = (byte) (this.bytPersistTime + this.gameRan.nextInt() % 8);
            this.bytWindSnow = (byte) (this.gameRan.nextInt() % 2);
            while (this.bytWindSnow == 0) {
                this.bytWindSnow = (byte) (this.gameRan.nextInt() % 2);
            }
        }
        short _index = 0;
        for (short i = 0; i < this.intsPeffect.length; ++i) {
            final int[] array = this.intsPeffect[i];
            final int n = 1;
            array[n] += this.intsPeffect[i][3] + this.bytWindIntensity;
            final int[] array2 = this.intsPeffect[i];
            final int n2 = 2;
            array2[n2] += ((this.intsPeffect[i][0] == 1) ? 1 : 2);
            if (this.blnIsOver) {
                this.intsPeffect[i][1] = (short) ((this.intsPeffect[i][1] - Param.getInstance().CAMERAX + this.shtPeffectW) % this.shtPeffectW + Param.getInstance().CAMERAX);
                this.intsPeffect[i][2] = (short) ((this.intsPeffect[i][2] - Param.getInstance().CAMERAY + this.shtPeffectH) % this.shtPeffectH + Param.getInstance().CAMERAY);
            } else if (this.intTimer <= 0) {
                this.intsPeffect[i][0] = -1;
                ++_index;
            }
            if (this.bytCounter <= 1) {
                final int k1 = this.gameRan.nextInt() % 10;
                if (k1 < -6) {
                    this.intsPeffect[i][3] = this.gameRan.nextInt() % 2;
                    while (this.intsPeffect[i][3] == 0) {
                        this.intsPeffect[i][3] = (short) (this.gameRan.nextInt() % 2);
                    }
                } else {
                    this.intsPeffect[i][3] = this.bytWindSnow;
                }
            }
            if (this.bytPeffectType == 3 && this.intsPeffect[i][0] == 3 && this.intsPeffect[i][4] == 1) {
                final int[] array3 = this.intsPeffect[i];
                final int n3 = 5;
                ++array3[n3];
                if (this.intsPeffect[i][5] >= this.bytBladderPlace.length / 3) {
                    final int[] array4 = this.intsPeffect[i];
                    final int n4 = 1;
                    array4[n4] += this.bytBladderPlace[(this.intsPeffect[i][5] - 1) * 3 + 0];
                    final int[] array5 = this.intsPeffect[i];
                    final int n5 = 2;
                    array5[n5] += this.bytBladderPlace[(this.intsPeffect[i][5] - 1) * 3 + 1];
                    this.intsPeffect[i][5] = 0;
                }
            }
        }
        return _index;
    }

    private short logicBladder() {
        short _index = 0;
        for (short i = 0; i < this.intsPeffect.length; ++i) {
            final int[] array = this.intsPeffect[i];
            final int n = 3;
            ++array[n];
            if (this.intsPeffect[i][3] > 12) {
                if (this.blnIsOver) {
                    this.intsPeffect[i][1] = Math.abs(this.gameRan.nextInt() % 100) * ((this.shtPeffectW + 19) / 100) + Param.getInstance().CAMERAX;
                    this.intsPeffect[i][2] = Math.abs(this.gameRan.nextInt() % 100) * ((this.shtPeffectH + 19) / 100) + Param.getInstance().CAMERAY;
                    this.intsPeffect[i][3] = -Math.abs(this.gameRan.nextInt() % 5);
                } else {
                    this.intsPeffect[i][0] = -1;
                    ++_index;
                }
            }
        }
        return _index;
    }

    private short logicCloud() {
        short _index = 0;
        for (short i = 0; i < this.intsPeffect.length; ++i) {
            final int[] array = this.intsPeffect[i];
            final int n = 1;
            array[n] += this.intsPeffect[i][3] + this.bytWindIntensity;
            if (this.intsPeffect[i][1] > Param.getInstance().shtMapMaxWidth) {
                if (this.blnIsOver) {
                    this.intsPeffect[i][1] = -30 - Math.abs(this.gameRan.nextInt() % 50);
                    this.intsPeffect[i][2] = Math.abs(this.gameRan.nextInt() % 100) * ((Param.getInstance().shtMapMaxHeight + 30) / 100);
                } else if (this.intTimer <= 0) {
                    this.intsPeffect[i][0] = -1;
                    ++_index;
                }
            }
        }
        return _index;
    }
}
