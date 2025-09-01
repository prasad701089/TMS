package com.TollManagementSystem.TMS.controller;

import com.TollManagementSystem.TMS.entity.TollPrice;
import com.TollManagementSystem.TMS.repository.TollPriceRepository;

import com.TollManagementSystem.TMS.entity.TollTicket;
import com.TollManagementSystem.TMS.repository.TollTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;


import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

// import javax.swing.text.Document; // Removed incorrect import

@Controller
public class InvoiceDownloadController {

	private final TollTicketRepository tollTicketRepository;
	private final TollPriceRepository tollPriceRepository;

	@Autowired
	public InvoiceDownloadController(TollTicketRepository tollTicketRepository, TollPriceRepository tollPriceRepository) {
		this.tollTicketRepository = tollTicketRepository;
		this.tollPriceRepository = tollPriceRepository;
	}

	@GetMapping("/download_invoice")
	public void downloadInvoice(HttpServletResponse response) throws IOException {
		response.setContentType("application/pdf");
		response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=invoice.pdf");

		// Fetch latest TollTicket
		TollTicket ticket = tollTicketRepository.findAll()
				.stream()
				.reduce((first, second) -> second) // get last
				.orElse(null);

		com.lowagie.text.Document document = new com.lowagie.text.Document();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			PdfWriter.getInstance(document, baos);
			document.open();
			Paragraph header = new Paragraph("Toll Payment Receipt", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 22));
			header.setAlignment(Element.ALIGN_CENTER);
			document.add(header);
			document.add(new Paragraph("\n"));
			if (ticket != null) {
				PdfPTable table = new PdfPTable(2);
				table.setWidthPercentage(90);
				table.setSpacingBefore(10f);
				table.setSpacingAfter(10f);

				Font labelFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
				Font valueFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

				table.addCell(new PdfPCell(new Phrase("Date", labelFont)));
				table.addCell(new PdfPCell(new Phrase(ticket.getCreatedAt() != null ? ticket.getCreatedAt().toString() : "", valueFont)));
				table.addCell(new PdfPCell(new Phrase("State", labelFont)));
				table.addCell(new PdfPCell(new Phrase(ticket.getState() != null ? ticket.getState() : "", valueFont)));
				table.addCell(new PdfPCell(new Phrase("District", labelFont)));
				table.addCell(new PdfPCell(new Phrase(ticket.getDistrict() != null ? ticket.getDistrict() : "", valueFont)));
				table.addCell(new PdfPCell(new Phrase("NH Number", labelFont)));
				table.addCell(new PdfPCell(new Phrase(ticket.getNhNumber() != null ? ticket.getNhNumber() : "", valueFont)));
				table.addCell(new PdfPCell(new Phrase("Toll Name", labelFont)));
				table.addCell(new PdfPCell(new Phrase(ticket.getTollName() != null ? ticket.getTollName() : "", valueFont)));
				table.addCell(new PdfPCell(new Phrase("Toll KM", labelFont)));
				table.addCell(new PdfPCell(new Phrase(ticket.getTollKm() != null ? ticket.getTollKm() : "", valueFont)));
				table.addCell(new PdfPCell(new Phrase("Section", labelFont)));
				table.addCell(new PdfPCell(new Phrase(ticket.getSection() != null ? ticket.getSection() : "", valueFont)));
				table.addCell(new PdfPCell(new Phrase("Vehicle Type", labelFont)));
				table.addCell(new PdfPCell(new Phrase(ticket.getVehicleType() != null ? ticket.getVehicleType() : "", valueFont)));
				table.addCell(new PdfPCell(new Phrase("Plan", labelFont)));
				table.addCell(new PdfPCell(new Phrase(ticket.getPlan() != null ? ticket.getPlan() : "", valueFont)));
				table.addCell(new PdfPCell(new Phrase("Vehicle Number", labelFont)));
				table.addCell(new PdfPCell(new Phrase(ticket.getVehicleNumber() != null ? ticket.getVehicleNumber() : "", valueFont)));
				table.addCell(new PdfPCell(new Phrase("Amount", labelFont)));
				// Fetch price based on vehicle type and plan
				String priceStr = "N/A";
				if (ticket.getVehicleType() != null && ticket.getPlan() != null) {
					TollPrice price = tollPriceRepository.findByVehicleTypeAndPlan(ticket.getVehicleType(), ticket.getPlan()).orElse(null);
					if (price != null) {
						priceStr = "₹" + price.getPrice();
					}
				}
				table.addCell(new PdfPCell(new Phrase(priceStr, valueFont)));

				document.add(table);
				document.add(new Paragraph("\n"));
				Paragraph thanks = new Paragraph("Thank you for your payment!", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14));
				thanks.setAlignment(Element.ALIGN_CENTER);
				document.add(thanks);
			} else {
				document.add(new Paragraph("No ticket found."));
			}
			document.close();
		} catch (Exception e) {
			document.close();
			throw new IOException("Error generating PDF", e);
		}
		response.getOutputStream().write(baos.toByteArray());
		response.getOutputStream().flush();
	}
}
