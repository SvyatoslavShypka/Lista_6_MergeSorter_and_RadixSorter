import java.util.Arrays;

public class IterativeMergeSorter implements ISorter {
    private final IChecker checker;

    public IterativeMergeSorter(IChecker checker) {
        this.checker = checker;
    }

    @Override
    public void sort(int[] values) {
        // sorting intArray[low...high] using iterative approach
        int low = 0; int high = values.length - 1;
        // sort array intArray[] using temporary array temp
        int[] temp = Arrays.copyOf(values, values.length);
        // divide the array into blocks of size m
        // m = [1, 2, 4, 8, 16...]
        for (int m = 1; m <= high - low; m = 2*m) {
            for (int i = low; i < high; i += 2*m) {
                int start = i;
                int mid = (i + m - 1);
                if (mid > high) mid = high;
                int end = i + 2 * m - 1;
                if (end > high) end = high;

        //call merge routine to merge the arrays
                merge(values, temp, start, mid, end);
            }
        // Pamiętaj o wywołaniu checker.check(values); po kazdym wywołaniu zewnętrznej petli
            checker.check(values);
        }
    }

    public void merge(int[] values, int[] temp, int start, int mid, int end) {
        int k = start, i = start, j = mid + 1;
        // traverse through elements of left and right arrays
        while (i <= mid && j <= end) {
            if (values[i] < values[j]) {
                temp[k++] = values[i++];
            } else {
                temp[k++] = values[j++];
            }
        }
        // Copy remaining elements
        while (i <= mid) {
            temp[k++] = values[i++];
        }
        // copy temp array back to the original array to reflect sorted order
        for (i = start; i <= end; i++) {
            values[i] = temp[i];
        }
    }
}
