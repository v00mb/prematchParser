package org.example.http;

import java.io.IOException;
import java.net.http.HttpResponse;

public class HttpSports extends HttpRequestHandler {

    private static final String SPORTS_URL = "https://leonbets.com/api-2/betline/sports?ctag=en-US&flags=urlv2";

    public HttpResponse<String> getSportsRequest() throws IOException, InterruptedException {
        return createRequestAndGetResponse(SPORTS_URL);
    }
}
