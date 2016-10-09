package voting;

import java.util.*;
import java.util.stream.Collectors;

public abstract class Voter extends Person {
    
    private static int voterSerialNumberCounter = 1;
    private final int voterSerialNumber;
    
    public Voter(double[] preferences) {
        super(preferences);
        this.voterSerialNumber = voterSerialNumberCounter++;
    }
    
    public abstract double utilityIfCandidateElected(Candidate candidate);
    
    public List<Candidate> getMostPreferredCandidates(List<Candidate> candidates) {
        List<Candidate> preferredCandidates = new ArrayList<>();
        double bestUtility = Double.NEGATIVE_INFINITY;
        for (Candidate c : candidates) {
            double utility = utilityIfCandidateElected(c);
            if (utility > bestUtility) {
                preferredCandidates.clear();
                bestUtility = utility;
            }
            if (utility == bestUtility) {
                preferredCandidates.add(c);
            }
        }
        return preferredCandidates;
    }
    
    public List<Candidate> getLeastPreferredCandidates(List<Candidate> candidates) {
        List<Candidate> leastPreferredCandidates = new ArrayList<>();
        double worstUtility = Double.POSITIVE_INFINITY;
        for (Candidate c : candidates) {
            double utility = utilityIfCandidateElected(c);
            if (utility < worstUtility) {
                leastPreferredCandidates.clear();
                worstUtility = utility;
            }
            if (utility == worstUtility) {
                leastPreferredCandidates.add(c);
            }
        }
        return leastPreferredCandidates;
    }
    
    public List<Set<Candidate>> getPartialPreferenceOrder(List<Candidate> candidates) {
        return candidates
                .stream()
                // Group candidates by utility, returning a map from utilities to sets of candidates.
                .collect(Collectors.groupingBy(this::utilityIfCandidateElected, Collectors.toSet()))
                .entrySet()
                .stream()
                // Sort entries in the utility-candidates map by key (utility)
                // The compiler needs a type hint to figure this out, unfortunately
                .sorted(Comparator.<Map.Entry<Double, Set<Candidate>>, Double>comparing(Map.Entry::getKey).reversed())
                // Take only the values (sets of candidates)
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
    
    @Override
    public String toString() {
        return "Voter #" + voterSerialNumber + " with preferences " + Arrays.toString(preferences);
    }
    
}
