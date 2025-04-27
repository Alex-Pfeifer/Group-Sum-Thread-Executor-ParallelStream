package ait.numbers.model;

import ait.numbers.task.OneGroupSum;

public class ThreadGroupSum extends GroupSum {
    public ThreadGroupSum(int[][] numberGroups) {
        super(numberGroups);
    }

    @Override
    public int computeSum() {
        OneGroupSum[] tasks = new OneGroupSum[numberGroups.length];
        Thread[] threads =new Thread[numberGroups.length];

        for (int i = 0; i < numberGroups.length; i++) {
            tasks[i] = new OneGroupSum(numberGroups[i]);
            threads[i] = new Thread(tasks[i]);
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int totalSum = 0;
        for (OneGroupSum task : tasks) {
            totalSum = totalSum + task.getSum();
        }

        return totalSum;

        // TODO ThreadGroupSum
//        return 0;
    }
}
