package expenses

import auth.User
import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ExpenseServiceSpec extends Specification {

    ExpenseService expenseService
    SessionFactory sessionFactory
    User testUser, adminUser
    Category steuer, freizeit
    BigDecimal amount = new BigDecimal(30)//grails could not implicitly cast number to decimal

    Date today      = new Date().clearTime();
    Date yesterday  = new Date().plus(-1).clearTime()
    Date lastYear   = new Date().plus(-365).clearTime()

    private Long setupData() {
        //user seed
        testUser = User.findOrSaveWhere(username: 'test', fullname: 'test', password: 'test')
        adminUser = User.findOrSaveWhere(username: 'admin', fullname: 'test', password: 'admin')
        //category seed
        steuer = Category.findOrSaveWhere(name: "Steuern", description: "Alle möglichen Steuern ausser Mehrwertsteuer")
        freizeit = Category.findOrSaveWhere(name: "Freizeit", description: "Alle Ausgaben für Freizeitaktivitäten")

        //expenses setup
        if (Expense.list().size() == 0) {

            Expense.findOrSaveWhere(description: "steuer1", category: steuer, date: lastYear, amount: amount, user: adminUser)
            Expense.findOrSaveWhere(description: "steuer2", category: steuer, date: lastYear, amount: amount, user: testUser)
            Expense expense = Expense.findOrSaveWhere(description: "Gym Abo", category: freizeit, date: today, amount: amount, user: adminUser)
            expense.id
        }
    }

    void "test get"() {
        setupData()

        expect:
        expenseService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Expense> expenseList = expenseService.list(max: 2, offset: 2)

        then:
        expenseList.size() == 1
    }

    void "test count"() {
        setupData()

        expect:
        expenseService.count() == 3
    }

    void "test delete"() {
        Long expenseId = setupData()

        expect:
        expenseService.count() == 3

        when:
        expenseService.delete(expenseId)
        sessionFactory.currentSession.flush()

        then:
        expenseService.count() == 2
    }

    void "test save"() {
        when:
        Expense expense = Expense.findOrSaveWhere(description: "steuer1", category: steuer, date: lastYear, amount: amount, user: adminUser)

        then:
        expense != null
    }
}
