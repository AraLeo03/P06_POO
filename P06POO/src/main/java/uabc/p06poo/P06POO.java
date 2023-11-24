/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package uabc.p06poo;

/**
 *
 * @author Dell
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import com.google.gson.Gson;

public class P06POO {

    public static void main(String[] args) {
        try {
            // URL del endpoint del API de Chuck Norris
            String apiUrl = "https://api.chucknorris.io/jokes/random";

            // Realizar la solicitud HTTP
            String jsonResponse = sendHttpRequest(apiUrl);

            // Crear un HashMap a partir de la respuesta JSON
            HashMap<String, Object> jokeMap = parseJsonResponse(jsonResponse);

            // Imprimir la broma de Chuck Norris
            String joke = (String) jokeMap.get("value");
            System.out.println("Broma de Chuck Norris: " + joke);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String sendHttpRequest(String apiUrl) throws Exception {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Leer la respuesta del servicio web
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();
        connection.disconnect();

        return response.toString();
    }

    private static HashMap<String, Object> parseJsonResponse(String jsonResponse) {
        // Utilizar la biblioteca Gson para convertir la respuesta JSON a un HashMap
        Gson gson = new Gson();
        return gson.fromJson(jsonResponse, HashMap.class);
    }
}

