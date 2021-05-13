package binarySearch;

import java.io.*;

public class BOJ1300_K번째수 {
	static int N, K;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		inputAndSettingData();
		solve();
	}

	static void solve() {
		int start = 0;
		int end = K;
		while (start < end) {
			int mid = (start + end) >>> 1;
			if (getIdx(mid) >= K) {
				end = mid;
			} else {
				start = mid + 1;
			}
		}
		System.out.println(end);
	}

	static int getIdx(int m) {
		int ret = 0;
		for (int i = 1; i <= N; i++) {
			ret += Math.min(m / i, N);
		}
		return ret;
	}

	static void inputAndSettingData() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
	}
}
