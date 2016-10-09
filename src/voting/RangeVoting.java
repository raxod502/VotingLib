package voting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RangeVoting implements VotingSystem {
    
    private final double minUtility;
    private final double maxUtility;
    private final boolean scaled;
    
    public RangeVoting(double minUtility, double maxUtility, boolean scaled) {
        this.minUtility = minUtility;
        this.maxUtility = maxUtility;
        this.scaled = scaled;
    }
    
    @Override
    public List<Candidate> getWinners(List<Voter> voters, List<Candidate> candidates) {
        Map<Voter, Double> minUtilities = new HashMap<>(), maxUtilities = new HashMap<>();
        Map<Voter, Map<Candidate, Double>> utilities = new HashMap<>();
        for (Voter v : voters) {
            utilities.put(v, new HashMap<>());
            double minUtility = Double.MIN_VALUE, maxUtility = Double.MIN_VALUE;
            for (Candidate c : candidates) {
                double utility = v.utilityIfCandidateElected(c);
                if (utility < minUtility) {
                    minUtility = utility;
                }
                if (utility > maxUtility) {
                    maxUtility = utility;
                }
                utilities.get(v).put(c, utility);
            }
            minUtilities.put(v, minUtility);
            maxUtilities.put(v, maxUtility);
        }
        Map<Candidate, Double> scores = new HashMap<>();
        for (Candidate c : candidates) {
            double score = 0;
            for (Voter v : voters) {
                if (scaled) {
                    score += Util.transformLinearly(
                            utilities.get(v).get(c),
                            minUtilities.get(v), maxUtilities.get(v),
                            minUtility, maxUtility);
                }
                else {
                    score += Util.clamp(minUtility, utilities.get(v).get(c), maxUtility);
                }
            }
            scores.put(c, score);
        }
        return Util.maxElementsDouble(scores.keySet(), scores::get);
    }
    
    @Override
    public String toString() {
        return "Range voting (" + (scaled ? "scaled" : "unscaled") + ")";
    }
    
}
