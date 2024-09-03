package io.github.ricky.cg.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/1
 * @className Vector2Test
 * @desc
 */
class Vector2Test {

    @Test
    public void add() {
        // Given
        Vector v1 = new Vector2(1, 0);
        Vector v2 = new Vector2(0, 1);

        // When
        Vector v3 = Vector.add(v1, v2);
        v1.addAndSet(v2).addAndSet(v2);

        // Then
        System.out.println(v3);
        System.out.println(v1);
        assertThat(v3).isEqualTo(Vector2.UNIT);
        assertThat(v1).isEqualTo(new Vector2(1, 2));
    }

    @Test
    public void subtract() {
        // Given
        Vector v1 = new Vector2(5, 5);
        Vector v2 = new Vector2(3, 4);

        // When
        Vector v3 = Vector.subtract(v1, v2);
        v1.subtractAndSet(v2).subtractAndSet(v2);

        // Then
        System.out.println(v3);
        System.out.println(v1);
        assertThat(v3).isEqualTo(new Vector2(2, 1));
        assertThat(v1).isEqualTo(new Vector2(-1, -3));
    }

    @Test
    public void scalarMultiply() {
        // Given
        Vector v1 = new Vector2(5, 5);
        double n = 0.5;

        // When
        Vector v2 = Vector.scalarMultiply(v1, n);
        v1.scalarMultiplyAndSet(n).scalarMultiplyAndSet(n);

        // Then
        System.out.println(v2);
        System.out.println(v1);
        assertThat(v2).isEqualTo(new Vector2(2.5, 2.5));
        assertThat(v1).isEqualTo(new Vector2(1.25, 1.25));
    }

    @Test
    public void dot() {
        // Given
        Vector v1 = new Vector2(2, 2);
        Vector v2 = new Vector2(3, 4);

        // When
        double dot = Vector.dot(v1, v2);

        // Then
        System.out.println(dot);
        assertThat(dot).isEqualTo(14);
    }

    @Test
    public void cross() {
        // Given
        Vector v1 = new Vector2(2, 2);
        Vector v2 = new Vector2(3, 4);

        // When
        double cross = Vector.cross(v1, v2);

        // Then
        System.out.println(cross);
        assertThat(cross).isEqualTo(2);
    }

    @Test
    public void modulo() {
        // Given
        Vector2 v1 = Vector2.UNIT;

        // When
        double modulo = v1.modulo();

        // Then
        System.out.println(modulo);
        assertThat(modulo).isEqualTo(Math.sqrt(2));
    }

    @Test
    public void cosine() {
        // Given
        Vector v1 = new Vector2(6, 0);
        Vector v2 = new Vector2(3, 3 * Math.sqrt(3));

        // When
        double cosine = Vector.cosine(v1, v2);

        // THen
        System.out.println(cosine);
        assertThat(cosine).isEqualTo(0.5);
    }

}