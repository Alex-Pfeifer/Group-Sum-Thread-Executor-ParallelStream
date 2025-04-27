package ait.numbers.model;

import ait.numbers.task.OneGroupSum;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorGroupSum extends GroupSum{
    public ExecutorGroupSum(int[][] numberGroups) {
        super(numberGroups);
    }

    @Override
    public int computeSum() {
        OneGroupSum[] tasks = new OneGroupSum[numberGroups.length];
        int cpus = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(cpus * 2);

        List<Future<OneGroupSum>> futures = new ArrayList<>();

        for (int i = 0; i < numberGroups.length; i++) {
            OneGroupSum task = new OneGroupSum(numberGroups[i]);
            tasks[i] = task;
            futures.add(executor.submit(() -> {
                task.run();
                return task;
            }));
        }

//        for (Future<OneGroupSum> future : futures) {
//            try {
//                futures.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        int totalSum = 0;
        for (Future<OneGroupSum> future : futures) {
            OneGroupSum task = null;
            try {
                task = future.get();
                totalSum = totalSum + task.getSum();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        return totalSum;



        // TODO ExecutorGroupSum
//        return 0;
    }
}
