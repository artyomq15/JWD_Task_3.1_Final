package by.tr.likeitnetwork.entity;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    private int id;
    private String context;
    private Date creatingDate;
    private int likes;
    private int userId;
    private int topicId;

    public Message(){

    }

    public Message(int id, String context, Date creatingDate, int likes, int userId, int topicId) {
        this.id = id;
        this.context = context;
        this.creatingDate = creatingDate;
        this.likes = likes;
        this.userId = userId;
        this.topicId = topicId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Message message = (Message) object;

        if (id != message.id) return false;
        if (likes != message.likes) return false;
        if (userId != message.userId) return false;
        if (topicId != message.topicId) return false;
        if (context != null ? !context.equals(message.context) : message.context != null) return false;
        return creatingDate != null ? creatingDate.equals(message.creatingDate) : message.creatingDate == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (context != null ? context.hashCode() : 0);
        result = 31 * result + (creatingDate != null ? creatingDate.hashCode() : 0);
        result = 31 * result + likes;
        result = 31 * result + userId;
        result = 31 * result + topicId;
        return result;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", context='" + context + '\'' +
                ", creatingDate=" + creatingDate +
                ", likes=" + likes +
                ", userId=" + userId +
                ", topicId=" + topicId +
                '}';
    }
}
