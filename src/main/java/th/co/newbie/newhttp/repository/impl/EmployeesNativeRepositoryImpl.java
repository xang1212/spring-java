package th.co.newbie.newhttp.repository.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import th.co.newbie.newhttp.model.EmployeeCriteriaModel;
import th.co.newbie.newhttp.model.EmployeeModel;
import th.co.newbie.newhttp.repository.EmployeesNativeRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Repository
public class EmployeesNativeRepositoryImpl implements EmployeesNativeRepository {
    private JdbcTemplate jdbcTemplate;
    public EmployeesNativeRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<EmployeeModel> findEmployeeByEmployee(EmployeeCriteriaModel employeeModel) {

        List<Object> paramList = new ArrayList<>();

        String sql = " select emp_no, birth_date, first_name, last_name, gender, hire_date ";
        sql += " from employees ";
        sql +=" where 1=1 ";
        if(StringUtils.isNotEmpty(employeeModel.getFirstName())){
            sql +=" and emp_no = ?  ";
            paramList.add(employeeModel.getEmpNo());
        }
        if(StringUtils.isNotEmpty(employeeModel.getFirstName())){
            sql +=" and first_name = ?  ";
            paramList.add(employeeModel.getFirstName());
        }
        if(StringUtils.isNotEmpty(employeeModel.getLastName())){
            sql +=" and last_name = ?  ";
            paramList.add(employeeModel.getLastName());
        }
        if(StringUtils.isNotEmpty(employeeModel.getGender())){
            sql +=" and gender = ?  ";
            paramList.add(employeeModel.getGender());
        }
        if(null != employeeModel.getBirthDate1() && null == employeeModel.getBirthDate2()){
            sql +=" and birth_date = ?  ";
            paramList.add(employeeModel.getBirthDate1());
        }
        if(null != employeeModel.getBirthDate1() && null != employeeModel.getBirthDate2()){
            sql +=" and birth_date between ? and ? ";
            paramList.add(employeeModel.getBirthDate1());
            paramList.add(employeeModel.getBirthDate2());
        }

        List<EmployeeModel> result = this.jdbcTemplate.query(sql, new RowMapper<EmployeeModel>() {
            @Override
            public EmployeeModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                EmployeeModel x = new EmployeeModel();
                int col = 1;
                x.setEmpNo(rs.getInt(col++));
                x.setBirthDate(rs.getDate(col++).toLocalDate());
                x.setFirstName(rs.getString(col++));
                x.setLastName(rs.getString(col++));
                x.setGender(rs.getString(col++));
                x.setHireDate(rs.getDate(col++).toLocalDate());
                return x;
            }
        }, paramList.toArray());

        return result;
    }

    @Override
    public int insertEmployee(List<EmployeeModel> employeeModels) {
        List<Object> paramList = new ArrayList<>();

        String sql = " insert into  employees (emp_no, birth_date, first_name, last_name, gender, hire_date)  values ";
        StringJoiner stringJoiner = new StringJoiner(",");
        for (EmployeeModel e: employeeModels) {
            String value = " ((select max(emp_no)+1 from employees e) ,?, ?, ?, ?, ? )";
            paramList.add(e.getBirthDate());
            paramList.add(e.getFirstName());
            paramList.add(e.getLastName());
            paramList.add(e.getGender());
            paramList.add(e.getHireDate());
            stringJoiner.add(value);
        }
        sql+= stringJoiner.toString();

        int insertedRow = this.jdbcTemplate.update(sql, paramList.toArray());
        return insertedRow;
    }

    @Override
    public int insertEmployeeByOne(EmployeeModel employeeModel) {
        List<Object> paramList = new ArrayList<>();

        String sql = "INSERT INTO employees (emp_no, birth_date, first_name, last_name, gender, hire_date) VALUES ";
        String value = "((SELECT MAX(emp_no) + 1 FROM employees e), ?, ?, ?, ?, ?)";

        paramList.add(employeeModel.getBirthDate());
        paramList.add(employeeModel.getFirstName());
        paramList.add(employeeModel.getLastName());
        paramList.add(employeeModel.getGender());
        paramList.add(employeeModel.getHireDate());

        sql += value;

        int insertedRow = this.jdbcTemplate.update(sql, paramList.toArray());
        return insertedRow;
    }


    @Override
    public int updateEmployee(EmployeeModel employeeModel) {
        List<Object> paramList = new ArrayList<>();

        String sql = " update employees set ";
        StringJoiner stringJoiner = new StringJoiner(",");

        if(StringUtils.isNotEmpty(employeeModel.getGender())){
            stringJoiner.add(" gender = ? ") ;
            paramList.add(employeeModel.getGender());
        }
        if(StringUtils.isNotEmpty(employeeModel.getFirstName())){
            stringJoiner.add(" first_name = ? ") ;
            paramList.add(employeeModel.getFirstName());
        }
        if(StringUtils.isNotEmpty(employeeModel.getLastName())){
            stringJoiner.add(" last_name = ? ") ;
            paramList.add(employeeModel.getLastName());
        }
        if(null != employeeModel.getHireDate()){
            stringJoiner.add(" hire_date = ? ") ;
            paramList.add(employeeModel.getHireDate());
        }

        int updatedRow = 0;
        if(paramList.size()>0){
            sql+= stringJoiner.toString();
            sql+= " where emp_no = ? ";
            paramList.add(employeeModel.getEmpNo());
            updatedRow = this.jdbcTemplate.update(sql, paramList.toArray());
        }
        return updatedRow;
    }
}
