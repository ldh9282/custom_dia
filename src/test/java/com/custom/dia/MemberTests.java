package com.custom.dia;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.custom.dia.cmmn.exception.CustomException;
import com.custom.dia.cmmn.model.CustomMap;
import com.custom.dia.login.service.LoginServcie;

@SpringBootTest
public class MemberTests {

	@Autowired
	private LoginServcie loginServcie;
	@Test
	void test01() {
		CustomMap customMap = new CustomMap();
		customMap.put("memId", "admin");
		customMap.put("memPw", "admin");
		try {
			loginServcie.insertMemberDetail(customMap);
		} catch (CustomException e) {
			e.printStackTrace();
		}
	}
}
