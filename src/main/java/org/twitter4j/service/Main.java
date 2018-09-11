package org.twitter4j.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.twitter4j.writer.OutputWriter;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class Main {

	public static void searchTweetsOf(String user) throws TwitterException {
		Twitter twitter = new TwitterFactory().getInstance();
		String string_date = "01-January-2018";
		SimpleDateFormat f = new SimpleDateFormat("dd-MMM-yyyy");
		String filename = user + ".txt";
		ArrayList<String> tweetList = new ArrayList<String>();

		try {
			List<Status> statuses;

			Date d = f.parse(string_date);

			if (user.length() > 0) {

				statuses = twitter.getUserTimeline(user, new Paging(1, 100, d.getTime()));
			} else {
				user = twitter.verifyCredentials().getScreenName();
				statuses = twitter.getUserTimeline();
			}
			System.out.println("Showing @" + user + "'s user timeline.");
			for (Status status : statuses) {
				if (!status.isRetweet()) {
					String filteredTweet = filter(status.getText());
					System.out.println("@" + status.getUser().getScreenName() + " - " + filteredTweet);
					tweetList.add(filteredTweet);
				}

			}
		} catch (TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to get timeline: " + te.getMessage());
			System.exit(-1);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		try {
			OutputWriter.write(filename, tweetList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static String filter(String text) {
		StringBuilder sb = new StringBuilder();
		String[] stringArr = text.split(" ");

		for (int i = 0; i < stringArr.length; i++) {
			if (stringArr[i].contains("@") || stringArr[i].contains("http")) {
				stringArr[i] = "";
			}

			if (!stringArr[i].isEmpty())
				sb.append(stringArr[i] + " ");

		}

		return sb.toString();

	}

}
