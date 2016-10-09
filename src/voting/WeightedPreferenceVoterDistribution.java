package voting;

public class WeightedPreferenceVoterDistribution implements VoterDistribution {
    
    private final PointDistribution preferencesDistribution;
    private final PointDistribution preferenceWeightsDistribution;
    
    public WeightedPreferenceVoterDistribution(PointDistribution preferencesDistribution, PointDistribution preferenceWeightsDistribution) {
        this.preferencesDistribution = preferencesDistribution;
        this.preferenceWeightsDistribution = preferenceWeightsDistribution;
    }
    
    @Override
    public Voter getVoter() {
        return new WeightedPreferenceVoter(
                preferencesDistribution.getPoint(),
                preferenceWeightsDistribution.getPoint());
    }
    
}
