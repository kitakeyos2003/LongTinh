// 
// Decompiled by Procyon v0.5.36
// 
package model;

import means.ImageManager;
import java.util.Vector;

public class AnimalObject extends ORole {

    public boolean blnIsActivation;

    public void newAnimal(final byte _TileX, final byte _TileY) {
        super.bytTileX = _TileX;
        super.bytTileY = _TileY;
        super.shtJudgeX = this.getJudgeX();
        super.shtJudgeY = this.getJudgeY();
        super.bytDirection = 2;
        super.blnIsDraw = true;
        super.vTask = new Vector(1, 1);
        super.blnTaskOK = true;
    }

    public void setAnimalPic(final int _userId, final short _anuid, final short _picId) {
        super.intUserId = _userId;
        super.shtAnuId = _anuid;
        super.shtPicId = _picId;
        super.bytSpeed = 3;
        super.sprInstance = ImageManager.loadSpriteById(3, ImageManager.EncodespriteId(new StringBuffer(String.valueOf(super.intUserId)).toString(), new StringBuffer(String.valueOf(super.shtPicId)).toString()), new StringBuffer(String.valueOf(super.shtAnuId)).toString(), new StringBuffer(String.valueOf(super.shtPicId)).toString(), "npc", false, false);
        this.pushTask((byte) (-1), super.bytDirection);
        this.roleTaskAction(0);
    }

    public void setRoleAnimation() {
        super.sprAnimation = this.getRoleAnimation(super.bytState, ORole.transDirection(super.bytDirection));
        if (super.sprInstance != null && super.sprAnimation != -1 && super.bytState != 5) {
            super.sprInstance.setAnimation(super.sprAnimation);
        }
    }

    public int getRoleAnimation(final int action, final int direction) {
        return action * 4 + direction;
    }
}
