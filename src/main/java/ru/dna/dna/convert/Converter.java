package ru.dna.dna.convert;

import java.util.ArrayList;

public class Converter {
    public void convert(String stringToConvert) {
        String[] rows = stringToConvert.split("\n");
        rows[0] = replaceOrAddPairNum(rows[0]);

        ArrayList<String[]> cells = new ArrayList<>();

        for (String str : rows)
            cells.add(str.split(","));
    }

    private static String replaceOrAddPairNum(String s) {
        if (s.contains("pairnum"))
            s = s.replace("pairnum,", "");

        return s.substring(1);
    }
}
