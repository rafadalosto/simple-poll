package simple.poll

import grails.converters.*
import grails.validation.ValidationException

class UserController {

	def userService

	static allowedMethods = [list: 'GET', create: 'POST', getUser: 'GET', update: 'PUT', delete: 'DELETE' ]

    def list() { 
    	def userList = userService.listUsers()
    	def result = [userList: userList]
    	render (result as JSON)
    }

    def create() {
    	def json = request.JSON
    	try {
    		def user 	= userService.saveUser(json)	
    		def result 	= [user:user, message: "Usuário salvo com sucesso"]
    		render(result as JSON) 
    	} catch(ValidationException ex) {
    		def errors 		= ex.getErrors()
    		def message 	= ex.formatErrors(errors)
    		def result 		= [message: message]
    		response.status = 422
    		render(errors as JSON) 
    	} catch(RuntimeException ex) {
 			def message 	= ex.getMessage()
 			def result 		= [message:message]
 			response.status = 500
 			render(result as JSON)  
    	}
    }

    def getUser() {
    	def user = User.findById(params.userId)
    	render ([user: user] as JSON)
    }

    def update() {
    	def json = request.JSON
    	try {
    		def user 	= userService.updateUser(json)	
    		def result 	= [user:user, message: "Usuário atualizado com sucesso"]
    		render(result as JSON) 
    	} catch(ValidationException ex) {
    		def errors 		= ex.getErrors()
    		def message 	= ex.formatErrors(errors)
    		def result 		= [message: message]
    		response.status = 422
    		render(errors as JSON) 
    	} catch(RuntimeException ex) {
 			def message 	= ex.getMessage()
 			def result 		= [message:message]
 			response.status = 500
 			render(result as JSON)  
    	}

    }

    def delete() {
    	try {
    		def user = User.findById(params.userId)
    		user.delete(flush:true, failOnError: true)
    		render ([message: "Usuário removido com sucesso"] as JSON)
    	} catch( Exception ex) {
    		response.status = 500
    		render ([message: "Ocorreu um erro ao remover o usuário!"] as JSON)
    	}
    }

}
