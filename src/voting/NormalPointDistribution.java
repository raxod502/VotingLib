package voting;

public class NormalPointDistribution implements PointDistribution {
    
    private final PointDistribution distribution;
    
    public NormalPointDistribution(int dims) {
        distribution = new DimensionIndependentPointDistribution(dims, new NormalDistribution());
    }
    
    public NormalPointDistribution(int dims, boolean onlyPositive) {
        distribution = new DimensionIndependentPointDistribution(dims, new NormalDistribution(onlyPositive));
    }
    
    public NormalPointDistribution(int dims, boolean onlyPositive, double standardDeviation) {
        distribution = new DimensionIndependentPointDistribution(dims, new NormalDistribution(onlyPositive, standardDeviation));
    }
    
    public NormalPointDistribution(int dims, boolean onlyPositive, double standardDeviation, double mean) {
        distribution = new DimensionIndependentPointDistribution(dims, new NormalDistribution(onlyPositive, standardDeviation, mean));
    }
    
    @Override
    public double[] getPoint() {
        return distribution.getPoint();
    }
    
}
