// 
// Decompiled by Procyon v0.5.36
// 
package face;

import base.Param;
import base.GameControl;
import base.Macro;
import base.DCanvas;
import javax.microedition.lcdui.Graphics;
import means.StringManager;

public class CueUI implements UIbase {

    private static CueUI cu;
    private boolean blnIsThis;
    private byte[] bytButtonType;
    private byte bytCueType;
    private TwoDialogUI twodialog;
    private String strFullTitle;
    private String[] strsFullContent;
    private byte bytFullMove;
    private byte bytFullRoll;
    private short shtFaceBackW;
    private short shtFaceBackX;
    private short shtFaceBackY;
    private short shtFaceRectX;
    private short shtFaceRectY;

    public CueUI() {
        this.blnIsThis = false;
        CueUI.cu = this;
    }

    public static CueUI getInstance() {
        return CueUI.cu;
    }

    public void init() {
        this.bytButtonType = new byte[3];
    }

    public void clean() {
        CueUI.cu = null;
        this.bytButtonType = null;
        this.twodialog = null;
    }

    public void isThis(final boolean _type) {
        this.blnIsThis = _type;
    }

    private void setButton(final int _type, final int _leftword, final int _rightword) {
        this.bytButtonType[0] = (byte) _type;
        this.bytButtonType[1] = (byte) _leftword;
        this.bytButtonType[2] = (byte) _rightword;
    }

    public void setComCue(final String _str) {
        (this.twodialog = new TwoDialogUI()).setTwoRect(_str, -1, (short) (-1), (byte) (-1), (byte) 1, -1);
        this.setButton(0, 0, 100);
        this.bytCueType = 1;
    }

    public void setFullCue(final String _title, final String _str) {
        this.strFullTitle = _title;
        this.strsFullContent = StringManager.wenZi(_str, StringManager.getNewLineW());
        this.setButton(1, 100, 2);
        this.bytCueType = 2;
    }

    public void setFullFace() {
        this.setInputFace();
        this.setButton(0, 0, 2);
        this.bytCueType = 3;
    }

    public void paint(final Graphics g) {
        if (this.bytCueType == 1) {
            this.twodialog.paint(g);
        } else if (this.bytCueType == 2) {
            DCanvas.getInstance().drawFullSee(g, this.strFullTitle, 0, this.strsFullContent.length, this.bytFullMove, Macro.bytMaxFullRow);
            DCanvas.getInstance().drawContent(g, this.strsFullContent, 12, Macro.FONTHEIGHT + 16, this.bytFullMove, Macro.bytMaxFullRow, (byte) 20);
        } else if (this.bytCueType == 3) {
            this.drawInputFace(g);
        }
        DCanvas.getInstance().drawSoftkey(g, this.bytButtonType[0], this.bytButtonType[1], this.bytButtonType[2], this.blnIsThis);
    }

    public void logic(final int time) {
        if (DCanvas.getInstance().IsKeyRelease(65536) || DCanvas.getInstance().IsKeyRelease(262144)) {
            this.leftEvent();
            DCanvas.getInstance().buttonLeftFlash = 0;
        } else if (DCanvas.getInstance().isKeyDown(65536) || DCanvas.getInstance().IsKeyRelease(262144)) {
            DCanvas.getInstance().buttonLeftFlash = 1;
        } else if (DCanvas.getInstance().IsKeyRelease(131072)) {
            this.rightEvent();
            DCanvas.getInstance().buttonRightFlash = 0;
        } else if (DCanvas.getInstance().isKeyDown(131072)) {
            DCanvas.getInstance().buttonRightFlash = 1;
        } else if (DCanvas.getInstance().isKeyDown(16384)) {
            this.keyFullRectLeft();
        } else if (DCanvas.getInstance().isKeyDown(32768)) {
            this.keyFullRectRight();
        } else if (DCanvas.getInstance().isKeyDown(4096)) {
            this.keyFullRectUp();
        } else if (DCanvas.getInstance().isKeyDown(8192)) {
            this.keyFullRectDown();
        }
        if (DCanvas.getInstance().blnIsTouch && DCanvas.getInstance().blnPointerPressed) {
            if (DCanvas.getInstance().PointerDwonSoftKey_Left()) {
                if (this.bytButtonType[1] != 100) {
                    this.leftEvent();
                }
            } else if (DCanvas.getInstance().PointerDwonSoftKey_Right()) {
                if (this.bytButtonType[2] != 100) {
                    this.rightEvent();
                }
            } else if (this.bytCueType == 3 && DCanvas.getInstance().IsPointerDown(this.shtFaceBackX + 2, this.shtFaceBackY + 2, this.shtFaceBackW - 5, this.shtFaceBackW - 5)) {
                final byte _bytFullMove = (byte) ((DCanvas.getInstance().CurPressedX - this.shtFaceBackX) / 22);
                final byte _bytFullRoll = (byte) ((DCanvas.getInstance().CurPressedY - this.shtFaceBackY) / 22);
                if (this.bytFullMove == _bytFullMove && this.bytFullRoll == _bytFullRoll) {
                    this.leftEvent();
                } else {
                    this.bytFullMove = _bytFullMove;
                    this.bytFullRoll = _bytFullRoll;
                    this.setInputFace();
                }
            }
        }
    }

    private void leftEvent() {
        if (this.bytButtonType[1] == 100) {
            return;
        }
        if (this.bytCueType == 3) {
            if (FormDes.getInstance().chatstr != null) {
                final int length = FormDes.getInstance().chatstr.length();
                FormDes.getInstance().getClass();
                if (length < 24 - 3) {
                    final String _tempstr1 = FormDes.getInstance().chatstr.substring(0, FormDes.getInstance().intCurPlace);
                    final String _tempstr2 = FormDes.getInstance().chatstr.substring(FormDes.getInstance().intCurPlace);
                    FormDes.getInstance().chatstr = String.valueOf(_tempstr1) + "#C" + (this.bytFullMove + this.bytFullRoll * 4 + 10) + _tempstr2;
                }
                FormDes.getInstance().setChatContent(FormDes.getInstance().chatstr);
            } else {
                FormDes.getInstance().chatstr = "#C" + (this.bytFullMove + this.bytFullRoll * 4 + 10);
                FormDes.getInstance().setChatContent(FormDes.getInstance().chatstr);
            }
        }
        GameControl.getInstance().delUIbase(11);
    }

    private void rightEvent() {
        if (this.bytButtonType[2] != 100) {
            if (this.bytCueType == 3) {
                FormDes.getInstance().showFormDes();
            }
            GameControl.getInstance().delUIbase(11);
        }
    }

    private void keyFullRectLeft() {
        if (this.bytCueType == 3) {
            --this.bytFullMove;
            if (this.bytFullMove < 0) {
                this.bytFullMove = 3;
                --this.bytFullRoll;
                if (this.bytFullRoll < 0) {
                    this.bytFullRoll = 3;
                }
            }
            this.setInputFace();
        }
    }

    private void keyFullRectRight() {
        if (this.bytCueType == 3) {
            ++this.bytFullMove;
            if (this.bytFullMove > 3) {
                this.bytFullMove = 0;
                ++this.bytFullRoll;
                if (this.bytFullRoll > 3) {
                    this.bytFullRoll = 0;
                }
            }
            this.setInputFace();
        }
    }

    private void keyFullRectUp() {
        if (this.bytCueType == 3) {
            --this.bytFullRoll;
            if (this.bytFullRoll < 0) {
                this.bytFullRoll = 3;
                --this.bytFullMove;
                if (this.bytFullMove < 0) {
                    this.bytFullMove = 3;
                }
            }
            this.setInputFace();
        } else {
            DCanvas.getInstance().bytSpoolrFlash = 1;
            if (this.bytCueType != 2 && Macro.bytMaxFullRow > this.strsFullContent.length) {
                return;
            }
            --this.bytFullMove;
            if (this.bytFullMove <= 0) {
                this.bytFullMove = 0;
            }
        }
    }

    private void keyFullRectDown() {
        if (this.bytCueType == 3) {
            ++this.bytFullRoll;
            if (this.bytFullRoll > 3) {
                this.bytFullRoll = 0;
                ++this.bytFullMove;
                if (this.bytFullMove > 3) {
                    this.bytFullMove = 0;
                }
            }
            this.setInputFace();
        } else {
            DCanvas.getInstance().bytSpoolrFlash = 2;
            if (this.bytCueType != 2 && Macro.bytMaxFullRow > this.strsFullContent.length) {
                return;
            }
            if (this.bytFullMove < this.strsFullContent.length - Macro.bytMaxFullRow) {
                ++this.bytFullMove;
            }
        }
    }

    private void setInputFace() {
        this.shtFaceBackW = 88;
        this.shtFaceBackX = (short) ((Macro.SCREEN_WIDTH - this.shtFaceBackW) / 2);
        this.shtFaceBackY = (short) ((Macro.SCREEN_HEIGHT - 18 - this.shtFaceBackW) / 2);
        this.shtFaceRectX = (short) ((Macro.SCREEN_WIDTH - this.shtFaceBackW) / 2 + 3 + this.bytFullMove * 21);
        this.shtFaceRectY = (short) ((Macro.SCREEN_HEIGHT - 18 - this.shtFaceBackW) / 2 + 3 + this.bytFullRoll * 21);
    }

    private void drawInputFace(final Graphics g) {
        g.setColor(0);
        g.fillRect(0, 0, (int) Macro.SCREEN_WIDTH, (int) Macro.SCREEN_HEIGHT);
        g.setColor(16777215);
        g.fillRect((int) this.shtFaceBackX, (int) this.shtFaceBackY, this.shtFaceBackW - 2, this.shtFaceBackW - 2);
        g.setColor(5660182);
        g.drawRect((int) this.shtFaceBackX, (int) this.shtFaceBackY, this.shtFaceBackW - 2, this.shtFaceBackW - 2);
        g.setColor(8492619);
        g.drawRect(this.shtFaceBackX + 1, this.shtFaceBackY + 1, 84, 84);
        for (byte i = 0; i < 4; ++i) {
            for (byte j = 0; j < 4; ++j) {
                Param.getInstance().sprface.drawAnimationFrame(g, i * 4 + j, 0, this.shtFaceBackX + 5 + j * 21 + 7, this.shtFaceBackY + 5 + i * 21 + 7);
            }
        }
        g.setColor(11253897);
        g.drawRect(this.shtFaceRectX - 1, this.shtFaceRectY - 1, 19, 19);
        g.setColor(8492619);
        g.drawRect((int) this.shtFaceRectX, (int) this.shtFaceRectY, 17, 17);
    }
}
