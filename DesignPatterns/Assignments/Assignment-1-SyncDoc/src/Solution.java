/*
Question:
SyncDoc is an app used to book doctor appointments. Write a program to create a booking portal.

Have 10 different kind of doctors.

Use facade pattern
 */
import syncdoc.BookingFacade;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

public class Solution {

    public static void interactiveMode()throws IOException
    {
        BookingFacade facade = new BookingFacade();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char ch;
        do
        {
            System.out.println("What do you want to do ?");
            System.out.println("1.Add a doctor");
            System.out.println("2.Add a patient");
            System.out.println("3. Request for a doctor");
            switch (Integer.parseInt(br.readLine()))
            {
                case 1:
                {
                    System.out.println("Enter Doctor name: ");
                    String name = br.readLine();
                    System.out.println("Enter Doctor availability(Start Hour, Start Minute, End Hour End Minute): ");
                    int[] availability = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                    System.out.println("Enter skills set:");
                    String[] skills = br.readLine().split(" ");
                    facade.addDoctor(name,
                            Arrays.asList(skills),
                            availability[0],
                            availability[1],
                            availability[2],
                            availability[3]);
                }
                break;
                case 2:
                {
                    System.out.println("Enter patient name: ");
                    String name = br.readLine();
                    System.out.println("Enter requirements: ");
                    String[] req = br.readLine().split(" ");
                    System.out.println("Enter time to request appointment in Hours, Minutes:");
                    String[] time = br.readLine().split(" ");
                    facade.setPatient(name,
                            Arrays.asList(req),
                            Integer.parseInt(time[0]),
                            Integer.parseInt(time[1]));
                }
                break;
                case 3:
                {
                    for(Map.Entry<?,?> entry:facade.matchDoctors().entrySet())
                    {
                        System.out.println(entry.getKey()+""+entry.getValue());
                    }
                }
            }
            System.out.println("Do you want to continue(y/n):");
        }while (((ch= br.readLine().charAt(0))!='n'));
    }


    public static void populateDoctors(BookingFacade facade)
    {

        facade.addDoctor("Priya",Arrays.asList("ENT","Dental"),10,20,15,40);
        facade.addDoctor("Ramu",Arrays.asList("Heart","Surgency"),11,26,17,30);
        facade.addDoctor("Rajesh",Arrays.asList("Ortho","General"),7,45,8,13);

        facade.addDoctor("Kamlesh",Arrays.asList("Gyno","Gastro"),13,13,22,10);
        facade.addDoctor("Shaik",Arrays.asList("Heart","Surgency"),10,8,12,20);
        facade.addDoctor("Aryan",Arrays.asList("Paediatrics"),2,20,3,40);

        facade.addDoctor("Bhargavi",Arrays.asList("Paediatrics","ENT"),15,30,20,25);
        facade.addDoctor("Pardhu",Arrays.asList("ENT"),9,30,10,40);
        facade.addDoctor("Rohita",Arrays.asList("Lungs"),14,30,17,15);

        facade.addDoctor("Shalini",Arrays.asList("Ortho"),11,28,14,15);
    }

    public static void main(String []args)throws IOException
    {
        //interactiveMode(); //please uncomment this to get interactive mode on doctors working
        BookingFacade facade = new BookingFacade();
        populateDoctors(facade);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter patient name: ");
        String name = br.readLine();
        do {

            System.out.println("Enter requirements: ");
            String[] req = br.readLine().split(" ");
            System.out.println("Enter time to request appointment in Hours, Minutes:");
            int[] time = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            facade.setPatient(name,
                    Arrays.asList(req),
                    time[0],
                    time[1]);
            System.out.println("We found the following matching doctors: ");
            boolean found = false;
            int i=1;
            for(Map.Entry<?,?> entry:facade.matchDoctors().entrySet())
            {
                System.out.println(i+" "+entry.getKey()+""+entry.getValue());
                found = true;
                i++;
            }
            if(found)
            {
                System.out.println("Select a doctor to continue (please enter the desired number): ");
                int booking = Integer.parseInt(br.readLine());
                System.out.println("Enter a time for making an appointment(Hours, Minutes): ");
                time = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                facade.makeBooking(booking, time[0], time[1]);

            }

            System.out.println("You're bookings are: ");
            facade.getAppointments();

            System.out.println("Do you want to make another booking(y/n): ");
        }while (br.readLine().charAt(0)!='n');
    }
}
