import FW.Pen;
import FW.PenFactory;

public class Solution {
    public static void main(String[] args) {

      for(int i=0;i<1000;i++)
      {
          Pen mPen = PenFactory.getRandPen();
          mPen.draw("Hello World");
      }
        System.out.println("Total number of objects are: "+PenFactory.getSize()+"/1000");
    }
}
