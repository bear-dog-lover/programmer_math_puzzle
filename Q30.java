class Q30 {

	static final int N = 20;
	static int[] dp = new int[N + 1];

	public static void main (String[] args) {

		dp[1] = 1;
		System.out.println(set_tap(N));

	}

	public static int set_tap (int n) {
		if (dp[n] > 0) return dp[n];
		int cnt = 0;
		// 2口
		for (int i = 1; i <= n / 2; i++) {
			int sti = set_tap(i);
			if (n - i == i)
				cnt += sti * (sti + 1) / 2;
			else
				cnt += set_tap(n - i) * sti;
		}
		// 3口
		for (int i = 1; i <= n / 3; i++) {
			for (int j = i; j <= (n - i) / 2; j++) {
				int sti = set_tap(i);
				if ((n - (i + j) == i) && (i == j))
					cnt += sti * (sti + 1) * (sti + 2) / 6;
				else if (i == j)
					cnt += set_tap(n - (i+j)) * sti * (sti+1) / 2;
				else if (n - (i + j) == j)
					cnt += set_tap(j) * (set_tap(j) + 1) * sti / 2;
				else
					cnt += set_tap(n - (i + j)) * set_tap(j) * sti;
			}
		}
		return (dp[n] = cnt);
	}

}