package mbti.board.api;

import lombok.extern.slf4j.Slf4j;
import mbti.board.Exception.BusinessException;
import mbti.board.api.bind.ApiResult;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@Slf4j
@RestControllerAdvice
public class CotrollerAdvice {

    // 비즈니스 로직 처리 중 발생한 에러
    @ExceptionHandler(BusinessException.class)
    private ApiResult<?> handleBusinessException(BusinessException e) {
        log.error(e.getMessage(), e.getCause());
        return ApiResult.fail(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Valid 어노테이션을 통해 걸러낸 검증 Exception handler
    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ApiResult<?> handleValidationException(MethodArgumentNotValidException e) throws Exception {
        log.error(e.getMessage(), e.getCause());
        BindingResult bindingResult = e.getBindingResult();
        var FieldError = bindingResult.getFieldErrors().stream().findFirst().orElseThrow(RuntimeException::new);
        String message = FieldError.getDefaultMessage();
        return ApiResult.fail(message, HttpStatus.BAD_REQUEST);
    }
    //TODO: 에러 메세지 임시처리 조치
    @ExceptionHandler(ConstraintViolationException.class)
    private ApiResult<?> handleValidationException(ConstraintViolationException e) {
        log.error(e.getMessage(), e.getCause());
        return ApiResult.fail(e.getMessage().split(": ")[1], HttpStatus.BAD_REQUEST);
    }



    // 정의하지 않은 에러 handler... 근데 에러 출력이 제대로 안됨. 이거 어떻게 처리하지? -> 임시처리
    //TODO: 에러 제대로 안나오는 이유 확인 및 조치
    @ExceptionHandler(RuntimeException.class)
    private ApiResult<?> handleUnexpectedException(RuntimeException e){
        log.error(e.toString(), e);
        return ApiResult.fail("예상치 못한 오류가 발생했습니다. 관리자에게 문의 바랍니다.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
