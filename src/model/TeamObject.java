// 
// Decompiled by Procyon v0.5.36
// 
package model;

public class TeamObject {

    public int intId;
    public byte bytOcc;
    public short shtLevel;
    public boolean blnIsMale;
    public String strName;
    public byte bytTeamId;
    public byte bytType;
    public byte bytTroopRank;
    public boolean blnIsOnLine;
    public byte bytRelation;
    public byte bytHead;
    public int intNowBlood;
    public int intMaxBlood;
    public int intNowMagic;
    public int intMaxMagic;
    public boolean blnIsDeath;
    public int priority;
    public byte ranking;
    public int rankValue;
    public String occupation;

    public TeamObject() {
        this.strName = "";
        this.bytType = -1;
        this.bytTroopRank = -1;
        this.bytRelation = -1;
        this.intNowBlood = -1;
        this.intMaxBlood = -1;
        this.intNowMagic = -1;
        this.intMaxMagic = -1;
    }

    public void setOcc(final byte _occ) {
        this.bytOcc = _occ;
    }
}
