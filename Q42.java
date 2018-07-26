import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Q42 {

	public static void main (String[] args) {

		final int TARGET = 1234;
		int min = Integer.MAX_VALUE;
		List<String> list_ans = new ArrayList<>();

		// 使用する数字
		for (int n = 1; n < 10; n++) {
			// nをいくつ使うか
			for (int ci = 1; ci < min; ci++) {
				// nをci個使ったすべての式を取得
				List<String> list_exp_all = make_exp_all(n, ci);
				for (String exp : list_exp_all) {
					// 式を計算
					if (calc(exp) != TARGET) continue;
					// より少ない数字で表せたため,前の結果を破棄
					if (ci < min) {
						min = ci;
						list_ans.clear();
					}
					list_ans.add(exp);
				}
			}
		}

		for (String exp : list_ans)
			System.out.println(exp);

	}

	// use_numをuse_cnt個使った式のすべてを返す
	public static List<String> make_exp_all (int use_num, int use_cnt) {
		List<String> ret = new ArrayList<>();
		char[] op = {'+', '-', '*', '/'};

		Queue<String> q_exp = new LinkedList<>();
		Queue<Integer> q_cnt = new LinkedList<>();
		Queue<Boolean> q_is_add_operator = new LinkedList<>();

		q_exp.add("");
		q_cnt.add(0);
		q_is_add_operator.add(false);

		while (!q_exp.isEmpty()) {
			String exp = q_exp.poll();
			int cnt = q_cnt.poll();
			boolean is_add_operator = q_is_add_operator.poll();

			if (cnt == use_cnt) {
				ret.add(exp);
				continue;
			}

			// 式の末尾に数字を追加
			q_exp.add(exp + use_num);
			q_cnt.add(cnt + 1);
			q_is_add_operator.add(true);

			// 演算子が使えないならば次へ
			if (!is_add_operator) continue;

			// 四つの演算子を式の末尾に追加
			// 数字の使用は無い、また、次に演算子は使えない
			for (int oi = 0; oi < 4; oi++) {
				q_exp.add(exp + op[oi]);
				q_cnt.add(cnt);
				q_is_add_operator.add(false);
			}
		}

		return ret;
	}

	// 数式を計算
	public static int calc (String exp) {
		// 乗算・除算を先に処理
		exp = calc_special_operator(exp);

		int ret = 0, buf = 0;
		boolean is_add = true;
		for (int i = 0; i < exp.length(); i++) {
			char c = exp.charAt(i);
			if (is_digit(c)) {
				buf = buf * 10 + (c - '0');
			} else {
				ret = is_add ? ret + buf : ret - buf;
				is_add = c == '+';
				buf = 0;
			}
		}

		return is_add ? ret + buf : ret - buf;
	}

	// 乗算または除算のみを計算
	public static String calc_special_operator (String exp) {

		for (int idx = next_idx(exp); idx > -1; idx = next_idx(exp)) {
			final int len = exp.length();

			// 第一項の開始位置を探す
			int idx_term_start_one = idx - 1;
			while (idx_term_start_one >= 0 && is_digit(exp.charAt(idx_term_start_one)))
				idx_term_start_one--;
			idx_term_start_one++;

			// 第二項の終了位置を探す
			int idx_term_end_two = idx + 1;
			while (idx_term_end_two < len && is_digit(exp.charAt(idx_term_end_two)))
				idx_term_end_two++;
			idx_term_end_two--;

			// 計算
			int term_one = Integer.parseInt(exp.substring(idx_term_start_one, idx));
			int term_two = Integer.parseInt(exp.substring(idx + 1, idx_term_end_two + 1));

			char operator = exp.charAt(idx);
			boolean is_mul = operator == '*';
			int res = is_mul ? term_one * term_two : term_one / term_two;

			// 計算結果を置き換える
			exp = exp.substring(0, idx_term_start_one) + res + exp.substring(idx_term_end_two + 1, len);
		}
		return exp;
	}

	private static int next_idx (String exp) {
		int idx_mul = exp.indexOf('*');
		if (idx_mul == -1) idx_mul = Integer.MAX_VALUE;

		int idx_div = exp.indexOf('/');
		if (idx_div == -1) idx_div = Integer.MAX_VALUE;

		int ret = Math.min(idx_mul, idx_div);
		return ret == Integer.MAX_VALUE ? -1 : ret;
	}

	public static boolean is_digit (char c) {
		return '0' <= c && c <= '9';
	}
}
