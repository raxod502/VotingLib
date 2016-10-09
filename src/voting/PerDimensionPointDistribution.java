package voting;

public class PerDimensionPointDistribution implements PointDistribution {
    
    private final Distribution[] distributions;
    
    public PerDimensionPointDistribution(Distribution[] distributions) {
        this.distributions = distributions;
    }
    
    @Override
    public double[] getPoint() {
        double[] point = new double[distributions.length];
        for (int i=0; i<distributions.length; i++) {
            point[i] = distributions[i].getNumber();
        }
        return point;
    }
    
}
