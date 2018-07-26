import static util.util_math.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Q45 {

	static int SIZE;

	public static void main (String[] args) {

		List<Integer> primes = new ArrayList<>();
		for (int i = 101; i < 1000; i += 2) {
			if (!is_prime(i)) continue;
			primes.add(i);
		}
		SIZE = primes.size();

		System.out.println(solve(primes, 0, new int[3], new HashSet<Integer>()));

	}

	public static int solve (List<Integer> primes, int cnt, int[] selected, Set<Integer> selected_idx) {
		if (cnt == 3) {
			boolean c = check(primes, selected);
			//if (c && selected[0] == 127) debug(selected);
			return c ? 1 : 0;
		}
		int ret = 0;
		for (int i = 0; i < SIZE; i++) {
			// 同じやつは使えない
			if (selected_idx.contains(i)) continue;
			selected[cnt] = primes.get(i);
			selected_idx.add(i);
			ret += solve(primes, cnt + 1, selected, selected_idx);
			selected_idx.remove(i);
		}
		return ret;
	}

	public static boolean check (List<Integer> primes, int[] selected) {
		int s0 = selected[0];
		int s1 = selected[1];
		int s2 = selected[2];

		int p1 = Integer.parseInt(s0 / 100 + "" + s1 / 100 + "" + s2 / 100);
		int p2 = Integer.parseInt(s0 % 100 / 10 + "" + s1 % 100 / 10 + "" + s2 % 100 / 10);

		if (p1 == p2) return false;

		int p3 = Integer.parseInt(s0 % 10 + "" + s1 % 10 + "" + s2 % 10);

		if (p3 == p1 || p3 == p2) return false;

		int[] p = {p1, p2, p3};

		for (int i = 0; i < 3; i++)
			if (!primes.contains(p[i]) || p[i] == s0 || p[i] == s1 || p[i] == s2) return false;

		return true;
	}

	public static void debug (int[] primes) {
		System.out.println(primes[0]);
		System.out.println(primes[1]);
		System.out.println(primes[2]);
		System.out.println();
	}

}