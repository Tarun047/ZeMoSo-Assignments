package syncdoc;
import java.util.LinkedList;
import java.util.List;

public class Patient {
    private String name;
    List<String> req;
    int startHour;
    int startMinute;
    private List<Slot> appointments;

    Patient(String name)
    {
        this.name=name;
        appointments = new LinkedList<>();
    }

    void setReq(List<String> req) {
        this.req = req;
    }

    void addAppointments(Slot when)
    {
        appointments.add(when);
    }

    List<Slot> getAppointments()
    {
        return appointments;
    }

    void setTime(int startHour,int startMinute)
    {
        this.startHour = startHour;
        this.startMinute = startMinute;
    }
}
