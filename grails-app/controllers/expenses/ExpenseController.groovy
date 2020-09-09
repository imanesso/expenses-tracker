package expenses

import auth.*
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

import static org.springframework.http.HttpStatus.*

@Secured([Role.ROLE_ADMIN, Role.ROLE_USER])
class ExpenseController {

    ExpenseService expenseService
    SpringSecurityService springSecurityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def currentUser = springSecurityService.currentUser
        //only load expenses of the current user
        respond expenseService.findExpensesByUser(currentUser, params), model: [expenseCount: expenseService.count()]
    }

    def loadByDate() {
        def date = new Date().clearTime()
        params.max = Math.min(max ?: 10, 100)
        //only load expenses of the current user
        respond Expense.findAllByDate(date), model: [expenseCount: expenseService.count()]
    }

    def show(Long id) {
        respond expenseService.get(id)
    }

    def create() {
        respond new Expense(params)
    }

    def save(Expense expense) {
        if (expense == null) {
            notFound()
            return
        }

        try {
            expenseService.save(expense)
        } catch (ValidationException e) {
            respond expense.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'expense.label', default: 'Expense'), expense.id])
                redirect expense
            }
            '*' { respond expense, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond expenseService.get(id)
    }

    def update(Expense expense) {
        if (expense == null) {
            notFound()
            return
        }

        try {
            expenseService.save(expense)
        } catch (ValidationException e) {
            respond expense.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'expense.label', default: 'Expense'), expense.id])
                redirect expense
            }
            '*'{ respond expense, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        expenseService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'expense.label', default: 'Expense'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    def analyse(){

        def currentUser = springSecurityService.currentUser
        ExpenseAnalysisService expenseAnalysisService = new ExpenseAnalysisService()

        //get data to pass to view
        def catExpenses      = expenseAnalysisService.getCategoryExpenses(currentUser)
        def worstExpense    = expenseAnalysisService.getWorstExpense(currentUser)


        render(view: "analyse", model: [worstExpense: worstExpense,categories:catExpenses])
    }

    def statement(){
        params.max = Math.min(10, 100)
        Date startDate = params.startDate

        def currentUser = springSecurityService.currentUser
        def list

        if (startDate != null) {
            //calculate the start and end date
            Calendar cal = Calendar.getInstance()
            cal.setTime(startDate)
            cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            Date endDate = cal.getTime()

            list = Expense.findAllByUserAndDateBetween(currentUser, startDate, endDate)
        }else{
            list = expenseService.findExpensesByUser(currentUser, params)
        }

        render(view: "statement", model: [expenseList: list,expenseCount: Expense.count()])
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'expense.label', default: 'Expense'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
