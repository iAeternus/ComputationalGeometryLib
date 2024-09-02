package io.github.ricky.cg.utils;

import io.github.ricky.cg.constants.MathConstants;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/1
 * @className DoubleUtils
 * @desc 双精度浮点数工具类
 */
public class DoubleUtils {

    private DoubleUtils() {
    }

    /**
     * 判断浮点数的符号
     *
     * @param number 浮点数
     * @return 若x在误差范围内等于0，返回0
     * 若x < 0，返回-1表示负数
     * 若x > 0，返回1表示正数
     */
    public static int sgn(double number) {
        if (Math.abs(number) < MathConstants.EPS) {
            return 0;
        }
        return number < 0 ? -1 : 1;
    }

    /**
     * 按给定精度处理浮点数
     *
     * @param number 浮点数
     * @return 若x在误差范围内等于0，则返回0；否则什么都不做
     */
    public static double zeroIfNearZero(double number) {
        return sgn(number) == 0 ? 0 : number;
    }

}
