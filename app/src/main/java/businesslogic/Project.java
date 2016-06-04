package businesslogic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Observable;

/**
 * Created by idokov on 13/05/2016.
 */
public class Project extends Observable {
    private String title;
    private String description;
    private String notes;
    private boolean isRunning;
    private ArrayList<Session> sessions;
    private long totalTime;
    private Session currentSession;
    // TODO: 14/05/2016 find better way to represent money
    private BigDecimal paymentPerHour;
    private BigDecimal paymentTotal;

    public Project(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public ArrayList<Session> getSessions() {
        return sessions;
    }

    public void setSessions(ArrayList<Session> sessions) {
        this.sessions = sessions;
    }

    public BigDecimal getPaymentPerHour() {
        return paymentPerHour;
    }

    public void setPaymentPerHour(BigDecimal paymentPerHour) {
        this.paymentPerHour = paymentPerHour;
    }

    public BigDecimal getPaymentTotal() {
        return paymentTotal;
    }

    public void setPaymentTotal(BigDecimal paymentTotal) {
        this.paymentTotal = paymentTotal;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    public boolean startProject() {
        if (currentSession == null) {
            currentSession = new Session(Calendar.getInstance().getTimeInMillis());
            setIsRunning(true);
            return true;
        }

        return false;

    }

    public boolean stopProject() {
        if (currentSession != null) {
            currentSession.setEndTime(Calendar.getInstance().getTimeInMillis());
            sessions.add(currentSession);
            currentSession = null;
            return true;
        }

        return false;
    }

}
