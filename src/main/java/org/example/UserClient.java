package org.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class UserClient {

    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hej! Gör ett val.");
        System.out.println("1. Hämta alla användare.");
        System.out.println("2. Hämta användare med id (1-5).");
        int choice = scanner.nextInt();
        String urlMod = "null";



        if(choice == 1) {
            urlMod = "users";
        }
        else if (choice == 2) {
            System.out.println("Ange ID på användare: ");
            String choiceId = scanner.nextLine();
            urlMod = "user?id=" + choiceId;

        } else {
            System.out.println("Felaktigt val!");
        }


        // Make a GET request to the /user endpoint with ID 1
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8090/" + urlMod))
                .uri(URI.create("http://localhost:8090/users"))
                .GET()
                .build();

        // Send the request and get the response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Print the response body
        System.out.println(response.body());

        System.out.println(request);
    }
}