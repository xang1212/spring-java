package th.co.newbie.newhttp.service;

import org.springframework.stereotype.Service;
import th.co.newbie.newhttp.model.AddressModel;
import th.co.newbie.newhttp.model.PersonModel;
import th.co.newbie.newhttp.model.ResponseModel;

@Service
public class AppService {

    public ResponseModel<PersonModel> getPersonDataAndResponse(){
        ResponseModel<PersonModel> result = new ResponseModel<>();

        result.setStatus(200);
        result.setDescription("ok");

        try {
            PersonModel personModel = new PersonModel();
            personModel.setAge(10);
            personModel.setName("Xang");
            result.setData(personModel);

        }catch (Exception e){
            result.setStatus(500);
            result.setDescription(e.getMessage());
        }

        return result;
    }

    private PersonModel getPersonData(){
        PersonModel personModel = new PersonModel();
        personModel.setName("Alieng");
        personModel.setAge(21);
        return personModel;
    }

    public ResponseModel<AddressModel> getAddressDataResponse(){
        ResponseModel<AddressModel> result = new ResponseModel<>();
        try {

            result.setStatus(200);
            result.setDescription("ok");

            AddressModel addressModel = new AddressModel();


            result.setData(addressModel);
        }catch (Exception e){
            result.setStatus(500);
            result.setDescription(e.getMessage());
        }
        return result;
    }

    private AddressModel getAddressModel(){
        AddressModel addressModel = new AddressModel();
        addressModel.setHouseNo("135");
        addressModel.setPostalCode("21");
        addressModel.setProvince("135");
        addressModel.setDistrict("21");
        return addressModel;
    }
}
