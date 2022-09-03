package db;

//  收入或者支出具体支出的类
public class TypeBean {
    int id;
//    类型名称
    String typename;
//    未被选中图片id
    int imageId;
//    被选中图片id
    int simageId;
//    收入1 支出0
    int kind;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getSimageId() {
        return simageId;
    }

    public void setSimageId(int simageId) {
        this.simageId = simageId;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public TypeBean() {
    }

    public TypeBean(int id, String typename, int imageId, int simageId, int kind) {
        this.id = id;
        this.typename = typename;
        this.imageId = imageId;
        this.simageId = simageId;
        this.kind = kind;
    }
}
