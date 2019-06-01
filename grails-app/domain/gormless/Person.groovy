package gormless

class Person implements grails.validation.Validateable {

	String name

    static constraints = {
    	name minSize:2, blank: false
    }
}
