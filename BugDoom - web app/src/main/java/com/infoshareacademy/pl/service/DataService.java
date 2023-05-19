package com.infoshareacademy.pl.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class DataService<T> {
    private Gson gson = new Gson();

    public <T> void saveToFile(T listToSave, String file) {
        try {
            Path path = Paths.get(file);
            if (file.equals(FilePathConstants.EVENT_FILE_PATH) || file.equals(FilePathConstants.TRACK_FILE_PATH)) {
                gson = addLocalDateSerializer();
            }
            String json = gson.toJson(listToSave);
            Files.write(path, json.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<T> readFromFile(String file, Class<T[]> classToRead) {
        try {
            Path path = Paths.get(file);
            String objectsFromFile = Files.readString(path);
            if (file.equals(FilePathConstants.EVENT_FILE_PATH) || file.equals(FilePathConstants.TRACK_FILE_PATH)) {
                gson = addLocalDateDeserializer();
            }
            T[] arr = gson.fromJson(objectsFromFile, classToRead);
            return Arrays.asList(arr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Gson addLocalDateSerializer() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
        return gsonBuilder.create();
    }

    private Gson addLocalDateDeserializer() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
        return gsonBuilder.create();
    }
}
