import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


import java.io.File;
import java.util.Comparator;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Temperature {
    static CSVRecord coldestHourInFile(CSVParser parser)
    {
        return   StreamSupport.stream(parser.spliterator(), false).
                filter(record -> Double.parseDouble(record.get("TemperatureF")) != -9999D).
                min(Comparator.comparingDouble(record -> Double.parseDouble(record.get("TemperatureF")))).
                orElse(null);
    }

    static void testcoldestHourInFile()
    {
        FileResource fr = new FileResource();
        CSVRecord record = coldestHourInFile(fr.getCSVParser());
        System.out.format("The Coldest temperature is at: %s and is %s F\n",record.get("DateUTC"),record.get("TemperatureF"));
    }

    static double extractMinPropFromFile(File f, Function<CSVParser,CSVRecord> function,String prop)
    {
        FileResource fr = new FileResource(f);
        CSVParser parser = fr.getCSVParser();
        return Double.parseDouble(function.apply(parser).get(prop));
    }


    static String fileWithColdestTemperature()
    {
        DirectoryResource dr = new DirectoryResource();
        return StreamSupport.stream(dr.selectedFiles().spliterator(),false).
                min(Comparator.
                        comparingDouble(file->extractMinPropFromFile(file,Temperature::coldestHourInFile,"TemperatureF"))).
                get().
                getAbsolutePath();
    }

    static void testFileWithColdestTemperature()
    {
        File f = new File(fileWithColdestTemperature());
        System.out.println("File name: "+f.getName()+" Temperature value:"+
                extractMinPropFromFile(f,Temperature::coldestHourInFile,"TemperatureF"));
    }

    static CSVRecord lowestHumidityInFile(CSVParser parser)
    {
        return   StreamSupport.stream(parser.spliterator(), false).
                filter(record ->!record.get("Humidity").equals("N/A")).
                min(Comparator.comparingDouble(record -> Double.parseDouble(record.get("Humidity")))).
                orElse(null);
    }

    static void testLowestHumidityInFile()
    {
        FileResource fr = new FileResource();
        CSVRecord record = lowestHumidityInFile(fr.getCSVParser());
        System.out.format("Lowest Humidity was %s at %s\n",record.get("Humidity"),record.get("DateUTC"));
    }
    static CSVRecord lowestHumidityInManyFiles()
    {
        DirectoryResource dr = new DirectoryResource();
        Stream<CSVRecord> recordStream = StreamSupport.stream(dr.selectedFiles().spliterator(),false).
                map(file->lowestHumidityInFile(new FileResource(file).getCSVParser()));
        return recordStream.min(Comparator.comparingDouble(record->Double.parseDouble(record.get("Humidity")))).
                orElse(null);
    }

    static void testLowestHumidityInMultipleFiles()
    {
        CSVRecord record = lowestHumidityInManyFiles();
        System.out.format("Lowest Humidity was %s at %s\n",record.get("Humidity"),record.get("DateUTC"));
    }

    static double averageTemperatureInFile(CSVParser parser)
    {
        return StreamSupport.stream(parser.spliterator(), false).
                filter(record -> Double.parseDouble(record.get("TemperatureF")) != -9999D).
                mapToDouble(record->Double.parseDouble(record.get("TemperatureF"))).average().orElse(Double.NaN);
    }


    static void testAverageTemperatureInFile()
    {
        FileResource fr = new FileResource();
        System.out.println("Average temperature in file is: "+averageTemperatureInFile(fr.getCSVParser()));
    }

     static double averageTemperatureWithHighHumidityInFile(CSVParser parser,int humidity)
    {
        return StreamSupport.stream(parser.spliterator(), false).
                filter(record -> Double.parseDouble(record.get("TemperatureF")) != -9999D
                        && Integer.parseInt(record.get("Humidity"))>=humidity).
                mapToDouble(record->Double.parseDouble(record.get("TemperatureF"))).average().orElse(Double.NaN);
    }


    static void testAverageTemperatureWithHighHumidityInFile()
    {
        FileResource fr = new FileResource();
        double temp = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(),80);
        System.out.println(temp!=Double.NaN?"Average Temp when high Humidity is "+temp:"No temperatures with that humidity");
    }

    public static void main(String[] args) {
        testcoldestHourInFile();
        testFileWithColdestTemperature();
        testLowestHumidityInFile();
        testLowestHumidityInMultipleFiles();
        testAverageTemperatureInFile();
        testAverageTemperatureWithHighHumidityInFile();
    }

}
