import java.util.LinkedList;
import java.util.Queue;

/**
 * @author hangn
 *
 *行きは右か下、帰りは左か上、と進めば必ず最短ステップでスタートまたはゴールに着くため、
 *ステップ数を数える必要がない
 *
 */

public class Q31 {

	static final int TARGET = 6;

	public static void main (String[] args) {

		int ans = 0;

		int[] dx_go   = { 1,  0};
		int[] dy_go   = { 0,  1};
		int[] dx_back = {-1,  0};
		int[] dy_back = { 0, -1};

		Queue<Integer> queue_x = new LinkedList<>();
		Queue<Integer> queue_y = new LinkedList<>();
		Queue<Boolean> queue_is_back = new LinkedList<>();
		Queue<String>  queue_through_road = new LinkedList<>();

		queue_x.add(0);
		queue_y.add(0);
		queue_is_back.add(false);
		queue_through_road.add("");

		while (!queue_x.isEmpty()) {
			int x = queue_x.poll();
			int y = queue_y.poll();
			boolean is_back = queue_is_back.poll();
			String through_road = queue_through_road.poll();

			// スタートにいる、かつ、復路である
			if (x == 0 && y == 0 && is_back) {
				ans++;
				continue;
			}

			for (int i = 0; i < 2; i++) {
				int x_next = x + (is_back ? dx_back[i] : dx_go[i]);
				int y_next = y + (is_back ? dy_back[i] : dy_go[i]);

				// 外に飛び出していないか
				if (x_next < 0 || x_next > TARGET || y_next < 0 || y_next > TARGET) continue;

				// 既に通った経路でないか
				String road_next_rep1 = x_next + ":" + y_next + "->" + x + ":" + y;
				String road_next_rep2 = x + ":" + y + "->" + x_next + ":" + y_next;
				if (through_road.indexOf(road_next_rep1) != -1 ||
						through_road.indexOf(road_next_rep2) != -1) continue;

				queue_x.add(x_next);
				queue_y.add(y_next);
				queue_is_back.add(is_back ? true : x_next == TARGET && y_next == TARGET);
				queue_through_road.add(through_road + "," + road_next_rep1 + "," + road_next_rep2);
			}
		}

		System.out.println(ans);

	}

}