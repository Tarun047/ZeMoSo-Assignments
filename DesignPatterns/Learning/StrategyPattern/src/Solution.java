import Strategy.Display;
import Strategy.ImageViewer;
import Strategy.TextViewer;

import java.io.IOException;

public class Solution {
    public static void main(String[] args)throws IOException
    {
        Display myDisplay = new Display();

        myDisplay.setDisplayType(new TextViewer("Coder.txt"));
        myDisplay.show();

        myDisplay.setDisplayType(new ImageViewer("sample.bmp"));
        myDisplay.show();

    }
}
