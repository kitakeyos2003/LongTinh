// Decompiled with: CFR 0.151
// Class Version: 3
package means;

import base.Macro;
import base.Param;
import common.Common;
import face.GameUI;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.rms.RecordStore;
import means.DebugFrame;
import means.QSprite;
import means.QSpriteData;
import means.QSpritePng;
import model.ONpc;
import model.ORMonster;
import model.ORPlayer;
import model.ORole;
import model.ResPoolFactory;
import network.NetManager;

public class ImageManager {
    private static ImageManager im;
    public static Hashtable htDownloadList;
    private static final String[] RES_NAME_ALL;
    public static Hashtable htRsAllImg;
    private static int coutNumError;
    static StringBuffer _buff;
    public static final int POOL_GLOBAL = 0;
    public static final int POOL_MENU = 1;
    public static final int POOL_LOADING = 2;
    public static final int POOL_INGAME_TEMP = 3;
    public static final int POOL_ROLE = 4;
    public static final int POOL_EQUIP = 5;
    public static final int POOL_WEAPON = 6;
    public static final int POOL_SKILL = 7;
    public static final int POOL_COUNT = 8;

    private ImageManager() {
        if (htDownloadList == null) {
            htDownloadList = new Hashtable(50);
        }
        for (int i = 0; i < RES_NAME_ALL.length; ++i) {
            String string = RES_NAME_ALL[i];
            Hashtable hashtable = null;
            if (!htRsAllImg.containsKey(string)) {
                hashtable = new Hashtable();
                htRsAllImg.put(string, hashtable);
            } else {
                hashtable = (Hashtable)htRsAllImg.get(string);
            }
            ImageManager.initRMList(string);
        }
    }

    public static ImageManager getInstance() {
        if (im == null) {
            im = new ImageManager();
        }
        return im;
    }

    public static void addRequestRes(String string) {
        if (string == null) {
            return;
        }
        if (htDownloadList == null) {
            htDownloadList = new Hashtable(50);
        }
        if (!htDownloadList.containsKey(string)) {
            if (Macro.bytGameTypeReal != 1) {
                return;
            }
            DebugFrame.getInstance().logIn("[Send]download start：" + string);
            ImageManager.sendResquetResMsg(string);
            htDownloadList.put(string, string);
        }
    }

    private static void sendResquetResMsg(String string) {
        try {
            DataOutputStream dataOutputStream = NetManager.getDOS((short)23832);
            dataOutputStream.writeUTF(string);
            dataOutputStream.writeByte(Macro.CLIENTTYPE);
            NetManager.getInstance().addPacket();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public static void removeRequestRes(String string) {
        if (string == null) {
            return;
        }
        if (htDownloadList.containsKey(string)) {
            htDownloadList.remove(string);
        }
        DebugFrame.getInstance().logIn("[Recv]download end:" + string);
    }

    public static Hashtable getRmList(String string) {
        Hashtable hashtable = null;
        if (htRsAllImg.containsKey(string)) {
            hashtable = (Hashtable)htRsAllImg.get(string);
        }
        return hashtable;
    }

    public static synchronized boolean saveResRecordStore(String string, byte[] byArray) {
        block11: {
            String string2 = ImageManager.parseResDirName(string);
            Hashtable hashtable = ImageManager.getRmList(string2);
            if (string2 == null || hashtable == null) {
                return false;
            }
            RecordStore recordStore = null;
            try {
                boolean bl;
                recordStore = RecordStore.openRecordStore(string2, true);
                if (recordStore.getSizeAvailable() <= Macro.RMS_MIN_FREE_MEMORY_SIZE) break block11;
                int n = -1;
                try {
                    n = recordStore.getNumRecords() == 0 ? recordStore.getNextRecordID() + 1 : recordStore.getNextRecordID();
                    recordStore.addRecord(byArray, 0, byArray.length);
                }
                catch (Exception exception) {
                    String string3 = "Exception: saveResRecordStore: 添加资源本身异常，strRsPath" + string + "curRecordId:" + n;
                    DebugFrame.getInstance().logIn(string3);
                    exception.printStackTrace();
                    if (recordStore != null) {
                        recordStore.closeRecordStore();
                    }
                    return false;
                }
                if (recordStore != null) {
                    recordStore.closeRecordStore();
                }
                if (!(bl = ImageManager.saveResRecordListStore(string, (byte)1, n))) {
                    ImageManager.cleanRMSRecord(string2, n);
                }
                return bl;
            }
            catch (Exception exception) {
                String string4 = "Exception: 打开记录集异常: strRsPath" + string;
                DebugFrame.getInstance().logIn(string4);
                exception.printStackTrace();
                try {
                    if (recordStore != null) {
                        recordStore.closeRecordStore();
                        return false;
                    }
                    return false;
                }
                catch (Exception exception2) {
                    string4 = "Exception: 打开RMS异常，在捕获后关闭时又异常: strRsPath" + string2;
                    DebugFrame.getInstance().logIn(string4);
                    exception2.printStackTrace();
                }
            }
        }
        return false;
    }

    public static String parseResDirName(String string) {
        String string2 = "";
        int n = string.indexOf("/", 1);
        if (n != -1) {
            string2 = string.substring(1, n);
        } else {
            String string3 = "Error! 解析资源路径，获取资源目录出错！" + string;
            DebugFrame.getInstance().logIn(string3);
        }
        return string2;
    }

    public boolean isResNeedReLoad(String string) {
        boolean bl = true;
        String string2 = ImageManager.parseResDirName(string);
        Hashtable hashtable = ImageManager.getRmList(string2);
        if (hashtable != null && hashtable.containsKey(string)) {
            int[] nArray = (int[])hashtable.get(string);
            int n = nArray[0];
            int n2 = nArray[1];
            if (n == 1) {
                bl = true;
            } else if (n == 2) {
                bl = false;
            }
        } else {
            bl = true;
        }
        return bl;
    }

    public static boolean saveResRecordListStore(String string, byte by, int n) {
        String string2 = ImageManager.parseResDirName(string);
        Hashtable hashtable = ImageManager.getRmList(string2);
        if (string2 == null || hashtable == null) {
            return true;
        }
        RecordStore recordStore = null;
        try {
            recordStore = RecordStore.openRecordStore(string2, true);
            try {
                if (recordStore.getSizeAvailable() > Macro.RMS_MIN_FREE_MEMORY_SIZE) {
                    hashtable.put(string, new int[]{by, n});
                    byte[] byArray = new byte[]{};
                    Enumeration enumeration = hashtable.keys();
                    while (enumeration.hasMoreElements()) {
                        String string3 = (String)enumeration.nextElement();
                        int[] nArray = (int[])hashtable.get(string3);
                        byte[] byArray2 = Common.getByteArrayFromInt(nArray[1]);
                        byte[] byArray3 = string3.getBytes();
                        byte[] byArray4 = new byte[byArray.length + byArray3.length + 1 + 1 + 4];
                        System.arraycopy(byArray, 0, byArray4, 0, byArray.length);
                        System.arraycopy(byArray3, 0, byArray4, byArray.length, byArray3.length);
                        byArray4[byArray4.length - 6] = 10;
                        byArray4[byArray4.length - 5] = (byte)nArray[0];
                        byArray4[byArray4.length - 4] = byArray2[0];
                        byArray4[byArray4.length - 3] = byArray2[1];
                        byArray4[byArray4.length - 2] = byArray2[2];
                        byArray4[byArray4.length - 1] = byArray2[3];
                        byArray = byArray4;
                    }
                    if (recordStore.getNumRecords() == 0) {
                        recordStore.addRecord(byArray, 0, byArray.length);
                    } else {
                        recordStore.setRecord(1, byArray, 0, byArray.length);
                    }
                    if (recordStore != null) {
                        recordStore.closeRecordStore();
                    }
                }
                return true;
            }
            catch (Exception exception) {
                hashtable.remove(string);
                String string4 = "Exception: saveResRecordStore: 添加资源索引时异常，strRsPath" + string + "curRecordId:" + n;
                DebugFrame.getInstance().logIn(string4);
                if (recordStore != null) {
                    recordStore.closeRecordStore();
                }
                return false;
            }
        }
        catch (Exception exception) {
            String string5 = "Exception: 打开记录集异常: rmDirName:" + string2 + "/strPath:" + string;
            DebugFrame.getInstance().logIn(string5);
            exception.printStackTrace();
            try {
                if (recordStore != null) {
                    recordStore.closeRecordStore();
                }
            }
            catch (Exception exception2) {
                string5 = "Exception: 打开RMS异常，在捕获后关闭时又异常: rmDirName:" + string2 + "/strPath:" + string;
                DebugFrame.getInstance().logIn(string5);
                exception2.printStackTrace();
            }
            return false;
        }
    }

    public static byte[] getResRecordStore(int n, String string, Hashtable hashtable) {
        String string2 = ImageManager.parseResDirName(string);
        RecordStore recordStore = null;
        byte[] byArray = null;
        try {
            recordStore = RecordStore.openRecordStore(string2, true);
            try {
                byArray = recordStore.getRecord(n);
            }
            catch (Exception exception) {
                if (recordStore != null) {
                    recordStore.closeRecordStore();
                }
                String string3 = "Exception: 从rms中提取数据时异常，将资源索引重载至内存" + string2;
                DebugFrame.getInstance().logIn(string3);
                ImageManager.initRMList(string2);
                return null;
            }
            recordStore.closeRecordStore();
            return byArray;
        }
        catch (Exception exception) {
            String string4 = "Exception: 打开RMS异常或数据错误: strRsPath" + string2;
            DebugFrame.getInstance().logIn(string4);
            exception.printStackTrace();
            try {
                if (recordStore != null) {
                    recordStore.closeRecordStore();
                }
            }
            catch (Exception exception2) {
                string4 = "Exception: 打开RMS异常，在捕获后关闭时又异常: strRsPath" + string2;
                DebugFrame.getInstance().logIn(string4);
                exception2.printStackTrace();
            }
            return null;
        }
    }

    private static int getRecordStoreNumByType(Hashtable hashtable, byte by) {
        int n = 0;
        if (hashtable != null && !hashtable.isEmpty()) {
            Enumeration enumeration = hashtable.elements();
            while (enumeration.hasMoreElements()) {
                int[] nArray = (int[])enumeration.nextElement();
                if (nArray[0] != by) continue;
                ++n;
            }
        }
        return n;
    }

    public static boolean cleanRMSRecord(String string, int n) {
        RecordStore recordStore = null;
        try {
            recordStore = RecordStore.openRecordStore(string, true);
            recordStore.deleteRecord(n);
            recordStore.closeRecordStore();
            return true;
        }
        catch (Exception exception) {
            String string2 = "Exception: cleanRMSRecord 打开记录集异常: rmDirName:" + string + "/recordId:" + n;
            DebugFrame.getInstance().logIn(string2);
            exception.printStackTrace();
            try {
                if (recordStore != null) {
                    recordStore.closeRecordStore();
                }
            }
            catch (Exception exception2) {
                string2 = "Exception: cleanRMSRecord 打开RMS异常，在捕获后关闭时又异常: rmDirName:" + string + "/recordId:" + n;
                DebugFrame.getInstance().logIn(string2);
                exception2.printStackTrace();
            }
            return false;
        }
    }

    public static void cleanAllRMS() {
        try {
            htRsAllImg.clear();
            String[] stringArray = RecordStore.listRecordStores();
            if (stringArray != null) {
                for (int i = 0; i < stringArray.length; ++i) {
                    for (int j = 0; j < stringArray.length; ++j) {
                        if (stringArray[i].equals(RES_NAME_ALL[j])) continue;
                        RecordStore.deleteRecordStore(stringArray[i]);
                    }
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public static void initRMList(String string) {
        Hashtable hashtable = ImageManager.getRmList(string);
        hashtable.clear();
        RecordStore recordStore = null;
        try {
            recordStore = RecordStore.openRecordStore(string, true);
            if (recordStore.getNumRecords() > 0) {
                byte[] byArray = recordStore.getRecord(1);
                if (byArray == null) {
                    String string2 = "Warning!: 获取资源索引时, 返回为空，rmDirName： " + string;
                    DebugFrame.getInstance().logIn(string2);
                    recordStore.closeRecordStore();
                    return;
                }
                int n = 0;
                for (int i = 0; i < byArray.length; ++i) {
                    if (byArray[i] != 10) continue;
                    if (i > n) {
                        byte[] byArray2 = new byte[i - n];
                        System.arraycopy(byArray, n, byArray2, 0, byArray2.length);
                        String string3 = new String(byArray2, 0, byArray2.length);
                        int n2 = byArray[++i];
                        int n3 = Common.readIntFromByte(byArray, ++i);
                        i += 3;
                        hashtable.put(string3, new int[]{n2, n3});
                    }
                    n = i + 1;
                }
            }
            recordStore.closeRecordStore();
        }
        catch (Exception exception) {
            String string4 = "Exception: initRMList: 获取资源索引时异常，rmDirName" + string + "vctRm.size():" + hashtable.size();
            DebugFrame.getInstance().logIn(string4);
            exception.printStackTrace();
            try {
                if (recordStore != null) {
                    recordStore.closeRecordStore();
                }
            }
            catch (Exception exception2) {
                string4 = "Exception: 打开RMS或关闭或读取数据时异常，在捕获后关闭时又异常: rmDirName" + string;
                DebugFrame.getInstance().logIn(string4);
                exception2.printStackTrace();
            }
        }
    }

    public static byte[] getResDataByteArray(String string) {
        String string2 = ImageManager.parseResDirName(string);
        Hashtable hashtable = ImageManager.getRmList(string2);
        String string3 = string;
        byte[] byArray = null;
        if (hashtable != null && hashtable.containsKey(string)) {
            string3 = String.valueOf(string3) + "本地RMS有记录，存储位置：";
            int[] nArray = (int[])hashtable.get(string);
            int n = nArray[0];
            int n2 = nArray[1];
            if (n == 1) {
                string3 = String.valueOf(string3) + "RMS，";
                byArray = ImageManager.getResRecordStore(n2, string, hashtable);
            }
        } else {
            string3 = String.valueOf(string3) + "本地RMS无记录,存储位置：";
            byArray = ImageManager.readFile(string);
            if (byArray == null) {
                string3 = String.valueOf(string3) + "服务器，";
                if (string2 != null && hashtable != null) {
                    ImageManager.saveResRecordListStore(string, (byte)2, 0);
                    string3 = String.valueOf(string3) + "，记录至RMS，";
                } else {
                    string3 = String.valueOf(string3) + "本地读取失败，无需记录至RMS，";
                }
            }
        }
        if (byArray == null && string2 != null && hashtable != null) {
            string3 = "";
            ImageManager.addRequestRes(string);
        }
        if (Macro.BLN_RES_CLASSIFY_SAVE_FUNCTION_DEBUG_INFO) {
            DebugFrame.getInstance().logIn(string3);
        }
        return byArray;
    }

    public static byte[] readFile(String string) {
        byte[] byArray = new byte[128];
        byte[] byArray2 = null;
        try {
            InputStream inputStream = new String().getClass().getResourceAsStream(string);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int n = 0;
            while ((n = inputStream.read(byArray)) != -1) {
                byteArrayOutputStream.write(byArray, 0, n);
            }
            byArray2 = byteArrayOutputStream.toByteArray();
        }
        catch (Exception exception) {
            return null;
        }
        return byArray2;
    }

    public boolean checkClothing() {
        int n = 0;
        boolean bl = false;
        boolean bl2 = false;
        for (int n2 = 0; n2 < Param.getInstance().shtsClothingId.length; n2 = (int)((byte)(n2 + 1))) {
            if (Param.getInstance().shtsClothingId[n2][0] < 0) {
                ++n;
                continue;
            }
            if (Param.getInstance().shtsClothingId[n2][0] == 525) {
                bl = true;
                continue;
            }
            if (Param.getInstance().shtsClothingId[n2][0] != 526) continue;
            bl2 = true;
        }
        return n > 2 || n == 2 && (bl || bl2) || n == 1 && bl && bl2;
    }

    public byte addImage(short[][] sArray, Image[] imageArray, short s, String string) {
        byte by = this.getImgId(sArray, s);
        if (by == -1) {
            for (byte by2 = 0; by2 < sArray.length; by2 = (byte)((byte)(by2 + 1))) {
                if (sArray[by2][0] > 0) continue;
                sArray[by2][0] = s;
                sArray[by2][1] = 1;
                imageArray[by2] = ImageManager.CreateImage(String.valueOf(s), string);
                return by2;
            }
        } else if (sArray[by][1] > 0) {
            short[] sArray2 = sArray[by];
            boolean bl = true;
            sArray2[1] = (short)(sArray2[1] + 1);
        }
        return by;
    }

    public byte addImage(short[][] sArray, Image[] imageArray, short s, Image image) {
        byte by = this.getImgId(sArray, s);
        if (by == -1) {
            for (byte by2 = 0; by2 < sArray.length; by2 = (byte)((byte)(by2 + 1))) {
                if (sArray[by2][0] >= 0) continue;
                sArray[by2][0] = s;
                sArray[by2][1] = 1;
                imageArray[by2] = image;
                return by2;
            }
        } else if (sArray[by][1] > 0) {
            short[] sArray2 = sArray[by];
            boolean bl = true;
            sArray2[1] = (short)(sArray2[1] + 1);
        }
        return by;
    }

    public void delImage(short[][] sArray, Image[] imageArray, short s) {
        if (sArray[s][0] > 0) {
            short[] sArray2 = sArray[s];
            boolean bl = true;
            sArray2[1] = (short)(sArray2[1] - 1);
            if (sArray[s][1] == 0) {
                sArray[s][0] = -1;
                imageArray[s] = null;
            }
        }
    }

    public byte getImgId(short[][] sArray, short s) {
        for (byte by = 0; by < sArray.length; by = (byte)(by + 1)) {
            if (sArray[by][0] != s) continue;
            return by;
        }
        return -1;
    }

    public byte addImageNum(short[][] sArray, short s) {
        byte by = this.getImgId(sArray, s);
        if (by != -1 && sArray[by][1] != -1) {
            short[] sArray2 = sArray[by];
            boolean bl = true;
            sArray2[1] = (short)(sArray2[1] + 1);
        }
        return by;
    }

    public void drawPropAndNum(Graphics graphics, short s, short s2, int n, int n2) {
        Image image = GameUI.getInstance().getObjectImg(s);
        if (image != null) {
            graphics.drawImage(image, n, n2, 20);
            if (s2 > 1 || Param.getInstance().bytSelectType == 1 && s2 > 0) {
                GameUI.getInstance().drawWhiteNum(graphics, s2, n, n2 + 11);
            }
        }
    }

    public void addDeadImg() {
        Param param = Param.getInstance();
        param.bytDeadImgNum = (byte)(param.bytDeadImgNum + 1);
        if (Param.getInstance().imgDead == null) {
            Param.getInstance().imgDead = ImageManager.CreateImage("die", "ingame");
        }
    }

    public void delDeadImg() {
        Param param = Param.getInstance();
        param.bytDeadImgNum = (byte)(param.bytDeadImgNum - 1);
        if (Param.getInstance().bytDeadImgNum <= 0) {
            Param.getInstance().bytDeadImgNum = 0;
            Param.getInstance().imgDead = null;
        }
    }

    public void addNpcImage(short[] sArray) {
        if (Param.getInstance().shtsNewNPCId == null) {
            Param.getInstance().shtsNewNPCId = new Hashtable();
        }
        if (Param.getInstance().shtsNewNPCId.get(new Short(sArray[0])) == null) {
            Param.getInstance().shtsNewNPCId.put(new Short(sArray[0]), sArray);
        }
    }

    public static short addNpcImage(short s, int n) {
        return ((short[])Param.getInstance().shtsNewNPCId.get(new Short(s)))[n];
    }

    public void addMonsterImage(short[] sArray) {
        if (Param.getInstance().shtsNewMonsterId == null) {
            Param.getInstance().shtsNewMonsterId = new Hashtable();
        }
        if (Param.getInstance().shtsNewMonsterId.get(new Short(sArray[0])) == null) {
            Param.getInstance().shtsNewMonsterId.put(new Short(sArray[0]), sArray);
        }
    }

    public static short addMonsterImage(short s, int n) {
        return ((short[])Param.getInstance().shtsNewMonsterId.get(new Short(s)))[n];
    }

    public void addTaskImage(short[] sArray) {
        if (Param.getInstance().shtsNewTaskId == null) {
            Param.getInstance().shtsNewTaskId = new Hashtable();
        }
        if (Param.getInstance().shtsNewTaskId.get(new Short(sArray[0])) == null) {
            Param.getInstance().shtsNewTaskId.put(new Short(sArray[0]), sArray);
        }
    }

    public static short addTaskImage(short s, int n) {
        return ((short[])Param.getInstance().shtsNewTaskId.get(new Short(s)))[n];
    }

    public void delPetImage(byte by) {
        int n = Integer.parseInt(Param.getInstance().shtsPetId[by][1]);
        int n2 = n - 1;
        Param.getInstance().shtsPetId[by][1] = String.valueOf(n2);
        if (Param.getInstance().shtsPetId[by][1] == "0") {
            if (Param.getInstance().imgPetIndex.length == 1) {
                Param.getInstance().imgPetIndex = null;
                Param.getInstance().shtsPetId = null;
            } else {
                Param.getInstance().imgPetIndex[by] = null;
                Param.getInstance().shtsPetId[by][0] = "0";
            }
        }
    }

    public static Image CreateImage(String string, String string2) {
        Image image = null;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            if (string2 != null) {
                stringBuffer.append("/");
                stringBuffer.append(string2);
            }
            stringBuffer.append("/");
            stringBuffer.append(string);
            stringBuffer.append(".png");
            int[] nArray = ImageManager.getPoolAndResType(stringBuffer.toString());
            if (nArray != null && nArray[0] >= 0 && nArray[1] >= 0) {
                if (nArray[1] == 1) {
                    image = ImageManager.getPng(nArray[0], stringBuffer.toString());
                }
            } else {
                byte[] byArray = ImageManager.getResDataByteArray(stringBuffer.toString());
                if (byArray != null) {
                    image = Image.createImage(byArray, 0, byArray.length);
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return image;
    }

    public static String EncodespriteId(String string, String string2) {
        return String.valueOf(string) + "/" + string2;
    }

    public static int[] DecodespriteId(String string) {
        int[] nArray = new int[2];
        boolean bl = false;
        boolean bl2 = false;
        nArray[0] = 0;
        nArray[1] = 0;
        return nArray;
    }

    public static QSprite loadSpriteById(int n, String string, String string2, String string3, String string4) {
        return ImageManager.loadSpriteById(n, string, string2, string3, string4, true, true);
    }

    public static QSprite loadSpriteById(int n, String string, String string2, String string3, String string4, boolean bl, boolean bl2) {
        if (string.equals("-1") || string3.equals("-1")) {
            DebugFrame.getInstance().logIn("loadSpriteById Argument error! spriteid/pngid/anuid/path" + string + "/" + string3 + "/" + string2 + "/" + string4);
            return null;
        }
        try {
            QSprite qSprite = null;
            String string5 = "/" + string4 + "/" + string2 + ".anu";
            if (bl) {
                ImageManager.loadSpriteDataById(n, string5);
            }
            String string6 = "/" + string4 + "/" + string3 + ".png";
            if (bl2) {
                ImageManager.loadSpritePngById(n, string6);
            }
            qSprite = new QSprite(string, string5, string6, string4, n);
            return qSprite;
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public static boolean loadSpriteDataById(int n, String string) {
        return ImageManager.loadSpriteDataById(n, string, 0);
    }

    public static boolean loadSpriteDataById(int n, String string, int n2) {
        boolean bl = false;
        if (string.equals("")) {
            DebugFrame.getInstance().logIn("loadSpriteAnu Argument error!");
            return bl;
        }
        bl = ResPoolFactory.getInstance().isResExist(n, string) || ResPoolFactory.getInstance().pool_insert(n, string, n2, (byte)2);
        return bl;
    }

    public static boolean loadSpritePngById(int n, String string) {
        return ImageManager.loadSpritePngById(n, string, 0);
    }

    public static boolean loadSpritePngById(int n, String string, int n2) {
        boolean bl = false;
        if (string.equals("")) {
            DebugFrame.getInstance().logIn("loadSpritePng Argument error!");
            return bl;
        }
        bl = ResPoolFactory.getInstance().isResExist(n, string) || ResPoolFactory.getInstance().pool_insert(n, string, n2, (byte)1);
        return bl;
    }

    public static QSpriteData CreatSpriteData(int n, String string) {
        try {
            QSpriteData qSpriteData = QSpriteData.load(n, string);
            return qSpriteData;
        }
        catch (Exception exception) {
            return null;
        }
    }

    public static QSpritePng CreatSpritePng(int n, String string) {
        try {
            QSpritePng qSpritePng = QSpritePng.load(n, string);
            return qSpritePng;
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public static QSpriteData getData(int n, String string) {
        QSpriteData qSpriteData = (QSpriteData)ResPoolFactory.getInstance().getRes(n, string);
        if (qSpriteData == null && ImageManager.getInstance().isResNeedReLoad(string)) {
            boolean bl = ImageManager.loadSpriteDataById(n, string);
            qSpriteData = (QSpriteData)ResPoolFactory.getInstance().getRes(n, string);
        }
        return qSpriteData;
    }

    public static Image getPng(int n, String string) {
        Image image = null;
        QSpritePng qSpritePng = (QSpritePng)ResPoolFactory.getInstance().getRes(n, string);
        if (qSpritePng == null && ImageManager.getInstance().isResNeedReLoad(string)) {
            boolean bl = ImageManager.loadSpritePngById(n, string);
            qSpritePng = (QSpritePng)ResPoolFactory.getInstance().getRes(n, string);
        }
        if (qSpritePng != null) {
            image = qSpritePng.getImage();
        }
        return image;
    }

    public static void SendServerGetRes(byte by, String string, String string2) {
    }

    public static int[] getPoolAndResType(String string) {
        int n = -1;
        int n2 = -1;
        int n3 = -1;
        if (string != null && !string.equals("")) {
            int n4 = string.length();
            String string2 = string.substring(n4 - 4, n4);
            if (string2.equals(".png")) {
                n2 = 1;
            } else if (string2.equals(".anu")) {
                n2 = 2;
            }
            int n5 = string.indexOf("/", 1);
            String string3 = string.substring(1, n5);
            if (n2 >= 1) {
                if (string3.equals("hair") || string3.equals("headwear") || string3.equals("naked") || string3.equals("pet") || string3.equals("equipeffect") || string3.equals("equipeffect") || string3.equals("weaponattack") || string3.equals("weaponeffect")) {
                    n = 4;
                } else if (string3.equals("equip")) {
                    n = 5;
                } else if (string3.equals("weapon")) {
                    n = 6;
                } else if (string3.equals("skill")) {
                    n = 7;
                } else if (string3.equals("npc") || string3.equals("decorate") || string3.equals("orge") || string3.equals("npc") || string3.equals("task") || string3.equals("building") || string3.equals("prop") || string3.equals("prop_v5") || string3.equals("sys") || string3.equals("sys_v5") || string3.equals("states") || string3.equals("ingame") || string3.equals("ui") || string3.equals("ui_v5")) {
                    n = 3;
                } else if (string3.equals("loading") || string3.equals("logo")) {
                    n = 1;
                }
                n5 = string.lastIndexOf(47);
                String string4 = string.substring(n5 + 1, string.length() - 4);
                try {
                    n3 = Integer.parseInt(string4);
                }
                catch (Exception exception) {
                    n3 = -1;
                }
            }
        }
        return new int[]{n, n2, n3};
    }

    public static boolean sendUpdateMsg(final String s, final int[] array) {
        final String resDirName = parseResDirName(s);
        if (array != null && array.length == 3 && array[0] > 0 && array[1] > 0 && array[2] > 0 && resDirName.equals("npc")) {
            try {
                if (Param.getInstance().htRoleNPC != null && !Param.getInstance().htRoleNPC.isEmpty()) {
                    Enumeration elements = Param.getInstance().htRoleNPC.elements();
                    while (elements.hasMoreElements()) {
                        ONpc oNpc = (ONpc) elements.nextElement();
                        if (oNpc != null && oNpc.blnUseDefault && ((array[1] == 1 && oNpc.shtPicId == array[2]) || (array[1] == 2 && oNpc.shtAnuId == array[2]))) {
                            oNpc.blnNeedUpdate = true;
                            oNpc.recvUpdateResMsg();
                        }
                    }
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (resDirName.equals("orge")) {
            try {
                if (Param.getInstance().htRoleMonster != null) {
                    Enumeration elements2 = Param.getInstance().htRoleMonster.elements();
                    while (elements2.hasMoreElements()) {
                        ORMonster orMonster = (ORMonster) elements2.nextElement();
                        if (orMonster != null && orMonster.blnUseDefault && ((array[1] == 1 && orMonster.shtPicId == array[2]) || (array[1] == 2 && orMonster.shtAnuId == array[2]))) {
                            orMonster.blnNeedUpdate = true;
                            orMonster.recvUpdateResMsg();
                        }
                    }
                }
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
            }
        }
        if (ORPlayer.getResPartByResPath(resDirName) >= 0 && Param.getInstance().htRolePlayer != null) {
            Enumeration elements3 = Param.getInstance().htRolePlayer.elements();
            while (elements3.hasMoreElements()) {
                ORPlayer orPlayer = (ORPlayer) elements3.nextElement();
                if (orPlayer != null) {
                    orPlayer.recvSyncRole(s, array);
                }
            }
        }
        if (Param.getInstance().playerColne != null) {
            Param.getInstance().playerColne.recvSyncRole(s, array);
        }
        if (Param.getInstance().partner != null) {
            Param.getInstance().partner.recvSyncRole(s, array);
        }
        return true;
    }

    static {
        RES_NAME_ALL = new String[]{"naked", "hair", "headwear", "pet", "equipeffect", "equipeffect", "weaponattack", "weaponeffect", "equip", "weapon", "skill", "npc", "decorate", "orge", "task", "building", "prop", "prop_v5", "sys", "sys_v5", "states", "ingame", "ui", "ui_v5", "loading", "logo"};
        htRsAllImg = new Hashtable();
        coutNumError = 0;
        _buff = new StringBuffer();
    }
}
