package org.zerock.service;

import org.springframework.stereotype.Service;

@Service
public class SampleServiceImpl implements SampleService{

	@Override
	public Integer doAdd(String str1, String str2) throws Exception {
		// TODO Auto-generated method stub
		return Integer.parseInt(str1) + Integer.parseInt(str2);
	}

}
