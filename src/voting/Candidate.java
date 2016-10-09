package voting;

import java.util.Arrays;

public class Candidate extends Person {
    
    private static int candidateSerialNumberCounter = 1;
    protected final int candidateSerialNumber;
    protected final double popularity;
    
    public Candidate(double[] preferences, double popularity) {
        super(preferences);
        this.candidateSerialNumber = candidateSerialNumberCounter++;
        this.popularity = popularity;
    }
    
    public double getPopularity() {
        return popularity;
    }
    
    @Override
    public String toString() {
        return "Candidate #" + candidateSerialNumber + " with preferences " + Arrays.toString(preferences);
    }
    
}
