package br.com.dbconnect;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Properties;

import com.mongodb.DB;
import com.mongodb.MongoClient;

/**
 * <I><B>Nota:</B> Classe responsável pela estrutura de conexão com o MongoDB.
 */
public class Connect {

	private static Connect INSTANCE;
	private static MongoClient mongo;
	private DB db;
	private static Properties props = new Properties();
	private static Utils utils = new Utils();
	
	public Connect(){}

	/**
	 * Garante sempre uma única instância da classe Connect.
	 * 
	 * @return Retorna uma instância da classe Connect.
	 */
	// garante sempre uma única instância
	public static Connect getInstance() {
		if (INSTANCE == null)
			INSTANCE = new Connect();
		return INSTANCE;
	}

	/**
	 * Garante um único objeto da classe MongoClient.
	 * 
	 * @return Retorna um Database.
	 */
	// garante um único objeto mongo
	public DB getDB() {
		if (mongo == null) {
			try {
				createProperties();
						try {
							props.load(new FileInputStream("connect.properties"));
						} catch (IOException ex) {
							// TODO Auto-generated catch block
							utils.Log(EntityDao.class).error(ex);
						}
				if(!props.getProperty("HOST").equals("") && !props.getProperty("PORT").equals("")){
					mongo = new MongoClient(props.getProperty("HOST"), Integer.parseInt(props.getProperty("PORT")));
					List<String> mongoDB = mongo.getDatabaseNames();
					if (mongoDB.contains(props.getProperty("DB_NAME")))
						db = mongo.getDB(props.getProperty("DB_NAME"));
					else
						System.out.println("Não Possui Esse Banco: " + props.getProperty("DB_NAME"));
				}
				else{
					System.out.println("Favor Configurar o Arquivo connect.properties!");
					return null;
				}
			} catch (UnknownHostException ex) {
				utils.Log(EntityDao.class).error(ex);
			}
		}
		return db;
	}
	
	/**
	 * Função para criar Arquivo connect.properties.
	 */
	private static void createProperties(){		
		try {
			if(!new File("connect.properties").exists())
			{
			BufferedWriter bw = new BufferedWriter(new FileWriter("connect.properties"));
			bw.write("HOST=\n");
			bw.write("PORT=\n");
			bw.write("DB_NAME=\n");
			bw.write("DB_LOGIN=\n");
			bw.write("DB_PASSWORD=\n");
			bw.flush();
			bw.close();
			}
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			utils.Log(EntityDao.class).error(ex);
		}
	}
	
	/**
	 * Função para alterar os valores do Banco MongoDB dentro do arquivo connect.properties.
	 * @param HOST local do banco.
	 * @param PORT porta de acesso.
	 * @param DB_NAME nome do banco.
	 * @param DB_LOGIN usuário do banco.
	 * @param DB_PASSWORD senha do banco.
	 */
	public void setValuesConnect(String HOST, String PORT, String DB_NAME, String DB_LOGIN, String DB_PASSWORD){		
		try {
			createProperties();
			props.load(new FileInputStream("connect.properties"));
			if(HOST!="" && HOST!=null)
				props.setProperty("HOST", HOST);
			if(PORT!="" && PORT!=null)
				props.setProperty("PORT", PORT);
			if(DB_NAME!="" && DB_NAME!=null)
				props.setProperty("DB_NAME", DB_NAME);
			if(DB_LOGIN!="" && DB_LOGIN!=null)
				props.setProperty("DB_LOGIN", DB_LOGIN);
			if(DB_PASSWORD!="" && DB_PASSWORD!=null)
				props.setProperty("DB_PASSWORD", DB_PASSWORD);
			props.store(new FileOutputStream("connect.properties"),"Arquivo de configuração do Banco MongoDB");
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			utils.Log(EntityDao.class).error(ex);
		}
		
	}
	/**
	 * Função responsável por limpar todas as informações contidas no arquivo connect.properties.
	 */
	public void resetValuesConnect(){
		try
		{
		createProperties();
		properties.HOST.setValue("");
		properties.PORT.setValue("");
		properties.DB_NAME.setValue("");
		properties.DB_LOGIN.setValue("");
		properties.DB_PASSWORD.setValue("");
		}
		catch(Exception ex){
			utils.Log(EntityDao.class).error(ex);
		}
	}
	/**
	 * Função para recuperar todas as informações do Arquivo connect.properties.
	 * @return <B>Exemplo:</B><BR>
	 * [HOST: localhost, PORT: 27017, DB_NAME: exemploDB, DB_LOGIN: Exemplo, DB_PASSWORD: Exemplo123]
	 */
	public String getAllValuesConnect(){
		try
		{
		return "[HOST: "+Connect.properties.HOST.getValue()+
				", PORT: "+Connect.properties.PORT.getValue()+
				", DB_NAME: "+Connect.properties.DB_NAME.getValue()+
				", DB_LOGIN: "+Connect.properties.DB_LOGIN.getValue()+
				", DB_PASSWORD: "+Connect.properties.DB_PASSWORD.getValue()+"]";
		}
		catch(Exception ex){
			utils.Log(EntityDao.class).error(ex);
			return null;
		}
	}
	
	/**
	 * Informações das propriedades relacionadas ao Banco de Dados do MongoDB.
	 */
	public enum properties {
		// Propriedades relacionadas ao banco MongoDB
		HOST("HOST"),
		PORT("PORT"),
		DB_NAME("DB_NAME"),
		DB_LOGIN("DB_LOGIN"),
		DB_PASSWORD("DB_PASSWORD");

		private final String value;

		properties(String _value) {
			value = _value;
		}

		/**
		 * Função responsável por recuperar uma informação de uma propriedade específica dentro do arquivo connect.properties.
		 * @return System.out.println(Connect.properties.HOST.getValue());<BR> 
		 * localhost
		 */
		public String getValue() {
			// TODO Auto-generated method stub
			try {
				createProperties();
				props.load(new FileInputStream("connect.properties"));
				if (props.getProperty(value).equals(""))
					return null;
				return props.getProperty(value);
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				utils.Log(EntityDao.class).error(ex);
				return null;
			}
		}

		/**
		 * Função responsável por alterar uma informação de uma propriedade específica dentro do arquivo connect.properties.
		 * @param _value Exemplo para alterar informação do hostName. <BR>
		 * Connect.properties.HOST.setValue("");
		 */
		public void setValue(String _value) {
			// TODO Auto-generated method stub

			try {
				if (_value == null)
					_value = "";
				createProperties();
				props.load(new FileInputStream("connect.properties"));
				props.setProperty(value, _value);
				props.store(new FileOutputStream("connect.properties"),"Arquivo de configuração do Banco MongoDB");
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				utils.Log(EntityDao.class).error(ex);
			}
		}
	}
}
