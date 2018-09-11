package org.twitter4j.service;

import org.junit.Test;

import twitter4j.TwitterException;

public class SearchTest {

	@Test
	public void search_tweet_test() {
		try {
			Main.searchTweetsOf("lanceptrck");
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}

}
