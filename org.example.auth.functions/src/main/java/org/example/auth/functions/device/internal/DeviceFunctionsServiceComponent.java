package org.example.auth.functions.device.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.auth.functions.device.DeviceAuthFunction;
import org.example.auth.functions.device.DeviceAuthFunctionImpl;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.wso2.carbon.identity.application.authentication.framework.JsFunctionRegistry;

@Component(
        name = "identity.conditional.auth.functions.device.component",
        immediate = true
)
public class DeviceFunctionsServiceComponent {

    private static final Log LOG = LogFactory.getLog(DeviceFunctionsServiceComponent.class);

    @Activate
    protected void activate(ComponentContext context) {
        DeviceAuthFunction deviceAuthFunctionImpl = new DeviceAuthFunctionImpl();
        JsFunctionRegistry jsFunctionRegistry = DeviceFunctionsServiceDataHolder.getInstance().getJsFunctionRegistry();

        if (jsFunctionRegistry != null) {
            jsFunctionRegistry.register(
                    JsFunctionRegistry.Subsystem.SEQUENCE_HANDLER, "isUserDeviceRegistered", deviceAuthFunctionImpl);
            LOG.info("Device authentication function has been registered successfully.");
        } else {
            LOG.error("Failed to register device authentication function: JsFunctionRegistry is null.");
        }
    }

    @Deactivate
    protected void deactivate(ComponentContext context) {
        JsFunctionRegistry jsFunctionRegistry = DeviceFunctionsServiceDataHolder.getInstance().getJsFunctionRegistry();
        if (jsFunctionRegistry != null) {
            jsFunctionRegistry.deRegister(JsFunctionRegistry.Subsystem.SEQUENCE_HANDLER, "isUserDeviceRegistered");
            LOG.info("Device authentication function has been deregistered.");
        }
    }

    @Reference(
            service = JsFunctionRegistry.class,
            cardinality = ReferenceCardinality.MANDATORY,
            policy = ReferencePolicy.DYNAMIC,
            unbind = "unsetJsFunctionRegistry"
    )
    public void setJsFunctionRegistry(JsFunctionRegistry jsFunctionRegistry) {
        DeviceFunctionsServiceDataHolder.getInstance().setJsFunctionRegistry(jsFunctionRegistry);
    }

    public void unsetJsFunctionRegistry(JsFunctionRegistry jsFunctionRegistry) {
        DeviceFunctionsServiceDataHolder.getInstance().setJsFunctionRegistry(null);
    }
}