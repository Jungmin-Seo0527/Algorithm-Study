
package primeNumber;

import java.io.*;
import java.util.*;

// BOJ2960_에라토스테네스의_체
public class BOJ2960_에라토스테네스의_체
{

	public static void main(String[ ] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		int N = Integer.parseInt(st.nextToken( ));
		int K = Integer.parseInt(st.nextToken( ));

		ArrayList< Boolean > primeList = new ArrayList< Boolean >(N + 1);

		primeList.add(false);
		primeList.add(false);

		for (int i = 2; i <= N; i++)
		{
			primeList.add(i, true);
		}

		int cnt = 0;
		for (int i = 2; i <= N; i++)
		{
			if (primeList.get(i) == true)
			{
				for (int j = i; j <= N; j += i)
				{
					if (primeList.get(j) == true)
					{
						primeList.set(j, false);
						cnt++;
						if (cnt == K)
						{
							System.out.println(j);
							return;
						}
					}
				}
			}
		}
	}
}
