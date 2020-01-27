package ru.dna.dna.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.dna.dna.service.DnaService;
import ru.dna.dna.utils.FileUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
public class DnaController {

    private final DnaService dnaService;

    @Autowired
    public DnaController(DnaService dnaService) {
        this.dnaService = dnaService;
    }

    @GetMapping(value = "/zip", produces = "application/zip")
    public byte[] zipFiles(@RequestParam(name = "dna") String dna, HttpServletResponse response)
            throws IOException {
        response.setContentType("application/zip");
        response.setStatus(HttpServletResponse.SC_OK);
        response.addHeader("Content-Disposition", "attachment; filename=\"dna.zip\"");

        String[] split = dna.split(",");
        List<String> stringList = Arrays.stream(split).map(String::trim).collect(Collectors.toList());

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream);
        ZipOutputStream zipOutputStream = new ZipOutputStream(bufferedOutputStream);

        addXmlFileToZip(zipOutputStream, stringList, FileUtil.PAIR);
        addXmlFileToZip(zipOutputStream, stringList, FileUtil.PAIR_PAR);
        addXmlFileToZip(zipOutputStream, stringList, FileUtil.STEP);
        addXmlFileToZip(zipOutputStream, stringList, FileUtil.SIMPLEBP);
        addXmlFileToZip(zipOutputStream, stringList, FileUtil.SIMPLESTEP);
        addXmlFileToZip(zipOutputStream, stringList, FileUtil.BSTEP);
        addXmlFileToZip(zipOutputStream, stringList, FileUtil.MAINTORSION);
        addXmlFileToZip(zipOutputStream, stringList, FileUtil.PSEUDOTORSION);
        addXmlFileToZip(zipOutputStream, stringList, FileUtil.SUGARTORSION);

        addTxtFile(zipOutputStream, stringList);

        zipOutputStream.finish();
        zipOutputStream.flush();
        IOUtils.closeQuietly(zipOutputStream);
        IOUtils.closeQuietly(bufferedOutputStream);
        IOUtils.closeQuietly(byteArrayOutputStream);

        return byteArrayOutputStream.toByteArray();
    }

    private void addXmlFileToZip(ZipOutputStream zipOutputStream, List<String> dna, String fileName)
            throws IOException {
        zipOutputStream.putNextEntry(new ZipEntry(FileUtil.getXmlFle(fileName)));
        zipOutputStream.write(dnaService.getFile(fileName, dna).getBytes());
        zipOutputStream.closeEntry();
    }

    private void addTxtFile(ZipOutputStream zipOutputStream, List<String> dna) throws IOException {
        for (String str : dna) {
            zipOutputStream.putNextEntry(new ZipEntry(str + FileUtil.TORSION_WITHOUT_DNA));
            zipOutputStream.write(dnaService.getTorinsonTxt(str).getBytes());
            zipOutputStream.closeEntry();
            zipOutputStream.putNextEntry(new ZipEntry(str + FileUtil.SUMMARY_WITHOUT_DNA));
            zipOutputStream.write(dnaService.getSummaryTxt(str).getBytes());
            zipOutputStream.closeEntry();
        }
    }
}
