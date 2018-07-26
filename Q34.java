import java.util.HashSet;
import java.util.Set;

public class Q34 {

	static final int SIZE = 9;

	public static void main (String[] args) {

		final int MAX = SIZE * SIZE;
		int ans = 0;

		// 飛車の位置ループ
		for (int pos_hi = 0; pos_hi < MAX; pos_hi++) {
			int pos_x_hi = pos_hi / 9;
			int pos_y_hi = pos_hi % 9;
			// 角の位置ループ
			for (int pos_ka = 0; pos_ka < MAX; pos_ka++) {
				int pos_x_ka = pos_ka / 9;
				int pos_y_ka = pos_ka % 9;
				// 駒は1マスに1駒
				if (pos_x_hi == pos_x_ka && pos_y_hi == pos_y_ka) continue;
				// 効きの計算
				ans += calc(pos_x_hi, pos_y_hi, pos_x_ka, pos_y_ka);
			}
		}

		System.out.println(ans);

	}

	// 飛車の効き + 角の効き
	static int calc (int pos_x_hi, int pos_y_hi, int pos_x_ka, int pos_y_ka) {
		Set<Integer> kiki_hi = new HashSet<>();
		int hi = calc_hi(pos_x_hi, pos_y_hi, pos_x_ka, pos_y_ka, kiki_hi);
		int ka = calc_ka(pos_x_hi, pos_y_hi, pos_x_ka, pos_y_ka, kiki_hi);
		//System.out.println(hi + ", " + ka);
		return hi + ka;
	}

	// 飛車の効き
	static int calc_hi (int pos_x_hi, int pos_y_hi, int pos_x_ka, int pos_y_ka, Set<Integer> kiki_hi) {
		int ret = 0;
		int[] dx = { 0, 0, -1, 1};
		int[] dy = {-1, 1,  0, 0};
		for (int di = 0; di < 4; di++) {
			int pos_x = pos_x_hi + dx[di];
			int pos_y = pos_y_hi + dy[di];
			while (0 <= pos_x && pos_x < SIZE && 0 <= pos_y && pos_y < SIZE &&
					(pos_x != pos_x_ka || pos_y != pos_y_ka)) {
				kiki_hi.add(pos_x * 10 + pos_y);
				ret++;
				pos_x += dx[di];
				pos_y += dy[di];
			}
		}
		return ret;
	}

	// 角の効き
	static int calc_ka (int pos_x_hi, int pos_y_hi, int pos_x_ka, int pos_y_ka, Set<Integer> kiki_hi) {
		int ret = 0;
		int[] dx = { 1, 1, -1, -1};
		int[] dy = {-1, 1,  1, -1};
		for (int di = 0; di < 4; di++) {
			int pos_x = pos_x_ka + dx[di];
			int pos_y = pos_y_ka + dy[di];
			while (0 <= pos_x && pos_x < SIZE && 0 <= pos_y && pos_y < SIZE && (pos_x != pos_x_hi || pos_y != pos_y_hi)) {
				if (!kiki_hi.contains(pos_x * 10 + pos_y)) ret++;
				pos_x += dx[di];
				pos_y += dy[di];
			}
		}
		return ret;
	}

}
