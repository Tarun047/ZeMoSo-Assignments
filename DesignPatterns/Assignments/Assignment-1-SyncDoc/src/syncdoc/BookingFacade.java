package syncdoc;

import java.util.*;

public class BookingFacade {
    static int MAX_BOOKINGS = 10;
    private List<Doctor> doctorList;
    private Patient patient;
    private List<Doctor> found;


    public BookingFacade()
    {
        doctorList = new LinkedList<>();
        found = new ArrayList<>();
    }

    public void addDoctor(String name,List<String> skills,int startHour,int startMinute,int endHour,int endMinute)
    {
        Doctor doc = new Doctor(name);
        doc.addSpecializations(skills);
        doc.setTimings(startHour,startMinute,endHour,endMinute);
        doctorList.add(doc);
    }

    public void setPatient(String name,List<String> req,int startHour,int startMinute)
    {
        if(patient==null) {
            patient = new Patient(name);
            patient.setTime(startHour, startMinute);
            patient.setReq(req);
        }
        else
        {
            patient.setTime(startHour, startMinute);
            patient.setReq(req);
        }
    }

    public Map<Doctor,Slot> matchDoctors()
    {
        Map<Doctor,Slot> matches = new HashMap<>();
        if(patient==null || doctorList.size()==0) {
            System.out.println("We couldn't find at least one doctor or patient in the entries, please add at least one " +
                    "doctor or patient");
            return null;
        }

        for(Doctor doctor:doctorList)
        {
            if(doctor.hasSpecialization(patient.req))
            {
                Slot time = doctor.requestAvailability(patient.startHour,patient.startMinute);
                if(time!=null)
                {
                    matches.put(doctor, time);
                    found.add(doctor);
                }
                else
                    System.out.println("Sorry we couldn't find a doctor who meets your requirements at the time mentioned");
            }
        }
        if(matches.size()==0)
        {
            System.out.println("Sorry no matches found! with required skills");
        }
        return matches;
    }

    public void makeBooking(int idx,int startHour,int startMinute)
    {
        Doctor doc = found.get(idx);
        if(doc.setBookings(doc.getBookings()+1) && doc.requestAvailability(startHour,startMinute)!=null)
        {
            Slot slot = new Slot();
            slot.setStartTime(SlotManager.getDateObject(startHour,startMinute));
            patient.addAppointments(slot);
        }
    }

    public void getAppointments()
    {
        for(Object obj:patient.getAppointments())
        {
            System.out.println(obj);
        }
    }
}
