import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageGenerator {

    public void create() throws IOException {

        //read image
        BufferedImage originalImage = ImageIO.read(new File("assets/movie_image_bigger.jpg"));
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int newHeigh = height + 200;

        //create new image in memory with new size and filter
        BufferedImage newImage = new BufferedImage(width,newHeigh, BufferedImage.TRANSLUCENT);
        Graphics2D graphics =  (Graphics2D) newImage.getGraphics();

        //copy original image to new image, graphics is like a pen to write in this image
        graphics.drawImage(originalImage, 0, 0, null);

        //customize font
        var font = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setColor(Color.GREEN);
        graphics.setFont(font);


        //write sentence in the new image
//        var text  = "BEST!";
        var text  = "BEST ONE!";
        var center = width/2 - (text.length() * 20);    //lets image each char 20px
        System.out.println(center);
        graphics.drawString(text, center , newHeigh - 100);

        //write new image in new file
        ImageIO.write(newImage, "png", new File("assets/movie_image_new.png"));

    }

    public static void main(String[] args) throws IOException {
        var test = new ImageGenerator();
        test.create();
    }
}
