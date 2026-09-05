package com.asuncion.utils;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

    private static ExtentReports extent;

    private ExtentManager() {
    }

    /*
     * Devuelve la instancia única de ExtentReports, creándola la primera vez.
     * El reporte se genera en test-output/ExtentReport/, fuera de target/,
     * para que sobreviva a un mvn clean.
     */
    public static synchronized ExtentReports getInstance() {

        if (extent == null) {

            String rutaReporte = System.getProperty("user.dir")
                    + File.separator + "test-output"
                    + File.separator + "ExtentReport"
                    + File.separator + "Reporte.html";

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(rutaReporte);
            sparkReporter.config().setTheme(Theme.STANDARD);
            sparkReporter.config().setDocumentTitle("Reporte de Pruebas - Colegio La Asunción");
            sparkReporter.config().setReportName("Resultados de Automatización");

            // Embebe el CSS/JS dentro del propio HTML para que el reporte
            // sea un único archivo autocontenido, sin depender de un CDN externo.
            sparkReporter.config().setOfflineMode(true);

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
        }

        return extent;
    }

}
