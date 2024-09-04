package io.github.ricky.cg.shape.circle;

import io.github.ricky.cg.basic.point.Point;
import io.github.ricky.cg.shape.Shape;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/4
 * @className Circle
 * @desc 圆
 */
public interface Circle extends Shape {

    /**
     * 获取圆心
     * @return 圆心坐标
     */
    Point getCenter();

    /**
     * 获取半径
     * @return 半径
     */
    double getRadius();

}
