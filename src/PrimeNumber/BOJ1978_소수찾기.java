package PrimeNumber;

import java.io.*;
import java.util.*;

// BOJ1978_소수찾기
// 에라토스테네스의 체
public class BOJ1978_소수찾기
{

	public static void main(String[ ] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		int N = Integer.parseInt(st.nextToken( ));
		int arr[] = new int[ N ];
		st = new StringTokenizer(br.readLine( ));
		for (int i = 0; i < N; i++)
		{
			int n = Integer.parseInt(st.nextToken( ));
			arr[ i ] = n;
		}
		Arrays.sort(arr);

		ArrayList< Boolean > primeList = new ArrayList< Boolean >(arr[ N - 1 ] + 1);

		primeList.add(false);
		primeList.add(false);

		for (int i = 2; i <= arr[ N - 1 ]; i++)
		{
			primeList.add(i, true);
		}

		for (int i = 2; i * i <= arr[ N - 1 ]; i++)
		{
			if (primeList.get(i) == true)
			{
				for (int j = i * i; j <= arr[ N - 1 ]; j += i)
				{
					primeList.set(j, false);
				}
			}
		}

		int ret = 0;
		for (int i = 0; i < N; i++)
		{
			if (primeList.get(arr[ i ])) ret++;
		}
		System.out.println(ret);
	}
}
