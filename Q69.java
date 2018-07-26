public class Q69 {

	static final int HEIGHT = 5;
	static final int WIDTH  = 6;
	static final int LIM = HEIGHT * WIDTH / 2;
	static int[][] charis = new int[HEIGHT][WIDTH];
	static int[] dx = { 0, 0, 1,-1};
	static int[] dy = {-1, 1, 0, 0};

	public static void main (String[] args) {
		long s = System.currentTimeMillis();

		System.out.println(solve(0, 0));

		long e = System.currentTimeMillis();
		System.out.println("time:" + (e-s) + "ms");
	}

	static int solve (int cnt_male, int cnt_female) {
		if (cnt_male > LIM || cnt_female > LIM) return 0;

		if (cnt_male == LIM && cnt_female == LIM) return 1;

		for (int y = 0; y < HEIGHT; y++) {
			for (int x = 0; x < WIDTH; x++) {
				// 誰か座ってる
				if (charis[y][x] != 0) continue;

				int ret = 0;

				charis[y][x] = -1;
				if (check(x, y, -1)) ret += solve(cnt_male, cnt_female + 1);

				charis[y][x] = 1;
				if (check(x, y,  1)) ret += solve(cnt_male + 1, cnt_female);

				charis[y][x] = 0;

				return ret;
			}
		}
		return 0;
	}

	static boolean check (int x, int y, int sex) {
		if (is_exist_cross(x, y, sex)) return false;
		for (int di = 0; di < 4; di++) {
			int nx = x + dx[di];
			int ny = y + dy[di];
			if (!is_in(nx, ny)) continue;
			if (is_exist_cross(nx, ny, charis[ny][nx])) return false;
		}
		return true;
	}

	static boolean is_exist_cross (int x, int y, int sex) {
		if (charis[y][x] == 0) return false;
		for (int di = 0; di < 4; di++) {
			int nx = x + dx[di];
			int ny = y + dy[di];
			if (!is_in(nx, ny)) continue;
			if (charis[ny][nx] != sex) return false;
		}
		return true;
	}

	static boolean is_in (int x, int y) {
		return 0 <= x && x < WIDTH && 0 <= y && y < HEIGHT;
	}

}
