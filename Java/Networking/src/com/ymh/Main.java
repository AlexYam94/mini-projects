package com.ymh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class Main {

    public static void main(String[] args) {
        // write your code here
        try {
            URL url = new URL("https://api.flickr.com/services/feeds/photos_public.gne?tags=cats");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent","Chrome");
            connection.setReadTimeout(5000);

            int responseCode = connection.getResponseCode();
            System.out.println("Response code = " + responseCode);

            if(responseCode!= 200){
                System.out.println("Error reading web page");
                System.out.println(connection.getResponseMessage());
                return;
            }
            BufferedReader inputReader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream())
            );

            String line = "";
            while((line=inputReader.readLine()) != null){
                System.out.println(line);
            }
            inputReader.close();

//            urlConnection.setDoOutput(true);
//            urlConnection.connect();
//
//            BufferedReader inputStream = new BufferedReader(
//                    new InputStreamReader(urlConnection.getInputStream())
//            );
//            Map<String, List<String>> headerFields = urlConnection.getHeaderFields();
//            for(Map.Entry<String, List<String>> entry : headerFields.entrySet()){
//                String key = entry.getKey();
//                List<String> value = entry.getValue();
//                System.out.println("------key = "+ key);
//                for(String string: value){
//                    System.out.println("value = "+ value);
//                }
//            }
//            String line = "";
//            while(line != null){
//                line = inputStream.readLine();
//                System.out.println(line);
//            }

//            URI uri = new URI("http://username:password@myserver.com:5000/catalogue/phones?os=android#samsung");
//            URI baseUri = new URI("http://username:password@mynewserver.com:5000");
//            URI uri1 = new URI("/catalogue/phones?os=android#samsung");
//            URI uri2 = new URI("/catalogue/TVs?manufacturer=samsung#samsung");
//            URI uri3 = new URI("/stores/locations?zip=12345");
//
//            URI resolveUri1 = baseUri.resolve(uri1);
//            URI resolveUri2 = baseUri.resolve(uri2);
//            URI resolveUri3 = baseUri.resolve(uri3);
//
//            URL url1 = resolveUri1.toURL();
//            System.out.println("URL1 = "+url1);
//            URL url2 = resolveUri2.toURL();
//            System.out.println("URL2 = "+url2);
//            URL url3 = resolveUri3.toURL();
//            System.out.println("URL3 = "+url3);
//
//            URI relativizedURI = baseUri.relativize(resolveUri2);
//            System.out.println("Relative URI = "+ relativizedURI);
//        }catch (URISyntaxException e){

        }catch (MalformedURLException e) {

        }catch (IOException e){

        }
    }
}
