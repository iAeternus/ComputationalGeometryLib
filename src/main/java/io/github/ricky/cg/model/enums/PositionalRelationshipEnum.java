package io.github.ricky.cg.model.enums;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/2
 * @className PositionalRelationshipEnum
 * @desc 位置关系枚举
 */
public enum PositionalRelationshipEnum {

    /**
     * 点在多边形内
     */
    INCLUDE,

    /**
     * 点在多边形边上
     */
    ONLINE,

    /**
     * 点在多边形外
     */
    EXTERNAL,
    ;

}
