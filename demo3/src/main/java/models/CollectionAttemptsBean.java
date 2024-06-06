package models;


import database.HibernateManager;

import jakarta.faces.bean.ApplicationScoped;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.ManagedProperty;
import jakarta.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

@ManagedBean(name = "attempts", eager = true)
@ApplicationScoped
public class CollectionAttemptsBean implements Serializable {
    //нужно использовать потокобезопасные коллекции,
    // потому что взаимодействия с коллекциями могут происходить в разных потоках.
    private final CopyOnWriteArrayList<Attempt> attempts;
    private final HibernateManager hibernateManager;

    private Attempt currentAttempt = new Attempt("0", "0", "2");

    public CollectionAttemptsBean() {
        hibernateManager = new HibernateManager();
        // чтобы он работал, не забывать прокидывать порт!!!

        attempts = new CopyOnWriteArrayList<>();
    }

    public void add(Attempt attempt) {
        attempts.add(attempt);

        hibernateManager.addAttempt(attempt);
    }

    public void clear() {
        attempts.clear();

        hibernateManager.clearAttempts();
    }

    public List<Attempt> getAttempts() {
        return attempts;
    }

    public void addFromForm() {
        currentAttempt.updateIsHIt();
        add(currentAttempt);
        currentAttempt = new Attempt(currentAttempt.getX(),
                currentAttempt.getY(), currentAttempt.getR());
    }

    public void addFromCanvas() {
        String strX = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("x");
        String strY = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("y");
        String strR = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("r");

        Attempt attempt = new Attempt(strX, strY, strR);

        add(attempt);
    }

    public Attempt getCurrentAttempt() {
        return currentAttempt;
    }

    public void setCurrentAttempt(Attempt currentAttempt) {
        this.currentAttempt = currentAttempt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CollectionAttemptsBean that = (CollectionAttemptsBean) o;
        return Objects.equals(attempts, that.attempts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attempts);
    }

    @Override
    public String toString() {
        return "CollectionAttempts{" +
                "attempts=" + attempts +
                '}';
    }
}