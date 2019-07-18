import edu.duke.DirectoryResource;
import edu.duke.ImageResource;
import edu.duke.Pixel;

import java.util.function.Function;
import java.util.stream.StreamSupport;

public class ImageConverter {
    static void normalize(Pixel p)
    {
        p.setAlpha(0);
        int avg = (p.getGreen()+p.getBlue()+p.getRed())/3;
        p.setBlue(avg);
        p.setRed(avg);
        p.setGreen(avg);

    }

    static void invertPixel(Pixel p)
    {
        p.setRed(255-p.getRed());
        p.setBlue(255-p.getBlue());
        p.setGreen(255-p.getBlue());
    }

    static ImageResource convertToGrayScale(ImageResource oldImage)
    {
        ImageResource newImage = new ImageResource();
        newImage.setFileName("gray-"+oldImage.getFileName());
        for(int i=0;i<oldImage.getWidth();i++)
            for(int j=0;j<oldImage.getHeight();j++)
            {
                normalize(oldImage.getPixel(i,j));
                newImage.setPixel(i,j,oldImage.getPixel(i,j));
            }
        return newImage;
    }

    static void imageTransformer(Function<ImageResource,ImageResource> process)
    {
        DirectoryResource dr = new DirectoryResource();
        StreamSupport.stream(dr.selectedFiles().spliterator(),false).
                map(ImageResource::new).
                map(process).forEach(ImageResource::save);
        
    }

    static ImageResource invertImage(ImageResource oldImage)
    {
        ImageResource newImage = new ImageResource();
        newImage.setFileName("inverted-"+oldImage.getFileName());
        for(int i=0;i<oldImage.getWidth();i++)
            for(int j=0;j<oldImage.getHeight();j++)
            {
                invertPixel(oldImage.getPixel(i,j));
                newImage.setPixel(i,j,oldImage.getPixel(i,j));
            }
        return newImage;
    }

    public static void main(String[] args) {
        imageTransformer(ImageConverter::convertToGrayScale);
        imageTransformer(ImageConverter::invertImage);
    }

}
