package utooservices;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cab
{
    private String cabModel;
    private String cabManufacturer;
    private String cabColor;
    private String cabNumberPlate;
    private String locationCode;
    private CabCategory category;

    public String getCabModel() {
        return cabModel;
    }

    public void setCabModel(String cabModel) {
        this.cabModel = cabModel;
    }

    public String getCabManufacturer() {
        return cabManufacturer;
    }

    public void setCabManufacturer(String cabManufacturer) {
        this.cabManufacturer = cabManufacturer;
    }

    public String getCabColor() {
        return cabColor;
    }

    public void setCabColor(String cabColor) {
        this.cabColor = cabColor;
    }

    public String getCabNumberPlate() {
        return cabNumberPlate;
    }

    public void setCabNumberPlate(String cabNumberPlate) {
        this.cabNumberPlate = cabNumberPlate;
    }

    public CabCategory getCategory() {
        return category;
    }

    public void setCategory(CabCategory category) {
        this.category = category;
    }

    public String getLocationCode() {
        return locationCode;
    }


    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }


    @Override
    public String toString()
    {
        return String.format("Cab Details are: \n\t Cab Model: %s\n\t Cab Manufacturer: %s\n\t Cab Color: %s\n\t Cab Number: %s",
                getCabModel(),
                getCabManufacturer(),
                getCabColor(),
                getCabNumberPlate());
    }

    public static String getRandNumber(int n)
    {
        String ph="";
        Random gen = new Random();
        for(int i=0;i<n;i++)
            ph+=(1+gen.nextInt(10));
        return ph;
    }

    public static List<Cab> getDummyCab()
    {

        String[] colors = new String[]{
                "Red",
                "Blue",
                "Green",
                "Yellow",
                "White"

        };

        String[] brands = new String[]{
                "Nissan",
                "Volkswagen",
                "Mercedes",
                "Ferrari",
                "Mitsubishi"

        };

        String[] models = new String[]{
                "Sunny",
                "Pento",
                "Q5",
                "F1",
                "M1"

        };

        String[] types= new String[]
                {
                        "MICRO",
                        "MINI",
                        "SUV",
                        "SEDAN"

                };

        List<Cab> cabs = new ArrayList<>();

        Random gen = new Random();
        for(int i=0;i<10;i++)
        {
            Cab c = new Cab();
            c.setCabModel(models[gen.nextInt(5)]);
            c.setCabManufacturer(brands[gen.nextInt(5)]);
            c.setCabColor(colors[gen.nextInt(5)]);
            c.setCabNumberPlate(getRandNumber(10));
            c.setCategory(CabCategory.valueOf(types[gen.nextInt(4)]));
            cabs.add(c);
        }

        return cabs;
    }
}
