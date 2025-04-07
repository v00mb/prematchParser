package org.example.util;

import com.fasterxml.jackson.databind.JsonNode;
import org.example.entity.League;
import org.example.http.HttpMatches;

import java.io.IOException;
import java.util.*;


public class TopLeaguesExtractor {

    public void extract(JsonNode root) throws IOException, InterruptedException {
        Set<String> allowedSports = new HashSet<>(Arrays.asList("Football", "Tennis", "Ice Hockey", "Basketball"));
        List<League> topLeagues = new ArrayList<>();

        if (root.isArray()) {
            for (JsonNode sport : root) {
                String sportName = sport.get("name").asText();

                if (!allowedSports.contains(sportName)) continue;

                JsonNode regions = sport.get("regions");
                if (regions != null && regions.isArray()) {
                    for (JsonNode region : regions) {
                        JsonNode leagues = region.get("leagues");
                        String leagueRegion = region.get("name").asText();
                        if (leagues != null && leagues.isArray()) {
                            for (JsonNode league : leagues) {
                                JsonNode topNode = league.get("top");
                                if (topNode != null && topNode.asBoolean()) {
                                    topLeagues.add(new League(sportName, league, leagueRegion));
                                }
                            }
                        }
                    }
                }
            }
        }

        for (League pair : topLeagues) {
            JsonNode league = pair.league();
            System.out.println(pair.sportName() + ", " +  pair.leagueRegion() + " " + league.get("name").asText());
            new HttpMatches().getFirstTwoMatches(league.get("id").asLong());
            System.out.println("---");
        }
    }


}
