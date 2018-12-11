package org.zerock.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class Criteria {
	
	@Setter
	private int PageNum;
	@Setter
	private int amount;

	private int startAmount;
	
	public Criteria() {
		this(1, 10);
	}
	
	public Criteria(int PageNum, int amount) {
		this.PageNum = PageNum;
		this.amount = amount;
	}
	
	public void setStartAmount() {
		this.startAmount =  ((this.PageNum - 1) * this.amount) + 1; 
	}
}
