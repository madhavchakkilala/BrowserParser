import java.util.Scanner;
import java.util.Stack;

public class Parser {

    private static Parser instance = new Parser();

    public static Parser getInstance(){
        return instance;
    }

    public DOMElement parse(String input){
        DOMElement rootElement = new DOMElement();
        dfs(rootElement, input);
        return rootElement;
    }

    public void dfs(DOMElement element, String input){ //<div>hi<
                                                        //div>
            String currentInput = "";
            for(int i=0;i<input.length();i++) {
                if(input.charAt(i) == '<') {

                }
            }
            //find the next tag
            //if opening tag:
            //    create a child and add it to the list and call dfs(newlyCreatedNode, input);
            //else if text:
            //    add a leaf element
            //else if closing tag:
            //    do nothing
    } //    >

    public DOMElement getNextElement(String input, int index){
        boolean isOpeningBraceFound = false;
        String currentString = "";
        for(; index < input.length();index++){
            if(currentString.charAt(index) == '<'){
                isOpeningBraceFound = true;
            } else if(currentString.charAt(index) == '>') {
                return new DOMElement(currentString);
            } else {
                currentString += currentString.charAt(index);
            }
        }
        return null;
    }






}
