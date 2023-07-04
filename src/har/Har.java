/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package har;

import output.OutputWriter;

import sample.MyNumber;
import sample.Progress;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pravin.a
 */
public class Har {

    public static int harProcessEnd = 0;

    public void UrlTracker() {
        try {
            //        new Url().job();
//        new Url().job();
           // new output.OutputWriter().create();
//            new sample.Progress().setlbllord("0%");
//            new sample.Progress().setloadbar(0.50);

            System.out.println("hAR STARTED");
            new PerformanceHar().run();
        } catch (IOException | URISyntaxException ex) {
            OutputWriter.appendToFile(ex);
            ex.printStackTrace();
        }

        harProcessEnd = 1;
        System.out.println("harProcessEnd=1" + harProcessEnd);
    }
}
