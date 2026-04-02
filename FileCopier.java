import java.util.ArrayList;

import java.io.*;
import java.util.ArrayList;

public class FileCopier {
    public  String speedstring;
    public  Boolean stop = false;
    public  Boolean cancel = false;
    public  Boolean complete = false;
    public int copiedCount = 0;
    public int copy(String finalPath, ArrayList<String> filesToCopy) {
        copiedCount = 0;

        for (String sourcePath : filesToCopy) {


            File source = new File(sourcePath);
            File target = new File(finalPath, source.getName());

            try {
                if (source.isDirectory()) {
                    copiedCount += copyDirectory(source, target);
                    if (cancel) {
                    return copiedCount;
                    }
                } else {
                    copyFileWithSpeed(source, target);
                    if (cancel) {
                        return copiedCount;
                    }
                    copiedCount++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        complete = true;
        return copiedCount;
    }

    private int copyDirectory(File sourceDir, File targetDir) throws IOException {
        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }

        File[] files = sourceDir.listFiles();
        if (files != null) {
            for (File file : files) {
                File target = new File(targetDir, file.getName());
                if (file.isDirectory()) {
                    copiedCount += copyDirectory(file, target);
                } else {
                    copyFileWithSpeed(file, target);
                    
                    
                    copiedCount++;
                    if (cancel) {
                    return copiedCount;
                    }
                }
                System.out.println(copiedCount);
            }
        }
        return copiedCount;
    }

    private void copyFileWithSpeed(File source, File target) throws IOException {

        //késöbb átalakitani hogy megkérdeze a felhasználót
    if (target.exists()) {
        String baseName = source.getName();
        String newName = baseName + "_copy";
        target = new File(target.getParent(), newName);
        }

        while (stop) {
                try {
                    Thread.sleep(200);
                } catch (Exception e) {

                }
                if (cancel) {
                    break;
                }
            }
            //duplán mert a fenti break a while bol lép ki
            
            if (cancel) {
                return;
            }


    try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(source));
         BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(target))) {

        byte[] buffer = new byte[8192];
        int bytesRead;
        long totalBytes = 0;
        long startTime = System.nanoTime();
        






        while ((bytesRead = in.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
            totalBytes += bytesRead;

            long elapsed = System.nanoTime() - startTime;
            double seconds = elapsed / 1_000_000_000.0;
            if (seconds > 0) {
                double speed = totalBytes / seconds;
                speedstring = formatSpeed(speed);
            }
        }
    }
}


    private String formatSpeed(double bytesPerSecond) {
        if (bytesPerSecond < 1024) {
            return String.format("%.2f B/s", bytesPerSecond);
        } else if (bytesPerSecond < 1024 * 1024) {
            return String.format("%.2f KB/s", bytesPerSecond / 1024);
        } else if (bytesPerSecond < 1024 * 1024 * 1024) {
            return String.format("%.2f MB/s", bytesPerSecond / (1024 * 1024));
        } else {
            return String.format("%.2f GB/s", bytesPerSecond / (1024.0 * 1024 * 1024));
        }
    }
}