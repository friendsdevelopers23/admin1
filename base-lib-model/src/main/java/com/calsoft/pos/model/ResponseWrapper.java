package com.calsoft.pos.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ResponseWrapper implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@JsonProperty("datalist")
    private List results;


    @JsonProperty("data")
    private Object result;

    /**
     * AST-200 - Success Status Code
     * AST-XXX - Any other Status Code resembles error
     */
    @JsonProperty("status_code")
    private String statusCode;

    /**
     * This will carry both success and failure messages.
     */
    @JsonProperty("status_message")
    private String statusMessage;
    
    
    @JsonProperty("datas")
    private Object resultSet;
    
    

    public ResponseWrapper(List result, String statusCode, String statusMessage) {
        this.results = result;
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }
    public ResponseWrapper(Object result, String statusCode, String statusMessage) {
        this.result = result;
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }
	public ResponseWrapper(Object result, String statusCode, String statusMessage, Object resultSet) {
		this.result = result;
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		this.resultSet = resultSet;
	}


}
