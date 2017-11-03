/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itca.requerimientos.controller.sbean.jasper;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRPptxExporter;
import org.itca.requerimientos.model.entities.jasper.RetrasoPrestamoJasper;
import org.itca.requerimientos.controller.facade.loanrequest.DetallePrestamoFacade;
import org.itca.requerimientos.jasper.utils.JasperUtil;

/**
 *
 * @author dfhernandez
 */
@ManagedBean(name = "equipmentReturnedOverTimeReport")
@SessionScoped
public class EquipmentReturnedOverTimeReport implements Serializable {
    
    private String selectedJR = "equipmentReturnedOverTimeReport";
    
    private List<RetrasoPrestamoJasper> modelList;
    private JasperPrint jasperPrint;
    
    @EJB private DetallePrestamoFacade detallePrestamoFacade;

    public List<RetrasoPrestamoJasper> getModelList() {
        modelList = detallePrestamoFacade.findAllForEquipmentReturnedOverTimeReport();
        return modelList;
    }

    public void setModelList(List<RetrasoPrestamoJasper> modelList) {
        this.modelList = modelList;
    }
    
    public void init() throws JRException {
        String reportPath = (new JasperUtil()).getReportsPath() + (new JasperUtil()).getReportName(selectedJR);
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(modelList);
        jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(), beanCollectionDataSource);
    }
    
    public void PDF(ActionEvent actionEvent) throws JRException, IOException {
        init();
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        String fileName = (new JasperUtil()).getReportExportName(selectedJR, "PDF");
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + fileName);
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
    }
    
    public void DOCX(ActionEvent actionEvent) throws JRException, IOException {
        init();
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        String fileName = (new JasperUtil()).getReportExportName(selectedJR, "DOCX");
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + fileName);
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JRDocxExporter docxExporter = new JRDocxExporter();
        docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        docxExporter.exportReport();
    }
    
    public void XLSX(ActionEvent actionEvent) throws JRException, IOException {
        init();
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        String fileName = (new JasperUtil()).getReportExportName(selectedJR, "XLSX");
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + fileName);
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JRXlsExporter xlsExporter = new JRXlsExporter();
        xlsExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        xlsExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        xlsExporter.exportReport();
    }
    
    public void ODT(ActionEvent actionEvent) throws JRException, IOException {
        init();
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        String fileName = (new JasperUtil()).getReportExportName(selectedJR, "ODT");
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + fileName);
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JROdtExporter odtExporter = new JROdtExporter();
        odtExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        odtExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        odtExporter.exportReport();
    }
    
    public void PPT(ActionEvent actionEvent) throws JRException, IOException {
        init();
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        String fileName = (new JasperUtil()).getReportExportName(selectedJR, "PPT");
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + fileName);
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JRPptxExporter pptxExporter = new JRPptxExporter();
        pptxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        pptxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        pptxExporter.exportReport();
    }

    /**
     * Creates a new instance of JasperBean
     */
    public EquipmentReturnedOverTimeReport() {
    }
    
}