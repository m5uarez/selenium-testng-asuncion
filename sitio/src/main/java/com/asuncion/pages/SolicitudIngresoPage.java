package com.asuncion.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.asuncion.core.BasePage;

public class SolicitudIngresoPage extends BasePage {

private final By btnFormulario = By.cssSelector("a[href*='docs.google.com/forms']");
private final By footer = By.cssSelector("section.piedepagina");

     public SolicitudIngresoPage (WebDriver driver) {
		super(driver);
	}

    /*
    * Accede al formulario de Google.
    */
    public void accederFormulario() {
        scrollToElement(footer);
        scrollToElement(btnFormulario);
        click(btnFormulario);
    }




}
