package br.com.well.spesedlearning.repositoies

import br.com.well.spesedlearning.model.Content
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ContentRepository: JpaRepository<Content, UUID> {
}