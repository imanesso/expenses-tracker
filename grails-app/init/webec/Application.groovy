package webec

import auth.*
import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration
import grails.plugin.springsecurity.annotation.Secured

class Application extends GrailsAutoConfiguration {

    static void main(String[] args) {
        GrailsApp.run(Application, args)
    }
}