package com.asuncion.steps;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.asuncion.pages.AutogestionPage;
import com.asuncion.pages.PortalFamiliasPage;

public class PortalFamiliasTest extends BaseTest {

    /*
    * Valida que el acceso al Portal para Familias desde Autogestión
    * lleve correctamente a la página de login de Aulica.
    */
    @Test(groups = {"smoke", "regression"})
    public void validarAccesoPortalFamilias() {

        AutogestionPage autogestionPage = new AutogestionPage(driver);

        autogestionPage.irAAutogestion();
        autogestionPage.accederPortalFamilias();

        PortalFamiliasPage portalFamiliasPage = new PortalFamiliasPage(driver);

        Assert.assertTrue(portalFamiliasPage.esPaginaPortalFamilias());
    }

}
