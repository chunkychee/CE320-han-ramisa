package main.ce320_react_spring.MVC;

public class FileUploadResponse {
    private String fileName;
    private String downloadedURi;
    private long size;

    public String getFileName(){
        return fileName;
    }
    public void setFileName(String fileName){
        this.fileName = fileName;
    }
     public String getdownloadedURi(){
        return downloadedURi;
    }
    public void setdownloadedURi(String downloadedURi){
        this.downloadedURi = downloadedURi;
    }
    
    public long getsize(){
        return size;
    }

    public void setsize(long size){
        this.size = size;
    }
    
}
