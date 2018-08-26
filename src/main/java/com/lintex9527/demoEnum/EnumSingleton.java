package com.lintex9527.demoEnum;

/**
 * Enum
 *
 * 参见：
 * https://blog.csdn.net/fboypsx/article/details/43527625
 *
 * Enum Singleton 是目前最优的单例模式，好处有三：
 * 1、枚举写法简单；
 * 2、枚举自己出力序列化
 * 3、枚举实例创建是thread-safe
 *
 */
public enum EnumSingleton {
    // 表示单实例，好像必须要放到最前面
    INSTANCE;

    private final String TAG = EnumSingleton.class.getSimpleName();

    /**
     * 私有的构造方法
     */
    private EnumSingleton() {
        System.out.println(TAG + "--私有的无参的构造方法");
    }


    /**
     * 方法
     * @param i
     */
    public void say(int i) {
        System.out.println("i = " + i);
    }


    public static void main(String[] args) {
        EnumSingleton.INSTANCE.say(1);
        EnumSingleton.INSTANCE.say(20);
        EnumSingleton.INSTANCE.say(300);
    }
}
