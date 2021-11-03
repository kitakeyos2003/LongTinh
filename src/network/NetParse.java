package network;

import base.DCanvas;
import base.GameControl;
import base.Macro;
import base.Param;
import face.CueUI;
import face.GameUI;
import face.LoadingUI;
import face.MenuUI;
import face.Peffect;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import javax.microedition.lcdui.Image;
import means.DebugFrame;
import means.ImageManager;
import means.QSprite;
import means.Rms;
import model.Box;
import model.MakeObject;
import model.Map;
import model.MenuObject;
import model.ONpc;
import model.ORMonster;
import model.ORPMe;
import model.ORPlayer;
import model.ORole;
import model.PackageObject;
import model.PetObject;
import model.ResPoolLevelFunction;
import model.SkillObject;
import model.Task;
import model.TaskObject;
import model.TeamObject;

public class NetParse
        implements Runnable {

    private static NetParse np;
    private static NetParse2 np2;
    private static NetParse3 np3;
    private Vector packetNet;
    private boolean isParse;
    private boolean isRun;
    public int intPSessionId;
    int _MaxTemp = 511;

    public NetParse() {
        np = this;
        np2 = new NetParse2();
        np3 = new NetParse3();
        this.packetNet = new Vector(2, 1);
        this.isParse = false;
        this.isRun = true;
        new Thread((Runnable) this).start();
    }

    public static NetParse getInstance() {
        return np;
    }

    public void addNetPacket(byte[] _data) {
        this.packetNet.addElement((Object) _data);
    }

    public void clean() {
        this.stopParse();
        np = null;
        np2 = null;
        np3 = null;
    }

    public void reload() {
        this.packetNet = new Vector(2, 1);
        this.isParse = false;
    }

    private void parse() {
        this.isParse = true;
        byte[] _data = (byte[]) this.packetNet.elementAt(0);
        this.packetNet.removeElementAt(0);
        ByteArrayInputStream bais = new ByteArrayInputStream(_data);
        DataInputStream dis = new DataInputStream((InputStream) bais);
        long recvTime = 0L;
        try {
            dis.readByte();
            this.intPSessionId = dis.readInt();
            byte _num = dis.readByte();
            for (byte i = 0; i < _num; i = (byte) (i + 1)) {
                try {
                    int length = 0;
                    try {
                        length = dis.readShort() & 65535;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    byte[] data = new byte[length];
                    dis.readFully(data);
                    this.parsePacket(data);
                    continue;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.isParse = false;
    }

    public void run() {
        while (this.isRun) {
            try {
                if (!this.isParse && !this.packetNet.isEmpty()) {
                    this.parse();
                }
                Thread.sleep((long) 100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopParse() {
        this.isRun = false;
        this.isParse = false;
        this.packetNet.removeAllElements();
    }

    private void parsePacket(byte[] _disData) {
        int _PackID = -1;
        try {
            try {
                _PackID = np2.parsePacket(_disData);
                _PackID = np3.parsePacket(_disData);
            } catch (Exception e) {
                e.printStackTrace();
                if (_PackID != 11545 && _PackID != 11544 && _PackID != 1032 && _PackID != 2048 && _PackID != 11569 && _PackID != 7681 && _PackID != 7186 && _PackID != 7189 && _PackID != 751) {
                    DCanvas.getInstance().setNetLoad(false);
                }
            }
        } finally {
            if (_PackID != 11545 && _PackID != 11544 && _PackID != 1032 && _PackID != 2048 && _PackID != 11569 && _PackID != 7681 && _PackID != 7186 && _PackID != 7189 && _PackID != 751) {
                DCanvas.getInstance().setNetLoad(false);
            }
        }
    }

    public void insertTeamPlayer(Vector tempV, TeamObject _to) {
        if (_to.blnIsOnLine) {
            tempV.insertElementAt((Object) _to, 0);
        } else {
            tempV.addElement((Object) _to);
        }
    }

    public void TeamPlayerChange() {
        Enumeration enumPlayer = Param.getInstance().vTeam.elements();
        Param.bytChatSubTeamNumCount = null;
        Param.bytChatSubTeamNumCount = new byte[2];
        while (enumPlayer.hasMoreElements()) {
            TeamObject tmpObj = (TeamObject) enumPlayer.nextElement();
            if (tmpObj.bytTeamId == 1) {
                byte[] arrby = Param.bytChatSubTeamNumCount;
                arrby[0] = (byte) (arrby[0] + 1);
            }
            if (tmpObj.bytTeamId != 2) {
                continue;
            }
            byte[] arrby = Param.bytChatSubTeamNumCount;
            arrby[1] = (byte) (arrby[1] + 1);
        }
    }

    public void RankTeam() {
        for (int i = 0; i < Param.getInstance().vTeam.size(); ++i) {
            TeamObject tmpObj = (TeamObject) Param.getInstance().vTeam.elementAt(i);
            if (tmpObj.bytTroopRank != 3) {
                continue;
            }
            Param.getInstance().vTeam.removeElementAt(i);
            Param.getInstance().vTeam.insertElementAt((Object) tmpObj, 0);
        }
        GameUI.getInstance().addTeamer();
    }

    public void MasterPlayerList(Vector _tempV) {
        Enumeration enumPlayer = _tempV.elements();
        Param.bytChatSubMasterNumCount = null;
        Param.bytChatSubMasterNumCount = new byte[2];
        while (enumPlayer.hasMoreElements()) {
            TeamObject tmpObj = (TeamObject) enumPlayer.nextElement();
            if (tmpObj.bytRelation == 1) {
                byte[] arrby = Param.bytChatSubMasterNumCount;
                arrby[0] = (byte) (arrby[0] + 1);
            }
            if (tmpObj.bytRelation != 2) {
                continue;
            }
            byte[] arrby = Param.bytChatSubMasterNumCount;
            arrby[1] = (byte) (arrby[1] + 1);
        }
    }

    public void addElement(Vector vecTarget, TeamObject _team) {
        TeamObject Obj;
        int index;
        _team.priority = this.getPriority(_team.blnIsOnLine ? 1 : 0, _team.bytTroopRank, _team.shtLevel);
        int vecSize = vecTarget.size();
        for (index = 0; index < vecSize; ++index) {
            Obj = (TeamObject) vecTarget.elementAt(index);
            if (_team.intId != Obj.intId) {
                continue;
            }
            vecTarget.removeElementAt(index);
            break;
        }
        if (vecSize == 0) {
            vecTarget.addElement((Object) _team);
        } else {
            for (index = 0; index < vecSize; ++index) {
                Obj = (TeamObject) vecTarget.elementAt(index);
                if (_team.priority > Obj.priority) {
                    vecTarget.insertElementAt((Object) _team, index);
                    break;
                }
                if (index != vecSize - 1) {
                    continue;
                }
                vecTarget.addElement((Object) _team);
                break;
            }
        }
    }

    private int getPriority(int online, int occ, int level) {
        return (online << 8 & this._MaxTemp) + (occ << 6 & this._MaxTemp) + (level & this._MaxTemp);
    }

    public void readMapData(DataInputStream dis) throws IOException {
        byte i;
        short _unpassLenght;
        int _MapGroundLength;
        byte _InNum;
        Map.getInstance().initMap();
        Map.getInstance().shtMapId = dis.readShort();
        Map.getInstance().bytMapClanType = dis.readByte();
        DebugFrame.getInstance().logIn("Số map:" + Map.getInstance().shtMapId);
        Map.getInstance().shtSmallMapId = dis.readShort();
        Map.getInstance().shtSmallMapId = (short) (Map.getInstance().shtSmallMapId + 1000);
        DebugFrame.getInstance().logIn("Số minimap:" + Map.getInstance().shtSmallMapId);
        short _MaterialImageId = dis.readShort();
        DebugFrame.getInstance().logIn("Tile id:" + _MaterialImageId);
        if (_MaterialImageId > 0) {
            Image _img = ImageManager.CreateImage(String.valueOf((int) _MaterialImageId), "tile");
            Map.getInstance().creatMaterialImg(_img);
        } else {
            Map.getInstance().creatMaterialImg(null);
        }
        byte _weatherid = dis.readByte();
        Param.getInstance().peffect = null;
        byte _weatherdirection = dis.readByte();
        if (Rms.bytEffect == 0 && _weatherid != 0 && Rms.blnWeather) {
            if (Param.getInstance().peffect == null) {
                Param.getInstance().peffect = new Peffect();
            }
            Param.getInstance().peffect.setPeffect(_weatherid, _weatherdirection);
        }
        String _Name = dis.readUTF();
        byte _Column = dis.readByte();
        byte _Row = dis.readByte();
        byte _IsPk = dis.readByte();
        dis.readByte();
        byte _BornX = dis.readByte();
        byte _BornY = dis.readByte();
        DebugFrame.getInstance().logIn("Bản đồ điểm xuất hiện " + _BornX + "  " + _BornY);
        Map.getInstance().setMapInfo(_Column, _Row);
        Map.getInstance().strName = _Name;
        Map.getInstance().bytPk = _IsPk;
        Map.getInstance().bytBornX = _BornX;
        Map.getInstance().bytBornY = _BornY;
        DebugFrame.getInstance().logIn("Bản đồ PK" + _IsPk);
        int lenth = _MapGroundLength = dis.readShort();
        byte[] _MapGroundData = new byte[_MapGroundLength];
        byte[] _MapGroundTransformData = new byte[_MapGroundLength];
        for (int i2 = 0; i2 < lenth; ++i2) {
            _MapGroundData[i2] = dis.readByte();
            _MapGroundTransformData[i2] = dis.readByte();
        }
        Map.getInstance().setMapGround(_MapGroundData);
        Map.getInstance().setMapGroundTransform(_MapGroundTransformData);
        byte _OutNum = dis.readByte();
        if (_OutNum > 0) {
            short[][] _OutData = new short[_OutNum][3];
            String[] _OutName = new String[_OutNum];
            byte[] _OutDirection = new byte[_OutNum];
            byte[] _conceal = new byte[_OutNum];
            for (i = 0; i < _OutNum; i = (byte) (i + 1)) {
                byte _direction;
                short _OutMapId;
                _OutData[i][0] = dis.readByte();
                _OutData[i][1] = dis.readByte();
                _OutDirection[i] = _direction = dis.readByte();
                _OutData[i][2] = _OutMapId = dis.readShort();
                _OutName[i] = dis.readUTF();
                _conceal[i] = dis.readByte();
            }
            Map.getInstance().setOutJumpPoint(_OutData, _OutName, _OutDirection, _conceal);
        }
        if ((_InNum = dis.readByte()) > 0) {
            byte[][] _InData = new byte[_InNum][4];
            for (byte i3 = 0; i3 < _InNum; i3 = (byte) (i3 + 1)) {
                _InData[i3][0] = dis.readByte();
                _InData[i3][1] = dis.readByte();
                _InData[i3][2] = dis.readByte();
                _InData[i3][3] = dis.readByte();
            }
            Map.getInstance().setInJumpPoint(_InData);
        }
        byte _MsgLength = dis.readByte();
        for (byte i4 = 0; i4 < _MsgLength; i4 = (byte) (i4 + 1)) {
            dis.readByte();
            dis.readByte();
            dis.readUTF();
        }
        byte _MapCartoonNum = dis.readByte();
        if (_MapCartoonNum > 0) {
            byte[][] _MapCartoonData = new byte[_MapCartoonNum][2];
            for (i = 0; i < _MapCartoonNum; i = (byte) (i + 1)) {
                _MapCartoonData[i][0] = dis.readByte();
                _MapCartoonData[i][1] = (byte) (dis.readByte() + 1);
            }
            Map.getInstance().setMapCartoon(_MapCartoonData);
        }
        if ((_unpassLenght = dis.readShort()) > 0) {
            for (short m = 0; m < _unpassLenght; m = (short) (m + 1)) {
                byte _x = dis.readByte();
                byte _y = dis.readByte();
                Map.getInstance().setMapEvent(_y, _x, (short) -1);
            }
        }
        short _shtAreaMapId = dis.readShort();
        String _strAreaMapName = dis.readUTF();
        Map.getInstance().setAreaMap(_shtAreaMapId, _strAreaMapName);
        Map.getInstance().bytIsCity = dis.readByte();
    }

    public void sendSecondData() {
        Macro.shtGameDataReceived = 0;
        if (Macro.bytGameType == 1) {
            LoadingUI.getInstance().setSpeed(90);
            ORPMe.ME.setTile(Map.getInstance().bytBornX, Map.getInstance().bytBornY);
            ORPMe.ME.s_htRoleParts.clear();
            ORPMe.ME.addAllRolePartData(ORPMe.ME);
            if (Macro.BLN_LEVEL_FUNCTION_EQUIP) {
                ResPoolLevelFunction.getInstance().bindEquip(ORPMe.ME, ORPMe.ME.shtEquipId, ORPMe.ME.shtEquipAnuId);
            }
            if (Macro.BLN_LEVEL_FUNCTION_WEAPON) {
                ResPoolLevelFunction.getInstance().bindWeapon(ORPMe.ME, ORPMe.ME.shtWeaponId, ORPMe.ME.shtWeaponAnuId);
            }
            GameUI.getInstance().addObject(ORPMe.ME);
            Map.getInstance().setCameraTileXY(ORPMe.ME.bytTileX, ORPMe.ME.bytTileY);
            LoadingUI.getInstance().setSpeed(100);
            NetSend.getInstance().sendSecondDataIn();
        } else {
            LoadingUI.getInstance().setSpeed(50);
            NetSend.getInstance().sendSecondData();
        }
    }

    public void readMeData(DataInputStream dis) throws IOException {
        int _PHPMax;
        String _PConsortiaName;
        int _PHP;
        byte _PCountry;
        String _PNickName;
        int _PMPMax;
        short _PLevel;
        byte _PIsMale;
        byte _POccupation;
        int _PMP;
        int _gold;
        int _PEXP;
        int _PNEXTEXP;
        ORPMe _orm = new ORPMe();
        _orm.newPlayer(Map.getInstance().bytBornX, Map.getInstance().bytBornY);
        _orm.intUserId = dis.readInt();
        _orm.strNickName = _PNickName = dis.readUTF();
        _orm.shtLevel = _PLevel = dis.readShort();
        GameUI.getInstance().bytMeLevel = GameUI.getInstance().getBytes(_orm.shtLevel);
        ORPMe.Gold = _gold = dis.readInt();
        _orm.bytIsMale = _PIsMale = dis.readByte();
        _orm.bytCountry = _PCountry = dis.readByte();
        _orm.blnCountry = true;
        _orm.strConsortia = _PConsortiaName = dis.readUTF();
        _orm.societyName = dis.readUTF();
        byte _relation = dis.readByte();
        if (_relation == 1) {
            _orm.MasterName = dis.readUTF();
            byte _num = dis.readByte();
            String[] discipleName = new String[_num];
            for (byte i = 0; i < _num; i = (byte) (i + 1)) {
                discipleName[i] = dis.readUTF();
                _orm.appreticeName = String.valueOf((Object) _orm.appreticeName) + discipleName[i] + "  ";
            }
        }
        _orm.bytOccupation = _POccupation = dis.readByte();
        _orm.intHP = _PHP = dis.readInt();
        _orm.intHPMax = _PHPMax = dis.readInt();
        byte _MPtype = dis.readByte();
        DebugFrame.getInstance().logIn("ME:_MPtype->" + _MPtype);
        _orm.blnUseMp = true;
        _orm.intMP = _PMP = dis.readInt();
        _orm.intMPMax = _PMPMax = dis.readInt();
        _orm.EXP = _PEXP = dis.readInt();
        _orm.NextEXP = _PNEXTEXP = dis.readInt();
        ORPMe.ME.ShowEXP = dis.readInt();
        ORPMe.ME.ShowNextEXP = dis.readInt();
        _orm.shtImmobilityTimeMax = dis.readShort();
        _orm.bytAtkDistance = dis.readByte();
        DCanvas.getInstance().setCreateRole(_orm);
        _orm.setRolePortrait(_orm.getPortraitID(_PCountry, _PIsMale));
        _orm.shtEquipRealLevel = 0;
        short _PClothesId = dis.readShort();
        short _PClothesAnuId = dis.readShort();
        byte _zhongzu = dis.readByte();
        short _equipimgId = dis.readShort();
        short _equipanuId = dis.readShort();
        if (Macro.BLN_LEVEL_FUNCTION_EQUIP) {
            ResPoolLevelFunction.getInstance().bindEquip(_orm, _PClothesId, _PClothesAnuId);
        } else {
            _orm.addRoleRes(2, _PClothesId, _PClothesAnuId);
        }
        _orm.addRoleRes(7, _equipimgId, _equipanuId);
        short _headWreaId = dis.readShort();
        short _headWreaAnuId = dis.readShort();
        byte _headzhuongzu = dis.readByte();
        _orm.addRoleRes(-1, _headWreaId, _headWreaAnuId);
        _orm.shtWeaponRealLevel = 0;
        byte equipFlag = dis.readByte();
        if (equipFlag > 0) {
            short _PEquipment = dis.readShort();
            short _PEquipmentAnu = dis.readShort();
            short _inlayimgId = dis.readShort();
            short _inlayanuId = dis.readShort();
            short _effectAttack = dis.readShort();
            short _effectAttackAnu = dis.readShort();
            _orm.addRoleRes(8, _effectAttack, _effectAttackAnu);
            short _weaponType = dis.readShort();
            _orm.bytWeaponPart = ORole.transWeaponType(_weaponType);
            if (Macro.BLN_LEVEL_FUNCTION_WEAPON) {
                ResPoolLevelFunction.getInstance().bindWeapon(_orm, _PEquipment, _PEquipmentAnu);
            } else {
                _orm.addRoleRes(4, _PEquipment, _PEquipmentAnu);
            }
            _orm.addRoleRes(9, _inlayimgId, _inlayanuId);
        } else if (Macro.BLN_LEVEL_FUNCTION_WEAPON) {
            ResPoolLevelFunction.getInstance().bindWeapon(_orm, (short) -1, (short) -1);
        } else {
            _orm.addRoleRes(4, (short) -1, (short) -1);
        }
        byte _teach = dis.readByte();
        if (_teach != 1) {
            int _num = dis.readByte();
            DebugFrame.getInstance().logIn("Số lượng thú nuôi:" + _num);
            Hashtable _temp = null;
            _temp = new Hashtable();
            if (_num > 0) {
                for (int i = 0; i < _num; ++i) {
                    int skillcd;
                    short ptype;
                    int _petid;
                    byte show;
                    PackageObject _po = new PackageObject();
                    _po.intPOindex = i + 8;
                    _po.petShow = show = dis.readByte();
                    _po.petKey = _petid = dis.readInt();
                    ORPlayer.itSelectPetId = _petid;
                    short _imgId = dis.readShort();
                    short _anuId = dis.readShort();
                    _po.shtType = ptype = dis.readShort();
                    short function = dis.readShort();
                    if (ptype != 1) {
                        _orm.setPet(_petid, _imgId, _anuId, ptype);
                    }
                    int sn = dis.readByte();
                    Macro.petskillcount = sn;
                    Macro.petskill = new int[sn];
                    for (int j = 0; j < sn; ++j) {
                        int skillid = dis.readInt();
                        skillcd = dis.readInt();
                        Macro.petskill[j] = skillid;
                    }
                    int pn = dis.readByte();
                    for (int j = 0; j < pn; ++j) {
                        skillcd = dis.readInt();
                    }
                    _temp.put((Object) new Integer(_po.intPOindex), (Object) _po);
                }
                _orm.hPackagePet = _temp;
            }
        }
        _orm.bytDirection = (byte) 2;
        _orm.pushTask((byte) 0, _orm.bytDirection);
        _orm.roleTaskAction(0);
        GameUI.getInstance().addObject(_orm);
        byte _Stall = dis.readByte();
        if (_Stall == 1) {
            String _str = dis.readUTF();
            DebugFrame.getInstance().logIn("_str = " + _str);
            _orm.setStall(_str);
        }
        _orm.spouse = dis.readUTF();
        _orm.skillPoints = dis.readShort();
    }

    public ORPlayer readPlayerData(DataInputStream dis, boolean _type) throws IOException {
        short _clothsLevel;
        boolean _att;
        ORPlayer _orp = new ORPlayer();
        _orp.intUserId = dis.readInt();
        _orp.strNickName = dis.readUTF();
        _orp.shtLevel = dis.readShort();
        _orp.bytIsMale = dis.readByte();
        _orp.bytCountry = dis.readByte();
        _orp.blnCountry = _orp.bytCountry == ORPMe.ME.bytCountry;
        _orp.spouse = dis.readUTF();
        _orp.strConsortia = dis.readUTF();
        _orp.societyName = dis.readUTF();
        byte _relation = dis.readByte();
        if (_relation == 1) {
            _orp.MasterName = dis.readUTF();
            byte _num = dis.readByte();
            String[] discipleName = new String[_num];
            for (byte i = 0; i < _num; i = (byte) (i + 1)) {
                discipleName[i] = dis.readUTF();
                _orp.appreticeName = String.valueOf((Object) _orp.appreticeName) + discipleName[i] + "   ";
            }
        }
        _orp.bytOccupation = dis.readByte();
        _orp.intHP = dis.readInt();
        _orp.intHPMax = dis.readInt();
        byte _MPtype = dis.readByte();
        DebugFrame.getInstance().logIn("_MPtype:" + _MPtype);
        _orp.blnUseMp = true;
        _orp.intMP = dis.readInt();
        _orp.intMPMax = dis.readInt();
        byte _cellx = dis.readByte();
        byte _celly = dis.readByte();
        _orp.newPlayer(_cellx, _celly);
        byte _direction = dis.readByte();
        DCanvas.getInstance().setCreateRole(_orp);
        _orp.setRolePortrait(_orp.getPortraitID(_orp.bytCountry, _orp.bytIsMale));
        _orp.shtEquipRealLevel = _clothsLevel = dis.readShort();
        short _clothesId = dis.readShort();
        short _clothesanuId = dis.readShort();
        byte _clothes = dis.readByte();
        if (Macro.BLN_LEVEL_FUNCTION_EQUIP) {
            ResPoolLevelFunction.getInstance().bindEquip(_orp, _clothesId, _clothesanuId);
        } else {
            _orp.addRoleRes(2, _clothesId, _clothesanuId);
        }
        short _equipimgId = dis.readShort();
        short _equipanuId = dis.readShort();
        _orp.addRoleRes(7, _equipimgId, _equipanuId);
        short _headWreaId = dis.readShort();
        short _headWreAnuId = dis.readShort();
        byte _headzhuongzu = dis.readByte();
        _orp.addRoleRes(-1, _headWreaId, _headWreAnuId);
        byte equipFlag = dis.readByte();
        if (equipFlag > 0) {
            short _weaponLevel;
            _orp.shtWeaponRealLevel = _weaponLevel = dis.readShort();
            short _equipment = dis.readShort();
            short _equipmentAnu = dis.readShort();
            short _effectAttack = dis.readShort();
            short _effectAttackAnu = dis.readShort();
            _orp.addRoleRes(8, _effectAttack, _effectAttackAnu);
            short _weaponType = dis.readShort();
            _orp.bytWeaponPart = ORole.transWeaponType(_weaponType);
            short _inlayimgId = dis.readShort();
            short _inlayanuId = dis.readShort();
            _orp.addRoleRes(9, _inlayimgId, _inlayanuId);
            if (Macro.BLN_LEVEL_FUNCTION_WEAPON) {
                ResPoolLevelFunction.getInstance().bindWeapon(_orp, _equipment, _equipmentAnu);
            } else {
                _orp.addRoleRes(4, _equipment, _equipmentAnu);
            }
        } else if (Macro.BLN_LEVEL_FUNCTION_WEAPON) {
            ResPoolLevelFunction.getInstance().bindWeapon(_orp, (short) -1, (short) -1);
        } else {
            _orp.addRoleRes(4, (short) -1, (short) -1);
        }
        _orp.blnIsAttack = _att = Map.getInstance().checkAttack(_orp.bytCountry);
        if (_type) {
            byte isLive = dis.readByte();
            byte isSee = dis.readByte();
            if (isLive == 0) {
                _orp.pushTask((byte) 5, _direction);
            } else {
                if (isSee == 0) {
                    GameUI.getInstance().setRoleEffect_Conceal(_orp);
                }
                _orp.pushTask((byte) -1, _direction);
            }
        } else {
            byte isSee = dis.readByte();
            if (isSee == 0) {
                GameUI.getInstance().setRoleEffect_Conceal(_orp);
            }
            _orp.pushTask((byte) -1, _direction);
        }
        int _num = dis.readByte();
        Hashtable _temp = null;
        _temp = new Hashtable();
        for (int i = 0; i < _num; ++i) {
            short ptype;
            int _petid;
            byte show;
            int skillcd;
            PackageObject _po = new PackageObject();
            _po.petShow = show = dis.readByte();
            _po.petKey = _petid = dis.readInt();
            ORPlayer.itSelectPetId = _petid;
            short _imgId = dis.readShort();
            short _imgAnuId = dis.readShort();
            _po.shtType = ptype = dis.readShort();
            short function = dis.readShort();
            if (ptype != 1) {
                _orp.setPet(_petid, _imgId, _imgAnuId, ptype);
            }
            int sn = dis.readByte();
            Macro.petskillcount = sn;
            Macro.petskill = new int[sn];
            for (int j = 0; j < sn; ++j) {
                int skillid = dis.readInt();
                skillcd = dis.readInt();
                Macro.petskill[j] = skillid;
            }
            int pn = dis.readByte();
            for (int j = 0; j < pn; ++j) {
                skillcd = dis.readInt();
            }
            _temp.put((Object) new Integer(_po.intPOindex), (Object) _po);
        }
        _orp.hPackagePet = _temp;
        byte _Stall = dis.readByte();
        DebugFrame.getInstance().logIn("\u73a9\u5bb6\u6446\u644a:" + _Stall);
        if (_Stall == 1) {
            String _str = dis.readUTF();
            DebugFrame.getInstance().logIn("\u73a9\u5bb6\u5e97\u540d = " + _str);
            _orp.setStall(_str);
        }
        if (!_orp.strNickName.equals((Object) ORPMe.ME.strNickName)) {
            GameUI.getInstance().addObject(_orp);
            if (Param.getInstance().vTeam != null) {
                GameUI.getInstance().updateTeamHP(_orp, _orp.intHP, _orp.intHPMax);
                GameUI.getInstance().updateTeamMP(_orp, _orp.intMP, _orp.intMPMax);
            }
        }
        return _orp;
    }

    public ORPlayer readPartnerDate(DataInputStream dis, byte _relation) throws IOException {
        short _clothsLevel;
        ORPlayer _orp = new ORPlayer();
        _orp.PARTNER_relation = _relation;
        _orp.strNickName = dis.readUTF();
        _orp.shtLevel = dis.readShort();
        _orp.bytIsMale = dis.readByte();
        _orp.bytCountry = dis.readByte();
        _orp.bytOccupation = dis.readByte();
        _orp.strConsortia = dis.readUTF();
        _orp.PARTNER_loves_value = dis.readInt();
        _orp.PARTNER_loves_days = dis.readInt();
        _orp.PARTNER_loves_ranking = dis.readInt();
        _orp.PARTNER_loves_Lv = dis.readUTF();
        _orp.newPlayer((byte) 10, (byte) 10);
        DCanvas.getInstance().setCreateRole(_orp);
        _orp.shtEquipRealLevel = _clothsLevel = dis.readShort();
        short _clothesId = dis.readShort();
        short _clothesanuId = dis.readShort();
        byte _clothes = dis.readByte();
        _orp.addRoleRes(2, _clothesId, _clothesanuId);
        short _headWreaId = dis.readShort();
        short _headWreAnuId = dis.readShort();
        byte _head = dis.readByte();
        _orp.addRoleRes(-1, _headWreaId, _headWreAnuId);
        byte equipFlag = dis.readByte();
        if (equipFlag == 1) {
            short _weaponLevel;
            _orp.shtWeaponRealLevel = _weaponLevel = dis.readShort();
            short _equipment = dis.readShort();
            short _equipmentAnu = dis.readShort();
            byte _ef = dis.readByte();
            _orp.addRoleRes(4, _equipment, _equipmentAnu);
            short _effectAttack = dis.readShort();
            short _effectAttackAnu = dis.readShort();
            _orp.addRoleRes(8, _effectAttack, _effectAttackAnu);
            short _weaponType = dis.readShort();
            _orp.bytWeaponPart = ORole.transWeaponType(_weaponType);
        } else {
            _orp.addRoleRes(4, (short) -1, (short) -1);
        }
        Param.getInstance().IfLoad = true;
        return _orp;
    }

    public ONpc readNpcData(DataInputStream dis, short l) throws IOException {
        ONpc _orn = new ONpc();
        _orn.intUserId = dis.readInt();
        DebugFrame.getInstance().logIn("ID:" + _orn.intUserId);
        _orn.strOccupation = dis.readUTF();
        _orn.strNickName = dis.readUTF();
        DebugFrame.getInstance().logIn("\u6635\u79f0:" + _orn.strNickName);
        byte _bc = dis.readByte();
        _orn.blnCountry = _bc == 0 || _bc == ORPMe.ME.bytCountry;
        _orn.strTALK = dis.readUTF();
        byte _chat = dis.readByte();
        if (_chat == 1) {
            String _str = dis.readUTF();
            if (Rms.bytEffect == 0) {
                GameUI.getInstance().bytHaveNpcChat = (byte) (GameUI.getInstance().bytHaveNpcChat + 1);
                _orn.setChat(_str);
            }
        }
        _orn.blnIsTalk = dis.readByte() == 1;
        byte _cellx = dis.readByte();
        byte _celly = dis.readByte();
        _orn.newPlayer(_orn.intUserId, _cellx, _celly);
        byte _getPic = dis.readByte();
        if (_getPic == 1) {
            short[] _p = new short[9];
            _p[0] = dis.readShort();
            _p[1] = dis.readShort();
            _p[2] = 0;
            _p[3] = dis.readByte();
            _p[4] = dis.readShort();
            _p[5] = dis.readByte();
            ImageManager.getInstance().addNpcImage(_p);
        }
        byte _picType = dis.readByte();
        DebugFrame.getInstance().logIn("NPC \u52a8\u753b\u7c7b\u578b  1- \u9759\u6001\u56fe  2\u5355\u5fc3\u8df3  3 \u591a\u65b9\u5411:" + _picType);
        short _picId = dis.readShort();
        short _anuid = dis.readShort();
        DebugFrame.getInstance().logIn("NPC _picId:" + _picId + "_anuid:" + _anuid);
        _orn.setRoleFrame(_orn.intUserId, _picId, _anuid, _picType == 3);
        Map.getInstance().bytsNpcXY[l][0] = _celly;
        Map.getInstance().bytsNpcXY[l][1] = _cellx;
        GameUI.getInstance().addObject(_orn);
        return _orn;
    }

    public void readMonsterData(DataInputStream dis, boolean _type) throws IOException {
        byte _direction;
        byte isSee;
        ORMonster _ors = new ORMonster();
        _ors.intUserId = dis.readInt();
        _ors.strNickName = dis.readUTF();
        DebugFrame.getInstance().logIn("\u52a0\u8f7d\u602a\u7269id = " + _ors.strNickName);
        _ors.shtLevel = dis.readShort();
        _ors.bytCountry = dis.readByte();
        _ors.blnCountry = _ors.bytCountry == ORPMe.ME.bytCountry;
        _ors.blnInitiative = dis.readByte() == 0;
        _ors.bytOccupation = dis.readByte();
        _ors.blnIsBoss = dis.readByte() == 2;
        _ors.bytPhyleType = dis.readByte();
        _ors.blnIsabsorb = dis.readByte() == 1;
        _ors.intHP = dis.readInt();
        _ors.intHPMax = dis.readInt();
        _ors.intMP = dis.readInt();
        _ors.intMPMax = dis.readInt();
        byte _cellx = dis.readByte();
        byte _celly = dis.readByte();
        _ors.newPlayer(_cellx, _celly);
        _ors.bytDirection = _direction = dis.readByte();
        byte _isExist = dis.readByte();
        if (_isExist == 1) {
            short[] _p = new short[11];
            _p[0] = dis.readShort();
            _p[1] = dis.readShort();
            _p[2] = 0;
            _p[3] = dis.readByte();
            _p[4] = dis.readShort();
            _p[5] = dis.readByte();
            ImageManager.getInstance().addMonsterImage(_p);
            this.readSprite(dis, 3, _ors.intUserId, _p[0], _p[1], "orge");
        }
        short _picID = dis.readShort();
        short _anuID = dis.readShort();
        DebugFrame.getInstance().logIn("_picID:" + _picID);
        DebugFrame.getInstance().logIn("_anuID:" + _anuID);
        _ors.setRoleFrame(_ors.intUserId, _picID, _anuID);
        GameUI.getInstance().addObject(_ors);
        _ors.blnIsAttack = _ors.bytCountry != ORPMe.ME.bytCountry;
        String _str = null;
        if (_str != null) {
            _ors.setChat(_str);
            GameUI.getInstance().blnHaveMonsterChat = true;
        }
        _ors.pushTask((byte) 0, _direction);
        if (_type && (isSee = dis.readByte()) == 0) {
            _ors.pushTask((byte) 3, (byte) 4);
        }
    }

    public void readOtherData(DataInputStream dis) throws IOException {
        short _NpcNum;
        short _monsterNum;
        byte _PicNum;
        short _otherPlayerNum = dis.readShort();
        if (_otherPlayerNum > 0) {
            for (short i = 0; i < _otherPlayerNum; i = (short) (i + 1)) {
                ORPlayer oRPlayer = this.readPlayerData(dis, true);
            }
        }
        if (Macro.BLN_LEVEL_FUNCTION_EQUIP) {
            ResPoolLevelFunction.getInstance().LevelFunction_update((byte) 1);
        }
        if (Macro.BLN_LEVEL_FUNCTION_WEAPON) {
            ResPoolLevelFunction.getInstance().LevelFunction_update((byte) 2);
        }
        if ((_PicNum = dis.readByte()) > 0) {
            for (byte l = 0; l < _PicNum; l = (byte) (l + 1)) {
                short[] _p = new short[]{dis.readShort(), dis.readShort(), 0, dis.readByte(), dis.readShort(), dis.readByte()};
                ImageManager.getInstance().addNpcImage(_p);
            }
        }
        if ((_NpcNum = dis.readShort()) > 0) {
            GameControl.getInstance().initCreateNpc(_NpcNum);
            for (short l = 0; l < _NpcNum; l = (short) (l + 1)) {
                this.readNpcData(dis, l);
            }
            if (Macro.BLN_ACCERATE_NPC_TASK && Param.getInstance().htRoleNPC != null && !Param.getInstance().htRoleNPC.isEmpty()) {
                Enumeration e = Param.getInstance().htRoleNPC.elements();
                while (e.hasMoreElements()) {
                    ONpc on = (ONpc) e.nextElement();
                    if (!Macro.BLN_ACCERATE_NPC_TASK || on == null) {
                        continue;
                    }
                    NetSend.getInstance().sendNpcOption(on.intUserId);
                }
            }
        }
        if ((_PicNum = dis.readByte()) > 0) {
            for (byte l = 0; l < _PicNum; l = (byte) (l + 1)) {
                short[] _p = new short[]{dis.readShort(), dis.readShort(), 0, dis.readByte(), dis.readShort(), dis.readByte()};
                ImageManager.getInstance().addMonsterImage(_p);
            }
        }
        if ((_monsterNum = dis.readShort()) > 0) {
            Param.getInstance().htRoleMonster = new Hashtable((int) _monsterNum);
            for (short n = 0; n < _monsterNum; n = (short) (n + 1)) {
                this.readMonsterData(dis, true);
            }
        }
    }

    public void readDefaultPlayerEquip(DataInputStream dis) throws IOException {
        for (int occ = 0; occ < 4; ++occ) {
            byte occIndex = dis.readByte();
            Macro.GAME_UI_LEVEL_DEFAULT_EQUIP[occIndex - 1][0] = dis.readShort();
            Macro.GAME_UI_LEVEL_DEFAULT_EQUIP[occIndex - 1][0] = dis.readShort();
        }
        for (int weapon = 0; weapon < 5; ++weapon) {
            short WeaponType = (short) (dis.readShort() - 601);
            Macro.GAME_UI_LEVEL_DEFAULT_WEAPON_RES[WeaponType][0] = dis.readShort();
            Macro.GAME_UI_LEVEL_DEFAULT_WEAPON_RES[WeaponType][0] = dis.readShort();
        }
    }

    public PackageObject readBookData(DataInputStream dis) throws IOException {
        PackageObject po = new PackageObject();
        po.intId = dis.readInt();
        if (po.intId > 0) {
            po.shtIcon = dis.readShort();
            po.bytQuality = dis.readByte();
            po.shtAddition = dis.readShort();
            po.strName = dis.readUTF();
            po.strInfo = dis.readUTF();
            GameUI.getInstance().addObjectImg(po.shtIcon, "prop");
        }
        Param.getInstance().vSkillBook.addElement((Object) po);
        return po;
    }

    public SkillObject readActiveSkillData(DataInputStream dis) throws IOException {
        SkillObject so = new SkillObject();
        so.blnPassivity = false;
        so.intId = dis.readInt();
        so.strName = dis.readUTF();
        so.bytLv = dis.readByte();
        so.bytRank = dis.readByte();
        so.shtIcon = dis.readShort();
        so.strInfo = dis.readUTF();
        byte isNextSkill = dis.readByte();
        so.strNextInfo = isNextSkill == 1 ? dis.readUTF() : "";
        so.bytNeedPoints = dis.readShort();
        so.intNeedMoney = dis.readInt();
        so.bytCorrOcc = dis.readByte();
        ORPMe.ME.setOccupation(so.bytCorrOcc);
        so.bytSkillType = dis.readByte();
        so.shtCDType = dis.readShort();
        so.intCDTimeMax = dis.readInt() * 1000;
        so.intCDTime = dis.readInt() * 1000;
        so.shtUseTime = dis.readShort();
        SkillObject.resetMagicContinuous(so);
        int len = dis.readByte();
        so.shtWeapon = new short[len];
        for (int i = 0; i < len; ++i) {
            so.shtWeapon[i] = ORole.transWeaponType(dis.readShort());
        }
        so.bytSelect = dis.readByte();
        so.bytUserType = dis.readByte();
        so.bytAtkDistance = dis.readByte();
        so.shtHP = dis.readShort();
        so.byteActive = 0;
        short _mp = dis.readShort();
        byte _fp = dis.readByte();
        byte _gp = dis.readByte();
        if (_mp != 0) {
            so.shtMp = _mp;
            so.bytMpType = 0;
        } else if (_fp != 0) {
            so.shtMp = _fp;
            so.bytMpType = 1;
        } else if (_gp != 0) {
            so.shtMp = _gp;
            so.bytMpType = (byte) 2;
        }
        so.bytSkillPicId = dis.readShort();
        so.bytSkillAnuId = dis.readShort();
        so.bytRoleAction = dis.readByte();
        so.byteLayer = dis.readByte();
        so.bytDisplayPosYOffType = dis.readByte();
        so.blnSkillMultiAnimation = dis.readByte() == 1;
        Param.getInstance().vSkillList.addElement((Object) so);
        return so;
    }

    public void readPassiveSkillData(DataInputStream dis) throws IOException {
        SkillObject so = new SkillObject();
        so.blnPassivity = true;
        so.intId = dis.readInt();
        so.strName = dis.readUTF();
        so.bytLv = dis.readByte();
        so.bytRank = dis.readByte();
        so.shtIcon = dis.readShort();
        so.strInfo = dis.readUTF();
        byte isNextSkill = dis.readByte();
        so.strNextInfo = isNextSkill == 1 ? dis.readUTF() : "";
        so.bytNeedPoints = dis.readShort();
        so.intNeedMoney = dis.readInt();
        so.bytCorrOcc = dis.readByte();
        ORPMe.ME.setOccupation(so.bytCorrOcc);
        Param.getInstance().vSkillPassivityList.addElement((Object) so);
    }

    public MenuObject readMenuListData(DataInputStream dis, byte _typeNum, MenuObject _mo, boolean isSimulater) throws IOException {
        Param.getInstance().intNpcNum = 0;
        if (_mo == null) {
            _mo = new MenuObject();
        }
        if (_typeNum == 3) {
            _mo.bytType = 1;
            _mo.strInfo = dis.readUTF();
        } else if (_typeNum == 4) {
            _mo.bytType = (byte) 5;
            _mo.strInfo = dis.readUTF();
            if (!isSimulater) {
                Param.getInstance().strNpcInputTitle = _mo.strInfo;
            }
            _mo.intMin = dis.readByte();
            if (!isSimulater) {
                Param.getInstance().bytNpcInputMin = (byte) _mo.intMin;
            }
            _mo.intMax = dis.readByte();
            if (!isSimulater) {
                Param.getInstance().bytNpcInputMax = (byte) _mo.intMax;
            }
        } else if (_typeNum == 5) {
            _mo.bytType = (byte) 4;
            byte _type = dis.readByte();
            _mo.strInfo = dis.readUTF();
            if (_type == 1) {
                _mo.intMin = dis.readInt();
                _mo.intMax = dis.readInt();
            } else {
                _mo.intMin = -1;
                _mo.intMax = -1;
            }
        }
        return _mo;
    }

    public Hashtable readPackageData(DataInputStream dis, byte _readType, boolean _bln) throws IOException {
        Hashtable _package;
        block9:
        {
            byte _objectNum;
            block14:
            {
                block13:
                {
                    block12:
                    {
                        block11:
                        {
                            block10:
                            {
                                block8:
                                {
                                    _objectNum = dis.readByte();
                                    if (_objectNum == 0) {
                                        return null;
                                    }
                                    _package = new Hashtable((int) _objectNum);
                                    if (_readType != 1) {
                                        break block8;
                                    }
                                    for (byte i = 0; i < _objectNum; i = (byte) (i + 1)) {
                                        PackageObject po = this.readMeEquipData(dis);
                                        _package.put((Object) new Integer(po.intPOindex), (Object) po);
                                    }
                                    break block9;
                                }
                                if (_readType != 2) {
                                    break block10;
                                }
                                for (byte i = 0; i < _objectNum; i = (byte) (i + 1)) {
                                    PackageObject po = this.readNpcEquipData(dis);
                                    _package.put((Object) new Integer(po.intPOindex), (Object) po);
                                }
                                break block9;
                            }
                            if (_readType != 3) {
                                break block11;
                            }
                            for (byte i = 0; i < _objectNum; i = (byte) (i + 1)) {
                                PackageObject po = this.readMeGoodsData(dis, true);
                                _package.put((Object) new Integer(po.intPOindex), (Object) po);
                            }
                            break block9;
                        }
                        if (_readType != 4) {
                            break block12;
                        }
                        for (byte i = 0; i < _objectNum; i = (byte) (i + 1)) {
                            PackageObject po = this.readNpcGoodsData(dis);
                            _package.put((Object) new Integer(po.intPOindex), (Object) po);
                        }
                        break block9;
                    }
                    if (_readType != 5) {
                        break block13;
                    }
                    for (byte i = 0; i < _objectNum; i = (byte) (i + 1)) {
                        PackageObject po = this.readNpcDepotData(dis);
                        _package.put((Object) new Integer(po.intPOindex), (Object) po);
                    }
                    break block9;
                }
                if (_readType != 6) {
                    break block14;
                }
                for (byte i = 0; i < _objectNum; i = (byte) (i + 1)) {
                    PackageObject po = this.readDealGoodsData(dis, true);
                    _package.put((Object) new Integer(po.intPOindex), (Object) po);
                }
                break block9;
            }
            if (_readType != 7) {
                break block9;
            }
            for (byte i = 0; i < _objectNum; i = (byte) (i + 1)) {
                PackageObject po = this.readDealGoodsData(dis, false);
                _package.put((Object) new Integer(po.intPOindex), (Object) po);
            }
        }
        return _package;
    }

    public PackageObject readMeEquipData(DataInputStream dis) throws IOException {
        PackageObject po = new PackageObject();
        po.intPOindex = dis.readByte();
        po.intId = dis.readInt();
        po.shtIcon = dis.readShort();
        po.strName = dis.readUTF();
        DebugFrame.getInstance().logIn("\u540d\u79f0\uff1a" + po.strName);
        byte _partType = dis.readByte();
        this.readPropAttribute(dis, po, _partType);
        return po;
    }

    public PackageObject readMeGoodsData(DataInputStream dis, boolean _bln) throws IOException {
        try {
            PackageObject po = new PackageObject();
            po.intPOindex = dis.readByte();
            po.intId = dis.readInt();
            po.byteEquipType = (byte) -1;
            po.shtIcon = dis.readShort();
            po.strName = dis.readUTF();
            po.bytQuality = dis.readByte();
            po.intColor = Macro.INT_PROP_COLOR[po.bytQuality];
            po.shtPOnum = dis.readShort();
            po.bytPOnumMax = dis.readByte();
            po.intPrice = dis.readInt();
            po.shtLevel = dis.readShort();
            po.strInfo = dis.readUTF();
            po.bytOperation = dis.readByte();
            if (_bln) {
                po.blnUse = dis.readByte() == 1;
                po.bytKey = dis.readByte();
            }
            return po;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public MakeObject readMakeObject(DataInputStream dis) throws IOException {
        MakeObject _mo = new MakeObject();
        _mo.intId = dis.readInt();
        _mo.shtIcon = dis.readShort();
        _mo.strName = dis.readUTF();
        byte _bytQuality = dis.readByte();
        _mo.intColor = Macro.INT_PROP_COLOR[_bytQuality];
        _mo.bytType = dis.readByte();
        return _mo;
    }

    public PackageObject readNpcEquipData(DataInputStream dis) throws IOException {
        PackageObject po = new PackageObject();
        po.intPOindex = dis.readByte();
        po.intId = dis.readInt();
        po.shtIcon = dis.readShort();
        po.strName = dis.readUTF();
        this.get(dis, po);
        po.bytIsBind = dis.readByte();
        po.bytSeal = dis.readByte();
        po.shtStamina = dis.readShort();
        po.shtPOnum = dis.readShort();
        po.bytPOnumMax = dis.readByte();
        po.intPaiMaiPrice = po.intPrice = dis.readInt();
        return po;
    }

    public MakeObject readMakeDatum(DataInputStream dis) throws IOException {
        MakeObject _mo = new MakeObject();
        int _materialNum = dis.readByte();
        _mo.shtMaterialNameIcon = new short[_materialNum];
        _mo.strMaterialName = new String[_materialNum];
        _mo.shtMaterialNum = new short[_materialNum];
        _mo.intMaterialColor = new int[_materialNum];
        _mo.shtNowNum = new short[_materialNum];
        for (int k = 0; k < _materialNum; ++k) {
            _mo.shtMaterialNameIcon[k] = dis.readShort();
            GameUI.getInstance().addObjectImg(_mo.shtMaterialNameIcon[k], "prop");
            _mo.strMaterialName[k] = dis.readUTF();
            _mo.shtMaterialNum[k] = dis.readShort();
            byte _bytQuality = dis.readByte();
            _mo.intMaterialColor[k] = Macro.INT_PROP_COLOR[_bytQuality];
            _mo.shtNowNum[k] = dis.readShort();
        }
        return _mo;
    }

    public PackageObject readNpcGoodsData(DataInputStream dis) throws IOException {
        PackageObject po = new PackageObject();
        po.intPOindex = dis.readByte();
        po.intId = dis.readInt();
        po.shtIcon = dis.readShort();
        po.strName = dis.readUTF();
        po.bytQuality = dis.readByte();
        po.intColor = Macro.INT_PROP_COLOR[po.bytQuality];
        po.shtPOnum = dis.readShort();
        po.intPrice = dis.readInt();
        po.shtLevel = dis.readShort();
        po.bytBagPlace = po.bytPOnumMax = dis.readByte();
        po.strInfo = dis.readUTF();
        return po;
    }

    public PackageObject readNpcDepotData(DataInputStream dis) throws IOException {
        PackageObject po = new PackageObject();
        po.intPOindex = dis.readByte();
        po.intId = dis.readInt();
        po.shtIcon = dis.readShort();
        po.shtLevel = dis.readShort();
        po.strName = dis.readUTF();
        byte _type = dis.readByte();
        if (_type == 2) {
            this.get(dis, po);
            po.bytIsBind = dis.readByte();
            po.bytSeal = dis.readByte();
            po.shtStamina = dis.readShort();
        } else {
            po.bytQuality = dis.readByte();
            po.intColor = Macro.INT_PROP_COLOR[po.bytQuality];
            po.strInfo = dis.readUTF();
        }
        po.shtPOnum = dis.readShort();
        po.bytOperation = 1;
        return po;
    }

    public PackageObject readDealGoodsData(DataInputStream dis, boolean _bln) throws IOException {
        PackageObject po = new PackageObject();
        po.intPOindex = dis.readByte();
        po.intId = dis.readInt();
        po.shtIcon = dis.readShort();
        DebugFrame.getInstance().logIn("_PicId=" + po.intId);
        po.strName = dis.readUTF();
        if (_bln) {
            byte _partType = dis.readByte();
            this.readPropAttribute(dis, po, _partType);
        } else {
            po.bytQuality = dis.readByte();
            po.intColor = Macro.INT_PROP_COLOR[po.bytQuality];
            po.shtPOnum = dis.readShort();
            po.strInfo = dis.readUTF();
            po.bytOperation = dis.readByte();
            po.byteEquipType = (byte) -1;
        }
        return po;
    }

    public void readMenuOptionData(DataInputStream dis, MenuObject mo) throws IOException {
        byte _menuNum = dis.readByte();
        String[] _menuName = new String[_menuNum];
        MenuObject[][] _nextMenu = new MenuObject[_menuNum][];
        for (byte i = 0; i < _menuNum; i = (byte) (i + 1)) {
            _menuName[i] = dis.readUTF();
            byte _nextMenuNum = dis.readByte();
            if (_nextMenuNum <= 0) {
                continue;
            }
            _nextMenu[i] = new MenuObject[_nextMenuNum];
            for (byte l = 0; l < _nextMenuNum; l = (byte) (l + 1)) {
                byte _nextMenutype = dis.readByte();
                MenuObject _tmo = this.readMenuListData(dis, _nextMenutype, null, false);
                if (_tmo == null) {
                    continue;
                }
                _nextMenu[i][l] = _tmo;
            }
        }
        mo.strOption = _menuName;
        mo.vNextMenu = _nextMenu;
    }

    public void readTaskData(DataInputStream dis, boolean _type) throws IOException {
        Task _t = new Task();
        _t.intUserId = dis.readInt();
        _t.strNickName = dis.readUTF();
        DebugFrame.getInstance().logIn("Name:" + _t.strNickName);
        if (_type) {
            _t.strInfo = dis.readUTF();
            _t.bytTaskType = 0;
            _t.strOption = dis.readUTF();
        } else {
            _t.strInfo = null;
            _t.bytTaskType = 1;
        }
        byte _x = dis.readByte();
        byte _y = dis.readByte();
        _t.newTask(_x, _y);
        short _picId = dis.readShort();
        DebugFrame.getInstance().logIn("_picId" + _picId);
        _t.setTaskPic(_picId);
        GameUI.getInstance().addObject(_t);
    }

    public void readMosterBox(DataInputStream dis) throws IOException {
        int _id = dis.readInt();
        int _monsterid = dis.readInt();
        byte _pick = dis.readByte();
        byte _tileX = dis.readByte();
        byte _tileY = dis.readByte();
        Box box = new Box();
        box.newBox(_id, _tileX, _tileY, (byte) 1);
        box.intMonsterId = _monsterid;
        if (_monsterid != 0 && Param.getInstance().htRoleMonster != null && Param.getInstance().htRoleMonster.containsKey((Object) new Integer(_monsterid))) {
            ((ORMonster) Param.getInstance().htRoleMonster.get((Object) new Integer((int) _monsterid))).blnHaveBox = true;
        }
        box.setBoxState(_pick == 1);
        GameUI.getInstance().addVObject(box);
    }

    public void readWorldBox(DataInputStream dis) throws IOException {
        Box box = new Box();
        int id = dis.readInt();
        byte _tileX = dis.readByte();
        byte _tileY = dis.readByte();
        box.newBox(id, _tileX, _tileY, (byte) 2);
        box.setBoxState(true);
        box.bytBoxType = (byte) 2;
        GameUI.getInstance().addVObject(box);
    }

    public QSprite readSprite(DataInputStream dis, int poolType, int userId, short _pngid, short _anuid, String _path) throws IOException {
        return ImageManager.loadSpriteById(poolType, ImageManager.EncodespriteId(String.valueOf((int) userId), String.valueOf((int) _pngid)), String.valueOf((int) _anuid), String.valueOf((int) _pngid), _path);
    }

    public void updatePackageGoods(DataInputStream dis) throws IOException {
        byte _packageType = dis.readByte();
        byte _index = dis.readByte();
        short _num = dis.readShort();
        boolean _this = false;
        boolean _bln = false;
        if (_packageType == 36) {
            _this = GameUI.getInstance().checkMeMenuUi(36);
        } else if (_packageType == 37) {
            _this = GameUI.getInstance().checkMeMenuUi(37);
        } else if (_packageType == 38) {
            _this = GameUI.getInstance().checkMeMenuUi(38);
        } else if (_packageType == 39) {
            _this = GameUI.getInstance().checkMeMenuUi(39);
            boolean bl = _bln = Param.getInstance().hPackage != null;
        }
        if ((_this || _bln) && Param.getInstance().hPackage != null) {
            if (_num == 0) {
                this.clearTwoRect();
                Param.getInstance().hPackage.remove((Object) new Integer((int) _index));
                if (Param.getInstance().hPackage.isEmpty()) {
                    Param.getInstance().hPackage = null;
                }
            } else if (Param.getInstance().hPackage.containsKey((Object) new Integer((int) _index))) {
                ((PackageObject) Param.getInstance().hPackage.get((Object) new Integer((int) _index))).shtPOnum = _num;
            }
            if (_this) {
                MenuUI.getInstance().setInfoContent(Param.getInstance().hPackage, MenuUI.getInstance().getPropRectMove());
            }
        }
    }

    public void npcMenu(DataInputStream dis) throws IOException {
        try {
            int _npcId = dis.readInt();
            int _menuID = dis.readInt();
            byte _step = dis.readByte();
            byte _type = dis.readByte();
            DebugFrame.getInstance().logIn("\u4e0eNPC:" + _npcId + "\u53d1\u751f\u4ea4\u4e92,\u5f39\u51fa\u6846\u7c7b\u578b:" + _type + "  \u5c42\u6b21\uff1a" + _step + "  \u7f16\u53f7\uff1a" + _menuID);
            MenuObject mo = new MenuObject();
            mo.intOptionId = _menuID;
            mo.bytStep = _step;
            boolean _this = true;
            if (_type == 1) {
                byte _num = dis.readByte();
                if (_num != 0) {
                    String[] _str = new String[_num];
                    int[] _int = new int[_num];
                    short[] _icon = new short[_num];
                    MenuObject[][] _nextMenu = new MenuObject[_num][];
                    for (byte i = 0; i < _num; i = (byte) (i + 1)) {
                        _int[i] = dis.readInt();
                        _icon[i] = dis.readShort();
                        _str[i] = dis.readUTF();
                        byte _nextMenuNum = dis.readByte();
                        if (_nextMenuNum <= 0) {
                            continue;
                        }
                        _nextMenu[i] = new MenuObject[_nextMenuNum];
                        for (byte l = 0; l < _nextMenuNum; l = (byte) (l + 1)) {
                            byte _nextMenutype = dis.readByte();
                            MenuObject _tmo = this.readMenuListData(dis, _nextMenutype, null, false);
                            if (_tmo == null) {
                                continue;
                            }
                            _nextMenu[i][l] = _tmo;
                        }
                    }
                    if (Param.getInstance().oSelectNpc != null && (_this = GameUI.getInstance().checkNpcMenuUi(_npcId, mo.intOptionId))) {
                        mo.bytType = (byte) 10;
                        mo.strOption = _str;
                        mo.intsOptionId = _int;
                        mo.shtIcon = _icon;
                        mo.vNextMenu = _nextMenu;
                    }
                }
            } else if (_type == 2) {
                mo.strInfo = dis.readUTF();
                byte _num = dis.readByte();
                String[] _str = new String[_num];
                MenuObject[][] _nextMenu = new MenuObject[_num][];
                for (byte i = 0; i < _num; i = (byte) (i + 1)) {
                    _str[i] = dis.readUTF();
                    byte _nextMenuNum = dis.readByte();
                    if (_nextMenuNum <= 0) {
                        continue;
                    }
                    _nextMenu[i] = new MenuObject[_nextMenuNum];
                    for (byte l = 0; l < _nextMenuNum; l = (byte) (l + 1)) {
                        byte _nextMenutype = dis.readByte();
                        MenuObject _tmo = this.readMenuListData(dis, _nextMenutype, null, false);
                        if (_tmo == null) {
                            continue;
                        }
                        _nextMenu[i][l] = _tmo;
                    }
                }
                _this = GameUI.getInstance().checkNpcMenuUi(_npcId, mo.intOptionId);
                if (_this) {
                    mo.bytType = (byte) 11;
                    mo.strOption = _str;
                    mo.vNextMenu = _nextMenu;
                }
            } else if (_type == 3) {
                this.readMenuListData(dis, _type, mo, false);
            } else if (_type == 4) {
                this.readMenuListData(dis, _type, mo, false);
            } else if (_type == 5) {
                this.readMenuListData(dis, _type, mo, false);
            } else if (_type == 6) {
                if (mo.bytStep == 10) {
                    if (Param.getInstance().hNpcOursPackTable == null) {
                        Param.getInstance().hNpcOursPackTable = new Hashtable();
                        Param.getInstance().npcShopOursTabArray = new String[1];
                        Param.getInstance().npcShopOursNumPack = new byte[1];
                    }
                    byte _readType = dis.readByte();
                    byte _packageNum = dis.readByte();
                    Hashtable _h = this.readPackageData(dis, _readType, true);
                    this.readMenuOptionData(dis, mo);
                    _this = GameUI.getInstance().checkNpcMenuUi(_npcId, mo.intOptionId);
                    if (_this) {
                        Param.getInstance().hNpcStorageBoxMaxNum = _packageNum;
                        if (_h != null) {
                            Enumeration e = _h.elements();
                            while (e.hasMoreElements()) {
                                GameUI.getInstance().addObjectImg(((PackageObject) e.nextElement()).shtIcon, "prop");
                            }
                        }
                        this.clearTwoRect();
                        Param.getInstance().hNpcStorage = _h;
                        mo.bytType = (byte) 13;
                    }
                } else if (mo.bytStep == 2) {
                    Param.getInstance().hNpcOursPackTable = new Hashtable();
                    Param.getInstance().npcShopOursTabArray = new String[1];
                    Param.getInstance().npcShopOursNumPack = new byte[1];
                    byte _readType = dis.readByte();
                    byte _packageNum = dis.readByte();
                    Hashtable hNpcBarList = null;
                    int tabNum = dis.readByte();
                    if (tabNum > 0) {
                        hNpcBarList = new Hashtable(tabNum);
                        String[] barName = new String[tabNum];
                        for (int ti = 0; ti < tabNum; ++ti) {
                            String tabName;
                            barName[ti] = tabName = dis.readUTF();
                            Hashtable hPackage = this.readPackageData(dis, _readType, true);
                            hNpcBarList.put((Object) new Integer(ti), (Object) hPackage);
                        }
                        Param.getInstance().npcShopBarTabArray = barName;
                    }
                    this.readMenuOptionData(dis, mo);
                    _this = GameUI.getInstance().checkNpcMenuUi(_npcId, mo.intOptionId);
                    if (_this) {
                        Param.getInstance().packageBoxMaxNum = _packageNum;
                        if (hNpcBarList != null) {
                            for (int i = 0; i < hNpcBarList.size(); ++i) {
                                Hashtable _h = (Hashtable) hNpcBarList.get((Object) new Integer(i));
                                if (_h == null) {
                                    continue;
                                }
                                Enumeration e = _h.elements();
                                while (e.hasMoreElements()) {
                                    GameUI.getInstance().addObjectImg(((PackageObject) e.nextElement()).shtIcon, "prop");
                                }
                            }
                            this.clearTwoRect();
                            Param.getInstance().hNpcBarPackTable = hNpcBarList;
                        }
                    }
                } else if (mo.bytStep == 3 || mo.bytStep == 21 || mo.bytStep == 22 || mo.bytStep == 23 || mo.bytStep == 24 || mo.bytStep == 25) {
                    byte _readType = dis.readByte();
                    String tabName = dis.readUTF();
                    byte _packageNum = dis.readByte();
                    int index = 0;
                    if (Param.getInstance().npcShopOursTabArray != null) {
                        if (Param.getInstance().npcShopOursTabArray[0] == null) {
                            Param.getInstance().npcShopOursTabArray[0] = tabName;
                        } else {
                            String[] _temp = Param.getInstance().npcShopOursTabArray;
                            index = this.hasData(_temp, tabName);
                            if (index == -1) {
                                String[] _tempAarry = new String[_temp.length + 1];
                                System.arraycopy((Object) _temp, (int) 0, (Object) _tempAarry, (int) 0, (int) _temp.length);
                                _tempAarry[_temp.length] = tabName;
                                Param.getInstance().npcShopOursTabArray = _tempAarry;
                            } else {
                                Param.getInstance().npcBarSelectIndex = index;
                            }
                        }
                    }
                    if (Param.getInstance().npcShopOursNumPack != null) {
                        if (Param.getInstance().npcShopOursNumPack[0] == 0) {
                            Param.getInstance().npcShopOursNumPack[0] = _packageNum;
                        } else {
                            byte[] _tempByte = Param.getInstance().npcShopOursNumPack;
                            if (index == -1) {
                                Param.getInstance().npcShopOursNumPack = new byte[_tempByte.length + 1];
                                System.arraycopy((Object) _tempByte, (int) 0, (Object) Param.getInstance().npcShopOursNumPack, (int) 0, (int) _tempByte.length);
                                Param.getInstance().npcShopOursNumPack[_tempByte.length] = _packageNum;
                            }
                        }
                    }
                    Hashtable _h = new Hashtable(1);
                    Hashtable _temp = this.readPackageData(dis, _readType, true);
                    if (_temp != null) {
                        _h = _temp;
                    }
                    this.readMenuOptionData(dis, mo);
                    _this = GameUI.getInstance().checkNpcMenuUi(_npcId, mo.intOptionId);
                    if (_this) {
                        Param.getInstance().packageBoxMaxNum = _packageNum;
                        if (_h != null) {
                            Enumeration e = _h.elements();
                            while (e.hasMoreElements()) {
                                GameUI.getInstance().addObjectImg(((PackageObject) e.nextElement()).shtIcon, "prop");
                            }
                        }
                        this.clearTwoRect();
                        Param.getInstance().hNpcOursPackTable.put((Object) tabName, (Object) _h);
                        mo.bytType = (byte) 13;
                    }
                }
            } else if (_type == 8) {
                byte i;
                byte _num = dis.readByte();
                Vector _temp = null;
                if (_num > 0) {
                    _temp = new Vector((int) _num);
                    for (i = 0; i < _num; i = (byte) (i + 1)) {
                        SkillObject _so = new SkillObject();
                        _so.intId = dis.readInt();
                        _so.strName = dis.readUTF();
                        _so.bytLv = dis.readByte();
                        _so.shtIcon = dis.readShort();
                        _so.strInfo = dis.readUTF();
                        _so.intCDTime = dis.readInt();
                        _temp.addElement((Object) _so);
                    }
                } else {
                    DCanvas.getInstance().addInformation("\u6ca1\u6709\u53ef\u5b66\u6280\u80fd");
                }
                this.readMenuOptionData(dis, mo);
                _this = GameUI.getInstance().checkNpcMenuUi(_npcId, mo.intOptionId);
                if (_this) {
                    if (_temp != null) {
                        for (i = 0; i < _temp.size(); i = (byte) (i + 1)) {
                            GameUI.getInstance().addObjectImg(((SkillObject) _temp.elementAt((int) i)).shtIcon, "prop");
                        }
                    }
                    this.clearTwoRect();
                    Param.getInstance().vCommonList = _temp;
                    mo.bytType = (byte) 12;
                }
            } else if (_type == 9) {
                byte _index = dis.readByte();
                int _id = dis.readInt();
                byte _num = dis.readByte();
                if (Param.getInstance().hPackage != null) {
                    if (_id == ((PackageObject) Param.getInstance().hPackage.get((Object) new Integer((int) _index))).intId) {
                        if (_num == 0) {
                            this.clearTwoRect();
                            Param.getInstance().hPackage.remove((Object) new Integer((int) _index));
                        } else {
                            ((PackageObject) Param.getInstance().hPackage.get((Object) new Integer((int) _index))).shtPOnum = _num;
                        }
                    }
                    if (DCanvas.getInstance().UMenu != null) {
                        MenuUI.getInstance().setInfoContent(Param.getInstance().hPackage, MenuUI.getInstance().getPropRectMove());
                    }
                }
                _this = false;
            } else if (_type == 10) {
                byte i;
                short _page;
                Param.getInstance().shtNoncePage = _page = dis.readShort();
                byte _num = dis.readByte();
                Vector _temp = null;
                if (_num > 0) {
                    _temp = new Vector((int) _num);
                    for (i = 0; i < _num; i = (byte) (i + 1)) {
                        PackageObject po = new PackageObject();
                        po.intId = dis.readInt();
                        po.shtIcon = dis.readShort();
                        po.strName = dis.readUTF();
                        po.shtPOnum = dis.readShort();
                        po.intPaiMaiPrice = dis.readInt();
                        byte _partType = dis.readByte();
                        if (_partType == 3) {
                            po.strInfo = dis.readUTF();
                            po.bytQuality = dis.readByte();
                            po.intColor = Macro.INT_PROP_COLOR[po.bytQuality];
                            po.shtLevel = dis.readShort();
                        } else {
                            this.readPropAttribute(dis, po, _partType);
                        }
                        _temp.addElement((Object) po);
                    }
                } else {
                    DCanvas.getInstance().addInformation("\u6ca1\u6709\u641c\u7d22\u5230\u8be5\u7269\u54c1");
                }
                this.readMenuOptionData(dis, mo);
                _this = GameUI.getInstance().checkNpcMenuUi(_npcId, mo.intOptionId);
                if (_this) {
                    if (_temp != null) {
                        for (i = 0; i < _temp.size(); i = (byte) (i + 1)) {
                            GameUI.getInstance().addObjectImg(((PackageObject) _temp.elementAt((int) i)).shtIcon, "prop");
                        }
                    }
                    this.clearTwoRect();
                    Param.getInstance().vCommonList = _temp;
                }
            } else if (_type == 11) {
                short _page;
                Param.getInstance().shtNoncePage = _page = dis.readShort();
                byte _num = dis.readByte();
                Vector _temp = null;
                GameUI.getInstance().blnNewMail = false;
                if (_num > 0) {
                    _temp = new Vector((int) _num);
                    for (byte i = 0; i < _num; i = (byte) (i + 1)) {
                        PackageObject _po = new PackageObject();
                        _po.intId = dis.readInt();
                        _po.strInfo = dis.readUTF();
                        _po.strTitle = dis.readUTF();
                        _po.strContent = dis.readUTF();
                        _po.bytType = dis.readByte();
                        _po.strDate = dis.readUTF();
                        _po.bytSign = dis.readByte();
                        if (_po.bytSign == 0) {
                            GameUI.getInstance().blnNewMail = true;
                        }
                        _po.shtIcon = dis.readShort();
                        if (_po.shtIcon != -1) {
                            GameUI.getInstance().addObjectImg(_po.shtIcon, "prop");
                        }
                        _po.strName = dis.readUTF();
                        _po.bytQuality = dis.readByte();
                        _po.intColor = Macro.INT_MAIL_COLOR[_po.bytSign];
                        _po.shtPOnum = dis.readShort();
                        _temp.addElement((Object) _po);
                    }
                    this.readMenuOptionData(dis, mo);
                } else {
                    DCanvas.getInstance().addInformation("\u90ae\u7bb1\u662f\u7a7a\u7684");
                }
                if (GameUI.getInstance().checkNpcMenuUi(_npcId, mo.intOptionId)) {
                    this.clearTwoRect();
                    Param.getInstance().bytNpcDataType = (byte) 6;
                    Param.getInstance().vCommonList = _temp;
                    mo.bytType = (byte) 16;
                }
            } else if (_type == 12) {
                int _oSelectId;
                byte _NSelectNum;
                MenuUI.getInstance().bytTaskTempMove = (byte) MenuUI.getInstance().shtOneMenuMove;
                MenuUI.getInstance().bytTaskTempRoll = (byte) MenuUI.getInstance().shtOneMenuRoll;
                TaskObject _toO = new TaskObject();
                _toO.bytType = dis.readByte();
                _toO.shtLevel = dis.readShort();
                _toO.strName = dis.readUTF();
                _toO.strInfo = dis.readUTF();
                _toO.intMoney = dis.readInt();
                _toO.intEXP = dis.readInt();
                byte _SelectNum = dis.readByte();
                if (_SelectNum > 0) {
                    _toO.vSelectObject = new Hashtable((int) _SelectNum);
                    for (byte i = 0; i < _SelectNum; i = (byte) (i + 1)) {
                        PackageObject _po = new PackageObject();
                        byte type = dis.readByte();
                        if (type == 0) {
                            _po.intId = dis.readInt();
                            _po.shtIcon = dis.readShort();
                            _po.strName = dis.readUTF();
                            this.get(dis, _po);
                            dis.readByte();
                            dis.readByte();
                            _po.shtStaminaMax = dis.readShort();
                            _po.shtPOnum = dis.readByte();
                            _po.intPrice = dis.readInt();
                            _po.shtLevel = dis.readShort();
                        } else if (type == 1) {
                            _po.intId = dis.readInt();
                            _po.strName = dis.readUTF();
                            _po.bytQuality = dis.readByte();
                            _po.shtIcon = dis.readShort();
                            _po.strInfo = dis.readUTF();
                            _po.shtPOnum = dis.readByte();
                            _po.intPrice = dis.readInt();
                            _po.shtLevel = dis.readShort();
                        }
                        _toO.vSelectObject.put((Object) new Integer((int) i), (Object) _po);
                    }
                }
                if ((_NSelectNum = dis.readByte()) > 0) {
                    _toO.vNSelectObject = new Hashtable((int) _NSelectNum);
                    for (byte i = 0; i < _NSelectNum; i = (byte) (i + 1)) {
                        PackageObject _po = new PackageObject();
                        byte type = dis.readByte();
                        if (type == 0) {
                            _po.shtIcon = dis.readShort();
                            _po.strName = dis.readUTF();
                            this.get(dis, _po);
                            dis.readByte();
                            dis.readByte();
                            _po.shtStaminaMax = dis.readShort();
                            _po.shtPOnum = dis.readByte();
                            _po.intPrice = dis.readInt();
                            _po.shtLevel = dis.readShort();
                        } else if (type == 1) {
                            _po.strName = dis.readUTF();
                            _po.bytQuality = dis.readByte();
                            _po.shtIcon = dis.readShort();
                            _po.strInfo = dis.readUTF();
                            _po.shtPOnum = dis.readByte();
                            _po.intPrice = dis.readInt();
                            _po.shtLevel = dis.readShort();
                        }
                        _toO.vNSelectObject.put((Object) new Integer((int) i), (Object) _po);
                    }
                }
                if ((_oSelectId = dis.readInt()) > 0) {
                    _toO.vOtherObject = new Hashtable(1);
                    PackageObject _po = new PackageObject();
                    _po.strName = dis.readUTF();
                    _po.shtIcon = dis.readShort();
                    _po.strInfo = dis.readUTF();
                    _toO.vOtherObject.put((Object) new Integer(1), (Object) _po);
                }
                if (_this = GameUI.getInstance().checkNpcMenuUi(_npcId, mo.intOptionId)) {
                    this.clearTwoRect();
                    Param.getInstance().vCommonList = new Vector(1);
                    Param.getInstance().vCommonList.addElement((Object) _toO);
                    MenuUI.getInstance().bytTaskTempMove = 0;
                    MenuUI.getInstance().strOneTitlestr = ((TaskObject) Param.getInstance().vCommonList.elementAt((int) 0)).strName;
                    mo.bytType = (byte) 15;
                }
            } else if (_type == 14) {
                int _itemLength = dis.readByte();
                Vector _temp = null;
                if (_itemLength > 0) {
                    _temp = new Vector(_itemLength);
                    for (int l = 0; l < _itemLength; ++l) {
                        MakeObject _mo = new MakeObject();
                        _mo.intId = dis.readInt();
                        _mo.shtIcon = dis.readShort();
                        _mo.strName = dis.readUTF();
                        byte _bytQuality = dis.readByte();
                        _mo.intColor = Macro.INT_PROP_COLOR[_bytQuality];
                        _mo.shtNum = dis.readShort();
                        _mo.strInfo = dis.readUTF();
                        _temp.addElement((Object) _mo);
                    }
                } else {
                    DCanvas.getInstance().addInformation("\u6ca1\u6709\u53ef\u5151\u6362\u7269\u54c1");
                }
                this.readMenuOptionData(dis, mo);
                _this = GameUI.getInstance().checkNpcMenuUi(_npcId, mo.intOptionId);
                if (_this) {
                    this.clearTwoRect();
                    if (_temp != null) {
                        for (int i = 0; i < _temp.size(); i = (int) ((byte) (i + 1))) {
                            GameUI.getInstance().addObjectImg(((MakeObject) _temp.elementAt((int) i)).shtIcon, "prop");
                        }
                    }
                    Param.getInstance().vCommonList = _temp;
                    mo.bytType = (byte) 17;
                }
            } else if (_type == 15) {
                int _propID = dis.readInt();
                MakeObject _mo = this.readMakeDatum(dis);
                _this = GameUI.getInstance().checkNpcMenuUi(_npcId, mo.intOptionId);
                if (_this && ((MenuObject) Param.getInstance().vMenuMemory.elementAt((int) (Param.getInstance().vMenuMemory.size() - 1))).bytType == 17) {
                    this.clearTwoRect();
                    MakeObject _moo = null;
                    for (int i = 0; i < Param.getInstance().vCommonList.size(); i = (int) ((byte) (i + 1))) {
                        if (_propID != ((MakeObject) Param.getInstance().vCommonList.elementAt((int) i)).intId) {
                            continue;
                        }
                        _moo = (MakeObject) Param.getInstance().vCommonList.elementAt(i);
                        break;
                    }
                    _moo.shtMaterialNameIcon = _mo.shtMaterialNameIcon;
                    _moo.strMaterialName = _mo.strMaterialName;
                    _moo.shtMaterialNum = _mo.shtMaterialNum;
                    _moo.intMaterialColor = _mo.intMaterialColor;
                    _moo.shtNowNum = _mo.shtNowNum;
                    MenuUI.getInstance().setExchangeSee(MenuUI.getInstance().getOneMove());
                }
                _this = false;
            } else if (_type == 16) {
                byte _num = dis.readByte();
                Vector _temp = null;
                if (_num > 0) {
                    _temp = new Vector(1, 1);
                    for (byte i = 0; i < _num; i = (byte) (i + 1)) {
                        PackageObject _po = new PackageObject();
                        _po.bytKey = dis.readByte();
                        _po.strName = dis.readUTF();
                        _po.shtIcon = dis.readShort();
                        _po.strPropName = dis.readUTF();
                        byte _partType = 1;
                        this.readPropAttribute(dis, _po, _partType);
                        _temp.addElement((Object) _po);
                    }
                }
                if (_this = GameUI.getInstance().checkNpcMenuUi(_npcId, mo.intOptionId)) {
                    this.clearTwoRect();
                    Param.getInstance().vCommonList = _temp;
                    mo.bytType = (byte) 18;
                }
            } else if (_type == 17) {
                short _nowNum;
                ORPMe.ME.shtCNowNum = _nowNum = dis.readShort();
                short _allNum = dis.readShort();
                byte _page = dis.readByte();
                byte _Allpage = dis.readByte();
                ORPMe.ME.shtCAllNum = _allNum;
                Vector _temp = null;
                byte _num = dis.readByte();
                _temp = new Vector(1, 1);
                for (byte i = 0; i < _num; i = (byte) (i + 1)) {
                    TeamObject _to = new TeamObject();
                    _to.intId = dis.readInt();
                    _to.strName = dis.readUTF();
                    _to.bytTroopRank = dis.readByte();
                    boolean bl = _to.blnIsOnLine = dis.readByte() == 1;
                    if (_to.blnIsOnLine) {
                        byte _occu = dis.readByte();
                        _to.setOcc(_occu);
                        _to.shtLevel = dis.readShort();
                        _to.bytType = 1;
                        _to.blnIsMale = dis.readByte() == 1;
                    }
                    _temp.addElement((Object) _to);
                }
                this.readMenuOptionData(dis, mo);
                _this = GameUI.getInstance().checkNpcMenuUi(_npcId, mo.intOptionId);
                if (_this) {
                    this.clearTwoRect();
                    if (_page > _Allpage) {
                        _page = _Allpage;
                    }
                    Param.getInstance().shtNoncePage = _page;
                    Param.getInstance().shtAllPage = _Allpage;
                    Param.getInstance().vCommonList = _temp;
                }
            } else if (_type == 18) {
                byte _n = dis.readByte();
                String[] _strs = new String[_n];
                for (byte i = 0; i < _n; i = (byte) (i + 1)) {
                    _strs[i] = dis.readUTF();
                }
                short _num = dis.readShort();
                Vector _temp = null;
                if (_num > 0) {
                    _temp = new Vector(1, 1);
                    for (short i = 0; i < _num; i = (short) (i + 1)) {
                        int _price;
                        SkillObject _so = new SkillObject();
                        _so.intId = dis.readInt();
                        dis.readShort();
                        _so.strName = dis.readUTF();
                        _so.strPropName = dis.readUTF();
                        _so.shtIcon = dis.readShort();
                        byte _bytQuality = dis.readByte();
                        _so.intCDTimeMax = Macro.INT_PROP_COLOR[_bytQuality];
                        _so.bytType = dis.readByte();
                        _so.intCDTime = _price = dis.readInt();
                        _so.bytKey = (byte) -1;
                        _temp.addElement((Object) _so);
                    }
                }
                this.readMenuOptionData(dis, mo);
                _this = GameUI.getInstance().checkNpcMenuUi(_npcId, mo.intOptionId);
                if (_this) {
                    this.clearTwoRect();
                    MenuUI.getInstance().setTitle(_strs);
                    Param.getInstance().vCommonList = _temp;
                    mo.bytType = (byte) -12;
                }
            } else if (_type == 19) {
                mo.strInfo = dis.readUTF();
                byte _num = dis.readByte();
                String[] _str = new String[_num];
                String[] _strmoney = new String[_num];
                MenuObject[][] _nextMenu = new MenuObject[_num][];
                for (byte i = 0; i < _num; i = (byte) (i + 1)) {
                    _str[i] = dis.readUTF();
                    _strmoney[i] = dis.readUTF();
                    byte _nextMenuNum = dis.readByte();
                    if (_nextMenuNum <= 0) {
                        continue;
                    }
                    _nextMenu[i] = new MenuObject[_nextMenuNum];
                    for (byte l = 0; l < _nextMenuNum; l = (byte) (l + 1)) {
                        byte _nextMenutype = dis.readByte();
                        MenuObject _tmo = this.readMenuListData(dis, _nextMenutype, null, false);
                        if (_tmo == null) {
                            continue;
                        }
                        _nextMenu[i][l] = _tmo;
                    }
                }
                _this = GameUI.getInstance().checkNpcMenuUi(_npcId, mo.intOptionId);
                if (_this) {
                    mo.bytType = (byte) 11;
                    mo.strOption = _str;
                    mo.strOptionMoney = _strmoney;
                    mo.vNextMenu = _nextMenu;
                }
            } else if (_type == 20) {
                if (mo.bytStep == 2) {
                    mo.strBakInfo = dis.readUTF();
                    mo.strInfo = dis.readUTF();
                    byte _num = dis.readByte();
                    String[] _str = new String[_num];
                    MenuObject[][] _nextMenu = new MenuObject[_num][];
                    for (byte i = 0; i < _num; i = (byte) (i + 1)) {
                        _str[i] = dis.readUTF();
                        int _nextMenuNum = 0;
                        if (_nextMenuNum <= 0) {
                            continue;
                        }
                        _nextMenu[i] = new MenuObject[_nextMenuNum];
                        for (int l = 0; l < _nextMenuNum; l = (int) ((byte) (l + 1))) {
                            byte _nextMenutype = dis.readByte();
                            MenuObject _tmo = this.readMenuListData(dis, _nextMenutype, null, false);
                            if (_tmo == null) {
                                continue;
                            }
                            _nextMenu[i][l] = _tmo;
                        }
                    }
                    _this = GameUI.getInstance().checkNpcMenuUi(_npcId, mo.intOptionId);
                    if (_this) {
                        mo.bytType = (byte) 14;
                        mo.strOption = _str;
                        mo.vNextMenu = _nextMenu;
                    }
                } else {
                    DebugFrame.getInstance().logIn("NPCMENU: \u7c7b\u578b\u9519\u8bef!!");
                }
            } else if (_type == 22) {
                if (mo.bytStep == 3) {
                    mo.strBakInfo = dis.readUTF();
                    mo.strInfo = dis.readUTF();
                    int _num = 1;
                    String[] _str = new String[_num];
                    MenuObject[][] _nextMenu = new MenuObject[_num][];
                    for (int i = 0; i < _num; i = (int) ((byte) (i + 1))) {
                        _str[i] = dis.readUTF();
                        int _nextMenuNum = 0;
                        if (_nextMenuNum <= 0) {
                            continue;
                        }
                        _nextMenu[i] = new MenuObject[_nextMenuNum];
                        for (int l = 0; l < _nextMenuNum; l = (int) ((byte) (l + 1))) {
                            byte _nextMenutype = dis.readByte();
                            MenuObject _tmo = this.readMenuListData(dis, _nextMenutype, null, false);
                            if (_tmo == null) {
                                continue;
                            }
                            _nextMenu[i][l] = _tmo;
                        }
                    }
                    _this = GameUI.getInstance().checkNpcMenuUi(_npcId, mo.intOptionId);
                    if (_this) {
                        mo.bytType = (byte) 22;
                        mo.strOption = _str[0].equals((Object) "") ? null : _str;
                        mo.vNextMenu = _nextMenu;
                    }
                }
            } else if (_type == 21) {
                mo.strInfo = dis.readUTF();
                int _num = dis.readByte();
                byte[] _inputLength = new byte[_num];
                String[] _inputStr = new String[_num];
                for (int i = 0; i < _num; ++i) {
                    _inputLength[i] = dis.readByte();
                    _inputStr[i] = dis.readUTF();
                }
                _this = GameUI.getInstance().checkNpcMenuUi(_npcId, mo.intOptionId);
                if (_this) {
                    mo.bytType = (byte) 23;
                    mo.strInfoArray = _inputStr;
                    mo.intMaxArray = _inputLength;
                }
            }
            if (Param.getInstance().oSelectNpc != null && _this && GameUI.getInstance().checkNpcMenuUi(_npcId, mo.intOptionId)) {
                this.clearTwoRect();
                GameUI.getInstance().pushNpcMenu(mo);
            }
            Param.getInstance().blnNpcOptionWork = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatePackageEquip(DataInputStream dis) throws IOException {
        byte _packageType = dis.readByte();
        byte _operateType = dis.readByte();
        if (_operateType == 0) {
            byte _ObjectIndex = dis.readByte();
            if (_packageType == 10) {
                this.clearTwoRect();
                Param.getInstance().hPackageEquip.remove((Object) new Integer((int) _ObjectIndex));
                GameUI.getInstance().checkEquipStamina();
                if (GameUI.getInstance().checkMeMenuUi(10)) {
                    MenuUI.getInstance().setInfoContent(Param.getInstance().hPackageEquip, MenuUI.getInstance().bytRoleMove);
                }
            } else if (_packageType == 58) {
                this.clearTwoRect();
                Param.getInstance().hPetPackEquip.remove((Object) new Integer((int) _ObjectIndex));
                GameUI.getInstance().checkEquipStamina();
                if (GameUI.getInstance().checkMeMenuUi(58)) {
                    MenuUI.getInstance().setInfoContent(Param.getInstance().hPetPackEquip, MenuUI.getInstance().bytPetMove);
                }
            } else if (_packageType == 56) {
                this.clearTwoRect();
                if (GameUI.getInstance().checkMeMenuUi(10)) {
                    if (Param.getInstance().hPackage != null) {
                        Param.getInstance().hPackage.remove((Object) new Integer((int) _ObjectIndex));
                    }
                    MenuUI.getInstance().setInfoContent(Param.getInstance().hPackage, MenuUI.getInstance().getPropRectMove());
                }
            } else if (_packageType == 57) {
                this.clearTwoRect();
                if (GameUI.getInstance().checkMeMenuUi(58)) {
                    if (Param.getInstance().hPackage != null) {
                        Param.getInstance().hPackage.remove((Object) new Integer((int) _ObjectIndex));
                    }
                    MenuUI.getInstance().setInfoContent(Param.getInstance().hPackage, MenuUI.getInstance().getPropRectMove());
                }
            }
        } else if (_packageType == 10 || _packageType == 58) {
            PackageObject po = this.readMeEquipData(dis);
            this.clearTwoRect();
            Param.getInstance().hPackageEquip.remove((Object) new Integer(po.intPOindex));
            Param.getInstance().hPetPackEquip.remove((Object) new Integer(po.intPOindex));
            if (GameUI.getInstance().checkMeMenuUi(10)) {
                GameUI.getInstance().addObjectImg(po.shtIcon, "prop");
            }
            if (GameUI.getInstance().checkMeMenuUi(58)) {
                GameUI.getInstance().addObjectImg(po.shtIcon, "prop");
            }
            Param.getInstance().hPackageEquip.put((Object) new Integer(po.intPOindex), (Object) po);
            Param.getInstance().hPetPackEquip.put((Object) new Integer(po.intPOindex), (Object) po);
            GameUI.getInstance().checkEquipStamina();
            if (GameUI.getInstance().checkMeMenuUi(10) && MenuUI.getInstance().bytMoveType == 0) {
                MenuUI.getInstance().setInfoContent(Param.getInstance().hPackageEquip, MenuUI.getInstance().bytRoleMove);
            }
            if (GameUI.getInstance().checkMeMenuUi(58) && MenuUI.getInstance().bytMoveType == 0) {
                MenuUI.getInstance().setInfoContent(Param.getInstance().hPetPackEquip, MenuUI.getInstance().bytPetMove);
            }
        } else if (_packageType == 56 || _packageType == 57) {
            PackageObject po = this.readMeEquipData(dis);
            if (GameUI.getInstance().checkMeMenuUi(10) && MenuUI.getInstance().tabStyleInstance.getTabCurrentIndex() == 0) {
                if (Param.getInstance().hPackage == null) {
                    Param.getInstance().hPackage = new Hashtable();
                }
                this.clearTwoRect();
                Param.getInstance().hPackage.remove((Object) new Integer(po.intPOindex));
                GameUI.getInstance().addObjectImg(po.shtIcon, "prop");
                Param.getInstance().hPackage.put((Object) new Integer(po.intPOindex), (Object) po);
                MenuUI.getInstance().setInfoContent(Param.getInstance().hPackage, MenuUI.getInstance().getPropRectMove());
            }
        }
        Param.getInstance().personalPackage.setPopupDialog();
    }

    public void skillEffect(DataInputStream dis) throws IOException {
        int i;
        short _startPicId = dis.readShort();
        short _startAnuId = dis.readShort();
        byte bytRoleAction = dis.readByte();
        byte byteLayer = dis.readByte();
        byte shtDisplayPosOff = dis.readByte();
        byte _startType = dis.readByte();
        int _startId = dis.readInt();
        byte _direction = dis.readByte();
        boolean isSkillMultiDir = dis.readByte() == 1;
        int _endNum = dis.readByte();
        boolean _blnMe = false;
        int[][] _end = null;
        if (_endNum > 0) {
            short _endPicId = dis.readShort();
            short _endAnuId = dis.readShort();
            int _endLayer = 1;
            byte _endDisplayPosOff = dis.readByte();
            DebugFrame.getInstance().logIn("\u88ab\u6253\u6280\u80fd\u9ad8\u5ea6\u663e\u793a\u504f\u79fb :" + _endDisplayPosOff);
            _end = new int[_endNum][6];
            for (i = 0; i < _endNum; i = (byte) (i + 1)) {
                _end[i][0] = dis.readByte();
                _end[i][1] = dis.readInt();
                if (Param.getInstance().intSelectId != -1 && _end[i][1] == ORPMe.ME.intUserId) {
                    _blnMe = true;
                }
                _end[i][2] = _endPicId;
                _end[i][3] = _endAnuId;
                _end[i][4] = _endLayer;
                _end[i][5] = _endDisplayPosOff;
            }
        }
        ORole atc = null;
        if (_startType == 2 && Param.getInstance().htRolePlayer != null) {
            atc = (ORole) Param.getInstance().htRolePlayer.get((Object) new Integer(_startId));
        } else if (_startType == 3 && Param.getInstance().htRoleMonster != null) {
            atc = (ORole) Param.getInstance().htRoleMonster.get((Object) new Integer(_startId));
        } else if (_startType == 4 && Param.getInstance().htRolePet != null) {
            atc = (ORole) Param.getInstance().htRolePet.get((Object) new Integer(_startId));
        }
        int _endid = -1;
        int _endType = -1;
        if (_end != null && _end[0] != null && _end[0].length > 2) {
            _endid = (short) _end[0][1];
            _endType = (byte) _end[0][0];
        }
        QSprite createSkillRes = null;
        if (atc != null) {
            if (Param.getInstance().intSelectId == -1 && _blnMe) {
                ORPMe.ME.setSelectRole(atc);
            }
            if (_startType == 2) {
                SkillObject SkillObj = new SkillObject(_startPicId, _startAnuId, (byte) 0);
                SkillObj.byteActive = 0;
                SkillObj.byteSkillType = 0;
                SkillObj.byteLayer = byteLayer;
                SkillObj.bytDisplayPosYOffType = shtDisplayPosOff;
                SkillObj.bytRoleAction = bytRoleAction;
                SkillObj.blnSkillMultiAnimation = isSkillMultiDir;
                if (_startId != ORPMe.ME.intUserId && ((ORPlayer) atc).curSkillObj != null && (createSkillRes = Macro.BLN_LEVEL_FUNCTION_SKILL ? ResPoolLevelFunction.getInstance().bindSkill(atc, null, SkillObj) : ((ORPlayer) atc).setSkill(SkillObj)) != null) {
                    ((ORPlayer) atc).curSkillObj = SkillObj;
                    ((ORPlayer) atc).pushTask((byte) 4, _direction == 0 ? ((ORPlayer) atc).bytDirection : _direction);
                }
            } else if (_startType == 3) {
                SkillObject skillObj = new SkillObject(_startPicId, _startAnuId, (byte) 0);
                skillObj.byteActive = 0;
                skillObj.byteSkillType = 0;
                skillObj.byteLayer = byteLayer;
                skillObj.bytDisplayPosYOffType = shtDisplayPosOff;
                skillObj.bytRoleAction = bytRoleAction;
                skillObj.blnSkillMultiAnimation = isSkillMultiDir;
                createSkillRes = Macro.BLN_LEVEL_FUNCTION_SKILL ? ResPoolLevelFunction.getInstance().bindSkill(atc, null, skillObj) : ((ORMonster) atc).setSkill(skillObj);
                if (createSkillRes != null) {
                    ((ORMonster) atc).curSkillObj = skillObj;
                    ((ORMonster) atc).pushTask((byte) 4, _direction == 0 ? ((ORPlayer) atc).bytDirection : _direction);
                }
            } else if (_startType == 4) {
                SkillObject skillObj = new SkillObject(_startPicId, _startAnuId, (byte) 0);
                skillObj.byteActive = 0;
                skillObj.byteSkillType = 0;
                skillObj.byteLayer = byteLayer;
                skillObj.bytDisplayPosYOffType = shtDisplayPosOff;
                skillObj.bytRoleAction = bytRoleAction;
                skillObj.blnSkillMultiAnimation = isSkillMultiDir;
                createSkillRes = Macro.BLN_LEVEL_FUNCTION_SKILL ? ResPoolLevelFunction.getInstance().bindSkill(atc, null, skillObj) : ((PetObject) atc).setSkill(skillObj);
                if (createSkillRes != null) {
                    ((PetObject) atc).curSkillObj = skillObj;
                    ((PetObject) atc).pushTask((byte) 4, _direction == 0 ? ((ORPlayer) atc).bytDirection : _direction);
                }
            }
        }
        if (_end != null) {
            for (i = 0; i < _end.length; ++i) {
                SkillObject endSkillObj;
                ORole atc_end = null;
                createSkillRes = null;
                DebugFrame.getInstance().logIn("\u627f\u53d7\u8005id:" + _end[i][1] + "/_endPicId/_endAnuId:" + _end[i][2] + "/" + _end[i][3]);
                if (_end[i][0] == 2 && Param.getInstance().htRolePlayer != null) {
                    atc_end = (ORole) Param.getInstance().htRolePlayer.get((Object) new Integer(_end[i][1]));
                    if (atc_end == null) {
                        continue;
                    }
                    endSkillObj = new SkillObject((short) _end[i][2], (short) _end[i][3], (byte) 1);
                    endSkillObj.byteLayer = (byte) _end[i][4];
                    endSkillObj.bytDisplayPosYOffType = (byte) _end[i][5];
                    endSkillObj.byteActive = 1;
                    endSkillObj.byteSkillType = 1;
                    endSkillObj.blnSkillMultiAnimation = false;
                    createSkillRes = Macro.BLN_LEVEL_FUNCTION_SKILL ? ResPoolLevelFunction.getInstance().bindSkill(atc_end, atc, endSkillObj) : ((ORPlayer) atc_end).setSkill(endSkillObj);
                    if (createSkillRes == null) {
                        continue;
                    }
                    atc_end.setSkillFrame(endSkillObj, 1);
                    continue;
                }
                if (_end[i][0] != 3 || Param.getInstance().htRoleMonster == null || (atc_end = (ORole) Param.getInstance().htRoleMonster.get((Object) new Integer(_end[i][1]))) == null) {
                    continue;
                }
                endSkillObj = new SkillObject((short) _end[i][2], (short) _end[i][3], (byte) 1);
                endSkillObj.byteLayer = (byte) _end[i][4];
                endSkillObj.bytDisplayPosYOffType = (byte) _end[i][5];
                endSkillObj.byteActive = 1;
                endSkillObj.byteSkillType = 1;
                endSkillObj.blnSkillMultiAnimation = false;
                createSkillRes = Macro.BLN_LEVEL_FUNCTION_SKILL ? ResPoolLevelFunction.getInstance().bindSkill(atc_end, atc, endSkillObj) : ((ORMonster) atc_end).setSkill(endSkillObj);
                if (createSkillRes == null) {
                    continue;
                }
                atc_end.setSkillFrame(endSkillObj, 1);
            }
        }
    }

    private int hasData(String[] _data, String str) {
        for (int i = 0; i < _data.length; ++i) {
            if (!_data[i].equals((Object) str)) {
                continue;
            }
            return i;
        }
        return -1;
    }

    public void optionTroop() {
        if (DCanvas.getInstance().UMenu != null && MenuUI.getInstance().getState() == 35) {
            if (DCanvas.getInstance().UHandle != null) {
                GameControl.getInstance().delUIbase(5);
            }
            MenuUI.getInstance().setRoleList((byte) 35, Param.getInstance().vTeam);
        }
    }

    public void clearTwoRect() {
        if (DCanvas.getInstance().UHandle != null) {
            GameControl.getInstance().delUIbase(5);
            GameControl.getInstance().delUIbase(8);
            GameControl.getInstance().delUIbase(10);
        }
    }

    public void dapNpcRect() {
        if (DCanvas.getInstance().UMenu != null && Param.getInstance().bytSelectType == 1) {
            this.clearTwoRect();
            GameControl.getInstance().delUIbase(MenuUI.getInstance().bytDelState);
            Param.getInstance().vMenuMemory = null;
            Param.getInstance().hImgObject = null;
        }
    }

    public void clearDeal() {
        Param.getInstance().vDealDuiProp = null;
        Param.getInstance().vDealMyProp = null;
        Param.getInstance().intsDealRgbColor = null;
    }

    public void checkMeSpecialtiesState() {
        ORPMe.ME.blnCanFight = true;
        ORPMe.ME.blnCanMove = true;
        ORPMe.ME.blnCanSkill = true;
        ORPMe.ME.blnCanSlow = false;
        if (ORPMe.ME.bytSpecialtiesState == null) {
            return;
        }
        block6:
        for (int i = 0; i < ORPMe.ME.bytSpecialtiesState.length; i = (int) ((byte) (i + 1))) {
            switch (ORPMe.ME.bytSpecialtiesState[i]) {
                case 2:
                case 3: {
                    ORPMe.ME.blnCanFight = false;
                    ORPMe.ME.blnCanMove = false;
                    ORPMe.ME.blnCanSkill = false;
                    continue block6;
                }
                case 1: {
                    ORPMe.ME.blnCanSkill = false;
                    continue block6;
                }
                case 4: {
                    ORPMe.ME.blnCanMove = false;
                    continue block6;
                }
                case 5: {
                    ORPMe.ME.blnCanSlow = true;
                }
            }
        }
    }

    public void updateSkillCD(int[] _id, int[] _time) {
        block0:
        for (int i = 0; i < _id.length; ++i) {
            for (int l = 0; l < Param.getInstance().vSkillList.size(); ++l) {
                SkillObject so = (SkillObject) Param.getInstance().vSkillList.elementAt(l);
                if (_id[i] != so.intId) {
                    continue;
                }
                so.intCDTimeMax = _time[i];
                if (so.intCDTime <= so.intCDTimeMax) {
                    continue block0;
                }
                so.intCDTime = so.intCDTimeMax;
                continue block0;
            }
        }
    }

    public void addEquipDialog(PackageObject _po) {
        if (Param.getInstance().vEquipDialog == null) {
            Param.getInstance().vEquipDialog = new Vector(1, 1);
        }
        GameUI.getInstance().addObjectImg(_po.shtIcon, "prop");
        Param.getInstance().vEquipDialog.addElement((Object) _po);
        GameUI.getInstance().setDialog((byte) 8);
    }

    public void readPropAttribute(DataInputStream dis, PackageObject po, byte _partType) throws IOException {
        String describe;
        byte _hitNum;
        this.get(dis, po);
        po.bytIsBind = dis.readByte();
        po.bytSeal = dis.readByte();
        po.shtStamina = dis.readShort();
        po.intPrice = dis.readInt();
        po.DescribeProperty = describe = dis.readUTF();
        short picID = dis.readShort();
        short sprieID = dis.readShort();
        po.holeNum = _hitNum = dis.readByte();
        if (_hitNum > 0) {
            po.holeDate = new byte[12];
            for (int i = 0; i < 12; ++i) {
                po.holeDate[i] = dis.readByte();
            }
        }
    }

    public void get(DataInputStream dis, PackageObject po) throws IOException {
        byte _index = dis.readByte();
        byte[] _byttemp = null;
        short[] _shttemp = null;
        if (_index != -1) {
            _byttemp = new byte[40];
            _shttemp = new short[40];
        }
        int _temp = 0;
        while (_index != -1) {
            if (_index < 80) {
                short _date = dis.readShort();
                switch (_index) {
                    case 1: {
                        po.byteEquipType = (byte) (_date - 601 + 10);
                        break;
                    }
                    case 2: {
                        po.byteEquipPart = (byte) _date;
                        break;
                    }
                    case 3: {
                        po.byteEquipFixed = (byte) _date;
                        break;
                    }
                    case 4: {
                        po.shtLevel = _date;
                        break;
                    }
                    case 5: {
                        po.bytQuality = (byte) _date;
                        po.intColor = Macro.INT_PROP_COLOR[po.bytQuality];
                        break;
                    }
                    case 6: {
                        po.bytOperation = (byte) _date;
                        break;
                    }
                    case 38: {
                        po.shtStaminaMax = _date;
                        break;
                    }
                    default: {
                        _shttemp[_temp] = _date;
                        _byttemp[_temp] = _index;
                        _temp = (byte) (_temp + 1);
                        break;
                    }
                }
            } else {
                String _tempstr = dis.readUTF();
                switch (_index) {
                    case 81: {
                        po.strSuitName = _tempstr;
                        break;
                    }
                    case 82: {
                        po.strSuitInfo = _tempstr;
                    }
                }
            }
            _index = dis.readByte();
        }
        if (_temp != 0) {
            po.bytsAttribute = new byte[_temp];
            po.shtsAttributeInfo = new short[_temp];
            for (int i = 0; i < _temp; i = (int) ((byte) (i + 1))) {
                po.bytsAttribute[i] = _byttemp[i];
                po.shtsAttributeInfo[i] = _shttemp[i];
            }
        }
        po.strExplain = dis.readUTF();
    }

    public void initCue(String _title, String _info) {
        GameControl.getInstance().CreateState((byte) 11);
        if (_title == null) {
            CueUI.getInstance().setComCue(_info);
        } else {
            CueUI.getInstance().setFullCue(_title, _info);
        }
    }

    public void setInitLoadingPre() {
        if (Macro.bytGameType == 1) {
            GameControl.getInstance().CreateState((byte) 6);
        } else {
            GameControl.getInstance().CreateState((byte) 6);
        }
        LoadingUI.getInstance().setSpeed(80);
    }

    public void setInitLoading() {
        if (Macro.bytGameType == 1) {
            GameControl.getInstance().delUIbase(7);
            if (DCanvas.getInstance().UMenu != null) {
                GameControl.getInstance().delUIbase(MenuUI.getInstance().bytDelState);
            }
            GameControl.getInstance().delUIbase(4);
        } else {
            GameControl.getInstance().delUIbase(1);
        }
    }
}
