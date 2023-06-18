package com.calsoft.pos.model.reviewrating;
/*
 * package com.calsoft.pos.model;
 * 
 * import java.io.Serializable;
 * 
 * import javax.persistence.Column; import javax.persistence.Entity; import
 * javax.persistence.GeneratedValue; import javax.persistence.GenerationType;
 * import javax.persistence.Id; import javax.persistence.JoinColumn; import
 * javax.persistence.OneToOne; import javax.persistence.Table;
 * 
 * import com.fasterxml.jackson.annotation.JsonBackReference;
 * 
 * @Entity
 * 
 * @Table(name = "review_entity_summary") public class ReviewEntitySummary
 * implements Serializable {
 * 
 * private static final long serialVersionUID = -3009157732242241606L;
 * 
 * @Id
 * 
 * @GeneratedValue(strategy = GenerationType.IDENTITY)
 * 
 * @Column(name = "primary_id") private int primaryId;
 * 
 * 
 * @Column(name = "entity_pk_value") private int entityPkValue;
 * 
 * @Column(name = "entity_type") private int entityType;
 * 
 * @Column(name = "reviews_count") private int reviewsCount;
 * 
 * @Column(name = "rating_summary") private int ratingSummary;
 * 
 * @Column(name = "store_id") private int storeId;
 * 
 * @JsonBackReference
 * 
 * @OneToOne
 * 
 * @JoinColumn(name = "entity_pk_value") private Review review;
 * 
 * public int getPrimaryId() { return primaryId; }
 * 
 * public void setPrimaryId(int primaryId) { this.primaryId = primaryId; }
 * 
 * 
 * 
 * public int getEntityType() { return entityType; }
 * 
 * public void setEntityType(int entityType) { this.entityType = entityType; }
 * 
 * public int getReviewsCount() { return reviewsCount; }
 * 
 * public void setReviewsCount(int reviewsCount) { this.reviewsCount =
 * reviewsCount; }
 * 
 * public int getRatingSummary() { return ratingSummary; }
 * 
 * public void setRatingSummary(int ratingSummary) { this.ratingSummary =
 * ratingSummary; }
 * 
 * public int getStoreId() { return storeId; }
 * 
 * public void setStoreId(int storeId) { this.storeId = storeId; }
 * 
 * public Review getReview() { return review; }
 * 
 * public void setReview(Review review) { this.review = review; }
 * 
 * }
 */