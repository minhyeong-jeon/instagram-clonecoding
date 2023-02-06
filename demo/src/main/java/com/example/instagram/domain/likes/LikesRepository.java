package com.example.instagram.domain.likes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface LikesRepository extends JpaRepository<Likes, Integer>{
	
	@Modifying // 삭제하는것은 @Modifying 붙여줘야함
	@Query(value = "INSERT INTO likes(imageId, userId) VALUES(:imageId, :principalId)", nativeQuery = true)
	int mLike(int imageId, int principalId);
	
	@Modifying
	@Query(value = "DELETE FROM likes WHERE imageId = :imageId AND userId = :principalId", nativeQuery = true)
	int mUnLike(int imageId, int principalId);
}