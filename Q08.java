

//import static string.util_string.*;

public class Q08 {
	static final int TARGET = 12;
	static final int SIZE = TARGET * 2 + 1;
	static final int D_LEN = 4;
	static int[] dx = { 0, 0,-1, 1};
	static int[] dy = {-1, 1, 0, 0};

	public static void main (String[] args) {
		boolean[][] is_visited = new boolean[SIZE][SIZE];
		is_visited[TARGET][TARGET] = true;
		System.out.println(solve(is_visited, TARGET, TARGET, 0));
	}

	public static int solve (boolean[][] is_visited, int x, int y, int cnt) {
		if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return 0;
		if (cnt == TARGET) return 1;
		int ret = 0;
		for (int di = 0; di < D_LEN; di++) {
			int nx = x + dx[di];
			int ny = y + dy[di];
			if (is_visited[ny][nx]) continue;
			is_visited[ny][nx] = true;
			ret += solve(is_visited, nx, ny, cnt + 1);
			is_visited[ny][nx] = false;
		}
		return ret;
	}
}
