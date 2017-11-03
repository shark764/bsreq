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
import org.itca.requerimientos.model.entities.jasper.SolicitudTecnicoJasper;
import org.itca.requerimientos.controller.facade.request.DetalleSolicitudFacade;
import org.itca.requerimientos.jasper.utils.JasperUtil;

/**
 *
 * @author dfhernandez
 */
@ManagedBean(name = "requestByAssignedTechnicianReport")
@SessionScoped
public class RequestByAssignedTechnicianReport implements Serializable {
    
    private String selectedJR = "requestByAssignedTechnicianReport";
    
    private List<SolicitudTecnicoJasper> modelList;
    private JasperPrint jasperPrint;
    
    @EJB private DetalleSolicitudFacade detalleSolicitudFacade;

    public List<SolicitudTecnicoJasper> getModelList() {
        modelList = detalleSolicitudFacade.findAllForRequestByAssignedTechnicianReport();
        return modelList;
    }

    public void setModelList(List<SolicitudTecnicoJasper> modelList) {
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
    public RequestByAssignedTechnicianReport() {
    }
    
}