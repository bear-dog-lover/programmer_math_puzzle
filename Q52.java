import java.util.HashSet;
import java.util.Set;

public class Q52 {

	static final int N = 8;
	static String END = "";

	static {
		for (int i = 0; i < N; i++)
			END += "1";
	}

	public static void main (String[] args) {
		long s = System.currentTimeMillis();

		int ans = 0;
		for (int i = 12345678; i <= 87654321; i++) {
			String si = i + "";
			if (si.indexOf('0') != -1) continue;
			if (si.indexOf('9') != -1) continue;
			if (is_exist_duplicate_digit(si)) continue;

			if (solve(si)) ans++;
		}

		long e = System.currentTimeMillis();
		System.out.println(ans);
		System.out.println("time:" + (e-s) + "ms");
	}

	// 重複桁のチェック
	static boolean is_exist_duplicate_digit (String str) {
		int[] seq = new int[10];
		char[] c = str.toCharArray();
		for (int i = 0; i < N; i++)
			if (++seq[c[i] - '0'] > 1) return true;
		return false;
	}

	// 頭からひっくり返していく
	static boolean solve (String str) {
		boolean ret = false;

		final int[] timers = convert(str);
		int[] timers_tmp = timers.clone();
		Set<String> set = new HashSet<>();

		int pos_reverse = 0;
		while (true) {

			String str_timers = convert(timers_tmp) + pos_reverse;

			if (str_timers.startsWith(END)) {
				ret = true;
				break;
			}

			if (set.contains(str_timers)) {
				ret = false;
				break;
			} else
				set.add(str_timers);

			// 1分経過
			for (int i = 0; i < N; i++) {
				if (timers_tmp[i] == 0) continue;
				timers_tmp[i]--;
			}

			// ひっくり返す
			for (int i = pos_reverse; i < pos_reverse + timers[pos_reverse]; i++) {
				int ti = i % N;
				timers_tmp[ti] = timers[ti] - timers_tmp[ti];
			}

			if (++pos_reverse == N) pos_reverse = 0;
		}

		return ret;
	}

	static int[] convert (String str) {
		int[] ret = new int[N];
		for (int i = 0; i < N; i++)
			ret[i] = str.charAt(i) - '0';
		return ret;
	}

	static String convert (int[] timers) {
		StringBuilder ret = new StringBuilder();
		for (int t : timers) ret.append(t);
		return ret.toString();
	}
}
