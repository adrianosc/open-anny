package br.com.dbconnect;

public class Main {

	/**
	 * Nota: Classe Principal.
	 */
	public static void main(String[] args) {
		// Connect c = new Connect();
		// c.setValuesConnect("localhost", "27017", "annyDB",
		// "OpenAnny","OpenAnny123");
		// // //System.out.println(Connect.properties.HOST.getValue());
		// //
		// FileProperties f = new FileProperties();
		// f.setValuesLogDatabase("localhost", "27017", "annyDB", "Log",
		// "OpenAnny", "OpenAnny123",
		// "{\"Milisegundos\": \"%r\",\"Classe\": \"%C\" ,\"Thread\": \"%t\" ,\"Data\": \"%d{dd/MM/yyyy}\" ,\"Hora\": \"%d{HH:mm:ss}\" ,\"Local\": \"%l\", \"Prioridade\": \"%p\", \"Mensagem\": \"%m\"}");
		// f.setValuesLogFile("log/app/app.log",
		// "Milisegundos desde o início do programa: %r %nClasse: %C %nThread: %t %nData: %d{dd/MM/yyyy} %nHora: %d{HH:mm:ss} %nLocal: %l %nPrioridade: %p %nMensagem: %m %n----------------------------------------%n");
		// System.out.println(f.getAllValuesProperties());
		// System.out.println(c.getAllValuesConnect());
		String json = "{\"firstname\" : \"Jhon\",\"lastname\" : \"Lindo Tesouro\",\"age\" : 24}";
		
		
		for(Object o : new EntityDao(User.class).getRegister(2, true))
		{
			System.out.println(o.toString());
		}
		// User u = new User("Adriano", "Santos da Cruz", 23);
		// new EntityDao(User.class).save(json);
	}

}
