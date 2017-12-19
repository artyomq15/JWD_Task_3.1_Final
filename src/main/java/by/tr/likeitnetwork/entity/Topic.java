package by.tr.likeitnetwork.entity;

import java.io.Serializable;
import java.util.Date;

public class Topic implements Serializable {
    private int id;
    private String header;
    private String context;
    private Date creatingDate;
    private int userId;
    private int themeId;

    public Topic(){}

    public Topic(int id, String header, String context, Date creatingDate, int userId, int themeId) {
        this.id = id;
        this.header = header;
        this.context = context;
        this.creatingDate = creatingDate;
        this.userId = userId;
        this.themeId = themeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getCreatingDate() {
        return creatingDate;
    }

    public void setCreatingDate(Date creatingDate) {
        this.creatingDate = creatingDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getThemeId() {
        return themeId;
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Topic topic = (Topic) object;

        if (id != topic.id) return false;
        if (userId != topic.userId) return false;
        if (themeId != topic.themeId) return false;
        if (header != null ? !header.equals(topic.header) : topic.header != null) return false;
        if (context != null ? !context.equals(topic.context) : topic.context != null) return false;
        return creatingDate != null ? creatingDate.equals(topic.creatingDate) : topic.creatingDate == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (header != null ? header.hashCode() : 0);
        result = 31 * result + (context != null ? context.hashCode() : 0);
        result = 31 * result + (creatingDate != null ? creatingDate.hashCode() : 0);
        result = 31 * result + userId;
        result = 31 * result + themeId;
        return result;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", header='" + header + '\'' +
                ", context='" + context + '\'' +
                ", creatingDate=" + creatingDate +
                ", userId=" + userId +
                ", themeId=" + themeId +
                '}';
    }
}
