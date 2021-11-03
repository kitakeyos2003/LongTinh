// 
// Decompiled by Procyon v0.5.36
// 
package model;

import common.LayoutStyle;
import javax.microedition.lcdui.Graphics;
import face.GameUI;
import face.DialogUI;
import means.DebugFrame;
import means.StringManager;
import base.Macro;
import base.Param;
import means.ImageManager;
import java.util.Vector;

public class ORMonster extends ORole {

    public byte bytChaseX;
    public byte bytChaseY;
    public byte bytConcealX;
    public byte bytConcealY;
    public boolean blnHaveBox;
    public boolean blnInitiative;
    private String[] strAIChat;
    private boolean blnDrawAIChat;
    public boolean blnIsBoss;
    public byte bytPhyleType;
    public boolean blnIsabsorb;
    public boolean blnIsSetPass;
    public byte bytCartoon;
    public short shtUpDataId;

    public ORMonster() {
        super.bytType = 3;
        super.bytRoleMoveFrame = 4;
        super.vTask = new Vector(1, 1);
        super.blnTaskOK = true;
        super.shtPicIdDefault = 280;
        super.shtAnuIdDefault = 280;
    }

    public void setRoleFrame(final int userid, final short pngId, final short anuid) {
        super.intUserId = userid;
        super.shtPicId = pngId;
        super.shtAnuId = pngId;
        super.strResPath = "orge";
        super.intPoolType = 3;
        super.bytPicSize = ORole.transPicSize((byte) ImageManager.addMonsterImage(pngId, 3));
        super.bytShadowPicSize = ORole.transPicSize((byte) ImageManager.addMonsterImage(pngId, 5));
        super.shtImgWidth = (short) (super.bytPicSize * 16);
        super.shtImgHeight = ImageManager.addMonsterImage(pngId, 4);
        super.sprInstance = ImageManager.loadSpriteById(3, this.getRoleSpriteEncode(super.shtPicId), new StringBuffer(String.valueOf(super.shtPicId)).toString(), new StringBuffer(String.valueOf(super.shtAnuId)).toString(), super.strResPath, false, false);
        if (super.sprInstance.getData() == null || super.sprInstance.getPng() == null) {
            super.blnUseDefault = true;
            super.sprInstance = ImageManager.loadSpriteById(super.intPoolType, ImageManager.EncodespriteId(new StringBuffer(String.valueOf(super.intUserId)).toString(), new StringBuffer(String.valueOf(super.shtPicId)).toString()), new StringBuffer(String.valueOf(super.shtAnuIdDefault)).toString(), new StringBuffer(String.valueOf(super.shtPicIdDefault)).toString(), super.strResPath);
        }
        (super.sprDeadInstance = ImageManager.loadSpriteById(3, this.getRoleSpriteEncode("deadman"), "deadman", "deadman", "ingame")).setAnimation(0);
        super.shadeSprInstance = Param.getInstance().sprShadow;
        super.intShadeAnimation = (byte) this.getShadeType(super.bytShadowPicSize);
        super.shtDrawNameX = 0;
        super.shtDrawNameY = (short) (-super.shtImgHeight);
        this.pushTask((byte) (-1), super.bytDirection);
        this.roleTaskAction(0);
    }

    public void setChat(final String _str) {
        super.strChat = StringManager.wenZi(_str, Macro.SCREEN_WIDTH / 2);
    }

    public void setAIChat(final String _str) {
        this.strAIChat = StringManager.wenZi(_str, Macro.SCREEN_WIDTH / 2);
        this.blnDrawAIChat = true;
        super.bytChatTime = 0;
    }

    protected void setMove(final byte _direction) {
        super.blnIsRealMove = false;
        super.bytDirection = _direction;
        this.switchActionState((byte) 1);
        super.bytFrame = 0;
        super.bytBlockPixel = 16;
        if (super.bytSpeed != 0) {
            super.bytRoleMoveFrame = super.bytSpeed;
            super.bytSpeed = 0;
        }
        if (super.bytDirection == 1) {
            if (Map.getInstance().checkIsPass(super.bytTileX, super.bytTileY - 1)) {
                this.changeTile(0, -1);
                super.blnIsRealMove = true;
            }
        } else if (super.bytDirection == 2) {
            if (Map.getInstance().checkIsPass(super.bytTileX, super.bytTileY + 1)) {
                this.changeTile(0, 1);
                super.blnIsRealMove = true;
            }
        } else if (super.bytDirection == 3) {
            if (Map.getInstance().checkIsPass(super.bytTileX - 1, super.bytTileY)) {
                this.changeTile(-1, 0);
                super.blnIsRealMove = true;
            }
        } else if (super.bytDirection == 4 && Map.getInstance().checkIsPass(super.bytTileX + 1, super.bytTileY)) {
            this.changeTile(1, 0);
            super.blnIsRealMove = true;
        }
        if (!Map.getInstance().judgeIsDraw(super.shtJudgeX, super.shtImgWidth, super.shtJudgeY, super.shtImgHeight)) {
            super.blnTaskOK = true;
            if (super.bytDirection == 1) {
                super.shtJudgeY -= super.bytBlockPixel;
            } else if (super.bytDirection == 2) {
                super.shtJudgeY += super.bytBlockPixel;
            } else if (super.bytDirection == 4) {
                super.shtJudgeX += super.bytBlockPixel;
            } else if (super.bytDirection == 3) {
                super.shtJudgeX -= super.bytBlockPixel;
            }
            this.checkEvent();
            this.endMove();
            super.blnIsRealMove = false;
            this.switchActionState((byte) 0);
        }
        this.setRoleAnimation();
    }

    protected void setStand(final byte _direction) {
        this.switchActionState((byte) 0);
        super.blnTaskOK = true;
        super.bytFrame = 0;
        super.blnIsRealMove = false;
        super.bytDirection = _direction;
        if (super.sprInstance.spriteId.equals("1712/207")) {
            DebugFrame.getInstance().logIn("Stand" + super.sprInstance.getAnimation() + "/" + super.sprInstance.getCurrentFrame());
        }
        this.setRoleAnimation();
    }

    protected void setDead(final byte _direction) {
        this.switchActionState((byte) 5);
        super.bytFrame = 0;
        super.bytDirection = _direction;
        super.bytFrameIndex = 0;
        super.bytSpecialtiesState = null;
        super.intsDeBuff = null;
        super.strsDeBuff = null;
        super.intsBuff = null;
        super.strsBuff = null;
        if (DialogUI.getInstance() != null) {
            DialogUI.getInstance().delDialogBuff(super.intUserId);
        }
        if (Param.getInstance().bytSelectType == 3 && Param.getInstance().intSelectId == super.intUserId) {
            ORPMe.ME.delSelsectRole();
        }
        if (!this.blnIsSetPass) {
            this.blnIsSetPass = true;
        }
        if (!Map.getInstance().judgeIsDraw(super.shtJudgeX, super.shtImgWidth, super.shtJudgeY, super.shtImgHeight)) {
            this.delthis();
        }
        this.setRoleAnimation();
        super.bytFrameMax = (byte) (this.getSpriteFrameMax(super.sprDeadInstance, 1) + 5);
    }

    protected void doDead() {
        ++super.bytFrame;
        ++super.bytFrameIndex;
        if (super.bytFrameIndex < 5) {
            super.blnIsDraw = !super.blnIsDraw;
        } else if (super.bytFrameIndex == 5) {
            super.blnIsDraw = true;
        } else if (super.bytFrameIndex > super.bytFrameMax) {
            super.blnTaskOK = true;
            super.blnIsDraw = false;
            this.delthis();
        }
    }

    private void delthis() {
        super.intsDeBuff = null;
        super.strsDeBuff = null;
        super.intsBuff = null;
        super.strsBuff = null;
        if (DialogUI.getInstance() != null) {
            DialogUI.getInstance().delDialogBuff(super.intUserId);
        }
        GameUI.getInstance().delObject(this);
    }

    protected void setCartoon(final byte _type) {
        this.setStand(super.bytDirection);
        super.blnTaskOK = false;
        this.switchActionState((byte) 3);
        this.bytCartoon = _type;
        super.bytFrame = 0;
    }

    protected void doCartoon() {
        if (this.bytCartoon == 1) {
            this.blnIsabsorb = false;
            super.intHP = super.intHPMax;
            super.intMP = super.intMPMax;
            super.intsDamageData = null;
            super.intsDamageType = null;
            super.intsCureData = null;
            super.bytCureDataIndex = -1;
            super.bytMpDataIndex = -1;
            super.intsDeBuff = null;
            super.strsDeBuff = null;
            super.intsBuff = null;
            super.strsBuff = null;
            super.blnIsDraw = true;
            super.bytSpecialtiesState = null;
            this.bytCartoon = 0;
            super.blnTaskOK = true;
            this.setTileXY(this.bytChaseX, this.bytChaseY);
            this.setStand(super.bytDirection);
        } else if (this.bytCartoon == 2) {
            ++super.bytFrame;
            if (super.bytFrame > 10) {
                super.bytFrame = 0;
                this.delthis();
                super.blnTaskOK = true;
            }
        } else if (this.bytCartoon == 3) {
            super.blnTaskOK = true;
            this.setStand(super.bytDirection);
        } else if (this.bytCartoon == 4) {
            if (super.blnIsDraw) {
                if (Param.getInstance().bytSelectType == 3 && Param.getInstance().intSelectId == super.intUserId) {
                    ORPMe.ME.delSelsectRole();
                }
                super.blnIsDraw = false;
            }
            super.blnTaskOK = true;
            this.setStand(super.bytDirection);
        } else if (this.bytCartoon == 5) {
            if (super.blnIsDraw) {
                super.blnIsDraw = false;
            }
            this.setTileXY(this.bytConcealX, this.bytConcealY);
            if (!super.blnIsDraw) {
                super.blnIsDraw = true;
            }
            super.blnTaskOK = true;
            this.setStand(super.bytDirection);
        }
    }

    public int getRoleAnimation(int action, final int direction) {
        int actinId = 0;
        if (action == 4) {
            action = 2;
        }
        if (action == 5) {
            actinId = 0;
        } else if (direction == 0 || direction == 1) {
            actinId = action * 2 + direction + 1;
        } else {
            actinId = action * 2 + (direction - 2) + 1;
        }
        return actinId;
    }

    public int getRoleDeadAnimation(final int action, final int direction) {
        return 0;
    }

    private void setDrawName(final byte _type) {
        switch (super.bytDrawNameType = _type) {
            case 0: {
                super.strDrawName = super.strNickName;
                break;
            }
            case 1: {
                super.strDrawName = "LV " + super.shtLevel;
                break;
            }
            case 2: {
                super.strDrawName = Macro.MONSTER_TYPE[this.bytPhyleType];
                break;
            }
            case 3: {
                super.strDrawName = ORole.getOccName(super.bytOccupation);
                break;
            }
            case 4: {
                super.strDrawName = null;
                break;
            }
        }
    }

    public void drawName(final Graphics g, final byte _type) {
        final int _drawx = super.shtJudgeX + super.shtDrawNameX - Param.getInstance().CAMERAX;
        final int _drawy = super.shtJudgeY + super.shtDrawNameY - Param.getInstance().CAMERAY;
        if (super.bytDrawNameType != _type) {
            this.setDrawName(_type);
        }
        if (_type == 4) {
            if (!super.blnIsAttack) {
                GameUI.getInstance().drawHP(g, _drawx + 1, _drawy + 1, super.intHP, super.intHPMax, 41445, 20114);
            } else {
                GameUI.getInstance().drawHP(g, _drawx + 1, _drawy + 1, super.intHP, super.intHPMax, 15007783, 9568256);
            }
        } else if (!super.blnIsAttack) {
            StringManager.drawShadowWord(g, super.strDrawName, _drawx, _drawy, 2424772, 682828, 33);
        } else if (this.blnInitiative) {
            StringManager.drawShadowWord(g, super.strDrawName, _drawx + 1, _drawy + 1, 16777147, 15623697, 33);
        } else {
            StringManager.drawShadowWord(g, super.strDrawName, _drawx + 1, _drawy + 1, 13762595, 16429489, 33);
        }
        if (super.bytChatTime > 0) {
            if (this.blnDrawAIChat) {
                this.drawChat(g, _drawx, _drawy - Macro.FONTHEIGHT, this.strAIChat);
            } else {
                this.drawChat(g, _drawx, _drawy - Macro.FONTHEIGHT, super.strChat);
            }
        }
    }

    private void drawChat(final Graphics g, final int _drawx, final int _drawy, final String[] _chat) {
        LayoutStyle.getInstance().drawChat(g, _drawx, _drawy, _chat, (byte) 3);
    }

    protected void melogic(final int _time) {
        if (this.blnDrawAIChat && this.chatLogic() == 0) {
            this.blnDrawAIChat = false;
            this.strAIChat = null;
        }
    }

    public void delRole() {
        super.delRole();
        if (!this.blnIsSetPass) {
            this.blnIsSetPass = true;
        }
    }

    public void setSpeed(byte _speedType) {
        if (_speedType >= IRoleDefine.MONSTER_SPEED.length) {
            _speedType = (byte) (IRoleDefine.MONSTER_SPEED.length - 1);
        } else if (_speedType < 0) {
            _speedType = 0;
        }
        super.bytSpeed = IRoleDefine.MONSTER_SPEED[_speedType];
    }

    public void setTileXY(final byte _x, final byte _y) {
        super.bytTileX = _x;
        super.bytTileY = _y;
        super.shtJudgeX = this.getJudgeX();
        super.shtJudgeY = this.getJudgeY();
    }
}
