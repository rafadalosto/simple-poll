package simple.poll

class Poll {

	String 	name
	String 	question
	
    static constraints = {
    	name 		(nullable: false)
    	question 	(nullable: false, unique: true)
    }
}
