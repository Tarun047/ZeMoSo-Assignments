package Strategy;

import javax.swing.*;
import java.awt.*;
import java.io.*;
public class TextViewer implements Viewer {
    File mFile;
    JTextArea textArea;
    JFrame frame;


    public TextViewer(String name)throws IOException
    {
        mFile = new File(name);
        if(!mFile.exists())
            throw new FileNotFoundException("File name:"+name+" not found");

        textArea = new JTextArea(10,10);
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(textArea, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }



    @Override
    public void show()
    {
        try{
            
            FileReader fr = new FileReader(mFile);
            int c;
            StringBuffer sb = new StringBuffer();
            textArea.setText("Reading Text File now!");
            while((c=fr.read())!=-1)
            {
                sb.append((char)c);
            }
            textArea.setText(sb.toString());

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
