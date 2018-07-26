import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * こいつ除かないと
 * ■□□
 * □□■
 * □■□
 * http://www.nikoli.co.jp/ja/puzzles/renkuro/
 * 白はすべて縦横で続いていなきゃね
 * @author hangn
 *
 */

public class Q67 {

	static final int WIDTH  = 6;
	static final int HEIGHT = 5;
	static final int ALL = WIDTH * HEIGHT;
	static int ans = 0;
	static boolean[][] map = new boolean[HEIGHT][WIDTH];

	public static void main (String[] args) {
		long s = System.currentTimeMillis();

		solve(0, 0, 0, false);

		System.out.println(ans);
		long e = System.currentTimeMillis();
		System.out.println("time:" + (e-s) + "ms");
	}

	static void solve (int x, int y, int cnt, boolean is_draw) {

		// 分断チェック
		if (is_draw && is_divided(cnt)) return;

		if (y == HEIGHT) {
			ans++;
			//debug();
			return;
		}

		int nx = x + 1 == WIDTH ? 0 : x + 1;
		int ny = x + 1 == WIDTH ? y + 1 : y;

		solve(nx, ny, cnt, false);

		if (check_cross(x, y)) {
			map[y][x] = true;
			solve(nx, ny, cnt + 1, true);
			map[y][x] = false;
		}
	}

	static boolean is_divided (int cnt_draw) {
		// 開始位置を決める
		int x_start = 0, y_start = 0;
		find_pos_start:
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				if (map[i][j]) continue;
				x_start = j;
				y_start = i;
				break find_pos_start;
			}
		}

		Queue<Integer> pos_x = new LinkedList<>();
		Queue<Integer> pos_y = new LinkedList<>();
		Set<Integer> set = new HashSet<>();
		int[] dx = {0,0,-1,1};
		int[] dy = {-1,1,0,0};

		pos_x.add(x_start);
		pos_y.add(y_start);
		set.add(x_start << 3 | y_start);

		int area = 0;
		while (!pos_x.isEmpty()) {
			int x = pos_x.poll();
			int y = pos_y.poll();

			area++;

			for (int di = 0; di < 4; di++) {
				int nx = x + dx[di];
				int ny = y + dy[di];

				if (!is_in(nx, ny)) continue;
				if (map[ny][nx]) continue;

				int val = nx << 3 | ny;
				if (set.contains(val)) continue;
				set.add(val);

				pos_x.add(nx);
				pos_y.add(ny);
			}
		}

		return area != ALL - cnt_draw;
	}

	static boolean check_cross (int x, int y) {
		// 左上からおいてるからチェックは左か上だけでよい
		int[] dx = {0,-1};
		int[] dy = {-1,0};
		for (int di = 0; di < 2; di++) {
			int nx = x + dx[di];
			int ny = y + dy[di];
			if (!is_in(nx, ny)) continue;
			if (map[ny][nx]) return false;
		}
		return true;
	}

	static boolean is_in (int x, int y) {
		return 0 <= x && x < WIDTH && 0 <= y && y < HEIGHT;
	}

	static void debug () {
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				System.out.print(map[i][j] ? "■": "□");
			}
			System.out.println();
		}
		System.out.println();
	}
}
