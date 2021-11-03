// 
// Decompiled by Procyon v0.5.36
// 
package model;

import means.AStar;
import face.GameUI;
import javax.microedition.lcdui.Graphics;
import means.DebugFrame;
import base.Param;
import means.ImageManager;
import java.util.Vector;

public class PetObject extends ORole {

    public short shtType;
    public boolean blnIsActivation;
    public static boolean blnFight;
    public byte bytCartoon;
    private int intRoleId;
    int petCD;
    int skillnum;
    int skillCD;
    int a;

    static {
        PetObject.blnFight = false;
    }

    public PetObject() {
        this.intRoleId = 0;
        this.petCD = 0;
        this.skillnum = -1;
        this.skillCD = 0;
        this.a = 0;
    }

    public void newPet(final byte _TileX, final byte _TileY) {
        super.bytTileX = _TileX;
        super.bytTileY = _TileY;
        super.shtJudgeX = this.getJudgeX();
        super.shtJudgeY = this.getJudgeY();
        super.bytType = 4;
        super.bytDirection = 2;
        super.vTask = new Vector(1, 1);
        super.blnTaskOK = true;
    }

    public void setPetPic(final int _userId, final short _picid, final short _anuId, final int _roleId, final short _type) {
        super.intUserId = _userId;
        super.shtPicId = _picid;
        super.shtAnuId = _anuId;
        this.intRoleId = _roleId;
        super.bytPicSize = 2;
        super.bytShadowPicSize = 2;
        super.sprInstance = ImageManager.loadSpriteById(4, ImageManager.EncodespriteId(String.valueOf(_roleId) + "/" + super.intUserId, new StringBuffer(String.valueOf(_picid)).toString()), new StringBuffer(String.valueOf(_picid)).toString(), new StringBuffer(String.valueOf(super.shtAnuId)).toString(), "pet", false, false);
        super.intShadeAnimation = (byte) this.getShadeType(super.bytShadowPicSize);
        super.shadeSprInstance = Param.getInstance().sprShadow;
        super.sprAnimation = 0;
        this.shtType = _type;
        this.pushTask((byte) (-1), super.bytDirection);
        this.roleTaskAction(0);
    }

    public void setCartoon(final byte _type) {
        this.bytCartoon = _type;
        super.bytDirection = 2;
        super.bytFrame = 0;
        super.bytFrameIndex = 0;
        super.blnIsRealMove = false;
        this.switchActionState((byte) 3);
        this.setRoleAnimation();
    }

    public void doCartoon() {
        ++super.bytFrame;
    }

    public void setFight(final byte _direction) {
        this.switchActionState((byte) 2);
        super.bytDirection = _direction;
        this.setRoleAnimation();
    }

    public void doFight() {
        ++super.bytFrameIndex;
        if (this.checkRoleActionSpriteDone()) {
            super.bytFrameIndex = 0;
            this.setStand(super.bytDirection);
        }
    }

    public void update() {
        this.updateRoleSkill();
        if (super.sprInstance != null && super.sprAnimation != -1 && super.bytState != 5) {
            super.sprInstance.update();
        }
    }

    public int getRoleAnimation(final int action, final int direction) {
        if (this.shtType != 2 && action == 2) {
            DebugFrame.getInstance().logIn("Error!  \u975e\u6218\u6597\u5ba0\u7269\u4e0d\u80fd\u6218\u6597!");
            return -1;
        }
        if (this.shtType == 0) {
            return 0;
        }
        return action * 4 + direction;
    }

    public void draw(final Graphics g) {
        final int _drawx = super.shtJudgeX - Param.getInstance().CAMERAX;
        final int _drawy = super.shtJudgeY - Param.getInstance().CAMERAY;
        this.draw(g, _drawx, _drawy);
    }

    public void draw(final Graphics g, final int _drawx, final int _drawy) {
        if (GameUI.getInstance().blnFight) {
            this.doFight();
        }
        if (super.shadeSprInstance != null && super.intShadeAnimation != -1) {
            super.shadeSprInstance.drawAnimationFrame(g, super.intShadeAnimation, 0, _drawx, _drawy);
        }
        if (super.sprInstance != null && this.shtType != 0) {
            super.sprInstance.drawAnimation(g, _drawx, _drawy);
        }
    }

    public void checkRole(final byte _TileX, final byte _TileY, final byte _speed) {
        if (Math.abs(super.bytTileX - _TileX) + Math.abs(super.bytTileY - _TileY) > 3) {
            super.bytSpeed = _speed;
            final AStar as = new AStar(super.bytTileX, super.bytTileY, _TileX, _TileY, (byte) 4, (byte) 1);
            final byte[] _path = as.getPath();
            if (_path != null && _path.length > 1) {
                for (int i = 0; i < _path.length - 1; ++i) {
                    this.pushTask((byte) 1, _path[i]);
                }
            } else {
                this.setTileXY(_TileX, _TileY);
            }
        }
    }

    public void setTileXY(final byte _x, final byte _y) {
        super.bytTileX = _x;
        super.bytTileY = _y;
        super.shtJudgeX = this.getJudgeX();
        super.shtJudgeY = this.getJudgeY();
    }
}
