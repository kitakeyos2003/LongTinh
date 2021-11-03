// Decompiled with: Procyon 0.5.36
// Class Version: 1
package face;

import common.ScrollText;
import javax.microedition.lcdui.Graphics;
import common.DrawBase;
import means.StringManager;
import base.Param;
import base.Macro;
import base.DCanvas;
import model.PackageObject;

public class SeeDetail {

    private static final String[][] STRING_PROPPART;
    private static final String[][] STRING_ARMPART;
    private static final String STRING_KILL = "Giết chết";
    private static final String STRING_MASSACRE = "Tumo";
    private PackageObject PO;
    private byte bytMove;
    private byte bytRoll;
    private byte bytMax;
    private String[] strOption;
    private int intTimer;
    private short shtWordMove;
    private String[] strContent;
    private static SeeDetail sd;

    static {
        STRING_PROPPART = new String[][]{{"Cap top", "Hat", "Kết cấu"}, {"Lót", "Tay áo", "Kết cấu"}, {"Bảo vệ lòng bàn tay", "Ngón tay", "Kết cấu"}, {"Boots", "Gót chân", "Kết cấu"}, new String[0], {"Cổ áo", "Mặt dây chuyền", "Kết cấu"}, {"Vòng tay", "Đá quý", "Kết cấu"}, {"Vòng tròn tay", "Glyph", "Kết cấu"}};
        STRING_ARMPART = new String[][]{{"Thân giáo", "Spear handle", "Lưỡi giáo", "Giáo"}, {"Hammer", "Tay cầm búa", "Hammerhead", "Sắt"}, {"Thân cây đũa", "Xử lý", "Đá quý", "Glyph"}, {"Cơ thể cung", "Dây cung", "Lưỡi mũi tên", "Mũi tên lông"}, {"Cơ thể thanh kiếm", "Hilt", "Blade", "Thanh kiếm"}, {"Giấy", "Trục gỗ", "Mực", "Thần chú"}};
        SeeDetail.sd = null;
    }

    public static SeeDetail getInstance() {
        return SeeDetail.sd;
    }

    public SeeDetail(final PackageObject po) {
        DCanvas.getInstance().blnSpoolr = false;
        SeeDetail.sd = this;
        this.PO = po;
        if (this.PO.byteEquipType <= 9) {
            this.strOption = SeeDetail.STRING_PROPPART[this.PO.byteEquipType];
        } else if (this.PO.bytQuality >= 4) {
            (this.strOption = new String[6])[0] = "Tumo";
            this.strOption[1] = "Giết chết";
            for (int i = 0; i < 4; i = (byte) (i + 1)) {
                this.strOption[2 + i] = SeeDetail.STRING_ARMPART[this.PO.byteEquipType % 10][i];
            }
        } else {
            this.strOption = new String[4];
            this.strOption = SeeDetail.STRING_ARMPART[this.PO.byteEquipType % 10];
        }
        this.bytMax = (byte) ((Macro.shtRectHeight - 20) / Macro.FONTHEIGHT);
        Param.getInstance().IntRgbColor = DrawBase.getRGB(StringManager.getNewLineW(), Macro.FONTHEIGHT, 1144385017);
        final byte b = 0;
        this.bytRoll = b;
        this.bytMove = b;
        this.setConter((byte) 0);
    }

    public void clear() {
        SeeDetail.sd = null;
        this.PO = null;
        this.strContent = null;
        this.strOption = null;
    }

    public void drawWordMove_Y(final Graphics graphics, final String[] array, final short n, final short shtWordMove) {
        if (array == null) {
            return;
        }
        if (this.intTimer <= 0 && array.length * Macro.FONTHEIGHT > shtWordMove) {
            this.shtWordMove -= 2;
            if (this.shtWordMove < -(array.length * Macro.FONTHEIGHT)) {
                this.shtWordMove = shtWordMove;
            }
        }
        graphics.setClip(0, n, Macro.SCREEN_WIDTH, shtWordMove + 2);
        GameUI.getInstance().drawWordMove(graphics, array, 12, n + this.shtWordMove, (byte) 20);
        DCanvas.getInstance().clearScreen();
    }

    private void logicUp() {
        final int length = this.PO.bytsAggrandizement.length;
        if (this.bytMove <= 0) {
            this.bytMove = 0;
            --this.bytRoll;
            if (this.bytRoll < 0) {
                this.bytRoll = (byte) ((this.bytMax < length) ? (length - this.bytMax) : 0);
                if (length <= 0) {
                    this.bytMove = 0;
                } else {
                    this.bytMove = (byte) ((this.bytMax < length) ? (this.bytMax - 1) : (length - 1));
                }
            }
        } else {
            --this.bytMove;
        }
        this.setConter((byte) (this.bytMove + this.bytRoll));
        DCanvas.getInstance().bytSpoolrFlash = 1;
    }

    private void logicDown() {
        final int length = this.PO.bytsAggrandizement.length;
        if (this.bytMove < ((this.bytMax < length) ? (this.bytMax - 1) : (length - 1))) {
            ++this.bytMove;
        } else {
            this.bytMove = (byte) (this.bytMax - 1);
            ++this.bytRoll;
            if (this.bytRoll >= length - this.bytMax + 1) {
                this.bytRoll = 0;
                this.bytMove = 0;
            }
        }
        this.setConter((byte) (this.bytMove + this.bytRoll));
        DCanvas.getInstance().bytSpoolrFlash = 2;
    }

    public void logic(final int n) {
        if (this.intTimer > 0) {
            this.intTimer -= n;
        }
        if (DCanvas.getInstance().IsKeyRelease(65536) || DCanvas.getInstance().IsKeyRelease(262144)) {
            DCanvas.getInstance().buttonLeftFlash = 0;
        } else if (DCanvas.getInstance().isKeyDown(65536) || DCanvas.getInstance().IsKeyRelease(262144)) {
            DCanvas.getInstance().buttonLeftFlash = 1;
        } else if (DCanvas.getInstance().IsKeyRelease(131072)) {
            DCanvas.getInstance().buttonRightFlash = 0;
            this.clear();
        } else if (DCanvas.getInstance().isKeyDown(131072)) {
            DCanvas.getInstance().buttonRightFlash = 1;
        } else if (DCanvas.getInstance().isKeyDown(4096)) {
            this.logicUp();
        } else if (DCanvas.getInstance().isKeyDown(8192)) {
            this.logicDown();
        }
        if (DCanvas.getInstance().blnIsTouch && DCanvas.getInstance().blnPointerPressed && !DCanvas.getInstance().PointerDwonSoftKey_Left()) {
            if (DCanvas.getInstance().PointerDwonSoftKey_Right()) {
                this.clear();
            } else {
                this.pointKey();
            }
        }
    }

    private void setConter(final byte b) {
        this.strContent = StringManager.wenZi((this.PO.byteEquipType >= 10) ? this.getWeaponDetail(b) : this.getArmorDetail(b), StringManager.getNewLineW());
        this.intTimer = 3000;
        this.shtWordMove = 0;
    }

    private String getWeaponDetail(final byte b) {
        final StringBuffer sb = new StringBuffer();
        byte b2;
        if (this.PO.bytsAggrandizement.length == 0) {
            b2 = 0;
        } else {
            sb.append("Cấp độ:");
            b2 = this.PO.bytsAggrandizement[b];
            sb.append(b2);
        }
        final int length = this.PO.bytsAggrandizement.length;
        if (b2 != 0) {
            sb.append("\n");
            boolean b3 = false;
            if (length >= 6) {
                b3 = true;
            }
            if (this.PO.bytsAttribute != null) {
                switch (b) {
                    case 0: {
                        if (b3) {
                            sb.append(this.PO.strPveNumber);
                            break;
                        }
                        sb.append(this.getPropAttribute(b2));
                        break;
                    }
                    case 1: {
                        if (b3) {
                            sb.append(this.PO.strPvpNumber);
                            break;
                        }
                        sb.append(this.getPropFastness(b2));
                        break;
                    }
                    case 2: {
                        if (b3) {
                            sb.append(this.getPropAttribute(b2));
                            break;
                        }
                        final byte attributeIndex = GameUI.getInstance().getAttributeIndex((byte) 25, this.PO.bytsAttribute);
                        final byte attributeIndex2 = GameUI.getInstance().getAttributeIndex((byte) 26, this.PO.bytsAttribute);
                        if (attributeIndex != -1) {
                            final int n = (b2 * ((this.PO.shtsAttributeInfo[attributeIndex] + this.PO.shtsAttributeInfo[attributeIndex2]) / 2) * 10 + 50) / 100;
                            if (n > 0) {
                                final StringBuffer sb2 = sb;
                                GameUI.getInstance().getClass();
                                sb2.append("+");
                                sb.append(n);
                                sb.append("Sức tấn công vật lý");
                                sb.append("\n");
                            }
                        }
                        sb.append(this.getPropHarm(b2));
                        break;
                    }
                    case 3: {
                        if (b3) {
                            sb.append(this.getPropFastness(b2));
                            break;
                        }
                        sb.append(this.getPropOdds(b2));
                        break;
                    }
                    case 4: {
                        final byte attributeIndex3 = GameUI.getInstance().getAttributeIndex((byte) 25, this.PO.bytsAttribute);
                        final byte attributeIndex4 = GameUI.getInstance().getAttributeIndex((byte) 26, this.PO.bytsAttribute);
                        if (attributeIndex3 != -1) {
                            final int n2 = (b2 * ((this.PO.shtsAttributeInfo[attributeIndex3] + this.PO.shtsAttributeInfo[attributeIndex4]) / 2) * 10 + 50) / 100;
                            if (n2 > 0) {
                                final StringBuffer sb3 = sb;
                                GameUI.getInstance().getClass();
                                sb3.append("+");
                                sb.append(n2);
                                sb.append("Sức tấn công vật lý");
                                sb.append("\n");
                            }
                        }
                        sb.append(this.getPropHarm(b2));
                        break;
                    }
                    case 5: {
                        sb.append(this.getPropOdds(b2));
                        break;
                    }
                }
            }
        } else if (length > 4) {
            sb.append("\n");
            if (b == 0) {
                sb.append(this.PO.strPveNumber);
            } else if (b == 1) {
                sb.append(this.PO.strPvpNumber);
            }
        }
        return sb.toString();
    }

    private String getArmorDetail(final byte b) {
        final StringBuffer sb = new StringBuffer();
        byte b2;
        if (this.PO.bytsAggrandizement.length == 0) {
            b2 = 0;
        } else {
            sb.append("Cấp độ:");
            b2 = this.PO.bytsAggrandizement[b];
            sb.append(b2);
        }
        if (b2 != 0) {
            sb.append("\n");
            if (this.PO.bytsAttribute != null) {
                switch (b) {
                    case 0: {
                        sb.append(this.getPropAttribute(b2));
                        break;
                    }
                    case 1: {
                        sb.append(this.getPropOdds(b2));
                        break;
                    }
                    case 2: {
                        sb.append(this.getPropFastness(b2));
                        break;
                    }
                }
            }
        }
        return sb.toString();
    }

    private String getPropAttribute(final byte b) {
        final StringBuffer sb = new StringBuffer();
        final byte attributeIndex = GameUI.getInstance().getAttributeIndex((byte) 13, this.PO.bytsAttribute);
        if (attributeIndex != -1) {
            final int n = (b * this.PO.shtsAttributeInfo[attributeIndex] * 10 + 50) / 100;
            if (n > 0) {
                final StringBuffer sb2 = sb;
                GameUI.getInstance().getClass();
                sb2.append("+");
                sb.append(n);
                sb.append("Cuộc sống");
                sb.append(n);
                sb.append("\n");
            }
        }
        final byte attributeIndex2 = GameUI.getInstance().getAttributeIndex((byte) 14, this.PO.bytsAttribute);
        if (attributeIndex2 != -1) {
            final int n2 = (b * this.PO.shtsAttributeInfo[attributeIndex2] * 10 + 50) / 100;
            if (n2 > 0) {
                final StringBuffer sb3 = sb;
                GameUI.getInstance().getClass();
                sb3.append("+");
                sb.append(n2);
                sb.append("Ma thuật");
                sb.append("\n");
            }
        }
        final byte attributeIndex3 = GameUI.getInstance().getAttributeIndex((byte) 9, this.PO.bytsAttribute);
        if (attributeIndex3 != -1) {
            final int n3 = (b * this.PO.shtsAttributeInfo[attributeIndex3] * 10 + 50) / 100;
            if (n3 > 0) {
                final StringBuffer sb4 = sb;
                GameUI.getInstance().getClass();
                sb4.append("+");
                sb.append(n3);
                sb.append("Sức mạnh");
                sb.append("\n");
            }
        }
        final byte attributeIndex4 = GameUI.getInstance().getAttributeIndex((byte) 7, this.PO.bytsAttribute);
        if (attributeIndex4 != -1) {
            final int n4 = (b * this.PO.shtsAttributeInfo[attributeIndex4] * 10 + 50) / 100;
            if (n4 > 0) {
                final StringBuffer sb5 = sb;
                GameUI.getInstance().getClass();
                sb5.append("+");
                sb.append(n4);
                sb.append("Độ bền");
                sb.append("\n");
            }
        }
        final byte attributeIndex5 = GameUI.getInstance().getAttributeIndex((byte) 11, this.PO.bytsAttribute);
        if (attributeIndex5 != -1) {
            final int n5 = (b * this.PO.shtsAttributeInfo[attributeIndex5] * 10 + 50) / 100;
            if (n5 > 0) {
                final StringBuffer sb6 = sb;
                GameUI.getInstance().getClass();
                sb6.append("+");
                sb.append(n5);
                sb.append("Nhanh nhẹn");
                sb.append("\n");
            }
        }
        final byte attributeIndex6 = GameUI.getInstance().getAttributeIndex((byte) 8, this.PO.bytsAttribute);
        if (attributeIndex6 != -1) {
            final int n6 = (b * this.PO.shtsAttributeInfo[attributeIndex6] * 10 + 50) / 100;
            if (n6 > 0) {
                final StringBuffer sb7 = sb;
                GameUI.getInstance().getClass();
                sb7.append("+");
                sb.append(n6);
                sb.append("Trí thông minh");
                sb.append("\n");
            }
        }
        final byte attributeIndex7 = GameUI.getInstance().getAttributeIndex((byte) 10, this.PO.bytsAttribute);
        if (attributeIndex7 != -1) {
            final int n7 = (b * this.PO.shtsAttributeInfo[attributeIndex7] * 10 + 50) / 100;
            if (n7 > 0) {
                final StringBuffer sb8 = sb;
                GameUI.getInstance().getClass();
                sb8.append("+");
                sb.append(n7);
                sb.append("Tinh thần");
                sb.append("\n");
            }
        }
        final byte attributeIndex8 = GameUI.getInstance().getAttributeIndex((byte) 12, this.PO.bytsAttribute);
        if (attributeIndex8 != -1) {
            final int n8 = (b * this.PO.shtsAttributeInfo[attributeIndex8] * 10 + 50) / 100;
            if (n8 > 0) {
                final StringBuffer sb9 = sb;
                GameUI.getInstance().getClass();
                sb9.append("+");
                sb.append(n8);
                sb.append("May mắn");
            }
        }
        return sb.toString();
    }

    private String getPropHarm(final byte b) {
        final StringBuffer sb = new StringBuffer();
        final byte attributeIndex = GameUI.getInstance().getAttributeIndex((byte) 27, this.PO.bytsAttribute);
        final byte attributeIndex2 = GameUI.getInstance().getAttributeIndex((byte) 32, this.PO.bytsAttribute);
        if (attributeIndex != -1) {
            final int n = (b * ((this.PO.shtsAttributeInfo[attributeIndex] + this.PO.shtsAttributeInfo[attributeIndex2]) / 2) * 10 + 50) / 100;
            if (n > 0) {
                final StringBuffer sb2 = sb;
                GameUI.getInstance().getClass();
                sb2.append("+");
                sb.append(n);
                sb.append("Sát thương phép thuật");
                sb.append("\n");
            }
        }
        final byte attributeIndex3 = GameUI.getInstance().getAttributeIndex((byte) 28, this.PO.bytsAttribute);
        final byte attributeIndex4 = GameUI.getInstance().getAttributeIndex((byte) 33, this.PO.bytsAttribute);
        if (attributeIndex3 != -1) {
            final int n2 = (b * ((this.PO.shtsAttributeInfo[attributeIndex3] + this.PO.shtsAttributeInfo[attributeIndex4]) / 2) * 10 + 50) / 100;
            if (n2 > 0) {
                final StringBuffer sb3 = sb;
                GameUI.getInstance().getClass();
                sb3.append("+");
                sb.append(n2);
                sb.append("Shadow Magic Damage");
                sb.append("\n");
            }
        }
        final byte attributeIndex5 = GameUI.getInstance().getAttributeIndex((byte) 29, this.PO.bytsAttribute);
        final byte attributeIndex6 = GameUI.getInstance().getAttributeIndex((byte) 34, this.PO.bytsAttribute);
        if (attributeIndex5 != -1) {
            final int n3 = (b * ((this.PO.shtsAttributeInfo[attributeIndex5] + this.PO.shtsAttributeInfo[attributeIndex6]) / 2) * 10 + 50) / 100;
            if (n3 > 0) {
                final StringBuffer sb4 = sb;
                GameUI.getInstance().getClass();
                sb4.append("+");
                sb.append(n3);
                sb.append("Hỏa hoạn ma thuật cháy");
                sb.append("\n");
            }
        }
        final byte attributeIndex7 = GameUI.getInstance().getAttributeIndex((byte) 30, this.PO.bytsAttribute);
        final byte attributeIndex8 = GameUI.getInstance().getAttributeIndex((byte) 35, this.PO.bytsAttribute);
        if (attributeIndex7 != -1) {
            final int n4 = (b * ((this.PO.shtsAttributeInfo[attributeIndex7] + this.PO.shtsAttributeInfo[attributeIndex8]) / 2) * 10 + 50) / 100;
            if (n4 > 0) {
                final StringBuffer sb5 = sb;
                GameUI.getInstance().getClass();
                sb5.append("+");
                sb.append(n4);
                sb.append("Sát thương phép thuật nước");
                sb.append("\n");
            }
        }
        final byte attributeIndex9 = GameUI.getInstance().getAttributeIndex((byte) 31, this.PO.bytsAttribute);
        final byte attributeIndex10 = GameUI.getInstance().getAttributeIndex((byte) 36, this.PO.bytsAttribute);
        if (attributeIndex9 != -1) {
            final int n5 = (b * ((this.PO.shtsAttributeInfo[attributeIndex9] + this.PO.shtsAttributeInfo[attributeIndex10]) / 2) * 10 + 50) / 100;
            if (n5 > 0) {
                final StringBuffer sb6 = sb;
                GameUI.getInstance().getClass();
                sb6.append("+");
                sb.append(n5);
                sb.append("Sát thương phép của trái đất");
            }
        }
        return sb.toString();
    }

    private String getPropOdds(final byte b) {
        final StringBuffer sb = new StringBuffer();
        final byte attributeIndex = GameUI.getInstance().getAttributeIndex((byte) 15, this.PO.bytsAttribute);
        if (attributeIndex != -1) {
            final int n = (b * this.PO.shtsAttributeInfo[attributeIndex] * 10 + 50) / 100;
            if (n > 0) {
                final StringBuffer sb2 = sb;
                GameUI.getInstance().getClass();
                sb2.append("+");
                sb.append(n);
                sb.append("Xếp hạng tấn công quan trọng về thể chất");
                sb.append("\n");
            }
        }
        final byte attributeIndex2 = GameUI.getInstance().getAttributeIndex((byte) 16, this.PO.bytsAttribute);
        if (attributeIndex2 != -1) {
            final int n2 = (b * this.PO.shtsAttributeInfo[attributeIndex2] * 10 + 50) / 100;
            if (n2 > 0) {
                final StringBuffer sb3 = sb;
                GameUI.getInstance().getClass();
                sb3.append("+");
                sb.append(n2);
                sb.append("Xếp hạng tấn công quan trọng kỳ diệu");
                sb.append("\n");
            }
        }
        final byte attributeIndex3 = GameUI.getInstance().getAttributeIndex((byte) 17, this.PO.bytsAttribute);
        if (attributeIndex3 != -1) {
            final int n3 = (b * this.PO.shtsAttributeInfo[attributeIndex3] * 10 + 50) / 100;
            if (n3 > 0) {
                final StringBuffer sb4 = sb;
                GameUI.getInstance().getClass();
                sb4.append("+");
                sb.append(n3);
                sb.append("Xếp hạng lượt truy cập");
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    private String getPropFastness(final byte b) {
        final StringBuffer sb = new StringBuffer();
        final byte attributeIndex = GameUI.getInstance().getAttributeIndex((byte) 19, this.PO.bytsAttribute);
        if (attributeIndex != -1) {
            final int n = (b * this.PO.shtsAttributeInfo[attributeIndex] * 10 + 50) / 100;
            if (n > 0) {
                final StringBuffer sb2 = sb;
                GameUI.getInstance().getClass();
                sb2.append("+");
                sb.append(n);
                sb.append("Phòng thủ");
                sb.append("\n");
            }
        }
        final byte attributeIndex2 = GameUI.getInstance().getAttributeIndex((byte) 18, this.PO.bytsAttribute);
        if (attributeIndex2 != -1) {
            final int n2 = (b * this.PO.shtsAttributeInfo[attributeIndex2] * 10 + 50) / 100;
            if (n2 > 0) {
                final StringBuffer sb3 = sb;
                GameUI.getInstance().getClass();
                sb3.append("+");
                sb.append(n2);
                sb.append("Xếp hạng Dodge");
                sb.append("\n");
            }
        }
        final byte attributeIndex3 = GameUI.getInstance().getAttributeIndex((byte) 20, this.PO.bytsAttribute);
        if (attributeIndex3 != -1) {
            final int n3 = (b * this.PO.shtsAttributeInfo[attributeIndex3] * 10 + 50) / 100;
            if (n3 > 0) {
                final StringBuffer sb4 = sb;
                GameUI.getInstance().getClass();
                sb4.append("+");
                sb.append(n3);
                sb.append("Thần kháng");
                sb.append("\n");
            }
        }
        final byte attributeIndex4 = GameUI.getInstance().getAttributeIndex((byte) 21, this.PO.bytsAttribute);
        if (attributeIndex4 != -1) {
            final int n4 = (b * this.PO.shtsAttributeInfo[attributeIndex4] * 10 + 50) / 100;
            if (n4 > 0) {
                final StringBuffer sb5 = sb;
                GameUI.getInstance().getClass();
                sb5.append("+");
                sb.append(n4);
                sb.append("Bóng kháng");
                sb.append("\n");
            }
        }
        final byte attributeIndex5 = GameUI.getInstance().getAttributeIndex((byte) 22, this.PO.bytsAttribute);
        if (attributeIndex5 != -1) {
            final int n5 = (b * this.PO.shtsAttributeInfo[attributeIndex5] * 10 + 50) / 100;
            if (n5 > 0) {
                final StringBuffer sb6 = sb;
                GameUI.getInstance().getClass();
                sb6.append("+");
                sb.append(n5);
                sb.append("Khả năng chống cháy");
                sb.append("\n");
            }
        }
        final byte attributeIndex6 = GameUI.getInstance().getAttributeIndex((byte) 23, this.PO.bytsAttribute);
        if (attributeIndex6 != -1) {
            final int n6 = (b * this.PO.shtsAttributeInfo[attributeIndex6] * 10 + 50) / 100;
            if (n6 > 0) {
                final StringBuffer sb7 = sb;
                GameUI.getInstance().getClass();
                sb7.append("+");
                sb.append(n6);
                sb.append("Khả năng chịu nước");
                sb.append("\n");
            }
        }
        final byte attributeIndex7 = GameUI.getInstance().getAttributeIndex((byte) 24, this.PO.bytsAttribute);
        if (attributeIndex7 != -1) {
            final int n7 = (b * this.PO.shtsAttributeInfo[attributeIndex7] * 10 + 50) / 100;
            if (n7 > 0) {
                final StringBuffer sb8 = sb;
                GameUI.getInstance().getClass();
                sb8.append("+");
                sb.append(n7);
                sb.append("Kháng đất");
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public void pointKey() {
        int length;
        if (this.PO.bytsAggrandizement.length != 0) {
            length = this.PO.bytsAggrandizement.length;
        } else {
            length = 0;
        }
        if (DCanvas.getInstance().IsPointerDown(12, Macro.FONTHEIGHT + 16, StringManager.getNewLineW(), (byte) ((length > this.bytMax) ? this.bytMax : length) * Macro.FONTHEIGHT - 1)) {
            this.bytMove = (byte) ((DCanvas.getInstance().CurPressedY - Macro.FONTHEIGHT - 16) / Macro.FONTHEIGHT);
            this.setConter((byte) (this.bytMove + this.bytRoll));
        }
        if (DCanvas.getInstance().blnSpoolr) {
            final byte arrowhead_width = ScrollText.arrowhead_width;
            final byte arrowhead_height = ScrollText.arrowhead_height;
            final short shtSpoolrX = DCanvas.getInstance().shtSpoolrX;
            final short shtSpoolrSY = DCanvas.getInstance().shtSpoolrSY;
            final int n = DCanvas.getInstance().shtSpoolrEY - DCanvas.getInstance().shtSpoolrSY;
            final int n2 = arrowhead_width * 3;
            if (DCanvas.getInstance().IsPointerDragged(shtSpoolrX - arrowhead_width, shtSpoolrSY - arrowhead_height, n2, (n >> 1) + arrowhead_height)) {
                this.logicDown();
            } else if (DCanvas.getInstance().IsPointerDragged(shtSpoolrX - arrowhead_width, shtSpoolrSY + (n >> 1) - arrowhead_height, n2, (n >> 1) + arrowhead_height)) {
                this.logicUp();
            } else if (DCanvas.getInstance().IsPointerDown(shtSpoolrX - arrowhead_width, shtSpoolrSY - arrowhead_height, n2, (n >> 1) + arrowhead_height)) {
                this.logicDown();
            } else if (DCanvas.getInstance().IsPointerDown(shtSpoolrX - arrowhead_width, shtSpoolrSY + (n >> 1) - arrowhead_height, n2, (n >> 1) + arrowhead_height)) {
                this.logicUp();
            }
        }
    }
}
