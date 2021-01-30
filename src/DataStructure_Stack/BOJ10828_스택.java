
package DataStructure_Stack;

import java.io.*;
import java.util.*;

// BOJ10828_스택
// 그냥 시키는대로 하면 됨
public class BOJ10828_스택
{
	static int N;

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		StringBuilder sb = new StringBuilder( );
		Stack< Integer > stack = new Stack<>( );

		N = Integer.parseInt(st.nextToken( ));
		for (int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine( ));
			String command = st.nextToken( );
			switch (command)
			{
			case "push" :
				int n = Integer.parseInt(st.nextToken( ));
				stack.add(n);
				break;
			case "pop" :
				if (stack.size( ) == 0) sb.append(-1 + "\n");
				else sb.append(stack.pop( ) + "\n");

				break;
			case "size" :
				sb.append(stack.size( ) + "\n");
				break;
			case "empty" :
				if (stack.isEmpty( )) sb.append("1\n");
				else sb.append("0\n");
				break;
			case "top" :
				if (stack.isEmpty( )) sb.append(-1 + "\n");
				else sb.append(stack.peek( ) + "\n");
				break;

			default :
				break;
			}
		}
		System.out.println(sb);
	}
}
