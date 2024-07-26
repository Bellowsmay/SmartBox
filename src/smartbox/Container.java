package smartbox;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.*;

import mvc.*;
import smartbox.components.*;


public class Container extends Model {

    private Map<Class<?>, Component> providedInterfacesCont = new HashMap<Class<?>, Component>();
    private Map<Class<?>, Component> requiredInterfacesCont = new HashMap<Class<?>, Component>();
    private Map<String, Component> components = new HashMap<String, Component>();

    public Collection<Component> getComponents() {
        return components.values();
    }

    public void addComponent(String name) throws Exception {
        String qualName = "smartbox.components." +  name;
        // Object obj = a new instance of qualName
        Object obj = Class.forName(qualName).getDeclaredConstructor().newInstance();
        addComponent((Component)obj);
    }


    private void addComponent(Component component) throws Exception {
        component.setContainer(this);
        // add new guy to the componebnts table:
        components.put(component.name, component);
        // update provided interfaces table:
        for(Class<?> intf: component.getProvidedInterfacesComp()) {
            providedInterfacesCont.put(intf,  component);
        }
        // update required interfaces table:
        for(Class<?> intf: component.getRequiredInterfacesComp()) {
            requiredInterfacesCont.put(intf,  component);
        }
        //find providers for the new component and hook it up:
        findProviders();
        changed();
    }

    public void remComponent(String name) throws Exception {
        Component component = components.get(name);
        components.remove(name);
        // unhook removed component from any clients:
        for(Class<?> intf: component.getProvidedInterfacesComp()) {
            for(Component client: components.values()) {
                if (client.getRequiredInterfacesComp().contains(intf)) {
                    client.setProvider(intf,  null);
                    requiredInterfacesCont.put(intf, client);
                }
            }
        }
        changed();
    }

    // each time we add a new component we try to connect as many clients and providers as we can:
    private void findProviders() throws Exception {
        Set<Class<?>> reqInterfaces = requiredInterfacesCont.keySet();
        for(Class<?> intf: reqInterfaces) {
            Component client = requiredInterfacesCont.get(intf);
            Component provider = providedInterfacesCont.get(intf);
            if (client != null && provider != null) {
                client.setProvider(intf,  provider);
                requiredInterfacesCont.put(intf, null);
            }
        }
    }

    public void launch(String name) throws Exception {
        try {
            // look up component and call main if it's an App
            Component comp = components.get(name);
            Class<?> c = comp.getClass();
            Method meth = c.getMethod("main", null);
            meth.invoke(comp, null);
        } catch(Exception e) {
            mvc.Utilities.error(e);
            e.printStackTrace();
        }
    }

    // needed by File/Open to restore component.fields
    public void initContainer(){
        for(Component c: components.values()) c.initComponent();
        changed();
    }

}
