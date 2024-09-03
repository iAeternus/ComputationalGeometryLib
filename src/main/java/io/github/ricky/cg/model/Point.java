package io.github.ricky.cg.model;

import io.github.ricky.cg.utils.ComputationalGeometryUtils;
import io.github.ricky.cg.utils.DoubleUtils;

import java.util.Comparator;
import java.util.Objects;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/1
 * @className Point
 * @desc 坐标点
 */
public final class Point {

    /**
     * 横坐标
     */
    private final double x;

    /**
     * 纵坐标
     */
    private final double y;

    /**
     * 原点
     */
    public static final Point ORIGINAL_POINT = new Point(0.0, 0.0);

    public Point(double x, double y) {
        this.x = DoubleUtils.zeroIfNearZero(x);
        this.y = DoubleUtils.zeroIfNearZero(y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public static Point newInstance(double x, double y) {
        return new Point(x, y);
    }

    public static Point updatePosition(double x, double y) {
        return newInstance(x, y);
    }

    /**
     * 坐标点的比较，用于graham扫描法寻找凸包
     */
    public static class PointComparator implements Comparator<Point> {

        private final Point point;

        public PointComparator(Point point) {
            this.point = point;
        }

        @Override
        public int compare(Point o1, Point o2) {
            double cp = ComputationalGeometryUtils.cross(point, o1, o2);
            int sign = DoubleUtils.sgn(cp);
            if (sign != 0) {
                return sign;
            }
            return Double.compare(ComputationalGeometryUtils.distance(point, o1), ComputationalGeometryUtils.distance(point, o2));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Double.compare(x, point.x) == 0 && Double.compare(y, point.y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
