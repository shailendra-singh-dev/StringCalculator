package main.com.seven_shifts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class StringCalculator {

	public int Add(String text) {
		char delimiter = ',';
		if (text.indexOf("//") == 0) {
			delimiter = text.charAt(2);
		}
		return Add(text, delimiter);
	}

	private int Add(String text, char delimiter) {
		if (text == null || text.isEmpty()) {
			return 0;
		}

		List<String> negatives = new ArrayList<String>();
		StringBuilder delimiters = new StringBuilder();
		delimiters.append(delimiter);

		String[] chuncks = null;
		if (text.indexOf("//") != -1) {
			delimiters.append("|");
			delimiters.append("//");
		}
		if (text.indexOf("\n") != -1) {
			delimiters.append("|");
			delimiters.append("\n");
		}
		if (text.indexOf("$") != -1) {
			text = text.replaceAll("\\$", ",");
			delimiters.append("|");
			delimiters.append(",");
		}
		if (text.indexOf("*") != -1) {
			text = text.replaceAll("\\*", ",");
			delimiters.append("|");
			delimiters.append(",");
		}

		chuncks = text.split(delimiters.toString());

		List<String> list = new ArrayList<String>(Arrays.asList(chuncks));
		list.removeAll(Arrays.asList("", null));
		chuncks = list.stream().toArray(String[]::new);

		int total = 0;
		for (String item : chuncks) {
			int num = Integer.parseInt(item);
			if (num < 0) {
				negatives.add(item);
			} else if (num > 1000) {
				continue;
			}
			total += num;
		}
		if (!negatives.isEmpty()) {
			throw new IllegalArgumentException("Negatives not allowed " + String.join(",", negatives));
		}
		return total;
	}
}
