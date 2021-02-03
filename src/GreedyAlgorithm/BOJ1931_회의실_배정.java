package GreedyAlgorithm;

import java.io.*;
import java.util.*;

// BOJ1931_회의실_배정
// Greedy Algorithm + Priority Queue
// 회의실 배정 문제는 그리디 문제에서 유명한 문제
// 회의가 끝나는 시간을 기준으로 오름차순으로 정렬하되 끝나는 시간이 같은 경우는 시작시간으로 오름차순 정렬
// 시작 시간 오름 차순 예 (1, 3) (3, 5) (5, 5)
// 정렬은 PriorityQueue로 구현

public class BOJ1931_회의실_배정 {
    static class Point implements Comparable<Point> {
        int start, end;

        public Point(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Point o) {
            if (this.end < o.end) return -1;
            else if (this.end == o.end) {
                if (this.start < o.start) return -1;
            }
            return 1;
        }
    }

    static int N;
    static PriorityQueue<Point> que = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        sovle();
    }

    static void sovle() {
        int cnt = 1;
        int cur_time = que.poll().end;
        while (!que.isEmpty()) {
            Point cur = que.poll();
            if (cur.start >= cur_time) {
                cnt++;
                cur_time = cur.end;
            }
        }
        System.out.println(cnt);
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            que.add(new Point(start, end));
        }
    }
}

