package io.github.ricky.cg.model;

import io.github.ricky.cg.constants.MathConstants;
import io.github.ricky.cg.model.enums.ShapeTypeEnum;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/2
 * @className Circle
 * @desc 圆
 */
public final class Circle implements Shape {

    /**
     * 圆心
     */
    private final Point center;

    /**
     * 半径
     */
    private final double radius;

    /**
     * 单位圆
     */
    public static final Circle UNIT_CIRCLE = new Circle(Point.ORIGINAL_POINT, 1.0);

    public Circle(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public Point getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public ShapeTypeEnum type() {
        return ShapeTypeEnum.CIRCLE;
    }

    @Override
    public double area() {
        return MathConstants.PI * radius * radius;
    }

    @Override
    public double perimeter() {
        return 2 * MathConstants.PI * radius;
    }
}
