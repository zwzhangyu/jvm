package com.zy.mybatis.generatedaoproxy;


import org.springframework.web.bind.annotation.*;

/**
 * 测试通过字节码生成实现类操作数据库
 *
 * @author zhangyu
 * @date 2023/4/6
 */
@RestController
@RequestMapping("/emp")
public class EmployeeController {

    private EmployeeDao employeeDao = (EmployeeDao) MyBatisGenerateDaoProxy.generate(SqlSessionUtil.openSession(), EmployeeDao.class);

    @GetMapping("/{id}")
    public Object get(@PathVariable("id") Long id) {
        return employeeDao.selectById(id);
    }


    @PutMapping("/{id}")
    public Object update(@PathVariable("id") Long id) {
        employeeDao.update(new Employee(id, "zhangsan-new", "zhangsan-new@qq.com"));
        return "OK";
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable("id") Long id) {
        employeeDao.delete(id);
        return "OK";
    }

    @PostMapping
    public Object insert() {
        employeeDao.insert(new Employee(100, "zhangsan", "zhangsan@qq.com"));
        return "OK";
    }

}
