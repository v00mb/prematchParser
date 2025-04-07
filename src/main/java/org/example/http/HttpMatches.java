package org.example.http;

import java.io.IOException;
import java.net.http.HttpResponse;

import static org.example.util.FirstTwoMatchesExtractor.extractFirstTwoMatches;

public class HttpMatches extends HttpRequestHandler {

    private static final String FIRST_PART_URL = "https://leonbets.com/api-2/betline/changes/all?ctag=en-US&vtag=9c2cd386-31e1-4ce9-a140-28e9b63a9300&league_id=";
    private static final String SECOND_PART_URL = "&hideClosed=true&flags=reg,urlv2,mm2,rrc,nodup";

    public void getFirstTwoMatches(long id) throws IOException, InterruptedException {
        HttpResponse<String> response = createRequestAndGetResponse(FIRST_PART_URL + id + SECOND_PART_URL);
        extractFirstTwoMatches(response);
    }

}
