package io.github.ricky.cg.basic.line;

import io.github.ricky.cg.basic.point.Point;
import io.github.ricky.cg.common.constants.MathConstants;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/2
 * @className RadialLine
 * @desc 射线
 */
public final class RadialLine extends Segment {

    public RadialLine(Point begin) {
        super(begin, new Point(MathConstants.INF, MathConstants.INF));
    }

}
