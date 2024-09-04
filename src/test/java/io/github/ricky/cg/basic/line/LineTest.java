package io.github.ricky.cg.basic.line;

import io.github.ricky.cg.basic.point.Point;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/4
 * @className LineTest
 * @desc
 */
class LineTest {

    @Test
    public void lineIntersect() {
        // Given
        Line l1 = new GeneralEquationLine(Point.ORIGINAL_POINT, new Point(10, 10));
        Line l2 = new GeneralEquationLine(new Point(0, 10), new Point(10, 0));
        Line l3 = new GeneralEquationLine(new Point(0, 1), new Point(10, 11));

        // When
        Point res1 = Line.lineIntersect(l1, l2);
        Point res2 = Line.lineIntersect(l1, l3);

        // Then
        System.out.println(res1);
        System.out.println(res2);
        assertThat(res1).isEqualTo(new Point(5, 5));
        assertThat(res2).isNull();
    }

}