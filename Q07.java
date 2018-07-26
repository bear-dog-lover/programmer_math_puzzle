

import static util.util_string.*;

import java.time.LocalDate;

public class Q07 {
	public static void main (String[] args) {
		long s = System.currentTimeMillis();

		LocalDate start = LocalDate.of(1964, 10, 10);
		LocalDate end   = LocalDate.of(2020,  7, 24);
		while (!start.equals(end)) {
			int val = start.getYear() * 10000 +
						start.getMonthValue() * 100 + start.getDayOfMonth();
			if (is_reverse(Integer.toBinaryString(val))) {
				System.out.println(start);
			}
			start = start.plusDays(1);
		}

		long e = System.currentTimeMillis();
		System.out.println("time:" + (e-s) + "ms");
	}
}
