import static util.util_math.*;

import java.util.LinkedList;
import java.util.Queue;

public class Q44 {

	public static void main (String[] args) {
		long s = System.currentTimeMillis();

		int ans = 0;
		for (int a = 10; a <= 100; a += 2) {
			for (int b = a / 2 + 1; b < a; b++) {
				if (gcd(a, b) > 1) continue;
				if (solve(a, b, a - b)) ans++;
			}
		}

		long e = System.currentTimeMillis();
		System.out.println(ans);
		System.out.println((e-s)+"ms");
	}

	static boolean solve (int cap_a, int cap_b, int cap_c) {
		final int TARGET = cap_a / 2;
		final int[] caps = {cap_a, cap_b, cap_c};

		Queue<int[]> q_mounts = new LinkedList<>();
		Queue<String> q_status = new LinkedList<>();

		q_mounts.add(new int[]{cap_a, 0, 0});
		q_status.add(cap_a+",0,0");

		while (!q_mounts.isEmpty()) {
			int[] mounts = q_mounts.poll();
			int mount_a = mounts[0];
			int mount_b = mounts[1];
			int mount_c = mounts[2];
			String status = q_status.poll();

			if (mount_a == TARGET && (mount_b == TARGET || mount_c == TARGET))
				return true;

			for (int from = 0; from < 3; from++) {
				for (int to = 0; to < 3; to++) {
					if (from == to) continue;

					int[] mounts_next = {mount_a, mount_b, mount_c};

					// 空のコップからは移せない
					if (mounts_next[from] == 0) continue;
					// 満タンのコップへは移せない
					if (mounts_next[to] == caps[to]) continue;

					// 水を足す
					mounts_next[to]    = Math.min(caps[to], mounts[to] + mounts[from]);
					// 水を引く
					mounts_next[from] -= Math.min(caps[to] - mounts[to], mounts[from]);

					// 足されたコップが満杯でない、かつ、足したコップが空でない
					if (mounts_next[to] < caps[to] && mounts_next[from] > 0) continue;
					// 一度到達した状態
					String status_next = mounts_next[0]+","+mounts_next[1]+","+mounts_next[2];
					if (status.indexOf(status_next) > -1) continue;

					q_mounts.add(mounts_next);
					q_status.add(status + ":" + status_next);
				}
			}
		}

		return false;
	}
}
