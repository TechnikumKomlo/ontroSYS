
import java.io.File;
import java.util.ArrayList;

public class drive {
    public ArrayList<String> driveletters() {

        File[] rootDrive = File.listRoots();
        ArrayList<String> meghajto = new ArrayList<>();
        for (int i = 0; i < rootDrive.length; i++) {
            meghajto.add(rootDrive[i].getAbsolutePath());
        }
        return meghajto;
    }
}
