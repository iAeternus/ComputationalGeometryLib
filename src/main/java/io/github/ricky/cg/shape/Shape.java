package io.github.ricky.cg.shape;

import io.github.ricky.cg.shape.polygon.enums.ShapeTypeEnum;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/2
 * @className Shape
 * @desc 平面图形接口
 */
public interface Shape {

    /**
     * 获取几何图形的类型
     *
     * @return 几何图形的类型
     */
    ShapeTypeEnum type();

    /**
     * 计算面积
     *
     * @return 面积
     */
    double area();

    /**
     * 计算周长
     *
     * @return 周长
     */
    double perimeter();

}
