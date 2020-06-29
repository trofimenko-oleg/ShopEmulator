package com.myshop.controller;

import com.myshop.service.DrinkService;
import com.myshop.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
public class JasperReportController {

    @Autowired
    DrinkService drinkService;

    @Autowired
    ReportService reportService;

    @GetMapping(value = "/report/pdf/{action}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> generatePDFReport(@PathVariable String action) throws IOException, JRException, URISyntaxException {
        String path = reportService.exportReport("pdf");
        byte[] contents = Files.readAllBytes(Paths.get(path));
        HttpHeaders headers = new HttpHeaders();
        String filename = "drinks_report.pdf";
        if (action.equals("show")) {
            headers.add("content-disposition", "inline;filename=" + filename);
        } else {
            headers.setContentDispositionFormData(filename, filename);
        }
        ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
        return response;
    }

    @GetMapping(value = "/report/html", produces = MediaType.TEXT_HTML_VALUE)
    public byte[] generateHTMLReport() throws IOException, JRException, URISyntaxException {
        String path = reportService.exportReport("html");
        byte[] contents = Files.readAllBytes(Paths.get(path));
        return contents;
    }
}
