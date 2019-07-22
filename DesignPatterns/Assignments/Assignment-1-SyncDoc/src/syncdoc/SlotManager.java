package syncdoc;

import java.util.Calendar;
import java.util.Date;
import java.util.TreeSet;

class SlotManager {
    private TreeSet<Slot> slots;
    private static Calendar cal;

    SlotManager()
    {
        slots = new TreeSet<>();
        cal = Calendar.getInstance();
    }

    private boolean isValidTime(int hour,int minute)
    {
        return hour<23 && minute<60;
    }

    static Date getDateObject(int hour,int minute)
    {
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        return cal.getTime();
    }

    void setAvailability(int startHour,int startMinute,int endHour,int endMinute)
    {

        if(startHour>endHour)
        {
            throw new IllegalArgumentException("Start time can't be less than end time");
        }
        if(isValidTime(startHour,startMinute) && isValidTime(endHour,endMinute))
        {
            Slot mSlot = new Slot();
            mSlot.setStartTime(getDateObject(startHour,startMinute));
            mSlot.setEndTime(getDateObject(endHour,endMinute));
            slots.add(mSlot);
        }
    }


    Slot requestAvailability(int hour, int minute)
    {

        if(isValidTime(hour,minute)) {

            Slot mSlot = new Slot();
            mSlot.setStartTime(getDateObject(hour,minute));
            Slot uSlot = slots.ceiling(mSlot);
            Slot dSlot =  slots.floor(mSlot);
            if(uSlot!=null)
                return uSlot;
            else
                return dSlot;
        }
        return null;
    }


}
