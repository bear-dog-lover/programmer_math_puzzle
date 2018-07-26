import static util.util_string.*;

public class Q41 {

	public static void main (String[] args) {
		int ans = 0;

		for (int left = 0; left < 256; left++) {

			if (is_exist_duplicate_digits(left + "")) continue;

			String left_bs = Integer.toBinaryString(left);
			while (left_bs.length() < 8) left_bs = "0" + left_bs;

			String left_bs_rev = reverse(left_bs);
			int left_rev_2_to_10 = Integer.parseInt(left_bs_rev, 2);

			if (is_exist_duplicate_digits(left + "" + left_rev_2_to_10)) continue;

			for (int right = 0; right < 256; right++) {

				if (is_exist_duplicate_digits(right + "")) continue;

				String right_bs = Integer.toBinaryString(right);
				while (right_bs.length() < 8) right_bs = "0" + right_bs;

				String right_bs_rev = reverse(right_bs);
				int right_rev_2_to_10 = Integer.parseInt(right_bs_rev, 2);

				String ip_address = left + "" + right + right_rev_2_to_10 + left_rev_2_to_10;

				if (ip_address.length() != 10) continue;
				if (is_exist_duplicate_digits(ip_address)) continue;

				System.out.println(++ans + "\t" + left + "." + right + "." + right_rev_2_to_10 + "." + left_rev_2_to_10);
			}
		}

		System.out.println(ans);
	}

	static boolean is_exist_duplicate_digits (String str) {
		int[] seq = new int[10];
		char[] c = str.toCharArray();
		for (int i = 0; i < c.length; i++)
			if (++seq[c[i] - '0'] > 1) return true;
		return false;
	}

}
