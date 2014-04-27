package com.itsy.sparky.pageflow.domain;

import static com.itsy.sparky.common.utils.Log.info;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itsy.sparky.pageflow.model.Step;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:WebContent/WEB-INF/sparky-service.xml")
public class OrdinalIndexComparableTest {

	@Test
	public void testCompare() {
		List<Step> steps = new ArrayList<Step>();
		
		Step s1 = new Step();
		s1.setStepName("20");
		s1.setStepOrdIndex(20);

		Step s2 = new Step();
		s2.setStepName("120");
		s2.setStepOrdIndex(120);

		Step s3 = new Step();
		s3.setStepName("10");
		s3.setStepOrdIndex(10);
		
		Step s4 = new Step();
		s4.setStepName("120");
		s4.setStepOrdIndex(120);
		
		steps.add(s1);
		steps.add(s2);
		steps.add(s3);
		steps.add(s4);
		
		OrdinalIndexComparable comparable = new OrdinalIndexComparable();
		Collections.sort(steps, comparable);

		for (Step step : steps) {
			info(this, step.toString());
		}
		
		assertEquals(steps.get(0).getStepOrdIndex(), 10);
		assertEquals(steps.get(1).getStepOrdIndex(), 20);
		assertEquals(steps.get(2).getStepOrdIndex(), 120);
		assertEquals(steps.get(3).getStepOrdIndex(), 120);
	}

}
