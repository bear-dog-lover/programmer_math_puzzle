public class Q25 {

	static final int SIZE = 7;
	static final int SIZE2 = SIZE * 2;

	public static void main (String[] args) {

		System.out.println(solve(new boolean[2][SIZE + 1], 0, 0, new int[SIZE2]));

	}

	static int solve (boolean[][] is_selected, int cnt_select, int last_selected, int[] passed_hole) {
		//System.out.println(cnt_select);
		if (cnt_select == SIZE2) return calc(passed_hole);
		int ret = 0;
		int ls = last_selected < 0 ? 1 : 0;
		for (int i = 1; i <= SIZE; i++) {
			if (is_selected[ls][i]) continue;
			is_selected[ls][i] = true;
			passed_hole[cnt_select] = i;
			ret = Math.max(ret, solve(is_selected, cnt_select + 1, ls == 1 ? i : -i, passed_hole));
			is_selected[ls][i] = false;
		}
		return ret;
	}

	public static int calc (int[] passed_hole) {
		int ret = 0;
		for (int i = 3; i < SIZE2; i++) {
			for (int j = 1; j < i - 1; j++) {

				int ph1 = passed_hole[j];
				int ph2 = passed_hole[j-1];
				//int h1 = ph2, h2 = ph1;
				if (ph2 < ph1) {
					int tmp = ph2;
					ph2 = ph1;
					ph1 = tmp;
				}

				int ph3 = passed_hole[i];
				int ph4 = passed_hole[i-1];
				//int h3 = ph4, h4 = ph3;
				if (ph4 < ph3) {
					int tmp = ph4;
					ph4 = ph3;
					ph3 = tmp;
				}

				//boolean is_cross = false;
				if (!is_out(ph1, ph2, ph3, ph4)) {
					//is_cross = true;
					ret++;
				}

				//System.out.println(h1 + "-" + h2 + " : " + h3 + "-" + h4);
				//System.out.println("\t"+ ph1 + "-" + ph2 + " : " + ph3 + "-" + ph4 + "\t" + is_cross);

			}
		}
		//System.out.println(Arrays.toString(passed_hole) + "\t"+ret);
		return ret;
	}

	public static boolean is_out (int start_a, int end_a, int start_b, int end_b) {
		return end_a <= start_b || end_b <= start_a;
	}

}
