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
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.itca.requerimientos.model.entities.jasper.InventarioDefectuosoJasper;
import org.itca.requerimientos.controller.facade.inventory.InventarioDefectuosoFacade;
import org.itca.requerimientos.jasper.utils.JasperUtil;

/**
 *
 * @author dfhernandez
 */
@ManagedBean(name = "defectiveInventoryReport")
@SessionScoped
public class DefectiveInventoryReport implements Serializable {
    
    private String selectedJR = "defectiveInventoryReport";
    
    private List<InventarioDefectuosoJasper> modelList;
    private JasperPrint jasperPrint;
    
    @EJB private InventarioDefectuosoFacade inventarioDefectuosoFacade;

    public List<InventarioDefectuosoJasper> getModelList() {
        modelList = inventarioDefectuosoFacade.findAllForDefectiveInventoryReport();
        return modelList;
    }

    public void setModelList(List<InventarioDefectuosoJasper> modelList) {
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
        String fileName = (new JasperUtil()).getReportExportName(selectedJR);
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + fileName);
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
    }
    
    public void DOCX(ActionEvent actionEvent) {
        System.out.println("data size: " + modelList.size() + ", actionEvent: DOCX");
    }
    
    public void XLSX(ActionEvent actionEvent) {
        System.out.println("data size: " + modelList.size() + ", actionEvent: XLSX");
    }
    
    public void ODT(ActionEvent actionEvent) {
        System.out.println("data size: " + modelList.size() + ", actionEvent: ODT");
    }
    
    public void PPT(ActionEvent actionEvent) {
        System.out.println("data size: " + modelList.size() + ", actionEvent: PPT");
    }

    /**
     * Creates a new instance of JasperBean
     */
    public DefectiveInventoryReport() {
    }
    
}