package org.zerock.sample;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.config.RootConfig;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfig.class })
@Log4j
public class HotelTests {

	@Setter(onMethod_ = { @Autowired })
	private SampleHotel hotel;

	@Test
	public void testExist() {

		assertNotNull(hotel);

		log.info(hotel);
		log.info("-------------------------------");
		log.info(hotel.getChef());
	}
}
