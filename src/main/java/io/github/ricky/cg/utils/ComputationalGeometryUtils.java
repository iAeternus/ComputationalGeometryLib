package io.github.ricky.cg.utils;

import io.github.ricky.cg.constants.MathConstants;
import io.github.ricky.cg.model.Point;
import io.github.ricky.cg.model.Segment;
import io.github.ricky.cg.model.Vector;
import io.github.ricky.cg.model.Vector2;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/1
 * @className ComputationalGeometryUtils
 * @desc 计算几何工具类
 */
public class ComputationalGeometryUtils {

    private ComputationalGeometryUtils() {
    }

    // 点的基本运算

    /**
     * 计算两点之间欧式距离
     *
     * @param p1 第一个点
     * @param p2 第二个点
     * @return 两点之间欧式距离
     */
    public static double distance(Point p1, Point p2) {
        Vector2 v = new Vector2(p1, p2);
        return v.modulo();
    }

    /**
     * 计算两个向量op1和op2的叉积
     * 叉积的绝对值为op1和op2围成的平行四边形的面积
     *
     * @param o  共用点
     * @param p1 向量op1的终点
     * @param p2 向量op2的终点
     * @return 两个向量op1和op2的叉积
     * 特别的，令返回值为 r
     * r > 0: p1 在矢量op2的顺时针方向；
     * r = 0: o p1 p2 三点共线；
     * r < 0: p1 在矢量op2的逆时针方向
     */
    public static double cross(Point o, Point p1, Point p2) {
        Vector op1 = new Vector2(o, p1);
        Vector op2 = new Vector2(o, p2);
        return Vector.cross(op1, op2);
    }

    /**
     * 计算两个向量os和oe的叉积的模长
     *
     * @param o  共用点
     * @param p1 向量op1的终点
     * @param p2 向量op2的终点
     * @return 计算两个向量os和oe的叉积的模长
     */
    public static double absCross(Point o, Point p1, Point p2) {
        return Math.abs(cross(o, p1, p2));
    }

    /**
     * 计算两个向量op1和op2的点积
     *
     * @param o  共用点
     * @param p1 向量op1的终点
     * @param p2 向量op2的终点
     * @return 两个向量op1和op2的点积
     * 特别的，令返回值为r
     * r < 0: 两矢量夹角为锐角
     * r = 0: 两矢量夹角为直角
     * r > 0: 两矢量夹角为钝角
     */
    public static double dot(Point o, Point p1, Point p2) {
        Vector op1 = new Vector2(o, p1);
        Vector op2 = new Vector2(o, p2);
        return Vector.dot(op1, op2);
    }

    /**
     * 判断点p是否在线段l上<br>
     * 条件: (p在线段l所在的直线上)&& (点p在以线段l为对角线的矩形内)
     *
     * @param l 线段
     * @param p 点
     * @return true=在线段上 false=不在线段上
     */
    public static boolean online(Segment l, Point p) {
        Point begin = l.getBegin();
        Point end = l.getEnd();
        return cross(begin, p, end) == 0 &&
                (p.getX() - begin.getX()) * (p.getX() - end.getX()) <= 0 &&
                (p.getY() - begin.getY()) * (p.getY() - end.getY()) <= 0;
    }

    /**
     * 返回点p以点o为圆心逆时针旋转alpha(单位：弧度)后所在的位置
     *
     * @param o     起点
     * @param p     终点
     * @param alpha 旋转弧度
     * @return 旋转之后的位置
     */
    public static Point rotate(Point o, Point p, double alpha) {
        Vector2 op = new Vector2(o, p);
        return new Point(
                op.getX() * Math.cos(alpha) - op.getY() * Math.sin(alpha) + o.getX(),
                op.getY() * Math.cos(alpha) + op.getX() * Math.sin(alpha) + o.getY()
        );
    }

    /**
     * 返回顶点在o点，起始边为op1，终止边为op2的夹角(单位：弧度)
     * 可以用于求线段之间的夹角
     *
     * @param o  顶点
     * @param p1 起始边终点
     * @param p2 终止边终点
     * @return 终止边在起始边的顺时针方向，返回负值；否则返回正值
     */
    public static double angle(Point o, Point p1, Point p2) {
        Vector op1 = new Vector2(o, p1);
        Vector op2 = new Vector2(o, p2);

        // 计算两个向量的点积
        double cosPhi = Vector.dot(op1, op2);
        // 计算两个向量模平方的乘积
        double norm = op1.sqrModulo() * op2.sqrModulo();
        // 归一化点积
        cosPhi /= Math.sqrt(norm);

        if (cosPhi >= 1.0) {
            return 0;
        }
        if (cosPhi <= -1.0) {
            return MathConstants.PI;
        }

        double phi = Math.acos(cosPhi);
        if (Vector.cross(op1, op2) < 0) {
            // 终止边在起始边的顺时针方向
            return -phi;
        }
        return phi;
    }

    // 线段及直线的基本运算

    /**
     * 判断点C在线段AB所在的直线l上垂足P的与线段AB的关系
     * r = (AC dot AB) / ||AB||^2
     *
     * @param c 点
     * @param l 线段
     * @return 令返回值为r
     * r = 0    P = A
     * r = 1    P = B
     * r < 0    P is on the forward extension of AB
     * r > 1    P is on the backward extension of AB
     * 0 < r < 1    P is interior to AB
     */
    public static double relation(Point c, Segment l) {
        Vector ac = new Vector2(l.getBegin(), c);
        Vector ab = new Vector2(l.getBegin(), l.getEnd());
        return Vector.dot(ac, ab) / ab.sqrModulo();
    }

    /**
     * 求点 C到线段AB所在直线的垂足 P
     *
     * @param c 点
     * @param l 线段
     * @return 返回垂足P的坐标
     */
    public static Point perpendicular(Point c, Segment l) {
        double r = relation(c, l);
        Point a = l.getBegin();
        Point b = l.getEnd();
        return new Point(
                a.getX() + r * (b.getX() - a.getX()),
                a.getY() + r * (b.getY() - a.getY())
        );
    }

    /**
     * 求线段l上距点p最近的点np<br>
     * 注意：np是线段l上到点p最近的点，不一定是垂足<br>
     *
     * @param p 点
     * @param l 线段
     * @return 线段l上距点p最近的点
     */
    public static Point closestPointToSegment(Point p, Segment l) {
        double r = relation(p, l);
        if (r < 0) {
            return l.getBegin();
        } else if (r > 1) {
            return l.getEnd();
        } else {
            return perpendicular(p, l);
        }
    }

    /**
     * 求点 p到线段l所在直线的距离
     *
     * @param p 点
     * @param l 线段
     * @return 点 p到线段l所在直线的距离
     */
    public static double pointToLineDistance(Point p, Segment l) {
        return Math.abs(cross(l.getBegin(), p, l.getEnd())) / l.length();
    }

}
