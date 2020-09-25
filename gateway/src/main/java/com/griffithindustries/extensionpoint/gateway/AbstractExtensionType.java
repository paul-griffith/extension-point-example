package com.griffithindustries.extensionpoint.gateway;

import com.inductiveautomation.ignition.gateway.model.BaseExtensionPointType;
import com.inductiveautomation.ignition.gateway.model.GatewayContext;

public abstract class AbstractExtensionType extends BaseExtensionPointType {
    public AbstractExtensionType(String typeId) {
        super(typeId, typeId +  "nameKey", typeId + "descriptionKey");
    }

    abstract Extendable createNewInstance(GatewayContext context, BaseRecord baseRecord);
}
