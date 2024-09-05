package com.aurionpro.main.dto;

import java.util.List;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PageResponseDto <T>{

	private int pageSize;
	private int pageNumber;
	private List<T> content;
}
