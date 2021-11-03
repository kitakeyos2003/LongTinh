// Decompiled with: Procyon 0.5.36
// Class Version: 1
package network;

import java.util.Enumeration;
import common.IResConfig;
import face.FullInfo;
import face.SystemMenuUI;
import means.RmsBase;
import face.FormDes;
import face.Peffect;
import means.Rms;
import means.DebugFrame;
import model.Task;
import java.util.Hashtable;
import model.ONpc;
import java.util.Vector;
import face.DialogUI;
import base.GameControl;
import model.ORole;
import base.DCanvas;
import model.ORPMe;
import model.ORPlayer;
import javax.microedition.lcdui.Image;
import means.ImageManager;
import model.Map;
import model.ORMonster;
import model.PackageObject;
import model.SkillObject;
import model.MakeObject;
import model.MenuObject;
import base.Param;
import face.GameUI;
import face.MenuUI;
import base.Macro;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;

public class NetParse2 {

    public short parsePacket(byte[] array) {
        short short1 = -1;
        Label_16957:
        {
            try {
                DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(array));
                short1 = dataInputStream.readShort();
                if (Macro.netRecvFollowDOS == short1) {
                    Macro.BLN_HTTP_TIME = false;
                    Macro.TIME_RESPONSE_CUT = System.currentTimeMillis();
                    String string = "R:" + Macro.netSendFollowDOS + ">" + Macro.netRecvFollowDOS + "=" + "T:" + (Macro.TIME_RESPONSE_CUT - Macro.TIME_START_CUT);
                    if (Macro.netUseInfoVector.size() > 5) {
                        Macro.netUseInfoVector.removeAllElements();
                    }
                    Macro.netUseInfoVector.addElement(string);
                }
                switch (short1) {
                    case 15366: {
                        int int1 = dataInputStream.readInt();
                        long long1 = dataInputStream.readLong();
                        long long2 = dataInputStream.readLong();
                        long long3 = dataInputStream.readLong();
                        dataInputStream.readUTF();
                        long currentTimeMillis = System.currentTimeMillis();
                        long n = currentTimeMillis - long3;
                        long n2 = currentTimeMillis - long1;
                        String string2 = String.valueOf(int1) + " .U:" + long2 + "/D:" + n + "/T:" + n2 + "ms\n";
                        Macro.tatalDefyTime += n2;
                        Macro.hNetList.put(new Integer(int1), string2);
                        ++Macro.missSum;
                        MenuUI.getInstance().scrolltext.keyDown();
                        break;
                    }
                    case 6659: {
                        byte byte1 = dataInputStream.readByte();
                        PackageObject meGoodsData = NetParse.getInstance().readMeGoodsData(dataInputStream, true);
                        if (GameUI.getInstance().checkMeMenuUi(byte1) && Param.getInstance().hPackage != null) {
                            NetParse.getInstance().clearTwoRect();
                            Param.getInstance().hPackage.remove(new Integer(meGoodsData.intPOindex));
                            GameUI.getInstance().addObjectImg(meGoodsData.shtIcon, "prop");
                            Param.getInstance().hPackage.put(new Integer(meGoodsData.intPOindex), meGoodsData);
                            break;
                        }
                        break;
                    }
                    case 6661: {
                        NetParse.getInstance().dapNpcRect();
                        break;
                    }
                    case 24082: {
                        byte byte2 = dataInputStream.readByte();
                        if (byte2 == 0) {
                            GameUI.getInstance().bytMakeSkill = 0;
                            break;
                        }
                        GameUI.getInstance().bytMakeSkill = byte2;
                        break;
                    }
                    case 6662: {
                        byte byte3 = dataInputStream.readByte();
                        byte byte4 = dataInputStream.readByte();
                        int int2 = dataInputStream.readInt();
                        if (Param.getInstance().intSelectId != Param.getInstance().oSelectNpc.intUserId) {
                            break;
                        }
                        MenuObject menuObject = (MenuObject) Param.getInstance().vMenuMemory.elementAt(Param.getInstance().vMenuMemory.size() - 1);
                        if (menuObject.bytStep != byte3) {
                            break;
                        }
                        NetParse.getInstance().clearTwoRect();
                        switch (menuObject.bytType) {
                            case 13: {
                                if (Param.getInstance().hPackage == null || Param.getInstance().hPackage.isEmpty() || byte4 != 1) {
                                    break Label_16957;
                                }
                                Param.getInstance().hPackage.remove(new Integer(int2));
                                if (Param.getInstance().hPackage.isEmpty()) {
                                    Param.getInstance().hPackage = null;
                                    MenuUI.getInstance().setButton(1, 100, 2);
                                    break Label_16957;
                                }
                                break Label_16957;
                            }
                            case 17: {
                                if (Param.getInstance().vCommonList == null) {
                                    break Label_16957;
                                }
                                if (byte4 != 0) {
                                    for (int i = 0; i < Param.getInstance().vCommonList.size(); i = (short) (i + 1)) {
                                        if (((MakeObject) Param.getInstance().vCommonList.elementAt(i)).intId == int2) {
                                            Param.getInstance().vCommonList.removeElementAt(i);
                                            MenuUI.getInstance().ifVectorNull();
                                            break;
                                        }
                                    }
                                    break Label_16957;
                                }
                                Param.getInstance().vCommonList.removeElementAt(int2);
                                if (MenuUI.getInstance().ifVectorNull()) {
                                    MenuUI.getInstance().shtMenuMoveLength = (short) Param.getInstance().vCommonList.size();
                                    MenuUI.getInstance().setChoiceMove(MenuUI.getInstance().shtMenuMoveLength, MenuUI.getInstance().getOneMove(), MenuUI.getInstance().bytWordMaxH);
                                    break Label_16957;
                                }
                                break Label_16957;
                            }
                            case -12: {
                                if (Param.getInstance().vCommonList == null) {
                                    break Label_16957;
                                }
                                if (byte4 == 0) {
                                    int intId = ((SkillObject) Param.getInstance().vCommonList.elementAt(int2)).intId;
                                    Param.getInstance().vCommonList.removeElementAt(int2);
                                    MenuUI.getInstance().ifVectorNull();
                                    int j = 0;
                                    while (j < MenuUI.getInstance().vTempVessel.size()) {
                                        if (intId == ((SkillObject) MenuUI.getInstance().vTempVessel.elementAt(j)).intId) {
                                            MenuUI.getInstance().vTempVessel.removeElementAt(j);
                                            if (MenuUI.getInstance().vTempVessel.isEmpty()) {
                                                MenuUI.getInstance().vTempVessel = null;
                                                MenuUI.getInstance().setButton(1, 100, 2);
                                                break;
                                            }
                                            MenuUI.getInstance().shtMenuMoveLength = (short) MenuUI.getInstance().vTempVessel.size();
                                            MenuUI.getInstance().setChoiceMove(MenuUI.getInstance().shtMenuMoveLength, MenuUI.getInstance().getOneMove(), MenuUI.getInstance().bytWordMaxH);
                                            break;
                                        } else {
                                            j = (byte) (j + 1);
                                        }
                                    }
                                    break Label_16957;
                                }
                                int k = 0;
                                while (k < Param.getInstance().vCommonList.size()) {
                                    if (((SkillObject) Param.getInstance().vCommonList.elementAt(k)).intId == int2) {
                                        Param.getInstance().vCommonList.removeElementAt(k);
                                        if (MenuUI.getInstance().ifVectorNull()) {
                                            MenuUI.getInstance().shtMenuMoveLength = (short) Param.getInstance().vCommonList.size();
                                            MenuUI.getInstance().setChoiceMove(MenuUI.getInstance().shtMenuMoveLength, MenuUI.getInstance().getOneMove(), MenuUI.getInstance().bytWordMaxH);
                                            break;
                                        }
                                        break;
                                    } else {
                                        k = (short) (k + 1);
                                    }
                                }
                                int l = 0;
                                while (l < MenuUI.getInstance().vTempVessel.size()) {
                                    if (((SkillObject) MenuUI.getInstance().vTempVessel.elementAt(l)).intId == int2) {
                                        MenuUI.getInstance().vTempVessel.removeElementAt(l);
                                        if (MenuUI.getInstance().vTempVessel.isEmpty()) {
                                            MenuUI.getInstance().vTempVessel = null;
                                            MenuUI.getInstance().setButton(1, 100, 2);
                                            break;
                                        }
                                        MenuUI.getInstance().shtMenuMoveLength = (short) MenuUI.getInstance().vTempVessel.size();
                                        MenuUI.getInstance().setChoiceMove(MenuUI.getInstance().shtMenuMoveLength, MenuUI.getInstance().getOneMove(), MenuUI.getInstance().bytWordMaxH);
                                        break;
                                    } else {
                                        l = (short) (l + 1);
                                    }
                                }
                                break Label_16957;
                            }
                            case 12: {
                                if (Param.getInstance().vCommonList == null) {
                                    break Label_16957;
                                }
                                if (byte4 != 0) {
                                    short n3 = (short) Param.getInstance().vCommonList.size();
                                    short n4 = 0;
                                    while (n4 < n3) {
                                        if (((SkillObject) Param.getInstance().vCommonList.elementAt(n4)).intId == int2) {
                                            Param.getInstance().vCommonList.removeElementAt(n4);
                                            if (MenuUI.getInstance().ifVectorNull()) {
                                                MenuUI.getInstance().shtMenuMoveLength = (short) Param.getInstance().vCommonList.size();
                                                MenuUI.getInstance().setChoiceMove(MenuUI.getInstance().shtMenuMoveLength, MenuUI.getInstance().getOneMove(), MenuUI.getInstance().bytWordMaxH);
                                                break;
                                            }
                                            break;
                                        } else {
                                            ++n4;
                                        }
                                    }
                                    break Label_16957;
                                }
                                Param.getInstance().vCommonList.removeElementAt(int2);
                                if (MenuUI.getInstance().ifVectorNull()) {
                                    MenuUI.getInstance().shtMenuMoveLength = (short) Param.getInstance().vCommonList.size();
                                    MenuUI.getInstance().setChoiceMove(MenuUI.getInstance().shtMenuMoveLength, MenuUI.getInstance().getOneMove(), MenuUI.getInstance().bytWordMaxH);
                                    break Label_16957;
                                }
                                break Label_16957;
                            }
                            default: {
                                if (Param.getInstance().vCommonList == null || Param.getInstance().vCommonList.isEmpty()) {
                                    break Label_16957;
                                }
                                if (byte4 != 0) {
                                    short n5 = (short) Param.getInstance().vCommonList.size();
                                    short n6 = 0;
                                    while (n6 < n5) {
                                        if (((PackageObject) Param.getInstance().vCommonList.elementAt(n6)).intId == int2) {
                                            Param.getInstance().vCommonList.removeElementAt(n6);
                                            if (MenuUI.getInstance().ifVectorNull()) {
                                                if (menuObject.bytType == 16) {
                                                    MenuUI.getInstance().setParcelPostButton(MenuUI.getInstance().getOneMove());
                                                }
                                                MenuUI.getInstance().shtMenuMoveLength = (short) Param.getInstance().vCommonList.size();
                                                MenuUI.getInstance().setChoiceMove(MenuUI.getInstance().shtMenuMoveLength, MenuUI.getInstance().getOneMove(), MenuUI.getInstance().bytWordMaxH);
                                                break;
                                            }
                                            break;
                                        } else {
                                            ++n6;
                                        }
                                    }
                                    break Label_16957;
                                }
                                Param.getInstance().vCommonList.removeElementAt(int2);
                                if (MenuUI.getInstance().ifVectorNull()) {
                                    MenuUI.getInstance().shtMenuMoveLength = (short) Param.getInstance().vCommonList.size();
                                    MenuUI.getInstance().setChoiceMove(MenuUI.getInstance().shtMenuMoveLength, MenuUI.getInstance().getOneMove(), MenuUI.getInstance().bytWordMaxH);
                                    break Label_16957;
                                }
                                break Label_16957;
                            }
                        }
                    }
                    case 11552: {
                        int int3 = dataInputStream.readInt();
                        String utf = dataInputStream.readUTF();
                        ORMonster orMonster = (ORMonster) Param.getInstance().htRoleMonster.get(new Integer(int3));
                        if (orMonster != null) {
                            orMonster.setAIChat(utf);
                            break;
                        }
                        break;
                    }
                    case 11570: {
                        if (dataInputStream.readShort() != Map.getInstance().shtMapId) {
                            break;
                        }
                        if (dataInputStream.readByte() == 1) {
                            ImageManager.getInstance().addMonsterImage(new short[]{dataInputStream.readShort(), dataInputStream.readShort(), 0, dataInputStream.readByte(), dataInputStream.readShort(), dataInputStream.readByte()});
                        }
                        int int4 = dataInputStream.readInt();
                        int int5 = dataInputStream.readInt();
                        int int6 = dataInputStream.readInt();
                        int int7 = dataInputStream.readInt();
                        int int8 = dataInputStream.readInt();
                        short short2 = dataInputStream.readShort();
                        dataInputStream.readShort();
                        ORMonster orMonster2 = (ORMonster) Param.getInstance().htRoleMonster.get(new Integer(int4));
                        if (orMonster2 != null) {
                            orMonster2.intHP = int5;
                            orMonster2.intHPMax = int6;
                            orMonster2.intMP = int7;
                            orMonster2.intMPMax = int8;
                            orMonster2.shtUpDataId = short2;
                            orMonster2.pushTask((byte) 3, (byte) 4);
                            break;
                        }
                        break;
                    }
                    case 11557: {
                        int int9 = dataInputStream.readInt();
                        byte byte5 = dataInputStream.readByte();
                        byte byte6 = dataInputStream.readByte();
                        ORMonster orMonster3 = (ORMonster) Param.getInstance().htRoleMonster.get(new Integer(int9));
                        if (orMonster3 != null) {
                            orMonster3.bytConcealX = byte5;
                            orMonster3.bytConcealY = byte6;
                            orMonster3.pushTask((byte) 3, (byte) 5);
                            break;
                        }
                        break;
                    }
                    case 11571: {
                        byte byte7 = dataInputStream.readByte();
                        byte byte8 = dataInputStream.readByte();
                        if (dataInputStream.readByte() == 1) {
                            Map.getInstance().creatRoleSkillArea(dataInputStream.readByte(), dataInputStream.readInt(), byte7, byte8, (short) 3000);
                            break;
                        }
                        Map.getInstance().creatSkillArea(dataInputStream.readByte(), dataInputStream.readByte(), byte7, byte8, (short) 3000, 1144385017);
                        break;
                    }
                    case 10769: {
                        byte byte9 = dataInputStream.readByte();
                        Map.getInstance().shtBuildingId = new short[byte9];
                        Map.getInstance().imgBuilding = new Image[byte9];
                        Map.getInstance().shtBuildingId = new short[byte9];
                        Map.getInstance().shtsBuildingWH = new short[byte9][2];
                        Map.getInstance().defaultBuilding = ImageManager.CreateImage(new StringBuffer(String.valueOf(Map.getInstance().shtDefaultBuildingId)).toString(), "building");
                        Map.getInstance().defaultBuildingWidth = (short) Map.getInstance().defaultBuilding.getWidth();
                        Map.getInstance().defaultBuildingHeight = (short) Map.getInstance().defaultBuilding.getHeight();
                        short n7 = 0;
                        for (byte b = 0; b < byte9; ++b) {
                            Map.getInstance().shtBuildingId[b] = dataInputStream.readShort();
                            Map.getInstance().imgBuilding[b] = Map.getInstance().defaultBuilding;
                            if (Map.getInstance().imgBuilding[b] != null) {
                                Map.getInstance().shtsBuildingWH[b][0] = Map.getInstance().defaultBuildingWidth;
                                Map.getInstance().shtsBuildingWH[b][1] = Map.getInstance().defaultBuildingHeight;
                                if (Map.getInstance().shtsBuildingWH[b][1] > n7) {
                                    n7 = Map.getInstance().shtsBuildingWH[b][1];
                                }
                            }
                        }
                        Map.getInstance().bytMaxDrawRow = (byte) (n7 / 16 + 1);
                        short[] array2 = new short[dataInputStream.readShort() * 3];
                        for (int n8 = 0; n8 < array2.length; ++n8) {
                            array2[n8] = dataInputStream.readShort();
                        }
                        Map.getInstance().setMapFloat(array2, byte9, Map.getInstance().shtBuildingId);
                        byte[] mapFloatTransform = new byte[dataInputStream.readShort()];
                        dataInputStream.read(mapFloatTransform);
                        Map.getInstance().setMapFloatTransform(mapFloatTransform);
                        break;
                    }
                    case 10770: {
                        short short3 = dataInputStream.readShort();
                        short short4 = dataInputStream.readShort();
                        if (short3 == Map.getInstance().shtMapId) {
                            Map.getInstance().setOutJumpPointAppear(short4);
                            break;
                        }
                        break;
                    }
                    case 1033: {
                        int int10 = dataInputStream.readInt();
                        byte byte10 = dataInputStream.readByte();
                        int int11 = dataInputStream.readInt();
                        int int12 = dataInputStream.readInt();
                        if (Param.getInstance().htRolePlayer == null) {
                            break;
                        }
                        ORPlayer orPlayer = (ORPlayer) Param.getInstance().htRolePlayer.get(new Integer(int10));
                        if (orPlayer == null) {
                            break;
                        }
                        orPlayer.bytOccupation = byte10;
                        orPlayer.intHPMax = int11;
                        orPlayer.intHP = int11;
                        if (orPlayer.blnUseMp) {
                            orPlayer.intMPMax = int12;
                        }
                        orPlayer.intMP = int12;
                        orPlayer.pushTask((byte) 3, (byte) 3);
                        if (orPlayer.intUserId == ORPMe.ME.intUserId) {
                            DCanvas.getInstance().addInformation("你已成为 " + ORole.getOccName(byte10), 16642234);
                            break;
                        }
                        break;
                    }
                    case 7446: {
                        PackageObject packageObject = new PackageObject();
                        packageObject.intId = dataInputStream.readShort();
                        packageObject.strName = dataInputStream.readUTF();
                        packageObject.bytQuality = dataInputStream.readByte();
                        packageObject.intColor = Macro.INT_PROP_COLOR[packageObject.bytQuality];
                        packageObject.shtPOnum = dataInputStream.readByte();
                        packageObject.shtIcon = dataInputStream.readShort();
                        packageObject.strInfo = dataInputStream.readUTF();
                        packageObject.byteEquipType = dataInputStream.readByte();
                        PackageObject packageObject2 = packageObject;
                        PackageObject packageObject3 = packageObject;
                        int int13 = dataInputStream.readInt();
                        packageObject3.intPOindex = int13;
                        packageObject2.intPrice = int13;
                        packageObject.shtLevel = dataInputStream.readShort();
                        if (DCanvas.getInstance().UMenu != null) {
                            GameControl.getInstance().delUIbase(MenuUI.getInstance().bytDelState);
                        }
                        NetParse.getInstance().addEquipDialog(packageObject);
                        break;
                    }
                    case 7682: {
                        String utf2 = dataInputStream.readUTF();
                        String utf3 = dataInputStream.readUTF();
                        int int14 = dataInputStream.readInt();
                        byte byte11 = dataInputStream.readByte();
                        if (byte11 == 1) {
                            DCanvas.getInstance().addInformation(String.valueOf(utf2) + utf3, int14);
                            break;
                        }
                        DCanvas.getInstance().addInformation(String.valueOf(utf2) + utf3 + "*" + byte11, int14);
                        break;
                    }
                    case 3601: {
                        String utf4 = dataInputStream.readUTF();
                        byte byte12 = dataInputStream.readByte();
                        byte byte13 = dataInputStream.readByte();
                        int int15 = dataInputStream.readInt();
                        int int16 = dataInputStream.readInt();
                        short short5 = dataInputStream.readShort();
                        if (ORPMe.ME.bytState == 5) {
                            Param.lngCountDownStartTime = System.currentTimeMillis();
                            Param.lngCountDownMaxTime = short5 * 1000;
                            Param.lngCountDownLeftTime = Param.lngCountDownMaxTime;
                            StringBuffer sb = new StringBuffer(String.valueOf(GameUI.getInstance().strReliveName));
                            GameUI.getInstance().getClass();
                            Param.strCountDownTxt = sb.append("想要复活你，是否接收？").toString();
                            GameUI.getInstance().strReliveName = utf4;
                            GameUI.getInstance().bytReliveX = byte12;
                            GameUI.getInstance().bytReliveY = byte13;
                            GameUI.getInstance().intReliveHp = int15;
                            GameUI.getInstance().intReliveMp = int16;
                            GameUI.getInstance().setDialog((byte) 6);
                            break;
                        }
                        break;
                    }
                    case 1040: {
                        int int17 = dataInputStream.readInt();
                        int int18 = dataInputStream.readInt();
                        int int19 = dataInputStream.readInt();
                        int int20 = dataInputStream.readInt();
                        int int21 = dataInputStream.readInt();
                        byte byte14 = dataInputStream.readByte();
                        byte byte15 = dataInputStream.readByte();
                        ORPlayer orPlayer2 = (ORPlayer) Param.getInstance().htRolePlayer.get(new Integer(int17));
                        if (orPlayer2 != null) {
                            if (orPlayer2.bytState == 5) {
                                orPlayer2.intHP = int18;
                                orPlayer2.intHPMax = int19;
                                orPlayer2.intMP = int20;
                                orPlayer2.intMPMax = int21;
                                orPlayer2.setTileXY(byte14, byte15);
                                if (orPlayer2.intUserId == ORPMe.ME.intUserId) {
                                    Map.getInstance().setCameraTileXY(ORPMe.ME.bytTileX, ORPMe.ME.bytTileY);
                                    GameUI.getInstance().strReliveName = null;
                                }
                                orPlayer2.blnRelive = true;
                            }
                            orPlayer2.intSkillDownLayerEffectID = -1;
                            break;
                        }
                        break;
                    }
                    case 24834: {
                        byte byte16 = dataInputStream.readByte();
                        if (byte16 == 1) {
                            int int22 = dataInputStream.readInt();
                            String utf5 = dataInputStream.readUTF();
                            Param.getInstance().intPkId = int22;
                            if (GameUI.getInstance().checkDialogPRI((byte) 13, (byte) 2)) {
                                DialogUI.getInstance().setDialog((byte) 13, String.valueOf(utf5) + "向你发出了决斗邀请，是否接受？", (byte) 2);
                                break;
                            }
                            break;
                        } else {
                            if (byte16 == 2) {
                                GameUI.getInstance().StartPK(dataInputStream.readInt(), dataInputStream.readByte(), dataInputStream.readByte(), dataInputStream.readShort());
                                break;
                            }
                            if (byte16 == 3) {
                                dataInputStream.readByte();
                                GameUI.getInstance().endPK(true);
                                break;
                            }
                            break;
                        }
                    }
                    case 10497: {
                        short short6 = dataInputStream.readShort();
                        String[][] array3 = null;
                        if (short6 > 0) {
                            array3 = new String[short6][3];
                            for (short n9 = 0; n9 < short6; ++n9) {
                                array3[n9][0] = dataInputStream.readUTF();
                                array3[n9][1] = "编号：" + dataInputStream.readInt() + dataInputStream.readUTF();
                            }
                        }
                        if (!GameUI.getInstance().checkMeMenuUi(-34)) {
                            break;
                        }
                        if (array3 == null) {
                            DCanvas.getInstance().addInformation("目前没有副本记录");
                            break;
                        }
                        Param.getInstance().vInfoContent = new Vector(1, 1);
                        for (short n10 = 0; n10 < short6; ++n10) {
                            Param.getInstance().vInfoContent.addElement(array3[n10]);
                        }
                        MenuUI.getInstance().setFBCD();
                        break;
                    }
                    case 10771: {
                        dataInputStream.readUTF();
                        if (DialogUI.getInstance() != null && DialogUI.getInstance().bytDialogState == 10) {
                            GameControl.getInstance().delUIbase(7);
                        }
                        if (DCanvas.getInstance().ULoading != null) {
                            GameControl.getInstance().delUIbase(6);
                            break;
                        }
                        break;
                    }
                    case 7476: {
                        byte byte17 = dataInputStream.readByte();
                        String utf6 = dataInputStream.readUTF();
                        GameControl.getInstance().CreateState((byte) 7);
                        if (byte17 == 0) {
                            DialogUI.getInstance().setDialog((byte) 113, utf6, (byte) 2);
                            break;
                        }
                        DialogUI.getInstance().setDialog((byte) 112, utf6, (byte) 2);
                        break;
                    }
                    case 7449: {
                        Param.getInstance().bytAccouterBag = dataInputStream.readByte();
                        Param.getInstance().bytLeechdomBag = dataInputStream.readByte();
                        Param.getInstance().bytStuffBag = dataInputStream.readByte();
                        Param.getInstance().bytEspecialBag = dataInputStream.readByte();
                        if (GameUI.getInstance().checkMeMenuUi(10)) {
                            Param.getInstance().packageBoxMaxNum = Param.getInstance().bytAccouterBag;
                            if (DCanvas.getInstance().UMenu != null && Param.getInstance().hPackage == null && Param.getInstance().packageBoxMaxNum >= 40) {
                                MenuUI.getInstance().setButton(1, 100, 2);
                                break;
                            }
                            break;
                        } else if (GameUI.getInstance().checkMeMenuUi(36)) {
                            Param.getInstance().packageBoxMaxNum = Param.getInstance().bytLeechdomBag;
                            if (DCanvas.getInstance().UMenu != null && Param.getInstance().hPackage == null && Param.getInstance().packageBoxMaxNum >= 40) {
                                MenuUI.getInstance().setButton(1, 100, 2);
                                break;
                            }
                            break;
                        } else if (GameUI.getInstance().checkMeMenuUi(37)) {
                            Param.getInstance().packageBoxMaxNum = Param.getInstance().bytStuffBag;
                            if (DCanvas.getInstance().UMenu != null && Param.getInstance().hPackage == null && Param.getInstance().packageBoxMaxNum >= 40) {
                                MenuUI.getInstance().setButton(1, 100, 2);
                                break;
                            }
                            break;
                        } else {
                            if (!GameUI.getInstance().checkMeMenuUi(39)) {
                                break;
                            }
                            Param.getInstance().packageBoxMaxNum = Param.getInstance().bytEspecialBag;
                            if (DCanvas.getInstance().UMenu != null && Param.getInstance().hPackage == null && Param.getInstance().packageBoxMaxNum >= 40) {
                                MenuUI.getInstance().setButton(1, 100, 2);
                                break;
                            }
                            break;
                        }
                    }
                    case 10765: {
                        byte byte18 = dataInputStream.readByte();
                        if (byte18 > 0) {
                            short initCreateNpc = GameControl.getInstance().initCreateNpc(byte18);
                            for (byte b2 = 0; b2 < byte18; ++b2) {
                                ImageManager.getInstance().addNpcImage(new short[]{dataInputStream.readShort(), dataInputStream.readShort(), 0, dataInputStream.readByte(), dataInputStream.readShort(), dataInputStream.readByte()});
                            }
                            for (byte byte19 = dataInputStream.readByte(), b3 = 0; b3 < byte19; ++b3) {
                                ONpc oNpc = new ONpc();
                                int int23 = dataInputStream.readInt();
                                byte byte20 = dataInputStream.readByte();
                                byte byte21 = dataInputStream.readByte();
                                oNpc.newPlayer(int23, byte20, byte21);
                                oNpc.intUserId = int23;
                                oNpc.strTALK = dataInputStream.readUTF();
                                oNpc.setRoleFrame((short) oNpc.intUserId, (short) int23, (short) int23, false);
                                oNpc.blnIsTalk = true;
                                oNpc.blnNpc = false;
                                oNpc.strNickName = "路牌";
                                Map.getInstance().bytsNpcXY[initCreateNpc + b3][0] = byte21;
                                Map.getInstance().bytsNpcXY[initCreateNpc + b3][1] = byte20;
                                GameUI.getInstance().addObject(oNpc);
                            }
                        }
                        byte byte22 = dataInputStream.readByte();
                        if (byte22 > 0) {
                            if (Param.getInstance().htTask == null) {
                                Param.getInstance().htTask = new Hashtable();
                                Map.getInstance().bytsTaskXY = new byte[Map.getInstance().bytRow][Map.getInstance().bytColumn];
                            }
                            for (byte b4 = 0; b4 < byte22; ++b4) {
                                Task task = new Task();
                                task.intUserId = dataInputStream.readInt();
                                task.bytTaskType = 2;
                                byte byte23 = dataInputStream.readByte();
                                byte byte24 = dataInputStream.readByte();
                                task.strNickName = dataInputStream.readUTF();
                                task.newTask(byte23, byte24);
                                task.setTaskPic((short) 1);
                                GameUI.getInstance().addObject(task);
                            }
                        }
                        for (int n11 = 0; n11 < 2; n11 = (byte) (n11 + 1)) {
                            DebugFrame.getInstance().logIn((n11 == 0) ? "任务机关" : "任务物品");
                            byte byte25 = dataInputStream.readByte();
                            if (byte25 > 0) {
                                for (byte b5 = 0; b5 < byte25; ++b5) {
                                    short[] array4 = new short[6];
                                    short short7 = dataInputStream.readShort();
                                    short short8 = dataInputStream.readShort();
                                    short n12 = short7;
                                    byte byte26 = dataInputStream.readByte();
                                    short short9 = dataInputStream.readShort();
                                    byte byte27 = dataInputStream.readByte();
                                    array4[0] = n12;
                                    array4[1] = short7;
                                    array4[2] = short8;
                                    array4[3] = byte26;
                                    array4[4] = short9;
                                    array4[5] = byte27;
                                    DebugFrame.getInstance().logIn("pic/anu = " + short7 + "/" + short8);
                                    ImageManager.getInstance().addTaskImage(array4);
                                }
                                for (byte byte28 = dataInputStream.readByte(), b6 = 0; b6 < byte28; ++b6) {
                                    NetParse.getInstance().readTaskData(dataInputStream, n11 == 0);
                                }
                            }
                        }
                        break;
                    }
                    case 10772: {
                        if (Rms.bytEffect != 0 || !Rms.blnWeather) {
                            break;
                        }
                        Param.getInstance().peffect = null;
                        byte byte29 = dataInputStream.readByte();
                        byte byte30 = dataInputStream.readByte();
                        if (!Rms.blnWeather) {
                            break;
                        }
                        if (byte29 != 0) {
                            if (Param.getInstance().peffect == null) {
                                Param.getInstance().peffect = new Peffect();
                            }
                            Param.getInstance().peffect.setPeffect(byte29, byte30);
                            break;
                        }
                        if (Param.getInstance().peffect != null) {
                            Param.getInstance().peffect.setIsOver();
                            break;
                        }
                        break;
                    }
                    case 7456: {
                        PackageObject packageObject4 = (PackageObject) Param.getInstance().HSInlay.get(new Integer(Param.getInstance().InlayIndex));
                        if (dataInputStream.readByte() == 1) {
                            Param.getInstance().hPackage.remove(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()));
                            GameUI.getInstance().checkEquipStamina();
                            if (GameUI.getInstance().checkMeMenuUi(10)) {
                                MenuUI.getInstance().setInfoContent(Param.getInstance().hPackageEquip, MenuUI.getInstance().bytRoleMove);
                            }
                        }
                        dataInputStream.readInt();
                        dataInputStream.readByte();
                        dataInputStream.readShort();
                        dataInputStream.readShort();
                        packageObject4.DescribeProperty = dataInputStream.readUTF();
                        byte byte31 = dataInputStream.readByte();
                        if ((packageObject4.holeNum = byte31) > 0) {
                            packageObject4.holeDate = new byte[byte31];
                            for (byte b7 = 0; b7 < byte31; ++b7) {
                                packageObject4.holeDate[b7] = dataInputStream.readByte();
                            }
                        }
                        if (true && Param.getInstance().RightDate) {
                            if (MenuUI.getInstance().getState() == 11) {
                                MenuUI.getInstance().inlayHole.setPopupShow();
                            }
                            Param.getInstance().RightDate = false;
                            break;
                        }
                        break;
                    }
                    case 7577: {
                        Param.getInstance();
                        Param.ASKING_ONCE = false;
                        byte byte32 = dataInputStream.readByte();
                        Param.getInstance().bitHoleTime = byte32;
                        Param.getInstance().EachHolePrice = new int[byte32];
                        for (byte b8 = 0; b8 < byte32; ++b8) {
                            Param.getInstance().EachHolePrice[b8] = dataInputStream.readInt();
                        }
                        byte byte33 = dataInputStream.readByte();
                        Param.getInstance().InlayTime = byte33;
                        Param.getInstance().EachInlayPrice = new int[byte33];
                        for (byte b9 = 0; b9 < byte33; ++b9) {
                            Param.getInstance().EachInlayPrice[b9] = dataInputStream.readInt();
                        }
                        byte byte34 = dataInputStream.readByte();
                        Param.getInstance().RemoveTime = byte34;
                        Param.getInstance().EachRemovePirce = new int[byte34];
                        for (byte b10 = 0; b10 < byte34; ++b10) {
                            Param.getInstance().EachRemovePirce[b10] = dataInputStream.readInt();
                        }
                        Param.getInstance();
                        Param.ASKING_ONCE = true;
                        Param.getInstance().describeText = dataInputStream.readUTF();
                        break;
                    }
                    case 7475: {
                        if (dataInputStream.readByte() == 1) {
                            String strName = ((PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()))).strName;
                            int n13 = Param.getInstance().EachInlayPrice[MenuUI.getInstance().inlayHole.getCellBoxIndex()];
                            GameControl.getInstance().CreateState((byte) 7);
                            DialogUI.getInstance().setDialog((byte) 101, "使用" + strName + "宝石，花费" + n13 + "金钱进行镶嵌，确定继续吗？", (byte) 2);
                            break;
                        }
                        break;
                    }
                    case 7457: {
                        Param.getInstance().intCrystalId = dataInputStream.readInt();
                        Param.getInstance().bytCrystalPlace = dataInputStream.readByte();
                        NetParse.getInstance().clearTwoRect();
                        if (DCanvas.getInstance().UMenu == null) {
                            GameControl.getInstance().CreateState((byte) 3);
                        }
                        Param.getInstance().blnCrystal = false;
                        MenuUI.getInstance().setState((byte) (-5), "强化");
                        break;
                    }
                    case 7458: {
                        int int24 = dataInputStream.readInt();
                        PackageObject packageObject5 = (PackageObject) Param.getInstance().hPackageEquip.get(new Integer(4));
                        if (packageObject5.intId == int24) {
                            StringBuffer sb2 = new StringBuffer();
                            int int25 = dataInputStream.readInt();
                            int int26 = dataInputStream.readInt();
                            byte byte35 = dataInputStream.readByte();
                            sb2.append("首领击杀：");
                            sb2.append(int25);
                            sb2.append("/");
                            sb2.append(int26);
                            if (byte35 > 0) {
                                String utf7 = dataInputStream.readUTF();
                                sb2.append("\n");
                                sb2.append(utf7);
                            }
                            packageObject5.strPveNumber = sb2.toString();
                            if (packageObject5.bytsAggrandizement[0] != byte35) {
                                DCanvas.getInstance().addInformation("屠魔等级提升到了 " + byte35 + " 级", 16642234);
                            }
                            packageObject5.bytsAggrandizement[0] = byte35;
                            StringBuffer sb3 = new StringBuffer();
                            int int27 = dataInputStream.readInt();
                            int int28 = dataInputStream.readInt();
                            byte byte36 = dataInputStream.readByte();
                            sb3.append("氏族击杀：");
                            sb3.append(int27);
                            sb3.append("/");
                            sb3.append(int28);
                            if (byte36 > 0) {
                                String utf8 = dataInputStream.readUTF();
                                sb3.append("\n");
                                sb3.append(utf8);
                            }
                            packageObject5.strPvpNumber = sb3.toString();
                            if (((PackageObject) Param.getInstance().hPackageEquip.get(new Integer(4))).bytsAggrandizement[1] != byte36) {
                                DCanvas.getInstance().addInformation("杀戮等级提升到了 " + byte36 + " 级", 16642234);
                            }
                            packageObject5.bytsAggrandizement[1] = byte36;
                            break;
                        }
                        break;
                    }
                    case 755: {
                        String utf9 = dataInputStream.readUTF();
                        String utf10 = dataInputStream.readUTF();
                        Macro.strVersionURL = dataInputStream.readUTF();
                        if (Macro.VERSION.compareTo(utf10) >= 0) {
                            String string3 = "有新版本可下载，版本号：" + utf9;
                            GameControl.getInstance().CreateState((byte) 7);
                            DialogUI.getInstance().setDialog((byte) 120, string3, (byte) 2);
                            break;
                        }
                        String string4 = "下载新版本后方可以继续游戏，最新版本号：" + utf9;
                        GameControl.getInstance().CreateState((byte) 7);
                        DialogUI.getInstance().setDialog((byte) 110, string4, (byte) 2);
                        break;
                    }
                    case 756: {
                        String utf11 = dataInputStream.readUTF();
                        String utf12 = dataInputStream.readUTF();
                        Rms.saveUseCode(true);
                        Rms.saveID(utf11, utf12, Rms.blnIsSaveLandInfo);
                        break;
                    }
                    case 5121: {
                        String utf13 = dataInputStream.readUTF();
                        Param.getInstance().blnIsGameClose = true;
                        NetManager.getInstance().stopNetManager(NetManager.getInstance().MC);
                        if (Macro.bytGameType != 1) {
                            GameControl.getInstance().CreateState((byte) 7);
                            DialogUI.getInstance().setDialog((byte) 7, "已从登陆服务器断开", (byte) 2);
                            break;
                        }
                        if (FormDes.getInstance() != null) {
                            FormDes.getInstance().clean();
                        }
                        GameControl.getInstance().CreateState((byte) 7);
                        DialogUI.getInstance().setDialog((byte) 7, utf13, (byte) 2);
                        break;
                    }
                    case 7459: {
                        byte byte37 = dataInputStream.readByte();
                        int int29 = dataInputStream.readInt();
                        DCanvas.getInstance().addInformation("古老的封印逐渐散去");
                        if (DCanvas.getInstance().UMenu != null && Param.getInstance().hPackage != null && Param.getInstance().hPackage.containsKey(new Integer(byte37)) && ((PackageObject) Param.getInstance().hPackage.get(new Integer(byte37))).intId == int29) {
                            ((PackageObject) Param.getInstance().hPackage.get(new Integer(byte37))).bytSeal = 0;
                            MenuUI.getInstance().setInfoContent(Param.getInstance().hPackage, MenuUI.getInstance().getPropRectMove());
                            break;
                        }
                        break;
                    }
                    case 7460: {
                        byte byte38 = dataInputStream.readByte();
                        FormDes.getInstance().showForm((byte) 44);
                        FormDes.getInstance().bytClarionPlace = byte38;
                        break;
                    }
                    case 14850: {
                        int int30 = dataInputStream.readInt();
                        byte byte39 = dataInputStream.readByte();
                        if (Param.getInstance().htRolePlayer == null) {
                            break;
                        }
                        ORPlayer orPlayer3 = (ORPlayer) Param.getInstance().htRolePlayer.get(new Integer(int30));
                        if (orPlayer3 == null) {
                            break;
                        }
                        if (byte39 == 2) {
                            dataInputStream.readShort();
                            dataInputStream.readByte();
                            break;
                        }
                        orPlayer3.delPet();
                        break;
                    }
                    case 15105: {
                        GameUI.getInstance().setOnlineTime(dataInputStream.readLong(), dataInputStream.readLong(), dataInputStream.readLong());
                        break;
                    }
                    case 24083: {
                        GameUI.getInstance().intNowSkilful = dataInputStream.readInt();
                        break;
                    }
                    case 7461: {
                        byte byte40 = dataInputStream.readByte();
                        byte byte41 = dataInputStream.readByte();
                        Param.getInstance().bytCrystalType = byte40;
                        Param.getInstance().bytCrystalPlace = byte41;
                        GameUI.getInstance().setDialog((byte) 16);
                        break;
                    }
                    case 23825: {
                        dataInputStream.readByte();
                        if (dataInputStream.readByte() == 0) {
                            GameUI.getInstance().delRoleStateIcon((short) 1);
                            GameUI.getInstance().blnNewMail = false;
                            break;
                        }
                        GameUI.getInstance().pushRoleStateIcon((short) 1);
                        GameUI.getInstance().blnNewMail = true;
                        break;
                    }
                    case 14851: {
                        byte b11 = Macro.PET_CARRY = dataInputStream.readByte();
                        Hashtable hPackage = new Hashtable();
                        for (byte intPOindex = 0; intPOindex < b11; ++intPOindex) {
                            PackageObject packageObject6 = new PackageObject();
                            packageObject6.intPOindex = intPOindex;
                            int int31 = dataInputStream.readInt();
                            short short10 = dataInputStream.readShort();
                            packageObject6.shtStage = short10;
                            Macro.PET_STATGE = short10;
                            dataInputStream.readShort();
                            short short11 = dataInputStream.readShort();
                            packageObject6.shtType = short11;
                            Macro.PET_TYPE = short11;
                            dataInputStream.readByte();
                            packageObject6.petKey = int31;
                            packageObject6.shtIcon = dataInputStream.readShort();
                            GameUI.getInstance().addObjectImg(packageObject6.shtIcon, "prop");
                            packageObject6.shtPngId = dataInputStream.readShort();
                            packageObject6.shtAnuId = dataInputStream.readShort();
                            packageObject6.strName = dataInputStream.readUTF();
                            packageObject6.value = dataInputStream.readInt();
                            DebugFrame.getInstance().logIn("_po.value:" + packageObject6.value);
                            hPackage.put(new Integer(packageObject6.intPOindex), packageObject6);
                        }
                        if (b11 > 0) {
                            Param.getInstance().bytPetSaleType = (byte) (dataInputStream.readByte() - 1);
                        } else {
                            DCanvas.getInstance().addInformation("没有宠物");
                        }
                        if (GameUI.getInstance().checkMeMenuUi(33)) {
                            Param.getInstance().hPackage = hPackage;
                            MenuUI.getInstance().setPetList();
                            break;
                        }
                        Param.getInstance().hImgObject = null;
                        break;
                    }
                    case 14852: {
                        int int32 = dataInputStream.readInt();
                        dataInputStream.readInt();
                        dataInputStream.readByte();
                        dataInputStream.readShort();
                        dataInputStream.readShort();
                        short short12 = dataInputStream.readShort();
                        short short13 = dataInputStream.readShort();
                        dataInputStream.readByte();
                        dataInputStream.readUTF();
                        dataInputStream.readInt();
                        short short14 = dataInputStream.readShort();
                        short short15 = dataInputStream.readShort();
                        DebugFrame.getInstance().logIn("_petStage:" + short15);
                        if (short15 == 1) {
                            short12 = -1;
                        }
                        ORPMe.ME.setPet(int32, short12, short13, short14);
                        break;
                    }
                    case 14854: {
                        dataInputStream.readInt();
                        dataInputStream.readInt();
                        DebugFrame.getInstance().logIn(new StringBuffer().append(dataInputStream.readByte()).toString());
                        break;
                    }
                    case 14856: {
                        dataInputStream.readInt();
                        dataInputStream.readInt();
                        dataInputStream.readByte();
                        dataInputStream.readUTF();
                        break;
                    }
                    case 14858: {
                        DebugFrame.getInstance().logIn("-----------宠物饲养值-----------");
                        PackageObject packageObject7 = new PackageObject();
                        int int33 = dataInputStream.readInt();
                        DebugFrame.getInstance().logIn("玩家ID：" + int33);
                        ORole oRole = null;
                        if (Param.getInstance().htRolePlayer != null) {
                            oRole = (ORole) Param.getInstance().htRolePlayer.get(new Integer(int33));
                        }
                        int int34 = dataInputStream.readInt();
                        packageObject7.petKey = int34;
                        DebugFrame.getInstance().logIn("要喂养的宠物ID：" + int34);
                        byte byte42 = dataInputStream.readByte();
                        packageObject7.petDead = byte42;
                        DebugFrame.getInstance().logIn("是否死亡 (1:活着  0:死亡) 死亡后宠物不再显示：" + byte42);
                        DebugFrame.getInstance().logIn("宠物表情id：" + dataInputStream.readByte());
                        int int35 = dataInputStream.readInt();
                        packageObject7.value = int35;
                        DebugFrame.getInstance().logIn("饲养值：" + int35);
                        if ((packageObject7.shtStage = dataInputStream.readShort()) == 2) {
                            Macro.PET_PHYACT_VALUE = dataInputStream.readInt();
                            Macro.PET_SPEED_VALUE = dataInputStream.readInt();
                        }
                        if (oRole != null) {
                            ORPlayer.itSelectPetId = int34;
                            oRole.hPackagePet.put(new Integer(packageObject7.intPOindex), packageObject7);
                            break;
                        }
                        break;
                    }
                    case 14861: {
                        dataInputStream.readInt();
                        dataInputStream.readInt();
                        dataInputStream.readShort();
                        dataInputStream.readShort();
                        dataInputStream.readByte();
                        dataInputStream.readInt();
                        break;
                    }
                    case 14860: {
                        DebugFrame.getInstance().logIn("--------宠物经验--------");
                        DebugFrame.getInstance().logIn("玩家ID：" + dataInputStream.readInt());
                        DebugFrame.getInstance().logIn("宠物ID：" + dataInputStream.readInt());
                        DebugFrame.getInstance().logIn("成长点" + (Macro.PET_GROW_POINTS = dataInputStream.readInt()));
                        DebugFrame.getInstance().logIn("战斗经验：" + (Macro.PET_ACT_POINTS = dataInputStream.readInt()));
                        break;
                    }
                    case 14863: {
                        DebugFrame.getInstance().logIn("--------宠物点数---------");
                        DebugFrame.getInstance().logIn("玩家ID：" + dataInputStream.readInt());
                        DebugFrame.getInstance().logIn("宠物ID：" + dataInputStream.readInt());
                        DebugFrame.getInstance().logIn("能力槽编号:" + dataInputStream.readByte());
                        DebugFrame.getInstance().logIn("给该能力槽分配进化点后的点数：" + dataInputStream.readInt());
                        break;
                    }
                    case 14864: {
                        dataInputStream.readInt();
                        dataInputStream.readInt();
                        dataInputStream.readByte();
                        dataInputStream.readInt();
                        dataInputStream.readInt();
                        break;
                    }
                    case 14866: {
                        dataInputStream.readInt();
                        Macro.PET_LEVEL = dataInputStream.readInt();
                        Macro.PET_MAQIC_POINTS = dataInputStream.readInt();
                        Macro.PET_PHY_VALUE = dataInputStream.readInt();
                        Macro.PET_QUICK_VALUE = dataInputStream.readInt();
                        Macro.PET_SPIRIT_VALUE = dataInputStream.readInt();
                        Macro.PET_WIT_VALUE = dataInputStream.readInt();
                        DebugFrame.getInstance().logIn("宠物幸运值" + (Macro.PET_GOODLUCK_VALUE = dataInputStream.readInt()));
                        break;
                    }
                    case 14865: {
                        dataInputStream.readInt();
                        dataInputStream.readInt();
                        dataInputStream.readInt();
                        Macro.PET_PHY_VALUE = dataInputStream.readInt();
                        Macro.PET_QUICK_VALUE = dataInputStream.readInt();
                        Macro.PET_SPIRIT_VALUE = dataInputStream.readInt();
                        Macro.PET_WIT_VALUE = dataInputStream.readInt();
                        Macro.PET_GOODLUCK_VALUE = dataInputStream.readInt();
                        dataInputStream.readShort();
                        dataInputStream.readByte();
                        Macro.PET_TYPE = dataInputStream.readShort();
                        dataInputStream.readUTF();
                        Macro.PET_LEVEL = dataInputStream.readInt();
                        dataInputStream.readInt();
                        Macro.PET_ACT_POINTS = dataInputStream.readInt();
                        Macro.PET_WISDOM_VALUE = dataInputStream.readInt();
                        Macro.PET_LOYAL_VALUE = dataInputStream.readInt();
                        Macro.PET_ANGER_VALUE = dataInputStream.readInt();
                        Macro.PET_PHYACT_VALUE = dataInputStream.readInt();
                        Macro.PET_MAQICACT_VALUE = dataInputStream.readInt();
                        Macro.PET_SPEED_VALUE = dataInputStream.readInt();
                        Macro.PET_HIT_VALUE = dataInputStream.readInt();
                        Macro.PET_CRIT_VALUE = dataInputStream.readInt();
                        dataInputStream.readInt();
                        break;
                    }
                    case 7463: {
                        PackageObject packageObject8 = (PackageObject) Param.getInstance().hPetPackEquip.get(new Integer(4));
                        dataInputStream.readInt();
                        Macro.PET_TPYE_EQUIP = dataInputStream.readByte();
                        dataInputStream.readShort();
                        break;
                    }
                    case 7464: {
                        dataInputStream.readByte();
                        dataInputStream.readUTF();
                        Param.getInstance().packageBoxMaxNum = dataInputStream.readByte();
                        short n14 = Macro.PET_EQUIP_NUM = dataInputStream.readByte();
                        Hashtable hPackage2 = new Hashtable();
                        for (short n15 = 0; n15 < n14; ++n15) {
                            PackageObject packageObject9 = new PackageObject();
                            packageObject9.shtPOnum = 1;
                            packageObject9.intPOindex = dataInputStream.readByte();
                            packageObject9.intId = dataInputStream.readInt();
                            packageObject9.shtIcon = dataInputStream.readShort();
                            packageObject9.strName = dataInputStream.readUTF();
                            dataInputStream.readByte();
                            int int36 = dataInputStream.readInt();
                            byte[] array5 = new byte[int36];
                            for (int n16 = 0; n16 < int36; ++n16) {
                                array5[n16] = dataInputStream.readByte();
                            }
                            dataInputStream.readByte();
                            dataInputStream.readInt();
                            GameUI.getInstance().addObjectImg(packageObject9.shtIcon, "prop");
                            hPackage2.put(new Integer(packageObject9.intPOindex), packageObject9);
                        }
                        if (GameUI.getInstance().checkMeMenuUi(58)) {
                            Param.getInstance().hPackage = hPackage2;
                            MenuUI.getInstance().setPetPack();
                            break;
                        }
                        Param.getInstance().hImgObject = null;
                        break;
                    }
                    case 14868: {
                        DebugFrame.getInstance().logIn("-------宠物技能列表--------");
                        if (Param.getInstance().htPetSkillList == null) {
                            Param.getInstance().htPetSkillList = new Hashtable();
                        }
                        int int37 = dataInputStream.readInt();
                        DebugFrame.getInstance().logIn("cid" + int37);
                        Vector vector = new Vector();
                        Param.getInstance().htPetSkillList.put(new Integer(int37), vector);
                        int n17 = Macro.petskillcount = dataInputStream.readInt();
                        Macro.petskill = new int[n17];
                        DebugFrame.getInstance().logIn("zzzz主动技能数量zzzz:" + n17);
                        for (int n18 = 0; n18 < n17; ++n18) {
                            SkillObject skillObject = new SkillObject();
                            skillObject.intId = dataInputStream.readInt();
                            skillObject.strName = dataInputStream.readUTF();
                            skillObject.bytLv = (byte) dataInputStream.readInt();
                            skillObject.shtIcon = dataInputStream.readShort();
                            skillObject.blnPassivity = (dataInputStream.readByte() != 1);
                            skillObject.strInfo = dataInputStream.readUTF();
                            skillObject.intCDTimeMax = dataInputStream.readInt();
                            skillObject.intCDTime = dataInputStream.readInt();
                            skillObject.bytSelect = dataInputStream.readByte();
                            skillObject.bytUserType = dataInputStream.readByte();
                            skillObject.bytAtkDistance = dataInputStream.readByte();
                            DebugFrame.getInstance().logIn("Sát thương phép:" + dataInputStream.readInt());
                            DebugFrame.getInstance().logIn("Sát thương vật lý:" + dataInputStream.readInt());
                            DebugFrame.getInstance().logIn("Phạm vi ảnh hưởng:" + dataInputStream.readByte());
                            DebugFrame.getInstance().logIn("Hồi phục HP:" + dataInputStream.readInt());
                            DebugFrame.getInstance().logIn("Hồi phục MP:" + dataInputStream.readInt());
                            DebugFrame.getInstance().logIn("Tiêu hao MP:" + dataInputStream.readInt());
                            DebugFrame.getInstance().logIn("Sát thương phép:" + dataInputStream.readUTF());
                            skillObject.bytSkillPicId = dataInputStream.readShort();
                            skillObject.bytSkillAnuId = dataInputStream.readShort();
                            vector.addElement(skillObject);
                        }
                        int int38 = dataInputStream.readInt();
                        DebugFrame.getInstance().logIn("bbbbb宠物被动技能数量bbbbb:" + int38);
                        for (int n19 = 0; n19 < int38; ++n19) {
                            SkillObject skillObject2 = new SkillObject();
                            skillObject2.intId = dataInputStream.readInt();
                            skillObject2.strName = dataInputStream.readUTF();
                            skillObject2.bytLv = (byte) dataInputStream.readInt();
                            skillObject2.shtIcon = dataInputStream.readShort();
                            skillObject2.strInfo = dataInputStream.readUTF();
                            vector.addElement(skillObject2);
                        }
                        break;
                    }
                    case 14870: {
                        dataInputStream.readByte();
                        byte byte43 = dataInputStream.readByte();
                        Hashtable hPackage3 = new Hashtable();
                        for (byte b12 = 0; b12 < byte43; ++b12) {
                            PackageObject packageObject10 = new PackageObject();
                            packageObject10.intPOindex = dataInputStream.readByte();
                            packageObject10.petKey = dataInputStream.readInt();
                            packageObject10.shtStage = dataInputStream.readShort();
                            dataInputStream.readShort();
                            packageObject10.shtType = dataInputStream.readShort();
                            dataInputStream.readByte();
                            packageObject10.shtIcon = dataInputStream.readShort();
                            packageObject10.strName = dataInputStream.readUTF();
                            int int39 = dataInputStream.readInt();
                            packageObject10.value = int39;
                            DebugFrame.getInstance().logIn("包裹里面的饲养殖:" + int39);
                            packageObject10.shtPngId = dataInputStream.readShort();
                            packageObject10.shtAnuId = dataInputStream.readShort();
                            GameUI.getInstance().addObjectImg(packageObject10.shtIcon, "prop");
                            hPackage3.put(new Integer(packageObject10.intPOindex), packageObject10);
                        }
                        Param.getInstance().hPackage = hPackage3;
                        if (GameUI.getInstance().checkMeMenuUi(33)) {
                            MenuUI.getInstance().setEquipImage(Param.getInstance().hPackage);
                            break;
                        }
                        break;
                    }
                    case 14871: {
                        DebugFrame.getInstance().logIn("-------------更新人物宠物装备------------");
                        dataInputStream.readByte();
                        byte byte44 = dataInputStream.readByte();
                        Param.petValue = new int[2][4];
                        Hashtable hPackagePet = new Hashtable();
                        for (byte b13 = 0; b13 < byte44; ++b13) {
                            PackageObject packageObject11 = new PackageObject();
                            byte byte45 = dataInputStream.readByte();
                            packageObject11.intPOindex = byte45;
                            int int40 = dataInputStream.readInt();
                            packageObject11.petKey = int40;
                            short short16 = dataInputStream.readShort();
                            packageObject11.shtStage = short16;
                            dataInputStream.readShort();
                            packageObject11.shtType = dataInputStream.readShort();
                            dataInputStream.readByte();
                            packageObject11.shtIcon = dataInputStream.readShort();
                            packageObject11.strName = dataInputStream.readUTF();
                            int int41 = dataInputStream.readInt();
                            packageObject11.value = int41;
                            packageObject11.shtPngId = dataInputStream.readShort();
                            packageObject11.shtAnuId = dataInputStream.readShort();
                            if (byte45 == 8) {
                                Param.petValue[0][0] = int40;
                                Param.petValue[0][1] = int41;
                                Param.petValue[0][3] = short16;
                            }
                            GameUI.getInstance().addObjectImg(packageObject11.shtIcon, "prop");
                            hPackagePet.put(new Integer(packageObject11.intPOindex), packageObject11);
                        }
                        ORPMe.ME.hPackagePet = hPackagePet;
                        MenuUI.getInstance().updatePetList();
                        break;
                    }
                    case 14867: {
                        if (Param.getInstance().htPetSkillList == null) {
                            Param.getInstance().htPetSkillList = new Hashtable();
                        }
                        int int42 = dataInputStream.readInt();
                        Vector vector2;
                        if (Param.getInstance().htPetSkillList.contains(new Integer(int42))) {
                            vector2 = (Vector) Param.getInstance().htPetSkillList.get(new Integer(int42));
                        } else {
                            vector2 = new Vector();
                            Param.getInstance().htPetSkillList.put(new Integer(int42), vector2);
                        }
                        for (int int43 = dataInputStream.readInt(), n20 = 0; n20 < int43; ++n20) {
                            SkillObject skillObject3 = new SkillObject();
                            skillObject3.intId = dataInputStream.readInt();
                            skillObject3.strName = dataInputStream.readUTF();
                            skillObject3.bytLv = (byte) dataInputStream.readInt();
                            skillObject3.shtIcon = dataInputStream.readShort();
                            skillObject3.bytSkillPicId = dataInputStream.readShort();
                            skillObject3.bytSkillAnuId = dataInputStream.readShort();
                            vector2.addElement(skillObject3);
                        }
                        break;
                    }
                    case 14869: {
                        DebugFrame.getInstance().logIn("---------宠物控制--------");
                        DebugFrame.getInstance().logIn("宠物ID :" + dataInputStream.readInt());
                        DebugFrame.getInstance().logIn("宠物行走速度:" + (Macro.pet_v = dataInputStream.readByte()));
                        byte byte46 = dataInputStream.readByte();
                        DebugFrame.getInstance().logIn("行走路径length:" + byte46);
                        byte[] pet_PATH = new byte[byte46];
                        for (byte b14 = 0; b14 < byte46; ++b14) {
                            pet_PATH[b14] = dataInputStream.readByte();
                            Macro.PET_PATH = pet_PATH;
                        }
                        break;
                    }
                    case 14872: {
                        dataInputStream.readInt();
                        int n21 = Macro.petskillcount = dataInputStream.readByte();
                        Macro.petskill = new int[n21];
                        for (int n22 = 0; n22 < n21; ++n22) {
                            int int44 = dataInputStream.readInt();
                            dataInputStream.readInt();
                            Macro.petskill[n22] = int44;
                        }
                        for (byte byte47 = dataInputStream.readByte(), b15 = 0; b15 < byte47; ++b15) {
                            dataInputStream.readInt();
                        }
                        break;
                    }
                    case 11281:
                    case 11285: {
                        byte byte48 = dataInputStream.readByte();
                        DebugFrame.getInstance().logIn("个人商店的物品数量 = " + byte48);
                        String utf14 = "";
                        Hashtable hShopPackage = null;
                        if (byte48 > 0) {
                            utf14 = dataInputStream.readUTF();
                            hShopPackage = new Hashtable(byte48);
                            for (byte b16 = 0; b16 < byte48; ++b16) {
                                byte byte49 = dataInputStream.readByte();
                                PackageObject packageObject12;
                                if (byte49 == 1) {
                                    packageObject12 = NetParse.getInstance().readMeEquipData(dataInputStream);
                                    packageObject12.bytKey = byte49;
                                } else {
                                    packageObject12 = NetParse.getInstance().readMeGoodsData(dataInputStream, false);
                                    packageObject12.bytKey = byte49;
                                }
                                packageObject12.intPaiMaiPrice = dataInputStream.readInt();
                                hShopPackage.put(new Integer(packageObject12.intPOindex), packageObject12);
                            }
                        }
                        if (hShopPackage != null) {
                            Enumeration elements = hShopPackage.elements();
                            while (elements.hasMoreElements()) {
                                GameUI.getInstance().addObjectImg(((PackageObject) elements.nextElement()).shtIcon, "prop");
                            }
                        }
                        MenuUI.getInstance().strShopName = utf14;
                        NetParse.getInstance().clearTwoRect();
                        Param.getInstance().hShopPackage = hShopPackage;
                        if (GameUI.getInstance().checkMeMenuUi(-72)) {
                            MenuUI.getInstance().setInfoContent(Param.getInstance().hShopPackage, MenuUI.getInstance().personalShopSellPackage.getCellBoxIndex());
                        }
                        if (Param.getInstance().hShopPackage == null) {
                            Param.getInstance().hImgObject = null;
                        }
                        if (short1 == 11281) {
                            Param.getInstance().packageBoxMaxNum = Param.getInstance().bytShopBag;
                            GameUI.getInstance().setNextMenu((byte) (-35), "个人商店");
                            NetSend.getInstance().sendShop_OpenBag((byte) 1);
                            break;
                        }
                        break;
                    }
                    case 11282: {
                        byte byte50 = dataInputStream.readByte();
                        byte byte51 = dataInputStream.readByte();
                        DebugFrame.getInstance().logIn("包裹格子的数量：" + byte51);
                        Param.getInstance().packageBoxMaxNum = ((byte51 == 0) ? Param.getInstance().packageBoxMaxNum : byte51);
                        if (Param.getInstance().personalPackage != null) {
                            Param.getInstance().personalPackage.reset(Param.getInstance().packageBoxMaxNum);
                        }
                        byte byte52 = dataInputStream.readByte();
                        Hashtable hPackage4 = new Hashtable();
                        if (byte50 == 1) {
                            for (byte b17 = 0; b17 < byte52; ++b17) {
                                PackageObject meEquipData = NetParse.getInstance().readMeEquipData(dataInputStream);
                                meEquipData.bytKey = byte50;
                                hPackage4.put(new Integer(meEquipData.intPOindex), meEquipData);
                            }
                        } else {
                            for (byte b18 = 0; b18 < byte52; ++b18) {
                                PackageObject meGoodsData2 = NetParse.getInstance().readMeGoodsData(dataInputStream, false);
                                meGoodsData2.bytKey = byte50;
                                hPackage4.put(new Integer(meGoodsData2.intPOindex), meGoodsData2);
                            }
                        }
                        if (DCanvas.getInstance().UMenu == null) {
                            break;
                        }
                        if (hPackage4 != null) {
                            Enumeration elements2 = hPackage4.elements();
                            while (elements2.hasMoreElements()) {
                                PackageObject packageObject13 = (PackageObject) elements2.nextElement();
                                int n23 = -1;
                                if (Param.getInstance().hShopPackage != null) {
                                    int intPOindex2 = packageObject13.intPOindex;
                                    Enumeration elements3 = (Enumeration) Param.getInstance().hShopPackage.elements();
                                    while (elements3.hasMoreElements()) {
                                        PackageObject packageObject14 = (PackageObject) elements3.nextElement();
                                        if (intPOindex2 == packageObject14.bytBagPlace && packageObject13.intId == packageObject14.intId) {
                                            packageObject13.shtPOnum -= packageObject14.shtPOnum;
                                            if (packageObject13.shtPOnum != 0) {
                                                continue;
                                            }
                                            hPackage4.remove(new Integer(packageObject13.intPOindex));
                                            n23 = 0;
                                        }
                                    }
                                }
                                if (n23 != 0) {
                                    GameUI.getInstance().addObjectImg(packageObject13.shtIcon, "prop");
                                }
                            }
                        }
                        if (DCanvas.getInstance().UMenu == null) {
                            Param.getInstance().hImgObject = null;
                            break;
                        }
                        NetParse.getInstance().clearTwoRect();
                        Param.getInstance().hPackage = hPackage4;
                        if (GameUI.getInstance().checkMeMenuUi(-35)) {
                            MenuUI.getInstance().setInfoContent(Param.getInstance().hPackage, Param.getInstance().personalPackage.getCellBoxIndex());
                            break;
                        }
                        break;
                    }
                    case 11283: {
                        int int45 = dataInputStream.readInt();
                        String utf15 = "";
                        byte byte53 = dataInputStream.readByte();
                        if (byte53 == 1) {
                            utf15 = dataInputStream.readUTF();
                        }
                        if (ORPMe.ME.intUserId == int45) {
                            if (byte53 == 1) {
                                ORPMe.ME.setStall(utf15);
                                if (GameUI.getInstance().checkMeMenuUi(-35)) {
                                    GameControl.getInstance().delUIbase(3);
                                }
                            } else {
                                ORPMe.ME.delStall();
                                DCanvas.getInstance().addInformation("商店已关闭");
                                GameControl.getInstance().delUIbase(3);
                                GameUI.getInstance().setButton();
                            }
                        }
                        if (Param.getInstance().htRolePlayer == null) {
                            break;
                        }
                        ORPlayer orPlayer4 = (ORPlayer) Param.getInstance().htRolePlayer.get(new Integer(int45));
                        if (orPlayer4 == null) {
                            break;
                        }
                        if (byte53 == 1) {
                            orPlayer4.setStall(utf15);
                            break;
                        }
                        orPlayer4.delStall();
                        GameControl.getInstance().delUIbase(3);
                        break;
                    }
                    case 11284: {
                        dataInputStream.readByte();
                        byte byte54 = dataInputStream.readByte();
                        byte byte55 = dataInputStream.readByte();
                        if (DCanvas.getInstance().UMenu == null || (MenuUI.getInstance().bytMenuState != -35 && MenuUI.getInstance().bytMenuState != -72)) {
                            break;
                        }
                        if (byte55 == 1) {
                            int int46 = dataInputStream.readInt();
                            if (Param.getInstance().hShopPackage == null) {
                                break;
                            }
                            PackageObject packageObject15 = (PackageObject) Param.getInstance().hShopPackage.get(new Integer(byte54));
                            if (packageObject15 != null) {
                                packageObject15.intPaiMaiPrice = int46;
                                MenuUI.getInstance().setInfoContent(Param.getInstance().hShopPackage, MenuUI.getInstance().personalShopSellPackage.getCellBoxIndex());
                                break;
                            }
                            break;
                        } else {
                            if (Param.getInstance().hShopPackage != null) {
                                Param.getInstance().hShopPackage.remove(new Integer(byte54));
                                if (Param.getInstance().hShopPackage.isEmpty()) {
                                    Param.getInstance().hShopPackage = null;
                                }
                                MenuUI.getInstance().setInfoContent(Param.getInstance().hShopPackage, MenuUI.getInstance().personalShopSellPackage.getCellBoxIndex());
                                break;
                            }
                            break;
                        }
                    }
                    case 11298: {
                        byte byte56 = dataInputStream.readByte();
                        int int47 = dataInputStream.readInt();
                        String utf16 = dataInputStream.readUTF();
                        Param.getInstance().blnIsBaiShi = (byte56 == 2);
                        Param.getInstance().intShiTuID = int47;
                        Param.getInstance().strShiTuName = utf16;
                        GameUI.getInstance().setDialog((byte) 18);
                        break;
                    }
                    case 23826: {
                        String utf17 = dataInputStream.readUTF();
                        String utf18 = dataInputStream.readUTF();
                        if (!GameUI.getInstance().blnFight) {
                            NetParse.getInstance().initCue(utf17, utf18);
                        } else {
                            Param.getInstance().strFullTitle = utf17;
                            Param.getInstance().strFullCue = utf18;
                        }
                        if (!Param.getInstance().blnNpcOptionWork) {
                            Param.getInstance().blnNpcOptionWork = true;
                            break;
                        }
                        break;
                    }
                    case 23828: {
                        GameUI.getInstance().setHintDialog((byte) 5, dataInputStream.readUTF());
                        break;
                    }
                    case 1041: {
                        GameControl.getInstance().setReLoadRole();
                        GameControl.getInstance().delUIbase(7);
                        NetSend.getInstance().sendReloadLand();
                        break;
                    }
                    case 16657: {
                        byte byte57 = dataInputStream.readByte();
                        int int48 = dataInputStream.readInt();
                        if (byte57 == 1) {
                            String utf19 = dataInputStream.readUTF();
                            if (Param.getInstance().htRoleNPC == null) {
                                break;
                            }
                            ONpc npc = GameUI.getInstance().getNpc(int48);
                            if (npc == null) {
                                break;
                            }
                            npc.setChat(utf19);
                            npc.bytChatTime = 1;
                            if (GameUI.getInstance().bytHaveNpcChat > 0) {
                                GameUI.getInstance().bytHaveNpcChat = (byte) (-GameUI.getInstance().bytHaveNpcChat);
                                break;
                            }
                            break;
                        } else {
                            if (Param.getInstance().htRoleNPC == null) {
                                break;
                            }
                            ONpc npc2 = GameUI.getInstance().getNpc(int48);
                            if (npc2 == null) {
                                break;
                            }
                            npc2.bytChatTime = 0;
                            npc2.strChat = null;
                            if (GameUI.getInstance().bytHaveNpcChat < 0) {
                                GameUI.getInstance().bytHaveNpcChat = (byte) (-GameUI.getInstance().bytHaveNpcChat);
                                break;
                            }
                            break;
                        }
                    }
                    case 15106: {
                        Macro.FEE_POINT = dataInputStream.readInt();
                        break;
                    }
                    case 15109: {
                        DebugFrame.getInstance().logIn("8026获得商城数据解析：" + System.currentTimeMillis());
                        if (dataInputStream.readByte() == 1) {
                            DebugFrame.getInstance().logIn("8026更新解析开始：" + System.currentTimeMillis());
                            if (Param.MALL_TAB_LIST != null) {
                                for (int n24 = 0; n24 < Param.MALL_TAB_LIST.length; ++n24) {
                                    RmsBase.deletRecord(Param.MALL_TAB_LIST[n24]);
                                }
                            }
                            Param.MALL_VERSION = dataInputStream.readShort();
                            byte byte58 = dataInputStream.readByte();
                            Param.MALL_EXTEND_LIST = new Vector(1, 1);
                            for (byte b19 = 0; b19 < byte58; ++b19) {
                                Param.MALL_EXTEND_LIST.addElement(new String[]{new StringBuffer().append(dataInputStream.readUTF()).toString(), new StringBuffer().append(dataInputStream.readByte()).toString()});
                            }
                            byte byte59 = dataInputStream.readByte();
                            Param.strNotice = dataInputStream.readUTF();
                            if (byte59 > 0) {
                                Hashtable hMallPackage = new Hashtable(byte59);
                                Param.MALL_TAB_LIST = new String[byte59];
                                Param.MALL_BOX_NUM = new byte[byte59];
                                for (byte intPOindex3 = 0; intPOindex3 < byte59; ++intPOindex3) {
                                    String utf20 = dataInputStream.readUTF();
                                    Param.MALL_TAB_LIST[intPOindex3] = utf20;
                                    short short17 = dataInputStream.readShort();
                                    Param.getInstance().packageBoxMaxNum = (byte) ((short17 / 8 + 1) * 8);
                                    Param.MALL_BOX_NUM[intPOindex3] = Param.getInstance().packageBoxMaxNum;
                                    Hashtable hashtable = new Hashtable(short17);
                                    if (short17 > 0) {
                                        for (short n25 = 0; n25 < short17; ++n25) {
                                            PackageObject packageObject16 = new PackageObject();
                                            packageObject16.intPOindex = intPOindex3;
                                            packageObject16.intId = dataInputStream.readInt();
                                            packageObject16.strName = dataInputStream.readUTF();
                                            packageObject16.bytQuality = dataInputStream.readByte();
                                            packageObject16.intColor = Macro.INT_PROP_COLOR[packageObject16.bytQuality];
                                            packageObject16.shtIcon = dataInputStream.readShort();
                                            packageObject16.strInfo = dataInputStream.readUTF();
                                            packageObject16.intPaiMaiPrice = dataInputStream.readInt();
                                            packageObject16.shtLevel = dataInputStream.readShort();
                                            packageObject16.bytBagPlace = dataInputStream.readByte();
                                            packageObject16.shtPOnum = dataInputStream.readShort();
                                            hashtable.put(new Integer(n25), packageObject16);
                                            RmsBase.addRecord(utf20, packageObject16.toBytes());
                                        }
                                    }
                                    RmsBase.colseRecord(utf20);
                                    hMallPackage.put(new Integer(intPOindex3), hashtable);
                                }
                                Rms.delRMS("XJMALL");
                                Rms.saveMall(Param.MALL_VERSION, Param.strNotice, Param.MALL_TAB_LIST, Param.MALL_BOX_NUM, Param.MALL_EXTEND_LIST);
                                Param.getInstance().hMallPackage = hMallPackage;
                            } else {
                                DCanvas.getInstance().addInformation("暂无商品");
                            }
                            DebugFrame.getInstance().logIn("8026更新解析结束：" + System.currentTimeMillis());
                        } else {
                            DebugFrame.getInstance().logIn("8026不更新读RMS开始：" + System.currentTimeMillis());
                            if (Param.MALL_TAB_LIST != null) {
                                int length = Param.MALL_TAB_LIST.length;
                                Param.getInstance().hMallPackage = new Hashtable(length);
                                for (int n26 = 0; n26 < length; ++n26) {
                                    String s = Param.MALL_TAB_LIST[n26];
                                    Param.getInstance().hMallPackage.put(new Integer(n26), RmsBase.getRecordsHT(s));
                                    RmsBase.colseRecord(s);
                                }
                            }
                            DebugFrame.getInstance().logIn("8026不更新读RMS结束：" + System.currentTimeMillis());
                        }
                        DebugFrame.getInstance().logIn("8026显示商城结束：" + System.currentTimeMillis());
                        break;
                    }
                    case 15112: {
                        byte byte60 = dataInputStream.readByte();
                        if (byte60 != 0) {
                            Param.getInstance().MALL_CHANNEL_VLIST = new Vector(2, 2);
                            for (byte b20 = 0; b20 < byte60; ++b20) {
                                String[] array6 = new String[6];
                                byte byte61 = dataInputStream.readByte();
                                String utf21 = dataInputStream.readUTF();
                                String utf22 = dataInputStream.readUTF();
                                array6[2] = new StringBuffer().append(byte61).toString();
                                array6[3] = utf21;
                                array6[5] = utf22;
                                Param.getInstance().MALL_CHANNEL_VLIST.addElement(array6);
                            }
                            byte byte62 = dataInputStream.readByte();
                            if (byte62 != 0) {
                                Param.getInstance().MALL_CHANNEL_SUB_VLIST = new Vector(3, 1);
                                for (byte b21 = 0; b21 < byte62; ++b21) {
                                    String[] array7 = new String[6];
                                    byte byte63 = dataInputStream.readByte();
                                    String utf23 = dataInputStream.readUTF();
                                    byte byte64 = dataInputStream.readByte();
                                    String utf24 = dataInputStream.readUTF();
                                    int int49 = dataInputStream.readInt();
                                    String utf25 = dataInputStream.readUTF();
                                    array7[0] = new StringBuffer().append(byte63).toString();
                                    array7[1] = utf23;
                                    array7[2] = new StringBuffer().append(byte64).toString();
                                    array7[3] = utf24;
                                    array7[4] = new StringBuffer().append(int49).toString();
                                    array7[5] = utf25;
                                    Param.getInstance().MALL_CHANNEL_SUB_VLIST.addElement(array7);
                                }
                            }
                        }
                        if (GameUI.getInstance().checkMeSystemMenuUi(-16) || GameUI.getInstance().checkMeSystemMenuUi(-30)) {
                            NetParse.getInstance().clearTwoRect();
                            SystemMenuUI.getInstance().setState((byte) (-18));
                            break;
                        }
                        break;
                    }
                    case 15110: {
                        Param.getInstance().MALL_SHANGCHENG_SERIAL = new String[]{dataInputStream.readUTF(), new StringBuffer().append(dataInputStream.readInt()).toString(), new StringBuffer().append(dataInputStream.readInt()).toString(), new StringBuffer().append(dataInputStream.readByte()).toString(), new StringBuffer().append(dataInputStream.readShort()).toString(), new StringBuffer().append(((String[]) Param.getInstance().MALL_SHOW_LIST.elementAt(MenuUI.getInstance().getOneMove()))[1]).toString()};
                        if (GameUI.getInstance().checkMeMenuUi(-16)) {
                            MenuUI.getInstance().setState((byte) (-18), "点数充值");
                            break;
                        }
                        break;
                    }
                    case 15124: {
                        String utf26 = dataInputStream.readUTF();
                        Param.getInstance().MALL_STR_RECORD = utf26;
                        if (utf26 != null) {
                            GameUI.getInstance().setNextMenu((byte) (-26), "明细查询");
                            break;
                        }
                        break;
                    }
                    case 1042: {
                        int[][] array8 = new int[26][5];
                        short[] array9 = new short[26];
                        String[] array10 = new String[26];
                        for (int n27 = 0; n27 < 26; n27 = (byte) (n27 + 1)) {
                            byte byte65 = dataInputStream.readByte();
                            if (byte65 == 1) {
                                array8[n27][4] = 1;
                                array8[n27][0] = dataInputStream.readInt();
                            } else if (byte65 == 2) {
                                array8[n27][4] = 2;
                                array8[n27][0] = dataInputStream.readInt();
                            } else if (byte65 == 3) {
                                array8[n27][4] = 3;
                                array8[n27][0] = dataInputStream.readInt();
                                array10[n27] = dataInputStream.readUTF();
                                array9[n27] = dataInputStream.readShort();
                                int int50 = dataInputStream.readInt();
                                if (int50 > 0) {
                                    array8[n27][2] = int50 * 1000;
                                    array8[n27][1] = dataInputStream.readInt() * 1000;
                                    array8[n27][3] = dataInputStream.readShort();
                                }
                            }
                        }
                        GameUI.getInstance().initShortcutKeys(array8, array9, array10);
                        break;
                    }
                    case 7462: {
                        PackageObject packageObject17 = new PackageObject();
                        packageObject17.strName = dataInputStream.readUTF();
                        if (dataInputStream.readByte() == 1) {
                            NetParse.getInstance().get(dataInputStream, packageObject17);
                            packageObject17.bytIsBind = dataInputStream.readByte();
                            packageObject17.bytSeal = dataInputStream.readByte();
                            packageObject17.shtStamina = dataInputStream.readShort();
                            packageObject17.intPrice = dataInputStream.readInt();
                            Param.getInstance().GoodsSee = true;
                            GameUI.getInstance().getEquioAttribute(packageObject17);
                            Param.getInstance().GoodsSee = false;
                            new FullInfo(packageObject17.strName, packageObject17.intColor, (byte) 42);
                            FullInfo.getInstance().setFullRectMenu_Vector();
                            break;
                        }
                        packageObject17.bytQuality = dataInputStream.readByte();
                        packageObject17.strInfo = dataInputStream.readUTF();
                        packageObject17.intPrice = dataInputStream.readInt();
                        StringBuffer sb4 = new StringBuffer();
                        sb4.append(packageObject17.strInfo);
                        sb4.append("\n");
                        sb4.append("出售价格：");
                        sb4.append(packageObject17.intPrice);
                        new FullInfo(packageObject17.strName, packageObject17.intColor, (byte) 42);
                        FullInfo.getInstance().setFullRectMenu(sb4.toString());
                        break;
                    }
                    case 23827: {
                        byte byte66 = dataInputStream.readByte();
                        int int51 = dataInputStream.readInt();
                        ORole oRole2 = null;
                        int int52 = dataInputStream.readInt();
                        if (byte66 == 2 && Param.getInstance().htRolePlayer != null) {
                            GameUI.getInstance().updateTeamHP((ORPlayer) Param.getInstance().htRolePlayer.get(new Integer(int51)), int52, -1);
                            oRole2 = (ORole) Param.getInstance().htRolePlayer.get(new Integer(int51));
                        } else if (byte66 == 3 && Param.getInstance().htRoleMonster != null) {
                            oRole2 = (ORole) Param.getInstance().htRoleMonster.get(new Integer(int51));
                        }
                        if (oRole2 == null) {
                            break;
                        }
                        oRole2.intHP = int52;
                        oRole2.intHPMax = dataInputStream.readInt();
                        if (oRole2.blnUseMp) {
                            oRole2.intMP = dataInputStream.readInt();
                            if (byte66 == 2 && Param.getInstance().htRolePlayer != null) {
                                GameUI.getInstance().updateTeamMP((ORPlayer) Param.getInstance().htRolePlayer.get(new Integer(int51)), oRole2.intMP, -1);
                                oRole2 = (ORole) Param.getInstance().htRolePlayer.get(new Integer(int51));
                            }
                            oRole2.intMPMax = dataInputStream.readInt();
                            break;
                        }
                        break;
                    }
                    case 3332: {
                        byte byte67 = dataInputStream.readByte();
                        DebugFrame.getInstance().logIn("修改移动速度:");
                        int int53 = dataInputStream.readInt();
                        DebugFrame.getInstance().logIn("_type:" + byte67 + " _id:" + int53);
                        ORole oRole3 = null;
                        if (byte67 == 2 && Param.getInstance().htRolePlayer != null) {
                            oRole3 = (ORole) Param.getInstance().htRolePlayer.get(new Integer(int53));
                        } else if (byte67 == 3 && Param.getInstance().htRoleMonster != null) {
                            oRole3 = (ORole) Param.getInstance().htRoleMonster.get(new Integer(int53));
                        }
                        if (oRole3 == null) {
                            break;
                        }
                        byte byte68 = dataInputStream.readByte();
                        DebugFrame.getInstance().logIn("_speed type:" + byte68);
                        if (byte68 < 3) {
                            oRole3.addSpecialtiesState((byte) 5);
                        } else {
                            oRole3.delSpecialtiesState((byte) 5);
                        }
                        if (byte67 == 2) {
                            oRole3.setSpeed(byte68);
                            break;
                        }
                        break;
                    }
                    case 3590: {
                        int int54 = dataInputStream.readInt();
                        if (dataInputStream.readByte() == 1) {
                            int int55 = dataInputStream.readInt();
                            String utf27 = dataInputStream.readUTF();
                            byte byte69 = dataInputStream.readByte();
                            short short18 = dataInputStream.readShort();
                            String utf28 = dataInputStream.readUTF();
                            int int56 = dataInputStream.readInt();
                            if (Param.getInstance().vCommonList != null) {
                                for (int n28 = 0; n28 < Param.getInstance().vCommonList.size(); n28 = (short) (n28 + 1)) {
                                    SkillObject skillObject4 = (SkillObject) Param.getInstance().vCommonList.elementAt(n28);
                                    if (skillObject4.intId == int54) {
                                        skillObject4.intId = int55;
                                        skillObject4.shtIcon = short18;
                                        skillObject4.strName = utf27;
                                        skillObject4.strInfo = utf28;
                                        skillObject4.bytLv = byte69;
                                        skillObject4.intCDTime = int56;
                                        break;
                                    }
                                }
                            }
                        } else if (Param.getInstance().vCommonList != null) {
                            int n29 = 0;
                            while (n29 < Param.getInstance().vCommonList.size()) {
                                if (((SkillObject) Param.getInstance().vCommonList.elementAt(n29)).intId == int54) {
                                    Param.getInstance().vCommonList.removeElementAt(n29);
                                    if (Param.getInstance().vCommonList.isEmpty()) {
                                        Param.getInstance().vCommonList = null;
                                        break;
                                    }
                                    MenuUI.getInstance().setChoiceMove(Param.getInstance().vCommonList.size(), MenuUI.getInstance().getOneMove(), MenuUI.getInstance().bytWordMaxH);
                                    break;
                                } else {
                                    n29 = (short) (n29 + 1);
                                }
                            }
                        }
                        if (DCanvas.getInstance().UMenu != null) {
                            MenuUI.getInstance().setStudySkill();
                            break;
                        }
                        break;
                    }
                    case 3587: {
                        DebugFrame.getInstance().logIn("3587");
                        if (dataInputStream.readByte() == 1) {
                            if (Param.getInstance().vSkillList == null) {
                                Param.getInstance().vSkillList = new Vector(1, 1);
                            }
                            NetParse.getInstance().readActiveSkillData(dataInputStream);
                        } else {
                            if (Param.getInstance().vSkillPassivityList == null) {
                                Param.getInstance().vSkillPassivityList = new Vector(1, 1);
                            }
                            NetParse.getInstance().readPassiveSkillData(dataInputStream);
                        }
                        MenuUI.getInstance().iniSkillOccList();
                        break;
                    }
                    case 3588: {
                        DebugFrame.getInstance().logIn("更新一个技能*************************");
                        byte byte70 = dataInputStream.readByte();
                        String utf29 = dataInputStream.readUTF();
                        DebugFrame.getInstance().logIn("技能_name:" + utf29);
                        if (byte70 == 1) {
                            if (Param.getInstance().vSkillList != null) {
                                for (int n30 = 0; n30 < Param.getInstance().vSkillList.size(); ++n30) {
                                    SkillObject skillObject5 = (SkillObject) Param.getInstance().vSkillList.elementAt(n30);
                                    if (skillObject5.strName.equals(utf29)) {
                                        skillObject5.intId = dataInputStream.readInt();
                                        skillObject5.bytLv = dataInputStream.readByte();
                                        skillObject5.bytRank = dataInputStream.readByte();
                                        skillObject5.strInfo = dataInputStream.readUTF();
                                        if (dataInputStream.readByte() == 1) {
                                            skillObject5.strNextInfo = dataInputStream.readUTF();
                                        } else {
                                            skillObject5.strNextInfo = "";
                                        }
                                        skillObject5.bytNeedPoints = dataInputStream.readShort();
                                        skillObject5.intNeedMoney = dataInputStream.readInt();
                                        skillObject5.bytCorrOcc = dataInputStream.readByte();
                                        ORPMe.ME.skillPoints = dataInputStream.readShort();
                                        skillObject5.intCDTimeMax = dataInputStream.readInt() * 1000;
                                        if (skillObject5.intCDTime > skillObject5.intCDTimeMax) {
                                            skillObject5.intCDTime = skillObject5.intCDTimeMax;
                                        }
                                        skillObject5.shtUseTime = dataInputStream.readShort();
                                        SkillObject.resetMagicContinuous(skillObject5);
                                        skillObject5.bytUserType = dataInputStream.readByte();
                                        skillObject5.bytAtkDistance = dataInputStream.readByte();
                                        skillObject5.shtHP = dataInputStream.readShort();
                                        short short19 = dataInputStream.readShort();
                                        byte byte71 = dataInputStream.readByte();
                                        byte byte72 = dataInputStream.readByte();
                                        if (short19 != 0) {
                                            skillObject5.shtMp = short19;
                                            skillObject5.bytMpType = 0;
                                        } else if (byte71 != 0) {
                                            skillObject5.shtMp = byte71;
                                            skillObject5.bytMpType = 1;
                                        } else if (byte72 != 0) {
                                            skillObject5.shtMp = byte72;
                                            skillObject5.bytMpType = 2;
                                        }
                                        if (skillObject5.bytKey != -1) {
                                            Param.getInstance().intShortcutKeys[skillObject5.bytKey][0] = skillObject5.intId;
                                            Param.getInstance().intShortcutKeys[skillObject5.bytKey][1] = skillObject5.intCDTime;
                                            Param.getInstance().intShortcutKeys[skillObject5.bytKey][2] = skillObject5.intCDTimeMax;
                                        }
                                        skillObject5.bytSkillPicId = dataInputStream.readShort();
                                        skillObject5.bytSkillAnuId = dataInputStream.readShort();
                                        DebugFrame.getInstance().logIn("主动技能png id :" + skillObject5.bytSkillPicId);
                                        DebugFrame.getInstance().logIn("主动技能anu id :" + skillObject5.bytSkillAnuId);
                                        skillObject5.bytRoleAction = dataInputStream.readByte();
                                        DebugFrame.getInstance().logIn("主动技能对应人物动作 :" + skillObject5.bytRoleAction);
                                        skillObject5.byteLayer = dataInputStream.readByte();
                                        DebugFrame.getInstance().logIn("主动技能显示层级(1 上 2 下) :" + skillObject5.byteLayer);
                                        skillObject5.bytDisplayPosYOffType = dataInputStream.readByte();
                                        DebugFrame.getInstance().logIn("主动技能显示偏移类型 :" + skillObject5.bytDisplayPosYOffType);
                                        skillObject5.blnSkillMultiAnimation = (dataInputStream.readByte() == 1);
                                        break;
                                    }
                                }
                            }
                        } else if (Param.getInstance().vSkillPassivityList != null) {
                            for (int n31 = 0; n31 < Param.getInstance().vSkillPassivityList.size(); ++n31) {
                                SkillObject skillObject6 = (SkillObject) Param.getInstance().vSkillPassivityList.elementAt(n31);
                                if (skillObject6.strName.equals(utf29)) {
                                    skillObject6.intId = dataInputStream.readInt();
                                    skillObject6.bytLv = dataInputStream.readByte();
                                    skillObject6.bytRank = dataInputStream.readByte();
                                    skillObject6.strInfo = dataInputStream.readUTF();
                                    if (dataInputStream.readByte() == 1) {
                                        skillObject6.strNextInfo = dataInputStream.readUTF();
                                    } else {
                                        skillObject6.strNextInfo = "";
                                    }
                                    skillObject6.bytNeedPoints = dataInputStream.readShort();
                                    skillObject6.intNeedMoney = dataInputStream.readInt();
                                    skillObject6.bytCorrOcc = dataInputStream.readByte();
                                    ORPMe.ME.skillPoints = dataInputStream.readShort();
                                    break;
                                }
                            }
                        }
                        MenuUI.getInstance().iniSkillOccList();
                        break;
                    }
                    case 3586: {
                        short short20 = dataInputStream.readShort();
                        if (short20 != 0) {
                            Param.getInstance().vSkillList = new Vector(1, 1);
                            for (short n32 = 0; n32 < short20; ++n32) {
                                NetParse.getInstance().readActiveSkillData(dataInputStream);
                            }
                            MenuUI.getInstance().iniSkillOccList();
                        }
                        short short21 = dataInputStream.readShort();
                        if (short21 != 0) {
                            Param.getInstance().vSkillPassivityList = new Vector(1, 1);
                            for (short n33 = 0; n33 < short21; ++n33) {
                                NetParse.getInstance().readPassiveSkillData(dataInputStream);
                            }
                            MenuUI.getInstance().iniSkillOccList();
                        } else {
                            Param.getInstance().vSkillPassivityList = null;
                        }
                        GameUI.getInstance().updateSkillShortcutKeys();
                        Param.getInstance().vSkillBook = new Vector();
                        byte byte73 = dataInputStream.readByte();
                        if (byte73 != 0) {
                            for (short n34 = 0; n34 < byte73; ++n34) {
                                NetParse.getInstance().readBookData(dataInputStream);
                            }
                            break;
                        }
                        break;
                    }
                    case 1049: {
                        dataInputStream.readInt();
                        dataInputStream.readByte();
                        boolean b22 = dataInputStream.readByte() == 0;
                        dataInputStream.readByte();
                        break;
                    }
                    case 1056: {
                        dataInputStream.readInt();
                        GameUI.getInstance().setNextMenu((byte) 61, "战斗技能");
                        MenuUI.getInstance().initSkillBookList();
                        MenuUI.getInstance().bytMoveType = 2;
                        break;
                    }
                    case 1057: {
                        Hashtable hPackage5 = new Hashtable();
                        byte byte74 = dataInputStream.readByte();
                        if (byte74 > 0) {
                            for (byte b23 = 0; b23 < byte74; ++b23) {
                                PackageObject packageObject18 = new PackageObject();
                                packageObject18.intId = dataInputStream.readInt();
                                packageObject18.shtIcon = dataInputStream.readShort();
                                packageObject18.shtAddition = dataInputStream.readShort();
                                packageObject18.bytQuality = dataInputStream.readByte();
                                packageObject18.strName = dataInputStream.readUTF();
                                packageObject18.strInfo = dataInputStream.readUTF();
                                GameUI.getInstance().addObjectImg(packageObject18.shtIcon, "prop");
                                hPackage5.put(new Integer(b23), packageObject18);
                            }
                        }
                        Param.getInstance().hPackage = hPackage5;
                        if (DCanvas.getInstance().UMenu != null && MenuUI.getInstance().bytMenuState == 61 && MenuUI.getInstance().bytTitleMove == 2 && !Param.getInstance().hPackage.isEmpty()) {
                            MenuUI.getInstance().setPackageDataRect("镶嵌天书", (short) 0, 0);
                            MenuUI.getInstance().twodialog.setPackageSettingData(Param.getInstance().hPackage);
                            break;
                        }
                        break;
                    }
                    case 3593: {
                        int int57 = dataInputStream.readInt();
                        if (Param.getInstance().vSkillList != null) {
                            int n35 = 0;
                            while (n35 < Param.getInstance().vSkillList.size()) {
                                SkillObject skillObject7 = (SkillObject) Param.getInstance().vSkillList.elementAt(n35);
                                if (skillObject7.intId == int57) {
                                    skillObject7.intCDTimeMax = dataInputStream.readInt() * 1000;
                                    skillObject7.intCDTime = dataInputStream.readInt() * 1000;
                                    skillObject7.shtUseTime = dataInputStream.readShort();
                                    skillObject7.bytAtkDistance = dataInputStream.readByte();
                                    skillObject7.shtHP = dataInputStream.readShort();
                                    short short22 = dataInputStream.readShort();
                                    byte byte75 = dataInputStream.readByte();
                                    byte byte76 = dataInputStream.readByte();
                                    if (short22 != 0) {
                                        skillObject7.shtMp = short22;
                                        skillObject7.bytMpType = 0;
                                        break;
                                    }
                                    if (byte75 != 0) {
                                        skillObject7.shtMp = byte75;
                                        skillObject7.bytMpType = 1;
                                        break;
                                    }
                                    if (byte76 != 0) {
                                        skillObject7.shtMp = byte76;
                                        skillObject7.bytMpType = 2;
                                        break;
                                    }
                                    break;
                                } else {
                                    ++n35;
                                }
                            }
                            break;
                        }
                        break;
                    }
                    case 3592: {
                        for (short short23 = dataInputStream.readShort(), n36 = 0; n36 < short23; ++n36) {
                            int int58 = dataInputStream.readInt();
                            short short24 = dataInputStream.readShort();
                            for (int n37 = 0; n37 < Param.getInstance().vSkillList.size(); ++n37) {
                                SkillObject skillObject8 = (SkillObject) Param.getInstance().vSkillList.elementAt(n37);
                                if (skillObject8 != null && int58 == skillObject8.intId) {
                                    skillObject8.shtUseTime = short24;
                                    break;
                                }
                            }
                        }
                        break;
                    }
                    case 11572: {
                        int int59 = dataInputStream.readInt();
                        int int60 = dataInputStream.readInt();
                        byte byte77 = dataInputStream.readByte();
                        String utf30 = dataInputStream.readUTF();
                        Param.getInstance().userID = int59;
                        Param.getInstance().askID = int60;
                        Param.getInstance().answer = byte77;
                        Param.getInstance().dialog = utf30;
                        GameUI.getInstance().setHintDialog((byte) (-8), Param.getInstance().dialog);
                        break;
                    }
                    case 23318: {
                        dataInputStream.readInt();
                        ORPMe.ME.spouse = dataInputStream.readUTF();
                        break;
                    }
                    case 10499: {
                        short short25 = dataInputStream.readShort();
                        short short26 = dataInputStream.readShort();
                        short short27 = dataInputStream.readShort();
                        Macro.MAP_X = short26;
                        Macro.MAP_Y = short27;
                        Macro.MAP_NUMBER = short25;
                        GameControl.getInstance().CreateState((byte) 7);
                        DialogUI.getInstance().setDialog((byte) (-20), "请选择副本模式", (byte) 2);
                        break;
                    }
                    case 23829: {
                        int int61 = dataInputStream.readInt();
                        int int62 = dataInputStream.readInt();
                        String utf31 = dataInputStream.readUTF();
                        Param.getInstance().roleID = int61;
                        Param.getInstance().playID = int62;
                        Param.getInstance().askdialog = utf31;
                        GameUI.getInstance().setDialog((byte) (-21));
                        break;
                    }
                    case 3080: {
                        int int63 = dataInputStream.readInt();
                        int int64 = dataInputStream.readInt();
                        int int65 = dataInputStream.readInt();
                        int int66 = dataInputStream.readInt();
                        int int67 = dataInputStream.readInt();
                        if (Param.getInstance().htRolePlayer != null) {
                            GameUI.getInstance().updateOtherHP(int63, int64, int65);
                            GameUI.getInstance().updateOtherMP(int63, int66, int67);
                            break;
                        }
                        break;
                    }
                    case 23042: {
                        byte byte78 = dataInputStream.readByte();
                        Param.getInstance().PARTNER_relation = byte78;
                        Param.getInstance().IfLoad = false;
                        if (byte78 <= 0) {
                            GameUI.getInstance().setNextMenu((byte) (-50), "");
                            DCanvas.getInstance().addInformation("您还没有伴侣");
                            break;
                        }
                        Param.getInstance().partner = NetParse.getInstance().readPartnerDate(dataInputStream, byte78);
                        if (Param.getInstance().partner != null) {
                            Param.getInstance().partner.pushTask((byte) 0, (byte) 2, (byte) 1);
                        }
                        if (Param.getInstance().IfLoad) {
                            GameUI.getInstance().setNextMenu((byte) (-50), "");
                            Param.getInstance().IfLoad = false;
                            break;
                        }
                        break;
                    }
                    case 11573: {
                        Param.getInstance().PARTNER_COPYID = dataInputStream.readInt();
                        Param.getInstance().PARTNER_WEDDINGID = dataInputStream.readShort();
                        String utf32 = dataInputStream.readUTF();
                        GameControl.getInstance().CreateState((byte) 7);
                        DialogUI.getInstance().setDialog((byte) (-10), utf32, (byte) 2);
                        break;
                    }
                    case 11574: {
                        byte byte79 = dataInputStream.readByte();
                        String utf33 = dataInputStream.readUTF();
                        Param.getInstance().PARTNER_buy_type = byte79;
                        Param.getInstance().dialog = utf33;
                        GameUI.getInstance().setHintDialog((byte) (-9), Param.getInstance().dialog);
                    }
                    case 11007: {
                        DebugFrame.getInstance().logIn("获取资源配置表!!!");
                        int int68 = dataInputStream.readInt();
                        if (IResConfig.RES_CONFIG.length == int68) {
                            for (int n38 = 0; n38 < int68; ++n38) {
                                String utf34 = dataInputStream.readUTF();
                                String utf35 = dataInputStream.readUTF();
                                String utf36 = dataInputStream.readUTF();
                                IResConfig.RES_CONFIG[n38][1] = utf34;
                                IResConfig.RES_CONFIG[n38][0] = utf35;
                                IResConfig.RES_CONFIG[n38][2] = utf36;
                            }
                            break;
                        }
                        break;
                    }
                    case 3858: {
                        DebugFrame.getInstance().logIn("请求计费服务器,获得计费方式");
                        Param.Task_Fee_Type = dataInputStream.readByte();
                        if (Param.Task_Fee_Type != 0) {
                            if (Param.Task_Fee_Type == 1) {
                                Param.Task_Fee_Phone_Num = dataInputStream.readUTF();
                                Param.Task_Fee_Msg_TXT_1 = dataInputStream.readUTF();
                                Param.Task_Fee_Msg_TXT_2 = dataInputStream.readUTF();
                                Param.Fee_Trans_Id = dataInputStream.readUTF();
                                Param.Fee_SMS_Count = dataInputStream.readByte();
                            } else if (Param.Task_Fee_Type == 2) {
                                Param.Fee_Proxy_pCode = dataInputStream.readUTF();
                                Param.Fee_Proxy_ID = dataInputStream.readByte();
                                Param.Fee_Trans_Id = dataInputStream.readUTF();
                            }
                        }
                        Param.Fee_Type_Received = true;
                        Param.getInstance();
                        if (Param.Fee_Dialog_Accept && Param.Fee_Type_Received) {
                            NetSend.getInstance().sendAcceptFeeMessage_Double_Check();
                            Param.getInstance();
                            Param.Fee_Dialog_Accept = false;
                            Param.Fee_Type_Received = false;
                            break;
                        }
                        break;
                    }
                    case 1059: {
                        int int69 = dataInputStream.readInt();
                        dataInputStream.readUTF();
                        dataInputStream.readUTF();
                        short short28 = dataInputStream.readShort();
                        DebugFrame.getInstance().logIn("get gift: Time:" + int69);
                        Param.Gifts_Time_Cout_Down = int69 * 1000;
                        Param.Gifts_Icon_Cout_Down = short28;
                        break;
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return short1;
    }
}
