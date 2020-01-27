package ru.dna.dna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.dna.dna.service.DnaService;
import ru.dna.dna.utils.File;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class DnaController {

    private final DnaService dnaService;

    @Autowired
    public DnaController(DnaService dnaService) {
        this.dnaService = dnaService;
    }

    @GetMapping(value = "/{filename}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    byte[] getInfo(@RequestParam(name = "dna") String dna, @PathVariable String filename, HttpServletResponse response){

        response.setContentType("text/plain");
        response.setHeader("Content-Disposition","attachment;filename="+File.getXmlFle(filename));

        String[] split = dna.split(",");

        List<String> stringList = Arrays.stream(split).map(String::trim).collect(Collectors.toList());

        return dnaService.getFile(filename, stringList).getBytes();
    }

}
