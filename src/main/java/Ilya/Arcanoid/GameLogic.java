package Ilya.Arcanoid;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;


/**
 *
 * @author Kovalev I.V. & Chaban Diman
 */
public class GameLogic extends Pane{


    ArrayList<Brick>RemWall;
    ArrayList<Brick> wall;
    Racket Racket;
    Ball ball;
    Double size;
    int lscore,rscore;
    Timeline t;
    double widthBrick;
    double heightBrick;

    boolean LeftPress,RightPress;

    double grow;

    public GameLogic(){

    }

    public GameLogic(final double size){


        this.lscore=0;
        this.rscore=0;
        this.wall=new ArrayList<Brick>();
        this.RemWall = new ArrayList<Brick>();
        this.size=size;
        this.widthBrick=this.size/8;
        this.heightBrick=this.size*0.4/4;

        Racket racket = new Racket(size*0.48, size*0.98, size);
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

        Ball ball = (Ball) context.getBean("ball");
        ball.posx=size*0.48;
        ball.posy=size*0.48;
        ball.ballMoverX = size*0.02;
        ball.ballMoverY = size*0.01;
        this.Racket=racket;

        this.ball=ball;

        this.LeftPress=false;
        this.RightPress=false;

        this.setMaxHeight(size);
        this.setMaxWidth(size);
        this.setMinHeight(size);
        this.setMinWidth(size);

        for (double a=0;a<=size*0.4-40;a+=this.heightBrick){
            for (double d=0;d<=size-50;d+=this.widthBrick) {
                Brick brick = new Brick(d,a);
                this.getChildren().add(brick);
                brick.setLayoutX(brick.posx);
                brick.setLayoutY(brick.posy);
                wall.add(brick);
                RemWall.add(brick);
            }
        }

        this.getChildren().add(ball);
        this.getChildren().add(Racket);

        this.Racket.setLayoutX(Racket.posx);
        this.Racket.setLayoutY(Racket.posy);
        this.ball.setLayoutX(ball.posx);
        this.ball.setLayoutY(ball.posy);

        this.setStyle("-fx-background: black;");
        final Timeline t = new Timeline(new KeyFrame(Duration.millis(30), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                ball.move(ball.ballMoverX, ball.ballMoverY);
                ball.ballMoverX=ball.ballMoverX*0.999;
                ball.ballMoverY=ball.ballMoverY*0.999;
                for (int number=0;number<wall.size();number++){
                    if ((ball.posy-size*0.02<wall.get(number).posy+40 && ball.posy-size*0.02>wall.get(number).posy ||
                            ball.posy+size*0.02<wall.get(number).posy+40 && ball.posy+size*0.02>wall.get(number).posy)&&
                            ball.posx>wall.get(number).posx && ball.posx<wall.get(number).posx+50 ){
                        wall.get(number).setLayoutY(900);
                        wall.get(number).setLayoutX(900);
                        if (ball.posy<wall.get(number).posy+32 && ball.posy>wall.get(number).posy
                                && (ball.posx+size*0.02<wall.get(number).posx+20 &&ball.posx+size*0.02>wall.get(number).posx
                                ||ball.posx-size*0.02<wall.get(number).posx+50 &&ball.posx-size*0.02>wall.get(number).posx+20)){

                            ball.ballMoverY=ball.ballMoverY*0.7;
                            ball.ballMoverX=-ball.ballMoverX*1.1;
                            ball.move(ball.ballMoverX, ball.ballMoverY);
                            wall.remove(wall.get(number));
                        }else{
                        ball.ballMoverY=-ball.ballMoverY*0.95;
                        ball.ballMoverX=ball.ballMoverX*0.95;
                        ball.move(ball.ballMoverX, ball.ballMoverY);
                        wall.remove(wall.get(number));

                            }
                    }
                }

                if( ball.posy< size*0.03){
                    ball.ballMoverY=-ball.ballMoverY*0.95;
                    ball.ballMoverX=ball.ballMoverX*0.95;
                    ball.move(ball.ballMoverX, ball.ballMoverY);
                }
                if( ball.posx< size*0.02||ball.posx>size*0.98){
                    ball.ballMoverY=ball.ballMoverY*0.95;
                    ball.ballMoverX=-ball.ballMoverX*0.95;
                    ball.move(ball.ballMoverX, ball.ballMoverY);
                }
                if(ball.posy>size * 0.96 && (ball.posx+size*0.02 > Racket.posx  && ball.posx-size*0.02 < Racket.posx + size*0.2)){

                    Double delta = ball.posx - Racket.posx -size*0.2;
                    Double bounceAngle= delta*(Math.PI/(180));
                    ball.ballMoverY=-ball.ballMoverY*0.97 + size*0.01*Math.sin(bounceAngle);
                    ball.ballMoverX=ball.ballMoverX*0.97 + size*0.01*Math.cos(bounceAngle);
                    ball.move(ball.ballMoverX, ball.ballMoverY);
                }
                if (ball.posy > size){
                    ball.posx=size*0.48;
                    ball.posy=size*0.48;
                    ball.setLayoutX(ball.posx);
                    ball.setLayoutY(ball.posy);
                    ball.ballMoverX=0.0;
                    ball.ballMoverY=0.0;
                }
                if(LeftPress){
                    if(Racket.posx>(size*0.03)){
                        Racket.moveLeft();

                    }
                }
                else if(RightPress){
                    if(Racket.posx<(size*0.8)){
                        Racket.moveRight();
                    }
                }
                refresh();
            }
        }));

        t.setCycleCount(Timeline.INDEFINITE);
        this.t =t;
    }

    public void Wall(){
        for (int i=0;i<wall.size();i++){
            this.getChildren().remove(wall.get(i));
        }
        this.wall=new ArrayList<>();

        for (double a=0;a<=size*0.4-40;a+=this.heightBrick){
            for (double d=0;d<=size-50;d+=this.widthBrick) {
                Brick brick = new Brick(d,a);
                this.getChildren().add(brick);
                brick.setLayoutX(brick.posx);
                brick.setLayoutY(brick.posy);
                wall.add(brick);
                refresh();
            }
        }
    }

    public void refresh(){
        this.Racket.setLayoutX(Racket.posx);
        this.Racket.setLayoutY(Racket.posy);
        this.ball.setLayoutX(ball.posx);
        this.ball.setLayoutY(ball.posy);
    }
}
