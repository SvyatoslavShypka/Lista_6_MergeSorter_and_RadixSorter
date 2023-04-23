import java.util.Arrays;

public class RadixSorter implements ISorter {
    private final IChecker checker;

    public RadixSorter(IChecker checker) {
        this.checker = checker;
    }

    @Override
    public void sort(int[] values) {
        // Find the maximum number to know number of digits
        int m = getMax(values);

        // Do counting sort for every digit. Note that
        // instead of passing digit number, exp is passed.
        // exp is 10^i where i is current digit number
        for (int exp = 1; m / exp > 0; exp *= 10) {
            countSort(values, exp);

            // Pamiętaj o wywołaniu checker.check(values); po kazdym wywołaniu zewnętrznej petli
            checker.check(values);
        }
    }
    // A utility function to get maximum value in arr[]
    public int getMax(int values[])
    {
        int mx = values[0];
        for (int i = 1; i < values.length; i++)
            if (values[i] > mx) mx = values[i];
        return mx;
    }

    // A function to do counting sort of arr[] according to
    // the digit represented by exp.
    public void countSort(int values[], int exp)
    {
        int output[] = new int[values.length]; // output array
        int i;
        int count[] = new int[values.length];
        Arrays.fill(count, 0);

        // Store count of occurrences in count[]
        for (i = 0; i < values.length; i++)
            count[(values[i] / exp) % 10]++;

        // Change count[i] so that count[i] now contains
        // actual position of this digit in output[]
        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];

        // Build the output array
        for (i = values.length - 1; i >= 0; i--) {
            output[count[(values[i] / exp) % 10] - 1] = values[i];
            count[(values[i] / exp) % 10]--;
        }

        // Copy the output array to arr[], so that arr[] now
        // contains sorted numbers according to current
        // digit
        for (i = 0; i < values.length; i++)
            values[i] = output[i];
    }
}
