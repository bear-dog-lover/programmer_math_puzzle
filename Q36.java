import static util.util_string.*;

import java.util.BitSet;
import java.util.Map;
import java.util.TreeMap;

public class Q36 {

	public static void main (String[] args) {

		final int MAX = 50;
		BitSet is_find = new BitSet(MAX + 1);
		Map<Integer, Long> list_ans = new TreeMap<>();

		is_find.set(0);
		is_find.set(13);

		for (long i = 1; ; i++) {

			Long n = Long.parseLong(Long.toBinaryString(i).replaceAll("1", "7"));
			boolean is_reverse = is_reverse(n+"");

			for (int ni = 1; ni <= MAX; ni++) {
				if (is_find.get(ni)) continue;
				if (n % ni != 0)     continue;
				is_find.set(ni);
				if (!is_reverse) continue;
				list_ans.put(ni, n);
			}
			if (is_find.cardinality() == MAX + 1) break;
		}

		for (int n : list_ans.keySet()) {
			long val = list_ans.get(n);
			System.out.printf("n=%2dのとき\t%2d*%d=%d\n",n,n,val/n,val);
		}

	}
}
