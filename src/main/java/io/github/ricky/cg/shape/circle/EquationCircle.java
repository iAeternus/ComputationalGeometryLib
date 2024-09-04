package io.github.ricky.cg.shape.circle;

import io.github.ricky.cg.basic.point.Point;
import io.github.ricky.cg.shape.polygon.enums.ShapeTypeEnum;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/4
 * @className EquationCircle
 * @desc 用方程表示的圆<br>
 * ax^2 + by^2 = r^2 TODO
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

    @Override
    public Point getCenter() {
        return null;
    }

    @Override
    public double getRadius() {
        return Math.sqrt(sqrRadius);
    }

    @Override
    public ShapeTypeEnum type() {
        return ShapeTypeEnum.CIRCLE;
    }

    @Override
    public double area() {
        return 0;
    }

    @Override
    public double perimeter() {
        return 0;
    }
}
