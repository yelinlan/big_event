package com.yll.event.vo.resp;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 分页
 * @author 夜林蓝
 */
@Data
@AllArgsConstructor
public class PageBean<T> {

	private Long total;

	private List<T> items;

}