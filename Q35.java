import java.util.LinkedList;
import java.util.Queue;

public class Q35 {

	public static void main (String[] args) {

		final int SIZE = 6;

//		int[][] dp = new int[SIZE + 1][SIZE + 1];
//		for (int i = 0; i <= SIZE; i++) {
//			dp[0][i] = 1;
//			dp[i][0] = 1;
//		}
//		for (int i = 1; i <= SIZE; i++) {
//			for (int j = 1; j <= SIZE; j++)
//				dp[i][j] = dp[i-1][j] + dp[i][j-1];
//		}
//
//		for (int i = 0; i <= SIZE; i++) {
//			System.out.println(Arrays.toString(dp[i]));
//		}
//		System.out.println();

		Queue<Integer> q_pos_x_a = new LinkedList<>();
		Queue<Integer> q_pos_y_a = new LinkedList<>();
		Queue<Integer> q_pos_x_b = new LinkedList<>();
		Queue<Integer> q_pos_y_b = new LinkedList<>();
		Queue<Integer> q_cnt_nearmiss = new LinkedList<>();
		Queue<Boolean> q_is_meet = new LinkedList<>();

		int ans = 0;
		int[] dx_a = { 1,  0};
		int[] dy_a = { 0,  1};
		int[] dx_b = {-1,  0};
		int[] dy_b = { 0, -1};

		q_pos_x_a.add(0);
		q_pos_y_a.add(0);
		q_pos_x_b.add(SIZE);
		q_pos_y_b.add(SIZE);
		q_cnt_nearmiss.add(0);
		q_is_meet.add(false);

		while (!q_pos_x_a.isEmpty()) {

			int x_a = q_pos_x_a.poll();
			int y_a = q_pos_y_a.poll();
			int x_b = q_pos_x_b.poll();
			int y_b = q_pos_y_b.poll();
			int cnt_nearmiss = q_cnt_nearmiss.poll();
			boolean is_meet = q_is_meet.poll();

			if ((cnt_nearmiss >= 2 || is_meet) && x_a == SIZE && y_a == SIZE && x_b == 0 && y_b == 0) {
				//System.out.println("A:(" + x_a+","+y_a + ") B:(" + x_b+","+y_b+")");
				//ans += dp[SIZE - y_a][SIZE - x_a] * dp[SIZE - y_b][SIZE - x_b];
				ans++;
				continue;
			}

			for (int ia = 0; ia < 2; ia++) {
				int x_a_next = x_a + dx_a[ia];
				int y_a_next = y_a + dy_a[ia];
				if (x_a_next < 0 || x_a_next > SIZE || y_a_next < 0 || y_a_next > SIZE) continue;
				for (int ib = 0; ib < 2; ib++) {
					int x_b_next = x_b + dx_b[ib];
					int y_b_next = y_b + dy_b[ib];
					if (x_b_next < 0 || x_b_next > SIZE || y_b_next < 0 || y_b_next > SIZE) continue;

					q_pos_x_a.add(x_a_next);
					q_pos_y_a.add(y_a_next);
					q_pos_x_b.add(x_b_next);
					q_pos_y_b.add(y_b_next);

					boolean is_equals_x = x_a_next == x_b_next;
					boolean is_equals_y = y_a_next == y_b_next;
					q_cnt_nearmiss.add(cnt_nearmiss + (is_equals_x | is_equals_y ? 1 : 0));
					q_is_meet.add(is_meet ? true : is_equals_x & is_equals_y);
				}
			}

		}

		System.out.println(ans);
	}
}
