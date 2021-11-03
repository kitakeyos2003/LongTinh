// Decompiled with: Procyon 0.5.36
// Class Version: 1
package model;

import common.Common;
import java.util.Enumeration;
import java.util.Vector;
import common.DrawBase;
import base.DCanvas;
import java.util.Random;
import means.ImageManager;
import base.Macro;
import base.Param;
import common.DrawCarmck;
import java.util.Hashtable;
import javax.microedition.lcdui.Graphics;
import means.QSprite;
import javax.microedition.lcdui.Image;

public class Map
{
    private static Map map;
    public byte bytIsCity;
    public short shtAreaMapId;
    public short shtSmallMapId;
    public Image imgSmallMap;
    public QSprite imgAreaMap;
    public boolean blnAreaMapExist;
    public short imgAreaMapPngId;
    public short imgAreaMapAnuId;
    public QSprite imgWorldMap;
    public boolean blnWorldMapExist;
    public short imgWorldMapPngId;
    public short imgWorldMapAnuId;
    public short[][] shtsAreaMapPlaceXY;
    public short[][] shtsAreaMapPlaceBornXY;
    public short[] shtsAreaMapflag;
    public String[] strsAreaMapPlaceName;
    public String strAreaMapName;
    public short[][] shtsWorldMapPlaceXY;
    public short[] shtsWorldMapflag;
    public String[] strsWorldMapPlaceName;
    public String strWorldMapName;
    public Image[] imgWorldMapArrow;
    public short shtMapId;
    public byte bytMapClanType;
    public byte bytCurAreaMapClanType;
    public int intMapTransferNpcId;
    public String strName;
    public byte bytPk;
    public byte bytBornX;
    public byte bytBornY;
    public Image imgMaterial;
    public short[] shtBuildingId;
    public Image[] imgBuilding;
    public Image defaultBuilding;
    public short defaultBuildingHeight;
    public short defaultBuildingWidth;
    public short shtDefaultBuildingId;
    private boolean blnDrawFloat;
    public short[][] shtsBuildingWH;
    private QSprite sprOutJump;
    public Image imgCarry;
    public Image imgMap;
    public Graphics gimgMap;
    public short shtMapImgWidth;
    public short shtMapImgHeight;
    public byte bytTileX;
    public byte bytTileY;
    public short shtJudgeX;
    public short shtJudgeY;
    public short shtAnuId;
    public byte[] bytsMap_Ground_Transform;
    private byte[][] bytsMap_Float_Transform;
    public byte[] bytsMap_Ground_Kmack;
    private short[][] bytsMap_Float;
    private short[][] shtsMap_FloatIndex;
    public short[][] shtsMapEvent;
    public byte[][] bytsNpcXY;
    public byte[][] bytsBoxXY;
    public byte[][] bytsTaskXY;
    public boolean blnHaveOutJump;
    public boolean blnHaveInJump;
    public byte[][] bytInJumpPlace;
    public short[][] shtOutJumpPlace;
    public byte[] bytOutJumpDirection;
    public String[] strOutJumpName;
    public static final byte DIRECTION_DOWN = 1;
    public static final byte DIRECTION_UP = 0;
    public static final byte DIRECTION_RIGHT = 3;
    public static final byte DIRECTION_LEFT = 2;
    public byte bytColumn;
    public byte bytRow;
    public short shtSmallMapMaxWidth;
    public short shtSmallMapMaxHeight;
    public short shtWorldMapMaxWidth;
    public short shtWorldMapMaxHeight;
    public byte bytTileHeight;
    public byte bytTileWidth;
    private short[] shtsTileSetX;
    private short[] shtsTileSetY;
    private byte[][] bytsCartoon_Index;
    private boolean blnHaveCartoon;
    public boolean blnDrawCartoon;
    private final byte MAX_CARTOONTIME;
    private byte bytCartoonTime;
    private byte bytColumnS;
    private byte bytColumnE;
    private byte bytRowS;
    private byte bytRowE;
    public byte bytDrawRowS;
    public byte bytDrawRowE;
    public byte bytDrawColumnS;
    public byte bytDrawColumnE;
    public byte bytMaxDrawRow;
    public byte bytNpcConvoyNum;
    private int[] intSkillAreaColor;
    private short shtShowAreaTime;
    private boolean blnShowArea;
    private byte bytShowAreaRoleType;
    private int intShowAreaRoleId;
    private byte bytSkillAreaTileX;
    private byte bytSkillAreaTileY;
    private byte bytSkillAreaTileW;
    private byte bytSkillAreaTileH;
    private byte bytSkillAreaEndX;
    private byte bytSkillAreaEndY;
    int oldy;
    public Hashtable htShowJumpPoint;
    
    public Map() {
        this.bytIsCity = 0;
        this.defaultBuildingHeight = 0;
        this.defaultBuildingWidth = 0;
        this.shtDefaultBuildingId = 176;
        this.MAX_CARTOONTIME = 2;
        this.htShowJumpPoint = new Hashtable(1);
        Map.map = this;
    }
    
    public void delMap() {
        Map.map = null;
        if (DrawCarmck.m_tileKit1 != null) {
            DrawCarmck.m_tileKit1 = null;
        }
    }
    
    public static Map getInstance() {
        return Map.map;
    }
    
    public void initMap() {
        this.clean();
        this.strName = "";
    }
    
    public void setMapInfo(final byte bytColumn, final byte bytRow) {
        this.bytColumn = bytColumn;
        this.bytRow = bytRow;
        this.reSetMap(false);
        this.setMapEvent(new short[this.bytRow][this.bytColumn]);
        Param.getInstance().htRolePlayer = new Hashtable(1);
        this.bytNpcConvoyNum = 0;
        this.blnHaveCartoon = false;
        this.blnHaveOutJump = false;
        this.blnHaveInJump = false;
        this.bytMaxDrawRow = 2;
    }
    
    public void reSetMap(final boolean b) {
        this.shtMapImgWidth = Macro.SCREEN_WIDTH;
        this.shtMapImgHeight = Macro.SCREEN_GAME_HEIGHT;
        Param.getInstance().shtMapMaxWidth = (short)(this.bytColumn * 16);
        Param.getInstance().shtMapMaxHeight = (short)(this.bytRow * 16);
        this.bytTileWidth = (byte)(this.shtMapImgWidth / 16 + this.getTile(this.shtMapImgWidth));
        this.bytTileHeight = (byte)(this.shtMapImgHeight / 16 + this.getTile(this.shtMapImgHeight));
        if (this.bytTileHeight > this.bytRow) {
            this.bytTileHeight = this.bytRow;
            this.shtMapImgHeight = (short)(this.bytRow * 16);
        }
        if (this.bytTileWidth > this.bytColumn) {
            this.bytTileWidth = this.bytColumn;
            this.shtMapImgWidth = (short)(this.bytColumn * 16);
        }
        DrawCarmck.createBg();
        if (b && ORPMe.ME != null) {
            this.setCameraTileXY(ORPMe.ME.bytTileX, ORPMe.ME.bytTileY);
        }
    }
    
    public void setMapGround(final byte[] bytsMap_Ground_Kmack) {
        this.bytsMap_Ground_Kmack = bytsMap_Ground_Kmack;
        DrawCarmck.createBg();
    }
    
    public void setMapGroundTransform(final byte[] bytsMap_Ground_Transform) {
        this.bytsMap_Ground_Transform = bytsMap_Ground_Transform;
    }
    
    public void setMapFloatTransform(final byte[] array) {
        this.bytsMap_Float_Transform = new byte[this.bytRow][this.bytColumn];
        for (byte b = 0; b < array.length; ++b) {
            this.bytsMap_Float_Transform[b / this.bytColumn][b % this.bytColumn] = array[b];
        }
    }
    
    public void setMapFloat(final short[] array, final byte b, final short[] array2) {
        if (array.length == 0) {
            return;
        }
        final short[][] array3 = new short[array.length / 3][3];
        for (int i = 0; i < array3.length; ++i) {
            array3[i][0] = array[i * 3];
            array3[i][1] = array[i * 3 + 1];
            array3[i][2] = array[i * 3 + 2];
        }
        this.shtsMap_FloatIndex = new short[this.bytRow][2];
        this.bytsMap_Float = new short[array3.length][2];
        short n = array3[0][1];
        for (int j = 0; j < array3.length; ++j) {
            final short[] array4 = this.shtsMap_FloatIndex[array3[j][1]];
            final int n2 = 0;
            ++array4[n2];
            if (n != array3[j][1]) {
                this.shtsMap_FloatIndex[array3[j][1]][1] = (short)j;
            }
            n = array3[j][1];
            this.bytsMap_Float[j][0] = array3[j][0];
            for (short n3 = 0; n3 < array2.length; n3 = (byte)(n3 + 1)) {
                if (array3[j][2] == array2[n3]) {
                    this.bytsMap_Float[j][1] = n3;
                }
            }
        }
        this.blnDrawFloat = true;
    }
    
    public void setOutJumpPoint(final short[][] shtOutJumpPlace, final String[] strOutJumpName, final byte[] bytOutJumpDirection, final byte[] array) {
        this.blnHaveOutJump = true;
        this.shtOutJumpPlace = shtOutJumpPlace;
        this.strOutJumpName = strOutJumpName;
        this.bytOutJumpDirection = bytOutJumpDirection;
        for (int i = 0; i < strOutJumpName.length; i = (byte)(i + 1)) {
            this.shtsMapEvent[shtOutJumpPlace[i][1]][shtOutJumpPlace[i][0]] = (short)(i + 201);
            if (array[i] == 0) {
                final short[] array2 = this.shtsMapEvent[shtOutJumpPlace[i][1]];
                final short n = shtOutJumpPlace[i][0];
                array2[n] += 10000;
                final short[] array3 = this.shtOutJumpPlace[i];
                final int n2 = 2;
                array3[n2] += 10000;
            }
        }
        (this.sprOutJump = ImageManager.loadSpriteById(3, "OutJumptransport2", "transport2", "transport2", "ingame")).setAnimation(0);
        this.initOutJump();
    }
    
    public void setOutJumpPointAppear(final short n) {
        if (this.shtOutJumpPlace != null) {
            for (int i = 0; i < this.shtOutJumpPlace.length; ++i) {
                if (this.shtOutJumpPlace[i][2] == n + 10000) {
                    final short[] array = this.shtsMapEvent[this.shtOutJumpPlace[i][1]];
                    final short n2 = this.shtOutJumpPlace[i][0];
                    array[n2] -= 10000;
                    final short[] array2 = this.shtOutJumpPlace[i];
                    final int n3 = 2;
                    array2[n3] -= 10000;
                }
            }
        }
        this.initOutJump();
    }
    
    public void setInJumpPoint(final byte[][] bytInJumpPlace) {
        this.blnHaveInJump = true;
        this.bytInJumpPlace = bytInJumpPlace;
        for (int i = 0; i < this.bytInJumpPlace.length; i = (byte)(i + 1)) {
            this.shtsMapEvent[bytInJumpPlace[i][1]][bytInJumpPlace[i][0]] = (byte)(i + 101);
        }
        this.imgCarry = ImageManager.CreateImage("carry", "ingame");
    }
    
    private void setMapEvent(final short[][] shtsMapEvent) {
        this.shtsMapEvent = shtsMapEvent;
    }
    
    public void setMapEvent(final short n, final short n2, final short n3) {
        this.shtsMapEvent[n2][n] = n3;
    }
    
    public void setMapCartoon(final byte[][] array) {
        this.blnHaveCartoon = true;
        this.blnDrawCartoon = true;
        int n = 0;
        final byte[][] array2 = new byte[this.bytRow][this.bytColumn];
        for (byte b = 0; b < this.bytRow; ++b) {
            for (byte b2 = 0; b2 < this.bytColumn; ++b2) {
                array2[b][b2] = -1;
                for (int i = 0; i < array.length; i = (byte)(i + 1)) {
                    if (this.bytsMap_Ground_Kmack[this.bytColumn * b2 + b] == array[i][0]) {
                        array2[b][b2] = array[i][0];
                        n = (short)(n + 1);
                        break;
                    }
                }
            }
        }
        this.bytsCartoon_Index = new byte[n][5];
        final Random random = new Random();
        int n2 = 0;
        int n3 = 0;
        byte b3 = 0;
        for (byte b4 = 0; b4 < this.bytRow; ++b4) {
            for (byte b5 = 0; b5 < this.bytColumn; ++b5) {
                if (array2[b4][b5] != -1) {
                    this.bytsCartoon_Index[n2][2] = array2[b4][b5];
                    int j = 0;
                    while (j < array.length) {
                        if (array2[b4][b5] == array[j][0]) {
                            if (n3 == 0) {
                                b3 = (byte)Math.abs(random.nextInt() % array[j][1]);
                            }
                            this.bytsCartoon_Index[n2][0] = b5;
                            this.bytsCartoon_Index[n2][1] = b4;
                            this.bytsCartoon_Index[n2][3] = array[j][1];
                            this.bytsCartoon_Index[n2][4] = b3;
                            if (this.bytsCartoon_Index[n2][4] > this.bytsCartoon_Index[n2][3]) {
                                this.bytsCartoon_Index[n2][4] = 0;
                                break;
                            }
                            break;
                        }
                        else {
                            j = (short)(j + 1);
                        }
                    }
                    n2 = (short)(n2 + 1);
                    n3 = 1;
                }
                else {
                    n3 = 0;
                }
            }
        }
        this.bytCartoonTime = 0;
    }
    
    public void creatMaterialImg(final Image imgMaterial) {
        if (imgMaterial != null) {
            this.imgMaterial = imgMaterial;
        }
        final int width = this.imgMaterial.getWidth();
        final int height = this.imgMaterial.getHeight();
        final int n = width * height / 16 / 16;
        this.shtsTileSetX = new short[n];
        this.shtsTileSetY = new short[n];
        int n2 = 0;
        for (short n3 = 0; n3 < height; n3 += 16) {
            for (short n4 = 0; n4 < width; n4 += 16) {
                this.shtsTileSetX[n2] = n4;
                this.shtsTileSetY[n2] = n3;
                ++n2;
            }
        }
    }
    
    public void draw(final Graphics graphics) {
        DCanvas.getInstance();
        DrawCarmck.mapFastDraw(DCanvas.gameG, Param.getInstance().CAMERAX, Param.getInstance().CAMERAY);
        if (this.shtShowAreaTime > 0) {
            this.drawArea(graphics);
        }
    }
    
    public void drawMapFloat(final Graphics graphics, final int n) {
        if (this.blnDrawFloat && this.bytsMap_Float != null) {
            for (short n2 = 0; n2 < this.shtsMap_FloatIndex[n][0]; ++n2) {
                final short n3 = this.bytsMap_Float[this.shtsMap_FloatIndex[n][1] + n2][0];
                final short n4 = this.bytsMap_Float[this.shtsMap_FloatIndex[n][1] + n2][1];
                final short n5 = getInstance().shtBuildingId[n4];
                int n6;
                if (this.bytsMap_Float_Transform != null) {
                    n6 = this.bytsMap_Float_Transform[n][n3];
                }
                else {
                    n6 = 0;
                }
                final int n7 = n3 * 16;
                short n8 = this.shtsBuildingWH[n4][0];
                final int n9 = (n + 1) * 16;
                short n10 = this.shtsBuildingWH[n4][1];
                if (this.judgeImageIsDraw(n7, n8, n9, n10, (n8 == 0) ? 200 : n8, 0)) {
                    final Image png = ImageManager.getPng(3, "/building/" + n5 + ".png");
                    if (png != null && png != this.imgBuilding[n4]) {
                        this.imgBuilding[n4] = png;
                        this.shtsBuildingWH[n4][0] = (short)this.imgBuilding[n4].getWidth();
                        this.shtsBuildingWH[n4][1] = (short)this.imgBuilding[n4].getHeight();
                        n8 = this.shtsBuildingWH[n4][0];
                        n10 = this.shtsBuildingWH[n4][1];
                    }
                    if (this.imgBuilding[n4] != null && this.judgeImageIsDraw(n7, n8, n9, n10, 0, 0)) {
                        DrawBase.drawRegion(this.imgBuilding[n4], n3 * 16 - Param.getInstance().CAMERAX, (n + 1) * 16 - Param.getInstance().CAMERAY, 0, 0, n8, n10, n6, 36);
                    }
                }
            }
        }
    }
    
    public void initOutJump() {
        final Hashtable hashtable = new Hashtable(1);
        for (short n = 0; n < this.shtOutJumpPlace.length; n = (byte)(n + 1)) {
            if (this.shtOutJumpPlace[n][2] < 10000) {
                final short n2 = this.shtOutJumpPlace[n][2];
                final short n3 = this.shtOutJumpPlace[n][0];
                final short n4 = this.shtOutJumpPlace[n][1];
                if (!hashtable.containsKey(new Short(n2))) {
                    Vector vector = new Vector(1);
                    vector.addElement(new short[] { n3, n4, n });
                    hashtable.put(new Short(n2), vector);
                }
                else {
                    Vector vector2 = (Vector) hashtable.get(new Short(n2));
                    for (int i = 0; i < vector2.size(); ++i) {
                        short[] array = (short[]) vector2.elementAt(i);
                        if (n3 + n4 <= array[0] + array[1]) {
                            vector2.insertElementAt(new short[] { n3, n4, n }, i);
                            break;
                        }
                        if (i == vector2.size() - 1) {
                            vector2.addElement(new short[] { n3, n4, n });
                            break;
                        }
                    }
                }
            }
        }
        final Enumeration keys = hashtable.keys();
        while (keys.hasMoreElements()) {
            Short n5 = (Short) keys.nextElement();
             Vector vector3 = (Vector) hashtable.get(n5);
            if (vector3.size() > 0) {
                this.htShowJumpPoint.put(n5, vector3.elementAt(vector3.size() / 2));
            }
        }
        hashtable.clear();
    }
    
    public void drawOutJump(final Graphics graphics, final int n) {
        if (getInstance().htShowJumpPoint != null) {
            Enumeration keys = (Enumeration)getInstance().htShowJumpPoint.keys();
            while (keys.hasMoreElements()) {
                short shortValue = ((Short) keys.nextElement()).shortValue();
                short[] array = (short[]) getInstance().htShowJumpPoint.get(new Short(shortValue));
                int n2 = array[0] * 16 - Param.getInstance().CAMERAX + 8;
                int n3 = array[1] * 16 - Param.getInstance().CAMERAY + 8;
                if (shortValue < 10000 && array[1] >= this.bytDrawRowS && array[1] <= this.bytDrawRowE && array[0] >= this.bytDrawColumnS && array[0] <= this.bytDrawColumnE && n == array[1]) {
                    this.sprOutJump.drawAnimation(graphics, n2, n3);
                }
            }
        }
    }
    
    private void drawCartoon() {
        for (int i = 0; i < this.bytsCartoon_Index.length; i = (short)(i + 1)) {
            if (this.bytsCartoon_Index[i][1] >= this.bytDrawRowS && this.bytsCartoon_Index[i][1] <= this.bytDrawRowE && this.bytsCartoon_Index[i][0] >= this.bytDrawColumnS) {
                final byte b = this.bytsCartoon_Index[i][0];
            }
        }
    }
    
    private void moveMapLeft(final int n) {
        final Param instance = Param.getInstance();
        instance.CAMERAX += (short)n;
        final byte b = (byte)(Param.getInstance().CAMERAX / 16);
        final byte b2 = (byte)((Param.getInstance().CAMERAX - n) / 16 + this.getTile(Param.getInstance().CAMERAX - n));
        final byte b3 = (byte)(Param.getInstance().CAMERAY / 16);
        final byte b4 = (byte)((Param.getInstance().CAMERAY + this.shtMapImgHeight) / 16 + this.getTile(Param.getInstance().CAMERAY + this.shtMapImgHeight));
        final int n2 = this.bytColumnS - b;
        this.bytColumnS -= (byte)n2;
        this.bytColumnE -= (byte)n2;
        this.bytDrawColumnS = (byte)(this.bytColumnS - 1);
        this.bytDrawColumnS = (byte)((this.bytDrawColumnS < 0) ? 0 : this.bytDrawColumnS);
        this.bytDrawColumnE = (byte)(this.bytColumnE + 1);
        this.bytDrawColumnE = ((this.bytDrawColumnE > this.bytColumn) ? this.bytColumn : this.bytDrawColumnE);
    }
    
    private void moveMapUp(final int n) {
        final Param instance = Param.getInstance();
        instance.CAMERAY += (short)n;
        final byte b = (byte)(Param.getInstance().CAMERAY / 16);
        final byte b2 = (byte)((Param.getInstance().CAMERAY - n) / 16 + this.getTile(Param.getInstance().CAMERAY - n));
        final byte b3 = (byte)(Param.getInstance().CAMERAX / 16);
        final byte b4 = (byte)((Param.getInstance().CAMERAX + this.shtMapImgWidth) / 16 + this.getTile(Param.getInstance().CAMERAX + this.shtMapImgWidth));
        final int n2 = this.bytRowS - b;
        this.bytRowS -= (byte)n2;
        this.bytRowE -= (byte)n2;
        this.bytDrawRowS = (byte)(this.bytRowS - 1);
        this.bytDrawRowS = (byte)((this.bytDrawRowS < 0) ? 0 : this.bytDrawRowS);
        this.bytDrawRowE = (byte)(this.bytRowE + 1);
        this.bytDrawRowE = ((this.bytDrawRowE > this.bytRow) ? this.bytRow : this.bytDrawRowE);
    }
    
    private void moveMapRight(final int n) {
        if (Param.getInstance().CAMERAX + n < getInstance().bytColumn * 16 - DrawCarmck.PF_LV_WIDTH) {
            final Param instance = Param.getInstance();
            instance.CAMERAX += (short)n;
        }
        final byte b = (byte)((Param.getInstance().CAMERAX + this.shtMapImgWidth - n - 1) / 16);
        final byte b2 = (byte)((Param.getInstance().CAMERAX + this.shtMapImgWidth) / 16 + this.getTile(Param.getInstance().CAMERAX + this.shtMapImgWidth));
        final byte b3 = (byte)(Param.getInstance().CAMERAY / 16);
        final byte b4 = (byte)((Param.getInstance().CAMERAY + this.shtMapImgHeight) / 16 + this.getTile(Param.getInstance().CAMERAY + this.shtMapImgHeight));
        final int n2 = b2 - this.bytColumnE;
        this.bytColumnS += (byte)n2;
        this.bytColumnE += (byte)n2;
        this.bytDrawColumnS = (byte)(this.bytColumnS - 1);
        this.bytDrawColumnS = (byte)((this.bytDrawColumnS < 0) ? 0 : this.bytDrawColumnS);
        this.bytDrawColumnE = (byte)(this.bytColumnE + 1);
        this.bytDrawColumnE = ((this.bytDrawColumnE > this.bytColumn) ? this.bytColumn : this.bytDrawColumnE);
    }
    
    private void moveMapDown(final int n) {
        if (Param.getInstance().CAMERAY + n < getInstance().bytRow * 16 - DrawCarmck.PF_LV_HEIGHT) {
            final Param instance = Param.getInstance();
            instance.CAMERAY += (short)n;
        }
        final byte b = (byte)((Param.getInstance().CAMERAY + this.shtMapImgHeight - n) / 16);
        final byte b2 = (byte)((Param.getInstance().CAMERAY + this.shtMapImgHeight) / 16 + this.getTile(Param.getInstance().CAMERAY + this.shtMapImgHeight));
        final byte b3 = (byte)(Param.getInstance().CAMERAX / 16);
        final byte b4 = (byte)((Param.getInstance().CAMERAX + this.shtMapImgWidth) / 16 + this.getTile(Param.getInstance().CAMERAX + this.shtMapImgWidth));
        final int n2 = b2 - this.bytRowE;
        this.bytRowS += (byte)n2;
        this.bytRowE += (byte)n2;
        this.bytDrawRowS = (byte)(this.bytRowS - 1);
        this.bytDrawRowS = (byte)((this.bytDrawRowS < 0) ? 0 : this.bytDrawRowS);
        this.bytDrawRowE = (byte)(this.bytRowE + 1);
        this.bytDrawRowE = ((this.bytDrawRowE > this.bytRow) ? this.bytRow : this.bytDrawRowE);
    }
    
    private byte getTile(final int n) {
        return (byte)((n % 16 != 0) ? 1 : 0);
    }
    
    public void setCameraTileXY(final int n, final int n2) {
        DrawCarmck.m_updateBg = true;
        int n3 = n * 16 + 8 - this.shtMapImgWidth / 2;
        if (n3 < 0) {
            n3 = 0;
        }
        else if (n3 + this.shtMapImgWidth > Param.getInstance().shtMapMaxWidth) {
            n3 = Param.getInstance().shtMapMaxWidth - this.shtMapImgWidth;
        }
        int n4 = n2 * 16 + 8 - this.shtMapImgHeight / 2;
        if (n4 < 0) {
            n4 = 0;
        }
        else if (n4 + this.shtMapImgHeight > Param.getInstance().shtMapMaxHeight) {
            n4 = Param.getInstance().shtMapMaxHeight - this.shtMapImgHeight;
        }
        if (n3 > (getInstance().bytColumn - 1) * 16 - Macro.SCREEN_WIDTH) {
            n3 = (getInstance().bytColumn - 1) * 16 - Macro.SCREEN_WIDTH;
        }
        if (n3 < 0) {
            n3 = 0;
        }
        Param.getInstance().CAMERAX = (short)n3;
        if (n4 > (getInstance().bytRow - 1) * 16 - Macro.SCREEN_GAME_HEIGHT) {
            n4 = (getInstance().bytRow - 1) * 16 - Macro.SCREEN_GAME_HEIGHT;
        }
        if (n4 < 0) {
            n4 = 0;
        }
        Param.getInstance().CAMERAY = (short)n4;
        final byte bytColumnS = (byte)(n3 / 16);
        final byte bytColumnE = (byte)(bytColumnS + this.bytTileWidth);
        final byte bytRowS = (byte)(n4 / 16);
        final byte bytRowE = (byte)(bytRowS + this.bytTileHeight);
        this.bytColumnS = bytColumnS;
        this.bytColumnE = bytColumnE;
        this.bytRowS = bytRowS;
        this.bytRowE = bytRowE;
        this.bytDrawRowS = (byte)(this.bytRowS - 1);
        this.bytDrawRowS = (byte)((this.bytDrawRowS < 0) ? 0 : this.bytDrawRowS);
        this.bytDrawRowE = (byte)(this.bytRowE + 1);
        this.bytDrawRowE = ((this.bytDrawRowE > this.bytRow) ? this.bytRow : this.bytDrawRowE);
        this.bytDrawColumnS = (byte)(this.bytColumnS - 1);
        this.bytDrawColumnS = (byte)((this.bytDrawColumnS < 0) ? 0 : this.bytDrawColumnS);
        this.bytDrawColumnE = (byte)(this.bytColumnE + 1);
        this.bytDrawColumnE = ((this.bytDrawColumnE > this.bytColumn) ? this.bytColumn : this.bytDrawColumnE);
    }
    
    public void moveMap(int n, int n2) {
        if (Param.getInstance().CAMERAX + n < 0) {
            n = -Param.getInstance().CAMERAX;
        }
        else if (Param.getInstance().CAMERAX + this.shtMapImgWidth + n > Param.getInstance().shtMapMaxWidth) {
            n = Param.getInstance().shtMapMaxWidth - (Param.getInstance().CAMERAX + this.shtMapImgWidth);
        }
        if (Param.getInstance().CAMERAY + n2 < 0) {
            n2 = -Param.getInstance().CAMERAY;
        }
        else if (Param.getInstance().CAMERAY + this.shtMapImgHeight + n2 > Param.getInstance().shtMapMaxHeight) {
            n2 = Param.getInstance().shtMapMaxHeight - (Param.getInstance().CAMERAY + this.shtMapImgHeight);
        }
        if (n < 0) {
            this.moveMapLeft(n);
        }
        else if (n > 0) {
            this.moveMapRight(n);
        }
        else if (n2 < 0) {
            this.moveMapUp(n2);
        }
        else if (n2 > 0) {
            this.moveMapDown(n2);
        }
    }
    
    public void setCameraTileXY_SmallMap(final int n, final int n2, final int n3, final int n4) {
        int n5 = n - this.shtMapImgWidth / 2;
        if (n5 < 0) {
            n5 = 0;
        }
        else if (n5 + this.shtMapImgWidth > n3) {
            n5 = n3 - this.shtMapImgWidth;
        }
        int n6 = n2 - this.shtMapImgHeight / 2;
        if (n6 < 0) {
            n6 = 0;
        }
        else if (n6 + this.shtMapImgHeight > n4) {
            n6 = n4 - this.shtMapImgHeight;
        }
        if (n5 > n3 - Macro.SCREEN_WIDTH) {
            n5 = n3 - Macro.SCREEN_WIDTH;
        }
        if (n5 < 0) {
            n5 = 0;
        }
        Param.getInstance().SMALL_MAP_CAMERAX = (short)n5;
        if (n6 > n4 - Macro.SCREEN_GAME_HEIGHT) {
            n6 = n4 - Macro.SCREEN_GAME_HEIGHT;
        }
        if (n6 < 0) {
            n6 = 0;
        }
        Param.getInstance().SMALL_MAP_CAMERAY = (short)n6;
    }
    
    public void moveMap_SmallMap(int n, int n2) {
        if (Param.getInstance().SMALL_MAP_CAMERAX + n < 0) {
            n = -Param.getInstance().SMALL_MAP_CAMERAX;
        }
        else if (Param.getInstance().SMALL_MAP_CAMERAX + this.shtMapImgWidth + n > this.shtSmallMapMaxWidth) {
            n = this.shtSmallMapMaxWidth - (Param.getInstance().CAMERAX + this.shtMapImgWidth);
        }
        if (Param.getInstance().SMALL_MAP_CAMERAY + n2 < 0) {
            n2 = -Param.getInstance().SMALL_MAP_CAMERAY;
        }
        else if (Param.getInstance().SMALL_MAP_CAMERAY + this.shtMapImgHeight + n2 > this.shtSmallMapMaxHeight) {
            n2 = this.shtSmallMapMaxHeight - (Param.getInstance().SMALL_MAP_CAMERAY + this.shtMapImgHeight);
        }
        if (n < 0) {
            this.moveMapLeft_SmallMap(n);
        }
        else if (n > 0) {
            this.moveMapRight_SmallMap(n);
        }
        if (n2 < 0) {
            this.moveMapUp_SmallMap(n2);
        }
        else if (n2 > 0) {
            this.moveMapDown_SmallMap(n2);
        }
    }
    
    private void moveMapLeft_SmallMap(final int n) {
        final Param instance = Param.getInstance();
        instance.SMALL_MAP_CAMERAX += (short)n;
    }
    
    private void moveMapUp_SmallMap(final int n) {
        final Param instance = Param.getInstance();
        instance.CAMERAY += (short)n;
    }
    
    private void moveMapRight_SmallMap(final int n) {
        final Param instance = Param.getInstance();
        instance.CAMERAX += (short)n;
    }
    
    private void moveMapDown_SmallMap(final int n) {
        final Param instance = Param.getInstance();
        instance.CAMERAY += (short)n;
    }
    
    public void setAreaMap(final short shtAreaMapId, final String strAreaMapName) {
        this.strAreaMapName = strAreaMapName;
        if (this.shtAreaMapId != shtAreaMapId) {
            this.shtAreaMapId = shtAreaMapId;
            this.imgAreaMap = null;
            this.shtsAreaMapPlaceXY = null;
            this.shtsAreaMapflag = null;
            this.strsAreaMapPlaceName = null;
        }
    }
    
    public void clean() {
        this.blnDrawFloat = false;
        this.blnShowArea = false;
        this.imgSmallMap = null;
        this.imgSmallMap = null;
        this.sprOutJump = null;
        this.gimgMap = null;
        this.bytsMap_Ground_Transform = null;
        this.bytsMap_Float_Transform = null;
        this.bytsMap_Ground_Kmack = null;
        this.bytsCartoon_Index = null;
        this.bytsMap_Float = null;
        this.bytInJumpPlace = null;
        this.shtOutJumpPlace = null;
        this.bytOutJumpDirection = null;
        this.strOutJumpName = null;
        this.imgBuilding = null;
        this.shtsBuildingWH = null;
        this.shtsMap_FloatIndex = null;
        this.imgCarry = null;
        this.bytsNpcXY = null;
        this.bytsBoxXY = null;
        this.bytsTaskXY = null;
        this.shtShowAreaTime = 0;
        this.bytShowAreaRoleType = 0;
        this.intShowAreaRoleId = 0;
        this.intSkillAreaColor = null;
        this.htShowJumpPoint.clear();
    }
    
    public void logic(final int n) {
        if (this.blnHaveCartoon) {
            ++this.bytCartoonTime;
            if (this.bytCartoonTime >= 2) {
                this.bytCartoonTime = 0;
                for (int i = 0; i < this.bytsCartoon_Index.length; ++i) {
                    final byte[] array = this.bytsCartoon_Index[i];
                    final int n2 = 4;
                    ++array[n2];
                    if (this.bytsCartoon_Index[i][4] >= this.bytsCartoon_Index[i][3]) {
                        this.bytsCartoon_Index[i][4] = 0;
                    }
                }
                this.blnDrawCartoon = true;
            }
        }
        if (this.sprOutJump != null && getInstance().htShowJumpPoint != null) {
            Enumeration keys = (Enumeration)getInstance().htShowJumpPoint.keys();
            while (keys.hasMoreElements()) {
                short shortValue = ((Short) keys.nextElement()).shortValue();
                short[] array2 = (short[]) getInstance().htShowJumpPoint.get(new Short(shortValue));
                if (shortValue < 10000 && array2[1] >= this.bytDrawRowS && array2[1] <= this.bytDrawRowE && array2[0] >= this.bytDrawColumnS && array2[0] <= this.bytDrawColumnE && this.sprOutJump != null) {
                    this.sprOutJump.update();
                    break;
                }
            }
        }
        if (this.shtShowAreaTime > 0 && !this.blnShowArea) {
            this.shtShowAreaTime -= (short)n;
            if (this.bytShowAreaRoleType != 0 && !this.checkRoleSkillArea()) {
                this.shtShowAreaTime = -1;
            }
            if (this.shtShowAreaTime <= 0) {
                this.bytShowAreaRoleType = 0;
                this.intShowAreaRoleId = 0;
                this.intSkillAreaColor = null;
            }
        }
    }
    
    public boolean judgeIsDraw(final int n, final int n2, final int n3, final int n4) {
        return this.judgeIsDraw(n, n2, n3, n4, 0);
    }
    
    public boolean judgeRoleIsDraw(final ORole oRole) {
        return this.judgeIsDraw(oRole.shtJudgeX, oRole.shtImgWidth, oRole.shtJudgeY, oRole.shtImgHeight, this.shtMapImgWidth / 10);
    }
    
    public boolean judgeIsDraw(final int n, final int n2, final int n3, final int n4, final int n5) {
        return n > Param.getInstance().CAMERAX - n2 / 2 - n5 && n <= Param.getInstance().CAMERAX + this.shtMapImgWidth + n2 / 2 + n5 && n3 >= Param.getInstance().CAMERAY - n4 / 2 - n5 && n3 < Param.getInstance().CAMERAY + this.shtMapImgHeight + n4 / 2 + n5;
    }
    
    public boolean judgeImageIsDraw(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        return n >= Param.getInstance().CAMERAX - n2 - n5 && n <= Param.getInstance().CAMERAX + this.shtMapImgWidth + n2 + n5 && n3 >= Param.getInstance().CAMERAY - n4 - n5 && n3 <= Param.getInstance().CAMERAY + this.shtMapImgHeight + n4 + n5;
    }
    
    public boolean checkMeIsPass(final int n, final int n2) {
        if (n < 0 || n >= this.bytColumn || n2 < 0 || n2 >= this.bytRow) {
            return false;
        }
        if (this.shtsMapEvent[n2][n] >= 0) {
            if (this.bytsNpcXY != null) {
                for (int i = 0; i < this.bytsNpcXY.length; ++i) {
                    if (this.bytsNpcXY[i][0] == n2 && this.bytsNpcXY[i][1] == n) {
                        return false;
                    }
                }
            }
            return this.bytsTaskXY == null || this.bytsTaskXY[n2][n] != 2;
        }
        return false;
    }
    
    public boolean checkBox(final int n, final int n2) {
        return this.bytsBoxXY != null && n >= 0 && n < this.bytColumn && n2 >= 0 && n2 < this.bytRow && this.bytsBoxXY[n2][n] > 0;
    }
    
    public boolean checkTask(final int n, final int n2) {
        return this.bytsTaskXY != null && n >= 0 && n < this.bytColumn && n2 >= 0 && n2 < this.bytRow && this.bytsTaskXY[n2][n] == 1;
    }
    
    public boolean checkIsPass(final int n, final int n2) {
        return n >= 0 && n < this.bytColumn && n2 >= 0 && n2 < this.bytRow && this.shtsMapEvent[n2][n] >= 0;
    }
    
    public boolean checkRoleIsPass(final int n, final int n2) {
        return n >= 0 && n < this.bytColumn && n2 >= 0 && n2 < this.bytRow;
    }
    
    public byte checkEvent(final int n, final int n2) {
        if (n < 0 || n >= this.bytColumn || n2 < 0 || n2 >= this.bytRow) {
            return -1;
        }
        if (this.bytsNpcXY != null) {
            for (int i = 0; i < this.bytsNpcXY.length; ++i) {
                if (this.bytsNpcXY[i][0] == n2 && this.bytsNpcXY[i][1] == n) {
                    return 1;
                }
            }
        }
        if (this.bytsBoxXY != null && this.bytsBoxXY[n2][n] > 0) {
            return 4;
        }
        if (this.bytsTaskXY != null && this.bytsTaskXY[n2][n] > 0) {
            return 5;
        }
        return 0;
    }
    
    public void creatSkillArea(byte bytSkillAreaTileX, byte bytSkillAreaTileY, byte b, byte b2, final short shtShowAreaTime, final int n) {
        if (shtShowAreaTime != 0) {
            this.blnShowArea = false;
            this.intSkillAreaColor = DrawBase.getRGB(16, 16, n);
            this.shtShowAreaTime = shtShowAreaTime;
            if (this.shtShowAreaTime == -1) {
                this.shtShowAreaTime = 1;
                this.blnShowArea = true;
            }
        }
        if (bytSkillAreaTileX < 0) {
            b += bytSkillAreaTileX;
            bytSkillAreaTileX = 0;
        }
        if (bytSkillAreaTileY < 0) {
            b2 += bytSkillAreaTileY;
            bytSkillAreaTileY = 0;
        }
        this.bytSkillAreaTileX = bytSkillAreaTileX;
        this.bytSkillAreaTileY = bytSkillAreaTileY;
        this.bytSkillAreaEndX = (byte)(this.bytSkillAreaTileX + b);
        this.bytSkillAreaEndY = (byte)(this.bytSkillAreaTileY + b2);
        if (this.bytSkillAreaEndX > this.bytColumn) {
            this.bytSkillAreaEndX = this.bytColumn;
        }
        if (this.bytSkillAreaEndY > this.bytRow) {
            this.bytSkillAreaEndY = this.bytRow;
        }
    }
    
    public void creatRoleSkillArea(final byte bytShowAreaRoleType, final int intShowAreaRoleId, final byte bytSkillAreaTileW, final byte bytSkillAreaTileH, final short shtShowAreaTime) {
        this.bytShowAreaRoleType = bytShowAreaRoleType;
        this.intShowAreaRoleId = intShowAreaRoleId;
        this.bytSkillAreaTileW = bytSkillAreaTileW;
        this.bytSkillAreaTileH = bytSkillAreaTileH;
        ORole oRole = null;
        if (this.bytShowAreaRoleType == 2 && Param.getInstance().htRolePlayer != null) {
            oRole = (ORole) Param.getInstance().htRolePlayer.get(new Integer(intShowAreaRoleId));
        }
        else if (this.bytShowAreaRoleType == 3 && Param.getInstance().htRoleMonster != null) {
            oRole = (ORole) Param.getInstance().htRoleMonster.get(new Integer(intShowAreaRoleId));
        }
        if (oRole == null) {
            this.bytShowAreaRoleType = 0;
            this.intShowAreaRoleId = 0;
            return;
        }
        this.shtShowAreaTime = shtShowAreaTime;
        this.intSkillAreaColor = DrawBase.getRGB(16, 16, 1144385017);
        this.checkRoleSkillArea();
    }
    
    public boolean checkRoleSkillArea() {
        ORole oRole = null;
        if (this.bytShowAreaRoleType == 2 && Param.getInstance().htRolePlayer != null) {
            oRole = (ORole) Param.getInstance().htRolePlayer.get(new Integer(this.intShowAreaRoleId));
        }
        else if (this.bytShowAreaRoleType == 3 && Param.getInstance().htRoleMonster != null) {
            oRole = (ORole) Param.getInstance().htRoleMonster.get(new Integer(this.intShowAreaRoleId));
        }
        if (oRole == null) {
            return false;
        }
        this.creatSkillArea((byte)(oRole.bytTileX - this.bytSkillAreaTileW / 2), (byte)(oRole.bytTileY - this.bytSkillAreaTileH / 2), this.bytSkillAreaTileW, this.bytSkillAreaTileH, (short)0, 1144385017);
        return true;
    }
    
    private void drawArea(final Graphics graphics) {
        for (byte bytSkillAreaTileX = this.bytSkillAreaTileX; bytSkillAreaTileX < this.bytSkillAreaEndX; ++bytSkillAreaTileX) {
            for (byte bytSkillAreaTileY = this.bytSkillAreaTileY; bytSkillAreaTileY < this.bytSkillAreaEndY; ++bytSkillAreaTileY) {
                if (this.bytSkillAreaTileY >= this.bytDrawRowS && this.bytSkillAreaTileY <= this.bytDrawRowE && this.bytSkillAreaTileX >= this.bytDrawColumnS && this.bytSkillAreaTileX <= this.bytDrawColumnE) {
                    graphics.drawRGB(this.intSkillAreaColor, 0, 16, bytSkillAreaTileX * 16 - Param.getInstance().CAMERAX, bytSkillAreaTileY * 16 - Param.getInstance().CAMERAY, 16, 16, true);
                }
            }
        }
    }
    
    public boolean checkAttack(final byte b) {
        return ORPMe.ME != null && ORPMe.ME.bytCountry != b && (this.bytPk == 1 || this.bytPk == 2 || ((this.bytPk == 3 || this.bytPk == 4) && false));
    }
    
    public byte getDirection(final byte b, final byte b2) {
        for (byte b3 = (byte)(b - 2); b3 < b + 3; ++b3) {
            for (byte b4 = (byte)(b2 - 2); b4 < b2 + 3; ++b4) {
                if (b3 >= 0 && b3 < this.bytColumn && b4 >= 0 && b4 < this.bytRow) {
                    final byte outJump = this.getOutJump(b3, b4);
                    if (outJump != 0) {
                        return outJump;
                    }
                }
            }
        }
        return 0;
    }
    
    private byte getOutJump(final byte b, final byte b2) {
        final short n = this.shtsMapEvent[b2][b];
        if (n > 0 && n >= 201 && n < 10000) {
            for (int i = 0; i < this.shtOutJumpPlace.length; ++i) {
                if (b == this.shtOutJumpPlace[i][0] && b2 == this.shtOutJumpPlace[i][1]) {
                    return this.bytOutJumpDirection[i];
                }
            }
        }
        return 0;
    }
    
    public static int transSmallMapId(final int n) {
        return n;
    }
    
    public static int[] getNextSelectPoint(final int n, final short[][] array, final int n2, final int n3) {
        int n4 = n2;
        int n5 = n3;
        if (array != null && array.length > 0) {
            int n6 = -1;
            for (int i = 0; i < array.length; ++i) {
                for (int j = 0; j < array[i].length; ++j) {
                    boolean b = false;
                    if (array[i][j] != -1) {
                        if (n == 3) {
                            if (i < n2) {
                                b = true;
                            }
                        }
                        else if (n == 4) {
                            if (i > n2) {
                                b = true;
                            }
                        }
                        else if (n == 1) {
                            if (j < n3) {
                                b = true;
                            }
                        }
                        else if (n == 2 && j > n3) {
                            b = true;
                        }
                        if (b) {
                            final short n7 = (short)(Common.pow(n2 - i, 2) + Common.pow(n3 - j, 2));
                            if (n6 > n7 || n6 == -1) {
                                n6 = n7;
                                n4 = i;
                                n5 = j;
                            }
                        }
                    }
                }
            }
        }
        return new int[] { n4, n5 };
    }
    
    public static int[] getCurPosInArray(final short[][] array, final short n) {
        int n2 = -1;
        int n3 = -1;
        for (int i = 0; i < array.length; ++i) {
            for (int j = 0; j < array[i].length; ++j) {
                if (array[i][j] == n) {
                    n2 = i;
                    n3 = j;
                }
            }
        }
        return new int[] { n2, n3 };
    }
    
    public static int[] getSmallMapNextSelectPoint(final int n, final short[][] array, final int n2) {
        final short n3 = array[n2][0];
        final short n4 = array[n2][1];
        int n5 = n2;
        short n6 = n3;
        short n7 = n4;
        if (array != null && array.length > 1) {
            long n8 = -1L;
            for (int i = 0; i < array.length; ++i) {
                final short n9 = array[i][0];
                final short n10 = array[i][1];
                boolean b = false;
                if (n == 3) {
                    if (n9 < n3) {
                        b = true;
                    }
                }
                else if (n == 4) {
                    if (n9 > n3) {
                        b = true;
                    }
                }
                else if (n == 1) {
                    if (n10 < n4) {
                        b = true;
                    }
                }
                else if (n == 2 && n10 > n4) {
                    b = true;
                }
                if (b) {
                    final long n11 = Common.pow(n3 - n9, 2) + Common.pow(n4 - n10, 2);
                    if (n8 > n11 || n8 == -1L) {
                        n8 = n11;
                        n5 = i;
                        n6 = n9;
                        n7 = n10;
                    }
                }
            }
        }
        return new int[] { n5, n6, n7 };
    }
    
    public int getSmallMapCurPlace(final short[][] array, final short[] array2, final byte b) {
        int n = -1;
        final int[] array3 = { array2[0] - b / 2, array2[1] - b / 2, b, b };
        if (array != null && array.length > 1) {
            for (int i = 0; i < array.length; ++i) {
                if (Common.judgeIsInterrupt(array3, array[i][0], 1, array[i][1], 1, 0)) {
                    n = i;
                    break;
                }
            }
        }
        return n;
    }
    
    public void moveSmallMap(final int n, final short[][] array, final short[] array2, final int n2, final int n3, final int n4) {
        int n5 = 0;
        int n6 = 0;
        boolean b = false;
        if (n == 3) {
            n5 -= n4;
        }
        else if (n == 4) {
            n5 += n4;
        }
        else if (n == 1) {
            n6 -= n4;
        }
        else if (n == 2) {
            n6 += n4;
        }
        if (array2[0] + n5 >= 0 && array2[0] + n5 <= n2) {
            final int n7 = 0;
            array2[n7] += (short)n5;
            b = true;
        }
        if (array2[1] + n6 >= 0 && array2[1] + n6 <= n3) {
            final int n8 = 1;
            array2[n8] += (short)n6;
            b = true;
        }
        if (b) {
            getInstance().setCameraTileXY_SmallMap(array2[0], array2[1], n2, n3);
        }
    }
}
