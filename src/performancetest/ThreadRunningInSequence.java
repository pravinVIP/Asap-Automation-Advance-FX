package performancetest;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import output.OutputWriter;
import sample.Progress;
import sample.Progress.*;
import java.io.IOException;

import static sample.FXMLController.totalUrl;
import static sample.Progress.*;

class progress extends Thread {

    @Override
    public void run() {
//        Stage stage=new Stage();
//        Parent root = null;
//        try {
//            root = FXMLLoader.load(getClass().getResource("progress.fxml"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        Scene scene = new Scene(root);
//        scene.getStylesheets().add("/StyleSheet/style.css");
//
//        stage.setTitle("Performance Tool");
//        stage.setScene(scene);
//        stage.resizableProperty().set(false);
//        stage.show();
        for(;;){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {



                    //   jLabel5.setText(urlLable);

                    //   jLabel6.setText(processLable);
                    // obtained * 100 / total
                    try {
                        percentTxt = Double.valueOf(Math.round(((int) Math.round(loadTxt)) * 100 / totalUrl));
                        percentBar = Double.valueOf(Math.round(((long) Math.round(loadBar)) * 100 / totalUrl));


//
                        System.out.println("####################################");
                        System.out.println("urlLable###"+urlLable+" & processLable"+processLable);

                        System.out.println("percent#$#"+percentTxt+" & "+percentBar);
                        sample.Progress.setPerLabel(percentTxt);
                        sample.Progress.setProgressLabel(percentBar/100);
                        sample.Progress.setUrlLabel(urlLable);
                        sample.Progress.setStatusLabel(processLable);
                        System.out.println("####################################");
//
                        //     loadbar.setProgress(percentBar);

                    }catch (Exception e){}




                }
            });
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}

class UrlTrack extends Thread {

    @Override
    public void run() {
        ThreadJoining1 t1= new ThreadJoining1();
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ThreadJoining5 t5= new ThreadJoining5();
        t5.start();
        try {
            t5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
class ThreadJoining1 extends Thread
{
    @Override
    public void run()
    {

        har.Har har = new har.Har();

        har.UrlTracker();
    }
}
class ThreadJoining5 extends Thread
{
    @Override
    public void run(){
        try {
            new output.OutputWriter().complete();
        }catch(Exception e){}
    }
}
//class DataTrack extends Thread {
//
//    @Override
//    public void run() {
//        new Progress().startProgress();
//    }}

public class ThreadRunningInSequence {
    public static void UI(){
        Stage stage=new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(Progress.class.getResource("progress.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/StyleSheet/style.css");

        stage.setTitle("Performance Tool");
        stage.setScene(scene);
        stage.resizableProperty().set(false);
        stage.show();

    }
    public static void runn() {
        try {
            // System.out.println("start");
            UI();
            Thread t2 = new UrlTrack();
            t2.start();
            Thread t3 = new progress();
            t3.start();


//
        } catch (Exception ex) {

            OutputWriter.appendToFile(ex);
            ex.printStackTrace();
        }

    }
}
