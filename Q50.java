import java.util.HashSet;
import java.util.Set;

public class Q50 {

	static final int HEIGHT = 5;
	static final int WIDTH  = 6;
	static Set<String> through_root = new HashSet<>();

	// 上右下左
	static int[] dx = { 0, 1, 0,-1};
	static int[] dy = {-1, 0, 1, 0};

	public static void main (String[] args) {
		int[] cnt_row = new int[HEIGHT + 1];
		int[] cnt_col = new int[WIDTH  + 1];
		System.out.println(solve(0, 0, 0, cnt_row, cnt_col));
	}

	static int solve (int x, int y, int step, int[] cnt_row, int[] cnt_col) {
		if (x == WIDTH && y == HEIGHT) return step;

		int ret = 0;
		for (int di = 0; di < 4; di++) {
			int x_next = x + dx[di];
			int y_next = y + dy[di];

			// はみ出していないか
			if (x_next < 0 || x_next > WIDTH || y_next < 0 || y_next > HEIGHT)
				continue;

			int[] cnt_row_next = cnt_row.clone();
			int[] cnt_col_next = cnt_col.clone();

			// 上
			if (di == 0) {
				if (cnt_col_next[x] == 2) continue;
				cnt_col_next[x]++;
			// 右
			} else if (di == 1) {
				if (cnt_row_next[y] == 2) continue;
				cnt_row_next[y]++;
			// 下
			} else if (di == 2) {
				if (cnt_col_next[x] == 2) continue;
				cnt_col_next[x]++;
			// 左
			} else {
				if (cnt_row_next[y] == 2) continue;
				cnt_row_next[y]++;
			}

			ret = Math.max(ret, solve(x_next, y_next, step + 1, cnt_row_next, cnt_col_next));
		}

		return ret;
	}
}
