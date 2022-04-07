package com.hs1;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtil {

    public static void writeToFile(String stringToWrite, String fileName) {
        //going to try to write to file
        //if file exists, we will overwrite
        try {
            FileWriter writer = new FileWriter(fileName, false);
            writer.write(stringToWrite);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getFileContents(String fileName) throws IOException {
        return Files.readString(Path.of(fileName));
    }
}
