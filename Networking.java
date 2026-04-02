import java.io.*;
import java.net.Socket;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class Networking {
    public static ArrayList<String> lastReceivedList = new ArrayList<>();


    public void sendCommand(String ip, int port, String command){

        try (Socket socket = new Socket(ip, port)) {

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            

            out.writeUTF("COMMAND");
            out.writeUTF(command);
            out.flush();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    //eyetlen fájl küldése
    public void sendFile(String ip, int port, File file, String basePath) {
        try (Socket socket = new Socket(ip, port)) {

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            out.writeUTF("FILE");
            out.writeUTF(basePath);          // relative path
            out.writeUTF(file.getName());
            out.writeLong(file.length());

            try (FileInputStream fis = new FileInputStream(file)) {
                fis.transferTo(out);
            }

            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //mappástúl küldés
    public void sendFolder(String ip, int port, File folder, String abs_position) {
        Path root = folder.toPath();

        try {
            Files.walk(root).forEach(path -> {
                try {
                    if (Files.isDirectory(path)) return;

                    File file = path.toFile();
                    String relative = root.relativize(path).toString();

                    sendFile(ip, port, file, relative);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void sendArrayList(String ip, int port, ArrayList<String> list) {
        try (Socket socket = new Socket(ip, port)) {

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            ObjectOutputStream objOut = new ObjectOutputStream(out);

             
            objOut.writeObject(list);          
            objOut.flush();

            

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void getCommand(String ip, int port) {
 
    try (Socket socket = new Socket(ip, port)) {
 
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
 
        String mode = in.readUTF(); // should be "COMMAND"
 
        if (!mode.equals("COMMAND")) {
            System.out.println("Unknown mode: " + mode);
            return;
        }
 
        String command = in.readUTF();
 
 
    } catch (Exception e) {
        e.printStackTrace();
    }
}




public void get_Arraylist(String ip, int port) {

        try (Socket socket = new Socket(ip, port)) {

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            ObjectInputStream objIn = new ObjectInputStream(socket.getInputStream());

            Object received = objIn.readObject();

            if (received instanceof ArrayList) {
                lastReceivedList = (ArrayList<String>) received;
                
            } else {
                
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getLastReceivedList() {
        return lastReceivedList;
    }



}
