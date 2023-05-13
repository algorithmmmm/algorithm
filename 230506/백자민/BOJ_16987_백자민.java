import java.util.Scanner;

public class BOJ_16987_백자민 {
	static int size, result = 0;
	static Egg[] list;
	
	private static void calc() {
		int count = 0;
		for (int i=0; i<size; i++) {
			if (list[i].durabilty <= 0) {
				count++;
			}
		}
		result = Math.max(count, result);
	}

	private static void hit(int pick) {
		calc();
		if (pick == size) {
			return;
		}

		if (list[pick].durabilty>0) {
			for (int target=0; target<size; target++) {
				if (list[target].durabilty>0 && target!=pick) {
					list[pick].durabilty -= list[target].weight;
					list[target].durabilty -= list[pick].weight;
					hit(pick + 1);
					list[pick].durabilty += list[target].weight;
					list[target].durabilty += list[pick].weight;
				}
			}
		} else {
			hit(pick + 1);
		}
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		size = input.nextInt();
		list = new Egg[size];

		for (int i=0; i<size; i++) {
			list[i] = new Egg(input.nextInt(), input.nextInt());
		}

		hit(0);
		System.out.println(result);
	}
	
	private static class Egg {
		int durabilty, weight;

		Egg(int durabilty, int weight) {
			this.durabilty = durabilty;
			this.weight = weight;
		}
	}
}
