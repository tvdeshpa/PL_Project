import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MergeSort {


    private static final int LIST_SIZE = 10000000;
    private static final int MAX_VALUE = 1000;

    public static void main(String args[]) {

        int[] result;

        int[] items = new int[LIST_SIZE];
        Random rand = new Random();
        for(int i = 0; i < LIST_SIZE; i++) {
            items[i] =rand.nextInt() % MAX_VALUE;
        }

        //Sequential
        MergeSortWorker sort = new MergeSortWorker(items);
        long startTime = System.currentTimeMillis();
        sort.mergeSort(items);
        sort.mergeSort(items);
        sort.mergeSort(items);
        long endTime = System.currentTimeMillis();
        System.out.println("Time Taken Sequential :: "+ (endTime - startTime));

        //Parallel

        ExecutorService executor = Executors.newFixedThreadPool(5);
        System.out.println("Parallel Execution ********");
        long parStartTime = System.currentTimeMillis();
        for(int i = 0; i < 3; i ++) {
            Runnable webWorker = new MergeSortWorker(items);
            executor.execute(webWorker);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        long parEndTime = System.currentTimeMillis();

        System.out.println("Time Taken for Parallel :: " + (parEndTime - parStartTime));


    }

}
