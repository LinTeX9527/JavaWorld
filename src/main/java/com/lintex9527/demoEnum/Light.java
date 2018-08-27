package com.lintex9527.demoEnum;

/**
 * @author : lintex9527@yeah.net
 * @date : 2018-08-26 2018/8/26
 */
public enum Light {
    // 利用构造函数传参数
    RED(1), GREEN(3), YELLOW(2);

    // 定义私有变量
    private int nCode;


    /**
     * 构造方法，枚举类型只能为私有
     * @param _nCode
     */
    private Light(int _nCode) {
        this.nCode = _nCode;
    }


    @Override
    public String toString() {
        return "对应的值 = " + String.valueOf(this.nCode);
    }
}
