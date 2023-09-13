package com.login.AuthLogin.Controllers;

import com.login.AuthLogin.Model.Users;
import com.login.AuthLogin.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UsersRepository usersRepository;

    @RequestMapping("/getOtp")
    public String getOtp(Long mobileNo) {
        if (String.valueOf(mobileNo).length() < 10) {
            return "Invalid Mobile Number";
        }

        if (String.valueOf(mobileNo).length() != 10) {
            return "Mobile Number must be exactly 10 digits";

        }
        Random rand = new Random();
        int otp = rand.nextInt(999999);

        System.out.println("otp" + otp);

        Users users = new Users();
        users.setOtp(otp);
        users.setMobileNumber(mobileNo);

        usersRepository.save(users);

        return String.valueOf(otp);

    }

    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public String validate(@RequestBody Users users){
        if(String.valueOf(users.getMobileNumber()).length() < 10 || String.valueOf(users.getOtp()).length() < 6){
            return "Invalid Mobile Number or OTP Number";
        }

        try {
            Users user =usersRepository.findByMobileNumber(users.getMobileNumber());

            if (user !=null && user.getOtp() == users.getOtp()){
                return "Validated Successfully!";
            }
            else {
                return "OTP Validation Failed!";

            }
        }
        catch (Exception e){
            return "Mobile Number Not Found!";

        }
    }

}

