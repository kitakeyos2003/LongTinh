// Decompiled with: CFR 0.151
// Class Version: 3
package means;

import base.Param;
import common.Common;
import face.GameUI;
import javax.microedition.lcdui.Graphics;
import means.StringManager;

public final class FightValue {
    public static final byte TYP_yellow = 1;
    public static final byte TYP_red = 2;
    public static final byte TYP_green = 3;
    public static final byte TYP_blue = 4;
    public static final byte TYP_TXT = 5;
    public static final byte DIR_LEFT = 1;
    public static final byte DIR_RIGHT = 2;
    public static final byte DIR_UP = 13;
    public static final byte AddHp = 11;
    public static final byte AaaMp = 21;
    public byte HurtType;
    public int startX;
    private byte type;
    private byte Pictype;
    private int value;
    private int fightValueOffsetX;
    private int fightValueOffsetY;
    private int offsetX;
    private int offsetY;
    private int startY;
    private int velocityH;
    private int velocityV;
    private byte DIR;
    private String info;
    private int fightValueMinVelocityH;
    public static int fightValueMaxVelocityH = 8;
    public static int fightValueVelocityV = 15;
    private int texValueVelocityV = 12;
    private int fightValueAcceleration = -5;
    private int textfightValueAcceleration = -2;

    public FightValue(byte by, byte by2, byte by3, int n, int n2, int n3) {
        this.type = by;
        this.value = n;
        this.fightValueOffsetX = n2;
        this.fightValueOffsetY = n3;
        this.HurtType = by3;
        this.DIR = by2;
        if (this.HurtType == 3) {
            this.DIR = (byte)13;
        }
        if ((by2 = (byte)(this.DIR == 13 ? 13 : (int)this.transdirection(by2))) != 13) {
            this.velocityH = by3 == 2 ? (by2 == 1 ? -fightValueMaxVelocityH : fightValueMaxVelocityH) : (by2 == 1 ? -Common.getRandom(this.fightValueMinVelocityH, fightValueMaxVelocityH + 1) : Common.getRandom(this.fightValueMinVelocityH, fightValueMaxVelocityH + 1));
        }
        this.velocityV = fightValueVelocityV;
        this.startY = this.offsetY;
        if (this.HurtType != 3) {
            if (this.type == 2) {
                this.Pictype = (byte)2;
            } else if (this.type == 3) {
                this.Pictype = 1;
            } else if (this.type == 11) {
                this.Pictype = (byte)3;
            } else if (this.type == 21) {
                this.Pictype = (byte)4;
            }
        }
    }

    public FightValue(byte by, byte by2, String string, int n, int n2) {
        this.type = by;
        this.DIR = by2;
        this.fightValueOffsetX = n;
        this.fightValueOffsetY = n2;
        this.velocityV = this.texValueVelocityV;
        this.info = string;
    }

    private byte transdirection(byte by) {
        byte by2 = 1;
        if (by == 3) {
            by2 = 2;
        }
        return by2;
    }

    public boolean update() {
        this.offsetX += this.velocityH;
        this.offsetY -= this.velocityV;
        this.velocityV += this.type == 5 && this.DIR == 13 ? this.textfightValueAcceleration : this.fightValueAcceleration;
        return (this.type != 5 || this.DIR != 13 || this.velocityV > 0) && this.offsetY <= this.startY;
    }

    public void paint(Graphics graphics, int n, int n2, int n3) {
        if (this.type == 5) {
            StringManager.drawShadowWord(graphics, this.info, n + this.fightValueOffsetX + this.offsetX, n2 + this.fightValueOffsetY + this.offsetY, 16719432, 6029586, n3);
        } else {
            switch (this.Pictype) {
                case 1: {
                    GameUI.getInstance().drawNum(graphics, this.value, n + this.fightValueOffsetX + this.offsetX, n2 + this.offsetY, (byte)1, Param.getInstance().sprYellowNum);
                    break;
                }
                case 2: {
                    GameUI.getInstance().drawNum(graphics, this.value, n + this.fightValueOffsetX + this.offsetX, n2 + this.offsetY, (byte)2, Param.getInstance().sprRedNum);
                    break;
                }
                case 3: {
                    GameUI.getInstance().drawNum(graphics, this.value, n + this.fightValueOffsetX + this.offsetX, n2 + this.offsetY, (byte)3, Param.getInstance().sprGreenNum);
                    break;
                }
                case 4: {
                    GameUI.getInstance().drawNum(graphics, this.value, n + this.fightValueOffsetX + this.offsetX, n2 + this.offsetY, (byte)4, Param.getInstance().sprBlueBNum);
                }
            }
            if (this.HurtType == 2) {
                GameUI.getInstance().drawMiss(graphics, 1, n + this.fightValueOffsetX + this.offsetX, n2 + this.offsetY, Param.getInstance().sprMiss);
            } else if (this.HurtType == 3) {
                GameUI.getInstance().drawMiss(graphics, 0, n + this.fightValueOffsetX + this.offsetX, n2 + this.offsetY, Param.getInstance().sprMiss);
            }
        }
    }
}
