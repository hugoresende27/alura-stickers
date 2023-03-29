import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ImageGenerator {

    public void create(InputStream inputStream, String fileName) throws IOException {

        //read image
//        BufferedImage originalImage = ImageIO.read(new File("assets/movie_image_bigger.jpg"));//read from assets folder

        //https://m.media-amazon.com/images/M/MV5BY2Q2NDI1MjUtM2Q5ZS00MTFlLWJiYWEtNTZmNjQ3OGJkZDgxXkEyXkFqcGdeQXVyNTI4MjkwNjA@._V1_UX128_CR0,3,128,176_AL_.jpg
        //read from URL
//        InputStream inputStrem = new URL("https://m.media-amazon.com/images/M/MV5BY2Q2NDI1MjUtM2Q5ZS00MTFlLWJiYWEtNTZmNjQ3OGJkZDgxXkEyXkFqcGdeQXVyNTI4MjkwNjA@.jpg").openStream();
        BufferedImage originalImage = ImageIO.read(inputStream);

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
        graphics.drawString(text, center , newHeigh - 100);

        //my custom image
        BufferedImage myImage = ImageIO.read(new File("src/images/my_image2.jpg"));

        int myHeight = 200;
        int myWidth = 200;
        double aspectRatio = (double) myImage.getWidth() / (double) myImage.getHeight();
        System.out.println(aspectRatio);

        if (aspectRatio > 1.0) {
            myHeight = (int) (myWidth / aspectRatio);
        } else {
            myWidth = (int) (myHeight * aspectRatio);
        }
        System.out.println(myHeight);
        System.out.println(myWidth);
        BufferedImage resizedImage = new BufferedImage(myWidth, myHeight,myImage.getType());

//        graphics.drawImage(myImage, 0,0,null);

        //write new image in new file
        ImageIO.write(newImage, "png", new File("assets/"+fileName));

    }

//    public static void main(String[] args) throws IOException {
//        var test = new ImageGenerator();
//        test.create();
//    }
}
