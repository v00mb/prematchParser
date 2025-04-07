package org.example.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.http.HttpMarkets;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import static org.example.util.KickOffFormatter.getDateAndTimeFromKickOff;


public class FirstTwoMatchesExtractor {

    public static void extractFirstTwoMatches(HttpResponse<String> response) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode root = mapper.readTree(response.body());
            JsonNode dataArray = root.get("data");

            if (dataArray != null && dataArray.size() >= 2) {
                for (int i = 0; i < 2; i++) {
                    JsonNode match = dataArray.get(i);
                    String id = match.get("id").asText();
                    String name = match.get("name").asText();
                    System.out.println("\t" + name + ", " + getDateAndTimeFromKickOff(match.get("kickoff").asLong()) + ", " + id);
                    new HttpMarkets().getAllMarkets(id);
                    System.out.println();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
