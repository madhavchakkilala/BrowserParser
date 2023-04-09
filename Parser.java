public class Parser {

    private static Parser instance = new Parser();

    public static Parser getInstance(){
        return instance;
    }

    public DOMElement parse(String input){
        DOMElement rootElement = new DOMElement("SUPER_ROOT");
        Iterator iterator = new Iterator();
        dfs(rootElement, input, iterator);
        return rootElement;
    }

    public void dfs(DOMElement element, String input, Iterator iterator){
        DOMElement currentInput = getNextElement(input, iterator);
        while (currentInput != null){
            switch(currentInput.elementType){
                case "OPENING_ELEMENT":
                    if(currentInput == null){
                        return;
                    }
                    element.elements.add(currentInput);
                    dfs(currentInput, input, iterator);
                    currentInput = getNextElement(input, iterator);
                    break;
                case "CLOSING_ELEMENT":
                    return;
                case "LEAF_ELEMENT":
                    element.elements.add(currentInput);
                    currentInput = getNextElement(input, iterator);
                    break;
            }
        }
    }

    public DOMElement getNextElement(String input, Iterator iterator){
        boolean isOpeningBraceFound = false;
        String currentString = "";
        for(; iterator.index < input.length();iterator.index++){
            if(currentString.charAt(iterator.index) == '<'){
                isOpeningBraceFound = true;
            } else if(currentString.charAt(iterator.index) == '>') {
                return new DOMElement(currentString);
            } else {
                currentString += currentString.charAt(iterator.index);
            }
        }
        return null;
    }

    class Iterator{
        int index = 0;
    }
}
