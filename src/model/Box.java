// 
// Decompiled by Procyon v0.5.36
// 
package model;

import javax.microedition.lcdui.Graphics;
import base.Param;
import java.util.Vector;
import means.ImageManager;

public class Box extends ORole {

    public boolean blnShine;
    public boolean blnAlive;
    public boolean blnPickUp;
    public int intMonsterId;
    public byte bytBoxType;

    public void newBox(final int _id, final byte _TileX, final byte _TileY, final byte _type) {
        super.intUserId = _id;
        super.bytType = 4;
        this.bytBoxType = _type;
        super.blnIsDraw = false;
        super.bytTileX = _TileX;
        super.bytTileY = _TileY;
        super.bytPicSize = 1;
        super.shtJudgeX = this.getJudgeX();
        super.shtJudgeY = this.getJudgeY();
        super.sprInstance = ImageManager.loadSpriteById(3, this.getRoleSpriteEncode("bag"), "bag", "bag", "ingame", false, false);
        super.shtImgWidth = 0;
        super.shtImgHeight = 0;
        this.blnAlive = true;
        super.vTask = new Vector(1, 1);
        super.blnTaskOK = true;
        this.pushTask((byte) (-1), super.bytDirection);
        this.roleTaskAction(0);
    }

    public void delBox() {
        super.sprAnimation = -1;
        super.sprInstance = null;
        super.blnIsDraw = false;
    }

    public void setBoxState(final boolean _canPick) {
        if (this.blnShine && !_canPick) {
            super.sprAnimation = 1;
            super.blnIsDraw = false;
        } else if (!this.blnShine && _canPick) {
            super.sprAnimation = 2;
            super.blnIsDraw = true;
        }
        this.blnShine = _canPick;
    }

    public void setBoxAlive(final boolean isAlive) {
        this.blnAlive = isAlive;
        super.sprAnimation = 3;
        this.setRoleAnimation();
    }

    public void setRoleAnimation() {
        super.sprAnimation = this.getRoleAnimation(super.bytState, ORole.transDirection(super.bytDirection));
        if (super.sprInstance != null && super.sprAnimation != -1) {
            if (super.sprAnimation == 3) {
                super.sprInstance.setLoopOffset(1);
            }
            super.sprInstance.setAnimation(super.sprAnimation);
        }
    }

    public int getRoleAnimation(final int action, final int direction) {
        return super.sprAnimation;
    }

    public void update() {
        if (super.sprInstance != null && super.sprAnimation != -1) {
            super.sprInstance.update();
            if (super.sprInstance.getAnimation() == 3 && super.sprInstance.isPlayDone()) {
                this.delBox();
                Param.getInstance().htBox.remove(new Integer(super.intUserId));
                if (Param.getInstance().bytSelectType == 4 && Param.getInstance().intSelectId == super.intUserId) {
                    ORPMe.ME.delSelsectRole();
                }
            }
        }
    }

    public void draw(final Graphics g) {
        final int _drawx = super.shtJudgeX - Param.getInstance().CAMERAX;
        final int _drawy = super.shtJudgeY - Param.getInstance().CAMERAY;
        this.draw(g, _drawx, _drawy);
    }

    public void draw(final Graphics g, final int _drawx, final int _drawy) {
        if (super.blnIsDraw && super.sprInstance != null && super.sprAnimation != -1) {
            super.sprInstance.drawAnimation(g, _drawx, _drawy);
        }
    }
}
