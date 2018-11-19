package org.zeorck.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {
	
	@Select("Select sysdate From dual")
	public String getTime();
}
