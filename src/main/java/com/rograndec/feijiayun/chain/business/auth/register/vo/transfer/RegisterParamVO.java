package com.rograndec.feijiayun.chain.business.auth.register.vo.transfer;

import com.rograndec.feijiayun.chain.business.auth.register.vo.RegisterEnterpriseVO;
import com.rograndec.feijiayun.chain.business.auth.register.vo.RegisterUserVO;
import com.rograndec.feijiayun.chain.business.auth.register.vo.RegisterVO;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Location;

import java.util.List;

public class RegisterParamVO {

    private RegisterVO registerVO;

    private RegisterEnterpriseVO registerEnterpriseVO;

    private RegisterUserVO registerUserVO;

    private List<Location> locations;

    private User user;

    public RegisterVO getRegisterVO() {
        return registerVO;
    }

    public void setRegisterVO(RegisterVO registerVO) {
        this.registerVO = registerVO;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public RegisterUserVO getRegisterUserVO() {
        return registerUserVO;
    }

    public void setRegisterUserVO(RegisterUserVO registerUserVO) {
        this.registerUserVO = registerUserVO;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public RegisterEnterpriseVO getRegisterEnterpriseVO() {
        return registerEnterpriseVO;
    }

    public void setRegisterEnterpriseVO(RegisterEnterpriseVO registerEnterpriseVO) {
        this.registerEnterpriseVO = registerEnterpriseVO;
    }
}
