import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;

public class dataprocess {
    
    public void updatedrive(){


        drive meghajto = new drive();
        Gvar.leftdrivedata = meghajto.driveletters();
        Gvar.rightdrivedata = meghajto.driveletters();

        for (String data : Gvar.leftdrivedata) {
            Gvar.leftdrive.addItem(data);    
        }
        for (String data : Gvar.rightdrivedata) {
            Gvar.rightdrive.addItem(data);    
        }
    }

    //adat oszlopok feltöltése
    public void loadleftsidestucture(){

        Gvar.leftfolderpoz.setText(Gvar.pathleft);
        ArrayList<String> name = new ArrayList<>();
        ArrayList<String> type = new ArrayList<>();
        ArrayList<String> lastmod = new ArrayList<>();
        ArrayList<String> size = new ArrayList<>();
        
        for (String absolutefile : Gvar.leftdatastructureraw) {
            name.add(getFileName(absolutefile));
        }
        Gvar.namelist1.setListData(name.toArray());
        for (String absolutefile : Gvar.leftdatastructureraw) { 
            type.add(getFileType(absolutefile));
        }
        Gvar.extensionlist1.setListData(type.toArray());
        for (String absolutefile : Gvar.leftdatastructureraw) {
            lastmod.add(getLastModified(absolutefile));
        }
        Gvar.lastmodlist1.setListData(lastmod.toArray());
        for (String absolutefile : Gvar.leftdatastructureraw) {
            size.add(getFileSize(absolutefile));
        }
        Gvar.sizelist1.setListData(size.toArray());
       
        
        
        
    }

    //jobb oldal
    public void loadrightsidestucture(){
        Gvar.rightfolderpoz.setText(Gvar.pathright);
        ArrayList<String> name = new ArrayList<>();
        ArrayList<String> type = new ArrayList<>();
        ArrayList<String> lastmod = new ArrayList<>();
        ArrayList<String> size = new ArrayList<>();
        for (String absolutefile : Gvar.rightdatastructureraw) {
            name.add(getFileName(absolutefile));
            type.add(getFileType(absolutefile));
            lastmod.add(getLastModified(absolutefile));
            size.add(getFileSize(absolutefile));
        }
        
        Gvar.namelist2.setListData(name.toArray());
        Gvar.extensionlist2.setListData(type.toArray());
        Gvar.lastmodlist2.setListData(lastmod.toArray());
        Gvar.sizelist2.setListData(size.toArray());
        
    }


    //fájl lista ujratöltése a Gvar.path@@ alapján
    public void reloadleftsidestucture(){
        fileolvaso file = new fileolvaso();
        Gvar.leftdatastructureraw = file.filebeolvasas(Gvar.pathleft);
        loadleftsidestucture();
    }
    //
        public void reloadrightsidestucture(){
        fileolvaso file = new fileolvaso();
        Gvar.rightdatastructureraw = file.filebeolvasas(Gvar.pathright);
        loadrightsidestucture();
    }
// név alapján sort
public void sortbynameleft() {
    Gvar.leftdatastructureraw.sort(new Comparator<String>() {
        @Override
        public int compare(String path1, String path2) {
            String name1 = new File(path1).getName();
            String name2 = new File(path2).getName();
            return compareNatural(name1, name2);
        }

        // Natural order comparison: "file2" < "file10"
        private int compareNatural(String a, String b) {
            int i = 0, j = 0;
            while (i < a.length() && j < b.length()) {
                char ca = a.charAt(i);
                char cb = b.charAt(j);

                if (Character.isDigit(ca) && Character.isDigit(cb)) {
                    // extract full number from both strings
                    int startI = i;
                    while (i < a.length() && Character.isDigit(a.charAt(i))) i++;
                    int startJ = j;
                    while (j < b.length() && Character.isDigit(b.charAt(j))) j++;

                    int numA = Integer.parseInt(a.substring(startI, i));
                    int numB = Integer.parseInt(b.substring(startJ, j));

                    if (numA != numB) {
                        return Integer.compare(numA, numB);
                    }
                } else {
                  
                    int cmp = Character.compare(
                        Character.toLowerCase(ca),
                        Character.toLowerCase(cb)
                    );
                    if (cmp != 0) return cmp;
                    i++;
                    j++;
                }
            }
            return Integer.compare(a.length(), b.length());
        }
    });

    // Debug print
    for (String absolutefile : Gvar.leftdatastructureraw) {
        System.out.println(absolutefile);
    }

    loadleftsidestucture();
}

//jobb

public void sortbynameright() {
    Gvar.rightdatastructureraw.sort(new Comparator<String>() {
        @Override
        public int compare(String path1, String path2) {
            String name1 = new File(path1).getName();
            String name2 = new File(path2).getName();
            return compareNatural(name1, name2);
        }

 
        private int compareNatural(String a, String b) {
            int i = 0, j = 0;
            while (i < a.length() && j < b.length()) {
                char ca = a.charAt(i);
                char cb = b.charAt(j);

                if (Character.isDigit(ca) && Character.isDigit(cb)) {
                 
                    int startI = i;
                    while (i < a.length() && Character.isDigit(a.charAt(i))) i++;
                    int startJ = j;
                    while (j < b.length() && Character.isDigit(b.charAt(j))) j++;

                    int numA = Integer.parseInt(a.substring(startI, i));
                    int numB = Integer.parseInt(b.substring(startJ, j));

                    if (numA != numB) {
                        return Integer.compare(numA, numB);
                    }
                } else {
              
                    int cmp = Character.compare(
                        Character.toLowerCase(ca),
                        Character.toLowerCase(cb)
                    );
                    if (cmp != 0) return cmp;
                    i++;
                    j++;
                }
            }
            return Integer.compare(a.length(), b.length());
        }
    });

    // Debug print
    for (String absolutefile : Gvar.rightdatastructureraw) {
        System.out.println(absolutefile);
    }

    loadrightsidestucture();
}

// tipus alapján

public void sortByExtensionLeft() {
    Gvar.leftdatastructureraw.sort(new Comparator<String>() {
        @Override
        public int compare(String path1, String path2) {
            File f1 = new File(path1);
            File f2 = new File(path2);

            // mappák mindig alul!!!!444!!!44!!444!
            if (f1.isDirectory() && !f2.isDirectory()) {
                return 1; // f1  f2
            }
            if (!f1.isDirectory() && f2.isDirectory()) {
                return -1; // f1  f2
            }

            
            String ext1 = getExtension(f1.getName());
            String ext2 = getExtension(f2.getName());

            return ext1.compareToIgnoreCase(ext2);
        }

     
        private String getExtension(String name) {
            int dotIndex = name.lastIndexOf('.');
            if (dotIndex > 0 && dotIndex < name.length() - 1) {
                return name.substring(dotIndex + 1);
            }
            return ""; 
        }
    });

    // Debug print
    for (String absolutefile : Gvar.leftdatastructureraw) {
        System.out.println(absolutefile);
    }

    loadleftsidestucture();
}
//jobb
public void sortByExtensionRight() {
    Gvar.rightdatastructureraw.sort(new Comparator<String>() {
        @Override
        public int compare(String path1, String path2) {
            File f1 = new File(path1);
            File f2 = new File(path2);

            
            if (f1.isDirectory() && !f2.isDirectory()) {
                return 1; // f1  f2
            }
            if (!f1.isDirectory() && f2.isDirectory()) {
                return -1; // f1  f2
            }

            
            String ext1 = getExtension(f1.getName());
            String ext2 = getExtension(f2.getName());

            return ext1.compareToIgnoreCase(ext2);
        }

     
        private String getExtension(String name) {
            int dotIndex = name.lastIndexOf('.');
            if (dotIndex > 0 && dotIndex < name.length() - 1) {
                return name.substring(dotIndex + 1);
            }
            return ""; 
        }
    });

    // Debug print
    for (String absolutefile : Gvar.rightdatastructureraw) {
        System.out.println(absolutefile);
    }

    loadrightsidestucture();
}

    //módositás dátuma alapján

public void sortByLastModifiedLeft() {
    Gvar.leftdatastructureraw.sort(new Comparator<String>() {
        @Override
        public int compare(String path1, String path2) {
            File f1 = new File(path1);
            File f2 = new File(path2);

            long lm1 = f1.lastModified();
            long lm2 = f2.lastModified();

            // ujabb tól régebig
            return Long.compare(lm2, lm1);
        }
    });

    // Debug print
    for (String absolutefile : Gvar.leftdatastructureraw) {
        System.out.println(absolutefile + " | " + getLastModified(absolutefile));
    }

    loadleftsidestucture();
}
    //jobb


    public void sortByLastModifiedRight() {
    Gvar.rightdatastructureraw.sort(new Comparator<String>() {
        @Override
        public int compare(String path1, String path2) {
            File f1 = new File(path1);
            File f2 = new File(path2);

            long lm1 = f1.lastModified();
            long lm2 = f2.lastModified();

            // 
            return Long.compare(lm2, lm1);
        }
    });

    // Debug print
    for (String absolutefile : Gvar.rightdatastructureraw) {
        System.out.println(absolutefile + " | " + getLastModified(absolutefile));
    }

    loadrightsidestucture();
}


    //méret alapján
    public void sortBySizeLeft() {
    Gvar.leftdatastructureraw.sort(new Comparator<String>() {
        @Override
        public int compare(String path1, String path2) {
            File f1 = new File(path1);
            File f2 = new File(path2);

            long size1 = f1.length();
            long size2 = f2.length();

          
            return Long.compare(size2, size1);
        }
    });

    // Debug print: méret
    for (String absolutefile : Gvar.leftdatastructureraw) {
        System.out.println(absolutefile + " | " + getFileSize(absolutefile));
    }

    loadleftsidestucture();
}

    //jobb

    public void sortBySizeRight() {
    Gvar.rightdatastructureraw.sort(new Comparator<String>() {
        @Override
        public int compare(String path1, String path2) {
            File f1 = new File(path1);
            File f2 = new File(path2);

            long size1 = f1.length();
            long size2 = f2.length();

       
            return Long.compare(size2, size1);
        }
    });

    // Debug print: méret
    for (String absolutefile : Gvar.rightdatastructureraw) {
        System.out.println(absolutefile + " | " + getFileSize(absolutefile));
    }

    loadrightsidestucture();
    }





    //fájl adatok megkapása
    // név
    public String getFileName(String absolutePath) {
        File file = new File(absolutePath);
        String name = file.getName();
        int dotIndex = name.lastIndexOf('.');
        if (dotIndex > 0) {
            return name.substring(0, dotIndex); 
        }
        return name; 
    }

    // olvasható fájl méret
    public String getFileSize(String absolutePath) {
        File file = new File(absolutePath);
        long bytes = file.length();

        if (bytes < 1024) {
            return bytes + " B";
        } else if (bytes < 1024 * 1024) {
            double kb = bytes / 1024.0;
            return String.format("%.2f KB", kb);
        } else if (bytes < 1024L * 1024L * 1024L) {
            double mb = bytes / (1024.0 * 1024.0);
            return String.format("%.2f MB", mb);
        } else {
            double gb = bytes / (1024.0 * 1024.0 * 1024.0);
            return String.format("%.2f GB", gb);
        }
    }

   // fájl tipus
    public String getFileType(String absolutePath) {
        File file = new File(absolutePath);
        if (file.isDirectory()) {
            return "DIR";
        } else {
            String name = file.getName();
            int dotIndex = name.lastIndexOf('.');
            if (dotIndex > 0 && dotIndex < name.length() - 1) {
                return name.substring(dotIndex + 1); 
            } else {
                return "Unknown"; 
            }
        }
    }

    // modositás dátuma
    public String getLastModified(String absolutePath) {
        File file = new File(absolutePath);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(file.lastModified());
    }




    public void createnewfolder(String path){
        String copypath = path;
        File folder = new File(path);
        if (!folder.exists()) {
            boolean created = folder.mkdir(); 
            if (created) {
                System.out.println("Folder created: " + folder.getAbsolutePath());

            } else {
                System.out.println("Failed to create folder.");

                }

        }
        //ha létezik hozon létre ujjat második fájl pozició létezése a kelemetlen fájl név érdekében
        else{
            int num = 0;
            while (folder.exists()) {
                num++;
                copypath +=String.valueOf(num);
                folder = new File(copypath);
                copypath = path;
                boolean created = folder.mkdir(); 
            if (created) {
                System.out.println("Folder created: " + folder.getAbsolutePath());
                break;
            }
            else{
                if (num>100) {
                    System.out.println("unable to create new folder out of bounds for limit of max based name folders (100)");
                    Terminal.Log.append("unable to create new folder out of bounds for limit of max based name folders (100) \n");
                    break;
                }
            }
            }
        }
    }




    public void deletefiles(String path){
        




    }
}