package Ilya.Arcanoid;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import org.springframework.context.ConfigurableApplicationContext;

import java.awt.*;
import java.io.File;
import java.util.Random;

/**
 *
 * @author Kovalev I.V.
 */

public class Tennis extends Application {

    @Override
    public void stop(){

    }

    @Override
    public void start(Stage primaryStage) {

        final GameLogic game = new GameLogic(400);

        HBox gameBox= new HBox(game);
        gameBox.setAlignment(Pos.CENTER);
        Button StartButton = new Button();
        StartButton.setPrefHeight(80);
        StartButton.setPrefWidth(160);
        StartButton.setBackground(new Background(new BackgroundImage(new Image(String.valueOf(getClass().getResource("/Images/Play.png")),160,80,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));

        Button ExitButton = new Button();
        ExitButton.setPrefSize(160,80);
        ExitButton.setBackground(new Background(new BackgroundImage(new Image(String.valueOf(getClass().getResource("/Images/Exit.png")),160,80,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));

        VBox startPain = new VBox(StartButton,ExitButton);
        startPain.setPrefSize(300,400);

        startPain.setBackground(new Background(new BackgroundImage(new Image(String.valueOf(getClass().getResource("/Images/StartPane.png")),320,410,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT)));
        startPain.setAlignment(Pos.CENTER);
        startPain.setSpacing(25);

        gameBox.setPadding(new Insets(5));
        BackgroundImage myBI= new BackgroundImage(new Image(String.valueOf(getClass().getResource("/Images/Field.png")),400,410,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        game.setBackground(new Background(myBI));
        BorderPane root = new BorderPane();
        root.setCenter(gameBox);
        Scene GameScene = new Scene(root);
        Scene scene = new Scene(startPain);

        primaryStage.setTitle("Arkanoid");
        primaryStage.setScene(scene);
        primaryStage.show();

        ExitButton.setOnAction(event->{
            primaryStage.close();
        });
        StartButton.setOnAction(event -> {
            primaryStage.setScene(GameScene);
            primaryStage.show();
        });
        GameScene.setOnKeyPressed(event -> {
                if(event.getCode()==KeyCode.LEFT){
                    game.LeftPress=true;
                }

                if(event.getCode()==KeyCode.RIGHT){
                    game.RightPress=true;
                }
        });

        scene.setOnKeyReleased(event->{
             if (event.getCode()==KeyCode.ESCAPE){
                        primaryStage.close();
                }
        });

        GameScene.setOnKeyReleased(event -> {
                if(event.getCode()==KeyCode.LEFT){
                    game.LeftPress=false;
                }
                if(event.getCode()==KeyCode.RIGHT){
                    game.RightPress=false;
                }
                if (event.getCode()==KeyCode.ESCAPE){
                    game.t.stop();
                    primaryStage.setScene(scene);}
                if(event.getCode()==KeyCode.ENTER){

                    Random r = new Random();
                    double xSpeed=0;

                    do{
                        xSpeed= r.nextDouble()*20 -10;
                    }
                    while(Math.abs(xSpeed)<5);
                    game.ball.ballMoverX=game.size*0.002*(xSpeed);
                    game.ball.ballMoverY=game.size*0.002*(r.nextDouble()*20+1 );
                    game.ball.posx= game.size*0.48;
                    game.ball.posy= game.size*0.48;
                    game.Wall();
                    game.refresh();
                    game.t.play();
                }
        });
    }
    public static void main(String[] args) {
        launch(args);
    }

}
