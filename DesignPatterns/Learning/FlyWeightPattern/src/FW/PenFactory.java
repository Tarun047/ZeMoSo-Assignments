package FW;

import java.lang.reflect.InvocationTargetException;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.Random;

public class PenFactory
{
    private static final HashMap<String, Pen> pensMap = new HashMap<>();
    private static Random gen = new Random();

    public static Pen getRandPen()
    {

        String color = Color.values()[gen.nextInt(12)].toString();
        String size = BrushSize.values()[gen.nextInt(3)].toString();

        Pen pen = pensMap.get(color+size);

        if(pen != null) {
            System.out.println("Found match!");
            return pen;
        }
        else {
                if(size.equals("MEDIUM"))
                    pen = new MediumPen();
                else if(size.equals("THIN"))
                    pen = new ThinPen();
                else
                    pen = new ThickPen();
                pen.setColor(color);
                pensMap.put(color+size,pen);

        }

        return pen;
    }

    public static int getSize()
    {
        return pensMap.size();
    }
}