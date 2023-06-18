package com.calsoft.pos.model.reviewrating;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Transient;

public class RatingReview {

	@Transient
	private List<RatingOptionVote> ratingOptionVote = new ArrayList<RatingOptionVote>();

	@Transient
	private Review review;

	@Transient
	private String operationType;

	public List<RatingOptionVote> getRatingOptionVote() {
		return ratingOptionVote;
	}

	public void setRatingOptionVote(List<RatingOptionVote> ratingOptionVote) {
		this.ratingOptionVote = ratingOptionVote;
	}

	public Review getReview() {
		return review;
	}

	public void setReview(Review review) {
		this.review = review;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

}
