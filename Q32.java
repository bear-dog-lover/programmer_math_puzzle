import java.util.Arrays;

/**
 * @author hangn
 *
 * 引いた畳に一意な番号を振る
 * 四近傍に異なる4つの番号が存在すれば十字ができていることに他ならない
 */

public class Q32 {

	static final int HEIGHT = 5;
	static final int WIDTH = 6;
	static int[][] room = new int[HEIGHT][WIDTH];
	static final int CNT_TATAMI = HEIGHT * WIDTH / 2;

	public static void main (String[] args) {
		solve(0, 0, 0);
	}

	static void solve (int cnt, int x, int y) {
		//System.out.println(cnt);
		// 十字ができていたら戻る
		if (is_exist_cross()) return;

		// 全部敷けた
		if (cnt == CNT_TATAMI) {
			disp();
			System.exit(0);
		}

		int ix = x, iy = y;
		while (ix < WIDTH && iy < HEIGHT) {
			//System.out.println(cnt + "," + ix + "," + iy + "," + room[iy][ix]);
			// 畳が敷かれていない
			if (room[iy][ix] == 0) {
				// 横に敷けるか
				if (ix + 1 < WIDTH && room[iy][ix + 1] == 0) {
					room[iy][ix] = cnt + 1;
					room[iy][ix + 1] = cnt + 1;
					solve(cnt + 1, ix, iy);
					room[iy][ix] = 0;
					room[iy][ix + 1] = 0;
				}
				// 縦に敷けるか
				if (iy + 1 < HEIGHT && room[iy + 1][ix] == 0) {
					room[iy][ix] = cnt + 1;
					room[iy + 1][ix] = cnt + 1;
					solve(cnt + 1, ix, iy);
					room[iy][ix] = 0;
					room[iy + 1][ix] = 0;
				}
			}
			if (++ix == WIDTH) {
				ix = 0;
				iy++;
			}
		}
	}

	static boolean is_exist_cross () {
		for (int y = 0; y < HEIGHT - 1; y++) {
			for (int x = 0; x < WIDTH - 1; x++) {
				if (room[y][x] == 0) continue;
				if (room[y][x + 1] == 0) continue;
				if (room[y + 1][x] == 0) continue;
				if (room[y + 1][x + 1] == 0) continue;

				int[] ary = {room[y][x], room[y][x + 1], room[y + 1][x], room[y + 1][x + 1]};
				Arrays.sort(ary);

				if (ary[0] < ary[1] && ary[1] < ary[2] && ary[2] < ary[3]) return true;
			}
		}
		return false;
	}

	static void disp () {
		int cnt = 1;
		char[][] out = new char[HEIGHT][WIDTH];

		for (int y = 0; y < HEIGHT; y++) {
			for (int x = 0; x < WIDTH; x++) {
				if (room[y][x] != cnt) continue;
				if (x + 1 < WIDTH && room[y][x + 1] == cnt)
					out[y][x] = out[y][x + 1] = 'ー';
				else
					out[y][x] = out[y + 1][x] = '｜';
				cnt++;
			}
		}

		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++)
				System.out.print(out[i][j]);
			System.out.println();
		}
	}

}