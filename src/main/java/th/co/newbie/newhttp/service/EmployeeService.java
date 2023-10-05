package th.co.newbie.newhttp.service;

import org.springframework.stereotype.Service;
import th.co.newbie.newhttp.component.EmployeeTransformComponent;
import th.co.newbie.newhttp.entity.EmployeesEntity;
import th.co.newbie.newhttp.model.EmployeeCriteriaModel;
import th.co.newbie.newhttp.model.EmployeeModel;
import th.co.newbie.newhttp.model.ResponseModel;
import th.co.newbie.newhttp.repository.EmployeesNativeRepository;
import th.co.newbie.newhttp.repository.EmployeesRepository;

import java.util.List;

@Service
public class EmployeeService {
    public EmployeeService(EmployeesRepository employeesRepository,
                           EmployeesNativeRepository employeeNativeRepository1,
                           EmployeeTransformComponent employeeTransformComponent) {
        this.employeesRepository = employeesRepository;
        this.employeeNativeRepository = employeeNativeRepository1;
        this.employeeTransformComponent = employeeTransformComponent;
    }

    private EmployeesRepository employeesRepository;
    private EmployeesNativeRepository employeeNativeRepository;
    private EmployeeTransformComponent employeeTransformComponent;


    public ResponseModel<List<EmployeeModel>> getEmployeeByEmployee(EmployeeCriteriaModel employeeModel){
        ResponseModel<List<EmployeeModel>> result = new ResponseModel<>();

        result.setStatus(200);
        result.setDescription("ok");
        try {
            // do some business
            List<EmployeeModel> transformedData = this.employeeNativeRepository.findEmployeeByEmployee(employeeModel);
            result.setData(transformedData);
        } catch (Exception e){
            result.setStatus(500);
            result.setDescription(e.getMessage());
        }
        return result;
    }

    public ResponseModel<Integer> insertEmployeeByNativeSql(List<EmployeeModel> employeeModels){
        ResponseModel<Integer> result = new ResponseModel<>();

        result.setStatus(201);
        result.setDescription("ok");
        try {
            // do some business
            int insertedRows = this.employeeNativeRepository.insertEmployee(employeeModels);
            result.setData(insertedRows);
        } catch (Exception e){
            result.setStatus(500);
            result.setDescription(e.getMessage());
        }
        return result;
    }

    public ResponseModel<Integer> updateEmployeeByNativeSql(EmployeeModel employeeModels){
        ResponseModel<Integer> result = new ResponseModel<>();

        result.setStatus(201);
        result.setDescription("ok");
        try {
            // do some business
            int insertedRows = this.employeeNativeRepository.updateEmployee(employeeModels);
            result.setData(insertedRows);
        } catch (Exception e){
            result.setStatus(500);
            result.setDescription(e.getMessage());
        }
        return result;
    }

    public ResponseModel<Integer> insertOneEmployeeByNativeSql(EmployeeModel employeeModels){
        ResponseModel<Integer> result = new ResponseModel<>();

        result.setStatus(201);
        result.setDescription("ok");
        try {
            // do some business
            int insertedRows = this.employeeNativeRepository.insertEmployeeByOne(employeeModels);
            result.setData(insertedRows);
        } catch (Exception e){
            result.setStatus(500);
            result.setDescription(e.getMessage());
        }
        return result;
    }

    public ResponseModel<List<EmployeeModel>> getEmployeeByLastnameThenResponse(String lastName){
        ResponseModel<List<EmployeeModel>> result = new ResponseModel<>();


        result.setStatus(200);
        result.setDescription("ok");
        try {
//            AddressModel addressModel = new AddressModel();
            List<EmployeesEntity> data = this.getEmployeeByLastName(lastName);
            List<EmployeeModel> transformedData = this.employeeTransformComponent.transformEntityToModelList(data);
            result.setData(transformedData);
        }catch (Exception e){
            result.setStatus(500);
            result.setDescription(e.getMessage());
        }
        return result;
    }

    private List<EmployeesEntity> getEmployeeByLastName(String lastName){
        return this.employeesRepository.findByLastName(lastName);
    }


    public ResponseModel<Void> insertAndUpdateEmployee(EmployeeModel employeeModel){
        ResponseModel<Void> result = new ResponseModel<>();

        result.setStatus(201);
        result.setDescription("ok");
        try {
            // do some business
            EmployeesEntity employeesEntity = this.employeeTransformComponent.transfromModelToEntity(employeeModel);
            this.employeesRepository.save(employeesEntity);
        } catch (Exception e){
            result.setStatus(500);
            result.setDescription(e.getMessage());
        }
        return result;
    }

    public ResponseModel<Void> deleteEmployee(EmployeeModel employeeModel){
        ResponseModel<Void> result = new ResponseModel<>();

        result.setStatus(201);
        result.setDescription("ok");
        try {
            // do some business
            EmployeesEntity employeesEntity = new EmployeesEntity();
            this.employeeTransformComponent.transfromModelToEntityForUpdate(employeesEntity, employeeModel);
            this.employeesRepository.delete(employeesEntity);
        } catch (Exception e){
            result.setStatus(500);
            result.setDescription(e.getMessage());
        }
        return result;
    }


}
