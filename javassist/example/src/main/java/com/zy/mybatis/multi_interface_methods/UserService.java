package com.zy.mybatis.multi_interface_methods;


public interface UserService {

    void insert(String name, String phone);

    void update(String name, String phone);

    String find(String name);

    int delete();
}
