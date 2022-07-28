package com.taha.auth.utils.responses.model;

import com.taha.auth.utils.responses.enums.BackEndResponseTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BackEndResponse {
    private BackEndResponseTypes message;
    private Object data;
}
