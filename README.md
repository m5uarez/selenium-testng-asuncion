# Selenium TestNG - Colegio La Asunción

Framework de automatización de pruebas end-to-end para el sitio institucional [colegiolaasuncion.com.ar](https://colegiolaasuncion.com.ar/), su Campus Virtual (Moodle) y el Portal para Familias (Aulica).

## 🛠️ Stack técnico

- **Java** + **Selenium WebDriver 4.43.0**
- **TestNG 7.12.0** — ejecución, agrupación de tests y DataProviders
- **Maven** — gestión de dependencias y build
- **ExtentReports 5.1.2** — reportes HTML visuales con capturas de pantalla
- **Patrón Page Object Model (POM)**

## 📁 Estructura del proyecto
sitio/src
├── main/java/com/asuncion/
│ ├── core/ → BasePage (métodos base de Selenium: esperas, click, scroll, etc.)
│ └── pages/ → Page Objects (una clase por página del sitio)
└── test/java/com/asuncion/
├── steps/ → Clases de test (TestNG) + BaseTest
└── utils/ → Reportes (ExtentReports) y lectura de datos (CSV)


## ✅ Cobertura de pruebas

| Página / Flujo | Descripción |
|---|---|
| **HomePage** | Menú principal, acceso a Solicitud de Ingreso, Historia del Colegio y Administración |
| **AdministracionPage** | Validación del correo de contacto |
| **AutogestionPage** | Accesos a Campus Virtual y Portal para Familias |
| **CampusVirtualPage** | Login al Campus Virtual (Moodle), validado con datos externos (CSV) |
| **PortalFamiliasPage** | Acceso al Portal para Familias (Aulica) |
| **HistoriaColegioPage / SolicitudIngresoPage** | Validación de contenido institucional |

Los tests están agrupados con TestNG en dos suites:

- **`smoke`** → subset rápido con los casos más críticos
- **`regression`** → set completo de pruebas

## ▶️ Cómo ejecutar

```bash
# Corre la suite completa (regression.xml, valor por defecto)
mvn test

# Corre solo el subset smoke
mvn test -DsuiteXmlFile=smoke.xml

# Corre la suite de regression de forma explícita
mvn test -DsuiteXmlFile=regression.xml
```

Al finalizar la ejecución, se genera un reporte visual en `test-output/ExtentReport/Reporte.html`, con capturas de pantalla embebidas de cada test.

### 📊 Reporte de ejecución en vivo

🔗 **[Ver reporte interactivo (ExtentReports)](https://m5uarez.github.io/selenium-testng-asuncion/report/Reporte.html)**

El reporte incluye resultados por test, tiempos de ejecución y capturas de pantalla embebidas de cada paso.

## 💡 Decisiones técnicas destacadas

Algunos de los desafíos reales resueltos durante el desarrollo:

- **Animaciones de Elementor**: varios bloques del sitio arrancan ocultos (`visibility: hidden`) y solo se revelan al hacer scroll. `BasePage.scrollToElement()` espera la presencia en el DOM (no la visibilidad) para evitar bloqueos.
- **Localizadores resilientes**: en Campus Virtual, el saludo de bienvenida varía de texto entre sesiones (`Hola` / `Bienvenido/a`), así que se optó por localizar un emoji constante en vez del texto variable.
- **Bug real detectado en producción**: el link de contacto de Administración tiene un typo en su `href` (`malito:` en vez de `mailto:`). Se ajustó el localizador para validar por el texto visible en vez del atributo roto.
- **Tests guiados por datos (Data-Driven Testing)**: el login del Campus Virtual usa un `@DataProvider` de TestNG que lee casos desde un CSV externo, separando datos de lógica de test.
- **Reportería con evidencia**: listener personalizado (`ExtentTestListener`) que adjunta capturas de pantalla en base64 automáticamente en cada test, exitoso o fallido.

## 🔒 Nota sobre datos sensibles

Los archivos de credenciales de prueba (`login_campus.csv`) se excluyen del repositorio vía `.gitignore` por buenas prácticas de seguridad.

## 👤 Autor

Maximiliano Suárez — QA Automation Engineer