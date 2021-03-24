package com.codecool.chessopen;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ChessResults {

    public List<String> getCompetitorsNamesFromFile(String fileName){
        List<String> result = new ArrayList<>();
        try {
            String wholeFile = readWholeFileIntoString(fileName);
            String[] textIntoStrArray = wholeFile.split(",");

            Map<String, Integer> results = putResultsIntoMap(textIntoStrArray);

            results.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .forEachOrdered(e -> result.add(e.getKey()));

        } catch (IOException e) {
            System.out.println("File not found!");
        }
        return result;
    }

    public Map<String, Integer> putResultsIntoMap(String[] fileIntoStrArray) {
        Map<String, Integer> results = new HashMap<>();
        for (int i = 0; i < fileIntoStrArray.length; i+=6) {
            results.put(fileIntoStrArray[i], (Integer.parseInt(fileIntoStrArray[i+1])+Integer.parseInt(fileIntoStrArray[i+2])+Integer.parseInt(fileIntoStrArray[i+3])+Integer.parseInt(fileIntoStrArray[i+4])+Integer.parseInt(fileIntoStrArray[i+5])));

        }
        return results;
    }

    public String readWholeFileIntoString(String fileName) throws IOException {
        StringBuilder sb = new StringBuilder();
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        while ((line = br.readLine()) != null) {
            sb.append(line);
            sb.append(",");
        }
        return sb.toString();
    }
}
