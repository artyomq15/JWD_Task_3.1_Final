package by.tr.likeitnetwork.entity.input;

import java.io.Serializable;
import java.util.Objects;

public class MessageInput extends Input implements Serializable {
    private String context;
    private int userId;
    private int topicId;

    public MessageInput(){}

    public MessageInput(String context, int userId, int topicId) {
        this.context = context;
        this.userId = userId;
        this.topicId = topicId;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MessageInput)) return false;
        MessageInput that = (MessageInput) o;
        return userId == that.userId &&
                topicId == that.topicId &&
                Objects.equals(context, that.context);
    }

    @Override
    public int hashCode() {
        return Objects.hash(context, userId, topicId);
    }

    @Override
    public String toString() {
        return "MessageInput{" +
                "context='" + context + '\'' +
                ", userId=" + userId +
                ", topicId=" + topicId +
                '}';
    }
}
