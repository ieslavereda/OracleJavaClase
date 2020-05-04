package es.ieslavereda.tienda.classes;

public class Usuario {

	private int id;
	private String login;
	private String mail;
	private String role;
	private String password;
	
	public Usuario(int id, String login, String mail, String role, String password) {
		super();
		this.id = id;
		this.login = login;
		this.mail = mail;
		this.role = role;
		this.password = password;
	}
	public Usuario( String login, String mail, String role, String password) {
		super();
		this.login = login;
		this.mail = mail;
		this.role = role;
		this.password = password;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", login=" + login + ", mail=" + mail + ", role=" + role + ", password=" + password
				+ "]";
	}
	
	
}
