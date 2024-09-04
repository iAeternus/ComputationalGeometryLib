package io.github.ricky.cg.utils;

import io.github.ricky.cg.constants.MathConstants;
import io.github.ricky.cg.model.*;
import io.github.ricky.cg.model.enums.PositionalRelationshipEnum;

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
     * 计算两点之间欧氏距离的平方
     *
     * @param p1 第一个点
     * @param p2 第二个点
     * @return 两点之间欧式距离的平方
     */
    public static double sqrDistance(Point p1, Point p2) {
        Vector2 v = new Vector2(p1, p2);
        return v.sqrModulo();
    }

    /**
     * 计算两点之间欧氏距离
     *
     * @param p1 第一个点
     * @param p2 第二个点
     * @return 两点之间欧式距离
     */
    public static double distance(Point p1, Point p2) {
        return Math.sqrt(sqrDistance(p1, p2));
    }

    /**
     * 计算两个向量op1和op2的叉积
     * 叉积的绝对值为op1和op2围成的平行四边形的面积
     *
     * @param o  共用点
     * @param p1 向量op1的终点
     * @param p2 向量op2的终点
     * @return 两个向量op1和op2的叉积<br>
     * 特别的，令返回值为 r<br>
     * r > 0: p1 在矢量op2的顺时针方向；<br>
     * r = 0: o p1 p2 三点共线；<br>
     * r < 0: p1 在矢量op2的逆时针方向<br>
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

    /**
     * 求线段l1与l2之间的夹角
     *
     * @param u 第一条线段
     * @param v 第二条线段
     * @return 线段l1与l2之间的夹角，单位：弧度 范围(-PI，PI)
     */
    public static double segmentAngle(Segment u, Segment v) {
        Point o = Point.ORIGINAL_POINT;
        Point p1 = new Point(u.getEnd().getX() - u.getBegin().getX(), u.getEnd().getY() - u.getBegin().getY());
        Point p2 = new Point(v.getEnd().getX() - v.getBegin().getX(), v.getEnd().getY() - v.getBegin().getY());
        return angle(o, p1, p2);
    }

    /**
     * 判断线段u和v相交(包括相交在端点处)
     *
     * @param u 第一条线段
     * @param v 第二条线段
     * @return true=相交 false=不相交
     */
    public static boolean isIntersect(Segment u, Segment v) {
        // 排斥实验
        if (Math.max(u.getBegin().getX(), u.getEnd().getX()) < Math.min(v.getBegin().getX(), v.getEnd().getX()) ||
                Math.min(u.getBegin().getX(), u.getEnd().getX()) > Math.max(v.getBegin().getX(), v.getEnd().getX()) ||
                Math.max(u.getBegin().getY(), u.getEnd().getY()) < Math.min(v.getBegin().getY(), v.getEnd().getY()) ||
                Math.min(u.getBegin().getY(), u.getEnd().getY()) > Math.max(v.getBegin().getY(), v.getEnd().getY())) {
            return false;
        }

        // 跨立实验
        double d1 = cross(u.getBegin(), u.getEnd(), v.getBegin());
        double d2 = cross(u.getBegin(), u.getEnd(), v.getEnd());
        double d3 = cross(v.getBegin(), v.getEnd(), u.getBegin());
        double d4 = cross(v.getBegin(), v.getEnd(), u.getEnd());

        // 如果d1和d2异号，且d3和d4也异号，则两线段相交
        return d1 * d2 <= 0 && d3 * d4 <= 0;
    }

    /**
     * 判断线段u和v相交（不包括双方的端点）
     *
     * @param u 第一条线段
     * @param v 第二条线段
     * @return true=相交 false=不相交
     */
    public static boolean isIntersectExcludingEndpoints(Segment u, Segment v) {
        return isIntersect(u, v) &&
                !online(u, v.getBegin()) &&
                !online(u, v.getEnd()) &&
                !online(v, u.getBegin()) &&
                !online(v, u.getEnd());
    }

    /**
     * 判断线段u所在直线与线段v相交
     * 方法：判断线段v是否跨立线段u
     *
     * @param u 第一条线段
     * @param v 第二条线段
     * @return true=相交 false=不相交
     */
    public static boolean isCrossingLine(Segment u, Segment v) {
        return cross(u.getBegin(), u.getEnd(), v.getBegin()) * cross(u.getBegin(), u.getEnd(), v.getEnd()) <= 0;
    }

    /**
     * 求点 p关于直线l的对称点
     *
     * @param l 直线
     * @param p 点
     * @return 返回对称点坐标
     */
    public static Point symmetry(Line l, Point p) {
        double a = l.getA();
        double b = l.getB();
        double c = l.getC();
        return new Point(
                ((b * b - a * a) * p.getX() - 2 * a * b * p.getY() - 2 * a * c) / (a * a + b * b),
                ((a * a - b * b) * p.getY() - 2 * a * b * p.getX() - 2 * b * c) / (a * a + b * b)
        );
    }

    /**
     * 计算两条线段的交点
     *
     * @param l1 第一条线段
     * @param l2 第二条线短
     * @return 两条线段相交，返回交点，否则返回null
     */
    public static Point lineIntersect(Segment l1, Segment l2) {
        Line line1 = new GeneralEquationLine(l1);
        Line line2 = new GeneralEquationLine(l2);
        Point point = Line.lineIntersect(line1, line2);
        if (point != null && online(l1, point)) {
            return point;
        }
        return null;
    }

    /**
     * 判断点q与多边形polygon的位置关系
     *
     * @param polygon 多边形
     * @param q       点
     * @return 位置关系
     */
    public static PositionalRelationshipEnum positionalRelationship(Polygon polygon, Point q) {
        RadialLine r = new RadialLine(q);
        int n = polygon.count();
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            Segment l = polygon.getEdge(i);
            if (online(l, q)) {
                return PositionalRelationshipEnum.ONLINE;
            }
            if (isIntersectExcludingEndpoints(r, l)) {
                // 相交且不在端点
                ++cnt;
            }

            if (online(r, l.getEnd()) && !online(r, l.getBegin()) && l.getEnd().getY() > l.getBegin().getY() ||
                    !online(r, l.getEnd()) && online(r, l.getBegin()) && l.getBegin().getY() > l.getEnd().getY()) {
                // l的一个端点在r上且该端点是两端点中纵坐标较大的那个，忽略平行边
                ++cnt;
            }
        }
        if ((cnt & 1) == 1) {
            return PositionalRelationshipEnum.INSIDE;
        } else {
            return PositionalRelationshipEnum.EXTERNAL;
        }
    }

    /**
     * 判断点q在凸多边形polygon内
     * 注意：多边形polygon一定要是凸多边形
     *
     * @param polygon 凸多边形
     * @param q       点
     * @return true=点q在凸多边形polygon内(包括边上)<br>
     * false=点q不在凸多边形polygon内
     */
    public static boolean insideConvexPolygon(final Polygon polygon, Point q) {
        Point p = Point.ORIGINAL_POINT;
        int n = polygon.count();
        // 寻找一个肯定在多边形 polygon 内的点 p：多边形顶点平均值
        for (int i = 0; i < n; i++) {
            p = Point.updatePosition(
                    p.getX() + polygon.getVertex(i).getX(),
                    p.getY() + polygon.getVertex(i).getY()
            );
        }
        p = Point.updatePosition(p.getX() / n, p.getY() / n);

        for (int i = 0; i < n; i++) {
            Segment l = polygon.getEdge(i);
            if (cross(l.getBegin(), p, l.getEnd()) * cross(l.getBegin(), q, l.getEnd()) < 0) {
                // 点p和点q在边l的两侧，说明q点肯定在多边形外
                return false;
            }
        }
        return true;
    }

    /**
     * 寻找凸包-graham扫描法
     *
     * @param pointSet 点集
     * @return 返回凸包上的点集，按逆时针方向排列
     */
    public static Point[] findingConvexHulls(Point[] pointSet) {
        int n = pointSet.length;
        if (n < 3) {
            return null; // 凸包至少需要3个点
        }

        // 找到y坐标最小的点，如果y坐标相同，则取x坐标最小的点
        int k = 0;
        for (int i = 1; i < n; i++) {
            if (pointSet[i].getY() < pointSet[k].getY() || (pointSet[i].getY() == pointSet[k].getY() && pointSet[i].getX() < pointSet[k].getX())) {
                k = i;
            }
        }

        // 将最小点交换到数组的第一个位置
        Point tmp = pointSet[0];
        pointSet[0] = pointSet[k];
        pointSet[k] = tmp;

        // 根据与p0的极角排序，这里使用稳定的冒泡排序
        for (int i = 1; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (cross(pointSet[0], pointSet[j], pointSet[i]) > 0 || (cross(pointSet[0], pointSet[j], pointSet[i]) == 0 &&
                        distance(pointSet[0], pointSet[j]) < distance(pointSet[0], pointSet[i]))) {
                    tmp = pointSet[i];
                    pointSet[i] = pointSet[j];
                    pointSet[j] = tmp;
                }
            }
        }

        // 构建凸包，这里使用数组模拟栈
        Point[] hull = new Point[n];
        int top = 2;
        hull[0] = pointSet[0];
        hull[1] = pointSet[1];
        hull[2] = pointSet[2];

        for (int i = 3; i < n; i++) {
            while (top >= 2 && cross(pointSet[i], hull[top], hull[top - 1]) >= 0) {
                top--;
            }
            hull[++top] = pointSet[i];
        }

        // 裁剪hull数组
        Point[] result = new Point[top + 1];
        System.arraycopy(hull, 0, result, 0, top + 1);

        return result;
    }
}

