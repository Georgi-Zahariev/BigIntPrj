import java.util.ArrayList;

public class BigInt {
	private final static int mil = 1000000000; // Основа на бройната система
	// Динамичен масив от "цифри"
	private ArrayList<Integer> dig = new ArrayList<Integer>();
	// Брой "цифри" в масива
	private int cnt;

	// Празен конструктор: създава числото 0
	public BigInt() {
		dig.add(0);
		cnt = 1;
	}

	// Конструктор от низ
	public BigInt(String s) {
		if (s.length() < 10) { // Ако се събира в една "цифра"
			dig.add(Integer.parseInt(s));
			cnt = 1;
			return;
		}
		// Повече от една "цифра"
		cnt = 0;
		int i;
		for (i = s.length() - 9; i >= 0; i -= 9) { // Всеки 9 символа са една "цифра"
			dig.add(Integer.parseInt(s.substring(i, i + 9)));
			cnt++;
		}
		// Тук i<0
		i += 9; // Колко символа остава да се добавят?
		if (i > 0) { // Ако са повече от нула, добавяме и тях
			dig.add(Integer.parseInt(s.substring(0, i)));
			cnt++;
		}
	}

	public BigInt add(BigInt a) {
		BigInt r = new BigInt(); // Резултат от събирането (засега 0 с една "цифра")
		int carry, d; // Пренос от предишен разред и резултат от сума на две "цифри"
		d = dig.get(0) + a.dig.get(0); // Сума на най-младшите две "цифри"
		// Ако не е по-малка от основата на бройната система - установяваме пренос 1
		if (d >= mil) {
			d -= mil;
			carry = 1;
		} else
			carry = 0; // иначе пренос 0
		r.dig.set(0, d); // Променяме нулевата цифра на резултата
		for (int i = 1; i < cnt && i < a.cnt; i++) { // Докато има "цифри" за събиране
			d = carry; // Пренос от предишния разред
			if (i < cnt)
				d += dig.get(i); // Ако има i-та "цифра" в this, добавяме я
			if (i < a.cnt)
				d += a.dig.get(i);// Ако има i-та "цифра" в a, добавяме я
			// Ще има ли пренос?
			if (d >= mil) {
				d -= mil;
				carry = 1;
			} else
				carry = 0;
			r.dig.add(d);// Добавяме следваща "цифра" към резултата
			r.cnt++;
		}
		// Ако след сумирането на всички "цифри" има пренос
		if (carry > 0) {
			r.dig.add(1);
			r.cnt++;
		}
		return r;
	}

	@Override
	public String toString() {
		// Най-старшата "цифра" е неформатирана
		String s = dig.get(cnt - 1).toString();
		// Останалите са допълнени с 0 до 9 символа
		for (int i = cnt - 2; i >= 0; i--)
			s += String.format("%09d", dig.get(i));
		return s;
	}
}
