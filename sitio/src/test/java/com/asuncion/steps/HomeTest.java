package com.asuncion.steps;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.asuncion.pages.AdministracionPage;
import com.asuncion.pages.HistoriaColegioPage;
import com.asuncion.pages.HomePage;
import com.asuncion.pages.SolicitudIngresoPage;

public class HomeTest extends BaseTest {

    public HomePage homePage;
	public HistoriaColegioPage historiaPage;

    /*
    * Navega a la home del sitio antes de cada test
    * (el navegador ya fue inicializado por BaseTest).
    */
    @BeforeMethod(alwaysRun = true)
	public void setUp() {

        homePage = new HomePage(driver);
		homePage.goTo("https://colegiolaasuncion.com.ar/");

		System.out.println("Validar titulo de la pagina: " + driver.getTitle());
	}

    /*
    * Valida que el menú principal muestre exactamente
    * las 6 opciones esperadas, en el orden correcto.
    */
    @Test(groups = {"smoke", "regression"})
    public void validarMenuPrincipal() {

  
    List<String> menuEsperado = Arrays.asList(
            "Inicio",
            "Quiénes Somos",
            "Proyecto Curricular En Diálogo Evangelizador",
            "Niveles Educativos",
            "Documentos Importantes",
            "Autogestión"
    	);

	Assert.assertEquals(homePage.obtenerOpcionesMenu().size(), 6);
    Assert.assertEquals(homePage.obtenerOpcionesMenu(), menuEsperado);
    }


	/*
	* Valida que el acceso a Solicitud de Ingreso
	* lleve correctamente al formulario de Google.
	*/
	@Test(groups = {"regression"})
	public void validarAccesoFormularioIngreso() {

    homePage.accederSolicitudIngreso();

    SolicitudIngresoPage solicitudPage = new SolicitudIngresoPage(driver);

    solicitudPage.accederFormulario();

    Assert.assertTrue(driver.getCurrentUrl().contains("docs.google.com/forms"));

	}

	/*
	* Valida que el acceso a Historia del Colegio muestre
	* correctamente sus bloques de contenido.
	*/
	@Test(groups = {"regression"})
	public void validarAccesoHistoriaColegio(){
		HistoriaColegioPage historiaPage = new HistoriaColegioPage(driver);

		homePage.ingresarHistoriaColegio();
		
		Assert.assertTrue(historiaPage.validarNuestraHistoria());

		Assert.assertTrue(historiaPage.validarGaleriaFotos());
	}

	/*
	* Valida que el acceso a Administración muestre
	* el correo de contacto correcto.
	*/
	@Test(groups = {"smoke", "regression"})
	public void validarAccesoAdministracion() {

		homePage.accederAdministracion();

		AdministracionPage administracionPage = new AdministracionPage(driver);

		Assert.assertTrue(administracionPage.validarCorreoVisible());

		Assert.assertEquals(administracionPage.obtenerTextoCorreo(), "administracion@colegiolaasuncion.com.ar");
	}

}
