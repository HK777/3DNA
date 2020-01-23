package ru.dna.dna;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
class ApplicationTests {

    @Test
    void contextLoads() {
        String stringToConvert = "[Pair ID,pairnum,pair,chain1,resnum1,base1,base2,resnum2,chain2,hydnum,wc\n" +
                "1,C-G,A,1,C,G,24,B,3,Y\n" +
                "2,G-C,A,2,G,C,23,B,3,Y\n" +
                "3,C-G,A,3,C,G,22,B,3,Y\n" +
                "4,G-C,A,4,G,C,21,B,3,Y\n" +
                "5,A-T,A,5,A,T,20,B,2,Y\n" +
                "6,A-T,A,6,A,T,19,B,2,Y\n" +
                "7,T-A,A,7,T,A,18,B,2,Y\n" +
                "8,T-A,A,8,T,A,17,B,2,Y\n" +
                "9,C-G,A,9,C,G,16,B,3,Y\n" +
                "10,G-C,A,10,G,C,15,B,3,Y\n" +
                "11,C-G,A,11,C,G,14,B,3,Y\n" +
                "12,G-C,A,12,G,C,13,B,3,Y";

        String[] rows = stringToConvert.split("\n");
        rows[0] = replaceOrAddPairNum(rows[0]);

        ArrayList<String[]> cells = new ArrayList<>();

        for (String str : rows) {
            cells.add(str.split(","));
        }

        String bbb = rows[0];
        ArrayList<String[]> a = cells;
    }

    private static String replaceOrAddPairNum(String s) {
        if (s.contains("pairnum")) {
            s=s.replace("pairnum,", "");
        }
        return s.substring(1);
    }


}
