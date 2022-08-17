package com.techelevator.dao;

import com.techelevator.model.Employee;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcEmployeeDao implements EmployeeDao{
    private final JdbcTemplate jdbcTemplate;
    public JdbcEmployeeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Employee> gitAllEmployee(){
        List<Employee> employeesList = new ArrayList<Employee>();
        String sql = "select user_id, username from users where users.role=?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, "ROLE_ADMIN");
        while(results.next()) {
            Employee employee = new Employee();
            employee.setId(results.getInt("user_id"));
            employee.setName(results.getString("username"));
            employeesList.add(employee);
        }
        return employeesList;
    }
}
