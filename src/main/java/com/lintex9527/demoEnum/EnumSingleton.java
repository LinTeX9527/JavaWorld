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

    private String name = null;
    private int age = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

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


    /**
     * 什么叫做单实例？只有一个对象，怎么修改都只对一个单一的对象起作用。
     */
    public static void test_modify() {
        EnumSingleton.INSTANCE.setName("XiaoMing");
        EnumSingleton.INSTANCE.setAge(22);
        System.out.println("当前实例状态：\n" + EnumSingleton.INSTANCE);

        EnumSingleton.INSTANCE.setAge(100);
        System.out.println("当前实例状态：\n" + EnumSingleton.INSTANCE);

        EnumSingleton.INSTANCE.setName("XiaoHong");
        EnumSingleton.INSTANCE.setAge(20);
        System.out.println("当前实例状态：\n" + EnumSingleton.INSTANCE);
    }


    public static void main(String[] args) {
        EnumSingleton.INSTANCE.say(1);
        EnumSingleton.INSTANCE.say(20);
        EnumSingleton.INSTANCE.say(300);

        test_modify();
    }

    @Override
    public String toString() {
        return "name: " + getName() + "\nage: " + getAge();
    }
}
