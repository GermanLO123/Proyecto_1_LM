import static org.junit.jupiter.api.Assertions.*;

import java.io.FileWriter;

import org.junit.jupiter.api.Test;

class TestLProject {

	@Test
	public void testCreacionArchivo() throws Exception {
        FileWriter writer = new FileWriter("..\\WEB\\index.html");

        // Verifica que el archivo se haya creado correctamente
        assertNotNull(writer);
    }
}
