package days;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day04 {
	
	public static void main(String[] args) throws IOException {
		List<StringBuffer> lines = new ArrayList<>();
		BufferedReader reader = new BufferedReader(new FileReader(
				new File("").getAbsolutePath().concat("/src/input/Day04.txt")));
		
		String line;
		while ((line = reader.readLine()) != null) {
			lines.add(new StringBuffer(line).append(' '));
		}
		reader.close();
		
		int previousPassportIndex = 0;
		List<StringBuffer> adjusted = new ArrayList<>();
		for (StringBuffer sb : lines) {
			if (sb.charAt(0) == ' ' || lines.indexOf(sb) == lines.size() - 1) {
				adjusted.add(lines.get(previousPassportIndex));
				int index = adjusted.indexOf(lines.get(previousPassportIndex));
				for (int i = previousPassportIndex + 1; i < lines.indexOf(sb); i++) {
					adjusted.get(index).append(lines.get(i));
				}
				previousPassportIndex = lines.indexOf(sb);
				if (lines.indexOf(sb) == lines.size() - 1)
					adjusted.get(index).append(lines.get(lines.size() - 1));
			}
		}
		
		for (int i = 0; i < adjusted.size(); i++) {
			if (adjusted.get(i).charAt(0) == ' ')
				adjusted.get(i).deleteCharAt(0);
			if (adjusted.get(i).charAt(adjusted.get(i).length() - 1) == ' ')
				adjusted.get(i).deleteCharAt(adjusted.get(i).length() - 1);
		}
		
		int validPassports = 0;
		lines:
		for (StringBuffer sb : adjusted) {
			String passport = sb.toString();
			String[] fields = passport.split(" ");
			if (fields.length == 8 && checkForValidity(fields)) {
				validPassports++;
			} else if (fields.length == 7) {
				for (int i = 0; i < fields.length; i++) {
					if (fields[i].startsWith("cid")) {
						continue lines;
					}
				}
				if (checkForValidity(fields))
					validPassports++;
			}
		}
		
		adjusted.forEach(sb -> System.out.println(sb));
		System.out.println(validPassports);
	}
	
	private static boolean checkForValidity(String[] fields) {
		int valid = 0;
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].startsWith("byr")) {
				int byr = Integer.parseInt(fields[i].substring(4));
				if (1920 <= byr && byr <= 2002)
					valid++;
			} else if (fields[i].startsWith("iyr")) {
				int iyr = Integer.parseInt(fields[i].substring(4));
				if (2010 <= iyr && iyr <= 2020)
					valid++;
			} else if (fields[i].startsWith("eyr")) {
				int eyr = Integer.parseInt(fields[i].substring(4));
				if (2020 <= eyr && eyr <= 2030)
					valid++;
			} else if (fields[i].startsWith("hgt")) {
				if (fields[i].charAt(fields[i].length() - 1) == 'n') {
					int hgt = Integer.parseInt(fields[i].substring(4, fields[i].length() - 2));
					if (59 <= hgt && hgt <= 76)
						valid++;
				} else if (fields[i].charAt(fields[i].length() - 1) == 'm') { // centimeters
					int hgt = Integer.parseInt(fields[i].substring(4, fields[i].length() - 2));
					if (150 <= hgt && hgt <= 193)
						valid++;
				}
			} else if (fields[i].startsWith("hcl")) {
				String hex = fields[i].substring(4);
				if (hex.startsWith("#") && hex.length() == 7) {
					String sub = hex.substring(1);
					char[] array = sub.toCharArray();
					int validChars = 0;
					for (int j = 0; j < array.length; j++) {
						if (('0' <= array[j] && array[j] <= '9')
								|| ('a' <= array[j] && array[j] <= 'f'))
							validChars++;
					}
					if (validChars == array.length)
						valid++;
				}
			} else if (fields[i].startsWith("ecl")) {
				String ecl = fields[i].substring(4);
				if (ecl.equals("amb") || ecl.equals("blu") || ecl.equals("brn") || ecl.equals("gry") || ecl.equals("grn") || ecl.equals("hzl") || ecl.equals("oth")) {
					valid++;
				}
			} else if (fields[i].startsWith("pid")) {
				String sub = fields[i].substring(4);
				if (sub.length() == 9) {
					char[] array = sub.toCharArray();
					int numeric = 0;
					for (int j = 0; j < array.length; j++) {
						if (Character.isDigit(array[j]))
							numeric++;
					}
					if (numeric == array.length)
						valid++;
				}
			}
		}
		if (valid == 7)
			return true;
		return false;
	}

}
