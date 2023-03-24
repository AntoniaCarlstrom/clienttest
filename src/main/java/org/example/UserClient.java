package org.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class UserClient {

    public static void main(String[] args) throws Exception {
        //Skapar scanner för att läsa input från konsol
        Scanner scanner = new Scanner(System.in);
        //Skriver ut val i konsol till användaren
        System.out.println("Hej! Gör ett val.");
        System.out.println("1. Hämta alla användare.");
        System.out.println("2. Hämta användare med id (1-5).");

        int choice = scanner.nextInt();
        String urlMod = "null";


//Väljer användaren 1 listas alla users
        if (choice == 1 || choice == 2) {
            if (choice == 1) {
                urlMod = "users";
            }
            //Väljer användaren 2 behöver man göra ett val till, av ID.
            else if (choice == 2) {
                scanner.nextLine();
                System.out.println("Ange ID på användare: ");
                int choiceId = scanner.nextInt();
                //Kollar att valet är inom spannet för User ID
                if (choiceId >= 1 && choiceId <= 5) {
                    urlMod = "user?id=" + choiceId;
                } else {
                    System.out.println("Felaktigt ID");
                }

            } else {
                //Väljer man något annat än 1 eller 2 får man felmeddelande.
                System.out.println("Vänligen välj alternativ 1 eller 2!");
            }

            // Gör GET-request med URL som beror på tidigare val.
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8090/" + urlMod))
                    .GET()
                    .build();

            // Skicka request och få svar
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Skriv ut response body
            System.out.println(response.body());
        }
    }
}