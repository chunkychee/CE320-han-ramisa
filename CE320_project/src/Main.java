import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.File;
import javax.swing.*;

public class Main {

    public static void LoadPDF() throws IOException {
        File contractPDF = new File("C:\\Users\\biont\\CE320_project\\CONTRACT AGREEMENT.pdf");
        //get PDF pages
        PDDocument documentOfContract = Loader.loadPDF(contractPDF);
        int end = documentOfContract.getNumberOfPages();
        // Find the index of the word you want to highlight
        PDFTextStripperByArea striptext = new PDFTextStripperByArea();

        for(int count = 2; count<=end-1 ;count++) {
            PDPage page = documentOfContract.getPage(count);
            String searchText = "Signature";
            String searchTextCAPS = "SIGNATURE";
            String SmallsearchText = "signature";
            Rectangle2D region = new Rectangle2D.Float(72, 72, 500, 650);
            String pg = "page" + count;
            striptext.addRegion(pg, region);
            striptext.extractRegions(page);
            String text = striptext.getTextForRegion(pg);
            if (text.contains(searchTextCAPS) || text.contains(SmallsearchText) || text.contains(searchText)) {
                PDFTextStripper stripper = new PDFTextStripper();
                String locateText = stripper.getText(documentOfContract);
                int index = locateText.indexOf(searchText);
                System.out.println(index);
                PDPageContentStream contentStream = new PDPageContentStream(documentOfContract, page, PDPageContentStream.AppendMode.APPEND, true);
                float x = 500;
                float y = 0;
                PDImageXObject signature = PDImageXObject.createFromFile("signature.png", documentOfContract);
                // /6f means the size of the signature
                float width = signature.getWidth() / 6f;
                float height = signature.getHeight() / 6f;
                contentStream.drawImage(signature, x, y, width, height);
                contentStream.close();
            }else{
                continue;
            }
        }
                // Close the content stream
                documentOfContract.save("Signed.pdf");
                documentOfContract.close();
    }
    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame();
        JLabel label = new JLabel();
        label.setText("Auto-Sign Your Document with SWAP!");
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        frame.setTitle("SWAP");
        frame.add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setVisible(true);
        LoadPDF();

    }
}
