package model;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.Objects;

@Named
@SessionScoped
public class SelectXBean implements Serializable {
    private boolean selectedM2;
    private boolean selectedM15;
    private boolean selectedM1;
    private boolean selectedM05;
    private boolean selected0;
    private boolean selected05;
    private SelectX lastSelected;

    public SelectXBean() {
        lastSelected = SelectX.UNSELECTED;
    }

    public boolean isSelectedM2() {
        return selectedM2;
    }

    public void setSelectedM2(boolean selectedM2) {
        this.selectedM2 = selectedM2;
    }

    public boolean isSelectedM15() {
        return selectedM15;
    }

    public void setSelectedM15(boolean selectedM15) {
        this.selectedM15 = selectedM15;
    }

    public boolean isSelectedM1() {
        return selectedM1;
    }

    public void setSelectedM1(boolean selectedM1) {
        this.selectedM1 = selectedM1;
    }

    public boolean isSelectedM05() {
        return selectedM05;
    }

    public void setSelectedM05(boolean selectedM05) {
        this.selectedM05 = selectedM05;
    }

    public boolean isSelected0() {
        return selected0;
    }

    public void setSelected0(boolean selected0) {
        this.selected0 = selected0;
    }

    public boolean isSelected05() {
        return selected05;
    }

    public void setSelected05(boolean selected05) {
        this.selected05 = selected05;
    }

    public SelectX getLastSelected() {
        return lastSelected;
    }

    public double getValue() {
        return lastSelected.getValue();
    }

    public void checkboxValueChanged() {
        if (isSelectedM2() && lastSelected.ordinal() == 0) setSelectedM2(false);
        if (isSelectedM15() && lastSelected.ordinal() == 1) setSelectedM15(false);
        if (isSelectedM1() && lastSelected.ordinal() == 2) setSelectedM1(false);
        if (isSelectedM05() && lastSelected.ordinal() == 3) setSelectedM05(false);
        if (isSelected0() && lastSelected.ordinal() == 4) setSelected0(false);
        if (isSelected05() && lastSelected.ordinal() == 5) setSelected05(false);

        if (isSelectedM2()) lastSelected = SelectX.MINUS2;
        else if (isSelectedM15()) lastSelected = SelectX.MINUS15;
        else if (isSelectedM1()) lastSelected = SelectX.MINUS1;
        else if (isSelectedM05()) lastSelected = SelectX.MINUS05;
        else if (isSelected0()) lastSelected = SelectX.PLUS0;
        else if (isSelected05()) lastSelected = SelectX.PLUS05;
        else lastSelected = SelectX.UNSELECTED;
    }

    public void validateSelectX(FacesContext context, UIComponent component, Object value) {
        if (lastSelected.getValue() == null) {
            FacesMessage message = new FacesMessage("Please, select at least one checkbox!");
            throw new ValidatorException(message);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SelectXBean)) return false;
        SelectXBean that = (SelectXBean) o;
        return isSelectedM2() == that.isSelectedM2() && isSelectedM15() == that.isSelectedM15() && isSelectedM1() == that.isSelectedM1() && isSelectedM05() == that.isSelectedM05() && isSelected0() == that.isSelected0() && isSelected05() == that.isSelected05() && getLastSelected() == that.getLastSelected();
    }

    @Override
    public int hashCode() {
        return Objects.hash(isSelectedM2(), isSelectedM15(), isSelectedM1(), isSelectedM05(), isSelected0(), isSelected05(), getLastSelected());
    }

    @Override
    public String toString() {
        return "SelectXBean{" +
                "selectedM2=" + selectedM2 +
                ", selectedM15=" + selectedM15 +
                ", selectedM1=" + selectedM1 +
                ", selectedM05=" + selectedM05 +
                ", selected0=" + selected0 +
                ", selected05=" + selected05 +
                ", lastSelected=" + lastSelected +
                '}';
    }
}
