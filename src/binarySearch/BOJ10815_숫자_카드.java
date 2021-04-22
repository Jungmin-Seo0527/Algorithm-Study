
package binarySearch;

import java.io.*;
import java.util.*;

// BOJ10815_숫자_카드
public class BOJ10815_숫자_카드
{
	static int N;
	static BitSet bitset;
	static int s = 10000000;

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		N = Integer.parseInt(st.nextToken( ));
		bitset = new BitSet( );

		st = new StringTokenizer(br.readLine( ));
		for (int i = 0; i < N; i++)
		{
			int temp = Integer.parseInt(st.nextToken( ));

			// 주어진 입력값의 범위가 -10000000부터 시작하는데 bitset의 index는 음수를 가질 수 없다.
			// 따라서 주어지는 입력값에 무조건 10000000을 더해주어 0이상으로 만들어서 setting한다
			bitset.set(temp + s);
		}

		st = new StringTokenizer(br.readLine( ));
		N = Integer.parseInt(st.nextToken( ));
		st = new StringTokenizer(br.readLine( ));

		StringBuilder sb = new StringBuilder( );
		for (int i = 0; i < N; i++)
		{
			int find = Integer.parseInt(st.nextToken( ));
			boolean ans = bitset.get(find + s);
			if (ans) sb.append("1 ");
			else sb.append("0 ");
		}
		System.out.println(sb);
	}
}
