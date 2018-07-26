

//import static string.util_string.*;

public class Q10 {

	static final int MAX = 36;

	public static void main (String[] args) {
		int[] eulope  = new int[]{ 0,32,15,19, 4,21, 2,25,17,34, 6,27,13,36,11,30, 8,23,10, 5,24,16,33, 1,20,14,31, 9,22,18,29, 7,28,12,35, 3,26};
		int[] america = new int[]{ 0,28, 9,26,30,11, 7,20,32,17, 5,22,34,15, 3,24,36,13, 1, 0,27,10,25,29,12, 8,19,31,18, 6,21,33,16, 4,23,35,14, 2};
		int[] find_max_e = solve(eulope);
		int[] find_max_a = solve(america);
		int ans = 0;
		for (int i = 2; i <= MAX; i++)
			if (find_max_e[i] < find_max_a[i]) ans++;
		//System.out.println(Arrays.toString(find_max_e));
		//System.out.println(Arrays.toString(find_max_a));
		System.out.println(ans);
	}

	public static int[] solve (int[] ary) {
		int[] ret = new int[MAX + 1];
		int len = ary.length;
		for (int si = 0; si < len; si++) {
			int sum = 0;
			for (int ai = 1; ai <= MAX; ai++) {
				sum += ary[(si + ai - 1) % len];
				ret[ai] = Math.max(ret[ai], sum);
			}
		}
		return ret;
	}

}
