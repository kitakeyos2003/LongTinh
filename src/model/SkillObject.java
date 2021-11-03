// 
// Decompiled by Procyon v0.5.36
// 
package model;

public class SkillObject {

    public int intId;
    public String strName;
    public int intCDTimeMax;
    public int intCDTime;
    public short shtUseTime;
    public byte bytAtkDistance;
    public byte bytSelect;
    public byte bytUserType;
    public byte bytSkillType;
    public short shtHP;
    public short shtMp;
    public byte bytMpType;
    public String strInfo;
    public byte bytKey;
    public short shtIcon;
    public boolean blnPassivity;
    public short shtCDType;
    public short[] shtWeapon;
    public short bytSkillPicId;
    public short bytSkillAnuId;
    public String strPropName;
    public byte bytLv;
    public byte bytRank;
    public byte bytType;
    public short bytNeedPoints;
    public int intNeedMoney;
    public byte bytCorrOcc;
    public String strNextInfo;
    public byte bytNextNeedPoints;
    public byte bytLiftskillType;
    public String strLiftskillName;
    public String strLeveName;
    public int intMastery;
    public short shortLifeSkillNum;
    public int intskillID;
    public short shortIconID;
    public String strGoodsName;
    public byte byteGoodsNum;
    public byte byteLifeSkillNum;
    public byte byteLayer;
    public byte bytDisplayPosXOffType;
    public byte bytDisplayPosYOffType;
    public byte byteMusicId;
    public byte byteLoop;
    public long byteDelay;
    public short shtAnimationFrameIndex;
    public short shtAnimationFrameMax;
    public byte byteActive;
    public short shtMagicTime;
    public short shtMagicTimeMax;
    public short shtSendSkillTime;
    public byte byteSkillType;
    public byte bytRoleAction;
    public boolean blnSkillMultiAnimation;

    public static void resetMagicContinuous(final SkillObject skillObj) {
        if (skillObj != null) {
            if (skillObj.shtUseTime > 0) {
                skillObj.byteLoop = -1;
            } else {
                skillObj.byteLoop = 1;
            }
        }
    }

    public SkillObject() {
        this.bytKey = -1;
        this.byteLayer = 1;
        this.byteLoop = 1;
        this.byteDelay = 0L;
        this.shtAnimationFrameIndex = 0;
        this.shtAnimationFrameMax = 0;
        this.byteActive = 0;
        this.byteSkillType = 0;
        this.blnSkillMultiAnimation = false;
    }

    public SkillObject(final short pic, final short anu, final byte active) {
        this.bytKey = -1;
        this.byteLayer = 1;
        this.byteLoop = 1;
        this.byteDelay = 0L;
        this.shtAnimationFrameIndex = 0;
        this.shtAnimationFrameMax = 0;
        this.byteActive = 0;
        this.byteSkillType = 0;
        this.blnSkillMultiAnimation = false;
        this.bytSkillPicId = pic;
        this.bytSkillAnuId = anu;
        this.byteActive = active;
    }
}
