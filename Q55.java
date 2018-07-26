public class Q55 {

	static int min = Integer.MAX_VALUE;
	static String exp = "";
	static boolean[] is_used = new boolean[11];

	public static void main (String[] args) {
		long s = System.currentTimeMillis();

		solve(0, 0, 0, "");

		System.out.println(exp);
		System.out.println(min);

		long e = System.currentTimeMillis();
		System.out.println("time:" + (e-s) + "ms");
	}

	static void solve (int cnt_selected, int cnt_movoed, int sum, String path) {
		if (cnt_selected == 10) {
			if (cnt_movoed < min) {
				exp = path;
				min = cnt_movoed;
			}
			return;
		}

		for (int i = 1; i <= 10; i++) {
			if (is_used[i]) continue;
			is_used[i] = true;

			solve(cnt_selected + 1, cnt_movoed + calc(sum, i), sum + i, path.equals("") ? i+"" : path+"+"+i);

			is_used[i] = false;
		}
	}

	static int calc (int bef, int add) {
		int aft = bef + add;
		int pos10_top = Math.abs(bef / 10 / 5 - aft / 10 / 5);
		int pos10_btm = Math.abs(bef / 10 % 5 - aft / 10 % 5);
		int pos01_top = Math.abs(bef % 10 / 5 - aft % 10 / 5);
		int pos01_btm = Math.abs(bef % 5 - aft % 5);
		return pos10_top + pos10_btm + pos01_top + pos01_btm;
	}
}
