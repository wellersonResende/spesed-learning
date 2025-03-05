package br.com.well.spesedlearning.model

import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "subjects")
data class Subject(val name:String, @OneToMany val contentList: MutableList<Content>){
    @Id
    val id: UUID = UUID.randomUUID()

}
