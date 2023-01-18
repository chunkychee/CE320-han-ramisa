import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.common.*;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.IOException;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        File contractPDF = new File("C:\\Users\\biont\\CE320_project\\CONTRACTAGREEMENT.pdf");
        FileInputStream fis = new FileInputStream(contractPDF);
        //get PDF pages
        PDDocument documentOfContract = Loader.loadPDF(contractPDF);
        int end = documentOfContract.getPages().getCount();

        //read text in pdf and put into a string
        PDFTextStripper striptext = new PDFTextStripper();
        striptext.setStartPage(1);
        striptext.setEndPage(end);
        String PDFtext = striptext.getText(documentOfContract);
        System.out.println(PDFtext);

        int signature = PDFtext.indexOf("signature:");
        int date = PDFtext.indexOf("\n", signature);
        //String date = text.substring(dateStart, dateEnd);


        //documentOfContract.save("C:\\Users\\biont\\PDF.pdf");
        //System.out.println("PDF created");
        //documentOfContract.close();
        //fis.close();
     }
}