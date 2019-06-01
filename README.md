The setup:

	grails create-app gormless
	cd gormless
	grails
	create-domain-class Person
	# add 1 properties to Person.groovy     String name
	generate-all Person 
	# Modify BootStrap.groovy to create 1 Person Object
	grails run-app

Try to change name of Graeme Rocher
http://localhost:8080/person/edit/1

Result: Works fine.
All Other CRUD Methods Work
(This phase can be demonstrated by checking out the initial branch)
https://github.com/codeconsole/gormless/tree/initial

The modification:

Modify PersonService to not use Gorm:

	class PersonService {
		Map<Long, Person> database = [:]
		long idGenerator = 0
		Person get(Serializable id) { database[id] }	
		List<Person> list(Map args) { database.values().collect { it } }
		Long count() { database.size() }
		void delete(Serializable id) { database.remove(id) }
		Person save(Person person) {
			person.id = person.id?:++idGenerator
			database[person.id] = person
		}
	}


	grails run-app

Try to change name of Graeme Rocher
http://localhost:8080/person/edit/1

Result: No change. notFound() due to controller update method receiving null person object.
All Other CRUD Methods Work
(This phase can be demonstrated by checking out the service-based branch)
https://github.com/codeconsole/gormless/tree/service-based

IMPORTANT NOTE: adding logSql:true shows that a Person.get() call is attempted prior to calling the update(Person person) controller method.  This is unexpected behavior as personService was completely bipassed.  personService.get() should have been called instead.

Completely Remove GORM by:

	* Change Person to implement grails.validation.Validateable
	* Remove hibernate from build.gradle and from application.yml
	* Change g:link in show.gsp to not use resource

Try to change name of Graeme Rocher
http://localhost:8080/person/edit/1

Result: No change. notFound() due to controller update method receiving null person object.
All Other CRUD Methods Work
