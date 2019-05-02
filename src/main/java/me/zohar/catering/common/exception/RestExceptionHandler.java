package me.zohar.catering.common.exception;

import java.util.Iterator;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import me.zohar.catering.common.vo.Result;

@RestControllerAdvice
public class RestExceptionHandler {
	
	
	@ExceptionHandler(BizException.class)
	@ResponseStatus(value = HttpStatus.OK)
	public Result handleBizException(BizException e) {
		String msg = "biz exception";
		if (e != null) {
			msg = e.getMsg();
		}
		return Result.fail(msg);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(value = HttpStatus.OK)
	public Result handleConstraintViolationException(ConstraintViolationException e) {
		String msg = "param valid exception";
		if (e != null) {
			Iterator<ConstraintViolation<?>> iterator = e.getConstraintViolations().iterator();
			if (iterator.hasNext()) {
				ConstraintViolation<?> violation = iterator.next();
				msg = violation.getPropertyPath() + ":" + violation.getMessage();
			}
		}
		return Result.fail(msg);
	}

	@ExceptionHandler(ParamValidException.class)
	@ResponseStatus(value = HttpStatus.OK)
	public Result handleParamValidException(ParamValidException e) {
		String msg = "param valid exception";
		if (e != null) {
			msg = e.getMsg();
		}
		return Result.fail(msg);
	}
}
