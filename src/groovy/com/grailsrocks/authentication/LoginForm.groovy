package com.grailsrocks.authentication
import  grails.validation.Validateable

@Validateable
class LoginForm implements Serializable {
	String login
	String password
	
	boolean rememberMe
	

	static constraints = {
		login(size:4..40, nullable: false, blank:false)
		password(size:6..40, password:true, nullable: false, blank:false)
	}
}
