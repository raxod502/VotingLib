package voting;

import java.util.stream.DoubleStream;

public class LinearCombinationPointDistribution implements PointDistribution {
    
    private final PointDistribution[] distributions;
    private final double[] weights;
    
    public LinearCombinationPointDistribution(PointDistribution[] distributions) {
        this(distributions, DoubleStream.generate(() -> 1).toArray());
    }
    
    public LinearCombinationPointDistribution(PointDistribution[] distributions, double[] weights) {
        this.distributions = distributions;
        this.weights = weights;
    }
    
    @Override
    public double[] getPoint() {
        return distributions[Util.weightedRandInt(weights)].getPoint();
    }
    
}
