/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itca.requerimientos.jasper.utils;

/**
 *
 * @author dfhernandez
 */
public class JasperUtil {
    
    public String getReportsPath() {
        String pathJR = "/";
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            pathJR = "C:\\jasper-reports\\";    // filepath for windows
        }
        else if (System.getProperty("os.name").toLowerCase().contains("linux")) {
            pathJR = "/var/www/jasper-reports/";    // filepath for linux
        }
        System.out.println("pathJR: " + pathJR);
        return pathJR;
    }
    
    public String getReportName(String selected) {
        String nameJR = "";
        switch (selected) {
            case "stockByEquipmentModelReport":
                nameJR = "stockByEquipmentModelReport.jasper";
                break;
            case "defectiveInventoryReport":
                nameJR = "defectiveInventoryReport.jasper";
                break;
            case "equipmentReturnedOverTimeReport":
                nameJR = "equipmentReturnedOverTimeReport.jasper";
                break;
            case "requestByAssignedTechnicianReport":
                nameJR = "requestByAssignedTechnicianReport.jasper";
                break;
            case "requestByEquipmentFailureReport":
                nameJR = "requestByEquipmentFailureReport.jasper";
                break;
            case "requestByEquipmentModelReport":
                nameJR = "requestByEquipmentModelReport.jasper";
                break;
            default:
                break;
        }
        System.out.println("nameJR: " + nameJR);
        return nameJR;
    }
    
    public String getReportExportName(String selected) {
        String exportJR = "";
        switch (selected) {
            case "stockByEquipmentModelReport":
                exportJR = "reporte-existencia-por-modelo.pdf";
                break;
            case "defectiveInventoryReport":
                exportJR = "reporte-inventario-defectuoso.pdf";
                break;
            case "equipmentReturnedOverTimeReport":
                exportJR = "reporte-prestamos-retrasados.pdf";
                break;
            case "requestByAssignedTechnicianReport":
                exportJR = "reporte-solicitudes-por-tecnico.pdf";
                break;
            case "requestByEquipmentFailureReport":
                exportJR = "reporte-solicitudes-por-falla.pdf";
                break;
            case "requestByEquipmentModelReport":
                exportJR = "reporte-solicitudes-por-modelo.pdf";
                break;
            default:
                break;
        }
        System.out.println("exportJR: " + exportJR);
        return exportJR;
    }
    
}