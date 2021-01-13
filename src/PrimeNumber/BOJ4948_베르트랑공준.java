
package PrimeNumber;

import java.io.*;
import java.util.*;

// BOJ4948_베르트랑공준
public class BOJ4948_베르트랑공준
{
	public static void main(String[ ] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int SZ = 123456;
		ArrayList< Boolean > primeList = new ArrayList< Boolean >(2 * SZ + 1);
		primeList.add(false);
		primeList.add(false);

		for (int i = 2; i <= 2 * SZ + 1; i++)
		{
			primeList.add(i, true);
		}

		for (int i = 2; i * i <= 2 * SZ + 1; i++)
		{
			if (primeList.get(i) == true)
			{
				for (int j = i * i; j <= 2 * SZ + 1; j += i)
				{
					primeList.set(j, false);
				}
			}
		}
		StringBuilder sb = new StringBuilder( );
		while (true)
		{
			st = new StringTokenizer(br.readLine( ));
			int n = Integer.parseInt(st.nextToken( ));
			if (n == 0) break;
			int cnt = 0;
			for (int i = n + 1; i <= 2 * n; i++)
			{
				if (primeList.get(i) == true)
				{
					cnt++;
				}
			}
			sb.append(cnt + "\n");
		}
		System.out.println(sb);
	}
}
