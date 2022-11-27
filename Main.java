package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    static int number = 200, width = 1000, height = 650;
    static double mouseX, mouseY, attraction = 0.1, friction = 0.995, boom1 = 2, boom2 = -2;
    static Canvas cnv = new Canvas(width, height);
    static GraphicsContext graph = cnv.getGraphicsContext2D();
    static double[] x = new double[number];
    static double[] y = new double[number];
    static double[] x2 = new double[number];
    static double[] y2 = new double[number];
    static double[] x3 = new double[number];
    static double[] y3 = new double[number];
    static double[] x4 = new double[number];
    static double[] y4 = new double[number];
    static double[] x5 = new double[number];
    static double[] y5 = new double[number];
    static double[] vx = new double[number];
    static double[] vy = new double[number];

    @Override
    public void start(Stage primaryStage) throws Exception{
        AnchorPane root = new AnchorPane();
        root.getChildren().add(cnv);
        cnv.setVisible(true);
        graph.setStroke(Color.BEIGE);
        graph.setLineWidth(3);

        Scene scene = new Scene(root, width, height);
        scene.setFill(Color.BLACK);

        scene.setOnMouseClicked(event -> {
            for (int i = 0; i <number; i++) {
                if(x[i]>mouseX && vx[i]<0) {
                    vx[i] *= boom2;
                }
                else if(x[i]>mouseX && vx[i]>0) {
                    vx[i] *= boom1;
                }
                else if(x[i]<mouseX && vx[i]<0) {
                    vx[i] *= boom1;
                }
                else if(x[i]<mouseX && vx[i]>0) {
                    vx[i] *= boom2;
                }
                if(y[i]>mouseY && vy[i]<0) {
                    vy[i] *= boom2;
                }
                else if(y[i]>mouseY && vy[i]>0) {
                    vy[i] *= boom1;
                }
                else if(y[i]<mouseY && vy[i]<0) {
                    vy[i] *= boom1;
                }
                else if(y[i]<mouseY && vy[i]>0) {
                    vy[i] *= boom2;
                }
            }
        });

        scene.setOnMouseMoved(event -> {
            mouseX = event.getX();
            mouseY = event.getY();
        });

        drawParticles();
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), event -> {
            graph.setFill(new Color(0, 0, 0, 0.1));
            graph.fillRect(0, 0, width, height);
            for (int i = 0; i <number; i++) {
                if(x[i]>mouseX)
                {
                    vx[i]-=attraction;
                }
                else if(x[i]<mouseX)
                {
                    vx[i]+=attraction;
                }
                if(y[i]>mouseY)
                {
                    vy[i]-=attraction;
                }
                else if(y[i]<mouseY)
                {
                    vy[i]+=attraction;
                }

                vx[i]*=friction;
                vy[i]*=friction;
                x5[i]=x4[i];
                y5[i]=y4[i];
                x4[i]=x3[i];
                y4[i]=y3[i];
                x3[i]=x2[i];
                y3[i]=y2[i];
                x2[i]=x[i];
                y2[i]=y[i];
                x[i]+=vx[i];
                y[i]+=vy[i];
                graph.setStroke(Color.BEIGE);
                graph.beginPath();
                graph.lineTo(x[i], y[i]);
                graph.stroke();
                graph.setStroke(Color.rgb(218, 225, 227, 0.75));
                graph.beginPath();
                graph.lineTo(x2[i], y2[i]);
                graph.stroke();
                graph.setStroke(Color.rgb(218, 225, 227, 0.5));
                graph.beginPath();
                graph.lineTo(x3[i], y3[i]);
                graph.stroke();
                graph.setStroke(Color.rgb(218, 225, 227, 0.3));
                graph.beginPath();
                graph.lineTo(x3[i], y3[i]);
                graph.stroke();
                graph.setStroke(Color.rgb(218, 225, 227, 0.1));
                graph.beginPath();
                graph.lineTo(x3[i], y3[i]);
                graph.stroke();
            }
        }));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Tracking particles");
        stage.show();
    }

    public static void drawParticles()
    {
        for (int i = 0; i <number; i++) {
            x[i] = Math.random()*width;
            y[i] = Math.random()*height;
            graph.beginPath();
            graph.lineTo(x[i], y[i]);
            graph.stroke();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
