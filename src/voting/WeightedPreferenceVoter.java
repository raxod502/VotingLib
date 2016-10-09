package voting;

public class WeightedPreferenceVoter extends Voter {
    
    protected final double[] preferenceWeights;
    
    public WeightedPreferenceVoter(double[] preferences, double[] preferenceWeights) {
        super(preferences);
        this.preferenceWeights = new double[preferenceWeights.length];
        for (int i=0; i<preferenceWeights.length; i++) {
            this.preferenceWeights[i] = preferenceWeights[i];
        }
    }
    
    @Override
    public double utilityIfCandidateElected(Candidate candidate) {
        double sumOfSquares = 0;
        for (int i=0; i<preferences.length; i++) {
            double difference = preferences[i] - candidate.preferences[i];
            sumOfSquares += difference * difference * preferenceWeights[i];
        }
        return -Math.sqrt(sumOfSquares) / candidate.getPopularity();
    }
    
    public double[] getPreferenceWeights() {
        return preferenceWeights;
    }
    
}
