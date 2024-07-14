package com.userJibei.model;

import java.util.List;

public interface UserJibeiDAO_interface {
	public void add(UserJibeiVO userJibeiVO);
	public boolean use(UserJibeiVO userJibeiVO);
	public List<UserJibeiVO> findByUser_id(Integer user_id);
}
