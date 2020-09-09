package expenses

import auth.*
import grails.testing.gorm.DomainUnitTest
import org.apache.catalina.startup.Bootstrap
import spock.lang.Specification

class ExpenseSpec extends Specification implements DomainUnitTest<Expense> {

    User testUser, adminUser
    Category steuer, freizeit

    Date today      = new Date().clearTime();
    Date yesterday  = new Date().plus(-1).clearTime()
    Date lastYear   = new Date().plus(-365).clearTime()

    def setup() {
        //create different dates for exenses
        testUser    = User.findOrSaveWhere(username: '2', fullname: 'test', password: 'test')
        adminUser   = User.findOrSaveWhere(username: '1', fullname: 'test', password: 'admin')

        //category seed
        steuer   = save(new Category(name: "Steuern",  description: "Alle möglichen Steuern ausser Mehrwertsteuer"))
        freizeit = save(new Category(name: "Freizeit", description: "Alle Ausgaben für Freizeitaktivitäten"))
        def expense = save(new Expense(description: "Umsatzsteuer",   category: steuer,    date: lastYear, amount: 300,   user: adminUser))
        def expense2 = save(new Expense(description: "andere Steuer",   category: steuer,    date: yesterday, amount: 300,   user: adminUser))
        def expense3 = save(new Expense(description: "andere Steuer",   category: freizeit,    date: today, amount: 300,   user: adminUser))
    }

    def cleanup() {
        Expense.list()*.delete()
    }

    void "toString test"() {
        when: "a new expense gets created"
        def expense = save(new Expense(description: "Umsatzsteuer",   category: steuer,    date: lastYear, amount: 300,   user: adminUser))

        then: "the toString() method equals the description"
        expense.toString() == "Umsatzsteuer"
    }

    static <Domain> Domain save(Domain domainObject) {
        domainObject.save(failOnError: true, flush:true)
    }
}
