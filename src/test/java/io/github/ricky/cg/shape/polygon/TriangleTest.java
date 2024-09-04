package io.github.ricky.cg.shape.polygon;

import io.github.ricky.cg.basic.point.Point;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/3
 * @className TriangleTest
 * @desc
 */
class TriangleTest {

    static Triangle triangle = new Triangle(
            Point.ORIGINAL_POINT,
            new Point(1, 0),
            new Point(1, 1)
    );

    @Test
    public void test1() {
        // When
        double area = triangle.area();
        double perimeter = triangle.perimeter();
        Point gravityCenter = triangle.gravityCenter();

        // Then
        System.out.println(area);
        System.out.println(perimeter);
        System.out.println(gravityCenter);
        assertThat(area).isEqualTo(0.5);
        assertThat(perimeter).isEqualTo(2 + Math.sqrt(2));
        assertThat(gravityCenter).isEqualTo(new Point(2.0 / 3.0, 1.0 / 3.0));
    }

}