import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BrowserParser {
    public static void main(String[] args) throws FileNotFoundException {
        Parser parser = Parser.getInstance();
        String htmlContent = "<div>hi</div><div><div>hi</div></div>";
        DOMElement tree = parser.parse(htmlContent);
    }
}
