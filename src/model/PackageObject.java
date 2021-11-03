// 
// Decompiled by Procyon v0.5.36
// 
package model;

import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;

public class PackageObject {

    public int intPOindex;
    public int intId;
    public short shtIcon;
    public int intPrice;
    public int intPaiMaiPrice;
    public short shtLevel;
    public short shtPngId;
    public short shtAnuId;
    public short shortIcon;
    public short shtStage;
    public short shtType;
    public short shtLocal;
    public short bytSeal;
    public String strPropName;
    public String strName;
    public String strInfo;
    public byte bytType;
    public String strDate;
    public byte bytSign;
    public String strTitle;
    public String strContent;
    public String strExplain;
    public short shtPOnum;
    public byte bytPOnumMax;
    public int intColor;
    public byte bytQuality;
    public byte bytOperation;
    public byte bytIsBind;
    public boolean blnUse;
    public byte bytKey;
    public byte petShow;
    public int petKey;
    public byte petDead;
    public int value;
    public short shtStamina;
    public short shtStaminaMax;
    public byte bytAggrandizementLv;
    public byte[] bytsAggrandizement;
    public String strPveNumber;
    public String strPvpNumber;
    public byte byteEquipType;
    public byte byteEquipFixed;
    public byte byteEquipPart;
    public String strSuitName;
    public String strSuitInfo;
    public byte[] bytsAttribute;
    public short[] shtsAttributeInfo;
    public byte bytBagPlace;
    public short shtAddition;
    public byte[] holeDate;
    public byte holeNum;
    public String DescribeProperty;

    public PackageObject() {
        this.intPaiMaiPrice = -1;
        this.strPropName = "";
        this.strName = "";
        this.strInfo = "";
        this.bytType = 0;
        this.strDate = "";
        this.bytSign = 0;
        this.strTitle = "";
        this.strContent = "";
        this.strExplain = "";
        this.shtPOnum = 1;
        this.bytPOnumMax = 1;
        this.intColor = 0;
        this.bytQuality = 0;
        this.bytOperation = 1;
        this.bytKey = -1;
        this.petShow = -1;
        this.petKey = -1;
        this.petDead = -1;
        this.value = 0;
        this.strPveNumber = "";
        this.strPvpNumber = "";
        this.byteEquipFixed = -1;
        this.strSuitName = "";
        this.strSuitInfo = "";
        this.bytBagPlace = -1;
    }

    public PackageObject(final byte[] rec) {
        this.intPaiMaiPrice = -1;
        this.strPropName = "";
        this.strName = "";
        this.strInfo = "";
        this.bytType = 0;
        this.strDate = "";
        this.bytSign = 0;
        this.strTitle = "";
        this.strContent = "";
        this.strExplain = "";
        this.shtPOnum = 1;
        this.bytPOnumMax = 1;
        this.intColor = 0;
        this.bytQuality = 0;
        this.bytOperation = 1;
        this.bytKey = -1;
        this.petShow = -1;
        this.petKey = -1;
        this.petDead = -1;
        this.value = 0;
        this.strPveNumber = "";
        this.strPvpNumber = "";
        this.byteEquipFixed = -1;
        this.strSuitName = "";
        this.strSuitInfo = "";
        this.bytBagPlace = -1;
        final ByteArrayInputStream bais = new ByteArrayInputStream(rec);
        final DataInputStream dis = new DataInputStream(bais);
        try {
            this.intPOindex = dis.readInt();
            this.intId = dis.readInt();
            this.shtIcon = dis.readShort();
            this.intPrice = dis.readInt();
            this.intPaiMaiPrice = dis.readShort();
            this.shtLevel = dis.readShort();
            this.shtPngId = dis.readShort();
            this.shtAnuId = dis.readShort();
            this.shortIcon = dis.readShort();
            this.shtStage = dis.readShort();
            this.shtType = dis.readShort();
            this.shtLocal = dis.readShort();
            this.bytSeal = dis.readShort();
            this.strPropName = dis.readUTF();
            this.strName = dis.readUTF();
            this.strInfo = dis.readUTF();
            this.bytType = dis.readByte();
            this.strDate = dis.readUTF();
            this.bytSign = dis.readByte();
            this.strTitle = dis.readUTF();
            this.strContent = dis.readUTF();
            this.strExplain = dis.readUTF();
            this.shtPOnum = dis.readShort();
            this.bytPOnumMax = dis.readByte();
            this.intColor = dis.readInt();
            this.bytQuality = dis.readByte();
            this.bytOperation = dis.readByte();
            this.bytIsBind = dis.readByte();
            this.blnUse = dis.readBoolean();
            this.bytKey = dis.readByte();
            this.petShow = dis.readByte();
            this.petKey = dis.readInt();
            this.petDead = dis.readByte();
            this.value = dis.readInt();
            this.shtStamina = dis.readShort();
            this.shtStaminaMax = dis.readShort();
            this.bytAggrandizementLv = dis.readByte();
            this.strPveNumber = dis.readUTF();
            this.strPvpNumber = dis.readUTF();
            this.byteEquipType = dis.readByte();
            this.byteEquipFixed = dis.readByte();
            this.byteEquipPart = dis.readByte();
            this.strSuitName = dis.readUTF();
            this.strSuitInfo = dis.readUTF();
            for (int lenth = dis.readInt(), i = 0; i < lenth; ++i) {
                this.bytsAttribute[i] = dis.readByte();
            }
            for (int lenthInfo = dis.readInt(), j = 0; j < lenthInfo; ++j) {
                this.shtsAttributeInfo[j] = dis.readShort();
            }
            this.bytBagPlace = dis.readByte();
            this.shtAddition = dis.readShort();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public byte[] toBytes() {
        byte[] data = null;
        try {
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final DataOutputStream dos = new DataOutputStream(baos);
            dos.writeInt(this.intPOindex);
            dos.writeInt(this.intId);
            dos.writeShort(this.shtIcon);
            dos.writeInt(this.intPrice);
            dos.writeShort(this.intPaiMaiPrice);
            dos.writeShort(this.shtLevel);
            dos.writeShort(this.shtPngId);
            dos.writeShort(this.shtAnuId);
            dos.writeShort(this.shortIcon);
            dos.writeShort(this.shtStage);
            dos.writeShort(this.shtType);
            dos.writeShort(this.shtLocal);
            dos.writeShort(this.bytSeal);
            dos.writeUTF(this.strPropName);
            dos.writeUTF(this.strName);
            dos.writeUTF(this.strInfo);
            dos.writeByte(this.bytType);
            dos.writeUTF(this.strDate);
            dos.writeByte(this.bytSign);
            dos.writeUTF(this.strTitle);
            dos.writeUTF(this.strContent);
            dos.writeUTF(this.strExplain);
            dos.writeShort(this.shtPOnum);
            dos.writeByte(this.bytPOnumMax);
            dos.writeInt(this.intColor);
            dos.writeByte(this.bytQuality);
            dos.writeByte(this.bytOperation);
            dos.writeByte(this.bytIsBind);
            dos.writeBoolean(this.blnUse);
            dos.writeByte(this.bytKey);
            dos.writeByte(this.petShow);
            dos.writeInt(this.petKey);
            dos.writeByte(this.petDead);
            dos.writeInt(this.value);
            dos.writeShort(this.shtStamina);
            dos.writeShort(this.shtStaminaMax);
            dos.writeByte(this.bytAggrandizementLv);
            dos.writeUTF(this.strPveNumber);
            dos.writeUTF(this.strPvpNumber);
            dos.writeByte(this.byteEquipType);
            dos.writeByte(this.byteEquipFixed);
            dos.writeByte(this.byteEquipPart);
            dos.writeUTF(this.strSuitName);
            dos.writeUTF(this.strSuitInfo);
            int lenth = 0;
            if (this.bytsAttribute != null) {
                lenth = this.bytsAttribute.length;
            }
            dos.writeInt(lenth);
            for (int i = 0; i < lenth; ++i) {
                dos.writeByte(this.bytsAttribute[i]);
            }
            int lenthInfo = 0;
            if (this.shtsAttributeInfo != null) {
                lenthInfo = this.shtsAttributeInfo.length;
            }
            dos.writeInt(lenthInfo);
            for (int j = 0; j < lenthInfo; ++j) {
                dos.writeShort(this.shtsAttributeInfo[j]);
            }
            dos.writeByte(this.bytBagPlace);
            dos.writeShort(this.shtAddition);
            data = baos.toByteArray();
            baos.close();
            dos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
