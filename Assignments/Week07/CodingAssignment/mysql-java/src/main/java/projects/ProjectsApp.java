package projects;

import projects.dao.JDBCconnection;

public class ProjectsApp {

	public static void main(String[] args) {
		
		// Connection Method Call
		JDBCconnection.getConnection();
	}

}
