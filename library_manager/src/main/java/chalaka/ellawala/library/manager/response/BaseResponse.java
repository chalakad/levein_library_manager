package chalaka.ellawala.library.manager.response;

import chalaka.ellawala.library.manager.enums.ResponseStatuses;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class BaseResponse<T> implements Serializable {

    private ResponseStatuses status;
    private String errorCode;
    private String errorMessage;
    private String details;
    private String timestamp;
    private T responseData;

    public BaseResponse() {
    }

    public BaseResponse(ResponseStatuses status) {
        this.status = status;
    }

    public BaseResponse(ResponseStatuses status, String errorCode, String errorMessage, String details, String timestamp, T responseData) {
        this.status = status;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.details = details;
        this.timestamp = timestamp;
        this.responseData = responseData;
    }
}
