package io.github.ricky.cg.basic.line;

import io.github.ricky.cg.basic.point.Point;
import io.github.ricky.cg.common.constants.MathConstants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/3
 * @className GeneralEquationLineTest
 * @desc
 */
class GeneralEquationLineTest {

    @Test
    @DisplayName("k = 2")
    public void test1() {
        // Given
        Point p1 = new Point(0, 1);
        Point p2 = new Point(-0.5, 0);

        // When
        Line l = new GeneralEquationLine(p1, p2);
        double slope = l.slope();
        double alpha = l.alpha();

        // Then
        System.out.println(l);
        System.out.println(slope);
        System.out.println(alpha);
        assertThat(l.toString()).isEqualTo("x-0.5y+0.5 = 0");
        assertThat(slope).isEqualTo(2.0);
        assertThat(alpha).isEqualTo(Math.atan(2));
    }

    @Test
    @DisplayName("k = 0")
    public void test2() {
        // Given
        Point p1 = new Point(0, 1);
        Point p2 = new Point(1, 1);

        // When
        Line l = new GeneralEquationLine(p1, p2);
        double slope = l.slope();
        double alpha = l.alpha();

        // Then
        System.out.println(l);
        System.out.println(slope);
        System.out.println(alpha);
        assertThat(l.toString()).isEqualTo("y+1.0 = 0");
        assertThat(slope).isEqualTo(0.0);
        assertThat(alpha).isEqualTo(0.0);
    }

    @Test
    @DisplayName("k = INF")
    public void test3() {
        // Given
        Point p1 = new Point(1, 0);
        Point p2 = new Point(1, 1);

        // When
        Line l = new GeneralEquationLine(p1, p2);
        double slope = l.slope();
        double alpha = l.alpha();

        // Then
        System.out.println(l);
        System.out.println(slope);
        System.out.println(alpha);
        assertThat(l.toString()).isEqualTo("x-1.0 = 0");
        assertThat(slope).isEqualTo(MathConstants.INF);
        assertThat(alpha).isEqualTo(MathConstants.PI / 2);
    }

}