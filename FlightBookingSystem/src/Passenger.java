import java.util.UUID;

public class Passenger {
    String name;
    int age;
    String identificationNum;
    String mobileNum;
    String id;

    public Passenger(String name, int age, String identificationNum, String mobileNum) {
    
        this.name = name;
        this.age = age;
        this.identificationNum = identificationNum;
        this.mobileNum = mobileNum;

        id = UUID.randomUUID().toString().split("-")[0];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getIdentificationNum() {
        return identificationNum;
    }

    public void setIdentificationNum(String identificationNum) {
        this.identificationNum = identificationNum;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", identificationNum='" + identificationNum + '\'' +
                ", mobileNum='" + mobileNum + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
