import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Q57 {

	static final int N = 7;

	public static void main (String[] args) {
		long s = System.currentTimeMillis();

		Queue<Integer> q_nums = new LinkedList<>();
		Queue<Integer> q_step = new LinkedList<>();
		Set<Integer> set = new HashSet<>();

		int start = 0;
		for (int i = 1; i <= N; i++)
			start = start * 10 + i;

		q_nums.add(start);
		q_step.add(0);
		set.add(start);

		while (!q_nums.isEmpty()) {
			int[] nums = convert(q_nums.poll());
			int step = q_step.poll();

			if (step == 10) {
				System.out.println(q_nums.size() + 1);
				break;
			}

			int[] next = nums.clone();
			++step;

			for (int i = 1; i < N; i++) {
				swap(next, i - 1, i);

				int con = convert(next);
				if (set.contains(con)) {
					swap(next, i - 1, i);
					continue;
				}

				q_nums.add(con);
				q_step.add(step);
				set.add(con);

				swap(next, i - 1, i);
			}
		}

		long e = System.currentTimeMillis();
		System.out.println("time:" + (e-s) + "ms");
	}

	static int convert (int[] nums) {
		int ret = 0;
		for (int n : nums)
			ret = ret * 10 + n;
		return ret;
	}

	static int[] convert (int n) {
		char[] c = (n + "").toCharArray();
		int[] ret = new int[N];
		for (int i = 0; i < N; i++)
			ret[i] = c[i] - '0';
		return ret;
	}

	static void swap (int[] ary, int i, int j) {
		int tmp = ary[i];
		ary[i] = ary[j];
		ary[j] = tmp;
	}
}
