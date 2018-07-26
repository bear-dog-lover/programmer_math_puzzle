/**
 * time:16122ms
 * ↓分断チェック(is_maze)させると・・・
 * time:7038ms
 */

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Q64 {

	static final int SIZE = 5;
	static final int CNT_MIN = 2 * SIZE - 1;
	static boolean[][] map = new boolean[SIZE + 2][SIZE + 2];
	static int ans = 0;

	public static void main (String[] args) {
		long s = System.currentTimeMillis();

		map[1][1] = map[SIZE][SIZE] = true;
		solve(1, 1, 2, true);

		System.out.println(ans);
		long e = System.currentTimeMillis();
		System.out.println("time:" + (e-s) + "ms");
	}

	static void solve (int x, int y, int cnt_white, boolean is_changed) {

		if (x == SIZE && y == SIZE) return;

		if (is_changed && cnt_white >= CNT_MIN && is_maze() && is_meet()) ans++;

		int nx = x + 1 > SIZE ? 1 : x + 1;
		int ny = x + 1 > SIZE ? y + 1 : y;

		solve(nx, ny, cnt_white, false);

		if (nx != SIZE || ny != SIZE) {
			map[ny][nx] = true;
			solve(nx, ny, cnt_white + 1, true);
			map[ny][nx] = false;
		}
	}

	// 分断チェック
	static boolean is_maze () {
		Queue<Integer> q_x = new LinkedList<>();
		Queue<Integer> q_y = new LinkedList<>();
		Set<Integer> memo = new HashSet<>();
		int[] dx = {0,0,-1,1};
		int[] dy = {-1,1,0,0};

		q_x.add(1);
		q_y.add(1);
		memo.add(1 << 3 | 1);
		while (!q_x.isEmpty()) {
			int x = q_x.poll();
			int y = q_y.poll();
			if (x == SIZE && y == SIZE) return true;

			for (int di = 0; di < 4; di++) {
				int nx = x + dx[di];
				int ny = y + dy[di];
				if (!map[ny][nx]) continue;
				if (!is_in(nx, ny)) continue;
				int m = nx << 3 | ny;
				if (memo.contains(m)) continue;

				memo.add(m);
				q_x.add(nx);
				q_y.add(ny);
			}
		}
		return false;
	}

	static boolean is_in (int x, int y) {
		return 1 <= x && x <= SIZE && 1 <= y && y <= SIZE;
	}

	static boolean is_meet () {
		// (0,0)で下向き
		int[] pos_a = new int[] {1, 1, 0, 0, 1};
		// (4,4)で上向き
		int[] pos_b = new int[] {SIZE, SIZE, 2, SIZE + 1, SIZE};
		Set<Integer> set = new HashSet<>();

		while (true) {
			int pos_x_a = pos_a[0];
			int pos_y_a = pos_a[1];
			int dist_a =  pos_a[2];
			int pos_x_b = pos_b[0];
			int pos_y_b = pos_b[1];
			int dist_b =  pos_b[2];

			int val = pos_x_a << 15 | pos_y_a << 12 | dist_a << 9 | pos_x_b << 6 | pos_y_b << 3 | dist_b;
			if (set.contains(val)) return false;
			set.add(val);

			if (pos_x_a == pos_x_b && pos_y_a == pos_y_b)
				return true;
			else if ((pos_x_a == SIZE && pos_y_a == SIZE) || (pos_x_b == 1 && pos_y_b == 1))
				return false;

			// AとBを１歩進める
			if (!next(pos_a, true))  return false;
			if (!next(pos_b, false)) return false;
		}
	}

	static boolean next (int[] pos, boolean is_a) {
		int x   = pos[0];
		int y   = pos[1];
		int di  = pos[2];
		int pos_x_wall = pos[3];
		int pos_y_wall = pos[4];
		// ↓←↑→
		int[] dx = {0,-1,0,1};
		int[] dy = {1,0,-1,0};
		int loop = 0;

		// 1ループで方向あるいは位置が変わる
		while (x == pos[0] && y == pos[1]) {
			if (loop == 4) return false;
			loop++;
			// 次のマス
			int nx = x + dx[di];
			int ny = y + dy[di];
			if (map[ny][nx]) {
				// 現在地の更新
				x = nx;
				y = ny;
				// 手をつく壁を更新
				int di_next = (di + 1) % 4;
				// 進んだ先の右側が壁でない
				if (map[y + dy[di_next]][x + dx[di_next]]) {
					// 方向を更新
					di = di_next;
				}
			} else {
				// 手をつく壁を更新
				pos_x_wall = nx;
				pos_y_wall = ny;
				// 方向の更新
				if (--di < 0) di += 4;
			}
		}
		pos[0] = x;
		pos[1] = y;
		pos[2] = di;
		pos[3] = pos_x_wall;
		pos[4] = pos_y_wall;
		return true;
	}

	static void debug () {
		for (int i = 1; i <= SIZE; i++) {
			for (int j = 1; j <= SIZE; j++) {
				System.out.print(map[i][j] ? "□" : "■");
			}
			System.out.println();
		}
		System.out.println();
	}
}
