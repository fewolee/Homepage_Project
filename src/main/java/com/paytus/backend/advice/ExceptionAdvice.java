package com.paytus.backend.advice;

import com.paytus.backend.model.response.CommonResult;
import com.paytus.backend.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestControllerAdvice // 예외 발생 시 json 형태로 결과 반환 | 프로젝트의 모든 Controller에 로직 적용
// @RestControllerAdvice(basePackages = "com.paytus.backend") : backend 하위 Controller 에만 로직 적용
public class ExceptionAdvice {
    private final ResponseService responseService; // 결과에 대한 정보를 도출하는 클래스 명시

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult defaultException(HttpServletRequest request, Exception e) {
        // CommonResult : 응답 결과에 대한 정보
        return responseService.getFailResult();
        // getFailResult : setSuccess, setCode, setMsg
    }
}