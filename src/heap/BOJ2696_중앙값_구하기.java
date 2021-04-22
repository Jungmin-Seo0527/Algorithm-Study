
package heap;

import java.io.*;
import java.util.*;

// BOJ2696_중앙값_구하기
// 가운데를 말해요 문제와 거의 동일
public class BOJ2696_중앙값_구하기
{
	public static void main(String[ ] args) throws IOException
	{
		PriorityQueue< Integer > rightHeap = new PriorityQueue<>((o1, o2) -> o1 - o2); // minheap
		PriorityQueue< Integer > leftHeap = new PriorityQueue<>((o1, o2) -> o2 - o1); // maxheap
		StringBuilder sb = new StringBuilder( );
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		int T = Integer.parseInt(st.nextToken( ));
		for (int t = 0; t < T; t++)
		{
			st = new StringTokenizer(br.readLine( ));
			int n = Integer.parseInt(st.nextToken( ));

			st = new StringTokenizer(br.readLine( ));
			int temp = (n + 1) / 2;
			sb.append(temp + "\n");

			int cnt = 0;
			for (int i = 0; i < n; i++)
			{
				if (i != 0 && (i) % 10 == 0) st = new StringTokenizer(br.readLine( ));
				int input = Integer.parseInt(st.nextToken( ));

				if (i == 0) rightHeap.add(input);
				else if (i % 2 == 1) rightHeap.add(input);
				else leftHeap.add(input);

				if (rightHeap.size( ) > 0 && leftHeap.size( ) > 0)
				{
					if (rightHeap.peek( ) < leftHeap.peek( ))
					{
						int right = rightHeap.poll( );
						int left = leftHeap.poll( );
						rightHeap.add(left);
						leftHeap.add(right);
					}
				}
				if ((i + 1) % 2 == 1)
				{
					sb.append(rightHeap.peek( ) + " ");
					cnt++;
				}
				if (cnt == 10)
				{
					sb.append("\n");
					cnt = 0;
				}
			}
			rightHeap.clear( );
			leftHeap.clear( );
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
	}
}
