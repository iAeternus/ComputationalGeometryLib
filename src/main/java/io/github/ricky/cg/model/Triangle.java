package io.github.ricky.cg.model;

import io.github.ricky.cg.model.enums.ShapeTypeEnum;
import io.github.ricky.cg.utils.ComputationalGeometryUtils;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/3
 * @className Triangle
 * @desc
 */
public final class Triangle implements Shape {

    /**
     * 第一个点
     */
    private final Point a;

    /**
     * 第二个点
     */
    private final Point b;

    /**
     * 第三个点
     */
    private final Point c;

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Point getA() {
        return a;
    }

    public Point getB() {
        return b;
    }

    public Point getC() {
        return c;
    }

    public static Triangle newInstance(Point a, Point b, Point c) {
        return new Triangle(a, b, c);
    }

    @Override
    public ShapeTypeEnum type() {
        return ShapeTypeEnum.TRIANGLE;
    }

    @Override
    public double area() {
        return ComputationalGeometryUtils.cross(a, b, c) / 2;
    }

    @Override
    public double perimeter() {
        Segment ab = new Segment(a, b);
        Segment ac = new Segment(a, c);
        Segment bc = new Segment(b, c);
        return ab.length() + ac.length() + bc.length();
    }

    /**
     * 求三角形重心
     *
     * @return 重心坐标
     */
    public Point gravityCenter() {
        return new Point((a.getX() + b.getX() + c.getX()) / 3, (a.getY() + b.getY() + c.getY()) / 3);
    }
}
