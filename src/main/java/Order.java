import java.util.List;
public class Order {
    private String lastName;
    private String address;
    private String firstName;
    private String comment;
    private int metroStation;
    private String phone;
    private Number rentTime;
    private String deliveryDate;
    private List<String> color;


    public Order(String firstName, String lastName, String address, int metroStation, String phone, Number rentTime, String deliveryDate, String comment, List<String> color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.metroStation = metroStation;
        this.comment = comment;
        this.phone = phone;
        this.color = color;
    }


    public Order() {
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getMetroStation() {
        return metroStation;
    }
    public Number getRentTime() {
        return rentTime;
    }
    public void setRentTime(Number rentTime) {
        this.rentTime = rentTime;
    }
    public String getDeliveryDate() {
        return deliveryDate;
    }
    public String getComment() {
        return comment;
    }
    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public void setColor(List<String> color) {
        this.color = color;
    }
    public List<String> getColor() {
        return color;
    }

}
