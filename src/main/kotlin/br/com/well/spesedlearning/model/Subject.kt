package br.com.well.spesedlearning.model

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "subjects")
data class Subject(val name:String, @OneToMany val contentList: MutableList<Content>){
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID = UUID.randomUUID()

}
