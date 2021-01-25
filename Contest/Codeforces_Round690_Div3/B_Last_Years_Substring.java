
package Codeforces_Round690_Div3;

import java.io.*;
import java.util.*;

// 800
// 숫자로 주어진 문자열이 주어졌을때
// 부분문자열을 0번 또는 1번만 지워서
// 2020 으로 만드는 문제
public class B_Last_Years_Substring
{
	static int T, N, arr[];
	static StringBuilder sb = new StringBuilder( );

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
	}

	static boolean solve(String str)
	{
		if (str.compareTo("2020") == 0) return true;
		for (int i = 0; i < str.length( ); i++)
		{
			for (int j = i; j < str.length( ); j++)
			{
				if (str.length( ) - (j - i + 1) != 4) continue;
				String temp = "";
				for (int idx = 0; idx < str.length( ); idx++)
				{
					if (idx < i || idx > j)
					{
						temp = temp + str.charAt(idx);
					}
				}
				if (temp.compareTo("2020") == 0) return true;
			}
		}
		return false;
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		T = Integer.parseInt(st.nextToken( ));
		for (int t = 0; t < T; t++)
		{
			st = new StringTokenizer(br.readLine( ));
			N = Integer.parseInt(st.nextToken( ));
			arr = new int[ N ];
			String input = br.readLine( );
			if (solve(input)) sb.append("YES\n");
			else sb.append("NO\n");
		}
		System.out.println(sb);
	}
}
