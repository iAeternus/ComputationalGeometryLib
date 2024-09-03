package io.github.ricky.cg.model;

import io.github.ricky.cg.constants.MathConstants;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/2
 * @className CircleTest
 * @desc
 */
class CircleTest {

    @Test
    public void area() {
        // Given
        Circle o = Circle.UNIT_CIRCLE;

        // When
        double area = o.area();

        // Then
        System.out.println(area);
        assertThat(area).isEqualTo(MathConstants.PI);
    }

    @Test
    public void perimeter() {
        // Given
        Circle o = Circle.UNIT_CIRCLE;

        // When
        double perimeter = o.perimeter();

        // Then
        System.out.println(perimeter);
        assertThat(perimeter).isEqualTo(2 * MathConstants.PI);
    }

}