import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FileolvasoTest {

    @TempDir
    Path tempDir; // Ideiglenes mappa a teszthez

    @Test
    void testFilebeolvasas_WithExistingFiles() throws IOException {
        // GIVEN: Létrehozunk pár fájlt az ideiglenes mappában
        File file1 = new File(tempDir.toFile(), "teszt1.txt");
        File file2 = new File(tempDir.toFile(), "teszt2.log");
        file1.createNewFile();
        file2.createNewFile();

        fileolvaso olvaso = new fileolvaso();

        // WHEN: Lefuttatjuk a beolvasást
        ArrayList<String> result = olvaso.filebeolvasas(tempDir.toString());

        // THEN: Ellenőrizzük az eredményt
        assertNotNull(result);
        assertEquals(2, result.size(), "Két fájlnak kellene szerepelnie a listában.");
        
        // Ellenőrizzük, hogy az elérési utak benne vannak-e
        assertTrue(result.contains(file1.getAbsolutePath()));
        assertTrue(result.contains(file2.getAbsolutePath()));
    }

    @Test
    void testFilebeolvasas_EmptyDirectory() {
        fileolvaso olvaso = new fileolvaso();
        
        // Üres mappára futtatjuk
        ArrayList<String> result = olvaso.filebeolvasas(tempDir.toString());

        assertNotNull(result);
        assertTrue(result.isEmpty(), "Üres mappa esetén a listának is üresnek kell lennie.");
    }

    @Test
    void testFilebeolvasas_NonExistentPath() {
        fileolvaso olvaso = new fileolvaso();
        
        // Olyan útvonal, ami nem létezik
        ArrayList<String> result = olvaso.filebeolvasas("ez/egy/nem/letezo/utvonal");

        assertNotNull(result);
        assertTrue(result.isEmpty(), "Hibás útvonal esetén üres listát várunk (a catch ág miatt).");
    }
}