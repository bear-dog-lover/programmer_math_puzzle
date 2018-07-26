import static util.util_math.*;

import java.util.ArrayList;
import java.util.List;

/**
 * どいひ
 * @author hangn
 *
 */

public class Q19 {

	public static void main (String[] args) {

		long s = System.currentTimeMillis();

		List<Integer> comp = new ArrayList<>();
		comp.add(4);
		comp.add(6);
		comp.add(8);
		comp.add(9);
		comp.add(10);
		comp.add(12);

		for (int n = 14; ; n++) {
			if (is_prime(n)) continue;
			if (solve(n, comp) == 7) {
				System.out.println(n);
				break;
			}
			comp.add(n);
		}

		long e = System.currentTimeMillis();
		System.out.println("time:" + (e-s) + "ms");

	}

	public static int solve (int n, List<Integer> comp) {
		return solve(n, 0, 0, comp, new int[6]);
	}

	public static int solve (int n, int cnt, int i, List<Integer> comp, int[] friends) {
		if (i >= comp.size()) return 0;
		if (cnt == 6)         return calc(n, friends);
		int s1 = solve(n, cnt, i + 1, comp, friends);
		friends[cnt] = comp.get(i);
		int s2 = solve(n, cnt + 1, i + 1, comp, friends);
		return Math.max(s1, s2);
	}

	public static int calc (int n, int[] friends) {
		boolean[] is_selected = new boolean[7];
		int[] new_friends = new int[7];
		for (int i = 0; i < 6; i++) new_friends[i] = friends[i];
		new_friends[6] = n;
		if (is_exists_hub(new_friends)) return 0;
		return find_max_chain(0, 0, is_selected, new_friends) ? 7 : 0;
	}

	// 3人以上の友達を含む数がいるか
	public static boolean is_exists_hub (int[] friends) {
		for (int i = 0; i < friends.length; i++) {
			int cnt = 0;
			for (int j = 0; j < friends.length; j++) {
				if (i == j) continue;
				if (gcd(friends[i], friends[j]) == 1) continue;
				if (++cnt > 2) return true;
			}
		}
		return false;
	}

	public static boolean find_max_chain (int last_selected, int cnt, boolean[] is_selected, int[] friends) {
		if (cnt == 7) return true;
		for (int i = 0; i < 7; i++) {
			if (is_selected[i]) continue;
			if (last_selected != -1 && gcd(last_selected, friends[i]) == 1) continue;
			is_selected[i] = true;
			if (find_max_chain(friends[i], cnt + 1, is_selected, friends)) return true;
			is_selected[i] = false;
		}
		return false;
	}

}
