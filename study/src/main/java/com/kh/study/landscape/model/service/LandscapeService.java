package com.kh.study.landscape.model.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LandscapeService {

//	@Autowired
//	private landscapeMapper mapper;
	
	public String getRes(int page) {
		String url = "https://apis.data.go.kr/B551011/PhokoAwrdService/phokoAwrdList?serviceKey=b6c2571f90cab25dbe940e4ff55342124063366590f7313bd991d224d79dc039&numOfRows=10&MobileOS=WIN&MobileApp=AppTest&arrange=Q&_type=json";
		url += "&pageNo=" +page;
		
		URI uri = null;
		
		try {
			uri=new URI(url);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String apiResponse = new RestTemplate().getForObject(uri, String.class);
		return apiResponse;
	}

	public String getDetail(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
