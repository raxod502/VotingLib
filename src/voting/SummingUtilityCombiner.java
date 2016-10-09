package voting;

import java.util.stream.DoubleStream;

public class SummingUtilityCombiner implements UtilityCombiner {
    
    @Override
    public double combineUtilities(double[] utilities) {
        return DoubleStream.of(utilities).sum();
    }
    
}
