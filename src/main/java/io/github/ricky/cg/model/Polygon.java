package io.github.ricky.cg.model;

import io.github.ricky.cg.model.enums.PositionalRelationshipEnum;
import io.github.ricky.cg.model.enums.ShapeTypeEnum;
import io.github.ricky.cg.utils.ComputationalGeometryUtils;

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
        if(vertexes.length == 0) {
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
        for (int i = 0; i < vertexes.length - 1; i++) {
            result += ComputationalGeometryUtils.distance(vertexes[i], vertexes[i + 1]);
        }
        return result;
    }

    /**
     * 获取多边形上指定边索引的边
     * 边索引规则：从点索引为0的顶点开始逆时针从0递增
     * @param index 边索引，从0开始
     * @return 边
     */
    public Segment getEdge(int index) {
        int count = count();
        if(count < 2) {
            throw new RuntimeException("There are no edges in this polygon.");
        }
        return new Segment(vertexes[index], vertexes[(index + 1) % count]);
    }

    /**
     * 获取多边形上指定点索引的顶点
     * @param index 点索引，从0开始
     * @return 顶点
     */
    public Point getVertex(int index) {
        if(index < 0 || index >= count()) {
            throw new IndexOutOfBoundsException("Incorrect point indexing.");
        }
        return vertexes[index];
    }
}
