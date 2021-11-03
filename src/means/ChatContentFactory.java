// Decompiled with: Procyon 0.5.36
// Class Version: 3
package means;

import common.DrawBase;
import face.GameUI;
import javax.microedition.lcdui.Graphics;
import face.MenuUI;
import base.DCanvas;
import base.Macro;
import base.Param;
import java.util.Vector;

public class ChatContentFactory {

    private static ChatContentFactory ccf;
    Vector _vector;
    int intWidht;
    int intChatX;
    public byte bytChatLineNum;

    public static ChatContentFactory getInstance() {
        return ChatContentFactory.ccf;
    }

    public ChatContentFactory() {
        this._vector = null;
        this.intWidht = 0;
        this.intChatX = 0;
        ChatContentFactory.ccf = this;
    }

    public void put(final String s, final int n, final byte b, final String s2, final int n2) {
        this.analysisChatContent(s2, s, n, b);
        switch (b) {
            case 10: {
                Param.getInstance().vChatRaceNote = this.setNoteVector(Param.getInstance().vChatRaceNote, this._vector, (byte) 20);
                Param.getInstance().vChatNote = this.setNoteVector(Param.getInstance().vChatNote, this._vector, (byte) 20);
                break;
            }
            case 2: {
                Param.getInstance().vChatMapNote = this.setNoteVector(Param.getInstance().vChatMapNote, this._vector, (byte) 20);
                Param.getInstance().vChatNote = this.setNoteVector(Param.getInstance().vChatNote, this._vector, (byte) 20);
                break;
            }
            case 1: {
                Param.getInstance().vChatWordNote = this.setNoteVector(Param.getInstance().vChatWordNote, this._vector, (byte) 20);
                Param.getInstance().vChatNote = this.setNoteVector(Param.getInstance().vChatNote, this._vector, (byte) 20);
                break;
            }
            case 0:
            case 11: {
                Param.getInstance().vChatSingleNote = this.setNoteVector(Param.getInstance().vChatSingleNote, this._vector, Macro.bytMaxChatNote);
                Param.getInstance().vChatNote = this.setNoteVector(Param.getInstance().vChatNote, this._vector, (byte) 20);
                break;
            }
            case 3: {
                Param.getInstance().vChatTeamNote = this.setNoteVector(Param.getInstance().vChatTeamNote, this._vector, Macro.bytMaxChatNote);
                Param.getInstance().vChatNote = this.setNoteVector(Param.getInstance().vChatNote, this._vector, (byte) 20);
                break;
            }
            case 4: {
                Param.getInstance().vChatConsortianNote = this.setNoteVector(Param.getInstance().vChatConsortianNote, this._vector, Macro.bytMaxChatNote);
                Param.getInstance().vChatNote = this.setNoteVector(Param.getInstance().vChatNote, this._vector, (byte) 20);
                break;
            }
        }
        if (DCanvas.getInstance().UMenu != null && MenuUI.getInstance().getState() == 42) {
            MenuUI.getInstance().blnSetChatNote = false;
            MenuUI.getInstance().setChatNote();
        }
        this._vector = null;
    }

    public Vector setNoteVector(Vector vector, final Vector vector2, final byte b) {
        if (vector == null) {
            vector = new Vector();
        }
        vector.insertElementAt(vector2, 0);
        if (vector.size() > b) {
            for (int i = b; i < vector.size(); ++i) {
                vector.removeElementAt(i);
            }
        }
        return vector;
    }

    public Vector setNoteVectorScreen(Vector vector, final Vector vector2, final byte b) {
        if (vector == null) {
            vector = new Vector();
        }
        vector.addElement(vector2);
        if (vector.size() > b) {
            for (int i = b; i < vector.size(); ++i) {
                vector.removeElementAt(i);
            }
        }
        return vector;
    }

    public boolean getString(final String s, final String s2) {
        if (s.equals("1")) {
            if (s2.equals("0") || s2.equals("1") || s2.equals("2") || s2.equals("3") || s2.equals("4") || s2.equals("5") || s2.equals("6") || s2.equals("7") || s2.equals("8") || s2.equals("9")) {
                return true;
            }
        } else if (s.equals("2") && (s2.equals("0") || s2.equals("1") || s2.equals("2") || s2.equals("3") || s2.equals("4") || s2.equals("5") || s2.equals("6"))) {
            return true;
        }
        return false;
    }

    public void analysisChatContent(final String s, String substring, final int n, final byte b) {
        this.intWidht = 0;
        this.intChatX = 0;
        String string = "";
        int n2 = -1;
        this._vector = new Vector(1, 1);
        for (int i = 0; i < substring.length(); ++i) {
            final int index = substring.indexOf("#S");
            if (index != -1) {
                if (index != 0) {
                    this.separationFace(s, substring.substring(0, index), n, b);
                }
                ++n2;
                final String substring2 = substring.substring(index + 2, index + 3);
                int int1;
                try {
                    int1 = Integer.parseInt(substring2);
                } catch (Exception ex) {
                    int1 = 1;
                }
                if (int1 < 0 || int1 > Macro.INT_PROP_COLOR.length) {
                    int1 = 1;
                }
                final int n3 = Macro.INT_PROP_COLOR[int1];
                final String substring3 = substring.substring(index + 3);
                final int index2 = substring3.indexOf("[");
                final String substring4 = substring3.substring(1, index2);
                final String substring5 = substring3.substring(index2);
                String substring6 = "";
                final int index3 = substring5.indexOf("]");
                if (index3 != -1) {
                    substring6 = substring5.substring(0, index3 + 1);
                }
                final StringBuffer sb = new StringBuffer();
                sb.append(string);
                sb.append("#S");
                sb.append(int1);
                sb.append("F");
                sb.append(substring4);
                sb.append(substring5.substring(0, index3 + 1));
                string = sb.toString();
                this.addWordSection(s, this.intWidht += Macro.font.stringWidth(substring6), Macro.SCREEN_WIDTH, n3, substring6, b);
                substring = substring5.substring(index3 + 1);
                i = -1;
            } else {
                this.separationFace(s, substring, n, b);
                substring = "";
            }
        }
        if (n2 != -1) {
            ((ChatContent) this._vector.elementAt(0)).strsContent = String.valueOf(n2 + 1) + string;
        }
    }

    private void separationFace(final String name, String s, final int n, final byte channel) {
        for (int i = 0; i < s.length(); ++i) {
            final int index = s.indexOf("#C");
            if (index != -1) {
                if (index != 0) {
                    final String substring = s.substring(0, index);
                    this.addWordSection(name, this.intWidht += Macro.font.stringWidth(substring), Macro.SCREEN_WIDTH, n, substring, channel);
                }
                s = s.substring(index);
                if (getInstance().getString(s.substring(2, 3), s.substring(3, 4))) {
                    final ChatContent chatContent = new ChatContent();
                    chatContent.imageID = (byte) (Integer.parseInt(s.substring(2, 4)) - 10);
                    chatContent.type = 2;
                    chatContent.name = name;
                    if (Macro.SCREEN_WIDTH - this.intWidht >= 14) {
                        chatContent.mX = this.intChatX;
                        this.intChatX += 14;
                        chatContent.mY = 0;
                        this.intWidht += 14;
                    } else {
                        this.intChatX = 0;
                        chatContent.mX = this.intChatX;
                        chatContent.mY = 1;
                        this.intWidht = 14;
                    }
                    chatContent.channel = channel;
                    this._vector.addElement(chatContent);
                    s = s.substring(4);
                } else {
                    this.addWordSection(name, this.intWidht += Macro.font.stringWidth(s), Macro.SCREEN_WIDTH, n, s, channel);
                    s = "";
                }
                i = -1;
            } else {
                this.addWordSection(name, this.intWidht += Macro.font.stringWidth(s), Macro.SCREEN_WIDTH, n, s, channel);
                s = "";
            }
        }
    }

    public void addWordSection(final String s, final int n, final int n2, final int n3, String substring, final byte b) {
        final int n4 = n - Macro.font.stringWidth(substring);
        if (n > n2) {
            int n5 = n2 - n4;
            int n6 = 0;
            int n7 = 0;
            if (n5 != 0) {
                for (int i = 1; i <= substring.length(); ++i) {
                    n7 = 0;
                    if (Macro.font.stringWidth(substring.substring(0, i)) > n5) {
                        final String substring2 = substring.substring(0, i - 1);
                        final ChatContent chatContent = new ChatContent();
                        chatContent.type = 1;
                        chatContent.name = s;
                        chatContent.character = substring2;
                        chatContent.fontColor = n3;
                        chatContent.mY = ((n6 != 0) ? 1 : 0);
                        if (chatContent.mY == 1) {
                            chatContent.mX = 0;
                            final int stringWidth = Macro.font.stringWidth(substring2);
                            this.intWidht = stringWidth;
                            this.intChatX = stringWidth;
                        } else {
                            chatContent.mX = this.intChatX;
                            this.intChatX += Macro.font.stringWidth(substring2);
                        }
                        chatContent.channel = b;
                        this._vector.addElement(chatContent);
                        substring = substring.substring(i - 1);
                        n5 = n2;
                        i = 0;
                        ++n6;
                        n7 = -1;
                    }
                }
                if (n7 == 0) {
                    final ChatContent chatContent2 = new ChatContent();
                    chatContent2.type = 1;
                    chatContent2.name = s;
                    chatContent2.character = substring;
                    chatContent2.fontColor = n3;
                    chatContent2.mY = ((n6 != 0) ? 1 : 0);
                    if (chatContent2.mY == 1) {
                        chatContent2.mX = 0;
                        final int stringWidth2 = Macro.font.stringWidth(substring);
                        this.intWidht = stringWidth2;
                        this.intChatX = stringWidth2;
                    } else {
                        chatContent2.mX = 0;
                        this.intChatX += Macro.font.stringWidth(substring);
                    }
                    chatContent2.channel = b;
                    this._vector.addElement(chatContent2);
                }
            } else {
                final ChatContent chatContent3 = new ChatContent();
                chatContent3.type = 1;
                chatContent3.name = s;
                chatContent3.character = substring;
                chatContent3.fontColor = n3;
                chatContent3.mY = 1;
                if (chatContent3.mY == 1) {
                    chatContent3.mX = 0;
                    final int stringWidth3 = Macro.font.stringWidth(substring);
                    this.intWidht = stringWidth3;
                    this.intChatX = stringWidth3;
                } else {
                    chatContent3.mX = this.intChatX;
                    this.intChatX += Macro.font.stringWidth(substring);
                }
                chatContent3.channel = b;
                this._vector.addElement(chatContent3);
            }
        } else {
            final ChatContent chatContent4 = new ChatContent();
            chatContent4.type = 1;
            chatContent4.name = s;
            chatContent4.character = substring;
            chatContent4.fontColor = n3;
            chatContent4.mY = 0;
            if (chatContent4.mY == 1) {
                chatContent4.mX = 0;
                final int stringWidth4 = Macro.font.stringWidth(substring);
                this.intWidht = stringWidth4;
                this.intChatX = stringWidth4;
            } else {
                chatContent4.mX = this.intChatX;
                this.intChatX += Macro.font.stringWidth(substring);
            }
            chatContent4.channel = b;
            this._vector.addElement(chatContent4);
        }
    }

    public void draw(final Graphics graphics) {
        int n = 0;
        int n2 = 0;
        graphics.setColor(16777215);
        graphics.fillRect(0, 20, Macro.SCREEN_WIDTH, 100);
        for (int i = 0; i < this._vector.size(); ++i) {
            final ChatContent chatContent = (ChatContent) this._vector.elementAt(i);
            if (chatContent.type == 1) {
                if (chatContent.mY == 1) {
                    n2 += Macro.FONTHEIGHT;
                    n = 0;
                }
                graphics.setColor(chatContent.fontColor);
                graphics.drawString(chatContent.character, n, 20 + n2, 20);
                n += Macro.font.stringWidth(chatContent.character);
            } else {
                if (chatContent.mY == 1) {
                    n2 += Macro.FONTHEIGHT;
                    n = 0;
                }
                Param.getInstance().sprface.drawAnimationFrame(graphics, chatContent.imageID, 0, n + 7, 20 + n2 + 7);
                n += 14;
            }
        }
    }

    public void drawChat(final Graphics graphics) {
        if (Param.getInstance().vChatNote != null) {
            graphics.setClip(0, Macro.SCREEN_GAME_HEIGHT - GameUI.getInstance().bytMsgNumber * Macro.FONTHEIGHT - 36, Macro.SCREEN_WIDTH, GameUI.getInstance().bytMsgNumber * Macro.FONTHEIGHT);
            if (!GameUI.getInstance().blnChannelType) {
                for (byte b = 0; b < this.bytChatLineNum; ++b) {
                    DrawBase.drawRGB(graphics, (byte) 3, 0, Macro.SCREEN_GAME_HEIGHT - (b + 1) * Macro.FONTHEIGHT - 36, Macro.SCREEN_WIDTH, Macro.FONTHEIGHT);
                }
            }
            this.bytChatLineNum = 0;
            int n = Macro.SCREEN_GAME_HEIGHT - Macro.FONTHEIGHT - 36;
            for (int i = 0; i < Param.getInstance().vChatNote.size(); ++i) {
                if (i < 0) {
                    break;
                }
                final Vector vector = (Vector) Param.getInstance().vChatNote.elementAt(i);
                int n2 = -1;
                for (short n3 = (short) (vector.size() - 1); n3 >= 0; --n3) {
                    final ChatContent chatContent = (ChatContent) vector.elementAt(n3);
                    if (GameUI.getInstance().bytMsgShowType == 0 || (GameUI.getInstance().bytMsgShowType == 1 && chatContent.channel != 1)) {
                        n2 = 0;
                        if (chatContent.type == 2) {
                            Param.getInstance().sprface.drawAnimationFrame(graphics, chatContent.imageID, 0, chatContent.mX + 7, n + (Macro.FONTHEIGHT - 14) / 2 + 7);
                        } else {
                            graphics.setColor(chatContent.fontColor);
                            graphics.drawString(chatContent.character, chatContent.mX, n, 20);
                        }
                        if (chatContent.mY == 1) {
                            n -= Macro.FONTHEIGHT;
                            ++this.bytChatLineNum;
                            if (this.bytChatLineNum > GameUI.getInstance().bytMsgNumber) {
                                break;
                            }
                        }
                    }
                }
                if (n2 == 0) {
                    n -= Macro.FONTHEIGHT;
                    ++this.bytChatLineNum;
                    if (this.bytChatLineNum > GameUI.getInstance().bytMsgNumber) {
                        break;
                    }
                }
            }
            DCanvas.getInstance().clearScreen();
        }
    }
}
