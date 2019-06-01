package gormless

class BootStrap {

	def personService

    def init = { servletContext ->
    	personService.save(new Person(name:'Graeme Rocher'))
    }
    def destroy = {
    }
}
