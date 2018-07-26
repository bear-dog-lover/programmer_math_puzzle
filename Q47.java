import java.util.HashMap;
import java.util.Map;

public class Q47 {

	static final int TARGET = 4;
	static final int SQ_TARGET = TARGET * TARGET;
	static final int LIM = 1 << (SQ_TARGET);

	public static void main (String[] args) {

//		String test_bs = "10010";
//		System.out.println(test_bs = padding(test_bs, SQ_TARGET));
//		System.out.println(get_bit_info(to_map(test_bs)));
//		System.exit(0);

		Map<String, Integer> seq = new HashMap<String, Integer>();
		for (int i = 0; i < LIM; i++) {
			// 2進数に変換し、16桁になるようパディング
			String i_bs = padding(Integer.toBinaryString(i), SQ_TARGET);
			// 〇✖マップに変換
			boolean[][] matrix = to_map(i_bs);
			// 各行・列の〇の個数を文字列形式で取得
			String bit_info = get_bit_info(matrix);
			// 一度表れたパターンであるか判定
			if (seq.containsKey(bit_info)) {
				seq.put(bit_info, seq.get(bit_info) + 1);
				continue;
			}
			seq.put(bit_info, 1);
		}

		int ans = 0;
		for (String key : seq.keySet())
			if (seq.get(key) == 1) ans++;

		System.out.println(ans);
	}

	static String padding (String str, int len) {
		if (str.length() >= len) return str;
		StringBuilder sb = new StringBuilder(str);

		while (sb.length() < len)
			sb.insert(0, '0');

		return sb.toString();
	}

	static boolean[][] to_map (String bs) {
		char[] c = bs.toCharArray();
		boolean[][] ret = new boolean[TARGET][TARGET];

		int cnt = 0;
		for (int i = 0; i < TARGET; i++) {
			for (int j = 0; j < TARGET; j++)
				ret[i][j] = c[cnt++] == '1';
		}

		return ret;
	}

	static String get_bit_info (boolean[][] matrix) {
		StringBuilder row_info = new StringBuilder();
		StringBuilder col_info = new StringBuilder();

		for (int i = 0; i < TARGET; i++) {
			// 行をカウント
			int cnt_bit_row = 0;
			for (int ri = 0; ri < TARGET; ri++)
				if (matrix[i][ri]) cnt_bit_row++;
			row_info.append(cnt_bit_row);
			// 列をカウント
			int cnt_bit_col = 0;
			for (int ci = 0; ci < TARGET; ci++)
				if (matrix[ci][i]) cnt_bit_col++;
			col_info.append(cnt_bit_col);
		}
		return row_info.append(col_info).toString();
	}

	static void debug (boolean[][] map) {
		for (int i = 0; i < TARGET; i++) {
			for (int j = 0; j < TARGET; j++)
				System.out.print(map[i][j] ? "〇" : "✖");
			System.out.println();
		}
	}
}
