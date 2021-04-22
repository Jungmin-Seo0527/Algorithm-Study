
package heap;

import java.io.*;
import java.util.*;

// BOJ1655_가운데를_말해요
// 중앙값 구하기 -> 두개의 힙(min, max)을 이용해서 구하기
public class BOJ1655_가운데를_말해요
{
	static int N;

	public static void main(String[ ] args) throws IOException
	{
		PriorityQueue< Integer > rightHeap = new PriorityQueue<>((o1, o2) -> o1 - o2); // minHeap
		PriorityQueue< Integer > leftHeap = new PriorityQueue<>((o1, o2) -> o2 - o1); // maxHeap
		StringBuilder sb = new StringBuilder( );

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		int N = Integer.parseInt(st.nextToken( ));
		for (int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine( ));
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
			sb.append(rightHeap.peek( ) + "\n");
		}
		System.out.println(sb);
	}
}
