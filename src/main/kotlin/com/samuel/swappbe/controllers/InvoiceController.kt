package com.samuel.swappbe.controllers

import InvoiceDto
import com.samuel.swappbe.services.InvoiceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class InvoiceController {

    @Autowired
    lateinit var invoiceService: InvoiceService

    @GetMapping("/invoice-table")
    fun getInvoiceTable(): List<InvoiceDto> {
        return invoiceService.getInvoices()
    }
}