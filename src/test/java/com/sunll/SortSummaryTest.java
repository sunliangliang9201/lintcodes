import com.sunll.lintcode.utils.SortSummary;
import org.junit.Test;

/**
 * compare all sort method cost the time.
 */
public class SortSummaryTest {
    SortSummary sort = new SortSummary();
    double start;
    double end;
    int[] arr;
    int[] test;
    int total = 10000;
    @org.junit.Before
    public void setUp() throws Exception {
        arr = new int[total];
        for(int i = 0; i < arr.length; i++){
            arr[i] = (int)(Math.random() * 1000);
        }
        test = arr.clone();
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @Test
    public void allTest() {
        arr = test.clone();
        insertionSort();
        arr = test.clone();
        shellSort();
        arr = test.clone();
        selectSort();
        arr = test.clone();
        mergeSort();
        arr = test.clone();
        bubbleSort();
        arr = test.clone();
        bucketSort();
        arr = test.clone();
        countingRadixSort();
        arr = test.clone();
        radixSort();
        arr = test.clone();
        quickSort();
        arr = test.clone();
        heatSort();
    }
    public void startTime(){
        start = System.currentTimeMillis();
    }
    public void endTime() {
        end = System.currentTimeMillis();
    }

    @org.junit.Test
    public  void insertionSort() {
        startTime();
        sort.insertionSort(arr);
        endTime();
        System.out.println("insertionSort:" + (end - start));
    }

    @org.junit.Test
    public void shellSort() {
        startTime();
        sort.shellSort(arr);
        endTime();
        System.out.println("sheelSort:" + (end - start));
    }

    @org.junit.Test
    public void selectSort() {
        startTime();
        sort.selectSort(arr);
        endTime();
        System.out.println("selectSort:" + (end - start));
    }

    @org.junit.Test
    public void heatSort() {
        startTime();
        sort.heatSort(arr);
        endTime();
        System.out.println("heatSort:" + (end - start));
    }

    @org.junit.Test
    public void bubbleSort() {
        startTime();
        sort.bubbleSort(arr);
        endTime();
        System.out.println("bubbleSort:" + (end - start));
    }

    @org.junit.Test
    public void quickSort() {
        startTime();
        sort.quickSort(arr, 0, arr.length - 1);
        endTime();
        System.out.println("quickSort:" + (end - start));
    }

    @org.junit.Test
    public void mergeSort() {
        startTime();
        sort.mergeSort(arr, 0, arr.length - 1);
        endTime();
        System.out.println("mergeSort:" + (end - start));
    }

    @org.junit.Test
    public void countingRadixSort() {
        startTime();
        sort.countingRadixSort(arr);
        endTime();
        System.out.println("countingRadixSort:" + (end - start));
    }

    @org.junit.Test
    public void radixSort() {
        startTime();
        sort.radixSort(arr);
        endTime();
        System.out.println("radixSort:" + (end - start));
    }

    @org.junit.Test
    public void bucketSort() {
        startTime();
        sort.bucketSort(arr);
        endTime();
        System.out.println("bucketSort:" + (end - start));
    }
}