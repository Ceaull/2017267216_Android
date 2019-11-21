package cn.edu.hstc.memoapplication;

/**
 * memo_app表的实体类
 */
public class Memo {
    private int id;
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Memo{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
