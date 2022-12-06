package com.samuel.swappbe.repositories

import com.samuel.swappbe.models.Invoice
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface InvoiceRepository : JpaRepository<Invoice, Long> {
    fun findAllByIdIn(ids: List<Long>): MutableList<Invoice>
}