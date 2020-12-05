package days;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Day 5: Binary Boarding
 * Day 5's objective was to find your seat on a plane even though you dropped your boarding pass.
 */
public class Day05 {
	
	public static void main(String[] args) throws Exception {
		Map<String, Integer> seats = new HashMap<>();
		BufferedReader reader = new BufferedReader(new FileReader(
				new File("").getAbsolutePath().concat("/src/input/Day05.txt")));
		
		String line;
		while ((line = reader.readLine()) != null) {
			seats.put(line, 0);
		}
		reader.close();
		
		/*
		 * First time using an Iterator!
		 * I chose to use an Iterator because it allows adding & removing elements while iterating.
		 * This is necessary because at the end of the loop, I replace elements with new values.
		 * Alternatively, I could have put the lines in a List of Strings above, then added
		 * the values to a HashMap below, and then I could just use a regular for each loop.
		 */
		Iterator<String> it = seats.keySet().iterator();
		while (it.hasNext()) {
			String s = it.next();
			int min = 0;
			int max = 127;
			int row, column;
			for (int i = 0; i < 7; i++) {
				if (s.charAt(i) == 'F') {
					max = (int) Math.round((double) (max + min) * 0.5D);
				} else if (s.charAt(i) == 'B') {
					min = (int) Math.round((double) (max + min) * 0.5D);
				}
			}
			row = Math.round((max + min) / 2);
			min = 0;
			max = 7;
			for (int i = 7; i < 10; i++) {
				if (s.charAt(i) == 'L') {
					max = (int) Math.round((double) (max + min) * 0.5D);
				} else if (s.charAt(i) == 'R') {
					min = (int) Math.round((double) (max + min) * 0.5D);
				}
			}
			column = min;
			seats.put(s, (row * 8) + column);
		}
		
		System.out.println(part1(seats));
		System.out.println(part2(seats));
	}
	
	// Get maximum seat ID
	public static int part1(Map<String, Integer> seats) {
		// Pretty handy method for finding the maximum value in a Collection
		return seats.values().stream().mapToInt(i -> i).max().getAsInt();
	}
	
	// Get the missing seat ID (the one that belongs to you)
	public static int part2(Map<String, Integer> seats) {
		for (int id : seats.values()) {
			if (id == part1(seats)) continue;
			/*
			 * Need to put the ++ before the variable because then it uses the incremented value
			 * as the parameter for the containsValue() method. If I used id++ it would use the
			 * old id as the parameter, and THEN increment the value. In that case, the whole
			 * part2 method would return 0 because it would be checking if the Collection we're
			 * looping through contains the very value we're using, which is always true.
			 */
			if (!seats.containsValue(++id))
				return id;
		}
		return 0;
	}

}
