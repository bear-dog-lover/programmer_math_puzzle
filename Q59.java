/**
 * 合ってたけど時間かかる
 *
 * 47
 * time:182671ms
 *
 * ↓depthを削った
 *
 * time:170309ms
 *
 * ↓縦横同じときは横に切らない
 *
 * time:69183ms
 *
 * solveの戻り値をvoidにしてansをstaticに.
 * あとans以上なら枝刈り
 *
 * ↓
 *
 * time:13ms
 *
 */

public class Q59 {

	static final int HEIGHT = 16;
	static final int WIDTH  = 12;
	static final int LIM = HEIGHT * WIDTH / 2;
	static int ans = Integer.MAX_VALUE;

	public static void main (String[] args) {
		long s = System.currentTimeMillis();

		solve(HEIGHT, WIDTH, 0, 0, true, 0);
		System.out.println(ans);

		long e = System.currentTimeMillis();
		System.out.println("time:" + (e-s) + "ms");
	}

	static void solve (int height, int width, int mount_ate_a, int mount_ate_b, boolean is_tern_a, int sum) {
		// 一方が半分より多く食べた場合
		if (mount_ate_a > LIM || mount_ate_b > LIM || sum >= ans) return;

		if (height == 1 && width == 1) {
			ans = sum;
			return;
		}

		// 縦に切る
		for (int i = 1; i <= width / 2; i++) {
			int mount_ate_a_next = is_tern_a ? mount_ate_a + height * i : mount_ate_a;
			int mount_ate_b_next = is_tern_a ? mount_ate_b : mount_ate_b + height * i;
			solve(height, width - i, mount_ate_a_next, mount_ate_b_next, !is_tern_a, sum + height);
		}

		// 横に切る
		for (int i = 1; i <= height / 2; i++) {
			int mount_ate_a_next = is_tern_a ? mount_ate_a + width * i : mount_ate_a;
			int mount_ate_b_next = is_tern_a ? mount_ate_b : mount_ate_b + width * i;
			solve(height - i, width, mount_ate_a_next, mount_ate_b_next, !is_tern_a, sum + width);
		}
	}

	static void debug (String str, int tab) {
		for (int i = 0; i < tab; i++) System.out.print("\t");
		System.out.println(str);
	}
}
