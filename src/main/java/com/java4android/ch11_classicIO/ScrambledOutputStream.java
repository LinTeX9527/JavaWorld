package com.java4android.ch11_classicIO;

import org.jetbrains.annotations.NotNull;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author: lintex9527@yeah.net
 * @date: 2018/9/6 16:08
 * @version: 0.1
 *
 * Byte array, file and piped 流 是把直接把直接传送到目的地。而 filter 流是把缓冲区进行 compress/uncompress
 * encrypt/decrypt， 或者其他的对流中字节操作（即把输入进行过滤）然后传送到目的地。
 *
 * filter 输出流，在方法 write() 中操作数据，过滤数据，然后写到底层输出流（底层输出流可以是另外的一个filter 输出流或者其他的输出流）
 * ，例如文件输出流。
 *
 * 使用 filter output stream 必须要继承 FilterOutputStream，子类实例化才可以。
 */


/**
 * 简单的对输出流中的字节进行加密（实际上就是打乱字节顺序）
 */
public class ScrambledOutputStream extends FilterOutputStream {
    private int[] map = null;
    /**
     * Creates an output stream filter built on top of the specified
     * underlying output stream.
     *
     * @param out the underlying output stream to be assigned to
     *            the field <tt>this.out</tt> for later use, or
     *            <code>null</code> if this instance is to be
     *            created without an underlying stream.
     */
    public ScrambledOutputStream(@NotNull OutputStream out, int[] map) {
        super(out);
        if (map == null) {
            throw new NullPointerException("map is null");
        }
        if (map.length != 256) {
            throw new IllegalArgumentException("map.length != 256");
        }
        this.map = map;
    }


    /**
     * 对out输出流中的字节进行加密（简单的打乱字节顺序）
     * 其他的几个write() 方法都是基于这个 write(int) 实现的。
     * @param b
     * @throws IOException
     */
    @Override
    public void write(int b) throws IOException {
        out.write(map[b]); // 扰乱，加解密，压缩，发生在这里
//        out.write(b);      // 如果字节调用out.write(b)就是去了 FilterOutputStream 的作用
        System.out.println("write int");
    }

    @Override
    public void write(@NotNull byte[] b) throws IOException {
        super.write(b);
        System.out.println("write byte");
    }

    @Override
    public void write(@NotNull byte[] b, int off, int len) throws IOException {
        super.write(b, off, len);
        System.out.println("write byte, off, len");
    }


}
