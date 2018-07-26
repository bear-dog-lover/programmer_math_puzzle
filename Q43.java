import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 *
 * 合ってはいるけど1分かかる、どげんかせんといかん
 *
 * @author hangn
 *
 */

public class Q43 {

	static final int TARGET  = 5;
	static final int TARGET2 = TARGET * 2;

	public static void main (String[] args) {
		long s = System.currentTimeMillis();

		String START = "";
		for (int i = 0; i < TARGET2; i++)
			START += i+"";

		String END="";
		for (int i=TARGET2-1;i>=0;i--)
			END+=i+"";

		Map<String, Integer> mins_for_head = new HashMap<>();
		Map<String, Integer> mins_for_tail = new HashMap<>();

		Queue<String> q_card  = new LinkedList<>();
		Queue<Integer> q_path = new LinkedList<>();
		Queue<Boolean> q_is_head = new LinkedList<>();

		q_card.add(START);
		q_path.add(0);
		q_is_head.add(true);

		q_card.add(END);
		q_path.add(0);
		q_is_head.add(false);

		main:
		while (!q_card.isEmpty()) {
			String card = q_card.poll();
			int path = q_path.poll();
			boolean is_head = q_is_head.poll();

			//System.out.println(card + ", " + path + ", " + is_head);

			if ((is_head && card.equals(END)) || (!is_head && card.equals(START))) {
				System.out.println(path);
				break;
			}

			for (int i = 1; i <= TARGET; i++) {
				String shuffled_card = shuffle(card, i, is_head);

				if (is_head) {
					if (mins_for_head.containsKey(shuffled_card)) {
						continue;
					} else {
						mins_for_head.put(shuffled_card, path + 1);
						if (mins_for_tail.containsKey(shuffled_card)) {
							System.out.println(path + 1 + mins_for_tail.get(shuffled_card));
							break main;
						}
					}
				} else {
					if (mins_for_tail.containsKey(shuffled_card)) {
						continue;
					} else {
						mins_for_tail.put(shuffled_card, path + 1);
						if (mins_for_head.containsKey(shuffled_card)) {
							System.out.println(path + 1 + mins_for_head.get(shuffled_card));
							break main;
						}
					}
				}

				q_card.add(shuffled_card);
				q_path.add(path + 1);
				q_is_head.add(is_head);
			}
		}

		long e = System.currentTimeMillis();
		System.out.println((e - s) + "ms");
	}

	static String shuffle (String card, int start, boolean is_head) {
		return is_head ? shuffle(card, start) : modosu(card, start);
	}

	static String shuffle (String card, int start) {
		char[] c   = card.toCharArray();
		char[] ret = c.clone();
		int idx_ret = 0;

		// 抜き出した分を入れる
		for (int i = start; i < start + TARGET; i++)
			ret[idx_ret++] = c[i];

		// 頭
		for (int i = 0; i < start; i++)
			ret[idx_ret++] = c[i];

		// Siri
		for (int i = start + TARGET; i < TARGET2; i++)
			ret[idx_ret++] = c[i];

		return String.valueOf(ret);
	}

	static String modosu (String card, int start) {
		char[] c   = card.toCharArray();
		char[] ret = new char[TARGET2];
		int idx_ret = 0;

		// 挿入位置の前まで入れる
		for (int i = TARGET; i < TARGET + start; i++)
			ret[idx_ret++] = c[i];

		//System.out.println("\tstep1=" + String.valueOf(ret));

		// 挿入するとこ
		for (int i = 0; i < TARGET; i++)
			ret[idx_ret++] = c[i];

		// Siri
		for (int i = start + TARGET; i < TARGET2; i++)
			ret[idx_ret++] = c[i];

		return String.valueOf(ret);
	}
}
