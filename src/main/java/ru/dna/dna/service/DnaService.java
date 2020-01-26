package ru.dna.dna.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dna.dna.clients.CsvClient;
import ru.dna.dna.parseAndConvert.ParserAndConverter;
import ru.dna.dna.utils.File;

@Component
public class DnaService {

    private final CsvClient csvClient;

    @Autowired
    public DnaService(CsvClient csvClient) {
        this.csvClient = csvClient;
    }

    public String getFile(String filename, String[] dna){
        ParserAndConverter parserAndConverter = new ParserAndConverter();

        for(String str: dna){
            parserAndConverter.parseAndConvert(str,csvClient.getFile(str, File.getCsvFile(filename)));
        }

        return parserAndConverter.getConvertedXml();
    }

}
