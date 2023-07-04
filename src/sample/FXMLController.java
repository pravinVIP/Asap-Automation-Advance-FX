package sample;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.List;

import har.Har;
import har.PerformanceHar;
import input.InputReader;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import output.OutputWriter;
import performancetest.ThreadRunningInSequence;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import static output.OutputWriter.folder;
import static sample.Main.primary;

public class FXMLController implements Initializable {

    public static String environ="";
    public static String project1="";
    public static int totalUrl=0;
   MyNumber myNum =  new  MyNumber();
   static ListView dndList;
   public static List<List<String>> arrList = new ArrayList<>();
    private List<File> fileList;

    @FXML
    Label jLabel5;
    @FXML
    Label jLabel6;

    @FXML
    public  TextField project;
    @FXML
    public  BorderPane borderPane;
    @FXML
    private TabPane tabPane;
    @FXML
    private  Tab tab2;
    @FXML
     TableView<Parameter> table;

    @FXML
    private TableColumn<Parameter, String> key;
    @FXML
    private TableColumn<Parameter, String> value;
    @FXML
    public  ComboBox<String> env;



    public  static String location="";
    public static LinkedList<String> HarList = new LinkedList<String>();
    public ObservableList<String> list1 = FXCollections.observableArrayList("Production", "Pre-Production");
    public ObservableList<Parameter> list = FXCollections.observableArrayList(
            new Parameter("Js/Css/font Cache", "31536000"),
            new Parameter("Html Cache", "600"),
            new Parameter("Img Cache", "3600"),
            new Parameter("Api Cache", "900"),
            new Parameter("Domain", "skavaone.com;cloudfront.net;images.salsify.com"),
            new Parameter("JS calls", "all.js;loadJS;lazy;jquery.min.js"),
            new Parameter("CSS calls", "all.css;loadCSS"),
            new Parameter("File Size", "100000"),
            new Parameter("calls counts", "5"),
            new Parameter("CDN", "cloudfront;edgekey;akamai;yottaa"),
            new Parameter("Transport_Security", "2628000")
          //  new Parameter("Excel Location", "C:\\Users\\Lenovo\\Desktop\\Tool Testing\\sheet.xls")
    );

    public FXMLController() {
    }


//    @FXML
//    private void dOver(DragEvent event) {
//        if (event.getGestureSource() != dndList && event.getDragboard().hasFiles()) {
//
//            event.acceptTransferModes(TransferMode.COPY);
//        }
//
//    }
//
//    /**
//     * The below method is triggered once you have dropped the files on the
//     * panel.
//     */
//    @FXML
//    private void dragdrop(DragEvent event) {
//        
//        Dragboard db = event.getDragboard();
//        boolean success = false;
//        if (db.hasFiles()) {
//            success = true;
//            String filePath = null;
//            fileList = db.getFiles();
//            for (File f : fileList) {
//                System.out.println("File attached:" + f.getName());
//
//                dndList.getItems().add(f.getPath());
//            }
//        }
//        event.setDropCompleted(success);
//
//    }


    public List<List<String>> onShow() {

        Parameter product = new Parameter();
        List<List<String>> arrList = new ArrayList<>();
        for (int i = 0; i < table.getItems().size(); i++) {
            product = table.getItems().get(i);
            List arrList2 = new ArrayList<>();
            arrList.add(arrList2);
            arrList.get(i).add(product.key.get());
            arrList.get(i).add(product.value.get());

        }
        return arrList;
//        for (int i = 0; i < arrList.size(); i++) {
//            for (int j = 0; j < arrList.get(i).size(); j++) {
//                System.out.println(arrList.get(i).get(j));
//
//            }
//        }

    }

    public void changeValueCellEvent(CellEditEvent edittedCell) {

        Parameter personSelected = table.getSelectionModel().getSelectedItem();
        personSelected.setValue(edittedCell.getNewValue().toString());

    }
    private int selectedIndex = -1;
    //private final ObservableList<String> harList = FXCollections.observableArrayList();
    ObservableList<Integer> selectedItems = FXCollections.observableArrayList();
    

    @FXML
    private void delete(ActionEvent event) {
        // System.out.println("clicked");
//        for(int i:selectedItems){
//            System.out.println("index"+i);
//       dndList.getItems().remove(i);
//        }
     //   ObservableList selectedIndices = dndList.getSelectionModel().getSelectedIndices();
//harList.removeAll(selectedIndices);
ObservableList<String> harSelected, allHar;
            allHar = dndList.getItems();
            harSelected = dndList.getSelectionModel().getSelectedItems();
            allHar.removeAll(harSelected);
//            for(Object o : selectedIndices){
//                System.out.println("index"+o);
//               
//            }
//            for(Object o : selectedIndices){
//                System.out.println("index"+o);
//                harList.remove((int)o);
//                harList.removeAll(selectedIndices);
//            
//            }
    }

    @FXML
    private void clear(ActionEvent event) {
        ObservableList<String>  allHar;
            allHar = dndList.getItems();
        allHar.clear();
    }


    public void but(ActionEvent actionEvent) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                Desktop.getDesktop().browseFileDirectory(new File("D:\\Daily\\3-4"));

            }
        });

    }

    public void checkHar(ActionEvent actionEvent) {
        ObservableList<String> allHar = dndList.getItems();
        List<String> checkHar = new LinkedList();
        Alert alert = null;
        ButtonType remove  = new ButtonType("Remove", ButtonBar.ButtonData.CANCEL_CLOSE);
        if (allHar.size() == 0) {
            alert = new Alert(Alert.AlertType.INFORMATION, "No File Found", ButtonType.OK);
            alert.setTitle("No File Found");
            alert.setHeaderText("Please Attach files");
        } else{
            for (String n : allHar) {
                if (n.endsWith(".har")) {
                    String bf = null;
                    try {
                        bf = PerformanceHar.readAllBytesJava7(n);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    byte[] bytes = bf.getBytes();

                    InputStream inputStream = new ByteArrayInputStream(bytes);

                    JsonObject empObj = null;
                    try {
                        try (
                                JsonReader reader = Json.createReader(inputStream)) {

                            empObj = reader.readObject();
                        }
                    } catch (Exception ex) {
                        OutputWriter.appendToFile(ex);
                        ex.printStackTrace();
                    }
                    try {
                        JsonObject addrObj = empObj.getJsonObject("log");
                        JsonArray arrObjpages = addrObj.getJsonArray("pages");
                    } catch (Exception e) {
                        checkHar.add(n);
                    }


                } else {
                    checkHar.add(n);
                }
            }


        if (checkHar.size()==0) {
            alert = new Alert(Alert.AlertType.INFORMATION, "Completed", ButtonType.OK);
            alert.setTitle("Completed");
            alert.setHeaderText("No Issues");

        } else {
//            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION, "Delete ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
//            alert1.showAndWait();
//
//            if (alert1.getResult() == ButtonType.YES) {
//                System.out.println("hi");
//            }


            alert = new Alert(Alert.AlertType.ERROR, "Error", ButtonType.OK, remove);
            alert.setTitle("Action Failed");
            alert.setHeaderText("Issue in Har");
            String issue = "";
            for (String defect : checkHar) {
                issue = issue + defect + "\n";
            }

            alert.setContentText(issue);

        }

    }
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(
                getClass().getResource("/StyleSheet/style.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");
//        alert.show();
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == remove) {

            System.out.println("haii");
            allHar.removeAll(checkHar);

        }
    }


    class ThreadJoining extends Thread
    {
        @Override
        public void run()
        {

                try
                {

                    System.out.printf("$$$$$$$$$$$$");

                    String projectName=project.getText();
                    String environment=env.getSelectionModel().getSelectedItem();
                    location="Tested Folder//"+projectName+environment+output.time.e()+"//";
                    System.out.println(projectName);
                    System.out.println(environment);
                    environ=environment;
                    project1=projectName;


                    System.out.printf("$$$$$$$$$$$$");

                    System.out.println("1");
                    ObservableList<String>  allHar=dndList.getItems();
                    totalUrl=allHar.size();

                    HarList.addAll(allHar);

                    System.out.println("TABLE");
                    Parameter product = new Parameter();

                    for (int ii = 0; ii < table.getItems().size(); ii++) {
                        product = table.getItems().get(ii);
                        List arrList2 = new ArrayList<>();
                        arrList.add(arrList2);
                        arrList.get(ii).add(product.key.get());
                        arrList.get(ii).add(product.value.get());

                    }
                    System.out.println("2");
//        performancetest.ThreadRunningToTrack a = new performancetest.ThreadRunningToTrack();
//        try {
//            //System.out.println("ThreadRunningToTrack");
//            a.startTrack();
//        } catch (IOException ex) {
//            OutputWriter.appendToFile(ex);
//            ex.printStackTrace();
//        }
                    File file = new File("Tested Folder");
                    //Creating the directory
                    boolean bool = file.mkdirs();
                    if(bool){
                        System.out.println("Directory created successfully");
                    }else{
                        System.out.println("Sorry couldnt create specified directory");
                    }
                    File file1 = new File("Tested Folder//"+project1+environ+output.time.e());
                    //Creating the directory
                    boolean bool1 = file1.mkdirs();
                    if(bool1){
                        System.out.println("Directory created successfully");
                    }else{
                        System.out.println("Sorry couldnt create specified directory");
                    }
                    output.OutputWriter write = new output.OutputWriter();
                    try{
                        System.out.println("started");
                        write.create();
                    }catch(Exception e) {
                        write.LoadingView("sheet.xls wrong location", "Pls check the proper sheet.xls location and save on Excel location in input.cxv file");
                    }


                }

                catch(Exception ex)
                {
                    System.out.println("Exception has" +
                            " been caught" + ex);
                }


        }
    }




    @FXML
    private void runOnClick(ActionEvent event) throws IOException {


        ThreadJoining t1= new  ThreadJoining();
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            Platform.runLater(() -> {

                primary.hide();
                new ThreadRunningInSequence().runn();

            });
        }).start();

//        ThreadJoining4 t5= new  ThreadJoining4();
//        t5.start();
//        ThreadJoining1  t2= new  ThreadJoining1();
//        t2.start();
//        try {
//            t2.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//      t5.stop();
//        ThreadJoining5  t6= new  ThreadJoining5();
//        t6.start();
//        try {
//            t6.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }




//        for (int i = 0; i < arrList.size(); i++) {
//            for (int j = 0; j < arrList.get(i).size(); j++) {
//                System.out.println(arrList.get(i).get(j));
//
//            }
//        }
      //  System.out.println(arrList.size());


//        File file = new File("Tested Folder");
//        //Creating the directory
//        boolean bool = file.mkdirs();
//        if(bool){
//            System.out.println("Directory created successfully");
//        }else{
//            System.out.println("Sorry couldnt create specified directory");
//        }
//        File file1 = new File("Tested Folder//"+projectName+environment);
//        //Creating the directory
//        boolean bool1 = file1.mkdirs();
//        if(bool1){
//            System.out.println("Directory created successfully");
//        }else{
//            System.out.println("Sorry couldnt create specified directory");
//        }


//        File testFile = new File("hello.txt");
//        try {
//            testFile.createNewFile();
//            System.out.println("created");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


//       tabPane.getSelectionModel().select(tab2);


//        Alert alert = new Alert(AlertType.INFORMATION, "Content here", ButtonType.OK);
//        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
//
//        DialogPane dialogPane = alert.getDialogPane();
//        dialogPane.getStylesheets().add(
//                getClass().getResource("/StyleSheet/style.css").toExternalForm());
//        dialogPane.getStyleClass().add("myDialog");
//        alert.show();

    }
private ListView createDndList() {
	    DndListView<String> dndListView = new DndListView<String>();

	 

	    dndListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	    
		return dndListView;
	}



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

       dndList = createDndList();
      dndList.setOnDragDropped(new EventHandler<DragEvent>() {
           @Override
           public void handle(DragEvent event) {
               System.out.println("dragdropq");
             Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasFiles()) {
            success = true;
            String filePath = null;
            fileList = db.getFiles();
            for (File f : fileList) {
                System.out.println("File attached:" + f.getName());

                dndList.getItems().add(f.getPath());
            }
        }
        event.setDropCompleted(success);
           }
           });
      dndList.setOnDragOver(new EventHandler<DragEvent>() {
           @Override
           public void handle(DragEvent event) {
               System.out.println("dragoverq");
                if (event.getGestureSource() != dndList && event.getDragboard().hasFiles()) {

            event.acceptTransferModes(TransferMode.COPY);
        }
           }
           });
       borderPane.setCenter(dndList);
			
      
        
        //dndList.setItems(harList);

        dndList.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                String selectedItem = dndList.getSelectionModel().getSelectedItems().toString();
                selectedItems = dndList.getSelectionModel().getSelectedIndices();
            }
        });

        key.setCellValueFactory(new PropertyValueFactory<Parameter, String>("key"));
        value.setCellValueFactory(new PropertyValueFactory<Parameter, String>("value"));

        env.setItems(list1);

if(new File("Data//data.bin").exists()){
    list.clear();
    List<List<String>> arrList = null;

    try {
        FileInputStream fis = new FileInputStream("Data//data.bin");
        ObjectInputStream inputStream = new ObjectInputStream(fis);
        arrList = (List<List<String>>) inputStream.readObject();
        inputStream.close();
    } catch (IOException | ClassNotFoundException ex) {
        System.err.println(ex);
    }
    for (int i = 0; i < arrList.size(); i++) {

        list.add(new Parameter(arrList.get(i).get(0).toString(),arrList.get(i).get(1).toString()));
//        for (int j = 0; j < arrList.get(i).size(); j++) {
//            System.out.println(arrList.get(i).get(j));
//
//        }
    }
    System.out.println("deserilize");
}
        table.setItems(list);

        table.setEditable(true);
        value.setOnEditCommit(new EventHandler<CellEditEvent<Parameter, String>>() {

            @Override
            public void handle(CellEditEvent edittedCell) {
                Parameter personSelected = table.getSelectionModel().getSelectedItem();
                personSelected.setValue(edittedCell.getNewValue().toString());

            }
        });

        value.setCellFactory(TextFieldTableCell.<Parameter>forTableColumn());
        InputReader a=new InputReader();
        a.show(onShow());


    }

}
