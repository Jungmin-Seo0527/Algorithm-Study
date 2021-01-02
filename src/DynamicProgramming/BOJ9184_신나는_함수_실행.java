package DynamicProgramming;

import java.io.*;
import java.util.*;

// BOJ9184_신나는_함수_실행
public class BOJ9184_신나는_함수_실행
{
	static int[ ][ ][ ] dp = new int[ 21 ][ 21 ][ 21 ];

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
	}

	static int w(int a, int b, int c)
	{
		// 해당 파라미터의 dp에 값이 존재하면 그 값을 쓴다
		if (isSafeRange(a, b, c) && dp[ a ][ b ][ c ] != 0)
		{
			return dp[ a ][ b ][ c ];
		}

		if (a <= 0 || b <= 0 || c <= 0)
		{
			return dp[ 0 ][ 0 ][ 0 ] = 1;
		}
		if (a > 20 || b > 20 || c > 20)
		{
			return dp[ 20 ][ 20 ][ 20 ] = w(20, 20, 20);
		}

		if (a < b && b < c)
		{
			return dp[ a ][ b ][ c ] = w(a, b, c - 1) + w(a, b - 1, c - 1) - w(a, b - 1, c);
		}

		return dp[ a ][ b ][ c ] = w(a - 1, b, c) + w(a - 1, b - 1, c) + w(a - 1, b, c - 1)
		                - w(a - 1, b - 1, c - 1);

	}

	private static boolean isSafeRange(int a, int b, int c)
	{
		return (0 <= a && a <= 20) && (0 <= b && b <= 20) && (0 <= c && c <= 20);
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder( );

		while (true)
		{
			st = new StringTokenizer(br.readLine( ));
			int a = Integer.parseInt(st.nextToken( ));
			int b = Integer.parseInt(st.nextToken( ));
			int c = Integer.parseInt(st.nextToken( ));
			if (a == -1 && b == -1 && c == -1) break;
			sb.append("w(" + a + ", " + b + ", " + c + ") = " + w(a, b, c) + "\n");
		}
		System.out.println(sb);
	}
}

