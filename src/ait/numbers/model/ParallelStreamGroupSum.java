package ait.numbers.model;

import java.util.Arrays;

public class ParallelStreamGroupSum extends GroupSum{
    public ParallelStreamGroupSum(int[][] numberGroups) {
        super(numberGroups);
    }

    @Override
    public int computeSum() {
        return Arrays.stream(numberGroups)
                .parallel()
                .flatMapToInt(group -> Arrays.stream(group))
                .sum();

        // TODO * ParallelStreamGroupSum, use parallel stream
//        return 0;
    }
}
