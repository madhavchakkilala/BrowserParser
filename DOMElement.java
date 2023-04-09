import java.util.HashMap;
import java.util.List;


public class DOMElement {
    String elementType;
    String elemetData;
    HashMap<String, String> propertyName;
    List<DOMElement> elements;
    boolean isValid;

    public DOMElement(String elementType){
        this.elementType = elementType;
    }
}


