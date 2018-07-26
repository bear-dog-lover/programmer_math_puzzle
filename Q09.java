

//import static string.util_string.*;

public class Q09 {

	static int M_MAX = 20;
	static int W_MAX = 10;
	static int SIZE = M_MAX + W_MAX;

	public static void main (String[] args) {
		int[] seq = new int[SIZE];
		System.out.println(solve(seq, 0, 0, 0));
	}

	public static int solve (int[] seq, int i, int cnt_m, int cnt_w) {
		if (i > 0 && i < SIZE && cnt_m == cnt_w) {
			return 0;
		}
		if (i == SIZE) {
			//System.out.println(Arrays.toString(seq));
			return check(seq, cnt_m, cnt_w) ? 1 : 0;
		}
		//System.out.println(Arrays.toString(seq) + "\ti = " + i + ", cnt_m = " + cnt_m + ", cnt_w = " + cnt_w);
		int ret = 0;
		if (cnt_m < M_MAX) {
			seq[i] = 1;
			ret += solve(seq, i + 1, cnt_m + 1, cnt_w);
			seq[i] = 0;
		}
		if (cnt_w < W_MAX) {
			seq[i] = -1;
			ret += solve(seq, i + 1, cnt_m, cnt_w + 1);
			seq[i] = 0;
		}
		return ret;
	}

	public static boolean check (int[] seq, int cnt_m, int cnt_w) {
		int sum = cnt_m - cnt_w;
		int sum2 = 0;
		for (int i = 0; i < SIZE - 1; i++) {
			sum2 += seq[i];
			if (sum - sum2 == 0) return false;
		}
		return true;
	}

}
