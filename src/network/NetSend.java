// 
// Decompiled by Procyon v0.5.36
// 
package network;

import means.Rms;
import java.util.Enumeration;
import face.FormDes;
import base.GameControl;
import java.util.Hashtable;
import model.PackageObject;
import model.MenuObject;
import face.MenuUI;
import face.GameUI;
import java.io.DataInputStream;
import base.DCanvas;
import model.ORPMe;
import model.Map;
import base.Param;
import means.DebugFrame;
import java.io.DataOutputStream;
import java.io.IOException;
import base.Macro;

public class NetSend {

    private static NetSend ns;
    public boolean blnStartNet;

    public NetSend() {
        this.blnStartNet = false;
        NetSend.ns = this;
    }

    public static NetSend getInstance() {
        return NetSend.ns;
    }

    public void clean() {
        NetSend.ns = null;
    }

    private void startNet() {
        this.blnStartNet = true;
        NetManager.getInstance();
        new NetParse();
        NetManager.getInstance().setConnectionType((byte) 1, Macro.URL_LAND);
    }

    public void sendHeart() {
        NetManager.getDOS((short) 5120);
        NetManager.getInstance().addPacket();
    }

    public void sendHttpSocket() {
        if (!this.blnStartNet) {
            this.startNet();
        }
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 15365);
            ++Macro.netKey;
            final long nowTime = System.currentTimeMillis();
            dos.writeInt(Macro.netKey);
            dos.writeLong(nowTime);
            Macro.hNetList.put(new Integer(Macro.netKey), String.valueOf(Macro.netKey) + " .MISS\u2026\u2026\n");
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendAutoLand(final String _name, final String _password) {
        if (!this.blnStartNet) {
            this.startNet();
        }
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 616);
            dos.writeUTF(_name);
            dos.writeUTF(_password);
            dos.writeUTF(Macro.VERSION);
            dos.writeInt(1);
            dos.writeUTF(new StringBuffer(String.valueOf(Macro.CLIVER)).toString());
            dos.writeUTF(Macro.PHONE_INFO);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendUserCenterURL() {
        if (!this.blnStartNet) {
            this.startNet();
        }
        final DataOutputStream dos = NetManager.getDOS((short) 661);
        NetManager.getInstance().addPacket();
    }

    public void sendUserIDToDownURL(final String userid) {
        if (!this.blnStartNet) {
            this.startNet();
        }
        try {
            DebugFrame.getInstance().logIn("\u8bf7\u6c42 \u6e38\u620f\u5305\u4e0b\u8f7d\u5730\u5740");
            final DataOutputStream dos = NetManager.getDOS((short) 663);
            dos.writeUTF(userid);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendDownJarState(final String userid, final String jarName) {
        if (!this.blnStartNet) {
            this.startNet();
        }
        try {
            DebugFrame.getInstance().logIn("\u6210\u529f\u4e0b\u8f7d" + jarName);
            final DataOutputStream dos = NetManager.getDOS((short) 664);
            dos.writeUTF(userid);
            dos.writeUTF(jarName);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMobileLogin() {
        DebugFrame.getInstance().logIn("\u767b\u9646\u79fb\u52a8\u83b7\u53d6\u79fb\u52a8\u4f2a\u7801");
//        if (Param.getInstance().CHIAN_MOBILE_LOGIN_RUL != null) {
//            final int size = Param.getInstance().CHIAN_MOBILE_LOGIN_RUL.length;
//            for (int i = 0; i < size - 1; ++i) {
//                NetManager.sendLoginChinaMobileGet(Param.getInstance().CHIAN_MOBILE_LOGIN_RUL[i], false);
//            }
//            NetManager.sendLoginChinaMobileGet(Param.getInstance().CHIAN_MOBILE_LOGIN_RUL[size - 1], true);
//        }
    }

    public void sendLand(final String _name, final String _password, final byte _type) {
        if (!this.blnStartNet) {
            this.startNet();
        }
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 610);
            dos.writeUTF(_name);
            dos.writeUTF(_password);
            dos.writeUTF(Macro.VERSION);
            dos.writeInt(1);
            dos.writeByte(_type);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendLandTestVersion(final String _name, final String _password, final String _phoneNum) {
        if (!this.blnStartNet) {
            this.startNet();
        }
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 613);
            dos.writeUTF(_phoneNum);
            dos.writeUTF(_name);
            dos.writeUTF(_password);
            dos.writeUTF(Macro.VERSION);
            dos.writeInt(1);
            dos.writeUTF(new StringBuffer(String.valueOf(Macro.CLIVER)).toString());
            dos.writeUTF(Macro.PHONE_INFO);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendRegister(final String _name, final String _password, final String _phone) {
        if (!this.blnStartNet) {
            this.startNet();
        }
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 618);
            dos.writeUTF(_phone);
            dos.writeUTF(_name);
            dos.writeUTF(_password);
            dos.writeUTF(Macro.VERSION);
            dos.writeInt(1);
            dos.writeUTF(new StringBuffer(String.valueOf(Macro.CLIVER)).toString());
            dos.writeUTF(Macro.PHONE_INFO);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendChangeMap(final short _id, final short _x, final short _y) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 10754);
            dos.writeShort(_id);
            dos.writeShort(_x);
            dos.writeShort(_y);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendResetPassWord(final String _accound, final String _oldpassword, final String _newpassword) {
        if (!this.blnStartNet) {
            this.startNet();
        }
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 614);
            dos.writeUTF(_accound);
            dos.writeUTF(_oldpassword);
            dos.writeUTF(_newpassword);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendNewRoleDefault() {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 660);
            dos.writeShort(Macro.RELOAD_Server_ID);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendNewRole(final String _name, final byte _country, final byte _occupation, final byte _isMale, final byte _place) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 619);
            dos.writeByte(_place);
            dos.writeUTF(_name);
            dos.writeByte(_country + 1);
            dos.writeByte(_occupation + 1);
            dos.writeByte(_isMale + 1);
            dos.writeByte(Macro.CLIENTTYPE);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendDelRole(final int _id) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 611);
            dos.writeInt(_id);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendGetMap() {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 10753);
            dos.writeByte(Macro.CLIENTTYPE);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
        NetManager.getInstance().blnOnceErr = true;
    }

    public void sendBreakAwayMap() {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 10777);
            dos.writeShort(Map.getInstance().shtMapId);
            NetManager.getInstance().addPacket();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendRequestRes(final short pngId, final short anuId, final String path) {
    }

    public void sendRequestRes(final int resTableSize) {
    }

    public void sendSelectRole(final int _id) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 620);
            dos.writeInt(_id);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
        NetManager.getInstance().blnOnceErr = true;
    }

    public void sendSelectServer(final short _num) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 615);
            dos.writeShort(_num);
            dos.writeByte(2);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendClientStep(final byte _type) {
        if (!this.blnStartNet) {
            this.startNet();
        }
        if (Param.BLN_CLIENT_FIRST_START) {
            try {
                final DataOutputStream dos = NetManager.getDOS((short) 662);
                dos.writeUTF(Param.CLIENT_FIRST_START_NUM);
                dos.writeByte(_type);
                NetManager.getInstance().addPacket();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMoveDirection(final byte[] _xy, final byte _x, final byte _y) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 1031);
            dos.writeByte((byte) _xy.length);
            for (int i = 0; i < _xy.length; ++i) {
                dos.writeByte(_xy[i]);
            }
            dos.writeByte(_x);
            dos.writeByte(_y);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendUseSkill(final int _skillid, byte _targetType, int _targetId, final byte _d) {
        if (_targetId == 0) {
            _targetId = ORPMe.ME.intUserId;
            _targetType = 2;
        }
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 3585);
            dos.writeInt(_skillid);
            dos.writeByte(_targetType);
            dos.writeInt(_targetId);
            dos.writeByte(_d);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendAttack(final byte _targetType, final int _targetId) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 7169);
            dos.writeByte(_targetType);
            dos.writeInt(_targetId);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendPetAttack(final int petid, final byte _targetType, final int _targetId) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 7170);
            dos.writeInt(petid);
            DebugFrame.getInstance().logIn("\u5ba0\u7269ID\uff1a" + petid);
            dos.writeByte(_targetType);
            dos.writeInt(_targetId);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendPetSkillAttack(final int petid, final int skillid, final byte _targetType, final int _targetId, final byte face) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 3602);
            dos.writeInt(petid);
            dos.writeInt(skillid);
            DebugFrame.getInstance().logIn("\u5ba0\u7269ID\uff1a" + petid);
            dos.writeByte(_targetType);
            dos.writeInt(_targetId);
            dos.writeByte(face);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendChat(final String _chat, final byte _type, final String _nickname) {
        Param.getInstance().vOptionPlace = null;
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 7681);
            dos.writeByte(_type);
            if (_type == 0) {
                dos.writeUTF(_nickname);
            }
            dos.writeUTF(_chat);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendFrameLeechdom(final byte _operateIndex, final byte _index, final int _ID) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 7427);
            dos.writeByte(_operateIndex);
            if (_operateIndex != 1) {
                dos.writeByte(_index);
                dos.writeInt(_ID);
            }
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendUseGoodsKey(final int _ID, final byte _seltype, final int _selId) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 7445);
            dos.writeInt(_ID);
            dos.writeByte(_seltype);
            dos.writeInt(_selId);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendFrameStuff(final byte _operateIndex, final byte _index, final int _ID) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 7430);
            dos.writeByte(_operateIndex);
            if (_operateIndex != 1) {
                dos.writeByte(_index);
                dos.writeInt(_ID);
            }
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendFrameTaskprop(final byte _operateIndex, final byte _index, final int _ID) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 7429);
            dos.writeByte(_operateIndex);
            if (_operateIndex != 1) {
                dos.writeByte(_index);
                dos.writeInt(_ID);
            }
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendFramePeculiar(final byte _operateIndex, final byte _index, final int _ID, final int _MID, final byte _slot) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 7444);
            dos.writeByte(_operateIndex);
            if (_operateIndex == 2) {
                dos.writeByte(_index);
                dos.writeInt(_ID);
                DebugFrame.getInstance().logIn(new StringBuffer().append(_ID).toString());
                dos.writeInt(120);
                dos.writeInt(_MID);
                DebugFrame.getInstance().logIn(new StringBuffer().append(_MID).toString());
                if (_slot != 0) {
                    dos.writeByte(_slot);
                }
            } else if (_operateIndex != 1) {
                dos.writeByte(_index);
                dos.writeInt(_ID);
            }
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendFrameEquip(final byte _operateIndex, final byte _index, final int _ID) {
        DebugFrame.getInstance().logIn((_operateIndex == 1) ? "\u62a5\u6587->\u88c5\u5907\u754c\u9762\u6570\u636e\u8bf7\u6c42" : ("\u62a5\u6587->\u88c5\u5907\u754c\u9762\u64cd\u4f5c\u8bf7\u6c42\uff0c \u64cd\u4f5c\u9009\u9879\uff1a" + _operateIndex + "\u4f4d\u7f6e\uff1a" + _index + "\u7f16\u53f7\uff1a" + _ID));
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 7426);
            dos.writeByte(_operateIndex);
            if (_operateIndex != 1) {
                dos.writeByte(_index);
                dos.writeInt(_ID);
            }
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendDelSeal(final byte _index, final int _ID) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 7459);
            dos.writeByte(_index);
            dos.writeInt(_ID);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendAreaMapInfo(final byte operation, final short _id, final byte areaType, final int npcId) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 10784);
            dos.writeByte(operation);
            if (operation == 1 || operation == 3 || operation == 6) {
                dos.writeShort(_id);
            } else if (operation == 5) {
                dos.writeByte(areaType);
            } else if (operation == 8) {
                dos.writeShort(_id);
                dos.writeInt(npcId);
            }
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendCityRelive() {
        NetManager.getDOS((short) 1027);
        NetManager.getInstance().addPacket();
    }

    public void sendItemRelive() {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 7477);
            final byte data = 50;
            dos.writeByte(data);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendSkillRelive(final byte _x, final byte _y, final int _hp, final int _mp) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 1040);
            dos.writeByte(_x);
            dos.writeByte(_y);
            dos.writeInt(_hp);
            dos.writeInt(_mp);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendShortcutKeys(final byte _type, final int _id, final byte _key) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 1043);
            dos.writeByte(_type);
            dos.writeInt(_id);
            dos.writeByte(_key);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendRequestInlayBook(final int _bookId, final byte _place) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 1048);
            dos.writeInt(_bookId);
            dos.writeByte(_place);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMoveShortcutKeys(final byte[] _key) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 1044);
            for (byte i = 0; i < _key.length; ++i) {
                dos.writeByte(_key[i]);
            }
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendNpcOption(final int _npcId) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 11521);
            dos.writeInt(_npcId);
            dos.writeByte(0);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendNpcOptionFake(final int _npcId) {
        try {
            if (Macro.BLN_ACCERATE_NPC_TASK) {
                final DataInputStream dis = FakeServer.getInstance().getNpcMenuStream(_npcId, 0, (byte) 1);
                if (dis != null) {
                    DCanvas.getInstance().setNetLoad(false);
                    NetParse.getInstance().npcMenu(dis);
                } else {
                    final String info = "Warning!!! \u4efb\u52a1\u54c8\u5e0c\u8868\u4e2d\u4e0d\u5b58\u5728\u8be5NPC\u6570\u636e!";
                    DebugFrame.getInstance().logIn(info);
                    this.sendNpcOption(_npcId);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendNpcOneOptionFake(final int _npcId, final byte step, final int _optionId) {
        GameUI.getInstance().intSendMenuId = _optionId;
        try {
            if (Macro.BLN_ACCERATE_NPC_TASK) {
                final DataInputStream dis = FakeServer.getInstance().getNpcMenuStream(_npcId, _optionId, (byte) (step + 1));
                if (dis != null) {
                    DCanvas.getInstance().setNetLoad(false);
                    NetParse.getInstance().npcMenu(dis);
                } else {
                    final String info = "Warning!!! \u8be5npc\u4efb\u52a1\u54c8\u5e0c\u8868\u4e2d\u4e0d\u5b58\u5728\u8be5\u7ec4\u6570\u636e!\u91cd\u65b0\u8bf7\u6c42!";
                    DebugFrame.getInstance().logIn(info);
                    this.sendNpcOneOption(_npcId, step, _optionId);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendNpcOneOption(final int _npcId, final byte step, final int _optionId) {
        this.sendNpcOneOption(_npcId, step, _optionId, true);
    }

    public void sendNpcOneOption(final int _npcId, final byte step, final int _optionId, final boolean isSimulator) {
        if (isSimulator) {
            GameUI.getInstance().intSendMenuId = _optionId;
        }
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 11521);
            dos.writeInt(_npcId);
            dos.writeByte(step);
            dos.writeInt(_optionId);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendNpcIsOption(final int _npcId, final byte step, final int _optionId, final byte _type) {
        GameUI.getInstance().intSendMenuId = _optionId;
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 11521);
            dos.writeInt(_npcId);
            dos.writeByte(step);
            dos.writeInt(_optionId);
            dos.writeByte(_type);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendNpcOption(final int _npcId, final byte step, final int _optionId, final byte _type, final int _id) {
        GameUI.getInstance().intSendMenuId = _optionId;
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 11521);
            dos.writeInt(_npcId);
            dos.writeByte(step);
            dos.writeInt(_optionId);
            dos.writeByte(_type);
            dos.writeInt(_id);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendNpcConsortiaTransfer(final int _npcId, final byte step, final int _optionId, final byte _type, final int _id) {
        GameUI.getInstance().intSendMenuId = _optionId;
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 11521);
            dos.writeInt(_npcId);
            dos.writeByte(step);
            dos.writeInt(_optionId);
            dos.writeByte(_type);
            dos.writeInt(_id);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendNpcConsortiaPage(final int _npcId, final byte step, final int _optionId, final byte _type, final byte _page) {
        GameUI.getInstance().intSendMenuId = _optionId;
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 11521);
            dos.writeInt(_npcId);
            dos.writeByte(step);
            dos.writeInt(_optionId);
            dos.writeByte(_type);
            dos.writeByte(_page);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendNpcPropOption(final int _npcId, final byte step, final int _optionId, final byte _type, final byte _index, final int _id) {
        GameUI.getInstance().intSendMenuId = _optionId;
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 11521);
            dos.writeInt(_npcId);
            dos.writeByte(step);
            dos.writeInt(_optionId);
            dos.writeByte(_type);
            dos.writeByte(_index);
            dos.writeInt(_id);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendNpcTaskOption(final int _npcId, final byte step, final int _optionId, final int _id) {
        GameUI.getInstance().intSendMenuId = _optionId;
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 11521);
            dos.writeInt(_npcId);
            dos.writeByte(step);
            dos.writeInt(_optionId);
            dos.writeInt(_id);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendNpcRecvActive(final int _npcId, final byte step, final int _optionId, final String[] content) {
        GameUI.getInstance().intSendMenuId = _optionId;
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 11521);
            dos.writeInt(_npcId);
            dos.writeByte(step);
            dos.writeInt(_optionId);
            if (content != null) {
                dos.writeByte(content.length);
                for (int i = 0; i < content.length; ++i) {
                    dos.writeUTF(content[i]);
                }
            }
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendNpcPaiBuy(final int _npcId, final byte step, final int _optionId, final byte _type, final int _id, final short _page) {
        GameUI.getInstance().intSendMenuId = _optionId;
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 11521);
            dos.writeInt(_npcId);
            dos.writeByte(step);
            dos.writeInt(_optionId);
            dos.writeByte(_type);
            dos.writeInt(_id);
            dos.writeShort(_page);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendNpcPaiSale(final int _npcId, final byte step, final int _optionId, final byte _type, final byte _index, final int _id, final int _objectNum, final int _objectMoney) {
        GameUI.getInstance().intSendMenuId = _optionId;
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 11521);
            dos.writeInt(_npcId);
            dos.writeByte(step);
            dos.writeInt(_optionId);
            dos.writeByte(_type);
            dos.writeByte(_index);
            dos.writeInt(_id);
            dos.writeInt(_objectNum);
            dos.writeInt(_objectMoney);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendNpcEail(final int _npcId, final byte step, final int _optionId, final byte _type, final byte _index, final int _id, final int _objectNum, final String _name, final String _title, final String _content) {
        GameUI.getInstance().intSendMenuId = _optionId;
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 11521);
            dos.writeInt(_npcId);
            dos.writeByte(step);
            dos.writeInt(_optionId);
            dos.writeByte(_type);
            dos.writeByte(_index);
            dos.writeInt(_id);
            dos.writeInt(_objectNum);
            dos.writeUTF(_name);
            dos.writeUTF(_title);
            dos.writeUTF(_content);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendNpcEailM(final int _npcId, final byte step, final int _optionId, final byte _type, final int _objectNum, final String _name, final String _title, final String _content) {
        GameUI.getInstance().intSendMenuId = _optionId;
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 11521);
            dos.writeInt(_npcId);
            dos.writeByte(step);
            dos.writeInt(_optionId);
            dos.writeByte(_type);
            dos.writeInt(_objectNum);
            dos.writeUTF(_name);
            dos.writeUTF(new StringBuffer(String.valueOf(_title)).toString());
            dos.writeUTF(new StringBuffer(String.valueOf(_content)).toString());
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendNpcEailText(final int _npcId, final byte step, final int _optionId, final byte _type, final String _name, final String _title, final String _content) {
        GameUI.getInstance().intSendMenuId = _optionId;
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 11521);
            dos.writeInt(_npcId);
            dos.writeByte(step);
            dos.writeInt(_optionId);
            dos.writeByte(_type);
            dos.writeUTF(_name);
            dos.writeUTF(new StringBuffer(String.valueOf(_title)).toString());
            dos.writeUTF(new StringBuffer(String.valueOf(_content)).toString());
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendNpcPaiM(final int _npcId, final byte step, final int _optionId, final byte _type, final String _name) {
        GameUI.getInstance().intSendMenuId = _optionId;
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 11521);
            dos.writeInt(_npcId);
            dos.writeByte(step);
            dos.writeInt(_optionId);
            dos.writeByte(_type);
            dos.writeUTF(_name);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendNpcInputNum(final int _npcId, final byte step, final int _optionId, final byte _type, final byte _index, final int _id, final int _num) {
        GameUI.getInstance().intSendMenuId = _optionId;
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 11521);
            dos.writeInt(_npcId);
            dos.writeByte(step);
            dos.writeInt(_optionId);
            dos.writeByte(_type);
            dos.writeByte(_index);
            dos.writeInt(_id);
            dos.writeInt(_num);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendNpcMoveOption(final short _move, final short _roll, final int _num) {
        byte _tempmove = (byte) (_move + _roll);
        final byte _size = (byte) Param.getInstance().vMenuMemory.size();
        final byte _propindex = MenuUI.getInstance().getPropRectMove();
        final MenuObject _mo = (MenuObject) Param.getInstance().vMenuMemory.elementAt(_size - 1);
        _mo.bytMove = (byte) _move;
        _mo.bytRoll = (byte) _roll;
        MenuUI.getInstance().blnIsTemp = false;
        if (_mo.vNextMenu != null && _mo.vNextMenu[_tempmove] != null && Param.getInstance().bytPopRectStep < _mo.vNextMenu[_tempmove].length) {
            final String _tempString = _mo.vNextMenu[_tempmove][Param.getInstance().bytPopRectStep].strInfo;
            final byte _type = _mo.vNextMenu[_tempmove][Param.getInstance().bytPopRectStep].bytType;
            Param.getInstance().intNpcNum = _num;
            MenuUI.getInstance().setTwoRect(_tempString, 0, _tempmove, _type, 0);
            final Param instance = Param.getInstance();
            ++instance.bytPopRectStep;
        } else if (_mo.bytType > 0 && _mo.bytType < 10) {
            final Param instance2 = Param.getInstance();
            --instance2.bytPopRectStep;
            MenuUI.getInstance().intTempOptionId = _mo.intOptionId;
            MenuUI.getInstance().intTempInputMax = _mo.intMax;
            MenuUI.getInstance().intTempInputMin = _mo.intMin;
            MenuUI.getInstance().bytTempStep = _mo.bytStep;
            MenuUI.getInstance().blnIsTemp = true;
            MenuUI.getInstance().setTwoRect(_mo.strInfo, 0, _tempmove, _mo.bytType, 0);
            Param.getInstance().vMenuMemory.removeElementAt(_size - 1);
        } else {
            final int _id = (_size == 1) ? _mo.intsOptionId[MenuUI.getInstance().getOneMove()] : _mo.intOptionId;
            final byte _step = _mo.bytStep;
            if (Param.getInstance().oSelectNpc != null) {
                final int _npcId = Param.getInstance().oSelectNpc.intUserId;
                if (Param.getInstance().hPackage != null) {
                    final int _goodsId = ((PackageObject) Param.getInstance().hPackage.get(new Integer(_propindex))).intId;
                    final byte _index = (byte) ((PackageObject) Param.getInstance().hPackage.get(new Integer(_propindex))).intPOindex;
                    if (_mo.vNextMenu == null) {
                        this.sendNpcPropOption(_npcId, _step, _id, _tempmove, _index, _goodsId);
                    } else if (Param.getInstance().bytPopRectStep > 1) {
                        this.sendNpcPaiSale(_npcId, _step, _id, _tempmove, _index, _goodsId, (Param.getInstance().intNpcNum == 0) ? 1 : Param.getInstance().intNpcNum, _num);
                        Param.getInstance().bytPopRectStep = 0;
                    } else {
                        this.sendNpcInputNum(_npcId, _step, _id, _tempmove, _index, _goodsId, _num);
                    }
                    DCanvas.getInstance().setNetLoad(true);
                    MenuUI.getInstance().clearTwoRect();
                    MenuUI.getInstance().setInfoContent(Param.getInstance().hPackage, _propindex);
                } else if (_mo.bytType == 10) {
                    Param.getInstance().blnNpcOptionWork = false;
                    Param.getInstance().intNpcOptionCounter = 4000;
                    Param.getInstance().strTempTitlestr = _mo.strOption[_tempmove];
                    DCanvas.getInstance().setNetLoad(true);
                    if (Macro.BLN_ACCERATE_NPC_TASK && FakeServer.getInstance().isOperationTask(_id)) {
                        this.sendNpcOneOptionFake(_npcId, _step, _id);
                    } else {
                        this.sendNpcOneOption(_npcId, _step, _id);
                    }
                } else if (_mo.bytType == 11 || _mo.bytType == 14) {
                    DCanvas.getInstance().setNetLoad(true);
                    this.sendNpcIsOption(_npcId, _step, _id, _tempmove);
                } else if (_mo.bytType == 31) {
                    DCanvas.getInstance().setNetLoad(true);
                    this.sendNpcIsOption(_npcId, _step, _id, _tempmove);
                } else if (_mo.bytType == 16) {
                    DCanvas.getInstance().setNetLoad(true);
                    this.sendNpcPaiBuy(_npcId, _step, _id, _tempmove, ((PackageObject) Param.getInstance().vCommonList.elementAt(MenuUI.getInstance().getOneMove())).intId, Param.getInstance().shtNoncePage);
                } else if (_mo.bytType != 15) {
                    if (_mo.bytType == -12) {
                        DCanvas.getInstance().setNetLoad(true);
                        this.sendNpcOption(_npcId, _step, _id, _tempmove, ((PackageObject) MenuUI.getInstance().vTempVessel.elementAt(MenuUI.getInstance().getOneMove())).intId);
                    } else if (_mo.bytType == 12) {
                        DCanvas.getInstance().setNetLoad(true);
                        this.sendNpcOption(_npcId, _step, _id, _tempmove, ((PackageObject) Param.getInstance().vCommonList.elementAt(MenuUI.getInstance().getOneMove())).intId);
                    } else if (_mo.bytType == 17) {
                        DCanvas.getInstance().setNetLoad(true);
                        this.sendNpcOption(_npcId, _step, _id, _tempmove, ((PackageObject) Param.getInstance().vCommonList.elementAt(MenuUI.getInstance().getOneMove())).intId);
                    } else if (_mo.bytType == 13) {
                        DCanvas.getInstance().setNetLoad(true);
                        switch (Macro.NPC_STEP_SELECT) {
                            case 2: {
                                final int buyIndex = Param.getInstance().npcShopBarTabStyle.getTabCurrentIndex();
                                final Hashtable buyTable = (Hashtable) Param.getInstance().hNpcBarPackTable.get(new Integer(buyIndex));
                                final int boxIndex = Param.getInstance().npcShopBarPackage.getCellBoxIndex();
                                int goodID = 0;
                                if (buyTable != null && buyTable.containsKey(new Integer(boxIndex))) {
                                    final PackageObject po = (PackageObject) buyTable.get(new Integer(boxIndex));
                                    goodID = po.intId;
                                }
                                if (_num > 1) {
                                    _tempmove = 1;
                                }
                                this.sendNpcInputNum(_npcId, Macro.NPC_STEP_SELECT, _id, _tempmove, (byte) boxIndex, goodID, _num);
                                break;
                            }
                            case 3: {
                                final String Name = Param.getInstance().npcShopOursTabArray[MenuUI.getInstance().tabStyleInstance.getTabCurrentIndex()];
                                final Hashtable sellTable = (Hashtable) Param.getInstance().hNpcOursPackTable.get(Name);
                                final int sboxIndex = Param.getInstance().personalPackage.getCellBoxIndex();
                                int sellID = 0;
                                if (sellTable != null && sellTable.containsKey(new Integer(sboxIndex))) {
                                    final PackageObject po2 = (PackageObject) sellTable.get(new Integer(sboxIndex));
                                    sellID = po2.intId;
                                }
                                if (_num > 1) {
                                    _tempmove = 1;
                                }
                                this.sendNpcInputNum(_npcId, Macro.NPC_STEP_SELECT, _id, _tempmove, (byte) sboxIndex, sellID, _num);
                                break;
                            }
                            case 10: {
                                final int Index = Param.getInstance().npcShopBarPackage.getCellBoxIndex();
                                int ID = 0;
                                if (Param.getInstance().hNpcStorage != null && Param.getInstance().hNpcStorage.containsKey(new Integer(Index))) {
                                    final PackageObject po3 = (PackageObject) Param.getInstance().hNpcStorage.get(new Integer(Index));
                                    ID = po3.intId;
                                }
                                if (_num > 1) {
                                    _tempmove = 1;
                                }
                                this.sendNpcInputNum(_npcId, Macro.NPC_STEP_SELECT, _id, _tempmove, (byte) Index, ID, _num);
                                break;
                            }
                            case 21:
                            case 22:
                            case 23:
                            case 24:
                            case 25: {
                                final String nameStorage = Param.getInstance().npcShopOursTabArray[MenuUI.getInstance().tabStyleInstance.getTabCurrentIndex()];
                                final Hashtable storageTable = (Hashtable) Param.getInstance().hNpcOursPackTable.get(nameStorage);
                                final int indexStorage = Param.getInstance().personalPackage.getCellBoxIndex();
                                int storageID = 0;
                                if (storageTable != null && storageTable.containsKey(new Integer(indexStorage))) {
                                    final PackageObject po4 = (PackageObject) storageTable.get(new Integer(indexStorage));
                                    storageID = po4.intId;
                                }
                                if (_num > 1) {
                                    _tempmove = 1;
                                }
                                this.sendNpcInputNum(_npcId, Macro.NPC_STEP_SELECT, _id, _tempmove, (byte) indexStorage, storageID, _num);
                                break;
                            }
                        }
                        GameControl.getInstance().delUIbase(7);
                        MenuUI.getInstance().setButton(1, 4, 2);
                    }
                }
            } else {
                DCanvas.getInstance().addInformation("\u8ddd\u79bb\u76ee\u6807\u592a\u8fdc");
                GameControl.getInstance().delUIbase(3);
            }
        }
    }

    public void sendUnlockMonsterBox(final int _boxid) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 7434);
            dos.writeInt(_boxid);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendUnlockWorldBox(final int _boxid) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 11524);
            dos.writeInt(_boxid);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendUnlockEvent(final int _Eventid) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 11537);
            dos.writeInt(_Eventid);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendUnlockEvent2(final int _Eventid) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 11525);
            dos.writeInt(_Eventid);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendExitGame() {
        NetManager.getDOS((short) 1030);
        NetManager.getInstance().addPacket();
    }

    public void sendTaskList(final byte _type, final int _id) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 3841);
            dos.writeByte(_type);
            dos.writeInt(_id);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendTaskSendList() {
        NetManager.getDOS((short) 3841);
        NetManager.getInstance().addPacket();
    }

    public void sendNpcSend(final int _taskid, final String _npcid) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 3841);
            dos.writeInt(_taskid);
            dos.writeUTF(_npcid);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendObjectSend(final int _taskid, final int _oid) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 3841);
            dos.writeInt(_taskid);
            dos.writeShort(_oid);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendTeam_add(final String _name) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 3073);
            dos.writeByte(1);
            dos.writeUTF(_name);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendTeam_option(final byte _type, final int _id) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 3073);
            dos.writeByte(_type);
            dos.writeInt(_id);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendTeam_Leave() {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 3073);
            dos.writeByte(8);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendTeamInvite(final byte _type) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 3073);
            dos.writeByte(2);
            dos.writeByte(_type);
            dos.writeUTF(Param.getInstance().strTroopInviteName);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendRoleSee(final byte type, final int _userid, final String usename) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 1045);
            dos.writeByte(type);
            if (type == 1) {
                dos.writeInt(_userid);
            } else {
                dos.writeUTF(usename);
            }
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendRoleOffLineSee(final String usename) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 1047);
            dos.writeUTF(usename);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendTeam_Transfer(final int _id, final byte _teamId) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 3073);
            dos.writeByte(6);
            dos.writeInt(_id);
            dos.writeByte(_teamId);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendSecondData() {
        NetManager.getDOS((short) 1042);
        NetManager.getInstance().addPacket1();
    }

    public void sendSecondDataIn() {
        NetManager.getDOS((short) 10756);
        NetManager.getInstance().addPacket();
    }

    public void sendDeal(final byte _type, final int _dealID) {
        DebugFrame.getInstance().logIn("\u62a5\u6587->\u4ea4\u6613|1.\u8bf7\u6c42\u30015.\u786e\u5b9a\u30016.\u53d6\u6d88\u30018.\u6b63\u5fd9->" + _type + "  \u4ea4\u6613ID(1\uff1a\u4ea4\u6613\u5bf9\u8c61ID)\uff1a" + _type);
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 23811);
            dos.writeByte(_type);
            dos.writeInt(_dealID);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendLockDeal() {
        final DataOutputStream dos = NetManager.getDOS((short) 23811);
        try {
            dos.writeByte(9);
            dos.writeInt(Param.getInstance().intDealID);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendDealBegPack(final int _dealID, final byte _goodsType) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 23811);
            dos.writeByte(3);
            dos.writeInt(_dealID);
            dos.writeByte(_goodsType);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendDealGoods(final byte _Goodstype, final int _goodsID, final short _index, final short _num) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 23811);
            dos.writeByte(4);
            dos.writeInt(Param.getInstance().intDealID);
            dos.writeByte(_Goodstype);
            dos.writeInt(_goodsID);
            dos.writeShort(_index);
            dos.writeShort(_num);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendCancelDealGoods(final int _goodsID, final short _index) {
        final DataOutputStream dos = NetManager.getDOS((short) 23811);
        try {
            dos.writeByte(11);
            dos.writeInt(Param.getInstance().intDealID);
            dos.writeShort(_index);
            dos.writeInt(_goodsID);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendDealGold(final int _gold) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 23811);
            dos.writeByte(2);
            dos.writeInt(Param.getInstance().intDealID);
            dos.writeInt(_gold);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendNpcConsortia(final int _npcId, final byte step, final int _optionId, final String _name) {
        GameUI.getInstance().intSendMenuId = _optionId;
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 11521);
            dos.writeInt(_npcId);
            dos.writeByte(step);
            dos.writeInt(_optionId);
            dos.writeUTF(_name);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendConsortiaAdd(final String _name) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 2561);
            dos.writeByte(2);
            dos.writeUTF(_name);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendConsortia(final byte _type, final byte _index, final int _id) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 2561);
            dos.writeByte(_type);
            switch (_type) {
                case 1: {
                    ORPMe.ME.shtCNowNum = -1;
                    dos.writeByte(_index);
                    break;
                }
                case 3: {
                    dos.writeInt(_id);
                    break;
                }
                case 4: {
                    dos.writeByte(_index);
                    dos.writeInt(_id);
                    break;
                }
            }
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendConsortiaInvite(final byte _type) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 2561);
            dos.writeByte(6);
            dos.writeByte(_type);
            dos.writeInt(Param.getInstance().intGuildRoleId);
            dos.writeInt(Param.getInstance().intConsortiaId);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMailList() {
        NetManager.getDOS((short) 23814);
        NetManager.getInstance().addPacket();
    }

    public void sendMailNew(final String _name, final String _title, final String _content) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 23815);
            dos.writeUTF(_name);
            dos.writeUTF(_title);
            dos.writeUTF(_content);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMailRenovate(final byte _type, final int _id) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 23816);
            dos.writeByte(_type);
            dos.writeInt(_id);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendRoleEQUIP(final int _id) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 7441);
            dos.writeInt(_id);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendHailFellow(final byte _option, final byte _type, final String _name) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 24321);
            dos.writeByte(_option);
            dos.writeByte(_type);
            if (_option != 1) {
                dos.writeUTF(_name);
            }
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendOperateEpurate(final byte _type, final int _id) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 23555);
            dos.writeByte(_type);
            if (_type == 1 || _type == 2) {
                dos.writeInt(_id);
            }
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendGetTradeList(final byte _type, final int _Skillid, final byte _manuftype, final int _equipid) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 24065);
            dos.writeByte(_type);
            if (_type == 1) {
                dos.writeByte(_Skillid);
            }
            if (_type == 2) {
                dos.writeByte(_Skillid);
            }
            if (_type == 3) {
                dos.writeInt(_equipid);
            }
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendSelectGoods(final short _goodsId, final byte _type) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 7446);
            dos.writeShort(_goodsId);
            dos.writeByte(_type);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendPlayerPk(final int _goodsId, final byte _type) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 24833);
            dos.writeByte(_type);
            dos.writeInt(_goodsId);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendFBCD() {
        NetManager.getDOS((short) 10497);
        NetManager.getInstance().addPacket();
    }

    public void sendUpdatePacket(final byte iperation, final byte _Id) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 7447);
            dos.writeByte(iperation);
            dos.writeByte(_Id);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendTeach(final byte _type) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 11777);
            dos.writeByte(_type);
            NetManager.getInstance().addPacket1();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendStrengthen(final byte _propID, final byte operate, final int jewelID, final byte holeID) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 7456);
            dos.writeByte(_propID);
            dos.writeByte(operate);
            if (operate == 1) {
                dos.writeInt(jewelID);
                dos.writeByte(holeID);
            }
            if (operate == 2) {
                dos.writeByte(holeID);
            }
            if (operate == 3) {
                dos.writeInt(jewelID);
                dos.writeByte(holeID);
            }
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendTimeAaking() {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 7577);
            final byte _time = 0;
            dos.writeByte(_time);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendClarion(final String _str) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 7460);
            dos.writeByte(FormDes.getInstance().bytClarionPlace);
            dos.writeUTF(_str);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendCoordinate() {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 7461);
            dos.writeByte(Param.getInstance().bytCrystalType);
            dos.writeByte(Param.getInstance().bytCrystalPlace);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendPetHandle(final byte _opetation, final int _petid) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 14849);
            dos.writeByte(_opetation);
            if (_opetation == 1 || _opetation == 8 || _opetation == 10 || _opetation == 11) {
                dos.writeInt(0);
            } else {
                dos.writeInt(_petid);
                DebugFrame.getInstance().logIn("\u4e3a\u7a7a:" + _petid);
            }
            if (_opetation == 5) {
                dos.writeByte(0);
            }
            if (_opetation == 9) {
                dos.writeShort(Macro.pet_x);
                dos.writeShort(Macro.pet_y);
                dos.writeShort(0);
                DebugFrame.getInstance().logIn("pet_x:" + Macro.pet_x);
                DebugFrame.getInstance().logIn("pet_y:" + Macro.pet_y);
            }
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendPetRename(final int _petid, final String _name) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 14853);
            dos.writeInt(_petid);
            dos.writeUTF(_name);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendPetDeal(final int _buyroleid, final int _sellroleid, final int _petid) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 14855);
            dos.writeInt(_buyroleid);
            dos.writeInt(_sellroleid);
            dos.writeInt(_petid);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendPetFeeding(final int _petid, final int _value) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 14859);
            dos.writeInt(_petid);
            dos.writeInt(_value);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendPetExperience(final int _petid, final byte _type) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 14857);
            dos.writeInt(_petid);
            dos.writeByte(_type);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendPetPoint(final int _petid, final byte _slot, final int _points) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 14862);
            dos.writeInt(_petid);
            dos.writeByte(_slot);
            dos.writeInt(_points);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendPetHandle_Set() {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 14849);
            dos.writeByte(5);
            dos.writeByte((byte) (Param.getInstance().bytPetSaleType + 1));
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendPetHandle_Sale(final byte _type) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 14849);
            dos.writeByte(_type);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendPetPack(final byte _operation, final int _petID, final byte _gridIndex, final int _equipID) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 7463);
            dos.writeByte(_operation);
            if (_operation == 2 || _operation == 3 || _operation == 4) {
                dos.writeInt(_petID);
                dos.writeByte(_gridIndex);
                dos.writeInt(_equipID);
            }
            if (_operation == 6) {
                dos.writeByte(_gridIndex);
                dos.writeInt(_equipID);
            }
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendPetProp(final int petid) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 14861);
            dos.writeInt(petid);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendPetSkill(final int petid) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 14863);
            dos.writeInt(petid);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendPetUseSkill(final int petid, final int skillid, final byte type, final int hitid, final byte face) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 14863);
            dos.writeInt(petid);
            dos.writeInt(skillid);
            dos.writeByte(type);
            dos.writeInt(hitid);
            dos.writeByte(face);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendShop_OpenBag(final byte _type) {
        DebugFrame.getInstance().logIn("\u62a5\u6587->\u6253\u5f00\u80cc\u5305\uff1a_type = " + _type);
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 11265);
            dos.writeByte(2);
            dos.writeByte(_type);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendShop_DelProp(final byte _place, final int _id) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 11265);
            dos.writeByte(3);
            dos.writeByte(_place);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendShop_AmendMoney(final byte _place, final int _id, final int _money) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 11265);
            dos.writeByte(4);
            dos.writeByte(_place);
            dos.writeInt(_money);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendShopHandle(final byte _type) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 11265);
            dos.writeByte(_type);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendShopOpen() {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 11265);
            dos.writeByte(7);
            dos.writeInt(Param.getInstance().oSelectRole.intUserId);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendShopBuy(final byte _place, final int _id) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 11265);
            dos.writeByte(8);
            dos.writeInt(Param.getInstance().oSelectRole.intUserId);
            dos.writeByte(_place);
            dos.writeInt(_id);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendAddShopProp() {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 11265);
            dos.writeByte(5);
            String _name = "";
            if (MenuUI.getInstance().strShopName.equals("")) {
                _name = String.valueOf(ORPMe.ME.strNickName) + "\u5546\u5e97";
            } else {
                _name = MenuUI.getInstance().strShopName;
            }
            dos.writeUTF(_name);
            dos.writeByte(MenuUI.getInstance().bytAddShopNumber);
            final Enumeration _e = Param.getInstance().hShopPackage.elements();
            while (_e.hasMoreElements()) {
                final PackageObject _po = (PackageObject) _e.nextElement();
                if (_po.blnUse) {
                    dos.writeByte(_po.bytKey);
                    dos.writeByte(_po.bytBagPlace);
                    dos.writeInt(_po.intId);
                    dos.writeByte(_po.shtPOnum);
                    dos.writeByte(_po.intPOindex);
                    dos.writeInt(_po.intPaiMaiPrice);
                }
            }
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendShiTu_Option(final byte _type, final int _id) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 11297);
            dos.writeByte(_type);
            if (_type == 4 || _type == 3) {
                dos.writeInt(_id);
            }
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendShiTu(final byte _type, final int _id) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 11298);
            dos.writeByte(_type);
            dos.writeInt(_id);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendReloadLand() {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 630);
            dos.writeInt(Macro.RELOAD_ROLE_ID);
            dos.writeShort(Macro.RELOAD_Server_ID);
            dos.writeUTF(Rms.strRmsName);
            dos.writeUTF(Macro.VERSION);
            NetManager.getInstance().addPacket1();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendReload() {
        NetManager.getDOS((short) 1041);
        NetManager.getInstance().addPacket1();
    }

    public void sendFee_See(final byte _type, final String _stime, final String _etime) {
    }

    public void sendMallSerial(final int price, final String swcode, final String mobileUserid, final int publisher) {
        final DataOutputStream dos = NetManager.getDOS((short) 15110);
        NetManager.getInstance().addPacket();
    }

    public void sendMallOtherSerial(final int price, final String forName, final String swcode, final String mobileUserid, final int publisher) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 15111);
            dos.writeUTF(forName);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMallSee(final byte type) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 15123);
            dos.writeByte(type);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMallRecharge(final byte id, final String transID, final String mid, final int userID, final int accountID, final String numNO, final String numPwd) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 15120);
            dos.writeByte(id);
            dos.writeUTF(transID);
            dos.writeUTF(mid);
            dos.writeInt(userID);
            dos.writeInt(accountID);
            dos.writeInt(1);
            dos.writeUTF("10.0.0.172");
            dos.writeUTF(numNO);
            dos.writeUTF(numPwd);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendShop_List(final short _type) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 15109);
            dos.writeByte(1);
            dos.writeShort(_type);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendRechargeList() {
        final DataOutputStream dos = NetManager.getDOS((short) 15126);
        NetManager.getInstance().addPacket();
    }

    public void sendShop_Buy(final int _id, final byte _num) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 15109);
            dos.writeByte(2);
            dos.writeInt(_id);
            dos.writeByte(_num);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendGM_CHAT(final String _name, final String _content) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 15362);
            dos.writeUTF(_name);
            dos.writeInt(Param.getInstance().intGMSessionID);
            dos.writeInt(Param.getInstance().intGMQuestionID);
            dos.writeUTF(_content);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendGM(final byte _title, final String _content) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 15364);
            dos.writeByte(_title);
            dos.writeUTF(_content);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void snedGmAppraise(final byte _type) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 15363);
            dos.writeInt(Param.getInstance().intGMSessionID);
            dos.writeByte(_type);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendSeeChatProp(final int _id) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 7462);
            dos.writeInt(_id);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendSearchTaskList() {
        NetManager.getDOS((short) 3843);
        NetManager.getInstance().addPacket();
    }

    public void sendTaskCarry_oknpc(final byte _type, final int _taskid) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 3842);
            dos.writeByte(_type);
            dos.writeInt(_taskid);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendTaskCarry_target(final int _taskid, final int _targetid) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 3842);
            dos.writeByte(2);
            dos.writeInt(_taskid);
            dos.writeInt(_targetid);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendOroleName(final String name) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 7682);
            dos.writeUTF(name);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMarry(final int userId, final int askId, final byte type, final byte answer) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 11526);
            dos.writeInt(userId);
            dos.writeInt(askId);
            dos.writeByte(type);
            dos.writeByte(answer);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendSkillUp(final int type, final int skillId) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 3603);
            dos.writeByte((byte) type);
            dos.writeInt(skillId);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendCopyModelState(final short mapID, final short _mapx, final short _mapy, final byte _copemodel) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 10498);
            dos.writeShort(mapID);
            dos.writeShort(_mapx);
            dos.writeShort(_mapy);
            dos.writeByte(_copemodel);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendDealAsk(final int roleID, final int playID, final byte result) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 23830);
            dos.writeInt(roleID);
            dos.writeInt(playID);
            dos.writeByte(result);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendAskPartner(final byte _type) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 23041);
            dos.writeByte(_type);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendDddPartner(final byte _type, final String _lovename) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 11528);
            dos.writeByte(_type);
            dos.writeUTF(_lovename);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendIfBuyDivorce(final byte _typeBuy, final byte _answer) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 11529);
            dos.writeByte(_typeBuy);
            dos.writeByte(_answer);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendPartnerAnswer(final int _copyID, final short _weddingID, final byte _type) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 11527);
            dos.writeInt(_copyID);
            dos.writeShort(_weddingID);
            dos.writeByte(_type);
            NetManager.getInstance().addPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendFeeMessage() {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 3846);
            dos.writeInt(Param.Fee_Serial_Id);
            NetManager.getInstance().addPacket();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendAcceptFeeMessage() {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 3845);
            dos.writeInt(Param.Fee_Serial_Id);
            dos.writeUTF(Macro.VERSION);
            dos.writeUTF(Param.chinaMobileUserID);
            NetManager.getInstance().addPacket();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendAcceptFeeMessage_Double_Check() {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 3844);
            dos.writeInt(Param.Fee_Serial_Id);
            dos.writeByte(Param.Task_Fee_Type);
            dos.writeUTF(Param.Fee_Trans_Id);
            if (Param.Task_Fee_Type == 2) {
                dos.writeUTF(Param.Fee_Proxy_pCode);
                dos.writeByte(Param.Fee_Proxy_ID);
                dos.writeUTF(Param.chinaMobileUserID);
            }
            NetManager.getInstance().addPacket();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendCancelAcceptFeeMessage() {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 3847);
            dos.writeInt(Param.Fee_Serial_Id);
            NetManager.getInstance().addPacket();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendCancelAcceptFeeMessage_Double_Check() {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 3848);
            dos.writeInt(Param.Fee_Serial_Id);
            NetManager.getInstance().addPacket();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendRankList(final byte _type, final byte _menuLevel, final byte _menuID, final byte _secondMenuID, final byte _thirdMenuID) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 23833);
            dos.writeByte(_type);
            if (_type == 1) {
                dos.writeByte(_menuLevel);
                dos.writeByte(_menuID);
                if (_menuLevel == 3) {
                    dos.writeByte(_secondMenuID);
                    dos.writeByte(_thirdMenuID);
                }
            }
            NetManager.getInstance().addPacket();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendHokAnswer(final byte _answer) {
        try {
            final DataOutputStream dos = NetManager.getDOS(Param.Msg_Hok_Command_Id);
            dos.writeByte(_answer);
            NetManager.getInstance().addPacket();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendBindingPhone(final String _accound, final String _password, final String _phoneNumber) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 665);
            dos.writeUTF(_accound);
            dos.writeUTF(_password);
            dos.writeUTF(_phoneNumber);
            NetManager.getInstance().addPacket();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendRetrievePassword(final String _accound) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 666);
            dos.writeUTF(_accound);
            NetManager.getInstance().addPacket();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendSevriseList() {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 617);
            NetManager.getInstance().addPacket();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendQuickRegister() {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 667);
            dos.writeUTF(Macro.VERSION);
            dos.writeInt(1);
            dos.writeUTF(new StringBuffer(String.valueOf(Macro.CLIVER)).toString());
            NetManager.getInstance().addPacket();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendNotice(final byte _type) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 23843);
            dos.writeByte(_type);
            NetManager.getInstance().addPacket();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendNoticeContent(final int _id) {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 23844);
            dos.writeInt(_id);
            NetManager.getInstance().addPacket();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendOffLine() {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 23847);
            NetManager.getInstance().addPacket();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendLoadingTips() {
        try {
            final DataOutputStream dos = NetManager.getDOS((short) 668);
            NetManager.getInstance().addPacket();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
