import java.util.HashSet;
import java.util.Set;

public class Q40 {

	public static void main (String[] args) {
		long s = System.currentTimeMillis();

		int ans = 0, max = 0;
		Set<Integer> memo = new HashSet<>();

		for (int n = 213456789; n <= 987654321;) {

			if (memo.contains(n)) {
				n++;
				continue;
			}

			int idx_zero = (n + "").lastIndexOf('0');
			if (idx_zero != -1) {
				//System.out.print("iz:" + n + "->");
				n += (int)Math.pow(10, 8 - idx_zero);
				//System.out.println(n);
				continue;
			}

			int cd = check_digit(n);
			if (cd > 0) {
				//System.out.print("cd:" + n + "->");
				n += (int)Math.pow(10, 8 - (n + "").lastIndexOf(cd + ""));
				//System.out.println(n);
				continue;
			}

			//System.out.println(n);
			int cnt = 0;
			for (int tmp_n = n; ; cnt++) {
				memo.add(tmp_n);
				int sorted_n = sort(tmp_n);
				if (sorted_n == tmp_n) break;
				tmp_n = sorted_n;
			}

			if (max < cnt) {
				max = cnt;
				ans = n;
			}
		}

		long e = System.currentTimeMillis();
		System.out.println(ans);
		System.out.println("time:" + (e-s) + "ms");
	}

	static int sort (int n) {
		char[] c = (n + "").toCharArray();
		int cnt_swap = c[0] - '0';
		for (int i = 0; i < cnt_swap / 2; i++) {
			char tmp = c[i];
			c[i] = c[cnt_swap - i - 1];
			c[cnt_swap - i - 1] = tmp;
		}
		return Integer.parseInt(String.valueOf(c));
	}

	static int check_digit (int n) {
		int[] seq = new int[10];
		while (n > 0) {
			int m = n % 10;
			if (++seq[m] > 1) return m;
			n /= 10;
		}
		return 0;
	}

}
