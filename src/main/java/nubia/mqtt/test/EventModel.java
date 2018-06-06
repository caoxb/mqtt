package nubia.mqtt.test;

public class EventModel {
    private int id;
    private String content;

    public EventModel() {
    }

    public EventModel(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "EventModel{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
