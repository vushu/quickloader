# Quickloader

simple loader for jmonkey

## Example

```
package com.vushu.quickloader;
import java.util.logging.Logger;

import com.jme3.app.Application;
import com.jme3.app.state.BaseAppState;
import com.jme3.asset.AssetManager;

public class StateTest extends BaseAppState implements QuickLoadListener {

    public static Logger logger = Logger.getGlobal();
    public AssetManager assetmanager;
    @Override
    protected void initialize(Application app) {
        this.assetmanager = app.getAssetManager();

        //Set listener
        QuickLoader.setListener(this);
        try{
            // looks in resouces folder
            QuickLoader.loadModel("/scenes/level.j3o", assetmanager);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void cleanup(Application app) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void onEnable() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void onDisable() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onLoading(String file, int bytesRead, int size) {
        float percentLoaded = (float) bytesRead / (float) size * 100;
        if (percentLoaded % 1 == 0){
            String loadText = String.format("%.0f %% | %.1f / %.1f kb",percentLoaded, (float) bytesRead/1024, (float) size/1024);
            logger.info(file + ": " + loadText);
        }

    }


}

```


