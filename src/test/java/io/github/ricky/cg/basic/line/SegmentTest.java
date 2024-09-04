package io.github.ricky.cg.basic.line;

import io.github.ricky.cg.basic.point.Point;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/1
 * @className SegmentTest
 * @desc
 */
class SegmentTest {

    @Test
    public void length() {
        // Given
        Segment l = new Segment(Point.ORIGINAL_POINT, new Point(5, 5));

        // When
        double length = l.length();

        // Then
        System.out.println(length);
        assertThat(length).isEqualTo(Math.sqrt(2) * 5);
    }

}