package recipes;

//import java.sql.Connection;

import recipes.dao.DbConnection;

public class recipes {

	public static void main(String[] args) {

		DbConnection.getConnection();

	}

}
