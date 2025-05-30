package br.com.well.spesedlearning.controller

import br.com.well.spesedlearning.model.Content
import br.com.well.spesedlearning.model.Subject
import br.com.well.spesedlearning.repositoies.ContentRepository
import br.com.well.spesedlearning.repositoies.SubjectRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import java.util.Optional

@RestController
@RequestMapping(value = ["/api/spesedlearning"])


class SpacedController(
    val subjectRepository: SubjectRepository,
    private val contentRepository: ContentRepository
){
    @GetMapping
    fun getAllSubject(): List<Subject> {
        val subjects = subjectRepository.findAll()
        return subjects
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
    @PostMapping("/subject")
    fun createSubject(@RequestBody subject: Subject): ResponseEntity<Subject> {
        val savedSubject = subjectRepository.save(subject)
        return ResponseEntity.ok(savedSubject)
    }
    @PatchMapping("/content/{uuid}")
    fun updateRepetionCounter(@PathVariable uuid: UUID): ResponseEntity<Subject> {
        val content: Optional<Content> = contentRepository.findById(uuid)
        return if (content.isPresent) {
            val contentToUpdate = content.get()
            contentToUpdate.repetition += 1
            contentRepository.save(contentToUpdate)
            ResponseEntity.ok(contentToUpdate.subject)
        } else {
            ResponseEntity.notFound().build()
        }

    }
}