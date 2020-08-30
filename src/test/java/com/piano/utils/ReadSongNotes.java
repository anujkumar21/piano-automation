package com.piano.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadSongNotes {
    private static final String DELIMITER = " ";

    public static List<String> getNotesFromFile(String filename) {
        String fileName = filename + ".txt";
        List<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.startsWith("!"))
                    for (String n : line.split(DELIMITER)) {
                        list.add(n);
                    }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
