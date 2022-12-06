package com.samuel.swappbe.services

import InvoiceDto
import com.samuel.swappbe.repositories.InvoiceRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import util.PDFGenerator.generateInvoicesPDF
import java.io.ByteArrayInputStream


const val PDF_NAME = "Invoices.pdf"

@Service
class InvoiceService() {

    @Autowired
    protected lateinit var invoiceRepository: InvoiceRepository


    fun getInvoices(): List<InvoiceDto> {
        return invoiceRepository.findAll().map { InvoiceDto(it.id, it.name, it.amount) }
    }

    fun getInvoicesPDFInputStream(invoiceIds: List<Long>): ByteArrayInputStream {
        val invoices =
            invoiceRepository.findAllByIdIn(invoiceIds).map { InvoiceDto(it.id, it.name, it.amount) }
        return generateInvoicesPDF(invoices)
    }
}