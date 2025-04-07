package org.example.entity;

import com.fasterxml.jackson.databind.JsonNode;

public record League(String sportName, JsonNode league, String leagueRegion) {
}
