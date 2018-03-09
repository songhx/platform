package com.platform.dto;

import com.platform.entity.CarpoolCar;
import com.platform.entity.CarpoolUser;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zuimeideshiguang on 18/2/13.
 */
public class CarpoolUserVo extends CarpoolUser implements Serializable {

    private CarpoolCar carpoolCar;


    public CarpoolCar getCarpoolCar() {
        return carpoolCar;
    }

    public void setCarpoolCar(CarpoolCar carpoolCar) {
        this.carpoolCar = carpoolCar;
    }
}
