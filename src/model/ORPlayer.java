// 
// Decompiled by Procyon v0.5.36
// 
package model;

import common.Common;
import common.LayoutStyle;
import face.MenuUI;
import means.StringManager;
import common.IDefines;
import face.GameUI;
import base.DCanvas;
import means.FightValue;
import java.util.Enumeration;
import network.NetSend;
import network.NetParse;
import face.DialogUI;
import means.ImageManager;
import base.Macro;
import java.io.IOException;
import means.DebugFrame;
import java.io.DataInputStream;
import base.Param;
import java.util.Vector;
import means.QSprite;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import java.util.Hashtable;

public class ORPlayer extends ORole implements IRoleDefine {

    public short shtPortraitId;
    public byte bytConcealDrawRole;
    public byte bytConcealIndex;
    public byte bytConcealAdd;
    public short shtConcealtime;
    public short shtHeadId;
    public short shtHeadAnuId;
    public short shtHairId;
    public short shtHairAnuId;
    public short shtTailId;
    public short shtTailAnuId;
    public short shtHeadwearID;
    public short shtHeadwearAnuID;
    public short shtLegId;
    public short shtLegAnuId;
    public short shtEquipId;
    public short shtEquipAnuId;
    public short shtEquipPngIdReal;
    public short shtEquipAnuIdReal;
    public short shtEquipRealLevel;
    public short shtEquipEffectRoundId;
    public short shtEquipEffectAnuRoundId;
    public short shtEquipEffectId;
    public short shtEquipEffectAnuId;
    public short shtWeaponId;
    public short shtWeaponAnuId;
    public short shtWeaponPngIdReal;
    public short shtWeaponAnuIdReal;
    public short shtWeaponRealLevel;
    public short bytWeaponPart;
    public short shtPetId;
    public short shtPetAnuId;
    public short shtWeaponAttackEffectID;
    public short shtWeaponAttackEffectAnuID;
    public short shtWeaponRoundEffectID;
    public short shtWeaponRoundEffectAnuID;
    public short shtWeaponLevel;
    public short shtShade;
    public short shtShadeAnu;
    public short shtRoleDeadId;
    public short shtRoleDeadAnuId;
    public Hashtable htPlayerData;
    public byte bytIntensify;
    public Intensify itsf;
    public static int itSelectPetId;
    public int roleActionId;
    boolean isSyncAnimationStep;
    boolean isReCacheAnimation;
    public Hashtable s_htRoleParts;
    public PetObject playerFollowPet;
    public boolean blnPlayerResOK;
    private short shtCheckResTime;
    public boolean blnRelive;
    public boolean blnStall;
    public String strStallInfo;
    public boolean blnTeam;
    public boolean blnKeepMagic;
    public boolean blnUseSpellAction;
    public byte bytSkillTargetType;
    public short shtSendSkillTime;
    public int intSkillTargetID;
    public byte bytCartoon;
    public boolean[] blnIsRolePartUpdate;
    public static Image playerPortraitImage;
    public static Graphics playerPortraitImageGraphics;
    public QSprite sprStallInstance;
    public byte PARTNER_relation;
    public String PARTNER_name;
    public int PARTNER_level;
    public String PARTNER_race;
    public String PARTNER_occupation;
    public String PARTNER_union;
    public int PARTNER_loves_value;
    public int PARTNER_loves_days;
    public int PARTNER_loves_ranking;
    public String PARTNER_loves_Lv;

    static {
        ORPlayer.itSelectPetId = 0;
    }

    public ORPlayer() {
        this.isSyncAnimationStep = true;
        this.isReCacheAnimation = false;
        this.s_htRoleParts = new Hashtable();
        super.bytType = 2;
        super.bytDirection = 2;
        super.shtImgWidth = 29;
        super.shtImgHeight = 48;
        super.shtDrawNameX = 0;
        super.shtDrawNameY = (short) (-super.shtImgHeight);
        super.bytPicSize = ORole.transPicSize((byte) 1);
        super.bytShadowPicSize = super.bytPicSize;
        super.blnIsAttack = false;
        this.shtHeadId = -1;
        this.shtHeadAnuId = -1;
        this.shtHairId = -1;
        this.shtHairAnuId = -1;
        this.shtTailId = -1;
        this.shtTailAnuId = -1;
        this.shtHeadwearID = -1;
        this.shtHeadwearAnuID = -1;
        this.shtLegId = -1;
        this.shtLegAnuId = -1;
        this.shtEquipId = -1;
        this.shtEquipAnuId = -1;
        this.shtWeaponId = -1;
        this.shtWeaponAnuId = -1;
        this.shtPetId = -1;
        this.shtPetAnuId = -1;
        this.bytWeaponPart = -1;
        this.shtWeaponAttackEffectID = -1;
        this.shtWeaponAttackEffectAnuID = -1;
        this.shtWeaponRoundEffectID = -1;
        this.shtWeaponRoundEffectAnuID = -1;
        this.shtShade = -1;
        this.shtShadeAnu = -1;
        this.shtRoleDeadId = -1;
        this.shtRoleDeadAnuId = -1;
        this.shtWeaponLevel = 0;
        super.bytRoleMoveFrame = 3;
        super.vTask = new Vector(1, 1);
        super.blnTaskOK = true;
        this.blnPlayerResOK = true;
        super.blnIsUpdate = true;
        this.resetRolePartUpdate(super.blnIsDraw = true);
        super.intShadeAnimation = (byte) this.getShadeType(super.bytShadowPicSize);
        super.shadeSprInstance = Param.getInstance().sprShadow;
    }

    public void setRolePortrait(final short _PortraitId) {
        this.shtPortraitId = _PortraitId;
        super.bytDrawPortraitId = (byte) _PortraitId;
    }

    public void setRolePartUpdate(final int part, final boolean update) {
        this.blnIsRolePartUpdate[part] = update;
    }

    public boolean getRolePartUpdate(final int part) {
        return part >= 0 && part < 15 && this.blnIsRolePartUpdate[part];
    }

    public void resetRolePartUpdate(final boolean update) {
        if (this.blnIsRolePartUpdate == null) {
            this.blnIsRolePartUpdate = new boolean[15];
        }
        for (int i = 0; i < 15; ++i) {
            this.blnIsRolePartUpdate[i] = update;
        }
    }

    public void checkRes() {
        if (this.blnPlayerResOK) {
            if (!Map.getInstance().judgeIsDraw(super.shtJudgeX, super.shtImgWidth, super.shtJudgeY, super.shtImgHeight, 16)) {
                this.blnPlayerResOK = false;
                this.releaseRoleRes();
            }
        } else if (Map.getInstance().judgeIsDraw(super.shtJudgeX, super.shtImgWidth, super.shtJudgeY, super.shtImgHeight, 16)) {
            if (this.bytIntensify >= 2) {
                this.loadIntensify();
            }
            this.blnPlayerResOK = true;
        }
    }

    public void addRoleAllRes(final DataInputStream dis) {
        try {
            this.addRoleRes(0, dis.readShort(), dis.readShort());
            this.addRoleRes(1, dis.readShort(), dis.readShort());
            this.addRoleRes(-1, dis.readShort(), dis.readShort());
            dis.readByte();
            this.addRoleRes(2, dis.readShort(), dis.readShort());
            dis.readByte();
            this.addRoleRes(7, dis.readShort(), dis.readShort());
            this.addRoleRes(3, dis.readShort(), dis.readShort());
            final short _weaponType = dis.readShort();
            this.bytWeaponPart = ORole.transWeaponType(_weaponType);
            this.addRoleRes(4, dis.readShort(), dis.readShort());
            this.addRoleRes(5, dis.readShort(), dis.readShort());
            this.addRoleRes(6, dis.readShort(), dis.readShort());
            this.addRoleRes(8, dis.readShort(), dis.readShort());
            this.addRoleRes(9, dis.readShort(), dis.readShort());
            this.addRoleRes(10, (short) (-1), (short) (-1));
            this.addRoleRes(11, (short) (-1), (short) (-1));
            this.addRoleRes(12, dis.readShort(), dis.readShort());
            this.addRoleRes(13, dis.readShort(), dis.readShort());
            this.addRoleRes(13, (short) (-1), (short) (-1));
        } catch (IOException e) {
            DebugFrame.getInstance().logIn("\u4eba\u7269\u7ec4\u6210\u6570\u636e\u89e3\u6790\u9519\u8bef!!");
        }
    }

    public QSprite addRoleRes(final int resType, final short pngId, final short anuId) {
        return this.addRoleRes(resType, pngId, anuId, true);
    }

    public QSprite addRoleRes(final int resType, final short pngId, final short anuId, final boolean isNeedLoadOnTime) {
        if (resType < 0 || resType >= 15) {
            return null;
        }
        switch (resType) {
            case 0: {
                this.shtHeadId = pngId;
                this.shtHeadAnuId = anuId;
                break;
            }
            case 1: {
                this.shtHairId = pngId;
                this.shtHairAnuId = anuId;
                break;
            }
            case -1: {
                this.shtHeadwearID = pngId;
                this.shtHeadwearAnuID = anuId;
                break;
            }
            case 2: {
                this.shtEquipId = pngId;
                this.shtEquipAnuId = anuId;
                break;
            }
            case 3: {
                this.shtLegId = pngId;
                this.shtLegAnuId = anuId;
                break;
            }
            case 4: {
                this.shtWeaponId = pngId;
                this.shtWeaponAnuId = anuId;
                break;
            }
            case 5: {
                this.shtPetId = pngId;
                this.shtPetAnuId = anuId;
                break;
            }
            case 6: {
                this.shtEquipEffectId = pngId;
                this.shtEquipEffectAnuId = anuId;
                break;
            }
            case 7: {
                this.shtEquipEffectRoundId = pngId;
                this.shtEquipEffectAnuRoundId = anuId;
                break;
            }
            case 8: {
                this.shtWeaponAttackEffectID = pngId;
                this.shtWeaponAttackEffectAnuID = anuId;
                break;
            }
            case 9: {
                this.shtWeaponRoundEffectID = pngId;
                this.shtWeaponRoundEffectAnuID = anuId;
            }
            case 10: {
            }
            case 12: {
                this.shtRoleDeadId = pngId;
                this.shtRoleDeadAnuId = anuId;
                break;
            }
            case 13: {
                this.shtTailId = pngId;
                this.shtTailAnuId = anuId;
                break;
            }
            case 14: {
                this.shtShade = pngId;
                this.shtShadeAnu = anuId;
                break;
            }
        }
        if (this.htPlayerData == null) {
            this.htPlayerData = new Hashtable(1);
        }
        this.htPlayerData.put(new Integer(resType), new short[]{pngId, anuId});
        return this.addRoleResData(resType, pngId, anuId, isNeedLoadOnTime);
    }

    public int getRoleResPoolType(final int resType) {
        int poolType = 4;
        if (resType == 10 || resType == 11) {
            poolType = 7;
        } else if (resType == 2) {
            poolType = 5;
        } else if (resType == 4) {
            poolType = 6;
        }
        return poolType;
    }

    public QSprite addRoleResData(final int resType, final short pngId, final short anuId, final boolean isNeedLoadOnTime) {
        if (this.s_htRoleParts == null) {
            this.s_htRoleParts = new Hashtable();
        }
        if ((pngId != -1 && anuId == -1) || (pngId == -1 && anuId != -1) || (pngId != 0 && anuId == 0) || (pngId == 0 && anuId != 0)) {
            DebugFrame.getInstance().logIn("Warning!! \u4eba\u7269png, anu \u8d44\u6e90\u53ef\u80fd\u9519\u8bef Part Id = " + resType + "png = " + pngId + "/anu = " + anuId);
        }
        if (pngId == -1 || anuId == -1 || pngId != 0) {
        }
        final int poolType = this.getRoleResPoolType(resType);
        final String encode = this.getRoleSpriteEncode(pngId);
        Hashtable htSprite = (Hashtable) this.s_htRoleParts.get(new Integer(resType));
        if (pngId == -1 || anuId == -1 || pngId == 0 || anuId == 0) {
            if (resType == 10 || resType == 11) {
                DebugFrame.getInstance().logIn("Warning!! \u6dfb\u52a0\u7684\u6280\u80fd\u8d44\u6e90\u4e0d\u5b58\u5728" + resType + "png = " + pngId + "/anu = " + anuId);
            } else if (htSprite != null) {
                htSprite.clear();
                DebugFrame.getInstance().logIn("Tip!! \u5220\u9664\u8d44\u6e90" + resType + "png = " + pngId + "/anu = " + anuId);
            }
            return null;
        }
        final QSprite sprite = ImageManager.loadSpriteById(poolType, encode, new StringBuffer(String.valueOf(anuId)).toString(), new StringBuffer(String.valueOf(pngId)).toString(), Macro.STRING_SPRITE_ROLE_RES[resType], isNeedLoadOnTime, isNeedLoadOnTime);
        if (sprite == null) {
            DebugFrame.getInstance().logIn("Warning!! \u6dfb\u52a0\u8d44\u6e90\uff1a\u672a\u627e\u5230\u8d44\u6e90 role id/png/anu" + super.intUserId + "/" + pngId + "/" + anuId);
            return null;
        }
        if (sprite.getData() == null || sprite.getPng() == null) {
            return null;
        }
        if (htSprite == null) {
            htSprite = new Hashtable(1);
            htSprite.put(encode, sprite);
            this.s_htRoleParts.put(new Integer(resType), htSprite);
        } else if (resType == 10 || resType == 11) {
            if (!htSprite.containsKey(encode)) {
                htSprite.put(encode, sprite);
            }
        } else {
            htSprite.clear();
            htSprite.put(encode, sprite);
        }
        return sprite;
    }

    public boolean removeRoleResData(final int resType, final short pngId, final short anuId) {
        if (this.s_htRoleParts == null) {
            return false;
        }
        if ((pngId != -1 && anuId == -1) || (pngId == -1 && anuId != -1) || (pngId != 0 && anuId == 0) || (pngId == 0 && anuId != 0)) {
            DebugFrame.getInstance().logIn("Warning!! \u9700\u5220\u9664\u7684\u4eba\u7269png, anu \u9519\u8bef! Part Id = " + resType + "png = " + pngId + "/anu = " + anuId);
        }
        if (pngId == -1 || anuId == -1 || pngId == 0 || anuId == 0) {
            return false;
        }
        final int poolType = this.getRoleResPoolType(resType);
        final String encode = this.getRoleSpriteEncode(pngId);
        final Hashtable htSprite = (Hashtable) this.s_htRoleParts.get(new Integer(resType));
        if (htSprite != null) {
            if (resType == 10 || resType == 11) {
                if (htSprite.containsKey(encode)) {
                    htSprite.remove(encode);
                }
            } else {
                htSprite.clear();
            }
            return true;
        }
        DebugFrame.getInstance().logIn("Warning!! \u672a\u627e\u5230\u8d44\u6e90 role id/png/anu" + super.intUserId + "/" + pngId + "/" + anuId);
        return false;
    }

    public void releaseRoleRes() {
    }

    private void loadIntensify() {
        if (this.itsf == null) {
            this.itsf = new Intensify();
        }
        this.itsf.setIntensifyId(this.bytIntensify);
    }

    private void releaseIntensify() {
        if (this.itsf != null) {
            this.itsf.delImg(this.bytIntensify);
            this.itsf = null;
        }
    }

    protected void endMove() {
        if (this.checkIsHoldWeapon() && !this.checkIsShowRidePet()) {
            this.setPull(super.bytDirection);
        } else {
            this.setStand(super.bytDirection);
        }
    }

    protected void doMagic() {
        final int _time = (int) System.currentTimeMillis();
        ++super.bytFrameIndex;
        if (super.curSkillObj != null) {
            final String encode = this.getRoleSpriteEncode(super.curSkillObj.bytSkillPicId);
            if (super.curSkillObj.byteActive == 0) {
                if (super.curSkillObj.shtUseTime > 0) {
                    if (super.curSkillObj.shtMagicTime > 0) {
                        final SkillObject curSkillObj = super.curSkillObj;
                        curSkillObj.shtMagicTime -= (short) (_time - super.intMTime);
                    }
                    if (super.curSkillObj.shtMagicTime <= 0) {
                        this.removeSkill(this.getRoleSpriteEncode(super.curSkillObj.bytSkillPicId));
                        this.cleanMagic();
                        super.blnTaskOK = true;
                        this.pushTask((byte) 2, super.bytDirection);
                    }
                    if (this.checkFrameDone()) {
                        this.setRolePartUpdate(0, false);
                        this.setRolePartUpdate(1, false);
                        this.setRolePartUpdate(3, false);
                        this.setRolePartUpdate(4, false);
                        this.setRolePartUpdate(2, false);
                    }
                } else {
                    if (this.blnUseSpellAction && this.checkFrameDone()) {
                        this.setRolePartUpdate(0, false);
                        this.setRolePartUpdate(1, false);
                        this.setRolePartUpdate(3, false);
                        this.setRolePartUpdate(4, false);
                        this.setRolePartUpdate(2, false);
                        this.blnUseSpellAction = false;
                    }
                    if (!this.blnUseSpellAction && this.checkFrameDone(super.curSkillObj)) {
                        this.removeSkill(this.getRoleSpriteEncode(super.curSkillObj.bytSkillPicId));
                        this.cleanMagic();
                        super.blnTaskOK = true;
                        this.pushTask((byte) 0, super.bytDirection);
                    }
                }
            }
        } else {
            this.cleanMagic();
            super.blnTaskOK = true;
            this.pushTask((byte) 0, super.bytDirection);
        }
        super.intMTime = _time;
    }

    public void cleanMagic() {
        super.bytFrameIndex = 0;
        super.bytFrameMax = 0;
        this.resetRolePartUpdate(true);
        this.blnUseSpellAction = false;
        super.curSkillObj = null;
        super.cleanMagic();
    }

    protected void setStand(final byte _direction) {
        this.switchActionState((byte) 0);
        super.bytDirection = _direction;
        super.blnTaskOK = true;
        super.bytFrame = 0;
        super.blnIsRealMove = false;
        super.bytFrameIndex = 0;
        this.setRoleAnimation();
    }

    protected void setPull(final byte _direction) {
        this.switchActionState((byte) 6);
        super.bytDirection = _direction;
        super.bytFrame = 0;
        super.bytFrameIndex = 0;
        this.setRoleAnimation();
        super.blnTaskOK = true;
        super.bytFrameMax = this.getSpriteFrameMax(4, this.getRoleSpriteEncode(this.shtWeaponId), 1);
    }

    protected void doPull() {
        ++super.bytFrameIndex;
        if (this.checkFrameDone()) {
            super.blnTaskOK = true;
            this.setStand(super.bytDirection);
        }
    }

    protected void setDead(final byte _direction) {
        ImageManager.getInstance().addDeadImg();
        this.delStall();
        this.switchActionState((byte) 5);
        super.bytDirection = _direction;
        super.bytFrameIndex = 0;
        this.blnRelive = false;
        super.intsDeBuff = null;
        super.strsDeBuff = null;
        super.intsBuff = null;
        super.strsBuff = null;
        this.cleanAllMagic();
        if (DialogUI.getInstance() != null) {
            DialogUI.getInstance().delDialogBuff(super.intUserId);
        }
        super.bytSpecialtiesState = null;
        this.cleanTask();
        this.setRoleAnimation();
    }

    protected void doDead() {
        if (this.blnRelive) {
            super.blnTaskOK = true;
            super.intSkillDownLayerEffectID = -1;
            this.setStand(super.bytDirection = 2);
            ImageManager.getInstance().delDeadImg();
            this.blnRelive = false;
            Param.lngDeadCountDownStartTime = -1L;
        }
    }

    protected void setFight(final byte _direction) {
        if (Macro.bytGameType == 1 && !Map.getInstance().judgeIsDraw(super.shtJudgeX, super.shtImgWidth, super.shtJudgeY, super.shtImgHeight)) {
            this.setStand(super.bytDirection);
            return;
        }
        this.switchActionState((byte) 2);
        super.bytDirection = _direction;
        super.bytFrame = 0;
        super.bytFrameIndex = 0;
        this.setRoleAnimation();
        super.bytFrameMax = this.getSpriteFrameMax(2, this.getRoleSpriteEncode(this.shtEquipId), 1);
    }

    protected void doFight() {
        ++super.bytFrameIndex;
        if (this.checkFrameDone()) {
            super.blnTaskOK = true;
            super.bytFrameIndex = 0;
            this.setStand(super.bytDirection);
        }
    }

    protected void setMagic(final byte _direction) {
        if (!Map.getInstance().judgeIsDraw(super.shtJudgeX, super.shtImgWidth, super.shtJudgeY, super.shtImgHeight)) {
            this.setStand(super.bytDirection);
            return;
        }
        this.switchActionState((byte) 4);
        super.bytDirection = _direction;
        super.bytFrameIndex = 0;
        if (super.curSkillObj.shtUseTime <= 0) {
            DebugFrame.getInstance().logIn("\u5176\u4ed6\u73a9\u5bb6\u4f7f\u7528\u77ac\u65f6\u6280\u80fdplayer id/png id/anu id" + super.intUserId + "/" + super.curSkillObj.bytSkillPicId + "/" + super.curSkillObj.bytSkillAnuId);
            this.setRoleAnimation(true);
            if (super.bytAnimState == 9) {
                this.blnUseSpellAction = true;
            }
            if (this.blnUseSpellAction) {
                super.bytFrameMax = this.getSpriteFrameMax(2, this.getRoleSpriteEncode(this.shtEquipId), 1);
            }
            this.setSkillFrame(super.curSkillObj, 1);
        } else {
            DebugFrame.getInstance().logIn("\u5176\u4ed6\u73a9\u5bb6\u4f7f\u7528\u6301\u7eed\u6027\u84c4\u529b\u6280\u80fdplayer id/png id/anu id" + super.intUserId + "/" + super.curSkillObj.bytSkillPicId + "/" + super.curSkillObj.bytSkillAnuId);
            this.setRoleAnimation(true);
            this.blnKeepMagic = true;
            if (super.bytAnimState == 9) {
                this.blnUseSpellAction = true;
            }
            super.bytFrameMax = this.getSpriteFrameMax(2, this.getRoleSpriteEncode(this.shtEquipId), 1);
        }
    }

    protected void setCartoon(final byte _type) {
        if (Map.getInstance().judgeIsDraw(super.shtJudgeX, super.shtImgWidth, super.shtJudgeY, super.shtImgHeight)) {
            switch (_type) {
                case 1: {
                    this.addJumpImg();
                    break;
                }
                case 3: {
                    this.addLvUPImg();
                    break;
                }
            }
            this.switchActionState((byte) 3);
            this.bytCartoon = _type;
            super.bytFrame = 0;
            this.cleanAllMagic();
            this.setRoleAnimation();
        } else {
            this.setStand(super.bytDirection);
        }
    }

    protected void doCartoon() {
        ++super.bytFrame;
        if (this.bytCartoon == 3 && super.sprLevelUp.isPlayDone()) {
            this.bytCartoon = 0;
            this.delLvUPImg();
            this.setStand(super.bytDirection);
        }
        if (this.bytCartoon == 1 && super.sprEnterJump.isPlayDone()) {
            this.bytCartoon = 0;
            ORPMe.ME.sendMoveTask();
            NetParse.getInstance().setInitLoadingPre();
            NetSend.getInstance().sendChangeMap(Map.getInstance().shtOutJumpPlace[ORPMe.ME.shtEvent][2], Map.getInstance().shtOutJumpPlace[ORPMe.ME.shtEvent][0], Map.getInstance().shtOutJumpPlace[ORPMe.ME.shtEvent][1]);
            NetParse.getInstance().setInitLoading();
            this.delJumpImg();
            this.setStand(super.bytDirection);
        }
    }

    protected void melogic(final int _time) {
    }

    protected void withMeLogic(final int _time) {
        if (this.playerFollowPet != null && this.playerFollowPet.bytState == 0) {
            this.playerFollowPet.checkRole(super.bytTileX, super.bytTileY, super.bytRoleMoveFrame);
        }
    }

    protected void checkEvent() {
        short intEvent = Map.getInstance().shtsMapEvent[super.bytTileY][super.bytTileX];
        if (intEvent > 0 && intEvent >= 101 && intEvent < 201) {
            intEvent -= 101;
            this.setTileXY(Map.getInstance().bytInJumpPlace[intEvent][2], Map.getInstance().bytInJumpPlace[intEvent][3]);
            this.setStand(super.bytDirection);
        }
    }

    public void draw(final Graphics g) {
        final int _drawx = super.shtJudgeX - Param.getInstance().CAMERAX;
        final int _drawy = super.shtJudgeY - Param.getInstance().CAMERAY;
        this.draw(g, _drawx, _drawy);
    }

    public void drawRolePortrait(final Graphics g) {
        final int _drawPortraitx = 40;
        final int _drawPortraity = 40;
        ORPlayer.playerPortraitImageGraphics.setClip(0, 0, 20, 20);
        this.draw(ORPlayer.playerPortraitImageGraphics, _drawPortraitx, _drawPortraity);
        g.drawImage(ORPlayer.playerPortraitImage, 0, 0, 0);
    }

    public void draw(final Graphics g, final int xoff, final int yoff) {
        if (super.shadeSprInstance != null && super.intShadeAnimation != -1) {
            super.shadeSprInstance.drawAnimationFrame(g, super.intShadeAnimation, 0, xoff, yoff);
        }
        QSprite sprite = null;
        Hashtable htSprite = null;
        for (int partIndex = 0; partIndex < IRoleDefine.ROLE_LAYER_TABLE[this.roleActionId].length; ++partIndex) {
            final int partsId = IRoleDefine.ROLE_LAYER_TABLE[this.roleActionId][partIndex];
            if (partsId >= 0 && partsId < 15) {
                htSprite = (Hashtable) this.s_htRoleParts.get(new Integer(partsId));
                if (htSprite != null) {
                    Enumeration enumSprite = htSprite.elements();
                    while (enumSprite.hasMoreElements()) {
                        sprite = (QSprite) enumSprite.nextElement();
                        if (sprite == null) {
                            continue;
                        }
                        if (sprite == null || sprite.getAnimation() == -1) {
                            continue;
                        }
                        sprite.drawAnimation(g, xoff, yoff);
                    }
                }
            }
        }
        switch (super.bytState) {
            case 3: {
                if (this.bytCartoon == 1) {
                    super.sprEnterJump.drawAnimation(g, xoff, yoff);
                    break;
                }
                if (this.bytCartoon != 2 && this.bytCartoon == 3) {
                    super.sprLevelUp.drawAnimation(g, xoff, yoff);
                    break;
                }
                break;
            }
        }
    }

    public void update() {
        this.updateAllRoleSprite();
        this.updateRoleSkill();
        switch (super.bytState) {
            case 3: {
                if (this.bytCartoon == 1) {
                    super.sprEnterJump.update();
                    break;
                }
                if (this.bytCartoon != 2 && this.bytCartoon == 3) {
                    super.sprLevelUp.update();
                    break;
                }
                break;
            }
        }
        if (super.fightValue != null) {
            int k = 0;
            while (k < super.fightValue.size()) {
                if (((FightValue) super.fightValue.elementAt(k)).update()) {
                    ++k;
                } else {
                    super.fightValue.removeElementAt(k);
                    super.intsDamageData = null;
                    super.intsDamageType = null;
                }
            }
        }
        if (super.addValue != null) {
            int k = 0;
            while (k < super.addValue.size()) {
                if (((FightValue) super.addValue.elementAt(k)).update()) {
                    ++k;
                } else {
                    super.addValue.removeElementAt(k);
                    super.intsCureData = null;
                }
            }
        }
        if (super.addValueMp != null) {
            int k = 0;
            while (k < super.addValueMp.size()) {
                if (((FightValue) super.addValueMp.elementAt(k)).update()) {
                    ++k;
                } else {
                    super.addValueMp.removeElementAt(k);
                    super.intsMpDate = null;
                }
            }
        }
    }

    public void updateRoleSkill() {
        final Enumeration enumSkill = super.vecNextSkill.elements();
        while (enumSkill.hasMoreElements()) {
            SkillObject skill = (SkillObject) enumSkill.nextElement();
            if (skill != null) {
                this.updateSkillFrame(skill);
                if (!this.isAttackedSkill(skill) || super.bytType != 2) {
                    continue;
                }
                final String encode = this.getRoleSpriteEncode(skill.bytSkillPicId);
                final SkillObject skillObject = skill;
                skillObject.byteDelay -= DCanvas.getInstance().costTime;
                if (skill.byteDelay < 0L) {
                    skill.byteDelay = 0L;
                }
                if (!this.checkFrameDone(skill)) {
                    continue;
                }
                this.removeSkill(this.getRoleSpriteEncode(skill.bytSkillPicId));
            }
        }
    }

    private void setRoleSpriteState() {
        switch (super.bytState) {
            case 0: {
                if (this.checkIsShowRidePet()) {
                    super.bytAnimState = 5;
                    break;
                }
                super.bytAnimState = 0;
                break;
            }
            case 1: {
                if (this.checkIsShowRidePet()) {
                    super.bytAnimState = 6;
                    break;
                }
                super.bytAnimState = 1;
                break;
            }
            case 2: {
                if (this.bytWeaponPart == 601) {
                    super.bytAnimState = 7;
                    break;
                }
                if (this.bytWeaponPart == 604) {
                    super.bytAnimState = 8;
                    break;
                }
                if (this.bytWeaponPart == 605 || this.bytWeaponPart == 603 || this.bytWeaponPart == 602) {
                    super.bytAnimState = this.getRandomKanAction();
                    break;
                }
                super.bytAnimState = this.getRandomKanAction();
                break;
            }
            case 3: {
                if (this.checkIsShowRidePet()) {
                    super.bytAnimState = 5;
                    break;
                }
                super.bytAnimState = 0;
                break;
            }
            case 4: {
                if (super.curSkillObj != null && super.curSkillObj.bytRoleAction >= 0 && super.curSkillObj.bytRoleAction < 12) {
                    super.bytAnimState = super.curSkillObj.bytRoleAction;
                    DebugFrame.getInstance().logIn("\u65bd\u653e\u6280\u80fd\uff0c\u8bbe\u7f6e\u52a8\u4f5cbytAnimState = " + super.bytAnimState);
                    break;
                }
                if (super.curSkillObj == null) {
                    DebugFrame.getInstance().logIn("Error!! \u5f53\u524d\u6280\u80fd\u4e3a\u7a7a! ");
                } else {
                    DebugFrame.getInstance().logIn("Tip !! \u8be5\u6280\u80fd\u672a\u6307\u5b9a\u5bf9\u5e94\u52a8\u4f5c! " + ImageManager.EncodespriteId(new StringBuffer(String.valueOf(super.intUserId)).toString(), new StringBuffer(String.valueOf(super.curSkillObj.bytSkillPicId)).toString()));
                }
                super.bytAnimState = 0;
                break;
            }
            case 5: {
                super.bytAnimState = 11;
                break;
            }
            case 6: {
                super.bytAnimState = 2;
                break;
            }
        }
    }

    public void setRoleAnimation() {
        this.setRoleAnimation(this.checkSyncAnimation());
    }

    public void setRoleAnimation(final boolean forceSet) {
        this.setRoleSpriteState();
        this.setAllRoleSpriteAnimation(forceSet);
    }

    private void setAllRoleSpriteAnimation(final boolean forceSet) {
        this.roleActionId = this.getCurrentLayer(super.bytAnimState, ORole.transDirection(super.bytDirection), ORole.transGender(super.bytIsMale));
        for (int partIndex = 0; partIndex < IRoleDefine.ROLE_LAYER_TABLE[this.roleActionId].length; ++partIndex) {
            final int partId = IRoleDefine.ROLE_LAYER_TABLE[this.roleActionId][partIndex];
            if (partId >= 0 && partId < 15) {
                final Hashtable htSprite = (Hashtable) this.s_htRoleParts.get(new Integer(partId));
                if (htSprite != null) {
                    final Enumeration enumSprite = htSprite.keys();
                    while (enumSprite.hasMoreElements()) {
                        String encode = (String) enumSprite.nextElement();
                        int loop = -1;
                        if (partId == 10 || partId == 11) {
                            loop = 1;
                        }
                        this.setRoleSpriteAnimation(partId, encode, loop, forceSet);
                    }
                }
            }
        }
    }

    public void setRoleSpriteAnimation(final int partId, final String encode, final int loop) {
        this.setRoleSpriteAnimation(partId, encode, loop, false);
    }

    public void setRoleSpriteAnimation(final int partId, final String encode, int loop, final boolean forceSetAnimation) {
        final Hashtable htSprite = (Hashtable) this.s_htRoleParts.get(new Integer(partId));
        if (htSprite == null) {
            DebugFrame.getInstance().logIn("Error!! \u4eba\u7269\u7ec4\u6210\u6ca1\u627e\u5230\u5bf9\u5e94sprite \u4eba\u7269Part Id = " + partId);
            return;
        }
        QSprite sprite = (QSprite) htSprite.get(encode);
        if (sprite == null) {
            DebugFrame.getInstance().logIn("Error!! \u8bbe\u7f6e\u4eba\u7269\u7ec4\u6210\u52a8\u753bAnimation \u6ca1\u627e\u5230\u5bf9\u5e94sprite");
            return;
        }
        int sprAnimation = -1;
        switch (partId) {
            case 0: {
                sprAnimation = this.getHead_Anim_Id(super.bytAnimState, ORole.transDirection(super.bytDirection));
                break;
            }
            case 1: {
                sprAnimation = this.getHead_Anim_Id(super.bytAnimState, ORole.transDirection(super.bytDirection));
                break;
            }
            case 13: {
                sprAnimation = this.getLeg_anim_id(super.bytAnimState, ORole.transDirection(super.bytDirection), ORole.transDirection(super.bytIsMale));
                break;
            }
            case -1: {
                sprAnimation = this.getHead_Anim_Id(super.bytAnimState, ORole.transDirection(super.bytDirection));
                break;
            }
            case 2: {
                sprAnimation = this.getEquip_anim_id(super.bytAnimState, ORole.transDirection(super.bytDirection), ORole.transGender(super.bytIsMale));
                break;
            }
            case 3: {
                sprAnimation = this.getLeg_anim_id(super.bytAnimState, ORole.transDirection(super.bytDirection), ORole.transGender(super.bytIsMale));
                break;
            }
            case 4: {
                sprAnimation = this.getWeapon_Anim_Id(super.bytAnimState, ORole.transDirection(super.bytDirection), this.bytWeaponPart);
                break;
            }
            case 5: {
                sprAnimation = this.getPet_Anim_Id(super.bytAnimState, ORole.transDirection(super.bytDirection));
                break;
            }
            case 6: {
                sprAnimation = this.getEqupEffect_Anim_Id(super.bytAnimState, ORole.transDirection(super.bytDirection));
                break;
            }
            case 7: {
                sprAnimation = this.getEqupEffectRound_Anim_Id(super.bytAnimState, ORole.transDirection(super.bytDirection));
                break;
            }
            case 8: {
                sprAnimation = this.getWeapon_Effect_Attack_Anim_Id(super.bytAnimState, ORole.transDirection(super.bytDirection), this.bytWeaponPart);
                break;
            }
            case 9: {
                sprAnimation = this.getWeapon_Effect_Round_Anim_Id(super.bytAnimState, ORole.transDirection(super.bytDirection), this.bytWeaponPart);
                break;
            }
            case 14: {
                sprAnimation = this.getShade_Anim_Id(this.shtPetId != -1);
                break;
            }
            case 12: {
                sprAnimation = this.getDeadSoulAnim_Id();
                break;
            }
            case 10:
            case 11: {
                SkillObject skill = (SkillObject) super.vecNextSkill.get(encode);
                if (skill != null) {
                    sprAnimation = this.getSkill_Anim_Id(super.bytAnimState, ORole.transDirection(super.bytDirection), skill.blnSkillMultiAnimation);
                    loop = skill.byteLoop;
                    break;
                }
                break;
            }
        }
        if ((sprAnimation != -1 && sprAnimation != sprite.getAnimation()) || (forceSetAnimation && sprAnimation != -1)) {
            sprite.setLoopOffset(loop);
            sprite.setAnimation(sprAnimation);
        }
    }

    public static byte getResPartByResPath(final String resDir) {
        byte rolePart = -1;
        if (resDir != null && !resDir.equals("")) {
            for (byte index = 0; index < Macro.STRING_SPRITE_ROLE_RES.length; ++index) {
                if (resDir.equals(Macro.STRING_SPRITE_ROLE_RES[index])) {
                    rolePart = index;
                }
            }
        }
        return rolePart;
    }

    public void recvSyncRole(final String resPath, final int[] resType) {
        final String resDir = ImageManager.parseResDirName(resPath);
        final byte rolePart = getResPartByResPath(resDir);
        if (this.htPlayerData != null && this.htPlayerData.containsKey(new Integer(rolePart))) {
            short[] roleResId = (short[]) this.htPlayerData.get(new Integer(rolePart));
            if (roleResId != null && roleResId.length == 2 && ((resType[1] == 1 && roleResId[0] == resType[2]) || (resType[1] == 2 && roleResId[1] == resType[2]))) {
                final String pngPath = "/" + Macro.STRING_SPRITE_ROLE_RES[rolePart] + "/" + roleResId[0] + ".png";
                final String anuPath = "/" + Macro.STRING_SPRITE_ROLE_RES[rolePart] + "/" + roleResId[1] + ".anu";
                final int poolType = this.getRoleResPoolType(rolePart);
                if (ImageManager.getData(poolType, anuPath) != null && ImageManager.getPng(poolType, pngPath) != null) {
                    this.addRoleResData(rolePart, roleResId[0], roleResId[1], true);
                    this.setRoleAnimation(true);
                    final String info = "\u4ece\u670d\u52a1\u5668\u83b7\u5f97\u6570\u636e\uff0c\u76f8\u5173\u89d2\u8272\u540c\u6b65\u52a8\u753b\u5e27\u5e8f\u5217!";
                    DebugFrame.getInstance().logIn(info);
                }
            }
        }
    }

    private void updateAllRoleSprite() {
        if (!super.blnIsUpdate) {
            return;
        }
        QSprite sprite = null;
        Hashtable htSprite = null;
        for (int partIndex = 0; partIndex < IRoleDefine.ROLE_LAYER_TABLE[this.roleActionId].length; ++partIndex) {
            final int partsId = IRoleDefine.ROLE_LAYER_TABLE[this.roleActionId][partIndex];
            if (this.getRolePartUpdate(partsId)) {
                if (partsId >= 0 && partsId < 15) {
                    htSprite = (Hashtable) this.s_htRoleParts.get(new Integer(partsId));
                    if (htSprite != null) {
                        final Enumeration enumSprite = htSprite.elements();
                        while (enumSprite.hasMoreElements()) {
                            sprite = (QSprite) enumSprite.nextElement();
                            if (sprite == null) {
                                continue;
                            }
                            if (sprite == null) {
                                continue;
                            }
                            sprite.update();
                        }
                    }
                }
            }
        }
    }

    public void drawName(final Graphics g, final byte _type) {
        if (super.bytDrawNameType != _type) {
            this.setDrawName(_type);
        }
        int _roleHpNameHeight = 8;
        if (this.checkIsShowRidePet()) {
            _roleHpNameHeight = 33;
        }
        if (!this.blnStall) {
            int _fcolor = 0;
            int _bcolor = 0;
            if (_type == 4) {
                if (!super.blnIsAttack) {
                    _fcolor = 16755968;
                    _bcolor = 13184772;
                } else {
                    _fcolor = 180465;
                    _bcolor = 682377;
                }
                GameUI.getInstance().drawHP(g, super.shtJudgeX + super.shtDrawNameX - Param.getInstance().CAMERAX, super.shtJudgeY + super.shtDrawNameY - Param.getInstance().CAMERAY - _roleHpNameHeight, super.intHP, super.intHPMax, _fcolor, _bcolor);
                if (super.strConsortia != null && !super.strConsortia.equals("")) {
                    StringManager.drawShadowWord(g, "<" + super.strConsortia + ">", super.shtJudgeX + super.shtDrawNameX - Param.getInstance().CAMERAX, super.shtJudgeY + super.shtDrawNameY - Param.getInstance().CAMERAY - _roleHpNameHeight - Macro.FONTHEIGHT, IDefines.FACTION_NAME_COLOUR[0], IDefines.FACTION_NAME_COLOUR[1], 33);
                }
            } else {
                if (super.strConsortia != null && !super.strConsortia.equals("")) {
                    StringManager.drawShadowWord(g, "<" + super.strConsortia + ">", super.shtJudgeX + super.shtDrawNameX - Param.getInstance().CAMERAX, super.shtJudgeY + super.shtDrawNameY - Param.getInstance().CAMERAY - _roleHpNameHeight - Macro.FONTHEIGHT, IDefines.FACTION_NAME_COLOUR[0], IDefines.FACTION_NAME_COLOUR[1], 33);
                }
                if (!super.blnIsAttack) {
                    if (super.blnCountry) {
                        if (this.blnTeam) {
                            _fcolor = 2397918;
                            _bcolor = 3158064;
                        } else {
                            _fcolor = 16777215;
                            _bcolor = 3158064;
                        }
                    } else {
                        _fcolor = 2809068;
                        _bcolor = 156560;
                    }
                    StringManager.drawShadowWord(g, String.valueOf(super.strDrawName) + super.strDebugInfo, super.shtJudgeX + super.shtDrawNameX - Param.getInstance().CAMERAX, super.shtJudgeY + super.shtDrawNameY - Param.getInstance().CAMERAY - _roleHpNameHeight, _fcolor, _bcolor, 33);
                } else {
                    StringManager.drawShadowWord(g, String.valueOf(super.strDrawName) + super.strDebugInfo, super.shtJudgeX + super.shtDrawNameX - Param.getInstance().CAMERAX + 1, super.shtJudgeY + super.shtDrawNameY - Param.getInstance().CAMERAY - _roleHpNameHeight, 13762595, 16429489, 33);
                }
            }
        } else if (_type % 2 == 0) {
            this.drawStallInfo(g, this.strStallInfo, 15326509);
        } else {
            this.drawStallInfo(g, MenuUI.getInstance().strShopName, 16777215);
        }
    }

    protected void drawStallInfo(final Graphics g, final String _str, final int _c) {
        int _roleHpNameHeight = 8;
        if (this.checkIsShowRidePet()) {
            _roleHpNameHeight = 33;
        }
        final int _drawx = super.shtJudgeX - Param.getInstance().CAMERAX + super.shtDrawNameX - Macro.font.stringWidth(_str) / 2;
        final int _drawy = super.shtJudgeY - Param.getInstance().CAMERAY - 16 - super.shtImgHeight - _roleHpNameHeight;
        LayoutStyle.getInstance().drawChat(g, _drawx, _drawy, _str, (byte) 3);
        if (super.strConsortia != null && !super.strConsortia.equals("")) {
            StringManager.drawShadowWord(g, "<" + super.strConsortia + ">", _drawx, _drawy - Macro.FONTHEIGHT - 1, IDefines.FACTION_NAME_COLOUR[0], IDefines.FACTION_NAME_COLOUR[1], 20);
        }
    }

    public void delRole() {
        super.delRole();
        if (super.bytState == 5) {
            ImageManager.getInstance().delDeadImg();
        } else if (super.bytState == 3) {
            if (this.bytCartoon == 3) {
                this.delLvUPImg();
            } else if (this.bytCartoon == 1) {
                this.delJumpImg();
            }
        }
        this.delPet();
        if (Macro.BLN_LEVEL_FUNCTION_EQUIP) {
            ResPoolLevelFunction.getInstance().LevelFunction_pop((byte) 1, this, null);
            ResPoolLevelFunction.getInstance().LevelFunction_update((byte) 1);
        }
        if (Macro.BLN_LEVEL_FUNCTION_WEAPON) {
            ResPoolLevelFunction.getInstance().LevelFunction_pop((byte) 2, this, null);
            ResPoolLevelFunction.getInstance().LevelFunction_update((byte) 2);
        }
        this.cleanMagic();
    }

    protected void setDrawName(final byte _type) {
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
                super.strDrawName = super.strConsortia;
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

    public void setPet(final int _id, final short _picid, final short _anuId, final short _type) {
        if (this.playerFollowPet != null) {
            this.delPet();
        }
        if (_type != 1) {
            (this.playerFollowPet = new PetObject()).setPetPic(_id, _picid, _anuId, super.intUserId, _type);
            this.playerFollowPet.newPet(super.bytTileX, super.bytTileY);
            GameUI.getInstance().addObject(this.playerFollowPet);
        }
    }

    public void delPet() {
        if (this.playerFollowPet != null) {
            GameUI.getInstance().delVObject(this.playerFollowPet);
            this.playerFollowPet = null;
        }
    }

    public void setStall(final String _str) {
        this.strStallInfo = _str;
        this.blnStall = true;
        if (Param.getInstance().sprStallInstance == null) {
            Param.getInstance().sprStallInstance = ImageManager.loadSpriteById(3, "sellshop", "sellshop", "sellshop", "ingame");
            Param.getInstance();
            Param.shtSprStallInstanceNum = 0;
        }
        (this.sprStallInstance = Param.getInstance().sprStallInstance).setAnimation(0);
        Param.getInstance();
        ++Param.shtSprStallInstanceNum;
    }

    public void delStall() {
        this.blnStall = false;
        this.strStallInfo = null;
        this.sprStallInstance = null;
        Param.getInstance();
        --Param.shtSprStallInstanceNum;
        Param.getInstance();
        if (Param.shtSprStallInstanceNum <= 0) {
            Param.getInstance().sprStallInstance = null;
        }
    }

    public void setSpeed(final byte _speedType) {
        super.bytSpeed = IRoleDefine.ROLE_SPEED[_speedType - 1];
        DebugFrame.getInstance().logIn("Role id/speed" + super.intUserId + "/" + super.bytSpeed);
    }

    public int getEquip_anim_id(final int action, final int direction, final int gender) {
        int animId = action * 5 + direction;
        if ((direction == 1 && gender == 2) || direction > 1) {
            ++animId;
        }
        return animId;
    }

    public int getLeg_anim_id(final int action, final int direction, final int gender) {
        int animId = action * 5 + direction;
        if (direction > 1) {
            ++animId;
        }
        return animId;
    }

    public int getHead_Anim_Id(final int action, final int direction) {
        return action * 4 + direction;
    }

    public int getPet_Anim_Id(final int action, final int direction) {
        if (action != 6 && action != 5) {
            DebugFrame.getInstance().logIn("Warning!! getPet_Anim_Id Error! action is not driving pet or pet action error");
            return -1;
        }
        return (action - 5) * 4 + direction;
    }

    public int getEqupEffect_Anim_Id(final int action, final int direction) {
        final int actionID = 0;
        return actionID;
    }

    public int getEqupEffectRound_Anim_Id(final int action, final int direction) {
        int actionID = 0;
        if (action == 6 || action == 5) {
            actionID = 1;
        }
        return actionID;
    }

    public int getSkill_Anim_Id(final int action, final int direction, final boolean withDir) {
        int actionID = 0;
        if (withDir) {
            actionID = direction;
        }
        return actionID;
    }

    public int getShade_Anim_Id(final boolean ridePet) {
        if (ridePet) {
            return 0;
        }
        return 0;
    }

    public int getWeapon_Anim_Id(final int action, final int direction, final int weapon) {
        boolean iserror = false;
        switch (weapon) {
            case 601: {
                if (action > 7) {
                    iserror = true;
                    break;
                }
                break;
            }
            case 604: {
                if (action > 8) {
                    iserror = true;
                    break;
                }
                break;
            }
            case 605: {
                if (action > 9) {
                    iserror = true;
                    break;
                }
                break;
            }
            case 603: {
                if (action > 9) {
                    iserror = true;
                    break;
                }
                break;
            }
            case 602: {
                if (action > 9) {
                    iserror = true;
                    break;
                }
                break;
            }
        }
        if (iserror) {
            DebugFrame.getInstance().logIn("getWeapon_Anim_Id error! action is not exist refer to this weapon...");
            DebugFrame.getInstance().logIn("Action = " + action + "weapon = " + weapon);
            return -1;
        }
        return action * 4 + direction;
    }

    public int getWeapon_Effect_Attack_Anim_Id(final int action, final int direction, final int weapon) {
        boolean iserror = false;
        int animId = -1;
        if (weapon == 601 && action != 7) {
            iserror = true;
        } else if (weapon == 604 && action != 8) {
            iserror = true;
        }
        if (iserror) {
            DebugFrame.getInstance().logIn("getWeapon_Effect_Attack_Anim_Id Error! Action is not match weapon and effect");
            DebugFrame.getInstance().logIn("Action = " + action + "weapon = " + weapon);
            return animId;
        }
        if (action == 3 || action == 4) {
            animId = (action - 3) * 4 + direction;
        } else {
            animId = direction;
        }
        return animId;
    }

    public int getWeapon_Effect_Round_Anim_Id(final int action, final int direction, final int weapon) {
        int actionID = -1;
        int framePerWeapon = 7;
        int[] weaponTypeIndex;
        int tmpWeaponTypeIndex;
        for (weaponTypeIndex = new int[]{601, 605, 602, 604, 603}, tmpWeaponTypeIndex = -1, tmpWeaponTypeIndex = 0; tmpWeaponTypeIndex < weaponTypeIndex.length && weapon != weaponTypeIndex[tmpWeaponTypeIndex]; ++tmpWeaponTypeIndex) {
        }
        if (tmpWeaponTypeIndex < weaponTypeIndex.length) {
            framePerWeapon = 16;
            actionID = tmpWeaponTypeIndex * framePerWeapon;
            if (action == 0) {
                actionID += direction;
            } else if (action == 1) {
                actionID += action * 4 + direction;
            } else if (action == 5) {
                actionID += 8 + direction;
            } else if (action == 6) {
                actionID += 12 + direction;
            }
        }
        return actionID;
    }

    public int getDeadSoulAnim_Id() {
        return 0;
    }

    private int getCurrentLayer(final int action, final int direction, final int gender) {
        return this.getEquip_anim_id(action, direction, gender);
    }

    protected boolean checkIsRidePet() {
        return super.blnSelectedRide;
    }

    protected boolean checkIsShowRidePet() {
        return this.checkIsRidePet() && !super.blnNeedConcealRidePet;
    }

    protected byte getRandomKanAction() {
        byte action = (byte) Common.getRandom(3, 5);
        DebugFrame.getInstance().logIn("Random Kan Action(3, 4):" + action);
        if (action < 3 || action > 4) {
            action = 3;
        }
        return action;
    }

    protected boolean checkIsHoldWeapon() {
        return this.shtWeaponId != -1 && this.shtWeaponAnuId != -1 && this.shtWeaponId != 0 && this.shtWeaponAnuId != 0;
    }

    protected boolean checkIsNeedPull() {
        return this.checkIsHoldWeapon() && super.bytState == 1 && !this.checkIsShowRidePet();
    }

    protected byte getSpriteFrameMax(final int partId, final String encodeId, final int loop) {
        byte frameMax = 0;
        final Hashtable sprite = (Hashtable) this.s_htRoleParts.get(new Integer(partId));
        QSprite sprSkill = null;
        if (sprite != null) {
            if (sprite.containsKey(encodeId)) {
                sprSkill = (QSprite) sprite.get(encodeId);
            }
            if (sprSkill != null && sprSkill.spriteId.equals(encodeId)) {
                if (sprSkill.isPlayDone()) {
                    frameMax = 0;
                } else if (sprSkill.getAnimation() != -1) {
                    if (loop == -1) {
                        frameMax = -1;
                    } else {
                        frameMax = (byte) (sprSkill.getFrameCount() * loop);
                    }
                } else {
                    frameMax = 0;
                }
            }
        }
        return frameMax;
    }

    protected boolean checkSpriteDone(final int partId, final String encodeId) {
        final Hashtable sprite = (Hashtable) this.s_htRoleParts.get(new Integer(partId));
        QSprite sprSkill = null;
        if (sprite != null) {
            if (sprite.containsKey(encodeId)) {
                sprSkill = (QSprite) sprite.get(encodeId);
            }
            return sprSkill != null && sprSkill.spriteId.equals(encodeId) && sprSkill.isPlayDone();
        }
        return true;
    }

    public boolean setSkillFrame(final SkillObject skillObj, final int loop) {
        if (skillObj != null) {
            skillObj.shtAnimationFrameIndex = 0;
            skillObj.shtAnimationFrameMax = this.getSpriteFrameMax(this.transLayer(skillObj.byteLayer), this.getRoleSpriteEncode(skillObj.bytSkillPicId), 1);
        } else {
            DebugFrame.getInstance().logIn("Error! \u8981\u8bbe\u7f6e\u5e27\u6570\u7684\u6280\u80fd\u4e0d\u5b58\u5728\uff01");
        }
        return false;
    }

    public void removeSkill(final String encodeId) {
        if (super.vecNextSkill != null) {
            SkillObject skill = (SkillObject) super.vecNextSkill.get(encodeId);
            int partId = -1;
            if (skill != null) {
                partId = this.transLayer(skill.byteLayer);
            }
            this.removeSkillSprite(partId, encodeId);
        }
    }

    public void removeSkillSprite(final int partId, final String encodeId) {
        if (super.vecNextSkill.containsKey(encodeId)) {
            SkillObject skillobj = (SkillObject) super.vecNextSkill.get(encodeId);
            super.vecNextSkill.remove(encodeId);
            if (Macro.BLN_LEVEL_FUNCTION_SKILL) {
                ResPoolLevelFunction.getInstance().LevelFunction_pop((byte) 0, this, new short[]{skillobj.bytSkillPicId, skillobj.bytSkillAnuId});
            }
        }
        if (this.s_htRoleParts.containsKey(new Integer(partId))) {
            final Hashtable htSprite = (Hashtable) this.s_htRoleParts.get(new Integer(partId));
            if (htSprite.containsKey(encodeId)) {
                QSprite sprite = (QSprite) htSprite.get(encodeId);
                sprite = null;
                htSprite.remove(encodeId);
            }
            if (htSprite.isEmpty()) {
                this.s_htRoleParts.remove(new Integer(partId));
            }
        }
    }

    protected void setSpriteLoop(final byte loopTimes, final int partId, final int sprId) {
        super.blnSetAnimStateLoop = true;
        super.bytAnimStateLoop = loopTimes;
        super.bytAnimStateLoopOnPartId = partId;
        super.bytAnimStateLoopOnSprId = sprId;
    }

    protected short checkSkillUseNormalAttack(final int oriNormalAttackEffect, final int skillEffect) {
        if (super.bytState == 4 && super.bytAnimState == 3) {
            return (short) skillEffect;
        }
        return (short) oriNormalAttackEffect;
    }

    public void setTileXY(final byte _x, final byte _y) {
        final int _temp_y = _y - super.bytTileY;
        super.bytTileX = _x;
        super.bytTileY = _y;
        super.shtJudgeX = this.getJudgeX();
        super.shtJudgeY = this.getJudgeY();
    }

    public short getPortraitID(final byte Faction, final byte sex) {
        short _Portrait = -1;
        if (Faction == 1 && sex == 1) {
            _Portrait = 0;
        } else if (Faction == 1 && sex == 2) {
            _Portrait = 1;
        } else if (Faction == 2 && sex == 1) {
            _Portrait = 2;
        } else if (Faction == 2 && sex == 2) {
            _Portrait = 3;
        }
        return _Portrait;
    }

    public ORPlayer colnePlayerBaseData() {
        final ORPlayer _orm = new ORPlayer();
        _orm.newPlayer(super.bytTileX, super.bytTileY);
        _orm.intUserId = -9999;
        _orm.strNickName = super.strNickName;
        _orm.shtLevel = super.shtLevel;
        _orm.bytIsMale = super.bytIsMale;
        _orm.bytCountry = super.bytCountry;
        _orm.blnCountry = true;
        _orm.strConsortia = super.strConsortia;
        _orm.bytOccupation = super.bytOccupation;
        _orm.blnSelectedRide = super.blnSelectedRide;
        this.addAllRolePartData(_orm);
        _orm.shtEquipRealLevel = this.shtEquipRealLevel;
        _orm.shtWeaponLevel = this.shtWeaponLevel;
        _orm.bytWeaponPart = this.bytWeaponPart;
        _orm.bytIntensify = this.bytIntensify;
        _orm.itsf = this.itsf;
        ORPlayer.itSelectPetId = ORPlayer.itSelectPetId;
        _orm.pushTask((byte) 0, _orm.bytDirection = 2);
        _orm.roleTaskAction(0);
        return _orm;
    }

    public void addAllRolePartData(final ORPlayer _orm) {
        _orm.addRoleRes(0, this.shtHeadId, this.shtHeadAnuId);
        _orm.addRoleRes(1, this.shtHairId, this.shtHairAnuId);
        _orm.addRoleRes(-1, this.shtHeadwearID, this.shtHeadwearAnuID);
        _orm.addRoleRes(2, this.shtEquipId, this.shtEquipAnuId);
        _orm.addRoleRes(3, this.shtLegId, this.shtLegAnuId);
        _orm.addRoleRes(4, this.shtWeaponId, this.shtWeaponAnuId);
        _orm.addRoleRes(5, this.shtPetId, this.shtPetAnuId);
        _orm.addRoleRes(6, this.shtEquipEffectId, this.shtEquipEffectAnuId);
        _orm.addRoleRes(7, this.shtEquipEffectRoundId, this.shtEquipEffectAnuRoundId);
        _orm.addRoleRes(8, this.shtWeaponAttackEffectID, this.shtWeaponAttackEffectAnuID);
        _orm.addRoleRes(9, this.shtWeaponRoundEffectID, this.shtWeaponRoundEffectAnuID);
        _orm.addRoleRes(10, (short) (-1), (short) (-1));
        _orm.addRoleRes(11, (short) (-1), (short) (-1));
        _orm.addRoleRes(12, this.shtRoleDeadId, this.shtRoleDeadAnuId);
        _orm.addRoleRes(13, this.shtTailId, this.shtTailAnuId);
        _orm.addRoleRes(14, this.shtShade, this.shtShadeAnu);
    }
}
