public class MergeSortWorker implements Runnable {

    int[] items;
    MergeSortWorker(int[] items) {
        this.items = items;
    }
    public int[] mergeSort(int[] items) {
        if(items.length == 1)
            return items;
        //System.out.println("Inside");
        int splitPoint = items.length / 2;
        int[] leftArray = new int[splitPoint];
        int[] rightArray = new int[items.length - splitPoint];
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

    private int[] merge(int[] left, int[] right) {
        int leftIndex = 0;
        int rightIndex = 0;
        int[] merged = new int[left.length + right.length];
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

    @Override
    public void run() {
        mergeSort(this.items);
        System.out.println("Sorting Complete");

    }
}
