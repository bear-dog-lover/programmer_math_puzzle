

import java.util.Arrays;

public class Q13 {

	public static void main (String[] args) {
		final int LIM = 18;
		int[] exp = new int[LIM];
		// -1は未決定
		Arrays.fill(exp, -1);

		boolean[] is_used = new boolean[10];

		int[][] conditions = new int[LIM][];
		conditions[1] = new int[]{5};			// B
		conditions[2] = new int[]{15};			// C
		conditions[3] = new int[]{9};			// D
		conditions[4] = new int[]{6};			// E
		conditions[7] = new int[]{10};			// F
		conditions[11] = new int[]{16, 17};		// H
		conditions[12] = new int[]{14};			// I

		System.out.println(solve(exp, LIM, is_used, conditions, 0));

	}

	public static int solve (int[] exp, int LIM, boolean[] is_used, int[][] conditions, int i) {
		//System.out.println(Arrays.toString(exp));
		// OK動作もいれよ
		if (i == LIM) {
			boolean c = check(exp);
			if (c) disp(exp);
			return c ? 1 : 0;
		}
		// 決定済
		if (exp[i] != -1) return solve(exp, LIM, is_used, conditions, i + 1);
		// 0~9
		int ret = 0;
		for (int ni = 0; ni < 10; ni++) {
			// 使用済み
			if (is_used[ni]) continue;
			int[] next_exp = Arrays.copyOf(exp, LIM);
			next_exp[i] = ni;
			// 同一箇所に入れる
			if (conditions[i] != null) {
				for (int c : conditions[i])
					next_exp[c] = ni;
			}
			is_used[ni] = true;
			ret += solve(next_exp, LIM, is_used, conditions, i + 1);
			is_used[ni] = false;
		}

		return ret;
	}

	public static boolean check (int[] exp) {
		if (exp[0] == 0 || exp[5] == 0 || exp[9] == 0) return false;
		int a = convert(exp, 0, 5);
		int b = convert(exp, 5, 9);
		int c = convert(exp, 9,13);
		int d = convert(exp,13,18);
		return a + b + c == d;
	}

	public static int convert (int[] exp, int s, int e) {
		int ret = 0;
		while (s < e) ret = ret * 10 + exp[s++];
		return ret;
	}

	public static void disp (int[] exp) {
		System.out.print(disp(exp,5,9));
		System.out.print(" + "+disp(exp,0,5));
		System.out.print(" + "+disp(exp,9,13));
		System.out.println(" = "+disp(exp,13,18));
	}

	public static String disp (int[] exp, int s, int e) {
		String ret = "";
		while (s < e) ret += exp[s++];
		return ret;
	}

}
