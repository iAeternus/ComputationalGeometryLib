package io.github.ricky.cg.model;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/4
 * @className DirectionVectorLine
 * @desc 点向式直线
 */
public class DirectionVectorLine implements Line {

    /**
     * 在直线上的一个点
     */
    private Point point;

    /**
     * 方向向量
     */
    private Vector2 direction;

    /**
     * 构造 y = x
     */
    public DirectionVectorLine() {
        this.point = Point.ORIGINAL_POINT;
        this.direction = Vector2.UNIT;
    }

    /**
     * 构造经过原点的直线
     */
    public DirectionVectorLine(Vector2 direction) {
        this.point = Point.ORIGINAL_POINT;
        this.direction = direction;
    }

    public DirectionVectorLine(Point point, Vector2 direction) {
        this.point = point;
        this.direction = direction;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Vector2 getDirection() {
        return direction;
    }

    public void setDirection(Vector2 direction) {
        this.direction = direction;
    }

    @Override
    public double getA() {
        return direction.getY();
    }

    @Override
    public double getB() {
        return -direction.getX();
    }

    @Override
    public double getC() {
        return direction.getX() * point.getY() - direction.getY() * point.getX();
    }

    @Override
    public double slope() {
        return direction.tanAlpha();
    }

    @Override
    public double alpha() {
        return direction.alpha();
    }
}
