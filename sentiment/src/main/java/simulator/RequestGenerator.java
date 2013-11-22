package simulator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class RequestGenerator {

	// constants
	private static final int DEFAULT_REQUEST_AMOUNT = 200;
	private static final String DEFAULT_OUTPUT_FILE = "files/simulator/input.txt";
	private static final String DEFAULT_WORDS_FILE = "files/simulator/words.txt";
	private static final int TIMESTAMP_DEVIATION = 100;
	public static final Random RANDOM = new Random();

	private static int[] timestamps;
	private static int timestampCounter = 0;
	private static List<String> words;

	public static void main(String[] args) {
		int amount = RequestGenerator.DEFAULT_REQUEST_AMOUNT;
		String fname = RequestGenerator.DEFAULT_OUTPUT_FILE;

		if (args.length == 2) {
			try {
				amount = Integer.valueOf(args[0]);
				fname = args[1];
			} catch (NumberFormatException e) {
				System.out
						.println("usage: RequestionGenerator [request amount] [outputfile name]");
                System.exit(1);
			}
		}
		generate(amount, fname);
	}

	public static void generate(int amount, String output) {

		FileWriter fw;
		int counter = 0;

		initTimestamps(amount);
		initWords();
		
		System.out.println("starting to generate requests("+amount+")...");
		
		try {
			fw = new FileWriter(output, false);
			while (counter < amount) {
				fw.write(getRequest());
				counter++;
			}
			fw.close();
			System.out.println("done ("+output+")");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	// get next random number
	private static int nextRandom(int max, int min) {
		return (int) Math.round(min + (max - min) * Math.random());
	}

	// get next normal distributed random number
	private static int nextNormalDistributedRandom(int deviation) {
		return (int) Math.abs(Math.round(RANDOM.nextGaussian() * deviation));
	}

	// generate all timestamps of apps and sort them
	private static void initTimestamps(int amount) {
		timestampCounter = 0;
		timestamps = new int[amount];
		for (int i = 0; i < amount; i++) {
			timestamps[i] = nextNormalDistributedRandom(TIMESTAMP_DEVIATION);
		}
		Arrays.sort(timestamps);
	}

	private static void initWords() {
		try {
			File fword = new File(RequestGenerator.DEFAULT_WORDS_FILE);
			Scanner s = new Scanner(fword);
			words = new ArrayList<String>();
			while (s.hasNext()) {
				words.add(s.next());
			}
			s.close();
		} catch (Exception e) {
			System.out.print("failed to init words!");
			System.exit(1);
		}
	}

	// get next timestamp
	private static int nextTimestamp() {
		int ts = timestamps[timestampCounter];
		timestampCounter++;
		return ts;
	}

	private static String nextWord() {
		int index = nextRandom(words.size() - 1, 0);
		return words.get(index);
	}

	private static String getRequest() {
		int timestamp = nextTimestamp();
		String word = nextWord();

		return timestamp + " " + word + System.getProperty("line.separator");
	}

}
