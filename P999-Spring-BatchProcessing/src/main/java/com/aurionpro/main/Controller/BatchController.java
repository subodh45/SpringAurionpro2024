package com.aurionpro.main.Controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BatchController {

	@Autowired
	private JobLauncher jobLauncher;
	
    @Autowired 
    private Job importUserJob; 

    @Autowired
    private Job exportUserJob; 
	
	@GetMapping("/process")
	public void process()
	{
		try {
		      JobParameters jobParameters = new JobParametersBuilder()
		                  .addLong("startAt", System.currentTimeMillis()).toJobParameters();
		      
		      jobLauncher.run(importUserJob, jobParameters);
		    } catch (JobExecutionAlreadyRunningException e) {
		      // TODO Auto-generated catch block
		      e.printStackTrace();
		    } catch (JobRestartException e) {
		      // TODO Auto-generated catch block
		      e.printStackTrace();
		    } catch (JobInstanceAlreadyCompleteException e) {
		      // TODO Auto-generated catch block
		      e.printStackTrace();
		    } catch (JobParametersInvalidException e) {
		      // TODO Auto-generated catch block
		      e.printStackTrace();
		    }
	}
	
	
	 @GetMapping("/processDatabaseToCsv")
	    public String processDatabaseToCsv() {
	        try {
	            JobParameters jobParameters = new JobParametersBuilder()
	                    .addLong("startAt", System.currentTimeMillis())
	                    .toJobParameters();

	            jobLauncher.run(exportUserJob, jobParameters);
	            return "Database to CSV job executed successfully!";
	        } catch (JobExecutionAlreadyRunningException | JobRestartException |
	                 JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
	            e.printStackTrace();
	            return "Failed to execute Database to CSV job: " + e.getMessage();
	        }
	    }
	
	
}
