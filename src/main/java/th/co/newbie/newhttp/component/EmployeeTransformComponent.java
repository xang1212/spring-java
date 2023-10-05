package th.co.newbie.newhttp.component;

import org.springframework.stereotype.Component;
import th.co.newbie.newhttp.entity.EmployeesEntity;
import th.co.newbie.newhttp.model.EmployeeModel;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeTransformComponent {

    public List<EmployeeModel> transformEntityToModelList(List<EmployeesEntity> employeesEntity) {
        List<EmployeeModel> employeeModelList = new ArrayList<>();
        for (EmployeesEntity x : employeesEntity) {
            EmployeeModel y = this.transformEntityToModel(x);
            employeeModelList.add(y);
        }
        return employeeModelList;
    }
    public EmployeeModel transformEntityToModel(EmployeesEntity employeesEntity){
        EmployeeModel employeeModel = new EmployeeModel();
        employeeModel.setGender(employeesEntity.getGender());
        employeeModel.setFirstName(employeesEntity.getFirstName());
        employeeModel.setLastName(employeesEntity.getLastName());
        employeeModel.setEmpNo(employeesEntity.getEmpNo());
        employeeModel.setHireDate(employeesEntity.getHireDate());

        return employeeModel;
    }


    public EmployeesEntity transfromModelToEntity(EmployeeModel employeeModel){
        EmployeesEntity employeesEntity = new EmployeesEntity();
        employeesEntity.setGender(employeeModel.getGender());
        employeesEntity.setBirthDate(employeeModel.getBirthDate());
        employeesEntity.setFirstName(employeeModel.getFirstName());
        employeesEntity.setEmpNo(employeeModel.getEmpNo());
        employeesEntity.setHireDate(employeeModel.getHireDate());
        employeesEntity.setLastName(employeeModel.getLastName());
        return employeesEntity;
    }

    public void transfromModelToEntityForUpdate(EmployeesEntity employeesEntity,EmployeeModel employeeModel){
        employeesEntity.setGender(employeeModel.getGender());
        employeesEntity.setBirthDate(employeeModel.getBirthDate());
        employeesEntity.setFirstName(employeeModel.getFirstName());
        employeesEntity.setEmpNo(employeeModel.getEmpNo());
        employeesEntity.setHireDate(employeeModel.getHireDate());
        employeesEntity.setLastName(employeeModel.getLastName());
    }
}
