import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Q49 {

	static final int  N = 8;
	static final int N2 = N * 2;

	public static void main (String[] args) {
		int[] mask = make_mask();

		String str_end_pattern_1 = "", str_end_pattern_2 = "";
		for (int i = 0; i < N2; i++) {
			str_end_pattern_1 += i % 2 == 0 ? "1" : "0";
			str_end_pattern_2 += i % 2 == 0 ? "0" : "1";
		}
		int end_pattern_1 = Integer.parseInt(str_end_pattern_1, 2);
		int end_pattern_2 = Integer.parseInt(str_end_pattern_2, 2);

		String left = "", right = "";
		for (int i = 0; i < N; i++) {
			left += "1";
			right += "0";
		}
		int start = Integer.parseInt(left + right, 2);

		Queue<Integer> q_cards = new LinkedList<>();
		Queue<Integer> q_step = new LinkedList<>();
		Set<Integer> set = new HashSet<>();

		q_cards.add(start);
		q_step.add(0);
		set.add(start);

		while (!q_cards.isEmpty()) {
			int cards = q_cards.poll();
			int step = q_step.poll();

			if (cards == end_pattern_1 || cards == end_pattern_2) {
				System.out.println(step);
				break;
			}

			for (int i = 0; i < N2; i++) {
				int next_cards = cards^mask[i];
				if (set.contains(next_cards)) continue;

				q_cards.add(next_cards);
				q_step.add(step + 1);
				set.add(next_cards);
			}
		}
	}

	static int[] make_mask () {
		int[] ret = new int[N2];
		String mask = "111";
		while (mask.length() < N2) mask += "0";

		for (int si = 0; si < N2; si++) {
			ret[si] = Integer.parseInt(mask, 2);
			char tail = mask.charAt(N2 - 1);
			mask = tail + mask.substring(0, N2 - 1);
		}

		return ret;
	}

	static void debug_mask (int[] mask) {
		System.out.print("[" + Integer.toBinaryString(mask[0]));
		for (int i = 1; i < N2; i++)
			System.out.print(", " + Integer.toBinaryString(mask[i]));
		System.out.println("]");
	}
}
