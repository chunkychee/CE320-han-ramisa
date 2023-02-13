package main.ce320_react_spring.MVC;
import java.io.IOException;
import java.net.FileNameMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.micrometer.core.ipc.http.HttpSender.Response;

import org.springframework.util.StringUtils;

@RestController 
//when user launch the api with the payload(FILE) what will the server do with it
public class Controller {
    
    @PostMapping("/fileUpload")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<FileUploadResponse> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            long size = file.getSize();
            fileuploadutil.saveFile(fileName, file);
            FileUploadResponse response = new FileUploadResponse();
            response.setFileName(fileName);
            response.setsize(size);
            response.setdownloadedURi("/downloadFile");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        @GetMapping("/getFile")
        @CrossOrigin(origins = "*", allowedHeaders = "*")
        public FileUploadResponse callBack(){
            FileUploadResponse respond = new FileUploadResponse();
            return respond;
        }
}