package org.example.auth.functions.device;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Arrays;

public class DeviceAuthFunctionImpl implements DeviceAuthFunction {

    private static final Log LOG = LogFactory.getLog(DeviceAuthFunctionImpl.class);

    @Override
    public boolean isUserDeviceRegistered(String username, String deviceId) {
        try {
            List<String> registeredDevices = getUserDevicesFromDB(username);
            return registeredDevices.contains(deviceId);
        } catch (Exception e) {
            LOG.error("Error while retrieving registered devices for user: " + username, e);
        }

        return false;
    }

    private List<String> getUserDevicesFromDB(String username) {
        // Mock database lookup (replace with actual DB query)
        return Arrays.asList("device123", "device456", "device789");
    }
}