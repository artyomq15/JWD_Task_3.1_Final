package by.tr.likeitnetwork.entity.input;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ThemeInput extends Input implements Serializable {
    private Map<String, String> localizedNameMap;

    public ThemeInput(){
        localizedNameMap = new HashMap<>();
    }

    public Map<String, String> getLocalizedNameMap() {
        return localizedNameMap;
    }

    public void setLocalizedNameMap(Map<String, String> localizedNameMap) {
        this.localizedNameMap = localizedNameMap;
    }

    public void putLocalizedName(String language, String name) {
        localizedNameMap.put(language, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ThemeInput)) return false;
        ThemeInput that = (ThemeInput) o;
        return Objects.equals(localizedNameMap, that.localizedNameMap);
    }

    @Override
    public int hashCode() {

        return Objects.hash(localizedNameMap);
    }
}
