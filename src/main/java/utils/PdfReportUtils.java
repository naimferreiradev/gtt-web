package utils;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.AreaBreakType;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PdfReportUtils {

    private static Document document;
    private static PdfDocument pdf;
    private static String caminhoPdf;

    public static void iniciar(String nomeCenario) {

        try {
            caminhoPdf = "reports/pdf/" + nomeCenario + ".pdf";
            new File("reports/pdf").mkdirs();

            PdfWriter writer = new PdfWriter(caminhoPdf);
            pdf = new PdfDocument(writer);
            document = new Document(pdf);


            // 🔝 LOGO DA EMPRESA
            Image logo = new Image(
                    ImageDataFactory.create("src/test/resources/logo.jpg"));

            logo.setWidth(120);                 // tamanho do logo
            logo.setMarginBottom(10);
            logo.setHorizontalAlignment(HorizontalAlignment.CENTER); // centralizado

            document.add(logo);

            document.add(new Paragraph("Relatório de Automação")
                    .setBold().setFontSize(16)
                    .setTextAlignment(TextAlignment.CENTER));

            document.add(new Paragraph("Cenário: " + nomeCenario));
            document.add(new Paragraph("Data: " +
                    LocalDateTime.now()
                            .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
            ));
//            document.add(new Paragraph(" "));

            String autor = PropertiesReader.get("autor.execucao");
            document.add(new Paragraph("Autor: " + autor));

            String ambiente = PropertiesReader.get("ambiente.execucao");
            document.add( new Paragraph("Ambiente: " + ambiente));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));


        } catch (Exception e) {
            throw new RuntimeException("Erro ao iniciar PDF", e);
        }
    }

    public static void adicionarStep(String nomeStep, String caminhoImagem) throws MalformedURLException {

        document.add(new Paragraph(nomeStep).setTextAlignment(TextAlignment.CENTER).setFontSize(12)
                .setMarginBottom(30));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));

        Image img = new Image(ImageDataFactory.create(caminhoImagem));
        img.setAutoScale(true)
                .setHorizontalAlignment(HorizontalAlignment.CENTER) // ✅ CENTRALIZA
                .setMarginTop(5)
                .setMarginBottom(12);
        document.add(img);

        // 🔥 força ir para próxima página
        document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
    }

    public static void finalizar(boolean sucesso) throws FileNotFoundException {



        try {
            Paragraph resultado = (sucesso
                    ? new Paragraph("✔ RESULTADO: SUCESSO")
                    : new Paragraph("✖ RESULTADO: FALHA"))
                    .setBold()
                    .setFontSize(15)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontColor(sucesso ? ColorConstants.GREEN : ColorConstants.RED)
                    .setMarginTop(30)
                    .setMarginBottom(20);

            document.add(resultado);

            document.close();
            pdf.close();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao finalizar PDF", e);
        }



    }
}