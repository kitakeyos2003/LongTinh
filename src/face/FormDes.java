// Decompiled with: CFR 0.151
// Class Version: 1
package face;

import base.DCanvas;
import base.GameControl;
import base.Macro;
import base.Param;
import common.Common;
import common.IResConfig;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;
import javax.microedition.lcdui.Ticker;
import means.Rms;
import model.MenuObject;
import model.ORPMe;
import model.ORPlayer;
import model.PackageObject;
import network.NetSend;

public class FormDes
        extends Form
        implements CommandListener {

    public static FormDes fd;
    private final String STRING_TITLE_INPUT_NAME;
    private final String STRING_TITLE_MAIL;
    private final String STRING_TITLE_SEND_CHAT;
    private String[] STRING_TITLE_CHANNAL_NAME = new String[]{"Chat riêng", "Thế giới", "Khu vực", "Đội", "Bang hội", "Gia tộc"};
    private final String STRING_COMMAND_GOODS;
    private final String STRING_INPUT_NAME;
    private final String STRING_INPUT_MAIL;
    private final String STRING_INPUT_SUBJECT;
    private final String STRING_INPUT_TEXT;
    private final String STRING_GOOD_TEXT;
    private final String STRING_OPTION_CHANNEL;
    private final String STRING_COMMAND_OK;
    private final String STRING_COMMAND_SEND;
    private final String STRING_COMMAND_SEND_GOOD;
    private final String STRING_COMMAND_BACK;
    private final String STRING_COMMAND_FACE;
    private final String STRING_INFO_INPUTFRONT;
    private final String STRING_INFO_INPUTHIT;
    private final String STRING_INFO_INPUTBACK;
    private final String[] STRING_TALK_CHANNEL = new String[]{"Chat riêng-chỉ người được mời mới thấy", "Tộc-người cùng tộc đều thấy được", "Thế giới-mọi người đều thấy", "Khu vực-cùng khu mới thấy"};
    private final String STRING_TALK_CHANNEL_CONSORTIA;
    private final String STRING_TALK_CHANNEL_TROOP;
    private final String STRING_CUE_NAME_SMALL;
    private final String STRING_CUE_NAME_ONESELF;
    private final String STRING_CUE_NAME_NULL;
    private final String STRING_CUE_CONTENT;
    private final String STRING_CUE_TEXT;
    private final String STRING_CUE_LOWER_LIMIT;
    private final String STRING_MALL_CARD;
    private final String STRING_MALL_PWD;
    private final String STRING_FOR_USER_NAME;
    private final String STRING_MALL_FOR_NAME;
    public byte bytFormState;
    private Command okCommand;
    private Command faceCommand;
    private Command changeChannal;
    private Command backCommand;
    private Command addCommand;
    private Command chat1Command;
    private Command chat2Command;
    private Command chat3Command;
    private Command chat4Command;
    private Command chat5Command;
    private TextField nameField;
    private TextField titleField;
    private Ticker TickerHint;
    public TextField inputField;
    private TextField goodsField;
    private TextField mallCardField;
    private TextField mallPwdField;
    private ChoiceGroup CGSelect;
    public String chatstr;
    private String strChatName;
    public String strTitle;
    private byte bytChatType = 1;
    public int intCurPlace;
    public final byte bytChatInputMax = (byte) 24;
    public final String petNameStr;
    public final byte bytPetInputMax = (byte) 6;
    public byte bytClarionPlace;
    public byte bytTypeMail;
    private String mailname;
    private String mailtitle;
    private String mailcontent;
    private String mailGoodscontent;
    private TextField[] recvInputField;
    private String[] recvInputTxt;
    private byte ForamState;
    private final byte bytAccountInputMax = (byte) 11;
    private final byte bytAccountInputMin = (byte) 6;
    private TextField InputAccount;
    private TextField InputPassword;
    private TextField MakeSurePassword;
    private TextField InputTelNumber;
    private TextField MakeSureTelNumber;
    private TextField NewPassword;
    private TextField MakeSureNewPassword;

    public FormDes() {
        super(null);
        this.STRING_TITLE_INPUT_NAME = "Nhập tên";
        this.STRING_TITLE_MAIL = "Viết thư";
        this.STRING_TITLE_SEND_CHAT = "Mời trò chuyện - ";
        this.STRING_COMMAND_GOODS = "Chèn vật phẩm";
        this.STRING_INPUT_NAME = "Tên:";
        this.STRING_INPUT_MAIL = "Người nhận:";
        this.STRING_INPUT_SUBJECT = "Tiêu đề:";
        this.STRING_INPUT_TEXT = "Nội dung:";
        this.STRING_GOOD_TEXT = "Phụ kiện:";
        this.STRING_OPTION_CHANNEL = "Chọn kênh:";
        this.STRING_COMMAND_OK = "Xác nhận";
        this.STRING_COMMAND_SEND = "Gửi";
        this.STRING_COMMAND_SEND_GOOD = "Thêm tiền";
        this.STRING_COMMAND_BACK = "Hủy";
        this.STRING_COMMAND_FACE = "Thêm biểu cảm";
        this.STRING_INFO_INPUTFRONT = "Mời nhập…";
        this.STRING_INFO_INPUTHIT = "Đến";
        this.STRING_INFO_INPUTBACK = " kí tự";
        this.STRING_TALK_CHANNEL_CONSORTIA = "Bang-người của bang mới thấy được";
        this.STRING_TALK_CHANNEL_TROOP = "Đội-thành viên trong đội đều thấy được";
        this.STRING_CUE_NAME_SMALL = "Tên không quá 2 ký tự";
        this.STRING_CUE_NAME_ONESELF = "Tên không thể là bản thân";
        this.STRING_CUE_NAME_NULL = "Tên không thể là bản thân";
        this.STRING_CUE_CONTENT = "Không được bỏ trống nội dung";
        this.STRING_CUE_TEXT = "Không được bỏ trống chủ đề";
        this.STRING_CUE_LOWER_LIMIT = "Nhập ít nhất";
        this.STRING_MALL_CARD = "Số sê-ri";
        this.STRING_MALL_PWD = "Mã PIN";
        this.STRING_FOR_USER_NAME = "Tên nhân vật đối phương";
        this.STRING_MALL_FOR_NAME = "Nạp cho người khác (cùng sever)";
        this.petNameStr = "Tên nhân vật giới hạn 2-8 ký tự, bao gồm chữ la tinh và số";
    }

    public static FormDes getInstance() {
        if (fd == null) {
            fd = new FormDes();
        }
        return fd;
    }

    public void showFormDes() {
        GameControl.getInstance().setScreen(this);
    }

    public void setMail(String string, String string2) {
        this.strChatName = string;
        this.strTitle = string2;
        this.showForm((byte) -37);
    }

    public void setSingleChat(String string) {
        this.strChatName = string;
        this.showForm((byte) 41);
    }

    public void SelcetChannel(String string) {
        this.strTitle = string;
        this.showForm((byte) 45);
    }

    public void setAddFriendName(String string) {
        this.strChatName = string;
        if (MenuUI.getInstance().blnNpcMailSendGood) {
            this.showForm((byte) -102);
        } else {
            this.showForm((byte) -100);
        }
    }

    public void setMailRecvAnswer(String string, String string2) {
        this.strChatName = string;
        this.strTitle = string2;
        this.showForm((byte) -42);
    }

    public void setMacroChat(String string, String string2) {
        this.strTitle = string;
        this.strChatName = string2;
        this.showForm((byte) -48);
    }

    public void setGmAppraise(String string) {
        this.showForm((byte) -71);
        this.strChatName = string;
    }

    public void setLandState(String string, byte by) {
        LandUI.getInstance().LoadAccoundPassword();
        this.strTitle = string;
        this.ForamState = by;
        this.showForm((byte) 22);
    }

    public void showForm(byte by) {
        this.bytFormState = by;
        this.clearForm();
        GameControl.getInstance().setScreen(this);
        switch (by) {
            case -23: {
                this.setTitle("Nạp cho người khác (cùng sever)");
                this.nameField = new TextField("Tên nhân vật đối phương", Param.getInstance().MALL_FOR_NAME, 6, 0);
                this.okCommand = new Command("Xác nhận", 4, 0);
                this.backCommand = new Command("Hủy", 2, 1);
                this.append(this.nameField);
                this.addCommand(this.backCommand);
                this.addCommand(this.okCommand);
                break;
            }
            case -19: {
                this.setTitle("Nhập tên");
                this.mallCardField = new TextField("Số sê-ri", Param.getInstance().MALL_CARD_NUMBER, 24, 0);
                this.mallPwdField = new TextField("Mã PIN", Param.getInstance().MALL_PASSWORD_NUMBER, 24, 0);
                this.okCommand = new Command("Xác nhận", 4, 0);
                this.backCommand = new Command("Hủy", 2, 1);
                this.append(this.mallCardField);
                this.append(this.mallPwdField);
                this.addCommand(this.backCommand);
                this.addCommand(this.okCommand);
                break;
            }
            case 19: {
                this.nameField = new TextField("Tên:", LandUI.getInstance().strName, 6, 0);
                this.okCommand = new Command("Xác nhận", 4, 0);
                this.backCommand = new Command("Hủy", 2, 1);
                this.setTitle("Nhập tên");
                this.append(this.nameField);
                this.append("Tên nhân vật giới hạn 2-6 ký tự, bao gồm chữ la tinh và số");
                this.addCommand(this.backCommand);
                this.addCommand(this.okCommand);
                break;
            }
            case 22: {
                this.setTitle(this.strTitle);
                switch (this.ForamState) {
                    case 0: {
                        String string;
                        String string2;
                        if (Param.getInstance().Forma_ccount == null || Param.getInstance().Forma_password == null) {
                            string2 = LandUI.getInstance().strLandAccounts;
                            string = LandUI.getInstance().strLandPassword;
                        } else {
                            string2 = Param.getInstance().Forma_ccount;
                            string = Param.getInstance().Forma_password;
                        }
                        this.InputAccount = new TextField("Tài khoản:", string2, 11, 0);
                        this.InputPassword = new TextField("Mật khẩu:", string, 11, 0);
                        this.append(this.InputAccount);
                        this.append(this.InputPassword);
                        break;
                    }
                    case 1: {
                        this.InputAccount = new TextField("Tài khoản:", "", 11, 0);
                        this.InputPassword = new TextField("Mật khẩu:", "", 11, 0);
                        this.MakeSurePassword = new TextField("Xác nhận mật khẩu", "", 11, 0);
                        this.append(this.InputAccount);
                        this.append(this.InputPassword);
                        this.append(this.MakeSurePassword);
                        this.append("Khi sử dụng số điện thoại làm mật mã cần khóa bảo vệ");
                        break;
                    }
                    case 2: {
                        this.InputAccount = new TextField("Tài khoản:", LandUI.getInstance().strLandAccounts, 11, 0);
                        this.InputPassword = new TextField("Mật khẩu:", "", 11, 0);
                        this.InputTelNumber = new TextField("Nhập số điện thoại:", "", 11, 3);
                        this.MakeSureTelNumber = new TextField("Xác nhận số điện thoại:", "", 11, 3);
                        this.append(this.InputAccount);
                        this.append(this.InputPassword);
                        this.append(this.InputTelNumber);
                        this.append(this.MakeSureTelNumber);
                        break;
                    }
                    case 3: {
                        this.InputAccount = new TextField("Tài khoản:", "", 11, 0);
                        this.append(this.InputAccount);
                        this.append("Để tìm lại mật mã, chúng tôi sẽ gửi mật mã mới tới số điện thoại mà bạn đã đăng ký khi tạo tài khoản, chúc bạn chơi vui!");
                        break;
                    }
                    case 4: {
                        this.InputAccount = new TextField("Tài khoản:", LandUI.getInstance().strLandAccounts, 11, 0);
                        this.InputPassword = new TextField("Mật khẩu cũ:", "", 11, 0);
                        this.NewPassword = new TextField("Mật khẩu mới:", "", 11, 0);
                        this.MakeSureNewPassword = new TextField("Xác nhận mật khẩu mới:", "", 11, 0);
                        this.append(this.InputAccount);
                        this.append(this.InputPassword);
                        this.append(this.NewPassword);
                        this.append(this.MakeSureNewPassword);
                    }
                }
                this.okCommand = new Command("Xác nhận", 4, 0);
                this.backCommand = new Command("Hủy", 2, 1);
                this.addCommand(this.backCommand);
                this.addCommand(this.okCommand);
                break;
            }
            case 20: {
                this.nameField = new TextField("Tên:", "", 6, 0);
                this.okCommand = new Command("Xác nhận", 4, 0);
                this.backCommand = new Command("Hủy", 2, 1);
                this.setTitle("Nhập tên");
                this.append(this.nameField);
                this.append("Tên nhân vật giới hạn 2-6 ký tự, bao gồm chữ la tinh và số");
                this.addCommand(this.backCommand);
                this.addCommand(this.okCommand);
                break;
            }
            case 21: {
                this.setTitle(Param.getInstance().strNpcInputTitle);
                if (Param.getInstance().bytArrayNpcInputMax != null && Param.getInstance().bytArrayNpcInputMax.length > 0) {
                    this.recvInputField = new TextField[Param.getInstance().bytArrayNpcInputMax.length];
                    int n = 0;
                    while (n < Param.getInstance().bytArrayNpcInputMax.length) {
                        this.recvInputField[n] = new TextField(Param.getInstance().strArrayNpcInputName[n], "", Param.getInstance().bytArrayNpcInputMax[n], 0);
                        this.append(this.recvInputField[n]);
                        ++n;
                    }
                }
                this.okCommand = new Command("Xác nhận", 4, 0);
                this.backCommand = new Command("Hủy", 2, 1);
                this.append("Nhập văn bản…");
                this.addCommand(this.backCommand);
                this.addCommand(this.okCommand);
                break;
            }
            case -102: {
                if (Param.getInstance().hPackage != null) {
                    PackageObject packageObject = (PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()));
                    this.mailGoodscontent = String.valueOf(packageObject.strName) + (Param.getInstance().intNpcNum == 0 ? 1 : Param.getInstance().intNpcNum) + "件";
                } else if (Param.getInstance().intNpcNum != 0) {
                    this.mailGoodscontent = String.valueOf(Param.getInstance().intNpcNum) + "金";
                }
                if (this.nameField == null) {
                    this.nameField = new TextField("Người nhận:", this.mailname, 6, 0);
                }
                if (this.titleField == null) {
                    this.titleField = new TextField("Tiêu đề:", this.mailtitle, 6, 0);
                }
                if (this.inputField == null) {
                    this.inputField = new TextField("Nội dung:", this.mailcontent, 75, 0);
                }
                this.goodsField = new TextField("Phụ kiện:", this.mailGoodscontent, 75, 262144);
                this.okCommand = new Command("Xác nhận", 4, 0);
                this.backCommand = new Command("Hủy", 2, 1);
                this.addCommand = new Command("Thêm tiền", 4, 0);
                this.setTitle(Param.getInstance().strNpcInputTitle);
                if (Param.getInstance().strNpcInputTitle.indexOf("Người chơi") != -1 || Param.getInstance().strNpcInputTitle.indexOf("Tên") != -1) {
                    this.faceCommand = new Command("Danh sách bạn", 4, 1);
                }
                this.append(this.nameField);
                this.append(this.titleField);
                this.append(this.inputField);
                this.append(this.goodsField);
                this.addCommand(this.okCommand);
                this.addCommand(this.addCommand);
                this.addCommand(this.backCommand);
                break;
            }
            case -100: {
                if (Param.getInstance().strNpcInputTitle.indexOf("Tên bang") != -1) {
                    this.nameField = new TextField("Nhập tên bang", this.strChatName, Param.getInstance().bytNpcInputMax, 0);
                    this.okCommand = new Command("Xác nhận", 4, 0);
                    this.backCommand = new Command("Hủy", 2, 1);
                    this.setTitle("Lập bang hội");
                    this.append(this.nameField);
                    this.append("Tên bang hội nhiều nhất " + Param.getInstance().bytNpcInputMax + " ký tự, tạo bang hội cần 300,000 sò và đạo cụ bang hội.");
                    this.addCommand(this.backCommand);
                    this.addCommand(this.okCommand);
                    break;
                }
                this.nameField = new TextField("", this.strChatName, Param.getInstance().bytNpcInputMax, 0);
                this.okCommand = new Command("Xác nhận", 4, 0);
                this.backCommand = new Command("Hủy", 2, 1);
                this.setTitle(Param.getInstance().strNpcInputTitle);
                this.append(this.nameField);
                this.append("Mời nhập…" + Param.getInstance().bytNpcInputMin + "Đến" + Param.getInstance().bytNpcInputMax + " kí tự");
                this.addCommand(this.backCommand);
                this.addCommand(this.okCommand);
                if (Param.getInstance().strNpcInputTitle.indexOf("Người chơi") == -1 && Param.getInstance().strNpcInputTitle.indexOf("Tên") == -1) {
                    break;
                }
                this.faceCommand = new Command("Danh sách bạn", 4, 1);
                this.addCommand(this.faceCommand);
                break;
            }
            case -101: {
                this.nameField = new TextField("Tên tiệm:", MenuUI.getInstance().strShopName, 6, 0);
                this.okCommand = new Command("Xác nhận", 4, 0);
                this.backCommand = new Command("Hủy", 2, 1);
                this.setTitle("Nhập tên tiệm");
                this.append(this.nameField);
                this.append("Tên tiệm sẽ hiện ngay phía trên đầu bạn");
                this.addCommand(this.backCommand);
                this.addCommand(this.okCommand);
                break;
            }
            case -70: {
                this.CGSelect = new ChoiceGroup("Vấn đề có liên quan", 4, Macro.STRING_GM_TITLE, null);
                this.inputField = new TextField("", "", 75, 0);
                this.okCommand = new Command("Gửi", 4, 0);
                this.backCommand = new Command("Hủy", 2, 1);
                this.setTitle("Xin GM hỗ trợ");
                this.append(this.CGSelect);
                this.append(this.inputField);
                this.addCommand(this.okCommand);
                this.addCommand(this.backCommand);
                break;
            }
            case -71: {
                this.inputField = new TextField("", "", 75, 0);
                this.okCommand = new Command("Gửi", 4, 0);
                this.backCommand = new Command("Hủy", 2, 1);
                this.setTitle("GM trả lời");
                this.append(this.inputField);
                this.addCommand(this.okCommand);
                this.addCommand(this.backCommand);
                break;
            }
            case -37: {
                this.nameField = new TextField("Người nhận:", this.strChatName, 6, 0);
                this.titleField = new TextField("Tiêu đề:", this.strTitle, 6, 0);
                this.inputField = new TextField("Nội dung:", "", 75, 0);
                this.mailname = this.nameField.getString() == null ? "" : this.nameField.getString().trim();
                this.mailtitle = this.titleField.getString() == null ? "" : this.titleField.getString().trim();
                this.mailcontent = this.inputField.getString() == null ? "" : this.inputField.getString().trim();
                this.okCommand = new Command("Gửi", 4, 0);
                this.backCommand = new Command("Hủy", 2, 1);
                this.setTitle("Viết thư");
                this.append(this.nameField);
                this.append(this.titleField);
                this.append(this.inputField);
                this.addCommand(this.okCommand);
                this.addCommand(this.backCommand);
                break;
            }
            case -42: {
                this.nameField = new TextField("Người nhận:", this.strChatName, 6, 0);
                this.titleField = new TextField("Tiêu đề:", this.strTitle, 6, 0);
                this.inputField = new TextField("Nội dung:", "", 75, 0);
                this.mailname = this.nameField.getString() == null ? "" : this.nameField.getString().trim();
                this.mailtitle = this.titleField.getString() == null ? "" : this.titleField.getString().trim();
                this.mailcontent = this.inputField.getString() == null ? "" : this.inputField.getString().trim();
                this.okCommand = new Command("Gửi", 4, 0);
                this.addCommand = new Command("Thêm tiền", 4, 0);
                this.backCommand = new Command("Hủy", 2, 1);
                this.setTitle("Viết thư");
                this.append(this.nameField);
                this.append(this.titleField);
                this.append(this.inputField);
                this.addCommand(this.okCommand);
                this.addCommand(this.addCommand);
                this.addCommand(this.backCommand);
                this.faceCommand = new Command("Danh sách bạn", 4, 1);
                break;
            }
            case 44: {
                this.setTitle("Kèn hiệu lệnh thế giới");
                this.inputField = new TextField("", this.chatstr, 24, 0);
                this.append(this.inputField);
                this.append("Kênh: thế giới \nmỗi 5s sẽ hiện tin này trong kênh thế giới một lần, tổng cộng 3 lần.");
                this.okCommand = new Command("Gửi", 4, 0);
                this.backCommand = new Command("Hủy", 2, 2);
                this.addCommand(this.okCommand);
                this.addCommand(this.backCommand);
                this.faceCommand = new Command("Thêm biểu cảm", 4, 1);
                this.addCommand(this.faceCommand);
                break;
            }
            case 40: {
                this.nameField = new TextField("Tên:", this.strChatName, 6, 0);
                this.inputField = new TextField("Nội dung:", this.chatstr, 24, 0);
                this.okCommand = new Command("Xác nhận", 4, 0);
                this.backCommand = new Command("Hủy", 2, 10);
                int n = 0;
                if (!ORPMe.ME.strConsortia.equals("")) {
                    n = (byte) (n + 1);
                }
                if (ORPMe.ME.bytTeamRank != 0) {
                    n = (byte) (n + 1);
                }
                String[] stringArray = new String[this.STRING_TALK_CHANNEL.length + n];
                int n2 = 0;
                while (n2 < this.STRING_TALK_CHANNEL.length) {
                    stringArray[n2] = this.STRING_TALK_CHANNEL[n2];
                    n2 = (byte) (n2 + 1);
                }
                n2 = 0;
                if (!ORPMe.ME.strConsortia.equals("")) {
                    stringArray[this.STRING_TALK_CHANNEL.length + n2] = "Bang-người của bang mới thấy được";
                    n2 = (byte) (n2 + 1);
                }
                if (ORPMe.ME.bytTeamRank != 0) {
                    stringArray[this.STRING_TALK_CHANNEL.length + n2] = "Đội-thành viên trong đội đều thấy được";
                    n2 = (byte) (n2 + 1);
                }
                this.CGSelect = new ChoiceGroup("Chọn kênh:", 1, stringArray, null);
                Param.getInstance().getClass();
                if (Param.getInstance().bytChatNoteType == 5) {
                    if (ORPMe.ME.bytTeamRank == 0) {
                        this.CGSelect.setSelectedIndex(1, true);
                    } else if (ORPMe.ME.strConsortia.length() <= 0) {
                        this.CGSelect.setSelectedIndex(4, true);
                    } else {
                        this.CGSelect.setSelectedIndex(5, true);
                    }
                } else {
                    Param.getInstance().getClass();
                    if (Param.getInstance().bytChatNoteType == 4 && ORPMe.ME.strConsortia.length() > 0) {
                        this.CGSelect.setSelectedIndex(4, true);
                    } else {
                        switch (Param.getInstance().bytOldChatChannel) {
                            case 1: {
                                this.CGSelect.setSelectedIndex(2, true);
                                break;
                            }
                            case 0: {
                                this.CGSelect.setSelectedIndex(0, true);
                                break;
                            }
                            case 2: {
                                this.CGSelect.setSelectedIndex(3, true);
                                break;
                            }
                            case 3: {
                                if (ORPMe.ME.bytTeamRank == 0) {
                                    this.CGSelect.setSelectedIndex(1, true);
                                    break;
                                }
                                if (ORPMe.ME.strConsortia.length() <= 0) {
                                    this.CGSelect.setSelectedIndex(4, true);
                                    break;
                                }
                                this.CGSelect.setSelectedIndex(5, true);
                                break;
                            }
                            case 4: {
                                if (ORPMe.ME.strConsortia.length() <= 0) {
                                    this.CGSelect.setSelectedIndex(1, true);
                                    break;
                                }
                                this.CGSelect.setSelectedIndex(4, true);
                                break;
                            }
                            case 10: {
                                this.CGSelect.setSelectedIndex(1, true);
                            }
                        }
                    }
                }
                if (Param.getInstance().bytOldChatChannel == 10) {
                    this.setTitle("Mời trò chuyện - " + this.STRING_TITLE_CHANNAL_NAME[5]);
                } else {
                    this.setTitle("Mời trò chuyện - " + this.STRING_TITLE_CHANNAL_NAME[Param.getInstance().bytOldChatChannel]);
                }
                this.append(this.inputField);
                this.append(this.nameField);
                this.append(this.CGSelect);
                this.addCommand = new Command("Chèn vật phẩm", 4, 2);
                this.addCommand(this.addCommand);
                this.addCommand(this.backCommand);
                this.addCommand(this.okCommand);
                this.faceCommand = new Command("Thêm biểu cảm", 4, 1);
                this.addCommand(this.faceCommand);
                if (Rms.strsMacroChat == null) {
                    break;
                }
                int n3 = 0;
                while (n3 < Rms.strsMacroChat.length / 2) {
                    switch (n3) {
                        case 0: {
                            this.chat1Command = new Command("[" + Rms.strsMacroChat[n3 * 2] + "]", 4, 3);
                            this.addCommand(this.chat1Command);
                            break;
                        }
                        case 1: {
                            this.chat2Command = new Command("[" + Rms.strsMacroChat[n3 * 2] + "]", 4, 4);
                            this.addCommand(this.chat2Command);
                            break;
                        }
                        case 2: {
                            this.chat3Command = new Command("[" + Rms.strsMacroChat[n3 * 2] + "]", 4, 5);
                            this.addCommand(this.chat3Command);
                            break;
                        }
                        case 3: {
                            this.chat4Command = new Command("[" + Rms.strsMacroChat[n3 * 2] + "]", 4, 6);
                            this.addCommand(this.chat4Command);
                            break;
                        }
                        case 4: {
                            this.chat5Command = new Command("[" + Rms.strsMacroChat[n3 * 2] + "]", 4, 7);
                            this.addCommand(this.chat5Command);
                        }
                    }
                    n3 = (byte) (n3 + 1);
                }
                break;
            }
            case 41: {
                this.inputField = new TextField("Nội dung:", this.chatstr, 24, 0);
                this.okCommand = new Command("Xác nhận", 4, 0);
                this.backCommand = new Command("Hủy", 4, 10);
                this.addCommand = new Command("Chèn vật phẩm", 4, 2);
                this.faceCommand = new Command("Thêm biểu cảm", 4, 1);
                this.setTitle("Mời trò chuyện - " + this.strChatName);
                this.append(this.inputField);
                this.addCommand(this.okCommand);
                this.addCommand(this.backCommand);
                this.addCommand(this.faceCommand);
                this.addCommand(this.addCommand);
                break;
            }
            case -48: {
                this.setTitle("Câu nói thường dùng:" + this.strTitle);
                this.nameField = new TextField("Tên (2 đến 4 tự):", this.strTitle, 4, 0);
                this.inputField = new TextField("Nội dung (tối đa 24 từ):", this.strChatName, 24, 0);
                this.append(this.nameField);
                this.append(this.inputField);
                this.okCommand = new Command("Xác nhận", 4, 0);
                this.backCommand = new Command("Hủy", 4, 2);
                this.faceCommand = new Command("Thêm biểu cảm", 4, 1);
                this.addCommand(this.okCommand);
                this.addCommand(this.backCommand);
                this.addCommand(this.faceCommand);
                break;
            }
            case -66: {
                this.inputField = new TextField("Tên nhân vật giới hạn 2-8 ký tự, bao gồm chữ la tinh và số", "", 6, 0);
                this.okCommand = new Command("Xác nhận", 4, 0);
                this.backCommand = new Command("Hủy", 2, 10);
                this.append(this.inputField);
                this.addCommand(this.okCommand);
                this.addCommand(this.backCommand);
                break;
            }
        }
        this.setCommandListener(this);
    }

    private void clearForm() {
        try {
            byte by;
            byte by2 = by = (byte) (this.size() - 1);
            while (by2 >= 0) {
                this.delete(by2);
                by2 = (byte) (by2 - 1);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        try {
            this.removeCommand(this.okCommand);
            this.removeCommand(this.backCommand);
            this.removeCommand(this.faceCommand);
            this.removeCommand(this.changeChannal);
            this.removeCommand(this.addCommand);
            this.removeCommand(this.chat1Command);
            this.removeCommand(this.chat2Command);
            this.removeCommand(this.chat3Command);
            this.removeCommand(this.chat4Command);
            this.removeCommand(this.chat5Command);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void commandAction(Command command, Displayable displayable) {
        block234:
        {
            this.setTicker(null);
            try {
                switch (this.bytFormState) {
                    case -23: {
                        if (command == this.backCommand) {
                            this.backScreen();
                            break;
                        }
                        if (command == this.okCommand) {
                            byte by = (byte) this.nameField.getString().trim().length();
                            if (by <= 0) {
                                this.nameField.setString("");
                                this.addTicker("Biệt danh khác không thể để trống");
                                break;
                            }
                            Param.getInstance().MALL_FOR_NAME = this.nameField.getString().trim();
                            if (DCanvas.getInstance().UMenu == null) {
                                GameControl.getInstance().delUIbase(4);
                                GameControl.getInstance().CreateState((byte) 3);
                            }
                            MenuUI.getInstance().setState((byte) -24, "Nạp cho người khác");
                            this.backScreen();
                        }
                        break;
                    }
                    case -19: {
                        if (command == this.backCommand) {
                            this.backScreen();
                            break;
                        }
                        if (command == this.okCommand) {
                            byte by = (byte) this.mallCardField.getString().trim().length();
                            byte by2 = (byte) this.mallPwdField.getString().trim().length();
                            if (by <= 0 || by2 <= 0) {
                                this.mallCardField.setString("");
                                this.mallPwdField.setString("");
                                this.addTicker("Mã PIN không thể để trống");
                                break;
                            }
                            Param.getInstance().MALL_CARD_NUMBER = this.mallCardField.getString().trim();
                            Param.getInstance().MALL_PASSWORD_NUMBER = this.mallPwdField.getString().trim();
                            if (DCanvas.getInstance().UMenu == null) {
                                GameControl.getInstance().CreateState((byte) 3);
                            }
                            MenuUI.getInstance().setState((byte) -20, "Xác nhận nạp tiền");
                            this.backScreen();
                        }
                        break;
                    }
                    case -101: {
                        if (command == this.backCommand) {
                            this.backScreen();
                            break;
                        }
                        if (command == this.okCommand) {
                            byte by = (byte) this.nameField.getString().trim().length();
                            if (by <= 0) {
                                this.nameField.setString("");
                                this.addTicker("Tên tiệm không thể bỏ trống");
                                break;
                            }
                            MenuUI.getInstance().strShopName = this.nameField.getString().trim();
                            this.backScreen();
                        }
                        break;
                    }
                    case 44: {
                        this.intCurPlace = this.inputField.getCaretPosition();
                        if (command == this.backCommand) {
                            this.backScreen();
                            break;
                        }
                        if (command == this.faceCommand) {
                            this.chatstr = this.inputField.getString();
                            GameControl.getInstance().setScreen(DCanvas.getInstance());
                            GameControl.getInstance().CreateState((byte) 11);
                            CueUI.getInstance().setFullFace();
                            break;
                        }
                        if (command == this.okCommand) {
                            byte by = (byte) this.inputField.getString().trim().length();
                            if (by < 1) {
                                this.addTicker("Không được bỏ trống nội dung");
                                break;
                            }
                            NetSend.getInstance().sendClarion(this.inputField.getString().trim());
                            this.backScreen();
                        }
                        break;
                    }
                    case 19: {
                        if (command == this.backCommand) {
                            this.backScreen();
                            break;
                        }
                        if (command == this.okCommand) {
                            if (this.nameField.getString().indexOf(" ") != -1 || this.nameField.getString().indexOf("　") != -1) {
                                this.addTicker("Tên không thể bỏ trống");
                                break;
                            }
                            byte by = (byte) this.nameField.getString().trim().length();
                            if (by <= 1) {
                                this.nameField.setString("");
                                this.addTicker("Tên không quá 2 ký tự");
                                break;
                            }
                            if (Macro.bytGameType == 1 && this.nameField.getString().equals(ORPMe.ME.strNickName)) {
                                this.nameField.setString("");
                                this.addTicker("Tên không thể là bản thân");
                                break;
                            }
                            if (by <= 6) {
                                String string;
                                LandUI.getInstance().strName = string = this.nameField.getString().trim();
                                LandUI.getInstance().bytReelMove = 1;
                                this.backScreen();
                                LandUI.getInstance().updataInfo();
                            }
                        }
                        break;
                    }
                    case 20: {
                        if (command == this.backCommand) {
                            this.backScreen();
                            break;
                        }
                        if (command == this.okCommand) {
                            if (this.nameField.getString().indexOf(" ") != -1 || this.nameField.getString().indexOf("　") != -1) {
                                this.addTicker("Tên không thể bỏ trống");
                                break;
                            }
                            byte by = (byte) this.nameField.getString().trim().length();
                            if (by <= 1) {
                                this.nameField.setString("");
                                this.addTicker("Tên không quá 2 ký tự");
                                break;
                            }
                            if (Macro.bytGameType == 1 && this.nameField.getString().equals(ORPMe.ME.strNickName)) {
                                this.nameField.setString("");
                                this.addTicker("Tên không thể là bản thân");
                                break;
                            }
                            if (by <= 6) {
                                String string = this.nameField.getString().trim();
                                if (DCanvas.getInstance().UMenu != null) {
                                    switch (MenuUI.getInstance().getState()) {
                                        case 34: {
                                            NetSend.getInstance().sendConsortiaAdd(string);
                                            break;
                                        }
                                        case -31: {
                                            NetSend.getInstance().sendHailFellow((byte) 2, (byte) 1, string);
                                            break;
                                        }
                                        case -32: {
                                            NetSend.getInstance().sendHailFellow((byte) 2, (byte) 2, string);
                                            break;
                                        }
                                        case -33: {
                                            NetSend.getInstance().sendHailFellow((byte) 2, (byte) 3, string);
                                            break;
                                        }
                                        case 35: {
                                            NetSend.getInstance().sendTeam_add(string);
                                            break;
                                        }
                                        case -50: {
                                            NetSend.getInstance().sendDddPartner((byte) 1, string);
                                            break;
                                        }
                                    }
                                }
                                this.backScreen();
                            }
                        }
                        break;
                    }
                    case 21: {
                        if (command == this.backCommand) {
                            int n = Param.getInstance().vMenuMemory.size();
                            Param.getInstance().vMenuMemory.removeElementAt(n - 1);
                            this.backScreen();
                            break;
                        }
                        if (command == this.okCommand) {
                            boolean bl = true;
                            if (Param.getInstance().bytArrayNpcInputMax != null && Param.getInstance().bytArrayNpcInputMax.length > 0) {
                                int n;
                                short s;
                                int n2 = Param.getInstance().bytArrayNpcInputMax.length;
                                if (n2 > 0) {
                                    Param.getInstance().strArrayNpcInputName = new String[n2];
                                    s = 0;
                                    while (s < n2) {
                                        n = (byte) this.recvInputField[s].getString().trim().length();
                                        if (n <= 0 || n > Param.getInstance().bytArrayNpcInputMax[s]) {
                                            bl = false;
                                            this.addTicker("Không được bỏ trống nội dung");
                                            break;
                                        }
                                        Param.getInstance().strArrayNpcInputName[s] = this.recvInputField[s].getString().trim();
                                        s = (short) (s + 1);
                                    }
                                }
                                if (bl) {
                                    s = (short) Param.getInstance().intSelectId;
                                    n = Param.getInstance().vMenuMemory.size();
                                    MenuObject menuObject = (MenuObject) Param.getInstance().vMenuMemory.elementAt(n - 1);
                                    NetSend.getInstance().sendNpcRecvActive(s, menuObject.bytStep, menuObject.intOptionId, Param.getInstance().strArrayNpcInputName);
                                    Param.getInstance().vMenuMemory.removeElementAt(n - 1);
                                    this.backScreen();
                                }
                            }
                        }
                        break;
                    }
                    case -102: {
                        if (command == this.backCommand) {
                            if (MenuUI.getInstance().blnNpcMailSendGood) {
                                MenuUI.getInstance().setBackOff();
                                Param.getInstance().vMenuMemory = null;
                                this.backScreen();
                                String string = Param.getInstance().oSelectNpc.getNpcName();
                                MenuUI.getInstance().setNpcTalk(string, Param.getInstance().oSelectNpc.strTALK);
                                DCanvas.getInstance().setNetLoad(true);
                                if (Macro.BLN_ACCERATE_NPC_TASK) {
                                    NetSend.getInstance().sendNpcOptionFake(Param.getInstance().oSelectNpc.intUserId);
                                } else {
                                    NetSend.getInstance().sendNpcOption(Param.getInstance().oSelectNpc.intUserId);
                                }
                                MenuUI.getInstance().setState((byte) 1, "Quản lý bưu kiện");
                                MenuUI.getInstance().blnNpcMailSendGood = false;
                                break;
                            }
                            MenuUI.getInstance().setBackSystem();
                            break;
                        }
                        if (command == this.okCommand) {
                            if (this.nameField.getString() == null) {
                                this.addTicker("Tên không thể là bản thân");
                                break;
                            }
                            if (this.nameField.getString().trim().indexOf(" ") != -1 || this.nameField.getString().trim().indexOf("　") != -1) {
                                this.addTicker("Nội dung không thể bỏ trống");
                                break;
                            }
                            byte by = (byte) this.nameField.getString().trim().length();
                            if (by <= Param.getInstance().bytNpcInputMin - 1) {
                                this.nameField.setString("");
                                this.addTicker("Nhập ít nhất" + Param.getInstance().bytNpcInputMin + " kí tự");
                                break;
                            }
                            if (this.nameField.getString().equals(ORPMe.ME.strNickName)) {
                                this.nameField.setString("");
                                this.addTicker("Tên không thể là bản thân");
                                break;
                            }
                            if (by <= Param.getInstance().bytNpcInputMax) {
                                String string = this.nameField.getString().trim();
                                String string2 = "";
                                if (this.titleField.getString() != null) {
                                    string2 = this.titleField.getString().trim();
                                }
                                String string3 = "";
                                if (this.inputField.getString() != null) {
                                    string3 = this.inputField.getString().trim();
                                }
                                if (Param.getInstance().oSelectNpc != null) {
                                    byte by3 = (byte) Param.getInstance().vMenuMemory.size();
                                    MenuObject menuObject = (MenuObject) Param.getInstance().vMenuMemory.elementAt(by3 - 1);
                                    short s = 0;
                                    s = menuObject.bytType == 10 || menuObject.bytType == 11 ? MenuUI.getInstance().getOneMove() : (short) Param.getInstance().bytHandleMove;
                                    if (menuObject != null && menuObject.vNextMenu != null && menuObject.vNextMenu[s] != null && Param.getInstance().bytPopRectStep + 1 < menuObject.vNextMenu[s].length) {
                                        Param.getInstance().strInputName = string;
                                        String string4 = menuObject.vNextMenu[s][Param.getInstance().bytPopRectStep].strInfo;
                                        byte by4 = menuObject.vNextMenu[s][Param.getInstance().bytPopRectStep].bytType;
                                        MenuUI.getInstance().setTwoRect(string4, 0, s, by4, 0);
                                        Param.getInstance().bytPopRectStep = (byte) (Param.getInstance().bytPopRectStep + 1);
                                    } else {
                                        int n = by3 == 1 ? menuObject.intsOptionId[s] : menuObject.intOptionId;
                                        byte by5 = menuObject.bytStep;
                                        int n3 = Param.getInstance().oSelectNpc.intUserId;
                                        if (Param.getInstance().hPackage != null) {
                                            PackageObject packageObject = (PackageObject) Param.getInstance().hPackage.get(new Integer(Param.getInstance().personalPackage.getCellBoxIndex()));
                                            if (this.titleField.getString() == null) {
                                                string2 = packageObject.strName;
                                            }
                                            NetSend.getInstance().sendNpcEail(n3, by5, n, (byte) 0, Param.getInstance().personalPackage.getCellBoxIndex(), packageObject.intId, Param.getInstance().intNpcNum == 0 ? 1 : Param.getInstance().intNpcNum, string, string2, string3);
                                        } else if (menuObject.bytType == 10) {
                                            NetSend.getInstance().sendNpcConsortia(n3, by5, n, string);
                                        } else if (Param.getInstance().intNpcNum != 0) {
                                            if (this.titleField.getString() == null) {
                                                string2 = "Tiền";
                                            }
                                            NetSend.getInstance().sendNpcEailM(n3, by5, n, (byte) 3, Param.getInstance().intNpcNum, string, string2, string3);
                                        } else {
                                            NetSend.getInstance().sendNpcPaiM(n3, by5, n, (byte) MenuUI.getInstance().getOneMove(), string);
                                        }
                                        DCanvas.getInstance().setNetLoad(true);
                                    }
                                }
                                MenuUI.getInstance().blnNpcMailSendGood = false;
                                MenuUI.getInstance().setBackSystem();
                                this.backScreen();
                            }
                        } else if (command == this.addCommand) {
                            Param.getInstance().bytAddGoodIndex = 1;
                            MenuUI.getInstance().tabStyleInstance.resetTabStyle((byte) 0);
                            int n = Param.getInstance().oSelectNpc.intUserId;
                            NetSend.getInstance().sendNpcOneOption(n, (byte) 1, 800001);
                            MenuUI.getInstance().blnNpcMailSendGood = true;
                            this.backTempScreen();
                        }
                        break;
                    }
                    case 22: {
                        if (command == this.backCommand) {
                            this.backScreen();
                            if (Param.getInstance().EnterGame) {
                                if (Rms.strRmsName != null) {
                                    DCanvas.getInstance().setNetLoad(true);
                                    NetSend.getInstance().sendLand(Rms.strRmsName, Rms.strRmsPassword, (byte) 2);
                                    NetSend.getInstance().sendRequestRes(IResConfig.RES_CONFIG.length);
                                }
                                Param.getInstance().EnterGame = false;
                            }
                            if (this.ForamState == 0) {
                                LandUI.getInstance().shtLandRoll = (short) 3;
                            }
                            break;
                        }
                        if (command != this.okCommand) {
                            break;
                        }
                        switch (this.ForamState) {
                            case 0: {
                                if (this.InputAccount.getString().trim().indexOf(" ") != -1 || this.InputAccount.getString().trim().indexOf("　") != -1 || this.InputPassword.getString().trim().indexOf(" ") != -1 || this.InputPassword.getString().trim().indexOf("　") != -1) {
                                    this.addTicker("Tài khoản hoặc mật khẩu không thể bỏ trống");
                                    break;
                                }
                                byte by = (byte) this.InputAccount.getString().trim().length();
                                byte by6 = (byte) this.InputPassword.getString().trim().length();
                                String string = this.InputAccount.getString().trim();
                                String string5 = this.InputPassword.getString().trim();
                                boolean bl = true;
                                if (by < 6 || 11 < by || by6 < 6 || 11 < by6) {
                                    bl = false;
                                    this.addTicker("Tài khoản và mật khẩu từ 6-11 ký tự");
                                    break;
                                }
                                if (Common.isUnicodeExist(string)) {
                                    bl = false;
                                    this.addTicker("Tài khoản chỉ có thể bao gồm chữ và số");
                                    break;
                                }
                                if (Common.isUnicodeExist(string5)) {
                                    bl = false;
                                    this.addTicker("Mật khẩu chỉ có thể bao gồm chữ và số");
                                    break;
                                }
                                if (bl) {
                                    Param.getInstance().Forma_ccount = string;
                                    Param.getInstance().Forma_password = string5;
                                    LandUI.getInstance().strLandAccounts = string;
                                    LandUI.getInstance().strLandPassword = string5;
                                    this.backScreen();
                                    LandUI.getInstance().shtLandRoll = (short) 3;
                                    break;
                                }
                                break block234;
                            }
                            case 1: {
                                if (this.InputAccount.getString().trim().indexOf(" ") != -1 || this.InputAccount.getString().trim().indexOf("　") != -1 || this.InputPassword.getString().trim().indexOf(" ") != -1 || this.InputPassword.getString().trim().indexOf("　") != -1) {
                                    this.addTicker("Tài khoản hoặc mật khẩu không thể bỏ trống");
                                    break;
                                }
                                byte by = (byte) this.InputAccount.getString().trim().length();
                                byte by7 = (byte) this.InputPassword.getString().trim().length();
                                byte by8 = (byte) this.MakeSurePassword.getString().trim().length();
                                String string = this.InputAccount.getString().trim();
                                String string6 = this.InputPassword.getString().trim();
                                String string7 = this.MakeSurePassword.getString().trim();
                                boolean bl = true;
                                if (by < 6 || 11 < by || by7 < 6 || 11 < by7) {
                                    bl = false;
                                    this.addTicker("Tài khoản và mật khẩu từ 6-11 ký tự");
                                    break;
                                }
                                if (Common.isUnicodeExist(string)) {
                                    bl = false;
                                    this.addTicker("Tài khoản chỉ có thể bao gồm chữ và số");
                                    break;
                                }
                                if (Common.isUnicodeExist(string6) || Common.isUnicodeExist(string7)) {
                                    bl = false;
                                    this.addTicker("Mật khẩu chỉ có thể bao gồm chữ và số");
                                    break;
                                }
                                if (by7 != by8 || !string6.equals(string7)) {
                                    bl = false;
                                    this.addTicker("Mật khẩu không khớp!");
                                    break;
                                }
                                if (bl) {
                                    LandUI.getInstance().strLandAccounts = string;
                                    LandUI.getInstance().strLandPassword = string6;
                                    this.backScreen();
                                    DCanvas.getInstance().setNetLoad(true);
                                    NetSend.getInstance().sendClientStep((byte) 2);
                                    NetSend.getInstance().sendRegister(LandUI.getInstance().strLandAccounts, LandUI.getInstance().strLandPassword, "null");
                                    break;
                                }
                                break block234;
                            }
                            case 2: {
                                if (this.InputAccount.getString().trim().indexOf(" ") != -1 || this.InputAccount.getString().trim().indexOf("　") != -1 || this.InputPassword.getString().trim().indexOf(" ") != -1 || this.InputPassword.getString().trim().indexOf("　") != -1) {
                                    this.addTicker("Tài khoản hoặc mật khẩu không thể bỏ trống");
                                    break;
                                }
                                byte by = (byte) this.InputAccount.getString().trim().length();
                                byte by9 = (byte) this.InputPassword.getString().trim().length();
                                byte by10 = (byte) this.InputTelNumber.getString().trim().length();
                                byte by11 = (byte) this.MakeSureTelNumber.getString().trim().length();
                                String string = this.InputAccount.getString().trim();
                                String string8 = this.InputPassword.getString().trim();
                                String string9 = this.InputTelNumber.getString().trim();
                                String string10 = this.MakeSureTelNumber.getString().trim();
                                boolean bl = true;
                                if (by < 6 || 11 < by || by9 < 6 || 11 < by9) {
                                    bl = false;
                                    this.addTicker("Tài khoản và mật khẩu từ 6-11 ký tự");
                                    break;
                                }
                                if (Common.isUnicodeExist(string)) {
                                    bl = false;
                                    this.addTicker("Tài khoản chỉ có thể bao gồm chữ và số");
                                    break;
                                }
                                if (Common.isUnicodeExist(string8)) {
                                    bl = false;
                                    this.addTicker("Mật khẩu chỉ có thể bao gồm chữ và số");
                                    break;
                                }
                                if (!string8.equals(LandUI.getInstance().strLandPassword)) {
                                    bl = false;
                                    this.addTicker("Mật khẩu không đúng");
                                    break;
                                }
                                if (by10 != 11 || by11 != 11) {
                                    bl = false;
                                    this.addTicker("Số điện thoại không chính xác");
                                    break;
                                }
                                if (Common.isUnicodeExist(string9) || Common.isUnicodeExist(string10)) {
                                    bl = false;
                                    this.addTicker("Số điện thoại không chính xác");
                                    break;
                                }
                                if (by10 != by11 || !string9.equals(string10)) {
                                    bl = false;
                                    this.addTicker("Số điện thoại không khớp!");
                                    break;
                                }
                                if (bl) {
                                    this.backScreen();
                                    DCanvas.getInstance().setNetLoad(true);
                                    NetSend.getInstance().sendBindingPhone(string, string8, string10);
                                    if (Param.getInstance().EnterGame) {
                                        if (Rms.strRmsName != null) {
                                            DCanvas.getInstance().setNetLoad(true);
                                            NetSend.getInstance().sendLand(Rms.strRmsName, Rms.strRmsPassword, (byte) 2);
                                            NetSend.getInstance().sendRequestRes(IResConfig.RES_CONFIG.length);
                                        }
                                        Param.getInstance().EnterGame = false;
                                        break;
                                    }
                                }
                                break block234;
                            }
                            case 3: {
                                if (this.InputAccount.getString().trim().indexOf(" ") != -1 || this.InputAccount.getString().trim().indexOf("　") != -1) {
                                    this.addTicker("Tài khoản hoặc mật khẩu không thể bỏ trống");
                                    break;
                                }
                                byte by = (byte) this.InputAccount.getString().trim().length();
                                String string = this.InputAccount.getString().trim();
                                boolean bl = true;
                                if (by < 6 || by > 11) {
                                    bl = false;
                                    this.addTicker("Tài khoản từ 6-11 ký tự");
                                    break;
                                }
                                if (Common.isUnicodeExist(string)) {
                                    bl = false;
                                    this.addTicker("Tài khoản chỉ có thể bao gồm chữ và số");
                                    break;
                                }
                                if (bl) {
                                    this.backScreen();
                                    DCanvas.getInstance().setNetLoad(true);
                                    NetSend.getInstance().sendRetrievePassword(string);
                                    break;
                                }
                                break block234;
                            }
                            case 4: {
                                if (this.InputAccount.getString().trim().indexOf(" ") != -1 || this.InputAccount.getString().trim().indexOf("　") != -1 || this.InputPassword.getString().trim().indexOf(" ") != -1 || this.InputPassword.getString().trim().indexOf("　") != -1 || this.NewPassword.getString().trim().indexOf(" ") != -1 || this.NewPassword.getString().trim().indexOf("　") != -1 || this.MakeSureNewPassword.getString().trim().indexOf(" ") != -1 || this.MakeSureNewPassword.getString().trim().indexOf("　") != -1) {
                                    this.addTicker("Tài khoản hoặc mật khẩu không thể bỏ trống");
                                    break;
                                }
                                byte by = (byte) this.InputAccount.getString().trim().length();
                                byte by12 = (byte) this.InputPassword.getString().trim().length();
                                byte by13 = (byte) this.NewPassword.getString().trim().length();
                                byte by14 = (byte) this.MakeSureNewPassword.getString().trim().length();
                                String string = this.InputAccount.getString().trim();
                                String string11 = this.InputPassword.getString().trim();
                                String string12 = this.NewPassword.getString().trim();
                                String string13 = this.MakeSureNewPassword.getString().trim();
                                boolean bl = true;
                                if (by < 6 || 11 < by || by12 < 6 || 11 < by12 || by13 < 6 || 11 < by13 || by14 < 6 || 11 < by14) {
                                    bl = false;
                                    this.addTicker("Tài khoản và mật khẩu từ 6-11 ký tự");
                                    break;
                                }
                                if (Common.isUnicodeExist(string)) {
                                    bl = false;
                                    this.addTicker("Tài khoản chỉ có thể bao gồm chữ và số");
                                    break;
                                }
                                if (Common.isUnicodeExist(string11)) {
                                    bl = false;
                                    this.addTicker("Mật khẩu chỉ có thể bao gồm chữ và số");
                                    break;
                                }
                                if (!string11.equals(LandUI.getInstance().strLandPassword)) {
                                    bl = false;
                                    this.addTicker("Mật khẩu không đúng");
                                    break;
                                }
                                if (Common.isUnicodeExist(string12) || Common.isUnicodeExist(string13)) {
                                    bl = false;
                                    this.addTicker("Mật khẩu chỉ có thể bao gồm chữ và số");
                                    break;
                                }
                                if (by13 != by14 || !string12.equals(string13)) {
                                    bl = false;
                                    this.addTicker("Mật khẩu không khớp!");
                                    break;
                                }
                                if (!bl) {
                                    break block234;
                                }
                                this.backScreen();
                                DCanvas.getInstance().setNetLoad(true);
                                NetSend.getInstance().sendResetPassWord(string, string11, string13);
                            }
                            default: {
                                break;
                            }
                        }
                        break;
                    }
                    case -100: {
                        if (command == this.backCommand) {
                            this.backScreen();
                            break;
                        }
                        if (command == this.okCommand) {
                            if (this.nameField.getString().indexOf(" ") != -1 || this.nameField.getString().indexOf("　") != -1) {
                                this.addTicker("Nội dung không thể bỏ trống");
                                break;
                            }
                            byte by = (byte) this.nameField.getString().trim().length();
                            if (by <= Param.getInstance().bytNpcInputMin - 1) {
                                this.nameField.setString("");
                                this.addTicker("Nhập ít nhất" + Param.getInstance().bytNpcInputMin + " kí tự");
                                break;
                            }
                            if (this.nameField.getString().equals(ORPMe.ME.strNickName)) {
                                this.nameField.setString("");
                                this.addTicker("Tên không thể là bản thân");
                                break;
                            }
                            if (by <= Param.getInstance().bytNpcInputMax) {
                                String string = this.nameField.getString().trim();
                                if (Param.getInstance().oSelectNpc != null) {
                                    byte by15 = (byte) Param.getInstance().vMenuMemory.size();
                                    MenuObject menuObject = (MenuObject) Param.getInstance().vMenuMemory.elementAt(by15 - 1);
                                    short s = 0;
                                    s = menuObject.bytType == 10 || menuObject.bytType == 11 ? MenuUI.getInstance().getOneMove() : (short) Param.getInstance().bytHandleMove;
                                    if (menuObject != null && menuObject.vNextMenu != null && menuObject.vNextMenu[s] != null && Param.getInstance().bytPopRectStep + 1 < menuObject.vNextMenu[s].length) {
                                        Param.getInstance().strInputName = string;
                                        String string14 = menuObject.vNextMenu[s][Param.getInstance().bytPopRectStep].strInfo;
                                        byte by16 = menuObject.vNextMenu[s][Param.getInstance().bytPopRectStep].bytType;
                                        MenuUI.getInstance().setTwoRect(string14, 0, s, by16, 0);
                                        Param.getInstance().bytPopRectStep = (byte) (Param.getInstance().bytPopRectStep + 1);
                                    } else {
                                        int n = by15 == 1 ? menuObject.intsOptionId[s] : menuObject.intOptionId;
                                        byte by17 = menuObject.bytStep;
                                        int n4 = Param.getInstance().oSelectNpc.intUserId;
                                        if (Param.getInstance().hPackage != null) {
                                            PackageObject packageObject = (PackageObject) Param.getInstance().hPackage.get(new Integer(MenuUI.getInstance().getPropRectMove()));
                                            NetSend.getInstance().sendNpcEail(n4, by17, n, (byte) 0, MenuUI.getInstance().getPropRectMove(), packageObject.intId, Param.getInstance().intNpcNum == 0 ? 1 : Param.getInstance().intNpcNum, string, "", "");
                                        } else if (menuObject.bytType == 10) {
                                            NetSend.getInstance().sendNpcConsortia(n4, by17, n, string);
                                        } else if (Param.getInstance().intNpcNum != 0) {
                                            NetSend.getInstance().sendNpcEailM(n4, by17, n, (byte) MenuUI.getInstance().getOneMove(), Param.getInstance().intNpcNum, string, "", "");
                                        } else {
                                            NetSend.getInstance().sendNpcPaiM(n4, by17, n, (byte) MenuUI.getInstance().getOneMove(), string);
                                        }
                                        DCanvas.getInstance().setNetLoad(true);
                                    }
                                }
                                this.backScreen();
                            }
                        }
                        break;
                    }
                    case -37: {
                        if (command == this.backCommand) {
                            this.backScreen();
                            break;
                        }
                        if (command == this.okCommand) {
                            if (this.nameField.getString().trim().length() <= 1) {
                                this.nameField.setString("");
                                this.addTicker("Tên không quá 2 ký tự");
                                break;
                            }
                            if (this.titleField.getString().trim().length() < 1) {
                                this.addTicker("Không được bỏ trống chủ đề");
                                break;
                            }
                            if (this.inputField.getString().trim().length() < 1) {
                                this.addTicker("Không được bỏ trống nội dung");
                                break;
                            }
                            NetSend.getInstance().sendMailNew(this.nameField.getString(), this.titleField.getString(), this.inputField.getString());
                            Param.getInstance().bytAddGoodIndex = 0;
                            MenuUI.getInstance().setBackSystem();
                            this.backScreen();
                        }
                        break;
                    }
                    case -42: {
                        if (command == this.backCommand) {
                            Param.getInstance().vMenuMemory = null;
                            this.backScreen();
                            String string = Param.getInstance().oSelectNpc.getNpcName();
                            MenuUI.getInstance().setNpcTalk(string, Param.getInstance().oSelectNpc.strTALK);
                            if (Macro.BLN_ACCERATE_NPC_TASK) {
                                NetSend.getInstance().sendNpcOptionFake(Param.getInstance().oSelectNpc.intUserId);
                            } else {
                                NetSend.getInstance().sendNpcOption(Param.getInstance().oSelectNpc.intUserId);
                            }
                            MenuUI.getInstance().setState((byte) 1, "Quản lý bưu kiện");
                            MenuUI.getInstance().blnNpcMailSendGood = false;
                            break;
                        }
                        if (command == this.okCommand) {
                            if (this.nameField.getString() == null) {
                                this.addTicker("Tên không thể là bản thân");
                                break;
                            }
                            if (this.nameField.getString() != null && this.nameField.getString().trim().length() <= 1) {
                                this.nameField.setString("");
                                this.addTicker("Tên không quá 2 ký tự");
                                break;
                            }
                            if (this.titleField.getString() != null && this.titleField.getString().trim().length() < 1) {
                                this.addTicker("Không được bỏ trống chủ đề");
                                break;
                            }
                            if (this.inputField.getString() != null && this.inputField.getString().trim().length() < 1) {
                                this.addTicker("Không được bỏ trống nội dung");
                                break;
                            }
                            byte by = (byte) Param.getInstance().vMenuMemory.size();
                            MenuObject menuObject = (MenuObject) Param.getInstance().vMenuMemory.elementAt(by - 1);
                            short s = 0;
                            s = menuObject.bytType == 10 || menuObject.bytType == 11 ? MenuUI.getInstance().getOneMove() : (short) Param.getInstance().bytHandleMove;
                            int n = by == 1 ? menuObject.intsOptionId[s] : menuObject.intOptionId;
                            int n5 = Param.getInstance().oSelectNpc.intUserId;
                            NetSend.getInstance().sendNpcEailText(n5, (byte) 20, n, (byte) 5, this.nameField.getString(), this.titleField.getString(), this.inputField.getString());
                            Param.getInstance().bytAddGoodIndex = 0;
                            MenuUI.getInstance().setBackSystem();
                            this.backScreen();
                            MenuUI.getInstance().blnNpcMailSendGood = false;
                            break;
                        }
                        if (command == this.addCommand) {
                            Param.getInstance().bytAddGoodIndex = 0;
                            MenuUI menuUI = MenuUI.getInstance();
                            MenuUI.getInstance().getClass();
                            menuUI.bytMailSubtabIndex = 0;
                            MenuUI.getInstance().tabStyleInstance.resetTabStyle(MenuUI.getInstance().bytMailSubtabIndex);
                            int n = Param.getInstance().oSelectNpc.intUserId;
                            NetSend.getInstance().sendNpcOneOption(n, (byte) 1, 800001);
                            MenuUI.getInstance().blnNpcMailSendGood = true;
                            this.backTempScreen();
                        }
                        break;
                    }
                    case 40: {
                        this.bytChatType = (byte) this.CGSelect.getSelectedIndex();
                        this.intCurPlace = this.inputField.getCaretPosition();
                        if (command == this.addCommand) {
                            this.chatstr = this.inputField.getString();
                            Param.getInstance().bytPropBagType = (byte) 2;
                            GameControl.getInstance().setScreen(DCanvas.getInstance());
                            GameControl.getInstance().CreateState((byte) 3);
                            GameUI.getInstance().setNextMenu((byte) 10, "Trang bị");
                            DCanvas.getInstance().setNetLoad(true);
                            NetSend.getInstance().sendFrameEquip((byte) 1, (byte) 0, 0);
                            DCanvas.getInstance().setNetLoad(true);
                            NetSend.getInstance().sendFrameEquip((byte) 1, (byte) 0, 0);
                            break;
                        }
                        if (command == this.backCommand) {
                            this.backScreen();
                            break;
                        }
                        if (command == this.faceCommand) {
                            this.chatstr = this.inputField.getString();
                            GameControl.getInstance().setScreen(DCanvas.getInstance());
                            GameControl.getInstance().CreateState((byte) 11);
                            CueUI.getInstance().setFullFace();
                            break;
                        }
                        if (command == this.okCommand) {
                            String string = this.nameField.getString();
                            if (string != null && string.length() < 1 && this.bytChatType == 0) {
                                this.addTicker("Tên không quá 2 ký tự");
                                if (this.size() != 3) {
                                    this.delete(3);
                                }
                                return;
                            }
                            if (string != null && this.bytChatType == 0 && string.equals(ORPMe.ME.strNickName)) {
                                this.addTicker("Tên không thể là bản thân");
                                return;
                            }
                            String string15 = this.inputField.getString();
                            if (string15 == null) {
                                this.addTicker("Không được bỏ trống nội dung");
                                if (this.size() == 3) {
                                    return;
                                }
                                this.delete(3);
                                return;
                            }
                            int n = 0;
                            String string16 = this.CGSelect.getString(this.bytChatType);
                            if (string16.equals(this.STRING_TALK_CHANNEL[0])) {
                                n = 0;
                            } else if (string16.equals(this.STRING_TALK_CHANNEL[2])) {
                                n = 1;
                            } else if (string16.equals(this.STRING_TALK_CHANNEL[3])) {
                                n = 2;
                            } else if (string16.equals("Bang-người của bang mới thấy được")) {
                                n = 4;
                            } else if (string16.equals("Đội-thành viên trong đội đều thấy được")) {
                                n = 3;
                            } else if (string16.equals(this.STRING_TALK_CHANNEL[1])) {
                                n = 10;
                            }
                            String string17 = this.siftChatContent(string15);
                            if (string17.length() <= 0) {
                                this.addTicker("Nội dung không hợp lệ");
                                this.inputField.setString("");
                                break;
                            }
                            Param.getInstance().bytOldChatChannel = (byte) n;
                            NetSend.getInstance().sendChat(string17, (byte) n, string);
                            this.backScreen();
                            break;
                        }
                        this.chatstr = this.inputField.getString();
                        String string = this.chatstr.substring(0, this.intCurPlace);
                        String string18 = this.chatstr.substring(this.intCurPlace);
                        if (this.chat1Command != null && command == this.chat1Command) {
                            String string19 = String.valueOf(string) + Rms.strsMacroChat[1] + string18;
                            if (string19.length() > 24) {
                                string19 = string19.substring(0, 24);
                            }
                            this.inputField.setString(string19);
                            break;
                        }
                        if (this.chat2Command != null && command == this.chat2Command) {
                            String string20 = String.valueOf(string) + Rms.strsMacroChat[3] + string18;
                            if (string20.length() > 24) {
                                string20 = string20.substring(0, 24);
                            }
                            this.inputField.setString(string20);
                            break;
                        }
                        if (this.chat3Command != null && command == this.chat3Command) {
                            String string21 = String.valueOf(string) + Rms.strsMacroChat[5] + string18;
                            if (string21.length() > 24) {
                                string21 = string21.substring(0, 24);
                            }
                            this.inputField.setString(string21);
                            break;
                        }
                        if (this.chat4Command != null && command == this.chat4Command) {
                            String string22 = String.valueOf(string) + Rms.strsMacroChat[7] + string18;
                            if (string22.length() > 24) {
                                string22 = string22.substring(0, 24);
                            }
                            this.inputField.setString(string22);
                            break;
                        }
                        if (this.chat5Command != null && command == this.chat5Command) {
                            String string23 = String.valueOf(string) + Rms.strsMacroChat[9] + string18;
                            if (string23.length() > 24) {
                                string23 = string23.substring(0, 24);
                            }
                            this.inputField.setString(string23);
                        }
                        break;
                    }
                    case 41: {
                        this.intCurPlace = this.inputField.getCaretPosition();
                        if (command == this.addCommand) {
                            this.chatstr = this.inputField.getString();
                            Param.getInstance().bytPropBagType = (byte) 2;
                            GameControl.getInstance().setScreen(DCanvas.getInstance());
                            GameControl.getInstance().CreateState((byte) 3);
                            MenuUI.getInstance().setState((byte) 10, "Trang bị");
                        }
                        if (command == this.backCommand) {
                            this.backScreen();
                            break;
                        }
                        if (command == this.faceCommand) {
                            this.chatstr = this.inputField.getString();
                            GameControl.getInstance().setScreen(DCanvas.getInstance());
                            GameControl.getInstance().CreateState((byte) 11);
                            CueUI.getInstance().setFullFace();
                            break;
                        }
                        if (command == this.okCommand) {
                            String string = this.inputField.getString().trim();
                            if (string.length() < 1) {
                                this.addTicker("Không được bỏ trống nội dung");
                                break;
                            }
                            String string24 = this.siftChatContent(string);
                            if (string24.length() <= 0) {
                                this.addTicker("Nội dung bạn nhập không hợp lệ");
                                this.inputField.setString("");
                                break;
                            }
                            NetSend.getInstance().sendChat(string24, (byte) 0, this.strChatName);
                            this.backScreen();
                        }
                        break;
                    }
                    case -48: {
                        this.intCurPlace = this.inputField.getCaretPosition();
                        if (command == this.backCommand) {
                            this.backScreen();
                            break;
                        }
                        if (command == this.faceCommand) {
                            this.chatstr = this.inputField.getString();
                            GameControl.getInstance().setScreen(DCanvas.getInstance());
                            GameControl.getInstance().CreateState((byte) 11);
                            CueUI.getInstance().setFullFace();
                            break;
                        }
                        if (command == this.okCommand) {
                            String string = this.nameField.getString().trim();
                            String string25 = this.inputField.getString().trim();
                            if (string.length() < 1) {
                                MenuUI.getInstance().setMacroChat("Câu nói thường dùng (chưa sử dụng)", "");
                                this.backScreen();
                                break;
                            }
                            if (string.length() < 2) {
                                this.addTicker("Tên từ 2-4 ký tự");
                                return;
                            }
                            if (string25.length() < 1) {
                                this.addTicker("Không được bỏ trống nội dung");
                                return;
                            }
                            MenuUI.getInstance().setMacroChat(string, string25);
                            this.backScreen();
                        }
                        break;
                    }
                    case -70: {
                        if (command == this.backCommand) {
                            this.backScreen();
                            break;
                        }
                        if (command == this.okCommand) {
                            if (this.inputField.getString().trim().length() < 1) {
                                this.addTicker("Không được bỏ trống nội dung");
                                break;
                            }
                            NetSend.getInstance().sendGM((byte) (this.CGSelect.getSelectedIndex() + 1), this.inputField.getString());
                            this.backScreen();
                        }
                        break;
                    }
                    case -71: {
                        if (command == this.backCommand) {
                            this.backScreen();
                            break;
                        }
                        if (command == this.okCommand) {
                            if (this.inputField.getString().trim().length() < 1) {
                                this.addTicker("Không được bỏ trống nội dung");
                                break;
                            }
                            NetSend.getInstance().sendGM_CHAT(this.strChatName, this.inputField.getString().trim());
                            this.backScreen();
                        }
                        break;
                    }
                    case -66: {
                        if (command == this.backCommand) {
                            this.backScreen();
                            break;
                        }
                        NetSend.getInstance().sendPetRename(ORPlayer.itSelectPetId, this.inputField.getString().trim());
                        this.backScreen();
                        break;
                    }
                    case 45: {
                        this.bytChatType = (byte) this.CGSelect.getSelectedIndex();
                        this.intCurPlace = this.inputField.getCaretPosition();
                        if (command == this.backCommand) {
                            this.showForm((byte) 40);
                            break;
                        }
                        if (command != this.okCommand) {
                            break;
                        }
                        int n = 0;
                        String string = this.CGSelect.getString(this.bytChatType);
                        if (string.equals(this.STRING_TALK_CHANNEL[0])) {
                            n = 0;
                        } else if (string.equals(this.STRING_TALK_CHANNEL[2])) {
                            n = 1;
                        } else if (string.equals(this.STRING_TALK_CHANNEL[3])) {
                            n = 2;
                        } else if (string.equals("Bang-người của bang mới thấy được")) {
                            n = 4;
                        } else if (string.equals("Đội-thành viên trong đội đều thấy được")) {
                            n = 3;
                        } else if (string.equals(this.STRING_TALK_CHANNEL[1])) {
                            n = 10;
                        }
                        Param.getInstance().bytOldChatChannel = (byte) n;
                        this.showForm((byte) 40);
                    }
                    default: {
                        break;
                    }
                }
            } catch (Exception exception) {
                exception.printStackTrace();
                this.backScreen();
            }
        }
    }

    private void addTicker(String string) {
        this.TickerHint = new Ticker("Lỗi: " + string);
        this.setTicker(this.TickerHint);
    }

    private void backTempScreen() {
        GameControl.getInstance().setScreen(DCanvas.getInstance());
    }

    private void backScreen() {
        GameControl.getInstance().setScreen(DCanvas.getInstance());
        this.clean();
    }

    public void clean() {
        this.okCommand = null;
        this.faceCommand = null;
        this.backCommand = null;
        this.nameField = null;
        this.inputField = null;
        this.CGSelect = null;
        fd = null;
        this.changeChannal = null;
    }

    private String siftChatContent(String string) {
        String string2;
        String string3;
        int n;
        int n2 = 0;
        while (n2 < string.length()) {
            n = string.indexOf("\n");
            if (n == -1) {
                break;
            }
            string3 = string.substring(0, n);
            string2 = string.substring(n + 2);
            string = String.valueOf(string3) + string2;
            n2 = 0;
            n2 = (short) (n2 + 1);
        }
        n2 = 0;
        while (n2 < string.length()) {
            n = string.indexOf("#S");
            if (n == -1) {
                break;
            }
            string3 = string.substring(0, n);
            string2 = string.substring(n + 2);
            string = String.valueOf(string3) + string2;
            n2 = 0;
            n2 = (short) (n2 + 1);
        }
        n2 = 0;
        while (n2 < string.length()) {
            n = string.indexOf("#F26");
            if (n == -1) {
                break;
            }
            string3 = string.substring(0, n + 3);
            string2 = string.substring(n + 3, n + 4);
            string2 = "5";
            string = String.valueOf(string3) + string2;
            n2 = 0;
            n2 = (short) (n2 + 1);
        }
        return string;
    }

    public void setChatContent(String string) {
        this.inputField.setString(string);
        this.showFormDes();
    }

    public void setChatAddProp_Equip(byte by, byte by2) {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("[");
            if (by == 0) {
                stringBuffer.append("w");
            } else {
                stringBuffer.append("z");
            }
            stringBuffer.append(by2);
            stringBuffer.append("]");
            if (this.chatstr != null) {
                String string = this.chatstr.substring(0, this.intCurPlace);
                String string2 = this.chatstr.substring(this.intCurPlace);
                this.intCurPlace = (String.valueOf(string) + stringBuffer.toString()).length();
                this.chatstr = String.valueOf(string) + stringBuffer.toString() + string2;
                this.inputField.setString(this.chatstr);
                DCanvas.getInstance().buttonRightFlash = 1;
                DCanvas.getInstance().buttonRightFlash = 0;
                MenuUI.getInstance().keyRightSoftEvent();
            } else {
                this.inputField.setString(stringBuffer.toString());
                DCanvas.getInstance().buttonRightFlash = 1;
                DCanvas.getInstance().buttonRightFlash = 0;
                MenuUI.getInstance().keyRightSoftEvent();
            }
        } catch (Exception exception) {
            DCanvas.getInstance().addInformation("Ô nhập vào đã đầy!");
        }
    }

    public void setChatAddProp(byte by, byte by2) {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("[");
            switch (by) {
                case 37: {
                    stringBuffer.append("c");
                    break;
                }
                case 36: {
                    stringBuffer.append("y");
                    break;
                }
                case 38: {
                    stringBuffer.append("r");
                    break;
                }
                case 39: {
                    stringBuffer.append("t");
                }
            }
            stringBuffer.append(by2);
            stringBuffer.append("]");
            if (this.chatstr != null) {
                String string = this.chatstr.substring(0, this.intCurPlace);
                String string2 = this.chatstr.substring(this.intCurPlace);
                this.intCurPlace = (String.valueOf(string) + stringBuffer.toString()).length();
                this.chatstr = String.valueOf(string) + stringBuffer.toString() + string2;
                this.inputField.setString(this.chatstr);
                DCanvas.getInstance().addInformation("Thêm vào thành công!");
                DCanvas.getInstance().buttonRightFlash = 1;
                DCanvas.getInstance().buttonRightFlash = 0;
                MenuUI.getInstance().keyRightSoftEvent();
            } else {
                this.inputField.setString(stringBuffer.toString());
                DCanvas.getInstance().addInformation("Thêm vào thành công!");
                DCanvas.getInstance().buttonRightFlash = 1;
                DCanvas.getInstance().buttonRightFlash = 0;
                MenuUI.getInstance().keyRightSoftEvent();
            }
        } catch (Exception exception) {
            DCanvas.getInstance().addInformation("Ô nhập vào đã đầy!");
        }
    }
}
