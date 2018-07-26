import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Q61 {

	static final int HEIGHT = 4;
	static final int WIDTH  = 5;
	static final int LEN  = HEIGHT * WIDTH;
	static final int HALF = LEN / 2;
	static final int LIM = (int)Math.pow(2, LEN);
	static int[] dx = { 0, 0, -1, 1};
	static int[] dy = {-1, 1,  0, 0};

	public static void main (String[] args) {
		long s = System.currentTimeMillis();

		int ans = 0;
		for (int i = 0; i < LIM; i++) {
			if (Integer.bitCount(i) != HALF) continue;
			boolean[][] map = convert(padding_head("0", Integer.toBinaryString(i), LEN));
			// trueエリア、falseエリア共に島ができているか
			if (check(map, true) && check(map, false)) ans++;
		}

		// 色が逆のパターンは１つとカウント
		System.out.println(ans / 2);
		long e = System.currentTimeMillis();
		System.out.println("time:" + (e-s) + "ms");
	}

	static String padding_head (String pad, String str, int len) {
		if (str.length() == len) return str;
		StringBuilder sb = new StringBuilder(str);
		while (sb.length() < len)
			sb.insert(0, pad);
		return sb.toString();
	}

	static boolean[][] convert (String bs) {
		boolean[][] ret = new boolean[HEIGHT][WIDTH];
		int i_bs = 0;
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				char c = bs.charAt(i_bs++);
				ret[i][j] = c == '1';
			}
		}
		return ret;
	}

	static boolean check (boolean[][] map, final boolean target) {
		int x_start = 0, y_start = 0;
		find_pos_start:
		for (int yi = 0; yi < HEIGHT; yi++) {
			for (int xi = 0; xi < WIDTH; xi++) {
				if (map[yi][xi] != target) continue;
				x_start = xi;
				y_start = yi;
				break find_pos_start;
			}
		}

		// 開始位置から広がる島の面積
		int area = 0;
		Queue<Integer> q_x   = new LinkedList<>();
		Queue<Integer> q_y   = new LinkedList<>();
		Set<Integer> memo = new HashSet<>();

		q_x.add(x_start);
		q_y.add(y_start);
		memo.add(x_start << 3 | y_start);

		while (!q_x.isEmpty()) {
			int x = q_x.poll();
			int y = q_y.poll();

			area++;

			for (int di = 0; di < 4; di++) {
				int nx = x + dx[di];
				int ny = y + dy[di];
				// マップ外でないか
				if (nx < 0 || nx >= WIDTH || ny < 0 || ny >= HEIGHT) continue;
				// 同じエリアでない
				if (map[ny][nx] != target) continue;
				// 一度訪れていないか
				int val_memo = nx << 3 | ny;
				if (memo.contains(val_memo))
					continue;
				else
					memo.add(val_memo);

				q_x.add(nx);
				q_y.add(ny);
			}
		}
		return area == HALF;
	}
}
