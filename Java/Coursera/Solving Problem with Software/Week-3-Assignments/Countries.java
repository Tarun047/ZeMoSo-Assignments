import edu.duke.*;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.util.Optional;
import java.util.stream.StreamSupport;

public class Countries
{
     static String countryInfo(CSVParser parser,String country)
    {

        Optional<CSVRecord> record = StreamSupport.stream(parser.spliterator(),false).
                filter(entry -> entry.get("Country").equals(country)).findFirst();
        if(record.isPresent())
        {
            CSVRecord ans = record.get();
            return String.format("%s : %s : %s",ans.get(0),ans.get(1),ans.get(2));
        }
        return "NOT_FOUND";

    }

     static void listExportersTwoProducts(CSVParser parser,String item1,String item2)
    {
        StreamSupport.stream(parser.spliterator(),false).
                filter(record->record.get("Exports").contains(item1)&&record.get("Exports").contains(item2)).
                map(record->record.get("Country")).forEach(System.out::println);
    }

    static long numberOfExporters(CSVParser parser,String export)
    {
        return StreamSupport.stream(parser.spliterator(),false).
                filter(record->record.get("Exports").contains(export)).count();
    }

    static void bigExporters(CSVParser parser,String amount)
    {
        StreamSupport.stream(parser.spliterator(),false).
                filter(record->record.get(2).length()>amount.length()).
                map(record->String.format("%s %s",record.get("Country"),record.get(2))).forEach(System.out::println);
    }

    static void tester()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println(countryInfo(parser,"Germany"));
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser,"gold","diamonds");
        parser = fr.getCSVParser();
        System.out.println(numberOfExporters(parser,"gold"));
        parser = fr.getCSVParser();
        bigExporters(parser,"$400,000,000");
    }

    public static void main(String[] args) {
        tester();
    }
}
