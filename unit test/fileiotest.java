import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileIOTest {

    private FileIO fileIO;
    private String testFilePath;

    @TempDir
    Path tempDir; // Automatikusan takarít maga után

    @BeforeEach
    void setUp() throws IOException {
        fileIO = new FileIO();
        // Létrehozunk egy alap tesztfájlt minden teszt előtt
        Path file = tempDir.resolve("test.txt");
        List<String> lines = List.of("First Line", "Second Line", "Third Line", "Fourth Line");
        Files.write(file, lines);
        testFilePath = file.toString();
    }

    @Test
    void testRead_all_ArrayList_ByLine() {
        ArrayList<String> result = fileIO.Read_all_ArrayList_ByLine(testFilePath);
        assertEquals(4, result.size());
        assertEquals("First Line", result.get(0));
        assertEquals("Fourth Line", result.get(3));
    }

    @Test
    void testRead_all_to_string() {
        String result = fileIO.Read_all_to_string(testFilePath);
        // Figyelem: a beolvasás UTF-8, de a fájlvégi karakterek eltérhetnek (OS függő)
        assertTrue(result.contains("First Line"));
        assertTrue(result.contains("Fourth Line"));
    }

    @Test
    void testRead_from_to_ArrayList_ByLine() {
        // Start: 1 (Second Line), End: 3 (Fourth Line-nál megáll, tehát a 1. és 2. indexet hozza)
        ArrayList<String> result = fileIO.Read_from_to_ArrayList_ByLine(testFilePath, 1, 3);
        assertEquals(2, result.size());
        assertEquals("Second Line", result.get(0));
        assertEquals("Third Line", result.get(1));
    }

    @Test
    void testReadLine_Static() {
        String line = FileIO.ReadLine(testFilePath, 2);
        assertEquals("Third Line", line);
    }

    @Test
    void testWrite_to_end() {
        fileIO.write_to_end(testFilePath, "New Last Line");
        ArrayList<String> result = fileIO.Read_all_ArrayList_ByLine(testFilePath);
        assertEquals(5, result.size());
        assertEquals("New Last Line", result.get(4));
    }

    @Test
    void testRewrite_to_end() {
        fileIO.rewrite_to_end(testFilePath, "Only Content");
        ArrayList<String> result = fileIO.Read_all_ArrayList_ByLine(testFilePath);
        // A rewrite törli az eredeti fájlt és újat hoz létre
        assertEquals(1, result.size());
        assertEquals("Only Content", result.get(0));
    }

    @Test
    void testErrorHandlingWithNonExistentFile() {
        // Olyan fájlt tesztelünk, ami nem létezik
        ArrayList<String> result = fileIO.Read_all_ArrayList_ByLine("non_existent.txt");
        assertTrue(result.isEmpty()); // A kódod catch ága üres listát ad vissza hiba esetén
    }
}