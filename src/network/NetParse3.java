// Decompiled with: CFR 0.151
// Class Version: 1
package network;

import base.DCanvas;
import base.GameControl;
import base.Macro;
import base.Param;
import common.DrawBase;
import common.IDefines;
import common.RoleViewStyle;
import face.DialogUI;
import face.FormDes;
import face.FullInfo;
import face.GameUI;
import face.LandUI;
import face.LoadingUI;
import face.MenuUI;
import face.SystemMenuUI;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import javax.microedition.lcdui.Image;
import means.ChatContent;
import means.DebugFrame;
import means.ImageManager;
import means.QSprite;
import means.Rms;
import means.StringManager;
import model.AnimalObject;
import model.Box;
import model.MailObject;
import model.Map;
import model.ODecorative;
import model.ONpc;
import model.ORMonster;
import model.ORPMe;
import model.ORPlayer;
import model.ORole;
import model.PackageObject;
import model.PetObject;
import model.ResPoolFactory;
import model.ResPoolLevelFunction;
import model.SkillObject;
import model.Task;
import model.TaskObject;
import model.TeamObject;

public class NetParse3 {

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled aggressive exception aggregation
     */
    public short parsePacket(byte[] byArray) {
        short s;
        block623:
        {
            s = -1;
            try {
                Object object;
                DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(byArray));
                s = dataInputStream.readShort();
                if (Macro.netRecvFollowDOS == s) {
                    Macro.BLN_HTTP_TIME = false;
                    Macro.TIME_RESPONSE_CUT = System.currentTimeMillis();
                    object = "R:" + Macro.netSendFollowDOS + ">" + Macro.netRecvFollowDOS + "=" + "T:" + (Macro.TIME_RESPONSE_CUT - Macro.TIME_START_CUT);
                    if (Macro.netUseInfoVector.size() > 5) {
                        Macro.netUseInfoVector.removeAllElements();
                    }
                    Macro.netUseInfoVector.addElement(object);
                }
                block5:
                switch (s) {
                    case 10753: {
                        NetHandler.sessionId = NetParse.getInstance().intPSessionId;
                        if (DCanvas.getInstance().ULoading == null) {
                            GameControl.getInstance().delUIbase(1);
                            GameControl.getInstance().CreateState((byte) 6);
                        }
                        GameControl.getInstance().CreateState((byte) 2);
                        GameControl.getInstance().inGame();
                        NetManager.getInstance().blnOnceErr = false;
                        NetManager.getInstance().blnSendHeart = true;
                        DCanvas.getInstance().setNetLoad(true);
                        DebugFrame.getInstance().logIn("8026开始获取RMS数据：" + System.currentTimeMillis());
                        Rms.getMall();
                        DebugFrame.getInstance().logIn("8026开始请求商城数据：" + System.currentTimeMillis());
                        NetSend.getInstance().sendShop_List(Param.MALL_VERSION);
                        break;
                    }
                    case 10754: {
                        Macro.shtGameDataReceived = (short) (Macro.shtGameDataReceived | 1);
                        if (Macro.bytGameType == 1) {
                            ResPoolFactory.getInstance().ClearPool(3);
                            ResPoolFactory.getInstance().ClearPool(4);
                            ResPoolFactory.getInstance().ClearPool(7);
                            ResPoolFactory.getInstance().ClearPool(5);
                            ResPoolFactory.getInstance().ClearPool(6);
                            ResPoolLevelFunction.getInstance().cleanAllLevelFunctionData();
                        }
                        Param.getInstance().vOptionPlace = null;
                        if (Macro.bytGameType == 1) {
                            GameUI.getInstance().setLoading();
                        }
                        LoadingUI.getInstance().setSpeed(80);
                        NetParse.getInstance().readMapData(dataInputStream);
                        NetParse.getInstance().sendSecondData();
                        GameUI.getInstance().setButton(0, 100, 3);
                        if (!Rms.blnShowRole) {
                            GameUI.getInstance().pushRoleStateIcon((short) 3);
                        }
                        break;
                    }
                    case 1029: {
                        NetParse.getInstance().readMeData(dataInputStream);
                        LoadingUI.getInstance().setSpeed(90);
                        Map.getInstance().setCameraTileXY(ORPMe.ME.bytTileX, ORPMe.ME.bytTileY);
                        LoadingUI.getInstance().setSpeed(100);
                        Macro.bytGameType = 1;
                        NetManager.getInstance().shtHeartTimeMax = (short) 1000;
                        NetSend.getInstance().sendTimeAaking();
                        NetSend.getInstance().sendNotice((byte) 0);
                        break;
                    }
                    case 10773: {
                        int n = 100;
                        short s2 = dataInputStream.readShort();
                        int n2 = dataInputStream.readByte();
                        Param.getInstance().DecorativeNum = n2;
                        if (Param.getInstance().htDecorative == null) {
                            Param.getInstance().htDecorative = new Hashtable();
                        }
                        int n3 = 0;
                        while (n3 < n2) {
                            short s3 = dataInputStream.readShort();
                            short s4 = dataInputStream.readShort();
                            byte by = dataInputStream.readByte();
                            byte by2 = dataInputStream.readByte();
                            byte by3 = dataInputStream.readByte();
                            byte by4 = dataInputStream.readByte();
                            ODecorative oDecorative = new ODecorative();
                            oDecorative.newDecorative(by, by2, by3);
                            oDecorative.setDecorativePic(n3, s3, s4, by4);
                            GameUI.getInstance().addObject(oDecorative);
                            ++n3;
                        }
                        break;
                    }
                    case 735: {
                        NetManager.getInstance().blnSendHeart = true;
                        NetHandler.sessionId = NetParse.getInstance().intPSessionId;
                        LandUI.getInstance().blnIsSaveLandInfo = true;
                        Macro.RELOAD_ROLE_ID = dataInputStream.readInt();
                        object = dataInputStream.readUTF();
                        if (GameControl.getInstance().isPhone && Param.MOBILE_USER_RUL != null) {
                            String string = String.valueOf(Param.MOBILE_USER_RUL) + Macro.RELOAD_ROLE_ID + (String) object;
                            NetManager.sendHttpGet(string);
                        }
                        byte by = dataInputStream.readByte();
                        Macro.strsServerList = new String[by];
                        Macro.shtsServerList = new short[by];
                        Macro.blnServerList = new boolean[by];
                        Macro.ServerState = new byte[by];
                        short[] sArray = new short[by];
                        int n = 0;
                        byte by5 = 0;
                        while (by5 < by) {
                            byte by6 = dataInputStream.readByte();
                            if (Rms.ServiceID >= 0 && Rms.ServiceID < by) {
                                sArray[by6 - 1] = dataInputStream.readShort();
                                if (sArray[by6 - 1] == Rms.ServiceID) {
                                    Macro.shtsServerList[0] = Rms.ServiceID;
                                    Macro.strsServerList[0] = dataInputStream.readUTF();
                                    Macro.blnServerList[0] = dataInputStream.readByte() == 1;
                                    Macro.ServerState[0] = dataInputStream.readByte();
                                } else {
                                    n = (byte) (n + 1);
                                    Macro.shtsServerList[n] = sArray[by6 - 1];
                                    Macro.strsServerList[n] = dataInputStream.readUTF();
                                    Macro.blnServerList[n] = dataInputStream.readByte() == 1;
                                    Macro.ServerState[n] = dataInputStream.readByte();
                                }
                            } else {
                                Macro.shtsServerList[by6 - 1] = dataInputStream.readShort();
                                Macro.strsServerList[by6 - 1] = dataInputStream.readUTF();
                                Macro.blnServerList[by6 - 1] = dataInputStream.readByte() == 1;
                                Macro.ServerState[by6 - 1] = dataInputStream.readByte();
                            }
                            by5 = (byte) (by5 + 1);
                        }
                        LandUI.getInstance().chooseServise = true;
                        if (!LandUI.getInstance().strLandAccounts.equals("")) {
                            Rms.saveUseCode(LandUI.getInstance().AutoLogin);
                            Rms.saveID(LandUI.getInstance().strLandAccounts, LandUI.getInstance().strLandPassword, LandUI.getInstance().blnIsSaveLandInfo);
                        } else {
                            Rms.saveID(Rms.strRmsName, Rms.strRmsPassword, Rms.blnIsSaveLandInfo);
                            Rms.saveUseCode(Rms.blnIsSava);
                        }
                        if (Param.getInstance().Tip_Content == null) {
                            NetSend.getInstance().sendLoadingTips();
                        }
                        break;
                    }
                    case 736: {
                        object = dataInputStream.readUTF();
                        if (((String) object).indexOf("昵称") != -1) {
                            LandUI landUI = LandUI.getInstance();
                            LandUI.getInstance().getClass();
                            LandUI.getInstance().getClass();
                            landUI.setTwoDialog((String) object, (byte) 0, (byte) -1);
                            break;
                        }
                        LandUI.getInstance().twodialog = null;
                        DCanvas.getInstance().addInformation((String) object);
                        LandUI landUI = LandUI.getInstance();
                        LandUI.getInstance().getClass();
                        LandUI.getInstance().getClass();
                        LandUI.getInstance().getClass();
                        landUI.setButton((byte) 0, (byte) -1, (byte) 1);
                        break;
                    }
                    case 750: {
                        NetHandler.sessionId = NetParse.getInstance().intPSessionId;
                        dataInputStream.readShort();
                        byte by = dataInputStream.readByte();
                        DebugFrame.getInstance().logIn("玩家数量：" + by);
                        LandUI.getInstance().initRoleList();
                        if (by > 0) {
                            byte[] byArray2 = new byte[by];
                            byte by7 = 0;
                            while (by7 < by) {
                                byArray2[by7] = dataInputStream.readByte();
                                by7 = (byte) (by7 + 1);
                            }
                            by7 = 0;
                            while (by7 < by) {
                                short[] sArray;
                                int n = dataInputStream.readInt();
                                String string = dataInputStream.readUTF();
                                DebugFrame.getInstance().logIn("_name:" + string);
                                byte by8 = dataInputStream.readByte();
                                DebugFrame.getInstance().logIn("_vocationDesc:" + by8);
                                byte by9 = dataInputStream.readByte();
                                byte by10 = dataInputStream.readByte();
                                short s5 = dataInputStream.readShort();
                                DebugFrame.getInstance().logIn("=====_level:" + s5);
                                ORPlayer oRPlayer = new ORPlayer();
                                LandUI.getInstance().newRoleList(oRPlayer, n, string, by8, s5, by10, byArray2[by7], by9);
                                oRPlayer.addRoleAllRes(dataInputStream);
                                if (QSprite.getPng(5, "/equip/" + oRPlayer.shtEquipId + ".png") == null || QSprite.getData(5, "/equip/" + oRPlayer.shtEquipAnuId + ".anu") == null) {
                                    sArray = ResPoolLevelFunction.getInstance()._getDefault_LF_Equip(oRPlayer.bytOccupation);
                                    oRPlayer.addRoleRes(2, sArray[0], sArray[1]);
                                }
                                if (QSprite.getPng(6, "/weapon/" + oRPlayer.shtWeaponId + ".png") == null || QSprite.getData(6, "/weapon/" + oRPlayer.shtWeaponAnuId + ".anu") == null) {
                                    sArray = ResPoolLevelFunction.getInstance()._getDefault_LF_Weapon(oRPlayer.bytWeaponPart);
                                    oRPlayer.addRoleRes(4, sArray[0], sArray[1]);
                                }
                                oRPlayer.pushTask((byte) 0, (byte) 2, (byte) 1);
                                oRPlayer.roleTaskAction(0);
                                LandUI.getInstance().hPlayerList.put(new Integer(byArray2[by7]), oRPlayer);
                                by7 = (byte) (by7 + 1);
                            }
                        }
                        DCanvas.getInstance().setNetLoad(true);
                        LandUI landUI = LandUI.getInstance();
                        LandUI.getInstance().getClass();
                        LandUI.getInstance().getClass();
                        landUI.setTwoDialog("正在获取角色数据", (byte) -1, (byte) -1);
                        NetSend.getInstance().sendNewRoleDefault();
                        break;
                    }
                    case 753: {
                        byte by = dataInputStream.readByte();
                        int n = dataInputStream.readInt();
                        String string = dataInputStream.readUTF();
                        byte by11 = dataInputStream.readByte();
                        byte by12 = dataInputStream.readByte();
                        byte by13 = dataInputStream.readByte();
                        short s6 = dataInputStream.readShort();
                        ORPlayer oRPlayer = new ORPlayer();
                        LandUI.getInstance().newRoleList(oRPlayer, n, string, by11, s6, by13, by, by12);
                        oRPlayer.addRoleAllRes(dataInputStream);
                        LandUI.getInstance().chooseRole = true;
                        LandUI.getInstance().setAcrossInfo(LandUI.getInstance().bytDialogData[0]);
                        LandUI.getInstance().twodialog = null;
                        oRPlayer.pushTask((byte) 0, (byte) 2, (byte) 1);
                        oRPlayer.roleTaskAction(0);
                        LandUI.getInstance().hPlayerList.put(new Integer(by), oRPlayer);
                        break;
                    }
                    case 738: {
                        DCanvas.getInstance().initRoleDefaultPart();
                        DCanvas.getInstance().readRoleDefaultPart(dataInputStream);
                        DCanvas.getInstance().initRoleFoundationPart();
                        DCanvas.getInstance().readRoleFoundationPart(dataInputStream);
                        LandUI.getInstance().chooseRole = true;
                        LandUI.getInstance().addFream();
                        if (LoadingUI.getInstance() != null) {
                            LoadingUI.getInstance().setSpeed(100);
                        }
                        break;
                    }
                    case 752: {
                        int n = dataInputStream.readInt();
                        int n4 = 0;
                        while (n4 < 3) {
                            if (LandUI.getInstance().hPlayerList.containsKey(new Integer(n4)) && ((ORPlayer) LandUI.getInstance().hPlayerList.get((Object) new Integer((int) n4))).intUserId == n) {
                                LandUI.getInstance().hPlayerList.remove(new Integer(n4));
                                break;
                            }
                            n4 = (byte) (n4 + 1);
                        }
                        LandUI.getInstance().twodialog = null;
                        LandUI.getInstance().setAcrossInfo(LandUI.getInstance().bytDialogData[0]);
                        break;
                    }
                    case 751: {
                        int n;
                        NetHandler.sessionId = n = dataInputStream.readInt();
                        String string = dataInputStream.readUTF();
                        String string2 = dataInputStream.readUTF();
                        String string3 = dataInputStream.readUTF();
                        DebugFrame.getInstance().logIn("_httpURLAddr:" + string);
                        DebugFrame.getInstance().logIn("_httpURLContext:" + string2);
                        DebugFrame.getInstance().logIn("_socketUrl:" + string3);
                        DebugFrame.getInstance().logIn("Rms.blnSelectHttp:" + Rms.blnSelectHttp);
                        Param.str_http_url = string;
                        Param.str_http_context = string2;
                        Param.bln_socket_url = string3;
                        NetManager.getInstance().stopNetManager(NetManager.getInstance().MC);
                        if (Rms.blnSelectHttp) {
                            Param.bln_http_recont = true;
                            NetManager.getInstance().setConnectionType((byte) 1, String.valueOf(string) + string2);
                        } else {
                            Param.bln_socket_recont = true;
                            NetManager.getInstance().setConnectionType((byte) 2, string3);
                        }
                        NetSend.getInstance().sendGetMap();
                        break;
                    }
                    case 1032: {
                        ORPlayer oRPlayer;
                        int n = dataInputStream.readInt();
                        dataInputStream.readByte();
                        byte by = dataInputStream.readByte();
                        byte[] byArray3 = new byte[by];
                        dataInputStream.readFully(byArray3);
                        byte by14 = dataInputStream.readByte();
                        byte by15 = dataInputStream.readByte();
                        if (Param.getInstance().htRolePlayer != null && (oRPlayer = (ORPlayer) Param.getInstance().htRolePlayer.get(new Integer(n))) != null) {
                            int n5 = 0;
                            while (n5 < byArray3.length) {
                                oRPlayer.pushTask((byte) 1, byArray3[n5]);
                                ++n5;
                            }
                            oRPlayer.pushTask((byte) 7, by14, by15);
                        }
                        break;
                    }
                    case 10755: {
                        object = NetParse.getInstance().readPlayerData(dataInputStream, false);
                        if (Macro.BLN_LEVEL_FUNCTION_EQUIP) {
                            ResPoolLevelFunction.getInstance().LevelFunction_update((byte) 1);
                        }
                        if (Macro.BLN_LEVEL_FUNCTION_WEAPON) {
                            ResPoolLevelFunction.getInstance().LevelFunction_update((byte) 2);
                        }
                        break;
                    }
                    case 11555: {
                        short s7 = dataInputStream.readShort();
                        if (s7 == Map.getInstance().shtMapId) {
                            if (Param.getInstance().htRoleMonster == null) {
                                Param.getInstance().htRoleMonster = new Hashtable(1);
                            }
                            NetParse.getInstance().readMonsterData(dataInputStream, false);
                        }
                        break;
                    }
                    case 11554: {
                        short s8 = dataInputStream.readShort();
                        if (s8 == Map.getInstance().shtMapId) {
                            short s9 = GameControl.getInstance().initCreateNpc((short) 1);
                            ONpc oNpc = NetParse.getInstance().readNpcData(dataInputStream, s9);
                            if (oNpc != null && Macro.BLN_ACCERATE_NPC_TASK && oNpc != null) {
                                NetSend.getInstance().sendNpcOption(oNpc.intUserId);
                            }
                        }
                        break;
                    }
                    case 10758: {
                        ONpc oNpc;
                        byte by = dataInputStream.readByte();
                        int n = dataInputStream.readInt();
                        int n6 = dataInputStream.readInt();
                        int n7 = dataInputStream.readInt();
                        int n8 = dataInputStream.readInt();
                        int n9 = dataInputStream.readInt();
                        if (by == 2) {
                            if (Param.getInstance().htRolePlayer != null) {
                                GameUI.getInstance().updateTeamHP((ORPlayer) Param.getInstance().htRolePlayer.get(new Integer(n)), n6, n7);
                                GameUI.getInstance().updateTeamMP((ORPlayer) Param.getInstance().htRolePlayer.get(new Integer(n)), n8, n9);
                                GameUI.getInstance().delVObject((ORPlayer) Param.getInstance().htRolePlayer.get(new Integer(n)));
                            }
                        } else if (by == 3) {
                            if (Param.getInstance().htRoleMonster != null) {
                                GameUI.getInstance().delObject((ORMonster) Param.getInstance().htRoleMonster.get(new Integer(n)));
                            }
                        } else if (by == 1 && Param.getInstance().htRoleNPC != null && (oNpc = (ONpc) Param.getInstance().htRoleNPC.get(new Integer(n))) != null) {
                            GameUI.getInstance().delObject(oNpc);
                        }
                        break;
                    }
                    case 1043: {
                        object = dataInputStream.readUTF();
                        FullInfo.getInstance().setFullRectMenu((String) object);
                        break;
                    }
                    case 11556: {
                        ORMonster oRMonster;
                        int n = dataInputStream.readInt();
                        byte by = dataInputStream.readByte();
                        byte by16 = dataInputStream.readByte();
                        if (Param.getInstance().htRoleMonster != null && (oRMonster = (ORMonster) Param.getInstance().htRoleMonster.get(new Integer(n))) != null) {
                            oRMonster.bytChaseX = by;
                            oRMonster.bytChaseY = by16;
                            oRMonster.pushTask((byte) 3, (byte) 1);
                        }
                        break;
                    }
                    case 11545: {
                        ORMonster oRMonster;
                        int n = dataInputStream.readInt();
                        byte by = dataInputStream.readByte();
                        int n10 = dataInputStream.readByte();
                        byte[] byArray4 = new byte[n10];
                        dataInputStream.readFully(byArray4);
                        if (Param.getInstance().htRoleMonster != null && (oRMonster = (ORMonster) Param.getInstance().htRoleMonster.get(new Integer(n))) != null) {
                            oRMonster.setSpeed(by);
                            int n11 = 0;
                            while (n11 < n10) {
                                if (byArray4[n11] != 0) {
                                    oRMonster.pushTask((byte) 1, byArray4[n11]);
                                }
                                ++n11;
                            }
                        }
                        break;
                    }
                    case 7681: {
                        String string;
                        byte by = dataInputStream.readByte();
                        int n = Macro.INT_CHAT_COLOR[by];
                        String string4 = string = dataInputStream.readUTF();
                        if (string.indexOf("GM") != -1 || string.indexOf("gm") != -1) {
                            string4 = "#F26 " + string;
                        }
                        String string5 = "";
                        if (by == 0 && ((string5 = dataInputStream.readUTF()).indexOf("GM") != -1 || string5.indexOf("gm") != -1)) {
                            string5 = "#F26 " + string5;
                        }
                        String string6 = dataInputStream.readUTF();
                        StringBuffer stringBuffer = new StringBuffer();
                        switch (by) {
                            case 0: {
                                stringBuffer.append("【");
                                stringBuffer.append(Macro.STRINT_CHAT_CHANNE[by]);
                                stringBuffer.append("】");
                                if (string.equals(ORPMe.ME.strNickName)) {
                                    stringBuffer.append("你对");
                                    stringBuffer.append(string5);
                                    stringBuffer.append("说：");
                                    stringBuffer.append(string6);
                                } else {
                                    stringBuffer.append(string4);
                                    stringBuffer.append("对你说：");
                                    stringBuffer.append(string6);
                                }
                                GameUI.getInstance().showChat();
                                break;
                            }
                            case 5: {
                                stringBuffer.append(string6);
                                DCanvas.getInstance().setSystemChat(stringBuffer.toString());
                                DCanvas.getInstance().addSystemChatNote(string6);
                                return 7681;
                            }
                            case 12: {
                                stringBuffer.append(string6);
                                DCanvas.getInstance().setSystemChat(stringBuffer.toString());
                                DCanvas.getInstance().addSystemChatNote(string6);
                                return 7681;
                            }
                            case 1: {
                                if (!Rms.blnChatWChannel) {
                                    return 7681;
                                }
                            }
                            case 2:
                            case 3:
                            case 4:
                            case 10: {
                                stringBuffer.append("【");
                                stringBuffer.append(Macro.STRINT_CHAT_CHANNE[by]);
                                stringBuffer.append("】");
                                stringBuffer.append(string4);
                                stringBuffer.append("：");
                                stringBuffer.append(string6);
                                GameUI.getInstance().showChat();
                                break;
                            }
                            default: {
                                stringBuffer.append("【");
                                stringBuffer.append(Macro.STRINT_CHAT_CHANNE[by]);
                                stringBuffer.append("】");
                                stringBuffer.append(string4);
                                stringBuffer.append("：");
                                stringBuffer.append(string6);
                                GameUI.getInstance().showChat();
                                DCanvas.getInstance().addSystemChatNote(stringBuffer.toString());
                            }
                        }
                        GameUI.getInstance().setChat(stringBuffer.toString(), n, by, string, 0);
                        break;
                    }
                    case 15362: {
                        String str = dataInputStream.readUTF();
                        String string = "";
                        if (str.indexOf("GM") != -1 || str.indexOf("gm") != -1) {
                            string = "#F26 " + str;
                        }
                        Param.getInstance().intGMSessionID = dataInputStream.readInt();
                        Param.getInstance().intGMQuestionID = dataInputStream.readInt();
                        String string7 = dataInputStream.readUTF();
                        GameUI.getInstance().showChat();
                        GameUI.getInstance().setChat(String.valueOf(string) + ":" + string7, Macro.INT_CHAT_COLOR[11], (byte) 11, (String) string, 0);
                        break;
                    }
                    case 15363: {
                        Param.getInstance().intGMSessionID = dataInputStream.readInt();
                        if (DCanvas.getInstance().UMenu != null) {
                            GameControl.getInstance().delUIbase(3);
                            GameControl.getInstance().delUIbase(9);
                        }
                        GameControl.getInstance().CreateState((byte) 9);
                        MenuUI.getInstance().setGmAppraise();
                        break;
                    }
                    case 15361: {
                        byte by = dataInputStream.readByte();
                        DebugFrame.getInstance().logIn("0移除 1添加：" + by);
                        if (by == 0) {
                            Param.getInstance().intGMQuizTimer = 0;
                            GameUI.getInstance().delRoleStateIcon((short) 4);
                            break;
                        }
                        Param.getInstance().intGMQuizTimer = (int) System.currentTimeMillis();
                        GameUI.getInstance().pushRoleStateIcon((short) 4);
                        break;
                    }
                    case 6658: {
                        NetParse.getInstance().updatePackageGoods(dataInputStream);
                        break;
                    }
                    case 6657: {
                        NetParse.getInstance().updatePackageEquip(dataInputStream);
                        break;
                    }
                    case 10776: {
                        short s10 = dataInputStream.readShort();
                        String string = dataInputStream.readUTF();
                        if (s10 == GameUI.getInstance().shtMoveMapID) {
                            Macro.strAreaMapInfo = string;
                        }
                        if (GameUI.getInstance().bytMapState == 1 && FullInfo.getInstance() != null && FullInfo.getInstance().bytState == -90) {
                            FullInfo.getInstance().setFullRectMenu(Macro.strAreaMapInfo);
                        }
                        break;
                    }
                    case 10777: {
                        short s11;
                        short s12 = dataInputStream.readShort();
                        Param.getInstance().htAreaMapNpcList = new Vector(1);
                        Param.getInstance().htAreaMapNpcIDList = new Vector(1);
                        if (s12 == GameUI.getInstance().shtMoveMapID && (s11 = dataInputStream.readShort()) > 0) {
                            short s13 = 0;
                            while (s13 < s11) {
                                int n = dataInputStream.readInt();
                                String string = dataInputStream.readUTF();
                                String string8 = dataInputStream.readUTF();
                                byte by = dataInputStream.readByte();
                                short s14 = dataInputStream.readShort();
                                short s15 = dataInputStream.readShort();
                                String string9 = String.valueOf(string8) + "【" + string + "】" + "(" + s14 + "," + s15 + ")";
                                Param.getInstance().htAreaMapNpcList.addElement(string9);
                                Param.getInstance().htAreaMapNpcIDList.addElement(new Integer(n));
                                s13 = (byte) (s13 + 1);
                            }
                        }
                        if (GameUI.getInstance().bytMapState == 1 && DCanvas.getInstance().UMenu != null && MenuUI.getInstance().getState() == -91) {
                            MenuUI.getInstance().initOptionList(Param.getInstance().htAreaMapNpcList, 0, 38);
                            if (Param.getInstance().htAreaMapNpcList.size() > 0) {
                                MenuUI.getInstance().setButton(1, 26, 2);
                            }
                        }
                        break;
                    }
                    case 10775: {
                        byte by = dataInputStream.readByte();
                        if (by == 0) {
                            String string = dataInputStream.readUTF();
                            DCanvas.getInstance().addInformation(string);
                        } else if (by == 1) {
                            String string = dataInputStream.readUTF();
                            byte by17 = dataInputStream.readByte();
                            short s16 = dataInputStream.readShort();
                            short s17 = dataInputStream.readShort();
                            short s18 = dataInputStream.readShort();
                            short s19 = dataInputStream.readShort();
                            Map.getInstance().shtSmallMapMaxWidth = s18;
                            Map.getInstance().shtSmallMapMaxHeight = s19;
                            Map.getInstance().imgAreaMapPngId = s16;
                            Map.getInstance().imgAreaMapAnuId = s17;
                            Map.getInstance().bytCurAreaMapClanType = by17;
                            byte by18 = dataInputStream.readByte();
                            Map.getInstance().shtsAreaMapPlaceXY = new short[by18][2];
                            Map.getInstance().shtsAreaMapPlaceBornXY = new short[by18][2];
                            Map.getInstance().shtsAreaMapflag = new short[by18];
                            Map.getInstance().strsAreaMapPlaceName = new String[by18];
                            Map.getInstance().strAreaMapName = string;
                            byte by19 = 0;
                            while (by19 < by18) {
                                short s20 = dataInputStream.readShort();
                                short s21 = dataInputStream.readShort();
                                short s22 = dataInputStream.readShort();
                                String string10 = dataInputStream.readUTF();
                                short s23 = dataInputStream.readShort();
                                short s24 = dataInputStream.readShort();
                                Map.getInstance().shtsAreaMapPlaceXY[by19][0] = s20;
                                Map.getInstance().shtsAreaMapPlaceXY[by19][1] = s21;
                                Map.getInstance().shtsAreaMapflag[by19] = s22;
                                Map.getInstance().strsAreaMapPlaceName[by19] = string10;
                                Map.getInstance().shtsAreaMapPlaceBornXY[by19][0] = s23;
                                Map.getInstance().shtsAreaMapPlaceBornXY[by19][1] = s24;
                                by19 = (byte) (by19 + 1);
                            }
                            Map.getInstance().blnAreaMapExist = true;
                        }
                        if (GameUI.getInstance().bytMapState == 1 && DialogUI.getInstance() != null && DialogUI.getInstance().bytDialogState == 4) {
                            GameControl.getInstance().delUIbase(7);
                            if (by == 1) {
                                GameUI.getInstance().initMapinfo();
                            }
                        }
                        if (by != 1) {
                            GameUI.getInstance().cleanSmallMap();
                        }
                        break;
                    }
                    case 10784: {
                        byte by = dataInputStream.readByte();
                        String string = dataInputStream.readUTF();
                        short s25 = dataInputStream.readShort();
                        short s26 = dataInputStream.readShort();
                        short s27 = dataInputStream.readShort();
                        short s28 = dataInputStream.readShort();
                        Map.getInstance().shtWorldMapMaxWidth = s27;
                        Map.getInstance().shtWorldMapMaxHeight = s28;
                        Map.getInstance().imgWorldMapPngId = s25;
                        Map.getInstance().imgWorldMapAnuId = s26;
                        byte by20 = dataInputStream.readByte();
                        Map.getInstance().shtsWorldMapPlaceXY = new short[by20][2];
                        Map.getInstance().shtsWorldMapflag = new short[by20];
                        Map.getInstance().strsWorldMapPlaceName = new String[by20];
                        Map.getInstance().strWorldMapName = string;
                        byte by21 = 0;
                        while (by21 < by20) {
                            byte by22 = (byte) (by21 + 1);
                            dataInputStream.readByte();
                            String string11 = dataInputStream.readUTF();
                            short s29 = dataInputStream.readShort();
                            short s30 = dataInputStream.readShort();
                            Map.getInstance().shtsWorldMapPlaceXY[by21][0] = s29;
                            Map.getInstance().shtsWorldMapPlaceXY[by21][1] = s30;
                            Map.getInstance().shtsWorldMapflag[by21] = by22;
                            Map.getInstance().strsWorldMapPlaceName[by21] = string11;
                            by21 = (byte) (by21 + 1);
                        }
                        Map.getInstance().blnWorldMapExist = true;
                        if (GameUI.getInstance().bytMapState == 2 && DialogUI.getInstance() != null && DialogUI.getInstance().bytDialogState == 4) {
                            GameControl.getInstance().delUIbase(7);
                            GameUI.getInstance().initMapinfo();
                        }
                        break;
                    }
                    case 7185: {
                        ORMonster oRMonster;
                        byte by = dataInputStream.readByte();
                        int n = dataInputStream.readInt();
                        byte by23 = dataInputStream.readByte();
                        if (by == 2 && Param.getInstance().htRolePlayer != null) {
                            ORPlayer oRPlayer = (ORPlayer) Param.getInstance().htRolePlayer.get(new Integer(n));
                            if (oRPlayer != null) {
                                if (by23 == 1) {
                                    GameUI.getInstance().delRoleEffect_Conceal(oRPlayer);
                                    break;
                                }
                                if (by23 == 2) {
                                    GameUI.getInstance().setRoleEffect_Conceal(oRPlayer);
                                    break;
                                }
                                if (by23 == 3) {
                                    oRPlayer.intSkillDownLayerEffectID = -1;
                                    oRPlayer.blnRelive = true;
                                    if (oRPlayer.intUserId == ORPMe.ME.intUserId) {
                                        GameUI.getInstance().strReliveName = null;
                                    }
                                } else if (by23 == 4) {
                                    if (oRPlayer.bytState != 5 && !oRPlayer.checkTask((byte) 5)) {
                                        oRPlayer.pushTask((byte) 5, oRPlayer.bytDirection);
                                        GameUI.getInstance().updateTeamHP(oRPlayer, 0, oRPlayer.intHPMax);
                                        GameUI.getInstance().updateTeamMP(oRPlayer, 0, oRPlayer.intMPMax);
                                        if (n == ORPMe.ME.intUserId) {
                                            GameUI.getInstance().pushFightInfo("你已死亡", (byte) 1);
                                        }
                                    }
                                } else if (by23 >= 5 && by23 <= 9) {
                                    oRPlayer.addSpecialtiesState((byte) (by23 - 4));
                                    if (oRPlayer.intUserId == ORPMe.ME.intUserId) {
                                        NetParse.getInstance().checkMeSpecialtiesState();
                                    }
                                } else if (by23 >= 10 && by23 <= 14) {
                                    oRPlayer.delSpecialtiesState((byte) (by23 - 9));
                                    if (oRPlayer.intUserId == ORPMe.ME.intUserId) {
                                        NetParse.getInstance().checkMeSpecialtiesState();
                                    }
                                }
                            }
                        } else if (by == 3 && Param.getInstance().htRoleMonster != null && (oRMonster = (ORMonster) Param.getInstance().htRoleMonster.get(new Integer(n))) != null) {
                            if (by23 == 2) {
                                oRMonster.pushTask((byte) 3, (byte) 4);
                                break;
                            }
                            if (by23 == 4) {
                                DebugFrame.getInstance().logIn("怪物死亡咯：_id" + n + "Name" + oRMonster.strNickName);
                                oRMonster.pushTask((byte) 5, oRMonster.bytDirection);
                                break;
                            }
                            if (by23 >= 5 && by23 <= 9) {
                                oRMonster.addSpecialtiesState((byte) (by23 - 4));
                                break;
                            }
                            if (by23 >= 10 && by23 <= 14) {
                                oRMonster.delSpecialtiesState((byte) (by23 - 9));
                            }
                        }
                        break;
                    }
                    case 1025: {
                        if (ORPMe.ME != null) {
                            ORPMe.ME.EXP = dataInputStream.readInt();
                            ORPMe.ME.NextEXP = dataInputStream.readInt();
                            ORPMe.ME.ShowEXP = dataInputStream.readInt();
                            ORPMe.ME.ShowNextEXP = dataInputStream.readInt();
                            ORPMe.ME.intHP = dataInputStream.readInt();
                            ORPMe.ME.intHPMax = dataInputStream.readInt();
                            if (ORPMe.ME.intHP > ORPMe.ME.intHPMax) {
                                ORPMe.ME.intHP = ORPMe.ME.intHPMax;
                            }
                            ORPMe.ME.intMP = dataInputStream.readInt();
                            ORPMe.ME.intMPMax = dataInputStream.readInt();
                            ORPMe.ME.Strength = dataInputStream.readShort();
                            ORPMe.ME.Agility = dataInputStream.readShort();
                            ORPMe.ME.Stamina = dataInputStream.readShort();
                            ORPMe.ME.Intellect = dataInputStream.readShort();
                            ORPMe.ME.Energy = dataInputStream.readShort();
                            ORPMe.ME.Luck = dataInputStream.readShort();
                            ORPMe.ME.AttackMax = dataInputStream.readInt();
                            ORPMe.ME.AttackMin = dataInputStream.readInt();
                            ORPMe.ME.MagicAttrack = dataInputStream.readInt();
                            ORPMe.ME.SKILLENEMY = dataInputStream.readInt();
                            ORPMe.ME.SKILLNUM = dataInputStream.readInt();
                            ORPMe.ME.PKWIN = dataInputStream.readInt();
                            ORPMe.ME.PKNUM = dataInputStream.readInt();
                            ORPMe.ME.ShadowDamage = dataInputStream.readInt();
                            ORPMe.ME.HolinessDamage = dataInputStream.readInt();
                            ORPMe.ME.FireDamage = dataInputStream.readInt();
                            ORPMe.ME.WaterDamage = dataInputStream.readInt();
                            ORPMe.ME.EarthDamage = dataInputStream.readInt();
                            ORPMe.ME.ArmLuckOdds = dataInputStream.readUTF();
                            ORPMe.ME.MagicLuckOdds = dataInputStream.readUTF();
                            ORPMe.ME.ArmTargetOdds = dataInputStream.readUTF();
                            ORPMe.ME.MagicTargetOdds = dataInputStream.readUTF();
                            ORPMe.ME.Recovery = dataInputStream.readInt();
                            ORPMe.ME.ShadowRecovery = dataInputStream.readInt();
                            ORPMe.ME.HolinessRecovery = dataInputStream.readInt();
                            ORPMe.ME.FireRecovery = dataInputStream.readInt();
                            ORPMe.ME.WaterRecovery = dataInputStream.readInt();
                            ORPMe.ME.EarthRecovery = dataInputStream.readInt();
                            ORPMe.ME.JookOdds = dataInputStream.readUTF();
                            ORPMe.ME.shtImmobilityTimeMax = dataInputStream.readShort();
                            ORPMe.ME.strAttrick = dataInputStream.readUTF();
                            ORPMe.ME.bytAtkDistance = dataInputStream.readByte();
                            ORPMe.ME.strConsortia = dataInputStream.readUTF();
                            ORPMe.ME.societyName = dataInputStream.readUTF();
                            ORPMe.ME.MasterName = dataInputStream.readUTF();
                            ORPMe.ME.appreticeName = dataInputStream.readUTF();
                            ORPMe.ME.skillPoints = dataInputStream.readShort();
                            if (DCanvas.getInstance().UMenu != null && MenuUI.getInstance().getState() == -7) {
                                MenuUI.getInstance().refreshRoleInfo();
                            }
                            if (MenuUI.getInstance().rView != null) {
                                MenuUI.getInstance().getClass();
                                RoleViewStyle.roleAbility = new int[]{ORPMe.ME.Strength, ORPMe.ME.Agility, ORPMe.ME.Stamina, ORPMe.ME.Intellect, ORPMe.ME.Stamina, ORPMe.ME.Energy, ORPMe.ME.Luck};
                            }
                        }
                        break;
                    }
                    case 7190: {
                        byte by = dataInputStream.readByte();
                        int n = dataInputStream.readInt();
                        byte by24 = dataInputStream.readByte();
                        int n12 = dataInputStream.readInt();
                        short s31 = dataInputStream.readShort();
                        short s32 = dataInputStream.readShort();
                        ORole oRole = null;
                        if (by == 2 && Param.getInstance().htRolePlayer != null) {
                            oRole = (ORPlayer) Param.getInstance().htRolePlayer.get(new Integer(n));
                        } else if (by == 3 && Param.getInstance().htRoleMonster != null) {
                            oRole = (ORMonster) Param.getInstance().htRoleMonster.get(new Integer(n));
                        } else if (by == 4 && Param.getInstance().htRolePet != null) {
                            oRole = (PetObject) Param.getInstance().htRolePet.get(new Integer(n));
                        }
                        if (oRole != null) {
                            ORole oRole2 = null;
                            if (by24 == 2 && Param.getInstance().htRolePlayer != null) {
                                oRole2 = (ORPlayer) Param.getInstance().htRolePlayer.get(new Integer(n12));
                                if (n12 == ORPMe.ME.intUserId && Param.getInstance().intSelectId == -1) {
                                    ORPMe.ME.setSelectRole(oRole);
                                }
                            } else if (by24 == 3 && Param.getInstance().htRoleMonster != null) {
                                oRole2 = (ORMonster) Param.getInstance().htRoleMonster.get(new Integer(n12));
                            }
                            if (oRole2 != null && by == 2 && (by24 == 3 || by24 == 2)) {
                                SkillObject skillObject = new SkillObject(s31, s32, (byte) 1);
                                skillObject.byteLayer = 1;
                                skillObject.bytDisplayPosYOffType = (byte) 2;
                                skillObject.byteActive = 1;
                                skillObject.byteSkillType = 1;
                                skillObject.blnSkillMultiAnimation = false;
                                if (by24 == 3) {
                                    if (Macro.BLN_LEVEL_FUNCTION_SKILL) {
                                        ResPoolLevelFunction.getInstance().bindSkill(oRole2, null, skillObject);
                                    } else {
                                        ((ORMonster) oRole2).setSkill(skillObject);
                                    }
                                } else if (by24 == 2) {
                                    if (Macro.BLN_LEVEL_FUNCTION_SKILL) {
                                        ResPoolLevelFunction.getInstance().bindSkill(oRole2, null, skillObject);
                                    } else {
                                        oRole2.setSkill(skillObject);
                                    }
                                }
                            }
                            if (n != ORPMe.ME.intUserId) {
                                if (oRole2 != null) {
                                    byte by25 = GameUI.getInstance().getDirection(oRole.bytTileX, oRole.bytTileY, oRole.bytPicSize, oRole2.bytTileX, oRole2.bytTileY, oRole2.bytPicSize);
                                    oRole.pushTask((byte) 2, by25);
                                    break;
                                }
                                oRole.pushTask((byte) 2, oRole.bytDirection);
                            }
                        }
                        break;
                    }
                    case 3600: {
                        NetParse.getInstance().skillEffect(dataInputStream);
                        break;
                    }
                    case 7187: {
                        byte by = dataInputStream.readByte();
                        int n = dataInputStream.readInt();
                        ORole oRole = null;
                        if (by == 2 && Param.getInstance().htRolePlayer != null) {
                            oRole = (ORole) Param.getInstance().htRolePlayer.get(new Integer(n));
                            if (oRole.strNickName.equals(ORPMe.ME.strNickName)) {
                                GameUI.getInstance().pushFightInfo("你躲过了一次攻击", (byte) 2);
                            } else {
                                GameUI.getInstance().pushFightInfo("你未能击中" + oRole.strNickName, (byte) 1);
                            }
                        } else if (by == 3 && Param.getInstance().htRoleMonster != null) {
                            oRole = (ORole) Param.getInstance().htRoleMonster.get(new Integer(n));
                            GameUI.getInstance().pushFightInfo("你未能击中" + oRole.strNickName, (byte) 1);
                        }
                        if (oRole != null) {
                            oRole.addDamageData(0, (byte) 3);
                        }
                        break;
                    }
                    case 7186: {
                        byte by = dataInputStream.readByte();
                        int n = dataInputStream.readInt();
                        int n13 = dataInputStream.readInt();
                        int n14 = dataInputStream.readInt();
                        byte by26 = dataInputStream.readByte();
                        byte by27 = dataInputStream.readByte();
                        ORole oRole = null;
                        if (by == 2 && Param.getInstance().htRolePlayer != null) {
                            GameUI.getInstance().updateTeamHP((ORPlayer) Param.getInstance().htRolePlayer.get(new Integer(n)), n13, -1);
                            oRole = (ORole) Param.getInstance().htRolePlayer.get(new Integer(n));
                        } else if (by == 3 && Param.getInstance().htRoleMonster != null) {
                            oRole = (ORole) Param.getInstance().htRoleMonster.get(new Integer(n));
                        }
                        if (oRole != null) {
                            oRole.intHP = n13;
                            if (oRole.intHP > oRole.intHPMax) {
                                oRole.intHP = oRole.intHPMax;
                            }
                            if (by26 == 1) {
                                if (n14 > 0) {
                                    oRole.addCureData(n14);
                                    if (n == ORPMe.ME.intUserId) {
                                        GameUI.getInstance().pushFightInfo("你恢复了" + Math.abs(n14) + "点生命", (byte) 3);
                                    } else {
                                        GameUI.getInstance().pushFightInfo("你恢复了" + oRole.strNickName + " " + Math.abs(n14) + "点生命", (byte) 4);
                                    }
                                } else if (by27 == 1) {
                                    oRole.addDamageData(-n14, (byte) 2);
                                    if (n == ORPMe.ME.intUserId) {
                                        GameUI.getInstance().pushFightInfo("你受到了" + Math.abs(n14) + "点伤害(暴击)", (byte) 2);
                                    } else {
                                        GameUI.getInstance().pushFightInfo("你对" + oRole.strNickName + "造成了" + Math.abs(n14) + "点伤害(暴击)", (byte) 1);
                                    }
                                } else {
                                    oRole.addDamageData(-n14, (byte) 1);
                                    if (n == ORPMe.ME.intUserId) {
                                        GameUI.getInstance().pushFightInfo("你受到了" + Math.abs(n14) + "点伤害", (byte) 2);
                                    } else {
                                        GameUI.getInstance().pushFightInfo("你对" + oRole.strNickName + "造成了" + Math.abs(n14) + "点伤害", (byte) 1);
                                    }
                                }
                            }
                        }
                        if (DCanvas.getInstance().UMenu != null && MenuUI.getInstance().getState() == -7) {
                            MenuUI.getInstance().refreshRoleInfo();
                        }
                        break;
                    }
                    case 7189: {
                        int n = 0;
                        byte by = dataInputStream.readByte();
                        int n15 = dataInputStream.readInt();
                        int n16 = dataInputStream.readInt();
                        byte by28 = dataInputStream.readByte();
                        ORole oRole = null;
                        if (by == 2 && Param.getInstance().htRolePlayer != null) {
                            GameUI.getInstance().updateTeamMP((ORPlayer) Param.getInstance().htRolePlayer.get(new Integer(n15)), n16, -1);
                            oRole = (ORole) Param.getInstance().htRolePlayer.get(new Integer(n15));
                        } else if (by == 3 && Param.getInstance().htRoleMonster != null) {
                            oRole = (ORole) Param.getInstance().htRoleMonster.get(new Integer(n15));
                        }
                        if (oRole != null) {
                            if (n16 > oRole.intMP && (n = n16 - oRole.intMP) > 0 && by28 == 1) {
                                oRole.addMpData(n);
                            }
                            oRole.intMP = n16;
                            if (oRole.intMP > oRole.intMPMax) {
                                oRole.intMP = oRole.intMPMax;
                            }
                        }
                        if (DCanvas.getInstance().UMenu != null && MenuUI.getInstance().getState() == -7) {
                            MenuUI.getInstance().refreshRoleInfo();
                        }
                        break;
                    }
                    case 7440: {
                        ORPlayer oRPlayer;
                        DebugFrame.getInstance().logIn("替换人物图片和衣服");
                        int n = dataInputStream.readInt();
                        byte by = dataInputStream.readByte();
                        DebugFrame.getInstance().logIn("人物ID:" + n + " 1衣服2武器:" + by);
                        byte by29 = dataInputStream.readByte();
                        if (by29 == 0) {
                            ORPlayer oRPlayer2;
                            if (Param.getInstance().htRolePlayer != null && (oRPlayer2 = (ORPlayer) Param.getInstance().htRolePlayer.get(new Integer(n))) != null) {
                                short s33;
                                short s34;
                                short s35;
                                if (by == 4) {
                                    s35 = dataInputStream.readShort();
                                    s34 = dataInputStream.readShort();
                                    oRPlayer2.addRoleRes(8, s35, s34);
                                    s33 = dataInputStream.readShort();
                                    oRPlayer2.bytWeaponPart = ORole.transWeaponType(s33);
                                    DebugFrame.getInstance().logIn("武器_weaponType:" + s33);
                                } else {
                                    s35 = dataInputStream.readByte();
                                    DebugFrame.getInstance().logIn("zhongzu:" + s35);
                                }
                                s35 = dataInputStream.readShort();
                                s34 = dataInputStream.readShort();
                                s33 = dataInputStream.readShort();
                                short s36 = dataInputStream.readShort();
                                short s37 = dataInputStream.readShort();
                                boolean bl = false;
                                if (by == 0) {
                                    oRPlayer2.addRoleRes(-1, s34, s33);
                                } else if (by == 1) {
                                    oRPlayer2.shtEquipRealLevel = s35;
                                    if (Macro.BLN_LEVEL_FUNCTION_EQUIP) {
                                        ResPoolLevelFunction.getInstance().bindEquip(oRPlayer2, s34, s33);
                                        ResPoolLevelFunction.getInstance().LevelFunction_update((byte) 1);
                                    } else {
                                        oRPlayer2.addRoleRes(2, s34, s33);
                                    }
                                    oRPlayer2.addRoleRes(7, s36, s37);
                                    bl = true;
                                } else if (by == 4) {
                                    oRPlayer2.shtWeaponRealLevel = s35;
                                    if (Macro.BLN_LEVEL_FUNCTION_WEAPON) {
                                        ResPoolLevelFunction.getInstance().bindWeapon(oRPlayer2, s34, s33);
                                        ResPoolLevelFunction.getInstance().LevelFunction_update((byte) 2);
                                    } else {
                                        oRPlayer2.addRoleRes(4, s34, s33);
                                    }
                                    oRPlayer2.addRoleRes(9, s36, s37);
                                    bl = true;
                                }
                                oRPlayer2.pushTask((byte) 0, oRPlayer2.bytDirection, (byte) 1);
                            }
                        } else if (Param.getInstance().htRolePlayer != null && (oRPlayer = (ORPlayer) Param.getInstance().htRolePlayer.get(new Integer(n))) != null) {
                            boolean bl = false;
                            if (by == 0) {
                                oRPlayer.addRoleRes(-1, (short) -1, (short) -1);
                            } else if (by == 1) {
                                short s38;
                                oRPlayer.shtEquipRealLevel = s38 = dataInputStream.readShort();
                                short s39 = dataInputStream.readShort();
                                short s40 = dataInputStream.readShort();
                                DebugFrame.getInstance().logIn("clothesId:" + s39);
                                if (Macro.BLN_LEVEL_FUNCTION_EQUIP) {
                                    ResPoolLevelFunction.getInstance().bindEquip(oRPlayer, s39, s40);
                                    ResPoolLevelFunction.getInstance().LevelFunction_update((byte) 1);
                                } else {
                                    oRPlayer.addRoleRes(2, s39, s40);
                                }
                                oRPlayer.addRoleRes(7, (short) -1, (short) -1);
                                bl = true;
                            } else if (by == 4) {
                                oRPlayer.shtWeaponRealLevel = 0;
                                if (Macro.BLN_LEVEL_FUNCTION_WEAPON) {
                                    ResPoolLevelFunction.getInstance().bindWeapon(oRPlayer, (short) -1, (short) -1);
                                    ResPoolLevelFunction.getInstance().LevelFunction_update((byte) 2);
                                } else {
                                    oRPlayer.addRoleRes(4, (short) -1, (short) -1);
                                }
                                oRPlayer.addRoleRes(8, (short) -1, (short) -1);
                                oRPlayer.addRoleRes(9, (short) -1, (short) -1);
                                oRPlayer.bytWeaponPart = (short) -1;
                                bl = true;
                            }
                            oRPlayer.pushTask((byte) 0, oRPlayer.bytDirection, (byte) 1);
                        }
                        if (GameUI.getInstance().checkMeMenuUi(10) || GameUI.getInstance().checkMeMenuUi(36) || GameUI.getInstance().checkMeMenuUi(37) || GameUI.getInstance().checkMeMenuUi(38) || GameUI.getInstance().checkMeMenuUi(-70) || GameUI.getInstance().checkMeMenuUi(39) || GameUI.getInstance().checkMeMenuUi(10) || GameUI.getInstance().checkMeMenuUi(33) || GameUI.getInstance().checkMeMenuUi(21) || GameUI.getInstance().checkMeMenuUi(22)) {
                            if (n == ORPMe.ME.intUserId && Param.getInstance().playerColne != null) {
                                Param.getInstance().playerColne = Param.getInstance().oSelectRole != null ? ((ORPlayer) Param.getInstance().oSelectRole).colnePlayerBaseData() : ORPMe.ME.colnePlayerBaseData();
                                Param.getInstance().playerColne.pushTask((byte) 0, (byte) RoleViewStyle.ROLE_DIRECTION, (byte) 1);
                            }
                        } else if (GameUI.getInstance().checkMeMenuUi(-7)) {
                            if (Param.getInstance().playerColne != null) {
                                Param.getInstance().playerColne = Param.getInstance().oSelectRole != null ? ((ORPlayer) Param.getInstance().oSelectRole).colnePlayerBaseData() : ORPMe.ME.colnePlayerBaseData();
                                Param.getInstance().playerColne.pushTask((byte) 0, (byte) RoleViewStyle.ROLE_DIRECTION, (byte) 1);
                            }
                        } else if (Param.getInstance().playerColne != null) {
                            Param.getInstance().playerColne = Param.getInstance().oSelectRole != null ? ((ORPlayer) Param.getInstance().oSelectRole).colnePlayerBaseData() : ORPMe.ME.colnePlayerBaseData();
                            Param.getInstance().playerColne.pushTask((byte) 0, (byte) RoleViewStyle.ROLE_DIRECTION, (byte) 1);
                        }
                        break;
                    }
                    case 3589: {
                        int n = dataInputStream.readInt();
                        GameUI.getInstance().DelSkillCD(n);
                        break;
                    }
                    case 1028: {
                        byte by = dataInputStream.readByte();
                        int n = dataInputStream.readInt();
                        int n17 = dataInputStream.readInt();
                        if (by == 1) {
                            GameUI.getInstance().addGetExpGold(n, 0);
                        } else if (by == 2) {
                            DCanvas.getInstance().addInformation("获得经验" + n, 16642234);
                        }
                        GameUI.getInstance().pushFightInfo("获得经验" + n, (byte) 0);
                        ORPMe.ME.EXP = n17;
                        ORPMe.ME.ShowEXP = dataInputStream.readInt();
                        if (DCanvas.getInstance().UMenu != null && MenuUI.getInstance().getState() == -7) {
                            MenuUI.getInstance().refreshRoleInfo();
                        }
                        break;
                    }
                    case 1027: {
                        byte by = dataInputStream.readByte();
                        int n = dataInputStream.readInt();
                        if (by == 1) {
                            GameUI.getInstance().addGetExpGold(n, 1);
                        } else if (by == 2) {
                            if (n > 0) {
                                DCanvas.getInstance().addInformation("获得金钱" + n, 16642234);
                            } else {
                                DCanvas.getInstance().addInformation("失去金钱" + -n, 16642234);
                            }
                        }
                        if ((ORPMe.Gold += n) < 0) {
                            ORPMe.Gold = 0;
                        }
                        break;
                    }
                    case 1026: {
                        ORPlayer oRPlayer;
                        int n = dataInputStream.readInt();
                        short s41 = dataInputStream.readShort();
                        int n18 = dataInputStream.readInt();
                        int n19 = dataInputStream.readInt();
                        short s42 = dataInputStream.readShort();
                        if (Param.getInstance().htRolePlayer == null || (oRPlayer = (ORPlayer) Param.getInstance().htRolePlayer.get(new Integer(n))) == null) {
                            break;
                        }
                        oRPlayer.pushTask((byte) 3, (byte) 3);
                        oRPlayer.shtLevel = s41;
                        oRPlayer.intHPMax = n18;
                        oRPlayer.intHP = n18;
                        oRPlayer.bytDrawNameType = (byte) -1;
                        if (oRPlayer.blnUseMp) {
                            oRPlayer.intMPMax = n19;
                        }
                        oRPlayer.intMP = n19;
                        if (oRPlayer.intUserId == ORPMe.ME.intUserId) {
                            ORPMe.ME.skillPoints = s42;
                            GameUI.getInstance().bytMeLevel = GameUI.getInstance().getBytes(oRPlayer.shtLevel);
                            DCanvas.getInstance().addInformation("你升到了 " + s41 + " 级", 16642234);
                            GameUI.getInstance().pushFightInfo("你升到了 " + s41 + " 级", (byte) 4);
                            if (DCanvas.getInstance().UMenu != null && MenuUI.getInstance().getState() == -7) {
                                MenuUI.getInstance().refreshRoleInfo();
                            }
                            break;
                        }
                        GameUI.getInstance().bytMeSelLevel = GameUI.getInstance().getBytes(oRPlayer.shtLevel);
                        break;
                    }
                    case 11522: {
                        if (Macro.BLN_ACCERATE_NPC_TASK) {
                            int n = dataInputStream.available();
                            byte[] byArray5 = new byte[n];
                            dataInputStream.read(byArray5);
                            DataInputStream dataInputStream2 = new DataInputStream(new ByteArrayInputStream(byArray5));
                            dataInputStream = new DataInputStream(new ByteArrayInputStream(byArray5));
                            int n20 = dataInputStream2.readInt();
                            int n21 = dataInputStream2.readInt();
                            byte by = dataInputStream2.readByte();
                            byte by30 = dataInputStream2.readByte();
                            if (by30 == 1 || by30 == 12) {
                                FakeServer.getInstance().npcMenu(dataInputStream);
                                break;
                            }
                            NetParse.getInstance().npcMenu(dataInputStream);
                            break;
                        }
                        NetParse.getInstance().npcMenu(dataInputStream);
                        break;
                    }
                    case 11544: {
                        int n = dataInputStream.readInt();
                        int n22 = dataInputStream.readByte();
                        byte[] byArray6 = new byte[n22];
                        dataInputStream.readFully(byArray6);
                        ONpc oNpc = GameUI.getInstance().getNpc(n);
                        if (oNpc != null) {
                            if (!oNpc.blnIsActivation) {
                                oNpc.NpcActivation();
                                Map.getInstance().bytNpcConvoyNum = (byte) (Map.getInstance().bytNpcConvoyNum + 1);
                                GameUI.getInstance().delTempVObject(oNpc);
                            }
                            int n23 = 0;
                            while (n23 < n22) {
                                if (byArray6[n23] != 0) {
                                    oNpc.pushTask((byte) 1, byArray6[n23]);
                                }
                                ++n23;
                            }
                        }
                        break;
                    }
                    case 3331: {
                        DebugFrame.getInstance().logIn("更新一个BUFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
                        byte by = dataInputStream.readByte();
                        int n = dataInputStream.readInt();
                        DebugFrame.getInstance().logIn("player _id=" + n);
                        ORole oRole = null;
                        if (by == 2 && Param.getInstance().htRolePlayer != null) {
                            oRole = (ORole) Param.getInstance().htRolePlayer.get(new Integer(n));
                        } else if (by == 3 && Param.getInstance().htRoleMonster != null) {
                            oRole = (ORole) Param.getInstance().htRoleMonster.get(new Integer(n));
                        }
                        if (oRole != null) {
                            int n24 = dataInputStream.readInt();
                            int n25 = dataInputStream.readInt();
                            int n26 = dataInputStream.readInt();
                            byte by31 = dataInputStream.readByte();
                            short s43 = dataInputStream.readShort();
                            dataInputStream.readByte();
                            int n27 = dataInputStream.readShort() * 1000;
                            short s44 = dataInputStream.readShort();
                            DebugFrame.getInstance().logIn("_buff icon=" + s44);
                            String string = dataInputStream.readUTF();
                            byte by32 = dataInputStream.readByte();
                            short s45 = dataInputStream.readShort();
                            short s46 = dataInputStream.readShort();
                            if (by32 == 1 && by == 2) {
                                QSprite qSprite = ((ORPlayer) oRole).addRoleRes(5, s45, s46);
                                if (qSprite != null) {
                                    ((ORPlayer) oRole).blnSelectedRide = true;
                                    ((ORPlayer) oRole).blnNeedConcealRidePet = false;
                                    IDefines.MENU_UI_PET_ATTRI_TITLE_VAL[0] = "--";
                                    IDefines.MENU_UI_PET_ATTRI_TITLE_VAL[1] = "--";
                                    IDefines.MENU_UI_PET_ATTRI_TITLE_VAL[2] = String.valueOf(ORPMe.ME.bytSpeed);
                                } else {
                                    ((ORPlayer) oRole).blnSelectedRide = false;
                                    ((ORPlayer) oRole).blnNeedConcealRidePet = false;
                                }
                                if (Param.getInstance().playerColne != null) {
                                    Param.getInstance().playerColne = ORPMe.ME.colnePlayerBaseData();
                                    Param.getInstance().playerColne.pushTask((byte) 0, Param.getInstance().playerColne.bytDirection, (byte) 1);
                                }
                                ((ORPlayer) oRole).pushTask(((ORPlayer) oRole).bytState, ((ORPlayer) oRole).bytDirection, (byte) 1);
                            }
                            if (n27 < 0) {
                                n27 = 0;
                            }
                            if (by31 == 1) {
                                DebugFrame.getInstance().logIn("更新buffer");
                                oRole.updateBuff(n24, n25, n26, n27, s43, s44, string);
                                break;
                            }
                            if (by31 == 2) {
                                DebugFrame.getInstance().logIn("更新debuffer");
                                oRole.updateDebuff(n24, n25, n26, n27, s43, s44, string);
                            }
                        }
                        break;
                    }
                    case 3329: {
                        DebugFrame.getInstance().logIn("收到一个新的BUFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
                        byte by = dataInputStream.readByte();
                        int n = dataInputStream.readInt();
                        ORole oRole = null;
                        if (by == 2 && Param.getInstance().htRolePlayer != null) {
                            oRole = (ORole) Param.getInstance().htRolePlayer.get(new Integer(n));
                        } else if (by == 3 && Param.getInstance().htRoleMonster != null) {
                            oRole = (ORole) Param.getInstance().htRoleMonster.get(new Integer(n));
                        }
                        if (oRole != null) {
                            int n28 = dataInputStream.readInt();
                            DebugFrame.getInstance().logIn("_buffID=" + n28);
                            int n29 = dataInputStream.readInt();
                            DebugFrame.getInstance().logIn("_playerId=" + n29);
                            byte by33 = dataInputStream.readByte();
                            DebugFrame.getInstance().logIn("_buffType=" + by33);
                            dataInputStream.readByte();
                            int n30 = dataInputStream.readShort() * 1000;
                            if (n30 < 0) {
                                n30 = 0;
                            }
                            short s47 = dataInputStream.readShort();
                            DebugFrame.getInstance().logIn("_buff icon=" + s47);
                            String string = dataInputStream.readUTF();
                            DebugFrame.getInstance().logIn("_name=" + string);
                            String string12 = dataInputStream.readUTF();
                            byte by34 = dataInputStream.readByte();
                            short s48 = dataInputStream.readShort();
                            short s49 = dataInputStream.readShort();
                            if (by34 == 1 && by == 2) {
                                QSprite qSprite = ((ORPlayer) oRole).addRoleRes(5, s48, s49);
                                if (qSprite != null) {
                                    ((ORPlayer) oRole).blnSelectedRide = true;
                                    ((ORPlayer) oRole).blnNeedConcealRidePet = false;
                                    IDefines.MENU_UI_PET_ATTRI_TITLE_VAL[0] = "--";
                                    IDefines.MENU_UI_PET_ATTRI_TITLE_VAL[1] = "--";
                                    IDefines.MENU_UI_PET_ATTRI_TITLE_VAL[2] = String.valueOf(ORPMe.ME.bytSpeed);
                                } else {
                                    ((ORPlayer) oRole).blnSelectedRide = false;
                                    ((ORPlayer) oRole).blnNeedConcealRidePet = false;
                                }
                                if ((GameUI.getInstance().checkMeMenuUi(10) || GameUI.getInstance().checkMeMenuUi(36) || GameUI.getInstance().checkMeMenuUi(37) || GameUI.getInstance().checkMeMenuUi(38) || GameUI.getInstance().checkMeMenuUi(-70) || GameUI.getInstance().checkMeMenuUi(39) || GameUI.getInstance().checkMeMenuUi(33) || GameUI.getInstance().checkMeMenuUi(21) || GameUI.getInstance().checkMeMenuUi(22)) && Param.getInstance().playerColne != null && Param.getInstance().playerColne.strNickName.equals(ORPMe.ME.strNickName)) {
                                    Param.getInstance().playerColne = ORPMe.ME.colnePlayerBaseData();
                                    Param.getInstance().playerColne.pushTask((byte) 0, (byte) RoleViewStyle.ROLE_DIRECTION, (byte) 1);
                                }
                                ((ORPlayer) oRole).pushTask(((ORPlayer) oRole).bytState, ((ORPlayer) oRole).bytDirection, (byte) 1);
                            }
                            if (Param.getInstance().intsBuffColor == null) {
                                Param.getInstance().intsBuffColor = DrawBase.getRGB(8, 8, -1721473948);
                            }
                            if (by33 == 1) {
                                DebugFrame.getInstance().logIn("增加一个BUFF");
                                oRole.addBuff(n28, n30, s47, string, string12, n29, 1);
                                GameUI.getInstance().setRoleEffect(oRole, s47, (short) n30);
                                break;
                            }
                            if (by33 == 2) {
                                DebugFrame.getInstance().logIn("增加一个DEBUFF");
                                oRole.addDeBuff(n28, n30, s47, string, string12, n29, 1);
                            }
                        }
                        break;
                    }
                    case 3330: {
                        DebugFrame.getInstance().logIn("删除一个BUFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
                        byte by = dataInputStream.readByte();
                        DebugFrame.getInstance().logIn("_type=" + by);
                        int n = dataInputStream.readInt();
                        DebugFrame.getInstance().logIn("player _id=" + n);
                        ORole oRole = null;
                        if (by == 2 && Param.getInstance().htRolePlayer != null) {
                            oRole = (ORole) Param.getInstance().htRolePlayer.get(new Integer(n));
                        } else if (by == 3 && Param.getInstance().htRoleMonster != null) {
                            oRole = (ORole) Param.getInstance().htRoleMonster.get(new Integer(n));
                        }
                        if (oRole != null) {
                            int n31 = dataInputStream.readInt();
                            DebugFrame.getInstance().logIn("_playerId=" + n31);
                            int n32 = dataInputStream.readInt();
                            DebugFrame.getInstance().logIn("_buffID=" + n32);
                            byte by35 = dataInputStream.readByte();
                            DebugFrame.getInstance().logIn("_buffType=" + by35);
                            short s50 = dataInputStream.readShort();
                            DebugFrame.getInstance().logIn("_buff icon=" + s50);
                            byte by36 = dataInputStream.readByte();
                            short s51 = dataInputStream.readShort();
                            short s52 = dataInputStream.readShort();
                            if (by36 == 1 && by == 2) {
                                QSprite qSprite = ((ORPlayer) oRole).addRoleRes(5, s51, s52);
                                ((ORPlayer) oRole).pushTask(((ORPlayer) oRole).bytState, ((ORPlayer) oRole).bytDirection, (byte) 1);
                                if (qSprite == null) {
                                    ((ORPlayer) oRole).blnSelectedRide = false;
                                    ((ORPlayer) oRole).blnNeedConcealRidePet = false;
                                    IDefines.MENU_UI_PET_ATTRI_TITLE_VAL[0] = "--";
                                    IDefines.MENU_UI_PET_ATTRI_TITLE_VAL[1] = "--";
                                    IDefines.MENU_UI_PET_ATTRI_TITLE_VAL[2] = String.valueOf(ORPMe.ME.bytSpeed);
                                }
                                if (GameUI.getInstance().checkMeMenuUi(-6)) {
                                    if (Param.getInstance().playerColne != null) {
                                        Param.getInstance().playerColne = Param.getInstance().oSelectRole != null ? ((ORPlayer) Param.getInstance().oSelectRole).colnePlayerBaseData() : ORPMe.ME.colnePlayerBaseData();
                                        Param.getInstance().playerColne.pushTask((byte) 0, (byte) RoleViewStyle.ROLE_DIRECTION, (byte) 1);
                                    }
                                } else if (Param.getInstance().oSelectRole == null) {
                                    Param.getInstance().playerColne = ORPMe.ME.colnePlayerBaseData();
                                    Param.getInstance().playerColne.pushTask((byte) 0, (byte) RoleViewStyle.ROLE_DIRECTION, (byte) 1);
                                }
                                ((ORPlayer) oRole).pushTask(((ORPlayer) oRole).bytState, ((ORPlayer) oRole).bytDirection, (byte) 1);
                            }
                            if (by35 == 1) {
                                DebugFrame.getInstance().logIn("删除一个BUFF:" + n32 + " " + s50);
                                oRole.delBuff(n32, n31);
                                GameUI.getInstance().delRoleEffect(oRole, s50);
                                break;
                            }
                            if (by35 == 2) {
                                DebugFrame.getInstance().logIn("删除一个DEBUFF:" + n32);
                                oRole.delDeBuff(n32, n31);
                            }
                        }
                        break;
                    }
                    case 23809: {
                        byte by = dataInputStream.readByte();
                        String string = dataInputStream.readUTF();
                        byte by37 = dataInputStream.readByte();
                        if (by == 0) {
                            if (by37 == 1) {
                                GameControl.getInstance().createFullInfoMenu("提示", string, (byte) 102, (byte) -1, new byte[]{1, 100, 2});
                            } else {
                                DCanvas.getInstance().addInformation(string);
                            }
                        } else if (by == 1) {
                            if (by37 == 1) {
                                GameControl.getInstance().createFullInfoMenu("提示", string, (byte) 102, (byte) -1, new byte[]{1, 100, 2});
                            } else {
                                GameControl.getInstance().CreateState((byte) 7);
                                DialogUI.getInstance().setDialog((byte) 102, string, (byte) 2);
                            }
                        } else if (by == 2) {
                            byte by38 = dataInputStream.readByte();
                            if (by37 == 1) {
                                byte[] byArray7 = new byte[3];
                                byArray7[0] = 1;
                                byArray7[2] = 2;
                                GameControl.getInstance().createFullInfoMenu("提示", string, (byte) 102, by38, byArray7);
                            } else {
                                GameControl.getInstance().CreateState((byte) 7);
                                DialogUI.getInstance().setDialog((byte) 102, by38, string, (byte) 2);
                            }
                        } else if (by == 3) {
                            byte by39 = dataInputStream.readByte();
                            Param.Fee_Serial_Id = dataInputStream.readInt();
                            short s53 = dataInputStream.readShort();
                            if (s53 > 0) {
                                Param.lngCountDownStartTime = System.currentTimeMillis();
                                Param.lngCountDownLeftTime = Param.lngCountDownMaxTime = (long) (s53 * 1000);
                                Param.strCountDownTxt = string;
                            }
                            if (by37 == 1) {
                                byte[] byArray8 = new byte[3];
                                byArray8[0] = 1;
                                byArray8[2] = 2;
                                GameControl.getInstance().createFullInfoMenu("提示", string, (byte) 102, by39, byArray8);
                            } else {
                                GameControl.getInstance().CreateState((byte) 7);
                                DialogUI.getInstance().setDialog((byte) 102, by39, string, (byte) 2);
                            }
                        } else if (by == 4) {
                            byte by40 = dataInputStream.readByte();
                            Param.Msg_Hok_Command_Id = dataInputStream.readShort();
                            if (by37 == 1) {
                                byte[] byArray9 = new byte[3];
                                byArray9[0] = 1;
                                byArray9[2] = 2;
                                GameControl.getInstance().createFullInfoMenu("提示", string, (byte) 102, by40, byArray9);
                            } else {
                                GameControl.getInstance().CreateState((byte) 7);
                                DialogUI.getInstance().setDialog((byte) 102, by40, string, (byte) 2);
                            }
                        }
                        if (DCanvas.getInstance().UMenu != null && !Param.getInstance().blnNpcOptionWork) {
                            Param.getInstance().blnNpcOptionWork = true;
                        }
                        break;
                    }
                    case 7426: {
                        DebugFrame.getInstance().logIn("接收包裹里的装备数据---");
                        byte by = dataInputStream.readByte();
                        String string = dataInputStream.readUTF();
                        DebugFrame.getInstance().logIn("读取类型 1背包装备,2系统装备,3背包道具,4系统道具:" + by);
                        byte by41 = dataInputStream.readByte();
                        DebugFrame.getInstance().logIn("包裹格子的数量：" + by41);
                        Param.getInstance().packageBoxMaxNum = by41;
                        Hashtable hashtable = NetParse.getInstance().readPackageData(dataInputStream, by, true);
                        if (GameUI.getInstance().checkMeMenuUi(10)) {
                            Param.getInstance().packageBoxMaxNum = by41;
                            if (hashtable != null) {
                                Enumeration enumeration = hashtable.elements();
                                while (enumeration.hasMoreElements()) {
                                    PackageObject packageObject = (PackageObject) enumeration.nextElement();
                                    if (Param.getInstance().hShopPackage != null) {
                                        int n = packageObject.intPOindex;
                                        Enumeration enumeration2 = Param.getInstance().hShopPackage.elements();
                                        while (enumeration2.hasMoreElements()) {
                                            PackageObject packageObject2 = (PackageObject) enumeration2.nextElement();
                                            if (n != packageObject2.bytBagPlace) {
                                                continue;
                                            }
                                            packageObject.shtPOnum = (short) (packageObject.shtPOnum - packageObject2.shtPOnum);
                                        }
                                    }
                                    GameUI.getInstance().addObjectImg(packageObject.shtIcon, "prop");
                                }
                            }
                            if (GameUI.getInstance().checkMeMenuUi(10) || GameUI.getInstance().checkMeMenuUi(33)) {
                                NetParse.getInstance().clearTwoRect();
                                Param.getInstance().hPackage = hashtable;
                                MenuUI.getInstance().setEquip();
                                break;
                            }
                            Param.getInstance().hImgObject = null;
                        }
                        break;
                    }
                    case 23813: {
                        byte by = dataInputStream.readByte();
                        DebugFrame.getInstance().logIn("_readType :" + by);
                        byte by42 = dataInputStream.readByte();
                        DebugFrame.getInstance().logIn("包裹格子的数量：" + by42);
                        Hashtable hashtable = NetParse.getInstance().readPackageData(dataInputStream, (byte) (by == 0 ? 6 : 7), true);
                        Param.getInstance().bytDealGoods = by;
                        Param.getInstance().packageBoxMaxNum = by42;
                        if (hashtable != null) {
                            Enumeration enumeration = hashtable.elements();
                            while (enumeration.hasMoreElements()) {
                                GameUI.getInstance().addObjectImg(((PackageObject) enumeration.nextElement()).shtIcon, "prop");
                            }
                        }
                        Param.getInstance().hPackage = hashtable;
                        if (DialogUI.du == null) {
                            GameUI.getInstance().setDialog((byte) 9);
                        }
                        break;
                    }
                    case 3847: {
                        byte by = dataInputStream.readByte();
                        Vector vector = null;
                        if (by > 0) {
                            vector = new Vector();
                            byte by43 = 0;
                            while (by43 < by) {
                                String string;
                                TaskObject taskObject = new TaskObject();
                                taskObject.intId = dataInputStream.readInt();
                                taskObject.shtLevel = dataInputStream.readShort();
                                taskObject.strName = string = dataInputStream.readUTF();
                                byte by44 = dataInputStream.readByte();
                                taskObject.bnlOverType = by44 == 1;
                                taskObject.bytIsProp = dataInputStream.readByte();
                                vector.addElement(taskObject);
                                by43 = (byte) (by43 + 1);
                            }
                        }
                        Param.getInstance().vTaskList = vector;
                        break;
                    }
                    case 3856: {
                        int n;
                        byte by = dataInputStream.readByte();
                        int n33 = dataInputStream.readInt();
                        TaskObject taskObject = new TaskObject();
                        if (Param.getInstance().vTaskList != null) {
                            n = 0;
                            while (n < Param.getInstance().vTaskList.size()) {
                                TaskObject taskObject2 = (TaskObject) Param.getInstance().vTaskList.elementAt(n);
                                if (taskObject2.intId == n33) {
                                    taskObject.strName = taskObject2.strName;
                                    taskObject.shtLevel = taskObject2.shtLevel;
                                    break;
                                }
                                n = (byte) (n + 1);
                            }
                        }
                        taskObject.intId = n33;
                        if (taskObject != null) {
                            int n34;
                            PackageObject packageObject;
                            byte by45;
                            byte by46;
                            if (by == 1) {
                                taskObject.strInfo = dataInputStream.readUTF();
                                taskObject.intMoney = dataInputStream.readInt();
                                taskObject.intEXP = dataInputStream.readInt();
                                n = dataInputStream.readByte();
                                if (n > 0) {
                                    taskObject.intConditionId = new int[n];
                                    taskObject.strCondition = new String[n];
                                    taskObject.bytConditionType = new byte[n];
                                    taskObject.bytIsCarry = new byte[n];
                                    int n35 = 0;
                                    while (n35 < n) {
                                        taskObject.intConditionId[n35] = dataInputStream.readInt();
                                        taskObject.bytConditionType[n35] = dataInputStream.readByte();
                                        taskObject.strCondition[n35] = dataInputStream.readUTF();
                                        taskObject.bytIsCarry[n35] = dataInputStream.readByte();
                                        n35 = (byte) (n35 + 1);
                                    }
                                }
                                taskObject.bytType = 0;
                                Param.getInstance().vCommonList = new Vector(1, 1);
                                Param.getInstance().vCommonList.addElement(taskObject);
                                break;
                            }
                            TaskObject taskObject3 = null;
                            if (Param.getInstance().vCommonList != null) {
                                taskObject3 = (TaskObject) Param.getInstance().vCommonList.elementAt(0);
                            }
                            if ((by46 = dataInputStream.readByte()) > 0) {
                                taskObject3.vSelectObject = new Hashtable(by46);
                                by45 = 0;
                                while (by45 < by46) {
                                    PackageObject packageObject3 = new PackageObject();
                                    packageObject3.intId = dataInputStream.readInt();
                                    packageObject3.strName = dataInputStream.readUTF();
                                    packageObject3.bytQuality = dataInputStream.readByte();
                                    packageObject3.intColor = Macro.INT_PROP_COLOR[packageObject3.bytQuality];
                                    packageObject3.shtIcon = dataInputStream.readShort();
                                    packageObject3.strInfo = dataInputStream.readUTF();
                                    packageObject3.shtPOnum = dataInputStream.readByte();
                                    taskObject3.vSelectObject.put(new Integer(by45), packageObject3);
                                    by45 = (byte) (by45 + 1);
                                }
                            }
                            if ((by45 = dataInputStream.readByte()) > 0) {
                                taskObject3.vNSelectObject = new Hashtable(by45);
                                byte by47 = 0;
                                while (by47 < by45) {
                                    packageObject = new PackageObject();
                                    packageObject.strName = dataInputStream.readUTF();
                                    packageObject.bytQuality = dataInputStream.readByte();
                                    packageObject.intColor = Macro.INT_PROP_COLOR[packageObject.bytQuality];
                                    packageObject.shtIcon = dataInputStream.readShort();
                                    packageObject.strInfo = dataInputStream.readUTF();
                                    packageObject.shtPOnum = dataInputStream.readByte();
                                    taskObject3.vNSelectObject.put(new Integer(by47), packageObject);
                                    by47 = (byte) (by47 + 1);
                                }
                            }
                            if ((n34 = dataInputStream.readInt()) > 0) {
                                taskObject3.vOtherObject = new Hashtable(1);
                                packageObject = new PackageObject();
                                packageObject.strName = dataInputStream.readUTF();
                                packageObject.shtIcon = dataInputStream.readShort();
                                packageObject.strInfo = dataInputStream.readUTF();
                                taskObject3.vOtherObject.put(new Integer(1), packageObject);
                            }
                            Param.getInstance().vCommonList = new Vector(1, 1);
                            Param.getInstance().vCommonList.addElement(taskObject3);
                            MenuUI.getInstance().addTaskImage((short) 0, true);
                            MenuUI.getInstance().setTaskPrizeInfo();
                        }
                        break;
                    }
                    case 3857: {
                        byte by = dataInputStream.readByte();
                        int n = dataInputStream.readInt();
                        String string = dataInputStream.readUTF();
                        if (by == 1) {
                            DCanvas.getInstance().addInformation("接受任务'" + string + "'");
                            short s54 = dataInputStream.readShort();
                            boolean bl = dataInputStream.readByte() == 1;
                            byte by48 = dataInputStream.readByte();
                            TaskObject taskObject = new TaskObject();
                            taskObject.intId = n;
                            taskObject.strName = string;
                            taskObject.shtLevel = s54;
                            taskObject.bnlOverType = bl;
                            taskObject.bytIsProp = by48;
                            if (Param.getInstance().vTaskList == null) {
                                Param.getInstance().vTaskList = new Vector(1, 1);
                            }
                            Param.getInstance().vTaskList.addElement(taskObject);
                            if (GameUI.getInstance().checkMeMenuUi(63)) {
                                NetParse.getInstance().clearTwoRect();
                                MenuUI.getInstance().setTaskType();
                            }
                            break;
                        }
                        if (by == 3) {
                            DCanvas.getInstance().addInformation("放弃任务'" + string + "'");
                        } else {
                            DCanvas.getInstance().addInformation("完成任务'" + string + "'");
                        }
                        if (Param.getInstance().vTaskList == null) {
                            break;
                        }
                        int n36 = 0;
                        while (n36 < Param.getInstance().vTaskList.size()) {
                            if (((TaskObject) Param.getInstance().vTaskList.elementAt((int) n36)).intId == n) {
                                Param.getInstance().vTaskList.removeElementAt(n36);
                                if (Param.getInstance().vTaskList.isEmpty()) {
                                    Param.getInstance().vTaskList = null;
                                }
                                if (GameUI.getInstance().checkMeMenuUi(63)) {
                                    NetParse.getInstance().clearTwoRect();
                                    MenuUI.getInstance().setTaskType();
                                    break block5;
                                }
                                break block623;
                            }
                            ++n36;
                        }
                        break;
                    }
                    case 3848: {
                        int n;
                        int n37 = dataInputStream.readInt();
                        int n38 = dataInputStream.readInt();
                        byte by = dataInputStream.readByte();
                        String string = dataInputStream.readUTF();
                        DCanvas.getInstance().addInformation(string);
                        if (GameUI.getInstance().checkMeMenuUi(-127) && Param.getInstance().vCommonList != null) {
                            n = 0;
                            block174:
                            while (n < Param.getInstance().vCommonList.size()) {
                                TaskObject taskObject = (TaskObject) Param.getInstance().vCommonList.elementAt(n);
                                if (taskObject.intId == n37) {
                                    int n39 = 0;
                                    while (n39 < taskObject.strCondition.length) {
                                        if (taskObject.intConditionId[n39] == n38) {
                                            taskObject.strCondition[n39] = string;
                                            taskObject.bytConditionType[n39] = by;
                                            MenuUI.getInstance().setTaskInfo((short) 0);
                                            break block174;
                                        }
                                        ++n39;
                                    }
                                }
                                ++n;
                            }
                        }
                        if ((n = dataInputStream.readByte()) == 1 && Param.getInstance().vTaskList != null) {
                            int n40 = 0;
                            while (n40 < Param.getInstance().vTaskList.size()) {
                                if (((TaskObject) Param.getInstance().vTaskList.elementAt((int) n40)).intId == n37) {
                                    DCanvas.getInstance().addInformation("'" + ((TaskObject) Param.getInstance().vTaskList.elementAt((int) n40)).strName + "'任务完成");
                                    ((TaskObject) Param.getInstance().vTaskList.elementAt((int) n40)).bnlOverType = true;
                                    break block5;
                                }
                                n40 = (byte) (n40 + 1);
                            }
                        }
                        break;
                    }
                    case 3843: {
                        byte by = dataInputStream.readByte();
                        Vector vector = null;
                        if (by > 0) {
                            byte by49 = 0;
                            while (by49 < by) {
                                TaskObject taskObject = new TaskObject();
                                taskObject.intId = dataInputStream.readInt();
                                taskObject.strName = dataInputStream.readUTF();
                                taskObject.strInfo = dataInputStream.readUTF();
                                vector.addElement(taskObject);
                                by49 = (byte) (by49 + 1);
                            }
                        }
                        if (GameUI.getInstance().checkMeMenuUi(63)) {
                            MenuUI.getInstance().vTempVessel = vector;
                            NetParse.getInstance().clearTwoRect();
                            MenuUI.getInstance().setTaskType();
                        }
                        if (GameUI.getInstance().checkMeMenuUi(64)) {
                            MenuUI.getInstance().vTempVessel = vector;
                            NetParse.getInstance().clearTwoRect();
                            MenuUI.getInstance().setLifeSkillType();
                        }
                        break;
                    }
                    case 7425: {
                        byte by = dataInputStream.readByte();
                        String string = dataInputStream.readUTF();
                        Param.getInstance().bytNpcPropType = by;
                        byte by50 = dataInputStream.readByte();
                        Hashtable hashtable = NetParse.getInstance().readPackageData(dataInputStream, by, false);
                        if (hashtable != null) {
                            NetParse.getInstance().clearTwoRect();
                            Param.getInstance().hPackageEquip = hashtable;
                            GameUI.getInstance().checkEquipStamina();
                        }
                        break;
                    }
                    case 7442: {
                        Hashtable hashtable;
                        DebugFrame.getInstance().logIn("获取其他人装备数据---");
                        byte by = dataInputStream.readByte();
                        String string = dataInputStream.readUTF();
                        Param.getInstance().bytNpcPropType = by;
                        byte by51 = dataInputStream.readByte();
                        DebugFrame.getInstance().logIn("包裹格子的数量：" + by51);
                        boolean bl = GameUI.getInstance().checkMeMenuUi(-6);
                        if (bl && (hashtable = NetParse.getInstance().readPackageData(dataInputStream, by, true)) != null) {
                            MenuUI.getInstance().setEquipImage(hashtable);
                            Param.getInstance().hOtherPackageEquip = hashtable;
                        }
                        break;
                    }
                    case 7427:
                    case 7429:
                    case 7430:
                    case 7444: {
                        byte by = dataInputStream.readByte();
                        String string = dataInputStream.readUTF();
                        Param.getInstance().bytNpcPropType = by;
                        byte by52 = dataInputStream.readByte();
                        boolean bl = false;
                        if (s == 7427) {
                            bl = GameUI.getInstance().checkMeMenuUi(36);
                            if (MenuUI.getInstance().getState() == -81 && MenuUI.TabState == 1) {
                                bl = true;
                            }
                        } else if (s == 7430) {
                            bl = GameUI.getInstance().checkMeMenuUi(37);
                        } else if (s == 7429) {
                            bl = GameUI.getInstance().checkMeMenuUi(38);
                        } else if (s == 7444) {
                            bl = GameUI.getInstance().checkMeMenuUi(39);
                            if (MenuUI.getInstance().getState() == -81 && MenuUI.TabState == 4) {
                                bl = true;
                            }
                        }
                        if (bl) {
                            NetParse.getInstance().clearTwoRect();
                            Hashtable hashtable = NetParse.getInstance().readPackageData(dataInputStream, by, true);
                            Param.getInstance().packageBoxMaxNum = by52;
                            if (MenuUI.getInstance().getState() != -81) {
                                Param.getInstance().personalPackage.reset(by52);
                            }
                            if (hashtable != null) {
                                Enumeration enumeration = hashtable.elements();
                                while (enumeration.hasMoreElements()) {
                                    PackageObject packageObject = (PackageObject) enumeration.nextElement();
                                    if (Param.getInstance().hShopPackage != null) {
                                        int n = packageObject.intPOindex;
                                        Enumeration enumeration3 = Param.getInstance().hShopPackage.elements();
                                        while (enumeration3.hasMoreElements()) {
                                            PackageObject packageObject4 = (PackageObject) enumeration3.nextElement();
                                            if (n != packageObject4.shtPOnum) {
                                                continue;
                                            }
                                            packageObject.shtPOnum = (short) (packageObject.shtPOnum - packageObject4.shtPOnum);
                                        }
                                    }
                                    GameUI.getInstance().addObjectImg(packageObject.shtIcon, "prop");
                                }
                            }
                            if (s == 7427) {
                                bl = GameUI.getInstance().checkMeMenuUi(36);
                                if (MenuUI.getInstance().getState() == -81 && MenuUI.TabState == 1) {
                                    bl = true;
                                }
                            } else if (s == 7430) {
                                bl = GameUI.getInstance().checkMeMenuUi(37);
                            } else if (s == 7429) {
                                bl = GameUI.getInstance().checkMeMenuUi(38);
                            } else if (s == 7444) {
                                bl = GameUI.getInstance().checkMeMenuUi(39);
                                if (MenuUI.getInstance().getState() == -81 && MenuUI.TabState == 4) {
                                    bl = true;
                                }
                            }
                            if (bl) {
                                Param.getInstance().hPackage = hashtable;
                                MenuUI.getInstance().setInfoContent(Param.getInstance().hPackage, MenuUI.getInstance().getPropRectMove());
                                break;
                            }
                            Param.getInstance().hImgObject = null;
                        }
                        break;
                    }
                    case 7437: {
                        int n = dataInputStream.readInt();
                        GameUI.getInstance().DelGoodsCD(n);
                        break;
                    }
                    case 3075: {
                        byte by = dataInputStream.readByte();
                        Vector vector = new Vector();
                        byte by53 = 0;
                        while (by53 < by) {
                            TeamObject teamObject = new TeamObject();
                            teamObject.bytTeamId = dataInputStream.readByte();
                            teamObject.intId = dataInputStream.readInt();
                            teamObject.strName = dataInputStream.readUTF();
                            byte by54 = dataInputStream.readByte();
                            teamObject.setOcc(by54);
                            teamObject.blnIsMale = dataInputStream.readByte() == 1;
                            teamObject.shtLevel = dataInputStream.readShort();
                            teamObject.bytTroopRank = dataInputStream.readByte();
                            teamObject.blnIsOnLine = dataInputStream.readByte() == 1;
                            vector.addElement(teamObject);
                            if (teamObject.strName.equals(ORPMe.ME.strNickName)) {
                                ORPMe.ME.bytMeTeamID = teamObject.bytTeamId;
                                ORPMe.ME.bytTeamRank = teamObject.bytTroopRank;
                            }
                            GameUI.getInstance().setTeamNameColor(teamObject.strName, true);
                            teamObject.bytHead = ORPMe.ME.bytCountry == 1 ? (teamObject.blnIsMale ? (byte) 0 : 1) : (teamObject.blnIsMale ? (byte) 2 : (byte) 3);
                            by53 = (byte) (by53 + 1);
                        }
                        Param.getInstance().vTeam = vector;
                        NetParse.getInstance().TeamPlayerChange();
                        GameUI.getInstance().addTeamer();
                        DCanvas.getInstance().addInformation("加入队伍");
                        NetParse.getInstance().optionTroop();
                        break;
                    }
                    case 3078: {
                        byte by = dataInputStream.readByte();
                        int n = dataInputStream.readInt();
                        short s55 = dataInputStream.readShort();
                        if (Param.getInstance().vTeam != null) {
                            TeamObject teamObject = null;
                            int n41 = 0;
                            while (n41 < Param.getInstance().vTeam.size()) {
                                if (((TeamObject) Param.getInstance().vTeam.elementAt((int) n41)).intId == n) {
                                    teamObject = (TeamObject) Param.getInstance().vTeam.elementAt(n41);
                                    break;
                                }
                                n41 = (byte) (n41 + 1);
                            }
                            NetParse.getInstance().RankTeam();
                            switch (by) {
                                case 1: {
                                    if (teamObject != null) {
                                        teamObject.bytTeamId = (byte) s55;
                                        if (teamObject.strName.equals(ORPMe.ME.strNickName)) {
                                            ORPMe.ME.bytMeTeamID = teamObject.bytTeamId;
                                            DCanvas.getInstance().addInformation("你被调到第" + teamObject.bytTeamId + "小队", 16642234);
                                        }
                                        NetParse.getInstance().TeamPlayerChange();
                                    }
                                    GameUI.getInstance().addTeamer();
                                    break;
                                }
                                case 2: {
                                    if (teamObject.strName.equals(ORPMe.ME.strNickName)) {
                                        String string = "";
                                        if (teamObject.bytTroopRank < s55) {
                                            if (s55 == 2) {
                                                string = "你成为了队伍助手";
                                            } else if (s55 == 3) {
                                                string = "你成为了队伍领袖";
                                            }
                                        } else {
                                            string = s55 == 1 && teamObject.bytTroopRank == 2 ? "你不再是队伍助手" : "你不再是队长";
                                        }
                                        ORPMe.ME.bytTeamRank = (byte) s55;
                                        DCanvas.getInstance().addInformation(string);
                                    } else if (s55 == 1 && teamObject.bytTroopRank == 2) {
                                        DCanvas.getInstance().addInformation(String.valueOf(teamObject.strName) + "不再是队伍助手", 0xFFFFFF);
                                    } else if (s55 == 2) {
                                        DCanvas.getInstance().addInformation(String.valueOf(teamObject.strName) + "成为队伍助手", 0xFFFFFF);
                                    } else if (s55 == 3) {
                                        DCanvas.getInstance().addInformation(String.valueOf(teamObject.strName) + "成为队伍领袖", 0xFFFFFF);
                                    }
                                    teamObject.bytTroopRank = (byte) s55;
                                    break;
                                }
                                case 3: {
                                    teamObject.blnIsOnLine = s55 == 1;
                                    DCanvas.getInstance().addInformation(String.valueOf(teamObject.strName) + (teamObject.blnIsOnLine ? "上线了" : "离线了"));
                                    break;
                                }
                                case 4: {
                                    teamObject.shtLevel = s55;
                                    break;
                                }
                                case 5: {
                                    teamObject.setOcc((byte) s55);
                                }
                            }
                            NetParse.getInstance().optionTroop();
                        }
                        break;
                    }
                    case 3076: {
                        object = new TeamObject();
                        ((TeamObject) object).bytTeamId = dataInputStream.readByte();
                        ((TeamObject) object).intId = dataInputStream.readInt();
                        ((TeamObject) object).strName = dataInputStream.readUTF();
                        ((TeamObject) object).setOcc(dataInputStream.readByte());
                        ((TeamObject) object).blnIsMale = dataInputStream.readByte() == 1;
                        ((TeamObject) object).shtLevel = dataInputStream.readShort();
                        ((TeamObject) object).bytTroopRank = dataInputStream.readByte();
                        ((TeamObject) object).blnIsOnLine = true;
                        ((TeamObject) object).bytHead = ORPMe.ME.bytCountry == 1 ? (((TeamObject) object).blnIsMale ? (byte) 0 : 1) : (((TeamObject) object).blnIsMale ? (byte) 2 : (byte) 3);
                        NetParse.getInstance().clearTwoRect();
                        DCanvas.getInstance().addInformation(String.valueOf(((TeamObject) object).strName) + "加入了队伍", 0xFFFFFF);
                        GameUI.getInstance().pushTeam((TeamObject) object);
                        GameUI.getInstance().setTeamNameColor(((TeamObject) object).strName, true);
                        NetParse.getInstance().TeamPlayerChange();
                        GameUI.getInstance().addTeamer();
                        NetParse.getInstance().optionTroop();
                        break;
                    }
                    case 3077: {
                        int n = dataInputStream.readInt();
                        int n42 = 0;
                        while (n42 < Param.getInstance().vTeam.size()) {
                            if (((TeamObject) Param.getInstance().vTeam.elementAt((int) n42)).intId == n) {
                                GameUI.getInstance().setTeamNameColor(((TeamObject) Param.getInstance().vTeam.elementAt((int) n42)).strName, false);
                                Param.getInstance().vTeam.removeElementAt(n42);
                                GameUI.getInstance().addTeamer();
                                NetParse.getInstance().TeamPlayerChange();
                                NetParse.getInstance().optionTroop();
                                break block5;
                            }
                            n42 = (byte) (n42 + 1);
                        }
                        break;
                    }
                    case 3079: {
                        object = dataInputStream.readUTF();
                        DCanvas.getInstance().addInformation((String) object);
                        ORPMe.ME.bytTeamRank = 0;
                        ORPMe.ME.bytMeTeamID = 0;
                        if (Param.getInstance().vTeam != null) {
                            int n = 0;
                            while (n < Param.getInstance().vTeam.size()) {
                                GameUI.getInstance().setTeamNameColor(((TeamObject) Param.getInstance().vTeam.elementAt((int) n)).strName, false);
                                n = (byte) (n + 1);
                            }
                            NetParse.getInstance().TeamPlayerChange();
                        }
                        Param.getInstance().vTeam = null;
                        Param.getInstance().bytsMyTeam = null;
                        NetParse.getInstance().optionTroop();
                        break;
                    }
                    case 7435: {
                        NetParse.getInstance().readMosterBox(dataInputStream);
                        break;
                    }
                    case 7448: {
                        byte by = dataInputStream.readByte();
                        byte by55 = 0;
                        while (by55 < by) {
                            NetParse.getInstance().readMosterBox(dataInputStream);
                            by55 = (byte) (by55 + 1);
                        }
                        break;
                    }
                    case 7436: {
                        int n = dataInputStream.readInt();
                        byte by = dataInputStream.readByte();
                        GameUI.getInstance().delVObject(n, by, (byte) 1);
                        break;
                    }
                    case 7447: {
                        int n = dataInputStream.readInt();
                        byte by = dataInputStream.readByte();
                        if (Param.getInstance().htBox == null || Param.getInstance().htBox.isEmpty()) {
                            break;
                        }
                        Enumeration enumeration = Param.getInstance().htBox.elements();
                        while (enumeration.hasMoreElements()) {
                            Box box = (Box) enumeration.nextElement();
                            if (box.bytTileY != by || 1 != box.bytBoxType || n != box.intUserId) {
                                continue;
                            }
                            box.setBoxState(false);
                            if (box.blnShine) {
                                byte[] byArray10 = Map.getInstance().bytsBoxXY[box.bytTileY];
                                byte by56 = box.bytTileX;
                                byArray10[by56] = (byte) (byArray10[by56] - 1);
                            }
                            if (Param.getInstance().bytSelectType == 4 && Param.getInstance().intSelectId == n) {
                                ORPMe.ME.delSelsectRole();
                                break block5;
                            }
                            break block623;
                        }
                        break;
                    }
                    case 10766: {
                        byte by = dataInputStream.readByte();
                        byte by57 = 0;
                        while (by57 < by) {
                            NetParse.getInstance().readWorldBox(dataInputStream);
                            by57 = (byte) (by57 + 1);
                        }
                        break;
                    }
                    case 11539: {
                        NetParse.getInstance().readWorldBox(dataInputStream);
                        break;
                    }
                    case 11540: {
                        int n = dataInputStream.readInt();
                        byte by = dataInputStream.readByte();
                        GameUI.getInstance().delVObject(n, by, (byte) 2);
                        break;
                    }
                    case 7431: {
                        byte by = dataInputStream.readByte();
                        byte by58 = 0;
                        while (by58 < by) {
                            byte by59 = dataInputStream.readByte();
                            short s56 = dataInputStream.readShort();
                            PackageObject packageObject = (PackageObject) Param.getInstance().hPackageEquip.get(new Integer(by59));
                            if (packageObject != null) {
                                packageObject.shtStamina = s56;
                                packageObject.intColor = packageObject.shtStamina == 0 ? Macro.INT_PROP_COLOR[0] : Macro.INT_PROP_COLOR[packageObject.bytQuality];
                            }
                            by58 = (byte) (by58 + 1);
                        }
                        GameUI.getInstance().checkEquipStamina();
                        break;
                    }
                    case 3591: {
                        int n = dataInputStream.readInt();
                        int[] nArray = new int[n];
                        int[] nArray2 = new int[n];
                        int n43 = 0;
                        while (n43 < n) {
                            nArray[n43] = dataInputStream.readInt();
                            nArray2[n43] = dataInputStream.readInt();
                            ++n43;
                        }
                        NetParse.getInstance().updateSkillCD(nArray, nArray2);
                        break;
                    }
                    case 1030: {
                        if (Macro.bytGameType == 1) {
                            if (FormDes.getInstance() != null) {
                                FormDes.getInstance().clean();
                            }
                            GameControl.getInstance().CreateState((byte) 7);
                            DialogUI.getInstance().setDialog((byte) 7, "已从游戏服务器断开(1007)", (byte) 2);
                        } else {
                            GameControl.getInstance().CreateState((byte) 7);
                            DialogUI.getInstance().setDialog((byte) 7, "已从游戏服务器断开(1008)", (byte) 2);
                        }
                        NetManager.getInstance().stopNetManager(NetManager.getInstance().MC);
                        NetParse.getInstance().stopParse();
                        break;
                    }
                    case 10764: {
                        byte by = dataInputStream.readByte();
                        Param.getInstance().animalNum = by;
                        String[] stringArray = new String[by];
                        if (Param.getInstance().htAnimal == null) {
                            Param.getInstance().htAnimal = new Hashtable();
                        }
                        byte by60 = 0;
                        while (by60 < by) {
                            int n = dataInputStream.readInt();
                            byte by61 = dataInputStream.readByte();
                            byte by62 = dataInputStream.readByte();
                            short s57 = dataInputStream.readShort();
                            short s58 = dataInputStream.readShort();
                            boolean bl = false;
                            if (Param.getInstance().htAnimal != null) {
                                AnimalObject animalObject;
                                Enumeration enumeration = Param.getInstance().htAnimal.elements();
                                while (enumeration.hasMoreElements()) {
                                    animalObject = (AnimalObject) enumeration.nextElement();
                                    if (animalObject.intUserId != n) {
                                        continue;
                                    }
                                    animalObject.bytTileX = by61;
                                    animalObject.bytTileY = by62;
                                    bl = true;
                                }
                                if (!bl) {
                                    animalObject = new AnimalObject();
                                    animalObject.newAnimal(by61, by62);
                                    animalObject.setAnimalPic(n, s58, s57);
                                    GameUI.getInstance().addVObject(animalObject);
                                }
                            }
                            by60 = (byte) (by60 + 1);
                        }
                        break;
                    }
                    case 10774: {
                        int n = dataInputStream.readInt();
                        ORPlayer oRPlayer = null;
                        if (Param.getInstance().htRolePlayer != null) {
                            oRPlayer = (ORPlayer) Param.getInstance().htRolePlayer.get(new Integer(n));
                        }
                        byte by = dataInputStream.readByte();
                        Hashtable hashtable = new Hashtable();
                        byte by63 = 0;
                        while (by63 < by) {
                            short s59;
                            short s60;
                            short s61;
                            short s62;
                            int n44;
                            byte by64;
                            PackageObject packageObject = new PackageObject();
                            packageObject.petShow = by64 = dataInputStream.readByte();
                            packageObject.petKey = n44 = dataInputStream.readInt();
                            ORPlayer.itSelectPetId = n44;
                            packageObject.shortIcon = s62 = dataInputStream.readShort();
                            packageObject.shtPngId = s61 = dataInputStream.readShort();
                            packageObject.shtAnuId = s60 = dataInputStream.readShort();
                            packageObject.shtType = s59 = dataInputStream.readShort();
                            short s63 = dataInputStream.readShort();
                            hashtable.put(new Integer(packageObject.intPOindex), packageObject);
                            by63 = (byte) (by63 + 1);
                        }
                        if (oRPlayer != null) {
                            oRPlayer.hPackagePet = hashtable;
                        }
                        break;
                    }
                    case 11569: {
                        int n = dataInputStream.readInt();
                        short s64 = dataInputStream.readShort();
                        byte by = dataInputStream.readByte();
                        byte[] byArray11 = new byte[by];
                        dataInputStream.readFully(byArray11);
                        if (Param.getInstance().htAnimal != null && !Param.getInstance().htAnimal.isEmpty()) {
                            Hashtable hashtable = Param.getInstance().htAnimal;
                            synchronized (hashtable) {
                                Enumeration enumeration = Param.getInstance().htAnimal.elements();
                                while (enumeration.hasMoreElements()) {
                                    AnimalObject animalObject = (AnimalObject) enumeration.nextElement();
                                    if (animalObject.intUserId != n) {
                                        continue;
                                    }
                                    int n45 = 0;
                                    while (n45 < byArray11.length) {
                                        if (byArray11[n45] != 0) {
                                            animalObject.pushTask((byte) 1, byArray11[n45]);
                                        }
                                        ++n45;
                                    }
                                    break;
                                }
                            }
                        }
                        break;
                    }
                    case 3846: {
                        int n = dataInputStream.readInt();
                        byte by = dataInputStream.readByte();
                        ONpc oNpc = GameUI.getInstance().getNpc(n);
                        DebugFrame.getInstance().logIn("更新NPC任务标记: _npcId = " + n + "_task = " + by);
                        if (oNpc != null) {
                            oNpc.setNpcTaskSign(by);
                            oNpc.bytIsTask = by;
                            if (Macro.BLN_ACCERATE_NPC_TASK) {
                                DebugFrame.getInstance().logIn("获取任务图标: 模拟与" + n + "的交互");
                                NetSend.getInstance().sendNpcOption(oNpc.intUserId);
                            }
                        }
                        break;
                    }
                    case 10763: {
                        Macro.shtGameDataReceived = (short) (Macro.shtGameDataReceived | 2);
                        NetParse.getInstance().readOtherData(dataInputStream);
                        break;
                    }
                    case 3849: {
                        byte by = dataInputStream.readByte();
                        DebugFrame.getInstance().logIn("NPC接收任务小图标:" + by);
                        byte by65 = 0;
                        while (by65 < by) {
                            int n = dataInputStream.readInt();
                            byte by66 = dataInputStream.readByte();
                            ONpc oNpc = GameUI.getInstance().getNpc(n);
                            DebugFrame.getInstance().logIn("各自NPC _npcId" + n + "_type = " + by66);
                            if (oNpc != null) {
                                oNpc.setNpcTaskSign(by66);
                                if (Macro.BLN_ACCERATE_NPC_TASK) {
                                    NetSend.getInstance().sendNpcOption(oNpc.intUserId);
                                }
                            }
                            by65 = (byte) (by65 + 1);
                        }
                        break;
                    }
                    case 3081: {
                        byte by = dataInputStream.readByte();
                        String string = dataInputStream.readUTF();
                        if (DCanvas.getInstance().UMenu != null && MenuUI.getInstance().getState() == by && FullInfo.getInstance() != null) {
                            FullInfo.getInstance().setFullRectMenu(string);
                        }
                        break;
                    }
                    case 23812: {
                        byte by = dataInputStream.readByte();
                        switch (by) {
                            case 1: {
                                String string = dataInputStream.readUTF();
                                int n = dataInputStream.readInt();
                                if (!Rms.blnBusiness) {
                                    NetSend.getInstance().sendDeal((byte) 6, n);
                                    return 23812;
                                }
                                Param.getInstance().strDealName = string;
                                Param.getInstance().intDealID = n;
                                if (Param.getInstance().htRolePlayer.containsKey(new Integer(Param.getInstance().intDealID))) {
                                    ORPMe.ME.setSelectRole((ORole) Param.getInstance().htRolePlayer.get(new Integer(Param.getInstance().intDealID)));
                                }
                                Param.getInstance().bytDealGoods = 0;
                                NetSend.getInstance().sendDealBegPack(Param.getInstance().intDealID, Param.getInstance().bytDealGoods);
                                break;
                            }
                            case 2: {
                                int n;
                                Param.getInstance().intDealGold = n = dataInputStream.readInt();
                                break;
                            }
                            case 4: {
                                byte by67 = dataInputStream.readByte();
                                PackageObject packageObject = new PackageObject();
                                packageObject.byteEquipType = (byte) -1;
                                if (by67 == 0) {
                                    packageObject.strName = dataInputStream.readUTF();
                                    packageObject.shtIcon = dataInputStream.readShort();
                                    packageObject.shtPOnum = dataInputStream.readShort();
                                    packageObject.bytQuality = dataInputStream.readByte();
                                    packageObject.intColor = Macro.INT_PROP_COLOR[packageObject.bytQuality];
                                    packageObject.strInfo = dataInputStream.readUTF();
                                } else {
                                    packageObject.shtIcon = dataInputStream.readShort();
                                    packageObject.strName = dataInputStream.readUTF();
                                    packageObject.shtPOnum = dataInputStream.readShort();
                                    byte by68 = dataInputStream.readByte();
                                    NetParse.getInstance().readPropAttribute(dataInputStream, packageObject, by68);
                                }
                                if (DCanvas.getInstance().UDialog == null || DialogUI.getInstance().bytDialogState != 9) {
                                    break;
                                }
                                if (Param.getInstance().vDealDuiProp == null) {
                                    Param.getInstance().vDealDuiProp = new Hashtable();
                                }
                                if (Param.getInstance().vDealDuiProp.size() >= 6) {
                                    break;
                                }
                                DialogUI.getInstance().addDealProp(Param.getInstance().vDealDuiProp, packageObject, packageObject.shtPOnum);
                                break;
                            }
                            case 5: {
                                DCanvas.getInstance().addInformation(String.valueOf(Param.getInstance().strDealName) + "确认交易", 0xFFFFFF);
                                break;
                            }
                            case 6: {
                                DCanvas.getInstance().addInformation("交易取消");
                                if (DCanvas.getInstance().UDialog == null || DialogUI.getInstance().bytDialogState != 9) {
                                    break;
                                }
                                GameControl.getInstance().delUIbase(7);
                                if (DCanvas.getInstance().UMenu != null) {
                                    GameControl.getInstance().delUIbase(MenuUI.getInstance().bytDelState);
                                }
                                NetParse.getInstance().clearDeal();
                                break;
                            }
                            case 7: {
                                DCanvas.getInstance().addInformation("交易完成");
                                if (DCanvas.getInstance().UDialog == null || DialogUI.getInstance().bytDialogState != 9) {
                                    break;
                                }
                                GameControl.getInstance().delUIbase(7);
                                NetParse.getInstance().clearDeal();
                                break;
                            }
                            case 8: {
                                DCanvas.getInstance().addInformation("对方正忙");
                                if (DCanvas.getInstance().UDialog == null || DialogUI.getInstance().bytDialogState != 9) {
                                    break;
                                }
                                GameControl.getInstance().delUIbase(7);
                                NetParse.getInstance().clearDeal();
                                break;
                            }
                            case 9: {
                                DialogUI.getInstance().dealIsPeerLock = true;
                                break;
                            }
                            case 11: {
                                short s65 = dataInputStream.readShort();
                                if (Param.getInstance().vDealDuiProp == null || !Param.getInstance().vDealDuiProp.containsKey(new Integer(s65))) {
                                    break;
                                }
                                Param.getInstance().vDealDuiProp.remove(new Integer(s65));
                            }
                        }
                        NetParse.getInstance().clearTwoRect();
                        break;
                    }
                    case 2562: {
                        byte by;
                        ORPMe.ME.bytOfficialRank = by = dataInputStream.readByte();
                        break;
                    }
                    case 2563: {
                        DCanvas.getInstance().addInformation("帮派解散了");
                        ORPMe.ME.bytOfficialRank = 0;
                        ORPMe.ME.strConsortia = "";
                        ORPMe.ME.bytDrawNameType = (byte) -1;
                        if (GameUI.getInstance().checkMeMenuUi(34)) {
                            GameControl.getInstance().delUIbase(MenuUI.getInstance().bytDelState);
                        }
                        break;
                    }
                    case 2566: {
                        int n = dataInputStream.readInt();
                        byte by = dataInputStream.readByte();
                        String string = "";
                        if (by == 1) {
                            string = dataInputStream.readUTF();
                        }
                        if (Param.getInstance().htRolePlayer == null) {
                            break;
                        }
                        Enumeration enumeration = Param.getInstance().htRolePlayer.elements();
                        while (enumeration.hasMoreElements()) {
                            ORole oRole = (ORole) enumeration.nextElement();
                            if (oRole.intUserId != n) {
                                continue;
                            }
                            oRole.strConsortia = string;
                            oRole.bytDrawNameType = (byte) -1;
                            if (by == 1 && n == ORPMe.ME.intUserId) {
                                ORPMe.ME.bytOfficialRank = 1;
                                DCanvas.getInstance().addInformation("加入了" + string + "帮派", 0xBCBBBB);
                                break block5;
                            }
                            if (n == ORPMe.ME.intUserId) {
                                ORPMe.ME.bytOfficialRank = 0;
                                ORPMe.ME.strConsortia = "";
                                ORPMe.ME.bytDrawNameType = (byte) -1;
                                if (GameUI.getInstance().checkMeMenuUi(34)) {
                                    GameControl.getInstance().delUIbase(MenuUI.getInstance().bytDelState);
                                    break block5;
                                }
                            }
                            break block623;
                        }
                        break;
                    }
                    case 3074: {
                        Param.getInstance().strTroopInviteName = dataInputStream.readUTF();
                        if (!Rms.blnTeam) {
                            NetSend.getInstance().sendTeamInvite((byte) 0);
                            return 3074;
                        }
                        if (GameUI.getInstance().checkDialogPRI((byte) 11, (byte) 2)) {
                            DialogUI.getInstance().setDialog((byte) 11, String.valueOf(Param.getInstance().strTroopInviteName) + "邀请你加入队伍，是否接受？", (byte) 2);
                        }
                        break;
                    }
                    case 6660: {
                        Param.getInstance().vMenuMemory = null;
                        if (DCanvas.getInstance().UMenu != null) {
                            GameControl.getInstance().delUIbase(MenuUI.getInstance().bytDelState);
                        }
                        break;
                    }
                    case 11523: {
                        int n = dataInputStream.readInt();
                        byte by = dataInputStream.readByte();
                        ONpc oNpc = GameUI.getInstance().getNpc(n);
                        if (oNpc != null) {
                            if (Param.getInstance().oSelectNpc != null && n == Param.getInstance().oSelectNpc.intUserId) {
                                GameUI.getInstance().setButton(0, 100, 2);
                            }
                            oNpc.blnIsTalk = by == 1;
                        }
                        break;
                    }
                    case 11553: {
                        int n = dataInputStream.readInt();
                        byte by = dataInputStream.readByte();
                        byte by69 = dataInputStream.readByte();
                        ONpc oNpc = GameUI.getInstance().getNpc(n);
                        if (oNpc != null) {
                            oNpc.setTileXY(by, by69);
                        }
                        break;
                    }
                    case 2564: {
                        boolean bl = false;
                        int n = dataInputStream.readByte();
                        ORPMe.ME.UpgradeConsortia = new int[n];
                        int n46 = 0;
                        while (n46 < n) {
                            ORPMe.ME.UpgradeConsortia[n46] = dataInputStream.readInt();
                            ++n46;
                        }
                        n46 = dataInputStream.readByte();
                        ORPMe.ME.UpgradeMember = new short[n46];
                        int n47 = 0;
                        while (n47 < n46) {
                            ORPMe.ME.UpgradeMember[n47] = dataInputStream.readShort();
                            ++n47;
                        }
                        ORPMe.ME.ConsortiaLevel = dataInputStream.readByte();
                        ORPMe.ME.bytOfficialRank = dataInputStream.readByte();
                        n47 = dataInputStream.readShort();
                        ORPMe.ME.shtCNowNum = (short) n47;
                        short s66 = dataInputStream.readShort();
                        byte by = dataInputStream.readByte();
                        byte by70 = dataInputStream.readByte();
                        ORPMe.ME.shtCAllNum = s66;
                        Vector vector = null;
                        byte by71 = dataInputStream.readByte();
                        vector = new Vector(1, 1);
                        byte by72 = 0;
                        while (by72 < by71) {
                            TeamObject teamObject = new TeamObject();
                            teamObject.intId = dataInputStream.readInt();
                            teamObject.strName = dataInputStream.readUTF();
                            teamObject.bytTroopRank = dataInputStream.readByte();
                            boolean bl2 = teamObject.blnIsOnLine = dataInputStream.readByte() == 1;
                            if (teamObject.blnIsOnLine) {
                                byte by73 = dataInputStream.readByte();
                                teamObject.setOcc(by73);
                                teamObject.bytType = 1;
                                teamObject.shtLevel = dataInputStream.readShort();
                                teamObject.blnIsMale = dataInputStream.readByte() == 1;
                            }
                            NetParse.getInstance().addElement(vector, teamObject);
                            by72 = (byte) (by72 + 1);
                        }
                        bl = true;
                        if (bl) {
                            GameUI.getInstance().setNextMenu((byte) 34, ORPMe.ME.strConsortia);
                            NetParse.getInstance().clearTwoRect();
                            Param.getInstance().shtNoncePage = by;
                            Param.getInstance().shtAllPage = by70;
                            MenuUI.getInstance().setRoleList((byte) 34, vector);
                            Param.getInstance().vCommonList = vector;
                            bl = false;
                            break;
                        }
                        DCanvas.getInstance().addInformation("请重试");
                        break;
                    }
                    case 2568: {
                        ORPMe.ME.ConsortiaName = dataInputStream.readUTF();
                        ORPMe.ME.MasterName = dataInputStream.readUTF();
                        ORPMe.ME.ConsortiaLevel = dataInputStream.readByte();
                        ORPMe.ME.shtCNowNum = dataInputStream.readShort();
                        new FullInfo("帮派信息", 0, (byte) 34);
                        FullInfo.getInstance().setFullRectMenu("帮派名称：" + ORPMe.ME.ConsortiaName + "\n帮主：" + ORPMe.ME.MasterName + "\n帮派等级：" + ORPMe.ME.ConsortiaLevel + "级" + "\n帮派成员：" + ORPMe.ME.shtCNowNum + "人");
                        break;
                    }
                    case 2569: {
                        ORPMe.ME.ConsortiaLevel = dataInputStream.readByte();
                        ORPMe.ME.shtCNowNum = dataInputStream.readShort();
                        break;
                    }
                    case 2565: {
                        int n = dataInputStream.readInt();
                        String string = dataInputStream.readUTF();
                        int n48 = dataInputStream.readInt();
                        String string13 = dataInputStream.readUTF();
                        if (GameUI.getInstance().checkDialogPRI((byte) 12, (byte) 2)) {
                            NetParse.getInstance().clearTwoRect();
                            Param.getInstance().intConsortiaId = n48;
                            Param.getInstance().intGuildRoleId = n;
                            StringBuffer stringBuffer = new StringBuffer();
                            stringBuffer.append(string);
                            stringBuffer.append("邀请你加入");
                            stringBuffer.append(string13);
                            stringBuffer.append("帮派");
                            DialogUI.getInstance().setDialog((byte) 12, stringBuffer.toString(), (byte) 2);
                        }
                        break;
                    }
                    case 2567: {
                        if (GameUI.getInstance().checkMeMenuUi(34)) {
                            NetSend.getInstance().sendConsortia((byte) 1, (byte) Param.getInstance().shtNoncePage, 1);
                        }
                        break;
                    }
                    case 23817: {
                        byte by = dataInputStream.readByte();
                        Vector vector = null;
                        if (by > 0) {
                            vector = new Vector();
                            byte by74 = 0;
                            while (by74 < by) {
                                byte by75 = dataInputStream.readByte();
                                int n = dataInputStream.readInt();
                                String string = dataInputStream.readUTF();
                                String string14 = dataInputStream.readUTF();
                                String string15 = dataInputStream.readUTF();
                                String string16 = dataInputStream.readUTF();
                                byte by76 = dataInputStream.readByte();
                                byte by77 = dataInputStream.readByte();
                                MailObject mailObject = new MailObject();
                                mailObject.bytType = by75;
                                mailObject.intId = n;
                                mailObject.strName = string14;
                                mailObject.strTitle = string;
                                mailObject.strContent = string15;
                                mailObject.strTime = string16;
                                mailObject.blnIsRead = by76 == 1;
                                mailObject.bytResidualD = by77;
                                vector.addElement(mailObject);
                                by74 = (byte) (by74 + 1);
                            }
                        } else {
                            DCanvas.getInstance().addInformation("没有信件");
                        }
                        if (GameUI.getInstance().checkMeMenuUi(-36)) {
                            NetParse.getInstance().clearTwoRect();
                            Param.getInstance().vCommonList = vector;
                            MenuUI.getInstance().setMailList((short) 0);
                        }
                        break;
                    }
                    case 11543: {
                        if (Param.getInstance().htTask == null) {
                            Param.getInstance().htTask = new Hashtable();
                            Map.getInstance().bytsTaskXY = new byte[Map.getInstance().bytRow][Map.getInstance().bytColumn];
                        }
                        object = new Task();
                        ((ORole) object).intUserId = dataInputStream.readInt();
                        ((ORole) object).strNickName = dataInputStream.readUTF();
                        ((Task) object).bytTaskType = 1;
                        byte by = dataInputStream.readByte();
                        byte by78 = dataInputStream.readByte();
                        ((Task) object).newTask(by, by78);
                        short s67 = dataInputStream.readShort();
                        ((Task) object).setTaskPic(s67);
                        byte by79 = dataInputStream.readByte();
                        GameUI.getInstance().addObject((Task) object);
                        ((Task) object).setTaskState(by79 == 1);
                        break;
                    }
                    case 11542: {
                        int n = dataInputStream.readInt();
                        byte by = dataInputStream.readByte();
                        GameUI.getInstance().delTaskObject(n, by);
                        break;
                    }
                    case 11538:
                    case 11541: {
                        boolean bl;
                        int n = dataInputStream.readInt();
                        byte by = dataInputStream.readByte();
                        boolean bl3 = bl = dataInputStream.readByte() == 1;
                        if (Param.getInstance().htTask == null || Param.getInstance().htTask.isEmpty()) {
                            break;
                        }
                        Enumeration enumeration = Param.getInstance().htTask.elements();
                        while (enumeration.hasMoreElements()) {
                            Task task = (Task) enumeration.nextElement();
                            if (task.bytTileY != by || task.intUserId != n) {
                                continue;
                            }
                            task.setTaskState(bl);
                            if (!bl && Param.getInstance().bytSelectType == 5 && Param.getInstance().intSelectId == n) {
                                if (DCanvas.getInstance().UMenu != null && MenuUI.getInstance().getState() == 1) {
                                    GameControl.getInstance().delUIbase(MenuUI.getInstance().bytDelState);
                                }
                                ORPMe.ME.delSelsectRole();
                                break block5;
                            }
                            break block623;
                        }
                        break;
                    }
                    case 11299: {
                        short s68 = dataInputStream.readByte();
                        Vector vector = null;
                        if (s68 > 0) {
                            int n;
                            ORPMe.ME.uesID = n = dataInputStream.readInt();
                            ORPMe.ME.bytApprentice = dataInputStream.readByte();
                            vector = new Vector();
                            short s69 = 0;
                            while (s69 < s68) {
                                TeamObject teamObject = new TeamObject();
                                teamObject.intId = dataInputStream.readInt();
                                teamObject.strName = dataInputStream.readUTF();
                                boolean bl = teamObject.blnIsOnLine = dataInputStream.readByte() == 1;
                                if (teamObject.blnIsOnLine) {
                                    byte by = dataInputStream.readByte();
                                    teamObject.setOcc(by);
                                    teamObject.shtLevel = dataInputStream.readShort();
                                    teamObject.blnIsMale = dataInputStream.readByte() == 1;
                                }
                                teamObject.bytRelation = dataInputStream.readByte();
                                vector.addElement(teamObject);
                                s69 = (short) (s69 + 1);
                            }
                            NetParse.getInstance().MasterPlayerList(vector);
                        } else {
                            DCanvas.getInstance().addInformation("你不存在师徒关系");
                        }
                        if (Param.bytChatSubMasterNumCount[MenuUI.getInstance().intRelation] == 0) {
                            DCanvas.getInstance().addInformation("你没有师傅");
                        }
                        if (GameUI.getInstance().checkMeMenuUi(-48)) {
                            MenuUI.getInstance().getPeople(vector, false);
                        }
                        break;
                    }
                    case 24322: {
                        int n;
                        DebugFrame.getInstance().logIn("获取好友列表");
                        byte by = dataInputStream.readByte();
                        int n49 = dataInputStream.readShort();
                        DebugFrame.getInstance().logIn("列表数量：" + n49);
                        Vector vector = null;
                        if (n49 > 0) {
                            vector = new Vector(1, 1);
                            n = 0;
                            while (n < n49) {
                                TeamObject teamObject = new TeamObject();
                                teamObject.intId = dataInputStream.readInt();
                                teamObject.strName = dataInputStream.readUTF();
                                boolean bl = teamObject.blnIsOnLine = dataInputStream.readByte() == 1;
                                if (teamObject.blnIsOnLine) {
                                    teamObject.setOcc(dataInputStream.readByte());
                                    teamObject.shtLevel = dataInputStream.readShort();
                                    teamObject.blnIsMale = dataInputStream.readByte() == 1;
                                } else {
                                    byte by80 = dataInputStream.readByte();
                                    short s70 = dataInputStream.readShort();
                                }
                                NetParse.getInstance().insertTeamPlayer(vector, teamObject);
                                n = (short) (n + 1);
                            }
                        }
                        n = 0;
                        if (by == 1) {
                            if (GameUI.getInstance().checkMeMenuUi(-31)) {
                                MenuUI.getInstance().getPeople(vector, true);
                            }
                            if (n49 == 0) {
                                return 24322;
                            }
                        } else if (by == 2) {
                            n = -32;
                        } else if (by == 3) {
                            n = -33;
                        }
                        if (GameUI.getInstance().checkMeMenuUi(n)) {
                            MenuUI.getInstance().getPeople(vector, true);
                        }
                        break;
                    }
                    case 24323: {
                        byte by = dataInputStream.readByte();
                        TeamObject teamObject = new TeamObject();
                        teamObject.intId = dataInputStream.readInt();
                        teamObject.strName = dataInputStream.readUTF();
                        teamObject.blnIsOnLine = true;
                        teamObject.setOcc(dataInputStream.readByte());
                        teamObject.shtLevel = dataInputStream.readShort();
                        teamObject.blnIsMale = dataInputStream.readByte() == 1;
                        int n = 0;
                        if (by == 1) {
                            n = -31;
                            DCanvas.getInstance().addInformation("你已将" + teamObject.strName + "加为好友", 16642234);
                        } else if (by == 2) {
                            n = -32;
                            DCanvas.getInstance().addInformation("你已将" + teamObject.strName + "加入黑名单", 16642234);
                        } else if (by == 3) {
                            n = -33;
                            DCanvas.getInstance().addInformation("你已将" + teamObject.strName + "加入通缉名单", 16642234);
                        }
                        if (GameUI.getInstance().checkMeMenuUi(n)) {
                            if (Param.getInstance().vCommonList == null) {
                                Param.getInstance().vCommonList = new Vector(1, 1);
                            }
                            Param.getInstance().shtAllPage = (short) ((Param.getInstance().vCommonList.size() - 1) / 20 + 1);
                            if (Param.getInstance().IntRgbColor == null) {
                                Param.getInstance().IntRgbColor = DrawBase.getRGB(StringManager.getNewLineW(), Macro.FONTHEIGHT, 1144385017);
                            }
                            Param.getInstance().vCommonList.addElement(teamObject);
                            if (teamObject.blnIsOnLine) {
                                TeamObject teamObject2 = (TeamObject) Param.getInstance().vCommonList.elementAt(Param.getInstance().vCommonList.size() - 1);
                                Param.getInstance().vCommonList.removeElementAt(Param.getInstance().vCommonList.size() - 1);
                                Param.getInstance().vCommonList.insertElementAt(teamObject2, 0);
                            }
                            Param.getInstance().shtAllPage = (short) ((Param.getInstance().vCommonList.size() - 1) / 20 + 1);
                            MenuUI.getInstance().shtMenuMoveLength = (short) (Param.getInstance().vCommonList.size() > 20 ? 20 : Param.getInstance().vCommonList.size());
                        }
                        break;
                    }
                    case 7200: {
                        byte by = dataInputStream.readByte();
                        boolean bl = GameUI.getInstance().blnFight = by == 1;
                        if (GameUI.getInstance().blnFight) {
                            GameUI.getInstance().pushRoleStateIcon((short) 5);
                        } else {
                            GameUI.getInstance().delRoleStateIcon((short) 5);
                        }
                        if (!GameUI.getInstance().blnFight && Param.getInstance().strFullTitle != null) {
                            NetParse.getInstance().initCue(Param.getInstance().strFullTitle, Param.getInstance().strFullCue);
                            Param.getInstance().strFullTitle = null;
                            Param.getInstance().strFullCue = null;
                        }
                        break;
                    }
                    case 7445: {
                        byte by = dataInputStream.readByte();
                        int n = dataInputStream.readInt() * 1000;
                        int n50 = dataInputStream.readInt() * 1000;
                        short s71 = dataInputStream.readShort();
                        GameUI.getInstance().updateGoodsShortcutKeys(by, n, n50, s71);
                        break;
                    }
                    case 780: {
                        int n;
                        Macro.TIME_RESPONSE_CUT = (int) (System.currentTimeMillis() - Macro.TIME_START_CUT);
                        Param.MOBILE_USER_RUL = dataInputStream.readUTF();
                        if (GameControl.getInstance().isPhone) {
                            object = "&s=" + (int) System.currentTimeMillis();
                            NetManager.sendHttpGet(String.valueOf(Param.MOBILE_USER_RUL) + (String) object);
                            if (NetManager.message != null && !NetManager.message.equals("") && NetManager.message.indexOf("text/vnd.wap.wml") != -1) {
                                NetManager.sendHttpGet(String.valueOf(Param.MOBILE_USER_RUL) + (String) object);
                            }
                        }
                        if ((n = dataInputStream.readByte()) > 0) {
                            String[] stringArray = new String[n];
                            int n51 = 0;
                            while (n51 < n) {
                                stringArray[n51] = dataInputStream.readUTF();
                                ++n51;
                            }
                            Param.getInstance().CHIAN_MOBILE_LOGIN_RUL = stringArray;
                            NetSend.getInstance().sendMobileLogin();
                        }
                        break;
                    }
                    case 782: {
                        byte by = dataInputStream.readByte();
                        DebugFrame.getInstance().logIn("获取 游戏包下载地址" + by);
                        switch (by) {
                            case 0: {
                                break;
                            }
                            case 1: {
                                int n = dataInputStream.readByte();
                                if (n > 0) {
                                    String string = dataInputStream.readUTF();
                                    int n52 = dataInputStream.readInt();
                                    int n53 = 0;
                                    while (n53 < n) {
                                        String string17 = dataInputStream.readUTF();
                                        String string18 = dataInputStream.readUTF();
                                        new MockDownConnector(string17, string18, string, n52);
                                        ++n53;
                                    }
                                }
                                break block623;
                            }
                        }
                        break;
                    }
                    case 781: {
                        break;
                    }
                    case 1058: {
                        int n = dataInputStream.readByte();
                        DebugFrame.getInstance().logIn("数量：" + n);
                        int n54 = 0;
                        while (n54 < n) {
                            byte by = dataInputStream.readByte();
                            DebugFrame.getInstance().logIn("更新快捷键页数：" + by);
                            byte by81 = dataInputStream.readByte();
                            DebugFrame.getInstance().logIn("更新快捷键位置 1-8：" + by81);
                            int n55 = dataInputStream.readInt();
                            DebugFrame.getInstance().logIn("更新快捷键 消耗品ID：" + n55);
                            short s72 = dataInputStream.readShort();
                            DebugFrame.getInstance().logIn("更新快捷键消耗品数量：" + s72);
                            if (Param.getInstance().intShortcutKeys[by81 + (by - 1) * 8][0] == n55) {
                                Param.getInstance().intShortcutKeys[by81 + (by - 1) * 8][5] = s72;
                            }
                            ++n54;
                        }
                        break;
                    }
                    case 23841: {
                        boolean bl = false;
                        int n = dataInputStream.readByte();
                        Param.getInstance().Main_List = new Vector(1, 1);
                        Hashtable hashtable = new Hashtable();
                        int n56 = 0;
                        while (n56 < n) {
                            String[] stringArray = new String[4];
                            byte by = dataInputStream.readByte();
                            String string = dataInputStream.readUTF();
                            byte by82 = dataInputStream.readByte();
                            byte by83 = dataInputStream.readByte();
                            stringArray[0] = "" + by;
                            stringArray[1] = string;
                            stringArray[2] = "" + by82;
                            stringArray[3] = "" + by83;
                            Param.getInstance().Main_List.addElement(stringArray);
                            if (by83 == 1) {
                                int n57 = dataInputStream.readByte();
                                Param.getInstance().Second_List = new Vector(1, 1);
                                Hashtable hashtable2 = new Hashtable();
                                int n58 = 0;
                                while (n58 < n57) {
                                    String[] stringArray2 = new String[4];
                                    byte by84 = dataInputStream.readByte();
                                    String string19 = dataInputStream.readUTF();
                                    byte by85 = dataInputStream.readByte();
                                    byte by86 = dataInputStream.readByte();
                                    stringArray2[0] = "" + by84;
                                    stringArray2[1] = string19;
                                    stringArray2[2] = "" + by85;
                                    stringArray2[3] = "" + by86;
                                    Param.getInstance().Second_List.addElement(stringArray2);
                                    if (by86 > 0) {
                                        int n59 = dataInputStream.readByte();
                                        Param.getInstance().Submenu_List = new Vector(1, 1);
                                        int n60 = 0;
                                        while (n60 < n59) {
                                            String[] stringArray3 = new String[3];
                                            byte by87 = dataInputStream.readByte();
                                            String string20 = dataInputStream.readUTF();
                                            byte by88 = dataInputStream.readByte();
                                            stringArray3[0] = "" + by87;
                                            stringArray3[1] = string20;
                                            stringArray3[2] = "" + by88;
                                            Param.getInstance().Submenu_List.addElement(stringArray3);
                                            ++n60;
                                        }
                                        hashtable2.put(new Integer(by84), Param.getInstance().Submenu_List);
                                    }
                                    ++n58;
                                }
                                Param.getInstance().Occupation_List = hashtable2;
                            }
                            hashtable.put(new Integer(by), Param.getInstance().Second_List);
                            Param.getInstance().MainOccupation_List = hashtable;
                            ++n56;
                        }
                        bl = true;
                        if (bl && Param.getInstance().Submenu_List != null && Param.getInstance().Second_List != null && Param.getInstance().Main_List != null) {
                            NetParse.getInstance().clearTwoRect();
                            SystemMenuUI.getInstance().setState((byte) -15);
                            break;
                        }
                        DCanvas.getInstance().addInformation("请重试");
                        break;
                    }
                    case 23840: {
                        boolean bl = false;
                        int n = dataInputStream.readByte();
                        Param.getInstance().Tab_Name = new String[n];
                        int n61 = 0;
                        while (n61 < n) {
                            Param.getInstance().Tab_Name[n61] = dataInputStream.readUTF();
                            ++n61;
                        }
                        n61 = dataInputStream.readByte();
                        Param.getInstance().Member_List = new Vector(1, 1);
                        int n62 = 0;
                        while (n62 < n61) {
                            TeamObject teamObject = new TeamObject();
                            teamObject.ranking = dataInputStream.readByte();
                            teamObject.strName = dataInputStream.readUTF();
                            teamObject.occupation = dataInputStream.readUTF();
                            teamObject.rankValue = dataInputStream.readInt();
                            teamObject.intId = dataInputStream.readInt();
                            teamObject.blnIsOnLine = dataInputStream.readByte() == 1;
                            Param.getInstance().Member_List.addElement(teamObject);
                            ++n62;
                        }
                        bl = true;
                        if (bl && Param.getInstance().Member_List != null && Param.getInstance().Tab_Name != null) {
                            MenuUI.getInstance().RecvRankValue();
                        }
                        break;
                    }
                    case 783: {
                        Param.getInstance().accound = dataInputStream.readUTF();
                        Param.getInstance().password = dataInputStream.readUTF();
                        String string21 = dataInputStream.readUTF();
                        Rms.saveUseCode(true);
                        Rms.saveID(Param.getInstance().accound, Param.getInstance().password, true);
                        LandUI.getInstance().twodialog = null;
                        GameControl.getInstance().CreateState((byte) 7);
                        DialogUI.getInstance().setDialog((byte) 22, string21, (byte) 2);
                        break;
                    }
                    case 23831: {
                        String string;
                        int n;
                        int n63;
                        object = dataInputStream.readUTF();
                        String string22 = ImageManager.parseResDirName((String) object);
                        short s73 = dataInputStream.readShort();
                        byte[] byArray12 = new byte[s73];
                        dataInputStream.readFully(byArray12);
                        ImageManager.saveResRecordStore((String) object, byArray12);
                        ImageManager.removeRequestRes((String) object);
                        int[] nArray = ImageManager.getPoolAndResType((String) object);
                        if (nArray != null && nArray[0] >= 0 && nArray[1] >= 0) {
                            if (nArray[1] == 2) {
                                ImageManager.loadSpriteDataById(nArray[0], (String) object);
                            } else if (nArray[1] == 1) {
                                ImageManager.loadSpritePngById(nArray[0], (String) object);
                            }
                        }
                        if (string22.equals("prop") || string22.equals("sys")) {
                            n63 = ((String) object).lastIndexOf(47);
                            n = ((String) object).indexOf(46);
                            string = ((String) object).substring(n63 + 1, n);
                            try {
                                short s74 = (short) Integer.parseInt(string);
                                Image image = ImageManager.CreateImage(String.valueOf(s74), string22);
                                if (image != null && Param.getInstance().hImgObject != null) {
                                    Param.getInstance().hImgObject.put(new Integer(s74), image);
                                }
                            } catch (Exception exception) {
                                exception.printStackTrace();
                            }
                        }
                        if (string22.equals("states")) {
                            n63 = ((String) object).lastIndexOf(47);
                            n = ((String) object).indexOf(46);
                            string = ((String) object).substring(n63 + 1, n);
                            try {
                                short s75 = (short) Integer.parseInt(string);
                                if (GameUI.getInstance() != null) {
                                    GameUI.getInstance().addStateIcon(s75);
                                }
                            } catch (Exception exception) {
                                exception.printStackTrace();
                            }
                        }
                        ImageManager.sendUpdateMsg((String) object, nArray);
                        break;
                    }
                    case 3859: {
                        int n = dataInputStream.readInt();
                        int n64 = dataInputStream.readInt();
                        Param.getInstance().Recv_Task = n;
                        Param.getInstance().Recv_Total_Task = n64;
                        break;
                    }
                    case 23845: {
                        boolean bl = false;
                        int n = dataInputStream.readInt();
                        if (n > 0) {
                            Param.getInstance().Notice = new Vector();
                            int n65 = 0;
                            while (n65 < n) {
                                ChatContent chatContent = new ChatContent();
                                int n66 = dataInputStream.readInt();
                                String string = dataInputStream.readUTF();
                                int n67 = dataInputStream.readInt();
                                chatContent.imageID = n66;
                                chatContent.strsContent = string;
                                chatContent.fontColor = n67;
                                Param.getInstance().Notice.addElement(chatContent);
                                ++n65;
                            }
                            bl = true;
                            if (Param.getInstance().Notice != null && bl) {
                                GameControl.getInstance().delUIbase(4);
                                GameControl.getInstance().CreateState((byte) 7);
                                DialogUI.getInstance().setDialog((byte) -53, "活动", Param.getInstance().Notice, (byte) 3);
                            }
                            break;
                        }
                        NetSend.getInstance().sendOffLine();
                        break;
                    }
                    case 23846: {
                        String string = dataInputStream.readUTF();
                        FullInfo.getInstance().setColourContent(string);
                        FullInfo.getInstance().setButton(1, 100, 2);
                        break;
                    }
                    case 784: {
                        byte by = dataInputStream.readByte();
                        int n = dataInputStream.readShort();
                        if (n != 0) {
                            Param.getInstance().Tip_time = by;
                            Param.getInstance().Tip_Num = (short) n;
                            Param.getInstance().Tip_Content = new String[n];
                            int n68 = 0;
                            while (n68 < n) {
                                Param.getInstance().Tip_Content[n68] = dataInputStream.readUTF();
                                ++n68;
                            }
                        }
                        break;
                    }
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return s;
    }
}
