package io.github.ricky.cg.basic.vector;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/2
 * @className Vector
 * @desc 向量
 */
public interface Vector {

    /**
     * 向量累加
     *
     * @param v 向量
     * @return 自身
     */
    Vector addAndSet(Vector v);

    /**
     * 向量加法
     *
     * @param v 向量
     * @return 计算 this + v，返回新向量
     */
    Vector add(Vector v);

    /**
     * 向量加法
     *
     * @param v1 第一个向量
     * @param v2 第二个向量
     * @return 计算 v1 + v2，返回新向量
     */
    static Vector add(Vector v1, Vector v2) {
        return v1.add(v2);
    }

    /**
     * 向量累减
     *
     * @param v 向量
     * @return 自身
     */
    Vector subtractAndSet(Vector v);

    /**
     * 向量减法
     *
     * @param v 向量
     * @return 计算 this - v，返回新向量
     */
    Vector subtract(Vector v);

    /**
     * 向量减法
     *
     * @param v1 第一个向量
     * @param v2 第二个向量
     * @return 计算 v1 - v2，返回新向量
     */
    static Vector subtract(Vector v1, Vector v2) {
        return v1.subtract(v2);
    }

    /**
     * 向量数乘
     *
     * @param n 数
     * @return 自身
     */
    Vector scalarMultiplyAndSet(double n);

    /**
     * 向量数乘
     *
     * @param n 数
     * @return 计算 this * n，返回新向量
     */
    Vector scalarMultiply(double n);

    /**
     * 向量数乘
     *
     * @param v 向量
     * @param n 数
     * @return 计算 this * n，返回新向量
     */
    static Vector scalarMultiply(Vector v, double n) {
        return v.scalarMultiply(n);
    }

    /**
     * 向量点乘
     *
     * @param v 向量
     * @return 计算 this * v
     */
    double dot(Vector v);

    /**
     * 向量点乘
     *
     * @param v1 第一个向量
     * @param v2 第二个向量
     * @return 计算 v1 * v2
     */
    static double dot(Vector v1, Vector v2) {
        return v1.dot(v2);
    }

    /**
     * 向量叉乘<br>
     * 叉乘的结果是个向量，方向在z轴上<br>
     * 本方法返回它的模<br>
     *
     * @param v 向量
     * @return 计算 |this x v|
     */
    double cross(Vector v);

    /**
     * 向量叉乘<br>
     * 叉乘的结果是个向量，方向在z轴上<br>
     * 本方法返回它的模<br>
     *
     * @param v1 第一个向量
     * @param v2 第二个向量
     * @return 计算 |v1 x v2|
     */
    static double cross(Vector v1, Vector v2) {
        return v1.cross(v2);
    }

    /**
     * 计算向量模长的平方
     *
     * @return 返回自身的模长平方 |x|^2
     */
    double sqrModulo();

    /**
     * 向量取模
     *
     * @return 返回自身的模长 |x|
     */
    double modulo();

    /**
     * 获取自身对应的单位向量
     *
     * @return 单位向量
     */
    Vector unit();

    /**
     * 计算两向量夹角余弦
     *
     * @param v 向量
     * @return 计算 cos this, v
     */
    double cosine(Vector v);

    /**
     * 计算两向量夹角余弦
     *
     * @param v1 第一个向量
     * @param v2 第二个向量
     * @return 计算 cos v1, v2
     */
    static double cosine(Vector v1, Vector v2) {
        return v1.cosine(v2);
    }

}
