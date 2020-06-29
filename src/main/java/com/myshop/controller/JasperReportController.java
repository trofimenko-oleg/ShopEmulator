package com.myshop.controller;

import com.myshop.domain.Drink;
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
import java.util.List;

@RestController
public class JasperReportController {

    @Autowired
    DrinkService drinkService;

    @Autowired
    ReportService reportService;

    @GetMapping("/drinks/list")
    public List<Drink> getDrinks(){
        return drinkService.getAll();
    }

    @GetMapping(value = "/report/{format}", produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] generateReport(@PathVariable String format) throws IOException, JRException, URISyntaxException {
        String path = reportService.exportReport(format);
        byte[] contents = Files.readAllBytes(Paths.get(path));
        if (path.endsWith(".pdf")){
            //downloads
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_PDF);
//            // Here you have to set the actual filename of your pdf
//            String filename = "output.pdf";
//            headers.setContentDispositionFormData(filename, filename);
//            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
//            ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
//            return response;
        }
//        else if (path.endsWith("html")){
//            return null;
//        }
        return contents;
    }

}
