import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Q65 {

	static final int N = 12;
	static final int NONE = 15;

	public static void main (String[] args) {
		long s = System.currentTimeMillis();

		int[][] swaps = {
				{6,7},
				{6,7,8},
				{7,8,9},
				{8,9,10},
				{9,10,11},
				{10,11},
				{0,1},
				{0,1,2},
				{1,2,3},
				{2,3,4},
				{3,4,5},
				{4,5}
		};

		Queue<Long> q_status  = new LinkedList<>();
		Queue<Integer> q_poor = new LinkedList<>();
		Queue<Integer> q_cnt  = new LinkedList<>();
		Queue<Boolean> q_dist = new LinkedList<>();
		Map<Long, Integer> memo_for_head = new HashMap<>();
		Map<Long, Integer> memo_for_tail = new HashMap<>();

		// i番目の学生は,i+1番のボールを持つ
		final long start1 = convert(new int[]{   1,2,3,4,5,6,7,8,9,10,11,NONE});
		final long start2 = convert(new int[]{NONE,2,3,4,5,6,7,8,9,10,11,   1});
		q_status.add(start1);
		q_status.add(start2);
		q_poor.add(11);
		q_poor.add(0);
		q_cnt.add(0);
		q_cnt.add(0);
		q_dist.add(true);
		q_dist.add(false);
		memo_for_head.put(start1, 0);
		memo_for_tail.put(start2, 0);

		while (!q_status.isEmpty()) {
			long qs = q_status.poll();
			int[] status = convert(qs);
			int poor = q_poor.poll();
			int cnt = q_cnt.poll();
			boolean dist = q_dist.poll();

			if (dist && memo_for_tail.containsKey(qs)) {
				System.out.println(cnt + memo_for_tail.get(qs));
				break;
			} else if (!dist && memo_for_head.containsKey(qs)) {
				System.out.println(cnt + memo_for_head.get(qs));
				break;
			}

			// 学生poorが学生swaps[si](= sw)からボールを受け取る
			for (int si = 0; si < swaps[poor].length; si++) {
				int[] status_next = status.clone();
				int sw = swaps[poor][si];
				status_next[poor] = status_next[sw];
				status_next[sw] = 0;

				long next = convert(status_next);
				if ((dist && memo_for_head.containsKey(next)) ||
						(!dist && memo_for_tail.containsKey(next))) continue;

				q_status.add(next);
				q_poor.add(sw);
				q_cnt.add(cnt + 1);
				q_dist.add(dist);
				if (dist)
					memo_for_head.put(next, cnt + 1);
				else
					memo_for_tail.put(next, cnt + 1);
			}
		}

		long e = System.currentTimeMillis();
		System.out.println("time:" + (e-s) + "ms");
	}

	static long convert (int[] nums) {
		long ret = nums[0];
		for (int i = 1; i < N; i++) {
			ret <<= 4;
			ret |= nums[i];
		}
		return ret;
	}

	static int[] convert (long n) {
		int[] ret = new int[N];
		for (int i = N - 1; i >= 0; i--, n >>= 4)
			ret[i] = (int)(n & 0xf);
		return ret;
	}
}
