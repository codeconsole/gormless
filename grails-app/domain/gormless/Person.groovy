package gormless

class Person {

	String name

    static constraints = {
    	name minSize:2, blank: false
    }
}
