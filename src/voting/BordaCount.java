package voting;

import java.util.*;

public class BordaCount implements VotingSystem {
    
    @Override
    public List<Candidate> getWinners(List<Voter> voters, List<Candidate> candidates) {
        Map<Candidate, Double> points = new HashMap<>();
        for (Candidate c : candidates) {
            points.put(c, 0.0);
        }
        for (Voter v : voters) {
            List<Set<Candidate>> order = v.getPartialPreferenceOrder(candidates);
            int upperPointLimit = candidates.size() - 1;
            for (Set<Candidate> equivalenceClass : order) {
                double pointsToAdd = upperPointLimit - (equivalenceClass.size() - 1) / 2.0;
                for (Candidate c : equivalenceClass) {
                    points.put(c, points.get(c) + pointsToAdd);
                }
                upperPointLimit -= equivalenceClass.size();
            }
        }
        return Util.maxElementsDouble(points.keySet(), points::get);
    }
    
    @Override
    public String toString() {
        return "Borda count";
    }
}
