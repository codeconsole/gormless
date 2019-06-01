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

The modification:

* Modify PersonService to not use Gorm

	grails run-app

Try to change name of Graeme Rocher
http://localhost:8080/person/edit/1

Result: No change. notFound() due to controller update method receiving null person object.
All Other CRUD Methods Work

Completely Remove GORM by:

	* Change Person to implement grails.validation.Validateable
	* Remove hibernate from build.gradle and from application.yml
	* Change g:link in show.gsp to not use resource

Try to change name of Graeme Rocher
http://localhost:8080/person/edit/1

Result: No change. notFound() due to controller update method receiving null person object.
All Other CRUD Methods Work
