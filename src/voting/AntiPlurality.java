package voting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AntiPlurality implements VotingSystem {
    
    @Override
    public List<Candidate> getWinners(List<Voter> voters, List<Candidate> candidates) {
        Map<Candidate, Double> points = new HashMap<>();
        for (Candidate c : candidates) {
            points.put(c, 0.0);
        }
        for (Voter v : voters) {
            List<Candidate> leastPreferredCandidates = v.getLeastPreferredCandidates(candidates);
            for (Candidate c : leastPreferredCandidates) {
                points.put(c, points.get(c) + 1.0 / leastPreferredCandidates.size());
            }
        }
        return Util.minElementsDouble(points.keySet(), points::get);
    }
    
    @Override
    public String toString() {
        return "Anti-plurality";
    }
}
