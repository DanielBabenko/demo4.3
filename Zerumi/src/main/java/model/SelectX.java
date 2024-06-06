package model;

import java.io.Serializable;

public enum SelectX implements Serializable {
    MINUS2(-2.0),
    MINUS15(-1.5),
    MINUS1(-1.0),
    MINUS05(-0.5),
    PLUS0(0.0),
    PLUS05(0.5),
    UNSELECTED(null);
    private final Double value;
    SelectX(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }
}
