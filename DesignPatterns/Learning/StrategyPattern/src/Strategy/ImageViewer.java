package Strategy;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class ImageViewer implements Viewer {
    File mFile;
    JFrame frame;
    JLabel imageLabel;

    public ImageViewer(String name)throws IOException
    {
        mFile = new File(name);
        if(!mFile.exists())
            throw  new FileNotFoundException("File Name:"+name+" was not found");
        frame  = new JFrame();
        imageLabel = new JLabel();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.getContentPane().add(imageLabel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);

    }

    @Override
    public void show() {
        try {
            Image i = ImageIO.read(mFile);
            ImageIcon image = new ImageIcon(i);
            imageLabel.setIcon(image);
            frame.setSize(image.getIconWidth(),image.getIconHeight());
            frame.repaint();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
