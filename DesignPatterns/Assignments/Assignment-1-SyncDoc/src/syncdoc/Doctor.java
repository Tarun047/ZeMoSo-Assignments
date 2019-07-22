package syncdoc;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Doctor {
    private String name;
    private SlotManager sm;
    private Set<String> specializations;
    private int bookings;

    Doctor(String name)
    {
        sm = new SlotManager();
        specializations = new HashSet<>();
        this.name = name.toLowerCase();
    }

    public int getBookings() {
        return bookings;
    }

    public boolean setBookings(int bookings) {
        if(bookings>BookingFacade.MAX_BOOKINGS)
            return false;
        this.bookings = bookings;
        return true;
    }

    void addSpecializations(List<String> skills)
    {
        specializations.addAll(skills);
    }

    boolean hasSpecialization(List<String> skills)
    {
        for(String skill:skills)
            if(!specializations.contains(skill))
                return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("-----Doctor Info-----\nName: %s%s\nSkills: %s", name.substring(0, 1).toUpperCase(), name.substring(1), specializations);
    }

    void setTimings(int startHour, int startMinute, int endHour, int endMinute)
    {
        sm.setAvailability(startHour,startMinute,endHour,endMinute);
    }

    Slot requestAvailability(int startHour,int startMinute)
    {
        return sm.requestAvailability(startHour,startMinute);
    }
}
