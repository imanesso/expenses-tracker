package auth

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class UserServiceSpec extends Specification {

    UserService userService
    SessionFactory sessionFactory
    User testUser, adminUser

    private Long setupData() {

        testUser    = User.findOrSaveWhere(username: '2', fullname: 'test', password: 'test')
        adminUser   = User.findOrSaveWhere(username: '1', fullname: 'test', password: 'admin')
        User user   = User.findOrSaveWhere(username: '3', fullname: 'user', password: 'user')

        user.id
    }

    void "test get"() {
        setupData()

        expect:
        userService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<User> userList = userService.list(max: 2, offset: 2)

        then:
        userList.size() == 1
    }

    void "test count"() {
        setupData()

        expect:
        userService.count() == 3
    }

    void "test delete"() {
        Long userId = setupData()

        expect:
        userService.count() == 3

        when:
        userService.delete(userId)
        sessionFactory.currentSession.flush()

        then:
        userService.count() == 2
    }

    void "test save"() {
        when:
        User user = new User(username: '2327', fullname: 'test', password: 'test')
        userService.save(user)

        then:
        user.id != null
    }
}
