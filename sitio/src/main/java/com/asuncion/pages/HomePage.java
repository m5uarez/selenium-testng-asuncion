package com.asuncion.pages;

import java.util.ArrayList;
import java.util.List;
//import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.asuncion.core.BasePage;

public class HomePage extends BasePage {

    private final By menuPrincipal = By.cssSelector("#menu-41493987 > li > a");
    private final By btnSolicitudIngreso = By.cssSelector("a[href*='solicitud-de-ingreso']");
    private final By menuQuienesSomos = By.xpath("//a[normalize-space()='Quiénes Somos']");
    private final By opcionHistoriaColegio = By.cssSelector("a[href*='historia-del-colegio']");
    private final By btnAdministracion = By.cssSelector("a[href*='/administracion/']");

    public HomePage (WebDriver driver) {
		super(driver);
	}

    /*
    * Obtiene los textos visibles de las opciones del menú principal.
    * return: lista de nombres de las opciones del menú, en orden.
    */
    public List<String> obtenerOpcionesMenu() {

    List<String> textos = new ArrayList<>();

    for (WebElement opcion : findElements(menuPrincipal)) {

        String texto = opcion.getText().trim();

        if (!texto.isEmpty()) {
            textos.add(texto);
        }
    }

    return textos;
    }


    /*
    * Accede a la página de Solicitud de Ingreso.
    */
    public void accederSolicitudIngreso() {
        click(btnSolicitudIngreso);
    }

    /*
    * Ingresa a Historia del Colegio
    * desde el menú Quiénes Somos.
    */
    public void ingresarHistoriaColegio(){

        hover(menuQuienesSomos);

        click(opcionHistoriaColegio);
    }

    /*
    * Accede a la página de Administración
    * desde el tooltip de la home.
    */
    public void accederAdministracion() {
        click(btnAdministracion);
    }

}
