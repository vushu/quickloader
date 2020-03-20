package com.vushu.quickloader;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Paths;
import java.util.logging.Logger;

import com.jme3.asset.AssetManager;
import com.jme3.asset.MaterialKey;
import com.jme3.asset.ModelKey;
import com.jme3.asset.TextureKey;
import com.jme3.material.Material;
import com.jme3.scene.Spatial;
import com.jme3.texture.Texture;

/**
 * QuickLoader
 */
public class QuickLoader {

    private static QuickLoadListener listener;
    private static Logger logger = Logger.getLogger(QuickLoader.class.getName());

    /*
     * @param path is the specified path to file
     */
    private static QuickLoadInputStream getFile(String path) {
        return load(path);
    }

    public static String greet(String name) {
        return "Hi " + name;
    }

    public static Spatial loadModel(String path, AssetManager assetManager) {

        Spatial model = null;

        var file = Paths.get(path).getFileName().toString();
        var in = getFile(path);

        var key = new ModelKey(file);
        model = assetManager.loadAssetFromStream(key, in);
        closeStream(in);
        return model;
    }

    public static Material loadMaterial(String path, AssetManager assetManager) {
        var file = Paths.get(path).getFileName().toString();
        var in = getFile(path);
        var key = new MaterialKey(file);
        var tex = assetManager.loadAssetFromStream(key, in);
        closeStream(in);
        return tex;
    }


    public static Texture loadTexture(String path, AssetManager assetManager) {
        var file = Paths.get(path).getFileName().toString();
        var in = getFile(path);
        var key = new TextureKey(file);
        var tex = assetManager.loadAssetFromStream(key, in);
        closeStream(in);
        return tex;
    }


    private static void closeStream(InputStream in) {
        try {
            in.close();
        } catch(Exception e){
            logger.severe(e.getMessage());
        }
    }

    private static QuickLoadInputStream load(String path) {

        try {
            URL url = QuickLoader.class.getResource(path);
            if (url == null) {
                logger.severe("Failed path doesn't exist maybe add / before");
                return null;
            }
            URLConnection connection = url.openConnection();
            if (connection == null) {
                logger.info("Connection url is null!");
                return null;
            }
            int size = connection.getContentLength();
            InputStream inputStream = connection.getInputStream();
            if (QuickLoader.listener == null) {
                logger.info("Please add listener first!");
                return null;
            }
            QuickLoadInputStream sizeInputStream = new QuickLoadInputStream(inputStream, size, path, listener);
            return sizeInputStream;

        } catch(IOException e){
            e.printStackTrace();
        }
        return null;

    }

    public static QuickLoadListener getListener() {
        return listener;
    }

    public static void setListener(QuickLoadListener listener) {
        QuickLoader.listener = listener;
    }

}
