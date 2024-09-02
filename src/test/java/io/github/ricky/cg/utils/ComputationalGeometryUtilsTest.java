package io.github.ricky.cg.utils;

import io.github.ricky.cg.constants.MathConstants;
import io.github.ricky.cg.model.Point;
import io.github.ricky.cg.model.Segment;
import io.github.ricky.cg.utils.ComputationalGeometryUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/1
 * @className ComputationalGeometryUtilsTest
 * @desc
 */
class ComputationalGeometryUtilsTest {

    @Test
    public void distance() {
        // Given
        Point p1 = new Point(1, 1);
        Point p2 = Point.ORIGINAL_POINT;

        // When
        double distance = ComputationalGeometryUtils.distance(p1, p2);

        // Then
        System.out.println(distance);
        assertThat(distance).isEqualTo(Math.sqrt(2));
    }

    @Test
    public void cross() {
        // Given
        Point o = Point.ORIGINAL_POINT;
        Point p1 = new Point(1, 2);
        Point p2 = new Point(2, 1);

        // When
        double cross = ComputationalGeometryUtils.cross(o, p1, p2);
        double absCross = ComputationalGeometryUtils.absCross(o, p1, p2);

        // Then
        System.out.println(cross);
        System.out.println(absCross);
        assertThat(cross).isEqualTo(-3);
        assertThat(absCross).isEqualTo(3);
    }

    @Test
    public void dot() {
        // Given
        Point o = Point.ORIGINAL_POINT;
        Point p1 = new Point(1, 2);
        Point p2 = new Point(2, 1);

        // When
        double dot = ComputationalGeometryUtils.dot(o, p1, p2);

        // Then
        System.out.println(dot);
        assertThat(dot).isEqualTo(4);
    }

    @Test
    public void online() {
        // Given
        Segment l = new Segment(Point.ORIGINAL_POINT, new Point(5, 5));
        Point p1 = new Point(2.5, 2.5);
        Point p2 = new Point(1, 2);

        // When
        boolean res1 = ComputationalGeometryUtils.online(l, p1);
        boolean res2 = ComputationalGeometryUtils.online(l, p2);

        // Then
        System.out.println(res1);
        System.out.println(res2);
        assertThat(res1).isTrue();
        assertThat(res2).isFalse();
    }

    @Test
    public void rotate() {
        // Given
        Point o = Point.ORIGINAL_POINT;
        Point p = new Point(1, 0);
        double alpha1 = MathConstants.PI;
        double alpha2 = MathConstants.PI / 2;

        // When
        Point t1 = ComputationalGeometryUtils.rotate(o, p, alpha1);
        Point t2 = ComputationalGeometryUtils.rotate(o, p, alpha2);

        // Then
        System.out.println(t1);
        System.out.println(t2);
        assertThat(t1).isEqualTo(new Point(-1, 0));
        assertThat(t2).isEqualTo(new Point(0, 1));
    }

    @Test
    public void angle() {
        // Given
        Point o = Point.ORIGINAL_POINT;
        Point p1 = new Point(Math.sqrt(3), 1);
        Point p2 = new Point(1, Math.sqrt(3));

        double piInSix = MathConstants.PI / 6;

        // When
        double angle = ComputationalGeometryUtils.angle(o, p1, p2);

        // Then
        System.out.println(angle);
        assertThat(angle).isBetween(piInSix - MathConstants.EPS, piInSix + MathConstants.EPS);
    }

    @Test
    public void relation() {
        // Given
        Point c1 = new Point(1,2);
        Point c2 = new Point(6, 2);
        Point c3 = new Point(-1, 2);
        Point c4 = Point.ORIGINAL_POINT;
        Point c5 = new Point(5, 0);

        Segment l = new Segment(Point.ORIGINAL_POINT, new Point(5, 0));

        // When
        double res1 = ComputationalGeometryUtils.relation(c1, l);
        double res2 = ComputationalGeometryUtils.relation(c2, l);
        double res3 = ComputationalGeometryUtils.relation(c3, l);
        double res4 = ComputationalGeometryUtils.relation(c4, l);
        double res5 = ComputationalGeometryUtils.relation(c5, l);

        // Then
        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);
        System.out.println(res4);
        System.out.println(res5);
        assertThat(res1).isBetween(0.0, 1.0);
        assertThat(res2).isGreaterThan(1.0);
        assertThat(res3).isLessThan(0.0);
        assertThat(res4).isEqualTo(0.0);
        assertThat(res5).isEqualTo(1.0);
    }

    @Test
    public void perpendicular() {
        // Given
        Point c = new Point(2.5, 5);
        Segment l = new Segment(Point.ORIGINAL_POINT, new Point(5, 0));

        // When
        Point perpendicular = ComputationalGeometryUtils.perpendicular(c, l);

        // Then
        System.out.println(perpendicular);
        assertThat(perpendicular).isEqualTo(new Point(2.5, 0));
    }

    @Test
    public void closestPointToSegment() {
        // Given
        double sqrt3 = Math.sqrt(3);

        Point c1 = new Point(-1, sqrt3);
        Point c2 = new Point(2.5, 2);
        Point c3 = new Point(6, sqrt3);

        Segment l = new Segment(Point.ORIGINAL_POINT, new Point(5, 0));

        // When
        Point res1 = ComputationalGeometryUtils.closestPointToSegment(c1, l);
        Point res2 = ComputationalGeometryUtils.closestPointToSegment(c2, l);
        Point res3 = ComputationalGeometryUtils.closestPointToSegment(c3, l);

        double dist1 = ComputationalGeometryUtils.distance(res1, c1);
        double dist2 = ComputationalGeometryUtils.distance(res2, c2);
        double dist3 = ComputationalGeometryUtils.distance(res3, c3);

        // Then
        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);

        System.out.println(dist1);
        System.out.println(dist2);
        System.out.println(dist3);

        assertThat(res1).isEqualTo(Point.ORIGINAL_POINT);
        assertThat(res2).isEqualTo(new Point(2.5, 0));
        assertThat(res3).isEqualTo(new Point(5, 0));

        assertThat(dist1).isBetween(2 - MathConstants.EPS, 2 + MathConstants.EPS);
        assertThat(dist2).isBetween(2 - MathConstants.EPS, 2 + MathConstants.EPS);
        assertThat(dist3).isBetween(2 - MathConstants.EPS, 2 + MathConstants.EPS);
    }

    @Test
    public void pointToLineDistance() {
        // Given
        double sqrt3 = Math.sqrt(3);

        Point p = new Point(-1, sqrt3);

        Segment l = new Segment(Point.ORIGINAL_POINT, new Point(5, 0));

        // When
        double distance = ComputationalGeometryUtils.pointToLineDistance(p, l);

        // Then
        System.out.println(distance);
        assertThat(distance).isEqualTo(sqrt3);
    }

}