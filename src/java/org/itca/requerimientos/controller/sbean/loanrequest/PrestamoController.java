package org.itca.requerimientos.controller.sbean.loanrequest;

import org.itca.requerimientos.model.entities.Prestamo;
import org.itca.requerimientos.controller.sbean.util.JsfUtil;
import org.itca.requerimientos.controller.sbean.util.PaginationHelper;
import org.itca.requerimientos.controller.facade.loanrequest.PrestamoFacade;

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
import org.itca.requerimientos.model.entities.DetallePrestamo;

@ManagedBean(name = "prestamoController")
@SessionScoped
public class PrestamoController implements Serializable {

    private Prestamo current;
    private DataModel items = null;
    @EJB
    private org.itca.requerimientos.controller.facade.loanrequest.PrestamoFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    
    @EJB
    private org.itca.requerimientos.controller.facade.loanrequest.DetallePrestamoFacade ejbDetallePrestamoFacade;
    private List<DetallePrestamo> loanRequestDetailList;
    private boolean hasNew = false;
    
    @EJB
    private org.itca.requerimientos.controller.facade.catalogues.EstadoPrestamoFacade ejbEstadoPrestamoFacade;

    public boolean isHasNew() {
        return hasNew;
    }

    public void setHasNew(boolean hasNew) {
        this.hasNew = hasNew;
    }
    
    public List<DetallePrestamo> getLoanRequestDetailList() {
        if (this.loanRequestDetailList == null) {
            if (current == null) {
                this.loanRequestDetailList = new ArrayList<DetallePrestamo>();  // nueva lista si current es null
                return loanRequestDetailList;
            }
            this.loanRequestDetailList = current.getDetallePrestamoList();  // asignar lista de objetos dependientes
        }
        return loanRequestDetailList;
    }

    public void setLoanRequestDetailList(List<DetallePrestamo> loanRequestDetailList) {
        this.loanRequestDetailList = loanRequestDetailList;
    }
    
    public void updateLoanRequestDetail(DetallePrestamo dp) {
        this.hasNew = false;    // cambiar de registro a edición
        if(current.getId() != null) {   // registrar si existe entidad padre
            if(dp.getId() != null) {
                this.ejbDetallePrestamoFacade.edit(dp); // editar existente
            }
            else {
                dp.setFechaPrestamo(new Date());
                
                Date dt = new Date();
                Calendar cl = Calendar.getInstance(); 
                cl.setTime(dt); 
                cl.add(Calendar.DATE, 8);
                dt = cl.getTime();
                dp.setFechaLimite(dt);

                dp.setIdEstadoPrestamo(ejbEstadoPrestamoFacade.findByCodigo("001"));
                this.ejbDetallePrestamoFacade.create(dp);   // crear nuevo
            }
        }
        System.out.println("Updating: [" + dp.getIdEquipo()+ "] " + dp.getIdPrestamo() + " - INI " + dp.getFechaPrestamo()+ " - LIM " + dp.getFechaLimite());
        // recreateModel();
        // return null;
    }
    
    public void removeLoanRequestDetail(DetallePrestamo dp) {
        this.hasNew = false;
        System.out.println("Removing: [" + dp.getIdEquipo()+ "] " + dp.getIdPrestamo());
        this.loanRequestDetailList.remove(dp);    // borrar de lista
        if(dp.getId() != null) {
            this.ejbDetallePrestamoFacade.remove(dp);   // borrar registro de PU
        }
        // recreateModel();
        // return null;
    }
    
    public void addNewLoanRequestDetail() {
        if (this.loanRequestDetailList == null) {
            this.loanRequestDetailList = new ArrayList<DetallePrestamo>();
        }
        this.loanRequestDetailList.add(new DetallePrestamo(current));
        this.hasNew = true;
        System.out.println("Adding - count: " + this.loanRequestDetailList.size());
    }

    public PrestamoController() {
    }

    public Prestamo getSelected() {
        if (current == null) {
            current = new Prestamo();
            selectedItemIndex = -1;
        }
        return current;
    }

    private PrestamoFacade getFacade() {
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
        current = (Prestamo) getItems().getRowData();
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
        current = new Prestamo();
        this.loanRequestDetailList = current.getDetallePrestamoList();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            if (this.loanRequestDetailList != null) {
                for (DetallePrestamo dp : this.loanRequestDetailList) {
                    dp.setFechaPrestamo(new Date());

                    Date dt = new Date();
                    Calendar cl = Calendar.getInstance(); 
                    cl.setTime(dt); 
                    cl.add(Calendar.DATE, 8);
                    dt = cl.getTime();
                    dp.setFechaLimite(dt);

                    dp.setIdEstadoPrestamo(ejbEstadoPrestamoFacade.findByCodigo("001"));
                }
                current.setDetallePrestamoList(this.loanRequestDetailList);
            }
            current.setFecha(new Date());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/org/itca/requerimientos/bundles/LoanRequestBundle").getString("PrestamoCreated"));
            // return prepareCreate();
            return createAndView();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/org/itca/requerimientos/bundles/LoanRequestBundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Prestamo) getItems().getRowData();
        this.loanRequestDetailList = current.getDetallePrestamoList();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/org/itca/requerimientos/bundles/LoanRequestBundle").getString("PrestamoUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/org/itca/requerimientos/bundles/LoanRequestBundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Prestamo) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/org/itca/requerimientos/bundles/LoanRequestBundle").getString("PrestamoDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/org/itca/requerimientos/bundles/LoanRequestBundle").getString("PersistenceErrorOccured"));
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

    @FacesConverter(forClass = Prestamo.class)
    public static class PrestamoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PrestamoController controller = (PrestamoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "prestamoController");
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
            if (object instanceof Prestamo) {
                Prestamo o = (Prestamo) object;
                return getStringKey(o.getId());
                // return object.toString();
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Prestamo.class.getName());
            }
        }

    }

}
