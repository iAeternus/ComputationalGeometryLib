package io.github.ricky.cg.common.utils;

import io.github.ricky.cg.common.constants.MathConstants;

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
     * 比较两个浮点数
     *
     * @param num1 第一个浮点数
     * @param num2 第二个浮点数
     * @return 若两个浮点数在误差阈值内相等，返回0.0
     * 若num1 < num2，返回负数
     * 若num1 > num2，返回正数
     */
    public static double cmp(double num1, double num2) {
        double cmp = num1 - num2;
        return Math.abs(cmp) < MathConstants.EPS ? 0.0 : cmp;
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
        if (cmp(number, 0) == 0.0) {
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

    /**
     * 计算倒数
     *
     * @param number 浮点数
     * @return 倒数
     */
    public static double reciprocal(double number) {
        if (number == 0) {
            throw new RuntimeException("Divided by 0.");
        }
        return zeroIfNearZero(1.0 / number);
    }

}
