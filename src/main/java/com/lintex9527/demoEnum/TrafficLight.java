package com.lintex9527.demoEnum;

/**
 * @author : lintex9527@yeah.net
 * @date : 2018-08-26 2018/8/26
 */

/**
 * Java enum 用法2： switch
 *
 */
public class TrafficLight {
    private Signal color = Signal.RED;

    public void change() {
        switch (color) {
            case RED:
                color = Signal.GREEN;
                break;
            case GREEN:
                color = Signal.YELLOW;
                break;
            case YELLOW:
                color = Signal.RED;
                break;
        }
    }
}


enum Signal {
    GREEN, YELLOW, RED
}
