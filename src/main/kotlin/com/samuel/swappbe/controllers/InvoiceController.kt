package com.samuel.swappbe.controllers

import InvoiceDto
import com.samuel.swappbe.services.InvoiceService
import com.samuel.swappbe.services.PDF_NAME
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.InputStreamResource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class InvoiceController {

    @Autowired
    lateinit var invoiceService: InvoiceService

    @GetMapping("/invoices-table")
    fun getInvoicesTable(): List<InvoiceDto> {
        return invoiceService.getInvoices()
    }

    @PostMapping("/invoices-pdf", produces = [MediaType.APPLICATION_PDF_VALUE])
    fun getInvoicesPDF(@RequestBody body: InvoiceRequestBody): ResponseEntity<InputStreamResource> {
        val bis = invoiceService.getInvoicesPDFInputStream(body.invoiceIds)
        val headers = HttpHeaders()
        headers.add("Content-Disposition", "inline; filename=$PDF_NAME")
        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
            .body(InputStreamResource(bis))
    }
}

class InvoiceRequestBody(val invoiceIds: List<Long>)