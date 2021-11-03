// 
// Decompiled by Procyon v0.5.36
// 
package model;

import javax.microedition.lcdui.Graphics;
import java.util.Random;
import means.ImageManager;
import javax.microedition.lcdui.Image;

public class Pet {

    public Image imgDrawPet;
    public short shtImgWidth;
    public short shtImgHeight;
    public short shtDrawX;
    public short shtDrawY;
    public byte bytDrawType;
    public byte bytDrawReflect;
    private int bytFrameIndex;
    private byte bytPetNowStep;
    private byte bytPetMaxStep;
    private static Pet pet;

    public Pet() {
        Pet.pet = this;
    }

    public static Pet getInstance() {
        return Pet.pet;
    }

    public void clear() {
        Pet.pet = null;
    }

    public void setPetFlash(final short _id) {
        this.bytFrameIndex = 0;
        this.imgDrawPet = ImageManager.CreateImage(new StringBuffer(String.valueOf(_id)).toString(), "pet");
        this.shtImgWidth = (short) this.imgDrawPet.getWidth();
        this.shtImgHeight = (short) this.imgDrawPet.getHeight();
        this.randomStep();
    }

    private void randomStep() {
        this.bytPetNowStep = 0;
        this.bytPetMaxStep = (byte) Math.abs(new Random().nextInt() % 3 + 1);
        this.bytDrawType = (byte) Math.abs(new Random().nextInt() % 3);
        this.bytDrawReflect = (byte) ((Math.abs(new Random().nextInt() % 4) <= 1) ? 0 : 2);
    }

    public void logicPetFlash() {
        ++this.bytFrameIndex;
        if (this.bytFrameIndex > 6) {
            this.bytFrameIndex = 0;
            ++this.bytPetNowStep;
            if (this.bytPetNowStep >= this.bytPetMaxStep) {
                this.randomStep();
            }
        }
    }

    public void drawPetFlash(final Graphics g) {
    }
}
