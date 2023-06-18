package net.atopecode.projectintegrationtest.controller.json;

public class JsonManualSerializer {

    private final String comillas = "\"";
    private StringBuilder stringBuilder;

    public JsonManualSerializer() {
        this.stringBuilder = new StringBuilder();
    }

    public JsonManualSerializer init() {
        stringBuilder.append("{");
        return this;
    }

    public JsonManualSerializer addFieldName(String fieldName) {
        stringBuilder.append(comillas + fieldName + comillas + ":");
        return this;
    }

    public JsonManualSerializer addFieldValue(Object value) {
        return addFieldValue(value, true);
    }

    public JsonManualSerializer addFieldValue(Object value, boolean appendSeparator) {
        if(value != null) {
            stringBuilder.append(comillas + value + comillas);
        }
        else {
            stringBuilder.append(value);
        }

        stringBuilder.append((appendSeparator) ? "," : "");
        return this;
    }

    public JsonManualSerializer finish() {
        stringBuilder.append("}");
        return this;
    }

    public String getResult() {
        return stringBuilder.toString();
    }
}
