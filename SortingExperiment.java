import java.util.Arrays;
import java.util.Random;

public class SortingExperiment {

    static class SortResult {
        long comparisons;
        long executionTimeNs;

        public SortResult(long comparisons, long executionTimeNs) {
            this.comparisons = comparisons;
            this.executionTimeNs = executionTimeNs;
        }
    }

    // --- 1. SELECTION SORT ---
    public static SortResult selectionSort(int[] original) {
        int[] arr = original.clone();
        long comparisons = 0;
        int n = arr.length;

        long startTime = System.nanoTime();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                comparisons++;
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
        long endTime = System.nanoTime();

        return new SortResult(comparisons, endTime - startTime);
    }

    // --- 2. INSERTION SORT ---
    public static SortResult insertionSort(int[] original) {
        int[] arr = original.clone();
        long comparisons = 0;
        int n = arr.length;

        long startTime = System.nanoTime();
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0) {
                comparisons++;
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
        }
        long endTime = System.nanoTime();

        return new SortResult(comparisons, endTime - startTime);
    }

    // --- 3. MERGE SORT ---
    private static long mergeComparisons = 0;

    public static SortResult mergeSort(int[] original) {
        int[] arr = original.clone();
        mergeComparisons = 0;

        long startTime = System.nanoTime();
        sort(arr, 0, arr.length - 1);
        long endTime = System.nanoTime();

        return new SortResult(mergeComparisons, endTime - startTime);
    }

    private static void sort(int[] arr, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        sort(arr, left, mid);
        sort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++) L[i] = arr[left + i];
        for (int j = 0; j < n2; j++) R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            mergeComparisons++;
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }
        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    // --- 4. QUICK SORT ---
    private static long quickComparisons = 0;

    public static SortResult quickSort(int[] original) {
        int[] arr = original.clone();
        quickComparisons = 0;

        long startTime = System.nanoTime();
        qSort(arr, 0, arr.length - 1);
        long endTime = System.nanoTime();

        return new SortResult(quickComparisons, endTime - startTime);
    }

    private static void qSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            qSort(arr, low, pi - 1);
            qSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            quickComparisons++;
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    public static void main(String[] args) {
        int[] sizes = {20, 50, 100, 500};
        Random rand = new Random(42); // Seeded for reproducibility

        System.out.printf("%-15s | %-10s | %-20s | %-20s\n", "Algorithm", "Input Size", "Comparisons", "Execution Time (ns)");
        for (int size : sizes) {
            int[] baseArray = new int[size];
            for (int i = 0; i < size; i++) {
                baseArray[i] = rand.nextInt(1000);
            }
            runAndPrint("Selection Sort", size, selectionSort(baseArray));
            runAndPrint("Insertion Sort", size, insertionSort(baseArray));
            runAndPrint("Merge Sort",     size,     mergeSort(baseArray));
            runAndPrint("Quick Sort",     size,     quickSort(baseArray));
        }

        System.out.println("\n--- ADDITIONAL TEST: Almost-Sorted Array (Size 100) ---");
        int[] almostSorted = new int[100];
        for (int i = 0; i < 100; i++) almostSorted[i] = i * 2; // sorted values
       
        for (int p = 0; p < 5; p++) {
            int idx = p * 15 + 2;
            int temp = almostSorted[idx];
            almostSorted[idx] = almostSorted[idx + 1];
            almostSorted[idx + 1] = temp;
        }

        runAndPrint("Selection Sort", 100, selectionSort(almostSorted));
        runAndPrint("Insertion Sort", 100, insertionSort(almostSorted));
        runAndPrint("Merge Sort",     100,     mergeSort(almostSorted));
        runAndPrint("Quick Sort",     100,     quickSort(almostSorted));
    }

    private static void runAndPrint(String algo, int size, SortResult res) {
        System.out.printf("%-15s | %-10d | %-20d | %-20d\n", algo, size, res.comparisons, res.executionTimeNs);
    }
}
