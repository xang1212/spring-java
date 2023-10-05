package th.co.newbie.newhttp.controller.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import th.co.newbie.newhttp.model.AddressModel;
import th.co.newbie.newhttp.model.PersonModel;
import th.co.newbie.newhttp.model.ResponseModel;
import th.co.newbie.newhttp.service.AppService;

@RestController
@RequestMapping("/api")
public class AppRestController {

    public AppRestController(AppService appService) {
        this.appService = appService;
    }

    private AppService appService;
    @GetMapping("/person")
    public ResponseModel<PersonModel> getPersonData(){

        return this.appService.getPersonDataAndResponse();
    }

    @GetMapping("/address")
    public ResponseModel<AddressModel> getAddress(){

        return this.appService.getAddressDataResponse();

    }
}
