import java.util.Random;

public class MergeSort {


    private static final int LIST_SIZE = 1000000;
    private static final int MAX_VALUE = 1000;

    public static void main(String args[]) {

        Integer[] result;
        MergeSort sort = new MergeSort();

        Integer[] items = new Integer[LIST_SIZE];
        Random rand = new Random();
        for(int i = 0; i < LIST_SIZE; i++) {
            items[i] =rand.nextInt() % MAX_VALUE;
        }

        long startTime = System.currentTimeMillis();
        sort.mergeSort(items);
        sort.mergeSort(items);
        sort.mergeSort(items);
        long endTime = System.currentTimeMillis();


        System.out.println("Time Taken Sequential :: "+ (endTime - startTime));


    }

    public Integer[] mergeSort(Integer[] items) {
        if(items.length == 1)
            return items;
        //System.out.println("Inside");
        int splitPoint = items.length / 2;
        Integer[] leftArray = new Integer[splitPoint];
        Integer[] rightArray = new Integer[items.length - splitPoint];
        int traverseIndex = 0;
        for(int i = 0; i < splitPoint; i++) {
            leftArray[i] = items[traverseIndex];
            traverseIndex++;
        }

        for(int i = 0; i < (items.length - splitPoint); i++) {
            rightArray[i] = items[traverseIndex];
            traverseIndex++;
        }

        return merge(mergeSort(leftArray), mergeSort(rightArray));
    }

    private Integer[] merge(Integer[] left, Integer[] right) {
        int leftIndex = 0;
        int rightIndex = 0;
        Integer[] merged = new Integer[left.length + right.length];
        int mergedIndex = 0;

        while (leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex] <= right[rightIndex]) {
                merged[mergedIndex] = left[leftIndex];
                leftIndex += 1;
            } else {
                merged[mergedIndex] = right[rightIndex];
                rightIndex += 1;
            }
            mergedIndex++;
        }
        if (leftIndex == left.length) {
            for(int i = rightIndex; i < right.length; i++) {
                merged[mergedIndex] = right[i];
                mergedIndex++;
            }
        } else {
            for(int i = leftIndex; i < left.length; i++) {
                merged[mergedIndex] = left[i];
                mergedIndex++;
            }
        }
        return merged;

    }

}
