package org.itca.requerimientos.controller.sbean.security;

import org.itca.requerimientos.model.entities.Roles;
import org.itca.requerimientos.controller.sbean.util.JsfUtil;
import org.itca.requerimientos.controller.sbean.util.PaginationHelper;
import org.itca.requerimientos.controller.facade.security.RolesFacade;

import java.io.Serializable;
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

@ManagedBean(name = "rolesController")
@SessionScoped
public class RolesController implements Serializable {

    private Roles current;
    private DataModel items = null;
    @EJB
    private org.itca.requerimientos.controller.facade.security.RolesFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public RolesController() {
    }

    public Roles getSelected() {
        if (current == null) {
            current = new Roles();
            current.setRolesPK(new org.itca.requerimientos.model.entities.RolesPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private RolesFacade getFacade() {
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
        current = (Roles) getItems().getRowData();
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
        current = new Roles();
        current.setRolesPK(new org.itca.requerimientos.model.entities.RolesPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/org/itca/requerimientos/bundles/SecurityBundle").getString("RolesCreated"));
            // return prepareCreate();
            return createAndView();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/org/itca/requerimientos/bundles/SecurityBundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Roles) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/org/itca/requerimientos/bundles/SecurityBundle").getString("RolesUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/org/itca/requerimientos/bundles/SecurityBundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Roles) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/org/itca/requerimientos/bundles/SecurityBundle").getString("RolesDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/org/itca/requerimientos/bundles/SecurityBundle").getString("PersistenceErrorOccured"));
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

    @FacesConverter(forClass = Roles.class)
    public static class RolesControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            RolesController controller = (RolesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "rolesController");
            return controller.ejbFacade.find(getKey(value));
        }

        org.itca.requerimientos.model.entities.RolesPK getKey(String value) {
            org.itca.requerimientos.model.entities.RolesPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new org.itca.requerimientos.model.entities.RolesPK();
            key.setRol(values[0]);
            key.setIduser(values[1]);
            return key;
        }

        String getStringKey(org.itca.requerimientos.model.entities.RolesPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getRol());
            sb.append(SEPARATOR);
            sb.append(value.getIduser());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Roles) {
                Roles o = (Roles) object;
                return getStringKey(o.getRolesPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Roles.class.getName());
            }
        }

    }

}
