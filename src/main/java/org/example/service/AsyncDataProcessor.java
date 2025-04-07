package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.http.HttpSports;
import org.example.util.TopLeaguesExtractor;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncDataProcessor {
    private static final ExecutorService executor = Executors.newFixedThreadPool(3);

    public void startScraping() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = fetchDataAsync();
        future.get();
        executor.shutdown();
    }

    private CompletableFuture<Void> fetchDataAsync() {

        return CompletableFuture.supplyAsync(() -> {
            try {
                HttpResponse<String> response = new HttpSports().getSportsRequest();
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readTree(response.body());
            } catch (IOException | InterruptedException e) {
                return null;
            }
        }, executor).thenAcceptAsync(data -> {
            if (data != null) {
                try {
                    new TopLeaguesExtractor().extract(data);
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, executor);
    }

}
