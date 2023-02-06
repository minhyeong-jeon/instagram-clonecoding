package com.example.instagram.domain.likes;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.example.instagram.domain.image.Image;
import com.example.instagram.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Like는 mysql에 안되서 s를 붙여줌
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Likes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="imageId")
	private Image image;
	
	//이미지를 좋아요 눌렀는데 누가 좋아요 했는지 알아야 함
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	@CreationTimestamp
	private Timestamp createDate;
}