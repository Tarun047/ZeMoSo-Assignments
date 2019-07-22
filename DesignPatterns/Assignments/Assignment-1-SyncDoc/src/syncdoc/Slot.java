package syncdoc;

import java.text.SimpleDateFormat;
import java.util.Date;

class Slot implements Comparable<Slot>
{
    private Date startTime;
    private Date endTime;
    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {

        if(endTime!=null)
            return String.format("\n Slot is at: %s-%s", sdf.format(startTime), sdf.format(endTime));
        else
            return String.format("\n Appointment is at:%s", startTime);
    }

    @Override
    public int compareTo(Slot o) {
        if(this.startTime.equals(o.startTime))
            return this.endTime.compareTo(o.endTime);
        else
            return this.startTime.compareTo(o.startTime);
    }
}
