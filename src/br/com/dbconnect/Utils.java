package br.com.dbconnect;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.google.gson.Gson;

/**
 * <I><B>Nota:</B> Classe com métodos útils.
 */
public class Utils {

	private static Logger logger;

	/**
	 * Função para converter um json em um objeto do tipo passado por parâmetro.
	 * @param json {"age": 25}
	 * @param generica User.class
	 * @return User{firstName='null', lastName='null', age=25}
	 */
	public Object convertToObject(String json, Class<?> generica) {
		try {
			return new Gson().fromJson(json, generica);
		} catch (Exception ex) {
			Log(Utils.class).error(ex);
			return null;
		}
	}

	/**
	 * Função para converter um objeto em um json.
	 * @param classe Objeto de uma classe.
	 * @return <B>Exemplo:</B><BR>
	 * User json = new User("Robert", "Downey Jr.", 35);<BR>
	 * System.out.println(new Utils().convertToJson(json));<BR>
	 * {"firstname":"Robert","lastname":"Downey Jr.","age":35}		
	 */
	public String convertToJson(Object classe) {
		try {
			return new Gson().toJson(classe);
		} catch (Exception ex) {
			Log(Utils.class).error(ex);
			return ex.getMessage();
		}
	}

	/**
	 * Função com configurações para Log.
	 * @param generica Objeto de uma classe.
	 * @return Um objeto do tipo Logger.
	 */
	public Logger Log(Class<?> generica) {
		logger = Logger.getLogger(generica);
		try {
			if(!new File("log4j.properties").exists()){
				System.out.println("Favor Configurar o Arquivo log4j.properties");
				return null;
			}
			PropertyConfigurator.configure("log4j.properties");
			return logger;
		} catch (Exception ex) {
			logger.error(ex);
			return null;
		}
	}
}