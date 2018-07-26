import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Q39 {

	static final int SIZE = 4;
	static final int SQ_SIZE = SIZE * SIZE;

	public static void main (String[] args) {
		int ans = 0;

		Set<Integer> set_status_board_alreay_searched = new HashSet<>();
		Queue<Integer> q_status_board = new LinkedList<>();
		Queue<Integer> q_cnt = new LinkedList<>();

		q_status_board.add(0);
		q_cnt.add(0);

		while (!q_cnt.isEmpty()) {
			int status_board = q_status_board.poll();
			int cnt = q_cnt.poll();

			ans = Math.max(ans, cnt);

			for (int y = 0; y < SIZE; y++) {
				for (int x = 0; x < SIZE; x++) {
					boolean[][] board = int_to_board(status_board);
					boolean[][] rev_board = reverse(board, y, x);
					int status_board_rev = board_to_int(rev_board);
					if (set_status_board_alreay_searched.contains(status_board_rev)) continue;
					set_status_board_alreay_searched.add(status_board_rev);

					q_status_board.add(status_board_rev);
					q_cnt.add(cnt + 1);
				}
			}
		}

		System.out.println(ans);
	}

	public static boolean[][] int_to_board (int n) {
		boolean[][] ret = new boolean[SIZE][SIZE];
		String str = Integer.toBinaryString(n);
		while (str.length() < SQ_SIZE) str = "0" + str;
		char[] c = str.toCharArray();
		int ci = 0;
		for (int j = 0; j < SIZE; j++) {
			for (int k = 0; k < SIZE; k++) {
				ret[j][k] = c[ci++] == '1';
			}
		}
		return ret;
	}

	public static int board_to_int (boolean[][] board) {
		String str = "";
		for (int i = 0; i < SQ_SIZE; i++) {
			str += board[i / SIZE][i % SIZE] ? "1" : "0";
		}
		return Integer.parseInt(str, 2);
	}

	public static boolean[][] reverse (boolean[][] board, int y, int x) {
		boolean[][] ret = copy(board);

		// 反転
		for (int i = 0; i < SIZE; i++) {
			ret[y][i] = !ret[y][i];
			ret[i][x] = !ret[i][x];
		}
		ret[y][x] = !ret[y][x];

		return ret;
	}

	public static boolean[][] copy (boolean[][] ary) {
		boolean[][] ret = new boolean[SIZE][SIZE];
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				ret[i][j] = ary[i][j];
			}
		}
		return ret;
	}

	public static void debug (boolean[][] board) {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				System.out.print(board[i][j] ? "■" : "□");
			}
			System.out.println();
		}
		System.out.println();
	}

}
