package com.chung.daniel;
import java.util.ArrayList;

import jakarta.validation.OverridesAttribute.List;

public class BusinessLayer {

    public BusinessLayer(){
    }

    public boolean checkEmptyStrings(ArrayList<String> queryParams){
        for (String param : queryParams){
            if (param.equals("")){
                return true;
            }
        }
        return false;
    }

    public boolean checkEmptyInts(ArrayList<Integer> queryParams){
        for (int param : queryParams){
            if (param == 0){
                return true;
            }
        }
        return false;
    }
}
