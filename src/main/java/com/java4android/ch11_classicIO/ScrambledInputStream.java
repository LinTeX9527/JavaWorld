package com.java4android.ch11_classicIO;

import org.jetbrains.annotations.NotNull;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author: lintex9527@yeah.net
 * @date: 2018/9/7 11:13
 * @version: 0.1
 */
public class ScrambledInputStream extends FilterInputStream {

    private int[] map;

    public ScrambledInputStream(InputStream in, int[] map) {
        super(in);
        if (map == null) {
            throw new NullPointerException("map is null");
        }
        if (map.length != 256) {
            throw new IllegalArgumentException("map.length != 256");
        }
        this.map = map;
    }

    @Override
    public int read() throws IOException {
        int value = in.read();
        return (value == -1) ? -1 : map[value];
    }


    /**
     * 只需要实现 read() 方法和 read(byte[], int, int)方法，因为
     * read(byte[]) 方法是基于后者实现的。
     * @param b
     * @param off
     * @param len
     * @return
     * @throws IOException
     */
    @Override
    public int read(@NotNull byte[] b, int off, int len) throws IOException {
        int nBytes = in.read(b, off, len);
        if (nBytes <= 0) {
            return nBytes;
        }

        for (int i = 0; i < nBytes; i++) {
            b[off + i] = (byte) map[off + i];
        }

        return nBytes;
    }
}
