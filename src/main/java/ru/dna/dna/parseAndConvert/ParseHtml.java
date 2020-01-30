package ru.dna.dna.parseAndConvert;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ParseHtml {
    public HashMap<String, String> findAndReturnAllParams(String html) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        Pattern fnPattern = Pattern.compile("(|^)[\\w.]{0,}\\.pdb(|$)");
        Matcher fnMatcher = fnPattern.matcher(html);

        Pattern uppathPattern = Pattern.compile("/home/shuxiang/public_html/3dna-v2-web/data/usr/\\d{1,}/\\d{1,}_\\d{1,}_\\d{1,}_\\d{1,}/\\d{1,}/analysis");
        Matcher uppathMatcher = uppathPattern.matcher(html);

        Pattern uppath2Pattern = Pattern.compile("data/usr/\\d+/\\d+_\\d+_\\d+_\\d+/\\d+/analysis");
        Matcher uppath2Matcher = uppath2Pattern.matcher(html);

        Pattern jobidPattern = Pattern.compile("name=\"jobid\" value=\"\\d+\"");
        Matcher jobidMatcher = jobidPattern.matcher(html);

        if (fnMatcher.find() && uppathMatcher.find() && uppath2Matcher.find() && jobidMatcher.find()) {
            hashMap.put("fn", fnMatcher.group());
            hashMap.put("uppath", uppathMatcher.group());
            hashMap.put("uppath2", uppath2Matcher.group());
            hashMap.put("root", "/home/shuxiang/public_html/3dna-v2-web");
            hashMap.put("jobid", jobidMatcher.group().replaceAll("\\D", ""));
        }

        return hashMap;

    }


}
