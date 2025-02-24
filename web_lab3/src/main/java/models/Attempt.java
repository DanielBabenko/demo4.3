package models;

import utils.Checker;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "attempts")
public class Attempt implements Serializable {
    //    create sequence attempts_sequence start 1000 increment -7 maxvalue 1000;
    @Id
    @Column(name="id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attempts_generator")
    @SequenceGenerator(name = "attempts_generator", sequenceName = "attempts_sequence", allocationSize = 1)
    private int id;

    @Column(name = "x", nullable = false)
    private double x;

    @Column(name = "y", nullable = false)
    private double y;

    @Column(name = "r", nullable = false)
    private int r;

    @Column(name = "isHit", nullable = false)
    private boolean isHit;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private GroupOfPoints group = new GroupOfPoints("default");

    public Attempt() {

    }

    public Attempt(String x, String y, String r) {
        this.x = Double.parseDouble(x);
        this.y = Double.parseDouble(y);
        this.r = Integer.parseInt(r);

        isHit = Checker.checkHit(this.x, this.y, this.r);
    }

    public Attempt(double x, double y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;

        isHit = Checker.checkHit(this.x, this.y, this.r);
    }

    public int getId() {
        return id;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getR() {
        return r;
    }

    public boolean getIsHit() {
        return isHit;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setR(int r) {
        this.r = r;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }

    public GroupOfPoints getGroup() {
        return group;
    }

    public void setGroup(GroupOfPoints group) {
        this.group = group;
    }

    public void updateIsHIt() {
        isHit = Checker.checkHit(this.x, this.y, this.r);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attempt attempt = (Attempt) o;
        return id == attempt.id && Double.compare(attempt.x, x) == 0 && Double.compare(attempt.y, y) == 0 && r == attempt.r && isHit == attempt.isHit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, x, y, r, isHit);
    }

    @Override
    public String toString() {
        return "Attempt{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", r=" + r +
                ", isHit=" + isHit +
                '}';
    }
}
