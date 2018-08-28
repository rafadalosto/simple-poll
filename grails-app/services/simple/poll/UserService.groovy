package simple.poll

import grails.gorm.transactions.Transactional

@Transactional
class UserService {

	def listUsers() {
    	return User.findAll([sort: 'name', order: 'desc'])
    }

    def saveUser(def params) {
    	def user 		= new User()
    	user.name 		= params.name
    	user.email 		= params.email
    	user.birthDate 	= convertStringToDate(params.birthDate)
    	user.save(flush:true, failOnError: true)
    	return user
    }

    def updateUser(def params) {
    	def user = User.findById(params.userId)
    	if (!user) {
    		throw new RuntimeException("O ID do usuário informado é inválido!")
    	}

    	user.name 		= params.name
    	user.email 		= params.email
    	user.birthDate 	= convertStringToDate(params.birthDate)
    	user.save(flush:true, failOnError: true)
    	return user
    }

    private convertStringToDate(def birthDate) {
    	try {
    		return new Date().parse("yyyy-MM-dd", birthDate)
    	} catch( Exception ex) {
    		throw new RuntimeException("Ocorreu um erro ao converter a data. Verifique se a data encontra-se no formato correto: yyyy-MM-dd")
    	}
    }
}
