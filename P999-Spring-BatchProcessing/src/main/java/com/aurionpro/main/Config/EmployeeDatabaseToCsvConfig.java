package com.aurionpro.main.Config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import com.aurionpro.main.Entity.Employee;

@Configuration
public class EmployeeDatabaseToCsvConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public JdbcCursorItemReader<Employee> readEmployeeFromDatabase() {
        return new JdbcCursorItemReaderBuilder<Employee>()
                .dataSource(dataSource)
                .name("employeeDatabaseReader")
                .sql("SELECT employee_id, name, salary FROM employees")
                .rowMapper((rs, rowNum) -> {
                    Employee employee = new Employee();
                    employee.setEmployeeId(rs.getInt("employee_id"));
                    employee.setName(rs.getString("name"));
                    employee.setSalary(rs.getDouble("salary"));
                    return employee;
                })
                .build();
    }

    @Bean
    public FlatFileItemWriter<Employee> writeEmployeeToCsv() {
        return new FlatFileItemWriterBuilder<Employee>()
                .name("employeeCsvWriter")
                .resource(new FileSystemResource("employees.csv"))
                .delimited()
                .delimiter(",")
                .names("employeeId", "name", "salary")
                .build();
    }

    @Bean
    public Step step2(JobRepository jobRepository, DataSourceTransactionManager transactionManager,
                      JdbcCursorItemReader<Employee> reader, FlatFileItemWriter<Employee> writer) {
        return new StepBuilder("exportDatabaseToCsvStep", jobRepository)
                .<Employee, Employee>chunk(2, transactionManager)
                .reader(reader)
                .writer(writer)
                .build();
    }

    @Bean
    public Job exportUserJob(JobRepository jobRepository, Step step2, JobCompletionNotificationListener listener) {
        return new JobBuilder("exportUserJob", jobRepository)
                .listener(listener)
                .start(step2)
                .build();
    }
}

