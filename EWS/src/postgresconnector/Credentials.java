package postgresconnector;

import java.util.Properties;

public class Credentials {
	private static String username;
	private static String pwd;
	
	public Credentials(String user, String pass)
	{
		username=user;
		pwd=pass;
	}

	public Properties getLoginForDB()
	{
		Properties props = new Properties();
		props.setProperty("user", username);
		props.setProperty("password", pwd);
		return props;
	}

}
