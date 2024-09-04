package io.github.ricky.cg.common.utils;

import io.github.ricky.cg.common.constants.MathConstants;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/3
 * @className DoubleUtilsTest
 * @desc
 */
class DoubleUtilsTest {

    @Test
    public void cmp() {
        assertThat(DoubleUtils.cmp(1.0, 1.0)).isZero();
        assertThat(DoubleUtils.cmp(1.0, 1.0 + MathConstants.EPS / 2)).isZero();
        assertThat(DoubleUtils.cmp(1.0, 2.0)).isNegative();
        assertThat(DoubleUtils.cmp(2.0, 1.0)).isPositive();
        assertThat(DoubleUtils.cmp(-1.0, -2.0)).isPositive();
        assertThat(DoubleUtils.cmp(-2.0, -1.0)).isNegative();
        assertThat(DoubleUtils.cmp(1.0 + 2 * MathConstants.EPS, 1.0)).isPositive();
        assertThat(DoubleUtils.cmp(1.0, 1.0 + 2 * MathConstants.EPS)).isNegative();
    }

}