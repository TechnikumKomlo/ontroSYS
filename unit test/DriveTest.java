import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class driveTest {

    @Test
    void testDriveletters_ReturnsNotEmptyList() {
     
        drive driveHandler = new drive();

    
        ArrayList<String> drives = driveHandler.driveletters();

    
        // Egy működő számítógépen legalább egy gyökérkönyvtárnak (C:\ vagy /) lennie kell.
        assertNotNull(drives, "A lista nem lehet null.");
        assertFalse(drives.isEmpty(), "Legalább egy meghajtónak léteznie kell a rendszeren.");
    }

    @Test
    void testDriveletters_FormatCheck() {
       
        drive driveHandler = new drive();

        
        ArrayList<String> drives = driveHandler.driveletters();

    
        for (String drivePath : drives) {
            // Ellenőrizzük, hogy nem üres stringeket kaptunk-e vissza
            assertFalse(drivePath.isEmpty(), "A meghajtó elérési útja nem lehet üres.");
            
            // Megnézzük, hogy a visszaadott útvonal valóban létezik-e a fájlrendszerben
            java.io.File root = new java.io.File(drivePath);
            assertTrue(root.exists(), "A visszaadott meghajtónak (" + drivePath + ") léteznie kell.");
        }
    }
}