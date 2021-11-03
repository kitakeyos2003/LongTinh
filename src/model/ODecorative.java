// 
// Decompiled by Procyon v0.5.36
// 
package model;

import means.ImageManager;
import java.util.Vector;

public class ODecorative extends ORole {

    public byte bytTileZ;

    public void newDecorative(final byte _TileX, final byte _TileY, final byte _TileZ) {
        super.bytTileX = _TileX;
        super.bytTileY = _TileY;
        this.bytTileZ = _TileZ;
        super.shtJudgeX = this.getJudgeX();
        super.shtJudgeY = this.getJudgeY();
        super.blnIsDraw = true;
        super.vTask = new Vector(1, 1);
        super.blnTaskOK = true;
    }

    public void setDecorativePic(final int _id, final short _picid, final short _anuid, final int animationid) {
        super.intUserId = _id;
        super.shtPicId = _picid;
        super.shtAnuId = _anuid;
        super.sprAnimation = animationid;
        super.sprInstance = ImageManager.loadSpriteById(3, ImageManager.EncodespriteId(new StringBuffer(String.valueOf(_id)).toString(), new StringBuffer(String.valueOf(_picid)).toString()), new StringBuffer(String.valueOf(_anuid)).toString(), new StringBuffer(String.valueOf(_picid)).toString(), "decorate");
        this.pushTask((byte) (-1), super.bytDirection);
        this.roleTaskAction(0);
    }

    public short getJudgeX() {
        return (short) (super.bytTileX * 16 + 8);
    }

    public short getJudgeY() {
        return (short) ((super.bytTileY + 1 - this.bytTileZ) * 16);
    }

    public int getRoleAnimation(final int action, final int direction) {
        return super.sprAnimation;
    }
}
