package com.github.jep42.formatcompare;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class UnitTestUtil {


	public static String getFile(String ressource) {
		InputStream is = UnitTestUtil.class.getClassLoader().getResourceAsStream(ressource);
		InputStreamReader inputStreamReader = new InputStreamReader(is);
		return loadFile(inputStreamReader);
	}

	private static String loadFile(Reader reader) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(reader);

		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    return sb.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			closeStream(br);
		}
	}

	private static void closeStream(BufferedReader br) {
		try {
			if (br != null) {
				br.close();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
