package com.asuncion.steps;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.asuncion.pages.AutogestionPage;
import com.asuncion.pages.CampusVirtualPage;
import com.asuncion.utils.CsvReader;

public class CampusVirtualTest extends BaseTest {

    /*
    * Provee los casos de login del Campus Virtual, leídos desde
    * data/login_campus.csv (caso, usuario, contraseña, resultado esperado).
    * return: filas del CSV, una por cada invocación del test.
    */
    @DataProvider(name = "credencialesCampus")
    public Object[][] credencialesCampus() {
        return CsvReader.leerCsv("data/login_campus.csv");
    }

    /*
    * Valida el login al Campus Virtual para cada caso del CSV: si el
    * resultado esperado es "exito", verifica que se llegó a la plataforma;
    * en cualquier otro caso, verifica el mensaje de acceso inválido.
    * caso: nombre descriptivo del escenario (ej. "caso_feliz").
    * usuario: usuario a ingresar en el login.
    * contrasena: contraseña a ingresar en el login.
    * resultado: resultado esperado ("exito" o "error").
    */
    @Test(dataProvider = "credencialesCampus", groups = {"smoke", "regression"})
    public void validarLoginCampusVirtual(String caso, String usuario, String contrasena, String resultado) {

        System.out.println("Ejecutando caso: " + caso);

        AutogestionPage autogestionPage = new AutogestionPage(driver);

        autogestionPage.irAAutogestion();
        autogestionPage.accederPlataformaEducativa();

        CampusVirtualPage campusPage = new CampusVirtualPage(driver);

        campusPage.loginCompleto(usuario, contrasena);

        if ("exito".equals(resultado)) {
            Assert.assertTrue(campusPage.esPaginaCampusVirtual());
        } else {
            Assert.assertEquals(campusPage.obtenerMensajeError(), "Acceso inválido. Por favor, inténtelo otra vez.");
        }
    }

}
