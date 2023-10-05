package th.co.newbie.newhttp.controller.rest;

import org.springframework.web.bind.annotation.*;
import th.co.newbie.newhttp.entity.EmployeesEntity;
import th.co.newbie.newhttp.model.EmployeeCriteriaModel;
import th.co.newbie.newhttp.model.EmployeeModel;
import th.co.newbie.newhttp.model.ResponseModel;
import th.co.newbie.newhttp.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    private EmployeeService employeeService;



    @PostMapping("/find/employee")
    public ResponseModel<List<EmployeeModel>> getEmployeeByLastName(
            @RequestBody EmployeeCriteriaModel employeeModel
    ){
        return this.employeeService.getEmployeeByEmployee(employeeModel);
    }




    @PostMapping("/insert/one-employee")
    public ResponseModel<Integer> getEmployeeByLastName(
            @RequestBody List<EmployeeModel> employeeModel
    ){
        return this.employeeService.insertEmployeeByNativeSql(employeeModel);
    }

    @PostMapping("/insert/employee")
    public ResponseModel<Integer> insertEmployeeByLastName(
            @RequestBody EmployeeModel employeeModel
    ){
        return this.employeeService.insertOneEmployeeByNativeSql(employeeModel);
    }

    @PostMapping("/update/employee")
    public ResponseModel<Integer> getEmployeeByLastName(
            @RequestBody EmployeeModel employeeModel
    ){
        return this.employeeService.updateEmployeeByNativeSql(employeeModel);
    }


    @GetMapping("/employee")
    public ResponseModel<List<EmployeeModel>> getEmployeeByLastName(@RequestParam(name = "lastName") String lastName){
        return this.employeeService.getEmployeeByLastnameThenResponse(lastName);
    }

    @GetMapping("/employee/{lastName}")
    public ResponseModel<List<EmployeeModel>> getEmployeeByLastName2(@PathVariable(name = "lastName") String lastName){
        return this.employeeService.getEmployeeByLastnameThenResponse(lastName);
    }

    @PostMapping("employee")
    public ResponseModel<Void> insertAndUpdateEmployee(
            @RequestBody EmployeeModel employeeModel
    ){
        return this.employeeService.insertAndUpdateEmployee(employeeModel);
    }

    @DeleteMapping("/employee")
    public ResponseModel<Void> deleteEmployee(
            @RequestBody EmployeeModel employeeModel
    ){
        return this.employeeService.deleteEmployee(employeeModel);
    }
}
