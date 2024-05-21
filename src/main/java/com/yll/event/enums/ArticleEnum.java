package com.yll.event.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ArticleEnum {

	PUBLISH("已发布"),
	DRAFT("草稿");

	private String description;

}
