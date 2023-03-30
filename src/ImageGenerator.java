import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ImageGenerator {

    public void create(InputStream inputStream, String fileName, String comment, InputStream inputStreamOver) throws IOException {

        //read image
//        BufferedImage originalImage = ImageIO.read(new File("assets/movie_image_bigger.jpg"));//read from assets folder

        //https://m.media-amazon.com/images/M/MV5BY2Q2NDI1MjUtM2Q5ZS00MTFlLWJiYWEtNTZmNjQ3OGJkZDgxXkEyXkFqcGdeQXVyNTI4MjkwNjA@._V1_UX128_CR0,3,128,176_AL_.jpg
        //read from URL
//        InputStream inputStrem = new URL("https://m.media-amazon.com/images/M/MV5BY2Q2NDI1MjUtM2Q5ZS00MTFlLWJiYWEtNTZmNjQ3OGJkZDgxXkEyXkFqcGdeQXVyNTI4MjkwNjA@.jpg").openStream();
        BufferedImage originalImage = ImageIO.read(inputStream);


        //create new image in memory with new size and filter
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int newHeigh = height + 200;
        BufferedImage newImage = new BufferedImage(width, newHeigh, BufferedImage.TRANSLUCENT);


        //copy original image to new image, graphics is like a pen to write in this image
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(originalImage, 0, 0, null);

        //draw my_image, passed as inputStreamOver
        BufferedImage imageOver = ImageIO.read(inputStreamOver);
        int positionImageOverY = newHeigh - imageOver.getHeight();
        graphics.drawImage(imageOver, 0, positionImageOverY, null);


        //customize font
        var font = new Font("Impact", Font.BOLD, 80);
        graphics.setColor(Color.GREEN);
        graphics.setFont(font);
        graphics.setStroke(new BasicStroke(2f)); // Set border width


        //write sentence in the new image
        //my solution to center text
        var center = width / 2 - (comment.length() * 20);    //lets image each char 20px
        var centerHeight = newHeigh - 100;

        //alura solution
        FontMetrics fontMetrics = graphics.getFontMetrics();
        Rectangle2D rectangle = fontMetrics.getStringBounds(comment, graphics);
        int textWidth = (int) rectangle.getWidth();
        var center2 = (width - textWidth) / 2;


        graphics.drawString(comment, center, centerHeight);

        //to outline, my solution
//        graphics.setColor(Color.GREEN);
//        graphics.drawString(text, center - 1, newHeigh - 101); // Draw the string again with offset


        //to outline, alura solution
        //textLayout
        FontRenderContext fontRenderContext = graphics.getFontRenderContext();
        var textLayout = new TextLayout(comment, font, fontRenderContext);
        //pencil
        Shape outline = textLayout.getOutline(null);
        AffineTransform transform = graphics.getTransform();
        transform.translate(center, centerHeight);
        graphics.setTransform(transform);
        //
        var outlineStroke = new BasicStroke(width * 0.004f);
        graphics.setStroke(outlineStroke);
        graphics.setColor(Color.BLACK);
        graphics.draw(outline);
        graphics.setClip(outline);


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
        BufferedImage resizedImage = new BufferedImage(myWidth, myHeight, myImage.getType());

//        graphics.drawImage(myImage, 0,0,null);

        //write new image in new file
        ImageIO.write(newImage, "png", new File("assets/" + fileName));

    }

//    public static void main(String[] args) throws IOException {
//        var test = new ImageGenerator();
//        test.create();
//    }
}
