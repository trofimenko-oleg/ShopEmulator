package com.myshop.service;

import com.myshop.domain.Drink;
import com.myshop.repository.DrinkRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private DrinkRepository drinkRepository;

    public String exportReport(String reportFormat) throws IOException, JRException, URISyntaxException {
        String path = Paths.get(getClass().getClassLoader().getResource("resources/reports/temp.txt").toURI()).toString();
        path = new File(path).getParentFile().toString();

        new File(path).mkdir();
        List<Drink> drinks = drinkRepository.getAll();
        File file = ResourceUtils.getFile("classpath:resources/drinksReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource =  new JRBeanCollectionDataSource(drinks);
        Map parameters = new HashMap();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (reportFormat.equalsIgnoreCase("html")){
            path = path + "\\drinksReport.html";
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path);
        }
        if (reportFormat.equalsIgnoreCase("pdf")){
            path = path + "\\drinksReport.pdf";
            JasperExportManager.exportReportToPdfFile(jasperPrint, path);
        }
        return path;
    }
}
