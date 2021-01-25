
package Codeforces_Round690_Div3;

import java.io.*;
import java.util.*;

// 단순 반복문 응용
// 입력받은 숫자들을 배열의 양 끝에 왕복으로 채워넣어 배열을 만든다.
// 그 배열을 보고 원래 입력받은 숫자들을 출력하는 문제
public class A_Favorite_Sequence
{
	static int T, n, arr[];
	static StringBuilder sb = new StringBuilder( );

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		T = Integer.parseInt(st.nextToken( ));
		for (int t = 0; t < T; t++)
		{
			st = new StringTokenizer(br.readLine( ));
			n = Integer.parseInt(st.nextToken( ));
			arr = new int[ n ];
			st = new StringTokenizer(br.readLine( ));
			int idx = 0;
			while (idx < n)
			{
				arr[ idx ] = Integer.parseInt(st.nextToken( ));
				idx = idx + 2;
			}
			if (n % 2 == 1) idx = idx - 3;
			else idx = idx - 1;

			while (idx > 0)
			{
				arr[ idx ] = Integer.parseInt(st.nextToken( ));
				idx = idx - 2;
			}

			for (int i = 0; i < n; i++)
			{
				sb.append(arr[ i ] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
