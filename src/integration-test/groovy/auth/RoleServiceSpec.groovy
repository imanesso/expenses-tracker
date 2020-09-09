package auth

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class RoleServiceSpec extends Specification {

    RoleService roleService
    SessionFactory sessionFactory

    private Long setupData() {


            Role.findOrCreateWhere(authority: "ROLE1")
            Role.findOrCreateWhere(authority: "ROLE2")
            Role.findOrCreateWhere(authority: "ROLE3")
            Role.findOrCreateWhere(authority: "ROLE4")
            Role role = Role.findOrCreateWhere(authority: "ROLE_ADMIN")
            role.id

    }


    void "test list"() {
        setupData()

        when:
        List<Role> roleList = roleService.list(max: 2, offset: 2)

        then:
        roleList.size() == 0
    }

    void "test count"() {
        setupData()

        expect:
        roleService.count() == 0
    }


    void "test save"() {
        when:
        Role role = new Role(authority: "ROLE1234")
        roleService.save(role)

        then:
        role != null
    }
}
