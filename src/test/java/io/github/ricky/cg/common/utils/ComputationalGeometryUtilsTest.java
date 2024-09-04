package io.github.ricky.cg.common.utils;

import io.github.ricky.cg.common.constants.MathConstants;
import io.github.ricky.cg.basic.line.GeneralEquationLine;
import io.github.ricky.cg.basic.line.Line;
import io.github.ricky.cg.basic.point.Point;
import io.github.ricky.cg.basic.line.Segment;
import io.github.ricky.cg.common.utils.ComputationalGeometryUtils;
import io.github.ricky.cg.shape.polygon.Polygon;
import io.github.ricky.cg.shape.polygon.enums.PositionalRelationshipEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

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
        Point c1 = new Point(1, 2);
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

    @Test
    public void segmentAngle() {
        // Given
        double piInThree = MathConstants.PI / 3;

        Segment u = new Segment(new Point(1, 0), new Point(7, 0));
        Segment v = new Segment(Point.ORIGINAL_POINT, new Point(3, 3 * Math.sqrt(3)));

        // When
        double angle = ComputationalGeometryUtils.segmentAngle(u, v);

        // Then
        System.out.println(angle);
        assertThat(angle).isBetween(piInThree - MathConstants.EPS, piInThree + MathConstants.EPS);
    }

    @Test
    public void isIntersect() {
        // Given
        double onePointFiveSqrt3 = 1.5 * Math.sqrt(3);

        Segment u = new Segment(new Point(1, 0), new Point(7, 0));
        Segment v1 = new Segment(new Point(-1.5, -onePointFiveSqrt3), new Point(1.5, onePointFiveSqrt3));
        Segment v2 = new Segment(new Point(-0.5, -onePointFiveSqrt3), new Point(2.5, onePointFiveSqrt3));
        Segment v3 = new Segment(new Point(2.5, -onePointFiveSqrt3), new Point(5.5, onePointFiveSqrt3));
        Segment v4 = new Segment(new Point(5.5, -onePointFiveSqrt3), new Point(8.5, onePointFiveSqrt3));
        Segment v5 = new Segment(new Point(6.5, -onePointFiveSqrt3), new Point(9.5, onePointFiveSqrt3));

        // When
        boolean res1 = ComputationalGeometryUtils.isIntersect(u, v1);
        boolean res2 = ComputationalGeometryUtils.isIntersect(u, v2);
        boolean res3 = ComputationalGeometryUtils.isIntersect(u, v3);
        boolean res4 = ComputationalGeometryUtils.isIntersect(u, v4);
        boolean res5 = ComputationalGeometryUtils.isIntersect(u, v5);

        // Then
        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);
        System.out.println(res4);
        System.out.println(res5);
        assertThat(res1).isFalse();
        assertThat(res2).isTrue();
        assertThat(res3).isTrue();
        assertThat(res4).isTrue();
        assertThat(res5).isFalse();
    }

    @Test
    public void isIntersectA() {
        // Given
        double onePointFiveSqrt3 = 1.5 * Math.sqrt(3);

        Segment u = new Segment(new Point(1, 0), new Point(7, 0));
        Segment v1 = new Segment(new Point(-1.5, -onePointFiveSqrt3), new Point(1.5, onePointFiveSqrt3));
        Segment v2 = new Segment(new Point(-0.5, -onePointFiveSqrt3), new Point(2.5, onePointFiveSqrt3));
        Segment v3 = new Segment(new Point(2.5, -onePointFiveSqrt3), new Point(5.5, onePointFiveSqrt3));
        Segment v4 = new Segment(new Point(5.5, -onePointFiveSqrt3), new Point(8.5, onePointFiveSqrt3));
        Segment v5 = new Segment(new Point(6.5, -onePointFiveSqrt3), new Point(9.5, onePointFiveSqrt3));

        // When
        boolean res1 = ComputationalGeometryUtils.isIntersectExcludingEndpoints(u, v1);
        boolean res2 = ComputationalGeometryUtils.isIntersectExcludingEndpoints(u, v2);
        boolean res3 = ComputationalGeometryUtils.isIntersectExcludingEndpoints(u, v3);
        boolean res4 = ComputationalGeometryUtils.isIntersectExcludingEndpoints(u, v4);
        boolean res5 = ComputationalGeometryUtils.isIntersectExcludingEndpoints(u, v5);

        // Then
        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);
        System.out.println(res4);
        System.out.println(res5);
        assertThat(res1).isFalse();
        assertThat(res2).isFalse();
        assertThat(res3).isTrue();
        assertThat(res4).isFalse();
        assertThat(res5).isFalse();
    }

    @Test
    public void isIntersectL() {
        // Given
        Segment u = new Segment(new Point(0, 0), new Point(4, 4));
        Segment v1 = new Segment(new Point(1, 1), new Point(3, 3)); // 应相交
        Segment v2 = new Segment(new Point(6, 5), new Point(7, 5)); // 不相交
        Segment v3 = new Segment(new Point(2, 2), new Point(4, 4)); // 共线且重叠
        Segment v4 = new Segment(new Point(4, 4), new Point(6, 6)); // 共点且重叠

        // When
        boolean res1 = ComputationalGeometryUtils.isCrossingLine(u, v1);
        boolean res2 = ComputationalGeometryUtils.isCrossingLine(u, v2);
        boolean res3 = ComputationalGeometryUtils.isCrossingLine(u, v3);
        boolean res4 = ComputationalGeometryUtils.isCrossingLine(u, v4);

        // Then
        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);
        System.out.println(res4);
        assertThat(res1).isTrue();
        assertThat(res2).isFalse();
        assertThat(res3).isTrue();
        assertThat(res4).isTrue();
    }

    @Test
    public void symmetry() {
        // Given
        Line l = new GeneralEquationLine(2, -4, 9);
        Point p = new Point(2, 2);

        // When
        Point symmetry = ComputationalGeometryUtils.symmetry(l, p);

        // Then
        System.out.println(symmetry);
        assertThat(symmetry).isEqualTo(new Point(1, 4));
    }

    @Test
    @DisplayName("line")
    public void lineIntersect1() {
        // Given
        Segment l1 = new Segment(Point.ORIGINAL_POINT, new Point(10, 10));
        Segment l2 = new Segment(new Point(0, 10), new Point(10, 0));
        Segment l3 = new Segment(new Point(0, 1), new Point(10, 11));

        // When
        Point res1 = ComputationalGeometryUtils.lineIntersect(l1, l2);
        Point res2 = ComputationalGeometryUtils.lineIntersect(l1, l3);

        // Then
        System.out.println(res1);
        System.out.println(res2);
        assertThat(res1).isEqualTo(new Point(5, 5));
        assertThat(res2).isNull();
    }

    @Test
    @DisplayName("segment")
    public void lineIntersect2() {
        // Given
        Segment l1 = new Segment(Point.ORIGINAL_POINT, new Point(10, 10));
        Segment l2 = new Segment(new Point(0, 10), new Point(10, 0));
        Segment l3 = new Segment(new Point(0, 1), new Point(10, 11));

        // When
        Point res1 = ComputationalGeometryUtils.lineIntersect(l1, l2);
        Point res2 = ComputationalGeometryUtils.lineIntersect(l1, l3);

        // Then
        System.out.println(res1);
        System.out.println(res2);
        assertThat(res1).isEqualTo(new Point(5, 5));
        assertThat(res2).isNull();
    }

    final Polygon polygon = new Polygon(new Point[]{
            Point.ORIGINAL_POINT,
            new Point(1, 0),
            new Point(1, 1),
            new Point(0, 1),
    });

    @Test
    public void positionalRelationship() {
        // Given
        Point q1 = new Point(2, 2);
        Point q2 = new Point(1, 1);
        Point q3 = new Point(0.5, 1);
        Point q4 = new Point(0.5, 0.5);

        // When
        PositionalRelationshipEnum res1 = ComputationalGeometryUtils.positionalRelationship(polygon, q1);
        PositionalRelationshipEnum res2 = ComputationalGeometryUtils.positionalRelationship(polygon, q2);
        PositionalRelationshipEnum res3 = ComputationalGeometryUtils.positionalRelationship(polygon, q3);
        PositionalRelationshipEnum res4 = ComputationalGeometryUtils.positionalRelationship(polygon, q4);

        // Then
        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);
        System.out.println(res4);
        assertThat(res1).isEqualTo(PositionalRelationshipEnum.EXTERNAL);
        assertThat(res2).isEqualTo(PositionalRelationshipEnum.ONLINE);
        assertThat(res3).isEqualTo(PositionalRelationshipEnum.ONLINE);
        assertThat(res4).isEqualTo(PositionalRelationshipEnum.INSIDE);
    }

    @Test
    public void insideConvexPolygon() {
        // Given
        Point q1 = new Point(2, 2);
        Point q2 = new Point(1, 1);
        Point q3 = new Point(0.5, 1);
        Point q4 = new Point(0.5, 0.5);

        // When
        boolean res1 = ComputationalGeometryUtils.insideConvexPolygon(polygon, q1);
        boolean res2 = ComputationalGeometryUtils.insideConvexPolygon(polygon, q2);
        boolean res3 = ComputationalGeometryUtils.insideConvexPolygon(polygon, q3);
        boolean res4 = ComputationalGeometryUtils.insideConvexPolygon(polygon, q4);

        // Then
        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);
        System.out.println(res4);
        assertThat(res1).isFalse();
        assertThat(res2).isTrue();
        assertThat(res3).isTrue();
        assertThat(res4).isTrue();
    }

    @Test
    public void findingConvexHulls() {
        // Given
        Point[] pointSet = {
                new Point(0, 0),
                new Point(2, 0),
                new Point(2, 2),
                new Point(0, 2),
                new Point(1, 1),
                new Point(0.5, 0.5),
        };

        // When
        Point[] hull = ComputationalGeometryUtils.findingConvexHulls(pointSet);
        assert hull != null;

        Polygon polygon = new Polygon(hull);
        double perimeter = polygon.perimeter();

        // Then
        Stream.of(hull).forEach(System.out::println);
        System.out.println(perimeter);
        assertThat(hull).isEqualTo(new Point[]{
                new Point(0, 0),
                new Point(2, 0),
                new Point(2, 2),
                new Point(0, 2),
        });
        assertThat(perimeter).isEqualTo(8);
    }

}