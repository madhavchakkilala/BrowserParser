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

    public DOMElement getNextElement(String input) {
		int len = input.length();
		int baseIndex = index;
		if(index >= len)
		{
			return null;
		}
		Stack<Character> stack = new Stack<>();
		
		do
		{
			char ch = input.charAt(index);
			if (ch == '<')
			{
				if(stack.size() > 0)
				{
					// throw exception
				} else if (stack.size() == 0 && baseIndex != index)
				{
					String res = input.substring(baseIndex, index);
					return new DOMElement("LEAF_ELEMENT", res);
				}
				stack.push(ch);
			} else if(ch == '/')
			{
				if(stack.size() == 0)
				{
					// throw exception
				} else if(baseIndex+1 != index)
				{
					// throw exception
				}
				stack.push(ch);
			} else if(ch =='>') {
				if(stack.size() == 0)
				{
					// throw exception
				}
				String res = input.substring(baseIndex, index+1);
				index++;
				DOMElement element = new DOMElement(stack.size() == 1 ? "OPENING_ELEMENT" : "CLOSING_ELEMENT", res);
				return element;
			} 
			index++;
			
		} while(index < len);
		
		if(stack.size() != 0)
		{
			// throw exception
		}
		String res = input.substring(baseIndex, index);
		DOMElement element = new DOMElement("LEAF_ELEMENT", res);
		return element;
	}
    
    class Iterator{
        int index = 0;
    }
}
