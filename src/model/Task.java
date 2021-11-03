// 
// Decompiled by Procyon v0.5.36
// 
package model;

import means.StringManager;
import base.Param;
import javax.microedition.lcdui.Graphics;
import means.ImageManager;
import java.util.Vector;
import means.QSprite;

public class Task extends ORole {

    public byte bytTaskType;
    public boolean blnShine;
    public boolean blnPass;
    public String strInfo;
    public String strOption;
    public boolean blnAlive;
    public QSprite sprShineInstance;
    public int sprShineAnimation;

    public void newTask(final byte _TileX, final byte _TileY) {
        super.bytTileX = _TileX;
        super.bytTileY = _TileY;
        super.shtJudgeX = this.getJudgeX();
        super.shtJudgeY = this.getJudgeY();
        super.bytType = 5;
        super.vTask = new Vector(1, 1);
        super.blnTaskOK = true;
    }

    public void setTaskPic(final short _picId) {
        super.bytPicSize = (byte) ImageManager.addTaskImage(_picId, 3);
        super.bytShadowPicSize = (byte) ImageManager.addTaskImage(_picId, 5);
        final short picHeight = ImageManager.addTaskImage(_picId, 4);
        super.shtPicId = (byte) ImageManager.addTaskImage(_picId, 1);
        super.shtAnuId = (byte) ImageManager.addTaskImage(_picId, 2);
        super.sprInstance = ImageManager.loadSpriteById(3, ImageManager.EncodespriteId(new StringBuffer(String.valueOf(super.shtPicId)).toString(), new StringBuffer(String.valueOf(super.shtPicId)).toString()), new StringBuffer(String.valueOf(super.shtPicId)).toString(), new StringBuffer(String.valueOf(super.shtAnuId)).toString(), "task", false, false);
        this.sprShineInstance = ImageManager.loadSpriteById(3, this.getRoleSpriteEncode("questitem"), "questitem", "questitem", "ingame", false, false);
        super.sprAnimation = 1;
        super.sprInstance.setAnimation(super.sprAnimation);
        super.shtImgWidth = (short) (super.bytPicSize * 16);
        super.shtImgHeight = picHeight;
        this.blnPass = false;
        super.blnIsDraw = true;
        this.pushTask((byte) (-1), super.bytDirection);
        this.roleTaskAction(0);
    }

    public void delTask() {
        this.setTaskState(false);
        this.sprShineInstance = null;
        super.sprInstance = null;
        super.blnIsDraw = false;
    }

    public void setTaskState(final boolean _canUnlock) {
        if ((!this.blnShine || _canUnlock) && !this.blnShine && _canUnlock) {
            super.blnIsDraw = true;
        }
        this.blnShine = _canUnlock;
    }

    public void update() {
        if (super.blnIsDraw && super.sprInstance != null && super.sprAnimation != -1) {
            super.sprInstance.update();
            if (this.blnShine && this.sprShineInstance != null) {
                if (this.sprShineInstance.getAnimation() == -1) {
                    this.sprShineAnimation = 0;
                    this.sprShineInstance.setAnimation(this.sprShineAnimation);
                }
                this.sprShineInstance.update();
            }
        }
    }

    public void draw(final Graphics g) {
        final int drawX = super.shtJudgeX - Param.getInstance().CAMERAX;
        final int drawY = super.shtJudgeY - Param.getInstance().CAMERAY;
        if (super.blnIsDraw && super.sprInstance != null) {
            super.sprInstance.drawAnimation(g, drawX, drawY);
            if (this.blnShine) {
                if (this.sprShineInstance != null && this.sprShineInstance.getAnimation() != -1) {
                    this.sprShineInstance.drawAnimation(g, drawX, drawY);
                }
                StringManager.drawShadowWord(g, super.strNickName, drawX, drawY - super.shtImgHeight, 2424772, 682828, 33);
            }
        }
    }

    public int getRoleAnimation(final int action, final int direction) {
        return 1;
    }
}
