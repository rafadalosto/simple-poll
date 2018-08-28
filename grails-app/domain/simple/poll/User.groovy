package simple.poll

class User {

	String 	name
	String 	email
	Date 	birthDate

    static constraints = {
    	name 		(nullable: false)
    	email 		(nullable: false, unique: true)
    	birthDate 	(nullable: false)
    }
}
