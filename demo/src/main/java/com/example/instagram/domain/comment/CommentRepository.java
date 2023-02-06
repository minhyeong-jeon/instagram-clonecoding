package com.example.instagram.domain.comment;


import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer>{
	
	/*
	 * @Query(value =
	 * "INSERT INTO comment(content, imageId, userId, createDate) VALUES(:content,:imageId,:userId,now())"
	 * , nativeQuery = true) int mSave(String content, int imageId, int userId);
	 */
	
}