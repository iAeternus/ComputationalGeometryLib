package io.github.ricky.cg.shape.circle;

import io.github.ricky.cg.basic.point.Point;
import io.github.ricky.cg.shape.polygon.enums.ShapeTypeEnum;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/4
 * @className EquationCircle
 * @desc 用方程表示的圆<br>
 * (x - a)^2 + (y - b)^2 = r^2
 */
public class EquationCircle implements Circle {

    /**
     * 系数a
     */
    private double a;

    /**
     * 系数b
     */
    private double b;

    /**
     * 半径平方
     */
    private double sqrRadius;

    /**
     * 获取圆心
     *
     * @return 圆心坐标
     */
    @Override
    public Point getCenter() {
        return null;
    }

    /**
     * 获取半径
     *
     * @return 半径
     */
    @Override
    public double getRadius() {
        return Math.sqrt(sqrRadius);
    }

    /**
     * 获取几何图形的类型
     *
     * @return 几何图形的类型
     */
    @Override
    public ShapeTypeEnum type() {
        return ShapeTypeEnum.CIRCLE;
    }

    /**
     * 计算面积
     *
     * @return 面积
     */
    @Override
    public double area() {
        return 0;
    }

    /**
     * 计算周长
     *
     * @return 周长
     */
    @Override
    public double perimeter() {
        return 0;
    }
}
