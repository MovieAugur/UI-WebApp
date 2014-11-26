package com.ufl.augur;

public class MovieMetrics {

	private String movieName;
	private String boxOfficeEarnings;
	private String rtRating;
	private String youtubeViews;
	private String popularity;
	private String imageName;
	
	public MovieMetrics(String movieName, String boxOfficeEarnings,
			String rtRating, String youtubeViews, String popularity) {
		super();
		this.movieName = movieName;
		this.boxOfficeEarnings = boxOfficeEarnings;
		this.rtRating = rtRating;
		this.youtubeViews = youtubeViews;
		this.popularity = popularity;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getBoxOfficeEarnings() {
		return boxOfficeEarnings;
	}

	public void setBoxOfficeEarnings(String boxOfficeEarnings) {
		this.boxOfficeEarnings = boxOfficeEarnings;
	}

	public String getRtRating() {
		return rtRating;
	}

	public void setRtRating(String rtRating) {
		this.rtRating = rtRating;
	}

	public String getYoutubeViews() {
		return youtubeViews;
	}

	public void setYoutubeViews(String youtubeViews) {
		this.youtubeViews = youtubeViews;
	}

	public String getPopularity() {
		return popularity;
	}

	public void setPopularity(String popularity) {
		this.popularity = popularity;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
}
