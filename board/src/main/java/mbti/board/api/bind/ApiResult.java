package mbti.board.api.bind;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class ApiResult <T>{

    private final T data;
    private final String message;
    private final HttpStatus status;

    public static <T> ApiResult<T> success(T data, HttpStatus httpStatus){
        return new ApiResult<T>(
               data, "성공", httpStatus
        );
    }

    public static ApiResult<?> fail(String message, HttpStatus httpStatus){
        return new ApiResult<>(
                null, message, httpStatus
        );
    }
}
