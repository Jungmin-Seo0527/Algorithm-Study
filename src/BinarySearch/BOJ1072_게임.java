
package BinarySearch;

import java.io.*;
import java.util.*;

// BOJ1072_게임
// 부동소수점 연산은 정확하지 않으므로 주의
// 정답이 될수 있는 값들중 최소값 구하기
public class BOJ1072_게임
{
	static long X, Y;

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		System.out.println(solve( ));
	}

	static long solve( )
	{
		long start = 1;
		long end = X;
		long temp = Y * 100 / X;

		// 100퍼와 99퍼의 승률은 절대 변할수 없다.
		// 100퍼는 당연이 더이상 오를 승률이 존재하지 않고
		// 99퍼가 승률이 오르기 위해선 100퍼가 되어야 한다(승률의 퍼센트는 모두 정수이기 때문에)
		// 이미 이전에 졌던 전적이 존재하므로 절대로 100퍼센트의 승률이 나올 수 없다.
		if (temp >= 99) return -1;
		while (start < end)
		{
			long mid = (start + end) >> 1;
			long score = (Y + mid) * 100 / (X + mid);
			if (score == temp) start = mid + 1;
			else end = mid;
		}
		return start;
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		X = Long.parseLong(st.nextToken( ));
		Y = Long.parseLong(st.nextToken( ));
	}
}
