package org.itca.requerimientos.controller.sbean.request;

import org.itca.requerimientos.model.entities.DetalleSolicitud;
import org.itca.requerimientos.controller.sbean.util.JsfUtil;
import org.itca.requerimientos.controller.sbean.util.PaginationHelper;
import org.itca.requerimientos.controller.facade.request.DetalleSolicitudFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import org.itca.requerimientos.model.entities.InsumoUtilizado;
import org.itca.requerimientos.model.entities.Empleado;
import org.itca.requerimientos.model.entities.Equipo;
import org.itca.requerimientos.model.entities.TipoSolucion;
import org.itca.requerimientos.model.entities.TipoRequerimiento;
import org.itca.requerimientos.model.entities.TipoFalla;

@ManagedBean(name = "coordinatorController")
@SessionScoped
public class CoordinatorController implements Serializable {

    private DetalleSolicitud current;
    private DataModel items = null;
    @EJB
    private org.itca.requerimientos.controller.facade.request.DetalleSolicitudFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    
    @EJB
    private org.itca.requerimientos.controller.facade.request.InsumoUtilizadoFacade ejbInsumoUtilizadoFacade;
    private List<InsumoUtilizado> resourcesUsedList;
    private boolean hasNew = false;

    public boolean isHasNew() {
        return hasNew;
    }

    public void setHasNew(boolean hasNew) {
        this.hasNew = hasNew;
    }
    
    public List<InsumoUtilizado> getResourcesUsedList() {
        if (this.resourcesUsedList == null) {
            if (current == null) {
                this.resourcesUsedList = new ArrayList<InsumoUtilizado>();  // nueva lista si current es null
                return resourcesUsedList;
            }
            this.resourcesUsedList = current.getInsumoUtilizadoList();  // asignar lista de objetos dependientes
        }
        return resourcesUsedList;
    }

    public void setResourcesUsedList(List<InsumoUtilizado> resourcesUsedList) {
        this.resourcesUsedList = resourcesUsedList;
    }
    
    public void updateResourcesUsed(InsumoUtilizado iu) {
        this.hasNew = false;    // cambiar de registro a edición
        if(current.getId() != null) {   // registrar si existe entidad padre
            if(iu.getId() != null) {
                this.ejbInsumoUtilizadoFacade.edit(iu); // editar existente
            }
            else {
                this.ejbInsumoUtilizadoFacade.create(iu);   // crear nuevo
            }
        }
        System.out.println("Updating: [" + iu.getIdDetalleSolicitud()+ "] - U " + iu.getUtlilizado()+ " - D " + iu.getDesperdicio());
        // recreateModel();
        // return null;
    }
    
    public void removeResourcesUsed(InsumoUtilizado iu) {
        this.hasNew = false;
        System.out.println("Removing: [" + iu.getIdDetalleSolicitud()+ "] " + iu.getUtlilizado() + " - " + iu.getDesperdicio());
        this.resourcesUsedList.remove(iu);    // borrar de lista
        if(iu.getId() != null) {
            this.ejbInsumoUtilizadoFacade.remove(iu);   // borrar registro de PU
        }
        // recreateModel();
        // return null;
    }
    
    public void addNewResourcesUsed() {
        if (this.resourcesUsedList == null) {
            this.resourcesUsedList = new ArrayList<InsumoUtilizado>();
        }
        this.resourcesUsedList.add(new InsumoUtilizado(current));
        this.hasNew = true;
        System.out.println("Adding - count: " + this.resourcesUsedList.size());
    }
    
    public void updateSelectedRow(DetalleSolicitud dsItem) {
            // if(dsItem.getId() != null) {
                getFacade().edit(dsItem); // editar existente
            // }
            // else {
            //     getFacade().create(dsItem);   // crear nuevo
            // }
        System.out.println("Updating: [" + dsItem.getIdEquipo() + "] - " + dsItem);
        // recreateModel();
        // return null;
    }

    private String dataFilterType;
    private Empleado employee;
    private Equipo equipment;
    private TipoRequerimiento requestType;
    private TipoFalla faultType;
    private TipoSolucion solutionType;
    private Date startDate;
    private Date endDate;

    public String getDataFilterType() {
        return dataFilterType;
    }

    public void setDataFilterType(String dataFilterType) {
        this.dataFilterType = dataFilterType;
    }
    
    //generated by map
    private static Map<String, Object> dataFilterTypeValue;
    static
    {
        dataFilterTypeValue = new LinkedHashMap<String, Object>();
        dataFilterTypeValue.put(" -- seleccione filtro -- ", "NONE"); // label, value
        dataFilterTypeValue.put("Requerimientos sobre tiempo límite", "limitTime");
        dataFilterTypeValue.put("Requerimientos solventados sobre tiempo límite", "solvedOverTime");
        dataFilterTypeValue.put("Buscar requerimientos por equipo", "findByEquipment");
        dataFilterTypeValue.put("Buscar requerimientos por solicitante", "findByEmployee");
        dataFilterTypeValue.put("Requerimientos no solventados", "notSolved");
        dataFilterTypeValue.put("Requerimientos no solventados por técnico asignado", "notSolvedByAssignedTechnician");
        dataFilterTypeValue.put("Buscar entre rango de fecha de solicitud", "entryRange");
        dataFilterTypeValue.put("Buscar requerimientos por técnico asignado", "findByAssignedTechnician");
        dataFilterTypeValue.put("Buscar requerimientos por solución", "findBySolutionType");
        dataFilterTypeValue.put("Buscar requerimientos por tipo de mantenimiento", "findByRequestType");
        dataFilterTypeValue.put("Buscar requerimientos por falla presentada", "findByFaultType");
    }
    public Map<String, Object> getDataFilterTypeValue()
    {
        return dataFilterTypeValue;
    }

    public Empleado getEmployee() {
        return employee;
    }

    public void setEmployee(Empleado employee) {
        this.employee = employee;
    }

    public Equipo getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipo equipment) {
        this.equipment = equipment;
    }

    public TipoRequerimiento getRequestType() {
        return requestType;
    }

    public void setRequestType(TipoRequerimiento requestType) {
        this.requestType = requestType;
    }

    public TipoFalla getFaultType() {
        return faultType;
    }

    public void setFaultType(TipoFalla faultType) {
        this.faultType = faultType;
    }

    public TipoSolucion getSolutionType() {
        return solutionType;
    }

    public void setSolutionType(TipoSolucion solutionType) {
        this.solutionType = solutionType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void applyDataFilterType() {
        System.out.println("dataFilterType is: " + dataFilterType);
        recreateModel();
    }

    public void filterByLimitTime() {
        Date limitTime = new Date();
        System.out.println("limitTime is: " + limitTime);
        recreateModel();
    }

    public void filterByEmployee() {
        System.out.println("employee is: " + employee);
        recreateModel();
    }

    public void filterByEquipment() {
        System.out.println("equipment is: " + equipment);
        recreateModel();
    }

    public void filterByRequestType() {
        System.out.println("requestType is: " + requestType);
        recreateModel();
    }

    public void filterByFaultType() {
        System.out.println("faultType is: " + faultType);
        recreateModel();
    }

    public void filterBySolutionType() {
        System.out.println("solutionType is: " + solutionType);
        recreateModel();
    }

    public void filterSolvedOverTime() {
        System.out.println("overTime is: " + new Date());
        recreateModel();
    }

    public void filterNotSolved() {
        System.out.println("overTime not solved is: " + new Date());
        recreateModel();
    }

    public void filterNotSolvedByAssignedTechnician() {
        System.out.println("employee is: " + employee);
        recreateModel();
    }

    public void filterByAssignedTechnician() {
        System.out.println("employee is: " + employee);
        recreateModel();
    }

    public void filterByEntryRange() {
        System.out.println(new Date());
        System.out.println("startDate is: " + startDate + ", endDate is: " + endDate);
        recreateModel();
    }

    public CoordinatorController() {
    }

    public DetalleSolicitud getSelected() {
        if (current == null) {
            current = new DetalleSolicitud();
            selectedItemIndex = -1;
        }
        return current;
    }

    private DetalleSolicitudFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(15) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    if ("limitTime".equals(dataFilterType)) {
                        return new ListDataModel(getFacade().limitTime(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                    }
                    if ("solvedOverTime".equals(dataFilterType)) {
                        return new ListDataModel(getFacade().solvedOverTime(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                    }
                    else if ("findByEquipment".equals(dataFilterType) && equipment != null) {
                        return new ListDataModel(getFacade().findByEquipment(equipment.getId(), new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                    }
                    else if ("findByEmployee".equals(dataFilterType) && employee != null) {
                        return new ListDataModel(getFacade().findByEmployee(employee.getId(), new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                    }
                    if ("notSolved".equals(dataFilterType)) {
                        return new ListDataModel(getFacade().notSolved(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                    }
                    else if ("notSolvedByAssignedTechnician".equals(dataFilterType) && employee != null) {
                        return new ListDataModel(getFacade().notSolvedByAssignedTechnician(employee.getId(), new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                    }
                    else if ("entryRange".equals(dataFilterType) && startDate != null && endDate != null) {
                        return new ListDataModel(getFacade().entryRange(startDate, endDate, new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                    }
                    else if ("findByAssignedTechnician".equals(dataFilterType) && employee != null) {
                        return new ListDataModel(getFacade().findByAssignedTechnician(employee.getId(), new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                    }
                    else if ("findBySolutionType".equals(dataFilterType) && solutionType != null) {
                        return new ListDataModel(getFacade().findBySolutionType(solutionType.getId(), new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                    }
                    else if ("findByRequestType".equals(dataFilterType) && requestType != null) {
                        return new ListDataModel(getFacade().findByRequestType(requestType.getId(), new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                    }
                    else if ("findByFaultType".equals(dataFilterType) && faultType != null) {
                        return new ListDataModel(getFacade().findByFaultType(faultType.getId(), new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                    }
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (DetalleSolicitud) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }
    
    public String createAndView() {
        if (current == null) {
            recreatePagination();
            recreateModel();
            return "List";
        }
        return "View";
    }

    public String prepareCreate() {
        current = new DetalleSolicitud();
        this.resourcesUsedList = current.getInsumoUtilizadoList();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.setFechaInicio(new Date());

            Date dt = new Date();
            Calendar cl = Calendar.getInstance(); 
            cl.setTime(dt); 
            cl.add(Calendar.DATE, 8);
            dt = cl.getTime();
            current.setFechaLimite(dt);

            if ("010".equals(current.getIdEstadoSolicitud().getCodigo())) {
                current.setFechaFin(new Date());
            }
            
            if (this.resourcesUsedList != null) {
                current.setInsumoUtilizadoList(this.resourcesUsedList);
            }
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/org/itca/requerimientos/bundles/RequestBundle").getString("DetalleSolicitudCreated"));
            // return prepareCreate();
            return createAndView();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/org/itca/requerimientos/bundles/RequestBundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (DetalleSolicitud) getItems().getRowData();
        this.resourcesUsedList = current.getInsumoUtilizadoList();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            if (current.getFechaLimite() == null) {
                Date dt = new Date(current.getFechaInicio().getTime());
                Calendar cl = Calendar.getInstance(); 
                cl.setTime(dt); 
                cl.add(Calendar.DATE, 8);
                dt = cl.getTime();
                current.setFechaLimite(dt);
            }
            if ("010".equals(current.getIdEstadoSolicitud().getCodigo()) && current.getFechaFin() == null) {
                current.setFechaFin(new Date());
            }
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/org/itca/requerimientos/bundles/RequestBundle").getString("DetalleSolicitudUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/org/itca/requerimientos/bundles/RequestBundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (DetalleSolicitud) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreatePagination();
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/org/itca/requerimientos/bundles/RequestBundle").getString("DetalleSolicitudDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/org/itca/requerimientos/bundles/RequestBundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    @FacesConverter(forClass = DetalleSolicitud.class)
    public static class CoordinatorControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CoordinatorController controller = (CoordinatorController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "coordinatorController");
            return controller.ejbFacade.find(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof DetalleSolicitud) {
                DetalleSolicitud o = (DetalleSolicitud) object;
                return getStringKey(o.getId());
                // return object.toString();
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + DetalleSolicitud.class.getName());
            }
        }

    }

}
