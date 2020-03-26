package myproject



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PersonneController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Personne.list(params), model:[personneInstanceCount: Personne.count()]
    }

    def show(Personne personneInstance) {
        respond personneInstance
    }

    def create() {
        respond new Personne(params)
    }

    @Transactional
    def save(Personne personneInstance) {
        if (personneInstance == null) {
            notFound()
            return
        }

        if (personneInstance.hasErrors()) {
            respond personneInstance.errors, view:'create'
            return
        }

        personneInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'personne.label', default: 'Personne'), personneInstance.id])
                redirect personneInstance
            }
            '*' { respond personneInstance, [status: CREATED] }
        }
    }

    def edit(Personne personneInstance) {
        respond personneInstance
    }

    @Transactional
    def update(Personne personneInstance) {
        if (personneInstance == null) {
            notFound()
            return
        }

        if (personneInstance.hasErrors()) {
            respond personneInstance.errors, view:'edit'
            return
        }

        personneInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Personne.label', default: 'Personne'), personneInstance.id])
                redirect personneInstance
            }
            '*'{ respond personneInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Personne personneInstance) {

        if (personneInstance == null) {
            notFound()
            return
        }

        personneInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Personne.label', default: 'Personne'), personneInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'personne.label', default: 'Personne'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
