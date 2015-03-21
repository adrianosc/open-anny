package br.com.dbconnect;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 * <I><B>Nota:</B> Essa classe é responsável por gerar e alterar o arquivo log4j.properties.
 */
public class FileProperties {

	private final static Properties props = new Properties();
	private static Utils utils = new Utils();
	
	/**
	 * Função para criar Arquivo log4j.properties.
	 */
	private static void createProperties(){		
		try {
			if(!new File("log4j.properties").exists())
			{
			BufferedWriter bw = new BufferedWriter(new FileWriter("log4j.properties"));
			// Informações para Banco MongoDB
			bw.write("log4j.rootLogger=INFO\n");
			bw.write("log4j.appender.MongoDB.hostname=\n");
			bw.write("log4j.appender.MongoDB.port=\n");
			bw.write("log4j.appender.MongoDB.databaseName=\n");
			bw.write("log4j.appender.MongoDB.collectionName=\n");
			bw.write("log4j.appender.MongoDB.userName=\n");
			bw.write("log4j.appender.MongoDB.password=\n");
			bw.write("log4j.appender.MongoDB=\n");
			bw.write("log4j.appender.MongoDB.layout=\n");
			bw.write("log4j.appender.MongoDB.layout.ConversionPattern=\n");
			// Informações para Arquivo de Log
			bw.write("log4j.appender.fout=\n");
			bw.write("log4j.appender.fout.File=\n");
			bw.write("log4j.appender.fout.layout=\n");
			bw.write("log4j.appender.fout.layout.ConversionPattern=\n");
			bw.flush();
			bw.close();
			}
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			utils.Log(EntityDao.class).error(ex);
		}
	}
	/**
	 * Função para alterar as informações do arquivo log4j.properties referente a Log no Banco de Dados.
	 * @param hostname Nome do Host da base onde se encontra o banco de dados MongoDB.
	 * @param port Porta da base onde se encontra o banco de dados MongoDB.
	 * @param databaseName Nome da base de dados.
	 * @param collectionName Nome da tabela.
	 * @param userName Nome do usuário.
	 * @param password Senha do banco.
	 * @param conversionPattern Formato de como deverá ser o Log.
	 */
	public void setValuesLogDatabase(String hostname, String port,
			String databaseName, String collectionName, String userName,
			String password, String conversionPattern) {

		try {
			createProperties();
			props.load(new FileInputStream("log4j.properties"));

			if (!props.getProperty("log4j.rootLogger").contains("MongoDB"))
				props.setProperty("log4j.rootLogger",
						props.getProperty("log4j.rootLogger") + ",MongoDB");
			if (hostname != "" && hostname != null)
				props.setProperty("log4j.appender.MongoDB.hostname", hostname);
			if (port != "" && port != null)
				props.setProperty("log4j.appender.MongoDB.port", port);
			if (databaseName != "" && databaseName != null)
				props.setProperty("log4j.appender.MongoDB.databaseName",
						databaseName);
			if (collectionName != "" && collectionName != null)
				props.setProperty("log4j.appender.MongoDB.collectionName",
						collectionName);
			if (userName != "" && userName != null)
				props.setProperty("log4j.appender.MongoDB.userName", userName);
			if (password != "" && password != null)
				props.setProperty("log4j.appender.MongoDB.password", password);
			props.setProperty("log4j.appender.MongoDB",
					"org.log4mongo.MongoDbPatternLayoutAppender");
			props.setProperty("log4j.appender.MongoDB.layout",
					"org.log4mongo.MongoDbPatternLayout");
			if (conversionPattern != "" && conversionPattern != null)
				props.setProperty(
						"log4j.appender.MongoDB.layout.ConversionPattern",
						conversionPattern);
			props.store(new FileOutputStream("log4j.properties"),"Arquivo de configuração de Log");
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			utils.Log(EntityDao.class).error(ex);
		}

	}

	/**
	 * Função para alterar as informações do arquivo log4j.properties referente a Log em um Arquivo.
	 * @param pathFile Caminho onde será gravado o Arquivo de Log.
	 * @param conversionPattern Formato de como deverá ser o Log.
	 */
	public void setValuesLogFile(String pathFile, String conversionPattern) {
		try {
			createProperties();
			props.load(new FileInputStream("log4j.properties"));
			if (!props.getProperty("log4j.rootLogger").contains("fout"))
				props.setProperty("log4j.rootLogger",
						props.getProperty("log4j.rootLogger") + ",fout");
			props.setProperty("log4j.appender.fout",
					"org.apache.log4j.FileAppender");
			if (pathFile != "" && pathFile != null)
				props.setProperty("log4j.appender.fout.File", pathFile);
			props.setProperty("log4j.appender.fout.layout",
					"org.apache.log4j.PatternLayout");
			if (conversionPattern != "" && conversionPattern != null)
				props.setProperty(
						"log4j.appender.fout.layout.ConversionPattern",
						conversionPattern);
			props.store(new FileOutputStream("log4j.properties"),"Arquivo de configuração de Log");
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			utils.Log(EntityDao.class).error(ex);
		}
	}
	
	/**
	 * Função para alterar todas as propriedades do arquivo log4j.properties.
	 * @param hostname Nome do Host da base onde se encontra o banco de dados MongoDB.
	 * @param port Porta da base onde se encontra o banco de dados MongoDB.
	 * @param databaseName Nome da base de dados.
	 * @param collectionName Nome da tabela.
	 * @param userName Nome do usuário.
	 * @param password Senha do banco
	 * @param conversionPattern Formato de como deverá ser o Log do Banco de Dados.
	 * @param pathFile Caminho onde será gravado o Arquivo de Log.
	 * @param conversionPatternLog Formato de como deverá ser o Log.
	 */
	public void setAllValues(String hostname, String port,
			String databaseName, String collectionName, String userName,
			String password, String conversionPattern,String pathFile, String conversionPatternLog ){
		try{
		setValuesLogDatabase(hostname, port, databaseName, collectionName, userName, password, conversionPatternLog);
		setValuesLogFile(pathFile, conversionPatternLog);
		}
		catch(Exception ex){
			utils.Log(EntityDao.class).error(ex);
		}
	}

	/**
	 * Função responsável por limpar as informações contidas no arquivo log4j.properties referente a Log no Banco de Dados.
	 */
	public void resetValuesLogDatabase() {
		try {
			createProperties();
			properties.MongoDB_hostName.setValue(null);
			properties.MongoDB_port.setValue(null);
			properties.MongoDB_databaseName.setValue(null);
			properties.MongoDB_collectionName.setValue(null);
			properties.MongoDB_userName.setValue(null);
			properties.MongoDB_password.setValue(null);
			properties.MongoDB_conversionPattern.setValue(null);

			props.load(new FileInputStream("log4j.properties"));

			props.setProperty("log4j.rootLogger", "INFO");
			props.setProperty("log4j.appender.MongoDB", "");
			props.setProperty("log4j.appender.MongoDB.layout", "");
			props.store(new FileOutputStream("log4j.properties"),"Arquivo de configuração de Log");
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			utils.Log(EntityDao.class).error(ex);
		}
	}

	/**
	 * Função responsável por limpar as informações contidas no arquivo log4j.properties referente a Log em um Arquivo.
	 */
	public void resetValuesLogFile() {
		try {
			createProperties();
			properties.FileOut_pathFile.setValue("");
			properties.FileOut_conversionPattern.setValue("");
			
			props.load(new FileInputStream("log4j.properties"));
			props.setProperty("log4j.rootLogger", "INFO");
			props.setProperty("log4j.appender.fout", "");
			props.setProperty("log4j.appender.fout.layout", "");
			props.store(new FileOutputStream("log4j.properties"),"Arquivo de configuração de Log");
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			utils.Log(EntityDao.class).error(ex);
		}
	}
	
	/**
	 * Função responsável por limpar todas as informações contidas no arquivo log4j.properties.
	 */
	public void resetAllLogs(){
		try
		{
		resetValuesLogDatabase();
		resetValuesLogFile();
		}
		catch(Exception ex){
			utils.Log(EntityDao.class).error(ex);
		}
	}

	/**
	 * Função responsável por recuperar as informações contidas no arquivo log4j.properties referente a Log no Banco de Dados.
	 * @return <B>Exemplo:</B><BR>
	 * <B>hostname:</B> localhost, <BR>
	 * <B>port:</B> 27017, <BR>
	 * <B>databaseName:</B> databaseDB, <BR>
	 * <B>collectionName:</B> Log, <BR>
	 * <B>userName:</B> MongoDB, <BR>
	 * <B>password:</B> MongoDB123, <BR>
	 * <B>conversionPattern:</B> {"Milisegundos": "%r","Classe": "%C" ,"Thread": "%t" ,"Data": "%d{dd/MM/yyyy}" ,"Hora": "%d{HH:mm:ss}" ,"Local": "%l", "Prioridade": "%p", "Mensagem": "%m"}
	 */
	public String getValuesLogDatabase() {
		try {
			return "hostname: " + properties.MongoDB_hostName.getValue()
					+ ", \n" + "port: " + properties.MongoDB_port.getValue()
					+ ", \n" + "databaseName: "
					+ properties.MongoDB_databaseName.getValue() + ", \n"
					+ "collectionName: "
					+ properties.MongoDB_collectionName.getValue() + ", \n"
					+ "userName: " + properties.MongoDB_userName.getValue()
					+ ", \n" + "password: "
					+ properties.MongoDB_password.getValue() + ", \n"
					+ "conversionPattern: "
					+ properties.MongoDB_conversionPattern.getValue();
		} catch (Exception ex) {
			// TODO
			utils.Log(EntityDao.class).error(ex);
			return null;
		}
	}

	/**
	 * Função responsável por recuperar as informações contidas no arquivo log4j.properties referente a Log em um Arquivo.
	 * @return <B>Exemplo:</B><BR>
	 * <B>pathFile:</B> log/app.log, <BR>
	 * <B>conversionPattern:</B> Milisegundos desde o início do programa: %r %nClasse: %C %nThread: %t %nData: %d{dd/MM/yyyy} %nHora: %d{HH:mm:ss} %nLocal: %l %nPrioridade: %p %nMensagem: %m %n----------------------------------------%n
	 */
	public String getValuesLogFile() {
		try {
			return "pathFile: " + properties.FileOut_pathFile.getValue() + ", "
					+ "\nconversionPattern: "
					+ properties.FileOut_conversionPattern.getValue();
		} catch (Exception ex) {
			utils.Log(EntityDao.class).error(ex);
			return null;
		}
	}

	/**
	 * Função para retornar todos os valores dentro do arquivo log4j.properties.<BR><BR>
	 * <B>Exemplo:</B><BR> 
	 * LogDatabase[<BR>
	 * hostname: localhost,<BR>
	 * port: 27017,<BR> 
	 * databaseName: databaseDB,<BR> 
	 * collectionName: Log,<BR> 
	 * userName: MongoDB,<BR> 
	 * password: MongoDB123,<BR> 
	 * conversionPattern: {"Milisegundos": "%r","Classe": "%C" ,"Thread": "%t" ,"Data": "%d{dd/MM/yyyy}" ,"Hora": "%d{HH:mm:ss}" ,"Local": "%l", "Prioridade": "%p", "Mensagem": "%m"}<BR>
	 * ]<BR>
	 * LogFile[<BR>
	 * pathFile: log/app.log,<BR> 
	 * conversionPattern: Milisegundos desde o início do programa: %r %nClasse: %C %nThread: %t %nData: %d{dd/MM/yyyy} %nHora: %d{HH:mm:ss} %nLocal: %l %nPrioridade: %p %nMensagem: %m %n----------------------------------------%n<BR>
	 * ]
	 */
	public String getAllValuesProperties() {
		try {
			return "LogDatabase[\n" + getValuesLogDatabase()
					+ "\n]\nLogFile[\n" + getValuesLogFile() + "\n]";
		} catch (Exception ex) {
			utils.Log(EntityDao.class).error(ex);
			return null;
		}
	}

	/**
	 * Informações das propriedades relacionadas ao Banco de Dados do MongoDB e Arquivo de Log.
	 */
	public enum properties {
		// Propriedades relacionadas ao banco MongoDB
		MongoDB_hostName("log4j.appender.MongoDB.hostname"), 
		MongoDB_port("log4j.appender.MongoDB.port"),
		MongoDB_databaseName("log4j.appender.MongoDB.databaseName"), 
		MongoDB_collectionName("log4j.appender.MongoDB.collectionName"), 
		MongoDB_userName("log4j.appender.MongoDB.userName"), 
		MongoDB_password("log4j.appender.MongoDB.password"), 
		MongoDB_conversionPattern("log4j.appender.MongoDB.layout.ConversionPattern"),
		// Propriedades relacionadas ao arquivo de Log
		FileOut_pathFile("log4j.appender.fout.File"), 
		FileOut_conversionPattern("log4j.appender.fout.layout.ConversionPattern");

		private final String value;

		properties(String _value) {
			value = _value;
		}

		/**
		 * Função responsável por recuperar uma informação de uma propriedade específica dentro do arquivo log4j.properties.
		 * @return System.out.println(FileProperties.properties.MongoDB_hostName.getValue());<BR> 
		 * localhost
		 */
		public String getValue() {
			// TODO Auto-generated method stub
			try {
				createProperties();
				props.load(new FileInputStream("log4j.properties"));
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
		 * Função responsável por alterar uma informação de uma propriedade específica dentro do arquivo log4j.properties.
		 * @param _value Exemplo para alterar informação do hostName.<BR>
		 * FileProperties.properties.MongoDB_hostName.setValue("");
		 */
		public void setValue(String _value) {
			// TODO Auto-generated method stub

			try {
				if (_value == null)
					_value = "";
				createProperties();
				props.load(new FileInputStream("log4j.properties"));
				props.setProperty(value, _value);
				props.store(new FileOutputStream("log4j.properties"),"Arquivo de configuração de Log");
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				utils.Log(EntityDao.class).error(ex);
			}
		}
	}
}