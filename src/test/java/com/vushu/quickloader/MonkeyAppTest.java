package com.vushu.quickloader;
import java.util.logging.Logger;

import com.jme3.app.SimpleApplication;

public class MonkeyAppTest extends SimpleApplication {

    public static Logger logger = Logger.getGlobal();

    @Override
    public void simpleInitApp() {
        this.getStateManager().attach(new StateTest());
    }

    public void hej() {
        logger.info("HEJ");
    }

}
