package it.unina.softeng.pizzashop.client;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PizzaShopClient {
    private static String baseUrl = "http://localhost:8080/";

    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient http = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "auth"))
                .headers("Content-Type","application/json")
                .POST(HttpRequest.BodyPublishers.ofString(
                        "{\"login\":\"luigi\", \"password\":\"luigi\"}"
                )).build();
        HttpResponse<String> response = http.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Response status code: "+response.statusCode());
        System.out.println("Response body       : "+response.body());

        JSONObject json = new JSONObject(response.body());
        String token = json.getString("accessToken");

        System.out.println("JWT Token           : "+token);


        // prepare request to get all pizzas
        request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "pizza/"))
                .headers("Authorization","Bearer "+token) //set authorization header accordingly
                .GET().build();
        response = http.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Response status code: "+response.statusCode());
        System.out.println("Response body       : "+response.body());
    }
}
