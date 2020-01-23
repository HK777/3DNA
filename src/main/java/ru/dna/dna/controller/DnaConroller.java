package ru.dna.dna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dna.dna.clients.CsvClient;

@RestController
public class DnaConroller {

    private final CsvClient csvClient;

    @Autowired
    public DnaConroller(CsvClient csvClient) {
        this.csvClient = csvClient;
    }

    @GetMapping("/")
    String getInfo(){
        return csvClient.getFile("355D","pair.csv");
    }

}
