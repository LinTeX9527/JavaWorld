package com.java4android.ch11_classicIO;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

/**
 * @author: lintex9527@yeah.net
 * @date: 2018/9/3 16:45
 * @version: 0.1
 *
 *
 * File.listRoots() 类File中的静态方法，返回一个File对象数组，包含系统上文件系统根节点，和系统本身的实现有关，
 * 例如Windows返回的是： [C:\, D:\, E:\]，而Linux/Unix平台返回的是/
 */
public class DumpRoots {

    public static void main(String[] args) {
        File[] roots = File.listRoots();
        for (File root: roots) {
            System.out.println(root);
        }

        createFiles();
        createDirs();
    }

    /**
     * 通过 file.createNewFile() 新建文件
     */
    private static void createFiles() {
        // 相对路径
        String path01 = "temp/text01.txt";

        // 绝对路径，以当前工作目录的驱动器为起始路径，如果在D:\盘，则最终的路径是 D:\\temp\\text02.txt
        String path02 = "/temp/text02.txt";

        // 绝对路径
        String path03 = "F:\\temp\\text03.txt";

        File file = new File(path01);
        try {
            if (file.createNewFile()){
                System.out.println("createNewFile()成功新建文件");
            } else {
                System.out.println(file.getName() + "已经存在");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (file.exists()) {
            System.out.println("创建文件成功");
//            file.delete();
        }
    }


    /**
     * file.mkdir() 和 mkdirs() 的区别在于：
     * mkdirs() 会把路径中确实的部分全都创建，而mkdir()检查到某个目录不能存在则新建目录失败。
     *
     * 需要注意 file.delete() 只删除路径中最后的一个目录，其余的都保留。
     * 例如下面的例子只删除了 path02 中的 dir03
     */
    private static void createDirs() {
        String path01 = "temp/dir01";
        String path02 = "temp/testdir/dir01/dir02/dir03";

        File file = new File(path02);
        if (file.exists()) {
            System.out.println(file.getAbsolutePath() + "目录已经存在");
        } else {
            if(file.mkdirs()) {
                System.out.println(file.getAbsolutePath() + "目录新建成功");
            } else {
                System.out.println(file.getAbsolutePath() + "目录新建失败");
            }
        }

//        if (file.delete()) {
//            System.out.println(file.getAbsolutePath() + "目录删除成功");
//        } else {
//            System.out.println(file.getAbsolutePath() + "目录删除失败");
//        }
        File dir = new File("temp/testdir");
        listDirs(dir, 0);
        deleteDirs(new File("temp/testdir"));

//        if (dir.delete()){
//            System.out.println(dir.getAbsolutePath() + "删除成功");
//        } else {
//            System.out.println(dir.getAbsolutePath() + "删除失败");
//        }
    }


    /**
     * 列出目录下的子目录以及文件以及子目录下的所有文件。
     * @param dir 起始目录
     * @param depth 记录目录深度，起始的深度为0
     */
    public static void listDirs(@NotNull File dir, int depth) {
        // 目录下对应的所有子目录和文件列表
        String[] dirContents = null;
        if (dir.exists()) {
            dirContents = dir.list();
            if (depth++ == 0) { // 无论目录深度为几，这条语句执行之后depth必然会加1
                System.out.println(dir.getName() + "/");
            }
            if (null != dirContents) {
                for (String file: dirContents) { // 列出所有的文件
                    for(int i = 0; i < depth; i++) {
                        System.out.print("\t");
                    }
                    System.out.print("|----");
                    File tempfile = new File(dir, file);
                    if (tempfile.isDirectory()) {
                        System.out.println(tempfile.getName() + "/");
                        listDirs(tempfile, depth);
                    } else {
                        System.out.println(tempfile.getName());
                    }
                }
            }
        }
    }


    /**
     * 递归删除整个目录
     * @param dir 起始目录
     * @return
     */
    public static boolean deleteDirs(File dir) {
        boolean retval = true;
        if (dir.exists()){
            File[] dirContents = dir.listFiles();
            if (dirContents != null && dirContents.length > 0) {
                for (File tempfile : dirContents) {
                    if (tempfile.isDirectory()) {
                        deleteDirs(tempfile);       // 删除目录下的目录
                        retval = tempfile.delete(); // 删除这个目录
                    } else { // 删除当前目录中的文件文件
                        retval = tempfile.delete();
                    }
                }
            }
        }
        return retval;
    }

}
