package utils;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static java.lang.Math.sqrt;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

public class CheckerTest {
    @Test
    public void checkIfOnSectorEdge() {
        Assertions.assertTrue(Checker.checkHit((float)-sqrt(2),(float)sqrt(2),2));
    }

    @Test
    public void checkIfOnSector() {
        assertTrue(Checker.checkHit(-1,1,2));
    }

    @Test
    public void checkIfNotOnSector() {
        assertFalse(Checker.checkHit(-2,2,2));
    }

    @Test
    public void checkIfOnRectEdge() {
        assertTrue(Checker.checkHit(-1,-2,2));
    }

    @Test
    public void checkIfOnRect() {
        assertTrue(Checker.checkHit(-0.9,-0.9,2));
    }

    @Test
    public void checkIfNotOnRect() {
        assertFalse(Checker.checkHit(-1.1,-2.1,1));
    }

    @Test
    public void checkIfOnTriangleEdge() {
        assertTrue(Checker.checkHit(0.75,-0.75,3));
    }

    @Test
    public void checkIfOnTriangle() {
        assertTrue(Checker.checkHit(0.5,-0.6,3));
    }

    @Test
    public void checkIfNotOnTriangle() {
        assertFalse(Checker.checkHit(1.0000001,-0.5,3));
    }
}