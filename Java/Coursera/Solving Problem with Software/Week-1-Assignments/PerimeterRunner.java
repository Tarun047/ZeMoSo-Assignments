import edu.duke.*;
import java.io.File;
public class PerimeterRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    //Assignment 1 - Task 1
    public int getNumPoints(Shape shape)
    {
        int numberOfPoints = 0;
        for(Point p:shape.getPoints())
          numberOfPoints++;
        return numberOfPoints;
    }

    //Assignment 1 - Task 2
    public double getAverageLength(Shape shape)
    {
       return (double)getPerimeter(shape)/getNumPoints(shape);
    }

    //Assingment 1 - Task 3
    public double getLongestSide(Shape s)
    {
      Point prevPt = s.getLastPoint();
      double maxDist = 0;
      for (Point currPt : s.getPoints()) {

          double currDist = prevPt.distance(currPt);
          maxDist = Math.max(currDist,maxDist);
          prevPt = currPt;
      }
      return maxDist;
    }

    //Assignment 1 - Task 4
    public double getLargestX(Shape s)
    {
      double maxValue = s.getLastPoint().getX();
      for(Point p:s.getPoints())
          maxValue = Math.max(p.getX(),maxValue);
      return maxValue;
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int numberOfPoints = getNumPoints(s);
        double averageLength = getAverageLength(s);
        double longestSide = getLongestSide(s);
        double maxXCord = getLargestX(s);
        System.out.println("perimeter = " + length);
        System.out.println("number of points = "+numberOfPoints);
        System.out.println("average length = "+averageLength);
        System.out.println("longest side = "+longestSide);
        System.out.println("maximum x value = "+maxXCord);
    }

    //Assignment 2 - Task 1
    public double getLargestPerimeterMultipleFiles()
    {
      DirectoryResource dr = new DirectoryResource();
      double maxPerimeter = 0;
      for(File f:dr.selectedFiles())
      {
          FileResource fr = new FileResource(f);
          Shape s = new Shape(fr);
          maxPerimeter = Math.max(maxPerimeter,getPerimeter(s));
      }
      return maxPerimeter;
    }
    //Assignment 2 - Task 2
    public File getFileWithLargestPerimeter()
    {
      DirectoryResource dr = new DirectoryResource();
      double maxPerimeter = 0;
      File fileReference=null;
      for(File f:dr.selectedFiles())
      {
          FileResource fr = new FileResource(f);
          Shape s = new Shape(fr);
          double currentPerimeter = getPerimeter(s);
          if(maxPerimeter<currentPerimeter){
            maxPerimeter = currentPerimeter;
            fileReference = f;
        }
      }
      return fileReference;
    }

    public void testPerimeterMultipleFiles()
    {
      System.out.println("maximum perimeter = "+getLargestPerimeterMultipleFiles());
      System.out.println("file name with maximum perimeter = "+getFileWithLargestPerimeter().getName());
    }

    public static void main (String[] args) {
        PerimeterRunner pr = new PerimeterRunner();
        pr.testPerimeter();
        pr.testPerimeterMultipleFiles();
    }
}
