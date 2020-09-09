package expenses

import auth.User

class Expense {
    String description
    BigDecimal amount
    Date date
    static belongsTo = [user: User, category: Category]

    String toString(){
        "${description}"
    }

    static constraints = {
        description nullable: false
        amount nullable: false
        date nullable: false
    }
}
