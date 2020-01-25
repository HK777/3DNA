package ru.dna.dna.parseAndConvert;

import java.util.ArrayList;

public class ParserAndConverter {
    private String parseAndConvert(String dnaName, String stringToConvert) {
        String[] rows = stringToConvert.split("\n");
        rows[0] = replaceOrAddPairNum(rows[0]);

        ArrayList<String[]> cells = new ArrayList<>();

        for (String str : rows)
            cells.add(str.split(","));

        return convertToStringXML(dnaName, cells);
    }

    private static String replaceOrAddPairNum(String s) {
        if (s.contains("pairnum"))
            s = s.replace("pairnum,", "");

        return s.substring(1);
    }

    private String convertToStringXML(String dnaName, ArrayList<String[]> cells) {
        StringBuilder convertedToStringXml = new StringBuilder();

        String stringXMLBeginTemplate = "<?xml version=\"1.0\"?>\n" +
                "<?mso-application progid=\"Excel.Sheet\"?>\n" +
                "<Workbook xmlns=\"urn:schemas-microsoft-com:office:spreadsheet\"\n" +
                " xmlns:o=\"urn:schemas-microsoft-com:office:office\"\n" +
                " xmlns:x=\"urn:schemas-microsoft-com:office:excel\"\n" +
                " xmlns:ss=\"urn:schemas-microsoft-com:office:spreadsheet\"\n" +
                " xmlns:html=\"http://www.w3.org/TR/REC-html40\">\n" +
                " <DocumentProperties xmlns=\"urn:schemas-microsoft-com:office:office\">\n" +
                "  <LastAuthor>Microsoft Office User</LastAuthor>\n" +
                "  <Created>2020-01-25T09:32:33Z</Created>\n" +
                "  <Version>16.00</Version>\n" +
                " </DocumentProperties>\n" +
                " <OfficeDocumentSettings xmlns=\"urn:schemas-microsoft-com:office:office\">\n" +
                "  <AllowPNG/>\n" +
                " </OfficeDocumentSettings>\n" +
                " <ExcelWorkbook xmlns=\"urn:schemas-microsoft-com:office:excel\">\n" +
                "  <WindowHeight>16500</WindowHeight>\n" +
                "  <WindowWidth>28240</WindowWidth>\n" +
                "  <WindowTopX>280</WindowTopX>\n" +
                "  <WindowTopY>460</WindowTopY>\n" +
                "  <ProtectStructure>False</ProtectStructure>\n" +
                "  <ProtectWindows>False</ProtectWindows>\n" +
                " </ExcelWorkbook>\n" +
                " <Styles>\n" +
                "  <Style ss:ID=\"Default\" ss:Name=\"Normal\">\n" +
                "   <Alignment ss:Vertical=\"Bottom\"/>\n" +
                "   <Borders/>\n" +
                "   <Font ss:FontName=\"Calibri\" x:CharSet=\"204\" x:Family=\"Swiss\" ss:Size=\"12\"\n" +
                "    ss:Color=\"#000000\"/>\n" +
                "   <Interior/>\n" +
                "   <NumberFormat/>\n" +
                "   <Protection/>\n" +
                "  </Style>\n" +
                "  <Style ss:ID=\"s62\">\n" +
                "   <Font ss:FontName=\"Helvetica Neue\" x:Family=\"Swiss\" ss:Color=\"#000000\"\n" +
                "    ss:Bold=\"1\"/>\n" +
                "  </Style>\n" +
                "  <Style ss:ID=\"s63\">\n" +
                "   <Font ss:FontName=\"Helvetica Neue\" x:Family=\"Swiss\" ss:Color=\"#000000\"/>\n" +
                "  </Style>\n" +
                "  <Style ss:ID=\"s64\">\n" +
                "   <Font ss:FontName=\"Helvetica\" x:Family=\"Swiss\" ss:Size=\"12\" ss:Color=\"#000000\"/>\n" +
                "  </Style>\n" +
                " </Styles>\n";
        int size = cells.size();
        int length = cells.get(0).length;

        String stringXMLDnaBodyHead = "<Worksheet ss:Name=\"" + dnaName + "\">\n" +
                "  <Table ss:ExpandedColumnCount=\"" + length + 1 + "\" ss:ExpandedRowCount=\"" + size + "\" x:FullColumns=\"1\"\n" +
                "   x:FullRows=\"1\" ss:DefaultColumnWidth=\"65\" ss:DefaultRowHeight=\"16\">\n";

        convertedToStringXml.append(stringXMLBeginTemplate).append(stringXMLDnaBodyHead);

        convertedToStringXml.append("   <Row>\n");

        for (int i = 0; i < length; ++i)
            convertedToStringXml.append("    <Cell ss:StyleID=\"s62\"><Data ss:Type=\"String\">").append(cells.get(0)[i]).append("</Data></Cell>\n");

        convertedToStringXml.append("   </Row>\n");

        for (int i = 1; i < size; ++i) {
            convertedToStringXml.append("   <Row>\n");

            for (int j = 0; j < length; ++j)
                convertedToStringXml.append("    <Cell ss:StyleID=\"s63\"><Data ss:Type=\"String\">").append(cells.get(i)[j]).append("</Data></Cell>\n");

            convertedToStringXml.append("<Cell ss:StyleID=\"s63\"/>");
            convertedToStringXml.append("   </Row>\n");
        }
        convertedToStringXml.append("  </Table>\n");

        return convertedToStringXml.toString();
    }

    String addXmlDNAEnd(String convertedToStringXml) {

        String stringXmlDnaEnd = "  <WorksheetOptions xmlns=\"urn:schemas-microsoft-com:office:excel\">\n" +
                "   <PageSetup>\n" +
                "    <Header x:Margin=\"0.3\"/>\n" +
                "    <Footer x:Margin=\"0.3\"/>\n" +
                "    <PageMargins x:Bottom=\"0.75\" x:Left=\"0.7\" x:Right=\"0.7\" x:Top=\"0.75\"/>\n" +
                "   </PageSetup>\n" +
                "   <Panes>\n" +
                "    <Pane>\n" +
                "     <Number>3</Number>\n" +
                "     <ActiveRow>10</ActiveRow>\n" +
                "     <ActiveCol>10</ActiveCol>\n" +
                "    </Pane>\n" +
                "   </Panes>\n" +
                "   <ProtectObjects>False</ProtectObjects>\n" +
                "   <ProtectScenarios>False</ProtectScenarios>\n" +
                "  </WorksheetOptions>\n" +
                " </Worksheet>\n" +
                "</Workbook>";

        return convertedToStringXml + stringXmlDnaEnd;
    }
}
