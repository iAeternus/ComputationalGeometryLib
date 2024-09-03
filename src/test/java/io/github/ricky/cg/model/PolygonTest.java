package io.github.ricky.cg.model;

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

    final Polygon polygon = new Polygon(new Point[]{
            Point.ORIGINAL_POINT,
            new Point(2, 0),
            new Point(2, 2),
            new Point(0, 2),
    });

    @Test
    public void test() {
        // When
        double area = polygon.area();
        double perimeter = polygon.perimeter();
        Point gravityCenter = polygon.gravityCenter();

        // Then
        System.out.println(area);
        System.out.println(perimeter);
        System.out.println(gravityCenter);
        assertThat(area).isEqualTo(4);
        assertThat(perimeter).isEqualTo(8);
        assertThat(gravityCenter).isEqualTo(new Point(1, 1));
    }

}