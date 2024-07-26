package smartbox.components;
import smartbox.components.Component;

public class MathMachine extends Component implements CommandProcessor {

    @Override
    public String execute(String cmmd) throws Exception {
        String[] tokens = cmmd.split("\\s+");
        Double[] args = new Double[tokens.length - 1];
        Double result = 0.0;
        for(int i = 1; i < tokens.length; i++) {
            args[i - 1] = Double.valueOf(tokens[i]);
        }
        if (tokens[0].equalsIgnoreCase("add")) {
            for(int i = 0; i < args.length; i++) {
                result += args[i];
            }
        } else if (tokens[0].equalsIgnoreCase("mul")) {
            result = 1.0;
            for(int i = 0; i < args.length; i++) {
                result *= args[i];
            }
        }  else if (tokens[0].equalsIgnoreCase("div")) {
            result = args[0];
            for(int i = 1; i < args.length; i++) {
                result /= args[i];
            }
        } else if (tokens[0].equalsIgnoreCase("sub")) {
            result = args[0];
            for(int i = 1; i < args.length; i++) {
                result -= args[i];
            }
        } else {
            return "Unrecognized command: " + tokens[0];
        }
        return result.toString();
    }

}