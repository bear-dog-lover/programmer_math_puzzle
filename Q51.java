public class Q51 {
	public static void main (String[] args) {
		int ans = 0;
		for (int n = 1; n <= 100; n++)
			if (solve(n)) ans++;
		System.out.println(ans);
	}

	static boolean solve (int n) {
		final int n2 = n * 2;
		int[] cards = new int[n2];
		for (int i = 0; i < n2; i++)
			cards[i] = i + 1;

		int cnt = 1;
		for (; ; cnt++) {
			if (cnt > 2 * (n - 1)) break;
			cards = next(cards, n, n2);
			if (is_finished(cards, n2)) break;
		}
		return 2 * (n - 1) % cnt == 0;
	}

	// 半分に分けて左から順に取り出す
	static int[] next (int[] cards, int n, int n2) {
		int[] ret = new int[n2];
		for (int i = 0, j = 0; i < n2; i += 2, j++) {
			ret[i] = cards[j];
			ret[i + 1] = cards[n + j];
		}
		return ret;
	}

	static boolean is_finished (int[] cards, int n2) {
		for (int i = 0; i < n2; i++)
			if (cards[i] != i + 1) return false;
		return true;
	}
}
