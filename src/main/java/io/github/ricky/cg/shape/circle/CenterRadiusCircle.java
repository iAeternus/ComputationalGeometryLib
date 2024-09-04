package io.github.ricky.cg.shape.circle;

import io.github.ricky.cg.basic.point.Point;
import io.github.ricky.cg.common.constants.MathConstants;
import io.github.ricky.cg.shape.polygon.enums.ShapeTypeEnum;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/2
 * @className CenterRadiusCircle
 * @desc 用圆心和半径表示的圆
 */
public final class CenterRadiusCircle implements Circle {

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
    public static final CenterRadiusCircle UNIT_CENTER_RADIUS_CIRCLE = new CenterRadiusCircle(Point.ORIGINAL_POINT, 1.0);

    public CenterRadiusCircle(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    /**
     * 获取圆心
     *
     * @return 圆心坐标
     */
    @Override
    public Point getCenter() {
        return center;
    }

    /**
     * 获取半径
     *
     * @return 半径
     */
    @Override
    public double getRadius() {
        return radius;
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
        return MathConstants.PI * radius * radius;
    }

    /**
     * 计算周长
     *
     * @return 周长
     */
    @Override
    public double perimeter() {
        return 2 * MathConstants.PI * radius;
    }
}
