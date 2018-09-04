package com.java4android.ch11_classicIO;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * @author: lintex9527@yeah.net
 * @date: 2018/9/4 14:22
 * @version: 0.1
 */
public class UsePartsDB {
    public static void main(String[] args) {
        PartsDB pdb = null;
        try {
            pdb = new PartsDB("temp/parts.db");
            if (pdb.numRecs() == 0) {
                // 填充数据
                pdb.append("1-9009-3323-4x", "Wiper Blade Micro Edge", 30, 2468);
                pdb.append("1-3233-44923-7j", "Parking Brake Cable", 5, 1439);
                pdb.append("2-3399-6693-2m", "Halogen Bulb H4 55/60W", 22, 813);
                pdb.append("2-599-2029-6k", "Turbo Oil Line O-Ring", 26, 155);
                pdb.append("3-1299-3299-9u", "Air Pump Electric", 9, 20200);
            }

            dumpRecords(pdb);
            pdb.update(1, "1-3233-44923-7j", "Parking Brake Cable", 5, 1995);
            pdb.append("1-2392-6712-3331", "Halogen Brake Micro Wiper AirTurbo Cable", 12345, 12345);
            pdb.append("1-2392-6712-3331-78MO", "O-Ring Air Pump", 54321, 54321);
            dumpRecords(pdb);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (pdb != null) {
                pdb.close();
            }
        }
    }

    /**
     * 打印数据库中所有的记录
     * @param pdb
     * @throws IOException
     */
    private static void dumpRecords(@NotNull PartsDB pdb) throws IOException {
        for (int i = 0; i < pdb.numRecs(); i++) {
            PartsDB.Part part = pdb.select(i);
            System.out.print(format(part.getPartnum(), PartsDB.PNUMLEN, true));
            System.out.print(" | ");
            System.out.print(format(part.getDesc(), PartsDB.DESCLEN, true));
            System.out.print(" | ");
            System.out.print(format("" + part.getQty(), 10, false));
            System.out.print(" | ");
            String s = part.getUcost() / 100 + "." + part.getUcost() % 100;
            if (".".equals(s.charAt(s.length() - 2))) s += "0";
            System.out.println(format(s, 10, false));
        }
        System.out.println("Number of records = " + pdb.numRecs());
        System.out.println();
    }

    private static String format(@NotNull String value, int maxWidth, boolean leftAlign) {
        StringBuilder sb = new StringBuilder();
        int len = value.length();
        if (len > maxWidth) {
            len = maxWidth;
            value = value.substring(0, len);
        }
        if (leftAlign) { // 左对齐，就在右边填充空格
            sb.append(value);
            for (int i = 0; i < maxWidth - len; i++) {
                sb.append(" ");
            }
        } else { // 右对齐，就在左边填充空格
            for (int i = 0; i < maxWidth - len; i++) {
                sb.append(" ");
            }
            sb.append(value);
        }
        return sb.toString();
    }
}
