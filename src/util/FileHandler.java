package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHandler {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static <T> T readFromFile(String filePath, TypeToken<T> typeToken) {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            return gson.fromJson(reader, typeToken.getType());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> void writeToFile(String filePath, T data) {
        try (Writer writer = Files.newBufferedWriter(Paths.get(filePath))) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToFile(String filePath, String data, boolean append) {
        try (FileWriter writer = new FileWriter(filePath, append)) {
            writer.write(data + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
