package smartbox.components;


import smartbox.components.Component;
import smartbox.App;
import java.util.*;

public class StatsCalculator extends Component implements App {

    public ICalculator arithmeticCalculator;

    public StatsCalculator() {
        super();

    }

    public Double mean(List<Double> data) throws Exception {
        Double sum = 0.0;
        for(Double val: data) {
            sum = arithmeticCalculator.add(sum, val);
        }
        Double avg = arithmeticCalculator.div(sum, (double)data.size());
        return avg;
    }

    public void main() throws Exception {

        List<Double> scores = new LinkedList<Double>();
        for(int i = 0; i < 100; i++) {
            scores.add((double)i);
        }
        Double avg = mean(scores);
        mvc.Utilities.inform("Average = " + avg);
    }

}
