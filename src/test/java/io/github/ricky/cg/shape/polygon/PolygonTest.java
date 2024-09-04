package io.github.ricky.cg.shape.polygon;

import io.github.ricky.cg.basic.point.Point;
import io.github.ricky.cg.shape.polygon.Polygon;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/3
 * @className PolygonTest
 * @desc
 */
class PolygonTest {

    /**
     * 4边形
     */
    final Polygon polygon4 = new Polygon(new Point[]{
            Point.ORIGINAL_POINT,
            new Point(2, 0),
            new Point(2, 2),
            new Point(0, 2),
    });

    @Test
    public void test() {
        // When
        double area = polygon4.area();
        double perimeter = polygon4.perimeter();
        Point gravityCenter = polygon4.gravityCenter();

        // Then
        System.out.println(area);
        System.out.println(perimeter);
        System.out.println(gravityCenter);
        assertThat(area).isEqualTo(4);
        assertThat(perimeter).isEqualTo(8);
        assertThat(gravityCenter).isEqualTo(new Point(1, 1));
    }

    @Test
    public void diameter1() {
        // When
        double diameter = polygon4.diameter();

        // Then
        assertThat(diameter).isEqualTo(2 * Math.sqrt(2));
    }

    @Test
    public void diameter2() {
        // Given
        Polygon polygon20 = new Polygon(20, 1.0, Point.ORIGINAL_POINT);

        // When
        double diameter = polygon20.diameter();

        // Then
        System.out.println(diameter);
        assertThat(diameter).isEqualTo(2.0);
    }

}