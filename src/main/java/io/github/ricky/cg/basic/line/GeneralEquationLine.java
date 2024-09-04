package io.github.ricky.cg.basic.line;

import io.github.ricky.cg.basic.point.Point;
import io.github.ricky.cg.common.constants.MathConstants;
import io.github.ricky.cg.common.utils.DoubleUtils;

import java.util.Objects;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/1
 * @className GeneralEquationLine
 * @desc 直线
 * 使用一般式方程 ax + by + c = 0表示
 */
public final class GeneralEquationLine implements Line {

    private double a;

    private double b;

    private double c;

    /**
     * 构造 y = x
     */
    public GeneralEquationLine() {
        this.a = 1;
        this.b = -1;
        this.c = 0;
    }

    /**
     * 构造 y = ax
     */
    public GeneralEquationLine(double a) {
        this.a = DoubleUtils.zeroIfNearZero(a);
        this.b = -1;
        this.c = 0;
    }

    /**
     * 构造经过原点的直线
     */
    public GeneralEquationLine(double a, double b) {
        this.a = DoubleUtils.zeroIfNearZero(a);
        this.b = DoubleUtils.zeroIfNearZero(b);
        this.c = 0;
    }

    public GeneralEquationLine(double a, double b, double c) {
        this.a = DoubleUtils.zeroIfNearZero(a);
        this.b = DoubleUtils.zeroIfNearZero(b);
        this.c = DoubleUtils.zeroIfNearZero(c);
    }

    /**
     * 根据已知两点坐标，求过这两点的直线解析方程： ax+by+c = 0  (a >= 0)
     * (y2 - y1)x + (x1 - x2)y + (y1x2 - x1y2) = 0
     *
     * @param p1 第一个点
     * @param p2 第二个点
     */
    public GeneralEquationLine(Point p1, Point p2) {
        double sign = 1.0;
        a = p2.getY() - p1.getY();
        if (a < 0) {
            sign = -1.0;
            a *= sign;
        }
        b = sign * (p1.getX() - p2.getX());
        c = sign * (p1.getY() * p2.getX() - p1.getX() * p2.getY());
    }

    /**
     * 使用线段构造直线
     *
     * @param segment 线段
     */
    public GeneralEquationLine(Segment segment) {
        this(segment.getBegin(), segment.getEnd());
    }

    @Override
    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    @Override
    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    @Override
    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    /**
     * 计算直线的斜率k
     *
     * @return 水平线返回 0，竖直线返回INF
     */
    @Override
    public double slope() {
        if (DoubleUtils.sgn(a) == 0) {
            return 0.0;
        }
        if (DoubleUtils.sgn(b) == 0) {
            return MathConstants.INF;
        }
        return -(a / b);
    }

    /**
     * 计算直线的倾斜角alpha (0 - pi)
     * 注意：atan()返回的是 -PI/2 ~ PI/2
     *
     * @return 水平线返回 0，竖直线返回 PI / 2
     */
    @Override
    public double alpha() {
        if (DoubleUtils.sgn(a) == 0) {
            return 0.0;
        }
        if (DoubleUtils.sgn(b) == 0) {
            return MathConstants.PI / 2;
        }
        double k = slope();
        double alpha = Math.atan(k);
        return k > 0 ? alpha : MathConstants.PI + alpha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeneralEquationLine generalEquationLine = (GeneralEquationLine) o;
        return Double.compare(a, generalEquationLine.a) == 0 && Double.compare(b, generalEquationLine.b) == 0 && Double.compare(c, generalEquationLine.c) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        // 处理 x 项
        int signA = DoubleUtils.sgn(a);
        if (signA != 0) {
            stringBuilder.append(signA > 0 ? "+" : "").append(Math.abs(a) == 1 ? "x" : a + "x");
        }

        // 处理 y 项
        int signB = DoubleUtils.sgn(b);
        if (signB != 0) {
            if (!stringBuilder.isEmpty() && signA * signB > 0) {
                // 如果前面有项且符号相同，则添加加号
                stringBuilder.append("+");
            }
            stringBuilder.append(signB > 0 ? "+" : "").append(Math.abs(b) == 1 ? "y" : b + "y");
        }

        // 处理常数项
        int signC = DoubleUtils.sgn(c);
        if (signC != 0) {
            if (!stringBuilder.isEmpty()) {
                // 如果前面有项，则添加适当的符号
                stringBuilder.append(signC < 0 ? "-" : "+");
            }
            // 如果 c 的绝对值不是 1，则直接输出 c；否则，如果 c 是 0，则不输出
            if (Math.abs(c) != 1 || c != 0) {
                stringBuilder.append(Math.abs(c));
            }
            stringBuilder.append(" = 0");
        } else if (stringBuilder.isEmpty()) {
            // 如果 a, b, c 都是 0，则输出 "0 = 0"
            stringBuilder.append("0 = 0");
        }

        // 移除开头的多余加号
        if (!stringBuilder.isEmpty() && stringBuilder.charAt(0) == '+') {
            stringBuilder.deleteCharAt(0);
        }

        return stringBuilder.toString();
    }
}
