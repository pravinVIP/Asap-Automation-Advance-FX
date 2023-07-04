package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static output.OutputWriter.writer;

public class Main extends Application {
public static Stage primary;
    @Override
    public void start(Stage stage) throws Exception{
       // Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

       // scean.getStylesheets().add("/StyleSheet/style.css");

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/StyleSheet/style.css");

        stage.setTitle("Performance Tool");
        stage.setScene(scene);
        stage.resizableProperty().set(false);
        primary=stage;
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        System.out.println("STOP");

        FXMLController obj=new FXMLController();


//        System.out.println(obj.arrList.size());
//        for (int i = 0; i <  obj.arrList.size(); i++) {
//            for (int j = 0; j <  obj.arrList.get(i).size(); j++) {
//                System.out.println( obj.arrList.get(i).get(j));
//
//            }
//        }
        if(obj.arrList.size()!=0) {
            File file = new File("Data");
            //Creating the directory
            boolean bool = file.mkdirs();
            if (bool) {
                System.out.println("Directory created successfully");
            } else {
                System.out.println("Sorry couldnt create specified directory");
            }
            File file1 = new File("Tested Folder//" + "test");

            try {
                FileOutputStream fos = new FileOutputStream("Data//data.bin");
                ObjectOutputStream outputStream = new ObjectOutputStream(fos);
                outputStream.writeObject(obj.arrList);
                outputStream.close();
            } catch (IOException ex) {
                System.err.println(ex);
            }
            System.out.println("serilize");
            Platform.exit();
            System.exit(0);
        }

    }
    public static void main(String[] args) {
        launch(args);
    }



}
