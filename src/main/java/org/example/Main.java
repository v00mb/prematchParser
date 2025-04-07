package org.example;

import org.example.service.AsyncDataProcessor;

import java.util.concurrent.ExecutionException;


public class Main {

    public static void main(String[] args) {
        try {
            new AsyncDataProcessor().startScraping();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}


