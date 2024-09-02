package io.github.ricky.cg.model;

import io.github.ricky.cg.constants.MathConstants;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/2
 * @className RadialLine
 * @desc 射线
 */
public class RadialLine extends Segment {

    public RadialLine(Point begin) {
        super(begin, new Point(MathConstants.INF, MathConstants.INF));
    }

}
