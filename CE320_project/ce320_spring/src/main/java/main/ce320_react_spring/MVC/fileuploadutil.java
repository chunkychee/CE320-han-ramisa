package main.ce320_react_spring.MVC;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.io.InputStream;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
 
//this java class is to code out the method on how to save the file from API to the FOLDER
public class fileuploadutil {
	public static void saveFile(String filename, MultipartFile multipartFile) throws IOException{
        Path uploadDirectory = Paths.get("File-upload");
        try(InputStream inputStream = multipartFile.getInputStream()){
            Path filepath = uploadDirectory.resolve(filename);
            Files.copy(inputStream,filepath,StandardCopyOption.REPLACE_EXISTING);
        }catch(IOException ioe){
            throw new IOException("Error saving uploaded file: "+filename,ioe);
        }
    }
}
