package com.together.util;

import com.together.pojo.StateCode;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;

/**
 * @author w
 */
@Component
public class JsonUtil extends JSONObject {

    public void putStateCode(StateCode stateCode) throws JSONException {
        super.put("state", stateCode.getState());
        super.put("context", stateCode.getContext());
    }
}
