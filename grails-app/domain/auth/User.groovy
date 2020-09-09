package auth

import expenses.Expense
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class User implements Serializable {

    private static final long serialVersionUID = 1

    String username
    String fullname
    String password
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired
    static hasMany = [expenses: Expense]

    Set<Role> getAuthorities() {
        (UserRole.findAllByUser(this) as List<UserRole>)*.role as Set<Role>
    }

    String toString(){
        "${username}"
    }

    static constraints = {
        username nullable: false, blank: false, unique: true
        fullname nullable: false, blank: false
        password nullable: false, blank: false, password: true
        expenses display: false
    }

    static mapping = {
	    password column: '`password`'
    }
}
