package com.aurionpro.JPA.dto;

import java.util.List;

import com.aurionpro.JPA.entity.Student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PageResponseDto<T> {

	private long totalElements;
	private int totalPages;
	private int size;
	private List<T> content;
	private boolean isLastPage;
	
}
