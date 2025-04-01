package http;

import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.util.Locale;
import java.util.Map;
import java.util.HashMap;

/**
 * Class representing a single HTTP response message.
 *
 * @version 1.0
 */
public class HTTPResponse {
    
    private DataOutputStream socketOutput;
    private String page, httpMsg, path;
    private boolean isValid, hasError;
    
    HTTPResponse(DataOutputStream socketOutput, String path, boolean isValid){
        this.socketOutput = socketOutput;
        this.path = path;
        this.isValid = isValid;
    }
    
    private void content(String str1, String str2){
        page = str1;
        httpMsg = str2;
    }
    
    private void determineContent(){
        if(isValid){
            if(path.equals("/") || path.equals("/index.html")){
                content("content/index.html", "HTTP/1.1 200 OK\r\n");
                hasError = false;
            }
            else if(path.equals("/test.html")){
                content("content/test.html", "HTTP/1.1 200 OK\r\n");
                hasError = false;
            }
            else if(path.equals("/parrot.gif")){
                content("content/parrot.gif", "HTTP/1.1 200 OK\r\n");
                hasError = false;
            }
            else{
                content("errors/404.html", "HTTP/1.1 404 Not Found\r\n");
                hasError = true;
            }
        }
        else{
            content("errors/400.html", "HTTP/1.1 400 Bad Request\r\n");
            hasError = true;
        }
    }
    
    private String getLength(String filename) throws IOException{
        Path paths = Paths.get(filename);
        return "Content-Length: " + Files.size(paths) + "\r\n";
    }
    
    private String getServer() {
        return "Server: ramim@csci3363\r\n";
    }
    
    private String getType(String filename) throws IOException{
        Path file = Paths.get(filename);
        return "Content-Type: " + Files.probeContentType(file) + "\r\n";
    }
    
    private String getDate(){
        return "Date: " + DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now(ZoneOffset.UTC)) + "\r\n";
    }
    
    private byte[] printContent(String filename) throws IOException{
        Path file = Paths.get(filename);
        byte[] fileBytes = Files.readAllBytes(file);
        return fileBytes;
    }
    
    public String output() throws IOException {
        determineContent();
        String responseMsg = httpMsg + getServer() + getLength(page) + getDate() + getType(page) + "\r\n";
        socketOutput.writeBytes(responseMsg);
        socketOutput.write(printContent(page), 0, Integer.parseInt(getLength(page).replace("Content-Length: ", "").replace("\r\n", "")));
        
        if(hasError){
            socketOutput.close();
        }
        
        return (": Received " + httpMsg + " for " + path).replace("\r\n", "");
    }
  
}
