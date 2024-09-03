package io.github.ricky.cg.model;

import io.github.ricky.cg.utils.DoubleUtils;

import java.util.Objects;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/1
 * @className Vector
 * @desc 2维向量
 */
public final class Vector2 implements Vector {

    /**
     * x轴分量
     */
    private double x;

    /**
     * y轴分量
     */
    private double y;

    /**
     * 零向量
     */
    public static final Vector2 ZERO = new Vector2(0, 0);

    /**
     * 单位向量
     */
    public static final Vector2 UNIT = new Vector2(1, 1);

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(Point s, Point t) {
        this.x = t.getX() - s.getX();
        this.y = t.getY() - s.getY();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    /**
     * 向量累加
     *
     * @param v 向量
     * @return 自身
     */
    @Override
    public Vector addAndSet(Vector v) {
        if (!(v instanceof Vector2 vector2)) {
            throw new RuntimeException("The vector dimensions do not match.");
        }
        x += vector2.x;
        y += vector2.y;
        return this;
    }

    /**
     * 向量加法
     *
     * @param v 向量
     * @return 计算 this + v，返回新向量
     */
    @Override
    public Vector add(Vector v) {
        if (!(v instanceof Vector2 vector2)) {
            throw new RuntimeException("The vector dimensions do not match.");
        }
        return new Vector2(this.x + vector2.x, this.y + vector2.y);
    }

    /**
     * 向量累减
     *
     * @param v 向量
     * @return 自身
     */
    @Override
    public Vector subtractAndSet(Vector v) {
        if (!(v instanceof Vector2 vector2)) {
            throw new RuntimeException("The vector dimensions do not match.");
        }
        x -= vector2.x;
        y -= vector2.y;
        return this;
    }

    /**
     * 向量减法
     *
     * @param v 向量
     * @return 计算 this - v，返回新向量
     */
    @Override
    public Vector subtract(Vector v) {
        if (!(v instanceof Vector2 vector2)) {
            throw new RuntimeException("The vector dimensions do not match.");
        }
        return new Vector2(x - vector2.x, y - vector2.y);
    }

    /**
     * 向量数乘
     *
     * @param n 数
     * @return 自身
     */
    @Override
    public Vector scalarMultiplyAndSet(double n) {
        x *= n;
        y *= n;
        return this;
    }

    /**
     * 向量数乘
     *
     * @param n 数
     * @return 计算 this * n，返回新向量
     */
    @Override
    public Vector scalarMultiply(double n) {
        return new Vector2(x * n, y * n);
    }

    /**
     * 向量点乘
     *
     * @param v 向量
     * @return 计算 this * v
     */
    @Override
    public double dot(Vector v) {
        if (!(v instanceof Vector2 vector2)) {
            throw new RuntimeException("The vector dimensions do not match.");
        }
        return x * vector2.x + y * vector2.y;
    }

    /**
     * 向量叉乘<br>
     * 叉乘的结果是个向量，方向在z轴上<br>
     * 本方法返回它的模<br>
     *
     * @param v 向量
     * @return 计算 |this x v|
     */
    @Override
    public double cross(Vector v) {
        if (!(v instanceof Vector2 vector2)) {
            throw new RuntimeException("The vector dimensions do not match.");
        }
        return x * vector2.y - vector2.x * y;
    }

    /**
     * 计算向量模长的平方
     *
     * @return 返回自身的模长平方 |x|^2
     */
    @Override
    public double sqrModulo() {
        return x * x + y * y;
    }

    /**
     * 向量取模
     *
     * @return 返回自身的模长 |x|
     */
    @Override
    public double modulo() {
        return Math.sqrt(sqrModulo());
    }

    /**
     * 计算两向量夹角余弦
     *
     * @param v 向量
     * @return 计算 cos(this, v)
     */
    @Override
    public double cosine(Vector v) {
        double m = modulo() * v.modulo();
        if (DoubleUtils.sgn(m) == 0) {
            return 0.0;
        }
        return this.dot(v) / m;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2 vector2 = (Vector2) o;
        return Double.compare(x, vector2.x) == 0 && Double.compare(y, vector2.y) == 0;
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
