package voting;

public class DimensionIndependentPointDistribution implements PointDistribution {
    
    private final int dims;
    private final Distribution distribution;
    
    public DimensionIndependentPointDistribution(int dims, Distribution distribution) {
        this.dims = dims;
        this.distribution = distribution;
    }
    
    @Override
    public double[] getPoint() {
        double[] point = new double[dims];
        for (int i=0; i<dims; i++) {
            point[i] = distribution.getNumber();
        }
        return point;
    }
    
}
