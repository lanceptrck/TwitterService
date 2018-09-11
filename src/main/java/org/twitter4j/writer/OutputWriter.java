package org.twitter4j.writer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class OutputWriter {

	public static void write(String path, List<String> data) throws IOException {

		PrintWriter writer = new PrintWriter(path, "UTF-8");

		for (int i = 0; i < data.size(); i++)
			writer.println(data.get(i));

		writer.close();

	}

}