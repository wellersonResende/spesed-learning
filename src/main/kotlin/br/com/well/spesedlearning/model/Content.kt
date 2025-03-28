package br.com.well.spesedlearning.model

import jakarta.persistence.*
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "content")
data class Content(val name: String, val description: String, var repetition: Int = 0,val subjectID: UUID, val radomMusics: String? = null) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID = UUID.randomUUID()
    override fun toString(): String {
        return "Content(name='$name', description='$description')"
    }

    @Column(name = "creation_date")
    var creationDate: LocalDate? = null

    @get:Transient
    val nextRepetition: LocalDate?
        get() = creationDate?.let { date ->
            when (repetition) {
                0 -> date
                1 -> date.plusDays(1)
                2 -> date.plusDays(6)
                else -> date.plusDays(30)
            }
        }
    @PrePersist
    fun onCreate() {
        creationDate = LocalDate.now()
    }
}
