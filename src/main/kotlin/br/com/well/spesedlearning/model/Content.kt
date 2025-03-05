package br.com.well.spesedlearning.model

import jakarta.persistence.*
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "content")
data class Content (val name: String, val description: String, var repetition: Int = 0, val creationDate: LocalDate = LocalDate.now(),@ManyToOne val string: Subject) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID = UUID.randomUUID()
    override fun toString(): String {
        return "Content(name='$name', description='$description')"
    }
}
