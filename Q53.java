import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q53 {

	static int M = 5;
	static int N = 6;
	static int LIM = M * N;
	static int STATUS = (int)Math.pow(M, N);
	static int[] base = new int[LIM];
	static List<Map<Integer, Long>> memo = new ArrayList<>();

	static {
		int idx = 0;
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++)
				base[idx++] = i;
		}

		for (int i = 0; i <= LIM; i++)
			memo.add(new HashMap<Integer, Long>());
	}

	public static void main (String[] args) {
		long s = System.currentTimeMillis();

		System.out.println(solve(0, new int[M]));

		long e = System.currentTimeMillis();
		System.out.println("time:" + (e-s) + "ms");
	}

	static long solve (int cnt, int[] seq) {

		int con = convert(seq);
		if (memo.get(cnt).containsKey(con)) return memo.get(cnt).get(con);

		if (cnt == LIM) return 1;

		long ret = 0;
		for (int i = 0; i < M; i++) {
			// 同じ袋
			if (i == base[cnt]) continue;
			// 使用回数オーバー
			if (seq[i] >= N) continue;

			seq[i]++;
			ret += solve(cnt + 1, seq);
			seq[i]--;
		}

		memo.get(cnt).put(con, ret);
		return ret;
	}

	static int convert (int[] seq) {
		int ret = 0;
		for (int s : seq)
			ret = ret * 10 + s;
		return ret;
	}

}
