

public class Q17 {

	public static void main (String[] args) {

		final int TARGET = 30;
		long[] dp = new long[TARGET + 1];
		dp[0] = 1;
		dp[1] = 2;

		for (int i = 2; i <= TARGET; i++)
			dp[i] = dp[i-1] + dp[i-2];

		System.out.println(dp[TARGET]);

	}

}
