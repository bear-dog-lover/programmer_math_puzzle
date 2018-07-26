import java.util.Arrays;

public class Q28 {

	public static void main (String[] args) {

		final int MAX = 150;
		int[] wide   = {110,80, 4, 8, 9,18,10,70, 1, 3};
		int[] member = { 40,30,24,20,14,16,15,40,10,12};
		int[] dp = new int[MAX + 1];

		Arrays.fill(dp, -1);
		dp[0] = 0;

		for (int wi = 0; wi < wide.length; wi++) {
			int w = wide[wi];
			int m = member[wi];
			for (int di = MAX - m; di >= 0; di--) {
				if (dp[di] == -1) continue;
				dp[di + m] = Math.max(dp[di + m], dp[di] + w);
			}
		}

		int ans = 0;
		for (int i = 0; i <= MAX; i++)
			ans = Math.max(ans, dp[i]);

		System.out.println(ans * 100 + "m^2");

	}
}
