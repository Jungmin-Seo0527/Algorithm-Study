
package DataStructure_Stack;

import java.io.*;
import java.util.*;

// BOJ1874_스택수열
// 1부터 N까지 push를 하다가 arr순서대로 같은수를 만나면 push를 하면 된다.
public class BOJ1874_스택수열
{
	static int N, arr[];

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		solve( );
	}

	static void solve( )
	{
		Stack< Integer > stack = new Stack<>( );
		StringBuilder sb = new StringBuilder( );
		int idx = 0;
		for (int i = 1; i <= N; i++)
		{
			if (idx == N) break;
			if (stack.isEmpty( ) == false && stack.peek( ) == arr[ idx ])
			{
				sb.append("-\n");
				stack.pop( );
				idx++;
				i--;
			}
			else
			{
				stack.push(i);
				sb.append("+\n");
			}
		}
		while (true)
		{
			if (stack.peek( ) == arr[ idx ])
			{
				sb.append("-\n");
				stack.pop( );
			}
			idx++;
			if (idx == N) break;
		}
		if (stack.isEmpty( )) System.out.println(sb);
		else System.out.println("NO");
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		N = Integer.parseInt(st.nextToken( ));
		arr = new int[ N ];
		for (int i = 0; i < N; i++)
		{
			arr[ i ] = Integer.parseInt(br.readLine( ));
		}
	}
}
