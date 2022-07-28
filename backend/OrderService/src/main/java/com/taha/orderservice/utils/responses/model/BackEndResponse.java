package com.taha.orderservice.utils.responses.model;

import com.taha.orderservice.utils.responses.enums.BackEndResponseTypes;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
public class BackEndResponse {
    private BackEndResponseTypes message;
    private Object data;
}
