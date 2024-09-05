package com.aurionpro.main.Config;

import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionNotificationListener implements JobExecutionListener {

	public JobCompletionNotificationListener(){		
		super();
	}	
}
