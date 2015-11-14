package client;

import de.fhws.app.showcase.ejb.CalculationBeanRemote;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class EJBRemoteClient {

    public static void main(String[] args) throws Exception {

        //https://docs.jboss.org/author/display/WFLY8/EJB+invocations+from+a+remote+client+using+JNDI
        //https://github.com/wildfly/quickstart/tree/10.x/ejb-remote
        // Invoke a stateless bean
        invokeStatelessBean();

        
    }

    /**
     * Looks up a stateless bean and invokes on it
     *
     * @throws NamingException
     */
    private static void invokeStatelessBean() throws NamingException {
        // Let's lookup the remote stateless calculator
        final CalculationBeanRemote statelessRemoteCalculator = lookupRemoteStatelessCalculator();
       
        int result = statelessRemoteCalculator.calculation(5);
        
        System.out.println("Rechenergebnis vom Server: " + result);
    }

 
/**
     * Looks up and returns the proxy to remote stateless calculator bean
     *
     * @return
     * @throws NamingException
     */
    private static CalculationBeanRemote lookupRemoteStatelessCalculator() throws NamingException {
        final Hashtable<String, String> jndiProperties = new Hashtable<>();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        final Context context = new InitialContext(jndiProperties);

        // The JNDI lookup name for a stateless session bean has the syntax of:
        // ejb:<appName>/<moduleName>/<distinctName>/<beanName>!<viewClassName>
        //
        // <appName> The application name is the name of the EAR that the EJB is deployed in
        // (without the .ear). If the EJB JAR is not deployed in an EAR then this is
        // blank. The app name can also be specified in the EAR's application.xml
        //
        // <moduleName> By the default the module name is the name of the EJB JAR file (without the
        // .jar suffix). The module name might be overridden in the ejb-jar.xml
        //
        // <distinctName> : WildFly allows each deployment to have an (optional) distinct name.
        // This example does not use this so leave it blank.
        //
        // <beanName> : The name of the session been to be invoked.
        //
        // <viewClassName>: The fully qualified classname of the remote interface. Must include
        // the whole package name.
        // let's do the lookup
        return (CalculationBeanRemote) context.lookup("ejb:/fhws-2015/CalculationBean!de.fhws.app.showcase.ejb.CalculationBeanRemote");
    }
}
