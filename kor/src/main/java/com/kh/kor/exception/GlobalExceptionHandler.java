package com.kh.kor.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kh.kor.user.model.dto.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handlerArgumentsNotValid(MethodArgumentNotValidException e){

		Map<String, String> errors = new HashMap();
		e.getBindingResult().getFieldErrors().forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
		
		
		return ResponseEntity.badRequest().body(new ErrorResponse(400, "유효하지 않은 요청입니다.", errors));
	}
	
	
	@ExceptionHandler(DuplicateMemberIdException.class)
	public ResponseEntity<ErrorResponse> handlerDuplicateId(DuplicateMemberIdException e){
		
		ErrorResponse er = new ErrorResponse(400, e.getMessage(), null);
		return ResponseEntity.badRequest().body(er);
	}
	
	@ExceptionHandler(FailSignUpException.class)
	public ResponseEntity<ErrorResponse> handlerFailSignUp(FailSignUpException e){
		return ResponseEntity.internalServerError().body(new ErrorResponse(500, e.getMessage(), null));
	}
	
}
