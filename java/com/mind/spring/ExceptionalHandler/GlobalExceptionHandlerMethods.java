/*package com.mind.spring.ExceptionalHandler;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandlerMethods {

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = NullPointerException.class)
	public String handlerNullPointerException(Model m) {
		m.addAttribute("mssg", "Null Pointer Exception Occured");
		return "NullPointerException";
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = IOException.class)
	public String handlerIOException(Model m) {
		m.addAttribute("mssg", "IO Exception Occured");
		return "IOException";
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = Exception.class)
	public String handlerException(Model m) {
		m.addAttribute("mssg", "Unknown Exception Occured");
		return "Exception";
	}

}
*/