package th.co.newbie.newhttp.repository;

import th.co.newbie.newhttp.model.EmployeeCriteriaModel;
import th.co.newbie.newhttp.model.EmployeeModel;

import java.util.List;

public interface EmployeesNativeRepository {
    public List<EmployeeModel> findEmployeeByEmployee(EmployeeCriteriaModel employeeModel);
    public int insertEmployee(List<EmployeeModel> employeeModels);

    public int updateEmployee(EmployeeModel employeeModel);
    public int insertEmployeeByOne(EmployeeModel employeeModel);
}
