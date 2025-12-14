import java.io.File;
import java.util.ArrayList;

//a megadot helyen lévő fájlok kilistázása elérési uttal egütt
public class fileolvaso {
    
    public ArrayList<String> filebeolvasas(String AbsolutePATH) {
        ArrayList<String> filelista = new ArrayList<String>();
        try {   
        File mappa = new File(AbsolutePATH);
        File[] filesList = mappa.listFiles();
        if (mappa != null) {
            for (File file : filesList) {
                filelista.add(String.valueOf(file.getAbsoluteFile()));
            }
        }
        
        } catch (Exception e) {
           System.out.println(e);
           Terminal.Log.append(e + "\n");
        }
       return filelista;
    }
}