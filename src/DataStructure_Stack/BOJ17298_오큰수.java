
package DataStructure_Stack;

import java.io.*;
import java.util.*;

// BOJ17298_오큰수
// 나보다 오른쪽에 있는 수들중 큰수들중 가장 왼쪽에 있는 수 구하기
// stack
public class BOJ17298_오큰수
{
	static int arr[][], N;

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		solve( );
	}

	// stack이 비어있으면 현제 인덱스를  push
	// else top의 인덱스와 현제 인덱스를 비교
	// 현제 인덱스가 top의 인덱스보다 크면 top의 인덱스의 오큰수는 현제 인덱스의 숫자
	// 현제 인덱스가 top의 인덱스보다 작을때까지 push(push된 인덱스들의 오큰수는 현제 인덱스)
	// else 현제 인덱스를 push
	static void solve( )
	{
		Deque< Integer > stack = new LinkedList<>( );
		stack.addFirst(0);
		for (int i = 0; i < N; i++)
		{
			while (true)
			{
				if (stack.isEmpty( ) == true)
				{
					stack.addFirst(i);
					continue;
				}
				int top = stack.peekFirst( );
				if (arr[ top ][ 0 ] < arr[ i ][ 0 ])
				{
					arr[ top ][ 1 ] = arr[ i ][ 0 ];
					stack.pollFirst( );
				}
				else
				{
					stack.addFirst(i);
					break;
				}
			}
		}
		StringBuilder sb = new StringBuilder( );
		for (int i = 0; i < N; i++)
		{
			sb.append(arr[ i ][ 1 ] + " ");
		}
		System.out.println(sb);
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		N = Integer.parseInt(st.nextToken( ));
		st = new StringTokenizer(br.readLine( ));
		arr = new int[ N ][ 2 ];
		for (int i = 0; i < N; i++)
		{
			arr[ i ][ 0 ] = Integer.parseInt(st.nextToken( ));
			arr[ i ][ 1 ] = -1;
		}
	}
}
