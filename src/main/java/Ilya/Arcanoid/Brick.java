package Ilya.Arcanoid;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import org.springframework.context.annotation.Bean;

import java.util.Random;

public class Brick extends Movable{

    String[] colors = {"MyBrick.png","MyBrickBlue.png","MyBrickBlueGrey.png",
            "MyBrickBlueOrange.png","MyBrickGreen.png"};

    Random random = new Random();

    public Brick(double posx, double posy) {
        super(posx,posy);
        this.setWidth(50);
        this.setHeight(40);
        int i = random.nextInt(colors.length);
        this.setFill(new ImagePattern(new Image(String.valueOf(getClass().getResource("/Images/"+colors[i])))));
    }

}
