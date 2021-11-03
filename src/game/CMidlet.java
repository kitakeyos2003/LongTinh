package game;

import javax.microedition.midlet.MIDletStateChangeException;
import base.DCanvas;
import base.Macro;
import base.GameControl;
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;

public class CMidlet extends MIDlet {

    private static CMidlet Mid;
    public static Display DisInstance;

    public CMidlet() {
        CMidlet.Mid = this;
    }

    public static CMidlet getInstance() {
        return CMidlet.Mid;
    }

    protected void startApp() throws MIDletStateChangeException {
        if (CMidlet.DisInstance == null) {
            this.initApp();
        }
        GameControl.getInstance().checkPlatform();
        final String strEmuUrl = getInstance().getAppProperty("EMU_LANDURL");
        if (strEmuUrl != null && strEmuUrl.length() > 0) {
            Macro.URL_LAND = strEmuUrl;
        }
        if (!GameControl.blnIsRun) {
            DCanvas.getInstance().showNotify();
        }
    }

    private void initApp() {
        CMidlet.DisInstance = Display.getDisplay((MIDlet) this);
        GameControl.getInstance().startGame();
    }

    protected void pauseApp() {
        DCanvas.getInstance().hideNotify();
    }

    protected void showNotify() {
        GameControl.blnIsRun = true;
    }

    protected void hideNotify() {
        GameControl.blnIsRun = false;
    }

    protected void destroyApp(final boolean arg0) throws MIDletStateChangeException {
        this.exitGame();
    }

    public void exitGame() {
        this.notifyDestroyed();
    }
}
