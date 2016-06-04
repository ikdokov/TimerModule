package businesslogic;

/**
 * Created by idokov on 14/05/2016.
 */
public class Session {
    private long startTime;
    private long endTime;
    private String note;

    public Session(long startTime) {
        this.startTime = startTime;
    }

    public Session(long startTime, long endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Session(long startTime, long endTime, String note) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.note = note;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
