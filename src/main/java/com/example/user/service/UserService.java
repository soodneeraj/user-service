package com.example.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.user.VO.Department;
import com.example.user.VO.ResponseTemplateVO;
import com.example.user.entity.User;
import com.example.user.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
		
	public UserRepository getUserRepository() {
		return userRepository;
	}


	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}


	public RestTemplate getRestTemplate() {
		return restTemplate;
	}


	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}


	public User saveUser(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	
	public ResponseTemplateVO getUserWithDepartment(Long userId) {
		// TODO Auto-generated method stub
		ResponseTemplateVO vo=new ResponseTemplateVO();
		User user=userRepository.findByUserId(userId);
		System.out.println("http://localhost:9003/departments/userId==>>"+userId);
		Department department = restTemplate.getForObject("http://localhost:9003/departments/"+user.getDepartmentId() , Department.class);
		//Department department =restTemplate.get
		
		vo.setUser(user); 
		vo.setDepartment(department);
		
		return vo;
	}

}
