package days;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day01 {
	
	public static void main(String[] args) throws IOException {
		List<Integer> list = new ArrayList<>();
		BufferedReader reader = new BufferedReader(new FileReader(new File("").getAbsolutePath().concat("/src/input/Day01.dat")));
		String line;
		while ((line = reader.readLine()) != null) {
			list.add(Integer.parseInt(line));
		}
		reader.close();
		Integer[] array = list.toArray(new Integer[list.size()]);
		
		outer:
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length; j++) {
				if (i == j) continue;
				if (array[i] + array[j] == 2020) {
					System.out.println(array[i] * array[j]);
					break outer;
				}
			}
		}
		
		outer:
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length; j++) {
				for (int k = 0; k < array.length; k++) {
					if (i == j || j == k || i == k) continue;
					if (array[i] + array[j] + array[k] == 2020) {
						System.out.println(array[i] * array[j] * array[k]);
						break outer;
					}
				}
			}
		}
	}
}
