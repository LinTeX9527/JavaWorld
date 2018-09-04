package com.java4android.ch11_classicIO;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

/**
 * @author: lintex9527@yeah.net
 * @date: 2018/9/4 11:14
 * @version: 0.1
 *
 * 文件名字过滤器 FilenameFilter
 *
 * 还有文件过滤器 FileFilter。其实 FileFilter 和 FilenameFilter 用法一样，只不过
 */
public class DemoFilenameFilter {
    public static void main(String[] args) {
        String dirname = "F:\\temp";
        String accept = "zip"; // 排除其他类型的文件，只留下后缀名为 zip 的文件

        File dir = new File(dirname);
        FilenameFilter filenameFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(accept); // 条件符合则返回 true
            }
        };

        // 过滤其他的文件，只关注后缀名由accept指定的文件
        String[] results = dir.list(filenameFilter);
        if (results != null) {
            for (String name : results) {
                System.out.println(name);
            }
        }
    }
}
