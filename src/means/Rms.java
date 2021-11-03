// Decompiled with: CFR 0.151
// Class Version: 3
package means;

import base.GameControl;
import base.Macro;
import base.Param;
import face.GameUI;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Vector;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreNotOpenException;
import means.DebugFrame;
import model.ORPMe;
import storage.Store;

public class Rms {
    private static final String SETSELETC = "SETTING";
    private static final byte SETSELECTC_SETTING = 1;
    private static final byte[] SETSELETC_ARRAY = new byte[]{1};
    public static final String ROLEPLACE = "XJB1";
    public static final String PHONEINFO = "XJB2";
    public static final String MALLINFO = "XJMALL";
    private static final String MACROCHAT = "XJB7";
    private static final String UseState = "XJB8";
    public static final String UserNum = "XJB9";
    private static boolean blnPlace = false;
    private static boolean blnInfo = false;
    private static boolean blnuse = false;
    public static String strRmsName = null;
    public static short ServiceID = (short)-1;
    public static String strRmsUserNum = null;
    public static String strRmsPassword = null;
    public static boolean blnIsSaveLandInfo;
    public static boolean blnIsSava;
    public static byte bytPlace;
    private static boolean blnSETSELECT;
    public static boolean blnSelectHttp;
    public static boolean blnSelectCMWAP;
    public static byte bytEffect;
    public static byte bytPortrait;
    public static boolean blnBusiness;
    public static boolean blnTeam;
    public static byte bytChatRow;
    public static boolean blnChatWChannel;
    public static boolean blnWeather;
    public static byte bytShowName;
    public static boolean blnAutoWalk;
    public static boolean blnAutoSelect;
    public static byte bytShowMap;
    public static byte bytLevalFunctionFlag;
    public static boolean blnShowKeyInfo;
    public static boolean blnShowRole;
    public static String[] strsMacroChat;
    public static boolean blnIsRead;

    public static void saveUseCode(boolean bl) {
        blnuse = true;
        blnIsSava = bl;
        try {
            RecordStore recordStore = RecordStore.openRecordStore(UseState, true);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeBoolean(blnIsSava);
            recordStore.addRecord(byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.toByteArray().length);
            if (recordStore.getNumRecords() == 0) {
                recordStore.addRecord(byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.toByteArray().length);
            } else {
                recordStore.setRecord(1, byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.toByteArray().length);
            }
            recordStore.closeRecordStore();
            recordStore = null;
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void loadUseCode() {
        if (!blnuse) {
            blnuse = true;
            try {
                RecordStore recordStore = RecordStore.openRecordStore(UseState, true);
                if (recordStore.getNumRecords() != 0) {
                    byte[] byArray = recordStore.getRecord(1);
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byArray);
                    DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
                    blnIsSava = dataInputStream.readBoolean();
                }
                recordStore.closeRecordStore();
                recordStore = null;
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public static void saveServiseID(short s) {
        blnuse = true;
        ServiceID = s;
        try {
            RecordStore recordStore = RecordStore.openRecordStore("serviseID", true);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeShort(ServiceID);
            recordStore.addRecord(byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.toByteArray().length);
            if (recordStore.getNumRecords() == 0) {
                recordStore.addRecord(byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.toByteArray().length);
            } else {
                recordStore.setRecord(1, byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.toByteArray().length);
            }
            recordStore.closeRecordStore();
            recordStore = null;
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void loadServiseID() {
        try {
            RecordStore recordStore = RecordStore.openRecordStore("serviseID", true);
            if (recordStore.getNumRecords() != 0) {
                byte[] byArray = recordStore.getRecord(1);
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byArray);
                DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
                ServiceID = dataInputStream.readShort();
            }
            recordStore.closeRecordStore();
            recordStore = null;
        }
        catch (Exception exception) {
            DebugFrame.getInstance().logInException("没找到rms serviseID");
        }
    }

    public static void saveUseNum(String string) {
        try {
            RecordStore recordStore = RecordStore.openRecordStore(UserNum, true);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeUTF(string);
            recordStore.addRecord(byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.toByteArray().length);
            if (recordStore.getNumRecords() == 0) {
                recordStore.addRecord(byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.toByteArray().length);
            } else {
                recordStore.setRecord(1, byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.toByteArray().length);
            }
            recordStore.closeRecordStore();
            recordStore = null;
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void saveID(String string, String string2, boolean bl) {
        blnInfo = true;
        strRmsName = string;
        strRmsPassword = string2;
        blnIsSaveLandInfo = bl;
        try {
            RecordStore recordStore = RecordStore.openRecordStore(PHONEINFO, true);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeBoolean(blnIsSaveLandInfo);
            dataOutputStream.writeUTF(strRmsName);
            dataOutputStream.writeUTF(strRmsPassword);
            if (recordStore.getNumRecords() == 0) {
                recordStore.addRecord(byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.toByteArray().length);
            } else {
                recordStore.setRecord(1, byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.toByteArray().length);
            }
            recordStore.closeRecordStore();
            recordStore = null;
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void loadID() {
        if (!blnInfo) {
            blnInfo = true;
            try {
                RecordStore recordStore = RecordStore.openRecordStore(PHONEINFO, true);
                if (recordStore.getNumRecords() != 0) {
                    byte[] byArray = recordStore.getRecord(1);
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byArray);
                    DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
                    blnIsSaveLandInfo = dataInputStream.readBoolean();
                    strRmsName = dataInputStream.readUTF();
                    strRmsPassword = dataInputStream.readUTF();
                }
                recordStore.closeRecordStore();
                recordStore = null;
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public static void saveMall(int n, String string, String[] stringArray, byte[] byArray, Vector vector) {
        try {
            int n2;
            int n3;
            RecordStore recordStore = RecordStore.openRecordStore(MALLINFO, true);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeShort(n);
            dataOutputStream.writeUTF(string);
            int n4 = stringArray.length;
            dataOutputStream.writeByte(n4);
            for (n3 = 0; n3 < n4; ++n3) {
                dataOutputStream.writeUTF(stringArray[n3]);
            }
            n3 = byArray.length;
            dataOutputStream.writeByte(n3);
            for (n2 = 0; n2 < n3; ++n2) {
                dataOutputStream.writeByte(byArray[n2]);
            }
            n2 = vector.size();
            dataOutputStream.writeByte(n2);
            for (int i = 0; i < n2; ++i) {
                String[] stringArray2 = (String[])vector.elementAt(i);
                dataOutputStream.writeUTF(stringArray2[0].toString());
                dataOutputStream.writeUTF(stringArray2[1].toString());
            }
            if (recordStore.getNumRecords() == 0) {
                recordStore.addRecord(byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.toByteArray().length);
            } else {
                recordStore.setRecord(1, byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.toByteArray().length);
            }
            recordStore.closeRecordStore();
            recordStore = null;
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void getMall() {
        try {
            RecordStore recordStore = RecordStore.openRecordStore(MALLINFO, true);
            if (recordStore.getNumRecords() != 0) {
                int n;
                int n2;
                byte[] byArray = recordStore.getRecord(1);
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byArray);
                DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
                Param.MALL_VERSION = dataInputStream.readShort();
                Param.strNotice = dataInputStream.readUTF();
                int n3 = dataInputStream.readByte();
                Param.MALL_TAB_LIST = new String[n3];
                for (n2 = 0; n2 < n3; ++n2) {
                    Param.MALL_TAB_LIST[n2] = dataInputStream.readUTF();
                }
                n2 = dataInputStream.readByte();
                Param.MALL_BOX_NUM = new byte[n2];
                for (n = 0; n < n2; ++n) {
                    Param.MALL_BOX_NUM[n] = dataInputStream.readByte();
                }
                n = dataInputStream.readByte();
                Param.MALL_EXTEND_LIST = new Vector(1, 1);
                for (int i = 0; i < n; ++i) {
                    String[] stringArray = new String[]{dataInputStream.readUTF(), dataInputStream.readUTF()};
                    Param.MALL_EXTEND_LIST.addElement(stringArray);
                }
            }
            recordStore.closeRecordStore();
            recordStore = null;
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void getUserNum() {
        try {
            RecordStore recordStore = RecordStore.openRecordStore(UserNum, true);
            if (recordStore.getNumRecords() != 0) {
                byte[] byArray = recordStore.getRecord(1);
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byArray);
                DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
                strRmsUserNum = dataInputStream.readUTF();
            }
            recordStore.closeRecordStore();
            recordStore = null;
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void savePlace(byte by) {
        blnPlace = true;
        if (by != -1) {
            bytPlace = by;
        } else {
            by = bytPlace;
        }
        try {
            RecordStore recordStore = RecordStore.openRecordStore(ROLEPLACE, true);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeByte(by);
            recordStore.addRecord(byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.toByteArray().length);
            if (recordStore.getNumRecords() == 0) {
                recordStore.addRecord(byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.toByteArray().length);
            } else {
                recordStore.setRecord(1, byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.toByteArray().length);
            }
            recordStore.closeRecordStore();
            recordStore = null;
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void loadPlace() {
        if (!blnPlace) {
            blnPlace = true;
            try {
                RecordStore recordStore = RecordStore.openRecordStore(ROLEPLACE, true);
                if (recordStore.getNumRecords() != 0) {
                    byte[] byArray = recordStore.getRecord(1);
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byArray);
                    DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
                    bytPlace = dataInputStream.readByte();
                }
                recordStore.closeRecordStore();
                recordStore = null;
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public static void saveSet(byte by, byte by2, byte by3, byte by4, byte by5, byte by6, byte by7) {
        blnSETSELECT = true;
        if (by != -1) {
            blnChatWChannel = by == 0;
        } else {
            by = (byte)(!blnChatWChannel ? 1 : 0);
        }
        if (by2 != -1) {
            blnWeather = by2 == 0;
        } else {
            by2 = (byte)(!blnWeather ? 1 : 0);
        }
        if (by3 != -1) {
            blnShowRole = by3 == 1;
        } else {
            by3 = (byte)(blnShowRole ? 1 : 0);
        }
        if (Macro.bytGameType == 1) {
            if (blnShowRole) {
                GameUI.getInstance().delRoleStateIcon((short)3);
            } else {
                if (Param.getInstance().oSelectRole != null) {
                    ORPMe.ME.delSelsectRole();
                }
                GameUI.getInstance().pushRoleStateIcon((short)3);
            }
        }
        if (by4 != -1) {
            bytLevalFunctionFlag = by4;
        } else {
            by4 = bytLevalFunctionFlag;
        }
        GameControl.getInstance().checkLevelFunction();
        if (by5 != -1) {
            bytShowName = by5;
        } else {
            by5 = bytShowName;
        }
        if (by6 != -1) {
            blnAutoWalk = by6 == 0;
        } else {
            by6 = (byte)(!blnAutoWalk ? 1 : 0);
        }
        if (by7 != -1) {
            blnAutoSelect = by7 == 0;
        } else {
            by7 = (byte)(!blnAutoSelect ? 1 : 0);
        }
        try {
            Store store = Store.get(SETSELETC, 1);
            store.open(true);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeByte(by);
            dataOutputStream.writeByte(by2);
            dataOutputStream.writeByte(by3);
            dataOutputStream.writeByte(by4);
            dataOutputStream.writeByte(by5);
            dataOutputStream.writeByte(by6);
            dataOutputStream.writeByte(by7);
            if (store.getNumRecords() < 1) {
                store.addRecord(byteArrayOutputStream.toByteArray());
            } else {
                store.setRecord(1, byteArrayOutputStream.toByteArray());
            }
            blnSETSELECT = true;
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void loadSet() {
        blnIsRead = false;
        if (!blnSETSELECT) {
            blnSETSELECT = true;
            try {
                Store store = Store.get(SETSELETC, 1);
                store.open(true);
                if (store.getNumRecords() != 0) {
                    blnIsRead = true;
                    byte[] byArray = store.getRecord(1);
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byArray);
                    DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
                    blnChatWChannel = dataInputStream.readByte() == 0;
                    blnWeather = dataInputStream.readByte() == 0;
                    blnShowRole = dataInputStream.readByte() == 1;
                    bytLevalFunctionFlag = dataInputStream.readByte();
                    bytShowName = dataInputStream.readByte();
                    blnAutoWalk = dataInputStream.readByte() == 0;
                    blnAutoSelect = dataInputStream.readByte() == 0;
                }
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public static void saveMacroChat() {
        try {
            RecordStore recordStore = RecordStore.openRecordStore(MACROCHAT, true);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            int n = strsMacroChat != null ? strsMacroChat.length : 0;
            dataOutputStream.writeByte(n);
            for (int n2 = 0; n2 < n; n2 = (int)((byte)(n2 + 1))) {
                dataOutputStream.writeUTF(strsMacroChat[n2]);
            }
            if (recordStore.getNumRecords() == 0) {
                recordStore.addRecord(byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.toByteArray().length);
            } else {
                recordStore.setRecord(1, byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.toByteArray().length);
            }
            recordStore.closeRecordStore();
            recordStore = null;
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static String[] loadMacroChat() {
        String[] stringArray = null;
        try {
            RecordStore recordStore = RecordStore.openRecordStore(MACROCHAT, true);
            if (recordStore.getNumRecords() != 0) {
                byte[] byArray = recordStore.getRecord(1);
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byArray);
                DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
                byte by = dataInputStream.readByte();
                stringArray = new String[by];
                for (byte by2 = 0; by2 < by; by2 = (byte)(by2 + 1)) {
                    stringArray[by2] = dataInputStream.readUTF();
                }
            }
            recordStore.closeRecordStore();
            recordStore = null;
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return stringArray;
    }

    public static void delRMS(String string) {
        try {
            RecordStore recordStore = RecordStore.openRecordStore(string, true);
            recordStore.closeRecordStore();
            RecordStore.deleteRecordStore(string);
        }
        catch (RecordStoreNotOpenException recordStoreNotOpenException) {
            recordStoreNotOpenException.printStackTrace();
        }
        catch (RecordStoreException recordStoreException) {
            recordStoreException.printStackTrace();
        }
    }

    static {
        bytPlace = 0;
        blnSETSELECT = false;
        blnSelectHttp = false;
        blnSelectCMWAP = false;
        bytEffect = 0;
        bytPortrait = 0;
        blnBusiness = true;
        blnTeam = true;
        bytChatRow = 0;
        blnChatWChannel = true;
        blnWeather = true;
        bytShowName = 0;
        blnAutoWalk = false;
        blnAutoSelect = true;
        bytShowMap = 0;
        bytLevalFunctionFlag = 1;
        blnShowKeyInfo = false;
        blnShowRole = true;
    }
}
