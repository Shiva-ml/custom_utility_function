package org.example.auth.functions.device;

import org.wso2.carbon.identity.application.authentication.framework.config.model.graph.js.JsAuthenticationContext;

@FunctionalInterface
public interface DeviceAuthFunction {
    boolean isUserDeviceRegistered(String username, String deviceId);
}
