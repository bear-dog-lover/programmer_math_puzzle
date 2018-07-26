public class Q54 {
	static final int TARGET = 11;
	static final int SIZE = TARGET * 2;
	static int[] cards = new int[SIZE];
	static boolean[] is_used = new boolean[TARGET + 1];

	public static void main (String[] args) {
		long s = System.currentTimeMillis();

		System.out.println(dfs(0, 0));

		long e = System.currentTimeMillis();
		System.out.println("time:" + (e-s) + "ms");
	}

	public static int dfs (int i, int cnt) {
		if (cnt == TARGET) return 1;

		if (i >= SIZE) return 0;

		if (cards[i] > 0) return dfs(i + 1, cnt);

		int ret = 0;
		for (int n = 1; n <= TARGET; n++) {
			if (is_used[n]) continue;
			if (i + n + 1 >= SIZE || cards[i + n + 1] > 0) continue;

			is_used[n] = true;
			cards[i] = n;
			cards[i + n + 1] = n;

			ret += dfs(i + 1, cnt + 1);

			is_used[n] = false;
			cards[i] = 0;
			cards[i + n + 1] = 0;
		}

		return ret;
	}
}
