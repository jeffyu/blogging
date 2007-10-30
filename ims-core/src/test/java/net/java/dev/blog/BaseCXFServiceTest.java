/**
 * 
 */
package net.java.dev.blog;

import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.binding.BindingFactoryManager;
import org.apache.cxf.binding.soap.SoapBindingFactory;
import org.apache.cxf.binding.soap.SoapTransportFactory;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.transport.ConduitInitiatorManager;
import org.apache.cxf.transport.DestinationFactoryManager;
import org.apache.cxf.transport.local.LocalTransportFactory;
import org.apache.cxf.wsdl.WSDLManager;
import org.apache.cxf.wsdl11.WSDLManagerImpl;
import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 * @author Jeff.Yu
 *
 */
public class BaseCXFServiceTest extends BaseTestCase {
	
	protected static Bus bus;
	
	protected static LocalTransportFactory localTransport;
    
    @BeforeClass
    public static void initialize() throws Exception {
        if (BusFactory.getDefaultBus(false) != null) {
            throw new Exception("Bus was not null!!!  Check cleanup of previously run tests!!!");
        }
        
        bus = BusFactory.newInstance().createBus();
        setUpBus();       
    }

    protected static void setUpBus() throws Exception {
        
        SoapBindingFactory bindingFactory = new SoapBindingFactory();
        bindingFactory.setBus(bus);
        bus.getExtension(BindingFactoryManager.class)
            .registerBindingFactory("http://schemas.xmlsoap.org/wsdl/soap/", bindingFactory);
        bus.getExtension(BindingFactoryManager.class)
            .registerBindingFactory("http://schemas.xmlsoap.org/wsdl/soap/http", bindingFactory);

        DestinationFactoryManager dfm = bus.getExtension(DestinationFactoryManager.class);

        SoapTransportFactory soapDF = new SoapTransportFactory();
        soapDF.setBus(bus);
        dfm.registerDestinationFactory("http://schemas.xmlsoap.org/wsdl/soap/", soapDF);
        dfm.registerDestinationFactory("http://schemas.xmlsoap.org/soap/", soapDF);
        dfm.registerDestinationFactory("http://cxf.apache.org/transports/local", soapDF);
        
        localTransport = new LocalTransportFactory();
        dfm.registerDestinationFactory("http://schemas.xmlsoap.org/soap/http", localTransport);
        dfm.registerDestinationFactory("http://schemas.xmlsoap.org/wsdl/soap/http", localTransport);
        dfm.registerDestinationFactory("http://cxf.apache.org/bindings/xformat", localTransport);
        dfm.registerDestinationFactory("http://cxf.apache.org/transports/local", localTransport);

        ConduitInitiatorManager extension = bus.getExtension(ConduitInitiatorManager.class);
        extension.registerConduitInitiator(LocalTransportFactory.TRANSPORT_ID, localTransport);
        extension.registerConduitInitiator("http://schemas.xmlsoap.org/wsdl/soap/", localTransport);
        extension.registerConduitInitiator("http://schemas.xmlsoap.org/soap/http", localTransport);
        extension.registerConduitInitiator("http://schemas.xmlsoap.org/soap/", localTransport);
        
        WSDLManagerImpl manager = new WSDLManagerImpl();
        manager.setBus(bus);
        bus.setExtension(manager, WSDLManager.class);
        
        bus.getOutInterceptors().add(new LoggingOutInterceptor());
    }
    
    
    @AfterClass
    public static void shutDownBus() throws Exception {   
        if (bus != null) {
            bus.shutdown(false);
            bus = null;
        } 
        BusFactory.setDefaultBus(null);
    }
}
