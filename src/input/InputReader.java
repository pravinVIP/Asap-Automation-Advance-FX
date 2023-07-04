/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package input;

import output.OutputWriter;
import sample.FXMLController;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author pravin.a
 */
public class InputReader {

    public static String jsCssFontCacheio;
    public static String htmlCacheio;
    public static String transport_Security;
    public static String apiCacheio;
    public static String ImgCacheio;
    public static String sizeio;
    public static String callCount;
    public static String excel;
    public static String jsCallio[];
    public static String CssCallio[];
    public static String Domainio[];
    public static String CDN[];


    public void show(List<List<String>> arrList) {
        for (int i = 0; i < arrList.size(); i++) {

             if(arrList.get(i).get(0).equals("Js/Css/font Cache")){
                 jsCssFontCacheio=arrList.get(i).get(1);
             }
            else if(arrList.get(i).get(0).equals("Html Cache")){
                 htmlCacheio=arrList.get(i).get(1);
            }
             else if(arrList.get(i).get(0).equals("Img Cache")){
                 ImgCacheio=arrList.get(i).get(1);
             }
             else if(arrList.get(i).get(0).equals("Api Cache")){
                 apiCacheio=arrList.get(i).get(1);
             }
             else if(arrList.get(i).get(0).equals("Domain")){
                 Domainio=arrList.get(i).get(1).split(";");
             }
             else if(arrList.get(i).get(0).equals("JS calls")){
                 jsCallio=arrList.get(i).get(1).split(";");
             }
             else if(arrList.get(i).get(0).equals("CSS calls")){
                 CssCallio=arrList.get(i).get(1).split(";");
             }
             else if(arrList.get(i).get(0).equals("File Size")){
                 sizeio=arrList.get(i).get(1);
             }
             else if(arrList.get(i).get(0).equals("calls counts")){
                 callCount=arrList.get(i).get(1);
             }
             else if(arrList.get(i).get(0).equals("CDN")){
                 CDN=arrList.get(i).get(1).split(";");
             }
             else if(arrList.get(i).get(0).equals("Transport_Security")){
                 transport_Security=arrList.get(i).get(1);
             }
//             else if(arrList.get(i).get(0).equals("Excel Location")){
//                 excel=arrList.get(i).get(1);
//             }


        }
        excel="Data//sheet.xls";


    }



}
