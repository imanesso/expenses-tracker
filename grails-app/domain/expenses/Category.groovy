package expenses

class Category {

    String name
    String description
    static hasMany = [expenses: Expense]

    String toString(){
        "${name}"
    }

    static constraints = {
        name unique: true, blank: false
        description nullable: true, blank: true
        expenses display: false
    }
}
