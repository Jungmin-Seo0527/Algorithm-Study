
package binarySearch;

import java.io.*;
import java.util.*;

// BOJ1764_듣보잡
public class BOJ1764_듣보잡
{
	static HashSet< String > hash = new HashSet<>( );
	static StringBuilder sb = new StringBuilder( );

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		int N = Integer.parseInt(st.nextToken( ));
		int M = Integer.parseInt(st.nextToken( ));
		for (int i = 0; i < N; i++)
		{
			hash.add(br.readLine( ));
		}
		String[ ] arr = new String[ M ];
		for (int i = 0; i < M; i++)
		{
			String temp = br.readLine( );
			arr[ i ] = new String(temp);
		}
		Arrays.sort(arr);
		int ret = 0;
		for (int i = 0; i < M; i++)
		{
			if (hash.contains(arr[ i ]))
			{
				ret++;
				sb.append(arr[ i ] + "\n");
			}
		}
		System.out.println(ret);
		System.out.println(sb);
	}
}
