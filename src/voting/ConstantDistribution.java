package voting;

public class ConstantDistribution implements Distribution {
    
    private final double value;
    
    public ConstantDistribution(double value) {
        this.value = value;
    }
    
    @Override
    public double getNumber() {
        return value;
    }
    
}
