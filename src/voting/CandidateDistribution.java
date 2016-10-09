package voting;

public class CandidateDistribution {
    
    private final PointDistribution preferencesDistribution;
    private final Distribution popularityDistribution;
    
    public CandidateDistribution(PointDistribution preferencesDistribution, Distribution popularityDistribution) {
        this.preferencesDistribution = preferencesDistribution;
        this.popularityDistribution = popularityDistribution;
    }
    
    public Candidate getCandidate() {
        return new Candidate(preferencesDistribution.getPoint(), popularityDistribution.getNumber());
    }
    
}
