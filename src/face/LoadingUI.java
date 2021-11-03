// 
// Decompiled by Procyon v0.5.36
// 
package face;

import network.NetManager;
import common.DrawBase;
import javax.microedition.lcdui.Graphics;
import base.GameControl;
import means.StringManager;
import base.Macro;
import common.Common;
import base.Param;
import means.ImageManager;
import javax.microedition.lcdui.Displayable;
import base.DCanvas;
import game.CMidlet;
import means.QSprite;

public class LoadingUI implements UIbase {

    private static LoadingUI lu;
    private int intnum;
    private int intSpeed;
    private int intSpeedStep;
    private byte bytFreamIndex;
    private int intLoadTime;
    private QSprite loadInstance;
    private QSprite eggInstance;
    private int eggAnimation;
    private String[] strTip;
    private int showWidth;
    private int shtWordMoveY;
    private int intTimer;
    int intAfterSpeed;

    public LoadingUI() {
        this.intnum = 0;
        this.intSpeed = 0;
        this.intSpeedStep = 1;
        (LoadingUI.lu = this).init();
    }

    public void init() {
        CMidlet.DisInstance.setCurrent((Displayable) DCanvas.getInstance());
        DCanvas.getInstance().setFullScreen(true);
        DCanvas.getInstance().setNetLoad(false);
        this.intnum = 0;
        this.intSpeed = 0;
        this.bytFreamIndex = 0;
        this.intLoadTime = 200;
        (this.loadInstance = ImageManager.loadSpriteById(2, ImageManager.EncodespriteId("loading1", "loading1"), "loading1", "loading1", "ui")).setAnimation(0);
        this.eggInstance = ImageManager.loadSpriteById(2, ImageManager.EncodespriteId("loading", "loading"), "loading", "loading", "ui");
        this.eggAnimation = 0;
        this.eggInstance.setAnimation(this.eggAnimation);
        if (Param.getInstance().Tip_Content != null) {
            final String _tip = Param.getInstance().Tip_Content[Common.getRandom(0, Param.getInstance().Tip_Num)];
            this.showWidth = (short) (Macro.SCREEN_WIDTH - 40);
            this.strTip = StringManager.wenZi(_tip, this.showWidth);
            this.setWordMove();
        }
        if (DCanvas.getInstance().UDialog != null) {
            GameControl.getInstance().delUIbase(7);
        }
    }

    public void isThis(final boolean _type) {
    }

    public static LoadingUI getInstance() {
        return LoadingUI.lu;
    }

    public void paint(final Graphics g) {
        DrawBase.fillRect(0, 0, Macro.SCREEN_WIDTH, Macro.SCREEN_HEIGHT, 13866046);
        DrawBase.drawRect(0, 0, Macro.SCREEN_WIDTH, Macro.SCREEN_HEIGHT, 8612165);
        this.drawLoading(g);
        this.drawLoadegg(g);
    }

    private void drawLoading(final Graphics g) {
        if (this.loadInstance != null) {
            this.loadInstance.drawAnimation(g, Macro.SCREEN_WIDTH / 2, Macro.SCREEN_HEIGHT / 2);
        }
    }

    private void updateLoading() {
        if (this.loadInstance != null) {
            this.loadInstance.update();
        }
        if (this.loadInstance == null) {
            return;
        }
    }

    private void drawLoadegg(final Graphics g) {
        final short sht_egg_rect_x = 20;
        final short sht_egg_rect_y = (short) (Macro.SCREEN_HEIGHT / 3 * 2);
        final short sht_egg_rect_w = (short) (Macro.SCREEN_WIDTH - 40);
        final short sht_egg_rect_h = 20;
        short sht_egg_move_w = (short) ((sht_egg_rect_w - 10) * this.intSpeed / 100);
        if (sht_egg_move_w < 0) {
            sht_egg_move_w = 0;
        }
        DrawBase.drawRoundRect(sht_egg_rect_x, sht_egg_rect_y, sht_egg_rect_w, sht_egg_rect_h, 4, 4, 16777215);
        DrawBase.fillRoundRect(sht_egg_rect_x + 1, sht_egg_rect_y + 1, sht_egg_rect_w - 2, sht_egg_rect_h - 2, 4, 4, 8612165);
        DrawBase.drawRoundRect(sht_egg_rect_x + 1, sht_egg_rect_y + 1, sht_egg_rect_w - 2, sht_egg_rect_h - 2, 4, 4, 13866046);
        DrawBase.fillRect(sht_egg_rect_x + 2, sht_egg_rect_y + 2, sht_egg_move_w - 3, sht_egg_rect_h - 3, 1819385);
        DrawBase.drawLine(sht_egg_rect_x + 2, sht_egg_rect_y + 3, sht_egg_rect_x + sht_egg_move_w - 2, sht_egg_rect_y + 3, 5830653);
        DrawBase.drawLine(sht_egg_rect_x + 2, sht_egg_rect_y + sht_egg_rect_h - 2, sht_egg_rect_x + sht_egg_move_w - 2, sht_egg_rect_y + sht_egg_rect_h - 2, 556194);
        if (this.eggInstance != null) {
            this.eggInstance.drawAnimation(g, sht_egg_rect_x + sht_egg_move_w + 5, sht_egg_rect_y + 20);
        }
        this.drawTip();
    }

    private void updateLoadegg() {
        if (this.eggInstance != null) {
            this.eggInstance.update();
        }
    }

    public void logic(final int _time) {
        this.updateLoading();
        this.updateLoadegg();
        if (this.intLoadTime > 0) {
            this.intLoadTime -= _time;
        } else {
            this.intLoadTime = 200;
            ++this.bytFreamIndex;
            if (this.bytFreamIndex > 3) {
                this.bytFreamIndex = 0;
            }
        }
        final int intBeforSpeed = this.intSpeed;
        if (this.intSpeed < this.intnum) {
            final int _speed = this.intSpeedStep;
            this.intSpeed += _speed;
            if (this.intSpeed > 100) {
                this.intSpeed = 100;
            }
        } else if (this.intSpeed >= 100) {
            GameControl.getInstance().delUIbase(6);
        }
        this.timeOutRetryConn();
        if (this.intTimer > 0) {
            this.intTimer -= _time;
        }
    }

    public void clean() {
        LoadingUI.lu = null;
    }

    public void setSpeed(final int _speed) {
        if (this.intnum < _speed) {
            this.intSpeed = this.intnum;
            this.intnum = _speed;
        }
    }

    public void timeOutRetryConn() {
        if (Macro.bytGameType != 1 && NetManager.getInstance().endStartTime == 0L) {
            final long step = System.currentTimeMillis() - NetManager.getInstance().sendStartTime;
            if (NetManager.getInstance().blnTryReConn && step > 60000L) {
                NetManager.getInstance().blnTimeOut = true;
                NetManager.getInstance().autoReConnect();
            }
        }
    }

    public void setWordMove() {
        this.shtWordMoveY = 0;
        this.intTimer = Param.getInstance().Tip_time * 1000;
    }

    public void drawTip() {
        if (this.strTip == null) {
            return;
        }
        final int strTip_y = Macro.SCREEN_HEIGHT / 3 * 2 + 20 + Macro.SCREEN_HEIGHT / 20;
        final int strTip_x = Macro.SCREEN_WIDTH >> 1;
        final int showH = Macro.FONTHEIGHT << 1;
        DrawBase.setClip(0, 0, Macro.SCREEN_WIDTH, Macro.SCREEN_HEIGHT);
        DrawBase.setClip(0, strTip_y, Macro.SCREEN_WIDTH, Macro.FONTHEIGHT << 1);
        if (this.intTimer <= 0 && this.strTip.length * Macro.FONTHEIGHT > showH) {
            this.shtWordMoveY -= 2;
            if (this.shtWordMoveY < -(this.strTip.length * Macro.FONTHEIGHT)) {
                this.shtWordMoveY = showH;
            }
        }
        for (int i = 0; i < this.strTip.length; ++i) {
            DrawBase.drawString(this.strTip[i], strTip_x, strTip_y + i * Macro.FONTHEIGHT + this.shtWordMoveY, 0, 17);
        }
        DrawBase.setClip(0, 0, Macro.SCREEN_WIDTH, Macro.SCREEN_HEIGHT);
    }
}
