
package com.calsoft.pos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.reviewrating.RatingOptionVote;


@Repository
public interface RatingOptionVoteJpaRepository extends JpaRepository<RatingOptionVote, Long> {

	public List<RatingOptionVote> findByCustomerIdAndEntityPkValue(int customerId, long entityPkValue);

	@Query(value = "SELECT new  com.calsoft.pos.model.reviewrating.RatingOptionVote(u.entityPkValue,SUM(u.value)/count(*),count(*)) FROM RatingOptionVote u LEFT JOIN Review b ON u.reviewId=b.reviewId  where u.entityPkValue=?1 and b.statusId=?2 \r\n" + 
			"  group by u.entityPkValue")
	RatingOptionVote findByRatingOptionVoteEntityPkValue(Long entityPkValue,int statusId);

//	@Query(value = "SELECT new  com.calsoft.pos.model.reviewrating.RatingOptionVote(u.entityPkValue,count(*)) FROM RatingOptionVote u where u.entityPkValue=?1")
//	List<RatingOptionVote> findByRatingOptionVoteEntityPkValue(long entityPkValue);

}
