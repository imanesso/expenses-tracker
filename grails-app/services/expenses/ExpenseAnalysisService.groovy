package expenses

import auth.User
import groovy.transform.*
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.SpringSecurityService

@Transactional
class ExpenseAnalysisService {

    def getCategoryExpenses(User user) {
        //create map for all expenses and add list of expenses
        def list = Category.list()

        Map<Category,List<Expense>> map = new HashMap<Category, List<Expense>>()

        for(Category cat: list){
            def expenses = Expense.findAllByUserAndCategory(user, cat)
            //sort by descending order
            expenses.sort(){-it.amount}
            if (expenses.size()> 0) {
                map.put(cat, expenses) // only put expenses with value
            }
        }
        //sort map by size of value
        map.sort(){-it.value.size()}

        return map
    }

    def getWorstExpense(User user){
        //sort desc and get the first
        def list = Expense.findAllByUser(user).sort {it.amount}
        Expense worst = list.last()

        return worst
    }
}
