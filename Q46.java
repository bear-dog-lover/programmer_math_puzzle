import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Q46 {

	public static void main (String[] args) {
		long s = System.currentTimeMillis();

		int ans = 0;
		final int N = 7;
		Queue<int[]> q_nums = new LinkedList<>();
		Queue<Integer> q_swap = new LinkedList<>();
		Set<String> set = new HashSet<>();

		int[] start = new int[N];
		for (int i = 0; i < N; i++) start[i] = i + 1;
		q_nums.add(start);
		q_swap.add(0);
		set.add(concat(start));

		while (!q_nums.isEmpty()) {
			int[] nums = q_nums.poll();
			int swap = q_swap.poll();

			System.out.println(Arrays.toString(nums) + "\t" + swap);
			ans += swap;

			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					int[] next = copy(nums);
					swap(next, i, j);
					String str_next = concat(next);
					if (set.contains(str_next)) continue;

					q_nums.add(next);
					q_swap.add(swap + 1);
					set.add(str_next);
				}
			}
		}

		long e = System.currentTimeMillis();
		System.out.println(ans);
		System.out.println((e-s)+"ms");
	}

	static String concat (int[] nums) {
		StringBuilder sb = new StringBuilder();
		for (int n : nums) sb.append(n);
		return sb.toString();
	}

	static int[] copy (int[] ary) {
		int len = ary.length;
		int[] ret = new int[len];
		for (int i = 0; i < len; i++)
			ret[i] = ary[i];
		return ret;
	}

	static void swap (int[] ary, int i, int j) {
		int tmp = ary[i];
		ary[i] = ary[j];
		ary[j] = tmp;
	}
}
