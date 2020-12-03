package days;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day02 {

	public static void main(String[] args) throws IOException {
		List<String> lines = new ArrayList<>();
		BufferedReader reader = new BufferedReader(new FileReader(
				new File("").getAbsolutePath().concat("/src/input/Day02.dat")));
		
		String line;
		while ((line = reader.readLine()) != null) {
			lines.add(line);
		}
		reader.close();
		
		int validPasswords = 0;
		for (String s : lines) {
			String[] split = s.split("-");
			int min = Integer.parseInt(split[0]);
			int max = Integer.parseInt(split[1].split(" ")[0]);
			
			String[] splitSpace = s.split(" ");
			char character = splitSpace[1].charAt(0);
			String letters = splitSpace[2];
			
			int matches = (int) letters.chars().filter(c -> c == character).count();
			
			if (min <= matches && matches <= max) {
				validPasswords++;
			}
		}
		System.out.println(validPasswords);
		
		validPasswords = 0;
		for (String s : lines) {
			String[] split = s.split("-");
			int pos1 = Integer.parseInt(split[0]) - 1;
			int pos2 = Integer.parseInt(split[1].split(" ")[0]) - 1;
			
			String[] splitSpace = s.split(" ");
			char character = splitSpace[1].charAt(0);
			char[] letters = splitSpace[2].toCharArray();
			
			if ((letters[pos1] == character && letters[pos2] != character)
					|| (letters[pos1] != character && letters[pos2] == character)) {
				validPasswords++;
			}
		}
		System.out.println(validPasswords);
	}
}
