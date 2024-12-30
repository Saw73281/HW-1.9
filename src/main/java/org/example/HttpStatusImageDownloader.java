package org.example;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.*;

public class HttpStatusImageDownloader {
    private HttpStatusChecker checker = new HttpStatusChecker();

    public void downloadStatusImage(int code) throws Exception {
        String imageUrl = checker.getStatusImage(code);
        URL url = new URL(imageUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        try (InputStream inputStream = connection.getInputStream()) {
            Path outputPath = Paths.get(code + ".jpg");
            Files.copy(inputStream, outputPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Image saved as " + outputPath.toString());
        }
    }
}
