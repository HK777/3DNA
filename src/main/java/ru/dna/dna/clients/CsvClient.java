package ru.dna.dna.clients;

import feign.Param;
import feign.RequestLine;

public interface CsvClient {

    @RequestLine("GET /data/ndb/_{dna}/{file}")
    String getFile(@Param("dna") String dna, @Param("file") String file);
}
