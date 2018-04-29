
package Ilya.Arcanoid;

import javafx.scene.shape.Rectangle;



/**
 *
 * @author Kovalev I.V.
 */
public class Movable extends Rectangle{

    double posx,posy;

    public Movable(double posx,double posy){
        this.posx=posx;
        this.posy=posy;
    }

    public void move(double xMove, double yMove){
        this.posx = posx + xMove;
        this.posy = posy + yMove;
    }

}
