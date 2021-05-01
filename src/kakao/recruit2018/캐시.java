package kakao.recruit2018;

import java.util.LinkedList;
import java.util.Queue;

public class 캐시 {
    Queue<String> que = new LinkedList<>();

    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        if (cacheSize == 0) return cities.length * 5;

        for (String city : cities) {
            city = city.toLowerCase();
            int hit = hittingCache(city);
            if (hit == 0) {
                answer += missingCache(city, cacheSize);
            } else {
                answer += hit;
            }
        }
        return answer;
    }

    private int missingCache(String str, int cacheSZ) {
        if (que.size() == cacheSZ) {
            que.poll();
        }
        que.add(str);
        return 5;
    }

    private int hittingCache(String str) {
        int ret = 0;
        if (que.remove(str)) {
            que.add(str);
            ret = 1;
        }
        return ret;
    }
}