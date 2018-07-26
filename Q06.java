

public class Q06 {
	public static void main (String[] args) {
		int ans = 0;
		for (int i = 2; i <= 10000; i += 2) {
			int n = 3 * i + 1;
			//System.out.println(i + ":" + n);
			while (n != i && n != 1) {
				n = n % 2 == 0 ? n / 2 : 3 * n + 1;
				//System.out.println("\t" + n);
			}
			//System.out.println(n == i ? "Bingo!" : "Dammit!");
			ans += n == i ? 1 : 0;
		}
		System.out.println(ans);
	}
}
