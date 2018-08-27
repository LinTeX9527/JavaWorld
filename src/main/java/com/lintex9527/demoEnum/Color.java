package com.lintex9527.demoEnum;

/**
 * @author : lintex9527@yeah.net
 * @date : 2018-08-26 2018/8/26
 */

/**
 * Java enum 用法详解：
 * 用法1：常量
 * 有了枚举，可以把相关的常量分组到一个枚举类型里
 *
 * 用法5：实现接口
 * 所有的枚举都继承自 java.lang.Enum 类，由于Java不支持多继承，所以美剧对象不能再继承其他类。
 *
 * 参见：
 * http://www.cnblogs.com/happyPawpaw/archive/2013/04/09/3009553.html
 *
 */
public enum Color implements Behaviour{
    // 用法3：向枚举中添加新方法
    // 如果打算自定义自己的方法，必须在enum实例序列的最后一个添加分号，而且必须先定义enum实例。
    RED("红色", 1), GREEN("绿色", 2), BLANK("白色", 3), YELLOW("黄色", 4);

    // 成员变量
    private String name;
    private int index;

    /**
     * @param name
     * @param index
     */
    Color(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (Color color : Color.values()) {
            if (color.getIndex() == index) {
                return color.getName();
            }
        }
        return null;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


    /**
     * 实现接口中的方法
     */
    @Override
    public void print() {
        System.out.println("打印信息： index = " + index + ", name = " + name);

    }

    @Override
    public String getInfo() {
        return "getInfo() 结果：" + this.name;
    }
}
