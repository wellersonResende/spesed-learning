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
    @PostMapping("/content")
    fun createContest(@RequestBody content: Content): ResponseEntity<Content> {
       val subjectId = content.subjectID ?: return ResponseEntity.notFound().build()
        val subject = subjectRepository.findById(subjectId)
        return if (subject.isPresent) {
            val savedContent = contentRepository.save(content)
            subject.get().contentList.add(savedContent)
            subjectRepository.save(subject.get())
            ResponseEntity.ok(savedContent)
        } else {
            ResponseEntity.notFound().build()
        }
    }
    @PatchMapping("/content/{uuid}")
    fun updateRepetionCounter(@PathVariable uuid: UUID): ResponseEntity<Content> {
        val contentOption: Optional<Content> = contentRepository.findById(uuid)
        return if (contentOption.isPresent) {
          val content = contentOption.get()
            content.repetition++
          val savedContent = contentRepository.save(content)
            ResponseEntity.ok(savedContent)
        } else {
            ResponseEntity.notFound().build()
        }

    }
}