package io.github.ricky.cg.model;

import io.github.ricky.cg.model.enums.ShapeTypeEnum;
import io.github.ricky.cg.utils.DoubleUtils;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/2
 * @className Polygon
 * @desc 多边形
 */
public final class Polygon implements Shape {

    /**
     * 顶点集合，按逆时针排列
     */
    private final Point[] vertexes;

    public Polygon(Point[] vertexes) {
        if (vertexes.length == 0) {
            throw new RuntimeException("The polygon has too few vertices.");
        }
        this.vertexes = vertexes;
    }

    public Point[] getVertexes() {
        return vertexes;
    }

    /**
     * 获取多边形顶点个数
     *
     * @return 返回多边形顶点个数
     */
    public int count() {
        return vertexes.length;
    }

    /**
     * 判断顶点是否按逆时针排列
     *
     * @return true=顶点按逆时针排列 false=顶点不按逆时针排列
     */
    public boolean checkVerticesCounterClockwise() {
        return area() > 0;
    }

    @Override
    public ShapeTypeEnum type() {
        return ShapeTypeEnum.POLYGON;
    }

    /**
     * 获取多边形上指定边索引的边
     * 边索引规则：从点索引为0的顶点开始逆时针从0递增
     *
     * @param index 边索引，从0开始
     * @return 边
     */
    public Segment getEdge(int index) {
        int count = count();
        if (count < 2) {
            throw new RuntimeException("There are no edges in this polygon.");
        }
        return new Segment(vertexes[index], vertexes[(index + 1) % count]);
    }

    /**
     * 获取多边形上指定点索引的顶点
     *
     * @param index 点索引，从0开始
     * @return 顶点
     */
    public Point getVertex(int index) {
        if (index < 0 || index >= count()) {
            throw new IndexOutOfBoundsException("Incorrect point indexing.");
        }
        return vertexes[index];
    }

    /**
     * 使用高斯面积公式计算多边形面积
     *
     * @return 多边形面积
     */
    @Override
    public double area() {
        int count = count();
        if (count < 3) {
            return 0;
        }

        double s = vertexes[0].getY() * (vertexes[count - 1].getX() - vertexes[1].getX());
        for (int i = 1; i < count; i++) {
            s += vertexes[i].getY() * (vertexes[i - 1].getX() - vertexes[(i + 1) % count].getX());
        }
        return s / 2;
    }

    @Override
    public double perimeter() {
        double result = 0;
        for (int i = 0; i < vertexes.length; i++) {
            Segment l = getEdge(i);
            result += l.length();
        }
        return result;
    }

    /**
     * 求多边形的重心
     *
     * @return 重心坐标
     */
    public Point gravityCenter() {
        Point p = Point.ORIGINAL_POINT;
        Triangle triangle;
        double s = 0;
        for (int i = 0; i < vertexes.length - 1; i++) {
            triangle = Triangle.newInstance(vertexes[0], vertexes[i], vertexes[i + 1]);
            Point p0 = triangle.gravityCenter(); // 求当前三角形重心
            double triangleArea = triangle.area(); // 求当前三角形面积

            if (DoubleUtils.sgn(s) == 0) {
                p = Point.updatePosition(p0.getX(), p0.getY());
                s += triangleArea;
                continue;
            }
            double k = triangleArea / s; // /求面积比例
            p = Point.updatePosition(
                    (p.getX() + k * p0.getX()) / (1 + k),
                    (p.getY() + k * p0.getY()) / (1 + k)
            );
            s += triangleArea;
        }
        return p;
    }

    /**
     * 求凸多边形的直径<br>
     * 所谓凸多边形的直径，即凸多边形任两个顶点的最大距离。<br>
     * 下面的算法仅耗时O(n)，是一个优秀的算法。<br>
     * 输入必须是一个凸多边形，且顶点必须按顺序（顺时针、逆时针均可）依次输入。<br>
     * 若输入不是凸多边形而是一般点集，则要先求其凸包。<br>
     * 就是先求出所有跖对，然后求出每个跖对的距离，取最大者。点数要多于5个<br>
     *
     * @return 凸多边形的直径
     */
    public double diameter() {
        return 0;
    }
}
