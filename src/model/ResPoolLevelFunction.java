// 
// Decompiled by Procyon v0.5.36
// 
package model;

import base.Param;
import means.QSprite;
import means.DebugFrame;
import base.Macro;
import java.util.Vector;

public class ResPoolLevelFunction {

    private static ResPoolLevelFunction resPoolLFInstance;
    private static Vector lfPoolSkill;
    private static Vector lfPoolEquip;
    private static Vector lfPoolWeapon;
    public static final byte LEVEL_FUNCTION_SKILL = 0;
    public static final byte LEVEL_FUNCTION_EQUIP = 1;
    public static final byte LEVEL_FUNCTION_WEAPON = 2;
    public static final byte LEVEL_FUNCTION_STATUS_UNBANDED = 0;
    public static final byte LEVEL_FUNCTION_STATUS_BANDED = 1;

    static {
        ResPoolLevelFunction.resPoolLFInstance = null;
    }

    private ResPoolLevelFunction() {
        this._init();
    }

    public static synchronized ResPoolLevelFunction getInstance() {
        if (ResPoolLevelFunction.resPoolLFInstance == null) {
            ResPoolLevelFunction.resPoolLFInstance = new ResPoolLevelFunction();
        }
        return ResPoolLevelFunction.resPoolLFInstance;
    }

    private void _init() {
        ResPoolLevelFunction.lfPoolSkill = new Vector(1);
        ResPoolLevelFunction.lfPoolEquip = new Vector(1);
        ResPoolLevelFunction.lfPoolWeapon = new Vector(1);
    }

    public synchronized boolean LevelFunction_append(final byte type, final ORole sendRole, final short[] res, final int priority) {
        boolean isSuccess = false;
        Vector vecBand = null;
        if (type == 1) {
            vecBand = ResPoolLevelFunction.lfPoolEquip;
        } else if (type == 2) {
            vecBand = ResPoolLevelFunction.lfPoolWeapon;
        } else if (type == 0) {
            vecBand = ResPoolLevelFunction.lfPoolSkill;
        }
        if (vecBand != null) {
            if (type != 0) {
                final int popIndex = this._LevelFunction_pop(type, sendRole, null);
                if (popIndex != -1) {
                    this.LevelFunction_sort(vecBand);
                }
            }
            if (vecBand.isEmpty()) {
                final LFObject lfObj = new LFObject(res);
                lfObj.LF_append(sendRole.intUserId, sendRole.bytType, priority);
                vecBand.addElement(lfObj);
                isSuccess = true;
            } else {
                int index = 0;
                while (index < vecBand.size()) {
                    LFObject lfObj2 = (LFObject) vecBand.elementAt(index);
                    final String resKey = lfObj2.getKeyEncode(res);
                    if (lfObj2.LF_KEY.equals(resKey)) {
                        lfObj2.LF_append(sendRole.intUserId, sendRole.bytType, priority);
                        this.LevelFunction_sort(vecBand);
                        isSuccess = true;
                        break;
                    }
                    if (index == vecBand.size() - 1) {
                        if (type == 0 && vecBand.size() <= Macro.LEVEL_FUNCTION_POOL_SIZE[type]) {
                            lfObj2 = new LFObject(res);
                            lfObj2.LF_append(sendRole.intUserId, sendRole.bytType, priority);
                            vecBand.addElement(lfObj2);
                            this.LevelFunction_sort(vecBand);
                            isSuccess = true;
                            break;
                        }
                        lfObj2 = new LFObject(res);
                        lfObj2.LF_append(sendRole.intUserId, sendRole.bytType, priority);
                        vecBand.addElement(lfObj2);
                        this.LevelFunction_sort(vecBand);
                        isSuccess = true;
                        break;
                    } else {
                        ++index;
                    }
                }
            }
            this._printLevelFunction_Info(type);
        }
        return isSuccess;
    }

    private void LevelFunction_sort(final Vector vecBand) {
        if (vecBand.isEmpty() || vecBand.size() == 1) {
            return;
        }
        LFObject lfObjTmp = null;
        LFObject lfObjTmp2 = null;
        for (int index = 1; index < vecBand.size(); ++index) {
            lfObjTmp = (LFObject) vecBand.elementAt(index);
            int subIndex = index - 1;
            while (subIndex >= 0) {
                lfObjTmp2 = (LFObject) vecBand.elementAt(subIndex);
                if (lfObjTmp.LF_BandParam <= lfObjTmp2.LF_BandParam) {
                    if (index != subIndex + 1) {
                        vecBand.removeElementAt(index);
                        vecBand.insertElementAt(lfObjTmp, subIndex + 1);
                        break;
                    }
                    break;
                } else if (subIndex == 0) {
                    if (lfObjTmp.LF_BandParam > lfObjTmp2.LF_BandParam) {
                        vecBand.removeElementAt(index);
                        vecBand.insertElementAt(lfObjTmp, subIndex);
                        break;
                    }
                    break;
                } else {
                    --subIndex;
                }
            }
        }
    }

    public synchronized int LevelFunction_pop(final byte type, final ORole sendRole, final short[] key) {
        Vector vecBand = null;
        if (type == 1) {
            vecBand = ResPoolLevelFunction.lfPoolEquip;
        } else if (type == 2) {
            vecBand = ResPoolLevelFunction.lfPoolWeapon;
        } else if (type == 0) {
            vecBand = ResPoolLevelFunction.lfPoolSkill;
        }
        final int popIndex = this._LevelFunction_pop(type, sendRole, key);
        if (vecBand != null && popIndex != -1) {
            this.LevelFunction_sort(vecBand);
        }
        return popIndex;
    }

    private int _LevelFunction_pop(final byte type, final ORole sendRole, final short[] key) {
        int isSuccessPop = -1;
        Vector vecBand = null;
        if (type == 1) {
            vecBand = ResPoolLevelFunction.lfPoolEquip;
        } else if (type == 2) {
            vecBand = ResPoolLevelFunction.lfPoolWeapon;
        } else if (type == 0) {
            vecBand = ResPoolLevelFunction.lfPoolSkill;
        }
        if (vecBand != null && sendRole != null) {
            for (int index = 0; index < vecBand.size(); ++index) {
                final LFObject lfObj = (LFObject) vecBand.elementAt(index);
                if (lfObj != null) {
                    if (type == 0 && key != null) {
                        final String resKey = lfObj.getKeyEncode(key);
                        if (resKey.equals(lfObj.LF_KEY)) {
                            final boolean isSuccess = lfObj.LF_pop(sendRole.intUserId, sendRole.bytType);
                            if (isSuccess) {
                                isSuccessPop = index;
                            }
                        }
                    } else {
                        final boolean isSuccess2 = lfObj.LF_pop(sendRole.intUserId, sendRole.bytType);
                        if (isSuccess2) {
                            isSuccessPop = index;
                        }
                    }
                    if (lfObj.LF_BandParam <= 0) {
                        vecBand.removeElementAt(index);
                        isSuccessPop = -1;
                    }
                }
            }
            this._printLevelFunction_Info(type);
        }
        return isSuccessPop;
    }

    public synchronized void LevelFunction_update(final byte type) {
        Vector vecBand = null;
        if (type == 1) {
            vecBand = ResPoolLevelFunction.lfPoolEquip;
        } else if (type == 2) {
            vecBand = ResPoolLevelFunction.lfPoolWeapon;
        }
        if (vecBand != null) {
            for (int index = 0; index < vecBand.size(); ++index) {
                final LFObject lfObj = (LFObject) vecBand.elementAt(index);
                if (!lfObj.LF_KEY.equals("")) {
                    short pngId = lfObj.LF_RES_ID[0];
                    short anuId = lfObj.LF_RES_ID[1];
                    for (int bandIndex = 0; bandIndex < lfObj.LF_BandRole.size(); ++bandIndex) {
                        final Vector bandRoleInfo = (Vector) lfObj.LF_BandRole.elementAt(bandIndex);
                        final int bandRoleId = ((Integer) bandRoleInfo.elementAt(0)).intValue();
                        final byte bandRoleType = ((Byte) bandRoleInfo.elementAt(1)).byteValue();
                        final ORPlayer actRole = (ORPlayer) this._getRoleById(bandRoleId, bandRoleType);
                        if (actRole != null) {
                            short[] resId = null;
                            if (type == 1) {
                                if (index >= Macro.LEVEL_FUNCTION_POOL_SIZE[type]) {
                                    resId = this._getDefault_LF_Equip(actRole.bytOccupation);
                                    pngId = resId[0];
                                    anuId = resId[1];
                                }
                                actRole.addRoleRes(2, pngId, anuId);
                                actRole.setRoleAnimation(true);
                            } else if (type == 2) {
                                if (index >= Macro.LEVEL_FUNCTION_POOL_SIZE[type]) {
                                    resId = this._getDefault_LF_Weapon(actRole.bytWeaponPart);
                                    pngId = resId[0];
                                    anuId = resId[1];
                                }
                                actRole.addRoleRes(4, pngId, anuId);
                                actRole.setRoleAnimation(true);
                            }
                        }
                    }
                }
            }
        }
        this._printLevelFunction_Info(type);
    }

    private int _getBindParam_Skill(final ORole sendRole, final ORole recRoleId, final byte skillType) {
        int bindParam = 0;
        final byte actRoleType = sendRole.bytType;
        final int actRoleID = sendRole.intUserId;
        byte recRoleType = -1;
        int recRoleID = -1;
        if (recRoleId != null) {
            recRoleType = recRoleId.bytType;
            recRoleID = recRoleId.intUserId;
        }
        if (skillType == 0) {
            if (actRoleType == 2 && actRoleID == ORPMe.ME.intUserId) {
                bindParam = 50001;
            } else if ((actRoleType == 2 && actRoleID != ORPMe.ME.intUserId) || actRoleType == 3) {
                if (recRoleType >= 0 && recRoleType == 2 && recRoleID == ORPMe.ME.intUserId) {
                    bindParam = 5001;
                } else {
                    bindParam = 1;
                }
            }
        } else if (skillType == 1) {
            if (recRoleType == 2 && recRoleID == ORPMe.ME.intUserId) {
                bindParam = 20001;
            } else if (actRoleType == 2 && actRoleID == ORPMe.ME.intUserId) {
                bindParam = 1001;
            } else {
                bindParam = 1;
            }
        }
        return bindParam;
    }

    private int _getBindParam_Equip(final ORole role, final boolean isDefaultEquip) {
        int param = 0;
        if (role.bytType == 2) {
            param = ((ORPlayer) role).shtEquipRealLevel * 10;
            ++param;
            if (role.intUserId == ORPMe.ME.intUserId) {
                param += 50001;
            } else if (isDefaultEquip) {
                param += 2001;
            }
        } else {
            final String info = "Error! \u83b7\u53d6\u88c5\u5907\u7ed1\u5b9a\u7cfb\u6570\u65f6\uff0c\u89d2\u8272\u7c7b\u578b\u9519\u8bef!";
            DebugFrame.getInstance().logIn(info);
        }
        return param;
    }

    private int _getBindParam_Weapon(final ORole role, final boolean isDefaultWeapon) {
        int param = 0;
        if (role.bytType == 2) {
            param = ((ORPlayer) role).shtWeaponRealLevel * 10;
            ++param;
            if (role.intUserId == ORPMe.ME.intUserId) {
                param += 50001;
            } else if (isDefaultWeapon) {
                param += 2001;
            }
        } else {
            final String info = "Error! \u83b7\u53d6\u88c5\u5907\u7ed1\u5b9a\u7cfb\u6570\u65f6\uff0c\u89d2\u8272\u7c7b\u578b\u9519\u8bef!";
            DebugFrame.getInstance().logIn(info);
        }
        return param;
    }

    public void bindEquip(final ORPlayer actRole, final short png, final short anu) {
        final short[] DefaultesId = this._getDefault_LF_Equip(actRole.bytOccupation);
        actRole.addRoleRes(2, DefaultesId[0], DefaultesId[1], true);
        final int pngBindParam = this._getBindParam_Equip(actRole, false);
        final short[] res = {png, anu};
        this.LevelFunction_append((byte) 1, actRole, res, pngBindParam);
    }

    public void bindWeapon(final ORPlayer actRole, final short png, final short anu) {
        final short[] DefaultesId = this._getDefault_LF_Weapon(actRole.bytWeaponPart);
        actRole.addRoleRes(4, DefaultesId[0], DefaultesId[1], true);
        final int pngBindParam = this._getBindParam_Weapon(actRole, false);
        final short[] res = {png, anu};
        this.LevelFunction_append((byte) 2, actRole, res, pngBindParam);
    }

    public QSprite bindSkill(final ORole actRole, final ORole interActRole, final SkillObject skillObj) {
        QSprite bindSuccess = null;
        if (actRole.bytState != -1) {
            if (skillObj == null || skillObj.bytSkillPicId <= 0 || skillObj.bytSkillAnuId <= 0) {
                return bindSuccess;
            }
            final byte skillType = skillObj.byteActive;
            final int skillBindParam = this._getBindParam_Skill(actRole, interActRole, skillType);
            final short[] res = {skillObj.bytSkillPicId, skillObj.bytSkillAnuId};
            final boolean isAppendPngSuccess = this.LevelFunction_append((byte) 0, actRole, res, skillBindParam);
            if (isAppendPngSuccess) {
                bindSuccess = actRole.setSkill(skillObj);
            } else if (actRole.bytType == 2 && skillType == 0) {
                actRole.pushTask((byte) 2, actRole.bytDirection);
            }
        }
        return bindSuccess;
    }

    private ORole _getRoleById(final int bandRoleId, final byte bandRoleType) {
        ORole actRole = null;
        if (bandRoleType == 2 && Param.getInstance().htRolePlayer != null) {
            actRole = (ORole) Param.getInstance().htRolePlayer.get(new Integer(bandRoleId));
        } else if (bandRoleType == 3 && Param.getInstance().htRoleMonster != null) {
            actRole = (ORole) Param.getInstance().htRoleMonster.get(new Integer(bandRoleId));
        } else if (bandRoleType == 4 && Param.getInstance().htRolePet != null) {
            actRole = (ORole) Param.getInstance().htRolePet.get(new Integer(bandRoleId));
        }
        return actRole;
    }

    public short[] _getDefault_LF_Equip(byte roleOcc) {
        if (roleOcc < 0 || roleOcc > 20) {
            final String Info = "Error! \u83b7\u53d6\u9ed8\u8ba4\u88c5\u5907\u65f6\uff0c\u73a9\u5bb6\u7684\u804c\u4e1a\u7c7b\u578b\u9519\u8bef\uff01";
            DebugFrame.getInstance().logIn(Info);
            roleOcc = 1;
        }
        roleOcc = ((roleOcc > 4) ? ((byte) ((roleOcc - 1) / 4)) : roleOcc);
        --roleOcc;
        return Macro.GAME_UI_LEVEL_DEFAULT_EQUIP[roleOcc];
    }

    public short[] _getDefault_LF_Weapon(final short weaponType) {
        byte type = 0;
        if (weaponType >= 601 && weaponType <= 605) {
            type = (byte) (weaponType - 601);
            return Macro.GAME_UI_LEVEL_DEFAULT_WEAPON_RES[type];
        }
        return new short[]{-1, -1};
    }

    public void cleanAllLevelFunctionData() {
        ResPoolLevelFunction.lfPoolSkill.removeAllElements();
        ResPoolLevelFunction.lfPoolEquip.removeAllElements();
        ResPoolLevelFunction.lfPoolWeapon.removeAllElements();
    }

    private void _printLevelFunction_Info(final byte type) {
        if (Macro.BLN_LEVEL_FUNCTION_DEBUG_INFO) {
            Vector vecBand = null;
            String info = "";
            if (type == 1) {
                vecBand = ResPoolLevelFunction.lfPoolEquip;
                info = "*************Equp*******************";
            } else if (type == 2) {
                vecBand = ResPoolLevelFunction.lfPoolWeapon;
                info = "*************Weapon*******************";
            } else if (type == 0) {
                vecBand = ResPoolLevelFunction.lfPoolSkill;
                info = "*************Skill*******************";
            }
            DebugFrame.getInstance().logIn(info);
            for (int index = 0; index < vecBand.size(); ++index) {
                final LFObject lfObj = (LFObject) vecBand.elementAt(index);
                if (!lfObj.LF_KEY.equals("")) {
                    info = "Index: " + index + "Key:" + lfObj.LF_KEY + "/Param:" + lfObj.LF_BandParam;
                    DebugFrame.getInstance().logIn(info);
                    for (int bandIndex = 0; bandIndex < lfObj.LF_BandRole.size(); ++bandIndex) {
                        final Vector bandRoleInfo = (Vector) lfObj.LF_BandRole.elementAt(bandIndex);
                        final int bandRoleId = ((Integer) bandRoleInfo.elementAt(0)).intValue();
                        final byte bandRoleType = ((Byte) bandRoleInfo.elementAt(1)).byteValue();
                        final int bandRolePriority = ((Integer) bandRoleInfo.elementAt(2)).intValue();
                        final ORole atc = this._getRoleById(bandRoleId, bandRoleType);
                        if (atc != null) {
                            if (type == 1) {
                                info = "RoleName:" + ((ORPlayer) atc).strNickName;
                            } else if (type == 2) {
                                info = "RoleName:" + ((ORPlayer) atc).strNickName;
                            } else if (type == 0) {
                                if (bandRoleType == 2) {
                                    info = "RoleName:" + atc.strNickName;
                                } else if (bandRoleType == 3) {
                                    info = "RoleName:" + atc.strDrawName;
                                }
                            }
                        }
                        info = String.valueOf(info) + "Role Id:" + bandRoleId + "Type:" + bandRoleType + "Priority:" + bandRolePriority;
                        DebugFrame.getInstance().logIn(info);
                    }
                }
            }
        }
    }

    private String _getLevelFunctionRoleName(final Vector LF_BandRole, final int excepId) {
        String roleAll = "";
        for (int bandIndex = 0; bandIndex < LF_BandRole.size(); ++bandIndex) {
            final Vector bandRoleInfo = (Vector) LF_BandRole.elementAt(bandIndex);
            final int bandRoleId = ((Integer) bandRoleInfo.elementAt(0)).intValue();
            final byte bandRoleType =  ((Byte) bandRoleInfo.elementAt(1)).byteValue();
            final int bandRolePriority =  ((Integer) bandRoleInfo.elementAt(2)).intValue();
            ORPlayer atc = null;
            if (bandRoleType == 2 && Param.getInstance().htRolePlayer != null) {
                atc = (ORPlayer) Param.getInstance().htRolePlayer.get(new Integer(bandRoleId));
            }
            if (atc != null && excepId != bandRoleId) {
                roleAll = String.valueOf(roleAll) + "/" + atc.strNickName;
            }
        }
        return roleAll;
    }

    class LFObject {

        String LF_KEY;
        short[] LF_RES_ID;
        int LF_BandParam;
        Vector LF_BandRole;
        public static final byte LF_ROLE_ID = 0;
        public static final byte LF_ROLE_TYPE = 1;
        public static final byte LF_ROLE_PARAM = 2;
        public static final byte LF_ROLE_STATUS = 3;
        public static final byte LF_ROLE_COUNT = 4;

        public LFObject(final short[] LF_RES) {
            this.LF_RES_ID = LF_RES;
            this.LF_BandParam = 0;
            this.LF_BandRole = new Vector(1);
            this.LF_KEY = this.getKeyEncode(LF_RES);
        }

        public void LF_append(final int roleId, final byte roleType, final int priority) {
            if (this.LF_BandRole != null && this.LF_KEY != null && !this.LF_KEY.equals("")) {
                if (this.LF_BandRole.isEmpty()) {
                    final Vector bandRoleInfoTmp = new Vector(4);
                    bandRoleInfoTmp.addElement(new Integer(roleId));
                    bandRoleInfoTmp.addElement(new Byte(roleType));
                    bandRoleInfoTmp.addElement(new Integer(priority));
                    bandRoleInfoTmp.addElement(new Byte((byte) 0));
                    this.LF_BandRole.addElement(bandRoleInfoTmp);
                    this.LF_BandParam += priority;
                } else {
                    for (int index = 0; index < this.LF_BandRole.size(); ++index) {
                        final Vector bandRoleInfo = (Vector) this.LF_BandRole.elementAt(index);
                        final int bandRoleId =  ((Integer) bandRoleInfo.elementAt(0)).intValue();
                        final int bandRoleType = ((Byte) bandRoleInfo.elementAt(1)).byteValue();
                        final int bandRolePriority = ((Integer) bandRoleInfo.elementAt(2)).intValue();
                        if (bandRoleId == roleId && bandRoleType == roleType) {
                            this.LF_BandParam -= bandRolePriority;
                            this.LF_BandParam += priority;
                            break;
                        }
                        if (index == this.LF_BandRole.size() - 1) {
                            final Vector bandRoleInfoTmp2 = new Vector(4);
                            bandRoleInfoTmp2.addElement(new Integer(roleId));
                            bandRoleInfoTmp2.addElement(new Byte(roleType));
                            bandRoleInfoTmp2.addElement(new Integer(priority));
                            bandRoleInfoTmp2.addElement(new Byte((byte) 0));
                            this.LF_BandRole.addElement(bandRoleInfoTmp2);
                            this.LF_BandParam += priority;
                            break;
                        }
                    }
                }
            }
        }

        public boolean LF_pop(final int roleId, final byte roleType) {
            boolean isSuccess = false;
            if (this.LF_BandRole != null && this.LF_KEY != null && !this.LF_KEY.equals("")) {
                for (int index = 0; index < this.LF_BandRole.size(); ++index) {
                    final Vector bandRoleInfo = (Vector) this.LF_BandRole.elementAt(index);
                    int bandRoleId =  Integer.parseInt(bandRoleInfo.elementAt(0).toString());
                    final int bandRoleType = ((Byte) bandRoleInfo.elementAt(1)).byteValue();
                    final int bandRolePriority = ((Integer) bandRoleInfo.elementAt(2)).intValue();
                    if (bandRoleId == roleId && bandRoleType == roleType) {
                        this.LF_BandRole.removeElementAt(index);
                        this.LF_BandParam -= bandRolePriority;
                        isSuccess = true;
                        break;
                    }
                }
            } else {
                final String info = "Error! \u8d44\u6e90\u6c60\u5c1a\u672a\u521d\u59cb\u5316!";
                DebugFrame.getInstance().logIn(info);
            }
            return isSuccess;
        }

        public String getKeyEncode(final short[] res) {
            if (res[0] <= 0) {
                res[0] = 0;
            }
            if (res[1] <= 0) {
                res[1] = 0;
            }
            return new String(String.valueOf(res[0]) + "/" + res[1]);
        }
    }
}
