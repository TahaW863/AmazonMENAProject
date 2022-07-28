package com.taha.customerinformationservice.utils.responses.model;

import com.taha.customerinformationservice.utils.responses.enums.BackEndResponseTypes;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class BackEndResponse {
    private BackEndResponseTypes message;
    private Object data;
}
