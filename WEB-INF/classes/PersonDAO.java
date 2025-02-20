import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {
	public PersonInfo searchPerson(String sName) {
		PersonInfo person = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1/bank";
			Connection con = DriverManager.getConnection(url, "root", "root");
			String sql = "SELECT * FROM info WHERE username = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, sName);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				String name = rs.getString("username");
				String pwd = rs.getString("password");
				String role = rs.getString("role");
				String balance = rs.getString("balance");
				person = new PersonInfo(name, pwd, role, balance);
			}
			con.close();
		} catch (Exception ex) {
			return null;
		}

		return person;
	}

	public PersonInfo insertPerson(String sName, String pwd, String balance) {
		PersonInfo person = null;
		try {

			Class.forName("com.mysql.jdbc.Driver");

			String url = "jdbc:mysql://127.0.0.1/bank";

			Connection con = DriverManager.getConnection(url, "root", "root");

			Statement st = con.createStatement();

			String query = "INSERT INTO info(username,password,role,balance)VALUES('" + sName + "','" + pwd
					+ "','user',"
					+ balance + ") ";
			int rs = st.executeUpdate(query);

			if (rs == 1) {
				String sql = "SELECT * FROM info WHERE username = ?";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setString(1, sName);
				ResultSet rs2 = pst.executeQuery();
				if (rs2.next()) {
					String name = rs2.getString("username");
					String pwddb = rs2.getString("password");
					String role = rs2.getString("role");
					String balancedb = rs2.getString("balance");
					person = new PersonInfo(name, pwddb, role, balancedb);
				}
				con.close();
			}
			st.close();
			con.close();
		} catch (Exception e) {
			return null;
		}
		return person;

	}

	public PersonInfo updatePerson(int id, String newname, String newpwd, String newbalancestr) {
		PersonInfo person = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
	
			String url = "jdbc:mysql://127.0.0.1/bank";
			Connection con = DriverManager.getConnection(url, "root", "root");
	
			Statement st = con.createStatement();
	
			String query = "SELECT * FROM info WHERE id=" + id;
	
			ResultSet rs = st.executeQuery(query);
	
			if (rs.next()) {
				String oldpwd = rs.getString("password");
				Double oldbalance = rs.getDouble("balance");
	
				if (!newpwd.isEmpty()) {
					if (!oldpwd.isEmpty()) {
						String updatePassword = "UPDATE info SET password='" + newpwd + "' WHERE id=" + id;
						st.executeUpdate(updatePassword);
					} else {
						return null;
					}
				} else {
					return null;
				}
	
				if (!newbalancestr.isEmpty()) {
					Double newbalancedouble = null;
					try {
						newbalancedouble = Double.parseDouble(newbalancestr);
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
					if (newbalancedouble != null) {
						String updateBalance = "UPDATE info SET balance=" + newbalancedouble + " WHERE id=" + id;
						int rs2 = st.executeUpdate(updateBalance);
						if (rs2 < 0) {
							return null;
						}
					}
				}
	
				if (!newname.isEmpty()) {
					String updateUsername = "UPDATE info SET username='" + newname + "' WHERE id=" + id;
					int rs2 = st.executeUpdate(updateUsername);
					if (rs2 < 0) {
						return null;
					}
				}
	
				person = new PersonInfo();
	
				if (newname.isEmpty()) {
					person.setusername(rs.getString("username"));
				} else {
					person.setusername(newname);
				}
	
				if (newpwd.isEmpty()) {
					person.setpassword(oldpwd);
				} else {
					person.setpassword(newpwd);
				}
	
				if (!newbalancestr.isEmpty()) {
					try {
						Double newBalance = Double.parseDouble(newbalancestr);
						person.setbalance(String.valueOf(newBalance));
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				} else {
					person.setbalance(String.valueOf(oldbalance));
				}
			} else {
				return null;
			}
	
			person.setrole("user");
	
			st.close();
			con.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return person;
	}
	
	public PersonInfo deletePerson(int id) {
		PersonInfo person = null;
		try {

			Class.forName("com.mysql.jdbc.Driver");

			String url = "jdbc:mysql://127.0.0.1/bank";

			Connection con = DriverManager.getConnection(url, "root", "root");
			String sql = "SELECT * FROM info WHERE id = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				String namedb = rs.getString("username");
				String pwd = rs.getString("password");
				String role = rs.getString("role");
				String balance = rs.getString("balance");
				person = new PersonInfo(namedb, pwd, role, balance);
			}

			Statement st = con.createStatement();

			String query = "Delete from info Where id="+id;

			System.out.println(query);

			st.executeUpdate(query);
			st.close();
			con.close();

		} catch (Exception e) {
			return null;
		}
		return person;
	}

	public PersonInfo Login(String uname, String password) {
		PersonInfo person = null;
		String balancestr = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1/bank";
			Connection con = DriverManager.getConnection(url, "root", "root");
			String query = "SELECT username, password, role, balance FROM info WHERE username = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, uname);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String dbpassword = rs.getString("password");
				String role = rs.getString("role");
				double balancedouble = rs.getDouble("balance");
				try {
					balancestr = String.valueOf(balancedouble);
				} catch (NumberFormatException e) {
					System.out.println(e);
				}
				if (password.equals(dbpassword)) {
					person = new PersonInfo(uname, dbpassword, role, balancestr);
				} else {
					return null;
				}
			} else {
				return null;
			}
			rs.close();
			ps.close();
			con.close();
		} catch (Exception e) {
			return null;
		}
		return person;
	}

	public PersonInfo GetPersonInfo(String username, String role) {
		PersonInfo person = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1/bank";
			Connection con = DriverManager.getConnection(url, "root", "root");
			Statement st = con.createStatement();
			String sessionquery = "SELECT username, password, role, balance FROM info WHERE username = '" + username
					+ "'";
			ResultSet rs = st.executeQuery(sessionquery);

			if (rs.next()) {
				String pwd = rs.getString("password");
				if (role.equals("admin")) {
					person = new PersonInfo(username, pwd, role, null);
				} else {
					double balance = rs.getDouble("balance");
					String balancestr = String.valueOf(balance);
					person = new PersonInfo(username, pwd, role, balancestr);
				}
			}

			rs.close();
			st.close();
			con.close();
		} catch (Exception e) {
			return null;
		}
		return person;
	}

	public List<PaymentInfo> GetPayments(String username, String role) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1/bank";
			Connection con = DriverManager.getConnection(url, "root", "root");
			Statement st = con.createStatement();

			List<PaymentInfo> paymentlist = new ArrayList<>();
			String query;
			if (role.equals("admin")) {
				query = "SELECT * FROM payments";
			} else {
				query = "SELECT * FROM payments WHERE username = '" + username + "'";
			}
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				PaymentInfo payment = new PaymentInfo(
						rs.getString("username"),
						rs.getString("sendername"),
						rs.getDouble("amount"),
						rs.getString("recievername"),
						rs.getString("status"));
				paymentlist.add(payment);
			}

			rs.close();
			st.close();
			con.close();

			return paymentlist;
		} catch (Exception e) {

			return null;
		}
	}

	public PersonInfo Withdraw(String pwd, double amount, String sessionusername) {
		PersonInfo person = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1/bank";
			Connection con = DriverManager.getConnection(url, "root", "root");
			Statement st = con.createStatement();

			String query = "SELECT password, balance FROM info WHERE username = '" + sessionusername + "'";
			ResultSet rs = st.executeQuery(query);

			if (rs.next()) {
				String dbpassword = rs.getString("password");
				double balancecheck = rs.getDouble("balance");

				if (pwd.equals(dbpassword)) {
					if (amount <= balancecheck) {
						String update = "UPDATE info SET balance = balance - " + amount + " WHERE username = '"
								+ sessionusername + "'";
						String log = "INSERT INTO deposits(username, withdrawn) VALUES ('" + sessionusername + "', "
								+ amount + ")";

						int rs2 = st.executeUpdate(update);
						int rs3 = st.executeUpdate(log);

						if (rs2 > 0 && rs3 > 0) {
							String updatedBalanceQuery = "SELECT balance FROM info WHERE username = '" + sessionusername
									+ "'";
							ResultSet rsupdated = st.executeQuery(updatedBalanceQuery);

							if (rsupdated.next()) {
								double updatedbalance = rsupdated.getDouble("balance");
								String updatedbalancestr = String.valueOf(updatedbalance);
								person = new PersonInfo(sessionusername, pwd, "user", updatedbalancestr);
							}
							rsupdated.close();
						}
					}
				} else {
					return null;
				}
			}

			rs.close();
			st.close();
			con.close();
		} catch (Exception e) {
			return null;
		}
		return person;
	}

	public PersonInfo Deposit(String pwd, double amount, String sessionusername) {
		PersonInfo person = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1/bank";
			Connection con = DriverManager.getConnection(url, "root", "root");
			Statement st = con.createStatement();

			String query = "SELECT password, balance FROM info WHERE username = '" + sessionusername + "'";
			ResultSet rs = st.executeQuery(query);

			if (rs.next()) {
				String dbpassword = rs.getString("password");
				if (pwd.equals(dbpassword)) {

					String update = "UPDATE info SET balance = balance + " + amount + " WHERE username = '"
							+ sessionusername + "'";
					String log = "INSERT INTO deposits(username, deposited) VALUES ('" + sessionusername + "', "
							+ amount + ")";

					int rs2 = st.executeUpdate(update);
					int rs3 = st.executeUpdate(log);

					if (rs2 > 0 && rs3 > 0) {
						String updatequery = "SELECT balance FROM info WHERE username = '" + sessionusername
								+ "'";
						ResultSet rsupdated = st.executeQuery(updatequery);

						if (rsupdated.next()) {
							double updatedbalance = rsupdated.getDouble("balance");
							String updatedbalancestr = String.valueOf(updatedbalance);
							person = new PersonInfo(sessionusername, pwd, "user", updatedbalancestr);
						}
						rsupdated.close();
					}

				} else {
					return null;
				}
			}

			rs.close();
			st.close();
			con.close();
		} catch (Exception e) {
			return null;
		}
		return person;
	}

	public PersonInfo TransferPayment(String sender, String recipent, double amount, String pwd) {
		PersonInfo person = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1/bank";
			Connection con = DriverManager.getConnection(url, "root", "root");
			Statement st = con.createStatement();
			String checkbalance = "SELECT balance, password FROM info WHERE username = '" + sender + "'";
			ResultSet rs = st.executeQuery(checkbalance);

			if (!rs.next()) {
				return null;
			}

			double senderbalance = rs.getDouble("balance");
			String dbpassword = rs.getString("password");
			rs.close();

			if (senderbalance < amount) {
				return null;
			}

			if (!dbpassword.equals(pwd)) {
				return null;
			}
			String checkrecipent = "SELECT username FROM info WHERE username = '" + recipent + "'";
			rs = st.executeQuery(checkrecipent);
			if (!rs.next()) {
				return null;
			}
			rs.close();

			String updatesender = "UPDATE info SET balance = balance - " + amount + " WHERE username = '" + sender
					+ "'";
			int rs2 = st.executeUpdate(updatesender);
			if (rs2 == 0) {
				return null;
			}
			String updaterecipient = "UPDATE info SET balance = balance + " + amount + " WHERE username = '" + recipent
					+ "'";
			int rs3 = st.executeUpdate(updaterecipient);
			if (rs3 == 0) {
				return null;
			}
			String log = "INSERT INTO payments (username, sendername, recievername, amount, status) " +
					"VALUES ('" + sender + "', '" + sender + "', '" + recipent + "', " + amount + ", 'completed')";
			int rs4 = st.executeUpdate(log);
			if (rs4 == 0) {
				return null;
			}
			String updatequery = "SELECT balance FROM info WHERE username = '" + sender + "'";
			rs = st.executeQuery(updatequery);
			if (rs.next()) {
				double updatedsenderbalance = rs.getDouble("balance");
				String updatedbalancestr = String.valueOf(updatedsenderbalance);
				person = new PersonInfo(sender, pwd, "user", updatedbalancestr);
			}
			rs.close();

		} catch (Exception e) {
			return null;

		}

		return person;
	}
}
