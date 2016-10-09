package voting;

import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

import java.util.stream.DoubleStream;

public class ConfidenceInterval {
    
    private final double center;
    private final double radius;
    
    /**
     * Adapted from http://stackoverflow.com/q/5564621/3538165
     */
    public ConfidenceInterval(double[] nums, double confidence) {
        SummaryStatistics stats = new SummaryStatistics();
        DoubleStream.of(nums).forEach(stats::addValue);
        double standardDeviation = stats.getStandardDeviation();
        TDistribution distribution = new TDistribution(nums.length - 1);
        double criticalValue = distribution.inverseCumulativeProbability((confidence + 1) / 2);
        
        center = stats.getMean();
        radius = criticalValue * stats.getStandardDeviation() / Math.sqrt(nums.length);
    }
    
    public ConfidenceInterval(double center, double radius) {
        this.center = center;
        this.radius = radius;
    }
    
    public double getCenter() {
        return center;
    }
    
    public double getRadius() {
        return radius;
    }
    
    public double getLowerBound() {
        return center - radius;
    }
    
    public double getUpperBound() {
        return center + radius;
    }
    
    public ConfidenceInterval negate() {
        return new ConfidenceInterval(-center, radius);
    }
    
    @Override
    public String toString() {
        return toString("%s");
    }
    
    public String toString(String fmt) {
        return String.format(
                String.format("(%1$s, %1$s) = %1$s ± %1$s", fmt),
                getLowerBound(),
                getUpperBound(),
                getCenter(),
                getRadius());
    }
    
}
