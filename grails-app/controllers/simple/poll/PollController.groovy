package simple.poll

import grails.converters.*
import grails.validation.ValidationException

class PollController {

    def list() { 
    	def polls = Poll.findAll()
    	def result = [pollList:polls]
    	render (result as JSON)
    }

    def create() {
    	def poll = new Poll()
    	poll.name = params.name
    	poll.question = params.question
    	poll.save(flush:true, failOnError:true)
    	render (poll as JSON)
    }

    def getById() {
    	def id = params.id
    	def poll = Poll.findById(id)
    	poll.name = " Sr. " + poll.name
    	poll.save(flush:true)

    	if (poll) {
    		render ( poll as JSON)
    	} else {
    		render ([message:"Poll não encontrado"] as JSON)
   		}
    }
}
