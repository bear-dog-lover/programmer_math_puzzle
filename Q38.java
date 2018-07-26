import java.util.BitSet;

public class Q38 {

	static int ans = Integer.MAX_VALUE;
	static String ans_path;
	static int[][] disp = {
			{1,1,1,0,1,1,1},
			{0,0,1,0,0,1,0},
			{1,0,1,1,1,0,1},
			{1,0,1,1,0,1,1},
			{0,1,1,1,0,1,0},
			{1,1,0,1,0,1,1},
			{1,1,0,1,1,1,1},
			{1,0,1,0,0,1,0},
			{1,1,1,1,1,1,1},
			{1,1,1,1,0,1,1},
	};

	public static void main (String[] args) {
		BitSet is_used = new BitSet(10);
		solve(is_used, 0, -1, "");
		System.out.println(ans_path + "\n" + ans);
	}

	public static void solve (BitSet is_used, int sum, int now_disp, String path) {
		if (sum >= ans) return;
		if (is_used.cardinality() == 10) {
			if (ans > sum) {
				ans = sum;
				ans_path = path;
			}
			return;
		}
		for (int i = 0; i < 10; i++) {
			if (is_used.get(i)) continue;
			is_used.set(i, true);
			solve(is_used, sum + calc(now_disp, i), i, path.equals("") ? (i+"") : (path+"->"+i));
			is_used.set(i, false);
		}
	}

	public static int calc (int from, int to) {
		if (from == -1) return 0;
		int ret = 0;
		for (int i = 0; i < 7; i++)
			if (disp[from][i] != disp[to][i]) ret++;
		return ret;
	}

}
