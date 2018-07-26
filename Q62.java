import java.util.HashSet;
import java.util.Set;

public class Q62 {

	static final int WIDTH  = 5;
	static final int HEIGHT = 4;
	static final int MAX = WIDTH * HEIGHT;
	static int ans = 0;
	static Set<Integer> memo = new HashSet<>();
	static int[] dx = {0,0,-1,1};
	static int[] dy = {-1,1,0,0};

	public static void main (String[] args) {
		long s = System.currentTimeMillis();

		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				memo.clear();
				memo.add(j<<3|i);
				solve(j, i, 1);
			}
		}

		System.out.println(ans/2);
		long e = System.currentTimeMillis();
		System.out.println("time:" + (e-s) + "ms");
	}

	static void solve (int x, int y, int cnt) {
		if (cnt == MAX) {
			ans++;
			return;
		}

		for (int di = 0; di < 4; di++) {
			int nx = x + dx[di];
			int ny = y + dy[di];
			if (nx < 0 || nx >= WIDTH || ny < 0 || ny >= HEIGHT) continue;
			int val = nx << 3 | ny;
			if (memo.contains(val)) continue;
			memo.add(val);
			//solve(nx, ny, cnt + 1, path+","+val);
			solve(nx, ny, cnt + 1);
			memo.remove(val);
		}
	}

	static void debug (String path) {
		System.out.println("debug:"+path);
		String[] paths = path.split(",");
		int[] pass_x = new int[MAX];
		int[] pass_y = new int[MAX];
		char[][] map = new char[HEIGHT][WIDTH];

		for (int i = 0; i < MAX; i++) {
			int p = Integer.parseInt(paths[i]);
			pass_x[i] = p >> 3;
			pass_y[i] = p & 0b111;
		}

		map[pass_y[0]][pass_x[0]] = 'S';
		map[pass_y[MAX - 1]][pass_x[MAX - 1]] = 'E';

		int bx = pass_x[1];
		int by = pass_y[1];
		for (int i = 1; i < MAX - 1; i++) {
			int x = pass_x[i+1];
			int y = pass_y[i+1];
			int sub_x = x - bx;
			int sub_y = y - by;
			System.out.println("("+bx+","+by+")->("+x+","+y+")");
			if (sub_x == 1)
				map[by][bx] = '→';
			else if (sub_x == -1)
				map[by][bx] = '←';
			else if (sub_y == 1)
				map[by][bx] = '↓';
			else
				map[by][bx] = '↑';
			bx = x;
			by = y;
		}

		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
}
