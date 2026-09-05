package com.asuncion.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    private CsvReader() {
    }

    /*
     * Lee un archivo CSV desde el classpath (src/test/resources) y devuelve
     * sus filas (sin el encabezado) como Object[][], listas para usar como
     * fuente de un @DataProvider de TestNG.
     * rutaArchivo: ruta relativa dentro de resources, ej. "data/login_campus.csv".
     */
    public static Object[][] leerCsv(String rutaArchivo) {

        List<String[]> filas = new ArrayList<>();

        InputStream input = CsvReader.class.getClassLoader().getResourceAsStream(rutaArchivo);

        if (input == null) {
            throw new RuntimeException("No se encontró el archivo: " + rutaArchivo);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8))) {

            String linea = reader.readLine(); // descarta el encabezado

            while ((linea = reader.readLine()) != null) {
                if (!linea.isBlank()) {
                    filas.add(linea.split(",", -1));
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo: " + rutaArchivo, e);
        }

        return filas.toArray(new Object[0][]);
    }

}
