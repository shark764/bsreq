package org.itca.requerimientos.controller.sbean.request;

import org.itca.requerimientos.model.entities.SolicitudRequerimiento;
import org.itca.requerimientos.controller.sbean.util.JsfUtil;
import org.itca.requerimientos.controller.sbean.util.PaginationHelper;
import org.itca.requerimientos.controller.facade.request.SolicitudRequerimientoFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
import org.itca.requerimientos.model.entities.DetalleSolicitud;

@ManagedBean(name = "solicitudRequerimientoController")
@SessionScoped
public class SolicitudRequerimientoController implements Serializable {

    private SolicitudRequerimiento current;
    private DataModel items = null;
    @EJB
    private org.itca.requerimientos.controller.facade.request.SolicitudRequerimientoFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    
    @EJB
    private org.itca.requerimientos.controller.facade.request.DetalleSolicitudFacade ejbDetalleSolicitudFacade;
    private List<DetalleSolicitud> requestDetailList;
    private boolean hasNew = false;
    
    @EJB
    private org.itca.requerimientos.controller.facade.catalogues.EstadoSolicitudFacade ejbEstadoSolicitudFacade;

    public boolean isHasNew() {
        return hasNew;
    }

    public void setHasNew(boolean hasNew) {
        this.hasNew = hasNew;
    }
    
    public List<DetalleSolicitud> getRequestDetailList() {
        if (this.requestDetailList == null) {
            if (current == null) {
                this.requestDetailList = new ArrayList<DetalleSolicitud>();  // nueva lista si current es null
                return requestDetailList;
            }
            this.requestDetailList = current.getDetalleSolicitudList();  // asignar lista de objetos dependientes
        }
        return requestDetailList;
    }

    public void setRequestDetailList(List<DetalleSolicitud> requestDetailList) {
        this.requestDetailList = requestDetailList;
    }
    
    public void updateRequestDetail(DetalleSolicitud ds) {
        this.hasNew = false;    // cambiar de registro a edición
        if(current.getId() != null) {   // registrar si existe entidad padre
            if(ds.getId() != null) {
                this.ejbDetalleSolicitudFacade.edit(ds); // editar existente
            }
            else {
                ds.setFechaInicio(new Date());
                
                Date dt = new Date();
                Calendar cl = Calendar.getInstance(); 
                cl.setTime(dt); 
                cl.add(Calendar.DATE, 8);
                dt = cl.getTime();
                ds.setFechaLimite(dt);
                
                ds.setIdEstadoSolicitud(ejbEstadoSolicitudFacade.findByCodigo("001"));
                this.ejbDetalleSolicitudFacade.create(ds);   // crear nuevo
            }
        }
        System.out.println("Updating: [" + ds.getIdEquipo()+ "] " + ds.getIdTipoFalla() + " - INI " + ds.getFechaInicio() + " - LIM " + ds.getFechaLimite());
        // recreateModel();
        // return null;
    }
    
    public void removeRequestDetail(DetalleSolicitud ds) {
        this.hasNew = false;
        System.out.println("Removing: [" + ds.getIdEquipo()+ "] " + ds.getIdTipoFalla());
        this.requestDetailList.remove(ds);    // borrar de lista
        if(ds.getId() != null) {
            this.ejbDetalleSolicitudFacade.remove(ds);   // borrar registro de PU
        }
        // recreateModel();
        // return null;
    }
    
    public void addNewRequestDetail() {
        if (this.requestDetailList == null) {
            this.requestDetailList = new ArrayList<DetalleSolicitud>();
        }
        this.requestDetailList.add(new DetalleSolicitud(current));
        this.hasNew = true;
        System.out.println("Adding - count: " + this.requestDetailList.size());
    }

    public SolicitudRequerimientoController() {
    }

    public SolicitudRequerimiento getSelected() {
        if (current == null) {
            current = new SolicitudRequerimiento();
            selectedItemIndex = -1;
        }
        return current;
    }

    private SolicitudRequerimientoFacade getFacade() {
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
        current = (SolicitudRequerimiento) getItems().getRowData();
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
        current = new SolicitudRequerimiento();
        this.requestDetailList = current.getDetalleSolicitudList();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            if (this.requestDetailList != null) {
                for (DetalleSolicitud ds : this.requestDetailList) {
                    ds.setFechaInicio(new Date());

                    Date dt = new Date();
                    Calendar cl = Calendar.getInstance(); 
                    cl.setTime(dt); 
                    cl.add(Calendar.DATE, 8);
                    dt = cl.getTime();
                    ds.setFechaLimite(dt);

                    ds.setIdEstadoSolicitud(ejbEstadoSolicitudFacade.findByCodigo("001"));
                }
                current.setDetalleSolicitudList(this.requestDetailList);
            }
            current.setFecha(new Date());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/org/itca/requerimientos/bundles/RequestBundle").getString("SolicitudRequerimientoCreated"));
            // return prepareCreate();
            return createAndView();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/org/itca/requerimientos/bundles/RequestBundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (SolicitudRequerimiento) getItems().getRowData();
        this.requestDetailList = current.getDetalleSolicitudList();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/org/itca/requerimientos/bundles/RequestBundle").getString("SolicitudRequerimientoUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/org/itca/requerimientos/bundles/RequestBundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (SolicitudRequerimiento) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/org/itca/requerimientos/bundles/RequestBundle").getString("SolicitudRequerimientoDeleted"));
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

    @FacesConverter(forClass = SolicitudRequerimiento.class)
    public static class SolicitudRequerimientoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SolicitudRequerimientoController controller = (SolicitudRequerimientoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "solicitudRequerimientoController");
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
            if (object instanceof SolicitudRequerimiento) {
                SolicitudRequerimiento o = (SolicitudRequerimiento) object;
                return getStringKey(o.getId());
                // return object.toString();
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + SolicitudRequerimiento.class.getName());
            }
        }

    }

}
