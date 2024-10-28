package framework.steps;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ScenarioContext {
    private Map<String, Object> data;

    public ScenarioContext(){
        data = new HashMap<>();
    }

    public void setData(String key, Object value) {
        this.data.put(key, value);
        log.info("[SCENARIO CONTEXT] Enriched with a new element - Key:" + key + ", Value:" + value.toString());
    }

    public Object getData(String key) {
        Object value = this.data.get(key);
        if (value != null) {
            log.info("[SCENARIO CONTEXT] Element retrieved - Key: " + key + ", Value: " + value.toString());
        } else {
            log.info("[SCENARIO CONTEXT] No element found for key: " + key);
        }

        return value;
    }
    public void clearData() {
        data.clear();
    }

}
