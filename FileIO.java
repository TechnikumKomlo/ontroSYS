import java.util.ArrayList;
import java.io.*;
import java.nio.charset.Charset;
import java.io.*;
import java.nio.charset.*;


public class FileIO {
    // összes sor olvasása Arraylistbe
    ArrayList<String> Read_all_ArrayList_ByLine(String PATH) {
        ArrayList<String> adat = new ArrayList<String>();

        try {
            RandomAccessFile file = new RandomAccessFile(PATH, "r");
            int darab = 0;
            String sor = file.readLine();
            while (sor != null) {
                adat.add(sor);
                sor = file.readLine();
                darab++;
            }
            file.close();
        } catch (Exception e) {
            System.out.println("Read_all_ArrayList_ByLine error");
        }

        return adat;
    }

    // minden adat olvasása egyetlen String-be
    String Read_all_to_string(String PATH) {
        String sds = "";
        try (FileInputStream inputStream = new FileInputStream(PATH)) {
            sds = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

        } catch (Exception e) {
            System.out.println("Read_all_to_string error ");
        }
        return sds;
    }

    // sorok olvasása megadott adatok között
    ArrayList<String> Read_from_to_ArrayList_ByLine(String PATH, int start, int end) {
        ArrayList<String> adat = new ArrayList<String>();

        try {
            RandomAccessFile file = new RandomAccessFile(PATH, "r");
            int darab = 0;
            String sor = file.readLine();
            while (sor != null) {
                if (darab >= end)
                    break;

                if (darab < start) {
                    sor = file.readLine();
                } else {
                    adat.add(sor);
                    sor = file.readLine();
                }

                darab++;
            }
            file.close();
        } catch (Exception e) {
            System.out.println("Read_from_to_ArrayList_ByLine error");
        }

        return adat;
    }



    public static String ReadLine(String path, int lineNumber) {
        String line = null;
        int count = 0;

        Charset charset = StandardCharsets.UTF_8;

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(path), charset))) {

            while ((line = reader.readLine()) != null) {
                if (count == lineNumber) {
                    return line;
                }
                count++;
            }

        } catch (IOException e) {
            System.out.println("Readline error: " + e.getMessage());
            return "";
        }

        return null;
    }



    void write_to_end(String PATH, String data) {
        try {
            RandomAccessFile file = new RandomAccessFile(PATH, "rw");

            file.seek(file.length());
            file.writeBytes(data + "\n");
            file.close();
        } catch (Exception e) {
            System.out.println("write_to_end error");
        }

    }

    void write_to_Line(String PATH, String data, int linenumber) {
        try {
            ArrayList<String> Arraylisttomb = new ArrayList<String>();
            RandomAccessFile file = new RandomAccessFile(PATH, "rw");
            String adatok = Read_all_to_string(PATH);
            String[] lista = adatok.split("\n");
            for (String sor : lista) {
                Arraylisttomb.add(sor);
            }
            Arraylisttomb.remove(linenumber);
            Arraylisttomb.add(linenumber, data);
            for (String sor : Arraylisttomb) {
                file.writeBytes(sor + "\n");
            }
            file.close();
        } catch (Exception e) {
            System.out.println("write_to_Line error");
        }

    }

    void rewrite_to_end(String PATH, String data) {
        try {
            File file = new File(PATH);

            // Check if the file exists
            if (file.exists()) {
                // Delete the existing file
                file.delete();
            }

            // Create a new file
            file.createNewFile();

            // Open the file in read-write mode
            RandomAccessFile raf = new RandomAccessFile(file, "rw");

            // Move the file pointer to the end of the file
            raf.seek(raf.length());

            // Write the data to the file
            raf.writeBytes(data + "\n");

            // Close the file
            raf.close();
        } catch (Exception e) {
            System.out.println("nem sikerult a filet torolni és ujra generálni");

        }
    }

}