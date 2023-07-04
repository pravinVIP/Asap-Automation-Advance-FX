/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import javafx.util.converter.DefaultStringConverter;

/**
 *
 * @author Lenovo
 */
public class Parameter {
  protected  SimpleStringProperty key;
   protected  SimpleStringProperty value;
public static <Parameter> Callback<TableColumn<Parameter,String>, TableCell<Parameter,String>> forTableColumn(DefaultStringConverter defaultStringConverter) {
    return forTableColumn(new DefaultStringConverter());
}
    public String getKey() {
        return key.get();
    }

    public String getValue() {
        return value.get();
    }
 public void setValue(String value) {
        this.value =new SimpleStringProperty(value);
    }
 public Parameter(){}
    public Parameter(String key, String  value) {
        this.key = new SimpleStringProperty(key);
        this.value =new SimpleStringProperty(value);
    }
 
  
}
