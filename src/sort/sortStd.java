package sort;

import java.io.*;
import java.util.*;

public class sortStd {

    static int N;
    static int[] arr;

    static void solve() {
        // bubbleSort();
        // selectionSort();
        // insertionSort();
        quickSort();
    }

    /**
     * <h1>거품 정렬</h1>
     * 서로 인접한 두 원소의 대소를 비교하고, 조건에 맞지 않다면 자리를 교환하여 정렬하는 알고리즘 <br><br>
     * <h2>Process</h2>
     */
    static void bubbleSort() {
        for (int i = 0; i < N; i++) {
            for (int j = 1; j < N - i; j++) {
                if (arr[j - 1] > arr[j]) {
                    swap(arr, j - 1, j);
                }
            }
        }
    }

    /**
     * <h1>
     * 선택 정렬
     * </h1>
     * 해당 순서에 원소를 넣을 위치는 이미 정해져 있고, 어떤 원소를 넣을지 선택하는 알고리즘 <br><br>
     * <h2>Process</h2>
     * <ol>
     *  <li> 주어진 배열 중에 최소값을 찾는다.</li>
     *  <li> 그 값을 맨 앞에 위치한 값과 교체한다.</li>
     *  <li> 맨 처음 위치를 뺀 나머지 배열을 같은 방법으로 교체한다.</li>
     * </ol>
     * <p>
     * 핵심은 i index부터 배열 끝까지의 숫자 중에 최소값이 i 번째 위치한다는 것 <br>
     * 예) i = 0 인 경우 가장 첫번째에 오는 값은 최소값이 된다.<br>
     * i = 1 인 경우 i = 1이후의 값중(i = 0 에는 이미 배열의 최소값이 위치했으니)최소값을 위치하면 된다.<br>
     * <ul>
     *     <li> 시간 복잡도: O(n^2) - 최선, 평균, 최악 모두 동일</li>
     *     <li> 그나마 Bubble sort 보다는 swap이 적게 발생하는 장점이 있다.</li>
     * </ul>
     */
    static void selectionSort() {
        for (int i = 0; i < N - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < N; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            swap(arr, i, minIdx);
        }
    }

    /**
     * <h1>삽입 정렬</h1>
     * <ul>
     *     <li>Selection Sort와 유사하지만, 좀 더 효율적인 정렬 알고리즘</li>
     *     <li>2번째 원소부터 시작하여 그 앞(왼쪽)의 원소들과 비교하여 삽일할 위치를 지정</li>
     *     <li>원소를 뒤로 옮기고 지정된 자리에 자료를 삽입하여 정렬</li>
     * </ul>
     *
     * <h2>Process</h2>
     * 1. 정렬은 2번째 위치의 값을 cur에 저장 <br>
     * 2. cur와 이전에 있는 원소들과 비교하며 삽입한다. <br>
     * 3. '1'번으로 돌아가 다음 위치(index)의 값을 temp에 저장하고, 반복한다. <br>
     * <p>
     * 즉 현재 값이 왼쪽에 존재하는 값보다 크다면 계속 왼쪽으로 이동한다.
     * <p>
     * 시간 복잡도
     * 최악: O(N^2)
     * 최선: O(N) - 이미 정렬되어 있는 경우 한번씩만 비교하면 된다.
     */
    static void insertionSort() {
        for (int i = 1; i < N; i++) {
            int cur = arr[i];
            int prev = i - 1;
            while ((prev >= 0) && (arr[prev] > cur)) {
                arr[prev + 1] = arr[prev];
                prev--;
            }
            arr[prev + 1] = cur;
        }
    }

    /**
     * <h1>퀵 정렬(Quick Sort)</h1>
     * <ul>
     *     <li>Quick Sort는 <b>분할 정복(divide and conquer)방법</b>을 통해 주어진 배열을 정렬한다.</li>
     *     <li>불안정 정렬</li>
     *     <li>다른 원소와의 비교만으로 정렬을 수행하는 비교 정렬</li>
     *     <li>Merge Sort와 달리 배열을 균등하게 분할</li>
     * </ul>
     */
    static void quickSort() {
        quickSort(arr, 0, N - 1);
    }

    static void quickSort(int[] arr, int left, int right) {
        if (left >= right) return;
        int pivot = partition(arr, left, right);

        quickSort(arr, left, pivot - 1);
        quickSort(arr, pivot + 1, right);
    }

    static int partition(int[] arr, int left, int right) {
        int pivot = arr[left];
        int l = left, r = right;

        while (l < r) {
            while (pivot < arr[r]) {
                r--;
            }
            while (l < r && pivot >= arr[l]) {
                l++;
            }
            swap(arr, l, r);
        }
        arr[left] = arr[l];
        arr[l] = pivot;
        return l;
    }

    static void mergeSort() {

    }

    static void heapSort() {

    }

    static void swap(int[] arr, int idx1, int idx2) {
        int temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }

    public static void main(String[] args) throws IOException {
        System.out.println("===== input =====");
        String fileName = "input/input1.txt";
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        BufferedReader br2 = new BufferedReader(new FileReader(fileName));
        String s;
        while ((s = br2.readLine()) != null) {
            System.out.println(s);
        }
        System.out.println("===== output =====");
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        solve();
        System.out.println(Arrays.toString(arr));
    }
}
