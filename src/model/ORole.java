// 
// Decompiled by Procyon v0.5.36
// 
package model;

import means.AStar;
import javax.microedition.lcdui.Image;
import face.DialogUI;
import common.DrawBase;
import face.GameUI;
import network.NetSend;
import means.FightValue;
import base.DCanvas;
import means.ImageManager;
import java.util.Enumeration;
import base.Param;
import javax.microedition.lcdui.Graphics;
import base.Macro;
import means.DebugFrame;
import means.QSprite;
import java.util.Vector;
import java.util.Hashtable;

public class ORole {

    int intId;
    int intPosX;
    int intPosY;
    public int intEquipEffectID;
    public int intWeaponAttackEffectID;
    public int intWeaponRoundEffectID;
    public int intNormalAttackedEffectID;
    public int intSkillAttackedEffectID;
    public int intPrepareSkillEffectID;
    public int intAssistantSkillEffectID;
    public int intSkillUpLayerEffectID;
    public int intSkillDownLayerEffectID;
    public int intUserId;
    public int uesID;
    public short shtPicId;
    public short shtAnuId;
    public String strResPath;
    public int intPoolType;
    public short shtPicIdDefault;
    public short shtAnuIdDefault;
    public boolean blnNeedUpdate;
    public boolean blnUseDefault;
    public String strNickName;
    public String strDebugInfo;
    public byte bytMoveType;
    public byte bytType;
    public byte bytTileX;
    public byte bytTileY;
    public byte bytTileZ;
    public Hashtable hPackagePet;
    public short shtJudgeX;
    public short shtJudgeY;
    public short shtImgWidth;
    public short shtImgHeight;
    public byte bytPortraitX;
    public byte bytPortraitY;
    public byte bytDirection;
    public byte bytTaskDirection;
    public byte bytTask;
    public byte bytTaskSyncAnimation;
    public byte bytState;
    public byte bytStateLast;
    public byte bytAnimState;
    public boolean blnSetAnimStateLoop;
    public byte bytAnimStateLoop;
    public int bytAnimStateLoopOnPartId;
    public int bytAnimStateLoopOnSprId;
    public boolean blnTaskOK;
    protected byte bytFrame;
    public boolean blnSelectedRide;
    public boolean blnNeedConcealRidePet;
    protected boolean blnIsRealMove;
    public Vector vTask;
    public byte bytFrameIndex;
    public byte bytFrameMax;
    public byte bytSpeed;
    protected byte bytBlockPixel;
    public short bytNowSkill;
    public boolean blnNowSkillType;
    public Hashtable vecNextSkill;
    public Hashtable htNextSkill;
    public SkillObject curSkillObj;
    public byte bytDrawPortraitId;
    public short shtDrawNameX;
    public short shtDrawNameY;
    public byte bytRoleMoveFrame;
    public byte bytPicSize;
    public byte bytShadowPicSize;
    public boolean blnIsAttack;
    public boolean blnUseMp;
    public int[][] intsBuff;
    public String[][] strsBuff;
    public int[][] intsDeBuff;
    public String[][] strsDeBuff;
    public int intHP;
    public int intHPMax;
    public int intMP;
    public int intMPMax;
    public short shtLevel;
    public String spouse;
    public String strConsortia;
    public String societyName;
    public String MasterName;
    public String appreticeName;
    public byte bytIsMale;
    public byte bytCountry;
    public boolean blnCountry;
    public byte bytOccupation;
    public int[] intsDamageData;
    public byte[] intsDamageType;
    public int[] intsCureData;
    public int[] intsMpDate;
    public byte bytCureDataIndex;
    public byte bytMpDataIndex;
    public boolean blnIsDraw;
    public boolean blnIsUpdate;
    public byte[] bytSpecialtiesState;
    protected String strDrawName;
    public byte bytDrawNameType;
    public byte bytConceal;
    public int sprAnimation;
    public int sprDeadAnimation;
    public QSprite sprInstance;
    public QSprite sprDeadInstance;
    public QSprite shadeSprInstance;
    public int intShadeAnimation;
    public String[] strChat;
    public byte bytChatTime;
    public int intMTime;
    public QSprite sprLevelUp;
    public static byte bytLevelUpNum;
    public QSprite sprEnterJump;
    public static byte bytEnterJumpNum;
    public int fightValueOffsetX;
    public int fightValueOffsetY;
    public Vector fightValue;
    public Vector addValue;
    public Vector addValueMp;

    static {
        ORole.bytLevelUpNum = 0;
        ORole.bytEnterJumpNum = 0;
    }

    public ORole() {
        this.blnNeedUpdate = false;
        this.blnUseDefault = false;
        this.strNickName = "";
        this.strDebugInfo = "";
        this.bytMoveType = 3;
        this.hPackagePet = new Hashtable(2);
        this.bytState = 0;
        this.blnSelectedRide = false;
        this.blnNeedConcealRidePet = false;
        this.vecNextSkill = new Hashtable(1);
        this.htNextSkill = new Hashtable();
        this.strConsortia = "";
        this.bytCureDataIndex = -1;
        this.bytMpDataIndex = -1;
        this.bytDrawNameType = -1;
        this.sprAnimation = -1;
        this.sprDeadAnimation = -1;
        this.shadeSprInstance = null;
        this.fightValueOffsetX = 0;
        this.fightValueOffsetY = -40;
    }

    public void newPlayer(final byte _TileX, final byte _TileY) {
        this.bytTileX = _TileX;
        this.bytTileY = _TileY;
        this.shtJudgeX = this.getJudgeX();
        this.shtJudgeY = this.getJudgeY();
        this.blnIsDraw = true;
        this.fightValue = new Vector();
        this.addValue = new Vector();
        this.addValueMp = new Vector();
    }

    public void setRoleDraw(final boolean draw) {
        this.blnIsDraw = draw;
    }

    public void setRoleUpdate(final boolean update) {
        this.blnIsUpdate = update;
    }

    public boolean getRoleDraw() {
        return this.blnIsDraw;
    }

    public boolean getRoleUpdate() {
        return this.blnIsUpdate;
    }

    public boolean checkFrameDone() {
        return this.bytFrameIndex > this.bytFrameMax;
    }

    public void updateSkillFrame(final SkillObject skillObj) {
        if (skillObj != null) {
            ++skillObj.shtAnimationFrameIndex;
        }
    }

    public boolean setSkillFrame(final SkillObject skillObj, final int loop) {
        if (skillObj != null) {
            skillObj.shtAnimationFrameIndex = 0;
            skillObj.shtAnimationFrameMax = this.getSpriteFrameMax(this.getRoleSpriteEncode(skillObj.bytSkillPicId), loop);
        } else {
            DebugFrame.getInstance().logIn("Error! \u8981\u8bbe\u7f6e\u5e27\u6570\u7684\u6280\u80fd\u4e0d\u5b58\u5728\uff01");
        }
        return false;
    }

    public boolean checkFrameDone(final SkillObject skillObj) {
        if (skillObj != null) {
            if (skillObj.shtAnimationFrameMax == -1) {
                return false;
            }
            if (skillObj.shtAnimationFrameIndex >= skillObj.shtAnimationFrameMax) {
                return true;
            }
        }
        return false;
    }

    public void pushTask(final byte _task, final byte _direction) {
        this.pushTask(_task, _direction, (byte) 0, false);
    }

    public void pushTask(final byte _task, final byte _direction, final byte forceSync) {
        this.pushTask(_task, _direction, forceSync, true);
    }

    public void pushTask(final byte _task, final byte _direction, final byte forceSync, final boolean enterCamera) {
        boolean isCanPushTask = true;
        if (this.bytState == -1) {
            isCanPushTask = false;
            if (enterCamera) {
                isCanPushTask = true;
            } else if (_task == 5 || _task == 7) {
                isCanPushTask = true;
            }
        }
        if (isCanPushTask) {
            this.vTask.addElement(new byte[]{_task, _direction, forceSync});
        }
    }

    public void delTask(final byte _task) {
        synchronized (this.vTask) {
            if (!this.vTask.isEmpty()) {
                int _taskNum = 0;
                while (_taskNum < this.vTask.size()) {
                    if (((byte[]) this.vTask.elementAt(_taskNum))[0] == _task) {
                        this.vTask.removeElementAt(_taskNum);
                    } else {
                        ++_taskNum;
                    }
                }
            }
        }
        // monitorexit(this.vTask)
    }

    public void pushTaskFirst(final byte _task, final byte _direction) {
        this.pushTaskFirst(_task, _direction, (byte) 0);
    }

    public void pushTaskFirst(final byte _task, final byte _direction, final byte forceSync) {
        this.vTask.insertElementAt(new byte[]{_task, _direction, forceSync}, 0);
    }

    public boolean popTask() {
        this.bytTask = 0;
        this.bytTaskDirection = this.bytDirection;
        this.bytTaskSyncAnimation = 0;
        if (!this.vTask.isEmpty()) {
            this.blnTaskOK = false;
            byte[] _task = (byte[]) this.vTask.firstElement();
            this.bytTask = _task[0];
            this.bytTaskDirection = _task[1];
            this.bytTaskSyncAnimation = _task[2];
            this.vTask.removeElementAt(0);
            return true;
        }
        return false;
    }

    public boolean checkTask(final byte _task) {
        for (int i = 0; i < this.vTask.size(); ++i) {
            if (_task == ((byte[]) this.vTask.elementAt(i))[0]) {
                return true;
            }
        }
        return false;
    }

    public boolean checkSyncAnimation() {
        return this.bytTaskSyncAnimation == 1;
    }

    public void cleanTask() {
        if (!this.vTask.isEmpty()) {
            this.vTask.removeAllElements();
        }
    }

    protected void changeTile(final int _x, final int _y) {
        this.bytTileX += (byte) _x;
        this.bytTileY += (byte) _y;
    }

    public void checkAction(final int _time) {
        this.roleTaskAction(_time);
        this.melogic(_time);
    }

    public void roleTaskAction(final int _time) {
        if (this.blnTaskOK && this.popTask()) {
            switch (this.bytTask) {
                case -1: {
                    this.setNoneAction();
                    break;
                }
                case 0: {
                    this.setStand(this.bytTaskDirection);
                    break;
                }
                case 1: {
                    this.setMove(this.bytTaskDirection);
                    break;
                }
                case 3: {
                    this.setCartoon(this.bytTaskDirection);
                    break;
                }
                case 2: {
                    this.setFight(this.bytTaskDirection);
                    break;
                }
                case 4: {
                    this.setMagic(this.bytTaskDirection);
                    break;
                }
                case 6: {
                    this.setPull(this.bytTaskDirection);
                    break;
                }
                case 5: {
                    DebugFrame.getInstance().logIn("Pop Task dead");
                    this.setDead(this.bytTaskDirection);
                    break;
                }
                case 7: {
                    this.setAdjust();
                    break;
                }
            }
            this.bytTaskSyncAnimation = 0;
        }
        switch (this.bytState) {
            case -1: {
                this.doNoneAction();
                break;
            }
            case 0: {
                this.doStand();
                break;
            }
            case 1: {
                this.doMove();
                break;
            }
            case 3: {
                this.doCartoon();
                break;
            }
            case 2: {
                this.doFight();
                break;
            }
            case 4: {
                this.doMagic();
                break;
            }
            case 6: {
                this.doPull();
                break;
            }
            case 5: {
                this.doDead();
                break;
            }
        }
        this.logic(_time);
    }

    private void logic(final int _time) {
        if (this.intsBuff != null) {
            int _id = -1;
            int _playerId = -1;
            for (int i = 0; i < this.intsBuff.length; ++i) {
                if (this.intsBuff[i][1] > 0) {
                    final int[] array = this.intsBuff[i];
                    final int n = 1;
                    array[n] -= _time;
                    if (this.intsBuff[i][1] == 0) {
                        this.intsBuff[i][1] = -1;
                    }
                } else if (this.intsBuff[i][1] < 0) {
                    _id = this.intsBuff[i][0];
                    _playerId = this.intsBuff[i][4];
                    break;
                }
            }
            if (_id != -1) {
                this.delBuff(_id, _playerId);
            }
        }
        if (this.intsDeBuff != null) {
            int _id = -1;
            int _playerId = -1;
            for (int i = 0; i < this.intsDeBuff.length; ++i) {
                if (this.intsDeBuff[i][1] > 0) {
                    final int[] array2 = this.intsDeBuff[i];
                    final int n2 = 1;
                    array2[n2] -= _time;
                    if (this.intsDeBuff[i][1] == 0) {
                        this.intsDeBuff[i][1] = -1;
                    }
                } else if (this.intsDeBuff[i][1] < 0) {
                    _id = this.intsDeBuff[i][0];
                    _playerId = this.intsDeBuff[i][4];
                    break;
                }
            }
            if (_id != -1) {
                this.delDeBuff(_id, _playerId);
            }
        }
        this.withMeLogic(_time);
        if (Macro.bytGameType == 1 && this.bytState == -1 && Map.getInstance().judgeRoleIsDraw(this)) {
            if (Macro.BLN_CAMERA_STRICT_RESOURCE_DEBUG_INFO) {
                final String info = "Enter Camera: id/name/nick/pic/anu" + this.intUserId + this.strDrawName + this.strNickName + this.shtPicId + "/" + this.shtAnuId;
                DebugFrame.getInstance().logIn(info);
            }
            if (this.bytState != 5) {
                this.pushTask((byte) 0, this.bytDirection, (byte) 1);
            }
        }
        if (Macro.bytGameType == 1 && this.bytState != -1 && !Map.getInstance().judgeRoleIsDraw(this)) {
            if (Macro.BLN_CAMERA_STRICT_RESOURCE_DEBUG_INFO) {
                final String info = "Leave Camera: id/name/nick/pic/anu" + this.intUserId + this.strDrawName + this.strNickName + this.shtPicId + "/" + this.shtAnuId;
                DebugFrame.getInstance().logIn(info);
            }
            this.cleanTask();
            this.pushTask((byte) (-1), this.bytDirection);
            if (this.bytState == 5) {
                this.pushTask((byte) 5, this.bytDirection);
            }
        }
    }

    public void draw(final Graphics g) {
        final int _drawx = this.shtJudgeX - Param.getInstance().CAMERAX;
        final int _drawy = this.shtJudgeY - Param.getInstance().CAMERAY;
        this.draw(g, _drawx, _drawy);
    }

    public void draw(final Graphics g, final int _drawx, final int _drawy) {
        if (this.bytState != -1 && this.blnIsDraw) {
            if (this.shadeSprInstance != null) {
                this.shadeSprInstance.drawAnimationFrame(g, this.intShadeAnimation, 0, _drawx, _drawy);
            }
            if (this.bytState == 5) {
                if (this.bytFrameIndex <= 5) {
                    if (this.sprInstance != null && this.sprAnimation != -1) {
                        this.sprInstance.drawAnimation(g, _drawx, _drawy);
                    }
                } else if (this.sprDeadInstance != null && this.sprDeadAnimation != -1) {
                    this.sprDeadInstance.drawAnimation(g, _drawx, _drawy);
                }
            } else if (this.sprInstance != null && this.sprAnimation != -1) {
                this.sprInstance.drawAnimation(g, _drawx, _drawy);
            }
            this.drawSkill(g, _drawx, _drawy);
        }
    }

    public void drawSkill(final Graphics g, final int xoff, final int yoff) {
        if (this.vecNextSkill != null && !this.vecNextSkill.isEmpty()) {
            final Enumeration enumSkill = this.vecNextSkill.elements();
            while (enumSkill.hasMoreElements()) {
                SkillObject skill = (SkillObject) enumSkill.nextElement();
                String encode = this.getRoleSpriteEncode(skill.bytSkillPicId);
                QSprite sprSkill = (QSprite) this.htNextSkill.get(encode);
                if (sprSkill != null) {
                    sprSkill.drawAnimation(g, xoff, yoff);
                }
            }
        }
    }

    public String getRoleSpriteEncode(final int sprId) {
        return this.getRoleSpriteEncode(new StringBuffer(String.valueOf(sprId)).toString());
    }

    public String getRoleSpriteEncode(final String sprId) {
        return ImageManager.EncodespriteId(new StringBuffer(String.valueOf(this.intUserId)).toString(), sprId);
    }

    public void updateRoleSkill() {
        if (this.vecNextSkill != null && !this.vecNextSkill.isEmpty()) {
            Enumeration enumSkill = this.vecNextSkill.elements();
            while (enumSkill.hasMoreElements()) {
                SkillObject skill = (SkillObject) enumSkill.nextElement();
                if (skill != null) {
                    this.updateSkillFrame(skill);
                    if (!this.isAttackedSkill(skill) || this.bytType == 2) {
                        continue;
                    }
                    String encode = this.getRoleSpriteEncode(skill.bytSkillPicId);
                    SkillObject skillObject = skill;
                    skillObject.byteDelay -= DCanvas.getInstance().costTime;
                    if (skill.byteDelay < 0L) {
                        skill.byteDelay = 0L;
                    }
                    String encodeId = this.getRoleSpriteEncode(skill.bytSkillPicId);
                    QSprite sprSkill = (QSprite) this.htNextSkill.get(encodeId);
                    if (sprSkill == null) {
                        continue;
                    }
                    sprSkill.update();
                    if (!this.checkSpriteDone(encodeId)) {
                        continue;
                    }
                    DebugFrame.getInstance().logIn("\u602a\u7269\u88ab\u6253\u6280\u80fd\u5b8c\u6210:" + encode);
                    this.setSpriteDone(encodeId);
                    this.removeSkill(this.getRoleSpriteEncode(skill.bytSkillPicId));
                }
            }
        }
    }

    public void cleanMagic() {
        this.removeSkillType((byte) 0);
    }

    public void cleanAllMagic() {
        this.removeSkillType((byte) 2);
    }

    public void removeSkillType(final byte skillType) {
        if (this.vecNextSkill != null) {
            final Enumeration enumSkill = this.vecNextSkill.elements();
            while (enumSkill.hasMoreElements()) {
                SkillObject skill = (SkillObject) enumSkill.nextElement();
                String encode = this.getRoleSpriteEncode(skill.bytSkillPicId);
                if (skillType == 2 || skill.byteActive == skillType) {
                    this.removeSkill(encode);
                }
            }
        }
    }

    public int getAttackedSkillAnimation() {
        return 0;
    }

    public void setSpriteDone(final String encodeId) {
        final QSprite Sprite = (QSprite) this.htNextSkill.get(encodeId);
        if (Sprite != null) {
            Sprite.notifyEndOfAnimation();
        }
    }

    public void removeSkill(final String encodeId) {
        if (this.vecNextSkill != null && this.vecNextSkill.containsKey(encodeId)) {
            SkillObject skillobj = (SkillObject) this.vecNextSkill.get(encodeId);
            this.vecNextSkill.remove(encodeId);
            if (Macro.BLN_LEVEL_FUNCTION_SKILL) {
                ResPoolLevelFunction.getInstance().LevelFunction_pop((byte) 0, this, new short[]{skillobj.bytSkillPicId, skillobj.bytSkillAnuId});
            }
        }
        if (this.htNextSkill != null && this.htNextSkill.containsKey(encodeId)) {
            this.htNextSkill.remove(encodeId);
        }
    }

    public boolean isAttackedSkill(final SkillObject skill) {
        return skill != null && skill.byteActive == 1;
    }

    public boolean isBuffSkill(final SkillObject skill) {
        return skill != null && skill.byteSkillType == 1;
    }

    public void setBuffDone(final int bufferId) {
        final Enumeration enumSkill = this.vecNextSkill.elements();
        while (enumSkill.hasMoreElements()) {
            SkillObject skill = (SkillObject) enumSkill.nextElement();
            if (skill.intId == bufferId) {
                skill.byteLoop = 0;
            }
        }
    }

    public boolean isBuffDone(final SkillObject skill) {
        return this.isBuffSkill(skill) && skill.byteLoop == 0;
    }

    protected void setNoneAction() {
        this.switchActionState((byte) (-1));
        this.blnTaskOK = true;
    }

    protected void doNoneAction() {
    }

    protected void setStand(final byte _direction) {
        this.switchActionState((byte) 0);
        this.bytDirection = _direction;
        this.blnTaskOK = true;
        this.bytFrame = 0;
        this.blnIsRealMove = false;
        this.setRoleAnimation();
    }

    protected void doStand() {
        ++this.bytFrame;
    }

    protected void setMove(final byte _direction) {
        this.bytDirection = _direction;
        this.bytBlockPixel = 16;
        if (this.bytSpeed != 0) {
            this.bytRoleMoveFrame = this.bytSpeed;
            this.bytSpeed = 0;
        }
        this.blnIsRealMove = false;
        this.switchActionState((byte) 1);
        this.bytFrame = 0;
        if (this.bytDirection == 1) {
            if (Map.getInstance().checkIsPass(this.bytTileX, this.bytTileY - 1)) {
                this.changeTile(0, -1);
                this.blnIsRealMove = true;
            }
        } else if (this.bytDirection == 2) {
            if (Map.getInstance().checkIsPass(this.bytTileX, this.bytTileY + 1)) {
                this.changeTile(0, 1);
                this.blnIsRealMove = true;
            }
        } else if (this.bytDirection == 3) {
            if (Map.getInstance().checkIsPass(this.bytTileX - 1, this.bytTileY)) {
                this.changeTile(-1, 0);
                this.blnIsRealMove = true;
            }
        } else if (this.bytDirection == 4 && Map.getInstance().checkIsPass(this.bytTileX + 1, this.bytTileY)) {
            this.changeTile(1, 0);
            this.blnIsRealMove = true;
        }
        if (!Map.getInstance().judgeIsDraw(this.shtJudgeX, this.shtImgWidth, this.shtJudgeY, this.shtImgHeight)) {
            this.blnTaskOK = true;
            if (this.bytDirection == 1) {
                this.shtJudgeY -= this.bytBlockPixel;
            } else if (this.bytDirection == 2) {
                this.shtJudgeY += this.bytBlockPixel;
            } else if (this.bytDirection == 4) {
                this.shtJudgeX += this.bytBlockPixel;
            } else if (this.bytDirection == 3) {
                this.shtJudgeX -= this.bytBlockPixel;
            }
            this.checkEvent();
            this.endMove();
            this.blnIsRealMove = false;
            this.setStand(this.bytDirection);
        }
        this.setRoleAnimation();
    }

    protected void endMove() {
        this.setStand(this.bytDirection);
    }

    protected void doMove() {
        ++this.bytFrame;
        if (this.bytFrame == this.bytRoleMoveFrame) {
            this.blnTaskOK = true;
            this.checkEvent();
        } else if (this.bytFrame > this.bytRoleMoveFrame) {
            this.endMove();
            return;
        }
        this.setMoveFrame();
        if (this.blnIsRealMove) {
            final byte _speed = (byte) (this.bytBlockPixel / (this.bytRoleMoveFrame - this.bytFrame + 1));
            this.bytBlockPixel -= _speed;
            if (this.bytDirection == 1) {
                this.shtJudgeY -= _speed;
            } else if (this.bytDirection == 2) {
                this.shtJudgeY += _speed;
            } else if (this.bytDirection == 4) {
                this.shtJudgeX += _speed;
            } else if (this.bytDirection == 3) {
                this.shtJudgeX -= _speed;
            }
        }
    }

    protected void setAdjust() {
        final byte saveXTile = this.bytTaskDirection;
        final byte saveYTile = this.bytTaskSyncAnimation;
        if (this.shtJudgeX / 16 != saveXTile || this.shtJudgeY / 16 != saveYTile) {
            DebugFrame.getInstance().logIn("Warning!! \u4f4d\u7f6e\u77eb\u6b63 \uff01");
            this.setTileXY(saveXTile, saveYTile);
        }
        this.blnTaskOK = true;
    }

    public void setTileXY(final byte _x, final byte _y) {
        final int _temp_y = _y - this.bytTileY;
        this.bytTileX = _x;
        this.bytTileY = _y;
        this.shtJudgeX = this.getJudgeX();
        this.shtJudgeY = this.getJudgeY();
    }

    public QSprite setSkill(final SkillObject skillobj) {
        if (skillobj.bytSkillPicId == -1 || skillobj.bytSkillPicId == 0 || skillobj.bytSkillAnuId == -1 || skillobj.bytSkillAnuId == 0) {
            DebugFrame.getInstance().logIn("Tip!! \u5ffd\u7565\u8be5\u6280\u80fd\uff0c\u670d\u52a1\u5668\u4e0b\u53d1\u7684\u6280\u80fdid\u4e3a-1\u62160");
            return null;
        }
        if (skillobj.byteLoop == 0 || skillobj.byteDelay < -1L) {
            DebugFrame.getInstance().logIn("Warning!! \u6280\u80fd\u8bbe\u7f6e\u65e0\u6548 _skillId = " + skillobj.bytSkillPicId + " loop = " + skillobj.byteLoop + "delay = " + skillobj.byteDelay);
            return null;
        }
        final String encode = this.getRoleSpriteEncode(skillobj.bytSkillPicId);
        final int skillType = skillobj.byteSkillType;
        if (!this.vecNextSkill.containsKey(encode)) {
            if (this.bytType == 2) {
                final QSprite createdSkillSpriteRes = ((ORPlayer) this).addRoleRes(this.transLayer(skillobj.byteLayer), skillobj.bytSkillPicId, skillobj.bytSkillAnuId);
                if (createdSkillSpriteRes != null) {
                    final int xoff = 0;
                    final int yoff = this.getSkillYOffByType(skillobj.bytDisplayPosYOffType);
                    createdSkillSpriteRes.setSpriteOff(xoff, yoff);
                    DebugFrame.getInstance().logIn("\u6280\u80fdY\u504f\u79fb" + yoff);
                    this.vecNextSkill.put(encode, skillobj);
                    ((ORPlayer) this).setRoleAnimation(true);
                    return createdSkillSpriteRes;
                }
                DebugFrame.getInstance().logIn("Warning!! \u672a\u627e\u5230\u6280\u80fd\u8d44\u6e90 role id/png/anu" + this.intUserId + "/" + skillobj.bytSkillPicId + "/" + skillobj.bytSkillAnuId);
            } else {
                final QSprite sprSkill = ImageManager.loadSpriteById(7, encode, new StringBuffer(String.valueOf(skillobj.bytSkillAnuId)).toString(), new StringBuffer(String.valueOf(skillobj.bytSkillPicId)).toString(), "skill");
                if (sprSkill != null) {
                    final int xoff = 0;
                    final int yoff = this.getSkillYOffByType(skillobj.bytDisplayPosYOffType);
                    sprSkill.setSpriteOff(xoff, yoff);
                    DebugFrame.getInstance().logIn("\u6280\u80fdY\u504f\u79fb" + yoff);
                    sprSkill.setLoopOffset(1);
                    sprSkill.setAnimation(this.getAttackedSkillAnimation());
                    this.vecNextSkill.put(encode, skillobj);
                    this.htNextSkill.put(encode, sprSkill);
                    return sprSkill;
                }
                DebugFrame.getInstance().logIn("Warning!! \u672a\u627e\u5230\u6280\u80fd\u8d44\u6e90 role id/png/anu" + this.intUserId + "/" + skillobj.bytSkillPicId + "/" + skillobj.bytSkillAnuId);
            }
        } else {
            DebugFrame.getInstance().logIn("Tip!! \u8be5\u89d2\u8272\u6280\u80fd\u961f\u5217\u4e2d\u5df2\u5b58\u5728\u8be5\u6280\u80fdRoleId:" + this.intUserId + "\u6280\u80fdID" + skillobj.bytSkillPicId);
        }
        return null;
    }

    public void removeSkill(final SkillObject skillobj) {
        if (skillobj.bytSkillPicId == -1 || skillobj.byteLoop == 0 || skillobj.byteDelay < -1L) {
            DebugFrame.getInstance().logIn("Warning!! \u6280\u80fd\u8bbe\u7f6e\u65e0\u6548 _skillId = " + skillobj.bytSkillPicId + " loop = " + skillobj.byteLoop + "delay = " + skillobj.byteDelay);
            return;
        }
        final String encode = this.getRoleSpriteEncode(skillobj.bytSkillPicId);
        if (this.vecNextSkill.containsKey(encode)) {
            this.vecNextSkill.remove(encode);
        }
    }

    public int getLayerSkillCount(final int layer) {
        final Enumeration enumSkill = this.vecNextSkill.elements();
        int layerCount = 0;
        while (enumSkill.hasMoreElements()) {
            SkillObject skillobj = (SkillObject) enumSkill.nextElement();
            if (skillobj.byteLayer == layer) {
                ++layerCount;
            }
        }
        return layerCount;
    }

    protected byte getSpriteFrameMax(final String encodeId, final int loop) {
        final QSprite sprSkill = (QSprite) this.htNextSkill.get(encodeId);
        return this.getSpriteFrameMax(sprSkill, loop);
    }

    protected byte getSpriteFrameMax(final QSprite sprSkill, final int loop) {
        byte frameMax = 0;
        if (sprSkill != null) {
            if (sprSkill.getAnimation() != -1) {
                if (loop == -1) {
                    frameMax = -1;
                } else {
                    frameMax = (byte) (sprSkill.getFrameCount() * loop);
                }
            } else {
                frameMax = 0;
            }
        } else {
            DebugFrame.getInstance().logIn("Error! \u8981\u83b7\u53d6\u5e27\u6570\u7684\u6280\u80fd\u4e0d\u5b58\u5728\u4e8eRole\u6280\u80fd\u54c8\u5e0c\u8868\u4e2d!");
        }
        return frameMax;
    }

    public void delRole() {
        this.vecNextSkill.clear();
        this.htNextSkill.clear();
        this.bytNowSkill = 0;
        this.sprInstance = null;
        this.sprDeadInstance = null;
        this.shadeSprInstance = null;
        this.cleanMagic();
    }

    public void update() {
        if (this.bytState != -1) {
            if (this.bytState == 5) {
                if (this.bytFrameIndex <= 5) {
                    if (this.sprInstance != null && this.sprAnimation != -1) {
                        this.sprInstance.update();
                    }
                } else if (this.sprDeadInstance != null && this.sprDeadAnimation != -1) {
                    this.sprDeadInstance.update();
                }
            } else if (this.sprInstance != null && this.sprAnimation != -1) {
                this.sprInstance.update();
            }
            this.intShadeAnimation = (byte) this.getShadeType(this.bytShadowPicSize);
            this.updateRoleSkill();
            if (this.fightValue != null) {
                int k = 0;
                while (k < this.fightValue.size()) {
                    if (((FightValue) this.fightValue.elementAt(k)).update()) {
                        ++k;
                    } else {
                        this.fightValue.removeElementAt(k);
                        this.intsDamageData = null;
                        this.intsDamageType = null;
                    }
                }
            }
            if (this.addValue != null) {
                int k = 0;
                while (k < this.addValue.size()) {
                    if (((FightValue) this.addValue.elementAt(k)).update()) {
                        ++k;
                    } else {
                        this.addValue.removeElementAt(k);
                        this.intsCureData = null;
                    }
                }
            }
            if (this.addValueMp != null) {
                int k = 0;
                while (k < this.addValueMp.size()) {
                    if (((FightValue) this.addValueMp.elementAt(k)).update()) {
                        ++k;
                    } else {
                        this.addValueMp.removeElementAt(k);
                        this.intsMpDate = null;
                    }
                }
            }
        }
    }

    public void setRoleAnimation() {
        this.setRoleAnimation(this.checkSyncAnimation());
    }

    public void setRoleAnimation(final boolean forSet) {
        this.sprAnimation = this.getRoleAnimation(this.bytState, transDirection(this.bytDirection));
        this.sprDeadAnimation = this.getRoleDeadAnimation(this.bytState, transDirection(this.bytDirection));
        if (this.bytState != -1) {
            if (this.bytState == 5) {
                if (this.sprDeadInstance != null && this.sprDeadAnimation != -1) {
                    this.sprDeadInstance.setLoopOffset(1);
                    this.sprDeadInstance.setAnimation(this.sprAnimation);
                }
            } else if (this.sprInstance != null && this.sprAnimation != -1) {
                if (this.bytState == 2 || this.bytState == 4) {
                    this.sprInstance.setLoopOffset(1);
                } else {
                    this.sprInstance.setLoopOffset(-1);
                }
                if (this.sprAnimation != this.sprInstance.getAnimation() || forSet) {
                    this.sprInstance.setAnimation(this.sprAnimation);
                }
            }
        }
        this.intShadeAnimation = (byte) this.getShadeType(this.bytShadowPicSize);
    }

    public int getRoleAnimation(final int action, final int direction) {
        return 0;
    }

    public int getRoleDeadAnimation(final int action, final int direction) {
        return 0;
    }

    public void drawName(final Graphics g, final byte _type) {
    }

    protected void doCartoon() {
    }

    protected void melogic(final int _time) {
    }

    protected void setCartoon(final byte _type) {
    }

    protected void setFight(final byte _direction) {
        if (!Map.getInstance().judgeIsDraw(this.shtJudgeX, this.shtImgWidth, this.shtJudgeY, this.shtImgHeight)) {
            this.setStand(this.bytDirection);
            return;
        }
        this.switchActionState((byte) 2);
        this.bytDirection = _direction;
        this.setRoleAnimation();
    }

    protected void setMagic(final byte _direction) {
        if (!Map.getInstance().judgeIsDraw(this.shtJudgeX, this.shtImgWidth, this.shtJudgeY, this.shtImgHeight)) {
            this.setStand(this.bytDirection);
            return;
        }
        this.switchActionState((byte) 4);
        this.bytDirection = _direction;
        this.bytFrameIndex = 0;
        this.setRoleAnimation();
        this.setSkillFrame(this.curSkillObj, 1);
    }

    protected void setPull(final byte _direction) {
    }

    protected void setDead(final byte _direction) {
    }

    protected void doFight() {
        if (this.sprInstance != null && this.sprInstance.isPlayDone()) {
            this.blnTaskOK = true;
            this.setStand(this.bytDirection);
        }
    }

    protected void doMagic() {
        final int _time = (int) System.currentTimeMillis();
        ++this.bytFrameIndex;
        boolean allSkillDone = true;
        final Enumeration enumSkill = this.vecNextSkill.elements();
        while (enumSkill.hasMoreElements()) {
            SkillObject skill = (SkillObject) enumSkill.nextElement();
            final String encode = this.getRoleSpriteEncode(skill.bytSkillPicId);
            if (skill.byteActive == 0) {
                if (skill.shtUseTime > 0) {
                    if (skill.shtMagicTime > 0) {
                        final SkillObject skillObject = skill;
                        skillObject.shtMagicTime -= (short) (_time - this.intMTime);
                        allSkillDone = false;
                    } else {
                        this.removeSkill(this.getRoleSpriteEncode(skill.bytSkillPicId));
                        this.cleanMagic();
                        this.blnTaskOK = true;
                        this.pushTask((byte) 0, this.bytDirection);
                    }
                } else {
                    if (!this.checkFrameDone(skill)) {
                        continue;
                    }
                    this.removeSkill(this.getRoleSpriteEncode(skill.bytSkillPicId));
                    this.cleanMagic();
                    this.blnTaskOK = true;
                    this.pushTask((byte) 0, this.bytDirection);
                }
            }
        }
        this.intMTime = _time;
    }

    protected void doPull() {
    }

    protected void doDead() {
    }

    protected void setMoveFrame() {
    }

    protected void addTempTask() {
    }

    protected void checkEvent() {
    }

    protected void delSkillEndRole(final boolean _type) {
    }

    protected void withMeLogic(final int _time) {
    }

    public void setSpeed(final byte _speedType) {
    }

    public void switchActionState(final byte action) {
        this.bytStateLast = this.bytState;
        this.bytState = action;
        if (this.bytType == 2 && this.bytStateLast == 4 && this.bytState != 4) {
            if (this.curSkillObj != null) {
                this.removeSkill(this.getRoleSpriteEncode(this.curSkillObj.bytSkillPicId));
            }
            this.cleanMagic();
        }
        if (this.bytState != -1) {
            if (ORPMe.ME != null && this.intUserId == ORPMe.ME.intUserId && this.bytState != 1) {
                ORPMe.ME.blnKeepDirMove = false;
                ORPMe.ME.intKeepMoveKeyCode = -1;
                Macro.blnDistanceFar = false;
                Macro.blnDistanceFarAStar = false;
                ORPMe.ME.bytSelectPos = null;
            }
            if (this.bytType == 2 && ((ORPlayer) this).checkIsRidePet() && (this.bytState == 2 || this.bytState == 4 || this.bytState == 5)) {
                this.blnNeedConcealRidePet = true;
                this.blnSelectedRide = false;
                if (this.intUserId == ORPMe.ME.intUserId) {
                    NetSend.getInstance().sendPetHandle((byte) 3, 0);
                }
            } else {
                this.blnNeedConcealRidePet = false;
            }
        }
    }

    public byte chatLogic() {
        ++this.bytChatTime;
        if (this.bytChatTime > 50) {
            this.bytChatTime = 0;
        }
        return this.bytChatTime;
    }

    public void addBuff(final int _id, final int _CD, final short _icon, final String _name, final String _info, final int _playerid, final int _num) {
        if (this.intsBuff == null) {
            this.intsBuff = new int[1][6];
            this.strsBuff = new String[1][2];
        } else {
            for (int i = 0; i < this.intsBuff.length; ++i) {
                if (this.intsBuff[i][0] == _id && this.intsBuff[i][4] == _playerid) {
                    this.intsBuff[i][1] = _CD;
                    this.intsBuff[i][2] = _icon;
                    this.intsBuff[i][3] = ((_CD == 0) ? 1 : _CD);
                    this.strsBuff[i][0] = _name;
                    this.strsBuff[i][1] = _info;
                    GameUI.getInstance().addStateIcon(_icon);
                    return;
                }
            }
            if (this.intsBuff.length >= 8) {
                return;
            }
            final int[][] _tempIntsBuff = this.intsBuff;
            final String[][] _temStrsBuff = this.strsBuff;
            this.intsBuff = new int[_tempIntsBuff.length + 1][6];
            this.strsBuff = new String[_temStrsBuff.length + 1][2];
            System.arraycopy(_tempIntsBuff, 0, this.intsBuff, 0, _tempIntsBuff.length);
            System.arraycopy(_temStrsBuff, 0, this.strsBuff, 0, _temStrsBuff.length);
        }
        this.intsBuff[this.intsBuff.length - 1][0] = _id;
        this.intsBuff[this.intsBuff.length - 1][1] = _CD;
        this.intsBuff[this.intsBuff.length - 1][2] = _icon;
        this.intsBuff[this.intsBuff.length - 1][3] = ((_CD == 0) ? 1 : _CD);
        this.intsBuff[this.intsBuff.length - 1][4] = _playerid;
        this.intsBuff[this.intsBuff.length - 1][5] = _num;
        this.strsBuff[this.strsBuff.length - 1][0] = _name;
        this.strsBuff[this.strsBuff.length - 1][1] = _info;
        GameUI.getInstance().addStateIcon(_icon);
    }

    public void updateBuff(final int _buffid, final int _oldplayerId, final int _newplayerId, final int _cdtime, final int _num, final short _icon, final String _name) {
        if (this.intsBuff != null) {
            for (int i = 0; i < this.intsBuff.length; ++i) {
                if (this.intsBuff[i][0] == _buffid && this.intsBuff[i][4] == _oldplayerId) {
                    this.intsBuff[i][1] = _cdtime;
                    this.intsBuff[i][3] = ((_cdtime == 0) ? 1 : _cdtime);
                    this.intsBuff[i][4] = _newplayerId;
                    this.intsBuff[i][5] = _num;
                    return;
                }
            }
        }
        if (Param.getInstance().intsBuffColor == null) {
            Param.getInstance().intsBuffColor = DrawBase.getRGB(8, 8, -1721473948);
        }
        this.addBuff(_buffid, _cdtime, _icon, _name, _name, _newplayerId, _num);
    }

    public void delBuff(final int _id, final int _playerid) {
        if (this.intsBuff != null) {
            int i = -1;
            int l;
            for (l = 0, l = 0; l < this.intsBuff.length; ++l) {
                if (this.intsBuff[l][0] == _id && this.intsBuff[l][4] == _playerid) {
                    i = l;
                    break;
                }
            }
            if (i != -1) {
                if (DialogUI.getInstance() != null) {
                    DialogUI.getInstance().checkDialogBuffInfo(this.intUserId, this.strsBuff[i][0], i);
                }
                if (this.intsBuff.length == 1) {
                    this.intsBuff = null;
                    this.strsBuff = null;
                } else {
                    final short _icon = (short) this.intsBuff[i][2];
                    final int[][] _tempIntsBuff = this.intsBuff;
                    final String[][] _temStrsBuff = this.strsBuff;
                    this.intsBuff = new int[_tempIntsBuff.length - 1][5];
                    this.strsBuff = new String[_temStrsBuff.length - 1][2];
                    if (i != 0) {
                        System.arraycopy(_tempIntsBuff, 0, this.intsBuff, 0, i);
                        System.arraycopy(_temStrsBuff, 0, this.strsBuff, 0, i);
                    }
                    if (i != _tempIntsBuff.length - 1) {
                        System.arraycopy(_tempIntsBuff, i + 1, this.intsBuff, i, _tempIntsBuff.length - i - 1);
                        System.arraycopy(_temStrsBuff, i + 1, this.strsBuff, i, _temStrsBuff.length - i - 1);
                    }
                }
            }
        }
    }

    public void addDeBuff(final int _id, final int _CD, final short _icon, final String _name, final String _info, final int _playerid, final int _num) {
        if (this.intsDeBuff == null) {
            this.intsDeBuff = new int[1][6];
            this.strsDeBuff = new String[1][2];
        } else {
            for (int i = 0; i < this.intsDeBuff.length; ++i) {
                if (this.intsDeBuff[i][0] == _id && this.intsDeBuff[i][4] == _playerid) {
                    this.intsDeBuff[i][1] = _CD;
                    this.intsDeBuff[i][2] = _icon;
                    this.intsDeBuff[i][3] = ((_CD == 0) ? 1 : _CD);
                    this.strsDeBuff[i][0] = _name;
                    this.strsDeBuff[i][1] = _info;
                    GameUI.getInstance().addStateIcon(_icon);
                    return;
                }
            }
            if (this.intsDeBuff.length >= 8) {
                return;
            }
            final int[][] _tempIntsBuff = this.intsDeBuff;
            final String[][] _temStrsBuff = this.strsDeBuff;
            this.intsDeBuff = new int[_tempIntsBuff.length + 1][6];
            this.strsDeBuff = new String[_temStrsBuff.length + 1][2];
            System.arraycopy(_tempIntsBuff, 0, this.intsDeBuff, 0, _tempIntsBuff.length);
            System.arraycopy(_temStrsBuff, 0, this.strsDeBuff, 0, _temStrsBuff.length);
        }
        this.intsDeBuff[this.intsDeBuff.length - 1][0] = _id;
        this.intsDeBuff[this.intsDeBuff.length - 1][1] = _CD;
        this.intsDeBuff[this.intsDeBuff.length - 1][2] = _icon;
        this.intsDeBuff[this.intsDeBuff.length - 1][3] = ((_CD == 0) ? 1 : _CD);
        this.intsDeBuff[this.intsDeBuff.length - 1][4] = _playerid;
        this.intsDeBuff[this.intsDeBuff.length - 1][5] = _num;
        this.strsDeBuff[this.strsDeBuff.length - 1][0] = _name;
        this.strsDeBuff[this.strsDeBuff.length - 1][1] = _info;
        GameUI.getInstance().addStateIcon(_icon);
    }

    public void updateDebuff(final int _buffid, final int _oldplayerId, final int _newplayerId, final int _cdtime, final int _num, final short _icon, final String _name) {
        if (this.intsDeBuff != null) {
            for (int i = 0; i < this.intsDeBuff.length; ++i) {
                if (this.intsDeBuff[i][0] == _buffid && this.intsDeBuff[i][4] == _oldplayerId) {
                    this.intsDeBuff[i][1] = _cdtime;
                    this.intsDeBuff[i][3] = ((_cdtime == 0) ? 1 : _cdtime);
                    this.intsDeBuff[i][4] = _newplayerId;
                    this.intsDeBuff[i][5] = _num;
                    return;
                }
            }
        }
        if (Param.getInstance().intsBuffColor == null) {
            Param.getInstance().intsBuffColor = DrawBase.getRGB(8, 8, -1721473948);
        }
        this.addDeBuff(_buffid, _cdtime, _icon, _name, _name, _newplayerId, _num);
    }

    public void delDeBuff(final int _id, final int _playerid) {
        if (this.intsDeBuff != null) {
            int i = -1;
            int l;
            for (l = 0, l = 0; l < this.intsDeBuff.length; ++l) {
                if (this.intsDeBuff[l][0] == _id && this.intsDeBuff[l][4] == _playerid) {
                    i = l;
                    break;
                }
            }
            if (i != -1) {
                if (DialogUI.getInstance() != null) {
                    DialogUI.getInstance().checkDialogBuffInfo(this.intUserId, this.strsDeBuff[i][0], i + 10);
                }
                if (this.intsDeBuff.length == 1) {
                    this.intsDeBuff = null;
                    this.strsDeBuff = null;
                } else {
                    final int[][] _tempIntsBuff = this.intsDeBuff;
                    final String[][] _temStrsBuff = this.strsDeBuff;
                    this.intsDeBuff = new int[_tempIntsBuff.length - 1][5];
                    this.strsDeBuff = new String[_temStrsBuff.length - 1][2];
                    if (i != 0) {
                        System.arraycopy(_tempIntsBuff, 0, this.intsDeBuff, 0, i);
                        System.arraycopy(_temStrsBuff, 0, this.strsDeBuff, 0, i);
                    }
                    if (i != _tempIntsBuff.length - 1) {
                        System.arraycopy(_tempIntsBuff, i + 1, this.intsDeBuff, i, _tempIntsBuff.length - i - 1);
                        System.arraycopy(_temStrsBuff, i + 1, this.strsDeBuff, i, _temStrsBuff.length - i - 1);
                    }
                }
            }
        }
    }

    public void drawEffect(final Graphics g, final short _tx, short _ty) {
        if (this.intsBuff != null && this.intsBuff.length > 0 && this.strsBuff != null && this.strsBuff.length > 0) {
            int _x = _tx;
            g.setColor(10309913);
            for (int i = 0; i < this.strsBuff.length; ++i) {
                if ((this.intsBuff[i][1] > 5000 || this.intsBuff[i][1] / 300 % 3 < 2) && this.intsBuff[i][3] != 0) {
                    final short _icon = (short) this.intsBuff[i][2];
                    final Image iconImage = GameUI.getInstance().getStateIcon(_icon);
                    if (iconImage != null) {
                        DrawBase.drawImage(iconImage, _x, _ty, 24);
                    }
                    g.drawRect(_x - 1 - 9, _ty - 1, 9, 9);
                }
                _x -= 9;
            }
        }
        if (this.intsDeBuff != null && this.intsDeBuff.length > 0 && this.strsDeBuff != null && this.strsDeBuff.length > 0) {
            _ty += 9;
            int _x = _tx;
            g.setColor(10309913);
            for (int i = 0; i < this.strsDeBuff.length; ++i) {
                if ((this.intsDeBuff[i][1] > 5000 || this.intsDeBuff[i][1] / 300 % 3 < 2) && this.intsDeBuff[i][3] != 0) {
                    final short _icon = (short) this.intsDeBuff[i][2];
                    final Image iconImage = GameUI.getInstance().getStateIcon(_icon);
                    if (iconImage != null) {
                        DrawBase.drawImage(iconImage, _x, _ty, 24);
                    }
                    g.drawRect(_x - 1 - 9, _ty - 1, 9, 9);
                }
                _x -= 9;
            }
        }
    }

    public void addDamageData(final int _data, final byte _type) {
        if (this.intsDamageData == null) {
            this.intsDamageData = new int[5];
            this.intsDamageType = new byte[5];
        }
        try {
            for (int i = 0; i < this.intsDamageData.length; ++i) {
                if (this.intsDamageType[i] == 0) {
                    this.intsDamageData[i] = _data;
                    this.intsDamageType[i] = _type;
                    this.fightValue.addElement(new FightValue(this.bytType, this.bytDirection, _type, _data, this.fightValueOffsetX, this.fightValueOffsetY));
                    break;
                }
            }
            final int l = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void drawDamageData(final Graphics g) {
        try {
            final int _drawx = this.shtJudgeX + this.shtDrawNameX - Param.getInstance().CAMERAX;
            final int _drawy = this.shtJudgeY + this.shtDrawNameY - Param.getInstance().CAMERAY;
            this.drawFightValue(g, _drawx, _drawy);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void drawFightValue(final Graphics g, final int x, final int y) {
        for (int k = 0; k < this.fightValue.size(); ++k) {
            final FightValue value = (FightValue) this.fightValue.elementAt(k);
            value.paint(g, x - value.startX, y, 0);
        }
    }

    public void drawAddValue(final Graphics g, final int x, final int y) {
        for (int k = 0; k < this.addValue.size(); ++k) {
            FightValue value = (FightValue) this.addValue.elementAt(k);
            value.paint(g, x - value.startX, y, 0);
        }
    }

    public void drawAddValueMp(final Graphics g, final int x, final int y) {
        for (int k = 0; k < this.addValueMp.size(); ++k) {
            FightValue value = (FightValue) this.addValueMp.elementAt(k);
            value.paint(g, x - value.startX, y, 0);
        }
    }

    public void addCureData(final int _data) {
        if (this.intsCureData == null) {
            this.intsCureData = new int[5];
        }
        final int i = 0;
        if (i < this.intsCureData.length) {
            this.intsCureData[i] = _data;
            this.addValue.addElement(new FightValue((byte) 11, (byte) 13, (byte) 0, _data, this.fightValueOffsetX, this.fightValueOffsetY));
        }
    }

    public void addMpData(final int _data) {
        if (this.intsMpDate == null) {
            this.intsMpDate = new int[5];
        }
        final int i = 0;
        if (i < this.intsMpDate.length) {
            this.intsMpDate[i] = _data;
            this.addValueMp.addElement(new FightValue((byte) 21, (byte) 13, (byte) 0, _data, this.fightValueOffsetX, this.fightValueOffsetY));
        }
    }

    public void drawCureData(final Graphics g) {
        final int _drawx = this.shtJudgeX + this.shtDrawNameX - Param.getInstance().CAMERAX + 10;
        final int _drawy = this.shtJudgeY + this.shtDrawNameY - Param.getInstance().CAMERAY - 10;
        this.drawFightValue(g, _drawx, _drawy);
    }

    public void drawaddValue(final Graphics g) {
        final int _drawx = this.shtJudgeX + this.shtDrawNameX - Param.getInstance().CAMERAX + 14;
        final int _drawy = this.shtJudgeY + this.shtDrawNameY - Param.getInstance().CAMERAY - 10;
        this.drawAddValueMp(g, _drawx, _drawy);
    }

    public void addSpecialtiesState(final byte _type) {
        if (this.bytSpecialtiesState == null) {
            this.bytSpecialtiesState = new byte[]{_type};
        } else {
            for (int i = 0; i < this.bytSpecialtiesState.length; ++i) {
                if (_type == this.bytSpecialtiesState[i]) {
                    return;
                }
            }
            final byte[] tempSpecialtiesState = this.bytSpecialtiesState;
            System.arraycopy(tempSpecialtiesState, 0, this.bytSpecialtiesState = new byte[tempSpecialtiesState.length + 1], 0, tempSpecialtiesState.length);
            this.bytSpecialtiesState[this.bytSpecialtiesState.length - 1] = _type;
        }
    }

    public void delSpecialtiesState(final byte _type) {
        if (this.bytSpecialtiesState != null) {
            int i = -1;
            int l;
            for (l = 0, l = 0; l < this.bytSpecialtiesState.length; ++l) {
                if (this.bytSpecialtiesState[l] == _type) {
                    i = l;
                    break;
                }
            }
            if (i != -1) {
                if (this.bytSpecialtiesState.length == 1) {
                    this.bytSpecialtiesState = null;
                } else {
                    final byte[] tempSpecialtiesState = this.bytSpecialtiesState;
                    this.bytSpecialtiesState = new byte[tempSpecialtiesState.length - 1];
                    if (i != 0) {
                        System.arraycopy(tempSpecialtiesState, 0, this.bytSpecialtiesState, 0, i);
                    }
                    if (i != tempSpecialtiesState.length - 1) {
                        System.arraycopy(tempSpecialtiesState, i + 1, this.bytSpecialtiesState, i, tempSpecialtiesState.length - i - 1);
                    }
                }
            }
        }
    }

    public int getShadeType(final int _num) {
        int type = 0;
        if (_num <= 0) {
            type = -1;
        } else if (_num <= 2) {
            type = 0;
        } else if (_num <= 4) {
            type = 1;
        } else if (_num <= 6) {
            type = 2;
        } else {
            type = 3;
        }
        return type;
    }

    protected boolean checkSpriteDone(final String encodeId) {
        QSprite sprSkill = (QSprite) this.htNextSkill.get(encodeId);
        return sprSkill != null && sprSkill.isPlayDone();
    }

    protected boolean checkRoleActionSpriteDone() {
        return this.sprInstance != null && this.sprInstance.isPlayDone();
    }

    public static int transDirection(final int serverDirection) {
        return serverDirection - 1;
    }

    public static short transWeaponType(final short servertype) {
        return servertype;
    }

    public static byte transDirectionClientToServer(final int clientDirection) {
        return (byte) (clientDirection + 1);
    }

    public static int transGender(final int serverGender) {
        return serverGender;
    }

    public int transLayer(final int layer) {
        if (layer == 1) {
            return 10;
        }
        if (layer == 2) {
            return 11;
        }
        return -1;
    }

    public static String getOccName(final byte _occIndex) {
        if (_occIndex < 0 || _occIndex > IRoleDefine.ROLE_OCCU_NAME.length) {
            DebugFrame.getInstance().logIn("Error! \u83b7\u5f97\u804c\u4e1a\u540d\u79f0\u9519\u8bef!!");
            return "";
        }
        return IRoleDefine.ROLE_OCCU_NAME[_occIndex - 1];
    }

    public static byte getOccEquipBase(final byte _occIndex) {
        switch (_occIndex) {
            case 1: {
                return 3;
            }
            case 3:
            case 4: {
                return 2;
            }
            case 5:
            case 6:
            case 7:
            case 8: {
                return 1;
            }
            default: {
                DebugFrame.getInstance().logIn("Error!! \u804c\u4e1a\u7f16\u53f7[" + _occIndex + "]\u9519\u8bef");
                return 0;
            }
        }
    }

    public static byte getOccEquipExtend(final byte _occIndex) {
        switch (_occIndex - 1) {
            case 0:
            case 3:
            case 9:
            case 11:
            case 17: {
                return 2;
            }
            case 1:
            case 6:
            case 7:
            case 8:
            case 14:
            case 15:
            case 16: {
                return 1;
            }
            case 2:
            case 4:
            case 5:
            case 10:
            case 12:
            case 13: {
                return 3;
            }
            default: {
                DebugFrame.getInstance().logIn("Error!! \u804c\u4e1a\u7f16\u53f7[" + _occIndex + "]\u9519\u8bef");
                return 0;
            }
        }
    }

    public static boolean canEquipEquipment(final byte career, final byte equipment) {
        boolean can = false;
        int canEquipmentType = 0;
        if (equipment == 1) {
            return true;
        }
        if (equipment > 3) {
            can = true;
        } else {
            switch (career) {
                case 3:
                case 4:
                case 13:
                case 14:
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                case 20: {
                    canEquipmentType = 1;
                    break;
                }
                case 2:
                case 9:
                case 10:
                case 11:
                case 12: {
                    canEquipmentType = 2;
                    break;
                }
                case 1:
                case 5:
                case 6:
                case 7:
                case 8: {
                    canEquipmentType = 3;
                    break;
                }
            }
            if (canEquipmentType >= equipment) {
                can = true;
            }
        }
        return can;
    }

    public static boolean canEquipWeapon(final byte career, final byte weapon) {
        boolean can = false;
        switch (weapon) {
            case 10: {
                if (career == 2 || career == 9 || career == 10 || career == 11 || career == 12) {
                    can = true;
                    break;
                }
                break;
            }
            case 11: {
                if (career == 1 || career == 5 || career == 6 || career == 4 || career == 17 || career == 18 || career == 7 || career == 8 || career == 19 || career == 20) {
                    can = true;
                    break;
                }
                break;
            }
            case 12: {
                if (career == 3 || career == 13 || career == 18 || career == 4 || career == 17 || career == 14 || career == 15 || career == 16 || career == 19 || career == 20) {
                    can = true;
                    break;
                }
                break;
            }
            case 13: {
                if (career == 2 || career == 9 || career == 10 || career == 11 || career == 12) {
                    can = true;
                    break;
                }
                break;
            }
            case 14: {
                if (career == 1 || career == 5 || career == 6 || career == 3 || career == 13 || career == 14 || career == 7 || career == 8 || career == 15 || career == 16) {
                    can = true;
                    break;
                }
                break;
            }
            case 15: {
                can = false;
                break;
            }
        }
        return can;
    }

    public static String WeaponToCareer(final byte equipType) {
        String Career = null;
        switch (equipType) {
            case 10: {
                Career = "\u65a5\u4faf";
                break;
            }
            case 11: {
                Career = "\u529b\u58eb\u3001\u5deb\u533b";
                break;
            }
            case 12: {
                Career = "\u6cd5\u5e08\u3001\u5deb\u533b";
                break;
            }
            case 13: {
                Career = "\u65a5\u4faf";
                break;
            }
            case 14: {
                Career = "\u529b\u58eb\u3001\u6cd5\u5e08";
                break;
            }
            case 15: {
                Career = "\u65a5\u4faf";
                break;
            }
            case 1: {
                Career = "\u529b\u58eb\u3001\u65a5\u4faf\u3001\u6cd5\u5e08\u3001\u5deb\u533b";
                break;
            }
            case 2: {
                Career = "\u529b\u58eb\u3001\u65a5\u4faf";
                break;
            }
            case 3: {
                Career = "\u529b\u58eb";
                break;
            }
        }
        return Career;
    }

    public void checkRole(final byte _TileX, final byte _TileY, final byte _speed) {
        if (Math.abs(this.bytTileX - _TileX) + Math.abs(this.bytTileY - _TileY) > 2) {
            this.bytSpeed = _speed;
            final AStar as = new AStar(this.bytTileX, this.bytTileY, _TileX, _TileY, (byte) 4, (byte) 1);
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

    public short getJudgeX() {
        return (short) (this.bytTileX * 16 + 8);
    }

    public short getJudgeY() {
        return (short) ((this.bytTileY + 1 - this.bytTileZ) * 16 - 8);
    }

    public static byte transPicSize(byte picSize) {
        if (picSize <= 0) {
            DebugFrame.getInstance().logIn("Error! \u89d2\u8272\u5927\u5c0f\u4e0d\u80fd\u4e3a0\u6216\u8d1f\u6570!");
            picSize = 1;
        }
        if (picSize % 2 == 0) {
            --picSize;
        }
        return picSize;
    }

    public int getSkillYOffByType(final byte yOffType) {
        final short roleHalfHeight = (short) (this.shtImgHeight / 2);
        short roleYOff = 0;
        if (yOffType > 0 && yOffType < 4) {
            roleYOff -= (short) (roleHalfHeight * (yOffType - 1));
        } else {
            DebugFrame.getInstance().logIn("Error!! \u6280\u80fd\u504f\u79fb\u7c7b\u578b\u503c\u9519\u8bef");
        }
        return roleYOff;
    }

    public void addLvUPImg() {
        if (this.sprLevelUp == null) {
            ++ORole.bytLevelUpNum;
            (this.sprLevelUp = ImageManager.loadSpriteById(3, this.getRoleSpriteEncode("levelup"), "levelup", "levelup", "ingame")).setLoopOffset(1);
            this.sprLevelUp.setAnimation(0);
        }
    }

    public void delLvUPImg() {
        this.sprLevelUp = null;
        --ORole.bytLevelUpNum;
        if (ORole.bytLevelUpNum <= 0) {
            ORole.bytLevelUpNum = 0;
        }
    }

    public void addJumpImg() {
        if (this.sprEnterJump == null) {
            ++ORole.bytEnterJumpNum;
            (this.sprEnterJump = ImageManager.loadSpriteById(3, "transporteffect01", "transporteffect01", "transporteffect01", "ingame")).setLoopOffset(1);
            this.sprEnterJump.setAnimation(0);
        }
    }

    public void delJumpImg() {
        --ORole.bytEnterJumpNum;
        this.sprEnterJump = null;
        if (ORole.bytEnterJumpNum <= 0) {
            ORole.bytEnterJumpNum = 0;
        }
    }

    public void recvUpdateResMsg() {
        if (this.blnUseDefault && this.blnNeedUpdate && this.sprInstance != null) {
            final String strPngPath = "/" + this.strResPath + "/" + this.shtPicId + ".png";
            final String strAnuPath = "/" + this.strResPath + "/" + this.shtAnuId + ".anu";
            final boolean isSuccess = this.sprInstance.setQSpriteData(strAnuPath, strPngPath, this.strResPath, this.intPoolType);
            if (isSuccess) {
                this.blnUseDefault = false;
                this.blnNeedUpdate = false;
                this.setRoleAnimation(true);
            }
        }
    }
}
