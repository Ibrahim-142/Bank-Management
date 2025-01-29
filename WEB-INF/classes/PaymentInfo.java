public class PaymentInfo {
    String name;
    String sender;
    String reciever;
    double amount;
    String status;

    public PaymentInfo(String n, String s, double a, String r, String sta) {
        name = n;
        sender = s;
        amount = a;
        reciever = r;
        status = sta;
    }

    public PaymentInfo() {
        name = "empty";
        sender = "empty";
        reciever = "empty";
        amount = 0;
        status = "empty";
    }

    public String getsender() {
        return sender;
    }

    public String getreciever() {
        return reciever;
    }

    public double getamount() {
        return amount;
    }

    public String getusername() {
        return name;
    }

    public String getstatus() {
        return status;
    }

    public String toString() {
        return "Username: " + name + " Sender: " + sender + " Reciever: " + reciever + "Amount: " + amount;
    }
}