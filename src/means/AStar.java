// Decompiled with: Procyon 0.5.36
// Class Version: 3
package means;

import model.Map;
import java.util.Vector;

public class AStar
{
    private byte bytEndX;
    private byte bytEndY;
    private Vector vOpen;
    private Vector vClose;
    private byte bytMaxStep;
    private byte bytPassType;
    
    public AStar(final byte b, final byte b2, final byte bytEndX, final byte bytEndY, final byte bytMaxStep, final byte bytPassType) {
        this.bytPassType = bytPassType;
        if (!this.checkPass((byte)1, bytEndX, bytEndY)) {
            return;
        }
        if (b == bytEndX && b2 == bytEndY) {
            return;
        }
        this.bytMaxStep = bytMaxStep;
        this.bytEndX = bytEndX;
        this.bytEndY = bytEndY;
        this.vOpen = new Vector(1, 1);
        this.vClose = new Vector(1, 1);
        final cNode cNode = new cNode();
        cNode.setNode(b, b2, (byte)2, null);
        this.vOpen.addElement(cNode);
    }
    
    private void addOpen(final cNode fatherNode) {
        byte b = 0;
        byte b2 = 0;
        byte b3 = 0;
    Label_0453:
        for (int i = 0; i < 4; i = (byte)(i + 1)) {
            final cNode cNode = new cNode();
            if (i == 0) {
                b = (byte)(fatherNode.X + 1);
                b2 = (byte)fatherNode.Y;
                b3 = 4;
            }
            else if (i == 1) {
                b = (byte)(fatherNode.X - 1);
                b2 = (byte)fatherNode.Y;
                b3 = 3;
            }
            else if (i == 2) {
                b = (byte)fatherNode.X;
                b2 = (byte)(fatherNode.Y + 1);
                b3 = 2;
            }
            else if (i == 3) {
                b = (byte)fatherNode.X;
                b2 = (byte)(fatherNode.Y - 1);
                b3 = 1;
            }
            if ((this.bytEndX == b && this.bytEndY == b2) || this.checkPass(this.bytPassType, b, b2)) {
                if (this.bytPassType == 0 && (b != this.bytEndX || b2 != this.bytEndY)) {
                    final short n = Map.getInstance().shtsMapEvent[b2][b];
                    if (n > 0 && n >= 101 && n < 201) {
                        final short n2 = (short)(n - 101);
                        final cNode cNode2 = new cNode();
                        cNode2.setNode(b, b2, b3, fatherNode);
                        this.vClose.addElement(cNode2);
                        b = Map.getInstance().bytInJumpPlace[n2][2];
                        b2 = Map.getInstance().bytInJumpPlace[n2][3];
                    }
                }
                for (int j = 0; j < this.vClose.size(); ++j) {
                    final cNode cNode3 = (cNode) this.vClose.elementAt(j);
                    if (b == cNode3.X && b2 == cNode3.Y) {
                        continue Label_0453;
                    }
                }
                cNode.setNode(b, b2, b3, fatherNode);
                int k = 0;
                while (k < this.vOpen.size()) {
                    final cNode cNode4 = (cNode) this.vOpen.elementAt(k);
                    if (b == cNode4.X && b2 == cNode4.Y) {
                        if (cNode4.getG() > cNode.getG()) {
                            cNode4.fatherNode = fatherNode;
                            cNode4.D = cNode.D;
                        }
                        continue Label_0453;
                    }
                    else {
                        ++k;
                    }
                }
                cNode.G = cNode.getG();
                this.vOpen.addElement(cNode);
            }
        }
    }
    
    private boolean checkPass(final byte b, final byte b2, final byte b3) {
        switch (b) {
            case 0: {
                return Map.getInstance().checkMeIsPass(b2, b3);
            }
            case 1: {
                return Map.getInstance().checkIsPass(b2, b3);
            }
            default: {
                return false;
            }
        }
    }
    
    private void taxis() {
        int n = 0;
        int n2 = -1;
        for (int i = this.vOpen.size() - 1; i >= 0; --i) {
            final int cNode = ((cNode) this.vOpen.elementAt(i)).getF();
            if (cNode < n || n == 0) {
                n = cNode;
                n2 = i;
            }
        }
        if (n2 != -1) {
            final cNode cNode = (cNode) this.vOpen.elementAt(n2);
            this.vOpen.removeElementAt(n2);
            this.vOpen.insertElementAt(cNode, 0);
        }
    }
    
    public byte[] getPath() {
        if (this.vOpen == null) {
            return null;
        }
        boolean b = false;
        cNode fatherNode = null;
        int n = 0;
        while (this.vOpen.size() != 0) {
            fatherNode = (cNode) this.vOpen.firstElement();
            if (fatherNode.X == this.bytEndX && fatherNode.Y == this.bytEndY) {
                b = true;
                break;
            }
            this.vOpen.removeElement(fatherNode);
            this.vClose.addElement(fatherNode);
            this.addOpen(fatherNode);
            this.taxis();
            if (this.bytMaxStep > 0 && ++n > this.bytMaxStep) {
                break;
            }
        }
        byte[] array = null;
        if (b) {
            array = new byte[fatherNode.getG()];
            array[array.length - 1] = fatherNode.D;
            for (int i = array.length - 2; i >= 0; --i) {
                fatherNode = fatherNode.fatherNode;
                array[i] = fatherNode.D;
            }
        }
        this.vOpen = null;
        this.vClose = null;
        return array;
    }
    
    private class cNode
    {
        int G;
        int H;
        int X;
        int Y;
        byte D;
        cNode fatherNode;
        
        private void setNode(final byte x, final byte y, final byte d, final cNode fatherNode) {
            this.X = x;
            this.Y = y;
            this.D = d;
            this.H = Math.abs(this.X - AStar.this.bytEndX) + Math.abs(this.Y - AStar.this.bytEndY);
            this.fatherNode = fatherNode;
        }
        
        private int getG() {
            if (this.fatherNode == null) {
                return this.G = 1;
            }
            return this.G = this.fatherNode.G + 1;
        }
        
        private int getF() {
            return this.getG() + this.H;
        }
    }
}
