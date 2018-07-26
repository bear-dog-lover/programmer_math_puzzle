

public class Q22 {

	public static void main (String[] args) {

		final int n = 16;
		int[] dp = new int[n + 1];

		dp[0] = dp[2] = 1;
		for (int i = 4; i <= n; i += 2) {
			int sum = 0;;
			for (int j = 0; j <= i - 2; j += 2)
				sum += dp[j] * dp[i - j - 2];
			dp[i] = sum;
			//System.out.println(Arrays.toString(dp));
		}

		System.out.println(dp[n]);

	}

}
