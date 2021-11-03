// Decompiled with: Procyon 0.5.36
// Class Version: 3
package means;

import javax.microedition.lcdui.Graphics;
import base.Macro;
import java.util.Vector;

public class AnalysisWordContent
{
    private final int[] INTSCOLOR;
    private Vector _vector;
    private int intWidht;
    private int intChatX;
    private boolean blnNewLine;
    private byte bytLineNum;
    public short shtLenght;
    public AnalysisWordContent awc;
    
    public AnalysisWordContent() {
        this.INTSCOLOR = new int[] { 97530, 969327, 15737441, 14257966 };
        this._vector = null;
        this.intWidht = 0;
        this.intChatX = 0;
    }
    
    public void clear() {
        this._vector = null;
        this.awc = null;
    }
    
    public void analysisChatContent(String s, final int n, final int n2) {
        this.shtLenght = 0;
        this.blnNewLine = false;
        this.bytLineNum = 0;
        this.intWidht = 0;
        this.intChatX = 0;
        int n3 = -1;
        this._vector = new Vector(1, 1);
        for (int i = 0; i < s.length(); ++i) {
            final int index = s.indexOf("#S");
            if (index != -1) {
                if (index != 0) {
                    final String substring = s.substring(0, index);
                    final int index2 = substring.indexOf("\n");
                    if (index2 != -1) {
                        final String substring2 = substring.substring(0, index2);
                        this.addWordSection(this.intWidht += Macro.font.stringWidth(substring2), n2, n, substring2, true);
                        this.blnNewLine = true;
                        this.intWidht = 0;
                        s = s.substring(index2 + 1);
                        i = -1;
                        continue;
                    }
                    this.addWordSection(this.intWidht += Macro.font.stringWidth(substring), n2, n, substring, false);
                }
                ++n3;
                final String substring3 = s.substring(index + 2, index + 3);
                int int1;
                try {
                    int1 = Integer.parseInt(substring3);
                }
                catch (Exception ex) {
                    int1 = 1;
                }
                if (int1 < 0 || int1 > this.INTSCOLOR.length + 1) {
                    int1 = 1;
                }
                final int n4 = this.INTSCOLOR[int1 - 1];
                s = s.substring(index + 3);
                int n5 = s.indexOf("#E");
                final int index3 = s.indexOf("#S");
                boolean b = false;
                if (n5 == -1) {
                    if (index3 != -1) {
                        n5 = index3;
                    }
                    else {
                        n5 = s.length();
                        b = true;
                    }
                }
                else if (n5 != -1 && index3 != -1 && n5 > index3) {
                    n5 = index3;
                }
                final String substring4 = s.substring(0, n5);
                this.addWordSection(this.intWidht += Macro.font.stringWidth(" " + substring4 + " "), n2, n4, " " + substring4 + " ", false);
                if (b) {
                    s = s.substring(n5);
                }
                else {
                    s = s.substring(n5 + 2);
                }
                i = -1;
            }
            else {
                final int index4 = s.indexOf("\n");
                if (index4 == -1) {
                    this.addWordSection(this.intWidht += Macro.font.stringWidth(s), n2, n, s, false);
                    s = "";
                }
                else {
                    final String substring5 = s.substring(0, index4);
                    this.addWordSection(this.intWidht += Macro.font.stringWidth(substring5), n2, n, substring5, true);
                    this.blnNewLine = true;
                    this.intWidht = 0;
                    s = s.substring(index4 + 1);
                    i = -1;
                }
            }
        }
    }
    
    private void addWordSection(final int n, final int n2, final int n3, String substring, final boolean my) {
        final int n4 = n - Macro.font.stringWidth(substring);
        if (n > n2) {
            int n5 = n2 - n4;
            int n6 = 0;
            int n7 = 0;
            if (n5 != 0 || n / n2 >= 1) {
                for (int i = 1; i <= substring.length(); ++i) {
                    n7 = 0;
                    if (Macro.font.stringWidth(substring.substring(0, i)) > n5) {
                        final String substring2 = substring.substring(0, i - 1);
                        final ChatContent chatContent = new ChatContent();
                        chatContent.type = 1;
                        chatContent.character = substring2;
                        chatContent.fontColor = n3;
                        if (this.blnNewLine) {
                            chatContent.mY = 1;
                            this.blnNewLine = false;
                        }
                        else {
                            chatContent.mY = (my ? 1 : ((n6 == 0) ? 0 : 1));
                        }
                        if (chatContent.mY == 1) {
                            chatContent.mX = 0;
                            ++this.shtLenght;
                            final int stringWidth = Macro.font.stringWidth(substring2);
                            this.intWidht = stringWidth;
                            this.intChatX = stringWidth;
                        }
                        else {
                            chatContent.mX = this.intChatX;
                            this.intChatX += Macro.font.stringWidth(substring2);
                        }
                        if (chatContent.mY == 1) {
                            ++this.bytLineNum;
                        }
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
                    chatContent2.character = substring;
                    chatContent2.fontColor = n3;
                    if (this.blnNewLine) {
                        chatContent2.mY = 1;
                        this.blnNewLine = false;
                    }
                    else {
                        chatContent2.mY = (my ? 1 : ((n6 == 0) ? 0 : 1));
                    }
                    if (chatContent2.mY == 1) {
                        chatContent2.mX = 0;
                        ++this.shtLenght;
                        final int stringWidth2 = Macro.font.stringWidth(substring);
                        this.intWidht = stringWidth2;
                        this.intChatX = stringWidth2;
                    }
                    else {
                        chatContent2.mX = 0;
                        this.intChatX += Macro.font.stringWidth(substring);
                    }
                    if (chatContent2.mY == 1) {
                        ++this.bytLineNum;
                    }
                    this._vector.addElement(chatContent2);
                }
            }
            else {
                final ChatContent chatContent3 = new ChatContent();
                chatContent3.type = 1;
                chatContent3.character = substring;
                chatContent3.fontColor = n3;
                chatContent3.mY = 1;
                if (chatContent3.mY == 1) {
                    chatContent3.mX = 0;
                    ++this.shtLenght;
                    final int stringWidth3 = Macro.font.stringWidth(substring);
                    this.intWidht = stringWidth3;
                    this.intChatX = stringWidth3;
                }
                else {
                    chatContent3.mX = this.intChatX;
                    this.intChatX += Macro.font.stringWidth(substring);
                }
                if (chatContent3.mY == 1) {
                    ++this.bytLineNum;
                }
                this._vector.addElement(chatContent3);
            }
        }
        else {
            final ChatContent chatContent4 = new ChatContent();
            chatContent4.type = 1;
            chatContent4.character = substring;
            chatContent4.fontColor = n3;
            if (this.blnNewLine) {
                chatContent4.mY = 1;
                this.blnNewLine = false;
            }
            else {
                chatContent4.mY = (my ? 1 : 0);
            }
            if (chatContent4.mY == 1) {
                chatContent4.mX = 0;
                ++this.shtLenght;
                final int stringWidth4 = Macro.font.stringWidth(substring);
                this.intWidht = stringWidth4;
                this.intChatX = stringWidth4;
            }
            else {
                chatContent4.mX = this.intChatX;
                this.intChatX += Macro.font.stringWidth(substring);
            }
            if (chatContent4.mY == 1) {
                ++this.bytLineNum;
            }
            this._vector.addElement(chatContent4);
        }
    }
    
    public String[] wenZi() {
        if (this.bytLineNum != 0) {
            final String[] array = new String[this.bytLineNum + 1];
            array[0] = "";
            final int size = this._vector.size();
            int n = 0;
            for (int i = 0; i < size; i = (byte)(i + 1)) {
                final ChatContent chatContent = (ChatContent) this._vector.elementAt(i);
                if (chatContent.mY == 1) {
                    ++n;
                    if (chatContent.character != null) {
                        array[n] = chatContent.character;
                    }
                }
                else if (chatContent.character != null) {
                    array[n] = String.valueOf(array[n]) + chatContent.character;
                }
            }
            return array;
        }
        final String[] array2 = { "" };
        final int size2 = this._vector.size();
        final String[] array3 = new String[size2];
        for (int j = 0; j < size2; j = (byte)(j + 1)) {
            array3[j] = ((ChatContent)this._vector.elementAt(j)).character;
            if (array3[j] != null) {
                final String[] array4 = array2;
                array4[0] = String.valueOf(array4[0]) + array3[j];
            }
        }
        return array2;
    }
    
    public void drawString(final Graphics graphics, final int n, int n2) {
        for (int i = 0; i < this._vector.size(); i = (byte)(i + 1)) {
            final ChatContent chatContent = (ChatContent) this._vector.elementAt(i);
            if (chatContent.fontColor == -1) {
                chatContent.fontColor = 16642234;
            }
            graphics.setColor(chatContent.fontColor);
            if (i != 0 && chatContent.mY == 1) {
                n2 += Macro.FONTHEIGHT;
            }
            graphics.drawString(chatContent.character, n + chatContent.mX, n2, 20);
        }
    }
    
    public void drawContent(final Graphics graphics, final int n, int n2, final short n3, final byte b) {
        if (this._vector.size() > b) {
            for (byte b2 = 0; b2 < b; ++b2) {
                final ChatContent chatContent = (ChatContent) this._vector.elementAt(b2 + n3);
                if (chatContent.fontColor == -1) {
                    chatContent.fontColor = 16642234;
                }
                graphics.setColor(chatContent.fontColor);
                if (b2 != 0 && chatContent.mY == 1) {
                    n2 += Macro.FONTHEIGHT;
                }
                graphics.drawString(chatContent.character, n + chatContent.mX, n2, 20);
            }
        }
        else {
            for (int i = 0; i < this._vector.size(); i = (byte)(i + 1)) {
                final ChatContent chatContent2 = (ChatContent) this._vector.elementAt(i);
                if (chatContent2.fontColor == -1) {
                    chatContent2.fontColor = 16642234;
                }
                graphics.setColor(chatContent2.fontColor);
                if (i != 0 && chatContent2.mY == 1) {
                    n2 += Macro.FONTHEIGHT;
                }
                graphics.drawString(chatContent2.character, n + chatContent2.mX, n2, 20);
            }
        }
    }
    
    public void drawWord(final Graphics graphics, final int n, final int n2, final int n3) {
        StringManager.drawColorWordPage(graphics, this._vector, n, n2, n3, this.bytLineNum, 20);
    }
}
