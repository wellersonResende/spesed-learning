package br.com.well.spesedlearning.controller

import br.com.well.spesedlearning.model.Subject
import br.com.well.spesedlearning.repositoies.SubjectRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import java.util.Optional

@RestController
@RequestMapping(value = ["/api/spesedlearning"])


class SpacedController (val subjectRepository: SubjectRepository){
    @GetMapping
    fun getSpaced(): String {
        return "Spaced"
    }
    @GetMapping("/subject/{uuid}")
    fun getSubject(@PathVariable uuid: UUID): ResponseEntity<Subject> {
        val subject: Optional<Subject> = subjectRepository.findById(uuid)
        return if (subject.isPresent) {
            ResponseEntity.ok(subject.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }
}