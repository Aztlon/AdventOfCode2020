package days;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Day 3: Toboggan Trajectory
 * Day 3's objective was to determine how many trees someone would encounter while traversing a certain path on a grid.
 */
public class Day03 {
	
	public static void main(String[] args) throws IOException {
		List<StringBuffer> lines = new ArrayList<>();
		BufferedReader reader = new BufferedReader(new FileReader(
				new File("").getAbsolutePath().concat("/src/input/Day03.dat")));
		
		String line;
		while ((line = reader.readLine()) != null) {
			StringBuffer sb = new StringBuffer(line);
			int length = 323 * 7 / sb.length();
			for (int i = 0; i < length; i++)
				sb.append(line);
			lines.add(sb);
		}
		reader.close();
		
		// Part One
		System.out.println(part1(lines, 3, 1));
		
		// Part Two
		int[][] part2Movements = {
				{ 1, 1 },
				{ 3, 1 },
				{ 5, 1 },
				{ 7, 1 },
				{ 1, 2 }
		};
		System.out.println(part2(lines, part2Movements));
	}
	
	public static int part1(List<StringBuffer> lines, int horizontal, int vertical) {
		int trees = 0;
		int horizontalCounter = 0;
		
		for (int i = 0; i < lines.size(); i += vertical) {
			if (lines.get(i).charAt(horizontalCounter) == '#')
				trees++;
			horizontalCounter += horizontal;
		}
		
		return trees;
	}
	
	public static long part2(List<StringBuffer> lines, int[][] movements) {
		long product = 1;
		for (int i = 0; i < movements.length; i++)
			product *= part1(lines, movements[i][0], movements[i][1]);
		return product;
	}

}
