package com.samuel.swappbe.services

import InvoiceDto
import com.samuel.swappbe.models.Invoice
import com.samuel.swappbe.repositories.InvoiceRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class InvoiceService() {

    @Autowired
    protected lateinit var invoiceRepository: InvoiceRepository

    fun getInvoices(): List<InvoiceDto> {
        return invoiceRepository.findAll().map { InvoiceDto(it.id, it.name, it.amount) }
    }
}