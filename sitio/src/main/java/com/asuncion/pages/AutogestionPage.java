package com.asuncion.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.asuncion.core.BasePage;

public class AutogestionPage extends BasePage {

    private final By linkPlataformaEducativa = By.xpath("//img[@alt='Autogestion-nuestrosistema']/ancestor::a");
    private final By linkPortalFamilias = By.cssSelector("a[href^='https://familia.aulica.com.ar/login']");

     public AutogestionPage (WebDriver driver) {
		super(driver);
	}

    /*
    * Navega a la sección Autogestión del sitio.
    */
    public void irAAutogestion() {
        goTo("https://colegiolaasuncion.com.ar/autogestion/");
    }

    /*
    * Accede a la plataforma educativa (Campus Virtual)
    * desde el link de la imagen de autogestión.
    */
    public void accederPlataformaEducativa() {
        click(linkPlataformaEducativa);
    }

    /*
    * Accede al Portal para Familias (sistema Aulica)
    * desde el link de la imagen de autogestión.
    */
    public void accederPortalFamilias() {
        click(linkPortalFamilias);
    }

}
