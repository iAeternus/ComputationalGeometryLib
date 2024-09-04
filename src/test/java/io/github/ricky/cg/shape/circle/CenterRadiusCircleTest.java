package io.github.ricky.cg.shape.circle;

import io.github.ricky.cg.common.constants.MathConstants;
import io.github.ricky.cg.shape.circle.CenterRadiusCircle;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/2
 * @className CenterRadiusCircleTest
 * @desc
 */
class CenterRadiusCircleTest {

    @Test
    public void area() {
        // Given
        CenterRadiusCircle o = CenterRadiusCircle.UNIT_CENTER_RADIUS_CIRCLE;

        // When
        double area = o.area();

        // Then
        System.out.println(area);
        assertThat(area).isEqualTo(MathConstants.PI);
    }

    @Test
    public void perimeter() {
        // Given
        CenterRadiusCircle o = CenterRadiusCircle.UNIT_CENTER_RADIUS_CIRCLE;

        // When
        double perimeter = o.perimeter();

        // Then
        System.out.println(perimeter);
        assertThat(perimeter).isEqualTo(2 * MathConstants.PI);
    }

}