package kakao.recruit2018;

import java.util.ArrayList;
import java.util.List;

public class 비밀지도 {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[arr1.length];
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr1.length; i++) {
            list.add(arr1[i] | arr2[i]);
        }
        for (int i = 0; i < list.size(); i++) {
            int bit = list.get(i);
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < arr1.length; j++) {
                if ((bit & (1 << j)) == 0) {
                    sb.append(" ");
                } else {
                    sb.append("#");
                }
            }
            answer[i] = sb.reverse().toString();
        }
        return answer;
    }
}
