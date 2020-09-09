package expenses

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class CategorySpec extends Specification implements DomainUnitTest<Category> {

    def steuer
    def freizeit

    def setup() {
        //setup two categories
        steuer   = save(new Category(name: "Steuern",  description: "Alle möglichen Steuern ausser Mehrwertsteuer"))
        freizeit = save(new Category(name: "Freizeit", description: "Alle Ausgaben für Freizeitaktivitäten"))
    }

    def cleanup() {
        Category.list()*.delete()
    }

    void "toString() test"() {

        when: "a new category gets created"
        Category category = new Category(name: "Freizeit", description: "Alle möglichen Steuern ausser Mehrwertsteuer")

        then: "the toString() Method equals the name"
        category.toString() == "Freizeit"
    }

    static <Domain> Domain save(Domain domainObject) {
        domainObject.save(failOnError: true, flush:true)
    }
}
