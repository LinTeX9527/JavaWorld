package com.lintex9527.demoEnum;

/**
 * @author : lintex9527@yeah.net
 * @date : 2018-08-26 2018/8/26
 *
 * 用法6：使用接口组织枚举
 */
public interface Food {
    enum Coffee implements Food {
        BLACK_COFFEE, DECAF_COFFEE, LATTE, CAPPUCCINO;
    }

    enum Dessert implements Food {
        FRUIT, CAKE, GELATO;
    }
}
