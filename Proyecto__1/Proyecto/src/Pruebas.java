

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class Pruebas {

    @Test
    public void testUrlConnection() {
        // Verificar que la conexión a la URL se establece correctamente
        Xml_Import xmlImport = new Xml_Import();
        assertTrue(xmlImport.connectToUrl("https://api.tutiempo.net/xml/?lan=es&apid=zwDX4azaz4X4Xqs&ll=40.4178,-3.7022"));
    }

    @Test
    public void testXmlParsing() {
        // Verificar que el XML se analiza correctamente
        Xml_Import xmlImport = new Xml_Import();
        xmlImport.connectToUrl("https://api.tutiempo.net/xml/?lan=es&apid=zwDX4azaz4X4Xqs&ll=40.4178,-3.7022");
        assertTrue(xmlImport.parseXml());
    }

    @Test
    public void testHtmlWriting() {
        // Verificar que el archivo HTML se escribe correctamente
        Xml_Import xmlImport = new Xml_Import();
        xmlImport.connectToUrl("https://api.tutiempo.net/xml/?lan=es&apid=zwDX4azaz4X4Xqs&ll=40.4178,-3.7022");
        xmlImport.parseXml();
        try {
            xmlImport.writeHtml("/ruta/de/prueba/clima_test.html");
            // Verificar que el archivo se ha creado exitosamente
            File file = new File("/ruta/de/prueba/clima_test.html");
            assertTrue(file.exists());
            // Borrar el archivo después de las pruebas
            file.delete();
        } catch (IOException e) {
            fail("IOException occurred: " + e.getMessage());
        }
    }
}
