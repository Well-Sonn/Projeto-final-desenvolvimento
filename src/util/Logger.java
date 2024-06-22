package util;

import model.loggable.Loggable;

public class Logger implements Loggable {
    private static final String FILE_PATH = "data/logs.txt";

    @Override
    public void log(String message) {
        FileHandler.writeToFile(FILE_PATH, message, true);
    }
}
