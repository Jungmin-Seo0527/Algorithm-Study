
package Algorithm;

import java.io.*;
import java.util.*;

// Sieve of Eratosthenes
// 에라토스테네스의 체
// n보다 작은 소수 구하기
public class Sieve_of_Eratosthenes
{
	public static void main(String[ ] args) throws IOException
	{
		ArrayList< Boolean > primeList;
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt( );

		if (n <= 1) return;

		primeList = new ArrayList< Boolean >(n + 1);

		// 0과 1을 소수 아님으로 처리
		primeList.add(false);
		primeList.add(false);

		// 2~n까지 소수로 설정
		for (int i = 2; i <= n; i++)
		{
			primeList.add(i, true);
		}

		// 2부터 i*i<=n
		// 각각의 배수들을 지워간다.
		for (int i = 2; i * i <= n; i++)
		{
			if (primeList.get(i))
			{
				for (int j = i * i; j <= n; j += i)
				{
					primeList.set(j, false);
				}
			}
		}

		StringBuilder sb = new StringBuilder( );
		sb.append("{");
		for (int i = 0; i <= n; i++)
		{
			if (primeList.get(i) == true)
			{
				sb.append(i);
				sb.append(", ");
			}
		}
		sb.setCharAt(sb.length( ) - 1, '}');
		System.out.println(sb.toString( ));
	}
}
