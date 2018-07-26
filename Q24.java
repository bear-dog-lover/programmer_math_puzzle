

public class Q24 {

	static int SIZE;

	public static void main (String[] args) {

		String[] str = {"1","1-2","1-4","2","2-3","3","3-6","4","4-7","5","6","6-9","7","7-8","8","8-9","9"};
		SIZE = str.length;
		System.out.println(solve(str, new boolean[str.length], 0));

	}

	static int solve (String[] str, boolean[] is_used, int cnt) {
		if (cnt == 9) return 1;
		int ret = 0;
		for (int i = 0; i < SIZE; i++) {
			if (is_used[i]) continue;
			boolean[] next = copy(is_used);
			defeat(next, str, str[i]);
			ret += solve(str, next, cnt + (str[i].length() == 3 ? 2 : 1));
		}
		return ret;
	}

	static boolean[] copy (boolean[] ary) {
		boolean[] ret = new boolean[ary.length];
		for (int i = 0; i < ary.length; i++)
			ret[i] = ary[i];
		return ret;
	}

	static void defeat (boolean[] flg, String[] target, String str) {
		String[] spl = str.split("-");
		for (int i = 0; i < spl.length; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (flg[j]) continue;
				if (target[j].indexOf(spl[i]) != -1) flg[j] = true;
			}
		}
	}

}
