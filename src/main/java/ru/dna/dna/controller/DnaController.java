package ru.dna.dna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.dna.dna.clients.CsvClient;

import javax.servlet.http.HttpServletResponse;

@RestController
public class DnaController {

    private final CsvClient csvClient;

    @Autowired
    public DnaController(CsvClient csvClient) {
        this.csvClient = csvClient;
    }

    @GetMapping(value = "/{filename}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    byte[] getInfo(@PathVariable String filename, HttpServletResponse response){
        response.setContentType("text/plain");
        response.setHeader("Content-Disposition","attachment;filename="+filename);
        return csvClient.getFile("355D",filename).getBytes();
    }

}
