package io.github.ricky.cg.model;

import io.github.ricky.cg.utils.DoubleUtils;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/1
 * @className Line
 * @desc 直线
 * 使用一般式方程 ax + by + c = 0表示
 */
public final class Line {

    private final double a;

    private final double b;

    private final double c;

    public Line() {
        this.a = 1;
        this.b = -1;
        this.c = 0;
    }

    public Line(double a) {
        this.a = DoubleUtils.zeroIfNearZero(a);
        this.b = -1;
        this.c = 0;
    }

    public Line(double a, double b) {
        this.a = DoubleUtils.zeroIfNearZero(a);
        this.b = DoubleUtils.zeroIfNearZero(b);
        this.c = 0;
    }

    public Line(double a, double b, double c) {
        this.a = DoubleUtils.zeroIfNearZero(a);
        this.b = DoubleUtils.zeroIfNearZero(b);
        this.c = DoubleUtils.zeroIfNearZero(c);
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }
}
