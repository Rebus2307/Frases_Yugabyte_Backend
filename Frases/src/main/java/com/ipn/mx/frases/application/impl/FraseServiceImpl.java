package com.ipn.mx.frases.application.impl;

import com.ipn.mx.frases.application.FraseService;
import com.ipn.mx.frases.domain.entities.Frase;
import com.ipn.mx.frases.domain.repository.FraseRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

@Service
public class FraseServiceImpl implements FraseService {

    @Autowired
    private FraseRepository dao;

    @Override
    @Transactional(readOnly = true)
    public List<Frase> findAll() {
        return dao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Frase findById(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Frase save(Frase frase) {
        return dao.save(frase);
    }

    @Override
    public void deleteById(Long id) {
        dao.deleteById(id);
    }

    @Override
    public ByteArrayInputStream exportarFrase(List<Frase> frases) {
        Document documento = new Document();
        ByteArrayOutputStream salida = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(documento, salida);
            documento.open();
            Font tipoLetra = FontFactory.getFont(FontFactory.HELVETICA, 14, BaseColor.BLACK);
            Paragraph parrafo = new Paragraph("Lista de frases", tipoLetra);
            documento.add(parrafo);
            documento.add(Chunk.NEWLINE);

            Font tipoLetraTexto = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLUE);
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10);
            table.setSpacingAfter(10);
            Stream.of("id frase", "Autor", "Texto").forEach(encabezados -> {
                PdfPCell encabezadosTabla = new PdfPCell(new Phrase(encabezados));
                Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 9, BaseColor.BLUE);
                encabezadosTabla.setHorizontalAlignment(Element.ALIGN_CENTER);
                encabezadosTabla.setVerticalAlignment(Element.ALIGN_CENTER);
                encabezadosTabla.setPhrase(new Phrase(encabezados, headerFont));
                table.addCell(encabezadosTabla);

            });
        for (Frase f : frases) {
            PdfPCell celdaIdFrase = new PdfPCell(new Phrase(String.valueOf(f.getId()), tipoLetraTexto));
            celdaIdFrase.setHorizontalAlignment(Element.ALIGN_CENTER);
            celdaIdFrase.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celdaIdFrase.setPadding(1);
            table.addCell(celdaIdFrase);

            PdfPCell celdaAutor = new PdfPCell(new Phrase(f.getAutor(), tipoLetraTexto));
            celdaAutor.setHorizontalAlignment(Element.ALIGN_CENTER);
            celdaAutor.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celdaAutor.setPadding(1);
            table.addCell(celdaAutor);

            PdfPCell celdaFrase = new PdfPCell(new Phrase(f.getTexto(), tipoLetraTexto));
            celdaFrase.setHorizontalAlignment(Element.ALIGN_CENTER);
            celdaFrase.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celdaFrase.setPadding(1);
            table.addCell(celdaFrase);
        }
        documento.add(table);
        documento.close();

        } catch (DocumentException e){
            System.err.println(e.getMessage());
        }
        return new ByteArrayInputStream(salida.toByteArray());
    }

}
