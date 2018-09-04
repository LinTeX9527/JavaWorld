package com.java4android.ch11_classicIO;

import java.io.IOError;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author: lintex9527@yeah.net
 * @date: 2018/9/4 13:57
 * @version: 0.1
 */

/**
 * 用RandomAccessFile实现Flat file database
 */
public class PartsDB {
    // 表示记录中各个部分的长度
    // 前面的两个成员，看做字符串格式，一个字符咱两个字节 character = 2 bytes
    public static final int PNUMLEN = 20;
    public static final int DESCLEN = 30;

    // 后面的两个成员看做int，占据4个字节
    public static final int QUANLEN = 4;
    public static final int COSTLEN = 4;

    // 一条记录总的字节个数
    private final static int RECLEN = 2 * PNUMLEN + 2 * DESCLEN + QUANLEN + COSTLEN;
    // 代表随机访问的文件对象
    private RandomAccessFile raf;

    /**
     * 构造方法，新建一个RandomAccessFile 对象
     * @param pathname 文件路径
     * @throws IOException
     */
    public PartsDB(String pathname) throws IOException {
        raf = new RandomAccessFile(pathname, "rw");
    }


    /**
     * 向文件中添加一条记录
     * @param partnum
     * @param partdesc
     * @param qty
     * @param ucost
     * @throws IOException
     */
    public void append(String partnum, String partdesc, int qty, int ucost) throws IOException{
        // 定位到文件末尾
        raf.seek(raf.length());
        write(partnum, partdesc, qty, ucost);
    }


    /**
     * 关闭这个数据库对象，即这个文件
     */
    public void close() {
        try {
            raf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 查询这个数据库中存放的记录个数
     * @return 文件中存放的记录个数
     * @throws IOException
     */
    public int numRecs() throws IOException {
        return (int) raf.length() / RECLEN;
    }


    /**
     * 读取指定序号的记录
     * @param recno 记录的序号（下标从0开始）
     * @return
     * @throws IOException
     */
    public Part select(int recno) throws IOException {
        if (recno < 0 || recno >= numRecs()) {
            throw new IllegalArgumentException(recno + " out of range");
        }
        raf.seek(recno * RECLEN);
        return read();
    }


    /**
     * 更新指定序号的记录
     * @param recno    记录的序号
     * @param partnum
     * @param partdesc
     * @param qty
     * @param ucost
     * @throws IOException
     */
    public void update(int recno, String partnum, String partdesc, int qty, int ucost) throws IOException {
        if (recno < 0 || recno >= numRecs()) {
            throw new IllegalArgumentException(recno + " out of range");
        }
        raf.seek(recno * RECLEN);
        write(partnum, partdesc, qty, ucost);
    }


    /**
     * 读取一条记录
     * @return 读取一条记录，并返回一个记录的类对象
     * @throws IOException
     */
    private Part read() throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < PNUMLEN; i++) {
            sb.append(raf.readChar()); // 一个Char占用两个字节
        }
        String partnum = sb.toString().trim(); // 从这里知道记录的长度 RECLEN 的强两个部分为什么要乘以2
        sb.setLength(0);

        for (int i = 0; i < DESCLEN; i++) {
            sb.append(raf.readChar());
        }
        String partdesc = sb.toString().trim();
        sb.setLength(0);

        int qty = raf.readInt();
        int ucost = raf.readInt();
        return new Part(partnum, partdesc, qty, ucost);
    }


    /**
     * 插入一条记录，按字节的方式写入到文本中
     * @param partnum
     * @param partdesc
     * @param qty
     * @param ucost
     * @throws IOException
     */
    private void write(String partnum, String partdesc, int qty, int ucost) throws IOException{
        // 截断超出指定的长度部分，如不哦不足的话就填充空格
        StringBuffer sb = new StringBuffer(partnum);
        if (sb.length() > PNUMLEN) {
            sb.setLength(PNUMLEN);
        }
        if (sb.length() < PNUMLEN) { // 不足的部分补空格
            int len = PNUMLEN - sb.length();
            for (int i = 0; i < len; i++) {
                sb.append(" ");
            }
        }
        // 按字节的方式写入到文本中，需要注意一个character占据2个字节
        raf.writeChars(sb.toString());

        sb = new StringBuffer(partdesc);
        if (sb.length() > DESCLEN) {
            sb.setLength(DESCLEN);
        }
        if (sb.length() < DESCLEN) {
            int len = DESCLEN - sb.length();
            for (int i = 0; i < len; i++) {
                sb.append(" ");
            }
        }
        raf.writeChars(sb.toString());

        // 写入一个int数据，一个int占据4个字节
        raf.writeInt(qty);
        raf.writeInt(ucost);
    }


    /**
     * 一个类，表示一条记录
     */
    public static class Part {
        private String partnum;
        private String desc;
        private int qty;
        private int ucost;

        public Part(String partnum, String desc, int qty, int ucost) {
            this.partnum = partnum;
            this.desc = desc;
            this.qty = qty;
            this.ucost = ucost;
        }

        public String getPartnum() {
            return partnum;
        }

        public void setPartnum(String partnum) {
            this.partnum = partnum;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }

        public int getUcost() {
            return ucost;
        }

        public void setUcost(int ucost) {
            this.ucost = ucost;
        }
    }
}
