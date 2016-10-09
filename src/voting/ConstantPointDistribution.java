package voting;

public class ConstantPointDistribution extends DimensionIndependentPointDistribution {
    
    public ConstantPointDistribution(int dims, double value) {
        super(dims, new ConstantDistribution(value));
    }
    
}
