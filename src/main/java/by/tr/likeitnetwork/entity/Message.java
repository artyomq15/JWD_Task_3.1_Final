package by.tr.likeitnetwork.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Message implements Serializable {
    private int id;
    private String context;
    private String creatingDate;
    private int likes;
    private User user;
    private int topicId;
    private List<Integer> likedUserId;

    public Message(){

    }

    public Message(int id, String context, String creatingDate, int likes, User user, int topicId, List<Integer> likedUserId) {
        this.id = id;
        this.context = context;
        this.creatingDate = creatingDate;
        this.likes = likes;
        this.user = user;
        this.topicId = topicId;
        this.likedUserId = likedUserId;
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

    public String getCreatingDate() {
        return creatingDate;
    }

    public void setCreatingDate(String creatingDate) {
        this.creatingDate = creatingDate;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public List<Integer> getLikedUserId() {
        return likedUserId;
    }

    public void setLikedUserId(List<Integer> likedUserId) {
        this.likedUserId = likedUserId;
    }

    public void addLikedUserId (Integer userId) {
        likedUserId.add(userId);
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
        if (topicId != message.topicId) return false;
        if (context != null ? !context.equals(message.context) : message.context != null) return false;
        if (creatingDate != null ? !creatingDate.equals(message.creatingDate) : message.creatingDate != null)
            return false;
        if (user != null ? !user.equals(message.user) : message.user != null) return false;
        return likedUserId != null ? likedUserId.equals(message.likedUserId) : message.likedUserId == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (context != null ? context.hashCode() : 0);
        result = 31 * result + (creatingDate != null ? creatingDate.hashCode() : 0);
        result = 31 * result + likes;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + topicId;
        result = 31 * result + (likedUserId != null ? likedUserId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", context='" + context + '\'' +
                ", creatingDate=" + creatingDate +
                ", likes=" + likes +
                ", user=" + user +
                ", topic=" + topicId +
                ", likedUserId=" + likedUserId +
                '}';
    }
}
