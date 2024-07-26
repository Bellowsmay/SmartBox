package smartbox.components;

import smartbox.Container;

import java.util.*;
import java.io.Serializable;
import java.lang.reflect.*;



public class Component implements Serializable {

    private Set<Class<?>> requiredInterfacesComp;
    private Set<Class<?>> providedInterfacesComp;
    private transient Map<Class<?>, Field> fields; // transient because Field not serializable
    protected Container container;
    public String name;

    public Component() {
        fields = new HashMap<Class<?>, Field>();
        providedInterfacesComp = new HashSet<Class<?>>();
        requiredInterfacesComp = new HashSet<Class<?>>();
        computeRequiredInterfaces();
        computeProvidedInterfaces();
        container = null;
        name = this.getClass().getSimpleName();
    }

    // add needed getters & setters
    public void setContainer(Container container) {
        this.container = container;
    }

    public Set<Class<?>> getProvidedInterfacesComp() {
        return providedInterfacesComp;
    }

    public Set<Class<?>> getRequiredInterfacesComp() {
        return requiredInterfacesComp;
    }

    public String toString() { return name; }

    // initializes fields and requiredInterfaces
    public void computeRequiredInterfaces() {
        Field[] fieldArray = this.getClass().getDeclaredFields();
        for(int i = 0; i < fieldArray.length; i++) {
            if(fieldArray[i].getType().isInterface()) {
                fields.put(fieldArray[i].getType(), fieldArray[i]);
                requiredInterfacesComp.add(fieldArray[i].getType());
            }
        }
    }

    // initializes provided interfaces
    public void computeProvidedInterfaces() {
        // get interfaces implemented by the class of this component and add them to providedInterfaces
        Class<?>[] interfacesArr = this.getClass().getInterfaces();
        for(Class<?> eachInterface:interfacesArr) {
            providedInterfacesComp.add(eachInterface);
        }

    }

    // set the field of this object to the provider
    public void setProvider(Class<?> intf, Component provider) throws Exception {
        Field field = fields.get(intf);
        field.set(this, provider);
    }

    // needed by file/open
    public void initComponent() {
        fields = new HashMap<Class<?>, Field>();
        computeProvidedInterfaces();
        computeRequiredInterfaces();
    }
}
