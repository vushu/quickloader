package com.vushu.quickloader;

import java.io.IOException;
import java.io.InputStream;

public class QuickLoadInputStream extends InputStream {

    private InputStream in = null;

    private int size = 0;

    private int bytesRead = 0;
    private QuickLoadListener listener;
    private String file;

    public QuickLoadInputStream(InputStream in, int size, String file, QuickLoadListener listener) {
        this.in = in;
        this.size = size;
        this.listener = listener;
        this.file = file;
    }

    private void updateLoader() {
        listener.onLoading(file,bytesRead, size);
    }

    public int available() {
        return (size - bytesRead);
    }

    public int read() throws IOException {
        int b = in.read();

        if (b != -1) {
            bytesRead++;
        }
        updateLoader();
        return b;
    }

    public int read(byte[] b) throws IOException {
        int read = in.read(b);
        bytesRead += read;
        updateLoader();
        return read;
    }

    public int read(byte[] b, int off, int len) throws IOException {
        int read = in.read(b, off, len);
        bytesRead += read;
        updateLoader();
        return read;
    }

    public int getSize() {
        return size;
    }

    public float getPercentLoaded() {
        return (float) bytesRead / (float) size;
    }

    public int getBytesRead() {
        return bytesRead;
    }
}

