

// http://www.geocities.jp/oraclesqlpuzzle/kyoupro/kyoupro-05-023.html
// 本間違ってるよね...

public class Q23 {

	static final int COIN = 10;
	static final int CNT  = 24;
	static long[][] memo  = new long[COIN + CNT + 1][CNT + 1];

	public static void main (String[] args) {

		System.out.println(solve(COIN, CNT));

	}

	static long solve (int m, int n) {
		if (memo[m][n] > 0) return memo[m][n];
		if (n == 1) return m > 1 ? 2 : 1;
		if (m == 0) return 0;
		return memo[m][n] = solve(m - 1, n - 1) + solve(m + 1, n - 1);
	}

}
