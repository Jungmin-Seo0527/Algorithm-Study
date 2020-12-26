
package INU_code_festival_2020;

import java.io.*;
import java.util.*;

// BOJ20362_유니대전_퀴즈쇼
public class BOJ20362_유니대전_퀴즈쇼
{
	static int N, cnt, idx;
	static String[ ][ ] data;
	static String ans_person, ans_data;

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		N = Integer.parseInt(st.nextToken( ));
		ans_person = st.nextToken( );
		data = new String[ 2 ][ N ];
		for (int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine( ));
			data[ 0 ][ i ] = st.nextToken( );
			data[ 1 ][ i ] = st.nextToken( );
			if (data[ 0 ][ i ].compareTo(ans_person) == 0)
			{
				ans_data = data[ 1 ][ i ];
				idx = i; // 정답자의 위치 정보
			}
		}

		// 정답자의 위치 정보 이전의 답중 정답자의 답과 일치하는 정답을 외친 인원 count
		for (int i = 0; i < idx; i++)
		{
			if (data[ 1 ][ i ].compareTo(ans_data) == 0)
			{
				cnt++;
			}
		}
		System.out.println(cnt);
	}

}