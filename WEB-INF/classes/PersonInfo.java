public class PersonInfo {
    String name; 
String password; 
String role;
String balance; 
public PersonInfo(String n, String p, String r,String b) { 
name = n; 
password = p; 
role = r;
balance=b; 
} 
public PersonInfo(){
    name = "empty"; 
password = "empty"; 
role = "empty";
balance="empty"; 
}
public void setusername(String name){
    this.name=name;
}
public void setpassword(String password){
    this.password=password;
}
public void setbalance(String balance){
    this.balance=balance;
}
public void setrole(String role){
    this.role=role;
}
public String getrole (){
    return role;
}
public String getusername (){
    return name;
}
public String toString( ){ 
 return "Name: " + name + " Password: " + password + " Role: " + role+" Balance: "+balance; 
}
}