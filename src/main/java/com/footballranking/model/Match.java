package com.footballranking.model;

public class Match {

		protected int match_id;
		protected int home_team_id;
		protected int away_team_id;
		protected int home_team_score;
		protected int away_team_score;
		protected String match_date;

		
		public Match() {
		}

		public Match(int match_id, int home_team_id, int away_team_id, int home_team_score, int away_team_score, String match_date) {
			super();
			this.match_id = match_id;
			this.home_team_id = home_team_id;
			this.away_team_id = away_team_id;
			this.home_team_score = home_team_score;
			this.away_team_score = away_team_score;
			this.match_date = match_date;
		}

		
		public Match(int home_team_id, int away_team_id, int home_team_score, int away_team_score, String match_date) {
			super();
			this.home_team_id = home_team_id;
			this.away_team_id = away_team_id;
			this.home_team_score = home_team_score;
			this.away_team_score = away_team_score;
			this.match_date = match_date;
		}

		
		public int getMatch_id() {
			return match_id;
		}


		public void setMatch_id(int match_id) {
			this.match_id = match_id;
		}


		public int getHome_team_id() {
			return home_team_id;
		}


		public void setHome_team_id(int home_team_id) {
			this.home_team_id = home_team_id;
		}


		public int getAway_team_id() {
			return away_team_id;
		}


		public void setAway_team_id(int away_team_id) {
			this.away_team_id = away_team_id;
		}


		public int getHome_team_score() {
			return home_team_score;
		}


		public void setHome_team_score(int home_team_score) {
			this.home_team_score = home_team_score;
		}


		public int getAway_team_score() {
			return away_team_score;
		}


		public void setAway_team_score(int away_team_score) {
			this.away_team_score = away_team_score;
		}


		public String getMatch_date() {
			return match_date;
		}


		public void setMatch_date(String match_date) {
			this.match_date = match_date;
		}

		
}
