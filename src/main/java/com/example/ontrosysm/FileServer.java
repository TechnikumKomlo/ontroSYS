package com.example.ontrosysm;

import fi.iki.elonen.NanoHTTPD;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileServer extends NanoHTTPD {

    private final File rootDir;

    public FileServer(int port, File rootDir) {
        super(port);
        this.rootDir = rootDir;
    }

    @Override
    public Response serve(IHTTPSession session) {
        String uri = session.getUri().substring(1);
        File file = new File(rootDir, uri);

        if (uri.isEmpty() || file.isDirectory()) {
            StringBuilder html = new StringBuilder("<h2>Files</h2>");
            for (File f : rootDir.listFiles()) {
                html.append("<a href=\"")
                        .append(f.getName())
                        .append("\">")
                        .append(f.getName())
                        .append("</a><br>");
            }
            return newFixedLengthResponse(Response.Status.OK, "text/html", html.toString());
        }

        if (file.exists()) {
            try {
                FileInputStream fis = new FileInputStream(file);
                return newChunkedResponse(Response.Status.OK, "application/octet-stream", fis);
            } catch (Exception e) {
                return newFixedLengthResponse("Error reading file");
            }
        }

        return newFixedLengthResponse(Response.Status.NOT_FOUND, "text/html", "<h3>File not found</h3>");

    }
}
