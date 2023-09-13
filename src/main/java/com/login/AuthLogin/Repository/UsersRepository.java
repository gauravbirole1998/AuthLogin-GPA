package com.login.AuthLogin.Repository;

import com.login.AuthLogin.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository <Users, Long>{

    Users findByMobileNumber(Long mobileNumber);

}
