package com.together.pojo.returnPojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author w
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReturnData {
    private String code;
    private boolean error;
    private String errorMassage;
    private com.together.pojo.returnPojo.Data data;

    public ReturnData(String code, boolean error,com.together.pojo.returnPojo.Data data) {
        this.code = code;
        this.error = error;
        this.data = data;
    }

    public ReturnData(String code, boolean error) {
        this.code = code;
        this.error = error;
    }

    public ReturnData(String code, boolean error, String errorMassage) {
        this.code = code;
        this.error = error;
        this.errorMassage = errorMassage;
    }
}
