package niuke_ml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;;

public class lesson1 {
	private static void printHashtable(Hashtable<Integer, HashSet> hashtable) {
		Iterator iter = hashtable.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			Integer user = (Integer) entry.getKey();
			HashSet movies = (HashSet) entry.getValue();
			System.out.print("User:" + user+"\t");
			for (Iterator iterator = movies.iterator(); iterator.hasNext();) {
				System.out.printf("%s\t", iterator.next());
			}
			System.out.println();
		}
		System.out.println("--------------------------");
	}

	private static void addNewLine(Hashtable<Integer, HashSet> hashtable, String line) {
		String[] args = line.split("\t");
		String[] users = args[1].split(";");
		Integer movie_id = Integer.valueOf(args[0]);
		for (String user : users) {
			Integer user_id = Integer.valueOf(user);
			if (hashtable.containsKey(user_id)) {// 用户已经存在
				hashtable.get(user_id).add(movie_id);
			} else {
				HashSet hashset = new HashSet();
				hashset.add(movie_id);
				hashtable.put(user_id, hashset);
			}
		}
	}

	public static void main(String args[]) {
		Hashtable<Integer, HashSet> hashtable = new Hashtable<Integer, HashSet>();
//		String input = "1000079	30;45;28;46;83";
//		test(hashtable, input);
//		printHashtable(hashtable);
//
//		test(hashtable, "1001057	30;17");
//		printHashtable(hashtable);
		try {
			File filename = new File("/home/hadoop/workshop/lesson1/lesson1/sample-input"); //
			InputStreamReader reader = new InputStreamReader(new FileInputStream(filename)); // 建立一个输入流对象reader
			BufferedReader br = new BufferedReader(reader);
			String line = "";
			line = br.readLine();
			while (line != null) {
				addNewLine(hashtable, line);
				line = br.readLine(); // 一次读入一行数据
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		printHashtable(hashtable);
	}
}
