

public class Q11 {

	public static void main (String[] args) {

		int cnt = 0;
		long a = 1, b = 1;
		while (true) {
			long c = a + b;
			if (c % sum_of_digit(c) == 0) {
				System.out.println(c);
				if (++cnt == 11) break;
			}
			a = b;
			b = c;
		}

	}

	public static int sum_of_digit (long n) {
		int ret = 0;
		while (n > 0) {
			ret += n % 10;
			n /= 10;
		}
		return ret;
	}

}
