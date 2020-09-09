package webec
import auth.*
import expenses.*
import grails.util.Environment

class BootStrap {

    def init = { servletContext ->
        if (Environment.current == Environment.PRODUCTION) return; // guard clause
        if (Environment.current == Environment.TEST) return; // guard clause

        //seed user and admin role
        def userRole    = Role.findOrSaveWhere(authority: Role.ROLE_USER)
        def adminRole   = Role.findOrSaveWhere(authority: Role.ROLE_ADMIN)

        // seed test and admin user
        def testUser    = User.findOrSaveWhere(username: 'test', fullname: 'test', password: 'test')
        def adminUser   = User.findOrSaveWhere(username: 'admin', fullname: 'test', password: 'admin')
        save(new UserRole(user: testUser, role: userRole))
        save(new UserRole(user: adminUser, role: adminRole))

        //create different dates for exenses
        Date today = new Date().clearTime();
        Date yesterday = new Date().plus(-1).clearTime()
        Date lastYear = new Date().plus(-365).clearTime()

        //category seed
        Category steuer         = save(new Category(name: "Steuern",        description: "Alle möglichen Steuern ausser Mehrwertsteuer"))
        Category versicherung   = save(new Category(name: "Versicherung",   description: "Ausgaben für Versicherungen jeglicher Art"))
        Category wohnen         = save(new Category(name: "Wohnen",         description: "Alle Ausgaben, welche die Wohnsituation betreffen"))
        Category verkehr        = save(new Category(name: "Verkehr",        description: "Alle Ausgaben, um von einem Ort zum Nächsten zu kommen"))
        Category freizeit       = save(new Category(name: "Freizeit",        description: "Alle Ausgaben für Freizeitaktivitäten"))
        Category lebensmittel   = save(new Category(name: "Lebensmittel",   description: "Alle Ausgaben für den alltäglichen Einkauf"))
        Category gesundheit     = save(new Category(name: "Gesundheit",     description: "Alle Ausgaben welche die Gesundheit betreffen"))


        //Expenses seed
        save(new Expense(description: "Umsatzsteuer",            category: steuer,       date: today,        amount: 350,    user: adminUser))
        save(new Expense(description: "Umsatzsteuer",            category: steuer,       date: lastYear,     amount: 300,    user: adminUser))
        save(new Expense(description: "Umsatzsteuer",            category: steuer,       date: lastYear,     amount: 300,    user: testUser))
        save(new Expense(description: "Erbschaftssteuer",        category: steuer,       date: today,        amount: 1230,   user: testUser))
        save(new Expense(description: "Motorfahrzeugsteuer",     category: steuer,       date: lastYear,     amount: 120,    user: testUser))
        save(new Expense(description: "Wocheneinkauf",           category: lebensmittel, date: today,        amount: 95 ,    user: testUser))
        save(new Expense(description: "Metzger",                 category: lebensmittel, date: today,        amount: 154 ,    user: testUser))
        save(new Expense(description: "Bäcker",                  category: lebensmittel, date: today,        amount: 33 ,    user: testUser))
        save(new Expense(description: "Miete",                   category: wohnen,       date: today,        amount: 2200 ,    user: testUser))
        save(new Expense(description: "Nebenkosten",             category: wohnen,       date: today,        amount: 300,    user: testUser))
        save(new Expense(description: "Neuer Türrahmen",         category: wohnen,       date: today,        amount: 300,    user: testUser))
        save(new Expense(description: "Fitnessabo",              category: freizeit,     date: lastYear,     amount: 233 ,   user: testUser))
        save(new Expense(description: "Hobby",                   category: freizeit,     date: yesterday,    amount: 34 ,    user: testUser))
        save(new Expense(description: "Dinner",                  category: freizeit,     date: yesterday,    amount: 34 ,    user: testUser))
        save(new Expense(description: "Krankenversicherung",     category: versicherung, date: yesterday,    amount: 430 ,    user: testUser))
        save(new Expense(description: "Rechtsschutzversicherung",category: versicherung, date: yesterday,    amount: 54 ,    user: testUser))
        save(new Expense(description: "Fahrzeugversicherung",    category: versicherung, date: yesterday,    amount: 54 ,    user: testUser))
        save(new Expense(description: "Unfallversicherung",      category: versicherung, date: yesterday,    amount: 54 ,    user: testUser))
    }

    def destroy = {
    }

    static <Domain> Domain save(Domain domainObject) {
        domainObject.save(failOnError: true, flush:true)
    }

}
