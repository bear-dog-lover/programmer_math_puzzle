/**
 * 合ってるけどおっそ
 * time:65856ms
 * ↓ループの外内入れ替え、後置きインクリメントを前置きに、mapのcloneを外で一度だけに、などなど・・・
 * time:24640ms
 * ↓mapをcharに
 * time:21735ms(自力の限界)
 * ↓本を参考にmaskでやってみよ.convertも要らなくなるし
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Q70 {

	static final int HEIGHT = 6;
	static final int WIDTH  = 4;
	static final int MAX    = HEIGHT * WIDTH;

	public static void main (String[] args) {
		long s = System.currentTimeMillis();

		final int REVERSE = (1 << MAX) - 1;
		Queue<Integer> q_map = new LinkedList<>();
		Queue<Integer> q_step = new LinkedList<>();
		Set<Integer> set = new HashSet<>();
		int max = 0, ans = 0;

		int start1 = 0b0011_0011_0011_0011_0011_0011;
		int start2 = 0b0000_0000_0000_1111_1111_1111;

		List<Integer> mask = new ArrayList<>();
		// 横並び(23-22,22-21,21-20,20-19)
		for (int d = 22; d >= 0; d--) {
			if (d % 4 == 3) continue;
			mask.add(0b11 << d);
		}
		// 縦並び(23-19,22-18,21-17,20-16)
		for (int d = 19; d >= 0; d--)
			mask.add(0b10001 << d);

		int msize = mask.size();

		q_map.add(start1);
		q_map.add(start2);

		q_step.add(0);
		q_step.add(0);

		set.add(start1);
		set.add(start2);
		set.add(start1^REVERSE);
		set.add(start2^REVERSE);

		while (!q_map.isEmpty()) {
			int map = q_map.poll();
			int step = q_step.poll();

			if (step > max) {
				max = step;
				ans = 1;
			} else if (step == max) {
				++ans;
			}

			++step;

			for (int mi = 0; mi < msize; ++mi) {
				int m = mask.get(mi);
				int masked = map & m;

				if (masked == 0 || masked == m) continue;

				int swap = map ^ m;
				if (set.contains(swap)) continue;

				set.add(swap);
				set.add(swap^REVERSE);
				q_map.add(swap);
				q_step.add(step);
			}
		}

		System.out.println("ans = " + ans * 2 + ", max = " + max);
		long e = System.currentTimeMillis();
		System.out.println("time:" + (e-s) + "ms");
	}

}