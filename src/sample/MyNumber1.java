package sample;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class MyNumber1 {
    private DoubleProperty number;
    public  final double getNumber(){
        if(number!=null){
            System.out.println("get"+number);
            return  number.get();}
        System.out.println("get0");
        return 0;
    }
    public final void setNumber(double number){
        System.out.println("set"+number);
        this.numberProperty().set(number);
    }

    public final DoubleProperty numberProperty(){
        if(number ==null){
            number =new SimpleDoubleProperty(0);
        }
        return number;
    }
}
