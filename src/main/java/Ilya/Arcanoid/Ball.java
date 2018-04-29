package Ilya.Arcanoid;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Ball extends Circle {

    double posx,posy;
    double ballMoverX,ballMoverY;

    public Ball() {
        this.setFill(new ImagePattern(new Image(String.valueOf(getClass().getResource("/Images/Ball2.png")))));
        this.setRadius(400 * 0.02);
    }
    public void move(double xMove, double yMove){

        this.posx = posx + xMove;
        this.posy = posy + yMove;
    }

}

