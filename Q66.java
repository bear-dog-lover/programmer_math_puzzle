public class Q66 {

	static final int HEIGHT = 3;
	static final int WIDTH  = 4;;
	static int[][] degree = new int[HEIGHT + 1][WIDTH + 1];

	static {
		for (int i = 1; i < HEIGHT; i++) {
			for (int j = 1; j < WIDTH; j++)
				degree[i][j] = 4;
		}
		// 外枠(横)
		for (int i = 1; i < WIDTH; i++) {
			degree[0][i] = 3;
			degree[HEIGHT][i] = 3;
		}
		// 外枠(縦)
		for (int i = 1; i < HEIGHT; i++) {
			degree[i][0] = 3;
			degree[i][WIDTH] = 3;
		}
		// 四隅の分
		degree[0][0] = degree[0][WIDTH] = degree[HEIGHT][0] = degree[HEIGHT][WIDTH] = 2;
	}

	public static void main (String[] args) {
		long s = System.currentTimeMillis();

		System.out.println(solve(0, 0, 0, false));

		long e = System.currentTimeMillis();
		System.out.println("time:" + (e-s) + "ms");
	}

	static int solve (int h, int w, int cnt_odd, boolean is_next_row) {

		if (h == HEIGHT) {
			cnt_odd += cnt_odd(h - 1) + cnt_odd(h);
			return cnt_odd == 0 || cnt_odd == 2 ? 1 : 0;
		}

		if (is_next_row) {
			cnt_odd += cnt_odd(h - 1);
			if (cnt_odd > 2) return 0;
		}

		int ret = 0;
		boolean flg = w + 1 == WIDTH;
		int nh = flg ? h + 1 : h;
		int nw = flg ? 0 : w + 1;

		// パターン1
		ret += solve(nh, nw, cnt_odd, flg);

		// パターン2
		degree[h][w]++;
		degree[h + 1][w + 1]++;
		ret += solve(nh, nw, cnt_odd, flg);
		degree[h][w]--;
		degree[h + 1][w + 1]--;

		// パターン3
		degree[h + 1][w]++;
		degree[h][w + 1]++;
		ret += solve(nh, nw, cnt_odd, flg);
		degree[h + 1][w]--;
		degree[h][w + 1]--;

		// パターン4
		degree[h][w]++;
		degree[h + 1][w]++;
		degree[h][w + 1]++;
		degree[h + 1][w + 1]++;
		ret += solve(nh, nw, cnt_odd, flg);
		degree[h][w]--;
		degree[h + 1][w]--;
		degree[h][w + 1]--;
		degree[h + 1][w + 1]--;

		return ret;
	}

	static int cnt_odd (int row) {
		int ret = 0;
		for (int i = 0; i <= WIDTH; i++)
			if (degree[row][i] % 2 == 1) ret++;
		return ret;
	}
}
