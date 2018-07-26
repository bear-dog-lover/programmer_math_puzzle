

public class Q15 {

	public static void main (String[] args) {

		int n = 10;
		long[] dp = new long[n + 1];
		dp[0] =  1;
		dp[1] =  0;
		dp[2] =  1;
		dp[3] =  2;
		dp[4] =  4;
		dp[5] =  8;
		dp[6] = 14;
		dp[7] = 28;
		for(int i=8;i<=n;i++)
			dp[i]=dp[i-2]+2*dp[i-3]+3*dp[i-4]+4*dp[i-5]+3*dp[i-6]+2*dp[i-7]+dp[i-8];

		//System.out.println(Arrays.toString(dp));
		System.out.println(dp[n]);
	}

}
