import java.io.File;

public class OntroSYS {

    public static void main(String[] args) {
        
        //nyelv betöltése 
        FileIO lang = new FileIO();

        //létezik a path
        Gvar.nyelv = lang.ReadLine("./src/main/java/data.anb_syst", 0);
        File f = new File(Gvar.nyelv);

        //ha nem létezik valami folytán akkor alap nyelv beállitása
        if (!f.exists()) {
            Gvar.nyelv = "./src/main/java/SystemFiles/english.anb_language";
        }

        screen scr = new screen();
        scr.scr();

        //kijelző lefrisitése
        Gvar.frame.revalidate();
        Gvar.frame.repaint();


        //action listeners létrehozása
        actionlisteners aclis = new actionlisteners();
        aclis.createactionlisteners();
        //jobb click menü hozzáadása
        rightclickmenu rcm = new rightclickmenu();
        rcm.setuprightclickmenu();

        //indulási adatok betöltése
        startupdataload();

    }



    public static void startupdataload(){
        //kezdőkinézet betöltése
        
        dataprocess startup = new dataprocess();
        startup.updatedrive();
        drive drive = new drive();
        Gvar.pathleft=drive.driveletters().get(0);
        Gvar.pathright=drive.driveletters().get(0);

        // mapák betöltése
        fileolvaso olvas = new fileolvaso();




        Gvar.rightdatastructureraw =  olvas.filebeolvasas(Gvar.pathright);
        Gvar.leftdatastructureraw =  olvas.filebeolvasas(Gvar.pathleft);
        startup.loadleftsidestucture();
        startup.loadrightsidestucture();

        lookandfeel look = new lookandfeel();
        look.loadprevious();
    
    }
}


