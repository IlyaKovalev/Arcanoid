package Ilya.Arcanoid;


import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;



public class Racket extends Movable{

    double size;

    public Racket(double posx, double posy, double size){
        super(posx,posy);
        this.size=size;
        this.setWidth(size*0.2);
        this.setHeight(size*0.03);
        this.setFill(new ImagePattern(new Image(String.valueOf(getClass().getResource("/Images/Racket.png")))));

    }

    public void moveLeft(){
        this.move(-size*0.04,0);
    }

    public void moveRight(){
        this.move(size*0.04,0);
    }
}
