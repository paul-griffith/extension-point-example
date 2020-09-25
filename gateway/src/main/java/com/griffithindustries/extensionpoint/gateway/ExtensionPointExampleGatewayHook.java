package com.griffithindustries.extensionpoint.gateway;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.inductiveautomation.ignition.common.licensing.LicenseState;
import com.inductiveautomation.ignition.common.util.LoggerEx;
import com.inductiveautomation.ignition.gateway.dataroutes.RouteGroup;
import com.inductiveautomation.ignition.gateway.model.AbstractGatewayModuleHook;
import com.inductiveautomation.ignition.gateway.model.ExtensionPointManager;
import com.inductiveautomation.ignition.gateway.model.ExtensionPointType;
import com.inductiveautomation.ignition.gateway.model.GatewayContext;
import com.inductiveautomation.ignition.gateway.web.models.ConfigCategory;
import com.inductiveautomation.ignition.gateway.web.models.IConfigTab;
import simpleorm.dataset.SQuery;

import static com.griffithindustries.extensionpoint.gateway.ExtensionPage.CONFIG_CATEGORY;
import static com.griffithindustries.extensionpoint.gateway.ExtensionPage.CONFIG_ENTRY;

/**
 * Class which is instantiated by the Ignition platform when the module is loaded in the gateway scope.
 */
public class ExtensionPointExampleGatewayHook extends AbstractGatewayModuleHook implements ExtensionPointManager {
    private static final LoggerEx LOG =
        LoggerEx.newBuilder().build(ExtensionPointExampleGatewayHook.class.getSimpleName());

    static ExtensionPointExampleGatewayHook INSTANCE = null;

    private GatewayContext context;

    private final Map<String, AbstractExtensionType> extensionPoints = Map.of(
        "a", new ChildARecord.ChildAType(),
        "b", new ChildBRecord.ChildBType()
    );

    private final Set<Extendable> instances = new HashSet<>();

    /**
     * Called to before startup. This is the chance for the module to add its extension points and update persistent
     * records and schemas. None of the managers will be started up at this point, but the extension point managers will
     * accept extension point types.
     */
    @Override
    public void setup(GatewayContext context) {
        INSTANCE = this;
        this.context = context;

        try {
            context.getSchemaUpdater().updatePersistentRecords(BaseRecord.META, ChildARecord.META, ChildBRecord.META);
        } catch (SQLException e) {
            LOG.errorf("Error creating persistent records", e);
        }
    }

    /**
     * Called to initialize the module. Will only be called once. Persistence interface is available, but only in
     * read-only mode.
     */
    @Override
    public void startup(LicenseState activationState) {
        List<BaseRecord> baseRecords = context.getPersistenceInterface().query(new SQuery<>(BaseRecord.META));
        for (BaseRecord record : baseRecords) {
            Extendable instance = extensionPoints.get(record.getType()).createNewInstance(context, record);
            instances.add(instance);
        }
    }

    /**
     * Called to shutdown this module. Note that this instance will never be started back up - a new one will be created
     * if a restart is desired
     */
    @Override
    public void shutdown() {
        // for (Extendable instance : instances) {
        //     instance.shutdown();
        // }
    }

    @Override
    public ExtensionPointType getExtensionPoint(String s) {
        return extensionPoints.get(s);
    }

    @Override
    public List<? extends ExtensionPointType> getExtensionPoints() {
        return new ArrayList<>(extensionPoints.values());
    }

    /**
     * A list (may be null or empty) of panels to display in the config section. Note that any config panels that are
     * part of a category that doesn't exist already or isn't included in {@link #getConfigCategories()} will
     * <i>not be shown</i>.
     */
    @Override
    public List<? extends IConfigTab> getConfigPanels() {
        return List.of(CONFIG_ENTRY);
    }

    /**
     * A list (may be null or empty) of custom config categories needed by any panels returned by  {@link
     * #getConfigPanels()}
     */
    @Override
    public List<ConfigCategory> getConfigCategories() {
        return List.of(CONFIG_CATEGORY);
    }

    /**
     * @return {@code true} if this is a "free" module, i.e. it does not participate in the licensing system. This is
     * equivalent to the now defunct FreeModule attribute that could be specified in module.xml.
     */
    @Override
    public boolean isFreeModule() {
        return true;
    }


}
