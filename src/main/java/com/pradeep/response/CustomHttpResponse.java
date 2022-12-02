package com.pradeep.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomHttpResponse {
    private Integer httpStatusCode;
    private String title;
    private String message;
    private String path;
}
