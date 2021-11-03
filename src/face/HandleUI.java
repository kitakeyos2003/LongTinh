// Decompiled with: Procyon 0.5.36
// Class Version: 1
package face;

import common.DrawBase;
import means.DebugFrame;
import model.MakeObject;
import model.MailObject;
import model.ORole;
import model.TeamObject;
import network.NetParse;
import model.TaskObject;
import means.StringManager;
import model.SkillObject;
import java.util.Vector;
import means.ChatContent;
import model.ORPMe;
import model.ORPlayer;
import model.Map;
import java.util.Hashtable;
import model.PackageObject;
import base.GameControl;
import network.NetSend;
import base.Param;
import base.DCanvas;
import javax.microedition.lcdui.Graphics;
import base.Macro;
import model.MenuObject;

public class HandleUI implements UIbase {

    public boolean blnIsThis = false;
    private final String STRING_CUE_SPACE;
    private final String STRING_CUE_EMIT;
    private final String STRING_CUE_INVITE;
    private final String STRING_CUE_ABANDON;
    private final String STRING_CUE_NO_ARM;
    private final String STRING_CUE_NO_LV;
    private final String STRING_CUE_NO_MAGIC;
    private final String STRING_CUE_BAG_FILL;
    private final String STRING_CUE_UP_BAG0;
    private final String STRING_CUE_UP_BAG1;
    private final String STRING_CUE_MATERIAL_LACK;
    private final String STRING_OPTION_EXCHANGE;
    public byte bytState;
    public short shtMove;
    public byte bytHandleMove;
    private byte bytHandleRoll;
    private byte bytHandleMaxH;
    public short[] shtOptionPlace;
    public short[] shtFramePlace;
    public String[] strHandleOption;
    private byte[] bytButtonType;
    private byte bytTemp;
    private final byte space = (byte) 5;
    public static HandleUI handle = null;
    public final int color_OdrawBox;
    public final int color_OfillBox;
    int _num1 = 0;
    int _num2 = 0;

    static {
        HandleUI.handle = null;
    }

    public static HandleUI getInstance() {
        if (HandleUI.handle == null) {
            new HandleUI();
        }
        return HandleUI.handle;
    }

    public HandleUI() {
        this.STRING_CUE_SPACE = "Khoảng cách quá xa";
        this.STRING_CUE_EMIT = "Đã ban hành";
        this.STRING_CUE_INVITE = "Lời mời";
        this.STRING_CUE_ABANDON = "Bỏ cuộc";
        this.STRING_CUE_NO_ARM = "Chưa được trang bị";
        this.STRING_CUE_NO_LV = "Trình độ của bạn chưa đủ";
        this.STRING_CUE_NO_MAGIC = "Trạng thái hiện tại không thể truyền";
        this.STRING_CUE_BAG_FILL = "Túi của bạn đã đầy";
        this.STRING_CUE_UP_BAG0 = "Nâng cấp sẽ có chi phí";
        this.STRING_CUE_UP_BAG1 = "Điểm, tăng 8";
        this.STRING_CUE_MATERIAL_LACK = "Thiếu tài liệu";
        this.STRING_OPTION_EXCHANGE = "Trao đổi";
        this.color_OdrawBox = 4631237;
        this.color_OfillBox = 0xFFFFFF;
        handle = this;
        this.bytHandleMaxH = Macro.bytMaxFullRow;
    }

    public void init() {
        this.bytHandleMove = 0;
        this.bytHandleRoll = 0;
        this.bytButtonType = new byte[3];
        this.shtOptionPlace = new short[3];
        this.shtFramePlace = new short[4];
    }

    public void clean() {
        HandleUI.handle = null;
        this.bytButtonType = null;
        this.shtOptionPlace = null;
        this.strHandleOption = null;
    }

    private void setButton(final int n, final int n2, final int n3) {
        this.bytButtonType[0] = (byte) n;
        this.bytButtonType[1] = (byte) n2;
        this.bytButtonType[2] = (byte) n3;
    }

    public void setHandleOption(final byte bytTemp, final byte bytState, final String[] twoMenu) {
        this.bytTemp = bytTemp;
        this.setTwoMenu(twoMenu);
        this.bytState = bytState;
        if (bytState == -3 || bytState == -4) {
            this.setButton(0, 0, 2);
        } else if (bytState == -1) {
            this.setButton(1, 100, 100);
        } else {
            this.setButton(1, 0, 2);
        }
    }

    public void paint(final Graphics graphics) {
        this.drawTwoMenuRect(graphics);
        this.drawDetails(graphics);
        DCanvas.getInstance().drawSoftkey(graphics, this.bytButtonType[0], this.bytButtonType[1], this.bytButtonType[2], this.blnIsThis);
    }

    public void isThis(final boolean blnIsThis) {
        this.blnIsThis = blnIsThis;
    }

    public void sendPetPacket() {
        if (Param.petValue == null) {
            return;
        }
        if (Param.petValue[0][0] != 0 && Param.petValue[0][3] != 0) {
            ++this._num1;
            if (this._num1 > 600) {
                final int[] array = Param.petValue[0];
                final int n = 1;
                array[n] -= 2;
                NetSend.getInstance().sendPetFeeding(Param.petValue[0][0], Param.petValue[0][1]);
                this._num1 = 0;
            }
        }
        if (Param.petValue[1][0] != 0 && Param.petValue[1][3] != 0) {
            ++this._num2;
            if (this._num2 > 600) {
                final int[] array2 = Param.petValue[1];
                final int n2 = 1;
                array2[n2] -= 2;
                NetSend.getInstance().sendPetFeeding(Param.petValue[1][0], Param.petValue[1][1]);
                this._num2 = 0;
            }
        }
    }

    public void logic(final int n) {
        if (DCanvas.getInstance().IsKeyRelease(65536) || DCanvas.getInstance().IsKeyRelease(262144)) {
            DCanvas.getInstance().buttonLeftFlash = 0;
            this.keyAnswer((byte) (this.bytHandleMove + this.bytHandleRoll));
        } else if (DCanvas.getInstance().isKeyDown(65536) || DCanvas.getInstance().isKeyDown(262144)) {
            DCanvas.getInstance().buttonLeftFlash = 1;
        } else if (DCanvas.getInstance().IsKeyRelease(131072)) {
            DCanvas.getInstance().buttonRightFlash = 0;
            GameControl.getInstance().delUIbase(this.bytTemp);
        } else if (DCanvas.getInstance().isKeyDown(131072)) {
            DCanvas.getInstance().buttonRightFlash = 1;
        } else if (DCanvas.getInstance().isKeyDown(8192)) {
            final byte b = (byte) (this.strHandleOption.length + 1);
            final byte b2 = (byte) this.strHandleOption.length;
            if (this.bytHandleMove < ((b < b2) ? (b - 1) : (b2 - 1))) {
                ++this.bytHandleMove;
            } else {
                this.bytHandleMove = (byte) (b - 1);
                ++this.bytHandleRoll;
                if (this.bytHandleRoll >= b2 - b + 1) {
                    this.bytHandleRoll = 0;
                    this.bytHandleMove = 0;
                }
            }
        } else if (DCanvas.getInstance().isKeyDown(4096)) {
            final byte b3 = (byte) (this.strHandleOption.length + 1);
            final byte b4 = (byte) this.strHandleOption.length;
            if (this.bytHandleMove <= 0) {
                this.bytHandleMove = 0;
                --this.bytHandleRoll;
                if (this.bytHandleRoll < 0) {
                    this.bytHandleRoll = (byte) ((b3 < b4) ? (b4 - b3) : 0);
                    this.bytHandleMove = (byte) ((b3 < b4) ? (b3 - 1) : (b4 - 1));
                }
            } else {
                --this.bytHandleMove;
            }
        } else {
            final byte shortcut = DCanvas.getInstance().getShortcut(false);
            if (shortcut >= 0 && shortcut < this.strHandleOption.length) {
                DCanvas.getInstance().buttonLeftFlash = 0;
                if (shortcut > this.bytHandleMaxH) {
                    this.bytHandleMove = (byte) (this.bytHandleMaxH - 1);
                    this.bytHandleRoll = (byte) (shortcut - this.bytHandleMaxH);
                } else {
                    this.bytHandleMove = shortcut;
                    this.bytHandleRoll = 0;
                }
                this.keyAnswer(shortcut);
            }
        }
        if (DCanvas.getInstance().blnIsTouch && DCanvas.getInstance().blnPointerPressed) {
            if (this.bytState == -1) {
                if (DCanvas.getInstance().IsPointerDown(0, Macro.SCREEN_HEIGHT - Macro.expRow_h - 27 - 26, 54, 24)) {
                    DCanvas.getInstance().buttonLeftFlash = 0;
                    this.keyAnswer((byte) (this.bytHandleMove + this.bytHandleRoll));
                } else if (DCanvas.getInstance().IsPointerDown(Macro.SCREEN_WIDTH - 54, Macro.SCREEN_HEIGHT - Macro.expRow_h - 27 - 26, 54, 24)) {
                    DCanvas.getInstance().buttonRightFlash = 0;
                    GameControl.getInstance().delUIbase(this.bytTemp);
                }
            }
            if (DCanvas.getInstance().PointerDwonSoftKey_Left()) {
                DCanvas.getInstance().buttonLeftFlash = 0;
                this.keyAnswer((byte) (this.bytHandleMove + this.bytHandleRoll));
            } else if (DCanvas.getInstance().PointerDwonSoftKey_Right() || DCanvas.getInstance().IsPointerDownOutSideFrame(this.shtFramePlace[0], this.shtFramePlace[1], this.shtFramePlace[2], this.shtFramePlace[3])) {
                DCanvas.getInstance().buttonRightFlash = 0;
                GameControl.getInstance().delUIbase(this.bytTemp);
            } else {
                this.setPointer();
            }
        }
    }

    private void keyAnswer(final byte bytHandleMove) {
        boolean b = true;
        final String s = this.strHandleOption[bytHandleMove];
        Param.getInstance().bytHandleMove = bytHandleMove;
        switch (this.bytState) {
            case -6: {
                final String s2 = s;
                MenuUI.getInstance().getClass();
                if (s2.indexOf("查看属性") == -1) {
                    break;
                }
                final PackageObject packageObject = (PackageObject) Param.getInstance().hOtherPackageEquip.get(new Integer(MenuUI.getInstance().rView.getViewCurrentIndex()));
                if (packageObject.strInfo.equals("")) {
                    GameUI.getInstance().getEquioAttribute(Param.getInstance().hOtherPackageEquip, MenuUI.getInstance().rView.getViewCurrentIndex());
                    final FullInfo fullInfo = new FullInfo(String.valueOf(packageObject.strName) + ((packageObject.bytAggrandizementLv > 0) ? (" +" + packageObject.bytAggrandizementLv) : ""), packageObject.intColor, this.bytState);
                    FullInfo.getInstance().setFullRectMenu_Vector();
                    break;
                }
                new FullInfo(packageObject.strName, packageObject.intColor, this.bytState);
                FullInfo.getInstance().setFullRectMenu(packageObject.strInfo);
                break;
            }
            case -11: {
                final Hashtable hashtable = (Hashtable) Param.getInstance().hMallPackage.get(new Integer(MenuUI.getInstance().tabStyleInstance.getTabCurrentIndex()));
                if (s.indexOf("购买单个") != -1) {
                    Param.getInstance().intMallNum = 1;
                    if (Macro.FEE_POINT >= ((PackageObject) hashtable.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()))).intPaiMaiPrice) {
                        MenuUI.getInstance().mallShop();
                        break;
                    }
                    DCanvas.getInstance().addInformation("点数不足");
                    break;
                } else {
                    if (s.indexOf("购买多个") != -1) {
                        MenuUI.getInstance().setTwoNumber("输入购买数量", ((PackageObject) hashtable.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()))).bytBagPlace, 0, bytHandleMove);
                        break;
                    }
                    break;
                }
            }
            case -4: {
                if (s.indexOf("查看") != -1) {
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendAreaMapInfo((byte) 1, GameUI.getInstance().shtMoveMapID, Map.getInstance().bytCurAreaMapClanType, 0);
                    GameControl.getInstance().CreateState((byte) 3);
                    MenuUI.getInstance().setState((byte) (-90), "地图");
                    break;
                }
                if (s.indexOf("进入") != -1) {
                    if (GameUI.getInstance().shtMoveMapID != Map.getInstance().shtMapId) {
                        DCanvas.getInstance().setNetLoad(true);
                        NetSend.getInstance().sendAreaMapInfo((byte) 2, GameUI.getInstance().shtMoveMapID, Map.getInstance().bytCurAreaMapClanType, 0);
                        break;
                    }
                    DCanvas.getInstance().addInformation("已处于当前场景");
                    break;
                } else {
                    if (s.indexOf("查找NPC") != -1) {
                        DCanvas.getInstance().setNetLoad(true);
                        NetSend.getInstance().sendAreaMapInfo((byte) 3, GameUI.getInstance().shtMoveMapID, Map.getInstance().bytCurAreaMapClanType, 0);
                        GameControl.getInstance().CreateState((byte) 3);
                        MenuUI.getInstance().setState((byte) (-91), "地图");
                        break;
                    }
                    if (s.indexOf("返回") != -1) {
                        GameControl.getInstance().delUIbase(5);
                        break;
                    }
                    if (s.indexOf("世界地图") == -1) {
                        break;
                    }
                    GameUI.getInstance().bytMapState = 2;
                    if (!GameUI.getInstance().isMapExist((byte) 2, Map.getInstance().bytCurAreaMapClanType)) {
                        DCanvas.getInstance().setNetLoad(true);
                        NetSend.getInstance().sendAreaMapInfo((byte) 4, GameUI.getInstance().shtMoveMapID, Map.getInstance().bytCurAreaMapClanType, 0);
                        GameUI.getInstance().setDialog((byte) 4);
                        break;
                    }
                    GameUI.getInstance().initMapinfo();
                    break;
                }
            }
            case -45: {
                NetSend.getInstance().sendUnlockEvent(Param.getInstance().bSelectBox.intUserId);
                break;
            }
            case -1: {
                if (s.indexOf("打开商店") != -1) {
                    GameControl.getInstance().CreateState((byte) 3);
                    MenuUI.getInstance().setState((byte) (-72), ((ORPlayer) Param.getInstance().oSelectRole).strStallInfo);
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendShopOpen();
                    NetSend.getInstance().sendShop_OpenBag((byte) 1);
                    break;
                }
                final String s3 = s;
                GameUI.getInstance().getClass();
                if (s3.indexOf("查　　看") != -1) {
                    GameControl.getInstance().CreateState((byte) 3);
                    MenuUI.getInstance().setState((byte) (-6), Param.getInstance().oSelectRole.strNickName);
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendRoleEQUIP(Param.getInstance().oSelectRole.intUserId);
                    break;
                }
                final String s4 = s;
                GameUI.getInstance().getClass();
                if (s4.indexOf("私　　聊") != -1) {
                    FormDes.getInstance().setSingleChat(Param.getInstance().oSelectRole.strNickName);
                    break;
                }
                final String s5 = s;
                GameUI.getInstance().getClass();
                if (s5.indexOf("交　　易") != -1) {
                    if (ORPMe.ME.checkArea(Param.getInstance().oSelectRole.bytTileX, Param.getInstance().oSelectRole.bytTileY, (byte) 6, Param.getInstance().oSelectRole.bytPicSize)) {
                        final StringBuffer sb = new StringBuffer();
                        sb.append("已发出");
                        final StringBuffer sb2 = sb;
                        GameUI.getInstance().getClass();
                        sb2.append("交　　易".substring(0, 1));
                        final StringBuffer sb3 = sb;
                        GameUI.getInstance().getClass();
                        sb3.append("交　　易".substring(3));
                        sb.append("邀请");
                        DCanvas.getInstance().addInformation(sb.toString());
                        DCanvas.getInstance().setNetLoad(true);
                        NetSend.getInstance().sendDeal((byte) 1, Param.getInstance().oSelectRole.intUserId);
                        break;
                    }
                    DCanvas.getInstance().addInformation("距离太远了");
                    break;
                } else {
                    final String s6 = s;
                    GameUI.getInstance().getClass();
                    if (s6.indexOf("决　　斗") != -1) {
                        if (Map.getInstance().bytPk != 1 && Map.getInstance().bytPk != 3) {
                            DCanvas.getInstance().addInformation("该地图不可决斗");
                            break;
                        }
                        if (ORPMe.ME.checkArea(Param.getInstance().oSelectRole.bytTileX, Param.getInstance().oSelectRole.bytTileY, (byte) 6, Param.getInstance().oSelectRole.bytPicSize)) {
                            final StringBuffer sb4 = new StringBuffer();
                            sb4.append("已发出");
                            final StringBuffer sb5 = sb4;
                            GameUI.getInstance().getClass();
                            sb5.append("决　　斗".substring(0, 1));
                            final StringBuffer sb6 = sb4;
                            GameUI.getInstance().getClass();
                            sb6.append("决　　斗".substring(3));
                            sb4.append("邀请");
                            DCanvas.getInstance().addInformation(sb4.toString());
                            NetSend.getInstance().sendPlayerPk(Param.getInstance().oSelectRole.intUserId, (byte) 1);
                            break;
                        }
                        DCanvas.getInstance().addInformation("距离太远了");
                        break;
                    } else {
                        final String s7 = s;
                        GameUI.getInstance().getClass();
                        if (s7.indexOf("加为好友") != -1) {
                            NetSend.getInstance().sendHailFellow((byte) 2, (byte) 1, Param.getInstance().oSelectRole.strNickName);
                            break;
                        }
                        final String s8 = s;
                        GameUI.getInstance().getClass();
                        if (s8.indexOf("队伍邀请") != -1) {
                            NetSend.getInstance().sendTeam_add(Param.getInstance().oSelectRole.strNickName);
                            break;
                        }
                        if (s.indexOf("拜　　师") != -1) {
                            DCanvas.getInstance().addInformation("已向" + Param.getInstance().oSelectRole.strNickName + "发出拜师请求", 16642234);
                            NetSend.getInstance().sendShiTu((byte) 2, Param.getInstance().oSelectRole.intUserId);
                            break;
                        }
                        if (s.indexOf("收　　徒") != -1) {
                            DCanvas.getInstance().addInformation("已向" + Param.getInstance().oSelectRole.strNickName + "发出收徒邀请", 16642234);
                            NetSend.getInstance().sendShiTu((byte) 1, Param.getInstance().oSelectRole.intUserId);
                            break;
                        }
                        final String s9 = s;
                        MenuUI.getInstance().getClass();
                        if (s9.indexOf("邀请入帮") != -1) {
                            NetSend.getInstance().sendConsortiaAdd(Param.getInstance().oSelectRole.strNickName);
                            break;
                        }
                        break;
                    }
                }
            }
            case 42: {
                final String s10 = s;
                MenuUI.getInstance().getClass();
                if (s10.indexOf("聊　　天") != -1) {
                    FormDes.getInstance().showForm((byte) 40);
                    break;
                }
                final String s11 = s;
                MenuUI.getInstance().getClass();
                if (s11.indexOf("私　　聊") != -1) {
                    String singleChat = "";
                    final byte bytChatNoteType = Param.getInstance().bytChatNoteType;
                    Param.getInstance().getClass();
                    if (bytChatNoteType == 0) {
                        singleChat = ((ChatContent) ((Vector) Param.getInstance().vChatNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                    } else {
                        final byte bytChatNoteType2 = Param.getInstance().bytChatNoteType;
                        Param.getInstance().getClass();
                        if (bytChatNoteType2 == 2) {
                            singleChat = ((ChatContent) ((Vector) Param.getInstance().vChatRaceNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                        } else {
                            final byte bytChatNoteType3 = Param.getInstance().bytChatNoteType;
                            Param.getInstance().getClass();
                            if (bytChatNoteType3 == 6) {
                                singleChat = ((ChatContent) ((Vector) Param.getInstance().vChatSingleNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                            } else {
                                final byte bytChatNoteType4 = Param.getInstance().bytChatNoteType;
                                Param.getInstance().getClass();
                                if (bytChatNoteType4 == 4) {
                                    singleChat = ((ChatContent) ((Vector) Param.getInstance().vChatConsortianNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                                } else {
                                    final byte bytChatNoteType5 = Param.getInstance().bytChatNoteType;
                                    Param.getInstance().getClass();
                                    if (bytChatNoteType5 == 5) {
                                        singleChat = ((ChatContent) ((Vector) Param.getInstance().vChatTeamNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                                    } else {
                                        final byte bytChatNoteType6 = Param.getInstance().bytChatNoteType;
                                        Param.getInstance().getClass();
                                        if (bytChatNoteType6 == 3) {
                                            singleChat = ((ChatContent) ((Vector) Param.getInstance().vChatMapNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                                        } else {
                                            final byte bytChatNoteType7 = Param.getInstance().bytChatNoteType;
                                            Param.getInstance().getClass();
                                            if (bytChatNoteType7 == 1) {
                                                singleChat = ((ChatContent) ((Vector) Param.getInstance().vChatWordNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    FormDes.getInstance().setSingleChat(singleChat);
                    break;
                }
                final String s12 = s;
                MenuUI.getInstance().getClass();
                if (s12.indexOf("频道发言") != -1) {
                    byte bytOldChatChannel = 0;
                    final byte bytChatNoteType8 = Param.getInstance().bytChatNoteType;
                    Param.getInstance().getClass();
                    if (bytChatNoteType8 == 6) {
                        bytOldChatChannel = 0;
                    } else {
                        final byte bytChatNoteType9 = Param.getInstance().bytChatNoteType;
                        Param.getInstance().getClass();
                        if (bytChatNoteType9 == 4) {
                            bytOldChatChannel = 4;
                        } else {
                            final byte bytChatNoteType10 = Param.getInstance().bytChatNoteType;
                            Param.getInstance().getClass();
                            if (bytChatNoteType10 == 2) {
                                bytOldChatChannel = 10;
                            } else {
                                final byte bytChatNoteType11 = Param.getInstance().bytChatNoteType;
                                Param.getInstance().getClass();
                                if (bytChatNoteType11 == 5) {
                                    bytOldChatChannel = 3;
                                } else {
                                    final byte bytChatNoteType12 = Param.getInstance().bytChatNoteType;
                                    Param.getInstance().getClass();
                                    if (bytChatNoteType12 == 3) {
                                        bytOldChatChannel = 2;
                                    } else {
                                        final byte bytChatNoteType13 = Param.getInstance().bytChatNoteType;
                                        Param.getInstance().getClass();
                                        if (bytChatNoteType13 == 1) {
                                            bytOldChatChannel = 1;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    Param.getInstance().bytOldChatChannel = bytOldChatChannel;
                    FormDes.getInstance().showForm((byte) 40);
                    break;
                }
                final String s13 = s;
                GameUI.getInstance().getClass();
                if (s13.indexOf("加为好友") != -1) {
                    String s14 = "";
                    final byte bytChatNoteType14 = Param.getInstance().bytChatNoteType;
                    Param.getInstance().getClass();
                    if (bytChatNoteType14 == 0) {
                        s14 = ((ChatContent) ((Vector) Param.getInstance().vChatNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                    } else {
                        final byte bytChatNoteType15 = Param.getInstance().bytChatNoteType;
                        Param.getInstance().getClass();
                        if (bytChatNoteType15 == 2) {
                            s14 = ((ChatContent) ((Vector) Param.getInstance().vChatRaceNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                        } else {
                            final byte bytChatNoteType16 = Param.getInstance().bytChatNoteType;
                            Param.getInstance().getClass();
                            if (bytChatNoteType16 == 6) {
                                s14 = ((ChatContent) ((Vector) Param.getInstance().vChatSingleNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                            } else {
                                final byte bytChatNoteType17 = Param.getInstance().bytChatNoteType;
                                Param.getInstance().getClass();
                                if (bytChatNoteType17 == 4) {
                                    s14 = ((ChatContent) ((Vector) Param.getInstance().vChatConsortianNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                                } else {
                                    final byte bytChatNoteType18 = Param.getInstance().bytChatNoteType;
                                    Param.getInstance().getClass();
                                    if (bytChatNoteType18 == 5) {
                                        s14 = ((ChatContent) ((Vector) Param.getInstance().vChatTeamNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                                    } else {
                                        final byte bytChatNoteType19 = Param.getInstance().bytChatNoteType;
                                        Param.getInstance().getClass();
                                        if (bytChatNoteType19 == 3) {
                                            s14 = ((ChatContent) ((Vector) Param.getInstance().vChatMapNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                                        } else {
                                            final byte bytChatNoteType20 = Param.getInstance().bytChatNoteType;
                                            Param.getInstance().getClass();
                                            if (bytChatNoteType20 == 1) {
                                                s14 = ((ChatContent) ((Vector) Param.getInstance().vChatWordNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    NetSend.getInstance().sendHailFellow((byte) 2, (byte) 1, s14);
                    break;
                }
                final String s15 = s;
                MenuUI.getInstance().getClass();
                if (s15.indexOf("加为黑名") != -1) {
                    String s16 = "";
                    final byte bytChatNoteType21 = Param.getInstance().bytChatNoteType;
                    Param.getInstance().getClass();
                    if (bytChatNoteType21 == 0) {
                        s16 = ((ChatContent) ((Vector) Param.getInstance().vChatNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                    } else {
                        final byte bytChatNoteType22 = Param.getInstance().bytChatNoteType;
                        Param.getInstance().getClass();
                        if (bytChatNoteType22 == 2) {
                            s16 = ((ChatContent) ((Vector) Param.getInstance().vChatRaceNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                        } else {
                            final byte bytChatNoteType23 = Param.getInstance().bytChatNoteType;
                            Param.getInstance().getClass();
                            if (bytChatNoteType23 == 6) {
                                s16 = ((ChatContent) ((Vector) Param.getInstance().vChatSingleNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                            } else {
                                final byte bytChatNoteType24 = Param.getInstance().bytChatNoteType;
                                Param.getInstance().getClass();
                                if (bytChatNoteType24 == 4) {
                                    s16 = ((ChatContent) ((Vector) Param.getInstance().vChatConsortianNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                                } else {
                                    final byte bytChatNoteType25 = Param.getInstance().bytChatNoteType;
                                    Param.getInstance().getClass();
                                    if (bytChatNoteType25 == 5) {
                                        s16 = ((ChatContent) ((Vector) Param.getInstance().vChatTeamNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                                    } else {
                                        final byte bytChatNoteType26 = Param.getInstance().bytChatNoteType;
                                        Param.getInstance().getClass();
                                        if (bytChatNoteType26 == 3) {
                                            s16 = ((ChatContent) ((Vector) Param.getInstance().vChatMapNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                                        } else {
                                            final byte bytChatNoteType27 = Param.getInstance().bytChatNoteType;
                                            Param.getInstance().getClass();
                                            if (bytChatNoteType27 == 1) {
                                                s16 = ((ChatContent) ((Vector) Param.getInstance().vChatWordNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    NetSend.getInstance().sendHailFellow((byte) 2, (byte) 2, s16);
                    break;
                }
                final String s17 = s;
                MenuUI.getInstance().getClass();
                if (s17.indexOf("加为仇人") != -1) {
                    String s18 = "";
                    final byte bytChatNoteType28 = Param.getInstance().bytChatNoteType;
                    Param.getInstance().getClass();
                    if (bytChatNoteType28 == 0) {
                        s18 = ((ChatContent) ((Vector) Param.getInstance().vChatNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                    } else {
                        final byte bytChatNoteType29 = Param.getInstance().bytChatNoteType;
                        Param.getInstance().getClass();
                        if (bytChatNoteType29 == 2) {
                            s18 = ((ChatContent) ((Vector) Param.getInstance().vChatRaceNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                        } else {
                            final byte bytChatNoteType30 = Param.getInstance().bytChatNoteType;
                            Param.getInstance().getClass();
                            if (bytChatNoteType30 == 6) {
                                s18 = ((ChatContent) ((Vector) Param.getInstance().vChatSingleNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                            } else {
                                final byte bytChatNoteType31 = Param.getInstance().bytChatNoteType;
                                Param.getInstance().getClass();
                                if (bytChatNoteType31 == 4) {
                                    s18 = ((ChatContent) ((Vector) Param.getInstance().vChatConsortianNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                                } else {
                                    final byte bytChatNoteType32 = Param.getInstance().bytChatNoteType;
                                    Param.getInstance().getClass();
                                    if (bytChatNoteType32 == 5) {
                                        s18 = ((ChatContent) ((Vector) Param.getInstance().vChatTeamNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                                    } else {
                                        final byte bytChatNoteType33 = Param.getInstance().bytChatNoteType;
                                        Param.getInstance().getClass();
                                        if (bytChatNoteType33 == 3) {
                                            s18 = ((ChatContent) ((Vector) Param.getInstance().vChatMapNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                                        } else {
                                            final byte bytChatNoteType34 = Param.getInstance().bytChatNoteType;
                                            Param.getInstance().getClass();
                                            if (bytChatNoteType34 == 1) {
                                                s18 = ((ChatContent) ((Vector) Param.getInstance().vChatWordNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    NetSend.getInstance().sendHailFellow((byte) 2, (byte) 3, s18);
                    break;
                }
                final String s19 = s;
                MenuUI.getInstance().getClass();
                if (s19.indexOf("邀请组队") != -1) {
                    String s20 = "";
                    final byte bytChatNoteType35 = Param.getInstance().bytChatNoteType;
                    Param.getInstance().getClass();
                    if (bytChatNoteType35 == 0) {
                        s20 = ((ChatContent) ((Vector) Param.getInstance().vChatNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                    } else {
                        final byte bytChatNoteType36 = Param.getInstance().bytChatNoteType;
                        Param.getInstance().getClass();
                        if (bytChatNoteType36 == 2) {
                            s20 = ((ChatContent) ((Vector) Param.getInstance().vChatRaceNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                        } else {
                            final byte bytChatNoteType37 = Param.getInstance().bytChatNoteType;
                            Param.getInstance().getClass();
                            if (bytChatNoteType37 == 6) {
                                s20 = ((ChatContent) ((Vector) Param.getInstance().vChatSingleNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                            } else {
                                final byte bytChatNoteType38 = Param.getInstance().bytChatNoteType;
                                Param.getInstance().getClass();
                                if (bytChatNoteType38 == 4) {
                                    s20 = ((ChatContent) ((Vector) Param.getInstance().vChatConsortianNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                                } else {
                                    final byte bytChatNoteType39 = Param.getInstance().bytChatNoteType;
                                    Param.getInstance().getClass();
                                    if (bytChatNoteType39 == 3) {
                                        s20 = ((ChatContent) ((Vector) Param.getInstance().vChatMapNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                                    } else {
                                        final byte bytChatNoteType40 = Param.getInstance().bytChatNoteType;
                                        Param.getInstance().getClass();
                                        if (bytChatNoteType40 == 1) {
                                            s20 = ((ChatContent) ((Vector) Param.getInstance().vChatWordNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    NetSend.getInstance().sendTeam_add(s20);
                    break;
                }
                final String s21 = s;
                MenuUI.getInstance().getClass();
                if (s21.indexOf("添加成员") != -1) {
                    String s22 = "";
                    final byte bytChatNoteType41 = Param.getInstance().bytChatNoteType;
                    Param.getInstance().getClass();
                    if (bytChatNoteType41 == 0) {
                        s22 = ((ChatContent) ((Vector) Param.getInstance().vChatNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                    } else {
                        final byte bytChatNoteType42 = Param.getInstance().bytChatNoteType;
                        Param.getInstance().getClass();
                        if (bytChatNoteType42 == 2) {
                            s22 = ((ChatContent) ((Vector) Param.getInstance().vChatRaceNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                        } else {
                            final byte bytChatNoteType43 = Param.getInstance().bytChatNoteType;
                            Param.getInstance().getClass();
                            if (bytChatNoteType43 == 6) {
                                s22 = ((ChatContent) ((Vector) Param.getInstance().vChatSingleNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                            } else {
                                final byte bytChatNoteType44 = Param.getInstance().bytChatNoteType;
                                Param.getInstance().getClass();
                                if (bytChatNoteType44 == 4) {
                                    s22 = ((ChatContent) ((Vector) Param.getInstance().vChatConsortianNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                                } else {
                                    final byte bytChatNoteType45 = Param.getInstance().bytChatNoteType;
                                    Param.getInstance().getClass();
                                    if (bytChatNoteType45 == 5) {
                                        s22 = ((ChatContent) ((Vector) Param.getInstance().vChatTeamNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                                    } else {
                                        final byte bytChatNoteType46 = Param.getInstance().bytChatNoteType;
                                        Param.getInstance().getClass();
                                        if (bytChatNoteType46 == 3) {
                                            s22 = ((ChatContent) ((Vector) Param.getInstance().vChatMapNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                                        } else {
                                            final byte bytChatNoteType47 = Param.getInstance().bytChatNoteType;
                                            Param.getInstance().getClass();
                                            if (bytChatNoteType47 == 1) {
                                                s22 = ((ChatContent) ((Vector) Param.getInstance().vChatWordNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    NetSend.getInstance().sendConsortiaAdd(s22);
                    break;
                }
                final String s23 = s;
                MenuUI.getInstance().getClass();
                if (s23.indexOf("查看物品") != -1) {
                    String dialogOption = "";
                    final byte bytChatNoteType48 = Param.getInstance().bytChatNoteType;
                    Param.getInstance().getClass();
                    if (bytChatNoteType48 == 0) {
                        dialogOption = ((ChatContent) ((Vector) Param.getInstance().vChatNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).strsContent;
                    } else {
                        final byte bytChatNoteType49 = Param.getInstance().bytChatNoteType;
                        Param.getInstance().getClass();
                        if (bytChatNoteType49 == 2) {
                            dialogOption = ((ChatContent) ((Vector) Param.getInstance().vChatRaceNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).strsContent;
                        } else {
                            final byte bytChatNoteType50 = Param.getInstance().bytChatNoteType;
                            Param.getInstance().getClass();
                            if (bytChatNoteType50 == 6) {
                                dialogOption = ((ChatContent) ((Vector) Param.getInstance().vChatSingleNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).strsContent;
                            } else {
                                final byte bytChatNoteType51 = Param.getInstance().bytChatNoteType;
                                Param.getInstance().getClass();
                                if (bytChatNoteType51 == 4) {
                                    dialogOption = ((ChatContent) ((Vector) Param.getInstance().vChatConsortianNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).strsContent;
                                } else {
                                    final byte bytChatNoteType52 = Param.getInstance().bytChatNoteType;
                                    Param.getInstance().getClass();
                                    if (bytChatNoteType52 == 5) {
                                        dialogOption = ((ChatContent) ((Vector) Param.getInstance().vChatTeamNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).strsContent;
                                    } else {
                                        final byte bytChatNoteType53 = Param.getInstance().bytChatNoteType;
                                        Param.getInstance().getClass();
                                        if (bytChatNoteType53 == 3) {
                                            dialogOption = ((ChatContent) ((Vector) Param.getInstance().vChatMapNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).strsContent;
                                        } else {
                                            final byte bytChatNoteType54 = Param.getInstance().bytChatNoteType;
                                            Param.getInstance().getClass();
                                            if (bytChatNoteType54 == 1) {
                                                dialogOption = ((ChatContent) ((Vector) Param.getInstance().vChatWordNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).strsContent;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (Integer.parseInt(dialogOption.substring(0, 1)) <= 1) {
                        DCanvas.getInstance().setNetLoad(true);
                        NetSend.getInstance().sendSeeChatProp(Integer.parseInt(dialogOption.substring(dialogOption.indexOf("F") + 1, dialogOption.indexOf("["))));
                        break;
                    }
                    MenuUI.getInstance().setDialogOption(dialogOption);
                    break;
                } else {
                    final String s24 = s;
                    MenuUI.getInstance().getClass();
                    if (s24.indexOf("发送邮件") != -1) {
                        String s25 = "";
                        final byte bytChatNoteType55 = Param.getInstance().bytChatNoteType;
                        Param.getInstance().getClass();
                        if (bytChatNoteType55 == 0) {
                            s25 = ((ChatContent) ((Vector) Param.getInstance().vChatNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                        } else {
                            final byte bytChatNoteType56 = Param.getInstance().bytChatNoteType;
                            Param.getInstance().getClass();
                            if (bytChatNoteType56 == 2) {
                                s25 = ((ChatContent) ((Vector) Param.getInstance().vChatRaceNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                            } else {
                                final byte bytChatNoteType57 = Param.getInstance().bytChatNoteType;
                                Param.getInstance().getClass();
                                if (bytChatNoteType57 == 6) {
                                    s25 = ((ChatContent) ((Vector) Param.getInstance().vChatSingleNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                                } else {
                                    final byte bytChatNoteType58 = Param.getInstance().bytChatNoteType;
                                    Param.getInstance().getClass();
                                    if (bytChatNoteType58 == 4) {
                                        s25 = ((ChatContent) ((Vector) Param.getInstance().vChatConsortianNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                                    } else {
                                        final byte bytChatNoteType59 = Param.getInstance().bytChatNoteType;
                                        Param.getInstance().getClass();
                                        if (bytChatNoteType59 == 5) {
                                            s25 = ((ChatContent) ((Vector) Param.getInstance().vChatTeamNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                                        } else {
                                            final byte bytChatNoteType60 = Param.getInstance().bytChatNoteType;
                                            Param.getInstance().getClass();
                                            if (bytChatNoteType60 == 3) {
                                                s25 = ((ChatContent) ((Vector) Param.getInstance().vChatMapNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                                            } else {
                                                final byte bytChatNoteType61 = Param.getInstance().bytChatNoteType;
                                                Param.getInstance().getClass();
                                                if (bytChatNoteType61 == 1) {
                                                    s25 = ((ChatContent) ((Vector) Param.getInstance().vChatWordNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        FormDes.getInstance().setMail(s25, "");
                        break;
                    }
                    final String s26 = s;
                    MenuUI.getInstance().getClass();
                    if (s26.indexOf("回          复") != -1) {
                        String gmAppraise;
                        if (Param.getInstance().bytChatNoteType == 0) {
                            gmAppraise = ((ChatContent) ((Vector) Param.getInstance().vChatNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                        } else {
                            gmAppraise = ((ChatContent) ((Vector) Param.getInstance().vChatSingleNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                        }
                        FormDes.getInstance().setGmAppraise(gmAppraise);
                        break;
                    }
                    final String s27 = s;
                    MenuUI.getInstance().getClass();
                    if (s27.indexOf("查　　看") != -1) {
                        String s28 = "";
                        final byte bytChatNoteType62 = Param.getInstance().bytChatNoteType;
                        Param.getInstance().getClass();
                        if (bytChatNoteType62 == 0) {
                            s28 = ((ChatContent) ((Vector) Param.getInstance().vChatNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                        } else {
                            final byte bytChatNoteType63 = Param.getInstance().bytChatNoteType;
                            Param.getInstance().getClass();
                            if (bytChatNoteType63 == 2) {
                                s28 = ((ChatContent) ((Vector) Param.getInstance().vChatRaceNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                            } else {
                                final byte bytChatNoteType64 = Param.getInstance().bytChatNoteType;
                                Param.getInstance().getClass();
                                if (bytChatNoteType64 == 6) {
                                    s28 = ((ChatContent) ((Vector) Param.getInstance().vChatSingleNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                                } else {
                                    final byte bytChatNoteType65 = Param.getInstance().bytChatNoteType;
                                    Param.getInstance().getClass();
                                    if (bytChatNoteType65 == 4) {
                                        s28 = ((ChatContent) ((Vector) Param.getInstance().vChatConsortianNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                                    } else {
                                        final byte bytChatNoteType66 = Param.getInstance().bytChatNoteType;
                                        Param.getInstance().getClass();
                                        if (bytChatNoteType66 == 5) {
                                            s28 = ((ChatContent) ((Vector) Param.getInstance().vChatTeamNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                                        } else {
                                            final byte bytChatNoteType67 = Param.getInstance().bytChatNoteType;
                                            Param.getInstance().getClass();
                                            if (bytChatNoteType67 == 3) {
                                                s28 = ((ChatContent) ((Vector) Param.getInstance().vChatMapNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                                            } else {
                                                final byte bytChatNoteType68 = Param.getInstance().bytChatNoteType;
                                                Param.getInstance().getClass();
                                                if (bytChatNoteType68 == 1) {
                                                    s28 = ((ChatContent) ((Vector) Param.getInstance().vChatWordNote.elementAt(MenuUI.getInstance().getOneMove())).elementAt(0)).name;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        MenuUI.getInstance().getClass();
                        final FullInfo fullInfo2 = new FullInfo("人物资料", 0, this.bytState);
                        DCanvas.getInstance().setNetLoad(true);
                        NetSend.getInstance().sendRoleSee((byte) 2, 0, s28);
                        break;
                    }
                    final String s29 = s;
                    MenuUI.getInstance().getClass();
                    if (s29.indexOf("发送消息") != -1) {
                        FormDes.getInstance().showForm((byte) 40);
                        break;
                    }
                    final String s30 = s;
                    MenuUI.getInstance().getClass();
                    if (s30.indexOf("清除信息") == -1) {
                        break;
                    }
                    final byte bytChatNoteType69 = Param.getInstance().bytChatNoteType;
                    Param.getInstance().getClass();
                    if (bytChatNoteType69 == 0) {
                        if (Param.getInstance().vChatNote != null) {
                            Param.getInstance().vChatNote = null;
                            break;
                        }
                        break;
                    } else {
                        final byte bytChatNoteType70 = Param.getInstance().bytChatNoteType;
                        Param.getInstance().getClass();
                        if (bytChatNoteType70 == 2) {
                            if (Param.getInstance().vChatRaceNote != null) {
                                Param.getInstance().vChatRaceNote = null;
                                break;
                            }
                            break;
                        } else {
                            final byte bytChatNoteType71 = Param.getInstance().bytChatNoteType;
                            Param.getInstance().getClass();
                            if (bytChatNoteType71 == 6) {
                                if (Param.getInstance().vChatSingleNote != null) {
                                    Param.getInstance().vChatSingleNote = null;
                                    break;
                                }
                                break;
                            } else {
                                final byte bytChatNoteType72 = Param.getInstance().bytChatNoteType;
                                Param.getInstance().getClass();
                                if (bytChatNoteType72 == 5) {
                                    if (Param.getInstance().vChatTeamNote != null) {
                                        Param.getInstance().vChatTeamNote = null;
                                        break;
                                    }
                                    break;
                                } else {
                                    final byte bytChatNoteType73 = Param.getInstance().bytChatNoteType;
                                    Param.getInstance().getClass();
                                    if (bytChatNoteType73 == 4) {
                                        if (Param.getInstance().vChatConsortianNote != null) {
                                            Param.getInstance().vChatConsortianNote = null;
                                            break;
                                        }
                                        break;
                                    } else {
                                        final byte bytChatNoteType74 = Param.getInstance().bytChatNoteType;
                                        Param.getInstance().getClass();
                                        if (bytChatNoteType74 == 1) {
                                            if (Param.getInstance().vChatWordNote != null) {
                                                Param.getInstance().vChatWordNote = null;
                                                break;
                                            }
                                            break;
                                        } else {
                                            final byte bytChatNoteType75 = Param.getInstance().bytChatNoteType;
                                            Param.getInstance().getClass();
                                            if (bytChatNoteType75 == 3) {
                                                if (Param.getInstance().vChatMapNote != null) {
                                                    Param.getInstance().vChatMapNote = null;
                                                    break;
                                                }
                                                break;
                                            } else {
                                                final byte bytChatNoteType76 = Param.getInstance().bytChatNoteType;
                                                Param.getInstance().getClass();
                                                if (bytChatNoteType76 == 8) {
                                                    if (Param.getInstance().vChatFightNote != null) {
                                                        Param.getInstance().vChatFightNote = null;
                                                        break;
                                                    }
                                                    break;
                                                } else {
                                                    final byte bytChatNoteType77 = Param.getInstance().bytChatNoteType;
                                                    Param.getInstance().getClass();
                                                    if (bytChatNoteType77 == 7 && Param.getInstance().vSystemNote != null) {
                                                        Param.getInstance().vSystemNote = null;
                                                        break;
                                                    }
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            case 61: {
                if (MenuUI.getInstance().isCheckSkillinfo) {
                    MenuUI.getInstance().isCheckSkillinfo = false;
                    FullInfo.getInstance().clear();
                }
                final String s31 = s;
                MenuUI.getInstance().getClass();
                if (s31.indexOf("查　　看") != -1) {
                    if (MenuUI.getInstance().bytTitleMove == 0) {
                        new FullInfo(((SkillObject) Param.getInstance().vSkillOccupationBefore.elementAt(MenuUI.getInstance().getOneMove())).strName, 0, this.bytState);
                        FullInfo.getInstance().setFullRectMenu(((SkillObject) Param.getInstance().vSkillOccupationBefore.elementAt(MenuUI.getInstance().getOneMove())).strInfo);
                        FullInfo.getInstance().setButton(1, 4, 2);
                        MenuUI.getInstance().isCheckSkillinfo = true;
                        break;
                    }
                    if (MenuUI.getInstance().bytTitleMove == 1) {
                        new FullInfo(((SkillObject) Param.getInstance().vSkillOccupationLater.elementAt(MenuUI.getInstance().getOneMove())).strName, 0, this.bytState);
                        FullInfo.getInstance().setFullRectMenu(((SkillObject) Param.getInstance().vSkillOccupationLater.elementAt(MenuUI.getInstance().getOneMove())).strInfo);
                        FullInfo.getInstance().setButton(1, 4, 2);
                        MenuUI.getInstance().isCheckSkillinfo = true;
                        break;
                    }
                    if (MenuUI.getInstance().bytTitleMove != 2) {
                        break;
                    }
                    new FullInfo("天书说明", 0, this.bytState);
                    FullInfo.getInstance().setButton(1, 100, 2);
                    final short oneMove = MenuUI.getInstance().getOneMove();
                    if (Param.getInstance().vSkillBook != null && Param.getInstance().vSkillBook.size() > oneMove) {
                        FullInfo.getInstance().setFullRectMenu(((PackageObject) Param.getInstance().vSkillBook.elementAt(oneMove)).strInfo);
                        break;
                    }
                    break;
                } else {
                    final String s32 = s;
                    MenuUI.getInstance().getClass();
                    if (s32.indexOf("设快捷键") != -1) {
                        if (MenuUI.getInstance().bytTitleMove == 0) {
                            MenuUI.getInstance().setTwoKeyRect(((SkillObject) Param.getInstance().vSkillOccupationBefore.elementAt(MenuUI.getInstance().getOneMove())).strName, ((SkillObject) Param.getInstance().vSkillOccupationBefore.elementAt(MenuUI.getInstance().getOneMove())).shtIcon, 0);
                            break;
                        }
                        MenuUI.getInstance().setTwoKeyRect(((SkillObject) Param.getInstance().vSkillOccupationLater.elementAt(MenuUI.getInstance().getOneMove())).strName, ((SkillObject) Param.getInstance().vSkillOccupationLater.elementAt(MenuUI.getInstance().getOneMove())).shtIcon, 0);
                        break;
                    } else {
                        final String s33 = s;
                        MenuUI.getInstance().getClass();
                        if (s33.indexOf("使　　用") != -1) {
                            if (ORPMe.ME.blnStall) {
                                DCanvas.getInstance().addInformation("当前状态不能施法");
                                break;
                            }
                            if (ORPMe.ME.useSkill(MenuUI.getInstance().getOneMove())) {
                                GameControl.getInstance().delUIbase(MenuUI.getInstance().bytDelState);
                                break;
                            }
                            break;
                        } else {
                            final String s34 = s;
                            MenuUI.getInstance().getClass();
                            if (s34.indexOf("技能升级") != -1) {
                                MenuUI.getInstance().isDrawSkillinfo = true;
                                StringManager.resetWordMoveTimer();
                                MenuUI.getInstance().setButton(1, 0, 2);
                                break;
                            }
                            final String s35 = s;
                            MenuUI.getInstance().getClass();
                            if (s35.indexOf("洗　　点") != -1) {
                                GameControl.getInstance().CreateState((byte) 7);
                                DialogUI.getInstance().setDialog(this.bytState, "确定要使用吗？", (byte) 2);
                                break;
                            }
                            final String s36 = s;
                            MenuUI.getInstance().getClass();
                            if (s36.indexOf("镶嵌天书") != -1) {
                                DCanvas.getInstance().setNetLoad(true);
                                NetSend.getInstance().sendRequestInlayBook(0, (byte) MenuUI.getInstance().getOneMove());
                                break;
                            }
                            break;
                        }
                    }
                }
            }
            case 64: {
                final String s37 = s;
                MenuUI.getInstance().getClass();
                if (s37.indexOf("使　　用") != -1) {
                    new FullInfo("生活技能", 0, this.bytState);
                    FullInfo.getInstance().setFullRectMenu("使用");
                    break;
                }
                final String s38 = s;
                MenuUI.getInstance().getClass();
                if (s38.indexOf("物品属性") != -1) {
                    new FullInfo("生活技能", 0, this.bytState);
                    FullInfo.getInstance().setFullRectMenu("爱对方的发生大幅度上升幅度是十分");
                    break;
                }
                final String s39 = s;
                MenuUI.getInstance().getClass();
                s39.indexOf("返　　回");
                break;
            }
            case 63: {
                final String s40 = s;
                MenuUI.getInstance().getClass();
                if (s40.indexOf("查　　看") != -1) {
                    Param.getInstance().vInfoContent = null;
                    MenuUI.getInstance().bytTaskTempMove = (byte) MenuUI.getInstance().shtOneMenuMove;
                    MenuUI.getInstance().bytTaskTempRoll = (byte) MenuUI.getInstance().shtOneMenuRoll;
                    Param.getInstance().blnTaskType = true;
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendTaskList((byte) 1, ((TaskObject) Param.getInstance().vTaskList.elementAt(MenuUI.getInstance().getOneMove())).intId);
                    NetSend.getInstance().sendTaskList((byte) 2, ((TaskObject) Param.getInstance().vTaskList.elementAt(MenuUI.getInstance().getOneMove())).intId);
                    MenuUI.getInstance().setState((byte) (-127), ((TaskObject) Param.getInstance().vTaskList.elementAt(MenuUI.getInstance().getOneMove())).strName);
                    break;
                }
                final String s41 = s;
                MenuUI.getInstance().getClass();
                if (s41.indexOf("放　　弃") != -1) {
                    final StringBuffer sb7 = new StringBuffer();
                    sb7.append("放弃");
                    final StringBuffer sb8 = sb7;
                    GameUI.getInstance().getClass();
                    sb8.append(" ");
                    sb7.append(((TaskObject) Param.getInstance().vTaskList.elementAt(MenuUI.getInstance().getOneMove())).strName);
                    final StringBuffer sb9 = sb7;
                    GameUI.getInstance().getClass();
                    sb9.append(" ");
                    final StringBuffer sb10 = sb7;
                    GameUI.getInstance().getClass();
                    sb10.append("？");
                    MenuUI.getInstance().setTwoRect(sb7.toString(), MenuUI.getInstance().getTaskColor(((TaskObject) Param.getInstance().vTaskList.elementAt(MenuUI.getInstance().getOneMove())).shtLevel), bytHandleMove, (byte) 1, -1);
                    break;
                }
                if (s.indexOf("传　　送") == -1) {
                    break;
                }
                if (GameUI.getInstance().blnFight) {
                    DCanvas.getInstance().addInformation("战斗中无法传送");
                    break;
                }
                DCanvas.getInstance().setNetLoad(true);
                NetParse.getInstance().setInitLoadingPre();
                NetSend.getInstance().sendTaskCarry_oknpc((byte) 1, ((TaskObject) Param.getInstance().vTaskList.elementAt(MenuUI.getInstance().getOneMove())).intId);
                NetParse.getInstance().setInitLoading();
                break;
            }
            case 34: {
                if (Param.getInstance().vCommonList == null) {
                    break;
                }
                final TeamObject teamObject = (TeamObject) Param.getInstance().vCommonList.elementAt(MenuUI.getInstance().getOneMove());
                final String s42 = s;
                MenuUI.getInstance().getClass();
                if (s42.indexOf("查　　看") != -1) {
                    MenuUI.getInstance().getClass();
                    final FullInfo fullInfo3 = new FullInfo("人物资料", 0, this.bytState);
                    if (teamObject.blnIsOnLine) {
                        NetSend.getInstance().sendRoleSee((byte) 1, teamObject.intId, "");
                        break;
                    }
                    NetSend.getInstance().sendRoleOffLineSee(teamObject.strName);
                    break;
                } else {
                    final String s43 = s;
                    MenuUI.getInstance().getClass();
                    if (s43.indexOf("添加成员") != -1) {
                        final FormDes instance = FormDes.getInstance();
                        MenuUI.getInstance().getClass();
                        instance.strTitle = "添加成员";
                        FormDes.getInstance().showForm((byte) 20);
                        break;
                    }
                    final String s44 = s;
                    MenuUI.getInstance().getClass();
                    if (s44.indexOf("私　　聊") != -1) {
                        FormDes.getInstance().setSingleChat(teamObject.strName);
                        break;
                    }
                    final String s45 = s;
                    MenuUI.getInstance().getClass();
                    if (s45.indexOf("邀请组队") != -1) {
                        NetSend.getInstance().sendTeam_add(teamObject.strName);
                        break;
                    }
                    final String s46 = s;
                    MenuUI.getInstance().getClass();
                    if (s46.indexOf("加为好友") != -1) {
                        NetSend.getInstance().sendHailFellow((byte) 2, (byte) 1, teamObject.strName);
                        break;
                    }
                    final String s47 = s;
                    MenuUI.getInstance().getClass();
                    if (s47.indexOf("加为黑名") != -1) {
                        NetSend.getInstance().sendHailFellow((byte) 2, (byte) 2, teamObject.strName);
                        break;
                    }
                    final String s48 = s;
                    MenuUI.getInstance().getClass();
                    if (s48.indexOf("帮派管理") != -1) {
                        if (ORPMe.ME.bytOfficialRank == 1) {
                            DCanvas.getInstance().addInformation("你没有权限");
                            break;
                        }
                        final String[] array = null;
                        final Vector vector = new Vector();
                        if (ORPMe.ME.bytOfficialRank == 3) {
                            if (!teamObject.strName.equals(ORPMe.ME.strNickName)) {
                                if (teamObject.bytTroopRank == 1) {
                                    final Vector vector2 = vector;
                                    MenuUI.getInstance().getClass();
                                    vector2.addElement("提升职位");
                                }
                                if (teamObject.bytTroopRank == 2) {
                                    final Vector vector3 = vector;
                                    MenuUI.getInstance().getClass();
                                    vector3.addElement("降低职位");
                                }
                                final Vector vector4 = vector;
                                MenuUI.getInstance().getClass();
                                vector4.addElement("踢出帮派");
                            }
                            final Vector vector5 = vector;
                            MenuUI.getInstance().getClass();
                            vector5.addElement("帮派升级");
                        } else if (ORPMe.ME.bytOfficialRank == 2) {
                            final Vector vector6 = vector;
                            MenuUI.getInstance().getClass();
                            vector6.addElement("踢出帮派");
                        }
                        final String[] array2 = new String[vector.size()];
                        for (int i = 0; i < vector.size(); i = (short) (i + 1)) {
                            array2[i] = (String) vector.elementAt(i);
                        }
                        b = false;
                        this.init();
                        this.setHandleOption((byte) 5, this.bytState, array2);
                        break;
                    } else {
                        final String s49 = s;
                        MenuUI.getInstance().getClass();
                        if (s49.indexOf("帮派信息") != -1) {
                            DCanvas.getInstance().setNetLoad(true);
                            NetSend.getInstance().sendConsortia((byte) 8, (byte) 0, 0);
                            break;
                        }
                        final String s50 = s;
                        MenuUI.getInstance().getClass();
                        if (s50.indexOf("退出帮派") != -1) {
                            GameControl.getInstance().CreateState((byte) 7);
                            DialogUI.getInstance().setDialog(this.bytState, "你确定要退出" + ORPMe.ME.strConsortia + "吗？", (byte) 2);
                            Param.getInstance().bytConsortiaType = 0;
                            break;
                        }
                        final String s51 = s;
                        MenuUI.getInstance().getClass();
                        if (s51.indexOf("提升职位") != -1) {
                            GameControl.getInstance().CreateState((byte) 7);
                            DialogUI.getInstance().setDialog(this.bytState, "你确定要把" + teamObject.strName + "设为副帮主吗？", (byte) 2);
                            Param.getInstance().bytConsortiaType = 1;
                            break;
                        }
                        final String s52 = s;
                        MenuUI.getInstance().getClass();
                        if (s52.indexOf("降低职位") != -1) {
                            GameControl.getInstance().CreateState((byte) 7);
                            DialogUI.getInstance().setDialog(this.bytState, "你确定要把" + teamObject.strName + "设为帮众吗？", (byte) 2);
                            Param.getInstance().bytConsortiaType = 2;
                            break;
                        }
                        final String s53 = s;
                        MenuUI.getInstance().getClass();
                        if (s53.indexOf("踢出帮派") != -1) {
                            GameControl.getInstance().CreateState((byte) 7);
                            DialogUI.getInstance().setDialog(this.bytState, "你确定要把" + teamObject.strName + "踢出帮派吗？", (byte) 2);
                            Param.getInstance().bytConsortiaType = 3;
                            break;
                        }
                        final String s54 = s;
                        MenuUI.getInstance().getClass();
                        if (s54.indexOf("帮派升级") == -1) {
                            break;
                        }
                        if (ORPMe.ME.ConsortiaLevel >= 3) {
                            DCanvas.getInstance().addInformation("已到达帮派最高等级");
                            break;
                        }
                        GameControl.getInstance().CreateState((byte) 7);
                        DialogUI.getInstance().setDialog(this.bytState, "你确定要升级帮派为" + (ORPMe.ME.ConsortiaLevel + 1) + "级（" + ORPMe.ME.UpgradeMember[ORPMe.ME.ConsortiaLevel - 1] + "人）" + "花费" + ORPMe.ME.UpgradeConsortia[ORPMe.ME.ConsortiaLevel - 1] + "金币吗？", (byte) 2);
                        Param.getInstance().bytConsortiaType = 4;
                        break;
                    }
                }
            }
            case -51: {
                if (Param.getInstance().Member_List == null || Param.getInstance().Second_List == null) {
                    break;
                }
                if (!MenuUI.getInstance().Difference_Level) {
                    MenuUI.getInstance().Difference_Level = true;
                    final byte byte1 = Byte.parseByte(((String[]) Param.getInstance().Submenu_List.elementAt(bytHandleMove))[2]);
                    final byte byte2 = Byte.parseByte(((String[]) Param.getInstance().Submenu_List.elementAt(bytHandleMove))[0]);
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendRankList((byte) 1, byte1, MenuUI.getInstance().menuID, MenuUI.getInstance().secondID, byte2);
                    break;
                }
                final byte byte3 = Byte.parseByte(((String[]) Param.getInstance().Second_List.elementAt(bytHandleMove))[2]);
                final byte byte4 = Byte.parseByte(((String[]) Param.getInstance().Second_List.elementAt(bytHandleMove))[0]);
                MenuUI.getInstance().secondID = byte4;
                final byte byte5 = Byte.parseByte(((String[]) Param.getInstance().Second_List.elementAt(bytHandleMove))[3]);
                if (byte5 == 0 && byte4 == 1) {
                    NetSend.getInstance().sendHailFellow((byte) 2, (byte) 1, ((TeamObject) Param.getInstance().Member_List.elementAt(MenuUI.getInstance().getOneMove())).strName);
                    break;
                }
                if (byte5 == 0 && byte4 != 1) {
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendRankList((byte) 1, byte3, MenuUI.getInstance().menuID, byte4, (byte) 0);
                    break;
                }
                if (byte5 != 1) {
                    break;
                }
                MenuUI.getInstance().Difference_Level = false;
                final String[] array3 = null;
                if (Param.getInstance().Submenu_List != null) {
                    final Vector vector7 = (Vector) Param.getInstance().Occupation_List.get(new Integer(byte4));
                    final String[] array4 = new String[Param.getInstance().Submenu_List.size()];
                    for (int j = 0; j < array4.length; ++j) {
                        array4[j] = ((String[]) vector7.elementAt(j))[1];
                    }
                    b = false;
                    this.init();
                    this.setHandleOption((byte) 5, this.bytState, array4);
                    break;
                }
                break;
            }
            case 58: {
                final PackageObject packageObject2 = (PackageObject) Param.getInstance().hPetPackEquip.get(new Integer(MenuUI.getInstance().bytPetMove));
                if (s.indexOf("使        用") != -1) {
                    final int intId = ((PackageObject) Param.getInstance().hPackage.get(new Integer(MenuUI.getInstance().getPropRectMove()))).intId;
                    final int intPOindex = ((PackageObject) Param.getInstance().hPackage.get(new Integer(MenuUI.getInstance().getPropRectMove()))).intPOindex;
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendPetPack((byte) 4, ORPlayer.itSelectPetId, (byte) intPOindex, intId);
                    MenuUI.getInstance().setState((byte) 58, " ");
                    break;
                }
                if (s.indexOf("卸        下") != -1) {
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendPetPack((byte) 2, ORPlayer.itSelectPetId, MenuUI.getInstance().bytPetMove, packageObject2.intId);
                    MenuUI.getInstance().setState((byte) 58, " ");
                    break;
                }
                if (s.indexOf("装备丢弃") != -1) {
                    final int intId2 = ((PackageObject) Param.getInstance().hPackage.get(new Integer(MenuUI.getInstance().getPropRectMove()))).intId;
                    final int intPOindex2 = ((PackageObject) Param.getInstance().hPackage.get(new Integer(MenuUI.getInstance().getPropRectMove()))).intPOindex;
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendPetPack((byte) 6, ORPlayer.itSelectPetId, (byte) intPOindex2, intId2);
                    break;
                }
                break;
            }
            case 33: {
                if (Param.getInstance().hPackage == null) {
                    return;
                }
                int is_fight_id;
                short n;
                short n2;
                if (MenuUI.getInstance().bytMoveType == 1) {
                    is_fight_id = ((PackageObject) Param.getInstance().hPackage.get(new Integer(MenuUI.getInstance().getPropRectMove()))).petKey;
                    final short shtPngId = ((PackageObject) Param.getInstance().hPackage.get(new Integer(MenuUI.getInstance().getPropRectMove()))).shtPngId;
                    n = ((PackageObject) Param.getInstance().hPackage.get(new Integer(MenuUI.getInstance().getPropRectMove()))).shtStage;
                    n2 = ((PackageObject) Param.getInstance().hPackage.get(new Integer(MenuUI.getInstance().getPropRectMove()))).shtType;
                    final int value = ((PackageObject) Param.getInstance().hPackage.get(new Integer(MenuUI.getInstance().getPropRectMove()))).value;
                } else {
                    is_fight_id = ((PackageObject) ORPMe.ME.hPackagePet.get(new Integer(MenuUI.getInstance().bytRoleMove - 8))).petKey;
                    final short shtPngId2 = ((PackageObject) ORPMe.ME.hPackagePet.get(new Integer(MenuUI.getInstance().bytRoleMove - 8))).shtPngId;
                    n = ((PackageObject) ORPMe.ME.hPackagePet.get(new Integer(MenuUI.getInstance().bytRoleMove - 8))).shtStage;
                    n2 = ((PackageObject) ORPMe.ME.hPackagePet.get(new Integer(MenuUI.getInstance().bytRoleMove - 8))).shtType;
                    final int value2 = ((PackageObject) ORPMe.ME.hPackagePet.get(new Integer(MenuUI.getInstance().bytRoleMove - 8))).value;
                }
                ORPlayer.itSelectPetId = is_fight_id;
                if (s.indexOf("宠物属性") != -1) {
                    Macro.PET_PROP_CHANGE = -1;
                    MenuUI.getInstance().setState((byte) (-1), "宠物");
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendPetProp(is_fight_id);
                    break;
                }
                if (s.indexOf("宠物物品") != -1) {
                    Macro.PET_PROP_CHANGE = 2;
                    MenuUI.getInstance().setState((byte) 58, " ");
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendPetPack((byte) 1, is_fight_id, (byte) 1, 1956);
                    break;
                }
                if (s.indexOf("宠物技能") != -1) {
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendPetSkill(is_fight_id);
                    break;
                }
                if (s.indexOf("召唤宠物") != -1) {
                    ORPMe.ME.intPetId = is_fight_id;
                    MenuUI.getInstance().setPetList();
                    NetSend.getInstance().sendPetHandle((byte) 2, is_fight_id);
                    if (n != 0 && n2 == 2) {
                        Macro.OPEN_AI = false;
                        break;
                    }
                    break;
                } else if (s.indexOf("收回宠物") != -1) {
                    ORPMe.ME.delPet();
                    ORPMe.ME.intPetId = 0;
                    MenuUI.getInstance().setPetList();
                    NetSend.getInstance().sendPetHandle((byte) 3, is_fight_id);
                    if (n != 0 && n2 == 2) {
                        Macro.OPEN_AI = false;
                        break;
                    }
                    break;
                } else {
                    if (s.indexOf("修改名字") != -1) {
                        FormDes.getInstance().showForm((byte) (-66));
                        break;
                    }
                    if (s.indexOf("放生宠物") != -1) {
                        final String strName = ((PackageObject) Param.getInstance().hPackage.get(new Integer(MenuUI.getInstance().getPropRectMove()))).strName;
                        final StringBuffer sb11 = new StringBuffer();
                        sb11.append("确定放生 ");
                        sb11.append(strName);
                        sb11.append(" ");
                        final StringBuffer sb12 = sb11;
                        GameUI.getInstance().getClass();
                        sb12.append("？");
                        DCanvas.getInstance().setNetLoad(true);
                        NetSend.getInstance().sendPetHandle((byte) 4, is_fight_id);
                        MenuUI.getInstance().setTwoRect(sb11.toString(), -1, bytHandleMove, (byte) 1, -1);
                        break;
                    }
                    if (s.indexOf("物品贩卖") != -1) {
                        DCanvas.getInstance().setNetLoad(true);
                        NetSend.getInstance().sendPetHandle_Sale((byte) 6);
                        ORPMe.ME.playerFollowPet.pushTask((byte) 3, (byte) 3);
                        break;
                    }
                    if (s.indexOf("贩卖设置") != -1) {
                        MenuUI.getInstance().bytTaskTempMove = (byte) MenuUI.getInstance().shtOneMenuMove;
                        MenuUI.getInstance().bytTaskTempRoll = (byte) MenuUI.getInstance().shtOneMenuRoll;
                        MenuUI.getInstance().setState((byte) (-37), "贩卖设置");
                        break;
                    }
                    if (s.indexOf("槽进化点") != -1) {
                        DCanvas.getInstance().setNetLoad(true);
                        NetSend.getInstance().sendPetPoint(is_fight_id, (byte) 3, 2);
                        break;
                    }
                    if (s.indexOf("进        化") != -1) {
                        DCanvas.getInstance().setNetLoad(true);
                        NetSend.getInstance().sendPetExperience(is_fight_id, (byte) 2);
                        break;
                    }
                    if (s.indexOf("宠物收起") != -1) {
                        ORPMe.ME.delPet();
                        ORPMe.ME.intPetId = 0;
                        MenuUI.getInstance().setPetList();
                        NetSend.getInstance().sendPetHandle((byte) 3, is_fight_id);
                        break;
                    }
                    if (s.indexOf("宠物战斗") != -1) {
                        if (n != 0 && n2 == 2) {
                            NetSend.getInstance().sendPetHandle((byte) 9, is_fight_id);
                            Macro.OPEN_AI = true;
                        }
                        Macro.is_fight_id = is_fight_id;
                        break;
                    }
                    break;
                }
            }
            case -50: {
                final String s55 = s;
                MenuUI.getInstance().getClass();
                if (s55.indexOf("设置恋人") != -1) {
                    final FormDes instance2 = FormDes.getInstance();
                    MenuUI.getInstance().getClass();
                    instance2.strTitle = "添　　加";
                    FormDes.getInstance().showForm((byte) 20);
                    break;
                }
                final String s56 = s;
                MenuUI.getInstance().getClass();
                if (s56.indexOf("结婚说明") != -1) {
                    new FullInfo("婚姻关系", 0, this.bytState);
                    FullInfo.getInstance().setFullRectMenu("婚姻提示：你好，在爱情值到达3000后，你可以携带结婚戒指和你的恋人组队去找月老登记结婚。");
                    break;
                }
                final String s57 = s;
                MenuUI.getInstance().getClass();
                if (s57.indexOf("私　　聊") != -1) {
                    FormDes.getInstance().setSingleChat(Param.getInstance().partner.strNickName);
                    break;
                }
                final String s58 = s;
                MenuUI.getInstance().getClass();
                if (s58.indexOf("分　　手") != -1) {
                    GameControl.getInstance().CreateState((byte) 7);
                    DialogUI.getInstance().setDialog(this.bytState, "你确定于" + Param.getInstance().partner.strNickName + "分手吗？", (byte) 2);
                    break;
                }
                final String s59 = s;
                MenuUI.getInstance().getClass();
                if (s59.indexOf("邀请组队") != -1) {
                    NetSend.getInstance().sendTeam_add(Param.getInstance().partner.strNickName);
                    break;
                }
                final String s60 = s;
                MenuUI.getInstance().getClass();
                if (s60.indexOf("传　　送") != -1) {
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendAskPartner((byte) 4);
                    break;
                }
                final String s61 = s;
                MenuUI.getInstance().getClass();
                if (s61.indexOf("强制离婚") != -1) {
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendAskPartner((byte) 3);
                    break;
                }
                break;
            }
            case -48: {
                if (MenuUI.getInstance().vTempVessel == null) {
                    break;
                }
                final TeamObject teamObject2 = (TeamObject) MenuUI.getInstance().vTempVessel.elementAt(MenuUI.getInstance().getMasterPlayerMove((byte) MenuUI.getInstance().intRelation));
                final String s62 = s;
                MenuUI.getInstance().getClass();
                if (s62.indexOf("查　　看") != -1) {
                    MenuUI.getInstance().getClass();
                    final FullInfo fullInfo4 = new FullInfo("人物资料", 0, this.bytState);
                    DCanvas.getInstance().setNetLoad(true);
                    if (teamObject2.blnIsOnLine) {
                        NetSend.getInstance().sendRoleSee((byte) 1, teamObject2.intId, "");
                        break;
                    }
                    NetSend.getInstance().sendRoleOffLineSee(teamObject2.strName);
                    break;
                } else {
                    final String s63 = s;
                    MenuUI.getInstance().getClass();
                    if (s63.indexOf("私　　聊") != -1) {
                        FormDes.getInstance().setSingleChat(teamObject2.strName);
                        break;
                    }
                    final String s64 = s;
                    MenuUI.getInstance().getClass();
                    if (s64.indexOf("邀请组队") != -1) {
                        NetSend.getInstance().sendTeam_add(teamObject2.strName);
                        break;
                    }
                    final String s65 = s;
                    MenuUI.getInstance().getClass();
                    if (s65.indexOf("遗        弃") != -1) {
                        GameControl.getInstance().CreateState((byte) 7);
                        DialogUI.getInstance().setDialog(this.bytState, "是否要与" + teamObject2.strName + "脱离师徒关系？", (byte) 2);
                        break;
                    }
                    final String s66 = s;
                    MenuUI.getInstance().getClass();
                    if (s66.indexOf("脱        离") != -1) {
                        final StringBuffer sb13 = new StringBuffer();
                        GameControl.getInstance().CreateState((byte) 7);
                        DialogUI.getInstance().setDialog(this.bytState, "是否要与" + teamObject2.strName + "脱离师徒关系？", (byte) 2);
                        break;
                    }
                    break;
                }
            }
            case -33:
            case -32:
            case -31: {
                final byte b2 = (byte) ((Param.getInstance().shtNoncePage - 1) * 20 + MenuUI.getInstance().getOneMove());
                final String s67 = s;
                MenuUI.getInstance().getClass();
                if (s67.indexOf("查　　看") != -1) {
                    MenuUI.getInstance().getClass();
                    final FullInfo fullInfo5 = new FullInfo("人物资料", 0, this.bytState);
                    DCanvas.getInstance().setNetLoad(true);
                    if (((TeamObject) Param.getInstance().vCommonList.elementAt(b2)).blnIsOnLine) {
                        NetSend.getInstance().sendRoleSee((byte) 1, ((TeamObject) Param.getInstance().vCommonList.elementAt(b2)).intId, "");
                        break;
                    }
                    NetSend.getInstance().sendRoleOffLineSee(((TeamObject) Param.getInstance().vCommonList.elementAt(b2)).strName);
                    break;
                } else {
                    final String s68 = s;
                    MenuUI.getInstance().getClass();
                    if (s68.indexOf("私　　聊") != -1) {
                        FormDes.getInstance().setSingleChat(((TeamObject) Param.getInstance().vCommonList.elementAt(b2)).strName);
                        break;
                    }
                    final String s69 = s;
                    MenuUI.getInstance().getClass();
                    if (s69.indexOf("发送邮件") != -1) {
                        FormDes.getInstance().setMail(((TeamObject) Param.getInstance().vCommonList.elementAt(b2)).strName, "");
                        break;
                    }
                    final String s70 = s;
                    MenuUI.getInstance().getClass();
                    if (s70.indexOf("添加好友") == -1) {
                        final String s71 = s;
                        MenuUI.getInstance().getClass();
                        if (s71.indexOf("添加仇人") == -1) {
                            final String s72 = s;
                            MenuUI.getInstance().getClass();
                            if (s72.indexOf("添加黑名") == -1) {
                                final String s73 = s;
                                MenuUI.getInstance().getClass();
                                if (s73.indexOf("删　　除") != -1) {
                                    final String strName2 = ((TeamObject) Param.getInstance().vCommonList.elementAt(b2)).strName;
                                    final StringBuffer sb14 = new StringBuffer();
                                    sb14.append("是否将" + strName2 + "删除？");
                                    GameControl.getInstance().CreateState((byte) 7);
                                    DialogUI.getInstance().setDialog(this.bytState, sb14.toString(), (byte) 2);
                                    break;
                                }
                                final String s74 = s;
                                MenuUI.getInstance().getClass();
                                if (s74.indexOf("邀请组队") != -1) {
                                    NetSend.getInstance().sendTeam_add(((TeamObject) Param.getInstance().vCommonList.elementAt(b2)).strName);
                                    break;
                                }
                                final String s75 = s;
                                MenuUI.getInstance().getClass();
                                if (s75.indexOf("上　　页") != -1) {
                                    final Param instance3 = Param.getInstance();
                                    --instance3.shtNoncePage;
                                    MenuUI.getInstance().shtMenuMoveLength = 20;
                                    MenuUI.getInstance().clearMove();
                                    break;
                                }
                                final String s76 = s;
                                MenuUI.getInstance().getClass();
                                if (s76.indexOf("下　　页") != -1) {
                                    final Param instance4 = Param.getInstance();
                                    ++instance4.shtNoncePage;
                                    if (Param.getInstance().shtNoncePage >= Param.getInstance().shtAllPage) {
                                        final byte b3 = (byte) (Param.getInstance().vCommonList.size() - (Param.getInstance().shtNoncePage - 1) * 20);
                                        MenuUI.getInstance().shtMenuMoveLength = (short) ((b3 > 20) ? 20 : b3);
                                    }
                                    MenuUI.getInstance().clearMove();
                                    break;
                                }
                                if (s.indexOf("添加昵称") == -1) {
                                    break;
                                }
                                final String strName3 = ((TeamObject) Param.getInstance().vCommonList.elementAt(b2)).strName;
                                if (Param.getInstance().vMenuMemory != null) {
                                    FormDes.getInstance().setAddFriendName(strName3);
                                    MenuUI.getInstance().setBackOff();
                                    break;
                                }
                                FormDes.getInstance().setMail(strName3, "");
                                break;
                            }
                        }
                    }
                    final FormDes instance5 = FormDes.getInstance();
                    MenuUI.getInstance().getClass();
                    instance5.strTitle = "添　　加";
                    FormDes.getInstance().showForm((byte) 20);
                    break;
                }
            }
            case 35: {
                TeamObject teamObject3 = null;
                if (Param.getInstance().vTeam != null) {
                    teamObject3 = (TeamObject) Param.getInstance().vTeam.elementAt(MenuUI.getInstance().getTeamPlayerMove(MenuUI.getInstance().bytChatTeam));
                }
                final String s77 = s;
                MenuUI.getInstance().getClass();
                if (s77.indexOf("查　　看") != -1) {
                    MenuUI.getInstance().getClass();
                    final FullInfo fullInfo6 = new FullInfo("人物资料", 0, this.bytState);
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendRoleSee((byte) 1, teamObject3.intId, "");
                    break;
                }
                final String s78 = s;
                MenuUI.getInstance().getClass();
                if (s78.indexOf("邀请组队") != -1) {
                    final FormDes instance6 = FormDes.getInstance();
                    MenuUI.getInstance().getClass();
                    instance6.strTitle = "邀请组队";
                    FormDes.getInstance().showForm((byte) 20);
                    break;
                }
                final String s79 = s;
                MenuUI.getInstance().getClass();
                if (s79.indexOf("转让队长") != -1) {
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendTeam_option((byte) 5, teamObject3.intId);
                    break;
                }
                final String s80 = s;
                MenuUI.getInstance().getClass();
                if (s80.indexOf("删除队员") != -1) {
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendTeam_option((byte) 7, teamObject3.intId);
                    break;
                }
                final String s81 = s;
                MenuUI.getInstance().getClass();
                if (s81.indexOf("离开队伍") != -1) {
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendTeam_Leave();
                    break;
                }
                final String s82 = s;
                MenuUI.getInstance().getClass();
                if (s82.indexOf("任命助手") != -1) {
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendTeam_option((byte) 3, teamObject3.intId);
                    break;
                }
                final String s83 = s;
                MenuUI.getInstance().getClass();
                if (s83.indexOf("取消助手") != -1) {
                    DCanvas.getInstance().setNetLoad(true);
                    NetSend.getInstance().sendTeam_option((byte) 4, teamObject3.intId);
                    break;
                }
                final String s84 = s;
                MenuUI.getInstance().getClass();
                if (s84.indexOf("队员位置") != -1) {
                    final StringBuffer sb15 = new StringBuffer();
                    sb15.append("变更当前玩家小队？");
                    GameControl.getInstance().CreateState((byte) 7);
                    DialogUI.getInstance().setDialog(this.bytState, sb15.toString(), (byte) 2);
                    break;
                }
                final String s85 = s;
                MenuUI.getInstance().getClass();
                if (s85.indexOf("加为好友") != -1) {
                    NetSend.getInstance().sendHailFellow((byte) 2, (byte) 1, teamObject3.strName);
                    break;
                }
                final String s86 = s;
                MenuUI.getInstance().getClass();
                if (s86.indexOf("私　　聊") != -1) {
                    FormDes.getInstance().setSingleChat(teamObject3.strName);
                    break;
                }
                break;
            }
            case -38: {
                final String strName4 = ((TeamObject) Param.getInstance().vCommonList.elementAt(MenuUI.getInstance().getOneMove())).strName;
                final String s87 = s;
                MenuUI.getInstance().getClass();
                if (s87.indexOf("查　　看") != -1) {
                    final StringBuffer sb17;
                    final StringBuffer sb16 = sb17 = new StringBuffer();
                    MenuUI.getInstance().getClass();
                    sb17.append("昵称：");
                    sb16.append(strName4);
                    sb16.append("\n");
                    final StringBuffer sb18 = sb16;
                    MenuUI.getInstance().getClass();
                    sb18.append("职业：");
                    sb16.append(ORole.getOccName(((TeamObject) Param.getInstance().vCommonList.elementAt(MenuUI.getInstance().getOneMove())).bytOcc));
                    sb16.append("\n");
                    final StringBuffer sb19 = sb16;
                    MenuUI.getInstance().getClass();
                    sb19.append("等级：");
                    sb16.append(((TeamObject) Param.getInstance().vCommonList.elementAt(MenuUI.getInstance().getOneMove())).shtLevel);
                    new FullInfo(strName4, 0, this.bytState);
                    FullInfo.getInstance().setFullRectMenu(sb16.toString());
                    break;
                }
                final String s88 = s;
                GameUI.getInstance().getClass();
                if (s88.indexOf("加为好友") != -1) {
                    NetSend.getInstance().sendHailFellow((byte) 2, (byte) 1, strName4);
                    break;
                }
                final String s89 = s;
                MenuUI.getInstance().getClass();
                if (s89.indexOf("邀请组队") != -1) {
                    NetSend.getInstance().sendTeam_add(strName4);
                    break;
                }
                final String s90 = s;
                MenuUI.getInstance().getClass();
                if (s90.indexOf("添加成员") != -1) {
                    NetSend.getInstance().sendConsortiaAdd(strName4);
                    break;
                }
                break;
            }
            case -43: {
                Hashtable hNpcStorage = null;
                PackageObject packageObject3 = null;
                int n3 = 0;
                switch (Macro.NPC_STEP_SELECT) {
                    case 10: {
                        hNpcStorage = (Hashtable) Param.getInstance().hNpcStorage;
                        n3 = Param.getInstance().npcShopBarPackage.getCellBoxIndex();
                        if (hNpcStorage != null && hNpcStorage.containsKey(new Integer(n3))) {
                            packageObject3 = (PackageObject) hNpcStorage.get(new Integer(n3));
                            break;
                        }
                        break;
                    }
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                    case 25: {
                        hNpcStorage = (Hashtable) Param.getInstance().hNpcOursPackTable.get(Param.getInstance().npcShopOursTabArray[MenuUI.getInstance().tabStyleInstance.getTabCurrentIndex()]);
                        n3 = Param.getInstance().personalPackage.getCellBoxIndex();
                        if (hNpcStorage != null && hNpcStorage.containsKey(new Integer(n3))) {
                            packageObject3 = (PackageObject) hNpcStorage.get(new Integer(n3));
                            break;
                        }
                        break;
                    }
                }
                final String s91 = s;
                MenuUI.getInstance().getClass();
                if (s91.indexOf("查看属性") != -1) {
                    if (packageObject3 == null) {
                        break;
                    }
                    final String strName5 = packageObject3.strName;
                    int intColor;
                    if (packageObject3.shtStamina == 0) {
                        intColor = 6579300;
                    } else {
                        intColor = packageObject3.intColor;
                    }
                    final String strInfo = packageObject3.strInfo;
                    final byte bytAggrandizementLv = packageObject3.bytAggrandizementLv;
                    if (strInfo.equals("")) {
                        GameUI.getInstance().getEquioAttribute(hNpcStorage, n3);
                        final FullInfo fullInfo7 = new FullInfo(String.valueOf(strName5) + ((bytAggrandizementLv > 0) ? (" +" + bytAggrandizementLv) : ""), intColor, this.bytState);
                        FullInfo.getInstance().setFullRectMenu_Vector();
                        break;
                    }
                    new FullInfo(strName5, intColor, this.bytState);
                    FullInfo.getInstance().setFullRectMenu(strInfo);
                    break;
                } else {
                    final String s92 = s;
                    MenuUI.getInstance().getClass();
                    if (s92.indexOf("取　　出") != -1) {
                        if (packageObject3.shtPOnum > 1) {
                            MenuUI.getInstance().setTwoNumber("输入数量", MenuUI.getInstance().intMaxNum, 0, (byte) 0);
                            break;
                        }
                        NetSend.getInstance().sendNpcMoveOption(bytHandleMove, (short) 0, 1);
                        break;
                    } else {
                        final String s93 = s;
                        MenuUI.getInstance().getClass();
                        if (s93.indexOf("存　　入") == -1) {
                            break;
                        }
                        if (packageObject3.shtPOnum > 1) {
                            MenuUI.getInstance().setTwoNumber("输入数量", MenuUI.getInstance().intMaxNum, 0, (byte) 0);
                            break;
                        }
                        NetSend.getInstance().sendNpcMoveOption(bytHandleMove, (short) 0, 1);
                        break;
                    }
                }
            }
            case -39: {
                final String s94 = s;
                MenuUI.getInstance().getClass();
                if (s94.indexOf("查看属性") != -1) {
                    Hashtable hashtable2 = null;
                    PackageObject packageObject4 = null;
                    int n4 = 0;
                    switch (Macro.NPC_STEP_SELECT) {
                        case 2: {
                            hashtable2 = (Hashtable) Param.getInstance().hNpcBarPackTable.get(new Integer(Param.getInstance().npcShopBarTabStyle.getTabCurrentIndex()));
                            n4 = Param.getInstance().npcShopBarPackage.getCellBoxIndex();
                            if (hashtable2 != null && hashtable2.containsKey(new Integer(n4))) {
                                packageObject4 = (PackageObject) hashtable2.get(new Integer(n4));
                                break;
                            }
                            break;
                        }
                        case 3: {
                            hashtable2 = (Hashtable) Param.getInstance().hNpcOursPackTable.get(Param.getInstance().npcShopOursTabArray[MenuUI.getInstance().tabStyleInstance.getTabCurrentIndex()]);
                            n4 = Param.getInstance().personalPackage.getCellBoxIndex();
                            if (hashtable2 != null && hashtable2.containsKey(new Integer(n4))) {
                                packageObject4 = (PackageObject) hashtable2.get(new Integer(n4));
                                break;
                            }
                            break;
                        }
                    }
                    if (packageObject4 != null) {
                        final String strName6 = packageObject4.strName;
                        int intColor2;
                        if (packageObject4.shtStamina == 0) {
                            intColor2 = 6579300;
                        } else {
                            intColor2 = packageObject4.intColor;
                        }
                        final String strInfo2 = packageObject4.strInfo;
                        final byte bytAggrandizementLv2 = packageObject4.bytAggrandizementLv;
                        if (strInfo2.equals("")) {
                            GameUI.getInstance().getEquioAttribute(hashtable2, n4);
                            final FullInfo fullInfo8 = new FullInfo(String.valueOf(strName6) + ((bytAggrandizementLv2 > 0) ? (" +" + bytAggrandizementLv2) : ""), intColor2, this.bytState);
                            FullInfo.getInstance().setFullRectMenu_Vector();
                        } else {
                            new FullInfo(strName6, intColor2, this.bytState);
                            FullInfo.getInstance().setFullRectMenu(strInfo2);
                        }
                    }
                } else {
                    final String s95 = s;
                    MenuUI.getInstance().getClass();
                    if (s95.indexOf("出　　售") != -1) {
                        MenuUI.getInstance().shopNPC();
                    } else {
                        final String s96 = s;
                        MenuUI.getInstance().getClass();
                        if (s96.indexOf("出售多个") != -1) {
                            MenuUI.getInstance().setTwoNumber("输入数量", MenuUI.getInstance().intMaxNum, 0, (byte) 0);
                        } else {
                            final String s97 = s;
                            MenuUI.getInstance().getClass();
                            if (s97.indexOf("购　　买") != -1) {
                                MenuUI.getInstance().shopNPC();
                            } else {
                                final String s98 = s;
                                MenuUI.getInstance().getClass();
                                if (s98.indexOf("购买多个") != -1) {
                                    MenuUI.getInstance().setTwoNumber("输入数量", MenuUI.getInstance().intMaxNum, 0, (byte) 0);
                                }
                            }
                        }
                    }
                }
                Param.popupDialogInstance.setShow(false);
                break;
            }
            case 31: {
                final String s99 = s;
                MenuUI.getInstance().getClass();
                if (s99.indexOf("邮       寄") != -1) {
                    final NetSend instance7 = NetSend.getInstance();
                    MenuUI.getInstance().getClass();
                    instance7.sendNpcMoveOption((short) 3, (short) 0, 0);
                    break;
                }
                Param.getInstance().bytPopRectStep = 0;
                NetSend.getInstance().sendNpcMoveOption(bytHandleMove, (short) 0, 0);
                break;
            }
            case -36: {
                final String s100 = s;
                MenuUI.getInstance().getClass();
                if (s100.indexOf("查　　看") != -1) {
                    MenuUI.getInstance().setMailContent(MenuUI.getInstance().getOneMove());
                    GameUI.getInstance().blnNewMail = false;
                    break;
                }
                final String s101 = s;
                MenuUI.getInstance().getClass();
                if (s101.indexOf("写  邮   件") != -1) {
                    FormDes.getInstance().setMail("", "");
                    break;
                }
                final String s102 = s;
                MenuUI.getInstance().getClass();
                if (s102.indexOf("删　　除") != -1) {
                    GameControl.getInstance().CreateState((byte) 7);
                    DialogUI.getInstance().setDialog(this.bytState, "你确定删除这封邮件吗？", (byte) 2);
                    break;
                }
                final String s103 = s;
                MenuUI.getInstance().getClass();
                if (s103.indexOf("回　　复") != -1) {
                    final String strName7 = ((TeamObject) Param.getInstance().vCommonList.elementAt(MenuUI.getInstance().getOneMove())).strName;
                    final StringBuffer sb20 = new StringBuffer();
                    sb20.append(ORPMe.ME.strNickName);
                    final StringBuffer sb21 = sb20;
                    MenuUI.getInstance().getClass();
                    sb21.append("回　　复".substring(0, 1));
                    final StringBuffer sb22 = sb20;
                    MenuUI.getInstance().getClass();
                    sb22.append("回　　复".substring(3));
                    FormDes.getInstance().setMail(strName7, sb20.toString());
                    break;
                }
                break;
            }
            case -113: {
                NetSend.getInstance().sendNpcMoveOption(this.bytHandleMove, this.bytHandleRoll, 0);
                break;
            }
            case -46: {
                final String s104 = s;
                MenuUI.getInstance().getClass();
                if (s104.indexOf("查　　看") != -1) {
                    int intCDTime = 0;
                    String s105;
                    String fullRectMenu;
                    if (((SkillObject) Param.getInstance().vCommonList.elementAt(MenuUI.getInstance().getOneMove())).strPropName == null) {
                        s105 = ((SkillObject) Param.getInstance().vCommonList.elementAt(MenuUI.getInstance().getOneMove())).strName;
                        fullRectMenu = ((PackageObject) Param.getInstance().vCommonList.elementAt(MenuUI.getInstance().getOneMove())).strInfo;
                    } else {
                        s105 = ((SkillObject) Param.getInstance().vCommonList.elementAt(MenuUI.getInstance().getOneMove())).strPropName;
                        fullRectMenu = ((PackageObject) Param.getInstance().vCommonList.elementAt(MenuUI.getInstance().getOneMove())).strInfo;
                        intCDTime = ((SkillObject) Param.getInstance().vCommonList.elementAt(MenuUI.getInstance().getOneMove())).intCDTime;
                    }
                    new FullInfo(s105, intCDTime, this.bytState);
                    FullInfo.getInstance().setFullRectMenu(fullRectMenu);
                    break;
                }
                NetSend.getInstance().sendNpcMoveOption(this.bytHandleMove, this.bytHandleRoll, 0);
                break;
            }
            case -47: {
                final String strName8 = ((PackageObject) Param.getInstance().vCommonList.elementAt(MenuUI.getInstance().getOneMove())).strName;
                final int intColor3 = ((PackageObject) Param.getInstance().vCommonList.elementAt(MenuUI.getInstance().getOneMove())).intColor;
                final String strInfo3 = ((PackageObject) Param.getInstance().vCommonList.elementAt(MenuUI.getInstance().getOneMove())).strInfo;
                final String strTitle = ((PackageObject) Param.getInstance().vCommonList.elementAt(MenuUI.getInstance().getOneMove())).strTitle;
                final String strContent = ((PackageObject) Param.getInstance().vCommonList.elementAt(MenuUI.getInstance().getOneMove())).strContent;
                final String strDate = ((PackageObject) Param.getInstance().vCommonList.elementAt(MenuUI.getInstance().getOneMove())).strDate;
                final short shtIcon = ((PackageObject) Param.getInstance().vCommonList.elementAt(MenuUI.getInstance().getOneMove())).shtIcon;
                final short shtPOnum = ((PackageObject) Param.getInstance().vCommonList.elementAt(MenuUI.getInstance().getOneMove())).shtPOnum;
                final String s106 = s;
                MenuUI.getInstance().getClass();
                if (s106.indexOf("查　　看") != -1) {
                    final byte bytAggrandizementLv3 = ((PackageObject) Param.getInstance().vCommonList.elementAt(MenuUI.getInstance().getOneMove())).bytAggrandizementLv;
                    if (strInfo3.equals("")) {
                        GameUI.getInstance().getEquioAttribute((PackageObject) Param.getInstance().vCommonList.elementAt(MenuUI.getInstance().getOneMove()));
                        final FullInfo fullInfo9 = new FullInfo(String.valueOf(strName8) + ((bytAggrandizementLv3 > 0) ? (" +" + bytAggrandizementLv3) : ""), intColor3, this.bytState);
                        FullInfo.getInstance().setFullRectMenu_Vector();
                        break;
                    }
                    new FullInfo("我的邮件", intColor3, this.bytState);
                    FullInfo.getInstance().setMailFullRectMenu(strName8, strInfo3, strTitle, strContent, shtIcon, shtPOnum, strDate);
                    final NetSend instance8 = NetSend.getInstance();
                    MenuUI.getInstance().getClass();
                    instance8.sendNpcMoveOption((short) 4, (short) 0, 0);
                    break;
                } else {
                    final String s107 = s;
                    MenuUI.getInstance().getClass();
                    if (s107.indexOf("上　　页") != -1) {
                        final NetSend instance9 = NetSend.getInstance();
                        MenuUI.getInstance().getClass();
                        instance9.sendNpcMoveOption((short) 1, this.bytHandleRoll, 0);
                        break;
                    }
                    final String s108 = s;
                    MenuUI.getInstance().getClass();
                    if (s108.indexOf("下　　页") != -1) {
                        final NetSend instance10 = NetSend.getInstance();
                        MenuUI.getInstance().getClass();
                        instance10.sendNpcMoveOption((short) 2, this.bytHandleRoll, 0);
                        break;
                    }
                    final String s109 = s;
                    MenuUI.getInstance().getClass();
                    if (s109.indexOf("删　　除") != -1) {
                        if (shtIcon == -1) {
                            final NetSend instance11 = NetSend.getInstance();
                            MenuUI.getInstance().getClass();
                            instance11.sendNpcMoveOption((short) 3, this.bytHandleRoll, 0);
                            Param.getInstance().vCommonList.removeElementAt(MenuUI.getInstance().getOneMove());
                            MenuUI.getInstance().shtOneMenuMove = 0;
                            NetSend.getInstance().sendNpcOneOption(Param.getInstance().oSelectNpc.intUserId, (byte) 1, 800000);
                            break;
                        }
                        GameControl.getInstance().CreateState((byte) 7);
                        DialogUI.getInstance().setDialog((byte) (-47), "邮件内或许含有道具，确定删除？", (byte) 2);
                        break;
                    } else {
                        final String s110 = s;
                        MenuUI.getInstance().getClass();
                        if (s110.indexOf("回　　复") != -1) {
                            FormDes.getInstance().setMailRecvAnswer(strInfo3, strTitle);
                            break;
                        }
                        final String s111 = s;
                        MenuUI.getInstance().getClass();
                        if (s111.indexOf("收　　取") == -1) {
                            break;
                        }
                        if (shtIcon != -1 && shtIcon != 0) {
                            final NetSend instance12 = NetSend.getInstance();
                            MenuUI.getInstance().getClass();
                            instance12.sendNpcMoveOption((short) 0, this.bytHandleRoll, 0);
                            ((PackageObject) Param.getInstance().vCommonList.elementAt(MenuUI.getInstance().getOneMove())).strName = null;
                            ((PackageObject) Param.getInstance().vCommonList.elementAt(MenuUI.getInstance().getOneMove())).shtIcon = 0;
                            ((PackageObject) Param.getInstance().vCommonList.elementAt(MenuUI.getInstance().getOneMove())).shtPOnum = 0;
                            new FullInfo("我的邮件", intColor3, this.bytState);
                            FullInfo.getInstance().setMailFullRectMenu(strName8, strInfo3, strTitle, strContent, shtIcon, shtPOnum, strDate);
                            break;
                        }
                        DCanvas.getInstance().addInformation("附件已提取");
                        break;
                    }
                }
            }
            case -44: {
                final String s112 = s;
                MenuUI.getInstance().getClass();
                if (s112.indexOf("查　　看") != -1) {
                    final String strName9 = ((MakeObject) Param.getInstance().vCommonList.elementAt(MenuUI.getInstance().getOneMove())).strName;
                    final int intColor4 = ((MakeObject) Param.getInstance().vCommonList.elementAt(MenuUI.getInstance().getOneMove())).intColor;
                    final String strInfo4 = ((MakeObject) Param.getInstance().vCommonList.elementAt(MenuUI.getInstance().getOneMove())).strInfo;
                    new FullInfo(strName9, intColor4, this.bytState);
                    FullInfo.getInstance().setFullRectMenu(strInfo4);
                    break;
                }
                if (s.indexOf("兑　　换") == -1) {
                    NetSend.getInstance().sendNpcMoveOption(this.bytHandleMove, this.bytHandleRoll, 0);
                    break;
                }
                if (((MakeObject) Param.getInstance().vCommonList.elementAt(MenuUI.getInstance().getOneMove())).shtNum <= 0) {
                    DCanvas.getInstance().addInformation("材料不足");
                    break;
                }
                NetSend.getInstance().sendNpcMoveOption(this.bytHandleMove, this.bytHandleRoll, 0);
                break;
            }
            case 36: {
                final String s113 = s;
                MenuUI.getInstance().getClass();
                if (s113.indexOf("查看属性") == -1) {
                    final String s114 = s;
                    MenuUI.getInstance().getClass();
                    if (s114.indexOf("查看详情") == -1) {
                        final String s115 = s;
                        MenuUI.getInstance().getClass();
                        if (s115.indexOf("整　　理") != -1) {
                            DCanvas.getInstance().setNetLoad(true);
                            NetSend.getInstance().sendFrameLeechdom((byte) 5, Param.getInstance().personalPackage.getCellBoxIndex(), 0);
                            break;
                        }
                        final String s116 = s;
                        MenuUI.getInstance().getClass();
                        if (s116.indexOf("使　　用") != -1) {
                            DCanvas.getInstance().setNetLoad(true);
                            NetSend.getInstance().sendFrameLeechdom((byte) 2, Param.getInstance().personalPackage.getCellBoxIndex(), ((PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()))).intId);
                            break;
                        }
                        final String s117 = s;
                        MenuUI.getInstance().getClass();
                        if (s117.indexOf("设快捷键") != -1) {
                            MenuUI.getInstance().setTwoKeyRect(((PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()))).strName, ((PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()))).shtIcon, ((PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()))).intColor);
                            break;
                        }
                        final String s118 = s;
                        MenuUI.getInstance().getClass();
                        if (s118.indexOf("丢　　弃") != -1) {
                            final StringBuffer sb24;
                            final StringBuffer sb23 = sb24 = new StringBuffer();
                            MenuUI.getInstance().getClass();
                            sb24.append("丢　　弃".substring(0, 1));
                            final StringBuffer sb25 = sb23;
                            MenuUI.getInstance().getClass();
                            sb25.append("丢　　弃".substring(3));
                            final StringBuffer sb26 = sb23;
                            GameUI.getInstance().getClass();
                            sb26.append(" ");
                            sb23.append(((PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()))).strName);
                            final StringBuffer sb27 = sb23;
                            GameUI.getInstance().getClass();
                            sb27.append(" ");
                            final StringBuffer sb28 = sb23;
                            GameUI.getInstance().getClass();
                            sb28.append("？");
                            GameControl.getInstance().CreateState((byte) 7);
                            DialogUI.getInstance().setDialog((byte) 123, sb23.toString(), (byte) 2);
                            break;
                        }
                        final String s119 = s;
                        MenuUI.getInstance().getClass();
                        if (s119.indexOf("升级背包") != -1) {
                            final StringBuffer sb29 = new StringBuffer();
                            sb29.append("升级将花费");
                            sb29.append(((Param.getInstance().packageBoxMaxNum - 16) / 8 + 1) * ((Param.getInstance().packageBoxMaxNum - 16) / 8 + 1) * 200);
                            sb29.append("点，增加8格");
                            MenuUI.getInstance().setTwoRect(sb29.toString(), 0, bytHandleMove, (byte) 1, 1);
                            break;
                        }
                        final String s120 = s;
                        MenuUI.getInstance().getClass();
                        if (s120.indexOf("添加物品") != -1) {
                            FormDes.getInstance().setChatAddProp(this.bytState, Param.getInstance().personalPackage.getCellBoxIndex());
                            break;
                        }
                        break;
                    }
                }
                final String strName10 = ((PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()))).strName;
                int intColor5;
                if (((PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()))).shtStamina == 0) {
                    intColor5 = 6579300;
                } else {
                    intColor5 = ((PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()))).intColor;
                }
                final String strInfo5 = ((PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()))).strInfo;
                final byte bytAggrandizementLv4 = ((PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()))).bytAggrandizementLv;
                if (strInfo5.equals("")) {
                    GameUI.getInstance().getEquioAttribute(Param.getInstance().hPackage, Param.getInstance().personalPackage.getCellBoxIndex());
                    final FullInfo fullInfo10 = new FullInfo(String.valueOf(strName10) + ((bytAggrandizementLv4 > 0) ? (" +" + bytAggrandizementLv4) : ""), intColor5, this.bytState);
                    FullInfo.getInstance().setFullRectMenu_Vector();
                    break;
                }
                new FullInfo(strName10, intColor5, this.bytState);
                FullInfo.getInstance().setFullRectMenu(strInfo5);
                break;
            }
            case 37: {
                final String s121 = s;
                MenuUI.getInstance().getClass();
                if (s121.indexOf("查看属性") == -1) {
                    final String s122 = s;
                    MenuUI.getInstance().getClass();
                    if (s122.indexOf("查看详情") == -1) {
                        final String s123 = s;
                        MenuUI.getInstance().getClass();
                        if (s123.indexOf("整　　理") != -1) {
                            DCanvas.getInstance().setNetLoad(true);
                            NetSend.getInstance().sendFrameStuff((byte) 3, Param.getInstance().personalPackage.getCellBoxIndex(), 0);
                            break;
                        }
                        final String s124 = s;
                        MenuUI.getInstance().getClass();
                        if (s124.indexOf("丢　　弃") != -1) {
                            final StringBuffer sb31;
                            final StringBuffer sb30 = sb31 = new StringBuffer();
                            MenuUI.getInstance().getClass();
                            sb31.append("丢　　弃".substring(0, 1));
                            final StringBuffer sb32 = sb30;
                            MenuUI.getInstance().getClass();
                            sb32.append("丢　　弃".substring(3));
                            final StringBuffer sb33 = sb30;
                            GameUI.getInstance().getClass();
                            sb33.append(" ");
                            sb30.append(((PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()))).strName);
                            final StringBuffer sb34 = sb30;
                            GameUI.getInstance().getClass();
                            sb34.append(" ");
                            final StringBuffer sb35 = sb30;
                            GameUI.getInstance().getClass();
                            sb35.append("？");
                            GameControl.getInstance().CreateState((byte) 7);
                            DialogUI.getInstance().setDialog((byte) 124, sb30.toString(), (byte) 2);
                            break;
                        }
                        final String s125 = s;
                        MenuUI.getInstance().getClass();
                        if (s125.indexOf("升级背包") != -1) {
                            final StringBuffer sb36 = new StringBuffer();
                            sb36.append("升级将花费");
                            sb36.append(((Param.getInstance().packageBoxMaxNum - 16) / 8 + 1) * ((Param.getInstance().packageBoxMaxNum - 16) / 8 + 1) * 200);
                            sb36.append("点，增加8格");
                            MenuUI.getInstance().setTwoRect(sb36.toString(), 0, bytHandleMove, (byte) 1, 1);
                            break;
                        }
                        final String s126 = s;
                        MenuUI.getInstance().getClass();
                        if (s126.indexOf("添加物品") != -1) {
                            FormDes.getInstance().setChatAddProp(this.bytState, Param.getInstance().personalPackage.getCellBoxIndex());
                            break;
                        }
                        break;
                    }
                }
                final String strName11 = ((PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()))).strName;
                int intColor6;
                if (((PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()))).shtStamina == 0) {
                    intColor6 = 6579300;
                } else {
                    intColor6 = ((PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()))).intColor;
                }
                final String strInfo6 = ((PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()))).strInfo;
                final byte bytAggrandizementLv5 = ((PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()))).bytAggrandizementLv;
                if (strInfo6.equals("")) {
                    GameUI.getInstance().getEquioAttribute(Param.getInstance().hPackage, Param.getInstance().personalPackage.getCellBoxIndex());
                    final FullInfo fullInfo11 = new FullInfo(String.valueOf(strName11) + ((bytAggrandizementLv5 > 0) ? (" +" + bytAggrandizementLv5) : ""), intColor6, this.bytState);
                    FullInfo.getInstance().setFullRectMenu_Vector();
                    break;
                }
                new FullInfo(strName11, intColor6, this.bytState);
                FullInfo.getInstance().setFullRectMenu(strInfo6);
                break;
            }
            case 38: {
                final PackageObject packageObject5 = (PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()));
                final String s127 = s;
                MenuUI.getInstance().getClass();
                if (s127.indexOf("查看属性") == -1) {
                    final String s128 = s;
                    MenuUI.getInstance().getClass();
                    if (s128.indexOf("查看详情") == -1) {
                        final String s129 = s;
                        MenuUI.getInstance().getClass();
                        if (s129.indexOf("整　　理") != -1) {
                            DCanvas.getInstance().setNetLoad(true);
                            NetSend.getInstance().sendFrameTaskprop((byte) 5, Param.getInstance().personalPackage.getCellBoxIndex(), 0);
                            break;
                        }
                        final String s130 = s;
                        MenuUI.getInstance().getClass();
                        if (s130.indexOf("使　　用") != -1) {
                            DCanvas.getInstance().setNetLoad(true);
                            NetSend.getInstance().sendFrameTaskprop((byte) 2, Param.getInstance().personalPackage.getCellBoxIndex(), packageObject5.intId);
                            break;
                        }
                        final String s131 = s;
                        MenuUI.getInstance().getClass();
                        if (s131.indexOf("设快捷键") != -1) {
                            MenuUI.getInstance().setTwoKeyRect(packageObject5.strName, packageObject5.shtIcon, packageObject5.intColor);
                            break;
                        }
                        final String s132 = s;
                        MenuUI.getInstance().getClass();
                        if (s132.indexOf("丢　　弃") != -1) {
                            final StringBuffer sb38;
                            final StringBuffer sb37 = sb38 = new StringBuffer();
                            MenuUI.getInstance().getClass();
                            sb38.append("丢　　弃".substring(0, 1));
                            final StringBuffer sb39 = sb37;
                            MenuUI.getInstance().getClass();
                            sb39.append("丢　　弃".substring(3));
                            final StringBuffer sb40 = sb37;
                            GameUI.getInstance().getClass();
                            sb40.append(" ");
                            sb37.append(packageObject5.strName);
                            final StringBuffer sb41 = sb37;
                            GameUI.getInstance().getClass();
                            sb41.append(" ");
                            final StringBuffer sb42 = sb37;
                            GameUI.getInstance().getClass();
                            sb42.append("？");
                            GameControl.getInstance().CreateState((byte) 7);
                            DialogUI.getInstance().setDialog((byte) 125, sb37.toString(), (byte) 2);
                            break;
                        }
                        final String s133 = s;
                        MenuUI.getInstance().getClass();
                        if (s133.indexOf("添加物品") != -1) {
                            FormDes.getInstance().setChatAddProp(this.bytState, Param.getInstance().personalPackage.getCellBoxIndex());
                            break;
                        }
                        break;
                    }
                }
                final String strName12 = ((PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()))).strName;
                int intColor7;
                if (((PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()))).shtStamina == 0) {
                    intColor7 = 6579300;
                } else {
                    intColor7 = ((PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()))).intColor;
                }
                final String strInfo7 = ((PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()))).strInfo;
                final byte bytAggrandizementLv6 = ((PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()))).bytAggrandizementLv;
                if (strInfo7.equals("")) {
                    GameUI.getInstance().getEquioAttribute(Param.getInstance().hPackage, Param.getInstance().personalPackage.getCellBoxIndex());
                    final FullInfo fullInfo12 = new FullInfo(String.valueOf(strName12) + ((bytAggrandizementLv6 > 0) ? (" +" + bytAggrandizementLv6) : ""), intColor7, this.bytState);
                    FullInfo.getInstance().setFullRectMenu_Vector();
                    break;
                }
                new FullInfo(strName12, intColor7, this.bytState);
                FullInfo.getInstance().setFullRectMenu(strInfo7);
                break;
            }
            case 39: {
                final String s134 = s;
                MenuUI.getInstance().getClass();
                if (s134.indexOf("查看属性") == -1) {
                    final String s135 = s;
                    MenuUI.getInstance().getClass();
                    if (s135.indexOf("查看详情") == -1) {
                        final String s136 = s;
                        MenuUI.getInstance().getClass();
                        if (s136.indexOf("整　　理") != -1) {
                            DCanvas.getInstance().setNetLoad(true);
                            NetSend.getInstance().sendFramePeculiar((byte) 5, Param.getInstance().personalPackage.getCellBoxIndex(), 0, -1, (byte) 0);
                            break;
                        }
                        final String s137 = s;
                        MenuUI.getInstance().getClass();
                        if (s137.indexOf("使　　用") != -1) {
                            DCanvas.getInstance().setNetLoad(true);
                            NetSend.getInstance().sendFramePeculiar((byte) 2, Param.getInstance().personalPackage.getCellBoxIndex(), ((PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()))).intId, ORPlayer.itSelectPetId, (byte) 3);
                            break;
                        }
                        final String s138 = s;
                        MenuUI.getInstance().getClass();
                        if (s138.indexOf("设快捷键") != -1) {
                            MenuUI.getInstance().setTwoKeyRect(((PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()))).strName, ((PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()))).shtIcon, ((PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()))).intColor);
                            break;
                        }
                        final String s139 = s;
                        MenuUI.getInstance().getClass();
                        if (s139.indexOf("丢　　弃") != -1) {
                            final StringBuffer sb44;
                            final StringBuffer sb43 = sb44 = new StringBuffer();
                            MenuUI.getInstance().getClass();
                            sb44.append("丢　　弃".substring(0, 1));
                            final StringBuffer sb45 = sb43;
                            MenuUI.getInstance().getClass();
                            sb45.append("丢　　弃".substring(3));
                            final StringBuffer sb46 = sb43;
                            GameUI.getInstance().getClass();
                            sb46.append(" ");
                            sb43.append(((PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()))).strName);
                            final StringBuffer sb47 = sb43;
                            GameUI.getInstance().getClass();
                            sb47.append(" ");
                            final StringBuffer sb48 = sb43;
                            GameUI.getInstance().getClass();
                            sb48.append("？");
                            GameControl.getInstance().CreateState((byte) 7);
                            DialogUI.getInstance().setDialog((byte) 126, sb43.toString(), (byte) 2);
                            break;
                        }
                        final String s140 = s;
                        MenuUI.getInstance().getClass();
                        if (s140.indexOf("升级背包") != -1) {
                            final StringBuffer sb49 = new StringBuffer();
                            sb49.append("升级将花费");
                            sb49.append(((Param.getInstance().packageBoxMaxNum - 16) / 8 + 1) * ((Param.getInstance().packageBoxMaxNum - 16) / 8 + 1) * 200);
                            sb49.append("点，增加8格");
                            MenuUI.getInstance().setTwoRect(sb49.toString(), 0, bytHandleMove, (byte) 1, 1);
                            break;
                        }
                        final String s141 = s;
                        MenuUI.getInstance().getClass();
                        if (s141.indexOf("添加物品") != -1) {
                            FormDes.getInstance().setChatAddProp(this.bytState, Param.getInstance().personalPackage.getCellBoxIndex());
                            break;
                        }
                        break;
                    }
                }
                final String strName13 = ((PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()))).strName;
                int intColor8;
                if (((PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()))).shtStamina == 0) {
                    intColor8 = 6579300;
                } else {
                    intColor8 = ((PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()))).intColor;
                }
                final String strInfo8 = ((PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()))).strInfo;
                final byte bytAggrandizementLv7 = ((PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()))).bytAggrandizementLv;
                if (strInfo8.equals("")) {
                    GameUI.getInstance().getEquioAttribute(Param.getInstance().hPackage, Param.getInstance().personalPackage.getCellBoxIndex());
                    final FullInfo fullInfo13 = new FullInfo(String.valueOf(strName13) + ((bytAggrandizementLv7 > 0) ? (" +" + bytAggrandizementLv7) : ""), intColor8, this.bytState);
                    FullInfo.getInstance().setFullRectMenu_Vector();
                    break;
                }
                new FullInfo(strName13, intColor8, this.bytState);
                FullInfo.getInstance().setFullRectMenu(strInfo8);
                break;
            }
            case -127: {
                final String s142 = s;
                MenuUI.getInstance().getClass();
                if (s142.indexOf("接受任务") != -1) {
                    MenuUI.getInstance().sendTaskHandle((byte) 0, (byte) 0);
                    GameControl.getInstance().delUIbase(3);
                    break;
                }
                break;
            }
            case 10: {
                if (MenuUI.getInstance().bytMoveType == 1) {
                    final String s143 = s;
                    MenuUI.getInstance().getClass();
                    if (s143.indexOf("查看属性") == -1) {
                        final String s144 = s;
                        MenuUI.getInstance().getClass();
                        if (s144.indexOf("查看详情") == -1) {
                            final String s145 = s;
                            MenuUI.getInstance().getClass();
                            if (s145.indexOf("装　　备") != -1) {
                                final PackageObject packageObject6 = (PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()));
                                if (packageObject6.bytSeal == 1) {
                                    DCanvas.getInstance().addInformation("无法装备封印物品");
                                    break;
                                }
                                DebugFrame.getInstance().logIn("byteEquipType:" + packageObject6.byteEquipType);
                                boolean b4 = false;
                                if (packageObject6.byteEquipPart > 0) {
                                    b4 = ORole.canEquipEquipment(ORPMe.ME.bytOccupation, packageObject6.byteEquipPart);
                                }
                                if (packageObject6.byteEquipType >= 10) {
                                    b4 = ORole.canEquipWeapon(ORPMe.ME.bytOccupation, packageObject6.byteEquipType);
                                }
                                if (!b4) {
                                    final StringBuffer sb50 = new StringBuffer();
                                    sb50.append("无法装备");
                                    if (packageObject6.byteEquipPart == 1) {
                                        final StringBuffer sb51 = sb50;
                                        MenuUI.getInstance().getClass();
                                        sb51.append("布甲");
                                    } else if (packageObject6.byteEquipPart == 2) {
                                        final StringBuffer sb52 = sb50;
                                        MenuUI.getInstance().getClass();
                                        sb52.append("轻甲");
                                    } else if (packageObject6.byteEquipPart == 3) {
                                        final StringBuffer sb53 = sb50;
                                        MenuUI.getInstance().getClass();
                                        sb53.append("重甲");
                                    } else if (packageObject6.byteEquipType == 1) {
                                        final StringBuffer sb54 = sb50;
                                        MenuUI.getInstance().getClass();
                                        sb54.append("匕首");
                                    } else if (packageObject6.byteEquipType == 2) {
                                        final StringBuffer sb55 = sb50;
                                        MenuUI.getInstance().getClass();
                                        sb55.append("锤子");
                                    } else if (packageObject6.byteEquipType == 3) {
                                        final StringBuffer sb56 = sb50;
                                        MenuUI.getInstance().getClass();
                                        sb56.append("法杖");
                                    } else if (packageObject6.byteEquipType == 4) {
                                        final StringBuffer sb57 = sb50;
                                        MenuUI.getInstance().getClass();
                                        sb57.append("弓箭");
                                    } else if (packageObject6.byteEquipType == 5) {
                                        final StringBuffer sb58 = sb50;
                                        MenuUI.getInstance().getClass();
                                        sb58.append("剑");
                                    }
                                    DCanvas.getInstance().addInformation(sb50.toString());
                                    break;
                                }
                                if (packageObject6.shtLevel > ORPMe.ME.shtLevel) {
                                    DCanvas.getInstance().addInformation("你的等级不够");
                                    break;
                                }
                                if (packageObject6.bytOperation == 2 && packageObject6.bytIsBind == 0) {
                                    GameControl.getInstance().CreateState((byte) 7);
                                    DialogUI.getInstance().setDialog((byte) 127, "装备后绑定，确定装备吗？", (byte) 2);
                                    break;
                                }
                                DCanvas.getInstance().setNetLoad(true);
                                NetSend.getInstance().sendFrameEquip((byte) 4, Param.getInstance().personalPackage.getCellBoxIndex(), packageObject6.intId);
                                break;
                            } else {
                                final String s146 = s;
                                MenuUI.getInstance().getClass();
                                if (s146.indexOf("丢　　弃") != -1) {
                                    final StringBuffer sb60;
                                    final StringBuffer sb59 = sb60 = new StringBuffer();
                                    MenuUI.getInstance().getClass();
                                    sb60.append("丢　　弃".substring(0, 1));
                                    final StringBuffer sb61 = sb59;
                                    MenuUI.getInstance().getClass();
                                    sb61.append("丢　　弃".substring(3));
                                    final StringBuffer sb62 = sb59;
                                    GameUI.getInstance().getClass();
                                    sb62.append(" ");
                                    sb59.append(((PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()))).strName);
                                    final StringBuffer sb63 = sb59;
                                    GameUI.getInstance().getClass();
                                    sb63.append(" ");
                                    final StringBuffer sb64 = sb59;
                                    GameUI.getInstance().getClass();
                                    sb64.append("？");
                                    GameControl.getInstance().CreateState((byte) 7);
                                    DialogUI.getInstance().setDialog((byte) 122, sb59.toString(), (byte) 2);
                                    break;
                                }
                                final String s147 = s;
                                MenuUI.getInstance().getClass();
                                if (s147.indexOf("整　　理") != -1) {
                                    DCanvas.getInstance().setNetLoad(true);
                                    NetSend.getInstance().sendFrameEquip((byte) 5, Param.getInstance().personalPackage.getCellBoxIndex(), 0);
                                    break;
                                }
                                final String s148 = s;
                                MenuUI.getInstance().getClass();
                                if (s148.indexOf("升级背包") != -1) {
                                    final StringBuffer sb65 = new StringBuffer();
                                    sb65.append("升级将花费");
                                    sb65.append(((Param.getInstance().packageBoxMaxNum - 16) / 8 + 1) * ((Param.getInstance().packageBoxMaxNum - 16) / 8 + 1) * 200);
                                    sb65.append("点，增加8格");
                                    MenuUI.getInstance().setTwoRect(sb65.toString(), 0, bytHandleMove, (byte) 1, 1);
                                    break;
                                }
                                final String s149 = s;
                                MenuUI.getInstance().getClass();
                                if (s149.indexOf("添　　加") == -1) {
                                    final String s150 = s;
                                    MenuUI.getInstance().getClass();
                                    if (s150.indexOf("添加物品") == -1) {
                                        final String s151 = s;
                                        MenuUI.getInstance().getClass();
                                        if (s151.indexOf("打　　孔") != -1) {
                                            if (!Param.ASKING_ONCE) {
                                                DCanvas.getInstance().setNetLoad(true);
                                                NetSend.getInstance().sendTimeAaking();
                                                break;
                                            }
                                            final byte[] holeDate = ((PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()))).holeDate;
                                            int n5 = 0;
                                            for (int k = 0; k < holeDate.length; ++k) {
                                                if (holeDate[k] != 0) {
                                                    ++n5;
                                                }
                                            }
                                            final byte bitHoleTime = Param.getInstance().bitHoleTime;
                                            final int n6 = Param.getInstance().EachHolePrice[n5];
                                            GameControl.getInstance().CreateState((byte) 7);
                                            if (n5 + 1 < 10) {
                                                DialogUI.getInstance().setDialog((byte) 103, "当前可打孔位为第" + (n5 + 1) + "孔，需要打孔石1个，需要金钱" + n6 + ",打孔成功率100%，确定继续吗？", (byte) 2);
                                                break;
                                            }
                                            DialogUI.getInstance().setDialog((byte) 103, "当前可打孔位为第" + (n5 + 1) + "孔，需要打孔石1个，需要金钱" + n6 + ",打孔有失败几率，如失败会失去当前装备，确定继续吗？", (byte) 2);
                                            break;
                                        } else {
                                            final String s152 = s;
                                            MenuUI.getInstance().getClass();
                                            if (s152.indexOf("镶　　嵌") != -1) {
                                                Param.getInstance().EquipIndex = Param.getInstance().personalPackage.getCellBoxIndex();
                                                MenuUI.getInstance().getClass();
                                                MenuUI.StrengthenState = 1;
                                                MenuUI.getInstance().setState((byte) 11, "镶嵌宝石");
                                                break;
                                            }
                                            final String s153 = s;
                                            MenuUI.getInstance().getClass();
                                            if (s153.indexOf("剥　　离") != -1) {
                                                Param.getInstance().EquipIndex = Param.getInstance().personalPackage.getCellBoxIndex();
                                                MenuUI.getInstance();
                                                MenuUI.getInstance().getClass();
                                                MenuUI.StrengthenState = 2;
                                                MenuUI.getInstance().setState((byte) 11, "镶嵌宝石");
                                                break;
                                            }
                                            final String s154 = s;
                                            MenuUI.getInstance().getClass();
                                            if (s154.indexOf("移　　动") != -1) {
                                                DCanvas.getInstance().addInformation("暂未开放，敬请期待");
                                                break;
                                            }
                                            break;
                                        }
                                    }
                                }
                                FormDes.getInstance().setChatAddProp_Equip(MenuUI.getInstance().bytMoveType, Param.getInstance().personalPackage.getCellBoxIndex());
                                break;
                            }
                        }
                    }
                    final String strName14 = ((PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()))).strName;
                    int intColor9;
                    if (((PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()))).shtStamina == 0) {
                        intColor9 = 6579300;
                    } else {
                        intColor9 = ((PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()))).intColor;
                    }
                    final String strInfo9 = ((PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()))).strInfo;
                    final byte bytAggrandizementLv8 = ((PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()))).bytAggrandizementLv;
                    if (strInfo9.equals("")) {
                        GameUI.getInstance().getEquioAttribute(Param.getInstance().hPackage, Param.getInstance().personalPackage.getCellBoxIndex());
                        final FullInfo fullInfo14 = new FullInfo(String.valueOf(strName14) + ((bytAggrandizementLv8 > 0) ? (" +" + bytAggrandizementLv8) : ""), intColor9, this.bytState);
                        FullInfo.getInstance().setFullRectMenu_Vector();
                        break;
                    }
                    new FullInfo(strName14, intColor9, this.bytState);
                    FullInfo.getInstance().setFullRectMenu(strInfo9);
                    break;
                } else {
                    final PackageObject packageObject7 = (PackageObject) Param.getInstance().hPackageEquip.get(new Integer(MenuUI.getInstance().rView.getViewCurrentIndex()));
                    final String s155 = s;
                    MenuUI.getInstance().getClass();
                    if (s155.indexOf("查看属性") != -1) {
                        if (packageObject7.strInfo.equals("")) {
                            GameUI.getInstance().getEquioAttribute(Param.getInstance().hPackageEquip, MenuUI.getInstance().rView.getViewCurrentIndex());
                            final FullInfo fullInfo15 = new FullInfo(String.valueOf(packageObject7.strName) + ((packageObject7.bytAggrandizementLv > 0) ? (" +" + packageObject7.bytAggrandizementLv) : ""), packageObject7.intColor, this.bytState);
                            FullInfo.getInstance().setFullRectMenu_Vector();
                            break;
                        }
                        new FullInfo(packageObject7.strName, packageObject7.intColor, this.bytState);
                        FullInfo.getInstance().setFullRectMenu(packageObject7.strInfo);
                        break;
                    } else {
                        final String s156 = s;
                        MenuUI.getInstance().getClass();
                        if (s156.indexOf("卸　　下") == -1) {
                            final String s157 = s;
                            MenuUI.getInstance().getClass();
                            if (s157.indexOf("添　　加") == -1) {
                                final String s158 = s;
                                MenuUI.getInstance().getClass();
                                if (s158.indexOf("添加物品") == -1) {
                                    break;
                                }
                            }
                            FormDes.getInstance().setChatAddProp_Equip(MenuUI.getInstance().bytMoveType, MenuUI.getInstance().rView.getViewCurrentIndex());
                            break;
                        }
                        if (Param.getInstance().hPackage != null && !Param.getInstance().hPackage.isEmpty() && Param.getInstance().hPackage.size() >= Param.getInstance().packageBoxMaxNum) {
                            DCanvas.getInstance().addInformation("你的背包满了");
                            break;
                        }
                        DCanvas.getInstance().setNetLoad(true);
                        NetSend.getInstance().sendFrameEquip((byte) 2, MenuUI.getInstance().rView.getViewCurrentIndex(), packageObject7.intId);
                        break;
                    }
                }
            }
            case -3: {
                byte b5 = 0;
                if (s.indexOf(DialogUI.getInstance().STRING_OPTION_ALLOT[0]) != -1) {
                    b5 = 1;
                    DCanvas.getInstance().setNetLoad(true);
                } else if (s.indexOf(DialogUI.getInstance().STRING_OPTION_ALLOT[1]) != -1) {
                    b5 = 0;
                }
                NetSend.getInstance().sendSelectGoods((short) ((PackageObject) Param.getInstance().vEquipDialog.elementAt(DialogUI.getInstance().getMove())).intId, b5);
                DialogUI.getInstance().delEquipDialog(DialogUI.getInstance().getMove());
                break;
            }
            case 9: {
                final String s159 = s;
                DialogUI.getInstance().getClass();
                if (s159.indexOf("查看属性") != -1) {
                    final byte dealState = DialogUI.getInstance().dealState;
                    DialogUI.getInstance().getClass();
                    if (dealState == 0) {
                        final String strInfo10 = ((PackageObject) Param.getInstance().vDealMyProp.get(new Integer(DialogUI.getInstance().dealMyBox.getCellBoxIndex()))).strInfo;
                        final String strName15 = ((PackageObject) Param.getInstance().vDealMyProp.get(new Integer(DialogUI.getInstance().dealMyBox.getCellBoxIndex()))).strName;
                        final int intColor10 = ((PackageObject) Param.getInstance().vDealMyProp.get(new Integer(DialogUI.getInstance().dealMyBox.getCellBoxIndex()))).intColor;
                        if (strInfo10.equals("")) {
                            GameUI.getInstance().getEquioAttribute(Param.getInstance().vDealMyProp, DialogUI.getInstance().dealMyBox.getCellBoxIndex());
                            new FullInfo(strName15, intColor10, this.bytState);
                            FullInfo.getInstance().setFullRectMenu_Vector();
                            break;
                        }
                        new FullInfo(strName15, intColor10, this.bytState);
                        FullInfo.getInstance().setFullRectMenu(strInfo10);
                        break;
                    } else {
                        final byte dealState2 = DialogUI.getInstance().dealState;
                        DialogUI.getInstance().getClass();
                        if (dealState2 == 1) {
                            final String strInfo11 = ((PackageObject) Param.getInstance().vDealDuiProp.get(new Integer(DialogUI.getInstance().dealPeerBox.getCellBoxIndex()))).strInfo;
                            final String strName16 = ((PackageObject) Param.getInstance().vDealDuiProp.get(new Integer(DialogUI.getInstance().dealPeerBox.getCellBoxIndex()))).strName;
                            final int intColor11 = ((PackageObject) Param.getInstance().vDealDuiProp.get(new Integer(DialogUI.getInstance().dealPeerBox.getCellBoxIndex()))).intColor;
                            if (strInfo11.equals("")) {
                                GameUI.getInstance().getEquioAttribute(Param.getInstance().vDealDuiProp, DialogUI.getInstance().dealPeerBox.getCellBoxIndex());
                                new FullInfo(strName16, intColor11, this.bytState);
                                FullInfo.getInstance().setFullRectMenu_Vector();
                                break;
                            }
                            new FullInfo(strName16, intColor11, this.bytState);
                            FullInfo.getInstance().setFullRectMenu(strInfo11);
                            break;
                        } else {
                            final byte dealState3 = DialogUI.getInstance().dealState;
                            DialogUI.getInstance().getClass();
                            if (dealState3 != 4) {
                                break;
                            }
                            final String strInfo12 = ((PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()))).strInfo;
                            final String strName17 = ((PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()))).strName;
                            final int intColor12 = ((PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()))).intColor;
                            if (strInfo12.equals("")) {
                                GameUI.getInstance().getEquioAttribute(Param.getInstance().hPackage, Param.getInstance().personalPackage.getCellBoxIndex());
                                new FullInfo(strName17, intColor12, this.bytState);
                                FullInfo.getInstance().setFullRectMenu_Vector();
                                break;
                            }
                            new FullInfo(strName17, intColor12, this.bytState);
                            FullInfo.getInstance().setFullRectMenu(strInfo12);
                            break;
                        }
                    }
                } else {
                    final String s160 = s;
                    DialogUI.getInstance().getClass();
                    if (s160.indexOf("添加物品") != -1) {
                        final PackageObject packageObject8 = (PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()));
                        boolean b6 = true;
                        if (Param.getInstance().bytDealGoods == 0) {
                            if (packageObject8.bytIsBind != 0) {
                                b6 = false;
                            }
                        } else if (packageObject8.bytOperation != 1) {
                            b6 = false;
                        }
                        if (!b6) {
                            final DCanvas instance13 = DCanvas.getInstance();
                            MenuUI.getInstance().getClass();
                            instance13.addInformation("该物品无法交易");
                            break;
                        }
                        if (packageObject8.shtPOnum > 1) {
                            final DialogUI instance14 = DialogUI.getInstance();
                            MenuUI.getInstance().getClass();
                            instance14.setTwoNumber("输入交易数量", packageObject8.shtPOnum, 1, (byte) 0);
                            break;
                        }
                        if (Param.getInstance().vDealMyProp == null) {
                            Param.getInstance().vDealMyProp = new Hashtable();
                        }
                        if (Param.getInstance().vDealMyProp.size() < 6) {
                            NetSend.getInstance().sendDealGoods(Param.getInstance().bytDealGoods, packageObject8.intId, (short) packageObject8.intPOindex, (short) 1);
                            DialogUI.getInstance().addDealProp(Param.getInstance().vDealMyProp, packageObject8, (short) 1);
                            DialogUI.getInstance().dealPackageTabIndex = -1;
                            DialogUI.getInstance().setDealPackageMessage();
                            break;
                        }
                        DCanvas.getInstance().addInformation("无法再添加物品");
                        break;
                    } else {
                        final String s161 = s;
                        DialogUI.getInstance().getClass();
                        if (s161.indexOf("撤销物品") != -1) {
                            if (Param.getInstance().vDealMyProp != null && Param.getInstance().vDealMyProp.containsKey(new Integer(DialogUI.getInstance().dealMyBox.getCellBoxIndex()))) {
                                NetSend.getInstance().sendCancelDealGoods(((PackageObject) Param.getInstance().vDealMyProp.get(new Integer(DialogUI.getInstance().dealMyBox.getCellBoxIndex()))).intId, DialogUI.getInstance().dealMyBox.getCellBoxIndex());
                                Param.getInstance().vDealMyProp.remove(new Integer(DialogUI.getInstance().dealMyBox.getCellBoxIndex()));
                                if (Param.getInstance().vDealMyProp.isEmpty()) {
                                    Param.getInstance().vDealMyProp = null;
                                }
                                DialogUI.getInstance().dealPackageTabIndex = -1;
                                DialogUI.getInstance().setDealPackageMessage();
                                break;
                            }
                            break;
                        } else {
                            final String s162 = s;
                            DialogUI.getInstance().getClass();
                            if (s162.indexOf("输入金币") != -1) {
                                if (ORPMe.Gold == 0) {
                                    DCanvas.getInstance().addInformation("金钱为0");
                                    break;
                                }
                                final DialogUI instance15 = DialogUI.getInstance();
                                DialogUI.getInstance().getClass();
                                instance15.setTwoNumber("输入交易金额", ORPMe.Gold, 1, (byte) 0);
                                break;
                            } else {
                                final String s163 = s;
                                DialogUI.getInstance().getClass();
                                if (s163.indexOf("锁定交易") != -1) {
                                    DialogUI.getInstance().dealIsMyLock = true;
                                    NetSend.getInstance().sendLockDeal();
                                    break;
                                }
                                final String s164 = s;
                                DialogUI.getInstance().getClass();
                                if (s164.indexOf("确定交易") == -1) {
                                    break;
                                }
                                if (!DialogUI.getInstance().dealIsMyLock) {
                                    DCanvas.getInstance().addInformation("请先锁定交易");
                                    break;
                                }
                                if (!DialogUI.getInstance().dealIsPeerLock) {
                                    DCanvas.getInstance().addInformation("对方未锁定交易，请等待");
                                    break;
                                }
                                NetSend.getInstance().sendDeal((byte) 5, Param.getInstance().intDealID);
                                break;
                            }
                        }
                    }
                }
            }
            case -72:
            case -35: {
                final String s165 = s;
                MenuUI.getInstance().getClass();
                if (s165.indexOf("购买物品") != -1) {
                    MenuUI.getInstance().buyShop();
                }
                String s166 = null;
                final String s167 = s;
                MenuUI.getInstance().getClass();
                if (s167.indexOf("开启商店") != -1) {
                    if (Param.getInstance().hShopPackage != null && !Param.getInstance().hShopPackage.isEmpty()) {
                        if (ORPMe.ME.blnStall) {
                            s166 = "您的商店已开启";
                        } else {
                            DCanvas.getInstance().setNetLoad(true);
                            NetSend.getInstance().sendAddShopProp();
                        }
                    } else {
                        s166 = "您的摊位没有商品";
                    }
                } else {
                    final String s168 = s;
                    MenuUI.getInstance().getClass();
                    if (s168.indexOf("关闭商店") != -1) {
                        if (ORPMe.ME.blnStall) {
                            GameUI.getInstance().setDialog((byte) 17);
                        } else {
                            s166 = "您的商店未开启";
                        }
                    } else {
                        final String s169 = s;
                        MenuUI.getInstance().getClass();
                        if (s169.indexOf("更改店名") != -1) {
                            FormDes.getInstance().showForm((byte) (-101));
                        } else {
                            final String s170 = s;
                            MenuUI.getInstance().getClass();
                            if (s170.indexOf("查看属性") != -1) {
                                final byte personalShopState = MenuUI.getInstance().personalShopState;
                                MenuUI.getInstance().getClass();
                                if (personalShopState == 0) {
                                    final String strInfo13 = ((PackageObject) Param.getInstance().hShopPackage.get(new Integer(MenuUI.getInstance().personalShopSellPackage.getCellBoxIndex()))).strInfo;
                                    final String strName18 = ((PackageObject) Param.getInstance().hShopPackage.get(new Integer(MenuUI.getInstance().personalShopSellPackage.getCellBoxIndex()))).strName;
                                    final int intColor13 = ((PackageObject) Param.getInstance().hShopPackage.get(new Integer(MenuUI.getInstance().personalShopSellPackage.getCellBoxIndex()))).intColor;
                                    if (strInfo13.equals("")) {
                                        GameUI.getInstance().getEquioAttribute(Param.getInstance().hShopPackage, MenuUI.getInstance().personalShopSellPackage.getCellBoxIndex());
                                        new FullInfo(strName18, intColor13, this.bytState);
                                        FullInfo.getInstance().setFullRectMenu_Vector();
                                    } else {
                                        new FullInfo(strName18, intColor13, this.bytState);
                                        FullInfo.getInstance().setFullRectMenu(strInfo13);
                                    }
                                } else {
                                    final byte personalShopState2 = MenuUI.getInstance().personalShopState;
                                    MenuUI.getInstance().getClass();
                                    if (personalShopState2 == 2) {
                                        final String strInfo14 = ((PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()))).strInfo;
                                        final String strName19 = ((PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()))).strName;
                                        final int intColor14 = ((PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()))).intColor;
                                        if (strInfo14.equals("")) {
                                            GameUI.getInstance().getEquioAttribute(Param.getInstance().hPackage, Param.getInstance().personalPackage.getCellBoxIndex());
                                            new FullInfo(strName19, intColor14, this.bytState);
                                            FullInfo.getInstance().setFullRectMenu_Vector();
                                        } else {
                                            new FullInfo(strName19, intColor14, this.bytState);
                                            FullInfo.getInstance().setFullRectMenu(strInfo14);
                                        }
                                    }
                                }
                            } else {
                                final String s171 = s;
                                MenuUI.getInstance().getClass();
                                if (s171.indexOf("更改价格") != -1) {
                                    final String strName20 = ((PackageObject) Param.getInstance().hShopPackage.get(new Integer(MenuUI.getInstance().personalShopSellPackage.getCellBoxIndex()))).strName;
                                    final StringBuffer sb66 = new StringBuffer();
                                    sb66.append("请输入");
                                    sb66.append(strName20);
                                    sb66.append("的变更价格：");
                                    Param.getInstance().bytPopRectStep = 3;
                                    MenuUI.getInstance().setTwoNumber(sb66.toString(), 999999999, -1, bytHandleMove);
                                } else {
                                    final String s172 = s;
                                    MenuUI.getInstance().getClass();
                                    if (s172.indexOf("撤销商品") != -1) {
                                        final PackageObject packageObject9 = ((PackageObject) Param.getInstance().hShopPackage.get(new Integer(MenuUI.getInstance().personalShopSellPackage.getCellBoxIndex())));
                                        if (packageObject9.blnUse) {
                                            Param.getInstance().hShopPackage.remove(new Integer(MenuUI.getInstance().personalShopSellPackage.getCellBoxIndex()));
                                            final MenuUI instance16 = MenuUI.getInstance();
                                            --instance16.bytAddShopNumber;
                                            if (Param.getInstance().hShopPackage.isEmpty()) {
                                                Param.getInstance().hShopPackage = null;
                                            }
                                            MenuUI.getInstance().setInfoContent(Param.getInstance().hShopPackage, MenuUI.getInstance().personalShopSellPackage.getCellBoxIndex());
                                        } else {
                                            DCanvas.getInstance().setNetLoad(true);
                                            NetSend.getInstance().sendShop_DelProp(MenuUI.getInstance().personalShopSellPackage.getCellBoxIndex(), packageObject9.intId);
                                        }
                                        MenuUI.getInstance().personalShopPersonalPackageTabIndex = -1;
                                        MenuUI.getInstance().setPersonalShopPackageMessage();
                                    } else {
                                        final String s173 = s;
                                        MenuUI.getInstance().getClass();
                                        if (s173.indexOf("商品上架") != -1) {
                                            if (MenuUI.getInstance().bytAddShopNumber >= Param.getInstance().bytShopBag) {
                                                DCanvas.getInstance().addInformation("无法再添加物品");
                                            } else {
                                                final PackageObject packageObject10 = (PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()));
                                                if (packageObject10.bytIsBind == 0) {
                                                    final short shtPOnum2 = packageObject10.shtPOnum;
                                                    if (shtPOnum2 > 1) {
                                                        Param.getInstance().bytPopRectStep = 0;
                                                        MenuUI.getInstance().setTwoNumber("输入添加数量：", shtPOnum2, -1, (byte) 0);
                                                    } else {
                                                        Param.getInstance().bytPopRectStep = 2;
                                                        MenuUI.getInstance().setTwoNumber("输入出售物品总价：", 999999999, -1, (byte) (-1));
                                                    }
                                                } else {
                                                    DCanvas.getInstance().addInformation("该物品无法交易");
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (s166 != null) {
                    DCanvas.getInstance().addInformation(s166);
                    break;
                }
                break;
            }
            case -82: {
                if (s.indexOf("更改键位") != -1) {
                    MenuUI.getInstance().setTwoRect("更改键位", 16711680, (short) 1, (byte) 30, -1);
                    break;
                }
                if (s.indexOf("恢复默认") != -1) {
                    MenuUI.getInstance().setTwoRect("是否恢复成系统默认键位？", 16711680, (short) 1, (byte) 1, -1);
                    break;
                }
                break;
            }
        }
        if (b) {
            GameControl.getInstance().delUIbase(this.bytTemp);
        }
    }

    public void setTwoMenu(final String[] array) {
        this.strHandleOption = new String[array.length];
        int n = 0;
        for (int i = 0; i < this.strHandleOption.length; i = (byte) (i + 1)) {
            this.strHandleOption[i] = String.valueOf(i + 1) + "." + array[i];
            final int stringWidth = Macro.font.stringWidth(this.strHandleOption[i]);
            if (n < stringWidth) {
                n = stringWidth;
            }
        }
        final short[] shtOptionPlace = {(short) ((this.strHandleOption.length + 1) * (Macro.FONTHEIGHT + 5)), (short) n, (short) ((Macro.SCREEN_HEIGHT - (short) (this.strHandleOption.length * (Macro.FONTHEIGHT + 5))) / 2 + 5), 0};
        shtOptionPlace[3] = (short) ((Macro.SCREEN_WIDTH - shtOptionPlace[1]) / 2);
        this.shtOptionPlace = shtOptionPlace;
        this.shtFramePlace = this.getFrameRect();
    }

    public void drawTwoMenuRect(final Graphics graphics) {
        final short[] frameRect = this.getFrameRect();
        DrawBase.drawBox(frameRect[0], frameRect[1], frameRect[2], frameRect[3], new int[]{6494722, 16777215, 16763066, 11370322}, true);
    }

    public short[] getFrameRect() {
        final short n = (short) (this.shtOptionPlace[1] * 3 / 2);
        final short n2 = this.shtOptionPlace[0];
        return new short[]{(short) ((Macro.SCREEN_WIDTH - n) / 2), (short) ((Macro.SCREEN_HEIGHT - n2) / 2), n, n2};
    }

    public void drawDetails(final Graphics graphics) {
        DCanvas.getInstance().clearScreen();
        if (this.strHandleOption != null) {
            if (this.strHandleOption.length > this.bytHandleMaxH) {
                for (byte b = 0; b < this.bytHandleMaxH; ++b) {
                    DrawBase.drawBox(this.shtOptionPlace[3] - 5, this.shtOptionPlace[2] + b * (Macro.FONTHEIGHT + 5) - 3, this.shtOptionPlace[1] + 10, Macro.FONTW + 6 + 2, new int[]{9263661, 14995858}, true);
                    graphics.setColor((b == this.bytHandleMove) ? 10944554 : 0);
                    graphics.drawString(this.strHandleOption[b + this.bytHandleRoll], this.shtOptionPlace[3], this.shtOptionPlace[2] + b * (Macro.FONTHEIGHT + 5), 20);
                }
            } else {
                for (byte b2 = 0; b2 < this.strHandleOption.length; ++b2) {
                    DrawBase.drawBox(this.shtOptionPlace[3] - 5, this.shtOptionPlace[2] + b2 * (Macro.FONTHEIGHT + 5) - 3, this.shtOptionPlace[1] + 10, Macro.FONTW + 6 + 2, new int[]{9263661, (b2 == this.bytHandleMove) ? 16753705 : 16762219}, true);
                    graphics.setColor((b2 == this.bytHandleMove) ? 10944554 : 0);
                    graphics.drawString(this.strHandleOption[b2], this.shtOptionPlace[3], this.shtOptionPlace[2] + b2 * (Macro.FONTHEIGHT + 5), 20);
                }
            }
        }
        DCanvas.getInstance().clearScreen();
    }

    public void setPointer() {
        if (this.strHandleOption == null) {
            return;
        }
        for (byte b = (byte) ((this.strHandleOption.length > this.bytHandleMaxH) ? this.bytHandleMaxH : this.strHandleOption.length), b2 = 0; b2 < b; ++b2) {
            if (DCanvas.getInstance().IsPointerDown(this.shtOptionPlace[3] - 5, this.shtOptionPlace[2] + b2 * (Macro.FONTHEIGHT + 5) - 3, this.shtOptionPlace[1] + 10, Macro.FONTW + 6 + 2)) {
                if (this.bytHandleMove == b2) {
                    this.keyAnswer(b2);
                    return;
                }
                this.bytHandleMove = b2;
            }
        }
    }
}
