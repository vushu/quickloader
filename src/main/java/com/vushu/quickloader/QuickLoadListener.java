package com.vushu.quickloader;

/*
 * @param file path and name of the current file
 * @param bytesRead current bytes read
 * @param size is the size of the file
 */
public interface QuickLoadListener {
    void onLoading(String file, int bytesRead, int size);
}
