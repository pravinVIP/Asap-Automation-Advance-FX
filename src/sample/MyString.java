package sample;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MyString {
    private StringProperty number;
    public  final String getNumber(){
        if(number!=null){
            System.out.println("get"+number);
            return  number.get();}
        System.out.println("get0");
        return "";
    }
    public final void setNumber(String number){
        System.out.println("set"+number);
        this.numberProperty().set(number);
    }

    public final StringProperty numberProperty(){
        if(number ==null){
            number =new SimpleStringProperty("");
        }
        return number;
    }
}
