package com.piano.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class ReadSongNotes {
    private static final String DELIMITER = " ";

    public static List<String> getNotesFromFile(String filename) {
        String fileName = filename + ".txt";
        List<String> list = new ArrayList<>();
        try {
            list = Files.lines(Paths.get(fileName))
                    .map(s -> asList(s.split(DELIMITER)))
                    .flatMap(Collection::stream)
                    .filter(s -> s.length() > 0)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
