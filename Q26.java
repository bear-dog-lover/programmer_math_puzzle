

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Q26 {

	public static void main (String[] args) {

		final int SIZE_Y = 10;
		final int SIZE_X = 10;

		// KEY:盤面を表す8桁の数字([黒のy座標]+[黒のx座標]+[空のy座標]+[空のx座標]), VALUE:その盤面にたどり着くまでの最短経路
		Map<Integer, Integer> fast_path = new HashMap<>();

		Queue<Integer> pos   = new LinkedList<>();
		Queue<Integer> paths = new LinkedList<>();

		// 初期状態を登録
		pos.add((SIZE_Y - 1) * 100 + SIZE_X - 1);
		paths.add(0);

		// 上下左右
		int[] dx = { 0, 0, -1, 1};
		int[] dy = {-1, 1,  0, 0};

		while (!pos.isEmpty()) {
			int q = pos.poll();
			int pos_y_blk = fetch_pos_y_black(q);
			int pos_x_blk = fetch_pos_x_black(q);
			int pos_y_emp = fetch_pos_y_empty(q);
			int pos_x_emp = fetch_pos_x_empty(q);

			int path = paths.poll();

			// 右下にいるか
			if (pos_y_blk == SIZE_Y - 1 && pos_x_blk == SIZE_X - 1) {
				System.out.println(path);
				break;
			}

			// 最短経路であるか
			if (fast_path.containsKey(q)) {
				int f = fast_path.get(q);
				if (path >= f) continue;
			} else {
				fast_path.put(q, path);
			}

			// 空が4方向に動く
			for (int di = 0; di < 4; di++) {
				int next_pos_y_emp = pos_y_emp + dy[di];
				int next_pos_x_emp = pos_x_emp + dx[di];
				// 領域外判定
				if (next_pos_y_emp < 0 || next_pos_y_emp >= SIZE_Y ||
						next_pos_x_emp < 0 || next_pos_x_emp >= SIZE_X) continue;
				// 空と黒の交換になるか
				int next_pos_y_blk = pos_y_blk;
				int next_pos_x_blk = pos_x_blk;
				if (pos_y_blk == next_pos_y_emp && pos_x_blk == next_pos_x_emp) {
					next_pos_y_blk = pos_y_emp;
					next_pos_x_blk = pos_x_emp;
				}
				// 次
				pos.add(next_pos_y_blk * 1000000 + next_pos_x_blk * 10000 + next_pos_y_emp * 100 + next_pos_x_emp);
				paths.add(path + 1);
			}

		}

	}

	static int fetch_pos_y_black (int n) {
		return n / 1000000;
	}

	static int fetch_pos_x_black (int n) {
		return n % 1000000 / 10000;
	}

	static int fetch_pos_y_empty (int n) {
		return n % 10000 / 100;
	}

	static int fetch_pos_x_empty (int n) {
		return n % 10000 % 100;
	}

}
