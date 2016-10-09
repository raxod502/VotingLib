package voting;

import java.util.Random;

public class NormalDistribution implements Distribution {
    
    private final Random random;
    private final boolean positiveOnly;
    private final double mean, standardDeviation;
    
    public NormalDistribution() {
        this(false);
    }
    
    public NormalDistribution(boolean positiveOnly) {
        this(positiveOnly, 1);
    }
    
    public NormalDistribution(boolean positiveOnly, double standardDeviation) {
        this(positiveOnly, standardDeviation, 0);
    }
    
    public NormalDistribution(boolean positiveOnly, double standardDeviation, double mean) {
        this.random = new Random();
        this.positiveOnly = positiveOnly;
        this.mean = mean;
        this.standardDeviation = standardDeviation;
    }
    
    @Override
    public double getNumber() {
        double num = mean + standardDeviation * random.nextGaussian();
        if (positiveOnly) {
            num = Math.abs(num);
        }
        return num;
    }
    
}
