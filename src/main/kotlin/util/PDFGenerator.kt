package util

import InvoiceDto
import com.itextpdf.text.*
import com.itextpdf.text.pdf.PdfPCell
import com.itextpdf.text.pdf.PdfPTable
import com.itextpdf.text.pdf.PdfWriter
import org.slf4j.LoggerFactory
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream


object PDFGenerator {
    private val logger = LoggerFactory.getLogger(PDFGenerator::class.java)
    fun generateInvoicesPDF(invoiceDtos: List<InvoiceDto>): ByteArrayInputStream {
        val document = Document()
        val out = ByteArrayOutputStream()
        try {
            PdfWriter.getInstance(document, out)
            document.open()
            val table = PdfPTable(3)
            listOf("ID", "Name", "Amount").forEach { headerTitle: String? ->
                val header = PdfPCell()
                header.horizontalAlignment = Element.ALIGN_CENTER
                header.phrase = Phrase(headerTitle)
                table.addCell(header)
            }
            for (invoiceDto in invoiceDtos) {
                val idCell = PdfPCell(Phrase(invoiceDto.id.toString()))
                table.addCell(idCell)
                val nameCell = PdfPCell(Phrase(invoiceDto.name))
                table.addCell(nameCell)
                val amoutCell = PdfPCell(Phrase(invoiceDto.amount.toString()))
                table.addCell(amoutCell)
            }
            val emptyCell = PdfPCell(Phrase(""))
            table.addCell(emptyCell)
            table.addCell(emptyCell)
            val totalAmount = invoiceDtos.sumOf { it.amount }
            val totalCell = PdfPCell(Phrase("Total: $totalAmount"))
            table.addCell(totalCell)
            document.add(table)
            document.close()
        } catch (e: DocumentException) {
            logger.error(e.toString())
        }
        return ByteArrayInputStream(out.toByteArray())
    }
}