

public class Q05 {
	static final int TARGET = 1000;
	static int[] coins = {10,50,100,500};
	public static void main (String[] args) {
		System.out.println(solve(0, 0, 0));
	}
	public static int solve (int ci, int cnt, int sum) {
		if (ci >= coins.length) return 0;
		if (cnt > 15) return 0;
		if (sum == TARGET) return 1;
		return solve(ci, cnt + 1, sum + coins[ci]) + solve(ci + 1, cnt, sum);
	}
}
