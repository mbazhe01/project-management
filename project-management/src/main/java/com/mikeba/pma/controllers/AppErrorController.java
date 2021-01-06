package com.mikeba.pma.controllers;

import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.ServletWebRequest;

@Controller
public class AppErrorController implements ErrorController {

	@Value("${app.controller.addemployee.path}")
	private String addEmployeePath;

	@Value("${app.controller.addemployee.errmsg}")
	private String addEmployeeError;

	@Value("${app.controller.addproject.path}")
	private String addProjectPath;

	@Value("${app.controller.addproject.errmsg}")
	private String addProjectError;

	@Value("${app.controller.resourceNotFound.errmsg}")
	private String resourceNotFoundError;

	@Value("${app.controller.internalServerError.errmsg}")
	private String internalServerError;
	
	@Value("${app.controller.methodNotAllowed.errmsg}")
	private String methodNotAllowedError;

	@Value("${app.controller.generalError.errmsg}")
	private String generalError;

	@Autowired
	private ErrorAttributes errorAttributes;

	private static volatile String errorPagePath;
	private static volatile String errMsg;
	
	@GetMapping("/error")
	public String handleError(HttpServletRequest request, Model model) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		String message = null;
		
		ServletWebRequest servletWebRequest = new ServletWebRequest(request);
		Map<String, Object> errorAttributes = this.errorAttributes.getErrorAttributes(servletWebRequest, true);
		final StringBuilder errorDetails = new StringBuilder();
		errorAttributes.forEach((attribute, value) -> {
			errorDetails.append("|").append(attribute).append("--").append(value).append("|");
			if (attribute.equals("path"))
				errorPagePath = value.toString();
			else if (attribute.equals("message")) {
				errMsg = value.toString();
			}
		});

		if (status != null) {
			Integer statusCode = Integer.valueOf(status.toString());

			if (statusCode == HttpStatus.NOT_FOUND.value()) {
				message = String.format(resourceNotFoundError, errorPagePath);
				model.addAttribute("message", message);

				return "errorpages/error-404";
			} else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				message = String.format(internalServerError, errorPagePath);
				message += "\n\n"+ errMsg ;
				model.addAttribute("message", message);

				return "errorpages/error-500";
			} else if (statusCode == HttpStatus.FORBIDDEN.value()) {

				if (addEmployeePath.equals(errorPagePath))
					message = addEmployeeError;
				else if (addProjectPath.equals(errorPagePath))
					message = addProjectError;
				else
					message = "Undefined error message";
				model.addAttribute("message", message);

				return "errorpages/error-403";
			} else if (statusCode == HttpStatus.METHOD_NOT_ALLOWED.value()) {
				
				message = methodNotAllowedError + " | " + errorPagePath ;
				model.addAttribute("message", message);

				return "errorpages/error-405";
			}
		}

		// general error
		message = String.format(generalError, errorPagePath);
		model.addAttribute("message", message);

		return "errorpages/error";

	}

	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return "/error";
	}

}
