package voting;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public final class Main {
    
    private Main() {}
    
    public static void main(String[] args) {
        runAllTests();
    }
    
    public static void runAllTests() {
    
        List<String> tests = Stream.of("Baseline", "Popularity", "ManyCandidates", "Polarized").collect(Collectors.toList());
        System.out.println("Running tests: " + tests.stream().collect(Collectors.joining(", ")));
        long startTime = System.nanoTime();
        
        VotingSystem[] votingSystems = {};
        List<List<String>> columns = new ArrayList<>();
        
        for (String test : tests) {
            // Begin code that should be executed once per column.
            // utilities is indexed first by the test number and then by the voting system number.
            int dims = 3;
            int voterCount = 100;
            int candidateCount = test.contains("Many") ? 50 : 5;
            int testCount = test.contains("Many") ? 2000 : 20000;
            UtilityCombiner utilityCombiner = new SummingUtilityCombiner();
            double confidence = 0.95;
            
            double[][] utilities = new double[testCount][];
            for (int i = 0; i < testCount; i++) {
                VoterPopulation voterPopulation = new VoterPopulation(
                        new WeightedPreferenceVoterDistribution(
                                test.contains("Polarized")
                                        ? new LinearCombinationPointDistribution(
                                        new PointDistribution[] {
                                                new NormalPointDistribution(dims, false, 1, -5),
                                                new NormalPointDistribution(dims, false, 1, 5)
                                        },
                                        new double[] {1, 1})
                                        : new NormalPointDistribution(dims),
                                new NormalPointDistribution(dims, true)),
                        voterCount);
                CandidatePopulation candidatePopulation = new CandidatePopulation(
                        new CandidateDistribution(
                                test.contains("Polarized")
                                        ? new NormalPointDistribution(dims, false, 5)
                                        : new NormalPointDistribution(dims),
                                test.contains("Popularity")
                                        ? new NormalDistribution(true)
                                        : new ConstantDistribution(1)),
                        candidateCount);
                Random random = new Random();
                double[] sampleUtilities = new double[20];
                for (int j = 0; j < 20; j++) {
                    sampleUtilities[j] = voterPopulation
                            .getVoters()
                            .get(random.nextInt(voterPopulation.getSize()))
                            .utilityIfCandidateElected(
                                    candidatePopulation
                                            .getCandidates()
                                            .get(random.nextInt(candidatePopulation.getSize())));
                }
                double lowerUtilityBound = DoubleStream.of(sampleUtilities).average().getAsDouble() * 2;
                votingSystems = new VotingSystem[] {
                        new AntiPlurality(),
                        new Plurality(),
                        new InstantRunoff(),
                        new BordaCount(),
                        new RangeVoting(lowerUtilityBound, 0, true),
                        new RangeVoting(lowerUtilityBound, 0, false),
                        new MaxUtility(utilityCombiner)
                };
                double[] systemUtilities = new double[votingSystems.length];
                for (int j = 0; j < systemUtilities.length; j++) {
                    systemUtilities[j] = Election.getUtility(
                            voterPopulation,
                            candidatePopulation,
                            votingSystems[j],
                            utilityCombiner
                    );
                }
                utilities[i] = systemUtilities;
            }
            List<String> column = new ArrayList<>();
            for (int j = 0; j < votingSystems.length; j++) {
                double[] testUtilities = new double[testCount];
                for (int i = 0; i < testCount; i++) {
                    testUtilities[i] = utilities[i][j];
                }
                ConfidenceInterval interval = new ConfidenceInterval(testUtilities, confidence).negate();
                column.add(interval.toString("%3.0f"));
            }
            columns.add(column);
            // End code that should be executed once per column.
        }
            
        List<String> labels = new ArrayList<>();
        for (VotingSystem votingSystem : votingSystems) {
            labels.add(votingSystem.toString());
        }
        columns.add(0, labels);
        
        List<List<String>> rows = new ArrayList<>();
        for (int rowIndex=0; rowIndex<votingSystems.length; rowIndex++) {
            int rowIndexCopy = rowIndex; // because Java doesn't have actual closures for its lambdas
            List<String> row = new ArrayList<>();
            columns.stream().map(col -> col.get(rowIndexCopy)).forEach(row::add);
            rows.add(row);
        }
        
        tests.add(0, "Voting system");
        rows.add(0, tests);
        
        boolean[] alignment = new boolean[tests.size() + 1];
        
        System.out.println();
        System.out.println(Util.formatTable(rows, alignment, 1));
        System.out.println();
        System.out.printf("Elapsed time: %.1f seconds.%n", (System.nanoTime() - startTime) / 1e9);
        
    }
    
}
