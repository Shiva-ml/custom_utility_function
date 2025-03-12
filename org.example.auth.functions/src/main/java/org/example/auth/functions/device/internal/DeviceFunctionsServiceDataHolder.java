package org.example.auth.functions.device.internal;

import org.wso2.carbon.identity.application.authentication.framework.JsFunctionRegistry;

/**
 * Singleton holder class for managing references to core WSO2 services.
 */

public class DeviceFunctionsServiceDataHolder {

    private static final DeviceFunctionsServiceDataHolder instance = new DeviceFunctionsServiceDataHolder();

    private JsFunctionRegistry jsFunctionRegistry;

    /**
     * Private constructor to enforce singleton pattern.
     */
    private DeviceFunctionsServiceDataHolder() {
    }

    /**
     * Get the singleton instance of DeviceFunctionsServiceDataHolder.
     *
     * @return DeviceFunctionsServiceDataHolder instance.
     */
    public static DeviceFunctionsServiceDataHolder getInstance() {
        return instance;
    }

    /**
     * Get the JsFunctionRegistry.
     *
     * @return JsFunctionRegistry instance.
     */
    public JsFunctionRegistry getJsFunctionRegistry() {
        return jsFunctionRegistry;
    }

    /**
     * Set the JsFunctionRegistry.
     *
     * @param jsFunctionRegistry JsFunctionRegistry instance.
     */
    public void setJsFunctionRegistry(JsFunctionRegistry jsFunctionRegistry) {
        this.jsFunctionRegistry = jsFunctionRegistry;
    }
}