package gormless

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