package by.tr.likeitnetwork.entity.input;

import java.io.Serializable;
import java.util.Objects;

public class TopicInput extends Input implements Serializable {
    private String header;
    private String context;
    private int themeId;

    public TopicInput(){}

    public TopicInput(String header, String context, int themeId) {
        this.header = header;
        this.context = context;
        this.themeId = themeId;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getThemeId() {
        return themeId;
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TopicInput)) return false;
        TopicInput that = (TopicInput) o;
        return themeId == that.themeId &&
                Objects.equals(header, that.header) &&
                Objects.equals(context, that.context);
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, context, themeId);
    }

    @Override
    public String toString() {
        return "TopicInput{" +
                "header='" + header + '\'' +
                ", context='" + context + '\'' +
                ", themeId=" + themeId +
                '}';
    }
}
