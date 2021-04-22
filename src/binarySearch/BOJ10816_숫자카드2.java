
package binarySearch;

import java.io.*;
import java.util.*;

// BOJ10816_숫자카드2
public class BOJ10816_숫자카드2
{
	static final int PLUS = 10000000;
	static int arr[] = new int[ PLUS * 2 + 1 ];
	static StringBuilder sb = new StringBuilder( );

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		int n = Integer.parseInt(st.nextToken( ));
		st = new StringTokenizer(br.readLine( ));
		for (int i = 0; i < n; i++)
		{
			int temp = Integer.parseInt(st.nextToken( ));
			arr[ temp + PLUS ]++;
		}
		st = new StringTokenizer(br.readLine( ));
		n = Integer.parseInt(st.nextToken( ));
		st = new StringTokenizer(br.readLine( ));
		for (int i = 0; i < n; i++)
		{
			int temp = Integer.parseInt(st.nextToken( ));
			sb.append(arr[ temp + PLUS ] + " ");
		}
		System.out.println(sb);
	}
}
