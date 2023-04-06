package com.zy.mybatis.generatedaoproxy;


public interface EmployeeDao {

    Employee selectById(Long id);

    void insert(Employee employee);

    void update(Employee employee);

    void delete(Long employee);
}
