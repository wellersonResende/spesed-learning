package br.com.well.spesedlearning.model

import jakarta.persistence.*
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "content")
data class Content(val name: String, val description: String, var repetition: Int = 0, val creationDate: LocalDate = LocalDate.now(),@ManyToOne @JoinColumn(name = "subject_id") val subject: Subject) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID = UUID.randomUUID()
    override fun toString(): String {
        return "Content(name='$name', description='$description')"
    }
    val nextRepetition: LocalDate
        get() = when (repetition) {
            0 -> creationDate
            1 -> creationDate.plusDays(1)
            2 -> creationDate.plusDays(6)
            else -> creationDate.plusDays(30)
        }
}
