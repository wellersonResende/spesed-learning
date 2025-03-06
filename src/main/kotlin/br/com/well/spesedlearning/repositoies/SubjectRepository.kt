package br.com.well.spesedlearning.repositoies

import br.com.well.spesedlearning.model.Subject
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component
import java.util.Optional
import java.util.UUID

@Component
interface SubjectRepository: JpaRepository<Subject, UUID>{
}