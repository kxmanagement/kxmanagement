package com.kx.dao;

import org.apache.ibatis.annotations.Param;

public interface UserDao {
public int getCountByUserNameAndPassword(@Param("username")String username,@Param("password")String password);
}
