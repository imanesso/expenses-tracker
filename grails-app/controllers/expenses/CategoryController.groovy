package expenses

import auth.Role
import grails.plugin.springsecurity.annotation.Secured

@Secured([Role.ROLE_ADMIN])
class CategoryController {
    static scaffold = Category
}
