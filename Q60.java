public class Q60 {

	static final int N = 5;
	static final int CNT = N * N;

	public static void main (String[] args) {
		long s = System.currentTimeMillis();

		System.out.println(solve(0, new boolean[N][N]));

		long e = System.currentTimeMillis();
		System.out.println("time:" + (e-s) + "ms");
	}

	static int solve (int cnt, boolean[][] map) {
		if (cnt == CNT) return 1;

		int pos_x_start = -1, pos_y_start = -1;
		find_pos_start:
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j]) continue;
				pos_x_start = j;
				pos_y_start = i;
				break find_pos_start;
			}
		}

		if (pos_x_start == -1 || pos_y_start == -1) return 0;

		int ret = 0;
		for (int h = 1; h <= N; h++) {
			next_size:
			for (int w = 1; w <= N; w++) {
				//if (h == 1 && w == 1) continue;
				boolean[][] next = clone(map);
				for (int hi = 0; hi < h; hi++) {
					int ny = pos_y_start + hi;
					if (ny >= N) continue next_size;
					for (int wi = 0; wi < w; wi++) {
						int nx = pos_x_start + wi;
						if (nx >= N || next[ny][nx]) continue next_size;
						next[ny][nx] = true;
					}
				}
				ret += solve(cnt + h * w, next);
			}
		}

		return ret;
	}

	static int[][] copy (int[][] map) {
		int[][] ret = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				ret[i][j] = map[i][j];
			}
		}
		return ret;
	}

	static boolean[][] clone (boolean[][] ary) {
		boolean[][] ret = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				ret[i][j] = ary[i][j];
		}
		return ret;
	}
}
