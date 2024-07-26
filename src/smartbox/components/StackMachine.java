package smartbox.components;

import smartbox.*;

public class StackMachine extends Component implements CommandProcessor{

public IStack myStack;

public StackMachine() { super(); }

@Override
public String execute(String cmmd) throws Exception {
    String[] tokens = cmmd.split("\\s+");
    Double result = 0.0;
    String answer = "";
    if (tokens[0].equalsIgnoreCase("add")) {
        result = myStack.top();
        myStack.pop();
        result += myStack.top();
        myStack.pop();
        myStack.push(result);
        answer = "done";
    } else if (tokens[0].equalsIgnoreCase("mul")) {
        result = myStack.top();
        myStack.pop();
        result *= myStack.top();
        myStack.pop();
        myStack.push(result);
        answer = "done";
    }  else if (tokens[0].equalsIgnoreCase("div")) {
        result = myStack.top();
        myStack.pop();
        result /= myStack.top();
        myStack.pop();
        myStack.push(result);
        answer = "done";
    } else if (tokens[0].equalsIgnoreCase("sub")) {
        result = myStack.top();
        myStack.pop();
        result -= myStack.top();
        myStack.pop();
        myStack.push(result);
        answer = "done";
    } else if (tokens[0].equalsIgnoreCase("pop")) {
        myStack.pop();
        answer = "done";
    } else if (tokens[0].equalsIgnoreCase("top")) {
        answer = "" + myStack.top();
    } else if (tokens[0].equalsIgnoreCase("push")) {
        myStack.push(Double.valueOf(tokens[1]));
        answer = "done";
    } else if (tokens[0].equalsIgnoreCase("clear")) {
        myStack.clear();
        answer = "done";
    } else if (tokens[0].equalsIgnoreCase("isEmpty")) {
        answer = "" + myStack.isEmpty();;
    }
    else {
        throw new Exception("Unrecognized command: " + tokens[0]);
    }
    return answer;
}
}