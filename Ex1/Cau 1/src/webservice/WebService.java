/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


/**
 *
 * @author User
 */
public class WebService {
   
    public static void main(String[] args){
        WebService webservice = new WebService();
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter lat number: ");
        String lat = reader.nextLine();
        System.out.println("Enter long number: ");
        String lng = reader.nextLine();
        reader.close();
        HttpURLConnection urlConnection = null;
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try {
            URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?latlng=" + lat 
                    + "," + lng + "&key=AIzaSyB_xELLbzPgSAf7_5taICeTN7OoHX6OQRc");
            urlConnection = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            bufferedReader.close();
        }
        catch(Exception e) {
        }
        finally{
            urlConnection.disconnect();
        }
        String response = stringBuilder.toString();
        if(response == null) {
            System.out.println("Fail to connect");
        }
        else{
            try{
                JSONParser parser = new JSONParser();
                JSONObject obj = (JSONObject)parser.parse(response);
                JSONArray jarr = (JSONArray) obj.get("results");
                if (jarr.isEmpty() == true){
                    System.out.println("No Results");
                }
                else{
                    System.out.println("Address is: " + ((JSONObject) jarr.get(0)).get("formatted_address"));
                }   
            }
            catch(Exception e){
                System.out.println("Wrong lat long");
            }
        }
    }
    
}
