package by.tr.likeitnetwork.entity;

import java.io.Serializable;
import java.util.Date;

public class Topic implements Serializable {
    private int id;
    private String header;
    private String context;
    private Date creatingDate;
    private User user;
    private Theme theme;

    public Topic(){}

    public Topic(int id, String header, String context, Date creatingDate, User user, Theme theme) {
        this.id = id;
        this.header = header;
        this.context = context;
        this.creatingDate = creatingDate;
        this.user = user;
        this.theme = theme;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Topic topic = (Topic) object;

        if (id != topic.id) return false;
        if (header != null ? !header.equals(topic.header) : topic.header != null) return false;
        if (context != null ? !context.equals(topic.context) : topic.context != null) return false;
        if (creatingDate != null ? !creatingDate.equals(topic.creatingDate) : topic.creatingDate != null) return false;
        if (user != null ? !user.equals(topic.user) : topic.user != null) return false;
        return theme != null ? theme.equals(topic.theme) : topic.theme == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (header != null ? header.hashCode() : 0);
        result = 31 * result + (context != null ? context.hashCode() : 0);
        result = 31 * result + (creatingDate != null ? creatingDate.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (theme != null ? theme.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", header='" + header + '\'' +
                ", context='" + context + '\'' +
                ", creatingDate=" + creatingDate +
                ", user=" + user +
                ", theme=" + theme +
                '}';
    }
}
