

public class Q02 {
	public static void main (String[] args) {
		final int DIGIT = 6;
		final int START = (int)Math.pow(10, DIGIT - 1);
		final int END   = (int)Math.pow(10, DIGIT);
		final int LIM   = (int)Math.pow(2, DIGIT - 1);
		for (int i = START; i < END; i++) {
			String si = i + "";
			for (int j = 1; j < LIM; j++) {
				char[] ci = (i + "").toCharArray();
				String stmt = ci[0] + "";
				for (int k = 0; k < DIGIT - 1; k++) {
					int s = 1 << k;
					stmt += (j & s) == s ? "*" : "";
					stmt += ci[k+1] + "";
				}
				String[] nums = stmt.split("\\*");
				int sum = 1;
				for (String n : nums) {
					sum *= Integer.parseInt(n);
				}
				StringBuilder sb = new StringBuilder(sum + "");
				if (si.equals(sb.reverse().toString())) {
					System.out.println(i + ":" + stmt + "=" + sum);
					break;
				}
			}
		}

	}
}
