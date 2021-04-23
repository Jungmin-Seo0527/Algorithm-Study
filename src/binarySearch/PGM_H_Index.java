package binarySearch;

import java.util.*;

public class PGM_H_Index {
    public int solution(int[] citations) {

        Arrays.sort(citations);
        int answer = citations[0];
        for (int i = 0; i <= citations[citations.length - 1]; i++) {
            int idx = bs(citations, i);
            if (idx <= i && citations.length - idx >= i) {
                answer = i;
            }
        }

        return answer;
    }

    private int bs(int[] arr, int target) {
        int start = 0;
        int end = arr.length;

        while (start < end) {
            int mid = (start + end) >>> 1;
            if (arr[mid] >= target) end = mid;
            else start = mid + 1;

        }
        return end;
    }
}
