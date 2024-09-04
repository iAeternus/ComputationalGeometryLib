package io.github.ricky.cg.basic.line;

import io.github.ricky.cg.basic.point.Point;
import io.github.ricky.cg.common.utils.DoubleUtils;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/4
 * @className Line
 * @desc 直线
 */
public interface Line {

    /**
     * 获取一般式参数a
     *
     * @return a
     */
    double getA();

    /**
     * 获取一般式参数b
     *
     * @return b
     */
    double getB();

    /**
     * 获取一般式参数c
     *
     * @return c
     */
    double getC();

    /**
     * 计算直线的斜率k
     *
     * @return 水平线返回 0，竖直线返回INF
     */
    double slope();

    /**
     * 计算直线的极角(倾斜角)alpha (0 - pi)
     * 注意：atan()返回的是 -PI/2 ~ PI/2
     *
     * @return 水平线返回 0，竖直线返回 PI / 2
     */
    double alpha();

    /**
     * 计算两条直线的交点
     *
     * @param l1 第一条直线
     * @param l2 第二条直线
     * @return 两条直线相交，返回交点，否则返回null
     */
    static Point lineIntersect(Line l1, Line l2) {
        double d = l1.getA() * l2.getB() - l2.getA() * l1.getB();
        if (DoubleUtils.sgn(d) == 0) {
            return null;
        }
        return new Point(
                (l2.getC() * l1.getB() - l1.getC() * l2.getB()) / d,
                (l2.getA() * l1.getC() - l1.getA() * l2.getC()) / d
        );
    }

}
