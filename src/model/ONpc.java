// 
// Decompiled by Procyon v0.5.36
// 
package model;

import java.util.Hashtable;
import face.GameUI;
import javax.microedition.lcdui.Graphics;
import base.Param;
import means.ImageManager;
import means.StringManager;
import base.Macro;
import java.util.Vector;
import means.QSprite;

public class ONpc extends ORole {

    public byte bytIsTask;
    public boolean blnIsAction;
    public boolean blnIsTalk;
    public boolean blnIsConvoy;
    public String strOccupation;
    public String strTALK;
    public String[] strChat;
    public byte bytChatTime;
    public boolean blnNpc;
    public boolean blnWood;
    public boolean blnDrawMapPoint;
    public boolean blnIsActivation;
    public QSprite[] sprTaskSign;
    public int intTaskSignAnimation;

    public ONpc() {
        this.strOccupation = "";
    }

    public void newPlayer(final int id, final byte _TileX, final byte _TileY) {
        super.newPlayer(_TileX, _TileY);
        super.intUserId = id;
        super.bytType = 1;
        this.sprTaskSign = new QSprite[6];
        super.bytDirection = 2;
        this.blnIsActivation = false;
        this.blnNpc = true;
        super.bytRoleMoveFrame = 4;
        this.blnDrawMapPoint = true;
        super.vTask = new Vector(1, 1);
        super.blnTaskOK = true;
        super.shtPicIdDefault = 37;
        super.shtAnuIdDefault = 37;
    }

    public void setChat(final String _str) {
        this.strChat = StringManager.wenZi(_str, Macro.SCREEN_WIDTH / 2);
    }

    public void setRoleFrame(final int userid, final short pngId, final short anuid, final boolean _type) {
        this.blnIsConvoy = _type;
        super.shtPicId = ImageManager.addNpcImage(pngId, 0);
        super.shtAnuId = ImageManager.addNpcImage(anuid, 1);
        super.strResPath = "npc";
        super.intPoolType = 3;
        super.bytPicSize = ORole.transPicSize((byte) ImageManager.addNpcImage(pngId, 3));
        super.bytShadowPicSize = ORole.transPicSize((byte) ImageManager.addNpcImage(pngId, 5));
        super.shtImgWidth = (short) (super.bytPicSize * 16);
        super.shtImgHeight = ImageManager.addNpcImage(pngId, 4);
        super.sprInstance = ImageManager.loadSpriteById(super.intPoolType, ImageManager.EncodespriteId(new StringBuffer(String.valueOf(super.intUserId)).toString(), new StringBuffer(String.valueOf(super.shtPicId)).toString()), new StringBuffer(String.valueOf(super.shtAnuId)).toString(), new StringBuffer(String.valueOf(super.shtPicId)).toString(), super.strResPath, false, false);
        if (super.sprInstance.getData() == null || super.sprInstance.getPng() == null) {
            super.blnUseDefault = true;
            super.sprInstance = ImageManager.loadSpriteById(super.intPoolType, ImageManager.EncodespriteId(new StringBuffer(String.valueOf(super.intUserId)).toString(), new StringBuffer(String.valueOf(super.shtPicId)).toString()), new StringBuffer(String.valueOf(super.shtAnuIdDefault)).toString(), new StringBuffer(String.valueOf(super.shtPicIdDefault)).toString(), super.strResPath);
        }
        super.intShadeAnimation = (byte) this.getShadeType(super.bytShadowPicSize);
        super.shadeSprInstance = Param.getInstance().sprShadow;
        super.shtDrawNameX = 0;
        super.shtDrawNameY = (short) (-super.shtImgHeight);
        this.pushTask((byte) (-1), super.bytDirection);
        this.roleTaskAction(0);
    }

    public void setNpcTaskSign(final byte _task) {
        this.bytIsTask = _task;
        if (this.bytIsTask > 0 && this.bytIsTask < 4) {
            if (this.bytIsTask == 1) {
                this.intTaskSignAnimation = 0;
            } else if (this.bytIsTask == 2) {
                this.intTaskSignAnimation = 1;
            } else if (this.bytIsTask == 3) {
                this.intTaskSignAnimation = 3;
            }
            (this.sprTaskSign[this.intTaskSignAnimation] = ImageManager.loadSpriteById(3, ImageManager.EncodespriteId("npctask" + this.intTaskSignAnimation, "npctask"), "npctask", "npctask", "ingame")).setAnimation(this.intTaskSignAnimation);
        }
    }

    public void update() {
        super.update();
        if (this.sprTaskSign[this.intTaskSignAnimation] != null) {
            this.sprTaskSign[this.intTaskSignAnimation].update();
        }
    }

    public int getRoleAnimation(final int action, final int direction) {
        int actinId = 1;
        if (this.blnIsConvoy || super.blnUseDefault) {
            actinId = action * 4 + direction + 1;
        }
        return actinId;
    }

    public void draw(final Graphics g) {
        final int _drawx = super.shtJudgeX - Param.getInstance().CAMERAX;
        final int _drawy = super.shtJudgeY - Param.getInstance().CAMERAY;
        this.draw(g, _drawx, _drawy);
    }

    public void draw(final Graphics g, final int _drawx, final int _drawy) {
        super.draw(g, _drawx, _drawy);
        if (super.blnIsDraw && this.blnIsTalk && this.bytIsTask > 0 && this.bytIsTask < 4 && this.sprTaskSign[this.intTaskSignAnimation] != null) {
            this.sprTaskSign[this.intTaskSignAnimation].drawAnimation(g, _drawx + 15, _drawy - super.shtImgHeight / 2 - 20);
        }
    }

    public void drawName(final Graphics g, final byte _type) {
        if (!this.blnNpc) {
            return;
        }
        final int _drawx = super.shtJudgeX + super.shtDrawNameX - Param.getInstance().CAMERAX;
        int _drawy = super.shtJudgeY + super.shtDrawNameY - Param.getInstance().CAMERAY;
        if (_type != 4) {
            int _y = 0;
            if (super.blnCountry) {
                if (!this.strOccupation.equals("")) {
                    StringManager.drawShadowWord(g, "<" + this.strOccupation + ">", _drawx, _drawy, 898303, 19529, 33);
                    _y -= Macro.FONTHEIGHT;
                }
                if (!super.strNickName.equals("")) {
                    StringManager.drawShadowWord(g, super.strNickName, _drawx, _drawy + _y, 2424772, 682828, 33);
                }
            } else {
                if (!this.strOccupation.equals("")) {
                    StringManager.drawShadowWord(g, "<" + this.strOccupation + ">", _drawx, _drawy, 13762595, 16429489, 33);
                    _y -= Macro.FONTHEIGHT;
                }
                if (!super.strNickName.equals("")) {
                    StringManager.drawShadowWord(g, super.strNickName, _drawx, _drawy + _y, 13762595, 16429489, 33);
                }
            }
        }
        if (this.bytChatTime > 0) {
            _drawy -= Macro.FONTHEIGHT * this.strChat.length + 4;
            final int _l = Macro.font.stringWidth(this.strChat[0]) + 12;
            GameUI.getInstance().drawChatRect(g, _drawx - _l / 2, _drawy, _l, Macro.FONTHEIGHT * this.strChat.length, 55, true);
            g.setColor(16777215);
            for (int i = 0; i < this.strChat.length; ++i) {
                g.drawString(this.strChat[i], _drawx - _l / 2 + 4, _drawy + i * Macro.FONTHEIGHT, 20);
            }
        }
    }

    public String getNpcName() {
        if (super.strNickName.length() > 0) {
            return super.strNickName;
        }
        return this.strOccupation;
    }

    public void NpcActivation() {
        if (Param.getInstance().bytSelectType == 1 && Param.getInstance().intSelectId == super.intUserId) {
            ORPMe.ME.delSelsectRole();
        }
        this.blnIsActivation = true;
        if (Param.getInstance().htNpcConvoy == null) {
            Param.getInstance().htNpcConvoy = new Hashtable(1);
        }
        Param.getInstance().htNpcConvoy.put(new Integer(super.intUserId), this);
    }

    public void setTileXY(final byte _x, final byte _y) {
        if (!this.blnIsActivation) {
            this.NpcActivation();
            final Map instance = Map.getInstance();
            ++instance.bytNpcConvoyNum;
            GameUI.getInstance().delTempVObject(this);
        }
        if (this.blnIsActivation && Param.getInstance().htNpcConvoy.containsKey(new Integer(super.intUserId))) {
            final Map instance2 = Map.getInstance();
            --instance2.bytNpcConvoyNum;
            Param.getInstance().htNpcConvoy.remove(new Integer(super.intUserId));
            if (Param.getInstance().htNpcConvoy.isEmpty()) {
                Param.getInstance().htNpcConvoy = null;
            }
        }
        this.blnIsActivation = false;
        super.bytTileX = _x;
        super.bytTileY = _y;
        super.shtJudgeX = this.getJudgeX();
        super.shtJudgeY = this.getJudgeY();
        this.pushTask((byte) 0, (byte) 2);
        if (Map.getInstance().bytsNpcXY == null) {
            Map.getInstance().bytsNpcXY = new byte[1][2];
        } else {
            final byte[][] _tempXY = Map.getInstance().bytsNpcXY;
            System.arraycopy(_tempXY, 0, Map.getInstance().bytsNpcXY = new byte[_tempXY.length + 1][2], 0, _tempXY.length);
        }
        Map.getInstance().bytsNpcXY[Map.getInstance().bytsNpcXY.length - 1][0] = super.bytTileY;
        Map.getInstance().bytsNpcXY[Map.getInstance().bytsNpcXY.length - 1][1] = super.bytTileX;
        GameUI.getInstance().addObject(this);
    }

    private int getNPCPortraitAnimation() {
        return 0;
    }
}
