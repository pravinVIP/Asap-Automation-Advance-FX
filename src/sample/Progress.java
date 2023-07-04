package sample;

import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.text.TextAlignment;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import static output.OutputWriter.folder;
import static sample.FXMLController.totalUrl;

public class Progress implements Initializable {
    @FXML
    public Label lbllord;
    @FXML
    public ProgressBar loadbar;
@FXML
public Label urlProgressLable;
@FXML
public Label statusLable;


    public static String urlLable = "waiting";
    public static String processLable = "waiting";
    public static Double loadTxt = 0.0;
    public static Double loadBar = 0.0;
    public static Double percentTxt;
    public static Double percentBar;
   public static MyNumber myNum=new MyNumber();
    public static MyNumber1 myNum1=new MyNumber1();
    public static MyString mystr=new MyString();
    public static MyString1 mystr1=new MyString1();
    static HostServices Host;
    public void button(ActionEvent actionEvent) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                System.out.println("open folder:"+folder);

//                try {
//                    Runtime.getRuntime().exec("explorer.exe /select," + "D:\\");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                try {
//                    System.out.println(folder);
//                    Process builder = Runtime.getRuntime().exec("cmd /c start "+folder);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                try {
                    Desktop.getDesktop().open(new File(folder));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

    }
    public static void setPerLabel(Double d){
        myNum.setNumber(d);
    }
    public static void setProgressLabel(Double d){
        myNum1.setNumber(d);
    }
    public static void setUrlLabel(String d){
        mystr.setNumber(d);
    }
    public static void setStatusLabel(String d){
        mystr1.setNumber(d);
    }

    //    class ThreadJoining4 extends Thread
//    {
//        @Override
//        public void run()
//        {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Completed", ButtonType.OK);
//            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
//
//            DialogPane dialogPane = alert.getDialogPane();
//            dialogPane.getStylesheets().add(
//                    getClass().getResource("/StyleSheet/style.css").toExternalForm());
//            dialogPane.getStyleClass().add("myDialog");
//            alert.show();
//        }
//    }
//    class ThreadJoining5 extends Thread
//    {
//        @Override
//        public void run(){
//            try {
//                new output.OutputWriter().complete();
//            }catch(Exception e){}
//        }
//    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

      myNum.setNumber(0);
      myNum.numberProperty().addListener(
              new ChangeListener<Object>() {
                  @Override
                  public void changed(ObservableValue<? extends Object> observableValue, Object number, Object t1) {
                      lbllord.setText(Double.toString(myNum.getNumber()));
                  }
              }
      );
        myNum1.setNumber(0);
        myNum1.numberProperty().addListener(
                new ChangeListener<Object>() {
                    @Override
                    public void changed(ObservableValue<? extends Object> observableValue, Object number, Object t1) {
                        loadbar.progressProperty().bind(myNum1.numberProperty());
                                //setText(new Double(myNum1.getNumber()).toString());
                    }
                }
        );
        //myNum.setNumber(myNum.getNumber()+10);
        mystr.setNumber("Waiting");
        mystr.numberProperty().addListener(
                new ChangeListener<Object>() {
                    @Override
                    public void changed(ObservableValue<? extends Object> observableValue, Object number, Object t1) {
                        ComboBox<TextAlignment> textAlignmentBox = new ComboBox<>();
                        textAlignmentBox.getItems().addAll(TextAlignment.values());
                        textAlignmentBox.getSelectionModel().select(TextAlignment.LEFT);
                        urlProgressLable.textAlignmentProperty().bind(textAlignmentBox.valueProperty());
                        urlProgressLable.setText(mystr.getNumber());
                    }
                }
        );
        mystr1.setNumber("Waiting");
        mystr1.numberProperty().addListener(
                new ChangeListener<Object>() {
                    @Override
                    public void changed(ObservableValue<? extends Object> observableValue, Object number, Object t1) {
                        statusLable.setText(mystr1.getNumber());
                    }
                }
        );

   }

//    public void startProgress() {
//
//        for(;;){
////                jLabel5.setText(urlLable);
////
////                jLabel6.setText(processLable);
//            // obtained * 100 / total
//            try {
//                percentTxt = Math.round(((int) Math.round(loadTxt)) * 100 / totalUrl);
//                percentBar = Double.valueOf(Math.round(((long) Math.round(loadBar)) * 100 / totalUrl));
//
//                System.out.println("load###"+loadTxt+" & "+loadBar);
//
//                System.out.println("percent#$#"+percentTxt+" & "+percentBar);
////
////                    lbllord.setText(Integer.toString(percentTxt) + "%");
////
////                    loadbar.setProgress(percentBar);
//                setlbllord("0%");
//                setloadbar(0.50);
//            }catch (Exception e){}
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//    }
}
