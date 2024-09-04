package io.github.ricky.cg.basic.line;

import io.github.ricky.cg.basic.point.Point;

import java.util.Objects;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/1
 * @className Segment
 * @desc 线段
 */
public class Segment {

    /**
     * 起始点
     */
    private Point begin;

    /**
     * 结束点
     */
    private Point end;

    public Segment() {
    }

    public Segment(Point begin, Point end) {
        this.begin = begin;
        this.end = end;
    }

    public Point getBegin() {
        return begin;
    }

    public void setBegin(Point begin) {
        this.begin = begin;
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    /**
     * 计算线段长度平方
     *
     * @return 线段长度平方
     */
    public double sqrLength() {
        double dx = begin.getX() - end.getX();
        double dy = begin.getY() - end.getY();
        return dx * dx + dy * dy;
    }

    /**
     * 计算线段长度
     *
     * @return 返回线段长度
     */
    public double length() {
        return Math.sqrt(sqrLength());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Segment that = (Segment) o;
        return Objects.equals(begin, that.begin) && Objects.equals(end, that.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(begin, end);
    }

    @Override
    public String toString() {
        return "<" + begin.toString() + "-" + end.toString() + ">";
    }
}
