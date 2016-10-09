package voting;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.ToDoubleFunction;
import java.util.stream.DoubleStream;

public final class Util {
    
    private Util() {}
    
    public static double clamp(double min, double val, double max) {
        if (val <= min) return min;
        else if (val >= max) return max;
        else return val;
    }
    
    public static <T> List<T> maxElementsDouble(Iterable<T> elts, ToDoubleFunction<T> keyFn) {
        List<T> maxElts = new ArrayList<T>();
        double max = Double.NEGATIVE_INFINITY;
        for (T t : elts) {
            double val = keyFn.applyAsDouble(t);
            if (val > max) {
                maxElts.clear();
                max = val;
            }
            if (val == max) {
                maxElts.add(t);
            }
        }
        return maxElts;
    }
    
    public static <T> List<T> minElementsDouble(Iterable<T> elts, ToDoubleFunction<T> keyFn) {
        return maxElementsDouble(elts, t -> -keyFn.applyAsDouble(t));
    }
    
    public static int weightedRandInt(double[] weights) {
        double sum = Math.random() * DoubleStream.of(weights).sum();
        for (int i=0; i<weights.length; i++) {
            if (sum < weights[i]) {
                return i;
            }
            else {
                sum -= weights[i];
            }
        }
        // Shouldn't happen, but might if we get a rounding error.
        return weights.length - 1;
    }
    
    public static String formatTable(List<List<String>> rows, boolean[] alignment, int headerRowCount) {
        int columnCount = rows.get(0).size();
        int[] columnWidths = new int[columnCount];
        for (List<String> row : rows) {
            if (row.size() != columnCount) {
                throw new IllegalArgumentException("rows must all have the same length");
            }
            // Determine maximum column widths for each column.
            for (int i=0; i<columnCount; i++) {
                if (row.get(i).length() > columnWidths[i]) {
                    columnWidths[i] = row.get(i).length();
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        int rowIndex = 0;
        for (List<String> row : rows) {
            if (rowIndex == headerRowCount) {
                sb.append('\n');
                for (int i=0; i<columnCount; i++) {
                    if (i != 0) {
                        sb.append("-+-");
                    }
                    sb.append(StringUtils.repeat('-', columnWidths[i]));
                }
            }
            if (rowIndex != 0) {
                sb.append('\n');
            }
            for (int i=0; i<columnCount; i++) {
                if (i != 0) {
                    sb.append(" | ");
                }
                if (alignment[i]) {
                    sb.append(StringUtils.leftPad(row.get(i), columnWidths[i]));
                }
                else {
                    sb.append(StringUtils.rightPad(row.get(i), columnWidths[i]));
                }
            }
            rowIndex += 1;
        }
        return sb.toString();
    }
    
    public static double transformLinearly(double value,
                                           double originalLower, double originalUpper,
                                           double newLower, double newUpper) {
        double fraction = (value - originalLower) / (originalUpper - originalLower);
        return newLower + fraction * (newUpper - newLower);
    }
    
}
