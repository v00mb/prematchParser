package org.example.http;

import java.io.IOException;
import java.net.http.HttpResponse;

import static org.example.util.MarketsExtractor.extractMarkets;

public class HttpMarkets extends HttpRequestHandler {

    private static final String FIRST_PART_URL = "https://leonbets.com/api-2/betline/event/all?ctag=en-US&eventId=";
    private static final String SECOND_PART_URL = "&flags=reg,urlv2,mm2,rrc,nodup,smgv2,outv2";

    public void getAllMarkets(String id) throws IOException, InterruptedException {
        HttpResponse<String> response = createRequestAndGetResponse(FIRST_PART_URL + id + SECOND_PART_URL);
        extractMarkets(response);
    }
}
