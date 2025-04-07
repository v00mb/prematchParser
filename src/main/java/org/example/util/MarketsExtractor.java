package org.example.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.http.HttpResponse;

public class MarketsExtractor {

    public static void extractMarkets(HttpResponse<String> response) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(response.body());

            JsonNode markets = root.path("markets");

            for (JsonNode market : markets) {
                String marketName = market.path("name").asText();
                System.out.println("\t\t" + marketName);

                JsonNode runners = market.path("runners");

                for (JsonNode runner : runners) {
                    String name = runner.path("name").asText();
                    double price = runner.path("price").asDouble();
                    long marketId = runner.path("id").asLong();
                    System.out.println("\t\t\t" + name + ", " + price + ", " + marketId);
                }

                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
