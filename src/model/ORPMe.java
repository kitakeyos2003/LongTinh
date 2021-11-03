// 
// Decompiled by Procyon v0.5.36
// 
package model;

import means.StringManager;
import common.IDefines;
import javax.microedition.lcdui.Image;
import common.DrawBase;
import common.Common;
import face.HandleUI;
import java.util.Enumeration;
import network.NetParse;
import means.QSprite;
import means.DebugFrame;
import means.Rms;
import means.AStar;
import face.SystemMenuUI;
import network.NetSend;
import base.DCanvas;
import base.Macro;
import base.GameControl;
import face.MenuUI;
import base.Param;
import face.GameUI;
import javax.microedition.lcdui.Graphics;
import java.util.Vector;

public class ORPMe extends ORPlayer {

    public static ORPMe ME;
    public short skillPoints;
    public int intGoodsCDTime;
    public int intSkillCDTime;
    public short shtMagicTimeMax;
    public short shtMagicTime;
    public int intNextSkillNum;
    private int intSkillNum;
    public short shtSelectNum;
    private short shtFirstSelectNum;
    public byte bytSelectType;
    private byte bytFirstSelectPlayer;
    private byte bytFirstSelectMonster;
    private byte bytFirstSelectBox;
    private boolean blnfindSign;
    public byte[] bytSelectPos;
    public byte bytSelectPosArea;
    public int intSelectSkillNum;
    public short shtEvent;
    public byte bytAtkDistance;
    public short shtImmobilityTimeMax;
    public String strAttrick;
    public short shtImmobilityTime;
    public short shtSendAttackTime;
    public boolean blnAttackFlag;
    private boolean blnAttackTimeFlag;
    private byte[] bytsAttackStep;
    private Vector vMoveTask;
    private short shtSendMoveDTime;
    private byte bytMoveTileX;
    private byte bytMoveTileY;
    public boolean blnCanFight;
    public boolean blnCanMove;
    public boolean blnCanSkill;
    public boolean blnCanSlow;
    private int intSkillTime;
    public int EXP;
    public int NextEXP;
    public int ShowEXP;
    public int ShowNextEXP;
    public static int Gold;
    public short Strength;
    public short Agility;
    public short Stamina;
    public short Intellect;
    public short Energy;
    public short Luck;
    public int AttackMax;
    public int AttackMin;
    public int MagicAttrack;
    public int ShadowDamage;
    public int HolinessDamage;
    public int FireDamage;
    public int WaterDamage;
    public int EarthDamage;
    public String ArmLuckOdds;
    public String MagicLuckOdds;
    public String ArmTargetOdds;
    public String MagicTargetOdds;
    public int Recovery;
    public int ShadowRecovery;
    public int HolinessRecovery;
    public int FireRecovery;
    public int WaterRecovery;
    public int EarthRecovery;
    public String JookOdds;
    public int SKILLENEMY;
    public int SKILLNUM;
    public int PKWIN;
    public int PKNUM;
    public byte bytOfficialRank;
    public short shtCNowNum;
    public short shtCAllNum;
    public String ConsortiaName;
    public byte ConsortiaLevel;
    public int[] UpgradeConsortia;
    public short[] UpgradeMember;
    public byte bytTeamRank;
    public byte bytMeTeamID;
    public byte bytApprentice;
    public int intPetId;
    private short shtPetPUBoxTime;
    public byte bytWeaponMusic;
    public boolean blnAstarMove;
    public byte bytAstarMoveEndX;
    public byte bytAstarMoveEndY;
    public boolean pointFrame;
    public boolean blnKeepDirMove;
    public int intKeepMoveKeyCode;
    public long timeKeepDirMoveMax;
    private final String STRING_VIEW;
    private final String STRING_PM;
    private final String STRING_BUSINESS;
    private final String STRING_DUEL;
    private final String STRING_OPTION_BLACKLIST;
    private final String STRING_ADD_FRIEND;
    private final String STRING_INVITE_TEAM;
    private final String STRING_DISTANCE_FAR;
    private final String STRING_NO_ATTACK;
    private final String STRING_NO_MAGIC;
    private final String STRING_MAGIC;
    private final String STRING_SKILL_CD;
    private final String STRING_NO_POWER;
    private final String STRING_NO_MP;
    private final String STRING_NO_GAS;
    private final String STRING_NO_WEAPON;
    private final String STRING_NO_HP;
    private final String STRING_CD;
    private final String STRING_IMPEDE_TARGET;
    private final String STRING_SELECT_TARGET;
    private final String STRING_IS_RIDING;
    private final String STRING_NO_WEAPON_ATTACK;
    public byte bytOccupationBefore;
    public byte bytOccupationLater;
    public static final byte bytOccupationIndex = 7;

    public ORPMe() {
        this.intNextSkillNum = -1;
        this.intSelectSkillNum = -1;
        this.ArmLuckOdds = "0.00%";
        this.MagicLuckOdds = "0.00%";
        this.ArmTargetOdds = "0.00%";
        this.MagicTargetOdds = "0.00%";
        this.JookOdds = "0.00%";
        this.shtCNowNum = 1;
        this.shtCAllNum = 200;
        this.bytApprentice = -1;
        this.timeKeepDirMoveMax = 1000L;
        this.STRING_VIEW = "\u67e5\u3000\u3000\u770b";
        this.STRING_PM = "\u79c1\u3000\u3000\u804a";
        this.STRING_BUSINESS = "\u4ea4\u3000\u3000\u6613";
        this.STRING_DUEL = "\u51b3\u3000\u3000\u6597";
        this.STRING_OPTION_BLACKLIST = "\u52a0\u9ed1\u540d\u5355";
        this.STRING_ADD_FRIEND = "\u52a0\u4e3a\u597d\u53cb";
        this.STRING_INVITE_TEAM = "\u961f\u4f0d\u9080\u8bf7";
        this.STRING_DISTANCE_FAR = "\u8ddd\u79bb\u592a\u8fdc";
        this.STRING_NO_ATTACK = "\u65e0\u6cd5\u653b\u51fb";
        this.STRING_NO_MAGIC = "\u4e0d\u80fd\u65bd\u6cd5";
        this.STRING_MAGIC = "\u6b63\u5728\u65bd\u6cd5";
        this.STRING_SKILL_CD = "\u51b7\u5374\u4e2d";
        this.STRING_NO_POWER = "\u529b\u503c\u4e0d\u8db3";
        this.STRING_NO_MP = "\u9b54\u6cd5\u4e0d\u8db3";
        this.STRING_NO_GAS = "\u6c14\u503c\u4e0d\u8db3";
        this.STRING_NO_WEAPON = "\u6b66\u5668\u4e0d\u7b26";
        this.STRING_NO_HP = "\u751f\u547d\u4e0d\u8db3";
        this.STRING_CD = "\u79d2\u51b7\u5374";
        this.STRING_IMPEDE_TARGET = "\u8d85\u51fa\u89c6\u91ce";
        this.STRING_SELECT_TARGET = "\u76ee\u6807\u9519\u8bef";
        this.STRING_IS_RIDING = "\u6253\u4e0d\u5230";
        this.STRING_NO_WEAPON_ATTACK = "\u5fc5\u987b\u6301\u6709\u6b66\u5668\u624d\u80fd\u666e\u901a\u653b\u51fb";
        this.bytOccupationBefore = -1;
        this.bytOccupationLater = -1;
        (ORPMe.ME = this).delSelsectRole();
        super.blnCountry = true;
        this.intSkillNum = -1;
        this.shtMagicTime = 0;
        this.bytWeaponMusic = -1;
        this.vMoveTask = new Vector(5, 1);
        this.blnCanMove = true;
        this.blnCanSkill = true;
        this.blnCanFight = true;
        super.blnPlayerResOK = true;
        this.bytOccupationBefore = -1;
        this.bytOccupationLater = -1;
    }

    public void setRoleX(final int x) {
        super.intPosX = x;
    }

    public void setRoleY(final int y) {
        super.intPosY = y;
    }

    public void drawSprite(final Graphics g) {
    }

    public void doLeftKey() {
        if ((GameUI.getInstance().bytsButtonType[1] == 7 || GameUI.getInstance().bytsButtonType[1] == 17 || GameUI.getInstance().bytsButtonType[1] == 14 || GameUI.getInstance().bytsButtonType[1] == 5 || GameUI.getInstance().bytsButtonType[1] == 1) && (ORPMe.ME.blnAstarMove || ORPMe.ME.blnKeepDirMove)) {
            ORPMe.ME.delTask((byte) 1);
            ORPMe.ME.pushTask((byte) 0, ORPMe.ME.bytDirection);
            ORPMe.ME.blnKeepDirMove = false;
            ORPMe.ME.intKeepMoveKeyCode = -1;
        }
        switch (GameUI.getInstance().bytsButtonType[1]) {
            case 4: {
                if (Param.getInstance().bytSelectType == 2) {
                    String[] _strgameoption = null;
                    final String[] _temp = {"\u67e5\u3000\u3000\u770b", "\u79c1\u3000\u3000\u804a", "\u52a0\u4e3a\u597d\u53cb", "\u4ea4\u3000\u3000\u6613", "\u51b3\u3000\u3000\u6597", "\u52a0\u9ed1\u540d\u5355"};
                    if (this.bytTeamRank != 1) {
                        _strgameoption = new String[_temp.length + 1];
                        System.arraycopy(_temp, 0, _strgameoption, 0, _temp.length);
                        _strgameoption[_temp.length] = "\u961f\u4f0d\u9080\u8bf7";
                    } else {
                        _strgameoption = _temp;
                    }
                    final short _lv = ((ORPlayer) Param.getInstance().oSelectRole).shtLevel;
                    if (_lv - ORPMe.ME.shtLevel >= 20 && _lv >= 30) {
                        final String[] _temp2 = _strgameoption;
                        _strgameoption = new String[_temp2.length + 1];
                        System.arraycopy(_temp2, 0, _strgameoption, 0, _temp2.length);
                        _strgameoption[_temp2.length] = "\u62dc\u3000\u3000\u5e08";
                    } else if (ORPMe.ME.shtLevel >= 30 && ORPMe.ME.shtLevel - _lv >= 20) {
                        final String[] _temp2 = _strgameoption;
                        _strgameoption = new String[_temp2.length + 1];
                        System.arraycopy(_temp2, 0, _strgameoption, 0, _temp2.length);
                        _strgameoption[_temp2.length] = "\u6536\u3000\u3000\u5f92";
                    }
                    if (ORPMe.ME.bytOfficialRank >= 2) {
                        final String[] _temp2 = _strgameoption;
                        _strgameoption = new String[_temp2.length + 1];
                        System.arraycopy(_temp2, 0, _strgameoption, 0, _temp2.length);
                        final String[] array = _strgameoption;
                        final int length = _temp2.length;
                        MenuUI.getInstance().getClass();
                        array[length] = "\u9080\u8bf7\u5165\u5e2e";
                    }
                    if (((ORPlayer) Param.getInstance().oSelectRole).blnStall) {
                        final String[] _temp2 = _strgameoption;
                        _strgameoption = new String[_temp2.length + 1];
                        System.arraycopy(_temp2, 0, _strgameoption, 1, _temp2.length);
                        _strgameoption[0] = "\u6253\u5f00\u5546\u5e97";
                    }
                    GameUI.getInstance().setTwoMenu((byte) (-1), _strgameoption, (byte) 5);
                    break;
                }
                if (Param.getInstance().bytSelectType != 5) {
                    break;
                }
                if (this.checkArea(Param.getInstance().bSelectTask, (byte) 4)) {
                    GameControl.getInstance().CreateState((byte) 3);
                    MenuUI.getInstance().setNpcTalk(Param.getInstance().bSelectTask.strNickName, Param.getInstance().bSelectTask.strInfo);
                    MenuUI.getInstance().setNpcOption(new String[]{Param.getInstance().bSelectTask.strOption}, "", (byte) 0);
                    Param.getInstance().blnIsOk = true;
                    break;
                }
                Macro.blnDistanceFar = true;
                ORPMe.ME.bytSelectPos = new byte[]{Param.getInstance().bSelectTask.bytTileX, Param.getInstance().bSelectTask.bytTileY};
                ORPMe.ME.bytSelectPosArea = 4;
                break;
            }
            case 7:
            case 17: {
                if (Param.getInstance().bytSelectType != 1) {
                    break;
                }
                if (Param.getInstance().oSelectNpc == null || !this.checkArea(Param.getInstance().oSelectNpc, (byte) 4)) {
                    Macro.blnDistanceFar = true;
                    ORPMe.ME.bytSelectPos = new byte[]{Param.getInstance().oSelectNpc.bytTileX, Param.getInstance().oSelectNpc.bytTileY};
                    ORPMe.ME.bytSelectPosArea = 4;
                    break;
                }
                ORPMe.ME.delTask((byte) 1);
                ORPMe.ME.pushTask((byte) 0, ORPMe.ME.bytDirection);
                GameControl.getInstance().CreateState((byte) 3);
                final String _name = Param.getInstance().oSelectNpc.getNpcName();
                MenuUI.getInstance().setNpcTalk(_name, Param.getInstance().oSelectNpc.strTALK);
                if (!Param.getInstance().oSelectNpc.blnNpc) {
                    break;
                }
                GameUI.getInstance().intSendMenuId = 0;
                DCanvas.getInstance().setNetLoad(true);
                if (Macro.BLN_ACCERATE_NPC_TASK) {
                    NetSend.getInstance().sendNpcOptionFake(Param.getInstance().oSelectNpc.intUserId);
                    break;
                }
                NetSend.getInstance().sendNpcOption(Param.getInstance().oSelectNpc.intUserId);
                break;
            }
            case 14: {
                if (Param.getInstance().bytSelectType == 4) {
                    if (this.checkArea(Param.getInstance().bSelectBox, (byte) 4)) {
                        if (Param.getInstance().bSelectBox.bytBoxType == 1) {
                            Param.getInstance().bSelectBox.blnPickUp = true;
                            NetSend.getInstance().sendUnlockMonsterBox(Param.getInstance().bSelectBox.intUserId);
                        } else {
                            Param.getInstance().bSelectBox.blnPickUp = true;
                            NetSend.getInstance().sendUnlockWorldBox(Param.getInstance().bSelectBox.intUserId);
                        }
                        this.delSelsectRole();
                        break;
                    }
                    Macro.blnDistanceFar = true;
                    ORPMe.ME.bytSelectPos = new byte[]{Param.getInstance().bSelectBox.bytTileX, Param.getInstance().bSelectBox.bytTileY};
                    ORPMe.ME.bytSelectPosArea = 4;
                    break;
                } else {
                    if (Param.getInstance().bytSelectType != 5) {
                        break;
                    }
                    if (this.checkArea(Param.getInstance().bSelectTask, (byte) 4)) {
                        NetSend.getInstance().sendUnlockEvent2(Param.getInstance().bSelectTask.intUserId);
                        break;
                    }
                    Macro.blnDistanceFar = true;
                    ORPMe.ME.bytSelectPos = new byte[]{Param.getInstance().bSelectTask.bytTileX, Param.getInstance().bSelectTask.bytTileY};
                    ORPMe.ME.bytSelectPosArea = 4;
                    break;
                }
            }
            case 5: {
                if (!super.blnTaskOK) {
                    break;
                }
                if (!this.blnCanFight) {
                    GameUI.getInstance().addFrightInformation("\u65e0\u6cd5\u653b\u51fb");
                    break;
                }
                this.blnAttackFlag = true;
                if (this.doAttack()) {
                    this.sendMoveTask();
                    NetSend.getInstance().sendAttack(Param.getInstance().oSelectRole.bytType, Param.getInstance().oSelectRole.intUserId);
                    Macro.oldSelectType = Param.getInstance().oSelectRole.bytType;
                    Macro.oldSelectId = Param.getInstance().oSelectRole.intUserId;
                    this.shtSendAttackTime = 500;
                    this.shtImmobilityTime = this.shtImmobilityTimeMax;
                    GameUI.getInstance().setButton(0, 100, 2);
                    break;
                }
                Macro.blnAtc = false;
                break;
            }
            case 1: {
                GameUI.getInstance().setDialog((byte) 17);
                break;
            }
        }
        if (Macro.blnDistanceFar) {
            Macro.blnDistanceFar = false;
            if (ORPMe.ME.bytSelectPos != null) {
                Macro.blnDistanceFarAStar = true;
                GameUI.getInstance().setAStar(ORPMe.ME.bytSelectPos[0], ORPMe.ME.bytSelectPos[1]);
                ORPMe.ME.bytSelectPos = null;
            }
        }
    }

    public void doRightKey() {
        switch (GameUI.getInstance().bytsButtonType[2]) {
            case 3: {
                if (GameUI.getInstance().bytDrawType == 0 && !ORPMe.ME.blnStall) {
                    Param.getInstance().vOptionPlace = new Vector(1, 1);
                    GameUI.getInstance().hotKeyMenuSwitch = 1;
                    GameControl.getInstance().CreateState((byte) 4);
                    SystemMenuUI.getInstance().setState((byte) 0);
                    break;
                }
                break;
            }
            case 2: {
                if (GameUI.getInstance().bytDrawType != 0) {
                    GameUI.getInstance().bytDrawType = 0;
                    GameUI.getInstance().setButton();
                    break;
                }
                if (Param.getInstance().intSelectId >= 0) {
                    this.delSelsectRole();
                    break;
                }
                break;
            }
        }
    }

    public void doUseKey(final boolean _type, final byte num) {
        if (num >= 0) {
            if (_type) {
                if (Param.getInstance().intShortcutKeys[num][3] >= 0) {
                    this.useSkill(Param.getInstance().intShortcutKeys[num][3]);
                }
            } else if (Param.getInstance().intShortcutKeys[num][0] >= 0) {
                this.useGoods(num);
            }
        }
    }

    private void checkPlayer() {
        final byte _type = 0;
        final int _y1 = super.bytTileY;
        final int _x1 = super.bytTileX;
        if (this.findPlayer(_x1, _y1, false, false, (byte) 0, (byte) 0)) {
            this.blnfindSign = true;
            return;
        }
        if (this.findBox(_x1, _y1, false, false, (byte) 0, (byte) 0)) {
            this.blnfindSign = true;
            return;
        }
        if (this.findTask(_x1, _y1, false, false, (byte) 0, (byte) 0)) {
            this.blnfindSign = true;
            return;
        }
        if (this.findNpc(_y1, _x1, false, false, (byte) 0)) {
            this.blnfindSign = true;
            return;
        }
        if (this.findMonster(_x1, _y1, false, false, (byte) 0, (byte) 0)) {
            this.blnfindSign = true;
            return;
        }
        this.blnfindSign = false;
    }

    public void checkSelectFail() {
        if (Param.getInstance().intSelectId >= 0) {
            if (Param.getInstance().oSelectRole != null) {
                if (!GameUI.getInstance().checkPosInView(Param.getInstance().oSelectRole.bytTileX, Param.getInstance().oSelectRole.bytTileY, false)) {
                    this.delSelsectRole();
                }
            } else if (Param.getInstance().oSelectNpc != null) {
                if (!GameUI.getInstance().checkPosInView(Param.getInstance().oSelectNpc.bytTileX, Param.getInstance().oSelectNpc.bytTileY, false)) {
                    this.delSelsectRole();
                }
            } else if (Param.getInstance().bSelectBox != null && !GameUI.getInstance().checkPosInView(Param.getInstance().bSelectBox.bytTileX, Param.getInstance().bSelectBox.bytTileY, false)) {
                this.delSelsectRole();
            }
        }
    }

    protected void checkEvent() {
        final short _tempEvent = Map.getInstance().shtsMapEvent[super.bytTileY][super.bytTileX];
        if (_tempEvent > 0) {
            this.shtEvent = _tempEvent;
            if (this.shtEvent > 0) {
                if (this.shtEvent >= 101 && this.shtEvent < 201) {
                    this.shtSendMoveDTime = 0;
                    this.shtEvent -= 101;
                    this.pushTaskFirst((byte) 3, (byte) 1);
                } else if (this.shtEvent >= 201 && this.shtEvent < 10000) {
                    this.shtEvent -= 201;
                    GameUI.getInstance().setDialog((byte) 1);
                    if (this.blnAstarMove) {
                        ORPMe.ME.delTask((byte) 1);
                    }
                }
            }
        }
    }

    protected void setStand(final byte _direction) {
        super.setStand(_direction);
        if (!this.checkTask((byte) 1)) {
            this.blnAstarMove = false;
        }
        this.setRoleAnimation();
    }

    protected void setMove(final byte _direction) {
        this.shtSelectNum = 0;
        this.bytSelectType = 0;
        this.bytFirstSelectPlayer = 0;
        this.bytFirstSelectMonster = 0;
        this.bytFirstSelectBox = 0;
        this.shtFirstSelectNum = 0;
        super.bytDirection = _direction;
        super.blnIsRealMove = false;
        this.switchActionState((byte) 1);
        super.bytFrame = 0;
        super.bytBlockPixel = 16;
        if (super.bytSpeed != 0) {
            super.bytRoleMoveFrame = super.bytSpeed;
        }
        this.blnfindSign = false;
        if (super.bytDirection == 1) {
            if (Map.getInstance().checkMeIsPass(super.bytTileX, super.bytTileY - 1)) {
                this.changeTile(0, -1);
                super.blnIsRealMove = true;
            }
        } else if (super.bytDirection == 2) {
            if (Map.getInstance().checkMeIsPass(super.bytTileX, super.bytTileY + 1)) {
                this.changeTile(0, 1);
                super.blnIsRealMove = true;
            }
        } else if (super.bytDirection == 3) {
            if (Map.getInstance().checkMeIsPass(super.bytTileX - 1, super.bytTileY)) {
                this.changeTile(-1, 0);
                super.blnIsRealMove = true;
            }
        } else if (super.bytDirection == 4 && Map.getInstance().checkMeIsPass(super.bytTileX + 1, super.bytTileY)) {
            this.changeTile(1, 0);
            super.blnIsRealMove = true;
        }
        this.setRoleAnimation();
    }

    protected void doMove() {
        ++super.bytFrame;
        if (super.bytFrame == super.bytRoleMoveFrame) {
            if (super.blnIsRealMove) {
                this.bytMoveTileX = super.bytTileX;
                this.bytMoveTileY = super.bytTileY;
                this.vMoveTask.addElement(new Integer(super.bytDirection));
                if (Macro.blnDistanceFarAStar && this.checkArea(this.bytAstarMoveEndX, this.bytAstarMoveEndY, ORPMe.ME.bytSelectPosArea, (byte) 1)) {
                    Macro.blnDistanceFarAStar = false;
                    ORPMe.ME.blnKeepDirMove = false;
                    this.blnAstarMove = false;
                    ORPMe.ME.delTask((byte) 1);
                    ORPMe.ME.pushTask((byte) 0, ORPMe.ME.bytDirection);
                    if (ORPMe.ME.intSelectSkillNum >= 0) {
                        ORPMe.ME.useSkill(ORPMe.ME.intSelectSkillNum);
                        ORPMe.ME.intSelectSkillNum = -1;
                    } else {
                        ORPMe.ME.doLeftKey();
                    }
                }
            } else if (this.blnAstarMove && !this.checkArea(this.bytAstarMoveEndX, this.bytAstarMoveEndY, (byte) 4, (byte) 1)) {
                ORPMe.ME.delTask((byte) 1);
                final AStar as = new AStar(ORPMe.ME.bytTileX, ORPMe.ME.bytTileY, this.bytAstarMoveEndX, this.bytAstarMoveEndY, (byte) 0, (byte) 0);
                final byte[] _path = as.getPath();
                if (_path != null) {
                    for (int i = 0; i < _path.length; ++i) {
                        ORPMe.ME.pushTask((byte) 1, _path[i]);
                    }
                } else {
                    this.blnAstarMove = false;
                }
            }
            super.blnTaskOK = true;
            if (!Rms.blnAutoSelect) {
                this.checkPlayer();
            }
            this.checkEvent();
        } else if (super.bytFrame > super.bytRoleMoveFrame) {
            if (this.checkIsNeedPull()) {
                this.setPull(super.bytDirection);
            } else {
                this.setStand(super.bytDirection);
            }
            return;
        }
        if (super.blnIsRealMove) {
            final int _speed = super.bytBlockPixel / (super.bytRoleMoveFrame - super.bytFrame + 1);
            super.bytBlockPixel -= (byte) _speed;
            if (super.bytDirection == 1) {
                super.shtJudgeY -= (short) _speed;
                if (super.shtJudgeY - super.shtImgHeight / 2 < Param.getInstance().CAMERAY + Map.getInstance().shtMapImgHeight / 2) {
                    Map.getInstance().moveMap(0, -_speed);
                }
            } else if (super.bytDirection == 2) {
                super.shtJudgeY += (short) _speed;
                if (super.shtJudgeY - super.shtImgHeight / 2 > Param.getInstance().CAMERAY + Map.getInstance().shtMapImgHeight / 2) {
                    Map.getInstance().moveMap(0, _speed);
                }
            } else if (super.bytDirection == 4) {
                super.shtJudgeX += (short) _speed;
                if (super.shtJudgeX + super.shtImgWidth / 2 > Param.getInstance().CAMERAX + Map.getInstance().shtMapImgWidth / 2) {
                    Map.getInstance().moveMap(_speed, 0);
                }
            } else if (super.bytDirection == 3) {
                super.shtJudgeX -= (short) _speed;
                if (super.shtJudgeX + super.shtImgWidth / 2 < Param.getInstance().CAMERAX + Map.getInstance().shtMapImgWidth / 2) {
                    Map.getInstance().moveMap(-_speed, 0);
                }
            }
        }
    }

    protected void setDead(final byte _direction) {
        super.setDead(_direction);
        this.delSelsectRole();
        this.blnAttackTimeFlag = false;
        this.blnAttackTimeFlag = false;
        GameUI.getInstance().blnFight = false;
        GameUI.getInstance().delRoleStateIcon((short) 5);
        this.blnCanMove = true;
        this.blnCanSkill = true;
        this.blnCanFight = true;
        Param.lngDeadCountDownStartTime = System.currentTimeMillis();
        GameUI.getInstance().setDialog((byte) 6);
        this.setRoleAnimation();
    }

    protected void setMagic(final byte _direction) {
        this.switchActionState((byte) 4);
        super.bytDirection = _direction;
        super.blnTaskOK = true;
        super.bytFrame = 0;
        super.blnIsRealMove = false;
        super.bytFrameIndex = 0;
        super.curSkillObj = null;
        SkillObject so = null;
        if (this.intNextSkillNum >= 0) {
            so = (SkillObject) Param.getInstance().vSkillList.elementAt(this.intNextSkillNum);
            if (so != null) {
                this.intSkillTime = (int) System.currentTimeMillis();
                this.intSkillNum = this.intNextSkillNum;
                this.intNextSkillNum = -1;
                super.shtSendSkillTime = 500;
                this.shtMagicTime = so.shtUseTime;
                this.shtMagicTimeMax = this.shtMagicTime;
                super.intMTime = (int) System.currentTimeMillis();
                this.intSkillCDTime = 1500;
                QSprite setSkillResult = null;
                if (Macro.BLN_LEVEL_FUNCTION_SKILL) {
                    setSkillResult = ResPoolLevelFunction.getInstance().bindSkill(this, Param.getInstance().oSelectRole, so);
                } else {
                    setSkillResult = this.setSkill(so);
                }
                if (this.shtMagicTime <= 0) {
                    DebugFrame.getInstance().logIn("\u73a9\u5bb6\u81ea\u8eab\u53d1\u9001\u6280\u80fdskill id / png id" + so.intId + "/" + so.bytSkillPicId);
                    NetSend.getInstance().sendUseSkill(so.intId, super.bytSkillTargetType, super.intSkillTargetID, super.bytDirection);
                    GameUI.getInstance().SkillCD(so.intId);
                    this.intSkillCDTime = 1500;
                    super.curSkillObj = so;
                    this.setRoleAnimation(true);
                    if (super.bytAnimState == 9) {
                        super.blnUseSpellAction = true;
                    }
                    if (super.blnUseSpellAction) {
                        super.bytFrameMax = this.getSpriteFrameMax(2, this.getRoleSpriteEncode(super.shtEquipId), 1);
                    }
                    this.setSkillFrame(super.curSkillObj, 1);
                } else {
                    super.curSkillObj = so;
                    this.setRoleAnimation(true);
                    super.blnKeepMagic = true;
                    if (super.bytAnimState == 9) {
                        super.blnUseSpellAction = true;
                    }
                    super.bytFrameMax = this.getSpriteFrameMax(2, this.getRoleSpriteEncode(super.shtEquipId), 1);
                }
            } else {
                this.intNextSkillNum = -1;
                super.curSkillObj = null;
                super.blnKeepMagic = false;
                super.blnUseSpellAction = false;
            }
        }
    }

    protected void doMagic() {
        final int _time = (int) System.currentTimeMillis();
        if (this.shtImmobilityTime - (_time - super.intMTime) > 0) {
            this.shtImmobilityTime -= (short) (_time - super.intMTime);
        }
        ++super.bytFrameIndex;
        if (this.intSkillNum < 0) {
            this.cleanMagic();
            super.blnTaskOK = true;
            this.pushTask((byte) 0, super.bytDirection);
            return;
        }
        final SkillObject skillObj = (SkillObject) Param.getInstance().vSkillList.elementAt(this.intSkillNum);
        if (skillObj != null) {
            if (this.shtMagicTime > 0) {
                this.shtMagicTime -= (short) (_time - super.intMTime);
                if (this.shtMagicTime <= 0) {
                    super.bytFrame = 0;
                    this.removeSkill(this.getRoleSpriteEncode(skillObj.bytSkillPicId));
                    this.cleanMagic();
                    super.blnTaskOK = true;
                    this.pushTask((byte) 0, super.bytDirection);
                } else if (super.shtSendSkillTime > 0 && this.shtMagicTime <= super.shtSendSkillTime) {
                    final int _id = skillObj.intId;
                    final short _skilltime = skillObj.shtUseTime;
                    final int _nowSkillTime = (int) System.currentTimeMillis();
                    if (_nowSkillTime - this.intSkillTime >= _skilltime - 100 - super.shtSendSkillTime) {
                        NetSend.getInstance().sendUseSkill(_id, super.bytSkillTargetType, super.intSkillTargetID, super.bytDirection);
                    }
                    super.shtSendSkillTime = 0;
                    GameUI.getInstance().SkillCD(_id);
                }
                if (this.checkFrameDone()) {
                    this.setRolePartUpdate(0, false);
                    this.setRolePartUpdate(1, false);
                    this.setRolePartUpdate(3, false);
                    this.setRolePartUpdate(4, false);
                    this.setRolePartUpdate(2, false);
                }
            } else {
                if (super.blnUseSpellAction && this.checkFrameDone()) {
                    this.setRolePartUpdate(0, false);
                    this.setRolePartUpdate(1, false);
                    this.setRolePartUpdate(3, false);
                    this.setRolePartUpdate(4, false);
                    this.setRolePartUpdate(2, false);
                    super.blnUseSpellAction = false;
                }
                if (!super.blnUseSpellAction && this.checkFrameDone(skillObj)) {
                    this.removeSkill(this.getRoleSpriteEncode(skillObj.bytSkillPicId));
                    this.cleanMagic();
                    super.blnTaskOK = true;
                    this.pushTask((byte) 0, super.bytDirection);
                }
            }
        }
        super.intMTime = _time;
    }

    protected void doCartoon() {
        ++super.bytFrame;
        if (super.bytCartoon == 3 && super.sprLevelUp.isPlayDone()) {
            super.bytCartoon = 0;
            super.blnTaskOK = true;
            this.setStand(super.bytDirection);
            this.delLvUPImg();
        }
        if (super.bytCartoon == 1 && super.sprEnterJump.isPlayDone()) {
            super.bytCartoon = 0;
            this.delJumpImg();
            ORPMe.ME.sendMoveTask();
            NetParse.getInstance().setInitLoadingPre();
            NetSend.getInstance().sendChangeMap(Map.getInstance().shtOutJumpPlace[ORPMe.ME.shtEvent][2], Map.getInstance().shtOutJumpPlace[ORPMe.ME.shtEvent][0], Map.getInstance().shtOutJumpPlace[ORPMe.ME.shtEvent][1]);
            NetParse.getInstance().setInitLoading();
            super.blnTaskOK = true;
            this.setStand(super.bytDirection);
        }
    }

    protected void doStand() {
        if (GameUI.getInstance().blnFight) {
            return;
        }
        ++super.bytFrame;
    }

    protected void setFight(final byte _direction) {
        super.setFight(_direction);
    }

    protected void melogic(final int _time) {
        try {
            if (Param.getInstance().vSkillList != null) {
                for (byte i = 0; i < Param.getInstance().vSkillList.size(); ++i) {
                    final SkillObject so = (SkillObject) Param.getInstance().vSkillList.elementAt(i);
                    if (so.intCDTime > 0) {
                        final SkillObject skillObject = so;
                        skillObject.intCDTime -= _time;
                    }
                }
                if (this.intSkillCDTime > 0) {
                    this.intSkillCDTime -= _time;
                }
            }
            if (this.intGoodsCDTime > 0) {
                this.intGoodsCDTime -= _time;
            }
            for (byte i = 0; i < 26; ++i) {
                if (Param.getInstance().intShortcutKeys[i][1] > 0) {
                    final int[] array = Param.getInstance().intShortcutKeys[i];
                    final int n = 1;
                    array[n] -= _time;
                }
            }
            if (this.shtImmobilityTime > 0) {
                if (super.bytState != 4 && this.blnCanFight) {
                    this.shtImmobilityTime -= (short) _time;
                    if (this.blnAttackFlag) {
                        if (this.shtSendAttackTime > 0 && this.shtImmobilityTime <= this.shtSendAttackTime && Param.getInstance().oSelectRole != null && (Param.getInstance().oSelectRole.bytType == 2 || Param.getInstance().oSelectRole.bytType == 3)) {
                            this.shtSendAttackTime = 0;
                            if (this.judgeAttack() == 0) {
                                if (GameUI.getInstance().bytsButtonType[1] == 5) {
                                    GameUI.getInstance().setButton(0, 100, 2);
                                }
                                NetSend.getInstance().sendAttack(Param.getInstance().oSelectRole.bytType, Param.getInstance().oSelectRole.intUserId);
                            } else if (Param.getInstance().oSelectRole.blnIsAttack) {
                                this.blnAttackTimeFlag = true;
                            }
                        }
                        if (this.shtImmobilityTime <= 0 && !this.blnAttackTimeFlag && !this.doAttack()) {
                            this.blnAttackTimeFlag = true;
                        }
                    }
                }
            } else if (this.blnAttackTimeFlag) {
                if (this.blnCanFight) {
                    if (this.judgeAttack() == 0) {
                        this.blnAttackTimeFlag = false;
                        this.doAttack();
                        this.shtSendAttackTime = 500;
                        this.shtImmobilityTime = this.shtImmobilityTimeMax;
                        if (GameUI.getInstance().bytsButtonType[1] == 5) {
                            GameUI.getInstance().setButton(0, 100, 2);
                        }
                        NetSend.getInstance().sendAttack(Param.getInstance().oSelectRole.bytType, Param.getInstance().oSelectRole.intUserId);
                    }
                } else {
                    GameUI.getInstance().addFrightInformation("\u65e0\u6cd5\u653b\u51fb");
                }
            }
            this.shtSendMoveDTime += (short) _time;
            if (this.shtSendMoveDTime > 1500) {
                this.sendMoveTask();
            }
            if (this.bytTeamRank == 0 && super.playerFollowPet != null) {
                this.shtPetPUBoxTime += (short) _time;
                if (this.shtPetPUBoxTime > 1000) {
                    this.shtPetPUBoxTime = 0;
                    this.petCheckBox();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void petCheckBox() {
        if (Param.getInstance().htBox != null && super.playerFollowPet != null) {
            for (byte l = (byte) (super.bytTileY - 4); l < super.bytTileY + 5; ++l) {
                if (l >= 0 && l < Map.getInstance().bytRow && Param.getInstance().htBox != null && !Param.getInstance().htBox.isEmpty()) {
                    final Enumeration e = Param.getInstance().htBox.elements();
                    while (e.hasMoreElements()) {
                        final Box _b = (Box) e.nextElement();
                        if (_b.bytTileX > super.bytTileX - 4 && _b.bytTileX < super.bytTileX + 5 && _b.blnIsDraw && _b.blnShine && !_b.blnPickUp) {
                            final AStar as = new AStar(super.bytTileX, super.bytTileY, _b.bytTileX, _b.bytTileY, (byte) 4, (byte) 1);
                            final byte[] _path = as.getPath();
                            if (_path != null && _path.length > 1) {
                                for (int i = 0; i < _path.length - 1; ++i) {
                                    super.playerFollowPet.pushTask((byte) 1, _path[i]);
                                }
                            } else {
                                super.playerFollowPet.setTileXY(_b.bytTileX, _b.bytTileY);
                                super.playerFollowPet.pushTask((byte) 0, super.playerFollowPet.bytDirection);
                            }
                            _b.blnPickUp = true;
                            if (_b.bytBoxType == 1) {
                                NetSend.getInstance().sendUnlockMonsterBox(_b.intUserId);
                            } else if (_b.bytBoxType == 2) {
                                NetSend.getInstance().sendUnlockWorldBox(_b.intUserId);
                            }
                            return;
                        }
                    }
                }
            }
        }
    }

    public void sendMoveTask() {
        if (!this.vMoveTask.isEmpty()) {
            final byte[] _temp_move = new byte[this.vMoveTask.size()];
            for (int i = 0; i < this.vMoveTask.size(); ++i) {
                _temp_move[i] = ((Integer) this.vMoveTask.elementAt(i)).byteValue();
            }
            this.vMoveTask.removeAllElements();
            NetSend.getInstance().sendMoveDirection(_temp_move, this.bytMoveTileX, this.bytMoveTileY);
        }
        this.shtSendMoveDTime = 0;
    }

    private boolean useGoods(final int _key) {
        if (Param.getInstance().intShortcutKeys[_key][1] > 0) {
            GameUI.getInstance().addFrightInformation(String.valueOf(Param.getInstance().intShortcutKeys[_key][1] / 1000 + 1) + "\u79d2\u51b7\u5374");
            return false;
        }
        if (this.intGoodsCDTime > 0) {
            GameUI.getInstance().addFrightInformation(String.valueOf(this.intGoodsCDTime / 1000 + 1) + "\u79d2\u51b7\u5374");
            return false;
        }
        if (Param.getInstance().bytSelectType == 2 || Param.getInstance().bytSelectType == 3 || Param.getInstance().bytSelectType == 1) {
            NetSend.getInstance().sendUseGoodsKey(Param.getInstance().intShortcutKeys[_key][0], Param.getInstance().bytSelectType, Param.getInstance().intSelectId);
        } else {
            NetSend.getInstance().sendUseGoodsKey(Param.getInstance().intShortcutKeys[_key][0], (byte) 0, 0);
        }
        GameUI.getInstance().GoodsCD(Param.getInstance().intShortcutKeys[_key][0]);
        return true;
    }

    public boolean useSkill(final int _num) {
        final SkillObject so = (SkillObject) Param.getInstance().vSkillList.elementAt(_num);
        if (so == null) {
            return false;
        }
        if (!this.blnCanSkill && so.bytSkillType == 1) {
            GameUI.getInstance().addFrightInformation("\u4e0d\u80fd\u65bd\u6cd5");
            return false;
        }
        if (this.intSkillNum != -1 || this.shtMagicTime > 0 || this.intNextSkillNum >= 0) {
            GameUI.getInstance().addFrightInformation("\u6b63\u5728\u65bd\u6cd5");
            return false;
        }
        if (so.intCDTime > 0) {
            GameUI.getInstance().addFrightInformation("\u51b7\u5374\u4e2d");
            return false;
        }
        if (this.intSkillCDTime > 0) {
            GameUI.getInstance().addFrightInformation("\u51b7\u5374\u4e2d");
            return false;
        }
        if (so.bytMpType == 0 && super.intMP < so.shtMp) {
            GameUI.getInstance().addFrightInformation("\u9b54\u6cd5\u4e0d\u8db3");
            return false;
        }
        if (so.bytMpType == 1 && super.intMP < so.shtMp) {
            GameUI.getInstance().addFrightInformation("\u529b\u503c\u4e0d\u8db3");
            return false;
        }
        if (so.bytMpType == 2 && 100 - super.intMP < so.shtMp) {
            GameUI.getInstance().addFrightInformation("\u6c14\u503c\u4e0d\u8db3");
            return false;
        }
        boolean weanpon = false;
        for (int i = 0; i < so.shtWeapon.length; ++i) {
            if ((so.shtWeapon.length == 1 && so.shtWeapon[i] == 0) || so.shtWeapon[i] == super.bytWeaponPart) {
                weanpon = true;
                break;
            }
        }
        if (!weanpon) {
            GameUI.getInstance().addFrightInformation("\u6b66\u5668\u4e0d\u7b26");
            return false;
        }
        if (super.intHP < so.shtHP) {
            GameUI.getInstance().addFrightInformation("\u751f\u547d\u4e0d\u8db3");
            return false;
        }
        this.cleanMagic();
        Label_0881:
        {
            if (so.bytSelect == 1) {
                if (so.bytUserType == 1) {
                    if (Param.getInstance().intSelectId > 0 && (Param.getInstance().bytSelectType == 2 || Param.getInstance().bytSelectType == 3) && Param.getInstance().oSelectRole.blnIsAttack && Param.getInstance().oSelectRole.bytState != 5) {
                        if (!this.judgeUseSkill(so.bytAtkDistance)) {
                            Macro.blnDistanceFar = true;
                            ORPMe.ME.bytSelectPos = new byte[]{Param.getInstance().oSelectRole.bytTileX, Param.getInstance().oSelectRole.bytTileY};
                            ORPMe.ME.bytSelectPosArea = so.bytAtkDistance;
                            ORPMe.ME.intSelectSkillNum = _num;
                        } else {
                            if (this.checkAttack(Param.getInstance().oSelectRole.bytTileX, Param.getInstance().oSelectRole.bytTileY, Param.getInstance().oSelectRole.bytPicSize)) {
                                break Label_0881;
                            }
                            GameUI.getInstance().addFrightInformation("\u8d85\u51fa\u89c6\u91ce");
                        }
                    } else {
                        GameUI.getInstance().addFrightInformation("\u76ee\u6807\u9519\u8bef");
                    }
                } else if (so.bytUserType == 2) {
                    if (Param.getInstance().intSelectId <= 0 || Param.getInstance().bytSelectType != 2 || Param.getInstance().oSelectRole.blnIsAttack || Param.getInstance().oSelectRole.bytCountry != super.bytCountry) {
                        super.intSkillTargetID = super.intUserId;
                        super.bytSkillTargetType = 2;
                        break Label_0881;
                    }
                    if (!this.judgeUseSkill(so.bytAtkDistance)) {
                        GameUI.getInstance().addFrightInformation("\u8ddd\u79bb\u592a\u8fdc");
                        Macro.blnDistanceFar = true;
                        ORPMe.ME.bytSelectPos = new byte[]{Param.getInstance().oSelectRole.bytTileX, Param.getInstance().oSelectRole.bytTileY};
                        ORPMe.ME.bytSelectPosArea = so.bytAtkDistance;
                        ORPMe.ME.intSelectSkillNum = _num;
                    } else {
                        if (this.checkAttack(Param.getInstance().oSelectRole.bytTileX, Param.getInstance().oSelectRole.bytTileY, Param.getInstance().oSelectRole.bytPicSize)) {
                            break Label_0881;
                        }
                        GameUI.getInstance().addFrightInformation("\u8d85\u51fa\u89c6\u91ce");
                    }
                } else {
                    if (so.bytUserType != 3) {
                        break Label_0881;
                    }
                    if (Param.getInstance().intSelectId > 0 && Param.getInstance().bytSelectType == 2 && Param.getInstance().oSelectRole.bytCountry == super.bytCountry && Param.getInstance().oSelectRole.bytState == 5) {
                        if (!this.judgeUseSkill(so.bytAtkDistance)) {
                            Macro.blnDistanceFar = true;
                            ORPMe.ME.bytSelectPos = new byte[]{Param.getInstance().oSelectRole.bytTileX, Param.getInstance().oSelectRole.bytTileY};
                            ORPMe.ME.bytSelectPosArea = so.bytAtkDistance;
                            ORPMe.ME.intSelectSkillNum = _num;
                        } else {
                            if (this.checkAttack(Param.getInstance().oSelectRole.bytTileX, Param.getInstance().oSelectRole.bytTileY, Param.getInstance().oSelectRole.bytPicSize)) {
                                break Label_0881;
                            }
                            GameUI.getInstance().addFrightInformation("\u8d85\u51fa\u89c6\u91ce");
                        }
                    } else {
                        GameUI.getInstance().addFrightInformation("\u76ee\u6807\u9519\u8bef");
                    }
                }
                this.cleanMagic();
                if (Macro.blnDistanceFar) {
                    Macro.blnDistanceFar = false;
                    if (ORPMe.ME.bytSelectPos != null) {
                        Macro.blnDistanceFarAStar = true;
                        GameUI.getInstance().setAStar(ORPMe.ME.bytSelectPos[0], ORPMe.ME.bytSelectPos[1]);
                        ORPMe.ME.bytSelectPos = null;
                    }
                }
                return false;
            }
            this.sendMoveTask();
            super.intSkillTargetID = super.intUserId;
            super.bytSkillTargetType = 2;
        }
        byte _d = super.bytDirection;
        if (super.intSkillTargetID == 0 && Param.getInstance().oSelectRole != null) {
            super.intSkillTargetID = Param.getInstance().oSelectRole.intUserId;
            super.bytSkillTargetType = Param.getInstance().oSelectRole.bytType;
            if (so.bytSelect == 1) {
                _d = GameUI.getInstance().getDirection(super.bytTileX, super.bytTileY, super.bytPicSize, Param.getInstance().oSelectRole.bytTileX, Param.getInstance().oSelectRole.bytTileY, Param.getInstance().oSelectRole.bytPicSize);
            }
        }
        this.intNextSkillNum = _num;
        this.pushTask((byte) 4, _d);
        return true;
    }

    public void cleanMagic() {
        this.intSkillNum = -1;
        this.intNextSkillNum = -1;
        super.intSkillTargetID = 0;
        super.bytSkillTargetType = 0;
        this.shtMagicTime = 0;
        super.shtSendSkillTime = 0;
        super.curSkillObj = null;
        super.bytFrameIndex = 0;
        super.bytFrameMax = 0;
        this.resetRolePartUpdate(true);
        super.blnUseSpellAction = false;
        super.blnKeepMagic = false;
        super.curSkillObj = null;
        super.cleanMagic();
    }

    private boolean judgeUseSkill(final byte _size) {
        return Param.getInstance().oSelectRole == null || this.checkArea(Param.getInstance().oSelectRole.bytTileX, Param.getInstance().oSelectRole.bytTileY, _size, Param.getInstance().oSelectRole.bytPicSize);
    }

    public void setSelectRole(final ORole _o) {
        if (super.blnStall || _o == null) {
            return;
        }
        if (!Rms.blnShowRole && _o.bytType == 2) {
            return;
        }
        this.blnAttackTimeFlag = false;
        this.blnAttackFlag = false;
        GameUI.getInstance().bytMeSelLevel = GameUI.getInstance().getBytes(_o.shtLevel);
        Param.getInstance().intSelectId = _o.intUserId;
        Param.getInstance().oSelectRole = _o;
        Param.getInstance().oSelectNpc = null;
        Param.getInstance().bSelectBox = null;
        Param.getInstance().bSelectTask = null;
        Param.getInstance().bytSelectType = _o.bytType;
        GameUI.getInstance().setButton();
    }

    public void setSelectBox(final Box _o) {
        if (super.blnStall || _o == null) {
            return;
        }
        Param.getInstance().intSelectId = _o.intUserId;
        Param.getInstance().bSelectBox = _o;
        Param.getInstance().oSelectRole = null;
        Param.getInstance().oSelectNpc = null;
        Param.getInstance().bSelectTask = null;
        Param.getInstance().bytSelectType = 4;
        GameUI.getInstance().setButton();
    }

    public void setSelectTask(final Task _t) {
        if (super.blnStall || _t == null) {
            return;
        }
        Param.getInstance().intSelectId = _t.intUserId;
        Param.getInstance().bSelectTask = _t;
        Param.getInstance().bSelectBox = null;
        Param.getInstance().oSelectRole = null;
        Param.getInstance().oSelectNpc = null;
        Param.getInstance().bytSelectType = 5;
        GameUI.getInstance().setButton();
    }

    public void setSelectRole(final ONpc _o) {
        if (super.blnStall || _o == null) {
            return;
        }
        Param.getInstance().intSelectId = _o.intUserId;
        Param.getInstance().oSelectNpc = _o;
        Param.getInstance().bSelectBox = null;
        Param.getInstance().oSelectRole = null;
        Param.getInstance().bSelectTask = null;
        Param.getInstance().bytSelectType = 1;
        GameUI.getInstance().setButton();
    }

    public void delSelsectRole() {
        if (DCanvas.getInstance().UHandle != null) {
            if (Param.getInstance().bytSelectType == 2 && HandleUI.getInstance().bytState == -1) {
                GameControl.getInstance().delUIbase(5);
                GameControl.getInstance().delUIbase(8);
            } else if (Param.getInstance().bytSelectType == 4 && HandleUI.getInstance().bytState == -2) {
                GameControl.getInstance().delUIbase(5);
                GameControl.getInstance().delUIbase(8);
            }
        }
        Param.getInstance().intSelectId = -1;
        Param.getInstance().bytSelectType = 0;
        Param.getInstance().bSelectBox = null;
        Param.getInstance().oSelectNpc = null;
        Param.getInstance().oSelectRole = null;
        Param.getInstance().bSelectTask = null;
        GameUI.getInstance().bytMeSelLevel = null;
        this.blnAttackFlag = false;
        this.blnAttackTimeFlag = false;
        this.shtSelectNum = 0;
        this.shtFirstSelectNum = 0;
        this.bytSelectType = 0;
        this.bytFirstSelectPlayer = 0;
        this.bytFirstSelectMonster = 0;
        this.bytFirstSelectBox = 0;
        GameUI.getInstance();
        GameUI.lastSelectTargetIndex = -1;
        GameUI.getInstance().setButton();
    }

    public boolean find_Role(final byte _x, final byte _y, final boolean _checktype, final boolean positionCheck, final byte _countryType) {
        this.bytSelectPos = null;
        this.bytSelectPosArea = 0;
        if (this.findPlayer(_x, _y, _checktype, positionCheck, this.bytFirstSelectPlayer, _countryType)) {
            ++this.bytFirstSelectPlayer;
            return true;
        }
        if (this.findNpc(_y, _x, _checktype, positionCheck, _countryType)) {
            return true;
        }
        if (this.findMonster(_x, _y, _checktype, positionCheck, this.bytFirstSelectMonster, _countryType)) {
            ++this.bytFirstSelectMonster;
            return true;
        }
        if (this.findBox(_x, _y, _checktype, positionCheck, this.bytFirstSelectBox, _countryType)) {
            ++this.bytFirstSelectBox;
            return true;
        }
        if (this.findTask(_x, _y, _checktype, positionCheck, this.bytFirstSelectBox, _countryType)) {
            ++this.bytFirstSelectBox;
            return true;
        }
        return false;
    }

    private boolean findPlayer(final int _x, final int _y, final boolean _type, final boolean positionCheck, final byte _s, final byte _countryType) {
        if (!Rms.blnShowRole) {
            return false;
        }
        if (Param.getInstance().htRolePlayer != null && !Param.getInstance().htRolePlayer.isEmpty() && Param.getInstance().htRolePlayer != null) {
            final Enumeration e = Param.getInstance().htRolePlayer.elements();
            while (e.hasMoreElements()) {
                final ORPlayer _o = (ORPlayer) e.nextElement();
                final int id = _o.intUserId;
                if (id != super.intUserId && _o.bytConceal != 2 && this.checkCordination(_x, _y, _o.bytTileX, _o.bytTileY, _o.bytPicSize, _o.shtImgWidth, _o.shtImgHeight, _type, positionCheck) && (_countryType == 0 || (_countryType == 1 && _o.blnCountry) || (_countryType == 2 && !_o.blnCountry))) {
                    if (Param.getInstance().bytSelectType != 2 || id != Param.getInstance().intSelectId) {
                        this.setSelectRole(_o);
                        return this.pointFrame = true;
                    }
                    if (_type && Param.getInstance().bytSelectType == 2 && id == Param.getInstance().intSelectId) {
                        this.pointFrame = true;
                        ORPMe.ME.doLeftKey();
                        return true;
                    }
                    continue;
                }
            }
        }
        return false;
    }

    private boolean findNpc(final int _y, final int _x, final boolean _type, final boolean positionCheck, final byte _countryType) {
        if (Param.getInstance().htRoleNPC != null && !Param.getInstance().htRoleNPC.isEmpty()) {
            final Enumeration e = Param.getInstance().htRoleNPC.elements();
            while (e.hasMoreElements()) {
                final ONpc _o = (ONpc) e.nextElement();
                final int _id = _o.intUserId;
                if (this.checkCordination(_x, _y, _o.bytTileX, _o.bytTileY, _o.bytPicSize, _o.shtImgWidth, _o.shtImgHeight, _type, positionCheck) && (_countryType == 0 || (_countryType == 2 && !_o.blnCountry) || _countryType == 3)) {
                    if (Param.getInstance().bytSelectType != 1 || _id != Param.getInstance().intSelectId) {
                        this.setSelectRole(_o);
                        return this.pointFrame = true;
                    }
                    if (!_type || Param.getInstance().bytSelectType != 1 || _id != Param.getInstance().intSelectId) {
                        return false;
                    }
                    this.pointFrame = true;
                    if (this.checkArea(_o, (byte) 4)) {
                        ORPMe.ME.doLeftKey();
                        return true;
                    }
                    this.bytSelectPos = new byte[]{Param.getInstance().oSelectNpc.bytTileX, Param.getInstance().oSelectNpc.bytTileY};
                    ORPMe.ME.bytSelectPosArea = 4;
                    return true;
                }
            }
        }
        return false;
    }

    private boolean findBox(final int _x, final int _y, final boolean _type, final boolean positionCheck, final byte _s, final byte _countryType) {
        if (Param.getInstance().htBox != null && !Param.getInstance().htBox.isEmpty()) {
            final Enumeration e = Param.getInstance().htBox.elements();
            while (e.hasMoreElements()) {
                final Box _o = (Box) e.nextElement();
                final int _id = _o.intUserId;
                if (this.checkCordination(_x, _y, _o.bytTileX, _o.bytTileY, _o.bytPicSize, _o.shtImgWidth, _o.shtImgHeight, _type, positionCheck) && _o.blnShine && _countryType != 2) {
                    if (Param.getInstance().bytSelectType != 4 || _id != Param.getInstance().intSelectId) {
                        this.setSelectBox(_o);
                        return this.pointFrame = true;
                    }
                    if (!_type || Param.getInstance().bytSelectType != 4 || _id != Param.getInstance().intSelectId) {
                        continue;
                    }
                    this.pointFrame = true;
                    if (this.checkArea(_o, (byte) 4)) {
                        ORPMe.ME.doLeftKey();
                        return true;
                    }
                    this.bytSelectPos = new byte[]{Param.getInstance().bSelectBox.bytTileX, Param.getInstance().bSelectBox.bytTileY};
                    ORPMe.ME.bytSelectPosArea = 4;
                    return true;
                }
            }
        }
        return false;
    }

    private boolean findTask(final int _x, final int _y, final boolean _type, final boolean positionCheck, final byte _s, final byte _countryType) {
        if (Param.getInstance().htTask != null && !Param.getInstance().htTask.isEmpty()) {
            final Enumeration e = Param.getInstance().htTask.elements();
            while (e.hasMoreElements()) {
                final Task _t = (Task) e.nextElement();
                final int _id = _t.intUserId;
                if (this.checkCordination(_x, _y, _t.bytTileX, _t.bytTileY, _t.bytPicSize, _t.shtImgWidth, _t.shtImgHeight, _type, positionCheck) && _t.blnShine && _countryType != 2) {
                    if (Param.getInstance().bytSelectType != 5 || _id != Param.getInstance().intSelectId) {
                        this.setSelectTask(_t);
                        return this.pointFrame = true;
                    }
                    if (!_type || Param.getInstance().bytSelectType != 5 || _id != Param.getInstance().intSelectId) {
                        continue;
                    }
                    this.pointFrame = true;
                    if (this.checkArea(_t, (byte) 4)) {
                        ORPMe.ME.doLeftKey();
                        return true;
                    }
                    this.bytSelectPos = new byte[]{Param.getInstance().bSelectTask.bytTileX, Param.getInstance().bSelectTask.bytTileY};
                    ORPMe.ME.bytSelectPosArea = 4;
                    return true;
                }
            }
        }
        return false;
    }

    private boolean findMonster(final int _x, final int _y, final boolean _type, final boolean positionCheck, final byte _s, final byte _countryType) {
        if (Param.getInstance().htRoleMonster != null && !Param.getInstance().htRoleMonster.isEmpty()) {
            final Enumeration e = Param.getInstance().htRoleMonster.elements();
            while (e.hasMoreElements()) {
                final ORMonster _o = (ORMonster) e.nextElement();
                final int _id = _o.intUserId;
                if (_o.bytConceal != 2 && _o.bytState != 5 && this.checkCordination(_x, _y + 4, _o.bytTileX, (byte) (_o.bytTileY + 4), _o.bytPicSize, _o.shtImgWidth, _o.shtImgHeight, _type, positionCheck) && (_countryType == 0 || (_countryType == 2 && !_o.blnCountry) || (_countryType == 1 && _o.blnCountry))) {
                    if (Param.getInstance().bytSelectType != 3 || _id != Param.getInstance().intSelectId) {
                        this.setSelectRole(_o);
                        return this.pointFrame = true;
                    }
                    if (!_type || Param.getInstance().bytSelectType != 3 || _id != Param.getInstance().intSelectId) {
                        continue;
                    }
                    this.pointFrame = true;
                    ORPMe.ME.doLeftKey();
                    if (!this.doAttack()) {
                        this.bytSelectPos = new byte[]{Param.getInstance().oSelectRole.bytTileX, Param.getInstance().oSelectRole.bytTileY};
                        ORPMe.ME.bytSelectPosArea = this.bytAtkDistance;
                        return true;
                    }
                    continue;
                }
            }
        }
        return false;
    }

    private boolean checkCordination(final int _x, final int _y, final byte bytTileX, final byte bytTileY, final byte picSize, final short shtImgWidth, final short shtImgHeight, final boolean _type, final boolean positionCheck) {
        if (_type) {
            if (_x * 16 >= bytTileX * 16 - shtImgWidth / 2 && _x * 16 <= bytTileX * 16 + shtImgWidth / 2 && _y <= bytTileY && _y * 16 >= bytTileY * 16 - shtImgHeight) {
                return true;
            }
        } else if (positionCheck) {
            if (_x == bytTileX && _y == bytTileY) {
                return true;
            }
        } else if (_x >= bytTileX - ORole.transPicSize(picSize) / 2 - 1 && _x <= bytTileX + ORole.transPicSize(picSize) / 2 + 1 && _y >= bytTileY - ORole.transPicSize(picSize) / 2 - 1 && _y <= bytTileY + ORole.transPicSize(picSize) / 2 + 1) {
            return true;
        }
        return false;
    }

    private boolean checkAttack(final byte _tox, final byte _toy, final byte _size) {
        boolean type = true;
        for (byte k = 0; k < _size; ++k) {
            type = true;
            byte s_x = super.bytTileX;
            byte s_y = super.bytTileY;
            final byte e_x = (byte) (_tox + k);
            final byte e_y = _toy;
            byte m_x = 0;
            byte m_y = 0;
            byte m_x_t = 0;
            byte m_y_t = 0;
            byte max_step = 0;
            m_x = (byte) ((e_x - s_x) / 2);
            m_x_t = (byte) ((e_x - s_x) % 2);
            m_y = (byte) ((e_y - s_y) / 2);
            m_y_t = (byte) ((e_y - s_y) % 2);
            max_step = (byte) Math.abs((Math.abs(m_x) >= Math.abs(m_y)) ? m_x : m_y);
            m_x += s_x;
            m_y += s_y;
            final int _num = max_step;
            this.bytsAttackStep = new byte[_num];
            byte _tempx = 0;
            byte _tempy = 0;
            for (byte i = 0; i < max_step; ++i) {
                _tempx = 0;
                _tempy = 0;
                if (s_x > m_x) {
                    _tempx = -1;
                } else if (s_x < m_x) {
                    _tempx = 1;
                }
                if (s_y > m_y) {
                    _tempy = -1;
                } else if (s_y < m_y) {
                    _tempy = 1;
                }
                if (!this.checkD(s_x, s_y, _tempx, _tempy)) {
                    type = false;
                    break;
                }
                s_x += _tempx;
                s_y += _tempy;
                this.addstep((byte) (max_step - i - 1), _tempx, _tempy);
            }
            if (type) {
                if (m_x_t != 0 || m_y_t != 0) {
                    if (!this.checkD(s_x, s_y, m_x_t, m_y_t)) {
                        continue;
                    }
                    s_x += m_x_t;
                    s_y += m_y_t;
                }
                for (byte i = 0; i < _num; ++i) {
                    _tempx = 0;
                    _tempy = 0;
                    if (this.bytsAttackStep[i] == 0) {
                        _tempy = -1;
                    } else if (this.bytsAttackStep[i] == 1) {
                        _tempx = 1;
                        _tempy = -1;
                    } else if (this.bytsAttackStep[i] == 2) {
                        _tempx = 1;
                    } else if (this.bytsAttackStep[i] == 3) {
                        _tempx = 1;
                        _tempy = 1;
                    } else if (this.bytsAttackStep[i] == 4) {
                        _tempy = 1;
                    } else if (this.bytsAttackStep[i] == 5) {
                        _tempx = -1;
                        _tempy = 1;
                    } else if (this.bytsAttackStep[i] == 6) {
                        _tempx = -1;
                    } else if (this.bytsAttackStep[i] == 7) {
                        _tempx = -1;
                        _tempy = -1;
                    }
                    if (!this.checkD(s_x, s_y, _tempx, _tempy)) {
                        type = false;
                    }
                    if (type) {
                        s_x += _tempx;
                        s_y += _tempy;
                    }
                }
                return true;
            }
        }
        return false;
    }

    private void addstep(final byte _num, final byte _tempx, final byte _tempy) {
        if (_tempx == 0) {
            if (_tempy == 1) {
                this.bytsAttackStep[_num] = 4;
            } else if (_tempy == -1) {
                this.bytsAttackStep[_num] = 0;
            }
        } else if (_tempx == 1) {
            if (_tempy == 1) {
                this.bytsAttackStep[_num] = 3;
            } else if (_tempy == -1) {
                this.bytsAttackStep[_num] = 1;
            } else if (_tempy == 0) {
                this.bytsAttackStep[_num] = 2;
            }
        } else if (_tempx == -1) {
            if (_tempy == 1) {
                this.bytsAttackStep[_num] = 5;
            } else if (_tempy == -1) {
                this.bytsAttackStep[_num] = 7;
            } else if (_tempy == 0) {
                this.bytsAttackStep[_num] = 6;
            }
        }
    }

    private boolean checkD(final byte _x, final byte _y, final byte _tempx, final byte _tempy) {
        boolean _xok = true;
        boolean _yok = true;
        if (_tempx != 0) {
            if (!Map.getInstance().checkIsPass(_x + _tempx, _y)) {
                _xok = false;
            }
        } else {
            _xok = false;
        }
        if (_tempy != 0) {
            if (!Map.getInstance().checkIsPass(_x, _y + _tempy)) {
                _yok = false;
            }
        } else {
            _yok = false;
        }
        return (_xok || _yok) && (_tempx == 0 || _tempy == 0 || Map.getInstance().checkIsPass(_x + _tempx, _y + _tempy));
    }

    public boolean checkArea(final ORole role, final byte area) {
        return this.checkArea(role.bytTileX, role.bytTileY, area, ORole.transPicSize(role.bytPicSize));
    }

    public boolean checkArea(final byte _tox, final byte _toy, final byte _area, final byte _size) {
        if (_area == 0) {
            return true;
        }
        final byte s_x = super.bytTileX;
        final byte s_y = super.bytTileY;
        final byte e_x = _tox;
        final byte e_y = _toy;
        return Common.pow(s_x - e_x, 2) + Common.pow(s_y - e_y, 2) <= Common.pow(_area + _size / 2, 2);
    }

    private byte judgeAttack() {
        final byte _re = 0;
        if (Param.getInstance().intSelectId <= 0 || (Param.getInstance().bytSelectType != 2 && Param.getInstance().bytSelectType != 3)) {
            return 5;
        }
        if (!Param.getInstance().oSelectRole.blnIsAttack) {
            return 5;
        }
        if (!this.checkArea(Param.getInstance().oSelectRole.bytTileX, Param.getInstance().oSelectRole.bytTileY, this.bytAtkDistance, Param.getInstance().oSelectRole.bytPicSize)) {
            return 1;
        }
        if (!this.checkAttack(Param.getInstance().oSelectRole.bytTileX, Param.getInstance().oSelectRole.bytTileY, Param.getInstance().oSelectRole.bytPicSize)) {
            return 2;
        }
        this.bytsAttackStep = null;
        return _re;
    }

    public boolean doAttack() {
        if (Param.getInstance().oSelectRole != null && this.shtImmobilityTime <= 0 && Param.getInstance().oSelectRole.bytState != 5) {
            final byte _type = this.judgeAttack();
            if (_type == 0) {
                PetObject.blnFight = true;
                this.shtSendAttackTime = 500;
                final byte d = GameUI.getInstance().getDirection(super.bytTileX, super.bytTileY, super.bytPicSize, Param.getInstance().oSelectRole.bytTileX, Param.getInstance().oSelectRole.bytTileY, Param.getInstance().oSelectRole.bytPicSize);
                this.pushTask((byte) 2, d);
                this.shtImmobilityTime = this.shtImmobilityTimeMax;
                if (this.isPetDoAttack()) {
                    super.playerFollowPet.pushTask((byte) 2, d);
                    NetSend.getInstance().sendPetAttack(super.playerFollowPet.intUserId, Param.getInstance().oSelectRole.bytType, Param.getInstance().oSelectRole.intUserId);
                    Macro.blnAtc = true;
                }
                return true;
            }
            if (_type == 1) {
                Macro.blnDistanceFar = true;
                ORPMe.ME.bytSelectPos = new byte[]{Param.getInstance().oSelectRole.bytTileX, Param.getInstance().oSelectRole.bytTileY};
                ORPMe.ME.bytSelectPosArea = this.bytAtkDistance;
                return false;
            }
            if (_type == 2) {
                GameUI.getInstance().addFrightInformation("\u8d85\u51fa\u89c6\u91ce");
                return false;
            }
            if (_type == 3) {
                GameUI.getInstance().addFrightInformation("\u6253\u4e0d\u5230");
                return false;
            }
            if (_type == 4) {
                GameUI.getInstance().addFrightInformation("\u5fc5\u987b\u6301\u6709\u6b66\u5668\u624d\u80fd\u666e\u901a\u653b\u51fb");
                return false;
            }
            if (_type == 5) {
                GameUI.getInstance().addFrightInformation("\u76ee\u6807\u9519\u8bef");
                return false;
            }
        }
        return false;
    }

    public SkillObject PetUseSkill() {
        if (super.playerFollowPet != null && Param.getInstance().htPetSkillList != null) {
            final Vector petSkill = (Vector) Param.getInstance().htPetSkillList.get(new Integer(super.playerFollowPet.intUserId));
            if (petSkill != null && petSkill.size() > 0) {
                final int randomSkill = Common.getRandom(0, petSkill.size());
                return (SkillObject) petSkill.elementAt(randomSkill);
            }
        }
        return null;
    }

    public boolean isPetDoAttack() {
        return super.playerFollowPet != null;
    }

    public void resetMe() {
        this.shtSendMoveDTime = 0;
        this.vMoveTask.removeAllElements();
        this.cleanTask();
        if (super.bytState != 0 || super.bytState != 5) {
            this.setStand(super.bytDirection);
        }
        super.vecNextSkill.clear();
        super.intsDamageData = null;
        super.intsDamageType = null;
        super.intsCureData = null;
        super.bytCureDataIndex = -1;
        super.bytMpDataIndex = -1;
        super.bytIntensify = -1;
        this.bytWeaponMusic = -1;
        super.itsf = null;
        this.delSelsectRole();
        super.playerFollowPet = null;
    }

    public void drawEffect(final Graphics g, final short _tx, short _ty) {
        if (super.intsBuff != null) {
            int _x = _tx;
            g.setColor(10309913);
            for (int i = 0; i < super.strsBuff.length; ++i) {
                if ((super.intsBuff[i][1] > 5000 || super.intsBuff[i][1] / 300 % 3 < 2) && super.intsBuff[i][3] != 0) {
                    final short _icon = (short) super.intsBuff[i][2];
                    final Image iconImage = GameUI.getInstance().getStateIcon(_icon);
                    if (iconImage != null) {
                        DrawBase.drawImage(iconImage, _x, _ty, 20);
                    }
                    if (super.intsBuff[i][1] != 0) {
                        final int _m = super.intsBuff[i][1] * 8 / super.intsBuff[i][3];
                        g.drawRGB(Param.getInstance().intsBuffColor, 0, 8, _x, (int) _ty, 8, 8 - _m, true);
                    }
                    g.drawRect(_x - 1, _ty - 1, 9, 9);
                }
                _x += 9;
            }
        }
        if (super.intsDeBuff != null) {
            _ty += 9;
            int _x = _tx;
            g.setColor(10309913);
            for (int i = 0; i < super.strsDeBuff.length; ++i) {
                if ((super.intsDeBuff[i][1] > 5000 || super.intsDeBuff[i][1] / 300 % 3 < 2) && super.intsDeBuff[i][3] != 0) {
                    final short _icon = (short) super.intsDeBuff[i][2];
                    final Image iconImage = GameUI.getInstance().getStateIcon(_icon);
                    DrawBase.drawImage(iconImage, _x, _ty, 20);
                    if (super.intsDeBuff[i][1] != 0) {
                        final int _m = super.intsDeBuff[i][1] * 8 / super.intsDeBuff[i][3];
                        g.drawRGB(Param.getInstance().intsBuffColor, 0, 8, _x, (int) _ty, 8, 8 - _m, true);
                    }
                    g.drawRect(_x - 1, _ty - 1, 9, 9);
                }
                _x += 9;
            }
        }
    }

    public void drawDamageData(final Graphics g) {
        final int _drawx = super.shtJudgeX + super.shtDrawNameX - Param.getInstance().CAMERAX;
        final int _drawy = super.shtJudgeY + super.shtDrawNameY - Param.getInstance().CAMERAY;
        this.drawFightValue(g, _drawx, _drawy);
    }

    public void drawCureData(final Graphics g) {
        final int _drawx = super.shtJudgeX + super.shtDrawNameX - Param.getInstance().CAMERAX + 14;
        final int _drawy = super.shtJudgeY + super.shtDrawNameY - Param.getInstance().CAMERAY - 10;
        this.drawAddValue(g, _drawx, _drawy);
    }

    public void drawaddValue(final Graphics g) {
        final int _drawx = super.shtJudgeX + super.shtDrawNameX - Param.getInstance().CAMERAX + 14;
        final int _drawy = super.shtJudgeY + super.shtDrawNameY - Param.getInstance().CAMERAY - 10;
        this.drawAddValueMp(g, _drawx, _drawy);
    }

    public void setTile(final byte _x, final byte _y) {
        super.bytTileX = _x;
        super.bytTileY = _y;
        super.shtJudgeX = this.getJudgeX();
        super.shtJudgeY = this.getJudgeY();
        final byte _d = Map.getInstance().getDirection(super.bytTileX, super.bytTileY);
        if (_d != 0) {
            super.bytDirection = _d;
        }
        if (super.bytState == 0) {
            this.setStand(super.bytDirection);
        }
        if (super.playerFollowPet != null) {
            super.playerFollowPet.newPet(super.bytTileX, super.bytTileY);
            GameUI.getInstance().addObject(super.playerFollowPet);
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
        if (!super.blnStall) {
            if (super.strConsortia != null && !super.strConsortia.equals("")) {
                StringManager.drawShadowWord(g, "<" + super.strConsortia + ">", super.shtJudgeX + super.shtDrawNameX - Param.getInstance().CAMERAX, super.shtJudgeY + super.shtDrawNameY - Param.getInstance().CAMERAY - _roleHpNameHeight - Macro.FONTHEIGHT, IDefines.FACTION_NAME_COLOUR[0], IDefines.FACTION_NAME_COLOUR[1], 33);
            }
            if (_type == 4) {
                GameUI.getInstance().drawHP(g, super.shtJudgeX + super.shtDrawNameX - Param.getInstance().CAMERAX, super.shtJudgeY + super.shtDrawNameY - Param.getInstance().CAMERAY - _roleHpNameHeight, super.intHP, super.intHPMax, 516456, 429586);
            } else {
                StringManager.drawShadowWord(g, String.valueOf(super.strDrawName) + super.strDebugInfo, super.shtJudgeX + super.shtDrawNameX - Param.getInstance().CAMERAX, super.shtJudgeY + super.shtDrawNameY - Param.getInstance().CAMERAY - _roleHpNameHeight, 15326509, 7290894, 33);
            }
        } else if (_type % 2 == 0) {
            super.drawStallInfo(g, super.strStallInfo, 15326509);
        } else {
            super.drawStallInfo(g, super.strNickName, 16777215);
        }
    }

    public void setStall(final String _str) {
        this.delSelsectRole();
        super.setStall(_str);
        GameUI.getInstance().setButton();
    }

    public void setOccupation(final byte OccupationId) {
        if (OccupationId > 7) {
            this.bytOccupationLater = OccupationId;
        } else {
            this.bytOccupationBefore = OccupationId;
        }
    }
}
