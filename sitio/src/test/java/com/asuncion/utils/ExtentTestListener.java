package com.asuncion.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.asuncion.steps.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentTestListener implements ITestListener {

    private static final ExtentReports extent = ExtentManager.getInstance();
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    /*
    * TestNG lo llama antes de cada test. Crea la entrada correspondiente
    * en el reporte de ExtentReports.
    * result: información del test que está por ejecutarse.
    */
    @Override
    public void onTestStart(ITestResult result) {
        test.set(extent.createTest(result.getMethod().getMethodName()));
    }

    /*
    * TestNG lo llama cuando un test termina sin errores. Marca la entrada
    * del reporte como exitosa y adjunta una captura de pantalla.
    * result: información del test que finalizó con éxito.
    */
    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test ejecutado correctamente.");
        adjuntarCaptura(result);
    }

    /*
    * TestNG lo llama cuando un test falla. Marca la entrada del reporte
    * como fallida, agrega el stack trace de la excepción y adjunta
    * una captura de pantalla del momento del error.
    * result: información del test que falló.
    */
    @Override
    public void onTestFailure(ITestResult result) {
        test.get().fail(result.getThrowable());
        adjuntarCaptura(result);
    }

    /*
    * TestNG lo llama una sola vez, al terminar toda la corrida. Escribe
    * el archivo final del reporte a disco (sin esto, Reporte.html
    * nunca se genera).
    * context: contexto de la corrida que finalizó.
    */
    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    /*
     * Toma una captura de pantalla del WebDriver del test actual y la
     * adjunta al reporte en base64, para que el HTML final quede
     * autocontenido (sin archivos de imagen sueltos).
     */
    private void adjuntarCaptura(ITestResult result) {

        Object instancia = result.getInstance();

        if (!(instancia instanceof BaseTest)) {
            return;
        }

        WebDriver driver = ((BaseTest) instancia).getDriver();

        if (driver == null) {
            return;
        }

        try {
            String base64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
            test.get().addScreenCaptureFromBase64String(base64, result.getMethod().getMethodName());
        } catch (Exception e) {
            test.get().warning("No se pudo adjuntar la captura de pantalla: " + e.getMessage());
        }
    }

}
