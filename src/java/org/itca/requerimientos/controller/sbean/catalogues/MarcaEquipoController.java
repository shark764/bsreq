package org.itca.requerimientos.controller.sbean.catalogues;

import org.itca.requerimientos.model.entities.MarcaEquipo;
import org.itca.requerimientos.controller.sbean.util.JsfUtil;
import org.itca.requerimientos.controller.sbean.util.PaginationHelper;
import org.itca.requerimientos.controller.facade.catalogues.MarcaEquipoFacade;

import java.io.Serializable;
import java.util.ArrayList;
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
import org.itca.requerimientos.model.entities.ModeloEquipo;

@ManagedBean(name = "marcaEquipoController")
@SessionScoped
public class MarcaEquipoController implements Serializable {

    private MarcaEquipo current;
    private DataModel items = null;
    @EJB
    private org.itca.requerimientos.controller.facade.catalogues.MarcaEquipoFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    
    @EJB
    private org.itca.requerimientos.controller.facade.catalogues.ModeloEquipoFacade ejbModeloEquipoFacade;
    private List<ModeloEquipo> equipmentModelList;
    private boolean hasNew = false;

    public boolean isHasNew() {
        return hasNew;
    }

    public void setHasNew(boolean hasNew) {
        this.hasNew = hasNew;
    }
    
    public List<ModeloEquipo> getEquipmentModelList() {
        if (this.equipmentModelList == null) {
            if (current == null) {
                this.equipmentModelList = new ArrayList<ModeloEquipo>();  // nueva lista si current es null
                return equipmentModelList;
            }
            this.equipmentModelList = current.getModeloEquipoList();  // asignar lista de objetos dependientes
        }
        return equipmentModelList;
    }

    public void setEquipmentModelList(List<ModeloEquipo> equipmentModelList) {
        this.equipmentModelList = equipmentModelList;
    }
    
    public void updateEquipmentModel(ModeloEquipo mq) {
        this.hasNew = false;    // cambiar de registro a edición
        if(current.getId() != null) {   // registrar si existe entidad padre
            if(mq.getId() != null) {
                this.ejbModeloEquipoFacade.edit(mq); // editar existente
            }
            else {
                this.ejbModeloEquipoFacade.create(mq);   // crear nuevo
            }
        }
        System.out.println("Updating: [" + mq.getCodigo()+ "] " + mq.getNombre());
        // recreateModel();
        // return null;
    }
    
    public void removeEquipmentModel(ModeloEquipo mq) {
        this.hasNew = false;
        System.out.println("Removing: [" + mq.getCodigo()+ "] " + mq.getNombre());
        this.equipmentModelList.remove(mq);    // borrar de lista
        if(mq.getId() != null) {
            this.ejbModeloEquipoFacade.remove(mq);   // borrar registro de PU
        }
        // recreateModel();
        // return null;
    }
    
    public void addNewEquipmentModel() {
        if (this.equipmentModelList == null) {
            this.equipmentModelList = new ArrayList<ModeloEquipo>();
        }
        this.equipmentModelList.add((new ModeloEquipo(current)));
        this.hasNew = true;
        System.out.println("Adding - count: " + this.equipmentModelList.size());
    }

    public void PDF() {
        System.out.println("report is: PDF");
        recreateModel();
    }
    public void DOCX() {
        System.out.println("report is: DOCX");
        recreateModel();
    }
    public void XLSX() {
        System.out.println("report is: XLSX");
        recreateModel();
    }
    public void ODT() {
        System.out.println("report is: ODT");
        recreateModel();
    }
    public void PPT() {
        System.out.println("report is: PPT");
        recreateModel();
    }

    public MarcaEquipoController() {
    }

    public MarcaEquipo getSelected() {
        if (current == null) {
            current = new MarcaEquipo();
            selectedItemIndex = -1;
        }
        return current;
    }

    private MarcaEquipoFacade getFacade() {
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
        current = (MarcaEquipo) getItems().getRowData();
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
        current = new MarcaEquipo();
        this.equipmentModelList = current.getModeloEquipoList();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            if (this.equipmentModelList != null) {
                current.setModeloEquipoList(this.equipmentModelList);
            }
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/org/itca/requerimientos/bundles/CataloguesBundle").getString("MarcaEquipoCreated"));
            // return prepareCreate();
            return createAndView();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/org/itca/requerimientos/bundles/CataloguesBundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (MarcaEquipo) getItems().getRowData();
        this.equipmentModelList = current.getModeloEquipoList();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            // if (this.equipmentModelList != null) {
            //     current.setModeloEquipoList(this.equipmentModelList);
            // }
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/org/itca/requerimientos/bundles/CataloguesBundle").getString("MarcaEquipoUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/org/itca/requerimientos/bundles/CataloguesBundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (MarcaEquipo) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/org/itca/requerimientos/bundles/CataloguesBundle").getString("MarcaEquipoDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/org/itca/requerimientos/bundles/CataloguesBundle").getString("PersistenceErrorOccured"));
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

    @FacesConverter(forClass = MarcaEquipo.class)
    public static class MarcaEquipoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MarcaEquipoController controller = (MarcaEquipoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "marcaEquipoController");
            return controller.ejbFacade.find(getKey(value));
        }

        java.lang.Short getKey(String value) {
            java.lang.Short key;
            key = Short.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Short value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof MarcaEquipo) {
                MarcaEquipo o = (MarcaEquipo) object;
                return getStringKey(o.getId());
                // return object.toString();
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + MarcaEquipo.class.getName());
            }
        }

    }

}
