import calculations.Operator;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Base {
    static Map<String,String> mMap = new HashMap<>();
    public static void loadMap()
    {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("OperationMappings")));
            String line;
            while((line=br.readLine())!=null)
            {
                String codes[] = line.split(" ");
                mMap.put(codes[0],codes[1]);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("No Class Mappings Found");
            System.exit(0);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public static double operate(String[] codes)
    {
        try {
            Class cls = ClassLoader.getSystemClassLoader().loadClass("calculations." + mMap.get(codes[2]));
            Operator inst = (Operator)cls.getConstructor().newInstance();
            inst.setOperands(Double.parseDouble(codes[0]),Double.parseDouble(codes[1]));
            return inst.result();
        }
        catch (Exception e)
        {
            System.out.println("No such Mapping");
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args)throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        loadMap();
        System.out.println("Enter your operation (operand1,operand2,operation): ");
        String op[] = br.readLine().split(",");
        System.out.println("Result is: "+operate(op));
    }
}
