package org.example;


import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws MalformedURLException {
//        // load orders
//        System.out.println(loadOrders());
        // run business rules on orders
        executeRules();
        // update orders on backend
    }

    // get orders from firebase backend
//    public static String loadOrders() throws MalformedURLException {
//        try{
//            StringBuilder result = new StringBuilder();
//            URL url = new URL("https://droolsdemo-c1afb-default-rtdb.firebaseio.com/orders/order1");
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//            try (BufferedReader reader = new BufferedReader(
//                    new InputStreamReader(conn.getInputStream()))) {
//                for (String line; (line = reader.readLine()) != null; ) {
//                    result.append(line);
//                }
//            }
//            return result.toString();
//        } catch(MalformedURLException e){
//            throw new MalformedURLException();
//        } catch (ProtocolException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    // execute business rules
    public static void executeRules(){
        KieServices kieServices = KieServices.Factory.get();
        KieFileSystem kfs = kieServices.newKieFileSystem();
        kfs.write(ResourceFactory.newFileResource("src/main/resources/orders.drl"));
        KieBuilder kieBuilder = kieServices.newKieBuilder(kfs).buildAll();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("kSession");

        // create orders, then insert orders in session and fire rules
        Order order1 = new Order("Christian", "Visa", 50);
        Order order2 = new Order("Max", "MasterCard", 100);
        Order order3 = new Order("Alazar", "Discover", 150);

        kieSession.insert(order1);
        kieSession.insert(order2);
        kieSession.insert(order3);

        kieSession.fireAllRules();
    }
}