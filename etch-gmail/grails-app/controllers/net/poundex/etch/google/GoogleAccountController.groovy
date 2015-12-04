package net.poundex.etch.google

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class GoogleAccountController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond GoogleAccount.list(params), model:[googleAccountCount: GoogleAccount.count()]
    }

    def show(GoogleAccount googleAccount) {
        respond googleAccount
    }

    def create() {
        respond new GoogleAccount(params)
    }

    @Transactional
    def save(GoogleAccount googleAccount) {
        if (googleAccount == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (googleAccount.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond googleAccount.errors, view:'create'
            return
        }

        googleAccount.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'googleAccount.label', default: 'GoogleAccount'), googleAccount.id])
                redirect googleAccount
            }
            '*' { respond googleAccount, [status: CREATED] }
        }
    }

    def edit(GoogleAccount googleAccount) {
        respond googleAccount
    }

    @Transactional
    def update(GoogleAccount googleAccount) {
        if (googleAccount == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (googleAccount.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond googleAccount.errors, view:'edit'
            return
        }

        googleAccount.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'googleAccount.label', default: 'GoogleAccount'), googleAccount.id])
                redirect googleAccount
            }
            '*'{ respond googleAccount, [status: OK] }
        }
    }

    @Transactional
    def delete(GoogleAccount googleAccount) {

        if (googleAccount == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        googleAccount.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'googleAccount.label', default: 'GoogleAccount'), googleAccount.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'googleAccount.label', default: 'GoogleAccount'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
