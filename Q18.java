

import static util.util_math.*;

public class Q18 {

	public static void main (String[] args) {

		for (int n = 2; ; n++) {
			boolean[] is_used = new boolean[n + 1];
			is_used[1] = true;
			if (solve(n, 1, 1, is_used)) {
				System.out.println(n);
				break;
			}
		}

	}

	public static boolean solve (int n, int last_selected, int cnt_used, boolean[] is_used) {
		if (cnt_used == n) return is_sqrt(last_selected + 1);
		for (int i = 2; i <= n; i++) {
			if (is_used[i]) continue;
			if (!is_sqrt(last_selected + i)) continue;
			is_used[i] = true;
			if (solve(n, i, cnt_used + 1, is_used)) return true;
			is_used[i] = false;
		}
		return false;
	}

}
