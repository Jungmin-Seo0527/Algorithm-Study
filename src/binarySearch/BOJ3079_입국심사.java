package binarySearch;

import java.io.*;
import java.util.*;

public class BOJ3079_입국심사 {

	static int N, M;
	static long[] arr;
	static long max = Long.MIN_VALUE;

	static void solve() {
		long left = 1;
		long right = max * M;
		while (left < right) {
			long mid = (left + right) >>> 1;
			long sum = 0;
			for (int i = 0; i < N; i++) {
				sum += mid / arr[i];
				if (sum >= M) {
					break;
				}
			}
			if (sum >= M) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		System.out.println(right);
	}

	public static void main(String[] args) throws IOException {
		// BufferedReader br = readInputFile();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new long[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			max = Math.max(arr[i], max);
		}
		solve();
	}

	private static BufferedReader readInputFile() throws IOException {
		System.out.println("===== input =====");
		String fileName = "input/input2.txt";
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		BufferedReader br2 = new BufferedReader(new FileReader(fileName));
		String s;
		while ((s = br2.readLine()) != null) {
			System.out.println(s);
		}
		System.out.println("===== output =====");
		return br;
	}
}
