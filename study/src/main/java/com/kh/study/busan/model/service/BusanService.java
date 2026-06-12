package com.kh.study.busan.model.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.kh.study.busan.model.dao.ReviewMapper;
import com.kh.study.busan.model.dto.ReviewDto;
import com.kh.study.busan.model.dto.UpdateReviewDto;

@Service
public class BusanService {
	
	@Autowired
	private ReviewMapper mapper;

	public String getRes(int page) {
		String url = "https://apis.data.go.kr/6260000/FoodService/getFoodKr?serviceKey=b6c2571f90cab25dbe940e4ff55342124063366590f7313bd991d224d79dc039&numOfRows=6&resultType=json";
		url += "&pageNo=" + page;
		
		URI uri = null;
		
		try {
			uri = new URI(url);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		String apiResponse = new RestTemplate().getForObject(uri, String.class);
		return apiResponse;
	
		
		
	}

	public String getDetail(int seq) {
		String url = "https://apis.data.go.kr/6260000/FoodService/getFoodKr?serviceKey=b6c2571f90cab25dbe940e4ff55342124063366590f7313bd991d224d79dc039&numOfRows=1&resultType=json";
		url += "&UC_SEQ=" + seq;
		
		URI uri = null;
		
		try {
			uri = new URI(url);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		String apiResponse = new RestTemplate().getForObject(uri, String.class);
		return apiResponse;
	
	}

	public void save(Long ucSeq, ReviewDto review) {
		review.setUcSeq(ucSeq);
		mapper.save(review);
	}

	public List<ReviewDto> findBySeq(Long ucSeq) {

		return mapper.findBySeq(ucSeq);
	}

	public void update(Long ucSeq, UpdateReviewDto urd) {
		//urd.setUcSeq(ucSeq);
		
		
	}

	public void delete(Long ucSeq, UpdateReviewDto urd) {
		// TODO Auto-generated method stub
		
	}

	
}
