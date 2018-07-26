

import static util.util_math.*;

public class Q16 {

	public static void main (String[] args) {

		int ans = 0;
		for (int a = 1; a < 125; a++) {
			for (int c = a + 1; c < 125; c++) {
				if (gcd(a, c) > 1)       continue;
				if (!is_sqrt(2 * a * c)) continue;
				if (check(4 * (a + c + (int)Math.sqrt(2*a*c)), a, c)) ans++;
				if (check(4 * (a + c - (int)Math.sqrt(2*a*c)), a, c)) ans++;
			}
		}
		System.out.println(ans);
	}

	public static boolean check (int n, int a, int c) {
		if (n < 1 || n > 500) return false;

		int b=(n-2*a)/2, d = (n-2*c)/2;
		if (a > b || c > d) return false;

		debug(n, a, c);
		return true;
	}

	public static void debug (int n, int a, int c) {

		int b=(n-2*a)/2, d = (n-2*c)/2;

		System.out.println("・ひもの長さ = " + n);
		System.out.println("  1本目  縦" + a + "×横" + b + "の長方形\t→\t面積 = " + a*b);
		System.out.println("  2本目  縦" + c + "×横" + d + "の長方形\t→\t面積 = " + c*d);
		System.out.println("  3本目  縦" + n/4 + "×横" + n/4 + "の長方形\t→\t面積 = " + n*n/16 + "\n");
	}

}
