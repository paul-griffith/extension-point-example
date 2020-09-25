package com.griffithindustries.extensionpoint.gateway;

import com.inductiveautomation.ignition.gateway.localdb.persistence.RecordMeta;
import com.inductiveautomation.ignition.gateway.model.ExtensionPointManager;
import com.inductiveautomation.ignition.gateway.web.components.ExtensionPointPage;
import com.inductiveautomation.ignition.gateway.web.models.ConfigCategory;
import com.inductiveautomation.ignition.gateway.web.models.DefaultConfigTab;
import com.inductiveautomation.ignition.gateway.web.models.IConfigTab;
import com.inductiveautomation.ignition.gateway.web.pages.IConfigPage;

public class ExtensionPage extends ExtensionPointPage<BaseRecord> {
    public static final ConfigCategory CONFIG_CATEGORY = new ConfigCategory("categoryName", "categoryDisplayKey");

    public static final IConfigTab CONFIG_ENTRY = DefaultConfigTab.builder()
        .category(CONFIG_CATEGORY)
        .name("extensionPointCategoryName")
        .i18n("config.nav.settings.title")
        .page(ExtensionPage.class)
        .terms("search", "terms")
        .build();

    public ExtensionPage(IConfigPage configPage) {
        super(configPage);
    }

    @Override
    protected ExtensionPointManager getExtensionPointManager() {
        return ExtensionPointExampleGatewayHook.INSTANCE;
    }

    @Override
    protected RecordMeta<BaseRecord> getRecordMeta() {
        return BaseRecord.META;
    }
}
